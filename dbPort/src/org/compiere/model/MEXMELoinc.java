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

public class MEXMELoinc extends X_EXME_Loinc {

	private static final long serialVersionUID = -5194519832362489827L;
	private static CLogger s_log = CLogger.getCLogger(MEXMELoinc.class);

	public MEXMELoinc(Properties ctx, int EXME_Loinc_ID, String trxName) {
		super(ctx, EXME_Loinc_ID, trxName);

	}

	public MEXMELoinc(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);

	}

	/**
	 * Obtiene los estudios excluyendo los ids enviados
	 * 
	 * @param ctx
	 * @param value
	 *            Valor a buscar
	 * @param notIn
	 *            Ids a excluir
	 * @param maxNum
	 *            Maximo de registros
	 * @param trxName
	 * @return
	 */
	public static List<MEXMELoinc> get(Properties ctx, String value, Integer[] notIn, int maxNum, String trxName) {
		StringBuilder st = new StringBuilder("SELECT * FROM EXME_Loinc WHERE isActive = 'Y' ");
		if (notIn != null && notIn.length > 0) {
			st.append("AND EXME_Loinc_ID not in (").append(StringUtils.join(notIn, ",")).append(") ");
		}
		if (!StringUtils.isEmpty(value)) {
			st.append("AND  (upper(value) LIKE upper('%").append(value.toUpperCase()).append("%' ) OR ");
			st.append("  upper(shortname) LIKE upper('%").append(value.toUpperCase()).append("%' )) ");
		}
		
		if (DB.isOracle()) {
			st.append(" AND rownum < ").append(maxNum).append(" ");
		}

		st.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", "EXME_Loinc"));
		st.append(" order by value");
		
		if (DB.isPostgreSQL()) {
			st = new StringBuilder(DB.getDatabase().addPagingSQL(st.toString(), 1, maxNum-1));
		}
		
		List<MEXMELoinc> lista = new ArrayList<MEXMELoinc>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(st.toString(), trxName);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				lista.add(new MEXMELoinc(ctx, rs, trxName));
			}
			
		} catch (Exception e) {
			s_log.log(Level.SEVERE, st.toString(), e);
		} finally {
			DB.close(rs,pstmt);
		}
		return lista;
	}

//	public static List<LabelValueBean> getAllInfo(Properties ctx, String like) throws Exception {
//
//		List<LabelValueBean> lst = new ArrayList<LabelValueBean>();
//		final StringBuilder sql = new StringBuilder();
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//
//		sql.append("SELECT UPPER(lc.description), lc.EXME_Loinc_id FROM EXME_Loinc lc ");
//		if (like != null ) {
//			sql.append("WHERE UPPER(lc.description) LIKE  ? ");
//		}
//		sql.append(" ORDER BY lc.description ");
//		pstmt = null;
//		rs = null;
//
//		try {
//			pstmt = DB.prepareStatement(sql.toString(), null);
//			if (like != null) {
//				pstmt.setString(1, like);
//			}
//			rs = pstmt.executeQuery();
//
//			while (rs.next()) {
//				LabelValueBean partner = new LabelValueBean(rs.getString(1), rs.getString(2));
//				lst.add(partner);
//			}
//               
//		} catch (Exception e) {
//    		s_log.log(Level.SEVERE, "Error:" +sql, e);
//    	}finally {
//    		DB.close(rs,pstmt);
//    	}
//		return lst;
//	}
	
	private double maxLevel;
	private double minLevel;

	public double getMaxLevel() {
		return maxLevel;
	}

	public void setMaxLevel(double maxLevel) {
		this.maxLevel = maxLevel;
	}

	public double getMinLevel() {
		return minLevel;
	}

	public void setMinLevel(double minLevel) {
		this.minLevel = minLevel;
	}

	@Override
	public Object onCompare(int type) {
		Object comparable;
		if (type == COLUMNNAME_Value.hashCode()) {
			comparable = getValue();
		} else if (type == COLUMNNAME_ShortName.hashCode()) {
			comparable = getShortName();
		} else if (type == COLUMNNAME_Long_Common_Name.hashCode()) {
			comparable = getLong_Common_Name();
		} else if (type == COLUMNNAME_Description.hashCode()) {
			comparable = getDescription();
		} else {
			comparable = super.onCompare(type);
		}
		return comparable;
	}

	/** Obtiene los códigos loinc relacionados a un CPT */
	public static List<MEXMELoinc> getLoincFromCPT(Properties ctx, int m_product_id, String trxName) {
		final StringBuilder sql = new StringBuilder();
		sql.append(" INNER JOIN EXME_IntervencionLoinc il ON (EXME_Loinc.EXME_Loinc_ID=il.EXME_Loinc_ID) ");
		sql.append(" INNER JOIN M_Product p ON (p.EXME_Intervencion_ID=il.EXME_Intervencion_ID ");
		sql.append(" AND p.M_Product_ID=?) ");
		return new Query(ctx, Table_Name, "", trxName)//
			.setOnlyActiveRecords(true)//
			.addAccessLevelSQL(true)//
			.setJoins(sql)//
			.setParameters(m_product_id)//
			.list();
	}

	/** Obtiene el rango del loinc de acuerdo a la informacion del paciente: Sexo / Edad */
	public X_EXME_LoincRange getRange(final MEXMEPaciente paciente) {
		if (paciente != null) {
			// edad paciente : numerico
//			final CalcularEdadAMD age = paciente.getAge();
			double edadpac = paciente.getAge().getAge();
//			if (age.getMeses() > 0) {
//				edadpac += age.getMeses() / 12d;
//			}
//			if (age.getDias() > 0) {
//				Calendar cal = Calendar.getInstance();
//				edadpac += age.getDias() / (cal.isLenient() ? 366d : 365d);
//			}// FXME Mejorar mtto .... agregar unidad de medida para cada campo..... (lista referencia YEAR / MONTH / DAY
			final StringBuilder sql = new StringBuilder();
			sql.append("      EXME_Loinc_ID=? ");
			sql.append(" AND (EdadMinima IS NULL OR EdadMinima<=?) ");
			sql.append(" AND (EdadMaxima IS NULL OR EdadMaxima>=?) ");
			sql.append(" AND (Sexo IS NULL OR Sexo=?) ");

			return new Query(getCtx(), X_EXME_LoincRange.Table_Name, sql.toString(), null)//
				.setParameters(getEXME_Loinc_ID(), edadpac, edadpac, paciente.getSexo())//
				.setOnlyActiveRecords(true)//
				.addAccessLevelSQL(true)//
				.setOrderBy(" EdadMinima, EdadMaxima, Sexo ")//
				.first();
		}
		return null;
	}
	
	/**
	 * Get By Value
	 *
	 * @param value
	 * @return LOINC
	 */
	public static MEXMELoinc getFromValue(final Properties ctx, final String value, final String trxName) {
		return new Query(ctx, Table_Name, " IsActive = 'N' AND Value = ?", trxName)
					.setParameters(value)
					.first();
	}
}
