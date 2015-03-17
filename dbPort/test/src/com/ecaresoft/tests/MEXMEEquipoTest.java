/**
 * 
 */
package com.ecaresoft.tests;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

import junit.framework.TestCase;

import org.compiere.model.MEXMEEquipo;
import org.compiere.model.MEXMEEquipoH;
import org.compiere.util.Env;

import com.ecaresoft.tests.utils.TestUtils;

/**
 * @author Lorena Lama
 *
 */
public class MEXMEEquipoTest extends TestCase {

	/**@see junit.framework.TestCase#setUp() */
	protected void setUp() throws Exception {
		TestUtils.setUpLake();
	}

//	/**
//	 * Test method for {@link org.compiere.model.MEXMEEquipo#isNew(int, java.lang.String)}.
//	 */
//	public void testIsNew() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MEXMEEquipo#getEquiposPorOrden(java.util.Properties, java.lang.String, java.util.List, java.lang.String)}.
//	 */
//	public void testGetEquiposPorOrden() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MEXMEEquipo#getEquipo(java.util.Properties, java.lang.String, java.util.List, java.lang.String)}.
//	 */
//	public void testGetEquipo() {
//		fail("Not yet implemented"); // TODO
//	}

	/**
	 * Test method for {@link org.compiere.model.MEXMEEquipo#get(java.util.Properties, int, java.lang.String)}.
	 */
	public void testGet() {
		// vacio si es nuevo
		assertTrue(MEXMEEquipo.get(Env.getCtx(), 0, null).isEmpty());
		// random
		final MEXMEEquipo obj = TestUtils.getRandomPO(MEXMEEquipo.class);
		if (obj != null) {
			assertFalse(MEXMEEquipo.get(Env.getCtx(), obj.getEXME_Area_ID(), null).isEmpty());
		}
	}

	/**
	 * Test method for {@link org.compiere.model.MEXMEEquipo#getAvailable(java.util.Properties, int, java.sql.Timestamp, java.sql.Timestamp, java.lang.String)}.
	 */
	public void testGetAvailable() {
		// vacio si es nuevo
		assertTrue(MEXMEEquipo.getAvailable(Env.getCtx(), 0, null, null, null).isEmpty());
		try {
			// random
			final MEXMEEquipoH obj = TestUtils.getRandomPO(MEXMEEquipoH.class);
			
			final MEXMEEquipo obj2;
			if (obj == null) {
				obj2 = TestUtils.getRandomPO(MEXMEEquipo.class);
			} else {
				// si existe una programacion, no debe regresar disponible
				final List<MEXMEEquipo> lst = MEXMEEquipo.getAvailable(Env.getCtx(), obj.getEXME_Area_ID(), obj.getFecha_Ini(), obj.getFecha_Fin(), null);
				for (MEXMEEquipo mexmeEquipo : lst) {
					assertTrue(mexmeEquipo.getEXME_Equipo_ID()!=obj.getEXME_Equipo_ID());
				}
				obj2 = obj.getEXME_Equipo();
			}
			if(obj2 != null) { // si existe un equipo, validar que la lista no este vacia.
				final Timestamp fechaini = new Timestamp(new SimpleDateFormat("dd/MM/yyyy").parse("01/01/1900").getTime());
				final Timestamp fechaFin = new Timestamp(new SimpleDateFormat("dd/MM/yyyy").parse("01/01/1900").getTime());
				assertFalse(MEXMEEquipo.getAvailable(Env.getCtx(), obj2.getEXME_Area_ID(), fechaini, fechaFin, null).isEmpty());
			}
		} catch (Exception e) {
			fail(e.toString());
		}
	}

}
