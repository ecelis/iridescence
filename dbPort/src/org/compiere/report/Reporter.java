package org.compiere.report;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;

import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

import org.compiere.model.MClient;
import org.compiere.model.MEXMELookupInfo;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Ini;
import org.compiere.util.WebEnv;

/**
 * Reporter
 * 
 * Based on {@link org.compiere.hospital.utils.Reporter}
 * 
 */
public class Reporter {
	/** Static Logger */
	private final CLogger log = CLogger.getCLogger(Reporter.class);

	public final static String COMPIERE_HOME = "COMPIERE_HOME";

	private String JASPERFilePath;
	@SuppressWarnings("unused")
	private String PDFFilePath;
	private String PDFFilePathAlt = null;

	private JasperReport _rep = null;
	private Map<String, Object> _params;
	private String _repName = null;
	private String env = null;
	private Properties _ctx;
	public boolean isSession = false;

	private final static String JASPER_EXTENSION = ".jasper";
	private final static String ERR_NO_GEN_REP = "Error: The report can't be generated. Detail: ";

	/**
	 * Crea un reporte con los parametros enviados y los parametros por defecto
	 * 
	 * @param params
	 *            Parametros del reporte
	 * @param repName
	 *            Nombre del reporte
	 * @param ctx
	 *            Contexto de la aplicacion
	 * @param tableName
	 *            Nombre de la tabla principal
	 */
	public Reporter(Map<String, Object> params, String repName, Properties ctx, String tableName) {
		_repName = repName;
		_ctx = ctx;

		FileInputStream fis = null;
		_params = new HashMap<String, Object>();

		try {
			env = Ini.getCompiereHome();

			String propPath = env + File.separator + "CompiereEnv.properties";

			fis = new FileInputStream(propPath);

			Properties properties = new Properties();
			properties.load(fis);
			JASPERFilePath = properties.getProperty("MEDSYS_PATH_JASPER") + File.separator + Env.getContext(_ctx, "#AD_Client_Value") + File.separator;

			properties = WebEnv.getXPT_Properties();
			PDFFilePath = env + File.separator + properties.getProperty("PDFFilePath");

			params.put("IMG", JASPERFilePath);
			params.put("SUBREPORT_FILE_NAME", JASPERFilePath);
			params.put("SUBREPORT_DIR", JASPERFilePath);
			params.put("REPORT_TIME_ZONE", Env.getTimeZone(_ctx));

			int AD_Client_ID = Env.getContextAsInt(ctx, "#AD_Client_ID");
			int AD_Org_ID = Env.getContextAsInt(ctx, "#AD_Org_ID");
			String Client = Env.getContext(ctx, "#AD_Client_Name");
			String Org = Env.getContext(ctx, "#AD_Org_Name");
			int AD_User_ID = Env.getAD_User_ID(ctx);

			if (tableName != null) {
				String str = MEXMELookupInfo.addAccessLevelSQL(ctx, "/", tableName);
				str = str.replace("/", "");
				params.put("ACCESSLEVEL", str);
			}

			params.put("CLIENT_ID", Long.valueOf(AD_Client_ID));
			params.put("ORG_ID", Long.valueOf(AD_Org_ID));
			params.put("CLIENT", Client);
			params.put("ORG", Org);
			params.put("AD_USER_ID", Integer.valueOf(AD_User_ID)); // Se cambia valor de Long por Integer para poder correr el reporte
			params.put("REPORT_LOCALE", Env.getLanguage(ctx).getLocale());
			params.put("REPORT_RESOURCE_BUNDLE", ResourceBundle.getBundle("org.compiere.ReportLabels", Env.getLanguage(ctx).getLocale()));

			_params = cleanParams(params);
			String dirName = ctx.getProperty("documentDir", ".");

			File dir = new File(dirName);
			if (!dir.exists() && !dir.mkdir()) {
				throw new IOException("The directory can not be created : ".concat(dirName));
			}
		} catch (IOException e) {
			log.log(Level.SEVERE, "Running Reporter", e);
		} finally {
			try {
				if (fis != null) {
					fis.close();
				}
			} catch (IOException e) {
				log.log(Level.SEVERE, "Running Reporter", e);
			}
		}

	}

	/**
	 * Crea un reporte con los parametros enviados y los parametros por defecto
	 * 
	 * @param params
	 *            Parametros del reporte
	 * @param repName
	 *            Nombre del reporte
	 * @param ctx
	 *            Contexto de la aplicacion
	 */
	public Reporter(Map<String, Object> params, String repName, Properties ctx) {
		this(params, repName, ctx, null);
	}

	/**
	 * Generar archivo del reporte
	 * 
	 * @param dataSource
	 *            Tipo de fuente de datos
	 * @param exporter
	 *            Tipo de Formato del archivo
	 * @return
	 * @throws Exception
	 */
	public JasperPrint generar() throws Exception {

		Connection conn = null;
		try {
			// El parametro "IMG" contiene el nombre del logo no debe
			// concatenarse en el archivo jrxml.

			MClient client = new MClient(_ctx, Env.getAD_Client_ID(_ctx), null);
			String ruta = _params.get("IMG").toString();
			if (client.getImage() != null) {
				_params.put("IMG", ruta.concat(client.getImage()));
			} else {
				_params.put("IMG", ruta.concat("logo.gif"));
			}

			// Leemos el archivo .jasper
			log.log(Level.INFO, "****** Generar pdf ****** ");

			conn = DB.createConnection(false, Connection.TRANSACTION_READ_COMMITTED);

			File jasperFile = new File(JASPERFilePath + _repName + JASPER_EXTENSION);
			
			if (!jasperFile.exists()) {
				jasperFile = new File(jasperFile.getParentFile().getParentFile().getAbsolutePath() + File.separatorChar + _repName + JASPER_EXTENSION);
				_params.put("SUBREPORT_FILE_NAME", jasperFile.getParentFile().getAbsolutePath()+File.separatorChar);
				_params.put("SUBREPORT_DIR", jasperFile.getParentFile().getAbsolutePath()+File.separatorChar);
			}
			
			String repStr = jasperFile.getPath();
			_rep = (JasperReport) JRLoader.loadObject(repStr);

			return JasperFillManager.fillReport(_rep, _params, conn);

		} catch (Exception e) {
			log.log(Level.SEVERE, "generar", e);
			throw new Exception(ERR_NO_GEN_REP + e.getMessage());
		} finally {
			if(conn != null){
				conn.close();
			}
		}
	}

	public String getPDFFilePathAlt() {
		return PDFFilePathAlt;
	}

	public void setPDFFilePathAlt(String filePathAlt) {
		PDFFilePathAlt = filePathAlt;
	}

	/**
	 * Removes all null values from the map.
	 * 
	 * @param params
	 *            The map with the key/value pairs.
	 * @return a map without null values.
	 */
	private Map<String, Object> cleanParams(Map<String, Object> params) {
		// Verificando parametros
		List<String> aRemover = new ArrayList<String>();
		log.log(Level.FINEST, "Reporter - Parameters for report : " + _repName);

		Iterator<Entry<String, Object>> itEntries = params.entrySet().iterator();
		while (itEntries.hasNext()) {
			Entry<String, Object> element = itEntries.next();
			log.log(Level.FINEST, element.getKey() + " = " + element.getValue());

			if (element.getValue() == null) {
				aRemover.add(element.getKey());
			}
		}

		Iterator<String> itParams = aRemover.iterator();
		while (itParams.hasNext()) {
			params.remove(itParams.next());
		}

		return params;
	}
	
}