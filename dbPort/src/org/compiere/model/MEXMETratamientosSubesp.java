package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.DB;
/**
 * Clase para relacion entre Tratamientos y Sub Especialidad
 * @author Lizeth de la Garza
 *
 */
	
public class MEXMETratamientosSubesp extends X_EXME_TratamientosSubesp {
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static CLogger log = CLogger.getCLogger(MEXMETratamientosSubesp.class);
	 
	 private MEXMEEspecialidad especialidad = null;
	 
	 private MEXMETratamientos tratamiento = null;
	 
	 
	public MEXMEEspecialidad getEspecialidad() {
		if (especialidad == null) {
			especialidad = new MEXMEEspecialidad(getCtx(), getEXME_Especialidad_ID(), null);
		}
		return especialidad;
	}
	
	public MEXMEEspecialidad getTratamiento() {
		if (tratamiento == null) {
			tratamiento = new MEXMETratamientos(getCtx(), getEXME_Tratamientos_ID(), null);
		}
		return especialidad;
	}

	public void setEspecialidad(MEXMEEspecialidad especialidad) {
		this.especialidad = especialidad;
	}

	/**
     * @param ctx
     * @param EXME_TratamientosSubesp_ID
     * @param trxName
     */
    public MEXMETratamientosSubesp(Properties ctx, int EXME_TratamientosSubesp_ID, String trxName) {
        super(ctx, EXME_TratamientosSubesp_ID, trxName);
    }

    /**
     * @param ctx
     * @param rs
     * @param trxName
     */
    public MEXMETratamientosSubesp(Properties ctx, ResultSet rs, String trxName) {
        super(ctx, rs, trxName);
    }
    
    /**
     * Metodo que regresa las sub especialidades relacionadas a un tratamiento dado
     * @param Properties ctx Contexto
     * @param int tratamientoID Identificador del tratamiento
     * @param trxName Nombre de la Transaccion
     * @return ArrayList<MEXMETratamientosSubesp> 
     * Lista de Sub Especialidades
     * @author Lizeth de la Garza
     * 
     */
    public static ArrayList<MEXMETratamientosSubesp> obtenerSubEspecialidad(Properties ctx, int tratamientoID, 
    		String trxName){

		ArrayList<MEXMETratamientosSubesp> lista = new ArrayList<MEXMETratamientosSubesp>();
		
		StringBuffer sql = new StringBuffer();

		sql.append("SELECT tratam.* FROM EXME_TratamientosSubEsp tratam ")
		.append(" INNER JOIN EXME_Especialidad esp ON (esp.EXME_Especialidad_ID = tratam.EXME_Especialidad_ID ) ")
		.append(" WHERE tratam.EXME_Tratamientos_ID = ? ORDER BY esp.Name ");

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			 pstmt = DB.prepareStatement(sql.toString(), trxName);
			 pstmt.setInt(1, tratamientoID);
			 rs = pstmt.executeQuery();
			 while (rs.next()) {
				 MEXMETratamientosSubesp obj = new MEXMETratamientosSubesp(ctx, rs, null);
				 lista.add(obj); 
			 }
		}catch (Exception e) {
			log.log(Level.SEVERE, sql.toString(), e);
			
		}finally {
			DB.close(rs, pstmt);
			pstmt = null;
			rs = null;
	
		}
		return lista;
	}

}
