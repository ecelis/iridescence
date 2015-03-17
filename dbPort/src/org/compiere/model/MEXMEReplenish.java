package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.apache.struts.util.LabelValueBean;
import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;

/**
 * Replenish
 * @author Expert
 *
 */
public class MEXMEReplenish extends MReplenish {

	/** serialVersionUID. */
	private static final long serialVersionUID = 1L;
	/** Static Logger */
	private static CLogger s_log = CLogger.getCLogger(MEXMEReplenish.class);

	/**
	 * Constructor Result set
	 * 
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MEXMEReplenish(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	/**
	 * Constructor key
	 * 
	 * @param ctx
	 * @param M_Replenish_ID
	 * @param trxName
	 */
	public MEXMEReplenish(Properties ctx, int M_Replenish_ID, String trxName) {
		super(ctx, M_Replenish_ID, trxName);
	}

	/**
	 * Primer registro donde se ubica el producto para cierto almacen si el
	 * producto no se encuentra en ese almacen entonces Primer registro donde se
	 * ubica el producto que este activo
	 * 
	 * @param ctx
	 *            contexto
	 * @param M_Product_ID
	 *            Producto a buscar
	 * @param M_Warehouse_ID
	 *            Almacen (primero a considerar o preferido)
	 * @param trxName
	 *            Nombre de transaccion
	 * @return Registra un objeto de MEXMEReplenish que coincida con los
	 *         criterios
	 *
	public static MEXMEReplenish getProducto(Properties ctx, int M_Product_ID,
			int M_Warehouse_ID, String trxName) {

		// 1ro. Relacion Producto Almacen
		MEXMEReplenish retValue = getProductoAlmacen(ctx, M_Product_ID,
				M_Warehouse_ID, trxName);

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			// 2do. producto con existencias o si alguna vez tuvo existencias
			// y relacionado a un almacen
			if (retValue == null) {
				StringBuilder sql = new StringBuilder(
						Constantes.INIT_CAPACITY_ARRAY);
				sql.append(" SELECT  * FROM M_Replenish ");
				sql.append(" WHERE M_Replenish.IsActive = 'Y' ");
				sql.append(" AND M_Replenish.M_Product_ID = ? ");// M_Product_ID;

				sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ",
						Table_Name));

				pstmt = DB.prepareStatement(sql.toString(), trxName);
				pstmt.setInt(1, M_Product_ID);
				rs = pstmt.executeQuery();

				if (rs.next()) {
					retValue = new MEXMEReplenish(ctx, rs, trxName);
				}
			}

		} catch (Exception e) {
			s_log.log(Level.SEVERE, e.getMessage(), e);
		} finally {
			DB.close(rs, pstmt);
		}

		return retValue;
	}

	/**
	 * Buscamos el registro donde exista el prod para ese almacen
	 * para el proceso ImportInventory
	 * @param ctx
	 * @param M_Product_ID
	 * @param M_Warehouse_ID
	 * @param trxName
	 * @return
	 */
	public static MEXMEReplenish getProductoAlmacen(Properties ctx,
			int producto, int almacen, String trxName) {

		MEXMEReplenish retValue = null;
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append(" SELECT  * FROM M_Replenish ");
		//sql.append(" WHERE M_Replenish.IsActive = 'Y' ");
		sql.append(" WHERE M_Replenish.M_Product_ID = ? ");// + M_Product_ID +
		sql.append(" AND M_Replenish.M_Warehouse_ID = ? ");// + M_Warehouse_ID;

		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name));

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, producto);
			pstmt.setInt(2, almacen);
			rs = pstmt.executeQuery();

			if (rs.next())
				retValue = new MEXMEReplenish(ctx, rs, trxName);

		} catch (Exception e) {
			s_log.log(Level.SEVERE, e.getMessage(), e);
		} finally {
			DB.close(rs, pstmt);
		}
		return retValue;
	}

	/**
	 * Relacion entre almacenes para un producto y un tipo de almacen
	 * 
	 * @param ctx
	 *            Contexto
	 * @param M_Product_ID
	 *            Producto a buscar
	 * @param M_Warehouse_ID
	 *            Almacen que solicita a otros
	 * @param tipoAlmacen
	 *            Tipo de almacen
	 * @param trxName
	 *            Nombre de transaccion
	 * @return Listado de LabelValueBean con los almacenes que surten el
	 *         producto
	 * @throws Exception
	 *
	public static List<LabelValueBean> getProductoAlmacenes(Properties ctx,
			int M_Product_ID, int M_Warehouse_ID, String tipoAlmacen,
			String trxName) throws Exception {

		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql
				.append(" SELECT  w.M_warehouse_id, w.Name ")
				.append(" FROM M_Replenish r ")
				.append(
						" INNER JOIN  EXME_WarehouseRel wr ON wr.M_WarehouseRel_ID = r.M_Warehouse_ID  AND  wr.M_Warehouse_ID = ?")
				.append(
						" INNER JOIN M_Warehouse w ON w.M_Warehouse_ID = wr.M_WarehouseRel_ID ")
				.append(" WHERE r.IsActive = 'Y'  AND r.M_Product_id = ? ");

		List<Object> params = new ArrayList<Object>();
		params.add(M_Warehouse_ID);
		params.add(M_Product_ID);

		if (tipoAlmacen != null) {
			sql.append(" AND w.TipoAlmacen = ?  ");
			params.add(tipoAlmacen);
		}
		sql
				.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name,
						"r"));
		sql.append(" GROUP BY w.M_warehouse_id, w.Name ");
		sql.append(" ORDER BY w.M_warehouse_id, w.Name ");

		return MEXMEReplenish.getConsultaLVB(ctx, sql.toString(), params, null);
	}

	/**
	 * Validamos que un producto se encuentre en un almacen considerando que el
	 * producto sea: vendible, que este activo y que pertenezca al almacen (
	 * parametro ) Si el almacen no es de consigna se puede filtrar por nivel de
	 * acceso
	 * 
	 *@param producto
	 *            El producto a validar
	 *@param almacen
	 *            El almacen donde se debe encontrar el producto
	 *@return true en caso de encontrar el producto en el almacen false en caso
	 *         contrario
	 *@exception Exception
	 *                Description of the Exception
	 */
	public static boolean validarProductoAlm(Properties ctx, long producto,
			long almacen) throws Exception {

		/*boolean encontro = false;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		// Creamos un almacen para validar si es de consigna, eruiz
		MWarehouse wh = new MWarehouse(ctx, (int) almacen, null);

		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		// armamos la cadena sql para validar el producto
		sql
				.append(" SELECT p.M_Product_ID, p.Name ")
				.append(" FROM M_Replenish, M_Product p ")
				.append(
						" WHERE p.IsSold='Y' AND p.IsActive = 'Y' AND p.M_Product_ID = ? ")
				.append(" AND M_Replenish.M_Warehouse_ID = ? ") // Almac�n que
																// solicita
				.append(" AND p.M_Product_ID = M_Replenish.M_Product_ID");

		/*
		 * Si el almacen esta a consigna, quitamos el accesslevel debido a que
		 * un almacen en consigna pertenece a distinta organizacion padre y
		 * al guardar los productos que este puede almacenar, los registros se
		 * guardan con la organizacion padre del almacen. eruiz
		 */
		/*if (!wh.isConsigna())
			sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ",
					"M_Replenish"));

		pstmt = null;
		rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setLong(1, producto);
			pstmt.setLong(2, almacen);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				encontro = true;
			}

		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e.getMessage());
		} finally {
			DB.close(rs, pstmt);
		}
*/
		return MEXMEReplenish.validarRelProductAlm(ctx, (int)producto,
				(int)almacen);
	}

	/**
	 * Buscamos el registro donde exista el prod para ese almacen
	 * 
	 * @param ctx
	 * @param M_Product_ID
	 * @param M_Warehouse_ID
	 * @return
	 */
	public static boolean validarRelProductAlm(Properties ctx, int producto,
			int almacen) throws Exception {

		return MEXMEProductClassWhs.validProductWarehouse( ctx, almacen, producto, null);
	}

	/**
	 * Listado de almacenes relaciondado por un grupo, agrupados por almacen Id
	 * y nombre
	 * 
	 * @param ctx
	 * @param M_Product_ID
	 * @return
	 *
	public static List<LabelValueBean> getWarehousesByProduct(Properties ctx,
			int M_Product_ID) {

		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql
				.append(" SELECT  w.M_warehouse_id, w.Name ")
				.append(" FROM M_Replenish r ")
				.append(
						" INNER JOIN M_Warehouse w ON w.M_Warehouse_ID = r.M_Warehouse_ID ")
				.append(" WHERE r.IsActive = 'Y'  AND r.M_Product_id = ? ");
		sql.append(" GROUP BY w.M_warehouse_id, w.Name ");

		List<Integer> params = new ArrayList<Integer>();
		params.add(M_Product_ID);

		return MEXMEReplenish.getConsultaLVB(ctx, sql.toString(), params, null);
	}

	/**
	 * Obtenemos reposicion de un producto en un almacen
	 * 
	 * @param ctx
	 *            Contexto
	 * @param productId
	 *            Producto que buscamos
	 * @param warehouseId
	 *            Almacen en el que esta el producto
	 * @return
	 *
	public static MEXMEReplenish getReplenish(Properties ctx, int productId,
			int warehouseId) {
		return getProductoAlmacen(ctx, productId, warehouseId, null);
	}

	/**
	 * Productos por defecto del almacen y organizacion
	 * clases ApptProcedures, ApptStudies
	 * @param ctx
	 * @param andClause
	 * @param params
	 * @return
	 */
	public static List<MProduct> getDefaultProducts(Properties ctx,
			String andClause, Object... params) {
		List<MProduct> list = new ArrayList<MProduct>();

		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);

		sql
				.append(" SELECT  prod.* FROM M_Product prod ")
				.append(" WHERE prod.IsActive = 'Y'  AND prod.AD_Org_ID = ? ")
				//.append(" AND DefaultProduct = 'Y' ")
				.append(andClause);

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			DB.setParameters(pstmt, params);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				list.add(new MProduct(ctx, rs, null));
			}

		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
		}

		return list;
	}

	/**
	 * busca en la tabla MReplenish
	 * 
	 * @param ctx
	 *            Contexto
	 * @param sql
	 *            Consulta a la base de datos
	 * @param params
	 *            Listado de parametros en orden deacuerdo a la consulta
	 * @param trxName
	 *            Nombre de transaccion
	 * @return Listado LabelValueBean
	 *
	public static List<LabelValueBean> getConsultaLVB(Properties ctx,
			String sql, List<?> params, String trxName) {
		List<LabelValueBean> lstAlmacen = new ArrayList<LabelValueBean>();

		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {

			stmt = DB.prepareStatement(sql, trxName);
			DB.setParameters(stmt, params);
			rs = stmt.executeQuery();

			while (rs.next()) {
				lstAlmacen.add(new LabelValueBean(rs.getString(2), String
						.valueOf(rs.getInt(1))));
			}

		} catch (Exception e) {
			s_log.log(Level.SEVERE, e.getMessage(), e);
		} finally {
			DB.close(rs, stmt);
		}

		return lstAlmacen;
	}

	/**
	 * 
	 * @param ctx
	 * @param trxName
	 * @return
	 *
	public static List<MEXMEReplenish> getFormulary(Properties ctx, String trxName) {

		List<MEXMEReplenish> retValue = new ArrayList<MEXMEReplenish>();
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append(" SELECT DISTINCT(M_Replenish.m_product_id), m_product.description, M_Replenish.isFormulary FROM M_Replenish ");
		sql.append(" INNER JOIN m_product ON M_Replenish.m_product_id = m_product.m_product_id ");
		sql.append(" WHERE M_Replenish.M_Warehouse_ID IN (SELECT pcw.M_Warehouse_ID");
		sql.append(" FROM EXME_ProductClassWhs pcw INNER JOIN M_Warehouse w ON pcw.M_Warehouse_ID      = w.M_Warehouse_ID  ");
		sql.append(" WHERE pcw.IsActive         = 'Y'  ");
		sql.append(" AND TRIM(pcw.ProductClass) ='" + MProduct.PRODUCTCLASS_Drug.trim() + "')");
		sql.append(" AND M_Replenish.isformulary = 'Y' ");

		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name));
		
//		if(whereClause != null){
//			sql.append(" AND (UPPER(m_product.name) like '%"+ whereClause.toUpperCase() + "%'  ");
//			sql.append(" OR UPPER(m_product.description) like '%" +  whereClause.toUpperCase() + "%')  ");
//		}
		sql.append(" ORDER BY UPPER(m_product.description)");

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
//			pstmt.setInt(1, almacen);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				MEXMEReplenish rp = new MEXMEReplenish(ctx, rs, trxName);
				retValue.add(rp);
			}

		} catch (Exception e) {
			s_log.log(Level.SEVERE, e.getMessage(), e);
		} finally {
			DB.close(rs, pstmt);
		}
		return retValue;
	}
/*
	public static MEXMEReplenish getRelation(Properties ctx, int producto,
			int almacen, String trxName) {

		MEXMEReplenish retValue = null;
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append(" SELECT  * FROM M_Replenish ");
		sql.append(" WHERE M_Replenish.M_Product_ID = ? ");// + M_Product_ID +
		sql.append(" AND M_Replenish.M_Warehouse_ID = ? ");// + M_Warehouse_ID;

		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name));

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, producto);
			pstmt.setInt(2, almacen);
			rs = pstmt.executeQuery();

			if (rs.next())
				retValue = new MEXMEReplenish(ctx, rs, trxName);
		} catch (Exception e) {
			s_log.log(Level.SEVERE, e.getMessage(), e);
		} finally {
			DB.close(rs, pstmt);
		}
		return retValue;
	}

	/**
	 * Valida que un producto exista en almacenes a los que se puede surtir
	 * 
	 * @return
	 * @throws Exception
	 *
	public static List<LabelValueBean> validaAlmacenes(Properties ctx,
			int productID) throws Exception {

		// Validamos almacen que solicita
		if (!MEXMEReplenish.validarProductoAlm(ctx, productID, Env
				.getM_Warehouse_ID(ctx))) {
			return null;
		}

		// Validamos que el producto este en almacenes a los que puede
		// solicitar
		return MEXMEReplenish.getProductoAlmacenes(ctx, productID, Env
				.getM_Warehouse_ID(ctx), null, null);
	}

	/**
	 * Almacenes especificos del producto
	 * 
	 * @param ctx
	 * @param warehouse
	 * @param productID
	 * @param trxName
	 * @return
	 **/
	public static List<MEXMEReplenish> getProdWarehouse(Properties ctx, int productID, String trxName) {

		List<MEXMEReplenish> retValue = new ArrayList<MEXMEReplenish>();
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append(" SELECT  * FROM M_Replenish ");
		sql.append(" WHERE M_Replenish.M_Product_ID = ? ");
//		sql.append(" AND M_Replenish.M_Warehouse_ID IN ( ");
//		sql.append(warehouse);
//		sql.append(" ) ");
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name));
		sql.append("ORDER BY created desc");

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, productID);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				MEXMEReplenish rp = new MEXMEReplenish(ctx, rs, trxName);
				retValue.add(rp);
			}

		} catch (Exception e) {
			s_log.log(Level.SEVERE, e.getMessage(), e);
		} finally {
			DB.close(rs, pstmt);
		}
		return retValue;
	}
	
	
    /**
     * Nombre e identicador para el Rev. code
     * de M_Replenish
     *
    private LabelValueBean revCodeRep = null;

	public LabelValueBean getRevCodeRep() {
		return this.revCodeRep;
	}

	public void setRevCodeRep(LabelValueBean revCodeRep) {
		this.revCodeRep = revCodeRep;
	}
	/**
	 * Object Revenue Codes
	 *
	private MEXMERevenueCodes revenueCodes = null;

	public MEXMERevenueCodes getRevenueCodes() {
		if(revenueCodes==null)
			revenueCodes = new MEXMERevenueCodes(getCtx(), getEXME_RevenueCode_ID(), get_TrxName());
		return this.revenueCodes;
	}

	public void setRevenueCodes(MEXMERevenueCodes revenueCodes) {
		this.revenueCodes = revenueCodes;
	}*/
	
	 /**
     * Nombre e identicador para el almacen
     */
    private LabelValueBean lbvAlmacen = null;

	public LabelValueBean getLbvAlmacen() {
		return this.lbvAlmacen;
	}

	public void setLbvAlmacen(LabelValueBean lbvAlmacen) {
		this.lbvAlmacen = lbvAlmacen;
	}

	/**
	 * Listado de objetos de replenish por producto
	 * @param ctx Contexto
	 * @param M_Product_ID ID producto
	 * @param trxName Nombre de transaccion
	 * @return List<MEXMEReplenish> Listado de objetos de replenish por producto
	 *
	public static List<MEXMEReplenish> getReplenishProd(Properties ctx,
			int M_Product_ID, String trxName) {

		List<MEXMEReplenish> retValue = new ArrayList<MEXMEReplenish>();
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append(" SELECT  * FROM M_Replenish ");
		sql.append(" WHERE M_Replenish.M_Product_ID = ? ");

		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name));
		sql.append(" ORDER BY created desc");

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, M_Product_ID);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				MEXMEReplenish rp = new MEXMEReplenish(ctx, rs, trxName);
				retValue.add(rp);
			}

		} catch (Exception e) {
			s_log.log(Level.SEVERE, e.getMessage(), e);
		} finally {
			DB.close(rs, pstmt);
		}
		return retValue;
	}
	
	/**
	 * Almacenes por clase de producto
	 * @param product objeto del producto
	 * @param isFormulyCheck True. es parte de un formulario
	 * @return List<ModelError>
	 *
	public List<ModelError> addProductReplenish(MEXMEProduct product,
			boolean isFormulyCheck) {
		
		// Listado de almacenes
		List<LabelValueBean> listaAlm = MEXMEProductClassWhs.getAllProdClass(
				getCtx(), product.getProductClass(), null);
		
		return addProductReplenish( listaAlm, isFormulyCheck, product.getM_Product_ID()) ;
	}
	
	
	/**
	 * Agregar un producto en replenish
	 * @param listaAlm Listado de almacenes
	 * @param isFormulyCheck True. es parte de un formulario
	 * @param product objeto del producto
	 * @return List<ModelError> lstErrors
	 *
	public List<ModelError> addProductReplenish(List<LabelValueBean> listaAlm,
			boolean isFormulyCheck, int productID) {
		List<ModelError> lstErrors = new ArrayList<ModelError>();
		Trx trx = null;
		try {

			// El almacen es obligatorio
			if (listaAlm == null || listaAlm.size() <= 0) {
				lstErrors.add(
						new ModelError(ModelError.TIPOERROR_Error,
								"msg.err.loteProd.nowarehouse"));
			}

			// producto obligatorio
			if (productID<= 0) {
				lstErrors.add(
						new ModelError(ModelError.TIPOERROR_Error,
								"error.noProducto"));
			}
			
			// si no hay errores
			if(lstErrors==null || lstErrors.size()<=0){
				trx = Trx.get(Trx.createTrxName("Rep"), Boolean.TRUE);
				
				// iteramos los almacenes
				for (int i = 0; i < listaAlm.size(); i++) {

					if(!addReplenish(Integer.parseInt(listaAlm
							.get(i).getValue()), isFormulyCheck, productID, trx.getTrxName())){
						lstErrors.add(new ModelError(ModelError.TIPOERROR_Error,
						"error.factDirecta.refreshAlmacenRel"));
						trx.rollback();
						break;
					}
				}// fin for
				
			
				if(lstErrors==null || lstErrors.size()<=0){
					trx.commit();
					lstErrors.add(
							new ModelError(ModelError.TIPOERROR_Informativo,
									"msj.informacionGuardada"));
				}
			}
		} catch (Exception e) {
			lstErrors.add(
					new ModelError(ModelError.TIPOERROR_Excepcion,
							"msj.error"));
			if (trx != null)
				trx.rollback();
		} finally {
			Trx.close(trx, true);
		}
		
		return lstErrors;
	}
	
	/**
	 * Crea registro en replenish
	 * @param warehouseID
	 * @param isFormulyCheck
	 * @param productID
	 * @param trxName
	 * @return
	 *
	public boolean addReplenish(int warehouseID, 
			boolean isFormulyCheck, int productID, String trxName) {
		MReplenish replenish = new MReplenish(getCtx(), 0, trxName);
		replenish.setLevel_Max(Env.ZERO);
		replenish.setLevel_Min(Env.ZERO);
		replenish.setReplenishType(MReplenish.REPLENISHTYPE_Manual);
		replenish.setM_Product_ID(productID);
		replenish.setM_Warehouse_ID(warehouseID);
		//Cambia a  EXME_ProductoOrg replenish.setIsFormulary(isFormulyCheck);
		if (!replenish.save(trxName)) {
			if(replenish.eErrorBD!=null && DB.isOracle() && replenish.eErrorBD!=null
					&& replenish.eErrorBD.getValue().indexOf("ORA-00001")>=0){
				;
			} else {
				return false;
			}
		}
		
		return true;
	}
	
	/**
	 * Relacion por clase de producto 
	 * @param productClass
	 * @param warehouseID
	 *
	public boolean addWarehouseReplenish(Properties ctx, String productClass, int warehouseID, String trxName){
		// busca todos los productos que existan en replenish y que tengan esa clase de producto
		List<LabelValueBean> lst = MEXMEReplenish.getReplenishProd(ctx,
				productClass,  trxName) ;
		
		// relacionar ese producto ese almacen en replenish		
		for (int i = 0; i < lst.size(); i++) {
			
			LabelValueBean lvb = lst.get(i);
			if(!addReplenish( warehouseID, lvb.getValue().equals("Y"), Integer.parseInt(lvb.getLabel()), trxName)) {
				return false;
			}
		}
		
		return true;
	}

	/**
	 * Listado de objetos de replenish por producto que tengan la clase de producto
	 * igual al parametro
	 * @param ctx: Contexto
	 * @param productClass: product Class
	 * @param trxName Nombre de transaccion
	 * @return List<LabelValueBean> Listado de IDs
	 *
	public static List<LabelValueBean> getReplenishProd(Properties ctx,
			String productClass, String trxName) {

		List<LabelValueBean> retValue = new ArrayList<LabelValueBean>();
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append(" SELECT  m_product.m_product_ID ,  M_Replenish.isformulary ");
		sql.append(" FROM M_Replenish ");
		sql.append(" INNER JOIN m_product ON M_Replenish.m_product_id = m_product.m_product_id ");
		sql.append(" WHERE m_product.ProductClass = ? ");

		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name));
		sql.append(" GROUP BY m_product.m_product_ID ,  M_Replenish.isformulary ");

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setString(1, productClass);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				retValue.add(new LabelValueBean( String.valueOf(rs.getInt(1)) ,rs.getString(2)));
			}

		} catch (Exception e) {
			s_log.log(Level.SEVERE, e.getMessage(), e);
		} finally {
			DB.close(rs, pstmt);
		}
		return retValue;
	}

	
	/**
	 * Relacion por clase de producto 
	 * @param productClass
	 * @param warehouseID
	 *
	public boolean delWarehouseReplenish(Properties ctx, String productClass, int warehouseID, String trxName){
		
		// busca todos los productos que existan en replenish y que tengan esa clase de producto
		List<MEXMEReplenish> lst = MEXMEReplenish.getWarehouse(ctx,
				warehouseID, productClass, trxName); 
		
		// relacionar ese producto ese almacen en replenish		
		for (int i = 0; i < lst.size(); i++) {
			lst.get(i).setIsActive(false);
			if(!lst.get(i).save(trxName)){
				return false;
			}
		}
		
		return true;
	}
	
	/**
	 * Almacenes especificos del producto
	 * 
	 * @param ctx
	 * @param warehouse
	 * @param productID
	 * @param trxName
	 * @return
	 *
	public static List<MEXMEReplenish> getWarehouse(Properties ctx,
			int warehouse, String productClass, String trxName) {

		List<MEXMEReplenish> retValue = new ArrayList<MEXMEReplenish>();
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append(" SELECT M_Replenish.* FROM M_Replenish   ");
		sql.append(" INNER JOIN M_Product ON M_Replenish.M_Product_ID = M_Product.M_Product_ID   ");
		sql.append(" WHERE M_Replenish.IsActive = 'Y'  AND M_Replenish.M_Warehouse_ID = ? ");
		sql.append(" AND M_Product.ProductClass = ? ");
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name));
		sql.append(" ORDER BY M_Replenish.created desc");

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, warehouse);
			pstmt.setString(2, productClass);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				retValue.add(new MEXMEReplenish(ctx, rs, trxName));
			}

		} catch (Exception e) {
			s_log.log(Level.SEVERE, e.getMessage(), e);
		} finally {
			DB.close(rs, pstmt);
		}
		return retValue;
	}
	
	
	/**
	 * Relacion entre almacenes para un producto y un tipo de almacen
	 * 
	 * @param ctx
	 *            Contexto
	 * @param M_Product_ID
	 *            Producto a buscar
	 * @param M_Warehouse_ID
	 *            Almacen que solicita a otros
	 * @param tipoAlmacen
	 *            Tipo de almacen
	 * @param trxName
	 *            Nombre de transaccion
	 * @return Listado de LabelValueBean con los almacenes que surten el
	 *         producto
	 * @throws Exception
	 *
	public static List<LabelValueBean> getProdAlmacen(Properties ctx,
			int M_Product_ID, String tipoAlmacen,
			String trxName) throws Exception {
		
		List<Object> params = new ArrayList<Object>();
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql
				.append(" SELECT  w.M_warehouse_id, w.Name ")
				.append(" FROM M_Replenish r ")
				.append(" inner join m_warehouse w  ON w.M_Warehouse_ID = r.M_Warehouse_ID ")
				.append(" WHERE r.IsActive = 'Y'  AND r.M_Product_id = ? ");
		params.add(M_Product_ID);

		if (tipoAlmacen != null) {
			sql.append(" AND w.TipoAlmacen = ?  ");
			params.add(tipoAlmacen);
		}
		sql
				.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name,
						"r"));
		sql.append(" GROUP BY w.M_warehouse_id, w.Name ");
		sql.append(" ORDER BY w.M_warehouse_id, w.Name ");

		return MEXMEReplenish.getConsultaLVB(ctx, sql.toString(), params, null);
	}*/
}
