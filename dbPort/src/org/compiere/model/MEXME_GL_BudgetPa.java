package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Vector;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.Env;

public class MEXME_GL_BudgetPa extends X_EXME_GL_BudgetPa {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static CLogger s_log = CLogger.getCLogger(MEXME_GL_BudgetPa.class);
	
	public MEXME_GL_BudgetPa(Properties ctx, int EXME_GL_BUDGETPA_ID,
			String trxName) {
		super(ctx, EXME_GL_BUDGETPA_ID, trxName);
		/** if (EXME_GL_BUDGETPA_ID == 0)
		{
		setEXME_GL_BudgetPa_ID (0);
		setEXME_Partida_ID (0);
		setGL_Budget_ID (0);
		setIsDistributed (false);
		setPre_Solicitado (Env.ZERO);
		}
		 */
	}
	
	public MEXME_GL_BudgetPa(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}
	
	public MEXME_GL_BudgetPaPe[] getLines(int gl_budgetpa_id) {
		/*
		 * if (m_lines != null) return m_lines;
		 */
		List<MEXME_GL_BudgetPaPe> list = new ArrayList<MEXME_GL_BudgetPaPe>();
		String sql = "select * from exme_gl_budgetpape where isactive='Y' and exme_gl_budgetpa_id=?"
					+ " order by periodo";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql, get_TrxName());
			pstmt.setInt(1, gl_budgetpa_id);
			rs = pstmt.executeQuery();
			//MEXME_GL_BudgetPa part=null;
			while (rs.next()){
				/*part=new MEXME_GL_BudgetPa(getCtx(),0,null);
				part.setEXME_GL_BudgetPa_ID(rs.getInt(1));
				part.setName(rs.getString(2));
				part.setDescription(rs.getString(3));
				part.setPre_Solicitado(rs.getBigDecimal(4));
				part.setIsDistributed(rs.getBoolean(5));
				list.add(part);*/
				list.add(new MEXME_GL_BudgetPaPe(getCtx(), rs, get_TrxName()));
			}
			
		} catch (Exception e) {
			s_log.log(Level.SEVERE, "getLines - sql = " + sql, e);
		} finally {
			DB.close(rs, pstmt);
		}
		
		MEXME_GL_BudgetPaPe[] m_detalles = new MEXME_GL_BudgetPaPe[list.size()];
		list.toArray(m_detalles);
		return m_detalles;
	}

	public Vector<String> getPartidaNameValue(int exme_partida_id) {
		
		Vector<String> list = new Vector<String>();
		String sql = "select value, name from exme_partida where exme_partida_id=?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql, get_TrxName());
			pstmt.setInt(1, exme_partida_id);
			rs = pstmt.executeQuery();
			
			while (rs.next()){
				list.add(rs.getString(1));
				list.add(rs.getString(2));
			}
			
		} catch (Exception e) {
			s_log.log(Level.SEVERE, "getPartidaNameValue - sql = " + sql, e);
		} finally {
			DB.close(rs, pstmt);
		}
		return list;
	}
	
	public Vector<String> getPartidaAdOrgTrx(int ad_orgtrx_id) {
		
		Vector<String> list = new Vector<String>();
		String sql = "select name from ad_org where ad_org_id=?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql, get_TrxName());
			pstmt.setInt(1, ad_orgtrx_id);
			rs = pstmt.executeQuery();
			
			while (rs.next()){
				list.add(rs.getString(1));
			}
			
		} catch (Exception e) {
			s_log.log(Level.SEVERE, "getPartidaAdOrgTrx - sql = " + sql, e);
		} finally {
			DB.close(rs, pstmt);
		}
		return list;
	}
	
	public static MEXME_GL_BudgetPa getBudgetPa(int budgetID, String tipo, int recordID, int acctschemaID){
  		
		MEXME_GL_BudgetPa budgetPa = null;
		
		//String sql = null;
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);

		
		if (tipo.equalsIgnoreCase(MOrder.Table_Name)){
			/*sql = "SELECT  CE.EXME_PARTIDA_ID " +
			     "FROM M_PRODUCT_ACCT MP " +
			     "INNER JOIN C_VALIDCOMBINATION CV " +
			     "		ON CV.C_VALIDCOMBINATION_ID = MP.COMMITMENTOFFSET_ACCT " +
			     "INNER JOIN C_ELEMENTVALUE  CE " +
			     "		ON CE.C_ELEMENTVALUE_ID = CV.ACCOUNT_ID " +
				 " WHERE MP.M_PRODUCT_ID = ? AND MP.C_ACCTSCHEMA_ID = ? ";*/
			sql.append("SELECT  CE.EXME_PARTIDA_ID ");
			sql.append("FROM M_PRODUCT_ACCT MP ");
			sql.append("INNER JOIN C_VALIDCOMBINATION CV ");
			sql.append("		ON CV.C_VALIDCOMBINATION_ID = MP.COMMITMENTOFFSET_ACCT ");
			sql.append("INNER JOIN C_ELEMENTVALUE  CE ");
			sql.append("		ON CE.C_ELEMENTVALUE_ID = CV.ACCOUNT_ID ");
			sql.append(" WHERE MP.M_PRODUCT_ID = ? AND MP.C_ACCTSCHEMA_ID = ? ");
		}else if (tipo.equalsIgnoreCase(MInvoice.Table_Name)){
			/*sql = " SELECT  CE.EXME_PARTIDA_ID "+
				  " FROM M_PRODUCT_ACCT MP  "+
				  " INNER JOIN C_VALIDCOMBINATION CV ON CV.C_VALIDCOMBINATION_ID = MP.P_INVENTORYCLEARING_ACCT "+
				  " INNER JOIN C_ELEMENTVALUE  CE ON CE.C_ELEMENTVALUE_ID = CV.ACCOUNT_ID "+
				  " WHERE MP.M_PRODUCT_ID = ? AND MP.C_ACCTSCHEMA_ID = ? ";*/
			
			sql.append(" SELECT  CE.EXME_PARTIDA_ID ");
			sql.append(" FROM M_PRODUCT_ACCT MP  ");
			sql.append(" INNER JOIN C_VALIDCOMBINATION CV ON CV.C_VALIDCOMBINATION_ID = MP.P_INVENTORYCLEARING_ACCT ");
			sql.append(" INNER JOIN C_ELEMENTVALUE  CE ON CE.C_ELEMENTVALUE_ID = CV.ACCOUNT_ID ");
			sql.append(" WHERE MP.M_PRODUCT_ID = ? AND MP.C_ACCTSCHEMA_ID = ? ");
		
		}else if (tipo.equalsIgnoreCase(MPayment.Table_Name)){
			/*sql = " SELECT  CE.EXME_PARTIDA_ID "+
				  " FROM  M_PRODUCT_ACCT MP "+
				  " INNER JOIN C_VALIDCOMBINATION CV ON CV.C_VALIDCOMBINATION_ID = MP.B_PaymentSelect_Acct "+ 
				  " INNER JOIN C_ELEMENTVALUE  CE ON CE.C_ELEMENTVALUE_ID = CV.ACCOUNT_ID "+
				  " WHERE MP.M_PRODUCT_ID = ? AND MP.C_ACCTSCHEMA_ID = ? ";*/
			
			sql.append(" SELECT  CE.EXME_PARTIDA_ID ");
			sql.append(" FROM M_PRODUCT_ACCT MP  ");
			sql.append(" INNER JOIN C_VALIDCOMBINATION CV ON CV.C_VALIDCOMBINATION_ID = MP.B_PaymentSelect_Acct ");
			sql.append(" INNER JOIN C_ELEMENTVALUE  CE ON CE.C_ELEMENTVALUE_ID = CV.ACCOUNT_ID ");
			sql.append(" WHERE MP.M_PRODUCT_ID = ? AND MP.C_ACCTSCHEMA_ID = ? ");
		}
  		
  		PreparedStatement pstmt = null;
  		ResultSet rs = null;
  		
  		try{
  			pstmt = DB.prepareStatement(sql.toString(), null);
  			
  				pstmt.setInt(1, recordID);
  				pstmt.setInt(2, acctschemaID);
  				
  			rs = pstmt.executeQuery();
  			
  			if(rs.next())
 				budgetPa = getBudgetPa(budgetID, rs.getInt(1));
  			  			
  		} catch (SQLException e) {
  			s_log.log(Level.SEVERE, "getBudgetPa - sql = " + sql, e);
  		} finally {
  			DB.close(rs, pstmt);
  		}
  		
  		return budgetPa;
  	}
	
	public static MEXME_GL_BudgetPa getBudgetPa(int budgetID, int partidaID){
  		
		MEXME_GL_BudgetPa budgetPa = null;
		
  		String sql = "SELECT * FROM EXME_GL_BUDGETPA WHERE GL_BUDGET_ID = ? AND EXME_PARTIDA_ID = ?";
  		PreparedStatement pstmt = null;
  		ResultSet rs = null;
  		
  		try{
  			pstmt = DB.prepareStatement(sql, null);
  			
  			pstmt.setInt(1, budgetID);
  			pstmt.setInt(2, partidaID);
  			rs = pstmt.executeQuery();
  			
  			if(rs.next())
 				budgetPa = new MEXME_GL_BudgetPa(Env.getCtx(), rs, null);
  			  			
  		} catch (SQLException e) {
  			s_log.log(Level.SEVERE, "getBudgetPa - sql = " + sql, e);
  		} finally {
  			DB.close(rs, pstmt);
  		}
  		
  		return budgetPa;
  	}

	
	Vector<String> targetPartida;

	public Vector<String> getPartida(int partida_id) {

		// if (targetName != null)
		// return targetName;

		// String value;
		/*
		 * if (!isSOTrx()){ value = "Y";
		 * System.out.println("******** DEBUG: isSOTrx = false"); }else{ value =
		 * "N"; System.out.println("******** DEBUG: isSOTrx = true"); }
		 * System.out
		 * .println("************ DEBUG: isSOTrx valor de de value: "+value);
		 */
//		String sql = "select * from exme_gl_budgetpa WHERE isactive='Y' and exme_gl_budgetpa_id=" + partida_id;
		
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append("select * from exme_gl_budgetpa WHERE isactive='Y' and exme_gl_budgetpa_id=");
		sql.append(partida_id);
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			pstmt = DB.prepareStatement(sql.toString(), get_TrxName());
			// pstmt.setString(1, value);
			rs = pstmt.executeQuery();
			targetPartida = new Vector<String>();

			while (rs.next()) {
				for (int i = 1; i <= 19; i++)
					targetPartida.add(rs.getString(i));
			}
 		} catch (Exception e) {
  			s_log.log(Level.SEVERE, "getPartida - sql = " + sql, e);
  		} finally {
  			DB.close(rs, pstmt);
  		}

		return targetPartida;
	}
	
	Vector<String> targetPartidasIDs;

	public Vector<String> getPartidasIDs(int gl_b) {

		// if (targetName != null)
		// return targetName;

		// String value;
		/*
		 * if (!isSOTrx()){ value = "Y";
		 * System.out.println("******** DEBUG: isSOTrx = false"); }else{ value =
		 * "N"; System.out.println("******** DEBUG: isSOTrx = true"); }
		 * System.out
		 * .println("************ DEBUG: isSOTrx valor de de value: "+value);
		 */
		StringBuilder sql = new StringBuilder (Constantes.INIT_CAPACITY_ARRAY);
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			
			sql.append("select exme_gl_budgetpa_id from exme_gl_budgetpa WHERE isactive='Y' and gl_budget_id=");
			sql.append(gl_b);

			pstmt = DB.prepareStatement(sql.toString(), get_TrxName());
			// pstmt.setString(1, value);
			rs = pstmt.executeQuery();
			targetPartidasIDs = new Vector<String>();

			while (rs.next()) {
				targetPartidasIDs.add(rs.getString(1));
			}
 		} catch (Exception e) {
  			s_log.log(Level.SEVERE, "getPartidasIDs - sql = " + sql.toString(), e);
  		} finally {
  			DB.close(rs, pstmt);
  		}

		return targetPartidasIDs;
	}
	
	Vector<Double> targetComprometido;
	double d = 0.0;
	
	public double getComprometido(int glb_id) {
		
 		double acum = 0.0;
 		String sql = "select pre_comprometido from exme_gl_budgetpa where exme_gl_budgetpa_id=?";
 		PreparedStatement pstmt = null;
 		ResultSet rs = null;
 		
 		try {

 			pstmt = DB.prepareStatement(sql, get_TrxName());
 			pstmt.setInt(1, glb_id);
 			rs = pstmt.executeQuery();
 			
 			targetComprometido = new Vector<Double>();
 			 			
 			while (rs.next()) {
 				targetComprometido.add(rs.getDouble(1));
 			}
 			 			
 			for(int i=0 ;i < targetComprometido.size();i++){
 				d = targetComprometido.get(i);
 				acum = acum + d;
 			}
 		} catch (Exception e) {
  			s_log.log(Level.SEVERE, "getComprometido - sql = " + sql, e);
  		} finally {
  			DB.close(rs, pstmt);
  		}

 		return Math.round(acum*Math.pow(10,2))/Math.pow(10,2);
 		
 	}
	
	Vector<Double> targetDevengado;
	double dd = 0.0;
	
	public double getDevengado(int glb_id) {
		
 		double acumd = 0.0;
 		String sql = "select pre_devengado from exme_gl_budgetpa where exme_gl_budgetpa_id=?";
 		PreparedStatement pstmt = null;
 		ResultSet rs = null;
 		
 		try {

 			pstmt = DB.prepareStatement(sql, get_TrxName());
 			pstmt.setInt(1, glb_id);
 			rs = pstmt.executeQuery();
 			
 			targetDevengado = new Vector<Double>();
 			 			
 			while (rs.next()) {
 				targetDevengado.add(rs.getDouble(1));
 			}
 			 			
 			for(int i=0 ;i < targetDevengado.size();i++){
 				dd = targetDevengado.get(i);
 				acumd = acumd + dd;
 			}
 		} catch (Exception e) {
  			s_log.log(Level.SEVERE, "getDevengado - sql = " + sql, e);
  		} finally {
  			DB.close(rs, pstmt);
  		}

 		return Math.round(acumd*Math.pow(10,2))/Math.pow(10,2);
 		
 	}
	
	Vector<Double> targetEjercido;
	double ddd = 0.0;
	
	public double getEjercido(int glb_id) {
		
 		double acumdd = 0.0;
 		String sql = "select pre_ejercido from exme_gl_budgetpa where exme_gl_budgetpa_id=?";
 		PreparedStatement pstmt = null;
 		ResultSet rs = null;
 		
 		try {

 			pstmt = DB.prepareStatement(sql, get_TrxName());
 			pstmt.setInt(1, glb_id);
 			rs = pstmt.executeQuery();
 			
 			targetEjercido = new Vector<Double>();
 			 			
 			while (rs.next()) {
 				targetEjercido.add(rs.getDouble(1));
 			}
 			 			
 			for(int i=0 ;i < targetEjercido.size();i++){
 				ddd = targetEjercido.get(i);
 				acumdd = acumdd + ddd;
 			}
 		} catch (Exception e) {
  			s_log.log(Level.SEVERE, "getEjercido - sql = " + sql, e);
  		} finally {
  			DB.close(rs, pstmt);
  		}

 		return Math.round(acumdd*Math.pow(10,2))/Math.pow(10,2);
 		
 	}
	Vector<Double> targetAutorizado;
	double dddd = 0.0;
	
	public double getAutorizado(int glb_id) {
		
 		double acumddd = 0.0;
 		String sql = "select pre_autorizado from exme_gl_budgetpa where exme_gl_budgetpa_id=?";
 		PreparedStatement pstmt = null;
 		ResultSet rs = null;
 		
 		try {

 			pstmt = DB.prepareStatement(sql, get_TrxName());
 			pstmt.setInt(1, glb_id);
 			rs = pstmt.executeQuery();
 			
 			targetAutorizado = new Vector<Double>();
 			 			
 			while (rs.next()) {
 				targetAutorizado.add(rs.getDouble(1));
 			}
 			 			
 			for(int i=0 ;i < targetAutorizado.size();i++){
 				dddd = targetAutorizado.get(i);
 				acumddd = acumddd + dddd;
 			}
 		} catch (Exception e) {
  			s_log.log(Level.SEVERE, "getAutorizado - sql = " + sql, e);
  		} finally {
  			DB.close(rs, pstmt);
  		}

 		return Math.round(acumddd*Math.pow(10,2))/Math.pow(10,2);
 		
 	}
	String name;
	public String getNamePartida(int id){
  		
  		String sql = "select p.name from exme_partida p, exme_gl_budgetpa b where p.exme_partida_id = b.exme_partida_id and b.exme_gl_budgetpa_id = ? and b.isactive = 'Y'";
  		PreparedStatement pstmt = null;
  		ResultSet rs = null;
  		
  		try{
  			pstmt = DB.prepareStatement(sql, get_TrxName());
  			pstmt.setInt(1, id);
  			rs = pstmt.executeQuery();
  			
  			if(rs.next())
 				name = rs.getString(1);
  			  			
 		} catch (Exception e) {
  			s_log.log(Level.SEVERE, "getNamePartida - sql = " + sql, e);
  		} finally {
  			DB.close(rs, pstmt);
  		}
  		
  		return name;
  	}
	
}
