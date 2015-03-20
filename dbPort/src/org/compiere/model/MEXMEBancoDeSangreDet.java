/*
 * Created on 08-feb-2005
 */
package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
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
public class MEXMEBancoDeSangreDet extends X_EXME_BancoDeSangreDet {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** Static logger */
	private static CLogger s_log = CLogger.getCLogger(MEXMEBancoDeSangreDet.class);

	/**
	 * @param ctx
	 * @param ID
	 */
	public MEXMEBancoDeSangreDet(Properties ctx, int ID, String trxName) {
		super(ctx, ID, trxName);
	}

	/**
	 * @param ctx
	 * @param rs
	 */
	public MEXMEBancoDeSangreDet(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}
	

	protected static CCache<String,MEXMEBancoDeSangreDet> s_cache = new CCache<String,MEXMEBancoDeSangreDet>(Table_Name, 1);
	
	
    /**
	 * Obtenemos la configuracion de MEXMEBancoDeSangreH
	 * de un hospital en particular. (Cliente + Organizacion).
	 * @param ctx
	 * @return
	 */
    public static MEXMEBancoDeSangreDet get(Properties ctx, String trxName) {
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
	public static MEXMEBancoDeSangreDet get(Properties ctx) {
		MEXMEBancoDeSangreDet retValue = (MEXMEBancoDeSangreDet) s_cache.get(Table_Name);
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
	public static MEXMEBancoDeSangreDet find(Properties ctx, String trxName) {
		MEXMEBancoDeSangreDet retValue = null;

		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);

		sql.append("SELECT EXME_BancoDeSangreDet.* ")
				.append("FROM EXME_BancoDeSangreDet WHERE EXME_BancoDeSangreDet.IsActive='Y' ")
				.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ",Table_Name))
				.append(" ORDER BY EXME_BancoDeSangreDet.AD_Org_ID DESC ");

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			rs = pstmt.executeQuery();
			if (rs.next()){
				retValue = new MEXMEBancoDeSangreDet(ctx, rs, trxName);
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
	 * 
	 * @param ctx
	 * @param trxName
	 * @return
	 */
	public static ArrayList<MEXMEBancoDeSangreDetView> getRecords(Properties ctx, String trxName,int PacienteID, Date fecha,String folio) {
		ArrayList<MEXMEBancoDeSangreDetView> lst = new ArrayList<MEXMEBancoDeSangreDetView>();
		
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		
		sql.append("SELECT EXME_BancoDeSangreH.ESTUDIOSOLICITADO,EXME_BancoDeSangreH.FOLIOANALISIS,EXME_BancoDeSangreH.CURP,")
				.append(" EXME_BancoDeSangreH.OBSERVACIONESANALISIS,EXME_BancoDeSangreDet.ANALISIS, EXME_BancoDeSangreDet.RESULTADOANALISIS  ")
				.append("FROM EXME_BancoDeSangreDet EXME_BancoDeSangreDet INNER JOIN EXME_BANCODESANGREH EXME_BANCODESANGREH")
				.append("  ON EXME_BancoDeSangreDet.EXME_BANCODESANGREH_ID=EXME_BANCODESANGREH.EXME_BANCODESANGREH_ID ")
				.append(" WHERE EXME_BancoDeSangreDet.IsActive='Y' AND EXME_BANCODESANGREH.EXME_PACIENTE_ID= ? ")
				.append(" and EXME_BancoDeSangreH.FOLIOANALISIS = ? ")
				.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ",Table_Name))
				.append(" ORDER BY EXME_BancoDeSangreDet.AD_Org_ID DESC ");

		//MEXMEBancoDeSangreDet det = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1,PacienteID);
			pstmt.setString(2,folio);
			MEXMEBancoDeSangreDetView detView = null;
			rs = pstmt.executeQuery();
			while (rs.next()){
				detView = new MEXMEBancoDeSangreDetView();
				detView.setEstudioSolicitado(rs.getString(1));
				detView.setFolioAnalisis(rs.getString(2));
				detView.setCURP(rs.getString(3));
				detView.setObservacionesAnalisis(rs.getString(4));
				detView.setAnalisis(rs.getString(5));
				detView.setResultadosAnalisis(rs.getString(6));
				lst.add(detView);
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, "get", e);
		} finally {
			DB.close(rs,pstmt);
			rs = null;
			pstmt = null;
		}

		
		return lst;
	}	

	
	public static MEXMEBancoDeSangreDet findPaciente(Properties ctx, int EXME_Paciente_ID,String trxName) {
		MEXMEBancoDeSangreDet retValue = null;

		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);

		sql.append("SELECT EXME_BancoDeSangreDet.* ")
				.append("FROM EXME_BancoDeSangreDet WHERE EXME_BancoDeSangreDet.IsActive='Y' ")
				.append(" and EXME_BancoDeSangreDet.Exme_Paciente_id= ? ")
				.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ",Table_Name))
				.append(" ORDER BY EXME_BancoDeSangreDet.AD_Org_ID DESC ");

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1,EXME_Paciente_ID);
			rs = pstmt.executeQuery();
			if (rs.next()){
				retValue = new MEXMEBancoDeSangreDet(ctx, rs, trxName);
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
	
	
	public static ArrayList<MEXMEBancoDeSangreDet> getEstudios(Properties ctx,int paciente_id) {
		MEXMEBancoDeSangreDet retValue = null;
		ArrayList<MEXMEBancoDeSangreDet> lstEstudios = new ArrayList<MEXMEBancoDeSangreDet>();

		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);

			 sql.append(" SELECT * ")
			 	.append(" FROM ")
				.append("     EXME_BANCODESANGREH BS") 
				.append(" WHERE ")
				.append("     BS.IsActive='Y' and BS.EXME_PACIENTE_ID= ? ") 
				.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ",Table_Name))
				.append(" ORDER BY ")
				.append("  BS.FECHAESTUDIO DESC	");

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setInt(1,paciente_id);
			rs = pstmt.executeQuery();
			int i=0;
			while (rs.next()){
				retValue = new MEXMEBancoDeSangreDet(ctx, rs, null);
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
