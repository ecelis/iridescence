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
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.adempiere.exceptions.MedsysException;
import org.compiere.util.CLogger;
import org.compiere.util.Env;

/**
 * Attachment Model. One Attachment can have multiple entries
 * 
 * @author Jorg Janke
 * @version $Id: MAttachment.java,v 1.4 2006/07/30 00:58:37 jjanke Exp $
 */
public class MAttachmentFile extends X_AD_AttachmentFile {
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;

	/** Static Logger */
	public static CLogger		slog				= CLogger.getCLogger(MAttachmentFile.class);

	/**
	 * Para registros anteriors, inserta en AD_AttachmentFile, cada una de las entradas de un ZIP
	 * TODO: remove after March 2012
	 * @param attch
	 */
	public static void validateEntries(MAttachment attch) {
		if (attch != null && !attch.is_new()) {
			if (attch.getEntryCount() > 0) {
				List<MAttachmentFile> lst = getFiles(attch);
				MAttachmentEntry[] array = attch.getEntries();
				if (lst.size() != array.length) {
					boolean addNew = true;
					for (MAttachmentEntry entry : array) {
						for (int i = 0; i < lst.size(); i++) {
							// si es el mismo nombre
							if (entry.getFile().getName().equals(lst.get(i).getFileName())) {
								// remover y continuar
								lst.remove(i);
								addNew = false;
								break;
							}
						}
						if (addNew) {
							final MAttachmentFile file = new MAttachmentFile(attch);
							file.setDateDoc(attch.getCreated());
							file.setDescription(attch.getTextMsg());
							file.setFileName(entry.getFile().getName());
							if (!file.save()) {
								throw new MedsysException();
							}
						}
					}
					// se eliminan las que quedaron
					for (MAttachmentFile file : lst) {
						file.delete(true);
					}
				}
			}
		}
	}

	/**
	 * 
	 * @param attch
	 * @return
	 */
	public static List<MAttachmentFile> getFiles(final MAttachment attch) {
		List<MAttachmentFile> list;
		if (attch == null) {
			list = new ArrayList<MAttachmentFile>();
		} else {
			list = new Query(attch.getCtx(), Table_Name, " AD_Attachment_ID=? ", attch.get_TrxName())//
				.setParameters(attch.getAD_Attachment_ID())//
				.setOrderBy(" Created DESC ").list();
		}
		return list;
	} // getFiles

	private MAttachment			attachment;
	private MAttachmentEntry	entry;
	private X_AD_AttachmentType	attachmentType;

	/**
	 * Standard Constructor
	 * 
	 * @param ctx context
	 * @param AD_Attachment_ID id
	 * @param trxName transaction
	 */
	public MAttachmentFile(MAttachment attachment) {
		super(attachment.getCtx(), 0, attachment.get_TrxName());
		if (!attachment.is_new()) {
			setAD_Attachment_ID(attachment.getAD_Attachment_ID());
		}
		this.attachment = attachment;
	} // MAttachmentFile

	/**
	 * Standard Constructor
	 * 
	 * @param ctx context
	 * @param AD_Attachment_ID id
	 * @param trxName transaction
	 */
	public MAttachmentFile(Properties ctx, int AD_AttachmentFile_ID, String trxName) {
		super(ctx, AD_AttachmentFile_ID, trxName);
	} // MAttachmentFile

	/**
	 * Load Constructor
	 * 
	 * @param ctx context
	 * @param rs result set
	 * @param trxName transaction
	 */
	public MAttachmentFile(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	} // MAttachmentFile

	@Override
	public MAttachment getAD_Attachment() throws RuntimeException {
		if (attachment == null) {
			attachment = (MAttachment) super.getAD_Attachment();
		}
		return attachment;
	}

	@Override
	public X_AD_AttachmentType getAD_AttachmentType() throws RuntimeException {
		if (attachmentType == null || attachmentType.getAD_AttachmentType_ID() != getAD_AttachmentType_ID()) {
			attachmentType = (X_AD_AttachmentType) super.getAD_AttachmentType();
		}
		return attachmentType;
	}

	/**
	 * @return MAttachmentEntry
	 */
	public MAttachmentEntry getEntry() {
		if (entry == null) {
			entry = getAD_Attachment().getEntry(getFileName());
		}
		return entry;
	}

	@Override
	public Object onCompare(int type) {
		Object comparable;
		if (type == COLUMNNAME_FileName.hashCode()) {
			comparable = getFileName();
		} else if (type == COLUMNNAME_DateDoc.hashCode()) {
			comparable = getDateDoc();
		} else if (type == COLUMNNAME_AD_AttachmentType_ID.hashCode()) {
			comparable = getAD_AttachmentType().getName();
		} else if (type == COLUMNNAME_Description.hashCode()) {
			comparable = getDescription();
		} else {
			comparable = super.onCompare(type);
		}
		return comparable;
	}

	/**
	 * @param mEntry MAttachmentEntry
	 */
	public void setEntry(MAttachmentEntry mEntry) {
		this.entry = mEntry;
		if (entry != null) {
			setFileName(entry.getFile().getName());
			setDateDoc(Env.getCurrentDate());
		}
	}

} // MAttachment
