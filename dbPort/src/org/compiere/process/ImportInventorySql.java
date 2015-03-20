package org.compiere.process;

import java.sql.Timestamp;

import org.compiere.util.Constantes;
import org.compiere.util.DB;

/**
 * @author arangel
 * Actualizacion de datos para importacion de inventario
 * Clase que contiene los querys que estaban en doit, para la importacion de inventarios
 */
public class ImportInventorySql {
	
	/**
	 * 
	 * @param p_AD_Client_ID
	 * @param p_AD_Org_ID
	 * @param p_MovementDate
	 * @return
	 */
	public static StringBuffer updateInventory(int p_AD_Client_ID, int p_AD_Org_ID, Timestamp p_MovementDate){
		StringBuffer sql = new StringBuffer(Constantes.INIT_CAPACITY_ARRAY);
		sql.append("UPDATE I_Inventory SET AD_Client_ID = COALESCE (AD_Client_ID,")
				.append(p_AD_Client_ID)
				.append("), AD_Org_ID = COALESCE (AD_Org_ID,")
				.append(p_AD_Org_ID).append("),");
		if (p_MovementDate != null) {
			sql.append(" MovementDate = COALESCE (MovementDate,")
			.append(DB.TO_DATE(p_MovementDate)).append("),");
		}
		sql.append(" IsActive = COALESCE (IsActive, 'Y'),")
				.append(" Created = COALESCE (Created, SysDate),")
				.append(" CreatedBy = COALESCE (CreatedBy, 0),")
				.append(" Updated = COALESCE (Updated, SysDate),")
				.append(" UpdatedBy = COALESCE (UpdatedBy, 0),")
				.append(" I_ErrorMsg = NULL,")
				.append(" M_Warehouse_ID = NULL," )// reset
				.append(" I_IsImported = 'N' ")
				.append("WHERE I_IsImported<>'Y' OR I_IsImported IS NULL");
		return sql;
	}
	
	/**
	 * 
	 * @param clientCheck
	 * @return
	 */
	public static StringBuffer iventoryIsImported(String clientCheck){
		StringBuffer sql = new StringBuffer(Constantes.INIT_CAPACITY_ARRAY);
		sql = new StringBuffer("UPDATE I_Inventory o ")
				.append("SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||'ERR=Invalid Org, '")
				.append("WHERE (AD_Org_ID IS NULL OR AD_Org_ID=0")
				.append(" OR EXISTS (SELECT * FROM AD_Org oo WHERE o.AD_Org_ID=oo.AD_Org_ID AND (oo.IsSummary='Y' OR oo.IsActive='N')))")
				.append(" AND I_IsImported<>'Y'").append(clientCheck);
		return sql;
	}
	
	/**
	 * 
	 * @param clientCheck
	 * @return
	 */
	public static StringBuffer updateLocator(String clientCheck){
		StringBuffer sql = new StringBuffer(Constantes.INIT_CAPACITY_ARRAY);
		sql.append("UPDATE I_Inventory i ")
		.append("SET M_Locator_ID=(SELECT M_Locator_ID FROM M_Locator l")
		.append(" WHERE i.LocatorValue=l.Value AND i.AD_Client_ID=l.AD_Client_ID ");
	
		if (DB.isOracle()) {
			sql.append(" AND ROWNUM=1) ");
		} else if (DB.isPostgreSQL()) {
			sql = new StringBuffer(DB.getDatabase().addPagingSQL(
					sql.toString(), 1, 1));
			sql.append(") ");
		}
	
		sql.append("WHERE M_Locator_ID IS NULL AND LocatorValue IS NOT NULL");
		sql.append(" AND I_IsImported<>'Y'").append(clientCheck);
		return sql;		
	}
	
	/**
	 * 
	 * @param clientCheck
	 * @return
	 */
	public static StringBuffer updateLocatorXYZ(String clientCheck){	
		StringBuffer sql = new StringBuffer(Constantes.INIT_CAPACITY_ARRAY);
		sql.append("UPDATE I_Inventory i ")
				.append("SET M_Locator_ID=(SELECT M_Locator_ID FROM M_Locator l")
				.append(" WHERE i.X=l.X AND i.Y=l.Y AND i.Z=l.Z AND i.AD_Client_ID=l.AD_Client_ID ");

		if (DB.isOracle()) {
			sql.append(" AND ROWNUM=1) ");
		} else if (DB.isPostgreSQL()) {
			sql = new StringBuffer(DB.getDatabase().addPagingSQL(
					sql.toString(), 1, 1));
			sql.append(") ");
		}

		sql.append(" WHERE M_Locator_ID IS NULL AND X IS NOT NULL AND Y IS NOT NULL AND Z IS NOT NULL ")
				.append(" AND I_IsImported<>'Y' ").append(clientCheck);
		return sql;
	}
	
	/**
	 *
	 * @param clientCheck
	 * @param locator
	 * @return
	 */
	public static StringBuffer updateLocatorWarehouse(String clientCheck, boolean locator){	
		StringBuffer sql = new StringBuffer("UPDATE I_Inventory ")
				.append("SET I_IsImported = 'E', I_ErrorMsg=I_ErrorMsg || ")
				.append(locator ?  " 'ERR=No Location, ' " : " 'ERR=No Warehouse, '" )
				.append(" WHERE ")
				.append(locator ? 	"M_Locator_ID " : "M_Warehouse_ID ")
				.append("IS NULL")
				.append(" AND I_IsImported <> 'Y' ").append(clientCheck);
		return sql;	
	}
	
	/**
	 * 
	 * @param clientCheck
	 * @return
	 */
	public static StringBuffer updateWarehouse(String clientCheck) {
		StringBuffer sql = new StringBuffer("UPDATE I_Inventory i ")
				.append("SET M_Warehouse_ID = ( SELECT l.M_Warehouse_ID FROM M_Locator l  ")
				.append(" inner join m_warehouse wa on wa.m_warehouse_id = l.m_warehouse_id ")
				.append(" inner join i_inventory ii on wa.value = ii.warehousevalue ")
				.append(" and ii.M_Locator_ID = l.M_Locator_ID  ");
		if (DB.isOracle()) {
			sql.append("\t\t AND ROWNUM=1)\n");
		} else if (DB.isPostgreSQL()) {
			sql = new StringBuffer(DB.getDatabase().addPagingSQL(
					sql.toString(), 1, 1));
			sql.append(")\n ");
		}

		sql.append(" WHERE M_Locator_ID IS NOT NULL  ")
				.append(" AND I_IsImported<>'Y'").append(clientCheck);
		return sql;
	}

	/**
	 * 
	 * @param clientCheck
	 * @return
	 */
	public static StringBuffer updateProduct(String clientCheck){	
		StringBuffer sql = new StringBuffer("UPDATE I_Inventory i\n")
				.append("SET M_Product_ID = ( SELECT p.M_Product_ID \n")
				.append("\tFROM   M_Product p \n")
				.append("\t\tINNER  JOIN EXME_ProductoOrg po ON p.M_Product_ID = po.M_Product_ID\n")
				.append("\tWHERE i.Value = p.Value\n")
				.append("\t\tAND i.AD_Org_ID = po.AD_Org_ID\n ");
	//				+ "                        AND    i.AD_Org_ID = p.AD_Org_ID  ");

		if (DB.isOracle()) {
			sql.append("\t\t AND ROWNUM=1)\n");
		} else if (DB.isPostgreSQL()) {
			sql = new StringBuffer(DB.getDatabase().addPagingSQL(
					sql.toString(), 1, 1));
			sql.append(")\n ");
		}
	
			sql.append(" WHERE M_Product_ID IS NULL AND Value IS NOT NULL")
					.append(" AND I_IsImported<>'Y' ").append(clientCheck);
	
		return sql;
	}
	
	/**
	 * 
	 * @param clientCheck
	 * @return
	 */
	public static StringBuffer updateProductImported(String clientCheck){
		StringBuffer sql = new StringBuffer("UPDATE I_Inventory i ")
				.append(" SET M_Product_ID=(SELECT M_Product_ID FROM M_Product p ")
				.append(" WHERE i.UPC=p.UPC AND i.AD_Client_ID=p.AD_Client_ID ");

		if (DB.isOracle()) {
			sql.append(" AND ROWNUM=1) ");
		} else if (DB.isPostgreSQL()) {
			sql = new StringBuffer(DB.getDatabase().addPagingSQL(
					sql.toString(), 1, 1));
			sql.append(") ");
		}

		sql.append(" WHERE M_Product_ID IS NULL AND UPC IS NOT NULL ")
				.append(" AND I_IsImported<>'Y' ").append(clientCheck);
		return sql;
	}
	
	/**
	 * 
	 * @param clientCheck
	 * @return
	 */
	public static StringBuffer updateProductImportedErr(String clientCheck){
		StringBuffer sql = new StringBuffer("UPDATE I_Inventory ")
				.append(" SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||'ERR=No Product, ' ")
				.append(" WHERE M_Product_ID IS NULL ")
				.append(" AND I_IsImported<>'Y' ").append(clientCheck);	
		return sql;
	}
	
	/**
	 * 
	 * @param clientCheck
	 * @return
	 */
	public static StringBuffer updateQtyCount(String clientCheck){
		StringBuffer sql = new StringBuffer("UPDATE I_Inventory ")
				.append(" SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||'ERR=No Qty Count, ' ")
				.append(" WHERE QtyCount IS NULL" + " AND I_IsImported<>'Y' ")
				.append(clientCheck);
		return sql;
	}
}