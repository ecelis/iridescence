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
 * Lizeth de la Garza 23 Septiembre 2009 Clase para la valoracion del manejo del dolor del paciente <br>
 * Modificado por Lorena Lama, Oct 2012
 */
public class MEXMEManejoDolor extends X_EXME_ManejoDolor {
	/**
	 * 
	 */
	private static final long	serialVersionUID	= -1836690325486285934L;
	private static CLogger		s_log				= CLogger.getCLogger(MEXMEManejoDolor.class);

	public MEXMEManejoDolor(Properties ctx, int EXME_ManejoDolor_ID, String trxName) {
		super(ctx, EXME_ManejoDolor_ID, trxName);
	}

	/**
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MEXMEManejoDolor(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	private MEXMEParteCorporal	bodyPart;

	@Override
	public MEXMEParteCorporal getEXME_ParteCorporal() throws RuntimeException {
		if (bodyPart == null) {
			bodyPart = new MEXMEParteCorporal(getCtx(), getEXME_ParteCorporal_ID(), get_TrxName());
		}
		return bodyPart;
	}

	/**
	 * 
	 * @param ctx
	 * @param ctaPacID
	 * @param diarioEnfID
	 * @param trxName
	 * @return
	 */
	public static List<MEXMEManejoDolor> get(Properties ctx, int ctaPacID, int diarioEnfID, String trxName) {
		List<MEXMEManejoDolor> lista = new ArrayList<MEXMEManejoDolor>();
		final List<Object> params = new ArrayList<Object>();
		final StringBuilder sql = new StringBuilder();
		try {
			if (ctaPacID > 0) {
				sql.append(" EXME_ManejoDolor.EXME_CtaPac_ID=? ");
				params.add(ctaPacID);
			}
			if (diarioEnfID > 0) {
				params.add(diarioEnfID);
				if (sql.length() > 0) {
					sql.append(Constantes.SQL_AND);
				}
				sql.append(" EXME_ManejoDolor.EXME_DiarioEnf_ID=? ");
			}

			lista = new Query(ctx, Table_Name, sql.toString(), trxName)//
					.setParameters(params)//
					.addAccessLevelSQL(true)//
					.setOnlyActiveRecords(true)//
					.setOrderBy("EXME_ManejoDolor.Fecha Desc")//
					.list();
		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString() + " " + params.toString(), e);
		}

		return lista;
	}

	public String getDuracionLbl() {
		return MRefList.getListName(getCtx(), DURACION_AD_Reference_ID, StringUtils.trim(getDuracion()));
	}

	public String getTipoDolorLbl() {
		return MRefList.getListName(getCtx(), TIPO_DOLOR_AD_Reference_ID, StringUtils.trim(getTipo_Dolor()));
	}
	
	public String getParteCorporalName(){
		return getEXME_ParteCorporal().getName();
	}

}
