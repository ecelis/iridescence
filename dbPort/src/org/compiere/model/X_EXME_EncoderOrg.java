/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

/** Generated Model for EXME_EncoderOrg
 *  @author Generated Class 
 *  @version Version @VERSION@ - $Id$ */
public class X_EXME_EncoderOrg extends PO implements I_EXME_EncoderOrg, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_EncoderOrg (Properties ctx, int EXME_EncoderOrg_ID, String trxName)
    {
      super (ctx, EXME_EncoderOrg_ID, trxName);
      /** if (EXME_EncoderOrg_ID == 0)
        {
			setEXME_ConfigEncoder_ID (0);
			setEXME_EncoderOrg_ID (0);
			setRel_Client_ID (0);
			setRel_Org_ID (0);
			setUseTrucode (true);
// Y
        } */
    }

    /** Load Constructor */
    public X_EXME_EncoderOrg (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 9 - System 
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
      StringBuffer sb = new StringBuffer ("X_EXME_EncoderOrg[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public I_EXME_ConfigEncoder getEXME_ConfigEncoder() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_ConfigEncoder.Table_Name);
        I_EXME_ConfigEncoder result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_ConfigEncoder)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_ConfigEncoder_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Encoder configuration.
		@param EXME_ConfigEncoder_ID 
		Encoder configuration
	  */
	public void setEXME_ConfigEncoder_ID (int EXME_ConfigEncoder_ID)
	{
		if (EXME_ConfigEncoder_ID < 1)
			 throw new IllegalArgumentException ("EXME_ConfigEncoder_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_ConfigEncoder_ID, Integer.valueOf(EXME_ConfigEncoder_ID));
	}

	/** Get Encoder configuration.
		@return Encoder configuration
	  */
	public int getEXME_ConfigEncoder_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_ConfigEncoder_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Encoding by organization.
		@param EXME_EncoderOrg_ID 
		Encoding by organization
	  */
	public void setEXME_EncoderOrg_ID (int EXME_EncoderOrg_ID)
	{
		if (EXME_EncoderOrg_ID < 1)
			 throw new IllegalArgumentException ("EXME_EncoderOrg_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_EncoderOrg_ID, Integer.valueOf(EXME_EncoderOrg_ID));
	}

	/** Get Encoding by organization.
		@return Encoding by organization
	  */
	public int getEXME_EncoderOrg_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_EncoderOrg_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Client.
		@param Rel_Client_ID 
		Client
	  */
	public void setRel_Client_ID (int Rel_Client_ID)
	{
		if (Rel_Client_ID < 1)
			 throw new IllegalArgumentException ("Rel_Client_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_Rel_Client_ID, Integer.valueOf(Rel_Client_ID));
	}

	/** Get Client.
		@return Client
	  */
	public int getRel_Client_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Rel_Client_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Organization.
		@param Rel_Org_ID 
		Organization
	  */
	public void setRel_Org_ID (int Rel_Org_ID)
	{
		if (Rel_Org_ID < 1)
			 throw new IllegalArgumentException ("Rel_Org_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_Rel_Org_ID, Integer.valueOf(Rel_Org_ID));
	}

	/** Get Organization.
		@return Organization
	  */
	public int getRel_Org_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Rel_Org_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Client uses the encoding.
		@param UseTrucode 
		Client uses the encoding
	  */
	public void setUseTrucode (boolean UseTrucode)
	{
		set_Value (COLUMNNAME_UseTrucode, Boolean.valueOf(UseTrucode));
	}

	/** Get Client uses the encoding.
		@return Client uses the encoding
	  */
	public boolean isUseTrucode () 
	{
		Object oo = get_Value(COLUMNNAME_UseTrucode);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}
}