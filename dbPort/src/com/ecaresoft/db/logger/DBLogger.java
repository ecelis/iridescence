package com.ecaresoft.db.logger;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.lang.StringUtils;
import org.compiere.model.GridTable;
import org.compiere.model.PO;
import org.compiere.model.X_AD_Column;
import org.compiere.model.X_AD_Element;
import org.compiere.model.X_AD_Field;
import org.compiere.model.X_AD_Menu;
import org.compiere.model.X_AD_Process;
import org.compiere.model.X_AD_Process_Para;
import org.compiere.model.X_AD_Ref_List;
import org.compiere.model.X_AD_Ref_Table;
import org.compiere.model.X_AD_Reference;
import org.compiere.model.X_AD_Tab;
import org.compiere.model.X_AD_Table;
import org.compiere.model.X_AD_TreeNodeMM;
import org.compiere.model.X_AD_Window;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Ini;

/**
 * 
 * SOLO FUNCIONA CUANDO LA APLICACION ESTA EN MODO DESARROLLO {@link Ini#P_APPLICATION_MODE} 
 * <br/><br/>
 * Clase encargada de generar un archivo sql en la carpeta "Compiere2/utils/SQL log" que contendra todas las sentencias INSERT o UPDATE
 * que se ejecuten sobre las tablas contenidas en el arreglo {@link DBLogger#TABLES}, si el arreglo esta vacio no hara ningun filtro de tablas.
 * <br/><br/>
 * NOTA. Se agregaran al registro solo las sentencias pasen por {@link PO} o {@link DB#executeUpdateEx}, {@link DB#executeUpdate} o bien
 * cambios a traducciones generados manualmente en {@link GridTable#dataSave(boolean)}
 * @author pmendoza
 *
 */
public class DBLogger {
	static private FileHandler		fileTxt;
	static private SQLLogFormat	formatterTxt;
	private static final CLogger C_LOGGER = CLogger.getCLogger(DBLogger.class);
	private static Logger			LOGGER	= getLogger();
	public static final int INSERT = 0;
	public static final int UPDATE = 1;
	private static String TRL = "_TRL";
	private static final boolean isDeveloperMode = Ini.APP_MODE_DEV.equalsIgnoreCase(Ini.getProperty(Ini.P_APPLICATION_MODE));
	private static final String[] TABLES = new String[]{
		X_AD_Element.Table_Name, 
		X_AD_Element.Table_Name + TRL,
		X_AD_Column.Table_Name,
		X_AD_Column.Table_Name + TRL,
		X_AD_Table.Table_Name,
		X_AD_Table.Table_Name + TRL,
		X_AD_Window.Table_Name,
		X_AD_Window.Table_Name + TRL,
		X_AD_Field.Table_Name,
		X_AD_Field.Table_Name + TRL,
		X_AD_Process.Table_Name,
		X_AD_Process.Table_Name + TRL,
		X_AD_Process_Para.Table_Name,
		X_AD_Process_Para.Table_Name + TRL,
		X_AD_Menu.Table_Name,
		X_AD_Menu.Table_Name + TRL,
		X_AD_Tab.Table_Name,
		X_AD_Tab.Table_Name + TRL,
		X_AD_TreeNodeMM.Table_Name,
		X_AD_TreeNodeMM.Table_Name + TRL,
		X_AD_Ref_List.Table_Name,
		X_AD_Ref_List.Table_Name + TRL,
		X_AD_Reference.Table_Name,
		X_AD_Reference.Table_Name + TRL,
		X_AD_Ref_Table.Table_Name,
		X_AD_Ref_Table.Table_Name + TRL		
	};
	
	private static final String FOLDER_NAME = "SQL log";
	
	/**
	 * Regresa la unica instancia de logger
	 * @return
	 */
	private static Logger getLogger() {
		try {
			Logger logger = Logger.getLogger("sql.logger");
			Handler[] handlers = logger.getHandlers();			 
			for(Handler h : handlers) {
			  if(h instanceof FileHandler){
			    logger.removeHandler(h);
			  }
			}
			logger.setLevel(Level.FINE);
			String fileName = genereteFileName();
			
			fileTxt = new FileHandler(fileName + ".sql", true);
			formatterTxt = new SQLLogFormat();
			fileTxt.setFormatter(formatterTxt);
			logger.addHandler(fileTxt);
			
			return logger;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * SOLO FUNCIONA CUANDO LA APLICACION ESTA EN MODO DESARROLLO {@link Ini#P_APPLICATION_MODE} 
	 * <br/><br/>
	 * Agrega al alog SQL la sentencia enviada de parametro
	 * @param sql cadena a imprimir
	 */
	public static void log(String sql) {
		if(isDeveloperMode){
			if(StringUtils.isBlank(sql)){
				return;
			}
			try{
				if(needLog(sql)){
					LOGGER.log(Level.FINE, sql);
				}
			}catch(Exception e){
				LOGGER.log(Level.FINE, "--SQL Logger process fails while logging this statments: " + sql);
				C_LOGGER.log(Level.SEVERE, "SQL Logger process fails while logging this statments: " + sql, e);
			}
		}
	}
	
	/**
	 * SOLO FUNCIONA CUANDO LA APLICACION ESTA EN MODO DESARROLLO {@link Ini#P_APPLICATION_MODE} 
	 * <br/><br/>
	 * Hace una consulta SQL de la forma <b/>SELECT * From <i>tableName</i> WHERE <i>where</i></b> y a partir del primer resultado de esa consulta
	 * general el codigo SQL ya sea para el insert o update, despues lo agrega agregarlo al log.
	 * 
	 * 
	 * @param type
	 * @param tableName
	 * @param where
	 */
	public static void logForcedTRL(int type, String tableName, String where) {
		if(isDeveloperMode){
			if(StringUtils.isBlank(tableName) || !tableName.toUpperCase().endsWith(TRL)){
				return;
			}
			if(TABLES.length > 0){
				for(String table : TABLES){
					if(table.toUpperCase().equals(tableName.toUpperCase())){
						String warningMsg = "--This statement was created manually \n";
						if(type == INSERT){
							LOGGER.log(Level.FINE, warningMsg + GenerateStatement.generateInsertStatements(tableName, where ));
						}else if(type == UPDATE){
							LOGGER.log(Level.FINE, warningMsg + GenerateStatement.generateUpdateStatements(tableName, where ));
						}
					}
				}
			}
		}
	}
	
	public static void test(){
		LOGGER.log(Level.FINE, GenerateStatement.generateUpdateStatements("AD_Table_Trl", "Where AD_Table_ID=717 AND AD_Language='es_MX'" ));
	}
	
	/**
	 * Verifica que la sentencia SQL se ejecuta sobre alguna de las tablas del arreglo {@link DBLogger#TABLES}
	 * @param sql
	 * @return true si arreglo {@link DBLogger#TABLES} esta vacio o si la sentencia se ejecuta en alguna de sus tablas
	 */
	private static boolean needLog(String sql){
		if(TABLES.length > 0){
			if(sql.contains("INSERT INTO")){
				String tablename = sql.trim().split("INSERT INTO")[1].trim().split(" ")[0].toUpperCase();
				for(String table : TABLES){
					if(table.toUpperCase().equals(tablename)){
						return true;
					}
				}
			}else if(sql.contains("UPDATE")){
				String tablename = sql.trim().split("UPDATE")[1].trim().split(" ")[0].toUpperCase();
				for(String table : TABLES){
					if(table.toUpperCase().equals(tablename)){
						return true;
					}
				}
			}			
			return false;
		}else{
			return true;			
		}
	}
	
	/**
	 * Genera el nombre del archivo de log. Ejemplo "log 2012-11-29 (0)"
	 * @return
	 */
	private static String genereteFileName() {		
		SimpleDateFormat date_format = new SimpleDateFormat("yyyy-MM-dd");
		Date resultdate = new Date(Calendar.getInstance().getTimeInMillis());
		String fileName = "log " + date_format.format(resultdate);
		int count = initSQLLogFolder(fileName);
		return FOLDER_NAME + "/" + fileName + " (" + (count) + ")";
	}
	
	/**
	 * Verifica si la carpeta SQL Log existe, sino la crea
	 * @param fileName
	 * @return Numero de archivos sql en la carpeta log con la fecha de hoy
	 */
	private static int initSQLLogFolder(String fileName) {
		int fileCount = 0;
	    try {
	    	File homeLoggingDir = new File (FOLDER_NAME);
	        if (!homeLoggingDir.exists() ) {
	            homeLoggingDir.mkdirs();
	        }
	        for(File fileLog : homeLoggingDir.listFiles()){
	        	if(fileLog.getName().startsWith(fileName) && fileLog.getName().endsWith(".sql")){
	        		fileCount++;
	        	}
	        }
	    } catch(Exception e) {
	        e.printStackTrace();
	    }
	    return fileCount;
	}

}
