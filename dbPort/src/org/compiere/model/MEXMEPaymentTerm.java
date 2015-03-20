package org.compiere.model;

/***@deprecated El estandar es MPaymentTerm */
public class MEXMEPaymentTerm {} 
//extends MPaymentTerm{
//
//	/**
//	 * 
//	 */
//	private static final long serialVersionUID = 1L;
//	/**	Static Logger				*/
//	private static CLogger		s_log = CLogger.getCLogger (MEXMEPaymentTerm.class);
//	
//	public MEXMEPaymentTerm(Properties ctx, ResultSet rs, String trxName) {
//		super(ctx, rs, trxName);
//	} //	MPaymentTerm
//	
//	/**
//	 * 	Get Payment Schedule
//	 * 	@param requery if true re-query
//	 *	@return array of schedule
//	 *@deprecated Regresa un solo valor, pero itera todas las condiciones de pago
//	 */
//	public static MEXMEPaymentTerm getDefault(Properties ctx, String trxName) {
//		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
//		sql.append("SELECT * FROM C_PaymentTerm WHERE IsActive = 'Y' AND IsDefault='Y' "); 
//		
//		sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, sql.toString(), "C_PaymentTerm"));
//		
//		sql.append(" ORDER BY C_PaymentTerm_ID ASC");
//		MEXMEPaymentTerm pay = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		try {
//			pstmt = DB.prepareStatement(sql.toString(), trxName);
//
//			rs = pstmt.executeQuery();
//			while (rs.next()) {
//				pay = new MEXMEPaymentTerm(ctx, rs, trxName);
//			}
//			
//		} catch (Exception e) {
//			s_log.log(Level.SEVERE, "getSchedule", e);
//		} finally {
//			DB.close(rs, pstmt);
//		}
//		
//		return pay;
//	}
//}
