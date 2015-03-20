package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.DB;

public class MHL7Field extends X_HL7_Field {

	/**
	 * autogenerado para evitar warning
	 */
	private static final long serialVersionUID = -6498185672694469167L;
	
	/** Static Logger */
	private static CLogger s_log = CLogger.getCLogger(MHL7Version.class);


	public MHL7Field(Properties ctx, int HL7_Field_ID, String trxName) {
		super(ctx, HL7_Field_ID, trxName);
		
	}

	public MHL7Field(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		
	}
	
	/**
	 * metodo para pedir una version por nombre si el name que se pide es nullo
	 * no regresamos nada
	 */
	public static MHL7Field get(Properties ctx, String name) {

		if (name == null) {
			return null;
		}
		MHL7Field campo = null;

		String sql = "SELECT * FROM HL7_Field WHERE UPPER(name)=?";
		PreparedStatement pstmt = null;
		try {
			pstmt = DB.prepareStatement(sql, null);
			pstmt.setString(1, name.toUpperCase());
			ResultSet rs = pstmt.executeQuery();
			if (rs.next())
				campo = new MHL7Field(ctx, rs, null);
			rs.close();
			pstmt.close();
			pstmt = null;
		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql, e);
		}
		try {
			if (pstmt != null)
				pstmt.close();
			pstmt = null;
		} catch (Exception e) {
			pstmt = null;
		}

		return campo;
	}

}
