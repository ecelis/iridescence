/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;
import org.compiere.util.KeyNamePair;

/** Generated Model for M_Warehouse
 *  @author Generated Class 
 *  @version Version @VERSION@ - $Id$ */
public class X_M_Warehouse extends PO implements I_M_Warehouse, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_M_Warehouse (Properties ctx, int M_Warehouse_ID, String trxName)
    {
      super (ctx, M_Warehouse_ID, trxName);
      /** if (M_Warehouse_ID == 0)
        {
			setAddTestResult (false);
			setC_Location_ID (0);
			setConsigna (false);
			setM_Warehouse_ID (0);
			setName (null);
			setSeparator (null);
// *
			setValue (null);
			setVirtual (false);
        } */
    }

    /** Load Constructor */
    public X_M_Warehouse (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 7 - System - Client - Org 
      */
    protected int get_AccessLevel()
    {
      return accessLevel.intValue();
    }

    /** Load Meta Data */
    protected POInfo initPO (Properties ctx)
    {
      POInfo poi = POInfo.getPOInfo (ctx, Table_ID, get_TrxName());
      return poi;
    }

    public String toString()
    {
      StringBuffer sb = new StringBuffer ("X_M_Warehouse[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Test Result Registration.
		@param AddTestResult 
		Allows to register test results manually
	  */
	public void setAddTestResult (boolean AddTestResult)
	{
		set_Value (COLUMNNAME_AddTestResult, Boolean.valueOf(AddTestResult));
	}

	/** Get Test Result Registration.
		@return Allows to register test results manually
	  */
	public boolean isAddTestResult () 
	{
		Object oo = get_Value(COLUMNNAME_AddTestResult);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	public I_C_BPartner getC_BPartner() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_C_BPartner.Table_Name);
        I_C_BPartner result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_C_BPartner)constructor.newInstance(new Object[] {getCtx(), new Integer(getC_BPartner_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Company.
		@param C_BPartner_ID 
		Identifier for a Company
	  */
	public void setC_BPartner_ID (int C_BPartner_ID)
	{
		if (C_BPartner_ID < 1) 
			set_Value (COLUMNNAME_C_BPartner_ID, null);
		else 
			set_Value (COLUMNNAME_C_BPartner_ID, Integer.valueOf(C_BPartner_ID));
	}

	/** Get Company.
		@return Identifier for a Company
	  */
	public int getC_BPartner_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_BPartner_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Address .
		@param C_Location_ID 
		Location or Address
	  */
	public void setC_Location_ID (int C_Location_ID)
	{
		if (C_Location_ID < 1)
			 throw new IllegalArgumentException ("C_Location_ID is mandatory.");
		set_Value (COLUMNNAME_C_Location_ID, Integer.valueOf(C_Location_ID));
	}

	/** Get Address .
		@return Location or Address
	  */
	public int getC_Location_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Location_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Is Consignment Warehosue.
		@param Consigna Is Consignment Warehosue	  */
	public void setConsigna (boolean Consigna)
	{
		set_Value (COLUMNNAME_Consigna, Boolean.valueOf(Consigna));
	}

	/** Get Is Consignment Warehosue.
		@return Is Consignment Warehosue	  */
	public boolean isConsigna () 
	{
		Object oo = get_Value(COLUMNNAME_Consigna);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Specimen Managament.
		@param ControlaEspecimen Specimen Managament	  */
	public void setControlaEspecimen (boolean ControlaEspecimen)
	{
		set_Value (COLUMNNAME_ControlaEspecimen, Boolean.valueOf(ControlaEspecimen));
	}

	/** Get Specimen Managament.
		@return Specimen Managament	  */
	public boolean isControlaEspecimen () 
	{
		Object oo = get_Value(COLUMNNAME_ControlaEspecimen);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Inventory Management.
		@param ControlExistencias 
		The warehouse uses inventory management
	  */
	public void setControlExistencias (boolean ControlExistencias)
	{
		set_Value (COLUMNNAME_ControlExistencias, Boolean.valueOf(ControlExistencias));
	}

	/** Get Inventory Management.
		@return The warehouse uses inventory management
	  */
	public boolean isControlExistencias () 
	{
		Object oo = get_Value(COLUMNNAME_ControlExistencias);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Copy Revenue Code.
		@param CopyRevenueCode 
		Copy Revenue Code
	  */
	public void setCopyRevenueCode (String CopyRevenueCode)
	{
		set_Value (COLUMNNAME_CopyRevenueCode, CopyRevenueCode);
	}

	/** Get Copy Revenue Code.
		@return Copy Revenue Code
	  */
	public String getCopyRevenueCode () 
	{
		return (String)get_Value(COLUMNNAME_CopyRevenueCode);
	}

	/** Set Description.
		@param Description 
		Optional short description of the record
	  */
	public void setDescription (String Description)
	{
		set_Value (COLUMNNAME_Description, Description);
	}

	/** Get Description.
		@return Optional short description of the record
	  */
	public String getDescription () 
	{
		return (String)get_Value(COLUMNNAME_Description);
	}

	/** Set Format.
		@param EXME_Formato_ID Format	  */
	public void setEXME_Formato_ID (int EXME_Formato_ID)
	{
		if (EXME_Formato_ID < 1) 
			set_Value (COLUMNNAME_EXME_Formato_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_Formato_ID, Integer.valueOf(EXME_Formato_ID));
	}

	/** Get Format.
		@return Format	  */
	public int getEXME_Formato_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Formato_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_Impresora getEXME_Impresora() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_Impresora.Table_Name);
        I_EXME_Impresora result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_Impresora)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Impresora_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set printer.
		@param EXME_Impresora_ID 
		printer
	  */
	public void setEXME_Impresora_ID (int EXME_Impresora_ID)
	{
		if (EXME_Impresora_ID < 1) 
			set_Value (COLUMNNAME_EXME_Impresora_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_Impresora_ID, Integer.valueOf(EXME_Impresora_ID));
	}

	/** Get printer.
		@return printer
	  */
	public int getEXME_Impresora_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Impresora_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Warehouse Shifts 2.
		@param EXME_Turnos2_ID Warehouse Shifts 2	  */
	public void setEXME_Turnos2_ID (int EXME_Turnos2_ID)
	{
		if (EXME_Turnos2_ID < 1) 
			set_Value (COLUMNNAME_EXME_Turnos2_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_Turnos2_ID, Integer.valueOf(EXME_Turnos2_ID));
	}

	/** Get Warehouse Shifts 2.
		@return Warehouse Shifts 2	  */
	public int getEXME_Turnos2_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Turnos2_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Generates HL 7 Data.
		@param Genera_HL7 
		Generates HL 7 Data
	  */
	public void setGenera_HL7 (boolean Genera_HL7)
	{
		set_Value (COLUMNNAME_Genera_HL7, Boolean.valueOf(Genera_HL7));
	}

	/** Get Generates HL 7 Data.
		@return Generates HL 7 Data
	  */
	public boolean isGenera_HL7 () 
	{
		Object oo = get_Value(COLUMNNAME_Genera_HL7);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Requires HL7 ORM Message.
		@param HL7ORM Requires HL7 ORM Message	  */
	public void setHL7ORM (boolean HL7ORM)
	{
		set_Value (COLUMNNAME_HL7ORM, Boolean.valueOf(HL7ORM));
	}

	/** Get Requires HL7 ORM Message.
		@return Requires HL7 ORM Message	  */
	public boolean isHL7ORM () 
	{
		Object oo = get_Value(COLUMNNAME_HL7ORM);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set HL7 Server.
		@param HostHL7 
		HL7 Server
	  */
	public void setHostHL7 (String HostHL7)
	{
		set_Value (COLUMNNAME_HostHL7, HostHL7);
	}

	/** Get HL7 Server.
		@return HL7 Server
	  */
	public String getHostHL7 () 
	{
		return (String)get_Value(COLUMNNAME_HostHL7);
	}

	/** Set HL 7 Interface.
		@param Interfaz_HL7 
		HL 7 Interface
	  */
	public void setInterfaz_HL7 (String Interfaz_HL7)
	{
		set_Value (COLUMNNAME_Interfaz_HL7, Interfaz_HL7);
	}

	/** Get HL 7 Interface.
		@return HL 7 Interface
	  */
	public String getInterfaz_HL7 () 
	{
		return (String)get_Value(COLUMNNAME_Interfaz_HL7);
	}

	/** Set Interval.
		@param Intervalo 
		Interval
	  */
	public void setIntervalo (int Intervalo)
	{
		set_Value (COLUMNNAME_Intervalo, Integer.valueOf(Intervalo));
	}

	/** Get Interval.
		@return Interval
	  */
	public int getIntervalo () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Intervalo);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set In Transit.
		@param IsInTransit 
		Movement is in transit
	  */
	public void setIsInTransit (boolean IsInTransit)
	{
		set_Value (COLUMNNAME_IsInTransit, Boolean.valueOf(IsInTransit));
	}

	/** Get In Transit.
		@return Movement is in transit
	  */
	public boolean isInTransit () 
	{
		Object oo = get_Value(COLUMNNAME_IsInTransit);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Warehouse.
		@param M_Warehouse_ID 
		Storage Warehouse and Service Point
	  */
	public void setM_Warehouse_ID (int M_Warehouse_ID)
	{
		if (M_Warehouse_ID < 1)
			 throw new IllegalArgumentException ("M_Warehouse_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_M_Warehouse_ID, Integer.valueOf(M_Warehouse_ID));
	}

	/** Get Warehouse.
		@return Storage Warehouse and Service Point
	  */
	public int getM_Warehouse_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_Warehouse_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Source Warehouse.
		@param M_WarehouseSource_ID 
		Optional Warehouse to replenish from
	  */
	public void setM_WarehouseSource_ID (int M_WarehouseSource_ID)
	{
		if (M_WarehouseSource_ID < 1) 
			set_Value (COLUMNNAME_M_WarehouseSource_ID, null);
		else 
			set_Value (COLUMNNAME_M_WarehouseSource_ID, Integer.valueOf(M_WarehouseSource_ID));
	}

	/** Get Source Warehouse.
		@return Optional Warehouse to replenish from
	  */
	public int getM_WarehouseSource_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_WarehouseSource_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Name.
		@param Name 
		Alphanumeric identifier of the entity
	  */
	public void setName (String Name)
	{
		if (Name == null)
			throw new IllegalArgumentException ("Name is mandatory.");
		set_Value (COLUMNNAME_Name, Name);
	}

	/** Get Name.
		@return Alphanumeric identifier of the entity
	  */
	public String getName () 
	{
		return (String)get_Value(COLUMNNAME_Name);
	}

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), getName());
    }

	/** Set Port For HL7.
		@param PortHL7 
		Host's Port For HL7 Message Reception
	  */
	public void setPortHL7 (int PortHL7)
	{
		set_Value (COLUMNNAME_PortHL7, Integer.valueOf(PortHL7));
	}

	/** Get Port For HL7.
		@return Host's Port For HL7 Message Reception
	  */
	public int getPortHL7 () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_PortHL7);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Replenishment Class.
		@param ReplenishmentClass 
		Custom class to calculate Quantity to Order
	  */
	public void setReplenishmentClass (String ReplenishmentClass)
	{
		set_Value (COLUMNNAME_ReplenishmentClass, ReplenishmentClass);
	}

	/** Get Replenishment Class.
		@return Custom class to calculate Quantity to Order
	  */
	public String getReplenishmentClass () 
	{
		return (String)get_Value(COLUMNNAME_ReplenishmentClass);
	}

	/** Set Element Separator.
		@param Separator 
		Element Separator
	  */
	public void setSeparator (String Separator)
	{
		if (Separator == null)
			throw new IllegalArgumentException ("Separator is mandatory.");
		set_Value (COLUMNNAME_Separator, Separator);
	}

	/** Get Element Separator.
		@return Element Separator
	  */
	public String getSeparator () 
	{
		return (String)get_Value(COLUMNNAME_Separator);
	}

	/** Set Warehouse purchase.
		@param TipoAlmacen Warehouse purchase	  */
	public void setTipoAlmacen (boolean TipoAlmacen)
	{
		set_Value (COLUMNNAME_TipoAlmacen, Boolean.valueOf(TipoAlmacen));
	}

	/** Get Warehouse purchase.
		@return Warehouse purchase	  */
	public boolean isTipoAlmacen () 
	{
		Object oo = get_Value(COLUMNNAME_TipoAlmacen);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Trajectory.
		@param Trayectoria Trajectory	  */
	public void setTrayectoria (String Trayectoria)
	{
		set_Value (COLUMNNAME_Trayectoria, Trayectoria);
	}

	/** Get Trajectory.
		@return Trajectory	  */
	public String getTrayectoria () 
	{
		return (String)get_Value(COLUMNNAME_Trayectoria);
	}

	/** Type AD_Reference_ID=1200392 */
	public static final int TYPE_AD_Reference_ID=1200392;
	/** Laboratory = L */
	public static final String TYPE_Laboratory = "L";
	/** Radiology = R */
	public static final String TYPE_Radiology = "R";
	/** Blood Bank = B */
	public static final String TYPE_BloodBank = "B";
	/** Immunization = I */
	public static final String TYPE_Immunization = "I";
	/** Others = O */
	public static final String TYPE_Others = "O";
	/** Sterilization = S */
	public static final String TYPE_Sterilization = "S";
	/** Warehouse Supply = W */
	public static final String TYPE_WarehouseSupply = "W";
	/** Supply + Sterilization = WS */
	public static final String TYPE_SupplyPlusSterilization = "WS";
	/** Audit = A */
	public static final String TYPE_Audit = "A";
	/** Set Type.
		@param Type 
		Type of Validation
	  */
	public void setType (String Type)
	{

		if (Type == null || Type.equals("L") || Type.equals("R") || Type.equals("B") || Type.equals("I") || Type.equals("O") || Type.equals("S") || Type.equals("W") || Type.equals("WS") || Type.equals("A")); else throw new IllegalArgumentException ("Type Invalid value - " + Type + " - Reference_ID=1200392 - L - R - B - I - O - S - W - WS - A");		set_Value (COLUMNNAME_Type, Type);
	}

	/** Get Type.
		@return Type of Validation
	  */
	public String getType () 
	{
		return (String)get_Value(COLUMNNAME_Type);
	}

	/** Set Search Key.
		@param Value 
		Search key for the record in the format required - must be unique
	  */
	public void setValue (String Value)
	{
		if (Value == null)
			throw new IllegalArgumentException ("Value is mandatory.");
		set_Value (COLUMNNAME_Value, Value);
	}

	/** Get Search Key.
		@return Search key for the record in the format required - must be unique
	  */
	public String getValue () 
	{
		return (String)get_Value(COLUMNNAME_Value);
	}

	/** Set Virtual.
		@param Virtual Virtual	  */
	public void setVirtual (boolean Virtual)
	{
		set_Value (COLUMNNAME_Virtual, Boolean.valueOf(Virtual));
	}

	/** Get Virtual.
		@return Virtual	  */
	public boolean isVirtual () 
	{
		Object oo = get_Value(COLUMNNAME_Virtual);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}
}