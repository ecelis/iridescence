/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

/** Generated Model for EXME_Paciente_TS
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_Paciente_TS extends PO implements I_EXME_Paciente_TS, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_Paciente_TS (Properties ctx, int EXME_Paciente_TS_ID, String trxName)
    {
      super (ctx, EXME_Paciente_TS_ID, trxName);
      /** if (EXME_Paciente_TS_ID == 0)
        {
			setConyuge_Desco (false);
			setConyuge_Vive (false);
			setEXME_Escolaridad_ID (0);
			setEXME_Especialidad_TS_ID (0);
			setEXME_Nacionalidad_ID (0);
			setEXME_Paciente_ID (0);
			setEXME_Paciente_TS_ID (0);
			setEXME_Referencia_ID (0);
			setEXME_Referencia_Int_ID (0);
			setMadre_Desco (false);
			setMadre_Vive (false);
			setPadre_Desco (false);
			setPadre_Vive (false);
        } */
    }

    /** Load Constructor */
    public X_EXME_Paciente_TS (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_Paciente_TS[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Trx Organization.
		@param AD_OrgTrx_ID 
		Performing or initiating organization
	  */
	public void setAD_OrgTrx_ID (int AD_OrgTrx_ID)
	{
		if (AD_OrgTrx_ID < 1) 
			set_Value (COLUMNNAME_AD_OrgTrx_ID, null);
		else 
			set_Value (COLUMNNAME_AD_OrgTrx_ID, Integer.valueOf(AD_OrgTrx_ID));
	}

	/** Get Trx Organization.
		@return Performing or initiating organization
	  */
	public int getAD_OrgTrx_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_OrgTrx_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Responsible Person's Last Name.
		@param Apellido1_Resp Responsible Person's Last Name	  */
	public void setApellido1_Resp (String Apellido1_Resp)
	{
		set_Value (COLUMNNAME_Apellido1_Resp, Apellido1_Resp);
	}

	/** Get Responsible Person's Last Name.
		@return Responsible Person's Last Name	  */
	public String getApellido1_Resp () 
	{
		return (String)get_Value(COLUMNNAME_Apellido1_Resp);
	}

	/** Set Responsible Person's Mother's Maiden Name.
		@param Apellido2_Resp Responsible Person's Mother's Maiden Name	  */
	public void setApellido2_Resp (String Apellido2_Resp)
	{
		set_Value (COLUMNNAME_Apellido2_Resp, Apellido2_Resp);
	}

	/** Get Responsible Person's Mother's Maiden Name.
		@return Responsible Person's Mother's Maiden Name	  */
	public String getApellido2_Resp () 
	{
		return (String)get_Value(COLUMNNAME_Apellido2_Resp);
	}

	/** Set Responsable Address.
		@param C_Location_D_Resp_ID 
		Responsable Address
	  */
	public void setC_Location_D_Resp_ID (int C_Location_D_Resp_ID)
	{
		if (C_Location_D_Resp_ID < 1) 
			set_Value (COLUMNNAME_C_Location_D_Resp_ID, null);
		else 
			set_Value (COLUMNNAME_C_Location_D_Resp_ID, Integer.valueOf(C_Location_D_Resp_ID));
	}

	/** Get Responsable Address.
		@return Responsable Address
	  */
	public int getC_Location_D_Resp_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Location_D_Resp_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Permanent Address.
		@param C_Location_Perm_ID 
		Permanent Address
	  */
	public void setC_Location_Perm_ID (int C_Location_Perm_ID)
	{
		if (C_Location_Perm_ID < 1) 
			set_Value (COLUMNNAME_C_Location_Perm_ID, null);
		else 
			set_Value (COLUMNNAME_C_Location_Perm_ID, Integer.valueOf(C_Location_Perm_ID));
	}

	/** Get Permanent Address.
		@return Permanent Address
	  */
	public int getC_Location_Perm_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Location_Perm_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Prov. Patient's Address.
		@param C_Location_P_Prov_ID 
		Prov. Patient's Address
	  */
	public void setC_Location_P_Prov_ID (int C_Location_P_Prov_ID)
	{
		if (C_Location_P_Prov_ID < 1) 
			set_Value (COLUMNNAME_C_Location_P_Prov_ID, null);
		else 
			set_Value (COLUMNNAME_C_Location_P_Prov_ID, Integer.valueOf(C_Location_P_Prov_ID));
	}

	/** Get Prov. Patient's Address.
		@return Prov. Patient's Address
	  */
	public int getC_Location_P_Prov_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Location_P_Prov_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Patient Work's Address.
		@param C_Location_P_Trab_ID 
		Patient Work's Address
	  */
	public void setC_Location_P_Trab_ID (int C_Location_P_Trab_ID)
	{
		if (C_Location_P_Trab_ID < 1) 
			set_Value (COLUMNNAME_C_Location_P_Trab_ID, null);
		else 
			set_Value (COLUMNNAME_C_Location_P_Trab_ID, Integer.valueOf(C_Location_P_Trab_ID));
	}

	/** Get Patient Work's Address.
		@return Patient Work's Address
	  */
	public int getC_Location_P_Trab_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Location_P_Trab_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Responsable Work's Address.
		@param C_Location_T_Resp_ID 
		Responsable Work's Address
	  */
	public void setC_Location_T_Resp_ID (int C_Location_T_Resp_ID)
	{
		if (C_Location_T_Resp_ID < 1) 
			set_Value (COLUMNNAME_C_Location_T_Resp_ID, null);
		else 
			set_Value (COLUMNNAME_C_Location_T_Resp_ID, Integer.valueOf(C_Location_T_Resp_ID));
	}

	/** Get Responsable Work's Address.
		@return Responsable Work's Address
	  */
	public int getC_Location_T_Resp_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Location_T_Resp_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Unknown Spouse.
		@param Conyuge_Desco 
		Unknown Spouse
	  */
	public void setConyuge_Desco (boolean Conyuge_Desco)
	{
		set_Value (COLUMNNAME_Conyuge_Desco, Boolean.valueOf(Conyuge_Desco));
	}

	/** Get Unknown Spouse.
		@return Unknown Spouse
	  */
	public boolean isConyuge_Desco () 
	{
		Object oo = get_Value(COLUMNNAME_Conyuge_Desco);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Alive Spouse.
		@param Conyuge_Vive 
		Alive Spouse
	  */
	public void setConyuge_Vive (boolean Conyuge_Vive)
	{
		set_Value (COLUMNNAME_Conyuge_Vive, Boolean.valueOf(Conyuge_Vive));
	}

	/** Get Alive Spouse.
		@return Alive Spouse
	  */
	public boolean isConyuge_Vive () 
	{
		Object oo = get_Value(COLUMNNAME_Conyuge_Vive);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set CopyAddPerm.
		@param CopyAddPerm 
		CopyAddPerm
	  */
	public void setCopyAddPerm (String CopyAddPerm)
	{
		set_Value (COLUMNNAME_CopyAddPerm, CopyAddPerm);
	}

	/** Get CopyAddPerm.
		@return CopyAddPerm
	  */
	public String getCopyAddPerm () 
	{
		return (String)get_Value(COLUMNNAME_CopyAddPerm);
	}

	/** Set Copy Address to Responsible.
		@param CopyAddr 
		Copy Address to Responsible
	  */
	public void setCopyAddr (String CopyAddr)
	{
		set_Value (COLUMNNAME_CopyAddr, CopyAddr);
	}

	/** Get Copy Address to Responsible.
		@return Copy Address to Responsible
	  */
	public String getCopyAddr () 
	{
		return (String)get_Value(COLUMNNAME_CopyAddr);
	}

	/** Set Final Diagnostic.
		@param Diagnostico_Egr 
		Final Diagnostic
	  */
	public void setDiagnostico_Egr (String Diagnostico_Egr)
	{
		set_Value (COLUMNNAME_Diagnostico_Egr, Diagnostico_Egr);
	}

	/** Get Final Diagnostic.
		@return Final Diagnostic
	  */
	public String getDiagnostico_Egr () 
	{
		return (String)get_Value(COLUMNNAME_Diagnostico_Egr);
	}

	/** Set Initial Diagnostic.
		@param Diagnostico_Ing 
		Initial Diagnostic
	  */
	public void setDiagnostico_Ing (String Diagnostico_Ing)
	{
		set_Value (COLUMNNAME_Diagnostico_Ing, Diagnostico_Ing);
	}

	/** Get Initial Diagnostic.
		@return Initial Diagnostic
	  */
	public String getDiagnostico_Ing () 
	{
		return (String)get_Value(COLUMNNAME_Diagnostico_Ing);
	}

	/** Set Age of mother.
		@param Edad_Madre 
		Age of mother
	  */
	public void setEdad_Madre (int Edad_Madre)
	{
		set_Value (COLUMNNAME_Edad_Madre, Integer.valueOf(Edad_Madre));
	}

	/** Get Age of mother.
		@return Age of mother
	  */
	public int getEdad_Madre () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Edad_Madre);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Age of father.
		@param Edad_Padre 
		Age of father
	  */
	public void setEdad_Padre (int Edad_Padre)
	{
		set_Value (COLUMNNAME_Edad_Padre, Integer.valueOf(Edad_Padre));
	}

	/** Get Age of father.
		@return Age of father
	  */
	public int getEdad_Padre () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Edad_Padre);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** EdoCivil_Madre AD_Reference_ID=1200122 */
	public static final int EDOCIVIL_MADRE_AD_Reference_ID=1200122;
	/** Single = F */
	public static final String EDOCIVIL_MADRE_Single = "F";
	/** Civilian = G */
	public static final String EDOCIVIL_MADRE_Civilian = "G";
	/** Monk = H */
	public static final String EDOCIVIL_MADRE_Monk = "H";
	/** Civilian and Monk = I */
	public static final String EDOCIVIL_MADRE_CivilianAndMonk = "I";
	/** Free Union = J */
	public static final String EDOCIVIL_MADRE_FreeUnion = "J";
	/** Divorced = D */
	public static final String EDOCIVIL_MADRE_Divorced = "D";
	/** Separated = L */
	public static final String EDOCIVIL_MADRE_Separated = "L";
	/** Widower = Q */
	public static final String EDOCIVIL_MADRE_Widower = "Q";
	/** Institutional Protection = R */
	public static final String EDOCIVIL_MADRE_InstitutionalProtection = "R";
	/** Tutor = S */
	public static final String EDOCIVIL_MADRE_Tutor = "S";
	/** Set Marital status of mother.
		@param EdoCivil_Madre 
		Marital status of mother
	  */
	public void setEdoCivil_Madre (String EdoCivil_Madre)
	{

		if (EdoCivil_Madre == null || EdoCivil_Madre.equals("F") || EdoCivil_Madre.equals("G") || EdoCivil_Madre.equals("H") || EdoCivil_Madre.equals("I") || EdoCivil_Madre.equals("J") || EdoCivil_Madre.equals("D") || EdoCivil_Madre.equals("L") || EdoCivil_Madre.equals("Q") || EdoCivil_Madre.equals("R") || EdoCivil_Madre.equals("S")); else throw new IllegalArgumentException ("EdoCivil_Madre Invalid value - " + EdoCivil_Madre + " - Reference_ID=1200122 - F - G - H - I - J - D - L - Q - R - S");		set_Value (COLUMNNAME_EdoCivil_Madre, EdoCivil_Madre);
	}

	/** Get Marital status of mother.
		@return Marital status of mother
	  */
	public String getEdoCivil_Madre () 
	{
		return (String)get_Value(COLUMNNAME_EdoCivil_Madre);
	}

	/** EdoCivil_Padre AD_Reference_ID=1200122 */
	public static final int EDOCIVIL_PADRE_AD_Reference_ID=1200122;
	/** Single = F */
	public static final String EDOCIVIL_PADRE_Single = "F";
	/** Civilian = G */
	public static final String EDOCIVIL_PADRE_Civilian = "G";
	/** Monk = H */
	public static final String EDOCIVIL_PADRE_Monk = "H";
	/** Civilian and Monk = I */
	public static final String EDOCIVIL_PADRE_CivilianAndMonk = "I";
	/** Free Union = J */
	public static final String EDOCIVIL_PADRE_FreeUnion = "J";
	/** Divorced = D */
	public static final String EDOCIVIL_PADRE_Divorced = "D";
	/** Separated = L */
	public static final String EDOCIVIL_PADRE_Separated = "L";
	/** Widower = Q */
	public static final String EDOCIVIL_PADRE_Widower = "Q";
	/** Institutional Protection = R */
	public static final String EDOCIVIL_PADRE_InstitutionalProtection = "R";
	/** Tutor = S */
	public static final String EDOCIVIL_PADRE_Tutor = "S";
	/** Set Marital status of father.
		@param EdoCivil_Padre 
		Marital status of father
	  */
	public void setEdoCivil_Padre (String EdoCivil_Padre)
	{

		if (EdoCivil_Padre == null || EdoCivil_Padre.equals("F") || EdoCivil_Padre.equals("G") || EdoCivil_Padre.equals("H") || EdoCivil_Padre.equals("I") || EdoCivil_Padre.equals("J") || EdoCivil_Padre.equals("D") || EdoCivil_Padre.equals("L") || EdoCivil_Padre.equals("Q") || EdoCivil_Padre.equals("R") || EdoCivil_Padre.equals("S")); else throw new IllegalArgumentException ("EdoCivil_Padre Invalid value - " + EdoCivil_Padre + " - Reference_ID=1200122 - F - G - H - I - J - D - L - Q - R - S");		set_Value (COLUMNNAME_EdoCivil_Padre, EdoCivil_Padre);
	}

	/** Get Marital status of father.
		@return Marital status of father
	  */
	public String getEdoCivil_Padre () 
	{
		return (String)get_Value(COLUMNNAME_EdoCivil_Padre);
	}

	/** Set Responsible Address Area.
		@param EXME_Delegacion_D_Resp_ID 
		Responsible Address Area
	  */
	public void setEXME_Delegacion_D_Resp_ID (int EXME_Delegacion_D_Resp_ID)
	{
		if (EXME_Delegacion_D_Resp_ID < 1) 
			set_Value (COLUMNNAME_EXME_Delegacion_D_Resp_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_Delegacion_D_Resp_ID, Integer.valueOf(EXME_Delegacion_D_Resp_ID));
	}

	/** Get Responsible Address Area.
		@return Responsible Address Area
	  */
	public int getEXME_Delegacion_D_Resp_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Delegacion_D_Resp_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Permanent Zone (Delegation).
		@param EXME_Delegacion_Perm_ID 
		Permanent Zone (Delegation)
	  */
	public void setEXME_Delegacion_Perm_ID (int EXME_Delegacion_Perm_ID)
	{
		if (EXME_Delegacion_Perm_ID < 1) 
			set_Value (COLUMNNAME_EXME_Delegacion_Perm_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_Delegacion_Perm_ID, Integer.valueOf(EXME_Delegacion_Perm_ID));
	}

	/** Get Permanent Zone (Delegation).
		@return Permanent Zone (Delegation)
	  */
	public int getEXME_Delegacion_Perm_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Delegacion_Perm_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Office Branch Prov Add..
		@param EXME_Delegacion_P_Prov_ID 
		Office Branch prov Add.
	  */
	public void setEXME_Delegacion_P_Prov_ID (int EXME_Delegacion_P_Prov_ID)
	{
		if (EXME_Delegacion_P_Prov_ID < 1) 
			set_Value (COLUMNNAME_EXME_Delegacion_P_Prov_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_Delegacion_P_Prov_ID, Integer.valueOf(EXME_Delegacion_P_Prov_ID));
	}

	/** Get Office Branch Prov Add..
		@return Office Branch prov Add.
	  */
	public int getEXME_Delegacion_P_Prov_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Delegacion_P_Prov_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Worker Add Off Branch..
		@param EXME_Delegacion_P_Trab_ID 
		Worker Add Off Branch
	  */
	public void setEXME_Delegacion_P_Trab_ID (int EXME_Delegacion_P_Trab_ID)
	{
		if (EXME_Delegacion_P_Trab_ID < 1) 
			set_Value (COLUMNNAME_EXME_Delegacion_P_Trab_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_Delegacion_P_Trab_ID, Integer.valueOf(EXME_Delegacion_P_Trab_ID));
	}

	/** Get Worker Add Off Branch..
		@return Worker Add Off Branch
	  */
	public int getEXME_Delegacion_P_Trab_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Delegacion_P_Trab_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Resp Worker Add Off Branch..
		@param EXME_Delegacion_T_Resp_ID 
		Resp Worker Add off Branch.
	  */
	public void setEXME_Delegacion_T_Resp_ID (int EXME_Delegacion_T_Resp_ID)
	{
		if (EXME_Delegacion_T_Resp_ID < 1) 
			set_Value (COLUMNNAME_EXME_Delegacion_T_Resp_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_Delegacion_T_Resp_ID, Integer.valueOf(EXME_Delegacion_T_Resp_ID));
	}

	/** Get Resp Worker Add Off Branch..
		@return Resp Worker Add off Branch.
	  */
	public int getEXME_Delegacion_T_Resp_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Delegacion_T_Resp_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Schooling.
		@param EXME_Escolaridad_ID 
		Schooling
	  */
	public void setEXME_Escolaridad_ID (int EXME_Escolaridad_ID)
	{
		if (EXME_Escolaridad_ID < 1)
			 throw new IllegalArgumentException ("EXME_Escolaridad_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_Escolaridad_ID, Integer.valueOf(EXME_Escolaridad_ID));
	}

	/** Get Schooling.
		@return Schooling
	  */
	public int getEXME_Escolaridad_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Escolaridad_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Schooling of Mother.
		@param EXME_Escolaridad_Madre_ID 
		Schooling of Mother
	  */
	public void setEXME_Escolaridad_Madre_ID (int EXME_Escolaridad_Madre_ID)
	{
		if (EXME_Escolaridad_Madre_ID < 1) 
			set_Value (COLUMNNAME_EXME_Escolaridad_Madre_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_Escolaridad_Madre_ID, Integer.valueOf(EXME_Escolaridad_Madre_ID));
	}

	/** Get Schooling of Mother.
		@return Schooling of Mother
	  */
	public int getEXME_Escolaridad_Madre_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Escolaridad_Madre_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Father's Schooling.
		@param EXME_Escolaridad_Padre_ID 
		Father's Schooling
	  */
	public void setEXME_Escolaridad_Padre_ID (int EXME_Escolaridad_Padre_ID)
	{
		if (EXME_Escolaridad_Padre_ID < 1) 
			set_Value (COLUMNNAME_EXME_Escolaridad_Padre_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_Escolaridad_Padre_ID, Integer.valueOf(EXME_Escolaridad_Padre_ID));
	}

	/** Get Father's Schooling.
		@return Father's Schooling
	  */
	public int getEXME_Escolaridad_Padre_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Escolaridad_Padre_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Social Work Specialty.
		@param EXME_Especialidad_TS_ID 
		Social Work Specialty
	  */
	public void setEXME_Especialidad_TS_ID (int EXME_Especialidad_TS_ID)
	{
		if (EXME_Especialidad_TS_ID < 1)
			 throw new IllegalArgumentException ("EXME_Especialidad_TS_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_Especialidad_TS_ID, Integer.valueOf(EXME_Especialidad_TS_ID));
	}

	/** Get Social Work Specialty.
		@return Social Work Specialty
	  */
	public int getEXME_Especialidad_TS_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Especialidad_TS_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Doctor.
		@param EXME_Medico_Aper_ID Doctor	  */
	public void setEXME_Medico_Aper_ID (int EXME_Medico_Aper_ID)
	{
		if (EXME_Medico_Aper_ID < 1) 
			set_Value (COLUMNNAME_EXME_Medico_Aper_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_Medico_Aper_ID, Integer.valueOf(EXME_Medico_Aper_ID));
	}

	/** Get Doctor.
		@return Doctor	  */
	public int getEXME_Medico_Aper_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Medico_Aper_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Nationality.
		@param EXME_Nacionalidad_ID 
		Nationality
	  */
	public void setEXME_Nacionalidad_ID (int EXME_Nacionalidad_ID)
	{
		if (EXME_Nacionalidad_ID < 1)
			 throw new IllegalArgumentException ("EXME_Nacionalidad_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_Nacionalidad_ID, Integer.valueOf(EXME_Nacionalidad_ID));
	}

	/** Get Nationality.
		@return Nationality
	  */
	public int getEXME_Nacionalidad_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Nacionalidad_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Ocupation.
		@param EXME_Ocupacion_Resp_ID 
		Ocupation
	  */
	public void setEXME_Ocupacion_Resp_ID (int EXME_Ocupacion_Resp_ID)
	{
		if (EXME_Ocupacion_Resp_ID < 1) 
			set_Value (COLUMNNAME_EXME_Ocupacion_Resp_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_Ocupacion_Resp_ID, Integer.valueOf(EXME_Ocupacion_Resp_ID));
	}

	/** Get Ocupation.
		@return Ocupation
	  */
	public int getEXME_Ocupacion_Resp_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Ocupacion_Resp_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Other Institution.
		@param EXME_Otra_Inst 
		Other Institution
	  */
	public void setEXME_Otra_Inst (String EXME_Otra_Inst)
	{
		set_Value (COLUMNNAME_EXME_Otra_Inst, EXME_Otra_Inst);
	}

	/** Get Other Institution.
		@return Other Institution
	  */
	public String getEXME_Otra_Inst () 
	{
		return (String)get_Value(COLUMNNAME_EXME_Otra_Inst);
	}

	public I_EXME_Paciente getEXME_Paciente() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_Paciente.Table_Name);
        I_EXME_Paciente result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_Paciente)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Paciente_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Patient.
		@param EXME_Paciente_ID 
		Patient
	  */
	public void setEXME_Paciente_ID (int EXME_Paciente_ID)
	{
		if (EXME_Paciente_ID < 1)
			 throw new IllegalArgumentException ("EXME_Paciente_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_Paciente_ID, Integer.valueOf(EXME_Paciente_ID));
	}

	/** Get Patient.
		@return Patient
	  */
	public int getEXME_Paciente_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Paciente_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Patient (social work).
		@param EXME_Paciente_TS_ID 
		Social Work's Patient
	  */
	public void setEXME_Paciente_TS_ID (int EXME_Paciente_TS_ID)
	{
		if (EXME_Paciente_TS_ID < 1)
			 throw new IllegalArgumentException ("EXME_Paciente_TS_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_Paciente_TS_ID, Integer.valueOf(EXME_Paciente_TS_ID));
	}

	/** Get Patient (social work).
		@return Social Work's Patient
	  */
	public int getEXME_Paciente_TS_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Paciente_TS_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Responsible Person kinship.
		@param EXME_Parentesco_Resp_ID 
		Responsible Person kinship
	  */
	public void setEXME_Parentesco_Resp_ID (int EXME_Parentesco_Resp_ID)
	{
		if (EXME_Parentesco_Resp_ID < 1) 
			set_Value (COLUMNNAME_EXME_Parentesco_Resp_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_Parentesco_Resp_ID, Integer.valueOf(EXME_Parentesco_Resp_ID));
	}

	/** Get Responsible Person kinship.
		@return Responsible Person kinship
	  */
	public int getEXME_Parentesco_Resp_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Parentesco_Resp_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Patient Reference.
		@param EXME_Referencia_ID 
		Reference to which the patient belongs.
	  */
	public void setEXME_Referencia_ID (int EXME_Referencia_ID)
	{
		if (EXME_Referencia_ID < 1)
			 throw new IllegalArgumentException ("EXME_Referencia_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_Referencia_ID, Integer.valueOf(EXME_Referencia_ID));
	}

	/** Get Patient Reference.
		@return Reference to which the patient belongs.
	  */
	public int getEXME_Referencia_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Referencia_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Internal Reference.
		@param EXME_Referencia_Int_ID 
		Patient's Internal Reference
	  */
	public void setEXME_Referencia_Int_ID (int EXME_Referencia_Int_ID)
	{
		if (EXME_Referencia_Int_ID < 1)
			 throw new IllegalArgumentException ("EXME_Referencia_Int_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_Referencia_Int_ID, Integer.valueOf(EXME_Referencia_Int_ID));
	}

	/** Get Internal Reference.
		@return Patient's Internal Reference
	  */
	public int getEXME_Referencia_Int_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Referencia_Int_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Institucion AD_Reference_ID=1200126 */
	public static final int INSTITUCION_AD_Reference_ID=1200126;
	/** IMSS = IM */
	public static final String INSTITUCION_IMSS = "IM";
	/** ISSSTE = IS */
	public static final String INSTITUCION_ISSSTE = "IS";
	/** None = NI */
	public static final String INSTITUCION_None = "NI";
	/** Others = OT */
	public static final String INSTITUCION_Others = "OT";
	/** Set Institution.
		@param Institucion Institution	  */
	public void setInstitucion (String Institucion)
	{

		if (Institucion == null || Institucion.equals("IM") || Institucion.equals("IS") || Institucion.equals("NI") || Institucion.equals("OT")); else throw new IllegalArgumentException ("Institucion Invalid value - " + Institucion + " - Reference_ID=1200126 - IM - IS - NI - OT");		set_Value (COLUMNNAME_Institucion, Institucion);
	}

	/** Get Institution.
		@return Institution	  */
	public String getInstitucion () 
	{
		return (String)get_Value(COLUMNNAME_Institucion);
	}

	/** Set ListaInstitucion.
		@param ListaInstitucion ListaInstitucion	  */
	public void setListaInstitucion (String ListaInstitucion)
	{
		set_Value (COLUMNNAME_ListaInstitucion, ListaInstitucion);
	}

	/** Get ListaInstitucion.
		@return ListaInstitucion	  */
	public String getListaInstitucion () 
	{
		return (String)get_Value(COLUMNNAME_ListaInstitucion);
	}

	/** Set Unknown mother.
		@param Madre_Desco 
		Unknown mother
	  */
	public void setMadre_Desco (boolean Madre_Desco)
	{
		set_Value (COLUMNNAME_Madre_Desco, Boolean.valueOf(Madre_Desco));
	}

	/** Get Unknown mother.
		@return Unknown mother
	  */
	public boolean isMadre_Desco () 
	{
		Object oo = get_Value(COLUMNNAME_Madre_Desco);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Mother Lives.
		@param Madre_Vive 
		Mother Lives
	  */
	public void setMadre_Vive (boolean Madre_Vive)
	{
		set_Value (COLUMNNAME_Madre_Vive, Boolean.valueOf(Madre_Vive));
	}

	/** Get Mother Lives.
		@return Mother Lives
	  */
	public boolean isMadre_Vive () 
	{
		Object oo = get_Value(COLUMNNAME_Madre_Vive);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Name of Responsible.
		@param Nombre_Resp Name of Responsible	  */
	public void setNombre_Resp (String Nombre_Resp)
	{
		set_Value (COLUMNNAME_Nombre_Resp, Nombre_Resp);
	}

	/** Get Name of Responsible.
		@return Name of Responsible	  */
	public String getNombre_Resp () 
	{
		return (String)get_Value(COLUMNNAME_Nombre_Resp);
	}

	/** Set Spouse Name.
		@param Nom_Conyuge 
		Spouse Name
	  */
	public void setNom_Conyuge (String Nom_Conyuge)
	{
		set_Value (COLUMNNAME_Nom_Conyuge, Nom_Conyuge);
	}

	/** Get Spouse Name.
		@return Spouse Name
	  */
	public String getNom_Conyuge () 
	{
		return (String)get_Value(COLUMNNAME_Nom_Conyuge);
	}

	/** Set Mother's Name.
		@param Nom_Madre 
		Mother's Name
	  */
	public void setNom_Madre (String Nom_Madre)
	{
		set_Value (COLUMNNAME_Nom_Madre, Nom_Madre);
	}

	/** Get Mother's Name.
		@return Mother's Name
	  */
	public String getNom_Madre () 
	{
		return (String)get_Value(COLUMNNAME_Nom_Madre);
	}

	/** Set Father's Name.
		@param Nom_Padre 
		Father's Name
	  */
	public void setNom_Padre (String Nom_Padre)
	{
		set_Value (COLUMNNAME_Nom_Padre, Nom_Padre);
	}

	/** Get Father's Name.
		@return Father's Name
	  */
	public String getNom_Padre () 
	{
		return (String)get_Value(COLUMNNAME_Nom_Padre);
	}

	/** Set Unknown Fatner.
		@param Padre_Desco 
		Unknown Father
	  */
	public void setPadre_Desco (boolean Padre_Desco)
	{
		set_Value (COLUMNNAME_Padre_Desco, Boolean.valueOf(Padre_Desco));
	}

	/** Get Unknown Fatner.
		@return Unknown Father
	  */
	public boolean isPadre_Desco () 
	{
		Object oo = get_Value(COLUMNNAME_Padre_Desco);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Alive Father.
		@param Padre_Vive 
		Alive Father
	  */
	public void setPadre_Vive (boolean Padre_Vive)
	{
		set_Value (COLUMNNAME_Padre_Vive, Boolean.valueOf(Padre_Vive));
	}

	/** Get Alive Father.
		@return Alive Father
	  */
	public boolean isPadre_Vive () 
	{
		Object oo = get_Value(COLUMNNAME_Padre_Vive);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Tel. Dom. Resp..
		@param Tel_Dom_Resp 
		Tel. Dom. Resp.
	  */
	public void setTel_Dom_Resp (String Tel_Dom_Resp)
	{
		set_Value (COLUMNNAME_Tel_Dom_Resp, Tel_Dom_Resp);
	}

	/** Get Tel. Dom. Resp..
		@return Tel. Dom. Resp.
	  */
	public String getTel_Dom_Resp () 
	{
		return (String)get_Value(COLUMNNAME_Tel_Dom_Resp);
	}

	/** Set Pat  Phone Prov..
		@param Tel_Pac_Prov 
		Pat  Phone Prov
	  */
	public void setTel_Pac_Prov (String Tel_Pac_Prov)
	{
		set_Value (COLUMNNAME_Tel_Pac_Prov, Tel_Pac_Prov);
	}

	/** Get Pat  Phone Prov..
		@return Pat  Phone Prov
	  */
	public String getTel_Pac_Prov () 
	{
		return (String)get_Value(COLUMNNAME_Tel_Pac_Prov);
	}

	/** Set Work Pat  Phone.
		@param Tel_Pac_Trab 
		Work Pat  Phone
	  */
	public void setTel_Pac_Trab (String Tel_Pac_Trab)
	{
		set_Value (COLUMNNAME_Tel_Pac_Trab, Tel_Pac_Trab);
	}

	/** Get Work Pat  Phone.
		@return Work Pat  Phone
	  */
	public String getTel_Pac_Trab () 
	{
		return (String)get_Value(COLUMNNAME_Tel_Pac_Trab);
	}

	/** Set Permanent Phone Number.
		@param Tel_Perm Permanent Phone Number	  */
	public void setTel_Perm (String Tel_Perm)
	{
		set_Value (COLUMNNAME_Tel_Perm, Tel_Perm);
	}

	/** Get Permanent Phone Number.
		@return Permanent Phone Number	  */
	public String getTel_Perm () 
	{
		return (String)get_Value(COLUMNNAME_Tel_Perm);
	}

	/** Set Resp. Work Phone.
		@param Tel_Trab_Resp 
		Resp. Work Phone
	  */
	public void setTel_Trab_Resp (String Tel_Trab_Resp)
	{
		set_Value (COLUMNNAME_Tel_Trab_Resp, Tel_Trab_Resp);
	}

	/** Get Resp. Work Phone.
		@return Resp. Work Phone
	  */
	public String getTel_Trab_Resp () 
	{
		return (String)get_Value(COLUMNNAME_Tel_Trab_Resp);
	}
}