/*
 * Created on 04-abr-2005
 *
 */
package org.compiere.model;


/**
 * @author hassan reyes
 * @deprecated
 */
public class MPaqBaseDet {}
//extends X_EXME_PaqBaseDet {
//    
//    /**
//	 * 
//	 */
//	private static final long serialVersionUID = -423809221733378015L;
//	private int scale = MCurrency.getStdPrecision(getCtx(), Env.getContextAsInt(getCtx(), "$C_Currency_ID")); // decimas.
//    /**
//     * Variables Miembro.
//     */
//  
//    private MProduct m_product = null;
//
//    /**
//     * @param ctx
//     * @param EXME_PaqBaseDet_ID
//     * @param trxName
//     */
//    public MPaqBaseDet(Properties ctx, int EXME_PaqBaseDet_ID, String trxName) {
//        super(ctx, EXME_PaqBaseDet_ID, trxName);
//    }
//
//    /**
//     * @param ctx
//     * @param rs
//     * @param trxName
//     */
//    public MPaqBaseDet(Properties ctx, ResultSet rs, String trxName) {
//        super(ctx, rs, trxName);
//    }
//    
//          
//    public MProduct getProduct(){
//        
//        if(m_product == null || m_product.getM_Product_ID() == 0){
//            m_product = new MProduct(getCtx(), getM_Product_ID(), get_TrxName());
//        }
//        
//        return m_product;
//    }
//    
//    public MUOM getUom() {
//        return new MUOM(getCtx(), getC_UOM_ID(), null);
//	}
//
//	public MEXMECurrency getCurrency() {
//		return new MEXMECurrency(getCtx(), getC_Currency_ID(), null);
//	}
//	
//	
//	//en unidad de medida de almacenamiento
//	private BigDecimal qtyPendiente = Env.ZERO;
//	
//    public BigDecimal getQtyPendiente() {
//        return qtyPendiente;
//    }
//    public void setQtyPendiente(BigDecimal qtyPendiente) {
//        this.qtyPendiente = qtyPendiente;
//    }
//    
//	
////	private String messageError = null;
////
////	public String getMessageError() {
////		return messageError;
////	}
////
////	public void setMessageError(String messageError) {
////		this.messageError = messageError;
////	}
//	
//    /**
//     * Obtenemos la Version especifica del paquete que fue reservada a la cuenta paciente.
//     * @return
//     */
//    public MTax getTax() {
//
//        if(getC_Tax_ID() > 0)
//            return new MTax(getCtx(), getC_Tax_ID(), get_TrxName());
//        else
//            return null;
//
//    }
//    
//    
//    //Red = Redondeo
//    public BigDecimal getPriceLimitRed(){
//        return getPriceLimit().setScale(scale, BigDecimal.ROUND_HALF_UP);
//    }
//    
//    public BigDecimal getPriceActualRed(){
//        return getPriceActual().setScale(scale, BigDecimal.ROUND_HALF_UP);
//    }
//    
//    public BigDecimal getPriceListRed(){
//        return getPriceList().setScale(scale, BigDecimal.ROUND_HALF_UP);
//    }
//    
//    public BigDecimal getLineNetAmtRed(){
//        return getLineNetAmt().setScale(scale, BigDecimal.ROUND_HALF_UP);
//    }
//    
//    public BigDecimal getTaxAmtRed(){
//        return getTaxAmt().setScale(scale, BigDecimal.ROUND_HALF_UP);
//    }
//    
//    public BigDecimal getLineTotalAmtRed(){
//        return getLineTotalAmt().setScale(scale, BigDecimal.ROUND_HALF_UP);
//    }
//    
////    public BigDecimal getCostStandardRed(){
////    	MEXMECost mcost = MEXMECost.get(getCtx(), getProduct(),  X_M_CostElement.COSTINGMETHOD_StandardCosting, get_TrxName());
////    	BigDecimal costo = Env.ZERO;
////    	if(mcost!=null)
////    		costo = mcost.getCurrentCostPrice().setScale(scale, BigDecimal.ROUND_HALF_UP);
////    	
////    	return costo;
////        //return getCostStandard().setScale(scale, BigDecimal.ROUND_HALF_UP);
////    }
////    
////    public BigDecimal getCostAverageRed(){
////    	MEXMECost mcost = MEXMECost.get(getCtx(), getProduct(),  X_M_CostElement.COSTINGMETHOD_AveragePO, get_TrxName());
////    	BigDecimal costo = Env.ZERO;
////    	if(mcost!=null)
////    		costo = mcost.getCurrentCostPrice().setScale(scale, BigDecimal.ROUND_HALF_UP);
////
////    	return costo;
////        //return getCostAverage().setScale(scale, BigDecimal.ROUND_HALF_UP);
////    }
////    
////    public BigDecimal getPriceLastPORed(){
////    	MEXMECost mcost = MEXMECost.get(getCtx(), getProduct(),  X_M_CostElement.COSTINGMETHOD_LastPOPrice, get_TrxName());
////    	
////    	BigDecimal costo = Env.ZERO;
////    	if(mcost!=null)
////    		costo = mcost.getCurrentCostPrice().setScale(scale, BigDecimal.ROUND_HALF_UP);
////
////    	return costo;
////        //return getPriceLastPO().setScale(scale, BigDecimal.ROUND_HALF_UP);
////    }
//    
//    public static MEXMEPaqBaseDet copyFrom(Properties ctx, MEXMEPaqBaseDet m_packOld, 
//    		int EXME_PaqBase_Version_ID) throws Exception{
//    	
//    	MEXMEPaqBaseDet m_packNew = new MEXMEPaqBaseDet(ctx, 0, m_packOld.get_TrxName());
//    	m_packNew.setName(m_packOld.getName());
//    	m_packNew.setDescription(m_packOld.getDescription());
//    	m_packNew.setEXME_PaqBase_Version_ID(EXME_PaqBase_Version_ID);
//    	
//    	//Converciones de unidades de medida Regresa ejemplo: la cantidad de piezas que hay en 1 caja
//    	BigDecimal cantUdmV = MEXMEUOMConversion.convertProductFrom(ctx, m_packOld.getM_Product_ID(), 
//    			m_packOld.getC_UOM_ID(), Env.ONE, m_packOld.get_TrxName());
//    	if (cantUdmV == null || Env.ZERO.compareTo(cantUdmV) >= 0) {
//    		m_packNew.setMessageError("error.udm.noExisteConversion");
//    		throw new Exception("No existe conversion de unidad de medida");
//    		
//    	}
//    	m_packNew.setCantidadAlm(cantUdmV);
//    	m_packNew.setSecuencia(m_packOld.getSecuencia());
//    	m_packNew.setM_Product_ID(m_packOld.getM_Product_ID());
//    	m_packNew.setC_UOM_ID(m_packOld.getC_UOM_ID());
//    	m_packNew.setC_UOMVolume_ID(m_packOld.getC_UOMVolume_ID());
//    	m_packNew.setOp_Uom(m_packOld.getOp_Uom());
//    	m_packNew.setCantidad(m_packOld.getCantidad());
//    	m_packNew.setCantidadVol(m_packOld.getCantidadVol());
//    	m_packNew.setPriceList(m_packOld.getPriceLimit());
//    	m_packNew.setPriceLimit(m_packOld.getPriceLimit());
//    	m_packNew.setPriceActual(m_packOld.getPriceActual());
//    	m_packNew.setC_Currency_ID(m_packOld.getC_Currency_ID());
//    	m_packNew.setC_Tax_ID(m_packOld.getC_Tax_ID());
//    	m_packNew.setLineNetAmt(m_packOld.getLineNetAmt());
//    	m_packNew.setTaxAmt(m_packOld.getTaxAmt());
//    	m_packNew.setLineTotalAmt(m_packOld.getLineTotalAmt());
//    	m_packNew.setCosto(m_packOld.getCosto());
//    	
//    	//Costos
//		m_packNew.setCostStandard(m_packOld.getCostStandardRed());
//		m_packNew.setCostAverage(m_packOld.getCostAverageRed());
//		m_packNew.setPriceLastPO(m_packOld.getPriceLastPORed());
//		
//    	return m_packNew;
//    }
//    
//	/**
//	 * El detalle de los paquetes relacionados a la ctapac
//	 * @param ctx
//	 * @param whereClause
//	 * @param orderBy
//	 * @param trxName
//	 * @return
//	 */
//	public static List<MEXMEPaqBaseDet> getLineasPaqueteCta(Properties ctx, int EXME_CtaPac_ID, String trxName){
//		
//		List<MEXMEPaqBaseDet> lista = new ArrayList<MEXMEPaqBaseDet>();
//		StringBuilder sql = new StringBuilder ();
//		
//		 sql.append(" SELECT * FROM EXME_PaqBaseDet ")
//			.append(" WHERE EXME_PaqBase_Version_ID IN ")
//			.append(" (SELECT cpq.EXME_PaqBase_Version_ID FROM ")
//			.append(" EXME_CtaPacPaq cpq WHERE cpq.EXME_CtaPac_ID = ? ) ");
//		
//		sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, sql.toString(),"EXME_PaqBaseDet"));
//		sql.append(" ORDER BY EXME_PaqBase_Version_ID ");
//
//		PreparedStatement pstmt = null;
//		
//		try {
//		    //System.out.println("SQL > " + sql);
//			pstmt = DB.prepareStatement(sql.toString(), trxName);
//			pstmt.setInt(1, EXME_CtaPac_ID);
//			ResultSet rs = pstmt.executeQuery();
//			
//			while (rs.next()) {
//				MEXMEPaqBaseDet ctaPacPaq = new MEXMEPaqBaseDet(ctx, rs, trxName);
//				lista.add(ctaPacPaq);
//			}
//			
//			rs.close();
//			pstmt.close();
//			pstmt = null;
//			//expert:
//			rs=null;
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				if (pstmt != null)
//					pstmt.close ();
//			} catch (Exception e) {}
//			pstmt = null;
//		}
//
//		return lista;
//	}
//	
//	
//    /**
//	 * Obtener lista Cuenta de paciente detalle
//	 * Arturo
//	 * @return
//     * @throws SQLException 
//	 */
//	public static HashMap<String,ArrayList<RegCtaPacDet>> getAllDetallePaqVer(Properties ctx, HashMap<String, Object> mParams, boolean rangoFechas) throws SQLException {
//		
//		HashMap<String,ArrayList<RegCtaPacDet>> mPaqBaseVer = new HashMap<String,ArrayList<RegCtaPacDet>>(50);
//		ArrayList<RegCtaPacDet> paqBaseVerList = new ArrayList<RegCtaPacDet>(50);
//		
//		String paqBaseVer_id = null;
//		StringBuilder sql = new StringBuilder(1000);
//		
//		sql.append("SELECT pbd.EXME_Paqbase_Version_ID")
//		 .append(", pbd.M_Product_ID")
//		 .append(", pbd.c_UOM_ID")
//		 .append(", pbd.cantidad")
//
//		 .append(" FROM EXME_PaqBaseDet pbd")
//
//		 .append(" WHERE pbd.IsActive = 'Y'")
//		 .append(" AND pbd.EXME_PaqBase_Version_ID IN( ")
//		 .append(" 	   SELECT cpp.EXME_PaqBase_Version_ID")
//		 .append("      FROM EXME_CtaPac CtP ")
//		 .append("      INNER JOIN EXME_CtaPacPaq cpp ON ( ctp.EXME_CtaPac_ID = cpp.EXME_CtaPac_ID )")
//		 .append("     WHERE TO_DATE(TO_CHAR( ");
//		 if(!rangoFechas)
//			 sql.append(" CtP.DateOrdered");
//		 else
//			 sql.append(" cpp.Created");
//		 sql.append(" , ")
//		 .append(DB.getDateMask(ctx)).append(") , ")
//		 .append(DB.getDateMask(ctx)).append(") BETWEEN TO_DATE(?, ")
//		 .append(DB.getDateMask(ctx)).append(") AND TO_DATE(?, ")
//		 .append(DB.getDateMask(ctx)).append(") ")
//		 .append("      AND CtP.DocumentNo  >= ? ")
//		 .append(" 	    AND CtP.DocumentNo  <= ? ");
//		 
//		 if(mParams.containsKey("Clause") && mParams.get("Clause")!=null )
//			 sql.append(mParams.get("Clause"));
//		
//		 sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", "EXME_CtaPac", "CtP"))
//		 .append(" GROUP BY cpp.EXME_PaqBase_Version_ID    )")
//		 .append(" ORDER BY pbd.EXME_PaqBase_Version_ID");
//		
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		
//		try {
//			pstmt = DB.prepareStatement(sql.toString(), null);
//			pstmt.setString(1, (String) mParams.get("FechaIni"));
//			pstmt.setString(2, (String) mParams.get("FechaFin"));
//			pstmt.setString(3, (String) mParams.get("CtaPacIniValue"));
//			pstmt.setString(4, (String) mParams.get("CtaPacFinValue"));
//			rs = pstmt.executeQuery();
//			
//			
//			while (rs.next()) {
//				
//				// Si es una nueva cta paciente 
//				if(paqBaseVer_id != null){
//					if (!paqBaseVer_id.equals(rs.getString("EXME_Paqbase_Version_ID"))){
//						mPaqBaseVer.put(paqBaseVer_id, paqBaseVerList);
//						paqBaseVerList = new ArrayList<RegCtaPacDet>(100);
//					}
//				}
//				
//				// Creamos un nuevo registro de ctaPacDet "cargos"
//				RegCtaPacDet regCtaPacDet = new RegCtaPacDet(); ;
//				paqBaseVer_id = rs.getString("EXME_Paqbase_Version_ID");
//				
//				regCtaPacDet.setValue("EXME_PAQBASE_VERSION_ID", rs.getInt("EXME_Paqbase_Version_ID")); 
//				regCtaPacDet.setValue("M_PRODUCT_ID", 	rs.getInt("M_Product_ID")); 
//				regCtaPacDet.setValue("C_UOM_ID", rs.getInt("c_UOM_ID")); 
//				regCtaPacDet.setValue("CANTIDAD", rs.getInt("cantidad")); 
//				regCtaPacDet.setValue("CTD_TMP", rs.getInt("cantidad")); 
//				//Agregar el nuevo registro del a la lista y agregar al mapa del ctaPac
//				paqBaseVerList.add(regCtaPacDet);
//				
//			}
//			
//			if(paqBaseVer_id != null){
//				mPaqBaseVer.put(paqBaseVer_id, paqBaseVerList);
//			}
//			
//			rs.close();
//			rs=null;
//			pstmt.close();
//			pstmt = null;
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//			throw new SQLException("Lista Paquetes:" + e.getMessage() );
//		} finally {
//			try {
//				if (pstmt != null)
//					pstmt.close ();
//				if (rs != null)
//					rs.close ();
//			} catch (Exception e) {e.printStackTrace();}
//			rs=null;
//			pstmt = null;
//		}
//
//		//MCtaPacDet[] ctasPacDet = new MCtaPacDet[list.size()];
//		//list.toArray(ctasPacDet);
//
//		return mPaqBaseVer;
//
//	}
//	
//	public static double getTotalAmts(Properties ctx, List<MEXMEPaqBaseDet> paqIDs, String trxName){
//		
//		double resultado = 0;
//		
//		if(paqIDs.size()>0){
//		
//			String sql = "Select lineTotalAmt from EXME_PaqBaseDet where EXME_PaqBaseDet_ID = ";
//			
//			
//		
//			try {
//				boolean firstEntry = true;
//				for (int i = 0; i<paqIDs.size(); i++) {
//					if (firstEntry) {
//						MEXMEPaqBaseDet p = (MEXMEPaqBaseDet) paqIDs.get(i);
//						sql += " " + String.valueOf(p.getEXME_PaqBaseDet_ID()) + " ";
//						firstEntry= false;
//					} else {
//						MEXMEPaqBaseDet p = (MEXMEPaqBaseDet) paqIDs.get(i);
//						sql += " OR EXME_PaqBaseDet_ID = " + String.valueOf(p.getEXME_PaqBaseDet_ID()) + " ";
//					}
//				}
//			
//			} catch (Exception e) {
//				e.printStackTrace();
//				return 0.0;
//			}
//
//			sql = MEXMELookupInfo.addAccessLevelSQL(ctx, sql.toString(),"EXME_PaqBaseDet");
//			
//			PreparedStatement pstmt = null;
//			
//			try {
//				pstmt = DB.prepareStatement(sql, trxName);
//				ResultSet rs = pstmt.executeQuery();
//				
//				while (rs.next()) {
//					double total = rs.getDouble(1);
//					resultado += total;
//				}
//				
//				rs.close();
//				pstmt.close();
//				pstmt = null;
//				rs = null;
//				
//			} catch (Exception e) {
//				e.printStackTrace();
//			} finally {
//				try {
//					if (pstmt != null)
//						pstmt.close ();
//				} catch (Exception e) {}
//				pstmt = null;
//			}
//		}
//		
//		return resultado;
//	}
//	
////	/**
////	 * Retorna el detalle de una version de paquetes
////	 * @param ctx
////	 * @param EXME_PaqBaseVer_ID
////	 * @param trxName
////	 * @return
////	 */
////	public static List<MEXMEPaqBaseDet> getLines(Properties ctx, int EXME_PaqBaseVer_ID, String trxName){
////			
////			List<Integer> params = new ArrayList<Integer>();
////			
////			StringBuilder sql = new StringBuilder();
////			
////			sql.append(" SELECT * FROM EXME_PaqBaseDet ")
////			   .append(" WHERE EXME_PaqBase_Version_ID = ? ")
////			   .append(" AND IsActive = 'Y' ");
////			params.add(EXME_PaqBaseVer_ID);
////			sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, sql.toString(), "EXME_PaqBaseDet"));
////			
////			sql.append(" ORDER BY Secuencia ASC");
////			
////			return getLines(ctx,  sql.toString(), params, trxName);
////	}
//	
////	/**
////	 * Detalle de varios paquetes
////	 * @param ctx
////	 * @param lstPaqBaseVer
////	 * @param trxName
////	 * @return
////	 */
////	public static List<MEXMEPaqBaseDet> getLines(Properties ctx, List<MEXMEPaqBaseVersion> lstPaqBaseVer, String trxName){
////
////		List<Integer> params = new ArrayList<Integer>();
////
////		StringBuilder sql = new StringBuilder();
////
////		sql.append(" SELECT * FROM EXME_PaqBaseDet ")
////		.append(" WHERE IsActive = 'Y'")
////		.append(" AND EXME_PaqBase_Version_ID IN ( ");
////		
////		for (int i = 0; i < lstPaqBaseVer.size(); i++) 
////		{
////			if(i>0)
////				sql.append(" , ");
////			
////			sql.append(" ? ");
////			params.add(lstPaqBaseVer.get(i).getEXME_PaqBase_Version_ID());	
////		}
////		
////		sql.append(") ");
////		sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, sql.toString(), "EXME_PaqBaseDet"));
////		sql.append(" ORDER BY Secuencia ASC");
////
////		return getLines(ctx,  sql.toString(), params, trxName);
////	}
////	
////
////	/**
////	 * 
////	 * @param ctx
////	 * @param sql
////	 * @param params
////	 * @param trxName
////	 * @return
////	 */
////	public static List<MEXMEPaqBaseDet> getLines(Properties ctx, String sql, List<?> params, String trxName){
////		
////		List<MEXMEPaqBaseDet> lista = new ArrayList<MEXMEPaqBaseDet>();
////		
////		PreparedStatement pstmt = null;
////		ResultSet rs = null;
////		try {
////			pstmt = DB.prepareStatement(sql.toString(), trxName);
////			DB.setParameters(pstmt, params);
////			rs = pstmt.executeQuery();
////			
////			while (rs.next()) {
////				MEXMEPaqBaseDet ctaPacPaq = new MEXMEPaqBaseDet(ctx, rs, trxName);
////				ctaPacPaq.setCantidadOld(ctaPacPaq.getCantidad());
////				lista.add(ctaPacPaq);
////			}
////						
////		} catch (Exception e) {
////			e.printStackTrace();
////		} finally {
////			DB.close(rs, pstmt);
////		}
////
////		return lista;
////	}
//	
//	
//	/**
//	 * Con el resultado del query sabremos si para un determinado paquete
//	 * las existencias en determinado almacen son suficientes ademas de que existan esos productos
//	 * @param ctx
//	 * @param EXME_PaqBaseVer_ID
//	 * @param M_Warehouse_ID
//	 * @param trxName
//	 * @return
//	 */
//	public static boolean existencias(Properties ctx, int EXME_PaqBaseVer_ID, 
//			int M_Warehouse_ID, String trxName){
//		
//		boolean existencias = true;
//		
//		StringBuilder sql = new StringBuilder();
//		
//		 sql.append(" SELECT EXME_PaqBaseDet.m_product_id ")
//			.append(", SUM(NVL(s.qtyOnhand,0) - NVL(s.qtyReserved,0)) - SUM(NVL(EXME_PaqBaseDet.Cantidadalm,0)) AS disponible ")
//			.append(" FROM EXME_PaqBaseDet ")
//			.append(" LEFT JOIN M_Product p ON ( p.M_Product_ID = EXME_PaqBaseDet.M_Product_ID AND p.IsActive = 'Y' ) ")
//			.append(" LEFT JOIN M_Replenish r ON ( r.M_Product_ID = EXME_PaqBaseDet.M_Product_ID AND r.M_Warehouse_ID = ? AND r.IsActive = 'Y' ) ")
//			.append(" LEFT JOIN M_Locator l ON ( l.M_Warehouse_ID = r.M_Warehouse_ID AND l.IsActive = 'Y' ) ")
//			.append(" LEFT JOIN M_Storage s ON ( s.M_Locator_ID = l.M_Locator_ID AND s.M_Product_ID = EXME_PaqBaseDet.M_Product_ID AND s.IsActive = 'Y' ) ")
//			.append(" WHERE EXME_PaqBaseDet.IsActive = 'Y' ")
//			.append(" AND EXME_PaqBaseDet.EXME_PaqBase_Version_ID = ? ")
//			.append(" AND p.ProductType <> 'S' ");
//		
//		sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, sql.toString(), "EXME_PaqBaseDet"));
//		
//		sql.append(" GROUP BY EXME_PaqBaseDet.M_Product_ID ");
//		PreparedStatement pstmt = null;
//		
//		try {
//			pstmt = DB.prepareStatement(sql.toString(), trxName);
//			pstmt.setInt(1, M_Warehouse_ID);
//			pstmt.setInt(2, EXME_PaqBaseVer_ID);
//			ResultSet rs = pstmt.executeQuery();
//			
//			while(rs.next()){
//				BigDecimal dospo = rs.getBigDecimal("disponible");
//				if(dospo!= null && dospo.compareTo(Env.ZERO)<=0){
//					existencias = false;
//					break;
//				}
//			}
//				
//				/*double disponible  = rs.getDouble("disponible");
//				if(disponible<=0.0)
//					existencias = false;
//				if(rs.getBigDecimal("disponible").compareTo(Env.ZERO)<=0){
//					existencias = false;
//					break;
//				}*/
//			//}
//			
//			rs.close();
//			pstmt.close();
//			pstmt = null;
//			rs=null;
//			
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				if (pstmt != null)
//					pstmt.close ();
//			} catch (Exception e) {}
//			pstmt = null;
//		}
//		
//		return existencias;
//	}
//
//	/**
//	 * 
//	 * @param ctx
//	 * @param EXME_PaqBaseVer_ID
//	 * @param EXME_Paciente_ID
//	 * @param trxName
//	 * @return
//	 */
//	
//	public static boolean alergias(Properties ctx, int EXME_PaqBaseVer_ID,
//			int EXME_Paciente_ID, String trxName){
//		
//		boolean existencias = true;
//		
//		StringBuilder sql = new StringBuilder();
//		
//		 sql.append(" SELECT a.EXME_SActiva_ID ")
//		.append(" FROM EXME_PaqBaseDet pbv ")
//		.append(" INNER JOIN EXME_ProductSActiva s ON (s.M_Product_ID = pbv.M_Product_ID AND s.IsActive = 'Y') ")
//		.append(" INNER JOIN EXME_PacienteAler a ON (a.EXME_SActiva_ID = s.EXME_SActiva_ID AND a.IsActive = 'Y') ")
//		.append(" WHERE  pbv.IsActive = 'Y'  ")  
//		.append(" AND a.EXME_Paciente_ID = ? ") 
//		.append(" AND pbv.EXME_PaqBase_Version_ID = ? ");
//		
//		sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, sql.toString(), "EXME_PaqBaseDet"));
//		
//		PreparedStatement pstmt = null;
//		
//		try {
//			pstmt = DB.prepareStatement(sql.toString(), trxName);
//			pstmt.setInt(1, EXME_Paciente_ID);
//			pstmt.setInt(2, EXME_PaqBaseVer_ID);
//			
//			ResultSet rs = pstmt.executeQuery();
//			
//			while (rs.next()) {
//				if(rs.getBigDecimal(2).compareTo(Env.ZERO)<=0){
//					existencias = false;
//					break;
//				}
//			}
//			
//			rs.close();
//			pstmt.close();
//			pstmt = null;
//			//expert:
//			rs=null;
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				if (pstmt != null)
//					pstmt.close ();
//			} catch (Exception e) {}
//			pstmt = null;
//		}
//		
//		return existencias;
//	}
//    
//    
//    /**
//     * Retorna el detalle de una version de paquetes
//     * @param ctx
//     * @param EXME_PaqBaseVer_ID
//     * @param M_Product_ID
//     * @param trxName
//     * @return
//     */
//    public static MEXMEPaqBaseDet getLine(Properties ctx, int EXME_PaqBaseVer_ID, 
//		int M_Product_ID, String trxName){
//        
//        //List lista = new ArrayList();
//    	MEXMEPaqBaseDet retValue = null;
//
//        StringBuilder sql = new StringBuilder("SELECT * FROM EXME_PaqBaseDet ")
//            .append("WHERE EXME_PaqBase_Version_ID = ? ")
//            .append("AND M_Product_ID = ? AND IsActive = 'Y' ");
//
//        sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, sql.toString(),
//                "EXME_PaqBaseDet")).append(" ORDER BY Secuencia");
//        
//        PreparedStatement pstmt = null;
//        
//        try {
//            pstmt = DB.prepareStatement(sql.toString(), trxName);
//            pstmt.setInt(1, EXME_PaqBaseVer_ID);
//            pstmt.setInt(2, M_Product_ID);
//            ResultSet rs = pstmt.executeQuery();
//            
//            if (rs.next()) {
//                retValue = new MEXMEPaqBaseDet(ctx, rs, trxName);
//            }
//            
//            rs.close();
//            pstmt.close();
//            pstmt = null;
//            rs=null;
//            
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                if (pstmt != null)
//                    pstmt.close ();
//            } catch (Exception e) {}
//            pstmt = null;
//        }
//
//        return retValue;
//    }
//    
//    private String priceActualString = null;
//    
//    public String getPriceActualString() {
//    	try{
//    		if(priceActualString==null || (priceActualString!=null && priceActualString.equalsIgnoreCase("")))
//    			priceActualString = new DecimalFormat("###,###,##0.00").format(Double.valueOf(getPriceActual().setScale(2, BigDecimal.ROUND_HALF_UP).toString()));
//    		else
//    			priceActualString = new DecimalFormat("###,###,##0.00").format(Double.valueOf(priceActualString.replace(",", "")));//lhernandez. Double.valueOf... para evitar IllegalArgumentException 
//    	}catch (Exception e) {
//    		e.printStackTrace();
//    	}
//    	return priceActualString;
//    }
//
//	public void setPriceActualString(String priceActualString) {
//		this.priceActualString = priceActualString;
//	}
//	
//	public BigDecimal priceActual() {
//		String priceActual = null;
//		if(priceActualString == null || (priceActualString != null && priceActualString.equalsIgnoreCase("")))
// 			priceActualString = getPriceActual().setScale(2, BigDecimal.ROUND_HALF_UP).toString();
// 		else
// 			priceActual = priceActualString.replace(",", "");
//		 return new BigDecimal(priceActual);
//	 }
//	 
////	/**
////	 * Cuando estamos en el mtto de paquetes, en la edicion es posible que el usuario
////	 * decida siempre no actualizar en la lista de detalle de la version la cantidad que 
////	 * incluye el paquete. CantidadOld conserva la cantidad original
////	 */
////	private BigDecimal cantidadOld = Env.ZERO;
////
////	public BigDecimal getCantidadOld() {
////		return cantidadOld;
////	}
////
////	public void setCantidadOld(BigDecimal cantidadOld) {
////		this.cantidadOld = cantidadOld;
////	}
//	
//	  /**
//     * Llena la tabla temporal de EXME_T_PaqBaseDet
//     * a partir del detalle de un paquete en especifico, para la cuenta paciente
//     * @param ctx
//     * @param EXME_CtaPac_ID
//     * @param AD_Session_ID
//     * @param secuencia
//     * @param paqbase_version_id
//     * @param trxName
//     * @throws Exception
//     */
////    public static void getPaquetes(Properties ctx, int EXME_CtaPac_ID, int AD_Session_ID,
////            BigDecimal secuencia, int paqbase_version_id, String trxName) throws Exception {
////        
////        StringBuilder cadena = new StringBuilder("SELECT EXME_PaqBaseDet.* FROM EXME_PaqBaseDet ")
////            .append("WHERE EXME_PaqBaseDet.IsActive = 'Y' ")
////            .append("AND EXME_PaqBaseDet.EXME_PaqBase_Version_ID = ").append(paqbase_version_id);
////            
////        String sql = MEXMELookupInfo.addAccessLevelSQL(ctx, cadena.toString(), "EXME_PaqBaseDet");
////        
////        PreparedStatement pstmt = null;
////        ResultSet rs = null;
////        try {
////            pstmt = DB.prepareStatement(sql, trxName);
////            rs = pstmt.executeQuery();
////            
////            while (rs.next()) {
////                MEXMETPaqBaseDet paqBaseDet = new MEXMETPaqBaseDet(ctx, 0, null);
////                paqBaseDet.setM_Product_ID(rs.getInt("M_Product_ID"));
////                paqBaseDet.setC_UOM_ID(rs.getInt("C_UOM_ID"));
////                paqBaseDet.setAD_Session_ID(AD_Session_ID);
////                paqBaseDet.setSecuencia(secuencia);
////                paqBaseDet.setCantidad(rs.getBigDecimal("Cantidad"));
////                paqBaseDet.setEXME_CtaPac_ID(EXME_CtaPac_ID);
////                //La cantidad deber� estar en unidad de medida de almacenamiento
////                BigDecimal qty = MEXMEUOMConversion.convertProductFrom(ctx, 
////                        paqBaseDet.getM_Product_ID(), paqBaseDet.getC_UOM_ID(), 
////                        rs.getBigDecimal("Cantidad"), null);
////                paqBaseDet.setQtyPendiente(qty);
////                paqBaseDet.setEXME_PaqBase_Version_ID(rs.getInt("EXME_PaqBase_Version_ID"));
////
////                if(!paqBaseDet.save(trxName)){
////                    throw new Exception("error.paqute.noSave");
////                }
////                
////                paqBaseDet = null;
////            }
////            
////        } catch (Exception e) {
////           // s_log.log(Level.SEVERE, sql.toString(), e.getMessage());
////            throw new Exception("error.paqute.noSave");
////        }finally{
////            try {
////                if (pstmt != null)
////                    pstmt.close ();
////                if (rs != null)
////                    rs.close();
////            }
////            catch (Exception e) {}
////            pstmt = null;
////            rs = null;
////            sql = null;
////        }
////    }
//
//}
