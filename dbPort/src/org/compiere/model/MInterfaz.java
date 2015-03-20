/*
 * Created on 6/09/2005
 *
 */
package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;

/**
 * @author GUEST_MTY
 *
 */
public class MInterfaz extends X_EXME_Interfaz {
    
    public MInterfaz(Properties ctx, int EXME_Interfaz_ID, String trxName){
        super(ctx, EXME_Interfaz_ID, trxName);
    }
    
    public MInterfaz(Properties ctx, ResultSet rs, String trxName){
        super(ctx, rs, trxName);
    }
    
}
