import org.apache.commons.lang.StringUtils;
import org.compiere.model.MElement;
import org.compiere.model.MTree;
import org.compiere.model.X_AD_Tree;
import org.compiere.model.X_C_AcctSchema_Default;
import org.compiere.model.X_C_Element;
import org.compiere.model.X_I_ElementValue;
import org.compiere.process.ImportElecAccount;
import org.compiere.process.SvrProcess;
import org.compiere.util.CLogMgt;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.junit.Before;
import org.junit.Test;

import com.ecaresoft.tests.utils.TestUtils;

import junit.framework.TestCase;

/**
 * 
 */

/**
 * @author twrytania
 * Configurar que el cliente utilizará contabilidad electrónica, y por lo tanto deberá importar/exigir las cuentas predeterminadas propias del proceso
 * Configurar que el cliente no utilizará contabilidad electrónica, y por lo tanto no deberá exigir las cuentas predeterminadas propias del proceso
 * Importar el catalogo de cuentas de 3to nivel, debe decir que son de 3er nivel y que no puede importarlo 
 * Importar el catalogo de cuentas de 4to nivel con agrupadores con predeterminadas y con cuentas padres de 3er nivel, no debe marcar error
 * Importar el catalogo de cuentas de 4to nivel con agrupadores sin predeterminadas y con cuentas padres de 3er nivel, no debe marcar error 
 * Importar el catalogo de cuentas de 4to nivel sin agrupadores sin predeterminadas y con cuentas padres de 3er nivel, debe marcar error que faltan agrupadores
 * Importar el catalogo de cuentas de 4to nivel con agrupadores sin predeterminadas y con cuentas padres de 3er nivel no existentes, debe marcar error por no existir las cuentas. padres
 *
 */
public class ImportElecAccountTest extends TestCase {

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	@Before
	protected void setUp() throws Exception {
		TestUtils.setConnectionBD("ecaresoftdevmx", "26", 10001032); 
	}
	
	public int testLlenarTablaDeTest() {
		int id = 0;
		boolean record = true;
		int treeID = MTree.getDefaultAD_Tree_ID(Env.getAD_Client_ID(Env.getCtx()), "C_ElementValue_ID");
		
		final MTree tree = new MTree(Env.getCtx(), treeID, null);
		tree.setName("TEST");
		tree.setIsAllNodes(Boolean.TRUE);
		tree.setIsDefault(Boolean.FALSE);
		tree.setTreeType(X_AD_Tree.TREETYPE_ElementValue);
		if(tree.alreadyExists("Name")<=0)
			record = tree.save();
		
		final MElement mElement = new MElement(Env.getCtx(), 0, null);
		mElement.setName(tree.getName());
		mElement.setElementType(X_C_Element.ELEMENTTYPE_Account);
		mElement.setIsBalancing(false);
		mElement.setIsNaturalAccount(true);
		mElement.setAD_Tree_ID(tree.getAD_Tree_ID());
		id = mElement.alreadyExists("Name");
		if(id<=0)
			if(mElement.save())
				id = mElement.getC_Element_ID();
//		MAcctSchema[] dd = MAcctSchema.getClientAcctSchema(Env.getCtx(), Env.getAD_Client_ID(Env.getCtx()), null);
		
		 /** Importar el catalogo de cuentas de 3to nivel, 
		  * debe decir que son de 3er nivel y que no puede importarlo 
		  */
		X_I_ElementValue data = new X_I_ElementValue(Env.getCtx(), 0, null);
		data.setValue ("TEST01CTA3ERNIVEL_Value");
		data.setName ("TEST01CTA3ERNIVEL_Name");
		data.setDescription ("TEST01CTA3ERNIVEL_Description");
		data.setParentValue ("801");//10015509
		record = data.save();
	
		/** Importar el catalogo de cuentas de 4to nivel 
		  * con agrupadores con predeterminadas y con cuentas padres de 3er nivel, 
		  * no debe marcar error
		  */
		data = new X_I_ElementValue(Env.getCtx(), 0, null);
		data.setValue ("TEST02CTA4toNIVEL_Value");
		data.setName ("TEST02CTA4toNIVEL_Name");
		data.setDescription ("TEST02CTA4toNIVEL_Description");
		data.setParentValue ("80101");//10015510
//		data.setGroupAcct_Value();
		data.setDefault_Account(X_C_AcctSchema_Default.COLUMNNAME_B_AssetFgn_Acct);
		record = data.save();
		 /** Importar el catalogo de cuentas de 4to nivel 
		  * con agrupadores sin predeterminadas y con cuentas padres de 3er nivel, 
		  * no debe marcar error 
		  */
		data = new X_I_ElementValue(Env.getCtx(), 0, null);
		data.setValue ("TEST03CTA4toNIVEL_Value");
		data.setName ("TEST03CTA4toNIVEL_Name");
		data.setDescription ("TEST03CTA4toNIVEL_Description");
		data.setParentValue ("10101");//"EFECTIVO": 10015093
//		data.setGroupAcct_Value();
		record = data.save();
		 /** Importar el catalogo de cuentas de 4to nivel 
		  * sin agrupadores sin predeterminadas y con cuentas padres de 3er nivel
		  * , debe marcar error que faltan agrupadores
		  */
		data = new X_I_ElementValue(Env.getCtx(), 0, null);
		data.setValue ("TEST04CTA4toNIVEL_Value");
		data.setName ("TEST04CTA4toNIVEL_Name");
		data.setDescription ("TEST04CTA4toNIVEL_Description");
		data.setParentValue ("10102");//"BANCOS": 10015098
		record = data.save();
		 /** Importar el catalogo de cuentas de 4to nivel 
		  * con agrupadores sin predeterminadas y con cuentas padres de 3er nivel no existentes
		  * , debe marcar error por no existir las cuentas. padres
		  */
		data = new X_I_ElementValue(Env.getCtx(), 0, null);
		data.setValue ("TEST05CTA4toNIVEL_Value");
		data.setName ("TEST05CTA4toNIVEL_Name");
		data.setDescription ("TEST05CTA4toNIVEL_Description");
		data.setParentValue ("10107");//"INVENTARIOS":10015133
//		data.setGroupAcct_Value();
		record = data.save();
		return id;
	}

	
	@Test
	public void test() {
		int id = testLlenarTablaDeTest();
		if(id>0) {

			final ImportElecAccount m = new ImportElecAccount();
			m.setParamsTest(Env.getCtx(), Env.getAD_Client_ID(Env.getCtx())
					, id
					, true
					, true
					, true);

			String msg = null;
			boolean success = true;
			try {
				msg = m.doItTest();

			} catch (Throwable e) {
				msg = e.getLocalizedMessage();
				if (msg == null)
					msg = e.toString();
				if (e.getCause() != null)
					System.out.println(msg +" "+ e.getCause());
				else if (CLogMgt.isLevelFine())
					System.out.println(msg +" "+ e);
				else
					System.out.println(msg +" "+ e);
				success = false;
				//	throw new RuntimeException(e);
			}

			//transaction should rollback if there are error in process
			//Se cambia para que funcionen los mensajes diferentes a @ERROR@ - Mensaje de Error.
			//Jesus Cantu
			if (StringUtils.startsWithIgnoreCase(msg, SvrProcess.ERROR)){
				success = false;
			}

			//	Parse Variables
			msg = Msg.parseTranslation(Env.getCtx(), msg);
			System.out.println(msg);
			/*if(result!=null){
				m_pi.setSummary (msg, !success, result);
			} else {*/
			//RQM 5719 arangel
			//Cambiar el mensaje cuando es un error o no hay nada en summary
			//			if(StringUtils.isBlank(m_pi.getSummary()) || !success){
			//				m_pi.setSummary (msg, !success);
			//			}
			assertTrue(success);
		} else {
			assertTrue(false);
		}
	}
}
