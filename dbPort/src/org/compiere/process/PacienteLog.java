package org.compiere.process;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.logging.Level;

import org.compiere.model.MEXMELookupInfo;
import org.compiere.model.MEXMEPaciente;
import org.compiere.model.X_EXME_PacienteLog;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.Validacion;

/**
 *  @author     Noelia Correa
 */

public class PacienteLog  extends SvrProcess{

	//Parametros
	private String Name = null;
    private String Nombre2 = "";
    private String Apellido1 = null;
    private String Apellido2 = null;
    private Timestamp FechaNac = null;
    private String Motivo = null;
    
    
	
	protected void prepare() {
		
		ProcessInfoParameter[] para = getParameter();
		
		for (int i = 0; i < para.length; i++) {
			
			String name = para[i].getParameterName();
			
			if (para[i].getParameter() == null)
				;
			else if (name.equals("Name"))
				Name = para[i].getParameter().toString().trim().toUpperCase();
			else if (name.equals("Nombre2"))
				Nombre2 = para[i].getParameter().toString().trim().toUpperCase();
			else if (name.equals("Apellido1"))
				Apellido1 = para[i].getParameter().toString().trim().toUpperCase();
			else if (name.equals("Apellido2"))
                Apellido2 = para[i].getParameter().toString().trim().toUpperCase();
			else if (name.equals("FechaNac"))
				FechaNac = (Timestamp)para[i].getParameter();
			else if (name.equals("Motivo"))
				Motivo = para[i].getParameter().toString();
            else
                log.log(Level.SEVERE, "Unknown Parameter: " + name);
		}
	}
	
	
	
	protected String doIt()	throws Exception {
		
		int no = 0;
		int noErrors = 0;
		int EXME_Paciente_ID = 0;
        String lastName = null;
        String lastFechaNac = null;
        ResultSet rs = null;
        StringBuilder errors;
        MEXMEPaciente paciente = null;
        
        try{   
        
        	errors = validParam();
        	
        	if (errors==null){
        		
        		EXME_Paciente_ID = getRecord_ID();
        		
        		StringBuilder sql = new StringBuilder ("SELECT EXME_Paciente.* FROM EXME_Paciente ")
										   .append("WHERE EXME_Paciente_ID = ? ");
        		sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(getCtx(), sql.toString(), MEXMEPaciente.Table_Name));
					
        		PreparedStatement pstmt = DB.prepareStatement(sql.toString(), null);
        		pstmt.setInt(1,EXME_Paciente_ID); 
        		rs = pstmt.executeQuery();
		    
        		while (rs.next()){    
        			paciente = new MEXMEPaciente(getCtx(), rs, get_TrxName());
        		}
        		
        		rs.close();
        		
        		if(paciente!=null){
        			lastName = paciente.getNombre_Pac();
                    lastFechaNac = Constantes.getSdfFecha().format(paciente.getFechaNac());  
            		
            		paciente.setFechaNac(FechaNac);
            		paciente.setApellido1(Apellido1);
            		paciente.setApellido2(Apellido2);
            		paciente.setName(Name);
            		paciente.setNombre2(Nombre2);
            		            		   			
            		if(!paciente.save()){
            			log.finer("Not Update Paciente = " + no);
            			noErrors++;                 		
            		}
    				
            		if(!lastName.equals(paciente.getNombre_Pac()) || !Constantes.getSdfFecha().format(FechaNac).equals(lastFechaNac)){
            			
            			X_EXME_PacienteLog pacLog = new X_EXME_PacienteLog(getCtx(), 0, get_TrxName());
        				
                		pacLog.setNewName(paciente.getNombre_Pac());
                		pacLog.setOldName(lastName);
                		pacLog.setMotivo(Motivo);
                		pacLog.setEXME_Paciente_ID(EXME_Paciente_ID);
        				
                		if(!pacLog.save()){
                			log.finer("Not Update Paciente Log = " + no);
                			noErrors++;                 		
                		}
            		}else{
            			log.finer("Not Update Paciente Log = " + no);
            			addLog("No se detecto ningun cambio en Nombre del Paciente");
            		}        		
        		}
        		
        	}else{
        		addLog(errors.toString());
        		noErrors++;
        	}
        	
        
        }catch (SQLException e){
			try {
				if (rs != null)
					rs.close();				
			}catch (SQLException ex){
			}
			log.log(Level.SEVERE, "doIt", e);
			rs = null;		
			throw new Exception("doIt", e);
		} finally {
			if (rs != null)
				rs.close();
			rs = null;			
		}
		
		if (noErrors == 0){ 
			addLog("La actualizaci#n de Datos del Paciente se realizo satisfactoriamente");
		}else{
			addLog("Los datos no fueron actualizados");
		}
				
		return "";
	}	
	
	protected StringBuilder validParam(){
		
		if(!Validacion.isLetter(Name, true) || (Nombre2!=null?!Validacion.isLetter(Nombre2, true):false)
				|| !Validacion.isLetter(Apellido1, true) || !Validacion.isLetter(Apellido2, true)){
			return (new StringBuilder("Error en los datos del paciente introducidos"));
		}else{
			return null;
		}
	}
	
}
	
