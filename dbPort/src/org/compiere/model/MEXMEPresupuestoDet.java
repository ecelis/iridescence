package org.compiere.model;

import java.sql.ResultSet;
import java.util.List;
import java.util.Properties;

/**
 * Presupuesto detallado
 * 
 * @author twry
 * 
 */
public class MEXMEPresupuestoDet extends X_EXME_PresupuestoDet {

	/** serialVersionUID */
	private static final long serialVersionUID = -3571915380728600889L;

	/**
	 * constructor
	 * 
	 * @param ctx
	 * @param EXME_PresupuestoEgr_ID
	 * @param trxName
	 */
	public MEXMEPresupuestoDet(final Properties ctx,
			final int pPresupEgrID, final String trxName) {
		super(ctx, pPresupEgrID, trxName);
	}

	/**
	 * constructor
	 * 
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MEXMEPresupuestoDet(final Properties ctx, final ResultSet rset,
			final String trxName) {
		super(ctx, rset, trxName);
	}

	/**
	 * Detalle de Presupuesto
	 * 
	 * @param ctx
	 * @param sqlWhere
	 * @return
	 */
	public static List<MEXMEPresupuestoDet> getAll(final Properties ctx,
			final String sqlWhere) {
		final List<MEXMEPresupuestoDet> lst = new org.compiere.model.Query(ctx,
				X_EXME_PresupuestoDet.Table_Name, sqlWhere, null)// .setParameters(1)
				.setOnlyActiveRecords(true).setOrderBy(" Line ").list();
		return lst;
	}

	/**
	 * consulta basica de la consulta del presupuesto
	 * 
	 * @param ctx
	 *            Contexto para el nivel de acceso
	 * @param columns
	 *            con las columnas a mostrar y agrupar
	 * @return
	 */
	public static StringBuilder consulta(final Properties ctx, final int nivel,
			final int reasignacion, final String columns, final String where,
			final String groupby, final String orderby) {

		final StringBuilder sql = new StringBuilder("SELECT ")
				.append(columns == null || columns.isEmpty() ? "ur.UnidadResponsable, ur2.Name as description "
						: columns)
				.append(", sum ( pd.amount )    AS amount ")
				.append(", sum ( pd.amountene ) AS amountene ")
				.append(", sum ( pd.amountfeb ) AS amountfeb ")
				.append(", sum ( pd.amountmar ) AS amountmar ")
				.append(", sum ( pd.amountabr ) AS amountabr ")
				.append(", sum ( pd.amountmay ) AS amountmay ")
				.append(", sum ( pd.amountjun ) AS amountjun ")
				.append(", sum ( pd.amountjul ) AS amountjul ")
				.append(", sum ( pd.amountago ) AS amountago ")
				.append(", sum ( pd.amountsep ) AS amountsep ")
				.append(", sum ( pd.amountoct ) AS amountoct ")
				.append(", sum ( pd.amountnov ) AS amountnov ")
				.append(", sum ( pd.amountdic ) AS amountdic ")
				.append(nivel == 14 ? ", pd.Tipo, pd.Line " : ", pd.Tipo ")
				.append(" FROM   EXME_PresupuestoDet      pd ")
				.append(" INNER JOIN EXME_PresupuestoEgr    pe   ON pd.EXME_PresupuestoEgr_ID = pe.EXME_PresupuestoEgr_ID ")
				.append(reasignacion <= 0 ? ""
						: "INNER JOIN EXME_PresupuestoModif  pm   ON pd.EXME_PresupuestoModif_ID = pd.EXME_PresupuestoModif_ID")
				.append(" LEFT  JOIN AD_ClientInfo          ur   ON pd.AD_Client_ID = ur.AD_Client_ID ")
				.append(" LEFT  JOIN AD_Client              ur2  ON pd.AD_Client_ID = ur2.AD_Client_ID ")
				.append(" LEFT  JOIN EXME_ClasFuncional     fi   ON pd.EXME_ClasFuncional_ID     = fi.EXME_ClasFuncional_ID ")
				.append(" LEFT  JOIN EXME_ClasFuncional_Fun fu   ON pd.EXME_ClasFuncional_fun_ID = fu.EXME_ClasFuncional_fun_ID ")
				.append(" LEFT  JOIN EXME_ClasFuncional_Sfu sf   ON pd.EXME_ClasFuncional_Sfu_ID = sf.EXME_ClasFuncional_Sfu_ID ")
				.append(" LEFT  JOIN EXME_Reasignacion      rg   ON pd.EXME_Reasignacion_ID      = rg.EXME_Reasignacion_ID ")
				.append(" LEFT  JOIN EXME_ActInstitucional  ai   ON pd.EXME_ActInstitucional_ID  = ai.EXME_ActInstitucional_ID ")
				.append(" LEFT  JOIN EXME_ProgPresupuestal  pp   ON pd.EXME_ProgPresupuestal_ID  = pp.EXME_ProgPresupuestal_ID ")
				.append(" LEFT  JOIN EXME_ProgInstitucional pi   ON pd.EXME_ProgInstitucional_ID = pi.EXME_ProgInstitucional_ID ")
				.append(" LEFT  JOIN EXME_PartidaPres       ptda ON pd.EXME_PartidaPres_ID       = ptda.EXME_PartidaPres_ID ")
				.append(" LEFT  JOIN EXME_TipoGasto         tg   ON pd.EXME_TipoGasto_ID         = tg.EXME_TipoGasto_ID ")
				.append(" LEFT  JOIN EXME_FteFinanciamiento ff   ON pd.EXME_FteFinanciamiento_ID = ff.EXME_FteFinanciamiento_ID ")
				.append(" LEFT  JOIN C_Region               ef   ON pd.C_Region_ID  = ef.C_Region_ID ")
				.append(" LEFT  JOIN C_Project              ppi  ON pd.C_Project_ID = ppi.C_Project_ID ")
				.append(" WHERE pd.IsActive = 'Y' ")
				.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ",
						X_EXME_PresupuestoDet.Table_Name, "pd"))
				.append(" AND pd.EXME_PresupuestoEgr_ID = ? ")
				.append(where == null ? "" : where)
				// .append(nivel==14?"":nivel==1?" AND pd.EXME_ClasFuncional_ID is null":" AND pd.EXME_PartidaPres_ID > 0       ")
				// // Evita sumar los montos de los encabezados
				.append(nivel == 1 ? " AND pd.EXME_ClasFuncional_ID is null"
						: " ")
				// Evita sumar los montos de los encabezados
				.append(nivel == 13 ? " AND pd.C_Project_ID > 0 " : "")
				// Solo cuando es por proyecto
				.append(reasignacion <= 0 ? "  AND pd.EXME_PresupuestoModif_ID IS NULL "
						: " AND pm.EXME_PresupuestoModif_ID = ")
				.append(reasignacion <= 0 ? "" : reasignacion)
				.append(" GROUP BY ")
				.append(groupby == null || groupby.isEmpty() ? " ur.UnidadResponsable, ur2.Name "
						: groupby)
				.append(nivel == 14 ? ", pd.Tipo, pd.Line " : ", pd.Tipo ")
				.append(orderby == null ? "" : orderby);

		return sql;
	}
}