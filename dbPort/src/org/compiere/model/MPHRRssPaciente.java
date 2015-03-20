package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.DB;

/**
 * 
 * @author freyes
 *
 */
public class MPHRRssPaciente extends X_PHR_RssPac {

	private static final long serialVersionUID = 1992235485042866447L;
	private static CLogger s_log = CLogger.getCLogger(MPHRRssPaciente.class);

	public MPHRRssPaciente(Properties ctx, int PHR_RssPac_ID, String trxName) {
		super(ctx, PHR_RssPac_ID, trxName);
	}

	public MPHRRssPaciente(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	public static List<MPHRRssPaciente> get(Properties ctx, int patientId, String trxName) {
		StringBuilder st = new StringBuilder("select * from PHR_RssPac ");
		st.append("where EXME_Paciente_ID = ? ");
		st = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, st.toString(), MPHRRssPaciente.Table_Name));
		st.append(" order by created");
		List<MPHRRssPaciente> lista = new ArrayList<MPHRRssPaciente>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(st.toString(), trxName);
			pstmt.setInt(1, patientId);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				lista.add(new MPHRRssPaciente(ctx, rs, trxName));
			}
			rs.close();
			pstmt.close();
			pstmt = null;
		} catch (Exception e) {
			s_log.log(Level.SEVERE, st.toString(), e);
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				pstmt = null;
				if (rs != null)
					rs.close();
				rs = null;
			} catch (Exception e) {
				pstmt = null;
				rs = null;
			}
		}
		return lista;
	}

}
