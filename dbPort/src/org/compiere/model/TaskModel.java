/**
 * 
 */
package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.apache.commons.lang.StringUtils;
import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.Env;

/**
 * Dashboard
 * 
 * @author Lorena Lama
 */
public class TaskModel {

	public static class MEXMECtaPacTask extends MEXMECtaPac {
		private static final long	serialVersionUID	= 1L;

		private boolean				medication;
		private boolean				prn;
		private boolean				labs;
		private boolean				xRay;
		private boolean				services;
		private boolean				other;
		private boolean				vitals;

		public MEXMECtaPacTask(final Properties ctx, final int EXME_CtaPac_ID, final String trxName) {
			super(ctx, EXME_CtaPac_ID, trxName);
		}

		public MEXMECtaPacTask(final Properties ctx, final ResultSet rs, final String trxName) {
			super(ctx, rs, trxName);
		}

		/** @see java.lang.Object#equals(java.lang.Object) */
		@Override
		public boolean equals(final Object obj) {
			if (this == obj || obj instanceof Integer && getEXME_CtaPac_ID() == (Integer) obj) {
				return true;
			}
			if (!super.equals(obj)) {
				return false;
			}
			if (getClass() != obj.getClass()) {
				return false;
			}
			final MEXMECtaPacTask other = (MEXMECtaPacTask) obj;
			if (getEXME_CtaPac_ID() != other.getEXME_CtaPac_ID()) {
				return false;
			}
			return true;
		}

		/** @see java.lang.Object#hashCode() */
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + getEXME_CtaPac_ID();
			return result;
		}

		public boolean isLabs() {
			return labs;
		}

		public boolean isMedication() {
			return medication;
		}

		public boolean isOther() {
			return other;
		}

		public boolean isPrn() {
			return prn;
		}

		public boolean isServices() {
			return services;
		}

		public boolean isVitals() {
			return vitals;
		}

		public boolean isxRay() {
			return xRay;
		}

		public void setLabs(final boolean labs) {
			this.labs = labs;
		}

		public void setMedication(final boolean medication) {
			this.medication = medication;
		}

		public void setOther(final boolean other) {
			this.other = other;
		}

		public void setPrn(final boolean prn) {
			this.prn = prn;
		}

		public void setServices(final boolean services) {
			this.services = services;
		}

		public void setVitals(final boolean vitals) {
			this.vitals = vitals;
		}

		public void setxRay(final boolean xRay) {
			this.xRay = xRay;
		}

	}

	private class QueryBuilder {

		private List<Object>	params;
		private final Timestamp	dateIni;
		private final Timestamp	dateFin;

		public QueryBuilder(final Timestamp dateIni, final Timestamp dateFin) {
			super();
			this.dateIni = dateIni;
			this.dateFin = dateFin;
			setParamters();
		}

		public void addDiarioEnf() {
			if (isByNurse() && Env.getEXME_Enfermeria_ID(ctx) > 0) {
				params.add(Env.getEXME_Enfermeria_ID(ctx));
			}
		}

		public List<Object> getParams() {
			return params;
		}

		/** PENDING MEDICATIONS (Only medical staff / entire organization) */
		private void setMedicationParams() {
			addDiarioEnf();
			params.add(dateIni);
			params.add(dateFin);
		}

		public void setParamters() {
			params = new ArrayList<Object>();
			final boolean medicalStaff = Env.getEXME_Medico_ID(ctx) > 0 || Env.getEXME_Enfermeria_ID(ctx) > 0 || Env.getEXME_Pharmacist_ID(ctx) > 0;
			// (Only medical staff / entire organization)
			if (medicalStaff || !isByEstServ()) {
				setMedicationParams();
				if (addPrn) {
					setPrnParams();
				}
			}
			// Studies
			setStudiesParams();
			// Vitals
			setVitalsParams();
		}

		/** PRN (Only medical staff / entire organization) */
		private void setPrnParams() {
			addDiarioEnf();
			params.add(dateIni);
			params.add(dateFin);
		}

		/** PENDING SERVICES */
		private void setStudiesParams() {
			addDiarioEnf();
			params.add(dateIni);
			params.add(dateFin);
			if (byEstServ) {// Requested To
				params.add(Env.getM_Warehouse_ID(ctx));
			}
		}

		/** PENDING VITALS */
		private void setVitalsParams() {
			addDiarioEnf();
			final Calendar calFin = Calendar.getInstance();
			calFin.setTime(dateIni);
			params.add(Constantes.getDosDigitos().format(calFin.get(Calendar.HOUR_OF_DAY)));
			if (byEstServ) {
				params.add(Env.getEXME_EstServ_ID(ctx));
			}
			params.add(dateIni);
			// params.add(dateFin);
		}
	}

	private static CLogger	SLOG	= CLogger.getCLogger(TaskModel.class);

	private Properties		ctx		= null;
	private boolean			byEstServ;
	private boolean			byNurse;
	private boolean			addPrn;
	private StringBuilder	sql;
	private Query			queryOther;


	public TaskModel(final Properties ctx) {
		this.ctx = ctx;
	}

	public void appendBetween(final String column) {
		// remove all seconds
		if (DB.isOracle()) {
			sql.append(" AND TRUNC(").append(column).append(",'mi') BETWEEN ? AND ? ");
		} else if (DB.isPostgreSQL()) {
			sql.append(" AND DATE_TRUNC('minute', ").append(column).append(") BETWEEN ? AND ? ");
		} else {
			sql.append(" AND ").append(column).append(" BETWEEN ? AND ? ");
		}
	}

	public void appendDiarioEnf(final String alias) {
		if (isByNurse() && Env.getEXME_Enfermeria_ID(ctx) > 0) {
			sql.append(" INNER JOIN EXME_DiarioEnf diario ON (diario.EXME_CtaPac_ID=").append(alias).append(".EXME_CtaPac_ID ");
			sql.append(" AND diario.isActive='Y' ");
			sql.append(" AND diario.EXME_Enfermeria_ID=? ");
			sql.append(" AND diario.Fecha_Egreso IS NULL) ");
		}
	}

	/**
	 * 
	 * @param dateIni
	 * @param dateFin
	 * @return
	 */
	public List<MEXMECtaPacTask> getPatients(final Timestamp dateIni, final Timestamp dateFin) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		final String sql = getSql().toString();
		final List<MEXMECtaPacTask> retValue = new ArrayList<MEXMECtaPacTask>();
		final QueryBuilder query = new QueryBuilder(dateIni, dateFin);
		try {
			pstmt = DB.prepareStatement(sql, null);
			DB.setParameters(pstmt, query.getParams());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				MEXMECtaPacTask task = new MEXMECtaPacTask(ctx, rs.getInt(1), null)  ;
				final int index = retValue.indexOf(task);
				if (index < 0) {
					task.setOther(getQueryOther().setParameters(task.getEXME_CtaPac_ID()).count() > 0);
					retValue.add(task);
				} else {
					task = retValue.get(index);
				}
				if (StringUtils.equalsIgnoreCase("meds", rs.getString("TypeMed"))) {
					task.setMedication(true);
				}
				if (addPrn && StringUtils.equalsIgnoreCase("prn", rs.getString("TypePRN"))) {
					task.setMedication(true);
				}
				final String typeServ = rs.getString("TypeServ");
				if (StringUtils.containsIgnoreCase("serv", typeServ)) {
					task.setServices(true);
				}
				if (StringUtils.containsIgnoreCase(X_M_Product.PRODUCTCLASS_Laboratory, typeServ)) {
					task.setLabs(true);
				}
				if (StringUtils.containsIgnoreCase(X_M_Product.PRODUCTCLASS_XRay, typeServ)) {
					task.setxRay(true);
				}
				task.setVitals(StringUtils.equalsIgnoreCase("vitals", rs.getString("TypeSV")));
			}
		} catch (final Exception e) {
			SLOG.log(Level.SEVERE, sql + Constantes.NEWLINE + query.getParams().toString(), e);
		} finally {
			DB.close(rs, pstmt);
		}
		return retValue;

	}

	/** PRN (Only medical staff / entire organization) */
	private void getPrnSQL() {
		sql.append(" SELECT DISTINCT prx.EXME_CtaPac_ID, null as TypeMed, null as TypeServ, null as TypeSV, 'prn' as TypePRN ");
		sql.append(" FROM EXME_PrescRx prx ");
		appendDiarioEnf("prx");
		sql.append(" INNER JOIN EXME_PrescRxDet prxdet ON (prx.EXME_PrescRx_ID=prxdet.EXME_PrescRx_ID ");
		sql.append(" AND prxdet.isActive='Y' AND COALESCE(prxdet.IsPRN,'N')='Y')");
		sql.append(" WHERE prx.isActive='Y' ");
		appendBetween("prxdet.StartDate");
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", I_EXME_PrescRX.Table_Name, "prx"));
	}

	public Query getQueryOther() {
		if (queryOther == null) {
			queryOther = new Query(ctx, I_EXME_CuidadosPac.Table_Name, " EXME_CtaPac_ID=? ", null)//
				.setOnlyActiveRecords(true)//
				.addAccessLevelSQL(true);
		}
		return queryOther;
	}

	public StringBuilder getSql() {
		if (sql == null) {
			initSQL();
		}
		return sql;
	}

	/** PENDING MEDICATIONS (Only medical staff / entire organization) */
	private void getSQLMedication() {
		sql.append(" SELECT DISTINCT plan.EXME_CtaPac_ID, 'meds' as TypeMed, null as TypeServ, null as TypeSV, null as TypePRN ");
		sql.append(" FROM EXME_PlanMed plan ");
		appendDiarioEnf("plan");
		sql.append(" INNER JOIN EXME_PlanMedLine line ON (plan.EXME_PlanMed_ID=line.EXME_PlanMed_ID AND line.Estatus<>'AC')");
		sql.append(" WHERE line.isActive='Y' ");
		appendBetween("line.ProgDate");
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", I_EXME_PlanMed.Table_Name, "plan"));
	}

	/** PENDING MEDICATIONS */
	private void getSQLStudies() {
		sql.append(" SELECT DISTINCT EXME_ActPacienteIndH.EXME_CtaPac_ID, null as TypeMed, ");
		sql.append(" CASE WHEN p.ProductClass IN ('LA','XR') THEN p.ProductClass ");
		sql.append(" ELSE 'Serv' END as TypeServ, null as TypeSV, null as TypePRN ");
		sql.append(" FROM EXME_ActPacienteIndH ");
		appendDiarioEnf("EXME_ActPacienteIndH");
		sql.append(" INNER JOIN EXME_ActPacienteInd ind ON (EXME_ActPacienteIndH.EXME_ActPacienteIndH_ID=ind.EXME_ActPacienteIndH_ID ");
		sql.append(" AND ind.isActive='Y' AND ind.Estatus<>'A') ");// Not Cancelled
		sql.append(" INNER JOIN M_Product p ON (ind.M_Product_ID=p.M_Product_ID AND p.ProductClass IN (");
		sql.append(DB.TO_STRING(X_M_Product.PRODUCTCLASS_Laboratory)).append(",");
		sql.append(DB.TO_STRING(X_M_Product.PRODUCTCLASS_XRay)).append(",");
		sql.append(DB.TO_STRING(X_M_Product.PRODUCTCLASS_HomeHealt)).append(",");
		sql.append(DB.TO_STRING(X_M_Product.PRODUCTCLASS_Ambulance)).append(",");
		sql.append(DB.TO_STRING(X_M_Product.PRODUCTCLASS_Blood)).append(",");
		sql.append(DB.TO_STRING(X_M_Product.PRODUCTCLASS_Hospice)).append(",");
		sql.append(DB.TO_STRING(X_M_Product.PRODUCTCLASS_Procedures)).append(",");
		sql.append(DB.TO_STRING(X_M_Product.PRODUCTCLASS_FQHCRHC)).append(",");
		sql.append(DB.TO_STRING(X_M_Product.PRODUCTCLASS_OtherService));
		sql.append(")) ");
		sql.append(" WHERE EXME_ActPacienteIndH.IsActive='Y' ");
		sql.append(" AND EXME_ActPacienteIndH.IsService='Y' ");
		appendBetween("EXME_ActPacienteIndH.DatePromised");
		sql.append(" AND EXME_ActPacienteIndH.DocStatus <> 'VO' ");// Not cancelled
		if (byEstServ) {// Requested To
			sql.append(" AND EXME_ActPacienteIndH.M_Warehouse_ID=? ");
		}
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", I_EXME_ActPacienteIndH.Table_Name));
	}

	/** PENDING VITALS */
	private void getSQLVitals() {
		sql.append(" SELECT DISTINCT ctapac.EXME_CtaPac_ID, null as TypeMed, null as TypeServ, 'vitals' as TypeSV, null as TypePRN ");
		sql.append(" FROM EXME_CtaPac ctapac ");
		appendDiarioEnf("ctapac");
		sql.append(" INNER JOIN EXME_Area area ON (area.EXME_Area_ID= ctapac.EXME_Area_ID) ");
		sql.append(" INNER JOIN EXME_Frequency2 freq2 ON (freq2.EXME_Frequency2_ID=area.EXME_Frequency2_ID AND freq2.IsActive='Y') ");
		sql.append(" INNER JOIN EXME_Frequency3 freq3 ON (freq3.EXME_Frequency2_ID=freq2.EXME_Frequency2_ID ");
		sql.append(" AND freq3.IsActive='Y' AND to_char(freq3.hour, 'hh24')=?) ");

		sql.append(" WHERE  ctapac.encounterstatus in (");
		sql.append(DB.TO_STRING(X_EXME_CtaPac.ENCOUNTERSTATUS_Admission)).append(", ");
		sql.append(DB.TO_STRING(X_EXME_CtaPac.ENCOUNTERSTATUS_Predischarge)).append(") ");
		// encounter service unit
		if (byEstServ) {
			sql.append(" AND ctapac.EXME_EstServ_ID=? ");
		}
		// validate admit/dischargedate
		if (DB.isOracle()) {// Admit Date must be prior to the initial date, and discharge date must be null, or after final date
			sql.append(" AND TRUNC(ctapac.DateOrdered,'mi')<=? ");
			// sql.append(" AND (ctapac.FechaAlta IS NULL OR TRUNC(ctapac.FechaAlta,'mi')>=?) ");
		} else if (DB.isPostgreSQL()) {
			sql.append(" AND DATE_TRUNC('minute',ctapac.DateOrdered)<=? ");
			// sql.append(" AND (ctapac.FechaAlta IS NULL OR DATE_TRUNC('minute',ctapac.FechaAlta)>=?) ");
		}
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", I_EXME_CtaPac.Table_Name, "ctapac"));
	}

	public void initSQL() {
		final boolean medicalStaff = Env.getEXME_Medico_ID(ctx) > 0 || Env.getEXME_Enfermeria_ID(ctx) > 0 || Env.getEXME_Pharmacist_ID(ctx) > 0;
		sql = new StringBuilder();
		sql.append(" SELECT DISTINCT dual.EXME_CtaPac_ID, ");
		if (DB.isOracle()) { // Note: LISTAGG para Oracle11 !!
			sql.append(" wm_concat(dual.TypeServ) ");
		} else if (DB.isPostgreSQL()) {
			sql.append(" string_agg(dual.TypeServ, ',') ");
		} else {
			sql.append(" null ");
		}
		sql.append("as TypeServ, MAX(dual.TypeMed) as TypeMed, MAX(dual.TypeSV) as TypeSV, MAX(dual.TypePRN) as TypePRN ");
		sql.append(" FROM ( ");
		// (Only medical staff / entire organization)
		if (medicalStaff || !isByEstServ()) {
			getSQLMedication();
			sql.append(" UNION ");
			if (addPrn) {
				getPrnSQL();
				sql.append(" UNION ");
			}
		}
		// Studies
		getSQLStudies();
		sql.append(" UNION ");
		// Vitals
		getSQLVitals();
		sql.append(" ) dual ");
		sql.append(" GROUP BY dual.exme_ctapac_id ");
	}

	public boolean isByEstServ() {
		return byEstServ;
	}

	public boolean isByNurse() {
		return byNurse;
	}

	public void setByEstServ(final boolean byEstServ) {
		if (sql != null && this.byEstServ != byEstServ) {
			sql = null;
		}
		this.byEstServ = byEstServ;
	}

	public void setByNurse(final boolean byNurse) {
		if (sql != null && this.byNurse != byNurse) {
			sql = null;
		}
		this.byNurse = byNurse;
	}

	public void setAddPrn(boolean addPrn) {
		if (sql != null && this.addPrn != addPrn) {
			sql = null;
		}
		this.addPrn = addPrn;
	}
}
