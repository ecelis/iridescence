package org.compiere.model;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

import org.apache.commons.codec.binary.Base64;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;

public class MEXMEERXConfig extends X_EXME_ERXConfig {
	
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	private static CLogger s_log = CLogger.getCLogger(MEXMEERXConfig.class);
	private static MEXMEERXConfig config= null;

	public MEXMEERXConfig(Properties ctx, int EXME_ERXConfig_ID, String trxName) {
		super(ctx, EXME_ERXConfig_ID, trxName);
	}

	public MEXMEERXConfig(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}
	
	

	public static  MEXMEERXConfig getInstance(Properties ctx){
		//if (config == null){

			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM EXME_ERXConfig WHERE EXME_ERXConfig.IsActive='Y' ");
			if(Env.getUserPatientID(ctx) < 0) //Si es un usuario paciente no agrega el accessLevel
				sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx," ",Table_Name));
			sql.append(" ORDER BY EXME_ERXConfig.AD_Org_ID DESC ");

			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {
				pstmt = DB.prepareStatement(sql.toString(), null);
				rs = pstmt.executeQuery();
				if (rs.next()) {
					config = new MEXMEERXConfig(ctx, rs, null);				
				}

			}catch (Exception e) {
				s_log.log(Level.SEVERE, "get", e);
			} finally {
				DB.close(rs,pstmt);
				rs = null;
				pstmt = null;
			}
		
		return config;
	}
	
	/**Realiza la encriptacion necesaria en Basic Autorization*/
	public String generateBasicAuth(){
		String result = null;
		  try {
	            String concatened = getUsuario() + ":" + String.valueOf(getPassword());
	            byte[] utf8array = concatened.getBytes("UTF-8");
	            byte[] b64array = Base64.encodeBase64(utf8array);
	            result = new String(b64array);


	        } catch (UnsupportedEncodingException ex) {
	            log.log(Level.SEVERE, "", ex);
	        }
		  
		  return "Basic "+result;

	}
	
	/**Realiza el Digest necesaria en Security en directories*/
	public String generateDigestPass(){
		String result = null;
		  try {
			  MessageDigest digest =MessageDigest.getInstance("SHA1");
 	           byte[] binaryData = digest.digest(getDirectoryUserPass().toUpperCase().getBytes("UTF-16LE"));
	           result = new String(Base64.encodeBase64(binaryData));

	        } catch (UnsupportedEncodingException ex) {
	            log.log(Level.SEVERE, "UnsupportedEncodingException", ex);
	        } catch (NoSuchAlgorithmException e) {
	        	log.log(Level.SEVERE, "NoSuchAlgorithmException", e);
			}
		  
		  return result;

	}

}
