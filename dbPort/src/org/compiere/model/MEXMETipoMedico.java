package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.apache.struts.util.LabelValueBean;
import org.compiere.util.CLogger;
import org.compiere.util.DB;

public class MEXMETipoMedico extends X_EXME_TipoMedico {

	private static final long serialVersionUID = 5876779174014471109L;
	private static CLogger s_log = CLogger.getCLogger(MEXMETipoMedico.class);

	public MEXMETipoMedico(Properties ctx, int EXME_TipoMedico_ID, String trxName) {
		super(ctx, EXME_TipoMedico_ID, trxName);
	}

	public MEXMETipoMedico(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	public static List<LabelValueBean> getFromSystem() {
		StringBuilder st = new StringBuilder("select name, exme_tipomedico_id from EXME_TipoMedico ");
		st.append(" where ad_client_id = 0 and ad_org_id = 0");
		List<LabelValueBean> lst = new ArrayList<LabelValueBean>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(st.toString(), null);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				lst.add(new LabelValueBean(rs.getString(1), rs.getString(2)));
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, st.toString(), e);
		} finally {
			DB.close(rs, pstmt);
		}
		return lst;
	}

}
