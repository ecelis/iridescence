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

/**
 * @author odelarosa
 * 
 */
public class MEXMEGrupoCuestionario extends X_EXME_GrupoCuestionario {
	private static final long serialVersionUID = -6426297282096178031L;
	private static CLogger slog = CLogger.getCLogger(MEXMEGrupoCuestionario.class);

	/**
	 * 
	 * @param ctx
	 * @param sql
	 * @param lvb
	 * @param trxName
	 * @return
	 */
	public static List<Integer> getDetail(Properties ctx, Integer GroupID, String trxName) {

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Integer> lst = new ArrayList<Integer>();
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT ");
			sql.append("  cd.EXME_Cuestionario_ID ");
			sql.append("FROM ");
			sql.append("  EXME_GrupoCuestionarioDet cd ");
			sql.append("  INNER JOIN exme_cuestionario c ");
			sql.append("  ON cd.exme_cuestionario_id = c.exme_cuestionario_id ");
			sql.append("WHERE ");
			sql.append("  cd.EXME_GrupoCuestionario_ID = ? AND ");
			sql.append("  cd.isactive = 'Y' AND ");
			sql.append("  c.isactive = ");
			sql.append("'Y' ORDER BY ");
			sql.append("  cd.seqno ");

			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, GroupID);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				lst.add(rs.getInt("EXME_Cuestionario_ID"));
			}

		} catch (Exception e) {
			slog.log(Level.SEVERE, "getDetail", e);
		} finally {
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}

		return lst;
	}

	/**
	 * 
	 * Regresa un Form Group con la especialidad de sistema unico asignada
	 * 
	 * @param ctx
	 * @param value
	 * @param trxName
	 * @return true si existen valores con esa especialidad
	 */
	public static boolean isSingleSystemAdded(Properties ctx, String singleSysEsp, String trxName) {
		boolean singleSys = false;
		StringBuilder sb = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		ResultSet rs = null;
		PreparedStatement pstmt = null;

		sb.append("SELECT * FROM EXME_GrupoCuestionario WHERE SingleSysEsp = '");
		sb.append(singleSysEsp);
		sb.append("' AND isActive = 'Y'");
		sb.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", MEXMEGrupoCuestionario.Table_Name));

		try {
			pstmt = DB.prepareStatement(sb.toString(), trxName);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				singleSys = true;
			}
		} catch (SQLException ex) {
			slog.log(Level.SEVERE, "MEXMEGrupoCuestionario at method isSingleSystemAdded ", ex);
		} finally {
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}
		return singleSys;
	}

	private String multiSysVal = null;

	private String singleSysEspVal = null;

	/**
	 * @param ctx
	 * @param EXME_GrupoCuestionario_ID
	 * @param trxName
	 */
	public MEXMEGrupoCuestionario(Properties ctx, int EXME_GrupoCuestionario_ID, String trxName) {
		super(ctx, EXME_GrupoCuestionario_ID, trxName);
	}
	/**
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MEXMEGrupoCuestionario(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	public String getMultiSysVal() {
		return multiSysVal;
	}

	public String getSingleSysEspVal() {
		return singleSysEspVal;
	}

	public void setMultiSysVal(String multiSys) {
		if (multiSys != null && !multiSys.isEmpty()) {
			this.singleSysEspVal = null;
			this.multiSysVal = multiSys;
		}
	}

	public void setSingleSysEspVal(String singleSysEsp) {
		if (singleSysEsp != null && !singleSysEsp.isEmpty()) {
			this.singleSysEspVal = singleSysEsp;
			this.multiSysVal = null;
		}
	}

}