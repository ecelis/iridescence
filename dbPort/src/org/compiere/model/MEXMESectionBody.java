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
 * Modelo para la tabla EXME_SectionBody
 * @author raul
 *
 */
public class MEXMESectionBody extends X_EXME_SectionBody implements BodyPart {

	/* serialVersionUID*/
	private static final long serialVersionUID = 601875770916825684L;
	/*Log*/
	private static CLogger log = CLogger.getCLogger (MEXMESectionBody.class);
	private String nameTrl;
	//private LinkedList<String> imagesSrc;
	//private LinkedList<String> coords;
	
	/**
	 * Constructor que recibe un ResultSet
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MEXMESectionBody(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}
	
	/**
	 * Constructor que recibe un id
	 * @param ctx
	 * @param EXME_SectionBody_ID
	 * @param trxName
	 */
	public MEXMESectionBody(Properties ctx, int EXME_SectionBody_ID, String trxName) {
		super(ctx, EXME_SectionBody_ID, trxName);
	}
	
	/**
	 * Regresa una lista con las secciones
	 * de la seccion pasada como parametro
	 * @return 
	 */
	public static ArrayList<MEXMESectionBody> getLstSection(Properties ctx, int EXME_SectionBody_ID, String trxName){
		StringBuilder sql = new StringBuilder();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<MEXMESectionBody> lstSystem = new ArrayList<MEXMESectionBody>();

		try{
			sql.append(" SELECT EXME_SectionBody.EXME_SectionBody_ID " )
			.append(" FROM EXME_SectionBody ")
			.append(" WHERE EXME_SectionBody.Ref_SectionBody_ID = ? ")
			.append(" AND EXME_SectionBody.IsActive = 'Y'");
			
			if(Env.getUserPatientID(ctx) < 0)
				sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, sql.toString(), "EXME_SectionBody"));
			sql.append(" ORDER BY EXME_SectionBody.Name ");
			
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, EXME_SectionBody_ID);
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
	
	/*public LinkedList<String> getImagesSrc(){
		if(imagesSrc == null){
			imagesSrc = new LinkedList<String>();
			if(getFrontImage() != null){
				imagesSrc.add(getFrontImage());
			}else{
				imagesSrc.add("");
			}
			if(getRightImage() != null){
				imagesSrc.add(getRightImage());
			}else{
				imagesSrc.add("");
			}
			if(getBackImage() != null){
				imagesSrc.add(getBackImage());
			}else{
				imagesSrc.add("");
			}
			if(getLeftImage() != null){
				imagesSrc.add(getLeftImage());
			}else{
				imagesSrc.add("");
			}
		}	
		return imagesSrc;
	}*/
	/**
	 * Obtiene el padre de la seccion, sea un sistema u otra seccion
	 * @return
	 */
	public BodyPart getParent(){
		if(getREF_SectionBody_ID() > 0){
			return new MEXMESectionBody(getCtx(), getREF_SectionBody_ID(), get_TrxName());
		}else
			return new MEXMESystemBody(getCtx(), getEXME_SystemBody_ID(), get_TrxName());
	}
	/*
	 * 
	 * @return
	 
	public LinkedList<String> getCoordsMap(){
		if(coords == null){
			coords = new LinkedList<String>();
			if(getFrontMap() != null){
				coords.add(getFrontMap());
			}else{
				coords.add("");
			}
			if(getRightMap() != null){
				coords.add(getRightMap());
			}else{
				coords.add("");
			}
			if(getBackMap() != null){
				coords.add(getBackMap());
			}else{
				coords.add("");
			}
			if(getLeftMap() != null){
				coords.add(getLeftMap());
			}else{
				coords.add("");
			}
		}	
		return imagesSrc;
	}
	*/
	/**
	 * Obtiene el nombre traducido si existe
	 */
	@Override
	public String getName(){
		if (nameTrl == null){
			Language lan = Env.getLanguage(getCtx());
			
			StringBuilder sql = new StringBuilder();
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try{
				sql.append(" SELECT trl.Name " )
				.append(" FROM EXME_SectionBody sb ")
				.append(" INNER JOIN EXME_SectionBody_TRL trl on (sb.EXME_SectionBody_ID = trl.EXME_SectionBody_ID) ")
				.append(" WHERE trl.IsTranslated = 'Y' ")
				.append(" AND trl.IsActive = 'Y' ")
				.append(" AND trl.AD_Language = ? ")
				.append(" AND trl.EXME_SectionBody_ID = ?");
				
				pstmt = DB.prepareStatement(sql.toString(), get_TrxName());
				pstmt.setString(1,	lan.getAD_Language());
				pstmt.setInt(2, getEXME_SectionBody_ID());
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
