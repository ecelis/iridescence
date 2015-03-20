package org.compiere.util.confHL7;

import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.db.CConnection;
import org.compiere.util.CLogger;
import org.compiere.util.DB;

import com.mirth.connect.client.core.Client;
import com.mirth.connect.client.core.ClientException;
import com.mirth.connect.connectors.jdbc.DatabaseWriterProperties;
import com.mirth.connect.connectors.vm.ChannelReaderProperties;
import com.mirth.connect.model.Channel;
import com.mirth.connect.model.ChannelProperties;
import com.mirth.connect.model.Connector;
import com.mirth.connect.model.Filter;
import com.mirth.connect.model.MessageObject.Protocol;
import com.mirth.connect.model.Rule;
import com.mirth.connect.model.Step;
import com.mirth.connect.model.Transformer;

public class DefaultAckManager {

	/**	Static Logger				*/
	private static CLogger		s_log = CLogger.getCLogger (DefaultAckManager.class);
	private static final String DEPOLY_SCRIPT = "return;";
	private static final String POSTPROCESSING_SCRIPT = "return;";
	private static final String PREPROCESSING_SCRIPT = "return message;";
	private static final String SHUTDOWN_SCRIPT = "return;";
	private final String SQL_STATEMENT = "UPDATE HL7_Dashboard SET STATUS = ${acknowledgmentCode} WHERE HL7_Dashboard_ID = ${messageControlId}";

	public static final String CHANNEL_NAME = "DefaultAckManager";

	private StringBuilder inboundTemplate = new StringBuilder(
			"MSH|^~\\&amp;|GC APP|GC FAC|ACME APP|ACME FAC|20071016055244||ACK^A01|20071016055244131|P|2.3.1|")
			.append("\nMSA|AA|13463136|MSG Received Successfully|");

	private Channel ackManager = new Channel();

	@SuppressWarnings("unused")
	private DefaultAckManager() {
	}

	public DefaultAckManager(Client mirthClient) throws ClientException {

		ackManager.setName(DefaultAckManager.CHANNEL_NAME);

		List<Channel> channels = mirthClient.getChannel(ackManager);

		// logica repetida esto tmbn se hace en MHL7MEssageCong.getChannel
		if (channels.isEmpty()) {
			ackManager.setVersion(mirthClient.getVersion());
			ackManager.setId(mirthClient.getGuid());

			ackManager
					.setDescription("creado desde medsys para manejar las respuestas ACK");
			ackManager.setEnabled(true);

			ackManager.setDeployScript(DEPOLY_SCRIPT);
			ackManager.setPostprocessingScript(POSTPROCESSING_SCRIPT);
			ackManager.setPreprocessingScript(PREPROCESSING_SCRIPT);
			ackManager.setShutdownScript(SHUTDOWN_SCRIPT);

			ackManager.setProperties(new ChannelProperties().getDefaults());
			//siempre se guardaron los mensjes encriptados en el mirth
			ackManager.getProperties().setProperty(ChannelProperties.ENCRYPT_DATA,"true");

		} else {
			// se obtiene el existente para se actualizado
			ackManager = channels.get(0);

		}

		ackManager.setLastModified(Calendar.getInstance());
		ackManager.setSourceConnector(getChannelSource());
		ackManager.setDestinationConnectors(getDestinations());

	}

	private Connector getChannelSource() {

		Connector source = new Connector();

		source.setMode(Connector.Mode.SOURCE);
		source.setName("sourceConnector");

		source.setProperties(new ChannelReaderProperties().getDefaults());

		Transformer trans = new Transformer();

		trans.setSteps(getDefaultSteps());

		trans.setInboundTemplate(inboundTemplate.toString());
		trans.setOutboundTemplate(null);

		trans.setInboundProtocol(Protocol.HL7V2);
		trans.setOutboundProtocol(Protocol.HL7V2);

		trans.setInboundProperties(null);
		trans.setOutboundProperties(null);

		Filter f = new Filter();
		f.setRules(new ArrayList<Rule>());
		source.setFilter(f);

		source.setTransportName(ChannelReaderProperties.name);
		source.setEnabled(true);

		source.setTransformer(trans);

		return source;

	}

	private List<Connector> getDestinations() {
		List<Connector> dests = new ArrayList<Connector>();

		Connector dest = new Connector();

		dest.setName("destino");
		dest.setProperties(getDefaultProperties());

		dest.setMode(Connector.Mode.DESTINATION);
		dest.setEnabled(true);
		dest.setFilter(new Filter());
		dest.setTransportName(DatabaseWriterProperties.name);

		Transformer megatron = new Transformer();
		megatron.setInboundProtocol(Protocol.HL7V2);
		megatron.setOutboundProtocol(Protocol.HL7V2);
		//megatron.transform("!!");
		

		dest.setTransformer(megatron);
		dest.setEnabled(true);

		dests.add(dest);

		return dests;
	}

	private Properties getDefaultProperties() {

		Properties p = new DatabaseWriterProperties().getDefaults();

		CConnection ccon = CConnection.get();
		try {
			DatabaseMetaData dbmd = DB.getConnectionRO().getMetaData();

			p.setProperty(DatabaseWriterProperties.DATABASE_USERNAME, dbmd
					.getUserName());
			p.setProperty(DatabaseWriterProperties.DATABASE_URL, dbmd.getURL());
			p.setProperty(DatabaseWriterProperties.DATABASE_SQL_STATEMENT,
					SQL_STATEMENT);

			p.setProperty(DatabaseWriterProperties.DATABASE_PASSWORD, ccon
					.getDbPwd());
			p
					.setProperty(DatabaseWriterProperties.DATABASE_DRIVER,
							DriverManager.getDriver(dbmd.getURL()).getClass()
									.getName());
			p.setProperty(DatabaseWriterProperties.DATABASE_HOST, "query");

			System.out.println("propiedades de destino: " + p);

		} catch (SQLException e) {
			s_log.log(Level.SEVERE, e.getMessage(), e);
		}

		return p;
	}

	private List<Step> getDefaultSteps() {
		List<Step> steps = new ArrayList<Step>();

		/*
		 * dos pasos en la transformada, uno para poner el codigo del Ack y otro
		 * para el messagecontrol ID
		 */

		// FIXME esta muy feo
		Step step1 = new Step();
		step1.setSequenceNumber(0);
		step1.setName("acknowledgmentCode");
		step1
				.setScript("var mapping; try { mapping = msg['MSA']['MSA.1']['MSA.1.1'].toString(); }catch (e) { logger.error(e);  mapping = '';}channelMap.put('acknowledgmentCode', validate( mapping , '', new Array()));");
		step1.setType("Mapper");

		HashMap<String, Object> data1 = new HashMap<String, Object>();
		data1.put("Mapping", "msg['MSA']['MSA.1']['MSA.1.1'].toString()");
		data1.put("Variable", "acknowledgmentCode");
		data1.put("RegularExpressions", new ArrayList<Object>());
		data1.put("DefaultValue", "");
		data1.put("isGlobal", "channel");
		step1.setData(data1);

		Step step2 = new Step();
		step2.setSequenceNumber(1);
		step2.setName("messageControlId");
		step2
				.setScript("var mapping; try { mapping = msg['MSA']['MSA.2']['MSA.2.1'].toString(); }catch (e) { logger.error(e);  mapping = '';}channelMap.put('messageControlId', validate( mapping , '', new Array()));");
		step2.setType("Mapper");

		HashMap<String, Object> data2 = new HashMap<String, Object>();
		data2.put("Mapping", "msg['MSA']['MSA.2']['MSA.2.1'].toString()");
		data2.put("Variable", "messageControlId");
		data2.put("RegularExpressions", new ArrayList<Object>());
		data2.put("DefaultValue", "");
		data2.put("isGlobal", "channel");
		step2.setData(data2);

		steps.add(step1);
		steps.add(step2);

		return steps;
	}

	public Channel get() {
		return ackManager;
	}

}
