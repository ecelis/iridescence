package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.model.X_EXME_ProductSActiva;
import org.compiere.model.X_I_EXME_ProductSActiva;
import org.compiere.util.CLogger;
import org.compiere.util.DB;

/**
 * Modelo de negocios para la tabla de productos - sustancias
 * activas.
 * <p>
 * Creado: 04/Abr/2005<p>
 * Modificado: $Date: 2005/05/31 16:46:33 $<p>
 * Por: $Author: hassan $<p>
 * 
 * @author mrojas
 * @version $Revision: 1.2 $
 */
public class MProductSActiva extends X_EXME_ProductSActiva {


    /** Static Logger               */
    private static CLogger      s_log = CLogger.getCLogger (MProductSActiva.class);

    
    /**
     * @param ctx El contexto de la aplicaci&oacute;n
     * @param EXME_ProductSActiva_ID El identificador del registro espec&iacute;fico
     * @param trxName El nombre de transacci&oacute;n
     */
    public MProductSActiva(Properties ctx, int EXME_ProductSActiva_ID, String trxName) {
        super(ctx, EXME_ProductSActiva_ID, trxName);
    }


    /**
	 * 	Load Constructor
	 *	@param ctx context
	 *	@param rs result set
	 */
	public MProductSActiva (Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
	}	//	MProductSActiva
    
    /**
     * Contstructor de importaci&oacute;n
     * @param psa El objeto con los datos de importaci&oacute;n
     * @param trxName El nombre de transacci&oacute;n
     */
    public MProductSActiva(X_I_EXME_ProductSActiva psa, String trxName) {
        this (psa.getCtx(), 0, trxName);
        
        setEXME_SActiva_ID(psa.getEXME_SActiva_ID());
        setM_Product_ID(psa.getM_Product_ID());
        setDescription(psa.getDescription());
    }
    
    
	/**
	 *  Devolvemos la lista de sustancias activas de un producto determinado
	 *
	 *@param  producto    El producto
	 *@return             Un valor List conteniendo las sustancias activas.
	 *@throws  Exception  en caso de ocurrir un error al procesar la consulta.
	 */
	public static List<Long> getSusActivas(Properties ctx, long producto) throws Exception {
		String sql = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		sql = "SELECT EXME_SActiva_ID FROM EXME_ProductSActiva WHERE M_Product_ID = ? AND IsActive = 'Y'";
		
		sql = MEXMELookupInfo.addAccessLevelSQL(ctx,sql.toString(),"EXME_ProductSActiva");

		pstmt = null;
		rs = null;
        
        List<Long> lista = new ArrayList<Long>();
		try {
			pstmt = DB.prepareStatement(sql, null);
			pstmt.setLong(1, producto);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				lista.add(new Long(rs.getInt(1)));
			}
			
		} catch (Exception e) {
    		s_log.log(Level.SEVERE, sql.toString(), e.getMessage());
    		try {
    			if (pstmt != null)
    				pstmt.close ();
    			if (rs != null)
    				rs.close ();
    		} catch (Exception ex) {
    			s_log.log(Level.SEVERE, ex.getMessage());
    		}
    		pstmt = null;
    		rs = null;
    	}finally {
    		try {
    			if (pstmt != null) {
    				pstmt.close();
    			}
    			if (rs != null) {
    				rs.close();
    			}
    			pstmt = null;
    			rs = null;
    		} catch (Exception e) {
    			s_log.log(Level.SEVERE, "Closing rs and pstmt", e.getMessage());
    			pstmt = null;
    			rs = null;
    		}
    	}        
        return lista;
	}

}
