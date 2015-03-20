/**
 *
 */
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;

import org.apache.commons.lang.StringUtils;
import org.compiere.model.bpm.GetPrice;
import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.compiere.util.QuickSearchTables;
import org.compiere.util.Trx;

/**
 * @author twry
 *
 */
public class MEXMEProductoOrg extends X_EXME_ProductoOrg {

	/** serialVersionUID */
	private static final long serialVersionUID = -2560810649383763310L;
	/** Static Logger */
	private static CLogger slog = CLogger.getCLogger(MEXMEProductoOrg.class);
	/** Object Revenue Codes */
	private MEXMERevenueCodes revenueCodes = null;
	/** Object product */
	private MProduct product = null;
	/** Precio */
	private BigDecimal precio = null;
	/** ValidFrom */
	private Timestamp validFrom = null;
	/** Nivel de CPT para definir es CPT o HCPCS */
	private String hcpcsLevel = null;
	/** Relacion precio - producto (USA) */
	private MEXMEProductoPrecio productoPrecio;
	/** Objeto Modificador 1 */
	private MEXMEModifiers modifier1 = null;
	/** Objeto Modificador 2 */
	private MEXMEModifiers modifier2 = null;
	/** Objeto Modificador 3 */
	private MEXMEModifiers modifier3 = null;
	/** Objeto Modificador 4 */
	private MEXMEModifiers modifier4 = null;
	/** Nombre de CPT */
	private String intervencionName = null;

	/**
	 * Constructor
	 *
	 * @param ctx
	 * @param EXME_ProductoOrg_ID
	 * @param trxName
	 */
	public MEXMEProductoOrg(final Properties ctx, final int pProdOrgID,
			final String trxName) {
		super(ctx, pProdOrgID, trxName);
	}

	/**
	 * Constructor
	 *
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MEXMEProductoOrg(final Properties ctx, final ResultSet rSet,
			final String trxName) {
		super(ctx, rSet, trxName);
	}

	/**
	 * Before Save
	 *
	 * @param newRecord
	 *            new
	 * @return true
	 */
	protected boolean beforeSave(final boolean newRecord) {

		if (getAD_Client_ID() > 0 && getAD_Org_ID() <= 0) {
			log.saveError("Error",
					Msg.parseTranslation(getCtx(), "@Org0NotAllowed@"));//
			return false;
		}

		return true;
	} // beforeSave

	/**
	 * After Save
	 *
	 * @param newRecord
	 *            new
	 * @param success
	 *            success
	 * @return success
	 */
	protected boolean afterSave(final boolean newRecord, final boolean success) {
		if (success) {
			try {

				if (newRecord
						|| is_ValueChanged("IsActive")
						|| is_ValueChanged(COLUMNNAME_AD_Org_ID)
						|| is_ValueChanged(COLUMNNAME_M_Product_ID)
						|| is_ValueChanged(COLUMNNAME_Unused)
						|| is_ValueChanged(COLUMNNAME_IsObsolete)) {
					MEXMEProductV.checkIndexByProductOrg(getCtx(), get_ID(), get_TrxName());
				}

				
				if (newRecord
						|| is_ValueChanged("IsActive")//
						|| is_ValueChanged("AD_Org_ID")
						|| is_ValueChanged("M_Product_ID")
						|| is_ValueChanged(X_EXME_ProductoOrg.COLUMNNAME_IsFormulary)) {

					// Actualiza el GenProduct
					MEXMEGenProduct.actualizarEXMETGenProdTrade(getCtx(),
							getM_Product_ID(), getAD_Org_ID(), newRecord, // REV
																			// almacen
																			// que
							// se esta
							// enviando
							isActive(), getAD_Org_ID() > 0, isFormulary(), get_TrxName());
				}
				rebuild(getCtx());
				// New - Acct
				if (newRecord && getAD_Client_ID()>0) {
					getProduct().insertAccounting(getAD_Client_ID());
				}
			} catch (Exception ex) {
				log.log(Level.WARNING, "QuickSearchTables.checkTables", ex);
			}
		}
		return success;
	}

	/**
	 * Reconstrucciones de indices relacionados a EXME_ProductoOrg
	 * 
	 * @param ctx
	 *            Contexto de la app
	 */
	public static void rebuild(Properties ctx) {
		QuickSearchTables.rebuildAll(MProduct.Table_Name + "@PSol*", ctx);
		QuickSearchTables.rebuildAll(MProduct.Table_Name + "@ProdChar*", ctx);
		QuickSearchTables.rebuildAll(MProduct.Table_Name + "@ChgMast*", ctx);
	}

	/**
	 * Actualiza la busqueda del producto
	 */
	public void checkTable() {
		QuickSearchTables.checkTables(MProduct.class, MProduct.Table_Name,
				getM_Product_ID(), get_TrxName(), p_ctx);
	}

	/**
	 * after Delete
	 *
	 * @param success
	 *            success
	 */
	protected boolean afterDelete(final boolean success) {

		if (success) {
			try {
				MEXMEProductV.checkIndexByProductOrg(getCtx(), getEXME_ProductoOrg_ID(), get_TrxName());
				
				rebuild(getCtx());
				
				if (getAD_Org_ID() > 0) {
					MEXMEGenProduct.actualizarEXMETGenProdTrade(getCtx(),
							getM_Product_ID(), getAD_Org_ID(), false,
							isActive(), getAD_Org_ID() > 0, isFormulary(), get_TrxName());
				}
			} catch (Exception e) {
				log.log(Level.WARNING, "Exception", e);
			}
		}
		return success;
	}

	/**
	 * Busqueda del formulario.
	 *
	 * @param ctx
	 *            Contexto
	 * @param trxName
	 *            Nombre de la transaccion
	 * @return Listado de objetos <MProduct>
	 */
//	public static List<MProduct> getFormulary(final Properties ctx,
//			final String trxName) {
//
//		final List<MProduct> retValue = new ArrayList<MProduct>();
//		final StringBuilder sql = new StringBuilder(
//				Constantes.INIT_CAPACITY_ARRAY);
//		sql.append(" SELECT m_product.* ");
//		sql.append(" FROM   m_product ");
//		sql.append(" INNER JOIN EXME_ProductoOrg ON m_product.m_product_id = EXME_ProductoOrg.m_product_id ");
//		sql.append(" WHERE EXME_ProductoOrg.IsActive = 'Y'  ");
//		sql.append(" AND TRIM(m_product.ProductClass) = '"
//				+ MProduct.PRODUCTCLASS_Drug.trim() + "' ");
//		sql.append(" AND EXME_ProductoOrg.isformulary = 'Y' ");
//		sql.append(" AND EXME_ProductoOrg.AD_Org_ID = ? ");
//		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ",
//				X_M_Product.Table_Name));// nivel organiozacion
//		sql.append(" ORDER BY UPPER(m_product.description) ");
//
//		PreparedStatement pstmt = null;
//		ResultSet rSet = null;
//		try {
//			pstmt = DB.prepareStatement(sql.toString(), trxName);
//			pstmt.setInt(1, Env.getContextAsInt(ctx, "#AD_Org_ID"));
//			rSet = pstmt.executeQuery();
//
//			while (rSet.next()) {
//				retValue.add(new MProduct(ctx, rSet, trxName));
//			}
//
//		} catch (Exception e) {
//			slog.log(Level.SEVERE, e.getMessage(), e);
//		} finally {
//			DB.close(rSet, pstmt);
//		}
//		return retValue;
//	}

	/**
	 * get Obj MEXMEProductoOrg
	 *
	 * @param ctx
	 * @param trxName
	 * @return <MEXMEProductoOrg>
	 */
	public static MEXMEProductoOrg getObj(final Properties ctx,
			final String value, final String trxName) {

		MEXMEProductoOrg retValue = null;
		final StringBuilder sql = new StringBuilder(
				Constantes.INIT_CAPACITY_ARRAY);
		sql.append(" SELECT EXME_ProductoOrg.*");
		sql.append(" FROM m_product ");
		sql.append(" INNER JOIN EXME_ProductoOrg  ");
		sql.append(" ON m_product.m_product_id        = EXME_ProductoOrg.m_product_id  ");
		sql.append(" WHERE EXME_ProductoOrg.IsActive  = 'Y' ");
		sql.append(" AND TRIM(m_product.ProductClass) ='"
				+ MProduct.PRODUCTCLASS_Drug.trim() + "' ");
		sql.append(" AND m_product.value = ?");
		sql.append(" AND EXME_ProductoOrg.AD_Org_ID = ? ");
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ",
				X_EXME_ProductoOrg.Table_Name));

		PreparedStatement pstmt = null;
		ResultSet rSet = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setString(1, value);
			pstmt.setInt(2, Env.getAD_Org_ID(ctx));
			rSet = pstmt.executeQuery();

			if (rSet.next()) {
				retValue = new MEXMEProductoOrg(ctx, rSet, trxName);
			}

		} catch (Exception e) {
			slog.log(Level.SEVERE, e.getMessage(), e);
		} finally {
			DB.close(rSet, pstmt);
		}
		return retValue;
	}

	/**
	 * get modifiers int
	 *
	 * @param ctx
	 * @param trxName
	 * @return <Integer>
	 */
	public static List<Integer> getModifiers(final Properties ctx,
			final int productOrgID, final String trxName) {

		final List<Integer> retValue = new ArrayList<Integer>();
		final StringBuilder sql = new StringBuilder(
				Constantes.INIT_CAPACITY_ARRAY);
		sql.append(" SELECT EXME_Modifier_ID ").append(" FROM  ( ")
				.append(" SELECT exme_modifier1_id exme_modifier_id ")
				.append(" FROM exme_productoorg ")
				.append(" WHERE m_product_id = ? ").append(" UNION ALL ")
				.append(" SELECT exme_modifier2_id ")
				.append(" FROM exme_productoorg ")
				.append(" WHERE m_product_id = ? ").append(" UNION ALL ")
				.append(" SELECT exme_modifier3_id ")
				.append(" FROM exme_productoorg ")
				.append(" WHERE m_product_id = ? ").append(" UNION ALL ")
				.append(" SELECT exme_modifier4_id ")
				.append(" FROM exme_productoorg ")
				.append(" WHERE m_product_id = ?) ");

		if (DB.isPostgreSQL()) {
			sql.append("as prodOrg");
		}

		sql.append(" WHERE EXME_Modifier_ID IS NOT NULL ");
		// sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ",
		// X_EXME_ProductoOrg.Table_Name));

		PreparedStatement pstmt = null;
		ResultSet rSet = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, productOrgID);
			pstmt.setInt(2, productOrgID);
			pstmt.setInt(3, productOrgID);
			pstmt.setInt(4, productOrgID);
			rSet = pstmt.executeQuery();

			while (rSet.next()) {
				retValue.add(rSet.getInt("EXME_Modifier_ID"));
			}

		} catch (Exception e) {
			slog.log(Level.SEVERE, e.getMessage(), e);
		} finally {
			DB.close(rSet, pstmt);
		}
		return retValue;
	}

	/**
	 * Validacion para solicitar el lote del producto
	 *
	 * @param ctx
	 * @param M_Product_ID
	 * @return
	 */
	public static boolean isLot(final Properties ctx, final int M_Product_ID) {
		final StringBuilder sql = new StringBuilder(
				Constantes.INIT_CAPACITY_ARRAY);
		sql.append("SELECT po.ISLOT          ");
		sql.append("FROM   EXME_ProductoOrg po ");
		sql.append("WHERE  po.isActive='Y'    ");
		sql.append("AND    po.M_Product_ID=? ");// #1
		sql.append(MEXMELookupInfo
				.addAccessLevelSQL(ctx, " ", Table_Name, "po"));

		final String value = DB.getSQLValueString(null, sql.toString(),
				M_Product_ID);
		return DB.TO_BOOLEAN(value);
	}

	/**
	 * saber si el generico ya existe En Formulario
	 *
	 * @param ctx
	 * @param trxName
	 * @return <boolean> true: existe false: no existe
	 */
	public static boolean existeEnFormulario(final Properties ctx,
			final int idGenProduct, final String trxName) {

		boolean retValue = false;
		final StringBuilder sql =
			new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);

		sql.append("SELECT EXME_ProductoOrg.* \n");
		sql.append("FROM   EXME_ProductoOrg \n");
		sql.append("  INNER  JOIN M_Product \n");
		sql.append("    ON EXME_ProductoOrg.m_product_id = M_Product.m_product_id \n");
		sql.append("WHERE  EXME_ProductoOrg.IsActive     = 'Y' \n");
		sql.append("  "); //just to give some lebensraum ;)
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ",X_EXME_ProductoOrg.Table_Name)).append('\n');
		sql.append("  AND    EXME_ProductoOrg.AD_Org_ID    = ? \n");// #1
		sql.append("  AND    EXME_ProductoOrg.isformulary  = 'Y' \n");
		sql.append("  AND    M_Product.IsActive            = 'Y' \n");
		sql.append("  AND    TRIM(M_Product.productclass)  =  ? \n");
		sql.append("  AND    M_Product.exme_genproduct_id  = ?");// #2

		PreparedStatement pstmt = null;
		ResultSet rSet = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, Env.getAD_Org_ID(ctx));
			pstmt.setString(2, X_M_Product.PRODUCTCLASS_Drug.trim());
			pstmt.setInt(3, idGenProduct);

			rSet = pstmt.executeQuery();

			if (rSet.next()) {
				retValue = true;
			}

		} catch (Exception e) {
			slog.log(Level.SEVERE, "SQL : " + sql, e);
		} finally {
			DB.close(rSet, pstmt);
		}
		return retValue;
	}

	/**
	 * Busqueda del formulario.
	 *
	 * @param ctx
	 *            Contexto
	 * @param trxName
	 *            Nombre de la transaccion
	 * @return Listado de objetos <MProduct>
	 */
	public static List<MEXMEGenProduct> getFormulary(final Properties ctx) {

		final List<MEXMEGenProduct> retValue = new ArrayList<MEXMEGenProduct>();
		final StringBuilder sql = new StringBuilder(
				Constantes.INIT_CAPACITY_ARRAY);
		sql.append("SELECT gp.*, sub.ProdVal, sub.M_Product_ID \n");
		sql.append("FROM EXME_GenProduct gp \n");
		sql.append("  INNER  JOIN ( \n");
		sql.append("    SELECT EXME_GenProduct.EXME_GenProduct_ID, M_Product.Value AS ProdVal, M_Product.M_Product_ID \n");
		sql.append(" 	FROM   M_Product \n");
		sql.append(" 	INNER  JOIN EXME_ProductoOrg ON       M_Product.m_product_id = EXME_ProductoOrg.m_product_id \n");
		sql.append(" 	INNER  JOIN EXME_GenProduct  ON M_Product.EXME_GenProduct_id = EXME_GenProduct.EXME_GenProduct_id \n");
		sql.append(" 	WHERE  M_Product.IsActive = 'Y' \n");
		sql.append("    "); //just to give some lebensraum ;)
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ",X_M_Product.Table_Name)).append('\n');
		sql.append("      AND    TRIM(M_Product.ProductClass) = ? \n");
		sql.append("      AND    EXME_ProductoOrg.IsActive    = 'Y' \n");
		sql.append("	  AND    EXME_ProductoOrg.isformulary = 'Y' \n");
		sql.append(" 	  AND    EXME_ProductoOrg.AD_Org_ID   = ? \n");
		sql.append("    GROUP BY EXME_GenProduct.EXME_GenProduct_ID, M_Product.Value, M_Product.M_Product_ID \n");
		sql.append("  ) sub ON gp.EXME_GenProduct_ID = sub.EXME_GenProduct_ID \n");
		sql.append("ORDER BY UPPER(gp.generic_product_name) ");

		PreparedStatement pstmt = null;
		ResultSet rSet = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setString(1, MProduct.PRODUCTCLASS_Drug.trim());
			pstmt.setInt(2, Env.getContextAsInt(ctx, "#AD_Org_ID"));
			rSet = pstmt.executeQuery();

			while (rSet.next()) {
				MEXMEGenProduct genProduct = new MEXMEGenProduct(ctx, rSet, null);
				genProduct.setNdc(rSet.getString("ProdVal"));
				genProduct.setKey(rSet.getInt("M_Product_ID"));
				retValue.add(genProduct);
			}

		} catch (Exception e) {
			slog.log(Level.SEVERE, "SQL : " + sql, e);
		} finally {
			DB.close(rSet, pstmt);
		}
		return retValue;
	}

	/**
	 * get Obj MEXMEProductoOrg
	 * @param ctx
	 * @param trxName
	 * @return <MEXMEProductoOrg>
	 */
	public static MEXMEProductoOrg getObj(Properties ctx, int id, String trxName) {

		MEXMEProductoOrg retValue = null;
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append("SELECT EXME_ProductoOrg.* \n");
		sql.append("FROM m_product ");
		sql.append("  INNER JOIN EXME_ProductoOrg \n");
		sql.append("    ON m_product.m_product_id = EXME_ProductoOrg.m_product_id \n");
		sql.append("WHERE EXME_ProductoOrg.IsActive  = 'Y' \n");
		sql.append("  AND EXME_ProductoOrg.m_product_id = ? \n");
		sql.append("  AND EXME_ProductoOrg.AD_Org_ID = ? \n");
		sql.append("  "); //just to give some lebensraum ;)
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", X_EXME_ProductoOrg.Table_Name)).append('\n');
		sql.append("  AND TRIM(m_product.ProductClass) = ? ");

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, id);
			pstmt.setInt(2, Env.getAD_Org_ID(ctx));
			pstmt.setString(3, MProduct.PRODUCTCLASS_Drug.trim());
			rs = pstmt.executeQuery();

			if (rs.next()) {
				retValue = new MEXMEProductoOrg(ctx, rs, trxName);
			}

		} catch (Exception e) {
			slog.log(Level.SEVERE, e.getMessage(), e);
		} finally {
			DB.close(rs, pstmt);
		}
		return retValue;
	}

	/**
	 * Busqueda del formulario.
	 *
	 * @param ctx
	 *            Contexto
	 * @param trxName
	 *            Nombre de la transaccion
	 * @return Listado de objetos <MProduct>
	 */
	public static long getNumReg(final Properties ctx,
			final String trxName) {

		long  retValue = 0;
		final StringBuilder sql =
			new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);

		sql.append(" SELECT count(*) AS num ");
		sql.append(" FROM   EXME_ProductoOrg ");
		sql.append(" WHERE  EXME_ProductoOrg.IsActive = 'Y'  ");
		sql.append(" AND    EXME_ProductoOrg.AD_Org_ID = ? ");
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ",
				X_EXME_ProductoOrg.Table_Name));// nivel organiozacion

		PreparedStatement pstmt = null;
		ResultSet rSet = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, Env.getContextAsInt(ctx, "#AD_Org_ID"));
			rSet = pstmt.executeQuery();

			if (rSet.next()) {
				retValue = rSet.getLong(1);
			}
		} catch (Exception e) {
			slog.log(Level.SEVERE, e.getMessage(), e);
		} finally {
			DB.close(rSet, pstmt);
		}
		return retValue;
	}

	/**************************************************************/
	public MEXMERevenueCodes getRevenueCodes() {
		if (revenueCodes == null) {
			revenueCodes = new MEXMERevenueCodes(getCtx(),
					getEXME_RevenueCode_ID(), get_TrxName());
		}
		return this.revenueCodes;
	}

	public void setRevenueCodes(final MEXMERevenueCodes revenueCodes) {
		this.revenueCodes = revenueCodes;
	}

	public MProduct getProduct() {
		if (product == null) {
			product = new MProduct(getCtx(), getM_Product_ID(), get_TrxName());
		}
		return this.product;
	}

	public void setProduct(final MProduct pproduct) {
		this.product = pproduct;
	}

	public void setPrecio(final BigDecimal bigDecimal) {
		precio = bigDecimal;
	}

	public BigDecimal getPrecio() {
		return precio;
	}

	public Timestamp getValidFrom() {
		return validFrom;
	}

	public void setValidFrom(final Timestamp timestamp) {
		validFrom = timestamp;
	}

	/**
	 * Precio del chargemaster usa
	 * @return
	 */
	public MEXMEProductoPrecio getProductoPrecio() {
		if (productoPrecio == null) {
			productoPrecio = GetPrice.calcularPrecio(getCtx(),
					getM_Product_ID(), null);
			if (productoPrecio == null) {
				productoPrecio = new MEXMEProductoPrecio(getCtx(), 0, null);
			}
		}
		return productoPrecio;
	}

	public void setProductoPrecio(final MEXMEProductoPrecio productoPrecio) {
		this.productoPrecio = productoPrecio;
	}

	public MEXMEModifiers getModifier1() {
		if (getEXME_Modifier1_ID() > 0) {
			if (modifier1 == null) {
				modifier1 = new MEXMEModifiers(getCtx(),
						getEXME_Modifier1_ID(), null);
			}
		} else {
			modifier1 = null;
		}
		return modifier1;
	}

	public void setModifier1(final MEXMEModifiers modifier1) {
		this.modifier1 = modifier1;
	}

	public MEXMEModifiers getModifier2() {
		if (getEXME_Modifier2_ID() > 0) {
			if (modifier2 == null) {
				modifier2 = new MEXMEModifiers(getCtx(),
						getEXME_Modifier2_ID(), null);
			}
		} else {
			modifier2 = null;
		}
		return modifier2;
	}

	public void setModifier2(final MEXMEModifiers modifier2) {
		this.modifier2 = modifier2;
	}

	public MEXMEModifiers getModifier3() {
		if (getEXME_Modifier3_ID() > 0) {
			if (modifier3 == null) {
				modifier3 = new MEXMEModifiers(getCtx(),
						getEXME_Modifier3_ID(), null);
			}
		} else {
			modifier3 = null;
		}
		return modifier3;
	}

	public void setModifier3(final MEXMEModifiers modifier3) {
		this.modifier3 = modifier3;
	}

	public MEXMEModifiers getModifier4() {
		if (getEXME_Modifier4_ID() > 0) {
			if (modifier4 == null) {
				modifier4 = new MEXMEModifiers(getCtx(),
						getEXME_Modifier4_ID(), null);
			}
		} else {
			modifier4 = null;
		}
		return modifier4;
	}

	public void setModifier4(final MEXMEModifiers modifier4) {
		this.modifier4 = modifier4;
	}

	public String getIntervencionName() {
		return intervencionName;
	}

	public void setIntervencionName(final String string) {
		intervencionName = string;

	}

	public String getHcpcsLevel() {
		return hcpcsLevel;
	}

	public void setHcpcsLevel(final String pHcpcsLevel) {
		this.hcpcsLevel = pHcpcsLevel;
	}

	/**
	 * get list modifiers int
	 *
	 * @return  List<Integer>
	 */
	public List<Integer> getModifiers() {
		List<Integer> lst = new ArrayList<Integer>();
		if(getEXME_Modifier1_ID()>0){
			lst.add(getEXME_Modifier1_ID());
		}
		if(getEXME_Modifier2_ID()>0){
			lst.add(getEXME_Modifier2_ID());
		}
		if(getEXME_Modifier3_ID()>0){
			lst.add(getEXME_Modifier3_ID());
		}
		if(getEXME_Modifier4_ID()>0){
			lst.add(getEXME_Modifier4_ID());
		}
		return lst;
	}

	/**
	 * busca los Precios
	 *
	 * @param productSql
	 */

	public static Map<Integer, MEXMEProductoOrg> buscarPrecios(final String productSql) {
		final Map<Integer, MEXMEProductoOrg> lstMapa = new HashMap<Integer, MEXMEProductoOrg>();

		if (productSql != null && productSql.length() > 1) {
			//lstMapa.clear();

			final StringBuilder sql = new StringBuilder();
			sql.append("SELECT DISTINCT po.M_Product_ID, pr.ValidFrom, pr.PRICESTD, i.Value AS Inter, i.HCPCS_Level, po.* \n")
			.append("FROM EXME_ProductoOrg po \n")
			.append("  INNER JOIN M_Product p ON po.M_Product_ID = p.M_Product_ID \n")
			.append("  LEFT JOIN EXME_Intervencion i ON p.EXME_Intervencion_ID = i.EXME_Intervencion_ID \n")
			.append("  LEFT JOIN (\n")
			.append("    SELECT pp.M_Product_ID, pp.ValidFrom, pp.PRICESTD \n")
			.append("    FROM EXME_ProductoPrecio pp \n")
					//
			.append("      INNER JOIN (\n")
			.append("        SELECT M_PRODUCT_ID AS product, COUNT(*) AS numreg, MAX( VALIDFROM ) as VALID\n")
			.append("        FROM ( \n")
			.append("          SELECT EXME_PRODUCTOPRECIO_id,  M_PRODUCT_ID, VALIDFROM\n ")
			.append("          FROM EXME_PRODUCTOPRECIO \n ")
			.append("          WHERE VALIDFROM <= ").append(DB.TO_DATE(DB.getTimestampForOrg(Env.getCtx()))).append('\n')
			.append("          AND IsActive = 'Y' \n")
			.append("          AND AD_ORG_ID = ?  \n")
			.append("          AND M_Product_ID IN (\n")
			.append(productSql).append('\n')
			.append("      )\n")
			.append("          ORDER BY M_PRODUCT_ID, VALIDFROM DESC \n")
			.append("        ) ").append(DB.isPostgreSQL()?"AS prodPrecio":StringUtils.EMPTY).append('\n')
			.append("        GROUP BY M_PRODUCT_ID\n")
			.append("        ORDER BY COUNT(*) desc\n ")
			.append("      ) ").append(DB.isPostgreSQL()?"AS ":StringUtils.EMPTY).append("other ON other.product = pp.M_PRODUCT_ID  AND other.VALID = pp.VALIDFROM\n")
					//
			.append("    WHERE pp.IsActive = 'Y'\n")
			.append("      AND pp.AD_ORG_ID = ? \n")
			.append("      AND pp.ValidFrom <= ")
				.append(DB.TO_DATE(DB.getTimestampForOrg(Env.getCtx()))).append('\n')
			.append("      AND pp.M_Product_ID IN (\n")
			.append(productSql).append('\n')
			.append("      )\n")
			.append("      ORDER BY pp.M_Product_ID, pp.ValidFrom DESC\n")
			.append("  ) ").append(DB.isPostgreSQL()?"AS ":StringUtils.EMPTY).append("pr ON po.M_Product_ID = pr.M_Product_ID \n")

					//
			.append("WHERE po.IsActive = 'Y' \n")
			.append("  AND po.M_Product_ID IN (").append(productSql).append('\n')
			.append("  )  ").append(" AND po.AD_ORG_ID = ? \n")
			.append("ORDER BY  po.M_Product_ID, pr.ValidFrom DESC");
			PreparedStatement pstmt = null;
			ResultSet rSet = null;

			try {
				pstmt = DB.prepareStatement(sql.toString(), null);
				pstmt.setInt(1, Env.getAD_Org_ID(Env.getCtx()));
				pstmt.setInt(2, Env.getAD_Org_ID(Env.getCtx()));
				pstmt.setInt(3, Env.getAD_Org_ID(Env.getCtx()));
				rSet = pstmt.executeQuery();
				while (rSet.next()) {
					final MEXMEProductoOrg prod = new MEXMEProductoOrg(
							Env.getCtx(), rSet, null);
					prod.setPrecio(rSet.getBigDecimal("PRICESTD"));
					prod.setValidFrom(rSet.getTimestamp("ValidFrom"));
					prod.setIntervencionName(rSet.getString("Inter"));
					prod.setHcpcsLevel(rSet.getString("HCPCS_Level"));

					if (!lstMapa.containsKey(rSet.getInt("M_Product_ID"))) {
						lstMapa.put(rSet.getInt("M_Product_ID"), prod);
					}
				}

			} catch (Exception e) {
				slog.log(Level.SEVERE, "SQL : " + sql, e);
			} finally {
				DB.close(rSet, pstmt);
			}
		}

		return lstMapa;
	}
	
	public static MEXMEProductoOrg getProductoOrg(final Properties ctx, final int M_Product_ID, final String trxName) {
		return getProductoOrg(ctx, M_Product_ID, Env.getAD_Client_ID(ctx), Env.getAD_Org_ID(ctx), trxName);
	}

	/**
	 * getProductoOrg MEXMEProductoOrg
	 *
	 * @param ctx
	 * @param trxName
	 * @return <MEXMEProductoOrg>
	 */
	public static MEXMEProductoOrg getProductoOrg(final Properties ctx, final int M_Product_ID, final int AD_Client_ID, final int AD_Org_ID, final String trxName) {
		MEXMEProductoOrg retValue = null;
		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append(" SELECT * FROM EXME_PRODUCTOORG ");
		sql.append(" WHERE M_PRODUCT_ID = ? ");
		sql.append(" AND AD_CLIENT_ID = ? ");
		sql.append(" AND AD_ORG_ID = ? ");

		PreparedStatement pstmt = null;
		ResultSet rSet = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, M_Product_ID);
			pstmt.setInt(2, AD_Client_ID);
			pstmt.setInt(3, AD_Org_ID);
			rSet = pstmt.executeQuery();

			if (rSet.next()) {
				retValue = new MEXMEProductoOrg(ctx, rSet, trxName);
			}

		} catch (Exception e) {
			slog.log(Level.SEVERE, e.getMessage(), e);
		} finally {
			DB.close(rSet, pstmt);
			if (retValue == null && AD_Client_ID>0){
				retValue = getProductoOrg(ctx, M_Product_ID, 0, 0, trxName);
			}
		}
		return retValue;
	}

	/**
	 * Obtener productos por organización de logueo
	 *
	 * @param ctx
	 *            Contexto
	 * @param name
	 *            Nombre o clave a buscar
	 * @param trxName
	 *            Trx name
	 * @return Listado de productos por organización
	 */
	public static List<MProduct> get(Properties ctx, String name, boolean orderByName, String prodClass, String trxName) {
		List<MProduct> lst = new ArrayList<MProduct>();

		name = StringUtils.upperCase("%" + StringUtils.defaultIfEmpty(name, "").toUpperCase() + "%");

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ");
		sql.append("  p.* ");
		sql.append("FROM ");
		sql.append("  m_product p ");
		sql.append("  INNER JOIN exme_productoorg po ");
		sql.append("  ON p.m_product_id = po.m_product_id ");
		sql.append("WHERE ");
		sql.append("  (upper(p.name) LIKE ? OR ");
		sql.append("  upper(p.value) LIKE ? ) AND ");
		sql.append("  po.ad_client_id = ? AND ");
		sql.append("  po.ad_org_id = ? ");
		//sql.append("  po.ad_org_id = ? AND ");
		//sql.append("  po.isActive = 'Y' AND ");
		//sql.append("  p.isActive = 'Y' ");

		if(StringUtils.isNotBlank(prodClass)){
		    sql.append("  AND p.Productclass = ? ");
		}

		// sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name, "po"));

		if (orderByName) {
			sql.append(" order by p.name");
		} else {
			sql.append(" order by p.value");
		}

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setString(1, name);
			pstmt.setString(2, name);
			pstmt.setInt(3, Env.getAD_Client_ID(ctx));
			pstmt.setInt(4, Env.getAD_Org_ID(ctx));

			if(StringUtils.isNotBlank(prodClass)){
				pstmt.setString(5, prodClass);
			}

			rs = pstmt.executeQuery();

			while (rs.next()) {
				lst.add(new MProduct(ctx, rs, null));
			}
		} catch (Exception e) {
			slog.log(Level.SEVERE, null, e);
		} finally {
			DB.close(rs, pstmt);
		}

		return lst;
	}

	/**
	 * Producto-Organizacion
	 * 1. A nivel de Organizacion activo
	 * Si no lo encuentra a nivel de Organizacion activo
	 * 2. Busca a nivel de Organizacion inactivo
	 * Si no lo encuentra a nivel de Organizacion inactivo
	 * 3. Buscar a nivel de System donde siempre debera existir
	 * @param ctx: Contexto
	 * @param mProductID: Producto ID
	 * @param AD_Org_ID: Organizacion
	 * @param trxName: Nombre de transaccion
	 * @return MEXMEProductoOrg
	 */
	public static MEXMEProductoOrg getProductoOrgCharged(final Properties ctx,
			final int mProductID, final int AD_Org_ID, final String trxName) {
		MEXMEProductoOrg retValue = null;
		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append(" SELECT * FROM EXME_PRODUCTOORG ");
		sql.append(" WHERE EXME_ProductoOrg_ID = fnc_getProductOrg(?, ?) ");

		PreparedStatement pstmt = null;
		ResultSet rSet = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, mProductID);
			pstmt.setInt(2, AD_Org_ID);
			rSet = pstmt.executeQuery();

			if (rSet.next()) {
				retValue = new MEXMEProductoOrg(ctx, rSet, trxName);
			}

		} catch (Exception e) {
			slog.log(Level.SEVERE, e.getMessage(), e);
		} finally {
			DB.close(rSet, pstmt);
		}
		return retValue;
	}

	public BigDecimal getPrecioStd(final Timestamp dateDelivered) {
		if (productoPrecio == null) {
			productoPrecio = GetPrice.calcularPrecio(getCtx(),
					getM_Product_ID(), dateDelivered);
			if (productoPrecio == null) {
				return BigDecimal.ZERO;
			}
		}
		return productoPrecio.getPriceStd();
	}


	/**
	 * Inserta producto dentro del charge master
	 *
	 * @param mProductId
	 * @param exmeModifier1Id
	 * @param exmeModifier2Id
	 * @param exmeModifier3Id
	 * @param exmeModifier4Id
	 * @param c_taxcategory_id
	 * @param category_id
	 * @param Iscovered
	 * @param Isprofessional
	 * @param EXME_TipoProd_ID
	 * @param EXME_ProductFam_ID
	 * @param IsStocked
	 * @param revCodeId
	 * @param trx
	 * @return
	 */
	public static String insertChargeMaster(Properties ctx, final int mProductId,
			final X_I_EXME_ProductoOrg ipo, final int revCodeId, final Trx trx) {
		final StringBuffer retVal = new StringBuffer();

		final StringBuilder sBuilder = new StringBuilder("SELECT po.EXME_ProductoOrg_ID ");
		sBuilder.append("FROM EXME_ProductoOrg po ");
		sBuilder.append("WHERE po.M_Product_ID = ? ");
		sBuilder.append("AND po.AD_Org_ID = ? ");
		sBuilder.append("AND po.IsActive = 'Y' ");

		int productoOrgId =
				DB.getSQLValue(
						trx.getTrxName(),
						sBuilder.toString(),
						new Object[] { mProductId, Env.getAD_Org_ID(ctx)}
		);

		if (productoOrgId <= 0) {

			// it's not in the charge master yet

			final StringBuilder stringBuild = new StringBuilder("INSERT INTO EXME_ProductoOrg ");
			stringBuild.append(" (exme_productoorg_id, ad_client_id, ad_org_id, isactive, ");
			stringBuild.append(" createdby, updatedby , m_product_id, isformulary, ");
			stringBuild.append(" exme_modifier1_id, exme_modifier2_id, exme_modifier3_id, exme_modifier4_id, ");
			stringBuild.append(" exme_revenuecode_id , c_taxcategory_id, m_product_category_id, Iscoveredbyinsurance, ");
			stringBuild.append(" Isprofessional) ");
			stringBuild.append("values( ");
			stringBuild.append(" ?, ?, ?, ?, ");
			stringBuild.append(" ?, ?, ?, ?, ");
			stringBuild.append(" ?, ?, ?, ?, ");
			stringBuild.append(" ?, ?, ?, ?, ");
			stringBuild.append(" ? )");

			PreparedStatement pstmt = null;

			try {
				productoOrgId =
						DB.getNextID(ctx, I_EXME_ProductoOrg.Table_Name, null);

				pstmt = DB.prepareStatement(stringBuild.toString(), trx.getTrxName());

				pstmt.setInt(1, productoOrgId);
				pstmt.setInt(2, Env.getAD_Client_ID(ctx));
				pstmt.setInt(3, Env.getAD_Org_ID(ctx));
				pstmt.setString(4, "Y");
				pstmt.setInt(5, Env.getAD_User_ID(ctx));
				pstmt.setInt(6, Env.getAD_User_ID(ctx));
				pstmt.setInt(7, mProductId);
				pstmt.setString(8, "Y");
				if (ipo.getEXME_Modifier1_ID() > 0) {
					pstmt.setInt(9, ipo.getEXME_Modifier1_ID());
				} else {
					pstmt.setObject(9, null);
				}

				if (ipo.getEXME_Modifier2_ID() > 0) {
					pstmt.setInt(10, ipo.getEXME_Modifier2_ID());
				} else {
					pstmt.setObject(10, null);
				}

				if (ipo.getEXME_Modifier3_ID() > 0) {
					pstmt.setInt(11, ipo.getEXME_Modifier3_ID());
				} else {
					pstmt.setObject(11, null);
				}

				if (ipo.getEXME_Modifier4_ID() > 0) {
					pstmt.setInt(12, ipo.getEXME_Modifier4_ID());
				} else {
					pstmt.setObject(12, null);
				}

				if (revCodeId > 0) {
					pstmt.setInt(13, revCodeId);
				} else {
					pstmt.setObject(13, null);
				}

				if (ipo.getC_TaxCategory_ID() > 0) {
					pstmt.setInt(14, ipo.getC_TaxCategory_ID());
				} else {
					pstmt.setObject(14, null);
				}

				if (ipo.getM_Product_Category_ID() > 0) {
					pstmt.setInt(15, ipo.getM_Product_Category_ID());
				} else {
					pstmt.setObject(15, null);
				}

				pstmt.setString(16, DB.TO_STRING(ipo.isCoveredByInsurance()));
				pstmt.setString(17, DB.TO_STRING(ipo.isProfessional()));

				final int numRec = pstmt.executeUpdate();
				
				MEXMEProductV.checkIndexByProductOrg(ctx, productoOrgId, trx.getTrxName());
				
				slog.info("Product/Org insert = " + numRec);

			} catch (final SQLException e) {
				slog.log(Level.WARNING, "", e);
				productoOrgId = 0;

				retVal.append(Msg.getElement(ctx, "EXME_ProductoOrg_ID"));
				retVal.append(' ');
				retVal.append(Msg.getMsg(ctx, "SaveIgnored"));
				retVal.append(':');
				retVal.append(e.getMessage());

			} finally {
				DB.close(pstmt);
			}
			
			MProduct mProduct = new MProduct(ctx, mProductId, null);
			try {
				mProduct.insertAccounting(Env.getAD_Client_ID(ctx));
			} catch (Exception e) {
				slog.log(Level.SEVERE, null, e);
			}

		} else {

			// it's in the charge master already

			final StringBuilder sql2 = new StringBuilder("UPDATE EXME_ProductoOrg");
			sql2.append(" SET EXME_Modifier1_ID= ?, EXME_Modifier2_ID= ?, EXME_Modifier3_ID=?, EXME_Modifier4_ID= ?, ");
			sql2.append(" C_TaxCategory_ID= ?, ");
			sql2.append(" M_Product_Category_ID= ?, ");
			sql2.append(" iscoveredbyinsurance= ?, ");
			sql2.append(" isprofessional = ?, ");
			sql2.append(" m_product_id = ? ");
			sql2.append(" WHERE EXME_ProductoOrg_ID = ? ");

			final int numRec =
					DB.executeUpdate(
							sql2.toString(), new Object[] {
								ipo.getEXME_Modifier1_ID() > 0 ? ipo.getEXME_Modifier1_ID() : null,
								ipo.getEXME_Modifier2_ID() > 0 ? ipo.getEXME_Modifier2_ID() : null,
								ipo.getEXME_Modifier3_ID() > 0 ? ipo.getEXME_Modifier3_ID() : null,
								ipo.getEXME_Modifier4_ID() > 0 ? ipo.getEXME_Modifier4_ID() : null,
								ipo.getC_TaxCategory_ID() > 0 ? ipo.getC_TaxCategory_ID() : null,
								ipo.getM_Product_Category_ID() > 0 ? ipo.getM_Product_Category_ID() : null,
								ipo.isCoveredByInsurance(),
								ipo.isProfessional(),
								mProductId,
								productoOrgId
							},
							trx.getTrxName()
					);
			
			MEXMEProductV.checkIndexByProductOrg(ctx, productoOrgId, trx.getTrxName());
			
			slog.info("Product/Org updated = " + numRec);
		}

		return retVal.toString();

	}
	
	/**
	 * Obtener id del producto
	 * 
	 * @param ctx
	 *            Contexto de la app
	 * @param productoOrgId
	 *            Id de producto org
	 * @param trxName
	 *            Nombre de trx
	 * @return Id del producto o -1 si no fue encontrado
	 */
	public static int getProductId(Properties ctx, int productoOrgId, String trxName) {
		return DB.getSQLValue(trxName, "select M_Product_ID from EXME_ProductoOrg where EXME_ProductoOrg_ID = ?", productoOrgId);
	}
	
	/**
	 * Listado de relaciones de producto organización
	 * 
	 * @param ctx
	 *            Contexto
	 * @param productId
	 *            Producto a revisar
	 * @param trxName
	 *            Nombre de transacción
	 * @return Listado de las relaciones
	 */
	public static List<Integer> getAll(Properties ctx, int productId, String trxName) {
		List<Integer> lst = new ArrayList<Integer>();

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ");
		sql.append("  EXME_ProductoOrg_ID ");
		sql.append("FROM ");
		sql.append("  EXME_ProductoOrg ");
		sql.append("WHERE ");
		sql.append("  M_Product_ID = ? ");

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, productId);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				lst.add(rs.getInt(1));
			}
		} catch (Exception e) {
			slog.log(Level.SEVERE, null, e);
		} finally {
			DB.close(rs, pstmt);
		}

		return lst;
	}
	
	/**
	 * Listado de Ids de productos de una organización
	 * 
	 * @param ctx
	 *            Contexto de la App
	 * @param orgId
	 *            Id de la Organización
	 * @param trxName
	 *            Trx
	 * @return Listado de Ids
	 */
	public static List<Integer> getAllProductsByOrg(Properties ctx, int orgId, String trxName) {
		List<Integer> lst = new ArrayList<Integer>();

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ");
		sql.append("  M_Product_ID ");
		sql.append("FROM ");
		sql.append("  EXME_ProductoOrg ");
		sql.append("WHERE ");
		sql.append("  AD_Org_ID = ? ");

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, orgId);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				lst.add(rs.getInt(1));
			}
		} catch (Exception e) {
			slog.log(Level.SEVERE, null, e);
		} finally {
			DB.close(rs, pstmt);
		}

		return lst;
	}
	
	
	/**
	 * Producto a nivel de organizacion
	 * @param ctx: Contexto
	 * @param M_Product_ID: Producto
	 * @param AD_Org_ID: Organizacion en que deberia estar el producto
	 * @param filtroActivo: 0: Inactivos, 1: Activos, -1: Indistinto
	 * @return MEXMEProductoOrg
	 */
	public static MEXMEProductoOrg getProductOrg(Properties ctx,
			final int M_Product_ID, final int AD_Org_ID, final int filtroActivo) {
		final Query mQuery = new Query(ctx, Table_Name, " AD_Org_ID = ? AND M_Product_ID = ? ", null)//
		.setParameters(AD_Org_ID, M_Product_ID);//
		
		if(filtroActivo>=0){
			mQuery.setOnlyActiveRecords(filtroActivo==1);//
		}
		
		return mQuery.addAccessLevelSQL(true)//
		.first();
	}
	
	/**
	 * Marca como en desuso sin revisar validaciones
	 * 
	 * @return Si/no pudo marcar como desuso
	 */
	public boolean markAsUnused() {
		boolean retValue = true;

		setUnused(true);
		setIsObsolete(true);

		Trx microTrx = null;

		try {
			microTrx = Trx.get(Trx.createTrxName("Unused"), true);

			if (save(microTrx.getTrxName())) {
				Trx.commit(microTrx);
			} else {
				retValue = false;
				Trx.rollback(microTrx);
			}

		} catch (Exception ex) {
			Trx.rollback(microTrx);
			retValue = false;
			slog.log(Level.SEVERE, null, ex);
		} finally {
			Trx.close(microTrx);
		}

		return retValue;
	}
	
	public static enum RemoveAction {
		REMOVED("msj.desuso.exito"), NOTREMOVED("error.borrarLiga"), PARTIALREMOVED("msj.desuso.movimientos"), CANTREMOVE("error.existenciasBorrarLiga"), HASREFERENCES("Tiene referencias");

		private String msg;

		private RemoveAction(String msg) {
			this.msg = msg;
		}

		public String getMsg() {
			return msg;
		}
	}
}
