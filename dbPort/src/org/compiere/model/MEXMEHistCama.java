/*
 * Created on 31-ene-2006
 *
 */
package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.apache.commons.lang.StringUtils;
import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.Env;

/**
 * @author Omar Torres
 * @author Lorena Lama
 */
public class MEXMEHistCama extends X_EXME_Hist_Cama {

	private static final long serialVersionUID = 1L;
	/** Static Logger */
	private static CLogger s_log = CLogger.getCLogger(MEXMEHistCama.class);

	private MEXMECama camaAnt = null;
	private MEXMECama camaAct = null;

	public MEXMEHistCama(Properties ctx, int EXME_Hist_Cama_ID, String trxName) {
		super(ctx, EXME_Hist_Cama_ID, trxName);
	}

	/**
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MEXMEHistCama(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

    /**
     * 
     * @param ctapacId
     * @return
     */
	public static Timestamp fechaUltimaActualizaCama(final int ctapacId) {
		final StringBuffer sql = new StringBuffer();
		sql.append("SELECT nvl(fecha_cambio,fecha_ingreso) as fecha_ultimaAct\n")
		.append("FROM (SELECT MAX(fecha_cambio) as fecha_cambio\n")
    	.append("       FROM EXME_Hist_Cama WHERE exme_ctapac_id=?) ")
    	.append(DB.isPostgreSQL() ? "AS maxDate " : StringUtils.EMPTY).append('\n')
    	.append("LEFT JOIN EXME_NotificaIngreso noti ON (noti.exme_ctapac_id=?)");
		return DB.getSQLValueTS(null, sql.toString(), ctapacId, ctapacId);
	}
    
	/**
	 * Borra registros para determinada cuenta paciente
	 * 
	 * @param ctx
	 * @param EXME_CtaPac_ID
	 * @param trxName
	 * @return
	 */
	public static void delete(Properties ctx, int EXME_CtaPac_ID, String trxName) throws SQLException {
		final String sql = "DELETE EXME_Hist_Cama WHERE EXME_CtaPac_ID=?";
		try {
			final int noReg = DB.executeUpdate(sql, EXME_CtaPac_ID, false, trxName);
			s_log.fine("EXME_Hist_Cama Num de Registros borrados = " + noReg);
		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
		}
	}

	/**
	 * Obtenemos la habitacion a la que pertenece la cama.
	 * 
	 * @return
	 */
	public MEXMECama getCamaAnt() {
		if (camaAnt == null && getEXME_Cama_Ant_ID() > 0) {
			camaAnt = new MEXMECama(getCtx(), getEXME_Cama_Ant_ID(), null);
		}
		return camaAnt;
	}

	/**
	 * Obtenemos la habitacion a la que pertenece la cama.
	 * 
	 * @return
	 */
	public MEXMECama getCamaAct() {
		if (camaAct == null && getEXME_Cama_Act_ID() > 0) {
			camaAct = new MEXMECama(getCtx(), getEXME_Cama_Act_ID(), null);
		}
		return camaAct;
	}

	/**
	 * 
	 * @param ctx
	 * @param camaID
	 * @param camaOldID
	 * @param ctapac
	 * @param trxName
	 * @return
	 */
	public static boolean guardar(Properties ctx, int camaID, int camaOldID, MEXMECtaPac ctapac, String trxName) {
		return save(ctx, camaID, camaOldID, -1, ctapac, trxName);
	}
	/**
	 * 
	 * @param ctx
	 * @param camaID
	 * @param camaOldID
	 * @param ctapac
	 * @param trxName
	 * @return
	 */
	public static boolean guardar(Properties ctx, int camaID, int camaOldID, int ctapac, String trxName) {
		return save(ctx, camaID, camaOldID, ctapac, null, trxName);
	}
	/**
	 * 
	 * @param ctx
	 * @param camaID
	 * @param camaOldID
	 * @param ctapacId
	 * @param ctapac
	 * @param trxName
	 * @return
	 */
	public static boolean save(Properties ctx, int camaID, int camaOldID, int ctapacId, MEXMECtaPac ctapac, String trxName) {
		boolean retValue = true;
		try {
			final MEXMEHistCama hCam = new MEXMEHistCama(ctx, 0, trxName);
			hCam.setEXME_Cama_Ant_ID(camaOldID);
			hCam.setEXME_Cama_Act_ID(camaID);

			if (hCam.getCamaAnt() != null && hCam.getCamaAnt().get_ID() > 0) {
				hCam.setEXME_EstServ_Ant_ID(hCam.getCamaAnt().getHabitacion().getEXME_EstServ_ID());
			}
			if (hCam.getCamaAct() != null && hCam.getCamaAct().get_ID() > 0) {
				hCam.setEXME_EstServ_Act_ID(hCam.getCamaAct().getHabitacion().getEXME_EstServ_ID());
			}
			final Timestamp fecha = MEXMEHistCama.fechaUltimaActualizaCama(ctapacId);

			if (fecha != null) {
				hCam.setFecha_Cambio_Ant(fecha);
			} else {
				if (ctapac == null) {
					ctapac = new MEXMECtaPac(ctx, ctapacId, trxName);
				}
				hCam.setFecha_Cambio_Ant(ctapac.getCreated());
			}
			hCam.setEXME_CtaPac_ID(ctapac == null ? ctapacId : ctapac.getEXME_CtaPac_ID());
			hCam.setAD_User_ID(Env.getAD_User_ID(ctx));

			if (!hCam.save()) {
				retValue = false;
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, e.getMessage(), e);
			retValue = false;
		}
		return retValue;
	}

	/**
	 * Nuevo metodo get regresa un arreglo de los registros segun un IC de cuentaPac
	 * 
	 * @param ctx
	 * @param EXME_CtaPac_ID
	 * @param trxName
	 * @return
	 */
	public static MEXMEHistCama[] get(Properties ctx, int EXME_CtaPac_ID, String trxName) {
		final List<MEXMEHistCama> list = new ArrayList<MEXMEHistCama>();

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);

		try {
			sql.append("SELECT * FROM EXME_Hist_Cama WHERE isActive = 'Y' AND EXME_CtaPac_ID = ? ");
			sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name));

			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, EXME_CtaPac_ID);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				list.add(new MEXMEHistCama(ctx, rs, trxName));
			}

		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
			pstmt = null;
			rs = null;
		}

		final MEXMEHistCama[] retValue = new MEXMEHistCama[list.size()];
		list.toArray(retValue);

		return retValue;
	}
}
