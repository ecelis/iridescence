/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_MO_DentalProblems
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_MO_DentalProblems 
{

    /** TableName=EXME_MO_DentalProblems */
    public static final String Table_Name = "EXME_MO_DentalProblems";

    /** AD_Table_ID=1200578 */
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

    /** Column name Color1 */
    public static final String COLUMNNAME_Color1 = "Color1";

	/** Set Color 1	  */
	public void setColor1 (String Color1);

	/** Get Color 1	  */
	public String getColor1();

    /** Column name Color2 */
    public static final String COLUMNNAME_Color2 = "Color2";

	/** Set Color 2	  */
	public void setColor2 (String Color2);

	/** Get Color 2	  */
	public String getColor2();

    /** Column name Color3 */
    public static final String COLUMNNAME_Color3 = "Color3";

	/** Set Color 3	  */
	public void setColor3 (String Color3);

	/** Get Color 3	  */
	public String getColor3();

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

    /** Column name Diagrama */
    public static final String COLUMNNAME_Diagrama = "Diagrama";

	/** Set Diagram	  */
	public void setDiagrama (boolean Diagrama);

	/** Get Diagram	  */
	public boolean isDiagrama();

    /** Column name EsOdontograma */
    public static final String COLUMNNAME_EsOdontograma = "EsOdontograma";

	/** Set Is Odontogram.
	  * Is Odontogram
	  */
	public void setEsOdontograma (boolean EsOdontograma);

	/** Get Is Odontogram.
	  * Is Odontogram
	  */
	public boolean isEsOdontograma();

    /** Column name EXME_MO_DentalProblems_ID */
    public static final String COLUMNNAME_EXME_MO_DentalProblems_ID = "EXME_MO_DentalProblems_ID";

	/** Set Dental Problem	  */
	public void setEXME_MO_DentalProblems_ID (int EXME_MO_DentalProblems_ID);

	/** Get Dental Problem	  */
	public int getEXME_MO_DentalProblems_ID();

    /** Column name Imagen */
    public static final String COLUMNNAME_Imagen = "Imagen";

	/** Set Image.
	  * Name of stored image
	  */
	public void setImagen (String Imagen);

	/** Get Image.
	  * Name of stored image
	  */
	public String getImagen();

    /** Column name IsNormal */
    public static final String COLUMNNAME_IsNormal = "IsNormal";

	/** Set IsNormal	  */
	public void setIsNormal (boolean IsNormal);

	/** Get IsNormal	  */
	public boolean isNormal();

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
