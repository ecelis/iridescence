package org.compiere.process;

import java.util.List;
import org.compiere.model.MEXMECuestEsp;
import org.compiere.model.MEXMECuestionario;
import org.compiere.util.Trx;

/**
 * 	Copy the element of Required 
 *  
 */
public class QuestDefaultCopy extends SvrProcess {
	String trxName = null;
	int	EXME_Cuestionario_ID = 0;
	MEXMECuestionario cuest = null;

	@Override
	protected void prepare() {
		EXME_Cuestionario_ID = this.getRecord_ID();
	}

	@Override
	protected String doIt() throws Exception {
		Trx trx = Trx.get(Trx.createTrxName("DCE"), true);
		trxName = trx.getTrxName();
		cuest = new MEXMECuestionario(getCtx(), EXME_Cuestionario_ID, trxName);
		List<MEXMECuestEsp> cuestEspLst = MEXMECuestEsp.getLstFromCuest(getCtx(), EXME_Cuestionario_ID, trxName);
		
		boolean allSaved = true;
		
		for (MEXMECuestEsp cuestEsp : cuestEspLst) {
			cuestEsp.setIsDefault(cuest.isEsDefault());
			if (!cuestEsp.save(trxName)) {
				allSaved = false;
				break;
			}			
		}
		
		if (allSaved) {
			Trx.commit(trx);
		} else {
			Trx.rollback(trx);
		}
		if (trx != null) {
			Trx.close(trx);
		}
		return null;
	}

}