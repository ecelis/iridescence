/**
 * 
 */
package org.compiere.process;

import org.compiere.model.MTable;
import org.compiere.model.X_C_Invoice;
import org.compiere.model.X_HL7_MessageConf;
import org.compiere.util.confHL7.MessageGenerator;

/**
 * @author Expert 03/11/2010
 * 
 */
public class BatchMessageGeneratorProc extends SvrProcess {
	
	
	private String cadenaIds;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.compiere.process.SvrProcess#doIt()
	 */
	@Override
	protected String doIt() throws Exception {
		
		
		MessageGenerator generator = new MessageGenerator(getCtx(), true);
		
		generator.generateMessage(cadenaIds, X_C_Invoice.Table_Name, X_HL7_MessageConf.CONFTYPE_Elegibility, null);
		
		
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.compiere.process.SvrProcess#prepare()
	 */
	@Override
	protected void prepare() {
		
		

		int[] todosLosIds = MTable.getAllIDs(X_C_Invoice.Table_Name,
				" isGenerated  = 'N' ", null);
		
		cadenaIds = formatIds(todosLosIds);
		

	}
	
	private String formatIds(int[] todosLosIds){
		
		StringBuilder cadenaIds = new StringBuilder("");
		
		for(int i =0;i < todosLosIds.length; /*se incrementa en la logica interna del for*/){
			//se obtiene el valor del arreglo
			cadenaIds.append(String.valueOf(todosLosIds[i++]));
			
			if(i!=todosLosIds.length){
				cadenaIds.append(",");
			}
		}
		
		return cadenaIds.toString();
	}

}
