/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.util.KeyNamePair;

/** Generated Model for EXME_RolesConfigurador
 *  @author Generated Class 
 *  @version Release 1.1.2 - $Id$ */
public class X_EXME_RolesConfigurador extends PO implements I_EXME_RolesConfigurador, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_RolesConfigurador (Properties ctx, int EXME_RolesConfigurador_ID, String trxName)
    {
      super (ctx, EXME_RolesConfigurador_ID, trxName);
      /** if (EXME_RolesConfigurador_ID == 0)
        {
			setEXME_RolesConfigurador_ID (0);
			setRoleType (null);
			setTipoArea (null);
			setValue (null);
        } */
    }

    /** Load Constructor */
    public X_EXME_RolesConfigurador (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 4 - System 
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
      StringBuffer sb = new StringBuffer ("X_EXME_RolesConfigurador[")
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

	/** Set Configurator Roles.
		@param EXME_RolesConfigurador_ID Configurator Roles	  */
	public void setEXME_RolesConfigurador_ID (int EXME_RolesConfigurador_ID)
	{
		if (EXME_RolesConfigurador_ID < 1)
			 throw new IllegalArgumentException ("EXME_RolesConfigurador_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_RolesConfigurador_ID, Integer.valueOf(EXME_RolesConfigurador_ID));
	}

	/** Get Configurator Roles.
		@return Configurator Roles	  */
	public int getEXME_RolesConfigurador_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_RolesConfigurador_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Name.
		@param Name 
		Alphanumeric identifier of the entity
	  */
	public void setName (String Name)
	{
		set_Value (COLUMNNAME_Name, Name);
	}

	/** Get Name.
		@return Alphanumeric identifier of the entity
	  */
	public String getName () 
	{
		return (String)get_Value(COLUMNNAME_Name);
	}

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), getName());
    }

	/** RoleType AD_Reference_ID=1200422 */
	public static final int ROLETYPE_AD_Reference_ID=1200422;
	/** PHYSICIAN PO = PO */
	public static final String ROLETYPE_PHYSICIANPO = "PO";
	/** ASSISTANT PO = PR */
	public static final String ROLETYPE_ASSISTANTPO = "PR";
	/** CASHIER PO = CA */
	public static final String ROLETYPE_CASHIERPO = "CA";
	/** INFIRMARY STAFF PO = IS */
	public static final String ROLETYPE_INFIRMARYSTAFFPO = "IS";
	/** ADMINISTRATIVE PERSONNEL PO = AP */
	public static final String ROLETYPE_ADMINISTRATIVEPERSONNELPO = "AP";
	/** PHYSICIAN ASC = PS */
	public static final String ROLETYPE_PHYSICIANASC = "PS";
	/** PHYSICIAN OCC = POC */
	public static final String ROLETYPE_PHYSICIANOCC = "POC";
	/** ADMINISTRATIVE PERSONNEL ASC = APS */
	public static final String ROLETYPE_ADMINISTRATIVEPERSONNELASC = "APS";
	/** ADMINISTRATIVE PERSONNEL HC = APH */
	public static final String ROLETYPE_ADMINISTRATIVEPERSONNELHC = "APH";
	/** ADMINISTRATIVE PERSONNEL OCC = APO */
	public static final String ROLETYPE_ADMINISTRATIVEPERSONNELOCC = "APO";
	/** CASHIER ASC = CAS */
	public static final String ROLETYPE_CASHIERASC = "CAS";
	/** CASHIER HC = CAH */
	public static final String ROLETYPE_CASHIERHC = "CAH";
	/** CASHIER OCC = CAO */
	public static final String ROLETYPE_CASHIEROCC = "CAO";
	/** INFIRMARY STAFF ASC = ISS */
	public static final String ROLETYPE_INFIRMARYSTAFFASC = "ISS";
	/** INFIRMARY STAFF HC = ISH */
	public static final String ROLETYPE_INFIRMARYSTAFFHC = "ISH";
	/** INFIRMARY STAFF OCC = ISO */
	public static final String ROLETYPE_INFIRMARYSTAFFOCC = "ISO";
	/** PHYSICIAN HC = PH */
	public static final String ROLETYPE_PHYSICIANHC = "PH";
	/** TECHNICIAN ASC = TS */
	public static final String ROLETYPE_TECHNICIANASC = "TS";
	/** TECHNICIAN HC = TH */
	public static final String ROLETYPE_TECHNICIANHC = "TH";
	/** TECHNICIAN OCC = TO */
	public static final String ROLETYPE_TECHNICIANOCC = "TO";
	/** ASSISTANT ASC = AS */
	public static final String ROLETYPE_ASSISTANTASC = "AS";
	/** ASSISTANT HC = AH */
	public static final String ROLETYPE_ASSISTANTHC = "AH";
	/** ASSISTANT OCC = AO */
	public static final String ROLETYPE_ASSISTANTOCC = "AO";
	/** HIM PO = HIP */
	public static final String ROLETYPE_HIMPO = "HIP";
	/** HIM HC = HIC */
	public static final String ROLETYPE_HIMHC = "HIC";
	/** HIM ASC = HIA */
	public static final String ROLETYPE_HIMASC = "HIA";
	/** HIM OCC = HIO */
	public static final String ROLETYPE_HIMOCC = "HIO";
	/** ADIMISION PO = ADP */
	public static final String ROLETYPE_ADIMISIONPO = "ADP";
	/** ADIMISION HC = ADC */
	public static final String ROLETYPE_ADIMISIONHC = "ADC";
	/** ADIMISION ASC = ADA */
	public static final String ROLETYPE_ADIMISIONASC = "ADA";
	/** ADIMISION OCC = ADO */
	public static final String ROLETYPE_ADIMISIONOCC = "ADO";
	/** IT ADMIN PO = ITP */
	public static final String ROLETYPE_ITADMINPO = "ITP";
	/** IT ADMIN HC = ITC */
	public static final String ROLETYPE_ITADMINHC = "ITC";
	/** IT ADMIN ASC = ITA */
	public static final String ROLETYPE_ITADMINASC = "ITA";
	/** IT ADMIN OCC = ITO */
	public static final String ROLETYPE_ITADMINOCC = "ITO";
	/** PHARMACIST PO = PHP */
	public static final String ROLETYPE_PHARMACISTPO = "PHP";
	/** PHARMACIST HC = PHC */
	public static final String ROLETYPE_PHARMACISTHC = "PHC";
	/** PHARMACIST ASC = PHA */
	public static final String ROLETYPE_PHARMACISTASC = "PHA";
	/** PHARMACIST OCC = PHO */
	public static final String ROLETYPE_PHARMACISTOCC = "PHO";
	/** CODER = COC */
	public static final String ROLETYPE_CODER = "COC";
	/** Set Role Type.
		@param RoleType Role Type	  */
	public void setRoleType (String RoleType)
	{
		if (RoleType == null) throw new IllegalArgumentException ("RoleType is mandatory");
		if (RoleType.equals("PO") || RoleType.equals("PR") || RoleType.equals("CA") || RoleType.equals("IS") || RoleType.equals("AP") || RoleType.equals("PS") || RoleType.equals("POC") || RoleType.equals("APS") || RoleType.equals("APH") || RoleType.equals("APO") || RoleType.equals("CAS") || RoleType.equals("CAH") || RoleType.equals("CAO") || RoleType.equals("ISS") || RoleType.equals("ISH") || RoleType.equals("ISO") || RoleType.equals("PH") || RoleType.equals("TS") || RoleType.equals("TH") || RoleType.equals("TO") || RoleType.equals("AS") || RoleType.equals("AH") || RoleType.equals("AO") || RoleType.equals("HIP") || RoleType.equals("HIC") || RoleType.equals("HIA") || RoleType.equals("HIO") || RoleType.equals("ADP") || RoleType.equals("ADC") || RoleType.equals("ADA") || RoleType.equals("ADO") || RoleType.equals("ITP") || RoleType.equals("ITC") || RoleType.equals("ITA") || RoleType.equals("ITO") || RoleType.equals("PHP") || RoleType.equals("PHC") || RoleType.equals("PHA") || RoleType.equals("PHO") || RoleType.equals("COC")); else throw new IllegalArgumentException ("RoleType Invalid value - " + RoleType + " - Reference_ID=1200422 - PO - PR - CA - IS - AP - PS - POC - APS - APH - APO - CAS - CAH - CAO - ISS - ISH - ISO - PH - TS - TH - TO - AS - AH - AO - HIP - HIC - HIA - HIO - ADP - ADC - ADA - ADO - ITP - ITC - ITA - ITO - PHP - PHC - PHA - PHO - COC");		set_Value (COLUMNNAME_RoleType, RoleType);
	}

	/** Get Role Type.
		@return Role Type	  */
	public String getRoleType () 
	{
		return (String)get_Value(COLUMNNAME_RoleType);
	}

	/** TipoArea AD_Reference_ID=1200421 */
	public static final int TIPOAREA_AD_Reference_ID=1200421;
	/** PHYSICIAN OFFICE  = P */
	public static final String TIPOAREA_PHYSICIANOFFICE = "P";
	/** OUTPATIENT CARE CENTER  = O */
	public static final String TIPOAREA_OUTPATIENTCARECENTER = "O";
	/** AMBULATORY SURGERY CENTER  = A */
	public static final String TIPOAREA_AMBULATORYSURGERYCENTER = "A";
	/** HOSPITAL CENTER = C */
	public static final String TIPOAREA_HOSPITALCENTER = "C";
	/** Set Area Type.
		@param TipoArea 
		Admission Area Type
	  */
	public void setTipoArea (String TipoArea)
	{
		if (TipoArea == null) throw new IllegalArgumentException ("TipoArea is mandatory");
		if (TipoArea.equals("P") || TipoArea.equals("O") || TipoArea.equals("A") || TipoArea.equals("C")); else throw new IllegalArgumentException ("TipoArea Invalid value - " + TipoArea + " - Reference_ID=1200421 - P - O - A - C");		set_Value (COLUMNNAME_TipoArea, TipoArea);
	}

	/** Get Area Type.
		@return Admission Area Type
	  */
	public String getTipoArea () 
	{
		return (String)get_Value(COLUMNNAME_TipoArea);
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