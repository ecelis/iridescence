package org.compiere.model.bpm;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.model.MEXMEActPacienteInd;
import org.compiere.model.MEXMELookupInfo;
import org.compiere.model.MEXMERevenueCodes;
import org.compiere.model.X_M_Product;
import org.compiere.util.CLogger;
import org.compiere.util.DB;

/**
 * Consulta la vista EXME_CargosAGenerar_V para localizar los cargos que aun no
 * se generan debido a que no tienen un rev.Code
 * 
 * @author Expert
 * 
 */
public class AsignacionCodBeneficio {
	/** Logger */
	protected static CLogger log = CLogger.getCLogger(AsignacionPrecios.class);

	/**
	 * Obtenemos los cargos a procesar por nivel de acceso
	 * @param ctx contexto 
	 * @param pEXMECtaPacID cuenta paciente
	 * @return listado de cargos por cuenta paciente
	 */
	public static List<MEXMEActPacienteInd> getLstDetalle(Properties ctx,
			int pEXMECtaPacID, int productID) {
		StringBuilder sql = new StringBuilder();

		sql.append(" SELECT i.* ");
		sql.append(" , cagv.ProdValue || ' ' || cagv.ProdName AS ProdName ");
		sql.append(" , cagv.WareName ");
		sql.append(" , cagv.revcodereplenishname ");
		sql.append(" , cagv.revcodewarename ");
		sql.append(" , cagv.revcodereplenishid AS ReplenishRC");
		sql.append(" , cagv.revcodewareid AS WarehouseRC ");
		sql.append(" , cagv.ProductClass   ");
		sql.append(" , g.JCODE   ");
		sql.append(" , cagv.EXME_Intervencion_ID ");
		sql.append(" , inte.EXME_RevenueCode_ID AS IntervencionRC ");
		sql.append(" , t.EXME_RevenueCode_ID AS TipoHabitacionRC ");
		sql.append(" , rci.Name AS revcodeInterName ");
		sql.append(" , rch.Name AS revcodeTipoHabName ");

		sql.append(" FROM  EXME_CargosAGenerar_V cagv  ");
		sql
				.append(" INNER JOIN EXME_ActPacienteInd i ON cagv.exme_actpacienteInd_id = i.exme_actpacienteInd_id ");
		sql.append(" LEFT JOIN EXME_TipoHabitacion t    ");
		sql.append("  	ON cagv.M_product_Id = t.M_product_ID ");
		sql.append(" LEFT JOIN EXME_GenProduct g    ");
		sql.append("  	ON cagv.EXME_GenProduct_Id = g.EXME_GenProduct_ID ");
		sql.append(" LEFT JOIN EXME_Intervencion inte ");
		sql
				.append(" 	ON inte.EXME_Intervencion_ID = cagv.Exme_intervencion_id ");
		sql.append(" LEFT JOIN EXME_RevenueCode rch ");
		sql.append("    ON t.EXME_RevenueCode_ID  = rch.EXME_RevenueCode_ID ");
		sql.append(" LEFT JOIN EXME_RevenueCode rci ");
		sql
				.append("    ON inte.EXME_RevenueCode_ID = rci.EXME_RevenueCode_ID ");

		if (DB.isOracle()) {
			sql.append(" WHERE cagv.M_Product_ID > 0  AND rownum <=100 ");
		} else if (DB.isPostgreSQL()) {
			sql.append(" WHERE cagv.M_Product_ID > 0 ");
		}
		
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ",
				"EXME_CargosAGenerar_V", "cagv"));

		if (pEXMECtaPacID > 0) {// 1000005
			sql.append(" AND cagv.EXME_CtaPac_ID = ? ");
		}
		
		if (productID > 0) {
			sql.append(" AND cagv.M_Product_ID = ?  ");
		} 
		
		if (DB.isOracle()) {
			sql.append(" AND rownum <= 100 ");
			sql.append(" ORDER BY cagv.ProdName ");
		} else if (DB.isPostgreSQL()) {
			sql.append(" ORDER BY cagv.ProdName ");
			sql.append(" LIMIT 100 ");
		}

		List<MEXMEActPacienteInd> list = new ArrayList<MEXMEActPacienteInd>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			pstmt = DB.prepareStatement(sql.toString(), null);
			
			if (pEXMECtaPacID > 0) {
				pstmt.setInt(1, pEXMECtaPacID);
				if (productID > 0) {
					pstmt.setInt(2, productID);
				}
			} else if (productID > 0) {
				pstmt.setInt(1, productID);
			}
			
			rs = pstmt.executeQuery();

			MEXMERevenueCodes revenueCodes = null;
			
			while (rs.next()) {
				BeanView bean = new BeanView();
				bean.setCadena1(rs.getString("ProdName"));
				bean.setCadena2(rs.getString("WareName"));
				//bean.setCadena3(rs.getString("revcodename"));
				//bean.setInteger1(rs.getInt("revcodeid"));

				MEXMEActPacienteInd modelo = new MEXMEActPacienteInd(ctx, rs,
						null);
				modelo.setBeanID(bean);
				
				// Revenue code que corresponde
				revenueCodes = AsignacionCodBeneficio.getRevCodePorProductClass(ctx,rs);
				if (revenueCodes != null && revenueCodes.getEXME_RevenueCode_ID() > 0) {

					/*revenueCodes.setInfoProd(new String[] {
							rs.getString("ProductClass"),
							rs.getString("JCODE") });*/
					modelo.setBeanRevCode(revenueCodes);
				}
			
				
				list.add(modelo);

			}

		} catch (Exception e) {
			log.log(Level.SEVERE, "MEstServ.getLstDetalle - sql: " + sql, e);
		} finally {
			DB.close(rs, pstmt);
		}
		return list;
	}

	/**
	 * Reglas para uso de Revenue Codes 6-Sep-2011 1- Mantenimiento de
	 * Productos, Estudios y Habitaciones: � Si ProductClass=�Drug� y no tiene
	 * JCode asignado, entonces mostrar Revenue Code contenido en M_Warehouse
	 * como Revenue Code de default, pudiendo el usuario modificarlo por
	 * cualquier otro, quedando actualizado M_Replenish. � Si
	 * ProductClass=�Drug� y s� tiene JCode asignado, entonces mostrar Revenue
	 * Code asociado al CPT (Exme_Intervencion) como Revenue Code de default,
	 * pudiendo el usuario modificarlo por cualquier otro, quedando actualizado
	 * M_Replenish. � Si (ProductClass != �Drug� & != �Medical Supplies� & !=
	 * �Rooms�)entonces mostrar Revenue Code asociado al CPT (Exme_Intervencion)
	 * como Revenue Code de default, pudiendo el usuario modificarlo por
	 * cualquier otro, quedando actualizado M_Replenish. � Si
	 * ProductClass=�Medical Supplies� entonces mostrar Revenue Code contenido
	 * en M_Warehouse como Revenue Code de default, pudiendo el usuario
	 * modificarlo por cualquier otro, quedando actualizado M_Replenish. � Si
	 * ProductClass=�Rooms� entonces mostrar Revenue Code contenido en
	 * Exme_TipoHabitacion.
	 * 
	 * 2. Cargos a la cuenta paciente: � Si es Favorito -- contenido en tabla
	 * M_Replenish Si rc en m_replenish = nul --- no se le configur� rv en la
	 * unidad de servicio Si (ProductClass != �Drug� & != �Medical Supplies�) -
	 * es un estudio: Tomar rc de CPT�s (Exme_Intervencion) Sino Tomar rc de
	 * Service Unit (M_Warehouse) FinSino Sino Tomr rc de m_replenish. FinSino
	 * Sino -- no es favorito Si (ProductClass != �Drug� & != �Medical
	 * Supplies�) - es un estudio: Tomar rc de CPT�s (Exme_Intervencion) Sino
	 * Tomar rc de Service Unit (M_Warehouse) FinSino FinSino
	 * 
	 * 
	 * NOTA: La condici�n para que funcione esto: - Todo ProductClass deber�
	 * tener relacionado al menos 1 Service Unit.
	 */

	/**
	 * Listado de rev. code (beneficios) por producto almacen
	 * @param ctx
	 * @param productID
	 * @param trxName
	 * @return
	 */
	public static List<MEXMERevenueCodes> getRevCode(Properties ctx,
			int productID, int warehouseID, String trxName) {

		StringBuilder sql = new StringBuilder();
		MEXMERevenueCodes revenueCodes = null;

		sql.append(" SELECT  ");
		sql.append("    r.EXME_RevenueCode_ID AS ReplenishRC ");
		sql.append("  , w.EXME_RevenueCode_ID AS WarehouseRC ");
		sql.append("  , i.EXME_RevenueCode_ID AS IntervencionRC ");
		sql.append("  , t.EXME_RevenueCode_ID AS TipoHabitacionRC ");
		sql.append("  , p.ProductClass ");
		sql.append("  , g.JCODE ");
		sql.append("  ,	r.M_Warehouse_Id ");
		sql.append("  FROM M_Product p ");
		sql.append("  LEFT JOIN M_Replenish r  ");
		sql.append("  LEFT JOIN M_Warehouse w ");
		sql.append("  ON r.M_Warehouse_Id = w.M_Warehouse_ID ");
		
		sql.append(" 	ON p.M_product_Id = r.M_product_ID ");
		sql.append("  LEFT JOIN EXME_Intervencion i  ");
		sql.append("  	ON p.EXME_Intervencion_Id = i.EXME_Intervencion_ID ");
		sql.append("  LEFT JOIN EXME_TipoHabitacion t  ");
		sql.append("  	ON p.M_product_Id = t.M_product_ID ");
		sql.append("  LEFT JOIN EXME_GenProduct g  ");
		sql.append("  	ON p.EXME_GenProduct_Id = g.EXME_GenProduct_ID ");
		sql.append(" WHERE p.M_Product_Id = ? ");

		sql.append(warehouseID > 0 ? " AND r.M_Warehouse_Id =? " : "");
		
		List<MEXMERevenueCodes> list = new ArrayList<MEXMERevenueCodes>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setInt(1, productID);
			if( warehouseID > 0)
				pstmt.setInt(2, warehouseID);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				revenueCodes = AsignacionCodBeneficio.getRevCodePorProductClass(ctx,rs);
				if (revenueCodes.getEXME_RevenueCode_ID() > 0) {

					revenueCodes.setInfoProd(new String[] {
							rs.getString("ProductClass"),
							rs.getString("JCODE"),
							String.valueOf(rs.getInt("M_Warehouse_Id")) });
					list.add(revenueCodes);
				}
			}// fin rs

		} catch (Exception e) {
			log.log(Level.SEVERE, "MEstServ.get", e);
			list = null;
		} finally {
			DB.close(rs, pstmt);
		}
		return list;
	}

	/**
	 * De todos los rev. code que puede tener un producto
	 * se selecciona uno.
	 * @param ctx contexto
	 * @param rs Resulset
	 * @return
	 */
	public static MEXMERevenueCodes getRevCodePorProductClass(Properties ctx,
			ResultSet rs) {
		MEXMERevenueCodes revenueCodes = null;

		try {
			revenueCodes = new MEXMERevenueCodes(ctx, rs.getInt("ReplenishRC"),
					null);

			if (revenueCodes.getEXME_RevenueCode_ID() <= 0) {

				if (rs.getString("ProductClass").equals(
						X_M_Product.PRODUCTCLASS_Drug)) {
					if (rs.getString("JCODE") == null) {
						revenueCodes = new MEXMERevenueCodes(ctx, rs
								.getInt("WarehouseRC"), null);
					} else {
						revenueCodes = new MEXMERevenueCodes(ctx, rs
								.getInt("IntervencionRC"), null);
					}
				} else if (rs.getString("ProductClass").equals(
						X_M_Product.PRODUCTCLASS_MedicalSupplies)) {
					revenueCodes = new MEXMERevenueCodes(ctx, rs
							.getInt("WarehouseRC"), null);
				} else if (rs.getString("ProductClass").equals(
						X_M_Product.PRODUCTCLASS_Rooms)) {
					revenueCodes = new MEXMERevenueCodes(ctx, rs
							.getInt("TipoHabitacionRC"), null);
				} else {
					revenueCodes = new MEXMERevenueCodes(ctx, rs
							.getInt("IntervencionRC"), null);
				}
			}

			if (revenueCodes.getEXME_RevenueCode_ID() > 0) {

				revenueCodes.setInfoProd(new String[] {
						rs.getString("ProductClass"), rs.getString("JCODE"),
						String.valueOf(rs.getInt("M_Warehouse_Id")) });
			}

		} catch (SQLException e) {
			revenueCodes = null;
			e.printStackTrace();
		}

		return revenueCodes;
	}
}
