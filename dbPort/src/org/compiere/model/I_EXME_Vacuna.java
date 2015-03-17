/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_Vacuna
 *  @author Generated Class 
 *  @version Version @VERSION@
 */
public interface I_EXME_Vacuna 
{

    /** TableName=EXME_Vacuna */
    public static final String Table_Name = "EXME_Vacuna";

    /** AD_Table_ID=1200363 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 7 - All 
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

    /** Column name Cantidad */
    public static final String COLUMNNAME_Cantidad = "Cantidad";

	/** Set Quantity.
	  * Quantity
	  */
	public void setCantidad (BigDecimal Cantidad);

	/** Get Quantity.
	  * Quantity
	  */
	public BigDecimal getCantidad();

    /** Column name CodeCVX */
    public static final String COLUMNNAME_CodeCVX = "CodeCVX";

	/** Set CVX Code	  */
	public void setCodeCVX (String CodeCVX);

	/** Get CVX Code	  */
	public String getCodeCVX();

    /** Column name C_UOM_ID */
    public static final String COLUMNNAME_C_UOM_ID = "C_UOM_ID";

	/** Set UOM.
	  * Unit of Measure
	  */
	public void setC_UOM_ID (int C_UOM_ID);

	/** Get UOM.
	  * Unit of Measure
	  */
	public int getC_UOM_ID();

	public I_C_UOM getC_UOM() throws RuntimeException;

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

    /** Column name EXME_Diagnostico_ID */
    public static final String COLUMNNAME_EXME_Diagnostico_ID = "EXME_Diagnostico_ID";

	/** Set Diagnosis.
	  * Diagnosis
	  */
	public void setEXME_Diagnostico_ID (int EXME_Diagnostico_ID);

	/** Get Diagnosis.
	  * Diagnosis
	  */
	public int getEXME_Diagnostico_ID();

    /** Column name EXME_Diagnostico2_ID */
    public static final String COLUMNNAME_EXME_Diagnostico2_ID = "EXME_Diagnostico2_ID";

	/** Set Second Diagnostic.
	  * Second Diagnostic
	  */
	public void setEXME_Diagnostico2_ID (int EXME_Diagnostico2_ID);

	/** Get Second Diagnostic.
	  * Second Diagnostic
	  */
	public int getEXME_Diagnostico2_ID();

    /** Column name EXME_Diagnostico3_ID */
    public static final String COLUMNNAME_EXME_Diagnostico3_ID = "EXME_Diagnostico3_ID";

	/** Set Third Diagnostic.
	  * Third Diagnostic
	  */
	public void setEXME_Diagnostico3_ID (int EXME_Diagnostico3_ID);

	/** Get Third Diagnostic.
	  * Third Diagnostic
	  */
	public int getEXME_Diagnostico3_ID();

    /** Column name EXME_Diagnostico4_ID */
    public static final String COLUMNNAME_EXME_Diagnostico4_ID = "EXME_Diagnostico4_ID";

	/** Set Fourth Diagnosis	  */
	public void setEXME_Diagnostico4_ID (int EXME_Diagnostico4_ID);

	/** Get Fourth Diagnosis	  */
	public int getEXME_Diagnostico4_ID();

    /** Column name EXME_Diagnostico5_ID */
    public static final String COLUMNNAME_EXME_Diagnostico5_ID = "EXME_Diagnostico5_ID";

	/** Set Fifth Diagnosis	  */
	public void setEXME_Diagnostico5_ID (int EXME_Diagnostico5_ID);

	/** Get Fifth Diagnosis	  */
	public int getEXME_Diagnostico5_ID();

    /** Column name EXME_Vacuna_ID */
    public static final String COLUMNNAME_EXME_Vacuna_ID = "EXME_Vacuna_ID";

	/** Set Vaccine.
	  * Vaccine
	  */
	public void setEXME_Vacuna_ID (int EXME_Vacuna_ID);

	/** Get Vaccine.
	  * Vaccine
	  */
	public int getEXME_Vacuna_ID();

    /** Column name EXME_ViasAdministracion_ID */
    public static final String COLUMNNAME_EXME_ViasAdministracion_ID = "EXME_ViasAdministracion_ID";

	/** Set Route of Administration	  */
	public void setEXME_ViasAdministracion_ID (int EXME_ViasAdministracion_ID);

	/** Get Route of Administration	  */
	public int getEXME_ViasAdministracion_ID();

    /** Column name IncluyeCartilla */
    public static final String COLUMNNAME_IncluyeCartilla = "IncluyeCartilla";

	/** Set Included in Immunization Cards	  */
	public void setIncluyeCartilla (boolean IncluyeCartilla);

	/** Get Included in Immunization Cards	  */
	public boolean isIncluyeCartilla();

    /** Column name M_Product_ID */
    public static final String COLUMNNAME_M_Product_ID = "M_Product_ID";

	/** Set Product.
	  * Product, Service, Item
	  */
	public void setM_Product_ID (int M_Product_ID);

	/** Get Product.
	  * Product, Service, Item
	  */
	public int getM_Product_ID();

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

    /** Column name Rel_Vacuna_ID */
    public static final String COLUMNNAME_Rel_Vacuna_ID = "Rel_Vacuna_ID";

	/** Set Related Vaccine	  */
	public void setRel_Vacuna_ID (int Rel_Vacuna_ID);

	/** Get Related Vaccine	  */
	public int getRel_Vacuna_ID();

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

    /** Column name Via */
    public static final String COLUMNNAME_Via = "Via";

	/** Set Route of Administration.
	  * Route of Administration
	  */
	public void setVia (String Via);

	/** Get Route of Administration.
	  * Route of Administration
	  */
	public String getVia();

    /** Column name VIS_Date */
    public static final String COLUMNNAME_VIS_Date = "VIS_Date";

	/** Set Date on VIS.
	  * Vaccine Information Statement date
	  */
	public void setVIS_Date (Timestamp VIS_Date);

	/** Get Date on VIS.
	  * Vaccine Information Statement date
	  */
	public Timestamp getVIS_Date();
}
