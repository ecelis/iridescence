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

public class MHL7BPartnerSMB extends X_HL7_BPartnerSMB implements ECareConnectorProperties{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6890638822080833770L;
	public static CLogger s_log = CLogger.getCLogger(MHL7BPartnerSMB.class);

	public MHL7BPartnerSMB(Properties ctx, int HL7_BPartnerSMB_ID,
			String trxName) {
		super(ctx, HL7_BPartnerSMB_ID, trxName);
	}

	public MHL7BPartnerSMB(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	public Properties getProperties() {
		
		Properties properties = new FileWriterProperties().getDefaults();
		properties.put(FileWriterProperties.FILE_SCHEME, FileWriterProperties.SCHEME_SMB);
		
		properties.put(FileWriterProperties.DATATYPE, FileWriterProperties.name);
		
		properties.put(FileWriterProperties.FILE_HOST, getSMBHost());
		properties.put(FileWriterProperties.FILE_DIRECTORY, getDirectory());
		properties.put(FileWriterProperties.FILE_NAME, getFileName());
		
		properties.put(FileWriterProperties.FILE_USERNAME, getUserName());
		properties.put(FileWriterProperties.FILE_PASSWORD, getPassword());

		properties.put(FileWriterProperties.FILE_APPEND, "1");
		properties.put(FileWriterProperties.FILE_TYPE, getFileType());
		properties.put(FileWriterProperties.CONNECTOR_CHARSET_ENCODING, getEncoding());
		properties.put(FileWriterProperties.FILE_CONTENTS, getTemplate());
		

		return properties;
	}

	public String getTransportName() {
		return FileWriterProperties.name;
	}
	
	public static MHL7BPartnerSMB getByHL7BPartner(Properties ctx, int HL7_BPartner_id, String trxName) {
		MHL7BPartnerSMB reg = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
			sql.append("SELECT * FROM HL7_BPARTNERSMB WHERE ISACTIVE = 'Y' AND HL7_BPARTNER_ID = ? ");
			
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, HL7_BPartner_id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				reg = new MHL7BPartnerSMB(ctx, rs, trxName);
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
