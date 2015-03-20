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

/** Generated Model for EXME_Mejoras
 *  @author Generated Class 
 *  @version Version @VERSION@ - $Id$ */
public class X_EXME_Mejoras extends PO implements I_EXME_Mejoras, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_Mejoras (Properties ctx, int EXME_Mejoras_ID, String trxName)
    {
      super (ctx, EXME_Mejoras_ID, trxName);
      /** if (EXME_Mejoras_ID == 0)
        {
			setAgregarPreguntas (false);
			setCalcImpFact (false);
			setCleanBed (false);
// N
			setCURPMandatory (false);
			setDateFrom (new Timestamp( System.currentTimeMillis() ));
			setDateTo (new Timestamp( System.currentTimeMillis() ));
			setEditPastSchedules (false);
			setEXME_Mejoras_ID (0);
			setExpAuto (false);
			setGeneraExp (false);
			setImpBrazalete (false);
			setisDiagEnCuest (false);
// N
			setIsEditarClienteFD (false);
			setIsEditarClienteFE (false);
			setIsProductRequestByGrouping (false);
			setLotWarnings (false);
			setMaxClaimsBatch (0);
			setMaxQueryRecords (0);
			setMaxStmtBatch (0);
			setProgramarCita (false);
			setTolerancia (0);
// 1
			setVistaCitas (false);
			setVoucherRequired (false);
// N
        } */
    }

    /** Load Constructor */
    public X_EXME_Mejoras (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_Mejoras[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public I_A_Asset_Group getA_Asset_Group() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_A_Asset_Group.Table_Name);
        I_A_Asset_Group result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_A_Asset_Group)constructor.newInstance(new Object[] {getCtx(), new Integer(getA_Asset_Group_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Asset Group.
		@param A_Asset_Group_ID 
		Group of Assets
	  */
	public void setA_Asset_Group_ID (int A_Asset_Group_ID)
	{
		if (A_Asset_Group_ID < 1) 
			set_Value (COLUMNNAME_A_Asset_Group_ID, null);
		else 
			set_Value (COLUMNNAME_A_Asset_Group_ID, Integer.valueOf(A_Asset_Group_ID));
	}

	/** Get Asset Group.
		@return Group of Assets
	  */
	public int getA_Asset_Group_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_A_Asset_Group_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Add Questions.
		@param AgregarPreguntas Add Questions	  */
	public void setAgregarPreguntas (boolean AgregarPreguntas)
	{
		set_Value (COLUMNNAME_AgregarPreguntas, Boolean.valueOf(AgregarPreguntas));
	}

	/** Get Add Questions.
		@return Add Questions	  */
	public boolean isAgregarPreguntas () 
	{
		Object oo = get_Value(COLUMNNAME_AgregarPreguntas);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Recalculate Taxes when Billing.
		@param CalcImpFact Recalculate Taxes when Billing	  */
	public void setCalcImpFact (boolean CalcImpFact)
	{
		set_Value (COLUMNNAME_CalcImpFact, Boolean.valueOf(CalcImpFact));
	}

	/** Get Recalculate Taxes when Billing.
		@return Recalculate Taxes when Billing	  */
	public boolean isCalcImpFact () 
	{
		Object oo = get_Value(COLUMNNAME_CalcImpFact);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	public I_C_Charge getC_Charge() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_C_Charge.Table_Name);
        I_C_Charge result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_C_Charge)constructor.newInstance(new Object[] {getCtx(), new Integer(getC_Charge_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Charge.
		@param C_Charge_ID 
		Additional document charges
	  */
	public void setC_Charge_ID (int C_Charge_ID)
	{
		if (C_Charge_ID < 1) 
			set_Value (COLUMNNAME_C_Charge_ID, null);
		else 
			set_Value (COLUMNNAME_C_Charge_ID, Integer.valueOf(C_Charge_ID));
	}

	/** Get Charge.
		@return Additional document charges
	  */
	public int getC_Charge_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Charge_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Charge on Stock.
		@param ChargeOnStock 
		Charge on Stock
	  */
	public void setChargeOnStock (boolean ChargeOnStock)
	{
		set_Value (COLUMNNAME_ChargeOnStock, Boolean.valueOf(ChargeOnStock));
	}

	/** Get Charge on Stock.
		@return Charge on Stock
	  */
	public boolean isChargeOnStock () 
	{
		Object oo = get_Value(COLUMNNAME_ChargeOnStock);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Clean bed after release.
		@param CleanBed 
		Clean bed after release
	  */
	public void setCleanBed (boolean CleanBed)
	{
		set_Value (COLUMNNAME_CleanBed, Boolean.valueOf(CleanBed));
	}

	/** Get Clean bed after release.
		@return Clean bed after release
	  */
	public boolean isCleanBed () 
	{
		Object oo = get_Value(COLUMNNAME_CleanBed);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Inventory Management.
		@param ControlExistencias 
		The warehouse uses inventory management
	  */
	public void setControlExistencias (boolean ControlExistencias)
	{
		set_Value (COLUMNNAME_ControlExistencias, Boolean.valueOf(ControlExistencias));
	}

	/** Get Inventory Management.
		@return The warehouse uses inventory management
	  */
	public boolean isControlExistencias () 
	{
		Object oo = get_Value(COLUMNNAME_ControlExistencias);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set CurpHistory.
		@param CurpHist 
		CurpHistory
	  */
	public void setCurpHist (boolean CurpHist)
	{
		set_Value (COLUMNNAME_CurpHist, Boolean.valueOf(CurpHist));
	}

	/** Get CurpHistory.
		@return CurpHistory
	  */
	public boolean isCurpHist () 
	{
		Object oo = get_Value(COLUMNNAME_CurpHist);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set CURPMandatory.
		@param CURPMandatory CURPMandatory	  */
	public void setCURPMandatory (boolean CURPMandatory)
	{
		set_Value (COLUMNNAME_CURPMandatory, Boolean.valueOf(CURPMandatory));
	}

	/** Get CURPMandatory.
		@return CURPMandatory	  */
	public boolean isCURPMandatory () 
	{
		Object oo = get_Value(COLUMNNAME_CURPMandatory);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Date From.
		@param DateFrom 
		Starting date for a range
	  */
	public void setDateFrom (Timestamp DateFrom)
	{
		if (DateFrom == null)
			throw new IllegalArgumentException ("DateFrom is mandatory.");
		set_Value (COLUMNNAME_DateFrom, DateFrom);
	}

	/** Get Date From.
		@return Starting date for a range
	  */
	public Timestamp getDateFrom () 
	{
		return (Timestamp)get_Value(COLUMNNAME_DateFrom);
	}

	/** Set Date To.
		@param DateTo 
		End date of a date range
	  */
	public void setDateTo (Timestamp DateTo)
	{
		if (DateTo == null)
			throw new IllegalArgumentException ("DateTo is mandatory.");
		set_Value (COLUMNNAME_DateTo, DateTo);
	}

	/** Get Date To.
		@return End date of a date range
	  */
	public Timestamp getDateTo () 
	{
		return (Timestamp)get_Value(COLUMNNAME_DateTo);
	}

	/** Set Area digit number.
		@param DigitosArea Area digit number	  */
	public void setDigitosArea (int DigitosArea)
	{
		set_Value (COLUMNNAME_DigitosArea, Integer.valueOf(DigitosArea));
	}

	/** Get Area digit number.
		@return Area digit number	  */
	public int getDigitosArea () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DigitosArea);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Telephone digits number.
		@param DigitosTel Telephone digits number	  */
	public void setDigitosTel (int DigitosTel)
	{
		set_Value (COLUMNNAME_DigitosTel, Integer.valueOf(DigitosTel));
	}

	/** Get Telephone digits number.
		@return Telephone digits number	  */
	public int getDigitosTel () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DigitosTel);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** eClaim_Status_Doc AD_Reference_ID=1200660 */
	public static final int ECLAIM_STATUS_DOC_AD_Reference_ID=1200660;
	/** Drafted = DR */
	public static final String ECLAIM_STATUS_DOC_Drafted = "DR";
	/** In Progress = IP */
	public static final String ECLAIM_STATUS_DOC_InProgress = "IP";
	/** Set eClaim_Status_Doc.
		@param eClaim_Status_Doc eClaim_Status_Doc	  */
	public void seteClaim_Status_Doc (String eClaim_Status_Doc)
	{

		if (eClaim_Status_Doc == null || eClaim_Status_Doc.equals("DR") || eClaim_Status_Doc.equals("IP")); else throw new IllegalArgumentException ("eClaim_Status_Doc Invalid value - " + eClaim_Status_Doc + " - Reference_ID=1200660 - DR - IP");		set_Value (COLUMNNAME_eClaim_Status_Doc, eClaim_Status_Doc);
	}

	/** Get eClaim_Status_Doc.
		@return eClaim_Status_Doc	  */
	public String geteClaim_Status_Doc () 
	{
		return (String)get_Value(COLUMNNAME_eClaim_Status_Doc);
	}

	/** Set Edit past operating room's schedules.
		@param EditPastSchedules Edit past operating room's schedules	  */
	public void setEditPastSchedules (boolean EditPastSchedules)
	{
		set_Value (COLUMNNAME_EditPastSchedules, Boolean.valueOf(EditPastSchedules));
	}

	/** Get Edit past operating room's schedules.
		@return Edit past operating room's schedules	  */
	public boolean isEditPastSchedules () 
	{
		Object oo = get_Value(COLUMNNAME_EditPastSchedules);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Edit Operating room time Programming.
		@param EditProgOperatingTime 
		Allows programming restore operating room time, when it was made from a request for surgery.
	  */
	public void setEditProgOperatingTime (boolean EditProgOperatingTime)
	{
		set_Value (COLUMNNAME_EditProgOperatingTime, Boolean.valueOf(EditProgOperatingTime));
	}

	/** Get Edit Operating room time Programming.
		@return Allows programming restore operating room time, when it was made from a request for surgery.
	  */
	public boolean isEditProgOperatingTime () 
	{
		Object oo = get_Value(COLUMNNAME_EditProgOperatingTime);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Age of the encounter  for conciliation.
		@param EncounterAge 
		Time to consider that the encounter should be integrated into a new encounter
	  */
	public void setEncounterAge (BigDecimal EncounterAge)
	{
		set_Value (COLUMNNAME_EncounterAge, EncounterAge);
	}

	/** Get Age of the encounter  for conciliation.
		@return Time to consider that the encounter should be integrated into a new encounter
	  */
	public BigDecimal getEncounterAge () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_EncounterAge);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set eScript Sending Enabled.
		@param EScriptSendingEnabled eScript Sending Enabled	  */
	public void setEScriptSendingEnabled (boolean EScriptSendingEnabled)
	{
		set_Value (COLUMNNAME_EScriptSendingEnabled, Boolean.valueOf(EScriptSendingEnabled));
	}

	/** Get eScript Sending Enabled.
		@return eScript Sending Enabled	  */
	public boolean isEScriptSendingEnabled () 
	{
		Object oo = get_Value(COLUMNNAME_EScriptSendingEnabled);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** EsquemaInstalacion AD_Reference_ID=1200451 */
	public static final int ESQUEMAINSTALACION_AD_Reference_ID=1200451;
	/** Rent = rta */
	public static final String ESQUEMAINSTALACION_Rent = "rta";
	/** Sale = vta */
	public static final String ESQUEMAINSTALACION_Sale = "vta";
	/** Set Installation Schema.
		@param EsquemaInstalacion 
		Installation Schema
	  */
	public void setEsquemaInstalacion (String EsquemaInstalacion)
	{

		if (EsquemaInstalacion == null || EsquemaInstalacion.equals("rta") || EsquemaInstalacion.equals("vta")); else throw new IllegalArgumentException ("EsquemaInstalacion Invalid value - " + EsquemaInstalacion + " - Reference_ID=1200451 - rta - vta");		set_Value (COLUMNNAME_EsquemaInstalacion, EsquemaInstalacion);
	}

	/** Get Installation Schema.
		@return Installation Schema
	  */
	public String getEsquemaInstalacion () 
	{
		return (String)get_Value(COLUMNNAME_EsquemaInstalacion);
	}

	/** Set Area of Amphitheater.
		@param EXME_Area_Ref_ID 
		Area of Amphitheater
	  */
	public void setEXME_Area_Ref_ID (int EXME_Area_Ref_ID)
	{
		if (EXME_Area_Ref_ID < 1) 
			set_Value (COLUMNNAME_EXME_Area_Ref_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_Area_Ref_ID, Integer.valueOf(EXME_Area_Ref_ID));
	}

	/** Get Area of Amphitheater.
		@return Area of Amphitheater
	  */
	public int getEXME_Area_Ref_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Area_Ref_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set ICF Version.
		@param EXME_CIFHdr_ID ICF Version	  */
	public void setEXME_CIFHdr_ID (int EXME_CIFHdr_ID)
	{
		if (EXME_CIFHdr_ID < 1) 
			set_Value (COLUMNNAME_EXME_CIFHdr_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_CIFHdr_ID, Integer.valueOf(EXME_CIFHdr_ID));
	}

	/** Get ICF Version.
		@return ICF Version	  */
	public int getEXME_CIFHdr_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_CIFHdr_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Questionnaire.
		@param EXME_Cuestionario_ID 
		Questionnaire
	  */
	public void setEXME_Cuestionario_ID (int EXME_Cuestionario_ID)
	{
		if (EXME_Cuestionario_ID < 1) 
			set_Value (COLUMNNAME_EXME_Cuestionario_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_Cuestionario_ID, Integer.valueOf(EXME_Cuestionario_ID));
	}

	/** Get Questionnaire.
		@return Questionnaire
	  */
	public int getEXME_Cuestionario_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Cuestionario_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Social Work Specialty.
		@param EXME_EspTrabajoSocial_ID Social Work Specialty	  */
	public void setEXME_EspTrabajoSocial_ID (int EXME_EspTrabajoSocial_ID)
	{
		if (EXME_EspTrabajoSocial_ID < 1) 
			set_Value (COLUMNNAME_EXME_EspTrabajoSocial_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_EspTrabajoSocial_ID, Integer.valueOf(EXME_EspTrabajoSocial_ID));
	}

	/** Get Social Work Specialty.
		@return Social Work Specialty	  */
	public int getEXME_EspTrabajoSocial_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_EspTrabajoSocial_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_EstServ getEXME_EstServ() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_EstServ.Table_Name);
        I_EXME_EstServ result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_EstServ)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_EstServ_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Service Unit.
		@param EXME_EstServ_ID 
		Service Unit
	  */
	public void setEXME_EstServ_ID (int EXME_EstServ_ID)
	{
		if (EXME_EstServ_ID < 1) 
			set_Value (COLUMNNAME_EXME_EstServ_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_EstServ_ID, Integer.valueOf(EXME_EstServ_ID));
	}

	/** Get Service Unit.
		@return Service Unit
	  */
	public int getEXME_EstServ_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_EstServ_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Reference Service Station.
		@param EXME_EstServRef_ID 
		It establishes a relationship to the service station.
	  */
	public void setEXME_EstServRef_ID (int EXME_EstServRef_ID)
	{
		if (EXME_EstServRef_ID < 1) 
			set_Value (COLUMNNAME_EXME_EstServRef_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_EstServRef_ID, Integer.valueOf(EXME_EstServRef_ID));
	}

	/** Get Reference Service Station.
		@return It establishes a relationship to the service station.
	  */
	public int getEXME_EstServRef_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_EstServRef_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Service Station of Morgue.
		@param EXME_EstServ_Ref_Morgue_ID 
		Service Station of Morgue
	  */
	public void setEXME_EstServ_Ref_Morgue_ID (int EXME_EstServ_Ref_Morgue_ID)
	{
		if (EXME_EstServ_Ref_Morgue_ID < 1) 
			set_Value (COLUMNNAME_EXME_EstServ_Ref_Morgue_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_EstServ_Ref_Morgue_ID, Integer.valueOf(EXME_EstServ_Ref_Morgue_ID));
	}

	/** Get Service Station of Morgue.
		@return Service Station of Morgue
	  */
	public int getEXME_EstServ_Ref_Morgue_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_EstServ_Ref_Morgue_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Station in charge of Acquisitions.
		@param EXME_EstServTrx_ID 
		It defines the station that this in chargue of the departament of Acquisitions
	  */
	public void setEXME_EstServTrx_ID (int EXME_EstServTrx_ID)
	{
		if (EXME_EstServTrx_ID < 1) 
			set_Value (COLUMNNAME_EXME_EstServTrx_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_EstServTrx_ID, Integer.valueOf(EXME_EstServTrx_ID));
	}

	/** Get Station in charge of Acquisitions.
		@return It defines the station that this in chargue of the departament of Acquisitions
	  */
	public int getEXME_EstServTrx_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_EstServTrx_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_IntervencionHdr getEXME_IntervencionHdr() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_IntervencionHdr.Table_Name);
        I_EXME_IntervencionHdr result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_IntervencionHdr)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_IntervencionHdr_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set ICD 9 Vol III.
		@param EXME_IntervencionHdr_ID ICD 9 Vol III	  */
	public void setEXME_IntervencionHdr_ID (int EXME_IntervencionHdr_ID)
	{
		if (EXME_IntervencionHdr_ID < 1) 
			set_Value (COLUMNNAME_EXME_IntervencionHdr_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_IntervencionHdr_ID, Integer.valueOf(EXME_IntervencionHdr_ID));
	}

	/** Get ICD 9 Vol III.
		@return ICD 9 Vol III	  */
	public int getEXME_IntervencionHdr_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_IntervencionHdr_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Improvements.
		@param EXME_Mejoras_ID Improvements	  */
	public void setEXME_Mejoras_ID (int EXME_Mejoras_ID)
	{
		if (EXME_Mejoras_ID < 1)
			 throw new IllegalArgumentException ("EXME_Mejoras_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_Mejoras_ID, Integer.valueOf(EXME_Mejoras_ID));
	}

	/** Get Improvements.
		@return Improvements	  */
	public int getEXME_Mejoras_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Mejoras_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set specialty military promotion .
		@param EXME_PromoEspec_ID specialty military promotion 	  */
	public void setEXME_PromoEspec_ID (int EXME_PromoEspec_ID)
	{
		if (EXME_PromoEspec_ID < 1) 
			set_Value (COLUMNNAME_EXME_PromoEspec_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_PromoEspec_ID, Integer.valueOf(EXME_PromoEspec_ID));
	}

	/** Get specialty military promotion .
		@return specialty military promotion 	  */
	public int getEXME_PromoEspec_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_PromoEspec_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Atomatic File Number.
		@param ExpAuto 
		Automatically Generate File Number 
	  */
	public void setExpAuto (boolean ExpAuto)
	{
		set_Value (COLUMNNAME_ExpAuto, Boolean.valueOf(ExpAuto));
	}

	/** Get Atomatic File Number.
		@return Automatically Generate File Number 
	  */
	public boolean isExpAuto () 
	{
		Object oo = get_Value(COLUMNNAME_ExpAuto);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set GeneraExp.
		@param GeneraExp GeneraExp	  */
	public void setGeneraExp (boolean GeneraExp)
	{
		set_Value (COLUMNNAME_GeneraExp, Boolean.valueOf(GeneraExp));
	}

	/** Get GeneraExp.
		@return GeneraExp	  */
	public boolean isGeneraExp () 
	{
		Object oo = get_Value(COLUMNNAME_GeneraExp);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Host Open Office Service Manager.
		@param HostServManagOOff Host Open Office Service Manager	  */
	public void setHostServManagOOff (String HostServManagOOff)
	{
		set_Value (COLUMNNAME_HostServManagOOff, HostServManagOOff);
	}

	/** Get Host Open Office Service Manager.
		@return Host Open Office Service Manager	  */
	public String getHostServManagOOff () 
	{
		return (String)get_Value(COLUMNNAME_HostServManagOOff);
	}

	/** Set Print Bracelet.
		@param ImpBrazalete 
		Print Bracelet
	  */
	public void setImpBrazalete (boolean ImpBrazalete)
	{
		set_Value (COLUMNNAME_ImpBrazalete, Boolean.valueOf(ImpBrazalete));
	}

	/** Get Print Bracelet.
		@return Print Bracelet
	  */
	public boolean isImpBrazalete () 
	{
		Object oo = get_Value(COLUMNNAME_ImpBrazalete);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Add to Lot.
		@param IsAgregaLote Add to Lot	  */
	public void setIsAgregaLote (boolean IsAgregaLote)
	{
		set_Value (COLUMNNAME_IsAgregaLote, Boolean.valueOf(IsAgregaLote));
	}

	/** Get Add to Lot.
		@return Add to Lot	  */
	public boolean isAgregaLote () 
	{
		Object oo = get_Value(COLUMNNAME_IsAgregaLote);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Automatically return charges.
		@param IsAutoReturnCharges Automatically return charges	  */
	public void setIsAutoReturnCharges (boolean IsAutoReturnCharges)
	{
		set_Value (COLUMNNAME_IsAutoReturnCharges, Boolean.valueOf(IsAutoReturnCharges));
	}

	/** Get Automatically return charges.
		@return Automatically return charges	  */
	public boolean isAutoReturnCharges () 
	{
		Object oo = get_Value(COLUMNNAME_IsAutoReturnCharges);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Diagnosis in Questionnaire.
		@param isDiagEnCuest Diagnosis in Questionnaire	  */
	public void setisDiagEnCuest (boolean isDiagEnCuest)
	{
		set_Value (COLUMNNAME_isDiagEnCuest, Boolean.valueOf(isDiagEnCuest));
	}

	/** Get Diagnosis in Questionnaire.
		@return Diagnosis in Questionnaire	  */
	public boolean isDiagEnCuest () 
	{
		Object oo = get_Value(COLUMNNAME_isDiagEnCuest);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Edit Client Info In Direct Invoicing.
		@param IsEditarClienteFD 
		Edit Client Info In Direct Invoicing
	  */
	public void setIsEditarClienteFD (boolean IsEditarClienteFD)
	{
		set_Value (COLUMNNAME_IsEditarClienteFD, Boolean.valueOf(IsEditarClienteFD));
	}

	/** Get Edit Client Info In Direct Invoicing.
		@return Edit Client Info In Direct Invoicing
	  */
	public boolean isEditarClienteFD () 
	{
		Object oo = get_Value(COLUMNNAME_IsEditarClienteFD);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Edit Client Info in Extension Invoicing.
		@param IsEditarClienteFE 
		Edit Client Info in Extension Invoicing
	  */
	public void setIsEditarClienteFE (boolean IsEditarClienteFE)
	{
		set_Value (COLUMNNAME_IsEditarClienteFE, Boolean.valueOf(IsEditarClienteFE));
	}

	/** Get Edit Client Info in Extension Invoicing.
		@return Edit Client Info in Extension Invoicing
	  */
	public boolean isEditarClienteFE () 
	{
		Object oo = get_Value(COLUMNNAME_IsEditarClienteFE);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Clinical Decision Support.
		@param IsMDS 
		Use the Medical Decision Support validation
	  */
	public void setIsMDS (boolean IsMDS)
	{
		set_Value (COLUMNNAME_IsMDS, Boolean.valueOf(IsMDS));
	}

	/** Get Clinical Decision Support.
		@return Use the Medical Decision Support validation
	  */
	public boolean isMDS () 
	{
		Object oo = get_Value(COLUMNNAME_IsMDS);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Modify Lot.
		@param IsModificaLotes Modify Lot	  */
	public void setIsModificaLotes (boolean IsModificaLotes)
	{
		set_Value (COLUMNNAME_IsModificaLotes, Boolean.valueOf(IsModificaLotes));
	}

	/** Get Modify Lot.
		@return Modify Lot	  */
	public boolean isModificaLotes () 
	{
		Object oo = get_Value(COLUMNNAME_IsModificaLotes);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Is Multi UOM.
		@param IsMultiUOM 
		Has multiple UOM for the product
	  */
	public void setIsMultiUOM (boolean IsMultiUOM)
	{
		set_Value (COLUMNNAME_IsMultiUOM, Boolean.valueOf(IsMultiUOM));
	}

	/** Get Is Multi UOM.
		@return Has multiple UOM for the product
	  */
	public boolean isMultiUOM () 
	{
		Object oo = get_Value(COLUMNNAME_IsMultiUOM);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Is name for Last Name.
		@param IsNombrePorApellido Is name for Last Name	  */
	public void setIsNombrePorApellido (boolean IsNombrePorApellido)
	{
		set_Value (COLUMNNAME_IsNombrePorApellido, Boolean.valueOf(IsNombrePorApellido));
	}

	/** Get Is name for Last Name.
		@return Is name for Last Name	  */
	public boolean isNombrePorApellido () 
	{
		Object oo = get_Value(COLUMNNAME_IsNombrePorApellido);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Hide Discounts Application with password.
		@param isOcultarPass Hide Discounts Application with password	  */
	public void setisOcultarPass (boolean isOcultarPass)
	{
		set_Value (COLUMNNAME_isOcultarPass, Boolean.valueOf(isOcultarPass));
	}

	/** Get Hide Discounts Application with password.
		@return Hide Discounts Application with password	  */
	public boolean isOcultarPass () 
	{
		Object oo = get_Value(COLUMNNAME_isOcultarPass);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Activate product request by grouping.
		@param IsProductRequestByGrouping 
		used field to form doing multiple requests grouped by type product
	  */
	public void setIsProductRequestByGrouping (boolean IsProductRequestByGrouping)
	{
		set_Value (COLUMNNAME_IsProductRequestByGrouping, Boolean.valueOf(IsProductRequestByGrouping));
	}

	/** Get Activate product request by grouping.
		@return used field to form doing multiple requests grouped by type product
	  */
	public boolean isProductRequestByGrouping () 
	{
		Object oo = get_Value(COLUMNNAME_IsProductRequestByGrouping);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Warning messages in lot.
		@param LotWarnings Warning messages in lot	  */
	public void setLotWarnings (boolean LotWarnings)
	{
		set_Value (COLUMNNAME_LotWarnings, Boolean.valueOf(LotWarnings));
	}

	/** Get Warning messages in lot.
		@return Warning messages in lot	  */
	public boolean isLotWarnings () 
	{
		Object oo = get_Value(COLUMNNAME_LotWarnings);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set MaxClaimsBatch.
		@param MaxClaimsBatch MaxClaimsBatch	  */
	public void setMaxClaimsBatch (int MaxClaimsBatch)
	{
		set_Value (COLUMNNAME_MaxClaimsBatch, Integer.valueOf(MaxClaimsBatch));
	}

	/** Get MaxClaimsBatch.
		@return MaxClaimsBatch	  */
	public int getMaxClaimsBatch () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_MaxClaimsBatch);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Max Query Records.
		@param MaxQueryRecords 
		If defined, you cannot query more records as defined - the query criteria needs to be changed to query less records
	  */
	public void setMaxQueryRecords (int MaxQueryRecords)
	{
		set_Value (COLUMNNAME_MaxQueryRecords, Integer.valueOf(MaxQueryRecords));
	}

	/** Get Max Query Records.
		@return If defined, you cannot query more records as defined - the query criteria needs to be changed to query less records
	  */
	public int getMaxQueryRecords () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_MaxQueryRecords);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set MaxStmtBatch.
		@param MaxStmtBatch MaxStmtBatch	  */
	public void setMaxStmtBatch (int MaxStmtBatch)
	{
		set_Value (COLUMNNAME_MaxStmtBatch, Integer.valueOf(MaxStmtBatch));
	}

	/** Get MaxStmtBatch.
		@return MaxStmtBatch	  */
	public int getMaxStmtBatch () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_MaxStmtBatch);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Max Try WebService Communication.
	@param MaxTryWS Max Try WebService Communication	  */
	public void setMaxTryWS (int MaxTryWS)
	{
		set_Value (COLUMNNAME_MaxTryWS, Integer.valueOf(MaxTryWS));
	}
	
	/** Get Max Try WebService Communication.
		@return Max Try WebService Communication	  */
	public int getMaxTryWS () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_MaxTryWS);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
	
	/** Set Issued Only Once CDS Warning.
		@param MDSRepResp Issued Only Once CDS Warning	  */
	public void setMDSRepResp (boolean MDSRepResp)
	{
		set_Value (COLUMNNAME_MDSRepResp, Boolean.valueOf(MDSRepResp));
	}

	/** Get Issued Only Once CDS Warning.
		@return Issued Only Once CDS Warning	  */
	public boolean isMDSRepResp () 
	{
		Object oo = get_Value(COLUMNNAME_MDSRepResp);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Message for Due Between 121 and 180.
		@param Message_Due121_180 Message for Due Between 121 and 180	  */
	public void setMessage_Due121_180 (String Message_Due121_180)
	{
		set_Value (COLUMNNAME_Message_Due121_180, Message_Due121_180);
	}

	/** Get Message for Due Between 121 and 180.
		@return Message for Due Between 121 and 180	  */
	public String getMessage_Due121_180 () 
	{
		return (String)get_Value(COLUMNNAME_Message_Due121_180);
	}

	/** Set Message for Due Between 1 and 30.
		@param Message_Due1_30 Message for Due Between 1 and 30	  */
	public void setMessage_Due1_30 (String Message_Due1_30)
	{
		set_Value (COLUMNNAME_Message_Due1_30, Message_Due1_30);
	}

	/** Get Message for Due Between 1 and 30.
		@return Message for Due Between 1 and 30	  */
	public String getMessage_Due1_30 () 
	{
		return (String)get_Value(COLUMNNAME_Message_Due1_30);
	}

	/** Set Message for Due Greater than 181.
		@param Message_Due181_Plus Message for Due Greater than 181	  */
	public void setMessage_Due181_Plus (String Message_Due181_Plus)
	{
		set_Value (COLUMNNAME_Message_Due181_Plus, Message_Due181_Plus);
	}

	/** Get Message for Due Greater than 181.
		@return Message for Due Greater than 181	  */
	public String getMessage_Due181_Plus () 
	{
		return (String)get_Value(COLUMNNAME_Message_Due181_Plus);
	}

	/** Set Message for Due Between 31 and 60.
		@param Message_Due31_60 Message for Due Between 31 and 60	  */
	public void setMessage_Due31_60 (String Message_Due31_60)
	{
		set_Value (COLUMNNAME_Message_Due31_60, Message_Due31_60);
	}

	/** Get Message for Due Between 31 and 60.
		@return Message for Due Between 31 and 60	  */
	public String getMessage_Due31_60 () 
	{
		return (String)get_Value(COLUMNNAME_Message_Due31_60);
	}

	/** Set Message for Due Between 61 and 90.
		@param Message_Due61_90 Message for Due Between 61 and 90	  */
	public void setMessage_Due61_90 (String Message_Due61_90)
	{
		set_Value (COLUMNNAME_Message_Due61_90, Message_Due61_90);
	}

	/** Get Message for Due Between 61 and 90.
		@return Message for Due Between 61 and 90	  */
	public String getMessage_Due61_90 () 
	{
		return (String)get_Value(COLUMNNAME_Message_Due61_90);
	}

	/** Set Message for Due Between 91 and 120.
		@param Message_Due91_120 Message for Due Between 91 and 120	  */
	public void setMessage_Due91_120 (String Message_Due91_120)
	{
		set_Value (COLUMNNAME_Message_Due91_120, Message_Due91_120);
	}

	/** Get Message for Due Between 91 and 120.
		@return Message for Due Between 91 and 120	  */
	public String getMessage_Due91_120 () 
	{
		return (String)get_Value(COLUMNNAME_Message_Due91_120);
	}

	/** Set Product quantity must be in stock.
		@param MustBeStocked 
		If not sufficient in stock in the warehouse, the BOM is not produced
	  */
	public void setMustBeStocked (boolean MustBeStocked)
	{
		set_Value (COLUMNNAME_MustBeStocked, Boolean.valueOf(MustBeStocked));
	}

	/** Get Product quantity must be in stock.
		@return If not sufficient in stock in the warehouse, the BOM is not produced
	  */
	public boolean isMustBeStocked () 
	{
		Object oo = get_Value(COLUMNNAME_MustBeStocked);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	public I_M_Warehouse getM_Warehouse() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_M_Warehouse.Table_Name);
        I_M_Warehouse result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_M_Warehouse)constructor.newInstance(new Object[] {getCtx(), new Integer(getM_Warehouse_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Warehouse.
		@param M_Warehouse_ID 
		Storage Warehouse and Service Point
	  */
	public void setM_Warehouse_ID (int M_Warehouse_ID)
	{
		if (M_Warehouse_ID < 1) 
			set_Value (COLUMNNAME_M_Warehouse_ID, null);
		else 
			set_Value (COLUMNNAME_M_Warehouse_ID, Integer.valueOf(M_Warehouse_ID));
	}

	/** Get Warehouse.
		@return Storage Warehouse and Service Point
	  */
	public int getM_Warehouse_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_Warehouse_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Not Available NDC.
		@param NotAvailableNDC Not Available NDC	  */
	public void setNotAvailableNDC (int NotAvailableNDC)
	{
		set_Value (COLUMNNAME_NotAvailableNDC, Integer.valueOf(NotAvailableNDC));
	}

	/** Get Not Available NDC.
		@return Not Available NDC	  */
	public int getNotAvailableNDC () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_NotAvailableNDC);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Nursing Charges in eMAR.
		@param NursingCharges 
		Makes the charge of the drug when is applied by the nurse
	  */
	public void setNursingCharges (boolean NursingCharges)
	{
		set_Value (COLUMNNAME_NursingCharges, Boolean.valueOf(NursingCharges));
	}

	/** Get Nursing Charges in eMAR.
		@return Makes the charge of the drug when is applied by the nurse
	  */
	public boolean isNursingCharges () 
	{
		Object oo = get_Value(COLUMNNAME_NursingCharges);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Pass.
		@param Pentaho_psw Pass	  */
	public void setPentaho_psw (String Pentaho_psw)
	{
		set_Value (COLUMNNAME_Pentaho_psw, Pentaho_psw);
	}

	/** Get Pass.
		@return Pass	  */
	public String getPentaho_psw () 
	{
		return (String)get_Value(COLUMNNAME_Pentaho_psw);
	}

	/** Set Pentaho Server.
		@param Pentaho_server Pentaho Server	  */
	public void setPentaho_server (String Pentaho_server)
	{
		set_Value (COLUMNNAME_Pentaho_server, Pentaho_server);
	}

	/** Get Pentaho Server.
		@return Pentaho Server	  */
	public String getPentaho_server () 
	{
		return (String)get_Value(COLUMNNAME_Pentaho_server);
	}

	/** Set User.
		@param Pentaho_user User	  */
	public void setPentaho_user (String Pentaho_user)
	{
		set_Value (COLUMNNAME_Pentaho_user, Pentaho_user);
	}

	/** Get User.
		@return User	  */
	public String getPentaho_user () 
	{
		return (String)get_Value(COLUMNNAME_Pentaho_user);
	}

	/** Set Service Manager port of Open Office.
		@param PortServManagOOff Service Manager port of Open Office	  */
	public void setPortServManagOOff (int PortServManagOOff)
	{
		set_Value (COLUMNNAME_PortServManagOOff, Integer.valueOf(PortServManagOOff));
	}

	/** Get Service Manager port of Open Office.
		@return Service Manager port of Open Office	  */
	public int getPortServManagOOff () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_PortServManagOOff);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Product Key.
		@param ProductValue 
		Key of the Product
	  */
	public void setProductValue (String ProductValue)
	{
		set_Value (COLUMNNAME_ProductValue, ProductValue);
	}

	/** Get Product Key.
		@return Key of the Product
	  */
	public String getProductValue () 
	{
		return (String)get_Value(COLUMNNAME_ProductValue);
	}

	/** Set Schedule an appointment.
		@param ProgramarCita 
		Allows the doctor to schedule a new appointment from appointment's execution
	  */
	public void setProgramarCita (boolean ProgramarCita)
	{
		set_Value (COLUMNNAME_ProgramarCita, Boolean.valueOf(ProgramarCita));
	}

	/** Get Schedule an appointment.
		@return Allows the doctor to schedule a new appointment from appointment's execution
	  */
	public boolean isProgramarCita () 
	{
		Object oo = get_Value(COLUMNNAME_ProgramarCita);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Records per Block.
		@param RegBloque 
		Number of records included in each searching blocks inside Opening File
	  */
	public void setRegBloque (int RegBloque)
	{
		set_Value (COLUMNNAME_RegBloque, Integer.valueOf(RegBloque));
	}

	/** Get Records per Block.
		@return Number of records included in each searching blocks inside Opening File
	  */
	public int getRegBloque () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_RegBloque);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Require MDS Response.
		@param ReqMDSResp Require MDS Response	  */
	public void setReqMDSResp (boolean ReqMDSResp)
	{
		set_Value (COLUMNNAME_ReqMDSResp, Boolean.valueOf(ReqMDSResp));
	}

	/** Get Require MDS Response.
		@return Require MDS Response	  */
	public boolean isReqMDSResp () 
	{
		Object oo = get_Value(COLUMNNAME_ReqMDSResp);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Request EMail.
		@param RequestEMail 
		EMail address to send automated mails from or receive mails for automated processing (fully qualified)
	  */
	public void setRequestEMail (String RequestEMail)
	{
		set_Value (COLUMNNAME_RequestEMail, RequestEMail);
	}

	/** Get Request EMail.
		@return EMail address to send automated mails from or receive mails for automated processing (fully qualified)
	  */
	public String getRequestEMail () 
	{
		return (String)get_Value(COLUMNNAME_RequestEMail);
	}

	/** Set RequestID.
		@param RequestID RequestID	  */
	public void setRequestID (String RequestID)
	{
		set_Value (COLUMNNAME_RequestID, RequestID);
	}

	/** Get RequestID.
		@return RequestID	  */
	public String getRequestID () 
	{
		return (String)get_Value(COLUMNNAME_RequestID);
	}

	/** Set Request User.
		@param RequestUser 
		User Name (ID) of the email owner
	  */
	public void setRequestUser (String RequestUser)
	{
		set_Value (COLUMNNAME_RequestUser, RequestUser);
	}

	/** Get Request User.
		@return User Name (ID) of the email owner
	  */
	public String getRequestUser () 
	{
		return (String)get_Value(COLUMNNAME_RequestUser);
	}

	/** Set Request User Password.
		@param RequestUserPW 
		Password of the user name (ID) for mail processing
	  */
	public void setRequestUserPW (String RequestUserPW)
	{
		set_Value (COLUMNNAME_RequestUserPW, RequestUserPW);
	}

	/** Get Request User Password.
		@return Password of the user name (ID) for mail processing
	  */
	public String getRequestUserPW () 
	{
		return (String)get_Value(COLUMNNAME_RequestUserPW);
	}

	/** Set Round up the charge quantity.
		@param RoundQtyCharge Round up the charge quantity	  */
	public void setRoundQtyCharge (boolean RoundQtyCharge)
	{
		set_Value (COLUMNNAME_RoundQtyCharge, Boolean.valueOf(RoundQtyCharge));
	}

	/** Get Round up the charge quantity.
		@return Round up the charge quantity	  */
	public boolean isRoundQtyCharge () 
	{
		Object oo = get_Value(COLUMNNAME_RoundQtyCharge);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Open Office Path.
		@param RutaOpenOffice 
		Open Office Path
	  */
	public void setRutaOpenOffice (String RutaOpenOffice)
	{
		set_Value (COLUMNNAME_RutaOpenOffice, RutaOpenOffice);
	}

	/** Get Open Office Path.
		@return Open Office Path
	  */
	public String getRutaOpenOffice () 
	{
		return (String)get_Value(COLUMNNAME_RutaOpenOffice);
	}

	/** Set Rx Fax Sending Enabled.
		@param RxFaxSendingEnabled Rx Fax Sending Enabled	  */
	public void setRxFaxSendingEnabled (boolean RxFaxSendingEnabled)
	{
		set_Value (COLUMNNAME_RxFaxSendingEnabled, Boolean.valueOf(RxFaxSendingEnabled));
	}

	/** Get Rx Fax Sending Enabled.
		@return Rx Fax Sending Enabled	  */
	public boolean isRxFaxSendingEnabled () 
	{
		Object oo = get_Value(COLUMNNAME_RxFaxSendingEnabled);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set StatementAge.
		@param StatementAge StatementAge	  */
	public void setStatementAge (BigDecimal StatementAge)
	{
		set_Value (COLUMNNAME_StatementAge, StatementAge);
	}

	/** Get StatementAge.
		@return StatementAge	  */
	public BigDecimal getStatementAge () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_StatementAge);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Temporary NDC.
		@param TemporaryNDC Temporary NDC	  */
	public void setTemporaryNDC (int TemporaryNDC)
	{
		set_Value (COLUMNNAME_TemporaryNDC, Integer.valueOf(TemporaryNDC));
	}

	/** Get Temporary NDC.
		@return Temporary NDC	  */
	public int getTemporaryNDC () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_TemporaryNDC);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Time Out in Seconds Connecting to WebService.
	@param TimeOutWS Time Out in Seconds Connecting to WebService	  */
	public void setTimeOutWS (int TimeOutWS)
	{
		set_Value (COLUMNNAME_TimeOutWS, Integer.valueOf(TimeOutWS));
	}
	
	/** Get Time Out in Seconds Connecting to WebService.
		@return Time Out in Seconds Connecting to WebService	  */
	public int getTimeOutWS () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_TimeOutWS);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
	
	/** Set Tolerance.
		@param Tolerancia Tolerance	  */
	public void setTolerancia (int Tolerancia)
	{
		set_Value (COLUMNNAME_Tolerancia, Integer.valueOf(Tolerancia));
	}

	/** Get Tolerance.
		@return Tolerance	  */
	public int getTolerancia () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Tolerancia);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Appointment View.
		@param VistaCitas Appointment View	  */
	public void setVistaCitas (boolean VistaCitas)
	{
		set_Value (COLUMNNAME_VistaCitas, Boolean.valueOf(VistaCitas));
	}

	/** Get Appointment View.
		@return Appointment View	  */
	public boolean isVistaCitas () 
	{
		Object oo = get_Value(COLUMNNAME_VistaCitas);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Requires payment voucher.
		@param VoucherRequired 
		Requires payment voucher
	  */
	public void setVoucherRequired (boolean VoucherRequired)
	{
		set_Value (COLUMNNAME_VoucherRequired, Boolean.valueOf(VoucherRequired));
	}

	/** Get Requires payment voucher.
		@return Requires payment voucher
	  */
	public boolean isVoucherRequired () 
	{
		Object oo = get_Value(COLUMNNAME_VoucherRequired);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}
}