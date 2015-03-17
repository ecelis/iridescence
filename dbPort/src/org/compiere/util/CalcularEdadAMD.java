package org.compiere.util;

import java.text.MessageFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.Properties;
import java.util.logging.Level;

/**
 * @author Lorena Lama
 * 
 * Nota. Cualquier cambio en el metodo de calcular edad en AMD, 
 * se debe considerar la clase CalculosIreport
 */
public class CalcularEdadAMD {

	private int meses;
	private int dias;
	private int anios;
	private int horas;
	private String mesesStr;
	private String diasStr;
	private String aniosStr;
	private String horasStr;
	private String edadAMD;
	private String ageDecimal;
	private Properties ctx;
	
	private static CLogger s_log = CLogger.getCLogger(CalcularEdadAMD.class);
	
	public CalcularEdadAMD(Properties ctx) {
		this.ctx = ctx;
	}
	
	/**
	 * Get the actual age as years, months and days in format yy.mm.dd
	 * 
	 * @param birthDate - initial date
	 * @return Age
	 * @throws Exception
	 */
	public static CalcularEdadAMD getAge(Properties ctx, Date birthDate) throws Exception {
		return getAge(ctx, null, birthDate, Env.getCurrentDate());
	}

	/**
	 * Get the age between two dates as years, months and days
	 * 
	 * @param birthDate - initial date
	 * @param visitDate - ending date
	 * @return Age
	 * @throws Exception
	 */
	public static CalcularEdadAMD getEdadCita(Properties ctx, String birthDate, String endDate) {
		return getAge(ctx, birthDate, endDate);
	}
	/**
	 * Get the age between two dates as years, months and days
	 * 
	 * @param birthDate - initial date
	 * @param visitDate - ending date
	 * @return Age
	 * @throws Exception
	 */
	public static CalcularEdadAMD getEdad(String birthDate) {
		return getEdad(Env.getCtx(), birthDate);
	}
	/**
	 * Get the actual age as years, months and days in format yy.mm.dd
	 * 
	 * @param birthDate - initial date
	 * @param AD_Language
	 * @return Age
	 * @throws Exception
	 */
	public static CalcularEdadAMD getEdad(Properties ctx, String fechaNac) {
		Date birthDate = null;
		try {
			birthDate = Constantes.getSdfFecha().parse(fechaNac);
		} catch (Exception e) {
			s_log.log(Level.SEVERE, "getEdad", e);
			return new CalcularEdadAMD(ctx);
		}
		return getAge(ctx, null, birthDate, Env.getCurrentDate());
	}

	/**
	 * Get the age between two dates as years, months and days
	 * if the age is less than a day returns the hours
	 * 
	 * @param birthDate - initial date
	 * @param endDate - ending date
	 * @param AD_Language - language for messages
	 * @return Age
	 * @throws Exception
	 */
	public static CalcularEdadAMD getAge(Properties ctx, String birthDate, String endDate) {
		Date iniD = null;
		Date endD = null;
		try {
			if (birthDate != null)
				iniD = Constantes.getSdfFecha().parse(birthDate);
			if (endDate != null)
				endD = Constantes.getSdfFecha().parse(endDate);
			else
				endD = Env.getCurrentDate();
		} catch (ParseException e) {
			s_log.log(Level.SEVERE, "getEdad", e);
			return new CalcularEdadAMD(ctx);
		}
		return getAge(ctx,null, iniD, endD);
	}

	/**
	 * Get the number of days in that month
	 * 
	 * @param month
	 * @param year
	 * @return days in a specific month
	 */
	public static int getDaysMonth(int month, int year) {
		Calendar calendar = new GregorianCalendar(year, month - 1, 1);
		int days = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		return days;
	}

	/**
	 * Sets the hours
	 * 
	 * @param birthDate
	 */
	public void setHours(Properties ctx, Date birthDate) {
		setHours(ctx, birthDate, Env.getCurrentDate());
	}
	
	/**
	 * Sets the hours
	 * 
	 * @param birthDate
	 */
	public void setHours(Properties ctx, Date birthDate, Date endDate) {
		long time = endDate.getTime() - birthDate.getTime();
		setHoras((int) time / (60 * 60 * 1000));
		setHorasStr(getHoras() + " " + Utilerias.getMsg(ctx, "progMed.horas"));
		setEdadAMD((getEdadAMD()!=null?getEdadAMD():" ") + getHorasStr());
	}

	/**
	 * Agrega las horas transcurridas entre la fecha inicial y fecha actual (RN)
	 * 
	 * @param fechaNac
	 * @return
	 * @throws Exception
	 */
	public static CalcularEdadAMD getEdadHrs(Properties ctx, Date birthDate) throws Exception {
		CalcularEdadAMD age = getAge(ctx, birthDate);
		if (age.getHoras() <= 0 && (age.getAnios() <= 0 && age.getMeses() <= 0 && age.getDias() <=0 )) {
			age.setHours(ctx, birthDate);
		}
		return age;
	}

	/**
	 * Get the age between two dates as years, months and days
	 * if the age is less than a day returns the hours
	 * 
	 * @param birthDate - initial date
	 * @param endDate - ending date
	 * @param AD_Language - language for messages, if it's null returns only yy.mm.dd
	 * @return Age
	 * @throws Exception
	 */
	public static CalcularEdadAMD getAge(Properties ctx, Locale locale, Date birthDate, Date endDate) {
		return new CalcularEdadAMD(ctx).setAge(birthDate, endDate);
	}
	
	/**
	 * Get the age between two dates as years, months and days
	 * if the age is less than a day returns the hours
	 * 
	 * @param birthDate - initial date
	 * @param endDate - ending date
	 * @param AD_Language - language for messages, if it's null returns only yy.mm.dd
	 * @return Age
	 * @throws Exception
	 */
	public CalcularEdadAMD setAge(Date birthDate, Date endDate) {

		// validate not nulls
		if (endDate == null || birthDate == null)
			return this;
		// validate initial / final date
		if (endDate.before(birthDate))
			return this;
		// less than a day
		if ((endDate.getTime() - birthDate.getTime()) < (24 * 60 * 60 * 1000)) {
			setHours(ctx, birthDate, endDate);
			return this;
		}

		// Age
		int months = 0;
		int days = 0;
		int years = 0;

		// initial date
		Calendar cal = Calendar.getInstance();
		cal.setTime(birthDate);
		int b_days = cal.get(Calendar.DAY_OF_MONTH);
		int b_months = cal.get(Calendar.MONTH) + 1;
		int b_years = cal.get(Calendar.YEAR);

		// final date
		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(endDate);
		int v_days = cal2.get(Calendar.DAY_OF_MONTH);
		int v_months = cal2.get(Calendar.MONTH) + 1;
		int v_years = cal2.get(Calendar.YEAR);
		// days of month
		int daysMonth = getDaysMonth(v_months == 1 ? 12 : v_months - 1, v_years);

		int s_months = 0;
		int s_years = 0;
		int daysA = 0;
		int daysB = 0;

		daysB = (daysMonth - b_days) + v_days;// B
		if (b_days > v_days) {// A...
			s_months = 1;
			if (b_days > daysMonth)
				daysA = v_days;
			else if (b_days == daysMonth)
				daysA = v_days - 1;
			else
				daysA = daysB;// B
		}
		else
			daysA = v_days - b_days;// ...A

		if (b_months > v_months) {
			s_years = 1;
			months = 12 - (b_months - v_months);
			days = daysA;
			/*
			 * if (b_days > v_days) {//...A
			 * s_months = 1;
			 * if (b_days > daysMonth)
			 * days = v_days;
			 * else if (b_days == daysMonth)
			 * days = v_days - 1;
			 * else
			 * days = (daysMonth - b_days) + v_days;//B
			 * } else {
			 * days = v_days - b_days;
			 * }//...A
			 */
		}
		else if (b_months == v_months && b_days > v_days) {
			months = 12;
			days = daysB;
			// days = (daysMonth - b_days) + v_days;//B
			s_years = 1;
		}
		else {
			months = v_months - b_months;
			days = daysA;
			/*
			 * if (b_days > v_days) {//A...
			 * s_months = 1;
			 * if (b_days > daysMonth)
			 * days = v_days;
			 * else if (b_days == daysMonth)
			 * days = v_days - 1;
			 * else
			 * days = (daysMonth - b_days) + v_days;//B
			 * } else {
			 * days = v_days - b_days;
			 * }//...A
			 */
		}

		years = (v_years - b_years) - s_years;
		months -= s_months;

		setAnios(years);
		setMeses(months);
		setDias(days);

		// from *.properties

		StringBuffer str = new StringBuffer(50);
		if (years > 0) {
			if(years == 1){
				setAniosStr(years + " " + Utilerias.getMsg(ctx, "msj.anio"));
				str.append(getAniosStr() + " ");
			}else{
				setAniosStr(years + " " + Utilerias.getMsg(ctx, "msj.anios"));
				str.append(getAniosStr() + " ");
			}
		}
		if (months > 0) {
			if(months == 1){
				setMesesStr(months + " " + Utilerias.getMsg(ctx, "msj.mes"));
				str.append(getMesesStr() + " ");
			}else{
				setMesesStr(months + " " + Utilerias.getMsg(ctx, "msj.meses"));
				str.append(getMesesStr() + " ");
			}
		}
		if (days > 0) {
			if(days == 1){
				setDiasStr(days + " " + Utilerias.getMsg(ctx, "msj.dia"));
				str.append(getDiasStr() + " ");
			}else{
				setDiasStr(days + " " + Utilerias.getMsg(ctx, "msj.dias"));
				str.append(getDiasStr() + " ");
			}
		}

		if(getEdadAMD() == null || getEdadAMD().equals("")) {
			setEdadAMD(str.toString());
		}
		return this;
	}

	/**
	 * 
	 * @param AD_Message
	 * @param AD_Language
	 * @param args
	 * @return
	 */
	public String getMsg(String AD_Message, String AD_Language, Object[] args) {
		String message = null;
		try {
			String baseLang = DB.getSQLValueString(null, "SELECT AD_Language FROM AD_Language WHERE isbaselanguage = 'Y' ");

			if (AD_Language.equals(baseLang)) {
				message = DB.getSQLValueString(null, "SELECT msgText FROM AD_Message WHERE value = ? ", AD_Message);
			}
			else {
				message = DB.getSQLValueString(null,
						new StringBuffer(
								"SELECT mt.msgText FROM AD_Message m " +
								"INNER JOIN AD_Message_trl mt ON (m.AD_Message_ID = mt.AD_Message_ID) " +
								"WHERE m.value = ? AND mt.AD_Language = ?"
						).toString(), AD_Message, AD_Language);
			}
			message = MessageFormat.format(message, args);

		} catch (Exception e) {
			s_log.log(Level.SEVERE, "getEdad", e);
			message = null;
		}
		return message;
	}

	/**
	 * @param edadAMD The edadAMD to set.
	 */
	public void setEdadAMD(String edadAMD) {
		this.edadAMD = edadAMD;
	}

	/**
	 * @return Returns the edadAMD.
	 */
	public String getEdadAMD() {
		return edadAMD;
	}

	/**
	 * @return Returns the anios.
	 */
	public int getAnios() {
		return anios;
	}

	/**
	 * @param anios The anios to set.
	 */
	public void setAnios(int anios) {
		this.anios = anios;
	}

	/**
	 * @return Returns the dias.
	 */
	public int getDias() {
		return dias;
	}

	/**
	 * @param dias The dias to set.
	 */
	public void setDias(int dias) {
		this.dias = dias;
	}

	/**
	 * @return Returns the meses.
	 */
	public int getMeses() {
		return meses;
	}

	/**
	 * @param meses The meses to set.
	 */
	public void setMeses(int meses) {
		this.meses = meses;
	}

	/**
	 * @param horas The meses to set.
	 */
	public void setHoras(int horas) {
		this.horas = horas;
	}

	/**
	 * @return Returns the horas.
	 */
	public int getHoras() {
		return horas;
	}

	public String getMesesStr() {
		return mesesStr;
	}

	public void setMesesStr(String mesesStr) {
		this.mesesStr = mesesStr;
	}

	public String getDiasStr() {
		return diasStr;
	}

	public void setDiasStr(String diasStr) {
		this.diasStr = diasStr;
	}

	public String getAniosStr() {
		return aniosStr;
	}

	public void setAniosStr(String aniosStr) {
		this.aniosStr = aniosStr;
	}

	public String getHorasStr() {
		return horasStr;
	}

	public void setHorasStr(String horasStr) {
		this.horasStr = horasStr;
	}

	public String getAgeDecimal() {
		if (ageDecimal == null)
			ageDecimal = anios + "." + meses + "." + dias;
		return ageDecimal;
	}

	 public static void set_locale(Locale loc) {
//	        locale = loc;
	   }

	public String getAgeSimple() {
		return getAnios() <= 0 ? getMeses() <= 0 ? getDias() <= 0 ? getHorasStr() : getDiasStr() : getMesesStr() : getAniosStr();
	}
	
	public double getAge() {
		double ageD = getAnios();
		if (getMeses() > 0) {
			ageD += getMeses() / 12d;
		}
		if (getDias() > 0) {
			final Calendar cal = Calendar.getInstance();
			ageD += getDias() / (cal.isLenient() ? 366d : 365d);
		}
		return ageD;
	}
}
