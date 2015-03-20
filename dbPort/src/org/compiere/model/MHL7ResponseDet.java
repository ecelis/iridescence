package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.compiere.minigrid.IDColumn;
import org.compiere.util.DB;
import org.compiere.util.GridItem;

public class MHL7ResponseDet extends X_HL7_ResponseDet implements GridItem {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5089860799903132347L;

	private String[] columns;
	
	private IDColumn idColumn;

	public MHL7ResponseDet(Properties ctx, int HL7_ResponseDet_ID,
			String trxName) {
		super(ctx, HL7_ResponseDet_ID, trxName);
		
	}

	public MHL7ResponseDet(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		
	}

	@Override
	public String[] getColumns() {
		if(columns == null){
			columns = new String[]{
					"idColumn",					
					"name",
					"script"
			};
		}
		
		
		
		return columns;
	}

	@Override
	public IDColumn getIdColumn() {
		if(idColumn==null){
			idColumn = new IDColumn(getHL7_ResponseDet_ID());
		}
		return idColumn;
	}
	
	public List<MHL7ResponseDet> getResponseDets(String trxName) {
		ArrayList<MHL7ResponseDet> dets = new ArrayList<MHL7ResponseDet>();

		StringBuilder sb = new StringBuilder();
		sb
				.append("SELECT rd.* FROM HL7_ResponseDet rd WHERE rd.HL7_ResponseDetParent_ID = ?");
		PreparedStatement stmt = DB.prepareStatement(sb.toString(), trxName);
		ResultSet rs = null;

		try {
			stmt.setInt(1, getHL7_ResponseDet_ID());
			rs = stmt.executeQuery();

			while (rs.next()) {
				dets.add(new MHL7ResponseDet(getCtx(), rs, trxName));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			try {
				if (stmt != null) {
					stmt.close();
				}
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

		return dets;

	}


}
