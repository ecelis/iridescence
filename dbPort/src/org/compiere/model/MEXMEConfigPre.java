/**
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
 * Clase para configuracion de precios.
 * 
 * @author Hassan Reyes
 */
public class MEXMEConfigPre extends X_EXME_ConfigPre {

	/**
	 * Serial Version
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Costos usados para el calculo de listas de precios y precios de venta.
	 * (M_Product_Costing).
	 */
	public static String PRICELIST_COST = "PLC";
	public static String PRICEACTUAL_COST = "PAC";
	private static CLogger s_log = CLogger.getCLogger(MEXMEConfigPre.class);

	// /**
	// * Lista de precios elegida en el proceso de entradas a almacen via web,
	// eruiz
	// */
	// public static int M_PriceList_ID = 0;

	// /**
	// * Mensaje de error en caso de que el producto no tenga configurado el
	// factor precio, eruiz
	// */
	// public static String errorFactorPre = "";
	/** Lista de precios de configuracion */
	private MEXMEPriceList lista = null;

	/**
	 * @param ctx
	 * @param ID
	 * @param trxName
	 */
	public MEXMEConfigPre(Properties ctx, int ID, String trxName) {
		super(ctx, ID, trxName);
	}

	/**
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MEXMEConfigPre(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	/**
	 * Obtenemos la configuracion de precios de un hospital en particular.
	 * (Cliente + Organizacion).
	 * 
	 * @param ctx
	 * @return
	 */
	public static MEXMEConfigPre get(Properties ctx, String trxName) {
		return MConfigPre.get(ctx, trxName);
	}

	/**
	 * Obtenemos la Lista de Precios de Venta para la Empresa + Organizaci�n. Si
	 * la organizacion no cuenta con Lista de Precios propia, buscara a nivel de
	 * Empresa.
	 * 
	 * @param ctx
	 * @return M_PriceList_ID
	 *
	public static int getPriceListId(Properties ctx) {
		int idList = Env.getM_PriceListSO_ID(ctx);
		if (idList == 0) {
			final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
			sql.append(" SELECT M_PriceList.M_PriceList_ID FROM EXME_ConfigPre  ");
			sql.append(" INNER JOIN M_PriceList ON (M_PriceList.M_PriceList_ID=EXME_ConfigPre.M_PriceList_ID) ");
			sql.append(" WHERE EXME_ConfigPre.IsActive='Y' AND M_PriceList.IsActive='Y' ");
			sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name));
			sql.append(" ORDER BY EXME_ConfigPre.AD_Org_ID DESC ");

			idList = DB.getSQLValue(null, sql.toString());
		}
		return idList;
	}*/

	/**
	 * Obtenemos la Lista de Precios de Venta para la Empresa + Organizaci�n. Si
	 * la organizacion no cuenta con Lista de Precios propia, buscara a nivel de
	 * Empresa.
	 * 
	 * @param ctx
	 * @return
	 */
	public static MPriceList getPriceList(Properties ctx, String trxName) {
		MPriceList priceList = null;
		int idList = Env.getM_PriceListSO_ID(ctx);
		if (idList == 0) {
			final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
			sql.append(" SELECT M_PriceList.* FROM EXME_ConfigPre  ");
			sql.append(" INNER JOIN M_PriceList ON (M_PriceList.M_PriceList_ID=EXME_ConfigPre.M_PriceList_ID) ");
			sql.append(" WHERE EXME_ConfigPre.IsActive='Y' AND M_PriceList.IsActive='Y' ");
			sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name));
			sql.append(" ORDER BY EXME_ConfigPre.AD_Org_ID DESC ");
			
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {
				pstmt = DB.prepareStatement(sql.toString(), trxName);
				rs = pstmt.executeQuery();

				if (rs.next()) {
					priceList = new MPriceList(ctx, rs, trxName);
				}

			} catch (Exception e) {
				s_log.log(Level.SEVERE, "get - closing", e);
			} finally {
				DB.close(rs, pstmt);
				rs = null;
				pstmt = null;
			}

		} else {
			priceList = new MPriceList(ctx, idList, trxName);
		}
		return priceList;
	}
	
	/**
	 * Obtenemos la Lista de Precios de Venta para la Empresa + Organizaci�n. Si
	 * la organizacion no cuenta con Lista de Precios propia, buscara a nivel de
	 * Empresa.
	 * 
	 * @param ctx
	 * @return
	 */
	public static MPriceList getPriceList(final Properties ctx, final int ad_org_id) {
		MPriceList priceList = null;
			final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
			sql.append(" SELECT M_PriceList.* FROM EXME_ConfigPre  ");
			sql.append(" INNER JOIN M_PriceList ON (M_PriceList.M_PriceList_ID=EXME_ConfigPre.M_PriceList_ID) ");
			sql.append(" WHERE EXME_ConfigPre.IsActive='Y' AND M_PriceList.IsActive='Y' ");
			sql.append(" AND EXME_ConfigPre.AD_Client_ID=? AND EXME_ConfigPre.AD_Org_ID IN (0,?) ");
			sql.append(" ORDER BY EXME_ConfigPre.AD_Org_ID DESC");
			
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {
				pstmt = DB.prepareStatement(sql.toString(), null);
				pstmt.setInt(1, Env.getAD_Client_ID(ctx));
				pstmt.setInt(2, ad_org_id);
				rs = pstmt.executeQuery();

				if (rs.next()) {
					priceList = new MPriceList(ctx, rs, null);
				}

			} catch (Exception e) {
				s_log.log(Level.SEVERE, "get - closing", e);
			} finally {
				DB.close(rs, pstmt);
			}

		return priceList;
	}
	
	

	// /**
	// * Calculamos la Lista de Precios de Venta a partir de
	// * una Linea de Orden de Compra.
	// * @param ol Linea de Orden de Compra.
	// * @return
	// */
	// public boolean calculateSalesPL(MOrderLine ol){
	// boolean success = true;
	//
	// if (ol == null) {
	// return false;
	// }
	//
	// log.log(Level.FINEST,"Calculando Lista de Precios de Venta para la Linea de Orden de Compra [OrderLine] - "
	// + ol);
	// success = calculateSalesPL(ol.getM_Product_ID(), ol.getPriceActual(),
	// ol.getPriceList());
	//
	// return success;
	// }

	// /**
	// * Calculo de Precios de Venta.
	// * @param M_Product_ID
	// * @param priceActual Precio con Descuentos por unidad de almacenamiento.
	// * @param priceList Precio Sin Descuentos por unidad de almacenamiento.
	// * @return
	// */
	// public boolean calculateSalesPL(int M_Product_ID, BigDecimal priceActual,
	// BigDecimal priceList){
	//
	// boolean success = true;
	//
	// if (M_Product_ID == 0 || (priceActual == null && priceList == null))
	// return false;
	//
	// log.log(Level.FINEST,
	// "Calculando Lista de Precios de Venta para [Producto] - " + M_Product_ID
	// + ", [priceActual] - " + priceActual + ", [PriceList] - " + priceList);
	//
	// MProduct product = MProduct.get(getCtx(), M_Product_ID);
	//
	// //Con Descuentos o Sin Descuentos?
	// BigDecimal price = (isIncluirDesctos() ? priceActual : priceList);
	// //BigDecimal nivelSuperior = Env.ZERO;
	// // BigDecimal porcentaje = Env.ZERO;
	// BigDecimal newPrice = Env.ZERO;
	// int acctSchema = Env.getContextAsInt(getCtx(), "$C_AcctSchema_ID");
	//
	// // Verificamos si se calculara en automatico la Lista de Precios de Venta
	// /* if(isUsarFactor()){
	//
	// if(product.getEXME_FactorPre_ID() > 0) {
	// // Se ordenan los factores por linea en orden decendente. La primera
	// coincidencia de rango es la que se toma.
	// StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
	// sql.append("SELECT Linea, NivelSuperior, Porcentaje " )
	// .append(" FROM EXME_FactorPreDet WHERE EXME_FactorPre_ID = ? ")
	// .append(" AND IsActive='Y' AND NivelSuperior >= ? ");
	//
	// sql.append(MEXMELookupInfo.addAccessLevelSQL(getCtx(), " ",
	// "EXME_FactorPreDet"));
	//
	// sql.append(" ORDER BY NivelSuperior ASC "); // se puede para org * ?
	//
	// PreparedStatement pstmt = null;
	// ResultSet rs = null;
	//
	// try{
	// pstmt = DB.prepareStatement (sql.toString(), null);
	// pstmt.setInt(1, product.getEXME_FactorPre_ID());
	//
	// //Precio. El precio es determinado segun la configuracion (Lista o
	// Actual).
	// pstmt.setBigDecimal(2, price);
	//
	// rs = pstmt.executeQuery();
	//
	// if (rs.next()) {
	// // Obtenemos el nivel superior.
	// //nivelSuperior = rs.getBigDecimal("NivelSuperior");
	// porcentaje = rs.getBigDecimal("Porcentaje");
	//
	//
	// newPrice =
	// (price.multiply(porcentaje).divide(Env.ONEHUNDRED).add(price));
	//
	// //Obtenemos el ultimo precio de compra (procelist o priceactual).
	// BigDecimal lastPOPrice =
	// MEXMETransaction.getProductItemCost(getCtx(),product.getM_Product_ID(),
	// MAcctSchema.get(getCtx(),acctSchema), (isIncluirDesctos() ?
	// PRICEACTUAL_COST : PRICELIST_COST));
	// if (lastPOPrice == null) {
	// lastPOPrice = Env.ZERO;
	// }
	//
	// log.log(Level.FINEST, "Comparando Precios: (Price > LastPOPrice) - (" +
	// newPrice + " > " + lastPOPrice + "). � permite bajar precios de venta - "
	// + isBajarPrecioVta());
	// /*
	// * Validamos si el nuevo precio es menor al precio actual.
	// * Verificamos si se permiten actualizar los precios aunque estos (los
	// nuevos)
	// * vengan menores a los actuales.
	// */
	// /* if (newPrice.compareTo(lastPOPrice) > 0 || isBajarPrecioVta()) {
	// //Actualizamos M_Product_Costing. Siempre y cuando haya habido alguna
	// diferencia.
	// if (newPrice.compareTo(lastPOPrice) != 0) {
	// // Guardamos el nuevo precio en la (ACTUAL) version de la lista de
	// precios.
	// int m_PriceList_Version_ID = getSalesPLVersion(product.getM_Product_ID(),
	// true);
	// // Actualizamos el precio de lista del Producto
	// log.log(Level.FINEST,
	// "Actualizamos el precio de lista del Producto - [M_Product_ID] = " +
	// product.getM_Product_ID()
	// + ", [M_PriceList_Version_ID] = " + m_PriceList_Version_ID +
	// ", [NewPrice] = "+ newPrice);
	// if (!updateProductPriceList(product.getM_Product_ID(),
	// m_PriceList_Version_ID, newPrice, product.getC_UOM_ID()))
	// success = false;
	// } // Cambio precio?
	// }
	//
	//
	// } else {
	// log.log(Level.WARNING,
	// "El Precio Actual no encaja en ningun Nivel Superior. no se pudo determinar el Factor Precio. \nProducto: "
	// + product.getM_Product_ID());
	// success = false;
	// }
	//
	// } catch (Exception e) {
	// log.log(Level.SEVERE, "get", e);
	// success = false;
	// } finally {
	// DB.close(rs, pstmt);
	// rs = null;
	// pstmt = null;
	// }
	// } else {
	// success = true;
	// }
	// */
	// /* } else {
	// */
	// if (isCalculoAut()) {
	// // El nuevo precio no va a tener factor precio. Esto es que va a estar al
	// costo. Requiere calculo manual.
	// newPrice = price;
	//
	// // Obtenemos el ultimo precio de compra (procelist o priceactual).
	// BigDecimal lastPOPrice =
	// MEXMETransaction.getProductItemCost(getCtx(),product.getM_Product_ID(),
	// MAcctSchema.get(getCtx(),acctSchema), (isIncluirDesctos() ?
	// PRICEACTUAL_COST : PRICELIST_COST));
	// if (lastPOPrice == null) {
	// lastPOPrice = Env.ZERO;
	// }
	//
	// log.log(Level.FINEST, "Comparando Precios: (Price > LastPOPrice) - (" +
	// newPrice + " > " + lastPOPrice + "). � permite bajar precios de venta - "
	// + isBajarPrecioVta());
	// /*
	// * Validamos si el nuevo precio es menor al precio actual.
	// * Verificamos si se permiten actualizar los precios aunque estos (los
	// nuevos)
	// * vengan menores a los actuales.
	// */
	// if (newPrice.compareTo(lastPOPrice) > 0 || isBajarPrecioVta()) {
	// //Actualizamos M_Product_Costing. Siempre y cuando haya habido alguna
	// diferencia.
	// if (newPrice.compareTo(lastPOPrice) != 0) {
	// // Guardamos el nuevo precio en la (ACTUAL) version de la lista de
	// precios.
	// int m_PriceList_Version_ID = getSalesPLVersion(product.getM_Product_ID(),
	// true);
	// // Actualizamos el precio de lista del Producto
	// log.log(Level.FINEST,
	// "Actualizamos el precio de lista del Producto - [M_Product_ID] = " +
	// product.getM_Product_ID()
	// + ", [M_PriceList_Version_ID] = " + m_PriceList_Version_ID +
	// ", [NewPrice] = "+ newPrice);
	// if (!updateProductPriceList(product.getM_Product_ID(),
	// m_PriceList_Version_ID, newPrice, product.getC_UOM_ID()))
	// success = false;
	// } // Cambio precio?
	// }
	//
	// } //Fin Calculo automatico
	// // } //Fin else calculo de factor
	//
	// return success;
	// }

	// /**
	// * Actualizamos el Precio de Lista de un Producto y Version de Lista de
	// Precios dados (M_ProductPrice).
	// * (Nivel: Organizacion).
	// * @param M_Product_ID
	// * @param M_PriceList_Version_ID
	// * @param PriceList
	// * @return true, si se actualizo; si no, false.
	// */
	// public boolean updateProductPriceList(int M_Product_ID, int
	// M_PriceList_Version_ID, BigDecimal PriceList, int C_UOM_ID){
	// boolean success = true;
	//
	// StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
	// //StringBuilder sql2 = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
	// PreparedStatement pstmt = null;
	// //PreparedStatement pstmt2 = null;
	// ResultSet rs = null;
	// //ResultSet rs2 = null;
	// boolean isNewPLV = true;
	//
	// MProductPrice productPrice = null;
	// // MEXMEProductoPrecio productoPrecio = null;
	// try {
	// sql.append("SELECT * FROM M_ProductPrice WHERE ")
	// .append("M_PriceList_Version_ID = ? ")
	// .append("AND M_Product_ID = ? AND IsActive = 'Y'");
	//
	// //agregamos el nivel de acceso
	// sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(getCtx(),
	// sql.toString(), "M_ProductPrice"));
	// // Expert: Proyecto #102 Posteo, Costos y Precios
	// // Depreciada la tabla EXME_ProductoPrecio
	// /* sql2.append("SELECT * FROM EXME_ProductoPrecio WHERE ")
	// .append("M_PriceList_Version_ID = ? ")
	// .append("AND M_Product_ID = ? AND IsActive = 'Y' and C_UOM_ID = ?");
	//
	// sql2 = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(getCtx(),
	// sql2.toString(), "EXME_ProductoPrecio"));
	// */
	// // Verificamos si existe ya un registro para el producto.
	// pstmt = DB.prepareStatement(sql.toString(), null);
	// pstmt.setInt(1, M_PriceList_Version_ID);
	// pstmt.setInt(2, M_Product_ID);
	// rs = pstmt.executeQuery();
	//
	// /* pstmt2 = DB.prepareStatement(sql2.toString(), null);
	// pstmt2.setInt(1, M_PriceList_Version_ID);
	// pstmt2.setInt(2, M_Product_ID);
	// pstmt2.setInt(3, C_UOM_ID);
	// rs2 = pstmt2.executeQuery();
	// */
	// if (rs.next())
	// isNewPLV = false;
	//
	// // Verificamos si es una nueva version.
	// if (isNewPLV) {
	// // Insertamos el registro de precios del producto para la nueva version.
	// productPrice = new MProductPrice(getCtx(), 0, null);
	// productPrice.setM_Product_ID(M_Product_ID);
	// /*
	// productoPrecio = new MEXMEProductoPrecio(getCtx(), 0, null);
	// productoPrecio.setM_Product_ID(M_Product_ID);
	// */
	// // Obtenemos los precios Std y Limit de la version anterior.
	// MProductPricing pp = new MProductPricing(M_Product_ID, 0, new
	// BigDecimal(0), false); // (Producto, socio de Negocios NO Necesario,
	// Cantidad NO necesaria).
	// pp.setM_PriceList_Version_ID(M_PriceList_Version_ID);
	// pp.setPriceDate(DB.getTimestampForOrg(getCtx()));
	// pp.calculatePrice();
	//
	// // Actualizamos los precios para la nueva version.
	// productPrice.setPriceLimit(pp.getPriceLimit());
	// productPrice.setPriceList(pp.getPriceList());
	// productPrice.setPriceStd(pp.getPriceStd());
	// /*
	// productoPrecio.setPriceLimit(pp.getPriceLimit());
	// productoPrecio.setPriceList(pp.getPriceList());
	// productoPrecio.setPriceStd(pp.getPriceStd());
	// productoPrecio.setM_PriceList_Version_ID(M_PriceList_Version_ID);
	// */
	// } else {
	// // Actualizamos los precios del producto para la version existente.
	// productPrice = new MProductPrice(getCtx(), rs, null);
	// productPrice.setPriceList(PriceList);
	// productPrice.setPriceStd(PriceList);
	// productPrice.setPriceLimit(PriceList);
	//
	// /* productoPrecio = new MEXMEProductoPrecio(getCtx(), rs2, null);
	// productoPrecio.setPriceList(PriceList);
	// productoPrecio.setPriceStd(PriceList);
	// productoPrecio.setPriceLimit(PriceList);
	// if (productoPrecio.getM_PriceList_Version_ID() == 0) {
	// productoPrecio.setM_PriceList_Version_ID(M_PriceList_Version_ID);
	// }
	// */
	// }
	//
	// log.log(Level.FINEST, "Guardando Product_Price - PriceList = " +
	// PriceList);
	// if (!productPrice.save(get_TrxName())) {
	// log.log(Level.WARNING,
	// "No fue posible [actualizar | insertar] los precios para el Producto - "
	// + M_Product_ID +
	// " y Version de Lista de Precios - " + M_PriceList_Version_ID);
	// success = false;
	// }
	//
	// } catch (Exception e) {
	// log.log(Level.SEVERE, "get", e);
	// success = false;
	// } finally {
	// DB.close(rs, pstmt);
	// rs = null;
	// pstmt = null;
	// }
	//
	// return success;
	//
	// }

	// /**
	// * Obtiene la m�s actual version de lista de precios.
	// * @param update Indica si re remplazan los valores de la version actual
	// * o se genera una nueva version.
	// * @return ID de la version afectada. 0 si no encuentra el producto en
	// ninguna version
	// * de la lista de precios configurada.
	// */
	// public int getSalesPLVersion(int M_Product_ID, boolean update){
	// int plv = 0;
	// MPriceListVersion priceListVersion = null;
	//
	// // Remplazamos la lista actual con nuevos precios?
	// if (update) {
	// // Obtenemos la mas reciente y valida version de lista de precios.
	// StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
	// sql.append("SELECT pv.M_PriceList_Version_ID ")
	// .append(" FROM M_Product ")
	// .append(" INNER JOIN M_ProductPrice pp ON (M_Product.M_Product_ID=pp.M_Product_ID) ")
	// .append(" INNER JOIN  M_PriceList_Version pv ON (pp.M_PriceList_Version_ID=pv.M_PriceList_Version_ID) ")
	// .append(" INNER JOIN M_Pricelist pl ON (pv.M_PriceList_ID=pl.M_PriceList_ID) ")
	// .append(" WHERE pv.IsActive='Y' AND M_Product.isActive = 'Y' ")
	// //.append(" AND pl.AD_Client_ID = ? ")
	// //.append(" AND pl.AD_Org_ID = ? ")
	// .append(" AND M_Product.M_Product_ID = ? ")
	// .append(" AND pv.M_PriceList_ID = ? ")
	// .append(" AND pv.ValidFrom <= ? ");
	//
	// //agregamos el nivel de acceso
	// sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(getCtx(),
	// sql.toString(), "M_Product"));
	//
	// sql.append(" ORDER BY pv.ValidFrom DESC ");
	//
	// PreparedStatement pstmt = null;
	// ResultSet rs = null;
	//
	// try {
	// pstmt = DB.prepareStatement(sql.toString(), null);
	// // pstmt.setInt(1,getAD_Client_ID());
	// // pstmt.setInt(2,getAD_Org_ID());
	// pstmt.setInt(1, M_Product_ID);
	//
	// //Validamos la lista de precios a utilizar, eruiz
	// //if(M_PriceList_ID == 0)
	// pstmt.setInt(2, getM_PriceList_ID());
	// //else
	// // pstmt.setInt(2, M_PriceList_ID);
	// //Fin de la validaci�n
	// pstmt.setDate(3, new Date(System.currentTimeMillis()));
	//
	// rs = pstmt.executeQuery();
	// if (rs.next()) {
	// plv = rs.getInt(1);
	// }
	//
	// } catch (Exception e) {
	// log.log(Level.SEVERE, "get", e);
	// } finally {
	// DB.close(rs, pstmt);
	// rs = null;
	// pstmt = null;
	// }
	// } else { // Creamos una nueva version.
	// priceListVersion = new MPriceListVersion(getCtx(), 0, null);
	//
	// if (!priceListVersion.save(get_TrxName())) {
	// log.log(Level.WARNING,
	// "No fue posible guardar la Nueva Version de Lista de Precios.");
	// return 0;
	// }
	//
	// plv = priceListVersion.getM_PriceList_Version_ID();
	// }
	//
	// //reseteamos el id de la lista de precios
	//
	// M_PriceList_ID = 0;
	//
	// return plv;
	//
	// }//puedes probar de favor ...

	// /**
	// * Actualiza los precios de lista y actual de M_Product_Costing, para
	// * siempre tratar de mantener los costos mas altos para el calculo de
	// * precios de venta.
	// * <br>
	// *
	// * @param C_AcctSchema_ID accounting schema
	// * @param orderLine linea de la orden de campra a partir de la cual se
	// determinara el producto
	// * y precios de lista y venta.
	// */
	// public void updateProductInfo (int C_AcctSchema_ID, MOrderLine orderLine)
	// {
	// if(orderLine == null || orderLine.getC_OrderLine_ID() == 0 ||
	// C_AcctSchema_ID == 0){
	// log.log(Level.WARNING, "orderLine = "+ orderLine +" , C_AcctSchema_ID = "
	// + C_AcctSchema_ID);
	// return;
	// }
	//
	// log.log(Level.FINEST,
	// "updateProductInfo (priceList & priceActual) - M_OrdeLine_ID=" +
	// orderLine.getC_OrderLine_ID());
	//
	// StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
	//
	// // PreparedStatement pstmt = null;
	// int M_Product_ID = orderLine.getM_Product_ID();
	// // Obtenemos los precios de la linea de la orden de compra.
	// BigDecimal priceListPP = orderLine.getPriceList();
	// BigDecimal priceActualPP = orderLine.getPriceActual();
	//
	// //Ticket#04664 Documento INVALIDO en Recepción de Material debido a Lista
	// de Precios de OC Ealvarez
	//
	// BigDecimal priceList = Env.ZERO;
	// if (priceListPP.compareTo(Env.ZERO) == 0) {
	// priceList = Env.ZERO;
	// } else {
	// priceList = MEXMEUOMConversion.convertProductTo(getCtx(), M_Product_ID,
	// orderLine.getC_UOM_ID(), priceListPP, null);
	// }
	// BigDecimal priceActual = Env.ZERO;
	// if (priceActualPP.compareTo(Env.ZERO) == 0) {
	// priceActual = Env.ZERO;
	// } else {
	// priceActual = MEXMEUOMConversion.convertProductTo(getCtx(), M_Product_ID,
	// orderLine.getC_UOM_ID(), priceActualPP, null);
	// }
	//
	// try {
	// // Si permite bajar los precios.
	// if (isBajarPrecioVta()) {
	// // Guardamos los precios tal y como esten.
	// sql.append("UPDATE M_Product_Costing pc " +
	// "SET pc.PriceList = ").append(priceList.doubleValue())
	// .append(" ,pc.PriceActual = ").append(priceActual.doubleValue())
	// .append(" WHERE pc.C_AcctSchema_ID = ").append(C_AcctSchema_ID)
	// .append(" AND pc.M_Product_ID = ").append(M_Product_ID);
	//
	// int no = DB.executeUpdate(sql.toString(), get_TrxName());
	// log.log(Level.FINEST,
	// "M_Product_Costing - Updated PriceList & PriceActual, Registros Actualizados="
	// + no);
	// } else {
	// // Guardamos los precios (costos) mas altos.
	//
	// // Actualizamos PriceList siempre y cuando el nuevo precio venga mayor al
	// actual.
	// sql.append("UPDATE M_Product_Costing " +
	// "SET PriceList = ").append(priceList.doubleValue())
	// .append(" WHERE C_AcctSchema_ID = ").append(C_AcctSchema_ID)
	// .append(" AND M_Product_ID = ").append(M_Product_ID)
	// .append(" AND PriceList < ").append(priceList.doubleValue());
	//
	// int no = DB.executeUpdate(sql.toString(), get_TrxName());
	// log.log(Level.FINEST,
	// "M_Product_Costing - Updated PriceList, Registros Actualizados=" + no );
	//
	// // Actualizamos PricepriceActual siempre y cuando el nuevo precio venga
	// mayor al actual.
	// sql = new StringBuilder("UPDATE M_Product_Costing " +
	// "SET PriceActual = ").append(priceActual.doubleValue())
	// .append(" WHERE C_AcctSchema_ID = ").append(C_AcctSchema_ID)
	// .append(" AND M_Product_ID = ").append(M_Product_ID)
	// .append(" AND PriceActual < ").append(priceActual.doubleValue());
	//
	// no = DB.executeUpdate(sql.toString(), get_TrxName());
	// log.log(Level.FINEST,
	// "M_Product_Costing - Updated PriceActual, Registros Actualizados=" + no);
	// }
	// } catch (Exception e) {
	// log.log(Level.SEVERE, "updateProductInfo - PriceList & PriceActual ", e);
	// try {
	// DB.rollback(true, get_TrxName());
	// } catch (SQLException sqle) {
	// log.log(Level.SEVERE, "ERROR in MEXMEConfigPre.updateProductInfo = ",
	// sqle);
	// }
	// }
	// } // updateProductInfo

	// /**
	// * Obtenemos el [PriceList | PriceActual] (costo) del producto de la tabla
	// * M_Product_Costing.
	// * @param M_Product_ID
	// * @return El costo del producto segun la configuracion de precios. Si no
	// lo encuentra entonces 0.
	// */
	// public BigDecimal getPriceCost(int M_Product_ID){
	// BigDecimal cost = Env.ZERO;
	//
	// if (M_Product_ID == 0) {
	// log.log(Level.WARNING, "M_Product_ID = 0");
	// return cost;
	// }
	//
	// final StringBuilder sql = new
	// StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
	// sql.append(" SELECT PriceList, PriceActual FROM M_Product_Costing WHERE ")
	// .append(" M_Product_ID = ? AND C_AcctSchema_ID = ? AND IsActive = 'Y' ");
	//
	// int C_AcctSchema_ID = Env.getContextAsInt(getCtx(),
	// "#WS_C_AcctSchema_ID");
	//
	// if (C_AcctSchema_ID==0){
	// C_AcctSchema_ID = Env.getContextAsInt(getCtx(), "$C_AcctSchema_ID");
	// s_log.log(Level.ALL,"Warning no se pudo cargar esquema desde WS, se carga el de esquema de sistema: "+C_AcctSchema_ID);
	// }else{
	// s_log.log(Level.ALL,"WS_C_AcctSchema_ID equals to "+C_AcctSchema_ID);
	// }
	//
	// PreparedStatement pstmt = null;
	// ResultSet rs = null;
	//
	// sql.append(MEXMELookupInfo.addAccessLevelSQL(getCtx(),
	// " ","M_Product_Costing"));
	//
	// try {
	// pstmt = DB.prepareStatement(sql.toString(), null);
	// pstmt.setInt(1, M_Product_ID);
	// pstmt.setInt(2, C_AcctSchema_ID);
	// rs = pstmt.executeQuery();
	// if (rs.next()) {
	// // Incluyendo descuentos?
	// if (isIncluirDesctos()) {
	// cost = rs.getBigDecimal("PriceActual");
	// } else {
	// cost = rs.getBigDecimal("PriceList");
	// }
	// }
	// } catch (Exception e) {
	// log.log(Level.SEVERE, "get", e);
	// } finally {
	// DB.close(rs,pstmt);
	// rs = null;
	// pstmt = null;
	// }
	// log.log(Level.FINEST, "CostPrice = " + cost.doubleValue());
	// return cost;
	// }

	// Medica Sur .- LAMA, valor duro
	// Se cambio a true por ticket 2321 HRAE no cambia precios para aseguradora.
	// Cambio de regla de Negocios segun configuraciÃ³n. Expert:aaranda 11112010
	// public boolean isRecalculaPre() {
	// return true;
	// }

	@Override
	protected boolean afterSave(boolean newRecord, boolean success) {
		if (!success) {
			return success;
		}
		// actualiza las propiedades si es nuevo registro, o si se activa la
		// configuracion - Lama
		if (newRecord || is_Changed()) {
			// si se desactiva la cofig se resetea el ctx - Lama
			if (is_ValueChanged("isActive") && !isActive()) {
				deactivate();
			} else {
				// solo si la config aplica para la organizacion de logueo
				if (getAD_Org_ID() == Env.getAD_Org_ID(getCtx())
						|| getAD_Org_ID() == get(getCtx(), get_TrxName())
								.getAD_Org_ID()) {
					resetCtx();
				}
			}
		}
		return success;
	}

	@Override
	protected boolean afterDelete(boolean success) {
		deactivate();
		return success;
	}

	/**
	 * Agrega al ctx propiedades requeridas para el mtto de ingreso paciente
	 * 
	 * @param ctx
	 * @author lorena lama
	 */
	public static void setCtx(Properties ctx) {
		setCtx(ctx, null);
	}

	/**
	 * Remueve o actualiza la configuracion, que aplica para la organizacion
	 * 
	 * @author lorena lama
	 */
	public void deactivate() {
		if (getAD_Org_ID() == Env.getAD_Org_ID(getCtx()) || getAD_Org_ID() == 0)
			setCtx(getCtx(), get_TrxName());
	}

	/**
	 * Actualiza/Elimina las propiedades del ctx
	 * 
	 * @param ctx
	 * @param trxName
	 * @author lorena lama
	 */
	public static void setCtx(Properties ctx, String trxName) {
		// config. precios
		MEXMEConfigPre configPre = get(ctx, trxName);
		if (configPre == null) {
			ctx.setProperty("#ConfigPre", DB.TO_STRING(false));
			// Lista de precios
			ctx.setProperty("#M_PriceListSO_ID", "0");
			ctx.setProperty("#IsPriceZero", DB.TO_STRING(false));
			ctx.setProperty("#RecalculaPre", DB.TO_STRING(false));
			return;
		}
		configPre.resetCtx();
	}

	/**
	 * Agrega al ctx propiedades requeridas para el mtto de ingreso paciente
	 * 
	 * @author lorena lama
	 */
	private void resetCtx() {
		// si tiene una config de precios para la organizacion
		Env.setContext(getCtx(), "#ConfigPre", DB.TO_STRING(true));
		Env.setContext(getCtx(), "#M_PriceListSO_ID", getM_PriceList_ID());
		Env.setContext(getCtx(), "#IsPriceZero",
				X_EXME_ConfigPre.BUSQUEDAPRECIO_LP.equals(getBusquedaPrecio()));// siempre
																				// un
																				// precio
		Env.setContext(getCtx(), "#RecalculaPre", isRecalculaPre());
	}

	/**
	 * Lista de precios de configuracion
	 * 
	 * @return
	 */
	public MEXMEPriceList getPriceList() {
		if (lista == null) {
			lista = new MEXMEPriceList(getCtx(), getM_PriceList_ID(), null);
		}
		return lista;
	}

	/**
	 * Obtener la version de lista de precios
	 * 
	 * @param ctx
	 *            Contexto
	 * @return
	 */
	public static int getPriceListVersion(Properties ctx) {
		MEXMEConfigPre config = MEXMEConfigPre.get(ctx, null);
		if (config != null) {
			return config.getPriceList().getVersionActual()
					.getM_PriceList_Version_ID();
		} else {
			return -1;
		}
	}

	/**
	 * Si la organización puede hacer cargos donde el precio aun no se define
	 * 
	 * @param price
	 *            Precio
	 * @return true : Cuando se pueda aplicar, false : cuando no se pueda
	 *         aplicar
	 */
	public static boolean aplicarCargoSinPrecio(final BigDecimal price) {
		boolean aplicar = false;
		if (price == null || price.compareTo(Env.ZERO) <= 0) {
			final MEXMEConfigPre configPre = MEXMEConfigPre.get(Env.getCtx(),
					null);
			if (configPre == null) {
				aplicar = true;
			} else {
				aplicar = configPre.isAplicaServSinPrec();
			}
		} else {
			aplicar = true;
		}
		return aplicar;
	}
}
