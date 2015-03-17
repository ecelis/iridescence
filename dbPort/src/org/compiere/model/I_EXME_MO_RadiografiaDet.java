/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_MO_RadiografiaDet
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_MO_RadiografiaDet 
{

    /** TableName=EXME_MO_RadiografiaDet */
    public static final String Table_Name = "EXME_MO_RadiografiaDet";

    /** AD_Table_ID=1200395 */
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

    /** Column name EXME_MO_RadiografiaDet_ID */
    public static final String COLUMNNAME_EXME_MO_RadiografiaDet_ID = "EXME_MO_RadiografiaDet_ID";

	/** Set X-Ray detail	  */
	public void setEXME_MO_RadiografiaDet_ID (int EXME_MO_RadiografiaDet_ID);

	/** Get X-Ray detail	  */
	public int getEXME_MO_RadiografiaDet_ID();

    /** Column name EXME_MO_Radiografias_ID */
    public static final String COLUMNNAME_EXME_MO_Radiografias_ID = "EXME_MO_Radiografias_ID";

	/** Set X-Ray	  */
	public void setEXME_MO_Radiografias_ID (int EXME_MO_Radiografias_ID);

	/** Get X-Ray	  */
	public int getEXME_MO_Radiografias_ID();

	public I_EXME_MO_Radiografias getEXME_MO_Radiografias() throws RuntimeException;

    /** Column name EXME_TratamientosPaciente_ID */
    public static final String COLUMNNAME_EXME_TratamientosPaciente_ID = "EXME_TratamientosPaciente_ID";

	/** Set Patient Treatments	  */
	public void setEXME_TratamientosPaciente_ID (int EXME_TratamientosPaciente_ID);

	/** Get Patient Treatments	  */
	public int getEXME_TratamientosPaciente_ID();

	public I_EXME_TratamientosPaciente getEXME_TratamientosPaciente() throws RuntimeException;
}
