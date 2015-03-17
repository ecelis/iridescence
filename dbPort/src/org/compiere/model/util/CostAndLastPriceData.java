package org.compiere.model.util;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.Env;
/**
 * @author avillegas
 * 
 * 
 */

public class CostAndLastPriceData
 {

	/** Static Logger */
	private static CLogger s_log = CLogger.getCLogger(CostAndLastPriceData.class);

	/**
	 * 
	 * @param ctx
	 * @param warehouseId
	 * @param trxName
	 * @return
	 */
	public static List<CostAndLastPriceData> getProductCostData(Properties ctx, int warehouseId, String trxName) {
		List<CostAndLastPriceData> values = new ArrayList<CostAndLastPriceData>();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		try {
			sql.append("SELECT ");
			sql.append("  al.name ");
			sql.append("  AS almacen, ");
			sql.append("  ml.value ");
			sql.append("  AS localizacion, ");
			sql.append("  p.value ");
			sql.append("  AS codigo, ");
			sql.append("  p.name ");
			sql.append("  AS nombre, ");
			sql.append("  p.description ");
			sql.append("  AS descripcion, ");
			sql.append("  uom.name ");
			sql.append("  AS udm_minima, ");
			sql.append("  c.currentcostprice ");
			sql.append("  AS costo, ");
			sql.append("  po.pricelastpo ");
			sql.append("  AS ultimo_precio_compra ");
			sql.append("FROM ");
			sql.append("  m_storage s ");
			sql.append("  INNER JOIN m_locator ml ");
			sql.append("  ON (s.m_locator_id = ml.m_locator_id) ");
			sql.append("  INNER JOIN m_warehouse al ");
			sql.append("  ON (ml.m_warehouse_id = al.m_warehouse_id) ");
			sql.append("  INNER JOIN m_product p ");
			sql.append("  ON (s.m_product_id = p.m_product_id) ");
			sql.append("  INNER JOIN c_uom uom ");
			sql.append("  ON (p.c_uom_id = uom.c_uom_id) ");
			sql.append("  INNER JOIN m_cost c ");
			sql.append("  ON (p.m_product_id = c.m_product_id) ");
			sql.append("  INNER JOIN m_costelement ce ");
			sql.append("  ON (c.m_costelement_id = ce.m_costelement_id) left ");
			sql.append("  JOIN m_product_po po ");
			sql.append("  ON (p.m_product_id = po.m_product_id) ");
			sql.append("WHERE ");
			sql.append("  s.ad_client_id = ? AND ");
			sql.append("  c.ad_client_id = ? AND ");
			sql.append("  ce.costingmethod = 'S' AND ");
			sql.append("  al.ad_client_id = ? AND ");
			if (warehouseId > 0) {
				sql.append("  al.m_warehouse_id = ? AND ");
			}
			sql.append("  po.created = ");
			sql.append("  (SELECT ");
			sql.append("      MAX(created) ");
			sql.append("    FROM ");
			sql.append("      m_product_po ");
			sql.append("    WHERE ");
			sql.append("      m_product_id = p.m_product_id ");
			sql.append("  ) ");
			sql.append("ORDER BY ");
			sql.append("  al.name, ");
			sql.append("  p.value ");

			pstm = DB.prepareStatement(sql.toString(), trxName);

			pstm.setInt(1, Env.getAD_Client_ID(ctx));
			pstm.setInt(2, Env.getAD_Client_ID(ctx));
			pstm.setInt(3, Env.getAD_Client_ID(ctx));

			if(warehouseId > 0){
				pstm.setInt(4, warehouseId);
			}

			rs = pstm.executeQuery();
			while (rs.next()) {
				final CostAndLastPriceData rValues = new CostAndLastPriceData();

				rValues.setAlmacen(rs.getString("almacen"));
				rValues.setLocalizacion(rs.getString("localizacion"));
				rValues.setCodigo(rs.getString("codigo"));
				rValues.setNombre(rs.getString("nombre"));
				rValues.setDescripcion(rs.getString("descripcion"));
				rValues.setUdmMinima(rs.getString("udm_minima"));
				rValues.setCosto(rs.getString("costo"));
				rValues.setUltimoPrecio(rs.getString("ultimo_precio_compra"));
				values.add(rValues);
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, pstm);
		}
		return values;
	}

	private String almacen;
	private String codigo;
	private String costo;
	private String descripcion;
	private String localizacion;
	private String nombre;
	private String udmMinima;
	private String ultimoPrecio;

	public String getAlmacen() {
		return almacen;
	}
	public void setAlmacen(String almacen) {
		this.almacen = almacen;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public BigDecimal getCosto() {
		BigDecimal bdCosto  = new  BigDecimal(costo);
		return bdCosto;
	}
	public void setCosto(String costo) {
		this.costo = costo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getLocalizacion() {
		return localizacion;
	}
	public void setLocalizacion(String localizacion) {
		this.localizacion = localizacion;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getUdmMinima() {
		return udmMinima;
	}
	public void setUdmMinima(String udmMinima) {
		this.udmMinima = udmMinima;
	}
	public BigDecimal getUltimoPrecio() {
		BigDecimal bd  = new  BigDecimal(ultimoPrecio);
		return bd;
	}
	public void setUltimoPrecio(String ultimoPrecio) {
		this.ultimoPrecio = ultimoPrecio;
	}

}
