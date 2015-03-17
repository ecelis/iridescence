/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

/** Generated Model for EXME_Cuestionario
 *  @author Generated Class 
 *  @version Version @VERSION@ - $Id$ */
public class X_EXME_Cuestionario extends PO implements I_EXME_Cuestionario, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_Cuestionario (Properties ctx, int EXME_Cuestionario_ID, String trxName)
    {
      super (ctx, EXME_Cuestionario_ID, trxName);
      /** if (EXME_Cuestionario_ID == 0)
        {
			setEXME_Cuestionario_ID (0);
			setName (null);
			setNivel_Acc (null);
			setOnlyAnswer (false);
			setTipoArea (null);
			setValue (null);
        } */
    }

    /** Load Constructor */
    public X_EXME_Cuestionario (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_Cuestionario[")
        .append(get_ID()).append("]");
      return sb.toString();
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

	/** Set EsDefault.
		@param EsDefault EsDefault	  */
	public void setEsDefault (boolean EsDefault)
	{
		set_Value (COLUMNNAME_EsDefault, Boolean.valueOf(EsDefault));
	}

	/** Get EsDefault.
		@return EsDefault	  */
	public boolean isEsDefault () 
	{
		Object oo = get_Value(COLUMNNAME_EsDefault);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Questionnaire.
		@param EXME_Cuestionario_ID 
		Questionnaire
	  */
	public void setEXME_Cuestionario_ID (int EXME_Cuestionario_ID)
	{
		if (EXME_Cuestionario_ID < 1)
			 throw new IllegalArgumentException ("EXME_Cuestionario_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_Cuestionario_ID, Integer.valueOf(EXME_Cuestionario_ID));
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

	public I_EXME_Especialidad getEXME_Especialidad() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_Especialidad.Table_Name);
        I_EXME_Especialidad result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_Especialidad)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Especialidad_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Specialty.
		@param EXME_Especialidad_ID 
		Specialty
	  */
	public void setEXME_Especialidad_ID (int EXME_Especialidad_ID)
	{
		if (EXME_Especialidad_ID < 1) 
			set_Value (COLUMNNAME_EXME_Especialidad_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_Especialidad_ID, Integer.valueOf(EXME_Especialidad_ID));
	}

	/** Get Specialty.
		@return Specialty
	  */
	public int getEXME_Especialidad_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Especialidad_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_Reporte getEXME_Reporte() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_Reporte.Table_Name);
        I_EXME_Reporte result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_Reporte)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Reporte_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Report.
		@param EXME_Reporte_ID Report	  */
	public void setEXME_Reporte_ID (int EXME_Reporte_ID)
	{
		if (EXME_Reporte_ID < 1) 
			set_Value (COLUMNNAME_EXME_Reporte_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_Reporte_ID, Integer.valueOf(EXME_Reporte_ID));
	}

	/** Get Report.
		@return Report	  */
	public int getEXME_Reporte_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Reporte_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set ISGLOBAL.
		@param ISGLOBAL ISGLOBAL	  */
	public void setISGLOBAL (boolean ISGLOBAL)
	{
		set_Value (COLUMNNAME_ISGLOBAL, Boolean.valueOf(ISGLOBAL));
	}

	/** Get ISGLOBAL.
		@return ISGLOBAL	  */
	public boolean isGLOBAL () 
	{
		Object oo = get_Value(COLUMNNAME_ISGLOBAL);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Required.
		@param IsRequired 
		Required
	  */
	public void setIsRequired (boolean IsRequired)
	{
		set_Value (COLUMNNAME_IsRequired, Boolean.valueOf(IsRequired));
	}

	/** Get Required.
		@return Required
	  */
	public boolean isRequired () 
	{
		Object oo = get_Value(COLUMNNAME_IsRequired);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Name.
		@param Name 
		Alphanumeric identifier of the entity
	  */
	public void setName (String Name)
	{
		if (Name == null)
			throw new IllegalArgumentException ("Name is mandatory.");
		set_ValueNoCheck (COLUMNNAME_Name, Name);
	}

	/** Get Name.
		@return Alphanumeric identifier of the entity
	  */
	public String getName () 
	{
		return (String)get_Value(COLUMNNAME_Name);
	}

	/** Nivel_Acc AD_Reference_ID=1200560 */
	public static final int NIVEL_ACC_AD_Reference_ID=1200560;
	/** All = T */
	public static final String NIVEL_ACC_All = "T";
	/** Assistant = A */
	public static final String NIVEL_ACC_Assistant = "A";
	/** Physician = P */
	public static final String NIVEL_ACC_Physician = "P";
	/** Set Level Access.
		@param Nivel_Acc Level Access	  */
	public void setNivel_Acc (String Nivel_Acc)
	{
		if (Nivel_Acc == null) throw new IllegalArgumentException ("Nivel_Acc is mandatory");
		if (Nivel_Acc.equals("T") || Nivel_Acc.equals("A") || Nivel_Acc.equals("P")); else throw new IllegalArgumentException ("Nivel_Acc Invalid value - " + Nivel_Acc + " - Reference_ID=1200560 - T - A - P");		set_Value (COLUMNNAME_Nivel_Acc, Nivel_Acc);
	}

	/** Get Level Access.
		@return Level Access	  */
	public String getNivel_Acc () 
	{
		return (String)get_Value(COLUMNNAME_Nivel_Acc);
	}

	/** Set Only Answer.
		@param OnlyAnswer Only Answer	  */
	public void setOnlyAnswer (boolean OnlyAnswer)
	{
		set_Value (COLUMNNAME_OnlyAnswer, Boolean.valueOf(OnlyAnswer));
	}

	/** Get Only Answer.
		@return Only Answer	  */
	public boolean isOnlyAnswer () 
	{
		Object oo = get_Value(COLUMNNAME_OnlyAnswer);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Default Process.
		@param ProcessDef 
		Default Process
	  */
	public void setProcessDef (String ProcessDef)
	{
		set_Value (COLUMNNAME_ProcessDef, ProcessDef);
	}

	/** Get Default Process.
		@return Default Process
	  */
	public String getProcessDef () 
	{
		return (String)get_Value(COLUMNNAME_ProcessDef);
	}

	/** Set Required Process.
		@param ProcessReq 
		Required Process
	  */
	public void setProcessReq (String ProcessReq)
	{
		set_Value (COLUMNNAME_ProcessReq, ProcessReq);
	}

	/** Get Required Process.
		@return Required Process
	  */
	public String getProcessReq () 
	{
		return (String)get_Value(COLUMNNAME_ProcessReq);
	}

	/** Set Score.
		@param Score Score	  */
	public void setScore (int Score)
	{
		set_Value (COLUMNNAME_Score, Integer.valueOf(Score));
	}

	/** Get Score.
		@return Score	  */
	public int getScore () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Score);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** TipoArea AD_Reference_ID=1000039 */
	public static final int TIPOAREA_AD_Reference_ID=1000039;
	/** Hospitalization = H */
	public static final String TIPOAREA_Hospitalization = "H";
	/** Emergency = U */
	public static final String TIPOAREA_Emergency = "U";
	/** Ambulatory = A */
	public static final String TIPOAREA_Ambulatory = "A";
	/** Medical Consultation = C */
	public static final String TIPOAREA_MedicalConsultation = "C";
	/** Procedures = P */
	public static final String TIPOAREA_Procedures = "P";
	/** Medical Records = R */
	public static final String TIPOAREA_MedicalRecords = "R";
	/** Other = O */
	public static final String TIPOAREA_Other = "O";
	/** External = E */
	public static final String TIPOAREA_External = "E";
	/** Admission = D */
	public static final String TIPOAREA_Admission = "D";
	/** Social Work = T */
	public static final String TIPOAREA_SocialWork = "T";
	/** Social Comunication = S */
	public static final String TIPOAREA_SocialComunication = "S";
	/** Set Area Type.
		@param TipoArea 
		Admission Area Type
	  */
	public void setTipoArea (String TipoArea)
	{
		if (TipoArea == null) throw new IllegalArgumentException ("TipoArea is mandatory");
		if (TipoArea.equals("H") || TipoArea.equals("U") || TipoArea.equals("A") || TipoArea.equals("C") || TipoArea.equals("P") || TipoArea.equals("R") || TipoArea.equals("O") || TipoArea.equals("E") || TipoArea.equals("D") || TipoArea.equals("T") || TipoArea.equals("S")); else throw new IllegalArgumentException ("TipoArea Invalid value - " + TipoArea + " - Reference_ID=1000039 - H - U - A - C - P - R - O - E - D - T - S");		set_Value (COLUMNNAME_TipoArea, TipoArea);
	}

	/** Get Area Type.
		@return Admission Area Type
	  */
	public String getTipoArea () 
	{
		return (String)get_Value(COLUMNNAME_TipoArea);
	}

	/** Type AD_Reference_ID=1200641 */
	public static final int TYPE_AD_Reference_ID=1200641;
	/** Other = O */
	public static final String TYPE_Other = "O";
	/** Specialty = SP */
	public static final String TYPE_Specialty = "SP";
	/** Surgical = SU */
	public static final String TYPE_Surgical = "SU";
	/** Special Form = SF */
	public static final String TYPE_SpecialForm = "SF";
	/** Set Type.
		@param Type 
		Type of Validation
	  */
	public void setType (String Type)
	{

		if (Type == null || Type.equals("O") || Type.equals("SP") || Type.equals("SU") || Type.equals("SF")); else throw new IllegalArgumentException ("Type Invalid value - " + Type + " - Reference_ID=1200641 - O - SP - SU - SF");		set_Value (COLUMNNAME_Type, Type);
	}

	/** Get Type.
		@return Type of Validation
	  */
	public String getType () 
	{
		return (String)get_Value(COLUMNNAME_Type);
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