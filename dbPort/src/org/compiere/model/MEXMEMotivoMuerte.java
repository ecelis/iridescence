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
import org.compiere.util.KeyNamePair;
import org.compiere.util.Utilerias;

/**
 * Tabla con motivos de muerte
 * TODO: Revisar si se requiere conservar esta tabla,
 * ya que actualmente existe otra llamada {@link X_EXME_CausaMuerte}
 * 
 * @author erick
 * 
 */
public class MEXMEMotivoMuerte extends X_EXME_MotivoMuerte {
	private static final long	serialVersionUID	= 1L;
	/** Static Logger */
	private static CLogger		s_log				= CLogger.getCLogger(MEXMEMotivoMuerte.class);

	/**
	 * @param ctx
	 * @param EXME_MotivoMuerte_ID
	 * @param trxName
	 */
	public MEXMEMotivoMuerte(Properties ctx, int EXME_MotivoMuerte_ID, String trxName) {
		super(ctx, EXME_MotivoMuerte_ID, trxName);

	}

	/**
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MEXMEMotivoMuerte(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);

	}

	/**
	 * Obtiene un listado de motivo de muerte
	 * 
	 * @param ctx
	 * @param trxName
	 * @return List<KeyNamePair>
	 */
	public static List<KeyNamePair> getList(Properties ctx, String trxName) {

		List<KeyNamePair> retValue = new ArrayList<KeyNamePair>();

		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		PreparedStatement psmt = null;
		ResultSet rs = null;

		try {
			sql.append("SELECT EXME_MotivoMuerte.EXME_MotivoMuerte_ID, EXME_MotivoMuerte.Name ")
				.append("FROM EXME_MotivoMuerte WHERE EXME_MotivoMuerte.isActive = 'Y' ")
				.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name));

			psmt = DB.prepareStatement(sql.toString(), trxName);
			rs = psmt.executeQuery();

			while (rs.next()) {
				KeyNamePair lvb = new KeyNamePair(rs.getInt("EXME_MotivoMuerte_ID"), rs.getString("Name"));
				retValue.add(lvb);
			}

		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, psmt);
			sql = null;
		}

		return retValue;
	}

	@Override
	protected boolean beforeDelete() {

		// validar si hay referencias a esa tabla

		if (!puedeBorrar()) {
			log.saveError("Error", Utilerias.getMessage(getCtx(), null, "msj.errorEliminarRegistro"));
			return false;
		}

		return true;
	}

	public boolean puedeBorrar() {

		// Buscar Tablas
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		// Buscar Tablas
		StringBuilder sql1 = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);

		ResultSet rs = null;
		PreparedStatement psmt = null;

		ResultSet rs1 = null;
		PreparedStatement psmt1 = null;

		try {
			sql.append("SELECT distinct tablename").append(" FROM ad_column c INNER JOIN ad_table t ON c.ad_table_id = t.ad_table_id ")
				.append("WHERE columnname = 'EXME_MotivoMuerte_ID'").append("AND t.tablename <> ").append(DB.TO_STRING(Table_Name));

			psmt = DB.prepareStatement(sql.toString(), get_TrxName());
			rs = psmt.executeQuery();

			while (rs.next()) {
				sql1 = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
				sql1.append("select * from ").append(rs.getString("tablename")).append(" where exme_motivomuerte_id = ?");

				psmt1 = DB.prepareStatement(sql1.toString(), get_TrxName());
				psmt1.setInt(1, getEXME_MotivoMuerte_ID());
				rs1 = psmt1.executeQuery();

				if (rs1.next()) {

					return false;
				}

			}

		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, psmt);
			sql = null;
			DB.close(rs1, psmt1);
			sql1 = null;
		}

		return true;
	}

}
