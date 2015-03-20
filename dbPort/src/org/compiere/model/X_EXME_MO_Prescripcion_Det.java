/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

/** Generated Model for EXME_MO_Prescripcion_Det
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_MO_Prescripcion_Det extends PO implements I_EXME_MO_Prescripcion_Det, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_MO_Prescripcion_Det (Properties ctx, int EXME_MO_Prescripcion_Det_ID, String trxName)
    {
      super (ctx, EXME_MO_Prescripcion_Det_ID, trxName);
      /** if (EXME_MO_Prescripcion_Det_ID == 0)
        {
			setEXME_MO_Prescripcion_Det_ID (0);
			setEXME_MO_Prescripcion_ID (0);
        } */
    }

    /** Load Constructor */
    public X_EXME_MO_Prescripcion_Det (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_MO_Prescripcion_Det[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Quantity.
		@param Cantidad 
		Quantity
	  */
	public void setCantidad (int Cantidad)
	{
		set_Value (COLUMNNAME_Cantidad, Integer.valueOf(Cantidad));
	}

	/** Get Quantity.
		@return Quantity
	  */
	public int getCantidad () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Cantidad);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Number to take.
		@param Cant_Tomar Number to take	  */
	public void setCant_Tomar (int Cant_Tomar)
	{
		set_Value (COLUMNNAME_Cant_Tomar, Integer.valueOf(Cant_Tomar));
	}

	/** Get Number to take.
		@return Number to take	  */
	public int getCant_Tomar () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Cant_Tomar);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_C_UOM getC_UOM() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_C_UOM.Table_Name);
        I_C_UOM result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_C_UOM)constructor.newInstance(new Object[] {getCtx(), new Integer(getC_UOM_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set UOM.
		@param C_UOM_ID 
		Unit of Measure
	  */
	public void setC_UOM_ID (int C_UOM_ID)
	{
		if (C_UOM_ID < 1) 
			set_Value (COLUMNNAME_C_UOM_ID, null);
		else 
			set_Value (COLUMNNAME_C_UOM_ID, Integer.valueOf(C_UOM_ID));
	}

	/** Get UOM.
		@return Unit of Measure
	  */
	public int getC_UOM_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_UOM_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Detail Prescription.
		@param EXME_MO_Prescripcion_Det_ID Detail Prescription	  */
	public void setEXME_MO_Prescripcion_Det_ID (int EXME_MO_Prescripcion_Det_ID)
	{
		if (EXME_MO_Prescripcion_Det_ID < 1)
			 throw new IllegalArgumentException ("EXME_MO_Prescripcion_Det_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_MO_Prescripcion_Det_ID, Integer.valueOf(EXME_MO_Prescripcion_Det_ID));
	}

	/** Get Detail Prescription.
		@return Detail Prescription	  */
	public int getEXME_MO_Prescripcion_Det_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_MO_Prescripcion_Det_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_MO_Prescripcion getEXME_MO_Prescripcion() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_MO_Prescripcion.Table_Name);
        I_EXME_MO_Prescripcion result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_MO_Prescripcion)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_MO_Prescripcion_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Prescription.
		@param EXME_MO_Prescripcion_ID Prescription	  */
	public void setEXME_MO_Prescripcion_ID (int EXME_MO_Prescripcion_ID)
	{
		if (EXME_MO_Prescripcion_ID < 1)
			 throw new IllegalArgumentException ("EXME_MO_Prescripcion_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_MO_Prescripcion_ID, Integer.valueOf(EXME_MO_Prescripcion_ID));
	}

	/** Get Prescription.
		@return Prescription	  */
	public int getEXME_MO_Prescripcion_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_MO_Prescripcion_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Indications.
		@param Indicaciones Indications	  */
	public void setIndicaciones (String Indicaciones)
	{
		set_Value (COLUMNNAME_Indicaciones, Indicaciones);
	}

	/** Get Indications.
		@return Indications	  */
	public String getIndicaciones () 
	{
		return (String)get_Value(COLUMNNAME_Indicaciones);
	}

	public I_M_Product getM_Product() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_M_Product.Table_Name);
        I_M_Product result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_M_Product)constructor.newInstance(new Object[] {getCtx(), new Integer(getM_Product_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Product.
		@param M_Product_ID 
		Product, Service, Item
	  */
	public void setM_Product_ID (int M_Product_ID)
	{
		if (M_Product_ID < 1) 
			set_Value (COLUMNNAME_M_Product_ID, null);
		else 
			set_Value (COLUMNNAME_M_Product_ID, Integer.valueOf(M_Product_ID));
	}

	/** Get Product.
		@return Product, Service, Item
	  */
	public int getM_Product_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_Product_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Number of days.
		@param Num_Dias Number of days	  */
	public void setNum_Dias (int Num_Dias)
	{
		set_Value (COLUMNNAME_Num_Dias, Integer.valueOf(Num_Dias));
	}

	/** Get Number of days.
		@return Number of days	  */
	public int getNum_Dias () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Num_Dias);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set times on a day.
		@param Veces_Dia times on a day	  */
	public void setVeces_Dia (int Veces_Dia)
	{
		set_Value (COLUMNNAME_Veces_Dia, Integer.valueOf(Veces_Dia));
	}

	/** Get times on a day.
		@return times on a day	  */
	public int getVeces_Dia () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Veces_Dia);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}