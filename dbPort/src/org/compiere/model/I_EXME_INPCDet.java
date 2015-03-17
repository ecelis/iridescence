/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_INPCDet
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_INPCDet 
{

    /** TableName=EXME_INPCDet */
    public static final String Table_Name = "EXME_INPCDet";

    /** AD_Table_ID=1200031 */
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

    /** Column name C_Period_ID */
    public static final String COLUMNNAME_C_Period_ID = "C_Period_ID";

	/** Set Period.
	  * Period of the Calendar
	  */
	public void setC_Period_ID (int C_Period_ID);

	/** Get Period.
	  * Period of the Calendar
	  */
	public int getC_Period_ID();

	public I_C_Period getC_Period() throws RuntimeException;

    /** Column name EXME_INPCDet_ID */
    public static final String COLUMNNAME_EXME_INPCDet_ID = "EXME_INPCDet_ID";

	/** Set Detail INPC	  */
	public void setEXME_INPCDet_ID (int EXME_INPCDet_ID);

	/** Get Detail INPC	  */
	public int getEXME_INPCDet_ID();

    /** Column name EXME_INPC_ID */
    public static final String COLUMNNAME_EXME_INPC_ID = "EXME_INPC_ID";

	/** Set INPC	  */
	public void setEXME_INPC_ID (int EXME_INPC_ID);

	/** Get INPC	  */
	public int getEXME_INPC_ID();

	public I_EXME_INPC getEXME_INPC() throws RuntimeException;

    /** Column name Secuencia */
    public static final String COLUMNNAME_Secuencia = "Secuencia";

	/** Set Sequence.
	  * Sequence
	  */
	public void setSecuencia (int Secuencia);

	/** Get Sequence.
	  * Sequence
	  */
	public int getSecuencia();

    /** Column name Tasa */
    public static final String COLUMNNAME_Tasa = "Tasa";

	/** Set Rate	  */
	public void setTasa (BigDecimal Tasa);

	/** Get Rate	  */
	public BigDecimal getTasa();
}
