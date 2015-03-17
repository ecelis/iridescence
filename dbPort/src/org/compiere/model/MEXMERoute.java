package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.util.LabelValueBean;
import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;

/**
 * 
 * @author Expert
 * 
 */
public class MEXMERoute extends X_EXME_Route {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2222957802577291059L;
	/** Logger **/
	private static CLogger log = CLogger.getCLogger(MEXMERoute.class);

	public MEXMERoute(Properties ctx, int EXME_Route_ID, String trxName) {
		super(ctx, EXME_Route_ID, trxName);
	}

	public MEXMERoute(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	public static boolean validateRoute(Properties ctx, int RTID) {
		boolean valido = true;
		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);

		sql.append("SELECT r.EXME_ROUTE_ID ");
		sql.append("FROM EXME_ROUTE r ");
		sql.append("WHERE r.RTID = ? ");

		try {
			final int routeID = DB.getSQLValue(null, sql.toString(), RTID);
			if (routeID <= 0) {
				valido = createRoute(ctx, RTID);
			}

		} catch (Exception e) {
			log.log(Level.SEVERE, "getManufacturer(Properties ctx, int RTID)" + e.getMessage(), e);
		}
		return valido;
	}

	public static boolean createRoute(Properties ctx, int RTID) {
		MEXMERoute ret = null;
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);

		sql.append("SELECT r.RTID, r.DESCRIPTION1, r.DESCRIPTION2, ");
		sql.append("r.DESCRIPTION3, r.DESCRIPTION4, r.DESCRIPTION5, ");
		sql.append("r.DESCRIPTION6, r.ABBREV ");
		sql.append("FROM FDB_ROUTE r ");// ? misma tabla ??
		sql.append("WHERE r.RTID = ? ");

		PreparedStatement pstmt = null;
		ResultSet result = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setInt(1, RTID);
			result = pstmt.executeQuery();

			if (result.next()) {
				ret = new MEXMERoute(ctx, 0, null);
				ret.setRtID(result.getInt(1));
				ret.setDescription1(result.getString(2));
				ret.setDescription2(result.getString(3));
				ret.setDescription3(result.getString(4));
				ret.setDescription4(result.getString(5));
				ret.setDescription5(result.getString(6));
				ret.setDescription6(result.getString(7));
				ret.setAbrev(result.getString(8));
			}
		} catch (Exception e) {
			log.log(Level.SEVERE, "getManufacturer(Properties ctx, int RTID)" + e.getMessage(), e);
		} finally {
			DB.close(result, pstmt);
		}
		return ret.save();
	}

	/**
	 * Regresa las vias de administracion disponibles
	 * 
	 * @param ctx
	 *            Contexto de la aplicacion
	 * @param trxName
	 *            Nombre de la transaccion
	 * 
	 */
	public static List<LabelValueBean> getViasAdministracion(Properties ctx, String trxName) {
		return get(ctx, COLUMNNAME_Description1, trxName);
	}
	public static List<LabelValueBean> getAbrevDesc(Properties ctx, String trxName) {
		return get(ctx, "LOWER("+MEXMERoute.COLUMNNAME_Abrev + "||' - '||" + MEXMERoute.COLUMNNAME_Description1 + ")", trxName);
	}

	/**
	 * Regresa las vias de administracion disponibles
	 * 
	 * @param ctx
	 *            Contexto de la aplicacion
	 * @param labelColumn
	 *            Nombre de la columna que se mostrara como label
	 * @param trxName
	 *            Nombre de la transaccion
	 * @return
	 */
	public static List<LabelValueBean> get(Properties ctx, String labelColumn, String trxName) {

		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		final List<LabelValueBean> resultado = new ArrayList<LabelValueBean>();

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			sql.append("SELECT EXME_Route_ID as value, ");
			sql.append(StringUtils.isNotBlank(labelColumn) ? labelColumn : COLUMNNAME_Description1);
			sql.append(" as label ");
			sql.append("FROM EXME_Route WHERE isActive = 'Y' ");
			sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name));

			sql.append(" order by Description1 ");
			pstmt = DB.prepareStatement(sql.toString(), trxName);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				final LabelValueBean obj = new LabelValueBean(rs.getString("label"), rs.getString("value"));
				resultado.add(obj);
			}

		} catch (Exception e) {
			log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
		}
		return resultado;
	}
}
