package org.compiere.model;

import static com.mirth.connect.connectors.mllp.LLPSenderProperties.CHANNEL_ID;
import static com.mirth.connect.connectors.mllp.LLPSenderProperties.CONNECTOR_CHARSET_ENCODING;
import static com.mirth.connect.connectors.mllp.LLPSenderProperties.LLP_ACK_TIMEOUT;
import static com.mirth.connect.connectors.mllp.LLPSenderProperties.LLP_ADDRESS;
import static com.mirth.connect.connectors.mllp.LLPSenderProperties.LLP_BUFFER_SIZE;
import static com.mirth.connect.connectors.mllp.LLPSenderProperties.LLP_CHAR_ENCODING;
import static com.mirth.connect.connectors.mllp.LLPSenderProperties.LLP_END_OF_MESSAGE_CHARACTER;
import static com.mirth.connect.connectors.mllp.LLPSenderProperties.LLP_HL7_ACK_RESPONSE;
import static com.mirth.connect.connectors.mllp.LLPSenderProperties.LLP_KEEP_CONNECTION_OPEN;
import static com.mirth.connect.connectors.mllp.LLPSenderProperties.LLP_MAX_RETRY_COUNT;
import static com.mirth.connect.connectors.mllp.LLPSenderProperties.LLP_PORT;
import static com.mirth.connect.connectors.mllp.LLPSenderProperties.LLP_PROTOCOL_NAME;
import static com.mirth.connect.connectors.mllp.LLPSenderProperties.LLP_PROTOCOL_NAME_VALUE;
import static com.mirth.connect.connectors.mllp.LLPSenderProperties.LLP_RECORD_SEPARATOR;
import static com.mirth.connect.connectors.mllp.LLPSenderProperties.LLP_SEGMENT_END;
import static com.mirth.connect.connectors.mllp.LLPSenderProperties.LLP_SERVER_TIMEOUT;
import static com.mirth.connect.connectors.mllp.LLPSenderProperties.LLP_START_OF_MESSAGE_CHARACTER;
import static com.mirth.connect.connectors.mllp.LLPSenderProperties.LLP_TEMPLATE;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.ECareConnectorProperties;
import org.compiere.util.confHL7.DefaultAckManager;
import org.compiere.util.confHL7.MirthClient;

import com.mirth.connect.client.core.ClientException;
import com.mirth.connect.connectors.mllp.LLPSenderProperties;
import com.mirth.connect.model.Channel;

public class MHL7BpartnerLLP extends X_HL7_BPartnerLLP implements
		ECareConnectorProperties {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6508430702533047339L;
	public static CLogger s_log = CLogger.getCLogger(MHL7BpartnerLLP.class);

	public MHL7BpartnerLLP(Properties ctx, int HL7_BPartnerLLP_ID,
			String trxName) {
		super(ctx, HL7_BPartnerLLP_ID, trxName);
	}

	public MHL7BpartnerLLP(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	public Properties getProperties() {
		Properties properties= new LLPSenderProperties().getDefaults();
		
	    properties.put(LLP_PROTOCOL_NAME, LLP_PROTOCOL_NAME_VALUE);
        properties.put(LLP_ADDRESS, getLLPAddress());
        properties.put(LLP_PORT, getLLPPort());
        properties.put(LLP_SERVER_TIMEOUT, getSendTimeout());
        properties.put(LLP_BUFFER_SIZE, getBufferSize());
        properties.put(LLP_KEEP_CONNECTION_OPEN, getKeepConnectionOpen());
        properties.put(LLP_MAX_RETRY_COUNT, getMaxRetryCount());
        properties.put(LLP_CHAR_ENCODING, getCharEncoding());
        properties.put(LLP_START_OF_MESSAGE_CHARACTER, getStartOfMessage());
        properties.put(LLP_END_OF_MESSAGE_CHARACTER, getEndOfMessage());
        properties.put(LLP_RECORD_SEPARATOR, getRecordSeparator());
        properties.put(LLP_SEGMENT_END, getSegmentEnd());
        properties.put(LLP_ACK_TIMEOUT, getACKTimeout());
        
        
        properties.put(LLP_HL7_ACK_RESPONSE, getACKResponse());
        
        if (getACKResponse().endsWith(MHL7BpartnerLLP.ACKRESPONSE_Yes)){
        	
        	Channel tmp = new Channel();
        	tmp.setName(DefaultAckManager.CHANNEL_NAME);
        	
        	try {
				List<Channel> channels =  MirthClient.getClient(getCtx()).getChannel(tmp);
				
				if (!channels.isEmpty()){
					properties.put(CHANNEL_ID, channels.get(0).getId());
				}
				
				
				
			} catch (ClientException e) {
				properties.put(CHANNEL_ID, "sink");
				
				e.printStackTrace();				
			}
        	
        	
        	
        	
        	
        }else{
        	properties.put(CHANNEL_ID, "sink");
            
        }
        	
        
        properties.put(CONNECTOR_CHARSET_ENCODING, getEncoding());
        properties.put(LLP_TEMPLATE, getTemplate());
        
		
		
		return null;
	}

	public String getTransportName() {
		return LLPSenderProperties.name;
	}

	public static MHL7BpartnerLLP getByHL7BPartner(Properties ctx, int HL7_BPartner_id, String trxName) {
		MHL7BpartnerLLP reg = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
			sql.append("SELECT * FROM HL7_BPARTNERLLP WHERE ISACTIVE = 'Y' AND HL7_BPARTNER_ID = ? ");
			
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, HL7_BPartner_id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				reg = new MHL7BpartnerLLP(ctx, rs, trxName);
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, "getByHL7BPartner", e);
		} finally {
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}
		return reg;
	}
}
