package org.compiere.model;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.Env;

public class MEXMETPaqBaseDet 
{
//extends X_EXME_T_PaqBaseDet{
//	
//	/**
//	 * 
//	 */
//	private static final long serialVersionUID = 1L;
//	
////	public static HashMap<Integer,Integer> valueMap = new HashMap<Integer,Integer>();
//	
//	/** static logger   */
//	private static CLogger s_log = CLogger.getCLogger(MEXMETPaqBaseDet.class);
//	
//	public MEXMETPaqBaseDet(Properties ctx, int EXME_T_PaqBaseDet, String trxName) {
//		super(ctx, EXME_T_PaqBaseDet, trxName);
//	}
//	
//	public MEXMETPaqBaseDet(Properties ctx, ResultSet rs, String trxName) {
//		super(ctx, rs, trxName);
//	}
//	
//	public static MEXMETPaqBaseDet get(Properties ctx, MPaqBaseDet paqBaseOrig, String trxName) {
//		MEXMETPaqBaseDet paqBase = new MEXMETPaqBaseDet(ctx, 0, trxName);
//		paqBase.setC_UOM_ID(paqBaseOrig.getC_UOM_ID());
//		paqBase.setCantidad(paqBaseOrig.getCantidad());
//		paqBase.setEXME_PaqBase_Version_ID(paqBaseOrig.getEXME_PaqBase_Version_ID());
//		paqBase.setM_Product_ID(paqBaseOrig.getM_Product_ID());
//		paqBase.setAD_Session_ID(Env.getContextAsInt(ctx, "#AD_Session_ID"));
//		paqBase.setQtyPendiente(paqBaseOrig.getCantidad());
//		return paqBase;
//	}
//	
//	private MProduct m_product = null;
//	
//	public MProduct getProduct(){
//		if(m_product == null || getM_Product_ID() == 0)
//			m_product = new MProduct(getCtx(), getM_Product_ID(), get_TrxName()); 
//		return m_product;
//	}
//	
//	private MUOM m_uom = null;
//	
//	public MUOM getUom() {
//		
//		if(m_uom==null || getC_UOM_ID() == 0)
//			m_uom = new MUOM(getCtx(), getC_UOM_ID(), get_TrxName());
//		
//		return new MUOM(getCtx(), getC_UOM_ID(), null);
//	}
//	
//
//	/**
//	 * Verificar cuales productos del mini paquete son del paquete original y las 
//	 * cantidades que no esten excedidas
//	 * @param ctx
//	 * @param EXME_CtaPac_ID
//	 * @param whereClause
//	 * @param trxName
//	 * @return
//	 */
//	public static void paqueteVsCargo(Properties ctx, int EXME_PaqBase_Version_ID, 
//			int AD_Session_ID, String trxName)throws Exception {
//		
//		 StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
//		sql.append(" SELECT EXME_T_PaqBaseDet.EXME_T_PaqBaseDet_ID, ")
//		.append(" EXME_T_PaqBaseDet.Cantidad AS QtyPack, EXME_T_PaqBaseDet.C_UOM_ID AS UomPack, ")
//		.append(" tpbd.EXME_T_PaqBaseDetCargo_ID, ")
//		.append(" tpbd.Cantidad AS QtyCargo, tpbd.C_UOM_ID AS UomCargo, ")
//		.append(" EXME_T_PaqBaseDet.Cantidad - tpbd.Cantidad AS CantPendiente ")
//		.append(" FROM EXME_T_PaqBaseDet ")
//		.append(" INNER JOIN EXME_T_PaqBaseDetCargo  tpbd ")
//		.append(" ON ( tpbd.M_Product_ID = EXME_T_PaqBaseDet.M_Product_ID AND tpbd.IsActive = 'Y') ")
//		.append(" WHERE EXME_T_PaqBaseDet.IsActive = 'Y'  ");
//		
//		sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, sql.toString(), "EXME_T_PaqBaseDet"));
//				
//		sql.append(" AND EXME_T_PaqBaseDet.EXME_PaqBase_Version_ID = ? ")
//		.append(" AND tpbd.EXME_PaqBase_Version_ID = ? ")
//		.append(" AND EXME_T_PaqBaseDet.AD_Session_ID = ?")
//		.append(" AND tpbd.AD_Session_ID = ?")
//		.append(" ORDER BY EXME_T_PaqBaseDet.Secuencia ASC ");
//		
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		boolean error = false;
//		try {
//			pstmt = DB.prepareStatement(sql.toString(), trxName);
//			pstmt.setInt(1, EXME_PaqBase_Version_ID);
//			pstmt.setInt(2, EXME_PaqBase_Version_ID);
//			pstmt.setInt(3, AD_Session_ID);
//			pstmt.setInt(4, AD_Session_ID);
//			rs = pstmt.executeQuery();
//			while (rs.next() && !error) {
//				if(rs.getInt("UomPack") == rs.getInt("UomCargo")){
//					
//					MEXMETPaqBaseDet paq = new MEXMETPaqBaseDet(ctx, rs.getInt("EXME_T_PaqBaseDet_ID"),trxName);
//					MEXMETPaqBaseDetCargo cargo = new MEXMETPaqBaseDetCargo(ctx, rs.getInt("EXME_T_PaqBaseDetCargo_ID"),trxName);
//					
//					/**
//					 * La cantidad pendiente dentro de la tabla de paquete-cargo refiere, 
//					 * si es positiva, a la cantidad que falta segun el paquete
//					 * si es negativa, a la cantidad que excede
//					 */
//					/**
//					 * La cantidad pendiente dentro de la tabla de paquete-paquete 
//					 * solo puede ser cero o mayor a este y refiere, si es mayor de cero, 
//					 * a la cantidad que aun falta para completar el paquete
//					 */
//					
//					if (rs.getBigDecimal("CantPendiente").compareTo(Env.ZERO)<=0){
//						//La cantidad cargada es mayor a la permitida
//						paq.setQtyPendiente(Env.ZERO);
//						paq.setProcesado(true);
//						cargo.setQtyPendiente(rs.getBigDecimal("CantPendiente"));//Negativa - Excede
//						if(rs.getBigDecimal("CantPendiente").compareTo(Env.ZERO)==0)
//							cargo.setProcesado(true);
//					}else{
//						//La cantidad del paquete no ha sido rebasada
//						cargo.setQtyPendiente(Env.ZERO);
//						cargo.setProcesado(true);
//						paq.setQtyPendiente(rs.getBigDecimal("CantPendiente"));
//					}
//					
//					if(!paq.save(trxName))
//						error = true;
//					
//					if(!error && !cargo.save(trxName))
//						error = true;
//				}
//			}
//			
//			
//		} catch (Exception e) {
//			throw new Exception();
//		} finally {
//			DB.close(rs, pstmt);
//			pstmt = null;
//			rs=null;
//		}
//		
//		if(error){
//			throw new Exception("error.paqute.noSave");
//		}
//	}
//	
//	/**
//	 * Evaluamos las lineas que han quedado pendientes 
//	 * de relacionarse con algun cargo
//	 * @param ctx
//	 * @param EXME_CtaPac_ID
//	 * @param whereClause
//	 * @param trxName
//	 * @return
//	 */
//	public static void lineasSinCargo(Properties ctx, int EXME_PaqBase_Version_ID, 
//			int AD_Session_ID, String fecha, String trxName)throws Exception {
//		
//		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
//		sql.append(" SELECT * FROM EXME_T_PaqBaseDet  ")
//		.append(" WHERE EXME_T_PaqBaseDet.IsActive = 'Y' ")
//		.append("  AND EXME_T_PaqBaseDet.procesado = 'N' ");
//		//" AND EXME_T_PaqBaseDet.QtyPendiente > 0  ";//� <> 0 es lo mismo en la tabla t del paquete nunca sera menor de cero
//		
//		sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, sql.toString(), "EXME_T_PaqBaseDet"));
//
//		sql.append(" AND EXME_T_PaqBaseDet.EXME_PaqBase_Version_ID = ? ")
//		.append(" AND EXME_T_PaqBaseDet.AD_Session_ID = ? ")
//		.append(" ORDER BY EXME_T_PaqBaseDet.Secuencia ASC ");
//		
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		boolean error = false;
//		try {
//			pstmt = DB.prepareStatement(sql.toString(), trxName); 
//			pstmt.setInt(1, EXME_PaqBase_Version_ID);
//			pstmt.setInt(2, AD_Session_ID);
//			rs = pstmt.executeQuery();
//			while (rs.next() && !error) {
//				MEXMETPaqBaseDet paq = new MEXMETPaqBaseDet(ctx, rs,trxName);
//				sustituto(ctx, EXME_PaqBase_Version_ID, AD_Session_ID,
//						fecha, paq, trxName);
//				
//			}
//			
//			
//		} catch (Exception e) {
//			throw new Exception();
//		} finally {
//			DB.close(rs, pstmt);
//			pstmt = null;
//			rs=null;
//		}
//		
//		if(error){
//			throw new Exception("error.paqute.noSave");
//		}
//	}
//	
//	
//	/**
//	 * Evaluamos las lineas que han quedado pendientes 
//	 * de relacionarse con un paquete
//	 * @param ctx
//	 * @param EXME_CtaPac_ID
//	 * @param whereClause
//	 * @param trxName
//	 * @return
//	 */
//	public static void sustituto(Properties ctx, int EXME_PaqBase_Version_ID, 
//			int AD_Session_ID, String fecha, MEXMETPaqBaseDet paq,
//			String trxName)throws Exception {
//		
//		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
//		sql.append(" SELECT  EXME_T_PaqBaseDetCargo.* ")
//		.append(" FROM EXME_T_PaqBaseDetCargo  ")
//		.append(" WHERE EXME_T_PaqBaseDetCargo.IsActive = 'Y' ")
//		.append(" AND EXME_T_PaqBaseDetCargo. procesado = 'N'  "); 
//		//" AND EXME_T_PaqBaseDetCargo.QtyPendiente > 0 " + 
//		
//		sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, sql.toString(), "EXME_T_PaqBaseDetCargo"));
//		
//		sql.append("  AND EXME_T_PaqBaseDetCargo.EXME_PaqBase_Version_ID = ? ")
//		.append(" AND EXME_T_PaqBaseDetCargo.AD_Session_ID = ? ")
//		.append(" AND EXME_T_PaqBaseDetCargo.M_Product_ID IN ( SELECT s.Substitute_Id   ")
//		.append(" FROM M_Product p INNER JOIN M_Substitute s ON (s.M_Product_ID = p.M_Product_ID AND s.Isactive = 'Y') ")
//		.append(" WHERE p.IsActive = 'Y' AND p.M_Product_ID = ? ")
//		.append(" AND s.ValidFrom <= to_date(?,'dd/mm/yyyy') AND s.ValidTo >= to_date(?,'dd/mm/yyyy')  )  ")
//		.append(" ORDER BY EXME_T_PaqBaseDetCargo.Secuencia ASC ");
//		
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		boolean error = false;
//		try {
//			pstmt = DB.prepareStatement(sql.toString(), trxName);
//			pstmt.setInt(1, EXME_PaqBase_Version_ID);
//			pstmt.setInt(2, AD_Session_ID);
//			pstmt.setInt(3, paq.getM_Product_ID());
//			pstmt.setString(4, fecha);
//			pstmt.setString(5, fecha);
//			
//			rs = pstmt.executeQuery();
//			while (rs.next() && !error) {
//				
//				MEXMETPaqBaseDetCargo cargo = new MEXMETPaqBaseDetCargo(ctx, rs, trxName);
//				
//				BigDecimal cantPendiente = paq.getQtyPendiente().subtract(cargo.getQtyPendiente());
//				
//				if (cantPendiente.compareTo(Env.ZERO)<=0){
//					//La cantidad cargada es mayor a la permitida
//					paq.setQtyPendiente(Env.ZERO);
//					paq.setProcesado(true);
//					cargo.setQtyPendiente(cantPendiente);
//					if(cantPendiente.compareTo(Env.ZERO)==0)
//						cargo.setProcesado(true);
//				}else{
//					//La cantidad del paquete no ha sido rebasada
//					cargo.setQtyPendiente(Env.ZERO);
//					cargo.setProcesado(true);
//					paq.setQtyPendiente(cantPendiente);
//				}
//				
//				if(!paq.save(trxName))
//					error = true;
//				
//				if(!error && !cargo.save(trxName))
//					error = true;
//			}
//		} catch (Exception e) {
//			throw new Exception();//java.sql.SQLException: �ndice de columna no v�lido
//		} finally {
//			DB.close(rs, pstmt);
//			pstmt = null;
//			rs=null;
//		}
//		
//		if(error){
//			throw new Exception("error.paqute.noSave");
//		}
//	}
//	
//	/**
//	 c
//	 * @param ctx
//	 * @param EXME_CtaPac_ID
//	 * @param whereClause
//	 * @param trxName
//	 * @return
//	 */
//	public static void lineasSinPaq(Properties ctx, int EXME_PaqBase_Version_ID, 
//			int AD_Session_ID, String fecha, String trxName)throws Exception {
//		
//		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
//		sql.append(" SELECT * FROM EXME_T_PaqBaseDetCargo  ")
//		.append(" WHERE EXME_T_PaqBaseDetCargo.IsActive = 'Y' ")
//		.append(" AND EXME_T_PaqBaseDetCargo.Procesado = 'N' ")
//		.append(" AND ( EXME_T_PaqBaseDetCargo.QtyPendiente < 0  OR ")//Para definir cuando se ha excedido un cargo ( Paquete - cargo )
//		.append(" EXME_T_PaqBaseDetCargo.QtyPendiente = EXME_T_PaqBaseDetCargo.Cantidad ) "); //Cuando existen cargos que no fueron contemplado
//		
//		sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, sql.toString(), "EXME_T_PaqBaseDetCargo"));
//		
//		sql.append(" AND EXME_T_PaqBaseDetCargo.EXME_PaqBase_Version_ID = ? ")
//		.append(" AND EXME_T_PaqBaseDetCargo.AD_Session_ID = ? ")
//		.append(" ORDER BY EXME_T_PaqBaseDetCargo.Secuencia ASC ");
//		
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		boolean error = false;
//		try {
//			pstmt = DB.prepareStatement(sql.toString(), trxName);
//			pstmt.setInt(1, EXME_PaqBase_Version_ID);
//			pstmt.setInt(2, AD_Session_ID);
//			rs = pstmt.executeQuery();
//			if(rs.next())
//				error = true;
//			
//		} catch (Exception e) {
//			throw new Exception();
//		} finally {
//			DB.close(rs, pstmt);
//			pstmt = null;
//			rs=null;
//		}
//		
//		if(error){
//			throw new Exception("error.paqute.noSave");
//		}
//	}
//	
//	/**
//	 * Evaluamos las lineas que han quedado pendientes 
//	 * de relacionarse con un paquete
//	 * @param ctx
//	 * @param EXME_CtaPac_ID
//	 * @param whereClause
//	 * @param trxName
//	 * @return
//	 */
//	public static boolean borrarLineas(Properties ctx, int EXME_CtaPac_ID,  
//			boolean twoTables, String trxName) throws Exception {
//		////PIRUETA DELETE PIRUET 
//		 
//		//System.out.println("@Mandrake :: --> MEXMETPaqBaseDet.borrarLineas "+EXME_CtaPac_ID);
//		
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
////		int noReg = 0;
//		
//		synchronized (Banderas.valueMap){
//
//			/* gderreza
//			 * el proceso orginal para el cual fue usado valuemap es la sincronizacion de codigo,
//			 * el proceso inicia en MCtaPacDet.resetCargos() pero como aqui se entra sin haber pasado por
//			 * MCtaPacDet.resetCargos() entonces no se habia inicializado la variable y pues no se carga este
//			 * proceso.
//			 */
//			if(!Banderas.valueMap.containsKey(EXME_CtaPac_ID)){
//				Banderas.valueMap.put(EXME_CtaPac_ID,2); //fin cambios gderreza
//			}
//			if(Banderas.valueMap.containsKey(EXME_CtaPac_ID) && Banderas.valueMap.get(EXME_CtaPac_ID)<=3){
//				Banderas.valueMap.put(EXME_CtaPac_ID,Banderas.valueMap.get(EXME_CtaPac_ID)+1);		
//				
//				//System.out.println("@Mandrake :: --> MEXMETPaqBaseDet.borrarLineas IniTime :"+System.currentTimeMillis());
//				
//				try {
//					 StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
//					sql.append(" select EXME_T_PaqBaseDet_id FROM EXME_T_PaqBaseDet  ")
//					.append(" WHERE EXME_T_PaqBaseDet.AD_Session_ID = ?  ")
//					.append(" AND EXME_T_PaqBaseDet.EXME_CtaPac_ID = ? ");
//					
//					sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, sql.toString(), "EXME_T_PaqBaseDet"));
//					pstmt = DB.prepareStatement(sql.toString(), trxName);
//					pstmt.setInt(1, Env.getContextAsInt(ctx, "#AD_Session_ID"));
//					pstmt.setInt(2, EXME_CtaPac_ID);
//					
//					rs  = pstmt.executeQuery();
//					while (rs.next()){
//						new X_EXME_T_PaqBaseDet(ctx,rs.getInt(1),trxName).delete(true,trxName);
//					}
//					
////					s_log.fine("EXME_T_CtaPacCargos Num de Registros borrados = " + noReg);
//					DB.close(rs, pstmt);
//					
//					if(twoTables){
//						sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
//						sql.append(" select EXME_T_PaqBaseDetCargo_id FROM EXME_T_PaqBaseDetCargo  ")
//						.append(" WHERE EXME_T_PaqBaseDetCargo.AD_Session_ID = ?  ")
//						.append(" AND EXME_T_PaqBaseDetCargo.EXME_CtaPac_ID = ? ");
//						
//						sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, sql.toString(), "EXME_T_PaqBaseDetCargo"));
//						
//						pstmt = DB.prepareStatement(sql.toString(), null);
//						pstmt.setInt(1, Env.getContextAsInt(ctx, "#AD_Session_ID"));
//						pstmt.setInt(2, EXME_CtaPac_ID);
//						rs = pstmt.executeQuery();
//						
//						while (rs.next()){
//							
//							new X_EXME_T_PaqBaseDetCargo(ctx,rs.getInt(1),trxName).delete(true,trxName);
//						}
//					
////						s_log.fine("EXME_T_CtaPacCargos Num de Registros borrados = " + noReg);
//						
//					}
//				} catch (Exception e) {
//					e.printStackTrace();
//					throw new Exception();
//				} finally {
//					DB.close(rs, pstmt);
//					pstmt = null;
//					
//				}
//				
//				if (Banderas.valueMap.get(EXME_CtaPac_ID)==3)
//					Banderas.valueMap.remove(EXME_CtaPac_ID);
//				
//				ctx = null; 
//				trxName = null;
//				
////				return noReg >= 0;
//				return true;
//			
//			} else {
//				ctx = null; 
//				trxName = null;				
//				//System.out.println("@Mandrake :: --> MEXMETPaqBaseDetCargo.actualizaLineasCargo :: id "+EXME_CtaPac_ID);
//				throw new Exception("Excepcion, este mismo id ya est� siendo procesado, probablemente presion� el proceso mas de una vez");
//			}
//			
//		}
//		
//	}
//	
//	
//	
//	/**
//	 * Evaluamos las lineas que han quedado pendientes 
//	 * de relacionarse con un paquete
//	 * @param ctx
//	 * @param EXME_CtaPac_ID
//	 * @param whereClause
//	 * @param trxName
//	 * @return
//	 */
//	public static List<MEXMETPaqBaseDetCargo> getLines(Properties ctx, int EXME_PaqBase_Version_ID, 
//			int AD_Session_ID, String trxName)throws Exception {
//		
//		List<MEXMETPaqBaseDetCargo> lista = new ArrayList<MEXMETPaqBaseDetCargo>();
//		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
//		sql.append(" SELECT * FROM EXME_T_PaqBaseDetCargo  ")
//		.append(" WHERE EXME_T_PaqBaseDetCargo.IsActive = 'Y' ");
//		
//		sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, sql.toString(), "EXME_T_PaqBaseDetCargo"));
//		
//		sql.append(" AND EXME_T_PaqBaseDetCargo.EXME_PaqBase_Version_ID = ? ")
//		.append(" AND EXME_T_PaqBaseDetCargo.AD_Session_ID = ? ")
//		.append(" ORDER BY EXME_T_PaqBaseDetCargo.Secuencia ASC ");
//		
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		try {
//			pstmt = DB.prepareStatement(sql.toString(), trxName);
//			pstmt.setInt(1, EXME_PaqBase_Version_ID);
//			pstmt.setInt(2, AD_Session_ID);
//			rs = pstmt.executeQuery();
//			while(rs.next()){
//				MEXMETPaqBaseDetCargo cargo = new MEXMETPaqBaseDetCargo(ctx, rs, trxName);
//				lista.add(cargo);
//			}
//		} catch (Exception e) {
//			throw new Exception();
//		} finally {
//			DB.close(rs, pstmt);
//			pstmt = null;
//			rs=null;
//		}
//		
//		return lista;
//	}
//	
//	/**
//	 * @param ctx
//	 * @param EXME_CtaPac_ID
//	 * @param whereClause
//	 * @param trxName
//	 * @return
//	 */
//	public static MEXMETPaqBaseDet[] getLnsPendientes(Properties ctx, int EXME_CtaPac_ID, 
//			int AD_Session_ID, BigDecimal secuencia, String trxName)throws Exception {
//		
//		List<MEXMETPaqBaseDet> lista = new ArrayList<MEXMETPaqBaseDet>();
//		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
//		sql.append(" SELECT EXME_T_PaqBaseDet.* ")
//		.append(" FROM EXME_T_PaqBaseDet  ")
//		.append(" WHERE EXME_T_PaqBaseDet.IsActive = 'Y' ")
//		.append(" AND EXME_T_PaqBaseDet.QtyPendiente > 0 ")
//		.append(" AND EXME_T_PaqBaseDet.AD_Session_ID = ? ")
//		.append(" AND EXME_T_PaqBaseDet.EXME_CtaPac_ID = ? ");
//		
//		sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, sql.toString(), "EXME_T_PaqBaseDet"));
//		
//		sql.append(" ORDER BY EXME_T_PaqBaseDet.M_PRODUCT_ID ");
//		
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		try {
//			pstmt = DB.prepareStatement(sql.toString(), trxName);
//			pstmt.setInt(1, AD_Session_ID);
//			pstmt.setInt(2, EXME_CtaPac_ID);
//			rs = pstmt.executeQuery();
//			while(rs.next()){
//				MEXMETPaqBaseDet paq = new MEXMETPaqBaseDet(ctx, rs, null);
//				lista.add(paq);
//			}
//			
//		} catch (Exception e) {
//			throw new Exception();
//		}finally{
//			DB.close(rs, pstmt);
//  			pstmt = null;
//  			rs = null;
//  		}
//		
//		MEXMETPaqBaseDet[] lineas = new MEXMETPaqBaseDet[lista.size()];
//		lista.toArray(lineas);
//		return lineas;
//	}
//	
//	/**
//	 * Ahora el proceso permitira que si el producto existe 
//	 * duplicado pero con otra unidad de medida se agrupen y se guarden nuevamente
//	 * pero la unidad de medida sera la de almacenamiento
//	 * @param ctx
//	 * @param EXME_CtaPac_ID
//	 * @param AD_Session_ID
//	 * @param secuencia
//	 * @param trxName
//	 */
///*	public static void recalcularLineas(Properties ctx, int EXME_CtaPac_ID, 
//			int AD_Session_ID, BigDecimal secuencia, String trxName)
//	throws Exception {
//		
//		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
//		sql.append(" SELECT EXME_T_PaqBaseDet.M_Product_ID, p.C_UOM_ID, ")
//		.append(" NVL( SUM (EXME_T_PaqBaseDet.cantidad ),0) AS Cantidad ")
//		.append(" FROM EXME_T_PaqBaseDet ")
//		.append(" INNER JOIN M_Product p ON ( p.M_Product_ID = EXME_T_PaqBaseDet.M_Product_ID )")
//		.append(" WHERE EXME_T_PaqBaseDet.IsActive = 'Y' ")
//		.append(" AND EXME_T_PaqBaseDet.AD_Session_ID = ? ")
//		.append(" AND EXME_T_PaqBaseDet.EXME_CtaPac_ID = ? ");
//		
//		sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, sql.toString(), "EXME_T_PaqBaseDet"));
//		sql.append(" GROUP BY EXME_T_PaqBaseDet.M_Product_ID, p.C_UOM_ID "); 
//		
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		try {
//			pstmt = DB.prepareStatement(sql.toString(), trxName);
//			pstmt.setInt(1, AD_Session_ID);
//			pstmt.setInt(2, EXME_CtaPac_ID);
//			rs = pstmt.executeQuery();
//			borrarLineas(ctx, EXME_CtaPac_ID, false, trxName);
//			while (rs.next()) {
//				MEXMETPaqBaseDet paqBaseDet = new MEXMETPaqBaseDet(ctx, 0, trxName);
//				paqBaseDet.setM_Product_ID(rs.getInt("M_Product_ID"));
//				paqBaseDet.setC_UOM_ID(rs.getInt("C_UOM_ID"));
//				paqBaseDet.setAD_Session_ID(AD_Session_ID);
//				paqBaseDet.setSecuencia(secuencia);
//				paqBaseDet.setCantidad(rs.getBigDecimal("Cantidad"));
//				paqBaseDet.setEXME_CtaPac_ID(EXME_CtaPac_ID);
//				paqBaseDet.setQtyPendiente(rs.getBigDecimal("Cantidad"));
//				if(!paqBaseDet.save(trxName)){
//					//System.out.println("No jala el save");
//					throw new Exception("error.paqute.noSave");
//				}
//				
//			}
//			
//	 	} catch (Exception e) {
//    		s_log.log(Level.SEVERE, sql.toString(), e.getMessage());
//    		throw new Exception("error.paqute.noSave");
//  		}finally{
//  			DB.close(rs, pstmt);
//  			pstmt = null;
//  			rs = null;
//  		}
//
//	}
//    */
//
//    /**
//     * Regresa las lineas para una ctapac y un paquete
//     * @param ctx
//     * @param EXME_PaqBase_Version_ID
//	 * @param EXME_CtaPac_ID
//     * @param AD_Session_ID
//     * @param trxName
//     * @return
//     */
//    public static List<MEXMETPaqBaseDet> getLinesDet(Properties ctx, int EXME_PaqBase_Version_ID,
//			int EXME_CtaPac_ID, int AD_Session_ID, String trxName)
//			throws Exception {
//        
//        List<MEXMETPaqBaseDet> lista = new ArrayList<MEXMETPaqBaseDet>();
//        StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
//        sql.append("SELECT * FROM EXME_T_PaqBaseDet ")
//        .append("WHERE EXME_T_PaqBaseDet.IsActive = 'Y' ")
//        .append("AND EXME_T_PaqBaseDet.EXME_PaqBase_Version_ID = ? ")
//        .append("AND EXME_T_PaqBaseDet.AD_Session_ID = ? ")
//        .append("AND EXME_T_PaqBaseDet.EXME_CtaPac_ID = ? ");                    
//
//        sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, sql.toString(),
//                "EXME_T_PaqBaseDet")).append(" ORDER BY EXME_T_PaqBaseDet.Secuencia ASC ");
//        
//        PreparedStatement pstmt = null;
//        ResultSet rs = null;
//        try {
//            pstmt = DB.prepareStatement(sql.toString(), trxName);
//            pstmt.setInt(1, EXME_PaqBase_Version_ID);
//            pstmt.setInt(2, AD_Session_ID);
//            pstmt.setInt(3, EXME_CtaPac_ID);
//            
//            rs = pstmt.executeQuery();
//            while(rs.next()){
//                MEXMETPaqBaseDet detalle = new MEXMETPaqBaseDet(ctx, rs, trxName);
//                lista.add(detalle);
//            }
//            
//        } catch (Exception e) {
//        	s_log.log(Level.SEVERE, "getLinesDet", e);
//			throw new Exception();
//        } finally {
//        	DB.close(rs, pstmt);
//            pstmt = null;
//            rs=null;
//            sql = null;
//        }
//        
//        return lista;
//	}
//
//    
//    private boolean isChecked = false;
//    public boolean isChecked() {
//        return isChecked;
//    }
//    public void setChecked(boolean isChecked) {
//        this.isChecked = isChecked;
//	}

}
