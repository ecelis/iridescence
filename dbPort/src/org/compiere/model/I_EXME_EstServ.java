/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_EstServ
 *  @author Generated Class 
 *  @version Version @VERSION@
 */
public interface I_EXME_EstServ 
{

    /** TableName=EXME_EstServ */
    public static final String Table_Name = "EXME_EstServ";

    /** AD_Table_ID=1200344 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 7 - System - Client - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(7);

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

    /** Column name AD_OrgTrx_ID */
    public static final String COLUMNNAME_AD_OrgTrx_ID = "AD_OrgTrx_ID";

	/** Set Trx Organization.
	  * Performing or initiating organization
	  */
	public void setAD_OrgTrx_ID (int AD_OrgTrx_ID);

	/** Get Trx Organization.
	  * Performing or initiating organization
	  */
	public int getAD_OrgTrx_ID();

    /** Column name Description */
    public static final String COLUMNNAME_Description = "Description";

	/** Set Description.
	  * Optional short description of the record
	  */
	public void setDescription (String Description);

	/** Get Description.
	  * Optional short description of the record
	  */
	public String getDescription();

    /** Column name EXME_Area_ID */
    public static final String COLUMNNAME_EXME_Area_ID = "EXME_Area_ID";

	/** Set Service.
	  * Service
	  */
	public void setEXME_Area_ID (int EXME_Area_ID);

	/** Get Service.
	  * Service
	  */
	public int getEXME_Area_ID();

    /** Column name EXME_EstServ_ID */
    public static final String COLUMNNAME_EXME_EstServ_ID = "EXME_EstServ_ID";

	/** Set Service Unit.
	  * Service Unit
	  */
	public void setEXME_EstServ_ID (int EXME_EstServ_ID);

	/** Get Service Unit.
	  * Service Unit
	  */
	public int getEXME_EstServ_ID();

    /** Column name IsBancoOjos */
    public static final String COLUMNNAME_IsBancoOjos = "IsBancoOjos";

	/** Set Is Eyes Bank.
	  * Description about the Eyes Bank
	  */
	public void setIsBancoOjos (boolean IsBancoOjos);

	/** Get Is Eyes Bank.
	  * Description about the Eyes Bank
	  */
	public boolean isBancoOjos();

    /** Column name ManejaAgenda */
    public static final String COLUMNNAME_ManejaAgenda = "ManejaAgenda";

	/** Set Use Schedule	  */
	public void setManejaAgenda (boolean ManejaAgenda);

	/** Get Use Schedule	  */
	public boolean isManejaAgenda();

    /** Column name Name */
    public static final String COLUMNNAME_Name = "Name";

	/** Set Name.
	  * Alphanumeric identifier of the entity
	  */
	public void setName (String Name);

	/** Get Name.
	  * Alphanumeric identifier of the entity
	  */
	public String getName();

    /** Column name Piso */
    public static final String COLUMNNAME_Piso = "Piso";

	/** Set Floor	  */
	public void setPiso (String Piso);

	/** Get Floor	  */
	public String getPiso();

    /** Column name TipoArea */
    public static final String COLUMNNAME_TipoArea = "TipoArea";

	/** Set Area Type.
	  * Admission Area Type
	  */
	public void setTipoArea (String TipoArea);

	/** Get Area Type.
	  * Admission Area Type
	  */
	public String getTipoArea();

    /** Column name UsaLPCECOFI */
    public static final String COLUMNNAME_UsaLPCECOFI = "UsaLPCECOFI";

	/** Set Usa L.P. SECOFI.
	  * Use SECOFI P.L.
	  */
	public void setUsaLPCECOFI (boolean UsaLPCECOFI);

	/** Get Usa L.P. SECOFI.
	  * Use SECOFI P.L.
	  */
	public boolean isUsaLPCECOFI();

    /** Column name Value */
    public static final String COLUMNNAME_Value = "Value";

	/** Set Search Key.
	  * Search key for the record in the format required - must be unique
	  */
	public void setValue (String Value);

	/** Get Search Key.
	  * Search key for the record in the format required - must be unique
	  */
	public String getValue();
}
