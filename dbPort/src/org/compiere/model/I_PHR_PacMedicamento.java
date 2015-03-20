/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for PHR_PacMedicamento
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_PHR_PacMedicamento 
{

    /** TableName=PHR_PacMedicamento */
    public static final String Table_Name = "PHR_PacMedicamento";

    /** AD_Table_ID=1201012 */
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

    /** Column name EXME_ViasAdministracion_ID */
    public static final String COLUMNNAME_EXME_ViasAdministracion_ID = "EXME_ViasAdministracion_ID";

	/** Set Route of Administration	  */
	public void setEXME_ViasAdministracion_ID (int EXME_ViasAdministracion_ID);

	/** Get Route of Administration	  */
	public int getEXME_ViasAdministracion_ID();

	public I_EXME_ViasAdministracion getEXME_ViasAdministracion() throws RuntimeException;

    /** Column name FechaFin */
    public static final String COLUMNNAME_FechaFin = "FechaFin";

	/** Set Ending Date.
	  * Date of ending of intervention
	  */
	public void setFechaFin (Timestamp FechaFin);

	/** Get Ending Date.
	  * Date of ending of intervention
	  */
	public Timestamp getFechaFin();

    /** Column name FechaIni */
    public static final String COLUMNNAME_FechaIni = "FechaIni";

	/** Set Initial Date.
	  * Initial Date
	  */
	public void setFechaIni (Timestamp FechaIni);

	/** Get Initial Date.
	  * Initial Date
	  */
	public Timestamp getFechaIni();

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

    /** Column name Observaciones */
    public static final String COLUMNNAME_Observaciones = "Observaciones";

	/** Set Notes.
	  * Notes
	  */
	public void setObservaciones (String Observaciones);

	/** Get Notes.
	  * Notes
	  */
	public String getObservaciones();

    /** Column name Periodo */
    public static final String COLUMNNAME_Periodo = "Periodo";

	/** Set Period.
	  * Period
	  */
	public void setPeriodo (BigDecimal Periodo);

	/** Get Period.
	  * Period
	  */
	public BigDecimal getPeriodo();

    /** Column name PeriodoIndefinido */
    public static final String COLUMNNAME_PeriodoIndefinido = "PeriodoIndefinido";

	/** Set Indeterminate Period	  */
	public void setPeriodoIndefinido (boolean PeriodoIndefinido);

	/** Get Indeterminate Period	  */
	public boolean isPeriodoIndefinido();

    /** Column name PHR_Evento_ID */
    public static final String COLUMNNAME_PHR_Evento_ID = "PHR_Evento_ID";

	/** Set Patient Event	  */
	public void setPHR_Evento_ID (int PHR_Evento_ID);

	/** Get Patient Event	  */
	public int getPHR_Evento_ID();

	public I_PHR_Evento getPHR_Evento() throws RuntimeException;

    /** Column name PHR_PacMedicamento_ID */
    public static final String COLUMNNAME_PHR_PacMedicamento_ID = "PHR_PacMedicamento_ID";

	/** Set Patient Medication	  */
	public void setPHR_PacMedicamento_ID (int PHR_PacMedicamento_ID);

	/** Get Patient Medication	  */
	public int getPHR_PacMedicamento_ID();

    /** Column name UOMIntervalo */
    public static final String COLUMNNAME_UOMIntervalo = "UOMIntervalo";

	/** Set Interval UOM.
	  * Interval UOM
	  */
	public void setUOMIntervalo (String UOMIntervalo);

	/** Get Interval UOM.
	  * Interval UOM
	  */
	public String getUOMIntervalo();

    /** Column name UOMPeriodo */
    public static final String COLUMNNAME_UOMPeriodo = "UOMPeriodo";

	/** Set Period UOM	  */
	public void setUOMPeriodo (String UOMPeriodo);

	/** Get Period UOM	  */
	public String getUOMPeriodo();

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
