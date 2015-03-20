/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_MO_Expediente
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_MO_Expediente 
{

    /** TableName=EXME_MO_Expediente */
    public static final String Table_Name = "EXME_MO_Expediente";

    /** AD_Table_ID=1200389 */
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

    /** Column name AusenciaDiente */
    public static final String COLUMNNAME_AusenciaDiente = "AusenciaDiente";

	/** Set absence tooth	  */
	public void setAusenciaDiente (boolean AusenciaDiente);

	/** Get absence tooth	  */
	public boolean isAusenciaDiente();

    /** Column name EsAdulto */
    public static final String COLUMNNAME_EsAdulto = "EsAdulto";

	/** Set is adult	  */
	public void setEsAdulto (boolean EsAdulto);

	/** Get is adult	  */
	public boolean isEsAdulto();

    /** Column name EsCalculo */
    public static final String COLUMNNAME_EsCalculo = "EsCalculo";

	/** Set Is Calculus	  */
	public void setEsCalculo (boolean EsCalculo);

	/** Get Is Calculus	  */
	public boolean isEsCalculo();

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

    /** Column name EsSupuracion */
    public static final String COLUMNNAME_EsSupuracion = "EsSupuracion";

	/** Set IsSuppuration	  */
	public void setEsSupuracion (boolean EsSupuracion);

	/** Get IsSuppuration	  */
	public boolean isEsSupuracion();

    /** Column name EXME_MO_Expediente_ID */
    public static final String COLUMNNAME_EXME_MO_Expediente_ID = "EXME_MO_Expediente_ID";

	/** Set MO Record	  */
	public void setEXME_MO_Expediente_ID (int EXME_MO_Expediente_ID);

	/** Get MO Record	  */
	public int getEXME_MO_Expediente_ID();

    /** Column name EXME_MO_PiezaDental_ID */
    public static final String COLUMNNAME_EXME_MO_PiezaDental_ID = "EXME_MO_PiezaDental_ID";

	/** Set Dental Piece	  */
	public void setEXME_MO_PiezaDental_ID (int EXME_MO_PiezaDental_ID);

	/** Get Dental Piece	  */
	public int getEXME_MO_PiezaDental_ID();

	public I_EXME_MO_PiezaDental getEXME_MO_PiezaDental() throws RuntimeException;

    /** Column name EXME_Paciente_ID */
    public static final String COLUMNNAME_EXME_Paciente_ID = "EXME_Paciente_ID";

	/** Set Patient.
	  * Patient
	  */
	public void setEXME_Paciente_ID (int EXME_Paciente_ID);

	/** Get Patient.
	  * Patient
	  */
	public int getEXME_Paciente_ID();

	public I_EXME_Paciente getEXME_Paciente() throws RuntimeException;

    /** Column name Placa */
    public static final String COLUMNNAME_Placa = "Placa";

	/** Set plate	  */
	public void setPlaca (boolean Placa);

	/** Get plate	  */
	public boolean isPlaca();

    /** Column name Sangrado */
    public static final String COLUMNNAME_Sangrado = "Sangrado";

	/** Set Bleeding	  */
	public void setSangrado (boolean Sangrado);

	/** Get Bleeding	  */
	public boolean isSangrado();
}
