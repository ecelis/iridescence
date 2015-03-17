package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.ECareConnectorProperties;

import com.mirth.connect.connectors.smtp.SmtpSenderProperties;



public class MHL7BPartnerEmail extends X_HL7_BPartnerEmail implements
		ECareConnectorProperties {

	/**
	 * 
	 */
	private static final long serialVersionUID = -285352680274239238L;
	public static CLogger s_log = CLogger.getCLogger(MHL7BPartnerEmail.class);

	public MHL7BPartnerEmail(Properties ctx, int HL7_BPartnerEmail_ID,
			String trxName) {
		super(ctx, HL7_BPartnerEmail_ID, trxName);
	}

	public MHL7BPartnerEmail(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	public Properties getProperties() {

		Properties properties = new SmtpSenderProperties().getDefaults();		
		
		properties.put(SmtpSenderProperties.SMTP_HOST, getSMTPHost());
		properties.put(SmtpSenderProperties.SMTP_PORT, getSMTPPort());

		properties.put(SmtpSenderProperties.SMTP_USERNAME, getUserName());
		properties.put(SmtpSenderProperties.SMTP_PASSWORD, getUserPassword());		

		properties.put(SmtpSenderProperties.SMTP_TO, getMailTo());
		properties.put(SmtpSenderProperties.SMTP_FROM, getMailFrom());
		properties.put(SmtpSenderProperties.SMTP_SUBJECT, getSubject());

		properties.put(SmtpSenderProperties.SMTP_BODY, getMailBody());
		
		properties.put(SmtpSenderProperties.SMTP_SECURE,
				getSecureConnection());
//		properties.put(SmtpSenderProperties.EMAIL_CONTENT_TYPE,getContentType());		

		return properties;
		
	}

	public String getTransportName() {
		return SmtpSenderProperties.name;
	}
	
	public static MHL7BPartnerEmail getByHL7BPartner(Properties ctx, int HL7_BPartner_id, String trxName) {
		MHL7BPartnerEmail reg = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
			sql.append("SELECT * FROM HL7_BPARTNEREMAIL WHERE ISACTIVE = 'Y' AND HL7_BPARTNER_ID = ? ");
			
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, HL7_BPartner_id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				reg = new MHL7BPartnerEmail(ctx, rs, trxName);
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, "getByHL7BPartner", e);
		} finally {
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}
		return reg;
	}

}
