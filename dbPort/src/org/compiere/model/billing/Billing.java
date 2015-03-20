package org.compiere.model.billing;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.adempiere.exceptions.MedsysException;
import org.compiere.model.MBPBankAccount;
import org.compiere.model.MBPartner;
import org.compiere.model.MBPartnerLocation;
import org.compiere.model.MBankAccount;
import org.compiere.model.MCtaPacDet;
import org.compiere.model.MDocType;
import org.compiere.model.MEXMEClaimPayment;
import org.compiere.model.MEXMEClaimPaymentH;
import org.compiere.model.MEXMECtaPac;
import org.compiere.model.MEXMECtaPacExt;
import org.compiere.model.MEXMELookupInfo;
import org.compiere.model.MEXMEPaciente;
import org.compiere.model.MInvoice;
import org.compiere.model.MInvoiceLine;
import org.compiere.model.MLocation;
import org.compiere.model.MPayment;
import org.compiere.model.Query;
import org.compiere.model.X_C_Invoice;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.OptionItem;
import org.compiere.util.Trx;

/**
 * (Draft)
 * 
 * @author Lorena Lama
 */
public class Billing {

	private Trx trx = null;
	private boolean intTrx = Boolean.TRUE;

	/**
	 * Crea <STRIKE>las 2 facturas</STRIKE> <i>la factura</i> por cada cuenta paciente (aseguradas)<br>
	 * 
	 * Se postea <STRIKE>la primera que representa la venta de los cargos, <br>
	 * no tendrá relacionado ningun pago.</STRIKE> <i>la factura de la aseguradora primaria</i><br>
	 * 
	 * <STRIKE>La segunda factura se queda como CxC<br>
	 * (se genera para la aseguradora 1)</STRIKE>
	 * 
	 * Considera transaccion externa definida para el objeto Billing
	 * 
	 * @param encounters
	 * @return
	 */
	
	public List<MInvoice> processBatchTrx(final Properties ctx, final List<MEXMECtaPacExt> extensiones, 
			String confType, final boolean forze) {
		final List<MInvoice> retValue = new ArrayList<MInvoice>();
		
		try {
			if (trx == null) {
				setTrx(Trx.get(Trx.createTrxName(), true));
			} else {
				intTrx = Boolean.FALSE;
			}
			
			for (MEXMECtaPacExt ext : extensiones) {
				if(forze || ext.getEXME_CtaPac().getFechaAlta()!=null){
					// CAMBIO: se crea una sola factura para la aseguradora/guarantor/paciente
					final MInvoice invoice = createInvoice(ctx, 
					// encounter charges
							ext.getCtaPac().getLstDetalleInv(confType,ext.getEXME_CtaPacExt_ID())
							// patient / guarantor / insurance company ID
							, ext.getCtaPac().getPatientBPartner(confType), confType, getTrx().getTrxName());
					
					// create CxC invoice
					// final MInvoice invoice = createInvoiceCxC(
					// create sales invoice
					// createInvoice(encounter.getLstDetalle(null), false),
					// // patient / guarantor / insurance company ID
					// encounter.getPatientBPartner(),
					// // detail
					// (List<MEXMEClaimPayment>) null );
					if (invoice == null) {
						CLogger.get().severe("Invoice Not saved for ext: " + ext.get_ID());
					} else {
						retValue.add(invoice);
					}
				} else {
					CLogger.get().severe("Invoice Not saved for ext: " + ext.get_ID());
				}
			}
			if (intTrx) {
				Trx.commit(getTrx());
			}
		} catch (Exception e) {
			if (intTrx) {
				Trx.rollback(getTrx());
			}
			CLogger.get().log(Level.SEVERE, e.getMessage(), e);
			return null;
		} finally {
			if (intTrx) {
				Trx.close(getTrx());
				setTrx(null);
			}
		}
		return retValue;
	}
	
	/**
	 * Crea <STRIKE>las 2 facturas</STRIKE> <i>la factura</i> por cada cuenta paciente (aseguradas)<br>
	 * 
	 * Se postea <STRIKE>la primera que representa la venta de los cargos, <br>
	 * no tendrá relacionado ningun pago.</STRIKE> <i>la factura de la aseguradora primaria</i><br>
	 * 
	 * <STRIKE>La segunda factura se queda como CxC<br>
	 * (se genera para la aseguradora 1)</STRIKE>
	 * 
	 * @param encounters
	 * @return
	 */
	public List<MInvoice> processBatch(final Properties ctx, final List<MEXMECtaPacExt> extensiones, 
			String confType, final boolean forze) {
		final List<MInvoice> retValue = new ArrayList<MInvoice>();
		
		try {
			setTrx(Trx.get(Trx.createTrxName(), true));
			for (MEXMECtaPacExt ext : extensiones) {
				if(forze || ext.getEXME_CtaPac().getFechaAlta()!=null){
					// CAMBIO: se crea una sola factura para la aseguradora/guarantor/paciente
					final MInvoice invoice = createInvoice(ctx, 
					// encounter charges
							ext.getCtaPac().getLstDetalleInv(confType,ext.getEXME_CtaPacExt_ID())
							// patient / guarantor / insurance company ID
							, ext.getCtaPac().getPatientBPartner(confType), confType, getTrx().getTrxName());
					
					// create CxC invoice
					// final MInvoice invoice = createInvoiceCxC(
					// create sales invoice
					// createInvoice(encounter.getLstDetalle(null), false),
					// // patient / guarantor / insurance company ID
					// encounter.getPatientBPartner(),
					// // detail
					// (List<MEXMEClaimPayment>) null );
					if (invoice == null) {
						CLogger.get().severe("Invoice Not saved for ext: " + ext.get_ID());
					} else {
						retValue.add(invoice);
					}
				} else {
					CLogger.get().severe("Invoice Not saved for ext: " + ext.get_ID());
				}
			}
			Trx.commit(getTrx());
		} catch (Exception e) {
			Trx.rollback(getTrx());
			CLogger.get().log(Level.SEVERE, e.getMessage(), e);
		} finally {
			Trx.close(getTrx());
			setTrx(null);
		}
		return retValue;
	}

	/**
	 * Crea <STRIKE>las 2 facturas</STRIKE> <i>la factura</i> por cada extension de cuenta paciente (aseguradas)<br>
	 * 
	 * Se postea <STRIKE>la primera que representa la venta de los cargos, <br>
	 * no tendrá relacionado ningun pago.</STRIKE> <i>la factura de la aseguradora primaria</i><br>
	 * 
	 * <STRIKE>La segunda factura se queda como CxC<br>
	 * (se genera para la aseguradora 1)</STRIKE>
	 * 
	 * @param encounters
	 * @return
	 */
	public List<MInvoice> processBatchExt(final Properties ctx, final List<MEXMECtaPacExt> encountersExt, 
			String confType, final boolean forze) {
		final List<MInvoice> retValue = new ArrayList<MInvoice>();
		try {
			setTrx(Trx.get(Trx.createTrxName(), true));
			for (MEXMECtaPacExt encounterExt : encountersExt) {
				if(forze || encounterExt.getEXME_CtaPac().getFechaAlta()!=null){
					// CAMBIO: se crea una sola factura para la aseguradora/guarantor/paciente
					final MInvoice invoice = createInvoice(ctx, 
					// encounter charges
							encounterExt.getLstCargos(null)
							// patient / guarantor / insurance company ID
							, encounterExt.getCtaPac().getPatientBPartner(), confType, this.getTrx().getTrxName());
	
					// create CxC invoice
					// final MInvoice invoice = createInvoiceCxC(
					// create sales invoice
					// createInvoice(encounter.getLstDetalle(null), false),
					// // patient / guarantor / insurance company ID
					// encounter.getPatientBPartner(),
					// // detail
					// (List<MEXMEClaimPayment>) null );
					if (invoice == null) {
						CLogger.get().severe("Invoice Not saved for encounter extension: " + encounterExt.get_ID());
					} else {
						retValue.add(invoice);
					}
				} else {
					CLogger.get().severe("Invoice Not saved for encounter extension: " + encounterExt.get_ID());
				}
			}
			Trx.commit(getTrx());
		} catch (Exception e) {
			Trx.rollback(getTrx());
			CLogger.get().log(Level.SEVERE, e.getMessage(), e);
		} finally {
			Trx.close(getTrx());
			setTrx(null);
		}
		return retValue;
	}
	
	/**
	 * Crea <STRIKE>las 2 facturas</STRIKE> <i>la factura (Statement)</i> de la cuenta paciente<br>
	 * 
	 * Se postea <STRIKE>la primera que representa la venta de los cargos, <br>
	 * no tendrá relacionado ningun pago.</STRIKE> <i>la factura del paciente/guarantor</i><br>
	 * 
	 * <STRIKE>La segunda factura se queda como CxC<br>
	 * (se genera para el guarantor o paciente)</STRIKE>
	 * 
	 * @param charges
	 *            Encounter charges
	 * @param cbPartnerID
	 * @return patient / guarantor company ID
	 */
	public MInvoice process(final Properties ctx, final List<MCtaPacDet> charges, final int cbPartnerID) {
		MInvoice invoice = null;
		try {
			setTrx(Trx.get(Trx.createTrxName(), true));

			// CAMBIO: se crea una sola factura para el paciente/guarantor
			invoice = createInvoice(ctx, charges, cbPartnerID);

			// create sales invoice
			// final MInvoice invoiceFrom = createInvoice(charges, false);
			// create CxC invoice TODO: validar MONTOS por linea y total.
			// invoice = createInvoiceCxC(//
			// invoiceFrom, // reference invoice
			// cbPartnerID,// patient / guarantor company ID
			// invoiceFrom.getGrandTotal()// total amt
			// );

			if (invoice == null) {
				Trx.rollback(getTrx());
			} else {
				invoice.setExtensionInvoiced();
				Trx.commit(getTrx());
			}
		} catch (Exception e) {
			Trx.rollback(getTrx());
			CLogger.get().log(Level.SEVERE, e.getMessage(), e);
		} finally {
			Trx.close(getTrx());
			setTrx(null);
		}
		return invoice;
	}

	/**
	 * Registra los pagos y salda la factura<br>
	 * <br>
	 * Verifica las lineas de la factura (product) contra los servicios de la respuesta (product).<br>
	 * Si no existe el servicio, se agrega la línea y a la cxc y se actualiza el total.<br>
	 * Si hay un cargo de menos se remueve la linea de la factura<br>
	 * <br>
	 * Genera la factura cxc para la siguiente aseguradora o paciente.
	 * 
	 * @param invoice
	 * @return
	 */
	public MInvoice processPayment(final MInvoice invoice) {
		MInvoice retValue = null;
		try {
			setTrx(Trx.get(Trx.createTrxName(), true));

			retValue = start(invoice);
			if (retValue == null) {
				Trx.rollback(getTrx());
			} else {
				Trx.commit(getTrx());
			}
		} catch (Exception e) {
			Trx.rollback(getTrx());
			CLogger.get().log(Level.SEVERE, e.getMessage(), e);
		} finally {
			Trx.close(getTrx());
			setTrx(null);
		}
		return retValue;
	}

	/**
	 * Crea la factura con los cargos de la cuenta paciente.
	 * 
	 * @param charges
	 *            Encounter charges (extension Zero)
	 * @param <STRIKE>isCxC false: postea la factura</STRIKE>
	 * @return
	 */private  MDocType[] doctype= null;
	private MInvoice createInvoice(final Properties ctx, final List<MCtaPacDet> charges, int cbPartnerID/* , final boolean isCxC */) {
		return createInvoice(ctx, charges, cbPartnerID, null, getTrx().getTrxName());
	}
	
	/**
	 * Crea la factura con los cargos de la cuenta paciente.
	 * 
	 * @param charges
	 *            Encounter charges (extension Zero)
	 * @param <STRIKE>isCxC false: postea la factura</STRIKE>
	 * @param confType Claim Configuration Type
	 * @return
	 */
	public MInvoice createInvoice(final Properties ctx, final List<MCtaPacDet> charges, int cbPartnerID, String confType, String trxName) {
		MInvoice invoice = null;
		if (!charges.isEmpty()) {
			final MCtaPacDet charge = charges.get(0);
			invoice = new MInvoice(ctx, 0, trxName);
			// copy encounter data
			MInvoiceLine.copyValues(charge, invoice);
			// CtaPac
			invoice.setEXME_CtaPac_ID(charge.getEXME_CtaPac_ID());
			
			MEXMECtaPac cta = new MEXMECtaPac(ctx, charge.getEXME_CtaPac_ID(), trxName);
			if(cta!=null ){
				MEXMEPaciente pac = new MEXMEPaciente(ctx, cta.getEXME_Paciente_ID(),trxName);
				if(pac!=null){
					invoice.setNombre_Paciente(pac.getNombre_Pac());
				}
			}
			
			// doctype
			if(doctype==null) {// doctype
				doctype = MDocType.getOfDocBaseType(invoice.getCtx(), MDocType.DOCBASETYPE_ARInvoice);
			}
			if (doctype != null && doctype.length > 0) {
				invoice.setC_DocType_ID(doctype[0].get_ID());
			}
			// invoice date (patient discharge)
			invoice.setDateInvoiced(charge.getCtaPac().getFechaAlta());
			// Informacion de bPartner
			final MBPartner company;
			if (cbPartnerID > 0) {
				company = new MBPartner(invoice.getCtx(), cbPartnerID, null);
			} else {
				final MEXMEPaciente paciente = charge.getCtaPac().getPaciente();
				company = paciente.getPatientBPartner();
			}
			
			invoice.setC_BPartner_ID(company.get_ID());
			
			// TODO: revisar direccion
			// buscar la direccion del socio que tenemos si no existe
			if(company.getPrimaryC_BPartner_Location_ID()>0){
				invoice.setC_BPartner_Location_ID(company.getPrimaryC_BPartner_Location_ID());
			} else {
				// Crear una direccion 
				MLocation direccion = new MLocation(invoice.getCtx(), 0, trxName);
				direccion.setC_Country_ID(Env.getC_Country_ID(invoice.getCtx()));
				if(direccion.save(trxName)){
					MBPartnerLocation socioDirect = new MBPartnerLocation(invoice.getCtx(), 0, trxName);
					socioDirect.setName("Address"+company.getName());
					socioDirect.setC_Location_ID(direccion.getC_Location_ID());
					socioDirect.setC_BPartner_ID(company.get_ID());
					if(socioDirect.save(trxName)){
						// Relacionarla al socio 
						invoice.setC_BPartner_Location_ID(socioDirect.getC_BPartner_Location_ID());// TODO: revisar direccion
					}
				}
			}
			
			
//			C_BPARTNER_ID                  NOT NULL NUMBER(10)                                                                                                                                                                                    
//			C_BPARTNER_LOCATION_ID         NOT NULL NUMBER(10)                                                                                                                                                                                    
//			C_CURRENCY_ID                  NOT NULL NUMBER(10)                                                                                                                                                                                    
//			TOTALLINES                     NOT NULL NUMBER                                                                                                                                                                                        
//			GRANDTOTAL                     NOT NULL NUMBER                                                                                                                                                                                        
                                                                                                                                                                                   
			invoice.setConfType(confType);
			invoice.setADOrgTrxID(charge);
			
			if (invoice.save(trxName)) {
				// si el bPartner es de clase Paciente, se crea la factura como Statement (sin detalle)
//				if (MBPartner.BP_CLASS_P.equals(company.getBP_Class())) {
//					BigDecimal total = BigDecimal.ZERO;
//					for (MCtaPacDet cargo : charges) {
//						total = total.add(cargo.getPriceList().multiply(cargo.getQtyEntered()));
//					}
//					invoice.createLine(total);
//				} else {
					// factura CxC para aseguradoras
					invoice.createLines(charges);
				//}
				completeDoc(invoice);
			} else {
				throw new MedsysException("Invoice not saved");
			}
		}
		return invoice;
	}

	/**
	 * Crea la factura cxc a partir de una factura previa, o con las lineas de pago (835).
	 * 
	 * @param invoiceFrom
	 * @param cbPartnerAsegID
	 * @param claimPays
	 * @return
	 */
	@SuppressWarnings("unused")
	private MInvoice createInvoiceCxC(final MInvoice invoiceFrom, final int cbPartnerAsegID,
			final List<MEXMEClaimPayment> claimPays) {
		final MInvoice invoice = createInvoice(invoiceFrom, cbPartnerAsegID);
		if (invoice!=null && invoice.save()) {
			if (claimPays == null || claimPays.isEmpty()) {
				// copia la las mismas lineas de la factura de origen.
				invoice.copyLinesFrom(invoiceFrom, false, false);
			} else {
				// crea la linas segun la respuesta de pago.
				invoice.createLines(claimPays);
			}
			completeDoc(invoice);

		} else {
			return null; //throw new MedsysException("error.configPre");
		}
		return invoice;
	}

	/**
	 * Procesa las lineas de pago, provenientes a la repuest 835.<br>
	 * Compara las lineas de la factura previa contra las lineas de pago, y actualiza la factura<br>
	 * Genera la factura cxc de la siguiente aseguradora (o paciente) con el remanente de lo no pagado, esta se completa
	 * y no debe postearse.<br>
	 * 
	 * @param invoiceFrom
	 * @return
	 *///REVISAR LAS REFERENCIAS
	private MInvoice start(final MInvoice invoiceFrom) {

		// header de la respuesta (para todos los pagos)
		final MEXMEClaimPaymentH header = new MEXMEClaimPaymentH(invoiceFrom.getCtx(), 0, getTrx().getTrxName());
		// id's en comun invoice / ctapac.
	//	header.setC_Invoice_ID(invoiceFrom.get_ID());
	//	header.setEXME_CtaPac_ID(new MEXMECtaPacExt(invoiceFrom.getCtx(), invoiceFrom.getEXME_CtaPacExt_ID(), null)
	//			.getEXME_CtaPac_ID());

		// Match
		compareProducts(header, invoiceFrom);

		// generamos los paiments de la respuesta
		final BigDecimal pendingAmt = createPayments(header, invoiceFrom);

		MInvoice invoice = null;
		// crea la siguiente factura cxc a partir del remanente no pagado.
		if (pendingAmt.compareTo(Env.ZERO) >= 0) {
			// busca la siguiente aseguradora
	/*		final int company = new MEXMECtaPac(invoiceFrom.getCtx(), header.getEXME_CtaPac_ID(), null)
			// a partir aseguradora de la respuesta
					.getSubsequentAseg(invoiceFrom.getC_BPartner_ID());
			if (company > 0) {
				// se genera la nueva factura cxc
				invoice = createInvoiceCxC(invoiceFrom, company,
						header.getClaimPayments(true, MEXMEClaimPayment.COLUMNNAME_M_Product_ID));
				// si no tiene otra aseguradota, se crea el statement del paciente, con el total a pagar.
			} else { // Paciente (total)
				invoice = createInvoiceCxC(invoiceFrom, company, pendingAmt);
			}*/
		}
		
		// TODO: actualizar el estatus de la cuenta paciente (segun documento)
		
		return invoice;

	}

	/**
	 * Crea los pagos y los relaciona a la factura
	 * 
	 * @param header
	 * @param invoiceFrom
	 * @return
	 */
	private BigDecimal createPayments(final MEXMEClaimPaymentH header, final MInvoice invoiceFrom) {
		// detalle de pagos, ordenados por tipo de ajuste.
	/*	final List<MEXMEClaimPayment> claimPayments = header.getClaimPayments(true,
				MEXMEClaimPayment.COLUMNNAME_EXME_AdjustmentType_ID);

		// cantidad total pagada de la factura: total de factura -( ajuste contractual + pagos)
		BigDecimal pendingAmt = invoiceFrom.getGrandTotal();
		// Total por tipo de ajuste
		double total = 0.0;
		for (int i = 0; i < claimPayments.size(); i++) {
			final MEXMEClaimPayment line = claimPayments.get(i);

			// monto total por producto.
			if (line.getC_Charge_ID() > 0) {
				// monto de ajuste (contractual, deducible, coaseguro, etc)
				total = total + line.getAmt_Adjust().doubleValue();
			} else {
				// monto pagado
				total = total + line.getAmt_Paid().doubleValue();
			}
			// calcular monto remanente: total - (pagado + ajuste contractual)
			if (line.getC_Charge_ID() <= 0) {// pagos
				// restar pagos reales
				pendingAmt = pendingAmt.subtract(line.getAmt_Paid());
			} else if (MEXMEAdjustmentType.TYPE_Others.equals(line.getEXME_AdjustmentType().getType())) {
				// restar ajustes contractual
				pendingAmt = pendingAmt.subtract(line.getAmt_Adjust());
			}
			// si es la ultima linea o cambia de tipo de ajuste.
			if (i == claimPayments.size() - 1
					|| line.getC_Charge_ID() != claimPayments.get(i + 1).getC_Charge_ID()) {
				// se relacionan a la factura de la aseguradora
				if (line.getC_Invoice_ID() <= 0) {
					line.setC_Invoice_ID(header.getC_Invoice_ID());
				}
				// los pagos de un mismo tipo de ajuste se agrupan en un solo registro.
				final MPayment payment = createPayment(invoiceFrom.getC_BPartner_ID(), // aseguradora de la respuesta
						line, new BigDecimal(total));
				// completar el registro, crea los allocation de pago vs factura
				completeDoc(payment);
				total = 0.0;// reset del total por tipo de ajuste
			}
			
			// cambiar a procesada las lineas que ya fueron consideradas para creacion de pagos.
			//line.setProcessed(true); FIXME: DESCOMENTAR CUANDO SE AGREGUE MODELO !! (Lama)
			
		}
		return pendingAmt;
		*/
		return Env.ZERO;
	}

	/**
	 * Crea el pago de la factura (cxc)<br>
	 * 
	 * @param cbPartnerAsegID
	 * @param claimPayment
	 * @param payAmt
	 * @return
	 */
	@SuppressWarnings("unused")
	private MPayment createPayment(final int cbPartnerAsegID, final MEXMEClaimPayment claimPayment,
			final BigDecimal payAmt) {
		// Valores por defecto: tenderType = cheque, TrxType=Sales
		final MPayment payment = new MPayment(claimPayment.getCtx(), 0, null);

		payment.setC_BPartner_ID(cbPartnerAsegID);
		// si es NULL indica que es el pago.
		payment.setC_Charge_ID(claimPayment.getC_Charge_ID());
		// se relaciona la factura para que se creen los Allocation cuando se ejecute el completeIt
		payment.setC_Invoice_ID(claimPayment.getC_Invoice_ID());

		// Sender (Insurance)
		payment.setRoutingNo(claimPayment.getSenderRoutingNo());
		payment.setAccountNo(claimPayment.getSenderAccountNo());
		payment.setC_BP_BankAccount_ID(DB.getSQLValue(null, // trx
				"SELECT C_BP_BankAccount_ID FROM C_BP_BankAccount WHERE C_BPartner_ID=? AND RoutingNo=? AND AccountNo=?"
						+ MEXMELookupInfo.addAccessLevelSQL(payment.getCtx(), " ", MBPBankAccount.Table_Name),
				// parametros
				cbPartnerAsegID, claimPayment.getSenderRoutingNo(), claimPayment.getSenderAccountNo()));

		// Receiver (Hospital)
		int bankAcctID = DB.getSQLValue(
				null, // trx
				"SELECT C_BankAccount_ID FROM C_BankAccount WHERE AccountNo=?"
						+ MEXMELookupInfo.addAccessLevelSQL(payment.getCtx(), " ", MBankAccount.Table_Name),
				// parametros
				claimPayment.getReceiverAccountNo());
		if (bankAcctID <= 0) {
			// busca la primer cuenta bancaria segun el contexto
			final List<OptionItem> list = MBankAccount.getBankAccount(payment.getCtx());
			if (!list.isEmpty()) {
				bankAcctID = Integer.valueOf(list.get(0).getId());
			}
		}
		// TODO: la cuenta bancaria de los Pagos debe ser diferente a la de los Ajustes (contractual, deducible,
		// coaseguro, etc)
		// Pendiente definir si sera una cuenta **Dummy** o volver la columna no obligatoria y dejarla NULL. (GC
		// 20110913)
		payment.setC_BankAccount_ID(bankAcctID);
		payment.setAmount(0, payAmt == null ? Env.ZERO : payAmt);

		if (!payment.save(getTrx().getTrxName())) {
			throw new MedsysException("Payment not saved");
		}
		return payment;
	}

	public Trx getTrx() {
		return trx;
	}

	public void setTrx(final Trx trx) {
		this.trx = trx;
	}

	/**
	 * Crea la factura (cxc) "Statement" del paciente.<br>
	 * Una sola línea con el monto pendiente de pagar.
	 * 
	 * @param invoiceFrom
	 *            - factura anterior de ultima aseguradora
	 * @param cbPartnerID
	 *            - compañia a la que se relacionara la factura cxc
	 * @param total
	 *            - monto total a pagar responsabilidad del paciente
	 * @return
	 */
	@SuppressWarnings("unused")
	private MInvoice createInvoiceCxC(final MInvoice invoiceFrom, final int cbPartnerID, final BigDecimal total) {
		final MInvoice invoice = createInvoice(invoiceFrom, cbPartnerID);

		if (invoice.save()) {
			// crea una sola linea por el total
			invoice.createLine(total);
			completeDoc(invoice);
			invoice.setExtensionInvoiced(); // ctapacext (invoiced='Y')
		} else {
			throw new MedsysException("Invoice not Saved");
		}
		return invoice;
	}

	/**
	 * Utilerias: draft de una factura copia.
	 * 
	 * @param invoiceFrom
	 * @param cbPartnerAsegID
	 * @return
	 */
	private MInvoice createInvoice(final MInvoice invoiceFrom, final int cbPartnerAsegID) {
		if (invoiceFrom == null || invoiceFrom.get_ID() <= 0) {
			return null; //throw new MedsysException("error.configPre");
		}
		final MInvoice invoice = new MInvoice(invoiceFrom.getCtx(), 0, invoiceFrom.get_TrxName());
		MInvoice.copyValues(invoiceFrom, invoice);
		// Id de la factura origen
		invoice.setRef_Invoice_ID(invoiceFrom.get_ID());

		// Incializa la factura
		invoice.setDocStatus(MInvoice.DOCSTATUS_Drafted);
		invoice.setDocAction(MInvoice.DOCACTION_Complete);
		invoice.setProcessed(false);
		invoice.setPosted(false);
		invoice.setIsPaid(false);

		final MBPartner company;
		if (cbPartnerAsegID > 0) {
			company = new MBPartner(invoice.getCtx(), cbPartnerAsegID, null);
			// TODO: direccion del paciente/guarantor/insurance etc. ?
			invoice.setC_BPartner_ID(company.get_ID());
			invoice.setC_BPartner_Location_ID(company.getPrimaryC_BPartner_Location_ID());
		} // else: deja la informacion del bPartner de la factura origen.

		return invoice;
	}

	/**
	 * Manda llamar el metodo completeIt y closeIt del objeto tipo DocAction
	 * 
	 * @param docAction
	 * @param <STRIKE>post - si es false cambia la columna Processed a 'N'.</STRIKE>
	 */
	private void completeDoc(final MInvoice docAction) {

		final String docStatus = docAction.completeIt();
		if (!MInvoice.DOCSTATUS_Invalid.equals(docStatus)) {
			docAction.setDocStatus(X_C_Invoice.DOCSTATUS_Completed);
			docAction.setDocAction(X_C_Invoice.DOCSTATUS_Closed);
			// closeIt
			if (!docAction.closeIt()) {
				throw new MedsysException("Not closeit");
			}
		
			if (!docAction.save()) {
				throw new MedsysException("Not completed");
			}
		} else {
			throw new MedsysException("Not completed");
		}
	}

	/**
	 * Compara las lineas de la factura contra las lineas de pago de la respuesta 835<br>
	 * elimina los productos demás y crea las líneas adicionales.<br>
	 * (modifica el monto y la cantidad si así lo requiere)<br>
	 * 
	 * <br>
	 * <b>No</b> se afecta EXME_CtaPacDet (GC 20110914)<br>
	 * 
	 * @param header
	 * @param invoiceFrom
	 */
	public void compareProducts(final MEXMEClaimPaymentH header, final MInvoice invoiceFrom) {
		// ** IMPORTANTE ** Las lineas, deben venir agrupados por producto, con las cantidades y montos (facturado) sumarizados !!
		final List<MEXMEClaimPayment> lineas = header.getClaimPayments(true, "M_Product_ID,NVL(Amt_Paid,0) DESC");

		// TODO:
		// De no tener productos la respuesta 835, se crearia solo una linea con el monto a pagar, sin Producto.
		// Pendiente por hacer, ya que M_Product_ID es obligatorio para EXME_ClaimPayment.

		// lineas de la factura ordenadas por producto
		final List<MInvoiceLine> invoicelines = new Query(invoiceFrom.getCtx(), MInvoiceLine.Table_Name,
				"C_Invoice_ID=?", null)//
				.setOnlyActiveRecords(true)//
				.setOrderBy(MInvoiceLine.COLUMNNAME_M_Product_ID)//
				.setParameters(invoiceFrom.get_ID())//
				.list();

		for (MInvoiceLine invoiceline : invoicelines) {

			// el producto se encuentra en la respuesta.
			boolean included = false;
			for (int j = 0; j < lineas.size(); j++) {
				final MEXMEClaimPayment claimline = lineas.get(j);
				// si coincide el mismo productId
				included = invoiceline.getM_Product_ID() == claimline.getM_Product_ID();
				if (included) {
					// si la cantidad (agrupada) del claim es mayor a la facturada
					if (claimline.getQtyInvoiced().compareTo(invoiceline.getQtyInvoiced()) > 0) {
						// se resta la cantidad de linea y el monto facturado (total para ese producto)
						claimline.setQtyInvoiced(claimline.getQtyInvoiced().subtract(invoiceline.getQtyInvoiced()));
						claimline.setAmt_Billed(claimline.getAmt_Billed().subtract(invoiceline.getLineTotalAmt()));
						// continua con la siguiente linea de factura
					} else
					// si la cantidad es menor o igual
					if (claimline.getQtyInvoiced().compareTo(invoiceline.getQtyInvoiced()) <= 0) {
						// se asigna la misma cantidad del claim
						invoiceline.setQtyInvoiced(claimline.getQtyInvoiced());
						// si el precio es diferente, se asigna el mismo que el claim.
						if (claimline.getAmt_Billed().doubleValue() != invoiceline.getLineTotalAmt().doubleValue()) {
							invoiceline.setLineTotalAmt(claimline.getAmt_Billed());
						}
						if (!invoiceline.save(trx.getTrxName())) {
							throw new MedsysException("Invoice Line Not saved");
						}
						lineas.remove(j); // ya no se necesita.
						--j;
					}
					// solo es un registro por cada producto, cambia a la siguiente linea de factura
					break;//Importante: ya que se repite el producto por 
				}
			}
			// si no se encontro en el claim, se elimina la linea de la factura.
			if (!included) {
				if (!invoiceline.delete(true, trx.getTrxName())) {
					throw new MedsysException("Invoice Line Not Deleted");
				}
			}
		}
		// las lineas nuevas se agregan.
		invoiceFrom.createLines(lineas);
		invoiceFrom.setPosted(false);
		invoiceFrom.setDocStatus(MInvoice.DOCSTATUS_Drafted);
		invoiceFrom.setDocAction(MInvoice.DOCACTION_Complete);
		completeDoc(invoiceFrom);
	}
	
	/**
	 * Metodo de prueba de processBatch
	 * <No utilizar como parte de algun proceso>
	 * @param test
	 * @param encounters
	 * @return
	 */
	public static List<MInvoice> processBatchTest(final Properties ctx, boolean test, final List<MEXMECtaPac> encounters) {
		final List<MInvoice> retValue = new ArrayList<MInvoice>();
		Trx trx = Trx.get(Trx.createTrxName(), true);
		try {
			Billing bill = new Billing();
			bill.setTrx(Trx.get(Trx.createTrxName(), true));
			for (MEXMECtaPac encounter : encounters) {
				if(encounter.getFechaAlta()!=null){
					
					final MInvoice invoice = bill.createInvoice(ctx, 
							// encounter charges
									encounter.getLstDetalle(null)
									// patient / guarantor / insurance company ID
									, encounter.getPatientBPartner());
			
					// create CxC invoice
					/*final MInvoice invoice = bill.createInvoiceCxC(
							// create sales invoice
							bill.createInvoice(encounter.getLstDetalle(null), encounter.getPatientBPartner()/*, false*/
					/*),
							// patient / guarantor / insurance company ID
							encounter.getPatientBPartner(),
							// detail
							(List<MEXMEClaimPayment>) null);
							*/
					if (invoice == null) {
						CLogger.get().severe("Invoice Not saved for encounter: " + encounter.get_ID());
					} else {
						retValue.add(invoice);

						System.out.println("ID > :" +
								invoice.getC_Invoice_ID() );
					}
				} else {
					CLogger.get().severe("Invoice Not saved for encounter: " + encounter.get_ID());
				}
			}

			//trx.commit(true);
		} catch (Exception e) {
			trx.rollback();
		} finally {
			trx.rollback();
			Trx.close(trx);
		}
		return retValue;
	}
}
