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

public class MEXMEResidenciaRot extends X_EXME_ResidenciaRot{
	
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	private static CLogger s_log = CLogger.getCLogger(MEXMEResidenciaRot.class);

	public MEXMEResidenciaRot(Properties ctx, int EXME_ResidenciaRot_ID,
			String trxName) {
		super(ctx, EXME_ResidenciaRot_ID, trxName);
	}
	
	public MEXMEResidenciaRot(Properties ctx, ResultSet rs,
			String trxName) {
		super(ctx, rs, trxName);
	}
	
	private String medico = null;
	private String fechaInicioStr = null;
	private String fechaFinStr = null;
	public String getMedico() {
		return medico;
	}

	public void setMedico(String medico) {
		this.medico = medico;
	}
	public String getFechaInicioStr() {
		return fechaInicioStr;
	}
	public void setFechaInicioStr(String fechaInicioStr) {
		this.fechaInicioStr = fechaInicioStr;
	}
	public String getFechaFinStr() {
		return fechaFinStr;
	}
	public void setFechaFinStr(String fechaFinStr) {
		this.fechaFinStr = fechaFinStr;
	}
	
	
	/**
	 * Regresa el hist�rico de traslados
	 * @param ctx                Propiedades
	 * @param trxName            Nombre de la transaccion
	 * @param ctaPacID           Cuenta Paciente
	 * 
	 */
    public static List<MEXMEResidenciaRot> getHistoria(Properties ctx, String trxName)
    {
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		List<MEXMEResidenciaRot> lista = new ArrayList<MEXMEResidenciaRot>();
		
		try{
			
			
			sql.append("SELECT RES.*, MED.NOMBRE_MED FROM EXME_RESIDENCIAROT RES ");
			sql.append("INNER JOIN EXME_MEDICO MED ON RES.EXME_MEDICO_ID=MED.EXME_MEDICO_ID ");
			
			
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()){
				MEXMEResidenciaRot obj = null;
				obj = new MEXMEResidenciaRot(ctx, rs, null);
				obj.setMedico(rs.getString("NOMBRE_MED"));
				obj.setFechaInicioStr(Constantes.getSdfFecha().format(obj.getFechaInicio()));
				obj.setFechaFinStr(Constantes.getSdfFecha().format(obj.getFechaFin()));
				
				lista.add(obj);

			}
			
			
		}
		catch(Exception e){
    		s_log.log(Level.SEVERE, sql.toString(), e.getMessage());
		}
		finally{
			DB.close(rs, pstmt);
    		sql = null;
    		rs = null;
    		pstmt = null;
		}

		return lista;
    }

	
	

}
