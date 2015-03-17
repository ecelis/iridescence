package com.ecaresoft.tests;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import junit.framework.TestCase;

import org.apache.commons.lang.StringUtils;
import org.compiere.model.MEXMEPaciente;
import org.compiere.model.MEXMEPacienteLenguaje;
import org.compiere.model.MLanguage;
import org.compiere.util.Env;
import org.compiere.util.Trx;
import org.junit.Before;

import com.ecaresoft.tests.utils.TestUtils;

public class MEXMEPacienteLenguajeTest extends TestCase {
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
		// Los metodos getMEXMEPacienteLenguajes y getPatientLanguages deben de dar la
		// misma cantidad de registros antes y despues de guardar.
		int firstCount1 = MEXMEPacienteLenguaje.getMEXMEPacienteLenguajes(Env.getCtx(), exmePaciente.getEXME_Paciente_ID(), null).size();
		List<MLanguage> razas = MEXMEPacienteLenguaje.getPatientLanguages(Env.getCtx(), exmePaciente.getEXME_Paciente_ID(), null);
		int firstCount2 = razas.size();
		assertEquals(firstCount1, firstCount2);

		MLanguage newMLanguage = getDistincRandomLang(razas);

		if (newMLanguage != null) {
			final Trx mTrx = Trx.get(Trx.createTrxName("raceTest"), true);
			try {
				List<Integer> set = new ArrayList<Integer>();
				set.add(newMLanguage.getAD_Language_ID());
				MEXMEPacienteLenguaje.save(Env.getCtx(), set, exmePaciente.getEXME_Paciente_ID(), mTrx.getTrxName());
				int secondCount1 = MEXMEPacienteLenguaje.getMEXMEPacienteLenguajes(
						Env.getCtx(), exmePaciente.getEXME_Paciente_ID(), mTrx.getTrxName()).size();			
				
				int secondCount2 = MEXMEPacienteLenguaje.getPatientLanguages(Env.getCtx(), exmePaciente.getEXME_Paciente_ID(), mTrx.getTrxName())
						.size();
				//Validar que se haya agregado
				assertEquals(firstCount1 + 1, secondCount1);
				assertEquals(firstCount2 + 1, secondCount2);
			} finally {
				mTrx.rollback();
				mTrx.close();
			}
		}
	}

	private MLanguage getDistincRandomLang(List<MLanguage> razas) {
		StringBuilder where = new StringBuilder();
		if (!razas.isEmpty()) {
			where.append("AD_Language NOT IN( ");
			boolean first = true;
			for (Iterator<MLanguage> iterator = razas.iterator(); iterator.hasNext();) {
				if (first) {
					where.append(iterator.next().getAD_Language_ID());
					first = false;
				} else {
					where.append(",");
					where.append(iterator.next().getAD_Language_ID());
				}
			}
			where.append(")");
		}
		return TestUtils.getRandomPO(MLanguage.class, where.toString(), true);
	}

}
