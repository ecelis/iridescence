package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.util.LabelValueBean;
import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.Env;

public class MEXMECtaPacMed extends X_EXME_CtaPacMed {

	private static final long serialVersionUID = -2146838527403717528L;
	private static CLogger slog = CLogger.getCLogger(MEXMECtaPacMed.class);

	private static CLogger s_log = CLogger.getCLogger(MEXMECtaPacMed.class);
	
	public MEXMECtaPacMed(Properties ctx, int EXME_CtaPacMed_ID, String trxName) {
		super(ctx, EXME_CtaPacMed_ID, trxName);
	}

	public MEXMECtaPacMed(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}
	
	public static List<MEXMEMedico> getMed(Properties ctx, int ctaPacId, String trxName) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ");
		sql.append("  * ");
		sql.append("FROM ");
		sql.append("  exme_medico ");
		sql.append("WHERE ");
		sql.append("  exme_medico_id IN ");
		sql.append("  (SELECT DISTINCT ");
		sql.append("      (med.exme_medico_id) ");
		sql.append("    FROM ");
		sql.append("      exme_ctapacmed ctaMed ");
		sql.append("      INNER JOIN exme_medico med ");
		sql.append("      ON ctamed.exme_medico_id = med.exme_medico_id ");
		sql.append("      INNER JOIN exme_medico_org mo ");
		sql.append("      ON ctamed.ad_org_id = mo.ad_org_id ");
		sql.append("    WHERE ");
		sql.append("      ctamed.exme_ctapac_id = ? AND ");
		sql.append("      mo.isserviceprovider = 'N' ");
		sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, sql.toString(), MEXMECtaPacMed.Table_Name, "ctaMed"));
		sql.append(" ) ");

		List<MEXMEMedico> lstDiv = new ArrayList<MEXMEMedico>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setInt(1, ctaPacId);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				lstDiv.add(new MEXMEMedico(ctx, rs, trxName));
			}
			
		} catch (Exception e) {
			s_log.log(Level.SEVERE, null, e);
		} finally {
			DB.close(rs, pstmt);
		}
		return lstDiv;
	}

	/**
	 * Obtenemos el siguiente numero de secuencia que debe ser asignado a un registro se obtiene por cuenta paciente y tipo
	 * 
	 * @param ctaPacID
	 * @param type
	 * @return
	 */
	public static int getSequence(int ctaPacID, String type, String trxName) {
		int sequence = 0;

		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			sql.append("select * from exme_ctapacmed where exme_ctapac_id = ? and type = ?  order by created desc");

			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, ctaPacID);
			pstmt.setString(2, type);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				sequence = rs.getInt("sequence") + 1;
			}

		} catch (Exception e) {
			slog.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
		}

		return sequence;
	}

	

	/**
	 * Obtenemos las relaciones de una ctaPac agregando un filtro por tipo de medico
	 * @param ctaPacID
	 * @param type
	 * @param trxName
	 * @return List<MEXMList<MEXMECtaPacMed>ECtaPacMed>
	 */
	public static List<MEXMECtaPacMed> getActive(int ctaPacID, String type, String trxName) {
		
		List<MEXMECtaPacMed> list = new ArrayList<MEXMECtaPacMed>();
		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			sql.append("SELECT * FROM EXME_CtaPacMed")
			.append(" WHERE EXME_CtaPac_ID = ? ");
			if (StringUtils.isNotEmpty(type)){
				if (type.equalsIgnoreCase(Constantes.OTHERPHYSICIAN)){
					sql.append(" AND Type not in (?, ?) ");
				}else{
					sql.append(" AND Type = ? ");
				}
			}
			
			sql.append(" AND IsActive ='Y'")
			.append(" ORDER By Created DESC ");

			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, ctaPacID);
			if (StringUtils.isNotEmpty(type)){
				if (type.equalsIgnoreCase(Constantes.OTHERPHYSICIAN)){
					pstmt.setString(2, TYPE_Attending);
					pstmt.setString(3, TYPE_Admitting);
				}else{
					pstmt.setString(2, type);
				}
			}
			
			sql.append(MEXMELookupInfo.addAccessLevelSQL(Env.getCtx(), " ", Table_Name));
			rs = pstmt.executeQuery();

			while (rs.next()) {
				list.add(new MEXMECtaPacMed(Env.getCtx(), rs, trxName));
			}

		} catch (Exception e) {
			slog.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
		}

		return list;
	}
	
	/**
	 * @author gvaldez
	 * 
	 * @param ctapacid
	 *            - ID de la cuenta paciente
	 * 
	 * @return
	 * @throws Exception
	 */
	public static List<LabelValueBean> getBeanPhysicians(int ctaPacID, String trxName)  {
		
		List<LabelValueBean> list = new ArrayList<LabelValueBean>();
		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			sql.append("SELECT MIN(EXME_CtaPacMed.EXME_CtaPacMed_ID), EXME_Medico.Nombre_Med ")
			.append(" FROM EXME_CtaPacMed")
			.append(" INNER JOIN EXME_Medico ON EXME_Medico.EXME_Medico_ID = EXME_CtaPacMed.EXME_Medico_ID ")
			.append(" WHERE EXME_CtaPacMed.EXME_CtaPac_ID = ? ")
			.append(" AND EXME_CtaPacMed.IsActive ='Y'");
			sql.append(MEXMELookupInfo.addAccessLevelSQL(Env.getCtx(), " ", Table_Name))
			.append(" GROUP By EXME_Medico.Nombre_Med ")
			.append(" ORDER By EXME_Medico.Nombre_Med ");
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, ctaPacID);
			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				LabelValueBean lvb = new LabelValueBean(rs.getString(2), rs.getString(1));
				list.add(lvb);
			}

		} catch (Exception e) {
			slog.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
		}
		return list;
	}
	
}
