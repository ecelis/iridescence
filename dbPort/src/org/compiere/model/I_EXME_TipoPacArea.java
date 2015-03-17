/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_TipoPacArea
 *  @author Generated Class 
 *  @version Release 1.1.2
 */
public interface I_EXME_TipoPacArea 
{

    /** TableName=EXME_TipoPacArea */
    public static final String Table_Name = "EXME_TipoPacArea";

    /** AD_Table_ID=1201186 */
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

	public I_EXME_Area getEXME_Area() throws RuntimeException;

    /** Column name EXME_TipoPacArea_ID */
    public static final String COLUMNNAME_EXME_TipoPacArea_ID = "EXME_TipoPacArea_ID";

	/** Set EXME_TipoPacArea_ID	  */
	public void setEXME_TipoPacArea_ID (int EXME_TipoPacArea_ID);

	/** Get EXME_TipoPacArea_ID	  */
	public int getEXME_TipoPacArea_ID();

    /** Column name EXME_TipoPaciente_ID */
    public static final String COLUMNNAME_EXME_TipoPaciente_ID = "EXME_TipoPaciente_ID";

	/** Set Type of Patient.
	  * Type of Patient
	  */
	public void setEXME_TipoPaciente_ID (int EXME_TipoPaciente_ID);

	/** Get Type of Patient.
	  * Type of Patient
	  */
	public int getEXME_TipoPaciente_ID();

	public I_EXME_TipoPaciente getEXME_TipoPaciente() throws RuntimeException;

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
}
