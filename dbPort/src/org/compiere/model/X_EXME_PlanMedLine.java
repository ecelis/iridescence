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

/** Generated Model for EXME_PlanMedLine
 *  @author Generated Class 
 *  @version Version @VERSION@ - $Id$ */
public class X_EXME_PlanMedLine extends PO implements I_EXME_PlanMedLine, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_PlanMedLine (Properties ctx, int EXME_PlanMedLine_ID, String trxName)
    {
      super (ctx, EXME_PlanMedLine_ID, trxName);
      /** if (EXME_PlanMedLine_ID == 0)
        {
			setEXME_PlanMed_ID (0);
			setEXME_PlanMedLine_ID (0);
			setIsIntravenosa (false);
			setProgDate (new Timestamp( System.currentTimeMillis() ));
			setQtyAplied (Env.ZERO);
			setQtyPlanned (Env.ZERO);
        } */
    }

    /** Load Constructor */
    public X_EXME_PlanMedLine (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_PlanMedLine[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set User/Contact .
		@param AD_User_ID 
		User within the system - Internal or Business Partner Contact
	  */
	public void setAD_User_ID (int AD_User_ID)
	{
		if (AD_User_ID < 1) 
			set_Value (COLUMNNAME_AD_User_ID, null);
		else 
			set_Value (COLUMNNAME_AD_User_ID, Integer.valueOf(AD_User_ID));
	}

	/** Get User/Contact .
		@return User within the system - Internal or Business Partner Contact
	  */
	public int getAD_User_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_User_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Applied Date.
		@param ApliedDate Applied Date	  */
	public void setApliedDate (Timestamp ApliedDate)
	{
		set_Value (COLUMNNAME_ApliedDate, ApliedDate);
	}

	/** Get Applied Date.
		@return Applied Date	  */
	public Timestamp getApliedDate () 
	{
		return (Timestamp)get_Value(COLUMNNAME_ApliedDate);
	}

	/** Set Delivery Date.
		@param DeliveryDate Delivery Date	  */
	public void setDeliveryDate (Timestamp DeliveryDate)
	{
		set_Value (COLUMNNAME_DeliveryDate, DeliveryDate);
	}

	/** Get Delivery Date.
		@return Delivery Date	  */
	public Timestamp getDeliveryDate () 
	{
		return (Timestamp)get_Value(COLUMNNAME_DeliveryDate);
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

	/** Estatus AD_Reference_ID=1200505 */
	public static final int ESTATUS_AD_Reference_ID=1200505;
	/** Prescribed = PR */
	public static final String ESTATUS_Prescribed = "PR";
	/** Administered = AD */
	public static final String ESTATUS_Administered = "AD";
	/** Auto Cancel = AC */
	public static final String ESTATUS_AutoCancel = "AC";
	/** Held = HL */
	public static final String ESTATUS_Held = "HL";
	/** Missed = MI */
	public static final String ESTATUS_Missed = "MI";
	/** Set Status.
		@param Estatus 
		Status
	  */
	public void setEstatus (String Estatus)
	{

		if (Estatus == null || Estatus.equals("PR") || Estatus.equals("AD") || Estatus.equals("AC") || Estatus.equals("HL") || Estatus.equals("MI")); else throw new IllegalArgumentException ("Estatus Invalid value - " + Estatus + " - Reference_ID=1200505 - PR - AD - AC - HL - MI");		set_Value (COLUMNNAME_Estatus, Estatus);
	}

	/** Get Status.
		@return Status
	  */
	public String getEstatus () 
	{
		return (String)get_Value(COLUMNNAME_Estatus);
	}

	public I_EXME_ActPacienteIndH getEXME_ActPacienteIndH() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_ActPacienteIndH.Table_Name);
        I_EXME_ActPacienteIndH result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_ActPacienteIndH)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_ActPacienteIndH_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Patient's Indication.
		@param EXME_ActPacienteIndH_ID 
		Patient's Indication
	  */
	public void setEXME_ActPacienteIndH_ID (int EXME_ActPacienteIndH_ID)
	{
		if (EXME_ActPacienteIndH_ID < 1) 
			set_Value (COLUMNNAME_EXME_ActPacienteIndH_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_ActPacienteIndH_ID, Integer.valueOf(EXME_ActPacienteIndH_ID));
	}

	/** Get Patient's Indication.
		@return Patient's Indication
	  */
	public int getEXME_ActPacienteIndH_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_ActPacienteIndH_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_Hist_Vacuna getEXME_Hist_Vacuna() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_Hist_Vacuna.Table_Name);
        I_EXME_Hist_Vacuna result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_Hist_Vacuna)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Hist_Vacuna_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Vaccination History.
		@param EXME_Hist_Vacuna_ID 
		Vaccination History
	  */
	public void setEXME_Hist_Vacuna_ID (int EXME_Hist_Vacuna_ID)
	{
		if (EXME_Hist_Vacuna_ID < 1) 
			set_Value (COLUMNNAME_EXME_Hist_Vacuna_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_Hist_Vacuna_ID, Integer.valueOf(EXME_Hist_Vacuna_ID));
	}

	/** Get Vaccination History.
		@return Vaccination History
	  */
	public int getEXME_Hist_Vacuna_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Hist_Vacuna_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_PlanMed getEXME_PlanMed() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_PlanMed.Table_Name);
        I_EXME_PlanMed result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_PlanMed)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_PlanMed_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Medical Plan.
		@param EXME_PlanMed_ID Medical Plan	  */
	public void setEXME_PlanMed_ID (int EXME_PlanMed_ID)
	{
		if (EXME_PlanMed_ID < 1)
			 throw new IllegalArgumentException ("EXME_PlanMed_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_PlanMed_ID, Integer.valueOf(EXME_PlanMed_ID));
	}

	/** Get Medical Plan.
		@return Medical Plan	  */
	public int getEXME_PlanMed_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_PlanMed_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Scheduled Doctor.
		@param EXME_PlanMedLine_ID 
		Scheduled Doctor
	  */
	public void setEXME_PlanMedLine_ID (int EXME_PlanMedLine_ID)
	{
		if (EXME_PlanMedLine_ID < 1)
			 throw new IllegalArgumentException ("EXME_PlanMedLine_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_PlanMedLine_ID, Integer.valueOf(EXME_PlanMedLine_ID));
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

	/** Set Is intravenous.
		@param IsIntravenosa Is intravenous	  */
	public void setIsIntravenosa (boolean IsIntravenosa)
	{
		set_Value (COLUMNNAME_IsIntravenosa, Boolean.valueOf(IsIntravenosa));
	}

	/** Get Is intravenous.
		@return Is intravenous	  */
	public boolean isIntravenosa () 
	{
		Object oo = get_Value(COLUMNNAME_IsIntravenosa);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Lot No.
		@param Lot 
		Lot number (alphanumeric)
	  */
	public void setLot (String Lot)
	{
		set_Value (COLUMNNAME_Lot, Lot);
	}

	/** Get Lot No.
		@return Lot number (alphanumeric)
	  */
	public String getLot () 
	{
		return (String)get_Value(COLUMNNAME_Lot);
	}

	/** Set Reason.
		@param Motivos Reason	  */
	public void setMotivos (String Motivos)
	{
		set_Value (COLUMNNAME_Motivos, Motivos);
	}

	/** Get Reason.
		@return Reason	  */
	public String getMotivos () 
	{
		return (String)get_Value(COLUMNNAME_Motivos);
	}

	/** Set Programming Day.
		@param ProgDate Programming Day	  */
	public void setProgDate (Timestamp ProgDate)
	{
		if (ProgDate == null)
			throw new IllegalArgumentException ("ProgDate is mandatory.");
		set_Value (COLUMNNAME_ProgDate, ProgDate);
	}

	/** Get Programming Day.
		@return Programming Day	  */
	public Timestamp getProgDate () 
	{
		return (Timestamp)get_Value(COLUMNNAME_ProgDate);
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

	/** Set Planned Quantity.
		@param QtyPlanned Planned Quantity	  */
	public void setQtyPlanned (BigDecimal QtyPlanned)
	{
		if (QtyPlanned == null)
			throw new IllegalArgumentException ("QtyPlanned is mandatory.");
		set_Value (COLUMNNAME_QtyPlanned, QtyPlanned);
	}

	/** Get Planned Quantity.
		@return Planned Quantity	  */
	public BigDecimal getQtyPlanned () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_QtyPlanned);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Plan Medication Line Reference.
		@param Ref_PlanMedLine_ID Plan Medication Line Reference	  */
	public void setRef_PlanMedLine_ID (int Ref_PlanMedLine_ID)
	{
		if (Ref_PlanMedLine_ID < 1) 
			set_Value (COLUMNNAME_Ref_PlanMedLine_ID, null);
		else 
			set_Value (COLUMNNAME_Ref_PlanMedLine_ID, Integer.valueOf(Ref_PlanMedLine_ID));
	}

	/** Get Plan Medication Line Reference.
		@return Plan Medication Line Reference	  */
	public int getRef_PlanMedLine_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Ref_PlanMedLine_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Result.
		@param Result 
		Result of the action taken
	  */
	public void setResult (BigDecimal Result)
	{
		set_Value (COLUMNNAME_Result, Result);
	}

	/** Get Result.
		@return Result of the action taken
	  */
	public BigDecimal getResult () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Result);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Unity.
		@param Unidad Unity	  */
	public void setUnidad (BigDecimal Unidad)
	{
		set_Value (COLUMNNAME_Unidad, Unidad);
	}

	/** Get Unity.
		@return Unity	  */
	public BigDecimal getUnidad () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Unidad);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}
}