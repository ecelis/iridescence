package org.compiere.process;

import java.math.BigDecimal;
import java.util.List;

import org.compiere.model.MEXMECtrlPresup;
import org.compiere.model.MEXMEPresupuestoEgr;
import org.compiere.model.MEXMEPresupuestoModif;
import org.compiere.model.X_C_Invoice;
import org.compiere.model.X_EXME_PresupuestoModif;

/**
 * Actualiza el presupuesto disponible tramite
 * 
 * @author twry
 * 
 */
public class ProcessPresupDisponible extends SvrProcess {

	/** Nombre de Presupuesto */
	private int mPresupuestoEgr = 0;
	/** Nombre de Presupuesto */
	private int presupModifID = 0;

	@Override
	protected void prepare() {
		final ProcessInfoParameter[] para = getParameter();

		for (int i = 0; i < para.length; i++) {
			final String name = para[i].getParameterName();
			if (X_EXME_PresupuestoModif.COLUMNNAME_EXME_PresupuestoEgr_ID
					.equals(name)) {
				mPresupuestoEgr = ((BigDecimal) para[i].getParameter())
						.intValue();
			} else if (X_EXME_PresupuestoModif.COLUMNNAME_EXME_PresupuestoModif_ID
					.equals(name) && para[i].getParameter() != null) {
				presupModifID = ((BigDecimal) para[i].getParameter())
						.intValue();
			}
		}
	}

	@Override
	protected String doIt() throws Exception {
		String success = null;
		// Afectar la tabla EXME_CtrlPresupuesto
		if (presupModifID == 0) {

			success = presupuesto();
		} else {

			success = modificacion();

		} // modificacion o presupuesto
		return success;
	}

	/**
	 * Autorizacion del presupuesto
	 * 
	 * @return
	 */
	private String presupuesto() {
		String success = "";
		final MEXMEPresupuestoEgr bean = new MEXMEPresupuestoEgr(getCtx(),
				mPresupuestoEgr, get_TrxName());

		// Validar que no este autorizado previamente
		if (X_C_Invoice.DOCACTION_Complete.equals(bean.getDocStatus())) {
			success = ERROR;
		} else {

			// completar el presupuesto
			if (!X_C_Invoice.DOCACTION_Complete.equals(bean.completeIt())) {
				success = ERROR;
			}
		}
		return success;
	}

	/**
	 * Autorizacion de la modificación al presupuesto
	 * 
	 * @return
	 */
	private String modificacion() {
		String success = "";
		final MEXMEPresupuestoModif mPresupModif = new MEXMEPresupuestoModif(
				getCtx(), presupModifID, get_TrxName());

		// Los archivos del tipo de movimiento: RT no calcularán el nuevo
		// Modificado Autorizado, pero sí el Disponible Trámite, cambiando su
		// estatus de "P" a "A".
		// TODO: //Validar linea arriba

		// TODO: //Validar que no este autorizado previamente
		// if(X_C_Invoice.DOCACTION_Complete.equals(mPresupModif.getEstatus())){
		// success = ERROR;
		// }
		// // Validar que exista el presupuesto previamente autorizado antes de
		// cualquier modificación

		if (MEXMEPresupuestoModif.TIPO_Adding.equals(mPresupModif.getTipo())
				|| MEXMEPresupuestoModif.TIPO_Enlargement.equals(mPresupModif
						.getTipo())
				|| MEXMEPresupuestoModif.TIPO_Reduction.equals(mPresupModif
						.getTipo())
				|| MEXMEPresupuestoModif.TIPO_ReductionInProcess
						.equals(mPresupModif.getTipo())) {

			// Importacion de reasignación
			final List<MEXMECtrlPresup> lst = MEXMECtrlPresup.getModAutirizado(
					getCtx(), mPresupuestoEgr, presupModifID, get_TrxName());

			final boolean sumar = MEXMEPresupuestoModif.TIPO_Adding
					.equals(mPresupModif.getTipo())
					|| MEXMEPresupuestoModif.TIPO_Enlargement
							.equals(mPresupModif.getTipo());

			for (final MEXMECtrlPresup bean : lst) {
				bean.setMA(sumar);
				bean.setDI(sumar);
				if (!bean.save(get_TrxName())) {
					success = ERROR;
					break;
				}
			}
		}
		return success;
	}
}
