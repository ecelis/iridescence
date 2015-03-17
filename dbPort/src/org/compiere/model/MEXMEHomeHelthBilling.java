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
import org.compiere.util.CLogger;
import org.compiere.util.DB;

/**
 * @author odelarosa
 * 
 */
public class MEXMEHomeHelthBilling extends X_EXME_HomeHealthBilling {
	
	private static final long serialVersionUID = -1692410675196877105L;
	private static CLogger s_log = CLogger.getCLogger(MEXMEHomeHelthBilling.class);

	public static boolean create(Properties ctx, int ctaPacId, HomeHealth homeHealth, String trxName) {
		boolean retValue = false;

		MEXMEHomeHelthBilling homeHealthBilling = new MEXMEHomeHelthBilling(ctx, 0, null);
		homeHealthBilling.setEXME_CtaPac_ID(ctaPacId);

		if (homeHealthBilling.save(trxName)) {
			MAttachment att = new MAttachment(ctx, MEXMEHomeHelthBilling.Table_ID, homeHealthBilling.getEXME_HomeHealthBilling_ID(), null);
			ObjectOutputStream out = null;
			ByteArrayOutputStream outputStream = null;
			try {
				outputStream = new ByteArrayOutputStream();
				out = new ObjectOutputStream(outputStream);
				out.writeObject(homeHealth);
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

	public static MEXMEHomeHelthBilling get(Properties ctx, int ctaPacId, String trxName) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ");
		sql.append("  * ");
		sql.append("FROM ");
		sql.append("  EXME_HomeHealthBilling ");
		sql.append("WHERE ");
		sql.append("  EXME_CtaPac_ID = ? AND ");
		sql.append("  isActive = 'Y' ");
		sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, sql.toString(), "EXME_HomeHealthBilling"));
		MEXMEHomeHelthBilling homeHealthBilling = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, ctaPacId);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				homeHealthBilling = new MEXMEHomeHelthBilling(ctx, rs, trxName);
			}
			
		} catch (Exception e) {
			s_log.log(Level.SEVERE, null, e);
		} finally {
			DB.close(rs, pstmt);
		}
		return homeHealthBilling;
	}

	public static HomeHealth getObject(Properties ctx, int ctaPacId, String trxName) {
		HomeHealth homeHealth = null;
		try {
			MEXMEHomeHelthBilling homeHealthBilling = get(ctx, ctaPacId, trxName);
			if (homeHealthBilling != null) {
				MAttachment attachment = MAttachment.get(ctx, MEXMEHomeHelthBilling.Table_ID, homeHealthBilling.getEXME_HomeHealthBilling_ID());
				if (attachment != null) {
					File f = attachment.getEntry(0).getFile();
					if (f != null) {
						FileInputStream fis = null;
						ObjectInputStream in = null;
						try {
							fis = new FileInputStream(f);
							in = new ObjectInputStream(fis);
							homeHealth = (HomeHealth) in.readObject();
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
		return homeHealth;
	}

	/**
	 * @param ctx
	 * @param EXME_HomeHealthBilling_ID
	 * @param trxName
	 */
	public MEXMEHomeHelthBilling(Properties ctx, int EXME_HomeHealthBilling_ID, String trxName) {
		super(ctx, EXME_HomeHealthBilling_ID, trxName);
	}

	/**
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MEXMEHomeHelthBilling(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

}
