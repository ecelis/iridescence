package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.apache.struts.util.LabelValueBean;
import org.compiere.util.CLogger;
import org.compiere.util.DB;

/**
 * Modelo de Tipo de Pregunta.
 * 
 * @author mrojas
 * @version $Revision: 1.2 $
 */
public class MTipoPregunta extends X_EXME_TipoPregunta {
    
    /**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	/**	Static Logger				*/
	private static CLogger		s_log = CLogger.getCLogger (MCuestionario.class);

	/**
	 * Creamos a partir de un id.
	 * @param ctx
	 * @param EXME_TipoPregunta_ID
	 * @param trxName
	 */
    public MTipoPregunta(Properties ctx, int EXME_TipoPregunta_ID, String trxName) {
        super(ctx, EXME_TipoPregunta_ID, trxName);
    }
    
    /**
     * Creamos a partir de un ResultSet.
     * @param ctx
     * @param rs
     * @param trxName
     */
    public MTipoPregunta(Properties ctx, ResultSet rs, String trxName) {
        super(ctx, rs, trxName);
    }
    
    /**
     *  Obtenemos una lista con los tipos de pregunta
     *  @return La lista con los tipos de preguntas
     */
    public static List<LabelValueBean> get(Properties ctx) throws Exception{
        //lista con los tipos de preguntas
        List<LabelValueBean> lstTipoPreg = new ArrayList<LabelValueBean>();

        //buscamos las preguntas
        String sql = "SELECT EXME_TipoPregunta.EXME_TipoPregunta_ID, EXME_TipoPregunta.Name "
              + "FROM EXME_TipoPregunta "
              + "WHERE EXME_TipoPregunta.IsActive = 'Y' ";
        
        sql = MEXMELookupInfo.addAccessLevelSQL(ctx, sql.toString(), "EXME_TipoPregunta");
        
        sql += "ORDER BY EXME_TipoPregunta.Name";

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = DB.prepareStatement(sql, null);
            //pstmt.setInt(1, Env.getAD_Client_ID(ctx));
            //pstmt.setInt(2, Env.getAD_Org_ID(ctx));
            rs = pstmt.executeQuery();

            while(rs.next()) {
                LabelValueBean tipoPreg = 
                    new LabelValueBean(rs.getString("Name"),
                            String.valueOf(rs.getLong("EXME_TipoPregunta_ID"))
                    );
                lstTipoPreg.add(tipoPreg);
            }
		
		}
		catch (Exception e) {
			s_log.log(Level.SEVERE, "getTipoPregunta", e);
		}
		finally {
			DB.close(rs, pstmt);
			pstmt = null;
			rs = null;
		}

        return lstTipoPreg;
    }

    public static ArrayList<LabelValueBean> getTiposPreguntas(Properties ctx) throws Exception{
        //lista con los tipos de preguntas
        ArrayList<LabelValueBean> lstTipoPreg = new ArrayList<LabelValueBean>();

        //buscamos las preguntas
        String sql = "SELECT EXME_TipoPregunta.EXME_TipoPregunta_ID, EXME_TipoPregunta.Name "
              + "FROM EXME_TipoPregunta "
              + "WHERE EXME_TipoPregunta.IsActive = 'Y' ";
        
        sql = MEXMELookupInfo.addAccessLevelSQL(ctx, sql.toString(), "EXME_TipoPregunta");
        
        sql += "ORDER BY EXME_TipoPregunta.Name";

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = DB.prepareStatement(sql, null);
            rs = pstmt.executeQuery();
            LabelValueBean tipoPreg = null;
            while(rs.next()) {
            	tipoPreg = new LabelValueBean(rs.getString("Name"), rs.getString("EXME_TipoPregunta_ID"));
                lstTipoPreg.add(tipoPreg);
            }
		
        } catch (Exception e){
            s_log.log(Level.SEVERE, "getTipoPregunta", e);
        }finally{
        	DB.close(rs, pstmt);
            pstmt = null;
            rs = null;
        }

        return lstTipoPreg;
    }
    
}