package org.compiere.model;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

import org.compiere.util.DB;

public class CalloutTransplantes extends CalloutEngine {


	public void NoficaCURP(Properties ctx, int WindowNo, GridTab mTab,
			GridField mField, Object value) {
		Integer EXME_Paciente_ID = (Integer) value;
		if (EXME_Paciente_ID == null || EXME_Paciente_ID.intValue() == 0)
			return;
		else if (value instanceof BigDecimal)
			EXME_Paciente_ID = Integer.valueOf(value.toString());
		else if (value instanceof Integer)
			EXME_Paciente_ID = (Integer) value;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			String SQL_Paciente = " SELECT COUNT(*) FROM EXME_Trasplantes_Espera WHERE EXME_Paciente_ID=?";
			pstmt = DB.prepareStatement(SQL_Paciente, null);
			pstmt.setInt(1, EXME_Paciente_ID);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				Integer NC = rs.getInt(1);
				if (NC>0)
					log.saveWarning("trasplantes.pacexistente", "Este paciente ya se encuentra en la lista de espera");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void NoficaCURPDonador(Properties ctx, int WindowNo, GridTab mTab,
			GridField mField, Object value) {
		String Curp = (String) value;
		if (Curp == null || Curp=="")
			return;
		else if (value instanceof String)
			Curp = String.valueOf(value.toString());

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			String SQL_Paciente = " SELECT COUNT(*) FROM EXME_Donador WHERE Curp=?";
			pstmt = DB.prepareStatement(SQL_Paciente, null);
			pstmt.setString(1, Curp.trim());
			rs = pstmt.executeQuery();
			if (rs.next()) {
				Integer NC = rs.getInt(1);
				if (NC>0)
					log.saveWarning("trasplantes.pacexistente", "Este paciente ya se encuentra en la lista de espera");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
