/*
 * Derechos Reservados (c) a partir 2005 Expert Sistemas Computacionales, S. A. de C. V.
 * Sistema MedSys
 */
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.model.bpm.GetPrice;
import org.compiere.util.CLogger;
import org.compiere.util.Env;
import org.compiere.util.Utilerias;
import org.compiere.util.WebEnv;

/**
 * Acceso a la BD para el mantenimiento de traspasos entre almacenes
 * Tablas relacionadas: M_Movement y M_MovementLine
 * <p>
 * Modificado por: $Author: taniap $ <p>
 * Fecha: $Date: 2006/09/08 01:30:07 $ <p>
 *
 * @author Gisela Lee Gonzalez
 * @version $Revision: 1.1 $
 * @deprecated
 */
public class AlmacenesDB {

	   /** Static Logger               */
    private static CLogger      s_log = CLogger.getCLogger (AlmacenesDB.class);

	/**
	 * Formatea a fecha y hora (24) dd/MM/yyyy HH:mm
	 */ 
	public static SimpleDateFormat sdfFechaHora = new SimpleDateFormat("dd/MM/yyyy HH:mm");
	
	/**
	 * Crea un registro en movimiento de inventario
	 *
	 * @param  Bean de forma con la informacion a almacenar
	 * @throws SQLException si ocurre un error
	 * @return MMovement de nombre "mov"
	 */
	public static MMovement insertMovement(Properties ctx, String documentNo,
			boolean devol, int projectID, int activity, int campaign,
			int orgTrx, int user1, int user2, int ctaPac, int docType,
			String description, String trxName, String almacen) 
	throws SQLException {

		s_log.log(Level.INFO,"***** Insertando en M_Movement ***** ");

		MMovement mov = new MMovement(ctx, 0, trxName);

		mov.setIsDevol(devol); 
//		mov.setMovementDate(DB.getTimestampForOrg(ctx));
//		mov.setProcessing(false);
		mov.setC_Project_ID(projectID);
		mov.setC_Activity_ID(activity);
		mov.setC_Campaign_ID(campaign);
		mov.setAD_OrgTrx_ID(orgTrx);
		mov.setUser1_ID(user1);
		mov.setUser2_ID(user2);
//		mov.setDocAction(MMovement.ACTION_Complete);
//		mov.setDocStatus(MMovement.STATUS_Drafted);
		if(docType > 0) {
			mov.setC_DocType_ID(docType);
		}
		mov.setApprovalAmt(Env.ZERO);
		mov.setEXME_CtaPac_ID(ctaPac);
		if (almacen!= null){
			mov.setAlmacen(almacen);
		}
		mov.setDescription(description);
//		mov.setIsDevol(devol);

		if(!mov.save(trxName)){
			s_log.log(Level.SEVERE, "AlmacenesDB insertMovement .- Movimiento" + mov.getM_Movement_ID() 
					+ " Estatus " + mov.getDocStatus() + " Accion " + mov.getDocAction());
			throw new SQLException("error.traspasos.noInsertMov");

		}

		s_log.log(Level.FINEST, "AlmacenesDB insertMovement .- Movimiento" + mov.getM_Movement_ID() 
				+ " Estatus " + mov.getDocStatus() + " Accion " + mov.getDocAction());
		return mov;

	}



//	/**
//	 * Crea un registro por cada linea de movimiento entre almacenes
//	 *
//	 * @param Hashtable con objetos tipo MovementLine que representan a una linea
//	 * @throws SQLException si ocurre un error
//	 * @return ActionErorrs (Noelia)
//	 */
//
//	public static ActionErrors insertMovementLine(Properties ctx, List<MovementLine> htLineas, 
//			boolean devol, int movementID, int localizadorID, 
//			int locatorToID, int EXME_CtaPac_ID, String trxName) throws Exception {
//		s_log.log(Level.INFO,"***** Insertando en M_MovementLine ***** ");
//	
//		ActionErrors errors = new ActionErrors();
//		
//		try {
//			int localizador = 0;
//			int n = 10;
//			//ahora guardamos el detalle del traspaso entre almacenes
//			for (int i = 0; i < htLineas.size(); i++) {
//				//recuperamos los valores de la forma
//				MovementLine lineas = htLineas.get(i);
//				
//				MMovementLine line = new MMovementLine(ctx, 0, trxName);
//	
//				/*Validar que si el producto elegido utiliza numero de serie
//				 *sera necesario hacer que la cantidad maxima de producto sea 
//				 *en cantidad uno.
//				 */			
//                MProduct prod = new MProduct(ctx, (int)lineas.getProductID(), trxName);
//				if(prod != null && prod.isNumSerie() && EXME_CtaPac_ID>0){
//					if(lineas.getOriginalQty()!=1) {
//						errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.traspasos.cantidadMaxEsUno", prod.getName()));
//						return errors;
//					}
//				}
//                
//				if(lineas.getOriginalQty()!=0) {
//					line.setDescription(lineas.getDescription());
//					line.setLine(n);
//					line.setM_Movement_ID(movementID);
//					line.setM_Product_ID((int)lineas.getProductID());
//	                line.setOriginalQty(new BigDecimal(lineas.getOriginalQty()));
//					line.setNumSerie(lineas.getSerie()); //Numero de serie
//					/*line.setM_MovementLine_ID();
//					line.setConfirmedQty();
//					line.setM_AttributeSetInstance_ID();
//					line.setM_AttributeSetInstanceTo_ID();
//					line.setProcessed();
//					line.setScrappedQty();*/
//				
//					//buscamos el localizador de default del almacen resurtido
//					localizador = MLocator.getLocatorID(ctx, localizadorID, trxName); 
//					if(devol)
//						//si es una devolucion invertir los localizadores
//						line.setM_LocatorTo_ID(localizador);
//					else
//						line.setM_Locator_ID(localizador);
//					//buscamos el localizador de default del almacen solicitante
//					localizador = MLocator.getLocatorID(ctx, locatorToID, trxName); 
//					if(devol)
//						//si es una devolucion invertir los localizadores
//						line.setM_Locator_ID(localizador);
//					else
//						line.setM_LocatorTo_ID(localizador);
//					
//					if(devol) {
//						line.setTargetQty(new BigDecimal(lineas.getOriginalQty()));
//						line.setMovementQty(new BigDecimal(lineas.getOriginalQty()));
//					} else {
//						line.setTargetQty(Env.ZERO);
//						line.setMovementQty(Env.ZERO);
//					}
//					line.setAD_OrgTrx_ID(lineas.getOrgTrx()); //OrgTrx .- Lama
//					
//					//inicio .- determinar el precio de lista para cada producto .-Lama
////	                String tipoarea = Env.getContext(ctx, "#TipoArea");
////	                String estserv = Env.getContext(ctx, "#EXME_EstServ_ID");
////	                Timestamp date = DB.getTimestampForOrg(ctx);//la fecha actual
////	                
////	                MEXMEEstServ m_estserv = null;
////	                //MProduct prod = null;
////	               
////	                m_estserv = new MEXMEEstServ(ctx, Integer.parseInt(estserv), trxName);
//	                //prod = new MProduct(ctx, (int)lineas.getProductID(), trxName);
//	                
//	                line.setLotInfo(lineas.getLoteInfo());//Lote Omar de la Rosa
//	                line.setM_AttributeSetInstance_ID(lineas.getM_AttributeSetInstance());
//	                line.setEXME_CtaPac_ID(lineas.getCtaPacID());//TTPR: Recetas Colectivas
//	                
//	                
//	                if(prod!=null){
////	                    int ctapac = new MMovement(ctx, movementID, trxName).getEXME_CtaPac_ID();
////	                    int pacID = 0;
////	                    if(String.valueOf(ctapac)!=null || ctapac > 0){
////	                        pacID = new MEXMECtaPac(ctx, ctapac, trxName).getEXME_Paciente_ID(); 
////	                    }
//	     /*               PreciosVenta.m_configPre =  MEXMEConfigPre.get(ctx, null); //No requieren transaccion
//	                    PreciosVenta.m_Paciente =   new MEXMEPaciente(ctx, pacID, null); //No requieren transaccion
//	                    PreciosVenta.m_ConfigEC =  MConfigEC.get(ctx, null); //No requieren transaccion
//	       */             
//	                    
//	                    MPrecios precios = PreciosVenta.precioProdVtaMovement(ctx
////	                            tipoarea,prod.getM_Product_ID(), 
////	                            new BigDecimal(lineas.getOriginalQty()), 
////	                            prod.getC_UOM_ID(),Constantes.PVH, 
////	                            0,0,locatorToID, 
////	                            localizadorID,m_estserv.getEXME_Area_ID(),
////	                            date,true,
//	                    		, line
//	                            , trxName);
//	                    if(precios!=null)
//	                        line.setPriceList(precios.getPriceStd()); 
//	                }
//					//fin cambios .-Lama
//	                
//	                if(!line.save(trxName)){
//						errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.traspasos.noInsertMovLine"));
//						return errors;
//	                }else{
//	                	String retValue = null;
//	                	  if (devol){
//	                		  retValue = devAlmacenVirtual(ctx, line, trxName); 
//	                	  }else{
//	                		  retValue = solAlmacenVirtual(ctx, line, trxName);  
//	                	  }
//	                	if (retValue != null){
//	                		errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.traspasos.noInsertMovLine"));
//	                		break;
//	                	}
//	                	
//	                	//Se guarda la solicitud de la sengre en su propia tabla
//	                	MMovementLineB mlb = lineas.getMovementLineB(); 
//	                	if (mlb != null) {
//	                		mlb.setM_MovementLine_ID(line.getM_MovementLine_ID());
//	                		
//	                		//para prevenir problemas por precision de datos en la bd
//	                		mlb.setNeutrofilos(mlb.getNeutrofilos().setScale(3, BigDecimal.ROUND_HALF_UP));
//	                		mlb.setHemoglobina(mlb.getHemoglobina().setScale(3, BigDecimal.ROUND_HALF_UP));
//	                		mlb.setPlaquetas(mlb.getPlaquetas().setScale(3, BigDecimal.ROUND_HALF_UP));
//	                		mlb.setFibrinogeno(mlb.getFibrinogeno().setScale(3, BigDecimal.ROUND_HALF_UP));
//	                		
//	                		if(!mlb.save(trxName)){
//	    						errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.traspasos.noInsertMovLine"));
//	    						return errors;
//	                		}
//	                	}
//	                	;
//	                }
//	             	
//					n += 10;
//				}
//			}
//			
//		} catch (SQLException e) {
//			s_log.log(Level.SEVERE, "getHistorial", e);
//			errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.traspasos.noInsertMovLine"));
//			return errors;
//		}
//		
//		return errors;
//	}

	
	/**
	 * 
	 * @param ctx
	 * @param line
	 * @param trxName
	 * @return
	 */
	private static String devAlmacenVirtual(Properties ctx,
			MMovementLine line, String trxName) {
		
		String retValue = null;

		boolean esVirtual = false; 
		//LRHU. revisar si el objeto no viene null, se hace esta validacion ya que en solicitud de productos a consigna marcaba nullpointer exception. 
		if(MWarehouse.getFromLocator(ctx, line.getM_Locator_ID(), null) != null)
			esVirtual = MWarehouse.getFromLocator(ctx, line.getM_LocatorTo_ID(), null).isVirtual();
		
		if (esVirtual){
			try {
				//Liz de la Garza - Cambio del proceso para utilizarlo dentro del modelo de persistencia (para log de cambios)
				MDevolucion devolucion = new MDevolucion(ctx, 0, trxName);
				devolucion.setSM_Devolucion_ID(line.getM_MovementLine_ID());
				// FIXME: deberia solo usar el constructor
				devolucion.setDocumentNo(MMovement.get(ctx, line.getM_Movement_ID(), trxName).getDocumentNo());
				MEXMEConfigInt configInt = MEXMEConfigInt.get(ctx, null,null);		
				
				if (configInt != null && configInt.getInterfase_Inventarios().equalsIgnoreCase(MEXMEConfigInt.INTERFASE_INVENTARIOS_SIA)) {
					devolucion.setWHS_Dev_Value(MWarehouse.getFromLocator(ctx, line.getM_Locator_ID(), trxName).getDescription());
					devolucion.setWHS_Rec_Value(MWarehouse.getFromLocator(ctx, line.getM_LocatorTo_ID(), trxName).getDescription());
					devolucion.setProducto_Value(MProduct.get(ctx, line.getM_Product_ID()).getSKU());
				} else {
					devolucion.setWHS_Dev_Value(MWarehouse.getFromLocator(ctx, line.getM_Locator_ID(), trxName).getValue());
					devolucion.setWHS_Rec_Value(MWarehouse.getFromLocator(ctx, line.getM_LocatorTo_ID(), trxName).getValue());
					devolucion.setProducto_Value(String.valueOf(line.getM_Product_ID()));
				}
				devolucion.setCantidad(line.getOriginalQty());
				devolucion.setM_Movement_ID(line.getM_Movement_ID());
				devolucion.setM_MovementLine_ID(line.getM_MovementLine_ID());
				devolucion.setM_Product_ID(line.getM_Product_ID());
				devolucion.setUOM_Value(new MUOM(ctx, MProduct.get(ctx,line.getM_Product_ID()).getC_UOM_ID(),trxName).getName());
				devolucion.setTransferido(false);
				devolucion.setAD_Client_ID(line.getAD_Client_ID());
				devolucion.setAD_Org_ID(line.getAD_Org_ID());
				MAttributeSetInstance asi = new MAttributeSetInstance(ctx, line.getM_AttributeSetInstance_ID(), null);
				devolucion.setLot(asi.getLot());
				devolucion.setGuaranteeDate(asi.getGuaranteeDate());
				asi = null;
				
				if (!devolucion.save(trxName)) {
					retValue = "Error";
					s_log.log(Level.SEVERE, "devAlmacenVirtual()");
				}
				
				
				/*PreparedStatement psInsert = null;
				StringBuilder sqlUpdate = new StringBuilder("INSERT INTO  ");

        		
				sqlUpdate.append("SM_DEVOLUCION " )
				.append("(SM_DEVOLUCION_ID  ,DOCUMENTNO   , WHS_DEV_VALUE , WHS_REC_VALUE , " )
				.append(" PRODUCTO_VALUE    ,CANTIDAD     , CREATED       , M_MOVEMENT_ID , " )
				.append(" M_MOVEMENTLINE_ID ,M_PRODUCT_ID , UOM_VALUE     , TRANSFERIDO   , " )
				.append(" CREATEDBY, UPDATED, UPDATEDBY, Ad_CLIENT_ID, AD_ORG_ID, LOT, GUARANTEEDATE)" )
				.append("VALUES " )	                			
				.append("(?, ?, ?, ?, " )
				.append(" ?, ?, ?, ?, " )
				.append(" ?, ?, ?, ?, " )
				.append(" ?, ?, ?, ? , ?,?,? ) " );
				
				psInsert = DB.prepareStatement(sqlUpdate.toString(), trxName);
				
				psInsert.setInt(1 , line.getM_MovementLine_ID() );
				psInsert.setString(2 , MMovement.get(ctx, line.getM_Movement_ID(), trxName).getDocumentNo() );
				
				MEXMEConfigInt configInt = MEXMEConfigInt.get(ctx, null,null);				
				if (configInt != null && configInt.getInterfase_Inventarios().equalsIgnoreCase(X_EXME_ConfigInt.INTERFASE_INVENTARIOS_SIA)){
					psInsert.setString(3 , MWarehouse.getFromLocator(ctx, line.getM_Locator_ID(), trxName).getDescription());
					psInsert.setString(4 , MWarehouse.getFromLocator(ctx, line.getM_LocatorTo_ID(), trxName).getDescription());
					psInsert.setString(5 , MProduct.get(ctx, line.getM_Product_ID()).getSKU());
				}else { //Si no tiene interfaz configurada o tiene configurada cualquier otra interfaz diferente de SIA 
					psInsert.setString(3 , MWarehouse.getFromLocator(ctx, line.getM_Locator_ID(), trxName).getValue());
					psInsert.setString(4 , MWarehouse.getFromLocator(ctx, line.getM_LocatorTo_ID(), trxName).getValue());
					psInsert.setInt(5 , line.getM_Product_ID()) ;
				}
				configInt = null;
				
				psInsert.setBigDecimal(6 , line.getOriginalQty() );
				psInsert.setTimestamp(7 , line.getCreated() );
				psInsert.setInt(8 , line.getM_Movement_ID() );
				psInsert.setInt(9, line.getM_MovementLine_ID() );
				psInsert.setInt(10, line.getM_Product_ID());
				psInsert.setString(11,  new X_C_UOM(ctx, MProduct.get(ctx,line.getM_Product_ID()).getC_UOM_ID(),trxName).getName());
				psInsert.setString(12, "N");
				psInsert.setInt(13 , line.getCreatedBy() );
				psInsert.setTimestamp(14 , line.getUpdated() );
				psInsert.setInt(15 , line.getUpdatedBy() );
				psInsert.setInt(16 , line.getAD_Client_ID());
				psInsert.setInt(17 , line.getAD_Org_ID());
				//Lotes en Interfaz
				MAttributeSetInstance asi = new MAttributeSetInstance(ctx, line.getM_AttributeSetInstance_ID(), null);
				psInsert.setString(18 , asi.getLot());
				psInsert.setString(19, asi.getGuaranteeDate().toLocaleString().substring(0,10));
				
				asi = null;
				//Fin Lotes
				psInsert.executeUpdate();*/
				
				
				
			} catch (SQLException e) {
				if (WebEnv.DEBUG)
					e.printStackTrace();
				s_log.log(Level.SEVERE, "devAlmacenVirtual()");
				retValue = "Error";
			}
		}
		return retValue;
		
	}

	/**
	 * 
	 * @param ctx
	 * @param line
	 * @param trxName
	 * @return
	 */
	private static String solAlmacenVirtual(Properties ctx,
			MMovementLine line, String trxName) {

		String retValue = null;
		
		boolean esVirtual = false; 
		//LRHU. revisar si el objeto no viene null, se hace esta validacion ya que en solicitud de productos a consigna marcaba nullpointer exception.
		if(MWarehouse.getFromLocator(ctx, line.getM_Locator_ID(), null) != null)
			esVirtual = MWarehouse.getFromLocator(ctx, line.getM_Locator_ID(), null).isVirtual();
		
		if (esVirtual) {
			
			try {
				//Liz de la Garza - Cambio del proceso para utilizarlo dentro del modelo de persistencia (para log de cambios)
				MSolicitud solicitud = new MSolicitud(ctx, 0, trxName);
				solicitud.setSM_Solicitud_ID(line.getM_MovementLine_ID());
				// FIXME: va dos veces a la BD por el mismo objeto, deberia usar solo el constructor
				solicitud.setDocumentNo(MMovement.get(ctx, line.getM_Movement_ID(), trxName).getDocumentNo());
				solicitud.setMovementDate(MMovement.get(ctx, line.getM_Movement_ID(), trxName).getMovementDate() );
				MEXMEConfigInt configInt = MEXMEConfigInt.get(ctx, null,null);
				
				if (configInt != null && configInt.getInterfase_Inventarios().equalsIgnoreCase(MEXMEConfigInt.INTERFASE_INVENTARIOS_SIA)) {
					solicitud.setWHS_Sol_Value(MWarehouse.getFromLocator(ctx, line.getM_LocatorTo_ID(), null).getDescription());
					solicitud.setWHS_Sur_Value(MWarehouse.getFromLocator(ctx, line.getM_Locator_ID(), null).getDescription());
					solicitud.setProducto_Value(MProduct.get(ctx, line.getM_Product_ID()).getSKU());
				} else { //Si no tiene interfaz configurada o tiene configurada cualquier otra interfaz diferente de SIA 
					solicitud.setWHS_Sol_Value(MWarehouse.getFromLocator(ctx, line.getM_LocatorTo_ID(), null).getValue());
					solicitud.setWHS_Sur_Value(MWarehouse.getFromLocator(ctx, line.getM_Locator_ID(), null).getValue());
					solicitud.setProducto_Value(String.valueOf(line.getM_Product_ID()));
				}
		
				configInt = null;
				solicitud.setCantidad(line.getOriginalQty());
				solicitud.setM_Movement_ID(line.getM_Movement_ID());
				solicitud.setM_MovementLine_ID(line.getM_MovementLine_ID());
				solicitud.setM_Product_ID(line.getM_Product_ID());
				solicitud.setTransferido(false);
				solicitud.setUOM_Value(new MUOM(ctx, MProduct.get(ctx,line.getM_Product_ID()).getC_UOM_ID(), trxName).getName());
				solicitud.setAD_Client_ID(line.getAD_Client_ID());
				solicitud.setAD_Org_ID(line.getAD_Org_ID());
				
				if (!solicitud.save(trxName)) {
					retValue = "Error";
					s_log.log(Level.SEVERE, "solAlmacenVirtual()");
				}
				
			} catch (SQLException e) {
				s_log.log(Level.SEVERE, "solAlmacenVirtual()");
				retValue = "Error";
			}
			
				/*PreparedStatement psInsert = null;
				StringBuilder sqlUpdate = new StringBuilder("INSERT INTO  ");

				sqlUpdate.append("SM_SOLICITUD " )
				.append("(SM_SOLICITUD_ID ,DOCUMENTNO        ,MOVEMENTDATE      ,WHS_SOL_VALUE, " )
				.append(" WHS_SUR_VALUE   ,PRODUCTO_VALUE    ,CANTIDAD          ,CREATED      , " )
				.append(" M_MOVEMENT_ID   ,M_MOVEMENTLINE_ID ,M_PRODUCT_ID      ,TRANSFERIDO  , " )
				.append(" UOM_VALUE       ,CREATEDBY		 ,UPDATED			,UPDATEDBY, AD_CLIENT_ID, AD_ORG_ID) " );

				sqlUpdate.append("VALUES " )	                			
				.append("(?, ?, ?, ?, " )
				.append(" ?, ?, ?, ?, " )
				.append(" ?, ?, ?, ?, " )
				.append(" ?, ?, ?, ?, ? , ?) " );
				psInsert = DB.prepareStatement(sqlUpdate.toString(), trxName);
				psInsert.setInt(1 , line.getM_MovementLine_ID() );
				psInsert.setString(2 , MMovement.get(ctx, line.getM_Movement_ID(), trxName).getDocumentNo() );
				psInsert.setTimestamp(3 , MMovement.get(ctx, line.getM_Movement_ID(), trxName).getMovementDate() );
				
				configInt = MEXMEConfigInt.get(ctx, null,null);
				if(configInt != null && configInt.getInterfase_Inventarios().equalsIgnoreCase(X_EXME_ConfigInt.INTERFASE_INVENTARIOS_SIA)){
					psInsert.setString(4 , MWarehouse.getFromLocator(ctx, line.getM_LocatorTo_ID(), null).getDescription());
					psInsert.setString(5 , MWarehouse.getFromLocator(ctx, line.getM_Locator_ID(), null).getDescription());
					psInsert.setString(6 , MProduct.get(ctx, line.getM_Product_ID()).getSKU());
				}else{ //Si no tiene interfaz configurada o tiene configurada cualquier otra interfaz diferente de SIA 
					psInsert.setString(4 , MWarehouse.getFromLocator(ctx, line.getM_LocatorTo_ID(), null).getValue());
					psInsert.setString(5 , MWarehouse.getFromLocator(ctx, line.getM_Locator_ID(), null).getValue());
					psInsert.setInt(6 , line.getM_Product_ID());
				}
				configInt = null;
				
				psInsert.setBigDecimal(7 , line.getOriginalQty() );
				psInsert.setTimestamp(8 , line.getCreated() );
				psInsert.setInt(9 , line.getM_Movement_ID() );
				psInsert.setInt(10, line.getM_MovementLine_ID() );
				psInsert.setInt(11, line.getM_Product_ID());
				psInsert.setString(12, "N");
				psInsert.setString(13,  new X_C_UOM(ctx, MProduct.get(ctx,line.getM_Product_ID()).getC_UOM_ID(),trxName).getName());

				psInsert.setInt(14 , line.getCreatedBy() );
				psInsert.setTimestamp(15 , line.getUpdated() );
				psInsert.setInt(16 , line.getUpdatedBy() );
				psInsert.setInt(17 , line.getAD_Client_ID());
				psInsert.setInt(18 , line.getAD_Org_ID());

				psInsert.executeUpdate();*/


		}
		
		return retValue;
		
	}



	/**
	 * Actualiza un registro en movimiento de inventario
	 *
	 * @param  Bean de forma con la informacion a almacenar
	 * @throws SQLException si ocurre un error
	 */

//	public static void updateMovement(Properties ctx, int movimientoID, 
//			String description, String movementDate, int projectID,
//			int activityID, int campaignID, int orgTrxID, int user1ID,
//			int user2ID, int docType, boolean isInTransit, String trxName, boolean devol) throws SQLException {
//		s_log.log(Level.INFO,"***** Actualizando M_Movement ***** ");
//		
//		try{
//			MMovement mov = new MMovement(ctx, movimientoID, trxName);
//			mov.setDescription(description);
//			mov.setMovementDate(new Timestamp(sdfFechaHora.parse(movementDate).getTime()));
//			mov.setC_Project_ID(projectID);
//			mov.setC_Activity_ID(activityID);
//			mov.setC_Campaign_ID(campaignID);
//			mov.setAD_OrgTrx_ID(orgTrxID);
//			mov.setUser1_ID(user1ID);
//			mov.setUser2_ID(user2ID);
//			mov.setIsInTransit(isInTransit);
//			mov.setC_DocType_ID(docType);
//			mov.setIsDevol(devol);
//			if(!mov.save(trxName)){
//				throw new SQLException("error.traspasos.noUpdateMov");
//			}
//		}catch (Exception e) {
//			throw new SQLException("error.traspasos.noUpdateMov");
//		}
//	}



	/**
	 * Elimina una Movimiento de la base de datos
	 *
	 * @param   pk  Llave primaria del movimiento a eliminar
	 * @throws  SQLException si ocurre un error
	 */

//	public static void deleteMovement(Properties ctx, int movimientoID, 
//			String description, String movementDate, int projectID,
//			int activityID, int campaignID, int orgTrxID, int user1ID,
//			int user2ID, int docType, boolean isInTransit, String trxName) throws SQLException {
//		
//		MMovement mov = new MMovement(ctx, movimientoID, trxName);
//		s_log.log(Level.SEVERE, "AlmacenesAction delete .- Movimiento" + movimientoID + " Estatus " + mov.getDocStatus() + " Accion " + mov.getDocAction());
//		mov.setDocStatus(MMovement.STATUS_Voided);
//		mov.setDescription(mov.getDescription()!=null?mov.getDescription() + " - Movimiento Cancelado":"Movimiento Cancelado");
//		if(!mov.save(trxName)){
//			s_log.log(Level.SEVERE, "AlmacenesAction delete SEVERE "+mov.getM_Movement_ID()+".- Movimiento" + mov.getDocumentNo() + " Estatus " + mov.getDocStatus() + " Accion " + mov.getDocAction());
//			throw new SQLException("error.traspasos.noDeleteMov");
//		}
//		s_log.log(Level.SEVERE, "AlmacenesAction delete "+mov.getM_Movement_ID()+".- Movimiento" + mov.getDocumentNo() + " Estatus " + mov.getDocStatus() + " Accion " + mov.getDocAction());
//	}

	

	/**
	 * Actualiza un registro por cada linea de movimiento entre almacenes
	 *
	 * @param Un objeto tipo MovementLine que representan a una linea
	 * @throws SQLException si ocurre un error
	 */
//
//	public static void updateMovementLine(Properties ctx, int movementID, 
//			MovementLine linea, String trxName) throws SQLException {
//		s_log.log(Level.INFO,"***** Actualizando M_MovementLine ***** ");
//
//		//armamos el sql para insertar en M_MovementLine
//		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
//		//Liz de la Garza - Cambio del proceso para utilizarlo dentro del modelo de persistencia (para log de cambios)
//		sql.append("SELECT M_MovementLine_ID FROM M_MovementLine")
//		.append("WHERE M_Movement_ID = ? ")
//		.append("AND M_Product_ID = ?");
//		/*sql.append("UPDATE M_MovementLine SET ").
//		append("Updated = SYSDATE, UpdatedBy = ?, M_Product_ID = ?, ").
//		append("OriginalQty = ?, Description = ? ").
//		append("WHERE M_Movement_ID = ? ").
//		append("AND M_Product_ID = ?");*/
//
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		try {
//			pstmt = DB.prepareStatement(sql.toString(), trxName);
//
//			pstmt.setInt(1, movementID);
//			pstmt.setLong(2, linea.getProductID());
//
//			rs = pstmt.executeQuery();
//			while (rs.next()) {
//				MMovementLine obj = new MMovementLine(ctx, rs, null);
//				obj.setM_Product_ID(Integer.parseInt(String.valueOf(linea.getProductID())));
//				obj.setOriginalQty(BigDecimal.valueOf(linea.getOriginalQty()));
//				obj.setDescription(linea.getDescription());
//				if (!obj.save(trxName)) {
//					s_log.log(Level.SEVERE, "diarioEnf.error.noSave");
//					throw new SQLException("error.traspasos.noUpdateMovLine");
//				}
//				obj = null;
//
//			} 
//		} catch (Exception e) {
//			s_log.log(Level.SEVERE, sql.toString(), e.getMessage());
//			DB.rollback(true, trxName);			
//			throw new SQLException("error.traspasos.noUpdateMovLine");
//		} finally {
//			try {
//				if (rs != null) {
//					rs.close();
//				}
//				if (pstmt != null) {
//					pstmt.close();
//				}
//			} catch (Exception e) {
//				if (WebEnv.DEBUG)
//					e.printStackTrace();
//				s_log.log(Level.SEVERE, "closing rs / pstmt", e);
//			}	
//		}
//		//valores fijos para todo el detalle de actividad paciente
//		/*pstmt.setInt(1,  Env.getContextAsInt(ctx, "#AD_User_ID"));
//		pstmt.setLong(2, linea.getProductID());
//		pstmt.setLong(3, linea.getOriginalQty());
//		pstmt.setString(4, linea.getDescription());
//		pstmt.setInt(5, movementID);
//		pstmt.setLong(6, linea.getProductID());*/
//
//		/*int no = 0;
//		try {
//			no = pstmt.executeUpdate();
//		} catch (SQLException se) {
//			se.printStackTrace();
//		} finally
//        {
//            try
//            {
//                if (pstmt != null)
//                    pstmt.close ();
//            }
//            catch (Exception e)
//            {}
//            pstmt = null;
//        }
//
//		if(no == 0) {
//			DB.rollback(true, trxName);
//			throw new SQLException("error.traspasos.noUpdateMovLine");
//		}*/
//
//	}

	

	/**
	 * Elimina una linea del Movimiento de la base de datos
	 *
	 * @param   pk  Llave primaria del movimiento a eliminar
	 * @throws  SQLException si ocurre un error
	 */

//	public static void deleteMovementLine(Properties ctx, int pk, int movementID, 
//			String trxName) throws SQLException {
//
//		//armamos el sql para borrar de M_MovementLine
//
//		//Liz de la Garza - Cambio del proceso para utilizarlo dentro del modelo de persistencia (para log de cambios)
//		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
//    	sql.append("SELECT M_MovementLine_ID FROM M_MovementLine WHERE M_Movement_ID = ? AND M_Product_ID = ?");    	
//
//    	PreparedStatement pstmt = null;
//    	ResultSet rs = null;
//    	try {    		
//    		pstmt = DB.prepareStatement(sql.toString(), trxName);
//    		pstmt.setInt(1, movementID);
//    		pstmt.setInt(2, pk);
//    		rs = pstmt.executeQuery();
//
//    		while (rs.next()) {
//    			MMovementLine obj = new MMovementLine(ctx, rs, null);
//    			if (!obj.delete(true, trxName)) {
//    				s_log.log(Level.SEVERE, "error.traspasos.noDeleteMovLine");
//    				throw new SQLException("error.traspasos.noDeleteMovLine");
//    			}
//    			obj = null; 
//    		}
//    	} catch (Exception e) {
//    		s_log.log(Level.SEVERE, sql.toString(), e.getMessage());
//    		throw new SQLException("error.traspasos.noDeleteMovLine");
//    	} finally {
//    		try {
//				if (rs != null) {
//					rs.close();
//				}
//				if (pstmt != null) {
//					pstmt.close();
//				}
//			} catch (Exception e) {
//				if (WebEnv.DEBUG)
//					e.printStackTrace();
//				s_log.log(Level.SEVERE, "closing rs / pstmt", e);
//			}    		
//    	}
//		/*StringBuffer sql = new StringBuffer();
//
//		sql.append("DELETE FROM M_MovementLine ").
//		    append("WHERE M_Movement_ID = ? ").
//		    append("AND M_Product_ID = ?");
//
//		PreparedStatement pstmt = DB.prepareStatement(sql.toString(), trxName);
//		
//		pstmt.setInt(1, movementID);
//		pstmt.setInt(2, pk);
//
//		int no = pstmt.executeUpdate();
//
//        try
//        {
//            if (pstmt != null)
//                pstmt.close ();
//        }
//        catch (Exception e) {}
//        pstmt = null;
//        
//		if(no == 0) {
//			throw new SQLException("error.traspasos.noDeleteMovLine");
//		}*/
//
//	}

	

	/**
	 * Actualiza la fecha de actualizacion del movimiento con 
	 * la fecha del servidor
	 *
	 * @param   movementID  Llave primaria del movimiento a actualizar
	 * @throws  SQLException si ocurre un error
	 */

//	public static void updateFechaAct(Properties ctx, int movementID,
//			String trxName) throws SQLException {
//
//		MMovement object = new MMovement(ctx, movementID, null);
//		if (object != null) {
//			if (!object.save(trxName)) 
//				s_log.log(Level.SEVERE, "diarioEnf.error.noSave");
//		}		
//		object = null;
//		
//		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
//    	sql.append("SELECT M_MovementLine_ID FROM M_MovementLine WHERE M_Movement_ID = ? ");    	
//
//    	PreparedStatement pstmt = null;
//    	ResultSet rs = null;
//    	try {    		
//    		pstmt = DB.prepareStatement(sql.toString(), trxName);
//    		pstmt.setInt(1, movementID);
//    		rs = pstmt.executeQuery();
//
//    		while (rs.next()) {
//    			MMovementLine obj = new MMovementLine(ctx, rs, null);
//    			if (!obj.save(trxName)) {
//    				s_log.log(Level.SEVERE, "diarioEnf.error.noSave");
//    				throw new SQLException("error.traspasos.noUpdateFechaAct");
//    			}
//    			obj = null; 
//    		}
//    	} catch (Exception e) {
//    		s_log.log(Level.SEVERE, sql.toString(), e.getMessage());
//    		throw new SQLException("error.traspasos.noUpdateFechaAct");
//    	} finally {
//    		try {
//				if (rs != null) {
//					rs.close();
//				}
//				if (pstmt != null) {
//					pstmt.close();
//				}
//			} catch (Exception e) {
//				if (WebEnv.DEBUG)
//					e.printStackTrace();
//				s_log.log(Level.SEVERE, "closing rs / pstmt", e);
//			}    		
//    	}
//		//armamos el sql para actualizar en M_Movement
//		/*StringBuffer sql = new StringBuffer();
//
//		sql.append("UPDATE M_Movement ").
//		    append("SET Updated = SYSDATE ").
//		    append("WHERE M_Movement_ID = ?");
//
//		PreparedStatement pstmt = DB.prepareStatement(sql.toString(), trxName);
//		pstmt.setInt(1, movementID);
//
//		int no = pstmt.executeUpdate();
//
//        try {
//                if (pstmt != null)
//                    pstmt.close ();
//        } catch (Exception e) {} 
//        pstmt = null;
//            
//		if(no == 0) {
//			throw new SQLException("error.traspasos.noUpdateFechaAct");
//		}
//
//		//MMovement movement = new MMovement(ctx, movementID, trxName)
//		//armamos el sql para actualizar en M_MovementLine
//		sql = new StringBuffer();
//
//		sql.append("UPDATE M_MovementLine ").
//		    append("SET Updated = SYSDATE ").
//		    append("WHERE M_Movement_ID = ?");
//
//		pstmt = DB.prepareStatement(sql.toString(), trxName);
//		pstmt.setInt(1, movementID);
//
//		no = pstmt.executeUpdate();
//
//        try {
//            if (pstmt != null)
//                pstmt.close ();
//        } catch (Exception e) {} 
//        pstmt = null;
//
//        if(no == 0) {
//			throw new SQLException("error.traspasos.noUpdateFechaAct");
//
//		}*/
//	}
//	
	/**
	 * Crea un registro por cada linea de movimiento entre almacenes
	 *
	 * @param Hashtable con objetos tipo MovementLine que representan a una linea
	 * @throws SQLException si ocurre un error
	 * @return ActionErorrs (Noelia)
	 */

	public static boolean insertLine(Properties ctx, List<MovementLine> htLineas, 
			boolean devol, int movementID, int localizadorID, 
			int locatorToID, int EXME_CtaPac_ID, String trxName) throws Exception {
		s_log.log(Level.INFO,"***** Insertando en M_MovementLine ***** ");
	
		boolean valid = true;
		
		try {
			int localizador = 0;
			int n = 10;
			
			//ahora guardamos el detalle del traspaso entre almacenes
			for (int i = 0; i < htLineas.size(); i++) {
				//recuperamos los valores de la forma
				MovementLine lineas = htLineas.get(i);
				MMovementLine line = new MMovementLine(ctx, 0, trxName);
	
				//Validar que si el producto elegido utiliza numero de serie
				//sera necesario hacer que la cantidad maxima de producto sea 
				//en cantidad uno.
                MProduct prod = new MProduct(ctx, (int)lineas.getProductID(), trxName);
				if(prod != null && prod.isNumSerie() && EXME_CtaPac_ID>0){
					if(lineas.getOriginalQty()!=1) {
						valid =false;
					}
				}
                
				if(lineas.getOriginalQty()!=0) {
					line.setDescription(lineas.getDescription());
					line.setLine(n);
					line.setM_Movement_ID(movementID);
					line.setM_Product_ID((int)lineas.getProductID());
	                line.setOriginalQty(new BigDecimal(lineas.getOriginalQty()));
	                line.setTargetQty(new BigDecimal(lineas.getTargetQty()));
					line.setNumSerie(lineas.getSerie()); //Numero de serie
					/*line.setM_MovementLine_ID();
					line.setConfirmedQty();
					line.setM_AttributeSetInstance_ID();1
					line.setM_AttributeSetInstanceTo_ID();
					line.setProcessed();
					line.setScrappedQty();*/
				
					//buscamos el localizador de default del almacen resurtido
					localizador = MLocator.getLocatorID(ctx, localizadorID, trxName); 
					if(devol){
						//si es una devolucion invertir los localizadores
						line.setM_LocatorTo_ID(localizador);
					} else {
						line.setM_Locator_ID(localizador);
					}
					
					//buscamos el localizador de default del almacen solicitante
					localizador = MLocator.getLocatorID(ctx, locatorToID, trxName); 
					if(devol){
						//si es una devolucion invertir los localizadores
						line.setM_Locator_ID(localizador);
					} else {
						line.setM_LocatorTo_ID(localizador);
					}
					
//					if(devol) {
//						line.setTargetQty(new BigDecimal(lineas.getOriginalQty()));
//						line.setMovementQty(new BigDecimal(lineas.getOriginalQty()));
//					} else {
//						line.setTargetQty(Env.ZERO);
//						line.setMovementQty(Env.ZERO);
//					}
					line.setAD_OrgTrx_ID(lineas.getOrgTrx()); //OrgTrx .- Lama
					 
	                line.setLotInfo(lineas.getLoteInfo());//Lote Omar de la Rosa
	                line.setM_AttributeSetInstance_ID(lineas.getM_AttributeSetInstance());
	                line.setEXME_CtaPac_ID(lineas.getCtaPacID());//TTPR: Recetas Colectivas
	               
					if(prod!=null){
//						MEXMEEstServ m_estserv = new MEXMEEstServ(ctx, Env.getContextAsInt(ctx, "#EXME_EstServ_ID"), trxName);
//						MEXMECtaPac mCtaPac= new MEXMECtaPac(ctx, new MMovement(ctx, movementID, trxName).getEXME_CtaPac_ID(), trxName);
						MPrecios precios = null;
//						if (mCtaPac.getEXME_CtaPac_ID() > 0) {
							precios = GetPrice.getPriceMov(ctx 
									, line
									/*Env.getContext(ctx, "#TipoArea"), // Login
									line.getM_Product_ID(), 
									new BigDecimal(lineas.getOriginalQty()), 
									line.getProduct().getC_UOM_ID(),
									mCtaPac!=null && mCtaPac.getTipoArea()!=null?X_EXME_CtaPac.TIPOAREA_Ambulatory.equals(mCtaPac.getTipoArea())?Constantes.PVCE:Constantes.PVH:Constantes.PVH, 
									0,
									mCtaPac!=null && mCtaPac.getPaciente() != null?mCtaPac.getPaciente().getC_BPartner_ID():0,
									line.getM_LocatorTo_ID(), 
									line.getM_Locator_ID(),
									m_estserv!=null?m_estserv.getEXME_Area_ID():0, // Login
									DB.getTimestampForOrg(ctx),
									true,*/
									, trxName);

//						} else {
//							precios = PreciosVenta.precioProdVta(ctx, 
//                                    Env.getContext(ctx, "#TipoArea"), // Login
//                                    line.getM_Product_ID(), 
//                                    new BigDecimal(lineas.getOriginalQty()), 
//                                    line.getProduct().getC_UOM_ID(),
//                                    X_EXME_CtaPac.TIPOAREA_Ambulatory.equals(Env.getContext(ctx, "#TipoArea"))?Constantes.PVCE:Constantes.PVH, 
//                                    0,// Lista de precios
//                                    0,// socio de negocios
//                                    line.getM_LocatorTo_ID(), 
//                                    line.getM_Locator_ID(),
//                                    m_estserv!=null?m_estserv.getEXME_Area_ID():0, // Login
//                                    DB.getTimestampForOrg(ctx),
//                                    true,
//                                    trxName);
//						}
						if(precios!=null){
							line.setPriceList(precios.getPriceStd());
						}
					}
					//fin cambios .-Lama

					if(!line.save(trxName)){
	                	valid =false;
	                }else{
	                	// Almacen
	                	String retValue = null;
	                	  if (devol){
	                		  retValue = devAlmacenVirtual(ctx, line, trxName); 
	                	  }else{
	                		  retValue = solAlmacenVirtual(ctx, line, trxName);  
	                	  }
	                	  
	                	if (retValue != null){
	                		valid =false;
	                	}
	                	
	                	//Se guarda la solicitud de la sengre en su propia tabla
	                	MEXMEMovementLineB mlb = lineas.getMovementLineB(); 
	                	if (mlb != null) {
	                		mlb.setM_MovementLine_ID(line.getM_MovementLine_ID());
	                		
	                		//para prevenir problemas por precision de datos en la bd
	                		mlb.setNeutrofilos(mlb.getNeutrofilos().setScale(3, BigDecimal.ROUND_HALF_UP));
	                		mlb.setHemoglobina(mlb.getHemoglobina().setScale(3, BigDecimal.ROUND_HALF_UP));
	                		mlb.setPlaquetas(mlb.getPlaquetas().setScale(3, BigDecimal.ROUND_HALF_UP));
	                		mlb.setFibrinogeno(mlb.getFibrinogeno().setScale(3, BigDecimal.ROUND_HALF_UP));
	                		
	                		if(!mlb.save(trxName)){
	                			valid =false;
	                		}
	                	}
	                	;
	                }
	             	
					n += 10;
				}
			}
			
		} catch (Exception e) {
			s_log.log(Level.SEVERE, "getHistorial", e);
			valid =false;
		}
		
		return valid;
	}
	
	
	/**
	 * Elimina una Movimiento de la base de datos
	 *
	 * @param   pk  Llave primaria del movimiento a eliminar
	 * @throws  SQLException si ocurre un error
	 */
	public static void deleteMovement(Properties ctx, long pk) throws SQLException {
		//armamos el sql para borrar de M_Movement
		/*StringBuffer sql = new StringBuffer();
		//sql.append("DELETE FROM M_Movement ").
		    //append("WHERE M_Movement_ID = ?");
		sql.append("UPDATE M_Movement SET DocStatus = 'VO' ").
		    append("WHERE M_Movement_ID = ?");

		s_log.log(Level.FINE,sql.toString() + "  Movimiento: " + pk);
		PreparedStatement pstmt = DB.prepareStatement(sql.toString());
		pstmt.setLong(1, pk);
		
		int no = pstmt.executeUpdate();
		
		if(no == 0) {
			throw new SQLException("error.traspasos.noDeleteMov");
		}*/
		
		//Liz de la Garza - Cambio del proceso para utilizarlo dentro del modelo de persistencia (para log de cambios)
		MMovement movement = new MMovement(ctx, Integer.parseInt(String.valueOf(pk)), null);
		if (movement != null) {
			movement.setDocStatus(MMovement.ACTION_Void);
			if (!movement.save(null))
				throw new SQLException("error.traspasos.noDeleteMov");
		}
	}
	
	/**
	 * Metodo utilizado para guardar la solicitud de charolas y/o productos tomando en cuenta ambas unidades de medida
	 * y recibiendo como parametro la clase de modelo
	 * @author rsolorzano
	 * @param ctx
	 * @param htLineas
	 * @param devol
	 * @param movement
	 * @param almacenID
	 * @param locatorToID
	 * @param EXME_CtaPac_ID
	 * @param trxName
	 * @return
	 * @throws Exception
	 */
	public static boolean insertMEXMEMovementLine(Properties ctx, List<MMovementLine> htLineas, 
			boolean devol, MMovement movement, int almacenID, 
			int locatorToID, int EXME_CtaPac_ID, final boolean internalUse, String trxName) throws Exception {
		
		s_log.log(Level.INFO,"***** Insertando en M_MovementLine ***** ");
	
		boolean valid = true;
		
		try {
			
			if(internalUse)
				htLineas = movement.getMovementList(htLineas);
				
			int localizador = 0;
			int n = 10;
			
			//ahora guardamos el detalle del traspaso entre almacenes
			if(htLineas!=null && !htLineas.isEmpty()){
				for (int i = 0; i < htLineas.size(); i++) {
					
					MMovementLine line = htLineas.get(i);
		
					/*Validar que si el producto elegido utiliza numero de serie
					 *sera necesario hacer que la cantidad maxima de producto sea 
					 *en cantidad uno.
					 */			
	                MProduct prod = new MProduct(ctx, line.getM_Product_ID(), trxName);
	                
					if(prod != null && prod.isNumSerie() && EXME_CtaPac_ID>0){
						if(line.getQuantityUser().compareTo(BigDecimal.ONE)==1) {
							s_log.log(Level.ALL, Utilerias.getMsg(ctx, "error.traspasos.noInsertMovLine"));
							valid =false;
						}
					}
	                
					//if(line.getQuantityUser().compareTo(BigDecimal.ZERO)==1) {
						
					line.setLine(n);
					line.setM_Movement_ID(movement.getM_Movement_ID());
					line.setAD_OrgTrx_ID(movement.getAD_OrgTrx_ID());
					line.setEXME_CtaPac_ID(EXME_CtaPac_ID);
					
					
					//buscamos el localizador de default del almacen resurtido
					localizador = MLocator.getLocatorID(ctx, almacenID, trxName); 
					if(devol){
						//si es una devolucion invertir los localizadores
						line.setM_LocatorTo_ID(localizador);
					}else {
						line.setM_Locator_ID(localizador);
					}
						
					//buscamos el localizador de default del almacen solicitante
					localizador = MLocator.getLocatorID(ctx, locatorToID, trxName); 
					if(devol){
						//si es una devolucion invertir los localizadores
						line.setM_Locator_ID(localizador);
					}else{
						line.setM_LocatorTo_ID(localizador);
					}
						
					
					//inicio .- determinar el precio de lista para cada producto
//	                String tipoarea = Env.getContext(ctx, "#TipoArea");
//	                String estserv = Env.getContext(ctx, "#EXME_EstServ_ID");
//	                java.util.Date date= new java.util.Date();
//	                Timestamp dateT = new Timestamp(date.getTime());
//
//	                MEXMEEstServ m_estserv = new MEXMEEstServ(ctx, Integer.parseInt(estserv), trxName);
	       
	                if(prod!=null){
	                	
//	                    MPrecios precios = PreciosVenta.precioProdVta(ctx, tipoarea,prod.getM_Product_ID(), line.getOriginalQty(), //FIXME PRECIOS 
//	                            prod.getC_UOM_ID(),Constantes.PVH, 0,0,locatorToID, localizadorID,m_estserv.getEXME_Area_ID(),dateT,true,trxName);
//	                    
	                    MPrecios precios = GetPrice.getPriceMov(ctx
	                    		/*, tipoarea,prod.getM_Product_ID(), line.getOriginalQty(), //FIXME PRECIOS 
	                            prod.getC_UOM_ID(),Constantes.PVH, 0,0,locatorToID, localizadorID,m_estserv.getEXME_Area_ID(),
	                            dateT,true,*/
	                    		, line
	                            , trxName);
	                   
	                    if(precios!=null){
	                    	line.setPriceList(precios.getPriceStd());
	                    }
	                         
	                }
					
	                if(!line.save(trxName)){
	                	s_log.log(Level.ALL, Utilerias.getMsg(ctx, "error.traspasos.noInsertMovLine"));
	                	valid =false;
	                }else{
	                	String retValue = null;
	                	  if (devol){
	                		  retValue = devAlmacenVirtual(ctx, line, trxName); 
	                	  }else{
	                		  retValue = solAlmacenVirtual(ctx, line, trxName);  
	                	  }
	                	if (retValue != null){
	                		s_log.log(Level.ALL, Utilerias.getMsg(ctx, "error.traspasos.noInsertMovLine"));
	                		valid =false;
	                	}
	                	
	                
	                }
	             	
					n += 10;
					//}
				}
			}
			
			
		} catch (Exception e) {
			s_log.log(Level.SEVERE, "getHistorial", e);
			s_log.log(Level.ALL, Utilerias.getMsg(ctx, "error.traspasos.noInsertMovLine"));
			valid =false;
		}
		
		return valid;
	}
	
}