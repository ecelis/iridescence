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
 * Lizeth de la Garza Clase registro del Multistix del paciente 25/09/09 <br>
 * Modificado por Lorena Lama, Oct 2012
 */
public class MEXMEMultistix extends X_EXME_Multistix {
	/**
	 * 
	 */
	private static final long	serialVersionUID	= -2491792416688033406L;
	private static CLogger		s_log				= CLogger.getCLogger(MEXMEManejoDolor.class);

	public MEXMEMultistix(Properties ctx, int EXME_Multistix_ID, String trxName) {
		super(ctx, EXME_Multistix_ID, trxName);
	}

	/**
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MEXMEMultistix(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	/**
	 * 
	 * @param ctx
	 * @param ctaPacID
	 * @param diarioEnfID
	 * @param trxName
	 * @return
	 */
	public static List<MEXMEMultistix> get(Properties ctx, int ctaPacID, int diarioEnfID, String trxName) {
		List<MEXMEMultistix> lista = new ArrayList<MEXMEMultistix>();
		final List<Object> params = new ArrayList<Object>();
		final StringBuilder sql = new StringBuilder();
		try {
			if (ctaPacID > 0) {
				sql.append(" EXME_Multistix.EXME_CtaPac_ID=? ");
				params.add(ctaPacID);
			}
			if (diarioEnfID > 0) {
				params.add(diarioEnfID);
				if (sql.length() > 0) {
					sql.append(Constantes.SQL_AND);
				}
				sql.append(" EXME_Multistix.EXME_DiarioEnf_ID=? ");
			}

			lista = new Query(ctx, Table_Name, sql.toString(), trxName)//
					.setParameters(params)//
					.addAccessLevelSQL(true)//
					.setOnlyActiveRecords(true)//
					.setOrderBy("EXME_Multistix.Fecha Desc")//
					.list();
		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString() + " " + params.toString(), e);
		}

		return lista;
	}
	
	
	public String getTipoStr() {
		return MRefList.getListName(getCtx(), TIPO_AD_Reference_ID, StringUtils.trim(getTipo()));
	}

}
