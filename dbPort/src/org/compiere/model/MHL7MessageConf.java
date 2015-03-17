package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
//import java.util.Hashtable;
//import java.util.Iterator;
//import java.util.Map.Entry;
//import java.util.Set;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.ECareConnectorProperties;
import org.compiere.util.confHL7.DefaultSourceConnector;

import com.mirth.connect.client.core.Client;
import com.mirth.connect.client.core.ClientException;
import com.mirth.connect.connectors.ws.WebServiceSenderProperties;
import com.mirth.connect.model.Channel;
import com.mirth.connect.model.ChannelProperties;
import com.mirth.connect.model.Connector;
import com.mirth.connect.model.Filter;
import com.mirth.connect.model.MessageObject.Protocol;
import com.mirth.connect.model.Step;
import com.mirth.connect.model.Transformer;

public class MHL7MessageConf extends X_HL7_MessageConf {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6597754025482050526L;

	private static CLogger s_log = CLogger.getCLogger(MHL7MessageConf.class);

	/**
	 * Standard Constructor
	 * 
	 * @param ctx
	 *            context
	 * @param HL7_MessageConf_ID
	 *            id
	 * @param trxName
	 *            transaction
	 */
	public MHL7MessageConf(Properties ctx, int HL7_MessageConf_ID,
			String trxName) {
		super(ctx, HL7_MessageConf_ID, trxName);
		/**
		 * if (HL7_MessageConf_ID == 0) { setHL7_MessageConf_ID (0);
		 * setHL7_MessageDef_ID (0); }
		 */
	}

	/**
	 * Load Constructor
	 * 
	 * @param ctx
	 *            context
	 * @param rs
	 *            result set
	 * @param trxName
	 *            transaction
	 */
	public MHL7MessageConf(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	/**
	 * Called before Save for Pre-Save Operation
	 * 
	 * @param newRecord
	 *            new record
	 * @return true if record can be saved
	 */
	protected boolean beforeSave(boolean newRecord) {
//
//		boolean channelCreated = false;
//
//		if (isResponse()) {
//			/*
//			 * si es configuracion de respuesta el canal se creara al mappear
//			 * los detalles del mensaje
//			 */
//			return true;
//		}
//
//		/* pasamos 0.0.0 como version de consola para evitar validaciones */
//		try {
//
//			Client mirthClient = MirthClient.getClient(getCtx());
//
//			//channelCreated = updateChannel(mirthClient, newRecord);
//
//		} catch (ClientException e) {
//			s_log.saveError("Failure", e.getLocalizedMessage());
//			return false;
//
//		}
//
//		this.setIsActive(channelCreated);

		return true;
	} // beforeSave

	private List<Connector> getDestinationConnectors(Client mirthClient,
			boolean isResponse) {

		Connector destino = new Connector();
		destino.setName("destino");

		destino.setMode(Connector.Mode.DESTINATION);

		Transformer tran = new Transformer();
		tran.setInboundProtocol(getMessageProtocol());
		tran.setOutboundProtocol(getMessageProtocol());

		destino.setTransformer(tran);
		destino.setFilter(new Filter());
		destino.setEnabled(Boolean.TRUE);

		if (isResponse) {
			MEXMEMirthConfig mirthConfig = MEXMEMirthConfig.get(getCtx(), null);

			Properties prop = new WebServiceSenderProperties().getDefaults();
			prop.setProperty(WebServiceSenderProperties.WEBSERVICE_WSDL_URL, mirthConfig
					.getWSDLPath());
			prop.setProperty(WebServiceSenderProperties.WEBSERVICE_HOST,
					mirthConfig.getEndpointURI());
			prop.setProperty(WebServiceSenderProperties.WEBSERVICE_SOAP_ACTION, mirthConfig
					.getSoapActionURI());
			prop.setProperty(WebServiceSenderProperties.WEBSERVICE_OPERATION, mirthConfig
					.getMethod());
			
			
			/*craa el addres de webservece para mule*/
			StringBuilder host = new StringBuilder("axis:").append(mirthConfig.getEndpointURI())
			.append("?").append(mirthConfig.getMethod());
			prop.setProperty(WebServiceSenderProperties.WEBSERVICE_HOST, host.toString()); 

			// add variable to envelope
			prop.setProperty(WebServiceSenderProperties.WEBSERVICE_ENVELOPE, mirthConfig
					.getSoapEnvelope());

			destino.setProperties(prop);
			destino.setTransportName(WebServiceSenderProperties.name);

		} else {
			ECareConnectorProperties destinatario = MHL7BPartner
					.getConnectorProperties(getCtx(), getHL7_BPartner_ID(),
							get_TrxName());
			destino.setProperties(destinatario.getProperties());
			destino.setTransportName(destinatario.getTransportName());

		}

		List<Connector> res = new ArrayList<Connector>();
		res.add(destino);
		return res;
	}

	private Connector getSourceConnector(boolean isResponse, Properties ctx,
			int partnerID, String trxName/*
										 * getCtx(), getHL7_BPartner_ID(),
										 * get_TrxName()
										 */) {
		
		return DefaultSourceConnector.getDefaultSourceConnector(isResponse,
				ctx, partnerID, trxName, this);
	}

	/**
	 * Get Configurations 
	 * 
	 * @param tableName
	 *            DB Table
	 * @param confType
	 *            Configuration Type
	 *            
	 */
	public static List<Integer> getMessageConfIds(String tableName,
			String confType) {
		return getMessageConfIds(tableName,confType, 0, 0);
	}

	/**
	 * Get Configurations 
	 * if there is a configuration that only have a record at system level, 
	 * this method will return that particular record, but
	 * the priority is for configuration from same client /org
	 * 
	 * @param tableName
	 *            DB Table
	 * @param confType
	 *            Configuration Type
	 * @param clientID
	 *            Client
	 * @param orgID
	 *            Organization
	 *            
	 */
	public static List<Integer> getMessageConfIds(String tableName,
			String confType, int clientID, int orgID) {

		ArrayList<Integer> list = new ArrayList<Integer>();

		StringBuilder sql = new StringBuilder(" SELECT MC.HL7_MessageConf_ID ")
			.append("FROM AD_Table T ")
			.append("INNER JOIN HL7_Process P ")
			.append("ON P.AD_Table_ID = T.AD_Table_ID ")
			.append("INNER JOIN HL7_MessageConf MC ")
			.append("ON MC.IsActive = 'Y' ")
			.append("AND MC.HL7_Process_ID = P.HL7_Process_ID ")
			.append(" WHERE T.TableName = ? ")
		    .append(" AND ((MC.AD_Client_ID =? ")
		    .append(" AND (MC.AD_Org_ID = ? OR (? = 0 AND MC.AD_Org_ID = MC.AD_Org_ID)) ")
		    .append(" AND MC.ACCESSLEVEL <> ? ) ")
		    .append(" OR  MC.ACCESSLEVEL = ? ) "); 
		if (confType != null) {
			sql.append(" AND MC.ConfType = ? ");
		}
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = DB.prepareStatement(sql.toString(), null);

			pstmt.setString(1, tableName);
			pstmt.setInt(2, clientID);
			pstmt.setInt(3, orgID);
			pstmt.setInt(4, orgID);
			pstmt.setString(5, ACCESSLEVEL_All);
			pstmt.setString(6, ACCESSLEVEL_All);
			if (confType != null) {
				pstmt.setString(7, confType);
			}

			rs = pstmt.executeQuery();
			while (rs.next()) {

				list.add(rs.getInt(1));

			}
		} catch (SQLException e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
			return null;
		} finally {
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}

		return list;
	}

	/**
	 * Get Configurations 
	 * if there is a configuration that only have a record at system level, 
	 * this method will return that particular record, but
	 * the priority is for configuration from same client /org
	 * 
	 * @param tableName
	 *            DB Table
	 * @param confType
	 *            Configuration Type
	 * @param clientID
	 *            Client
	 * @param orgID
	 *            Organization
	 *            
	 */
	public static List<Integer> getMessageConfIds(String confType, int clientID, int orgID) {

		ArrayList<Integer> list = new ArrayList<Integer>();

		StringBuilder sql = new StringBuilder(" SELECT MC.HL7_MessageConf_ID ")
			.append("FROM AD_Table T ")
			.append("INNER JOIN HL7_Process P ")
			.append("ON P.AD_Table_ID = T.AD_Table_ID ")
			.append("INNER JOIN HL7_MessageConf MC ")
			.append("ON MC.HL7_Process_ID = P.HL7_Process_ID ")
		    .append(" WHERE ((MC.AD_Client_ID =? ")
		    .append(" AND (MC.AD_Org_ID = ? OR (? = 0 AND MC.AD_Org_ID = MC.AD_Org_ID)) ")
		    .append(" AND MC.ACCESSLEVEL <> ? ) ")
		    .append(" OR  MC.ACCESSLEVEL = ? ) "); 
		if (confType != null) {
			sql.append(" AND MC.ConfType = ? ");
		}
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = DB.prepareStatement(sql.toString(), null);

			pstmt.setInt(1, clientID);
			pstmt.setInt(2, orgID);
			pstmt.setInt(3, orgID);
			pstmt.setString(4, ACCESSLEVEL_All);
			pstmt.setString(5, ACCESSLEVEL_All);
			if (confType != null) {
				pstmt.setString(6, confType);
			}

			rs = pstmt.executeQuery();
			while (rs.next()) {

				list.add(rs.getInt(1));

			}
		} catch (SQLException e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
			return null;
		} finally {
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}

		return list;
	}

	public static MHL7MessageConf getConfByName(Properties ctx, String name, String trxName) {
		MHL7MessageConf conf = null;
		
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append("SELECT * FROM HL7_MESSAGECONF MC WHERE MC.NAME = ? ")
		   .append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", MHL7MessageConf.Table_Name, "MC"));
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setString(1, name);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				conf = new MHL7MessageConf(ctx, rs, trxName);
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, "getConfByName", e);
		} finally {
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}
		
		return conf;
	}
	
	private String getChannelName(String inOut) {

		String channelName = null;
		if("in".equals(inOut)){
			channelName = this.getName() + "_"+inOut;
		}
			channelName = this.getName() +inOut;

		return channelName;
	}

	public Channel getChannel(Client mirthClient,String inOut)
			throws ClientException {

		Channel channel = new Channel();

		channel.setName(getChannelName(inOut));

		List<Channel> channels = mirthClient.getChannel(channel);

		if (channels.isEmpty()) {
			channel.setId(mirthClient.getGuid());
			channel.setVersion(mirthClient.getVersion());

			// por cualquer motivo se asigna el nombre definido en el
			// registro actual
			channel.setName(getChannelName(inOut));
			return channel;
		} else {
			channel = channels.get(0);// el primero y unico?
		}

		return channel;
	}

	public boolean updateChannel(Client mirthClient, String inOut, boolean encryptData)
			throws ClientException {

		boolean channelUpadted = false;

		Channel newChannel = getChannel(mirthClient,inOut);
		
		boolean isResponse = "in".equals(inOut);

		// para logar enviar el mensaje necesitamos el id del canal
		this.setChannelId(newChannel.getId());

		newChannel.setLastModified(Calendar.getInstance());

		newChannel.setPostprocessingScript("return;");
		newChannel.setDeployScript("return;");
		newChannel.setPreprocessingScript("return message;");
		newChannel.setShutdownScript("return;");

		newChannel.setDescription(this.getDescription());
		newChannel.setEnabled(true);
		
		Properties channelProperties = new ChannelProperties().getDefaults();
		newChannel.setProperties(channelProperties);

		newChannel.setDestinationConnectors(getDestinationConnectors(
				mirthClient, isResponse));
		newChannel.setSourceConnector(getSourceConnector(isResponse,
				getCtx(), getHL7_BPartner_ID(), get_TrxName()));

		// siempre se encriptan y se guardan los mensjes mirth
		newChannel.getProperties().setProperty(ChannelProperties.ENCRYPT_DATA,
				encryptData ? Constantes.STR_TRUE : Constantes.STR_FALSE);
		newChannel.getProperties().setProperty(
				ChannelProperties.STORE_MESSAGES, "true");

		channelUpadted = mirthClient.updateChannel(newChannel, true);

		// if(this.is)
		List<String> ids = new ArrayList<String>();
		ids.add(newChannel.getId());
		mirthClient.deployChannels(ids);
//		mirthClient.deployChannels();

		mirthClient.logout();
		return channelUpadted && this.save(null);

	}

	public List<MHL7ResponseDet> getResponseDets(String trxName) {
		ArrayList<MHL7ResponseDet> dets = new ArrayList<MHL7ResponseDet>();

		StringBuilder sb = new StringBuilder();
		sb
				.append("SELECT rd.* FROM HL7_ResponseDet rd WHERE rd.HL7_MessageConf_ID = ?");
		PreparedStatement stmt = DB.prepareStatement(sb.toString(), trxName);
		ResultSet rs = null;

		try {
			stmt.setInt(1, getHL7_MessageConf_ID());
			rs = stmt.executeQuery();

			while (rs.next()) {
				dets.add(new MHL7ResponseDet(getCtx(), rs, trxName));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			try {
				if (stmt != null) {
					stmt.close();
				}
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

		return dets;

	}

	public void updateRecievingChannel(Client mirthClient, boolean newRecord)
			throws ClientException {

		//updateChannel(mirthClient);
		//
		// boolean channelUpadted = false;
		//
		// Channel newChannel = getChannel(mirthClient, newRecord);
		//
		// // para logar enviar el mensaje necesitamos el id del canal
		// this.setChannelId(newChannel.getId());
		//
		// newChannel.setLastModified(Calendar.getInstance());
		//
		// newChannel.setPostprocessingScript("return;");
		// newChannel.setDeployScript("return;");
		// newChannel.setPreprocessingScript("return message;");
		// newChannel.setShutdownScript("return;");
		//
		// newChannel.setDescription(this.getDescription());
		// newChannel.setEnabled(true);
		//
		// Properties channelProperties = new ChannelProperties().getDefaults();
		// newChannel.setProperties(channelProperties);
		//
		// newChannel
		// .setDestinationConnectors(getDestinationConnectors(mirthClient));
		// newChannel.setSourceConnector(getSourceConnector());
		//
		// // siempre se encriptan y no se guardan los mensjes mirth
		// newChannel.getProperties().setProperty(ChannelProperties.ENCRYPT_DATA,
		// "true");
		// newChannel.getProperties().setProperty(
		// ChannelProperties.STORE_MESSAGES, "false");
		//
		// channelUpadted = mirthClient.updateChannel(newChannel, true);
		//
		// // if(this.is)
		//
		// mirthClient.deployChannels();
		//
		// mirthClient.logout();
		// return channelUpadted;
		//
	}

	public List<Step> getSteps() {
		List<Step> steps = new ArrayList<Step>();

		Step step = new Step();

		step.setName("data");
		step.setType("JavaScript");
		step.setSequenceNumber(0);

		StringBuilder sbScript = new StringBuilder();
		if(getMessageProtocol().equals(Protocol.XML)){
			if (getConfType().equals(CONFTYPE_Elegibility)) {
				sbScript.append("data= '|msg:='+Packages.java.net.URLEncoder.encode(msg, 'UTF-8');\n ");
			} else {
				sbScript.append("data= '|msg:='+Packages.java.net.URLEncoder.encode(message, 'UTF-8');\n ");
			}
		}else{
			sbScript.append("data= '|msg:='+message;\n ");
		}
		sbScript.append(" channelMap.put('data', data);\n ");
		sbScript.append(" channelMap.put('messageId',messageObject.getId());\n ");
		sbScript.append(" channelMap.put('messageType',messageObject.getId());\n ");
		sbScript.append(" channelMap.put('channelId','").append(getClassname())
				.append("');\n ");
		sbScript.append(" channelMap.put('senderId','")
				.append(getEXME_InterfaceSender().getValue()).append("');\n ");

		HashMap<String, String> data = new HashMap<String, String>();
		data.put("Script", sbScript.toString());

		step.setScript(sbScript.toString());
		step.setData(data);

		steps.add(step);
		return steps;
	}
	
	
	/*?? */
//	private Step getStep(int seq, String var, String mapping){
//		
//		Step step = new Step();
//		step.setSequenceNumber(seq);
//		step.setName(var);
//		step
//				.setScript("var mapping; try { mapping = "+mapping+"; }catch (e) { logger.error(e);  mapping = '';}channelMap.put('"+var+"', validate( mapping , '', new Array()));");
//		step.setType("Mapper");
//		
//		HashMap<String, Object> data = new HashMap<String, Object>();
//		data.put("Mapping", mapping);
//		data.put("Variable", var);
//		data.put("RegularExpressions", new ArrayList<Object>());
//		data.put("DefaultValue", "");
//		data.put("isGlobal", "channel");
//		step.setData(data);
//
//		return step;
//		
//	}

//	private String getScriptFromMapping(Hashtable<String,Object> messageMapping , final String VAR) {		
//		
//		
//		
//		
//		StringBuilder js = new StringBuilder();
//		js.append(VAR).append("+= '{ '");//data += '{ ' 
//		
//		Set<Entry< String,Object>> entrySet = messageMapping.entrySet();
//		 //Set<String> keys = messageMapping.keySet();
//		 
//		 
//		 Iterator<Entry<String,Object>> iterator = entrySet.iterator();
//		 
//		 while(iterator.hasNext()){
//			 Entry<String,Object> entry = iterator.next();
//			 Object value = entry.getValue();
//			 if(value instanceof String){
//				 js.append(VAR).append("+= '").append(entry.getKey()).append(": '+").append(value.toString()).append(".toString()");
//				 //	data = '';			
//				 // data += '{ ' 
//				 // data += 'key: '+msg[][].toString(),
//				 
//			 }else if (value instanceof Hashtable<?, ?>){
//				 
//				 Hashtable<MHL7ResponseDet, Object> mapValue = ((Hashtable<MHL7ResponseDet, Object>)value);
//				 //Set<MHL7ResponseDet> nestedKeys = mapValue.keySet();
//				 Set<Entry<MHL7ResponseDet, Object>> innerEntrySet = mapValue.entrySet();
//				 Iterator<Entry<MHL7ResponseDet, Object>> innerIterator = innerEntrySet.iterator();
//				 
//				 while(innerIterator.hasNext()){
//					 MHL7ResponseDet det = innerIterator.next().getKey();
//					 js.append(VAR).append(" += '").append(det.getName()).append(" : {'");
//
//					 js.append("for ( seg in msg.children()){ ")//--
//					 .append(" if ( seg.name() == ").append(det.getScript()).append(".name() ){");
//					 Hashtable mapeo = (Hashtable)mapValue.get(det);
//					 js.append(getScriptFromMapping(mapeo, VAR));
////					 for (Object o : mapeo.keySet()){
////						 js.append(VAR).append("+=")
////					 }
//				 }
//				 if(iterator.hasNext()){
//					 js.append(VAR).append("+= ','");
//				 }
//				
//				 
//		//		 scriptBuilder.append(key).append(":").append(getScriptFromMapping((Hashtable<String, Object>)value));
//			 }
//			 
//		 }
//		 js.append(VAR).append("+= '}'");
//		
//		return js.toString();
//	}

//	private Hashtable mapping = new Hashtable(); 
//	private  Hashtable parents = new Hashtable();
	
//	@SuppressWarnings("unchecked")
//	private Hashtable getMessageMapping(List<MHL7ResponseDet> dets,Hashtable mapping, boolean root) {		
//
////		 = //this.getResponseDets(get_TrxName());
//		
//		for(MHL7ResponseDet det : dets){
//			
//			if((det.getHL7_ResponseDetParent_ID()== 0) ==root){				
//				
//				 if (det.isGroup()) {
//						Hashtable mapeoInte = new Hashtable();
//						mapeoInte.put(det, getMessageMapping(det.getResponseDets(get_TrxName()),new Hashtable(),false));
//						mapping.put(det.getName(),mapeoInte);						
//					}else{
//						mapping.put(det.getName(),det.getScript());
//					}
//			}else{				
//				
//			}
//			
//		}
//		return mapping;
//
//	}
//	
	
	/*usado para interactuar con api de mirth*/
	public Protocol getMessageProtocol(){
		if(getProtocol() != null){
			if (getProtocol().equals(MHL7MessageConf.PROTOCOL_ClinicalDocumentArchitectureCDA) || getProtocol().equals(MHL7MessageConf.PROTOCOL_ContinuityOfCareRecordCCR) ){
				return Protocol.valueOf(MHL7MessageConf.PROTOCOL_XMLExtensibleMarkupLanguage);
			}
		}
		return Protocol.valueOf(getProtocol());
	}
	
	/**	Static Logger				*/
	private static CLogger log = CLogger.getCLogger (MHL7MessageConf.class);
	
	public static ArrayList<MHL7MessageConf> getAllByOrg(Properties ctx, int AD_Org_ID, String trxName) {
		ArrayList<MHL7MessageConf> list = new ArrayList<MHL7MessageConf>();
		
		String sql = "SELECT * FROM HL7_MESSAGECONF WHERE ISACTIVE = 'Y' AND AD_ORG_ID = ? ORDER BY HL7_MESSAGECONF_ID";
		PreparedStatement pstmt = null;
		try {
			pstmt = DB.prepareStatement(sql, trxName);
			pstmt.setInt(1, AD_Org_ID);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				list.add(new MHL7MessageConf(ctx, rs, trxName));
			}
			rs.close();
			pstmt.close();
			pstmt = null;
		} catch (Exception e) {
			log.log(Level.SEVERE, "MHL7MessageConf.getAllByOrg", e);
		}
		try {
			if (pstmt != null)
				pstmt.close ();
			pstmt = null;
		} catch (Exception e) {
			pstmt = null;
		}
		
		return list;
	}

}