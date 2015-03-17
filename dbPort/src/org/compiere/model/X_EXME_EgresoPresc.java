/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;
import java.util.logging.Level;
import org.compiere.util.Env;

/** Generated Model for EXME_EgresoPresc
 *  @author Generated Class 
 *  @version Version @VERSION@ - $Id$ */
public class X_EXME_EgresoPresc extends PO implements I_EXME_EgresoPresc, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_EgresoPresc (Properties ctx, int EXME_EgresoPresc_ID, String trxName)
    {
      super (ctx, EXME_EgresoPresc_ID, trxName);
      /** if (EXME_EgresoPresc_ID == 0)
        {
			setCantidad (Env.ZERO);
			setEXME_CtaPac_ID (0);
			setEXME_EgresoPresc_ID (0);
			setEXME_Enfermeria_ID (0);
			setFechaAplica (new Timestamp( System.currentTimeMillis() ));
        } */
    }

    /** Load Constructor */
    public X_EXME_EgresoPresc (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_EgresoPresc[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Canceled By.
		@param CanceledBy 
		Canceled By
	  */
	public void setCanceledBy (int CanceledBy)
	{
		set_Value (COLUMNNAME_CanceledBy, Integer.valueOf(CanceledBy));
	}

	/** Get Canceled By.
		@return Canceled By
	  */
	public int getCanceledBy () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_CanceledBy);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Quantity.
		@param Cantidad 
		Quantity
	  */
	public void setCantidad (BigDecimal Cantidad)
	{
		if (Cantidad == null)
			throw new IllegalArgumentException ("Cantidad is mandatory.");
		set_Value (COLUMNNAME_Cantidad, Cantidad);
	}

	/** Get Quantity.
		@return Quantity
	  */
	public BigDecimal getCantidad () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Cantidad);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Comments.
		@param Comments 
		Comments or additional information
	  */
	public void setComments (String Comments)
	{
		set_Value (COLUMNNAME_Comments, Comments);
	}

	/** Get Comments.
		@return Comments or additional information
	  */
	public String getComments () 
	{
		return (String)get_Value(COLUMNNAME_Comments);
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

	/** Set Date Canceled.
		@param DateCanceled Date Canceled	  */
	public void setDateCanceled (Timestamp DateCanceled)
	{
		set_Value (COLUMNNAME_DateCanceled, DateCanceled);
	}

	/** Get Date Canceled.
		@return Date Canceled	  */
	public Timestamp getDateCanceled () 
	{
		return (Timestamp)get_Value(COLUMNNAME_DateCanceled);
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

	/** Set Discharges.
		@param EXME_EgresoPresc_ID Discharges	  */
	public void setEXME_EgresoPresc_ID (int EXME_EgresoPresc_ID)
	{
		if (EXME_EgresoPresc_ID < 1)
			 throw new IllegalArgumentException ("EXME_EgresoPresc_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_EgresoPresc_ID, Integer.valueOf(EXME_EgresoPresc_ID));
	}

	/** Get Discharges.
		@return Discharges	  */
	public int getEXME_EgresoPresc_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_EgresoPresc_ID);
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

	public I_EXME_Enfermeria getEXME_Enfermeria() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_Enfermeria.Table_Name);
        I_EXME_Enfermeria result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_Enfermeria)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Enfermeria_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Infirmary staff.
		@param EXME_Enfermeria_ID 
		Infirmary staff
	  */
	public void setEXME_Enfermeria_ID (int EXME_Enfermeria_ID)
	{
		if (EXME_Enfermeria_ID < 1)
			 throw new IllegalArgumentException ("EXME_Enfermeria_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_Enfermeria_ID, Integer.valueOf(EXME_Enfermeria_ID));
	}

	/** Get Infirmary staff.
		@return Infirmary staff
	  */
	public int getEXME_Enfermeria_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Enfermeria_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Date of Application.
		@param FechaAplica 
		Date of Application
	  */
	public void setFechaAplica (Timestamp FechaAplica)
	{
		if (FechaAplica == null)
			throw new IllegalArgumentException ("FechaAplica is mandatory.");
		set_Value (COLUMNNAME_FechaAplica, FechaAplica);
	}

	/** Get Date of Application.
		@return Date of Application
	  */
	public Timestamp getFechaAplica () 
	{
		return (Timestamp)get_Value(COLUMNNAME_FechaAplica);
	}

	/** Type AD_Reference_ID=1200622 */
	public static final int TYPE_AD_Reference_ID=1200622;
	/** Urine = UR */
	public static final String TYPE_Urine = "UR";
	/** Tube_Drain = TD */
	public static final String TYPE_Tube_Drain = "TD";
	/** Other = OT */
	public static final String TYPE_Other = "OT";
	/** Set Type.
		@param Type 
		Type of Validation
	  */
	public void setType (String Type)
	{

		if (Type == null || Type.equals("UR") || Type.equals("TD") || Type.equals("OT")); else throw new IllegalArgumentException ("Type Invalid value - " + Type + " - Reference_ID=1200622 - UR - TD - OT");		set_Value (COLUMNNAME_Type, Type);
	}

	/** Get Type.
		@return Type of Validation
	  */
	public String getType () 
	{
		return (String)get_Value(COLUMNNAME_Type);
	}
}