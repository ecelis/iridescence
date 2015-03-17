package org.compiere.util;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.Properties;
import java.util.logging.Level;

import org.apache.commons.io.FileUtils;
import org.compiere.model.MClient;

import com.ecaresoft.apps.secure.CryptoUtils;


public class UtilsDbPort {

	private static CLogger log = CLogger.getCLogger(UtilsDbPort.class);

	/**
	 * Metodo para enviar correos
	 *
	 * @param ctx
	 *            Contexto
	 * @param message
	 *            Mensaje
	 * @param isHtml
	 *            Es HTML
	 * @param subject
	 *            Asunto
	 * @param requestMail
	 *            Destinatario
	 * @return Exitoso o no
	 */
	public static boolean sendMail(Properties ctx, String message, boolean isHtml, String subject, String requestMail) {
		return sendMail(ctx, Env.getAD_Client_ID(ctx), message, isHtml, subject, requestMail, null);
	}

	public static boolean sendMailNoClient(Properties ctx, int clientID, String message, boolean isHtml, String subject, String requestMail) {
		return sendMail(ctx, clientID, message, isHtml, subject, requestMail, null);
	}

	/**
	 * Encripta texto
	 *
	 * @param texto
	 *            Texto a encriptar
	 * @return Texto encriptado
	 */
	public static String encrypt(String texto) {
//		try {
//			ContenidoLicencia cont = new ContenidoLicencia();
//			if (cont != null) {
//				//texto = org.compiere.apps.secure.Utils.encriptar(texto);
//			}
//		} catch (Exception e) {
//			log.log(Level.SEVERE, "", e);
//		}
//		return texto;

		return CryptoUtils.encrypt(texto);
	}

	/**
	 * Actualiza los datos del xml de FDB
	 *
	 * @param dbUrl
	 *            Url de la base
	 * @param user
	 *            Usuario de la base
	 * @param password
	 *            Usuario de la base
	 * @return
	 */
	public static boolean saveFDB(String dbUrl, String user, String password) {
		boolean retValue = false;
		StringBuilder env = new StringBuilder(Ini.getCompiereHome());
		env.append(File.separatorChar).append("utils").append(File.separatorChar);
		Connection jdbcConnection = null;
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			jdbcConnection = DriverManager.getConnection(dbUrl, user, password);
			if (jdbcConnection != null) {
				String texto = FileUtils.readFileToString(new File(env.toString() + "dataconnectionsTemplate.xml"));
				MessageFormat formatter = new MessageFormat(texto);
				String newFile = formatter.format(new String[] { dbUrl, user, password });
				FileUtils.writeStringToFile(new File(env.toString() + "dataconnections.xml"), newFile);
				retValue = true;
			}
		} catch (IOException e) {
			log.log(Level.SEVERE, "", e);
			return false;
		} catch (ClassNotFoundException e) {
			log.log(Level.SEVERE, "", e);
			return false;
		} catch (SQLException e) {
			log.log(Level.SEVERE, "", e);
			return false;
		} finally {
			if (jdbcConnection != null) {
				try {
					jdbcConnection.close();
				} catch (SQLException e) {
					jdbcConnection = null;
				}
			}
			jdbcConnection = null;
		}
		return retValue;
	}

	public static boolean sendMail(Properties ctx, String message, boolean isHtml, String subject, String requestMail, byte[] byteArray, String type,
		String name) {
		if (message == null || subject == null || requestMail == null) {
			return false;
		}
		try {
			// el cliente, para los datos del servidor
			final MClient client = new MClient(ctx, Env.getAD_Client_ID(ctx), null);
			final EMail email = new EMail(client, client.getRequestUser(), requestMail, subject, message, isHtml);
			email.setSSLConnection(client.isSSLConnection());
			email.setSSLPort(client.getSSLPort());
			if (byteArray != null && byteArray.length > 0) {
				email.addAttachment(byteArray, type, name);
			}
			if (client.isSmtpAuthorization()) {
				email.createAuthenticator(client.getRequestUser(), client.getRequestUserPW());
			}
			return EMail.SENT_OK.equals(email.send());
		}
		catch (Exception e) {
			log.log(Level.SEVERE, "", e);
			return false;
		}
	}

	/**
	 * Send email
	 * @param ctx
	 * @param message
	 * @param isHtml
	 * @param subject
	 * @param requestMail
	 * @param file
	 * @return
	 */
	public static boolean sendMail(Properties ctx, String message, boolean isHtml, String subject, String requestMail, File file) {
		return sendMail(ctx, Env.getAD_Client_ID(ctx), message, isHtml, subject, requestMail, file);
	}

	/**
	 * Send email
	 * @param ctx
	 * @param message
	 * @param isHtml
	 * @param subject
	 * @param requestMail
	 * @param file
	 * @return
	 */
	public static boolean sendMail(Properties ctx, int clientId, String message, boolean isHtml, String subject, String requestMail, File file) {
		if (message == null || subject == null || requestMail == null) {
			return false;
		}
		try {
			// el cliente, para los datos del servidor
			final MClient client = new MClient(ctx, clientId, null);
			final EMail email = new EMail(client, client.getRequestUser(), requestMail, subject, message, isHtml);
			email.setSSLConnection(client.isSSLConnection());
			email.setSSLPort(client.getSSLPort());
			email.addAttachment(file);
			if (client.isSmtpAuthorization()) {
				email.createAuthenticator(client.getRequestUser(), client.getRequestUserPW());
			}
			return EMail.SENT_OK.equals(email.send());
		}
		catch (Exception e) {
			log.log(Level.SEVERE, "", e);
			return false;
		}
	}
}
