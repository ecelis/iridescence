package org.compiere.util.vo;

import java.io.StringReader;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;

public class MessageLogVO {
	
	private CLogger	log	= CLogger.getCLogger (MessageLogVO.class);
	
	private Timestamp dateMsg;
	private String connector;
	private String type;
	private String source;
	private String status;
	private String protocol;
	private String rawMsg;
	private String transMsg;
	private String encMsg;
	private ArrayList<Properties> mappings;
	private String errors;
	
	public MessageLogVO() {
		mappings = new ArrayList<Properties>();
	}

	private Document convertToXML(String str) {
		Document doc = null;
		try {
			SAXBuilder builder = new SAXBuilder();
			doc = builder.build(new StringReader(str));
		} catch (Exception e) {
			log.log(Level.SEVERE, "convertToXML", e);
		}
		return doc;
	}
	
	public void addConnectorMap(String connMap) {
		
	}
	
	public void addChannelMap(String channMap) {
		
	}
	
	public void addResponseMap(String respMap) {
		Document doc = convertToXML(respMap);
		for (Object o : doc.getRootElement().getChildren("entry")) {
			Element e = (Element) o;
			Properties map = new Properties();
			map.put("Scope", "Response");
			Element var = (Element) e.getChildren().get(0);
			map.put("Variable", var.getText());
			Element value = (Element) e.getChildren().get(1);
			map.put("Value", value.getText());
			addMappings(map);
		}
	}
	
	public void addMappings(Properties mapping) {
		mappings.add(mapping);
	}
	
	public void clearMappings() {
		mappings.clear();
	}
	
	public ArrayList<Properties> getMappings() {
		return mappings;
	}
	
	public Timestamp getDateMsg() {
		return dateMsg;
	}

	public void setDateMsg(Timestamp dateMsg) {
		this.dateMsg = dateMsg;
	}

	public String getConnector() {
		return connector;
	}

	public void setConnector(String connector) {
		this.connector = connector;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getProtocol() {
		return protocol;
	}

	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}

	public String getRawMsg() {
		return rawMsg;
	}

	public void setRawMsg(String rawMsg) {
		this.rawMsg = rawMsg;
	}

	public String getTransMsg() {
		return transMsg;
	}

	public void setTransMsg(String transMsg) {
		this.transMsg = transMsg;
	}

	public String getEncMsg() {
		return encMsg;
	}

	public void setEncMsg(String encMsg) {
		this.encMsg = encMsg;
	}

	public String getErrors() {
		return errors;
	}

	public void setErrors(String errors) {
		this.errors = errors;
	}
}
