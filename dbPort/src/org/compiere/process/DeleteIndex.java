package org.compiere.process;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;

import org.apache.commons.io.FileUtils;
import org.compiere.util.CLogger;
import org.compiere.util.Ini;

/**
 * @author odelarosa
 * 
 */
public class DeleteIndex extends SvrProcess {

	private static CLogger s_log = CLogger.getCLogger(DeleteIndex.class);

	/**
	 * Borra los indices de búsqueda
	 */
	public static void deleteIndex() {
		StringBuilder file = new StringBuilder(Ini.getCompiereHome());
		file.append(File.separatorChar).append("data");
		file.append(File.separatorChar).append("patients");
		File f = new File(file.toString());
		if (f.exists()) {
			try {
				FileUtils.deleteDirectory(f);
			} catch (IOException e) {
				s_log.log(Level.SEVERE, e.getMessage(), e);
			}
		}
		file = new StringBuilder(Ini.getCompiereHome());
		file.append(File.separatorChar).append("data");
		file.append(File.separatorChar).append("dbData");
		f = new File(file.toString());
		if (f.exists()) {
			try {
				FileUtils.forceDelete(f);
			} catch (IOException e) {
				s_log.log(Level.SEVERE, "It was not possible to delete indexes", e);
			}
		}
	}

	/**
	 * @see org.compiere.process.SvrProcess#doIt()
	 */
	@Override
	protected String doIt() throws Exception {
		deleteIndex();
		return null;
	}

	/**
	 * @see org.compiere.process.SvrProcess#prepare()
	 */
	@Override
	protected void prepare() {

	}

}
