/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

/** Generated Model for EXME_ActPacienteIndLog
 *  @author Generated Class 
 *  @version Release 1.1.2 - $Id$ */
public class X_EXME_ActPacienteIndLog extends PO implements I_EXME_ActPacienteIndLog, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_ActPacienteIndLog (Properties ctx, int EXME_ActPacienteIndLog_ID, String trxName)
    {
      super (ctx, EXME_ActPacienteIndLog_ID, trxName);
      /** if (EXME_ActPacienteIndLog_ID == 0)
        {
			setEXME_ActPacienteIndH_ID (0);
			setEXME_ActPacienteIndLog_ID (0);
			setEXME_ActPacienteInd_ID (0);
			setValue (null);
        } */
    }

    /** Load Constructor */
    public X_EXME_ActPacienteIndLog (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 1 - Org 
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
      StringBuffer sb = new StringBuffer ("X_EXME_ActPacienteIndLog[")
        .append(get_ID()).append("]");
      return sb.toString();
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
			set_Value (COLUMNNAME_AD_Attachment_ID, null);
		else 
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

	/** Set Notes.
		@param Anotaciones 
		Notes related to the annexed image of the indication
	  */
	public void setAnotaciones (String Anotaciones)
	{
		set_Value (COLUMNNAME_Anotaciones, Anotaciones);
	}

	/** Get Notes.
		@return Notes related to the annexed image of the indication
	  */
	public String getAnotaciones () 
	{
		return (String)get_Value(COLUMNNAME_Anotaciones);
	}

	public I_EXME_ActPacienteIndH getEXME_ActPacienteIndH() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_ActPacienteIndH.Table_Name);
        I_EXME_ActPacienteIndH result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_ActPacienteIndH)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_ActPacienteIndH_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Header Indcations Patient Activity.
		@param EXME_ActPacienteIndH_ID 
		Header Indications Patient Activity
	  */
	public void setEXME_ActPacienteIndH_ID (int EXME_ActPacienteIndH_ID)
	{
		if (EXME_ActPacienteIndH_ID < 1)
			 throw new IllegalArgumentException ("EXME_ActPacienteIndH_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_ActPacienteIndH_ID, Integer.valueOf(EXME_ActPacienteIndH_ID));
	}

	/** Get Header Indcations Patient Activity.
		@return Header Indications Patient Activity
	  */
	public int getEXME_ActPacienteIndH_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_ActPacienteIndH_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Order Detail Log.
		@param EXME_ActPacienteIndLog_ID 
		Order Detail Log
	  */
	public void setEXME_ActPacienteIndLog_ID (int EXME_ActPacienteIndLog_ID)
	{
		if (EXME_ActPacienteIndLog_ID < 1)
			 throw new IllegalArgumentException ("EXME_ActPacienteIndLog_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_ActPacienteIndLog_ID, Integer.valueOf(EXME_ActPacienteIndLog_ID));
	}

	/** Get Order Detail Log.
		@return Order Detail Log
	  */
	public int getEXME_ActPacienteIndLog_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_ActPacienteIndLog_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_ActPacienteInd getEXME_ActPacienteInd() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_ActPacienteInd.Table_Name);
        I_EXME_ActPacienteInd result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_ActPacienteInd)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_ActPacienteInd_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Indications Patient Activity.
		@param EXME_ActPacienteInd_ID 
		Indications Patient Activity
	  */
	public void setEXME_ActPacienteInd_ID (int EXME_ActPacienteInd_ID)
	{
		if (EXME_ActPacienteInd_ID < 1)
			 throw new IllegalArgumentException ("EXME_ActPacienteInd_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_ActPacienteInd_ID, Integer.valueOf(EXME_ActPacienteInd_ID));
	}

	/** Get Indications Patient Activity.
		@return Indications Patient Activity
	  */
	public int getEXME_ActPacienteInd_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_ActPacienteInd_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Folio.
		@param Folio Folio	  */
	public void setFolio (int Folio)
	{
		set_Value (COLUMNNAME_Folio, Integer.valueOf(Folio));
	}

	/** Get Folio.
		@return Folio	  */
	public int getFolio () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Folio);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Reference.
		@param Reference 
		Reference for this record
	  */
	public void setReference (String Reference)
	{
		set_Value (COLUMNNAME_Reference, Reference);
	}

	/** Get Reference.
		@return Reference for this record
	  */
	public String getReference () 
	{
		return (String)get_Value(COLUMNNAME_Reference);
	}

	/** Set Sequence Number.
		@param SeqNo 
		Method of ordering records; lowest number comes first
	  */
	public void setSeqNo (int SeqNo)
	{
		set_Value (COLUMNNAME_SeqNo, Integer.valueOf(SeqNo));
	}

	/** Get Sequence Number.
		@return Method of ordering records; lowest number comes first
	  */
	public int getSeqNo () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_SeqNo);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Search Key.
		@param Value 
		Search key for the record in the format required - must be unique
	  */
	public void setValue (String Value)
	{
		if (Value == null)
			throw new IllegalArgumentException ("Value is mandatory.");
		set_Value (COLUMNNAME_Value, Value);
	}

	/** Get Search Key.
		@return Search key for the record in the format required - must be unique
	  */
	public String getValue () 
	{
		return (String)get_Value(COLUMNNAME_Value);
	}
}