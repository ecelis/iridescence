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

/** Generated Model for EXME_T_PaqCtaPac
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_T_PaqCtaPac extends PO implements I_EXME_T_PaqCtaPac, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_T_PaqCtaPac (Properties ctx, int EXME_T_PaqCtaPac_ID, String trxName)
    {
      super (ctx, EXME_T_PaqCtaPac_ID, trxName);
      /** if (EXME_T_PaqCtaPac_ID == 0)
        {
			setCostoExtras (Env.ZERO);
			setCostoPaq (Env.ZERO);
			setDifferenceQty (Env.ZERO);
			setEXME_T_PaqCtaPac_ID (0);
			setExtras (Env.ZERO);
			setQtyAplied (Env.ZERO);
			setQtyPaquete (Env.ZERO);
			setQtySustituto (Env.ZERO);
			setUsadoCostoPaq (Env.ZERO);
			setUsadoUtilidad (Env.ZERO);
			setUsadoVenta (Env.ZERO);
			setUtilidad (Env.ZERO);
			setVentaPaq (Env.ZERO);
        } */
    }

    /** Load Constructor */
    public X_EXME_T_PaqCtaPac (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_T_PaqCtaPac[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public I_AD_Session getAD_Session() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_AD_Session.Table_Name);
        I_AD_Session result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_AD_Session)constructor.newInstance(new Object[] {getCtx(), new Integer(getAD_Session_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Session.
		@param AD_Session_ID 
		User Session Online or Web
	  */
	public void setAD_Session_ID (int AD_Session_ID)
	{
		if (AD_Session_ID < 1) 
			set_Value (COLUMNNAME_AD_Session_ID, null);
		else 
			set_Value (COLUMNNAME_AD_Session_ID, Integer.valueOf(AD_Session_ID));
	}

	/** Get Session.
		@return User Session Online or Web
	  */
	public int getAD_Session_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_Session_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Cost of the Extras.
		@param CostoExtras 
		Cost of the Extras
	  */
	public void setCostoExtras (BigDecimal CostoExtras)
	{
		if (CostoExtras == null)
			throw new IllegalArgumentException ("CostoExtras is mandatory.");
		set_Value (COLUMNNAME_CostoExtras, CostoExtras);
	}

	/** Get Cost of the Extras.
		@return Cost of the Extras
	  */
	public BigDecimal getCostoExtras () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_CostoExtras);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Cost in the Package.
		@param CostoPaq 
		Cost in the Package
	  */
	public void setCostoPaq (BigDecimal CostoPaq)
	{
		if (CostoPaq == null)
			throw new IllegalArgumentException ("CostoPaq is mandatory.");
		set_Value (COLUMNNAME_CostoPaq, CostoPaq);
	}

	/** Get Cost in the Package.
		@return Cost in the Package
	  */
	public BigDecimal getCostoPaq () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_CostoPaq);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Difference.
		@param DifferenceQty 
		Difference Quantity
	  */
	public void setDifferenceQty (BigDecimal DifferenceQty)
	{
		if (DifferenceQty == null)
			throw new IllegalArgumentException ("DifferenceQty is mandatory.");
		set_Value (COLUMNNAME_DifferenceQty, DifferenceQty);
	}

	/** Get Difference.
		@return Difference Quantity
	  */
	public BigDecimal getDifferenceQty () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_DifferenceQty);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	public I_EXME_CtaPac getEXME_CtaPac() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_CtaPac.Table_Name);
        I_EXME_CtaPac result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_CtaPac)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_CtaPac_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Patient Account.
		@param EXME_CtaPac_ID 
		Patient Account
	  */
	public void setEXME_CtaPac_ID (int EXME_CtaPac_ID)
	{
		if (EXME_CtaPac_ID < 1) 
			set_Value (COLUMNNAME_EXME_CtaPac_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_CtaPac_ID, Integer.valueOf(EXME_CtaPac_ID));
	}

	/** Get Patient Account.
		@return Patient Account
	  */
	public int getEXME_CtaPac_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_CtaPac_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_PaqBase_Version getEXME_PaqBase_Version() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_PaqBase_Version.Table_Name);
        I_EXME_PaqBase_Version result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_PaqBase_Version)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_PaqBase_Version_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Package Version.
		@param EXME_PaqBase_Version_ID 
		Package Version
	  */
	public void setEXME_PaqBase_Version_ID (int EXME_PaqBase_Version_ID)
	{
		if (EXME_PaqBase_Version_ID < 1) 
			set_Value (COLUMNNAME_EXME_PaqBase_Version_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_PaqBase_Version_ID, Integer.valueOf(EXME_PaqBase_Version_ID));
	}

	/** Get Package Version.
		@return Package Version
	  */
	public int getEXME_PaqBase_Version_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_PaqBase_Version_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Package Vs Patient Account.
		@param EXME_T_PaqCtaPac_ID 
		Package Vs Patient Account
	  */
	public void setEXME_T_PaqCtaPac_ID (int EXME_T_PaqCtaPac_ID)
	{
		if (EXME_T_PaqCtaPac_ID < 1)
			 throw new IllegalArgumentException ("EXME_T_PaqCtaPac_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_T_PaqCtaPac_ID, Integer.valueOf(EXME_T_PaqCtaPac_ID));
	}

	/** Get Package Vs Patient Account.
		@return Package Vs Patient Account
	  */
	public int getEXME_T_PaqCtaPac_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_T_PaqCtaPac_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Extras.
		@param Extras 
		Extras
	  */
	public void setExtras (BigDecimal Extras)
	{
		if (Extras == null)
			throw new IllegalArgumentException ("Extras is mandatory.");
		set_Value (COLUMNNAME_Extras, Extras);
	}

	/** Get Extras.
		@return Extras
	  */
	public BigDecimal getExtras () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Extras);
		if (bd == null)
			 return Env.ZERO;
		return bd;
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

	/** Set QtyAplied.
		@param QtyAplied QtyAplied	  */
	public void setQtyAplied (BigDecimal QtyAplied)
	{
		if (QtyAplied == null)
			throw new IllegalArgumentException ("QtyAplied is mandatory.");
		set_Value (COLUMNNAME_QtyAplied, QtyAplied);
	}

	/** Get QtyAplied.
		@return QtyAplied	  */
	public BigDecimal getQtyAplied () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_QtyAplied);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Package Quantity.
		@param QtyPaquete 
		Package Quantity
	  */
	public void setQtyPaquete (BigDecimal QtyPaquete)
	{
		if (QtyPaquete == null)
			throw new IllegalArgumentException ("QtyPaquete is mandatory.");
		set_Value (COLUMNNAME_QtyPaquete, QtyPaquete);
	}

	/** Get Package Quantity.
		@return Package Quantity
	  */
	public BigDecimal getQtyPaquete () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_QtyPaquete);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Sustitute Quantity.
		@param QtySustituto 
		Sustitute Quantity
	  */
	public void setQtySustituto (BigDecimal QtySustituto)
	{
		if (QtySustituto == null)
			throw new IllegalArgumentException ("QtySustituto is mandatory.");
		set_Value (COLUMNNAME_QtySustituto, QtySustituto);
	}

	/** Get Sustitute Quantity.
		@return Sustitute Quantity
	  */
	public BigDecimal getQtySustituto () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_QtySustituto);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Substitute.
		@param Substitute_ID 
		Entity which can be used in place of this entity
	  */
	public void setSubstitute_ID (int Substitute_ID)
	{
		if (Substitute_ID < 1) 
			set_Value (COLUMNNAME_Substitute_ID, null);
		else 
			set_Value (COLUMNNAME_Substitute_ID, Integer.valueOf(Substitute_ID));
	}

	/** Get Substitute.
		@return Entity which can be used in place of this entity
	  */
	public int getSubstitute_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Substitute_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Used Package Cost Amount.
		@param UsadoCostoPaq 
		Used Package Cost Amount
	  */
	public void setUsadoCostoPaq (BigDecimal UsadoCostoPaq)
	{
		if (UsadoCostoPaq == null)
			throw new IllegalArgumentException ("UsadoCostoPaq is mandatory.");
		set_Value (COLUMNNAME_UsadoCostoPaq, UsadoCostoPaq);
	}

	/** Get Used Package Cost Amount.
		@return Used Package Cost Amount
	  */
	public BigDecimal getUsadoCostoPaq () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_UsadoCostoPaq);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Used in Utility.
		@param UsadoUtilidad 
		Used in Utility
	  */
	public void setUsadoUtilidad (BigDecimal UsadoUtilidad)
	{
		if (UsadoUtilidad == null)
			throw new IllegalArgumentException ("UsadoUtilidad is mandatory.");
		set_Value (COLUMNNAME_UsadoUtilidad, UsadoUtilidad);
	}

	/** Get Used in Utility.
		@return Used in Utility
	  */
	public BigDecimal getUsadoUtilidad () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_UsadoUtilidad);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Used in Sale Amount.
		@param UsadoVenta 
		Used in Sale Amount
	  */
	public void setUsadoVenta (BigDecimal UsadoVenta)
	{
		if (UsadoVenta == null)
			throw new IllegalArgumentException ("UsadoVenta is mandatory.");
		set_Value (COLUMNNAME_UsadoVenta, UsadoVenta);
	}

	/** Get Used in Sale Amount.
		@return Used in Sale Amount
	  */
	public BigDecimal getUsadoVenta () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_UsadoVenta);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Utility.
		@param Utilidad 
		Utility
	  */
	public void setUtilidad (BigDecimal Utilidad)
	{
		if (Utilidad == null)
			throw new IllegalArgumentException ("Utilidad is mandatory.");
		set_Value (COLUMNNAME_Utilidad, Utilidad);
	}

	/** Get Utility.
		@return Utility
	  */
	public BigDecimal getUtilidad () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Utilidad);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Package Sale Amount.
		@param VentaPaq 
		Package Sale Amount
	  */
	public void setVentaPaq (BigDecimal VentaPaq)
	{
		if (VentaPaq == null)
			throw new IllegalArgumentException ("VentaPaq is mandatory.");
		set_Value (COLUMNNAME_VentaPaq, VentaPaq);
	}

	/** Get Package Sale Amount.
		@return Package Sale Amount
	  */
	public BigDecimal getVentaPaq () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_VentaPaq);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}
}