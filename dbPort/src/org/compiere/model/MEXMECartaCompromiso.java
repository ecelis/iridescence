/*
 * Created on 31-ene-2006
 *
 */
package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;

/**
 * @author Omar Torres
 *
 */
public class MEXMECartaCompromiso extends X_EXME_CartaCompromiso 
{
	private static final long	serialVersionUID	= 1L;
        
    /**
     * @param ctx
     * @param EXME_Cama_ID
     * @param trxName
     */
    
    
    public MEXMECartaCompromiso(Properties ctx, int EXME_CartaCompromiso_ID, String trxName)
    {
        super (ctx, EXME_CartaCompromiso_ID, trxName);
    }

    /**
     * @param ctx
     * @param rs
     * @param trxName
     */
    public MEXMECartaCompromiso(Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }
     
  
}
