package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

import org.compiere.util.DB;
import org.compiere.util.Env;

public class MEXMEConversionType extends MConversionType {

	private static final long serialVersionUID = 1L;

	/**************************************************************************
	 * 	Standard Constructor
	 *	@param ctx context
	 *	@param C_ConversionType_ID id
	 *	@param trxName transaction
	 */
	public MEXMEConversionType(Properties ctx, int C_ConversionType_ID, String trxName)
	{
		super(ctx, C_ConversionType_ID, trxName);
	}	//	MConversionType

	
	/**
	 * Obtener el Tipo de Conversion de Moneda
	 */
	public static MConversionRate getConversionType(Properties ctx, int C_currency_ID, int C_Currency_ID_To, String trxName) {
		MConversionRate conversion_id = null;
		StringBuilder sql = new StringBuilder(100);

		sql.append("SELECT * FROM C_Conversion_Rate ")
			.append(" WHERE isActive = 'Y' ")
			.append(" AND C_CURRENCY_ID = ? ")
			.append(" AND C_CURRENCY_ID_TO = ? ")
			.append(MEXMELookupInfo.addAccessLevelSQL(Env.getCtx(), " ", "C_Conversion_Rate"));
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, C_currency_ID);
			pstmt.setInt(2, C_Currency_ID_To);
			
			rs = pstmt.executeQuery();
			if (rs.next()) {
				conversion_id = new MConversionRate(ctx, rs, trxName) ;			
			}

		} catch (Exception e) {
			e.printStackTrace();//FIXME
		} finally {
			DB.close(rs,pstmt);
		}
		
		return conversion_id;
	}
}
