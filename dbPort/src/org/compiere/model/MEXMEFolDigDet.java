package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MEXMEFolDigDet extends X_EXME_FolDigDet{

	/**
	 * Clase modelo "Detalle de Folios Digitales"
	 * @author gderreza	 * 
	 **/
	private static final long serialVersionUID = 3573941400723830622L;

	public MEXMEFolDigDet(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

	public MEXMEFolDigDet(Properties ctx, int EXME_FolDigDet_ID, String trxName) {
		super(ctx, EXME_FolDigDet_ID, trxName);
		// TODO Auto-generated constructor stub
	}

}
