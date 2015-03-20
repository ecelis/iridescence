package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.apache.commons.lang.StringUtils;
import org.compiere.util.CLogger;
import org.compiere.util.CalcularEdadAMD;
import org.compiere.util.Constantes;
import org.compiere.util.DB;

public class MEXMEOrderSetProd extends X_EXME_OrderSetProd {

	private boolean isNew = false;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** log de la clase*/
	private static CLogger log = CLogger.getCLogger(MEXMEOrderSetDiag.class);
	
	public MEXMEOrderSetProd(Properties ctx, int EXME_OrderSetProd_ID,
			String trxName) {
		super(ctx, EXME_OrderSetProd_ID, trxName);
	}

	public MEXMEOrderSetProd(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}
	

	/**
	 * Obtiene los productos(servicios) del order set, separados por coma
	 * @param ctx
	 * @param orderSetID
	 * @return String
	 */
	public static String getProductsStr(final Properties ctx, final int orderSetID){

		StringBuilder strResult = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);

		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		PreparedStatement pstmt = null;
		ResultSet result = null;
		try {
			sql.append("SELECT M_Product.Name ")
				.append(" FROM EXME_OrderSetProd ")
				.append("INNER JOIN M_Product ON EXME_OrderSetProd.M_Product_ID = M_Product.M_Product_ID ")
				.append("WHERE EXME_OrderSetProd.EXME_OrderSet_ID = ? AND EXME_OrderSetProd.ISACTIVE='Y' " )
				.append("AND (EXME_OrderSetProd.isEstudio = 'Y' OR EXME_OrderSetProd.ProductType = ? OR EXME_OrderSetProd.ProductType = ?) ")
				.append(" ORDER BY M_Product.Name ");
			
			pstmt = DB.prepareStatement(sql.toString(), null);
				pstmt.setInt(1, orderSetID);
				pstmt.setString(2, MEXMEOrderSetProd.PRODUCTTYPE_Service);
				pstmt.setString(3, MEXMEOrderSetProd.PRODUCTTYPE_Studies);
			
			result = pstmt.executeQuery();
			while(result.next()){
				if(strResult.length()>0){
					strResult.append(", ");
				}
				strResult.append(result.getString("Name"));
			}
		} catch (SQLException e) {
			log.log(Level.SEVERE, e.getLocalizedMessage());
		} finally {
			DB.close(result, pstmt);
		}

		return strResult.toString();
	}
	
	/**
	 * Obtiene los medicamentos (genproduct) del order set, separados por coma
	 * @param ctx
	 * @param orderSetID
	 * @return String
	 */
	public static String getGenProductStr(final Properties ctx, final int orderSetID){

		StringBuilder strResult = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);

		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		PreparedStatement pstmt = null;
		ResultSet result = null;
		try {
			sql.append("SELECT EXME_GenProduct.Generic_Product_Name as Name")
				.append(" FROM EXME_OrderSetProd ")
				.append("INNER JOIN EXME_GenProduct ON EXME_OrderSetProd.EXME_GenProduct_ID = EXME_GenProduct.EXME_GenProduct_ID ")
				.append("WHERE EXME_OrderSetProd.EXME_OrderSet_ID = ? AND EXME_OrderSetProd.ISACTIVE = 'Y' " )
				.append(" AND EXME_OrderSetProd.ProductType = ? " )
				.append(" ORDER BY EXME_GenProduct.Generic_Product_Name ");
			
			pstmt = DB.prepareStatement(sql.toString(), null);
				pstmt.setInt(1, orderSetID);
				pstmt.setString(2, MEXMEOrderSetProd.PRODUCTTYPE_Medication);
			
			result = pstmt.executeQuery();
			while(result.next()){
				if(strResult.length()>0){
					strResult.append(", ");
				}
				strResult.append(result.getString("Name"));
			}
		} catch (SQLException e) {
			log.log(Level.SEVERE, e.getLocalizedMessage());
		} finally {
			DB.close(result, pstmt);
		}

		return strResult.toString();
	}
	
	/**
	 * Obtiene los productos(procedimientos) del order set, separados por coma
	 * @param ctx
	 * @param orderSetID
	 * @return String
	 */
	public static String getProceduresStr(final Properties ctx, final int orderSetID){

		StringBuilder strResult = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);

		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		PreparedStatement pstmt = null;
		ResultSet result = null;
		try {
			sql.append("SELECT M_Product.Name ")
				.append(" FROM EXME_OrderSetProd ")
				.append("INNER JOIN M_Product ON EXME_OrderSetProd.M_Product_ID = M_Product.M_Product_ID ")
				.append("WHERE EXME_OrderSetProd.EXME_OrderSet_ID = ? " )
				.append("AND EXME_OrderSetProd.ProductType = ? AND EXME_OrderSetProd.ISACTIVE = 'Y'")
				.append(" ORDER BY M_Product.Name ");
			
			pstmt = DB.prepareStatement(sql.toString(), null);
				pstmt.setInt(1, orderSetID);
				pstmt.setString(2, MEXMEOrderSetProd.PRODUCTTYPE_Procedure);
			
			result = pstmt.executeQuery();
			while(result.next()){
				if(strResult.length()>0){
					strResult.append(", ");
				}
				strResult.append(result.getString("Name"));
			}
		} catch (SQLException e) {
			log.log(Level.SEVERE, e.getLocalizedMessage());
		} finally {
			DB.close(result, pstmt);
		}

		return strResult.toString();
	}
	
	/**
	 * Obtiene los productos(servicios) del order set
	 * @param ctx
	 * @param orderSetID
	 * @return List<MEXMEOrderSetProd>
	 */
	public static List<MEXMEOrderSetProd> getProducts(final Properties ctx, final int orderSetID){

		List<MEXMEOrderSetProd> array = new ArrayList<MEXMEOrderSetProd>();
		
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		PreparedStatement pstmt = null;
		ResultSet result = null;
		try {
			sql.append("SELECT EXME_OrderSetProd.* ")
				.append("FROM EXME_OrderSetProd ")
				.append("WHERE EXME_OrderSetProd.EXME_OrderSet_ID = ? ")
				.append("AND EXME_OrderSetProd.M_Product_ID > 0 ")
				.append("AND EXME_OrderSetProd.EXME_GenProduct_ID IS NULL ")
				.append("AND EXME_OrderSetProd.isActive = 'Y' ")
				.append("AND (EXME_OrderSetProd.isEstudio = 'Y' OR ProductType = ? OR ProductType = ?) ")
				.append(" ORDER BY EXME_OrderSetProd.Created ");
			
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setInt(1, orderSetID);
			pstmt.setString(2, MEXMEOrderSetProd.PRODUCTTYPE_Service);
			pstmt.setString(3, MEXMEOrderSetProd.PRODUCTTYPE_Studies);
			
			result = pstmt.executeQuery();
			while(result.next()){
				array.add(new MEXMEOrderSetProd(ctx, result, null));
			}
		} catch (SQLException e) {
			log.log(Level.SEVERE, e.getLocalizedMessage());
		} finally {
			DB.close(result, pstmt);
		}

		return array;
	}
	
	/**
	 * Obtiene los productos(servicios) del order set
	 * @param ctx
	 * @param orderSetID
	 * @return List<MEXMEOrderSetProd>
	 */
	public static List<MProduct> getProducts(final Properties ctx, final int orderSetID, final boolean isEstudio, final String type){

		List<MProduct> array = new ArrayList<MProduct>();
		
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		PreparedStatement pstmt = null;
		ResultSet result = null;
		try {
			sql.append("SELECT PROD.* ")
				.append("FROM EXME_OrderSetProd ")
				.append("INNER JOIN M_PRODUCT PROD ON PROD.M_PRODUCT_ID = EXME_ORDERSETPROD.M_PRODUCT_ID ")				
				.append("WHERE EXME_OrderSetProd.EXME_OrderSet_ID = ? ")
				.append("AND EXME_OrderSetProd.M_Product_ID > 0 ")
				.append("AND EXME_OrderSetProd.EXME_GenProduct_ID IS NULL ")
				.append("AND EXME_OrderSetProd.isActive = 'Y' ")
				.append("AND EXME_OrderSetProd.ProductType = ?  ");
			
				if(isEstudio)
					sql.append("AND EXME_OrderSetProd.isEstudio = 'Y' ");
			
					sql.append(" ORDER BY EXME_OrderSetProd.Created ");
			
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setInt(1, orderSetID);
			pstmt.setString(2, type);
			
			result = pstmt.executeQuery();
			while(result.next()){
				array.add(new MProduct(ctx, result, null));
			}
		} catch (SQLException e) {
			log.log(Level.SEVERE, e.getLocalizedMessage());
		} finally {
			DB.close(result, pstmt);
		}

		return array;
	}
	
	/**
	 * Obtiene los productos(servicios) del order set que no sean medicamentos y que sean internos
	 * @param ctx
	 * @param orderSetID
	 * @return List<MEXMEOrderSetProd>
	 */
	public static List<MProduct> getInternalServices(final Properties ctx, final int orderSetID){

		List<MProduct> array = new ArrayList<MProduct>();
		
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		PreparedStatement pstmt = null;
		ResultSet result = null;
		try {
			sql.append("SELECT PROD.* ")
				.append("FROM EXME_OrderSetProd ")
				.append("INNER JOIN M_PRODUCT PROD ON PROD.M_PRODUCT_ID = EXME_ORDERSETPROD.M_PRODUCT_ID ")				
				.append("WHERE EXME_OrderSetProd.EXME_OrderSet_ID = ? ")
				.append("AND EXME_OrderSetProd.M_Product_ID > 0 ")
				.append("AND EXME_OrderSetProd.EXME_GenProduct_ID IS NULL ")
				.append("AND EXME_OrderSetProd.isActive = 'Y' ")
				.append("AND EXME_OrderSetProd.ProductType <> 'MD'  ")
				.append("AND EXME_OrderSetProd.isExternal = 'N'  ")
				.append(" ORDER BY EXME_OrderSetProd.Created ");
			
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setInt(1, orderSetID);
			
			result = pstmt.executeQuery();
			while(result.next()){
				array.add(new MProduct(ctx, result, null));
			}
		} catch (SQLException e) {
			log.log(Level.SEVERE, e.getLocalizedMessage());
		} finally {
			DB.close(result, pstmt);
		}

		return array;
	}


	/**
	 * Obtiene los medicamentos(rxnorm) del order set
	 * @param ctx
	 * @param orderSetID
	 * @return List<MEXMEOrderSetProd>
	 */
	public static List<MEXMEOrderSetProd> getRXNorm(final Properties ctx, final int orderSetID){

		List<MEXMEOrderSetProd> array = new ArrayList<MEXMEOrderSetProd>();
		
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		PreparedStatement pstmt = null;
		ResultSet result = null;
		try {
			sql.append("SELECT EXME_OrderSetProd.* ")
				.append("FROM EXME_OrderSetProd ")
				.append("WHERE EXME_OrderSetProd.EXME_OrderSet_ID = ? ")
//				.append("AND EXME_OrderSetProd.M_Product_ID IS NULL ")
				.append("AND EXME_OrderSetProd.EXME_GenProduct_ID > 0 ")
				.append("AND EXME_OrderSetProd.ProductType = ? ")
				.append("AND EXME_OrderSetProd.isActive = 'Y' ")
				.append(" ORDER BY EXME_OrderSetProd.Created ");
			
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setInt(1, orderSetID);
			pstmt.setString(2, MEXMEOrderSetProd.PRODUCTTYPE_Medication);
			
			result = pstmt.executeQuery();
			while(result.next()){
				array.add(new MEXMEOrderSetProd(ctx, result, null));
			}
		} catch (SQLException e) {
			log.log(Level.SEVERE, e.getLocalizedMessage());
		} finally {
			DB.close(result, pstmt);
		}

		return array;
	}
	
	/**
	 * Obtiene los productos(procedimientos) del order set
	 * @param ctx
	 * @param orderSetID
	 * @return List<MEXMEOrderSetProd>
	 */
	public static List<MEXMEOrderSetProd> getProcedures(final Properties ctx, final int orderSetID){

		List<MEXMEOrderSetProd> array = new ArrayList<MEXMEOrderSetProd>();
		
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		PreparedStatement pstmt = null;
		ResultSet result = null;
		try {
			sql.append("SELECT EXME_OrderSetProd.* ")
				.append("FROM EXME_OrderSetProd ")
				.append("WHERE EXME_OrderSetProd.EXME_OrderSet_ID = ? ")
				.append("AND EXME_OrderSetProd.M_Product_ID > 0 ")
				.append("AND EXME_OrderSetProd.EXME_GenProduct_ID IS NULL ")
				.append("AND EXME_OrderSetProd.isActive = 'Y' ")
				.append("AND EXME_OrderSetProd.ProductType = ? ")
				.append(" ORDER BY EXME_OrderSetProd.Created ");
			
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setInt(1, orderSetID);
			pstmt.setString(2, MEXMEOrderSetProd.PRODUCTTYPE_Procedure);
			
			result = pstmt.executeQuery();
			while (result.next()) {
				array.add(new MEXMEOrderSetProd(ctx, result, null));
			}
		} catch (SQLException e) {
			log.log(Level.SEVERE, e.getLocalizedMessage());
		} finally {
			DB.close(result, pstmt);
		}

		return array;
	}	
	
	
	/**
	 * Obtiene los productos(servicios) del order set
	 * @param ctx
	 * @param orderSetID
	 * @return List<MEXMEOrderSetProd>
	 */
	public static List<MProduct> getProductsProd(final Properties ctx, final int orderSetID){

		List<MProduct> array = new ArrayList<MProduct>();
		
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		PreparedStatement pstmt = null;
		ResultSet result = null;
		try {
			sql.append("SELECT EXME_OrderSetProd.M_Product_ID ")
				.append("FROM EXME_OrderSetProd ")
				.append("WHERE EXME_OrderSetProd.EXME_OrderSet_ID = ? ")
				.append("AND EXME_OrderSetProd.M_Product_ID > 0 ")
				.append("AND EXME_OrderSetProd.EXME_GenProduct_ID IS NULL ")
				.append("AND EXME_OrderSetProd.isActive = 'Y' ")
				.append("AND (EXME_OrderSetProd.isEstudio = 'Y' OR ProductType = ? OR ProductType = ?) ")
				.append(" ORDER BY EXME_OrderSetProd.Created ");
			
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setInt(1, orderSetID);
			pstmt.setString(2, MEXMEOrderSetProd.PRODUCTTYPE_Service);
			pstmt.setString(3, MEXMEOrderSetProd.PRODUCTTYPE_Studies);
			
			result = pstmt.executeQuery();
			while(result.next()){
				array.add(new MProduct(ctx, result.getInt(COLUMNNAME_M_Product_ID), null));
			}
		} catch (SQLException e) {
			log.log(Level.SEVERE, e.getLocalizedMessage());
		} finally {
			DB.close(result, pstmt);
		}

		return array;
	}
	
	
	
	/**
	 * Obtiene los productos(procedimientos) del order set
	 * @param ctx
	 * @param orderSetID
	 * @return List<MEXMEOrderSetProd>
	 */
	public static List<MProduct> getProceduresProd(final Properties ctx, final int orderSetID){

		List<MProduct> array = new ArrayList<MProduct>();
		
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		PreparedStatement pstmt = null;
		ResultSet result = null;
		try {
			sql.append("SELECT EXME_OrderSetProd.M_Product_ID ")
				.append("FROM EXME_OrderSetProd ")
				.append("WHERE EXME_OrderSetProd.EXME_OrderSet_ID = ? ")
				.append("AND EXME_OrderSetProd.M_Product_ID > 0 ")
				.append("AND EXME_OrderSetProd.EXME_GenProduct_ID IS NULL ")
				.append("AND EXME_OrderSetProd.isActive = 'Y' ")
				.append("AND EXME_OrderSetProd.ProductType = ? ")
				.append(" ORDER BY EXME_OrderSetProd.Created ");
			
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setInt(1, orderSetID);
			pstmt.setString(2, MEXMEOrderSetProd.PRODUCTTYPE_Procedure);
			
			result = pstmt.executeQuery();
			while (result.next()) {
				array.add(new MProduct(ctx, result.getInt(COLUMNNAME_M_Product_ID), null));
			}
		} catch (SQLException e) {
			log.log(Level.SEVERE, e.getLocalizedMessage());
		} finally {
			DB.close(result, pstmt);
		}

		return array;
	}	
	
	private MEXMEGenProduct mexmeGenProduct = null;
	private MProduct mProduct = null;

	public MEXMEGenProduct getMexmeGenProduct() {
		if (mexmeGenProduct == null) {
			mexmeGenProduct = new MEXMEGenProduct(getCtx(), getEXME_GenProduct_ID(), null);
			mexmeGenProduct.setRefill(getRefill());
			mexmeGenProduct.setQuantity(getQuantity());
			mexmeGenProduct.setExme_Frecuencia_ID(getEXME_Frequency1_ID());
			
		}
		return mexmeGenProduct;
	}

	public void setMexmeGenProduct(MEXMEGenProduct mexmeGenProduct) {
		this.mexmeGenProduct = mexmeGenProduct;
	}
	
	public MProduct getM_Product() {
		if(mProduct == null && getM_Product_ID() > 0){
			mProduct = new MProduct(getCtx(), getM_Product_ID(), get_TrxName());
		}
		return mProduct;
	}
	
	/**
	 * Obtiene los medicamentos(rxnorm) del order set filtrando por la edad del paciente 
	 * @param ctx
	 * @param orderSetID
	 * @param patient
	 * @return List<MEXMEOrderSetProd>
	 */
	public static List<MEXMEOrderSetProd> getRXNorm(final Properties ctx, final int orderSetID, final MEXMEPaciente patient){
		List<MEXMEOrderSetProd> array = new ArrayList<MEXMEOrderSetProd>();
		if(patient == null){
			return array;
		}
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		PreparedStatement pstmt = null;
		ResultSet result = null;
		final CalcularEdadAMD edad = CalcularEdadAMD.getEdad(ctx,Constantes.getSdfFecha().format(patient.getFechaNac()));
		
		try {
			sql.append(" SELECT * FROM (  ")
				.append(" SELECT ") 
				.append(" exme_ordersetprod.*, ")
				.append(" (CASE EXME_ORDERSETPROD.unitmeasure ") 
				.append(" WHEN 'P' THEN (EXME_ORDERSETPROD.WEIGHTMIN * 0.4536) ")
				.append(" ELSE EXME_ORDERSETPROD.WEIGHTMIN ")
				.append(" END) as WPMin, ")
				.append(" (CASE EXME_ORDERSETPROD.unitmeasure ") 
				.append(" WHEN 'P' THEN (EXME_ORDERSETPROD.WEIGHTMAX * 0.4536) ")
				.append(" ELSE EXME_ORDERSETPROD.WEIGHTMAX ")
				.append(" END) as WPMax ")
				.append(" FROM EXME_OrderSetProd ")
				.append(" WHERE EXME_OrderSetProd.EXME_OrderSet_ID = ? ")
//				.append(" AND EXME_OrderSetProd.M_Product_ID      IS NULL  ")
				.append(" AND EXME_OrderSetProd.EXME_GenProduct_ID > 0  ")
				.append(" AND EXME_OrderSetProd.ProductType        = ?  ")
				.append(" AND EXME_OrderSetProd.isActive           = 'Y'  ")
				.append(" and EXME_OrderSetProd.isweightrank = 'Y')  info ")
				.append(" WHERE (( WPMin < ? ) AND (WPMax >  ? ))  ")
				.append(" ORDER BY Created  ");
			double weight = Double.valueOf(String.valueOf(MEXMESignoVitalDet.getPesoPatient(ctx, patient.get_ID())));
			if (MUser.SISTEMAMEDICION_EnglishSystem.equals(MUser.getSistMedicionUsuario(ctx))) {
				weight = weight * 0.4536;
			}
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setInt(1, orderSetID);
			pstmt.setString(2, MEXMEOrderSetProd.PRODUCTTYPE_Medication);
			pstmt.setDouble(3, weight);
			pstmt.setDouble(4, weight);

			result = pstmt.executeQuery();
			while (result.next()) {
				boolean add = true;
				MEXMEOrderSetProd newProd = new MEXMEOrderSetProd(ctx, result, null);
				for (MEXMEOrderSetProd ordSetProd : array) {
					if (ordSetProd.getEXME_GenProduct_ID() == newProd.getEXME_GenProduct_ID()) {
						if (newProd.getEdadFinal() != 0 || newProd.getEdadInicial() != 0 || newProd.getMesFinal() != 0 || newProd.getMesInicial() != 0) {
							array.remove(ordSetProd);
						} else {
							add = false;
						}
						break;
					}
				}
				if (add) {
					array.add(new MEXMEOrderSetProd(ctx, result, null));
				}
			}
		} catch (SQLException e) {
			log.log(Level.SEVERE, e.getLocalizedMessage());
		} finally {
			DB.close(result, pstmt);
		}

		try {
			 sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		     sql.append(" SELECT * FROM EXME_ORDERSETPROD WHERE EXME_ORDERSETPROD_ID IN (  ")
			    .append("SELECT EXME_ORDERSETPROD.EXME_ORDERSETPROD_ID ")
				.append("FROM EXME_OrderSetProd ")
				.append("WHERE EXME_OrderSetProd.EXME_OrderSet_ID = ? ")
//				.append("AND EXME_OrderSetProd.M_Product_ID IS NULL ")
				.append("AND EXME_OrderSetProd.EXME_GenProduct_ID > 0 ")
				.append("AND EXME_OrderSetProd.ProductType = ? ")
				.append("AND EXME_OrderSetProd.isActive = 'Y' ")
				.append("and EXME_OrderSetProd.isweightrank = 'N'  ")
				.append("AND ((EXME_OrderSetProd.edadFinal = 0 AND EXME_OrderSetProd.edadInicial = 0 AND EXME_OrderSetProd.mesInicial = 0 AND EXME_OrderSetProd.mesFinal = 0) ")
				.append("OR ((EXME_OrderSetProd.edadInicial < ? AND EXME_OrderSetProd.edadFinal > ?) ")
				.append("OR ((EXME_OrderSetProd.edadInicial = ? AND EXME_OrderSetProd.mesInicial <= ? AND edadinicial <> edadfinal) ")
				.append("OR (EXME_OrderSetProd.edadFinal = ? AND EXME_OrderSetProd.mesFinal >= ? AND edadinicial <> edadfinal) ")
				.append("OR (EXME_OrderSetProd.edadInicial = ? AND EXME_OrderSetProd.edadFinal = ? ")
				.append("AND EXME_OrderSetProd.mesInicial <= ? AND EXME_OrderSetProd.mesFinal >= ?))))")
				.append(DB.isPostgreSQL()? " except" : " minus ")
				.append(" select EXME_ORDERSETPROD_ID from EXME_OrderSetProd ")
				.append(" where isweightrank = 'Y' AND  EXME_OrderSet_ID = ?)")
				
				.append(" ORDER BY Created ");
			
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setInt(1, orderSetID);
			pstmt.setString(2, MEXMEOrderSetProd.PRODUCTTYPE_Medication);
			pstmt.setInt(3, edad.getAnios());
			pstmt.setInt(4, edad.getAnios());
			pstmt.setInt(5, edad.getAnios());
			pstmt.setInt(6, edad.getMeses());
			pstmt.setInt(7, edad.getAnios());
			pstmt.setInt(8, edad.getMeses());
			pstmt.setInt(9, edad.getAnios());
			pstmt.setInt(10, edad.getAnios());
			pstmt.setInt(11, edad.getMeses());
			pstmt.setInt(12, edad.getMeses());
			pstmt.setInt(13, orderSetID);

			result = pstmt.executeQuery();
			while (result.next()) {
				boolean add = true;
				MEXMEOrderSetProd newProd = new MEXMEOrderSetProd(ctx, result, null);
				for (MEXMEOrderSetProd ordSetProd : array) {
					if (ordSetProd.getEXME_GenProduct_ID() == newProd.getEXME_GenProduct_ID()) {
						if (newProd.getEdadFinal() != 0 || newProd.getEdadInicial() != 0 || newProd.getMesFinal() != 0 || newProd.getMesInicial() != 0) {
							array.remove(ordSetProd);
						} else {
							add = false;
						}
						break;
					}
				}
				if (add) {
					array.add(new MEXMEOrderSetProd(ctx, result, null));
				}
			}
		} catch (SQLException e) {
			log.log(Level.SEVERE, e.getLocalizedMessage());
		} finally {
			DB.close(result, pstmt);
		}

		return array;
	}
	
	/**
	 * Obtiene los productos(procedimientos) del order set filtrando por la edad del paciente 
	 * @param ctx
	 * @param orderSetID
	 * @param patient
	 * @return List<MProduct>
	 */
	public static List<MProduct> getProceduresProd(final Properties ctx, final int orderSetID, MEXMEPaciente patient){

		List<MProduct> array = new ArrayList<MProduct>();
//		List<MEXMEOrderSetProd> orderSet = new ArrayList<MEXMEOrderSetProd>();
		
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		PreparedStatement pstmt = null;
		ResultSet result = null;
		final CalcularEdadAMD edad = CalcularEdadAMD.getEdad(ctx,Constantes.getSdfFecha().format(patient.getFechaNac()));
		try {
			sql.append("SELECT EXME_OrderSetProd.* ")
				.append("FROM EXME_OrderSetProd ")
				.append("WHERE EXME_OrderSetProd.EXME_OrderSet_ID = ? ")
				.append("AND EXME_OrderSetProd.M_Product_ID > 0 ")
				.append("AND EXME_OrderSetProd.EXME_GenProduct_ID IS NULL ")
				.append("AND EXME_OrderSetProd.isActive = 'Y' ")
				.append("AND EXME_OrderSetProd.ProductType = ? ")
				.append("AND ((EXME_OrderSetProd.edadFinal = 0 AND EXME_OrderSetProd.edadInicial = 0 AND EXME_OrderSetProd.mesInicial = 0 AND EXME_OrderSetProd.mesFinal = 0) ")
				.append("OR ((EXME_OrderSetProd.edadInicial < ? AND EXME_OrderSetProd.edadFinal > ?) ")
				.append("OR ((EXME_OrderSetProd.edadInicial = ? AND EXME_OrderSetProd.mesInicial <= ? AND edadinicial <> edadfinal) ")
				.append("OR (EXME_OrderSetProd.edadFinal = ? AND EXME_OrderSetProd.mesFinal >= ? AND edadinicial <> edadfinal) ")
				.append("OR (EXME_OrderSetProd.edadInicial = ? AND EXME_OrderSetProd.edadFinal = ? ")
				.append("AND EXME_OrderSetProd.mesInicial <= ? AND EXME_OrderSetProd.mesFinal >= ?))))")
				.append(" ORDER BY EXME_OrderSetProd.Created ");
			
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setInt(1, orderSetID);
			pstmt.setString(2, MEXMEOrderSetProd.PRODUCTTYPE_Procedure);
			pstmt.setInt(3, edad.getAnios());
			pstmt.setInt(4, edad.getAnios());
			pstmt.setInt(5, edad.getAnios());
			pstmt.setInt(6, edad.getMeses());
			pstmt.setInt(7, edad.getAnios());
			pstmt.setInt(8, edad.getMeses());
			pstmt.setInt(9, edad.getAnios());
			pstmt.setInt(10, edad.getAnios());
			pstmt.setInt(11, edad.getMeses());
			pstmt.setInt(12, edad.getMeses());
			
			result = pstmt.executeQuery();
			while (result.next()) {
				boolean add = true;
				MProduct newProd = new MProduct(ctx, result.getInt(COLUMNNAME_M_Product_ID), null);
				newProd.setAnioFinal(result.getInt(COLUMNNAME_EdadFinal));
				newProd.setAnioInicial(result.getInt(COLUMNNAME_EdadInicial));
				newProd.setMesFinal(result.getInt(COLUMNNAME_MesFinal));
				newProd.setMesInicial(result.getInt(COLUMNNAME_MesInicial));
				for(MProduct ordSetProd: array){
					if(ordSetProd.getM_Product_ID() == newProd.getM_Product_ID()) {
						if (newProd.getAnioFinal() != 0 || newProd.getAnioInicial() != 0 || newProd.getMesFinal() != 0 || newProd.getMesInicial() != 0) {
							array.remove(ordSetProd);
						} else {
							add = false;
						}
						break;
					}
				}
				if(add) {
					array.add(newProd);
				}
			}
		} catch (SQLException e) {
			log.log(Level.SEVERE, e.getLocalizedMessage());
		} finally {
			DB.close(result, pstmt);
		}

		return array;
	}
	
	/**
	 * Obtiene los productos(servicios) del order set filtrando por la edad del paciente 
	 * @param ctx
	 * @param orderSetID
	 * @param patient
	 * @return List<MProduct>
	 */
	public static List<MProduct> getProductsProd(final Properties ctx, final int orderSetID, MEXMEPaciente patient){

		List<MProduct> array = new ArrayList<MProduct>();
//		List<MEXMEOrderSetProd> orderSet = new ArrayList<MEXMEOrderSetProd>();
		
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		PreparedStatement pstmt = null;
		ResultSet result = null;
		final CalcularEdadAMD edad = CalcularEdadAMD.getEdad(ctx,Constantes.getSdfFecha().format(patient.getFechaNac()));
		try {
			sql.append("SELECT EXME_OrderSetProd.* ")
				.append("FROM EXME_OrderSetProd ")
				.append("WHERE EXME_OrderSetProd.EXME_OrderSet_ID = ? ")
				.append("AND EXME_OrderSetProd.M_Product_ID > 0 ")
				.append("AND EXME_OrderSetProd.EXME_GenProduct_ID IS NULL ")
				.append("AND EXME_OrderSetProd.isActive = 'Y' ")
				.append("AND (EXME_OrderSetProd.isEstudio = 'Y' OR ProductType = ? OR ProductType = ?) ")
				.append("AND ((EXME_OrderSetProd.edadFinal = 0 AND EXME_OrderSetProd.edadInicial = 0 AND EXME_OrderSetProd.mesInicial = 0 AND EXME_OrderSetProd.mesFinal = 0) ")
				.append("OR ((EXME_OrderSetProd.edadInicial < ? AND EXME_OrderSetProd.edadFinal > ?) ")
				.append("OR ((EXME_OrderSetProd.edadInicial = ? AND EXME_OrderSetProd.mesInicial <= ? AND edadinicial <> edadfinal) ")
				.append("OR (EXME_OrderSetProd.edadFinal = ? AND EXME_OrderSetProd.mesFinal >= ? AND edadinicial <> edadfinal) ")
				.append("OR (EXME_OrderSetProd.edadInicial = ? AND EXME_OrderSetProd.edadFinal = ? ")
				.append("AND EXME_OrderSetProd.mesInicial <= ? AND EXME_OrderSetProd.mesFinal >= ?))))")
				.append(" ORDER BY EXME_OrderSetProd.Created ");
			
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setInt(1, orderSetID);
			pstmt.setString(2, MEXMEOrderSetProd.PRODUCTTYPE_Service);
			pstmt.setString(3, MEXMEOrderSetProd.PRODUCTTYPE_Studies);
			pstmt.setInt(4, edad.getAnios());
			pstmt.setInt(5, edad.getAnios());
			pstmt.setInt(6, edad.getAnios());
			pstmt.setInt(7, edad.getMeses());
			pstmt.setInt(8, edad.getAnios());
			pstmt.setInt(9, edad.getMeses());
			pstmt.setInt(10, edad.getAnios());
			pstmt.setInt(11, edad.getAnios());
			pstmt.setInt(12, edad.getMeses());
			pstmt.setInt(13, edad.getMeses());
			
			result = pstmt.executeQuery();
			while(result.next()){
				boolean add = true;
				MProduct newProd = new MProduct(ctx, result.getInt(COLUMNNAME_M_Product_ID), null);
				newProd.setAnioFinal(result.getInt(COLUMNNAME_EdadFinal));
				newProd.setAnioInicial(result.getInt(COLUMNNAME_EdadInicial));
				newProd.setMesFinal(result.getInt(COLUMNNAME_MesFinal));
				newProd.setMesInicial(result.getInt(COLUMNNAME_MesInicial));
				for(MProduct ordSetProd: array){
					if(ordSetProd.getM_Product_ID() == newProd.getM_Product_ID()) {
						if (newProd.getAnioFinal() != 0 || newProd.getAnioInicial() != 0 || newProd.getMesFinal() != 0 || newProd.getMesInicial() != 0) {
							array.remove(ordSetProd);
						} else {
							add = false;
						}
						break;
					}
				}
				if(add) {
					array.add(newProd);
				}
			}
		} catch (SQLException e) {
			log.log(Level.SEVERE, e.getLocalizedMessage());
		} finally {
			DB.close(result, pstmt);
		}

		return array;
	}
	
	/**
	 * Return route name from either EXME_Route or EXME_ViasAdministracion<br>
	 * Ticket 1000187
	 * 
	 * @return
	 * @author Lorena Lama
	 */
	public String getRouteName() {
		final String lblRouteAdm;
		if (getEXME_Route_ID() > 0 && getEXME_ViasAdministracion_ID() <= 0) {
			lblRouteAdm = getEXME_Route().getDescription1();
		} else if (getEXME_ViasAdministracion_ID() > 0) {
			lblRouteAdm = getEXME_ViasAdministracion().getName();
		} else {
			lblRouteAdm = StringUtils.EMPTY;
		}
		return lblRouteAdm;
	}

	public void setNew(boolean isNew) {
		this.isNew = isNew;
	}

	public boolean isNew() {
		return isNew;
	}

}
