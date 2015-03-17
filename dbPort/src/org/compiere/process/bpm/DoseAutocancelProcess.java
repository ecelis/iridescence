package org.compiere.process.bpm;

import org.compiere.model.bpm.DoseCancellationProcess;
import org.compiere.process.SvrProcess;

public class DoseAutocancelProcess extends SvrProcess {

	@Override
	protected String doIt() throws Exception {
		
		DoseCancellationProcess dcp = new DoseCancellationProcess(getCtx());
		dcp.cencel(0, null, null);
		return "The process execute. pendiente:"+dcp.getModif();
	}

	@Override
	protected void prepare() {}

}
