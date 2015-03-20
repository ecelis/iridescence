package com.ecaresoft.tests;

import java.util.List;

import junit.framework.TestCase;

import org.apache.commons.lang.StringUtils;
import org.compiere.model.MEXMECtaCuest;
import org.compiere.model.MEXMECuestionario;
import org.compiere.model.MEXMEPregunta;
import org.compiere.model.MEXMERespuestaCuestionario;
import org.compiere.util.Env;
import org.junit.Before;
import org.junit.Test;

import com.ecaresoft.tests.utils.TestUtils;

/**
 * Pruebas de MEXMECtaPac
 * 
 * @author odelarosa
 * 
 */
public class MEXMECtaCuestTest extends TestCase {

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		TestUtils.setUp();
	}

	@Test
	public void testSimpleSearch() {
		// buscamos si hay algun registro
		MEXMECtaCuest ctaCuest = TestUtils.getRandomPO(MEXMECtaCuest.class);

		if (ctaCuest != null) {
			// si lo hay, buscamos ese mismo dato
			List<MEXMECuestionario.QEvent> lst = MEXMECtaCuest.getFromPatient(Env.getCtx(), ctaCuest.getEXME_CtaPac().getEXME_Paciente_ID(), ctaCuest.getEXME_Cuestionario().getName(), null);

			// Debe tener al menos un registro, si no, hay error
			assertTrue(!lst.isEmpty());
		}
	}

	@Test
	public void testComplexSearch() {
		// buscamos si hay algun registro
		MEXMECtaCuest ctaCuest = TestUtils.getRandomPO(MEXMECtaCuest.class);

		if (ctaCuest != null) {
			// si lo hay, obtenemos sus respuestas
			List<MEXMERespuestaCuestionario> lstResp = MEXMERespuestaCuestionario.getRespuestas(Env.getCtx(), MEXMECtaCuest.Table_ID, ctaCuest.get_ID(), ctaCuest.getEXME_Cuestionario_ID(), 0, false, null);

			if (!lstResp.isEmpty()) {
				MEXMERespuestaCuestionario resp = null;
				for (MEXMERespuestaCuestionario dummy : lstResp) {
					// si la respuesta no es multi y tiene valor la seleccionamos
					if (!MEXMEPregunta.TIPODATO_MultiOptions.equals(dummy.getEXME_Pregunta().getTipoDato()) && StringUtils.isNotBlank(dummy.getTextBinary())) {
						resp = dummy;
						break;
					}
				}

				// si la respuesta no es nula
				if (resp != null) {
					// buscamos si coincide
					List<MEXMECuestionario.QEvent> lst = MEXMECtaCuest.search(Env.getCtx(), ctaCuest.getEXME_CtaPac().getEXME_Paciente_ID(), resp.getTextBinary(), null);

					// si esta vacia hay error
					assertTrue(!lst.isEmpty());

					boolean found = false;

					// iteramos las similitudes
					for (MEXMECuestionario.QEvent result : lst) {
						// si nuestro registro es encontrado se marca como encontrado
						if (ctaCuest.get_ID() == result.getRecordId()) {
							found = true;
							break;
						}
					}

					// Si no se encontró hay error
					assertTrue(found);
				}
			}
		}
	}
}
