/**
 * 
 */
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.adempiere.exceptions.MedsysException;
import org.apache.commons.lang.StringUtils;
import org.compiere.model.bean.PrePaymentBean;
import org.compiere.model.bean.PrePaymentLineBean;
import org.compiere.model.bean.ResponseProMujer;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.compiere.util.Utilerias;
import org.compiere.util.confHL7.MessageSenderProMujer;
import org.compiere.util.confHL7.MirthClient;

import com.ecaresoft.util.Error;
import com.ecaresoft.util.ErrorList;
import com.ecaresoft.util.PasswordHandler;
import com.mirth.connect.client.core.Client;

/**
 * Encabezado del prepago (pro mujer)
 * 
 * @author Lorena Lama
 *         Card http://control.ecaresoft.com/card/1548/
 */
public class MDHPrePayment extends X_DH_PrePayment {

	private static final long	serialVersionUID	= 1L;
	private static CLogger		sLog				= CLogger.getCLogger(MDHPrePayment.class);

	/**
	 * Listado de pre pagos (cotizaciones) pendientes de pago
	 * 
	 * @param ctx Contexto
	 * @param patientId Id de Paciente
	 * @param trxName Nombre de la transaccion
	 * @return
	 */
	public static List<MDHPrePayment> getPending(final Properties ctx, final int patientId, final String trxName) {
		final StringBuilder sql = new StringBuilder();
		sql.append("     EXME_Paciente_ID=? ");
		sql.append(" AND isPaid='N' ");
		sql.append(" AND DocStatus NOT IN (?,?) ");
		return new Query(ctx, Table_Name, sql.toString(), trxName)//
			.setOnlyActiveRecords(true)//
			.addAccessLevelSQL(true)//
			.setParameters(patientId, DOCSTATUS_Completed, DOCSTATUS_Voided)//
			.setOrderBy(" DateTrx DESC ")//
			.list();
	}

	/**
	 * Crea el registro de pre pago y actualiza el saldo del paciente
	 * 
	 * @param ctx
	 * @param header
	 * @param lines
	 * @param trxName
	 * @return
	 */
	public static ErrorList savePayment(final Properties ctx, final PrePaymentBean header, final List<PrePaymentLineBean> lines, final String trxName) {
		final ErrorList errors = new ErrorList();
		try {
			if (header == null) {
				errors.add(Error.VALIDATION_ERROR, Utilerias.getMsg(ctx, "msg.jheader.mandatory"));
			} else {
				// crea el encabezado
				final MDHPrePayment prePay = new MDHPrePayment(ctx, header.getId(), trxName);
				PrePaymentBean.fillFrom(header, prePay);

				if (prePay.save()) {
					header.setId(prePay.getDH_PrePayment_ID());
					// inserta el registro del voucher
					final MDHVoucher voucher = new MDHVoucher(ctx, 0, trxName);
					PrePaymentBean.fillFrom(header, voucher);
					// guarda el detalle de las lineas
					if (voucher.save()) {
						final int paidLines = prePay.saveLines(lines, voucher, errors);
						// si uno o todos los articulos estan excentos de pago, trata de completar el pre pago
						if (errors.isEmpty() && (BigDecimal.ZERO.compareTo(prePay.getGrandTotal()) == 0 || paidLines > 0)) {
							if (DOCSTATUS_Invalid.equals(prePay.completeIt())) {
								errors.add(new Error(Error.EXCEPTION_ERROR, Msg.parseTranslation(ctx, prePay.getProcessMsg())));
							} else if (!prePay.save()) {
								throw new MedsysException();
							}
						}

					} else {
						throw new MedsysException();
					}
				} else {
					throw new MedsysException();
				}
			}

		} catch (final Exception e) {
			sLog.log(Level.SEVERE, "savePayment", e);
			errors.add(Error.EXCEPTION_ERROR, e.getLocalizedMessage());
		}
		return errors;
	}

	/**
	 * Crea el pre pago
	 * 
	 * @param ctx
	 * @param header
	 * @param lines
	 * @param trxName
	 * @return
	 */
	public static ErrorList savePrePayment(final Properties ctx, final PrePaymentBean header, final List<PrePaymentLineBean> lines,
		final String trxName) {
		final ErrorList errors = new ErrorList();
		try {
			if (header == null) {
				errors.add(Error.VALIDATION_ERROR, Utilerias.getMsg(ctx, "msg.jheader.mandatory"));
			} else {
				final MDHPrePayment prePay = new MDHPrePayment(ctx, header.getId(), trxName);
				PrePaymentBean.fillFrom(header, prePay);
				// crea el encabezado
				if (prePay.save()) {
					header.setId(prePay.getDH_PrePayment_ID());
					header.setDocumentNo(prePay.getDocumentNo());
					// guarda el detalle de las lineas
					int paidLines = prePay.saveLines(lines, null, errors);

					// si uno o todos los articulos estan excentos de pago, se trata de completar el pre pago
					if (errors.isEmpty() && (BigDecimal.ZERO.compareTo(prePay.getGrandTotal()) == 0 || paidLines > 0)) {
						// si el proceso es invalido
						if (DOCSTATUS_Invalid.equals(prePay.completeIt())) {
							errors.add(new Error(Error.EXCEPTION_ERROR, Msg.parseTranslation(ctx, prePay.getProcessMsg())));
						} else if (!prePay.save()) {
							throw new MedsysException();
						}
					}
				} else {
					throw new MedsysException();
				}
			}

		} catch (final Exception e) {
			sLog.log(Level.SEVERE, "savePayment", e);
			errors.add(Error.EXCEPTION_ERROR, e.getLocalizedMessage());
		}
		return errors;
	}

	private String	processMsg;

	/**
	 * @param ctx
	 * @param DH_PrePayment_ID
	 * @param trxName
	 */
	public MDHPrePayment(final Properties ctx, final int DH_PrePayment_ID, final String trxName) {
		super(ctx, DH_PrePayment_ID, trxName);
		if (is_new()) {
			setDocAction(DOCACTION_Complete);
			setDocStatus(DOCSTATUS_Drafted);
			setIsPaid(false);
		}
	}

	/**
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MDHPrePayment(final Properties ctx, final ResultSet rs, final String trxName) {
		super(ctx, rs, trxName);
	}

	@Override
	protected boolean beforeSave(final boolean newRecord) {
		if (newRecord) {
			if (getDateTrx() == null) {
				setDateTrx(Env.getCurrentDate());
			} else if (getTaxAmt() == null) {
				setTaxAmt(BigDecimal.ZERO);
			} else if (getEXME_Paciente_ID() <= 0) {
				log.saveError(null, Utilerias.getMsg(getCtx(), "odontologia.msj.Paciente"));
				return false;
			}
		}
		return super.beforeSave(newRecord);
	}

	/**
	 * Completa un pago
	 * 
	 * @return Docstatus
	 */
	public String completeIt() {
		if (DOCSTATUS_Voided.equals(getDocStatus())) {
			processMsg = Msg.parseTranslation(getCtx(), "@Voided@");
			return getDocStatus();
		}
		final List<MDHPrePaymentLine> lines = getLines();
		if (lines.isEmpty()) {
			processMsg = Msg.parseTranslation(getCtx(), "@NoLines@");
			return DOCSTATUS_Invalid;
		}

		String docStatus = "";
		int count = 0;
		for (final MDHPrePaymentLine line : lines) {
			if (line.isPaid()) {
				final ErrorList errors = MDHPatientServices.saveFrom(getCtx(), getEXME_Paciente_ID(), line, get_TrxName());
				if (errors.isEmpty()) {
					count++;
				} else {
					processMsg = errors.toString();
					docStatus = DOCSTATUS_Invalid;
					break;
				}
			} else if (line.isActive() && StringUtils.isEmpty(docStatus)) {
				docStatus = DOCSTATUS_InProgress;
				setIsPaid(false);
			}
		}
		if (count == lines.size() && StringUtils.isEmpty(docStatus)) {
			docStatus = DOCSTATUS_Completed;
			setIsPaid(true);
		}
		if (!DOCSTATUS_Invalid.equals(docStatus)) {
			setDocAction(DOCACTION_Close);
		}
		return docStatus;
	}

	/**
	 * Monto total de las lineas activas
	 * 
	 * @param paid null: total de todas las lineas, true: solo pagadas, false: pendientes de cobro
	 * @return
	 */
	public BigDecimal getGrandTotal(final Boolean paid) {
		BigDecimal gradTotal;
		if (paid == null) {
			gradTotal = super.getGrandTotal();
		} else {
			final StringBuilder sql = new StringBuilder();
			sql.append(" SELECT sum(LineTotalAmt) ");
			sql.append(" FROM DH_PrePaymentLine l ");
			sql.append(" WHERE l.DH_PrePayment_ID=? ");
			sql.append("   AND l.isActive='Y' ");
			sql.append("   AND l.isPaid=? ");

			final List<Object> params = new ArrayList<>();
			params.add(getDH_PrePayment_ID());
			params.add(DB.TO_STRING(paid));

			gradTotal = DB.getSQLValueBD(null, sql.toString(), params);
		}
		return gradTotal == null ? BigDecimal.ZERO : gradTotal;
	}

	/** @return Detalle del pre pago */
	public List<MDHPrePaymentLine> getLines() {
		return MDHPrePaymentLine.getLines(getCtx(), getDH_PrePayment_ID(), get_TrxName());
	}

	/** @return cantidad de lineas pendientes de pagoo */
	public int getPendingLinesCount() {
		return getTotalLines(false);
	}

	/** @return total de impuesto pendiente */
	public BigDecimal getPendingTaxAmt() {
		return getTaxAmt(false);
	}

	/** @return monto total pendiente de pago */
	public BigDecimal getPendingTotal() {
		return getGrandTotal(false);
	}

	/** @return Mensaje de proceso */
	public String getProcessMsg() {
		return processMsg;
	}

	/** @return Descripcion de la linea */
	public String getSummary() {
		return toString();// TODO
	}

	/**
	 * Impuesto total de las lineas activas
	 * 
	 * @param paid null: total de todas las lineas, true: solo pagadas, false: pendientes de cobro
	 * @return
	 */
	public BigDecimal getTaxAmt(final Boolean paid) {
		BigDecimal taxAmt;
		if (paid == null) {
			taxAmt = super.getTaxAmt();
		} else {
			final StringBuilder sql = new StringBuilder();
			sql.append(" SELECT sum(taxAmt) ");
			sql.append(" FROM DH_PrePaymentLine l ");
			sql.append(" WHERE l.DH_PrePayment_ID=? ");
			sql.append("   AND l.isActive='Y' ");
			sql.append("   AND l.isPaid=? ");

			final List<Object> params = new ArrayList<>();
			params.add(getDH_PrePayment_ID());
			params.add(DB.TO_STRING(paid));

			taxAmt = DB.getSQLValueBD(null, sql.toString(), params);
		}
		return taxAmt == null ? BigDecimal.ZERO : taxAmt;
	}

	/**
	 * Cantidad de lineas activas
	 * 
	 * @param paid null : todos, true: pagados, false: pendientes de pago
	 * @return Cantidad de servicios
	 */
	public int getTotalLines(final Boolean paid) {
		final StringBuilder sql = new StringBuilder();
		sql.append(" SELECT Count(*) ");
		sql.append(" FROM DH_PrePaymentLine l ");
		sql.append(" WHERE l.DH_PrePayment_ID=? ");
		sql.append("   AND l.isActive='Y' ");

		final List<Object> params = new ArrayList<>();
		params.add(getDH_PrePayment_ID());
		if (paid != null) {
			sql.append("   AND l.isPaid=? ");
			params.add(DB.TO_STRING(paid));
		}
		final int total = DB.getSQLValue(null, sql.toString(), params);
		return total;
	}

	/**
	 * Inserta las lineas de detalle en DH_PrePaymentLine
	 * 
	 * @param lineas detalle de pre pago
	 * @param voucher registro del pago en banco, se considera pagado si no es null
	 * @param errors listado de errores
	 */
	private int saveLines(final List<PrePaymentLineBean> lines, final MDHVoucher voucher, final ErrorList errors) {
		BigDecimal grandTotal = BigDecimal.ZERO;

		int paidLines = 0;
		int lineIdx = 1;
		for (final PrePaymentLineBean bean : lines) {
			final MDHPrePaymentLine line = new MDHPrePaymentLine(getCtx(), bean.getId(), get_TrxName());
			PrePaymentLineBean.fillFrom(bean, line);
			if (line.is_new()) {
				line.setDH_PrePayment_ID(getDH_PrePayment_ID());
				line.setTransactionCode(getDH_PrePayment_ID() + "T" + new PasswordHandler(Env.getCtx()).generatePassword(5) + "L" + lineIdx);
			}
			if (voucher != null && voucher.getDH_Voucher_ID() > 0) {
				line.setIsPaid(true);
				line.setDH_Voucher_ID(voucher.getDH_Voucher_ID());
			} else if (BigDecimal.ZERO.compareTo(line.getLineTotalAmt()) == 0) {
				line.setIsPaid(true);
			}
			if (line.save()) {
				bean.setId(line.getDH_PrePaymentLine_ID());
				lineIdx++;
				if (line.isPaid()) {
					paidLines++;
				}
			} else {
				throw new MedsysException();
			}
			grandTotal = grandTotal.add(bean.getTotal());
		}

		if (getGrandTotal().compareTo(grandTotal) != 0) {
			errors.add(Error.VALIDATION_ERROR, Utilerias.getMsg(getCtx(), "El total de las lineas no coincide con el total del encabezado"));// FIXME
		}
		return paidLines;
	}

	/**
	 * Cancela una cotizacion, inactiva las lineas del detalle no pagadas,
	 * si el encabezado tiene al menos una linea pagada, se marca como completado.
	 * Excluye cotizaciones que no tengan docstatus DR / IP
	 * 
	 * @return true si fie exitoso
	 */
	public boolean voidIt() {
		if (StringUtils.indexOfAny(getDocStatus(), new String[] { DOCSTATUS_Drafted, DOCSTATUS_InProgress }) != -1) {
			final List<MDHPrePaymentLine> lines = getLines();
			int count = 0;
			for (final MDHPrePaymentLine line : lines) {
				if (line.isPaid()) {
					count++;
				} else {
					line.setIsActive(false);
					if (!line.save()) {
						processMsg = MedsysException.getMessageFromLogger();
						return false;
					}
				}
			}
			if (count == 0) {
				setDocStatus(DOCSTATUS_Voided);
			} else {
				setDocStatus(DOCSTATUS_Completed);
			}
			setDocAction(DOCACTION_None);
			return true;
		} else {
			processMsg = Utilerias.getMsg(getCtx(), "error.solicitudServ.cantDelete");
			return false;
		}
	}

	/**
	 * Card #1536 Pro Mujer {@link MessageSenderProMujer#sendMessageT02(int)}
	 * 
	 * @param ctx
	 * @param header
	 * @param lines
	 * @return
	 */
	public static boolean sendMessageT02(final Properties ctx, final PrePaymentBean header, final List<PrePaymentLineBean> lines) {
		boolean success = true;
		try {
			// Que el prepago tenga pago pendiente
			if (header.getId() > 0 && header.getGrandTotal().compareTo(BigDecimal.ZERO) > 0) {

				Client mirthClient = MirthClient.getClient(ctx);
				if (mirthClient == null) {
					sLog.log(Level.SEVERE, "sendMessageT01: No MirthClient");
				} else {
					final MessageSenderProMujer msg = new MessageSenderProMujer(mirthClient, Env.getC_Country_ID(ctx));
					for (PrePaymentLineBean bean : lines) {
						// que la linea de prepago este pendiente
						if (bean.getId() > 0 && bean.getTotal().compareTo(BigDecimal.ZERO) > 0) {
							// envia un mensaje por cada linea
							final ResponseProMujer response = msg.sendMessageT02(bean.getId());
							if (response == null) {
								sLog.log(Level.SEVERE, "sendMessageT02: No ResponseProMujer");
							} else {
								success = response.isSuccess();
							}
						}
					}
				}
			}
		} catch (final Exception e) {
			sLog.log(Level.SEVERE, "MDHPatientInfo - sendMessageT01 Fail", e);
		}
		return success;
	}

}
