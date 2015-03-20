package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;


/**
 * Modelo para Auditoria de Hoja Clasificacion
 *
 * <b>Fecha:</b> 03/Marzo/2006<p>
 * <b>Modificado: </b> $Author: gisela $<p>
 * <b>En :</b> $Date: 2006/05/18 21:45:52 $<p>
 *
 * @author Gisela Lee
 * @version $Revision: 1.2 $
 */
public class MEXMECartaAutorizaA extends X_EXME_CartaAutoriza_A {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** Static Logger               */
    private static CLogger      log = CLogger.getCLogger (MEXMECartaAutorizaA.class);
    
    /**
     * @param ctx
     * @param ID
     */
    public MEXMECartaAutorizaA(Properties ctx, int ID, String trxName) {
        super(ctx, ID, trxName);
    }

    /**
     * @param ctx
     * @param rs
     */
    public MEXMECartaAutorizaA(Properties ctx, ResultSet rs, String trxName) {
        super(ctx, rs, trxName);
    }
    
    /**
     * Constructor para generar auditoria a partir de una carta de autorizacion
     */
    public MEXMECartaAutorizaA(MEXMECartaAutoriza orig, String trxName) {
        this (orig.getCtx(), 0, trxName);
        setVersion(MEXMECartaAutorizaA.getSiguienteVersion(orig.getCtx(), orig.getEXME_Paciente_ID(), trxName));
        setEXME_Paciente_ID(orig.getEXME_Paciente_ID());
        setAD_User_ID(orig.getAD_User_ID());
        setDateOrdered(orig.getDateOrdered());
        setDatePrinted(orig.getDatePrinted());
        setDonadores(orig.getDonadores());
        setEXME_CartaAutoriza_ID(orig.getEXME_CartaAutoriza_ID());
        setIsActive(orig.isActive());
        setTestigo1(orig.getTestigo1());
        setTestigo2(orig.getTestigo2());
        setTipoCarta(orig.getTipoCarta());
    }
    
    /**
     * Obtenemos la siguiente version de auditoria para la carta de autorizacion
     * @param ctx El contexto de la aplicacion
     * @param EXME_Paciente_ID El paciente a obtener la version
     * @return La siguiente version de auditoria 
     */
    public static int getSiguienteVersion(Properties ctx, int EXME_Paciente_ID, 
            String trxName){
        int version = 1;
        String sql = "SELECT MAX(Version) AS Version FROM EXME_CartaAutoriza_A " +
                "WHERE AD_Client_ID = ? AND EXME_Paciente_ID = ?";
        		sql= MEXMELookupInfo.addAccessLevelSQL(ctx,sql,"EXME_CartaAutoriza_A");
   
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try{
            pstmt = DB.prepareStatement(sql, trxName);
            pstmt.setInt(1, Env.getAD_Client_ID(ctx));
            pstmt.setInt(2, EXME_Paciente_ID);
            rs = pstmt.executeQuery();

            if(rs.next()){
                version = rs.getInt("Version") + 1;
            }
        } catch (Exception e) {
			log.log(Level.SEVERE, sql, e);
		}finally {
			DB.close(rs, pstmt);
		}

        return version;
    }    
}