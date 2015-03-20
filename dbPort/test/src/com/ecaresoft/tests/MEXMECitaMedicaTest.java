/**
 * 
 */
package com.ecaresoft.tests;

import static org.compiere.util.Env.getCtx;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;

import junit.framework.TestCase;

import org.compiere.Compiere;
import org.compiere.model.MEXMEBlockTime;
import org.compiere.model.MEXMECitaMedica;
import org.compiere.model.MEXMEFinancialClass;
import org.compiere.model.MEXMEMedicoSust;
import org.compiere.model.MEXMEMotivoCita;
import org.compiere.model.MEXMEPaciente;
import org.compiere.model.MSession;
import org.compiere.util.CLogMgt;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Ini;
import org.compiere.util.Login;
import org.compiere.util.Msg;
import org.compiere.util.Utilerias;
import org.junit.Before;
import org.junit.Test;

import com.ecaresoft.tests.utils.TestUtils;
import com.google.common.primitives.Ints;

/**
 * 
 * @author vperez
 */
public class MEXMECitaMedicaTest extends TestCase {

	@Before
	public void setUp() throws Exception {
		// TestUtils.setUp();

		String bdName = Utilerias.getPropertiesFromEnv("COMPIERE_DB_NAME");
		String bdHost = Utilerias.getPropertiesFromEnv("COMPIERE_DB_SERVER");
		String appHost = Utilerias.getPropertiesFromEnv("COMPIERE_APPS_SERVER");

		final StringBuilder connString = new StringBuilder();
		connString.append("CConnection[name=apl01{10.90.1.80-10.90.1.79-expert},");
		connString.append("AppsHost=").append(appHost).append(",");
		connString.append("AppsPort=0,Profile=L,type=PostgreSQL,");
		connString.append("DBhost=").append(bdHost).append(",");
		connString.append("DBport=5432,DBname=").append(bdName).append(",");
		connString.append("BQ=false,FW=false,FWhost=,FWport=1630,UID=ecaresoft,PWD=ecaresoft]");

		Ini.setProperty(Ini.P_CONNECTION, connString.toString());

		Compiere.startup(true);
		CLogMgt.setLoggerLevel(Level.INFO, null);
		CLogMgt.setLevel(Level.INFO);

		String userName = "SUPERUSER";
		int ad_org_id = 10001437;

		String pass = DB.getSQLValueString(null, "SELECT password FROM AD_User WHERE NAME=? ", userName);
		String orgName = DB.getSQLValueString(null, "SELECT name FROM AD_Org WHERE AD_Org_ID=? ", ad_org_id);
		int clientId = DB.getSQLValue(null, "SELECT AD_Client_id FROM AD_Org WHERE AD_Org_ID=? ", ad_org_id);
		String clientName = DB.getSQLValueString(null, "SELECT name FROM AD_Client WHERE AD_Client_ID=? ", clientId);
		String roleName = DB.getSQLValueString(null, "SELECT name FROM ad_role WHERE name LIKE 'Admin %' AND ad_client_id=?", clientId);
		String warehouse = DB.getSQLValueString(null, "SELECT name FROM m_warehouse WHERE ad_client_id=? ", clientId);

		Ini.setProperty(Ini.P_UID, userName);
		Ini.setProperty(Ini.P_PWD, pass);
		Ini.setProperty(Ini.P_ROLE, roleName);
		Ini.setProperty(Ini.P_CLIENT, clientName);
		Ini.setProperty(Ini.P_ORG, orgName);
		Ini.setProperty(Ini.P_WAREHOUSE, warehouse);
		Ini.setProperty("#EXME_EstServ_ID", "");
		Ini.setProperty(Ini.P_LANGUAGE, "Spanish");

		// Ini.setProperty(Ini.P_UID,"DLUNA");
		// Ini.setProperty(Ini.P_PWD,"8UckBialPUsUr/CdsKMltg==");
		// Ini.setProperty(Ini.P_ROLE,"Admin  Zánitas Clínica + Hospital, S.A. de C.V.");
		// Ini.setProperty(Ini.P_CLIENT, "Zánitas Clínica + Hospital, S.A. de C.V.");
		// Ini.setProperty(Ini.P_ORG,"Zánitas Clínica + Hospital, S.A. de C.V.");
		// Ini.setProperty(Ini.P_WAREHOUSE,"CONSULTA EXTERNA");
		// Ini.setProperty("#EXME_EstServ_ID","");
		// Ini.setProperty(Ini.P_LANGUAGE,"Spanish");

		final Login login = new Login(Env.getCtx());

		if (!login.batchLogin(null)) {
			System.exit(1);
		}
		MSession.get(Env.getCtx(), "", "", "", null);
	}

	/**
	 * Prueba la funcionalidad de {@link MEXMECitaMedica#save()}
	 */
	@Test
	public void testSave() {
		String dateBeing = Constantes.getSdfFechaHoraBD24().format(new Date());
		String dateEnd = Constantes.getSdfFechaHoraBD24().format(new Date());
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(new Date().getTime());
		cal.add(Calendar.YEAR, -2);
		String dateIni = Constantes.getSdfFechaHoraBD24().format(cal.getTime());

		MEXMEPaciente pac = TestUtils.getRandomPO(MEXMEPaciente.class, " ISACTIVE = 'Y'", false);
		MEXMEBlockTime blocktime =
			TestUtils.getRandomPO(MEXMEBlockTime.class, MEXMEBlockTime.COLUMNNAME_Record_ID + ">0 AND " + MEXMEBlockTime.COLUMNNAME_AD_Table_ID
				+ " > 0" + " AND (((to_date('" + dateBeing + "', 'yyyy-mm-dd hh24:mi') BETWEEN fechahrini AND fechahrfin " + " OR to_date('"
				+ dateEnd + "', 'yyyy-mm-dd hh24:mi') BETWEEN fechahrini AND fechahrfin " + " OR fechahrini BETWEEN to_date('" + dateBeing
				+ "' ,'yyyy-mm-dd hh24:mi') " + " AND to_date('" + dateEnd + "', 'yyyy-mm-dd hh24:mi') " + " OR fechahrfin BETWEEN to_date('"
				+ dateBeing + "' ,'yyyy-mm-dd hh24:mi') " + " AND to_date('" + dateEnd + "', 'yyyy-mm-dd hh24:mi')) AND ISREPEAT = 'N') "
				+ " OR (fechahrini BETWEEN to_date('" + dateIni + "', 'yyyy-mm-dd hh24:mi') " + " AND to_date('" + dateEnd
				+ "', 'yyyy-mm-dd hh24:mi') AND ISREPEAT = 'Y')) ", true);
		MEXMECitaMedica datos = new MEXMECitaMedica(Env.getCtx(), 0, null);
		datos.setEXME_Medico_ID(blocktime.getRecord_ID());
		datos.setEXME_MEDICO_ORIG(blocktime.getRecord_ID());
		datos.setEXME_Paciente_ID(pac.getEXME_Paciente_ID());
		datos.setDuracion(5);
		datos.setEXME_MotivoCita_ID(0);
		datos.setEXME_Especialidad_ID(0);
		datos.setEXME_EstServ_ID(0);
		datos.setName(Msg.translate(getCtx(), "Cita") + " ");
		datos.setFechaHrCita(blocktime.getFechaHrIni());
		datos.setEstatus(MEXMECitaMedica.ESTATUS_ToBeConfirmed);
		assertFalse(datos.save());

		MEXMECitaMedica datosCorrectos = new MEXMECitaMedica(Env.getCtx(), 0, null);
		MEXMECitaMedica datosBase = TestUtils.getRandomPO(MEXMECitaMedica.class, " ISACTIVE = 'Y'", false);
		datosCorrectos.setEXME_Medico_ID(datosBase.getEXME_Medico_ID());
		datosCorrectos.setEXME_MEDICO_ORIG(datosBase.getEXME_MEDICO_ORIG());
		datosCorrectos.setEXME_Paciente_ID(datosBase.getEXME_Paciente_ID());
		datosCorrectos.setDuracion(5);
		datosCorrectos.setDescription("JUnit");
		datosCorrectos.setEXME_Especialidad_ID(0);
		datosCorrectos.setEXME_EstServ_ID(0);
		datosCorrectos.setName(Msg.translate(getCtx(), "Cita") + " ");
		datosCorrectos.setFechaHrCita(datosBase.getFechaHrCita());
		datosCorrectos.setEstatus(MEXMECitaMedica.ESTATUS_ToBeConfirmed);

		MEXMEMotivoCita motivo = TestUtils.getRandomPO(MEXMEMotivoCita.class, " ISACTIVE = 'Y'", true);
		datosCorrectos.setEXME_MotivoCita_ID(motivo.get_ID());
		assertTrue(datosCorrectos.save());
	}

	/**
	 * Prueba la funcionalidad de {@link MEXMECitaMedica#getEXME_GrupoCuestionario()}
	 */
	@Test
	public void testgetEXME_GrupoCuestionario() {
		MEXMECitaMedica cita = TestUtils.getRandomPO(MEXMECitaMedica.class, " ISACTIVE = 'Y'", false);
		assertTrue(cita.getEXME_GrupoCuestionario_ID() > 0 ? cita.getEXME_GrupoCuestionario() != null : true);
	}

	/**
	 * Prueba la funcionalidad de {@link MEXMECitaMedica#getEXME_GrupoCuestionario()}
	 */
	@Test
	public void testgetApptForBilling() {
		try {
			Date endDate = Constantes.getSdfFecha().parse(Constantes.getSdfFecha().format(DB.convert(Env.getCtx(), new Date())));
			Calendar cal = Calendar.getInstance();
			cal.setTimeInMillis(endDate.getTime());
			cal.add(Calendar.DAY_OF_YEAR, (int) (Math.random() * -100) - 100);
			MEXMEMotivoCita mot = TestUtils.getRandomPO(MEXMEMotivoCita.class, " ISACTIVE = 'Y'", true);
			MEXMEFinancialClass pFC = TestUtils.getRandomPO(MEXMEFinancialClass.class, " ISACTIVE = 'Y'", true);
			List<MEXMECitaMedica> citas =
				MEXMECitaMedica.getApptForBilling(Env.getCtx(), DB.invertConvert(Env.getCtx(), cal.getTime()),
					DB.invertConvert(Env.getCtx(), endDate), mot.getEXME_MotivoCita_ID(), pFC.getValue(), null);
			boolean valid = true;
			for (MEXMECitaMedica cita : citas) {
				if (!pFC.getName().equals(cita.getFinancialClassName()) || mot.getEXME_MotivoCita_ID() != cita.getEXME_MotivoCita_ID()) {
					valid = false;
					break;
				}
			}
			assertTrue(valid);
		} catch (ParseException e) {
			assertTrue(false);
			e.printStackTrace();
		}
	}

	/** Test method for {@link MEXMECitaMedica#getNextAppt(java.util.Properties, int, java.sql.Timestamp)} */
	public void testGetNextAppt() {
		// vacio si es nuevo
		assertNull(MEXMECitaMedica.getNextAppt(Env.getCtx(), 0, null));
		// random
		final MEXMECitaMedica obj = TestUtils.getRandomPO(MEXMECitaMedica.class);
		if (obj != null) {
			final Calendar cal = Calendar.getInstance();
			cal.setTime(obj.getFechaHrCita());
			cal.add(Calendar.HOUR_OF_DAY, -1);
			assertNotNull(MEXMECitaMedica.getNextAppt(obj.getCtx(), obj.getEXME_Paciente_ID(), obj.getFechaHrCita()));
		}
	}

	/** Test method for {@link MEXMECitaMedica#getCitasCalendar(java.util.Properties, int[], int, int[], Date, Date, String)} */
	public void testGetCitasCalendar() {
		// vacio si es nuevo
		try {
			// evitar nullpointerexception - lista vacia
			java.util.Properties ctx = Env.getCtx();
			List<MEXMECitaMedica> list = MEXMECitaMedica.getCitasCalendar(ctx, null, 0, null, null, null, null);
			assertNotNull(list);
			assertTrue(list.isEmpty());
			// buscar una cita valida (no cerrada y no cancelada)
			final MEXMECitaMedica obj = TestUtils.getRandomPO(MEXMECitaMedica.class, " estatus NOT  IN ('5','8') ", true);
			if (obj != null) {
				// si existe el metodo debe regresar al menos esa cita, se usa la misma fecha, para validar rango
				list =
					MEXMECitaMedica.getCitasCalendar(ctx, obj.getEXME_Medico_ID(), obj.getEXME_Paciente_ID(), 0, obj.getFechaHrCita(),
						obj.getFechaHrCita(), null);
				assertNotNull(list);
				assertTrue(!list.isEmpty());
				// validamos que la cita random este incluida en la lista
				boolean included = false;
				for (MEXMECitaMedica mexmeCitaMedica : list) {
					if (mexmeCitaMedica.getEXME_CitaMedica_ID() == obj.getEXME_CitaMedica_ID()) {
						included = true;
						break;
					}
				}
				assertTrue(included);
				// revisamos si el medico de la cita random tiene sustitutos
				List<Integer> sustId = MEXMEMedicoSust.getMedsSustitutos(ctx, obj.getEXME_Medico_ID(), null);
				// ArrayUtils.toPrimitive(sustId.toArray(new Integer[sustId.size()]));
				if (sustId != null && !sustId.isEmpty()) {
					// si se envia el sustituto y el medico, debe regresa al menos la cita random
					list =
						MEXMECitaMedica.getCitasCalendar(ctx, new int[] { obj.getEXME_Medico_ID() }, obj.getEXME_Paciente_ID(), Ints.toArray(sustId),
							obj.getFechaHrCita(), obj.getFechaHrCita(), null);
					assertNotNull(list);
					assertTrue(!list.isEmpty());
				} else {
					// si no, buscamos un sustituto random
					final MEXMEMedicoSust obj2 = TestUtils.getRandomPO(MEXMEMedicoSust.class);
					if (obj2 != null) {
						// buscamos la cita de ese medico sustituto
						list = MEXMECitaMedica.getCitasCalendar(ctx, null, 0, new int[] { obj2.getSubstitute_ID() }, null, null, null);
						assertNotNull(list);
						// si hubo citas
						boolean hasAppts = !list.isEmpty();

						// buscamos los medicos relacionados a ese sustituto
						List<Integer> medIds = MEXMEMedicoSust.getMedicos(ctx, obj2.getSubstitute_ID(), null);
						// buscamos las citas de los medicos relacionads, y del sustituto
						list =
							MEXMECitaMedica.getCitasCalendar(ctx, Ints.toArray(medIds), 0, new int[] { obj2.getSubstitute_ID() }, null, null, null);
						assertNotNull(list);

						// debe regresar al menos las del sustituto (si tiene)
						if (hasAppts) {
							assertTrue(!list.isEmpty());
						}
					}
				}
			}
		} catch (Exception e) {
			assertTrue(false);
			e.printStackTrace();
		}
	}

}