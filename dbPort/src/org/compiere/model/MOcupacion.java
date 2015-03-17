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

 * 

 */

public class MOcupacion extends X_EXME_Ocupacion {



    /**	Static Logger				*/

	private static CLogger		log = CLogger.getCLogger (MOcupacion.class);

	

    /**

     * @param ctx

     * @param EXME_Ocupacion_ID

     * @param trxName

     */

    public MOcupacion(Properties ctx, int EXME_Ocupacion_ID, String trxName) {

        super(ctx, EXME_Ocupacion_ID, trxName);

    }



    /**

     * @param ctx

     * @param rs

     * @param trxName

     */

    public MOcupacion(Properties ctx, ResultSet rs, String trxName) {

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

    public static MOcupacion[] get(Properties ctx, String trxName, String whereClause, String orderBy){
        ArrayList<MOcupacion> list = new ArrayList<MOcupacion>();
        StringBuilder sql = new StringBuilder("SELECT * FROM EXME_Ocupacion WHERE isActive = 'Y' ");
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
			boolean primero = false;			
			while (rs.next()) {
			    MOcupacion ocup = new MOcupacion(ctx, rs, trxName);
			    if(ocup.isDefault() && !primero){
			    	list.add(0, ocup);
			    	primero = true;
			    }else
			    	list.add(ocup);
			}
		

		} catch (Exception e) {
			log.log(Level.SEVERE, "get", e);
		} finally {			
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}
		MOcupacion[] ocupas = new MOcupacion[list.size()];
		list.toArray(ocupas);
		return ocupas;
    }

    /**
     * Busca toda las ocupaciones dispobibles
     * 
     * @param ctx
     * @param trxName
     * @param whereClause
     * @param orderBy
     * @return List de LabelValueBean<Name,ID>  con las ocupaciones
     */
    public static List<LabelValueBean> getAll(Properties ctx, String trxName, String whereClause, String orderBy){
    	final List<LabelValueBean> lista = new ArrayList<LabelValueBean>();
    	final StringBuilder sql = new StringBuilder("SELECT NAME, EXME_OCUPACION_ID FROM EXME_Ocupacion WHERE isActive = 'Y' ");
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
				lista.add(new LabelValueBean(rs.getString("NAME"), rs.getString("EXME_OCUPACION_ID")));
			}
		} catch (Exception e) {
			log.log(Level.SEVERE, "get", e);
		} finally {			
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}
		return lista;
    }


}

