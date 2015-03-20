/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_Expediente_Historia_V
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_Expediente_Historia_V 
{

    /** TableName=EXME_Expediente_Historia_V */
    public static final String Table_Name = "EXME_Expediente_Historia_V";

    /** AD_Table_ID=1200081 */
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

    /** Column name CtaPac */
    public static final String COLUMNNAME_CtaPac = "CtaPac";

	/** Set Patient Account.
	  * Patient Account
	  */
	public void setCtaPac (String CtaPac);

	/** Get Patient Account.
	  * Patient Account
	  */
	public String getCtaPac();

    /** Column name EXME_Expediente_Historia_V_ID */
    public static final String COLUMNNAME_EXME_Expediente_Historia_V_ID = "EXME_Expediente_Historia_V_ID";

	/** Set File - History.
	  * File - History
	  */
	public void setEXME_Expediente_Historia_V_ID (int EXME_Expediente_Historia_V_ID);

	/** Get File - History.
	  * File - History
	  */
	public int getEXME_Expediente_Historia_V_ID();

    /** Column name Expediente */
    public static final String COLUMNNAME_Expediente = "Expediente";

	/** Set Medical Record.
	  * Medical Record
	  */
	public void setExpediente (int Expediente);

	/** Get Medical Record.
	  * Medical Record
	  */
	public int getExpediente();

    /** Column name Historia */
    public static final String COLUMNNAME_Historia = "Historia";

	/** Set Unique Patient Identification.
	  * Unique Patient Identification
	  */
	public void setHistoria (String Historia);

	/** Get Unique Patient Identification.
	  * Unique Patient Identification
	  */
	public String getHistoria();

    /** Column name IsPrestado */
    public static final String COLUMNNAME_IsPrestado = "IsPrestado";

	/** Set Is On Loan.
	  * Is On Loan
	  */
	public void setIsPrestado (boolean IsPrestado);

	/** Get Is On Loan.
	  * Is On Loan
	  */
	public boolean isPrestado();

    /** Column name Nombre_Pac */
    public static final String COLUMNNAME_Nombre_Pac = "Nombre_Pac";

	/** Set Patient Name.
	  * Patient Name
	  */
	public void setNombre_Pac (String Nombre_Pac);

	/** Get Patient Name.
	  * Patient Name
	  */
	public String getNombre_Pac();
}
