package org.compiere.model;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.apache.commons.lang.StringUtils;
import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.SecureEngine;
import org.compiere.util.ValueNamePair;

/**
 * 
 * @author expert
 * 
 */
public class MEXMEClaimPaymentH extends X_EXME_ClaimPaymentH {

	/** serialVersionUID */
	private static final long	serialVersionUID	= 154961431236746876L;
	/** log */
	private static CLogger		slog				= CLogger.getCLogger(MEXMEClaimPaymentH.class);

	/**
	 * Constructor
	 * 
	 * @param ctx
	 */
	public MEXMEClaimPaymentH(Properties ctx) {
		super(ctx, 0, null);
	}

	/**
	 * Constructor
	 * 
	 * @param ctx
	 * @param EXME_ClaimPaymentH_ID
	 * @param trxName
	 */
	public MEXMEClaimPaymentH(Properties ctx, int EXME_ClaimPaymentH_ID, String trxName) {
		super(ctx, EXME_ClaimPaymentH_ID, trxName);
	}

	/**
	 * Constructor
	 * 
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MEXMEClaimPaymentH(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	/*
	 * private int C_Invoice_ID; private int EXME_CtaPac_ID; // sum private
	 * BigDecimal totalAdjust; private BigDecimal totalBilled; private
	 * BigDecimal totalCoIns; private BigDecimal totalCopay; private BigDecimal
	 * totalDeductible; private BigDecimal totalPaid;
	 */
	/** */
	private List<MEXMEClaimPayment>	claimPayments	= new ArrayList<MEXMEClaimPayment>();

	/**
	 * 
	 * @param ctx
	 * @param cbPartnerID
	 * @return
	 */
	public static List<MEXMEClaimPaymentH> getPendings(Properties ctx, int cbPartnerID) {
		return new Query(ctx, Table_Name, " C_BPartner_ID = ? AND PaidAmt <> TotalAmt ", null)//
				.setParameters(cbPartnerID)//
				.addAccessLevelSQL(true)//
				.list();
	}

	/**
	 * 
	 * @param requery
	 * @param orderBy
	 * @return
	 */
	public List<MEXMEClaimPayment> getClaimPayments(boolean requery, String orderBy) {

		if (claimPayments == null || requery) {
			String whereClause = null;
			Object[] params = null;

			if (getEXME_ClaimPaymentH_ID() > 0) {
				whereClause = " EXME_ClaimPaymentH_ID = ? ";
				params = new Object[] { getEXME_ClaimPaymentH_ID() };
				/*
				 * } else if (getEXME_CtaPac_ID() > 0 && getC_Invoice_ID() > 0)
				 * { whereClause = " C_Invoice_ID = ? AND EXME_CtaPac_ID = ? ";
				 * params = new Object[] { getC_Invoice_ID(),
				 * getEXME_CtaPac_ID() };
				 */
			} else {
				return new ArrayList<MEXMEClaimPayment>();
			}

			// if (claimPayments == null || requery) {
			claimPayments = new Query(getCtx(), I_EXME_ClaimPayment.Table_Name, whereClause, null)//
					.setParameters(params)//
					.setOnlyActiveRecords(true)//
					.addAccessLevelSQL(true)//
					.setOrderBy("EXME_ClaimPayment_ID, C_Invoice_ID, EXME_CtaPac_ID" + (StringUtils.isNotEmpty(orderBy) ? ", " + orderBy : ""))//
					.list();
			// }
		}

		return claimPayments;
	}

	/*
	 * public static List<MEXMEClaimPaymentH> getTotals(Properties ctx, String
	 * whereClause, Object... params ) { List<MEXMEClaimPaymentH> retValue = new
	 * ArrayList<MEXMEClaimPaymentH>(); // StringBuilder whereClause = new
	 * StringBuilder(); // List<Object> params = new ArrayList<Object>(); // if
	 * (getEXME_ClaimPaymentH_ID() > 0) { //
	 * whereClause.append(" AND cp.EXME_ClaimPaymentH_ID=? "); //
	 * params.add(getEXME_ClaimPaymentH_ID()); // } // if (getEXME_CtaPac_ID() >
	 * 0 && getC_Invoice_ID() > 0) { //
	 * whereClause.append(" AND cp.C_Invoice_ID=? AND cp.EXME_CtaPac_ID=? "); //
	 * params.add(getC_Invoice_ID()); // params.add(getEXME_CtaPac_ID()); // }
	 * 
	 * StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
	 * sql.append("SELECT cp.EXME_CtaPac_ID, cp.C_Invoice_ID, cp.Amt_Billed, ");
	 * sql.append("SUM(cp.Amt_Paid) paid, "); sql.append(
	 * "SUM(CASE WHEN cp.EXME_AdjustmentType_ID IS NULL OR Trim(at.Type)='O' THEN cp.Amt_Adjust ELSE 0 END) Adjust, "
	 * ); sql.append(
	 * "SUM(CASE WHEN Trim(at.Type)='D' THEN cp.Amt_Adjust ELSE 0 END) Deductible, "
	 * ); sql.append(
	 * "SUM(CASE WHEN Trim(at.Type)='I' THEN cp.Amt_Adjust ELSE 0 END) coins, "
	 * ); sql.append(
	 * "SUM(CASE WHEN Trim(at.Type)='C' THEN cp.Amt_Adjust ELSE 0 END) coPay ");
	 * sql.append("FROM EXME_ClaimPayment cp "); sql.append(
	 * "LEFT JOIN EXME_AdjustmentType at ON (cp.EXME_AdjustmentType_ID = at.EXME_AdjustmentType_ID) "
	 * ); sql.append("WHERE cp.IsActive = 'Y' "); sql.append(whereClause);
	 * sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ",
	 * I_EXME_ClaimPayment.Table_Name, "cp"));
	 * sql.append("GROUP BY cp.EXME_CtaPac_ID, cp.C_Invoice_ID, cp.Amt_Billed "
	 * );
	 * 
	 * PreparedStatement pstmt = null; ResultSet rs = null;
	 * 
	 * try { pstmt = DB.prepareStatement(sql.toString(), null); if
	 * (params.length > 0) { DB.setParameters(pstmt, params); } rs =
	 * pstmt.executeQuery();
	 * 
	 * while (rs.next()) { MEXMEClaimPaymentH header = new
	 * MEXMEClaimPaymentH(ctx);
	 * header.setEXME_CtaPac_ID(rs.getInt("EXME_CtaPac_ID"));
	 * header.setC_Invoice_ID(rs.getInt("C_Invoice_ID"));
	 * header.setTotalAdjust(rs.getBigDecimal("Adjust"));
	 * header.setTotalBilled(rs.getBigDecimal("Amt_Billed"));
	 * header.setTotalCoIns(rs.getBigDecimal("CoIns"));
	 * header.setTotalCopay(rs.getBigDecimal("Copay"));
	 * header.setTotalDeductible(rs.getBigDecimal("Deductible"));
	 * header.setTotalPaid(rs.getBigDecimal("Paid")); retValue.add(header); }
	 * 
	 * } catch (Exception e) { slog.log(Level.SEVERE, sql.toString(), e); }
	 * finally { DB.close(rs, pstmt); }
	 * 
	 * return retValue; }
	 * 
	 * public int getC_Invoice_ID() { return C_Invoice_ID; }
	 * 
	 * public void setC_Invoice_ID(int c_Invoice_ID) { C_Invoice_ID =
	 * c_Invoice_ID; }
	 * 
	 * public int getEXME_CtaPac_ID() { return EXME_CtaPac_ID; }
	 * 
	 * public void setEXME_CtaPac_ID(int eXME_CtaPac_ID) { EXME_CtaPac_ID =
	 * eXME_CtaPac_ID; }
	 */
	public List<MEXMEClaimPayment> getClaimPayments() {
		return claimPayments;
	}

	public void setClaimPayments(List<MEXMEClaimPayment> claimPayments) {
		this.claimPayments = claimPayments;
	}

	/*
	 * public BigDecimal getTotalAdjust() { return totalAdjust; }
	 * 
	 * public void setTotalAdjust(BigDecimal totalAdjust) { this.totalAdjust =
	 * totalAdjust; }
	 * 
	 * public BigDecimal getTotalBilled() { return totalBilled; }
	 * 
	 * public void setTotalBilled(BigDecimal totalBilled) { this.totalBilled =
	 * totalBilled; }
	 * 
	 * public BigDecimal getTotalCoIns() { return totalCoIns; }
	 * 
	 * public void setTotalCoIns(BigDecimal totalCoIns) { this.totalCoIns =
	 * totalCoIns; }
	 * 
	 * public BigDecimal getTotalCopay() { return totalCopay; }
	 * 
	 * public void setTotalCopay(BigDecimal totalCopay) { this.totalCopay =
	 * totalCopay; }
	 * 
	 * public BigDecimal getTotalDeductible() { return totalDeductible; }
	 * 
	 * public void setTotalDeductible(BigDecimal totalDeductible) {
	 * this.totalDeductible = totalDeductible; }
	 * 
	 * public BigDecimal getTotalPaid() { return totalPaid; }
	 * 
	 * public void setTotalPaid(BigDecimal totalPaid) { this.totalPaid =
	 * totalPaid; }
	 */
	/**
	 * Socio de negocio
	 */
	public MBPartner	mbPartner	= null;

	public MBPartner getBPartner() {
		if (getC_BPartner_ID() > 0 && mbPartner == null)
			mbPartner = new MBPartner(getCtx(), getC_BPartner_ID(), get_TrxName());
		return mbPartner;
	}

	/*
	 * private MEXMECtaPac mCtaPac = null;
	 * 
	 * public MEXMECtaPac getCtaPac() { if(getEXME_CtaPac_ID()>0) mCtaPac = new
	 * MEXMECtaPac(Env.getCtx(), getEXME_CtaPac_ID(), get_TrxName()); return
	 * mCtaPac; } /* private MPayment mPayment= null;
	 * 
	 * public MPayment getPayment() { //if(getC_Payment_ID()>0) //mPayment = new
	 * MPayment(Env.getCtx(), getC_Payment_ID(), get_TrxName());
	 * if(mPayment==null) mPayment = new MPayment(Env.getCtx(), 0,
	 * get_TrxName()); return mPayment; }
	 * 
	 * /*private BigDecimal montoDelPagoDeLaAseguradora = Env.ZERO; private
	 * BigDecimal montoPendienteDeAplicar = Env.ZERO; private BigDecimal
	 * montoTotalDeLasLineas = Env.ZERO;
	 * 
	 * public BigDecimal getMontoDelPagoDeLaAseguradora() { return
	 * montoDelPagoDeLaAseguradora; }
	 * 
	 * public void setMontoDelPagoDeLaAseguradora( BigDecimal
	 * montoDelPagoDeLaAseguradora) { this.montoDelPagoDeLaAseguradora =
	 * montoDelPagoDeLaAseguradora; }
	 * 
	 * public BigDecimal getMontoPendienteDeAplicar() { return
	 * montoPendienteDeAplicar; }
	 * 
	 * public void setMontoPendienteDeAplicar(BigDecimal
	 * montoPendienteDeAplicar) { this.montoPendienteDeAplicar =
	 * montoPendienteDeAplicar; }
	 * 
	 * public BigDecimal getMontoTotalDeLasLineas() { return
	 * montoTotalDeLasLineas; }
	 * 
	 * public void setMontoTotalDeLasLineas(BigDecimal montoTotalDeLasLineas) {
	 * this.montoTotalDeLasLineas = montoTotalDeLasLineas; }
	 */

	/**
	 * List
	 * 
	 * @param ctx
	 * @param cbPartnerID
	 * @return
	 * 
	 *         public static List<MEXMEClaimPaymentH> getLstPendings(Properties
	 *         ctx, int cbPartnerID) { List<MEXMEClaimPaymentH> claimPayments =
	 *         new ArrayList<MEXMEClaimPaymentH>();
	 * 
	 *         String sql =
	 *         "SELECT * FROM EXME_ClaimPaymentH WHERE IsActive = 'Y' "
	 *         +" AND C_BPartner_ID = ? AND PaidAmt <> TotalAmt ";
	 * 
	 *         PreparedStatement pstmt = null; ResultSet rs = null; try { pstmt
	 *         = DB.prepareStatement(sql, null); pstmt.setInt(1, cbPartnerID);
	 *         rs = pstmt.executeQuery(); while (rs.next()){
	 *         claimPayments.add(new MEXMEClaimPaymentH(ctx,rs, null)); } }
	 *         catch (Exception e) { //s_log.log(Level.SEVERE, sql, e); }
	 *         finally{ DB.close(rs, pstmt); } return claimPayments; }
	 */

	/**
	 * 
	 * @param ctx
	 * @param C_BPartner_ID
	 * @param EXME_CtaPac_ID
	 * @param trxName
	 * @return
	 */
	public HashMap<Integer, Object[]> claimPayment() {
		HashMap<Integer, Object[]> mapa = new LinkedHashMap<Integer, Object[]>();
		
		StringBuilder sql = new StringBuilder()
				.append(" SELECT  EXME_CtaPac_ID, documentno, Nombre_Pac, ")
				.append(" case when billingtype = 'P' then professionalstep else institutionalstep end step, ")
				.append(" case when billingtype = 'P' then professionalstatus else institutionalstatus end status,")

				// .append(" coalesce((select balance from exme_claimpayment cmp  ")
				.append(" (select balance from exme_claimpayment cmp  ")
				.append(" where cmp.exme_claimpayment_id = (select max(cm.exme_claimpayment_id) from exme_claimpayment cm ")
				// .append(" where cm.exme_claimpaymenth_id = ? and cm.exme_ctapac_id = lista.exme_ctapac_id)), GrandTotal) GrandTotal,")
				.append(" where cm.exme_claimpaymenth_id = ? and cm.exme_ctapac_id = lista.exme_ctapac_id)) AS GrandTotal,")
				.append(" sum(Pago) AS Pago, sum(Ajuste) AS Ajuste  ")
				.append(" FROM ( ")
				.append(" SELECT  ")
				.append("   cp.EXME_CtaPac_ID ")
				.append(" , case ")
				.append("     when crg.Type = 'P' then cp.amt_paid ")
				// AND EAT.TYPE IN ('P','Y','E','N','T','M')
				.append("     when crg.Type = 'Y' then cp.amt_paid ")
				.append("     when crg.Type = 'E' then cp.amt_paid ")
				.append("     when crg.Type = 'N' then cp.amt_paid ")
				.append("     when crg.Type = 'T' then cp.amt_paid ")
				.append("     when crg.Type = 'M' then cp.amt_paid ")
				.append("     else 0 ")
				.append("   end AS Pago ")
				.append(" , case ")
				.append("     when crg.Type = 'A' then cp.amt_paid ")
				// AND EAT.TYPE IN ('A','B','G','O')
				.append("     when crg.Type = 'B' then cp.amt_paid ").append("     when crg.Type = 'G' then cp.amt_paid ")
				.append("     when crg.Type = 'O' then cp.amt_paid ").append("     else 0 ").append("   end AS Ajuste ")
				.append(" , cta.documentno  ").append(" , cta.institutionalstep, cta.institutionalstatus ")
				.append(" , cta.professionalstep, cta.professionalstatus, cph.billingtype ")
				.append(" , pac.Nombre_Pac ")
				.append(" , inv.GrandTotal ")
				.append(" FROM   EXME_ClaimPayment cp ")
				.append(" INNER JOIN EXME_ClaimPaymentH cph ON cph.EXME_ClaimPaymentH_ID = cp.EXME_ClaimPaymentH_ID ")
				.append(" INNER JOIN EXME_CtaPac cta   on cta.exme_ctapac_id = cp.exme_ctapac_id ")
				.append(" INNER JOIN EXME_Paciente pac on cta.EXME_Paciente_id = pac.EXME_Paciente_id ")
				.append(" INNER JOIN C_Charge crg      on cp.C_Charge_id = crg.C_Charge_id ")
				.append(" LEFT JOIN C_Invoice inv     on cp.C_Invoice_id = inv.C_Invoice_id ")
				.append(" WHERE cp.EXME_ClaimPaymentH_ID = ? ")
				.append(" ) lista ")
				.append(" GROUP BY EXME_CtaPac_ID, documentno, institutionalstep, institutionalstatus, ")
				// .append(" professionalstep, professionalstatus, billingType, Nombre_Pac, GrandTotal ");
				.append(" professionalstep, professionalstatus, billingType, Nombre_Pac ")
				// se agregar Order By como fueron capturados (se obtiene la
				// primer linea del encuentro.
				.append(" ORDER BY (SELECT MIN(D.EXME_CLAIMPAYMENT_ID) ").append(" FROM EXME_CLAIMPAYMENT D ")
				.append(" WHERE D.EXME_ClaimPaymentH_ID = ?  ").append(" AND D.ISACTIVE = 'Y' ")
				.append(" AND D.EXME_CTAPAC_ID  = LISTA.EXME_CTAPAC_ID)");

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), get_TrxName());
			pstmt.setInt(1, getEXME_ClaimPaymentH_ID());
			pstmt.setInt(2, getEXME_ClaimPaymentH_ID());
			pstmt.setInt(3, getEXME_ClaimPaymentH_ID());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Object list[] = new Object[8];

				list[0] = rs.getInt("EXME_CtaPac_ID");
				list[1] = rs.getString("documentno");
				list[2] = rs.getString("step");
				list[3] = rs.getString("status");
				list[4] = rs.getString("Nombre_Pac");
				list[5] = rs.getBigDecimal("GrandTotal");
				list[6] = rs.getBigDecimal("Pago");
				list[7] = rs.getBigDecimal("Ajuste");
				if (!mapa.containsKey(rs.getInt("EXME_CtaPac_ID")))
					mapa.put(rs.getInt("EXME_CtaPac_ID"), list);
			}

		} catch (Exception e) {
			slog.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
		}

		//
		return mapa;
	}

	/**
	 * Is valid
	 * 
	 * @return <true> create payments
	 */
	public boolean isValid() {
		return getEXME_ClaimPaymentH_ID() > 0;
		// return getEXME_ClaimPaymentH_ID()>0 && getStatus()!=null &&
		// getStatus().equals(X_C_Invoice.DOCSTATUS_InProgress);
	}


	public static List<ValueNamePair> getAll(Properties ctx) {

		final List<ValueNamePair> array = new ArrayList<ValueNamePair>();
		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		sql.append("select * from ad_ref_list where ad_reference_id = ? and isactive = 'Y'");

		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setInt(1, BILLINGTYPE_AD_Reference_ID);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				array.add(new ValueNamePair(String.valueOf(rs.getString(MEXMETipoPaciente.COLUMNNAME_Value)), rs
						.getString(MEXMETipoPaciente.COLUMNNAME_Name)));
			}

		} catch (Exception e) {
			slog.log(Level.SEVERE, sql.toString() + " - " + e.getMessage());
		} finally {
			DB.close(rs, pstmt);
		}
		return array;
	}

	/**
	 * Obtenemos los ID de los headers segun su EXME_ClaimPayment_S_ID
	 * 
	 * @param ctx
	 *            El contexto de la aplicacion
	 * @param EXME_ClaimPayment_S_ID
	 *            El identificador de la tabla super padre que almacena los
	 *            headers
	 * @param trxName
	 *            El nombre de la transaccion
	 * @return Ids de los headers relacionados, separados por coma
	 */
	public static String getHeaders(Properties ctx, int EXME_ClaimPayment_S_ID, String trxName) {
		List<Integer> ids = new ArrayList<Integer>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);

		try {
			sql.append("SELECT EXME_ClaimPaymentH_ID FROM EXME_ClaimPaymentH WHERE EXME_ClaimPayment_S_ID = ? AND isActive = 'Y'").append(
					MEXMELookupInfo.addAccessLevelSQL(ctx, " ", MEXMEClaimPaymentH.Table_Name));
			pstmt = DB.prepareStatement(sql.toString(), trxName);

			pstmt.setInt(1, EXME_ClaimPayment_S_ID);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				ids.add(rs.getInt("EXME_ClaimPaymentH_ID"));
			}

		} catch (Exception e) {
			slog.log(Level.SEVERE, e.getMessage(), e);
		} finally {
			DB.close(rs, pstmt);
		}

		return StringUtils.join(ids, " , ");
	}

	/**
	 * Obtenemos los ID de los headers segun su EXME_ClaimPayment_S_ID
	 * 
	 * @param ctx
	 *            El contexto de la aplicacion
	 * @param EXME_ClaimPayment_S_ID
	 *            El identificador de la tabla super padre que almacena los
	 *            headers
	 * @param trxName
	 *            El nombre de la transaccion
	 * @return Ids de los headers relacionados
	 */
	public static List<MEXMEClaimPaymentH> getHeaderList(Properties ctx, int EXME_ClaimPayment_S_ID, String trxName) {
		List<MEXMEClaimPaymentH> ids = new ArrayList<MEXMEClaimPaymentH>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);

		try {
			sql.append("SELECT EXME_ClaimPaymentH_ID FROM EXME_ClaimPaymentH WHERE EXME_ClaimPayment_S_ID = ? AND isActive = 'Y'").append(
					MEXMELookupInfo.addAccessLevelSQL(ctx, " ", MEXMEClaimPaymentH.Table_Name));
			pstmt = DB.prepareStatement(sql.toString(), trxName);

			pstmt.setInt(1, EXME_ClaimPayment_S_ID);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				ids.add(new MEXMEClaimPaymentH(ctx, rs.getInt("EXME_ClaimPaymentH_ID"), null));
			}

		} catch (Exception e) {
			slog.log(Level.SEVERE, e.getMessage(), e);
		} finally {
			DB.close(rs, pstmt);
		}

		return ids;
	}

	/**
	 * Valida si el header MEXMEClaimPaymentH contiene lineas MEXMEClaimPayment
	 * 
	 * @return true si el header contiene lineas
	 */
	public static boolean hasLines(Properties ctx, int EXME_ClaimPaymentH_ID, String trxName) {
		boolean hasLines = false;
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append(" SELECT * FROM EXME_ClaimPaymentH hdr ")
				.append(" INNER JOIN EXME_ClaimPayment det ON det.EXME_ClaimPaymentH_ID = hdr.EXME_ClaimPaymentH_ID ")
				.append(" WHERE hdr.EXME_ClaimPaymentH_ID = ? AND hdr.isActive = 'Y' ")
				.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", MEXMEClaimPaymentH.Table_Name, "hdr"));

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, EXME_ClaimPaymentH_ID);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				hasLines = true;
			}
		} catch (Exception e) {
			slog.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
		}
		return hasLines;
	}

	public static class EncounterPay {
		private int		EXME_CtaPac_ID;
		private String	documentNo;
		private String	nombrePac;
		private String	step;
		private String	status;
		private double	total;
		private double	payment;
		private double	adjustment;
		private int EXME_CtaPacExt_ID;

		private EncounterPay(ResultSet rs) throws SQLException {
			EXME_CtaPac_ID = rs.getInt("EXME_CtaPac_ID");
			documentNo = rs.getString("documentno");
			step = rs.getString("step");
			status = rs.getString("status");
			nombrePac = SecureEngine.decrypt(rs.getString("Nombre_Pac"));
			total = rs.getDouble("GrandTotal");
			payment = rs.getDouble("Pago");
			adjustment = rs.getDouble("Ajuste");
			EXME_CtaPacExt_ID = rs.getInt("exme_ctapacext_id");
		}

		public static List<EncounterPay> getEncounterPayments(X_EXME_ClaimPaymentH exmeClaimPaymentH) {
			List<EncounterPay> encounterPays = new ArrayList<MEXMEClaimPaymentH.EncounterPay>();
			StringBuilder sql = new StringBuilder()
					.append(" SELECT EXME_CtaPac_ID, exme_ctapacext_id,  documentno, Nombre_Pac, ")
					.append(" case when billingtype = 'P' then professionalstep else institutionalstep end step, ")
					.append(" case when billingtype = 'P' then professionalstatus else institutionalstatus end status,")

					// .append(" coalesce((select balance from exme_claimpayment cmp  ")
					.append(" (select balance from exme_claimpayment cmp  ")
					.append(" where cmp.exme_claimpayment_id = (select max(cm.exme_claimpayment_id) from exme_claimpayment cm ")
					// .append(" where cm.exme_claimpaymenth_id = ? and cm.exme_ctapac_id = lista.exme_ctapac_id)), GrandTotal) GrandTotal,")
					.append(" where cm.exme_claimpaymenth_id = ? and cm.exme_ctapac_id = lista.exme_ctapac_id)) AS GrandTotal,")
					.append(" sum(Pago) AS Pago, sum(Ajuste) AS Ajuste  ")
					.append(" FROM ( ")
					.append(" SELECT  ")
					.append("   cp.EXME_CtaPac_ID ")
					.append(" , case ")
					.append("     when crg.Type = 'P' then cp.amt_paid ")
					// AND EAT.TYPE IN ('P','Y','E','N','T','M')
					.append("     when crg.Type = 'Y' then cp.amt_paid ")
					.append("     when crg.Type = 'E' then cp.amt_paid ")
					.append("     when crg.Type = 'N' then cp.amt_paid ")
					.append("     when crg.Type = 'T' then cp.amt_paid ")
					.append("     when crg.Type = 'M' then cp.amt_paid ")
					.append("     else 0 ")
					.append("   end AS Pago ")
					.append(" , case ")
					.append("     when crg.Type = 'A' then cp.amt_paid ")
					// AND EAT.TYPE IN ('A','B','G','O')
					.append("     when crg.Type = 'B' then cp.amt_paid ").append("     when crg.Type = 'G' then cp.amt_paid ")
					.append("     when crg.Type = 'O' then cp.amt_paid ").append("     else 0 ").append("   end AS Ajuste ")
					.append(" , cta.documentno  ").append(" , cte.institutionalstep, cte.institutionalstatus ")
					.append(" , cte.professionalstep, cte.professionalstatus, cph.billingtype ")
					.append(" , pac.Nombre_Pac ")
					.append(" , inv.GrandTotal, cp.exme_ctapacext_id  ")
					.append(" FROM   EXME_ClaimPayment cp ")
					.append(" INNER JOIN EXME_ClaimPaymentH cph ON cph.EXME_ClaimPaymentH_ID = cp.EXME_ClaimPaymentH_ID ")
					.append(" INNER JOIN EXME_CtaPac cta   on cta.exme_ctapac_id = cp.exme_ctapac_id ")
					.append(" INNER JOIN EXME_CtaPacExt cte   on cte.EXME_CtaPacExt_id = cp.EXME_CtaPacExt_id ")
					.append(" INNER JOIN EXME_Paciente pac on cta.EXME_Paciente_id = pac.EXME_Paciente_id ")
					.append(" INNER JOIN C_Charge crg      on cp.C_Charge_id = crg.C_Charge_id ")
					.append(" LEFT JOIN C_Invoice inv     on cp.C_Invoice_id = inv.C_Invoice_id ")
					.append(" WHERE cp.EXME_ClaimPaymentH_ID = ? ")
					.append(" ) lista ")
					.append(" GROUP BY EXME_CtaPac_ID, documentno, institutionalstep, institutionalstatus, ")
					// .append(" professionalstep, professionalstatus, billingType, Nombre_Pac, GrandTotal ");
					.append(" professionalstep, professionalstatus, billingType, Nombre_Pac, exme_ctapacext_id ")
					// se agregar Order By como fueron capturados (se obtiene la
					// primer linea del encuentro.
					.append(" ORDER BY (SELECT MIN(D.EXME_CLAIMPAYMENT_ID) ").append(" FROM EXME_CLAIMPAYMENT D ")
					.append(" WHERE D.EXME_ClaimPaymentH_ID = ?  ").append(" AND D.ISACTIVE = 'Y' ")
					.append(" AND D.EXME_CTAPAC_ID  = LISTA.EXME_CTAPAC_ID) ");

			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {
				pstmt = DB.prepareStatement(sql.toString(), exmeClaimPaymentH.get_TrxName());
				pstmt.setInt(1, exmeClaimPaymentH.getEXME_ClaimPaymentH_ID());
				pstmt.setInt(2, exmeClaimPaymentH.getEXME_ClaimPaymentH_ID());
				pstmt.setInt(3, exmeClaimPaymentH.getEXME_ClaimPaymentH_ID());
				rs = pstmt.executeQuery();
				while (rs.next()) {
					encounterPays.add(new EncounterPay(rs));
				}

			} catch (Exception e) {
				slog.log(Level.SEVERE, sql.toString(), e);
			} finally {
				DB.close(rs, pstmt);
			}
			return encounterPays;
		}

		public int getEXME_CtaPac_ID() {
			return EXME_CtaPac_ID;
		}

		public void setEXME_CtaPac_ID(int eXME_CtaPac_ID) {
			EXME_CtaPac_ID = eXME_CtaPac_ID;
		}

		public String getDocumentNo() {
			return documentNo;
		}

		public void setDocumentNo(String documentNo) {
			this.documentNo = documentNo;
		}

		public String getNombrePac() {
			return nombrePac;
		}

		public void setNombrePac(String nombrePac) {
			this.nombrePac = nombrePac;
		}

		public String getStep() {
			return step;
		}

		public void setStep(String step) {
			this.step = step;
		}

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}

		public double getTotal() {
			return total;
		}

		public void setTotal(double total) {
			this.total = total;
		}

		public double getPayment() {
			return payment;
		}

		public void setPayment(double payment) {
			this.payment = payment;
		}

		public double getAdjustment() {
			return adjustment;
		}

		public void setAdjustment(double adjustment) {
			this.adjustment = adjustment;
		}

		public int getEXME_CtaPacExt_ID() {
			return EXME_CtaPacExt_ID;
		}

		public void setEXME_CtaPacExt_ID(int eXME_CtaPacExt_ID) {
			EXME_CtaPacExt_ID = eXME_CtaPacExt_ID;
		}
		
		
	}

	/**
	 * Accion por cuenta paciente
	 * 
	 * @param ctx
	 * @param EXME_CtaPac_ID
	 * @param C_BPartner_ID
	 * @param C_Invoice_ID
	 * @param EXME_ClaimPaymentH_ID
	 * @param priority
	 * @param trxName
	 * @return
	 */
	public List<MEXMEClaimPayment> getClaimPaymentsForAccionCtaPac(Properties ctx, final int EXME_CtaPac_ID, boolean all, final String trxName) {

		List<MEXMEClaimPayment> claim = new ArrayList<MEXMEClaimPayment>();
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT p.* ");
		sql.append(" FROM EXME_ClaimPayment p ");
		sql.append(" INNER JOIN C_Charge ad ON p.C_Charge_ID = ad.C_Charge_ID AND ad.IsActive = 'Y' ");
		sql.append(" WHERE p.IsActive = 'Y' ");
		sql.append(" AND p.EXME_ClaimPaymentH_ID = ? ");
		sql.append(" AND p.EXME_CtaPac_ID = ? ");
		if (!all) {
			sql.append(" AND ad.NextInvoice = 'Y'");
		}

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, getEXME_ClaimPaymentH_ID());
			pstmt.setInt(2, EXME_CtaPac_ID);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				claim.add(new MEXMEClaimPayment(ctx, rs, trxName));
			}

		} catch (Exception e) {
			slog.log(Level.SEVERE, "", e);
			claim = null;
		} finally {
			DB.close(rs, pstmt);
		}

		//
		return claim;
	}
}
