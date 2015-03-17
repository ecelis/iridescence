/**
 * 
 */
package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;


public class MEXMEStopPolicy extends X_EXME_StopPolicy {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** log de la clase*/
	private static CLogger log = CLogger.getCLogger(MEXMEStopPolicy.class);
			
	/**
	 * @param ctx
	 * @param EXME_StopPolicy_ID
	 * @param trxName
	 */
	public MEXMEStopPolicy(Properties ctx, int EXME_StopPolicy_ID,
			String trxName) {
		super(ctx, EXME_StopPolicy_ID, trxName);
	}

	/**
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MEXMEStopPolicy(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}
	
	/**
	 * Consiguracion para la familia de productos del medicamento seleccionado.
	 * @param ctx
	 * @param exmeProdFamId
	 * @return
	 */
	public static MEXMEStopPolicy getFromProdFamId(Properties ctx, int exmeProdFamId){
		MEXMEStopPolicy mStopPolicy = null;
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		PreparedStatement pstmt = null;
		ResultSet result = null;
		try {
			sql.append("SELECT EXME_STOPPOLICY.* FROM EXME_STOPPOLICY ")
			.append(" WHERE EXME_STOPPOLICY.EXME_PRODUCTFAM_ID = ? ")
			.append(" AND EXME_STOPPOLICY.ISACTIVE = 'Y' ");
			
			sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, sql.toString(), Table_Name));

			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setInt(1, exmeProdFamId);
			result = pstmt.executeQuery();
			
			if(result.next()){
				mStopPolicy = new MEXMEStopPolicy(ctx, result, null); 
			}
			
		} catch (SQLException e) {
			log.log(Level.SEVERE, e.getLocalizedMessage(), e);
		} finally {
			DB.close(result, pstmt);
		}

		return mStopPolicy;
	}
	
	/**
	 * Configuracion default
	 * @param ctx Contexto
	 * @param value Valor de la familia
	 * @param trxName
	 */
	public static void createConfig(Properties ctx, String trxName) {
		int productFam = DB.getSQLValue(null, "select exme_productfam_id from exme_productfam where value = ? ", "ETHICAL DRUGS");
		int uomId = DB.getSQLValue(null, "select c_uom_id from c_uom where trim(uomsymbol) = ? ", "d");

		MEXMEStopPolicy stop = new MEXMEStopPolicy(ctx, 0, trxName);
		stop.setC_UOM_ID(uomId);
		stop.setEXME_ProductFam_ID(productFam);
		stop.setMaxAutoDuration(7);

		stop.save();

	}
	
	public static List<MEXMEStopPolicy> getAll(Properties ctx) {
		return new Query(ctx, Table_Name, null, null).setOnlyActiveRecords(true).setOrderBy(COLUMN_Created).list();

	}

}
