/******************************************************************************
 * Product: Compiere ERP & CRM Smart Business Solution                        *
 * Copyright (C) 1999-2006 ComPiere, Inc. All Rights Reserved.                *
 * This program is free software; you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * ComPiere, Inc., 2620 Augustine Dr. #245, Santa Clara, CA 95054, USA        *
 * or via info@compiere.org or http://www.compiere.org/license.html           *
 *****************************************************************************/
package org.compiere.util;

import java.io.File;
import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Properties;
import java.util.logging.Level;

import javax.activation.DataSource;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.compiere.model.MClient;

/**
 * EMail Object. Resources: http://java.sun.com/products/javamail/index.html
 * http://java.sun.com/products/javamail/FAQ.html
 * 
 * <p>
 * When I try to send a message, I get javax.mail.SendFailedException: 550
 * Unable to relay for my-address <br>
 * This is an error reply from your SMTP mail server. It indicates that your
 * mail server is not configured to allow you to send mail through it.
 * 
 * @author Jorg Janke
 * @version $Id: EMail.java,v 1.4 2006/07/30 00:54:35 jjanke Exp $
 */
public final class EMail implements Serializable {
	// use in serverbean
	public final static String HTML_MAIL_MARKER = "ContentType=text/html;";
	/** Logger */
	protected static CLogger log = CLogger.getCLogger(EMail.class);
	/** Mail Sent OK Status */
	public static final String SENT_OK = "OK";
	private static final long serialVersionUID = -6071480404458472885L;

	private boolean isSSLConnection = true;
	/** Attachments */
	private ArrayList<Object> m_attachments;
	/** BCC Addresses */
	private ArrayList<String> m_bcc;
	/** CC Addresses */
	private ArrayList<String> m_cc;
	/** From Address */
	private String m_from;
	/** Mail HTML Message */
	private String m_messageHTML;
	/** Mail Plain Message */
	private String m_messageText;
	/** Message */
	private MimeMessage m_msg = null;
	/** Reply To Address */
	private InternetAddress m_replyTo;
	/** Send result Message */
	private String m_sentMsg = null;
	/** Mail SMTP Server */
	private String m_smtpHost;
	/** Mail SMTP Server Port */
	private int m_smtpPort = 465;
	private int m_sslPort = 465;
	/** Mail Subject */
	private String m_subject;
	/** To Address */
	private ArrayList<String> m_to;
	/** Info Valid */
	private boolean m_valid = false;
	private String password;
	private String user;

	/**
	 * Full Constructor
	 * 
	 * @param client
	 *            the client
	 * @param from
	 *            Sender's EMail address
	 * @param to
	 *            Recipient EMail address
	 * @param subject
	 *            Subject of message
	 * @param message
	 *            The message
	 */
	public EMail(MClient client, String from, String to, String subject, String message) {
		this(client.getCtx(), client.getSMTPHost(), from, to, subject, message);
	} // EMail

	/**
	 * Full Constructor
	 * 
	 * @param client
	 *            the client
	 * @param from
	 *            Sender's EMail address
	 * @param to
	 *            Recipient EMail address
	 * @param subject
	 *            Subject of message
	 * @param message
	 *            The message
	 * @param html
	 */
	public EMail(MClient client, String from, String to, String subject, String message, boolean html) {
		this(client.getCtx(), client.getSMTPHost(), from, to, subject, message, html);
	} // EMail

	/**
	 * Full Constructor
	 * 
	 * @param ctx
	 *            context
	 * @param smtpHost
	 *            The mail server
	 * @param from
	 *            Sender's EMail address
	 * @param to
	 *            Recipient EMail address
	 * @param subject
	 *            Subject of message
	 * @param message
	 *            The message
	 */
	public EMail(Properties ctx, String smtpHost, String from, String to, String subject, String message) {
		this(ctx, smtpHost, from, to, subject, message, false);
	}

	/**
	 * Full Constructor
	 * 
	 * @param ctx
	 *            context
	 * @param smtpHost
	 *            The mail server
	 * @param from
	 *            Sender's EMail address
	 * @param to
	 *            Recipient EMail address
	 * @param subject
	 *            Subject of message
	 * @param message
	 *            The message
	 * @param html
	 *            html email
	 */
	public EMail(Properties ctx, String smtpHost, String from, String to, String subject, String message, boolean html) {
		setSmtpHost(smtpHost);
		setFrom(from);
		addTo(to);
		
		if (subject == null || subject.length() == 0)
			setSubject("."); // pass validation
		else
			setSubject(subject);
		if (message != null && message.length() > 0) {
			if (html)
				setMessageHTML(subject, message);
			else
				setMessageText(message);
		}
		m_valid = isValid(true);
	} // EMail

	/**
	 * Add attachment. (converted to ByteArrayDataSource)
	 * 
	 * @param data
	 *            data
	 * @param type
	 *            MIME type
	 * @param name
	 *            name of attachment
	 */
	public void addAttachment(byte[] data, String type, String name) {
		ByteArrayDataSource byteArray = new ByteArrayDataSource(data, type).setName(name);
		addAttachment(byteArray);
	} // addAttachment

	/**
	 * Add arbitary Attachment
	 * 
	 * @param dataSource
	 *            content to attach
	 */
	public void addAttachment(DataSource dataSource) {
		if (dataSource == null)
			return;
		if (m_attachments == null)
			m_attachments = new ArrayList<Object>();
		m_attachments.add(dataSource);
	} // addAttachment

	/**
	 * Add file Attachment
	 * 
	 * @param file
	 *            file to attach
	 */
	public void addAttachment(File file) {
		if (file == null)
			return;
		if (m_attachments == null)
			m_attachments = new ArrayList<Object>();
		m_attachments.add(file);
	} // addAttachment

	/**
	 * Add url based file Attachment
	 * 
	 * @param url
	 *            url content to attach
	 */
	public void addAttachment(URL url) {
		if (url == null)
			return;
		if (m_attachments == null)
			m_attachments = new ArrayList<Object>();
		m_attachments.add(url);
	} // addAttachment

	/**
	 * Add a collection of attachments
	 * 
	 * @param files
	 *            collection of files
	 */
	public void addAttachments(Collection<File> files) {
		if (files == null || files.size() == 0)
			return;
		for (File f : files) {
			addAttachment(f);
		}
	}

	/** Getter/Setter ********************************************************/

	/**
	 * Add BCC Recipient
	 * 
	 * @param newBcc
	 *            EMail cc Recipient
	 * @return true if valid
	 */
	public boolean addBcc(String newBcc) {
		if (newBcc == null || newBcc.length() == 0)
			return false;
		if (m_bcc == null)
			m_bcc = new ArrayList<String>();
		m_bcc.add(newBcc);
		return true;
	} // addBcc

	/**
	 * Add CC Recipient
	 * 
	 * @param newCc
	 *            EMail cc Recipient
	 * @return true if valid
	 */
	public boolean addCc(String newCc) {
		if (newCc == null || newCc.length() == 0)
			return false;
		if (m_cc == null)
			m_cc = new ArrayList<String>();
		m_cc.add(newCc);
		return true;
	} // addCc

	/**
	 * Add To Recipient
	 * 
	 * @param newTo
	 *            Recipient's email address
	 * @return true if valid
	 */
	public boolean addTo(String newTo) {
		if (newTo == null || newTo.length() == 0) {
			m_valid = false;
			return false;
		}
		if (m_to == null)
			m_to = new ArrayList<String>();
		m_to.add(newTo);
		return true;
	} // addTo

	private void checkAttachments(HtmlEmail email) {
		if (m_attachments != null) {
			for (int i = 0; i < m_attachments.size(); i++) {
				Object attachment = m_attachments.get(i);

				EmailAttachment att = new EmailAttachment();

				if (attachment instanceof File) {
					File file = (File) attachment;
					att.setPath(file.getPath());
				} else if (attachment instanceof URL) {
					URL url = (URL) attachment;
					att.setURL(url);
				} else if (attachment instanceof DataSource) {

				} else {
					log.log(Level.WARNING, "Attachement type unknown: " + attachment);
					continue;
				}

				att.setDisposition(EmailAttachment.ATTACHMENT);

				try {
					email.attach(att);
				} catch (EmailException e) {
					log.log(Level.SEVERE, null, e);
				}
			}
		}
	}

	/**
	 * Create Authenticator for User
	 * 
	 * @param username
	 *            user name
	 * @param password
	 *            user password
	 */
	public void createAuthenticator(String username, String password) {
		if (username == null || password == null) {
			log.warning("Ignored - " + username + "/" + password);
		} else {
			this.user = username;
			this.password = password;
		}
	} // createAuthenticator

	/**
	 * Dump Message Info
	 */
	private void dumpMessage() {
		if (m_msg == null)
			return;
		try {
			Enumeration<?> e = m_msg.getAllHeaderLines();
			while (e.hasMoreElements())
				log.fine("- " + e.nextElement());
		} catch (MessagingException ex) {
			log.log(Level.WARNING, m_msg.toString(), ex);
		}
	} // dumpMessage

	/**
	 * Get Sender
	 * 
	 * @return Sender's internet address
	 */
	public String getFrom() {
		return m_from;
	} // getFrom

	/**
	 * Get MIME String Message - line ending with CRLF.
	 * 
	 * @return message
	 */
	public String getMessageCRLF() {
		if (m_messageText == null)
			return "";
		char[] chars = m_messageText.toCharArray();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < chars.length; i++) {
			char c = chars[i];
			if (c == '\n') {
				int previous = i - 1;
				if (previous >= 0 && chars[previous] == '\r')
					sb.append(c);
				else
					sb.append("\r\n");
			} else
				sb.append(c);
		}
		// log.fine("IN  " + m_messageText);
		// log.fine("OUT " + sb);

		return sb.toString();
	} // getMessageCRLF

	/**
	 * Get HTML Message
	 * 
	 * @return message
	 */
	public String getMessageHTML() {
		return m_messageHTML;
	} // getMessageHTML

	/**
	 * Get Message ID or null
	 * 
	 * @return Message ID e.g.
	 *         <20030130004739.15377.qmail@web13506.mail.yahoo.com>
	 *         <25699763.1043887247538.JavaMail.jjanke@main>
	 */
	public String getMessageID() {
		try {
			if (m_msg != null)
				return m_msg.getMessageID();
		} catch (MessagingException ex) {
			log.log(Level.SEVERE, "", ex);
		}
		return null;
	} // getMessageID

	/**
	 * Get the message directly
	 * 
	 * @return mail message
	 */
	protected MimeMessage getMimeMessage() {
		return m_msg;
	} // getMessage

	/**
	 * Get Reply To
	 * 
	 * @return Reoly To internet address
	 */
	public InternetAddress getReplyTo() {
		return m_replyTo;
	} // getReplyTo

	/**
	 * Get Send Result Msg
	 * 
	 * @return msg
	 */
	public String getSentMsg() {
		return m_sentMsg;
	} // getSentMsg

	/**
	 * Get Mail Server name or address
	 * 
	 * @return mail server
	 */
	public String getSmtpHost() {
		return m_smtpHost;
	} // getSmtpHosr

	public int getSMTPPort() {
		return m_smtpPort;
	}

	public int getSSLPort() {
		return m_sslPort;
	}

	/**
	 * Get Subject
	 * 
	 * @return subject
	 */
	public String getSubject() {
		return m_subject;
	} // getSubject

	/**
	 * Get Recipient
	 * 
	 * @return Recipient's internet address
	 */
	public String getTo() {
		if (m_to == null || m_to.size() == 0)
			return null;
		String ia = m_to.get(0);
		return ia;
	} // getTo

	/**
	 * Was sending the Msg OK
	 * 
	 * @return msg == OK
	 */
	public boolean isSentOK() {
		return m_sentMsg != null && SENT_OK.equals(m_sentMsg);
	} // isSentOK

	public boolean isSSLConnection() {
		return isSSLConnection;
	}

	/**
	 * Is Info valid to send EMail
	 * 
	 * @return true if email is valid and can be sent
	 */
	public boolean isValid() {
		return m_valid;
	} // isValid

	/**
	 * Re-Check Info if valid to send EMail
	 * 
	 * @param recheck
	 *            if true check main variables
	 * @return true if email is valid and can be sent
	 */
	public boolean isValid(boolean recheck) {
		if (!recheck)
			return m_valid;

		// From
		if (StringUtils.isBlank(m_from)) {
			log.warning("From is invalid=" + m_from);
			return false;
		}
		// To
		if (m_to == null || m_to.isEmpty()) {
			log.warning("No To");
			return false;
		}

		// Host
		if (m_smtpHost == null || m_smtpHost.length() == 0) {
			log.warning("SMTP Host is invalid" + m_smtpHost);
			return false;
		}

		// Subject
		if (m_subject == null || m_subject.length() == 0) {
			log.warning("Subject is invalid=" + m_subject);
			return false;
		}
		return true;
	} // isValid

	/**
	 * Send Mail direct
	 * 
	 * @return OK or error message
	 */
	public String send() {
		log.info("(" + m_smtpHost + ") " + m_from + " -> " + m_to);
		m_sentMsg = null;
		//
		if (!isValid(true)) {
			m_sentMsg = "Invalid Data";
			return m_sentMsg;
		}

		try {
			HtmlEmail email = new HtmlEmail();

			email.setHostName(m_smtpHost);
			email.setSmtpPort(m_smtpPort);
			email.setAuthenticator(new DefaultAuthenticator(user, password));

			if (isSSLConnection) {
				email.setSSLOnConnect(true);
				email.setSslSmtpPort(Integer.toString(getSSLPort() > 0 ? getSSLPort() : 465));
			}

			email.setFrom(getFrom());
			email.setSubject(m_subject);

			if (StringUtils.isNotBlank(m_messageHTML)) {
				email.setHtmlMsg(m_messageHTML);
			} else {
				email.setMsg(m_messageText);
			}

			for (String to : m_to) {
				email.addTo(to);
			}

			if (m_bcc != null) {
				for (String bcc : m_bcc) {
					email.addBcc(bcc);
				}
			}

			if (m_cc != null) {
				for (String cc : m_cc) {
					email.addCc(cc);
				}
			}

			checkAttachments(email);

			//email.setDebug(true);
			email.send();

			m_msg = email.getMimeMessage();

		} catch (EmailException ex) {
			log.log(Level.SEVERE, null, ex);
			m_sentMsg = ex.getLocalizedMessage();
		}

		if (CLogMgt.isLevelFinest()) {
			dumpMessage();
		}
		m_sentMsg = StringUtils.defaultIfEmpty(m_sentMsg, SENT_OK);
		return m_sentMsg;
	} // send

	/**
	 * Set Sender
	 * 
	 * @param newFrom
	 *            Sender's email address
	 */
	public void setFrom(String newFrom) {
		if (newFrom == null) {
			m_valid = false;
			return;
		}
		m_from = newFrom;
	} // setFrom

	/**
	 * Set HTML Message
	 * 
	 * @param html
	 *            message
	 */
	public void setMessageHTML(String html) {
		if (html == null || html.length() == 0)
			m_valid = false;
		else {
			m_messageHTML = html;
			if (!m_messageHTML.endsWith("\n"))
				m_messageHTML += "\n";
		}
	} // setMessageHTML

	/**
	 * Set HTML Message
	 * 
	 * @param subject
	 *            subject repeated in message as H2
	 * @param message
	 *            message
	 */
	public void setMessageHTML(String subject, String message) {
		m_subject = subject;
		StringBuffer sb = new StringBuffer("<HTML>\n").append("<HEAD>\n").append("<TITLE>\n").append(subject + "\n").append("</TITLE>\n").append("</HEAD>\n");
		sb.append("<BODY>\n").append("<H2>" + subject + "</H2>" + "\n").append(message).append("\n").append("</BODY>\n");
		sb.append("</HTML>\n");
		m_messageHTML = sb.toString();
	} // setMessageHTML

	/**
	 * Set Message
	 * 
	 * @param newMessage
	 *            message
	 */
	public void setMessageText(String newMessage) {
		if (newMessage == null || newMessage.length() == 0)
			m_valid = false;
		else {
			m_messageText = newMessage;
		}
	} // setMessage

	/**
	 * Set Reply to Address
	 * 
	 * @param newTo
	 *            email address
	 * @return true if valid
	 */
	public boolean setReplyTo(String newTo) {
		if (newTo == null || newTo.length() == 0)
			return false;
		InternetAddress ia = null;
		try {
			ia = new InternetAddress(newTo, true);
		} catch (Exception e) {
			log.log(Level.WARNING, newTo + ": " + e.toString());
			return false;
		}
		m_replyTo = ia;
		return true;
	} // setReplyTo

	/**************************************************************************
	 * Set SMTP Host or address
	 * 
	 * @param newSmtpHost
	 *            Mail server
	 */
	public void setSmtpHost(String newSmtpHost) {
		if (newSmtpHost == null || newSmtpHost.length() == 0)
			m_valid = false;
		else
			m_smtpHost = newSmtpHost;
	} // setSMTPHost

	public void setSMTPPort(int m_smtpPort) {
		this.m_smtpPort = m_smtpPort;
	}

	public void setSSLConnection(boolean isSSLConnection) {
		this.isSSLConnection = isSSLConnection;
	}

	public void setSSLPort(int sslPort) {
		this.m_sslPort = sslPort;
	}

	/**************************************************************************
	 * Set Subject
	 * 
	 * @param newSubject
	 *            Subject
	 */
	public void setSubject(String newSubject) {
		if (newSubject == null || newSubject.length() == 0)
			m_valid = false;
		else
			m_subject = newSubject;
	} // setSubject

	/**
	 * String Representation
	 * 
	 * @return info
	 */
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer("EMail[");
		sb.append("From:").append(m_from).append(",To:").append(getTo()).append(",Subject=").append(getSubject()).append("]");
		return sb.toString();
	} // toString

} // EMail
