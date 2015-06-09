package org.compiere.util.confHL7;

import java.util.Hashtable;
import java.util.List;

import com.mirth.connect.client.core.Client;
import com.mirth.connect.client.core.ClientException;
import com.mirth.connect.model.Channel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* TODO Check if should be part of Iridiscence 
import org.compiere.model.MHL7MessageConf; */
/* TODO Sems to be UI code... remove? 
 * import org.compiere.model.X_HL7_Dashboard;
 */
/* TODO It should be part of the YAML module
 * import org.compiere.model.X_HL7_MessageConf;
 */
/* TODO remove
import org.compiere.util.CLogger;
import org.compiere.util.Env;
import org.compiere.util.SecureEngine;
*/
// TODO Missing in 3.2 import com.mirth.connect.model.MessageObject;

public class MessageSender {

	final Logger logger = LoggerFactory.getLogger(getClass());
	
	private Client mirthClient;
	private Hashtable<String, Channel> channelMap = 
			new Hashtable<String, Channel>();

	public MessageSender(Client mirthClient) throws ClientException {
		this.mirthClient = mirthClient;
		
		channelMap = loadChannel();
	}

	/* TODO Seems to be UI code... remove?
	public boolean sendMessage(X_HL7_Dashboard panel) {
		boolean res = Boolean.FALSE;
		
		if (panel.getHL7_Dashboard_ID() == 0 || panel.getStatus().equals("E")) {
			return res;
		}

		try {

			MHL7MessageConf conf = new MHL7MessageConf(Env.getCtx(), panel.getHL7_MessageConf_ID(), null);

			Channel channel = conf.getChannel(mirthClient, "out");

			MessageObject messageObject = new MessageObject();
			messageObject.setId(mirthClient.getGuid());
			messageObject.setServerId(mirthClient.getServerId());
			messageObject.setChannelId(channel.getId());

			messageObject.setRawDataProtocol(channel.getSourceConnector()
					.getTransformer().getInboundProtocol());
			messageObject.setDateCreated(Calendar.getInstance());
			messageObject.setConnectorName("Source");

			messageObject.setEncrypted(Boolean.valueOf(
					channel.getProperties().getProperty(
							ChannelProperties.ENCRYPT_DATA)).booleanValue());

			String msg = panel.getMessage();
			
			if (msg != null) {
				
				
				//TODO remove literak strings from evaluation
				if (conf.getProtocol().contains("CDA") || conf.getProtocol().contains("CCR") || conf.getProtocol().contains("XML"))
					messageObject.setRawData(panel.getMessage());
				else
					messageObject.setRawData(decryptMessage(panel.getMessage(), conf));

				mirthClient.processMessage(messageObject);
				
				panel.setStatus(X_HL7_Dashboard.STATUS_SentWaitingForAknowledgment);
				panel.save();
				res = Boolean.TRUE;
			}

		} catch (Exception e) {
			log.log(Level.SEVERE, "Error: Exception while sending HL7 message", e); 
		}
		return res;
	}
	*/

	/* TODO Should be in the YAML module
	private String decryptMessage(String encryptedMessage, MHL7MessageConf conf) {

		StringBuilder decryptedMessage = new StringBuilder("");

		StringTokenizer t = null;
		if (X_HL7_MessageConf.CONFTYPE_PIStatement.equals(conf.getConfType())) {
			String sep = conf.getField_Sep().concat(" ");
			t = new StringTokenizer(encryptedMessage, sep, true);
		} else {
			// se agrega espacio (abautista)
			t = new StringTokenizer(encryptedMessage,
								    encryptedMessage.substring(3, 4) + "\n\r^&~ ", true);
		}

		while (t.hasMoreTokens()) {
			
			String value = t.nextToken();
			
			if (StringUtils.isNotBlank(value)){
				decryptedMessage.append(SecureEngine.decrypt(value));
			}else{
				decryptedMessage.append(value);
			}
			
		}
		// System.out.println(msg.toString());

		return decryptedMessage.toString();

	}
*/
	private Hashtable<String, Channel> loadChannel() throws ClientException {

		if (mirthClient != null) {//Lama .- evitar NullPointerException
			// TODO Check why null as parameter
			List<Channel> channels = mirthClient.getChannels(null);

			for (Channel channel : channels) {
				channelMap.put(channel.getId(), channel);
			}
		}
		return channelMap;
	}

}
