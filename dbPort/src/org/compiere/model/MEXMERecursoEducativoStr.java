package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.apache.commons.collections.CollectionUtils;
import org.compiere.util.CLogger;
import org.compiere.util.DB;

/**
 * Recurso educativo de stroke
 * 
 * @author odelarosa
 * 
 */
public class MEXMERecursoEducativoStr extends X_EXME_RecursoEducativoStr {

	private static final long serialVersionUID = 5823051606186477701L;
	private static CLogger s_log = CLogger.getCLogger(MEXMERecursoEducativoStr.class);

	/**
	 * @param ctx
	 * @param EXME_RecursoEducativoStr_ID
	 * @param trxName
	 */
	public MEXMERecursoEducativoStr(Properties ctx, int EXME_RecursoEducativoStr_ID, String trxName) {
		super(ctx, EXME_RecursoEducativoStr_ID, trxName);
	}

	/**
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MEXMERecursoEducativoStr(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	/**
	 * Recursos por stroke
	 * 
	 * @param ctx
	 *            Contexto
	 * @param orig
	 *            Diagnosticos del paciente
	 * @param trxName
	 *            Trx
	 * @return
	 */
	public static List<MEXMERecursoEducativo> getRecursos(Properties ctx, List<Integer> orig, String trxName) {
		List<MEXMERecursoEducativo> recursos = new ArrayList<MEXMERecursoEducativo>();

		List<Integer> ids = joinDiag(ctx, orig, true, trxName);

		if (ids.size() > 0) {
			List<MEXMERecursoEducativoStr> strs = get(ctx, TYPE_IschemicStroke, trxName);
			for (MEXMERecursoEducativoStr str : strs) {
				MEXMERecursoEducativo re = new MEXMERecursoEducativo(ctx, str.getEXME_RecursoEducativo_ID(), trxName);
				if (re.isActive()) {
					recursos.add(re);
				}
			}
		}

		ids = joinDiag(ctx, orig, false, trxName);

		if (ids.size() > 0) {
			List<MEXMERecursoEducativoStr> strs = get(ctx, TYPE_HemorragicStroke, trxName);
			for (MEXMERecursoEducativoStr str : strs) {
				MEXMERecursoEducativo re = new MEXMERecursoEducativo(ctx, str.getEXME_RecursoEducativo_ID(), trxName);
				if (re.isActive()) {
					recursos.add(re);
				}
			}
		}

		return recursos;
	}

	/**
	 * Interseccion de diagnosticos del paciente por tipo de stroke
	 * 
	 * @param ctx
	 *            COntexto
	 * @param orig
	 *            Diagnosticos del paciente
	 * @param ischemic
	 *            Es o no Ischemic
	 * @param trxName
	 *            Trx
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Integer> joinDiag(Properties ctx, List<Integer> orig, boolean ischemic, String trxName) {
		List<Integer> ids = new ArrayList<Integer>();
		// Se cambio el campo Stroke ala tabla EXME_Group_Diag
		//List<Integer> tmp = MDiagnostico.getStroke(ctx, ischemic ? TYPE_IschemicStroke : TYPE_HemorragicStroke, trxName);
		List<Integer> tmp = MEXMEGroupDiag.getStroke(ctx, ischemic ? TYPE_IschemicStroke : TYPE_HemorragicStroke, trxName);

		ids.addAll(CollectionUtils.intersection(orig, tmp));

		return ids;
	}

	/**
	 * Recursos educativos por tipo de stroke
	 * 
	 * @param ctx
	 * @param type
	 * @param trxName
	 * @return
	 */
	public static List<MEXMERecursoEducativoStr> get(Properties ctx, String type, String trxName) {
		StringBuilder st = new StringBuilder("SELECT * FROM EXME_RecursoEducativoStr ");
		st.append(" where type = ? ");
		st = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, st.toString(), "EXME_RecursoEducativoStr"));
		List<MEXMERecursoEducativoStr> lista = new ArrayList<MEXMERecursoEducativoStr>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(st.toString(), trxName);
			pstmt.setString(1, type);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				lista.add(new MEXMERecursoEducativoStr(ctx, rs, trxName));
			}
			
		} catch (Exception e) {
			s_log.log(Level.SEVERE, st.toString(), e);
		} finally {
			DB.close(rs, pstmt);
		}
		return lista;
	}

	/**
	 * Recursos educativos por tipo y recurso educativo
	 * 
	 * @param ctx
	 * @param type
	 * @param EXME_RecursoEducativo_ID
	 * @param trxName
	 * @return
	 */
	public static List<MEXMERecursoEducativoStr> get(Properties ctx, String type, int EXME_RecursoEducativo_ID, String trxName) {
		StringBuilder st = new StringBuilder("SELECT * FROM EXME_RecursoEducativoStr ");
		st.append(" where type = ? and EXME_RecursoEducativo_ID = ? ");
		st = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, st.toString(), "EXME_RecursoEducativoStr"));
		List<MEXMERecursoEducativoStr> lista = new ArrayList<MEXMERecursoEducativoStr>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(st.toString(), trxName);
			pstmt.setString(1, type);
			pstmt.setInt(2, EXME_RecursoEducativo_ID);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				lista.add(new MEXMERecursoEducativoStr(ctx, rs, trxName));
			}
			
		} catch (Exception e) {
			s_log.log(Level.SEVERE, st.toString(), e);
		} finally {
			DB.close(rs, pstmt);
		}
		return lista;
	}

	public static List<MEXMERecursoEducativoStr> get(Properties ctx, int EXME_RecursoEducativo_ID, String trxName) {
		StringBuilder st = new StringBuilder("SELECT * FROM EXME_RecursoEducativoStr ");
		st.append(" where EXME_RecursoEducativo_ID = ? ");
		st = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, st.toString(), "EXME_RecursoEducativoStr"));
		List<MEXMERecursoEducativoStr> lista = new ArrayList<MEXMERecursoEducativoStr>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(st.toString(), trxName);
			pstmt.setInt(1, EXME_RecursoEducativo_ID);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				lista.add(new MEXMERecursoEducativoStr(ctx, rs, trxName));
			}
			
		} catch (Exception e) {
			s_log.log(Level.SEVERE, st.toString(), e);
		} finally {
			DB.close(rs, pstmt);
		}
		return lista;
	}

}
