/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

/** Generated Model for EXME_PoliticaDet
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_PoliticaDet extends PO implements I_EXME_PoliticaDet, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_PoliticaDet (Properties ctx, int EXME_PoliticaDet_ID, String trxName)
    {
      super (ctx, EXME_PoliticaDet_ID, trxName);
      /** if (EXME_PoliticaDet_ID == 0)
        {
			setAplicaPorc (false);
			setEXME_PoliticaDet_ID (0);
			setEXME_Politica_ID (0);
			setEXME_TipoProd_ID (0);
        } */
    }

    /** Load Constructor */
    public X_EXME_PoliticaDet (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_PoliticaDet[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Apply Percentage.
		@param AplicaPorc 
		Apply Percentage
	  */
	public void setAplicaPorc (boolean AplicaPorc)
	{
		set_Value (COLUMNNAME_AplicaPorc, Boolean.valueOf(AplicaPorc));
	}

	/** Get Apply Percentage.
		@return Apply Percentage
	  */
	public boolean isAplicaPorc () 
	{
		Object oo = get_Value(COLUMNNAME_AplicaPorc);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Detail of Policy.
		@param EXME_PoliticaDet_ID 
		Detail of policy
	  */
	public void setEXME_PoliticaDet_ID (int EXME_PoliticaDet_ID)
	{
		if (EXME_PoliticaDet_ID < 1)
			 throw new IllegalArgumentException ("EXME_PoliticaDet_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_PoliticaDet_ID, Integer.valueOf(EXME_PoliticaDet_ID));
	}

	/** Get Detail of Policy.
		@return Detail of policy
	  */
	public int getEXME_PoliticaDet_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_PoliticaDet_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_Politica getEXME_Politica() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_Politica.Table_Name);
        I_EXME_Politica result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_Politica)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Politica_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Policy.
		@param EXME_Politica_ID 
		Policy
	  */
	public void setEXME_Politica_ID (int EXME_Politica_ID)
	{
		if (EXME_Politica_ID < 1)
			 throw new IllegalArgumentException ("EXME_Politica_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_Politica_ID, Integer.valueOf(EXME_Politica_ID));
	}

	/** Get Policy.
		@return Policy
	  */
	public int getEXME_Politica_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Politica_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Product Subtype.
		@param EXME_TipoProd_ID 
		Product Subtype for hospitals
	  */
	public void setEXME_TipoProd_ID (int EXME_TipoProd_ID)
	{
		if (EXME_TipoProd_ID < 1)
			 throw new IllegalArgumentException ("EXME_TipoProd_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_TipoProd_ID, Integer.valueOf(EXME_TipoProd_ID));
	}

	/** Get Product Subtype.
		@return Product Subtype for hospitals
	  */
	public int getEXME_TipoProd_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_TipoProd_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}