/**
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

/**
 * @author twry
 * 
 */
public class MEXMEInOutStop extends X_EXME_InOutStop {

	/** serialVersionUID */
	private static final long serialVersionUID = 8751391972939259050L;
	/** Logger */
	private static CLogger sLog = CLogger.getCLogger(MEXMEInOutStop.class);

	/**
	 * Constructor
	 * 
	 * @param ctx
	 * @param inOutStopID
	 * @param trxName
	 */
	public MEXMEInOutStop(final Properties ctx, final int inOutStopID,
			final String trxName) {
		super(ctx, inOutStopID, trxName);
	}

	/**
	 * Constructor
	 * 
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MEXMEInOutStop(final Properties ctx, final ResultSet rset,
			final String trxName) {
		super(ctx, rset, trxName);
	}

	/**
	 * find
	 * 
	 * @param ctx
	 * @param productid
	 * @param lote
	 * @param trxName
	 * @return
	 */
	public static List<MEXMEInOutStop> find(final Properties ctx,
			final int productid, final String lote, final String trxName) {

		final List<MEXMEInOutStop> retValue = new ArrayList<MEXMEInOutStop>();
		final StringBuilder sql = new StringBuilder(
				Constantes.INIT_CAPACITY_ARRAY);

		sql.append("SELECT ios.* FROM exme_inoutstop ios WHERE ios.IsActive='Y' ");
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name,
				"ios"));
		sql.append(" and ios.status = 'IP' and ios.m_product_id = ? ");

		if (lote != null && !lote.isEmpty()) {
			sql.append(" and ios.lot = ? ");
		} else {
			sql.append(" and trim(ios.lot) is null ");
		}

		PreparedStatement pstmt = null;
		ResultSet rset = null;

		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, productid);
			if (lote != null && !lote.isEmpty()) {
				pstmt.setString(2, lote);
			}
			rset = pstmt.executeQuery();
			if (rset.next()) {
				retValue.add(new MEXMEInOutStop(ctx, rset, trxName));
			}

		} catch (Exception e) {
			sLog.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rset, pstmt);
			rset = null;
			pstmt = null;
		}
		return retValue;
	}

}
