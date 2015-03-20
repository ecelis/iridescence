package org.compiere.model;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.compiere.util.Constantes;
import org.compiere.util.KeyNamePair;
import org.compiere.util.Utilerias;

/**
 * Motivos de Cita
 * 
 * @author Expert/Medsys
 *         Modificado por Lorena Lama - Revision de codigo y metodos obsoletos
 */
public class MEXMEMotivoCita extends X_EXME_MotivoCita {

	private static final long	serialVersionUID	= 1L;

	/** Static logger */
	// private static CLogger s_log = CLogger.getCLogger(MEXMEMotivoCita.class);

	/**
	 * @param ctx
	 * @param EXME_CitaMedica_ID
	 * @param trxName
	 */
	public MEXMEMotivoCita(Properties ctx, int MEXMEMotivoCita_ID, String trxName) {
		super(ctx, MEXMEMotivoCita_ID, trxName);
	}

	public MEXMEMotivoCita(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	/**
	 * Regresa los motivos de cita
	 * 
	 * @param ctx
	 * @param blankLine
	 * @return
	 * @throws Exception
	 */
	public static List<KeyNamePair> getMotivos(Properties ctx, boolean blankLine) throws Exception {
		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append("SELECT EXME_MotivoCita.EXME_MotivoCita_ID, EXME_MotivoCita.Name ");
		sql.append("FROM EXME_MotivoCita ");
		sql.append("WHERE IsActive = 'Y' ");
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name));
		sql.append("ORDER BY EXME_MotivoCita.Name ");

		final List<KeyNamePair> lstMotivos = new ArrayList<KeyNamePair>();
		if (blankLine) {
			lstMotivos.add(new KeyNamePair(0, null));
		}
		lstMotivos.addAll(Query.getListKN(sql.toString(), null));

		return lstMotivos;
	}

	/**
	 * Obtenemos todos los motivos de cita para la empresa logeada
	 * 
	 * @param ctx El contexto de la aplicacion
	 * @param trxName El nombre de la transaccion
	 * @return
	 */
	public static List<MEXMEMotivoCita> getAll(Properties ctx, String where, String orderBy, Object[] params, String trxName) {
		final List<MEXMEMotivoCita> list = new Query(ctx, Table_Name, where, trxName)//
			.setOnlyActiveRecords(true)//
			.addAccessLevelSQL(true)//
			.setParameters(params)//
			.setOrderBy(orderBy)//
			.list();
		return list;
	}

	/**
	 * Obtenemos todos los motivos de cita para la empresa logeada
	 * 
	 * @param ctx El contexto de la aplicacion
	 * @param trxName El nombre de la transaccion
	 * @return
	 */
	public static int getFirstId(Properties ctx, String where,  String trxName, Object... params) {
		return new Query(ctx, Table_Name, where, trxName)//
			.setOnlyActiveRecords(true)//
			.addAccessLevelSQL(true)//
			.setParameters(params)//
			.setOrderBy(COLUMNNAME_Name)//
			.firstId();
	}

	/**
	 * Obtiene una lista de motivos de citas que cumplan con el criterio de busqueda
	 * 
	 * @param ctx Contexto de la app
	 * @param value Valor a buscar (name o value)
	 * @param orderBy Columna por la cual ordenar los registros
	 * @param trxName Nombre de trx
	 * @return Listado de motivos de citas
	 */
	public static List<MEXMEMotivoCita> getLst(Properties ctx, String value,  String orderBy, String trxName) {
		final String valueStr = new StringBuilder("%")//
			.append(StringUtils.upperCase(StringUtils.defaultIfEmpty(value, "")))//
			.append("%").toString();

		return getAll(ctx, //
			" (UPPER(name) LIKE ? OR UPPER(value) LIKE ?) ",//
			orderBy,//
			new Object[] { valueStr, valueStr },//
			trxName);
	}

	@Override
	protected boolean beforeSave(boolean newRecord) {
		if ((is_ValueChanged(COLUMNNAME_Name) || (StringUtils.isNotEmpty(getValue()) && is_ValueChanged(COLUMNNAME_Value)))
			&& getFirstId(getCtx(), " (name=? OR value=?) AND EXME_MotivoCita_ID<>? ", //
				get_TrxName(), getName(), getValue(), get_ID()) > 0) {
			log.saveError(null, Utilerias.getMsg(getCtx(), "msj.allergy.error"));
			return false;
		} else {
			return super.beforeSave(newRecord);
		}
	}
}
