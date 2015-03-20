package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;


/**
 * Modelo de Condiciones medicas del paciente
 * @author rsolorzano
 *
 */
public class MEXMECondMedica extends X_EXME_CondMedica{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MEXMECondMedica(Properties ctx, int EXME_CondMedica_ID,String trxName) {
		super(ctx, EXME_CondMedica_ID, trxName);
	}
	
	public MEXMECondMedica(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	/**	Static Logger				*/
	private static CLogger		s_log = CLogger.getCLogger (MEXMECondMedica.class);
	
	private String diagnostico=null;
	
	public String getDiagnostico() {
		return diagnostico;
	}

	public void setDiagnostico(String diagnostico) {
		this.diagnostico = diagnostico;
	}

	/**
	 * Regresa el listado de condiciones medicas de un paciente
	 * @param ctx                Propiedades
	 * @param pacienteID         ID del paciente
	 * @param trxName            Nombre de la transaccion
	 * 
	 */
    public static ArrayList<String> getCondPaciente(Properties ctx,int pacienteID, String trxName)
    {
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		ArrayList<String> array = new ArrayList<String>();
		
		
		try{
			
			
			sql.append(" select dia.value ")
			.append(" from exme_actpaciente act ")
			.append(" inner join EXME_ACTPACIENTEDIAG actdiag on act.exme_actpaciente_id = actdiag.exme_actpaciente_id and actdiag.padecimiento = 'Y' and actdiag.isactive = 'Y' ")
			.append(" inner join exme_diagnostico dia on actdiag.exme_diagnostico_id = dia.exme_diagnostico_id ")
			.append(" where act.exme_paciente_id = ? ");
			 
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, pacienteID);
			rs = pstmt.executeQuery();
			while (rs.next()){
				array.add(rs.getString(1));
			}
			
			
		}
		catch(Exception e){
    		s_log.log(Level.SEVERE, sql.toString(), e);
    		
		}
		finally{
			DB.close(rs, pstmt);
			sql = null;
			pstmt = null;
			rs = null;
		}
		
		return array;
    }

}
