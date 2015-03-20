/*
 * Created on 24/06/2005
 *
 */
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.Env;

/**
 * Modelo para la tabla MTax
 * 
 * <b>Fecha:</b> 24/Junio/2005
 * <p>
 * <b>Modificado: </b> $Author: taniap $
 * <p>
 * <b>En :</b> $Date: 2006/08/29 23:09:18 $
 * <p>
 * 
 * @author hassan reyes
 * @version $Revision: 1.5 $
 */
public class MEXMETax {
	//extends MTax {

//	/** serialVersionUID */
//	private static final long serialVersionUID = 9215298286892806125L;
	/** static logger */
	private static CLogger slog = CLogger.getCLogger(MEXMETax.class);
//	/**
//	 * constructor
//	 * 
//	 * @param ctx
//	 * @param C_Tax_ID
//	 * @param trxName
//	 */
//	public MEXMETax(final Properties ctx, final int C_Tax_ID,
//			final String trxName) {
//		super(ctx, C_Tax_ID, trxName);
//	}

//	/**
//	 * constructor
//	 * 
//	 * @param ctx
//	 * @param rs
//	 * @param trxName
//	 */
//	public MEXMETax(final Properties ctx, final ResultSet rSet,
//			final String trxName) {
//		super(ctx, rSet, trxName);
//	}
//
//	/**
//	 * constructor
//	 * 
//	 * @param ctx
//	 * @param Name
//	 * @param Rate
//	 * @param C_TaxCategory_ID
//	 * @param trxName
//	 */
//	public MEXMETax(final Properties ctx, final String Name,
//			final BigDecimal Rate, final int C_TaxCategory_ID,
//			final String trxName) {
//		super(ctx, Name, Rate, C_TaxCategory_ID, trxName);
//	}

	/**
	 * Retorna la primer coincidencia del impuesto que esta definido como
	 * Excento de Impuestos a nivel Org.
	 * 
	 * @param ctx
	 *            : contexto
	 * @param trxName
	 *            : Nombre de transaccion
	 * @return Id de la tasa de impuesto
	 * @throws Exception
	 */
	public static int getExemptTaxID(final Properties ctx, final String trxName) {
		// Tasa de impuesto que es exenta
		final MTax impuesto = MEXMETax.getExemptTax(ctx, trxName);
		return impuesto == null ? 0 : impuesto.getC_Tax_ID();
	}

	/**
	 * Retorna la primer coincidencia del impuesto que esta definido como
	 * Excento de Impuestos a nivel Org.
	 * 
	 * @param ctx
	 *            : contexto
	 * @param trxName
	 *            : Nombre de transaccion
	 * @return Obj. de la tasa de impuesto exenta
	 * @throws Exception
	 */
	public static MTax getExemptTax(final Properties ctx, final String trxName) {
		// Tasa de impuesto que es exenta
		final MTax impuesto = MEXMETax.impuesto(ctx, " AND IsTaxExempt='Y' ",
				trxName);
		return impuesto;
	}

	/**
	 * Retorna la primer coincidencia del impuesto que esta definido como por
	 * defecto de Impuestos a nivel Organizaci�n.
	 * 
	 * @param ctx
	 *            : contexto
	 * @param trxName
	 *            : Nombre de transaccion
	 * @return Id de la tasa de impuesto por defecto
	 * @throws Exception
	 */
	public static int getDefaultTaxID(final Properties ctx, final String trxName) {
		final MTax impuesto = MEXMETax.getDefaultTax(ctx, trxName);
		return impuesto == null ? 0 : impuesto.getC_Tax_ID();
	}

	/**
	 * Retorna la primer coincidencia del impuesto que esta definido como por
	 * defecto de Impuestos a nivel Organizaci�n.
	 * 
	 * @param ctx
	 *            : contexto
	 * @param trxName
	 *            : Nombre de transaccion
	 * @return la tasa de impuesto por defecto
	 * @throws Exception
	 */
	public static MTax getDefaultTax(final Properties ctx, final String trxName) {
		return MEXMETax.impuesto(ctx, " AND IsDefault = 'Y' ", trxName);
	}

	/**
	 * Retorna la primer coincidencia del impuesto que esta definido como
	 * tasa cero de Impuestos a nivel Organizaci�n.
	 * 
	 * @param ctx
	 *            : contexto
	 * @param trxName
	 *            : Nombre de transaccion
	 * @return la tasa de impuesto por defecto
	 * @throws Exception
	 */
	public static MTax getZeroTax(final Properties ctx, final String trxName) {
		return MEXMETax.impuesto(ctx, "  AND IsTaxExempt='N' AND Rate = 0 ", trxName);
	}
	
	/**
	 * Tasa de impuesto de un producto
	 * 
	 * @param ctx
	 *            : contexto
	 * @param product_ID
	 *            : Producto a buscar tasa de impuesto
	 * @param trxName
	 *            : Nombre de transaccion
	 * @return obj la tasa de impuesto del producto
	 * @throws Exception
	 */
	public static MTax getImpuestoProducto(final Properties ctx, final int product_ID,
			final String trxName)  {
		return MEXMETax.getImpuestoProducto(ctx, product_ID, trxName, Env.ZERO);
	}

	/**
	 * Tasa de impuesto de un producto
	 * 
	 * @param ctx
	 *            : contexto
	 * @param product_ID
	 *            : Producto a buscar tasa de impuesto
	 * @param trxName
	 *            : Nombre de transaccion
	 * @param monto
	 *            : monto a calcular el impuesto
	 * @return obj la tasa de impuesto del producto
	 * @throws Exception
	 */
	public static MTax getImpuestoProducto(final Properties ctx, final int product_ID,
			final String trxName, final BigDecimal monto) {

		MTax tax = null;
		try {
			tax = MEXMETax.impuestoProducto(ctx, product_ID, 0,
					" AND C_Tax.SOPOType IN ('" + X_C_Tax.SOPOTYPE_All + "', '"
							+ X_C_Tax.SOPOTYPE_SalesByExtensionTax + "') ", trxName);

			if (tax != null && monto != null && monto.compareTo(Env.ZERO) != 0) {
				tax.setAmount(tax.calculateTax(monto, false, 6));
			} else {
				if(tax==null){
					slog.severe("No tax product: "+product_ID+" AD_Client_ID: "+Env.getAD_Client_ID(ctx));
				}
			}

		} catch (Exception e) {
			slog.log(Level.SEVERE, "getImpuestoProducto", e.getMessage());
		}

		return tax;
	}

	/**
	 * Obtenemos el impuesto del producto
	 * 
	 * @param ctx
	 *            : contexto
	 * @param charge_ID
	 *            : Cargo a buscar tasa de impuesto
	 * @param trxName
	 *            : Nombre de transaccion
	 * @return obj la tasa de impuesto del producto
	 * @throws Exception
	 */
	public static MTax getImpuestoCargo(final Properties ctx, final int charge_ID,
			final String trxName) {
		return MEXMETax.getImpuestoCargo(ctx, charge_ID, trxName, Env.ZERO);
	}

	/**
	 * Obtenemos el impuesto del producto
	 * 
	 * @param ctx
	 *            : contexto
	 * @param charge_ID
	 *            : Cargo a buscar tasa de impuesto
	 * @param trxName
	 *            : Nombre de transaccion
	 * @param monto
	 *            : monto a calcular el impuesto
	 * @return obj la tasa de impuesto del producto
	 * @throws Exception
	 */
	public static MTax getImpuestoCargo(final Properties ctx, final int charge_ID,
			final String trxName, final BigDecimal monto) {

		MTax tax = null;

		try {
			tax = MEXMETax.impuestoCargo(ctx, charge_ID, 0,
					" AND C_Tax.SOPOType IN ('" + X_C_Tax.SOPOTYPE_All + "', '"
							+ X_C_Tax.SOPOTYPE_SalesByExtensionTax + "') ", trxName);


			if (tax != null && monto != null && monto.compareTo(Env.ZERO) != 0) {
				tax.setAmount(tax.calculateTax(monto, false, 6));
			}

		} catch (Exception e) {
			slog.log(Level.SEVERE, "getImpuestoProducto", e.getMessage());
		}

		return tax;
	}

//	/********************************************************************************/
//	/**
//	 * Monto del impuesto calculado
//	 */
//	private BigDecimal amount = Env.ZERO;
//
//	public BigDecimal getAmount() {
//		return amount;
//	}
//
//	public void setAmount(final BigDecimal amount) {
//		this.amount = amount;
//	}

	/********************************************************************************/
//	/**
//	 * REFACTURACION
//	 * 
//	 * @param ctx
//	 * @param EXME_CtaPacExt_ID
//	 * @param trxName
//	 * @return
//	 * @throws Exception
//	 */
//	public static String extension(final Properties ctx, final int EXME_CtaPacExt_ID,
//			final String trxName) throws Exception {
//		String error = null;
//
//		// Extension
//		final MEXMECtaPacExt extension = new MEXMECtaPacExt(ctx, EXME_CtaPacExt_ID,
//				trxName);
//
//		// Las lineas de la extension
//		final MCtaPacDet[] lstExtension = extension
//				.getCargosDeLaExtension(MEXMECtaPacExt.condicionCargos ());
//
//		// Barremos las lineas
//		for (int i = 0; i < lstExtension.length; i++) {
//			final MCtaPacDet cargo = (MCtaPacDet) lstExtension[i];
//
//			MTax mTax = null;
//			// A las lineas de coaseguro y deducible no se les calcula el
//			// impuesto relacionado
//			if (!((cargo.getTipoLinea().equalsIgnoreCase(
//					MCtaPacDet.TIPOLINEA_Coaseguro)
//					|| cargo.getTipoLinea().equalsIgnoreCase(
//							MCtaPacDet.TIPOLINEA_Deducible)
//					|| cargo.getTipoLinea().equalsIgnoreCase(
//							MCtaPacDet.TIPOLINEA_CoaseguroMedico) || cargo
//					.getTipoLinea().equalsIgnoreCase(
//							MCtaPacDet.TIPOLINEA_Copago)) && cargo
//					.getLineNetAmt().compareTo(Env.ZERO) <= 0)) {
//
//				if (cargo.getM_Product_ID() > 0) {
//					mTax = MEXMETax.getImpuestoProducto(ctx,
//							cargo.getM_Product_ID(), trxName,
//							cargo.getLineNetAmt());
//				} else {
//					mTax = MEXMETax.getImpuestoCargo(ctx,
//							cargo.getC_Charge_ID(), trxName,
//							cargo.getLineNetAmt());
//				}
//				BigDecimal taxAmt = Env.ZERO;
//				if (mTax != null) {
//					cargo.setC_Tax_ID(mTax.getC_Tax_ID());
//					taxAmt = mTax.getAmount();
//				}
//
//				cargo.setTaxAmt(taxAmt);
//
//				if (!cargo.save(trxName)) {
//					error = "No se logro recalcular el impuesto";
//					break;
//				}
//			}
//		}
//
//		extension.cargarLineasDeExtension(true, false);
//		if (!extension.calcularTotales(trxName)) {
//			error = "No se logro recalcular el impuesto";
//		}
//
//		if (!extension.save(trxName)) {
//			error = "No se logro recalcular el impuesto";
//		}
//		return error;
//	}

	/**
	 * REFACTURACION
	 * 
	 * @param ctx
	 * @param EXME_CtaPacExt_ID
	 * @param trxName
	 * @return
	 * @throws Exception
	 */
//	public static String factura(final MEXMEInvoice factura,
//			final BigDecimal grandTotalOrig) throws Exception {
//		String error = null;
//
//		// Las lineas de la extension
//		final MInvoiceLine[] lines = factura.getLines(true, false);
//
//		BigDecimal totalAmt = Env.ZERO; // lhernandez. para obtener el monto
//										// total.
//		BigDecimal totalAmtLine = Env.ZERO; // lhernandez. para obtener el monto
//											// por linea.
//		BigDecimal totalAmtTax = Env.ZERO;
//
//		final MConfigPre configPre = MConfigPre.get(factura.getCtx(), null);
//		boolean requierePagos = false;
//
//		// Barremos las lineas
//		for (int i = 0; i < lines.length; i++) {
//			final MInvoiceLine cargo = (MInvoiceLine) lines[i];
//
//			MEXMETax mTax = null;
//
//			if (cargo.getM_Product_ID() == 0
//					|| (cargo.getM_Product_ID() > 0
//							&& cargo.getLineNetAmt().compareTo(Env.ZERO) > 0
//							&& configPre.getDeducible_ID() != cargo
//									.getM_Product_ID()
//							&& configPre.getCoaseguro_ID() != cargo
//									.getM_Product_ID()
//							&& configPre.getCoaseguroMed_ID() != cargo
//									.getM_Product_ID() && configPre
//							.getCoPago_ID() != cargo.getM_Product_ID())) {
//
//				if (cargo.getM_Product_ID() > 0) {
//					mTax = MEXMETax.getImpuestoProducto(factura.getCtx(),
//							cargo.getM_Product_ID(), factura.get_TrxName(),
//							cargo.getLineNetAmt());
//				} else {// Los impuestos de los cargos deber�n ser exentos
//					mTax = MEXMETax.getImpuestoCargo(factura.getCtx(),
//							cargo.getC_Charge_ID(), factura.get_TrxName(),
//							cargo.getLineNetAmt());
//				}
//
//				if (mTax != null && cargo.getC_Tax_ID() != mTax.getC_Tax_ID()) {
//
//					cargo.setC_Tax_ID(mTax.getC_Tax_ID());
//					cargo.setTaxAmt(mTax.getAmount());
//
//					totalAmtLine = totalAmtLine.add(cargo.getLineNetAmt());// lhernandez.
//					// sumar
//					// los
//					// totales
//																			// sin
//																			// impuesto.
//					totalAmtTax = totalAmtTax.add(mTax.getAmount());
//					if (cargo.getM_Product_ID() > 0) {
//						cargo.setLineTotalAmt(cargo.getLineNetAmt().add(
//								cargo.getTaxAmt()));
//					}
//					if (!cargo.save(factura.get_TrxName())) {
//						error = "No se logro recalcular el impuesto";
//						return error;
//					}
//
//					requierePagos = true;
//				}
//			}
//		}
//
//		// Importe mas impuesto
//		totalAmt = totalAmtLine.add(totalAmtTax); // lhernandez. sumar el nuevo
//													// impuesto.
//
//		// Comparamos el total original por el total de la factura con el nuevo
//		// impuesto
//		if (requierePagos) {
//			if (totalAmt.compareTo(grandTotalOrig) != 0) {
//				factura.setAfecta_Caja(false);
//				factura.setProcessed(false);
//			}
//
//			if (!factura.save(factura.get_TrxName())) {
//				error = "No se logro recalcular el impuesto";
//			}
//		}
//		return error;
//	}

	/********************************************************************************/

	/**
	 * 
	 * @param ctx
	 * @param where
	 * @param fecha
	 * @param trxName
	 * @return
	 * @throws Exception
	 */
	public static MTax impuesto(final Properties ctx, final String where,
			final String trxName) {

		MTax tax = null;

		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY)
		.append(" SELECT * FROM C_Tax WHERE IsActive = 'Y' AND ad_client_id = ? ")
		.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", X_C_Tax.Table_Name))
		.append(sqlValidDate())
		.append(" AND IsDocumentLevel = 'Y'  ")
		.append(where != null?where:"")
		.append(" ORDER BY ValidFrom DESC, IsDefault DESC ");

		PreparedStatement pstmt = null;
		ResultSet rSet = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, Env.getAD_Client_ID(ctx));
			rSet = pstmt.executeQuery();
			
			// Primer valor
			if (rSet.next()){
				tax = new MTax(ctx, rSet, trxName);
			}
			
		} catch (Exception e) {
			slog.log(Level.SEVERE, sql.toString(), e.getMessage());
		} finally {
			DB.close(rSet, pstmt);
		}

		return tax;

	}

	/**
	 * Filtro de fechas para la tasa de impuesto
	 * @return
	 */
	
	public static String sqlValidDate() {
		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		if (DB.isOracle()) {
			sql.append(" AND TRUNC(C_Tax.ValidFrom) <= TRUNC("
					+ DB.TO_DATE(DB.getTimestampForOrg(Env.getCtx())) + ") ");
		} else if (DB.isPostgreSQL()) {
			sql.append(" AND DATE_TRUNC('day', C_Tax.ValidFrom) <= DATE_TRUNC('day', "
					+ DB.TO_DATE(DB.getTimestampForOrg(Env.getCtx())) + ") ");
		}
		
		return sql.toString();
	}
	
	/**
	 * Obtenemos el impuesto del producto La selección de la tasa de impuesto:
	 * 1. En el catalogo de producto dice la categoría de impuesto 2. En la
	 * opción tasa impuesto se buscan todas aquellas que sean de esa categoría
	 * de impuesto (paso 1) 3. La configuración de la tasa deberá decir que el
	 * tipo debe ser de venta o ambos 4. La configuración de la tasa deberá
	 * decir que es vigente por la fecha. 3. Si hay mas una tasa de búsqueda con
	 * todos los criterios anteriores busca de preferencia las que tengan que
	 * son por defecto, si por error hay mas de un tasa de impuesto seleccionada
	 * por defecto se toma la primera de acuerdo a la fecha de vigencia (de la
	 * mas actual a la mas vieja) y si hubiera mas de una con la misma fecha
	 * tomara la que este por defecto, en caso contrario toma una
	 * arbitrariamente.
	 * 
	 * @param producto
	 *            El identificador del producto
	 * @return El impuesto del producto
	 * @exception Exception
	 *                Description of the Exception
	 */
	private static MTax impuestoProducto(final Properties ctx, final int product_ID,
			final int categoria_ID, final String where, final String trxName) {

		MTax impuesto = null;
		final boolean filtrarPorOrg = MCountry.isUSA(ctx)?false:MProduct.isValidProductOrg(ctx, product_ID, false)==null;
		
		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		if(filtrarPorOrg) {
			sql.append(" SELECT C_Tax.*, product.AD_Client_ID FROM Exme_Productoorg  product")
			//.append(" LEFT  JOIN M_Product_Cte cte ON product.M_Product_ID = cte.M_Product_ID AND cte.AD_Client_ID = ? ")
			.append(" INNER JOIN C_TaxCategory     ON product.C_TaxCategory_ID = C_TaxCategory.C_TaxCategory_ID ")
			.append("                                 AND C_TaxCategory.IsActive = 'Y'                        ")
			.append(" INNER JOIN C_Tax             ON C_TaxCategory.C_TaxCategory_ID = C_Tax.C_TaxCategory_ID ")
			.append("                                 AND C_Tax.IsActive = 'Y'                                ")
			.append("                                 AND C_Tax.AD_Client_ID = ? ")
			.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", X_C_Tax.Table_Name))
			
			.append(" WHERE product.IsActive = 'Y' AND C_Tax.AD_Client_ID = ?                    ")
			.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", "EXME_ProductoOrg", "product"))
			.append(sqlValidDate())
			.append(" AND C_Tax.IsDocumentLevel = 'Y'                      ")
			.append(where != null ? where : "")
			.append(product_ID > 0?" AND product.M_Product_ID = ? ":" AND C_TaxCategory.C_TaxCategory_ID = ? ")
			.append(" ORDER BY product.AD_Client_ID DESC, C_Tax.ValidFrom DESC, C_Tax.IsDefault DESC  ");
		} else {
			sql.append(" SELECT C_Tax.*, po.AD_Client_ID ")
			.append(" FROM  M_Product         product     ")
			.append(" LEFT  JOIN Exme_Productoorg  po ON  product.M_Product_ID = po.M_Product_ID  AND po.AD_Org_ID = ?     AND po.IsActive = 'Y'  ")
			.append(" LEFT  JOIN M_Product_Cte    cte ON  product.M_Product_ID = cte.M_Product_ID AND cte.AD_Client_ID = ? AND cte.IsActive = 'Y' ")
			.append(" INNER JOIN C_TaxCategory        ON  COALESCE ( COALESCE (po.C_TaxCategory_ID, cte.C_TaxCategory_ID), product.C_TaxCategory_ID) = C_TaxCategory.C_TaxCategory_ID ")
			.append("                                 AND C_TaxCategory.IsActive = 'Y'                            ")
			.append(" INNER JOIN C_Tax                ON  C_TaxCategory.C_TaxCategory_ID = C_Tax.C_TaxCategory_ID ")
			.append("                                 AND C_Tax.IsActive = 'Y'                                    ")
			
			.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", X_C_Tax.Table_Name))
			
			.append(" WHERE product.IsActive = 'Y' AND C_Tax.AD_Client_ID = ?                       ")
			.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", X_M_Product.Table_Name, "product"))
			.append(sqlValidDate())
			.append(" AND   C_Tax.IsDocumentLevel = 'Y'                      ")
			.append(where != null ? where : "")
			.append(product_ID > 0?" AND product.M_Product_ID = ? ":" AND C_TaxCategory.C_TaxCategory_ID = ? ")
			.append(" ORDER BY po.AD_Client_ID DESC, C_Tax.ValidFrom DESC, C_Tax.IsDefault DESC  ");
		}
		
		
		PreparedStatement pstmt = null;
		ResultSet rSet = null;
		
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			if(filtrarPorOrg) {
				pstmt.setInt(1, Env.getAD_Client_ID(ctx));
				pstmt.setInt(2, Env.getAD_Client_ID(ctx));
				pstmt.setInt(3, product_ID > 0?product_ID:categoria_ID);
			} else {
				pstmt.setInt(1, Env.getAD_Org_ID(ctx));
				pstmt.setInt(2, Env.getAD_Client_ID(ctx));
				pstmt.setInt(3, Env.getAD_Client_ID(ctx));
				pstmt.setInt(4, product_ID > 0?product_ID:categoria_ID);
			}
			rSet = pstmt.executeQuery();

			if (rSet.next()) {
				impuesto = new MTax(ctx, rSet, trxName);
			}

		} catch (Exception e) {
			slog.log(Level.SEVERE, sql.toString(), e.getMessage());
		} finally {
			DB.close(rSet, pstmt);
		}

		return impuesto;
	}

	/**
	 * Obtenemos el impuesto del producto
	 * 
	 * @param producto
	 *            El identificador del producto
	 * @return El impuesto del producto
	 * @exception Exception
	 *                Description of the Exception
	 */
	private static MTax impuestoCargo(final Properties ctx, final int charge_ID,
			final int categoria_ID, final String where, final String trxName) {

		MTax impuesto = null;

		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);

		sql.append(" SELECT C_Tax.* FROM C_Charge             ")
		.append(" INNER JOIN C_TaxCategory                    ")
		.append("            ON  C_Charge.C_TaxCategory_ID = C_TaxCategory.C_TaxCategory_ID ")
		.append("            AND C_TaxCategory.IsActive = 'Y' ")
		.append(" INNER JOIN C_Tax                            ")
		.append("            ON  C_TaxCategory.C_TaxCategory_ID = C_Tax.C_TaxCategory_ID ")
		.append("            AND C_Tax.IsActive = 'Y'         ")
		
		.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", X_C_Tax.Table_Name))
		
		.append(" WHERE C_Charge.IsActive = 'Y' AND C_Tax.AD_Client_ID = ?                ")
		.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", "C_Charge"))
		.append(sqlValidDate())
		.append(" AND C_Tax.IsDocumentLevel = 'Y'          ")
		.append(where != null ? where : "")
		.append(charge_ID > 0?" AND C_Charge.C_Charge_ID = ? ":" AND C_TaxCategory.C_TaxCategory_ID = ? ")
		.append(" ORDER BY C_Tax.ValidFrom DESC, C_Tax.IsDefault DESC ");

		PreparedStatement pstmt = null;
		ResultSet rSet = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, Env.getAD_Client_ID(ctx));
			pstmt.setInt(2, charge_ID > 0?charge_ID:categoria_ID);
			rSet = pstmt.executeQuery();

			if (rSet.next()) {
				impuesto = new MTax(ctx, rSet, trxName);
			}

		} catch (Exception e) {
			slog.log(Level.SEVERE, sql.toString(), e.getMessage());
		} finally {
			DB.close(rSet, pstmt);
		}

		return impuesto;
	}
	
	/**
	 * Metodo para obtener el impuesta en base a la cateogoria de impuesto y su SOPOType.
	 * @param ctx
	 * @param trxName
	 * @return
	 */
	public static MTax getTax(Properties ctx, int CategoryID, String SOPOType, String trxName) {		
		MTax tax =  null;
		
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT * FROM C_Tax WHERE IsActive = 'Y' and C_TaxCategory_ID = ? and SOPOType = ? AND AD_Client_ID = ? ");	
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", "C_Tax"));		
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try
		{	
			pstmt = DB.prepareStatement (sql.toString(), trxName);
			pstmt.setInt(1, CategoryID);
			pstmt.setString(2, SOPOType);
			pstmt.setInt(3, Env.getAD_Client_ID(ctx));
			
			rs = pstmt.executeQuery ();
			
			//Se retorna el primero encontrado
			if(rs.next()){
				tax = new MTax(ctx, rs, null);
			}
			
		} catch (Exception e) {
			slog.log(Level.SEVERE, sql.toString(), e.getMessage());
			sql = null;
		}finally {
			DB.close(rs, pstmt);
		}
		
		return tax;
	}
}