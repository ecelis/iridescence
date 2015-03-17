/**
 * 
 */
package org.compiere.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.apache.commons.lang.StringUtils;
import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.KeyNamePair;
import org.compiere.util.Utilerias;

/**
 * Patient Type detail
 * 
 * @author Lorena Lama
 *         Revision PMD, FindBugs - May 2013
 */
public class MEXMETipoPacDestino extends X_EXME_TipoPacDestino {

	/** serialVersionUID */
	private static final long	serialVersionUID	= 1L;

	private static CLogger		log					= CLogger.getCLogger(MEXMETipoPacDestino.class);

	/**
	 * @param ctx
	 * @param EXME_TipoPacDestino_ID
	 * @param trxName
	 */
	public MEXMETipoPacDestino(final Properties ctx, final int EXME_TipoPacDestino_ID, final String trxName) {
		super(ctx, EXME_TipoPacDestino_ID, trxName);
	}

	/**
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MEXMETipoPacDestino(final Properties ctx, final java.sql.ResultSet rs, final String trxName) {
		super(ctx, rs, trxName);
	}

	/**
	 * Lista de tipos de paciente a los cuales se puede cambiar el tipo de paciente seleccionado
	 * 
	 * @param ctx contexto
	 * @param tipoPacienteId tipo de paciente seleccionado
	 * @return List<KeyNamePair>
	 */
	public static List<KeyNamePair> getList(final Properties ctx, final int tipoPacienteId) {
		return getList(ctx, tipoPacienteId, null);
	}

	/**
	 * Lista de tipos de paciente a los cuales se puede cambiar el tipo de paciente seleccionado
	 * 
	 * @param ctx contexto
	 * @param tipoPacienteId tipo de paciente seleccionado
	 * @param ctapac Objeto del encuentro del paciente. Si no es nulo agrega el Tipo de Paciente a la lista en caso (en caso de que no este como tipo
	 *            pac destino)
	 * @return List<KeyNamePair>
	 */
	public static List<KeyNamePair> getList(final Properties ctx, final int tipoPacienteId, final MEXMECtaPac ctapac) {
		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		final boolean basic = ctapac == null || ctapac.getEXME_TipoPaciente_ID() <= 0;
		List<KeyNamePair> retValue;
		if (basic) {
			sql.append(" SELECT DISTINCT td.EXME_TipoPacienteTo_ID as key, t.Name ");
			sql.append(" FROM EXME_TipoPaciente t ");
			sql.append(" INNER JOIN EXME_TipoPacDestino td ON (td.EXME_TipoPacienteTo_ID=t.EXME_TipoPaciente_ID) ");
			sql.append(" WHERE td.EXME_TipoPaciente_ID=?) ");
			sql.append(" AND td.isActive='Y' ");
			sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name, "td"));
			sql.append(" AND t.isActive='Y' ");
			sql.append(" ORDER BY t.Name ");
			retValue = Query.getListKN(sql.toString(), null, tipoPacienteId);
		} else {
			sql.append(" SELECT DISTINCT COALESCE(td.EXME_TipoPacienteTo_ID,t.EXME_TipoPaciente_ID) as key, t.Name ");
			sql.append(" FROM EXME_TipoPaciente t ");
			sql.append(" LEFT JOIN EXME_TipoPacDestino td ON (td.EXME_TipoPacienteTo_ID=t.EXME_TipoPaciente_ID ");
			sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name, "td"));
			sql.append(" AND td.isActive='Y') ");
			sql.append(" WHERE (td.EXME_TipoPaciente_ID=? OR t.EXME_TipoPaciente_ID=?) ");
			sql.append(" AND t.isActive='Y' ");
			sql.append(" ORDER BY t.Name ");
			retValue = Query.getListKN(sql.toString(), null, tipoPacienteId, ctapac.getEXME_TipoPaciente_ID());
		}
		return retValue;
	}

	/**
	 * Lista de tipos de paciente a los cuales se puede cambiar el tipo de paciente seleccionado
	 * 
	 * @param ctx contexto
	 * @param tipoPacienteId tipo de paciente seleccionado
	 * @return List<LabelValueBean>
	 * @deprecated use {@link KeyNamePair}
	 */
	@Deprecated
	public static List<org.apache.struts.util.LabelValueBean> getLvbRelatedTypes(final Properties ctx, final int tipoPacienteId) {
		final List<org.apache.struts.util.LabelValueBean> list = new ArrayList<org.apache.struts.util.LabelValueBean>();

		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		java.sql.PreparedStatement pstmt = null;
		java.sql.ResultSet result = null;

		sql.append(" SELECT EXME_TipoPacDestino.EXME_TipoPacienteTO_ID, EXME_TipoPaciente.Name ");
		sql.append(" FROM EXME_TipoPacDestino ");
		sql.append(" INNER JOIN EXME_TipoPaciente ON (EXME_TipoPacDestino.EXME_TipoPacienteTO_ID = EXME_TipoPaciente.EXME_TipoPaciente_ID) ");
		sql.append(" WHERE EXME_TipoPacDestino.EXME_TipoPaciente_ID = ? ");

		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name));
		sql.append(" ORDER BY EXME_TipoPaciente.Name ");

		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setInt(1, tipoPacienteId);
			result = pstmt.executeQuery();
			while (result.next()) {
				list.add(new org.apache.struts.util.LabelValueBean(result.getString(2), result.getString(1)));
			}
		} catch (final java.sql.SQLException e) {
			log.log(Level.SEVERE, e.getLocalizedMessage(), e);
		} finally {
			DB.close(result, pstmt);
		}
		return list;
	}

	/**
	 * Regresa el registro para un cambio especifico de tipo paciente
	 * 
	 * @param ctx contexto
	 * @param tipoPacOrigenId tipo de paciente origen
	 * @param exmeTipoPacToId tipo de paciente destino
	 * @return
	 */
	public static MEXMETipoPacDestino getRecord(final Properties ctx, final int tipoPacOrigenId, final int exmeTipoPacToId) {
		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append(" EXME_TipoPacDestino.EXME_TipoPaciente_ID=? ");
		sql.append(" AND EXME_TipoPacDestino.EXME_TipoPacienteTo_ID=? ");

		return new Query(ctx, Table_Name, sql.toString(), null)//
			.setParameters(tipoPacOrigenId, exmeTipoPacToId)//
			.setOnlyActiveRecords(true)//
			.addAccessLevelSQL(true)//
			.first();
	}

	/** From patient type */
	private MEXMETipoPaciente	tipoPaciente;
	/** To patient type */
	private MEXMETipoPaciente	tipoPacienteTo;

	/** @return Message from ApplicationResources.properties */
	public String getAlertMsg() {
		String msg;
		if(StringUtils.isNotEmpty(getAlertMessage())){
			msg = Utilerias.getMessage(getCtx(), null, super.getAlertMessage(), getTipoPacienteStr(), getTipoPacienteToStr());
		} else {
			msg = "";
		}
		return msg;
	}

	/** @return From patient type */
	public MEXMETipoPaciente getTipoPaciente() {
		if (tipoPaciente == null && getEXME_TipoPaciente_ID() > 0) {
			tipoPaciente = new MEXMETipoPaciente(getCtx(), getEXME_TipoPaciente_ID(), get_TrxName());
		}
		return tipoPaciente;
	}

	/** @return From patient type name */
	public String getTipoPacienteStr() {
		return getTipoPaciente() == null ? StringUtils.EMPTY : getTipoPaciente().getName();
	}

	/** @return To patient type */
	public MEXMETipoPaciente getTipoPacienteTo() {
		if (tipoPacienteTo == null && getEXME_TipoPacienteTo_ID() > 0) {
			tipoPacienteTo = new MEXMETipoPaciente(getCtx(), getEXME_TipoPacienteTo_ID(), get_TrxName());
		}
		return tipoPacienteTo;
	}

	/** @return To patient type name */
	public String getTipoPacienteToStr() {
		return getTipoPacienteTo() == null ? StringUtils.EMPTY : getTipoPacienteTo().getName();
	}

}
