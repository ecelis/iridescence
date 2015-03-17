package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.apache.commons.lang.StringUtils;
import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.QuickSearchTables;
import org.compiere.util.Utilerias;

public class MEXMECtaPacAuth extends X_EXME_CtaPacAuth {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1263629863026438033L;

	private static CLogger s_log = CLogger.getCLogger(MEXMECtaPacAuth.class);

	public MEXMECtaPacAuth(Properties ctx, int EXME_CtaPacAuth_ID,
			String trxName) {
		super(ctx, EXME_CtaPacAuth_ID, trxName);
	}

	public MEXMECtaPacAuth(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	/**
	 * 
	 * @param ctx
	 * @param asegID
	 * @param trxName
	 * @return
	 */
	public static List<MEXMECtaPacAuth> getLstCtaPacAuth(Properties ctx,
			int asegID, String trxName) {

		List<MEXMECtaPacAuth> list = new ArrayList<MEXMECtaPacAuth>();

		StringBuilder st = new StringBuilder();
		st.append("select * from exme_CtaPacAuth where exme_ctapac_id = ?");

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(st.toString(), trxName);
			pstmt.setInt(1, asegID);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				MEXMECtaPacAuth aut = new MEXMECtaPacAuth(ctx, rs, null);
				list.add(aut);
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, st.toString(), e);
		} finally {
			DB.close(rs, pstmt);
			rs= null;
			pstmt = null;
		}

		return list;

	}
	
	/**
	 * 
	 * @param ctx
	 * @param asegID
	 * @param trxName
	 * @return
	 */
	public static MEXMECtaPacAuth getFromCtaPac(Properties ctx,
			int ctaPacID, String trxName) {

		MEXMECtaPacAuth ctaPacAuth = null;

		StringBuilder st = new StringBuilder();
		st.append("select * from exme_CtaPacAuth where exme_ctapac_Id = ?");

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(st.toString(), trxName);
			pstmt.setInt(1, ctaPacID);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				ctaPacAuth = new MEXMECtaPacAuth(ctx, rs, null);
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, st.toString(), e);
		} finally {
			DB.close(rs, pstmt);
			rs= null;
			pstmt = null;
		}

		return ctaPacAuth;

	}
	
	/**
	 * After Save
	 * @param newRecord new
	 * @param success success
	 * @return success
	 */
	protected boolean afterSave(boolean newRecord, boolean success) {
		if(!success){
			return false;
		}
		
		// si es nuevo registro
		if (newRecord && success) {
			
			
			//La nota se guarda solo si el campo no esta vacio
			if (StringUtils.isNotBlank(getComments())){
				 saveRegistrarNote();
			}
		}
		return success;
	} // afterSave
		
	/**
	 * Guardamos una nota en MRequest usando Comments (Authorization - Notes)
	 * @return
	 */
	private boolean saveRegistrarNote() {
		MRequest req = new MRequest(Env.getCtx(), 0, get_TrxName());
		req.setClientOrg(Env.getAD_Client_ID(getCtx()), Env.getAD_Org_ID(getCtx()));
		req.setSummary(getComments());
		req.setNoteType(MRequest.NOTETYPE_RegistrationNote);
		req.setStartDate(Env.getCurrentDate());
		req.setInitials(MUser.getFromID(MUser.COLUMNNAME_Description, Env.getAD_User_ID(Env.getCtx())));
		req.setDueType(MRequest.DUETYPE_Overdue);
		req.setAD_Table_ID(MEXMECtaPac.Table_ID);
		req.setRecord_ID(getEXME_CtaPac_ID());
		return req.save();		
	}

}
