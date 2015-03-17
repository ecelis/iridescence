package org.compiere.factext;

import java.util.HashMap;

public class RegCtaPacDet extends Registro{
	
	/**
	 * Construye un registro con los datos de la Hashtable ht
	 * @param ht
	 */
	RegCtaPacDet(HashMap<String, Object> ht){
		ht_Registro = ht;
	}
	
	/**
	 * Construye un registro en blanco 
	 */
	public RegCtaPacDet(){	
		//registroBlanco(0);
	}
	
	/**
	 * Crea un registro con los los valores default
	 * @param prenominaD_ID Secuencia ID generada
	 * @param federal "Y" si la nomina es federal, "N" si es estatal
	 * @param ordinaria "Y" si la nomina es ordinaria, "N" si es extraordinaria
	 */
	public void registroBlanco(int ctaPacDet_ID){

		ht_Registro.put("Exme_Paciente_Id", null);
		ht_Registro.put("c_Bpartner_Id", null);
		ht_Registro.put("Exme_Ctapac_Id", null);
		ht_Registro.put("Exme_Cama_Id", null);
		ht_Registro.put("Exme_Habitacion_Id", null);
		ht_Registro.put("EXME_CtaPacDet_id", null);
		ht_Registro.put("m_product_id", null);
		ht_Registro.put("c_uom_id", null);
		ht_Registro.put("qtydelivered", null);
		ht_Registro.put("qtyinvoiced", null);
		ht_Registro.put("qtypendiente", null);
		ht_Registro.put("qtypaquete", null);
		ht_Registro.put("exme_ctapacext_id", null);
		ht_Registro.put("exme_paqbase_version_id", null);
		
	}
	
	
}
