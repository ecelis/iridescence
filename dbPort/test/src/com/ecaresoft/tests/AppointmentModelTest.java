/**
 * 
 */
package com.ecaresoft.tests;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import org.compiere.model.AppointmentModel;
import org.compiere.model.MEXMEActPaciente;
import org.compiere.model.MEXMECitaMedica;
import org.compiere.model.MEXMEProduct;
import org.compiere.model.MProduct;
import org.compiere.model.ServicioView;
import org.compiere.model.bpm.ServicesSave;
import org.compiere.util.Env;
import org.compiere.util.Trx;
import org.junit.Before;
import org.junit.Test;

import com.ecaresoft.tests.utils.TestUtils;

/**
 * @author vperez
 *
 */
public class AppointmentModelTest extends TestCase {

	@Before
	public void setUp() throws Exception {
		TestUtils.setUp();
	}
	
	/**
	 * Prueba de funcionalidad para guardar servicios metodos 
	 * {@link AppointmentModel#saveStudies(List, int, int, String)}
	 * {@link AppointmentModel#saveProcedures(List, int, int, String)} 
	 */
	@Test
	public void testSaveServicesOrders() {
		MEXMECitaMedica cita = TestUtils.getRandomPO(MEXMECitaMedica.class," ISACTIVE = 'Y' AND estatus NOT IN ('5','8') AND exme_ctapac_id > 0 ", true);		
		AppointmentModel model = new AppointmentModel(Env.getCtx(), cita);
		model.setServicesSave(new ServicesSave(Env.getCtx()));
		MProduct service = getService();
		
		MEXMEActPaciente actPac = MEXMEActPaciente.getActPacienteCitaMed(Env.getCtx(), cita.getEXME_CitaMedica_ID(), null, null);
		boolean saved = false;
		if (actPac != null && cita.getEXME_CtaPac_ID() > 0) {
			Trx trx = Trx.get(Trx.createTrxName("OD"), true);
			String trxName = trx.getTrxName();
			try {
				if (MEXMEProduct.PRODUCTCLASS_Laboratory.equals(service.getProductClass())
						|| MEXMEProduct.PRODUCTCLASS_XRay.equals(service.getProductClass())) {
					ServicioView serv = new ServicioView(Env.getCtx(), service);
					List<ServicioView> lst = new ArrayList<ServicioView>();
					lst.add(serv);
					model.saveStudies(lst, actPac.getEXME_ActPaciente_ID(),	cita.getEXME_CtaPac_ID(), trxName);
					saved = true;
				} else {
					ServicioView serv = new ServicioView(Env.getCtx(), service);
					List<ServicioView> lst = new ArrayList<ServicioView>();
					lst.add(serv);
					model.saveProcedure(lst, actPac.getEXME_ActPaciente_ID(), cita.getEXME_CtaPac_ID(), trxName);
				}
			} catch (Exception e) {
				saved = false;
			} finally {
				trx.rollback();
				Trx.close(trx);
				trx = null;
				trxName = null;
			}
		}
		assertTrue(saved);
	}
	
	/**
	 * Obtiene un servicio
	 * @return
	 */
	private MProduct getService() {
		StringBuilder where = new StringBuilder()
			.append(" M_PRODUCT.M_Product_ID in (SELECT PO.M_Product_ID FROM EXME_ProductoOrg PO WHERE PO.AD_ORG_ID = ")
			.append(Env.getAD_Org_ID(Env.getCtx())).append(")")
			.append(" AND (PRODUCTTYPE = '").append(MProduct.PRODUCTTYPE_Service).append("' OR PRODUCTTYPE = '").append(MProduct.PRODUCTTYPE_Package).append("') ")
			.append(" AND (ProductClass in ('")
			.append(MProduct.PRODUCTCLASS_Procedures).append("', '")
			.append(MProduct.PRODUCTCLASS_Cultures).append("', '")
			.append(MProduct.PRODUCTCLASS_Blood).append("', '")
			.append(MProduct.PRODUCTCLASS_Surgeries).append("', '")
			.append(MProduct.PRODUCTCLASS_Anesthesic).append("', '")
			.append(MProduct.PRODUCTCLASS_OtherService).append("', '")
			.append(MProduct.PRODUCTCLASS_Ambulance).append("', '")
			.append(MProduct.PRODUCTCLASS_HomeHealt).append("', '")
			.append(MProduct.PRODUCTCLASS_PhysicianServices).append("', '")
			.append(MProduct.PRODUCTCLASS_Others).append("', '")
			.append(MProduct.PRODUCTCLASS_Laboratory).append("', '")
			.append(MProduct.PRODUCTCLASS_XRay)
			.append("'))").append(" AND M_PRODUCT.AD_ORG_ID = ").append(Env.getAD_Org_ID(Env.getCtx()))
			.append(" AND (M_PRODUCT.PROCEDURETYPE is null or M_PRODUCT.PROCEDURETYPE <> 'DI')");
		
		return TestUtils.getRandomPO(MProduct.class, where.toString(), true);
	}
}