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

/** Generated Model for EXME_CateterEnf
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_CateterEnf extends PO implements I_EXME_CateterEnf, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_CateterEnf (Properties ctx, int EXME_CateterEnf_ID, String trxName)
    {
      super (ctx, EXME_CateterEnf_ID, trxName);
      /** if (EXME_CateterEnf_ID == 0)
        {
			setEXME_CateterEnf_ID (0);
			setEXME_CtaPac_ID (0);
			setEXME_Enfermeria_Ing (0);
			setEXME_ParteCorporal_ID (0);
			setFechaIngreso (new Timestamp( System.currentTimeMillis() ));
        } */
    }

    /** Load Constructor */
    public X_EXME_CateterEnf (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_CateterEnf[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set User who removes.
		@param AD_User_Egr User who removes	  */
	public void setAD_User_Egr (int AD_User_Egr)
	{
		set_Value (COLUMNNAME_AD_User_Egr, Integer.valueOf(AD_User_Egr));
	}

	/** Get User who removes.
		@return User who removes	  */
	public int getAD_User_Egr () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_User_Egr);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set User who installs.
		@param AD_User_Ing User who installs	  */
	public void setAD_User_Ing (int AD_User_Ing)
	{
		set_Value (COLUMNNAME_AD_User_Ing, Integer.valueOf(AD_User_Ing));
	}

	/** Get User who installs.
		@return User who installs	  */
	public int getAD_User_Ing () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_User_Ing);
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

	/** Set Nursing's Catheter.
		@param EXME_CateterEnf_ID Nursing's Catheter	  */
	public void setEXME_CateterEnf_ID (int EXME_CateterEnf_ID)
	{
		if (EXME_CateterEnf_ID < 1)
			 throw new IllegalArgumentException ("EXME_CateterEnf_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_CateterEnf_ID, Integer.valueOf(EXME_CateterEnf_ID));
	}

	/** Get Nursing's Catheter.
		@return Nursing's Catheter	  */
	public int getEXME_CateterEnf_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_CateterEnf_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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

	/** Set Encounter.
		@param EXME_CtaPac_ID 
		Encounter
	  */
	public void setEXME_CtaPac_ID (int EXME_CtaPac_ID)
	{
		if (EXME_CtaPac_ID < 1)
			 throw new IllegalArgumentException ("EXME_CtaPac_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_CtaPac_ID, Integer.valueOf(EXME_CtaPac_ID));
	}

	/** Get Encounter.
		@return Encounter
	  */
	public int getEXME_CtaPac_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_CtaPac_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_DiarioEnf getEXME_DiarioEnf() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_DiarioEnf.Table_Name);
        I_EXME_DiarioEnf result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_DiarioEnf)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_DiarioEnf_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Infirmary Diary.
		@param EXME_DiarioEnf_ID Infirmary Diary	  */
	public void setEXME_DiarioEnf_ID (int EXME_DiarioEnf_ID)
	{
		if (EXME_DiarioEnf_ID < 1) 
			set_Value (COLUMNNAME_EXME_DiarioEnf_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_DiarioEnf_ID, Integer.valueOf(EXME_DiarioEnf_ID));
	}

	/** Get Infirmary Diary.
		@return Infirmary Diary	  */
	public int getEXME_DiarioEnf_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_DiarioEnf_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_EncounterForms getEXME_EncounterForms() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_EncounterForms.Table_Name);
        I_EXME_EncounterForms result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_EncounterForms)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_EncounterForms_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Encounter Forms.
		@param EXME_EncounterForms_ID 
		Encounter Forms
	  */
	public void setEXME_EncounterForms_ID (int EXME_EncounterForms_ID)
	{
		if (EXME_EncounterForms_ID < 1) 
			set_Value (COLUMNNAME_EXME_EncounterForms_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_EncounterForms_ID, Integer.valueOf(EXME_EncounterForms_ID));
	}

	/** Get Encounter Forms.
		@return Encounter Forms
	  */
	public int getEXME_EncounterForms_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_EncounterForms_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Nursing Discharge.
		@param EXME_Enfermeria_Egr Nursing Discharge	  */
	public void setEXME_Enfermeria_Egr (int EXME_Enfermeria_Egr)
	{
		set_Value (COLUMNNAME_EXME_Enfermeria_Egr, Integer.valueOf(EXME_Enfermeria_Egr));
	}

	/** Get Nursing Discharge.
		@return Nursing Discharge	  */
	public int getEXME_Enfermeria_Egr () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Enfermeria_Egr);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Nursing Admission.
		@param EXME_Enfermeria_Ing Nursing Admission	  */
	public void setEXME_Enfermeria_Ing (int EXME_Enfermeria_Ing)
	{
		set_Value (COLUMNNAME_EXME_Enfermeria_Ing, Integer.valueOf(EXME_Enfermeria_Ing));
	}

	/** Get Nursing Admission.
		@return Nursing Admission	  */
	public int getEXME_Enfermeria_Ing () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Enfermeria_Ing);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_ParteCorporal getEXME_ParteCorporal() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_ParteCorporal.Table_Name);
        I_EXME_ParteCorporal result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_ParteCorporal)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_ParteCorporal_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Body part.
		@param EXME_ParteCorporal_ID Body part	  */
	public void setEXME_ParteCorporal_ID (int EXME_ParteCorporal_ID)
	{
		if (EXME_ParteCorporal_ID < 1)
			 throw new IllegalArgumentException ("EXME_ParteCorporal_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_ParteCorporal_ID, Integer.valueOf(EXME_ParteCorporal_ID));
	}

	/** Get Body part.
		@return Body part	  */
	public int getEXME_ParteCorporal_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_ParteCorporal_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Discharge Date.
		@param FechaEgreso Discharge Date	  */
	public void setFechaEgreso (Timestamp FechaEgreso)
	{
		set_Value (COLUMNNAME_FechaEgreso, FechaEgreso);
	}

	/** Get Discharge Date.
		@return Discharge Date	  */
	public Timestamp getFechaEgreso () 
	{
		return (Timestamp)get_Value(COLUMNNAME_FechaEgreso);
	}

	/** Set Admission Date.
		@param FechaIngreso 
		Admission Date
	  */
	public void setFechaIngreso (Timestamp FechaIngreso)
	{
		if (FechaIngreso == null)
			throw new IllegalArgumentException ("FechaIngreso is mandatory.");
		set_Value (COLUMNNAME_FechaIngreso, FechaIngreso);
	}

	/** Get Admission Date.
		@return Admission Date
	  */
	public Timestamp getFechaIngreso () 
	{
		return (Timestamp)get_Value(COLUMNNAME_FechaIngreso);
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
}