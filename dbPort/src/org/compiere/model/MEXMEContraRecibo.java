package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;

/**
 * @author Asael Sepulveda
 */
public class MEXMEContraRecibo extends X_EXME_ContraRecibo {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 3762779176181434636L;
	/** Static Logger               */
    private static CLogger      s_log = CLogger.getCLogger (MEXMEContraRecibo.class);

    public MEXMEContraRecibo(Properties ctx, int EXME_ContraRecibo_ID, String trxName) {
        super(ctx, EXME_ContraRecibo_ID, trxName);
    }

    public MEXMEContraRecibo(Properties ctx, ResultSet rs, String trxName) {
        super(ctx, rs, trxName);
    }

    public MEXMECtaPac getCtaPac() {
    	return new MEXMECtaPac(this.getCtx(), this.getEXME_CtaPac_ID(), null);
    }

    public MInvoice getInvoice() {
    	return new MInvoice(this.getCtx(), this.getC_Invoice_ID(), null);
    }

    public MEXMEMedico getMedico() {
    	return new MEXMEMedico(this.getCtx(), this.getEXME_Medico_ID(), null);
    }
    

    public MEXMEHabitacion getHabitacion() {
    	return new MEXMEHabitacion(this.getCtx(), this.getEXME_Habitacion_ID(), null);
    }
    
    /**
     * getAll
     * 
     * Devuelve todos los registros de EXME_ContraRecibo
     * 
     * @param ctx
     * @param trxName
     * @return
     */
    public static ArrayList<MEXMEContraRecibo> getAll(Properties ctx, String whereClause, String orderBy, String trxName) {
    	
        final ArrayList<MEXMEContraRecibo> retValue = new ArrayList<MEXMEContraRecibo>();

        final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
        
        sql.append("SELECT EXME_ContraRecibo.* FROM EXME_ContraRecibo ")
        .append(" LEFT JOIN C_Invoice i ON (i.C_Invoice_ID = EXME_ContraRecibo.C_Invoice_ID AND i.isActive = 'Y') ")
        .append(" LEFT JOIN EXME_CtaPac cp ON (cp.EXME_CtaPac_ID = EXME_ContraRecibo.EXME_CtaPac_ID AND cp.isActive = 'Y') ")
        .append(" LEFT JOIN EXME_Paciente p ON (cp.EXME_Paciente_ID = p.EXME_Paciente_ID AND p.isActive = 'Y') ")
        .append(" LEFT JOIN EXME_MEdico m ON (m.EXME_MEdico_ID = EXME_ContraRecibo.EXME_MEdico_ID AND m.isActive = 'Y') ")
        .append(" WHERE EXME_ContraRecibo.isActive = 'Y' ");
        
        sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx," ","EXME_ContraRecibo"));

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        if (whereClause != null) {
        	sql.append(whereClause);
        }
        
        if (orderBy != null) {
        	sql.append(" ORDER BY ").append(orderBy);
        }

        try {
            pstmt = DB.prepareStatement (sql.toString(), trxName);
            rs = pstmt.executeQuery ();
            
            while (rs.next ()) {
                retValue.add(new MEXMEContraRecibo(ctx, rs, trxName));
            }

        } catch (SQLException sqle) {
			s_log.log(Level.SEVERE, sql.toString(), sqle);
		}finally {
			DB.close(rs,pstmt);
		}
        return retValue;
    }
    
}
