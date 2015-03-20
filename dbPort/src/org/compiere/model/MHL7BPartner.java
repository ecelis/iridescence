package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.ECareConnectorProperties;

public class MHL7BPartner extends X_HL7_BPartner {

	/**
	 * 
	 */
	private static final long serialVersionUID = -147372104788158719L;
	
	private static final CLogger s_log = CLogger.getCLogger(MHL7BPartner.class);

	public MHL7BPartner(Properties ctx, int HL7_BPartner_ID, String trxName) {
		super(ctx, HL7_BPartner_ID, trxName);
	}

	public MHL7BPartner(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}
	

	public static ECareConnectorProperties getConnectorProperties(Properties ctx,
			int X_HL7_BPartner_ID, String trxName) {
		return getConnectorProperties(ctx, X_HL7_BPartner_ID, trxName, false);
	}
	
	

	/***/
	public static ECareConnectorProperties getConnectorProperties(Properties ctx,
			int X_HL7_BPartner_ID, String trxName, boolean isResposne) {

		X_HL7_BPartner encabezado = new MHL7BPartner(ctx, X_HL7_BPartner_ID,
				trxName);

		String tipo_tableName = isResposne ? encabezado.getInboundConnector() : encabezado.getConnectorType();
		int bpartnerId = encabezado.getHL7_BPartner_ID();

		ECareConnectorProperties retValue = null;

		/**/
		String sql = "SELECT AD_Table_ID FROM  AD_Table "
				+ " WHERE UPPER(AD_Table.TableName) =?";
		PreparedStatement pstmt = null;
		try {
			pstmt = DB.prepareStatement(sql, trxName);
			pstmt.setString(1, tipo_tableName.toUpperCase());

			ResultSet rs = pstmt.executeQuery();
			String isInbound = isResposne ?"'Y'":"'N'";
			if (rs.next()) {

				MTable table = MTable.get(ctx, rs.getInt(1));
				StringBuilder whereClause = new StringBuilder(" HL7_BPartner_ID = ")
				.append(bpartnerId).append(" AND isinbound = ").append(isInbound);
				// el PO que se recibe debria ser de un tipo que implemente
				// PropiedadesDestino
				retValue = (ECareConnectorProperties) table.getPO(whereClause.toString(),
						trxName);
				

			}
			rs.close();
			pstmt.close();
			pstmt = null;
		} catch (SQLException ex) {
			s_log.log(Level.SEVERE, sql, ex);
		}
		try {
			if (pstmt != null)
				pstmt.close();
		} catch (SQLException ex1) {
		}
		pstmt = null;

		return retValue;

		// if (tipo.equals(CONNECTORTYPE_Email)) {
		// destinatario = MHL7BPartnerEmail.getFromEncabezado(ctx,
		// encabezadoId, trxName);
		//
		// } else if (tipo.equals(CONNECTORTYPE_File)) {
		//
		// } else if (tipo.equals(CONNECTORTYPE_FTP)) {
		//
		// destinatario = MHL7BPartnerFTP.getFromEncabezado(ctx, encabezadoId,
		// trxName);
		//
		// } else if (tipo.equals(CONNECTORTYPE_Samba)) {
		// destinatario = MHL7BPartnerSMB.getFromEncabezado(ctx, encabezadoId,
		// trxName);
		//
		// } else if (tipo.equals(CONNECTORTYPE_SFTP)) {
		//
		// destinatario = MHL7BPartnerSFTP.getFromEncabezado(ctx,
		// encabezadoId, trxName);
		//
		// } else if (tipo.equals(CONNECTORTYPE_LLP)) {
		// // destinatario = MHL7BpartnerLLP.get
		// }

	}


}
