package org.compiere.model;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.ECareConnectorProperties;

import com.mirth.connect.connectors.js.JavaScriptWriterProperties;


public class MHL7BPartnerJS extends X_HL7_BPartnerJS implements
		ECareConnectorProperties {
	
	   /**
	 * 
	 */
	private static final long serialVersionUID = -3091649476151445057L;
	/** Standard Constructor */
    public MHL7BPartnerJS (Properties ctx, int HL7_BPartnerJS_ID, String trxName)
    {
      super (ctx, HL7_BPartnerJS_ID, trxName);
      /** if (HL7_BPartnerJS_ID == 0)
        {
			setHL7_BPartnerJS_ID (0);
        } */
    }
    public static CLogger s_log = CLogger.getCLogger(MHL7BPartnerJS.class);
    /** Load Constructor */
    public MHL7BPartnerJS (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

	@Override
	public Properties getProperties() {
		Properties props = new JavaScriptWriterProperties().getDefaults();
		props.put(JavaScriptWriterProperties.JAVASCRIPT_SCRIPT, getJavascript());
		
		
		return props;
	}

	@Override
	public String getTransportName() {
		// TODO Auto-generated method stub
		return JavaScriptWriterProperties.name;
	}

	public static MHL7BPartnerJS getByHL7BPartner(Properties ctx, int HL7_BPartner_id, String trxName) {
		MHL7BPartnerJS reg = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
			sql.append("SELECT * FROM HL7_BPARTNERJS WHERE ISACTIVE = 'Y' AND HL7_BPARTNER_ID = ? ");
			
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, HL7_BPartner_id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				reg = new MHL7BPartnerJS(ctx, rs, trxName);
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
