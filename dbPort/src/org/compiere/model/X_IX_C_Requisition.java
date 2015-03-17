/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

/** Generated Model for IX_C_Requisition
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_IX_C_Requisition extends PO implements I_IX_C_Requisition, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_IX_C_Requisition (Properties ctx, int IX_C_Requisition_ID, String trxName)
    {
      super (ctx, IX_C_Requisition_ID, trxName);
      /** if (IX_C_Requisition_ID == 0)
        {
			setIX_C_Requisition_ID (0);
			setM_Requisition_ID (0);
			setNombreArchivoInterfase (null);
        } */
    }

    /** Load Constructor */
    public X_IX_C_Requisition (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_IX_C_Requisition[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Requisition.
		@param IX_C_Requisition_ID 
		Requisition
	  */
	public void setIX_C_Requisition_ID (int IX_C_Requisition_ID)
	{
		if (IX_C_Requisition_ID < 1)
			 throw new IllegalArgumentException ("IX_C_Requisition_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_IX_C_Requisition_ID, Integer.valueOf(IX_C_Requisition_ID));
	}

	/** Get Requisition.
		@return Requisition
	  */
	public int getIX_C_Requisition_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_IX_C_Requisition_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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

	/** Set Interface File Name.
		@param NombreArchivoInterfase Interface File Name	  */
	public void setNombreArchivoInterfase (String NombreArchivoInterfase)
	{
		if (NombreArchivoInterfase == null)
			throw new IllegalArgumentException ("NombreArchivoInterfase is mandatory.");
		set_Value (COLUMNNAME_NombreArchivoInterfase, NombreArchivoInterfase);
	}

	/** Get Interface File Name.
		@return Interface File Name	  */
	public String getNombreArchivoInterfase () 
	{
		return (String)get_Value(COLUMNNAME_NombreArchivoInterfase);
	}
}