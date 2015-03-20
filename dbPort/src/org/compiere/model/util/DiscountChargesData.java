package org.compiere.model.util;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.apache.ecs.xhtml.big;
import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.SecureEngine;
import org.compiere.util.TimeUtil;

public class DiscountChargesData {
	/** Static Logger */
	private static CLogger s_log = CLogger.getCLogger(DiscountChargesData.class);
	DecimalFormat df = new DecimalFormat("0.00"); 

	/**
	 * 
	 * 
	 * @return
	 */
	
	public static List<DiscountChargesData> getDiscountChargesData(Properties ctx,Timestamp fechaIni, Timestamp fechaFin,String trxName) {
		List<DiscountChargesData> values = new ArrayList<DiscountChargesData>();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		try {
			sql.append("SELECT ");
			sql.append("  ep.Nombre_Pac ");
			sql.append("  AS nombreCuenta, ");
			sql.append("  ecp.DocumentNo ");
			sql.append("  AS cuentaPaciente, ");
			sql.append("  ci.Documentno ");
			sql.append("  AS notaRemision, ");
			sql.append("  civ.Documentno ");
			sql.append("  AS facturas, ");
			sql.append("  ci.totallines+ci.discountamt ");
			sql.append("  AS subTotal, ");
			sql.append("  ci.discountamt ");
			sql.append("  AS descuento, ");
			sql.append("  ci.totallines ");
			sql.append("  AS cargos, ");
			sql.append("  ci.grandtotal-ci.totallines ");
			sql.append("  AS impuesto, ");
			sql.append("  ci.grandtotal ");
			sql.append("  AS total ");
			sql.append("FROM ");
			sql.append("  exme_ctapac ecp ");
			sql.append("  INNER JOIN exme_paciente ");
			sql.append("  AS ep ");
			sql.append("  ON (ep.exme_paciente_id = ecp.exme_paciente_id) ");
			sql.append("  INNER JOIN exme_ctapacext ecpe ");
			sql.append("  ON (ecpe.exme_ctapac_id = ecp.exme_ctapac_id) ");
			sql.append("  INNER JOIN c_invoice ");
			sql.append("  AS ci ");
			sql.append("  ON (ecpe.c_invoice_id = ci.c_invoice_id) left ");
			sql.append("  JOIN c_invoice_customer_v ");
			sql.append("  AS civ ");
			sql.append("  ON (ci.c_invoice_id = civ.Ref_invoice_Sales_id) ");
			sql.append("WHERE ");
			sql.append("  ecpe.isactive = 'Y' AND ");
			sql.append("  ecp.ad_client_id = ? AND ");
			sql.append("  ecp.ad_org_id = ? AND ");
			sql.append("  ci.discountamt > 1 AND ");
			sql.append("  ci.dateinvoiced BETWEEN ");
			sql.append(" ? AND ");
			sql.append(" ? ORDER BY ");
			sql.append("  ci.c_invoice_id ");
			
			pstm = DB.prepareStatement(sql.toString(), trxName);
			
			pstm.setInt(1, Env.getAD_Client_ID(ctx));
			pstm.setInt(2, Env.getAD_Org_ID(ctx));
			if (fechaIni != null && fechaFin != null) {
				if (fechaIni.after(fechaFin)) {					
					pstm.setTimestamp(3, (Timestamp) TimeUtil.getFinalRange(ctx, fechaFin));
					pstm.setTimestamp(4, TimeUtil.getInitialRangeT(ctx, fechaIni));
				} else {
					pstm.setTimestamp(3,TimeUtil.getInitialRangeT(ctx, fechaIni));
					pstm.setTimestamp(4,(Timestamp) TimeUtil.getFinalRange(ctx, fechaFin));
				}
			}
			
			rs = pstm.executeQuery();
			
			while (rs.next()) {
				final DiscountChargesData rValues = new DiscountChargesData();
				rValues.setNombreCuenta(SecureEngine.decrypt(rs.getString("nombreCuenta")));
				rValues.setCuentaPaciente(rs.getString("cuentaPaciente"));
				rValues.setNotaRemision(rs.getString("notaRemision"));
				rValues.setFacturas(rs.getString("facturas"));
				rValues.setSubTotal(rs.getString("subTotal"));
				rValues.setDescuento(rs.getString("descuento"));
				rValues.setCargos(rs.getString("cargos"));
				rValues.setImpuesto(rs.getString("impuesto"));
				rValues.setTotal(rs.getString("total"));
				
				values.add(rValues);
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, pstm);
		}
		return values;
	}
	private String nombreCuenta;
	private String cuentaPaciente;
	private String notaRemision;
	private String facturas;
	private String cargos;
	private String total;
	private String descuento;
	private String subTotal;
	private String impuesto;

	public String getNombreCuenta() {
		return nombreCuenta;
	}
	public void setNombreCuenta(String nombreCuenta) {
		this.nombreCuenta = nombreCuenta;
	}
	public String getCuentaPaciente() {
		return cuentaPaciente;
	}
	public void setCuentaPaciente(String numeroCuenta) {
		cuentaPaciente = numeroCuenta;
	}
	public String getFacturas() {
		return facturas;
	}
	public void setFacturas(String facturas) {
		this.facturas = facturas;
	}
	public BigDecimal getCargos() {
		BigDecimal bdCargos  = new  BigDecimal(cargos);
		return bdCargos.setScale(2,BigDecimal.ROUND_UP);
	}
	public void setCargos(String cargos) {
		this.cargos = cargos;
	}
	public BigDecimal getTotal() {
		BigDecimal bdTotal  = new  BigDecimal(total);
		return bdTotal.setScale(2,BigDecimal.ROUND_UP);
	}
	public void setTotal(String total) {
		this.total = total;
	}
	public BigDecimal getDescuento() {		
		BigDecimal bdDesc  = new  BigDecimal(descuento);
		return bdDesc.setScale(2,BigDecimal.ROUND_UP);
	}
	public void setDescuento(String descuento) {
		this.descuento = descuento;
	}
	public String getNotaRemision() {
		return notaRemision;
	}
	public void setNotaRemision(String remision) {
		notaRemision = remision;
	}
	public BigDecimal getSubTotal() {
		BigDecimal bdsubTot  = new  BigDecimal(subTotal);
		return bdsubTot.setScale(2,BigDecimal.ROUND_UP);
	}
	public void setSubTotal(String subTotal) {
		this.subTotal = subTotal;
	}
	public BigDecimal getImpuesto() {
		BigDecimal bdImpuesto  = new  BigDecimal(impuesto);
		return bdImpuesto.setScale(2,BigDecimal.ROUND_UP);
	}
	public void setImpuesto(String impuesto) {
		this.impuesto = impuesto;
	}
	
	
}
