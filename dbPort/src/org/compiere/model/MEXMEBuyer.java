package org.compiere.model;

import java.io.File;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.process.DocAction;
import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;

public class MEXMEBuyer extends X_EXME_Buyer implements DocAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static CLogger slog = CLogger.getCLogger(MEXMEBuyer.class);

	public MEXMEBuyer(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		
	}
	
	public MEXMEBuyer(Properties ctx, int Exme_Buyer_ID, String trxName) {
		super(ctx, Exme_Buyer_ID, trxName);
		
	}
	
	/**
	 * Obetener la lista de compradores
	 * @param ctx
	 * @param userid id del usuari
	 * @param trxName
	 * @return
	 */
	public static List<KeyNamePair> getBuyer(final Properties ctx, final int userid, final String trxName) {
		List<KeyNamePair> list = new ArrayList<KeyNamePair>();
		
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append("SELECT ");
		sql.append("  bu.exme_buyer_id, us.name ");
		sql.append("FROM ");
		sql.append("  exme_buyer bu ");
		sql.append("  INNER JOIN ad_user us ");
		sql.append("  ON us.ad_user_id = bu.ad_user_id ");
		sql.append("  WHERE bu.isactive = 'Y' ");
		
		if(userid > 0){
			sql.append(" AND bu.ad_user_id = ").append(userid);
			
		}
		sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx,
				 sql.toString(), Table_Name, "bu"));
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = DB.prepareStatement(sql.toString(), null);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				list.add(new KeyNamePair(rs.getInt(1), rs.getString(2)));
			}
		} catch (Exception e) {
			slog.log(Level.SEVERE, "getAll", e);
		} finally {
			DB.close(rs, pstmt);
		}
		return list;
	}
	
	/**
	 * Obtener el id de exme_buyer dependiendo del usuario que se envie
	 * @param userid
	 * @return exme_buyer_id
	 */
	
	public static int getIdFromUser(final int userid) {
		int exmeBuyerId = -1;
		Properties ctx= Env.getCtx();
		final StringBuilder sql = new StringBuilder("Select EXME_Buyer_ID ");
		sql.append(" FROM EXME_Buyer WHERE Ad_User_id = ? ")
		   .append(" AND AD_Client_id = ? AND Ad_Org_id = ? ")
		   .append(" AND IsActive='Y' ");

		exmeBuyerId = DB.getSQLValue(null, sql.toString(), userid, Env.getAD_Client_ID(ctx), Env.getAD_Org_ID(ctx));

		return exmeBuyerId;
	}
	
	public static String getUserFromExmeBuyer(final int exme_Buyer_Id, int user_id) {
		String userName = null;
		Properties ctx= Env.getCtx();
		final StringBuilder sql = new StringBuilder("Select us.name ");
		sql.append(" FROM exme_buyer buy ");
		sql.append(" inner join ad_user us on us.Ad_User_id = buy.Ad_User_id ");
		sql.append(" WHERE exme_buyer_id = ? ");
		sql.append(" AND buy.AD_Client_id = ? AND buy.Ad_Org_id = ? ");

		userName = DB.getSQLValueStringEx(null, sql.toString(), exme_Buyer_Id, Env.getAD_Client_ID(ctx), Env.getAD_Org_ID(ctx));

		return userName;
	}
	
	@Override
	public void setDocStatus(String newStatus) {
		
		
	}

	@Override
	public String getDocStatus() {
		
		return null;
	}

	@Override
	public boolean processIt(String action) throws Exception {
		
		return false;
	}

	@Override
	public boolean unlockIt() {
		
		return false;
	}

	@Override
	public boolean invalidateIt() {
		
		return false;
	}

	@Override
	public String prepareIt() {

		return null;
	}

	@Override
	public boolean approveIt() {
		
		return false;
	}

	@Override
	public boolean rejectIt() {
		
		return false;
	}

	@Override
	public String completeIt() {
		
		return null;
	}

	@Override
	public boolean voidIt() {
		
		return false;
	}

	@Override
	public boolean closeIt() {
		
		return false;
	}

	@Override
	public boolean reverseCorrectIt() {
		
		return false;
	}

	@Override
	public boolean reverseAccrualIt() {
		
		return false;
	}

	@Override
	public boolean reActivateIt() {
		
		return false;
	}

	@Override
	public String getSummary() {
		
		return null;
	}

	@Override
	public String getDocumentNo() {
		
		return null;
	}

	@Override
	public String getDocumentInfo() {
		
		return null;
	}

	@Override
	public File createPDF() {
		
		return null;
	}

	@Override
	public String getProcessMsg() {
		
		return null;
	}

	@Override
	public int getDoc_User_ID() {
		
		return 0;
	}

	@Override
	public int getC_Currency_ID() {
		
		return 0;
	}

	@Override
	public BigDecimal getApprovalAmt() {
		
		return null;
	}

	@Override
	public String getDocAction() {
		
		return null;
	}

}
