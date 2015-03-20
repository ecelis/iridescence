/**
 * 
 */
package com.ecaresoft.tests.process;


import java.math.BigDecimal;

import org.compiere.model.MPeriod;
import org.compiere.model.MYear;
import org.compiere.process.CierreEjercicio;
import org.compiere.util.Env;
import org.junit.Before;
import org.junit.Test;

import com.ecaresoft.tests.utils.TestUtils;

import junit.framework.TestCase;

/**
 * @author mvrodriguez
 *
 */
public class CierreEjercicioTest extends TestCase {

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	@Before
	protected void setUp() throws Exception {
		TestUtils.setUpDemoMX();
	}

	/**
	 * Test method for {@link org.compiere.process.CierreEjercicio#obtieneUtilidadPerdida(java.util.Properties, org.compiere.model.MYear, org.compiere.model.MPeriod, int, java.math.BigDecimal, java.lang.String)}.
	 */
	@Test
	public void testObtieneUtilidadPerdidaPropertiesMYearMPeriodIntBigDecimalString() {
		final MYear year = new MYear(Env.getCtx(), 10001013, null);
		final MPeriod period = new MPeriod(Env.getCtx(), 10001155, null);
		
		final BigDecimal utilidad = CierreEjercicio.obtieneUtilidadPerdida(
				Env.getCtx(), 
				year, 
				period, 
				Env.getAD_Client_ID(Env.getCtx()), 
				null, null);
		assertTrue(utilidad.compareTo(Env.ZERO)!=0);
	}

}
