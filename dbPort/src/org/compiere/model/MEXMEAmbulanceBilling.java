package org.compiere.model;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.compiere.util.CLogger;
import org.compiere.util.DB;

/**
 * @author odelarosa
 * 
 */
public class MEXMEAmbulanceBilling extends X_EXME_AmbulanceBilling {

	private static final long serialVersionUID = 1719759466052694536L;
	private static CLogger s_log = CLogger.getCLogger(MEXMEAmbulanceBilling.class);

	public static boolean create(Properties ctx, int ctaPacId, Ambulance ambulance, String trxName) {
		boolean retValue = false;

		MEXMEAmbulanceBilling ambulanceBilling = new MEXMEAmbulanceBilling(ctx, 0, null);
		ambulanceBilling.setEXME_CtaPac_ID(ctaPacId);
		if (ambulance.getDiag() > 0) {
			ambulanceBilling.setEXME_Diagnostico_ID(ambulance.getDiag());
		}
		if (ambulance.getDiag2() > 0) {
			ambulanceBilling.setEXME_Diagnostico2_ID(ambulance.getDiag2());
		}
		if (ambulance.getDiag3() > 0) {
			ambulanceBilling.setEXME_Diagnostico3_ID(ambulance.getDiag3());
		}
		if (ambulance.getDiag4() > 0) {
			ambulanceBilling.setEXME_Diagnostico4_ID(ambulance.getDiag4());
		}
		ambulanceBilling.setPostal(ambulance.getPostalCode());
		ambulanceBilling.setMedicalCondition(StringUtils.join(ambulance.getMedicalConditions(), "\n"));
		ambulanceBilling.setOrigin(ambulance.getOrigin());
		ambulanceBilling.setDestination(ambulance.getDestination());
		ambulanceBilling.setLand(ambulance.isRadio3());

		if (ambulanceBilling.save(trxName)) {
			MAttachment att = new MAttachment(ctx, MEXMEAmbulanceBilling.Table_ID, ambulanceBilling.getEXME_AmbulanceBilling_ID(), null);
			ObjectOutputStream out = null;
			ByteArrayOutputStream outputStream = null;
			try {
				outputStream = new ByteArrayOutputStream();
				out = new ObjectOutputStream(outputStream);
				out.writeObject(ambulance);
			} catch (IOException e) {
				s_log.log(Level.SEVERE, null, e);
			} finally {
				IOUtils.closeQuietly(outputStream);
				IOUtils.closeQuietly(out);
			}

			if (outputStream != null) {
				att.addEntry("ambulance.obj", outputStream.toByteArray());
				retValue = att.save(trxName);
			}
		}

		return retValue;
	}

	public static MEXMEAmbulanceBilling get(Properties ctx, int ctaPacId, String trxName) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ");
		sql.append("  * ");
		sql.append("FROM ");
		sql.append("  EXME_AmbulanceBilling ");
		sql.append("WHERE ");
		sql.append("  EXME_CtaPac_ID = ? AND ");
		sql.append("  isActive = 'Y' ");
		sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, sql.toString(), "EXME_AmbulanceBilling"));
		MEXMEAmbulanceBilling ambulanceBilling = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, ctaPacId);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				ambulanceBilling = new MEXMEAmbulanceBilling(ctx, rs, trxName);
			}
			
		} catch (Exception e) {
			s_log.log(Level.SEVERE, null, e);
		} finally {
			DB.close(rs, pstmt);
		}
		return ambulanceBilling;
	}

	public static Ambulance getObject(Properties ctx, int ctaPacId, String trxName) {
		Ambulance ambulance = null;
		try {
			MEXMEAmbulanceBilling ambulanceBilling = get(ctx, ctaPacId, trxName);
			if (ambulanceBilling != null) {
				MAttachment attachment = MAttachment.get(ctx, MEXMEAmbulanceBilling.Table_ID, ambulanceBilling.getEXME_AmbulanceBilling_ID());
				if (attachment != null) {
					File f = attachment.getEntry(0).getFile();
					if (f != null) {
						FileInputStream fis = null;
						ObjectInputStream in = null;
						try {
							fis = new FileInputStream(f);
							in = new ObjectInputStream(fis);
							ambulance = (Ambulance) in.readObject();
						} catch (Exception e) {
							s_log.log(Level.SEVERE, null, e);
						} finally {
							IOUtils.closeQuietly(fis);
							IOUtils.closeQuietly(in);
						}
					}
				}
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, null, e);
		}
		return ambulance;
	}

	/**
	 * @param ctx
	 * @param EXME_AmbulanceBilling_ID
	 * @param trxName
	 */
	public MEXMEAmbulanceBilling(Properties ctx, int EXME_AmbulanceBilling_ID, String trxName) {
		super(ctx, EXME_AmbulanceBilling_ID, trxName);
	}

	/**
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MEXMEAmbulanceBilling(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

}
