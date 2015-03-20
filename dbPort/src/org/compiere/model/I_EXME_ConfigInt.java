/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_ConfigInt
 *  @author Generated Class 
 *  @version Version @VERSION@
 */
public interface I_EXME_ConfigInt 
{

    /** TableName=EXME_ConfigInt */
    public static final String Table_Name = "EXME_ConfigInt";

    /** AD_Table_ID=1200341 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 3 - Client - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(3);

    /** Load Meta Data */

    /** Column name AD_Org_ID */
    public static final String COLUMNNAME_AD_Org_ID = "AD_Org_ID";

	/** Set Organization.
	  * Organizational entity within client
	  */
	public void setAD_Org_ID (int AD_Org_ID);

	/** Get Organization.
	  * Organizational entity within client
	  */
	public int getAD_Org_ID();

    /** Column name EXME_ConfigInt_ID */
    public static final String COLUMNNAME_EXME_ConfigInt_ID = "EXME_ConfigInt_ID";

	/** Set Interface Configuration	  */
	public void setEXME_ConfigInt_ID (int EXME_ConfigInt_ID);

	/** Get Interface Configuration	  */
	public int getEXME_ConfigInt_ID();

    /** Column name GE_OpenAPI */
    public static final String COLUMNNAME_GE_OpenAPI = "GE_OpenAPI";

	/** Set GE Interface Centricity WebSite	  */
	public void setGE_OpenAPI (String GE_OpenAPI);

	/** Get GE Interface Centricity WebSite	  */
	public String getGE_OpenAPI();

    /** Column name Grifols_Warehouse_ID */
    public static final String COLUMNNAME_Grifols_Warehouse_ID = "Grifols_Warehouse_ID";

	/** Set Grifols Warehouse.
	  * Delivery Warehouse for external charges
	  */
	public void setGrifols_Warehouse_ID (int Grifols_Warehouse_ID);

	/** Get Grifols Warehouse.
	  * Delivery Warehouse for external charges
	  */
	public int getGrifols_Warehouse_ID();

    /** Column name Interfase_Cardiologia */
    public static final String COLUMNNAME_Interfase_Cardiologia = "Interfase_Cardiologia";

	/** Set Cardiology Interfase	  */
	public void setInterfase_Cardiologia (boolean Interfase_Cardiologia);

	/** Get Cardiology Interfase	  */
	public boolean isInterfase_Cardiologia();

    /** Column name Interfase_Cardiologia_R */
    public static final String COLUMNNAME_Interfase_Cardiologia_R = "Interfase_Cardiologia_R";

	/** Set Cardiology Interfase Rout	  */
	public void setInterfase_Cardiologia_R (String Interfase_Cardiologia_R);

	/** Get Cardiology Interfase Rout	  */
	public String getInterfase_Cardiologia_R();

    /** Column name Interfase_DGI */
    public static final String COLUMNNAME_Interfase_DGI = "Interfase_DGI";

	/** Set Medical Beneficiaries Interface	  */
	public void setInterfase_DGI (boolean Interfase_DGI);

	/** Get Medical Beneficiaries Interface	  */
	public boolean isInterfase_DGI();

    /** Column name Interfase_Eleg */
    public static final String COLUMNNAME_Interfase_Eleg = "Interfase_Eleg";

	/** Set Interfase_Eleg	  */
	public void setInterfase_Eleg (boolean Interfase_Eleg);

	/** Get Interfase_Eleg	  */
	public boolean isInterfase_Eleg();

    /** Column name Interfase_Equipos */
    public static final String COLUMNNAME_Interfase_Equipos = "Interfase_Equipos";

	/** Set Interfase_Equipos	  */
	public void setInterfase_Equipos (boolean Interfase_Equipos);

	/** Get Interfase_Equipos	  */
	public boolean isInterfase_Equipos();

    /** Column name Interfase_Equipos_R */
    public static final String COLUMNNAME_Interfase_Equipos_R = "Interfase_Equipos_R";

	/** Set Equipment Interfase Rout	  */
	public void setInterfase_Equipos_R (String Interfase_Equipos_R);

	/** Get Equipment Interfase Rout	  */
	public String getInterfase_Equipos_R();

    /** Column name Interfase_Estadistica */
    public static final String COLUMNNAME_Interfase_Estadistica = "Interfase_Estadistica";

	/** Set Estadistic Interfase	  */
	public void setInterfase_Estadistica (boolean Interfase_Estadistica);

	/** Get Estadistic Interfase	  */
	public boolean isInterfase_Estadistica();

    /** Column name Interfase_Estadistica_R */
    public static final String COLUMNNAME_Interfase_Estadistica_R = "Interfase_Estadistica_R";

	/** Set Estadistic Interfase Rout	  */
	public void setInterfase_Estadistica_R (String Interfase_Estadistica_R);

	/** Get Estadistic Interfase Rout	  */
	public String getInterfase_Estadistica_R();

    /** Column name Interfase_Fact */
    public static final String COLUMNNAME_Interfase_Fact = "Interfase_Fact";

	/** Set Interfase_Fact	  */
	public void setInterfase_Fact (boolean Interfase_Fact);

	/** Get Interfase_Fact	  */
	public boolean isInterfase_Fact();

    /** Column name Interfase_Grifols */
    public static final String COLUMNNAME_Interfase_Grifols = "Interfase_Grifols";

	/** Set Grifols Interfase	  */
	public void setInterfase_Grifols (boolean Interfase_Grifols);

	/** Get Grifols Interfase	  */
	public boolean isInterfase_Grifols();

    /** Column name Interfase_Grifols_R */
    public static final String COLUMNNAME_Interfase_Grifols_R = "Interfase_Grifols_R";

	/** Set Grifols Interfase Rout	  */
	public void setInterfase_Grifols_R (String Interfase_Grifols_R);

	/** Get Grifols Interfase Rout	  */
	public String getInterfase_Grifols_R();

    /** Column name Interfase_Inventarios */
    public static final String COLUMNNAME_Interfase_Inventarios = "Interfase_Inventarios";

	/** Set Inventory Interface	  */
	public void setInterfase_Inventarios (String Interfase_Inventarios);

	/** Get Inventory Interface	  */
	public String getInterfase_Inventarios();

    /** Column name Interfase_Laboratorio */
    public static final String COLUMNNAME_Interfase_Laboratorio = "Interfase_Laboratorio";

	/** Set Laboratory Interfase	  */
	public void setInterfase_Laboratorio (boolean Interfase_Laboratorio);

	/** Get Laboratory Interfase	  */
	public boolean isInterfase_Laboratorio();

    /** Column name Interfase_Laboratorio_R */
    public static final String COLUMNNAME_Interfase_Laboratorio_R = "Interfase_Laboratorio_R";

	/** Set Laboratory Interfase Rout	  */
	public void setInterfase_Laboratorio_R (String Interfase_Laboratorio_R);

	/** Get Laboratory Interfase Rout	  */
	public String getInterfase_Laboratorio_R();

    /** Column name Interfase_LigaEleg */
    public static final String COLUMNNAME_Interfase_LigaEleg = "Interfase_LigaEleg";

	/** Set Interfase_LigaEleg	  */
	public void setInterfase_LigaEleg (boolean Interfase_LigaEleg);

	/** Get Interfase_LigaEleg	  */
	public boolean isInterfase_LigaEleg();

    /** Column name Interfase_Siemens */
    public static final String COLUMNNAME_Interfase_Siemens = "Interfase_Siemens";

	/** Set Siemens Interfase	  */
	public void setInterfase_Siemens (boolean Interfase_Siemens);

	/** Get Siemens Interfase	  */
	public boolean isInterfase_Siemens();

    /** Column name Interfase_Siemens_R */
    public static final String COLUMNNAME_Interfase_Siemens_R = "Interfase_Siemens_R";

	/** Set Siemens Interfase Rout	  */
	public void setInterfase_Siemens_R (String Interfase_Siemens_R);

	/** Get Siemens Interfase Rout	  */
	public String getInterfase_Siemens_R();

    /** Column name Interfase_Telemedicina */
    public static final String COLUMNNAME_Interfase_Telemedicina = "Interfase_Telemedicina";

	/** Set Telemedicine Interfase	  */
	public void setInterfase_Telemedicina (boolean Interfase_Telemedicina);

	/** Get Telemedicine Interfase	  */
	public boolean isInterfase_Telemedicina();

    /** Column name Interfase_Telemedicina_r */
    public static final String COLUMNNAME_Interfase_Telemedicina_r = "Interfase_Telemedicina_r";

	/** Set Telemedicine Interfase Rout	  */
	public void setInterfase_Telemedicina_r (String Interfase_Telemedicina_r);

	/** Get Telemedicine Interfase Rout	  */
	public String getInterfase_Telemedicina_r();

    /** Column name LoincRequired */
    public static final String COLUMNNAME_LoincRequired = "LoincRequired";

	/** Set Is Loinc code required for laboratory results?	  */
	public void setLoincRequired (boolean LoincRequired);

	/** Get Is Loinc code required for laboratory results?	  */
	public boolean isLoincRequired();

    /** Column name Syngo_Param */
    public static final String COLUMNNAME_Syngo_Param = "Syngo_Param";

	/** Set Syngo_Param	  */
	public void setSyngo_Param (String Syngo_Param);

	/** Get Syngo_Param	  */
	public String getSyngo_Param();

    /** Column name Syngo_ParamValue */
    public static final String COLUMNNAME_Syngo_ParamValue = "Syngo_ParamValue";

	/** Set Syngo_ParamValue	  */
	public void setSyngo_ParamValue (String Syngo_ParamValue);

	/** Get Syngo_ParamValue	  */
	public String getSyngo_ParamValue();

    /** Column name Syngo_Pass */
    public static final String COLUMNNAME_Syngo_Pass = "Syngo_Pass";

	/** Set Syngo Password	  */
	public void setSyngo_Pass (String Syngo_Pass);

	/** Get Syngo Password	  */
	public String getSyngo_Pass();

    /** Column name Syngo_R */
    public static final String COLUMNNAME_Syngo_R = "Syngo_R";

	/** Set Syngo Route	  */
	public void setSyngo_R (String Syngo_R);

	/** Get Syngo Route	  */
	public String getSyngo_R();

    /** Column name Syngo_User */
    public static final String COLUMNNAME_Syngo_User = "Syngo_User";

	/** Set Syngo User	  */
	public void setSyngo_User (String Syngo_User);

	/** Get Syngo User	  */
	public String getSyngo_User();
}
