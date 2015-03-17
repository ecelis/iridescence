package org.compiere.model;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.apache.struts.util.LabelValueBean;
import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.Env;

/**
 * 
 * @author Expert
 *
 */
public class MEXMEBeneficios extends X_EXME_Beneficios {
	
	/** serialVersionUID .*/
	private static final long serialVersionUID = 1L;
	
	/**	Static Logger				*/
	private static CLogger		s_log = CLogger.getCLogger (MEXMEBeneficios.class);

	/**
	 * constructor
	 * @param ctx
	 * @param EXME_Beneficios_ID
	 * @param trxName
	 */
	public MEXMEBeneficios(Properties ctx, int EXME_Beneficios_ID, String trxName) {
		super(ctx, EXME_Beneficios_ID, trxName);
	}
	
	public MEXMEBeneficios(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	/**
	 * EXME_TIPOPROD_ID, NAMEBEN
	 * @param EXME_Paciente_ID
	 * @param ctx
	 * @param trxName
	 * @return
	 */
	public static List<X_EXME_TipoProd> getBenefitType(int EXME_Paciente_ID, Properties ctx, String trxName) {
		ArrayList<X_EXME_TipoProd> listb = new ArrayList<X_EXME_TipoProd>();
		
		StringBuffer sql = new StringBuffer(Constantes.INIT_CAPACITY_ARRAY);
		
		sql.append("SELECT D.EXME_TIPOPROD_ID, D.NAMEBEN , count(*) as total FROM ( ")
		 .append(" SELECT TP.EXME_TIPOPROD_ID, TP.NAME AS NAMEBEN, SE.NAME AS NAME1, PQ.NAME AS NAME2 ")
		 .append(" FROM  EXME_BENEFICIOS B INNER JOIN EXME_TIPOPROD TP ON TP.EXME_TIPOPROD_ID = B.EXME_TIPOPROD_ID ")
		 						.append(" INNER JOIN EXME_STATUSELEG SE ON SE.EXME_STATUSELEG_ID = B.EXME_STATUSELEG_ID ")
		 						.append(" INNER JOIN EXME_PERIODQUAL PQ ON PQ.EXME_PERIODQUAL_ID = B.EXME_PERIODQUAL_ID ")
		 .append(" WHERE B.EXME_BENEFICIOSH_ID IN (SELECT EXME_BENEFICIOSH_ID FROM EXME_VISTABENEFICIOS WHERE EXME_PACIENTE_ID = ?) ")
		 .append(" AND SE.NAME IN ('Deductible', 'Out of Pocket (Stop Loss)') ")
		 .append(" AND PQ.NAME IN ('Year to Date', 'Remaining') ")
		 .append(" GROUP BY TP.EXME_TIPOPROD_ID, TP.NAME, SE.NAME, PQ.NAME) D ")
		 .append(" GROUP BY D.EXME_TIPOPROD_ID, D.NAMEBEN ");
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, EXME_Paciente_ID);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				if (rs.getInt("total") == 4) {
					X_EXME_TipoProd tprod = new X_EXME_TipoProd(ctx, rs.getInt("EXME_TIPOPROD_ID"), trxName);
					listb.add(tprod);
				}
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, "getBenefitType : " + sql, e);
		} finally {
			DB.close(rs, pstmt);
		}
		return listb;
	}
	
	/**
	 * EXME_TIPOPROD_ID, NAME (del tipo de producto)
	 * @param EXME_Paciente_ID
	 * @param ctx
	 * @param trxName
	 * @return
	 */
	public static List<LabelValueBean> getBenefitAll(int EXME_Paciente_ID, Properties ctx, String trxName) {
		ArrayList<LabelValueBean> listb = new ArrayList<LabelValueBean>();
		
		StringBuffer sql = new StringBuffer(Constantes.INIT_CAPACITY_ARRAY);
		
		sql.append("SELECT TP.EXME_TIPOPROD_ID, TP.NAME AS NAMEBEN ")
		  .append(" FROM  EXME_BENEFICIOS B INNER JOIN EXME_TIPOPROD TP ON TP.EXME_TIPOPROD_ID = B.EXME_TIPOPROD_ID ") 
                                  .append(" INNER JOIN EXME_STATUSELEG SE ON SE.EXME_STATUSELEG_ID = B.EXME_STATUSELEG_ID ") 
                                  .append(" INNER JOIN EXME_PERIODQUAL PQ ON PQ.EXME_PERIODQUAL_ID = B.EXME_PERIODQUAL_ID ") 
          .append(" WHERE B.EXME_BENEFICIOSH_ID IN (SELECT EXME_BENEFICIOSH_ID FROM EXME_VISTABENEFICIOS WHERE EXME_PACIENTE_ID = ?) ") 
          .append(" GROUP BY TP.EXME_TIPOPROD_ID, TP.NAME ")
          .append(" ORDER BY TP.NAME ");
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, EXME_Paciente_ID);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				LabelValueBean tprod = new LabelValueBean(rs.getString("NAMEBEN"), rs.getString("EXME_TIPOPROD_ID"));
				listb.add(tprod);
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, "getBenefitApp : " + sql, e);
		} finally {
			DB.close(rs, pstmt);
		}
		return listb;
	}
	
	/**
	 * EXME_TIPOPROD_ID, NAMEBEN 
	 * @param EXME_Paciente_ID
	 * @param ctx
	 * @param trxName
	 * @return
	 */
	public static List<LabelValueBean> getBenefitApp(int EXME_Paciente_ID, Properties ctx, String trxName) {
		ArrayList<LabelValueBean> listb = new ArrayList<LabelValueBean>();
		
		StringBuffer sql = new StringBuffer(Constantes.INIT_CAPACITY_ARRAY);
		
		sql.append("SELECT D.EXME_TIPOPROD_ID, D.NAMEBEN , count(*) as total FROM ( ")
		 .append(" SELECT TP.EXME_TIPOPROD_ID, TP.NAME AS NAMEBEN, SE.NAME AS NAME1, PQ.NAME AS NAME2 ")
		 .append(" FROM  EXME_BENEFICIOS B INNER JOIN EXME_TIPOPROD TP ON TP.EXME_TIPOPROD_ID = B.EXME_TIPOPROD_ID ")
		 						.append(" INNER JOIN EXME_STATUSELEG SE ON SE.EXME_STATUSELEG_ID = B.EXME_STATUSELEG_ID ")
		 						.append(" INNER JOIN EXME_PERIODQUAL PQ ON PQ.EXME_PERIODQUAL_ID = B.EXME_PERIODQUAL_ID ")
		 .append(" WHERE B.EXME_BENEFICIOSH_ID IN (SELECT EXME_BENEFICIOSH_ID FROM EXME_VISTABENEFICIOS WHERE EXME_PACIENTE_ID = ?) ")
		 .append(" AND SE.NAME IN ('Deductible', 'Out of Pocket (Stop Loss)') ")
		 .append(" AND PQ.NAME IN ('Year to Date', 'Remaining') ")
		 .append(" GROUP BY TP.EXME_TIPOPROD_ID, TP.NAME, SE.NAME, PQ.NAME) D ")
		 .append(" GROUP BY D.EXME_TIPOPROD_ID, D.NAMEBEN ");
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, EXME_Paciente_ID);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				if (rs.getInt("total") == 4) {
					LabelValueBean tprod = new LabelValueBean(rs.getString("NAMEBEN"), rs.getString("EXME_TIPOPROD_ID"));
					listb.add(tprod);
				}
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, "getBenefitApp : " + sql, e);
		} finally {
			DB.close(rs, pstmt);
		}
		return listb;
	}
	
	/**
	 * AMOUNT de EXME_BENEFICIOS
	 * @param EXME_Paciente_ID
	 * @param EXME_TipoProd_ID
	 * @param statusEleg
	 * @param periodQual
	 * @return
	 */
	public static BigDecimal getBenefitAmt(int EXME_Paciente_ID, int EXME_TipoProd_ID, String statusEleg, String periodQual) {
		BigDecimal res = BigDecimal.ZERO;
		StringBuffer sql = new StringBuffer(Constantes.INIT_CAPACITY_ARRAY);
		sql.append("SELECT B.AMOUNT ")
		  .append(" FROM   EXME_BENEFICIOS B INNER JOIN EXME_STATUSELEG SE ON SE.EXME_STATUSELEG_ID = B.EXME_STATUSELEG_ID ")
                                   .append(" INNER JOIN EXME_PERIODQUAL PQ ON PQ.EXME_PERIODQUAL_ID = B.EXME_PERIODQUAL_ID ")
                                   .append(" INNER JOIN EXME_COVERAGETYPE CT ON CT.EXME_COVERAGETYPE_ID = B.EXME_COVERAGETYPE_ID ")
          .append(" WHERE  B.ISACTIVE = 'Y' ")
          .append(" AND    CT.NAME = 'Individual' ")
          .append(" AND    B.EXME_BENEFICIOSH_ID IN (SELECT EXME_BENEFICIOSH_ID FROM EXME_VISTABENEFICIOS WHERE EXME_PACIENTE_ID = ?) ")
          .append(" AND    B.EXME_TIPOPROD_ID = ? ")
          .append(" AND    UPPER(SE.NAME) = ? ")
          .append(" AND    UPPER(PQ.NAME) = ? ")
          .append(" AND    B.ISPLANNETWORK = 'Y'");
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setInt(1, EXME_Paciente_ID);
			pstmt.setInt(2, EXME_TipoProd_ID);
			pstmt.setString(3, statusEleg);
			pstmt.setString(4, periodQual);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				res = rs.getBigDecimal("AMOUNT");
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, "getBenefitAmt : " + sql, e);
		} finally {
			DB.close(rs, pstmt);
		}
		return res;
	}
	
	/**
	 * Obtiene listado de detalles de una solicitud de beneficios
	 */
	
	public static List<MEXMEBeneficios> getBenefits(Properties ctx, int EXME_BeneficiosH_ID, String trxName) {
		return new Query(ctx, Table_Name, " EXME_BeneficiosH_ID = ? ", trxName)
		.setParameters(EXME_BeneficiosH_ID)
		.list();
	}
	
	
	/**
	 * AMOUNT de EXME_BENEFICIOS
	 * @param EXME_Paciente_ID
	 * @param EXME_TipoProd_ID
	 * @param statusEleg
	 * @param periodQual
	 * @return
	 */
	public static List<MEXMEVistaBeneficios> getBenefit(Properties ctx, 
			int exmePacienteID, int exmeBeneficiosHID,
			int exmeCtaPacID, int cBPartnerID) {
	
		List<MEXMEVistaBeneficios> res = new ArrayList<MEXMEVistaBeneficios>();
		List<Integer> params = new ArrayList<Integer>();
		StringBuffer sql = new StringBuffer(Constantes.INIT_CAPACITY_ARRAY);
		sql.append("SELECT b.* ")
		  .append(" FROM   EXME_BENEFICIOSh bh ")
		  .append(" inner join EXME_BENEFICIOS b on b.EXME_BENEFICIOSh_id = bh.EXME_BENEFICIOSh_id ")
          .append(" WHERE  Bh.ISACTIVE = 'Y' ")
          .append(" AND (  bh.endDate > "+ DB.TO_DATE(DB.getTimestampForOrg(Env.getCtx())) + " OR  bh.endDate is null ) ");
		
		if(exmePacienteID>0){
			sql.append(" AND    bh.EXME_Paciente_ID = ? ");
			params.add(exmePacienteID);
		}
		if(exmeBeneficiosHID>0){
			sql.append(" and bh.EXME_BENEFICIOSh_ID = ? ");
			params.add(exmeBeneficiosHID);
		}
		if(exmeCtaPacID>0){
			sql.append(" AND    bh.EXME_CtaPac_ID = ? ");
			params.add(exmeCtaPacID);
		}
		if(cBPartnerID>0){
			sql.append(" and bh.C_BPartner_ID = ? ");
			params.add(cBPartnerID);
		}
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			DB.setParameters(pstmt, params);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				MEXMEBeneficios ben = new MEXMEBeneficios(ctx, rs, null);
				
				MEXMEVistaBeneficios vben = new MEXMEVistaBeneficios(ctx, 0, null);
				vben.setEXME_BeneficiosH_ID(ben.getEXME_BeneficiosH_ID());
				vben.setEXME_TipoProd_ID(ben.getEXME_TipoProd_ID());
				vben.setEXME_InsuranceType_ID(ben.getEXME_InsuranceType_ID());
				vben.setEXME_Beneficios_ID(ben.getEXME_Beneficios_ID());
				
				if(ben.isPlanNetwork()){
					vben.setValores_In(ben.getPlanCoverage());
					vben.setIsData_In(true);
					vben.setIsData_Out(false);
				} else {
					vben.setValores_Out(ben.getPlanCoverage());
					vben.setIsData_In(false);
					vben.setIsData_Out(true);
				}
				res.add(vben);
			}
			
		} catch (Exception e) {
			s_log.log(Level.SEVERE, "getBenefitAmt : " + sql, e);
		} finally {
			DB.close(rs, pstmt);
		}
		return res;
	}
}
