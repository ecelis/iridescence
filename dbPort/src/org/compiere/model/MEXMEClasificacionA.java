package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;


/**
 * Modelo para Auditoria de Clasificacion
 *
 * <b>Fecha:</b> 08/Febrero/2006<p>
 * <b>Modificado: </b> $Author: otorres $<p>
 * <b>En :</b> $Date: 2006/09/13 23:06:21 $<p>
 *
 * @author Gisela Lee
 * @version $Revision: 1.8 $
 */
public class MEXMEClasificacionA extends X_EXME_Clasificacion_A {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** Static Logger               */
    private static CLogger      log = CLogger.getCLogger (MEXMEClasificacionA.class);
    
    /**
     * @param ctx
     * @param ID
     */
    public MEXMEClasificacionA(Properties ctx, int ID, String trxName) {
        super(ctx, ID, trxName);
    }

    /**
     * @param ctx
     * @param rs
     */
    public MEXMEClasificacionA(Properties ctx, ResultSet rs, String trxName) {
        super(ctx, rs, trxName);
    }
    
    /**
     * Constructor para generar auditoria a partir de una clasificacion
     * @param ctx
     * @param rs
     */
    public MEXMEClasificacionA(MEXMEClasificacion clas, String trxName) {
        this (clas.getCtx(), 0, trxName);
        setVersion(MEXMEClasificacionA.getSiguienteVersion(clas.getCtx(), clas.getEXME_Paciente_ID(), trxName));
        setEXME_Paciente_ID(clas.getEXME_Paciente_ID());
        setEstatus(clas.getEstatus());
        setDateOrdered(clas.getDateOrdered());
        setEXME_Procedencia_ID(clas.getEXME_Procedencia_ID());
        setPtsProcedencia(clas.getPtsProcedencia());
        setEXME_Ocupacion_Clas_ID(clas.getEXME_Ocupacion_Clas_ID());
        setPtsOcupacion(clas.getPtsOcupacion());
        setEXME_Zona_ID(clas.getEXME_Zona_ID());
        setPtsZona(clas.getPtsZona());
        setEXME_TipoVivienda_ID(clas.getEXME_TipoVivienda_ID());
        setPtsTipoVivienda(clas.getPtsTipoVivienda());
        setEXME_Tenencia_ID(clas.getEXME_Tenencia_ID());
        setPtsTenencia(clas.getPtsTenencia());
        setEXME_ServPublico_ID(clas.getEXME_ServPublico_ID());
        setPtsServPublico(clas.getPtsServPublico());
        setEXME_MatConst_ID(clas.getEXME_MatConst_ID());
        setPtsMatConst(clas.getPtsMatConst());
        setEXME_NumHabts_ID(clas.getEXME_NumHabts_ID());
        setPtsNumHabts(clas.getPtsNumHabts());
        setEXME_NumPers_ID(clas.getEXME_NumPers_ID());
        setPtsNumPers(clas.getPtsNumPers());
        setEXME_NumEnferm_ID(clas.getEXME_NumEnferm_ID());
        setPtsNumEnferm(clas.getPtsNumEnferm());
        setNumMiembros(clas.getNumMiembros());
        setObservaciones(clas.getObservaciones());
        setTotalPts(clas.getTotalPts());
        setEXME_ClasCliente_ID(clas.getEXME_ClasCliente_ID());
        setIngJefeFam(clas.getIngJefeFam());
        setIngHijos(clas.getIngHijos());
        setIngOtros(clas.getIngOtros());
        setTotalIng(clas.getTotalIng());
        setEgrAlimentacion(clas.getEgrAlimentacion());
        setEgrVivienda(clas.getEgrVivienda());
        setEgrServicios(clas.getEgrServicios());
        setEgrOtros(clas.getEgrOtros());
        setTotalEgr(clas.getTotalEgr());
        setPorcAlimentacion(clas.getPorcAlimentacion());
        setPtsIngresos(clas.getPtsIngresos());
        setPtsEgre(clas.getPtsEgre());
        setDateVaidTo(clas.getDateVaidTo());
        setIsLocked(Boolean.parseBoolean(clas.getIsLocked()));
        setAD_User_ID(clas.getAD_User_ID());
        setFecha_Ult_Act(clas.getFecha_Ult_Act());
    }
    
    /**
     * Obtenemos la siguiente version de auditoria para el paciente
     * @param ctx El contexto de la aplicacion
     * @param EXME_Paciente_ID El paciente a obtener la version
     * @return La siguiente version de auditoria 
     */
    public static int getSiguienteVersion(Properties ctx, int EXME_Paciente_ID, 
            String trxName){
        int version = 1;
        String sql = "SELECT MAX(Version) AS Version FROM EXME_Clasificacion_A " +
                "WHERE AD_Client_ID = ? AND EXME_Paciente_ID = ?";
        sql = MEXMELookupInfo.addAccessLevelSQL(ctx,sql,"EXME_Clasificacion_A");
        
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
			DB.close(rs,pstmt);
		}
        
        return version;
    }    
}