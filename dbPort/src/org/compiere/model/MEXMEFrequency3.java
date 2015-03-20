/**
 * 
 */
package org.compiere.model;

import java.sql.ResultSet;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.compiere.util.CLogger;
import org.compiere.util.Constantes;

/**
 * Frequencies
 */
public class MEXMEFrequency3 extends X_EXME_Frequency3 {

	/** serialVersionUID */
	private static final long	serialVersionUID	= 1L;

	/** class log */
	private static CLogger		slog				= CLogger.getCLogger(MEXMEFrequency3.class);

	/**
	 * @param ctx
	 * @param EXME_Frequency3_ID
	 * @param trxName
	 */
	public MEXMEFrequency3(Properties ctx, int EXME_Frequency3_ID, String trxName) {
		super(ctx, EXME_Frequency3_ID, trxName);
	}

	/**
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MEXMEFrequency3(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}
	
	/**
	 * @param ctx
	 * @param EXME_Frequency3_ID
	 * @param trxName
	 */
	public MEXMEFrequency3(MEXMEFrequency2 freq2) {
		super(freq2.getCtx(), 0, freq2.get_TrxName());
		setEXME_Frequency1_ID(freq2.getEXME_Frequency1_ID());
		setEXME_Frequency2_ID(freq2.getEXME_Frequency2_ID());
	}

	/**
	 * Obtiene la lista de horarios para frecuencia 2 seleccionada
	 * 
	 * @param ctx
	 * 
	 * @param exmeFreq2 EXME_Frequency2_ID
	 * @return List<MEXMEFrequency3>
	 */
	public static List<MEXMEFrequency3> getFrequencies3(Properties ctx, int exmeFreqId2) {
		slog.info("getFrequencies3");
		return new Query(ctx, Table_Name, " EXME_Frequency3.EXME_Frequency2_ID=? ", null)// /
				.setParameters(exmeFreqId2)//
				// .setOnlyActiveRecords(true)//
				// .addAccessLevelSQL(true)//
				.setOrderBy(" EXME_Frequency3.Hour ")// )
				.list();
	}

	/**
	 * si la nueva fecha inicial es posterior a la fecha seleccionada de inicio
	 * 
	 * @param start
	 * @param compareTo
	 * @return
	 */
	public boolean isDateAfter(final Calendar start, final Date compareTo) {
		slog.info("isDateAfter");
		final Calendar hour = Calendar.getInstance();
		hour.setTime(getHour());

		start.set(Calendar.HOUR_OF_DAY, hour.get(Calendar.HOUR_OF_DAY));
		start.set(Calendar.MINUTE, hour.get(Calendar.MINUTE));
		start.set(Calendar.SECOND, 0);
		start.set(Calendar.MILLISECOND, 0);

		return start.getTime().after(compareTo);
	}

	public String getHourFormated() {
		return super.getHour() == null ? "" : Constantes.getSdfHora(getCtx()).formatConvert(super.getHour());
	}

}
