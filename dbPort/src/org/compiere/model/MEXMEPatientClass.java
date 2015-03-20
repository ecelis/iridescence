/* Created on 30-abr-2005 */

package org.compiere.model;

import java.sql.ResultSet;
import java.util.List;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.compiere.util.Constantes;
import org.compiere.util.KeyNamePair;

/**
 * @author hassan reyes
 * @author rsolorzano
 */
public class MEXMEPatientClass extends X_EXME_PatientClass {

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	/** Static Logger */
//	private static CLogger		s_log				= CLogger.getCLogger(MEXMEPatientClass.class);

	/**
	 * @param ctx
	 * @param EXME_TipoPaciente_ID
	 * @param trxName
	 */
	public MEXMEPatientClass(Properties ctx, int EXME_PatientClass_ID, String trxName) {
		super(ctx, EXME_PatientClass_ID, trxName);
	}

	/**
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MEXMEPatientClass(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	/**
	 * Obtenemos los tipos de paciente para una cliente + organizacion.
	 * 
	 * @param ctx
	 * @param trxName
	 * @param whereClause opcional
	 * @param orderBy opcional
	 * @return
	 */
	public static List<MEXMEPatientClass> get(Properties ctx, String trxName, String whereClause, String orderBy) {
		return new Query(ctx, Table_Name, whereClause, trxName)//
			.setOnlyActiveRecords(true)//
			.addAccessLevelSQL(true)//
			.setOrderBy(StringUtils.isNotBlank(orderBy) ? orderBy : " Name ").list();

	}

	/**
	 * Obtenemos los tipos de paciente para una cliente + organizacion.
	 * 
	 * @param ctx
	 * @param trxName
	 * @param whereClause opcional
	 * @param orderBy opcional
	 * @return
	 */
	public static List<KeyNamePair> getList(Properties ctx, String trxName, String whereClause, String orderBy) {
		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append("SELECT EXME_PatientClass_ID,Name FROM EXME_PatientClass WHERE isActive = 'Y' ");
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name));
		if (whereClause != null) {
			sql.append(whereClause);
		}
		if (StringUtils.isNotBlank(orderBy)) {
			sql.append(" ORDER BY ").append(orderBy);
		} else {
			sql.append(" ORDER BY Name ");
		}
		return Query.getListKN(sql.toString(), trxName);
	}

//	/**
//	 * Lista tipos de paciente (LabelValueBean)
//	 * 
//	 * @param ctx
//	 * @param trxName
//	 * @param whereClause
//	 * @param orderBy
//	 * @return
//	 * @author lhernandez
//	 * @deprecated
//	 */
//	public static List<LabelValueBean> getLVB(Properties ctx, String trxName, String whereClause, String orderBy) {
//		final List<LabelValueBean> list = new ArrayList<LabelValueBean>();
//		for (MEXMEPatientClass tp : get(ctx, trxName, whereClause, orderBy)) {
//			list.add(new LabelValueBean(tp.getName(), String.valueOf(tp.getEXME_PatientClass_ID())));
//		}
//		return list;
//	}
}