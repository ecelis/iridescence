package org.compiere.model;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.adempiere.exceptions.MedsysException;
import org.compiere.apps.form.BeanPaySelect;
import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.Env;

/**
 * Clase proceso para la relación de una Nota de Crédito con "N" Facturas 
 * @author cdelangel
 */
public class MEXMEInvoiceRel extends X_EXME_InvoiceRel {

	private static final long serialVersionUID = 3781543456480643522L;
	/** Logger */
	private static CLogger sLog = CLogger.getCLogger(MEXMEInvoiceRel.class);

	// c_invoice_id = facturas
	// ref_invoice_id = notas de crédito

	/**
	 * @param ctx
	 * @param EXME_InvoiceFam_ID
	 * @param trxName
	 */
	public MEXMEInvoiceRel(final Properties ctx, final int EXME_InvoiceRel_ID,
			final String trxName) {
		super(ctx, EXME_InvoiceRel_ID, trxName);
	}

	/**
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MEXMEInvoiceRel(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);

	}

	// API Facturas
	// APC NC

	/**
	 * Listar las notas de crédito que no esten asignadas a facturas
	 * @param ctx
	 * @param partnerId
	 * @param trxName
	 * @return
	 */
	public static List<MInvoice> getSearchCreditNote(final Properties ctx,
			final int partnerId, final String trxName) {
		List<MInvoice> list = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		final List<Object> params = new ArrayList<Object>();

		StringBuffer sql = new StringBuffer(Constantes.INIT_CAPACITY_ARRAY)
		.append(" WITH NOTASUSADAS as ( ")
		// id de la nota que estan asignadas a una factura por cancelación o pago parcial
		.append(" (	SELECT DISTINCT ir.Ref_Invoice_ID AS idInv ")
		.append(" 	FROM EXME_InvoiceRel ir ")
		.append(" 	INNER JOIN C_Invoice inv ON inv.C_Invoice_ID = ir.Ref_Invoice_ID  ")
		.append(" 		AND inv.isActive = 'Y' ")
		.append(" 		AND inv.AD_Org_ID = ? ")// #1
		.append("   	AND inv.C_BPartner_ID = ? ")// #2
		.append(" 	WHERE ir.isActive='Y' ")
		.append(" 	AND ir.AD_Org_ID = ? ")// #3
		.append(" )UNION ");
		params.add(Env.getAD_Org_ID(ctx));
		params.add(partnerId);
		params.add(Env.getAD_Org_ID(ctx));;
		
		// id de la nota que pueden estar canceladas
		sql.append(" ( SELECT DISTINCT ir.C_Invoice_ID AS idInv ")
		.append(" 	FROM EXME_InvoiceRel ir ")
		.append(" 	INNER JOIN C_Invoice inv ON inv.C_Invoice_ID = ir.C_Invoice_ID ")
		.append(" 		AND inv.isActive = 'Y' ")
		.append(" 		AND inv.AD_Org_ID = ? ")// #4
		.append("   	AND inv.C_BPartner_ID = ? ")// #5
		.append(" 	WHERE ir.isActive='Y' ")
		.append(" 	AND ir.AD_Org_ID = ? ")// #6
		.append(" )) ");
		params.add(Env.getAD_Org_ID(ctx));
		params.add(partnerId);
		params.add(Env.getAD_Org_ID(ctx));
		
		sql.append("SELECT c.iso_code, i.* ");
		sql.append("FROM C_Invoice i "); // notas CXP
		sql.append("INNER JOIN C_DocType       dt ON (i.C_DocType_ID  = dt.C_DocType_ID");
		sql.append("				AND dt.docbasetype IN ('APC') ");
		sql.append("				AND dt.AD_Org_ID  = i.AD_Org_ID) ");
		sql.append("INNER JOIN C_Currency c 	   ON i.C_Currency_ID  = c.C_Currency_ID ");
		sql.append("WHERE i.docstatus 			   IN ('CO','CL') ");
		sql.append("AND i.C_BPartner_ID = ? ");// #7
		params.add(partnerId); // TODO ??
		sql.append("AND i.IsPaid='N' ");
		sql.append("AND i.isActive='Y' ");
		sql.append("AND i.AD_Org_ID = ? ");// #8
		params.add(Env.getAD_Org_ID(ctx));
		
		sql.append(" AND i.C_Invoice_ID NOT IN (SELECT idInv FROM NOTASUSADAS) ");
		sql.append(" AND i.Trxtype In ('A') ");
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			DB.setParameters(pstmt, params);
			list = new ArrayList<MInvoice>();
			rs = pstmt.executeQuery();

			while (rs.next()) {
				final MInvoice invoice = new MInvoice(ctx, rs, trxName);
				invoice.setISOCode(rs.getString("iso_code"));
				list.add(invoice);
			}
		} catch (Exception e) {
			sLog.log(Level.SEVERE, "getCreditNote" + sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
		}
		return list;
	}

	/**
	 * Nota de crédito relacionada a factura
	 * 
	 * @param ctx
	 * @param trxName
	 * @return
	 */
	public static List<MInvoice> getNotaRelFact(final Properties ctx,
			final int partnerId, final String trxName) {
		List<MInvoice> list = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		final List<Object> params = new ArrayList<Object>();

		StringBuffer sql = new StringBuffer(Constantes.INIT_CAPACITY_ARRAY);
		sql.append("SELECT c.iso_code, i.*, fa.C_Invoice_ID AS Factura ");
		sql.append("FROM C_Invoice i "); // notasrel CXP
		sql.append("INNER JOIN C_DocType       dt ON (i.C_DocType_ID  = dt.C_DocType_ID ");
		sql.append("				AND dt.docbasetype IN ('APC') ");
		sql.append("				AND dt.AD_Org_ID  = i.AD_Org_ID) ");
		sql.append("INNER JOIN C_Currency c 	   ON i.C_Currency_ID  = c.C_Currency_ID ");
		sql.append("LEFT  JOIN exme_invoicerel ir ON i.C_Invoice_id   = ir.ref_invoice_id ");
		sql.append("LEFT  JOIN c_invoice       fa ON ir.C_Invoice_id  = fa.c_invoice_id ");
		sql.append("LEFT  JOIN C_Currency      cf ON fa.C_Currency_ID = cf.C_Currency_ID ");
		sql.append("WHERE i.docstatus 			   IN ('CO','CL') ");
		sql.append("AND i.C_BPartner_ID = ? ");
		params.add(partnerId); // TODO ??
		sql.append("AND i.IsPaid='N' ");
		sql.append("AND i.isActive='Y' ");
		sql.append("AND i.AD_Org_ID = ? ");
		params.add(Env.getAD_Org_ID(Env.getCtx()));
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			DB.setParameters(pstmt, params);
			list = new ArrayList<MInvoice>();
			rs = pstmt.executeQuery();

			while (rs.next()) {
				final MInvoice invoice = new MInvoice(ctx, rs, trxName);
				invoice.setISOCode(rs.getString("iso_code"));
				list.add(invoice);
			}
		} catch (Exception e) {
			sLog.log(Level.SEVERE, "getCreditNote", e);
		} finally {
			DB.close(rs, pstmt);
		}
		return list;
	}

	/**
	 * conversiones de montos
	 * 
	 */
//	public void conversionAmount(final MInvoice nota, final int id,final
//			String trxName){
//		MInvoice fact = new MInvoice(getCtx(), 0, null);
//		final MInvoiceRel obj = new MInvoiceRel(nota.getCtx(), 0, trxName);
//
//		final MAcctSchema acctSchema = MAcctSchema.getClientAcctSchema(nota.getCtx(),Env.getAD_Client_ID(nota.getCtx()))[0];
//		
//		if(fact.getCurrencyISO() == nota.getCurrencyISO()){	
//			
//		}else{				
//			if(fact.getC_Currency_ID() == acctSchema.getC_Currency_ID()){
//				
//			}else {
//				MConversionRate.getConversionRate(fact.getC_Currency_ID(), acctSchema.getC_Currency_ID());
//			}
//			
//			if(nota.getC_Currency_ID() == acctSchema.getC_Currency_ID()){
//				
//			}else {
//				MConversionRate.getConversionRate(nota.getC_Currency_ID(), acctSchema.getC_Currency_ID());
//			}
//		}
//		if(nota.getC_Currency_ID()!=acctSchema.getC_Currency_ID()){
//			MConversionRate.getConversionRate(nota.getC_Currency_ID(),
//					acctSchema.getC_Currency_ID());
//		}
//	}
	
	/**
	 * Generar la relación de la nota de credito con la(s) facturas que cubre
	 * @param nota
	 * @param list
	 * @param trxName
	 * @return
	 */
	public static int save(final MInvoice nota, List<?> list, final BigDecimal rate, final String trxName) {
		int count = 0;
		BigDecimal totalAmount = Env.ZERO;
		for (final Object bean : list) {
			if (bean instanceof BeanPaySelect) {
				final BeanPaySelect beanpay = (BeanPaySelect)bean;
				if (beanpay.getImp() == null || beanpay.getImp().compareTo(BigDecimal.ZERO) <= 0) {
					continue;
				}else {
					count++;
					
					final MInvoice factura = new MInvoice(nota.getCtx(), beanpay.getInvoiceId(), trxName);
					final MEXMEInvoiceRel allocation = new MEXMEInvoiceRel(nota.getCtx(), 0, trxName);
					
					allocation.setC_Invoice_ID(factura.getC_Invoice_ID());
					allocation.setRef_Invoice_ID(nota.getC_Invoice_ID());
					//			BeanPaySelect beanpay = new BeanPaySelect();
					allocation.setAmount(beanpay.getImp()); // De la nota a la factura --- MontoDeLaNotaHaciaLaFactura() (En pesos siempre)
					// // Tipo de cambio De la nota a la factura
					allocation.setC_Conversion_Rate_ID(factura.getC_Currency_ID());
					// Tipo de cambio de la factura a la nota
					allocation.setRef_Conversion_Rate_ID(nota.getC_Currency_ID());
					// Tipo / o el proceso para saber si es una cancelacion o una asignacion
					allocation.setTrxType(X_EXME_InvoiceRel.TRXTYPE_Allocation);// Allocation

					if (allocation.save(trxName)) {
						// Con la nota se pago la factura totalmente ????
//						BigDecimal pagos =  fact.calculaPago(trxName);
//						pagos = pagos.add(fact.getCredits(false, trxName)).add(beanpay.getImp()); // solucion temporal
						//          saldo = monto_pendiente_de_pago_de_la_Factura - monto_asignado_de_la_nota
						
						// Poner el rate a la relacion/asignacion de la nota con su(s) facturas
						String sql = " UPDATE Exme_InvoiceRel rateRel "
								+ " SET rate = " +rate
								+ "  WHERE Exme_InvoiceRel_ID = " + allocation.getEXME_InvoiceRel_ID();
						
						int num = DB.executeUpdate(sql, trxName); 
						factura.setIsPaid(factura.getInvoiceOpenAmount().compareTo(Env.ZERO) == 0);
												
//						BigDecimal  saldo = null;
//						
//						if(factura.getC_Currency_ID() != nota.getC_Currency_ID()){
//							final MClientInfo clientInfo = MClientInfo.get(nota.getCtx(), Env.getAD_Client_ID(nota.getCtx()), null);
//							final MAcctSchema schema = MAcctSchema.get(nota.getCtx(), clientInfo.getC_AcctSchema1_ID());
//							
//							BigDecimal rate = MConversionRate.getRate(nota.getCtx(),schema.getC_Currency_ID(),factura.getC_Currency_ID());
//							saldo = rate.multiply(factura.getInvoiceOpenAmt());
//						}else{
//							saldo = factura.getInvoiceOpenAmt();
//						}
						
//						saldo = fact.getGrandTotal().subtract(saldo);
						//TODO: Pendiente restar la nota que se procesando, se agrego solucion temporal 
//						factura.setIsPaid(saldo.compareTo(Env.ZERO) <= 0);
						if (!factura.save(trxName)) {
							throw new MedsysException();
						}
						
						totalAmount = totalAmount.add(beanpay.getImp());// en moneda de la nota
						if(totalAmount.compareTo(nota.getConvertAmt()) > 0 ){
							break;
						}
					} else {
						throw new MedsysException();
					}
				}
			} //delmismotipo
		}// finfor
		if(totalAmount.compareTo(nota.getConvertAmt()) != 0 && count > 0){
			count = -1;
		}
		return count;
	}

	/**
	 * Regresa los registros de notas de credito
	 * @param cInvoice
	 * @param trxName
	 * @return
	 */
	public static List<MEXMEInvoiceRel> getRefLst(int cInvoice, final String trxName) {
		return new Query(Env.getCtx(), Table_Name, " C_Invoice_ID=?", trxName)//
			.setParameters(cInvoice)//
			.setOnlyActiveRecords(true)//
			.addAccessLevelSQL(true)//
			.list();
	}
	
	
	/**
	 * Regresa las relaciones de una factura que no sean por cancelacion
	 * @param ctx
	 * @param cInvoice
	 * @param trxName
	 * @return
	 */
	public static List<MEXMEInvoiceRel> getRefAllocation(Properties ctx, int cInvoiceId, final String trxName) {
		final StringBuilder sql = new StringBuilder();
		sql.append(" C_Invoice_ID = ? ");
		sql.append(" AND Ref_Invoice_ID NOT IN ( ");
		sql.append(" SELECT C_Invoice_ID FROM EXME_InvoiceRel WHERE TrxType=? ");
		sql.append(" )");

		return new Query(ctx, Table_Name, sql.toString(), trxName)//
			.setParameters(cInvoiceId, TRXTYPE_Cancellation)//
			.setOnlyActiveRecords(true)//
			.addAccessLevelSQL(true)//
			.list();
	}
	
	
	/**
	 * Regresa las facturas relacionadas a una nota de credito
	 * @param ctx
	 * @param creditNoteId
	 * @param trxName
	 * @return
	 */
	public static List<MEXMEInvoiceRel> getRefInvoices(Properties ctx, int creditNoteId, final String trxName) {
		final StringBuilder sql = new StringBuilder();
		sql.append(" Ref_Invoice_ID = ? ");
		return new Query(ctx, Table_Name, sql.toString(), trxName)//
			.setParameters(creditNoteId)//
			.setOnlyActiveRecords(true)//
			.addAccessLevelSQL(true)//
			.list();
	}
	
	/**
	 * Valida que la nota de debito/credit haya sido generada por cancelacion
	 * @param ctx
	 * @param creditMemoId
	 * @param trxName
	 * @return
	 */
	public static boolean isCancellation(Properties ctx, int creditInvId, final String trxName) {
		final StringBuilder sql = new StringBuilder();
		sql.append(" Ref_Invoice_ID =? ");
		sql.append(" AND TrxType=? ");

		return new Query(ctx, Table_Name, sql.toString(), trxName)//
			.setParameters(creditInvId, TRXTYPE_Cancellation)//
			.setOnlyActiveRecords(true)//
			.addAccessLevelSQL(true)//
			.firstId() > 0;
	}
}
