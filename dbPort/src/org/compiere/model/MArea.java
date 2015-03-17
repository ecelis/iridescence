/*
 * Created on 25-may-2005
 *
 */
package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.apache.struts.util.LabelValueBean;
import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;

/**
 * <b>Fecha:</b> 25/Mayo/2005<p>
 * <b>Modificado: </b> $Author: hassan $<p>
 * <b>En :</b> $Date: 2005/06/01 00:07:31 $<p>
 *
 * @author hassan reyes
 * @version $Revision: 1.3 $
 */
public class MArea extends X_EXME_Area {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**	Static Logger				*/
	private static CLogger		s_log = CLogger.getCLogger (MArea.class);

    /**
     * @param ctx
     * @param EXME_Area_ID
     * @param trxName
     */
    public MArea(Properties ctx, int EXME_Area_ID, String trxName) {
        super(ctx, EXME_Area_ID, trxName);
    }

    /**
     * @param ctx
     * @param rs
     * @param trxName
     */
    public MArea(Properties ctx, ResultSet rs, String trxName) {
        super(ctx, rs, trxName);
    }

    /**
     * 
     * @param ctx
     * @param tipoArea
     * @param trxName
     * @return
     */
    public static MArea[] get(Properties ctx, String tipoArea, String trxName){
        
        ArrayList<MArea> list = new ArrayList<MArea>();
        StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append("SELECT * FROM EXME_Area WHERE isActive = 'Y' ")
		.append(" AND tipoArea = '").append(tipoArea).append("' ");
		
		sql= new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, sql.toString(), "EXME_Area"));
		
		sql.append(" ORDER BY Name");

		PreparedStatement pstmt = null;
        ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			rs = pstmt.executeQuery();
			while (rs.next()) {
			    MArea area = new MArea(ctx, rs, trxName);
				list.add(area);
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, "get", e);
		} finally {
           DB.close(rs, pstmt);
		}

		//
		MArea[] areas = new MArea[list.size()];
		list.toArray(areas);
		return areas;
        
    }
    
    //Metodo para llevar las areas
    //jochoa P Reporte de Nutrici�n

    public static ArrayList<MArea> getAreas(Properties ctx, String trxName, String tipoArea){
        
        ArrayList<MArea> list = new ArrayList<MArea>();
        StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append(" SELECT * FROM EXME_Area WHERE isActive = 'Y' ");
		if(tipoArea!=null)
			sql.append(" AND TIPOAREA = '").append(tipoArea).append("' ");
		sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, sql.toString(), "EXME_Area"));
		
		sql.append(" ORDER BY Name");

		PreparedStatement pstmt = null;
        ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			rs = pstmt.executeQuery();
			while (rs.next()) {
			    MArea area = new MArea(ctx, rs, trxName);
				list.add(area);
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, "get", e);
		} finally {
           DB.close(rs, pstmt);
		}

		return list;
        
    }
    
    /**
     * Obtiena la Lista de Areas
     * @param ctx
     * @return Label Value Bean
     */
    public static List<LabelValueBean> getAreasLabel(Properties ctx, String trx, String tipoArea){
    	List<LabelValueBean> temp = new ArrayList<LabelValueBean>();
    	List<MArea> lista = getAreas(ctx, trx, tipoArea);
		temp.add(new LabelValueBean("","0"));
    	for(int i = 0; i < lista.size(); i++){
    		MArea area = (MArea)lista.get(i);
			temp.add(new LabelValueBean(area.getName(),String.valueOf(area.getEXME_Area_ID())));
    	}
    	return temp;
    }
    
    /**
     * Obtenemos el area por cuenta paciente activa del paciente
     * @param ctx El contexto de la aplicacion
     * @param EXME_Paciente_ID El paciente
     * @param trxName el nombre de la transaccion 
     * @return La cama de un paciente 
     */
    public static MArea getByCtaPac(Properties ctx, int EXME_Paciente_ID,
			String trxName) {
		MArea clas = null;

		String sql = "Select * " +
				    " from exme_area area inner join exme_estserv serv inner join exme_ctapac cta on cta.exme_estserv_id = serv.exme_estserv_id " +
                             " on serv.exme_area_id = area.exme_area_id " +
                      " where cta.estatus = 'A' "+
                    	  " and exme_paciente_id = ? " ;
		       
        sql = MEXMELookupInfo.addAccessLevelSQL(ctx, sql, MArea.Table_ID, "area");
        
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
			pstmt = DB.prepareStatement(sql, trxName);
			pstmt.setInt(1, EXME_Paciente_ID);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				clas = new MArea(ctx, rs, trxName);
			}

		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql, e);
		} finally {
			DB.close(rs, pstmt);
		}
        
        return clas;
    }
    
    
    /**
     * Obtenemos las areas en una lista de tipo LabelValueBean
     * @param ctx
     * @param sql
     * @param params
     * @param trxName
     * @return
     */
    public static List<LabelValueBean> get(Properties ctx, String sql, 
    		List<Object> params, boolean blanco, String trxName) {
    	List<LabelValueBean> clas = new ArrayList<LabelValueBean>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
        	sql = MEXMELookupInfo.addAccessLevelSQL(ctx, sql, MArea.Table_ID);
			pstmt = DB.prepareStatement(sql, trxName);
			DB.setParameters(pstmt, params);
			rs = pstmt.executeQuery();

			if(blanco)
				clas.add(new LabelValueBean(" ", "0"));
			
			while (rs.next()) {
				clas.add(new LabelValueBean(rs.getString(1), String.valueOf(rs.getInt(2))));
			}

		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql, e);
		} finally {
			DB.close(rs, pstmt);
		}
        
        return clas;
    }
}
