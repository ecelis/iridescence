package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MEXMEDirectoryDownloadLog extends X_EXME_DirectoryDownloadLog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3815750776480156115L;

	public MEXMEDirectoryDownloadLog(Properties ctx,
			int EXME_DirectoryDownloadLog_ID, String trxName) {
		super(ctx, EXME_DirectoryDownloadLog_ID, trxName);
	}

	public MEXMEDirectoryDownloadLog(Properties ctx, ResultSet rs,
			String trxName) {
		super(ctx, rs, trxName);
	}

}
