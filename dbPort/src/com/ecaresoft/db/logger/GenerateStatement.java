package com.ecaresoft.db.logger;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Types;
import java.text.SimpleDateFormat;

import org.compiere.util.DB;

public class GenerateStatement {

	private static final SimpleDateFormat	dateFormat	= new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

	public static String generateInsertStatements(String tableName, String where) {
		try {
			if (!where.trim().startsWith("WHERE")) {
				where = "WHERE " + where;
			}
			ResultSet rs = DB.prepareStatement("SELECT * FROM " + tableName + " " + where, null).executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			int numColumns = rsmd.getColumnCount();
			int[] columnTypes = new int[numColumns];
			String columnNames = "";
			for (int i = 0; i < numColumns; i++) {
				columnTypes[i] = rsmd.getColumnType(i + 1);
				if (i != 0) {
					columnNames += ",";
				}
				columnNames += rsmd.getColumnName(i + 1);
			}

			java.util.Date d = null;
			StringBuilder p = new StringBuilder();
			if (rs.next()) {
				String columnValues = "";
				for (int i = 0; i < numColumns; i++) {
					if (i != 0) {
						columnValues += ",";
					}

					switch (columnTypes[i]) {
						case Types.BIGINT:
						case Types.BIT:
						case Types.BOOLEAN:
						case Types.DECIMAL:
						case Types.DOUBLE:
						case Types.FLOAT:
						case Types.INTEGER:
						case Types.SMALLINT:
						case Types.TINYINT:
						case Types.NUMERIC:
							String v = rs.getString(i + 1);
							columnValues += v;
							break;

						case Types.DATE:
							d = rs.getDate(i + 1);
						case Types.TIME:
							if (d == null)
								d = rs.getTime(i + 1);
						case Types.TIMESTAMP:
							if (d == null)
								d = rs.getTimestamp(i + 1);

							if (d == null) {
								columnValues += "null";
							} else {
								columnValues += "TO_DATE('" + dateFormat.format(d) + "', 'YYYY/MM/DD HH24:MI:SS')";
							}
							break;

						default:
							v = rs.getString(i + 1);
							if (v != null) {
								columnValues += "'" + v.replaceAll("'", "''") + "'";
							} else {
								columnValues += "null";
							}
							break;
					}
				}
				p.append(String.format("INSERT INTO %s (%s) values (%s)\n/", tableName, columnNames, columnValues));

			}
			return p.toString();
		} catch (Exception e) {
			return "- SQL Log process fails while trying to creat an INSERT statement for this record:  \n--SELECT * FROM " + tableName + " " + where;
		}
	}

	public static String generateUpdateStatements(String tableName, String where) {
		try {
			if (!where.trim().startsWith("WHERE")) {
				where = "WHERE " + where;
			}
			ResultSet rs = DB.prepareStatement("SELECT * FROM " + tableName + " " + where, null).executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			int numColumns = rsmd.getColumnCount();
			int[] columnTypes = new int[numColumns];
			String[] columnNames = new String[numColumns];
			for (int i = 0; i < numColumns; i++) {
				columnTypes[i] = rsmd.getColumnType(i + 1);
				columnNames[i] = rsmd.getColumnName(i + 1);
			}

			java.util.Date d = null;
			StringBuilder p = new StringBuilder();
			if (rs.next()) {
				String columnValues = "";
				for (int i = 0; i < numColumns; i++) {
					if (i != 0) {
						columnValues += ",";
					}

					switch (columnTypes[i]) {
						case Types.BIGINT:
						case Types.BIT:
						case Types.BOOLEAN:
						case Types.DECIMAL:
						case Types.DOUBLE:
						case Types.FLOAT:
						case Types.INTEGER:
						case Types.SMALLINT:
						case Types.TINYINT:
						case Types.NUMERIC:
							String v = rs.getString(i + 1);
							columnValues += columnNames[i] + "=" + v;
							break;

						case Types.DATE:
							d = rs.getDate(i + 1);
						case Types.TIME:
							if (d == null)
								d = rs.getTime(i + 1);
						case Types.TIMESTAMP:
							if (d == null)
								d = rs.getTimestamp(i + 1);

							if (d == null) {
								columnValues += columnNames[i] + "=" + "null";
							} else {
								columnValues += columnNames[i] + "=" + "TO_DATE('" + dateFormat.format(d) + "', 'YYYY/MM/DD HH24:MI:SS')";
							}
							break;

						default:
							v = rs.getString(i + 1);
							if (v != null) {
								columnValues += columnNames[i] + "=" + "'" + v.replaceAll("'", "''") + "'";
							} else {
								columnValues += columnNames[i] + "=" + "null";
							}
							break;
					}
				}
				p.append(String.format("UPDATE %s SET %s \n", tableName, columnValues) + " " + where);

			}
			return p.toString();
		} catch (Exception e) {
			return "- SQL Log process fails while trying to creat an UPDATE statement for this record:  \n--SELECT * FROM " + tableName + " " + where;
		}
	}

}
