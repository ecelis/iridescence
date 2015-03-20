package com.ecaresoft.tests;

import java.util.Date;
import java.util.List;

import junit.framework.TestCase;

import org.compiere.util.Env;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;

import com.ecaresoft.log.LogEntry;
import com.ecaresoft.log.LogUtils;
import com.ecaresoft.log.Table;
import com.ecaresoft.tests.utils.TestUtils;

/**
 * Pruebas del log
 * 
 * @author odelarosa
 * 
 */
public class LogTest extends TestCase {

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		TestUtils.setUp();
	}

	@Test
	public void testLog() {
		DateTime dateTime = new DateTime(new Date());

		// Revisamos si hay cambios en el ultimo año
		List<Table> tables = LogUtils.getTables(Env.getCtx(), false, null, dateTime.minusMonths(12).toDate(), dateTime.toDate());

		if (!tables.isEmpty()) {
			// Revisamos los registros de una tabla
			List<LogEntry> logEntries = LogUtils.getRecords(Env.getCtx(), tables.get(0), false, -1, dateTime.minusMonths(12).toDate(), dateTime.toDate());

			// Debe tener al menos un registro, si no, hay error
			assertTrue(!logEntries.isEmpty());
		}
	}

}
