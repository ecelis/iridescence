/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_MensajePregunta
 *  @author Generated Class 
 *  @version Version @VERSION@
 */
public interface I_EXME_MensajePregunta 
{

    /** TableName=EXME_MensajePregunta */
    public static final String Table_Name = "EXME_MensajePregunta";

    /** AD_Table_ID=1201308 */
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

    /** Column name EXME_MensajePregunta_ID */
    public static final String COLUMNNAME_EXME_MensajePregunta_ID = "EXME_MensajePregunta_ID";

	/** Set Question Message	  */
	public void setEXME_MensajePregunta_ID (int EXME_MensajePregunta_ID);

	/** Get Question Message	  */
	public int getEXME_MensajePregunta_ID();

    /** Column name EXME_Pregunta_ID */
    public static final String COLUMNNAME_EXME_Pregunta_ID = "EXME_Pregunta_ID";

	/** Set Question.
	  * Question
	  */
	public void setEXME_Pregunta_ID (int EXME_Pregunta_ID);

	/** Get Question.
	  * Question
	  */
	public int getEXME_Pregunta_ID();

	public I_EXME_Pregunta getEXME_Pregunta() throws RuntimeException;

    /** Column name FinalValue */
    public static final String COLUMNNAME_FinalValue = "FinalValue";

	/** Set Final Value	  */
	public void setFinalValue (BigDecimal FinalValue);

	/** Get Final Value	  */
	public BigDecimal getFinalValue();

    /** Column name InitialValue */
    public static final String COLUMNNAME_InitialValue = "InitialValue";

	/** Set Initial Value	  */
	public void setInitialValue (BigDecimal InitialValue);

	/** Get Initial Value	  */
	public BigDecimal getInitialValue();

    /** Column name Message */
    public static final String COLUMNNAME_Message = "Message";

	/** Set Message.
	  * EMail Message
	  */
	public void setMessage (String Message);

	/** Get Message.
	  * EMail Message
	  */
	public String getMessage();
}
