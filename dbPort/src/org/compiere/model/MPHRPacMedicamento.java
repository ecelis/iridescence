package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;

public class MPHRPacMedicamento extends X_PHR_PacMedicamento {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static CLogger log = CLogger.getCLogger(MPHRPacMedicamento.class);

	public MPHRPacMedicamento(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	public MPHRPacMedicamento(Properties ctx, int PHR_PacMedicamento_ID,
			String trxName) {
		super(ctx, PHR_PacMedicamento_ID, trxName);
	}
	
	/**
	 * Lista de los medicamentos que el paciente se ha automedicado.
	 * @param ctx
	 * @param pacienteId
	 * @return
	 */
	public static List<MPHRPacMedicamento> getMedications(Properties ctx, int pacienteId) {
		List <MPHRPacMedicamento> lista = new ArrayList<MPHRPacMedicamento>();
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		
		sql.append(" SELECT * FROM PHR_PacMedicamento WHERE ")
		.append(" PHR_PacMedicamento.IsActive = 'Y' ")
		.append(" AND PHR_PacMedicamento.EXME_Paciente_ID = ? ")
		.append(" ORDER BY PHR_PacMedicamento.FechaIni, PHR_PacMedicamento.Created DESC ");
		
		
		PreparedStatement	pstmt = null;
		ResultSet rs = null;
		
		try {

			pstmt = DB.prepareStatement(sql.toString(), null);

			pstmt.setInt(1, pacienteId);
			
			rs = pstmt.executeQuery();
			while(rs.next()){
				MPHRPacMedicamento pmed = new MPHRPacMedicamento(ctx, rs, null);
				lista.add(pmed);
			}
        } catch (SQLException e) {
            log.log(Level.SEVERE, " getMedications(Properties ctx, int pacienteId) ", e);
            
        } finally {
            try {
                if (rs != null)
                    rs.close();

                if (pstmt != null)
                    pstmt.close();
            } catch (SQLException e) {
                log.log(Level.SEVERE, "closing pstmt & rs ", e);
                
            }

            rs = null;
            pstmt = null;
        }

		return lista;

	}

}
