/*

 * Created on 30-abr-2005

 *

 */

package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.apache.struts.util.LabelValueBean;
import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;


/**
 * @author hassan reyes
 *
 */
public class MReligion extends X_EXME_Religion {

	private static final long serialVersionUID = 1L;
	/**	Static Logger				*/
	private static CLogger		s_log = CLogger.getCLogger (MReligion.class);
	
    /**
     * @param ctx
     * @param EXME_Religion_ID
     * @param trxName
     */
    public MReligion(Properties ctx, int EXME_Religion_ID, String trxName) {
        super(ctx, EXME_Religion_ID, trxName);
    }

    /**
     * @param ctx
     * @param rs
     * @param trxName
     */
    public MReligion(Properties ctx, ResultSet rs, String trxName) {
        super(ctx, rs, trxName);
    }
    
    /**
     * Obtenemos las religiones para una cliente + organizacion.
     * @param ctx
     * @param trxName
     * @param whereClause opcional
     * @param orderBy opcional
     * @return
     */
    public static MReligion[] get(Properties ctx, String trxName, String whereClause, String orderBy){
        
        final List<MReligion> list = new ArrayList<MReligion>();
        final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);

		sql.append("SELECT * FROM EXME_Religion WHERE isActive = 'Y' ");
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name));

		if (whereClause != null) {
			sql.append(whereClause);
		}
		if (orderBy != null && orderBy.length() > 0) {
			sql.append(" ORDER BY " + orderBy);
		}

		PreparedStatement pstmt = null;
		ResultSet rs  = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			rs = pstmt.executeQuery();
			while (rs.next()) {
			    MReligion rel = new MReligion(ctx, rs, trxName);
				list.add(rel);
			}
			
		} catch (Exception e) {
			s_log.log(Level.SEVERE, "get", e);
		} finally {
			DB.close(rs,pstmt);
		}

		//
		MReligion[] rels = new MReligion[list.size()];
		list.toArray(rels);
		return rels;
		
    }
    
    /**
     * Busca toda las religiones dispobibles en catalgodo de EXME_Religion
     * 
     * @param ctx
     * @param trxName
     * @param whereClause
     * @param orderBy
     * @return List de LabelValueBean<Name,ID>  con las religiones
     */
    public static List<LabelValueBean> getAll(Properties ctx, String trxName, String whereClause, String orderBy){
    	final List<LabelValueBean> lista = new ArrayList<LabelValueBean>();
    	final StringBuilder sql = new StringBuilder("SELECT NAME, EXME_Religion_ID FROM EXME_Religion WHERE isActive = 'Y' ");
    	sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name));

		if (whereClause != null)
			sql.append(" ").append(whereClause);

		if(orderBy != null && orderBy.length() > 0)
			sql.append(" ORDER BY ").append(orderBy);

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			pstmt = DB.prepareStatement(sql.toString(), trxName);
			rs = pstmt.executeQuery();
	
			while (rs.next()) {
				lista.add(new LabelValueBean(rs.getString("NAME"), rs.getString("EXME_Religion_ID")));
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, "get", e);
		} finally {			
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}
		return lista;
    }

    public static MReligion get(Properties ctx,String where){
    	MReligion religion =  new Query(ctx, Table_Name, where, null)//
		.setOnlyActiveRecords(true)
		.first();		
		return religion;
	}
}