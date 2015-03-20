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

/**
 * 
 * @author LLama
 * 
 */
public class MMotivoCancel extends X_EXME_MotivoCancel {

	/** SerialVersionUID */
	private static final long serialVersionUID = -8803352413344267687L;
	/** Static Logger */
	private static CLogger		slog				= CLogger.getCLogger(MMotivoCancel.class);

	/**
	 * @param ctx
	 * @param EXME_MotivoCancel_ID
	 * @param trxName
	 */
	public MMotivoCancel(Properties ctx, int EXME_MotivoCancel_ID, String trxName) {
		super(ctx, EXME_MotivoCancel_ID, trxName);
	}

	/**
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MMotivoCancel(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	/**
	 * Obtenemos una lista con los motivos de cancelaci�n. Se utiliza para llenar combo box de
	 * motivo de cancelaci�n
	 * 
	 * @return Una lista con las motivos de cancelaci�n
	 * @exception Exception
	 *                Description of the Exception
	 * @deprecated usar {@link #getList(Properties, String)}
	 */
	public static List<LabelValueBean> getMotivoC(Properties ctx, String modulo, boolean blankLine) throws Exception {

		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		// lista con las motivos de cancelaci�n
		List<LabelValueBean> lstMotivoCancel = new ArrayList<LabelValueBean>();

		// buscamos el motivo de cancelaci�n
		sql.append("SELECT EXME_MotivoCancel.EXME_MotivoCancel_ID, EXME_MotivoCancel.Name ")
				.append("FROM EXME_MotivoCancel ").append("WHERE IsActive = 'Y' ")
				.append(modulo != null ? " AND modulo = ? " : "")
				.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name))
				.append("ORDER BY EXME_MotivoCancel.Name ");

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			if (blankLine) {
				lstMotivoCancel.add(new LabelValueBean("", "0"));
			}
			pstmt = DB.prepareStatement(sql.toString(), null);

			if (modulo != null)
				pstmt.setString(1, modulo);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				LabelValueBean motivo = new LabelValueBean(rs.getString("Name"), String.valueOf(rs
						.getLong("EXME_MotivoCancel_ID")));
				lstMotivoCancel.add(motivo);
			}

		} catch (SQLException e) {
			throw new Exception("error.citas.getMotivos");
		} finally {
			DB.close(rs, pstmt);
		}

		return lstMotivoCancel;
	}

	/**
	 * Obtenemos una lista con los motivos de cancelaci�n. Se utiliza para llenar combo box de
	 * motivo de cancelaci�n
	 * 
	 * @param ctx
	 * @param modulo
	 * @return
	 */
	public static List<KeyNamePair> getList(Properties ctx, String modulo) {

		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		final List<KeyNamePair> list = new ArrayList<KeyNamePair>();

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			sql.append("SELECT EXME_MotivoCancel_ID as key, Name ");
			sql.append("FROM EXME_MotivoCancel ");
			sql.append("WHERE IsActive='Y' ");
			if (modulo != null) {
				sql.append(" AND modulo=? ");
			}
			sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name));
			sql.append("ORDER BY Name ");

			pstmt = DB.prepareStatement(sql.toString(), null);
			if (modulo != null) {
				pstmt.setString(1, modulo);
			}
			rs = pstmt.executeQuery();
			while (rs.next()) {
				list.add(new KeyNamePair(rs.getInt("key"), (rs.getString("Name"))));
			}
		} catch (SQLException e) {
			slog.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
		}

		return list;
	}
}
