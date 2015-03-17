/**
 *
 */
package com.ecaresoft.tests;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.Test;

import com.ecaresoft.util.CURP_RFC;

/**
 * @author mrojas
 *
 */
public class TestCurpAndRfc {

	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

	/**
	 * Test method for {@link com.ecaresoft.util.CURP_RFC#getCURP()}.
	 */
	@Test
	public void testGetCURP_1() {
		CURP_RFC curpRfc = new CURP_RFC();
		curpRfc.setNombre("Miguel");
		curpRfc.setSegundoNombre("Ángel");
		curpRfc.setApellidoPaterno("Rojas");
		curpRfc.setApellidoMaterno("Aquino");
		curpRfc.setEstadoNac("Veracruz");
		curpRfc.setSexo("M");
		try {
			curpRfc.setFechaNac(sdf.parse("19730127"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println(curpRfc.getCURP());

		assertTrue("ROAM730127HVZJQG".equals(curpRfc.getCURP()));
	}

	/**
	 * Test method for {@link com.ecaresoft.util.CURP_RFC#getCURP()}.
	 */
	@Test
	public void testGetCURP_2() {
		CURP_RFC curpRfc = new CURP_RFC();
		curpRfc.setNombre("Asael");
		curpRfc.setApellidoPaterno("Muñoz");
		curpRfc.setApellidoMaterno("Muñoz");
		curpRfc.setEstadoNac("Nuevo Leon");
		curpRfc.setSexo("M");
		try {
			curpRfc.setFechaNac(sdf.parse("19800702"));
		} catch (ParseException e) {
			e.printStackTrace();
		}

		System.out.println(curpRfc.getCURP());

		assertTrue("MUMA800702HNLXXS".equals(curpRfc.getCURP()));
	}


	@Test
	public void testGetCURP_5() {
		CURP_RFC curpRfc = new CURP_RFC();
		curpRfc.setNombre("lourdes");
		curpRfc.setApellidoPaterno("orozco");
		curpRfc.setApellidoMaterno("posadas");
		curpRfc.setEstadoNac("Nuevo Leon");
		curpRfc.setSexo("F");
		try {
			curpRfc.setFechaNac(sdf.parse("19761123"));
		} catch (ParseException e) {
			e.printStackTrace();
		}

		System.out.println(curpRfc.getCURP());

		assertTrue("OOPL761123MNLRSR".equals(curpRfc.getCURP()));
	}

	@Test
	public void testGetCURP_4() {
		CURP_RFC curpRfc = new CURP_RFC();
		curpRfc.setNombre("SABINO");
		curpRfc.setApellidoPaterno("GOMEZ");
		curpRfc.setApellidoMaterno("PEÑA");
		curpRfc.setEstadoNac("TAMAULIPAS");
		curpRfc.setSexo("M");
		try {
			curpRfc.setFechaNac(sdf.parse("19321229"));
		} catch (ParseException e) {
			e.printStackTrace();
		}

		System.out.println(curpRfc.getCURP());
		assertTrue("GOPS321229HTSMXB".equals(curpRfc.getCURP()));
	}


	@Test
	public void testGetCURP_6() {
		CURP_RFC curpRfc = new CURP_RFC();
		curpRfc.setNombre("AMALIA");
		curpRfc.setApellidoPaterno("REYES");
		curpRfc.setApellidoMaterno("BAUTISTA");
		curpRfc.setEstadoNac("distrito federal");
		curpRfc.setSexo("F");
		try {
			curpRfc.setFechaNac(sdf.parse("20131001"));
		} catch (ParseException e) {
			e.printStackTrace();
		}

		System.out.println(curpRfc.getCURP());

		assertTrue("REBA131001MDFYTM".equals(curpRfc.getCURP()));
	}


	@Test
	public void testGetCURP_7() {
		CURP_RFC curpRfc = new CURP_RFC();
		curpRfc.setNombre("MARIA");
		curpRfc.setSegundoNombre("ISABEL");
		curpRfc.setApellidoPaterno("CARRANZA");
		curpRfc.setApellidoMaterno("BAEZ");
		curpRfc.setEstadoNac("TAMAULIPAS");
		curpRfc.setSexo("F");
		try {
			curpRfc.setFechaNac(sdf.parse("19760329"));
		} catch (ParseException e) {
			e.printStackTrace();
		}

		System.out.println(curpRfc.getCURP());

		assertTrue("CABM760329MTSRZS".equals(curpRfc.getCURP()));
	}


	@Test
	public void testGetCURP_8() {
		CURP_RFC curpRfc = new CURP_RFC();
		curpRfc.setNombre("EDUARDO");
		curpRfc.setApellidoPaterno("VALERO");
		curpRfc.setApellidoMaterno("VILLARREAL");
		curpRfc.setEstadoNac("COAHUILA");
		curpRfc.setSexo("M");
		try {
			curpRfc.setFechaNac(sdf.parse("19381102"));
		} catch (ParseException e) {
			e.printStackTrace();
		}

		System.out.println(curpRfc.getCURP());

		assertTrue("VAVE381102HCLLLD".equals(curpRfc.getCURP()));
	}


	@Test
	public void testGetCURP_9() {
		CURP_RFC curpRfc = new CURP_RFC();
		curpRfc.setNombre("Jean");
		curpRfc.setSegundoNombre("Paul");
		curpRfc.setApellidoPaterno("Rèverté");
		curpRfc.setApellidoMaterno("Ramírez");
		curpRfc.setEstadoNac("Nuevo Leon");
		curpRfc.setSexo("M");
		try {
			curpRfc.setFechaNac(sdf.parse("19800702"));
		} catch (ParseException e) {
			e.printStackTrace();
		}

		System.out.println(curpRfc.getCURP());

		assertTrue("RERJ800702HNLVMN".equals(curpRfc.getCURP()));
	}


	@Test
	public void testGetCURP_10() {
		CURP_RFC curpRfc = new CURP_RFC();
		curpRfc.setNombre("TALINA");
		curpRfc.setApellidoPaterno("RAMIREZ");
		curpRfc.setApellidoMaterno("AVIÑA");
		curpRfc.setEstadoNac("MEXICO");
		curpRfc.setSexo("F");
		try {
			curpRfc.setFechaNac(sdf.parse("19741107"));
		} catch (ParseException e) {
			e.printStackTrace();
		}

		System.out.println(curpRfc.getCURP());

		assertTrue("RAAT741107MMCMVL".equals(curpRfc.getCURP()));
	}


	@Test
	public void testGetCURP_11() {
		CURP_RFC curpRfc = new CURP_RFC();
		curpRfc.setNombre("WENDY");
		curpRfc.setSegundoNombre("SAMARY");
		curpRfc.setApellidoPaterno("TRUJILLO");
		curpRfc.setApellidoMaterno("CUAUHTECATL");
		curpRfc.setEstadoNac("MEXICO");
		curpRfc.setSexo("F");
		try {
			curpRfc.setFechaNac(sdf.parse("20020818"));
		} catch (ParseException e) {
			e.printStackTrace();
		}

		System.out.println(curpRfc.getCURP());

		assertTrue("TUCW020818MMCRHN".equals(curpRfc.getCURP()));
	}

}
