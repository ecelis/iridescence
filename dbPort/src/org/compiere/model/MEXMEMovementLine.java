package org.compiere.model;


/**
 * 
 * @deprecated
 *
 */
public class MEXMEMovementLine {} // extends MMovementLine {

//	/** serialVersionUID */
//	private static final long serialVersionUID = -3162751733028716120L;
//	/** Static Logger               */
//    private static CLogger      s_log = CLogger.getCLogger (MEXMEMovementLine.class);
//    /** movimiento */
//    private MEXMEMovement mMovement = null;
//    /** cuenta paciente */
//    private MEXMECtaPac mCtaPac = null;
//    
//    /**
//     * @param ctx
//     * @param M_Movement_ID
//     * @param trxName
//     */
//    public MEXMEMovementLine(Properties ctx, int M_MovementLine_ID, String trxName) {
//        super(ctx, M_MovementLine_ID, trxName);
//    }

//    /**
//     * @param ctx
//     * @param rs
//     * @param trxName
//     */
//    public MEXMEMovementLine(Properties ctx, ResultSet rs, String trxName) {
//        super(ctx, rs, trxName);
//    }
//    
//    /**
//     * Devuelve un arreglo con la lineas de determinado movimiento
//     * @param ctx
//     * @param C_CashBook_ID Caja
//     * @param C_Cash_ID         Linea de Apertura de caja
//     * @param trxName
//     * @return
//     */
//    public static MEXMEMovementLine[] getLineas(Properties ctx, int M_Movement_ID, String trxName){
//
//        List<MEXMEMovementLine> lista = new ArrayList<MEXMEMovementLine>();
//      
//        if (ctx == null) {
//            return null;
//        }
//              
//       String sql =  " SELECT M_MovementLine_ID " +
//                    " FROM M_MovementLine WHERE IsActive = 'Y' " +
//                    " AND m_movement_id = ? "; 
//       
//       sql = MEXMELookupInfo.addAccessLevelSQL(ctx, sql, "M_MovementLine");
//     
//       s_log.log(Level.SEVERE,"SQL > " + sql);
//       //System.out.println("SQL >" + sql);
//       PreparedStatement pstmt = null;
//       ResultSet rs = null;
//       try {
//            pstmt = DB.prepareStatement(sql, trxName);
//            pstmt.setInt(1, M_Movement_ID); 
//            rs = pstmt.executeQuery();
//        
//            while(rs.next()){
//                MEXMEMovementLine m_movement = new MEXMEMovementLine(ctx, rs.getInt("M_MovementLine_ID"), trxName);
//                lista.add(m_movement);
//            } 
//           
//
//        } catch (Exception e) {
//        	e.printStackTrace();//FIXME
//        } finally {
//        	DB.close(rs, pstmt);
//        }
//        
//        MEXMEMovementLine[] retValue = new MEXMEMovementLine[lista.size()];
//        lista.toArray(retValue);
//
//        return retValue;
//    }
//    
//    /**
//     * 
//     * @param ctx
//     * @param M_Product_ID
//     * @param trxName
//     * @return
//     */
//    public static int getQtyUnconfirmed(Properties ctx, int M_Product_ID, String trxName){
//
//        int retValue = 0;
//    
//       String sql =  " SELECT SUM(ORIGINALQTY) - SUM(CONFIRMEDQTY) FROM M_MovementLine WHERE M_PRODUCT_ID = ? "
//    	   			+ " AND isActive = 'Y' ";
//       
//       sql += MEXMELookupInfo.addAccessLevelSQL(ctx," ", "M_MovementLine");
//
//       s_log.log(Level.SEVERE,"SQL > " + sql);
//       PreparedStatement pstmt = null;
//       ResultSet rs = null;
//       try {
//            pstmt = DB.prepareStatement(sql, trxName);
//            pstmt.setInt(1, M_Product_ID);
//            rs = pstmt.executeQuery();
//        
//            if(rs.next()){
//                retValue = rs.getInt(1);
//            } 
//           
//
//        } catch (SQLException e) {
//        	e.printStackTrace();//FIXME
//        } finally {
//        	DB.close(rs, pstmt);
//        }
//
//        return retValue;
//    }
//
//    /**
//     * Obtenemos el detalle del movimiento.
//     * @param ctx
//     * @param M_Movement_ID
//     * @param trxName
//     * @return
//     * @throws SQLException
//     */
//    public static boolean getDetailNumSerie(Properties ctx, int M_Movement_ID, String trxName)
//            throws SQLException {
//        StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
//        PreparedStatement pstmt = null;
//        ResultSet rs = null;
//        boolean numSerie = false;
//
//        try {
//            sql.append("SELECT M_MovementLine.* ")
//                .append("FROM M_MovementLine ")
//                .append("INNER JOIN M_Product p ON (M_MovementLine.M_Product_ID = p.M_Product_ID) ")
//                .append("WHERE M_MovementLine.M_Movement_ID = ? AND M_MovementLine.IsActive = 'Y' AND ")
//                .append("p.IsActive = 'Y' AND p.IsNumSerie = 'Y' ");
//            
//            sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, sql.toString(), "M_MovementLine"));
//            
//            sql.append(" ORDER BY M_MovementLine.Line");
//
//            s_log.finer("getDetailNumSerie : " + sql);
//            
//            pstmt = DB.prepareStatement(sql.toString(), trxName);
//            pstmt.setInt(1, M_Movement_ID);
//            rs = pstmt.executeQuery();
//
//            if (rs.next()) {
//                numSerie = true;
//            }
//
//        } catch (SQLException e) {
//            s_log.log(Level.SEVERE, "getDetailNumSerie (" + sql + ")", e);
//            throw e;
//        } finally {
//        	DB.close(rs, pstmt);
//            rs = null;
//            pstmt = null;
//        }
//
//        return numSerie;
//    }
//    
//    /**
//     * 
//     * @param ctx
//     * @param original
//     * @param lotes
//     * @param trxName
//     * @return
//     * @throws Exception
//     */
//	public static List<MMovementLine> divideByLot(Properties ctx, List<MMovementLine> original, StorageVOList lotes, String trxName) throws Exception {
//		List<MMovementLine> retValue = new ArrayList<MMovementLine>();
//		int linea = 10;
//		for (int i = 0; i < original.size(); i++) {
//			MMovementLine ml = original.get(i);
//			MProduct p = new MProduct(ctx, ml.getM_Product_ID(), null);
//			if (p.isLot()) {
//				List<StorageVO> tmpList = lotes.getByProductID(ml.getM_Product_ID());
//				boolean shouldAdd = false;
//				if (tmpList == null) {
//					// throw new Exception("msj.selccionarLotes");
//					ml.setTargetQty(BigDecimal.ZERO);
//					shouldAdd = true;
//				} else {
//					BigDecimal qtyOriginal = BigDecimal.ZERO;
//					BigDecimal acum = BigDecimal.ZERO;
//					int attributeId = 0;
//					if (tmpList.size() == 0) {
//						// throw new Exception("msj.selccionarLotes");
//						ml.setTargetQty(BigDecimal.ZERO);
//						shouldAdd = true;
//					} else {
//						qtyOriginal = tmpList.get(0).getQtyOriginal();
//						attributeId = tmpList.get(0).getM_AttributeSetInstance_ID();
//					}
//					for (int j = 0; j < tmpList.size(); j++) {
//						StorageVO tmp = tmpList.get(j);
//						MMovementLine toAdd = new MMovementLine(ctx, 0, trxName);
//						PO.copyValues(ml, toAdd);
//						toAdd.setUom((new MProduct(ctx, tmp.getM_Product_ID(), null)).getUom());
//						toAdd.setM_AttributeSetInstance_ID(tmp.getM_AttributeSetInstance_ID());
//						toAdd.setM_AttributeSetInstanceTo_ID(tmp.getM_AttributeSetInstance_ID());
//						toAdd.setOriginalQty(tmp.getQtyRequested());
//						toAdd.setTargetQty(tmp.getQtyRequested());
//						toAdd.setLot(tmp.getLot());
//						toAdd.setFecha(tmp.getGuaranteeDate());
//						toAdd.setLine(linea);
//						toAdd.setNumSerie(ml.getNumSerie());
//						retValue.add(toAdd);
//						linea = linea + 10;
//						acum = acum.add(tmp.getQtyRequested());
//					}
//					if(qtyOriginal.compareTo(acum) == 1){
//						BigDecimal dif = qtyOriginal.subtract(acum);
//						MMovementLine toAdd = new MMovementLine(ctx, 0, trxName);
//						PO.copyValues(ml, toAdd);
//						toAdd.setUom((new MProduct(ctx, ml.getM_Product_ID(), null)).getUom());
//						toAdd.setM_AttributeSetInstance_ID(attributeId);
//						toAdd.setM_AttributeSetInstanceTo_ID(attributeId);
//						toAdd.setOriginalQty(dif);
//						toAdd.setTargetQty(BigDecimal.ZERO);
//						toAdd.setLine(linea);
//						toAdd.setNumSerie(ml.getNumSerie());
//						retValue.add(toAdd);
//						linea = linea + 10;
//					}
//					
//					ml.delete(true, trxName);
//					
//				}
//				
//				if (shouldAdd) {
//					ml.setLine(linea);
//					retValue.add(ml);
//					linea = linea + 10;
//				}
//			} else {
//				ml.setLine(linea);
//				retValue.add(ml);
//				linea = linea + 10;
//			}
//		}
//		return retValue;
//	}
//    
//	/**
//	 * MEXMEMovement
//	 * @return
//	 */
//	public MEXMEMovement getMovement() {
//		if(mMovement==null){
//			mMovement = new MEXMEMovement(getCtx(), getM_Movement_ID(), get_TrxName());
//		}
//		return mMovement;
//	}
//	
//    /**
//     * Obtenemos la cuenta paciente
//     * @return
//     */
//    public MEXMECtaPac getCtaPac(){
//        if(mCtaPac == null || mCtaPac.getEXME_CtaPac_ID() == 0){
//        	mCtaPac = new MEXMECtaPac(getCtx(), getEXME_CtaPac_ID(), get_TrxName());
//        }
//        return mCtaPac;
//    }
//
//	public void setMovement(MEXMEMovement mexmeMovement) {
//		if(mMovement==null){
//			mMovement = mexmeMovement;
//		}
//	}
//}