package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.util.LabelValueBean;
import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.KeyNamePair;

public class MEXMEAdmitType extends X_EXME_AdmitType{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/** log de la clase */
	private static CLogger log = CLogger.getCLogger (MEXMEAdmitType.class);
	
	public MEXMEAdmitType(final Properties ctx, final int EXME_AdmitType_ID, final String trxName) {
		super(ctx, EXME_AdmitType_ID, trxName);
	}

	public MEXMEAdmitType(final Properties ctx, final ResultSet rs, final String trxName) {
		super(ctx, rs, trxName);
	}
	
	/** @deprecated use {@link KeyNamePair} instead {@link #getList(Properties, boolean, String, boolean)}*/
	public static List<LabelValueBean>	getAdmitTypes(final Properties ctx, final boolean isNewBorn, final String order, final boolean all){
		final List<LabelValueBean> list = new ArrayList<LabelValueBean>();

		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		PreparedStatement pstmt = null;
		ResultSet result = null;

		sql.append(" SELECT EXME_ADMITTYPE.EXME_ADMITTYPE_ID, EXME_ADMITTYPE.NAME ")
		.append(" FROM EXME_ADMITTYPE ")
		.append(" WHERE EXME_ADMITTYPE.ISACTIVE = 'Y' ");
		if (!all) {
			sql.append(" AND EXME_ADMITTYPE.ISNEWBORN = ? ");
		}
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name));
		if (StringUtils.isEmpty(order)) {
			sql.append(" ORDER BY EXME_ADMITTYPE.ISDEFAULT DESC, EXME_ADMITTYPE.NAME ");
		}else {
			sql.append(" ORDER BY ")
			.append(order);
		}
		try {

			pstmt = DB.prepareStatement(sql.toString(), null);
			if (!all) {
				pstmt.setString(1, DB.TO_STRING(isNewBorn));//Lama
			}			
			result = pstmt.executeQuery();
			while (result.next()) {
				list.add(new LabelValueBean(result.getString(2), result.getString(1)));
			}
		} catch(SQLException e){
			log.log(Level.SEVERE, e.getLocalizedMessage());
		} finally {
			DB.close(result, pstmt);
		}

		return list;
	}
	
	/**
	 * Admit Types
	 * @param ctx
	 * @return
	 */
	public static List<KeyNamePair> getList(final Properties ctx, final boolean isNewBorn, final String order, final boolean all) {
		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append(" SELECT EXME_ADMITTYPE_ID, NAME ");
		sql.append(" FROM EXME_ADMITTYPE ");
		sql.append(" WHERE ISACTIVE = 'Y' ");
		if (!all) {
			sql.append(" AND ISNEWBORN = ? ");
		}
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name));
		if (StringUtils.isBlank(order)) {
			sql.append(" ORDER BY ISDEFAULT DESC,NAME ");
		} else {
			sql.append(" ORDER BY ").append(order);
		}
		List<KeyNamePair> list;
		if (all) {
			list = Query.getListKN(sql.toString(), null);
		} else {
			list = Query.getListKN(sql.toString(), null, DB.TO_STRING(isNewBorn));
		}
		return list;
	}
	
	/**
	 * Copiar valores de system a la organizacion
	 * 
	 * @param ctx
	 *            Contexto
	 * @param trxName
	 *            Transaccion
	 */
	public static void copyFromSystem(final Properties ctx, final String trxName) {
		final StringBuilder sql = new StringBuilder("select * from EXME_AdmitType where isActive = ? and AD_Client_ID = ? and AD_Org_ID = ?");
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setString(1, "Y");
			pstmt.setInt(2, 0);
			pstmt.setInt(3, 0);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				final MEXMEAdmitType tpn = new MEXMEAdmitType(ctx, 0, null);
				final MEXMEAdmitType tp = new MEXMEAdmitType(ctx, rs, null);
				PO.copyValues(tp, tpn);
				tpn.save(trxName);
			}
		} catch (Exception e) {
			log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
		}
	}

}
