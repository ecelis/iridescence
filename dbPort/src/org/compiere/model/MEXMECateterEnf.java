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
 * 
 * <br>
 * Modificado por Lorena Lama, Oct 2012
 */
public class MEXMECateterEnf extends X_EXME_CateterEnf {

	/**
	 * 
	 */
	private static final long	serialVersionUID	= -2847350026157822176L;
	private static CLogger		log					= CLogger.getCLogger(MEXMECateterEnf.class);

	public MEXMECateterEnf(Properties ctx, int EXME_CateterEnf_ID, String trxName) {
		super(ctx, EXME_CateterEnf_ID, trxName);
	}

	public MEXMECateterEnf(Properties ctx, ResultSet resultset, String trxName) {
		super(ctx, resultset, trxName);
	}

	/**
	 * 
	 * @param ctx
	 * @param ctaPacID
	 * @param diarioEnfID
	 * @param trxName
	 * @return
	 */
	public static List<MEXMECateterEnf> get(Properties ctx, int ctaPacID, int diarioEnfID, String trxName) {
		return get(ctx, ctaPacID, diarioEnfID, null, trxName);
	}
	
	/**
	 * 
	 * @param ctx
	 * @param ctaPacID
	 * @param diarioEnfID si es mayor a cero, recupera los registros de ese diario
	 * @param completed 
	 *					si es true valida que FechaEgreso no sea nula <br>
	 *					si es false valida que FechaEgreso sea null <br>
	 *					si es null excluye la FechaEgreso
	 * @param trxName
	 * @return
	 */
	public static List<MEXMECateterEnf> get(Properties ctx, int ctaPacID, int diarioEnfID, Boolean completed, String trxName) {
		List<MEXMECateterEnf> lista = new ArrayList<MEXMECateterEnf>();
		final List<Object> params = new ArrayList<Object>();
		final StringBuilder sql = new StringBuilder();
		try {
			if (ctaPacID > 0) {
				sql.append(" EXME_CateterEnf.EXME_CtaPac_ID=? ");
				params.add(ctaPacID);
			}
			if (diarioEnfID > 0) {
				params.add(diarioEnfID);
				if (sql.length() > 0) {
					sql.append(Constantes.SQL_AND);
				}
				sql.append(" EXME_CateterEnf.EXME_DiarioEnf_ID=? ");
			}
			if (completed != null) {
				if (sql.length() > 0) {
					sql.append(Constantes.SQL_AND);
				}
				sql.append(" EXME_CateterEnf.FechaEgreso ");
				if (completed) {
					sql.append("IS NOT NULL");
				} else {
					sql.append("IS NULL");
				}
			}

			lista = new Query(ctx, Table_Name, sql.toString(), trxName)//
				.setParameters(params)//
				.addAccessLevelSQL(true)//
				.setOnlyActiveRecords(true)//
				.setOrderBy("EXME_CateterEnf.FechaIngreso Desc, EXME_CateterEnf.FechaEgreso Desc")//
				.list();
		}
		catch (Exception e) {
			log.log(Level.SEVERE, sql.toString() + " " + params.toString(), e);
		}
		return lista;
	}
	
	public String getFullDescription(){
		final StringBuilder str = new StringBuilder();
		if (getM_Product_ID() > 0) {
			str.append(getM_Product().getName());
		}
		if (StringUtils.isNotBlank(getDescription())) {
			if (str.length() > 0) {
				str.append(" / ");
			}
			str.append(getDescription());
		}
		return str.toString();
	}
	
	private MEXMEParteCorporal	bodyPart;

	@Override
	public MEXMEParteCorporal getEXME_ParteCorporal() throws RuntimeException {
		if (bodyPart == null) {
			bodyPart = new MEXMEParteCorporal(getCtx(), getEXME_ParteCorporal_ID(), get_TrxName());
		}
		return bodyPart;
	}
	
	public String getBodyPartName(){
	return	getEXME_ParteCorporal().getName();
	}

}
