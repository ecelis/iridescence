/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for I_EXME_Vacuna
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_I_EXME_Vacuna 
{

    /** TableName=I_EXME_Vacuna */
    public static final String Table_Name = "I_EXME_Vacuna";

    /** AD_Table_ID=1200487 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 7 - System - Client - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(7);

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

    /** Column name C_UOM_Det_ID */
    public static final String COLUMNNAME_C_UOM_Det_ID = "C_UOM_Det_ID";

	/** Set UOM Det	  */
	public void setC_UOM_Det_ID (int C_UOM_Det_ID);

	/** Get UOM Det	  */
	public int getC_UOM_Det_ID();

    /** Column name C_UOM_Det_Value */
    public static final String COLUMNNAME_C_UOM_Det_Value = "C_UOM_Det_Value";

	/** Set Value UOM	  */
	public void setC_UOM_Det_Value (String C_UOM_Det_Value);

	/** Get Value UOM	  */
	public String getC_UOM_Det_Value();

    /** Column name C_UOM_Hrd_ID */
    public static final String COLUMNNAME_C_UOM_Hrd_ID = "C_UOM_Hrd_ID";

	/** Set UOM Vaccine	  */
	public void setC_UOM_Hrd_ID (int C_UOM_Hrd_ID);

	/** Get UOM Vaccine	  */
	public int getC_UOM_Hrd_ID();

    /** Column name C_UOM_Hrd_Value */
    public static final String COLUMNNAME_C_UOM_Hrd_Value = "C_UOM_Hrd_Value";

	/** Set Value UOM Vaccine	  */
	public void setC_UOM_Hrd_Value (String C_UOM_Hrd_Value);

	/** Get Value UOM Vaccine	  */
	public String getC_UOM_Hrd_Value();

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

    /** Column name EdadMaxima */
    public static final String COLUMNNAME_EdadMaxima = "EdadMaxima";

	/** Set Maximum age	  */
	public void setEdadMaxima (BigDecimal EdadMaxima);

	/** Get Maximum age	  */
	public BigDecimal getEdadMaxima();

    /** Column name EdadMinima */
    public static final String COLUMNNAME_EdadMinima = "EdadMinima";

	/** Set Minimum age	  */
	public void setEdadMinima (BigDecimal EdadMinima);

	/** Get Minimum age	  */
	public BigDecimal getEdadMinima();

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

	public I_EXME_Diagnostico getEXME_Diagnostico() throws RuntimeException;

    /** Column name EXME_Diagnostico_Value */
    public static final String COLUMNNAME_EXME_Diagnostico_Value = "EXME_Diagnostico_Value";

	/** Set Diagnostic.
	  * Diagnostic
	  */
	public void setEXME_Diagnostico_Value (String EXME_Diagnostico_Value);

	/** Get Diagnostic.
	  * Diagnostic
	  */
	public String getEXME_Diagnostico_Value();

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

    /** Column name EXME_Diagnostico2_Value */
    public static final String COLUMNNAME_EXME_Diagnostico2_Value = "EXME_Diagnostico2_Value";

	/** Set Value Diagnosis 2	  */
	public void setEXME_Diagnostico2_Value (String EXME_Diagnostico2_Value);

	/** Get Value Diagnosis 2	  */
	public String getEXME_Diagnostico2_Value();

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

    /** Column name EXME_Diagnostico3_Value */
    public static final String COLUMNNAME_EXME_Diagnostico3_Value = "EXME_Diagnostico3_Value";

	/** Set Value Diagnosis 3	  */
	public void setEXME_Diagnostico3_Value (String EXME_Diagnostico3_Value);

	/** Get Value Diagnosis 3	  */
	public String getEXME_Diagnostico3_Value();

    /** Column name EXME_Diagnostico4_ID */
    public static final String COLUMNNAME_EXME_Diagnostico4_ID = "EXME_Diagnostico4_ID";

	/** Set Fourth Diagnosis	  */
	public void setEXME_Diagnostico4_ID (int EXME_Diagnostico4_ID);

	/** Get Fourth Diagnosis	  */
	public int getEXME_Diagnostico4_ID();

    /** Column name EXME_Diagnostico4_Value */
    public static final String COLUMNNAME_EXME_Diagnostico4_Value = "EXME_Diagnostico4_Value";

	/** Set Value Diagnosis 4	  */
	public void setEXME_Diagnostico4_Value (String EXME_Diagnostico4_Value);

	/** Get Value Diagnosis 4	  */
	public String getEXME_Diagnostico4_Value();

    /** Column name EXME_Diagnostico5_ID */
    public static final String COLUMNNAME_EXME_Diagnostico5_ID = "EXME_Diagnostico5_ID";

	/** Set Fifth Diagnosis	  */
	public void setEXME_Diagnostico5_ID (int EXME_Diagnostico5_ID);

	/** Get Fifth Diagnosis	  */
	public int getEXME_Diagnostico5_ID();

    /** Column name EXME_Diagnostico5_Value */
    public static final String COLUMNNAME_EXME_Diagnostico5_Value = "EXME_Diagnostico5_Value";

	/** Set Value Diagnosis 5	  */
	public void setEXME_Diagnostico5_Value (String EXME_Diagnostico5_Value);

	/** Get Value Diagnosis 5	  */
	public String getEXME_Diagnostico5_Value();

    /** Column name EXME_VacunaDet_ID */
    public static final String COLUMNNAME_EXME_VacunaDet_ID = "EXME_VacunaDet_ID";

	/** Set Detail immunization.
	  * Detail immunization
	  */
	public void setEXME_VacunaDet_ID (int EXME_VacunaDet_ID);

	/** Get Detail immunization.
	  * Detail immunization
	  */
	public int getEXME_VacunaDet_ID();

	public I_EXME_VacunaDet getEXME_VacunaDet() throws RuntimeException;

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

	public I_EXME_Vacuna getEXME_Vacuna() throws RuntimeException;

    /** Column name I_ErrorMsg */
    public static final String COLUMNNAME_I_ErrorMsg = "I_ErrorMsg";

	/** Set Import Error Message.
	  * Messages generated from import process
	  */
	public void setI_ErrorMsg (String I_ErrorMsg);

	/** Get Import Error Message.
	  * Messages generated from import process
	  */
	public String getI_ErrorMsg();

    /** Column name I_EXME_Vacuna_ID */
    public static final String COLUMNNAME_I_EXME_Vacuna_ID = "I_EXME_Vacuna_ID";

	/** Set Import Vaccine	  */
	public void setI_EXME_Vacuna_ID (int I_EXME_Vacuna_ID);

	/** Get Import Vaccine	  */
	public int getI_EXME_Vacuna_ID();

    /** Column name I_IsImported */
    public static final String COLUMNNAME_I_IsImported = "I_IsImported";

	/** Set Imported.
	  * Has this import been processed
	  */
	public void setI_IsImported (boolean I_IsImported);

	/** Get Imported.
	  * Has this import been processed
	  */
	public boolean isI_IsImported();

    /** Column name IncluyeCartilla */
    public static final String COLUMNNAME_IncluyeCartilla = "IncluyeCartilla";

	/** Set Included in Immunization Cards	  */
	public void setIncluyeCartilla (boolean IncluyeCartilla);

	/** Get Included in Immunization Cards	  */
	public boolean isIncluyeCartilla();

    /** Column name Intervalo */
    public static final String COLUMNNAME_Intervalo = "Intervalo";

	/** Set Interval.
	  * Interval
	  */
	public void setIntervalo (BigDecimal Intervalo);

	/** Get Interval.
	  * Interval
	  */
	public BigDecimal getIntervalo();

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

	public I_M_Product getM_Product() throws RuntimeException;

    /** Column name M_Product_Value */
    public static final String COLUMNNAME_M_Product_Value = "M_Product_Value";

	/** Set Product Code.
	  * product search Code
	  */
	public void setM_Product_Value (String M_Product_Value);

	/** Get Product Code.
	  * product search Code
	  */
	public String getM_Product_Value();

    /** Column name Processed */
    public static final String COLUMNNAME_Processed = "Processed";

	/** Set Processed.
	  * The document has been processed
	  */
	public void setProcessed (boolean Processed);

	/** Get Processed.
	  * The document has been processed
	  */
	public boolean isProcessed();

    /** Column name Processing */
    public static final String COLUMNNAME_Processing = "Processing";

	/** Set Process Now	  */
	public void setProcessing (boolean Processing);

	/** Get Process Now	  */
	public boolean isProcessing();

    /** Column name Rel_Vacuna_ID */
    public static final String COLUMNNAME_Rel_Vacuna_ID = "Rel_Vacuna_ID";

	/** Set Related Vaccine	  */
	public void setRel_Vacuna_ID (int Rel_Vacuna_ID);

	/** Get Related Vaccine	  */
	public int getRel_Vacuna_ID();

    /** Column name Rel_Vacuna_Value */
    public static final String COLUMNNAME_Rel_Vacuna_Value = "Rel_Vacuna_Value";

	/** Set Code of vaccine related 	  */
	public void setRel_Vacuna_Value (String Rel_Vacuna_Value);

	/** Get Code of vaccine related 	  */
	public String getRel_Vacuna_Value();

    /** Column name Secuencia */
    public static final String COLUMNNAME_Secuencia = "Secuencia";

	/** Set Sequence.
	  * Sequence
	  */
	public void setSecuencia (BigDecimal Secuencia);

	/** Get Sequence.
	  * Sequence
	  */
	public BigDecimal getSecuencia();

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

    /** Column name TipoDosis */
    public static final String COLUMNNAME_TipoDosis = "TipoDosis";

	/** Set Dose rate	  */
	public void setTipoDosis (String TipoDosis);

	/** Get Dose rate	  */
	public String getTipoDosis();

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
}
