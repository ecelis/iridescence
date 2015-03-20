/**
 *  No tiene relacion a alguna tabla en especifico hace el proceso de calculo de
 *  descuestos a partir de un esquema de la tabla EXME_EsquemaDescuestoLinea y
 *  del precio calculado en PreciosVenta
 */
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;

/**
 * Modelo que permite elcalculo de los convenios <b>Modificado: </b> $Author:
 * taniap $
 * <p>
 * <b>En :</b> $Date: 2006/11/02 21:36:39 $
 * <p>
 * 
 * @author Tania P.
 * @version $Revision: 1.12 $
 * @deprecated
 */
public class MEsquemaDescuento {

	/**
	 * Formato de fecha
	 */
	private static final SimpleDateFormat formatoFecha = new SimpleDateFormat(
			"dd/MM/yyyy");

	/** Static Logger */
	private static CLogger s_log = CLogger.getCLogger(MEsquemaDescuento.class);

	/**
	 * Enviando solo el objeto MPrecios
	 * 
	 * @param ctx
	 * @param precios
	 * @param EXME_Area_ID
	 * @param tipoArea
	 * @param C_UOM_ID
	 * @param fecha
	 * @param trxName
	 * @return
	 */
//	public static MPrecios descuentos(Properties ctx, MPrecios precios,
//			int EXME_Area_ID, String tipoArea, int C_UOM_ID, Timestamp fecha,
//			String trxName) {
//
//		return descuentos(ctx, precios, EXME_Area_ID, precios
//				.getC_BPartner_ID(), tipoArea, precios.getM_Product_ID(),
//				C_UOM_ID, fecha, trxName);
//	}

	/**
	 * Metodologia con los pasos para la busqueda de los esquemas de descuentos
	 * 
	 *@param precioList
	 *            Precio sobre el que se hacen los calculos de descuento
	 *@return una cadena con los motivos con los cuales no se calculo descuento
	 *         que no son errores
	 */
//	public static MPrecios descuentos(Properties ctx, MPrecios precios,
//			int EXME_Area_ID, int C_BPartner_ID, String tipoArea,
//			int M_Product_ID, int C_UOM_ID, Timestamp fecha, String trxName) {
//
//		if (C_BPartner_ID <= 0 || M_Product_ID <= 0) {
//			precios
//					.setM_processMsg("No existen socio de negocios para calcular descuentos");
//			return precios;
//		}

		/**
		 * if(precios!=null && ( (precios.getPrecioOriginal()!=null &&
		 * precios.getPrecioOriginal().compareTo(Env.ZERO)==0) ||
		 * precios.getPrecioOriginal()==null))
		 * precios.setPrecioOriginal(precios.getPriceList());
		 **/

//		// Socio de negocios
//		MBPartner m_BPartner = new MBPartner(ctx, C_BPartner_ID, trxName);
//
//		// Producto
//		MProduct m_Product = new MProduct(ctx, M_Product_ID, trxName);
//
//		if (C_UOM_ID <= 0)
//			C_UOM_ID = m_Product.getC_UOM_ID();
//
//		if (fecha == null)
//			fecha = DB.getTimestampForOrg(ctx);
//		//
//		String cad = "";
//
//		precios.setEXME_EsqDesLine_ID(0);
//
//		// Socio - Producto
//		cad = " AND EXME_EsqDesLine.C_BPartner_ID = "
//				+ m_BPartner.getC_BPartner_ID()
//				+ " AND EXME_EsqDesLine.M_Product_ID = "
//				+ m_Product.getM_Product_ID()
//				+ " AND EXME_EsqDesLine.C_UOM_ID =  " + C_UOM_ID;
//
//		precios = calculo(ctx, precios, cad, fecha, EXME_Area_ID, tipoArea,
//				"SP", trxName); // Socio y porducto
//
//		if (precios.getPriceStd().compareTo(Env.ZERO) <= 0) {
//			// Socio - Categoria
//			cad = " AND EXME_EsqDesLine.C_BPartner_ID = "
//					+ m_BPartner.getC_BPartner_ID()
//					+ " AND EXME_EsqDesLine.M_Product_Category_ID = "
//					+ m_Product.getM_Product_Category_ID();
//			precios = calculo(ctx, precios, cad, fecha, EXME_Area_ID, tipoArea,
//					"SC", trxName);
//
//			if (precios.getPriceStd().compareTo(Env.ZERO) <= 0) {
//				// Grupo - Producto
//				cad = " AND EXME_EsqDesLine.C_BP_Group_ID = "
//						+ m_BPartner.getC_BP_Group_ID()
//						+ " AND EXME_EsqDesLine.M_Product_ID = "
//						+ m_Product.getM_Product_ID()
//						+ " AND EXME_EsqDesLine.C_UOM_ID =  " + C_UOM_ID;
//				precios = calculo(ctx, precios, cad, fecha, EXME_Area_ID,
//						tipoArea, "GP", trxName);
//
//				if (precios.getPriceStd().compareTo(Env.ZERO) <= 0) {
//					// Grupo - Categoria
//					cad = " AND EXME_EsqDesLine.C_BP_Group_ID = "
//							+ m_BPartner.getC_BP_Group_ID()
//							+ " AND EXME_EsqDesLine.M_Product_Category_ID = "
//							+ m_Product.getM_Product_Category_ID();
//					precios = calculo(ctx, precios, cad, fecha, EXME_Area_ID,
//							tipoArea, "GC", trxName);
//
//					if (precios.getPriceStd().compareTo(Env.ZERO) <= 0) {
//						m_BPartner = null;
//						m_Product = null;
//						precios
//								.setM_processMsg("No se encontro ningun esquema de descuentos de linea para el socio/grupo con el producto/categoria");
//						return precios;
//					}
//				}
//			}
//		}
//
//		m_BPartner = null;
//		m_Product = null;
//
//		precios.setPriceLimit(precios.getPriceStd());
//		// System.out.println("**** descuentos fin : "+ formato.format(new
//		// Date()));
//		return precios;
//	}

	/**
	 * Asisgna el precio calculado ya con descuentos
	 * 
	 *@param cad
	 *            parte del where que permite hacer la busqueda de los esquemas
	 *            de descuento
	 *@param fecha
	 *            actual
	 *@return true si se calculo el descuento exitosamente falso si exitio un
	 *         error
	 */
//	public static MPrecios calculo(Properties ctx, MPrecios precios,
//			String cad, Timestamp fecha, int EXME_Area_ID, String tipoArea,
//			String opcion, String trxName) {
//
//		// System.out.println("**** Calculo Inicio : "+ formato.format(new
//		// Date()));
//
//		String sql = " SELECT EXME_EsqDesLine.* " + " FROM EXME_EsqDesLine "
//				+ " WHERE EXME_EsqDesLine.IsActive = 'Y' "
//				+ " AND to_date(?,'dd/MM/yyyy') BETWEEN "
//				+ " EXME_EsqDesLine.ValidFrom AND EXME_EsqDesLine.ValidTo AND ";
//
//		if (EXME_Area_ID > 0)
//			sql += " ( EXME_EsqDesLine.EXME_Area_ID = " + EXME_Area_ID
//					+ " OR  ";
//
//		sql += " EXME_EsqDesLine.EXME_Area_ID IS NULL ";
//
//		if (EXME_Area_ID > 0)
//			sql += " ) ";
//
//		sql += " AND ";
//
//		if (tipoArea != null && !tipoArea.equalsIgnoreCase(""))
//			sql += " ( EXME_EsqDesLine.TipoArea = '" + tipoArea + "' OR ";
//
//		sql += " EXME_EsqDesLine.TipoArea IS NULL ";
//
//		if (tipoArea != null && !tipoArea.equalsIgnoreCase(""))
//			sql += " ) ";
//
//		if (cad != null)
//			sql += cad;
//
//		sql = MEXMELookupInfo.addAccessLevelSQL(ctx, sql, "EXME_EsqDesLine");
//
//		sql += " ORDER BY EXME_EsqDesLine.EXME_Area_ID, EXME_EsqDesLine.TipoArea, "
//				+ " EXME_EsqDesLine.ValidFrom DESC ";
//
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		try {
//			pstmt = DB.prepareStatement(sql, trxName);
//			pstmt.setString(1, formatoFecha.format(fecha));
//
//			rs = pstmt.executeQuery();
//			if (rs.next()) {
//				MEXMEEsqDesLine esquema = new MEXMEEsqDesLine(ctx, rs, trxName);
//				precios.setPriceStd(precioStd(precios.getPriceList(), esquema
//						.getList_AddAmt(), esquema.getList_Discount(), opcion));
//				precios.setEXME_EsqDesLine_ID(esquema.getEXME_EsqDesLine_ID());
//				precios.setDiscountAmt(Env.ZERO);
//				precios.setDiscountPorc(Env.ZERO);
//
//				// Descuento por montp
//				if (esquema.getList_AddAmt().compareTo(Env.ZERO) != 0) {
//					// si es negativo es descuento
//					// si es positivo es aumento
//					if (esquema.getList_AddAmt().compareTo(
//							precios.getPriceList()) != 0)
//						precios.setPrecioOriginal(precios.getPriceList());// Conserva
//																			// precio
//																			// Original
//					precios.setPriceList(esquema.getList_AddAmt());
//				} else {
//					// Hay descuento, es un porcentaje
//					if (esquema.getList_Discount().compareTo(Env.ZERO) != 0) {
//						precios.setDiscountAmt(precios.getPriceList().subtract(
//								precios.getPriceStd()));
//						precios.setDiscountPorc(esquema.getList_Discount());// %
//					}
//				}
//
//				esquema = null;
//			}
//
//		} catch (Exception e) {
//			s_log.log(Level.SEVERE, sql.toString(), e.getMessage());
//			try {
//				if (pstmt != null)
//					pstmt.close();
//				if (rs != null)
//					rs.close();
//			} catch (Exception ex) {
//				s_log.log(Level.SEVERE, sql.toString(), ex);
//			}
//			pstmt = null;
//			rs = null;
//		} finally {
//			try {
//				if (pstmt != null) {
//					pstmt.close();
//				}
//				if (rs != null) {
//					rs.close();
//				}
//				pstmt = null;
//				rs = null;
//			} catch (Exception e) {
//				s_log.log(Level.SEVERE, "Closing rs and pstmt", e.getMessage());
//				pstmt = null;
//				rs = null;
//			}
//		}
//
//		return precios;
//	}

	// getBreaks

	/**
	 * Calulo del Precio Standar o Actual ya con un esquema de descuentos
	 * siempre y cuando exista un esquema de descuentos
	 * 
	 *@param addCant
	 *            Description of the Parameter
	 *@param desc
	 *            Description of the Parameter
	 *@return
	 */
	public static BigDecimal precioStd(BigDecimal priceList,
			BigDecimal addCant, BigDecimal desc, String opcion) {

		BigDecimal newPrice = Env.ZERO;
		BigDecimal multiplier = Env.ZERO;
		try {

			// monto agregado
			if (addCant != null
					&& Env.ZERO.compareTo(addCant) < 0
					&& (opcion.equalsIgnoreCase("SP") || opcion
							.equalsIgnoreCase("GP"))) {
				newPrice = addCant;
			} else {
				// descuento
				if (desc != null && Env.ZERO.compareTo(desc) < 0) {
					multiplier = desc.divide(Env.ONEHUNDRED);
					multiplier = priceList.multiply(multiplier);
					newPrice = priceList.subtract(multiplier);

				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		// regresa el precio
		return newPrice;
	}

}
