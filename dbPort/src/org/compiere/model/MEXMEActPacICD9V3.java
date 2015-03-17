package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.vo.ActPacICD9V3VO;

public class MEXMEActPacICD9V3 extends X_EXME_ActPac_ICD9V3 {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    /**	Static Logger				*/
	private static CLogger		s_log = CLogger.getCLogger (MEXMEActPacICD9V3.class);

	public MEXMEActPacICD9V3(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	public MEXMEActPacICD9V3(Properties ctx, int exme_ActPac_ICD9V3_ID,
			String trxName) {
		super(ctx, exme_ActPac_ICD9V3_ID, trxName);
	}
	
	
	/**************************************************************************
	 * 	Before Save
	 *	@param newRecord new
	 *	@return true
	 */
	protected boolean beforeSave (boolean newRecord)
	{
		log.fine("");
		if (newRecord && getSequence()==0 ){
			int sequence = getProceduresCount(getCtx(), getEXME_CtaPac_ID(), null, get_TrxName());
			setSequence(sequence+1);
		}
		
		return true;
	}	//	beforeSave
	
	
	public static List<ActPacICD9V3VO> getCtaPacICD9V3(int ctaPacID){
		List<ActPacICD9V3VO> lst = new ArrayList<ActPacICD9V3VO>();

		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append(" SELECT APICD.EXME_ActPac_ICD9V3_ID, APICD.DateOrdered, ICD.Value, ICD.Name, APICD.Description, APICD.Sequence, ")
		   //.append(" fnc_getStrClaimCodes(cp.exme_paciente_id, cp.exme_ctapac_id, ?, ?, apicd.exme_actpac_icd9v3_id) hcpcs")
		   .append(" CC.Record_ID ctaPacMedID, CC.EXME_ClaimCodes_ID ")
		   .append(" FROM EXME_ActPac_ICD9V3 APICD ")
		   .append(" INNER JOIN EXME_ICD9V3 ICD ON ICD.Exme_Icd9v3_Id = APICD.EXME_ICD9V3_ID ")
		   .append(" INNER JOIN EXME_CtaPac CP ON CP.EXME_CTAPAC_ID = APICD.Exme_Ctapac_Id ")
		   .append(" LEFT JOIN EXME_ClaimCodes CC ON CC.IsActive = 'Y' AND CC.AD_TableOrig_ID = ? ")
		   .append(" AND CC.RecordOrig_ID = APICD.EXME_ActPac_ICD9V3_ID ") 
		   .append(" AND CC.AD_Table_id = ?  ")
		   .append( "WHERE APICD.IsActive = 'Y' AND APICD.EXME_CtaPac_ID = ? ")
		   .append( "ORDER BY APICD.Sequence ");

		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setInt(1, MEXMEActPacICD9V3.Table_ID);
			pstmt.setInt(2, MEXMECtaPacMed.Table_ID);
			pstmt.setInt(3, ctaPacID);
			rs = pstmt.executeQuery();
			ActPacICD9V3VO obj = null;
			while (rs.next()) {
				obj = new ActPacICD9V3VO();
					obj.setActPac_ICD9V3_ID(rs.getInt("EXME_ActPac_ICD9V3_ID"));
					obj.setDateOrdered(rs.getTimestamp("DateOrdered"));
					obj.setName(rs.getString("Name"));
					obj.setValue(rs.getString("Value"));
					obj.setDescription(rs.getString("Description"));
					obj.setSequence(rs.getInt("Sequence"));
					obj.setClaimCodesID(rs.getInt("EXME_ClaimCodes_ID"));
					obj.setCtaPacMed_ID(rs.getInt("ctaPacMedID"));
					lst.add(obj);

			}
		} catch (SQLException ex) {
			s_log.log(Level.SEVERE, ex.getLocalizedMessage(), ex);
		} finally {
			DB.close(rs, pstmt);
		}

		return lst;
	}
	
	/**
	 * Return the number of Procedures for an Account
	 * 
	 * @param ctapacID Account identification
	 * @return Number of diagnoses
	 */
	public static int getProceduresCount(Properties ctx, int ctaPacID, String type, String trxName) {
		 int count = 0;

		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);

		sql.append(" select count(*) cuantos ");
		sql.append(" from exme_actpac_icd9v3 i ");
		sql.append(" where i.exme_ctapac_id = ? ");
		sql.append(" and i.isactive = 'Y' ");
		//FIXME: Revisar que va a pasar cuando se cancelen procedimientos, agregar el tipo cuando esto se defina con Zack
		//sql.append(" and actdiag.Type = ? ");
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setLong(1, ctaPacID);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				count = rs.getInt("cuantos");
			}

		} catch (Exception e) {
			s_log.log(Level.SEVERE, "getProceduresCount", e);
		} finally {
			DB.close(rs, pstmt);
		}
		return count;
	}

}
