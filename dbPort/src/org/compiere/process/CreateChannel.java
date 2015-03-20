package org.compiere.process;

import java.util.Arrays;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.confHL7.DefaultAckManager;
import org.compiere.util.confHL7.MirthClient;

import com.mirth.connect.client.core.Client;
import com.mirth.connect.client.core.ClientException;
import com.mirth.connect.model.Channel;

public class CreateChannel extends SvrProcess {
	
	private static CLogger log = CLogger.getCLogger(CreateChannel.class);

	private Client mirthClient = null;
	
	String processMessage = null;

	public CreateChannel() {
	}

	@Override
	protected String doIt() {
		boolean created =false;
		Channel channel = null;

		try {
			if (mirthClient == null){
				return processMessage;
			}			
			
			DefaultAckManager manager = new DefaultAckManager(mirthClient);
			
			channel = manager.get();		
			
			created = mirthClient.updateChannel(channel, true);
			mirthClient.deployChannels(Arrays.asList(channel.getId()));
			
			
			mirthClient.logout();
			
		} catch (ClientException e) {
			log.log(Level.SEVERE, e.getLocalizedMessage());
			return e.getLocalizedMessage();
		} 

		if(created){
			addLog(0, null, null, "Creado canal: "+channel.getName()+" "+channel.getId());
       	 	return "@Created@ :" +channel.getName()+" "+channel.getId();
			
		}
		
		return processMessage;
	}

	@Override
	protected void prepare() {

		try {
			mirthClient = MirthClient.getClient(getCtx());
		} catch (ClientException e) {
			log.log(Level.SEVERE, e.getLocalizedMessage());
			processMessage = e.getLocalizedMessage();
		}

	}

}
