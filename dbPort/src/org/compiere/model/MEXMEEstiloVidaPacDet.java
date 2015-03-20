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

public class MEXMEEstiloVidaPacDet extends X_EXME_EstiloVidaPacDet {

	private static final long serialVersionUID = 1L;
	private static CLogger log = CLogger.getCLogger(MEXMEEstiloVidaPaciente.class);
	
	 /**
     * @param ctx
     * @param EXME_EstiloVidaPacDet_ID
     * @param trxName
     */
    public MEXMEEstiloVidaPacDet(Properties ctx, int EXME_EstiloVidaPacDet_ID, String trxName) {
        super(ctx, EXME_EstiloVidaPacDet_ID, trxName);
    }

    /**
     * @param ctx
     * @param rs
     * @param trxName
     */
    public MEXMEEstiloVidaPacDet(Properties ctx, ResultSet rs, String trxName) {
        super(ctx, rs, trxName);
    }    
    
    private MActPacienteDiag actPacDiag = null;
    
    public MActPacienteDiag getActPacienteDiag() {
		if(actPacDiag == null){//FIXME : validar ID ?
			actPacDiag = new MActPacienteDiag(getCtx(), getEXME_ActPacienteDiag_ID(), get_TrxName());
		}
		return actPacDiag;
	}	

	/**
	 * Obtiene Nombre del Diagnostico
	 * @return String
	 */
	public String getNombreDiag() {
		return getActPacienteDiag() == null ? "" : actPacDiag.getDiagnostico() == null ? actPacDiag.getDiagnosis()
				: actPacDiag.getDiagnostico().getName();	
	}
    
    /**
	 * Lizeth de la Garza
	 * Obtener el historial cambios del estilo de vida
	 * @param ctx Contexto de la aplicacion
	 * @param patientID ID del paciente
	 * @param trxName Nombre de la transaccion
	 * @return List<MEXMEPacienteFumador> lista del historial
	 */
	public static List<MEXMEEstiloVidaPacDet> getHistoria(Properties ctx, int estiloVidaPacienteID, String trxName) {
		final List<MEXMEEstiloVidaPacDet> lst = new ArrayList<MEXMEEstiloVidaPacDet>();
		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append("SELECT EXME_EstiloVidaPacDet.* FROM EXME_EstiloVidaPacDet WHERE EXME_EstiloVidaPacDet.EXME_EstiloVidaPaciente_ID = ? ");
			
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		try {
			sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name));
			sql.append(" ORDER BY  EXME_EstiloVidaPacDet.FECHAFIN DESC, EXME_EstiloVidaPacDet.CREATED DESC");
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, estiloVidaPacienteID);

			rs = pstmt.executeQuery();
			while (rs.next()) { 			
				final MEXMEEstiloVidaPacDet estiloVida = new MEXMEEstiloVidaPacDet(ctx, rs, trxName);
				lst.add(estiloVida);
			}

		} catch (Exception e) {
			log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
		}
		
		return lst;		
	}
}
