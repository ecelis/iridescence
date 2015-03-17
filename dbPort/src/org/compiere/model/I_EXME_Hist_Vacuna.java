/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_Hist_Vacuna
 *  @author Generated Class 
 *  @version Version @VERSION@
 */
public interface I_EXME_Hist_Vacuna 
{

    /** TableName=EXME_Hist_Vacuna */
    public static final String Table_Name = "EXME_Hist_Vacuna";

    /** AD_Table_ID=1200365 */
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

    /** Column name C_Campaign_ID */
    public static final String COLUMNNAME_C_Campaign_ID = "C_Campaign_ID";

	/** Set Program.
	  * Program
	  */
	public void setC_Campaign_ID (int C_Campaign_ID);

	/** Get Program.
	  * Program
	  */
	public int getC_Campaign_ID();

	public I_C_Campaign getC_Campaign() throws RuntimeException;

    /** Column name C_LocationReg_ID */
    public static final String COLUMNNAME_C_LocationReg_ID = "C_LocationReg_ID";

	/** Set Registered Address	  */
	public void setC_LocationReg_ID (int C_LocationReg_ID);

	/** Get Registered Address	  */
	public int getC_LocationReg_ID();

    /** Column name C_UOMHeight_ID */
    public static final String COLUMNNAME_C_UOMHeight_ID = "C_UOMHeight_ID";

	/** Set UOM of Height.
	  * Unit of Measure of Height
	  */
	public void setC_UOMHeight_ID (int C_UOMHeight_ID);

	/** Get UOM of Height.
	  * Unit of Measure of Height
	  */
	public int getC_UOMHeight_ID();

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

    /** Column name C_UOM_Inter_ID */
    public static final String COLUMNNAME_C_UOM_Inter_ID = "C_UOM_Inter_ID";

	/** Set UOM Interval.
	  * Unit of measurement interval
	  */
	public void setC_UOM_Inter_ID (int C_UOM_Inter_ID);

	/** Get UOM Interval.
	  * Unit of measurement interval
	  */
	public int getC_UOM_Inter_ID();

    /** Column name C_UOMMasaCorp_ID */
    public static final String COLUMNNAME_C_UOMMasaCorp_ID = "C_UOMMasaCorp_ID";

	/** Set UOM of Corporal Mass.
	  * Unit of Measure of Corporal Mass
	  */
	public void setC_UOMMasaCorp_ID (int C_UOMMasaCorp_ID);

	/** Get UOM of Corporal Mass.
	  * Unit of Measure of Corporal Mass
	  */
	public int getC_UOMMasaCorp_ID();

    /** Column name C_UOMWeight_ID */
    public static final String COLUMNNAME_C_UOMWeight_ID = "C_UOMWeight_ID";

	/** Set UOM of Weight.
	  * UOM of Weight
	  */
	public void setC_UOMWeight_ID (int C_UOMWeight_ID);

	/** Get UOM of Weight.
	  * UOM of Weight
	  */
	public int getC_UOMWeight_ID();

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

    /** Column name EsLocal */
    public static final String COLUMNNAME_EsLocal = "EsLocal";

	/** Set Is Local.
	  * Indicates if the vaccine is applied locally or not
	  */
	public void setEsLocal (boolean EsLocal);

	/** Get Is Local.
	  * Indicates if the vaccine is applied locally or not
	  */
	public boolean isEsLocal();

    /** Column name EXME_ActPaciente_ID */
    public static final String COLUMNNAME_EXME_ActPaciente_ID = "EXME_ActPaciente_ID";

	/** Set Patient Activity.
	  * Patient Activity
	  */
	public void setEXME_ActPaciente_ID (int EXME_ActPaciente_ID);

	/** Get Patient Activity.
	  * Patient Activity
	  */
	public int getEXME_ActPaciente_ID();

	public I_EXME_ActPaciente getEXME_ActPaciente() throws RuntimeException;

    /** Column name EXME_CitaMedica_ID */
    public static final String COLUMNNAME_EXME_CitaMedica_ID = "EXME_CitaMedica_ID";

	/** Set Medical Appointment.
	  * Medical appointment
	  */
	public void setEXME_CitaMedica_ID (int EXME_CitaMedica_ID);

	/** Get Medical Appointment.
	  * Medical appointment
	  */
	public int getEXME_CitaMedica_ID();

	public I_EXME_CitaMedica getEXME_CitaMedica() throws RuntimeException;

    /** Column name EXME_Enfermeria_ID */
    public static final String COLUMNNAME_EXME_Enfermeria_ID = "EXME_Enfermeria_ID";

	/** Set Infirmary staff.
	  * Infirmary staff
	  */
	public void setEXME_Enfermeria_ID (int EXME_Enfermeria_ID);

	/** Get Infirmary staff.
	  * Infirmary staff
	  */
	public int getEXME_Enfermeria_ID();

	public I_EXME_Enfermeria getEXME_Enfermeria() throws RuntimeException;

    /** Column name EXME_Hist_Vacuna_ID */
    public static final String COLUMNNAME_EXME_Hist_Vacuna_ID = "EXME_Hist_Vacuna_ID";

	/** Set Vaccination History.
	  * Vaccination History
	  */
	public void setEXME_Hist_Vacuna_ID (int EXME_Hist_Vacuna_ID);

	/** Get Vaccination History.
	  * Vaccination History
	  */
	public int getEXME_Hist_Vacuna_ID();

    /** Column name EXME_Labeler_ID */
    public static final String COLUMNNAME_EXME_Labeler_ID = "EXME_Labeler_ID";

	/** Set Labeler.
	  * Labeler
	  */
	public void setEXME_Labeler_ID (int EXME_Labeler_ID);

	/** Get Labeler.
	  * Labeler
	  */
	public int getEXME_Labeler_ID();

	public I_EXME_Labeler getEXME_Labeler() throws RuntimeException;

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

    /** Column name EXME_ViasAdministracion_ID */
    public static final String COLUMNNAME_EXME_ViasAdministracion_ID = "EXME_ViasAdministracion_ID";

	/** Set Route of Administration	  */
	public void setEXME_ViasAdministracion_ID (int EXME_ViasAdministracion_ID);

	/** Get Route of Administration	  */
	public int getEXME_ViasAdministracion_ID();

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

    /** Column name FechaRegistro */
    public static final String COLUMNNAME_FechaRegistro = "FechaRegistro";

	/** Set Registration Date.
	  * Registration Date
	  */
	public void setFechaRegistro (Timestamp FechaRegistro);

	/** Get Registration Date.
	  * Registration Date
	  */
	public Timestamp getFechaRegistro();

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

    /** Column name IsExternal */
    public static final String COLUMNNAME_IsExternal = "IsExternal";

	/** Set External.
	  * External
	  */
	public void setIsExternal (boolean IsExternal);

	/** Get External.
	  * External
	  */
	public boolean isExternal();

    /** Column name Location */
    public static final String COLUMNNAME_Location = "Location";

	/** Set Location	  */
	public void setLocation (String Location);

	/** Get Location	  */
	public String getLocation();

    /** Column name Lot */
    public static final String COLUMNNAME_Lot = "Lot";

	/** Set Lot No.
	  * Lot number (alphanumeric)
	  */
	public void setLot (String Lot);

	/** Get Lot No.
	  * Lot number (alphanumeric)
	  */
	public String getLot();

    /** Column name Manufacturer */
    public static final String COLUMNNAME_Manufacturer = "Manufacturer";

	/** Set Manufacturer.
	  * Manufacturer of the Product
	  */
	public void setManufacturer (String Manufacturer);

	/** Get Manufacturer.
	  * Manufacturer of the Product
	  */
	public String getManufacturer();

    /** Column name MasaCorporal */
    public static final String COLUMNNAME_MasaCorporal = "MasaCorporal";

	/** Set Corporal Mass.
	  * Corporal Mass of the patient
	  */
	public void setMasaCorporal (BigDecimal MasaCorporal);

	/** Get Corporal Mass.
	  * Corporal Mass of the patient
	  */
	public BigDecimal getMasaCorporal();

    /** Column name M_AttributeSetInstance_ID */
    public static final String COLUMNNAME_M_AttributeSetInstance_ID = "M_AttributeSetInstance_ID";

	/** Set Attribute Set Instance.
	  * Product Attribute Set Instance
	  */
	public void setM_AttributeSetInstance_ID (int M_AttributeSetInstance_ID);

	/** Get Attribute Set Instance.
	  * Product Attribute Set Instance
	  */
	public int getM_AttributeSetInstance_ID();

    /** Column name MotivoRechazo */
    public static final String COLUMNNAME_MotivoRechazo = "MotivoRechazo";

	/** Set Rejection Reason.
	  * Reason for Immunization Rejection
	  */
	public void setMotivoRechazo (String MotivoRechazo);

	/** Get Rejection Reason.
	  * Reason for Immunization Rejection
	  */
	public String getMotivoRechazo();

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

    /** Column name Notes */
    public static final String COLUMNNAME_Notes = "Notes";

	/** Set Notes	  */
	public void setNotes (String Notes);

	/** Get Notes	  */
	public String getNotes();

    /** Column name Peso */
    public static final String COLUMNNAME_Peso = "Peso";

	/** Set Weight.
	  * Weight
	  */
	public void setPeso (BigDecimal Peso);

	/** Get Weight.
	  * Weight
	  */
	public BigDecimal getPeso();

    /** Column name Rejected */
    public static final String COLUMNNAME_Rejected = "Rejected";

	/** Set Rejected.
	  * Indicates if the vaccine is Rejected or not
	  */
	public void setRejected (boolean Rejected);

	/** Get Rejected.
	  * Indicates if the vaccine is Rejected or not
	  */
	public boolean isRejected();

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

    /** Column name SeqNo */
    public static final String COLUMNNAME_SeqNo = "SeqNo";

	/** Set Sequence Number.
	  * Method of ordering records;
 lowest number comes first
	  */
	public void setSeqNo (int SeqNo);

	/** Get Sequence Number.
	  * Method of ordering records;
 lowest number comes first
	  */
	public int getSeqNo();

    /** Column name SerNo */
    public static final String COLUMNNAME_SerNo = "SerNo";

	/** Set Serial No.
	  * Product Serial Number 
	  */
	public void setSerNo (String SerNo);

	/** Get Serial No.
	  * Product Serial Number 
	  */
	public String getSerNo();

    /** Column name Site */
    public static final String COLUMNNAME_Site = "Site";

	/** Set Site.
	  * Record the site where the vaccine was administered
	  */
	public void setSite (String Site);

	/** Get Site.
	  * Record the site where the vaccine was administered
	  */
	public String getSite();

    /** Column name Source */
    public static final String COLUMNNAME_Source = "Source";

	/** Set Source.
	  * Record the source of the vaccine given
	  */
	public void setSource (String Source);

	/** Get Source.
	  * Record the source of the vaccine given
	  */
	public String getSource();

    /** Column name Talla */
    public static final String COLUMNNAME_Talla = "Talla";

	/** Set Height.
	  * Height
	  */
	public void setTalla (BigDecimal Talla);

	/** Get Height.
	  * Height
	  */
	public BigDecimal getTalla();

    /** Column name ValidTo */
    public static final String COLUMNNAME_ValidTo = "ValidTo";

	/** Set Valid to.
	  * Valid to including this date (last day)
	  */
	public void setValidTo (Timestamp ValidTo);

	/** Get Valid to.
	  * Valid to including this date (last day)
	  */
	public Timestamp getValidTo();

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

    /** Column name VIS_DateGiven */
    public static final String COLUMNNAME_VIS_DateGiven = "VIS_DateGiven";

	/** Set VIS Date Given.
	  * Date when the Vaccine Information Statement was given
	  */
	public void setVIS_DateGiven (Timestamp VIS_DateGiven);

	/** Get VIS Date Given.
	  * Date when the Vaccine Information Statement was given
	  */
	public Timestamp getVIS_DateGiven();
}
