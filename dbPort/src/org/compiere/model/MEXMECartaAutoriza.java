package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;

import org.compiere.util.CLogger;


/**
 * Modelo para Carta de Autorizacion
 *
 * <b>Fecha:</b> 03/Marzo/2006<p>
 * <b>Modificado: </b> $Author: gisela $<p>
 * <b>En :</b> $Date: 2006/04/18 14:38:02 $<p>
 *
 * @author Gisela Lee
 * @version $Revision: 1.1 $
 */
public class MEXMECartaAutoriza extends X_EXME_CartaAutoriza {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** Static Logger               */
    @SuppressWarnings("unused")
	private static CLogger      log = CLogger.getCLogger (MEXMECartaAutoriza.class);
    
    /**
     * @param ctx
     * @param ID
     */
    public MEXMECartaAutoriza(Properties ctx, int ID, String trx) {
        super(ctx, ID, trx);
    }

    /**
     * @param ctx
     * @param rs
     */
    public MEXMECartaAutoriza(Properties ctx, ResultSet rs, String trx) {
        super(ctx, rs, trx);
    }
    
    /**
     *  After Save
     *  @param newRecord new
     *  @param success success
     *  @return success
     */
    protected boolean afterSave (boolean newRecord, boolean success)
    {
        if (!success)
            return success;
     
        MEXMECartaAutorizaA aud = new MEXMECartaAutorizaA(this, get_TrxName());
        if (!aud.save(get_TrxName()))
            throw new IllegalStateException("Error al generar la auditoria");
        
        return true;
    }
}