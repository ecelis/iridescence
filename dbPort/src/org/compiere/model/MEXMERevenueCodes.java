/**
 * 
 */
package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.apache.struts.util.LabelValueBean;
import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;

/**
 * @author luis
 *
 */
public class MEXMERevenueCodes extends X_EXME_RevenueCode {

	/** serialVersionUID */
	private static final long serialVersionUID = -7928728119800040094L;
	/** Static Logger */
	private static CLogger s_log = CLogger
			.getCLogger(MEXMERevenueCodes.class);

	/**
	 * @param ctx
	 * @param EXME_RevenueCode_ID
	 * @param trxName
	 */
	public MEXMERevenueCodes(Properties ctx, int EXME_RevenueCode_ID,
			String trxName) {
		super(ctx, EXME_RevenueCode_ID, trxName);
	}

	/**
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MEXMERevenueCodes(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	
	/**
	 * Obtenemos la nombre / id
	 * 
	 * @param ctx
	 * @param trxName
	 * @return
	 * @throws Exception
	 */
	public static List<LabelValueBean> getLabelValue(Properties ctx,
			String trxName) throws Exception {

		List<LabelValueBean> lista = new ArrayList<LabelValueBean>();
		
		if (ctx == null) {
			return null;
		}

		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append(" SELECT Value || ' ' || Name AS Name, EXME_RevenueCode_ID ")
		.append(" FROM EXME_RevenueCode WHERE IsActive = 'Y' ")
		.append(MEXMELookupInfo
				.addAccessLevelSQL(ctx, " ", "EXME_RevenueCode"))
		.append(" order by Case When IsNumeric (VALUE) = 'NM' then ")
		.append(" to_number(Value, '9999999999') ") // Se agrega mascara por error en postgresql. Jesus.
		.append(" Else 9999999999999999999999 End, value  ");

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				lista.add(new LabelValueBean(rs
						.getString(1), String.valueOf(rs.getInt(2))));
			}

			lista.add(new LabelValueBean(" ", "0"));
		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
		}

		return lista;

	}
	
	/**
	 * Obtenemos la nombre / id
	 * 
	 * @param ctx
	 * @param trxName
	 * @return
	 * @throws Exception
	 */
	public static HashMap<Integer,LabelValueBean> getListIds(Properties ctx,
			String trxName) throws Exception {

		HashMap<Integer,LabelValueBean> lista = new HashMap<Integer,LabelValueBean>();
		
		if (ctx == null) {
			return null;
		}

		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append(" SELECT Value , Name , EXME_RevenueCode_ID ")
		.append(" FROM EXME_RevenueCode WHERE IsActive = 'Y' ")
		.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", "EXME_RevenueCode"))
		.append(" order by Case When IsNumeric (VALUE) = 'NM' then ")
		.append(" to_number(Value, '9999999999') ")
		.append(" Else 9999999999999999999999 End, value  ");

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				lista.put(
						rs.getInt(3), 
						new LabelValueBean(rs.getString(1), rs.getString(2))
				);
			}
			
		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
		}

		return lista;

	}
	
	private String[] infoProd = null;

	public String[] getInfoProd() {
		return this.infoProd;
	}

	public void setInfoProd(String[] infoProd) {
		this.infoProd = infoProd;
	}
	
	/**
	 * Regresa el listado de codigos
	 * @param ctx
	 * @param trxName
	 * @return
	 */
	public static List<KeyNamePair> getKeyNameLst(Properties ctx,
			String trxName) {

		List<KeyNamePair> lista = new ArrayList<KeyNamePair>();
		
		if (ctx == null) {
			return null;
		}

		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append(" SELECT Value || ' ' || Name AS Name, EXME_RevenueCode_ID ")
		.append(" FROM EXME_RevenueCode WHERE IsActive = 'Y' ")
		.append(MEXMELookupInfo
				.addAccessLevelSQL(ctx, " ", "EXME_RevenueCode"))
		.append(" order by Case When IsNumeric (VALUE) = 'NM' then ")
		.append(" to_number(Value, '9999999999') ") // Se agrega mascara por error en postgresql. Jesus.
		.append(" Else 9999999999999999999999 End, value  ");

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				lista.add(new KeyNamePair(rs.getInt(2),rs.getString(1)));
			}

			lista.add(new KeyNamePair(0, " "));
		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
		}

		return lista;

	}
	/**
	 * Regresa el id del revenue apartir de un valor
	 * @param value valor de cual obtendra el id
	 * @param trxName
	 * @return
	 */
	public static int getIdFromValue(String value, String trxName){
		
		return DB.getSQLValue(trxName,
				"SELECT Exme_Revenuecode_ID from Exme_Revenuecode WHERE Value = ? AND AD_Org_ID IN(0,?) AND AD_Client_ID IN (0,?) ",
				new Object[]{value, Env.getAD_Org_ID(Env.getCtx()), Env.getAD_Client_ID(Env.getCtx())});
	}
	
	
}
