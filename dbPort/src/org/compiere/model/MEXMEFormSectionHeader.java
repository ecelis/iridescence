package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.apache.commons.lang.StringUtils;
import org.compiere.util.CLogger;
import org.compiere.util.DB;

public class MEXMEFormSectionHeader extends X_EXME_FormSectionHeader {

	private static CLogger slog = CLogger.getCLogger(MEXMEFormSectionHeader.class);
	private static final long serialVersionUID = -6534673546743763186L;

	/**
	 * 
	 * @param ctx
	 * @param headerId
	 * @param ctaPacId
	 * @param trxName
	 * @return
	 * Este metodo regresa la lista de formas ya agregadas a la cuenta paciente que coincidan con el headerID
	 */
	public static List<MEXMEEncounterForms> getEncunterForms(final Properties ctx, final int headerId, final int ctaPacId, final String trxName) {
		final StringBuilder sql = new StringBuilder();
		sql.append("SELECT ");
		sql.append("  ef.* ");
		sql.append("FROM ");
		sql.append("  exme_encounterforms ef ");
		sql.append("  INNER JOIN exme_formsectionheader sh ");
		sql.append("  ON ef.exme_formsectionheader_id = sh.exme_formsectionheader_id ");
		sql.append("WHERE ");
		sql.append("  ef.type = ? AND ");
		sql.append("  sh.exme_formsectionheader_id = ? AND ");
		sql.append("  ef.exme_ctapac_id = ? ");
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name, "ef"));
		sql.append(" order by ef.created desc");

		final List<MEXMEEncounterForms> list = new ArrayList<MEXMEEncounterForms>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setString(1, MEXMEEncounterForms.TYPE_Other);
			pstmt.setInt(2, headerId);
			pstmt.setInt(3, ctaPacId);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				list.add(new MEXMEEncounterForms(ctx, rs, trxName));
			}
		} catch (Exception e) {
			slog.log(Level.SEVERE, null, e);
		} finally {
			DB.close(rs, pstmt);
		}
		return list;
	}
	
	/** 
	 * @param ctx 
	 * @param ctaPacId
	 * @param trxName
	 * Metodo para consultar las formas ya agregadas por algun usuario a la cuenta paciente
	 */	
	public static List<MEXMEEncounterForms> getEncunterForms(final Properties ctx, final int ctaPacId, final String trxName) {
		final StringBuilder sql = new StringBuilder();
		sql.append("SELECT ");
		sql.append("  ef.* ");
		sql.append("FROM ");
		sql.append("  exme_encounterforms ef ");
		sql.append("  inner join EXME_FormSectionHeader h on (ef.EXME_FormSectionHeader_ID = h.EXME_FormSectionHeader_ID and h.isactive = 'Y') ");
		sql.append("WHERE ");
		sql.append("  ef.type = ? AND ");
		sql.append("  ef.exme_ctapac_id = ? ");
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name, "ef"));
		sql.append(" order by ef.created desc");

		final List<MEXMEEncounterForms> list = new ArrayList<MEXMEEncounterForms>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setString(1, MEXMEEncounterForms.TYPE_Other);
			pstmt.setInt(2, ctaPacId);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				list.add(new MEXMEEncounterForms(ctx, rs, trxName));
			}
		} catch (Exception e) {
			slog.log(Level.SEVERE, null, e);
		} finally {
			DB.close(rs, pstmt);
		}
		return list;
	}

	/**
	 * 
	 * @param ctx
	 * @param value
	 * @param ctapacID
	 * @param orderByName
	 * @param trxName
	 * @return Este metodo regresa el listado de plantillas de formas filtradas por el value y ordenas o no por el name de las formas. 
	 */
	public static List<MEXMEFormSectionHeader> getLst(final Properties ctx, final String value, final int ctapacID, final boolean orderByName, final String trxName) {
		final StringBuilder sql = new StringBuilder();
		sql.append("SELECT ");
		sql.append("  * ");
		sql.append("FROM ");
		sql.append("  EXME_FormSectionHeader ");
		sql.append("WHERE ");
		sql.append("  (upper(name) LIKE ? OR ");
		sql.append("  upper(value) LIKE ?) ");
		sql.append("  AND EXME_FormSectionHeader.EXME_FormSectionHeader_id not in ( ");
		sql.append("  select h.EXME_FormSectionHeader_id ");
		sql.append("  from exme_encounterforms f ");
		sql.append("  inner join EXME_FormSectionHeader h on f.EXME_FormSectionHeader_id = h.EXME_FormSectionHeader_id and h.type = 'D' ");
		sql.append("  where f.exme_ctapac_id = ? ");
		sql.append("  and f.isactive = 'Y' ");
		sql.append("  ) ");		
		sql.append(" AND EXME_FormSectionHeader.isactive = 'Y'  ");		
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name));
		if (orderByName) {
			sql.append(" order by name");
		} else {
			sql.append(" order by value");
		}
		final List<MEXMEFormSectionHeader> list = new ArrayList<MEXMEFormSectionHeader>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			final String valueAux = "%" + StringUtils.upperCase(StringUtils.defaultIfEmpty(value, "")) + "%";
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setString(1, valueAux);
			pstmt.setString(2, valueAux);
			pstmt.setInt(3, ctapacID);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				list.add(new MEXMEFormSectionHeader(ctx, rs, trxName));
			}
		} catch (Exception e) {
			slog.log(Level.SEVERE, null, e);
		} finally {
			DB.close(rs, pstmt);
		}
		return list;
	}
	
	

	public MEXMEFormSectionHeader(Properties ctx, int EXME_FormSectionHeader_ID, String trxName) {
		super(ctx, EXME_FormSectionHeader_ID, trxName);
	}

	public MEXMEFormSectionHeader(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}
	
	/** @return TRUE if the Type == 'D' (Lama) */
	public boolean isDischarge() {
		return MEXMEFormSectionHeader.TYPE_Discharge.equals(getType());
	}

}
