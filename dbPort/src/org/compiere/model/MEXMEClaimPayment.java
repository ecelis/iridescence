package org.compiere.model;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.apache.commons.lang.StringUtils;
import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.Env;

public class MEXMEClaimPayment extends X_EXME_ClaimPayment implements Comparable<MEXMEClaimPayment> {
	/** serialVersionUID */
	private static final long	serialVersionUID	= 1L;
	/** Static Logger */
	private static CLogger		slog				= CLogger.getCLogger(MEXMEClaimPayment.class);
	/** */
	private MEXMEProduct		m_product			= null;
	/** */
	private MCharge				mCharge				= null;

	/**
	 * Cosntructor
	 * 
	 * @param ctx
	 * @param EXME_ClaimPayment_ID
	 * @param trxName
	 */
	public MEXMEClaimPayment(Properties ctx, int EXME_ClaimPayment_ID, String trxName) {
		super(ctx, EXME_ClaimPayment_ID, trxName);
	}

	/**
	 * Constructor
	 * 
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MEXMEClaimPayment(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	/**
	 * Obtenemos el producto
	 * 
	 * @return
	 */
	public MEXMEProduct getProduct() {

		if (m_product != null)
			return m_product;

		if (getM_Product_ID() <= 0)
			return null;

		m_product = new MEXMEProduct(getCtx(), getM_Product_ID(), get_TrxName());

		return m_product;

	}

	/**
	 * Obtenemos el Adjustment Type
	 * 
	 * @return
	 */
	public MCharge getCharge() {

		if (mCharge != null)
			return mCharge;

		if (getC_Charge_ID() <= 0)
			return null;

		mCharge = new MCharge(getCtx(), getC_Charge_ID(), get_TrxName());

		return mCharge;

	}

	public void setAdjustmentType(MCharge pmCharge) {
		this.mCharge = pmCharge;
	}

	public int coinsuranceID() {
		MEXMEAdjustmentType ajust = MEXMEAdjustmentType.getMEXMEAdjustmentType(getCtx(), " AND  Type = ? ", X_EXME_AdjustmentType.TYPE_Coinsurance);
		return ajust.getEXME_AdjustmentType_ID();
	}

	public int deductibleID() {
		MEXMEAdjustmentType ajust = MEXMEAdjustmentType.getMEXMEAdjustmentType(getCtx(), " AND  Type = ? ", X_EXME_AdjustmentType.TYPE_Deductible);
		return ajust.getEXME_AdjustmentType_ID();
	}

	public int copayID() {
		MEXMEAdjustmentType ajust = MEXMEAdjustmentType.getMEXMEAdjustmentType(getCtx(), " AND  Type = ? ", X_EXME_AdjustmentType.TYPE_CoPay);
		return ajust.getEXME_AdjustmentType_ID();
	}

	public int providerID() {
		MEXMEAdjustmentType ajust = MEXMEAdjustmentType.getMEXMEAdjustmentType(getCtx(), " AND  Type = ? ", X_EXME_AdjustmentType.TYPE_Others);
		return ajust.getEXME_AdjustmentType_ID();
	}

	private MEXMECtaPac	mCtaPac	= null;

	public MEXMECtaPac getCtaPac() {
		if (getEXME_CtaPac_ID() > 0) {
			mCtaPac = new MEXMECtaPac(Env.getCtx(), getEXME_CtaPac_ID(), get_TrxName());
		}
		return mCtaPac;
	}

	public static MInvoice getInvoice(Properties ctx, int C_BPartner_ID, int EXME_CtaPac_ID, String trxName) {
		MInvoice list = null;
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append(" SELECT i.* FROM EXME_ClaimPayment cp ");
		sql.append(" INNER JOIN C_Invoice i ON i.C_Invoice_ID = cp.C_Invoice_ID ");
		sql.append(" WHERE i.C_BPartner_ID = ? ");
		sql.append(" AND cp.EXME_CtaPac_ID = ? ");

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, C_BPartner_ID);
			pstmt.setInt(2, EXME_CtaPac_ID);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				list = new MInvoice(ctx, rs, trxName);
			}

		} catch (Exception e) {
			slog.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
		}

		//
		return list;
	} 

	/**
	 * Obtener todas las lineas detalle de un Header determinado
	 * 
	 * @param ClaimPayment_ID
	 **/
	public static List<MEXMEClaimPayment> getfromHdr(Properties ctx, int ClaimPayment_ID, String trxName) {
		List<MEXMEClaimPayment> list = new ArrayList<MEXMEClaimPayment>();
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append(" SELECT * FROM EXME_ClaimPayment  ").append(" WHERE EXME_ClaimPaymentH_ID = ? ").append(" AND isActive = 'Y' ")
				.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", MEXMEClaimPayment.Table_Name));

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, ClaimPayment_ID);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				list.add(new MEXMEClaimPayment(ctx, rs, trxName));
			}

		} catch (Exception e) {
			slog.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
		}
		return list;
	}

	/**
	 * Obtener todas las lineas detalle de varios Headers
	 * 
	 * @param ClaimPaymentH_ID
	 **/
	public static List<MEXMEClaimPayment> getfromHdrs(Properties ctx, String ClaimPaymentH_IDs, String trxName) {
		List<MEXMEClaimPayment> list = new ArrayList<MEXMEClaimPayment>();
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append(" SELECT * FROM EXME_ClaimPayment  ").append(" WHERE EXME_ClaimPaymentH_ID IN ( ").append(ClaimPaymentH_IDs)
				.append(") AND isActive = 'Y' ").append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", MEXMEClaimPayment.Table_Name));

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				list.add(new MEXMEClaimPayment(ctx, rs, trxName));
			}

		} catch (Exception e) {
			slog.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
		}
		return list;
	}

	/**
	 * Encabezado
	 */
	private MEXMEClaimPaymentH	mClaimPaymentH	= null;

	/**
	 * Obj. del encabezado
	 * 
	 * @return
	 */
	public MEXMEClaimPaymentH getClaimPaymentH() {
		if (getEXME_ClaimPaymentH_ID() > 0 && mClaimPaymentH == null)
			mClaimPaymentH = new MEXMEClaimPaymentH(Env.getCtx(), getEXME_ClaimPaymentH_ID(), get_TrxName());
		return mClaimPaymentH;
	}

	public static int getCountEncounters(Properties ctx, int EXME_ClaimPayment_S_ID) {
		int encounterNum = 0;
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append(" SELECT COUNT(DISTINCT(det.EXME_CtaPac_ID)) ").append(" FROM EXME_ClaimPayment det ")
				.append(" INNER JOIN EXME_ClaimPaymentH hdr ON det.EXME_ClaimPaymentH_ID = hdr.EXME_ClaimPaymentH_ID ")
				.append(" INNER JOIN EXME_ClaimPayment_S super ON super.EXME_ClaimPayment_S_ID = hdr.EXME_ClaimPayment_S_ID ")
				.append(" WHERE super.EXME_ClaimPayment_S_ID = ? AND det.isActive = 'Y'")
				.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", MEXMEClaimPayment.Table_Name, "det"));

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setInt(1, EXME_ClaimPayment_S_ID);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				encounterNum = rs.getInt(1);
			}

		} catch (Exception e) {
			slog.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
		}
		return encounterNum;
	}

	/**
	 * Ordena por cuenta paciente, aseguradora, tipo de facturacion y su header
	 * (si existe o no)
	 */
	@Override
	public int compareTo(MEXMEClaimPayment o) {
		if (getEXME_CtaPac_ID() > o.getEXME_CtaPac_ID()) {
			return -1;
		} else if (getEXME_CtaPac_ID() == o.getEXME_CtaPac_ID()) {
			if (getC_BPartner_ID() > o.getC_BPartner_ID()) {
				return -1;
			} else if (getC_BPartner_ID() == o.getC_BPartner_ID()) {
				if (getBillingType().equals(o.getBillingType())) {
					if (getEXME_ClaimPaymentH_ID() > 0) {
						return -1;
					} else {
						return 0;
					}
				} else {
					return getBillingType().compareTo(o.getBillingType());
				}
			} else {
				return 1;
			}
		} else {
			return 1;
		}
	}

	/**
	 * Busca en EXME_ClaimCharges_ID si existe un registro con los mismos
	 * valores clave EXME_CtaPac_ID, DATEDELIVERED y CODE del EXME_ClaimPayment
	 * 
	 * @return
	 */
	public int findEXME_ClaimCharges_ID() {

		int exmeClaimChargesID = -1;
		if (StringUtils.isNotBlank(getCode()) && getDateOrdered() != null) {
			StringBuilder sql = new StringBuilder();
			sql.append(" SELECT ").append(" EXME_CLAIMCHARGES_ID").append(" FROM EXME_ClaimCharges").append(" WHERE IsActive = 'Y'  ")
					.append(MEXMELookupInfo.addAccessLevelSQL(Env.getCtx(), " ", "EXME_ClaimCharges")).append(" AND EXME_CtaPac_ID = ?")
					.append(" AND DATEDELIVERED = ? AND CODE = ? ");
			sql.append(" ORDER BY Code, RevenueCode, DATEDELIVERED ");
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {
				pstmt = DB.prepareStatement(sql.toString(), null);
				pstmt.setInt(1, getEXME_CtaPac_ID());
				pstmt.setTimestamp(2, getDateOrdered());
				pstmt.setString(3, getCode());

				rs = pstmt.executeQuery();

				if (rs.next()) {
					exmeClaimChargesID = rs.getInt(1);
				}
			} catch (Exception e) {
				DB.close(rs, pstmt);
				throw new RuntimeException(e);
			} finally {
				DB.close(rs, pstmt);
			}

		}
		return exmeClaimChargesID;

	}

	public List<ClaimCharge> getClaimCharges() {
		List<ClaimCharge> list = new ArrayList<ClaimCharge>();
		StringBuilder sql = new StringBuilder(
				"SELECT DISTINCT RECORDVIEW_ID, REVENUECODE, CODE, SERV_DATE,TOTAL_CHARGES,NON_COVERED_CHARGES,DESCRIPTION from EXME_VISTA_CHARGES_837 WHERE RECORDVIEW_ID = ?");

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setInt(1, getEXME_CtaPac_ID());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				list.add(new ClaimCharge(rs));

			}
		} catch (SQLException e) {
			slog.log(Level.SEVERE, sql.toString(), e);
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
			throw new RuntimeException(e);
		} finally {
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}
		return list;
	}

	public void loadClaimChargeValues(ClaimCharge claimCharge) {
		if (claimCharge == null) {
			setDateOrdered(null);
			setCode(null);
			setAmt_Billed(null);
		} else {
			setDateOrdered(claimCharge.getDeliveredDate());
			setCode(claimCharge.getCode() == null ? claimCharge.getRevenueCode() : claimCharge.getCode());
			setAmt_Billed(claimCharge.getTotalAmount().add(claimCharge.getNonCoveredCharges()));
		}
	}

	public static class ClaimCharge {
		private int			EXME_CtaPac_ID;
		private String		revenueCode;
		private String		code;
		private Timestamp	deliveredDate;
		private BigDecimal	totalAmount;
		private BigDecimal	nonCoveredCharges;
		private String		description;

		public ClaimCharge(ResultSet resultSet) throws SQLException {
			EXME_CtaPac_ID = resultSet.getInt("RECORDVIEW_ID");
			revenueCode = resultSet.getString("REVENUECODE");
			code = resultSet.getString("CODE");
			deliveredDate = resultSet.getTimestamp("SERV_DATE");
			totalAmount = resultSet.getBigDecimal("TOTAL_CHARGES");
			nonCoveredCharges = resultSet.getBigDecimal("NON_COVERED_CHARGES");
			description = resultSet.getString("DESCRIPTION");
		}

		public String getRevenueCode() {
			return revenueCode;
		}

		public String getCode() {
			return code;
		}

		public Timestamp getDeliveredDate() {
			return deliveredDate;
		}

		public BigDecimal getTotalAmount() {
			return totalAmount;
		}

		public BigDecimal getNonCoveredCharges() {
			return nonCoveredCharges;
		}

		public int getEXME_CtaPac_ID() {
			return EXME_CtaPac_ID;
		}

		public String getDescription() {
			return description;
		}

		@Override
		public String toString() {
			return (StringUtils.isBlank(getCode()) ? "" : (getCode() + " - ")) + getRevenueCode();
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
	public List<MEXMEClaimPayment> paymentCtaPac(Properties ctx, final int EXME_CtaPac_ID, final int EXME_ClaimPaymentH_ID, boolean all,
			final String trxName) {

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
			pstmt.setInt(1, EXME_ClaimPaymentH_ID);
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

	private static String DUMMY = "dummy";
	
	@Override
	protected boolean beforeSave(boolean newRecord) {
		if(newRecord && StringUtils.isBlank(getClaim_TransID())){
			setClaim_TransID(DUMMY);
		}		
		return super.beforeSave(newRecord);
	}
	
	@Override
	protected boolean afterSave(boolean newRecord, boolean success) {
		if(DUMMY.equals(getClaim_TransID()) && success){
			setClaim_TransID(String.valueOf(getEXME_ClaimPayment_ID()));
			save();
		}
		return super.afterSave(newRecord, success);
	}
}
