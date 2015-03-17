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

public class MEXMEPaqBaseBP extends X_EXME_PaqBase_BP{
    
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**	Static Logger				*/
	private static CLogger		s_log = CLogger.getCLogger (MEXMEPaqBaseBP.class);
	
	/**
     * @param ctx
     * @param EXME_Nacionalidad_ID
     * @param trxName
     */
	public MEXMEPaqBaseBP(Properties ctx, int EXME_PaqBase_BP_ID, String trxName) {
        super(ctx, EXME_PaqBase_BP_ID, trxName);
    }

    /**
     * @param ctx
     * @param rs
     * @param trxName
     */
    public MEXMEPaqBaseBP(Properties ctx, ResultSet rs, String trxName) {
        super(ctx, rs, trxName);
    }
    
    public static boolean borrarRegistros(Properties ctx, int EXME_PaqBase_Version_ID, String trxName) {
    	
    	boolean exito = true;
    	//Liz de la Garza - Cambio del proceso para utilizarlo dentro del modelo de persistencia (para log de cambios)
    	StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
    	sql.append("SELECT EXME_PaqBase_BP_ID FROM EXME_PaqBase_BP WHERE IsActive = 'Y' ")
		.append(" AND EXME_PaqBase_Version_ID = ?");

    	/*sql.append("DELETE EXME_PaqBase_BP WHERE IsActive = 'Y' ")
    		.append(" AND EXME_PaqBase_Version_ID = ")
    		.append(EXME_PaqBase_Version_ID);*/
    	
    	sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, sql.toString(), "EXME_PaqBase_BP"));
    	
    	PreparedStatement pstmt = null;
    	ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, EXME_PaqBase_Version_ID);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				MEXMEPaqBaseBP paqBaseBP = new MEXMEPaqBaseBP(ctx, rs, null);
				if (!paqBaseBP.delete(true, trxName)) {
					s_log.log(Level.SEVERE, "error.insulinas.registro.eliminar");
					exito = false;
					break;
				}
					
			}
			/*pstmt = DB.prepareStatement(sql.toString(), trxName);
			int noReg = pstmt.executeUpdate();
			s_log.fine("EXMEPaqBaseBP Num de Registros borrados = " + noReg);
			exito = true;*/

		} catch (Exception e) {
			exito = false;
			s_log.log(Level.SEVERE, sql.toString(), e.getMessage());
		} finally {
			DB.close(rs, pstmt);
		}
		return exito;
    }
    
    /**
     * 
     * @param ctx
     * @param EXME_PaqBaseVer_ID
     * @param trxName
     * @return
     */
	public static List<MEXMEPaqBaseBP> getLines(Properties ctx, int EXME_PaqBaseVer_ID, String trxName){
		
		List<MEXMEPaqBaseBP> lista = new ArrayList<MEXMEPaqBaseBP>();
		
		
		String sql = " SELECT * FROM EXME_PaqBase_BP pbd " +
					" WHERE pbd.EXME_PaqBase_Version_ID = ? ";
					
		sql = MEXMELookupInfo.addAccessLevelSQL(ctx, sql, "EXME_PaqBase_BP", "pbd");
		
		sql += " AND pbd.IsActive = 'Y' ORDER BY pbd.EXME_PaqBase_BP_ID ";

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql, trxName);
			pstmt.setInt(1, EXME_PaqBaseVer_ID);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				MEXMEPaqBaseBP paq = new MEXMEPaqBaseBP(ctx, rs, trxName);
				lista.add(paq);
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();//FIXME
		} finally {
			DB.close(rs, pstmt);
			pstmt = null;
		}

		return lista;
	}
	
	
	private X_C_BPartner socio = null;
	public X_C_BPartner getSocio() {
		if( socio == null)
			socio = new X_C_BPartner(getCtx(), getC_BPartner_ID(), null);
		return socio;
	}
}
