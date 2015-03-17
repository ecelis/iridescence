package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;

/**
 * @author odelarosa
 * @deprecated No se usara, no hay reemplazo
 */
public class MEXMEOrdenCuestionario extends X_EXME_OrdenCuestionario {

	private static CLogger s_log = CLogger.getCLogger(MEXMEOrdenCuestionario.class);
	private static final long serialVersionUID = -664980558682059907L;

	public static void delete(Properties ctx, int cuestionarioId, String trxName) {
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE ");
		sql.append("FROM ");
		sql.append("  exme_ordencuestionario oc ");
		sql.append("WHERE ");
		sql.append("  oc.exme_cuestionario_id = ? ");
		sql.append("AND oc.AD_Org_ID = ? ");
		PreparedStatement pstmt = null;

		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, cuestionarioId);
			pstmt.setInt(2, Env.getAD_Org_ID(ctx));
			pstmt.executeUpdate();

		} catch (Exception e) {
			s_log.log(Level.SEVERE, null, e);
		} finally {
			DB.close(null, pstmt);
		}
	}

	public static List<MEXMEOrdenCuestionario> get(Properties ctx, int cuestId, String trxName) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ");
		sql.append("  oc.* ");
		sql.append("FROM ");
		sql.append("  exme_ordencuestionario oc ");
		sql.append("  INNER JOIN exme_tipopregunta tp ");
		sql.append("  ON oc.exme_tipopregunta_id = tp.exme_tipopregunta_id left ");
		sql.append("  JOIN exme_cuestionariodt cdt ");
		sql.append("  ON oc.exme_cuestionariodt_id = cdt.exme_cuestionariodt_id left ");
		sql.append("  JOIN exme_pregunta p ");
		sql.append("  ON cdt.exme_pregunta_id = p.exme_pregunta_id ");
		sql.append("WHERE ");
		sql.append("  oc.exme_cuestionario_id = ? AND ");
		sql.append("  tp.isactive = 'Y' AND ");
		sql.append("  (cdt.isactive = 'Y' OR ");
		sql.append("  cdt.isactive IS NULL) AND ");
		sql.append("  (p.isactive = 'Y' OR ");
		sql.append("  p.isactive IS NULL) ");

		boolean orgLevel = orgLevel(ctx, cuestId, trxName);

		sql.append(" AND oc.AD_Org_ID = ? ");

		sql.append(" ORDER BY oc.seqNo ");
		List<MEXMEOrdenCuestionario> list = new ArrayList<MEXMEOrdenCuestionario>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, cuestId);
			pstmt.setInt(2, orgLevel ? Env.getAD_Org_ID(ctx) : 0);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				list.add(new MEXMEOrdenCuestionario(ctx, rs, trxName));
			}

		} catch (Exception e) {
			s_log.log(Level.SEVERE, null, e);
		} finally {
			DB.close(rs, pstmt);
		}
		return list;
	}

	public static List<MEXMEOrdenCuestionario> getOrder(Properties ctx, int cuestId, String trxName) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ");
		sql.append(" * ");
		sql.append("FROM ");
		sql.append("  exme_ordencuestionario oc ");
		sql.append("  INNER JOIN exme_cuestionariodt dt ");
		sql.append("  ON oc.exme_cuestionariodt_id = dt.exme_cuestionariodt_id ");
		sql.append("  INNER JOIN exme_pregunta p ");
		sql.append("  ON dt.exme_pregunta_id = p.exme_pregunta_id ");
		sql.append("WHERE ");
		sql.append("  oc.exme_cuestionario_id = ? AND ");
		sql.append("  oc.ad_org_id = ? AND ");
		sql.append("  oc.isselected = ");
		sql.append("'Y' ORDER BY ");
		sql.append("  oc.seqno ");

		boolean orgLevel = orgLevel(ctx, cuestId, trxName);

		List<MEXMEOrdenCuestionario> list = new ArrayList<MEXMEOrdenCuestionario>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, cuestId);
			pstmt.setInt(2, orgLevel ? Env.getAD_Org_ID(ctx) : 0);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				list.add(new MEXMEOrdenCuestionario(ctx, rs, trxName));
			}

		} catch (Exception e) {
			s_log.log(Level.SEVERE, null, e);
		} finally {
			DB.close(rs, pstmt);
		}
		return list;
	}

	public static boolean orgLevel(Properties ctx, int cuestId, String trxName) {
		boolean retValue = false;
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ");
		sql.append("  oc.* ");
		sql.append("FROM ");
		sql.append("  exme_ordencuestionario oc ");
		sql.append("WHERE ");
		sql.append("  oc.exme_cuestionario_id = ? ");
		sql.append("AND oc.AD_Org_ID = ? ");
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, cuestId);
			pstmt.setInt(2, Env.getAD_Org_ID(ctx));
			rs = pstmt.executeQuery();
			if (rs.next()) {
				retValue = true;
			}

		} catch (Exception e) {
			s_log.log(Level.SEVERE, null, e);
		} finally {
			DB.close(rs, pstmt);
		}
		return retValue;
	}

	private MEXMECuestionarioDt mexmeCuestionarioDt = null;

	/**
	 * @param ctx
	 * @param EXME_OrdenCuestionario_ID
	 * @param trxName
	 */
	public MEXMEOrdenCuestionario(Properties ctx, int EXME_OrdenCuestionario_ID, String trxName) {
		super(ctx, EXME_OrdenCuestionario_ID, trxName);
	}

	/**
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MEXMEOrdenCuestionario(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	public MEXMECuestionarioDt getEXME_CuestionarioDt() {
		if (mexmeCuestionarioDt == null && getEXME_CuestionarioDt_ID() > 0) {
			mexmeCuestionarioDt = new MEXMECuestionarioDt(getCtx(), getEXME_CuestionarioDt_ID(), null);
		}
		return mexmeCuestionarioDt;
	}

}
