/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_VacunaDet
 *  @author Generated Class 
 *  @version Version @VERSION@
 */
public interface I_EXME_VacunaDet 
{

    /** TableName=EXME_VacunaDet */
    public static final String Table_Name = "EXME_VacunaDet";

    /** AD_Table_ID=1200364 */
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

    /** Column name TipoDosis */
    public static final String COLUMNNAME_TipoDosis = "TipoDosis";

	/** Set Dose rate	  */
	public void setTipoDosis (String TipoDosis);

	/** Get Dose rate	  */
	public String getTipoDosis();

    /** Column name VaccineType */
    public static final String COLUMNNAME_VaccineType = "VaccineType";

	/** Set Type of Vaccine.
	  * Type of Vaccine (generic abreviation)
	  */
	public void setVaccineType (String VaccineType);

	/** Get Type of Vaccine.
	  * Type of Vaccine (generic abreviation)
	  */
	public String getVaccineType();
}
