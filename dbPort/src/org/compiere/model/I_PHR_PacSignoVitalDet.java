/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for PHR_PacSignoVitalDet
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_PHR_PacSignoVitalDet 
{

    /** TableName=PHR_PacSignoVitalDet */
    public static final String Table_Name = "PHR_PacSignoVitalDet";

    /** AD_Table_ID=1201014 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 4 - System 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(4);

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

    /** Column name EXME_SignoVital_ID */
    public static final String COLUMNNAME_EXME_SignoVital_ID = "EXME_SignoVital_ID";

	/** Set Vital Sign	  */
	public void setEXME_SignoVital_ID (int EXME_SignoVital_ID);

	/** Get Vital Sign	  */
	public int getEXME_SignoVital_ID();

	public I_EXME_SignoVital getEXME_SignoVital() throws RuntimeException;

    /** Column name PHR_PacSignoVitalDet_ID */
    public static final String COLUMNNAME_PHR_PacSignoVitalDet_ID = "PHR_PacSignoVitalDet_ID";

	/** Set Detail of patient's vital signs	  */
	public void setPHR_PacSignoVitalDet_ID (int PHR_PacSignoVitalDet_ID);

	/** Get Detail of patient's vital signs	  */
	public int getPHR_PacSignoVitalDet_ID();

    /** Column name PHR_PacSignoVital_ID */
    public static final String COLUMNNAME_PHR_PacSignoVital_ID = "PHR_PacSignoVital_ID";

	/** Set Patient's Vital Signs	  */
	public void setPHR_PacSignoVital_ID (int PHR_PacSignoVital_ID);

	/** Get Patient's Vital Signs	  */
	public int getPHR_PacSignoVital_ID();

	public I_PHR_PacSignoVital getPHR_PacSignoVital() throws RuntimeException;

    /** Column name Valor */
    public static final String COLUMNNAME_Valor = "Valor";

	/** Set Value	  */
	public void setValor (BigDecimal Valor);

	/** Get Value	  */
	public BigDecimal getValor();
}
