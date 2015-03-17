package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.DB;

public class MEXMEDiagnosticosPac_MO extends X_EXME_MO_DiagnosticosPac{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** Static logger           */
	private static CLogger log = CLogger.getCLogger(MEXMEMO_PiezaDental.class);
	private String piezaDental = null;
	private String nombreDiagnostico = null;
	
	 /**
     * @param ctx
     * @param EXME_CitaMedica_ID
     * @param trxName
     */
    public MEXMEDiagnosticosPac_MO(Properties ctx, int MEXMEDiagnosticosPac_ID, String trxName) {
        super(ctx, MEXMEDiagnosticosPac_ID, trxName);
    }

    /**
     * @param ctx
     * @param rs
     * @param trxName
     */
    public MEXMEDiagnosticosPac_MO(Properties ctx, ResultSet rs, String trxName) {
        super(ctx, rs, trxName);
    }
    
    
    public String getPiezaDental() {
		return piezaDental;
	}

	public void setPiezaDental(String piezaDental) {
		this.piezaDental = piezaDental;
	}
	

	public String getNombreDiagnostico() {
		return nombreDiagnostico;
	}

	public void setNombreDiagnostico(String nombreDiagnostico) {
		this.nombreDiagnostico = nombreDiagnostico;
	}
	
	
	public static ArrayList<MEXMEDiagnosticosPac_MO> obtenerDiagnosticosPac(Properties ctx, String trxName, int pacienteID,
 			int citaID, ArrayList<String> piezasDentalesID, String mes, String year, String esOdontograma){

		ArrayList<MEXMEDiagnosticosPac_MO> resultado = new ArrayList<MEXMEDiagnosticosPac_MO>();
		
		StringBuffer sql = new StringBuffer();

		sql.append("SELECT EXME_MO_DiagnosticosPac.*, ");	
		if(piezasDentalesID.size()>0){
			sql.append(" EXME_MO_PiezaDental.Value as piezaDental, EXME_Diagnostico.Name as nombreDiagnostico ");
		}else{
			sql.append(" ' ' as piezaDental, EXME_Diagnostico.Name as nombreDiagnostico ");
		}
		sql.append(" FROM EXME_MO_DiagnosticosPac ");
		if(piezasDentalesID.size()>0){
			sql.append(" INNER JOIN EXME_MO_PiezaDental ON EXME_MO_PiezaDental.EXME_MO_PiezaDental_ID = EXME_MO_DiagnosticosPac.EXME_MO_PiezaDental_ID ");
		}
		sql.append(" INNER JOIN EXME_Diagnostico ON EXME_Diagnostico.EXME_Diagnostico_ID = EXME_MO_DiagnosticosPac.EXME_Diagnostico_ID ");
		sql.append(" WHERE EXME_MO_DiagnosticosPac.EXME_Paciente_ID = ");
		sql.append(pacienteID);
		sql.append(" AND EXTRACT(MONTH FROM EXME_MO_DiagnosticosPac.Fecha_Diagnostico) = ");
		sql.append(mes);
		sql.append(" AND EXTRACT(YEAR FROM EXME_MO_DiagnosticosPac.Fecha_Diagnostico)  = ");
		sql.append(year);
		//if(piezasDentalesID.size()>0){
			sql.append(" AND EXME_MO_DiagnosticosPac.EsOdontograma = '");
			sql.append(esOdontograma);
			sql.append("' ");
		//}
		
		if(citaID!=0){
			sql.append(" AND EXME_MO_DiagnosticosPac.EXME_CitaMedica_ID = ");
			sql.append(citaID);
		}
		if(piezasDentalesID.size()>0){
			sql.append(" AND EXME_MO_DiagnosticosPac.EXME_MO_PiezaDental_ID IN ( ");
			for(int i=0; i <piezasDentalesID.size(); i++){
				 String piezaDentalID = piezasDentalesID.get(i).toString();
				 if((i+1)==piezasDentalesID.size()){
					 sql.append(piezaDentalID);
				 }else{
					 sql.append(piezaDentalID + " , ");
				 }
			 }
			 sql.append(" ) ");
		}else{
			sql.append(" AND (EXME_MO_DiagnosticosPac.EXME_MO_PiezaDental_ID IN (0) OR EXME_MO_DiagnosticosPac.EXME_MO_PiezaDental_ID IS NULL)");
		}
		
		
		sql = new StringBuffer(MEXMELookupInfo.addAccessLevelSQL(ctx, sql.toString(), "EXME_MO_DiagnosticosPac"));
		sql.append(" ORDER BY EXME_MO_DiagnosticosPac.Fecha_Diagnostico, EXME_MO_DiagnosticosPac.EXME_Diagnostico_ID, EXME_MO_DiagnosticosPac.Description");


		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			 pstmt = DB.prepareStatement(sql.toString(), trxName);
			 rs = pstmt.executeQuery();
			 while (rs.next()) {
				 MEXMEDiagnosticosPac_MO obj = new MEXMEDiagnosticosPac_MO(ctx, rs, trxName);
				 obj.setPiezaDental(rs.getString("piezaDental"));
				 obj.setNombreDiagnostico(rs.getString("nombreDiagnostico"));
				 resultado.add(obj); 
			 }
		}catch (Exception e) {
			log.log(Level.SEVERE, sql.toString(), e);
		}finally {
			DB.close(rs,pstmt);
			pstmt = null;
			rs =null;
		}
		return resultado;
	}



}
