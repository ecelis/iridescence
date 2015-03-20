package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;
import java.util.logging.Level;

import org.apache.struts.action.ActionError;
import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;

public class MEXMEIndicacionesPac_MO extends X_EXME_MO_IndicacionesPac{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static CLogger log = CLogger.getCLogger (MEXMEIndicacionesPac_MO.class);
	private String piezaDental = null;
	 
	public MEXMEIndicacionesPac_MO(Properties ctx, int indicacionID, String trxName) {
		super(ctx, indicacionID, trxName);
	}
	 
	public MEXMEIndicacionesPac_MO(Properties ctx, ResultSet rs, String trx) {
		super(ctx, rs, trx);
	}
 	 
	public String getPiezaDental() {
		return piezaDental;
	}

	public void setPiezaDental(String piezaDental) {
		this.piezaDental = piezaDental;
	}

	public static ArrayList<MEXMEIndicacionesPac_MO> obtenerIndicacionesPac(Properties ctx, String trxName, int pacienteID,
			 			int citaID, ArrayList<String> piezasDentalesID, String tipoIndicacion, String mes, String year, String esOdontograma){
		 
		 ArrayList<MEXMEIndicacionesPac_MO> resultado = new ArrayList<MEXMEIndicacionesPac_MO>();
		 
		 StringBuffer sql = new StringBuffer();
		 
		 sql.append(" SELECT EXME_MO_IndicacionesPac.*, ");
		 if(piezasDentalesID.size()>0){
			 sql.append(" EXME_MO_PiezaDental.Value as piezaDental FROM EXME_MO_IndicacionesPac ");
		 }else{
			 sql.append(" ' ' as piezaDental FROM EXME_MO_IndicacionesPac ");
		 }
		 
		 if(piezasDentalesID.size()>0){
			 sql.append(" INNER JOIN EXME_MO_PiezaDental ON EXME_MO_PiezaDental.EXME_MO_PiezaDental_ID = EXME_MO_IndicacionesPac.EXME_MO_PIEZADENTAL_ID "); 
		 }
		 sql.append(" WHERE EXME_MO_IndicacionesPac.EXME_Paciente_ID = ");
		 sql.append(pacienteID);
		 sql.append(" AND UPPER(EXME_MO_IndicacionesPac.TipoIndicacion) = UPPER('" + tipoIndicacion + "')");
		 sql.append(" AND EXTRACT(MONTH FROM EXME_MO_IndicacionesPac.Fecha_Indicacion) = " + mes);
		 sql.append(" AND EXTRACT(YEAR FROM EXME_MO_IndicacionesPac.Fecha_Indicacion) = " + year);
		 //if(piezasDentalesID.size()>0){
			 sql.append(" AND EXME_MO_IndicacionesPac.EsOdontograma = '");
			 sql.append(esOdontograma);
			 sql.append("' "); 
		 //}
		 if(citaID!=0){
			 sql.append(" AND EXME_MO_IndicacionesPac.EXME_CitaMedica_ID = ");
			 sql.append(citaID);
		 }
		 if(piezasDentalesID.size()>0){
			 sql.append(" AND EXME_MO_IndicacionesPac.EXME_MO_PiezaDental_ID IN ( ");
			 for(int i=0; i <piezasDentalesID.size(); i++){
				 String piezaDentalID = piezasDentalesID.get(i).toString();
				 if((i+1)==piezasDentalesID.size()){
					 sql.append(piezaDentalID);
				 }else{
					 sql.append(piezaDentalID + " , ");
				 }
			 }
			 sql.append(" )");
		 }else{
			 sql.append(" AND (EXME_MO_IndicacionesPac.EXME_MO_PiezaDental_ID IN (0) OR EXME_MO_IndicacionesPac.EXME_MO_PiezaDental_ID IS NULL)");
		 }
		 
		 sql = new StringBuffer(MEXMELookupInfo.addAccessLevelSQL(ctx, sql.toString(), "EXME_MO_IndicacionesPac"));
		 sql.append(" ORDER BY EXME_MO_IndicacionesPac.Fecha_Indicacion, EXME_MO_IndicacionesPac.Description ");
		 
		 PreparedStatement pstmt = null;
		 ResultSet rs = null;
	        
		 try {
			 pstmt = DB.prepareStatement(sql.toString(), trxName);
			 rs = pstmt.executeQuery();
			 while (rs.next()) {
				 MEXMEIndicacionesPac_MO obj = new MEXMEIndicacionesPac_MO(ctx, rs, trxName);
				 obj.setPiezaDental(rs.getString("piezaDental"));
				 resultado.add(obj); 
			 }
			 
			 }catch (Exception e) {
	    		log.log(Level.SEVERE, sql.toString(), e);
	    		
	    		
	    	 }finally {
	    		 DB.close(rs, pstmt);
	    		
	    	 }
	        return resultado;
	    }

	
	
	 public static ActionError guardarHigiene (Properties ctx,Integer paciente, Integer nivelHigiene, Boolean isOdo) throws Exception{

		 MEXMEIndicacionesPac_MO ind = null;
		 ActionError error = null;

	        try {
	        	
	        	
	        	ind = new MEXMEIndicacionesPac_MO(ctx, 0, null);
	        	
	        	ind.setEXME_Paciente_ID(paciente.intValue());
	        	ind.setEsOdontograma(isOdo.booleanValue());
	        	ind.setFecha_Indicacion(DB.getTimestampForOrg(ctx));
	        	ind.setTipoIndicacion("HIG");
	        	ind.setDescription(nivelHigiene.toString());
	        	
	        	if(!ind.save(null)){      	        			
	        		error = new ActionError("error.ts.noguardado");
	        	}
	            	           

			} catch (Exception e) {
				
				e.printStackTrace();
			}
	       

	        return error;
	    }    
	 
	 
	 public static Integer getHigieneBucal (Integer paciente) throws Exception{
		 
		 Integer hig = new Integer(1);

	      StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		  
	      sql.append(" 	select description")
	         .append(" 	from exme_mo_indicacionespac")
             .append(" 	where exme_paciente_id = ?")
	         .append(" 	and tipoindicacion = 'HIG'")
	         .append(" 	order by created desc");
	      

			PreparedStatement pstmt = null;
			ResultSet rs = null;

	      try {
				pstmt = DB.prepareStatement(sql.toString(), null);
				pstmt.setInt(1, paciente.intValue());
				rs = pstmt.executeQuery();
//				Pregunta_VO preg = null;
				if (rs.next()) {

					hig = new Integer(rs.getString(1));
					
				}

			} catch (Exception e) {
				log.log(Level.SEVERE, "While closing pstmt and result " ,e);
			} finally {
				DB.close(rs, pstmt);	
			}

			return hig;
		}
	
}
