/*
 * Derechos Reservados (c) a partir 2005 Expert Sistemas Computacionales, S. A. de C. V.
 * Sistema MedSys
 */

package org.compiere.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.compiere.util.Env;
import org.compiere.util.Trx;
import org.compiere.util.Workflow;



/**
 * Bean para el surtido de Productos
 * <p>
 * Creado: 05/06/09
 * Modificado por: $Author: gvaldez $ <p>
 * Fecha: $Date: 2009/06/05 15:53:21 $ <p>
 *
 * @author Gerardo Valdez
 * @version $Revision: 1 $
 */
public class SurtidoDet{
	
	
	private Properties ctx; 
	private String documentNo; 
	private List<MMovementLine> lstSurtidoDet = null;
	private int ctaPacID = -1;
	private boolean isConsigna;
	private int movementID = -1;
	private int movementConfirmID=-1; 
	private int mwarehouseID = -1;
	private Trx m_trx = null;
	private String trxName = null;
	private String numSerie = null;
	private String processed = null;
	
	
	public SurtidoDet(Properties ctx, String documentNo, List<MMovementLine> lstSurtidoDet, 
					  int ctaPacID, boolean isConsigna, int movementID, int mwarehouseID, String trxName){
		
		this.ctx = ctx;
		this.documentNo = documentNo;
		this.lstSurtidoDet = lstSurtidoDet;
		this.ctaPacID = ctaPacID;
		this.isConsigna = isConsigna;
		this.movementID = movementID;
		this.mwarehouseID = mwarehouseID;
		if (trxName == null) {
			m_trx = Trx.get(Trx.createTrxName("po"), true);
			this.trxName = m_trx.getTrxName();
		} else {
			this.trxName = trxName;
		}
		
	}
	
	
	public ArrayList<String[]> complete() {

		ArrayList<String[]> listaErrores = new ArrayList<String[]>();

		try {
			/* MMovementLine.updateMovement(forma.getLstSurtidoDet(),forma.getMovementID(),forma.getCtx(),(String)null); */
			// TODO: Validacion de que no haya sido conformado aun.
			MMovement movement = new MMovement(ctx, movementID, trxName);
			if (movement.getDocStatus().equalsIgnoreCase(MMovement.DOCSTATUS_Voided)) {
				listaErrores.add(listaErrores.size(), new String[] { "GLOBAL_ERROR", "error.traspasos.isCancelado",
					movement.getDocumentNo() });
				return listaErrores;
			}

			// LRHU. Verificamos si el almacen controla existencias de productos
			boolean controlaStock = MEXMEMejoras.inventories(ctx); // por default todos los almacenes controlan existencias
			//MWarehouse war = new MWarehouse(ctx, locatorID, null);
			//controlaStock = war.isControlExistencias();
//			controlaStock = 
			
			// actualizamos el movimiento con la cantidad capturada
			for (int i = 0; i < lstSurtidoDet.size(); i++) {
				MMovementLine linea = lstSurtidoDet.get(i);
				if (ctaPacID > 0 && linea.getProduct().isNumSerie() && linea.getTargetQty() != null
					&& linea.getTargetQty().compareTo(Env.ONE) != 0) {
					listaErrores.add(listaErrores.size(), new String[] { "GLOBAL_ERROR",
						"error.traspasos.cantidadMaxEsUno", linea.getProduct().getName() });
					return listaErrores;
					// return new ActionForward(mapping.getInput());

				}
				else {
					linea.setMovementQty(linea.getTargetQty());
					linea.setMovementQty_Vol(linea.getTargetQty_Vol());
					// Guardamos el precio para almacenes en consigna,
					// si el traspaso es entre almacenes de la organizacion padre, el precio no se podra escribir y
					// no actualizaremos costos, eruiz
					if (isConsigna) {
						linea.setPriceList(new BigDecimal(linea.getPrice()));
					}
				}

				// validamos el numero de serie
				if (ctaPacID > 0 && linea.getProduct().isNumSerie()) {

					// el numero de serie no debe ser nulo
					if (StringUtils.isEmpty(linea.getNumSerie())) {
						listaErrores.add(listaErrores.size(), new String[] { "GLOBAL_ERROR", "error.numSerie.notNull",
							linea.getProduct().getName(), linea.getNumSerie() });
						linea.setNumSerie(null);
						return listaErrores;
						// return new ActionForward(mapping.getInput());
					}

					// validamos uqe el Numero de serie no haya sifo ya tecleado en el resto del detalle
					for (int j = 0; j < lstSurtidoDet.size(); j++) {

						MMovementLine line2 = lstSurtidoDet.get(j);

						if (linea.getLine() != line2.getLine() && linea.getM_Product_ID() == line2.getM_Product_ID()
							&& linea.getNumSerie().equalsIgnoreCase(line2.getNumSerie())) {

							listaErrores.add(listaErrores.size(), new String[] { "GLOBAL_ERROR",
								"error.numSerie.lista", linea.getNumSerie(), linea.getProduct().getName() });
							linea.setNumSerie(null);
							return listaErrores;
							// return new ActionForward(mapping.getInput());
						}

					}
					// validamos que no haya sido cargado para otra ctapac
					if (!MEXMENumSerie.numSerieValido(ctx, linea.getNumSerie(), (int) linea.getM_Product_ID(), null)) {

						linea.setNumSerie(null);
						listaErrores.add(listaErrores.size(), new String[] { "GLOBAL_ERROR", "error.numSerie.existe",
							linea.getNumSerie(), linea.getProduct().getName() });
						return listaErrores;
						// return new ActionForward(mapping.getInput());
					}
				}

				// Obtenemos la cantidad a la mano del localizador que surte.
				// BigDecimal qtyOnHand = MStorage.getQtyOnHand(linea.getM_Locator_ID(), linea.getM_Product_ID(), 0,
				// null);

				// Si la cantidad a la mano es menor a la cantidad a surtir, NO se permite el surtido
				// if (qtyOnHand.compareTo(linea.getTargetQty())<0){
				// errores.add("ctaPac", new ActionError("error.traspasos.ctaPac"));
				// } else {
				if (!linea.save(trxName)) {
					if (m_trx != null) {
						Trx.rollback(m_trx);
					}
					listaErrores.add(listaErrores.size(), new String[] { "THROW_EXCEPTION", "error.numSerie.existe",
						"error.traspasos.guardaLinea", String.valueOf(linea.getLine()) });
					return listaErrores;
					// throw new Exception(messages.getMessage(locale,"error.traspasos.guardaLinea",linea.getLine()));
				}
				else {
					// LRHU. Si el almacen NO controla las existencias de productos, �stas no se afectan
					// sumamos la cantidad surtida a la columna de qtyOnHand
					if (!controlaStock) {
						MStorage.add(ctx, mwarehouseID, linea.getM_Locator_ID(), linea.getM_Product_ID(), 0, 0,
							linea.getTargetQty(), linea.getTargetQty(), Env.ZERO, trxName);
					}
					else {
						// MStorage para almacen que surte. Solamente se le suma la cantidad surtida a la columna
						// qtyReserved
						MStorage.add(ctx, mwarehouseID, linea.getM_Locator_ID(), linea.getM_Product_ID(),
							linea.getM_AttributeSetInstance_ID(), linea.getM_AttributeSetInstance_ID(), Env.ZERO,
							linea.getTargetQty(), Env.ZERO, trxName);
					}
				}
				// }

				// MovementLine linea = (MovementLine) forma.getLstSurtidoDet().get(i);
				// updateMovementLine(conn, linea, forma.getMovementID(),Env.getContextAsInt(ctx, "#AD_User_ID"));

				// si es numero de serie guardamos el numero de serie en la tabla
				if (ctaPacID > 0 && linea.getProduct().isNumSerie()) {
					MEXMENumSerie numSerie = new MEXMENumSerie(ctx, 0, trxName);

					numSerie.setNumSerie(linea.getNumSerie());
					numSerie.setM_Product_ID(linea.getM_Product_ID());
					numSerie.setM_MovementLine_ID(linea.getM_MovementLine_ID());
					numSerie.setEXME_CtaPac_ID(ctaPacID);
					numSerie.setDocStatus(MEXMENumSerie.DOCSTATUS_Surtido);

					if (!numSerie.save()) {
						if (m_trx != null) {
							Trx.rollback(m_trx);
						}
						listaErrores.add(listaErrores.size(), new String[] { "THROW_EXCEPTION",
							"error.numSerie.noSave", String.valueOf(linea.getLine()) });
						return listaErrores;
						// throw new Exception(messages.getMessage(locale,"error.numSerie.noSave", linea.getLine()));
					}
				}
			}

			if (listaErrores.size() == 0) {
				if (m_trx != null) {
					Trx.commit(m_trx);
				}

				//Workflow flujo = m_trx == null ? new Workflow(trxName) : new Workflow();
				
//				// nombre de transaccion
//				Workflow flujo = m_trx == null ? new Workflow(trxName) : new Workflow();
//				flujo.getInfo(MMovement.Table_Name);
//				flujo.startWorkflow(ctx, movementID);
//				flujo = null;

				// actualizamos la fecha de ultima actualizacion del movimiento (SYSDATE - Servidor)
				// AlmacenesDB.updateFechaAct(forma.getMovementID());

				// asignamos el numero de documento generado
				MMovementConfirm confirma = MMovementConfirm.get(ctx, movementID, trxName);
				if (confirma != null) {
					this.documentNo = confirma.getDocumentNo();
					this.movementConfirmID = confirma.get_ID();
				}
				// forma procesada
				this.processed = "Y";
				confirma = null;

				// forma.setDocumentNoConfirm(
				// DatosTraspasos.getDocumentMovementConfirm(forma.getMovementID())
				// );
				// return mapping.findForward("surtido");
			}
			else {
				if (m_trx != null) {
					Trx.rollback(m_trx);
				}
			}
		}
		catch (Exception e) {
			listaErrores.add(listaErrores.size(),
				new String[] { "Exception", "error.traspasos.completando", e.getMessage() });
		}
		finally {
			if (listaErrores.size() == 0) {
				if (m_trx != null) {
					Trx.commit(m_trx);
				}
			}
			else if (m_trx != null) {
				Trx.rollback(m_trx);
			}
			Trx.close(m_trx);
			m_trx = null;
			trxName = null;
		}
		return listaErrores; // Lama .- Regresamos los errores
	}
	
	public String getProcessed() {
		return processed;
	}



	public String getDocumentNo() {
		return documentNo;
	}

	public int getMovementConfirmID() {
		return movementConfirmID;
	}
	public String getNumSerie() {
		return numSerie;
	}

	public String getTrxName() {
		return trxName;
	}

	public void setTrxName(String trxName) {
		this.trxName = trxName;
	}

	public List<MMovementLine> getLstSurtidoDet() {
		return lstSurtidoDet;
	}

	public void setLstSurtidoDet(List<MMovementLine> lstSurtidoDet) {
		this.lstSurtidoDet = lstSurtidoDet;
	}
	
}