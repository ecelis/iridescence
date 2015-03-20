/**
 * 
 */
package org.compiere.model.bpm;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;
import java.util.Properties;

import org.compiere.model.MCtaPacDet;
import org.compiere.model.MDocType;
import org.compiere.model.MEXMEActPaciente;
import org.compiere.model.MEXMEActPacienteInd;
import org.compiere.model.MEXMEActPacienteIndH;
import org.compiere.model.MEXMEEquipoH;
import org.compiere.model.MEXMEInOut;
import org.compiere.model.MInOut;
import org.compiere.model.MProduct;
import org.compiere.model.MStorage;
import org.compiere.model.MUOM;
import org.compiere.model.MWarehouse;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Trx;

/**
 * @author Expert
 * @deprecated usar metodos de la clase modelo
 */
public class ServicesCanceled extends MEXMEActPacienteIndH {

	/**
	 * 
	 */
	private static final long serialVersionUID = 180710982882741827L;
	private String m_processMsg = null;
	
	/**
	 * @param ctx
	 * @param EXME_ActPacienteIndH_ID
	 * @param trxName
	 */
	public ServicesCanceled(Properties ctx, int EXME_ActPacienteIndH_ID,
			String trxName) {
		super(ctx, EXME_ActPacienteIndH_ID, trxName);
	}

//	/**
//	 * @param ctx
//	 * @param actPaciente
//	 * @param trxName
//	 */
//	public ServicesCanceled(Properties ctx, MEXMEActPaciente actPaciente,
//			String trxName) {
//		super(ctx, actPaciente, trxName);
//	}

	/**
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public ServicesCanceled(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	
	/** @deprecated usar metodos de la clase modelo {@link MEXMEActPacienteIndH#voidIt()} */
	public BeanView cancelar(final int motivoCancelID, final String motivoCancel) {
		BeanView ret = null;
		Trx trx = null;
		MEXMEActPacienteIndH header = null;
		List<MEXMEEquipoH> listaEquipos = null;
		if (getEXME_ActPacienteIndH_ID() > 0) {
			
			try {
				trx = Trx.get(Trx.createTrxName("Solicitud"), true);
				String trxName = trx.getTrxName();

				setEXME_MotivoCancel_ID(motivoCancelID);
				
				set_TrxName(trxName);
				// Hacemos la cancelacion.
				if (!voidIt() || !save()) {
					Trx.close(trx, true);
					return new BeanView("cancelaServ.error.cancelar.voidIt", 
							getM_processMsg(), "Error", null);
				}

				// Cancelacion de equipos
				header = (new MEXMEActPacienteIndH(getCtx(),
						getEXME_ActPacienteIndH_ID(), null));
				listaEquipos = header
						.getEquiposProgramados();

				for (int i = 0; i < listaEquipos.size(); i++) {
					((MEXMEEquipoH) listaEquipos.get(i))
							.setEstatus_Equipo(MEXMEEquipoH.ESTATUS_EQUIPO_Cancelled);
					if (!((MEXMEEquipoH) listaEquipos.get(i)).save(trxName)) {
						Trx.close(trx, true);
						return new BeanView("cancelaServ.error.cancelar.voidIt", getM_processMsg(), "Error", null);
					}
				}
				Trx.commit(trx);

				// Mensaje de proceso exitoso
				ret = new BeanView("cancelaServ.msg.cancelado", getDocumentNo(), null, null);

			} catch (Exception e) {
				Trx.close(trx, true);
				return new BeanView("cancelaServ.error.cancelar.voidIt", 
						getM_processMsg(), "Error", null);
			} finally {
				Trx.close(trx);
				trx = null;
				set_TrxName(null);
				header = null;
				listaEquipos = null;
			}
		}
		return ret;
	}
	
	
	/**
	 * 	Void Document. PENSADO SOLO PARA LA CENCELACION DE SERVICIOS
	 * 	Set Qtys to 0 - Sales: reverse all documents
	 * 	@return true if success
	 *  @deprecated usar metodos de la clase modelo  {@link MEXMEActPacienteIndH#voidIt()}
	 */
	public boolean voidIt()
	{
		final MEXMEActPacienteInd[] lines = getLineas(true);
		log.info(toString());
		for (int i = 0; i < lines.length; i++)
		{
			final MEXMEActPacienteInd line = lines[i];
			final BigDecimal old = line.getQtyDelivered();
			//if (old.compareTo(Env.ZERO) != 0)
			//{
				line.setDescription((line.getDescripcion()!=null?line.getDescripcion():getDocumentNo()) + " : Voided" + " (" + old + ")");
				line.save(get_TrxName());
			//}
		}
		
		//Los servicios no tienen stock
		if(!isService()){
			//	Clear Reservations
			if (!reserveStock(null, lines))
			{
				m_processMsg = "No se reservo al inventario (VoidIt)";
				return false;
			}
		}
		if (!getDocStatus().equals(DOCSTATUS_Drafted) 
				&& !getDocStatus().equals(DOCSTATUS_Approved)) {
			if (!createReversals()) {
				return false;
			}
		}
		
		setDocStatus(DOCSTATUS_Voided);
		
		setProcessed(true);
		setDocAction(DOCACTION_None);
		
		if(isService()){
			setEstatus(MEXMEActPacienteIndH.ESTATUS_OrdersCanceledServiceWithdrawn);
			setIntervalo(new BigDecimal(0));
		}
		
		return true;
	}	//	voidIt
	
	
	
	/**
	 * 	Reserve Inventory.
	 * 	Counterpart: MInOut.completeIt()
	 * 	@param dt document type or null
	 * 	@param lines order lines (ordered by M_Product_ID for deadlock prevention)
	 * 	@return true if (un) reserved
	 *  @deprecated usar metodos de la clase modelo {@link MEXMEActPacienteIndH#reserveStock }
	 */
	private boolean reserveStock (MDocType dt, MEXMEActPacienteInd[] lines)
	{
		if (dt == null) {
			dt = MDocType.get(getCtx(), getC_DocType_ID());
		}
		//	Binding
		boolean binding = !dt.isProposal();
		//	Not binding - i.e. Target=0
		if (DOCACTION_Void.equals(getDocAction())
			//	Closing Binding Quotation
			|| (MDocType.DOCSUBTYPESO_Quotation.equals(dt.getDocSubTypeSO()) 
				&& DOCACTION_Close.equals(getDocAction())))
			binding = false;
		
		boolean isSOTrx = true;
		
		log.fine("Binding=" + binding + " - IsSOTrx=" + isSOTrx);
		
		//	Force same WH for all but SO/PO
		int header_M_Warehouse_ID = getM_Warehouse_ID();
		if (MDocType.DOCSUBTYPESO_StandardOrder.equals(dt.getDocSubTypeSO())
			|| MDocType.DOCBASETYPE_PurchaseOrder.equals(dt.getDocBaseType()))
			header_M_Warehouse_ID = 0;		//	don't enforce
		
		//	Always check and (un) Reserve Inventory		
		for (int i = 0; i < lines.length; i++)
		{
			MEXMEActPacienteInd line = lines[i];
			//	Check/set WH/Org
			if (header_M_Warehouse_ID != 0)
			{
				if (header_M_Warehouse_ID != line.getM_Warehouse_ID())
					line.setM_Warehouse_ID(header_M_Warehouse_ID);
				if (getAD_Org_ID() != line.getAD_Org_ID())
					line.setAD_Org_ID(getAD_Org_ID());
			}
			//	Binding
			BigDecimal target = binding ? line.getQtyOrdered() : Env.ZERO; 
			BigDecimal difference = target
				.subtract(line.getQtyReserved())
				.subtract(line.getQtyDelivered()); 
			if (difference.compareTo(Env.ZERO) == 0)
				continue;
			
			log.fine("Line=" + line.getLine() 
				+ " - Target=" + target + ",Difference=" + difference
				+ " - Ordered=" + line.getQtyOrdered() 
				+ ",Reserved=" + line.getQtyReserved() + ",Delivered=" + line.getQtyDelivered());

			//	Check Product - Stocked and Item
			MProduct product = line.getProduct();
			if (product != null 
				&& product.isStocked())
			{
				//	Get existing Location
				int M_Locator_ID = MStorage.getMLocatorID (line.getM_Warehouse_ID(), 
					line.getM_Product_ID(), 0, line.getQtyOrdered(), get_TrxName());
				//	Get default Location
				if (M_Locator_ID == 0)
				{
					final MWarehouse wh = MWarehouse.get(getCtx(), line.getM_Warehouse_ID());
					M_Locator_ID = wh.getDefaultLocator().getM_Locator_ID();
				}
				BigDecimal reserved = isSOTrx ? difference : Env.ZERO;
				BigDecimal ordered = isSOTrx ? Env.ZERO : difference;
				//
				if (!MStorage.add(getCtx(), line.getM_Warehouse_ID(), M_Locator_ID, 
					line.getM_Product_ID(), 0, 0,
					Env.ZERO, reserved, ordered, get_TrxName()))
				{
					return false;
				}
				//	update line
				line.setQtyReserved(line.getQtyReserved().add(difference));
				if (!line.save(get_TrxName()))
					return false;
			}
		}	//	reverse inventory
		
		
		return true;
	}	//	reserveStock
	
	
	/**
	 * 	Create Shipment/Invoice Reversals
	 * 	@param true if success
	 *  @deprecated usar metodos de la clase modelo
	 */
	private boolean createReversals()
	{
		log.info("createReversals");
		final StringBuffer info = new StringBuffer();
		
		//Creamos los contra cargos (-) si hubo CtaPac.
		if(getEXME_CtaPac_ID() != 0){
		    
		    info.append("... @EXME_CtaPacDet_ID@:");
		    
		    try{
			    // Buscamos los cargos que coincidan en Producto, UOM y Cantidad.
			    String sql = "SELECT * FROM EXME_CtaPacDet WHERE EXME_CtaPac_ID = " + getEXME_CtaPac_ID() 
			    			+ " AND M_Product_ID = ?  AND C_UOM_ID = ? AND QtyDelivered =  ?";
			    
			    final MEXMEActPacienteInd[] lineas = getLineas(false);
			    
			    //Cargos coincidentes.
			    final Hashtable<MEXMEActPacienteInd, MCtaPacDet> cargos = new Hashtable<MEXMEActPacienteInd, MCtaPacDet>(lineas.length);
			    
			    for(int i = 0; i < lineas.length; i++){
			        
			        final MEXMEActPacienteInd linea = lineas[i];
			        PreparedStatement pstmt = null;
			        
			        if(linea.getQtyDelivered().compareTo(Env.ZERO)!=0){
			        	pstmt = DB.prepareStatement(sql, get_TrxName());
			        	pstmt.setInt(1,linea.getM_Product_ID());
			        	pstmt.setInt(2, linea.getC_UOM_ID());
			        	pstmt.setBigDecimal(3, linea.getQtyDelivered());
			        
			        	ResultSet rs = pstmt.executeQuery();
			        
				        if(rs.next()){
				            final MCtaPacDet cargo = new MCtaPacDet(getCtx(), rs, get_TrxName());
				            cargos.put(linea, cargo);
				        }else{
				            // No existe un solo cargo que coinsida con la linea.
				        	final MProduct product = new MProduct(getCtx(), linea.getM_Product_ID(), null);
				        	final MUOM uom = new MUOM(getCtx(), linea.getC_UOM_ID(), null);
				            m_processMsg += " No existe un solo cargo que coinsida con la linea [" 
				                + (product!=null?(" Producto-" + product.getName()):"") + ", "
				                + (uom!=null?("UOM-"+uom.getName()):"") + ", QtyDelivered-" + linea.getQtyDelivered() + ". ";
				            return false;
				        }
			        }
			    }//lineas
			    
			    if(cargos.size() != lineas.length){
			        m_processMsg += " No fue posible dar reversa a los cargos. " + cargos.size() + " cargos != " + lineas.length + " por cancelar. ";
			        return false;
			    }
			    
			    //Creamos contracargos.
			    final Enumeration<MCtaPacDet> enums = cargos.elements();
			    while(enums.hasMoreElements()) {
			        
			        final MCtaPacDet cargo = (MCtaPacDet)enums.nextElement();
			        cargo.setSeDevolvio(true);
			        final MCtaPacDet contraCargo = MCtaPacDet.copyFrom(cargo, cargo.getEXME_CtaPacExt_ID(), cargo.get_TrxName());
			        
			        contraCargo.setQtyDelivered(contraCargo.getQtyDelivered().negate());
			        contraCargo.setQtyInvoiced(contraCargo.getQtyInvoiced().negate());
			        contraCargo.setQtyOrdered(contraCargo.getQtyOrdered().negate());
			       
			        contraCargo.setSeDevolvio(true);
			        contraCargo.setDescription("Contracargo (" + cargo.getLine() + " - " + cargo.getCtaPac().getDocumentNo() + ") : " + cargo.getDescription());
			        contraCargo.setLineNetAmt();
			        if(!contraCargo.save(get_TrxName()) || !cargo.save(get_TrxName())) {
			            m_processMsg += " No fue posible dar reversa al cargo. [CtaPacDet_ID - " + cargo.getEXME_CtaPacDet_ID() + "]. ";
			            return false;
			        }
			        info.append(" ");
			        info.append("Cargo - " + cargo.getEXME_CtaPacDet_ID() + ", ContraCargo - " + contraCargo.getEXME_CtaPacDet_ID());
			    }//enum
			    
		    }catch (SQLException sqle) {
		        m_processMsg += "No fue posible dar reversa a los cargos. ";
                sqle.printStackTrace();
                return false;
            }
		}
		
		// Reverse All *Shipments*
		info.append("@M_InOut_ID@:");
		final MInOut[] shipments = getShipments();
		for (int i = 0; i < shipments.length; i++)
		{
			final MInOut ship = shipments[i];
			
			//	if closed - ignore
			if (MInOut.DOCSTATUS_Closed.equals(ship.getDocStatus())
				|| MInOut.DOCSTATUS_Reversed.equals(ship.getDocStatus())
				|| MInOut.DOCSTATUS_Voided.equals(ship.getDocStatus()) )
				continue;
			ship.set_TrxName(get_TrxName());
		
			//	If not completed - void - otherwise reverse it
			if (!MInOut.DOCSTATUS_Completed.equals(ship.getDocStatus())) {
				if (ship.voidIt())
					ship.setDocStatus(MInOut.DOCSTATUS_Voided);
			}else if (ship.reverseCorrectIt()) { //	completed shipment			
				ship.setDocStatus(MInOut.DOCSTATUS_Reversed);
				info.append(" ").append(ship.getDocumentNo());
			}else {
				m_processMsg = "Could not reverse Shipment " + ship;
				return false;
			}
			ship.setDocAction(MInOut.DOCACTION_None);
			ship.save(get_TrxName());
		}	//	for all shipments		
		
		m_processMsg = info.toString();
		
		return true;
	}	//	createReversals
	
}

