package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.DB;

public class MEXMEDrugFormulary extends X_EXME_DrugFormulary{

	private static CLogger slog = CLogger.getCLogger(MEXMEDrugFormulary.class);
	
	public MEXMEDrugFormulary(Properties ctx, int EXME_DrugFormulary_ID,
			String trxName) {
		super(ctx, EXME_DrugFormulary_ID, trxName);
		// TODO Auto-generated constructor stub
	}
	
	public MEXMEDrugFormulary(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}
	
	public static List<MEXMEDrugFormulary> getFormulary(final Properties ctx,
			final Integer pacienteID, final String trxName) {
		PreparedStatement pstmt = null;
		ResultSet rSet = null;
		final List<MEXMEDrugFormulary> lst = new ArrayList<MEXMEDrugFormulary>();
		try {
			final StringBuilder sql = new StringBuilder();
			sql.append(" select * from EXME_DrugFormulary where EXME_PACIENTE_ID = ? and isactive = 'Y' ");

			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, pacienteID);
			rSet = pstmt.executeQuery();

			while (rSet.next()) {
				lst.add(new MEXMEDrugFormulary(ctx, rSet, null));
			}

		} catch (Exception e) {
			slog.log(Level.SEVERE, "get", e);
		} finally {
			DB.close(rSet, pstmt);
			rSet = null;
			pstmt = null;
		}

		return lst;
	}
	
	protected boolean beforeSave (boolean newRecord) {	
		setAD_Org_ID(0);
		setAD_Client_ID(0);
		return true;
	}

}
