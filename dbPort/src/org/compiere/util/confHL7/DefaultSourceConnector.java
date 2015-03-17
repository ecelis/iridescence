package org.compiere.util.confHL7;

import java.util.Properties;

import org.compiere.model.MHL7BPartner;
import org.compiere.model.MHL7MessageConf;
import org.compiere.util.ECareConnectorProperties;

import com.mirth.connect.connectors.vm.ChannelReaderProperties;
import com.mirth.connect.model.Connector;
import com.mirth.connect.model.Filter;
import com.mirth.connect.model.Transformer;

/** */
public class DefaultSourceConnector {

	// private final static String SQL =
	// "SELECT MESSAGE AS mensaje FROM HL7_DASHBOARD WHERE STATUS = 'N' ";

	private DefaultSourceConnector() {

	}

	public static Connector getDefaultSourceConnector(boolean isResponse,  Properties ctx, int partnerID, String trxName, MHL7MessageConf conf) {
		

		Connector conn = new Connector();
		conn.setMode(Connector.Mode.SOURCE);
		conn.setName("Default Source Connector");
		conn.setFilter(new Filter());
		conn.setEnabled(true);
		
		
		
		if(isResponse){
			
			ECareConnectorProperties source = MHL7BPartner.getConnectorProperties(ctx, partnerID, trxName, isResponse);
			
			conn.setProperties(source.getProperties());
			conn.setTransportName(source.getTransportName());
			Transformer optimus = DefaultSourceTransfromer
			.getDefaultSourceTransfromer();
			optimus.setInboundProtocol(conf.getMessageProtocol());
			optimus.setOutboundProtocol(conf.getMessageProtocol());
			optimus.setSteps(conf.getSteps());
			conn.setTransformer(optimus);
			
			
		}else{
			Transformer optimus = DefaultSourceTransfromer
			.getDefaultSourceTransfromer();
			optimus.setInboundProtocol(conf.getMessageProtocol());
			optimus.setOutboundProtocol(conf.getMessageProtocol());

		conn.setTransformer(optimus);
		conn.setProperties(getDefaultProperties());
		conn.setTransportName(ChannelReaderProperties.name);
		
		}
		return conn;

	}

	/**
	 * al estar recieviendo los mensajes de fomra directa atravez del Cliente de
	 * mirth se deja una configuracion por default dummy
	 */
	private static Properties getDefaultProperties() {

		Properties p = new ChannelReaderProperties().getDefaults();

		return p;

	}

}
