package org.compiere.process;

import org.compiere.model.MTable;
import org.compiere.util.CLogger;
import org.compiere.util.ECareConnectorProperties;
import org.compiere.util.confHL7.MirthClient;

import com.mirth.connect.client.core.Client;
import com.mirth.connect.client.core.ClientException;
import com.mirth.connect.util.ConnectionTestResponse;

public class TestWrite extends SvrProcess {

	private static CLogger log = CLogger.getCLogger(TestWrite.class);

	private Client mirthClient;

	private ECareConnectorProperties destinatario;

	@Override
	protected String doIt() throws Exception {
		
		 try {
             ConnectionTestResponse response = (ConnectionTestResponse) mirthClient.invokeConnectorService(destinatario.getTransportName(), "testWrite", destinatario.getProperties());
             
             
             if (response == null) {
                 throw new ClientException("Failed to invoke service.");
             } else if(response.getType().equals(ConnectionTestResponse.Type.SUCCESS)) {
            	 log.info(response.getMessage());    	 
            	 addLog(0, null, null, response.getMessage());            	             	 
                 return response.getMessage();
                 
             } else { 
            	 log.info(response.getMessage());
            	 addLog(0, null, null, response.getMessage());            	 
                 return response.getMessage();
                 
             }
            
             
         } catch (ClientException e) {
             
        	 addLog(0, null, null, e.getMessage());        	 
             return e.getMessage(); 
         }
         
	}

	@Override
	protected void prepare() {
		
		MTable table = MTable.get(getCtx(), getTable_ID());
		
		log.info("Teting write for connector: "+table.getTableName());
		
		destinatario = (ECareConnectorProperties)table.getPO(getRecord_ID(), get_TrxName());

		try {
			mirthClient = MirthClient.getClient(getCtx());

		} catch (ClientException e) {
			log.severe(e.getMessage());
			e.printStackTrace();
		}



	}

}
