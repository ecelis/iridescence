/*

 * Created on 05-abr-2005

 *

 */

package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.Msg;

/**
 * @author hassan reyes
 *
 */
public class MSubstitute extends X_M_Substitute {

	/**
	 * 
	 */
	private static final long serialVersionUID = 480859323776944615L;
	private static CLogger slog = CLogger.getCLogger(MSubstitute.class);

    /**
     * @param ctx
     * @param M_Substitute_ID
     * @param trxName
     */
    public MSubstitute(Properties ctx, int M_Substitute_ID, String trxName) {
        super(ctx, M_Substitute_ID, trxName);
    }

    /**
     * @param ctx
     * @param rs
     * @param trxName
     */
    public MSubstitute(Properties ctx, ResultSet rs, String trxName) {
        super(ctx, rs, trxName);
    }

    
    protected boolean beforeSave(boolean newRecord) {
        boolean guardar = true;
        if (getValidTo()!= null && getValidFrom().after(getValidTo())) {
            log.saveError("Error", Msg.parseTranslation(getCtx(), "La fecha final debe ser mayor o igual a la fecha inicial"));
            guardar = false;
        }
        return guardar;
    }
    
	/**
	 * Substitutos del producto
	 * 
	 * @param ctx
	 * @param productID
	 * @param trxName
	 * @return lista de productos
	 *
	 **/
	public static List<MSubstitute> getSubstitutes(Properties ctx, int productID, String trxName) {

		List<MSubstitute> retValue = new ArrayList<MSubstitute>();
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append(" SELECT  * FROM M_substitute ");
		sql.append(" WHERE M_substitute.M_Product_ID = ? ");
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name));
		sql.append("ORDER BY created desc");

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, productID);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				MSubstitute rp = new MSubstitute(ctx, rs, trxName);
				retValue.add(rp);
			}

		} catch (Exception e) {
			slog.log(Level.SEVERE, e.getMessage(), e);
		} finally {
			DB.close(rs, pstmt);
		}
		return retValue;
	}
	
	/**
	 * Validacion de un producto substituto en el almacen
	 * @param ctx
	 * @param M_Substitute_ID
	 * @param M_Product_ID
	 * @param trxName
	 * @return
	 */
	
	public static MSubstitute get(Properties ctx, int M_Substitute_ID,
			int M_Product_ID, String trxName) {
		
		MSubstitute retValue = null;
		String sql = "SELECT * FROM m_Substitute WHERE Substitute_ID=? AND M_Product_ID=?";
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql, trxName);
			pstmt.setInt(1, M_Substitute_ID);
			pstmt.setInt(2, M_Product_ID);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				retValue = new MSubstitute(ctx, rs, trxName);
			}
			
		} catch (Exception e) {
			slog.log(Level.SEVERE, sql, e);
		} finally {
			DB.close(rs, pstmt);
		}
		return retValue;
	} // get    
	
	public static List<X_I_M_Substitute> getDataToImport(Properties ctx, String trxName) {
		List<X_I_M_Substitute> lista = new ArrayList<X_I_M_Substitute>();

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ");
		sql.append("  * ");
		sql.append("FROM ");
		sql.append("  I_M_Substitute ");
		sql.append("WHERE ");
		sql.append("  I_IsImported = 'N' AND ");
		sql.append("  Processed = 'N' ");
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, Constantes.SPACE, X_I_M_Substitute.Table_Name));

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				X_I_M_Substitute rp = new X_I_M_Substitute(ctx, rs, trxName);
				lista.add(rp);
			}

		} catch (Exception e) {
			slog.log(Level.SEVERE, e.getMessage(), e);
		} finally {
			DB.close(rs, pstmt);
		}
		return lista;
	}
	
}
