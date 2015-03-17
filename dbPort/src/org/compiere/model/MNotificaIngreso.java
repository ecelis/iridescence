/*

 * Created on 31-ene-2006

 *

 */

package org.compiere.model;

import java.util.Properties;

/**
 * 
 * Notificacion de ingreso de pacientes
 * 
 * @author Omar Torres
 * @deprecated Tabla Medsys , will be removed
 */

public class MNotificaIngreso extends X_EXME_NotificaIngreso {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** static logger */
//	private static CLogger s_log = CLogger.getCLogger(MNotificaIngreso.class);

	/**
	 * @param ctx
	 * @param EXME_Cama_ID
	 * @param trxName
	 */

	public MNotificaIngreso(Properties ctx, int EXME_NotificaIngreso_ID, String trxName) {
		super(ctx, EXME_NotificaIngreso_ID, trxName);
	}

	/**
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
//	public MNotificaIngreso(Properties ctx, ResultSet rs, String trxName) {
//		super(ctx, rs, trxName);
//	}
	
	/**
	 * Regresa un listado de notificaciones de ingreso del paciente
	 * @param ctx
	 * @param EXME_CtaPac_ID
	 * @param trxName
	 * @return
	 *
	public static MNotificaIngreso getFromCtaPac(Properties ctx, int EXME_CtaPac_ID, String trxName) {
		return getLast(ctx, EXME_CtaPac_ID, -1, trxName);
	}
	
	/**
	 * Regresa un listado de notificaciones de ingreso del paciente
	 * @param ctx
	 * @param EXME_CtaPac_ID
	 * @param trxName
	 * @return
	 *
	public static MNotificaIngreso getLast(Properties ctx, int EXME_CtaPac_ID, int EXME_Hist_Exp_ID, String trxName) {
		MNotificaIngreso retValue = null;
		List<MNotificaIngreso> list = getList(ctx, EXME_CtaPac_ID, EXME_Hist_Exp_ID, trxName);
		if(!list.isEmpty()){
			// regresamos el mas reciente
			retValue = list.get(list.size()-1);
		}		
		return retValue;
	}
	
	/**
	 * Regresa un listado de notificaciones de ingreso del paciente
	 * @param ctx
	 * @param EXME_CtaPac_ID
	 * @param trxName
	 * @return
	 *
	public static List<MNotificaIngreso> getList(Properties ctx, int EXME_CtaPac_ID, int EXME_Hist_Exp_ID,
			String trxName) {
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<MNotificaIngreso> retValue = new ArrayList<MNotificaIngreso>();

		try {
			sql.append("SELECT * FROM EXME_NotificaIngreso WHERE isActive = 'Y' ");
			if (EXME_CtaPac_ID > 0)
				sql.append("AND EXME_CtaPac_ID = ? ");
			if (EXME_Hist_Exp_ID > 0)
				sql.append(" AND EXME_Hist_Exp_ID = ? ");
			sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name));
			sql.append(" ORDER BY Created DESC ");
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			int i = 1;
			if (EXME_CtaPac_ID > 0)
				pstmt.setInt(i++, EXME_CtaPac_ID);
			if (EXME_Hist_Exp_ID > 0)
				pstmt.setInt(i++, EXME_Hist_Exp_ID);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				retValue.add(new MNotificaIngreso(ctx, rs, trxName));
			}

		} catch (SQLException e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
		} catch (Exception e) {
			s_log.log(Level.SEVERE, e.getMessage(), e);
		} finally {
			DB.close(rs, pstmt);
		}

		return retValue;
	}
	/**
	 * ErickG
	 * Funcion que permite validar si traemos una ctaPac ID, entonces mandamos un valo para usarlo en el Action
	 * y asi poder mostrar ciertos iconos
	 * @param ctx
	 * @param CtaPacID
	 * @param trxName
	 * @return
	 * @throws SQLException
	 *
	public static int isCtaPacAlready(Properties ctx, int CtaPacID, String trxName)
	throws SQLException{
		
		String sql = "  SELECT EXME_CtaPac_ID from EXME_NotificaIngreso WHERE EXME_CtaPac_ID = " + CtaPacID;

	
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int regreso = 0;
		
		sql = MEXMELookupInfo.addAccessLevelSQL(ctx, sql, "EXME_NotificaIngreso");
		
		try {
	

			pstmt = null;
			
		
				pstmt = DB.prepareStatement(sql, trxName);
				rs = pstmt.executeQuery(sql);
				while(rs.next())
				{
					regreso = 1;
				}
				rs.close();
				
				pstmt.close();
				pstmt = null;
				
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if (pstmt != null)
						pstmt.close ();
				} catch (Exception e) {}
				pstmt = null;
			}
	
			return regreso;
	}*/
}
