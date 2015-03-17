/*
 * Derechos Reservados (c) a partir 2005 Expert Sistemas Computacionales, S. A. de C. V.
 * Sistema MedSys
 */

package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.apache.commons.lang.StringUtils;
import org.compiere.process.DocAction;
import org.compiere.util.CLogger;
import org.compiere.util.Env;
import org.compiere.util.Trx;



/**
 * Bean para el surtido de Productos
 * <p>
 * Creado: 05/06/09
 * Modificado por: $Author: gvaldez $ <p>
 * Fecha: $Date: 2009/06/05 15:53:21 $ <p>
 *
 * @author Gerardo Valdez
 * @version $Revision: 1 $
 * @deprecated
 */
public class ConfirmaDet{

	private static CLogger s_log = CLogger.getCLogger(ConfirmaDet.class);
	private Properties ctx; 
	private String documentNo; 
//	private String aplicar = null;//No se usa
//	private String ctaPaciente = null;//No se usa
	private MConfigEC configEC = null;
//	private int estServID = -1;//No se usa
	private boolean isCeye = false;
//	private String backOrderID = null;//No se usa
	private int movementConfirmID = -1;
//	private String pacienteNom = null;//No se usa
	
	private Trx m_trx = null;
	private String trxName = null;
	private List<ConfirmaDetView> lstConfirmaDet  = new ArrayList<ConfirmaDetView>();
	
	/**  @deprecated muchos parametros, de los cuales solo se utilizan 6 */
	public ConfirmaDet(Properties ctx, String documentNo, List<ConfirmaDetView> lstConfirmaDet, 
			String aplicar, String ctaPaciente, MConfigEC configEC, 
			int estServID, boolean isCeye,
			int movementConfirmID, String pacienteNom){
		this(ctx, documentNo, lstConfirmaDet, aplicar, //
			ctaPaciente, configEC, estServID, isCeye, //
			movementConfirmID, pacienteNom, null);
		// this.ctx = ctx;
		// this.documentNo = documentNo;
		// this.lstConfirmaDet = lstConfirmaDet;
		// // this.aplicar = aplicar;
		// // this.ctaPaciente = ctaPaciente;
		// this.configEC = configEC;
		// // this.estServID = estServID;
		// this.isCeye = isCeye;
		// this.movementConfirmID = movementConfirmID;
		// // this.pacienteNom = pacienteNom;
		// Lama: la generacion de transaccion debe ser hasta que sea necesario
		// m_trx = Trx.get(Trx.createTrxName("Confirm"), true);
		// trxName = m_trx.getTrxName();
	}
	/**  @deprecated muchos parametros, de los cuales solo se utilizan 7 */
	public ConfirmaDet(Properties ctx, String documentNo, List<ConfirmaDetView> lstConfirmaDet, 
			String aplicar, String ctaPaciente, MConfigEC configEC, 
			int estServID, boolean isCeye,
			int movementConfirmID, String pacienteNom, String ptrxName){

		this.ctx = ctx;
		this.documentNo = documentNo;
		this.lstConfirmaDet = lstConfirmaDet;
//		this.aplicar = aplicar;
//		this.ctaPaciente = ctaPaciente;
		this.configEC = configEC;
//		this.estServID = estServID;
		this.isCeye = isCeye;
		this.movementConfirmID = movementConfirmID;
//		this.pacienteNom = pacienteNom;
		
//		m_trx = null;
		trxName = StringUtils.trimToNull(ptrxName);
	}

	/**  @deprecated revision de codigo {@link MMovementConfirm#confirm(Properties, MMovement, List, String)}
	 * generacion de backorders {@link MMovement#createBackorder(Properties, MMovement, List, String)}
	 * ya no se usa ceye o numero de serie*/
	public ArrayList<String[]> complete(){
		
		ArrayList<String[]> listaErrores = new ArrayList<String[]>();
		MMovement mov = null;
		
		try {
			if(trxName == null) { //Lama: crear la transaccion solo si es necesario
				m_trx = Trx.get(Trx.createTrxName("Confirm"), true);
				trxName = m_trx.getTrxName();
			}
			//si se generan back orders
			boolean generaBO = configEC != null && configEC.isGeneraBO();
			
			//Obtenemos los datos del movimiento original
			mov = MMovement.getFromConfirm(ctx, movementConfirmID, trxName);
			if(mov == null) {
				listaErrores.add(listaErrores.size(),new String[]{"Exception", "noMovement", null});
				return listaErrores;
			}
			this.documentNo = mov.getDocumentNo();
			
			boolean isBackOrder = mov.isApproved();
            String description = mov.getDescription()==null?"":mov.getDescription();
            
			s_log.fine("movimiento (documentNo): " + mov.getDocumentNo());
			
			//actualizamos la confirmacion del movimiento antes de iniciar el workflow
			BigDecimal faltantes = BigDecimal.ZERO;
			
			for(ConfirmaDetView linea : lstConfirmaDet) {

				/*Linea del producto a confirmar*/
				MMovementLine ml = new MMovementLine(ctx, (int)linea.getMovementLineID(), trxName);

				/*MStorage para almacen que surte. Solamente se le resta la cantidad surtida a la columna qtyReserved
				 * de manera que la cantidad quede igual que antes de haber hecho la confirmacion, y asi evitar conflictos
				 * con el proceso natural de Compiere*/
				//Alejandro. Si no se pudo actualizar la cantidad reservada, regresa el error.
				if(!MStorage.add(ctx, MWarehouse.getIdFromLocator(ctx, ml.getM_Locator_ID(), null), ml.getM_Locator_ID(), (int)linea.getProductID(),
						0, 0, Env.ZERO, linea.getTargetQty().negate(), Env.ZERO, trxName)){//cantidad Original 10
					listaErrores.add(listaErrores.size(),new String[]{"GLOBAL_MESSAGE", "error.confirma.cantReservada",null});
					return listaErrores;
				}

				/* Noelia: si es ceye se solicita la scrapped, sino ConfirmedQty
				 * scrapped = enviada - recibida
				 * devuelta = solicitada - confirmada
				 */
				if(!isCeye){
					linea.setScrappedQty(linea.getTargetQty().subtract(linea.getConfirmedQty()));
					linea.setScrappedQty_Vol(linea.getTargetQty_Vol().subtract(linea.getConfirmedQty_Vol()));
				} else{
					linea.setScrappedQty(linea.getDevolverQty());	
					linea.setScrappedQty_Vol(linea.getDevolverQty_Vol());
					//La cantidad a devolver no puede ser negativa
					BigDecimal scrappedQtyTmp;
					BigDecimal targetQtyTmp;
					if(ml.getOp_Uom() == ml.getProduct().getC_UOMVolume_ID()){ //unidad volumen
						scrappedQtyTmp = linea.getScrappedQty_Vol();
						targetQtyTmp = linea.getTargetQty_Vol();
					}else { //unidad minima
						scrappedQtyTmp = linea.getScrappedQty();
						targetQtyTmp = linea.getTargetQty();
					}
					
					if(scrappedQtyTmp.compareTo(BigDecimal.ZERO) < 0) { // >=0
						Trx.rollback(m_trx, true);
						listaErrores.add(listaErrores.size(),new String[]{"GLOBAL_MESSAGE", "msj.error.cantDevolverNeg", null});
						return listaErrores;
					}
					//No se puede devolver mas de la cantidad solicitada
					if(scrappedQtyTmp.compareTo(targetQtyTmp) == 1){ //>
						Trx.rollback(m_trx, true);
						listaErrores.add(listaErrores.size(),new String[]{"GLOBAL_MESSAGE", "error.encCtaPac.devolInsuficiente", null});
						return listaErrores;
					}
					
					linea.setConfirmedQty(linea.getTargetQty().subtract(linea.getScrappedQty()));
					linea.setDevueltaQty(linea.getOriginalQty().subtract(linea.getConfirmedQty()));
					
					linea.setConfirmedQty_Vol(linea.getTargetQty_Vol().subtract(linea.getScrappedQty_Vol()));
					linea.setDevueltaQty_Vol(linea.getOriginalQty_Vol().subtract(linea.getConfirmedQty_Vol()));
				}//fin Noelia

				faltantes = faltantes.add((linea.getOriginalQty().subtract(linea.getTargetQty())));// (solicitada - enviada) //FIXME

				MMovementLineConfirm mlc = MMovementLineConfirm.getFromMovLine(ctx,(int)linea.getMovementLineID(),	trxName );

				if (mlc== null){
					s_log.log(Level.SEVERE,"mlc.es null M_MovementLine_ID=" + ml.getM_MovementLine_ID());
					listaErrores.add(listaErrores.size(),new String[]{"THROW_EXCEPTION", "error.confirma.guardaLinea",linea.getProdName()});
					return listaErrores;
				}

				s_log.log(Level.SEVERE,"----despues de mmovement line confirm------");
				
				// A quien se le cargaran las diferencias
				if(configEC.isCargaDiferAlm()) {
					// Al almacen que surte
					mlc.setDifferenceQty(linea.getScrappedQty());//Se genera el inv fisico en el almacen que surte
					mlc.setScrappedQty(BigDecimal.ZERO);
					mlc.setConfirmedQty(linea.getTargetQty());/**Cambios generados por confirmacion de productos con diferencia que genera una salida al gasto */
					
					mlc.setDifferenceQty_Vol(linea.getScrappedQty_Vol());
					mlc.setScrappedQty_Vol(BigDecimal.ZERO);
					mlc.setConfirmedQty_Vol(linea.getTargetQty_Vol());/**Cambios generados por confirmacion de productos con diferencia que genera una salida al gasto */
					mov.setProdOrTray(true);/**Cambios generados por confirmacion de productos con diferencia que genera una salida al gasto */
					
				} else {
					// Al almacen que solicita/confirma
					mlc.setDifferenceQty(BigDecimal.ZERO);
					mlc.setScrappedQty(linea.getScrappedQty());
					mlc.setConfirmedQty(linea.getConfirmedQty());/**Cambios generados por confirmacion de productos con diferencia que genera una salida al gasto */
					
					mlc.setDifferenceQty_Vol(BigDecimal.ZERO);
					mlc.setScrappedQty_Vol(linea.getScrappedQty_Vol());
					mlc.setConfirmedQty_Vol(linea.getConfirmedQty_Vol());/**Cambios generados por confirmacion de productos con diferencia que genera una salida al gasto */
					//Coloca la cantidad solicitada en cero al completar el movimiento
					mov.setProdOrTray(true);
				}
				
				mlc.setDescription(linea.getDescription());

				if(!mlc.save(trxName)) {
					listaErrores.add(listaErrores.size(),new String[]{"THROW_EXCEPTION", "error.confirma.guardaLinea",linea.getProdName()});
					return listaErrores;
				}

				//si es devolucion, y la linea es de numero de serie eliminamos el registro de la tabla 
				if (description.toUpperCase().indexOf("DEVOLUCION") >= 0 && linea.getNumSerie() != null) {
					MEXMENumSerie numSerie = MEXMENumSerie.getFromNumSerie(ctx, linea.getNumSerie(), (int)linea.getProductID(), trxName);

					if (!numSerie.delete(false,trxName)) {
						Trx.rollback(m_trx, true);
						listaErrores.add(listaErrores.size(),new String[]{"THROW_EXCEPTION", "error.numSerie.noSave",String.valueOf((int)linea.getLine())});
						return listaErrores;
					}
				}
			}
			
			/*****************************************************
			********Noelia: Se sustituye llamado de los workflow**
			*****************por completeIt **********************
			*****************************************************/
			
			MMovementConfirm mc = new MMovementConfirm(ctx, movementConfirmID, trxName);				
			String status = mc.completeIt();
			if(!status.equals(DocAction.STATUS_Completed)){
				Trx.rollback(m_trx, true);
				listaErrores.add(listaErrores.size(),new String[]{"GLOBAL_ERROR", "error.order.complete", mc.getDocumentNo()});
				return listaErrores;
			}
			mc.setDocStatus(status);
			if(!mc.save(trxName)){
				Trx.rollback(m_trx, true);
				listaErrores.add(listaErrores.size(),new String[]{"GLOBAL_ERROR", "error.order.complete", mc.getDocumentNo()});
				return listaErrores;
			}
			
			status = mov.completeIt();
			if(!status.equals(DocAction.STATUS_Completed)){
				Trx.rollback(m_trx, true);
				listaErrores.add(listaErrores.size(),new String[]{"GLOBAL_ERROR", "error.order.complete", mov.getDocumentNo()});
				return listaErrores;
			}				
			mov.setDocStatus(status);
			if(!mov.save(trxName)){
				Trx.rollback(m_trx, true);
				listaErrores.add(listaErrores.size(),new String[]{"GLOBAL_ERROR", "error.order.complete", mov.getDocumentNo()});
				return listaErrores;
			}			
			
			//si hubo diferencias generar back orders
			if(faltantes.compareTo(BigDecimal.ZERO) == 1 && //hubo diferencia entre lo pedido y confirmado  -- > 0
			   generaBO && //esta configurado que se generen bo - .equalsIgnoreCase("Y")
			   (!isBackOrder||mov.isInTransit()) && //el mov. actual no es un back order -.equalsIgnoreCase("N")
               description.toUpperCase().indexOf("DEVOLUCION")==-1) { //el movto no es una devolucion 
				
				s_log.fine("***** Genera back orders ******");
				
				java.util.Date date= new java.util.Date();
				
				//insertar el movimiento
				MMovement movBackOrdr = new MMovement(ctx, 0, trxName);
				movBackOrdr.setDescription("BackOrder conf. =" + documentNo);
				movBackOrdr.setIsDevol(false);
				movBackOrdr.setMovementDate(new Timestamp(date.getTime()));
				movBackOrdr.setProcessing(false);
				movBackOrdr.setC_Project_ID(mov.getC_Project_ID());
				movBackOrdr.setC_Activity_ID(mov.getC_Activity_ID());
				movBackOrdr.setC_Campaign_ID(mov.getC_Campaign_ID());
				movBackOrdr.setAD_OrgTrx_ID(mov.getAD_OrgTrx_ID());
				movBackOrdr.setUser1_ID(mov.getUser1_ID());
				movBackOrdr.setUser2_ID(mov.getUser2_ID());
				movBackOrdr.setDocAction(MMovement.ACTION_Complete);
				movBackOrdr.setDocStatus(MMovement.STATUS_Drafted);
				movBackOrdr.setC_DocType_ID(mov.getC_DocType_ID());
				movBackOrdr.setApprovalAmt(Env.ZERO);
				movBackOrdr.setEXME_CtaPac_ID(mov.getEXME_CtaPac_ID());
				
				movBackOrdr.setIsApproved(true);
				
				
				//Guardamos el usuario que solicita el backorder - Jesus Cantu
				//TODO:Checar funcionamientos de backOrder (movBackOrdr.setIsApproved(true);)
				if(!movBackOrdr.save(trxName)) {
					Trx.rollback(m_trx, true);
    				listaErrores.add(listaErrores.size(),new String[]{"GLOBAL_MESSAGE", "error.traspasos.noInsertMov", null});
    				return listaErrores;
    			
				} else {
					
//					backOrderID = movBackOrdr.getDocumentNo();
					
					s_log.fine("******* Generando detalle del backorder ******* ");
					
					//y el detalle
					if(!MMovement.createBackorder(movBackOrdr, lstConfirmaDet)) {
						Trx.rollback(m_trx, true);//
                        listaErrores.add(listaErrores.size(),new String[]{"GLOBAL_MESSAGE", "error.traspasos.noInsertMovLine", null});
        				return listaErrores;
					}
				}
				
			}
			
			/*Completa el inventario fisico en caso de que se haya generado*/
			MMovementConfirm confirma =  new MMovementConfirm(ctx, movementConfirmID, trxName);
			
			if(confirma.getM_MovementConfirm_ID() <= 0){
				s_log.log(Level.SEVERE,"******* confirma == null ******* ");
				listaErrores.add(listaErrores.size(),new String[]{"THROW_EXCEPTION", "error.confirma.guardaConfirma", null});
				return listaErrores;
			}
			if(confirma.getM_Inventory_ID() > 0) {
				s_log.fine("******* Workflow para M_Inventory ******* ");
				
				MInventory inventory = new MInventory(ctx, confirma.getM_Inventory_ID(), trxName);
				inventory.setProcess("Traspasos");
				status = inventory.completeIt();
				if(!status.equals(DocAction.STATUS_Completed)){
					Trx.rollback(m_trx, true);
					listaErrores.add(listaErrores.size(),new String[]{"GLOBAL_ERROR", "error.order.complete", inventory.getDocumentNo()});
					return listaErrores;
				}
				inventory.setDocStatus(status);
				if(!inventory.save(trxName)){
					Trx.rollback(m_trx, true); 				
    				listaErrores.add(listaErrores.size(),new String[]{"GLOBAL_ERROR", "error.order.complete", inventory.getDocumentNo()});
					return listaErrores;
    			}				
				
				/**Cambios generados por confirmacion de productos con diferencia que genera una salida al gasto */
				// Como no se valido lass existencias en el before se valida en este punto
				for (int i = 0; i < inventory.getLines(true).length; i++) {
					if(!inventory.getLines(false)[i].validarExistencias(false)){
						Trx.rollback(m_trx, true);	    				
	    				listaErrores.add(listaErrores.size(),new String[]{"GLOBAL_ERROR", "error.order.complete", inventory.getDocumentNo()});
						return listaErrores;
					}
				}
			}//Fin Noelia.
	
//			if(aplicar== null || aplicar.equalsIgnoreCase("")){
//				aplicar = "N";
//	
//			}
	
			/*
			 * Noelia: En ceye se puede o no aplicar a la cuenta paciente
			 * Por lo tanto, verificamo que exista cantidad a surtir
			 * para iniciar el proceso de cargar a ctapac.
			 */
//			Boolean aplica = false;//No se usa ¿?
			if(isCeye){				
				for(int i=0; i<lstConfirmaDet.size(); i++) {							
					ConfirmaDetView linea = lstConfirmaDet.get(i);						
					MMovementLine ml = new MMovementLine(ctx, (int)linea.getMovementLineID(), trxName);
					BigDecimal qty = ml.getConfirmedQty().subtract(ml.getScrappedQty());
					if(qty != null && qty.compareTo(Env.ONE) >= 0){
//						aplica = true;
		                break;
		            }
				}
			}else{
//				aplica=true;
			}//fin Noelia
						

		} catch (Exception e) {
			listaErrores.add(listaErrores.size(), new String[] { "Exception", "error.confirma.complete", e.getMessage() });

		} finally {

			try {
				s_log.fine("******* Confirmacion procesada ******* ");
				if (listaErrores.isEmpty()) {
					if (!Trx.commit(m_trx, true)) {
						Trx.rollback(m_trx, true);
						listaErrores.add(listaErrores.size(), new String[] { "THROW_EXCEPTION", "error.confirma.guardaConfirma", null });
						return listaErrores;
					}
				} else {
					Trx.rollback(m_trx, true);
				}
			} catch (Exception e) {
				listaErrores.add(listaErrores.size(), new String[] { "SQLException", "error.confirma.complete", e.getMessage() });
			} finally {
				Trx.close(m_trx);
				m_trx = null;
			}
		}

			
       return listaErrores; //Lama .- Regresamos los errores
	}

//	public String getBackOrderID() {
//		return backOrderID;
//	}


//	public String getAplicar() {
//		return aplicar;
//	}

	public String getDocumentNo() {
		return documentNo;
	}

}