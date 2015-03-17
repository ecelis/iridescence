package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;

/**
 * @deprecated esta funcionalidad se reemplaza por {@link MEXMEPrescRXDet}
 */
public class MEXMEIngresoPresc extends X_EXME_IngresoPresc {

	private static final long serialVersionUID = 1L;
//	private List<MEXMEIngresoPrescDet> lstDet = null;
//	private static CLogger s_log = CLogger.getCLogger(MEXMEIngresoPresc.class);
//	private MProduct producto = null;
//	private MEXMEViasAdministracion via = null;
//	private MEXMEMedico medico = null;
//	private String indicacion = null;
//	private MEXMEObservacion observacion = null;
//	private String app = null;

//	public String getApp() {
//		if (app == null) {
//			if (getAplicacion() != null && getAplicacion().trim().length() > 0) {
//				app = MRefList.getListName(getCtx(),
//						APLICACION_AD_Reference_ID, getAplicacion());
//			}
//		}
//		return app;
//	}

//	public void setApp(String app) {
//		this.app = app;
//	}

//	public MEXMEObservacion getObservacion() {
//		if (observacion == null) {
//			observacion = MEXMEObservacion.get(getCtx(), Table_ID,
//					getEXME_IngresoPresc_ID());
//		}
//		return observacion;
//	}

//	public void setObservacion(MEXMEObservacion observacion) {
//		this.observacion = observacion;
//	}

//	public String getIndicacion() {
//		if (indicacion == null) {
//			StringBuilder st = new StringBuilder();
//			st.append(getCantidad()).append(" ");
//			MUOM uom = new MUOM(getCtx(), getC_UOM_ID(), null);
//			st.append(uom.getTrlName()).append(" ");
//			if (getM_Product_ID() > 0) {
//				st.append(getProducto().getName()).append(" ");
//			}
//			st.append(", ").append(getVia().getName()).append(", ");
//			if (getApp() != null) {
//				st.append(getApp()).append(", ");
//			}
//			st.append(getFreightAmt()).append(" ");
//			st.append(getUnidad()).append(" ");
//			indicacion = st.toString();
//		}
//		return indicacion;
//	}

//	public void setIndicacion(String indicacion) {
//		this.indicacion = indicacion;
//	}

//	public MEXMEMedico getMedico() {
//		if (medico == null) {
//			medico = new MEXMEMedico(getCtx(), getEXME_Medico_ID(), null);
//		}
//		return medico;
//	}

//	public void setMedico(MEXMEMedico medico) {
//		this.medico = medico;
//	}

//	public MEXMEViasAdministracion getVia() {
//		if (via == null) {
//			via = new MEXMEViasAdministracion(getCtx(),
//					getEXME_ViasAdministracion_ID(), null);
//		}
//		return via;
//	}

//	public void setVia(MEXMEViasAdministracion via) {
//		this.via = via;
//	}

//	public MProduct getProducto() {
//		if (producto == null) {
//			producto = new MProduct(getCtx(), getM_Product_ID(), null);
//		}
//		return producto;
//	}

//	public void setProducto(MProduct producto) {
//		this.producto = producto;
//	}

//	public List<MEXMEIngresoPrescDet> getLstDet() {
//		if (lstDet == null) {
//			lstDet = MEXMEIngresoPrescDet.get(getCtx(),
//					getEXME_IngresoPresc_ID(), get_TrxName());
//		}
//		return lstDet;
//	}

//	public void setLstDet(List<MEXMEIngresoPrescDet> lstDet) {
//		this.lstDet = lstDet;
//	}

//	public MEXMEIngresoPresc(Properties ctx, int EXME_IngresoPresc_ID,
//			String trxName) {
//		super(ctx, EXME_IngresoPresc_ID, trxName);
//	}

	public MEXMEIngresoPresc(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

//	public static List<MEXMEIngresoPresc> get(Properties ctx, String where,
//			boolean isActive, String trxName) {
//		StringBuilder st = new StringBuilder(
//		"SELECT * FROM EXME_IngresoPresc WHERE ");
//		if (where != null) {
//			st.append(where).append(" AND ");
//		}
//		st.append("EXME_CtaPac_ID = ? ");
//		if (isActive) {
//			st.append("isActive = 'Y' ");
//		} else {
//			st.append("isActive = 'N' ");
//		}
//		st = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, st
//				.toString(), "EXME_IngresoPresc"));
//
//		st.append(" order by fechaInicio ");
//
//		List<MEXMEIngresoPresc> lista = new ArrayList<MEXMEIngresoPresc>();
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		try {
//			pstmt = DB.prepareStatement(st.toString(), null);
//			rs = pstmt.executeQuery();
//			while (rs.next()) {
//				lista.add(new MEXMEIngresoPresc(ctx, rs, trxName));
//			}
//			
//		} catch (Exception e) {
//			s_log.log(Level.SEVERE, st.toString(), e);
//		} finally {
//			DB.close(rs, pstmt);
//		}
//		return lista;
//	}

//	public static List<MEXMEIngresoPresc> get(Properties ctx, String where,
//			int EXME_CtaPac_ID, String trxName) {
//		StringBuilder st = new StringBuilder(
//		"SELECT * FROM EXME_IngresoPresc WHERE ");
//		if (where != null) {
//			st.append(where).append(" AND ");
//		}
//		st.append("isActive = 'Y' AND EXME_CtaPac_ID = ? ");
//		st = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, st
//				.toString(), "EXME_IngresoPresc"));
//
//		st.append(" order by fechaInicio ");
//
//		List<MEXMEIngresoPresc> lista = new ArrayList<MEXMEIngresoPresc>();
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		try {
//			pstmt = DB.prepareStatement(st.toString(), null);
//			pstmt.setInt(1, EXME_CtaPac_ID);
//			rs = pstmt.executeQuery();
//			while (rs.next()) {
//				lista.add(new MEXMEIngresoPresc(ctx, rs, trxName));
//			}
//			
//		} catch (Exception e) {
//			s_log.log(Level.SEVERE, st.toString(), e);
//		} finally {
//			DB.close(rs, pstmt);
//		}
//		return lista;
//	}

//	public static List<MEXMEIngresoPresc> get(Properties ctx, String date,
//			int EXME_CtaPac_ID, int EXME_ProductFam_ID, boolean isActive,
//			String trxName) {
//		StringBuilder st = new StringBuilder(
//		"SELECT * FROM EXME_IngresoPresc presc ")
//		.append("INNER JOIN M_Product prod on prod.M_Product_ID = presc.M_Product_ID ")
//		.append("WHERE EXME_CtaPac_ID = ? ")
//		.append("AND prod.EXME_ProductFam_ID = ? ");
//		if (date != null)
//			st.append("AND  presc.fechaFin >= to_date(?, 'dd/MM/yyyy') ");				
//		if (isActive) {
//			st.append("AND presc.isActive = 'Y' ");
//		} else {
//			st.append("AND presc.isActive = 'N' ");
//		}
//		st = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, st
//				.toString(), "EXME_IngresoPresc", "presc"));
//
//		st.append(" order by presc.fechaInicio ");
//
//		List<MEXMEIngresoPresc> lista = new ArrayList<MEXMEIngresoPresc>();
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		try {
//			pstmt = DB.prepareStatement(st.toString(), null);			
//			pstmt.setInt(1, EXME_CtaPac_ID);			
//			pstmt.setInt(2, EXME_ProductFam_ID);
//			if (date != null)
//				pstmt.setString(3, date);
//			rs = pstmt.executeQuery();
//			while (rs.next()) {
//				lista.add(new MEXMEIngresoPresc(ctx, rs, trxName));
//			}
//			
//		} catch (Exception e) {
//			s_log.log(Level.SEVERE, st.toString(), e);
//		} finally {
//			DB.close(rs, pstmt);
//		}
//		return lista;
//	}

//	public static String getCDNs(Properties ctx, String date,
//			int EXME_CtaPac_ID, int EXME_ProductFam_ID, boolean isActive,
//			String trxName) {
//		StringBuilder st = new StringBuilder(
//		"SELECT prod.pmid FROM EXME_IngresoPresc presc ")
//		.append("INNER JOIN M_Product prod on prod.M_Product_ID = presc.M_Product_ID ")
//		.append("WHERE EXME_CtaPac_ID = ? ")
//		.append("AND prod.EXME_ProductFam_ID = ? ");
//		if (date != null)
//			st.append("AND  presc.fechaFin >= to_date(?, 'dd/MM/yyyy') ");				
//		if (isActive) {
//			st.append("AND presc.isActive = 'Y' ");
//		} else {
//			st.append("AND presc.isActive = 'N' ");
//		}
//		st = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, st
//				.toString(), "EXME_IngresoPresc", "presc"));
//
//		st.append(" order by presc.fechaInicio ");
//
//		String lista = "";
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		try {
//			pstmt = DB.prepareStatement(st.toString(), null);			
//			pstmt.setInt(1, EXME_CtaPac_ID);			
//			pstmt.setInt(2, EXME_ProductFam_ID);
//			if (date != null)
//				pstmt.setString(3, date);
//			rs = pstmt.executeQuery();
//			while (rs.next()) {
//				if(lista.equalsIgnoreCase(""))
//					lista = rs.getString(1);
//				else
//					lista = lista + "@@@"+rs.getString(1);
//			}
//			
//		} catch (Exception e) {
//			s_log.log(Level.SEVERE, st.toString(), e);
//		} finally {
//			DB.close(rs, pstmt);
//		}
//		return lista;
//	}

//	public MEXMEIngresoPresc(Properties ctx, int EXME_IngresoPresc_ID,
//			String trxName, List<MEXMEIngresoPrescDet> lstDet, MProduct producto) {
//		super(ctx, EXME_IngresoPresc_ID, trxName);
//		this.lstDet = lstDet;
//		this.producto = producto;
//	}

}
