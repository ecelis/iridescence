package com.ecaresoft.tests;

import java.util.ArrayList;
import java.util.Properties;

import junit.framework.TestCase;

import org.compiere.model.MEXMECitaMedica;
import org.compiere.model.MEXMEConfigOV;
import org.compiere.model.MEXMEMedico;
import org.compiere.util.Env;
import org.junit.Before;
import org.junit.Test;

import com.ecaresoft.tests.utils.TestUtils;

public class MEXMEConfigOVTest  extends TestCase {

	@Before
	public void setUp() throws Exception {
		TestUtils.setUp();
	}
	
	/**
	 * Prueba la funcionalidad de {@link MEXMEConfigOV#find(Properties ctx, int exmeMedicoID, String trxName)}
	 */
	@Test
	public void testfind() {
		//Medico: Almas Yousuf, MD ID:10001000
		MEXMEMedico medico = new MEXMEMedico(Env.getCtx(), 10001000, null);
		assertTrue((medico != null && medico.getEXME_Medico_ID() > 0) ? MEXMEConfigOV.find(Env.getCtx(), medico.getEXME_Medico_ID(), null) != null : true);		
	}
	
	/**
	 * Prueba la funcionalidad de {@link MEXMEConfigOV#findActPac(Properties ctx, int actPac, String trxName)}
	 */
	@Test
	public void testfindActPac() {
		//ID 45273 Actividad Paciente realizada por el medico: Almas Yousuf, MD ID:10001000		
		assertTrue(MEXMEConfigOV.findActPac(Env.getCtx(), 45273, null) != null);		
	}
		
	/**
	 * Prueba la funcionalidad de {@link MEXMEConfigOV#findRepetitions(Properties ctx, ArrayList<Integer> ids, String trxName)}
	 */
	@Test
	public void testfindRepetitions() {
		//IDs de grupo de cuestionarios con cuestionarios repetidos 10001017, 10001005
		ArrayList<Integer> ids = new ArrayList<Integer>();
		ids.add(0, 10001017);
		ids.add(1, 10001005);
		ids.add(2, 0);
		ids.add(3, 0);
		ids.add(4, 0);
		ids.add(5, 0);
		ids.add(6, 0);
		ids.add(7, 0);
		ids.add(8, 0);
		ids.add(9, 0);
		ids.add(10, 0);
		ids.add(11, 0);
		ids.add(12, 0);
		ids.add(13, 0);
		ids.add(14, 0);
		ids.add(15, 0);
		
		assertTrue(MEXMEConfigOV.findRepetitions(Env.getCtx(), ids, null));		
	}

}
