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
import org.compiere.util.WebEnv;

public class MFreightCategory extends X_M_FreightCategory {

	private static CLogger s_log = CLogger.getCLogger(MFreightCategory.class);

	public MFreightCategory(Properties ctx, int M_FreightCategory_ID, String trxName) {
		super(ctx, M_FreightCategory_ID, trxName);
	}

	public MFreightCategory(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);

	}
	
	/**
	 * Busca todos los agrupadores de producto existentes
	 * @param ctx
	 * @param trxName
	 * @author Lorena Lama
	 * @return
	 */
	public static List<MFreightCategory> getAll(Properties ctx, String trxName) {
		return get(ctx, null, -1, false, null, trxName);
	}
	
	/**
	 * Regresa solo los agrupadores con productos relacionados al grupo y almacen 
	 * @param ctx
	 * @param warehouseID - El id del almacen que maneja los productos
	 * @param tipoProd- Tipo de producto : Servicio o Producto (ProductType)
	 * @param trxName
	 * @author Lorena Lama
	 * @return
	 */
	public static List<MFreightCategory> getByWarehouse(Properties ctx, int warehouseID, String tipoProd, String trxName) {
		return get(ctx, null, warehouseID, true, tipoProd, trxName);
	}
	
	/**
	 * Busca los agrupadores de producto
	 * Si se envia almacen, recupera solo los agrupadores que tengan productos para ese almacen
	 * @param ctx
	 * @param whereClause - Condiciones adicionales
	 * @param warehouseID - El id del almacen que maneja los productos
	 * @param cProd - agrupadores con/sin productos relacionados
	 * @param tipoProd - Tipo de producto : Servicio o Producto (ProductType)
	 * @param trxName
	 * @author Lorena Lama
	 * @return
	 */
	public static List<MFreightCategory> get(Properties ctx, String whereClause, int warehouseID, boolean cProd, String tipoProd, String trxName) {
		List<MFreightCategory> lista = new ArrayList<MFreightCategory>();

		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			sql.append("SELECT DISTINCT M_FreightCategory.* FROM M_FreightCategory ");

			if (cProd) {
				sql.append("INNER JOIN M_Product p ON (M_FreightCategory.M_FreightCategory_id = P.M_FreightCategory_ID ")//
						.append(" AND  p.IsActive='Y' ")// activo,
						.append(tipoProd != null ? " AND  p.producttype = ? " : "")// tipo de producto
						.append(" ) ");

				if (warehouseID > 0)
					sql.append("INNER JOIN M_Replenish r ON (r.m_product_id = p.m_product_id ) ")//
							.append("INNER JOIN M_Warehouse w ON (r.m_warehouse_id = w.M_Warehouse_ID ")//
							.append("AND w.M_Warehouse_ID =  ? ) ");
			}
			sql.append(" WHERE M_FreightCategory.isActive = 'Y' ")
				.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name));

			if (whereClause != null)
				sql.append(whereClause);

			
				sql.append("ORDER BY M_FreightCategory.Name ");
			
			pstmt = DB.prepareStatement(sql.toString(), null);
			
			int i = 0;
			if(tipoProd != null)
				pstmt.setString(++i, tipoProd);
			if(warehouseID > 0)
				pstmt.setInt(++i, warehouseID);
			
			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				lista.add(new MFreightCategory(ctx, rs, trxName));
			}

		} catch (Exception e) {
			if (WebEnv.DEBUG)
				e.printStackTrace();
			s_log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
			} catch (SQLException e) {
				if (WebEnv.DEBUG)
					e.printStackTrace();
				s_log.log(Level.SEVERE, "closing rs / pstmt", e);
			}
		}
		return lista;

	}

	public static List<LabelValueBean> getLVB(Properties ctx, String trxName) {
		final List<LabelValueBean> lista = new ArrayList<LabelValueBean>();
		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			sql.append("SELECT  * FROM ").append(Table_Name).append(" where isActive = 'Y' ");
			sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name));

			pstmt = DB.prepareStatement(sql.toString(), trxName);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				lista.add(new LabelValueBean(rs.getString(COLUMNNAME_Name), rs.getString(COLUMNNAME_M_FreightCategory_ID)));
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs,pstmt);
		}
		return lista;
	}
}
