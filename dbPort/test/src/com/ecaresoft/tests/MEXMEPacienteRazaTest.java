package com.ecaresoft.tests;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import junit.framework.TestCase;

import org.compiere.model.MEXMEPaciente;
import org.compiere.model.MEXMEPacienteRaza;
import org.compiere.model.MEXMERazas;
import org.compiere.util.Env;
import org.compiere.util.Trx;
import org.junit.Before;

import com.ecaresoft.tests.utils.TestUtils;

public class MEXMEPacienteRazaTest extends TestCase {
	private MEXMEPaciente	exmePaciente;

	// private int initialRaceCount = 0;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		TestUtils.setUp();
		exmePaciente = TestUtils.getRandomPO(MEXMEPaciente.class);

	}

	public void test() {
		// Los metodos getMEXMEPacieneRazas y getPatientRaces deben de dar la
		// misma cantidad de registros.
		int firstCount1 = MEXMEPacienteRaza.getMEXMEPacieneRazas(Env.getCtx(), exmePaciente.getEXME_Paciente_ID(), null).size();
		List<MEXMERazas> razas = MEXMEPacienteRaza.getPatientRaces(Env.getCtx(), exmePaciente.getEXME_Paciente_ID(), null);
		int firstCount2 = razas.size();

		if (firstCount1 <= 0) {
			// Si no se obtiene ningun resultado en getMEXMEPacieneRazas,
			// suponemos que getPatientRaces solo trajo el resultado de
			// EXME_Razas que esta en la tabla EXME_Paciente
			assertEquals(exmePaciente.getEXME_Razas_ID() > 0 ? 1 : 0, firstCount2);
		} else {
			assertEquals(firstCount1, firstCount2);
		}

		MEXMERazas exmeRazas = getDistincRandomRace(razas);

		if (exmeRazas != null) {
			final Trx mTrx = Trx.get(Trx.createTrxName("raceTest"), true);
			try {
				List<Integer> set = new ArrayList<Integer>();
				set.add(exmeRazas.getEXME_Razas_ID());
				if (firstCount1 <= 0) {
					// Si pacRacesFirstCount es menor o igual a 0 quiere decir que se debe de tomar la raza en exme_paciente
					set.add(exmePaciente.getEXME_Razas_ID());
				}
				MEXMEPacienteRaza.save(Env.getCtx(), set, exmePaciente.getEXME_Paciente_ID(), mTrx.getTrxName());
				int secondCount1 = MEXMEPacienteRaza.getMEXMEPacieneRazas(Env.getCtx(), exmePaciente.getEXME_Paciente_ID(), mTrx.getTrxName())
						.size();				
				int secondCount2 = MEXMEPacienteRaza.getPatientRaces(Env.getCtx(), exmePaciente.getEXME_Paciente_ID(), mTrx.getTrxName()).size();
				//Validar que se haya agregado
				// Si firstCount1 si first count era 0 en un principio, entonces al momento de guardar se guardara el exme_raza id del paciente + otro distinto a ese
				if(firstCount1 <= 0){
					assertEquals(exmePaciente.getEXME_Razas_ID() > 0 ? 2 : 0, secondCount1);
				}else{
					assertEquals(firstCount1 + 1, secondCount1);
				}
				assertEquals(firstCount2 + 1, secondCount2);
			} finally {
				mTrx.rollback();
				mTrx.close();
			}
		}
	}

	private MEXMERazas getDistincRandomRace(List<MEXMERazas> razas) {
		StringBuilder where = new StringBuilder();
		if (!razas.isEmpty()) {
			where.append("EXME_Razas_ID NOT IN( ");
			boolean first = true;
			for (Iterator<MEXMERazas> iterator = razas.iterator(); iterator.hasNext();) {
				if (first) {
					where.append(iterator.next().getEXME_Razas_ID());
					first = false;
				} else {
					where.append(",");
					where.append(iterator.next().getEXME_Razas_ID());
				}
			}
			where.append(")");
		}
		return TestUtils.getRandomPO(MEXMERazas.class, where.toString(), true);
	}

}
