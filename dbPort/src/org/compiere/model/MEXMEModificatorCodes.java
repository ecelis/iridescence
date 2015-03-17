/**
 * 
 */
package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.struts.util.LabelValueBean;
import org.compiere.util.DB;

/**
 * @author luis
 *
 */
public class MEXMEModificatorCodes extends X_EXME_Modifiers {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5904750307394727067L;

	/**
	 * @param ctx
	 * @param EXME_ModificatorCodes_ID
	 * @param trxName
	 */
	public MEXMEModificatorCodes(Properties ctx, int EXME_ModificatorCodes_ID,
			String trxName) {
		super(ctx, EXME_ModificatorCodes_ID, trxName);
	}

	/**
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MEXMEModificatorCodes(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	
	public static List<LabelValueBean> get(Properties ctx, 
			String trxName) 
			throws Exception {
		List<LabelValueBean> lista = new ArrayList<LabelValueBean>();

		//buscamos el impuesto del producto
		StringBuilder sql = new StringBuilder()
		.append(" SELECT ")
		.append(X_EXME_Modifiers.COLUMNNAME_Name)
		.append(", ")
		.append(X_EXME_Modifiers.COLUMNNAME_EXME_Modifiers_ID)
		.append(" FROM ").append(X_EXME_Modifiers.Table_Name)
		.append(" WHERE IsActive = 'Y' ")
		.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", X_EXME_Modifiers.Table_Name))
		.append(" ORDER BY ")
		.append(X_EXME_Modifiers.COLUMNNAME_Name);

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				lista.add(new LabelValueBean(rs.getString(1), 
						String.valueOf(rs.getInt(2))));
			} 

		} catch (Exception e) {
			throw new Exception("error.impuesto.noExiste");
		}finally{
			DB.close(rs, pstmt);
		}

		return lista;
	}

}
