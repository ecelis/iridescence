package org.compiere.model;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.Env;

public class MEXMETPaqBaseDetCargo 
{
//extends X_EXME_T_PaqBaseDetCargo{
//	
//	/**
//	 * 
//	 */
//	//public static HashMap<Integer,Integer> valueMap = new HashMap<Integer,Integer>();
//	
//	private static final long serialVersionUID = 1L;
//	/** static logger   */
//	private static CLogger s_log = CLogger.getCLogger(MEXMETPaqBaseDetCargo.class);
//	
//	public MEXMETPaqBaseDetCargo(Properties ctx, int EXME_T_PaqBaseDetCargo_ID, String trxName) {
//		super(ctx, EXME_T_PaqBaseDetCargo_ID, trxName);
//	}
//
//	public MEXMETPaqBaseDetCargo(Properties ctx, ResultSet rs, String trxName) {
//		super(ctx, rs, trxName);
//	}
//	
//	public static MEXMETPaqBaseDetCargo get(Properties ctx, MPaqBaseDet paqBaseOrig, String trxName) {
//		MEXMETPaqBaseDetCargo paqBase = new MEXMETPaqBaseDetCargo(ctx, 0, trxName);
//		paqBase.setC_UOM_ID(paqBaseOrig.getC_UOM_ID());
//		paqBase.setCantidad(paqBaseOrig.getCantidad());
//		paqBase.setEXME_PaqBase_Version_ID(paqBaseOrig.getEXME_PaqBase_Version_ID());
//		paqBase.setM_Product_ID(paqBaseOrig.getM_Product_ID());
//		paqBase.setAD_Session_ID(Env.getContextAsInt(ctx, "#AD_Session_ID"));
//		paqBase.setSecuencia(paqBaseOrig.getSecuencia());
//		paqBase.setPriceActual(paqBaseOrig.getPriceActual());
//		paqBase.setPriceLimit(paqBaseOrig.getPriceList());
//		paqBase.setPriceList(paqBaseOrig.getPriceLimit());
//		paqBase.setProcesado(false);
//		paqBase.setC_Currency_ID(paqBaseOrig.getC_Currency_ID());
//		paqBase.setC_Tax_ID(paqBaseOrig.getC_Tax_ID());
//		paqBase.setLineNetAmt(paqBaseOrig.getLineNetAmt());
//		paqBase.setLineTotalAmt(paqBaseOrig.getLineTotalAmt());
//		paqBase.setTaxAmt(paqBaseOrig.getTaxAmt());
//		paqBase.setCosto(paqBaseOrig.getCosto());
//		paqBase.setCantidadAlm(paqBaseOrig.getCantidadAlm());
//		paqBase.setQtyPendiente(paqBaseOrig.getQtyPendiente());
//		paqBase.setLine(paqBaseOrig.getSecuencia().intValue());
//		return paqBase;
//	}
//	   /**
//     * 
//     * @param ctx
//     * @param cpd
//     * @param areaId
//     * @param ctapac
//     * @param ctaPacExtId
//     * @param almacenId
//     * @param paqueteId
//     * @param secuencia
//     * @param trxName
//     * @return
//     */
//    public static MEXMETPaqBaseDetCargo copyCargoPaqBaseDet(Properties ctx, MCtaPacDet cpd, 
//    		int AD_Session_ID, BigDecimal secuencia, int line, String trxName){
//    	
//    	//Creamos el cargo que utilizaremos para relacionar con la cuenta paciente
//    	MEXMETPaqBaseDetCargo linePack = new MEXMETPaqBaseDetCargo(ctx, 0 , trxName);
//    	
//    	//llenamos las columnas del cargos a la cuenta paciente
//    	linePack.setLine(line);
//    	linePack.setSecuencia(secuencia);
//		linePack.setM_Product_ID(cpd.getM_Product_ID());
//		linePack.setC_UOM_ID(cpd.getC_UOM_ID());
//		linePack.setProcesado(false);
//		linePack.setCantidad(cpd.getQtyPendiente());//Sin devoluciones
//		linePack.setQtyPendiente(cpd.getQtyPendiente());//
//		linePack.setCantidadAlm(cpd.getFreightAmt());//Cantidad en udm de almacenamiento
//		
//		linePack.setAD_Session_ID(AD_Session_ID);
//		linePack.setEXME_CtaPac_ID(cpd.getEXME_CtaPac_ID());
//		linePack.setEXME_CtaPacDet_ID(cpd.getEXME_CtaPacDet_ID());
//        
//		//copiamos los costos, y precio (.-Lama)
//        linePack.setCosto(cpd.getCosto());
//        linePack.setPriceActual(cpd.getPriceActual());
//		    	
//        //contener el convenio que se aplica
//        //linePack.setEXME_EsqDesLine_ID(cpd.getEXME_EsqDesLine_ID());
//    	return linePack;
//    
//    }
//	   /**
//     * 
//     * @param ctx
//     * @param cpd
//     * @param areaId
//     * @param ctapac
//     * @param ctaPacExtId
//     * @param almacenId
//     * @param paqueteId
//     * @param secuencia
//     * @param trxName
//     * @return
//     */
//    public static MEXMETPaqBaseDetCargo copyCargoPaqBaseDetRep(Properties ctx, MCtaPacDet cpd, 
//    		int AD_Session_ID, BigDecimal secuencia, int line, String trxName){
//    	
//    	//Creamos el cargo que utilizaremos para relacionar con la cuenta paciente
//    	MEXMETPaqBaseDetCargo linePack = new MEXMETPaqBaseDetCargo(ctx, 0 , trxName);
//    	
//    	//llenamos las columnas del cargos a la cuenta paciente
//    	linePack.setLine(line);
//    	linePack.setSecuencia(secuencia);
//		linePack.setM_Product_ID(cpd.getM_Product_ID());
//		linePack.setC_UOM_ID(cpd.getC_UOM_ID());
//		linePack.setProcesado(false);
//		linePack.setCantidad(cpd.getQtyDelivered());
//		//linePack.setCantidadAlm(cpd.getQtyEntered());//Cantidad en udm de almacenamiento
//		//linePack.setQtyPendiente(cpd.getQtyPendiente());//Cantidad en udm de almacenamiento
//		linePack.setQtyPendiente(cpd.getQtyEntered());//Cantidad en udm de almacenamiento
//		
//		linePack.setAD_Session_ID(AD_Session_ID);
//		linePack.setEXME_CtaPac_ID(cpd.getEXME_CtaPac_ID());
//		linePack.setEXME_CtaPacDet_ID(cpd.getEXME_CtaPacDet_ID());
//        
//		linePack.setCantidadAlm(cpd.getQtyPendiente());//Cantidad en udm de almacenamiento
//        //copiamos los costos, y precio (.-Lama)
//        linePack.setCosto(cpd.getCosto());
//        linePack.setPriceActual(cpd.getPriceActual());
//		    	
//        //contener el convenio que se aplica
//        //linePack.setEXME_EsqDesLine_ID(cpd.getEXME_EsqDesLine_ID());
//    	return linePack;
//    
//    }
//	private MProduct m_product = null;
//	
//    public MProduct getProduct(){
//    	if(m_product == null || getM_Product_ID() == 0)
//    		m_product = new MProduct(getCtx(), getM_Product_ID(), get_TrxName()); 
//        return m_product;
//    }
//
//    public void setProduct(int M_Product_ID){
//    	if(M_Product_ID > 0)
//    		m_product = new MProduct(getCtx(), M_Product_ID, get_TrxName()); 
//    }
//    
//	private MUOM m_uom = null;
//	
//    public MUOM getUom() {
//    	
//    	if(m_uom==null || getC_UOM_ID() == 0)
//    		m_uom = new MUOM(getCtx(), getC_UOM_ID(), get_TrxName());
//    	
//        return new MUOM(getCtx(), getC_UOM_ID(), null);
//	}
//    
//	/**
//	 * Permite traer todas las lineas en la tabla temporal con respecto a la
//	 * ctapaciente, para realizar los calculos de macheo
//	 * @param ctx
//	 * @param EXME_CtaPac_ID
//	 * @param trxName
//	 * @return
//	 * 
//	 */
//	public static MEXMETPaqBaseDetCargo[] getLineas(Properties ctx, int EXME_CtaPac_ID,
//			int M_Product_ID, String trxName) {
//
//		List<MEXMETPaqBaseDetCargo> list = new ArrayList<MEXMETPaqBaseDetCargo>();
//		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
//		sql.append(" SELECT EXME_T_PaqBaseDetCargo.*  ") 
//			.append(" FROM EXME_T_PaqBaseDetCargo    ")
//			.append(" WHERE EXME_T_PaqBaseDetCargo.IsActive = 'Y'  ") 
//			.append(" AND EXME_T_PaqBaseDetCargo.EXME_CtaPac_ID = ?   ")
//			.append(" AND EXME_T_PaqBaseDetCargo.AD_Session_ID = ?  ")
//			.append(" AND EXME_T_PaqBaseDetCargo.M_Product_ID = ? ");
//														
//		sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, sql.toString(), "EXME_T_PaqBaseDetCargo"));
//		
//		sql.append(" ORDER BY EXME_T_PaqBaseDetCargo.EXME_T_PaqBaseDetCargo_ID ASC ");
//		
//		s_log.log(Level.SEVERE,"SQL> " + sql + " ID > " + EXME_CtaPac_ID);
//
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		try {
//
//			pstmt = DB.prepareStatement(sql.toString(), trxName);
//			pstmt.setInt(1, EXME_CtaPac_ID);
//			pstmt.setInt(2, Env.getContextAsInt(ctx, "#AD_Session_ID"));
//			pstmt.setInt(3, M_Product_ID);
//			
//			rs = pstmt.executeQuery();
//
//			while (rs.next()) {
//				MEXMETPaqBaseDetCargo refValue = new MEXMETPaqBaseDetCargo(ctx, rs, null);
//				list.add(refValue); // lista sin lineas de devolucion
//			}
//
//		} catch (SQLException e) {
//			s_log.log(Level.SEVERE, "getLineas SQLException ", e);
//		} finally {
//			DB.close(rs, pstmt);
//  			pstmt = null;
//  			rs = null;
//  		}
//		MEXMETPaqBaseDetCargo[] lineas = new MEXMETPaqBaseDetCargo[list.size()];
//		list.toArray(lineas);
//		return lineas;
//	}
//	
//	/**
//	 * @author Luisandro de la Cruz
//	 * 
//	 * Permite traer todas las lineas en la tabla temporal con respecto a la
//	 * ctapaciente, para realizar los calculos de macheo
//	 * @param ctx
//	 * @param EXME_CtaPac_ID
//	 * @param trxName
//	 * @return
//	 * 
//	 */
//	public static MEXMETPaqBaseDetCargo[] getAllLineas(Properties ctx, int EXME_CtaPac_ID,
//			StringBuffer varsProductId, String trxName) {
//
//		List<MEXMETPaqBaseDetCargo> list = new ArrayList<MEXMETPaqBaseDetCargo>();
//		
//		if (varsProductId!=null && varsProductId.length()>0){ 
//			StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
//			sql.append(" SELECT EXME_T_PaqBaseDetCargo.*  ") 
//				.append(" FROM EXME_T_PaqBaseDetCargo    ")
//				.append(" WHERE EXME_T_PaqBaseDetCargo.IsActive = 'Y'  ") 
//				.append(" AND EXME_T_PaqBaseDetCargo.EXME_CtaPac_ID = ?   ")
//				.append(" AND EXME_T_PaqBaseDetCargo.AD_Session_ID = ?  ")
//				.append(" AND EXME_T_PaqBaseDetCargo.M_Product_ID in ( ")
//				.append(varsProductId.substring(0,varsProductId.length()-1))
//				.append(" ) ");
//															
//			sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, sql.toString(), 
//					"EXME_T_PaqBaseDetCargo"));
//			
//			sql.append(" ORDER BY EXME_T_PaqBaseDetCargo.M_Product_ID, ")
//			.append(" EXME_T_PaqBaseDetCargo.EXME_T_PaqBaseDetCargo_ID ASC ");
//			
//			s_log.log(Level.SEVERE,"SQL> " + sql + " ID > " + EXME_CtaPac_ID);
//	
//			PreparedStatement pstmt = null;
//			ResultSet rs = null;
//			try {
//	
//				pstmt = DB.prepareStatement(sql.toString(), trxName);
//				pstmt.setInt(1, EXME_CtaPac_ID);
//				pstmt.setInt(2, Env.getContextAsInt(ctx, "#AD_Session_ID"));
//				//pstmt.setInt(3, M_Product_ID);
//				
//				rs = pstmt.executeQuery();
//	
//				while (rs.next()) {
//					MEXMETPaqBaseDetCargo refValue = new MEXMETPaqBaseDetCargo(ctx, rs, null);
//					list.add(refValue); // lista sin lineas de devolucion
//				}
//	
//			} catch (SQLException e) {
//				s_log.log(Level.SEVERE, "getAllLineas SQLException ", e);
//			} finally {
//				DB.close(rs, pstmt);
//	  			pstmt = null;
//	  			rs = null;
//	  		}
//		}
//		
//		MEXMETPaqBaseDetCargo[] lineas = new MEXMETPaqBaseDetCargo[list.size()];
//		list.toArray(lineas);
//		return lineas;
//	}
//	
//	
//	/**
//	 * Obtengo los sustitutos de un producto que esten en la tabla de trabajo
//	 * de cargos - paquetes
//	 * @param ctx
//	 * @param EXME_CtaPac_ID
//	 * @param AD_Session_ID
//	 * @param M_Product_ID
//	 * @param trxName
//	 * @return
//	 */
//	public static MEXMETPaqBaseDetCargo[] getSustitutos(Properties ctx, int EXME_CtaPac_ID,
//			int AD_Session_ID, int M_Product_ID, String fecha, String trxName) {
//
//		ArrayList<MEXMETPaqBaseDetCargo> list = new ArrayList<MEXMETPaqBaseDetCargo>();
//		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
//		sql.append(" SELECT pbdc.* FROM M_Substitute ")
//			.append(" INNER JOIN EXME_T_PaqBaseDetCargo pbdc ")
//			.append(" ON ( pbdc.M_Product_ID = M_Substitute.Substitute_ID AND pbdc.IsActive = 'Y') ")
//			.append(" WHERE M_Substitute.IsActive = 'Y' ")
//			.append(" AND M_Substitute.M_Product_ID = ? ")
//			.append(" AND pbdc.EXME_CtaPac_ID = ? ")
//			.append(" AND pbdc.AD_Session_ID = ? ")
//			.append(" AND pbdc.QtyPendiente > 0 ")
//			//.append(" AND M_Substitute.ValidFrom <= ? AND M_Substitute.ValidTo >= ? .append(" ;
//		    .append(" AND M_Substitute.ValidFrom <= to_date(?,'dd/mm/yyyy') AND M_Substitute.ValidTo >= to_date(?,'dd/mm/yyyy')  ");											
//				
//		sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, sql.toString(), "M_Substitute"));
//		sql.append(" ORDER BY pbdc.EXME_T_PaqBaseDetCargo_ID ASC");
//		s_log.log(Level.FINEST,"SQL> " + sql + " ID > " + EXME_CtaPac_ID);
//
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		try {
//
//			pstmt = DB.prepareStatement(sql.toString(), trxName);
//			pstmt.setInt(1, M_Product_ID);
//			pstmt.setInt(2, EXME_CtaPac_ID);
//			pstmt.setInt(3, AD_Session_ID);
//			pstmt.setString(4, fecha);
//			pstmt.setString(5, fecha);
//			rs = pstmt.executeQuery();
//
//			while (rs.next()) {
//				MEXMETPaqBaseDetCargo refValue = new MEXMETPaqBaseDetCargo(ctx, rs, null);
//				list.add(refValue); // lista sin lineas de devolucion
//			}
//
//		} catch (Exception e) {
//			s_log.log(Level.SEVERE, "getLineas ", e);
//		}finally{
//  			DB.close(rs, pstmt);
//  			pstmt = null;
//  			rs = null;
//  		}
//		MEXMETPaqBaseDetCargo[] lineas = new MEXMETPaqBaseDetCargo[list.size()];
//		list.toArray(lineas);
//		return lineas;
//	}
//	
//	/**
//	 * @author ldelacruz
//	 * 
//	 * Obtengo todos los sustitutos de un producto que esten en la tabla de trabajo
//	 * de cargos - paquetes
//	 * @param ctx
//	 * @param EXME_CtaPac_ID
//	 * @param AD_Session_ID
//	 * @param M_Product_ID
//	 * @param trxName
//	 * @return
//	 */
//	public static MEXMETPaqBaseDetCargo[] getAllSustitutos(Properties ctx, int EXME_CtaPac_ID,
//			int AD_Session_ID, StringBuffer varsProductId, String fecha, String trxName) {
//
//		ArrayList<MEXMETPaqBaseDetCargo> list = new ArrayList<MEXMETPaqBaseDetCargo>();
//		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
//		sql.append(" SELECT pbdc.* FROM M_Substitute ")
//			.append(" INNER JOIN EXME_T_PaqBaseDetCargo pbdc ")
//			.append(" ON ( pbdc.M_Product_ID = M_Substitute.Substitute_ID AND pbdc.IsActive = 'Y') ")
//			.append(" WHERE M_Substitute.IsActive = 'Y' ")
//			.append(" AND M_Substitute.M_Product_ID in ( ")
//			.append(varsProductId.substring(0,varsProductId.length()-1))
//			.append(" ) ")
//			.append(" AND pbdc.EXME_CtaPac_ID = ? ")
//			.append(" AND pbdc.AD_Session_ID = ? ")
//			.append(" AND pbdc.QtyPendiente > 0 ")
//		    .append(" AND M_Substitute.ValidFrom <= to_date(?,'dd/mm/yyyy') ")
//		    .append(" AND M_Substitute.ValidTo >= to_date(?,'dd/mm/yyyy') ");											
//				
//		sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, sql.toString(), "M_Substitute"));
//		sql.append(" ORDER BY M_Substitute.M_Product_ID, pbdc.EXME_T_PaqBaseDetCargo_ID ASC");
//		s_log.log(Level.FINEST,"SQL> " + sql + " ID > " + EXME_CtaPac_ID);
//
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		try {
//
//			pstmt = DB.prepareStatement(sql.toString(), trxName);
//			pstmt.setInt(1, EXME_CtaPac_ID);
//			pstmt.setInt(2, AD_Session_ID);
//			pstmt.setString(3, fecha);
//			pstmt.setString(4, fecha);
//			rs = pstmt.executeQuery();
//
//			while (rs.next()) {
//				MEXMETPaqBaseDetCargo refValue = new MEXMETPaqBaseDetCargo(ctx, rs, null);
//				list.add(refValue); // lista sin lineas de devolucion
//			}
//
//		} catch (Exception e) {
//			s_log.log(Level.SEVERE, "getLineas ", e);
//		}finally{
//			DB.close(rs, pstmt);
//  			pstmt = null;
//  			rs = null;
//  		}
//		MEXMETPaqBaseDetCargo[] lineas = new MEXMETPaqBaseDetCargo[list.size()];
//		list.toArray(lineas);
//		return lineas;
//	}	
//
//	/**
//	 * Lineas que despues del proceso de macheo quedan como extras
//	 * @param ctx
//	 * @param EXME_CtaPac_ID
//	 * @param AD_Session_ID
//	 * @param secuencia
//	 * @param trxName
//	 * @return
//	 */
//	public static boolean actualizaLineasCargo(Properties ctx, int EXME_CtaPac_ID,  
//			BigDecimal secuencia, String tabla, 
//			String trxName) throws Exception {
//		
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
////		int noReg=-1;
//		//PIRUETA UPDATE
//		 
//		//System.out.println("@Mandrake :: Start --> MEXMETPaqBaseDetCargo.actualizaLineasCargo "+EXME_CtaPac_ID); 
//
//		synchronized (Banderas.valueMap){
//			
//			if(Banderas.valueMap.containsKey(EXME_CtaPac_ID) && Banderas.valueMap.get(EXME_CtaPac_ID)<=3){
//				Banderas.valueMap.put(EXME_CtaPac_ID,Banderas.valueMap.get(EXME_CtaPac_ID)+1);		
//			
//				//System.out.println("@Mandrake :: --> MEXMETPaqBaseDetCargo.actualizaLineasCargo IniTime :"+System.currentTimeMillis());
//			
//				try {
//					//La columna QtyPaquete = Es la cantidad que queda de QtyPendiente menos lo que entro en el paquete 
//					 StringBuilder sql = new StringBuilder(1000);
//					
//					sql.append(" SELECT t.exme_ctapacdet_id, ")
//					.append(" t.QtyPaquete + nvl(tt.Cantidad - tt.QtyPendiente, 0) as QtyPaquete, ") 
//					.append(" nvl(tt.QtyPendiente, 0) as QtyPendiente, ")
//					.append(" nvl(tt.CantidadAlm, 0) as FreightAmt ")
//					.append(" FROM ").append(tabla).append(" t, EXME_T_PaqBaseDetCargo tt ")
//					.append(" WHERE t.EXME_CtaPacDet_ID = tt.EXME_CtaPacDet_ID ") 
//					.append(" AND tt.EXME_CtaPac_ID = ? ") 
//					.append(" AND tt.AD_Session_ID = ? ");
//					
//					sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, 
//							sql.toString(), tabla, "t"));
//				
//					if (tabla.equalsIgnoreCase("EXME_T_CtaPacCargos")) {
//						sql.append(" AND t.AD_User_ID = ? AND t.AD_Session_ID = ? ");
//					}
//					
//		
//		
//					pstmt = DB.prepareStatement(sql.toString(), trxName);
//					pstmt.setInt(1, EXME_CtaPac_ID);
//					pstmt.setInt(2, Env.getContextAsInt(ctx, "#AD_Session_ID"));
//					
//					if(tabla.equalsIgnoreCase("EXME_T_CtaPacCargos")){
//						pstmt.setInt(3, Env.getContextAsInt(ctx, "#AD_User_ID"));
//						pstmt.setInt(4, Env.getContextAsInt(ctx, "#AD_Session_ID"));
//					}
//					
//					rs = pstmt.executeQuery();
//					
//					MCtaPacDet ctaPacDet = null;
//					MEXMETCtaPacCargos ctaPacCargos = null;
//					
//					
//					
//					while (rs.next()) {
//						if(tabla.equalsIgnoreCase("EXME_T_CtaPacCargos")) {
//							ctaPacCargos = new MEXMETCtaPacCargos(ctx, rs.getInt("exme_ctapacdet_id"), trxName);
//							ctaPacCargos.setQtyPaquete(rs.getBigDecimal("qtypaquete"));
//							ctaPacCargos.setQtyPendiente(rs.getBigDecimal("qtypendiente"));
//							ctaPacCargos.setFreightAmt(rs.getBigDecimal("freightamt"));
//							ctaPacCargos.save();
//						} else {
//							ctaPacDet = new MCtaPacDet(ctx, rs.getInt("exme_ctapacdet_id"), trxName);
//							ctaPacDet.setQtyPaquete(rs.getBigDecimal("qtypaquete"));
//							ctaPacDet.setQtyPendiente(rs.getBigDecimal("qtypendiente"));
//							ctaPacDet.setFreightAmt(rs.getBigDecimal("freightamt"));
//							ctaPacDet.save();
//						}
////						noReg++;
//					}
//					
//					//System.out.println("@Mandrake :: End --> MEXMETPaqBaseDetCargo.actualizaLineasCargo :: id "+EXME_CtaPac_ID);
//					
//				} catch (Exception e) {
//					e.printStackTrace();
//					throw new Exception();
//				} finally {
//					DB.close(rs, pstmt);
//				}
//				
//				if (Banderas.valueMap.get(EXME_CtaPac_ID)==3)
//					Banderas.valueMap.remove(EXME_CtaPac_ID);
////				return noReg >=0;
//				ctx = null; 
//				trxName = null;
//				
//				return true;
//			} else {					
//				ctx = null; 
//				trxName = null;				
//				//System.out.println("@Mandrake :: --> MEXMETPaqBaseDetCargo.actualizaLineasCargo :: id "+EXME_CtaPac_ID);
//				throw new Exception("Excepcion, este mismo id ya est� siendo procesado, probablemente presion� el proceso mas de una vez");
//			}
//		}
//		
//	}
//
//    /**
//     * Regresa las lineas para una ctapac
//     * @param ctx
//     * @param EXME_CtaPac_ID
//     * @param AD_Session_ID
//     * @param trxName
//     * @return
//     */
//    public static List<MEXMETPaqBaseDetCargo> getLinesDetCargo(Properties ctx, int EXME_CtaPac_ID,
//            int AD_Session_ID, String trxName) throws Exception {
//        
//        List<MEXMETPaqBaseDetCargo> lista = new ArrayList<MEXMETPaqBaseDetCargo>();
//        StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
//        sql.append("SELECT * FROM EXME_T_PaqBaseDetCargo ")
//            .append("WHERE IsActive = 'Y' AND AD_Session_ID = ? AND EXME_CtaPac_ID = ? ") ;
//                     
//        sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, sql.toString(),
//                "EXME_T_PaqBaseDetCargo")).append(" ORDER BY M_Product_ID ASC");
//
//        PreparedStatement pstmt = null;
//        ResultSet rs = null;
//        try {
//            pstmt = DB.prepareStatement(sql.toString(), trxName);
//            pstmt.setInt(1, AD_Session_ID);
//            pstmt.setInt(2, EXME_CtaPac_ID);
//            
//            rs = pstmt.executeQuery();
//            while(rs.next()){
//                MEXMETPaqBaseDetCargo detalle = new MEXMETPaqBaseDetCargo(ctx, rs, trxName);
//                lista.add(detalle);
//            }
//            
//        } catch (Exception e) {
//        	s_log.log(Level.SEVERE, "getLinesDetCargo", e);
//        	
//            throw new Exception();
//        } finally {
//        	DB.close(rs, pstmt);
//            pstmt = null;
//            rs=null;
//            sql = null;
//        }
//        
//        return lista;
//    }

}
