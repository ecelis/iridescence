/**
 * 
 */
package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.compiere.util.Constantes;
import org.compiere.util.Utilerias;

/**
 * Service from - to
 * 
 * @author Lorena Lama
 */
public class MEXMEAreaDestino extends X_EXME_AreaDestino {

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;

	/**
	 * Registro correspondiente para un cambio especifico de area
	 * 
	 * @param ctx Contexto de la aplicacion
	 * @param exmeAreaOrigenId Area origen
	 * @param exmeAreaToId Area destino
	 * @return
	 */
	public static MEXMEAreaDestino getRecord(final Properties ctx, final int exmeAreaOrigenId, final int exmeAreaToId) {
		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append(" EXME_AreaDestino.EXME_Area_ID=? ");
		sql.append(" AND EXME_AreaDestino.EXME_AreaTo_ID=? ");

		return new Query(ctx, Table_Name, sql.toString(), null)//
			.setParameters(exmeAreaOrigenId, exmeAreaToId)//
			.setOnlyActiveRecords(true)//
			.addAccessLevelSQL(true)//
			.first();
	}

	/** @return Service */
	private MEXMEArea	area;

	/** @return Service To */
	private MEXMEArea	areaTo;

	/**
	 * @param ctx
	 * @param EXME_AreaDestino_ID
	 * @param trxName
	 */
	public MEXMEAreaDestino(final Properties ctx, final int EXME_AreaDestino_ID, final String trxName) {
		super(ctx, EXME_AreaDestino_ID, trxName);
	}

	/**
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MEXMEAreaDestino(final Properties ctx, final ResultSet rs, final String trxName) {
		super(ctx, rs, trxName);
	}

	/** @return Message from ApplicationResources.properties */
	public String getAlertMsg() {
		String msg;
		if(StringUtils.isNotEmpty(getAlertMessage())){
			msg = Utilerias.getMessage(getCtx(), null, super.getAlertMessage(), getAreaStr(), getAreaToStr());
		} else {
			msg = "";
		}
		return msg;
	}

	/** @return Service */
	public MEXMEArea getArea() {
		if (area == null && getEXME_Area_ID() > 0) {
			area = new MEXMEArea(getCtx(), getEXME_Area_ID(), get_TrxName());
		}
		return area;
	}

	/** @return Service name */
	public String getAreaStr() {
		return getArea() == null ? StringUtils.EMPTY : getArea().getName();
	}

	/** @return Service To */
	public MEXMEArea getAreaTo() {
		if (areaTo == null && getEXME_AreaTo_ID() > 0) {
			areaTo = new MEXMEArea(getCtx(), getEXME_AreaTo_ID(), get_TrxName());
		}
		return areaTo;
	}

	/** @return Service To name */
	public String getAreaToStr() {
		return getAreaTo() == null ? StringUtils.EMPTY : getAreaTo().getName();
	}

}
