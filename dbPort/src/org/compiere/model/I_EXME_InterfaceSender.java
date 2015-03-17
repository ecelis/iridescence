/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_InterfaceSender
 *  @author Generated Class 
 *  @version Release 2.0.0 (alpha)
 */
public interface I_EXME_InterfaceSender 
{

    /** TableName=EXME_InterfaceSender */
    public static final String Table_Name = "EXME_InterfaceSender";

    /** AD_Table_ID=1200352 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 6 - System - Client 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(6);

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

    /** Column name C_AcctSchema_ID */
    public static final String COLUMNNAME_C_AcctSchema_ID = "C_AcctSchema_ID";

	/** Set Accounting Schema.
	  * Rules for accounting
	  */
	public void setC_AcctSchema_ID (int C_AcctSchema_ID);

	/** Get Accounting Schema.
	  * Rules for accounting
	  */
	public int getC_AcctSchema_ID();

	public I_C_AcctSchema getC_AcctSchema() throws RuntimeException;

    /** Column name Client */
    public static final String COLUMNNAME_Client = "Client";

	/** Set Client	  */
	public void setClient (int Client);

	/** Get Client	  */
	public int getClient();

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

	public I_EXME_EstServ getEXME_EstServ() throws RuntimeException;

    /** Column name EXME_InterfaceSender_ID */
    public static final String COLUMNNAME_EXME_InterfaceSender_ID = "EXME_InterfaceSender_ID";

	/** Set Iterfase Sender.
	  * Iterfase Sender
	  */
	public void setEXME_InterfaceSender_ID (int EXME_InterfaceSender_ID);

	/** Get Iterfase Sender.
	  * Iterfase Sender
	  */
	public int getEXME_InterfaceSender_ID();

    /** Column name ImagenORM_R */
    public static final String COLUMNNAME_ImagenORM_R = "ImagenORM_R";

	/** Set ImagenORM_R	  */
	public void setImagenORM_R (String ImagenORM_R);

	/** Get ImagenORM_R	  */
	public String getImagenORM_R();

    /** Column name Imprimir_CodZebra */
    public static final String COLUMNNAME_Imprimir_CodZebra = "Imprimir_CodZebra";

	/** Set Print Code Zebra	  */
	public void setImprimir_CodZebra (boolean Imprimir_CodZebra);

	/** Get Print Code Zebra	  */
	public boolean isImprimir_CodZebra();

    /** Column name Org */
    public static final String COLUMNNAME_Org = "Org";

	/** Set Organization	  */
	public void setOrg (int Org);

	/** Get Organization	  */
	public int getOrg();

    /** Column name ORM_Param */
    public static final String COLUMNNAME_ORM_Param = "ORM_Param";

	/** Set Image Parameters ORM	  */
	public void setORM_Param (String ORM_Param);

	/** Get Image Parameters ORM	  */
	public String getORM_Param();

    /** Column name ORM_ParamValue */
    public static final String COLUMNNAME_ORM_ParamValue = "ORM_ParamValue";

	/** Set Image Parameters Value ORM	  */
	public void setORM_ParamValue (String ORM_ParamValue);

	/** Get Image Parameters Value ORM	  */
	public String getORM_ParamValue();

    /** Column name Usar_ImagenORM */
    public static final String COLUMNNAME_Usar_ImagenORM = "Usar_ImagenORM";

	/** Set Use Image ORM.
	  * Use Image ORM
	  */
	public void setUsar_ImagenORM (boolean Usar_ImagenORM);

	/** Get Use Image ORM.
	  * Use Image ORM
	  */
	public boolean isUsar_ImagenORM();

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
