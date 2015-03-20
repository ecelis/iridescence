package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;

/**
 * Product Price
 * 
 */
public class MEXMEProductPrice extends MProductPrice {

	/** serialVersionUID. */
	private static final long serialVersionUID = -5191430616413025137L;
	/** Log */
	private static CLogger s_log = CLogger.getCLogger(MEXMEProductPrice.class);

	/**
	 * Persistency Constructor
	 * 
	 * @param ctx
	 *            context
	 * @param ignored
	 *            ignored
	 */
	public MEXMEProductPrice(Properties ctx, int ignored, String trxName) {
		super(ctx, 0, trxName);
		if (ignored != 0)
			throw new IllegalArgumentException("Multi-Key");
		setPriceLimit(Env.ZERO);
		setPriceList(Env.ZERO);
		setPriceStd(Env.ZERO);
	} // MProductPrice

	/**
	 * Load Constructor
	 * 
	 * @param ctx
	 *            context
	 * @param rs
	 *            result set
	 */
	public MEXMEProductPrice(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	} // MProductPrice

	/**
	 * Metodo para validar la lista de precios del producto, eruiz
	 * 
	 * @param ctx
	 * @param priceListID
	 * @param productID
	 * @return
	 */
	public static boolean validaPriceList(Properties ctx, int priceListID, int productID) {
		boolean valid = false;

		PreparedStatement psmt = null;
		ResultSet rs = null;

		StringBuilder sql = new StringBuilder("SELECT pl.M_PriceList_ID FROM M_ProductPrice ").append(" INNER JOIN M_PriceList_Version plv ").append(" ON (M_ProductPrice.M_PriceList_Version_ID ")
				.append(" = plv.M_PriceList_Version_ID) ").append(" INNER JOIN M_PriceList pl ").append(" ON (plv.M_PriceList_ID = pl.M_PriceList_ID) ")
				.append(" WHERE M_ProductPrice.IsActive = 'Y' ").append(" AND plv.isActive = 'Y' AND pl.IsActive = 'Y' ").append(" AND M_ProductPrice.M_Product_ID = " + productID);

		sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, sql.toString(), "M_ProductPrice"));

		try {
			psmt = DB.prepareStatement(sql.toString(), null);
			rs = psmt.executeQuery();

			if (rs.next()) {
				if (rs.getInt(1) == priceListID) {
					valid = true;
				}
			}

		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, psmt);
		}

		return valid;
	}

	/**
	 * Busca un precio para un determinado producto
	 * 
	 * @param ctx
	 *            Contexto
	 * @param where
	 *            Condicion de la consulta
	 * @param productID
	 *            ID del producto
	 * @param pricelist
	 *            Lista de precios
	 * @return
	 */
	public static List<MEXMEProductPrice> getFromProduct(Properties ctx, String where, int productID, int pricelist) {
		List<MEXMEProductPrice> ppr = new ArrayList<MEXMEProductPrice>();

		PreparedStatement psmt = null;
		ResultSet rs = null;

		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT pp.* FROM M_ProductPrice pp ").append(" INNER JOIN M_PriceList_Version pv ON pp.M_PriceList_Version_ID = pv.M_PriceList_Version_ID ")
				.append(" INNER JOIN M_PriceList pl ON pv.M_PriceList_ID = pl.M_PriceList_ID ").append(" WHERE pp.M_Product_ID = ? ");
		if (pricelist > 0)
			sql.append(" AND pv.M_PriceList_ID = ? ");
		sql.append(where);
		sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, sql.toString(), "M_ProductPrice", "pp"));
		sql.append(" ORDER BY pp.created desc ");

		try {
			psmt = DB.prepareStatement(sql.toString(), null);
			psmt.setInt(1, productID);
			if (pricelist > 0)
				psmt.setInt(2, pricelist);
			rs = psmt.executeQuery();

			while (rs.next()) {
				ppr.add(new MEXMEProductPrice(ctx, rs, null));
			}

		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, psmt);
		}

		return ppr;
	}

	/**
	 * Obtener precio del producto
	 * 
	 * @param ctx
	 *            Contexto
	 * @param productId
	 *            Producto
	 * @param versionId
	 *            Version de lista de precios
	 * @param trxName
	 * @return
	 */
	public static MEXMEProductPrice get(Properties ctx, int productId, int versionId, String trxName) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ");
		sql.append("  * ");
		sql.append("FROM ");
		sql.append("  m_productprice ");
		sql.append("WHERE ");
		sql.append("  m_product_id = ? AND ");
		sql.append("  m_pricelist_version_id = ? ");
		sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, sql.toString(), MEXMEProductPrice.Table_Name));
		MEXMEProductPrice price = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, productId);
			pstmt.setInt(2, versionId);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				price = new MEXMEProductPrice(ctx, rs, trxName);
			}

		} catch (Exception e) {
			s_log.log(Level.SEVERE, null, e);
		} finally {
			DB.close(rs, pstmt);
		}
		return price;
	}

	/**
	 * Nombre del producto
	 */
	private String productName = null;

	public String getProductName() {
		return this.productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	/**
	 * Metodo para validar la lista de precios del producto, eruiz
	 * 
	 * @param ctx
	 * @param priceListID
	 * @param productID
	 * @return
	 */
	public static List<MEXMEProductPrice> validaPriceList(Properties ctx, String sql, List<?> params) {
		List<MEXMEProductPrice> lst = new ArrayList<MEXMEProductPrice>();

		PreparedStatement psmt = null;
		ResultSet rs = null;

		try {
			psmt = DB.prepareStatement(sql.toString(), null);
			DB.setParameters(psmt, params);
			rs = psmt.executeQuery();

			while (rs.next()) {
				lst.add(new MEXMEProductPrice(ctx, rs, null));
			}

		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, psmt);
		}

		return lst;
	}

	/**
	 * product
	 */
	private MProduct product = null;

	/**
	 * product
	 * 
	 * @return X_M_Product
	 */
	public X_M_Product getProduct() {
		if (product == null && getM_Product_ID() > 0)
			product = new MProduct(getCtx(), getM_Product_ID(), null);
		return product;
	}

	/**
	 * Regresa una lista de ProductPrice
	 * 
	 * @param ctx
	 * @param params
	 * @return
	 */
	public static List<ProductPrice> getLstProductPrice(Properties ctx, Timestamp inicio, Timestamp fin, int productId, int categoryId) {
		List<ProductPrice> lst = new ArrayList<ProductPrice>();
		PreparedStatement psmt = null;
		ResultSet rs = null;
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT p.value AS CODIGO, p.name AS NOMBRE_PRODUCTO, mpc.name as category, bp.name AS PROVEEDOR, l.qtyordered as CANTIDAD, ")
				.append(" l.priceactual_vol as costoVOL, (l.priceactual_vol * (tax.rate/100)) as IVAVOL, (l.priceentered_vol + (l.priceentered_vol * (tax.rate/100))) as costoIVAVOL, ")
				.append(" u.name as UOM_EMPAQUE, l.priceactual as COSTO, um.name as UOM_MINIMA, ").append(" case  when (con.c_uom_to_id > 0) then con.dividerate ")
				.append(" when (con2.c_uom_to_id > 0) then con2.MultiplyRate ").append(" else 1 end as catEmp, ")
				.append(" o.documentno as ORDEN_COMPRA, to_char(o.created, 'dd/mm/yy') as FECHA_OC, l.line as LINEA, ").append(" fnc_getPrecioVenta(?, ?, p.m_product_id) as pVnt, ")
				.append(" (fnc_getPrecioVenta(?, ?, p.m_product_id) * (tax.rate/100)) as IVAVnt, ")
				.append(" (fnc_getPrecioVenta(?, ?, p.m_product_id) + (fnc_getPrecioVenta(?, ?, p.m_product_id) * (tax.rate/100))) as precIVAVnt ").append(" FROM c_order o ")
				.append(" INNER JOIN c_orderline l ON o.c_order_id = l.c_order_id ").append(" LEFT JOIN m_product p ON (l.m_product_id = p.m_product_id) ")
				.append(" LEFT JOIN c_bpartner bp ON (bp.c_bpartner_id = o.c_bpartner_id) ").append(" LEFT JOIN c_uom u ON (u.c_uom_id = l.c_uomvolume_id) ")
				.append(" LEFT JOIN c_uom um ON (um.c_uom_id = l.c_uom_id) ").append(" LEFT JOIN m_product_category mpc on (mpc.m_product_category_id = p.m_product_category_id) ")
				.append(" LEFT JOIN c_tax tax on (tax.c_tax_id = l.c_tax_id) ").append(" LEFT JOIN C_UOM_CONVERSION con  ON (con.C_Uom_ID=p.C_Uom_ID and con.C_Uom_To_ID =p.C_UomVolume_ID) ")
				.append(" LEFT JOIN C_UOM_CONVERSION con2 ON (con2.C_Uom_ID=p.C_UomVolume_ID and con2.C_Uom_To_ID =p.C_Uom_ID) ")
				.append(" WHERE o.ad_client_id = ? AND o.ad_org_id = ? AND o.docstatus = 'CO' ");
		if (inicio != null && fin != null) {
			sql.append(" AND o.created between ? AND ? ");
		}
		if (productId > 0) {
			sql.append(" AND p.m_product_id = ? ");
		}
		if (categoryId > 0) {
			sql.append(" AND p.m_product_category_id = ? ");
		}
		sql.append("ORDER BY documentno asc, l.line ");
		try {
			int i = 0;
			psmt = DB.prepareStatement(sql.toString(), null);
			psmt.setInt(++i, Env.getAD_Client_ID(ctx));
			psmt.setInt(++i, Env.getAD_Org_ID(ctx));
			psmt.setInt(++i, Env.getAD_Client_ID(ctx));
			psmt.setInt(++i, Env.getAD_Org_ID(ctx));
			psmt.setInt(++i, Env.getAD_Client_ID(ctx));
			psmt.setInt(++i, Env.getAD_Org_ID(ctx));
			psmt.setInt(++i, Env.getAD_Client_ID(ctx));
			psmt.setInt(++i, Env.getAD_Org_ID(ctx));
			psmt.setInt(++i, Env.getAD_Client_ID(ctx));
			psmt.setInt(++i, Env.getAD_Org_ID(ctx));
			if (inicio != null && fin != null) {
				psmt.setTimestamp(++i, inicio);
				psmt.setTimestamp(++i, fin);
			}
			if (productId > 0) {
				psmt.setInt(++i, productId);
			}
			if (categoryId > 0) {
				psmt.setInt(++i, categoryId);
			}
			rs = psmt.executeQuery();

			while (rs.next()) {
				ProductPrice prod = new ProductPrice();
				prod.setValue(rs.getString("CODIGO"));
				prod.setProducto(rs.getString("NOMBRE_PRODUCTO"));
				prod.setCategoria(rs.getString("category"));
				prod.setProveedr(rs.getString("PROVEEDOR"));
				prod.setCantidad(rs.getBigDecimal("CANTIDAD"));
				prod.setCostoPorEmp(rs.getBigDecimal("costoVOL"));
				prod.setIvaEmp(rs.getBigDecimal("IVAVOL"));
				prod.setCostoIVAEmp(rs.getBigDecimal("costoIVAVOL"));
				prod.setpVenta(rs.getBigDecimal("pVnt"));
				prod.setVntIVA(rs.getBigDecimal("IVAVnt"));
				prod.setPrecVntIVA(rs.getBigDecimal("precIVAVnt"));
				prod.setUomEmpaque(rs.getString("UOM_EMPAQUE"));
				prod.setCosto(rs.getBigDecimal("COSTO"));
				prod.setUomMinima(rs.getString("UOM_MINIMA"));
				prod.setCantidadPorEmp(rs.getBigDecimal("catEmp"));
				prod.setOrdenCompra(Integer.valueOf(rs.getInt("ORDEN_COMPRA")));
				prod.setFechaOC(rs.getString("FECHA_OC"));
				prod.setLinea(Integer.valueOf(rs.getInt("LINEA")));
				lst.add(prod);
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, psmt);
		}
		return lst;
	}

	/**
	 * Valida que los productos en consigna no se agregen en otra lista de precios, sin importar si la lista esta activa
	 * mprodId id del producto
	 * @return
	 */
	public static List<MProductPrice> validateProductSetPoint(int mprodId, boolean consigna) {
		StringBuilder join = new StringBuilder(" INNER JOIN m_pricelist_version plv ON plv.m_pricelist_version_id = m_productprice.m_pricelist_version_id ")
				.append(" INNER JOIN m_pricelist pl ON pl.m_pricelist_id = plv.m_pricelist_id and pl.issopricelist = 'N'")
				.append(" INNER JOIN exme_productoorg porg ON porg.m_product_id = M_ProductPrice.m_product_id  ");
		StringBuilder where = new StringBuilder("consigna = ? ");
		ArrayList<Object> lst = new ArrayList<Object>();
		lst.add(consigna ? "Y" : "N");
		if (mprodId > 0){
				where.append(" AND m_productprice.m_product_id = ? ");
				lst.add(mprodId);
		}

		//No filtar solo activos, 
			return new Query(Env.getCtx(), Table_Name, where.toString(), null)
			.setParameters(lst.toArray())
			.addAccessLevelSQL(true)
			.setJoins(join)
			.list();
	}
}
