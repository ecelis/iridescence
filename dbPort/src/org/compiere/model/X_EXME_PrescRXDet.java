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

/** Generated Model for EXME_PrescRXDet
 *  @author Generated Class 
 *  @version Version @VERSION@ - $Id$ */
public class X_EXME_PrescRXDet extends PO implements I_EXME_PrescRXDet, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_PrescRXDet (Properties ctx, int EXME_PrescRXDet_ID, String trxName)
    {
      super (ctx, EXME_PrescRXDet_ID, trxName);
      /** if (EXME_PrescRXDet_ID == 0)
        {
			setEXME_PrescRXDet_ID (0);
			setEXME_PrescRX_ID (0);
			setIsDelivered (false);
			setReconciliation (false);
			setSurtidoPorEnfermera (false);
			setType (null);
// D
        } */
    }

    /** Load Constructor */
    public X_EXME_PrescRXDet (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_PrescRXDet[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set ApprovalUser.
		@param ApprovalUser ApprovalUser	  */
	public void setApprovalUser (int ApprovalUser)
	{
		throw new IllegalArgumentException ("ApprovalUser is virtual column");	}

	/** Get ApprovalUser.
		@return ApprovalUser	  */
	public int getApprovalUser () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_ApprovalUser);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Authenticated.
		@param Authenticated Authenticated	  */
	public void setAuthenticated (boolean Authenticated)
	{
		set_Value (COLUMNNAME_Authenticated, Boolean.valueOf(Authenticated));
	}

	/** Get Authenticated.
		@return Authenticated	  */
	public boolean isAuthenticated () 
	{
		Object oo = get_Value(COLUMNNAME_Authenticated);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Authenticated By.
		@param AuthenticatedBy Authenticated By	  */
	public void setAuthenticatedBy (int AuthenticatedBy)
	{
		set_Value (COLUMNNAME_AuthenticatedBy, Integer.valueOf(AuthenticatedBy));
	}

	/** Get Authenticated By.
		@return Authenticated By	  */
	public int getAuthenticatedBy () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AuthenticatedBy);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Authentication Date.
		@param Authenticated_Date Authentication Date	  */
	public void setAuthenticated_Date (Timestamp Authenticated_Date)
	{
		set_Value (COLUMNNAME_Authenticated_Date, Authenticated_Date);
	}

	/** Get Authentication Date.
		@return Authentication Date	  */
	public Timestamp getAuthenticated_Date () 
	{
		return (Timestamp)get_Value(COLUMNNAME_Authenticated_Date);
	}

	/** Set Discontinued Date.
		@param DiscontinuedDate Discontinued Date	  */
	public void setDiscontinuedDate (Timestamp DiscontinuedDate)
	{
		set_Value (COLUMNNAME_DiscontinuedDate, DiscontinuedDate);
	}

	/** Get Discontinued Date.
		@return Discontinued Date	  */
	public Timestamp getDiscontinuedDate () 
	{
		return (Timestamp)get_Value(COLUMNNAME_DiscontinuedDate);
	}

	/** Dose AD_Reference_ID=1200501 */
	public static final int DOSE_AD_Reference_ID=1200501;
	/** Auto = 1 */
	public static final String DOSE_Auto = "1";
	/** 2 Doses = 2 */
	public static final String DOSE_2Doses = "2";
	/** 3 Doses = 3 */
	public static final String DOSE_3Doses = "3";
	/** 4 Doses = 4 */
	public static final String DOSE_4Doses = "4";
	/** 5 Doses = 5 */
	public static final String DOSE_5Doses = "5";
	/** 6 Doses = 6 */
	public static final String DOSE_6Doses = "6";
	/** 7 Doses = 7 */
	public static final String DOSE_7Doses = "7";
	/** 8 Doses = 8 */
	public static final String DOSE_8Doses = "8";
	/** Set Medication Doses.
		@param Dose 
		Medication Doses
	  */
	public void setDose (String Dose)
	{

		if (Dose == null || Dose.equals("1") || Dose.equals("2") || Dose.equals("3") || Dose.equals("4") || Dose.equals("5") || Dose.equals("6") || Dose.equals("7") || Dose.equals("8")); else throw new IllegalArgumentException ("Dose Invalid value - " + Dose + " - Reference_ID=1200501 - 1 - 2 - 3 - 4 - 5 - 6 - 7 - 8");		set_Value (COLUMNNAME_Dose, Dose);
	}

	/** Get Medication Doses.
		@return Medication Doses
	  */
	public String getDose () 
	{
		return (String)get_Value(COLUMNNAME_Dose);
	}

	/** Set Dose / Rate.
		@param DoseRate 
		Dose / Rate
	  */
	public void setDoseRate (String DoseRate)
	{
		set_Value (COLUMNNAME_DoseRate, DoseRate);
	}

	/** Get Dose / Rate.
		@return Dose / Rate
	  */
	public String getDoseRate () 
	{
		return (String)get_Value(COLUMNNAME_DoseRate);
	}

	/** ERxStatus AD_Reference_ID=1200591 */
	public static final int ERXSTATUS_AD_Reference_ID=1200591;
	/** Not Sent = 0 */
	public static final String ERXSTATUS_NotSent = "0";
	/** Error = 1 */
	public static final String ERXSTATUS_Error = "1";
	/** Sent = 2 */
	public static final String ERXSTATUS_Sent = "2";
	/** Refill Request = 3 */
	public static final String ERXSTATUS_RefillRequest = "3";
	/** Change Request = 4 */
	public static final String ERXSTATUS_ChangeRequest = "4";
	/** Prescription to Follow = 5 */
	public static final String ERXSTATUS_PrescriptionToFollow = "5";
	/** Free Standing Error = 6 */
	public static final String ERXSTATUS_FreeStandingError = "6";
	/** Verify = 7 */
	public static final String ERXSTATUS_Verify = "7";
	/** Set ePrescribing Status.
		@param ERxStatus ePrescribing Status	  */
	public void setERxStatus (String ERxStatus)
	{

		if (ERxStatus == null || ERxStatus.equals("0") || ERxStatus.equals("1") || ERxStatus.equals("2") || ERxStatus.equals("3") || ERxStatus.equals("4") || ERxStatus.equals("5") || ERxStatus.equals("6") || ERxStatus.equals("7")); else throw new IllegalArgumentException ("ERxStatus Invalid value - " + ERxStatus + " - Reference_ID=1200591 - 0 - 1 - 2 - 3 - 4 - 5 - 6 - 7");		set_Value (COLUMNNAME_ERxStatus, ERxStatus);
	}

	/** Get ePrescribing Status.
		@return ePrescribing Status	  */
	public String getERxStatus () 
	{
		return (String)get_Value(COLUMNNAME_ERxStatus);
	}

	public I_EXME_ActPacienteInd getEXME_ActPacienteInd() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_ActPacienteInd.Table_Name);
        I_EXME_ActPacienteInd result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_ActPacienteInd)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_ActPacienteInd_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Indication's detail.
		@param EXME_ActPacienteInd_ID 
		Indication's detail
	  */
	public void setEXME_ActPacienteInd_ID (int EXME_ActPacienteInd_ID)
	{
		if (EXME_ActPacienteInd_ID < 1) 
			set_Value (COLUMNNAME_EXME_ActPacienteInd_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_ActPacienteInd_ID, Integer.valueOf(EXME_ActPacienteInd_ID));
	}

	/** Get Indication's detail.
		@return Indication's detail
	  */
	public int getEXME_ActPacienteInd_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_ActPacienteInd_ID);
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

	public I_EXME_Farmacia getEXME_Farmacia() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_Farmacia.Table_Name);
        I_EXME_Farmacia result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_Farmacia)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Farmacia_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Pharmacy.
		@param EXME_Farmacia_ID Pharmacy	  */
	public void setEXME_Farmacia_ID (int EXME_Farmacia_ID)
	{
		if (EXME_Farmacia_ID < 1) 
			set_Value (COLUMNNAME_EXME_Farmacia_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_Farmacia_ID, Integer.valueOf(EXME_Farmacia_ID));
	}

	/** Get Pharmacy.
		@return Pharmacy	  */
	public int getEXME_Farmacia_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Farmacia_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_Frequency1 getEXME_Frequency1() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_Frequency1.Table_Name);
        I_EXME_Frequency1 result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_Frequency1)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Frequency1_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Frequency 1.
		@param EXME_Frequency1_ID 
		Frequency Header ID
	  */
	public void setEXME_Frequency1_ID (int EXME_Frequency1_ID)
	{
		if (EXME_Frequency1_ID < 1) 
			set_Value (COLUMNNAME_EXME_Frequency1_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_Frequency1_ID, Integer.valueOf(EXME_Frequency1_ID));
	}

	/** Get Frequency 1.
		@return Frequency Header ID
	  */
	public int getEXME_Frequency1_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Frequency1_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_Frequency2 getEXME_Frequency2() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_Frequency2.Table_Name);
        I_EXME_Frequency2 result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_Frequency2)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Frequency2_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Frequency 2.
		@param EXME_Frequency2_ID 
		Frequency First Detail ID
	  */
	public void setEXME_Frequency2_ID (int EXME_Frequency2_ID)
	{
		if (EXME_Frequency2_ID < 1) 
			set_Value (COLUMNNAME_EXME_Frequency2_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_Frequency2_ID, Integer.valueOf(EXME_Frequency2_ID));
	}

	/** Get Frequency 2.
		@return Frequency First Detail ID
	  */
	public int getEXME_Frequency2_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Frequency2_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_GenProduct getEXME_GenProduct() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_GenProduct.Table_Name);
        I_EXME_GenProduct result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_GenProduct)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_GenProduct_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Generic Product.
		@param EXME_GenProduct_ID Generic Product	  */
	public void setEXME_GenProduct_ID (int EXME_GenProduct_ID)
	{
		if (EXME_GenProduct_ID < 1) 
			set_Value (COLUMNNAME_EXME_GenProduct_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_GenProduct_ID, Integer.valueOf(EXME_GenProduct_ID));
	}

	/** Get Generic Product.
		@return Generic Product	  */
	public int getEXME_GenProduct_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_GenProduct_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_Medico getEXME_Medico() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_Medico.Table_Name);
        I_EXME_Medico result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_Medico)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Medico_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Doctor.
		@param EXME_Medico_ID 
		Doctor
	  */
	public void setEXME_Medico_ID (int EXME_Medico_ID)
	{
		if (EXME_Medico_ID < 1) 
			set_Value (COLUMNNAME_EXME_Medico_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_Medico_ID, Integer.valueOf(EXME_Medico_ID));
	}

	/** Get Doctor.
		@return Doctor
	  */
	public int getEXME_Medico_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Medico_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_OrderSet getEXME_OrderSet() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_OrderSet.Table_Name);
        I_EXME_OrderSet result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_OrderSet)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_OrderSet_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Order Set.
		@param EXME_OrderSet_ID Order Set	  */
	public void setEXME_OrderSet_ID (int EXME_OrderSet_ID)
	{
		if (EXME_OrderSet_ID < 1) 
			set_Value (COLUMNNAME_EXME_OrderSet_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_OrderSet_ID, Integer.valueOf(EXME_OrderSet_ID));
	}

	/** Get Order Set.
		@return Order Set	  */
	public int getEXME_OrderSet_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_OrderSet_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Pharmacist Certified.
		@param EXME_Pharmacistc_ID Pharmacist Certified	  */
	public void setEXME_Pharmacistc_ID (int EXME_Pharmacistc_ID)
	{
		if (EXME_Pharmacistc_ID < 1) 
			set_Value (COLUMNNAME_EXME_Pharmacistc_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_Pharmacistc_ID, Integer.valueOf(EXME_Pharmacistc_ID));
	}

	/** Get Pharmacist Certified.
		@return Pharmacist Certified	  */
	public int getEXME_Pharmacistc_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Pharmacistc_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Pharmacist Technician.
		@param EXME_PharmacistT_ID Pharmacist Technician	  */
	public void setEXME_PharmacistT_ID (int EXME_PharmacistT_ID)
	{
		if (EXME_PharmacistT_ID < 1) 
			set_Value (COLUMNNAME_EXME_PharmacistT_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_PharmacistT_ID, Integer.valueOf(EXME_PharmacistT_ID));
	}

	/** Get Pharmacist Technician.
		@return Pharmacist Technician	  */
	public int getEXME_PharmacistT_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_PharmacistT_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set RXNorm Prescription Detail.
		@param EXME_PrescRXDet_ID 
		RXNorm Prescription Detail
	  */
	public void setEXME_PrescRXDet_ID (int EXME_PrescRXDet_ID)
	{
		if (EXME_PrescRXDet_ID < 1)
			 throw new IllegalArgumentException ("EXME_PrescRXDet_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_PrescRXDet_ID, Integer.valueOf(EXME_PrescRXDet_ID));
	}

	/** Get RXNorm Prescription Detail.
		@return RXNorm Prescription Detail
	  */
	public int getEXME_PrescRXDet_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_PrescRXDet_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_PrescRX getEXME_PrescRX() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_PrescRX.Table_Name);
        I_EXME_PrescRX result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_PrescRX)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_PrescRX_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set RXNorm Prescription.
		@param EXME_PrescRX_ID 
		RXNorm Prescription
	  */
	public void setEXME_PrescRX_ID (int EXME_PrescRX_ID)
	{
		if (EXME_PrescRX_ID < 1)
			 throw new IllegalArgumentException ("EXME_PrescRX_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_PrescRX_ID, Integer.valueOf(EXME_PrescRX_ID));
	}

	/** Get RXNorm Prescription.
		@return RXNorm Prescription
	  */
	public int getEXME_PrescRX_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_PrescRX_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_Route getEXME_Route() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_Route.Table_Name);
        I_EXME_Route result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_Route)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Route_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Route.
		@param EXME_Route_ID 
		Route
	  */
	public void setEXME_Route_ID (int EXME_Route_ID)
	{
		if (EXME_Route_ID < 1) 
			set_Value (COLUMNNAME_EXME_Route_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_Route_ID, Integer.valueOf(EXME_Route_ID));
	}

	/** Get Route.
		@return Route
	  */
	public int getEXME_Route_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Route_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_ViasAdministracion getEXME_ViasAdministracion() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_ViasAdministracion.Table_Name);
        I_EXME_ViasAdministracion result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_ViasAdministracion)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_ViasAdministracion_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Route of Administration.
		@param EXME_ViasAdministracion_ID Route of Administration	  */
	public void setEXME_ViasAdministracion_ID (int EXME_ViasAdministracion_ID)
	{
		if (EXME_ViasAdministracion_ID < 1) 
			set_Value (COLUMNNAME_EXME_ViasAdministracion_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_ViasAdministracion_ID, Integer.valueOf(EXME_ViasAdministracion_ID));
	}

	/** Get Route of Administration.
		@return Route of Administration	  */
	public int getEXME_ViasAdministracion_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_ViasAdministracion_ID);
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

	/** Set Dispense as Written.
		@param IsDAW Dispense as Written	  */
	public void setIsDAW (boolean IsDAW)
	{
		set_Value (COLUMNNAME_IsDAW, Boolean.valueOf(IsDAW));
	}

	/** Get Dispense as Written.
		@return Dispense as Written	  */
	public boolean isDAW () 
	{
		Object oo = get_Value(COLUMNNAME_IsDAW);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Print DEA Number.
		@param IsDEA Print DEA Number	  */
	public void setIsDEA (boolean IsDEA)
	{
		set_Value (COLUMNNAME_IsDEA, Boolean.valueOf(IsDEA));
	}

	/** Get Print DEA Number.
		@return Print DEA Number	  */
	public boolean isDEA () 
	{
		Object oo = get_Value(COLUMNNAME_IsDEA);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Delivered.
		@param IsDelivered Delivered	  */
	public void setIsDelivered (boolean IsDelivered)
	{
		set_Value (COLUMNNAME_IsDelivered, Boolean.valueOf(IsDelivered));
	}

	/** Get Delivered.
		@return Delivered	  */
	public boolean isDelivered () 
	{
		Object oo = get_Value(COLUMNNAME_IsDelivered);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Is Prefered Medication.
		@param IsPrefer 
		Is Prefered Medication
	  */
	public void setIsPrefer (boolean IsPrefer)
	{
		set_Value (COLUMNNAME_IsPrefer, Boolean.valueOf(IsPrefer));
	}

	/** Get Is Prefered Medication.
		@return Is Prefered Medication
	  */
	public boolean isPrefer () 
	{
		Object oo = get_Value(COLUMNNAME_IsPrefer);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set When necessary.
		@param IsPRN When necessary	  */
	public void setIsPRN (boolean IsPRN)
	{
		set_Value (COLUMNNAME_IsPRN, Boolean.valueOf(IsPRN));
	}

	/** Get When necessary.
		@return When necessary	  */
	public boolean isPRN () 
	{
		Object oo = get_Value(COLUMNNAME_IsPRN);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Date of Medication Last Shot.
		@param LastDay Date of Medication Last Shot	  */
	public void setLastDay (Timestamp LastDay)
	{
		set_Value (COLUMNNAME_LastDay, LastDay);
	}

	/** Get Date of Medication Last Shot.
		@return Date of Medication Last Shot	  */
	public Timestamp getLastDay () 
	{
		return (Timestamp)get_Value(COLUMNNAME_LastDay);
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

	/** Set Notes.
		@param Notas Notes	  */
	public void setNotas (String Notas)
	{
		set_Value (COLUMNNAME_Notas, Notas);
	}

	/** Get Notes.
		@return Notes	  */
	public String getNotas () 
	{
		return (String)get_Value(COLUMNNAME_Notas);
	}

	/** Set Noted By.
		@param NotedBy Noted By	  */
	public void setNotedBy (int NotedBy)
	{
		set_Value (COLUMNNAME_NotedBy, Integer.valueOf(NotedBy));
	}

	/** Get Noted By.
		@return Noted By	  */
	public int getNotedBy () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_NotedBy);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Noted Date.
		@param NotedDate Noted Date	  */
	public void setNotedDate (Timestamp NotedDate)
	{
		set_Value (COLUMNNAME_NotedDate, NotedDate);
	}

	/** Get Noted Date.
		@return Noted Date	  */
	public Timestamp getNotedDate () 
	{
		return (Timestamp)get_Value(COLUMNNAME_NotedDate);
	}

	/** Set Notes.
		@param Notes Notes	  */
	public void setNotes (String Notes)
	{
		set_Value (COLUMNNAME_Notes, Notes);
	}

	/** Get Notes.
		@return Notes	  */
	public String getNotes () 
	{
		return (String)get_Value(COLUMNNAME_Notes);
	}

	/** Set Number of days.
		@param NumDias Number of days	  */
	public void setNumDias (int NumDias)
	{
		set_Value (COLUMNNAME_NumDias, Integer.valueOf(NumDias));
	}

	/** Get Number of days.
		@return Number of days	  */
	public int getNumDias () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_NumDias);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** OrderType AD_Reference_ID=1200543 */
	public static final int ORDERTYPE_AD_Reference_ID=1200543;
	/** Verbal Order = VO */
	public static final String ORDERTYPE_VerbalOrder = "VO";
	/** Written Order = WO */
	public static final String ORDERTYPE_WrittenOrder = "WO";
	/** Telephone Order = TO */
	public static final String ORDERTYPE_TelephoneOrder = "TO";
	/** Set OrderType.
		@param OrderType OrderType	  */
	public void setOrderType (String OrderType)
	{

		if (OrderType == null || OrderType.equals("VO") || OrderType.equals("WO") || OrderType.equals("TO")); else throw new IllegalArgumentException ("OrderType Invalid value - " + OrderType + " - Reference_ID=1200543 - VO - WO - TO");		set_Value (COLUMNNAME_OrderType, OrderType);
	}

	/** Get OrderType.
		@return OrderType	  */
	public String getOrderType () 
	{
		return (String)get_Value(COLUMNNAME_OrderType);
	}

	/** Set ORGEXME_PrescRXDet_ID.
		@param ORGEXME_PrescRXDet_ID 
		ORGEXME_PrescRXDet_ID
	  */
	public void setORGEXME_PrescRXDet_ID (int ORGEXME_PrescRXDet_ID)
	{
		if (ORGEXME_PrescRXDet_ID < 1) 
			set_Value (COLUMNNAME_ORGEXME_PrescRXDet_ID, null);
		else 
			set_Value (COLUMNNAME_ORGEXME_PrescRXDet_ID, Integer.valueOf(ORGEXME_PrescRXDet_ID));
	}

	/** Get ORGEXME_PrescRXDet_ID.
		@return ORGEXME_PrescRXDet_ID
	  */
	public int getORGEXME_PrescRXDet_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_ORGEXME_PrescRXDet_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Process Now.
		@param Processing Process Now	  */
	public void setProcessing (boolean Processing)
	{
		set_Value (COLUMNNAME_Processing, Boolean.valueOf(Processing));
	}

	/** Get Process Now.
		@return Process Now	  */
	public boolean isProcessing () 
	{
		Object oo = get_Value(COLUMNNAME_Processing);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Quantity Plan.
		@param QtyPlan 
		Planned Quantity
	  */
	public void setQtyPlan (BigDecimal QtyPlan)
	{
		set_Value (COLUMNNAME_QtyPlan, QtyPlan);
	}

	/** Get Quantity Plan.
		@return Planned Quantity
	  */
	public BigDecimal getQtyPlan () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_QtyPlan);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Quantity.
		@param Quantity Quantity	  */
	public void setQuantity (BigDecimal Quantity)
	{
		set_Value (COLUMNNAME_Quantity, Quantity);
	}

	/** Get Quantity.
		@return Quantity	  */
	public BigDecimal getQuantity () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Quantity);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** ReadBack AD_Reference_ID=319 */
	public static final int READBACK_AD_Reference_ID=319;
	/** Yes = Y */
	public static final String READBACK_Yes = "Y";
	/** No = N */
	public static final String READBACK_No = "N";
	/** Set Read Back.
		@param ReadBack 
		Read Back
	  */
	public void setReadBack (String ReadBack)
	{

		if (ReadBack == null || ReadBack.equals("Y") || ReadBack.equals("N")); else throw new IllegalArgumentException ("ReadBack Invalid value - " + ReadBack + " - Reference_ID=319 - Y - N");		set_Value (COLUMNNAME_ReadBack, ReadBack);
	}

	/** Get Read Back.
		@return Read Back
	  */
	public String getReadBack () 
	{
		return (String)get_Value(COLUMNNAME_ReadBack);
	}

	/** Set Medication reconciliation.
		@param Reconciliation 
		Medication reconciliation
	  */
	public void setReconciliation (boolean Reconciliation)
	{
		set_Value (COLUMNNAME_Reconciliation, Boolean.valueOf(Reconciliation));
	}

	/** Get Medication reconciliation.
		@return Medication reconciliation
	  */
	public boolean isReconciliation () 
	{
		Object oo = get_Value(COLUMNNAME_Reconciliation);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Refills.
		@param Resurtidos Refills	  */
	public void setResurtidos (int Resurtidos)
	{
		set_Value (COLUMNNAME_Resurtidos, Integer.valueOf(Resurtidos));
	}

	/** Get Refills.
		@return Refills	  */
	public int getResurtidos () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Resurtidos);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Pharmacist Review.
		@param RXReview Pharmacist Review	  */
	public void setRXReview (Timestamp RXReview)
	{
		set_Value (COLUMNNAME_RXReview, RXReview);
	}

	/** Get Pharmacist Review.
		@return Pharmacist Review	  */
	public Timestamp getRXReview () 
	{
		return (Timestamp)get_Value(COLUMNNAME_RXReview);
	}

	/** Set Pharmacist Review.
		@param RXReviewC Pharmacist Review	  */
	public void setRXReviewC (Timestamp RXReviewC)
	{
		set_Value (COLUMNNAME_RXReviewC, RXReviewC);
	}

	/** Get Pharmacist Review.
		@return Pharmacist Review	  */
	public Timestamp getRXReviewC () 
	{
		return (Timestamp)get_Value(COLUMNNAME_RXReviewC);
	}

	/** Set Start Date.
		@param StartDate 
		First effective day (inclusive)
	  */
	public void setStartDate (Timestamp StartDate)
	{
		set_Value (COLUMNNAME_StartDate, StartDate);
	}

	/** Get Start Date.
		@return First effective day (inclusive)
	  */
	public Timestamp getStartDate () 
	{
		return (Timestamp)get_Value(COLUMNNAME_StartDate);
	}

	/** Set Status.
		@param Status 
		Status of the currently running check
	  */
	public void setStatus (boolean Status)
	{
		set_Value (COLUMNNAME_Status, Boolean.valueOf(Status));
	}

	/** Get Status.
		@return Status of the currently running check
	  */
	public boolean isStatus () 
	{
		Object oo = get_Value(COLUMNNAME_Status);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Supplied by Infirmary Staff.
		@param SurtidoPorEnfermera 
		Medication supplied by Infirmary Staff
	  */
	public void setSurtidoPorEnfermera (boolean SurtidoPorEnfermera)
	{
		set_Value (COLUMNNAME_SurtidoPorEnfermera, Boolean.valueOf(SurtidoPorEnfermera));
	}

	/** Get Supplied by Infirmary Staff.
		@return Medication supplied by Infirmary Staff
	  */
	public boolean isSurtidoPorEnfermera () 
	{
		Object oo = get_Value(COLUMNNAME_SurtidoPorEnfermera);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Type AD_Reference_ID=1200618 */
	public static final int TYPE_AD_Reference_ID=1200618;
	/** Drug = D */
	public static final String TYPE_Drug = "D";
	/** Compound = C */
	public static final String TYPE_Compound = "C";
	/** Supply = S */
	public static final String TYPE_Supply = "S";
	/** Set Type.
		@param Type 
		Type of Validation
	  */
	public void setType (String Type)
	{
		if (Type == null) throw new IllegalArgumentException ("Type is mandatory");
		if (Type.equals("D") || Type.equals("C") || Type.equals("S")); else throw new IllegalArgumentException ("Type Invalid value - " + Type + " - Reference_ID=1200618 - D - C - S");		set_Value (COLUMNNAME_Type, Type);
	}

	/** Get Type.
		@return Type of Validation
	  */
	public String getType () 
	{
		return (String)get_Value(COLUMNNAME_Type);
	}
}