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
import org.compiere.util.DB;
import org.compiere.util.KeyNamePair;

import com.ecaresoft.api.Generic;

/**
 * @author odelarosa
 * 
 */
public class MEXMEModifiers extends X_EXME_Modifiers {

	private static final long serialVersionUID = 8289247992321506643L;
	private static CLogger log = CLogger.getCLogger(MEXMEModifiers.class);
	
	/**
	 * @param ctx
	 * @param EXME_Modifiers_ID
	 * @param trxName
	 */
	public MEXMEModifiers(Properties ctx, int EXME_Modifiers_ID, String trxName) {
		super(ctx, EXME_Modifiers_ID, trxName);
	}

	/**
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MEXMEModifiers(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	public static List<MEXMEModifiers> get(Properties ctx, int ctaPacId, int ctaPacDetId, String trxName) {
		List<MEXMEClaimCodes> codes = MEXMEClaimCodes.get(ctx, ctaPacId, MCtaPacDet.Table_ID, ctaPacDetId, MEXMEModifiers.Table_ID, trxName);

		List<MEXMEModifiers> mods = new ArrayList<MEXMEModifiers>();

		for (MEXMEClaimCodes code : codes) {
			mods.add(new MEXMEModifiers(ctx, code.getRecordOrig_ID(), trxName));
		}

		return mods;
	}

	/**
	 * Para cargos a cuenta paciente
	 * @param ctx
	 * @param ctaPacId
	 * @param ctaPacDetId
	 * @param trxName
	 * @return
	 */
	public static List<MEXMEModifiers> getCargos(Properties ctx, int ctaPacId, int ctaPacDetId, String trxName) {
		List<MEXMEClaimCodes> codes = MEXMEClaimCodes.getCargos(ctx, ctaPacId, MEXMEModifiers.Table_ID, ctaPacDetId, MCtaPacDet.Table_ID, trxName);
		
		List<MEXMEModifiers> mods = new ArrayList<MEXMEModifiers>();

		for (MEXMEClaimCodes code : codes) {
			mods.add(new MEXMEModifiers(ctx, code.getRecord_ID(), trxName));
		}

		return mods;
	}
	
	@Override
	public String toString() {
		return getValue();
	}

	

	/**
	 * List
	 * @param ctx
	 * @param cbPartnerID
	 * @return
	 * @throws Throwable 
	 */
	public static List<LabelValueBean> getLstPendings() throws Throwable {
		List<LabelValueBean> claimPayments = new ArrayList<LabelValueBean>();
		
		String sql = "SELECT Value || ' ' || Name AS Name, EXME_MODIFIERS_ID  FROM EXME_Modifiers WHERE IsActive = 'Y' ";
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement(sql, null);
			rs = pstmt.executeQuery();
			while (rs.next()){
				claimPayments.add(new LabelValueBean(rs.getString(1), String.valueOf(rs.getInt(2))));
			}
			
			claimPayments.add(new LabelValueBean(" ", "0"));
		}
		catch (Exception e)
		{
			throw new Exception("error.impuesto.noExiste");
		} finally{
			DB.close(rs, pstmt);
		}
		return claimPayments;
	}
	
	
	/**
	 * Obtiene una lista de modificadores activos
	 * @deprecated use {@link KeyNamePair}
	 */
	public static List<Generic> getLstModifiers() {
		List<Generic> lstModifiers = new ArrayList<Generic>();

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT Name, EXME_MODIFIERS_ID FROM EXME_Modifiers WHERE IsActive = 'Y'");

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				lstModifiers.add(new Generic(rs.getString(COLUMNNAME_Name), String.valueOf(rs.getInt(COLUMNNAME_EXME_Modifiers_ID))));
			}
		} catch (Exception e) {
			log.log(Level.SEVERE, "MEXMEModifier.getLstModifiers - sql: " + sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
		}
		return lstModifiers;
	}
	
	/**
	 * Obtiene una lista de modificadores activos
	 */
	public static List<KeyNamePair> getLstModifiers(Properties ctx, String trxName) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT EXME_MODIFIERS_ID, Name  FROM EXME_Modifiers WHERE IsActive = 'Y'");
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name));
		return Query.getListKN(sql.toString(), trxName);
	}
	
	/**
	 * List
	 * @param ctx
	 * @param cbPartnerID
	 * @return
	 * @throws Throwable 
	 */
	public static HashMap<Integer,LabelValueBean> getLstIds() throws Throwable {
		HashMap<Integer,LabelValueBean> claimPayments = new HashMap<Integer,LabelValueBean>();
		
		String sql = "SELECT Value , Name , EXME_MODIFIERS_ID  FROM EXME_Modifiers WHERE IsActive = 'Y' ";
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement(sql, null);
			rs = pstmt.executeQuery();
			while (rs.next()){
				claimPayments.put(rs.getInt(3), new LabelValueBean(rs.getString(1), rs.getString(2)));
			}
		}
		catch (Exception e)
		{
			throw new Exception("error.impuesto.noExiste");
		} finally{
			DB.close(rs, pstmt);
		}
		return claimPayments;
	}
	
	/**
	 * Obtiene una lista de modificadores activos
	 * @return
	 */
	public static List<KeyNamePair> getLst() {
		List<KeyNamePair> lstModifiers = new ArrayList<KeyNamePair>();

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT Name, EXME_MODIFIERS_ID FROM EXME_Modifiers WHERE IsActive = 'Y'");

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				lstModifiers.add(new KeyNamePair(rs.getInt(COLUMNNAME_EXME_Modifiers_ID), rs.getString(COLUMNNAME_Name)));
			}
		} catch (Exception e) {
			log.log(Level.SEVERE, "MEXMEModifier.getLstModifiers - sql: " + sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
		}
		return lstModifiers;
	}
	
}
