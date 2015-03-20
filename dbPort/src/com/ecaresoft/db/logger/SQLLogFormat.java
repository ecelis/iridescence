package com.ecaresoft.db.logger;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

import org.compiere.util.DB;

public class SQLLogFormat extends Formatter {

	@Override
	public String format(LogRecord logRecord) {
		StringBuilder record = new StringBuilder("\n-- ")
			.append(calcDate(logRecord.getMillis()))
			.append("\n")
			.append(formatMessage(logRecord))
			.append(";");
		
		return record.toString();
	}
	@Override
	public String getHead(Handler h) {
		String dbType = "-- DB Type: ";
		if(DB.isOracle()){
			dbType = dbType + "Oracle";
		}else if(DB.isPostgreSQL()){
			dbType = dbType + "PostgreSQL";
		}else if(DB.isMySQL()){
			dbType = dbType + "MySQL";
		}else{
			dbType = dbType + "Unknown";
		}
		dbType = dbType + "\n";
		return dbType;
	}

	protected static String calcDate(long millisecs) {
		SimpleDateFormat date_format = new SimpleDateFormat("MMM dd,yyyy HH:mm");
		Date resultdate = new Date(millisecs);
		return date_format.format(resultdate);
	}
	
}
