/*
 * Created on 22-mar-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.compiere.model;


/**
 * Modelo de Movimiento entre Almacenes
 * <b>Modificado: </b> $Author: taniap $<p>
 * <b>En :</b> $Date: 2006/10/12 21:32:33 $<p>
 *
 * @author Hassan Reyes
 * @version $Revision: 1.20 $
 * @deprecated
 */
public class MEXMEMovement {} /// extends MMovement {

//	/** serialVersionUID */
//	private static final long serialVersionUID = -8464636913593548837L;
//	/** Static Logger               */
//    private static CLogger      s_log = CLogger.getCLogger (MEXMEMovement.class);
//    /** cuenta paciente */
//    private MEXMECtaPac mCtaPac = null;
//    /**	Process Message 			*/
//	private String		m_processMsg = null;
//	/**
//	 * Nombre del Tipo de Documento MM Shipment
//	 */
//	private final String MM_SHIPMENT = "MM SHIPMENT";
//	
//    /**
//     * @param ctx
//     * @param M_Movement_ID
//     * @param trxName
//     */
//    public MEXMEMovement(Properties ctx, int M_Movement_ID, String trxName) {
//        super(ctx, M_Movement_ID, trxName);
//    }
//
//    /**
//     * @param ctx
//     * @param rs
//     * @param trxName
//     */
//    public MEXMEMovement(Properties ctx, ResultSet rs, String trxName) {
//        super(ctx, rs, trxName);
//    }
//	
//    /**
//     * Hace el cargo a la cuenta paciente a partir del movimiento entre almacenes.
//     * @param EXME_CtaPac_ID
//     * @param EXME_EstServ_ID
//     * @return true si se realizo el cargo, false de lo contrario.
//     */
//    public boolean chargeToCtaPac(int EXME_CtaPac_ID, int EXME_EstServ_ID){
//        
//        // Revisamos que el movimiento este completo.
//        if(DocAction.STATUS_Completed.equals(getDocStatus())){
//            
//            if(EXME_CtaPac_ID <= 0 || EXME_EstServ_ID <= 0){
//                m_processMsg = "La Cuenta Paciente y Estaci\u00F3n de Servicio son necesarios (EXME_CtaPac_ID <= 0 || EXME_EstServ_ID <= 0).";
//                return false;
//            }
//            
//            final MEXMECtaPac ctaPac = new MEXMECtaPac(getCtx(), EXME_CtaPac_ID, get_TrxName());
//            final MEXMEEstServ estServ = new MEXMEEstServ(getCtx(), EXME_EstServ_ID, get_TrxName());
//            
//            // Revisamos que se hayan movido almenos una linea.
//            final MMovementLine[] mlines = getLines(false);
//            if(mlines.length <= 0){
//                m_processMsg = "No existen lineas a cargar.";
//                return false;
//            }
//            
//            //Revisamos que almenos haya 1 unidad a cargar en todas las lineas..
//            // y obtenemos el almacen
//            // y si debe surtir
//            int almacen = 0;
//            boolean aSurtir = false;
//            for(int i = 0; i < mlines.length; i++){
//                final MMovementLine mline =  mlines[i];
//                final MLocator loc = new MLocator(getCtx(), mline.getM_LocatorTo_ID(), get_TrxName());
//                almacen = loc.getM_Warehouse_ID();
//                final BigDecimal qty = mline.getConfirmedQty().subtract(mline.getScrappedQty());
//                if(qty != null && qty.compareTo(Env.ONE) >= 0){
//                    aSurtir = true;
//                    break;
//                }
//            }
//            if(!aSurtir){
//                m_processMsg = "No existen lineas a cargar.";
//                return false;
//            }
//            
//         // Creamos un InOut por Almacen a nivel de linea del movimiento.
//            final ArrayList<MEXMEInOutLine> inOuts = new ArrayList<MEXMEInOutLine>();
//            int noLine = 0;
//            
//            // in out nuevo
//            final MEXMEInOut inOut = new MEXMEInOut(getCtx(), 0, get_TrxName());
//            inOut.setIsSOTrx(true);
//            inOut.setDateOrdered(getMovementDate());
//            inOut.setBPartner(ctaPac.getBPartner());
//            inOut.setM_Warehouse_ID(almacen);//
//            inOut.setAD_OrgTrx_ID(estServ.getAD_OrgTrx_ID()); // La OrgTrx se toma de la EstServ de donde se hace el cargo.(solicita)
//            inOut.setC_DocType_ID(MEXMEDocType.getOfName(getCtx(), MM_SHIPMENT, get_TrxName()));
//            inOut.setMovementType(MInOut.MOVEMENTTYPE_CustomerShipment);
//            inOut.setEXME_CtaPac_ID(ctaPac.getEXME_CtaPac_ID());
//            inOut.setProcessed(true);
//            if(!inOut.save(get_TrxName())){
//                m_processMsg = "No fu\u00E9 posible guardar la salida de inventario.";
//                return false;
//            }
//            
//            
//            //Lineas de movimiento que en vdd se van a cargar
//            for(int i = 0; i < mlines.length; i++){
//                
//            	final  MMovementLine mline =  mlines[i];
//                final  MLocator l = new MLocator(getCtx(),mlines[i].getM_Locator_ID(), get_TrxName());             //Noelia: se obtiene la estacion de servicio
//                final  MEXMEEstServ est =   MEstServAlm.getEstServ(getCtx(),l.getM_Warehouse_ID(),get_TrxName());      //que surte el producto
//                
//                //Validamos que la linea tenga cantidad a aplicar, si no se descarta.
//                BigDecimal qty = mline.getConfirmedQty().subtract(mline.getScrappedQty());
//                if(qty == null || qty.compareTo(Env.ONE) < 0 )
//                    continue;
//               
//                noLine += 10;
//                
//                // Stock Info
//                final MLocator locator = new MLocator(getCtx(), mline.getM_LocatorTo_ID(), get_TrxName());
//    			boolean stocked = MProduct.isProductStocked(getCtx(), mlines[i].getM_Product_ID());
//    			MStorage[] storage = null;
//    			if (stocked){
//    				storage = MStorage.getWarehouse (getCtx(), locator.getM_Warehouse_ID(), 
//    					mlines[i].getM_Product_ID(), 0, 0, 
//    					false, null, false, get_TrxName());
//
//	    			BigDecimal maxQty = Env.ZERO;
//					for (int ll = 0; ll < storage.length; ll++)
//						maxQty = maxQty.add(storage[ll].getQtyOnHand());
//					
//					if (maxQty.compareTo(qty) < 0){
//					    log.info("!!!! Se cargo a la cuenta paciente menos de la cantidad que fu\u00E9 movida.");
//						qty = maxQty;
//					}
//	                
//					//Creamos la linea en MInOut
//					final MEXMEInOutLine ioLine = new MEXMEInOutLine(inOut);
//	                ioLine.setLine(noLine);
//	                ioLine.setOrderLine(mline, mline.getM_LocatorTo_ID());
//	                ioLine.setQty(qty);
//	                ioLine.setProcessed(true);
//	                ioLine.setMovementLine(mline);// Guardamos la refeencia al movimiento (no se almacena en la BD).
//	                ioLine.setAD_OrgTrx_ID(est.getAD_OrgTrx_ID());//Noelia: la linea guarda la org trx del almacen que surte el producto
//	                
//	                if(!ioLine.save(get_TrxName())){
//	                    m_processMsg = "No fue posible guardar la linea de salida de inventario.";
//	                    return false;
//	                }
//	                
//	                // para cada linea del detalle actualizamos la tabla de EXME_NumSerie , con estatus = Aplicado
//					if (mline.getProduct().isNumSerie()) {
//						MEXMENumSerie numSerie = null;
//						try {
//							numSerie = MEXMENumSerie.getFromNumSerie(getCtx(), mline.getNumSerie(),(int) mline.getM_Product_ID(), get_TrxName());
//						} catch (Exception e) {e.printStackTrace();}
//						if (numSerie != null) {
//							numSerie.setDocStatus(MEXMENumSerie.DOCSTATUS_Aplicado);
//							if (!numSerie.save()) {
//								m_processMsg = "No fue posible guardar el numero de serie.";
//								return false;
//							}
//						}
//					}
//					// Fin .- Lama
//	                
//	                inOuts.add(ioLine);
//    			}// isStocked
//            }// Fin for lineas de movimiento que se van a cargar
//                
//            //completamos el documento de inOut
//            String status = inOut.prepareIt();
//            if(!DocAction.STATUS_InProgress.equals(status)){
//                m_processMsg = "Estatus incorrecto para la salida de inventario";
//                return false;
//            }
//            
//            status = inOut.completeIt();
//            inOut.setDocStatus(status);
//            if(!DocAction.STATUS_Completed.equals(status)){
//                m_processMsg = "No fue posible realizar la salida de inventario";
//                return false;
//            }
//            
//            if(!inOut.save(get_TrxName())){
//                m_processMsg = "No fue posible guardar la linea de salida de inventario.";
//                return false;
//            }
//
//            // Barremos las lines de los InOut generados para generar los cargos a la cuenta paciente.
//            Iterator<MEXMEInOutLine> i = inOuts.iterator();
//            while(i.hasNext()){
//                // Linea de inventario
//                final MEXMEInOutLine inOutLine = (MEXMEInOutLine)i.next();
//                // Cargo
//                MCtaPacDet ctaPacDet = MCtaPacDet.createFrom(ctaPac, ctaPac.getEXME_CtaPacExt_ID(), 
//                		estServ.getEXME_Area_ID(), inOutLine);
//                
//                if(ctaPacDet == null){ // possible nullpointer exception
//                    m_processMsg = "No fue posible generar el cargo a la cuenta paciente a partir de la salida de inventario.";
//                    return false;
//                }
//  
//                // Numero de linea.
//                ctaPacDet.setLine();
//                ctaPacDet.setDateDelivered(ctaPacDet.getDateOrdered());
//                ctaPacDet.setDescription("Aplicacion automatica a partir del movimiento - " + getDocumentNo());
//                ctaPacDet.setQtyPendiente(inOutLine.getMovementQty());
//                ctaPacDet.setQtyPaquete(Env.ZERO);
//                ctaPacDet.setM_InOutLine_ID(inOutLine.getM_InOutLine_ID());
//                ctaPacDet.setAD_OrgTrx_ID(estServ.getAD_OrgTrx_ID()); //Twry: el cargo guarda la org trx del almacen que surte el producto que es la misma que solicita viendo desde el punto de cargo de ctapac
//                ctaPacDet.setCgoProcesado(true);
//
//                //Para cuando se hace devolucion desde TS **gderreza**
//                if(isDevol()){
//                	ctaPacDet.setQtyOrdered(ctaPacDet.getQtyOrdered().negate());
//                	ctaPacDet.setQtyDelivered(ctaPacDet.getQtyDelivered().negate());
//                	ctaPacDet.setQtyPendiente(Env.ZERO);
//                	ctaPacDet.setCgoProcesado(true);
//                	ctaPacDet.setSeDevolvio(true);                    
//                	inOutLine.setMovementQty(inOutLine.getMovementQty().negate());
//                	inOutLine.setQtyEntered(inOutLine.getQtyEntered().negate());
//                }
//
//                // busqueda de precios
//                final MPrecios precios = GetPrice.getPriceCargo(getCtx()
//                		, ctaPacDet);
//                if(precios!=null){
//                	ctaPacDet =  precios.preciosActual(ctaPacDet);
//                }
//
//                // Guardar el cargo
//                if(!ctaPacDet.save(get_TrxName())){
//                	m_processMsg = "No fu\u00E9 posible guardar el cargo.";
//                	return false;
//                }
//                
//                // Guardar la relacions
//                inOutLine.setEXME_CtaPacDet_ID(ctaPacDet.getEXME_CtaPacDet_ID()); //Noelia: Guarda EXME_CtaPacDet_ID del Cargo creado en EXME_InOutLine
//
//                //Para cuando se hace devolucion desde TS **gderreza**
//                if (isDevol()) {
//                	try {
//                		calculoDevol(ctaPac, get_TrxName(), getCtx(), null, //27032009 rsolorzano ya no se debe utilizar la transacci�n de la forma
//                				ctaPacDet, almacen);
//                	} catch (Exception e) {
//						e.printStackTrace();
//					}
//				}
//                if(!inOutLine.save(get_TrxName())){   //Noelia: Validaci�n por si no se guarda la linea de salida de inventario
//                	m_processMsg = "No fu\u00E9 posible guardar la salida de inventario";
//                return false;
//                }
//                
//            }//InOuts
//            
//            // Actualizamos el estatus del movimiento.
//            setDocStatus(DocAction.STATUS_Closed);
//            setDocAction(DocAction.ACTION_None);
//            setProcessed(true);
//            
//        }// DocStatus - CO.
//        return true;
//    }
//    
//    public String getM_processMsg() {
//        return m_processMsg;
//    }
//    
//    public void setM_processMsg(String msg) {
//        m_processMsg = msg;
//    }
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
////    /**
////     * Le asignamos la cuenta paciente.
////     * @param ctaPac
////     */
////    public void setCtaPac(MEXMECtaPac ctaPac){
////        if(ctaPac != null){
////            //setEXME_CtaPac_ID(ctaPac.getEXME_CtaPac_ID());
////            m_CtaPac = ctaPac;
////        }
////    }
//	
////    /**
////	*  Obtenemos los productos de un movimiento en especifico
////	*
////	*  @param empresa la empresa con la que se logeo
////	*  @param organizacion la organizacion con la que se logeo
////	*  @return Una lista con los productos de las lineas de un movimiento
////	*/
////
////	public static List<String> getProdMovement(Properties ctx, int movimiento,
////			String trxName) throws Exception {
////
////		List<String> lista = new ArrayList<String>();
////
////		//proyectos por empresa y organizacion
////		
////		StringBuilder sql = new StringBuilder();
////		 sql.append("SELECT M_MovementLine.M_Product_ID FROM M_MovementLine ")
////		    .append("WHERE M_MovementLine.IsActive = 'Y' AND M_MovementLine.M_Movement_ID = ? ");
////
////		sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, sql.toString(), "M_MovementLine"));
////		
////		PreparedStatement pstmt = null;
////		ResultSet rs = null;
////
////		try {
////
////			pstmt = DB.prepareStatement(sql.toString(), null);
////			pstmt.setInt(1, movimiento);
////			rs = pstmt.executeQuery();
////
////			while (rs.next()){
////				lista.add(String.valueOf(rs.getLong("M_Product_ID")));
////			}
////
////		} catch (SQLException e) {
////			s_log.log(Level.SEVERE, "getProdMovement", e);
////			throw new Exception(e.getMessage());
////		} finally {
////			DB.close(rs, pstmt);
////            rs = null;
////            pstmt = null;
////        }
////
////		return lista;
////
////	}
//
////	/**
////	*  Obtenemos los almacenes (solicitante y resurtido)
////	*
////	*  @param movementID el identificador del movimiento
////	*  @return Un resultset con los almacenes del movimiento
////	*/
////
////	public static List<LabelValueBean> getAlmacenes(Properties ctx, int movementID, 
////			String trxName) throws Exception {
////
////		//lista
////		List<LabelValueBean> lista = null;
////		
////		//movimientos por id de movimiento
////		StringBuilder sql = new StringBuilder();
////		 sql.append(" SELECT DISTINCT a1.M_Warehouse_ID AS Almacen, ")
////		    .append( " a2.M_Warehouse_ID AS AlmacenTo, ") 
////		    .append(" w1.Name AS AlmacenN, ") 
////		    .append(" w2.Name AS AlmacenToN ") 
////		    .append(" FROM M_MovementLine ")
////		    .append( " INNER JOIN M_Locator a1 ON M_MovementLine.M_Locator_ID = a1.M_Locator_ID ")
////		    .append(" INNER JOIN M_Locator a2 ON M_MovementLine.M_LocatorTo_ID = a2.M_Locator_ID ")
////		    .append(" INNER JOIN M_Warehouse w1 ON w1.M_Warehouse_ID = a1.M_Warehouse_ID ")
////		    .append(" INNER JOIN M_Warehouse w2 ON w2.M_Warehouse_ID = a2.M_Warehouse_ID ")
////		    .append(" WHERE M_MovementLine.M_Movement_ID = ? AND M_MovementLine.IsActive = 'Y'");
////		 
////		 
////		sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, sql.toString() , "M_MovementLine"));
////		
////		PreparedStatement pstmt = null;
////		ResultSet rs = null;
////		try {
////
////			pstmt = DB.prepareStatement(sql.toString(), null);
////			pstmt.setInt(1, movementID);
////			rs = pstmt.executeQuery();
////			
////			if(rs.next()){
////				
////				lista=new ArrayList<LabelValueBean>();
////				lista.add(new LabelValueBean(rs.getObject("AlmacenN").toString(),rs.getObject("Almacen").toString()));
////				lista.add(new LabelValueBean(rs.getObject("AlmacenToN").toString(),rs.getObject("AlmacenTo").toString()));
////			}
////			
////            
////        } catch (SQLException e) {
////            s_log.log(Level.SEVERE, "getAlmacenes (" + sql + ")", e);
////            throw e;
////        } finally {
////        	DB.close(rs, pstmt);
////            rs = null;
////            pstmt = null;
////        }
////
////		return lista;
////
////	}
//	
////	/**
////	*  Obtenemos el detalle de los movimientos pendientes de surtir
////	*
////	*  @param movementID el id del movimiento a surtir
////	*  @return Una lista con el detalle de los movimientos pendientes de surtir
////	*/
////
////	public static List<MovementLine> getMovementLine(Properties ctx, int movementID, String trxName) throws Exception {
////		
////        List<MovementLine> lista = new ArrayList<MovementLine>();
////        StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
////		PreparedStatement pstmt = null; 
////		ResultSet rs = null;
////		
////		try {
////		    //movimientos por empresa, organizacion y almacen (localizado)
////            sql.append(" SELECT M_MovementLine.Line, M_MovementLine.M_Movement_ID, M_MovementLine.Description, M_MovementLine.OriginalQty, ")
////                .append(" p.Value, p.Name, p.M_Product_ID, u.Name Udm, M_MovementLine.NumSerie ")
////                .append(", cp.EXME_CtaPac_ID, cp.DocumentNo ")
////                .append(" FROM M_MovementLine ")
////                .append(" INNER JOIN M_Product p ON M_MovementLine.M_Product_ID = p.M_Product_ID ")
////                .append(" INNER JOIN C_Uom u ON p.C_Uom_ID = u.C_Uom_ID ")
////                .append(" LEFT  JOIN EXME_CtaPac cp ON M_MovementLine.EXME_CtaPac_ID = cp.EXME_CtaPac_ID ")
////                .append(" WHERE M_MovementLine.M_Movement_ID = ? AND ")
////                .append(" M_MovementLine.IsActive = 'Y' AND p.IsActive = 'Y'");
////                
////            sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, sql.toString(), "M_MovementLine"));
////                
////            sql.append(" ORDER BY M_MovementLine.Line");
////
////			pstmt = DB.prepareStatement(sql.toString(), null);
////			pstmt.setInt(1, movementID);
////			rs = pstmt.executeQuery(); 
////
////			while (rs.next()){
////
////				MovementLine surtidoDet = new MovementLine();
////				surtidoDet.setLine(rs.getLong("Line"));
////				surtidoDet.setProductID(rs.getLong("M_Product_ID"));
////				surtidoDet.setDescription(rs.getString("Description"));
////				surtidoDet.setValue(rs.getString("Value"));
////				surtidoDet.setProdValue(rs.getString("Value"));
////				surtidoDet.setProdName(rs.getString("Name"));
////				surtidoDet.setUomName(rs.getString("Udm"));
////				surtidoDet.setOriginalQty(rs.getLong("OriginalQty"));
////				surtidoDet.setTargetQty(surtidoDet.getOriginalQty());
////				surtidoDet.setSerie(rs.getString("NumSerie"));//Se agrega el numero de serie
////				surtidoDet.setCtaPacID(rs.getInt("EXME_CtaPac_ID"));//Se agrega el numero de serie
////				surtidoDet.setCtaPacValue(rs.getString("DocumentNo"));
////				lista.add(surtidoDet);
////			}
////
////        } catch (SQLException e) {
////            s_log.log(Level.SEVERE, "getMovementLine (" + sql + ")", e);
////            throw e;
////        } finally {
////        	DB.close(rs, pstmt);
////            rs = null;
////            pstmt = null;
////        }
////		return lista;
////
////	}
////	
//	/**
//	 * Obtenemos el detalle de los movimientos a preparar
//	 * 
//	 * @param movementID
//	 *            El id del movimiento a preparar
//	 * @return Una lista con el detalle de los movimientos a preparar
//	 */
//
//	public static List<MovementLine> getMovementLinePrep(Properties ctx, int movementID, String trxName) throws Exception {
//		List<MovementLine> lista = new ArrayList<MovementLine>();
//		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//
//		try {
//			sql.append("SELECT M_MovementLine.Line, M_MovementLine.OriginalQty, p.Value, p.Name, u.Name Udm FROM M_MovementLine ")
//			.append("INNER JOIN M_Product p ON M_MovementLine.M_Product_ID = p.M_Product_ID ")
//			.append("INNER JOIN C_Uom u ON p.C_Uom_ID = u.C_Uom_ID ")
//			.append("WHERE M_MovementLine.M_Movement_ID = ? AND M_MovementLine.IsActive = 'Y' AND p.IsActive = 'Y' ")
//			.append("ORDER BY M_MovementLine.Line");
//
//			pstmt = DB.prepareStatement(sql.toString(), null);
//			pstmt.setInt(1, movementID);
//			rs = pstmt.executeQuery();
//
//			while (rs.next()) {
//				MovementLine movementDet = new MovementLine();
//				movementDet.setLine(rs.getLong("Line"));
//				movementDet.setOriginalQty(rs.getLong("OriginalQty"));
//				movementDet.setProdValue(rs.getString("Value"));
//				movementDet.setProdName(rs.getString("Name"));
//				movementDet.setUomName(rs.getString("Udm"));
//				lista.add(movementDet);
//			}
//
//		} catch (SQLException e) {
//			s_log.log(Level.SEVERE, "getMovementLinePrep (" + sql + ")", e);
//			throw e;
//		} finally {
//			DB.close(rs, pstmt);
//			rs = null;
//			pstmt = null;
//		}
//		return lista;
//	}
//	
//    /**
//	*  Verificamos si la cuenta tiene al menos 1 linea 
//	*
//	*  @return True si tiene al menos 1 linea, false si no.
//	*/
//
//	public static boolean getForCtaPacId(Properties ctx, int ctaPacId, String trxName) {
////		boolean retValue = true;
////		StringBuffer sql = new StringBuffer("SELECT * FROM M_Movement ")
////		                   .append(" WHERE M_Movement.isActive = 'Y' AND M_Movement.EXME_CtaPac_ID = ").append(ctaPacId);
////
////		sql = new StringBuffer(MEXMELookupInfo.addAccessLevelSQL(ctx, sql.toString(), "M_Movement"));
////		
////		PreparedStatement pstmt = null;
////		ResultSet rs = null;
////
////		try {
////
////			pstmt = DB.prepareStatement(sql.toString(), null);
////			rs = pstmt.executeQuery();
////
////			if (!rs.next()){
////				retValue = false;
////			}
////
////			
////		} catch (SQLException e) {
////			s_log.log(Level.SEVERE, "getProdMovement", e);
////			throw new Exception(e.getMessage());
////		} finally {
////			DB.close(rs, pstmt);
////            rs = null;
////            pstmt = null;
////        }
//
//		return new Query(ctx, Table_Name, " EXME_CtaPac_ID=? ", trxName)//
//		.setOnlyActiveRecords(true)//
//		.addAccessLevelSQL(true)//
//		.setParameters(ctaPacId)//
//		.firstId() > 0
//		;
//
//	}
//    
////    /**
////     * 
////     * @param ctx
////     * @param documentNo
////     * @param trxName
////     * @return
////     */
////    public static boolean tienePedOServPendientes(Properties ctx, int documentNo, String trxName) {
////        boolean retValue = false;
////        
////        StringBuilder sql = new StringBuilder(" select EXME_CtaPac.documentno as ctapac  " )
////                          .append(" from m_movement m ")
////                          .append(" left join m_movementline ml on (ml.m_movement_id = m.m_movement_id) ")
////                          .append(" left join exme_ctapac on (EXME_CtaPac.exme_ctapac_id = m.exme_ctapac_id) ")
////                          .append(" left join m_locator loc1 on (loc1.m_locator_id = ml.m_locator_id) ")
////                          .append(" left join m_locator loc2 on (loc2.m_locator_id = ml.m_locatorto_id) ")
////                          .append(" left join m_warehouse w1 on (w1.m_warehouse_id = loc1.m_warehouse_id) ")
////                          .append(" left join m_warehouse w2 on (w2.m_warehouse_id = loc2.m_warehouse_id) ")
////                          .append(" left join ad_user u on (u.ad_user_id = m.createdby) ")
////                          .append(" where EXME_CtaPac.documentno = ? ")
////                          //.append(" and cta.ad_client_id = ? ")
////                          //.append(" and cta.ad_org_id = ? ")
////                          .append(" and m.docstatus in ('IP', 'DR') ");
////
////        sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, sql.toString(), "EXME_CtaPac"));
////        
////                           sql.append(" union ") 
////                              .append(" select EXME_CtaPac.documentno as ctapac ") 
////                              .append(" from EXME_ActPacienteINDH actH ") 
////                              .append(" left join EXME_ActPacienteInd ind on (ind.exme_actpacienteindh_id = acth.exme_actpacienteindh_id) ") 
////                              .append(" left join exme_ctapac on (EXME_CtaPac.exme_ctapac_id = actH.exme_ctapac_id) ") 
////                              .append(" left join m_warehouse w1 on (w1.m_warehouse_id = acth.m_warehouse_id) ") 
////                              .append(" left join m_warehouse w2 on (w2.m_warehouse_id = acth.m_warehouse_sol_id) ") 
////                              .append(" left join ad_user u on (u.ad_user_id = actH.createdby) ") 
////                              .append(" where EXME_CtaPac.documentno = ?")
////                              //.append(" and cta.ad_client_id = ? ")
////                              //.append(" and cta.ad_org_id = ? ")
////                              .append(" and actH.docstatus in ('IP', 'DR') ");
////        
////        sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, sql.toString(), "EXME_CtaPac"));
////
////        
////        PreparedStatement pstmt = null;
////        ResultSet rs = null;
////
////        try {
////
////            pstmt = DB.prepareStatement(sql.toString(), null);
////            pstmt.setInt(1, documentNo);
////            //pstmt.setInt(2, Env.getContextAsInt(ctx, "#AD_Client_ID"));
////            //pstmt.setInt(3, Env.getContextAsInt(ctx, "#AD_Org_ID"));
////            pstmt.setInt(2, documentNo);
////            //pstmt.setInt(5, Env.getContextAsInt(ctx, "#AD_Client_ID"));
////            //pstmt.setInt(6, Env.getContextAsInt(ctx, "#AD_Org_ID"));
////            rs = pstmt.executeQuery();
////
////            if (rs.next()){
////                retValue = true;
////            }
////
////        } catch (SQLException e) {
////            s_log.log(Level.SEVERE, "getProdMovement", e);
////        } finally {
////        	DB.close(rs, pstmt);
////            rs = null;
////            pstmt = null;
////        }
////
////        return retValue;
////    }
//
////    /**
////     * Obtenemos los movimientos solicitados
////     * 
////    *  @param empresa la empresa con la que se logeo
////    *  @param organizacion la organizacion con la que se logeo
////    *  @param almacen el almacen solicitante
////    *  @param status el status del documento
////    *  @return Una lista con los movimientos pendientes de surtir
////    */
////    public static List<MMovement> getMMovements(Properties ctx, int EXME_CtaPac_ID, String trxName)
////            throws Exception {
////        List<MMovement> lista = new ArrayList<MMovement>();
////
////        StringBuilder sql = new StringBuilder("SELECT m_movement.* ")
////            .append("FROM M_Movement ")
////            .append("WHERE M_Movement.DocStatus IN ('DR', 'IP', 'CO') ")
////            .append("AND M_Movement.EXME_CtaPac_ID = ? AND M_Movement.IsActive = 'Y' ");
////        
////        sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, sql.toString(), "M_Movement"));
////        
////        PreparedStatement pstmt = null;
////        ResultSet rs = null;
////        try {
////            pstmt = DB.prepareStatement(sql.toString(), trxName);
////            pstmt.setInt(1, EXME_CtaPac_ID);
////            rs = pstmt.executeQuery();
////         
////            while (rs.next()) {
////                MMovement mov = new MMovement(ctx,rs,trxName);
////                lista.add(mov);
////            }
////        } catch (Exception e) {
////            s_log.log(Level.SEVERE, sql.toString(), e);
////            throw new Exception("error.getMovement");
////        } finally {
////        	DB.close(rs, pstmt);
////        }
////
////        return lista;
////    } 
////    
//    /**
//     * 
//     * @param pac
//     * @param trxName
//     * @param ctx
//     * @param numSerie
//     * @param devoluciones
//     * @param M_Warehouse_ID
//     * @throws Exception
//     */
//    public void calculoDevol(MEXMECtaPac pac, String trxName, Properties ctx, 
//    		String numSerie, MCtaPacDet devoluciones, int M_Warehouse_ID) throws Exception {
//    	
//    	try {
//    		
//    		// valor maximo 0 el valor minimo es la cant a devolver
//    		double qtyEnDevolucion = devoluciones.getQtyDelivered().abs().doubleValue();
//    		
//    		// lineas de devoluciones ... No contamos con las facturadas ni con
//    		// las que tengan qtyDelivered cero puesto que pueden ser cargos devueltos
//    		
//    		MCtaPacDet[] cargos = MCtaPacDet.getLineasParaDevol(ctx, numSerie,
//    				pac.getEXME_CtaPac_ID(), 0, devoluciones.getM_Product_ID(),
//    				devoluciones.getC_UOM_ID(), null, null); //rsolorzano 31032009 se envia null en transacci�n
//    		
//    		for (int j = 0; j < cargos.length; j++) {
//    			
//    			// el valor maximo es la cant que se cargo el valor minimo
//    			// es 0
//    			double qtyEnCargo = cargos[j].getQtyDelivered().doubleValue();
//    			
//    			/**
//    			 * Se afectara del cargo 
//    			 * QtyDelivered para que en lo subsecuente ya no se tome encuenta para devolver si ya se devolvio
//    			 * QtyPendiente puesto que QtyPendiente deriva de QtyDelivered
//    			 * QtyPaqute en ultima instancia se vera afectada
//    			 */
//    			
//    			
//    			// Hacemos el comparativo de lineas. mismo producto misma
//    			// udm y mismo almacen
//    			// que existan cantidades para hacer calculos
//    			if (devoluciones.getM_Product_ID() == cargos[j].getM_Product_ID()
//    					&& devoluciones.getC_UOM_ID() == cargos[j].getC_UOM_ID()
//    					&& qtyEnDevolucion > 0.0
//    					&& qtyEnCargo > 0.0) {
//    				
//    				double qtyDevolver = qtyEnCargo - qtyEnDevolucion;
//    				
//    				// si qtyDevolver es positivo es que aun queda cantidad
//    				// de cargo
//    				// si qtyDevolver es negativo es que se acabo el cargo y
//    				// hay devolucion pendiente
//    				// si qtyDevolver es cero se acabo el cargo y la
//    				// devolucion
//    				if (qtyDevolver > 0.0) {
//    					//Actualizamos la cantidad que tiene el cargo
//    					cargos[j].setQtyDelivered(new BigDecimal(qtyDevolver));
//    					
//    					//Actualizamos la cantidad pendiente
//    					cargos[j].setQtyPendiente(new BigDecimal(qtyDevolver));
//    					qtyEnDevolucion = 0.0;
//    					cargos[j].setSeDevolvio(false);
//    				}
//    				
//    				
//    				if (qtyDevolver <= 0.0 ) {
//    					//Actualizamos la cantidad que tiene el cargo
//    					cargos[j].setQtyDelivered(Env.ZERO);
//    					cargos[j].setQtyPendiente(Env.ZERO);
//    					qtyEnDevolucion = qtyDevolver*-1;//-5*-1 � 0*-1
//    					cargos[j].setSeDevolvio(true);
//    				}
//    				
//    				
//					cargos[j].setQtyPaquete(Env.ZERO);
//					cargos[j].setFreightAmt(Env.ZERO);
//					
//				
//    				
//    			}// fin comparativo de cargos y devoluciones =
//    			
//    			devoluciones.setM_MovementLine_ID(cargos[j].getM_MovementLine_ID());//La devolucion tambien debe tener si relacion
//    			devoluciones.setLineNetAmt(Env.ZERO);
//    			cargos[j].setLineNetAmt(cargos[j].getPriceActual().multiply(cargos[j].getQtyDelivered()));
//    			
//    			if (!devoluciones.save(trxName) || !cargos[j].save(trxName)) {
//    				s_log.severe(Msg.getMsg(ctx,"ErrCalculoDevol"));
//    				throw new Exception(Msg.getMsg(ctx,"ErrCalculoDevol"));
//    			}
//    			
//    			if(qtyEnDevolucion == 0.0)
//    				break;
//    		}// fin for cargos
//    		
//    		devoluciones = null;
//    		cargos = null;
//    	} catch (Exception e) {
//    		s_log.log(Level.SEVERE,
//    		"error getLineasSinDevol ha producido una excepcion");
//    		throw new Exception(Msg.getMsg(ctx, "ErrCalculoDevol"));
//    	}
//    }
//    
//    /**
//     * 
//     * @param ctx
//     * @param progQuiroId
//     * @param trxName
//     * @return
//     */
//    public static List<MEXMEMovement> getFromProgQuirofano(Properties ctx, int progQuiroId, String trxName) {
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		List<MEXMEMovement> lst = new ArrayList<MEXMEMovement>();
//		try {
//			StringBuilder sql = new StringBuilder();
//			sql.append(" select * from M_Movement where EXME_ProgQuiro_ID = ? and DocStatus not in (?) ");
//			sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", MEXMEMovement.Table_Name));
//			
//			pstmt = DB.prepareStatement(sql.toString(), trxName);
//			pstmt.setInt(1, progQuiroId);
//			pstmt.setString(2, DOCSTATUS_Voided);
//
//			rs = pstmt.executeQuery();
//
//			while (rs.next()) {
//				lst.add(new MEXMEMovement(ctx, rs, trxName));
//			}
//		} catch (Exception e) {
//			s_log.log(Level.SEVERE, null, e);
//		} finally {
//			DB.close(rs, pstmt);
//			rs = null;
//			pstmt = null;
//		}
//		return lst;
//	}
//    
//	/**
//	 * Cancela el pedido que se le indica
//	 * @param movementID pedido a cancelar
//	 * @throws SQLException 
//	 */
//	public static boolean cancelPedido(Properties ctx, int movementID, String trxName) throws SQLException {
//		boolean isCanceled = false;
//		MMovement mov = new MMovement(ctx, movementID, trxName);
//		MMovementLine[] ml = mov.getLines(true);
//
//		for (int i = 0; i < ml.length; i++) {
//			MMovementLine linea = new MMovementLine(ctx, (int) ml[i].getM_MovementLine_ID(), null);
//			MMovementLineConfirm confirm = MMovementLineConfirm.getFromMovLine(ctx, linea.getM_MovementLine_ID(), null);
//
//			if (confirm != null) {
//				MStorage.add(ctx, linea.getM_LocatorTo_ID(), linea.getM_Locator_ID(), (int) linea.getM_Product_ID(), linea.getM_AttributeSetInstance_ID(),
//						linea.getM_AttributeSetInstanceTo_ID(), Env.ZERO, confirm.getTargetQty().negate(), Env.ZERO, trxName);
//			}
//		}
//		mov.setDocStatus(MMovement.DOCSTATUS_Voided);
//		if (mov.save()) {
//			isCanceled = true;
//		} else {
//			isCanceled = false;
//		}
//		return isCanceled;
//	}
//    
//	/**
//	 * Valida si hay existencias
//	 * 
//	 * @param warehouse : Almacen en que se requiere ver si hay existencias
//	 * @param productId : Producto a verificar
//	 * @param mAttribSetInstID : Atributo del producto
//	 * @param targetQty : Cantidad a mover del inventario
//	 * @return true : Hay existencias 
//	 */
//	public static boolean inStock(final Properties ctx, final int warehouse, final int productId, final int mAttribSetInstID, final BigDecimal targetQty) {
//		boolean inStock = true;
//		// Verifica que me maneje existencias a nivel organización
//		if(MEXMEMejoras.get(ctx).isControlExistencias()){
//			// Verifica la existencia del producto en el almacen
//			BigDecimal QtyAvailable = MStorage.getQtyAvailable(warehouse,
//					productId, mAttribSetInstID, null);
//
//			if (QtyAvailable == null) {
//				QtyAvailable = Env.ZERO;
//			}
//			if (targetQty.compareTo(QtyAvailable) > 0) {
//				s_log.saveError(
//						"SaveError",
//						"La cantidad de uso interno es mayor que la cantidad disponible para el almacen ("
//								+ QtyAvailable + ")");
//				inStock = false;
//			}
//		}
//		return inStock;
//	}
//	
//	
//	
//	/**
//	 * PRepara las lineas para crear los cargos
//	 */
//	public HashMap<Integer, Bean4String> createAllCharges(){
//		List<MMovementLine> lines = getLines();
//		HashMap<Integer, Bean4String> map = new HashMap<Integer, Bean4String>();
//		
//		for (MMovementLine line : lines) {
//			BigDecimal qty = BigDecimal.ZERO;
//			if(line.getOp_Uom() == 0 || line.getOp_Uom() == line.getProduct().getC_UOM_ID()){
//				qty = line.getConfirmedQty();
//			}else if(line.getProduct().getC_UOMVolume_ID() == line.getOp_Uom()){
//				qty =line.getConfirmedQty_Vol();
//			}
//			
//			map.put(line.getM_Product_ID(),new  Bean4String(qty, line.getOp_Uom(), null));
//		}
//		return map;
//	}
//	
//	/**
//	 * Generar cargos de la cuenta paciente
//	 * si no se logra hacer el cargo y existe destino 
//	 * destino queda como invalido
//	 * @param destino
//	 * @param map
//	 * @return
//	 */
//	public boolean saveCharges(/*MMovement origen,*/ MMovement destino, HashMap<Integer, Bean4String> map) {
//		boolean success = true;
//		try {
//			int warehouseId = new MLocator(Env.getCtx(), MMovement.getLocatorTo(Env.getCtx(), getM_Movement_ID(), null), null).getM_Warehouse_ID();
//			List<MCtaPacDet> lst = MCtaPacDet.createCharge(Env.getCtx(), getEXME_CtaPac_ID(), warehouseId, Env.getEXME_EstServ_ID(Env.getCtx()), map,null,null);
//			if (lst.isEmpty()) {
////				movementId = Integer.parseInt(getParameter("returnID"));
//				success = false;
//				if (destino != null) {
//					destino.voidIt();
//				}
////			} else {
////				buttonBar.getSaveButton().setDisabled(true);
////				buttonBar.getPrintButton().setDisabled(false);
////				Utils.info(Utils.getLabel("msj.cargosGenerados"));
//			}
//		} catch (Exception e) {
////			if (e instanceof ExpertException) {
////				Utils.error(e.getMessage(), e);
////			} else {
//				s_log.log(Level.SEVERE, null, e);
////			}
//			if (destino != null) {
//				destino.voidIt();
//			}
//			
////			try {
////				movementId = Integer.parseInt(getParameter("returnID"));
////			} catch (NumberFormatException ex) {
////				s_log.log(Level.SEVERE, null, ex);
////				movementId = 0;
////			}
//			success = false;
//		}
//		return success;
//	}
//	
//
//	/**
//	 * Charolas
//	 * @param nuevoMovId
//	 * @param isDevol
//	 * @param isSterilization
//	 * @return
//	 */
//	public  HashMap<Integer, Bean4String> createDevolCharges(final int nuevoMovId, final boolean isDevol ,final boolean isSterilization) {
//		//
//		final MMovement destino = new MMovement(Env.getCtx(), nuevoMovId, null);
//		final List<MMovementLine> linesOrig = getLines();
//		final List<MMovementLine> linesNew = destino.getLines();
//		final HashMap<Integer, Bean4String> map = new HashMap<Integer, Bean4String>();
//		
//		for (MMovementLine line : linesOrig) {
//			
//			boolean encontrado = false;
//			
//			// Iterar la lista del nuevo
//			for (MMovementLine line2 : linesNew) {
//				
//				if (line.getM_Product_ID() == line2.getM_Product_ID()) {
//					
//					BigDecimal num = BigDecimal.ZERO;
//					if(line.getOp_Uom() == 0 || line.getOp_Uom() == line.getProduct().getC_UOM_ID()){
//						num = line.getConfirmedQty().subtract(line2.getTargetQty());
//					}else if(line.getProduct().getC_UOMVolume_ID() == line.getOp_Uom()){
//						num = line.getConfirmedQty_Vol().subtract(line2.getTargetQty_Vol());
//					}
//					
//					//BigDecimal num = line.getConfirmedQty().subtract(line2.getTargetQty());
//					//Cuando se hace devolucion de productos, se cancela el cargo
//					if (isDevol && !isSterilization) {
//						num = num.negate();
//					}
//					if (num.intValue() > 0) {
//						
//						map.put(line.getM_Product_ID(), new Bean4String(num, line.getOp_Uom(), null));
//					}
//					encontrado = true;
//					break;
//				}
//			}
//			
//			// si hay una coincidencia
//			if(!encontrado){
//				BigDecimal qty = BigDecimal.ZERO;
//				if(line.getOp_Uom() == 0 || line.getOp_Uom() == line.getProduct().getC_UOM_ID()){
//					qty = line.getConfirmedQty();
//				}else if(line.getProduct().getC_UOMVolume_ID() == line.getOp_Uom()){
//					qty =line.getConfirmedQty_Vol();
//				}
//				map.put(line.getM_Product_ID(), new Bean4String(qty, line.getOp_Uom(),null));
//			}
//			
//		}
//
//		return map;
//	}
//	
//	
//
//	/**
//	 *  Obtenemos el encabezado de los movimientos pendientes de surtir
//	 *
//	 *  @param movementID el movimiento pendiente
//	 *  @return Un resultset con los movimientos pendientes de surtir
//	 */
//	public static MEXMEMovement getMEXMEMov(Properties ctx, int movementID, String trxName) 
//	throws SQLException {
//		//movimientos por id de movimiento
//		StringBuilder sql = new StringBuilder("SELECT M_Movement.*, a.M_Warehouse_ID as almSol, M_Movement.soli_user_id, ")
//		    .append("surte.M_Warehouse_ID AS almSurt ")
//		    .append("FROM M_Movement ")
//		    .append("INNER JOIN M_MovementLine l ON M_Movement.M_Movement_ID = l.M_Movement_ID ")
//		    .append("INNER JOIN M_Locator ll ON l.M_Locator_ID = ll.M_Locator_ID ") //surte
//		    .append("INNER JOIN M_Locator ls ON l.M_LocatorTo_ID = ls.M_Locator_ID ") //solicitante
//		    .append("INNER JOIN M_Warehouse a ON ls.M_Warehouse_ID = a.M_Warehouse_ID ")
//		    .append("INNER JOIN M_Warehouse surte ON ll.M_Warehouse_ID = surte.M_Warehouse_ID ")
//		    .append("WHERE M_Movement.M_Movement_ID = ? AND ")
//		    .append("M_Movement.IsActive = 'Y'")
//		    .append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", "M_Movement"));	
//
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		MEXMEMovement mov = null;
//		
//		try {
//			//Echense otro de estos
//			pstmt = DB.prepareStatement(sql.toString(), trxName);
//			pstmt.setInt(1, movementID);
//			rs = pstmt.executeQuery();
//			
//			if (rs.next()) {
//				mov = new MEXMEMovement(ctx, rs, trxName);
//				mov.setM_AlmSolic(new MWarehouse(ctx, rs.getInt("almSol"), trxName));
//				mov.setM_AlmSurt(new MWarehouse(ctx, rs.getInt("almSurt"), trxName));
//				mov.setM_Usuario(new MUser(ctx, rs.getInt("soli_user_id"), trxName));
//				
//				if (mov.getEXME_CtaPac_ID() > 0) {
//					mov.setM_CtaPac(new MEXMECtaPac(ctx, mov.getEXME_CtaPac_ID(), trxName));
//				}
//			}
//		} catch (SQLException e) {
//			s_log.log(Level.SEVERE, sql + "   movementID: " + movementID, e);
//		} finally {
//			DB.close(rs, pstmt);
//		}
//		
//		return mov;
//	}
//	
//	/**
//	 * copiar lineas
//	 * @param innerList
//	 * @return
//	 */
//	public static List<MEXMEMovementLine> getCopyMEXMEMovementList(final Properties ctx , final List<MEXMEMovementLine> innerList){
//		final List<MEXMEMovementLine> list = new ArrayList<MEXMEMovementLine>();
//		for (MEXMEMovementLine oldMov: innerList) {
//			// nuevo registro que sera de la devolucion
//			final MEXMEMovementLine newMov = new MEXMEMovementLine(ctx, 0, null);
//			MEXMEMovementLine.copyValues(oldMov, newMov);
//
//			newMov.setOriginalQty(oldMov.getOriginalQty());
//			newMov.setTargetQty(oldMov.getTargetQty());
//
//			newMov.setOriginalQty_Vol(oldMov.getOriginalQty_Vol());
//			newMov.setTargetQty_Vol(oldMov.getTargetQty_Vol());
//
//			list.add(newMov);
//		}
//		return list;
//	}
//	
//	
//	/**
//	 * 
//	 * @param innerList
//	 * @param copy
//	 * @return
//	 */
//	public List<MovementLine> getMovementList(List innerList, List copy) {
//		List<MovementLine> list = new ArrayList<MovementLine>();
//		if (copy != null) {
//			list.addAll(copy);
//		} else {
//			for (Object obj : innerList) {
//				if (obj instanceof MovementLine) {
//					list.add((MovementLine) obj);
//				}
//			}
//		}
//		return list;
//	}
//	
//	public List<MMovementLine> getMMovementList(List innerList){
//		List<MMovementLine> list = new ArrayList<MMovementLine>();
//		for (Object obj : innerList) {
//			if (obj instanceof MMovementLine) {
//				list.add((MMovementLine) obj);
//			}
//		}
//		return list;
//	}
//	
//	public List<MEXMEMovementLine> getMEXMEMovementList(List innerList){
//		List<MEXMEMovementLine> list = new ArrayList<MEXMEMovementLine>();
//		for (Object obj : innerList) {
//			if (obj instanceof MMovementLine) {
//				list.add((MEXMEMovementLine) obj);
//			}
//		}
//		return list;
//	}
//		
//	public List<ConfirmaDetView> getConfirmaList(List innerList) {
//		List<ConfirmaDetView> list = new ArrayList<ConfirmaDetView>();
//		for (Object obj : innerList) {
//			if (obj instanceof ConfirmaDetView) {
//				list.add((ConfirmaDetView) obj);
//			}
//		}
//		return list;
//	}
//}
