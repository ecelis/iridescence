package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.DB;


/**
 * Modelo para Auditoria de Pre-Reclasificacion
 *
 * <b>Fecha:</b> 08/Febrero/2006<p>
 * <b>Modificado: </b> $Author: gisela $<p>
 * <b>En :</b> $Date: 2006/05/18 21:47:31 $<p>
 *
 * @author Gisela Lee
 * @version $Revision: 1.1 $
 */
public class MEXMEPreReclasA extends X_EXME_Pre_Reclas_A {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** Static Logger               */
    private static CLogger      log = CLogger.getCLogger (MEXMEPreReclasA.class);
    
    /**
     * @param ctx
     * @param ID
     */
    public MEXMEPreReclasA(Properties ctx, int ID, String trxName) {
        super(ctx, ID, trxName);
    }

    /**
     * @param ctx
     * @param rs
     */
    public MEXMEPreReclasA(Properties ctx, ResultSet rs, String trxName) {
        super(ctx, rs, trxName);
    }
    
    /**
     * Constructor para generar auditoria a partir de una pre-reclasificacion
     * @param ctx
     * @param rs
     */
    public MEXMEPreReclasA(MEXMEPreReclas clas, String trxName) {
        this (clas.getCtx(), 0, trxName);
        setVersion(MEXMEPreReclasA.getSiguienteVersion(clas.getCtx(), clas.getEXME_Paciente_ID(), trxName));
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
        setIsLocked(clas.isLocked());
        setAD_User_ID(clas.getAD_User_ID());
        setAD_OrgTrx_ID(clas.getAD_OrgTrx_ID());
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
        String sql = "SELECT MAX(Version) AS Version FROM EXME_Pre_ReClas_A WHERE isActive = 'Y' AND EXME_Paciente_ID = ? ";
        
        sql = MEXMELookupInfo.addAccessLevelSQL(ctx, sql, X_EXME_Pre_Reclas_A.Table_Name);
        
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
			pstmt = DB.prepareStatement(sql, trxName);
			pstmt.setInt(1, EXME_Paciente_ID);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				version = rs.getInt("Version") + 1;
			}
			
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage(), e);
		} finally {
			DB.close(rs, pstmt);
		}

		return version;
	}    
}