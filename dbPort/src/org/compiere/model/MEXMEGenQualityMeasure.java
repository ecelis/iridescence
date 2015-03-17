package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.struts.util.LabelValueBean;
import org.compiere.util.DB;

public class MEXMEGenQualityMeasure extends X_EXME_GenQualityMeasure{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6257059438321191665L;


	public MEXMEGenQualityMeasure(Properties ctx, int EXME_GenQualityMeasure_ID, String trxName) {
		super(ctx, EXME_GenQualityMeasure_ID, trxName);
	}
	
	public MEXMEGenQualityMeasure(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	
	public static List<LabelValueBean> getFechaReportes(Properties ctx, String reportValue) {

		List<LabelValueBean> resultados = new ArrayList<LabelValueBean>();
		// Lista de prioridades
		StringBuilder sql = new StringBuilder();
		
		sql.append("	select to_char(CREATED, 'DD/MM/YYYY HH24:MI'), EXME_GENQUALITYMEASURE_ID") 
		   .append("	from EXME_GENQUALITYMEASURE")
		   .append("	where REPORT = ? ")
		   .append("	order by CREATED desc");
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setString(1, reportValue);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				LabelValueBean lvb = new LabelValueBean(rs
						.getString(1), rs.getString(2));
				resultados.add(lvb);
			}			
		} catch (SQLException e) {
			e.printStackTrace();	//FIXME		
		} finally {
			DB.close(rs,pstmt);
		}
		return resultados;
	}
}
