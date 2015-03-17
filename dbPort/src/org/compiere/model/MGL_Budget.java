package org.compiere.model;

import java.io.File;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Vector;
import java.util.logging.Level;

import org.compiere.process.DocAction;
import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.GenericModel;
import org.compiere.util.OptionItem;

public class MGL_Budget extends X_GL_Budget implements DocAction {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static CLogger s_log = CLogger.getCLogger(MGL_Budget.class);

	public MGL_Budget(Properties ctx, int GL_Budget_ID, String trxName) {
		super(ctx, GL_Budget_ID, trxName);
		/*
		 * if (GL_Budget_ID == 0) { setEjercicio (0); setFechaFin (new
		 * Timestamp(System.currentTimeMillis())); setFechaIni (new
		 * Timestamp(System.currentTimeMillis())); setGL_Budget_ID (0);
		 * setIsPrimary (null); // Y setName (null); setPre_Solicitado
		 * (Env.ZERO); setTipoPresup (null); }
		 */

	}
	
	/**
	 * recibe la clausula where
	 * 
	 * @param String clausa wher, similar a la clausala where en compiere
	 * */
	public static List<MGL_Budget> getGlBudgets(Properties ctx, String where) {
		/*TODO recibir argumetos para armar un PreparedStatement valido 
		 * y asi  evitar la recepcion de clausulas where armadas en duro*/

		List<MGL_Budget> budgets = new ArrayList<MGL_Budget>();
	

		StringBuffer sql = new StringBuffer("SELECT * FROM GL_Budget ");
		
		if (where != null){
			sql.append("where ").append(where);
		}
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			
			rs = pstmt.executeQuery();
			if (rs.next()){
				budgets.add(new MGL_Budget(ctx, rs, null));
			}

 		} catch (Exception e) {
  			s_log.log(Level.SEVERE, "getGlBudgets - sql = " + sql.toString(), e);
  		} finally {
  			DB.close(rs, pstmt);
  		}

		return budgets;

	}

	public MGL_Budget(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	public MGL_Budget[] getLinesBudgetTransfered() {
		/*
		 * if (m_lines != null) return m_lines;
		 */
		List<MGL_Budget> list = new ArrayList<MGL_Budget>();
		String sql = "select * from gl_budget where isTransfered='Y' and isactive='Y'";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql, get_TrxName());
			// pstmt.setInt(1, gl_budget_id);
			rs = pstmt.executeQuery();
			// MEXME_GL_BudgetPa part=null;
			while (rs.next()) {
				list.add(new MGL_Budget(getCtx(), rs, get_TrxName()));
			}
			/*
			 * rs.close(); pstmt.close(); pstmt = null; rs = null;
			 */
 		} catch (Exception e) {
  			s_log.log(Level.SEVERE, "getLinesBudgetTransfered - sql = " + sql, e);
  		} finally {
  			DB.close(rs, pstmt);
  		}
		MGL_Budget[] m_budget = new MGL_Budget[list.size()];
		list.toArray(m_budget);
		list = null;
		return m_budget;
	}
	
	public MGL_Budget[] getLinesBudget() {
		/*
		 * if (m_lines != null) return m_lines;
		 */
		List<MGL_Budget> list = new ArrayList<MGL_Budget>();
		String sql = "select * from gl_budget where isactive='Y'";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql, get_TrxName());
			// pstmt.setInt(1, gl_budget_id);
			rs = pstmt.executeQuery();
			// MEXME_GL_BudgetPa part=null;
			while (rs.next()) {
				list.add(new MGL_Budget(getCtx(), rs, get_TrxName()));
			}
			/*
			 * rs.close(); pstmt.close(); pstmt = null; rs = null;
			 */
 		} catch (Exception e) {
  			s_log.log(Level.SEVERE, "getLinesBudget - sql = " + sql, e);
  		} finally {
  			try {
	  			if (pstmt != null) {
	  				pstmt.close();
	  			}
	  			
	  			if (rs != null) {
	  				rs.close();
	  			}
  			} catch (SQLException exClose) {
  				s_log.log(Level.SEVERE, "getLinesBudget - while closing rs and pstmt objects", exClose);
  			}
  		}
		MGL_Budget[] m_budget = new MGL_Budget[list.size()];
		list.toArray(m_budget);
		list = null;
		return m_budget;
	}

	public MGL_Budget[] getLinesBudgetApprove() {
		List<MGL_Budget> list = new ArrayList<MGL_Budget>();
		String sql = "SELECT * FROM GL_Budget WHERE ISACTIVE='Y' AND ISAPPROVED='Y'";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql, get_TrxName());
			rs = pstmt.executeQuery();
			while (rs.next())
				list.add(new MGL_Budget(getCtx(), rs, get_TrxName()));
			
 		} catch (Exception e) {
  			s_log.log(Level.SEVERE, "getLinesBudgetApprove - sql = " + sql, e);
  		} finally {
  			DB.close(rs, pstmt);
  		}
		MGL_Budget[] m_budget = new MGL_Budget[list.size()];
		list.toArray(m_budget);
		list = null;
		return m_budget;
	}

	public Vector<GenericModel> getLines(int gl_budget_id) {

		Vector<GenericModel> list = new Vector<GenericModel>();
		/*String sql = "select pa.exme_gl_budgetpa_id, pa.exme_partida_id, pa.ad_orgtrx_id, pre_solicitado, pa.pre_autorizado, pa.pre_comprometido, pa.pre_devengado, pa.pre_ejercido, po.value, po.name, org.name ad_Org_Name, pa.isdistributed "
				+ "from exme_gl_budgetpa pa "
				+ "inner JOIN exme_partida po on pa.exme_partida_id= po.exme_partida_id "
				+ "left join ad_org org on pa.ad_orgtrx_id= org.ad_org_id "
				+ "where pa.isactive='Y' and pa.gl_budget_id=?";*/
		
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append("select pa.exme_gl_budgetpa_id, pa.exme_partida_id, pa.ad_orgtrx_id, pre_solicitado, ");
		sql.append("pa.pre_autorizado, pa.pre_comprometido, pa.pre_devengado, pa.pre_ejercido, po.value, ");
		sql.append("po.name, org.name ad_Org_Name, pa.isdistributed ");
		sql.append("from exme_gl_budgetpa pa ");
		sql.append("inner JOIN exme_partida po on pa.exme_partida_id= po.exme_partida_id ");
		sql.append("left join ad_org org on pa.ad_orgtrx_id= org.ad_org_id ");
		sql.append("where pa.isactive='Y' and pa.gl_budget_id=?");
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			pstmt = DB.prepareStatement(sql.toString(), get_TrxName());
			pstmt.setInt(1, gl_budget_id);
			rs = pstmt.executeQuery();
			GenericModel part = null;
			while (rs.next()) {
				part = new GenericModel();
				part.setExme_GL_BudgetPa_ID(rs.getString(1));
				part.setExme_Partida_ID(rs.getString(2));
				part.setAd_OrgTrx_ID(rs.getString(3));
				part.setPre_Solicitado(rs.getString(4));
				part.setPre_Autorizado(rs.getString(5));
				part.setPre_Comprometido(rs.getString(6));
				part.setPre_Devengado(rs.getString(7));
				part.setPre_Ejercido(rs.getString(8));
				part.setValue(rs.getString(9));
				part.setName(rs.getString(10));
				part.setAd_Org_Name(rs.getString(11));
				part.setIsDistributed(rs.getString(12));
				list.add(part);
			}
 		} catch (Exception e) {
  			s_log.log(Level.SEVERE, "getLines - sql = " + sql, e);
  		} finally {
  			DB.close(rs, pstmt);
  		}
		return list;
	}

	public Vector<GenericModel> getLinesAmplia(int gl_budget_id) {

		Vector<GenericModel> list = new Vector<GenericModel>();

		/*String sql = "select pa.exme_gl_budgetpa_id, pa.exme_partida_id, pa.ad_orgtrx_id, pre_solicitado, pa.pre_autorizado, pa.pre_comprometido, pa.pre_devengado, pa.pre_ejercido, po.value, po.name, org.name ad_Org_Name, pa.isdistributed "
				+ "from exme_gl_budgetpa pa "
				+ "inner JOIN exme_partida po on pa.exme_partida_id= po.exme_partida_id "
				+ "left join ad_org org on pa.ad_orgtrx_id= org.ad_org_id "
				+ "where pa.isactive='Y' and pa.gl_budget_id=?";*/
		
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append("select pa.exme_gl_budgetpa_id, pa.exme_partida_id, pa.ad_orgtrx_id, pre_solicitado, ");
		sql.append("pa.pre_autorizado, pa.pre_comprometido, pa.pre_devengado, pa.pre_ejercido, po.value, ");
		sql.append("po.name, org.name ad_Org_Name, pa.isdistributed ");
		sql.append("from exme_gl_budgetpa pa ");
		sql.append("inner JOIN exme_partida po on pa.exme_partida_id= po.exme_partida_id ");
		sql.append("left join ad_org org on pa.ad_orgtrx_id= org.ad_org_id ");
		sql.append("where pa.isactive='Y' and pa.gl_budget_id=?");
		
		String sqlCount = "select count(*) from exme_gl_budgetpape where exme_gl_budgetpa_id = ?";

		PreparedStatement pstmt = null;
		PreparedStatement pstmtCount = null;

		ResultSet rs = null;
		ResultSet rsCount = null;

		try {
			pstmt = DB.prepareStatement(sql.toString(), get_TrxName());
			pstmtCount = DB.prepareStatement(sqlCount, null);

			pstmt.setInt(1, gl_budget_id);
			rs = pstmt.executeQuery();
			GenericModel part = null;
			int exmeGLBudgetPaID = 0;
			while (rs.next()) {
				part = new GenericModel();
				exmeGLBudgetPaID = Integer.parseInt(rs.getString(1));
				part.setExme_GL_BudgetPa_ID(String.valueOf(exmeGLBudgetPaID));
				part.setExme_Partida_ID(rs.getString(2));
				part.setAd_OrgTrx_ID(rs.getString(3));

				// utilizamos este campo para guardar si tiene nodos hijos la
				// tabla
				pstmtCount.setInt(1, exmeGLBudgetPaID);
				rsCount = pstmtCount.executeQuery();
				part.setPre_Solicitado((rsCount.next()) ? ((rsCount
						.getString(1).equals("0")) ? "false" : "true") : String
						.valueOf("false"));

				part.setPre_Autorizado(rs.getString(5));
				part.setPre_Comprometido(rs.getString(6));
				part.setPre_Devengado(rs.getString(7));
				part.setPre_Ejercido(rs.getString(8));
				part.setValue(rs.getString(9));
				part.setName(rs.getString(10));
				part.setAd_Org_Name(rs.getString(11));
				part.setIsDistributed(rs.getString(12));

				list.add(part);
			}
 		} catch (Exception e) {
  			s_log.log(Level.SEVERE, "getLinesAmplia - sql = " + sql, e);
  			s_log.log(Level.SEVERE, "getLinesAmplia - sqlCount = " + sqlCount);
  		} finally {
  			DB.close(rs, pstmt);
  			DB.close(rsCount, pstmtCount);
  		}
		return list;
	}

	public BigDecimal getMaximunDecreaseBudget(){
		//tipo Compromiso 
		//CDE
		//sobre ejercido autorizado - ejercido
		//sobre comprometido autorizado - ejercido
		//sobre devengado autorizado - devengado
		String tipoCompromiso = getTipoCompromiso();
		String sql = "select PRE_AUTORIZADO,PRE_COMPROMETIDO,PRE_DEVENGADO,PRE_EJERCIDO from GL_Budget where GL_Budget_ID = ?";
		BigDecimal max = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BigDecimal autorizado = null;
		BigDecimal comprometido = null;
		BigDecimal devengado = null;
		BigDecimal ejercido = null;
		try{
			pstmt = DB.prepareStatement(sql, get_TrxName());
			pstmt.setInt(1, getGL_Budget_ID());
			rs = pstmt.executeQuery();
			while (rs.next()){
				autorizado = rs.getBigDecimal(1);
				comprometido = rs.getBigDecimal(2);
				devengado = rs.getBigDecimal(3);
				ejercido = rs.getBigDecimal(4); 
			}
			max = new BigDecimal("0");
			if (tipoCompromiso.equalsIgnoreCase("E") && tipoCompromiso.equalsIgnoreCase("DE") && tipoCompromiso.equalsIgnoreCase("CDE")){
				max = autorizado.subtract(ejercido);
				max = max.subtract(devengado);
				max = max.subtract(comprometido);
			}else{
				max = autorizado;
			}
			/*if (tipoCompromiso.equalsIgnoreCase("E")){
				max = autorizado.subtract(ejercido);
			}else if (tipoCompromiso.equalsIgnoreCase("DE")){
				max = autorizado.subtract(devengado);
			}else if (tipoCompromiso.equalsIgnoreCase("CDE")){
				max = autorizado.subtract(comprometido);
			}else{
				max = autorizado;
			}*/
 		} catch (Exception e) {
  			s_log.log(Level.SEVERE, "getMaximunDecreaseBudget - sql = " + sql, e);
  		} finally {
  			DB.close(rs, pstmt);
  		}
		return max;
	}
	
	public void deleteBudget(int gl_b) {
		/*String sql = "update gl_budget set isactive='N' where gl_budget_id="
				+ gl_b + "; commit";*/
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append("update gl_budget set isactive='N' where gl_budget_id = ");
		sql.append(gl_b);
		sql.append("; commit");
		PreparedStatement pstmt = null;

		try {

			pstmt = DB.prepareStatement(sql.toString(), get_TrxName());
			// pstmt.setString(1, value);
			pstmt.execute();

 		} catch (Exception e) {
  			s_log.log(Level.SEVERE, "deleteBudget - sql = " + sql, e);
  		} finally {
  			DB.close(pstmt);
  		}
	}

	Vector<String> targetBudget;
	public Timestamp fi, ff;

	public Vector<String> getBudget(int gl_b) {

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

		// MGl_Budget budget = new MGl_Budget
		//String sql = "select * from gl_budget where gl_budget_id=" + gl_b;
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append("select * from gl_budget where gl_budget_id=");
		sql.append(gl_b);
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			pstmt = DB.prepareStatement(sql.toString(), get_TrxName());
			// pstmt.setString(1, value);
			rs = pstmt.executeQuery();
			targetBudget = new Vector<String>();

			while (rs.next()) {
				for (int i = 1; i <= 35; i++) {
					if (i == 13)
						fi = rs.getTimestamp(13);
					else if (i == 14)
						ff = rs.getTimestamp(14);
					targetBudget.add(rs.getString(i));
				}
			}
 		} catch (Exception e) {
  			s_log.log(Level.SEVERE, "getBudget - sql = " + sql, e);
  		} finally {
  			DB.close(rs, pstmt);
  		}

		return targetBudget;
	}

	Vector<String> targetBudgetIDs;

	public Vector<String> getBudgetIDs() {

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
		String sql = "SELECT gl_budget_id FROM gl_budget WHERE isactive='Y' ORDER BY gl_budget_id";
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			pstmt = DB.prepareStatement(sql, get_TrxName());
			// pstmt.setString(1, value);
			rs = pstmt.executeQuery();
			targetBudgetIDs = new Vector<String>();

			while (rs.next()) {
				targetBudgetIDs.add(rs.getString(1));
			}
 		} catch (Exception e) {
  			s_log.log(Level.SEVERE, "getBudgetIDs - sql = " + sql, e);
  		} finally {
  			DB.close(rs, pstmt);
  		}

		return targetBudgetIDs;
	}

	Vector<String> targetDocumentNo;

	public Vector<String> getDocNo() {

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
		String sql = "SELECT MAX(documentno) FROM gl_budget WHERE isactive='Y'";
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			pstmt = DB.prepareStatement(sql, get_TrxName());
			// pstmt.setString(1, value);
			rs = pstmt.executeQuery();
			targetDocumentNo = new Vector<String>();

			while (rs.next()) {
				targetDocumentNo.add(rs.getString(1));
			}
 		} catch (Exception e) {
  			s_log.log(Level.SEVERE, "getDocNo - sql = " + sql, e);
  		} finally {
  			DB.close(rs, pstmt);
  		}

		return targetDocumentNo;
	}

	Vector<OptionItem> targetpartida;

	public Vector<OptionItem> getExme_Partida() {

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
		String sql = "select p.exme_partida_id,p.name from exme_partida p WHERE isactive='Y' order by name";
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			pstmt = DB.prepareStatement(sql, get_TrxName());
			// pstmt.setString(1, value);
			rs = pstmt.executeQuery();
			targetpartida = new Vector<OptionItem>();

			targetpartida.add(new OptionItem("-1", ""));
			while (rs.next()) {
				targetpartida.add(new OptionItem(rs.getString(1), rs
						.getString(2)));
			}
 		} catch (Exception e) {
  			s_log.log(Level.SEVERE, "getExme_Partida - sql = " + sql, e);
  		} finally {
  			DB.close(rs, pstmt);
  		}

		return targetpartida;
	}

	// GETNOMBREPRESUPUESTO CREADO PARA LA FORMA REASIGNACION DE PARTIDAS
	Vector<OptionItem> targetpresupuesto;

	public Vector<OptionItem> getNombrePresupuesto() {

		String sql = "select gl_budget_id, ejercicio, documentno, c_activity_id from gl_budget where isapproved='Y' and isactive='Y' and isprimary='Y' order by ejercicio";
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			pstmt = DB.prepareStatement(sql, get_TrxName());
			rs = pstmt.executeQuery();
			targetpresupuesto = new Vector<OptionItem>();

			targetpresupuesto.add(new OptionItem("-1", ""));
			while (rs.next()) {
				targetpresupuesto.add(new OptionItem(rs.getString(1), rs.getString(2)+ " - " + rs.getString(3)));
			}
 		} catch (Exception e) {
  			s_log.log(Level.SEVERE, "getNombrePresupuesto - sql = " + sql, e);
  		} finally {
  			try {
	  			if (pstmt != null) {
	  				pstmt.close();
	  			}
	  			
	  			if (rs != null) {
	  				rs.close();
	  			}
  			} catch (SQLException exClose) {
  				s_log.log(Level.SEVERE, "getNombrePresupuesto - while closing rs and pstmt objects", exClose);
  			}
  		}
		// System.out.println("AKI IMPRIMIMOS DESDE MGL_BUDGET::TARGETPRES: "+targetpresupuesto);
		return targetpresupuesto;
	}

	String tp;

	// GETTIPOCOMPROMISO CREADO PARA LA FORMA REASIGNACION DE PARTIDAS
	public String getTipoCompromiso() {

		String sql = "select commitmenttype from c_acctschema";
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = DB.prepareStatement(sql, get_TrxName());
			rs = pstmt.executeQuery();
			if (rs.next())
				tp = rs.getString(1);

 		} catch (Exception e) {
  			s_log.log(Level.SEVERE, "getTipoCompromiso - sql = " + sql, e);
  		} finally {
  			DB.close(rs, pstmt);
  		}

		return tp;
	}

	int ct_id;

	// GETFUENTEFINANCIAMIENTO CREADO PARA LA FORMA REASIGNACION DE PARTIDAS
	public int getidact(int act_id) {

		String sql = "select c_activity_id from gl_budget where gl_budget_id = ?";

		PreparedStatement pstmt = null;

		ResultSet rs = null;

		try {

			pstmt = DB.prepareStatement(sql, get_TrxName());
			pstmt.setInt(1, act_id);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				ct_id = rs.getInt(1);

			}

 		} catch (Exception e) {
  			s_log.log(Level.SEVERE, "getidact - sql = " + sql, e);
  		} finally {
  			DB.close(rs, pstmt);
  		}

		return ct_id;
	}

	String fuentef = null;
	int actid = 0;

	// GETFUENTEFINANCIAMIENTO CREADO PARA LA FORMA REASIGNACION DE PARTIDAS
	public String getFuenteFinanciamiento(int act_id) {

		String sql = "select name from c_activity where c_activity_id = ?";

		PreparedStatement pstmt = null;

		ResultSet rs = null;

		try {

			pstmt = DB.prepareStatement(sql, get_TrxName());
			pstmt.setInt(1, act_id);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				fuentef = rs.getString(1);

			}

			//System.out.println("ESTO ES LO QUE VALE ACTID::::::" + actid);
			//System.out.println("ESTO ES LO QUE VALE FUENTEF::::::" + fuentef);
 		} catch (Exception e) {
  			s_log.log(Level.SEVERE, "getFuenteFinanciamiento - sql = " + sql, e);
  		} finally {
  			DB.close(rs, pstmt);
  		}

		return fuentef;
	}

	Vector<OptionItem> targetName;

	public Vector<OptionItem> getCActivityNames() {

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
		String sql = "select c_activity_id, name from c_activity WHERE isactive='Y' ORDER BY name";
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			pstmt = DB.prepareStatement(sql, get_TrxName());
			// pstmt.setString(1, value);
			rs = pstmt.executeQuery();
			targetName = new Vector<OptionItem>();

			targetName.add(new OptionItem("-1", ""));
			while (rs.next()) {
				targetName
						.add(new OptionItem(rs.getString(1), rs.getString(2)));
			}
 		} catch (Exception e) {
  			s_log.log(Level.SEVERE, "getCActivityNames - sql = " + sql, e);
  		} finally {
  			DB.close(rs, pstmt);
  		}

		return targetName;
	}

	Vector<OptionItem> targetOrg = null;

	public Vector<OptionItem> getPHOrg(String ad_org_id) {

		if (targetOrg != null)
			return targetOrg;

		// int value=;
		// value=1000028;

		/*
		 * if (!isSOTrx()){ value = "Y";
		 * System.out.println("******** DEBUG: isSOTrx = false"); }else{ value =
		 * "N"; System.out.println("******** DEBUG: isSOTrx = true"); }
		 * System.out
		 * .println("************ DEBUG: isSOTrx valor de de value: "+value);
		 */
		/*String sql = "select org.ad_org_id, org.name"
				+ " from ad_org org"
				+ " where org.isactive='Y' and org.ad_org_id="
				+ ad_org_id
				+ " union all"
				+ " select o.ad_org_id, o.name"
				+ " from ad_org o, ad_orginfo i"
				+ " where o.isactive='Y' and o.ad_org_id= i.ad_org_id and i.parent_org_id="
				+ ad_org_id + " order by name";*/
		
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append("select org.ad_org_id, org.name");
		sql.append(" from ad_org org");
		sql.append(" where org.isactive='Y' and org.ad_org_id=");
		sql.append(ad_org_id);
		sql.append(" union all");
		sql.append(" select o.ad_org_id, o.name");
		sql.append(" from ad_org o, ad_orginfo i");
		sql.append(" where o.isactive='Y' and o.ad_org_id= i.ad_org_id and i.parent_org_id=");
		sql.append(ad_org_id);
		sql.append(" order by name");
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			pstmt = DB.prepareStatement(sql.toString(), get_TrxName());
			// pstmt.setString(1, value);
			rs = pstmt.executeQuery();
			targetOrg = new Vector<OptionItem>();

			targetOrg.add(new OptionItem("-1", ""));
			while (rs.next()) {
				targetOrg.add(new OptionItem(rs.getString(1), rs.getString(2)));
			}
 		} catch (Exception e) {
  			s_log.log(Level.SEVERE, "getPHOrg - sql = " + sql, e);
  		} finally {
  			DB.close(rs, pstmt);
  		}

		return targetOrg;
	}

	Vector<OptionItem> names;

	public Vector<OptionItem> getBudgetNames(int ad_client) {
		String sql = "SELECT GL_BUDGET_ID,gb.documentno  FROM GL_BUDGET GB WHERE GB.AD_CLIENT_ID = ? AND GB.ISACTIVE = 'Y' AND GB.ISAPPROVED = 'Y'";
		/*
		 * Stringsql2=
		 * "SELECT PRE_AUTORIZADO FROM EXME_GL_BUDGETPA  WHERE (gl_budget_id=1000095 AND EXME_PARTIDA_ID=1000000 AND AD_ORGTRX_ID = 1000155) AND "
		 * + "AD_CLIENT_ID=1000002 AND ISACTIVE='Y'";
		 */
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			pstmt = DB.prepareStatement(sql, get_TrxName());
			pstmt.clearParameters();
			pstmt.setInt(1, ad_client);
			rs = pstmt.executeQuery();
			names = new Vector<OptionItem>();

			while (rs.next()) {
				names.add(new OptionItem(rs.getString(1), rs.getString(2)));
			}
 		} catch (Exception e) {
  			s_log.log(Level.SEVERE, "getBudgetNames - sql = " + sql, e);
  		} finally {
  			DB.close(rs, pstmt);
  		}
		return names;
	}

	Vector<OptionItem> partidas;


	public Vector<OptionItem> getBudgetLines(int gl_budget)
	{
		/*String sql=" SELECT DISTINCT EP.EXME_PARTIDA_ID, EP.NAME FROM EXME_GL_BUDGETPA EGB INNER JOIN EXME_PARTIDA EP ON EP.EXME_PARTIDA_ID = EGB.EXME_PARTIDA_ID "+
					"WHERE EGB.GL_BUDGET_ID = ?";*/
		
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append("SELECT DISTINCT EP.EXME_PARTIDA_ID, EP.NAME ");
		sql.append("FROM EXME_GL_BUDGETPA EGB ");
		sql.append("INNER JOIN EXME_PARTIDA EP ON EP.EXME_PARTIDA_ID = EGB.EXME_PARTIDA_ID ");
		sql.append("WHERE EGB.GL_BUDGET_ID = ?");
		
		//HABER SI POR FIN SE COMPILA BIEN EL DESPLIEGUE!
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			pstmt = DB.prepareStatement(sql.toString(), get_TrxName());
			pstmt.clearParameters();
			pstmt.setInt(1, gl_budget);
			
			rs = pstmt.executeQuery();
			partidas = new Vector<OptionItem>();
			while (rs.next()) {
				partidas.add(new OptionItem(rs.getString(1), rs.getString(2)));
			}
 		} catch (Exception e) {
  			s_log.log(Level.SEVERE, "getBudgetLines - sql = " + sql, e);
  		} finally {
  			DB.close(rs, pstmt);
  		}

		
		return partidas;
	}

	Vector<OptionItem> ot;

	public Vector<OptionItem> getAdOrgTrx(int budget,int partida) {
		boolean tiene=false;
		/*String sql = "select ao.name,ao.ad_org_id from exme_gl_budgetpa egp,ad_org ao,gl_budget gb" 
					+ " where gb.gl_budget_id = egp.gl_budget_id" 
					+ " and egp.ad_orgtrx_id = ao.ad_org_id"
					+ " and gb.gl_budget_id =? AND egp.exme_partida_id = ?";*/
		
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append("select ao.name,ao.ad_org_id from exme_gl_budgetpa egp,ad_org ao,gl_budget gb");
		sql.append(" where gb.gl_budget_id = egp.gl_budget_id");
		sql.append(" and egp.ad_orgtrx_id = ao.ad_org_id");
		sql.append(" and gb.gl_budget_id =? AND egp.exme_partida_id = ?");

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = DB.prepareStatement(sql.toString(), get_TrxName());
			pstmt.clearParameters();
			pstmt.setInt(1, budget);
			pstmt.setInt(2, partida);
			rs = pstmt.executeQuery();
			ot = new Vector<OptionItem>();
			while (rs.next()) {
				ot.add(new OptionItem(rs.getString(2), rs.getString(1)));
				tiene=true;
			}
			if(!tiene) {
				ot.add(new OptionItem("0", "*"));
			}
 		} catch (Exception e) {
  			s_log.log(Level.SEVERE, "getAdOrgTrx - sql = " + sql, e);
  		} finally {
  			DB.close(rs, pstmt);
  		}
		return ot;

	}

	public String getAutorizado(int gl_budget, int partidaID, int adorgtrx,
			int adclientid) {
		String autorizado = null;

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql2 = "SELECT PRE_AUTORIZADO FROM EXME_GL_BUDGETPA WHERE gl_budget_id=? AND EXME_PARTIDA_ID=? AND AD_ORGTRX_ID=? AND AD_CLIENT_ID=? AND ISACTIVE='Y'";

		try {

			pstmt = DB.prepareStatement(sql2, get_TrxName());
			pstmt.clearParameters();
			pstmt.setInt(1, gl_budget);
			pstmt.setInt(2, partidaID);
			pstmt.setInt(3, adorgtrx);
			pstmt.setInt(4, adclientid);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				
				autorizado = rs.getString(1);
			}

 		} catch (Exception e) {
  			s_log.log(Level.SEVERE, "getAutorizado - sql = " + sql2, e);
  		} finally {
  			DB.close(rs, pstmt);
  		}

		return autorizado;
	}

	String sumatoria = null;

	public String getSumLines(int gl_budget, int partida) {
		String sql = "SELECT SUM(PRE_AUTORIZADO) FROM exme_gl_budgetpa WHERE gl_budget_id=? AND exme_partida_id=?";
		/*String sql_impresion = "SELECT SUM(PRE_AUTORIZADO) FROM exme_gl_budgetpa WHERE gl_budget_id="
				+ gl_budget + " AND exme_partida_id=" + partida;*/
		// System.out.println("OBTENIENDO SUMATORIA: "+sql_impresion);
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			pstmt = DB.prepareStatement(sql, get_TrxName());
			pstmt.clearParameters();
			pstmt.setInt(1, gl_budget);
			pstmt.setInt(2, partida);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				sumatoria = rs.getString(1);

			}

 		} catch (Exception e) {
  			s_log.log(Level.SEVERE, "getSumLines - sql = " + sql, e);
  		} finally {
  			DB.close(rs, pstmt);
  		}
		return sumatoria;
	}

	String substraction = null;

	public String getSubstraction(int gl_budget, int partida) {
		/*String sql = "SELECT SUM(ECP.PRE_AUTORIZADO) FROM C_CAMPAIGN CC "
				+ "INNER JOIN EXME_C_CAMPAIGNPA ECP ON ECP.C_CAMPAIGN_ID = CC.C_CAMPAIGN_ID "
				+ "WHERE cc.gl_budget_id=? " + "AND ECP.EXME_PARTIDA_ID=? "
				+ "AND CC.ISAPPROVED='Y'";*/
		
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append("SELECT SUM(ECP.PRE_AUTORIZADO) FROM C_CAMPAIGN CC ");
		sql.append("INNER JOIN EXME_C_CAMPAIGNPA ECP ON ECP.C_CAMPAIGN_ID = CC.C_CAMPAIGN_ID ");
		sql.append("WHERE cc.gl_budget_id=? " + "AND ECP.EXME_PARTIDA_ID=? ");
		sql.append("AND CC.ISAPPROVED='Y'");

		/*String sql_query = "SELECT SUM(cc.PRE_AUTORIZADO) FROM C_CAMPAIGN CC "
				+ "INNER JOIN EXME_C_CAMPAIGNPA ECP ON ECP.C_CAMPAIGN_ID = CC.C_CAMPAIGN_ID "
				+ "WHERE cc.gl_budget_id=" + gl_budget
				+ " AND ECP.EXME_PARTIDA_ID=" + partida
				+ " AND CC.ISAPPROVED='Y'";*/

		// System.out.println("QUERY PARA resta: "+sql_query);

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			pstmt = DB.prepareStatement(sql.toString(), get_TrxName());
			pstmt.clearParameters();
			pstmt.setInt(1, gl_budget);
			pstmt.setInt(2, partida);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				substraction = rs.getString(1);
			}

 		} catch (Exception e) {
  			s_log.log(Level.SEVERE, "getSubstraction - sql = " + sql, e);
  		} finally {
  			DB.close(rs, pstmt);
  		}
		if (substraction == null)
			substraction = "0";
		return substraction;
	}

	String adorgtrx = null;

	public String getOrgTrx(int partida) {
		/*String sql = "SELECT egb.ad_orgtrx_id FROM EXME_GL_BUDGETPA EGB ,EXME_PARTIDA EP "
				+ "WHERE EGB.exme_gl_budgetpa_id = ep.exme_partida_id AND EP.EXME_PARTIDA_ID = ?";*/
		
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append("SELECT egb.ad_orgtrx_id FROM EXME_GL_BUDGETPA EGB ,EXME_PARTIDA EP ");
		sql.append("WHERE EGB.exme_gl_budgetpa_id = ep.exme_partida_id AND EP.EXME_PARTIDA_ID = ?");

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			pstmt = DB.prepareStatement(sql.toString(), get_TrxName());
			pstmt.clearParameters();
			pstmt.setInt(1, partida);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				adorgtrx = rs.getString(1);
			}

 		} catch (Exception e) {
  			s_log.log(Level.SEVERE, "getOrgTrx - sql = " + sql, e);
  		} finally {
  			DB.close(rs, pstmt);
  		}
		return adorgtrx;
	}

	public String getDoctype_Id() {

		String list = "";

		String sql = "select c_doctype_id from c_doctype where name='Presupuesto'";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql, get_TrxName());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				list = rs.getString(1);
			}
 		} catch (Exception e) {
  			s_log.log(Level.SEVERE, "getDoctype_Id - sql = " + sql, e);
  		} finally {
  			DB.close(rs, pstmt);
  		}
		return list;
	}

	// Metodo de busqueda de presupuestos aprovados para determinado año
	public String getBudgetApproveYear(int year) {

		String list = "";

		String sql = "select count(*) as cuenta from gl_budget where isapproved='Y' and ejercicio=?  and isprimary = 'Y' and tipopresup = ? ";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql, get_TrxName());
			pstmt.setInt(1, year);
			pstmt.setString(2, MGL_Budget.TIPOPRESUP_Realistic);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				list = rs.getString("cuenta");
			}
			
 		} catch (Exception e) {
  			s_log.log(Level.SEVERE, "getBudgetApproveYear - sql = " + sql, e);
  		} finally {
  			DB.close(rs, pstmt);
  		}
		return list;
	}

	Vector<OptionItem> listType;

	public Vector<OptionItem> getBudgetType(String languaje, String referencia) {

		/*String sql = "SELECT AD_Ref_List.Value,trl.Name " 
			    + " FROM AD_Ref_List"
				+ " INNER JOIN AD_Ref_List_Trl trl"
				+ " 	ON AD_Ref_List.AD_Ref_List_ID=trl.AD_Ref_List_ID"
				+ " INNER JOIN AD_Reference AR "
				+ " 	ON AR.AD_Reference_ID = AD_Ref_List.AD_Reference_ID "
				+ " 	AND trl.AD_Language='"+languaje+"'"
				+ " WHERE AR.NAME = ? ";*/
		
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append("SELECT AD_Ref_List.Value,trl.Name ");
		sql.append(" FROM AD_Ref_List");
		sql.append(" INNER JOIN AD_Ref_List_Trl trl");
		sql.append(" 	ON AD_Ref_List.AD_Ref_List_ID=trl.AD_Ref_List_ID");
		sql.append(" INNER JOIN AD_Reference AR ");
		sql.append(" 	ON AR.AD_Reference_ID = AD_Ref_List.AD_Reference_ID ");
		sql.append(" 	AND trl.AD_Language='");
		sql.append(languaje);
		sql.append("' WHERE AR.NAME = ? ");
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), get_TrxName());
			pstmt.clearParameters();
			pstmt.setString(1, referencia);
			rs = pstmt.executeQuery();
			listType=new Vector<OptionItem>();
			while (rs.next()) {
				listType.add(new OptionItem(rs.getString(1), rs.getString(2)));
			}

 		} catch (Exception e) {
  			s_log.log(Level.SEVERE, "getBudgetType - sql = " + sql, e);
  		} finally {
  			DB.close(rs, pstmt);
  		}
		return listType;
	}
	
	public String getDateBudgetStart(int budget_id) {

		String sql = "select fechaini from gl_budget where gl_budget_id = ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		tp="";
		try {
			pstmt = DB.prepareStatement(sql, get_TrxName());
			pstmt.setInt(1, budget_id);
			rs = pstmt.executeQuery();
			if (rs.next())
				tp = rs.getString(1);

 		} catch (Exception e) {
  			s_log.log(Level.SEVERE, "getDateBudgetStart - sql = " + sql, e);
  		} finally {
  			DB.close(rs, pstmt);
  		}

		return tp;
	}
	
	// **************************************************************************************
	// Metodo usado para devolver el presupuesto realista en base al a�o
	// Presupuesto
	// **************************************************************************************
	
	public static MGL_Budget getBudgetByYear(int anio){
		MGL_Budget budget = null;
		String sql = "SELECT * FROM GL_Budget WHERE ejercicio = ? and isactive = 'Y' and isapproved = 'Y' ";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = DB.prepareStatement(sql, null);
			pstmt.setInt(1, anio);
			rs = pstmt.executeQuery();
			if (rs.next())
				budget = new MGL_Budget(Env.getCtx(), rs, null);

 		} catch (Exception e) {
  			s_log.log(Level.SEVERE, "getBudgetByYear - sql = " + sql, e);
  		} finally {
  			DB.close(rs, pstmt);
  		}
		return budget;
	}

	// **************************************************************************************
	// Metodo usado para obtener la lista de valores de tipo de tipo de
	// Presupuesto
	// **************************************************************************************
	// Vector<OptionItem> listType;
	//
	// public Vector<OptionItem> getBudgetType() {
	//
	// String sql = "SELECT AD_Ref_List.Value,trl.Name" + "FROM AD_Ref_List"
	// + "INNER JOIN AD_Ref_List_Trl trl "
	// + "ON (AD_Ref_List.AD_Ref_List_ID=trl.AD_Ref_List_ID"
	// + "AND trl.AD_Language='es_MX')"
	// + "WHERE AD_Ref_List.AD_Reference_ID=1200130";
	// PreparedStatement pstmt = null;
	// ResultSet rs = null;
	// try {
	// pstmt = DB.prepareStatement(sql, get_TrxName());
	// rs = pstmt.executeQuery();
	// while (rs.next()) {
	// listType.add(new OptionItem(rs.getString(1), rs.getString(2)));
	// }
	//
	// } catch (Exception e) {
	// log.log(Level.SEVERE, "getLines", e);
	// } finally {
	// try {
	// if (pstmt != null)
	// pstmt.close();
	// pstmt = null;
	// if (rs != null)
	// rs.close();
	// rs = null;
	// } catch (Exception e) {
	// }
	// }
	// return listType;
	// }

	public boolean approveIt() {
		
		return false;
	}

	public boolean closeIt() {
		
		return false;
	}

	public String completeIt() {
		
		return null;
	}

	public File createPDF() {
		
		return null;
	}

	public BigDecimal getApprovalAmt() {
		
		return null;
	}

	public int getC_Currency_ID() {
		
		return 0;
	}

	public int getDoc_User_ID() {
		
		return 0;
	}

	public String getDocumentInfo() {
		
		return null;
	}

	public String getProcessMsg() {
		
		return null;
	}

	public String getSummary() {
		
		return null;
	}

	public boolean invalidateIt() {
		
		return false;
	}

	public String prepareIt() {
		
		return null;
	}

	public boolean processIt(String action) throws Exception {
		
		return false;
	}

	public boolean reActivateIt() {
		
		return false;
	}

	public boolean rejectIt() {
		
		return false;
	}

	public boolean reverseAccrualIt() {
		
		return false;
	}

	public boolean reverseCorrectIt() {
		
		return false;
	}

	public boolean unlockIt() {
		
		return false;
	}

	public boolean voidIt() {
		
		return false;
	}
	
	/**
	 * @param DocumentNo
	 * Método que comprueba si el numero de documento ya existe en la tabla
	 * @return boolean true si existe, false de otro modo.
	 */
	
	public static boolean DocumentNoExist(String DocumentNo) {
		boolean Exist=false;
		int Count=0;
		//String sql = "SELECT COUNT (*) FROM GL_BUDGET WHERE DOCUMENTNO='"+DocumentNo+"'";
		
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append("SELECT COUNT (*) FROM GL_BUDGET WHERE DOCUMENTNO='");
		sql.append(DocumentNo);
		sql.append("'");
	
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			pstmt = DB.prepareStatement(sql.toString(), null);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Count = rs.getInt(1);
			}

			if (Count != 0) {
				Exist = true;
			}
			
 		} catch (Exception e) {
  			s_log.log(Level.SEVERE, "DocumentNoExist - sql = " + sql, e);
  		} finally {
  			DB.close(rs, pstmt);
  		}
		return Exist;
		
	}

}
