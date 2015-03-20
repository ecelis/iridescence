/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for PHR_PacienteAler
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_PHR_PacienteAler 
{

    /** TableName=PHR_PacienteAler */
    public static final String Table_Name = "PHR_PacienteAler";

    /** AD_Table_ID=1200928 */
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

    /** Column name EXME_Alergia_ID */
    public static final String COLUMNNAME_EXME_Alergia_ID = "EXME_Alergia_ID";

	/** Set Alergy.
	  * Alergy
	  */
	public void setEXME_Alergia_ID (int EXME_Alergia_ID);

	/** Get Alergy.
	  * Alergy
	  */
	public int getEXME_Alergia_ID();

	public I_EXME_Alergia getEXME_Alergia() throws RuntimeException;

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

    /** Column name EXME_SActiva_ID */
    public static final String COLUMNNAME_EXME_SActiva_ID = "EXME_SActiva_ID";

	/** Set Active Substance.
	  * Active Substance
	  */
	public void setEXME_SActiva_ID (int EXME_SActiva_ID);

	/** Get Active Substance.
	  * Active Substance
	  */
	public int getEXME_SActiva_ID();

	public I_EXME_SActiva getEXME_SActiva() throws RuntimeException;

    /** Column name FechaDiagnostico */
    public static final String COLUMNNAME_FechaDiagnostico = "FechaDiagnostico";

	/** Set Date of Diagnosis.
	  * Date of Diagnosis
	  */
	public void setFechaDiagnostico (Timestamp FechaDiagnostico);

	/** Get Date of Diagnosis.
	  * Date of Diagnosis
	  */
	public Timestamp getFechaDiagnostico();

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

    /** Column name PHR_PacienteAler_ID */
    public static final String COLUMNNAME_PHR_PacienteAler_ID = "PHR_PacienteAler_ID";

	/** Set Allergies	  */
	public void setPHR_PacienteAler_ID (int PHR_PacienteAler_ID);

	/** Get Allergies	  */
	public int getPHR_PacienteAler_ID();

    /** Column name Reaccion */
    public static final String COLUMNNAME_Reaccion = "Reaccion";

	/** Set Reaction	  */
	public void setReaccion (String Reaccion);

	/** Get Reaction	  */
	public String getReaccion();

    /** Column name TipoAlergia */
    public static final String COLUMNNAME_TipoAlergia = "TipoAlergia";

	/** Set Alergy Type.
	  * Alergy Type
	  */
	public void setTipoAlergia (String TipoAlergia);

	/** Get Alergy Type.
	  * Alergy Type
	  */
	public String getTipoAlergia();
}
