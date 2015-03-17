package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.compiere.util.DB;

/**
 * 
 * @author Expert
 *
 */
public class MEXMEPaqBaseAtributo extends X_EXME_PaqBaseAtributo {

	/** serialVersionUID */
	private static final long serialVersionUID = 6565480291648128759L;
	/** Diagnostico */
	private MDiagnostico diagnostico = null;
	/** Modificador */
	private X_EXME_Modifiers modifiers = null;
	
	/**
	 * Costructor
	 * 
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MEXMEPaqBaseAtributo(final Properties ctx, final ResultSet rs, final String trxName) {
		super(ctx, rs, trxName);
	}

	public MEXMEPaqBaseAtributo(final Properties ctx, final int id, final String trxName) {
		super(ctx, id, trxName);
	}

	public MDiagnostico getDiagnostico() {
		if (getAD_Table_ID() == X_EXME_Diagnostico.Table_ID
				&& diagnostico == null)
			diagnostico = new MDiagnostico(getCtx(), getRecord_ID(), null);
		return diagnostico;
	}

	public void setDiagnostico(final MDiagnostico diagnostico) {
		this.diagnostico = diagnostico;
	}

	public void setModifiers(final X_EXME_Modifiers modifiers) {
		this.modifiers = modifiers;
	}

	public X_EXME_Modifiers getModifiers() {
		if (getAD_Table_ID() == X_EXME_Modifiers.Table_ID && modifiers == null)
			modifiers = new X_EXME_Modifiers(getCtx(), getRecord_ID(), null);
		return modifiers;
	}
	
	/**
	 * Todos los paquetes por nivel de acceso activos
	 * 
	 * @param ctx
	 * @param trxName
	 * @return
	 */
	public static List<MEXMEPaqBaseAtributo> get(final Properties ctx,
			final int paqBaseDet, final int tableId, final String trxName) {

		if (ctx == null) {
			return null;
		}

		final StringBuilder sql = new StringBuilder();
		sql.append(" SELECT * FROM EXME_PaqBaseAtributo ").append(
				" WHERE EXME_PaqBaseAtributo.IsActive = 'Y' ").append(
				MEXMELookupInfo.addAccessLevelSQL(ctx, " ",
						MEXMEPaqBaseAtributo.Table_Name))
				.append(" AND EXME_PaqBaseAtributo.EXME_PaqBaseDet_ID = ? ")
				.append(" AND EXME_PaqBaseAtributo.AD_Table_ID = ? ")
				.append(" ORDER BY EXME_PaqBaseAtributo.EXME_PaqBaseAtributo_ID DESC ");
		final List<Integer> params = new ArrayList<Integer>();
		params.add(paqBaseDet);
		params.add(tableId);

		return get(ctx, sql.toString(), params, null);
	}


	/**
	 * Todos los paquetes por nivel de acceso activos
	 * 
	 * @param ctx
	 * @param trxName
	 * @return
	 */
	public static List<MEXMEPaqBaseAtributo> get(final Properties ctx,
			final int paqBaseDet, final String trxName) {

		if (ctx == null) {
			return null;
		}

		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT * FROM EXME_PaqBaseAtributo ").append(
				" WHERE EXME_PaqBaseAtributo.IsActive = 'Y' ").append(
				MEXMELookupInfo.addAccessLevelSQL(ctx, " ",
						MEXMEPaqBaseAtributo.Table_Name)).append(
				" AND EXME_PaqBaseAtributo.EXME_PaqBaseDet_ID = ? ").append(
				" ORDER BY EXME_PaqBaseAtributo.EXME_PaqBaseAtributo_ID DESC ");
		List<Integer> params = new ArrayList<Integer>();
		params.add(paqBaseDet);

		return get(ctx, sql.toString(), params, null);
	}

	/**
	 * Metdoos genericopara ejecutar una consulta y devuelva una lista de
	 * objetos MEXMEPaqBase
	 * 
	 * @param ctx
	 *            contexto Obligatorio
	 * @param sql
	 *            consulta
	 * @param params
	 *            parametros
	 * @param trxName
	 *            nombre de la transaccion
	 * @return List<MEXMEPaqBase>
	 */
	public static List<MEXMEPaqBaseAtributo> get(final Properties ctx, final String sql,
			final List<?> params, final String trxName) {

		List<MEXMEPaqBaseAtributo> resultados = new ArrayList<MEXMEPaqBaseAtributo>();

		if (ctx == null || sql == null) {
			return null;
		}

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = DB.prepareStatement(sql, trxName);
			DB.setParameters(pstmt, params);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				resultados.add(new MEXMEPaqBaseAtributo(ctx, rs, trxName));
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} finally {
			DB.close(rs, pstmt);
		}

		return resultados;
	}
}
