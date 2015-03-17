package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.util.LabelValueBean;
import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.Msg;
import org.compiere.util.Utilerias;
import org.compiere.util.ValueNamePair;

public class MEXMEQuirofano extends X_EXME_Quirofano {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static CLogger s_log = CLogger.getCLogger(MEXMEQuirofano.class);

	public MEXMEQuirofano(Properties ctx, int EXME_Quirofano_ID, String trxName) {
		super(ctx, EXME_Quirofano_ID, trxName);
	}

	public MEXMEQuirofano(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}
	
	protected boolean beforeSave(boolean newRecord) {

		if (getIntervalo() <= 0) {
			log.saveError("SaveError", Utilerias.getAppMsg(getCtx(), "phr.error.intervalo"));
			return false;
		}
		if (getDispQuirIni() != null && getDispQuirFin() != null) {
			if (getDispQuirIni().after(getDispQuirFin())) {
				log.saveError("SaveError", Utilerias.getAppMsg(getCtx(), "error.fechaInicialMenor"));
				return false;
			}
		} else {
			log.saveError("SaveError", Utilerias.getAppMsg(getCtx(), "msg.progRecord.fechas"));
			return false;
		}

		// Validamos que el color no sea repetido
		if (getAD_Color_ID() > 0 && is_ValueChanged("AD_Color_ID")) {
			int count = 0;
			StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
			sql.append(" SELECT AD_Color_ID FROM EXME_Quirofano WHERE AD_Color_ID = ?");

			count = DB.getSQLValue(get_TrxName(), sql.toString(), getAD_Color_ID());

			if (count > 0) {
				log.saveError("error.color.repeated", Msg.getElement(getCtx(), "EXME_Quiforano_ID "));
				return false;
			}
		}

		return true;

	}

	public static List<MEXMEQuirofano> get(Properties ctx, String value, String name, String trxName) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<MEXMEQuirofano> lst = new ArrayList<MEXMEQuirofano>();
		try {
			StringBuilder sql = new StringBuilder();
			sql.append(" select * from  EXME_QUIROFANO where upper(value) like ? and upper(name) like ?");
			sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", MEXMEQuirofano.Table_Name));

			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setString(1, "%" + (StringUtils.isEmpty(value) ? StringUtils.EMPTY : value.toUpperCase()) + "%");
			pstmt.setString(2, "%" + (StringUtils.isEmpty(name) ? StringUtils.EMPTY : name.toUpperCase()) + "%");
			rs = pstmt.executeQuery();

			while (rs.next()) {
				lst.add(new MEXMEQuirofano(ctx, rs, trxName));
			}

		} catch (Exception e) {
			s_log.log(Level.SEVERE, null, e);
		} finally {
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}

		return lst;
	}

	public static List<LabelValueBean> get(Properties ctx, int EXME_EstServ_ID, String trxName) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<LabelValueBean> lst = new ArrayList<LabelValueBean>();
		try {
			StringBuilder sql = new StringBuilder();
			sql.append(" select name, EXME_Quirofano_ID from  EXME_Quirofano where EXME_EstServ_ID = ? ");
			sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", MEXMEQuirofano.Table_Name));

			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, EXME_EstServ_ID);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				lst.add(new LabelValueBean(rs.getString(1), rs.getString(2)));
			}

		} catch (Exception e) {
			s_log.log(Level.SEVERE, null, e);
		} finally {
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}

		return lst;
	}
	
	/**
	 * Validamos la configuracion del quirofano para ser utilizado en la programacion
	 * 
	 * @param horaDuracion
	 * @param minDuracion
	 * @param calInicio
	 * @return
	 */
	public String valido(String horaDuracion, String minDuracion, Date dateIni) {
		String error = null;
		
		Calendar calInicio = Calendar.getInstance();
		calInicio.setTime(dateIni);

		/* Verifica que la hora de inicio est� dentro del horario de funcionamiento del quir�fano */
		if (getDispQuirIni() != null) {

			/* No se debe de poder procesar la programaci�n a una hora en el que el quir�fano no est� disponible */
			Calendar dispQuirIni = Calendar.getInstance();
			dispQuirIni.setTimeInMillis(getDispQuirIni().getTime());

			// Si la hora de inicio es menor a la hora de apertura del quir�fano, enviar el error
			if (calInicio.get(Calendar.HOUR_OF_DAY) < dispQuirIni.get(Calendar.HOUR_OF_DAY))
				error = "msj.alert.horarioInicioQuir";
		}
		Calendar calFinal = Calendar.getInstance();
		calFinal.setTime(calInicio.getTime());
		calFinal.add(Calendar.HOUR_OF_DAY, Integer.valueOf(horaDuracion));
		calFinal.add(Calendar.MINUTE, Integer.valueOf(minDuracion));
		/* Verifica que la hora de inicio está dentro del horario de funcionamiento del quirófano */
		if (getDispQuirFin() != null) {
			Calendar dispQuirFin = Calendar.getInstance();
			// dispQuirFin.setTime(Constantes.sdfHora.parse(quirofano.getDispQuirFin().toString()));
			dispQuirFin.setTime(getDispQuirFin());

			boolean dispInicial = isOnSchedule(getCtx(), get_ID(), calInicio.getTime(), calInicio.getTime());
			boolean dispFinal = isOnSchedule(getCtx(), get_ID(), calFinal.getTime(), calFinal.getTime());
			if (!dispInicial) {
				error = "msj.alert.horarioInicioQuir";
			} else if (!dispFinal) {
				error = "msj.alert.horarioFinQuir";
			}
		}
		return error;
	}

	/**
	 * Dada dos fechas verifica si están dentro del horario de servicio del quirófano
	 * 
	 * @param ctx
	 * @param quirofanoId
	 * @param fechaIni
	 * @param fechaFin
	 * @return
	 */
	public static boolean isOnSchedule(Properties ctx, int quirofanoId, Date fechaIni, Date fechaFin) {

		boolean returnValue = false;
		// Quirofano del que sacaremos sus horas disponibles
		MEXMEQuirofano quiro = new MEXMEQuirofano(ctx, quirofanoId, null);

		// Iniciar las variables Calendar
		Calendar calIni = Calendar.getInstance();
		Calendar calFin = Calendar.getInstance();
		Calendar calDispQuirIni = Calendar.getInstance();
		Calendar calDispQuirFin = Calendar.getInstance();

		// Establecer sus valores
		calIni.setTime(fechaIni);
		calFin.setTime(fechaFin);
		calDispQuirIni.setTime(quiro.getDispQuirIni());
		calDispQuirFin.setTime(quiro.getDispQuirFin());

		if (quiro != null) {
			// Comparar las horas y minutos de las fechas
			// La hora inicial tiene que ser mayor o igual a la hora de disponibilidad inicial
			if ((calIni.get(Calendar.HOUR_OF_DAY) > calDispQuirIni.get(Calendar.HOUR_OF_DAY) || (calIni.get(Calendar.HOUR_OF_DAY) == calDispQuirIni
				.get(Calendar.HOUR_OF_DAY) && calIni.get(Calendar.MINUTE) >= calDispQuirIni.get(Calendar.MINUTE))) &&
			// Y la hora final tiene que ser menor o igual a la hora de disponibilidad final
				((calFin.get(Calendar.HOUR_OF_DAY) < calDispQuirFin.get(Calendar.HOUR_OF_DAY) || (calFin.get(Calendar.HOUR_OF_DAY) == calDispQuirFin
					.get(Calendar.HOUR_OF_DAY) && calFin.get(Calendar.MINUTE) <= calDispQuirFin.get(Calendar.MINUTE))))) {
				returnValue = true;
				// La hora final tiene que ser menor o igual a la hora de disponibilidad final
			}
		}
		return returnValue;
	}
	
	/**
	 * Enlistar los quirofanos por nivel de acceso
	 * @param ctx: Contexto
	 * @return List<ValueNamePair>
	 * @throws SQLException
	 */
	public static List<ValueNamePair> getLstOperatingRooms(final Properties ctx) {
		final List<ValueNamePair> list = new ArrayList<ValueNamePair>();
		list.add(new ValueNamePair(String.valueOf(0), StringUtils.EMPTY));
		
		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY)
		.append("SELECT * FROM EXME_Quirofano WHERE IsActive = 'Y' ")
		.append(MEXMELookupInfo.addAccessLevelSQL(ctx," ", Table_Name))
		.append(" ORDER BY Name ");
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				list.add(new ValueNamePair(String.valueOf(rset.getInt(COLUMNNAME_EXME_Quirofano_ID))
						, rset.getString(COLUMNNAME_Name)
						));
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, "getLstOperatingRooms", e);
		} finally {
			DB.close(rset, pstmt);
		}
		return list;
	}
}
