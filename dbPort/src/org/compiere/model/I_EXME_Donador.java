/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_Donador
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_Donador 
{

    /** TableName=EXME_Donador */
    public static final String Table_Name = "EXME_Donador";

    /** AD_Table_ID=1200270 */
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

    /** Column name Apellido1 */
    public static final String COLUMNNAME_Apellido1 = "Apellido1";

	/** Set Last Name.
	  * Last Name
	  */
	public void setApellido1 (String Apellido1);

	/** Get Last Name.
	  * Last Name
	  */
	public String getApellido1();

    /** Column name Apellido2 */
    public static final String COLUMNNAME_Apellido2 = "Apellido2";

	/** Set Mother's Maiden Name.
	  * Mother's Maiden Name
	  */
	public void setApellido2 (String Apellido2);

	/** Get Mother's Maiden Name.
	  * Mother's Maiden Name
	  */
	public String getApellido2();

    /** Column name Caso */
    public static final String COLUMNNAME_Caso = "Caso";

	/** Set Caso.
	  * Donor Initials
	  */
	public void setCaso (String Caso);

	/** Get Caso.
	  * Donor Initials
	  */
	public String getCaso();

    /** Column name Curp */
    public static final String COLUMNNAME_Curp = "Curp";

	/** Set National Identification Number.
	  * National Identification Number
	  */
	public void setCurp (String Curp);

	/** Get National Identification Number.
	  * National Identification Number
	  */
	public String getCurp();

    /** Column name Edad */
    public static final String COLUMNNAME_Edad = "Edad";

	/** Set Age.
	  * Age
	  */
	public void setEdad (BigDecimal Edad);

	/** Get Age.
	  * Age
	  */
	public BigDecimal getEdad();

    /** Column name EXME_CausaMuerte_ID */
    public static final String COLUMNNAME_EXME_CausaMuerte_ID = "EXME_CausaMuerte_ID";

	/** Set Death Cause.
	  * Death Cause
	  */
	public void setEXME_CausaMuerte_ID (int EXME_CausaMuerte_ID);

	/** Get Death Cause.
	  * Death Cause
	  */
	public int getEXME_CausaMuerte_ID();

	public I_EXME_CausaMuerte getEXME_CausaMuerte() throws RuntimeException;

    /** Column name EXME_Donador_ID */
    public static final String COLUMNNAME_EXME_Donador_ID = "EXME_Donador_ID";

	/** Set Donor	  */
	public void setEXME_Donador_ID (int EXME_Donador_ID);

	/** Get Donor	  */
	public int getEXME_Donador_ID();

    /** Column name EXME_Medico_ID */
    public static final String COLUMNNAME_EXME_Medico_ID = "EXME_Medico_ID";

	/** Set Doctor.
	  * Doctor
	  */
	public void setEXME_Medico_ID (int EXME_Medico_ID);

	/** Get Doctor.
	  * Doctor
	  */
	public int getEXME_Medico_ID();

	public I_EXME_Medico getEXME_Medico() throws RuntimeException;

    /** Column name EXME_Organos_Tejidos_ID */
    public static final String COLUMNNAME_EXME_Organos_Tejidos_ID = "EXME_Organos_Tejidos_ID";

	/** Set Organs/Tissues .
	  * ID de table organs and tissues
	  */
	public void setEXME_Organos_Tejidos_ID (int EXME_Organos_Tejidos_ID);

	/** Get Organs/Tissues .
	  * ID de table organs and tissues
	  */
	public int getEXME_Organos_Tejidos_ID();

	public I_EXME_Organos_Tejidos getEXME_Organos_Tejidos() throws RuntimeException;

    /** Column name Fecha_Extraccion */
    public static final String COLUMNNAME_Fecha_Extraccion = "Fecha_Extraccion";

	/** Set Date Extraction.
	  * Date of organ harvesting
	  */
	public void setFecha_Extraccion (Timestamp Fecha_Extraccion);

	/** Get Date Extraction.
	  * Date of organ harvesting
	  */
	public Timestamp getFecha_Extraccion();

    /** Column name FechaMuerte */
    public static final String COLUMNNAME_FechaMuerte = "FechaMuerte";

	/** Set Death's Date.
	  * Death's Date
	  */
	public void setFechaMuerte (Timestamp FechaMuerte);

	/** Get Death's Date.
	  * Death's Date
	  */
	public Timestamp getFechaMuerte();

    /** Column name Fecha_Trasplante */
    public static final String COLUMNNAME_Fecha_Trasplante = "Fecha_Trasplante";

	/** Set Transplant Date.
	  * Transplant Date
	  */
	public void setFecha_Trasplante (Timestamp Fecha_Trasplante);

	/** Get Transplant Date.
	  * Transplant Date
	  */
	public Timestamp getFecha_Trasplante();

    /** Column name Hospital_Origen */
    public static final String COLUMNNAME_Hospital_Origen = "Hospital_Origen";

	/** Set Source Hospital.
	  * Source Hospital
	  */
	public void setHospital_Origen (String Hospital_Origen);

	/** Get Source Hospital.
	  * Source Hospital
	  */
	public String getHospital_Origen();

    /** Column name IsHospital */
    public static final String COLUMNNAME_IsHospital = "IsHospital";

	/** Set IsHospital.
	  * Determines whether the physician is the hospital
	  */
	public void setIsHospital (boolean IsHospital);

	/** Get IsHospital.
	  * Determines whether the physician is the hospital
	  */
	public boolean isHospital();

    /** Column name Medico_Externo */
    public static final String COLUMNNAME_Medico_Externo = "Medico_Externo";

	/** Set External Medical.
	  * External Medical
	  */
	public void setMedico_Externo (String Medico_Externo);

	/** Get External Medical.
	  * External Medical
	  */
	public String getMedico_Externo();

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

    /** Column name Nombre2 */
    public static final String COLUMNNAME_Nombre2 = "Nombre2";

	/** Set Middle Name.
	  * Middle name
	  */
	public void setNombre2 (String Nombre2);

	/** Get Middle Name.
	  * Middle name
	  */
	public String getNombre2();

    /** Column name Procedencia */
    public static final String COLUMNNAME_Procedencia = "Procedencia";

	/** Set Provanance.
	  * Sets from which the active 
	  */
	public void setProcedencia (String Procedencia);

	/** Get Provanance.
	  * Sets from which the active 
	  */
	public String getProcedencia();

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

    /** Column name Valoracion */
    public static final String COLUMNNAME_Valoracion = "Valoracion";

	/** Set Valuation	  */
	public void setValoracion (String Valoracion);

	/** Get Valuation	  */
	public String getValoracion();

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
