/*
 * Created on 14-abr-2005
 *
 */
package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;

/**
 * @author hassan reyes
 *
 *	Descuentos por Familia de Productos.
 */
public class MInvoiceFam extends X_EXME_InvoiceFam {

    /**
     * @param ctx
     * @param EXME_InvoiceFam_ID
     * @param trxName
     */
    public MInvoiceFam(Properties ctx, int EXME_InvoiceFam_ID, String trxName) {
        super(ctx, EXME_InvoiceFam_ID, trxName);
    }

    /**
     * @param ctx
     * @param rs
     * @param trxName
     */
    public MInvoiceFam(Properties ctx, ResultSet rs, String trxName) {
        super(ctx, rs, trxName);
    }
    
    /**
     * Crea una copia a partir de los descuentos por familia de productos de una Extension.
     * @param eFam
     * @return
     */
    public static MInvoiceFam copyFrom(MCtaPacFam eFam, int C_Invoice_ID){
        
        MInvoiceFam iFam = new MInvoiceFam(eFam.getCtx(), 0, eFam.get_TrxName());
        
        iFam.setClientOrg(eFam);
        
        iFam.setEXME_ProductFam_ID(eFam.getEXME_ProductFam_ID());
        iFam.setC_Invoice_ID(C_Invoice_ID);
        iFam.setDiscountAmt(eFam.getDiscountAmt());
        iFam.setDiscountPorcent(eFam.getDiscountPorcent());
        iFam.setBaseAmt(eFam.getBaseAmt());
        
        return iFam;
        
    }

}
