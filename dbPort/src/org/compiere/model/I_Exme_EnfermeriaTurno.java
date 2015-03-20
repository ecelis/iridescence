/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for Exme_EnfermeriaTurno
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_Exme_EnfermeriaTurno 
{

    /** TableName=Exme_EnfermeriaTurno */
    public static final String Table_Name = "Exme_EnfermeriaTurno";

    /** AD_Table_ID=1200623 */
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

    /** Column name EXME_Enfermeria_ID */
    public static final String COLUMNNAME_EXME_Enfermeria_ID = "EXME_Enfermeria_ID";

	/** Set Infirmary staff.
	  * Infirmary staff
	  */
	public void setEXME_Enfermeria_ID (int EXME_Enfermeria_ID);

	/** Get Infirmary staff.
	  * Infirmary staff
	  */
	public int getEXME_Enfermeria_ID();

	public I_EXME_Enfermeria getEXME_Enfermeria() throws RuntimeException;

    /** Column name Exme_EnfermeriaTurno_ID */
    public static final String COLUMNNAME_Exme_EnfermeriaTurno_ID = "Exme_EnfermeriaTurno_ID";

	/** Set Nursing Turn	  */
	public void setExme_EnfermeriaTurno_ID (int Exme_EnfermeriaTurno_ID);

	/** Get Nursing Turn	  */
	public int getExme_EnfermeriaTurno_ID();

    /** Column name Turno */
    public static final String COLUMNNAME_Turno = "Turno";

	/** Set Turn	  */
	public void setTurno (String Turno);

	/** Get Turn	  */
	public String getTurno();

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
