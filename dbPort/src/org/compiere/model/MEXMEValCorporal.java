package org.compiere.model;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.ValueNamePair;

/**
 * Modelo para la informacion de Evaluacion de la Piel
 * 
 * @author Lizeth de la Garza
 *         Septiembre 2009
 *         Modificada por Lorena Lama, 2012
 */
public class MEXMEValCorporal extends X_EXME_ValCorporal {

	private static final long	serialVersionUID	= 1L;
	private static CLogger		sLog				= CLogger.getCLogger(MEXMEValCorporal.class);

	public MEXMEValCorporal(final Properties ctx, final int EXME_ValCorporal_ID, final String trxName) {
		super(ctx, EXME_ValCorporal_ID, trxName);
		if (!is_new()) {
			setDetail();
		}
	}

	public MEXMEValCorporal(final Properties ctx, final ResultSet rs, final String trxName) {
		super(ctx, rs, trxName);
		setDetail();
	}

	/**
	 * Regresa la lista de registros de evaluacion de la piel
	 * 
	 * @param ctx Contexto de la aplicacion
	 * @param ctaPacID encuentro / cuenta paciente (si es 0 se excluye de la clausula where)
	 * @param diarioEnfID diario de enfermeria (si es 0 se excluye de la clausula where)
	 * @param trxName Nombre de la transaccion
	 * @return List<MEXMEValCorporal>
	 */
	public static List<MEXMEValCorporal> get(final Properties ctx, final int ctaPacID, final int diarioEnfID, final String trxName) {
		sLog.fine("MEXMEValCorporal#get ctaPacID: " + ctaPacID + " diarioEnfID: " + diarioEnfID);
		
		final List<Object> params = new ArrayList<Object>();
		final StringBuilder sql = new StringBuilder();

		if (ctaPacID > 0) {
			sql.append(" EXME_ValCorporal.EXME_CtaPac_ID=? ");
			params.add(ctaPacID);
		}
		if (diarioEnfID > 0) {
			params.add(diarioEnfID);
			if (sql.length() > 0) {
				sql.append(Constantes.SQL_AND);
			}
			sql.append(" EXME_ValCorporal.EXME_DiarioEnf_ID=? ");
		}
		return new Query(ctx, Table_Name, sql.toString(), trxName)//
			.setParameters(params)//
			.addAccessLevelSQL(true)//
			.setOnlyActiveRecords(true)//
			.setOrderBy("EXME_ValCorporal.Fecha Desc")//
			.list();

	}

	/** EXME_ValCorporalHerida.Name */
	private String	herida;

	/** EXME_ValCorporalHerida.Value */
	private String	heridaCorp;

	/** EXME_ValCorporalLesion.Value */
	private String	lesionCorp;

	/** EXME_ValCorporalLesion.Name */
	private String	lesion;

	/** @return Nombre traducido para el valor de deshidratracion */
	public String getDeshidratacionStr() {
		return MRefList.getListName(getCtx(), DESHIDRATACION_AD_Reference_ID, StringUtils.trim(getDeshidratacion()));
	}

	/** @return Nombre traducido para el valor de edema */
	public String getEdemaStr() {
		return MRefList.getListName(getCtx(), EDEMA_AD_Reference_ID, StringUtils.trim(getEdema()));
	}

	/** @return EXME_ValCorporalHerida.Name */
	public String getHerida() {
		return herida;
	}

	/** @return EXME_ValCorporalHerida.Value */
	public String getHeridaCorp() {
		return heridaCorp;
	}

	/** @return Nombre traducido para el valor de humedad */
	public String getHumedadStr() {
		return MRefList.getListName(getCtx(), HUMEDAD_AD_Reference_ID, StringUtils.trim(getHumedad()));
	}

	/** @return EXME_ValCorporalLesion.Name */
	public String getLesion() {
		return lesion;
	}

	/** @return EXME_ValCorporalLesion.Value */
	public String getLesionCorp() {
		return lesionCorp;
	}

	/** @return Nombre traducido para el valor de llenado capilar */
	public String getLlenCapilar() {
		return MRefList.getListName(getCtx(), LLEN_CAPILAR_AD_Reference_ID, StringUtils.trim(getLlen_Capilar()));
	}

	/** Llena los valores de lesio y herida */
	public final void setDetail() {
		sLog.fine("MEXMEValCorporal#setDetail");
		if (getEXME_ValCorporal_ID() > 0) {
			// Herida
			final ValueNamePair herida = MEXMEValCorporalHerida.getDetailAsString(getCtx(), getEXME_ValCorporal_ID(), get_TrxName());
			if (herida != null) {
				this.heridaCorp = herida.getValue();
				this.herida = herida.getName();
			}
			// Lesion
			final ValueNamePair lesion = MEXMEValCorporalLesion.getDetailAsString(getCtx(), getEXME_ValCorporal_ID(), get_TrxName());
			if (lesion != null) {
				this.lesionCorp = lesion.getValue();
				this.lesion = lesion.getName();
			}
		}
	}

	/** EXME_ValCorporalHerida.Name */
	public void setHerida(final String herida) {
		this.herida = herida;
	}

	/** EXME_ValCorporalHerida.Value */
	public void setHeridaCorp(final String heridaCorp) {
		this.heridaCorp = heridaCorp;
	}

	/** EXME_ValCorporalLesion.Name */
	public void setLesion(final String lesion) {
		this.lesion = lesion;
	}

	/** EXME_ValCorporalLesion.Value */
	public void setLesionCorp(final String lesionCorp) {
		this.lesionCorp = lesionCorp;
	}

}