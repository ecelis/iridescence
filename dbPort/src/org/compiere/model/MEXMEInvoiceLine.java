/*
 * Created on 02-mar-2005
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;

/**
 * La clase contendra aquellos métodos estaticos que hacen referencia a 
 * C_InvoiceLine
 */
public class MEXMEInvoiceLine {
	/** log */
	private static CLogger slog = CLogger.getCLogger(MEXMEInvoiceLine.class);

	/**
	 * Devuelve un arreglo con las facturas que aun no han sido procesadas por
	 * el cajero utilizada en cancelacion de prefacturas (init) y en la interfas
	 * en el metodo de crear DescuentosPorFamilia
	 * 
	 * @param ctx
	 * @param C_Invoice_ID
	 * @param cadena
	 * @param trxName
	 * @return
	 */
	public static List<MInvoiceLine> getLineas(Properties ctx,
			int C_Invoice_ID, String cadena, String trxName) {
		return MEXMEInvoiceLine.getLineas(ctx, C_Invoice_ID, trxName);
	}

	/**
	 * Devuelve un arreglo con las facturas que aun no han sido procesadas por
	 * el cajero utilizada en cancelacion de prefacturas (init) y en la interfas
	 * en el metodo de crear DescuentosPorFamilia
	 * 
	 * @param ctx
	 * @param C_Invoice_ID
	 * @param cadena
	 * @param trxName
	 * @return
	 */
	public static List<MInvoiceLine> getLineas(Properties ctx,
			int C_Invoice_ID, String trxName) {

		ArrayList<MInvoiceLine> lista = new ArrayList<MInvoiceLine>();

		if (ctx == null) {
			return null;
		}

		String sql = " SELECT C_InvoiceLine.* FROM C_InvoiceLine  "
				+ " WHERE C_InvoiceLine.IsActive = 'Y' "
				+ " AND C_InvoiceLine.C_Invoice_ID = ? ";

		sql = MEXMELookupInfo.addAccessLevelSQL(ctx, sql, "C_InvoiceLine");

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql, trxName);
			pstmt.setInt(1, C_Invoice_ID);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				lista.add(new MInvoiceLine(ctx, rs, trxName));
			}

		} catch (Exception e) {
			slog.log(Level.SEVERE, sql, e);
		} finally {
			DB.close(rs, pstmt);
		}
		return lista;
	}

	/**
	 * utilizado en el Calculo de coaseguro y deducible de facturacion directa
	 * 
	 * @param ctx
	 * @param orgTrx_ID
	 * @param cargos
	 * @param socio
	 * @return
	 */
	public static MInvoiceLine addLinea(final Properties ctx, 
			final MCtaPacDet cargos) {
		// Este es el objeto que se guardara en la base de datos
		final MInvoiceLine iline = new MInvoiceLine(ctx, 0, null);
		iline.setValuesCtaPacDet(cargos);
		iline.setAD_OrgTrx_ID(Env.getAD_OrgTrx_ID(ctx)); // Estacion de logueo
		iline.setDescription("Venta directa");
		// Lotes/Localizadores Card #1225
		iline.setM_AttributeSetInstance_ID(cargos.getM_AttributeSetInstance_ID());
//		iline.setM_Locator_ID(cargos.getM_Locator_ID()); ?? se ocupa ?? 
		return iline;
	}

	/**
	 * Impuestos agrupadas por tasa sin Coaseguro, Copago, Coaseguro médico, Deducible (CCCmD)
	 * @param ctx: Contexto
	 * @param invoiceId: Factura
	 * @param trxName: Nombre de transacción
	 * @return Impuestos de la factura por base gravable
	 */
	public static List<MInvoiceTax> getLinesGroupTax(final Properties ctx, final int invoiceId, final String trxName) {
		// Lineas
		final List<MInvoiceTax> list = new ArrayList<MInvoiceTax>();
		final MConfigPre configPre = MConfigPre.get(ctx, trxName);
		
		// Consulta
		final StringBuilder sql = new StringBuilder()
		.append(" SELECT il.c_tax_id, SUM(coalesce(il.taxamt,0)) as imp, ")
		.append(" SUM(coalesce(il.linenetamt,0)) as base")
		.append(" FROM C_InvoiceLine il ")
		.append(" WHERE il.IsActive = 'Y' ")
		.append(" AND   il.C_Invoice_ID = ? ")
		.append(" AND   il.M_Product_ID NOT IN ( ")
		.append(configPre.getCoaseguro_ID()).append(", ")
		.append(configPre.getDeducible_ID()).append(", ")
		.append(configPre.getCoaseguroMed_ID()).append(", ")
		.append(configPre.getCoPago_ID()).append(") ")
		.append(" GROUP BY il.c_tax_id ")
		.append(" ORDER BY il.c_tax_id ");

		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, invoiceId);

			rset = pstmt.executeQuery();
			while (rset.next()) {
				final MInvoiceTax mInvoiceTax = new MInvoiceTax(ctx, 0, trxName);
				mInvoiceTax.setC_Invoice_ID(invoiceId);
				mInvoiceTax.setTaxAmt(rset.getBigDecimal("imp"));
				mInvoiceTax.setTaxBaseAmt(rset.getBigDecimal("base"));
				mInvoiceTax.setC_Tax_ID(rset.getInt("c_tax_id"));
				list.add(mInvoiceTax);
			}
		} catch (Exception e) {
			slog.log(Level.SEVERE, "getLinesGroupTax : " + sql.toString(), e);
		} finally {
			DB.close(rset, pstmt);
		}

		return list;
	} // getLines
	
	/**
	 * Lineas de la factura que son Coaseguro, Copago, Coaseguro médico, Deducible
	 * @param ctx: Contexto
	 * @param invoiceId: Factura
	 * @param trxName: Nombre de transacción
	 * @return Lineas de la factura 
	 */
	public static List<MInvoiceLine> getLinesCCCmD(final Properties ctx, final int invoiceId, final String trxName) {
		final MConfigPre configPre = MConfigPre.get(ctx, trxName);
		final List<MInvoiceLine> invoiceLines = new ArrayList<MInvoiceLine>();
		
		// Sin CCCmD ni descuento
		final StringBuilder sql = new StringBuilder().append(" SELECT * ")
				.append(" FROM C_InvoiceLine ")
				.append(" WHERE IsActive = 'Y' AND C_Invoice_ID=? ")
				.append(" AND M_Product_ID IN ( ")
				.append(configPre.getCoaseguro_ID()).append(", ")
				.append(configPre.getDeducible_ID()).append(", ")
				.append(configPre.getCoaseguroMed_ID()).append(", ")
				.append(configPre.getCoPago_ID()).append(") ");

		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, invoiceId);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				invoiceLines.add(new MInvoiceLine(ctx, rset,trxName));
			}
		} catch (Exception e) {
			slog.log(Level.SEVERE, "setTaxBaseAmt", e);
		} finally {
			DB.close(rset, pstmt);
		}
		return invoiceLines;
	} 
}
