/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

/** Generated Model for EXME_LeyendaCte
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_LeyendaCte extends PO implements I_EXME_LeyendaCte, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_LeyendaCte (Properties ctx, int EXME_LeyendaCte_ID, String trxName)
    {
      super (ctx, EXME_LeyendaCte_ID, trxName);
      /** if (EXME_LeyendaCte_ID == 0)
        {
			setAgregar_Fechas (false);
			setC_BPartner_ID (0);
			setEXME_LeyendaCte_ID (0);
        } */
    }

    /** Load Constructor */
    public X_EXME_LeyendaCte (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_LeyendaCte[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Add Dates.
		@param Agregar_Fechas 
		Add Dates
	  */
	public void setAgregar_Fechas (boolean Agregar_Fechas)
	{
		set_Value (COLUMNNAME_Agregar_Fechas, Boolean.valueOf(Agregar_Fechas));
	}

	/** Get Add Dates.
		@return Add Dates
	  */
	public boolean isAgregar_Fechas () 
	{
		Object oo = get_Value(COLUMNNAME_Agregar_Fechas);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	public I_C_BPartner getC_BPartner() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_C_BPartner.Table_Name);
        I_C_BPartner result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_C_BPartner)constructor.newInstance(new Object[] {getCtx(), new Integer(getC_BPartner_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Business Partner.
		@param C_BPartner_ID 
		Identifier for a Business Partner
	  */
	public void setC_BPartner_ID (int C_BPartner_ID)
	{
		if (C_BPartner_ID < 1)
			 throw new IllegalArgumentException ("C_BPartner_ID is mandatory.");
		set_Value (COLUMNNAME_C_BPartner_ID, Integer.valueOf(C_BPartner_ID));
	}

	/** Get Business Partner.
		@return Identifier for a Business Partner
	  */
	public int getC_BPartner_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_BPartner_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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

	/** Set Client Special Legend.
		@param EXME_LeyendaCte_ID 
		Client Special Legend
	  */
	public void setEXME_LeyendaCte_ID (int EXME_LeyendaCte_ID)
	{
		if (EXME_LeyendaCte_ID < 1)
			 throw new IllegalArgumentException ("EXME_LeyendaCte_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_LeyendaCte_ID, Integer.valueOf(EXME_LeyendaCte_ID));
	}

	/** Get Client Special Legend.
		@return Client Special Legend
	  */
	public int getEXME_LeyendaCte_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_LeyendaCte_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Area Type.
		@param TipoArea 
		Admission Area Type
	  */
	public void setTipoArea (String TipoArea)
	{
		set_Value (COLUMNNAME_TipoArea, TipoArea);
	}

	/** Get Area Type.
		@return Admission Area Type
	  */
	public String getTipoArea () 
	{
		return (String)get_Value(COLUMNNAME_TipoArea);
	}
}