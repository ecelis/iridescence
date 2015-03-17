/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_Grafica
 *  @author Generated Class 
 *  @version Release 1.1.2
 */
public interface I_EXME_Grafica 
{

    /** TableName=EXME_Grafica */
    public static final String Table_Name = "EXME_Grafica";

    /** AD_Table_ID=1201083 */
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

    /** Column name EXME_Grafica_ID */
    public static final String COLUMNNAME_EXME_Grafica_ID = "EXME_Grafica_ID";

	/** Set Graphic	  */
	public void setEXME_Grafica_ID (int EXME_Grafica_ID);

	/** Get Graphic	  */
	public int getEXME_Grafica_ID();

    /** Column name EdadFinal */
    public static final String COLUMNNAME_EdadFinal = "EdadFinal";

	/** Set Year Maximum	  */
	public void setEdadFinal (int EdadFinal);

	/** Get Year Maximum	  */
	public int getEdadFinal();

    /** Column name EdadInicial */
    public static final String COLUMNNAME_EdadInicial = "EdadInicial";

	/** Set Year Minimum	  */
	public void setEdadInicial (int EdadInicial);

	/** Get Year Minimum	  */
	public int getEdadInicial();

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

    /** Column name Sexo */
    public static final String COLUMNNAME_Sexo = "Sexo";

	/** Set Sex.
	  * Sex
	  */
	public void setSexo (String Sexo);

	/** Get Sex.
	  * Sex
	  */
	public String getSexo();

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
