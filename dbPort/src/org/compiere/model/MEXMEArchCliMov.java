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

public class MEXMEArchCliMov extends X_EXME_ArchCliMov{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static CLogger s_log = CLogger.getCLogger(MEXMEArchCliMov.class);	
	
	public MEXMEArchCliMov(Properties ctx, int EXME_ArchCliMov_ID,
			String trxName) {
		super(ctx, EXME_ArchCliMov_ID, trxName);
	}
	
	public MEXMEArchCliMov(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}
	/**
	 * Metodo que obtiene y regresa la lista de documentos 
	 * de un tipo en especifico 
	 * solicitados en una determinada fecha.
	 * @author lhernandez
	 * @param ctx
	 * @param fechaSol
	 * @param tipoExpID
	 * @return lstDocsSolicitados
	 */
	public static List<MEXMEArchCliMov> getDocsSolicitados(Properties ctx, String fechaSol, int tipoExpID){
		List<MEXMEArchCliMov> lstDocsSolic = new ArrayList<MEXMEArchCliMov>();
	
		StringBuilder sql = new StringBuilder();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		sql.append("SELECT * FROM EXME_ArchCliMov WHERE IsActive = 'Y' " )
			.append(" AND TO_DATE (TO_CHAR (FechaSolicitud, 'dd/mm/yyyy') , 'dd/mm/yyyy') = TO_DATE (?, 'dd/mm/yyyy') ")
			.append(" AND EXME_TipoExped_ID = ? ")
			.append(" AND EnArchivo = 'Y' ");
		
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", "EXME_ArchCliMov"));
		
		try{
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setString(1, fechaSol);
			pstmt.setInt(2, tipoExpID);
			
			rs = pstmt.executeQuery();
			while(rs.next()){
				MEXMEArchCliMov archMov = new MEXMEArchCliMov(ctx, rs, null);
				//verificamos si tiene registro en EXME_Acervo y si las fechas de entrada y salida de archivo y acervo ya est�n registradas,
				// si no lo est�n o no tiene registro, se agrega a la lista
				if(archMov.getAcervo() != null){
					if(archMov.getAcervo().getFecha_IngAcervo() == null || archMov.getAcervo().getFecha_IngArch() == null || 
							archMov.getAcervo().getFecha_SalAcervo() == null || archMov.getAcervo().getFecha_SalArch() == null){
						lstDocsSolic.add(archMov);
					}
				} else {
					lstDocsSolic.add(archMov);
				}				
			}			
			
		} catch (Exception e) {			
			s_log.log(Level.SEVERE, "--getDocsSolicitados--", e);			
		} finally {
			DB.close(rs, pstmt);
		}
		
		return lstDocsSolic;
	}
	
	/**
	 * Metodo que obtiene y regresa un documento solicitado
	 * dado el EXME_ArcCli_ID
	 * @author lhernandez
	 * @param ctx
	 * @param archCliID
	 * @return docSol
	 */
	public static List<MEXMEArchCliMov> getDocSolicitado(Properties ctx, int archCliID){

		List<MEXMEArchCliMov> docSol = new ArrayList<MEXMEArchCliMov>();

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		 
		StringBuilder sql = new StringBuilder("SELECT * FROM EXME_ArchCliMov acm " )
		.append(" LEFT JOIN EXME_ArchCli ac ON (acm.EXME_ArchCli_ID = ac.exme_archcli_id ) ")	
		.append(" LEFT JOIN EXME_Acervo a ON (acm.EXME_ArchCliMov_ID  = a.exme_archclimov_id) ")
		.append(" WHERE acm.EXME_ArchCli_ID = ? ")
		.append(" AND acm.EnArchivo = 'Y' ")  
		.append(" AND (a.Fecha_SalAcervo IS NULL OR a.Fecha_SalArch IS NULL OR a.Fecha_IngArch IS NULL OR a.Fecha_IngAcervo IS NULL) ");

		try{
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setInt(1, archCliID);

			rs = pstmt.executeQuery();
			while(rs.next()){
				MEXMEArchCliMov archMov = new MEXMEArchCliMov(ctx, rs, null);
				docSol.add(archMov);

			}			

		} catch (Exception e) {			
			s_log.log(Level.SEVERE, "--getDocSolicitado--", e);			
		} finally {
			DB.close(rs, pstmt);
		}
		return docSol;
	}
	/**
	 * Obtenemos el registro del Acervo
	 * @return
	 */
	public  MEXMEAcervo getAcervo(){
		
		MEXMEAcervo acervo = null;
		
		StringBuilder sql = new StringBuilder(" SELECT * FROM EXME_Acervo ")
	      .append(" WHERE EXME_ArchCliMov_ID = ? ");
		PreparedStatement pstmt = null;
	    ResultSet rs = null;
		try {	
			pstmt = DB.prepareStatement (sql.toString(),null);
			pstmt.setInt(1, getEXME_ArchCliMov_ID());
			rs = pstmt.executeQuery ();
			if (rs.next())
				acervo = new MEXMEAcervo(getCtx(), rs, null);					
		}
		catch (Exception e) {
			s_log.log(Level.SEVERE, "--getAcervo--", e);
		} finally {
			DB.close(rs, pstmt);
		}
		
		return acervo;
	}
	
	/**
	 * Obtenemos la Especialidad
	 * @return especialidad
	 */
	public  MEXMEEspecialidad getEspecialidad(){
		
		MEXMEEspecialidad especialidad = null;
		
		StringBuilder sql = new StringBuilder("SELECT * FROM EXME_Especialidad ")
	      .append(" WHERE EXME_Especialidad_ID = ?");
		PreparedStatement pstmt = null;
	    ResultSet rs = null;
		try {	
			pstmt = DB.prepareStatement (sql.toString(),null);
			pstmt.setInt(1, getEXME_Especialidad_ID());
			rs = pstmt.executeQuery ();
			if (rs.next())
				especialidad = new MEXMEEspecialidad(getCtx(), rs, null);					
		}
		catch (Exception e) {
			s_log.log(Level.SEVERE, "--getEspecialidad--", e);
		} finally {
			DB.close(rs, pstmt);
		}
		if (especialidad == null) {
			especialidad = new MEXMEEspecialidad(getCtx(), 0, null);
		}
		
		return especialidad;
	}
	
	/**
	 * Obtener el value del EXME_ArchCli_ID.
	 * @return
	 */
	public MEXMEArchCli getArchCli(){

		MEXMEArchCli archCli = null;

		try {	
				archCli = new MEXMEArchCli(getCtx(), getEXME_ArchCli_ID(), null);					

		}catch (Exception e){
			s_log.log(Level.SEVERE, "--getArchCli--", e);
		}

		return archCli;
	}
	
	public MEXMEEstServ getEstacion(){
		MEXMEEstServ estacion = null;
		
		StringBuilder sql = new StringBuilder("SELECT * FROM EXME_EstServ e  ")
	      .append(" INNER JOIN EXME_EstServAlm a ON(e.exme_estserv_id=a.exme_estserv_id) ")
		  .append("WHERE a.m_warehouse_id = ? ");		
		PreparedStatement pstmt = null;
	    ResultSet rs = null;
		try {	
			pstmt = DB.prepareStatement (sql.toString(),null);
			pstmt.setInt(1, getM_Warehouse_ID());
			rs = pstmt.executeQuery ();
			if (rs.next())
				estacion = new MEXMEEstServ(getCtx(), rs, null);					
		}
		catch (Exception e) {
			s_log.log(Level.SEVERE, "--getEstacion--", e);
		} finally {
			DB.close(rs, pstmt);
		}
		
		return estacion;
	}
	
	
	public static int getNextValue(Properties ctx, int AD_Client_ID, String trxName) {

        int retValue = 0;
        
        StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
        
        sql.append("SELECT NVL(MAX(DOCUMENTNO),0)+1 AS DefaultValue FROM EXME_ArchCliMov WHERE isActive = 'Y' ");
        
        sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, sql.toString(),"EXME_ArchCliMov"));
        
        	//.append(AD_Client_ID);
        
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = DB.prepareStatement(sql.toString(), trxName);
            rs = pstmt.executeQuery();
            if (rs.next()) 
                retValue=rs.getInt(1);
            
    	} catch (Exception e) {
    		s_log.log(Level.SEVERE, sql.toString(), e);
    	} finally {
			DB.close(rs, pstmt);
		}
     
        return retValue;
    }
	
	
	public static MEXMEArchCliMov getExiste(Properties ctx, int EXME_Paciente_ID, int EXME_TipoExped_ID, String trxName){
		MEXMEArchCliMov result = null;
        StringBuilder sql = new StringBuilder();
        
         sql.append(" SELECT* FROM EXME_ArchCliMov ")
            .append(" WHERE EXME_ArchcliMov.Isactive = 'Y' AND EXME_ARchCliMov.EXME_Paciente_ID = ? ")
            .append(" AND EXME_ArchCliMov.EXME_TipoExped_ID = ?");
       
        sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, sql.toString(), "EXME_ArchCliMov"));
        
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = DB.prepareStatement(sql.toString(), trxName);
            pstmt.setInt(1, EXME_Paciente_ID);
            pstmt.setInt(2, EXME_TipoExped_ID);
            rs = pstmt.executeQuery();
            if (rs.next()) 
                result = new MEXMEArchCliMov(ctx, rs, trxName);
    
    	} catch (Exception e) {    		
    		s_log.log(Level.SEVERE, sql.toString(), e);
    	} finally {
			DB.close(rs, pstmt);
		}
		return result;
	}
	
	/* protected boolean afterSave(boolean newRecord, boolean success) {

		 
		 try {			
			 success = true;
			 if (newRecord){
				 MHistExp HistExp = new MHistExp(getCtx(), 0, null);
				 
				 HistExp.setEXME_Paciente_ID(getEXME_Paciente_ID());
				 HistExp.setFecha_Exp(getFechaSolicitud());
				 HistExp.setIsPrestado(true);		
				 
				 if(!HistExp.save()){
					 ActionErrors errores = new ActionErrors();   
					 errores.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.exp.nosave"));  
					 success = false;
					 }
			 	}
		 } catch (Exception e) {
			 	log.log(Level.SEVERE, "afterSave", e);
				log.saveError("Error", e.toString(), false);
				success = false;
			}
	    	
	    	return  success;
	    }*/
	
	public static List<MEXMEArchCliMov> getDocsSolxAlm(Properties ctx, String fechaSol, int tipoExpID){
		List<MEXMEArchCliMov> lstDocsSolic = new ArrayList<MEXMEArchCliMov>();
	
		StringBuilder sql = new StringBuilder();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		sql.append("SELECT * FROM EXME_ArchCliMov m  " )
			.append("INNER JOIN EXME_EstServAlm a ON(a.m_warehouse_id = m.m_warehouse_id) ")
			.append("INNER JOIN exme_estserv e ON(e.exme_estserv_id = a.exme_estserv_id)  ")
			.append(" AND TO_DATE (TO_CHAR (m.FechaSolicitud, 'dd/mm/yyyy') , 'dd/mm/yyyy') = TO_DATE (?, 'dd/mm/yyyy') ")
			.append(" AND m.EXME_TipoExped_ID = ? ")
			.append(" AND EnArchivo = 'Y' ")
			.append("Order by e.Name ASC ");
		
		//sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", "EXME_ArchCliMov"));
		
		try{
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setString(1, fechaSol);
			pstmt.setInt(2, tipoExpID);
			
			rs = pstmt.executeQuery();
			while(rs.next()){
				MEXMEArchCliMov archMov = new MEXMEArchCliMov(ctx, rs, null);
				//verificamos si tiene registro en EXME_Acervo y si las fechas de entrada y salida de archivo y acervo ya est�n registradas,
				// si no lo est�n o no tiene registro, se agrega a la lista
				if(archMov.getAcervo() != null){
					if(archMov.getAcervo().getFecha_IngAcervo() == null || archMov.getAcervo().getFecha_IngArch() == null || 
							archMov.getAcervo().getFecha_SalAcervo() == null || archMov.getAcervo().getFecha_SalArch() == null){
						lstDocsSolic.add(archMov);
					}
				} else {
					lstDocsSolic.add(archMov);
				}				
			}			
			
		} catch (Exception e) {			
			s_log.log(Level.SEVERE, "--getDocsSolicitados--", e);			
		} finally {
			DB.close(rs, pstmt);
		}
		
		return lstDocsSolic;
	}
}
