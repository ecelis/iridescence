package org.compiere.model;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import org.compiere.util.DB;

/**
 * Callouts for Morge
 * 
 * @author JCarmona
 * @version $Id: CalloutMorge.java,v 1.0 2010/02/24
 * */

public class CalloutMorgue extends CalloutEngine {

	/**
	 * 
	 * 
	 * @param ctx
	 * @param WindowNo
	 * @param mTab
	 * @param mField
	 * @param value
	 *            Paciente
	 * @return String CtaPaciente Abierta
	 */

	public String CtaPaciente(Properties ctx, int WindowNo, GridTab mTab,
			GridField mField, Object value) {

		if (value == null) {
			return "";
		}

		Integer EXME_Paciente_ID = null;
		if (value instanceof BigDecimal)
			EXME_Paciente_ID = Integer.valueOf(value.toString());
		else if (value instanceof Integer)
			EXME_Paciente_ID = (Integer) value;

		if (EXME_Paciente_ID == null || EXME_Paciente_ID.intValue() == 0) {
			return "";
		} else {
			//Integer C_LOCATION_ID = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			try {

				String SQL_Paciente = " SELECT EXME_CTAPAC.EXME_CTAPAC_ID, EXME_PACIENTE.C_LOCATION_ID FROM EXME_CTAPAC " +
						"INNER JOIN EXME_PACIENTE ON (EXME_PACIENTE.EXME_PACIENTE_ID=EXME_CTAPAC.EXME_PACIENTE_ID) " +
					    " WHERE EXME_CTAPAC.EXME_PACIENTE_ID=? " + " AND EXME_CTAPAC.Encounterstatus=? "; 
						
						String Sql_Access=MEXMELookupInfo.addAccessLevelSQL(ctx, SQL_Paciente, MEXMECtaPac.Table_ID);

				pstmt = DB.prepareStatement(Sql_Access, null);
				pstmt.setInt(1, EXME_Paciente_ID.intValue());
				pstmt.setString(2, MEXMECtaPac.ENCOUNTERSTATUS_Admission);
				rs = pstmt.executeQuery();
				if (rs.next()) {
					Integer EXME_CTAPAC_ID = rs.getInt(1);
					Integer C_LOCATION_ID = rs.getInt(2);

					rs.close();
					pstmt.close();
					mTab.setValue("EXME_CtaPac_ID", EXME_CTAPAC_ID);
					mTab.setValue("C_Location_ID", C_LOCATION_ID);
				}
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
			finally {
				try {
					if (rs != null)
						rs.close();
					if (pstmt != null)
						pstmt.close();
					
				} catch (SQLException e) {
				}

				rs = null;
				pstmt = null;
			}
			return "";
		}
	}

}
