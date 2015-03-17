/*
 * Created on 08-feb-2005
 */
package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.compiere.util.CCache;
import org.compiere.util.DB;
import org.compiere.util.Env;

/**
 * Modelo para acceso a configuraci&oacute;n de expediente cl&iacute;nico.<p>
 *
 * <b>Modificado: </b> $Author: mrojas $<p>
 * <b>En :</b> $Date: 2006/09/14 21:37:23 $<p>
 *
 * @author Hassan Reyes
 * @version $Revision: 1.10 $
 * Modificado por Lorena Lama
 */
public class MConfigEC extends X_EXME_ConfigEC {

	
	/** serialVersionUID */
	private static final long serialVersionUID = -9150949444930999626L;
	/** Static logger */
//	private static CLogger s_log = CLogger.getCLogger(MConfigEC.class);

	/**
	 * @param ctx
	 * @param ID
	 */
	public MConfigEC(Properties ctx, int ID, String trxName) {
		super(ctx, ID, trxName);
	}

	/**
	 * @param ctx
	 * @param rs
	 */
	public MConfigEC(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}
	

	protected static CCache<String,MConfigEC> s_cache = new CCache<String,MConfigEC>(Table_Name, 1);
	
	
    /**
	 * Obtenemos la configuracion de expediente clinico
	 * de un hospital en particular. (Cliente + Organizacion).
	 * @param ctx
	 * @return
	 */
    public static MConfigEC get(Properties ctx) {
		return get(ctx,null);
	}
    
    /**
	 * Si existe obtiene la configuracion de la cache
	 * @author Lorena Lama
	 * @param ctx
	 * @return
	 */
	public static MConfigEC get(Properties ctx, String trxName) {
		MConfigEC retValue = (MConfigEC) s_cache.get(getKeyName(ctx, Table_Name, false));
		if (retValue == null) {
			retValue = (MConfigEC) s_cache.get(getKeyName(ctx, Table_Name, true));// Client
			if (retValue == null) {
				retValue = find(ctx, trxName);
			}
		}
		return retValue;
	}
	
	/**
	 * Obtenemos la configuracion de expediente cl�nico
	 * de un hospital en particular. (Cliente + Organizacion).
	 * @param ctx
	 * @param trxName
	 * @return
	 */
	public static MConfigEC find(Properties ctx, String trxName) {
		MConfigEC retValue = new Query(ctx, Table_Name, "", trxName)//
		.setOnlyActiveRecords(true)//
		.addAccessLevelSQL(true)//
		.setOrderBy(" EXME_ConfigEC.AD_Org_ID DESC ")//
		.first();

//		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
//
//		sql.append("SELECT EXME_ConfigEC.* ")
//				.append("FROM EXME_ConfigEC WHERE EXME_ConfigEC.IsActive='Y' ")
//				.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ",Table_Name))
//				.append(" ORDER BY EXME_ConfigEC.AD_Org_ID DESC ");
//
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		try {
//			pstmt = DB.prepareStatement(sql.toString(), trxName);
//			rs = pstmt.executeQuery();
//			if (rs.next()){
//				retValue = new MConfigEC(ctx, rs, trxName);
				s_cache.put(getKeyName(retValue), retValue);
//			}
//		} catch (Exception e) {
//			s_log.log(Level.SEVERE, "get", e);
//		} finally {
//			DB.close(rs, pstmt);
//			rs = null;
//			pstmt = null;
//		}
		return retValue;
	}

//    /**
//	 * @param ctx
//	 * @return
//	 */
//	public static HashMap<String, Boolean> getConfigImp(Properties ctx) {
//
//		HashMap<String, Boolean> map = new HashMap<String, Boolean>(4);
//
//		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
//		
//        sql.append("SELECT imprecetamed, impnotaentmed, ")
//        	.append("impvaleentmed, impvaleparcial ")
//        	.append("FROM EXME_ConfigEC ")
//        	.append("WHERE EXME_ConfigEC.isActive = 'Y' ");
//        
//        sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, sql.toString(),"EXME_ConfigEC"));
//
//		sql.append(" ORDER BY EXME_ConfigEC.AD_Org_ID DESC ");
//
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		
//		try {
//			pstmt = DB.prepareStatement(sql.toString(), null);
//			rs = pstmt.executeQuery();
//           
//            if(rs.next()){
//	            map.put(new String("imprecetamed"),new Boolean(rs.getString("imprecetamed").equalsIgnoreCase("Y") ? true : false));
//	            map.put(new String("impnotaentmed"),new Boolean(rs.getString("impnotaentmed").equalsIgnoreCase("Y") ? true : false));
//	            map.put(new String("impvaleentmed"),new Boolean(rs.getString("impvaleentmed").equalsIgnoreCase("Y") ? true : false));
//	            map.put(new String("impvaleparcial"),new Boolean(rs.getString("impvaleparcial").equalsIgnoreCase("Y") ? true : false));
//            }
//            
//        } catch (SQLException sqle) {
//        	s_log.log(Level.SEVERE, "configImp", sqle);
//		} finally {
//			DB.close(rs, pstmt);
//		}
//
//		return map;
//	}

	/**
	 *  Agrega al ctx propiedades requeridas para el mtto de ingreso paciente
	 * @author lorena lama
	 */
	public void resetCtx() {
		//si tiene una config de precios para la organizacion
		Env.setContext(getCtx(), "#Privado", DB.TO_STRING(isPrivado()));
		Env.setContext(getCtx(), "#M_PriceListSE_ID", getM_PriceList_ID());
		Env.setContext(getCtx(), "#M_PriceListReg_ID", getM_PriceList_Reg_ID());
	}
	
//	/**
//	 * Creacion de cargos al momento de confirmar pedido
//	 * @param ctx Conexto
//	 * @return true: Aplicar pedido
//	 */
//	public static boolean isAplicaPedCtaPac(final Properties ctx){
//		final MConfigEC config = MConfigEC.get(ctx, null);
//		if (config == null) {
//			return false;
//		} else {
//			return config.isAplicaPedCtaPac() ;
//		}
//	}
	
	private final String rstr_IDIND = "@idInd@";
	private final String rstr_IDINDH = "@idIndH@";
	private final String rstr_DOCNO = "@docNo@";
	private final String rstr_IDPROD = "@idProd@";
	private final String rstr_VALUEPROD = "@valueProd@";
	private final String rstr_VALUEINTERV = "@valueInterv@";
	
	/**
	 * @param line
	 * @return
	 */
	public String getRadiologyURL(MEXMEActPacienteInd line) {
		String url;
		if (StringUtils.isNotEmpty(getKODAK_R()) && line != null) {
			/*url =
				getKODAK_R().replace("@idInd@", String.valueOf(line.getEXME_ActPacienteInd_ID()))
					.replace("@idIndH@", String.valueOf(line.getEXME_ActPacienteIndH_ID()))
					.replace("@docNo@", line.getActPacienteIndH().getDocumentNo()).replace("@idProd@", String.valueOf(line.getM_Product_ID()))
					.replace("@valueProd@", line.getProduct().getValue())
					.replace("@valueInterv@", line.getProduct().getIntevencion().getValue());*/
			url = getKODAK_R();
			if (url.contains(rstr_IDIND)) {
				url = url.replace(rstr_IDIND, String.valueOf(line.getEXME_ActPacienteInd_ID()));
			}
			if (url.contains(rstr_IDINDH)) {
				url = url.replace(rstr_IDINDH, String.valueOf(line.getEXME_ActPacienteIndH_ID()));
			}
			if (url.contains(rstr_DOCNO) && line.getActPacIndH() != null) {
				url = url.replace(rstr_DOCNO, line.getActPacIndH().getDocumentNo());
			}
			if (url.contains(rstr_IDPROD)) {
				url = url.replace(rstr_IDPROD, String.valueOf(line.getM_Product_ID()));
			}
			if (url.contains(rstr_VALUEPROD) && line.getProduct() != null) {
				url = url.replace(rstr_VALUEPROD, line.getProduct().getValue());
			}
			if (url.contains(rstr_VALUEINTERV) && line.getProduct() != null && line.getProduct().getIntevencion() != null) {
				if (line.getProduct().getIntevencion().getValue() != null) {
					url = url.replace(rstr_VALUEINTERV, line.getProduct().getIntevencion().getValue());
				}
			}
		} else {
			url = "";
		}
		return url;
	}
	
	public String valRadiologyURL(String url) {
		if (url.contains(rstr_IDIND) || url.contains(rstr_IDINDH) || url.contains(rstr_DOCNO) 
				|| url.contains(rstr_IDPROD) || url.contains(rstr_VALUEPROD)) {
			return "error.ece.confURLRIS";
		} else if (url.contains(rstr_VALUEINTERV)) {
			return "error.ece.confURLIntervencion";
		} else {
			return null;
		}
	}
}
