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

/** Generated Model for EXME_RecResiduo
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_RecResiduo extends PO implements I_EXME_RecResiduo, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_RecResiduo (Properties ctx, int EXME_RecResiduo_ID, String trxName)
    {
      super (ctx, EXME_RecResiduo_ID, trxName);
      /** if (EXME_RecResiduo_ID == 0)
        {
			setC_BPartner_ID (0);
			setEXME_RecResiduo_ID (0);
			setFechaRec (new Timestamp( System.currentTimeMillis() ));
// @#Date@
        } */
    }

    /** Load Constructor */
    public X_EXME_RecResiduo (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_RecResiduo[")
        .append(get_ID()).append("]");
      return sb.toString();
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

	/** Set Waste Collection.
		@param EXME_RecResiduo_ID 
		Lets keep track of collections that are made on such collection is made
	  */
	public void setEXME_RecResiduo_ID (int EXME_RecResiduo_ID)
	{
		if (EXME_RecResiduo_ID < 1)
			 throw new IllegalArgumentException ("EXME_RecResiduo_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_RecResiduo_ID, Integer.valueOf(EXME_RecResiduo_ID));
	}

	/** Get Waste Collection.
		@return Lets keep track of collections that are made on such collection is made
	  */
	public int getEXME_RecResiduo_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_RecResiduo_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Reception Date.
		@param FechaRec 
		Contains the date on which it carries out waste collection
	  */
	public void setFechaRec (Timestamp FechaRec)
	{
		if (FechaRec == null)
			throw new IllegalArgumentException ("FechaRec is mandatory.");
		set_Value (COLUMNNAME_FechaRec, FechaRec);
	}

	/** Get Reception Date.
		@return Contains the date on which it carries out waste collection
	  */
	public Timestamp getFechaRec () 
	{
		return (Timestamp)get_Value(COLUMNNAME_FechaRec);
	}
}