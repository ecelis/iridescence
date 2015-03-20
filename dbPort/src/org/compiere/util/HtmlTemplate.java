package org.compiere.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;

import net.sf.jooreports.templates.DocumentTemplate;
import net.sf.jooreports.templates.DocumentTemplateException;
import net.sf.jooreports.templates.DocumentTemplateFactory;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.compiere.model.MUser;

import com.artofsolving.jodconverter.DocumentConverter;
import com.artofsolving.jodconverter.openoffice.connection.OpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.connection.SocketOpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.converter.OpenOfficeDocumentConverter;

public class HtmlTemplate {
	private static CLogger s_log = CLogger.getCLogger(HtmlTemplate.class);
	private String htmlValue;
	private Properties ctx;

	public HtmlTemplate(String htmlValue, Properties ctx) {
		this.htmlValue = htmlValue;
		this.ctx = ctx;
		process();
	}

	public File convert(Map<String, Object> values) {
		OpenOfficeConnection connection = new SocketOpenOfficeConnection(8100);
		File html = null;
		File odt = null;
		File pdf = null;
		File res = null;
		try {
			connection.connect();

			html = File.createTempFile("tmp", ".html");
			html.deleteOnExit();

			odt = File.createTempFile("tmp", ".odt");
			odt.deleteOnExit();

			pdf = File.createTempFile("tmp", ".pdf");
			pdf.deleteOnExit();

			StringBuilder str = new StringBuilder("<html><body>");
			str.append(htmlValue);
			str.append("</body></html>");

			FileUtils.writeStringToFile(html, str.toString());

			DocumentConverter converter = new OpenOfficeDocumentConverter(connection);

			converter.convert(html, odt);

			html.delete();

			res = fillData(odt, values);

			converter.convert(res, html);

			FileUtils.writeStringToFile(html, postProcess(FileUtils.readFileToString(html)));

			converter.convert(html, pdf);

		} catch (Exception ex) {
			s_log.log(Level.FINEST, null, ex);
		} finally {
			connection.disconnect();
			if (html != null) {
				html.delete();
			}

			if (res != null) {
				res.delete();
			}

			if (odt != null) {
				odt.delete();
			}
		}

		return pdf;
	}

	private File fillData(File odt, Map<String, Object> values) {
		File ft = null;

		try {
			if (values == null) {
				values = new HashMap<String, Object>();
			}
			defaultValues(values);
			ft = File.createTempFile("tmp", ".odt");
			ft.deleteOnExit();
			DocumentTemplateFactory documentTemplateFactory = new DocumentTemplateFactory();
			DocumentTemplate template = documentTemplateFactory.getTemplate(odt);
			template.createDocument(values, new FileOutputStream(ft));
		} catch (IOException e) {
			s_log.log(Level.FINEST, null, e);
		} catch (DocumentTemplateException e) {
			s_log.log(Level.FINEST, null, e);
		}

		return ft;
	}

	private void defaultValues(Map<String, Object> values) {
		int adUserId = Env.getAD_User_ID(ctx);
		MUser user = new MUser(ctx, adUserId, null);
		values.put("user", user);
		Date date = new Date();
		values.put("shortdate", Constantes.getSDFFechaCortaHora(ctx).format(date));
		values.put("longdate", Constantes.getSdfFechaLarga(ctx).format(date));
	}

	private String postProcess(String content) {
		String[] arr1 = new String[] { "[table]", "[/table]", "[row]", "[/row]", "[column]", "[/column]" };
		String[] arr2 = new String[] { "<table>", "</table>", "<tr>", "</tr>", "<td>", "</td>" };
		return StringUtils.replaceEach(content, arr1, arr2);
	}

	private void process() {
		String[] arr1 = new String[] { "[repeat]", "[/repeat]" };
		String[] arr2 = new String[] { "[#list items as item]", "[/#list]" };
		htmlValue = StringUtils.replaceEach(htmlValue, arr1, arr2);
	}
}
