package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.interfaces.BodyPart;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Language;

/**
 * Modelo para la tabla Exme_SystemBody
 * @author raul
 *
 */
public class MEXMESystemBody extends X_EXME_SystemBody implements BodyPart{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2933794208383426803L;
	private static CLogger log = CLogger.getCLogger (MEXMESystemBody.class);
	//private LinkedList<String> imagesSrc;
	private String nameTrl;
	//private String name;
	
	/**
	 * Constructor que recibe con un systembody_id
	 * @param ctx
	 * @param EXME_SystemBody_ID
	 * @param trxName
	 */
	public MEXMESystemBody(Properties ctx, int EXME_SystemBody_ID, String trxName) {
		super(ctx, EXME_SystemBody_ID, trxName);
	}
	
	/**
	 * Constructor que recibe un ResultSet
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MEXMESystemBody(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}
	/**
	 * Regresa una lista con los sistemas
	 * guardados en la base de datos
	 * @return
	 */
	public static ArrayList<MEXMESystemBody> getLstSystem(Properties ctx, String trxName){
		StringBuilder sql = new StringBuilder();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<MEXMESystemBody> lstSystem = new ArrayList<MEXMESystemBody>();

		try{
			sql.append(" SELECT EXME_SystemBody.EXME_SystemBody_ID " )
			.append(" FROM EXME_SystemBody ")
			.append(" WHERE IsActive = 'Y' "); 

			if(Env.getUserPatientID(ctx) < 0)
				sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, sql.toString(), "EXME_SystemBody"));
			sql.append(" ORDER BY EXME_SystemBody.EXME_SystemBody_ID ");
			
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			rs = pstmt.executeQuery();

			while(rs.next()){
				lstSystem.add(new MEXMESystemBody(ctx, rs.getInt(1), trxName));
			}
		}catch(Exception e){
			log.log(Level.SEVERE, sql.toString(), e);
		}finally{
			DB.close(rs, pstmt);
		}

		return lstSystem;
	}
	
	/*public LinkedList<String> getImagesSrc(){
		if(imagesSrc == null){
			imagesSrc = new LinkedList<String>();
			if(!StringUtils.isBlank(getFrontImage())){
				imagesSrc.add(getFrontImage());
			}
			if(!StringUtils.isBlank(getRightImage())){
				imagesSrc.add(getRightImage());
			}
			if(!StringUtils.isBlank(getBackImage())){
				imagesSrc.add(getBackImage());
			}
			if(!StringUtils.isBlank(getLeftImage())){
				imagesSrc.add(getLeftImage());
			}
		}	
		return imagesSrc;
	}*/
	
	/**
	 * Regresa una lista con las secciones
	 * del sistema pasado como parametro
	 * y que no tienen una sección padre
	 * @return 
	 */
	public static ArrayList<MEXMESectionBody> getLstSection(Properties ctx, int EXME_SystemBody_ID, String trxName){
		StringBuilder sql = new StringBuilder();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<MEXMESectionBody> lstSystem = new ArrayList<MEXMESectionBody>();

		try{
			sql.append(" SELECT EXME_SectionBody.EXME_SectionBody_ID " )
			.append(" FROM EXME_SectionBody ")
			.append(" WHERE EXME_SectionBody.EXME_SystemBody_ID = ? ")
			.append(" AND EXME_SectionBody.Ref_SectionBody_ID IS NULL ");
			
			if(Env.getUserPatientID(ctx) < 0)
				sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, sql.toString(), "EXME_SectionBody"));
			sql.append(" ORDER BY EXME_SectionBody.Name ");
			
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, EXME_SystemBody_ID);
			rs = pstmt.executeQuery();

			while(rs.next()){
				lstSystem.add(new MEXMESectionBody(ctx, rs.getInt(1), trxName));
			}
		}catch(Exception e){
			log.log(Level.SEVERE, sql.toString(), e);
		}finally{
			DB.close(rs, pstmt);
		}
		return lstSystem;
	}
	@Override
	public String getName(){
		if (nameTrl == null){
			Language lan = Env.getLanguage(getCtx());
			
			StringBuilder sql = new StringBuilder();
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try{
				sql.append(" SELECT trl.Name " )
				.append(" FROM EXME_SystemBody sb ")
				.append(" INNER JOIN EXME_SystemBody_TRL trl on (sb.EXME_SystemBody_ID = trl.EXME_SystemBody_ID) ")
				.append(" WHERE trl.IsTranslated = 'Y' ")
				.append(" AND trl.IsActive = 'Y' ")
				.append(" AND trl.AD_Language = ? ")
				.append(" AND trl.EXME_SystemBody_ID = ?");
				
				pstmt = DB.prepareStatement(sql.toString(), get_TrxName());
				pstmt.setString(1,	lan.getAD_Language());
				pstmt.setInt(2, getEXME_SystemBody_ID());
				rs = pstmt.executeQuery();

				if(rs.next()){
					nameTrl = rs.getString(1);
				}else{
					nameTrl = super.getName();
				}
			}catch(Exception e){
				log.log(Level.SEVERE, sql.toString(), e);
			}finally{
				DB.close(rs, pstmt);
			}
		}
		return nameTrl;
	}
	
	@Override
	public String toString() {
		return get_ID() + " - "+getValue()+ " - " +getName();
	}	
	
}
