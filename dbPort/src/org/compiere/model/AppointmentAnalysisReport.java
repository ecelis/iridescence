/**
 * 
 */
package org.compiere.model;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.SecureEngine;
import org.compiere.util.Utilerias;

/**
 * @author vperez
 *
 */
public class AppointmentAnalysisReport {
	private List<AppointmentRow> lstAppt = new ArrayList<AppointmentRow>();
	private String filters = null;
	
	public String getFilters() {
		return filters;
	}

	public void setFilters(String filters) {
		this.filters = filters;
	}

	public List<AppointmentRow> getLstAppt() {
		return lstAppt;
	}

	public void setLstAppt(List<AppointmentRow> lstAppt) {
		this.lstAppt = lstAppt;
	}

	public class AppointmentRow {
		private String encounter = null;
		private String nombrePac = null;
		private String typeAppt = null;
		private Date scheduledDate = new Date();
		private Date arrivalDate = new Date();
		private Date closeDate = new Date();
		private String primFC = null;
		private boolean isVerified = false;
		private BigDecimal payments = null;
		private BigDecimal adeudo = null;
		
		public String getEncounter() {
			return encounter;
		}
		public void setEncounter(String encounter) {
			this.encounter = encounter;
		}
		public String getNombrePac() {
			return nombrePac;
		}
		public void setNombrePac(String nombrePac) {
			this.nombrePac = nombrePac;
		}
		public String getTypeAppt() {
			return typeAppt;
		}
		public void setTypeAppt(String typeAppt) {
			this.typeAppt = typeAppt;
		}
		public Date getScheduledDate() {
			return scheduledDate;
		}
		public void setScheduledDate(Date scheduledDate) {
			this.scheduledDate = scheduledDate;
		}
		public Date getArrivalDate() {
			return arrivalDate;
		}
		public void setArrivalDate(Date arrivalDate) {
			this.arrivalDate = arrivalDate;
		}
		public Date getCloseDate() {
			return closeDate;
		}
		public void setCloseDate(Date closeDate) {
			this.closeDate = closeDate;
		}
		public String getPrimFC() {
			return primFC;
		}
		public void setPrimFC(String primFC) {
			this.primFC = primFC;
		}
		public boolean isVerified() {
			return isVerified;
		}
		public void setVerified(boolean isVirified) {
			this.isVerified = isVirified;
		}
		public BigDecimal getPayments() {
			return payments;
		}
		public void setPayments(BigDecimal payments) {
			this.payments = payments;
		}
		public BigDecimal getAdeudo() {
			return adeudo;
		}
		public void setAdeudo(BigDecimal adeudo) {
			this.adeudo = adeudo;
		}
	}
	
	/**
	 * Agrega un registro al reporte
	 * @return
	 */
	public AppointmentRow addRow() {
		AppointmentRow row = new AppointmentRow();
		lstAppt.add(row);
		return row;
	}
	
	/**
	 * Llena reporte con las citas
	 * @param citas
	 * @param arrivalBeg
	 * @param arrivalEnd
	 */
	public void fillReport(List<MEXMECitaMedica> citas, Date arrivalBeg, Date arrivalEnd) {
		for (MEXMECitaMedica cita : citas) {
			if (arrivalBeg == null) {
				fillRow(addRow(), cita);
			} else {
				if (cita.getHoraLlegada() != null && isValidDate(cita, arrivalBeg, arrivalEnd)) {
					fillRow(addRow(), cita);
				}
			}
		}
	}
	
	/**
	 * Valida si la fecha de la hora de llegada es valida segun los filtros seleccionados
	 * @param cita
	 * @param arrivalBeg
	 * @param arrivalEnd
	 * @return
	 */
	public boolean isValidDate(MEXMECitaMedica cita, Date arrivalBeg, Date arrivalEnd) {
		boolean isValid = false;
		String st = Constantes.getSdfFecha().format(cita.getHoraLlegada());
		try {
			Date dateI = null;
			Date dateF = null;
			if (arrivalBeg.before(arrivalEnd)) {
				dateI = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(st + " " + new SimpleDateFormat("HH:mm").format(DB.convert(Env.getCtx(), arrivalBeg)));
				dateF = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(st + " " + new SimpleDateFormat("HH:mm").format(DB.convert(Env.getCtx(), arrivalEnd)));
			} else {
				Calendar cal = Calendar.getInstance();
				cal.setTimeInMillis(cita.getHoraLlegada().getTime());
				cal.add(Calendar.DAY_OF_YEAR, -1);
				st = Constantes.getSdfFecha().format(cal.getTime());
				dateI = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(st + " " + new SimpleDateFormat("HH:mm").format(DB.convert(Env.getCtx(), arrivalBeg)));
				cal.add(Calendar.DAY_OF_YEAR, 1);
				st = Constantes.getSdfFecha().format(cal.getTime());
				dateF = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(st + " " + new SimpleDateFormat("HH:mm").format(DB.convert(Env.getCtx(), arrivalEnd)));
			}
			Date dateCM = DB.convert(Env.getCtx(), cita.getHoraLlegada());
			if (dateCM.after(dateI) && dateCM.before(dateF)) {
				isValid = true;
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return isValid;
	}
	
	/**
	 * Llena los datos del registro a agregar
	 * @param row
	 * @param cita
	 */
	public void fillRow(AppointmentRow row, MEXMECitaMedica cita) {
		row.setNombrePac(SecureEngine.decrypt(cita.getEXME_CtaPac().getNombre_Pac()));
		row.setEncounter(cita.getEXME_CtaPac().getDocumentNo());
		row.setScheduledDate(cita.getFechaHrCita());
		row.setArrivalDate(cita.getHoraLlegada());
		row.setCloseDate(cita.getFechaHrFin());
		MEXMEMotivoCita motivo = new MEXMEMotivoCita(Env.getCtx(), cita.getEXME_MotivoCita_ID(), null);
		row.setTypeAppt(motivo.getName());
		row.setPrimFC(cita.getFinancialClassName());
		row.setVerified(false);
		row.setPayments(cita.getPagos());
		row.setAdeudo(cita.getCargos());
	}
	
	/**
	 * Agrega a la cadena de filtros el filtro indicado
	 * @param apptType
	 */
	public void addScheduledFilter(Date dateIni, Date dateFin) {
		if (dateIni != null && dateFin != null) {
			StringBuilder str = new StringBuilder();
			str.append(Utilerias.getMsg(Env.getCtx(), "msj.scheduledDate")).append(" ")
				.append(Constantes.sdfFecha(Env.getCtx()).format(dateIni)).append(" ")
				.append(Utilerias.getMsg(Env.getCtx(), "agendar.odontologia.A")).append(" ")
				.append(Constantes.sdfFecha(Env.getCtx()).format(dateFin));
			addFilter(str.toString());
		}
	}
	
	/**
	 * Agrega a la cadena de filtros el filtro indicado
	 * @param apptType
	 */
	public void addArrivaFilter(Date dateIni, Date dateFin) {
//		if (dateIni != null && dateFin != null) {
		StringBuilder str = new StringBuilder();
		str.append(Utilerias.getMsg(Env.getCtx(), "msj.arrivalTime"))
				.append(" ")
				.append(Constantes.getSdfHora24(Env.getCtx()).format(dateIni))
				.append(" ")
				.append(Utilerias.getMsg(Env.getCtx(), "agendar.odontologia.A"))
				.append(" ")
				.append(Constantes.getSdfHora24(Env.getCtx()).format(dateFin));
		addFilter(str.toString());
//		}
	}
	
	/**
	 * Agrega a la cadena de filtros el filtro indicado
	 * @param apptType
	 */
	public void addPrimFCFilter(String primFC) {
		if (StringUtils.isNotEmpty(primFC)) {
			StringBuilder str = new StringBuilder();
			str.append(Utilerias.getMsg(Env.getCtx(), "report.primaryFC")).append(" ")
				.append(primFC);
			addFilter(str.toString());
		}
	}
	
	/**
	 * Agrega a la cadena de filtros el filtro indicado
	 * @param apptType
	 */
	public void addApptTypeFilter(String apptType) {
		if (StringUtils.isNotEmpty(apptType)) {
			StringBuilder str = new StringBuilder();
			str.append(Utilerias.getMsg(Env.getCtx(), "msj.citasTipo")).append(" ")
				.append(apptType);
			addFilter(str.toString());
		}
	}

	public void addFilter(String filter) {
		this.filters = filters == null ? filter : filters + ", " + filter;
	}
}
