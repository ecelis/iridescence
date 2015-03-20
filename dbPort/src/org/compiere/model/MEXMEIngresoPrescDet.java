package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;

/**
 * @deprecated esta funcionalidad se reemplaza por {@link MEXMEPrescRXDet}
 */
public class MEXMEIngresoPrescDet extends X_EXME_IngresoPrescDet {

	private static final long serialVersionUID = 1L;
//	private static CLogger s_log = CLogger
//			.getCLogger(MEXMEIngresoPrescDet.class);
//	private String hora = null;
//	private String fecha = null;

//	public String getFecha() {
//		if (fecha == null) {
//			if (getFechaAplica() == null)
//				fecha = Constantes.getSdfFecha().format(getFechaProg());
//			else
//				fecha = Constantes.getSdfFecha().format(getFechaAplica());
//		}
//		return fecha;
//	}

//	public void setFecha(String fecha) {
//		this.fecha = fecha;
//	}

//	public String getHora() {
//		if (hora == null) {
//			if (getFechaAplica() == null)
//				hora = Constantes.getSdfHora().format(getFechaProg());
//			else
//				hora = Constantes.getSdfHora().format(getFechaAplica());
//		}
//		return hora;
//	}

//	public void setHora(String hora) {
//		this.hora = hora;
//	}

//	public MEXMEIngresoPrescDet(Properties ctx, int EXME_IngresoPrescDet_ID,
//			String trxName) {
//		super(ctx, EXME_IngresoPrescDet_ID, trxName);
//	}

	public MEXMEIngresoPrescDet(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

//	public static List<MEXMEIngresoPrescDet> get(Properties ctx, String where,
//			String trxName) {
//		StringBuilder st = new StringBuilder(
//				"SELECT * FROM EXME_IngresoPrescDet WHERE ");
//		if (where != null) {
//			st.append(where).append(" AND ");
//		}
//		st.append("isActive = 'Y' ");
//		st = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, st
//				.toString(), "EXME_IngresoPrescDet"));
//
//		List<MEXMEIngresoPrescDet> lista = new ArrayList<MEXMEIngresoPrescDet>();
//		PreparedStatement pstmt = null;
//		ResultSet rs = null; // Expert
//		try {
//			pstmt = DB.prepareStatement(st.toString(), null);
//			rs = pstmt.executeQuery();
//			while (rs.next()) {
//				lista.add(new MEXMEIngresoPrescDet(ctx, rs, trxName));
//			}
//			
//		} catch (Exception e) {
//			s_log.log(Level.SEVERE, st.toString(), e);
//		} finally {
//			DB.close(rs, pstmt);
//		}
//		return lista;
//	}

//	public static List<MEXMEIngresoPrescDet> get(Properties ctx, int id,
//			String trxName) {
//		StringBuilder st = new StringBuilder(
//				"SELECT * FROM EXME_IngresoPrescDet WHERE ");
//		st.append("isActive = 'Y' and EXME_IngresoPresc_ID = ? ");
//		st = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, st
//				.toString(), "EXME_IngresoPrescDet"));
//
//		st.append(" order by fechaProg");
//
//		List<MEXMEIngresoPrescDet> lista = new ArrayList<MEXMEIngresoPrescDet>();
//		PreparedStatement pstmt = null;
//		ResultSet rs = null; // Expert
//		try {
//			pstmt = DB.prepareStatement(st.toString(), null);
//			pstmt.setInt(1, id);
//			rs = pstmt.executeQuery();
//			while (rs.next()) {
//				lista.add(new MEXMEIngresoPrescDet(ctx, rs, trxName));
//			}
//			
//		} catch (Exception e) {
//			s_log.log(Level.SEVERE, st.toString(), e);
//		} finally {
//			DB.close(rs, pstmt);
//		}
//		return lista;
//	}

//	@Override
//	public void setAplicacion(String Aplicacion) {
//		if(Aplicacion!=null && (!Aplicacion.equals(""))){
//			super.setAplicacion(Aplicacion);
//		}else{
//			super.setAplicacion(null);
//		}
//		
//	}

//	public MEXMEIngresoPrescDet(Properties ctx, int EXME_IngresoPrescDet_ID,
//			String trxName, String hora) {
//		super(ctx, EXME_IngresoPrescDet_ID, trxName);
//		this.hora = hora;
//	}
	
	

}
