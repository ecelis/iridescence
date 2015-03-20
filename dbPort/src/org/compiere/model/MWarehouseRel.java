package org.compiere.model;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.apache.struts.util.LabelValueBean;
import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.Env;


public class MWarehouseRel extends X_EXME_WarehouseRel {

	private static final long serialVersionUID = 1L;
	/** Static logger */
	private static CLogger log = CLogger.getCLogger(MWarehouseRel.class);

	public MWarehouseRel(Properties ctx, int EXME_WarehouseRel_ID, String trxName) {
		super(ctx, EXME_WarehouseRel_ID, trxName);
	}

	public MWarehouseRel(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}


    /**
    *  Devuelve los almacenes a los que les puede pedir un almacen
    *  origen
    *
    *  @param empresa la empresa con la que se logeo
    *  @param organizacion la organizacion con la que se logeo
    *  @param almacen el almacen origen
    *  @return Una lista con los almacenes a los que les puede pedir
    *  @throws Exception en caso de ocurrir un error al procesar
    * la consulta.
    */
	public static List<LabelValueBean> getWarehouseRel(Properties ctx, String almacenesID, boolean blanco, String trxName) {

		final List<LabelValueBean> almaServ = new ArrayList<LabelValueBean>();
		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append(" SELECT EXME_WarehouseRel.M_WarehouseRel_ID, a.Name ");
		sql.append(" FROM EXME_WarehouseRel ");
		sql.append(" LEFT JOIN M_Warehouse a ON (a.M_Warehouse_ID = EXME_WarehouseRel.M_WarehouseRel_ID) ");
		sql.append(" WHERE EXME_WarehouseRel.IsActive = 'Y' AND a.IsActive = 'Y' ");
		sql.append(" AND EXME_WarehouseRel.M_Warehouse_ID IN (" + almacenesID + ") ");
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name));

		sql.append(" ORDER BY a.Name ");
        
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {

			if (blanco) {
				almaServ.add(new LabelValueBean("", "0"));
			}

			pstmt = DB.prepareStatement(sql.toString(), null);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				final LabelValueBean combo = new LabelValueBean(rs.getString("Name"),
						rs.getString("M_WarehouseRel_ID"));
				almaServ.add(combo);
			}

		} catch (Exception e) {
			log.log(Level.SEVERE, "getWarehouseRel", e);
		} finally {
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}
        return almaServ;
    }

    
	/**
	 *  Devuelve los almacenes que le pueden pedir un almacen destino.
	 *  (EXME_WarehouseRel)
	 *
	 *@param  ctx
	 *@param  almacen
	 *@return             Lista de LabelValueBean
	 *@throws  Exception
	 */
	public static List<LabelValueBean> getWarehouseRel(Properties ctx, int almacen) {

		final List<LabelValueBean> lista = new ArrayList<LabelValueBean>();

		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);

		sql.append("SELECT a.M_Warehouse_ID, a.Name ");
		sql.append("FROM EXME_WarehouseRel , M_Warehouse a ");
		sql.append("WHERE  EXME_WarehouseRel.M_WarehouseRel_ID = ? ");
		//sql.append("AND EXME_WarehouseRel.M_Warehouse_ID <> ? ");// Se permirte aplicar y solicitar asi mismo.Twry
		sql.append("AND EXME_WarehouseRel.IsActive = 'Y' AND a.IsActive = 'Y' ");
		sql.append("AND EXME_WarehouseRel.M_Warehouse_ID = a.M_Warehouse_ID ");
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name));
		sql.append(" ORDER BY a.Name");

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setInt(1, almacen);
			//pstmt.setInt(2, almacen); // Se permirte aplicar y solicitar asi mismo.Twry
			rs = pstmt.executeQuery();

			while (rs.next()) {
				final LabelValueBean combo = new LabelValueBean(String.valueOf(rs.getString("Name")), 
						String.valueOf(rs.getInt("M_Warehouse_ID")));
				lista.add(combo);
			}
			
		} catch (Exception e) {
			log.log(Level.SEVERE, "getWarehouseRel", e);
		} finally {
			DB.close(rs, pstmt);
		}
		return lista;
	}
	
	/**
	 *  Devuleve los almacenes que piden un sercicio a un almacen especifico,
	 *  pe. PB1, PB2, PB3 pueden pedir servicios a ANATOMIA PATOLOGICA
	 *  este get devuelve una lista con los almacenes que le han solicitado servicios o 
	 *  que pueden solicitarle servicios a Anatomia patologica.y asi con cualquier otro
	 *  almacen 
	 *
	 *@param  ctx			Parametro de contexto que contiende algunos atributos de la sesion
	 *@param  almacen	Parametro que contiene el valor del almacen que surte servicios
	 *@return             	Lista de LabelValueBean
	 *@throws  Exception
	 */
	public static List<LabelValueBean> getWarehouseRelComp(Properties ctx, long almacen) throws Exception {
		
		final List<LabelValueBean> lista = new ArrayList<LabelValueBean>();
		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		
		sql.append(" SELECT M_Warehouse.M_Warehouse_ID, M_Warehouse.Name ");
		sql.append(" FROM EXME_WarehouseRel ");
		sql.append(" INNER JOIN M_Warehouse ON ( EXME_WarehouseRel.M_Warehouse_ID = M_Warehouse.M_Warehouse_ID ) ");
		sql.append(" WHERE EXME_WarehouseRel.M_WarehouseRel_ID = ? ");
		sql.append(" AND EXME_WarehouseRel.IsActive = 'Y' ");
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name));
		sql.append(" ORDER BY EXME_WarehouseRel.M_Warehouse_ID ");

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setLong(1, almacen);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				final LabelValueBean combo = new LabelValueBean(String.valueOf(rs.getString("Name")),
						String.valueOf(rs.getInt("M_Warehouse_ID")));
				lista.add(combo);
			}
		} catch (SQLException e) {
			log.log(Level.SEVERE, sql + " =====> Almacen: " + almacen + "\n\n", e);
			throw new Exception("error.getWarehouseRel");
		} finally {
			DB.close(rs, pstmt);

		}
		return lista;
	}

	/**
	 *  Devuelve los almacenes a los que les puede pedir un almacen origen.
	 *  incluyendose el mismo (EXME_WarehouseRel)
	 *
	 *@param  ctx
	 *@param  almacen
	 *@return             Lista de LabelValueBean
	 *@throws  Exception
	 */
	public static List<LabelValueBean> getLstSolicitaAlm(Properties ctx, int almacen, boolean consigna) {

		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		final List<LabelValueBean> lista = new ArrayList<LabelValueBean>();
		
		sql.append("SELECT a.M_Warehouse_ID, a.Name ");
		sql.append("FROM EXME_WarehouseRel , M_Warehouse a ");
		sql.append("WHERE  EXME_WarehouseRel.M_Warehouse_ID = ? ");
		sql.append("AND EXME_WarehouseRel.IsActive = 'Y' AND a.IsActive = 'Y' ");
		sql.append("AND EXME_WarehouseRel.M_WarehouseRel_ID = a.M_Warehouse_ID ");

		// verificamos si necesitamos los almacenes en consigna, eruiz
		if (consigna) {
			sql.append(" AND a.Consigna = 'Y' AND a.AD_Org_ID <> ? ");// + ctx.getProperty("#AD_Org_ID"));
		} else {
			sql.append(" AND a.Consigna = 'N' ");
			sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name));
		}

		sql.append(" ORDER BY a.Name");

		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setLong(1, almacen);
			if (consigna) {
				pstmt.setInt(2, Env.getAD_Org_ID(ctx));
			}
			rs = pstmt.executeQuery();

			while (rs.next()) {
				final LabelValueBean combo = new LabelValueBean(
						String.valueOf(rs.getString("Name"))
						, String.valueOf(rs.getInt("M_Warehouse_ID"))
						);
				lista.add(combo);
			}
               
		} catch (Exception e) {
    		log.log(Level.SEVERE, sql.toString(), e);
    	}finally {
    		DB.close(rs, pstmt);
    	}

		return lista;
	}
	
	public static boolean getExistRelation(Properties ctx, int almacen, int almacenRel) {
		boolean ret = false;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;

		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append("SELECT EXME_WarehouseRel_ID FROM EXME_WarehouseRel ");
		sql.append("WHERE isActive = 'Y' ");
		sql.append("AND M_WareHouse_ID = ? ");
		sql.append("AND M_WareHouseRel_ID = ? ");
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name));

		try {
//			pstmt = DB.prepareStatement(sql.toString(), null);
//			pstmt.setLong(1, almacen);
//			pstmt.setLong(2, almacenRel);
//			rs = pstmt.executeQuery();

//			ret = rs.next();
			ret = DB.getSQLValue(null, sql.toString(), almacen, almacenRel) > 0;
			
		} catch (Exception e) {
			log.log(Level.SEVERE, sql.toString(), e);
		}
//		 finally {
//			DB.close(rs, pstmt);
//		}
		return ret;
	}
}

