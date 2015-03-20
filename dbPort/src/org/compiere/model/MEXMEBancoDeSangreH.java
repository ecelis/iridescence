/*
 * Created on 08-feb-2005
 */
package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CCache;
import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.Env;

/**
 * Modelo para acceso a configuraci&oacute;n de banco de sangre.<p>
 *
 * <b>Modificado: </b> $Author: scardenas $<p>
 * <b>En :</b> $Date: 2006/09/14 21:37:23 $<p>
 *
 * @author scardenas
 * @version $Revision: 1.10 $
 */
public class MEXMEBancoDeSangreH extends X_EXME_BancoDeSangreH {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** Static logger */
	private static CLogger s_log = CLogger.getCLogger(MEXMEBancoDeSangreH.class);

	/**
	 * @param ctx
	 * @param ID
	 */
	public MEXMEBancoDeSangreH(Properties ctx, int ID, String trxName) {
		super(ctx, ID, trxName);
	}

	/**
	 * @param ctx
	 * @param rs
	 */
	public MEXMEBancoDeSangreH(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}
	

	protected static CCache<String,MEXMEBancoDeSangreH> s_cache = new CCache<String,MEXMEBancoDeSangreH>(Table_Name, 1);
	
	
    /**
	 * Obtenemos la configuracion de MEXMEBancoDeSangreH
	 * de un hospital en particular. (Cliente + Organizacion).
	 * @param ctx
	 * @return
	 */
    public static MEXMEBancoDeSangreH get(Properties ctx, String trxName) {
		if (ctx == null) {
			return null;
		}
		return get(ctx);
	}
    
    /**
	 * Si existe obtiene la configuracion de la cache
	 * @author Lorena Lama
	 * @param ctx
	 * @return
	 */
	public static MEXMEBancoDeSangreH get(Properties ctx) {
		MEXMEBancoDeSangreH retValue = (MEXMEBancoDeSangreH) s_cache.get(Table_Name);
		if (retValue != null
				// Expert : Lama (verificar la sesion, para usar o no el objeto del cache)
				&& (Env.getContextAsInt(ctx, "#AD_Session_ID") == Env.getContextAsInt(retValue.getCtx(), "#AD_Session_ID")))
			return retValue;
		return find(ctx, null);
	}
	
	/**
	 * Obtenemos la configuracion de MEXMEBancoDeSangreH
	 * de un hospital en particular. (Cliente + Organizacion).
	 * @param ctx
	 * @param trxName
	 * @return
	 */
	public static MEXMEBancoDeSangreH find(Properties ctx, String trxName) {
		MEXMEBancoDeSangreH retValue = null;

		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);

		sql.append("SELECT EXME_BancoDeSangreH.* ")
				.append("FROM EXME_BancoDeSangreH WHERE EXME_BancoDeSangreH.IsActive='Y' ")
				.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ",Table_Name))
				.append(" ORDER BY EXME_BancoDeSangreH.AD_Org_ID DESC ");

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			rs = pstmt.executeQuery();
			if (rs.next()){
				retValue = new MEXMEBancoDeSangreH(ctx, rs, trxName);
				s_cache.put(Table_Name, retValue);
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, "get", e);
		} finally {
			DB.close(rs,pstmt);
			rs = null;
			pstmt = null;
		}
		return retValue;
	}

	/**
	 * Regresa el registro del banco de sangre de un paciente
	 * @param ctx
	 * @param EXME_Paciente_ID
	 * @param trxName
	 * @return
	 */
	public static MEXMEBancoDeSangreH findPaciente(Properties ctx, int EXME_Paciente_ID,String trxName) {
		MEXMEBancoDeSangreH retValue = null;

		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);

		sql.append("SELECT MEXMEBancoDeSangreH.* ")
				.append("FROM MEXMEBancoDeSangreH WHERE MEXMEBancoDeSangreH.IsActive='Y' ")
				.append(" and MEXMEBancoDeSangreH.Exme_Paciente_id= ? ")
				.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ",Table_Name))
				.append(" ORDER BY MEXMEBancoDeSangreH.AD_Org_ID DESC ");

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1,EXME_Paciente_ID);
			rs = pstmt.executeQuery();
			if (rs.next()){
				retValue = new MEXMEBancoDeSangreH(ctx, rs, trxName);
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, "get", e);
		} finally {
			DB.close(rs,pstmt);
			rs = null;
			pstmt = null;
		}
		return retValue;
	}
	
	/**
	 * Listado de reg, de banco de sangre por paciente 
	 * @param ctx
	 * @param paciente_id
	 * @return
	 */
	public static ArrayList<MEXMEBancoDeSangreH> getEstudios(Properties ctx,int paciente_id) {
		MEXMEBancoDeSangreH retValue = null;
		final ArrayList<MEXMEBancoDeSangreH> lstEstudios = new ArrayList<MEXMEBancoDeSangreH>();

		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);

			 sql.append(" SELECT * ")
			 	.append(" FROM ")
				.append("     EXME_BANCODESANGREH ") 
				.append(" WHERE ")
				.append("     IsActive='Y' and EXME_PACIENTE_ID= ? ") 
				.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ",Table_Name))
				.append(" ORDER BY ")
				.append("  FECHAESTUDIO DESC	");

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setInt(1,paciente_id);
			
			rs = pstmt.executeQuery();
			int i=0;
			while (rs.next()){
				retValue = new MEXMEBancoDeSangreH(ctx, rs, null);
				lstEstudios.add(i++,retValue);
				s_cache.put(Table_Name, retValue);
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, "getEstudios", e);
		} finally {
			DB.close(rs,pstmt);
			rs = null;
			pstmt = null;
		}
		return lstEstudios;
	}

	

}
