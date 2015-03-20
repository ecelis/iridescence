package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MEXMEEnfControlada extends X_EXME_EnfControlada {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
//	private static CLogger s_log = CLogger.getCLogger(MEXME_EnfControlada.class);

	public MEXMEEnfControlada(Properties ctx, int EXME_EnfControlada_ID, String trxName) {
		super(ctx, EXME_EnfControlada_ID, trxName);
	}

	public MEXMEEnfControlada(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

//	public static ArrayList<Enfermedad_VO> getEnfermedades() throws Exception {
//
//		final ArrayList<Enfermedad_VO> ret = new ArrayList<Enfermedad_VO>();
//
//		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
//
//		sql.append("select EXME_ENFCONTROLADA_ID,DESCRIPTION,VALUE,NAME from EXME_ENFCONTROLADA ");
//
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//
//		try {
//			pstmt = DB.prepareStatement(sql.toString(), null);
//
//			rs = pstmt.executeQuery();
//			Enfermedad_VO vo = null;
//
//			while (rs.next()) {
//				vo = new Enfermedad_VO();
//				vo.setEnfermedadID(rs.getInt(1));
//				vo.setDescripcion(rs.getString(2));
//				vo.setValue(rs.getString(3));
//				vo.setName(rs.getString(4));
//
//				ret.add(vo);
//			}
//
//		} catch (Exception e) {
//			s_log.log(Level.SEVERE, "MPregunta.getPreguntasCuestionario", e);
//		} finally {
//			DB.close(rs, pstmt);
//		}
//		return ret;
//	}
}
