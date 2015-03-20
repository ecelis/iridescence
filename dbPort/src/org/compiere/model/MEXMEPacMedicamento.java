package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.DB;

public class MEXMEPacMedicamento extends X_EXME_PacMedicamento {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	 /** Static logger           */
    private static CLogger s_log = CLogger.getCLogger(MEXMEPacMedicamento.class);
	
	public MEXMEPacMedicamento(Properties ctx, int EXME_PacMedicamento_ID,
			String trxName) {
		super(ctx, EXME_PacMedicamento_ID, trxName);
	}
	
	public MEXMEPacMedicamento(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	/**
	 * Lista de los medicamentos que el paciente se ha automedicado.
	 * @param ctx
	 * @param pacienteID
	 * @return
	 */
	public static List<MEXMEPacMedicamento> getMedicamentosPaciente(Properties ctx, int pacienteID) {
		List <MEXMEPacMedicamento> lista = new ArrayList<MEXMEPacMedicamento>();
		String sql = " SELECT * FROM EXME_PacMedicamento WHERE "
		      	   + " EXME_PacMedicamento.IsActive = 'Y' " 
		           + " AND EXME_PacMedicamento.EXME_Paciente_ID = ? ";
		           
		
		sql += " ORDER BY EXME_PacMedicamento.FechaIni, EXME_PacMedicamento.Created DESC";
		PreparedStatement	pstmt = null;
		ResultSet rs = null;
		
		try {

			pstmt = DB.prepareStatement(sql, null);

			pstmt.setInt(1, pacienteID);
			
			rs = pstmt.executeQuery();
			while(rs.next()){
				MEXMEPacMedicamento pmed = new MEXMEPacMedicamento(ctx, rs, null);
				lista.add(pmed);
			}
			
        } catch (SQLException e) {
            s_log.log(Level.SEVERE, "getMedicamentosPaciente ", e);
            
        } finally {
        	DB.close(rs, pstmt);

            rs = null;
            pstmt = null;
        }

		return lista;

	}
}
