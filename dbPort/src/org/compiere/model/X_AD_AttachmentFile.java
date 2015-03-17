/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;
import java.util.logging.Level;

/** Generated Model for AD_AttachmentFile
 *  @author Generated Class 
 *  @version Version @VERSION@ - $Id$ */
public class X_AD_AttachmentFile extends PO implements I_AD_AttachmentFile, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_AD_AttachmentFile (Properties ctx, int AD_AttachmentFile_ID, String trxName)
    {
      super (ctx, AD_AttachmentFile_ID, trxName);
      /** if (AD_AttachmentFile_ID == 0)
        {
			setAD_AttachmentFile_ID (0);
			setAD_Attachment_ID (0);
        } */
    }

    /** Load Constructor */
    public X_AD_AttachmentFile (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 7 - System - Client - Org 
      */
    protected int get_AccessLevel()
    {
      return accessLevel.intValue();
    }

    /** Load Meta Data */
    protected POInfo initPO (Properties ctx)
    {
      POInfo poi = POInfo.getPOInfo (ctx, Table_ID, get_TrxName());
      return poi;
    }

    public String toString()
    {
      StringBuffer sb = new StringBuffer ("X_AD_AttachmentFile[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Attachment File.
		@param AD_AttachmentFile_ID Attachment File	  */
	public void setAD_AttachmentFile_ID (int AD_AttachmentFile_ID)
	{
		if (AD_AttachmentFile_ID < 1)
			 throw new IllegalArgumentException ("AD_AttachmentFile_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_AD_AttachmentFile_ID, Integer.valueOf(AD_AttachmentFile_ID));
	}

	/** Get Attachment File.
		@return Attachment File	  */
	public int getAD_AttachmentFile_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_AttachmentFile_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_AD_AttachmentType getAD_AttachmentType() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_AD_AttachmentType.Table_Name);
        I_AD_AttachmentType result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_AD_AttachmentType)constructor.newInstance(new Object[] {getCtx(), new Integer(getAD_AttachmentType_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Attachment type.
		@param AD_AttachmentType_ID Attachment type	  */
	public void setAD_AttachmentType_ID (int AD_AttachmentType_ID)
	{
		if (AD_AttachmentType_ID < 1) 
			set_Value (COLUMNNAME_AD_AttachmentType_ID, null);
		else 
			set_Value (COLUMNNAME_AD_AttachmentType_ID, Integer.valueOf(AD_AttachmentType_ID));
	}

	/** Get Attachment type.
		@return Attachment type	  */
	public int getAD_AttachmentType_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_AttachmentType_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_AD_Attachment getAD_Attachment() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_AD_Attachment.Table_Name);
        I_AD_Attachment result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_AD_Attachment)constructor.newInstance(new Object[] {getCtx(), new Integer(getAD_Attachment_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Attachment.
		@param AD_Attachment_ID 
		Attachment for the document
	  */
	public void setAD_Attachment_ID (int AD_Attachment_ID)
	{
		if (AD_Attachment_ID < 1)
			 throw new IllegalArgumentException ("AD_Attachment_ID is mandatory.");
		set_Value (COLUMNNAME_AD_Attachment_ID, Integer.valueOf(AD_Attachment_ID));
	}

	/** Get Attachment.
		@return Attachment for the document
	  */
	public int getAD_Attachment_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_Attachment_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Document Date.
		@param DateDoc 
		Date of the Document
	  */
	public void setDateDoc (Timestamp DateDoc)
	{
		set_Value (COLUMNNAME_DateDoc, DateDoc);
	}

	/** Get Document Date.
		@return Date of the Document
	  */
	public Timestamp getDateDoc () 
	{
		return (Timestamp)get_Value(COLUMNNAME_DateDoc);
	}

	/** Set Description.
		@param Description 
		Optional short description of the record
	  */
	public void setDescription (String Description)
	{
		set_Value (COLUMNNAME_Description, Description);
	}

	/** Get Description.
		@return Optional short description of the record
	  */
	public String getDescription () 
	{
		return (String)get_Value(COLUMNNAME_Description);
	}

	/** Set File Name.
		@param FileName 
		Name of the local file or URL
	  */
	public void setFileName (String FileName)
	{
		set_Value (COLUMNNAME_FileName, FileName);
	}

	/** Get File Name.
		@return Name of the local file or URL
	  */
	public String getFileName () 
	{
		return (String)get_Value(COLUMNNAME_FileName);
	}
}