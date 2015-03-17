package org.compiere.model;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.TimeUtil;

import com.ecaresoft.acct.xml.CMoneda;
import com.ecaresoft.acct.xml.auxcuentas.AuxiliarCtas.Cuenta.DetalleAux;
import com.ecaresoft.acct.xml.auxiliarfolios.RepAuxFol;
import com.ecaresoft.acct.xml.auxiliarfolios.RepAuxFol.DetAuxFol;
import com.ecaresoft.acct.xml.auxiliarfolios.RepAuxFol.DetAuxFol.ComprNal;
import com.ecaresoft.acct.xml.polizas.Polizas;
import com.ecaresoft.acct.xml.polizas.Polizas.Poliza;
import com.ecaresoft.acct.xml.polizas.Polizas.Poliza.Transaccion;
import com.ecaresoft.acct.xml.polizas.Polizas.Poliza.Transaccion.Cheque;
import com.ecaresoft.acct.xml.polizas.Polizas.Poliza.Transaccion.CompNal;
import com.ecaresoft.acct.xml.polizas.Polizas.Poliza.Transaccion.Transferencia;
import com.sun.rowset.CachedRowSetImpl;

/**
 * @author odelarosa
 * 
 */
public class MEXMEPolicyInfo extends X_EXME_PolicyInfo {

	/**
	 * Clase dummy para xml de contabilidad
	 * 
	 * @author odelarosa
	 *
	 */
	public static class Dummy {
		private BigDecimal amount;
		private String auxType;
		private String benef;
		private BigDecimal currencyRate;
		private String curreny;
		private Timestamp dateTrx;
		private String dBank;
		private String dBankAccount;
		private boolean dBankNational;
		private String docNo;
		private String eBank;
		private String eBankAccount;
		private boolean eBankNational;
		private String taxId;
		private String uuid;

		public BigDecimal getAmount() {
			return amount;
		}

		public String getAuxType() {
			return auxType;
		}

		public String getBenef() {
			return benef;
		}

		public BigDecimal getCurrencyRate() {
			return currencyRate;
		}

		public String getCurreny() {
			return curreny;
		}

		public Timestamp getDateTrx() {
			return dateTrx;
		}

		public String getdBank() {
			return dBank;
		}

		public String getdBankAccount() {
			return dBankAccount;
		}

		public String getDocNo() {
			return docNo;
		}

		public String geteBank() {
			return eBank;
		}

		public String geteBankAccount() {
			return eBankAccount;
		}

		public String getTaxId() {
			return taxId;
		}

		public String getUuid() {
			return uuid;
		}

		public boolean isdBankNational() {
			return dBankNational;
		}

		public boolean iseBankNational() {
			return eBankNational;
		}

		public void setAmount(BigDecimal amount) {
			this.amount = amount;
		}

		public void setAuxType(String auxType) {
			this.auxType = auxType;
		}

		public void setBenef(String benef) {
			this.benef = benef;
		}

		public void setCurrencyRate(BigDecimal currencyRate) {
			this.currencyRate = currencyRate;
		}

		public void setCurreny(String curreny) {
			this.curreny = curreny;
		}

		public void setDateTrx(Timestamp dateTrx) {
			this.dateTrx = dateTrx;
		}

		public void setdBank(String dBank) {
			this.dBank = dBank;
		}

		public void setdBankAccount(String dBankAccount) {
			this.dBankAccount = dBankAccount;
		}

		public void setdBankNational(boolean dBankNational) {
			this.dBankNational = dBankNational;
		}

		public void setDocNo(String docNo) {
			this.docNo = docNo;
		}

		public void seteBank(String eBank) {
			this.eBank = eBank;
		}

		public void seteBankAccount(String eBankAccount) {
			this.eBankAccount = eBankAccount;
		}

		public void seteBankNational(boolean eBankNational) {
			this.eBankNational = eBankNational;
		}

		public void setTaxId(String taxId) {
			this.taxId = taxId;
		}

		public void setUuid(String uuid) {
			this.uuid = uuid;
		}

	}

	private static CLogger s_log = CLogger.getCLogger(MEXMEPolicyInfo.class);

	private static final long serialVersionUID = -8624153712008447897L;

	public static MEXMEPolicyInfo get(Properties ctx, int tableId, int recordId, int orgId, String trxName) {
		MEXMEPolicyInfo policyInfo;

		List<Object> params = new ArrayList<>();

		params.add(tableId);
		params.add(recordId);

		StringBuilder sql = new StringBuilder();
		sql.append("  ad_table_id = ? AND ");
		sql.append("  record_id = ? ");

		if (orgId > 0) {
			sql.append(" AND ad_org_id = ? ");
			params.add(orgId);
		}

		Query query = new Query(ctx, Table_Name, sql.toString(), trxName);

		if (orgId <= 0) {
			query.addAccessLevelSQL(true);
		}

		query.setParameters(params);

		policyInfo = query.first();

		return policyInfo;
	}

	/**
	 * Detalles de auxiliar de cuenta
	 * 
	 * @param ctx
	 *            Contexto
	 * @param periodId
	 *            Periodo
	 * @param accountId
	 *            Cuenta Contable
	 * @param trxName
	 *            Trx
	 * @return Listado de auxiliares
	 */
	public static List<DetalleAux> getDetalleAux(Properties ctx, int periodId, int accountId, String trxName) {
		List<DetalleAux> list = new ArrayList<DetalleAux>();

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ");
		sql.append("  facct.ad_table_id, ");
		sql.append("  facct.record_id, ");
		sql.append("  facct.dateacct, ");
		sql.append("  pi.PolicyType || pi.policyno as policyno, ");
		sql.append("  facct.description, ");
		sql.append("  facct.amtacctdr, ");
		sql.append("  facct.amtacctcr ");
		sql.append("FROM ");
		sql.append("  fact_acct facct ");
		sql.append("  INNER JOIN exme_policyinfo pi ");
		sql.append("  ON pi.ad_table_id = facct.ad_table_id AND ");
		sql.append("  pi.record_id = facct.record_id ");
		sql.append("WHERE ");
		sql.append("  facct.c_period_id = ? AND ");
		sql.append("  facct.account_id = ? ");
		sql.append(" order by facct.ad_table_id, facct.record_id ");

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, periodId);
			pstmt.setInt(2, accountId);

			rs = pstmt.executeQuery();
			
			int tableId = -1;
			int recordId = -1;

			DetalleAux detalleAux = null;

			while (rs.next()) {
				int thisTableId = rs.getInt("AD_Table_ID");
				int thisRecordId = rs.getInt("Record_ID");

				if (!(thisTableId == tableId && thisRecordId == recordId)) {
					detalleAux = new DetalleAux();
					detalleAux.setConcepto(StringUtils.abbreviate(rs.getString("description"), 200));
					detalleAux.setFecha(TimeUtil.toXMLGregorianCalendar(rs.getTimestamp("dateacct")));
					detalleAux.setNumUnIdenPol(rs.getString("policyno"));
					
					list.add(detalleAux);

					tableId = thisTableId;
					recordId = thisRecordId;
				}	
				
				if (detalleAux.getDebe() == null) {
					detalleAux.setDebe(BigDecimal.ZERO);
				}
				if (detalleAux.getHaber() == null) {
					detalleAux.setHaber(BigDecimal.ZERO);
				}
				
				detalleAux.setDebe(detalleAux.getDebe().add(rs.getBigDecimal("amtacctdr")));
				detalleAux.setHaber(detalleAux.getHaber().add(rs.getBigDecimal("amtacctcr")));
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, null, e);
		} finally {
			DB.close(rs, pstmt);
		}

		return list;
	}

	/**
	 * Búsqueda de pólizas de factura
	 * 
	 * @param ctx
	 *            Contexto
	 * @param docNo
	 *            Documento de factura
	 * @param date
	 *            Fecha inicial
	 * @param date2
	 *            Fecha final
	 * @param trxName
	 *            Trx name
	 * @return Listado de pólizas
	 */
	public static List<MEXMEPolicyInfo> getFromInvoice(Properties ctx, String docNo, Timestamp date, Timestamp date2, String trxName) {
		List<Object> params = new ArrayList<>();

		StringBuilder where = new StringBuilder();
		StringBuilder joins = new StringBuilder();

		joins.append(" inner join c_invoice i on record_id = i.c_invoice_id and ad_table_id = ? ");
		params.add(I_C_Invoice.Table_ID);

		if (StringUtils.isNotBlank(docNo)) {
			where.append("  i.documentNo = ? ");
			params.add(docNo);
		} else {
			where.append(" 1 = 1 ");

			if (date != null && date2 != null) {
				where.append("  AND i.dateinvoiced BETWEEN ? AND ? ");
				params.add(date);
				params.add(date2);
			}
		}

		Query query = new Query(ctx, Table_Name, where.toString(), trxName);
		query.setJoins(joins);
		query.addAccessLevelSQL(true);
		query.setParameters(params);
		query.setOrderBy(" order by datetrx desc ");

		return query.list();
	}

	/**
	 * Búsqueda de pólizas de GL_Journal
	 * 
	 * @param ctx
	 *            Contexto
	 * @param docNo
	 *            Documento de la poliza
	 * @param date
	 *            Fecha inicial
	 * @param date2
	 *            Fecha final
	 * @param trxName
	 *            Trx Name
	 * @return Listado de pólizas
	 */
	public static List<MEXMEPolicyInfo> getFromJournal(Properties ctx, String docNo, Timestamp date, Timestamp date2, String trxName) {
		List<Object> params = new ArrayList<>();

		StringBuilder where = new StringBuilder();
		StringBuilder joins = new StringBuilder();

		joins.append(" inner join gl_journal j on record_id = j.gl_journal_id and ad_table_id = ? ");
		params.add(I_GL_Journal.Table_ID);

		if (StringUtils.isNotBlank(docNo)) {
			where.append("  j.documentNo = ? ");
			params.add(docNo);
		} else {
			where.append(" 1 = 1 ");

			if (date != null && date2 != null) {
				where.append("  AND j.dateacct BETWEEN ? AND ? ");
				params.add(date);
				params.add(date2);
			}
		}

		Query query = new Query(ctx, Table_Name, where.toString(), trxName);
		query.setJoins(joins);
		query.addAccessLevelSQL(true);
		query.setParameters(params);
		query.setOrderBy(" order by datetrx desc ");

		return query.list();
	}

	/**
	 * Búsqueda de pólizas de pagos
	 * 
	 * @param ctx
	 *            Contexto
	 * @param docNo
	 *            Documento del pago
	 * @param date
	 *            Fecha inicial
	 * @param date2
	 *            Fecha final
	 * @param trxName
	 *            Trx Name
	 * @return Listado de pólizas
	 */
	public static List<MEXMEPolicyInfo> getFromPayment(Properties ctx, String docNo, Timestamp date, Timestamp date2, String trxName) {
		List<Object> params = new ArrayList<>();

		StringBuilder where = new StringBuilder();
		StringBuilder joins = new StringBuilder();

		joins.append(" inner join c_payment p on record_id = p.c_payment_id and ad_table_id = ? ");
		params.add(I_C_Payment.Table_ID);

		if (StringUtils.isNotBlank(docNo)) {
			where.append("  p.documentNo = ? ");
			params.add(docNo);
		} else {
			where.append(" 1 = 1 ");

			if (date != null && date2 != null) {
				where.append("  AND p.DateAcct BETWEEN ? AND ? ");
				params.add(date);
				params.add(date2);
			}
		}

		Query query = new Query(ctx, Table_Name, where.toString(), trxName);
		query.setJoins(joins);
		query.addAccessLevelSQL(true);
		query.setParameters(params);
		query.setOrderBy(" order by EXME_PolicyInfo.datetrx desc ");

		return query.list();
	}

	/**
	 * Búsqueda de pólizas
	 * 
	 * @param ctx
	 *            Contexto
	 * @param policyNo
	 *            Número de póliza
	 * @param date
	 *            Fecha inicial
	 * @param date2
	 *            Fecha final
	 * @param policyType
	 *            Tipo de póliza
	 * @param trxName
	 *            Trx
	 * @return Listado de pólizas
	 */
	public static List<MEXMEPolicyInfo> getInfo(Properties ctx, int policyNo, Timestamp date, Timestamp date2, String policyType, String trxName) {
		List<Object> params = new ArrayList<>();

		StringBuilder sql = new StringBuilder();

		if (policyNo > 0) {
			sql.append("  policyNo = ? ");
			params.add(policyNo);
		} else {
			sql.append(" 1 = 1 ");

			if (date != null && date2 != null) {
				sql.append("  AND datetrx BETWEEN ? AND ? ");
				params.add(date);
				params.add(date2);
			}

			if (StringUtils.isNotBlank(policyType)) {
				sql.append("  AND policyType = ? ");
				params.add(policyType);
			}
		}

		Query query = new Query(ctx, Table_Name, sql.toString(), trxName);
		query.addAccessLevelSQL(true);
		query.setParameters(params);
		query.setOrderBy(" order by datetrx desc ");

		return query.list();
	}

	/**
	 * Obtiene las pólizas de un periodo
	 * 
	 * @param ctx
	 *            Contexto
	 * @param periodId
	 *            Periodo
	 * @param trxName
	 *            Trx
	 * @return Polizas
	 */
	public static Polizas getXmlData(Properties ctx, int periodId, String trxName) {
		Polizas polizas = new Polizas();

		MPeriod mPeriod = new MPeriod(Env.getCtx(), periodId, null);
		MOrg mOrg = new MOrg(ctx, Env.getAD_Org_ID(ctx), null);
		MOrgInfo mOrgInfo = mOrg.getInfo();

		polizas.setAnio(Integer.valueOf(mPeriod.getC_Year().getYear()));
		polizas.setMes(StringUtils.leftPad(Integer.toString(mPeriod.getPeriodNo()), 2, '0'));
		// polizas.setTipoSolicitud("AF");

		polizas.setRFC(mOrgInfo.getTaxID());

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ");
		sql.append("  pi.PolicyNo, ");
		sql.append("  pi.DateTrx, ");
		sql.append("  pi.AD_Table_ID, ");
		sql.append("  pi.Record_ID, ");
		sql.append("  pi.policytype, ");
		sql.append("  pi.UUID, ");
		sql.append("  pi.Amount, ");
		sql.append("  pi.CurrencyRate, ");
		sql.append("  pi.AuxType, ");
		sql.append("  pi.docNo, ");
		sql.append("  pi.c_bpartner_id as partnerId, ");
		sql.append("  acct.line_id, ");
		sql.append("  acct.Description, ");
		sql.append("  acct.AmtAcctDr, ");
		sql.append("  acct.AmtAcctCr, ");
		sql.append("  p2.taxId as taxId2, ");
		sql.append("  c.iso_code, ");
		sql.append("  p.taxid, ");
		sql.append("  codGroup.value ");
		sql.append("  AS CodGroup, ");
		sql.append("  ce.name ");
		sql.append("  AS ctaName, ");
		sql.append("  ce.value ");
		sql.append("  AS ctaValue, ");
		sql.append("  pi.name as benef, ");
		sql.append("  be.name as be ,");
		sql.append("  bd.name as bd ,");
		sql.append("  bea.accountno ");
		sql.append("  AS bea, ");
		sql.append("  be.isnational ");
		sql.append("  AS ben, ");
		sql.append("  bda.accountno ");
		sql.append("  AS bda, ");
		sql.append("  bd.isnational ");
		sql.append("  AS bdn ");
		sql.append("FROM ");
		sql.append("  exme_policyinfo pi ");
		sql.append("  INNER JOIN fact_acct acct ");
		sql.append("  ON (pi.ad_table_id = acct.ad_table_id AND ");
		sql.append("  pi.record_id = acct.record_id) ");
		sql.append("  INNER JOIN C_ElementValue ce ");
		sql.append("  ON acct.account_id = ce.c_elementvalue_id ");
		sql.append("  INNER JOIN EXME_GroupAcct codGroup");
		sql.append("  ON ce.exme_groupacct_id = codGroup.exme_groupacct_id ");
		sql.append("  INNER JOIN c_currency c ");
		sql.append("  ON acct.c_currency_id = c.c_currency_id ");
		sql.append("  LEFT JOIN c_bpartner p ");
		sql.append("  ON acct.c_bpartner_id = p.c_bpartner_id ");
		sql.append("  LEFT JOIN c_bpartner p2 ");
		sql.append("  ON pi.c_bpartner_id = p2.c_bpartner_id left ");
		sql.append("  JOIN c_bankaccount bea ");
		sql.append("  ON pi.c_bankaccount_id = bea.c_bankaccount_id left ");
		sql.append("  JOIN c_bank be ");
		sql.append("  ON bea.c_bank_id = be.c_bank_id left ");
		sql.append("  JOIN c_bankaccount bda ");
		sql.append("  ON pi.c_bankaccountdest_id = bda.c_bankaccount_id left ");
		sql.append("  JOIN c_bank bd ");
		sql.append("  ON bda.c_bank_id = bd.c_bank_id ");
		sql.append("WHERE ");
		sql.append("  acct.c_period_id = ? AND ");
		sql.append("  pi.isActive = ");
		sql.append("'Y' ORDER BY ");
		sql.append("  acct.datetrx, ");
		sql.append("  pi.AD_Table_ID, ");
		sql.append("  pi.Record_ID, ");
		sql.append("  acct.created ");

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		CachedRowSetImpl crs = null;

		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, periodId);
			rs = pstmt.executeQuery();

			crs = new CachedRowSetImpl();
			crs.populate(rs);

			DB.close(rs, pstmt);

			int tableId = -1;
			int recordId = -1;

			Poliza poliza = null;

			while (crs.next()) {
				int thisTableId = crs.getInt("AD_Table_ID");
				int thisRecordId = crs.getInt("Record_ID");

				if (!(thisTableId == tableId && thisRecordId == recordId)) {
					poliza = new Poliza();
					polizas.getPoliza().add(poliza);

					tableId = thisTableId;
					recordId = thisRecordId;

					String policyNo = crs.getString("PolicyNo");
					Timestamp dateTrx = crs.getTimestamp("DateTrx");

					poliza.setNumUnIdenPol(crs.getString("policytype") + "-" + policyNo);
					poliza.setFecha(TimeUtil.toXMLGregorianCalendar(dateTrx));
					poliza.setConcepto(StringEscapeUtils.escapeXml(MFactAcct.getConcept(ctx, tableId, recordId, null)));
				}

				String taxId = crs.getString("taxId");

				if (StringUtils.isBlank(taxId)) {
					taxId = crs.getString("taxId2");
				}

				Transaccion transaccion = new Transaccion();
				transaccion.setConcepto(StringEscapeUtils.escapeXml(crs.getString("Description")));
				transaccion.setDebe(crs.getBigDecimal("AmtAcctDr"));
				transaccion.setHaber(crs.getBigDecimal("AmtAcctCr"));
				transaccion.setNumCta(crs.getString("ctaValue"));
				transaccion.setDesCta(StringEscapeUtils.escapeXml(crs.getString("ctaName")));

				poliza.getTransaccion().add(transaccion);

				// Si es GL_Journal, los documentos están por línea, no por
				// encabezado
				if (I_GL_Journal.Table_ID == tableId) {
					int lineId = crs.getInt("line_id");

					Dummy dummy = getXmlDummys(ctx, I_GL_JournalLine.Table_ID, lineId, null);

					if (dummy != null) {
						if (AUXTYPE_Invoice.equals(dummy.getAuxType())) {
							CompNal compNal = new CompNal();
							compNal.setMoneda(CMoneda.valueOf(dummy.getCurreny()));
							compNal.setMontoTotal(dummy.getAmount());
							compNal.setRFC(dummy.getTaxId());
							compNal.setTipCamb(dummy.getCurrencyRate());
							compNal.setUUIDCFDI(dummy.getUuid());

							transaccion.getCompNal().add(compNal);
						} else if (AUXTYPE_Check.equals(dummy.getAuxType())) {
							// if (StringUtils.isNotBlank(dummy.geteBank())) {
							Cheque cheque = new Cheque();

							if (dummy.iseBankNational()) {
								cheque.setBanEmisNal(StringEscapeUtils.escapeXml(dummy.geteBank()));
								cheque.setCtaOri(dummy.geteBankAccount());
							} else {
								cheque.setBanEmisExt(StringEscapeUtils.escapeXml(dummy.geteBank()));
								cheque.setCtaOri(dummy.geteBankAccount());
							}

							cheque.setNum(dummy.getDocNo());
							cheque.setBenef(StringEscapeUtils.escapeXml(dummy.getBenef()));
							cheque.setFecha(TimeUtil.toXMLGregorianCalendar(dummy.getDateTrx()));
							cheque.setMoneda(CMoneda.valueOf(dummy.getCurreny()));
							cheque.setMonto(dummy.getAmount());
							cheque.setTipCamb(dummy.getCurrencyRate());
							cheque.setRFC(dummy.getTaxId());

							transaccion.getCheque().add(cheque);
							// }
						} else if (AUXTYPE_Transfer.equals(dummy.getAuxType())) {
							// if (StringUtils.isNotBlank(dummy.geteBank())) {
							Transferencia transferencia = new Transferencia();

							if (dummy.isdBankNational()) {
								transferencia.setBancoDestNal(dummy.getdBank());
							} else {
								transferencia.setBancoDestExt(dummy.getdBank());
							}

							if (dummy.iseBankNational()) {
								transferencia.setBancoOriNal(dummy.geteBank());
							} else {
								transferencia.setBancoOriExt(dummy.geteBank());
							}

							transferencia.setCtaDest(dummy.getdBankAccount());
							transferencia.setCtaOri(dummy.geteBankAccount());
							transferencia.setFecha(TimeUtil.toXMLGregorianCalendar(dummy.getDateTrx()));
							transferencia.setMoneda(CMoneda.valueOf(dummy.getCurreny()));
							transferencia.setMonto(dummy.getAmount());
							transferencia.setTipCamb(dummy.getCurrencyRate());
							transferencia.setRFC(dummy.getTaxId());

							transferencia.setBenef(dummy.getBenef());

							transaccion.getTransferencia().add(transferencia);
							// }
						}
					}
				} else {
					// Si no es GL_Journal, se revisa el tipo de documento
					String auxType = crs.getString("auxType");

					if (AUXTYPE_Invoice.equals(auxType)) {
						String uuid = crs.getString("UUID");
						if (StringUtils.isNotBlank(uuid)) {
							if (poliza.getTransaccion().isEmpty() || poliza.getTransaccion().get(0).getCompNal().isEmpty()) {
								CompNal compNal = new CompNal();
								compNal.setMoneda(CMoneda.valueOf(crs.getString("iso_code")));
								compNal.setMontoTotal(crs.getBigDecimal("Amount"));
								compNal.setRFC(taxId);
								compNal.setTipCamb(crs.getBigDecimal("CurrencyRate"));
								compNal.setUUIDCFDI(uuid);

								transaccion.getCompNal().add(compNal);
							}
						}
					} else if (AUXTYPE_Check.equals(auxType)) {
						// if (StringUtils.isNotBlank(crs.getString("be"))) {
						// Los cheques solo son 1 por encabezado
						if (poliza.getTransaccion().isEmpty() || poliza.getTransaccion().get(0).getCheque().isEmpty()) {
							Cheque cheque = new Cheque();

							if ("Y".equals(crs.getString("bdn"))) {
								cheque.setBanEmisNal(StringEscapeUtils.escapeXml(crs.getString("be")));
								cheque.setCtaOri(crs.getString("bea"));
							} else {
								cheque.setBanEmisExt(StringEscapeUtils.escapeXml(crs.getString("be")));
								cheque.setCtaOri(crs.getString("bea"));
							}

							cheque.setNum(crs.getString("docNo"));
							cheque.setBenef(StringEscapeUtils.escapeXml(crs.getString("benef")));
							cheque.setFecha(TimeUtil.toXMLGregorianCalendar(crs.getTimestamp("dateTrx")));
							cheque.setMoneda(CMoneda.valueOf(crs.getString("iso_code")));
							cheque.setMonto(crs.getBigDecimal("amount"));
							cheque.setTipCamb(crs.getBigDecimal("currencyRate"));
							cheque.setRFC(taxId);

							transaccion.getCheque().add(cheque);
						}
						// }
					} else if (AUXTYPE_Transfer.equals(auxType)) {
						// if (StringUtils.isNotBlank(crs.getString("be"))) {
						// Las transferencias son 1 por encabezado
						if (poliza.getTransaccion().isEmpty() || poliza.getTransaccion().get(0).getTransferencia().isEmpty()) {
							Transferencia transferencia = new Transferencia();

							if ("Y".equals(crs.getString("bdn"))) {
								transferencia.setBancoDestNal(StringEscapeUtils.escapeXml(crs.getString("bd")));
							} else {
								transferencia.setBancoDestExt(StringEscapeUtils.escapeXml(crs.getString("bd")));
							}

							if ("Y".equals(crs.getString("ben"))) {
								transferencia.setBancoOriNal(StringEscapeUtils.escapeXml(crs.getString("be")));
							} else {
								transferencia.setBancoOriExt(StringEscapeUtils.escapeXml(crs.getString("be")));
							}

							transferencia.setCtaDest(crs.getString("bda"));
							transferencia.setCtaOri(crs.getString("bea"));
							transferencia.setFecha(TimeUtil.toXMLGregorianCalendar(crs.getTimestamp("dateTrx")));
							transferencia.setMoneda(CMoneda.valueOf(crs.getString("iso_code")));
							transferencia.setMonto(crs.getBigDecimal("amount"));
							transferencia.setTipCamb(crs.getBigDecimal("currencyRate"));
							transferencia.setRFC(taxId);

							transferencia.setBenef(crs.getString("benef"));

							transaccion.getTransferencia().add(transferencia);
						}
						// }
					}
				}
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, null, e);
		} finally {
			DB.close(rs, pstmt);
			DB.close(crs);
		}

		return polizas;
	}
	
	/**
	 * Metodo para traer los folios fiscales contenidos en las polizas contables
	 * 
	 * @param ctx
	 * @param periodId
	 * @param trxName
	 * @return
	 */
	public static RepAuxFol getXmlDataRepAux(Properties ctx, int periodId, String trxName) {
		RepAuxFol auxFol = new RepAuxFol();

		MPeriod mPeriod = new MPeriod(Env.getCtx(), periodId, null);
		MOrg mOrg = new MOrg(ctx, Env.getAD_Org_ID(ctx), null);
		MOrgInfo mOrgInfo = mOrg.getInfo();

		auxFol.setAnio(Integer.valueOf(mPeriod.getC_Year().getYear()));
		auxFol.setMes(StringUtils.leftPad(Integer.toString(mPeriod.getPeriodNo()), 2, '0'));
		auxFol.setRFC(mOrgInfo.getTaxID());

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ");
		sql.append("  fact.Description, ");
		sql.append("  fact.AmtAcctDr, ");
		sql.append("  fact.AmtAcctCr, ");
		sql.append("  fact.line_id, ");
		sql.append("  p.taxId, ");
		sql.append("  p2.taxId ");
		sql.append("  AS taxId2, ");
		sql.append("  c.iso_code, ");
		sql.append("  policy.PolicyNo, ");
		sql.append("  policy.DateTrx, ");
		sql.append("  policy.AD_Table_ID, ");
		sql.append("  policy.Record_ID, ");
		sql.append("  policy.policytype, ");
		sql.append("  policy.UUID, ");
		sql.append("  policy.Amount, ");
		sql.append("  policy.CurrencyRate, ");
		sql.append("  policy.AuxType, ");
		sql.append("  policy.docNo, ");
		sql.append("  policy.c_bpartner_id ");
		sql.append("  AS partnerId ");
		sql.append("FROM ");
		sql.append("  Fact_acct fact ");
		sql.append("  INNER JOIN Exme_PolicyInfo policy ");
		sql.append("  ON (fact.ad_table_Id = policy.ad_table_Id AND ");
		sql.append("  fact.record_Id = policy.record_Id) ");
		sql.append("  INNER JOIN C_ElementValue ce ");
		sql.append("  ON (fact.account_id = ce.c_elementvalue_id) ");
		sql.append("  INNER JOIN EXME_GroupAcct codGroup ");
		sql.append("  ON (ce.exme_groupacct_id = codGroup.exme_groupacct_id) ");
		sql.append("  INNER JOIN c_currency c ");
		sql.append("  ON fact.c_currency_id = c.c_currency_id LEFT ");
		sql.append("  JOIN c_bpartner p ");
		sql.append("  ON (fact.c_bpartner_id = p.c_bpartner_id) LEFT ");
		sql.append("  JOIN c_bpartner p2 ");
		sql.append("  ON (policy.c_bpartner_id = p2.c_bpartner_id) ");
		sql.append("WHERE ");
		sql.append("  AuxType = ? AND ");
		sql.append("  fact.c_period_id = ? AND ");
		sql.append("  policy.isActive = ");
		sql.append("'Y' ORDER BY ");
		sql.append("  fact.datetrx, ");
		sql.append("  policy.AD_Table_ID, ");
		sql.append("  policy.Record_ID, ");
		sql.append("  fact.created ");

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		CachedRowSetImpl crs = null;

		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setString(1, AUXTYPE_Invoice);
			pstmt.setInt(2, periodId);
			rs = pstmt.executeQuery();

			crs = new CachedRowSetImpl();
			crs.populate(rs);

			DB.close(rs, pstmt);

			int tableId = -1;
			int recordId = -1;

			DetAuxFol detFol = null;

			while (crs.next()) {
				int thisTableId = crs.getInt("AD_Table_ID");
				int thisRecordId = crs.getInt("Record_ID");

				String taxId = crs.getString("taxId");

				if (!(thisTableId == tableId && thisRecordId == recordId)) {
					detFol = new DetAuxFol();
					auxFol.getDetAuxFol().add(detFol);

					tableId = thisTableId;
					recordId = thisRecordId;

					String policyNo = crs.getString("PolicyNo");
					Timestamp dateTrx = crs.getTimestamp("DateTrx");

					detFol.setNumUnIdenPol(crs.getString("policytype") + "-" + policyNo);
					detFol.setFecha(TimeUtil.toXMLGregorianCalendar(dateTrx));
				}

				if (I_GL_Journal.Table_ID == tableId) {
					int lineId = crs.getInt("line_id");

					Dummy dummy = getXmlDummys(ctx, I_GL_JournalLine.Table_ID, lineId, null);
					if (dummy != null) {
						ComprNal comprNal = new ComprNal();
						comprNal.setMoneda(CMoneda.valueOf(dummy.getCurreny()));
						comprNal.setMontoTotal(dummy.getAmount());
						comprNal.setRFC(dummy.getTaxId());
						comprNal.setTipCamb(dummy.getCurrencyRate());
						comprNal.setUUIDCFDI(dummy.getUuid());

						detFol.getComprNal().add(comprNal);

					}
				} else {
					String uuid = crs.getString("UUID");
					if (detFol.getComprNal().isEmpty()) {
						ComprNal comprNal = new ComprNal();
						comprNal.setMoneda(CMoneda.valueOf(crs.getString("iso_code")));
						comprNal.setMontoTotal(crs.getBigDecimal("Amount"));
						comprNal.setRFC(taxId);
						comprNal.setTipCamb(crs.getBigDecimal("CurrencyRate"));
						comprNal.setUUIDCFDI(uuid);

						detFol.getComprNal().add(comprNal);
					}
				}
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, null, e);
		} finally {
			DB.close(rs, pstmt);
			DB.close(crs);
		}

		return auxFol;

	}

	/**
	 * Datos del xml
	 * 
	 * @param ctx
	 *            Contexto
	 * @param tableId
	 *            Tabla
	 * @param lineId
	 *            Linea
	 * @param trxName
	 *            Trx
	 * @return Dummy
	 */
	public static Dummy getXmlDummys(Properties ctx, int tableId, int lineId, String trxName) {
		Dummy dummy = null;

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ");
		sql.append("  pi.AuxType, ");
		sql.append("  c.iso_code, ");
		sql.append("  b.taxid, ");
		sql.append("  pi.uuid, ");
		sql.append("  pi.CurrencyRate, ");
		sql.append("  pi.Amount, ");
		sql.append("  pi.docNo, ");
		sql.append("  pi.datetrx, ");
		sql.append("  pi.name as benef, ");
		sql.append("  be.bankcode as be ,");
		sql.append("  bd.bankcode as bd ,");
		sql.append("  bea.accountno ");
		sql.append("  AS bea, ");
		sql.append("  be.isnational ");
		sql.append("  AS ben, ");
		sql.append("  bda.accountno ");
		sql.append("  AS bda, ");
		sql.append("  bd.isnational ");
		sql.append("  AS bdn ");
		sql.append("FROM ");
		sql.append("  exme_policyinfo pi ");
		sql.append("  INNER JOIN c_currency c ");
		sql.append("  ON pi.c_currency_id = c.c_currency_id ");
		sql.append("  INNER JOIN c_bpartner b ");
		sql.append("  ON pi.c_bpartner_id = b.c_bpartner_id left ");
		sql.append("  JOIN c_bankaccount bea ");
		sql.append("  ON pi.c_bankaccount_id = bea.c_bankaccount_id left ");
		sql.append("  JOIN c_bank be ");
		sql.append("  ON bea.c_bank_id = be.c_bank_id left ");
		sql.append("  JOIN c_bankaccount bda ");
		sql.append("  ON pi.c_bankaccountdest_id = bda.c_bankaccount_id left ");
		sql.append("  JOIN c_bank bd ");
		sql.append("  ON bda.c_bank_id = bd.c_bank_id ");
		sql.append("WHERE ");
		sql.append("  pi.ad_table_id = ? AND ");
		sql.append("  record_id = ? ");

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, tableId);
			pstmt.setInt(2, lineId);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				dummy = new Dummy();
				dummy.setAmount(rs.getBigDecimal("Amount"));
				dummy.setAuxType(rs.getString("AuxType"));
				dummy.setCurrencyRate(rs.getBigDecimal("CurrencyRate"));
				dummy.setCurreny(rs.getString("iso_code"));
				dummy.setTaxId(rs.getString("taxid"));
				dummy.setUuid(rs.getString("uuid"));
				dummy.seteBankAccount(rs.getString("bea"));
				dummy.seteBankNational("Y".equals(StringUtils.defaultString(rs.getString("ben"))));
				dummy.setdBankAccount(rs.getString("bda"));
				dummy.setdBankNational("Y".equals(StringUtils.defaultString(rs.getString("bdn"))));
				dummy.setDocNo(rs.getString("docNo"));
				dummy.seteBank(rs.getString("be"));
				dummy.setdBank(rs.getString("bd"));
				dummy.setBenef(rs.getString("benef"));
				dummy.setDateTrx(rs.getTimestamp("dateTrx"));
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, null, e);
		} finally {
			DB.close(rs, pstmt);
		}

		return dummy;
	}
	
	private String documentNo;
	private String policyTypeStr;
	private MTable table;

	/**
	 * @param ctx
	 * @param EXME_PolicyInfo_ID
	 * @param trxName
	 */
	public MEXMEPolicyInfo(Properties ctx, int EXME_PolicyInfo_ID, String trxName) {
		super(ctx, EXME_PolicyInfo_ID, trxName);
	}

	/**
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MEXMEPolicyInfo(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	/**
	 * Número de documento relacionado
	 * 
	 * @return No. de Doc
	 */
	public String getDocumentNo() {
		if (documentNo == null) {
			StringBuilder str = new StringBuilder();

			str.append(" select documentno from ");
			str.append(getTable().getTableName());
			str.append(" where ");
			str.append(getTable().getTableName());
			str.append("_ID = ? ");

			documentNo = DB.getSQLValueString(null, str.toString(), getRecord_ID());
		}
		return documentNo;
	}

	/**
	 * Tipo de póliza en texto
	 * 
	 * @return Tipo de póliza
	 */
	public String getPolicyTypeStr() {
		if (policyTypeStr == null) {
			policyTypeStr = MRefList.getListName(getCtx(), POLICYTYPE_AD_Reference_ID, getPolicyType());
		}
		return policyTypeStr;
	}

	/**
	 * Tabla relacionada
	 * 
	 * @return
	 */
	public MTable getTable() {
		if (table == null) {
			table = new MTable(getCtx(), getAD_Table_ID(), null);
		}
		return table;
	}

	/**
	 * Según el gl_category se puede saber si es o no editable
	 * 
	 * @return
	 */
	public boolean isEditable() {
		boolean retValue = false;

		if (getGL_Category_ID() > 0) {
			if (Categories.CASH_PAYMENTS.equals(getGL_Category().getName())) {
				retValue = true;
			}
		}

		return retValue;
	}

}
