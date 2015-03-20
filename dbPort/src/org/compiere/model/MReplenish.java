package org.compiere.model;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.QuickSearchTables;
import org.compiere.util.Utilerias;

import com.ecaresoft.util.EcsException;
import com.ecaresoft.util.Error;
import com.ecaresoft.util.ErrorList;

/**
 * Almacen-Producto
 */
public class MReplenish extends X_M_Replenish {

	/** serialVersionUID. */
	private static final long serialVersionUID = 1L;
	private static CLogger slog = CLogger.getCLogger(MReplenish.class);

	/**
	 * Constructor Result set
	 * 
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MReplenish(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	/**
	 * Constructor key
	 * 
	 * @param ctx
	 * @param M_Replenish_ID
	 * @param trxName
	 */
	public MReplenish(Properties ctx, int M_Replenish_ID, String trxName) {
		super(ctx, M_Replenish_ID, trxName);
	}

	@Override
	protected boolean afterSave(boolean newRecord, boolean success) {
		if (success) {
			/* Cambia a  EXME_ProductoOrg 
			try {

				// El almacen ya no se obtendra de la configuracion
				// int warehouseID =
				// MEXMEMejoras.get(getCtx()).getM_Warehouse_ID();
				// if (getM_Warehouse_ID() == warehouseID && //
				if (newRecord //
						|| is_ValueChanged(COLUMNNAME_M_Warehouse_ID) //
						// && warehouseID ==
						// get_ValueOldAsInt(COLUMNNAME_M_Warehouse_ID)) //
						|| is_ValueChanged("IsActive")//
						|| is_ValueChanged("IsFormulary")//
				// )//
				) {
					// Actualiza el GenProduct
					MEXMEGenProduct.actualizarEXMETGenProdTrade(getCtx(),
							getM_Product_ID(), getM_Warehouse_ID(), newRecord,
							isActive(), isFormulary(), get_TrxName());
				}
				
				
			} catch (Exception e) {
			}

			
			try {
				QuickSearchTables.checkTables(MProduct.class,MProduct.Table_Name, getM_Product_ID(), get_TrxName() , p_ctx);
			} catch (Exception ex) {
				log.log(Level.WARNING, "QuickSearchTables.checkTables", ex);
			}*/
			
		}
		QuickSearchTables.rebuildAll(MProduct.Table_Name + "@PSol*", getCtx());
		return success;
	}

	@Override
	protected boolean afterDelete(boolean success) {
		/* Cambia a  EXME_ProductoOrg 
		if (success) {
			try {
				if (isFormulary()) {
					MEXMEGenProduct.actualizarEXMETGenProdTrade(getCtx(), getM_Product_ID(), 0, false, isActive(), isFormulary(), get_TrxName());
				}
			} catch (Exception e) {
			}
		}*/
		return success;
	}
	
	
	/**************************************************************************
	 * 	Before Save
	 *	@param newRecord new
	 *	@return true
	 */
	protected boolean beforeSave (boolean newRecord)
	{
		//if(newRecord){
		//	revCodePorProduct(); //Cambia a  EXME_ProductoOrg 
		//}
		return true;
	}

	/**
	 * Revenue Code - Product
	 * @return
	 * Cambia a  EXME_ProductoOrg 
	public boolean revCodePorProduct(){
		if(getM_Product_ID()>0 && getEXME_RevenueCode_ID()<=0){
			if(getProduct()!=null && getProduct().getEXME_RevenueCode_ID()>0){
				setEXME_RevenueCode_ID(getProduct().getEXME_RevenueCode_ID());
				return true;
			}
		}

		return false;
	}*/

	private MProduct m_product = null;

	/**
	 * Obtenemos le producto.
	 * @return
	 */
	public MProduct getProduct(){

		if(m_product == null || m_product.getM_Product_ID() == 0){
			m_product = new MProduct(getCtx(), getM_Product_ID(), get_TrxName());
		}

		return m_product;

    }
	
	/**
	 * Pregunta por un replenish con la llave primaria
	 * @param ctx
	 * @param M_Warehous_ID almacen a preguntar
	 * @param M_Product_ID producto por el que se pregunta
	 * @param trxName
	 * @return
	 */	
	public static MReplenish get(Properties ctx, int M_Warehous_ID,
			int M_Product_ID, String trxName) {
		
		MReplenish retValue = null;
		String sql = "SELECT * FROM m_Replenish WHERE M_Warehouse_ID=? AND M_Product_ID=?";
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql, trxName);
			pstmt.setInt(1, M_Warehous_ID);
			pstmt.setInt(2, M_Product_ID);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				retValue = new MReplenish(ctx, rs, trxName);
			}
			
		} catch (Exception e) {
			slog.log(Level.SEVERE, sql, e);
		} finally {
			DB.close(rs, pstmt);
		}
		return retValue;
	} // get
	
	/**
	 * Productos del Almacen
	 * @param ctx
	 * @param M_Warehous_ID
	 * @param trxName
	 * @return
	 */
	public static List<Integer> getProductsByWarehouse(Properties ctx, int M_Warehous_ID, String trxName) {
		return getProductsByWarehouse(ctx, M_Warehous_ID, -1, trxName);
	}
	
	/**
	 * Productos del Almacen y categoría
	 * @param ctx
	 * @param M_Warehous_ID
	 * @param trxName
	 * @return
	 */
	public static List<Integer> getProductsByWarehouse(Properties ctx, int M_Warehous_ID, int pCategoryId, String trxName) {
		List<Integer> ids = new ArrayList<Integer>();
		
		List<Object> params = new ArrayList<Object>();
		params.add(M_Warehous_ID);
		
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ");
		sql.append("  r.M_Product_ID ");
		sql.append("FROM ");
		sql.append("  M_Replenish r ");
		sql.append("  INNER JOIN m_product p ");
		sql.append("  ON r.m_product_id = p.m_product_id ");
		sql.append("WHERE ");
		sql.append("  r.m_warehouse_id = ? AND ");
		// ETS 08085 — No respeta inactivación de producto en almacen
		// Jesus Cantu
		sql.append("  r.isactive = 'Y' ");

		if (pCategoryId > 0) {
			sql.append("  AND p.m_product_category_id = ? ");
			params.add(pCategoryId);
		}

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			DB.setParameters(pstmt, params);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				ids.add(rs.getInt(1));
			}
		} catch (Exception e) {
			slog.log(Level.SEVERE, null, e);
		} finally {
			DB.close(rs, pstmt);
		}
		return ids;
	}
	
	/**
	 * Convierte las líneas generadas automáticamente en un movimiento entre almacenes
	 * 
	 * @param ctx
	 *            Contexto de app
	 * @param errorList
	 *            Listado de errores
	 * @param list
	 *            Listado de líneas
	 * @param warehouseToId
	 *            Almacén solicitante
	 * @param warehouseFromId
	 *            Almacén que surte
	 * @param trxName
	 *            Trx Name
	 * @return Numero de documento generado o null si no se generó
	 * @throws Exception
	 *             Si el error es de tipo {@link EcsException} se puede usar directamente {@link EcsException#getMessage()} para mostrar al usuario
	 */
	public static String createRequestFromLines(Properties ctx, ErrorList errorList, List<AutomaticRequest> list, //
			int warehouseToId, int warehouseFromId, String trxName) throws Exception {
		String documentNo = null;

		MMovement movHdr = new MMovement(ctx, 0, null);
		movHdr.setMovementDate(DB.convert(ctx, new Timestamp(System.currentTimeMillis())));
		movHdr.setM_Warehouse_ID(warehouseFromId);
		movHdr.setM_WarehouseTo_ID(warehouseToId);
		movHdr.setPriorityRule(X_M_Movement.PRIORITYRULE_Medium);

		// Insertamos el encabezado de movimiento
		if (!movHdr.save(trxName)) {
			errorList.add(Error.EXCEPTION_ERROR, Utilerias.getAppMsg(ctx, "error.traspasos.noInsertMov"));
		}

		if (errorList.isEmpty()) {

			// Insercion de las lineas
			for (AutomaticRequest request : list) {

				// Localizador por defecto del almacen de resurtido
				MWarehouse warehouseTo = new MWarehouse(ctx, warehouseToId, null);

				errorList.getList().addAll(MMovementLine.saveLine(movHdr.getM_Movement_ID(), null, request.getProductId(), //
						request.getcUomId(), request.getAmount(), null, warehouseFromId, warehouseTo.getDefaultLocator().getM_Locator_ID(), trxName).getList());

				if (!errorList.isEmpty()) {
					break;
				}
			}

			if (errorList.isEmpty()) {
				errorList.getList().addAll(MMovement.request(ctx, movHdr.getM_Movement_ID(), //
						MMovement.getLines(ctx, movHdr.getM_Movement_ID(), trxName), trxName).getList());
				
				if(errorList.isEmpty()){
					documentNo = movHdr.getDocumentNo();
				}
			}
		}

		return documentNo;
	}
	
	/**
	 * Genera las líneas para un pedido automático, se basa en
	 * {@link MReplenish#getLevel_Max()} {@link MReplenish#getLevel_Min()} y
	 * {@link MStorage#getQtyOnHand()} {@link MStorage#getQtyReserved()}.
	 * {@link #getC_UOM_Volume_ID()} es la unidad seleccionada para los niveles,
	 * no necesariamente es de volúmen
	 * 
	 * @param ctx
	 *            Contexto de la app
	 * @param warehouseId
	 *            Almacén quien solicita
	 * @param trxName
	 *            Trx Name
	 * @return
	 */
	public static List<AutomaticRequest> getAutomaticRequestLines(Properties ctx, int warehouseId, String trxName) {
		List<AutomaticRequest> list = new ArrayList<AutomaticRequest>();

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ");
		sql.append("  p.m_product_id, ");
		sql.append("  p.value, ");
		sql.append("  p.name, ");
		sql.append("  p.description, ");
		sql.append("  r.level_min, ");
		sql.append("  r.level_max, ");
		sql.append("  r.replenishtype, ");
		sql.append("  p.c_uom_id, ");
		sql.append("  p.c_uomvolume_id, ");
		sql.append("  r.c_uom_volume_id ");
		sql.append("FROM ");
		sql.append("  m_replenish r ");
		sql.append("  INNER JOIN m_product p ");
		sql.append("  ON r.m_product_id = p.m_product_id ");
		
		sql.append("  INNER JOIN exme_productoorg pOrg ");
		sql.append("  ON (p.m_product_id = pOrg.m_product_id ");
		sql.append("  AND pOrg.ad_org_id = r.ad_org_id) ");
		
		sql.append("WHERE ");
		sql.append("  r.m_warehouse_id = ? AND ");
		sql.append("  r.isactive = 'Y' AND ");
		sql.append("  r.replenishtype in (?,?) AND ");
		sql.append("  p.isStocked = 'Y' AND ");
		sql.append("  p.isActive = 'Y' AND ");
		sql.append("  pOrg.unused = 'N' ");

		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, Constantes.SPACE, MReplenish.Table_Name, "r"));

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, warehouseId);
			pstmt.setString(2, MReplenish.REPLENISHTYPE_MaintainMaximumLevel);
			pstmt.setString(3, MReplenish.REPLENISHTYPE_ReorderBelowMinimumLevel);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				int prodId = rs.getInt(1);
				String prodValue = rs.getString(2);
				String prodName = rs.getString(3);
				String prodDesc = rs.getString(4);
				BigDecimal min = rs.getBigDecimal(5);
				BigDecimal max = rs.getBigDecimal(6);
				String type = rs.getString(7);
				int cUomId = rs.getInt(8);
				int cUomVolId = rs.getInt(9);
				int cUomReqId = rs.getInt(10);
				
				BigDecimal qtyOnHand = MStorage.getQtyAvailable(warehouseId, prodId, -1, null);
				
				BigDecimal value = null;

				if (qtyOnHand == null) {
					qtyOnHand = BigDecimal.ZERO;
				}
				
				if (cUomId != cUomVolId) {
					if (cUomVolId == cUomReqId) {
						max = MEXMEUOMConversion.convertProductTo(ctx, prodId, cUomVolId, max, null, true);
						min = MEXMEUOMConversion.convertProductTo(ctx, prodId, cUomVolId, min, null, true);

						if (max == null) {
							max = BigDecimal.ZERO;
						}

						if (min == null) {
							min = BigDecimal.ZERO;
						}

						qtyOnHand = MEXMEUOMConversion.convertProductTo(ctx, prodId, cUomVolId, qtyOnHand, null, true);
						
						if(qtyOnHand == null){
							qtyOnHand = BigDecimal.ZERO;
						}else{
							qtyOnHand = qtyOnHand.setScale(0, BigDecimal.ROUND_FLOOR);
						}
					}
				}

				if (MReplenish.REPLENISHTYPE_MaintainMaximumLevel.equals(type)) {
					value = max;
				} else {
					value = min;
				}

				if (qtyOnHand.compareTo(value) < 0) {
					BigDecimal diff = max.subtract(qtyOnHand);
					
					AutomaticRequest request = new AutomaticRequest();
					request.setProductId(prodId);
					request.setProductValue(prodValue);
					request.setProductName(prodName);
					request.setProductDesc(prodDesc);
					request.setLevelMax(max);
					request.setLevelMin(min);
					request.setcUomId(cUomReqId);
					request.setAmount(diff);
					request.setOnHand(qtyOnHand);
					request.setType(MRefList.getListName(ctx, X_M_Replenish.REPLENISHTYPE_AD_Reference_ID, type));

					list.add(request);
				}
			}
		} catch (Exception e) {
			slog.log(Level.SEVERE, null, e);
		} finally {
			DB.close(rs, pstmt);
		}
		
		return list;
	}
	
	/**
	 * Objeto para almacenr datos referentes al pedido automático
	 * 
	 * @author odelarosa
	 * 
	 */
	public static class AutomaticRequest {
		private int productId;
		private String productName;
		private String productValue;
		private String productDesc;
		private BigDecimal levelMax;
		private BigDecimal levelMin;
		private int cUomId;
		private String cUomName;
		private BigDecimal amount;
		private BigDecimal onHand;
		private String type;

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		public BigDecimal getOnHand() {
			return onHand;
		}

		public void setOnHand(BigDecimal onHand) {
			this.onHand = onHand;
		}

		public int getProductId() {
			return productId;
		}

		public void setProductId(int productId) {
			this.productId = productId;
		}

		public String getProductName() {
			return productName;
		}

		public void setProductName(String productName) {
			this.productName = productName;
		}

		public String getProductValue() {
			return productValue;
		}

		public void setProductValue(String productValue) {
			this.productValue = productValue;
		}

		public String getProductDesc() {
			return productDesc;
		}

		public void setProductDesc(String productDesc) {
			this.productDesc = productDesc;
		}

		public BigDecimal getLevelMax() {
			return levelMax;
		}

		public void setLevelMax(BigDecimal levelMax) {
			this.levelMax = levelMax;
		}

		public BigDecimal getLevelMin() {
			return levelMin;
		}

		public void setLevelMin(BigDecimal levelMin) {
			this.levelMin = levelMin;
		}

		public int getcUomId() {
			return cUomId;
		}

		public void setcUomId(int cUomId) {
			this.cUomId = cUomId;
		}

		public String getcUomName() {
			return cUomName;
		}

		public void setcUomName(String cUomName) {
			this.cUomName = cUomName;
		}

		public BigDecimal getAmount() {
			return amount;
		}

		public void setAmount(BigDecimal amount) {
			this.amount = amount;
		}
	}
}
