/*
 * Created on 11-feb-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
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
import org.compiere.util.KeyNamePair;


/**
 * @author Hassan Reyes
 *
 */
public class MTipoProd extends X_EXME_TipoProd {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static CLogger s_log = CLogger.getCLogger(MTipoProd.class);
	/** Costantes para los registros Obligatorios.
     *  Estos registron no deberan ser borrados por 
     * ningun motivo.
     * */
    public static final String SERVICIOS_VALUE = "S";
    public static final String MEDICAMENTOS_VALUE = "MEDICAMENTO";
    public static final String DESCARTABLES_VALUE = "MATERIAL DESCARTABLE";
    
    //variable string con la sentencia sql
 //   private static String sql = null;
    //PreparedStatement con la sentencia sql
  //  private static PreparedStatement pstmt = null;
    //ResultSet utilizado para manipular los resultados
  //  private static ResultSet rs = null;

    /**
     * @param ctx
     * @param ID
     */
    public MTipoProd(Properties ctx, int ID, String trx) {
        super(ctx, ID, trx);
    }

    /**
     * @param ctx
     * @param rs
     */
    public MTipoProd(Properties ctx, ResultSet rs, String trx) {
        super(ctx, rs, trx);
    }
    
    /**
     * Tipo de producto por value
     * @param ctx
     * @param value
     * @param trxName
     * @return
     */
    public static MTipoProd get(Properties ctx, String value, String trxName) {
		StringBuilder st = new StringBuilder("select * from exme_tipoprod where value = ? ");
		MTipoProd unidad = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(st.toString(), null);
			pstmt.setString(1, value);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				unidad = new MTipoProd(ctx, rs, trxName);
			}
			rs.close();
			pstmt.close();
			pstmt = null;
		} catch (Exception e) {
			s_log.log(Level.SEVERE, st.toString(), e);
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				pstmt = null;
				if (rs != null)
					rs.close();
				rs = null;
			} catch (Exception e) {
				pstmt = null;
				rs = null;
			}
		}
		return unidad;
	}

	public static List<LabelValueBean> getLVB(Properties ctx, String trxName) {
		final List<LabelValueBean> lista = new ArrayList<LabelValueBean>();
		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			sql.append("SELECT  * FROM ").append(Table_Name).append(" where isActive = 'Y' ");
			sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name));

			pstmt = DB.prepareStatement(sql.toString(), trxName);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				lista.add(new LabelValueBean(rs.getString(COLUMNNAME_Name), rs.getString(COLUMNNAME_EXME_TipoProd_ID)));
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs,pstmt);
		}
		return lista;
	}
	
	
	/**
	 * metodo para obtener el listado de tipos de producto
	 * @param ctx
	 * @param trxName
	 * @return
	 */
	public static List<KeyNamePair> getList(Properties ctx, String trxName) {
		
		final List<KeyNamePair> lista = new ArrayList<KeyNamePair>();
		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			sql.append("SELECT  * FROM ").append(Table_Name).append(" where isActive = 'Y' ");
			sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name));

			pstmt = DB.prepareStatement(sql.toString(), trxName);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				lista.add(new KeyNamePair(rs.getInt(COLUMNNAME_EXME_TipoProd_ID), rs.getString(COLUMNNAME_Name)));
			}
			
		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs,pstmt);
		}
		return lista;
	}
    
    
}
