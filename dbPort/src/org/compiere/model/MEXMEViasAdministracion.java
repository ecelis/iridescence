package org.compiere.model;

import java.util.List;
import java.util.Properties;

import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;

/**
 * Routes of administration
 */
public class MEXMEViasAdministracion extends X_EXME_ViasAdministracion {

	private static final long	serialVersionUID	= 1L;
	private static CLogger		slog				= CLogger.getCLogger(MEXMEViasAdministracion.class);

	public MEXMEViasAdministracion(Properties ctx, int EXME_ViasAdministracion_ID, String trxName) {
		super(ctx, EXME_ViasAdministracion_ID, trxName);
	}

	/**
	 * Regresa las v�as de administraci�n disponibles
	 * 
	 * @param ctx
	 *            Propiedades
	 * @param trxName
	 *            Nombre de la transaccion
	 * @deprecated use {@link #getAll(Properties, String, String)}
	 *
	public static List<LabelValueBean> getViasAdministracion(Properties ctx, String tipoVia, String trxName) {

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		final List<LabelValueBean> resultado = new ArrayList<LabelValueBean>();

		try {

			sql.append("SELECT EXME_ViasAdministracion_ID, Name ");
			sql.append("FROM EXME_ViasAdministracion WHERE isActive='Y' ");
			if (tipoVia != null) {
				sql.append("and tipo = ? ");
			}
			if (Env.getUserPatientID(ctx) <= 0) { // lhernandez. phr
				sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name));
			}
			sql.append(" ORDER BY Name ");
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			if (tipoVia != null) {
				pstmt.setString(1, tipoVia);
			}
			rs = pstmt.executeQuery();

			while (rs.next()) {
				resultado.add(new LabelValueBean(rs.getString("Name"), rs.getString("EXME_ViasAdministracion_ID")));
			}

		}
		catch (Exception e) {
			slog.log(Level.SEVERE, sql.toString(), e);
		}
		finally {
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}

		return resultado;
	}*/

	/**
	 * 
	 * @param idOnly
	 * @return
	 */
	public Object getRoute(boolean idOnly) {
		return getRoute(getCtx(), get_ID(), idOnly, get_TrxName());
	}

	/**
	 * 
	 * @param ctx
	 * @param eXMEViasAdministracionID
	 * @param idOnly
	 * @param trxName
	 * @return
	 */
	public static Object getRoute(Properties ctx, int eXMEViasAdministracionID, boolean idOnly, String trxName) {
		final Query query = new Query(ctx, MEXMERoute.Table_Name, "via.EXME_ViasAdministracion_ID=?", trxName)//
			.setJoins(new StringBuilder(" INNER JOIN EXME_ViasAdministracion via ON (NVL(via.Via,via.Value)=EXME_Route.Abrev) "))//
			.setOnlyActiveRecords(true)//
			.setParameters(eXMEViasAdministracionID);
		final Object obj;
		if (idOnly) {
			obj = query.firstId();
		}
		else {
			obj = query.first();
		}
		return obj;
	}
	
	/**
	 * 
	 * @param ctx
	 * @param eXMERouteID
	 * @param idOnly
	 * @param trxName
	 * @return
	 */
	public static Object getFromRoute(Properties ctx, int eXMERouteID, boolean idOnly, String trxName) {
		final Query query = new Query(ctx, Table_Name, "route.EXME_Route_ID=?", trxName)//
			.setJoins(new StringBuilder(" INNER JOIN EXME_Route route ON (NVL(EXME_ViasAdministracion.Via,EXME_ViasAdministracion.Value)=route.Abrev) "))//
			.setOnlyActiveRecords(true)//
			.setParameters(eXMERouteID)
			.setOrderBy("route.isActive DESC ");
		final Object obj;
		if (idOnly) {
			obj = query.firstId();
		}
		else {
			obj = query.first();
		}
		return obj;
	}
	
	
	/**
	 * 
	 * @param ctx
	 * @param type
	 * @param trxName
	 * @return
	 */
	public static List<KeyNamePair> getAll(Properties ctx, String type, String trxName) {
		slog.info("MEXMEViasAdministracion#getAll");
		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append("SELECT EXME_ViasAdministracion_ID, Name ");
		sql.append("FROM EXME_ViasAdministracion WHERE isActive='Y' ");
		if (type != null) {
			sql.append(" AND tipo = ? ");
		}
		if (Env.getUserPatientID(ctx) <= 0) { // lhernandez. phr
			sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name));
		}
		sql.append(" ORDER BY Name ");
		if (type == null) {
			return Query.getListKN(sql.toString(), trxName);
		} else {
			return Query.getListKN(sql.toString(), trxName, type);
		}
	}
}
