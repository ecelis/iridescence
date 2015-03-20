package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.DB;

/**
 * Clase para los Consentimientos del Paciente
 * @author mvrodriguez
 *
 */
public class MEXMEConsentimientoPac extends X_EXME_ConsentimientoPac {

	/** serialVersionUID */
	private static final long serialVersionUID = 7549436336141394035L;
	/** Log */
	private static CLogger log = CLogger.getCLogger (MEXMEConsentimientoPac.class);
	/** */
	private Timestamp date;
	/** */
	private boolean status;
	/** */
	private byte[] file;
	/** */
	private static Object image;

	/**
	 * Constructor con id
	 * @param ctx
	 * @param EXME_ConsentimimientoPac_ID
	 * @param trxName
	 */
	public MEXMEConsentimientoPac(Properties ctx, int EXME_ConsentimimientoPac_ID, String trxName) {
		super(ctx, EXME_ConsentimimientoPac_ID, trxName);
	}
	
	/**
	 * Constructor con ResultSet
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MEXMEConsentimientoPac(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}
	
	public void setDate(Timestamp date) {
		this.date = date;
	}
	
	public Timestamp getDate() {
		return date;
	}
	
	public void setStatus(boolean status) {
		this.status = status;
	}
	
	public boolean getStatus() {
		return status;
	}
	
	public void setFile(byte[] file) {
		this.file = file;
	}
	
	public byte[] getFile() {
		return file;
	}

	@SuppressWarnings("unused")
	private static void setImage(Object imageNew) {
		image = imageNew;
	}
	
	public Object getImage() {
		return image;
	}
	
	/**
	 * Nos trae la lista del Historial de Consentimientos de un paciente en particular
	 * @param ctx Contexto
	 * @param EXME_Paciente_ID Llave unica del paciente del que deseamos el historial
	 * @return List
	 */
	public static ArrayList<MEXMEConsentimientoPac> getHistoria(Properties ctx, int EXME_Paciente_ID, String isActive){
		StringBuilder sql = new StringBuilder();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		ArrayList<MEXMEConsentimientoPac> lstConsentimientoPac = new ArrayList<MEXMEConsentimientoPac>();

		try{
			
			sql.append(" SELECT EXME_CONSENTIMIENTOPAC.EXME_CONSENTIMIENTOPAC_ID " )
			   .append("      , EXME_CONSENTIMIENTOPAC.CREATED ")
			   .append("      , EXME_CONSENTIMIENTOPAC.DATEDOC ")
			   .append("      , EXME_CONSENTIMIENTOPAC.COMMENTS ")
			   .append("      , EXME_CONSENTIMIENTOPAC.ISACTIVE ")
			   .append("      , EXME_CONSENTIMIENTOPAC.ARCHIVO ")
			   .append("   FROM EXME_CONSENTIMIENTOPAC ")
			   .append("  INNER JOIN  EXME_PACIENTE ON (EXME_PACIENTE.EXME_PACIENTE_ID = EXME_CONSENTIMIENTOPAC.EXME_PACIENTE_ID ) ")
			  // .append("   FROM EXME_CTAPAC ")
			   //.append("  INNER JOIN EXME_CONSENTIMIENTOPAC ON (EXME_CTAPAC.EXME_CTAPAC_ID = EXME_CONSENTIMIENTOPAC.EXME_CTAPAC_ID) ")
			   .append("  WHERE EXME_CONSENTIMIENTOPAC.EXME_PACIENTE_ID = ? ");
			
			if(isActive != null) {
				
				sql.append("  AND EXME_CONSENTIMIENTOPAC.ISACTIVE = ? ");
				
			}
			
			sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, sql.toString(), Table_Name));
			sql.append(" ORDER BY EXME_CONSENTIMIENTOPAC.CREATED DESC ");
			
			pstmt = DB.prepareStatement(sql.toString(), null);
							
			pstmt.setInt(1, EXME_Paciente_ID);
			
			if(isActive != null) {
				
				pstmt.setString(2, isActive);
				
			}
			rs = pstmt.executeQuery();

			while(rs.next()){
				
				MEXMEConsentimientoPac cp = new MEXMEConsentimientoPac(ctx, rs, null);
				
				cp.setDate(cp.getCreated());
				cp.setStatus(cp.isActive());
				
				lstConsentimientoPac.add(cp);
				
			}
			
		}catch(Exception e){
			
			log.log(Level.SEVERE, sql.toString(), e);
			
		}finally{
			DB.close(rs, pstmt);
		}
		
		return lstConsentimientoPac;
	}
	
	/**
	 * Listado de consentimientos por cuenta paciente
	 * @param ctx Contexto
	 * @param id Llave unica de la cta paciente del que deseamos el historial
	 * @param isActive  si los registros estan activos o no 
	 * @return listado de consentimientos 
	 */
	public static ArrayList<MEXMEConsentimientoPac> getPorCuenta(
			Properties ctx, int id, String isActive) {

		return MEXMEConsentimientoPac.get(ctx,
				"  AND EXME_CONSENTIMIENTOPAC.EXME_CtaPac_ID = ? ", id,
				isActive);
	}

	/**
	 * Listado de consentimientos por paciente
	 * @param ctx Contexto
	 * @param id Llave unica del paciente del que deseamos el historial
	 * @param isActive  si los registros estan activos o no 
	 * @return listado de consentimientos 
	 */
	public static ArrayList<MEXMEConsentimientoPac> getPorPaciente(
			Properties ctx, int id, String isActive) {

		return MEXMEConsentimientoPac.get(ctx,
				"  AND EXME_CTAPAC.EXME_PACIENTE_ID = ? ", id, isActive);
	}

	/**
	 * Nos trae la lista del Historial de Consentimientos 
	 * activos o no de la base de datos
	 * @param ctx Contexto
	 * @param queryWhere condicion de la consulta
	 * @param id identificador
	 * @param isActive si los registros estan activos o no 
	 * @return listado de consentimientos 
	 */
	public static ArrayList<MEXMEConsentimientoPac> get(Properties ctx,
			String queryWhere, int id, String isActive) {
		StringBuilder sql = new StringBuilder();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<MEXMEConsentimientoPac> lstConsentimientoPac = new ArrayList<MEXMEConsentimientoPac>();

		try {

			sql
			.append(" SELECT EXME_CONSENTIMIENTOPAC.* ")
			.append("   FROM EXME_CONSENTIMIENTOPAC ")
			.append(
			"  INNER JOIN EXME_CTAPAC ON (EXME_CTAPAC.EXME_CTAPAC_ID = EXME_CONSENTIMIENTOPAC.EXME_CTAPAC_ID) ");
			
			if(isActive!=null){
				sql.append(" WHERE EXME_CONSENTIMIENTOPAC.ISACTIVE = '").append(isActive).append("' ");
			} else {
				sql.append(" WHERE EXME_CONSENTIMIENTOPAC.ISACTIVE IN ('Y' , 'N') ");
			}
			
			sql.append(
					MEXMELookupInfo.addAccessLevelSQL(ctx, " ",
							Table_Name)).append(queryWhere).append(
							" ORDER BY EXME_CONSENTIMIENTOPAC.CREATED DESC ");

			pstmt = DB.prepareStatement(sql.toString(), null);
			if(id>0)
				pstmt.setInt(1, id);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				lstConsentimientoPac.add(new MEXMEConsentimientoPac(ctx, rs,
						null));
			}

		} catch (Exception e) {
			log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
		}

		return lstConsentimientoPac;
	}

	/**
	 * ConsentimientoPac por cuenta paciente
	 * @param ctx
	 * @param exmeCtaPacID
	 * @param string
	 * @return
	 */
	public static MEXMEConsentimientoPac getCtaPac(Properties ctx,
			int exmeCtaPacID, String string) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MEXMEConsentimientoPac retValue = null;
		StringBuilder sql = new StringBuilder(); 
			sql.append(" SELECT * ")
			.append(" FROM EXME_ConsentimientoPac ")
			.append(" WHERE IsActive = 'Y' AND EXME_CtaPac_ID = ? ")
			.append(" ORDER BY Created DESC, EXME_ConsentimientoPac_ID DESC ");
			
		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setInt(1, exmeCtaPacID);
			rs = pstmt.executeQuery();

			if(rs.next()){
				retValue = new MEXMEConsentimientoPac(ctx, rs, null);
			}
		}catch(Exception e){
			log.log(Level.SEVERE, sql.toString(), e);
		}finally{
			DB.close(rs, pstmt);
		}

		return retValue;
	}

	/**
	 * Objeto de la Cuenta paciente
	 */
	private MEXMECtaPac mCtaPac = null;

	public MEXMECtaPac getmCtaPac() {
		if(this.mCtaPac==null)
			this.mCtaPac = new MEXMECtaPac(getCtx(), this.getEXME_CtaPac_ID(), null);
		return this.mCtaPac;
	}

	public void setmCtaPac(MEXMECtaPac mCtaPac) {
		this.mCtaPac = mCtaPac;
	}
	
	
	public String getResStatusStr() {
		return MRefList.getListName(getCtx(), RESSTATUS_AD_Reference_ID, super.getResStatus());
	}
	
}
