/**
 * 
 */
package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.util.LabelValueBean;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.QuickSearchTables;
import org.compiere.util.Utilerias;

/**
 * @author twry
 *
 */
public class MEXMEPacienteCtaV extends X_EXME_Paciente_Cta_V {

	/** serialVersionUID */
	private static final long serialVersionUID = 6634966956849912055L;
	/** Static Logger               */
    private static CLogger      log = CLogger.getCLogger (MEXMEPacienteCtaV.class);
	/**
	 * @param ctx
	 * @param EXME_Paciente_Expediente_Cta_V_ID
	 * @param trxName
	 */
	public MEXMEPacienteCtaV(Properties ctx,
			int EXME_Paciente_Expediente_Cta_V_ID, String trxName) {
		super(ctx, EXME_Paciente_Expediente_Cta_V_ID, trxName);
		loadFullName();
	}

	
	/**
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MEXMEPacienteCtaV(Properties ctx, ResultSet rs,
			String trxName) {
		super(ctx, rs, trxName);
		loadFullName();
	}
	

	/**
	 * Actualiza los archivos de indices de lucene
	 * para la vista EXME_Paciente_Cta_V
	 * filtrando por Organizacion y Paciente
	 * Update search
	 * @param ctx
	 * @param EXME_Paciente_ID
	 * @param trxName
	 */
	public static void updateSearchPac(Properties ctx, int EXME_Paciente_ID, String trxName){
		
		for (Integer ctaPacID : MEXMECtaPac.getOfPatientByOrg(ctx, EXME_Paciente_ID,trxName,Env.getAD_Org_ID(ctx))){
			updateSearchCta(ctx, ctaPacID, trxName);
		}
		
	}
	
	/**
	 * Actualiza los archivos de indices de lucene
	 * para la vista EXME_Paciente_Cta_V
	 * filtrando por cuenta paciente
	 * @param ctx
	 * @param EXME_CtaPac_ID
	 * @param trxName
	 */
	public static void updateSearchCta(Properties ctx, int EXME_CtaPac_ID, String trxName){
		try {
			QuickSearchTables.checkTables(MEXMEPacienteCtaV.class,
					MEXMEPacienteCtaV.Table_Name, EXME_CtaPac_ID, trxName , ctx);
			log.log(Level.INFO, "MEXMEPacienteCtaV.updateSearchCta ID = "+ EXME_CtaPac_ID);
		} catch (Exception ex) {
			log.log(Level.WARNING, "QuickSearchTables.checkTables", ex);
			log.log(Level.SEVERE, "Problema actualizando EXME_CtaPac_ID = " + EXME_CtaPac_ID);
			
		}
	}

		
	
	/**
	 * Actualiza los archivos de indices de lucene
	 * para la vista EXME_Paciente_Cta_V
	 * filtrando por Aseguradora y Organizacion
	 * @param ctx
	 * @param C_BPartner_ID
	 * @param AD_Org_ID
	 * @param trxName
	 */
	
	public static void updateSearchBPartner(Properties ctx, int C_BPartner_ID, int AD_Org_ID, String trxName){
		if (AD_Org_ID==0){
			for (Integer adOrgID : MOrg.getParentOrgArray(ctx, trxName, Boolean.FALSE, Boolean.TRUE, 0)){
				for (Integer ctaPacID : MEXMEBPartner.getCtaPacByBPartner(ctx, C_BPartner_ID, adOrgID, trxName )){
					updateSearchCta(ctx, ctaPacID, trxName);
				}
			}
		}else{
			for (Integer ctaPacID : MEXMEBPartner.getCtaPacByBPartner(ctx, C_BPartner_ID, AD_Org_ID, trxName )){
				updateSearchCta(ctx, ctaPacID, trxName);
			}
		}	
	}
	
	/**
	 * Actualiza los archivos de indices de lucene
	 * para la vista EXME_Paciente_Cta_V
	 * filtrando por Payer Class y Organizacion
	 * @param ctx
	 * @param C_BPartner_ID
	 * @param AD_Org_ID
	 * @param trxName
	 */
	
	public static void updateSearchPClass(Properties ctx, int EXME_PayerClass_ID, String trxName){
		for (LabelValueBean lvb : Utilerias.getListLabelValue(ctx, MBPartner.Table_Name,
				MBPartner.COLUMNNAME_AD_Org_ID,
				MBPartner.COLUMNNAME_C_BPartner_ID, " WHERE EXME_PayerClass_ID = ? ", Boolean.FALSE, EXME_PayerClass_ID)){
				updateSearchBPartner(ctx, Integer.valueOf(lvb.getValue()), Integer.valueOf(lvb.getLabel()),  trxName);
		}
	}
	
	
	public static List<MEXMEPacienteCtaV> getEncounterByStatus(Properties ctx, String where, Object[] params, String orderBy, String trxName) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ")
		   .append("  * ")
		   .append("FROM ")
		   .append("  EXME_Paciente_Cta_V ")
		   .append(" WHERE IsActive ='Y' ")
		   .append((StringUtils.isEmpty(where) ? ""  : where));
		sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, sql.toString(), MEXMEPacienteCtaV.Table_Name));
		if (StringUtils.isNotBlank(orderBy)){
			sql.append(orderBy);
		}
		
		List<MEXMEPacienteCtaV> lstDiv = new ArrayList<MEXMEPacienteCtaV>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			if (params != null && params.length > 0) {
				DB.setParameters(pstmt, params);
			}
			rs = pstmt.executeQuery();
			while (rs.next()) {
				lstDiv.add(new MEXMEPacienteCtaV(ctx, rs, trxName));
			}
		} catch (Exception e) {
			log.log(Level.SEVERE, null, e);
		} finally {
			DB.close(rs, pstmt);
		}
		return lstDiv;
	}
	
	public static List<MEXMEPacienteCtaV> getCtasBalanceDebtor(Properties ctx, String where, Object[] params, String orderBy, boolean all, String trxName){
		List<MEXMEPacienteCtaV> lstCtas = new ArrayList<MEXMEPacienteCtaV>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * from( ")
			.append("	SELECT  exme_paciente_cta_v.* ,  ( ")
			.append(" 		(SELECT coalesce(SUM(CPD.LineNetAmt),0) ")
			.append(" 			FROM EXME_CtaPacDet CPD ")
			.append(" 			WHERE CPD.EXME_CtaPac_ID = exme_paciente_cta_v.EXME_CtaPac_ID and CPD.IsActive = 'Y') - ")
			.append(" 		(SELECT coalesce(SUM(P.PayAmt),0) ")
			.append(" 			FROM C_Payment P INNER JOIN C_CHARGE C ON C.C_CHARGE_ID = P.C_CHARGE_ID AND C.TYPE NOT IN ('I','D','C') ")
			.append(" 			WHERE P.EXME_CtaPac_ID = exme_paciente_cta_v.EXME_CtaPac_ID and P.IsActive = 'Y') ")
			.append(" 					)as Balance ")
			.append(" 	FROM exme_paciente_cta_v ")
			.append((StringUtils.isEmpty(where) ? " WHERE IsActive ='Y' " : where));
			sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, sql.toString(), MEXMEPacienteCtaV.Table_Name));
			sql.append(" ) sbq ");
			if(!all){
				sql.append(" WHERE  Balance > 0 ");		
			}
		if (StringUtils.isNotBlank(orderBy)){
			sql.append(orderBy);
		}
		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			if (params != null && params.length > 0) {
				DB.setParameters(pstmt, params);
			}
			rs = pstmt.executeQuery();
			while (rs.next()) {
				lstCtas.add(new MEXMEPacienteCtaV(ctx, rs, trxName));
			}
		} catch (Exception e) {
			log.log(Level.SEVERE, null, e);
		} finally {
			DB.close(rs, pstmt);
		}
		return lstCtas;
	}
	
	private void loadFullName() {
		// Cargamos el nombre completo del Guarantor.
		StringBuffer fullName = new StringBuffer();
		
		if (StringUtils.isNotEmpty(getLast_Name())) {
			fullName.append(getLast_Name());
		}
		if (StringUtils.isNotEmpty(getName())) {
			if (fullName.length() > 0) {
				fullName.append(" ");
			}
			fullName.append(getName());
		}
		if (StringUtils.isNotEmpty(getName2())) {
			if (fullName.length() > 0) {
				fullName.append(" ");
			}
			fullName.append(getName2());
		}
		
		setGuar_Name(fullName.toString());
		
	}
	
}