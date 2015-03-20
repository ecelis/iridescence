package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.ECareConnectorProperties;

import com.mirth.connect.connectors.file.FileWriterProperties;


public class MHL7BPartnerFTP extends X_HL7_BPartnerFTP implements
		ECareConnectorProperties {
	//interface para usar cualquier tipo de destino de manera generica
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7847401482827565362L;
	
	public static CLogger s_log = CLogger.getCLogger(MHL7BPartnerFTP.class);

	public MHL7BPartnerFTP(Properties ctx, int HL7_BPartnerFTP_ID,
			String trxName) {
		super(ctx, HL7_BPartnerFTP_ID, trxName);
	}

	public MHL7BPartnerFTP(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	public Properties getProperties() {

		

		Properties properties = new FileWriterProperties().getDefaults();
		/* el base a la version 1.8.0 de mirth */
		/*
		 * Properties properties = new Properties(); properties.put(DATATYPE,
		 * name); properties.put(FILE_SCHEME, SCHEME_FILE);
		 * properties.put(FILE_HOST, ""); properties.put(FILE_DIRECTORY, "");
		 * properties.put(FILE_NAME, ""); properties.put(FILE_ANONYMOUS, "1");
		 * properties.put(FILE_USERNAME, "anonymous");
		 * properties.put(FILE_PASSWORD, "anonymous");
		 * properties.put(FILE_SECURE_MODE, "1");
		 * properties.put(FILE_PASSIVE_MODE, "1");
		 * properties.put(FILE_VALIDATE_CONNECTION, "1");
		 * properties.put(FILE_APPEND, "1"); properties.put(FILE_CONTENTS, "");
		 * properties.put(FILE_TYPE, "0");
		 * properties.put(CONNECTOR_CHARSET_ENCODING, "DEFAULT_ENCODING");
		 * return properties;
		 */

		/* se importan de maner estatica los valores de las propiedades */

		properties.put(FileWriterProperties.FILE_SCHEME, FileWriterProperties.SCHEME_FTP);
		
		properties.put(FileWriterProperties.FILE_HOST, getFtpHost());
		properties.put(FileWriterProperties.FILE_DIRECTORY, getFtpDirectory());
		properties.put(FileWriterProperties.FILE_NAME, getFileName());

		properties.put(FileWriterProperties.FILE_ANONYMOUS, getAnonymous());
		if (getAnonymous().equals("0")){//no
			properties.put(FileWriterProperties.FILE_USERNAME, getUserName());
			properties.put(FileWriterProperties.FILE_PASSWORD, getPassword());		
		}else{
			
		}

		properties.put(FileWriterProperties.FILE_PASSIVE_MODE, getPassiveMode() );
		properties.put(FileWriterProperties.FILE_VALIDATE_CONNECTION, getValidateConnection() );
		properties.put(FileWriterProperties.FILE_CONTENTS, getTemplate());
		properties.put(FileWriterProperties.FILE_TYPE, getFileType());
		properties.put(FileWriterProperties.CONNECTOR_CHARSET_ENCODING, getEncoding());

		return properties;
		
	}
	
	
	
	public String getTransportName() {
		return FileWriterProperties.name;
	}
	
	public static MHL7BPartnerFTP getByHL7BPartner(Properties ctx, int HL7_BPartner_id, String trxName) {
		MHL7BPartnerFTP reg = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
			sql.append("SELECT * FROM HL7_BPARTNERFTP WHERE ISACTIVE = 'Y' AND HL7_BPARTNER_ID = ? ");
			
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, HL7_BPartner_id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				reg = new MHL7BPartnerFTP(ctx, rs, trxName);
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
