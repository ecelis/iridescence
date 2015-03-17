package com.ecaresoft.log;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.lang.StringUtils;
import org.compiere.model.PO;
import org.compiere.util.CLogger;
import org.compiere.util.Env;
import org.compiere.util.Ini;
import org.compiere.util.SecureEngine;
import org.compiere.util.TimeUtil;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import com.mongodb.AggregationOutput;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoException;

/**
 * @author odelarosa
 *
 */
public class LogUtils {

	/**
	 * POJO para representar la información de mongo
	 *
	 * @author odelarosa
	 *
	 */
	private static class MongoData {
		private String host;
		private String pass;
		private int port;
		private String user;
		private boolean useAuth = false;
		private int connPerHost = 100;

		public int getConnPerHost() {
			return connPerHost;
		}

		public void setConnPerHost(int connPerHost) {
			this.connPerHost = connPerHost;
		}

		public boolean isUseAuth() {
			return useAuth;
		}

		public void setUseAuth(boolean useAuth) {
			this.useAuth = useAuth;
		}

		public String getHost() {
			return host;
		}

		public String getPass() {
			return pass;
		}

		public int getPort() {
			return port;
		}

		public String getUser() {
			return user;
		}

		public void setHost(String host) {
			this.host = host;
		}

		public void setPass(String pass) {
			this.pass = pass;
		}

		public void setPort(int port) {
			this.port = port;
		}

		public void setUser(String user) {
			this.user = user;
		}
	}

	private static CLogger s_log = CLogger.getCLogger(LogUtils.class);

	/**
	 * Se conecta a mongo
	 */
	public static void connectToMongo() {
		MongoClient mongoClient = null;
		MongoData mongo = getMongoData();

		boolean success = false;

		if (mongo != null) {
			DB db = null;
			try {
//				mongoClient = new MongoClient(mongo.getHost(), mongo.getPort());
				MongoClientOptions.Builder mco = new MongoClientOptions.Builder();
				mco.connectionsPerHost(mongo.getConnPerHost());

				mongoClient =
						new MongoClient(
								mongo.getHost() + ":" + mongo.getPort(),
								mco.build()
						);

				db = mongoClient.getDB("ecare");

				if (!mongo.isUseAuth() ||
						(mongo.isUseAuth() &&
						db.authenticate(mongo.getUser(), mongo.getPass().toCharArray()))) {
					success = true;
				}
			} catch (MongoException me) {
				s_log.log(Level.INFO, "", me);
			} catch (Exception ex) {
				s_log.log(Level.SEVERE, null, ex);
			}

			if(!success) {
				if (mongoClient != null) {
					mongoClient.close();
				}
				mongoClient = null;
				db = null;
				mongo = null;
			}

			PO.setMongoDB(db);
		}
	}

	public static DBCollection getLogList() {
		if (PO.getMongoDB() != null) {
			return PO.getMongoDB().getCollection("log");
		} else {
			return null;
		}
	}

	/**
	 * Lee el archivo de datos de mongo
	 *
	 * @return POJO con los datos
	 */
	private static MongoData getMongoData() {
		MongoData mongoData = null;
		try {
			File mongoFile = new File(Ini.getCompiereHome(), "mongo.xml");
			if (mongoFile.exists()) {
				DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
				Document doc = dBuilder.parse(mongoFile);

				doc.getDocumentElement().normalize();

				NodeList server = doc.getElementsByTagName("server");
				NodeList port = doc.getElementsByTagName("port");
				NodeList user = doc.getElementsByTagName("user");
				NodeList pass = doc.getElementsByTagName("pass");
				NodeList useAuth = doc.getElementsByTagName("useAuth");
				NodeList connPerHost = doc.getElementsByTagName("connectionsPerHost");

				if (server != null && port != null && user != null && pass != null) {
					mongoData = new MongoData();
					mongoData.setHost(StringUtils.trim(server.item(0).getTextContent()));
					mongoData.setPort(Integer.parseInt(StringUtils.trim(port.item(0).getTextContent())));
					mongoData.setUser(StringUtils.trim(user.item(0).getTextContent()));
					mongoData.setPass(SecureEngine.decrypt(StringUtils.trim(pass.item(0).getTextContent())));

					try {
						int connections =
								Integer.parseInt(
										StringUtils.trim(connPerHost.item(0).getTextContent())
								);

						if(connections > 0) {
							mongoData.setConnPerHost(connections);
						}
					} catch (Exception e) {
						s_log.log(Level.WARNING, "Loading mongodb configuration ...", e.getMessage());
					}

					if(useAuth != null && useAuth.getLength() > 0) {
						mongoData.setUseAuth(
								"Y".equals(StringUtils.trim(useAuth.item(0).getTextContent()))
						);
					}
				}
			}
		} catch (Exception ex) {
			s_log.log(Level.SEVERE, null, ex);
		}

		return mongoData;
	}

	public static List<LogEntry> getRecords(Properties ctx, Table table, boolean filterByOrg, int recordId, Date date, Date date2) {
		// switch
		if (date2.before(date)) {
			Date tmp = date;
			date = date2;
			date2 = tmp;
		}

		List<LogEntry> logEntries = new ArrayList<LogEntry>();
		DBCollection logList = getLogList();

		if (logList != null) {

			DBObject matchCriteria = new BasicDBObject("$gte", TimeUtil.getInitialRange(ctx, date).getTime());
			matchCriteria.put("$lte", TimeUtil.getFinalRange(ctx, date2).getTime());

			DBObject params = new BasicDBObject("table.id", table.getId());
			params.put("created", matchCriteria);

			params.put("clientId", Env.getAD_Client_ID(ctx));

			if (filterByOrg) {
				params.put("orgId", Env.getAD_Org_ID(ctx));
			}

			if (recordId != -1) {
				params.put("recordId", recordId);
			}

			DBCursor cursor = logList.find(params).sort(new BasicDBObject("created", -1));

			List<DBObject> lst = cursor.toArray();

			for (DBObject dbObject : lst) {
				LogEntry logEntry = new LogEntry();

				User user = new User();

				DBObject userObject = ((DBObject) dbObject.get("user"));

				user.setId(Integer.valueOf(userObject.get("id").toString()));
				user.setName(userObject.get("name").toString());

				logEntry.setCreated((Long) dbObject.get("created"));
				logEntry.setUser(user);
				logEntry.setType(dbObject.get("type").toString());
				logEntry.setRecordId(Integer.valueOf(dbObject.get("recordId").toString()));
				logEntry.setClientId(Integer.valueOf(dbObject.get("clientId").toString()));
				logEntry.setOrgId(Integer.valueOf(dbObject.get("orgId").toString()));
				logEntry.setSessionId(Integer.valueOf(dbObject.get("sessionId").toString()));
				logEntry.setTable(table);

				BasicDBList dataList = (BasicDBList) dbObject.get("data");

				for (@SuppressWarnings("rawtypes")
				Iterator iterator = dataList.iterator(); iterator.hasNext();) {
					DBObject object = (DBObject) iterator.next();

					Data data = new Data();

					Column column = new Column();

					DBObject columnObject = (DBObject) object.get("column");

					column.setId(Integer.valueOf(columnObject.get("id").toString()));
					column.setName(columnObject.get("name").toString());
					column.setPrintName(columnObject.get("printName").toString());

					data.setColumn(column);

					data.setNewValue(object.get("newValue"));
					data.setOldValue(object.get("oldValue"));

					logEntry.getData().add(data);
				}

				logEntries.add(logEntry);
			}
		}

		return logEntries;
	}

	public static List<Table> getTables(Properties ctx, boolean filterByOrg, List<Integer> tableIds, Date date, Date date2) {
		// switch
		if (date2.before(date)) {
			Date tmp = date;
			date = date2;
			date2 = tmp;
		}

		List<Table> tables = new ArrayList<Table>();

		DBCollection logList = getLogList();

		if (logList != null) {

			// build $match operation
			DBObject matchCriteria = new BasicDBObject("$gte", TimeUtil.getInitialRange(ctx, date).getTime());
			matchCriteria.put("$lte", TimeUtil.getFinalRange(ctx, date2).getTime());
			DBObject matchFiels = new BasicDBObject("created", matchCriteria);
			if (tableIds != null && !tableIds.isEmpty()) {
				BasicDBList or = new BasicDBList();
				for (int tableId : tableIds) {
					DBObject line = new BasicDBObject("table.id", tableId);
					or.add(line);
				}
				matchFiels.put("$or", or);
			}
			matchFiels.put("clientId", Env.getAD_Client_ID(ctx));

			if (filterByOrg) {
				matchFiels.put("orgId", Env.getAD_Org_ID(ctx));
			}

			DBObject match = new BasicDBObject("$match", matchFiels);

			// build $group operation
			DBObject groupFields = new BasicDBObject("id", "$table.id");
			groupFields.put("name", "$table.name");
			groupFields.put("printName", "$table.printName");
			DBObject groupId = new BasicDBObject("_id", groupFields);
			DBObject group = new BasicDBObject("$group", groupId);

			// build the $projection operation
			DBObject fields = new BasicDBObject("_id", 0);
			fields.put("id", "$_id.id");
			fields.put("name", "$_id.name");
			fields.put("printName", "$_id.printName");
			DBObject project = new BasicDBObject("$project", fields);

			// build the $sort operation
			DBObject sort = new BasicDBObject("$sort", new BasicDBObject("printName", 1));

			AggregationOutput output = logList.aggregate(match, group, project, sort);

			Iterable<DBObject> iterable = output.results();

			for (Iterator<DBObject> iterator = iterable.iterator(); iterator.hasNext();) {
				DBObject dbObject = (DBObject) iterator.next();

				Table table = new Table();
				table.setId(Integer.valueOf(dbObject.get("id").toString()));
				table.setName(dbObject.get("name").toString());
				table.setPrintName(dbObject.get("printName").toString());

				tables.add(table);
			}
		}

		return tables;
	}

}
