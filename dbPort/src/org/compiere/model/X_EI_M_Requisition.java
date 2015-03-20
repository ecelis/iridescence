/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

/** Generated Model for EI_M_Requisition
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EI_M_Requisition extends PO implements I_EI_M_Requisition, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EI_M_Requisition (Properties ctx, int EI_M_Requisition_ID, String trxName)
    {
      super (ctx, EI_M_Requisition_ID, trxName);
      /** if (EI_M_Requisition_ID == 0)
        {
			setDocumentNo (null);
			setEI_M_Requisition_ID (0);
			setErrorDescription (null);
			setErrorStatus (false);
			setM_Requisition_ID (0);
        } */
    }

    /** Load Constructor */
    public X_EI_M_Requisition (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EI_M_Requisition[")
        .append(get_ID()).append("]");
      return sb.toString();
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

	/** Set Requisition.
		@param EI_M_Requisition_ID 
		Requisition Number
	  */
	public void setEI_M_Requisition_ID (int EI_M_Requisition_ID)
	{
		if (EI_M_Requisition_ID < 1)
			 throw new IllegalArgumentException ("EI_M_Requisition_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EI_M_Requisition_ID, Integer.valueOf(EI_M_Requisition_ID));
	}

	/** Get Requisition.
		@return Requisition Number
	  */
	public int getEI_M_Requisition_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EI_M_Requisition_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set ErrorDescription.
		@param ErrorDescription 
		ErrorDescription
	  */
	public void setErrorDescription (String ErrorDescription)
	{
		if (ErrorDescription == null)
			throw new IllegalArgumentException ("ErrorDescription is mandatory.");
		set_Value (COLUMNNAME_ErrorDescription, ErrorDescription);
	}

	/** Get ErrorDescription.
		@return ErrorDescription
	  */
	public String getErrorDescription () 
	{
		return (String)get_Value(COLUMNNAME_ErrorDescription);
	}

	/** Set ErrorStatus.
		@param ErrorStatus 
		ErrorStatus
	  */
	public void setErrorStatus (boolean ErrorStatus)
	{
		set_Value (COLUMNNAME_ErrorStatus, Boolean.valueOf(ErrorStatus));
	}

	/** Get ErrorStatus.
		@return ErrorStatus
	  */
	public boolean isErrorStatus () 
	{
		Object oo = get_Value(COLUMNNAME_ErrorStatus);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	public I_M_Requisition getM_Requisition() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_M_Requisition.Table_Name);
        I_M_Requisition result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_M_Requisition)constructor.newInstance(new Object[] {getCtx(), new Integer(getM_Requisition_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Requisition.
		@param M_Requisition_ID 
		Material Requisition
	  */
	public void setM_Requisition_ID (int M_Requisition_ID)
	{
		if (M_Requisition_ID < 1)
			 throw new IllegalArgumentException ("M_Requisition_ID is mandatory.");
		set_Value (COLUMNNAME_M_Requisition_ID, Integer.valueOf(M_Requisition_ID));
	}

	/** Get Requisition.
		@return Material Requisition
	  */
	public int getM_Requisition_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_Requisition_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}