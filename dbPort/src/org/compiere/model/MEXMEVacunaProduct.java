package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;

public class MEXMEVacunaProduct extends X_EXME_VacunaProduct{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5468067831362532793L;
	private static CLogger s_log = CLogger.getCLogger(MEXMEVacunaProduct.class);
	
	public MEXMEVacunaProduct(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}
	
	
	public MEXMEVacunaProduct(Properties ctx, int EXME_VacunaProduct_ID,
			String trxName) {
		super(ctx, EXME_VacunaProduct_ID, trxName);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * vacunas asociadas a un producto 
	 * a aplicar
	 * @param ctx
	 * @param productID
	 * @return
	 */
	public static List<MEXMEVacuna> getByProduct(Properties ctx, int productID){
		List<MEXMEVacuna> lista = new ArrayList<MEXMEVacuna>();
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		sql.append("SELECT * FROM EXME_Vacunaproduct "); 
		sql.append("WHERE M_product_ID = ? ");
		sql.append(" AND ISACTIVE = 'Y' ");
		sql.append("ORDER BY updated DESC ");
		
		try{
			 pstmt = DB.prepareStatement(sql.toString(), null);
			 pstmt.setInt(1, productID);
			 rs = pstmt.executeQuery();
			 
			 while(rs.next()){
				 MEXMEVacunaProduct vacPrd = new MEXMEVacunaProduct(ctx, rs, null);
				 MEXMEVacuna det = new MEXMEVacuna(ctx, vacPrd.getEXME_Vacuna_ID(), null);
				 lista.add(det);
			 }
			 
		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
		}
		return lista;
	}
	
	/**
	 * Productos Asaociados a una vacuna 
	 * a aplicar
	 * @param ctx
	 * @param vacunaID
	 * @return
	 */
	public static List<MEXMEVacunaProduct> getAll(Properties ctx, int vacunaID){
		List<MEXMEVacunaProduct> lista = new ArrayList<MEXMEVacunaProduct>();
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		sql.append("SELECT * FROM EXME_Vacunaproduct "); 
		sql.append("WHERE EXME_Vacuna_ID = ? ");
		sql.append(" AND ISACTIVE = 'Y' ");
		sql.append("ORDER BY updated DESC ");
		
		try{
			 pstmt = DB.prepareStatement(sql.toString(), null);
			 pstmt.setInt(1, vacunaID);
			 rs = pstmt.executeQuery();
			 
			 while(rs.next()){
				 MEXMEVacunaProduct vacPrd = new MEXMEVacunaProduct(ctx, rs, null);
				 lista.add(vacPrd);
			 }
			 
		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
		}
		return lista;
	}
}
