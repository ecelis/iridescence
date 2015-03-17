/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

/** Generated Model for HL7_BPartnerJS
 *  @author Generated Class 
 *  @version Release 2.0.0 (alpha) - $Id$ */
public class X_HL7_BPartnerJS extends PO implements I_HL7_BPartnerJS, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_HL7_BPartnerJS (Properties ctx, int HL7_BPartnerJS_ID, String trxName)
    {
      super (ctx, HL7_BPartnerJS_ID, trxName);
      /** if (HL7_BPartnerJS_ID == 0)
        {
			setHL7_BPartnerJS_ID (0);
        } */
    }

    /** Load Constructor */
    public X_HL7_BPartnerJS (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 4 - System 
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
      StringBuffer sb = new StringBuffer ("X_HL7_BPartnerJS[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public I_HL7_BPartner getHL7_BPartner() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_HL7_BPartner.Table_Name);
        I_HL7_BPartner result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_HL7_BPartner)constructor.newInstance(new Object[] {getCtx(), new Integer(getHL7_BPartner_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Messaging Partner.
		@param HL7_BPartner_ID 
		Identifies a Messaging Partner
	  */
	public void setHL7_BPartner_ID (int HL7_BPartner_ID)
	{
		if (HL7_BPartner_ID < 1) 
			set_Value (COLUMNNAME_HL7_BPartner_ID, null);
		else 
			set_Value (COLUMNNAME_HL7_BPartner_ID, Integer.valueOf(HL7_BPartner_ID));
	}

	/** Get Messaging Partner.
		@return Identifies a Messaging Partner
	  */
	public int getHL7_BPartner_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HL7_BPartner_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Connector HL7 Javascript.
		@param HL7_BPartnerJS_ID Connector HL7 Javascript	  */
	public void setHL7_BPartnerJS_ID (int HL7_BPartnerJS_ID)
	{
		if (HL7_BPartnerJS_ID < 1)
			 throw new IllegalArgumentException ("HL7_BPartnerJS_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_HL7_BPartnerJS_ID, Integer.valueOf(HL7_BPartnerJS_ID));
	}

	/** Get Connector HL7 Javascript.
		@return Connector HL7 Javascript	  */
	public int getHL7_BPartnerJS_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HL7_BPartnerJS_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Is Inbound.
		@param IsInbound Is Inbound	  */
	public void setIsInbound (boolean IsInbound)
	{
		set_Value (COLUMNNAME_IsInbound, Boolean.valueOf(IsInbound));
	}

	/** Get Is Inbound.
		@return Is Inbound	  */
	public boolean isInbound () 
	{
		Object oo = get_Value(COLUMNNAME_IsInbound);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Javascript.
		@param Javascript 
		Javascript code used to send the message
	  */
	public void setJavascript (String Javascript)
	{
		set_Value (COLUMNNAME_Javascript, Javascript);
	}

	/** Get Javascript.
		@return Javascript code used to send the message
	  */
	public String getJavascript () 
	{
		return (String)get_Value(COLUMNNAME_Javascript);
	}
}