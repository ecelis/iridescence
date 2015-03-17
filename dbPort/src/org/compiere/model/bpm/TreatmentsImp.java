package org.compiere.model.bpm;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.compiere.model.PO;

/**
 * Linea del detalle del tratamiento
 * 
 * @author Expert
 * 
 */
public class TreatmentsImp extends TreatmentsDetail {

	public TreatmentsImp (Properties ctx, int pTratSesionID,
			String trxName){
		super(ctx, pTratSesionID, trxName);
	}
	
	private List<PO> lstDetalle = null;
	
	public List<PO> getLstDetalle() {
		return this.lstDetalle;
	}

	public void setLstDetalle(List<PO> lstDetalle) {
		this.lstDetalle = lstDetalle;
	}

	public void detalle(){
		buscarDetalle();
		lstDetalle = new ArrayList<PO>();
		for (int i = 0; i < getLstCuestionarios().size(); i++) {
			lstDetalle.add(getLstCuestionarios().get(i));
		}
		for (int i = 0; i < getLstPrescripciones().size(); i++) {
			lstDetalle.add(getLstPrescripciones().get(i));
		}
		for (int i = 0; i < getLstServicios().size(); i++) {
			lstDetalle.add(getLstServicios().get(i));
		}
		for (int i = 0; i < getLstProduct().size(); i++) {
			lstDetalle.add(getLstProduct().get(i));
		}
		for (int i = 0; i < getLstMiniPack().size(); i++) {
			lstDetalle.add(getLstMiniPack().get(i));
		}
	}
}
