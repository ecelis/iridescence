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

/** Generated Model for EXME_EsqInsulinaDet
 *  @author Generated Class 
 *  @version Version @VERSION@ - $Id$ */
public class X_EXME_EsqInsulinaDet extends PO implements I_EXME_EsqInsulinaDet, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_EsqInsulinaDet (Properties ctx, int EXME_EsqInsulinaDet_ID, String trxName)
    {
      super (ctx, EXME_EsqInsulinaDet_ID, trxName);
      /** if (EXME_EsqInsulinaDet_ID == 0)
        {
			setCantidad (Env.ZERO);
			setEXME_CtaPac_ID (0);
			setEXME_EsqInsulinaDet_ID (0);
			setFechaAplica (new Timestamp( System.currentTimeMillis() ));
			setM_Product_ID (0);
			setTipo (null);
        } */
    }

    /** Load Constructor */
    public X_EXME_EsqInsulinaDet (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_EsqInsulinaDet[")
        .append(get_ID()).append("]");
      return sb.toString();
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
			set_ValueNoCheck (COLUMNNAME_EXME_EncounterForms_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_EXME_EncounterForms_ID, Integer.valueOf(EXME_EncounterForms_ID));
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
			set_Value (COLUMNNAME_EXME_Enfermeria_ID, null);
		else 
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

	/** Set Insulin Scheme Detail.
		@param EXME_EsqInsulinaDet_ID Insulin Scheme Detail	  */
	public void setEXME_EsqInsulinaDet_ID (int EXME_EsqInsulinaDet_ID)
	{
		if (EXME_EsqInsulinaDet_ID < 1)
			 throw new IllegalArgumentException ("EXME_EsqInsulinaDet_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_EsqInsulinaDet_ID, Integer.valueOf(EXME_EsqInsulinaDet_ID));
	}

	/** Get Insulin Scheme Detail.
		@return Insulin Scheme Detail	  */
	public int getEXME_EsqInsulinaDet_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_EsqInsulinaDet_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_PlanMedLine getEXME_PlanMedLine() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_PlanMedLine.Table_Name);
        I_EXME_PlanMedLine result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_PlanMedLine)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_PlanMedLine_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Scheduled Doctor.
		@param EXME_PlanMedLine_ID 
		Scheduled Doctor
	  */
	public void setEXME_PlanMedLine_ID (int EXME_PlanMedLine_ID)
	{
		if (EXME_PlanMedLine_ID < 1) 
			set_Value (COLUMNNAME_EXME_PlanMedLine_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_PlanMedLine_ID, Integer.valueOf(EXME_PlanMedLine_ID));
	}

	/** Get Scheduled Doctor.
		@return Scheduled Doctor
	  */
	public int getEXME_PlanMedLine_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_PlanMedLine_ID);
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
			 throw new IllegalArgumentException ("M_Product_ID is mandatory.");
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

	/** Tipo AD_Reference_ID=1200185 */
	public static final int TIPO_AD_Reference_ID=1200185;
	/** Fast Action = AR */
	public static final String TIPO_FastAction = "AR";
	/** Short Action = AC */
	public static final String TIPO_ShortAction = "AC";
	/** Intermediate-acting NPH = AI */
	public static final String TIPO_Intermediate_ActingNPH = "AI";
	/** Prolonged action = AP */
	public static final String TIPO_ProlongedAction = "AP";
	/** Set Type.
		@param Tipo Type	  */
	public void setTipo (String Tipo)
	{
		if (Tipo == null) throw new IllegalArgumentException ("Tipo is mandatory");
		if (Tipo.equals("AR") || Tipo.equals("AC") || Tipo.equals("AI") || Tipo.equals("AP")); else throw new IllegalArgumentException ("Tipo Invalid value - " + Tipo + " - Reference_ID=1200185 - AR - AC - AI - AP");		set_Value (COLUMNNAME_Tipo, Tipo);
	}

	/** Get Type.
		@return Type	  */
	public String getTipo () 
	{
		return (String)get_Value(COLUMNNAME_Tipo);
	}
}