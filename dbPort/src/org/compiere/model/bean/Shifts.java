package org.compiere.model.bean;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;

import org.compiere.model.I_EXME_Medico_Org;
import org.compiere.model.MEXMELookupInfo;
import org.compiere.model.MEXMEMedico;
import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.Env;

/**
 * @author Lorena Lama
 */
public class Shifts implements Cloneable {

	public static class AppointmentDate {
		private Timestamp	date;
		private Calendar	cal;
		private Boolean		weekEnd;

		public AppointmentDate(final Timestamp date) {
			this.date = date;
		}

		@Override
		public boolean equals(final Object obj) {
			if (obj instanceof Shifts) {
				final Shifts other = (Shifts) obj;
				return other.compareTo(this);
			} else {
				return super.equals(obj);
			}
		}

		public Calendar getCal() {
			if (cal == null) {
				this.cal = Calendar.getInstance();
				cal.setTime(date);
			}
			return cal;
		}

		public boolean isWeekEnd() {
			if (weekEnd == null) {
				final int weekDay = getCal().get(Calendar.DAY_OF_WEEK);
				this.weekEnd = weekDay == Calendar.SATURDAY || weekDay == Calendar.SUNDAY;
			}
			return weekEnd;
		}
	}

	private static CLogger	log	= CLogger.getCLogger(Shifts.class);

	public static List<Shifts> getSchedule(final MEXMEMedico physician) {
		final List<Shifts> shifts = new ArrayList<>();
		if (physician == null) {
			return shifts;
		}
		final StringBuilder st = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		st.append(" SELECT horaent1es, horasal1es, ");
		st.append("        horaent2es, horasal2es, ");
		st.append("        horaent3es, horasal3es, ");
		st.append("        horaent1fs, horasal1fs, ");
		st.append(" CASE WHEN horaent2es IS NULL OR horasal2es IS NULL THEN 0 ELSE 1 END AS HS2, ");
		st.append(" CASE WHEN horaent3es IS NULL OR horasal3es IS NULL THEN 0 ELSE 1 END AS HS3, ");
		st.append(" CASE WHEN horaent1fs IS NULL OR horasal1fs IS NULL THEN 0 ELSE 1 END AS HF ");
		st.append(" FROM EXME_Medico_Org mo ");
		st.append(" INNER JOIN EXME_turnos t ON (mo.EXME_turnos_ID = t.EXME_turnos_ID) ");
		st.append(" WHERE mo.EXME_Medico_id=? ");
		st.append(MEXMELookupInfo.addAccessLevelSQL(physician.getCtx(), " ", I_EXME_Medico_Org.Table_Name, "mo"));

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(st.toString(), null);
			pstmt.setInt(1, physician.getEXME_Medico_ID());
			rs = pstmt.executeQuery();
			final SimpleDateFormat sdf = Constantes.getCustom("HH:mm");

			if (rs.next()) {
				shifts.add(new Shifts(sdf.parse(rs.getString(1)), sdf.parse(rs.getString(2)), false));
				if (rs.getInt("HS2") > 0) {
					shifts.add(new Shifts(sdf.parse(rs.getString(3)), sdf.parse(rs.getString(4)), false));
				}
				if (rs.getInt("HS3") > 0) {
					shifts.add(new Shifts(sdf.parse(rs.getString(5)), sdf.parse(rs.getString(6)), false));
				}
				if (rs.getInt("HF") > 0) {
					shifts.add(new Shifts(sdf.parse(rs.getString(7)), sdf.parse(rs.getString(8)), true));
				}
			}
		} catch (final Exception e) {
			log.log(Level.SEVERE, st.toString(), e);
		} finally {
			DB.close(rs, pstmt);
		}
		return shifts;
	}

	private Calendar		startHr;
	private Calendar		endHr;
	private final boolean	weekEnd;

	public Shifts(final Date start, final Date end, final boolean weekEnd) {
		super();
		this.weekEnd = weekEnd;
		this.startHr = getCalendar(start, true);
		this.endHr = getCalendar(end, false);
	}

	@Override
	public Shifts clone() {
		Shifts obj = null;
		try {
			obj = (Shifts) super.clone();
		} catch (final CloneNotSupportedException ex) {
			System.out.println(" no se puede duplicar");
		}
		return obj;
	}

	public boolean compareTo(final AppointmentDate from) {
		if (startHr == null || endHr == null || from == null) {
			return false;
		}
		if (weekEnd == from.isWeekEnd()) {
			final Date tm2 = getDate(from.getCal(), startHr), tm3 = getDate(from.getCal(), endHr);
			final int compare1 = from.date.compareTo(tm2);
			final int compare2 = from.date.compareTo(tm3);
			return compare1 >= 0 && compare2 <= 0;
		} else {
			return false;
		}
	}

	public Calendar getCalendar(final Date from, final boolean ini) {
		final Calendar cal = Calendar.getInstance();
		if (from == null) {
			cal.set(Calendar.HOUR_OF_DAY, ini ? 0 : 23);
			cal.set(Calendar.MINUTE, ini ? 0 : 59);
			cal.set(Calendar.SECOND, ini ? 0 : 59);
		} else {
			cal.setTime(from);
			cal.set(Calendar.SECOND, 0);
		}
		cal.set(Calendar.MILLISECOND, 0);
		return cal;
	}

	public Date getDate(final Calendar from, final Calendar to) {
		to.set(Calendar.DAY_OF_MONTH, from.get(Calendar.DAY_OF_MONTH));
		to.set(Calendar.MONTH, from.get(Calendar.MONTH));
		to.set(Calendar.YEAR, from.get(Calendar.YEAR));
		return DB.invertConvert(Env.getCtx(),to.getTime());
	}

	@Override
	public String toString() {
		final StringBuilder str = new StringBuilder();
		str.append("Ini: ").append(startHr.getTime());
		str.append(" - Fin: ").append(endHr.getTime());
		return str.toString();
	}

}