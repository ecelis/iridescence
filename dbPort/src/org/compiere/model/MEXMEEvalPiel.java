package org.compiere.model;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.apache.commons.lang.StringUtils;
import org.compiere.util.CLogger;
import org.compiere.util.Constantes;

/**
 * Modelo para la informacion de Valoracion corporal
 * 
 * 
 * @author Lizeth de la Garza <br>
 *         Creado: Septiembre/2009 <br>
 *         Modificado por Lorena Lama, Oct 2012
 */
public class MEXMEEvalPiel extends X_EXME_EvalPiel {

	private static final long	serialVersionUID	= 7594206044350066938L;
	private static CLogger		sLog				= CLogger.getCLogger(MEXMEEvalPiel.class);

	public MEXMEEvalPiel(final Properties ctx, final int EXME_EvalPiel_ID, final String trxName) {
		super(ctx, EXME_EvalPiel_ID, trxName);
	}

	public MEXMEEvalPiel(final Properties ctx, final ResultSet rs, final String trxName) {
		super(ctx, rs, trxName);
	}

	/**
	 * Regresa la lista de registros de la valoracion corporal
	 * 
	 * @param ctx Contexto de la aplicacion
	 * @param ctaPacID encuentro / cuenta paciente (si es 0 se excluye de la clausula where)
	 * @param diarioEnfID diario de enfermeria (si es 0 se excluye de la clausula where)
	 * @param trxName Nombre de la transaccion
	 * @return List<MEXMEValCorporal>
	 */
	public static List<MEXMEEvalPiel> get(final Properties ctx, final int ctaPacID, final int diarioEnfID, final String trxName) {
		sLog.fine("MEXMEEvalPiel#get ctaPacID: " + ctaPacID + " diarioEnfID: " + diarioEnfID);

		final List<Object> params = new ArrayList<Object>();
		final StringBuilder sql = new StringBuilder();

		if (ctaPacID > 0) {
			sql.append(" EXME_EvalPiel.EXME_CtaPac_ID=? ");
			params.add(ctaPacID);
		}
		if (diarioEnfID > 0) {
			params.add(diarioEnfID);
			if (sql.length() > 0) {
				sql.append(Constantes.SQL_AND);
			}
			sql.append(" EXME_EvalPiel.EXME_DiarioEnf_ID=? ");
		}

		return new Query(ctx, Table_Name, sql.toString(), trxName)//
			.setParameters(params)//
			.addAccessLevelSQL(true)//
			.setOnlyActiveRecords(true)//
			.setOrderBy("EXME_EvalPiel.Fecha Desc")//
			.list();
	}

	/** Estado de cunciencia del paciente */
	private MEXMEEdoConciencia	consciousness	= null;

	/** @return Nombre traducido para el valor de actividad motriz */
	public String getActMotriz() {
		return MRefList.getListName(getCtx(), ACT_MOTRIZ_AD_Reference_ID, StringUtils.trim(getAct_Motriz()));
	}
	/** @return Nombre traducido para el valor de estado de nutricion */
	public String getEdoNutricionStr() {
		return MRefList.getListName(getCtx(), EDONUTRICION_AD_Reference_ID, StringUtils.trim(getEdoNutricion()));
	}
	/** @return Estado de conciencia del paciente */
	@Override
	public MEXMEEdoConciencia getEXME_EdoConciencia() throws RuntimeException {
		if (consciousness == null) {
			consciousness = new MEXMEEdoConciencia(getCtx(), getEXME_EdoConciencia_ID(), get_TrxName());
		}
		return consciousness;
	}

	/** @return Nombre traducido para el valor de incontinencia */
	public String getIncontinenciaStr() {
		return MRefList.getListName(getCtx(), INCONTINENCIA_AD_Reference_ID, StringUtils.trim(getIncontinencia()));
	}

	/** @return Nombre traducido para el valor de movilidad */
	public String getMovilidadStr() {
		return MRefList.getListName(getCtx(), MOVILIDAD_AD_Reference_ID, StringUtils.trim(getMovilidad()));
	}

	/** @return EXME_EdoConciencia.Name (EXME_EdoConciencia.Valor) */
	public String getNivelConc() {
		if (getEXME_EdoConciencia() == null) {
			return "";
		}
		return getEXME_EdoConciencia().getName() + " (" + getEXME_EdoConciencia().getValor() + ")";
	}

	/** @return Total de la evaluacion */
	public int getTotalEval() {
		int total = 0;
		try {
			final int actMotriz = Integer.valueOf(getAct_Motriz());
			final int edoNutricion = Integer.valueOf(getEdoNutricion());
			final int movilidad = Integer.valueOf(getMovilidad());
			final int incontinencia = Integer.valueOf(getIncontinencia());
			final int edoCon = getEXME_EdoConciencia() == null ||
			// not empty
				StringUtils.isBlank(getEXME_EdoConciencia().getValor()) ? 0 :
			// parse
				Integer.valueOf(StringUtils.trimToNull(getEXME_EdoConciencia().getValor()));
			total = actMotriz + edoNutricion + movilidad + incontinencia + edoCon;
		} catch (final Exception e) {
			log.log(Level.SEVERE, e.getMessage(), e);
		}
		return total;
	}

}
