/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_EsqInsulina
 *  @author Generated Class 
 *  @version Version @VERSION@
 */
public interface I_EXME_EsqInsulina 
{

    /** TableName=EXME_EsqInsulina */
    public static final String Table_Name = "EXME_EsqInsulina";

    /** AD_Table_ID=1200463 */
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

    /** Column name EXME_CtaPac_ID */
    public static final String COLUMNNAME_EXME_CtaPac_ID = "EXME_CtaPac_ID";

	/** Set Encounter.
	  * Encounter
	  */
	public void setEXME_CtaPac_ID (int EXME_CtaPac_ID);

	/** Get Encounter.
	  * Encounter
	  */
	public int getEXME_CtaPac_ID();

	public I_EXME_CtaPac getEXME_CtaPac() throws RuntimeException;

    /** Column name EXME_EsqInsulina_ID */
    public static final String COLUMNNAME_EXME_EsqInsulina_ID = "EXME_EsqInsulina_ID";

	/** Set Insulin Scheme	  */
	public void setEXME_EsqInsulina_ID (int EXME_EsqInsulina_ID);

	/** Get Insulin Scheme	  */
	public int getEXME_EsqInsulina_ID();

    /** Column name EXME_GenProduct_ID */
    public static final String COLUMNNAME_EXME_GenProduct_ID = "EXME_GenProduct_ID";

	/** Set Generic Product	  */
	public void setEXME_GenProduct_ID (int EXME_GenProduct_ID);

	/** Get Generic Product	  */
	public int getEXME_GenProduct_ID();

	public I_EXME_GenProduct getEXME_GenProduct() throws RuntimeException;

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

    /** Column name Folio */
    public static final String COLUMNNAME_Folio = "Folio";

	/** Set Folio	  */
	public void setFolio (int Folio);

	/** Get Folio	  */
	public int getFolio();

    /** Column name Instructions */
    public static final String COLUMNNAME_Instructions = "Instructions";

	/** Set Instructions	  */
	public void setInstructions (String Instructions);

	/** Get Instructions	  */
	public String getInstructions();

    /** Column name Lim_Inferior */
    public static final String COLUMNNAME_Lim_Inferior = "Lim_Inferior";

	/** Set Minimum Level	  */
	public void setLim_Inferior (int Lim_Inferior);

	/** Get Minimum Level	  */
	public int getLim_Inferior();

    /** Column name Lim_Superior */
    public static final String COLUMNNAME_Lim_Superior = "Lim_Superior";

	/** Set Maximum Level	  */
	public void setLim_Superior (int Lim_Superior);

	/** Get Maximum Level	  */
	public int getLim_Superior();

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

    /** Column name Tipo */
    public static final String COLUMNNAME_Tipo = "Tipo";

	/** Set Type	  */
	public void setTipo (String Tipo);

	/** Get Type	  */
	public String getTipo();

    /** Column name Unidad */
    public static final String COLUMNNAME_Unidad = "Unidad";

	/** Set Unity	  */
	public void setUnidad (int Unidad);

	/** Get Unity	  */
	public int getUnidad();
}
