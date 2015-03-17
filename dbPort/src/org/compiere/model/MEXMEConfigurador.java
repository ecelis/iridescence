package org.compiere.model;

import java.sql.ResultSet;
import java.util.List;
import java.util.Properties;

/**
 * Configurador
 * 
 * @author odelarosa
 * 
 */
public class MEXMEConfigurador extends X_EXME_Configurador {

	private static final long serialVersionUID = -4562183172161503376L;
	private List<MEXMEConfiguradorDet> steps = null;

	public MEXMEConfigurador(Properties ctx, int EXME_Configurador_ID, String trxName) {
		super(ctx, EXME_Configurador_ID, trxName);
	}

	public MEXMEConfigurador(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	/**
	 * Obtiene los pasos del configurador
	 * 
	 * @return Pasos del Configurador
	 */
	public List<MEXMEConfiguradorDet> getSteps() {
		if (steps == null) {
			steps = MEXMEConfiguradorDet.getSteps(getCtx(), getEXME_Configurador_ID());
		}
		return steps;
	}

	/**
	 * @see org.compiere.model.X_EXME_Configurador#getName()
	 */
	@Override
	public String getName() {
		return get_Translation(COLUMNNAME_Name);
	}

	/**
	 * @see org.compiere.model.X_EXME_Configurador#getDescription()
	 */
	@Override
	public String getDescription() {
		return get_Translation(COLUMNNAME_Description);
	}

}
