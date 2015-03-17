/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_Tratamientos
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_Tratamientos 
{

    /** TableName=EXME_Tratamientos */
    public static final String Table_Name = "EXME_Tratamientos";

    /** AD_Table_ID=1200371 */
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

    /** Column name Can_Duracion */
    public static final String COLUMNNAME_Can_Duracion = "Can_Duracion";

	/** Set Duration Amount	  */
	public void setCan_Duracion (int Can_Duracion);

	/** Get Duration Amount	  */
	public int getCan_Duracion();

    /** Column name Code */
    public static final String COLUMNNAME_Code = "Code";

	/** Set Validation code.
	  * Validation Code
	  */
	public void setCode (String Code);

	/** Get Validation code.
	  * Validation Code
	  */
	public String getCode();

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

    /** Column name Duration */
    public static final String COLUMNNAME_Duration = "Duration";

	/** Set Duration.
	  * Normal Duration in Duration Unit
	  */
	public void setDuration (String Duration);

	/** Get Duration.
	  * Normal Duration in Duration Unit
	  */
	public String getDuration();

    /** Column name Esq_Precio */
    public static final String COLUMNNAME_Esq_Precio = "Esq_Precio";

	/** Set Price Scheme	  */
	public void setEsq_Precio (String Esq_Precio);

	/** Get Price Scheme	  */
	public String getEsq_Precio();

    /** Column name EXME_Especialidad_ID */
    public static final String COLUMNNAME_EXME_Especialidad_ID = "EXME_Especialidad_ID";

	/** Set Specialty.
	  * Specialty
	  */
	public void setEXME_Especialidad_ID (int EXME_Especialidad_ID);

	/** Get Specialty.
	  * Specialty
	  */
	public int getEXME_Especialidad_ID();

    /** Column name EXME_PaqBase_ID */
    public static final String COLUMNNAME_EXME_PaqBase_ID = "EXME_PaqBase_ID";

	/** Set Base Package.
	  * Base Package
	  */
	public void setEXME_PaqBase_ID (int EXME_PaqBase_ID);

	/** Get Base Package.
	  * Base Package
	  */
	public int getEXME_PaqBase_ID();

	public I_EXME_PaqBase getEXME_PaqBase() throws RuntimeException;

    /** Column name EXME_Tratamientos_ID */
    public static final String COLUMNNAME_EXME_Tratamientos_ID = "EXME_Tratamientos_ID";

	/** Set Treatments.
	  * Treatments
	  */
	public void setEXME_Tratamientos_ID (int EXME_Tratamientos_ID);

	/** Get Treatments.
	  * Treatments
	  */
	public int getEXME_Tratamientos_ID();

    /** Column name FechaBaja */
    public static final String COLUMNNAME_FechaBaja = "FechaBaja";

	/** Set Drop Date.
	  * Drop Date
	  */
	public void setFechaBaja (Timestamp FechaBaja);

	/** Get Drop Date.
	  * Drop Date
	  */
	public Timestamp getFechaBaja();

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

    /** Column name Rec_Medico */
    public static final String COLUMNNAME_Rec_Medico = "Rec_Medico";

	/** Set Medical Reminder	  */
	public void setRec_Medico (boolean Rec_Medico);

	/** Get Medical Reminder	  */
	public boolean isRec_Medico();

    /** Column name Rec_Paciente */
    public static final String COLUMNNAME_Rec_Paciente = "Rec_Paciente";

	/** Set Patient Reminder	  */
	public void setRec_Paciente (boolean Rec_Paciente);

	/** Get Patient Reminder	  */
	public boolean isRec_Paciente();

    /** Column name Ref_Tratamientos_ID */
    public static final String COLUMNNAME_Ref_Tratamientos_ID = "Ref_Tratamientos_ID";

	/** Set Next Treatment	  */
	public void setRef_Tratamientos_ID (int Ref_Tratamientos_ID);

	/** Get Next Treatment	  */
	public int getRef_Tratamientos_ID();

    /** Column name Sesion */
    public static final String COLUMNNAME_Sesion = "Sesion";

	/** Set Session.
	  * Session
	  */
	public void setSesion (int Sesion);

	/** Get Session.
	  * Session
	  */
	public int getSesion();

    /** Column name Tipo_Trat */
    public static final String COLUMNNAME_Tipo_Trat = "Tipo_Trat";

	/** Set Treatment Type	  */
	public void setTipo_Trat (String Tipo_Trat);

	/** Get Treatment Type	  */
	public String getTipo_Trat();

    /** Column name Type */
    public static final String COLUMNNAME_Type = "Type";

	/** Set Type.
	  * Type of Validation (SQL, Java Script, Java Language)
	  */
	public void setType (boolean Type);

	/** Get Type.
	  * Type of Validation (SQL, Java Script, Java Language)
	  */
	public boolean isType();

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
