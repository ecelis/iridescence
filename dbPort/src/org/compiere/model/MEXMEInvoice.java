package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;

/**
 * Clase que extiende de MInvoice.
 * <p>
 * Basado en version: MInvoice.java,v 1.77 2005/02/14 02:23:08 jjanke Exp $
 * <p>
 * 
 * <b>Modificado: </b> $Author: taniap $
 * <p>
 * <b>En :</b> $Date: 2006/10/08 01:31:21 $
 * <p>
 * 
 * @author Hassan Reyes
 * @version $Revision: 1.39 $
 * @deprecated
 */
public class MEXMEInvoice extends MInvoice {

	/** serialVersionUID */
	private static final long serialVersionUID = 7610023548382192822L;
	/** Static Logger */
	private static CLogger sLog = CLogger.getCLogger(MEXMEInvoice.class);
	
	/**
	 * @param ctx
	 * @param rs
	 */
	public MEXMEInvoice(final Properties ctx, final ResultSet rset,
			final String trxName) {
		super(ctx, rset, trxName);
	}

	/**
	 * Lineas de impuestos de la factura de la aseguradora
	 * @param ctx: Contexto
	 * @param mInvLine: Linea de la factura que corresponde a CCCmD
	 * @param trxName: Nombre de transacción
	 * @return Listado de impuesto de la factura
	 */
	public static List<MInvoiceTax> getLinesInsuranceTaxes(final Properties ctx,
			final MInvoiceLine mInvLine, final String trxName) {
		final List<MInvoiceTax> lstInvTax = new ArrayList<MInvoiceTax>();
		final MConfigPre configPre = MConfigPre.get(ctx, trxName);
		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		
		if(mInvLine.getEXME_CtaPacDet_ID()>0){
			sql.append(" SELECT det.c_tax_id                        ")
			.append("           , SUM(coalesce(det.taxamt,0))     as imp  ")
			.append("           , SUM(coalesce(det.linenetamt,0)) as base ")
			.append("    FROM EXME_CtaPacDet det                    ")
			.append("    WHERE det.IsActive = 'Y'                   ")
			.append("    AND   det.EXME_CtaPacExt_ID IN (           ")
			.append(" 				SELECT cpdRel.EXME_CtaPacExt_ID    ") // 06222 — Se cambia alias de la Tabla
			.append(" 				FROM  EXME_CtaPacDet cpd        ")
			.append(" 				INNER JOIN EXME_CtaPacDet cpdRel ON cpdRel.EXME_CtaPacDet_ID = cpd.Ref_CtaPacDet_ID ")
			.append(" 				WHERE cpd.IsActive = 'Y'        ")
			.append(" 				AND   cpd.EXME_CtaPacDet_ID = ? ") //
			.append(" 			)                                   ")
			.append("    AND   det.M_Product_ID NOT IN (      ")
			.append(configPre.getCoaseguro_ID()).append(",    ")
			.append(configPre.getDeducible_ID()).append(",    ")
			.append(configPre.getCoaseguroMed_ID()).append(", ")
			.append(configPre.getCoPago_ID()).append(" )      ")
			.append("    GROUP BY det.c_tax_id                ");
			
		} else {
			sql.append(" SELECT il.c_tax_id                   ")
			.append("          , SUM(coalesce(il.taxamt,0)) as imp      ")
			.append("          , SUM(coalesce(il.linenetamt,0)) as base ")
			.append(" FROM  C_InvoiceLine il                  ")
			.append(" WHERE il.IsActive = 'Y'                 ")
			.append(" AND   il.C_Invoice_ID IN (              ")
			.append(" 				SELECT inl.C_Invoice_ID   ")
			.append(" 				FROM  C_InvoiceLine inl   ")
			.append(" 				WHERE inl.IsActive = 'Y'  ")
			.append(" 				AND   inl.C_InvoiceLine_ID = ? ") //
			.append(" 			)                             ")   
			.append(" AND   il.M_Product_ID NOT IN (          ")
			.append(configPre.getCoaseguro_ID()).append(",    ")
			.append(configPre.getDeducible_ID()).append(",    ")
			.append(configPre.getCoaseguroMed_ID()).append(", ")
			.append(configPre.getCoPago_ID()).append(") ")
			.append(" GROUP BY il.c_tax_id ");
		
		
		}
		
		ResultSet rset = null;
		PreparedStatement pstmt = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setInt(1, mInvLine.getEXME_CtaPacDet_ID()>0?mInvLine.getEXME_CtaPacDet_ID():mInvLine.getRef_InvoiceLine_ID());

			rset = pstmt.executeQuery();
			while (rset.next()) {
				final MInvoiceTax mInvoiceTax = new MInvoiceTax(ctx, 0, trxName);
				mInvoiceTax.setTaxAmt(rset.getBigDecimal("imp"));
				mInvoiceTax.setTaxBaseAmt(rset.getBigDecimal("base"));
				mInvoiceTax.setC_Tax_ID(rset.getInt("c_tax_id"));
				lstInvTax.add(mInvoiceTax);
			}
		} catch (Exception e) {
			sLog.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rset, pstmt);
		}
		return lstInvTax;
	}
}