/**
 * 
 */
package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;

/**
 * @author twry
 *
 */
public class MEXMEProductoPrecio extends X_EXME_ProductoPrecio {
	private static CLogger s_log = CLogger.getCLogger(MEXMEProductoPrecio.class);

	/** serialVersionUID */
	private static final long serialVersionUID = -2890770623411367843L;

	/**
	 * @param ctx
	 * @param EXME_ProductoPrecio_ID
	 * @param trxName
	 */
	public MEXMEProductoPrecio(Properties ctx, int EXME_ProductoPrecio_ID,
			String trxName) {
		super(ctx, EXME_ProductoPrecio_ID, trxName);
	}

	/**
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MEXMEProductoPrecio(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	/**
	 * Product
	 */
	private MProduct product = null;
	
	/**
	 * Product
	 * @return <MProduct>
	 */
	public MProduct getProduct() {
		if(getM_Product_ID()>0)
			product = new MProduct(getCtx(), getM_Product_ID(), null);
		return product;
	}
	
	/**
	 * 
	 * @param ctx
	 * @param M_Product_ID
	 * @param trxName
	 * @return Historico de precios en EXME_ProductoPrecio
	 */
	public static List<MEXMEProductoPrecio> getHistoric(Properties ctx, int M_Product_ID, String trxName) {
		
		List<MEXMEProductoPrecio> retValue = new ArrayList<MEXMEProductoPrecio>();
		StringBuilder sql = new StringBuilder("SELECT * FROM exme_productoprecio WHERE M_Product_ID=?");
		sql.append(" ORDER BY validFrom DESC ");
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, M_Product_ID);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				retValue.add( new MEXMEProductoPrecio(ctx, rs, trxName));
			}
			
		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
		}
		return retValue;
	}
	
	
	/**
	 * 
	 * @param ctx
	 * @param M_Product_ID
	 * @param trxName
	 * @return Historico de precios en EXME_ProductoPrecio
	 */
	public static List<MEXMEProductoPrecio> getHistoricbyUpdated(Properties ctx, int M_Product_ID, String trxName) {
		
		List<MEXMEProductoPrecio> retValue = new ArrayList<MEXMEProductoPrecio>();
		StringBuilder sql = new StringBuilder("SELECT * FROM exme_productoprecio WHERE M_Product_ID=?");
		sql.append(" AND AD_CLIENT_ID = ? AND AD_ORG_ID = ? ")
			.append(" ORDER BY UPDATED DESC ");
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, M_Product_ID);
			pstmt.setInt(2, Env.getAD_Client_ID(ctx));
			pstmt.setInt(3, Env.getAD_Org_ID(ctx));
			rs = pstmt.executeQuery();
			while (rs.next()) {
				retValue.add( new MEXMEProductoPrecio(ctx, rs, trxName));
			}
			
		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
		}
		return retValue;
	}
	
	/**
	 * 
	 * @param ctx
	 * @param M_Product_ID
	 * @param trxName
	 * @return
	 */
	public static MEXMEProductoPrecio getCurrent(Properties ctx, int M_Product_ID, String trxName) {
		
		MEXMEProductoPrecio retValue = null;
		StringBuilder sql = new StringBuilder(" SELECT * FROM exme_productoprecio WHERE M_Product_ID= ? ");
		sql.append("  AND validfrom <= "+ DB.TO_DATE(DB.getTimestampForOrg(Env.getCtx())) + " order by validfrom desc ");
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, M_Product_ID);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				retValue = new MEXMEProductoPrecio(ctx, rs, trxName);
			}
			
		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
		}
		return retValue;
	}
	
	/**
	 * Obtener el precio desde el charge master
	 * @param ctx
	 * @param pProductID
	 * @param valid
	 * @return
	 */
	
	public static MEXMEProductoPrecio calcularPrecio(final Properties ctx,
			final int pProductID, final Timestamp valid) {
		if (pProductID == 0 || valid==null){
			return null;
		}
		
		MEXMEProductoPrecio precio = null;
		//
		final StringBuilder sql = new StringBuilder(
				"SELECT * FROM EXME_ProductoPrecio ");
		sql.append(" WHERE IsActive='Y' ").append(" AND M_Product_ID = ? ")
				.append(" AND ValidFrom <= ? ")
				.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", X_EXME_ProductoPrecio.Table_Name))
				.append(
						" ORDER BY ValidFrom DESC ");

		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setInt(1, pProductID);
			pstmt.setTimestamp(2, valid);
			rset = pstmt.executeQuery();
			if (rset.next()) {
				precio = new MEXMEProductoPrecio(ctx, rset, null);
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rset, pstmt);
		}

		return precio;
	}
}
