/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for M_Warehouse
 *  @author Generated Class 
 *  @version Version @VERSION@
 */
public interface I_M_Warehouse 
{

    /** TableName=M_Warehouse */
    public static final String Table_Name = "M_Warehouse";

    /** AD_Table_ID=190 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 7 - System - Client - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(7);

    /** Load Meta Data */

    /** Column name AddTestResult */
    public static final String COLUMNNAME_AddTestResult = "AddTestResult";

	/** Set Test Result Registration.
	  * Allows to register test results manually
	  */
	public void setAddTestResult (boolean AddTestResult);

	/** Get Test Result Registration.
	  * Allows to register test results manually
	  */
	public boolean isAddTestResult();

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

    /** Column name C_BPartner_ID */
    public static final String COLUMNNAME_C_BPartner_ID = "C_BPartner_ID";

	/** Set Company.
	  * Identifier for a Company
	  */
	public void setC_BPartner_ID (int C_BPartner_ID);

	/** Get Company.
	  * Identifier for a Company
	  */
	public int getC_BPartner_ID();

	public I_C_BPartner getC_BPartner() throws RuntimeException;

    /** Column name C_Location_ID */
    public static final String COLUMNNAME_C_Location_ID = "C_Location_ID";

	/** Set Address .
	  * Location or Address
	  */
	public void setC_Location_ID (int C_Location_ID);

	/** Get Address .
	  * Location or Address
	  */
	public int getC_Location_ID();

    /** Column name Consigna */
    public static final String COLUMNNAME_Consigna = "Consigna";

	/** Set Is Consignment Warehosue	  */
	public void setConsigna (boolean Consigna);

	/** Get Is Consignment Warehosue	  */
	public boolean isConsigna();

    /** Column name ControlaEspecimen */
    public static final String COLUMNNAME_ControlaEspecimen = "ControlaEspecimen";

	/** Set Specimen Managament	  */
	public void setControlaEspecimen (boolean ControlaEspecimen);

	/** Get Specimen Managament	  */
	public boolean isControlaEspecimen();

    /** Column name ControlExistencias */
    public static final String COLUMNNAME_ControlExistencias = "ControlExistencias";

	/** Set Inventory Management.
	  * The warehouse uses inventory management
	  */
	public void setControlExistencias (boolean ControlExistencias);

	/** Get Inventory Management.
	  * The warehouse uses inventory management
	  */
	public boolean isControlExistencias();

    /** Column name CopyRevenueCode */
    public static final String COLUMNNAME_CopyRevenueCode = "CopyRevenueCode";

	/** Set Copy Revenue Code.
	  * Copy Revenue Code
	  */
	public void setCopyRevenueCode (String CopyRevenueCode);

	/** Get Copy Revenue Code.
	  * Copy Revenue Code
	  */
	public String getCopyRevenueCode();

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

    /** Column name EXME_Formato_ID */
    public static final String COLUMNNAME_EXME_Formato_ID = "EXME_Formato_ID";

	/** Set Format	  */
	public void setEXME_Formato_ID (int EXME_Formato_ID);

	/** Get Format	  */
	public int getEXME_Formato_ID();

    /** Column name EXME_Impresora_ID */
    public static final String COLUMNNAME_EXME_Impresora_ID = "EXME_Impresora_ID";

	/** Set printer.
	  * printer
	  */
	public void setEXME_Impresora_ID (int EXME_Impresora_ID);

	/** Get printer.
	  * printer
	  */
	public int getEXME_Impresora_ID();

	public I_EXME_Impresora getEXME_Impresora() throws RuntimeException;

    /** Column name EXME_Turnos2_ID */
    public static final String COLUMNNAME_EXME_Turnos2_ID = "EXME_Turnos2_ID";

	/** Set Warehouse Shifts 2	  */
	public void setEXME_Turnos2_ID (int EXME_Turnos2_ID);

	/** Get Warehouse Shifts 2	  */
	public int getEXME_Turnos2_ID();

    /** Column name Genera_HL7 */
    public static final String COLUMNNAME_Genera_HL7 = "Genera_HL7";

	/** Set Generates HL 7 Data.
	  * Generates HL 7 Data
	  */
	public void setGenera_HL7 (boolean Genera_HL7);

	/** Get Generates HL 7 Data.
	  * Generates HL 7 Data
	  */
	public boolean isGenera_HL7();

    /** Column name HL7ORM */
    public static final String COLUMNNAME_HL7ORM = "HL7ORM";

	/** Set Requires HL7 ORM Message	  */
	public void setHL7ORM (boolean HL7ORM);

	/** Get Requires HL7 ORM Message	  */
	public boolean isHL7ORM();

    /** Column name HostHL7 */
    public static final String COLUMNNAME_HostHL7 = "HostHL7";

	/** Set HL7 Server.
	  * HL7 Server
	  */
	public void setHostHL7 (String HostHL7);

	/** Get HL7 Server.
	  * HL7 Server
	  */
	public String getHostHL7();

    /** Column name Interfaz_HL7 */
    public static final String COLUMNNAME_Interfaz_HL7 = "Interfaz_HL7";

	/** Set HL 7 Interface.
	  * HL 7 Interface
	  */
	public void setInterfaz_HL7 (String Interfaz_HL7);

	/** Get HL 7 Interface.
	  * HL 7 Interface
	  */
	public String getInterfaz_HL7();

    /** Column name Intervalo */
    public static final String COLUMNNAME_Intervalo = "Intervalo";

	/** Set Interval.
	  * Interval
	  */
	public void setIntervalo (int Intervalo);

	/** Get Interval.
	  * Interval
	  */
	public int getIntervalo();

    /** Column name IsInTransit */
    public static final String COLUMNNAME_IsInTransit = "IsInTransit";

	/** Set In Transit.
	  * Movement is in transit
	  */
	public void setIsInTransit (boolean IsInTransit);

	/** Get In Transit.
	  * Movement is in transit
	  */
	public boolean isInTransit();

    /** Column name M_Warehouse_ID */
    public static final String COLUMNNAME_M_Warehouse_ID = "M_Warehouse_ID";

	/** Set Warehouse.
	  * Storage Warehouse and Service Point
	  */
	public void setM_Warehouse_ID (int M_Warehouse_ID);

	/** Get Warehouse.
	  * Storage Warehouse and Service Point
	  */
	public int getM_Warehouse_ID();

    /** Column name M_WarehouseSource_ID */
    public static final String COLUMNNAME_M_WarehouseSource_ID = "M_WarehouseSource_ID";

	/** Set Source Warehouse.
	  * Optional Warehouse to replenish from
	  */
	public void setM_WarehouseSource_ID (int M_WarehouseSource_ID);

	/** Get Source Warehouse.
	  * Optional Warehouse to replenish from
	  */
	public int getM_WarehouseSource_ID();

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

    /** Column name PortHL7 */
    public static final String COLUMNNAME_PortHL7 = "PortHL7";

	/** Set Port For HL7.
	  * Host's Port For HL7 Message Reception
	  */
	public void setPortHL7 (int PortHL7);

	/** Get Port For HL7.
	  * Host's Port For HL7 Message Reception
	  */
	public int getPortHL7();

    /** Column name ReplenishmentClass */
    public static final String COLUMNNAME_ReplenishmentClass = "ReplenishmentClass";

	/** Set Replenishment Class.
	  * Custom class to calculate Quantity to Order
	  */
	public void setReplenishmentClass (String ReplenishmentClass);

	/** Get Replenishment Class.
	  * Custom class to calculate Quantity to Order
	  */
	public String getReplenishmentClass();

    /** Column name Separator */
    public static final String COLUMNNAME_Separator = "Separator";

	/** Set Element Separator.
	  * Element Separator
	  */
	public void setSeparator (String Separator);

	/** Get Element Separator.
	  * Element Separator
	  */
	public String getSeparator();

    /** Column name TipoAlmacen */
    public static final String COLUMNNAME_TipoAlmacen = "TipoAlmacen";

	/** Set Warehouse purchase	  */
	public void setTipoAlmacen (boolean TipoAlmacen);

	/** Get Warehouse purchase	  */
	public boolean isTipoAlmacen();

    /** Column name Trayectoria */
    public static final String COLUMNNAME_Trayectoria = "Trayectoria";

	/** Set Trajectory	  */
	public void setTrayectoria (String Trayectoria);

	/** Get Trajectory	  */
	public String getTrayectoria();

    /** Column name Type */
    public static final String COLUMNNAME_Type = "Type";

	/** Set Type.
	  * Type of Validation
	  */
	public void setType (String Type);

	/** Get Type.
	  * Type of Validation
	  */
	public String getType();

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

    /** Column name Virtual */
    public static final String COLUMNNAME_Virtual = "Virtual";

	/** Set Virtual	  */
	public void setVirtual (boolean Virtual);

	/** Get Virtual	  */
	public boolean isVirtual();
}
