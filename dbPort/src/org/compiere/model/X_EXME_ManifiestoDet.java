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

/** Generated Model for EXME_ManifiestoDet
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_ManifiestoDet extends PO implements I_EXME_ManifiestoDet, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_ManifiestoDet (Properties ctx, int EXME_ManifiestoDet_ID, String trxName)
    {
      super (ctx, EXME_ManifiestoDet_ID, trxName);
      /** if (EXME_ManifiestoDet_ID == 0)
        {
			setEXME_ManifiestoDet_ID (0);
			setEXME_Manifiesto_ID (0);
// @EXME_Manifiesto_ID@
			setEXME_Residuo_ID (0);
			setPeso (Env.ZERO);
// 1
        } */
    }

    /** Load Constructor */
    public X_EXME_ManifiestoDet (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_ManifiestoDet[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Manifesto Detail.
		@param EXME_ManifiestoDet_ID 
		Allows recording details of the vouchers issued by the collector
	  */
	public void setEXME_ManifiestoDet_ID (int EXME_ManifiestoDet_ID)
	{
		if (EXME_ManifiestoDet_ID < 1)
			 throw new IllegalArgumentException ("EXME_ManifiestoDet_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_ManifiestoDet_ID, Integer.valueOf(EXME_ManifiestoDet_ID));
	}

	/** Get Manifesto Detail.
		@return Allows recording details of the vouchers issued by the collector
	  */
	public int getEXME_ManifiestoDet_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_ManifiestoDet_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_Manifiesto getEXME_Manifiesto() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_Manifiesto.Table_Name);
        I_EXME_Manifiesto result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_Manifiesto)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Manifiesto_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Manifest.
		@param EXME_Manifiesto_ID 
		It contains the vouchers issued by the garbage collector
	  */
	public void setEXME_Manifiesto_ID (int EXME_Manifiesto_ID)
	{
		if (EXME_Manifiesto_ID < 1)
			 throw new IllegalArgumentException ("EXME_Manifiesto_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_Manifiesto_ID, Integer.valueOf(EXME_Manifiesto_ID));
	}

	/** Get Manifest.
		@return It contains the vouchers issued by the garbage collector
	  */
	public int getEXME_Manifiesto_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Manifiesto_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_Residuo getEXME_Residuo() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_Residuo.Table_Name);
        I_EXME_Residuo result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_Residuo)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Residuo_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Residue.
		@param EXME_Residuo_ID 
		Specifies the types of biological / infectious waste
	  */
	public void setEXME_Residuo_ID (int EXME_Residuo_ID)
	{
		if (EXME_Residuo_ID < 1)
			 throw new IllegalArgumentException ("EXME_Residuo_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_Residuo_ID, Integer.valueOf(EXME_Residuo_ID));
	}

	/** Get Residue.
		@return Specifies the types of biological / infectious waste
	  */
	public int getEXME_Residuo_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Residuo_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Weight.
		@param Peso 
		Weight
	  */
	public void setPeso (BigDecimal Peso)
	{
		if (Peso == null)
			throw new IllegalArgumentException ("Peso is mandatory.");
		set_Value (COLUMNNAME_Peso, Peso);
	}

	/** Get Weight.
		@return Weight
	  */
	public BigDecimal getPeso () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Peso);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}
}