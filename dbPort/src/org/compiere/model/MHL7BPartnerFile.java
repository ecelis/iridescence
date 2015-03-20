package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.ECareConnectorProperties;

import com.mirth.connect.connectors.file.FileReaderProperties;
import com.mirth.connect.connectors.file.FileWriterProperties;

public class MHL7BPartnerFile extends X_HL7_BPartnerFile implements
		ECareConnectorProperties {

	/**
	 * 
	 */
	private static final long serialVersionUID = 198341717424524625L;
	public static CLogger s_log = CLogger.getCLogger(MHL7BPartnerFile.class);
	
	public MHL7BPartnerFile(Properties ctx, int HL7_BPartnerFile_ID,
			String trxName) {
		super(ctx, HL7_BPartnerFile_ID, trxName);
		
	}

	public MHL7BPartnerFile(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		
	}

	@Override
	public Properties getProperties() {
		Properties props = null;
		if (isInbound()) {
			props = new FileReaderProperties().getDefaults();
			props.put(FileReaderProperties.FILE_SCHEME, FileReaderProperties.SCHEME_FILE);
			
			props.put(FileReaderProperties.FILE_PASSWORD, getPassword());			
			props.put(FileReaderProperties.DATATYPE, FileReaderProperties.name);
			props.put(FileReaderProperties.FILE_HOST, getDirectory());
			//props.put(FileReaderProperties.FILE_DIRECTORY, getDirectory());
			
			props.put(FileReaderProperties.FILE_ANONYMOUS, getAnonymous());
			props.put(FileReaderProperties.FILE_FILTER, getFileName());
			
			
			props.put(FileReaderProperties.FILE_USERNAME, getUserName());
			
			props.put(FileReaderProperties.CONNECTOR_CHARSET_ENCODING, getEncoding());
			props.put(FileReaderProperties.FILE_TYPE,getFileType());
			
			
		} else {
			props = new FileWriterProperties().getDefaults();
			
			props.put(FileWriterProperties.DATATYPE,
					FileWriterProperties.name);
			props.put(FileWriterProperties.FILE_SCHEME,
					FileWriterProperties.SCHEME_FILE);

			props.put(FileWriterProperties.FILE_HOST,  getDirectory());
			props.put(FileWriterProperties.FILE_DIRECTORY, getDirectory());

			props.put(FileWriterProperties.FILE_NAME, getFileName());

			props.put(FileWriterProperties.FILE_USERNAME, getUserName());
			props.put(FileWriterProperties.FILE_PASSWORD, getPassword());

			props.put(FileWriterProperties.FILE_APPEND, getAppendToFile());
			props.put(FileWriterProperties.FILE_CONTENTS, getTemplate());
			props.put(FileWriterProperties.FILE_TYPE, getFileType());
			props.put(FileWriterProperties.CONNECTOR_CHARSET_ENCODING,
					getEncoding());
			
		}

		return props;
	}

	@Override
	public String getPassword() {
		if (super.getPassword() == null){
			return "";
		}
		return super.getPassword();
	}

	@Override
	public String getUserName() {
		if (super.getUserName() == null){
			return "";
		}
		return super.getUserName();
	}

	@Override
	public String getTransportName() {
		if(isInbound()){
			return FileReaderProperties.name;
		}else{
			return FileWriterProperties.name;
		}
	}

	public static MHL7BPartnerFile getByHL7BPartner(Properties ctx, int HL7_BPartner_id, String trxName) {
		MHL7BPartnerFile reg = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
			sql.append("SELECT * FROM HL7_BPARTNERFILE WHERE ISACTIVE = 'Y' AND HL7_BPARTNER_ID = ? ");
			
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, HL7_BPartner_id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				reg = new MHL7BPartnerFile(ctx, rs, trxName);
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
