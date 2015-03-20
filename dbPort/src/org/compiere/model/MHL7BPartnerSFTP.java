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

public class MHL7BPartnerSFTP extends X_HL7_BPartnerSFTP implements
		ECareConnectorProperties {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1634797192594885929L;
	public static CLogger s_log = CLogger.getCLogger(MHL7BPartnerSFTP.class);

	public MHL7BPartnerSFTP(Properties ctx, int HL7_BPartnerSFTP_ID,
			String trxName) {
		super(ctx, HL7_BPartnerSFTP_ID, trxName);
	}

	public MHL7BPartnerSFTP(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	public Properties getProperties() {
		Properties props = null;
		if (isInbound()) {
			props = new FileReaderProperties().getDefaults();
			props.put(FileReaderProperties.FILE_SCHEME, FileReaderProperties.SCHEME_SFTP);
			
			props.put(FileReaderProperties.DATATYPE, FileReaderProperties.name);
			props.put(FileReaderProperties.FILE_HOST, getSFTPHost()+"/"+getDirectory());
			props.put(FileReaderProperties.FILE_DIRECTORY, getDirectory());
			props.put(FileReaderProperties.FILE_FILTER, getFilter());
			
			props.put(FileReaderProperties.FILE_ANONYMOUS, MHL7BPartnerSFTP.ISDELETEAFTERREAD_NO);
			props.put(FileReaderProperties.FILE_USERNAME, getUserName());
			props.put(FileReaderProperties.FILE_PASSWORD, getPassword());
			
			props.put(FileReaderProperties.FILE_POLLING_TYPE, getPollingType());
			props.put(FileReaderProperties.FILE_POLLING_FREQUENCY, String.valueOf(getPollingFreq()));
			props.put(FileReaderProperties.FILE_MOVE_TO_DIRECTORY, getMoveToDir());
			props.put(FileReaderProperties.FILE_MOVE_TO_PATTERN,getMoveToFile());
			props.put(FileReaderProperties.FILE_MOVE_TO_ERROR_DIRECTORY,getMoveErrorToFile());
			props.put(FileReaderProperties.FILE_DELETE_AFTER_READ, getIsDeleteAfterRead());
			props.put(FileReaderProperties.FILE_CHECK_FILE_AGE, getIsCheckAge());
			props.put(FileReaderProperties.CONNECTOR_CHARSET_ENCODING, getEncoding());
			props.put(FileReaderProperties.FILE_TYPE,getFileType());
			
			
		} else {
			props = new FileWriterProperties().getDefaults();
			
			props.put(FileWriterProperties.DATATYPE,
					FileWriterProperties.name);
			props.put(FileWriterProperties.FILE_SCHEME,
					FileWriterProperties.SCHEME_SFTP);

			props.put(FileWriterProperties.FILE_HOST, getSFTPHost() + "/"
					+ getDirectory());
			props.put(FileWriterProperties.FILE_DIRECTORY, getDirectory());
			props.put(FileReaderProperties.FILE_ANONYMOUS, MHL7BPartnerSFTP.ISDELETEAFTERREAD_NO);

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
	public String getMoveErrorToFile() {
		if(super.getMoveErrorToFile() ==null){
			return "";
		}
		return super.getMoveErrorToFile();
	}

	@Override
	public String getMoveToFile() {
		if(super.getMoveToFile() == null){
			return "";
		}
		return super.getMoveToFile();
	}

	@Override
	public String getMoveToDir() {
		
		if (super.getMoveToDir() == null){
			return "";
		}else{
			return super.getMoveToDir();
		}
	}

	public String getTransportName() {
		if(isInbound()){
			return FileReaderProperties.name;
		}else{
			return FileWriterProperties.name;
		}
	}

	public static MHL7BPartnerSFTP getByHL7BPartner(Properties ctx, int HL7_BPartner_id, String trxName) {
		MHL7BPartnerSFTP reg = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
			sql.append("SELECT * FROM HL7_BPARTNERSFTP WHERE ISACTIVE = 'Y' AND HL7_BPARTNER_ID = ? ");
			
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, HL7_BPartner_id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				reg = new MHL7BPartnerSFTP(ctx, rs, trxName);
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
