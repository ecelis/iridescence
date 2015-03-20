package org.compiere.model;

import java.sql.ResultSet;
import java.util.List;
import java.util.Properties;

import org.compiere.util.QuickSearchTables;

/**
 * Vista de productos con join a EXME_ProductoOrg
 * 
 * @author odelarosa
 * 
 */
public class MEXMEProductV extends X_EXME_ProductV {

	private static final long serialVersionUID = 1820595159272337492L;

	/**
	 * Actualización de índice por producto
	 * 
	 * @param ctx
	 *            Contexto
	 * @param productId
	 *            Producto
	 * @param trxName
	 *            Trx Name
	 */
	public static void checkIndexByProduct(Properties ctx, int productId, String trxName) {
		List<Integer> ids = MEXMEProductoOrg.getAll(ctx, productId, trxName);

		for (Integer id : ids) {
			checkIndexByProductOrg(ctx, id, trxName);
		}
	}

	/**
	 * Actualización por relación de producto org
	 * 
	 * @param ctx
	 *            Contexto
	 * @param productOrgId
	 *            Producto Org
	 * @param trxName
	 *            Trx Name
	 */
	public static void checkIndexByProductOrg(Properties ctx, int productOrgId, String trxName) {
		QuickSearchTables.checkTables(MEXMEProductV.class, MEXMEProductV.Table_Name, productOrgId, trxName, ctx);
	}

	/**
	 * @param ctx
	 * @param EXME_ProductV_ID
	 * @param trxName
	 */
	public MEXMEProductV(Properties ctx, int EXME_ProductV_ID, String trxName) {
		super(ctx, EXME_ProductV_ID, trxName);
	}

	/**
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MEXMEProductV(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

}
