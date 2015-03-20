package org.compiere.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Properties;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.lang.StringUtils;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * Clase de utilerías para búsquead de información
 * 
 * @author odelarosa
 * 
 */
public class InformationUtils {
	private static final String ICD9 = "2.16.840.1.113883.6.103";
	private static final String LOINC = "2.16.840.1.113883.6.1";
	private static final String NDC = "2.16.840.1.113883.6.69";
	private static final String RXCUI = "2.16.840.1.113883.6.88";
	private static final String SNOMED = "2.16.840.1.113883.6.96";

	/**
	 * Búsqueda de información para un ICD9
	 * 
	 * @param ctx
	 *            Contexto de la Aplicación
	 * @param icd9
	 *            ICD9 del cual se requiere la información
	 * @return Respuesta de información
	 * @throws IOException
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 */
	public static InformationResponse getICD9Response(final Properties ctx, final String icd9) throws IOException, ParserConfigurationException, SAXException {
		return getResponse(ctx, ICD9, icd9);
	}

	/**
	 * Búsqueda de información para un LOINC
	 * 
	 * @param ctx
	 *            Contexto de la Aplicación
	 * @param loinc
	 *            LOINC del cual se requiere la información
	 * @return Respuesta de información
	 * @throws IOException
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 */
	public static InformationResponse getLOINCResponse(final Properties ctx, final String loinc) throws IOException, ParserConfigurationException, SAXException {
		return getResponse(ctx, LOINC, loinc);
	}

	/**
	 * Búsqueda de información para un NDC
	 * 
	 * @param ctx
	 *            Contexto de la Aplicación
	 * @param ndc
	 *            NDC del cual se requiere la información
	 * @return Respuesta de información
	 * @throws IOException
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 */
	public static InformationResponse getNDCResponse(final Properties ctx, final String ndc) throws IOException, ParserConfigurationException, SAXException {
		return getResponse(ctx, NDC, ndc);
	}

	/**
	 * Búsqueda de información
	 * 
	 * @param ctx
	 *            Contexto de la aplicación
	 * @param type
	 *            Tipo de información, puede ser {@link #NDC}, {@link #LOINC},
	 *            {@link #SNOMED}, {@link #ICD9}, {@link #RXCUI}
	 * @param key
	 *            Valor a buscar
	 * @return Respuesta de información
	 * @throws IOException
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 */
	private static InformationResponse getResponse(final Properties ctx, final String type, final String key) throws IOException, ParserConfigurationException, SAXException {
		final StringBuilder str = new StringBuilder("http://apps.nlm.nih.gov/medlineplus/services/mpconnect_service.cfm?");
		str.append("mainSearchCriteria.v.cs=");
		str.append(type);
		str.append("&mainSearchCriteria.v.c=");
		str.append(key);

		if (ctx != null && StringUtils.startsWith(Env.getAD_Language(ctx), "es_")) {
			str.append("&informationRecipient.languageCode.c=es");
		}

		final URL uri = new URL(str.toString());

		final URLConnection con = uri.openConnection();

		final InformationResponse response = read(con.getInputStream());

		return response;
	}

	/**
	 * Búsqueda de información para un RXCUI
	 * 
	 * @param ctx
	 *            Contexto de la Aplicación
	 * @param rxcui
	 *            RXCUI del cual se requiere la información
	 * @return Respuesta de información
	 * @throws IOException
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 */
	public static InformationResponse getRXCUIResponse(final Properties ctx, final String rxcui) throws IOException, ParserConfigurationException, SAXException {
		return getResponse(ctx, RXCUI, rxcui);
	}

	/**
	 * Búsqueda de información para un SNOMED
	 * 
	 * @param ctx
	 *            Contexto de la Aplicación
	 * @param snomed
	 *            SNOMED del cual se requiere la información
	 * @return Respuesta de información
	 * @throws IOException
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 */
	public static InformationResponse getSNOMEDResponse(final Properties ctx, final String snomed) throws IOException, ParserConfigurationException, SAXException {
		return getResponse(ctx, SNOMED, snomed);
	}

	public static void main(final String[] args) {
		try {
			getICD9Response(null, "250.33");
		} catch (final Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * Conversión de Información
	 * 
	 * @param xml
	 *            XML de respuesta
	 * @return Respuesta de información
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 */
	private static InformationResponse read(final InputStream xml) throws ParserConfigurationException, SAXException, IOException {
		final InformationResponse response = new InformationResponse();
		final DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		final DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		final org.w3c.dom.Document doc = dBuilder.parse(xml);

		doc.getDocumentElement().normalize();

		final NodeList title = doc.getElementsByTagName("title");
		final NodeList subtitle = doc.getElementsByTagName("subtitle");
		final NodeList updated = doc.getElementsByTagName("updated");

		response.setTitle(title.item(0).getTextContent());
		response.setSubtitle(subtitle.item(0).getTextContent());
		response.setUpdated(updated.item(0).getTextContent());

		final NodeList entrys = doc.getElementsByTagName("entry");

		if (entrys.getLength() == 0) {
			return null;
		} else {
			for (int temp = 0; temp < entrys.getLength(); temp++) {
				final InformationResource educationalResource = new InformationResource();
				final Node nNode = entrys.item(temp);

				final Element eElement = (Element) nNode;
				final NodeList url = doc.getElementsByTagName("link");

				educationalResource.setTitle(eElement.getElementsByTagName("title").item(0).getTextContent());
				educationalResource.setUrl(((Element) url.item(0)).getAttribute("href"));
				educationalResource.setSummary(eElement.getElementsByTagName("summary").item(0).getTextContent());
				educationalResource.setUpdated(eElement.getElementsByTagName("updated").item(0).getTextContent());

				response.getEducationalResources().add(educationalResource);
			}
			return response;
		}

	}
}
