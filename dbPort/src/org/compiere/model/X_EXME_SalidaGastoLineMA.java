/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;
import org.compiere.util.Env;

/** Generated Model for EXME_SalidaGastoLineMA
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_SalidaGastoLineMA extends PO implements I_EXME_SalidaGastoLineMA, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_SalidaGastoLineMA (Properties ctx, int EXME_SalidaGastoLineMA_ID, String trxName)
    {
      super (ctx, EXME_SalidaGastoLineMA_ID, trxName);
      /** if (EXME_SalidaGastoLineMA_ID == 0)
        {
			setEXME_SalidaGastoLine_ID (0);
			setM_AttributeSetInstance_ID (0);
			setMovementQty (Env.ZERO);
        } */
    }

    /** Load Constructor */
    public X_EXME_SalidaGastoLineMA (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_SalidaGastoLineMA[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public I_EXME_SalidaGastoLine getEXME_SalidaGastoLine() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_SalidaGastoLine.Table_Name);
        I_EXME_SalidaGastoLine result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_SalidaGastoLine)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_SalidaGastoLine_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Internal Use Inventory Line.
		@param EXME_SalidaGastoLine_ID Internal Use Inventory Line	  */
	public void setEXME_SalidaGastoLine_ID (int EXME_SalidaGastoLine_ID)
	{
		if (EXME_SalidaGastoLine_ID < 1)
			 throw new IllegalArgumentException ("EXME_SalidaGastoLine_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_SalidaGastoLine_ID, Integer.valueOf(EXME_SalidaGastoLine_ID));
	}

	/** Get Internal Use Inventory Line.
		@return Internal Use Inventory Line	  */
	public int getEXME_SalidaGastoLine_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_SalidaGastoLine_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Attribute Set Instance.
		@param M_AttributeSetInstance_ID 
		Product Attribute Set Instance
	  */
	public void setM_AttributeSetInstance_ID (int M_AttributeSetInstance_ID)
	{
		if (M_AttributeSetInstance_ID < 0)
			 throw new IllegalArgumentException ("M_AttributeSetInstance_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_M_AttributeSetInstance_ID, Integer.valueOf(M_AttributeSetInstance_ID));
	}

	/** Get Attribute Set Instance.
		@return Product Attribute Set Instance
	  */
	public int getM_AttributeSetInstance_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_AttributeSetInstance_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Movement Quantity.
		@param MovementQty 
		Quantity of a product moved.
	  */
	public void setMovementQty (BigDecimal MovementQty)
	{
		if (MovementQty == null)
			throw new IllegalArgumentException ("MovementQty is mandatory.");
		set_Value (COLUMNNAME_MovementQty, MovementQty);
	}

	/** Get Movement Quantity.
		@return Quantity of a product moved.
	  */
	public BigDecimal getMovementQty () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_MovementQty);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}
}