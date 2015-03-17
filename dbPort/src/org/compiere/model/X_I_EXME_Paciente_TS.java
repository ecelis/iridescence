/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

/** Generated Model for I_EXME_Paciente_TS
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_I_EXME_Paciente_TS extends PO implements I_I_EXME_Paciente_TS, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_I_EXME_Paciente_TS (Properties ctx, int I_EXME_Paciente_TS_ID, String trxName)
    {
      super (ctx, I_EXME_Paciente_TS_ID, trxName);
      /** if (I_EXME_Paciente_TS_ID == 0)
        {
			setI_EXME_Paciente_TS_ID (0);
        } */
    }

    /** Load Constructor */
    public X_I_EXME_Paciente_TS (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_I_EXME_Paciente_TS[")
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

	/** Set Adress Street of Responsible Person Value.
		@param Calle_Dom_Resp_Value 
		Adress Street of Responsible Person Value
	  */
	public void setCalle_Dom_Resp_Value (String Calle_Dom_Resp_Value)
	{
		set_Value (COLUMNNAME_Calle_Dom_Resp_Value, Calle_Dom_Resp_Value);
	}

	/** Get Adress Street of Responsible Person Value.
		@return Adress Street of Responsible Person Value
	  */
	public String getCalle_Dom_Resp_Value () 
	{
		return (String)get_Value(COLUMNNAME_Calle_Dom_Resp_Value);
	}

	/** Set Provisional Work Adress Street of Patient Value.
		@param Calle_Pac_Prov_Value 
		Provisional Work Adress Street of Patient Value
	  */
	public void setCalle_Pac_Prov_Value (String Calle_Pac_Prov_Value)
	{
		set_Value (COLUMNNAME_Calle_Pac_Prov_Value, Calle_Pac_Prov_Value);
	}

	/** Get Provisional Work Adress Street of Patient Value.
		@return Provisional Work Adress Street of Patient Value
	  */
	public String getCalle_Pac_Prov_Value () 
	{
		return (String)get_Value(COLUMNNAME_Calle_Pac_Prov_Value);
	}

	/** Set Work Adress Street of Patient Value.
		@param Calle_Pac_Trab_Value 
		Work Adress Street of Patient Value
	  */
	public void setCalle_Pac_Trab_Value (String Calle_Pac_Trab_Value)
	{
		set_Value (COLUMNNAME_Calle_Pac_Trab_Value, Calle_Pac_Trab_Value);
	}

	/** Get Work Adress Street of Patient Value.
		@return Work Adress Street of Patient Value
	  */
	public String getCalle_Pac_Trab_Value () 
	{
		return (String)get_Value(COLUMNNAME_Calle_Pac_Trab_Value);
	}

	/** Set Permanent Adress Street of Patient Value.
		@param Calle_Perm_Value 
		Permanent Adress Street of Patient Value
	  */
	public void setCalle_Perm_Value (String Calle_Perm_Value)
	{
		set_Value (COLUMNNAME_Calle_Perm_Value, Calle_Perm_Value);
	}

	/** Get Permanent Adress Street of Patient Value.
		@return Permanent Adress Street of Patient Value
	  */
	public String getCalle_Perm_Value () 
	{
		return (String)get_Value(COLUMNNAME_Calle_Perm_Value);
	}

	/** Set Work Adress Street of Patient's Responsible Person Value.
		@param Calle_Trab_Resp_Value 
		Work Adress Street of Patient's Responsible Person Value
	  */
	public void setCalle_Trab_Resp_Value (String Calle_Trab_Resp_Value)
	{
		set_Value (COLUMNNAME_Calle_Trab_Resp_Value, Calle_Trab_Resp_Value);
	}

	/** Get Work Adress Street of Patient's Responsible Person Value.
		@return Work Adress Street of Patient's Responsible Person Value
	  */
	public String getCalle_Trab_Resp_Value () 
	{
		return (String)get_Value(COLUMNNAME_Calle_Trab_Resp_Value);
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

	/** Set Address Colony Of Responsible Person Value.
		@param Col_Dom_Resp_Value 
		Address Colony Of Responsible Person Value
	  */
	public void setCol_Dom_Resp_Value (String Col_Dom_Resp_Value)
	{
		set_Value (COLUMNNAME_Col_Dom_Resp_Value, Col_Dom_Resp_Value);
	}

	/** Get Address Colony Of Responsible Person Value.
		@return Address Colony Of Responsible Person Value
	  */
	public String getCol_Dom_Resp_Value () 
	{
		return (String)get_Value(COLUMNNAME_Col_Dom_Resp_Value);
	}

	/** Set Address Colony of the Patient Value.
		@param Col_Pac_Prov_Value 
		Address Colony of the Patient Value
	  */
	public void setCol_Pac_Prov_Value (String Col_Pac_Prov_Value)
	{
		set_Value (COLUMNNAME_Col_Pac_Prov_Value, Col_Pac_Prov_Value);
	}

	/** Get Address Colony of the Patient Value.
		@return Address Colony of the Patient Value
	  */
	public String getCol_Pac_Prov_Value () 
	{
		return (String)get_Value(COLUMNNAME_Col_Pac_Prov_Value);
	}

	/** Set Work Address Colony of the Patient Value.
		@param Col_Pac_Trab_Value 
		Work Address Colony of the Patient Value
	  */
	public void setCol_Pac_Trab_Value (String Col_Pac_Trab_Value)
	{
		set_Value (COLUMNNAME_Col_Pac_Trab_Value, Col_Pac_Trab_Value);
	}

	/** Get Work Address Colony of the Patient Value.
		@return Work Address Colony of the Patient Value
	  */
	public String getCol_Pac_Trab_Value () 
	{
		return (String)get_Value(COLUMNNAME_Col_Pac_Trab_Value);
	}

	/** Set Permanent Address Colony Value.
		@param Col_Perm_Value 
		Permanent Address Colony Value
	  */
	public void setCol_Perm_Value (String Col_Perm_Value)
	{
		set_Value (COLUMNNAME_Col_Perm_Value, Col_Perm_Value);
	}

	/** Get Permanent Address Colony Value.
		@return Permanent Address Colony Value
	  */
	public String getCol_Perm_Value () 
	{
		return (String)get_Value(COLUMNNAME_Col_Perm_Value);
	}

	/** Set Work address colony of the Responsible person Value.
		@param Col_Trab_Resp_Value 
		Work address colony of the Responsible person Value
	  */
	public void setCol_Trab_Resp_Value (String Col_Trab_Resp_Value)
	{
		set_Value (COLUMNNAME_Col_Trab_Resp_Value, Col_Trab_Resp_Value);
	}

	/** Get Work address colony of the Responsible person Value.
		@return Work address colony of the Responsible person Value
	  */
	public String getCol_Trab_Resp_Value () 
	{
		return (String)get_Value(COLUMNNAME_Col_Trab_Resp_Value);
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
	public void setCopyAddr (boolean CopyAddr)
	{
		set_Value (COLUMNNAME_CopyAddr, Boolean.valueOf(CopyAddr));
	}

	/** Get Copy Address to Responsible.
		@return Copy Address to Responsible
	  */
	public boolean isCopyAddr () 
	{
		Object oo = get_Value(COLUMNNAME_CopyAddr);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Responsible person's postal code of home address_Value.
		@param CP_Dom_Resp_Value 
		Responsible person's postal code of home address_Value
	  */
	public void setCP_Dom_Resp_Value (String CP_Dom_Resp_Value)
	{
		set_Value (COLUMNNAME_CP_Dom_Resp_Value, CP_Dom_Resp_Value);
	}

	/** Get Responsible person's postal code of home address_Value.
		@return Responsible person's postal code of home address_Value
	  */
	public String getCP_Dom_Resp_Value () 
	{
		return (String)get_Value(COLUMNNAME_CP_Dom_Resp_Value);
	}

	/** Set Patient's Provisional Postal Code.
		@param CP_Pac_Prov_Value 
		Patient's Provisional Postal Code
	  */
	public void setCP_Pac_Prov_Value (String CP_Pac_Prov_Value)
	{
		set_Value (COLUMNNAME_CP_Pac_Prov_Value, CP_Pac_Prov_Value);
	}

	/** Get Patient's Provisional Postal Code.
		@return Patient's Provisional Postal Code
	  */
	public String getCP_Pac_Prov_Value () 
	{
		return (String)get_Value(COLUMNNAME_CP_Pac_Prov_Value);
	}

	/** Set Patient's work postal code_Value.
		@param CP_Pac_Trab_Value 
		Patient's work postal code_Value
	  */
	public void setCP_Pac_Trab_Value (String CP_Pac_Trab_Value)
	{
		set_Value (COLUMNNAME_CP_Pac_Trab_Value, CP_Pac_Trab_Value);
	}

	/** Get Patient's work postal code_Value.
		@return Patient's work postal code_Value
	  */
	public String getCP_Pac_Trab_Value () 
	{
		return (String)get_Value(COLUMNNAME_CP_Pac_Trab_Value);
	}

	/** Set CP_Procedencia_Value.
		@param CP_Procedencia_Value 
		Provenance Postal Code_Value
	  */
	public void setCP_Procedencia_Value (String CP_Procedencia_Value)
	{
		set_Value (COLUMNNAME_CP_Procedencia_Value, CP_Procedencia_Value);
	}

	/** Get CP_Procedencia_Value.
		@return Provenance Postal Code_Value
	  */
	public String getCP_Procedencia_Value () 
	{
		return (String)get_Value(COLUMNNAME_CP_Procedencia_Value);
	}

	/** Set Work's postal code of responsible person_Value.
		@param CP_Trab_Resp_Value 
		Work's postal code of responsible person_Value
	  */
	public void setCP_Trab_Resp_Value (String CP_Trab_Resp_Value)
	{
		set_Value (COLUMNNAME_CP_Trab_Resp_Value, CP_Trab_Resp_Value);
	}

	/** Get Work's postal code of responsible person_Value.
		@return Work's postal code of responsible person_Value
	  */
	public String getCP_Trab_Resp_Value () 
	{
		return (String)get_Value(COLUMNNAME_CP_Trab_Resp_Value);
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

	/** Set State of Responsible Person_Value.
		@param Edo_Dom_Resp_Value 
		State of Responsible Person_Value
	  */
	public void setEdo_Dom_Resp_Value (String Edo_Dom_Resp_Value)
	{
		set_Value (COLUMNNAME_Edo_Dom_Resp_Value, Edo_Dom_Resp_Value);
	}

	/** Get State of Responsible Person_Value.
		@return State of Responsible Person_Value
	  */
	public String getEdo_Dom_Resp_Value () 
	{
		return (String)get_Value(COLUMNNAME_Edo_Dom_Resp_Value);
	}

	/** Set Patient Province_Value.
		@param Edo_Pac_Prov_Value 
		Patient Province_Value
	  */
	public void setEdo_Pac_Prov_Value (String Edo_Pac_Prov_Value)
	{
		set_Value (COLUMNNAME_Edo_Pac_Prov_Value, Edo_Pac_Prov_Value);
	}

	/** Get Patient Province_Value.
		@return Patient Province_Value
	  */
	public String getEdo_Pac_Prov_Value () 
	{
		return (String)get_Value(COLUMNNAME_Edo_Pac_Prov_Value);
	}

	/** Set Patient work's State.
		@param Edo_Pac_Trab_Value 
		Patient work's State
	  */
	public void setEdo_Pac_Trab_Value (String Edo_Pac_Trab_Value)
	{
		set_Value (COLUMNNAME_Edo_Pac_Trab_Value, Edo_Pac_Trab_Value);
	}

	/** Get Patient work's State.
		@return Patient work's State
	  */
	public String getEdo_Pac_Trab_Value () 
	{
		return (String)get_Value(COLUMNNAME_Edo_Pac_Trab_Value);
	}

	/** Set Permanent State_Value.
		@param Edo_Perm_Value 
		Permanent State_Value
	  */
	public void setEdo_Perm_Value (String Edo_Perm_Value)
	{
		set_Value (COLUMNNAME_Edo_Perm_Value, Edo_Perm_Value);
	}

	/** Get Permanent State_Value.
		@return Permanent State_Value
	  */
	public String getEdo_Perm_Value () 
	{
		return (String)get_Value(COLUMNNAME_Edo_Perm_Value);
	}

	/** Set Responsible Person work's State_Value.
		@param Edo_Trab_Resp_Value 
		Responsible Person work's State_Value
	  */
	public void setEdo_Trab_Resp_Value (String Edo_Trab_Resp_Value)
	{
		set_Value (COLUMNNAME_Edo_Trab_Resp_Value, Edo_Trab_Resp_Value);
	}

	/** Get Responsible Person work's State_Value.
		@return Responsible Person work's State_Value
	  */
	public String getEdo_Trab_Resp_Value () 
	{
		return (String)get_Value(COLUMNNAME_Edo_Trab_Resp_Value);
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

	/** Set EXME_Delegacion_D_Resp_Value.
		@param EXME_Delegacion_D_Resp_Value 
		EXME_Delegacion_D_Resp_Value
	  */
	public void setEXME_Delegacion_D_Resp_Value (String EXME_Delegacion_D_Resp_Value)
	{
		set_Value (COLUMNNAME_EXME_Delegacion_D_Resp_Value, EXME_Delegacion_D_Resp_Value);
	}

	/** Get EXME_Delegacion_D_Resp_Value.
		@return EXME_Delegacion_D_Resp_Value
	  */
	public String getEXME_Delegacion_D_Resp_Value () 
	{
		return (String)get_Value(COLUMNNAME_EXME_Delegacion_D_Resp_Value);
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

	/** Set EXME_Delegacion_Perm_Value.
		@param EXME_Delegacion_Perm_Value EXME_Delegacion_Perm_Value	  */
	public void setEXME_Delegacion_Perm_Value (String EXME_Delegacion_Perm_Value)
	{
		set_Value (COLUMNNAME_EXME_Delegacion_Perm_Value, EXME_Delegacion_Perm_Value);
	}

	/** Get EXME_Delegacion_Perm_Value.
		@return EXME_Delegacion_Perm_Value	  */
	public String getEXME_Delegacion_Perm_Value () 
	{
		return (String)get_Value(COLUMNNAME_EXME_Delegacion_Perm_Value);
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

	/** Set EXME_Delegacion_P_Prov_Value.
		@param EXME_Delegacion_P_Prov_Value 
		EXME_Delegacion_P_Prov_Value
	  */
	public void setEXME_Delegacion_P_Prov_Value (String EXME_Delegacion_P_Prov_Value)
	{
		set_Value (COLUMNNAME_EXME_Delegacion_P_Prov_Value, EXME_Delegacion_P_Prov_Value);
	}

	/** Get EXME_Delegacion_P_Prov_Value.
		@return EXME_Delegacion_P_Prov_Value
	  */
	public String getEXME_Delegacion_P_Prov_Value () 
	{
		return (String)get_Value(COLUMNNAME_EXME_Delegacion_P_Prov_Value);
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

	/** Set EXME_Delegacion_P_Trab_Value.
		@param EXME_Delegacion_P_Trab_Value EXME_Delegacion_P_Trab_Value	  */
	public void setEXME_Delegacion_P_Trab_Value (String EXME_Delegacion_P_Trab_Value)
	{
		set_Value (COLUMNNAME_EXME_Delegacion_P_Trab_Value, EXME_Delegacion_P_Trab_Value);
	}

	/** Get EXME_Delegacion_P_Trab_Value.
		@return EXME_Delegacion_P_Trab_Value	  */
	public String getEXME_Delegacion_P_Trab_Value () 
	{
		return (String)get_Value(COLUMNNAME_EXME_Delegacion_P_Trab_Value);
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

	/** Set EXME_Delegacion_T_Resp_Value.
		@param EXME_Delegacion_T_Resp_Value 
		EXME_Delegacion_T_Resp_Value
	  */
	public void setEXME_Delegacion_T_Resp_Value (String EXME_Delegacion_T_Resp_Value)
	{
		set_Value (COLUMNNAME_EXME_Delegacion_T_Resp_Value, EXME_Delegacion_T_Resp_Value);
	}

	/** Get EXME_Delegacion_T_Resp_Value.
		@return EXME_Delegacion_T_Resp_Value
	  */
	public String getEXME_Delegacion_T_Resp_Value () 
	{
		return (String)get_Value(COLUMNNAME_EXME_Delegacion_T_Resp_Value);
	}

	public I_EXME_Escolaridad getEXME_Escolaridad() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_Escolaridad.Table_Name);
        I_EXME_Escolaridad result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_Escolaridad)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Escolaridad_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Schooling.
		@param EXME_Escolaridad_ID 
		Schooling
	  */
	public void setEXME_Escolaridad_ID (int EXME_Escolaridad_ID)
	{
		if (EXME_Escolaridad_ID < 1) 
			set_Value (COLUMNNAME_EXME_Escolaridad_ID, null);
		else 
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

	/** Set Schooling.
		@param EXME_Escolaridad_Value 
		Schooling
	  */
	public void setEXME_Escolaridad_Value (String EXME_Escolaridad_Value)
	{
		set_Value (COLUMNNAME_EXME_Escolaridad_Value, EXME_Escolaridad_Value);
	}

	/** Get Schooling.
		@return Schooling
	  */
	public String getEXME_Escolaridad_Value () 
	{
		return (String)get_Value(COLUMNNAME_EXME_Escolaridad_Value);
	}

	/** Set Specialty.
		@param EXME_Especialidad 
		Specialty
	  */
	public void setEXME_Especialidad (String EXME_Especialidad)
	{
		set_Value (COLUMNNAME_EXME_Especialidad, EXME_Especialidad);
	}

	/** Get Specialty.
		@return Specialty
	  */
	public String getEXME_Especialidad () 
	{
		return (String)get_Value(COLUMNNAME_EXME_Especialidad);
	}

	public I_EXME_Especialidad_TS getEXME_Especialidad_TS() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_Especialidad_TS.Table_Name);
        I_EXME_Especialidad_TS result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_Especialidad_TS)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Especialidad_TS_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Social Work Specialty.
		@param EXME_Especialidad_TS_ID 
		Social Work Specialty
	  */
	public void setEXME_Especialidad_TS_ID (int EXME_Especialidad_TS_ID)
	{
		if (EXME_Especialidad_TS_ID < 1) 
			set_Value (COLUMNNAME_EXME_Especialidad_TS_ID, null);
		else 
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

	/** Set Speciality Value.
		@param EXME_Especialidad_Value Speciality Value	  */
	public void setEXME_Especialidad_Value (String EXME_Especialidad_Value)
	{
		set_Value (COLUMNNAME_EXME_Especialidad_Value, EXME_Especialidad_Value);
	}

	/** Get Speciality Value.
		@return Speciality Value	  */
	public String getEXME_Especialidad_Value () 
	{
		return (String)get_Value(COLUMNNAME_EXME_Especialidad_Value);
	}

	public I_EXME_Institucion getEXME_Institucion() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_Institucion.Table_Name);
        I_EXME_Institucion result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_Institucion)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Institucion_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Institution.
		@param EXME_Institucion_ID 
		Institution
	  */
	public void setEXME_Institucion_ID (int EXME_Institucion_ID)
	{
		if (EXME_Institucion_ID < 1) 
			set_Value (COLUMNNAME_EXME_Institucion_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_Institucion_ID, Integer.valueOf(EXME_Institucion_ID));
	}

	/** Get Institution.
		@return Institution
	  */
	public int getEXME_Institucion_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Institucion_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Institution Value.
		@param EXME_Institucion_Value 
		Institution
	  */
	public void setEXME_Institucion_Value (String EXME_Institucion_Value)
	{
		set_Value (COLUMNNAME_EXME_Institucion_Value, EXME_Institucion_Value);
	}

	/** Get Institution Value.
		@return Institution
	  */
	public String getEXME_Institucion_Value () 
	{
		return (String)get_Value(COLUMNNAME_EXME_Institucion_Value);
	}

	public I_EXME_Nacionalidad getEXME_Nacionalidad() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_Nacionalidad.Table_Name);
        I_EXME_Nacionalidad result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_Nacionalidad)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Nacionalidad_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Nationality.
		@param EXME_Nacionalidad_ID 
		Nationality
	  */
	public void setEXME_Nacionalidad_ID (int EXME_Nacionalidad_ID)
	{
		if (EXME_Nacionalidad_ID < 1) 
			set_Value (COLUMNNAME_EXME_Nacionalidad_ID, null);
		else 
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

	/** Set Nationality value.
		@param EXME_Nacionalidad_Value 
		Nationality value
	  */
	public void setEXME_Nacionalidad_Value (String EXME_Nacionalidad_Value)
	{
		set_Value (COLUMNNAME_EXME_Nacionalidad_Value, EXME_Nacionalidad_Value);
	}

	/** Get Nationality value.
		@return Nationality value
	  */
	public String getEXME_Nacionalidad_Value () 
	{
		return (String)get_Value(COLUMNNAME_EXME_Nacionalidad_Value);
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

	/** Set Responsible person's ocupation_Value.
		@param EXME_Ocupacion_Resp_Value 
		Responsible person's ocupation_Value
	  */
	public void setEXME_Ocupacion_Resp_Value (String EXME_Ocupacion_Resp_Value)
	{
		set_Value (COLUMNNAME_EXME_Ocupacion_Resp_Value, EXME_Ocupacion_Resp_Value);
	}

	/** Get Responsible person's ocupation_Value.
		@return Responsible person's ocupation_Value
	  */
	public String getEXME_Ocupacion_Resp_Value () 
	{
		return (String)get_Value(COLUMNNAME_EXME_Ocupacion_Resp_Value);
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
			set_Value (COLUMNNAME_EXME_Paciente_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_Paciente_ID, Integer.valueOf(EXME_Paciente_ID));
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

	public I_EXME_Paciente_TS getEXME_Paciente_TS() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_Paciente_TS.Table_Name);
        I_EXME_Paciente_TS result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_Paciente_TS)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Paciente_TS_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Patient (social work).
		@param EXME_Paciente_TS_ID 
		Social Work's Patient
	  */
	public void setEXME_Paciente_TS_ID (int EXME_Paciente_TS_ID)
	{
		if (EXME_Paciente_TS_ID < 1) 
			set_Value (COLUMNNAME_EXME_Paciente_TS_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_Paciente_TS_ID, Integer.valueOf(EXME_Paciente_TS_ID));
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

	/** Set Responsible Person kinship_Value.
		@param EXME_Parentesco_Resp_Value Responsible Person kinship_Value	  */
	public void setEXME_Parentesco_Resp_Value (String EXME_Parentesco_Resp_Value)
	{
		set_Value (COLUMNNAME_EXME_Parentesco_Resp_Value, EXME_Parentesco_Resp_Value);
	}

	/** Get Responsible Person kinship_Value.
		@return Responsible Person kinship_Value	  */
	public String getEXME_Parentesco_Resp_Value () 
	{
		return (String)get_Value(COLUMNNAME_EXME_Parentesco_Resp_Value);
	}

	public I_EXME_Referencia getEXME_Referencia() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_Referencia.Table_Name);
        I_EXME_Referencia result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_Referencia)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Referencia_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Patient Reference.
		@param EXME_Referencia_ID 
		Reference to which the patient belongs.
	  */
	public void setEXME_Referencia_ID (int EXME_Referencia_ID)
	{
		if (EXME_Referencia_ID < 1) 
			set_Value (COLUMNNAME_EXME_Referencia_ID, null);
		else 
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

	public I_EXME_Referencia_Int getEXME_Referencia_Int() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_Referencia_Int.Table_Name);
        I_EXME_Referencia_Int result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_Referencia_Int)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Referencia_Int_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Internal Reference.
		@param EXME_Referencia_Int_ID 
		Patient's Internal Reference
	  */
	public void setEXME_Referencia_Int_ID (int EXME_Referencia_Int_ID)
	{
		if (EXME_Referencia_Int_ID < 1) 
			set_Value (COLUMNNAME_EXME_Referencia_Int_ID, null);
		else 
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

	/** Set Internal Reference_Value.
		@param EXME_Referencia_Int_Value 
		Patient's Internal Reference_Value
	  */
	public void setEXME_Referencia_Int_Value (String EXME_Referencia_Int_Value)
	{
		set_Value (COLUMNNAME_EXME_Referencia_Int_Value, EXME_Referencia_Int_Value);
	}

	/** Get Internal Reference_Value.
		@return Patient's Internal Reference_Value
	  */
	public String getEXME_Referencia_Int_Value () 
	{
		return (String)get_Value(COLUMNNAME_EXME_Referencia_Int_Value);
	}

	/** Set Reference_Value.
		@param EXME_Referencia_Value Reference_Value	  */
	public void setEXME_Referencia_Value (String EXME_Referencia_Value)
	{
		set_Value (COLUMNNAME_EXME_Referencia_Value, EXME_Referencia_Value);
	}

	/** Get Reference_Value.
		@return Reference_Value	  */
	public String getEXME_Referencia_Value () 
	{
		return (String)get_Value(COLUMNNAME_EXME_Referencia_Value);
	}

	/** Set Import Error Message.
		@param I_ErrorMsg 
		Messages generated from import process
	  */
	public void setI_ErrorMsg (String I_ErrorMsg)
	{
		set_Value (COLUMNNAME_I_ErrorMsg, I_ErrorMsg);
	}

	/** Get Import Error Message.
		@return Messages generated from import process
	  */
	public String getI_ErrorMsg () 
	{
		return (String)get_Value(COLUMNNAME_I_ErrorMsg);
	}

	/** Set I_EXME_Paciente_TS_ID.
		@param I_EXME_Paciente_TS_ID I_EXME_Paciente_TS_ID	  */
	public void setI_EXME_Paciente_TS_ID (int I_EXME_Paciente_TS_ID)
	{
		if (I_EXME_Paciente_TS_ID < 1)
			 throw new IllegalArgumentException ("I_EXME_Paciente_TS_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_I_EXME_Paciente_TS_ID, Integer.valueOf(I_EXME_Paciente_TS_ID));
	}

	/** Get I_EXME_Paciente_TS_ID.
		@return I_EXME_Paciente_TS_ID	  */
	public int getI_EXME_Paciente_TS_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_I_EXME_Paciente_TS_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Imported.
		@param I_IsImported 
		Has this import been processed
	  */
	public void setI_IsImported (boolean I_IsImported)
	{
		set_Value (COLUMNNAME_I_IsImported, Boolean.valueOf(I_IsImported));
	}

	/** Get Imported.
		@return Has this import been processed
	  */
	public boolean isI_IsImported () 
	{
		Object oo = get_Value(COLUMNNAME_I_IsImported);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
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

	/** Set Responsible Person's Hometown.
		@param Mpio_Dom_Resp_Value 
		Responsible Person's Hometown
	  */
	public void setMpio_Dom_Resp_Value (String Mpio_Dom_Resp_Value)
	{
		set_Value (COLUMNNAME_Mpio_Dom_Resp_Value, Mpio_Dom_Resp_Value);
	}

	/** Get Responsible Person's Hometown.
		@return Responsible Person's Hometown
	  */
	public String getMpio_Dom_Resp_Value () 
	{
		return (String)get_Value(COLUMNNAME_Mpio_Dom_Resp_Value);
	}

	/** Set Patient's provisional municipality.
		@param Mpio_Pac_Prov_Value 
		Patient's provisional municipality
	  */
	public void setMpio_Pac_Prov_Value (String Mpio_Pac_Prov_Value)
	{
		set_Value (COLUMNNAME_Mpio_Pac_Prov_Value, Mpio_Pac_Prov_Value);
	}

	/** Get Patient's provisional municipality.
		@return Patient's provisional municipality
	  */
	public String getMpio_Pac_Prov_Value () 
	{
		return (String)get_Value(COLUMNNAME_Mpio_Pac_Prov_Value);
	}

	/** Set Patient's work municipality.
		@param Mpio_Pac_Trab_Value 
		Patient's work municipality
	  */
	public void setMpio_Pac_Trab_Value (String Mpio_Pac_Trab_Value)
	{
		set_Value (COLUMNNAME_Mpio_Pac_Trab_Value, Mpio_Pac_Trab_Value);
	}

	/** Get Patient's work municipality.
		@return Patient's work municipality
	  */
	public String getMpio_Pac_Trab_Value () 
	{
		return (String)get_Value(COLUMNNAME_Mpio_Pac_Trab_Value);
	}

	/** Set Patient's Permanent municipality..
		@param Mpio_Perm_Value 
		Patient's Permanent municipality.
	  */
	public void setMpio_Perm_Value (String Mpio_Perm_Value)
	{
		set_Value (COLUMNNAME_Mpio_Perm_Value, Mpio_Perm_Value);
	}

	/** Get Patient's Permanent municipality..
		@return Patient's Permanent municipality.
	  */
	public String getMpio_Perm_Value () 
	{
		return (String)get_Value(COLUMNNAME_Mpio_Perm_Value);
	}

	/** Set Responsible Person's Work Municipality..
		@param Mpio_Trab_Resp_Value 
		Responsible Person's Work Municipality.
	  */
	public void setMpio_Trab_Resp_Value (String Mpio_Trab_Resp_Value)
	{
		set_Value (COLUMNNAME_Mpio_Trab_Resp_Value, Mpio_Trab_Resp_Value);
	}

	/** Get Responsible Person's Work Municipality..
		@return Responsible Person's Work Municipality.
	  */
	public String getMpio_Trab_Resp_Value () 
	{
		return (String)get_Value(COLUMNNAME_Mpio_Trab_Resp_Value);
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

	/** Set History Number.
		@param NumHist History Number	  */
	public void setNumHist (String NumHist)
	{
		set_Value (COLUMNNAME_NumHist, NumHist);
	}

	/** Get History Number.
		@return History Number	  */
	public String getNumHist () 
	{
		return (String)get_Value(COLUMNNAME_NumHist);
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

	/** Set Processed.
		@param Processed 
		The document has been processed
	  */
	public void setProcessed (boolean Processed)
	{
		set_Value (COLUMNNAME_Processed, Boolean.valueOf(Processed));
	}

	/** Get Processed.
		@return The document has been processed
	  */
	public boolean isProcessed () 
	{
		Object oo = get_Value(COLUMNNAME_Processed);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
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