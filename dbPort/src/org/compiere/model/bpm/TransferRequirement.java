/**
 * 
 */
package org.compiere.model.bpm;

import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.adempiere.exceptions.MedsysException;
import org.compiere.model.MCountry;
import org.compiere.model.MEXMEPrescRXDet;
import org.compiere.model.MEXMEProductClassWhs;
import org.compiere.model.MEXMEProgQuiro;
import org.compiere.model.MMovement;
import org.compiere.model.MMovementLine;
import org.compiere.model.MWarehouse;
import org.compiere.model.PO;
import org.compiere.model.X_M_Product;
import org.compiere.model.X_M_Warehouse;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.util.Constantes;
import org.compiere.util.Env;

/**
 * 1. Orden-Solicitud
 * 
 * Surtido en caso de ser una devolucion o farmacia USA
 * 
 * @author twry
 * @deprecated
 */
public class TransferRequirement extends AbstractStockTransfer {
	/** Object origen */
	private PO parent;
	/** Almacen surte */
	private int mWarehouseAssId;
	/** Almacen solicita */
	private int mWarehouseReqId;

	@SuppressWarnings("unchecked")
	@Override
	protected void prepare() throws Exception {
		log.log(Level.INFO, "prepare");
		final ProcessInfoParameter[] para = getParameter();

		// Parametros
		for (int i = 0; i < para.length; i++) {
			final String name = para[i].getParameterName();

			if ("MovementLines".equals(name)) {
				mMovementLines = (List<MMovementLine>) para[i].getParameter();

			} else if ("Parent".equals(name)) {
				parent = ((PO) para[i].getParameter());

			} else if ("WarehouseFill".equals(name)) {// surte
				mWarehouseAssId = para[i].getParameterAsInt();// mWarehouseAssId
				// =
				// parent.getMWarehouseID();

			} else if ("WarehouseReq".equals(name)) {// solicita
				mWarehouseReqId = para[i].getParameterAsInt();//10001097

			} else {
				log.log(Level.SEVERE,
						"TransferRequirement.prepare - Unknown Parameter: "
								+ name);
			}
		}

		// Almacen que surte
		mWarehouseAssId = mWarehouseAssId > 0 ? mWarehouseAssId
				: getWarehouseAssId(mCtx);
		// Validaciones
		errores = validate(Constantes.SOLICITUD, mMovementLines);

		// Validaciones propias de la solicitud
		if (mWarehouseAssId <= 0 || mWarehouseReqId <= 0) {
			errores.add("@M_Warehouse_ID@");//FIXME mensaje
		}

		if (getPrescRXDet() != null && getPrescRXDet().isDelivered()) {
			errores.add("@Surtido@");//FIXME mensaje
		}

//		getPrescRXDet().setIsDelivered(false);
//		getPrescRXDet().save();
		// Surtido pendiente?
		
	}
	
	private boolean existe(){
		if (getPrescRXDet() != null) {
			mMovement = MMovement.getPrescRXDet(mCtx, getPrescRXDet().getEXME_PrescRXDet_ID(), mTrx.getTrxName());
		}
		return surtidoPendiente();
	}
	

	@Override
	protected String doIt() throws Exception {
		log.log(Level.INFO, "doIt");
//		boolean sucesss = true;
		
		if(!existe()){

//			final MMovement mov;
			mMovement = null;
			final int ctaPacId = getCtaPacId();

			// si no hay errores
			if (errores.isEmpty()) {
				mMovement = create(ctaPacId, getParameter("Description") == null ? ""
						: (String) getParameter("Description").getParameter(),
						getParameter("Priority") == null ? ""
								: (String) getParameter("Priority").getParameter(),
								getParameter("ProgQuiro") == null ? 0
										: (Integer) getParameter("ProgQuiro")
										.getParameter(), getParameter("Devol")
										.getParameter() == null ? false
												: (Boolean) getParameter("Devol").getParameter(),
												mTrx.getTrxName());

				if (mMovement == null) {
//					sucesss = false;
					throw new MedsysException("error.traspasos.noInsertMov");
				}

				if (parent != null) {
					updateMov();
				}

				// wfFill();
			} else {
				mMovement = null;
//				sucesss = false;
			}
		}// no existe
		
		return errores.isEmpty() ? "@OK@" + mMovement.getDocumentNo() : ERROR
				+ errores.toString();
	}

	/**
	 * Actualizar referencias del movimiento
	 * 
	 * @param mov
	 * @return
	 */
	private boolean updateMov() {
		log.log(Level.INFO, "updateMov");
		//
		if (parent instanceof MEXMEProgQuiro) {
			mMovement.setEXME_ProgQuiro_ID(parent.get_ID());

		} else if (getPrescRXDet() != null) {
			mMovement.setEXME_PrescRXDet_ID(parent.get_ID());
			getPrescRXDet().setIsDelivered(false);
			getPrescRXDet().save(mTrx.getTrxName());
		}
		return mMovement.save(mTrx.getTrxName());
	}

	/**
	 * Cuenta Paciente
	 * 
	 * @return id cuenta paciente
	 */
	private int getCtaPacId() {
		log.log(Level.INFO, "getCtaPacId");
		int ctaPacId = 0;
		if (getParameter("CtaPac").getParameter() != null
				&& ((Integer) getParameter("CtaPac").getParameter()) > 0) {
			ctaPacId = (Integer) getParameter("CtaPac").getParameter();

		} else if (getPrescRXDet() != null) {
			ctaPacId = getPrescRXDet().getPrescRx(false).getEXME_CtaPac_ID();
			// allMov = true;

		} else if (getProgQuiro() != null) {
			ctaPacId = getProgQuiro().getEXME_CtaPac_ID();
		}
		return ctaPacId;
	}

	/**
	 * Prescripcion
	 * 
	 * @return
	 */
	private MEXMEPrescRXDet getPrescRXDet() {
		log.log(Level.INFO, "getPrescRXDet");
		if (parent instanceof MEXMEPrescRXDet) {
			return (MEXMEPrescRXDet) parent;
		}
		return null;
	}

	/**
	 * Programación de quirofano
	 * 
	 * @return
	 */
	private MEXMEProgQuiro getProgQuiro() {
		log.log(Level.INFO, "getProgQuiro");
		if (parent instanceof MEXMEProgQuiro) {
			return (MEXMEProgQuiro) parent;
		}
		return null;
	}

	/**
	 * Metodo utilizado para guardar la solicitud de charolas y/o productos
	 * tomando en cuenta ambas unidades de medida y recibiendo como parametro la
	 * clase de modelo
	 * 
	 * @param ctaPacId
	 * @param documentNo
	 * @param description
	 * @param priorityRule
	 * @param progQuiroId
	 * @param isDevol
	 * @param trxName
	 * @return
	 * @throws Exception
	 */
	private MMovement create(final int ctaPacId, final String description,
			final String priorityRule, final int progQuiroId,
			final boolean isDevol, final String trxName) throws Exception {
		log.log(Level.INFO, "create");

		// Organizacion transaccional
		final int adOrgTrxId = MWarehouse.getOrgTrxId(mCtx,
				isDevol ? mWarehouseReqId : mWarehouseAssId);
		final String almacenSol = new X_M_Warehouse(mCtx, mWarehouseReqId, null)
				.getName();

		// guarda el encabezado
		MMovement mov = MMovement.insertMovement(mCtx, isDevol, adOrgTrxId,
				ctaPacId, progQuiroId, description, almacenSol, priorityRule,
				false, trxName);

		if (mov == null) {
			throw new MedsysException("error.traspasos.noInsertMov");
		} else {
			// guarda el detalle 
			final String success = mov.insertMEXMEMovementLine(mMovementLines,
					isDevol, ctaPacId, mWarehouseAssId, mWarehouseReqId,// almacen de surtido y de solicitud
					trxName);

			if (success == null) {
				mov.setWarehouseIds();
				if (!mov.save(trxName)) {
					throw new MedsysException(
							"error.traspasos.noUpdateMovPriority");
				}
			} else {
				errores.add(success);
			}
		}

		if (errores != null && !errores.isEmpty()) {
			mov = null;
		}

		return mov;
	}

	/**
	 * Almacen que surte Medicamentos. Dependiendo del pais es la configuracion
	 * 
	 * @param ctx
	 *            Conexto, obtener el país
	 * @return Id de la primera coincidencia
	 */
	public static int getWarehouseAssId(final Properties ctx) {
		int mWarehouseAssId = 0;
		List<MWarehouse> lst;

		// Requiere almacen de surtimiento
		if ("US".equals(MCountry.get(ctx, Env.getC_Country_ID(ctx))
				.getCountryCode())) {
			lst = MEXMEProductClassWhs.getAllWarehouseProdClass(ctx,
					X_M_Product.PRODUCTCLASS_Drug, null);
		} else {
			lst = MWarehouse.getWarehouseAss(ctx);
		}

		//
		if (lst != null && !lst.isEmpty()) {
			mWarehouseAssId = lst.get(0).getM_Warehouse_ID();
		}
		return mWarehouseAssId;
	}
}
