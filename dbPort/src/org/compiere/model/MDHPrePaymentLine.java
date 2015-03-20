/**
 * 
 */
package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.model.bean.PrePaymentLineBean;
import org.compiere.util.CLogger;
import org.compiere.util.DB;

/**
 * Detalle del prepago (pro mujer)
 * 
 * @author Lorena Lama
 *         Card http://control.ecaresoft.com/card/1548/
 */
public class MDHPrePaymentLine extends X_DH_PrePaymentLine {

	private static final long	serialVersionUID	= 1L;
	private static CLogger		sLog				= CLogger.getCLogger(MDHPrePaymentLine.class);

	private MDHPrePayment header;
	/**
	 * @param ctx
	 * @param DH_PrePaymentLine_ID
	 * @param trxName
	 */
	public MDHPrePaymentLine(final Properties ctx, final int DH_PrePaymentLine_ID, final String trxName) {
		super(ctx, DH_PrePaymentLine_ID, trxName);
	}

	/**
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MDHPrePaymentLine(final Properties ctx, final ResultSet rs, final String trxName) {
		super(ctx, rs, trxName);
	}

	/**
	 * Listado de lineas detalle de un pre pago
	 * 
	 * @param ctx Contexto
	 * @param prepaymentId Id del encabezado
	 * @param trxName nombre de la transaccion
	 * @return
	 */
	public static List<MDHPrePaymentLine> getLines(final Properties ctx, final int prepaymentId, final String trxName) {
		return new Query(ctx, Table_Name, " DH_PrePayment_ID=? ", trxName)//
			.setOnlyActiveRecords(true)//
			.addAccessLevelSQL(true)//
			.setParameters(prepaymentId)//
			.list();
	}

	/**
	 * Listado de servicios / paquetes de un pre pago
	 * 
	 * @param ctx Contexto
	 * @param prepaymentId
	 * @param paid null: todas las lineas true: solo las lineas pagadas false: las lineas pendientes de pago
	 * @param trxName nombre de la transaccion
	 * @return
	 * @throws SQLException
	 */
	public static List<PrePaymentLineBean> getList(final Properties ctx, final int prepaymentId, final Boolean paid, final String trxName)
		throws SQLException {
		final List<PrePaymentLineBean> list = new ArrayList<>();

		final StringBuilder sql = new StringBuilder();
		sql.append(" SELECT l.*, ");
		sql.append("(COALESCE(l.QtyEntered,1)*COALESCE(l.PriceList,0)) as subtotal, ");
		sql.append("p.value as prodValue, ");
		sql.append("p.name as prodName ");
		sql.append(" FROM DH_PrePaymentLine l ");
		sql.append(" INNER JOIN M_Product p ON (l.M_Product_ID=p.M_Product_ID)");
		sql.append(" WHERE l.DH_PrePayment_ID=? ");
		sql.append("   AND l.isActive='Y' ");
		if (paid != null) {
			sql.append("   AND l.isPaid=? ");
		}

		try (PreparedStatement pstmt = DB.prepareStatement(sql.toString(), null);) {
			pstmt.setInt(1, prepaymentId);
			if (paid != null) {
				pstmt.setString(2, DB.TO_STRING(paid));
			}
			final ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				final PrePaymentLineBean bean = new PrePaymentLineBean();
				bean.setId(rs.getInt(COLUMNNAME_DH_PrePaymentLine_ID));
				bean.setPrepaymentId(rs.getInt(COLUMNNAME_DH_PrePayment_ID));
				bean.setProductId(rs.getInt(COLUMNNAME_M_Product_ID));
				bean.setProduct(rs.getString("ProdName"));
				bean.setValue(rs.getString("ProdValue"));
				bean.setPqtId(rs.getInt(COLUMNNAME_EXME_PaqBase_Version_ID));
				bean.setPriceList(rs.getBigDecimal(COLUMNNAME_PriceList));
				bean.setQty(rs.getInt(COLUMNNAME_QtyEntered));
				bean.setTax(rs.getBigDecimal(COLUMNNAME_TaxAmt));
				bean.setSubtotal(rs.getBigDecimal("Subtotal"));
				bean.setTotal(rs.getBigDecimal(COLUMNNAME_LineTotalAmt));
				list.add(bean);
			}

		} catch (final SQLException e) {
			sLog.log(Level.SEVERE, sql.toString(), e);
			throw e;
		}
		return list;
	}
	
	@Override
	public I_DH_PrePayment getDH_PrePayment() throws RuntimeException {
		if(header == null || header.get_ID() != super.getDH_PrePayment_ID()){
			header = (MDHPrePayment)super.getDH_PrePayment();
		}
		return header;
	}
	
	public MDHPrePayment getHeader() {
		return (MDHPrePayment)getDH_PrePayment();
	}
}
