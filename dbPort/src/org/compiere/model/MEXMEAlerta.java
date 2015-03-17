package org.compiere.model;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.apache.commons.lang.StringUtils;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.SecureEngine;
import org.compiere.util.TimeUtil;
import org.compiere.util.YearUtils;
import org.joda.time.DateTime;
import org.joda.time.Period;

/**
 * @author odelarosa
 * 
 */
public class MEXMEAlerta extends X_EXME_Alerta {

	/**
	 * Clase para contener información de las alertas de reporte
	 * 
	 * @author odelarosa
	 * 
	 */
	public static class ReportBean {
		private String alert;
		private int alertId;
		private String email;
		private String mrn;
		private String name;
		private int pacId;
		private String phone;

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + alertId;
			result = prime * result + pacId;
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			ReportBean other = (ReportBean) obj;
			if (alertId != other.alertId)
				return false;
			if (pacId != other.pacId)
				return false;
			return true;
		}

		public String getAlert() {
			return alert;
		}

		public int getAlertId() {
			return alertId;
		}

		public String getEmail() {
			return email;
		}

		public String getMrn() {
			return mrn;
		}

		public String getName() {
			return name;
		}

		public int getPacId() {
			return pacId;
		}

		public String getPhone() {
			return phone;
		}

		public void setAlert(String alert) {
			this.alert = alert;
		}

		public void setAlertId(int alertId) {
			this.alertId = alertId;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public void setMrn(String mrn) {
			this.mrn = mrn;
		}

		public void setName(String name) {
			this.name = name;
		}

		public void setPacId(int pacId) {
			this.pacId = pacId;
		}

		public void setPhone(String phone) {
			this.phone = phone;
		}
	}

	private static CLogger s_log = CLogger.getCLogger(MEXMEAlerta.class);
	private static final long serialVersionUID = -6321163734113030568L;

	/**
	 * Evalua los valores segun el operador
	 * 
	 * @param operator
	 *            Operador puede ser = != > <
	 * @param value
	 *            Valor 1
	 * @param value2
	 *            Valor 2
	 * @return Resultado de la evaluacion
	 */
	public static boolean evaluate(String operator, double value, double value2) {
		boolean retValue = false;
		if ("=".equals(operator)) {
			retValue = value == value2;
		} else if ("!=".equals(operator)) {
			retValue = value != value2;
		} else if (">".equals(operator)) {
			retValue = value > value2;
		} else if ("<".equals(operator)) {
			retValue = value < value2;
		} else if ("<=".equals(operator)) {
			retValue = value <= value2;
		} else if (">=".equals(operator)) {
			retValue = value >= value2;
		}
		return retValue;
	}

	/**
	 * Revisa si hay alertas que deben notificarse
	 * 
	 * @param ctx
	 *            Contexto de la App
	 * @param pacId
	 *            Paciente a revisar
	 * @param date
	 *            Fecha base
	 * @param trxName
	 *            Trx
	 * @return Listado de Alertas a notificar
	 */
	public static List<MEXMEAlerta> getAlerts(Properties ctx, int pacId, Date date, String trxName) {
		List<MEXMEAlerta> lst = new ArrayList<MEXMEAlerta>();

		// Sacamos las alertas del paciente
		List<MEXMEAlertaPac> alertasPac = MEXMEAlertaPac.getLst(ctx, pacId, null);

		List<Integer> exclude = new ArrayList<Integer>();

		// Iteramos las alertas del paciente
		for (MEXMEAlertaPac alertaPac : alertasPac) {
			exclude.add(alertaPac.getEXME_Alerta_ID());
		}

		// Traemos todas las alertas, menos las del paciente
		List<MEXMEAlerta> alerts = getLst(ctx, "%", true, exclude, trxName);

		// Revisamos cada alerta para ver si hay que notificar
		for (MEXMEAlerta alert : alerts) {
			if (mustAlert(ctx, alert, null, pacId, date, trxName)) {
				lst.add(alert);
			}
		}

		// Revisamos cada alerta del paciente para ver si hay que notificar
		for (MEXMEAlertaPac pac : alertasPac) {
			MEXMEAlerta alert = new MEXMEAlerta(ctx, pac.getEXME_Alerta_ID(), null);
			if (mustAlert(ctx, alert, pac.getFrequency(), pacId, date, trxName)) {
				alert.setFrequency(pac.getFrequency());
				alert.setDescription(pac.getDescription());
				lst.add(alert);
			}
		}

		return lst;
	}

	/**
	 * Regresa la fecha limite del ultimo evento
	 * 
	 * @param frequency
	 *            Se utiliza para calcular el limite
	 * @return
	 */
	private static Timestamp getLimit(BigDecimal frequency, Date date) {
		int[] arr = YearUtils.split(frequency);

		DateTime dt = new DateTime(date);
		dt = dt.minusYears(arr[0]);
		dt = dt.minusMonths(arr[1]);

		return (new Timestamp(dt.getMillis()));
	}

	/**
	 * Listado de alertas, puede o no excluir algunas
	 * 
	 * @param ctx
	 * @param value
	 *            Valor a buscar
	 * @param orderByName
	 *            Ordenar por nombre
	 * @param exclude
	 *            Ids que no se desean buscar
	 * @param trxName
	 * @return
	 */
	public static List<MEXMEAlerta> getLst(Properties ctx, String value, boolean orderByName, List<Integer> exclude, String trxName) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ");
		sql.append("  * ");
		sql.append("FROM ");
		sql.append("  EXME_Alerta ");
		sql.append("WHERE ");
		sql.append("  (upper(name) LIKE ? OR ");
		sql.append("  upper(value) LIKE ?) ");

		if (exclude != null && !exclude.isEmpty()) {
			sql.append("AND EXME_Alerta_ID not in (");
			sql.append(StringUtils.join(exclude, ','));
			sql.append(") ");
		}

		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", MEXMEAlerta.Table_Name));
		if (orderByName) {
			sql.append(" order by name");
		} else {
			sql.append(" order by value");
		}
		List<MEXMEAlerta> list = new ArrayList<MEXMEAlerta>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			value = "%" + StringUtils.upperCase(StringUtils.defaultIfEmpty(value, "")) + "%";
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setString(1, value);
			pstmt.setString(2, value);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				list.add(new MEXMEAlerta(ctx, rs, trxName));
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, null, e);
		} finally {
			DB.close(rs, pstmt);
		}
		return list;
	}

	/**
	 * Listado de alertas para cierta fecha
	 * 
	 * @param ctx
	 *            Contexto de la app
	 * @param date
	 *            Fecha base para alertar
	 * @param trxName
	 *            Trx
	 * @return Listado de pacientes a notificar
	 */
	public static List<ReportBean> getReportLst(Properties ctx, Date date, String trxName) {
		StringBuilder sql = new StringBuilder();
		sql.append("WITH ALERTS AS ");
		sql.append("  (SELECT A.EXME_ALERTA_ID, ");
		sql.append("    A.NAME, ");
		sql.append("    A.DESCRIPTION, ");
		sql.append("    A.EXME_DIAGNOSTICO_ID , ");
		sql.append("    A.SEXO, ");
		sql.append("    A.PESO, ");
		sql.append("    A.ALTURA, ");
		sql.append("    A.EDAD, ");
		sql.append("    A.FREQUENCY, ");
		sql.append("    A.OPERATOR, ");
		sql.append("    A.M_PRODUCT_ID, ");
		sql.append("    NULL AS EXME_PACIENTE_ID ");
		sql.append("  FROM EXME_ALERTA A ");
		sql.append("  WHERE A.ISACTIVE = 'Y' ");
		sql.append("  AND A.AD_ORG_ID  = ? ");
		sql.append("  UNION ");
		sql.append("  SELECT A2.EXME_ALERTA_ID, ");
		sql.append("    A2.NAME, ");
		sql.append("    A2.DESCRIPTION, ");
		sql.append("    A2.EXME_DIAGNOSTICO_ID , ");
		sql.append("    A2.SEXO, ");
		sql.append("    A2.PESO, ");
		sql.append("    A2.ALTURA, ");
		sql.append("    A2.EDAD, ");
		sql.append("    AP.FREQUENCY, ");
		sql.append("    A2.OPERATOR, ");
		sql.append("    A2.M_PRODUCT_ID, ");
		sql.append("    AP.EXME_PACIENTE_ID ");
		sql.append("  FROM EXME_ALERTAPAC AP ");
		sql.append("  INNER JOIN EXME_ALERTA A2 ");
		sql.append("  ON A2.EXME_ALERTA_ID = AP.EXME_ALERTA_ID ");
		sql.append("  WHERE AP.ISACTIVE    = 'Y' ");
		sql.append("  AND A2.ISACTIVE      = 'Y' ");
		sql.append("  AND A2.AD_ORG_ID     = ? ");
		sql.append("  ), ");
		sql.append("  SERVICES AS ");
		sql.append("  (SELECT CP.EXME_PACIENTE_ID, ");
		sql.append("    D.M_PRODUCT_ID, ");
		sql.append("    MAX(D.DATEORDERED) AS DATEORDERED, ");
		sql.append("    D.ESTATUS, ");
		sql.append("    H.DOCSTATUS ");
		sql.append("  FROM EXME_CTAPAC CP ");
		sql.append("  INNER JOIN EXME_ACTPACIENTEINDH H ");
		sql.append("  ON H.EXME_CTAPAC_ID = CP.EXME_CTAPAC_ID ");
		sql.append("  AND H.ISACTIVE      = 'Y' ");
		sql.append("  LEFT JOIN EXME_ACTPACIENTEIND D ");
		sql.append("  ON H.EXME_ACTPACIENTEINDH_ID = D.EXME_ACTPACIENTEINDH_ID ");
		sql.append("  AND D.ISACTIVE               = 'Y' ");
		sql.append("  GROUP BY CP.EXME_PACIENTE_ID, ");
		sql.append("    D.M_PRODUCT_ID, ");
		sql.append("    D.ESTATUS, ");
		sql.append("    H.DOCSTATUS ");
		sql.append("  ) ");
		sql.append("SELECT L1.AD_CLIENT_ID, ");
		sql.append("  L1.AD_ORG_ID, ");
		sql.append("  L1.EXME_PACIENTE_ID, ");
		sql.append("  AL.EXME_ALERTA_ID, ");
		sql.append("  L1.NOMBRE_PAC, ");
		sql.append("  AL.NAME AS NAME_ALERTA, ");
		sql.append("  L1.PHONE, ");
		sql.append("  L1.EMAIL , ");
		sql.append("  L1.DOCUMENTNO AS MRN, ");
		sql.append("  AL.M_PRODUCT_ID , ");
		sql.append("  (SELECT MAX(DATEORDERED) ");
		sql.append("  FROM SERVICES ");
		sql.append("  WHERE EXME_PACIENTE_ID = L1.EXME_PACIENTE_ID ");
		sql.append("  AND M_PRODUCT_ID       = AL.M_PRODUCT_ID ");
		sql.append("  ) AS LASTDATE ");
		sql.append("FROM ");
		sql.append("  ( WITH CONTEXTO AS ");
		sql.append("  (SELECT ? AS FECHAACTUAL ");
		sql.append("  FROM ad_client ");
		sql.append("  WHERE ad_client_id = 0 ");
		sql.append("  ) ");
		sql.append("SELECT P.EXME_PACIENTE_ID, ");
		sql.append("  P.FECHANAC, ");
		sql.append("  P.SEXO, ");
		sql.append("  edadsql(P.FECHANAC) AS EDAD, ");
		sql.append("  P.NOMBRE_PAC, ");
		sql.append("  P.TELPARTICULAR AS PHONE, ");
		sql.append("  P.EMAIL , ");
		sql.append("  CTX.FECHAACTUAL, ");
		sql.append("  E.DOCUMENTNO, ");
		sql.append("  E.AD_CLIENT_ID, ");
		sql.append("  E.AD_ORG_ID ");
		sql.append("FROM EXME_HIST_EXP E ");
		sql.append("INNER JOIN EXME_PACIENTE P ");
		sql.append("ON P.EXME_PACIENTE_ID = E.EXME_PACIENTE_ID , ");
		sql.append("  CONTEXTO CTX ");
		sql.append("WHERE E.AD_ORG_ID = ? ");
		sql.append("  ) L1 , ");
		sql.append("  ALERTS AL ");
		sql.append("WHERE (AL.EDAD IS NULL ");
		sql.append("OR (AL.OPERATOR = '=' ");
		sql.append("AND L1.EDAD     = AL.EDAD ");
		sql.append("OR AL.OPERATOR  = '>=' ");
		sql.append("AND L1.EDAD    >= AL.EDAD ");
		sql.append("OR AL.OPERATOR  = '<=' ");
		sql.append("AND L1.EDAD    <= AL.EDAD)) ");
		sql.append("AND (AL.SEXO   IS NULL ");
		sql.append("OR (AL.SEXO     = L1.SEXO)) ");
		sql.append("AND (COALESCE( ");
		sql.append("  (SELECT edadsql(MAX(S.DATEORDERED), L1.fechaactual) ");
		sql.append("  FROM SERVICES S ");
		sql.append("  WHERE S.EXME_PACIENTE_ID = L1.EXME_PACIENTE_ID ");
		sql.append("  AND S.M_PRODUCT_ID       = AL.M_PRODUCT_ID ");
		sql.append("  AND S.DOCSTATUS          = ? ");
		sql.append("  ), AL.FREQUENCY)        >= AL.FREQUENCY) ");
		sql.append("AND (NOT EXISTS ");
		sql.append("  (SELECT EXME_PATIENTNOTICE_ID ");
		sql.append("  FROM EXME_PATIENTNOTICE PN ");
		sql.append("  WHERE PN.EXME_PACIENTE_ID                = L1.EXME_PACIENTE_ID ");
		sql.append("  AND PN.EXME_ALERTA_ID                    = AL.EXME_ALERTA_ID ");
		sql.append("  AND edadsql(PN.VALID_TO, L1.fechaactual) < AL.FREQUENCY ");
		sql.append("  AND PN.AD_ORG_ID                         = ? ");
		sql.append("  )) ");
		sql.append("AND (AL.EXME_PACIENTE_ID = L1.EXME_PACIENTE_ID ");
		sql.append("OR AL.EXME_PACIENTE_ID  IS NULL ");
		sql.append("AND NOT EXISTS ");
		sql.append("  (SELECT * ");
		sql.append("  FROM ALERTS IA ");
		sql.append("  WHERE IA.EXME_ALERTA_ID = AL.EXME_ALERTA_ID ");
		sql.append("  AND IA.EXME_PACIENTE_ID = L1.EXME_PACIENTE_ID ");
		sql.append("  )) ");

		List<ReportBean> list = new ArrayList<ReportBean>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			int adOrgId = Env.getAD_Org_ID(ctx);
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, adOrgId);
			pstmt.setInt(2, adOrgId);
			pstmt.setTimestamp(3, TimeUtil.getInitialRangeT(ctx, date));
			pstmt.setInt(4, adOrgId);
			pstmt.setString(5, MEXMEActPacienteInd.STATUS_Completed);
			pstmt.setInt(6, adOrgId);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				ReportBean bean = new ReportBean();
				bean.setAlert(rs.getString("name_alerta"));
				bean.setMrn(rs.getString("mrn"));
				bean.setName(SecureEngine.decrypt(rs.getString("nombre_pac")));
				bean.setEmail(rs.getString("email"));
				bean.setPhone(rs.getString("phone"));
				bean.setPacId(rs.getInt("exme_paciente_id"));
				bean.setAlertId(rs.getInt("exme_alerta_id"));
				if (!list.contains(bean)) {
					list.add(bean);
				}
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, null, e);
		} finally {
			DB.close(rs, pstmt);
		}
		
		// Por alguna razon no se respeta el orden del query
		Collections.sort(list, new Comparator<ReportBean>() {

			@Override
			public int compare(ReportBean o1, ReportBean o2) {
				return o1.getAlert().compareTo(o2.getAlert());
			}
		});
		
		return list;
	}

	/**
	 * Revisa si debe o no alertas
	 * 
	 * @param ctx
	 * @param alerta
	 *            Alerta
	 * @param frequency
	 *            Frecuencia, si es nula se usa la de la alerta
	 * @param pacId
	 *            Paciente
	 * @param date
	 *            Fecha Base
	 * @param trxName
	 * @return
	 */
	public static boolean mustAlert(Properties ctx, MEXMEAlerta alerta, BigDecimal frequency, int pacId, Date date, String trxName) {
		boolean retValue = false;

		Timestamp limit = getLimit(frequency == null ? alerta.getFrequency() : frequency, date);

		MEXMEPatientNotice lastNotice = MEXMEPatientNotice.getLast(ctx, pacId, alerta.getEXME_Alerta_ID(), trxName);

		MEXMEPaciente pac = new MEXMEPaciente(ctx, pacId, trxName);

		// Si la regla tiene sexo
		if (StringUtils.isNotEmpty(alerta.getSexo())) {
			// Si no coincide el sexo
			if (!alerta.getSexo().equals(pac.getSexo())) {
				return false;
			}
		}

		DateTime dob = new DateTime(pac.getFechaNac());
		DateTime now = new DateTime(date);

		Period period = new Period(dob, now);

		double years = period.getYears();
		double months = period.getMonths();

		if (months > 0) {
			months = months / 12d;
		}

		years = months + years;

		// si no coincide la edad
		if (!evaluate(alerta.getOperator(), years, alerta.getEdad().doubleValue())) {
			return false;
		}

		// Si ya se ha notificado antes
		if (lastNotice != null) {
			// Si la notificacion es superior al limite no se avisa
			if (lastNotice.getValid_To().after(limit)) {
				return false;
			}
		}

		// Si el paciente tiene el estudio
		MEXMEActPacienteInd ind = MEXMEActPacienteInd.getLastService(ctx, pacId, alerta.getM_Product_ID(), trxName);
		if (ind != null) {
			// TODO Validar que columna es la que debe usarse
			Timestamp eventDate = ind.getDateOrdered();

			// Si la fecha del estudio es posterior al limite no requiere avisar
			if (eventDate.after(limit)) {
				retValue = false;
			} else {
				retValue = true;
			}
		} else {
			retValue = true;
		}

		if (retValue) {
			alerta.setLimit(limit);
		}
		return retValue;
	}

	public Timestamp limit;

	private boolean selected = false;

	/**
	 * @param ctx
	 * @param EXME_Alerta_ID
	 * @param trxName
	 */
	public MEXMEAlerta(Properties ctx, int EXME_Alerta_ID, String trxName) {
		super(ctx, EXME_Alerta_ID, trxName);
	}

	/**
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MEXMEAlerta(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	public Timestamp getLimit() {
		return limit;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setLimit(Timestamp limit) {
		this.limit = limit;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

}