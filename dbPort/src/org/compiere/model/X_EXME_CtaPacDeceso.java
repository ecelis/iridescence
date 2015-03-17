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

/** Generated Model for EXME_CtaPacDeceso
 *  @author Generated Class 
 *  @version Version @VERSION@ - $Id$ */
public class X_EXME_CtaPacDeceso extends PO implements I_EXME_CtaPacDeceso, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_CtaPacDeceso (Properties ctx, int EXME_CtaPacDeceso_ID, String trxName)
    {
      super (ctx, EXME_CtaPacDeceso_ID, trxName);
      /** if (EXME_CtaPacDeceso_ID == 0)
        {
			setEXME_CtaPacDeceso_ID (0);
			setEXME_CtaPac_ID (0);
			setFecha_Muerte (new Timestamp( System.currentTimeMillis() ));
			setValue (null);
        } */
    }

    /** Load Constructor */
    public X_EXME_CtaPacDeceso (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_CtaPacDeceso[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Gestational week of abortion.
		@param AbortoGestacion Gestational week of abortion	  */
	public void setAbortoGestacion (BigDecimal AbortoGestacion)
	{
		set_Value (COLUMNNAME_AbortoGestacion, AbortoGestacion);
	}

	/** Get Gestational week of abortion.
		@return Gestational week of abortion	  */
	public BigDecimal getAbortoGestacion () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_AbortoGestacion);
		if (bd == null)
			 return Env.ZERO;
		return bd;
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

	/** Set External Causes.
		@param CausaExterna External Causes	  */
	public void setCausaExterna (String CausaExterna)
	{
		set_Value (COLUMNNAME_CausaExterna, CausaExterna);
	}

	/** Get External Causes.
		@return External Causes	  */
	public String getCausaExterna () 
	{
		return (String)get_Value(COLUMNNAME_CausaExterna);
	}

	/** CitaDe AD_Reference_ID=1200056 */
	public static final int CITADE_AD_Reference_ID=1200056;
	/** First Time = P */
	public static final String CITADE_FirstTime = "P";
	/** Pre Appointment = R */
	public static final String CITADE_PreAppointment = "R";
	/** Subsequent = S */
	public static final String CITADE_Subsequent = "S";
	/** Inter Appoinment = I */
	public static final String CITADE_InterAppoinment = "I";
	/** New Pt. Established = PE */
	public static final String CITADE_NewPtEstablished = "PE";
	/** Pt. Follow Up = FU */
	public static final String CITADE_PtFollowUp = "FU";
	/** Set Appointment Of.
		@param CitaDe 
		Appointment Of
	  */
	public void setCitaDe (String CitaDe)
	{

		if (CitaDe == null || CitaDe.equals("P") || CitaDe.equals("R") || CitaDe.equals("S") || CitaDe.equals("I") || CitaDe.equals("PE") || CitaDe.equals("FU")); else throw new IllegalArgumentException ("CitaDe Invalid value - " + CitaDe + " - Reference_ID=1200056 - P - R - S - I - PE - FU");		set_Value (COLUMNNAME_CitaDe, CitaDe);
	}

	/** Get Appointment Of.
		@return Appointment Of
	  */
	public String getCitaDe () 
	{
		return (String)get_Value(COLUMNNAME_CitaDe);
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

	/** Set Diagnostic.
		@param Diagnostico 
		Diagnostic
	  */
	public void setDiagnostico (String Diagnostico)
	{
		set_Value (COLUMNNAME_Diagnostico, Diagnostico);
	}

	/** Get Diagnostic.
		@return Diagnostic
	  */
	public String getDiagnostico () 
	{
		return (String)get_Value(COLUMNNAME_Diagnostico);
	}

	/** Estatus AD_Reference_ID=1200462 */
	public static final int ESTATUS_AD_Reference_ID=1200462;
	/** Active = 1 */
	public static final String ESTATUS_Active = "1";
	/** Canceled = 2 */
	public static final String ESTATUS_Canceled = "2";
	/** Set Status.
		@param Estatus 
		Status
	  */
	public void setEstatus (String Estatus)
	{

		if (Estatus == null || Estatus.equals("1") || Estatus.equals("2")); else throw new IllegalArgumentException ("Estatus Invalid value - " + Estatus + " - Reference_ID=1200462 - 1 - 2");		set_Value (COLUMNNAME_Estatus, Estatus);
	}

	/** Get Status.
		@return Status
	  */
	public String getEstatus () 
	{
		return (String)get_Value(COLUMNNAME_Estatus);
	}

	/** Set Death of Encounter.
		@param EXME_CtaPacDeceso_ID Death of Encounter	  */
	public void setEXME_CtaPacDeceso_ID (int EXME_CtaPacDeceso_ID)
	{
		if (EXME_CtaPacDeceso_ID < 1)
			 throw new IllegalArgumentException ("EXME_CtaPacDeceso_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_CtaPacDeceso_ID, Integer.valueOf(EXME_CtaPacDeceso_ID));
	}

	/** Get Death of Encounter.
		@return Death of Encounter	  */
	public int getEXME_CtaPacDeceso_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_CtaPacDeceso_ID);
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

	public I_EXME_MotivoCancel getEXME_MotivoCancel() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_MotivoCancel.Table_Name);
        I_EXME_MotivoCancel result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_MotivoCancel)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_MotivoCancel_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Cancel Reason.
		@param EXME_MotivoCancel_ID 
		Cancel Reason
	  */
	public void setEXME_MotivoCancel_ID (int EXME_MotivoCancel_ID)
	{
		if (EXME_MotivoCancel_ID < 1) 
			set_Value (COLUMNNAME_EXME_MotivoCancel_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_MotivoCancel_ID, Integer.valueOf(EXME_MotivoCancel_ID));
	}

	/** Get Cancel Reason.
		@return Cancel Reason
	  */
	public int getEXME_MotivoCancel_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_MotivoCancel_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_MotivoMuerte getEXME_MotivoMuerte() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_MotivoMuerte.Table_Name);
        I_EXME_MotivoMuerte result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_MotivoMuerte)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_MotivoMuerte_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Death Cause.
		@param EXME_MotivoMuerte_ID 
		Death Cause
	  */
	public void setEXME_MotivoMuerte_ID (int EXME_MotivoMuerte_ID)
	{
		if (EXME_MotivoMuerte_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_EXME_MotivoMuerte_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_EXME_MotivoMuerte_ID, Integer.valueOf(EXME_MotivoMuerte_ID));
	}

	/** Get Death Cause.
		@return Death Cause
	  */
	public int getEXME_MotivoMuerte_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_MotivoMuerte_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Date of Death.
		@param Fecha_Muerte 
		Date of Death
	  */
	public void setFecha_Muerte (Timestamp Fecha_Muerte)
	{
		if (Fecha_Muerte == null)
			throw new IllegalArgumentException ("Fecha_Muerte is mandatory.");
		set_Value (COLUMNNAME_Fecha_Muerte, Fecha_Muerte);
	}

	/** Get Date of Death.
		@return Date of Death
	  */
	public Timestamp getFecha_Muerte () 
	{
		return (Timestamp)get_Value(COLUMNNAME_Fecha_Muerte);
	}

	/** IntoxEtilica AD_Reference_ID=1200205 */
	public static final int INTOXETILICA_AD_Reference_ID=1200205;
	/** NO = 1 */
	public static final String INTOXETILICA_NO = "1";
	/** I Grade = 2 */
	public static final String INTOXETILICA_IGrade = "2";
	/** II Grade = 3 */
	public static final String INTOXETILICA_IIGrade = "3";
	/** III Grade = 4 */
	public static final String INTOXETILICA_IIIGrade = "4";
	/** Set Alcohol Poisoning.
		@param IntoxEtilica Alcohol Poisoning	  */
	public void setIntoxEtilica (String IntoxEtilica)
	{

		if (IntoxEtilica == null || IntoxEtilica.equals("1") || IntoxEtilica.equals("2") || IntoxEtilica.equals("3") || IntoxEtilica.equals("4")); else throw new IllegalArgumentException ("IntoxEtilica Invalid value - " + IntoxEtilica + " - Reference_ID=1200205 - 1 - 2 - 3 - 4");		set_Value (COLUMNNAME_IntoxEtilica, IntoxEtilica);
	}

	/** Get Alcohol Poisoning.
		@return Alcohol Poisoning	  */
	public String getIntoxEtilica () 
	{
		return (String)get_Value(COLUMNNAME_IntoxEtilica);
	}

	/** MortMaternaCausa AD_Reference_ID=1200203 */
	public static final int MORTMATERNACAUSA_AD_Reference_ID=1200203;
	/** Direct = 1 */
	public static final String MORTMATERNACAUSA_Direct = "1";
	/** Indirect = 2 */
	public static final String MORTMATERNACAUSA_Indirect = "2";
	/** Set Maternal Mortality in Relation to the Cause.
		@param MortMaternaCausa Maternal Mortality in Relation to the Cause	  */
	public void setMortMaternaCausa (String MortMaternaCausa)
	{

		if (MortMaternaCausa == null || MortMaternaCausa.equals("1") || MortMaternaCausa.equals("2")); else throw new IllegalArgumentException ("MortMaternaCausa Invalid value - " + MortMaternaCausa + " - Reference_ID=1200203 - 1 - 2");		set_Value (COLUMNNAME_MortMaternaCausa, MortMaternaCausa);
	}

	/** Get Maternal Mortality in Relation to the Cause.
		@return Maternal Mortality in Relation to the Cause	  */
	public String getMortMaternaCausa () 
	{
		return (String)get_Value(COLUMNNAME_MortMaternaCausa);
	}

	/** MortMaternaTiempo AD_Reference_ID=1200204 */
	public static final int MORTMATERNATIEMPO_AD_Reference_ID=1200204;
	/** Maternal Death = 1 */
	public static final String MORTMATERNATIEMPO_MaternalDeath = "1";
	/** Maternal Death Delayed = 2 */
	public static final String MORTMATERNATIEMPO_MaternalDeathDelayed = "2";
	/** Maternal death related to pregnancy = 3 */
	public static final String MORTMATERNATIEMPO_MaternalDeathRelatedToPregnancy = "3";
	/** Set Maternal Mortality in Relation to Time.
		@param MortMaternaTiempo Maternal Mortality in Relation to Time	  */
	public void setMortMaternaTiempo (String MortMaternaTiempo)
	{

		if (MortMaternaTiempo == null || MortMaternaTiempo.equals("1") || MortMaternaTiempo.equals("2") || MortMaternaTiempo.equals("3")); else throw new IllegalArgumentException ("MortMaternaTiempo Invalid value - " + MortMaternaTiempo + " - Reference_ID=1200204 - 1 - 2 - 3");		set_Value (COLUMNNAME_MortMaternaTiempo, MortMaternaTiempo);
	}

	/** Get Maternal Mortality in Relation to Time.
		@return Maternal Mortality in Relation to Time	  */
	public String getMortMaternaTiempo () 
	{
		return (String)get_Value(COLUMNNAME_MortMaternaTiempo);
	}

	/** Set Cancel Reason.
		@param MotivoCancel 
		Cancel Reason
	  */
	public void setMotivoCancel (String MotivoCancel)
	{
		set_Value (COLUMNNAME_MotivoCancel, MotivoCancel);
	}

	/** Get Cancel Reason.
		@return Cancel Reason
	  */
	public String getMotivoCancel () 
	{
		return (String)get_Value(COLUMNNAME_MotivoCancel);
	}

	/** Set Weight.
		@param Peso 
		Weight
	  */
	public void setPeso (BigDecimal Peso)
	{
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

	/** Set Weeks Gestation.
		@param SemGestacion Weeks Gestation	  */
	public void setSemGestacion (BigDecimal SemGestacion)
	{
		set_Value (COLUMNNAME_SemGestacion, SemGestacion);
	}

	/** Get Weeks Gestation.
		@return Weeks Gestation	  */
	public BigDecimal getSemGestacion () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_SemGestacion);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** TipoCtaPac AD_Reference_ID=1200206 */
	public static final int TIPOCTAPAC_AD_Reference_ID=1200206;
	/** Maternity = 1 */
	public static final String TIPOCTAPAC_Maternity = "1";
	/** Pediatric = 2 */
	public static final String TIPOCTAPAC_Pediatric = "2";
	/** General = 3 */
	public static final String TIPOCTAPAC_General = "3";
	/** Set Encounter Type.
		@param TipoCtaPac Encounter Type	  */
	public void setTipoCtaPac (String TipoCtaPac)
	{

		if (TipoCtaPac == null || TipoCtaPac.equals("1") || TipoCtaPac.equals("2") || TipoCtaPac.equals("3")); else throw new IllegalArgumentException ("TipoCtaPac Invalid value - " + TipoCtaPac + " - Reference_ID=1200206 - 1 - 2 - 3");		set_Value (COLUMNNAME_TipoCtaPac, TipoCtaPac);
	}

	/** Get Encounter Type.
		@return Encounter Type	  */
	public String getTipoCtaPac () 
	{
		return (String)get_Value(COLUMNNAME_TipoCtaPac);
	}

	/** Set Search Key.
		@param Value 
		Search key for the record in the format required - must be unique
	  */
	public void setValue (String Value)
	{
		if (Value == null)
			throw new IllegalArgumentException ("Value is mandatory.");
		set_Value (COLUMNNAME_Value, Value);
	}

	/** Get Search Key.
		@return Search key for the record in the format required - must be unique
	  */
	public String getValue () 
	{
		return (String)get_Value(COLUMNNAME_Value);
	}
}