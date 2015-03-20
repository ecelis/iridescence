package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.apache.struts.util.LabelValueBean;
import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.Env;

/**
 * 
 * @author igarcia
 *
 */
public class MEXMETipoHabitacion extends X_EXME_TipoHabitacion {

	private static final long serialVersionUID = 2016759481751384933L;
	private static CLogger	s_log	= CLogger.getCLogger (MEXMETipoHabitacion.class);

	public MEXMETipoHabitacion(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	public MEXMETipoHabitacion(Properties ctx, int EXME_TipoHabitacion_ID, String trxName) {
		super(ctx, EXME_TipoHabitacion_ID, trxName);
	}

	/**
	 * Obtiene todo los tipos de habitacion
	 * @param ctx
	 * @param userName
	 * @return MEXMETipoHabitacion
	 */
	public static List<MEXMETipoHabitacion> getRoomTypes(Properties ctx, String trxName) {
		StringBuilder st = new StringBuilder("SELECT * FROM EXME_TipoHabitacion where EXME_TipoHabitacion.IsActive = 'Y' ");
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<MEXMETipoHabitacion> roomTypes = new ArrayList<MEXMETipoHabitacion>();
		try {
			st = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, st.toString(), "EXME_TipoHabitacion"));
			pstmt = DB.prepareStatement(st.toString(), null);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				MEXMETipoHabitacion roomType = new MEXMETipoHabitacion(Env.getCtx(), rs, trxName);
				roomTypes.add(roomType);
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, st.toString(), e);
		} finally {
			DB.close(rs, pstmt);
		}
		return roomTypes;
	}
	
	/*
	 * Objeto rev. code
	 *
	private MEXMERevenueCodes revenueCodes = null; 
	
	public MEXMERevenueCodes getRevenueCodes() {
		if(revenueCodes==null)
			revenueCodes = new MEXMERevenueCodes(getCtx(), getEXME_RevenueCode_ID(),get_TrxName());
		return this.revenueCodes;
	}

	public void setRevenueCodes(MEXMERevenueCodes revenueCodes) {
		this.revenueCodes = revenueCodes;
	}
	
	/**
	 * Nombre del rev. code
	 *
	private String revenueCodeName = null;

	public String getRevenueCodeName() {
		if(revenueCodeName==null)
			revenueCodeName = getRevenueCodes().getName();
		return this.revenueCodeName;
	}

	public void setRevenueCodeName(String revenueCodeName) {
		this.revenueCodeName = revenueCodeName;
	}
	*/
	
	/**
	 * Obtiene tipo de habitacion apartir de un producto
	 * en teoria no deberian repetirse los productos
	 * en caso de que fuera asi
	 * solo tomara el primero
	 * @param ctx
	 * @param userName
	 * @return MEXMETipoHabitacion
	 */
	public static MEXMETipoHabitacion getRoomProduct(Properties ctx, int M_Product_ID, String trxName) {
		StringBuilder st = new StringBuilder("SELECT * FROM EXME_TipoHabitacion where EXME_TipoHabitacion.IsActive = 'Y' ");
		st.append(" AND M_Product_ID = ? ORDER BY EXME_TipoHabitacion_ID DESC ");
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MEXMETipoHabitacion roomType = null;
		try {
			st = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, st.toString(), "EXME_TipoHabitacion"));
			pstmt = DB.prepareStatement(st.toString(), null);
			pstmt.setInt(1, M_Product_ID);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				roomType = new MEXMETipoHabitacion(Env.getCtx(), rs, trxName);
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, st.toString(), e);
		} finally {
			DB.close(rs, pstmt);
		}
		return roomType;
	}
	
	public static List<LabelValueBean> getRoomsVB(Properties ctx) {
		List<LabelValueBean> lstReturn = new ArrayList<LabelValueBean>();

		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append("SELECT * FROM EXME_TIPOHABITACION WHERE CHARGESDAILY = 'Y' ")
		   .append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", "EXME_TipoHabitacion")) //EALVAREZ ticket 04872  Se agrego el nivel de Acceso.
		   .append(" ORDER BY VALUE ");
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				final MEXMETipoHabitacion tipoHab = new MEXMETipoHabitacion(Env.getCtx(), rs, null);
				lstReturn.add(new LabelValueBean(tipoHab.getValue() + " " + tipoHab.getName(),String.valueOf(tipoHab.getEXME_TipoHabitacion_ID())));

			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
		}
		return lstReturn;
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
}
