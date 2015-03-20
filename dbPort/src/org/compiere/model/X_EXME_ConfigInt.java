/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;

/** Generated Model for EXME_ConfigInt
 *  @author Generated Class 
 *  @version Version @VERSION@ - $Id$ */
public class X_EXME_ConfigInt extends PO implements I_EXME_ConfigInt, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_ConfigInt (Properties ctx, int EXME_ConfigInt_ID, String trxName)
    {
      super (ctx, EXME_ConfigInt_ID, trxName);
      /** if (EXME_ConfigInt_ID == 0)
        {
			setEXME_ConfigInt_ID (0);
			setInterfase_Cardiologia (false);
			setInterfase_Equipos (false);
			setInterfase_Estadistica (false);
			setInterfase_Grifols (false);
			setInterfase_Inventarios (null);
// N
			setInterfase_Laboratorio (false);
			setInterfase_Siemens (false);
			setInterfase_Telemedicina (false);
			setLoincRequired (false);
			setSyngo_Param (null);
			setSyngo_ParamValue (null);
        } */
    }

    /** Load Constructor */
    public X_EXME_ConfigInt (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_ConfigInt[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Interface Configuration.
		@param EXME_ConfigInt_ID Interface Configuration	  */
	public void setEXME_ConfigInt_ID (int EXME_ConfigInt_ID)
	{
		if (EXME_ConfigInt_ID < 1)
			 throw new IllegalArgumentException ("EXME_ConfigInt_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_ConfigInt_ID, Integer.valueOf(EXME_ConfigInt_ID));
	}

	/** Get Interface Configuration.
		@return Interface Configuration	  */
	public int getEXME_ConfigInt_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_ConfigInt_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set GE Interface Centricity WebSite.
		@param GE_OpenAPI GE Interface Centricity WebSite	  */
	public void setGE_OpenAPI (String GE_OpenAPI)
	{
		set_Value (COLUMNNAME_GE_OpenAPI, GE_OpenAPI);
	}

	/** Get GE Interface Centricity WebSite.
		@return GE Interface Centricity WebSite	  */
	public String getGE_OpenAPI () 
	{
		return (String)get_Value(COLUMNNAME_GE_OpenAPI);
	}

	/** Set Grifols Warehouse.
		@param Grifols_Warehouse_ID 
		Delivery Warehouse for external charges
	  */
	public void setGrifols_Warehouse_ID (int Grifols_Warehouse_ID)
	{
		if (Grifols_Warehouse_ID < 1) 
			set_Value (COLUMNNAME_Grifols_Warehouse_ID, null);
		else 
			set_Value (COLUMNNAME_Grifols_Warehouse_ID, Integer.valueOf(Grifols_Warehouse_ID));
	}

	/** Get Grifols Warehouse.
		@return Delivery Warehouse for external charges
	  */
	public int getGrifols_Warehouse_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Grifols_Warehouse_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Cardiology Interfase.
		@param Interfase_Cardiologia Cardiology Interfase	  */
	public void setInterfase_Cardiologia (boolean Interfase_Cardiologia)
	{
		set_Value (COLUMNNAME_Interfase_Cardiologia, Boolean.valueOf(Interfase_Cardiologia));
	}

	/** Get Cardiology Interfase.
		@return Cardiology Interfase	  */
	public boolean isInterfase_Cardiologia () 
	{
		Object oo = get_Value(COLUMNNAME_Interfase_Cardiologia);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Cardiology Interfase Rout.
		@param Interfase_Cardiologia_R Cardiology Interfase Rout	  */
	public void setInterfase_Cardiologia_R (String Interfase_Cardiologia_R)
	{
		set_Value (COLUMNNAME_Interfase_Cardiologia_R, Interfase_Cardiologia_R);
	}

	/** Get Cardiology Interfase Rout.
		@return Cardiology Interfase Rout	  */
	public String getInterfase_Cardiologia_R () 
	{
		return (String)get_Value(COLUMNNAME_Interfase_Cardiologia_R);
	}

	/** Set Medical Beneficiaries Interface.
		@param Interfase_DGI Medical Beneficiaries Interface	  */
	public void setInterfase_DGI (boolean Interfase_DGI)
	{
		set_Value (COLUMNNAME_Interfase_DGI, Boolean.valueOf(Interfase_DGI));
	}

	/** Get Medical Beneficiaries Interface.
		@return Medical Beneficiaries Interface	  */
	public boolean isInterfase_DGI () 
	{
		Object oo = get_Value(COLUMNNAME_Interfase_DGI);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Interfase_Eleg.
		@param Interfase_Eleg Interfase_Eleg	  */
	public void setInterfase_Eleg (boolean Interfase_Eleg)
	{
		set_Value (COLUMNNAME_Interfase_Eleg, Boolean.valueOf(Interfase_Eleg));
	}

	/** Get Interfase_Eleg.
		@return Interfase_Eleg	  */
	public boolean isInterfase_Eleg () 
	{
		Object oo = get_Value(COLUMNNAME_Interfase_Eleg);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Interfase_Equipos.
		@param Interfase_Equipos Interfase_Equipos	  */
	public void setInterfase_Equipos (boolean Interfase_Equipos)
	{
		set_Value (COLUMNNAME_Interfase_Equipos, Boolean.valueOf(Interfase_Equipos));
	}

	/** Get Interfase_Equipos.
		@return Interfase_Equipos	  */
	public boolean isInterfase_Equipos () 
	{
		Object oo = get_Value(COLUMNNAME_Interfase_Equipos);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Equipment Interfase Rout.
		@param Interfase_Equipos_R Equipment Interfase Rout	  */
	public void setInterfase_Equipos_R (String Interfase_Equipos_R)
	{
		set_Value (COLUMNNAME_Interfase_Equipos_R, Interfase_Equipos_R);
	}

	/** Get Equipment Interfase Rout.
		@return Equipment Interfase Rout	  */
	public String getInterfase_Equipos_R () 
	{
		return (String)get_Value(COLUMNNAME_Interfase_Equipos_R);
	}

	/** Set Estadistic Interfase.
		@param Interfase_Estadistica Estadistic Interfase	  */
	public void setInterfase_Estadistica (boolean Interfase_Estadistica)
	{
		set_Value (COLUMNNAME_Interfase_Estadistica, Boolean.valueOf(Interfase_Estadistica));
	}

	/** Get Estadistic Interfase.
		@return Estadistic Interfase	  */
	public boolean isInterfase_Estadistica () 
	{
		Object oo = get_Value(COLUMNNAME_Interfase_Estadistica);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Estadistic Interfase Rout.
		@param Interfase_Estadistica_R Estadistic Interfase Rout	  */
	public void setInterfase_Estadistica_R (String Interfase_Estadistica_R)
	{
		set_Value (COLUMNNAME_Interfase_Estadistica_R, Interfase_Estadistica_R);
	}

	/** Get Estadistic Interfase Rout.
		@return Estadistic Interfase Rout	  */
	public String getInterfase_Estadistica_R () 
	{
		return (String)get_Value(COLUMNNAME_Interfase_Estadistica_R);
	}

	/** Set Interfase_Fact.
		@param Interfase_Fact Interfase_Fact	  */
	public void setInterfase_Fact (boolean Interfase_Fact)
	{
		set_Value (COLUMNNAME_Interfase_Fact, Boolean.valueOf(Interfase_Fact));
	}

	/** Get Interfase_Fact.
		@return Interfase_Fact	  */
	public boolean isInterfase_Fact () 
	{
		Object oo = get_Value(COLUMNNAME_Interfase_Fact);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Grifols Interfase.
		@param Interfase_Grifols Grifols Interfase	  */
	public void setInterfase_Grifols (boolean Interfase_Grifols)
	{
		set_Value (COLUMNNAME_Interfase_Grifols, Boolean.valueOf(Interfase_Grifols));
	}

	/** Get Grifols Interfase.
		@return Grifols Interfase	  */
	public boolean isInterfase_Grifols () 
	{
		Object oo = get_Value(COLUMNNAME_Interfase_Grifols);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Grifols Interfase Rout.
		@param Interfase_Grifols_R Grifols Interfase Rout	  */
	public void setInterfase_Grifols_R (String Interfase_Grifols_R)
	{
		set_Value (COLUMNNAME_Interfase_Grifols_R, Interfase_Grifols_R);
	}

	/** Get Grifols Interfase Rout.
		@return Grifols Interfase Rout	  */
	public String getInterfase_Grifols_R () 
	{
		return (String)get_Value(COLUMNNAME_Interfase_Grifols_R);
	}

	/** Interfase_Inventarios AD_Reference_ID=1200247 */
	public static final int INTERFASE_INVENTARIOS_AD_Reference_ID=1200247;
	/** Not Applicable = N */
	public static final String INTERFASE_INVENTARIOS_NotApplicable = "N";
	/** Sicome = S */
	public static final String INTERFASE_INVENTARIOS_Sicome = "S";
	/** SIA = P */
	public static final String INTERFASE_INVENTARIOS_SIA = "P";
	/** Set Inventory Interface.
		@param Interfase_Inventarios Inventory Interface	  */
	public void setInterfase_Inventarios (String Interfase_Inventarios)
	{
		if (Interfase_Inventarios == null) throw new IllegalArgumentException ("Interfase_Inventarios is mandatory");
		if (Interfase_Inventarios.equals("N") || Interfase_Inventarios.equals("S") || Interfase_Inventarios.equals("P")); else throw new IllegalArgumentException ("Interfase_Inventarios Invalid value - " + Interfase_Inventarios + " - Reference_ID=1200247 - N - S - P");		set_Value (COLUMNNAME_Interfase_Inventarios, Interfase_Inventarios);
	}

	/** Get Inventory Interface.
		@return Inventory Interface	  */
	public String getInterfase_Inventarios () 
	{
		return (String)get_Value(COLUMNNAME_Interfase_Inventarios);
	}

	/** Set Laboratory Interfase.
		@param Interfase_Laboratorio Laboratory Interfase	  */
	public void setInterfase_Laboratorio (boolean Interfase_Laboratorio)
	{
		set_Value (COLUMNNAME_Interfase_Laboratorio, Boolean.valueOf(Interfase_Laboratorio));
	}

	/** Get Laboratory Interfase.
		@return Laboratory Interfase	  */
	public boolean isInterfase_Laboratorio () 
	{
		Object oo = get_Value(COLUMNNAME_Interfase_Laboratorio);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Laboratory Interfase Rout.
		@param Interfase_Laboratorio_R Laboratory Interfase Rout	  */
	public void setInterfase_Laboratorio_R (String Interfase_Laboratorio_R)
	{
		set_Value (COLUMNNAME_Interfase_Laboratorio_R, Interfase_Laboratorio_R);
	}

	/** Get Laboratory Interfase Rout.
		@return Laboratory Interfase Rout	  */
	public String getInterfase_Laboratorio_R () 
	{
		return (String)get_Value(COLUMNNAME_Interfase_Laboratorio_R);
	}

	/** Set Interfase_LigaEleg.
		@param Interfase_LigaEleg Interfase_LigaEleg	  */
	public void setInterfase_LigaEleg (boolean Interfase_LigaEleg)
	{
		set_Value (COLUMNNAME_Interfase_LigaEleg, Boolean.valueOf(Interfase_LigaEleg));
	}

	/** Get Interfase_LigaEleg.
		@return Interfase_LigaEleg	  */
	public boolean isInterfase_LigaEleg () 
	{
		Object oo = get_Value(COLUMNNAME_Interfase_LigaEleg);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Siemens Interfase.
		@param Interfase_Siemens Siemens Interfase	  */
	public void setInterfase_Siemens (boolean Interfase_Siemens)
	{
		set_Value (COLUMNNAME_Interfase_Siemens, Boolean.valueOf(Interfase_Siemens));
	}

	/** Get Siemens Interfase.
		@return Siemens Interfase	  */
	public boolean isInterfase_Siemens () 
	{
		Object oo = get_Value(COLUMNNAME_Interfase_Siemens);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Siemens Interfase Rout.
		@param Interfase_Siemens_R Siemens Interfase Rout	  */
	public void setInterfase_Siemens_R (String Interfase_Siemens_R)
	{
		set_Value (COLUMNNAME_Interfase_Siemens_R, Interfase_Siemens_R);
	}

	/** Get Siemens Interfase Rout.
		@return Siemens Interfase Rout	  */
	public String getInterfase_Siemens_R () 
	{
		return (String)get_Value(COLUMNNAME_Interfase_Siemens_R);
	}

	/** Set Telemedicine Interfase.
		@param Interfase_Telemedicina Telemedicine Interfase	  */
	public void setInterfase_Telemedicina (boolean Interfase_Telemedicina)
	{
		set_Value (COLUMNNAME_Interfase_Telemedicina, Boolean.valueOf(Interfase_Telemedicina));
	}

	/** Get Telemedicine Interfase.
		@return Telemedicine Interfase	  */
	public boolean isInterfase_Telemedicina () 
	{
		Object oo = get_Value(COLUMNNAME_Interfase_Telemedicina);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Telemedicine Interfase Rout.
		@param Interfase_Telemedicina_r Telemedicine Interfase Rout	  */
	public void setInterfase_Telemedicina_r (String Interfase_Telemedicina_r)
	{
		set_Value (COLUMNNAME_Interfase_Telemedicina_r, Interfase_Telemedicina_r);
	}

	/** Get Telemedicine Interfase Rout.
		@return Telemedicine Interfase Rout	  */
	public String getInterfase_Telemedicina_r () 
	{
		return (String)get_Value(COLUMNNAME_Interfase_Telemedicina_r);
	}

	/** Set Is Loinc code required for laboratory results?.
		@param LoincRequired Is Loinc code required for laboratory results?	  */
	public void setLoincRequired (boolean LoincRequired)
	{
		set_Value (COLUMNNAME_LoincRequired, Boolean.valueOf(LoincRequired));
	}

	/** Get Is Loinc code required for laboratory results?.
		@return Is Loinc code required for laboratory results?	  */
	public boolean isLoincRequired () 
	{
		Object oo = get_Value(COLUMNNAME_LoincRequired);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Syngo_Param.
		@param Syngo_Param Syngo_Param	  */
	public void setSyngo_Param (String Syngo_Param)
	{
		if (Syngo_Param == null)
			throw new IllegalArgumentException ("Syngo_Param is mandatory.");
		set_Value (COLUMNNAME_Syngo_Param, Syngo_Param);
	}

	/** Get Syngo_Param.
		@return Syngo_Param	  */
	public String getSyngo_Param () 
	{
		return (String)get_Value(COLUMNNAME_Syngo_Param);
	}

	/** Set Syngo_ParamValue.
		@param Syngo_ParamValue Syngo_ParamValue	  */
	public void setSyngo_ParamValue (String Syngo_ParamValue)
	{
		if (Syngo_ParamValue == null)
			throw new IllegalArgumentException ("Syngo_ParamValue is mandatory.");
		set_Value (COLUMNNAME_Syngo_ParamValue, Syngo_ParamValue);
	}

	/** Get Syngo_ParamValue.
		@return Syngo_ParamValue	  */
	public String getSyngo_ParamValue () 
	{
		return (String)get_Value(COLUMNNAME_Syngo_ParamValue);
	}

	/** Set Syngo Password.
		@param Syngo_Pass Syngo Password	  */
	public void setSyngo_Pass (String Syngo_Pass)
	{
		set_Value (COLUMNNAME_Syngo_Pass, Syngo_Pass);
	}

	/** Get Syngo Password.
		@return Syngo Password	  */
	public String getSyngo_Pass () 
	{
		return (String)get_Value(COLUMNNAME_Syngo_Pass);
	}

	/** Set Syngo Route.
		@param Syngo_R Syngo Route	  */
	public void setSyngo_R (String Syngo_R)
	{
		set_Value (COLUMNNAME_Syngo_R, Syngo_R);
	}

	/** Get Syngo Route.
		@return Syngo Route	  */
	public String getSyngo_R () 
	{
		return (String)get_Value(COLUMNNAME_Syngo_R);
	}

	/** Set Syngo User.
		@param Syngo_User Syngo User	  */
	public void setSyngo_User (String Syngo_User)
	{
		set_Value (COLUMNNAME_Syngo_User, Syngo_User);
	}

	/** Get Syngo User.
		@return Syngo User	  */
	public String getSyngo_User () 
	{
		return (String)get_Value(COLUMNNAME_Syngo_User);
	}
}