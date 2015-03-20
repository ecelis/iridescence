package com.ecaresoft.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ConnectException;
import java.util.HashMap;
import java.util.Properties;
import java.util.logging.Level;

import org.apache.commons.io.IOUtils;
import org.compiere.model.MAttachmentEntry;
import org.compiere.util.CLogger;
import org.compiere.util.Env;
import org.compiere.util.Ini;
import org.compiere.util.Utilerias;

import com.artofsolving.jodconverter.DocumentConverter;
import com.artofsolving.jodconverter.openoffice.connection.OpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.connection.SocketOpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.converter.OpenOfficeDocumentConverter;

import fr.opensagres.xdocreport.core.XDocReportException;
import fr.opensagres.xdocreport.document.IXDocReport;
import fr.opensagres.xdocreport.document.registry.XDocReportRegistry;
import fr.opensagres.xdocreport.template.IContext;
import fr.opensagres.xdocreport.template.TemplateEngineKind;

/**
 * Reportes ODT a PDF
 * 
 * @author odelarosa
 * 
 */
public class ODTReporter {

	private static final CLogger LOG = CLogger.getCLogger(ODTReporter.class);

	/**
	 * Generador de reportes pdf basados en plantillas ODT
	 * 
	 * @param ctx
	 *            Contexto de la App
	 * @param repName
	 *            Nombre del Reporte
	 * @param params
	 *            Parametros del reporte
	 * @return Pdf generado o nulo en caso de error
	 */
	public static File getPdf(Properties ctx, String repName, HashMap<String, Object> params) throws EcsException {
		File file = null;

		try {
			File odt = getTemplate(ctx, repName);

			if (odt.exists()) {
				InputStream in = new FileInputStream(odt);
				IXDocReport report = XDocReportRegistry.getRegistry().loadReport(in, TemplateEngineKind.Velocity);
				IContext context = report.createContext();
				context.putMap(params);

				File tmp = File.createTempFile("report", ".odt", new File(MAttachmentEntry.getTmpDirectory()));

				OutputStream out = new FileOutputStream(tmp);

				report.process(context, out);

				file = File.createTempFile("report", ".pdf", new File(MAttachmentEntry.getTmpDirectory()));

				// connect to an OpenOffice.org instance running on port 8100
				OpenOfficeConnection connection = new SocketOpenOfficeConnection(8100);
				connection.connect();

				// convert
				DocumentConverter converter = new OpenOfficeDocumentConverter(connection);
				converter.convert(tmp, file);

				// close the connection
				connection.disconnect();
			}
		} catch (ConnectException e) {
			LOG.log(Level.SEVERE, null, e);
			file = null;

			throw new EcsException(Utilerias.getAppMsg(Env.getCtx(), "msj.servOOMalConfigurado"));
		} catch (IOException e) {
			LOG.log(Level.SEVERE, null, e);
			file = null;
		} catch (XDocReportException e) {
			LOG.log(Level.SEVERE, null, e);
			file = null;

			throw new EcsException(Utilerias.getAppMsg(Env.getCtx(), "msj.errorLeerPlantilla"));
		} catch (Exception e) {
			LOG.log(Level.SEVERE, null, e);
			file = null;
		}

		return file;
	}

	/**
	 * Obtiene el odt en caso de existir
	 * 
	 * @param ctx
	 *            Contexto de la App
	 * @param reportName
	 *            Nombre del reporte a buscar
	 * @return Template
	 */
	public static File getTemplate(Properties ctx, String reportName) {
		File odt = null;

		String env = Ini.getCompiereHome();

		String propPath = env + File.separator + "CompiereEnv.properties";

		FileInputStream fis = null;
		try {
			fis = new FileInputStream(propPath);

			Properties properties = new Properties();
			properties.load(fis);

			String path = properties.getProperty("MEDSYS_PATH_JASPER") + File.separator + Env.getContext(ctx, "#AD_Client_Value") + File.separator;

			odt = new File(path, reportName);

			if (!odt.exists()) {
				odt = new File(new File(path).getParent(), reportName);
			}
		} catch (Exception e) {
			LOG.log(Level.SEVERE, null, e);
		} finally {
			IOUtils.closeQuietly(fis);
		}

		return odt;
	}

}
