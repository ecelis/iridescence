package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.Env;

public class MEXMEClaimError extends X_EXME_ClaimError {

	public MEXMEClaimError(Properties ctx, int EXME_ClaimError_ID,
			String trxName) {
		super(ctx, EXME_ClaimError_ID, trxName);
	}
	
	/** Load Constructor */
    public MEXMEClaimError (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

	/**
	 * 
	 */
	private static final long serialVersionUID = -6047201981097236884L;

    /**	Static Logger				*/
	private static CLogger		s_log = CLogger.getCLogger (MEXMEClaimError.class);
	
	public static List<MEXMEClaimError>getErrors(int ctaPacID, String status, String type, String trxName){
		ArrayList<MEXMEClaimError> list = new ArrayList<MEXMEClaimError>();
	       StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
	       sql.append(" SELECT * FROM EXME_ClaimError ")
	       .append(" WHERE EXME_ClaimError.EXME_CtaPac_ID = ? ")
	       .append(" AND EXME_ClaimError.ErrorStatus = 'Y' ")
	       .append(" AND EXME_ClaimError.Type = ? ")
	       .append(" AND EXME_ClaimError.ConfType = ? ")
	       .append(" AND EXME_ClaimError.IsActive = 'Y'  ");
	       sql.append(" ORDER BY EXME_ClaimError.EXME_ClaimError_ID ASC");

	       PreparedStatement pstmt = null;
	       ResultSet rs = null;
	        try {
	            pstmt = DB.prepareStatement(sql.toString(), trxName);

	            pstmt.setInt(1, ctaPacID);
	            if (type==MHL7MessageConf.CONFTYPE_ProfessionalClaim){
	            	pstmt.setString(2, status.equalsIgnoreCase(MEXMECtaPacExt.PROFESSIONALSTATUS_ErrorMandatoryFields) 
           				 ? MEXMEClaimError.TYPE_MandatoryFields
           				 : MEXMEClaimError.TYPE_MessageResponse);
	    		}else{
	    			pstmt.setString(2, status.equalsIgnoreCase(MEXMECtaPacExt.INSTITUTIONALSTATUS_ErrorMandatoryFields) 
           				 ? MEXMEClaimError.TYPE_MandatoryFields
           				 : MEXMEClaimError.TYPE_MessageResponse);
	    		}

            	pstmt.setString(3, type);
	            rs = pstmt.executeQuery();

	            while (rs.next()) {
	            	MEXMEClaimError retValue = new  MEXMEClaimError(Env.getCtx(), rs, trxName);
	               list.add(retValue);
	             }
	        } catch (Exception e){
	            s_log.log(Level.SEVERE, "getErrors ", e);
	        } finally{
	            DB.close(rs,pstmt);
			     rs = null;
			     pstmt = null;
	        }

	        return list;
	}
	
	public static boolean createError (Properties ctx, String confType, String type, int ctaPacID, String message , String trxName){
		MEXMEClaimError error = new MEXMEClaimError(ctx, 0, null);
		error.setType(type);
		error.setErrorStatus(Boolean.TRUE);
		error.setErrorDescription(message);
		error.setEXME_CtaPac_ID(ctaPacID);
		error.setConfType(confType);
		return error.save(trxName);
	}

}

