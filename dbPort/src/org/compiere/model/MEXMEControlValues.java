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

public class MEXMEControlValues extends X_EXME_ControlValues {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** Static Logger               */
	private static CLogger      s_log = CLogger.getCLogger (MEXMEControlValues.class);
	
	
	/**
	 * 
	 * @param ctx
	 * @param EXME_AccessCatalog_ID
	 * @param trxName
	 */
	public MEXMEControlValues(Properties ctx, int EXME_ControlValues_ID, String trxName) {
		super(ctx, EXME_ControlValues_ID, trxName);
	}
	
	/**
	 * 
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MEXMEControlValues(Properties ctx, ResultSet rs, String trxName) {
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
	public static MEXMEControlValues[] getAll(Properties ctx,String trxName,String whereCluse) {
		
		
		ArrayList<MEXMEControlValues> list = new ArrayList<MEXMEControlValues>();
		
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append("SELECT * FROM EXME_ControlValues where isactive='Y' ");
		 
		
		if (whereCluse!= null && whereCluse.length()>0)
			sql.append(" and "+whereCluse);
		
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(),trxName );
			rs = pstmt.executeQuery();
			while (rs.next()) {
				MEXMEControlValues cat = new MEXMEControlValues(ctx, rs, trxName);
				list.add(cat);
			}
			
		} catch (Exception e) {
			s_log.log(Level.SEVERE, "get", e);
		} finally {
			DB.close(rs, pstmt);
			pstmt = null;
			rs =null;
		}
		
		MEXMEControlValues[] cats = new MEXMEControlValues[list.size()];
		list.toArray(cats);
		return cats;
		
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
		sql.append(" SELECT count(*) FROM EXME_ControlValues where isactive = 'Y' ");
		
		
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
			DB.close(rs, pstmt);
			pstmt = null;
			rs =null;
		}
		
		return 0;
	}

	public static MEXMEControlValues getValueToUpdate(Properties ctx, String referenceName, String trxName) {
			
		
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		
		/*
		 * query que obtiene en base al nombre de la tabla referenciada 
		 * sus valor del query para representarlo en un objeto 
		 * y devolverlo asi para actualizar este.
		 */
		sql.append(" SELECT * FROM EXME_ControlValues where isactive = 'Y' ");
		sql.append(" and upper(trim(name)) =  '" + referenceName.trim().toUpperCase());
		sql.append("'");
		
		
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		
		try {
			pstmt = DB.prepareStatement(sql.toString(),trxName );
			rs = pstmt.executeQuery();
			if (rs.next()) {
				return new MEXMEControlValues(ctx,rs.getInt("EXME_ControlValues_ID"),trxName);
			}
			
		} catch (Exception e) {
			s_log.log(Level.SEVERE, "get", e);
		} finally {
			DB.close(rs, pstmt);
			pstmt = null;
			rs =null;
		}
		
		return null;
	}	
	
}
