package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;

import org.apache.commons.lang.StringUtils;
import org.compiere.util.CLogger;
import org.compiere.util.CalcularEdadAMD;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.SecureEngine;
import org.compiere.util.Utilerias;

/**
 * @author arangel
 * 
 */
public class CensusByDateData {

	/** Static Logger */
	private static CLogger s_log = CLogger.getCLogger(CensusByDateData.class);

	/**
	 * Report Census by Date
	 * 
	 * @param params
	 * @param filters
	 * @return lst of values a mostrar en el reporte Census by Date
	 */
	public static ArrayList<CensusByDateData> getCensusByDate(StringBuilder filters, List<Object> params, Object order) {
		ArrayList<CensusByDateData> values = new ArrayList<CensusByDateData>();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		int j = 1;
		StringBuilder reporte = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		try {
			reporte.append(SQLFinancialRpts.censusbyDate());
			if (filters.length() > 0) {
				reporte.append(filters);
			}else{
				//Si no tiene Filtros por defecto muestra los encuentros en Admission
				//Ticket 0102098
				reporte.append(" AND CTA.EncounterStatus = ").append(DB.TO_STRING(MEXMECtaPac.ENCOUNTERSTATUS_Admission));
			}

			if (order != null && StringUtils.isNotEmpty(order.toString())) {
				reporte.append("ORDER BY ").append(order);
			}
			pstm = DB.prepareStatement(reporte.toString(), null);

			pstm.setInt(j++, Env.getAD_Org_ID(Env.getCtx()));
			pstm.setInt(j++, Env.getAD_Client_ID(Env.getCtx()));
			if (filters.length() > 0 && !params.isEmpty()) {
				for (int i = 0; i < params.size(); i++) {
					// Solo filtros de fechas
					pstm.setTimestamp((i + j), (java.sql.Timestamp) params.get(i));
				}
			}
			rs = pstm.executeQuery();
			while (rs.next()) {
				final CensusByDateData rValues = new CensusByDateData();

				rValues.setUnit(rs.getString("unit"));
				rValues.setUnitBed(rs.getString("bed"));
				rValues.setPacName(SecureEngine.decrypt(rs.getString(MEXMEPaciente.COLUMNNAME_Nombre_Pac)));
				rValues.setEncounter(rs.getString("ENCOUNTER"));
				rValues.setMrn(rs.getString("MRN"));
				rValues.setSex(rs.getString("sexo"));
				
				Date date = rs.getDate("ADMIT");

				CalcularEdadAMD calcularEdadAMD = new CalcularEdadAMD(Env.getCtx()).setAge(rs.getDate(MEXMEPaciente.COLUMNNAME_FechaNac), date == null ? new Date() : date);
				int years = calcularEdadAMD.getAnios();
				if (years >= 1) {
					rValues.setAge(Integer.toString(years) + Constantes.SPACE + Utilerias.getMsg(Env.getCtx(), "msj.anios"));
				} else {
					int months = calcularEdadAMD.getMeses();
					if (months >= 1) {
						rValues.setAge(Integer.toString(months) + Constantes.SPACE + Utilerias.getMsg(Env.getCtx(), "msj.meses"));
					} else {
						rValues.setAge(Integer.toString(calcularEdadAMD.getDias()) + Constantes.SPACE + Utilerias.getMsg(Env.getCtx(), "progMed.dias"));
					}
				}

				rValues.setPacTipo(rs.getString("patientType"));
				rValues.setServTipo(rs.getString("SERVICE"));
				rValues.setArrival(rs.getTimestamp("ARRIVALDATE"));
				rValues.setAdmit(rs.getTimestamp("ADMIT"));
				rValues.setChiefComplaint(rs.getString("chiefComplaint"));
				rValues.setAdmitSource(rs.getString("admitSource"));
				rValues.setAttendig(rs.getString(MEXMECtaPacMed.COLUMNNAME_PhysicianName));
				rValues.setFclass(rs.getString("FCLASS"));

				values.add(rValues);
			}
			// values.addAll(createListCensusByDatesData(rs));
			if (MEXMEPaciente.COLUMNNAME_Nombre_Pac.equals(order)) {
				Collections.sort(values, new Comparator<CensusByDateData>() {
					public int compare(CensusByDateData o1, CensusByDateData o2) {
						return o1.getPacName().compareTo(o2.getPacName());
					}
				});
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, reporte.toString(), e);

		} finally {
			DB.close(rs, pstm);
		}
		return values;
	}

	private Date admit;
	private String admitSource;
	private String age;
	private Date arrival;
	private String attendig;
	private String chiefComplaint;
	private String encounter;
	private String fclass;
	private String mrn;
	private String pacName;
	private String pacTipo;
	private String servTipo;
	private String sex;
	private String unit;
	private String unitBed;

	public Date getAdmit() {
		return admit;
	}

	public String getAdmitSource() {
		return admitSource;
	}

	public String getAge() {
		return age;
	}

	public Date getArrival() {
		return arrival;
	}

	public String getAttendig() {
		return attendig;
	}

	public String getChiefComplaint() {
		return chiefComplaint;
	}

	public String getEncounter() {
		return encounter;
	}

	public String getFclass() {
		return fclass;
	}

	public String getMrn() {
		return mrn;
	}

	public String getPacName() {
		return pacName;
	}

	public String getPacTipo() {
		return pacTipo;
	}

	public String getServTipo() {
		return servTipo;
	}

	public String getSex() {
		return sex;
	}

	public String getUnit() {
		return unit;
	}

	public String getUnitBed() {
		return unitBed;
	}

	public void setAdmit(Date admit) {
		this.admit = admit;
	}

	public void setAdmitSource(String admitSource) {
		this.admitSource = admitSource;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public void setArrival(Date arrival) {
		this.arrival = arrival;
	}

	public void setAttendig(String attendig) {
		this.attendig = attendig;
	}

	public void setChiefComplaint(String chiefComplaint) {
		this.chiefComplaint = chiefComplaint;
	}

	public void setEncounter(String encounter) {
		this.encounter = encounter;
	}

	public void setFclass(String fclass) {
		this.fclass = fclass;
	}

	public void setMrn(String mrn) {
		this.mrn = mrn;
	}

	public void setPacName(String pacName) {
		this.pacName = pacName;
	}

	public void setPacTipo(String pacTipo) {
		this.pacTipo = pacTipo;
	}

	public void setServTipo(String servTipo) {
		this.servTipo = servTipo;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public void setUnitBed(String unitBed) {
		this.unitBed = unitBed;
	}
}
