/*
 * Derechos Reservados (c) a partir 2005 Expert Sistemas Computacionales, S. A. de C. V.
 * Sistema MedSys
 */



package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;

/**
 *  <p>
 * 
 * Creado: 2008/05/10<p>
 * Modificado: $Author: scardenas $<p>
 * Fecha: $Date: 2008/05/10 05:05:17 $<p>
 *
 * @author samuel cardenas
 * @version $Revision: 1.1 $
 */

public class MEXMEAccessCatalog extends X_EXME_AccessCatalog {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** Static Logger               */
	private static CLogger      s_log = CLogger.getCLogger (MEXMEAccessCatalog.class);
	
	
	
	
	/**
	 * 
	 * @param ctx
	 * @param EXME_AccessCatalog_ID
	 * @param trxName
	 */
	public MEXMEAccessCatalog(Properties ctx, int EXME_AccessCatalog_ID, String trxName) {
		super(ctx, EXME_AccessCatalog_ID, trxName);
	}
	
	/**
	 * 
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MEXMEAccessCatalog(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}
	
	/**
	 * 
	 * @param ctx
	 * @param AD_OrgID
	 * @param trxName
	 * @return
	 * @throws SQLException
	 */
	public static MEXMEAccessCatalog[] getAll(Properties ctx,String trxName) {
		ArrayList<MEXMEAccessCatalog> list = new ArrayList<MEXMEAccessCatalog>();
		
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append("SELECT * FROM EXME_AccessCatalog where isactive='Y' ");
		
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(),trxName );
			rs = pstmt.executeQuery();
			while (rs.next()) {
				MEXMEAccessCatalog cat = new MEXMEAccessCatalog(ctx, rs, trxName);
				list.add(cat);
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, "get", e);
		} finally {
			DB.close(rs,pstmt);
			pstmt = null;
			rs =null;
		}
		
		MEXMEAccessCatalog[] cats = new MEXMEAccessCatalog[list.size()];
		list.toArray(cats);
		return cats;
		
	}
	
	public static ArrayList<AccessCatalogBean> getTablesToUpdate(String trxName) {
		
		ArrayList<AccessCatalogBean> list = new ArrayList<AccessCatalogBean>();
		
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		
		/*
		 * query que obtiene de Tabla de Accesos  	: EXME_AccessCatalog
		 * 1: nombre de la tabla
		 * 2: acceso original
		 * 3: acceso multiorganizacional
		 * 4: acceso uniorganizacional
		 * 5: numero que se actualiza para indicar el numero de 
		 * 	  actualizaciones que se lleva una vez terminada la actualizaci�n
		 * 
		 * 											: AD_Table
		 * 6: id de la tabla referenciada
		 * 7: nivel de acceso de la tabla referenciada
		 *  
		 */
		sql.append(" SELECT trim(cat.Value),cat.ORIGINALACCESS,cat.MULTIACCESS, ");
		sql.append(" cat.UNIQUEACCESS,tab.ad_table_id,tab.ACCESSLEVEL ");
		sql.append(" FROM EXME_AccessCatalog cat inner join ad_table tab on ");
		sql.append(" (upper(cat.value) = upper(tab.TABLENAME))");
		
		
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		//ArrayList<AccessCatalogBean> arratACB = new ArrayList<AccessCatalogBean>();
		int i=0;
		
		try {
			pstmt = DB.prepareStatement(sql.toString(),trxName );
			rs = pstmt.executeQuery();
			while (rs.next()) {
				list.add(i,new AccessCatalogBean());
				list.get(i).setValue(rs.getString(1));
				list.get(i).setOriginalAccess(rs.getInt(2));
				list.get(i).setMultiAccess(rs.getInt(3));
				list.get(i).setUniqueAccess(rs.getInt(4));
				list.get(i).setAD_Table_ID(rs.getInt(5));
				list.get(i).setAD_Table_AccessLevel(rs.getInt(6));
				i++;			
			}
			
			i=0;
		} catch (Exception e) {
			s_log.log(Level.SEVERE, "get", e);
		} finally {
			DB.close(rs,pstmt);
			pstmt = null;
			rs =null;
			i =0;
		}
		
		return list;
	}
	
	/**
	 * Metodo que devuelve cero si existen cero valores en la tabla o si no procesa
	 * correctamente
	 * 
	 * @param trxName
	 * @return
	 */
	public static int getRowNumCatalog(String  trxName) {
		
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		
		/*
		 * query que obtiene el total de tablas contenidas en el catalogo
		 */
		sql.append(" SELECT count(*) FROM EXME_AccessCatalog where isactive = 'Y' ");
		
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		
		try {
			pstmt = DB.prepareStatement(sql.toString(),trxName );
			rs = pstmt.executeQuery();
			while (rs.next()) {
				return rs.getInt(1);
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, "get", e);
		} finally {
			DB.close(rs,pstmt);
			pstmt = null;
			rs =null;
		}
		
		return 0;
	}	
	
}
