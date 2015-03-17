package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Vector;

import org.compiere.util.DB;
import org.compiere.util.Env;

public class MEXME_GL_BudgetPaPe extends X_EXME_GL_BudgetPaPe {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MEXME_GL_BudgetPaPe(Properties ctx, int EXME_GL_BUDGETPAPE_ID,
			String trxName) {
		super(ctx, EXME_GL_BUDGETPAPE_ID, trxName);
		/**
		 * if (EXME_GL_BUDGETPAPE_ID == 0) { setEXME_GL_BudgetPa_ID (0);
		 * setEXME_GL_BudgetPaPe_ID (0); setPeriodo (Env.ZERO);
		 * setPre_Solicitado (Env.ZERO); }
		 */
	}

	public MEXME_GL_BudgetPaPe(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	Vector<String> targetDetalleIDs;

	public Vector<String> getDetallesIDs(int partida_id) {

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
		String sql = "select exme_gl_budgetpape_id from exme_gl_budgetpape WHERE isactive='Y' and exme_gl_budgetpa_id="
				+ partida_id + " ORDER BY periodo";
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			pstmt = DB.prepareStatement(sql, get_TrxName());
			// pstmt.setString(1, value);
			rs = pstmt.executeQuery();
			targetDetalleIDs = new Vector<String>();

			while (rs.next()) {
				targetDetalleIDs.add(rs.getString(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.close(rs, pstmt);
		}
		
		return targetDetalleIDs;
	}
	
	public static MEXME_GL_BudgetPaPe getBudgetPaPe(int budgetPaID, int periodo){
		
		MEXME_GL_BudgetPaPe budgetPaPe = null;
//		int id = 0;
		
		String sql = " SELECT * FROM exme_gl_budgetpape "+
					 " WHERE exme_gl_budgetpa_id = ? "+
					 " AND PERIODO = ? "; 
		
		PreparedStatement pstmt= null;
		ResultSet rs = null;
		
		try
		{
			pstmt = DB.prepareStatement(sql, null);
			pstmt.setInt(1, budgetPaID);
			pstmt.setInt(2, periodo);
			
			rs = pstmt.executeQuery();
			
			if(rs.next())
				budgetPaPe = new MEXME_GL_BudgetPaPe(Env.getCtx(), rs, null);
			
			
		}catch(SQLException e){
			e.printStackTrace();
		} finally {
			DB.close(rs, pstmt);
		}
		
		return budgetPaPe;
	}

}
