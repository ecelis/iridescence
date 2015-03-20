/******************************************************************************
 * The contents of this file are subject to the   Compiere License  Version 1.1
 * ("License"); You may not use this file except in compliance with the License
 * You may obtain a copy of the License at http://www.compiere.org/license.html
 * Software distributed under the License is distributed on an  "AS IS"  basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License for
 * the specific language governing rights and limitations under the License.
 * The Original Code is Compiere ERP & CRM Smart Business Solution. The Initial
 * Developer of the Original Code is Jorg Janke. Portions created by Jorg Janke
 * are Copyright (C) 1999-2005 Jorg Janke.
 * All parts are Copyright (C) 1999-2005 ComPiere, Inc.  All Rights Reserved.
 * Contributor(s): ______________________________________.
 *****************************************************************************/
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
 *	Delegacion Model (Value Object)
 *
 *  @author 	Valentin Garcia
 *  @version 	$Id: MDelegacion.java,v 1.2 2006/08/11 02:26:22 mrojas Exp $
 */
public final class MDelegacion extends X_EXME_Delegacion
	/*implements Comparator, Serializable*/
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8030578229372945647L;
	/**	Delegacion Cache					*/
//	private static CCache<String,MDelegacion>	s_delegacion = null;
	/**	Default Delegacion 				*/
//	private static MDelegacion		s_default = null;
	/**	Static Logger					*/
	private static CLogger		s_log = CLogger.getCLogger (MDelegacion.class);
	
	/**
	 *	Create Delegacion from current row in ResultSet
	 * 	@param ctx context
	 *  @param rs ResultSet
	 */
	public MDelegacion (Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
	}	//	MDelegacion
	
	
	/*************************************************************************
	 *	Create empty Delegacion
	 * 	@param ctx context
	 * 	@param EXME_Delegacion_ID ID
	 */
	public MDelegacion (Properties ctx, int EXME_Delegacion_ID, String trxName)
	{
		super (ctx, EXME_Delegacion_ID, trxName);
		/*if (EXME_Delegacion_ID == 0)
		{
		}*/
	}  
	
	
	/**
	 * 	Get Country (cached)
	 * 	@param ctx context
	 *	@param EXME_Delegacion_ID ID
	 *	@return Delegacion
	 */
	/*public static MDelegacion get (Properties ctx, int EXME_Delegacion_ID)
	{
		if (s_delegacion == null || s_delegacion.size() == 0)
			loadAllDelegaciones(ctx);
		String key = String.valueOf(EXME_Delegacion_ID);
		MDelegacion d = (MDelegacion)s_delegacion.get(key);
		if (d != null)
			return d;
		d = new MDelegacion (ctx, EXME_Delegacion_ID, null);
		if (d.getEXME_Delegacion_ID() == EXME_Delegacion_ID)
		{
			s_delegacion.put(key, d);
			return d;
		}
		return null;
	}	//	get

	/**
	 * 	Get Default Delegacion
	 * 	@param ctx context
	 *	@return Delegacion
	 */
/*	public static MDelegacion getDefault (Properties ctx)
	{
		if (s_delegacion == null || s_delegacion.size() == 0)
			loadAllDelegaciones(ctx);
		return s_default;
	}	//	get

	/**
	 *	Return Delegaciones as Array
	 * 	@param ctx context
	 *  @return MDelegacion Array
	 */
//	@SuppressWarnings("unchecked")
/*	public static MDelegacion[] getDelegaciones(Properties ctx)
	{
		if (s_delegacion == null || s_delegacion.size() == 0)
			loadAllDelegaciones(ctx);
		MDelegacion[] retValue = new MDelegacion[s_delegacion.size()];
		s_delegacion.values().toArray(retValue);
		Arrays.sort(retValue, new MDelegacion(ctx, 0, null));
		return retValue;
	}	//	getDelegaciones

	/**
	 * 	Load Delegaciones.
	 * 	Set Default Language to Client Language
	 *	@param ctx context
	 */
/*	private static void loadAllDelegaciones (Properties ctx)
	{
		MClient client = MClient.get (ctx);
		MLanguage lang = MLanguage.get(ctx, client.getAD_Language());
		MDelegacion del = null;
		
		Statement stmt = null;
		ResultSet rs = null;
		
		s_delegacion = new CCache<String,MDelegacion>("EXME_Delegacion", 250);
		String sql = "SELECT * FROM EXME_Delegacion WHERE IsActive='Y'";
		try
		{
			stmt = DB.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next())
			{
				MDelegacion d = new MDelegacion (ctx, rs, null);
				s_delegacion.put(String.valueOf(d.getEXME_Delegacion_ID()), d);
				
				if(d.getEXME_Delegacion_ID()==1000000){
					del = d;
					s_default = d;
				}
			}
			rs.close();
			stmt.close();
		}
		catch (SQLException e)
		{
			s_log.log(Level.SEVERE, sql, e);
		}
		if (s_default == null)
			s_default = del;
		s_log.fine("#" + s_delegacion.size() 
			+ " - Default=" + s_default);
	}	//	loadAllCountries
	
	*/
	

	/**
	 * 
	 * @param ctx
	 * @return
	 */
	public static List<LabelValueBean> getDelegaciones (Properties ctx)
	{
		List<LabelValueBean> lista = new ArrayList<LabelValueBean>();
		
    	PreparedStatement pstmt = null;
    	ResultSet rs = null;
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		 sql.append (" SELECT Description, EXME_Delegacion_ID ") 
			.append	(" FROM EXME_Delegacion WHERE IsActive='Y' ");			
		 sql = new StringBuilder (MEXMELookupInfo.addAccessLevelSQL(ctx, sql.toString(), "EXME_Delegacion"));
		 sql.append	(" ORDER BY Description ");
		 try
		{
	 		pstmt = DB.prepareStatement(sql.toString(), null);
    		rs = pstmt.executeQuery();
    		
			while(rs.next())
			{
				lista.add(new LabelValueBean(rs.getString(1), rs.getString(2)));
			}
			
    	} catch (Exception e) {
    		s_log.log(Level.SEVERE, sql.toString(), e);
    	}finally {
    		DB.close(rs, pstmt);
    	}

    	return lista; 
    }
    public static long getDefault(Properties ctx, String trxName)  {

		long del = 0;		
    	StringBuilder sql = new StringBuilder("SELECT EXME_Delegacion_ID ")
				                    .append(" FROM EXME_Delegacion WHERE isActive LIKE 'Y' AND isDefault = 'Y'");		

		sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, sql.toString(), "EXME_Delegacion"));
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				del = rs.getLong("EXME_Delegacion_ID");
			}

		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
        } finally {
        	DB.close(rs, pstmt);
        }
    	return del;	
    }

}	//	MDelegacion
