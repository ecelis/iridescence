package org.compiere.util.dwr;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.model.Banderas;
import org.compiere.model.MEXMELookupInfo;
import org.compiere.model.MEXMEPaciente;
import org.compiere.util.CLogger;
import org.compiere.util.DB;

public class IngresoPacienteAjax {
	
	/**	Static Logger				
	 */
	private static CLogger	s_log = CLogger.getCLogger (IngresoPacienteAjax.class);
	
	private Properties ctx;
	
	public IngresoPacienteAjax(){
		
	}
	
	public Properties getCtx() {
		return ctx;
	}

	public void setCtx(Properties ctx) {
		this.ctx = ctx;
	}

	/**
	 * Obtenemos las historias repetidas a partir de ciertos criterios.
	 * @param ctx
	 * @param where
	 * @param all Indica si se retornan tambien inactivos o solo activos.
	 * @return
	 */
	public String getPacientes(Properties ctx,String nombre1, String nombre2, String apellido1, String apellido2, String curp, int pacienteID, String fechaNac) {

		StringBuilder sql = new StringBuilder(100);
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String historias = null;		

		sql.append(" SELECT EXME_Paciente.Value FROM EXME_Paciente WHERE ");
		
		if(pacienteID>0) {
			sql.append(" EXME_Paciente.EXME_Paciente_ID <> ? AND ");
		}
		if (DB.isOracle()) {	
			sql.append(" trunc(EXME_Paciente.FechaNac, 'dd') = to_date(?, 'dd/mm/yyyy') ");
		} else if (DB.isPostgreSQL()) {
			sql.append(" DATE_TRUNC('day', EXME_Paciente.FechaNac) = to_date(?, 'dd/mm/yyyy') ");
		}
		sql.append(" AND UPPER(TRIM(EXME_Paciente.Apellido1)) = UPPER(?) ")
		.append(" AND UPPER(TRIM(EXME_Paciente.Apellido2)) =  UPPER(?) ")
		.append(" AND UPPER(TRIM(EXME_Paciente.name)) = UPPER(?) ")
		.append(" AND ( EXME_Paciente.Nombre2 is null OR UPPER(TRIM(EXME_Paciente.Nombre2)) = UPPER(?) ) ")		
		.append(" AND ( EXME_Paciente.CURP is null OR UPPER(EXME_Paciente.CURP) LIKE UPPER(?) ) ");
		
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", MEXMEPaciente.Table_Name));

		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			
			int i = 0;
			
			if(pacienteID>0){
				i++;
				pstmt.setInt(i, pacienteID);
			}
			
			pstmt.setString(1+i, fechaNac);
			pstmt.setString(2+i, apellido1.trim());
			pstmt.setString(3+i, apellido2.trim());
			pstmt.setString(4+i, nombre1.trim());
			pstmt.setString(5+i, nombre2.trim());
			pstmt.setString(6+i, curp!=null?curp.concat("%"):"");
						
			rs = pstmt.executeQuery();
			
			i = 0;
			
			while (rs.next()) {
				
				if(i==0)
					historias = " Existen registros con datos similares a los ingresados. \n Historias : ";//FIXME: Cambiar a properties
				else
					historias += ", ";
				
				historias += rs.getString("Value");				
				i++;
			}
			
			
		} catch (Exception e) {
			s_log.log(Level.SEVERE, "get", e);
		} finally {
			DB.close(rs,pstmt);
		}
		
		return historias;
	}

	
	
	public String decideBanderas(String ctaPacId, String numDoc, String liga) {
		
		String respuesta = "";
		
		if (Banderas.valueMap != null &&
	    	   Banderas.valueMap.size() > 0 && 
	           Banderas.valueMap.containsKey(Integer.parseInt(ctaPacId))) {
	     
	        
	        respuesta = "true|"+ctaPacId+"|"+liga+"|"+numDoc;
	    } else {
	    	respuesta = "false|"+ctaPacId+"|"+liga+"|"+numDoc;
	    }
		
		return respuesta;
	}

}
