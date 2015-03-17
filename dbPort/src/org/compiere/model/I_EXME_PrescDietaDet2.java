/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_PrescDietaDet2
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_PrescDietaDet2 
{

    /** TableName=EXME_PrescDietaDet2 */
    public static final String Table_Name = "EXME_PrescDietaDet2";

    /** AD_Table_ID=1200485 */
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

    /** Column name EXME_ConfigDieta_Det_ID */
    public static final String COLUMNNAME_EXME_ConfigDieta_Det_ID = "EXME_ConfigDieta_Det_ID";

	/** Set Configuration Diet Detail.
	  * Configuration Diet Detail
	  */
	public void setEXME_ConfigDieta_Det_ID (int EXME_ConfigDieta_Det_ID);

	/** Get Configuration Diet Detail.
	  * Configuration Diet Detail
	  */
	public int getEXME_ConfigDieta_Det_ID();

	public I_EXME_ConfigDieta_Det getEXME_ConfigDieta_Det() throws RuntimeException;

    /** Column name EXME_ConfigDieta_ID */
    public static final String COLUMNNAME_EXME_ConfigDieta_ID = "EXME_ConfigDieta_ID";

	/** Set Diet Configuration	  */
	public void setEXME_ConfigDieta_ID (int EXME_ConfigDieta_ID);

	/** Get Diet Configuration	  */
	public int getEXME_ConfigDieta_ID();

	public I_EXME_ConfigDieta getEXME_ConfigDieta() throws RuntimeException;

    /** Column name EXME_PrescDietaDet2_ID */
    public static final String COLUMNNAME_EXME_PrescDietaDet2_ID = "EXME_PrescDietaDet2_ID";

	/** Set Dietetics Prescription Details	  */
	public void setEXME_PrescDietaDet2_ID (int EXME_PrescDietaDet2_ID);

	/** Get Dietetics Prescription Details	  */
	public int getEXME_PrescDietaDet2_ID();

    /** Column name EXME_PrescDieta_ID */
    public static final String COLUMNNAME_EXME_PrescDieta_ID = "EXME_PrescDieta_ID";

	/** Set Dietetics Prescription.
	  * Dietetics Prescription
	  */
	public void setEXME_PrescDieta_ID (int EXME_PrescDieta_ID);

	/** Get Dietetics Prescription.
	  * Dietetics Prescription
	  */
	public int getEXME_PrescDieta_ID();

	public I_EXME_PrescDieta getEXME_PrescDieta() throws RuntimeException;
}
