package org.compiere.process;


import java.util.logging.Level;

import org.compiere.model.MEXMECtaPac;
import org.compiere.util.CLogger;

/**
 * Revisar las cuentas paciente creadas desde las 11PM de 2 dias antes hasta las
 * 10:59 de 1 dia antes con el estado P, I y con el area A, U
 * 
 * @author odelarosa
 * 
 */
public class EncounterCheck extends SvrProcess {

	private static CLogger s_log = CLogger.getCLogger(EncounterCheck.class);

	public static void main(String args[]) {
		EncounterCheck c = new EncounterCheck();
		try {
			c.doIt();
		} catch (Exception e) {
			s_log.log(Level.SEVERE, null, e);
		}
	}

	/**
	 * @see org.compiere.process.SvrProcess#doIt()
	 */
	@Override
	protected String doIt() throws Exception {
		// Mandamos llamar el proceso de revision
		MEXMECtaPac.encounterCheck(getCtx(), null);
		return null;
	}

	/**
	 * @see org.compiere.process.SvrProcess#prepare()
	 */
	@Override
	protected void prepare() {

	}

}
