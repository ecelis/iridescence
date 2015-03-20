/******************************************************************************
 * Product: Compiere ERP & CRM Smart Business Solution *
 * Copyright (C) 1999-2006 ComPiere, Inc. All Rights Reserved. *
 * This program is free software; you can redistribute it and/or modify it *
 * under the terms version 2 of the GNU General Public License as published *
 * by the Free Software Foundation. This program is distributed in the hope *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. *
 * See the GNU General Public License for more details. *
 * You should have received a copy of the GNU General Public License along *
 * with this program; if not, write to the Free Software Foundation, Inc., *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA. *
 * For the text or an alternative of this public license, you may reach us *
 * ComPiere, Inc., 2620 Augustine Dr. #245, Santa Clara, CA 95054, USA *
 * or via info@compiere.org or http://www.compiere.org/license.html *
 *****************************************************************************/
package org.compiere.model;

import java.sql.ResultSet;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.DB;

/**
 * Note Model
 * 
 * @author Jorg Janke
 * @version $Id: MNote.java,v 1.3 2006/07/30 00:58:37 jjanke Exp $
 */
public class MNote extends X_AD_Note {
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	/** Static Logger					*/
	private static CLogger		slog = CLogger.getCLogger (MNote.class);

	/**
	 * Standard Constructor
	 * 
	 * @param ctx context
	 * @param AD_Note_ID id
	 * @param trxName transaction
	 */
	public MNote(final Properties ctx, final int AD_Note_ID, final String trxName) {
		super(ctx, AD_Note_ID, trxName);
		if (AD_Note_ID == 0) {
			setProcessed(false);
			setProcessing(false);
		}
	} // MNote

	/**
	 * Load Constructor
	 * 
	 * @param ctx context
	 * @param rs result set
	 * @param trxName transaction
	 */
	public MNote(final Properties ctx, final ResultSet rs, final String trxName) {
		super(ctx, rs, trxName);
	} // MNote

	/**
	 * New Mandatory Constructor
	 * 
	 * @param ctx context
	 * @param AD_Message_ID message
	 * @param AD_User_ID targeted user
	 * @param trxName transaction
	 */
	public MNote(final Properties ctx, final int AD_Message_ID, final int AD_User_ID, final String trxName) {
		this(ctx, 0, trxName);
		setAD_Message_ID(AD_Message_ID);
		setAD_User_ID(AD_User_ID);
	} // MNote

	/**
	 * New Mandatory Constructor
	 * 
	 * @param ctx context
	 * @param AD_MessageValue message
	 * @param AD_User_ID targeted user
	 * @param trxName transaction
	 */
	public MNote(final Properties ctx, final String AD_MessageValue, final int AD_User_ID, final String trxName) {
		this(ctx, MMessage.getAD_Message_ID(ctx, AD_MessageValue), AD_User_ID, trxName);
	} // MNote

	/**
	 * Create Note
	 * 
	 * @param ctx context
	 * @param AD_Message_ID message
	 * @param AD_User_ID user
	 * @param AD_Table_ID table
	 * @param Record_ID record
	 * @param TextMsg text message
	 * @param Reference reference
	 * @param trxName transaction
	 */
	public MNote(final Properties ctx, final int AD_Message_ID, final int AD_User_ID, final int AD_Table_ID, final int Record_ID,
		final String Reference, final String TextMsg, final String trxName) {
		this(ctx, AD_Message_ID, AD_User_ID, trxName);
		setRecord(AD_Table_ID, Record_ID);
		setReference(Reference);
		setTextMsg(TextMsg);
	} // MNote

	/**
	 * Create Note
	 * for CDS
	 * 
	 * @param ctx context
	 * @param AD_Message_ID message
	 * @param AD_User_ID user
	 * @param AD_Table_ID table
	 * @param Record_ID record
	 * @param TextMsg text message
	 * @param Reference reference
	 * @param trxName transaction
	 */
	public MNote(final Properties ctx, final int AD_Message_ID, final int AD_User_ID, final int AD_Table_ID, final int Record_ID,
		final String Reference, final String TextMsg, final int PacienteID, final int cdsID, final String trxName) {
		this(ctx, AD_Message_ID, AD_User_ID, trxName);
		setRecord(AD_Table_ID, Record_ID);
		setReference(Reference);
		setTextMsg(TextMsg);
		setEXME_Paciente_ID(PacienteID);
		setNoticeType(X_AD_Note.NOTICETYPE_CDS);
		setDescription(String.valueOf(cdsID));
	} // MNote

	/**
	 * New Constructor
	 * 
	 * @param ctx context
	 * @param AD_MessageValue message
	 * @param AD_User_ID targeted user
	 * @param AD_Client_ID client
	 * @param AD_Org_ID org
	 * @param trxName transaction
	 */
	public MNote(final Properties ctx, final String AD_MessageValue, final int AD_User_ID, final int AD_Client_ID, final int AD_Org_ID,
		final String trxName) {
		this(ctx, MMessage.getAD_Message_ID(ctx, AD_MessageValue), AD_User_ID, trxName);
		setClientOrg(AD_Client_ID, AD_Org_ID);
	} // MNote

	/**************************************************************************
	 * Set Record.
	 * (Ss Button and defaults to String)
	 * 
	 * @param AD_Message AD_Message
	 */
	public void setAD_Message_ID(final String AD_Message) {
		final int AD_Message_ID = DB.getSQLValue(null, "SELECT AD_Message_ID FROM AD_Message WHERE Value=?", AD_Message);
		if (AD_Message_ID != -1) {
			super.setAD_Message_ID(AD_Message_ID);
		} else {
			super.setAD_Message_ID(240); // Error
			log.log(Level.SEVERE, "setAD_Message_ID - ID not found for '" + AD_Message + "'");
		}
	} // setRecord_ID

	/**
	 * Set AD_Message_ID.
	 * Looks up No Message Found if 0
	 * 
	 * @param AD_Message_ID id
	 */
	@Override
	public void setAD_Message_ID(final int AD_Message_ID) {
		if (AD_Message_ID == 0) {
			super.setAD_Message_ID(MMessage.getAD_Message_ID(getCtx(), "NoMessageFound"));
		} else {
			super.setAD_Message_ID(AD_Message_ID);
		}
	} // setAD_Message_ID

	/**
	 * Get Message
	 * 
	 * @return message
	 */
	public String getMessage() {
		final int AD_Message_ID = getAD_Message_ID();
		final MMessage msg = MMessage.get(getCtx(), AD_Message_ID);
		return msg.getMsgText();
	} // getMessage

	/**
	 * Set Record
	 * 
	 * @param AD_Table_ID table
	 * @param Record_ID record
	 */
	public void setRecord(final int AD_Table_ID, final int Record_ID) {
		setAD_Table_ID(AD_Table_ID);
		setRecord_ID(Record_ID);
	} // setRecord

	/**
	 * String Representation
	 * 
	 * @return info
	 */
	@Override
	public String toString() {
		final StringBuffer sb = new StringBuffer("MNote[").append(get_ID());
		sb.append(",AD_Message_ID=").append(getAD_Message_ID()).append(",");
		sb.append(getReference()).append(",Processed=").append(isProcessed());
		sb.append("]");
		return sb.toString();
	} // toString

//	public MNote get(final Properties ctx, final int EXME_Paciente_ID, final int cdsID, final String trxName) throws Exception {
//		MNote note = null;
//		try {
//			final StringBuilder sql = new StringBuilder();
//			sql.append(" AD_Note.EXME_Paciente_ID=? ");
//			sql.append(" AND AD_Note.Description=? ");
//			note = new Query(ctx, Table_Name, sql.toString(), trxName)//
//				.setParameters(EXME_Paciente_ID, cdsID)//
//				.setOnlyActiveRecords(true)//
//				.addAccessLevelSQL(true)//
//				.first();
//		} catch (final Exception e) {
//			throw new Exception(e.getMessage());
//		}
//		return note;
//	}

	public static MNote get(final Properties ctx, final int cdsID, final String trxName) throws Exception {
		final StringBuilder sql = new StringBuilder();
		sql.append(" AD_Note.Description=? ");
		return new Query(ctx, Table_Name, sql.toString(), trxName)//
			.setParameters(String.valueOf(cdsID))//
			.setOnlyActiveRecords(true)//
			.addAccessLevelSQL(true)//
			.first();
	}
	
	public static List<MNote> getList(final Properties ctx, final int table_id, final int record_id, final String trxName) {
		List<MNote> retValue = null;
		try {
			final StringBuilder sql = new StringBuilder();
			sql.append(" AD_Note.AD_Table_ID=? ");
			sql.append(" AND AD_Note.Record_ID=? ");

			retValue = new Query(ctx, Table_Name, sql.toString(), trxName)//
				.setParameters(table_id, record_id)//
				.setOnlyActiveRecords(true)//
				.addAccessLevelSQL(true)//
				.list();
		} catch (final Exception e) {
			slog.log(Level.SEVERE, e.getMessage(), e);
		}
		return retValue;
	}

} // MNote
