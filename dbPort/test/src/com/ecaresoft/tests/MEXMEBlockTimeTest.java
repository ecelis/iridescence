package com.ecaresoft.tests;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

import junit.framework.TestCase;

import org.compiere.model.MEXMEBlockTime;
import org.compiere.model.MEXMECitaMedica;
import org.compiere.model.MEXMEMedico;
import org.compiere.util.Constantes;
import org.compiere.util.Env;
import org.junit.Before;
import org.junit.Test;

import com.ecaresoft.tests.utils.TestUtils;

/**
 * 
 * @author vperez
 */
public class MEXMEBlockTimeTest extends TestCase {

	@Before
	public void setUp() throws Exception {
		TestUtils.setUp();
	}
	
	/**
	 * Pruebas de funcionalidad para metodo {@link MEXMEBlockTime#getBlocksTime(int, int, java.util.Date, java.util.Date)}
	 */
	@Test
	public void testBlocksTime() {
		assertTrue(MEXMEBlockTime.getBlocksTime(Env.getCtx(), 0, 0, new Date(), new Date()).isEmpty());
		
		String dateBeing = Constantes.getSdfFechaHoraBD24().format(new Date());
		String dateEnd = Constantes.getSdfFechaHoraBD24().format(new Date());
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(new Date().getTime());
		cal.add(Calendar.YEAR, -2);
		String dateIni = Constantes.getSdfFechaHoraBD24().format(cal.getTime());
		MEXMEBlockTime blocktime = TestUtils.getRandomPO(MEXMEBlockTime.class, 
				MEXMEBlockTime.COLUMNNAME_Record_ID + ">0 AND " + MEXMEBlockTime.COLUMNNAME_AD_Table_ID + " > 0"
					+ " AND (((to_date('" + dateBeing + "', 'yyyy-mm-dd hh24:mi') BETWEEN fechahrini AND fechahrfin "
					+ " OR to_date('" + dateEnd + "', 'yyyy-mm-dd hh24:mi') BETWEEN fechahrini AND fechahrfin "
					+ " OR fechahrini BETWEEN to_date('" + dateBeing + "' ,'yyyy-mm-dd hh24:mi') "
					+ " AND to_date('" + dateEnd + "', 'yyyy-mm-dd hh24:mi') "
					+ " OR fechahrfin BETWEEN to_date('" + dateBeing + "' ,'yyyy-mm-dd hh24:mi') "
					+ " AND to_date('" + dateEnd + "', 'yyyy-mm-dd hh24:mi')) AND ISREPEAT = 'N') "
					+ " OR (fechahrini BETWEEN to_date('" + dateIni + "', 'yyyy-mm-dd hh24:mi') "
					+ " AND to_date('" + dateEnd + "', 'yyyy-mm-dd hh24:mi') AND ISREPEAT = 'Y')) ", true);
		if (blocktime != null) {
			assertFalse(MEXMEBlockTime.getBlocksTime(Env.getCtx(), MEXMEMedico.Table_ID, blocktime.getRecord_ID(), new Date(), new Date()).isEmpty());
		}
	}
	
	/**
	 * Prueba de funcionalidad para metodo {@link MEXMEBlockTime#save()}
	 */
	@Test
	public void testSave() {
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(new Date().getTime());
		cal.add(Calendar.DAY_OF_YEAR, -30);
		String dateIni = Constantes.getSdfFechaHoraBD24().format(cal.getTime());
		String dateEnd = Constantes.getSdfFechaHoraBD24().format(new Date());
		MEXMECitaMedica cita = TestUtils.getRandomPO(MEXMECitaMedica.class,
			" ISACTIVE = 'Y' AND FechaHrCita BETWEEN to_date('" + dateIni + "' ,'yyyy-mm-dd hh24:mi') "
			+ " AND to_date('" + dateEnd + "', 'yyyy-mm-dd hh24:mi') "
			+ " AND ESTATUS NOT IN ('5','8') ", true);
		if (cita != null) {
			MEXMEBlockTime block = new MEXMEBlockTime(Env.getCtx(), 0, null);
			block.setAD_Table_ID(MEXMEMedico.Table_ID);
			block.setRecord_ID(cita.getEXME_Medico_ID());
			block.setIsDaily(false);
			block.setIsWeekly(false);
			block.setIsRepeat(false);
			
			Calendar dateIn = Calendar.getInstance();
			dateIn.setTimeInMillis(cita.getFechaHrCita().getTime());
			Calendar dateFin = Calendar.getInstance();
			dateFin.setTimeInMillis(cita.getFechaHrCita().getTime()+ (cita.getDuracion() * 60000));
			
			block.setFechaHrFin(new Timestamp(dateFin.getTime().getTime()));
			block.setFechaHrIni(new Timestamp(dateIn.getTime().getTime()));
			
			assertFalse(block.save());
		}
	}
}
