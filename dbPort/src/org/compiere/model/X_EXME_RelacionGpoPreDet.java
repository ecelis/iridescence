/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

/** Generated Model for EXME_RelacionGpoPreDet
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_RelacionGpoPreDet extends PO implements I_EXME_RelacionGpoPreDet, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_RelacionGpoPreDet (Properties ctx, int EXME_RelacionGpoPreDet_ID, String trxName)
    {
      super (ctx, EXME_RelacionGpoPreDet_ID, trxName);
      /** if (EXME_RelacionGpoPreDet_ID == 0)
        {
			setEXME_RelacionGpoPreDet_ID (0);
			setEXME_RelacionGpoPre_ID (0);
			setLineNo (0);
			setM_Product_Category_ID (0);
			setValue (null);
        } */
    }

    /** Load Constructor */
    public X_EXME_RelacionGpoPreDet (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_RelacionGpoPreDet[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Price Group Relation Detail.
		@param EXME_RelacionGpoPreDet_ID 
		Price Group Relation Detail
	  */
	public void setEXME_RelacionGpoPreDet_ID (int EXME_RelacionGpoPreDet_ID)
	{
		if (EXME_RelacionGpoPreDet_ID < 1)
			 throw new IllegalArgumentException ("EXME_RelacionGpoPreDet_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_RelacionGpoPreDet_ID, Integer.valueOf(EXME_RelacionGpoPreDet_ID));
	}

	/** Get Price Group Relation Detail.
		@return Price Group Relation Detail
	  */
	public int getEXME_RelacionGpoPreDet_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_RelacionGpoPreDet_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Price Group Relation.
		@param EXME_RelacionGpoPre_ID 
		Price Group Relation
	  */
	public void setEXME_RelacionGpoPre_ID (int EXME_RelacionGpoPre_ID)
	{
		if (EXME_RelacionGpoPre_ID < 1)
			 throw new IllegalArgumentException ("EXME_RelacionGpoPre_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_RelacionGpoPre_ID, Integer.valueOf(EXME_RelacionGpoPre_ID));
	}

	/** Get Price Group Relation.
		@return Price Group Relation
	  */
	public int getEXME_RelacionGpoPre_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_RelacionGpoPre_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Line.
		@param LineNo 
		Line No
	  */
	public void setLineNo (int LineNo)
	{
		set_Value (COLUMNNAME_LineNo, Integer.valueOf(LineNo));
	}

	/** Get Line.
		@return Line No
	  */
	public int getLineNo () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_LineNo);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_M_Product_Category getM_Product_Category() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_M_Product_Category.Table_Name);
        I_M_Product_Category result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_M_Product_Category)constructor.newInstance(new Object[] {getCtx(), new Integer(getM_Product_Category_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Product Category.
		@param M_Product_Category_ID 
		Category of a Product
	  */
	public void setM_Product_Category_ID (int M_Product_Category_ID)
	{
		if (M_Product_Category_ID < 1)
			 throw new IllegalArgumentException ("M_Product_Category_ID is mandatory.");
		set_Value (COLUMNNAME_M_Product_Category_ID, Integer.valueOf(M_Product_Category_ID));
	}

	/** Get Product Category.
		@return Category of a Product
	  */
	public int getM_Product_Category_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_Product_Category_ID);
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