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

/**
 * Clase para registrar el traslado de pacientes
 * Creado: 11/11/2009<p>
 *
 * @author rsolorzano
 */
public class MEXMETrasladoPac extends X_EXME_TrasladoPac{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static CLogger s_log = CLogger.getCLogger(MEXMETrasladoPac.class);

	public MEXMETrasladoPac(Properties ctx, int EXME_TrasladoPac_ID,
			String trxName) {
		super(ctx, EXME_TrasladoPac_ID, trxName);
	}
	
	public MEXMETrasladoPac(Properties ctx, ResultSet rs,
			String trxName) {
		super(ctx, rs, trxName);
	}
	
	private String nombrePac = null;
	private String institucion = null;
	private String fechaTrasladoStr = null;
	
	
	public String getNombrePac() {
		return nombrePac;
	}
	public void setNombrePac(String nombrePac) {
		this.nombrePac = nombrePac;
	}
	public String getInstitucion() {
		return institucion;
	}
	public void setInstitucion(String institucion) {
		this.institucion = institucion;
	}
	public String getFechaTrasladoStr() {
		return fechaTrasladoStr;
	}

	public void setFechaTrasladoStr(String fechaTrasladoStr) {
		this.fechaTrasladoStr = fechaTrasladoStr;
	}

	/**
	 * Regresa el hist�rico de traslados
	 * @param ctx                Propiedades
	 * @param trxName            Nombre de la transaccion
	 * @param ctaPacID           Cuenta Paciente
	 * 
	 */
    public static List<MEXMETrasladoPac> getHistoria(Properties ctx, String trxName)
    {
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		List<MEXMETrasladoPac> lista = new ArrayList<MEXMETrasladoPac>();
		
		try{
			
			
			sql.append("SELECT TRAS.*, PAC.NOMBRE_PAC AS PACIENTE, INS.NAME AS INSTITUCION FROM EXME_TRASLADOPAC TRAS ");
			sql.append("INNER JOIN EXME_PACIENTE PAC ON TRAS.EXME_PACIENTE_ID=PAC.EXME_PACIENTE_ID ");
			sql.append("INNER JOIN EXME_INSTITUCION INS ON TRAS.EXME_INSTITUCION_ID=INS.EXME_INSTITUCION_ID ");
			
			
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()){
				MEXMETrasladoPac obj = null;
				obj = new MEXMETrasladoPac(ctx, rs, null);
				obj.setNombrePac(rs.getString("PACIENTE"));
				obj.setInstitucion(rs.getString("INSTITUCION"));
				obj.setFechaTrasladoStr(Constantes.getSdfFecha().format(obj.getFechaTraslado()));
				
				lista.add(obj);

			}
			
		}
		catch(Exception e){
    		s_log.log(Level.SEVERE, sql.toString(), e);
    		
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
