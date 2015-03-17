/*
 * Expert Sistemas Computacionales, S. A. de C. V.
 * Derechos Reservados (c) a partir 2005
 * Sistema MedSys
 */
package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;

/**
 * Implementacion de la clase MEXMEHabitacion la cual nos sirve como modelo para actualizar informacion de las habitaciones.
 * 
 * @author Jesus Alberto Cantu Pena
 * @version 1.0
 */
public class MEXMEHabitacion extends X_EXME_Habitacion {

	/** serialVersionUID */
	private static final long serialVersionUID = 1088282577245133739L;
	/** log */
	private static CLogger slog = CLogger.getCLogger(MEXMEHabitacion.class);
	/** camas */
	private List<MEXMECama> camas;

	/**
	 * Constructor heredado de la clase X_EXME_Habitacion.
	 * 
	 * @param ctx
	 *            parametro de contexto.
	 * @param EXME_Habitacion_ID
	 *            el ID de la habitacion.
	 * @param trxName
	 *            el nombre de la transaccion.
	 */
	public MEXMEHabitacion(Properties ctx, int EXME_Habitacion_ID, String trxName) {
		super(ctx, EXME_Habitacion_ID, trxName);

	}

	/**
	 * Constructor heredado de la clase X_EXME_Habitacion.
	 * 
	 * @param ctx
	 *            parametro de contexto.
	 * @param rs
	 *            el objeto ResultSet con la informacion.
	 * @param trxName
	 *            el nombre de la transaccion.
	 */
	public MEXMEHabitacion(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	/**
	 * Metodo before save sobreescrito de la clase X_EXME_Habitacion. La razon principal de crearlo fue que necesitamos guardar el tipo de Area de la
	 * habitacion para que tambien sea cambiado segun el Area que se selecciono.
	 * 
	 * @param newRecord
	 *            boolean
	 * @author Jesus Cantu, 22 Oct 2009.
	 */
	protected boolean beforeSave(boolean newRecord) {
//		final MEXMEArea area = new MEXMEArea(getCtx(), getEXME_Area_ID(), get_TrxName());
//
//		if (getTipoArea() == null && area != null) {
//			setTipoArea(area.getTipoArea());
//		}
		return true;
	}

	/**
	 * Obtenemos todos las habitaciones para la empresa y area logeada
	 * 
	 * @param ctx
	 *            El contexto de la aplicacion
	 * @param trxName
	 *            El nombre de la transaccion
	 * @return
	 */
	public static List<MEXMEHabitacion> get(Properties ctx, String trxName) {
		final StringBuffer sql = new StringBuffer(Constantes.INIT_CAPACITY_ARRAY);
//		sql.append(" SELECT * FROM EXME_Habitacion WHERE  IsActive = 'Y' AND"); 
		sql.append("  TipoArea = ? ");
//		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name));
//		sql.append(" ORDER BY Name");
		return new Query(ctx, Table_Name, sql.toString(), trxName)//
		.setParameters(Env.getTipoArea(ctx))//
		.setOnlyActiveRecords(true)//
		.addAccessLevelSQL(true)//
		.setOrderBy(" EXME_Cama.Name ")//
		.list();

	}


	/**
	 * Obtenemos las camas de una habitacion
	 * 
	 * @param reQuery
	 * @return
	 */
	public List<MEXMECama> getCamas() {
		return getCamas(true, get_TrxName());
	}

	/**
	 * Obtenemos las camas de una habitacion
	 * 
	 * @param reQuery
	 * @return
	 */
	public List<MEXMECama> getCamas(boolean reQuery, String trxName) {

		if (camas == null || reQuery) {
			camas = getCamas(null, trxName);
		}

		return camas;
	}

	/**
	 * Obtenemos las camas de una habitacion
	 * 
	 * @return
	 */
	public List<MEXMECama> getCamas(String whereClause, String trxName) {
		final StringBuffer sql = new StringBuffer(Constantes.INIT_CAPACITY_ARRAY);
		sql.append(" EXME_Cama.EXME_Habitacion_ID = ? ");
		if (whereClause != null) {
			sql.append(whereClause);
		}

		return new Query(getCtx(), MEXMECama.Table_Name, sql.toString(), trxName)//
		.setParameters(getEXME_Habitacion_ID())//
		.setOnlyActiveRecords(true)//
		.addAccessLevelSQL(true)//
		.setOrderBy(" EXME_Cama.Name ")//
		.list();
	}

	/**
	 * Obtenemos la cama asignada a la cuenta paciente
	 * @return
	 */
	public MCDiario getCdiario() {

		MCDiario ret = null;

		if (getEXME_CDiario_ID() > 0) {
			ret = new MCDiario(getCtx(), getEXME_CDiario_ID(), get_TrxName());
		}

		return ret;

	}


	/****************************************** fin cambios Dreyser ****/
	public static List<KeyNamePair> getHabitaDisponibles(Properties ctx, int exmeEstServId, String trxName) {
		return getHabitaDisponibles(ctx, exmeEstServId, 0, trxName);
	}
	/**
	 * Obtenemos todos las habitaciones para la empresa y area logeada que tienen camas disponibles
	 * 
	 * @param ctx
	 *            El contexto de la aplicacion
	 * @param trxName
	 *            El nombre de la transaccion
	 * @return
	 */
	public static List<KeyNamePair> getHabitaDisponibles(Properties ctx, int exmeEstServId, int exmeHabitacionId, String trxName) {
		if (ctx == null) {
			return null;
		}
		final ArrayList<Object> params = new ArrayList<>();

		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append(" SELECT DISTINCT EXME_Habitacion.EXME_Habitacion_ID, EXME_Habitacion.name, EXME_Habitacion.EXME_EstServ_ID  ");
		sql.append(" FROM EXME_Habitacion ");
		sql.append(" INNER JOIN EXME_Cama c ON(c.EXME_Habitacion_ID = EXME_Habitacion.EXME_Habitacion_ID) ");
		sql.append(" WHERE EXME_Habitacion.IsActive = 'Y' AND c.isActive = 'Y' ");

		sql.append(" AND ");
		if (exmeHabitacionId > 0) { //Lama
			sql.append(" EXME_Habitacion.EXME_Habitacion_ID = ? OR ( "); //
			params.add(exmeHabitacionId);
		}
		sql.append(" c.estatus IN (?) "); //
		params.add(MEXMECama.ESTATUS_AvailableClean);
		if (exmeEstServId > 0) {
			sql.append(" AND EXME_Habitacion.EXME_EstServ_ID = ? ");
			params.add(exmeEstServId);
		}
		if (exmeHabitacionId > 0) {
			sql.append(" ) "); //
		}
		
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name));
		sql.append(" ORDER BY EXME_Habitacion.EXME_EstServ_ID, EXME_Habitacion.Name ");

		return Query.getListKN(sql.toString(), trxName, params);
	}


	/**
	 * Estacion de servicio
	 * @return
	 */
	public MEXMEEstServ getEstServ() {
		return new MEXMEEstServ(getCtx(), getEXME_EstServ_ID(), get_TrxName());
	}

	/**
	 * AreaEXME_Cama.
	 */
	public int getEXME_Area_ID() {
		int idArea = 0;
		if (getEXME_EstServ_ID() > 0) {
			idArea = getEstServ().getEXME_Area_ID();
		}
		return idArea;
	}

	/**
	 * 
	 * @param ctx
	 * @param exmeEstServId
	 * @param exmeAreaId
	 * @param msg
	 * @param trxName
	 * @return
	 */
	public static ArrayList<KeyNamePair> getHabitacion(Properties ctx, int exmeEstServId, int exmeAreaId, String trxName) {

		final ArrayList<KeyNamePair> lista = new ArrayList<KeyNamePair>();

		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);

		sql.append(" SELECT DISTINCT EXME_Habitacion.EXME_Habitacion_ID, EXME_Habitacion.Name ");
		sql.append(" FROM EXME_Habitacion ");
		sql.append(" INNER JOIN EXME_Cama ON EXME_Cama.EXME_Habitacion_ID = EXME_Habitacion.EXME_Habitacion_ID ");
		if (exmeAreaId > 0) {
			sql.append(" INNER JOIN EXME_CtaPac ON EXME_CtaPac.EXME_CtaPac_ID = EXME_Cama.EXME_CtaPac_ID ");
			//sql.append(" INNER JOIN EXME_EstServ ON ( EXME_EstServ.EXME_EstServ_ID = EXME_CtaPac.EXME_EstServ_ID ");
			sql.append(" AND EXME_CtaPac.EXME_AREA_ID = ? ");// 20110524
		}
		sql.append(" WHERE EXME_Habitacion.IsActive = 'Y' ");

		if (exmeEstServId > 0) {
			sql.append(" AND EXME_Habitacion.EXME_EstServ_ID = ? ");
		}
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name));
		sql.append(" ORDER BY EXME_Habitacion.Name ");

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			pstmt = DB.prepareStatement(sql.toString(), trxName);
			int index = 1;
			if (exmeAreaId > 0) {
				pstmt.setInt(index++, exmeAreaId);
			}
			if (exmeEstServId > 0) {
				pstmt.setInt(index++, exmeEstServId);
			}
			rs = pstmt.executeQuery();

			while (rs.next()) {
				lista.add(new KeyNamePair(rs.getInt("EXME_Habitacion_ID"), rs.getString("Name")));
			}

		} catch (Exception e) {
			slog.log(Level.SEVERE, "getIdNomHabita (" + sql + ")", e);

		} finally {
			DB.close(rs, pstmt);
		}

		return lista;
	}
	
	
	/** Tipo de area 
	 */
	public String getTipoArea(){
		return getEstServ().getTipoArea();
	}
	
	/**
	 * 
	 * @param ctx
	 * @param tipoHabitacionID
	 * @param whereClause
	 * @param orderBy
	 * @param trxName
	 * @return
	 */
	public static List<MEXMEHabitacion> getHabPorTipoHab(Properties ctx, int tipoHabitacionID, String trxName) {
		return new Query(ctx, Table_Name, "  EXME_TipoHabitacion_ID = ? ", trxName)//
		.setParameters(tipoHabitacionID)//
		.setOnlyActiveRecords(true)//
		.addAccessLevelSQL(true)//
		.setOrderBy(" Name ")//
		.list();
	}
	
	/**
	 * Devolvemos una lista de objetos <MEstServ> con las estaciones
	 * relacionadas al rol del login. Puede devolver solo la de login si es que
	 * la configuracion EC no permite ver todas las estaciones del login al
	 * mismo tiempo
	 * 
	 * @param ctx
	 * @param trxName
	 * @return
	 * 
	 */
	public static List<KeyNamePair> getEstServ(final Properties ctx, final boolean byRole, final String trxName) {

		final StringBuilder sql = new StringBuilder();
		sql.append("SELECT DISTINCT est.EXME_EstServ_ID, est.Name ");
		sql.append("FROM EXME_EstServ est ");
		sql.append("INNER JOIN EXME_Habitacion hab ON hab.EXME_EstServ_ID=est.EXME_EstServ_ID AND hab.IsActive='Y' ");
		
		final List<KeyNamePair> list;
		
		// Configuracion de Expediente Clinico

		// Se cambian las estaciones siempre y cuando este configurado a
		// nivel organizacion
		// que a los usuarios se les permite cambiar de estacion
		// relacionados a su rol
		if (!byRole || MConfigEC.get(ctx, trxName).isPermCamEst()) {
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
	
}
