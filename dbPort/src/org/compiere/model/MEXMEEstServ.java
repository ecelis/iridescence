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
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;

import com.ecaresoft.api.Generic;

/**
 * Created on 22-mar-2005
 * 
 * Modelo de Estacion de Servicio Modificado: $Author: mrojas $ $Date:
 * 2006/08/11 02:26:22 $
 * 
 * @author Hassan Reyes
 * @version $Revision: 1.10 $
 */
public class MEXMEEstServ extends X_EXME_EstServ {
	/** serialVersionUID */
	private static final long serialVersionUID = -5090509813710437326L;
	/** Static Logger */
	private static CLogger slog = CLogger.getCLogger(MEXMEEstServ.class);

	/**
	 * @param ctx
	 * @param EXME_EstServ_ID
	 * @param trxName
	 */
	public MEXMEEstServ(final Properties ctx, final int EXME_EstServ_ID, final String trxName) {
		super(ctx, EXME_EstServ_ID, trxName);
	}

	/**
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MEXMEEstServ(final Properties ctx, final ResultSet rs, final String trxName) {
		super(ctx, rs, trxName);
	}

	/**
	 * Listado de estaciones de servicio por tipo de area, del area a la que
	 * estan ligadas
	 * 
	 * @param ctx
	 * @param area
	 * @param trxName
	 * @return
	 */
	public static List<MEXMEEstServ> getLstEstServTipoArea(final Properties ctx, final String area, final String trxName) {
		return new Query(ctx, Table_Name, " a.TipoArea=?  ", trxName)//
		.setJoins(new StringBuilder(" INNER JOIN EXME_Area a ON (EXME_EstServ.EXME_Area_ID=a.EXME_Area_ID AND a.IsActive='Y') "))//
		.setParameters(area)//
		.setOnlyActiveRecords(true)//
		.addAccessLevelSQL(true)//
		.setOrderBy(" EXME_EstServ.NAME ")//
		.list();
	}
	
	/** @deprecated use {@link KeyNamePair} */
	public static List<LabelValueBean> getLstLVBEstServTipoAreaUbicacion(final Properties ctx, final List<?> params) {
		final StringBuilder sql = new StringBuilder("select es.* from exme_estserv es ")
		.append(" inner join exme_area a on es.exme_area_id = a.exme_area_id ")
		.append("  where a.exme_area_id in (select exme_area_id from exme_tipopacarea) and  a.tipoarea =  ?" );
		
		final List<MEXMEEstServ> est =  get(ctx, sql, params, null);
		final List<LabelValueBean> lvbEstSet = new ArrayList<LabelValueBean> ();
		
		for (MEXMEEstServ e : est){
			lvbEstSet.add(new LabelValueBean(e.getName(), String.valueOf(e.getEXME_EstServ_ID())));
		}
		return lvbEstSet;
		
	}

	/**
	 * Listado de objetos MEstServ
	 * 
	 * @param ctx
	 * @param sql
	 * @param params
	 * @param trxName
	 * @return
	 */
	private static List<MEXMEEstServ> get(final Properties ctx, final StringBuilder sql, final List<?> params, final String trxName) {

		final List<MEXMEEstServ> list = new ArrayList<MEXMEEstServ>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			pstmt = DB.prepareStatement(sql.toString(), null);
			DB.setParameters(pstmt, params);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				final MEXMEEstServ estServ = new MEXMEEstServ(ctx, rs, trxName);
				list.add(estServ);
			}

		} catch (Exception e) {
			slog.log(Level.SEVERE, "MEstServ.get", e);
		} finally {
			DB.close(rs, pstmt);
		}
		return list;
	}

	/**
	 * Obtenemos los almacenes relacionado a la estaci�n de servicio.
	 * 
	 * @return
	 */
	public MWarehouse[] getWarehouseRel(final String whereClause) {
		final ArrayList<MWarehouse> list = new ArrayList<MWarehouse>();

		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append(" SELECT * FROM M_Warehouse ");
		sql.append("WHERE M_Warehouse_ID IN (SELECT M_Warehouse_ID FROM EXME_EstServAlm ");
		sql.append(" WHERE EXME_EstServ_ID = ? ) ");

		sql.append(MEXMELookupInfo.addAccessLevelSQL(getCtx(), " ", MWarehouse.Table_Name));

		if (whereClause != null) {
			sql.append(whereClause);
		}
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), get_TrxName());
			pstmt.setInt(1, getEXME_EstServ_ID());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				final MWarehouse whs = new MWarehouse(getCtx(), rs, get_TrxName());
				list.add(whs);
			}
		} catch (Exception e) {
			log.log(Level.SEVERE, "getWarehouseRel", e);
		} finally {
			DB.close(rs, pstmt);
		}
		final MWarehouse[] whss = new MWarehouse[list.size()];
		list.toArray(whss);
		return whss;
	}

	/**
	 * @deprecated {@link MEXMEEstServ#getEstServOrgGeneric(Properties, boolean, String)}
	 * Devolvemos una lista de objetos <LabelValueBean> con las estaciones
	 * relacionadas al rol del login. Puede devolver solo la de login si es que
	 * la configuracion EC no permite ver todas las estaciones del login al
	 * mismo tiempo
	 * @param ctx
	 *            El contexto de la aplicaci&oacute;n
	 * @param truncar
	 *            Limita el nombre de la estacion a 40 caracteres < no se usa >
	 * @param trxName
	 *            nombre de la transaccion
	 * @return
	 */
	public static List<LabelValueBean> getEstServOrg(final Properties ctx, final boolean truncar, final String trxName)  {
		final List<MEXMEEstServ> lstObj = MEXMEEstServ.getEstServOrgObj(ctx,  null, null, null, trxName);
		final List<LabelValueBean> lstLVB = new ArrayList<LabelValueBean>();
		if (lstObj != null) {
			for (MEXMEEstServ estserv : lstObj) {
				lstLVB.add(new LabelValueBean(estserv.getName(), String.valueOf(estserv.getEXME_EstServ_ID())));
			}
		}
		return lstLVB;
	}
	
	/**
	 * Based on {@link MEXMEEstServ#getEstServOrg(Properties, boolean, String)}
	 * Devolvemos una lista de objetos <Generic> con las estaciones
	 * relacionadas al rol del login. Puede devolver solo la de login si es que
	 * la configuracion EC no permite ver todas las estaciones del login al
	 * mismo tiempo
	 * 
	 * @param ctx
	 *            El contexto de la aplicaci&oacute;n
	 * @param truncar
	 *            Limita el nombre de la estacion a 40 caracteres < no se usa >
	 * @param trxName
	 *            nombre de la transaccion
	 * @return
	 */
	public static List<Generic> getEstServOrgGeneric(Properties ctx, boolean truncar, String trxName)  {
		final List<MEXMEEstServ> lstObj = MEXMEEstServ.getEstServOrgObj(ctx, null, null, null, trxName);
		final List<Generic> lstLVB = new ArrayList<Generic>();
		if (lstObj != null) {
			for (MEXMEEstServ estserv : lstObj) {
				lstLVB.add(new Generic(estserv.getName(), String.valueOf(estserv.getEXME_EstServ_ID())));
			}
		}
		return lstLVB;
	}

	/**
	 * Devolvemos una lista de objetos <MEstServ> con las estaciones
	 * relacionadas al rol del login. Puede devolver solo la de login si es que
	 * la configuracion EC no permite ver todas las estaciones del login al
	 * mismo tiempo
	 * 
	 * @param ctx
	 * @param sqlWhere
	 * @param sqlOrder
	 * @param params
	 * @param trxName
	 * @return
	 * 
	 */
	public static List<MEXMEEstServ> getEstServOrgObj(final Properties ctx, final String sqlWhere, final String sqlOrder,
		final List<Object> params, final String trxName) {

		List<MEXMEEstServ> resultados;

		// Configuracion de Expediente Clinico
		final MConfigEC config = MConfigEC.get(ctx, trxName);

		// Se cambian las estaciones siempre y cuando este configurado a
		// nivel organizacion
		// que a los usuarios se les permite cambiar de estacion
		// relacionados a su rol
		if (config != null && config.isPermCamEst()) {

			resultados =
				new Query(ctx, Table_Name, " res.AD_Role_ID=? " + (sqlWhere == null ? "" : sqlWhere), trxName)
					.setJoins(
						new StringBuilder(
							" INNER JOIN EXME_RoleEstServ res ON EXME_EstServ.EXME_EstServ_ID=res.EXME_EstServ_ID AND res.IsActive = 'Y' "))//
					.setParameters(Env.getAD_Role_ID(ctx))//
					.setOnlyActiveRecords(true)//
					.addAccessLevelSQL(true)//
					.setOrderBy(sqlOrder == null ? " EXME_EstServ.NAME " : sqlOrder)//
					.list();

		} else {
			resultados = new Query(ctx, Table_Name, " EXME_EstServ.EXME_EstServ_ID=? ", trxName)//
				.setParameters(Env.getEXME_EstServ_ID(ctx))//
				.list();
		}

		return resultados;
	}
	
	
	/**
	 * Devolvemos una lista de objetos <MEstServ> con las estaciones
	 * relacionadas al rol del login. Puede devolver solo la de login si es que
	 * la configuracion EC no permite ver todas las estaciones del login al
	 * mismo tiempo
	 * 
	 * @param ctx
	 * @param sqlWhere
	 * @param sqlOrder
	 * @param params
	 * @param trxName
	 * @return
	 * 
	 */
	public static List<KeyNamePair> getByRole(final Properties ctx, final String trxName) {

		final StringBuilder sql = new StringBuilder();
		sql.append("SELECT DISTINCT est.EXME_EstServ_ID, est.Name ");
		sql.append("FROM EXME_EstServ est ");

		final List<KeyNamePair> list;

		// Configuracion de Expediente Clinico
		final MConfigEC config = MConfigEC.get(ctx, trxName);

		// Se cambian las estaciones siempre y cuando este configurado a
		// nivel organizacion
		// que a los usuarios se les permite cambiar de estacion
		// relacionados a su rol
		if (config != null && config.isPermCamEst()) {
			sql.append("INNER JOIN EXME_RoleEstServ res ON est.EXME_EstServ_ID=res.EXME_EstServ_ID ");
			sql.append("WHERE est.IsActive='Y' ");
			sql.append("AND res.AD_Role_ID=? ");
			sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name, "est"));
			sql.append(" ORDER BY est.Name ");
			list = Query.getListKN(sql.toString(), trxName, Env.getAD_Role_ID(ctx));

		} else {
			sql.append(" WHERE  est.EXME_EstServ_ID=? ");
			list = Query.getListKN(sql.toString(), trxName, Env.getEXME_EstServ_ID(ctx));
		}

		return list;
	}


	/**
	 * Devolvemos una lista de estaciones activas con su nivel de acceso
	 * posteriormente se relacionan a su rol para no perder la seguridad por
	 * perfil
	 * 
	 * @param ctx
	 * @param trxName
	 * @return
	 * @author J Rodriguez
	 */
	public static MEXMEEstServ[] getEstaciones(final Properties ctx, final String trxName) {
		final List<MEXMEEstServ> resultados = new Query(ctx, Table_Name, null, trxName)//
			.setOnlyActiveRecords(true)//
			.addAccessLevelSQL(true)//
			.setOrderBy(" EXME_EstServ.Created ASC ")//
			.list();
		final MEXMEEstServ[] reenviar = new MEXMEEstServ[resultados.size()];
		resultados.toArray(reenviar);
		return reenviar;
	}
	
	public static MEXMEEstServ getFirstEstServ(Properties ctx, String trxName) {
		return new Query(ctx, Table_Name, null, trxName) //
			.setOnlyActiveRecords(true)//
			.addAccessLevelSQL(true)//
			.setOrderBy(" EXME_EstServ.Created ASC ")//
			.first();
	}

	/**
	 * Devolvemos la organizacion transaccional de una estaci&oacute;nn de
	 * servicio en particular, a partir de un almacen
	 * 
	 *@param almacenId
	 *            almacen
	 *@return ID de la Organizacion Transaccional
	 */
	public static int getEstServAlmOrgTrx(Properties ctx, int almacenId)
			 {
		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append(" SELECT EXME_EstServ.AD_OrgTrx_ID ");
		sql.append(" FROM EXME_EstServAlm ");
		sql.append(" INNER JOIN EXME_EstServ ON ( EXME_EstServ.EXME_EstServ_ID = EXME_EstServAlm.EXME_EstServ_ID AND EXME_EstServ.isActive = 'Y' ) ");
		sql.append(" WHERE EXME_EstServAlm.IsActive = 'Y' ");
		sql.append(" AND   EXME_EstServAlm.M_Warehouse_ID = ? ");
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", MEstServAlm.Table_Name));
		sql.append(" ").append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name));

		final int orgTrxID = DB.getSQLValue(null, sql.toString(), almacenId);

		if (orgTrxID <= 0) {
			slog.log(Level.SEVERE, "No existe registro con el almacen " + almacenId);
		}

		return orgTrxID;
	}

	/**
	 * Devolvemos la organizacion transaccional de una estaci&oacute;nn de
	 * servicio en particular a partir de la estacion
	 * 
	 *@param estServID
	 *            Estaci&oacute;n de Servicio
	 *@return ID de la Organizacion Transaccional
	 */
	public static int getEstServOrgTrx(Properties ctx, int estServID) {
		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append(" SELECT EXME_EstServ.AD_OrgTrx_ID ");
		sql.append(" FROM EXME_EstServ ");
		sql.append(" WHERE EXME_EstServ.isActive = 'Y' AND EXME_EstServ.EXME_EstServ_ID = ? ");
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name));

		final int orgTrxID = DB.getSQLValue(null, sql.toString(), estServID);
		if (orgTrxID <= 0) {
			slog.log(Level.SEVERE, "o existe registro con la estacion de servicio " + estServID);
		}

		return orgTrxID;
	}

	/**
	 * Obtiene todas las estaciones de servicio
	 * 
	 * @param ctx
	 * @param trxName
	 * @return
	 * @throws Exception
	 * @deprecated solo utilizada por TableroCamasAction y StockEstacionAction
	 */
	public static List<LabelValueBean> getAllEstServOrg(final Properties ctx, final String trxName) throws Exception  {

		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		final List<LabelValueBean> resultados = new ArrayList<LabelValueBean>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			final MConfigEC config = MConfigEC.get(ctx, trxName);

			final MEXMEEstServ estServ = new MEXMEEstServ(ctx, Env.getEXME_EstServ_ID(ctx), trxName);
			sql.append(" SELECT EXME_EstServ.EXME_EstServ_ID, EXME_EstServ.Name  ").append(" FROM  EXME_EstServ ")
				.append(" INNER JOIN EXME_Area a ON EXME_EstServ.EXME_Area_ID = a.EXME_Area_ID ").append(" WHERE EXME_EstServ.IsActive = 'Y' ")
				.append(" AND   UPPER(a.tipoArea) = UPPER(?)");

			if (config == null || !config.isPermCamEst()) {
				sql.append(" AND EXME_EstServ.EXME_EstServ_ID = ? ");
				// .append(m_EstServ)-Lama
			}
			sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name));
			sql.append(" ORDER BY EXME_EstServ.Name ");

			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setString(1, estServ.getTipoArea());
			if (config == null || !config.isPermCamEst()) {
				pstmt.setInt(2, estServ.getEXME_EstServ_ID());
			}
			rs = pstmt.executeQuery();

			LabelValueBean resultado = new LabelValueBean("", "0");
			resultados.add(resultado);
			while (rs.next()) {
				resultado = new LabelValueBean(rs.getString("Name"), rs.getString("EXME_EstServ_ID"));
				resultados.add(resultado);
			}

		} catch (Exception e) {
			throw e;
		} finally {
			DB.close(rs, pstmt);
		}
		return resultados;
	}
	
	/**
	 * Obtiene un listado de estaciones de servicio
	 * 
	 * @param ctx
	 * @param join
	 *            tablas requeridas
	 * @param whereClause
	 *            condiciones
	 * @param blankLine
	 * @param trxName
	 * @return
	 */
	public static List<KeyNamePair> getActive(final Properties ctx, final String trxName) {

		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);

		sql.append("SELECT DISTINCT EXME_EstServ.EXME_EstServ_ID, EXME_EstServ.Name FROM EXME_EstServ ");
		sql.append(" WHERE EXME_EstServ.isActive = 'Y' ");
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name));
		sql.append(" ORDER BY EXME_EstServ.Name ");

		return Query.getListKN(sql.toString(), trxName);
	}

	/**
	 * Obtiene un listado de estaciones de servicio
	 * 
	 * @param ctx
	 * @param join
	 *            tablas requeridas
	 * @param whereClause
	 *            condiciones
	 * @param blankLine
	 * @param trxName
	 * @return
	 * @deprecated use {@link KeyNamePair} 
	 */
	public static List<LabelValueBean> getLstEstServ(final Properties ctx, final StringBuilder join,
		final StringBuilder whereClause, final boolean blankLine, final String trxName) {
		final List<LabelValueBean> retValue = new ArrayList<LabelValueBean>();

		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		PreparedStatement psmt = null;
		ResultSet rs = null;

		try {
			sql.append("SELECT DISTINCT EXME_EstServ.EXME_EstServ_ID, EXME_EstServ.Name FROM EXME_EstServ ");
			if (join != null) {
				sql.append(join);
			}
			sql.append(" WHERE EXME_EstServ.isActive = 'Y' ");
			sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name));

			if (whereClause != null) {
				sql.append(whereClause);
			}
			sql.append(" ORDER BY EXME_EstServ.Name ");

			psmt = DB.prepareStatement(sql.toString(), trxName);
			rs = psmt.executeQuery();

			if (blankLine) {
				retValue.add(new LabelValueBean("", "0"));
			}
			while (rs.next()) {
				retValue.add(new LabelValueBean(rs.getString(2), rs.getString(1)));
			}

		} catch (Exception e) {
			slog.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, psmt);
		}
		return retValue;
	}

	/**
	 * Nombre de la estacion de servicio ligada al almacen
	 * 
	 * @param ctx
	 * @param almacenId
	 * @return
	 * 
	 */
	public static String getEstServAlmfromWh(final Properties ctx, final int almacenId)  {
		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append("SELECT e.name FROM EXME_EstServ e ");
		sql.append(" INNER JOIN EXME_EstServAlm a ON(e.exme_estserv_id=a.exme_estserv_id) ");
		sql.append(" WHERE a.m_warehouse_id = ? ");
		final String alm = DB.getSQLValueString(null, sql.toString(), almacenId);
		if (StringUtils.isBlank(alm)) {
			slog.log(Level.SEVERE, "No existe registro con el almacen " + almacenId);
		}
		return alm;
	}


//	/**
//	 * Estaciones de servicio por tipo de area
//	 * 
//	 * @param ctx
//	 * @param tipoArea
//	 * @param trxName
//	 * @return
//	 * @deprecated use {@link KeyNamePair} NO UTILIZADA
//	 */
//	public static List<LabelValueBean> getLstLVBEstServTipoArea(final Properties ctx, final String tipoArea, final String trxName) {
//		final List<LabelValueBean> resultados = new ArrayList<LabelValueBean>();
//		try {
//			final List<MEXMEEstServ> lstObj = MEXMEEstServ.getLstEstServTipoArea(ctx, tipoArea, trxName);
//
//			if (lstObj != null) {
//				for (MEXMEEstServ estserv : lstObj) {
//					resultados.add(new LabelValueBean(estserv.getName(), String.valueOf(estserv.getEXME_EstServ_ID())));
//				}
//			}
//		} catch (Exception e) {
//			slog.log(Level.SEVERE, "getLstLVBEstServTipoArea", e);
//		}
//
//		return resultados;
//	}


	/**
	 * Obtener estacion de servicio por value
	 * 
	 * @param ctx
	 * @param trxName
	 * @param name
	 * @return
	 */
	public static int getFromValue(final Properties ctx, final String name, final String trxName) {
		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append("select EXME_ESTSERV_ID from EXME_ESTSERV where value like ? ");
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name));
		return DB.getSQLValue(trxName, sql.toString(), name);
	}


	/**
	 * Devolvemos el Area de Ingreso relacionado a una estacion de servicio en
	 * particular
	 * 
	 *@param estServID
	 *            Estacion de Servicio
	 *@return String Tipo de Area de la EstServID
	 *
	 *             en caso de ocurrir un error al procesar la consulta.
	 */
	public static String getTipoAreaEstServ(final Properties ctx, final long estServID) {
		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append("SELECT a.tipoarea FROM EXME_EstServ es ");
		sql.append(" INNER JOIN EXME_Area a ON es.EXME_Area_ID = a.EXME_Area_ID ").append(" WHERE es.isActive = 'Y' ");
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name, "es"));
		sql.append(" AND   es.EXME_EstServ_ID = ? ");
		sql.append(" AND    a.isActive = 'Y' ");
		return DB.getSQLValueString(null, sql.toString(), estServID);
	}

	/**
	 * Retornamos el valor a desplegar para la estacion de servicio.
	 * 
	 * @return Nombre de la estacion de servicio.
	 */
	public String getFullName() {
		final StringBuffer fullName = new StringBuffer(Constantes.INIT_CAPACITY_ARRAY);
		fullName.append(getName() == null ? "" : getName() + " ");
		return fullName.toString();
	}

//	/**
//	 * Devolvemos una lista de objetos LabelValueBean con las estaciones
//	 * relacionadas al perfil
//	 * 
//	 *@param ctx
//	 *            El contexto de la aplicaci&oacute;n
//	 *@return Lista de objetos LabelValueBean con las estaciones relacionadas
//	 *@deprecated use {@link KeyNamePair}
//	 */
//	public static List<LabelValueBean> getEstServRole(final Properties ctx, final String strWhere, final String strOrderBy, final List<Object> params)
//		 {
//		final List<MEXMEEstServ> lst = MEXMEEstServ.getEstServOrgObj(ctx, strWhere, strOrderBy, params, null);
//
//		final List<LabelValueBean> lstLVB = new ArrayList<LabelValueBean>();
//		if (lst != null) {
//			for (MEXMEEstServ estserv : lst) {
//				lstLVB.add(new LabelValueBean(estserv.getName(), String.valueOf(estserv.getEXME_EstServ_ID())));
//			}
//		}
//		return lstLVB;
//	}

	/**
	 * Listado de estaciones de servicio por cliente y por organizacion
	 * 
	 * @param ctx
	 *            contexto
	 * @param clientId
	 *            identificador cliente
	 * @param orgId
	 *            identificador organizacion
	 * @param trxName
	 *            nombre de transaccion
	 * @return
	 */
	// TODO: No deberian ser las activas, por rol ?
	public static List<MEXMEEstServ> getEstServ(final Properties ctx, final int clientId, final int orgId, final String trxName) {
		
		return new Query(ctx, Table_Name, " ad_client_id=? and ad_org_id=? ", trxName)//
		.setParameters(clientId, orgId)//
		//.setOnlyActiveRecords(true)//
		//.addAccessLevelSQL(true)//
		.list();
	}

	/**
	 * Estacion de servicio por area
	 * 
	 * @param ctx
	 *            contexto
	 * @param exmeAreaId
	 *            identificador del area
	 * @param trxName
	 *            nombre de transaccion
	 * @return
	 */
	public static List<KeyNamePair> getEstServPorArea(final Properties ctx, final int exmeAreaId, final String trxName) {

		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append(" SELECT EXME_EstServ.EXME_EstServ_ID, EXME_EstServ.NAME ");
		sql.append(" FROM EXME_EstServ  ");
		sql.append(" WHERE EXME_EstServ.IsActive = 'Y' ");
		sql.append(" AND EXME_EstServ.EXME_AREA_ID = ?  ");
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name, "EXME_EstServ"));
		return Query.getListKN(sql.toString(), trxName, exmeAreaId);
	}
	
	/**
	 * Get Area Type.
	 * 
	 * @return Admission Area Type
	 */
	public String getTipoArea() {
		String tipoArea = null;
		try {
			tipoArea = MEXMEEstServ.getTipoAreaEstServ(getCtx(), getEXME_EstServ_ID());
		} catch (Exception e) {
			slog.log(Level.SEVERE, "getTipoArea()", e);
		}
		if(tipoArea==null){
			tipoArea = MEXMEArea.TIPOAREA_Other;
		}
		return tipoArea;
	}

	public static List<KeyNamePair> getLstLVBEstServTipoAreaUH(final Properties ctx) {
		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append(" SELECT es.EXME_EstServ_ID, es.Name FROM EXME_EstServ es ");
		sql.append(" INNER JOIN EXME_Area a ON es.EXME_Area_ID = a.EXME_Area_ID ");
		sql.append(" WHERE es.IsActive='Y' ");
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", X_EXME_EstServ.Table_Name, "es"));
		sql.append(" AND a.IsActive = 'Y' ");
		sql.append(" AND a.TipoArea IN (?,?)");
		sql.append(" ORDER BY es.Name");
		return Query.getListKN(sql.toString(), null, MEXMEArea.TIPOAREA_Emergency, MEXMEArea.TIPOAREA_Hospitalization);
	}

	/**
	 * Obtiene las estaciones de servicio por organizacion
	 * @param ctx
	 * @param trxName
	 * @return
	 */
	public static List<KeyNamePair> getEstServPorOrg(final Properties ctx, final String trxName) {
		final StringBuilder sql = new StringBuilder();
		sql.append("select distinct es.EXME_EstServ_ID, es.Name, es.value from exme_estserv es ")
			.append(" inner join exme_tipopacarea tare on tare.exme_area_id = es.exme_area_id and tare.isactive = 'Y' ")
			.append(" where es.ad_org_id = ? order by es.value");
		return Query.getListKN(sql.toString(), trxName, Env.getAD_Org_ID(ctx));
	}
	
	/**
	 * Based on {@link MEXMEEstServ#getEstServPorOrg(Properties, String)}
	 * Obtiene las estaciones de servicio por organizacion
	 * @param ctx
	 * @param trxName
	 * @return
	 * @deprecated
	 */
	public static List<Generic> getEstServPorOrgGeneric(final Properties ctx, final String trxName) {
		final List<Generic> list = new ArrayList<Generic>();
		final StringBuilder sql = new StringBuilder();
		sql.append("select distinct es.* from exme_estserv es ")
			.append(" inner join exme_tipopacarea tare on tare.exme_area_id = es.exme_area_id and tare.isactive = 'Y' ")
			.append(" where es.ad_org_id = ? order by es.value");
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, Env.getAD_Org_ID(ctx));
			rs = pstmt.executeQuery();
			while (rs.next()) {
				list.add(new Generic(rs.getString(COLUMNNAME_Name), String.valueOf(rs.getInt(COLUMNNAME_EXME_EstServ_ID))));
			}
		} catch (Exception e) {
			slog.log(Level.SEVERE, "MEstServ.getEstServ", e);
		} finally {
			DB.close(rs, pstmt);
		}
		return list;
	}
	
	/** @deprecated use {@link KeyNamePair} */
	public static List<LabelValueBean> getEstServAgenda(final Properties ctx, final String text, final String trxName) {
		final List<LabelValueBean> list = new ArrayList<LabelValueBean>();
		final StringBuilder sql = new StringBuilder();
		sql.append("select distinct es.* from exme_estserv es ");
		sql.append(" where es.ad_org_id = ? and es.isActive = 'Y'");
		sql.append(" AND es.manejaAgenda = 'Y'");

		if (StringUtils.isNotEmpty(text)) {
			sql.append(" AND ( upper(es.value) like ? or upper(es.name) like ? )");
		}

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, Env.getAD_Org_ID(ctx));
			if (StringUtils.isNotEmpty(text)) {
				pstmt.setString(2, text.toUpperCase());
				pstmt.setString(3, text.toUpperCase());
			}
			rs = pstmt.executeQuery();
			while (rs.next()) {
				list.add(new LabelValueBean(rs.getString(COLUMNNAME_Name), String.valueOf(rs.getInt(COLUMNNAME_EXME_EstServ_ID))));
			}
		} catch (Exception e) {
			slog.log(Level.SEVERE, null, e);
		} finally {
			DB.close(rs, pstmt);
		}
		return list;
	}
	
	public static MEXMEEstServ getFromCtx(Properties ctx) {
		return new MEXMEEstServ(ctx, Env.getEXME_EstServ_ID(ctx), null);
	}
	
}
