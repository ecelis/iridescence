
package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.compiere.util.Constantes;
import org.compiere.util.DB;

/**
 * @author raul
 *	
 */
public class MEXMEPreRegistro extends X_EXME_PreRegistro {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 5743384990253919876L;

	public MEXMEPreRegistro(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	public MEXMEPreRegistro(Properties ctx, int EXME_PreRegistro_ID,
			String trxName) {
		super(ctx, EXME_PreRegistro_ID, trxName);
	}
	
	public boolean validateUniqueEmail(Properties ctx) {
		boolean retValue = true;
		if (StringUtils.isBlank(getEMail())) {
			return true;
		}
		int count = 0;
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append("SELECT EXME_Paciente_ID FROM EXME_Paciente WHERE email = ? ");
		sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, sql.toString(), MEXMEPaciente.Table_Name));
		count = DB.getSQLValue(get_TrxName(), sql.toString(), getEMail());

		if (count > 0) {
			retValue = false;
		} else {
			sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
			sql.append("SELECT EXME_PreRegistro_ID FROM EXME_PreRegistro WHERE email = ? ");
			count = count
					+ DB.getSQLValue(get_TrxName(), sql.toString(), getEMail());
		}
			if(count > 0){
				retValue = false;
			}
		return retValue;
	}
	
	
}
