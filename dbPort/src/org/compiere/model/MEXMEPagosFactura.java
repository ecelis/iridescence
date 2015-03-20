package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.compiere.util.Constantes;
import org.compiere.util.DB;

public class MEXMEPagosFactura {

	/**
	 * Pagos relacionados a la factura por medio del allocation
	 * EXCLUYENDO aquellos que fueron generados por un cash line como Anticipos
	 * @param ctx
	 * @param C_Invoice_ID
	 * @param trxName
	 * @return
	 * @throws Exception
	 */
	public static List<MPayment> pagosCxCAllocation(Properties ctx, int C_Invoice_ID,
			String trxName) throws Exception{

		List<MPayment> lista = new ArrayList<MPayment>();

		if (ctx == null) {
			return null;
		}

		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append(" SELECT C_Payment.* FROM C_Invoice ")
		.append(" INNER JOIN C_AllocationLine ON C_Invoice.C_Invoice_ID = C_AllocationLine.C_Invoice_ID ")
		.append(" INNER JOIN C_Payment ON C_AllocationLine.C_Payment_ID = C_Payment.C_Payment_ID  ")
		.append(" WHERE C_Invoice.IsActive = 'Y' ")
		.append(" AND  C_Invoice.C_Invoice_ID = ? ")
		.append(" AND C_Payment.C_Payment_ID NOT IN ( ")
		.append(" SELECT C_Payment_ID  FROM C_CashLine WHERE IsActive = 'Y' AND C_Payment_ID IS NOT NULL ")
		.append(" ) ")
		.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", "C_Invoice"));

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);

			pstmt.setInt(1, C_Invoice_ID);

			rs = pstmt.executeQuery();

			while(rs.next()){
				MPayment m_pago = new MPayment(ctx, rs, trxName);
				lista.add(m_pago);
			}
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}finally{
			DB.close(rs, pstmt);
			pstmt=null;
			rs=null;

		}
		return lista;
	}


	public static List<MPayment> pagosCxCPayment(Properties ctx, int C_Invoice_ID,
			String trxName) throws Exception{

		List<MPayment> lista = new ArrayList<MPayment>();

		if (ctx == null) {
			return null;
		}

		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append(" SELECT C_Payment.* FROM C_Invoice  ")
		.append(" INNER JOIN C_Payment ON C_Payment.C_Invoice_ID = C_Invoice.C_Invoice_ID AND C_Payment.IsActive = 'Y' ")
		.append(" WHERE C_Invoice.IsActive = 'Y' AND  C_Invoice.C_Invoice_ID = ? ")
		.append(" AND C_Payment.DocStatus = 'DR' AND C_Payment.C_Payment_ID NOT IN (  ")
		.append(" SELECT C_Payment_ID FROM C_AllocationLine  ")
		.append(" WHERE IsActive = 'Y' ")
		.append(" AND C_Payment_ID IS NOT NULL AND C_Invoice_ID = ? ")
		.append(" ) ")
		.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", "C_Invoice"));

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);

			pstmt.setInt(1, C_Invoice_ID);
			pstmt.setInt(2, C_Invoice_ID);

			rs = pstmt.executeQuery();

			while(rs.next()){
				MPayment m_pago = new MPayment(ctx, rs, trxName);
				lista.add(m_pago);
			}
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}finally{
			DB.close(rs, pstmt);
			pstmt=null;
			rs=null;

		}
		return lista;
	}
}
