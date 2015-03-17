package org.compiere.model;

import java.sql.ResultSet;
import java.util.List;
import java.util.Properties;

import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.ValueNamePair;

/**
 * Modelo para la informacion de Evaluacion de la lesion de la piel
 * 
 * @author Lizeth de la Garza
 *         Septiembre 2009
 *         Modificada por Lorena Lama, Oct 2012
 */
public class MEXMEValCorporalLesion extends X_EXME_ValCorporalLesion {

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	private static CLogger		sLog				= CLogger.getCLogger(MEXMEValCorporalLesion.class);

	public MEXMEValCorporalLesion(Properties ctx, int EXME_ValCorporalLesion_ID, String trxName) {
		super(ctx, EXME_ValCorporalLesion_ID, trxName);
	}

	/**
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MEXMEValCorporalLesion(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	/**
	 * 
	 * Value = Body Part Name <br>
	 * Name = lesion Name <br>
	 * 
	 * @param ctx
	 * @param valCorporalID
	 *            body part Id
	 * @param trxName
	 * @return
	 */
	public static ValueNamePair getDetailAsString(Properties ctx, int valCorporalID, String trxName) {
		sLog.fine("MEXMEValCorporalLesion#getDetailAsString valCorporalID: " + valCorporalID);
		
		final StringBuilder sql = new StringBuilder();
		sql.append("SELECT");
		if (DB.isOracle()) { // Note: LISTAGG para Oracle11 !!
			sql.append(" wm_concat(distinct p.Name) value, wm_concat(distinct l.lesion) name ");
		} else if (DB.isPostgreSQL()) {
			sql.append(" array_to_string(ARRAY_AGG(distinct p.Name), ',') AS value, ");
			sql.append(" array_to_string(ARRAY_AGG(distinct l.lesion), ',') AS name ");
		} else {
			sql.append(" null as value, null as name ");
		}
		sql.append("FROM EXME_ValCorporalLesion l ");
		sql.append("INNER JOIN EXME_ParteCorporal p ON (l.EXME_ParteCorporal_ID = p.EXME_ParteCorporal_ID) ");
		sql.append("WHERE l.isActive='Y' ");
		sql.append("AND l.EXME_ValCorporal_ID=? ");
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name, "l"));
	
		final List<ValueNamePair> lst = Query.getListVN(sql.toString(), trxName, valCorporalID);
		return lst == null || lst.isEmpty() ? null : lst.get(0);
	}
}
