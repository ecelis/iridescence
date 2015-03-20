package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.apache.struts.util.LabelValueBean;
import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.KeyNamePair;

public class MEXMEArrivalMode extends X_EXME_ArrivalMode{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** log de la clase */
	private static CLogger log = CLogger.getCLogger (MEXMEArrivalMode.class);
	
	public MEXMEArrivalMode(final Properties ctx, final int EXME_ArrivalMode_ID, final String trxName) {
		super(ctx, EXME_ArrivalMode_ID, trxName);
	}

	public MEXMEArrivalMode(final Properties ctx, final ResultSet rs, final String trxName) {
		super(ctx, rs, trxName);
	}
	
	/**
	 * Arrival Modes
	 * @param ctx
	 * @return
	 * @deprecated use {@link KeyNamePair} instead {@link #getList(Properties, boolean)}
	 */
	public static List<LabelValueBean>	getArrivalModes(final Properties ctx, final boolean isNewBorn){
		final List<LabelValueBean> list = new ArrayList<LabelValueBean>();
		
		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		PreparedStatement pstmt = null;
		ResultSet result = null;
		
		sql.append(" SELECT EXME_ARRIVALMODE_ID, NAME ")
			.append(" FROM EXME_ARRIVALMODE ")
			.append(" WHERE ISACTIVE='Y' ")
			.append(" AND ISNEWBORN=? ");
		
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name));
		sql.append(" ORDER BY ISDEFAULT DESC, NAME ");
		
		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setString(1, DB.TO_STRING(isNewBorn));// Lama
			result = pstmt.executeQuery();
			while (result.next()) {
				list.add(new LabelValueBean(result.getString(2), result.getString(1)));

			}
		} catch (SQLException e) {
			log.log(Level.SEVERE, e.getLocalizedMessage());
		} finally {
			DB.close(result, pstmt);
		}

		return list;
	}
	
	/**
	 * Arrival Modes
	 * @param ctx
	 * @return
	 */
	public static List<KeyNamePair>	getList(final Properties ctx, final boolean isNewBorn){
		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append(" SELECT EXME_ARRIVALMODE_ID, NAME ");
		sql.append(" FROM EXME_ARRIVALMODE ");
		sql.append(" WHERE ISACTIVE = 'Y' ");
		sql.append(" AND ISNEWBORN = ? ");
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name));
		sql.append(" ORDER BY ISDEFAULT DESC, NAME ");
		return Query.getListKN(sql.toString(), null, DB.TO_STRING(isNewBorn));
	}
	
	/**
	 * Copiar valores de system a la organizacion
	 * 
	 * @param ctx
	 *            Contexto
	 * @param trxName
	 *            Transaccion
	 */
	public static void copyFromSystem(final Properties ctx, final String trxName) {
		final StringBuilder st = new StringBuilder("select * from EXME_ArrivalMode where isActive = ? and AD_Client_ID = ? and AD_Org_ID = ?");
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(st.toString(), trxName);
			pstmt.setString(1, "Y");
			pstmt.setInt(2, 0);
			pstmt.setInt(3, 0);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				final MEXMEArrivalMode tpn = new MEXMEArrivalMode(ctx, 0, null);

				final MEXMEArrivalMode tp = new MEXMEArrivalMode(ctx, rs, null);

				PO.copyValues(tp, tpn);

				tpn.save(trxName);
			}
		} catch (Exception e) {
			log.log(Level.SEVERE, st.toString(), e);
		} finally {
			DB.close(rs, pstmt);
		}
	}


}
