/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.util.KeyNamePair;

/** Generated Model for I_EXME_Proveedores
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_I_EXME_Proveedores extends PO implements I_I_EXME_Proveedores, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_I_EXME_Proveedores (Properties ctx, int I_EXME_Proveedores_ID, String trxName)
    {
      super (ctx, I_EXME_Proveedores_ID, trxName);
      /** if (I_EXME_Proveedores_ID == 0)
        {
			setI_EXME_Proveedores_ID (0);
        } */
    }

    /** Load Constructor */
    public X_I_EXME_Proveedores (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 3 - Client - Org 
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
      StringBuffer sb = new StringBuffer ("X_I_EXME_Proveedores[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Address 1.
		@param Address1 
		Address line 1 for this location
	  */
	public void setAddress1 (String Address1)
	{
		set_Value (COLUMNNAME_Address1, Address1);
	}

	/** Get Address 1.
		@return Address line 1 for this location
	  */
	public String getAddress1 () 
	{
		return (String)get_Value(COLUMNNAME_Address1);
	}

	/** Set Address 2.
		@param Address2 
		Address line 2 for this location
	  */
	public void setAddress2 (String Address2)
	{
		set_Value (COLUMNNAME_Address2, Address2);
	}

	/** Get Address 2.
		@return Address line 2 for this location
	  */
	public String getAddress2 () 
	{
		return (String)get_Value(COLUMNNAME_Address2);
	}

	/** Set City.
		@param City 
		Identifies a City
	  */
	public void setCity (String City)
	{
		set_Value (COLUMNNAME_City, City);
	}

	/** Get City.
		@return Identifies a City
	  */
	public String getCity () 
	{
		return (String)get_Value(COLUMNNAME_City);
	}

	/** Set Provider Code.
		@param Codigo_Proveedor Provider Code	  */
	public void setCodigo_Proveedor (String Codigo_Proveedor)
	{
		set_Value (COLUMNNAME_Codigo_Proveedor, Codigo_Proveedor);
	}

	/** Get Provider Code.
		@return Provider Code	  */
	public String getCodigo_Proveedor () 
	{
		return (String)get_Value(COLUMNNAME_Codigo_Proveedor);
	}

	/** Set State.
		@param Estado State	  */
	public void setEstado (String Estado)
	{
		set_Value (COLUMNNAME_Estado, Estado);
	}

	/** Get State.
		@return State	  */
	public String getEstado () 
	{
		return (String)get_Value(COLUMNNAME_Estado);
	}

	/** Set Import Error Message.
		@param I_ErrorMsg 
		Messages generated from import process
	  */
	public void setI_ErrorMsg (String I_ErrorMsg)
	{
		set_Value (COLUMNNAME_I_ErrorMsg, I_ErrorMsg);
	}

	/** Get Import Error Message.
		@return Messages generated from import process
	  */
	public String getI_ErrorMsg () 
	{
		return (String)get_Value(COLUMNNAME_I_ErrorMsg);
	}

	/** Set I_EXME_Proveedores_ID.
		@param I_EXME_Proveedores_ID I_EXME_Proveedores_ID	  */
	public void setI_EXME_Proveedores_ID (int I_EXME_Proveedores_ID)
	{
		if (I_EXME_Proveedores_ID < 1)
			 throw new IllegalArgumentException ("I_EXME_Proveedores_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_I_EXME_Proveedores_ID, Integer.valueOf(I_EXME_Proveedores_ID));
	}

	/** Get I_EXME_Proveedores_ID.
		@return I_EXME_Proveedores_ID	  */
	public int getI_EXME_Proveedores_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_I_EXME_Proveedores_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Imported.
		@param I_IsImported 
		Has this import been processed
	  */
	public void setI_IsImported (boolean I_IsImported)
	{
		set_Value (COLUMNNAME_I_IsImported, Boolean.valueOf(I_IsImported));
	}

	/** Get Imported.
		@return Has this import been processed
	  */
	public boolean isI_IsImported () 
	{
		Object oo = get_Value(COLUMNNAME_I_IsImported);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Coin.
		@param Moneda Coin	  */
	public void setMoneda (String Moneda)
	{
		set_Value (COLUMNNAME_Moneda, Moneda);
	}

	/** Get Coin.
		@return Coin	  */
	public String getMoneda () 
	{
		return (String)get_Value(COLUMNNAME_Moneda);
	}

	/** Set Name.
		@param Name 
		Alphanumeric identifier of the entity
	  */
	public void setName (String Name)
	{
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

	/** Set Pais.
		@param Pais Pais	  */
	public void setPais (String Pais)
	{
		set_Value (COLUMNNAME_Pais, Pais);
	}

	/** Get Pais.
		@return Pais	  */
	public String getPais () 
	{
		return (String)get_Value(COLUMNNAME_Pais);
	}

	/** Set Processed.
		@param Processed 
		The document has been processed
	  */
	public void setProcessed (boolean Processed)
	{
		set_Value (COLUMNNAME_Processed, Boolean.valueOf(Processed));
	}

	/** Get Processed.
		@return The document has been processed
	  */
	public boolean isProcessed () 
	{
		Object oo = get_Value(COLUMNNAME_Processed);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Process Now.
		@param Processing Process Now	  */
	public void setProcessing (boolean Processing)
	{
		set_Value (COLUMNNAME_Processing, Boolean.valueOf(Processing));
	}

	/** Get Process Now.
		@return Process Now	  */
	public boolean isProcessing () 
	{
		Object oo = get_Value(COLUMNNAME_Processing);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Branch.
		@param Sucursal 
		Branch
	  */
	public void setSucursal (String Sucursal)
	{
		set_Value (COLUMNNAME_Sucursal, Sucursal);
	}

	/** Get Branch.
		@return Branch
	  */
	public String getSucursal () 
	{
		return (String)get_Value(COLUMNNAME_Sucursal);
	}

	/** Set Payment Terms.
		@param Termino_Pago Payment Terms	  */
	public void setTermino_Pago (String Termino_Pago)
	{
		set_Value (COLUMNNAME_Termino_Pago, Termino_Pago);
	}

	/** Get Payment Terms.
		@return Payment Terms	  */
	public String getTermino_Pago () 
	{
		return (String)get_Value(COLUMNNAME_Termino_Pago);
	}

	/** Set Operational Unit.
		@param Unidad_Operativa Operational Unit	  */
	public void setUnidad_Operativa (String Unidad_Operativa)
	{
		set_Value (COLUMNNAME_Unidad_Operativa, Unidad_Operativa);
	}

	/** Get Operational Unit.
		@return Operational Unit	  */
	public String getUnidad_Operativa () 
	{
		return (String)get_Value(COLUMNNAME_Unidad_Operativa);
	}
}