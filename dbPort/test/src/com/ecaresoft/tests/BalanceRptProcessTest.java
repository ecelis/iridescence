/**
 * 
 */
package com.ecaresoft.tests;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.compiere.model.MAcctSchemaElement;
import org.compiere.model.MElementValue;
import org.compiere.model.MPeriod;
import org.compiere.report.contabilidad.BalanceRptProcess;
import org.compiere.report.contabilidad.BalanceRptView;
import org.compiere.util.Env;
import org.junit.Before;
import org.junit.Test;

import com.ecaresoft.tests.utils.TestUtils;

/**
 * @author rsolorzano
 *
 */
public class BalanceRptProcessTest {

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		TestUtils.setUp();
	}

	/**
	 * Test method for {@link org.compiere.report.contabilidad.BalanceRptProcess#getAmts(java.util.Properties, org.compiere.report.contabilidad.BalanceRptView, int, java.lang.String)}.
	 */
	@Test
	public void testGetAmts() {
		
		MAcctSchemaElement schemaElement;
		int elementID = 0;
		try {
			schemaElement = MAcctSchemaElement.getAcctSchemaElementUserElement(
						Env.getCtx(), Env.getC_AcctSchema_ID(Env.getCtx()), MAcctSchemaElement.ELEMENTTYPE_Account, null);
			elementID = schemaElement.getC_Element_ID();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		List<MElementValue> lstElement = MElementValue.getLstbyElement(Env.getCtx(), elementID);
		BalanceRptView data = null;
		if (lstElement!=null && !lstElement.isEmpty()) {
			
			MElementValue elementValue = lstElement.get(0);
			data = new BalanceRptView();
			data.setElementValueID(elementValue.getC_ElementValue_ID());
			data.setValue(elementValue.getValue());
			data.setName(elementValue.getName());
			data.setParentID(elementValue.getParentElementValue_ID());
			data.setSummary(elementValue.isSummary());
			data.setProcessed(false); //indica que aun no se utiliza para las sumatorias de sus padres
		}
		
		MPeriod period = TestUtils.getRandomPO(MPeriod.class);
		
		if(data!=null){
			data = BalanceRptProcess.getAmts(Env.getCtx(), data, period.getPeriodNo(), period.getC_Year().getYear());
			assertNotNull(data);
		}
		
		
		
	}

}
