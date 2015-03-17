package com.ecaresoft.tests;

import java.sql.Timestamp;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import junit.framework.TestCase;

import org.compiere.model.MEXMECuestionario;
import org.compiere.model.MEXMEFormSectionConf;
import org.compiere.model.MEXMEFormSectionHeader;
import org.compiere.model.MEXMEPrintedFormatHdr;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Trx;
import org.junit.Before;
import org.junit.Test;

import com.ecaresoft.tests.utils.TestUtils;

public class MEXMEPrintFormatTest extends TestCase {

	private Properties ctx = Env.getCtx();
	CLogger slog = CLogger.getCLogger(MEXMEPrintFormatTest.class);

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		TestUtils.setUp();
	}

	/**
	 * Metodo que prueba que pueda crearse un formato con los valores minimos
	 */
	@Test
	public void testSaveFormat() {
		Trx trx = null;
		try {
			trx = Trx.get(Trx.createTrxName("FO"), true);

			MEXMEFormSectionHeader header = new MEXMEFormSectionHeader(ctx, 0, null);

			header.setName("Test");
			header.setType(MEXMEFormSectionHeader.TYPE_Format);
			header.setJasperReport("test.jasper");

			if (header.save(trx.getTrxName())) {
				int id = DB.getSQLValue(null, "Select AD_Form_ID from ad_form where classname = 'com.ecaresoft.web.forms.form.QuestionnaireWindow'");

				MEXMEFormSectionConf conf = new MEXMEFormSectionConf(ctx, 0, null);

				conf.setName("test");
				conf.setTitle("test");
				conf.setSequence(0);
				conf.setEXME_FormSectionHeader_ID(header.getEXME_FormSectionHeader_ID());
				conf.setAD_FormChild_ID(id);
				conf.setEXME_Cuestionario_ID(TestUtils.getRandomPO(MEXMECuestionario.class).getEXME_Cuestionario_ID());

				if (!conf.save(trx.getTrxName())) {
					assertNotNull(null);
				}
			} else {
				assertNotNull(null);
			}
		} catch (Exception ex) {
			slog.log(Level.SEVERE, null, ex);
		} finally {
			Trx.rollback(trx);
			Trx.close(trx);
		}
	}

	/**
	 * Método de prueba de búsqueda
	 */
	@Test
	public void testSearchFormat() {
		MEXMEPrintedFormatHdr header = TestUtils.getRandomPO(MEXMEPrintedFormatHdr.class);

		if (header != null) {
			int pacId = header.getEXME_Paciente_ID();
			Timestamp date = header.getCreated();

			List<MEXMEPrintedFormatHdr> headers = MEXMEPrintedFormatHdr.get(ctx, pacId, date, date, null);

			assertNotNull(headers);

			assertTrue(!headers.isEmpty());
		}
	}

}
