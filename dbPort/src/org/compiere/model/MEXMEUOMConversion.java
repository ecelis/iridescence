/*
 * Created on 24-abr-2005
 *
 */
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;


/**
 * @author hassan reyes
 *
 */
public class MEXMEUOMConversion extends MUOMConversion {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** Static Logger               */
    private static CLogger      s_log = CLogger.getCLogger (MEXMEUOMConversion.class);
    
    /**
     * @param ctx
     * @param C_UOM_Conversion_ID
     * @param trxName
     */
    public MEXMEUOMConversion(Properties ctx, int C_UOM_Conversion_ID,
            String trxName) {
        super(ctx, C_UOM_Conversion_ID, trxName);
    }

    /**
     * @param ctx
     * @param rs
     * @param trxName
     */
    public MEXMEUOMConversion(Properties ctx, ResultSet rs, String trxName) {
        super(ctx, rs, trxName);
    }

    /**
     * @param parent
     */
    public MEXMEUOMConversion(MUOM parent) {
        super(parent);
    }

    /**
     * @param parent
     */
    public MEXMEUOMConversion(MProduct parent) {
        super(parent);
    }
    
    /**
   	 *  Convert Qty/Amt FROM product UOM to entered UOM and round.
   	 *
   	 *@param  ctx           context
   	 *@param  qtyPrice      quantity or price
   	 *@param  m_Product_ID  Description of the Parameter
   	 *@param  c_UOM_To_ID   Description of the Parameter
   	 *@return               Entered: Qty in entered UoM (precision rounded)
   	 */
   	public static BigDecimal convertProductFrom(Properties ctx,
   			int m_Product_ID, int c_UOM_To_ID, BigDecimal qtyPrice, String trxName) {
   		return convertProductFrom(ctx,m_Product_ID, c_UOM_To_ID, qtyPrice, trxName, true);
   	}
   	
    
    /**
	 *  Convert Qty/Amt FROM product UOM to entered UOM and round.
	 *
	 *@param  ctx           context
	 *@param  qtyPrice      quantity or price
	 *@param  m_Product_ID  Description of the Parameter
	 *@param  c_UOM_To_ID   Description of the Parameter
	 *@param standardPrecision Use standard or cost precision
	 *@return               Entered: Qty in entered UoM (precision rounded)
	 */
	public static BigDecimal convertProductFrom(Properties ctx,
			int m_Product_ID, int c_UOM_To_ID, BigDecimal qtyPrice, String trxName, boolean standardPrecision) {
		
        //	No conversion
		if (qtyPrice == null || qtyPrice.equals(new BigDecimal(0))
				 || c_UOM_To_ID == 0 || m_Product_ID == 0) {
            s_log.info("No Conversion - QtyPrice=" + qtyPrice);
			return null;
		}

		MProduct mProduct = new MProduct(ctx, m_Product_ID, trxName);
		if(mProduct.getC_UOM_ID()==c_UOM_To_ID){
			StringBuffer logMsg = new StringBuffer(50);
			logMsg.append("Udm de Alm. y Vta son iguales ")
				.append(c_UOM_To_ID).append("==")
				.append(mProduct.getC_UOM_ID());
            s_log.info(logMsg.toString());
			return qtyPrice;
		}
		
		//rsolorzano
		//cambio para que no pida de manera obligatoria ambas conversiones
		//MUOMConversion rates = getProductConversions(ctx, m_Product_ID, c_UOM_To_ID, trxName);
		MUOMConversion rates = validateConversions(ctx, m_Product_ID, c_UOM_To_ID, trxName);
		if (rates == null) {
            s_log.info("getProductRateFrom - none found");
			return null;
		}

		BigDecimal retValue = null;
		if (rates.getC_UOM_To_ID() == c_UOM_To_ID) {
			retValue = rates.getDivideRate();
		}else{ //rsolorzano - cambio para que no pida de manera obligatoria ambas conversiones
			retValue = rates.getMultiplyRate();
		}

		if (retValue != null) {
			if (Env.ONE.compareTo(retValue) == 0) {
			    return qtyPrice;
			}
			MUOM uom = MUOM.get(ctx, c_UOM_To_ID);
			if (uom != null) {
				return uom.round(retValue.multiply(qtyPrice), standardPrecision);
			}
			return retValue.multiply(qtyPrice);
		}
        s_log.info("No Rate M_Product_ID=" + m_Product_ID);
		return null;
	}
	//	convertProductFrom


	/**
	 *  Get Product Conversions (cached)
	 *
	 *@param  ctx           context
	 *@param  m_Product_ID  Description of the Parameter
	 *@param  c_UOM_To_ID   Description of the Parameter
	 *@return               array of conversions
	 */
	public static MUOMConversion getProductConversions(Properties ctx, int m_Product_ID, int c_UOM_To_ID, String trxName) {
		MUOMConversion result = null;

		String sql = " SELECT * FROM C_UOM_Conversion " +
				" WHERE C_UOM_To_ID = ? " +
				" AND IsActive='Y' " +
				" AND C_UOM_ID IN ( SELECT C_UOM_ID FROM M_Product WHERE M_Product_ID = ? )  ";

		sql = MEXMELookupInfo.addAccessLevelSQL(ctx,sql.toString(),"C_UOM_Conversion");
		
		PreparedStatement pstmt = null;

		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql, trxName);
			pstmt.setInt(1, c_UOM_To_ID);
			pstmt.setInt(2, m_Product_ID);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				result = new MUOMConversion(ctx, rs, trxName);
			}
			
		} catch (Exception e) {
			 s_log.log(Level.SEVERE, e.getMessage(), e);
        } finally {
        	DB.close(rs, pstmt);
        }

		//	Convert & save
		return result;
	}
	//	getProductConversions
	
	/**************************************************************************
	 *	Convert Qty/Amt from entered UOM TO product UoM and round.
	 *  @param ctx context
	 *  @param M_Product_ID product
	 *  @param C_UOM_To_ID entered UOM
	 *  @param qtyPrice entered quantity or price
	 *  @param standardPrecision use standard or costing precision
	 *  @return Product: Qty/Amt in product UoM (precision rounded)
	 */
	static public BigDecimal convertProductTo (Properties ctx,
		int M_Product_ID, int C_UOM_To_ID, BigDecimal qtyPrice, String trxName){
		return convertProductTo (ctx, M_Product_ID, C_UOM_To_ID, qtyPrice, trxName, true);
	}
	
	/**************************************************************************
	 *	Convert Qty/Amt from entered UOM TO product UoM and round.
	 *	Convert to volume
	 *  @param ctx context
	 *  @param M_Product_ID product
	 *  @param C_UOM_To_ID entered UOM
	 *  @param qtyPrice entered quantity or price
	 *  @param standardPrecision use standard or costing precision
	 *  @return Product: Qty/Amt in product UoM (precision rounded)
	 */
	static public BigDecimal convertProductTo (Properties ctx,
		int M_Product_ID, int C_UOM_To_ID, BigDecimal qtyPrice, String trxName, boolean standardPrecision)
	{
	    
		//	No conversion
		if (qtyPrice == null || qtyPrice.compareTo(BigDecimal.ZERO) == 0
				 || C_UOM_To_ID == 0 || M_Product_ID == 0) {
            s_log.info("No Conversion - QtyPrice=" + qtyPrice);
			return null;
		}

		MProduct mProduct = new MProduct(ctx, M_Product_ID, trxName);
		if(mProduct.getC_UOM_ID()==C_UOM_To_ID){
			StringBuilder logMsg = new StringBuilder(50);
			logMsg.append("Udm de Alm. y Vta son iguales ")
				.append(C_UOM_To_ID).append("==").append(mProduct.getC_UOM_ID());
            s_log.info(logMsg.toString());
			return qtyPrice;
		}
	
		//rsolorzano
		//cambio para que no pida de manera obligatoria ambas conversiones
		//MUOMConversion rates = getProductConversions(ctx, M_Product_ID, C_UOM_To_ID, trxName);
		MUOMConversion rates = validateConversions(ctx, M_Product_ID, C_UOM_To_ID, trxName);
		if (rates == null) {
            s_log.warning("getProductRateTo - none found. No hay conversion "+ "- producto - " + M_Product_ID + "udm "+ C_UOM_To_ID);
			return null;
		}

		BigDecimal retValue = null;
		if (rates.getC_UOM_To_ID() == C_UOM_To_ID) {
			retValue = rates.getMultiplyRate();
		}else{//rsolorzano - cambio para que no pida de manera obligatoria ambas conversiones
			retValue = rates.getDivideRate();
		}

		if (retValue != null) {
			if (Env.ONE.compareTo(retValue) == 0) {
                s_log.info("retValue  >> " + retValue);
				return qtyPrice;
			}
			MUOM uom = MUOM.get(ctx, C_UOM_To_ID);
			if (uom != null) {
				return uom.round(retValue.multiply(qtyPrice), standardPrecision);
			}
			return retValue.multiply(qtyPrice);
		}
        s_log.info("No Rate M_Product_ID=" + M_Product_ID);
		return null;
	}	//	convertProductTo
	
	
	/**
	 * Metodo para validar si existe la conversion entre dos unidades de medida de un producto
	 * valida los dos sentidos de la conversion
	 * @param ctx
	 * @param m_Product_ID
	 * @param c_UOM_To_ID
	 * @param trxName
	 * @return
	 */	

	public static MUOMConversion validateConversions(Properties ctx, int m_Product_ID, int c_UOM_To_ID, String trxName) {		
//		MUOMConversion result = null;
		MProduct product = new MProduct(ctx, m_Product_ID, null);		
		return getUomsConversion(ctx, product.getC_UOM_ID(), c_UOM_To_ID, trxName);
//		StringBuilder sql = new StringBuilder();
//		sql.append(" SELECT * FROM C_UOM_Conversion ")
//		.append(" WHERE C_UOM_To_ID = ? ")
//		.append(" AND IsActive='Y' ")
//		.append(" AND C_UOM_ID IN ( SELECT C_UOM_ID FROM M_Product WHERE M_Product_ID = ? )  ");
//		 
//		sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx,sql.toString(),"C_UOM_Conversion"));
//		
//		StringBuilder sql2 = new StringBuilder();
//		sql2.append(" SELECT * FROM C_UOM_Conversion ")
//		.append(" WHERE C_UOM_To_ID = ( SELECT C_UOM_ID FROM M_Product WHERE M_Product_ID = ? ) ")
//		.append(" AND IsActive='Y' ")
//		.append(" AND C_UOM_ID IN ( ? )  ");
//		
//		sql2 = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx,sql2.toString(),"C_UOM_Conversion"));
//		
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		
//		try {
//			
//			pstmt = DB.prepareStatement(sql.toString(), trxName);
//			pstmt.setInt(1, c_UOM_To_ID);
//			pstmt.setInt(2, m_Product_ID);
//			rs = pstmt.executeQuery();
//			if (rs.next()) {
//				result = new MUOMConversion(ctx, rs, trxName);
//			}
//			
//			DB.close(rs, pstmt);
//			
//			if(result == null){
//				pstmt = DB.prepareStatement(sql2.toString(), trxName);
//				pstmt.setInt(1, m_Product_ID);
//				pstmt.setInt(2, c_UOM_To_ID);
//				rs = pstmt.executeQuery();
//				if (rs.next()) {
//					result = new MUOMConversion(ctx, rs, trxName);
//				}
//			}
//			
//		} catch (Exception e) {
//			 s_log.log(Level.SEVERE, e.getMessage());
//        } finally {
//        	DB.close(rs, pstmt);
//        }
//
//		return result;
	}

	/**
	 * Metodo para validar si existe la conversion entre dos unidades de medida
	 * valida los dos sentidos de la conversion
	 * @param ctx
	 * @param m_Product_ID
	 * @param c_UOM_To_ID
	 * @param trxName
	 * @return
	 */	
	public static MUOMConversion getUomsConversion(Properties ctx, int c_uom_ID, int c_UOM_To_ID, String trxName) {
		
		MUOMConversion result = null;

		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT * FROM C_UOM_Conversion ")
		.append(" WHERE C_UOM_To_ID = ? ")
		.append(" AND IsActive='Y' ")
		.append(" AND C_UOM_ID IN  ( ? ) ");
		 
		sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx,sql.toString(),"C_UOM_Conversion"));
		
		StringBuilder sql2 = new StringBuilder();
		sql2.append(" SELECT * FROM C_UOM_Conversion ")
		.append(" WHERE C_UOM_To_ID =  ?  ")
		.append(" AND IsActive='Y' ")
		.append(" AND C_UOM_ID IN ( ? )  ");
		
		sql2 = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx,sql2.toString(),"C_UOM_Conversion"));
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, c_UOM_To_ID);
			pstmt.setInt(2, c_uom_ID);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				result = new MUOMConversion(ctx, rs, trxName);
			}
			
			DB.close(rs, pstmt);
			
			if(result == null){
				pstmt = DB.prepareStatement(sql2.toString(), trxName);
				pstmt.setInt(1, c_uom_ID);
				pstmt.setInt(2, c_UOM_To_ID);
				rs = pstmt.executeQuery();
				if (rs.next()) {
					result = new MUOMConversion(ctx, rs, trxName);
				}
			}
			
		} catch (Exception e) {
			 s_log.log(Level.SEVERE, e.getMessage(), e);
        } finally {
        	DB.close(rs, pstmt);
        }

		return result;
		
	}
	
	/**
	 * Calcula unidades de volumen y mínima
	 * 
	 * @param ctx
	 *            Contexto de app
	 * @param product
	 *            Producto
	 * @param qtyTotal
	 *            Cantidad (Unidades Minimas)
	 * @return Arreglo con dos datos, unidad de volumen en primer registro
	 */
	public static BigDecimal[] calculateQtys(Properties ctx, MProduct product, BigDecimal qtyTotal) {
		BigDecimal[] arr = new BigDecimal[2];

		if (product.getC_UOMVolume_ID() <= 0 || product.getC_UOM_ID() == product.getC_UOMVolume_ID()) {
			arr[0] = BigDecimal.ZERO;
			arr[1] = qtyTotal;
		} else {
			// Obtenemos la cantidad de volumen (Ejemplo Cajas)
			BigDecimal qtyVol = MEXMEUOMConversion.convertProductTo(ctx, product.getM_Product_ID(), product.getC_UOMVolume_ID(), qtyTotal, null, true);

			// Si es nulo el valor, asignamos un 0
			if (qtyVol == null) {
				qtyVol = BigDecimal.ZERO;
			} else {
				// de lo contrario lo redondeamos hacia abajo, para que sea un
				// número entero (Ejemplo 2.5 cajas pasa a ser 2 cajas)
				qtyVol = qtyVol.setScale(0, BigDecimal.ROUND_FLOOR);
			}

			BigDecimal qtyMin = null;

			// si la cantidad de volumen es cero y la cantidad total es mayor a
			// cero, entonces cantidad minima es igual a total
			if (qtyVol.compareTo(BigDecimal.ZERO) <= 0 && qtyTotal.compareTo(BigDecimal.ZERO) == 1) {
				qtyMin = qtyTotal;
			} else {
				// si no se calcula la fracción
				qtyMin = MUOMConversion.getConversionDivisor(product, qtyTotal);
			}

			arr[0] = qtyVol;
			arr[1] = qtyMin;
		}

		return arr;
	}

}
