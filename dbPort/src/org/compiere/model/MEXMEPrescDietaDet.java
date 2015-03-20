package org.compiere.model;

import java.sql.ResultSet;
import java.util.List;
import java.util.Properties;

import org.compiere.util.CLogger;

public class MEXMEPrescDietaDet extends X_EXME_PrescDietaDet {

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	@SuppressWarnings("unused")
	private static CLogger		log					= CLogger.getCLogger(MEXMEPrescDietaDet.class);

	public MEXMEPrescDietaDet(Properties ctx, int EXME_PrescDietaDet_ID, String trxName) {
		super(ctx, EXME_PrescDietaDet_ID, trxName);
	}
	
	public MEXMEPrescDietaDet(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}
	
	/**
	 * Prescription detail
	 * 
	 * @param Properties ctx
	 * @param int prescID
	 * @param String trxName
	 * @return List<LabelValueBean>
	 */
	public static List<MEXMEPrescDietaDet> get(Properties ctx, int prescID, String trxName) {
		return new Query(ctx, Table_Name, " EXME_PRESCDIETA_ID=? ", trxName)//
			.setParameters(prescID)//
			.setOnlyActiveRecords(true)//
			.addAccessLevelSQL(true)//
			.setOrderBy(COLUMNNAME_Sequence)//
			.list();
	}

	/**
	 * rsolorzano
	 * Regresa el listado de dietas de una prescripcion
	 * @param Properties ctx
	 * @param int prescID
	 * @param String trxName
	 * @return List<LabelValueBean>
	 * @deprecated
	 *
	public static List<LabelValueBean> getDetalle(Properties ctx, int prescID, String trxName) {

		List<LabelValueBean> lista = new ArrayList<LabelValueBean>();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT DET.EXME_DIETA_ID AS ID, DIETA.NAME AS NAME FROM EXME_PRESCDIETADET DET ");
		sql.append("INNER JOIN EXME_DIETA DIETA ON DIETA.EXME_DIETA_ID = DET.EXME_DIETA_ID ");
		sql.append("WHERE DET.EXME_PRESCDIETA_ID = ? ORDER BY NAME");

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setInt(1, prescID);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				LabelValueBean dieta = new LabelValueBean(rs.getString("NAME"), rs.getString("ID"));
				lista.add(dieta);
			}
		}
		catch (Exception e) {
			log.log(Level.SEVERE, "getCitasMedico", e);
		}
		finally {
			DB.close(rs, pstmt);
		}
		return lista;
	}*/

}
