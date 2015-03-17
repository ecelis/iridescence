/**
 * 
 */
package com.ecaresoft.tests;

import java.math.BigDecimal;

import junit.framework.TestCase;

import org.apache.commons.lang.StringUtils;
import org.compiere.model.MEXMEEsqInsulina;
import org.compiere.model.MEXMEEsqInsulinaLine;
import org.compiere.util.Env;
import org.junit.Before;
import org.junit.Test;

import com.ecaresoft.tests.utils.TestUtils;

/**
 * Test Class form {@link org.compiere.model.MEXMEEsqInsulina}
 * 
 * @author Lorena Lama
 */
public class MEXMEEsqInsulinaTest extends TestCase {

	/** @see junit.framework.TestCase#setUp() */
	@Before
	protected void setUp() throws Exception {
		TestUtils.setUpLake();
	}

	/**
	 * Test method for
	 * {@link org.compiere.model.MEXMEEsqInsulina#get(java.util.Properties, int, java.lang.String)}.
	 */
	@Test
	public void testGetPropertiesIntString() {
		// si no existen registros el metodo debe regresar vacio
		assertNotNull(MEXMEEsqInsulina.get(Env.getCtx(), 0, null));
		final MEXMEEsqInsulina insulin = TestUtils.getRandomPO(MEXMEEsqInsulina.class, " EXME_Paciente_ID IS NOT NULL ", true);
		if (insulin != null) {
			// si existe al menos un registro en la BD el metodo NO debe regresar vacio
			assertFalse(MEXMEEsqInsulina.get(Env.getCtx(), insulin.getEXME_Paciente_ID(), null).isEmpty());
		}
	}

	/**
	 * Test method for
	 * {@link org.compiere.model.MEXMEEsqInsulina#getAll(java.util.Properties, java.lang.String)}.
	 */
	@Test
	public void testGetAll() {
		final MEXMEEsqInsulina insulin = TestUtils.getRandomPO(MEXMEEsqInsulina.class);
		if (insulin == null) {
			// si no existen registros el metodo debe regresar vacio
			assertTrue(MEXMEEsqInsulina.getAll(Env.getCtx(), null).isEmpty());
		} else {
			// si existe al menos un registro en la BD el metodo no debe regresar vacio
			assertFalse(MEXMEEsqInsulina.getAll(Env.getCtx(), null).isEmpty());
		}
	}

	/**
	 * Test method for {@link org.compiere.model.MEXMEEsqInsulina#getDetail()}.
	 */
	@Test
	public void testGetDetail() {
		// si el registro es nuevo, la lista no debe tener valores
		assertTrue(new MEXMEEsqInsulina(Env.getCtx(), 0, null).getDetail().isEmpty());
		final MEXMEEsqInsulina insulin = TestUtils.getRandomPO(MEXMEEsqInsulina.class);
		if (insulin != null) {
			// valida que no sea nula
			assertNotNull(insulin.getDetail());
		}
	}

	/**
	 * Test method for {@link org.compiere.model.MEXMEEsqInsulina#getTipoStr()}.
	 */
	@Test
	public void testGetTipoStr() {
		MEXMEEsqInsulina insulin = TestUtils.getRandomPO(MEXMEEsqInsulina.class);
		if (insulin == null) {
			insulin = new MEXMEEsqInsulina(Env.getCtx(), 0, null);
		}
		// si el objeto es nuevo o no tiene tipo, TipoStr debe regresar ""
		if (StringUtils.isBlank(insulin.getTipo())) {
			assertEquals("", insulin.getTipoStr());
		} else {
			// si no, tiene que regresar la desripcion del Tipo
			final String retValue = insulin.getTipoStr();
			assertNotSame("", retValue);
			assertNotSame(insulin.getTipo(), retValue);
		}

	}
	/**
	 * Test method for {@link org.compiere.model.MEXMEEsqInsulina#getMatchResult(java.math.BigDecimal))}.
	 */
	public void testGetMatchResult() throws Exception {
		final MEXMEEsqInsulinaLine line = TestUtils.getRandomPO(MEXMEEsqInsulinaLine.class);
		if (line != null) {
			final MEXMEEsqInsulina header = line.getEXME_EsqInsulina();
			assertNotNull(header.getMatchResult(new BigDecimal(line.getLim_Inferior() + 1)));
		}
	}
	/**
	 * Test method for {@link org.compiere.model.MEXMEEsqInsulina#get(java.util.Properties, int, int, String))}.
	 */
	public void testGetPropertiesIntIntString() throws Exception {
		// si no existen registros el metodo debe regresar vacio
		assertNotNull(MEXMEEsqInsulina.get(Env.getCtx(), 0, 0, null));
		final MEXMEEsqInsulina insulin = TestUtils.getRandomPO(MEXMEEsqInsulina.class, " EXME_Paciente_ID IS NOT NULL OR EXME_Medico_ID NOT NULL ",
				true);
		if (insulin != null) {
			// si existe al menos un registro en la BD el metodo NO debe regresar vacio
			assertFalse(MEXMEEsqInsulina.get(Env.getCtx(), insulin.getEXME_Paciente_ID(), insulin.getEXME_Medico_ID(), null).isEmpty());
		}
	}
	
}
