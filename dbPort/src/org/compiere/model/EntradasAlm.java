/*
 * Derechos Reservados (c) a partir 2005 Expert Sistemas Computacionales, S. A. de C. V.
 * Sistema MedSys
 */

package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.process.DocAction;
import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.Trx;



/**
 * Bean para la entradas al almacen
 * <p>
 * Creado: 05/06/09
 * Modificado por: $Author: gvaldez $ <p>
 * Fecha: $Date: 2009/06/05 15:53:21 $ <p>
 *
 * @author Gerardo Valdez
 * @version $Revision: 1 $
 * @deprecated
 */
public class EntradasAlm{
	
	
	private int orderID; 
	private Properties ctx; 
	private String actionMessage;
	private String documentNo; 
	private String description; 
	private int cDocTypeID;
	private String fecha; 
	private int cbPartnerID; 
	private int priceListID; 
	private int wareHouseID;
	private int adOrgTrxID; 
	private List<OrderLine> orderLineLst = null;
	private boolean isWeb;
	private Trx trx = null;
	//private String trxName = null;
	
	private static final String ERR_MIO_COMPL 	= "error.minout.complete";
	private static final String GLOBAL_ERROR 			= "GLOBAL_ERROR";
	
	private static CLogger logger = CLogger.getCLogger(EntradasAlm.class);
	
	public EntradasAlm(Properties ctx, String documentNo, String description, int cDocTypeID,
					   String fecha, int cbPartnerID, int priceListID, int wareHouseID,
					   int adOrgTrxID, List<OrderLine> orderLineLst, boolean isWeb){
		
		this.ctx = ctx;
		this.documentNo = documentNo;
		this.description = description;
		this.adOrgTrxID = adOrgTrxID;
		this.cbPartnerID = cbPartnerID;
		this.fecha = fecha;
		this.orderLineLst = orderLineLst;
		this.cDocTypeID = cDocTypeID;
		this.priceListID = priceListID;
		this.wareHouseID = wareHouseID;
		this.isWeb = isWeb;
		trx = Trx.get(Trx.createTrxName("po"), true);
	}
	
	public ArrayList<String[]> saveOrders(){
		ArrayList<String[]> listaErrores = new ArrayList<String[]>();
		
		isWeb = !isWeb;
		isWeb = !isWeb;
		//guardamos el encabezado de la orden de compra
        MOrder order = new MOrder(ctx, 0, trx.getTrxName());
        order.setIsSOTrx(false);

        if(this.documentNo!=null && !this.documentNo.equals("")) {
            order.setDocumentNo(this.documentNo);
        }
        order.setDescription(this.description);
        order.setDocStatus(MOrder.DOCSTATUS_Drafted);
        order.setDocAction(MOrder.DOCACTION_Complete);
        order.setIsTransferred(MOrder.ISTRANSFERRED_Transferred);
        order.setC_DocType_ID(this.cDocTypeID);
        order.setC_DocTypeTarget_ID(this.cDocTypeID);

        try{
        	if (isWeb){
        		order.setDateOrdered(new Timestamp(Constantes.getSdfFecha().parse(this.fecha).getTime()));
                order.setDateAcct(new Timestamp(Constantes.getSdfFecha().parse(this.fecha).getTime()));
        	} else {
        		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
        		Date parsedDate = dateFormat.parse(this.fecha);
        		order.setDateOrdered(new Timestamp(parsedDate.getTime()));
        		order.setDateAcct(new Timestamp(parsedDate.getTime()));
        	}

        }catch(Exception e) {
        	logger.log(Level.SEVERE, "", e);
            if (trx != null){
            	trx.rollback();
            }
        }
        order.setC_BPartner_ID(this.cbPartnerID);
        MBPartner bp = new MBPartner(this.ctx, this.cbPartnerID, null);
        /*
         * Se valida que tenga una direccion v�lida el socio de negocios
         * Si no tiene un registro en la tabla de CB_Partner_Location, manda
         * el error a pantalla. mantis 4802. Jesus Cantu
         */
        int ilPBPLocationId = bp.getPrimaryC_BPartner_Location_ID();
        if (bp != null && ilPBPLocationId >0) { 
        	order.setC_BPartner_Location_ID(ilPBPLocationId);
        } else {
        	listaErrores.add(listaErrores.size(),new String[]{"error.socio.noLocation", "error.socio.noLocation",null});
        	return listaErrores;
        }

        //lista de precios
        order.setM_PriceList_ID(this.priceListID);
        MPriceList pl = new MPriceList(this.ctx, this.priceListID, null);
        order.setC_Currency_ID(pl.getC_Currency_ID());
        order.setM_Warehouse_ID(this.wareHouseID);

        //representante de ventas (admin)
        order.setSalesRep_ID(MEXMEUser.getSalesRepAdmin(this.ctx));

        order.setAD_OrgTrx_ID(this.adOrgTrxID);//Organizacion de quien surte
		
        if(order.save()) {
           //insertamos las lineas de la orden de compra
            for(int i=0; i<this.orderLineLst.size(); i++) {

                OrderLine movto = (OrderLine) this.orderLineLst.get(i);

                MOrderLine ol = new MOrderLine(this.ctx, 0, trx.getTrxName());
                                                       
                ol.setC_Order_ID(order.getC_Order_ID());
                ol.setLine((i+1)*10);
                ol.setM_Product_ID(movto.getProductID());
                ol.setDateOrdered(order.getDateOrdered());
                ol.setQtyEntered(new BigDecimal(movto.getOriginalQty()));
                ol.setQtyOrdered(new BigDecimal(movto.getOriginalQty()));
                ol.setC_UOM_ID(movto.getUOM());
                ol.setC_Currency_ID(order.getC_Currency_ID());
                ol.setPriceActual(new BigDecimal(movto.getCostoUnit()));
                ol.setPriceList(new BigDecimal(movto.getCostoUnit()));
                ol.setPriceLimit(new BigDecimal(movto.getCostoUnit()));
                
                //Agregamos el priceEntered, eruiz
                ol.setPriceEntered(new BigDecimal(movto.getCostoUnit()));
                ol.setC_Tax_ID(movto.getTaxID());
                ol.setC_BPartner_ID(order.getC_BPartner_ID());
                ol.setC_BPartner_Location_ID(order.getC_BPartner_Location_ID());
                
                ol.setAD_OrgTrx_ID(this.adOrgTrxID);//Organizacion de quien surte
                
                ol.setLotInfo(movto.getLoteInfo());//Lote Omar de la Rosa
                ol.setM_AttributeSetInstance_ID(movto.getM_AttributeSetInstance());//Lote Omar de la Rosa
                
                if(ol.save()) {
                	(this.orderLineLst.get(i)).setOrderLineID(ol.getC_OrderLine_ID());
                    
                    /** se elimino el calculo de costo promedio por orden de Gerardo Camargo 12 Ago 2008*/

                } else {
                	if (trx != null){
                    	trx.rollback();
                    }
                    
                    listaErrores.add(listaErrores.size(),new String[]{"error.order.guardarOCL", "error.order.guardarOCL",null});
                    break;
                }
            }
                } else {
        	if (trx != null){
            	trx.rollback();
            }

        	listaErrores.add(listaErrores.size(),new String[]{"error.order.guardarOC", "error.order.guardarOC",null});
        }
                  
        if (listaErrores.isEmpty()) {
        	this.documentNo = order.getDocumentNo();
        	this.orderID = order.getC_Order_ID();
        }
        return listaErrores;
	}
	
	public ArrayList<String[]> complete(){
		
		ArrayList<String[]> listaErrores = new ArrayList<String[]>();
    		
    		try {
    			//Asignamos el id de la lista de precios en la clase MConfigPre, eruiz
//    			MEXMEConfigPre.M_PriceList_ID = this.priceListID;
    			
    			//completamos la orden de compra
    			MOrder order = new MOrder(this.ctx, this.getOrderID(), trx.getTrxName()); // Lama .- Asignamos el nombre de transaccion
    			String status = order.prepareIt();
    			
    			if(!DocAction.STATUS_InProgress.equals(status)){
    				
    				listaErrores.add(listaErrores.size(),new String[]{GLOBAL_ERROR, "error.order.complete",order.getProcessMsg()});
    				return listaErrores; 
    			}
    			
    			status = order.completeIt();
    			order.setDocStatus(status);
    			if(!DocAction.STATUS_Completed.equals(status)){
    				listaErrores.add(listaErrores.size(),new String[]{GLOBAL_ERROR, "error.order.complete",order.getProcessMsg()});
    				return listaErrores; //Lama .- Regresamos los errores
    			}
    			
    			if(!order.save()){ // Lama .- Asignamos el nombre de transaccion
    				listaErrores.add(listaErrores.size(),new String[]{GLOBAL_ERROR, "error.order.complete",order.getProcessMsg()});
    				return listaErrores; //Lama .- Regresamos los errores
    				//return mapping.findForward(mapeo);
    			}
    			
    			MDocType dt[] = MEXMEDocType.getOfDocBaseType(this.ctx,MEXMEDocType.DOCBASETYPE_MaterialReceipt);
    			
    			int C_DocType_ID = 0;
    			
    			for(int j=0; j<dt.length; j++) {
    				if(!dt[j].isDocNoControlled()) {
    					C_DocType_ID = dt[j].getC_DocType_ID();
    					break;
    				}
    			}
    			
    			if(C_DocType_ID==0) {
    				dt[0].getC_DocType_ID();
    			}
    			
    				
    			MInOut inOut = new MInOut (order, C_DocType_ID, order.getDateOrdered());
    			if(!inOut.save()) { // Lama .- Asignamos el nombre de transaccion
    				listaErrores.add(listaErrores.size(),new String[]{GLOBAL_ERROR, ERR_MIO_COMPL,order.getProcessMsg()});
    				return listaErrores; //Lama .- Regresamos los errores
    				//return mapping.findForward(mapeo);
    			}
    			
    			MOrderLine[] ol = order.getLines();
    			
    			for(int i=0; i<ol.length; i++) {
    				//Create new InOut Line
    				MInOutLine iol = new MInOutLine (inOut);
    				iol.setM_Product_ID(ol[i].getM_Product_ID(), ol[i].getC_UOM_ID());    //  Line UOM
    				iol.setQty(ol[i].getQtyEntered());                         //  Movement/Entered
    				//
    				iol.setC_OrderLine_ID(ol[i].getC_OrderLine_ID());
    				//  iol.setOrderLine(ol, M_Locator_ID, QtyEntered);
    				if (ol[i].getQtyEntered().compareTo(ol[i].getQtyOrdered()) != 0)
    				{
    					iol.setMovementQty(ol[i].getQtyEntered()
    							.multiply(ol[i].getQtyOrdered())
    							.divide(ol[i].getQtyEntered(), 12, BigDecimal.ROUND_HALF_UP));
    					iol.setC_UOM_ID(ol[i].getC_UOM_ID());
    				}
    				iol.setM_AttributeSetInstance_ID(ol[i].getM_AttributeSetInstance_ID());
    				iol.setDescription(ol[i].getDescription());
    				//
    				iol.setC_Project_ID(ol[i].getC_Project_ID());
    				iol.setC_ProjectPhase_ID(ol[i].getC_ProjectPhase_ID());
    				iol.setC_ProjectTask_ID(ol[i].getC_ProjectTask_ID());
    				iol.setC_Activity_ID(ol[i].getC_Activity_ID());
    				iol.setC_Campaign_ID(ol[i].getC_Campaign_ID());
    				iol.setAD_OrgTrx_ID(ol[i].getAD_OrgTrx_ID());
    				iol.setUser1_ID(ol[i].getUser1_ID());
    				iol.setUser2_ID(ol[i].getUser2_ID());
    				iol.setLotInfo(ol[i].getLotInfo());//Lote Omar de la Rosa
    				iol.setM_AttributeSetInstance_ID(ol[i].getM_AttributeSetInstance_ID());//Lote Omar de la Rosa
    				
    				//
    				MWarehouse wh = new MWarehouse(this.ctx, ol[i].getM_Warehouse_ID(), null);// Lama .- Asignamos el nombre de transaccion
    				iol.setM_Locator_ID(wh.getDefaultLocator().getM_Locator_ID());
    				if (!iol.save()) { //Lama .- Asignamos el nombre de transaccion
    					listaErrores.add(listaErrores.size(),new String[]{GLOBAL_ERROR, ERR_MIO_COMPL,order.getProcessMsg()});
        				return listaErrores; //Lama .- Regresamos los errores
    					//return mapping.findForward(mapeo);
    				}
    			}
    			
    			//completamos la recepcion de material
    			status = inOut.prepareIt();
    			if(!DocAction.STATUS_InProgress.equals(status)){
    				listaErrores.add(listaErrores.size(),new String[]{GLOBAL_ERROR, ERR_MIO_COMPL,inOut.getProcessMsg()});
    				return listaErrores; //Lama .- Regresamos los errores
    				//return mapping.findForward(mapeo);
    			}
    			
    			status = inOut.completeIt();
    			inOut.setDocStatus(status);
    			if(!DocAction.STATUS_Completed.equals(status)){
    				listaErrores.add(listaErrores.size(),new String[]{GLOBAL_ERROR, ERR_MIO_COMPL,inOut.getProcessMsg()});
    				return listaErrores; //Lama .- Regresamos los errores
    				//return mapping.findForward(mapeo);
    			}
    			
    			if(!inOut.save()){
    				
    				listaErrores.add(listaErrores.size(),new String[]{GLOBAL_ERROR, ERR_MIO_COMPL,inOut.getProcessMsg()});
    				return listaErrores; //Lama .- Regresamos los errores
    				//return mapping.findForward(mapeo);
    			}
    			
    			

    		} catch (Exception e) {
    			logger.log(Level.SEVERE, "", e);
    			listaErrores.add(listaErrores.size(),new String[]{"SQLException", e.getMessage(), null});
    		}finally{
    			try {
    				if (listaErrores.isEmpty()){
    					if (trx != null){
    						trx.commit();
    					}
    				} else if (trx != null){
    					trx.rollback();
    				}
    			} catch (Exception e) {
    				logger.log(Level.SEVERE, "", e);
    				listaErrores.add(listaErrores.size(),new String[]{"SQLException", e.getMessage(), null});
    			}finally {
    	        	try {
    	        		 if (trx != null){
    	                 	trx.close();
    	                 }
    				} catch (Exception e) {
    					logger.log(Level.SEVERE, "", e);
    	                 }
    	        }
    		}
    	
    	return listaErrores; //Lama .- Regresamos los errores
	}
	
		
	public String getDocumentNo() {
		return documentNo;
	}
	
	public int getOrderID(){
		return orderID;
	}
	
	public String getActionMessage() {
		return actionMessage;
	}
}