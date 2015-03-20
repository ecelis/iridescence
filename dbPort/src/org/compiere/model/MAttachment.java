/******************************************************************************
 * Product: Compiere ERP & CRM Smart Business Solution                        *
 * Copyright (C) 1999-2006 ComPiere, Inc. All Rights Reserved.                *
 * This program is free software; you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * ComPiere, Inc., 2620 Augustine Dr. #245, Santa Clara, CA 95054, USA        *
 * or via info@compiere.org or http://www.compiere.org/license.html           *
 *****************************************************************************/
package org.compiere.model;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Properties;
import java.util.logging.Level;
import java.util.zip.Deflater;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.compiere.util.CLogger;
import org.compiere.util.Env;
import org.compiere.util.MimeType;
import org.compiere.util.ZipUtil;

/**
 * Attachment Model. One Attachment can have multiple entries
 * 
 * @author Jorg Janke
 * @version $Id: MAttachment.java,v 1.4 2006/07/30 00:58:37 jjanke Exp $
 */
public class MAttachment extends X_AD_Attachment {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Get Attachment
	 * 
	 * @param ctx
	 *            context
	 * @param AD_Table_ID
	 *            table
	 * @param Record_ID
	 *            record
	 * @return attachment or null
	 */
	public static MAttachment get(Properties ctx, int AD_Table_ID, int Record_ID) {
//		MAttachment retValue = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		final String sql = "SELECT * FROM AD_Attachment WHERE AD_Table_ID=? AND Record_ID=?";
//		try {
//			pstmt = DB.prepareStatement(sql, null);
//			pstmt.setInt(1, AD_Table_ID);
//			pstmt.setInt(2, Record_ID);
//			rs = pstmt.executeQuery();
//			if (rs.next()) {
//				retValue = new MAttachment(ctx, rs, null);
//			}
//		} catch (Exception e) {
//			s_log.log(Level.SEVERE, sql, e);
//		} finally {
//			DB.close(rs, pstmt);
//		}
		return new Query(ctx, Table_Name, " AD_Table_ID=? AND Record_ID=? ", null)//
		.setParameters(AD_Table_ID, Record_ID)//
		.first();
	} // get

	/** Static Logger */
	public static CLogger s_log = CLogger.getCLogger(MAttachment.class);

	/**************************************************************************
	 * Standard Constructor
	 * 
	 * @param ctx
	 *            context
	 * @param AD_Attachment_ID
	 *            id
	 * @param trxName
	 *            transaction
	 */
	public MAttachment(Properties ctx, int AD_Attachment_ID, String trxName) {
		super(ctx, AD_Attachment_ID, trxName);
	} // MAttachment

	/**
	 * New Constructor
	 * 
	 * @param ctx
	 *            context
	 * @param AD_Table_ID
	 *            table
	 * @param Record_ID
	 *            record
	 * @param trxName
	 *            transaction
	 */
	public MAttachment(Properties ctx, int AD_Table_ID, int Record_ID, String trxName) {
		this(ctx, 0, trxName);
		if (AD_Table_ID > 0) {
			setAD_Table_ID(AD_Table_ID);
		}
		if (Record_ID > 0) {
			setRecord_ID(Record_ID);
		}
	} // MAttachment

	/**
	 * Load Constructor
	 * 
	 * @param ctx
	 *            context
	 * @param rs
	 *            result set
	 * @param trxName
	 *            transaction
	 */
	public MAttachment(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	} // MAttachment

	/** Indicator for no data */
	public static final String NONE = ".";
	/** Indicator for zip data */
	public static final String ZIP = "zip";

	/** List of Entry Data */
	private ArrayList<MAttachmentEntry> mItems = null;

	/**
	 * Add to Text Msg
	 * 
	 * @param added
	 *            text
	 */
	public void addTextMsg(String added) {
		final String oldTextMsg = getTextMsg();
		if (oldTextMsg == null) {
			setTextMsg(added);
		} else if (added != null) {
			setTextMsg(oldTextMsg + added);
		}
	} // addTextMsg

	/**
	 * Get Text Msg
	 * 
	 * @return trimmed message
	 */
	public String getTextMsg() {
		final String msg = super.getTextMsg();
		if (msg == null) {
			return null;
		}
		return msg.trim();
	} // setTextMsg

	/**
	 * String Representation
	 * 
	 * @return info
	 */
	public String toString() {
		final StringBuilder sb = new StringBuilder("MAttachment[");
		sb.append(getAD_Attachment_ID()).append(",Title=").append(getTitle()).append(",Entries=").append(getEntryCount());
		for (int i = 0; i < getEntryCount(); i++) {
			if (i == 0) {
				sb.append(":");
			} else {
				sb.append(",");
			}
			sb.append(getEntryName(i));
		}
		sb.append("]");
		return sb.toString();
	} // toString

	/**
	 * Add new Data Entry
	 * 
	 * @param file
	 *            file
	 * @return true if added
	 */
	public boolean addEntry(File file) {
		return addEntry(file, null);
	} // addEntry
	
	/**
	 * Add new Data Entry
	 * 
	 * @param file
	 *            file
	 * @return true if added
	 */
	public boolean addEntry(File file, String entryName) {
		if (file == null) {
			log.warning("No File");
			return false;
		}
		if (!file.exists() || file.isDirectory()) {
			log.warning("not added - " + file + ", Exists=" + file.exists() + ", Directory=" + file.isDirectory());
			return false;
		}
		log.fine("addEntry - " + file);
		//
		final String name = StringUtils.isBlank(entryName) ? file.getName() : entryName;
		byte[] data = null;
		try {
			final FileInputStream fis = new FileInputStream(file);
			final ByteArrayOutputStream bos = new ByteArrayOutputStream();
			final byte[] buffer = new byte[1024 * 8]; // 8kB
			int length = -1;
			while ((length = fis.read(buffer)) != -1) {
				bos.write(buffer, 0, length);
			}
			fis.close();
			data = bos.toByteArray();
			bos.close();
		} catch (IOException ioe) {
			log.log(Level.SEVERE, "(file)", ioe);
		}
		return addEntry(name, data);
	} // addEntry

	/**
	 * Add new Data Entry
	 * 
	 * @param name
	 *            name
	 * @param data
	 *            data
	 * @return true if added
	 */
	public boolean addEntry(String name, byte[] data) {
		return addEntry(name, data, null);
	} // addEntry

	public boolean addEntry(String name, byte[] data, String description) {
		if (name == null || data == null) {
			return false;
		}
		return addEntry(new MAttachmentEntry(name, data, description)); // random index
	}

	/**
	 * Add Entry
	 * 
	 * @param item
	 *            attachment entry
	 * @return true if added
	 */
	public boolean addEntry(MAttachmentEntry item) {
		if (item == null) {
			return false;
		}
		if (mItems == null) {
			loadLOBData();
		}
		final boolean retValue = mItems.add(item);
		log.fine(item.toStringX());
		addTextMsg(" "); // otherwise not saved
		setBinaryData(null);// Lama: force update
		return retValue;
	} // addEntry
	
	/**
	 * Get Attachment Entry
	 * 
	 * @param index
	 *            index of the item
	 * @return Entry or null
	 */
	public MAttachmentEntry getEntry(String fileName) {
		if (mItems == null) {
			loadLOBData();
		}
		
		for (MAttachmentEntry entry : mItems) {
			if(StringUtils.equals(entry.getName(),fileName) 
				|| StringUtils.equals(entry.getFile().getName(),fileName)){
				return entry;
			}
		}
		return null;
	} // getEntry

	/**
	 * Get Attachment Entry
	 * 
	 * @param index
	 *            index of the item
	 * @return Entry or null
	 */
	public MAttachmentEntry getEntry(int index) {
		if (mItems == null) {
			loadLOBData();
		}
		if (index < 0 || index >= mItems.size()) {
			return null;
		}
		return (MAttachmentEntry) mItems.get(index);
	} // getEntry

	/**
	 * Get Attachment Entries as array
	 * 
	 * @return array or null
	 */
	public MAttachmentEntry[] getEntries() {
		if (mItems == null) {
			loadLOBData();
		}
		final MAttachmentEntry[] retValue = new MAttachmentEntry[mItems.size()];
		mItems.toArray(retValue);
		return retValue;
	} // getEntries

	/**
	 * Delete Entry
	 * 
	 * @param index
	 *            index
	 * @return true if deleted
	 */
	public boolean deleteEntry(int index) {
		if (index >= 0 && index < mItems.size()) {
			mItems.remove(index);
			log.config("Index=" + index + " - NewSize=" + mItems.size());
			return true;
		}
		log.warning("Not deleted Index=" + index + " - Size=" + mItems.size());
		return false;
	} // deleteEntry
	
	/**
	 * Delete Entry
	 * 
	 * @param index
	 *            index
	 * @return true if deleted
	 */
	public boolean deleteEntry(MAttachmentEntry entry) {
		if (entry != null && !mItems.isEmpty()) {
			for (int i = 0; i < mItems.size(); i++) {
				if(mItems.get(i).getName().equals(entry.getName())){
					log.config("Entr=" + entry + " - NewSize=" + mItems.size());
					mItems.remove(i);
					return true;
				}
			}
		}
		log.warning("Not deleted Entry=" + entry + " - Size=" + mItems.size());
		setBinaryData(null);// Lama: forces update
		return false;
	} // deleteEntry

	/**
	 * Get Entry Count
	 * 
	 * @return number of entries
	 */
	public int getEntryCount() {
		if (mItems == null) {
			loadLOBData();
		}
		return mItems.size();
	} // getEntryCount

	/**
	 * Get Entry Name
	 * 
	 * @param index
	 *            index
	 * @return name or null
	 */
	public String getEntryName(int index) {
		final MAttachmentEntry item = getEntry(index);
		if (item != null) {
			return item.getName();
		}
		return null;
	} // getEntryName

	/**
	 * Dump Entry Names
	 */
	public void dumpEntryNames() {
		if (mItems == null) {
			loadLOBData();
		}
		if (mItems == null || mItems.isEmpty()) {
			System.out.println("- no entries -");
			return;
		}
		System.out.println("- entries: " + mItems.size());
		for (int i = 0; i < mItems.size(); i++) {
			System.out.println("  - " + getEntryName(i));
		}
	} // dumpEntryNames

	/**
	 * Get Entry Data
	 * 
	 * @param index
	 *            index
	 * @return data or null
	 */
	public byte[] getEntryData(int index) {
		final MAttachmentEntry item = getEntry(index);
		if (item != null) {
			return item.getData();
		}
		return null;
	} // getEntryData
	
	/**
	 * Get Entry File with name
	 * 
	 * @param entryName entry name
	 * @return file
	 */
	public File getEntryFile(String entryName) {
		final MAttachmentEntry item = getEntry(entryName);
		if (item != null) {
			return item.getFile();
		}
		return null;
	} // getEntryFile

	/**
	 * Get Entry File with name
	 * 
	 * @param index
	 *            index
	 * @param fileName
	 *            optional file name
	 * @return file
	 */
	public File getEntryFile(int index, String fileName) {
		final MAttachmentEntry item = getEntry(index);
		if (item != null) {
			return item.getFile(fileName);
		}
		return null;
	} // getEntryFile

	/**
	 * Get Entry File with name
	 * 
	 * @param index
	 *            index
	 * @param file
	 *            file
	 * @return file
	 */
	public File getEntryFile(int index, File file) {
		final MAttachmentEntry item = getEntry(index);
		if (item != null) {
			return item.getFile(file);
		}
		return null;
	} // getEntryFile

	/**
	 * Save Entry Data in Zip File format
	 * 
	 * @return true if saved
	 */
	private boolean saveLOBData() {
		if (mItems == null || mItems.isEmpty()) {
			setBinaryData(null);
			return true;
		}
		final ByteArrayOutputStream out = new ByteArrayOutputStream();
		final ZipOutputStream zip = new ZipOutputStream(out);
		zip.setMethod(ZipOutputStream.DEFLATED);
		zip.setLevel(Deflater.BEST_COMPRESSION);
		zip.setComment("compiere");
		//
		try {
			for (int i = 0; i < mItems.size(); i++) {
				final MAttachmentEntry item = getEntry(i);
				final ZipEntry entry = new ZipEntry(item.getName());
				entry.setTime(System.currentTimeMillis());
				entry.setMethod(ZipEntry.DEFLATED);
				entry.setComment(item.getDescription());
				zip.putNextEntry(entry);
				final byte[] data = item.getData();
				zip.write(data, 0, data.length);
				zip.closeEntry();
				log.fine(entry.getName() + " - " + entry.getCompressedSize() + " (" + entry.getSize() + ") " + (entry.getCompressedSize() * 100 / entry.getSize()) + "%");
			}
			// zip.finish();
			zip.close();
			final byte[] zipData = out.toByteArray();
			log.fine("Length=" + zipData.length);
			setBinaryData(zipData);
			return true;
		} catch (Exception e) {
			log.log(Level.SEVERE, "saveLOBData", e);
		}
		setBinaryData(null);
		return false;
	} // saveLOBData

	/**
	 * Load Data into local m_data
	 * 
	 * @return true if success
	 */
	@SuppressWarnings("unchecked")
	private boolean loadLOBData() {
		// Reset
		mItems = new ArrayList<MAttachmentEntry>();
		//
		final byte[] data = getBinaryData();
		if (data == null) {
			return true;
		}
		log.fine("ZipSize=" + data.length);
		if (data.length == 0) {
			return true;
		}

//		 Old Format - single file
//		if (!ZIP.equals(getTitle())) {
//			mItems.add(new MAttachmentEntry(getTitle(), data, 1));
//			return true;
//		}

		try {
			File file = File.createTempFile("tmp", ".zip", new File(MAttachmentEntry.getTmpDirectory()));
			FileUtils.writeByteArrayToFile(file, data);
			ZipUtil zipUtil = new ZipUtil(file);

			Enumeration<ZipEntry> entries = zipUtil.entries();
			while (entries != null && entries.hasMoreElements()) {
				ZipEntry entry = entries.nextElement();
				mItems.add(new MAttachmentEntry(entry.getName(), IOUtils.toByteArray(zipUtil.getinInputStream(entry)), mItems.size() + 1, entry.getComment()));
			}

			FileUtils.deleteQuietly(file);
		} catch (Exception e) {
			log.log(Level.SEVERE, "loadLOBData", e);
			mItems = null;
			return false;
		}
		return true;
	} // loadLOBData

	/**
	 * Before Save
	 * 
	 * @param newRecord
	 *            new
	 * @return true if can be saved
	 */
	protected boolean beforeSave(boolean newRecord) {
		if (getTitle() == null ) {
			setTitle(ZIP);
		}
		return saveLOBData(); // save in BinaryData
	} // beforeSave

	/**************************************************************************
	 * Test
	 * 
	 * @param args
	 *            ignored
	 */
	public static void main(String[] args) {
		// System.setProperty("javax.activation.debug", "true");

		System.out.println(MimeType.getMimeType("data.xls"));
		System.out.println(MimeType.getMimeType("data.cvs"));
		System.out.println(MimeType.getMimeType("data.txt"));
		System.out.println(MimeType.getMimeType("data.log"));
		System.out.println(MimeType.getMimeType("data.html"));
		System.out.println(MimeType.getMimeType("data.htm"));
		System.out.println(MimeType.getMimeType("data.png"));
		System.out.println(MimeType.getMimeType("data.gif"));
		System.out.println(MimeType.getMimeType("data.jpg"));
		System.out.println(MimeType.getMimeType("data.xml"));
		System.out.println(MimeType.getMimeType("data.rtf"));

		System.exit(0);

		org.compiere.Compiere.startupEnvironment(true);
		MAttachment att = new MAttachment(Env.getCtx(), 100, 0, null);
		att.addEntry(new File("C:\\Compiere\\Dev.properties"));
		att.addEntry(new File("C:\\Compiere2\\index.html"));
		att.save();
		System.out.println(att);
		att.dumpEntryNames();
		int AD_Attachment_ID = att.getAD_Attachment_ID();
		//
		System.out.println("===========================================");
		att = new MAttachment(Env.getCtx(), AD_Attachment_ID, null);
		System.out.println(att);
		att.dumpEntryNames();
		System.out.println("===========================================");
		MAttachmentEntry[] entries = att.getEntries();
		for (int i = 0; i < entries.length; i++) {
			final MAttachmentEntry entry = entries[i];
			entry.dump();
		}
		System.out.println("===========================================");
		att.delete(true);
	} // main

	/**
	 * Update existing entry
	 * 
	 * @param i
	 * @param file
	 * @return true if success, false otherwise
	 */
	public boolean updateEntry(int i, File file) {
		if (file == null) {
			log.warning("No File");
			return false;
		}
		if (!file.exists() || file.isDirectory()) {
			log.warning("not added - " + file + ", Exists=" + file.exists() + ", Directory=" + file.isDirectory());
			return false;
		}
		log.fine("updateEntry - " + file);
		//
		// String name = file.getName();
		byte[] data = null;
		try {
			final FileInputStream fis = new FileInputStream(file);
			final ByteArrayOutputStream os = new ByteArrayOutputStream();
			final byte[] buffer = new byte[1024 * 8]; // 8kB
			int length = -1;
			while ((length = fis.read(buffer)) != -1) {
				os.write(buffer, 0, length);
			}
			fis.close();
			data = os.toByteArray();
			os.close();
		} catch (IOException ioe) {
			log.log(Level.SEVERE, "(file)", ioe);
		}
		return updateEntry(i, data);

	}

	/**
	 * Update existing entry
	 * 
	 * @param i
	 * @param data
	 * @return true if success, false otherwise
	 */
	public boolean updateEntry(int i, byte[] data) {
		final MAttachmentEntry entry = getEntry(i);
		if (entry == null) {
			return false;
		}
		entry.setData(data);
		return true;
	}

	/**
	 * Returns the AD_Attachment_ID for the PO model
	 * 
	 * @param model PO
	 * @return
	 */
	public static int getAttachId(final PO model) {
		int attachmentID;
		if (model == null) {
			attachmentID = 0;
		} else if (model.get_ColumnIndex(COLUMNNAME_AD_Attachment_ID) >= 0) {
			// if there's a column called AD_Attachment_ID in the model
			attachmentID = model.get_ValueAsInt(COLUMNNAME_AD_Attachment_ID);
		} else {
			// searchs for the AD_Attachment_ID related to the model table/record
			attachmentID = new Query(model.getCtx(), X_AD_Attachment.Table_Name, " AD_Table_ID=? AND Record_ID=? ", null)//
				.setParameters(model.get_Table_ID(), model.get_ID())//
				.firstId();
		}
		return attachmentID;
	}
	
	/**
	 * Get Attachment with Title
	 * most recent record
	 * 
	 * @param ctx context
	 * @param AD_Table_ID table
	 * @param Record_ID  record
	 * @param Title  title
	 * @return attachment or null
	 */
	public static MAttachment get(Properties ctx, int AD_Table_ID, int Record_ID, String title) {
//		final String sql = "SELECT * FROM AD_Attachment WHERE AD_Table_ID=? AND Record_ID=? AND Title=? order by Created Desc ";
		return new Query(ctx, Table_Name, " AD_Table_ID=? AND Record_ID=? AND Title=? ", null)//
		.setParameters(AD_Table_ID,Record_ID,title)//
		.setOrderBy(" Created DESC ")
		.first();
	} // get
	
	/**
	 * Add Entry
	 * 
	 * @param name File Name
	 * @param data bytes array
	 * @return MAttachmentFile
	 */
	public MAttachmentFile addFileEntry(String name, byte[] data) {
		MAttachmentEntry entry = new MAttachmentEntry(name, data, null);
		if (addEntry(entry)) {
			MAttachmentFile file = new MAttachmentFile(this);
			file.setEntry(entry);
			return file;
		}
		return null;
	} // addFileEntry

} // MAttachment
