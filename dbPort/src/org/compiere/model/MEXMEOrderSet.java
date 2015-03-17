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
import org.compiere.util.Env;

import com.ecaresoft.api.Generic;

public class MEXMEOrderSet extends X_EXME_OrderSet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** log de la clase*/
	private static CLogger log = CLogger.getCLogger(MEXMEOrderSet.class);

	public MEXMEOrderSet(Properties ctx, int EXME_OrderSet_ID, String trxName) {
		super(ctx, EXME_OrderSet_ID, trxName);
	}

	public MEXMEOrderSet(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}


	/**
	 * Obtiene todos los order set
	 * @param ctx
	 * @param exmeMedicoID
	 * @param isActive
	 * @return
	 */
	public static List<MEXMEOrderSet> getOrderSets(final Properties ctx, final int exmeMedicoID, boolean isActive){

		final List<MEXMEOrderSet> list = new ArrayList<MEXMEOrderSet>();

		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		PreparedStatement pstmt = null;
		ResultSet result = null;
		try {
			sql.append("SELECT EXME_OrderSet.* ")
			.append(" FROM EXME_OrderSet ")
			.append(" WHERE EXME_OrderSet.Ad_Client_ID IN (?,?) ");
			if (isActive) {
				sql.append(" AND EXME_OrderSet.isActive = 'Y' ");
			}
			if (exmeMedicoID > 0) {
				if (isActive) {
					sql.append(" AND EXME_OrderSet.EXME_Medico_ID = ? ");
				} else {
					sql.append(" AND EXME_OrderSet.EXME_Medico_ID = ? ");
				}
			}

			sql.append(" ORDER BY EXME_OrderSet.Created DESC ");

			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setInt(1,0);
			pstmt.setInt(2, Env.getAD_Client_ID(ctx));
			if (exmeMedicoID > 0) {
				pstmt.setInt(3, exmeMedicoID);
			}

			result = pstmt.executeQuery();
			while (result.next()) {
				list.add(new MEXMEOrderSet(ctx, result, null));
			}
		} catch (SQLException e) {
			log.log(Level.SEVERE, e.getLocalizedMessage());
		} finally {
			DB.close(result,pstmt);
		}

		return list;
	}

	/**
	 * Obtiene todos los order set como LVB
	 * @param ctx
	 * @param exmeMedicoID
	 * @param isActive
	 * @return
	 */
	public static List<LabelValueBean> getOrderSetsLVB(final Properties ctx, final int exmeMedicoID, final int adOrgID, boolean isActive){

		final List<LabelValueBean> list = new ArrayList<LabelValueBean>();

		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		PreparedStatement pstmt = null;
		ResultSet result = null;
		int param = 0;
		try {
			sql.append("SELECT EXME_OrderSet.EXME_OrderSet_ID, EXME_OrderSet.Name ")
			.append(" FROM EXME_OrderSet WHERE ");
			if (isActive) {
				sql.append(" EXME_OrderSet.isActive = 'Y' AND ");
			}
			if (exmeMedicoID > 0) {
				sql.append(" EXME_OrderSet.EXME_Medico_ID = ? ");
				param ++;
			} else if (adOrgID > 0) {
				sql.append(" EXME_OrderSet.AD_Org_ID = ? ");
				param ++;
			}

			sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, sql.toString(), Table_Name));

			sql.append(" ORDER BY EXME_OrderSet.Created DESC ");

			pstmt = DB.prepareStatement(sql.toString(), null);
			if(param>0){
				if (exmeMedicoID > 0) {
					pstmt.setInt(1, exmeMedicoID);
				} else if (adOrgID > 0) {
					pstmt.setInt(1, adOrgID);
				}
			}

			result = pstmt.executeQuery();
			while(result.next()){
				list.add(new LabelValueBean(result.getString(COLUMNNAME_Name), String.valueOf(result.getInt(COLUMNNAME_EXME_OrderSet_ID))));
			}
		} catch (SQLException e) {
			log.log(Level.SEVERE, e.getLocalizedMessage());
		} finally {
			DB.close(result,pstmt);
		}

		return list;
	}

	/**
	 * Obtiene todos los order set
	 * @param ctx
	 * @param exmeMedicoID
	 * @param isActive
	 * @return
	 */
	public static List<LabelValueBean> getOrderSets(final Properties ctx, final String type, final int categoryID, 
			final String areaType, String isActive){
		return getOrderSets(ctx, type, categoryID, areaType, isActive, false, 0);
	}

	/**
	 * Obtiene todos los order set
	 * @param ctx
	 * @param type
	 * @param categoryID
	 * @param areaType
	 * @param isActive
	 * @param isByOrg
	 * @param medicoID
	 * @return
	 */
	public static List<LabelValueBean> getOrderSets(final Properties ctx, final String type, final int categoryID, 
			final String areaType, String isActive, boolean isByOrg, int medicoID){

		final List<LabelValueBean> list = new ArrayList<LabelValueBean>();
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		PreparedStatement pstmt = null;
		ResultSet result = null;
		try {
			final List<Object> params = new ArrayList<Object>();//Lama, manejo de parametros
			sql.append("SELECT EXME_OrderSet.EXME_OrderSet_ID, EXME_OrderSet.Name");
			sql.append(" FROM EXME_OrderSet ");
			sql.append(" WHERE 1=1 ");
			if (StringUtils.isNotEmpty(isActive)) {
				sql.append("AND EXME_OrderSet.isActive=? ");
				params.add(isActive);
			}
			if (categoryID > 0) {
				sql.append("AND EXME_OrderSet.EXME_OrderCategory_ID=? ");
				params.add(categoryID);
			}
			if (StringUtils.isNotEmpty(type)) {
				sql.append("AND EXME_OrderSet.OrderType=? ");
				params.add(type);
			}
			if (StringUtils.isNotEmpty(areaType)) {
				sql.append("AND EXME_OrderSet.TipoArea=? ");
				params.add(areaType);
			}
			if (isByOrg) {
				sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", MEXMEOrderSet.Table_Name));
			}
			if (medicoID > 0) {
				sql.append(" AND EXME_OrderSet.EXME_MEDICO_ID=?");
				params.add(medicoID);
			}

			sql.append("  ORDER BY EXME_OrderSet.Name");

			pstmt = DB.prepareStatement(sql.toString(), null);

			DB.setParameters(pstmt, params);

			result = pstmt.executeQuery();

			while(result.next()){
				list.add(new LabelValueBean(result.getString("Name"), result.getString("EXME_OrderSet_ID")));
			}
		} catch (SQLException e) {
			log.log(Level.SEVERE, e.getLocalizedMessage());
		} finally {
			DB.close(result,pstmt);
		}

		return list;
	}

	public static List<Generic> getGenOrderSets(final Properties ctx, final String type, final int categoryID, final String areaType, String isActive) {
		List<Generic> list = new ArrayList<Generic>();
		List<LabelValueBean> lst = getOrderSets(ctx, type, categoryID, areaType, isActive);

		for (LabelValueBean lb : lst) {
			list.add(new Generic(lb.getLabel(), lb.getValue()));
		}
		return list;
	}

	private List<MEXMEOrderSetDiag> diagnostics;

	public List<MEXMEOrderSetDiag> getDiagnostics(boolean requery, String trxName) {
		if (diagnostics == null || requery) {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT ");
			sql.append("  * ");
			sql.append("FROM ");
			sql.append("  exme_ordersetdiag ");
			sql.append("WHERE ");
			sql.append("  exme_orderset_id = ? ");
			sql.append(MEXMELookupInfo.addAccessLevelSQL(getCtx(), " ", MEXMEOrderSetDiag.Table_Name));
			diagnostics = new ArrayList<MEXMEOrderSetDiag>();
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
				pstmt = DB.prepareStatement(sql.toString(), trxName);
				pstmt.setInt(1, getEXME_OrderSet_ID());
				rs = pstmt.executeQuery();
				while (rs.next()) {
					diagnostics.add(new MEXMEOrderSetDiag(getCtx(), rs, trxName));
				}

			} catch (Exception e) {
				log.log(Level.SEVERE, null, e);
			} finally {
				DB.close(rs, pstmt);
			}
		}
		return diagnostics;
	}

	public static boolean getOSinUse(Properties ctx, int citaId, int OrderSetID, String trxName) {
		boolean ret = false;
		StringBuilder sql = new StringBuilder();

		sql.append(" (select exme_orderset_id from exme_citamedicadet where exme_citamedica_id = ? and isactive = 'Y' and exme_orderset_id =  ?) ")
		.append(" union  ")
		.append(" (SELECT  exme_actpacienteind.exme_orderset_id ")
		.append(" FROM EXME_ActPacienteInd ")
		.append(" INNER JOIN EXME_ActPacienteIndH indh ON (indh.exme_actpacienteindh_id = EXME_ActPacienteInd.exme_actpacienteindh_id) ")
		.append(" INNER JOIN EXME_ActPaciente act ON (act.exme_actpaciente_id = indh.exme_actpaciente_id AND act.EXME_CitaMedica_ID = ?) ")
		.append(" where EXME_ActPacienteInd.IsActive ='Y' ")
		.append(" and exme_actpacienteind.exme_orderset_id = ? ) ")
		.append(" union  ")
		.append(" (SELECT  EXME_ActPacienteDiag.exme_orderset_id ")
		.append(" FROM EXME_ActPacienteDiag   ")
		.append(" INNER JOIN EXME_ActPaciente act ON (act.exme_actpaciente_id = EXME_ActPacienteDiag.exme_actpaciente_id AND act.EXME_CitaMedica_ID = ? ) ")
		.append(" where EXME_ActPacienteDiag.IsActive ='Y' ")
		.append(" and EXME_ActPacienteDiag.exme_orderset_id = ?) ");
				
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			
			pstmt.setInt(1, citaId);
			pstmt.setInt(2, OrderSetID);
			pstmt.setInt(3, citaId);
			pstmt.setInt(4, OrderSetID);
			pstmt.setInt(5, citaId);
			pstmt.setInt(6, OrderSetID);		
			
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				ret = true;
			}

		} catch (Exception e) {
			log.log(Level.SEVERE, null, e);
		} finally {
			DB.close(rs, pstmt);
		}

		return ret;
	}

	public static boolean getOSinUseDiag(Properties ctx, int citaId, int OrderSetID, int RecordID, String trxName) {
		boolean ret = false;
		StringBuilder sql = new StringBuilder();

		sql.append(" (select exme_orderset_id from exme_citamedicadet where exme_citamedica_id = ? and exme_diagnostico_id = ? " )
		.append(" and isactive = 'Y' and exme_orderset_id =  ?) ")		
		.append(" union  ")
		.append(" (SELECT  EXME_ActPacienteDiag.exme_orderset_id ")
		.append(" FROM EXME_ActPacienteDiag   ")
		.append(" INNER JOIN EXME_ActPaciente act ON (act.exme_actpaciente_id = EXME_ActPacienteDiag.exme_actpaciente_id AND act.EXME_CitaMedica_ID = ? ) ")
		.append(" where EXME_ActPacienteDiag.IsActive ='Y' ")
		.append(" and exme_actpacientediag.exme_diagnostico_id = ? ")
		.append(" and EXME_ActPacienteDiag.exme_orderset_id = ?) ");

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, citaId);
			pstmt.setInt(2, RecordID);
			pstmt.setInt(3, OrderSetID);
			pstmt.setInt(4, citaId);
			pstmt.setInt(5, RecordID);
			pstmt.setInt(6, OrderSetID);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				ret = true;
			}

		} catch (Exception e) {
			log.log(Level.SEVERE, null, e);
		} finally {
			DB.close(rs, pstmt);
		}

		return ret;
	}

	public static boolean getOSinUseServOrDrug(Properties ctx, int citaId, int OrderSetID, int RecordID, String trxName) {
		boolean ret = false;
		StringBuilder sql = new StringBuilder();

		sql.append(" (select exme_orderset_id from exme_citamedicadet where exme_citamedica_id = ? and m_product_id = ? " )
		.append(" and isactive = 'Y' and exme_orderset_id =  ?) ")		
		.append(" union  ")
		.append(" (SELECT  exme_actpacienteind.exme_orderset_id ")
		.append(" FROM EXME_ActPacienteInd ")
		.append(" INNER JOIN EXME_ActPacienteIndH indh ON (indh.exme_actpacienteindh_id = EXME_ActPacienteInd.exme_actpacienteindh_id) ")
		.append(" INNER JOIN EXME_ActPaciente act ON (act.exme_actpaciente_id = indh.exme_actpaciente_id AND act.EXME_CitaMedica_ID = ?) ")
		.append(" where EXME_ActPacienteInd.IsActive ='Y' ")
		 .append(" and  EXME_ActPacienteInd.m_product_id = ? ")   
		.append(" and exme_actpacienteind.exme_orderset_id = ? ) ")
		.append(" union  ")
		.append(" (SELECT  exme_actpacienteind.exme_orderset_id ")
		.append(" FROM EXME_ActPacienteInd ")
		.append(" INNER JOIN EXME_ActPacienteIndH indh ON (indh.exme_actpacienteindh_id = EXME_ActPacienteInd.exme_actpacienteindh_id) ")
		.append(" INNER JOIN EXME_ActPaciente act ON (act.exme_actpaciente_id = indh.exme_actpaciente_id AND act.EXME_CitaMedica_ID = ?) ")
		.append(" where EXME_ActPacienteInd.IsActive ='Y' ")
		.append(" and  exme_actpacienteind.exme_genproduct_id = ? ")
		.append(" and exme_actpacienteind.exme_orderset_id = ? ) ");

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, citaId);
			pstmt.setInt(2, RecordID);
			pstmt.setInt(3, OrderSetID);
			pstmt.setInt(4, citaId);
			pstmt.setInt(5, RecordID);
			pstmt.setInt(6, OrderSetID);
			pstmt.setInt(7, citaId);
			pstmt.setInt(8, RecordID);
			pstmt.setInt(9, OrderSetID);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				ret = true;
			}

		} catch (Exception e) {
			log.log(Level.SEVERE, null, e);
		} finally {
			DB.close(rs, pstmt);
		}

		return ret;
	}
}
