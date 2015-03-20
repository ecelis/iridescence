/*
 * Created on 18/05/2005
 *
 *
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.apache.struts.util.LabelValueBean;
import org.compiere.hospital.utils.Datos;
import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Utilerias;

/**
 * @author YWRY
 * 
 * 
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public class MCDiario extends X_EXME_CDiario {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** Static Logger */
	private static CLogger s_log = CLogger.getCLogger(MCDiario.class);

	/**
	 * 
	 */
	public MCDiario(Properties ctx, int EXME_CDiario_ID, String trxName) {
		super(ctx, EXME_CDiario_ID, trxName);
		//
	}

	public MCDiario(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	/**
	 * Obtenemos las cuentas paciente a partir de ciertos criterios.
	 * 
	 * @param ctx
	 * @param where
	 * @return
	 */
	public static MCDiarioDet[] get(Properties ctx, int EXME_CDiario_ID,
			String trxName) {

		ArrayList<MCDiarioDet> list = new ArrayList<MCDiarioDet>();
		String sql = " SELECT EXME_CDiarioDet.* FROM EXME_CDiarioDet WHERE EXME_CDiarioDet.isActive = 'Y' "
				+ " AND EXME_CDiarioDet.EXME_CDiario_ID = ? ";

		sql = MEXMELookupInfo.addAccessLevelSQL(ctx, sql, "EXME_CDiarioDet");
		s_log.log(Level.FINEST, " sql " + sql);

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql, trxName);
			pstmt.setInt(1, EXME_CDiario_ID);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				MCDiarioDet cDiarioDet = new MCDiarioDet(ctx, rs, trxName);
				list.add(cDiarioDet);
			}

		} catch (Exception e) {
			s_log.log(Level.SEVERE, e.getMessage(), e);
		} finally {
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}

		//
		MCDiarioDet[] diarios = new MCDiarioDet[list.size()];
		list.toArray(diarios);
		return diarios;

	}

	// Obtener el almacen
	public static MWarehouse getMWareHouse(Properties ctx, String name,
			String trxName) throws Exception {
		long cDiarioID = 0;
		try {
			cDiarioID = Datos
					.getIDFromValue(Env.getCtx(), "EXME_CDiario", name);
		} catch (Exception e) {
			throw new Exception(Utilerias.getMessage(Env.getCtx(), null,
					"error.value.unique"));
		}
		if (cDiarioID > 0) {
			throw new Exception(Utilerias.getMessage(Env.getCtx(), null,
					"error.value.unique"));
		}

		// MCDiario cDiario = MCDiario.getCDiarioByName(ctx, name, trxName);
		MCDiario cDiario = new MCDiario(ctx, Integer.parseInt(String
				.valueOf(cDiarioID)), trxName);
		if (cDiario.get_ID() > 0) {
			return cDiario.getWareHouse(ctx);
		} else {
			return null;
		}
	}

	private MWarehouse warehouse;

	public MWarehouse getWareHouse(Properties ctx) {
		if (warehouse == null) {
			warehouse = new MWarehouse(ctx, getM_Warehouse_ID(), null);
		}
		return warehouse;

	}

	/**
	 * 
	 * @param ctx
	 * @param trxName
	 * @return
	 */
	public static List<LabelValueBean> get(Properties ctx, String trxName) {

		List<LabelValueBean> list = new ArrayList<LabelValueBean>();

		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);

		sql.append(" SELECT EXME_CDiario_ID, Name ")
				.append(" FROM EXME_CDiario ").append(" WHERE IsActive = 'Y' ");
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name));
		sql.append(" ORDER BY Name ");

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				LabelValueBean esp = new LabelValueBean(rs.getString("Name"),
						rs.getString("EXME_CDiario_ID"));
				list.add(esp);
			}

		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);

		} finally {
			DB.close(rs, pstmt);
		}
		return list;

	}

	/**
	 * 
	 * @param ctx
	 * @param trxName
	 * @return
	 */
	public static List<MCDiario> getAll(Properties ctx, String trxName) {

		List<MCDiario> list = new ArrayList<MCDiario>();

		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);

		sql.append(" SELECT * ").append(" FROM EXME_CDiario ")
				.append(" WHERE IsActive = 'Y' ");
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name));
		sql.append(" ORDER BY CREATED ");

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				list.add(new MCDiario(ctx, rs, null));
			}

		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);

		} finally {
			DB.close(rs, pstmt);
		}
		return list;

	}

	/**
	 * 
	 * @param ctx
	 * @param trxName
	 * @return
	 */
	public static MCDiario getByRoomType(final Properties ctx,
			final int tipoHabitacionId, final int tipoPacId,
			final int estServID, final String trxName) {

		MCDiario diario = null;

		final StringBuilder sql = new StringBuilder(
				Constantes.INIT_CAPACITY_ARRAY);

		sql.append(" select * from exme_cdiario where exme_tipohabitacion_id = ? ");
		sql.append(" and exme_tipopaciente_id = ? and exme_estserv_id = ?  ");

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, tipoHabitacionId);
			pstmt.setInt(2, tipoPacId);
			pstmt.setInt(3, estServID);
			rs = pstmt.executeQuery();
			s_log.info("cDiario.getByRoomType "+ sql.toString() +" tipoHabitacionId " + tipoHabitacionId
					 +" tipoPacId " + tipoPacId
					 +" estServID " + estServID
					
					);
			while (rs.next()) {
				diario = new MCDiario(ctx, rs, null);
			}

		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);

		} finally {
			DB.close(rs, pstmt);
		}
		return diario;

	}

	/**
	 * 
	 * @param ctx
	 * @param tipoPac
	 * @param tipoHab
	 * @param service
	 * @param productID
	 * @return valid (existe un registro con los parametros indicados)
	 */
	public static boolean validateIndex(Properties ctx, int tipoPac,
			int tipoHab, int service, int productID, String trxName) {
		boolean existente = false;

		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append(" select * from exme_cdiario where ");

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		if (productID > 0) {
			sql.append(" m_product_id = ? ");
		} else {
			sql.append("EXME_TIPOPACIENTE_ID = ? AND EXME_TIPOHABITACION_ID = ? ");// AND
																					// EXME_ESTSERV_ID
																					// =
																					// ?");
		}

		sql.append(" AND AD_ORG_ID = ?");

		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);

			if (productID != 0) {
				pstmt.setInt(1, productID);
				pstmt.setInt(2, Env.getAD_Org_ID(ctx));
			} else {
				pstmt.setInt(1, tipoPac);
				pstmt.setInt(2, tipoHab);
				// pstmt.setInt(3, service);
				pstmt.setInt(3, Env.getAD_Org_ID(ctx));
			}
			rs = pstmt.executeQuery();

			if (rs.next()) {
				existente = true;
			}

		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
			existente = false;

		} finally {
			DB.close(rs, pstmt);
		}

		return existente;
	}
}