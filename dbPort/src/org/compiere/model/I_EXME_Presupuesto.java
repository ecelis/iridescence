/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_Presupuesto
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_Presupuesto 
{

    /** TableName=EXME_Presupuesto */
    public static final String Table_Name = "EXME_Presupuesto";

    /** AD_Table_ID=1200331 */
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

    /** Column name EXME_ConceptoFin_ID */
    public static final String COLUMNNAME_EXME_ConceptoFin_ID = "EXME_ConceptoFin_ID";

	/** Set Financial Concept	  */
	public void setEXME_ConceptoFin_ID (int EXME_ConceptoFin_ID);

	/** Get Financial Concept	  */
	public int getEXME_ConceptoFin_ID();

	public I_EXME_ConceptoFin getEXME_ConceptoFin() throws RuntimeException;

    /** Column name EXME_Presupuesto_ID */
    public static final String COLUMNNAME_EXME_Presupuesto_ID = "EXME_Presupuesto_ID";

	/** Set Budget	  */
	public void setEXME_Presupuesto_ID (int EXME_Presupuesto_ID);

	/** Get Budget	  */
	public int getEXME_Presupuesto_ID();

    /** Column name EXME_ProgramaInv_ID */
    public static final String COLUMNNAME_EXME_ProgramaInv_ID = "EXME_ProgramaInv_ID";

	/** Set Research Program	  */
	public void setEXME_ProgramaInv_ID (int EXME_ProgramaInv_ID);

	/** Get Research Program	  */
	public int getEXME_ProgramaInv_ID();

	public I_EXME_ProgramaInv getEXME_ProgramaInv() throws RuntimeException;

    /** Column name EXME_ProyectoInv_ID */
    public static final String COLUMNNAME_EXME_ProyectoInv_ID = "EXME_ProyectoInv_ID";

	/** Set Research Project	  */
	public void setEXME_ProyectoInv_ID (int EXME_ProyectoInv_ID);

	/** Get Research Project	  */
	public int getEXME_ProyectoInv_ID();

	public I_EXME_ProyectoInv getEXME_ProyectoInv() throws RuntimeException;

    /** Column name Type */
    public static final String COLUMNNAME_Type = "Type";

	/** Set Type.
	  * Type of Validation (SQL, Java Script, Java Language)
	  */
	public void setType (String Type);

	/** Get Type.
	  * Type of Validation (SQL, Java Script, Java Language)
	  */
	public String getType();
}
