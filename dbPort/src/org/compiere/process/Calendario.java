package org.compiere.process;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;

import org.compiere.model.X_EXME_Calendario;
import org.compiere.util.DB;
import org.compiere.util.Env;

public class Calendario extends SvrProcess {
	private int d_Year = 0;
	public Calendario() {
	}

	@Override
	protected void prepare() {

		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++) {
			String name = para[i].getParameterName();
			if(name.equals("Year"))
				d_Year = para[i].getParameterAsInt();
			else
				log.log(Level.SEVERE, "Unknown Parameter: " + name);
		}
		
	}


	@Override
	protected String doIt() throws Exception {
		
		//Tomamos el a#o de la fecha actual
		Timestamp t_FechaActual = DB.getTimestampForOrg(Env.getCtx());
		Date d_FechaActual = new Date(t_FechaActual.getTime());
		//System.out.println("d_FechaActual  = " + d_FechaActual);
		
		//Iniciamos la fecha
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(d_FechaActual);
		calendar.set(Calendar.YEAR, d_Year);
		//System.out.println("calendar  = " + calendar.getTime());
		
		int year = calendar.get(Calendar.YEAR);
		//System.out.println("year  = " + year);
		
		//Iteramos los meses
		int ultimoDia = 0;
		if(calendar.isLeapYear(year))
			ultimoDia = 366;
		else
			ultimoDia = 365;

		//Iniciamos
		calendar.set(year, Calendar.JANUARY, 1);
		
		for (int dia = 1; dia <= ultimoDia; dia++) {

			
			//System.out.println("calendar  = " + calendar.getTime());

			//Creamos el objeto a guardar
			X_EXME_Calendario m_calendario = new X_EXME_Calendario(getCtx(), 0, get_TrxName());
			m_calendario.setDia_Semana(calendar.get(Calendar.DAY_OF_WEEK));
			m_calendario.setDia_Mes(calendar.get(Calendar.DAY_OF_MONTH));
			m_calendario.setMes(calendar.get(Calendar.MONTH));
			m_calendario.setYear(calendar.get(Calendar.YEAR));
			m_calendario.setEsFestivo(false);//Futuro puede consultar otra tabla para determinar
			m_calendario.setFecha(new Timestamp((calendar.getTime()).getTime()));
			if(!m_calendario.save(get_TrxName()))
			{
				return "@Error@";
			}

			calendar.add(Calendar.DATE, 1);
			
		}//Fin ciclo meses
		return "@OK@";
	}	//	createStdPeriods
}
