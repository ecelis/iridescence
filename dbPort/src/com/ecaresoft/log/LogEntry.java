package com.ecaresoft.log;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.apache.commons.io.FileUtils;
import org.compiere.model.MChangeLog;
import org.compiere.model.MRefList;
import org.compiere.model.PO;
import org.compiere.util.CLogger;
import org.compiere.util.Env;
import org.compiere.util.Ini;
import org.compiere.util.Utilerias;

import com.google.gson.Gson;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;

/**
 * @author odelarosa
 *
 */
public class LogEntry {

	private static CLogger s_log = CLogger.getCLogger(LogEntry.class);

	private static final SimpleDateFormat SDF = new SimpleDateFormat("ddMMyy-hhmmss.SSS");

	public static void test(final Properties ctx) {

	}

	private int clientId;
	private long created;
	private List<Data> data = new ArrayList<Data>();
	private transient String dummy;
	private int orgId;
	private int recordId;
	private int sessionId;
	private Table table;
	private String type;
	private transient String typeString;
	private User user;

	public LogEntry() {
	}

	/**
	 * Agrega registro a mongo
	 */
	public void addToMongo() {

		Thread thread = new Thread(new Runnable() {

			@Override
			public void run() {
				DB db = PO.getMongoDB();

				// si es nula, hay que tratar de reconectar
				if (db == null) {
					s_log.log(Level.INFO, "Trying to reconnect MongoDB");
					LogUtils.connectToMongo();
					db = PO.getMongoDB();
				}

				if (db == null) {
					s_log.log(Level.INFO, "MongoDB connection failed!!!");

					File file = new File(Ini.getCompiereHome(), "changeLog");

					if (!file.exists()) {
						file.mkdir();
					}

					try {
						String fileName = String.format("%s.%s", SDF.format(new Date()), "json");
						FileUtils.writeStringToFile(new File(file, fileName), new Gson().toJson(LogEntry.this));
					} catch (Exception e) {
						s_log.log(Level.SEVERE, null, e);
					}
				} else {
					db.requestStart();
					try {
						db.requestEnsureConnection();
						DBCollection logList = db.getCollection("log");
						DBObject dbObject = (DBObject) JSON.parse(new Gson().toJson(LogEntry.this));
						logList.insert(dbObject);
					} catch (Exception e) {
						s_log.log(Level.SEVERE, null, e);
					} finally {
						db.requestDone();
					}
				}
			}
		});

		thread.start();

	}

	public int getClientId() {
		return clientId;
	}

	public long getCreated() {
		return created;
	}

	public List<Data> getData() {
		return data;
	}

	public String getDummy() {
		return dummy;
	}

	public int getOrgId() {
		return orgId;
	}

	public int getRecordId() {
		return recordId;
	}

	public int getSessionId() {
		return sessionId;
	}

	public Table getTable() {
		return table;
	}

	public String getType() {
		return type;
	}

	public String getTypeString() {
		if (typeString == null) {
			if ("P".equals(getType())) {
				typeString = Utilerias.getAppMsg(Env.getCtx(), "msj.printed");
			} else if ("C".equals(getType())) {
				typeString = Utilerias.getAppMsg(Env.getCtx(), "msj.reviewed");
			} else {
				typeString = MRefList.getListasTraducidasValue(Env.getCtx(), MChangeLog.EVENTCHANGELOG_AD_Reference_ID, false, getType());
			}
		}
		return typeString;
	}

	public User getUser() {
		return user;
	}

	public void setClientId(int clientId) {
		this.clientId = clientId;
	}

	public void setCreated(long created) {
		this.created = created;
	}

	public void setData(List<Data> data) {
		this.data = data;
	}

	public void setDummy(String dummy) {
		this.dummy = dummy;
	}

	public void setOrgId(int orgId) {
		this.orgId = orgId;
	}

	public void setRecordId(int recordId) {
		this.recordId = recordId;
	}

	public void setSessionId(int sessionId) {
		this.sessionId = sessionId;
	}

	public void setTable(Table table) {
		this.table = table;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
