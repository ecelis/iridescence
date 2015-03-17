/**
 * 
 */
package org.compiere.model;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.adempiere.exceptions.MedsysException;
import org.apache.commons.lang.StringUtils;
import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;
import org.compiere.util.Msg;
import org.compiere.util.Utilerias;

/**
 * Frequencies
 * 
 * @author lhernandez
 */
public class MEXMEFrequency2 extends X_EXME_Frequency2 {

	/** serialVersionUID */
	private static final long	serialVersionUID	= 1L;

	/** class log */
	private static CLogger		slog				= CLogger.getCLogger(MEXMEFrequency2.class);

	/**
	 * @param ctx
	 * @param EXME_Frequency2_ID
	 * @param trxName
	 */
	public MEXMEFrequency2(final Properties ctx, final int exmeFreq2Id, final String trxName) {
		super(ctx, exmeFreq2Id, trxName);
	}

	/**
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MEXMEFrequency2(final Properties ctx, final ResultSet result, final String trxName) {
		super(ctx, result, trxName);
	}

	/**
	 * Obtiene la lista de horarios para frecuencia 3 seleccionada
	 * 
	 * @return List<MEXMEFrequency3>
	 */
	public List<MEXMEFrequency3> getFrequencies3() {
		return MEXMEFrequency3.getFrequencies3(getCtx(), getEXME_Frequency2_ID());
	}

	@Override
	protected boolean beforeSave(boolean newRecord) {
		// si es autocalculado se valida que capture la cantidad y la unidad de medida
		if (getEXME_Frequency1().isAutoCalculate()) {
			// lhernandez. cuando se captura cero, se toma como unica aplicacion.
			if (getQuantity() > 0 && getC_UOM_ID() <= 0) {
				slog.saveError("", Utilerias.getMessage(Env.getCtx(), null, "error.frequency"));
				return false;
			}
		}
		return true;
	}

	/**
	 * Calcular las fechas de aplicacion en base a la configuracion de Stop Policy
	 * 
	 * @param start
	 * @param freq2
	 * @param qty
	 * @return
	 */
	public Date getDateTime(final Calendar start, final int qty) {
		slog.info("getDateTime");
		Date date = new Date();
		// a la fecha de inicio se le suma el intervalo y se agrega a la lista
		if (getC_UOM().getX12DE355().equals(Constantes.MINUTOS)) {
			start.add(Calendar.MINUTE, qty);
			date = start.getTime();
		} else if (getC_UOM().getX12DE355().equals(Constantes.HORAS)) {
			start.add(Calendar.HOUR, qty);
			date = start.getTime();
		} else if (getC_UOM().getX12DE355().equals(Constantes.DIAS)) {
			start.add(Calendar.DAY_OF_MONTH, qty);
			date = start.getTime();
		} else if (getC_UOM().getX12DE355().equals(Constantes.SEMANAS)) {
			start.add(Calendar.WEEK_OF_MONTH, qty);
			date = start.getTime();
		} else { // por default
			Utilerias.addDay(start); // aumentar al siguiente dia
			date = start.getTime();
		}
		return date;
	}

	/**
	 * Lista de frecuencias 2
	 * 
	 * @param ctx
	 * @param exmeFreq1Id
	 * @return
	 */
	public static List<KeyNamePair> getAll(final Properties ctx, final int exmeFreq1Id) {
		return getAll(ctx, exmeFreq1Id, null, null);
	}

	/**
	 * Lista de frecuencias 2
	 * 
	 * @param ctx
	 * @param exmeFreq1Id
	 * @param onlyActive
	 * @return
	 */
	public static List<KeyNamePair> getAll(final Properties ctx, final int exmeFreq1Id, final String whereClause, List<Object> params) {
		slog.info("getAllFreqs2");
		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);

		sql.append("SELECT EXME_Frequency2.EXME_Frequency2_ID, lower(EXME_Frequency2.Name) as name ");
		sql.append(" FROM EXME_Frequency2 ");
		sql.append(" WHERE EXME_Frequency2.EXME_Frequency1_ID = ? ");
		sql.append(" AND EXME_Frequency2.IsActive = 'Y' ");
		if (StringUtils.isNotBlank(whereClause)) {
			if(!StringUtils.trim(whereClause).startsWith("AND")) {
				sql.append(" AND ");
			}
			sql.append(whereClause);
		}
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name));
		sql.append(" ORDER BY EXME_Frequency2.Name ASC ");

		if(params == null){
			params = new ArrayList<Object>();
		}
		params.add(0,exmeFreq1Id);
		return Query.getListKN(sql.toString(), null, params.toArray());
	}

	/**
	 * Lista de frecuencias 2
	 * 
	 * @param ctx
	 * @param exmeFreq1Id
	 * @return
	 */
	public static List<MEXMEFrequency2> getAllFreq2(final Properties ctx, final int exmeFreq1Id) {
		return getAllFreq2(ctx, exmeFreq1Id, null, null);
	}

	
	/**
	 * Lista de frecuencias 2
	 * 
	 * @param ctx
	 * @param whereClause
	 * @return
	 */
	public static List<MEXMEFrequency2> getAllFreq2(final Properties ctx, final int exmeFreq1Id, String whereClause, List<Object> params) {
		slog.info("List<MEXMEFrequency2> getAllFreq2");
		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append(" EXME_Frequency2.EXME_Frequency1_ID=? ");
		if (StringUtils.isNotBlank(whereClause)) {
			if (!StringUtils.trim(whereClause).startsWith("AND")) {
				sql.append(" AND ");
			}
			sql.append(whereClause);
		}
		if (params == null) {
			params = new ArrayList<Object>();
		}
		params.add(0, exmeFreq1Id);//primera posicion
		return new Query(ctx, MEXMEFrequency2.Table_Name, sql.toString(), null)//
			.setParameters(params)//
			.addAccessLevelSQL(true)//
			.setOrderBy(" EXME_Frequency2.Name ")//
			.list();
	}

	
	/**
	 * Regresa la frecuencia para una dosis unica
	 * 
	 * @param ctx
	 * @param autoCalculated
	 *            Si es TRUE: equivalente a la opcion Now. Valida si la frequencia 1 es
	 *            AutoCalculada o no.
	 * @param type
	 *            Tipo de frecuencia. Referencia: {@link X_EXME_Frequency1#TYPE_AD_Reference_ID} si
	 *            es nulo en la base de datos, se considera de medicamentos.
	 * @return
	 */
	public static MEXMEFrequency2 getOnceFrequency(final Properties ctx, final boolean autoCalculated, final String type) {
		final StringBuilder join = new StringBuilder();
		join.append(" INNER JOIN EXME_Frequency1 f1 ON (");
		join.append(" f1.EXME_Frequency1_ID=EXME_Frequency2.EXME_Frequency1_ID ");
		join.append(" AND f1.AutoCalculate=? ");
		join.append(" AND COALESCE(f1.Type,'M')=? ");// MEXMEFrequency1.TYPE_Medication
		join.append(" AND f1.isActive='Y'");
		join.append(" ) ");
		return new Query(ctx, Table_Name, " COALESCE(EXME_Frequency2.Quantity,0)=0 ", null)//
				.setJoins(join)//
				.setParameters(DB.TO_STRING(autoCalculated), type)//
				.setOnlyActiveRecords(true)//
				.addAccessLevelSQL(true)//
				.first();
	}

	public String getSummary() {
		final StringBuilder str = new StringBuilder();
		if (getQuantity() > 0) {
			str.append("").append(Msg.translate(Env.getAD_Language(getCtx()), "Every")).append(" ");
			str.append(getQuantity()).append("  ");
			str.append(getC_UOM().getName()).append("<br>");
		} else {
			
		}
		if (isStartDateReq()) {
			str.append("").append(Msg.translate(Env.getAD_Language(getCtx()), COLUMNNAME_StartDateReq)).append(" ");
		}
		return str.toString();
	}

	
	@Override
	protected boolean beforeDelete() {
		if(!is_new()){
			final List<MEXMEFrequency3> lst = getFrequencies3();
			for (MEXMEFrequency3 frequency3 : lst) {
				if(!frequency3.delete(true, get_TrxName())){
					return false;
				}
			}
		}
		return super.beforeDelete();
	}
	
	
	/**
	 * Genera los registros necesarios en EXME_Frequency3
	 * de acuerdo a EXME_Frequency2 que recibe como parametro
	 */
	public List<MEXMEFrequency3> createFrequencies3(final Date timeStart) {
		List<MEXMEFrequency3> lstFreq3 = new ArrayList<MEXMEFrequency3>();
		// fecha inicial proporcionada por el usuario
		final Calendar calendar = Calendar.getInstance();
		calendar.setTime(timeStart);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		// fecha final es el mismo dia, a las 12:59 pm
		final Calendar calFin = Calendar.getInstance();
		calFin.set(Calendar.HOUR_OF_DAY, 23);
		calFin.set(Calendar.MINUTE, 59);
		calFin.set(Calendar.SECOND, 59);
		calFin.set(Calendar.MILLISECOND, 0);

		// el intervalo sera en base a los minutos
		final int intervalo;
		final int field = Calendar.MINUTE;
		if (getC_UOM().getX12DE355().equalsIgnoreCase(Constantes.HORAS)) {
			intervalo = getQuantity() * 60;
		} else if (getC_UOM().getX12DE355().equalsIgnoreCase(Constantes.MINUTOS)) {
			intervalo = getQuantity();
		} else {
			intervalo = 0;// si es dia, o "Once", solo se creara un registro
		}

		final Timestamp timestamp = new Timestamp(calendar.getTime().getTime());
		// crea las frecuencias para 1 solo dia
		while (calendar.getTime().before(calFin.getTime())) {
			final MEXMEFrequency3 freq3 = new MEXMEFrequency3(this);
			freq3.setHour(timestamp);
			if (!freq3.save()) {
				throw new MedsysException();// no msg needed
			}
			lstFreq3.add(freq3);
			// sumar el intervalo, solo si es mayor a 0
			if (intervalo == 0) {
				break;
			}
			calendar.add(field, intervalo);
			timestamp.setTime(calendar.getTimeInMillis());
		}
		return lstFreq3;
	}
}
