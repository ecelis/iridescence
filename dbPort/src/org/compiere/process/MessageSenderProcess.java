package org.compiere.process;

import java.util.logging.Level;

import org.compiere.model.X_HL7_Dashboard;
import org.compiere.util.Language;
import org.compiere.util.Msg;
import org.compiere.util.confHL7.MessageSender;
import org.compiere.util.confHL7.MirthClient;

import com.mirth.connect.client.core.ClientException;

public class MessageSenderProcess extends SvrProcess {
	
	private MessageSender sender;

	public MessageSenderProcess() {
	}

	@Override
	protected String doIt() throws Exception {
		// TODO Auto-generated method stub
		
		///sender
		X_HL7_Dashboard panel = new X_HL7_Dashboard(getCtx(), getRecord_ID(), get_TrxName());
				
		sender.sendMessage(panel);
		
		
		return null;
	}

	@Override
	protected void prepare() {
		
		try {
			sender = new MessageSender(MirthClient.getClient(getCtx()));
		} catch (ClientException e) {
			// 

			log.log(Level.SEVERE, Msg.getMsg(Language.getLoginLanguage(), "MirthError") + e.getMessage());
			e.printStackTrace();
		}
		
	
		

	}

}
