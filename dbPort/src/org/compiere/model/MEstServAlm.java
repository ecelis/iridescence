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
import org.compiere.util.Env;

public class MEstServAlm extends X_EXME_EstServAlm {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    /** Static logger           */
    private static CLogger s_log = CLogger.getCLogger(MEstServAlm.class);
    
    
	public MEstServAlm(Properties ctx, int EXME_EstServAlm_ID, String trxName) {
		super(ctx, EXME_EstServAlm_ID, trxName);
	}

	/**
     * @param ctx
     * @param rs
     * @param trxName
     */
    public MEstServAlm(Properties ctx, ResultSet rs, String trxName) {
        super(ctx, rs, trxName);
    }
    
    /**
     * Obtenemos la estacion de servicio dependiendo del almacen en cuestion
     * @return
     */
    public static MEXMEEstServ getEstServ(Properties ctx, int M_Warehouse_ID, String trxName){
        
    	//Noelia: Cambio sql de String a StringBuilder
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY); 
    	
    	MEXMEEstServ estServ = null;
		//Noelia: Cambio del sql para utilizar rs
        sql.append( " SELECT e.* FROM EXME_EstServAlm ")
           .append( " INNER JOIN EXME_EstServ e on (EXME_EstServAlm.EXME_EstServ_ID = e.EXME_EstServ_ID)")
           .append(" WHERE EXME_EstServAlm.IsActive = 'Y' ")
           .append( " AND e.IsActive = 'Y' " )
           .append( " AND EXME_EstServAlm.M_Warehouse_ID = ? ");
		           
		//Para verificar si el almacen esta a consigna
        MWarehouse wh = new MWarehouse(ctx, M_Warehouse_ID, null);
        
        /*
         * Si el almacen esta a consigna, es porbable que el registro en la relaci�n se haya creado desde otra organizacion
         * padre, eruiz
         */
        if(!wh.isConsigna())
        	sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", "EXME_EstServAlm"));
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setInt(1, M_Warehouse_ID);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				estServ = new MEXMEEstServ(ctx, rs, trxName);
				
			}
    	} catch (Exception e) {
    		s_log.log(Level.SEVERE, sql.toString(), e);
    	}finally {
    		DB.close(rs,pstmt);
    	}


		return estServ;
        
    }
    
    /**
     * Devuelve los almacenes relacionados a las estaciones
     * de servicio.
     *
     * @param ctx El contexto de la aplicaci&oacute;n
     *  @param Si incluye un registro en blanco en el combo  
     *
     * @return Un valor List con la lista de almacenes
     * @throws Exception en caso de ocurrir un error al procesar
     * la consulta.
     */
    public static List<MWarehouse> getAlmacenesxExt(Properties ctx, int EXME_EstServ_ID, String trxName) 
    throws SQLException {
    	String sql = 
    		"SELECT a.* FROM M_Warehouse a " + 
    		"INNER JOIN EXME_EstServAlm e " +
    		" ON (e.M_Warehouse_ID = a.M_Warehouse_ID) " +
    		"WHERE e.AD_Org_ID = " + Env.getContextAsInt(ctx, "#AD_Org_ID") +
    		" AND e.EXME_EstServ_ID = " + EXME_EstServ_ID + 
    		" AND e.IsActive = 'Y' AND a.IsActive = 'Y' " +
    		" ORDER BY a.M_Warehouse_ID DESC, a.Name";

    	PreparedStatement pstmt = null;
    	ResultSet rs = null;
    	List<MWarehouse> estServ = null;

    	try {

    		pstmt = DB.prepareStatement(sql, null);

    		rs = pstmt.executeQuery();

    		estServ = new ArrayList<MWarehouse>();

    		while(rs.next()){
    			MWarehouse alm = new MWarehouse(ctx, rs, trxName);
    			estServ.add(alm);
    		}

    	} catch (Exception e) {
    		s_log.log(Level.SEVERE, "getAlmacenesxEst"+sql, e);
    	}finally {
    		DB.close(rs,pstmt);
    	}
    	return estServ;
    }
    
	/**
	 * Devuelve los almacenes relacionados a las estaciones de servicio.
	 * 
	 * @param ctx
	 *            El contexto de la aplicaci&oacute;n
	 * @param EXME_EstServ_ID
	 *            ID de la estacion de servicio
	 * 
	 * @return Un valor List con la lista de almacenes
	 * @throws Exception
	 *             En caso de ocurrir un error al procesar la consulta.
	 */
	public static List<LabelValueBean> getAlmacenes(Properties ctx, int EXME_EstServ_ID, String trxName) throws SQLException {
		ArrayList<LabelValueBean> almacenes = null;
		StringBuilder sql = new StringBuilder();
		
		sql.append("SELECT a.Name, a.M_Warehouse_ID FROM M_Warehouse a INNER JOIN EXME_EstServAlm e ")
		.append("ON (e.M_Warehouse_ID = a.M_Warehouse_ID) AND e.EXME_EstServ_ID = '")
		.append(EXME_EstServ_ID)
		.append("' AND e.IsActive = 'Y' AND a.IsActive = 'Y' ")
		.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", "M_Warehouse", "a"))
		.append(" ORDER BY a.M_Warehouse_ID DESC, a.Name");

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			rs = pstmt.executeQuery();
			almacenes = new ArrayList<LabelValueBean>();

			while (rs.next()) {
				LabelValueBean datos = new LabelValueBean(rs.getString("Name"), String.valueOf(rs.getInt("M_Warehouse_ID")));
				almacenes.add(datos);
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, "getAlmacenesxEst"+sql, e);
		} finally {
			DB.close(rs,pstmt);
		}
		return almacenes;
	}
    
    
    /**
     * Devuelve los almacenes relacionados a las estaciones
     * de servicio.
     *
     * @param ctx El contexto de la aplicaci&oacute;n
     *  @param Si incluye un registro en blanco en el combo  
     *
     * @return Un valor List con la lista de almacenes
     * @throws Exception en caso de ocurrir un error al procesar
     * la consulta.
     */
    public static List<LabelValueBean> getAlmacenesxExt(Properties ctx, int estServicios,  boolean blanco) throws Exception {
       String sql = " SELECT EXME_EstServAlm.M_Warehouse_ID, a.Name FROM EXME_EstServAlm , M_Warehouse a" +
                  " WHERE EXME_EstServAlm.EXME_EstServ_ID =  "  + estServicios +
                  " AND EXME_EstServAlm.AD_Org_ID = " + Env.getContextAsInt(ctx, "#AD_Org_ID") + 
                  " AND EXME_EstServAlm.IsActive = 'Y' AND EXME_EstServAlm.M_Warehouse_ID = a.M_Warehouse_ID " +
                  " AND a.IsActive = 'Y' ";
       			  sql = MEXMELookupInfo.addAccessLevelSQL(ctx,sql,"EXME_EstServAlm")+	
                  "ORDER BY EXME_EstServAlm.M_Warehouse_ID DESC, a.Name";
        
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<LabelValueBean> estServ = new ArrayList<LabelValueBean>();
        
        try {

			if (blanco) {
				estServ.add(new LabelValueBean("", "0"));
			}
			pstmt = DB.prepareStatement(sql, null);

			rs = pstmt.executeQuery();

            while(rs.next()){
                LabelValueBean datos = 
                    new LabelValueBean(rs.getString("Name"), 
                            String.valueOf(rs.getInt("M_Warehouse_ID"))
                    );
                estServ.add(datos);
            }
    	} catch (Exception e) {
    		s_log.log(Level.SEVERE, "getAlmacenesxEst"+sql, e);
    	}finally {
    		DB.close(rs,pstmt);
    	}
        return estServ;
    }
    	/**
	 *  Devuleve los almacenes que piden un sercicio a un almacen especifico,
	 *  pe. PB1, PB2, PB3 pueden pedir servicios a ANATOMIA PATOLOGICA
	 *  este get devuelve una lista con los almacenes que le han solicitado servicios o 
	 *  que pueden solicitarle servicios a Anatomia patologica.y asi con cualquier otro
	 *  almacen 
	 *
	 *@param  ctx			Parametro de contexto que contiende algunos atributos de la sesion
	 *@param  almacen	Parametro que contiene el valor del almacen que surte servicios
	 *@return             	Lista de LabelValueBean
	 *@throws  Exception
	 */
    public static List<LabelValueBean> getWarehouseRelComp(Properties ctx, long almacen)
    throws Exception {

    	List<LabelValueBean> lista = new ArrayList<LabelValueBean> ();
    	String sql=	  "SELECT EXME_WareHouseRel.M_Warehouse_ID , WH.Name "+
    	" FROM EXME_WAREHOUSEREL , M_WAREHOUSE WH, M_WAREHOUSE WH2    "+
    	" WHERE EXME_WareHouseRel.m_warehouseREL_id= "+almacen+"   AND "+
    	" EXME_WareHouseRel.m_WAREHOUSE_id=WH.m_WAREHOUSE_id AND "+
    	" EXME_WareHouseRel.AD_Client_ID = "+ctx.getProperty("#AD_Client_ID")+" AND "+
    	" EXME_WareHouseRel.AD_Org_ID = "+ctx.getProperty("#AD_Org_ID")+"  AND "+
    	" EXME_WareHouseRel.ISACTIVE='Y'  "+
    	" AND EXME_WareHouseRel.m_WAREHOUSEREL_id =WH2.m_WAREHOUSE_id  ";
    	sql = MEXMELookupInfo.addAccessLevelSQL(ctx,sql,"EXME_WareHouseRel")+
    	" ORDER BY EXME_WareHouseRel.m_WAREHOUSE_id ";

    	PreparedStatement pstmt = null;
    	ResultSet rs = null;
    	try {
    		pstmt = DB.prepareStatement(sql, null);
    		rs = pstmt.executeQuery();
    		
    		while (rs.next()) {
    			LabelValueBean combo = new LabelValueBean(
    					String.valueOf(rs.getString("Name"))
    					, String.valueOf(rs.getInt("M_Warehouse_ID"))
    			);
    			lista.add(combo);
    		}

    	} catch (Exception e) {
    		s_log.log(Level.SEVERE, "getAlmacenesxEst"+sql, e);
    		throw new Exception("error.getWarehouseRel");
    	}finally {
    		DB.close(rs,pstmt);
    	}
    	return lista;
    }
    
    
    /**
     * Obtenemos el ID de la estacion de servicio dependiendo del almacen en cuestion
     * @param ctx
     * @param M_Warehouse_ID
     * @param trxName
     * @return
     */
    public static int getEstServId(Properties ctx, int M_Warehouse_ID, String trxName){
		return new Query(ctx, MEXMEEstServ.Table_Name, " EXME_EstServAlm.M_Warehouse_ID=?  AND EXME_EstServAlm.IsActive='Y' ", trxName)
		.setParameters(M_Warehouse_ID)//
		.setOnlyActiveRecords(true)//
		.addAccessLevelSQL(true)//
		.setJoins(new StringBuilder(" INNER JOIN EXME_EstServAlm ON EXME_EstServAlm.EXME_EstServ_ID=EXME_EstServ.EXME_EstServ_ID"))
		.firstIdOnly();
    }
}
