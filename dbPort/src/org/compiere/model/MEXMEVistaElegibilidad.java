package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;

public class MEXMEVistaElegibilidad extends X_EXME_VistaElegibilidad {
	private static final long serialVersionUID = 1L;
	/**	Static Logger				*/
	private static CLogger		s_log = CLogger.getCLogger (MEXMEVistaElegibilidad.class);

	public MEXMEVistaElegibilidad(Properties ctx, int EXME_VistaBeneficios_ID, String trxName) {
		super(ctx, EXME_VistaBeneficios_ID, trxName);
	}
	
	public MEXMEVistaElegibilidad(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}
	
	public static MEXMEVistaElegibilidad[] gets(Properties ctx, int EXME_Paciente_ID) {
		List<MEXMEVistaElegibilidad> list = getAll(ctx, EXME_Paciente_ID);
		MEXMEVistaElegibilidad[] regs = new MEXMEVistaElegibilidad[list.size()];
		list.toArray(regs);
		return regs;
	}
	
	public static List<MEXMEVistaElegibilidad> getAll(Properties ctx, int EXME_Paciente_ID) {
		ArrayList<MEXMEVistaElegibilidad> list = new ArrayList<MEXMEVistaElegibilidad>();

		StringBuffer sql = new StringBuffer(Constantes.INIT_CAPACITY_ARRAY);

		sql.append("SELECT * FROM EXME_VISTAELEGIBILIDAD WHERE IsActive = 'Y' AND EXME_PACIENTE_ID = ? ");
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name));

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setInt(1, EXME_Paciente_ID);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				MEXMEVistaElegibilidad reg = new MEXMEVistaElegibilidad(ctx, rs, null);
				list.add(reg);
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, "gets: " + sql, e);
		} finally {
			DB.close(rs, pstmt);
		}

		return list;
	}
	
	public static MEXMEVistaElegibilidad get(Properties ctx, int EXME_BeneficiosH_ID) {
		MEXMEVistaElegibilidad res = null;
		
		StringBuffer sql = new StringBuffer(Constantes.INIT_CAPACITY_ARRAY);
		
		sql.append("SELECT * FROM EXME_VISTAELEGIBILIDAD WHERE IsActive = 'Y' AND EXME_BENEFICIOSH_ID = ? ");
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name));
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setInt(1, EXME_BeneficiosH_ID);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				res = new MEXMEVistaElegibilidad(ctx, rs, null);
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, "get: " + sql, e);
		} finally {
			DB.close(rs, pstmt);
		}
		
		return res;
	}
	
	/**
	 * Regresa la lista de beneficiosh_ID a partir de un paciente
	 * siempre y cuando no este expirada, o este ligada a un encuentro en particular
	 * @param ctx
	 * @param EXME_Paciente_ID Identificador del paciente
	 * @param EXME_CtaPac_ID Identificador de la cuenta
	 * @return
	 */
	public static List<Integer> getBeneficiosH(Properties ctx, int EXME_Paciente_ID, int EXME_CtaPac_ID, int C_BPartner_ID, int EXME_PacienteAseg_ID) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		List<Object> params = new ArrayList<Object>();
		StringBuffer sql = new StringBuffer(Constantes.INIT_CAPACITY_ARRAY);
		
		sql.append(" SELECT ELEG.EXME_BeneficiosH_ID ")
		   .append(" FROM EXME_VistaHElegibilidad ELEG, ")
		   .append(" (SELECT COALESCE((SELECT DISTINCT CC.RECORD_ID ")
		   .append(" FROM EXME_CLAIMCODES CC ")
		   .append(" WHERE CC.EXME_CtaPac_ID = ? ") //EXME_CtaPac_ID
		   .append(" AND CC.AD_TABLEORIG_ID = ? ") //1201051
		   .append(" AND CC.RECORDORIG_ID = ? ") // EXME_PacienteAseg_ID
		   .append(" AND CC.AD_TABLE_ID = ? ") // 1201043
		   .append(" AND CC.ISACTIVE = 'Y'),0) EXME_BENEFICIOSH_ID FROM DUAL) LIGA ")
		   .append(" WHERE ELEG.EXME_Paciente_ID = ? ");
		params.add(EXME_CtaPac_ID);
		params.add(MEXMEPacienteAseg.Table_ID);
		params.add(EXME_PacienteAseg_ID);
		params.add(MEXMEBeneficiosH.Table_ID);
		params.add(EXME_Paciente_ID);
		if (C_BPartner_ID >0 ){
			sql.append(" AND ELEG.C_BPartner_ID = ?");
			params.add(C_BPartner_ID);
		}
		sql.append(" AND ((LIGA.EXME_BENEFICIOSH_ID > 0 AND ELEG.EXME_BENEFICIOSH_ID = LIGA.EXME_BENEFICIOSH_ID) ")
		   .append(" OR (LIGA.EXME_BENEFICIOSH_ID = 0))");
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_ID, "ELEG"));
		sql.append(" GROUP BY ELEG.EXME_BENEFICIOSH_ID, ELEG.STARTDATE ")
		   .append(" ORDER BY ELEG.STARTDATE DESC");
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			DB.setParameters(pstmt, params);
			rs = pstmt.executeQuery();
			//return the last insurance verification or the previous linked
			if (rs.next()) {
				list.add(rs.getInt(1));
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, "gets: " + sql, e);
		} finally {
			DB.close(rs, pstmt);
		}

		return list;
	}
}
