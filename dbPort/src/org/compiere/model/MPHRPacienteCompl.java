package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;

public class MPHRPacienteCompl extends X_PHR_PacienteCompl {
	/** Static Logger */
	private static CLogger		slog				= CLogger.getCLogger(MPHRPacienteCompl.class);
	/**
	 * serialVersionUID
	 */
	private static final long	serialVersionUID	= -5065188854399767733L;

	public MPHRPacienteCompl(Properties ctx, int PHR_PacienteCompl_ID, String trxName) {
		super(ctx, PHR_PacienteCompl_ID, trxName);
	}

	public MPHRPacienteCompl(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	/**
	 * Regresa el Registro en la tabla PHR_PacienteCompl relacionado al paciente, o un registro
	 * nuevo si no existe aun la relacion
	 * 
	 * @return
	 */
	public static MPHRPacienteCompl getPacienteCompl(Properties ctx, int EXME_Paciente_ID, String trxName) {
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MPHRPacienteCompl compl = null;
		try {
			sql.append(" SELECT PHR_PACIENTECOMPL.PHR_PACIENTECOMPL_ID ");
			sql.append(" FROM PHR_PACIENTECOMPL ");
			sql.append(" WHERE PHR_PACIENTECOMPL.EXME_PACIENTE_ID = ? ");
			sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", MPHRPacienteCompl.Table_Name));
			sql.append(" ORDER BY PHR_PACIENTECOMPL.UPDATED DESC ");

			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, EXME_Paciente_ID);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				compl = new MPHRPacienteCompl(ctx, rs.getInt(1), trxName);
			} else {
				compl = new MPHRPacienteCompl(ctx, 0, trxName);
			}
		} catch (Exception e) {
			slog.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
		}
		return compl;
	}

}
