/**
 * 
 */
package com.ecaresoft.tests;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import junit.framework.TestCase;

import org.compiere.model.MEXMEEquipo;
import org.compiere.model.MEXMEEquipoH;
import org.compiere.util.Env;
import org.junit.Test;

import com.ecaresoft.tests.utils.TestUtils;

/**
 * @author Lorena Lama
 *
 */
public class MEXMEEquipoHTest extends TestCase {

	/**@see junit.framework.TestCase#setUp() */
	protected void setUp() throws Exception {
		TestUtils.setUpLake();
	}

//	/**
//	 * Test method for {@link org.compiere.model.MEXMEEquipoH#equiposDisponibles(java.util.Properties, java.lang.String, java.lang.String, java.util.List, java.lang.String)}.
//	 */
//	@Test
//	public void testEquiposDisponibles() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MEXMEEquipoH#getLstEquipos(java.util.Properties, java.util.List, java.lang.String)}.
//	 */
//	@Test
//	public void testGetLstEquipos() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MEXMEEquipoH#getEquiposH(java.util.Properties, int, java.lang.String)}.
//	 */
//	@Test
//	public void testGetEquiposH() {
//		fail("Not yet implemented"); // TODO
//	}
	
	/**
	 * Test method for {@link org.compiere.model.MEXMEEquipoH#get(java.util.Properties, int, int, java.util.Date, java.util.Date, boolean, java.lang.String)}.
	 */
	@Test
	public void testGetPropertiesIntIntDateDateBooleanString() {
		// vacio si es nuevo
		assertTrue(MEXMEEquipoH.get(Env.getCtx(), 0, 0, null, null, false, null).isEmpty());
		// random
		final MEXMEEquipoH obj = TestUtils.getRandomPO(MEXMEEquipoH.class);
		if (obj == null) {
			final MEXMEEquipo obj2 = TestUtils.getRandomPO(MEXMEEquipo.class);
			if (obj2 != null) {
				// si no hay registros en equipoH, no debe traer datos
				try {
					final Timestamp fechaini = new Timestamp(new SimpleDateFormat("dd/MM/yyyy").parse("01/01/1900").getTime());
					final Timestamp fechaFin = new Timestamp(new SimpleDateFormat("dd/MM/yyyy").parse("31/12/3000").getTime());
					assertTrue(MEXMEEquipoH.get(Env.getCtx(), 0, 0, fechaini, fechaFin, false, null).isEmpty());
				} catch (Exception e) {
					fail(e.toString());
				}
			}
		} else {
			// para un equipo
			assertFalse(MEXMEEquipoH.get(Env.getCtx(), obj.getEXME_Equipo_ID(), 0, obj.getFecha_Ini(), obj.getFecha_Fin(),
				MEXMEEquipoH.ESTATUS_EQUIPO_Cancelled.equals(obj.getEstatus_Equipo()), null).isEmpty());
			// para el area
			assertFalse(MEXMEEquipoH.get(Env.getCtx(), 0, obj.getEXME_Area_ID(), obj.getFecha_Ini(), obj.getFecha_Fin(),
				MEXMEEquipoH.ESTATUS_EQUIPO_Cancelled.equals(obj.getEstatus_Equipo()), null).isEmpty());
		}
	}

//	/**
//	 * Test method for {@link org.compiere.model.MEXMEEquipoH#isAvailable(java.util.Properties, int, int, java.util.Date, java.util.Date, java.lang.String)}.
//	 */
//	@Test
//	public void testIsAvailable() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MEXMEEquipoH#hasComments()}.
//	 */
//	@Test
//	public void testHasComments() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link org.compiere.model.MEXMEEquipoH#getComments()}.
//	 */
//	@Test
//	public void testGetComments() {
//		fail("Not yet implemented"); // TODO
//	}

}
