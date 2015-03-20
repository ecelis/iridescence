/*
 * Created on 09-may-2005
 */
package org.compiere.model;

import java.sql.PreparedStatement;import java.sql.ResultSet;
import java.util.ArrayList;import java.util.Properties;import java.util.logging.Level;import org.compiere.util.CLogger;import org.compiere.util.Constantes;import org.compiere.util.DB;import org.compiere.util.cuestionarios.Pregunta_VO;import org.compiere.util.cuestionarios.RespuestaList_VO;

/**
 * Modelo de Pregunta
 * <b>Modificado: </b> $Author: gisela $<p>
 * <b>En :</b> $Date: 2005/05/10 16:45:03 $<p>
 *
 * @author Gisela Lee
 * @version $Revision: 1.1 $
 */
public class MPregunta_Lista extends X_EXME_Pregunta_Lista {
	private static CLogger		s_log = CLogger.getCLogger (MPregunta_Lista.class);
    /**
     * @param ctx
     * @param EXME_Pregunta_Lista_ID
     * @param trxName
     */
    public MPregunta_Lista(Properties ctx, int EXME_Pregunta_Lista_ID, String trxName) {
        super(ctx, EXME_Pregunta_Lista_ID, trxName);
    }

    /**
     * @param ctx
     * @param rs
     * @param trxName
     */
    public MPregunta_Lista(Properties ctx, ResultSet rs, String trxName) {
        super(ctx, rs, trxName);
    }
    public static ArrayList<RespuestaList_VO> getListaRespuestas (Integer pregunta) throws Exception{        //lista con las preguntas        ArrayList<RespuestaList_VO> lstPreguntas = new ArrayList<RespuestaList_VO>();        StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);	            sql.append("	select rl.exme_pregunta_id, rl.value, rl.name, rl.exme_pregunta_lista_id")              .append(" 	from exme_pregunta_lista rl ")        	  .append(" 	where exme_pregunta_id = ?");                     PreparedStatement pstmt = null;        ResultSet rs = null;        try {            pstmt = DB.prepareStatement(sql.toString(), null);            pstmt.setInt(1, pregunta.intValue());            rs = pstmt.executeQuery();            RespuestaList_VO preg = null;            while(rs.next()) {            	            	 preg = new RespuestaList_VO();            	             	             	 preg.setPreguntaId(new Integer(rs.getInt(1)));            	 preg.setValorTxt(rs.getString(2));            	 preg.setDescripcion(rs.getString(3));            	 preg.setListaID(new Integer(rs.getInt(4)));            	                lstPreguntas.add(preg);            }           		} catch (Exception e) {			s_log.log(Level.SEVERE, "MPregunta_Lista.getListaRespuestas", e);			e.printStackTrace();		}        finally        {        	rs.close();        	pstmt.close();   			rs = null;   			pstmt = null;        }        return lstPreguntas;    }    
}