package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.DB;

/**
 * @author odelarosa
 * 
 */
public class MEXMESpecialtyForm extends X_EXME_SpecialtyForm {

	private static CLogger s_log = CLogger.getCLogger(MEXMESpecialtyForm.class);
	private static final long serialVersionUID = -7631707316413761484L;

	/**
	 * Listado de especialidades por cuestionario
	 * 
	 * @param ctx
	 *            Contexto de la App
	 * @param questId
	 *            Cuestionario
	 * @param trxName
	 * @return
	 */
	public static List<MEXMESpecialtyForm> get(Properties ctx, int questId, String trxName) {

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ");
		sql.append("  f.* ");
		sql.append("FROM ");
		sql.append("  EXME_SpecialtyForm f ");
		sql.append("  INNER JOIN EXME_Especialidad e ");
		sql.append("  ON f.exme_especialidad_id = e.exme_especialidad_id ");
		sql.append("WHERE ");
		sql.append("  e.isactive = 'Y' AND ");
		sql.append("  f.isActive = 'Y' AND ");
		sql.append("  f.EXME_Cuestionario_ID = ? ");
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", MEXMESpecialtyForm.Table_Name, "f"));
		sql.append("ORDER BY ");
		sql.append("  e.name ");

		List<MEXMESpecialtyForm> list = new ArrayList<MEXMESpecialtyForm>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, questId);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				list.add(new MEXMESpecialtyForm(ctx, rs, trxName));
			}

		} catch (Exception e) {
			s_log.log(Level.SEVERE, null, e);
		} finally {
			DB.close(rs, pstmt);
		}
		return list;
	}

	/**
	 * @param ctx
	 * @param EXME_SpecialityForm_ID
	 * @param trxName
	 */
	public MEXMESpecialtyForm(Properties ctx, int EXME_SpecialityForm_ID, String trxName) {
		super(ctx, EXME_SpecialityForm_ID, trxName);
	}

	/**
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MEXMESpecialtyForm(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	/**
	 * Extendido para comparar entre cada objeto</br> Compara por especialidad
	 * solamente
	 * 
	 * @see org.compiere.model.PO#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object cmp) {
		if (cmp instanceof MEXMESpecialtyForm && cmp != null) {
			MEXMESpecialtyForm other = (MEXMESpecialtyForm) cmp;
			if (other.getEXME_Especialidad_ID() == getEXME_Especialidad_ID()) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

}
