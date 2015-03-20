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
import org.compiere.util.KeyNamePair;

/** Generated Model for EXME_ProtocoloInv
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_ProtocoloInv extends PO implements I_EXME_ProtocoloInv, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_ProtocoloInv (Properties ctx, int EXME_ProtocoloInv_ID, String trxName)
    {
      super (ctx, EXME_ProtocoloInv_ID, trxName);
      /** if (EXME_ProtocoloInv_ID == 0)
        {
			setDateApproved (new Timestamp( System.currentTimeMillis() ));
			setDocAction (null);
			setDocStatus (null);
			setDocumentNo (null);
			setEXME_Investigador_ID (0);
			setEXME_ProtocoloInv_ID (0);
			setIsApproved (false);
			setIsCreated (false);
			setIsVerified (false);
			setValue (null);
        } */
    }

    /** Load Constructor */
    public X_EXME_ProtocoloInv (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 3 - Client - Org 
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
      StringBuffer sb = new StringBuffer ("X_EXME_ProtocoloInv[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Selection Criterion.
		@param Criterio Selection Criterion	  */
	public void setCriterio (String Criterio)
	{
		set_Value (COLUMNNAME_Criterio, Criterio);
	}

	/** Get Selection Criterion.
		@return Selection Criterion	  */
	public String getCriterio () 
	{
		return (String)get_Value(COLUMNNAME_Criterio);
	}

	/** Set Date Approved.
		@param DateApproved Date Approved	  */
	public void setDateApproved (Timestamp DateApproved)
	{
		if (DateApproved == null)
			throw new IllegalArgumentException ("DateApproved is mandatory.");
		set_Value (COLUMNNAME_DateApproved, DateApproved);
	}

	/** Get Date Approved.
		@return Date Approved	  */
	public Timestamp getDateApproved () 
	{
		return (Timestamp)get_Value(COLUMNNAME_DateApproved);
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

	/** Set Document Action.
		@param DocAction 
		The targeted status of the document
	  */
	public void setDocAction (String DocAction)
	{
		if (DocAction == null)
			throw new IllegalArgumentException ("DocAction is mandatory.");
		set_Value (COLUMNNAME_DocAction, DocAction);
	}

	/** Get Document Action.
		@return The targeted status of the document
	  */
	public String getDocAction () 
	{
		return (String)get_Value(COLUMNNAME_DocAction);
	}

	/** Set Document Status.
		@param DocStatus 
		The current status of the document
	  */
	public void setDocStatus (String DocStatus)
	{
		if (DocStatus == null)
			throw new IllegalArgumentException ("DocStatus is mandatory.");
		set_Value (COLUMNNAME_DocStatus, DocStatus);
	}

	/** Get Document Status.
		@return The current status of the document
	  */
	public String getDocStatus () 
	{
		return (String)get_Value(COLUMNNAME_DocStatus);
	}

	/** Set Document No.
		@param DocumentNo 
		Document sequence number of the document
	  */
	public void setDocumentNo (String DocumentNo)
	{
		if (DocumentNo == null)
			throw new IllegalArgumentException ("DocumentNo is mandatory.");
		set_Value (COLUMNNAME_DocumentNo, DocumentNo);
	}

	/** Get Document No.
		@return Document sequence number of the document
	  */
	public String getDocumentNo () 
	{
		return (String)get_Value(COLUMNNAME_DocumentNo);
	}

	public I_EXME_Investigador getEXME_Investigador() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_Investigador.Table_Name);
        I_EXME_Investigador result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_Investigador)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Investigador_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Researcher.
		@param EXME_Investigador_ID 
		Researcher
	  */
	public void setEXME_Investigador_ID (int EXME_Investigador_ID)
	{
		if (EXME_Investigador_ID < 1)
			 throw new IllegalArgumentException ("EXME_Investigador_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_Investigador_ID, Integer.valueOf(EXME_Investigador_ID));
	}

	/** Get Researcher.
		@return Researcher
	  */
	public int getEXME_Investigador_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Investigador_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Researching Protocol.
		@param EXME_ProtocoloInv_ID 
		Researching Protocol
	  */
	public void setEXME_ProtocoloInv_ID (int EXME_ProtocoloInv_ID)
	{
		if (EXME_ProtocoloInv_ID < 1)
			 throw new IllegalArgumentException ("EXME_ProtocoloInv_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_ProtocoloInv_ID, Integer.valueOf(EXME_ProtocoloInv_ID));
	}

	/** Get Researching Protocol.
		@return Researching Protocol
	  */
	public int getEXME_ProtocoloInv_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_ProtocoloInv_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Approved.
		@param IsApproved 
		Indicates if this document requires approval
	  */
	public void setIsApproved (boolean IsApproved)
	{
		set_Value (COLUMNNAME_IsApproved, Boolean.valueOf(IsApproved));
	}

	/** Get Approved.
		@return Indicates if this document requires approval
	  */
	public boolean isApproved () 
	{
		Object oo = get_Value(COLUMNNAME_IsApproved);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Records created.
		@param IsCreated Records created	  */
	public void setIsCreated (boolean IsCreated)
	{
		set_Value (COLUMNNAME_IsCreated, Boolean.valueOf(IsCreated));
	}

	/** Get Records created.
		@return Records created	  */
	public boolean isCreated () 
	{
		Object oo = get_Value(COLUMNNAME_IsCreated);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Verified.
		@param IsVerified 
		The BOM configuration has been verified
	  */
	public void setIsVerified (boolean IsVerified)
	{
		set_Value (COLUMNNAME_IsVerified, Boolean.valueOf(IsVerified));
	}

	/** Get Verified.
		@return The BOM configuration has been verified
	  */
	public boolean isVerified () 
	{
		Object oo = get_Value(COLUMNNAME_IsVerified);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Name.
		@param Name 
		Alphanumeric identifier of the entity
	  */
	public void setName (String Name)
	{
		set_Value (COLUMNNAME_Name, Name);
	}

	/** Get Name.
		@return Alphanumeric identifier of the entity
	  */
	public String getName () 
	{
		return (String)get_Value(COLUMNNAME_Name);
	}

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), getName());
    }

	/** Set Objetive.
		@param Objetivo Objetive	  */
	public void setObjetivo (String Objetivo)
	{
		set_Value (COLUMNNAME_Objetivo, Objetivo);
	}

	/** Get Objetive.
		@return Objetive	  */
	public String getObjetivo () 
	{
		return (String)get_Value(COLUMNNAME_Objetivo);
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

	/** Set Validity final date.
		@param Vigencia_Fin 
		Validity final date
	  */
	public void setVigencia_Fin (Timestamp Vigencia_Fin)
	{
		set_Value (COLUMNNAME_Vigencia_Fin, Vigencia_Fin);
	}

	/** Get Validity final date.
		@return Validity final date
	  */
	public Timestamp getVigencia_Fin () 
	{
		return (Timestamp)get_Value(COLUMNNAME_Vigencia_Fin);
	}

	/** Set Validity initial date.
		@param Vigencia_Ini 
		Validity initial date
	  */
	public void setVigencia_Ini (Timestamp Vigencia_Ini)
	{
		set_Value (COLUMNNAME_Vigencia_Ini, Vigencia_Ini);
	}

	/** Get Validity initial date.
		@return Validity initial date
	  */
	public Timestamp getVigencia_Ini () 
	{
		return (Timestamp)get_Value(COLUMNNAME_Vigencia_Ini);
	}
}