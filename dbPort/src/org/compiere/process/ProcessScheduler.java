package org.compiere.process;

import java.util.logging.Level;

import org.compiere.db.CConnection;
import org.compiere.interfaces.Server;

/**
 * Revisa si hay planificadores sin agregar y lo ejecuta
 * 
 * @author odelarosa
 * 
 */
public class ProcessScheduler extends SvrProcess {

	/**
	 * @see org.compiere.process.SvrProcess#prepare()
	 */
	@Override
	protected void prepare() {

	}

	/**
	 * @see org.compiere.process.SvrProcess#doIt()
	 */
	@Override
	protected String doIt() throws Exception {

		// try to get from Server when enabled
		if (CConnection.get().isAppsServerOK(true)) {
			try {
				Server server = CConnection.get().getServer();
				if (server != null) {
					server.processScheduler();
				}
			} catch (Exception e) {
				log.log(Level.SEVERE, null, e);
			}
		}
		return null;
	}

}
