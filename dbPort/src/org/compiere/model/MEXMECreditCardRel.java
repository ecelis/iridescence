/*

 * Created on 29-junio-2007

 *

 */
package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;

import org.compiere.util.Msg;

/**
 * <b>Fecha:</b> 29/junio/2007<p>
 * <b>Modificado: </b> $Author:  $<p>
 * <b>En :</b> $Date:  $<p>
 *
 * @author JOchoa
 * @version $Revision:  $
 */

public class MEXMECreditCardRel extends X_EXME_CreditCardRel {
	private static final long serialVersionUID = 1L;
    /**
     * @param ctx
     * @param EXME_CreditCardRel_ID
     * @param trxName
     */
    public MEXMECreditCardRel(Properties ctx, int EXME_CreditCardRel_ID, String trxName) {
        super(ctx, EXME_CreditCardRel_ID, trxName);
    }

    /**
     * @param ctx
     * @param rs
     * @param trxName
     */
    public MEXMECreditCardRel(Properties ctx, ResultSet rs, String trxName) {
        super(ctx, rs, trxName);
    }
    
    /**
     *  beforeSave
     *  @see org.compiere.model.PO#beforeSave(boolean)
     *  @param newRecord
     *  @return true
     */
    protected boolean beforeSave (boolean newRecord)
    {
        String A = getMontoComision();
        String B = getIVAComision();
        String C = getMontoOperacion();        

        String errMsg = "";
        if (((A == null )||(A.trim().length()== 0))&&((B == null )||(B.trim().length()== 0))&&((C == null )||(C.trim().length()== 0))){
        	errMsg = "Ingrese al menos un Metodo de pago";
        }        
        
        if (errMsg.length() > 0) {
            log.saveError("Error", Msg.parseTranslation(getCtx(), errMsg));
            return false;
        }
        
        return true;
    }   //  beforeSave
}