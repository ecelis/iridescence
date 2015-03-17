/**
 * 
 */
package org.compiere.model.bpm;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.adempiere.exceptions.MedsysException;
import org.compiere.model.MEXMEPrescRXDet;
import org.compiere.model.MMovement;
import org.compiere.model.MMovementLine;
import org.compiere.model.MWarehouse;
import org.compiere.model.PO;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.util.Constantes;
import org.compiere.util.Utilerias;

/**
 * Clase para probar la solicitud, surtido y confirmacion del stock
 * 
 * @author twry
 * * @deprecated
 */
public class StockTransfer {
	private final List<String> lstMsg = new ArrayList<String> ();
	public boolean validatePrevious = false;
	private AbstractStockTransfer trans;
	private Properties ctx;
	private PO mPO;
	

	/**
	 * Constructor
	 * 
	 * @param pctx
	 * @param parent
	 */
	public StockTransfer(final Properties pctx, final PO parent) {
		this.ctx = pctx;
		this.mPO = parent;
	}

	/**
	 * Transferencia de productos para prescripciones
	 * 
	 * @param ctx
	 * @param parent
	 * @return
	 * @throws MedsysException
	 */
	public static boolean prescRXDetTransfer(final Properties ctx,
			final MEXMEPrescRXDet parent) throws MedsysException {
		boolean success = false;
		final StockTransfer procss = new StockTransfer(ctx, parent);
		procss.validatePrevious = true;
		if (procss.solicitud()){//TODO: Tiempos ?
			if (procss.surtido()){//TODO: Tiempos ?
				if (procss.confirmacion()) {//TODO: Tiempos ?
					success = true;//TODO: 50seg :-O
				}
			}
		} else {
			throw new  MedsysException(procss.getMsg());
		
		}
		return success;
	}
	
	/**
	 * Solicitar un producto
	 * 
	 * @return
	 */
	public boolean solicitud() {
		trans = new TransferRequirement();
		trans.validatePrevious = validatePrevious;
		trans.setInfoParameter(getParameters(Constantes.SOLICITUD, ctx, mPO));
		boolean success = trans.startProcess(ctx, null);
		lstMsg.addAll(trans.errores);
		return success;
	}

	/**
	 * surtir un producto
	 * 
	 * @return
	 */
	public boolean surtido() {
		trans = new TransferOrder();
		trans.validatePrevious = validatePrevious;
		trans.setInfoParameter(getParameters(Constantes.SURTIDO, ctx, mPO));
		boolean success = trans.startProcess(ctx, null);
		lstMsg.addAll(trans.errores);
		return success;
	}

	/**
	 * Confirmar un producto
	 * 
	 * @return
	 */
	public boolean confirmacion() {
		trans = new ConfirmTransferOrder();
		trans.validatePrevious = validatePrevious;
		trans.setInfoParameter(getParameters(Constantes.CONFIRMACION, ctx, mPO));
		boolean success = trans.startProcess(ctx, null);
		lstMsg.addAll(trans.errores);
		return success;
	}

	/**
	 * Get Parameter
	 * 
	 * @param process
	 * @return
	 * @throws MedsysException
	 */
	public ProcessInfoParameter[] getParameters(final int process,
			final Properties ctx, final PO parent) throws MedsysException {
		final List<ProcessInfoParameter> lsParams = new ArrayList<ProcessInfoParameter>();
		
		//
		switch (process) {
		case Constantes.SOLICITUD:
			if (parent instanceof MEXMEPrescRXDet) {
//				final List<MMovementLine> lnMov = MMovementLine.createMovementLine((MEXMEPrescRXDet) parent);
//				if(lnMov==null || lnMov.isEmpty()){
//					throw new MedsysException(Utilerias.getLabel("error.encCtaPac.noExisten"));
//				}
//				lsParams.add(new ProcessInfoParameter("MovementLines",lnMov,null, null, null));
//				lsParams.add(new ProcessInfoParameter("WarehouseReq",((MEXMEPrescRXDet) parent).getMWarehouseCtaPacID(), null,null, null));// SOLICITA
			}
			break;

		case Constantes.SURTIDO:
		case Constantes.CONFIRMACION:
			if (parent instanceof MEXMEPrescRXDet) {
				final List<MMovement> lstMov = MMovement.getPrescRXDetPending(ctx,((MEXMEPrescRXDet) parent).getEXME_PrescRXDet_ID(),null);
				if (lstMov != null && !lstMov.isEmpty()) {
					lsParams.add(new ProcessInfoParameter("Movement", lstMov.get(0),null, null, null));
					lsParams.add(new ProcessInfoParameter("MovementLines",lstMov.get(0).getLines(), null, null, null));
				}
			}
			break;

		default:
			break;
		}
		return params(parent, lsParams);
	}

	/**
	 * Parametros aplicados a todos los procesos
	 * @param parent
	 * @param lsParams
	 * @return
	 * @throws MedsysException
	 */
	private ProcessInfoParameter[] params(final PO parent, final List<ProcessInfoParameter> lsParams) throws MedsysException {
		// Exclusivo medicamentos
		// Requiere almacen de surtimiento
		final MWarehouse alm = new MWarehouse(ctx, TransferRequirement.getWarehouseAssId(ctx), null);

		lsParams.add(new ProcessInfoParameter("Parent", parent, null, null, null));
		lsParams.add(new ProcessInfoParameter("WarehouseFill", alm.getM_Warehouse_ID(), null, null, null));// SURTE
		lsParams.add(new ProcessInfoParameter("Sterilization", false, null, null,null));
		lsParams.add(new ProcessInfoParameter("isConsigna", false, null, null, null));//TODO Por implementar
		lsParams.add(new ProcessInfoParameter("DocumentNo", "", null, null, null));
		lsParams.add(new ProcessInfoParameter("Description", "", null, null, null));
		lsParams.add(new ProcessInfoParameter("Priority", "5", null, null, null));
		lsParams.add(new ProcessInfoParameter("ProgQuiro", 0, null, null, null));
		
		final ProcessInfoParameter[] pars = new ProcessInfoParameter[lsParams.size()];
		lsParams.toArray(pars);
		return pars;
	}

	/**
	 * Mensajes de errores
	 * @return
	 */
	public String getMsg(){
		final StringBuilder sql = new StringBuilder();
		for (int i = 0; i < lstMsg.size(); i++) {
			sql.append(lstMsg.get(i)).append(" \n");
		}
		return sql.toString();
	}
}
