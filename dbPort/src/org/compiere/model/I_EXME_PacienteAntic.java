/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_PacienteAntic
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_PacienteAntic 
{

    /** TableName=EXME_PacienteAntic */
    public static final String Table_Name = "EXME_PacienteAntic";

    /** AD_Table_ID=1200535 */
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

    /** Column name EXME_Anticonceptivo_ID */
    public static final String COLUMNNAME_EXME_Anticonceptivo_ID = "EXME_Anticonceptivo_ID";

	/** Set Contraceptive	  */
	public void setEXME_Anticonceptivo_ID (int EXME_Anticonceptivo_ID);

	/** Get Contraceptive	  */
	public int getEXME_Anticonceptivo_ID();

    /** Column name EXME_PacienteAntic_ID */
    public static final String COLUMNNAME_EXME_PacienteAntic_ID = "EXME_PacienteAntic_ID";

	/** Set Applied Contraceptives	  */
	public void setEXME_PacienteAntic_ID (int EXME_PacienteAntic_ID);

	/** Get Applied Contraceptives	  */
	public int getEXME_PacienteAntic_ID();

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

    /** Column name FechaAplica */
    public static final String COLUMNNAME_FechaAplica = "FechaAplica";

	/** Set Date of Application.
	  * Date of Application
	  */
	public void setFechaAplica (Timestamp FechaAplica);

	/** Get Date of Application.
	  * Date of Application
	  */
	public Timestamp getFechaAplica();

    /** Column name FechaRetiro */
    public static final String COLUMNNAME_FechaRetiro = "FechaRetiro";

	/** Set Retirement Date	  */
	public void setFechaRetiro (Timestamp FechaRetiro);

	/** Get Retirement Date	  */
	public Timestamp getFechaRetiro();

    /** Column name FechaVencimiento */
    public static final String COLUMNNAME_FechaVencimiento = "FechaVencimiento";

	/** Set Terminate Date	  */
	public void setFechaVencimiento (Timestamp FechaVencimiento);

	/** Get Terminate Date	  */
	public Timestamp getFechaVencimiento();

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
