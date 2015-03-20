/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;

/** Generated Model for I_EXME_Asset
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_I_EXME_Asset extends PO implements I_I_EXME_Asset, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_I_EXME_Asset (Properties ctx, int I_EXME_Asset_ID, String trxName)
    {
      super (ctx, I_EXME_Asset_ID, trxName);
      /** if (I_EXME_Asset_ID == 0)
        {
        } */
    }

    /** Load Constructor */
    public X_I_EXME_Asset (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_I_EXME_Asset[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Class.
		@param Clase 
		Indicates the class to which belongs the group of assets
	  */
	public void setClase (String Clase)
	{
		set_Value (COLUMNNAME_Clase, Clase);
	}

	/** Get Class.
		@return Indicates the class to which belongs the group of assets
	  */
	public String getClase () 
	{
		return (String)get_Value(COLUMNNAME_Clase);
	}

	/** Set Account.
		@param Cuenta Account	  */
	public void setCuenta (String Cuenta)
	{
		set_Value (COLUMNNAME_Cuenta, Cuenta);
	}

	/** Get Account.
		@return Account	  */
	public String getCuenta () 
	{
		return (String)get_Value(COLUMNNAME_Cuenta);
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

	/** Set Station.
		@param Estacion 
		Sets the search key of the Service Station
	  */
	public void setEstacion (String Estacion)
	{
		set_Value (COLUMNNAME_Estacion, Estacion);
	}

	/** Get Station.
		@return Sets the search key of the Service Station
	  */
	public String getEstacion () 
	{
		return (String)get_Value(COLUMNNAME_Estacion);
	}

	/** Set Purchase Date.
		@param FechaCompra 
		It contains the date of purchase of the asset
	  */
	public void setFechaCompra (String FechaCompra)
	{
		set_Value (COLUMNNAME_FechaCompra, FechaCompra);
	}

	/** Get Purchase Date.
		@return It contains the date of purchase of the asset
	  */
	public String getFechaCompra () 
	{
		return (String)get_Value(COLUMNNAME_FechaCompra);
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

	/** Set Import Assets.
		@param I_EXME_Asset_ID 
		Allows importing assets related to its administration
	  */
	public void setI_EXME_Asset_ID (int I_EXME_Asset_ID)
	{
		if (I_EXME_Asset_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_I_EXME_Asset_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_I_EXME_Asset_ID, Integer.valueOf(I_EXME_Asset_ID));
	}

	/** Get Import Assets.
		@return Allows importing assets related to its administration
	  */
	public int getI_EXME_Asset_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_I_EXME_Asset_ID);
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

	/** Set Control Number.
		@param NumeroControl 
		Contains the number of active contro
	  */
	public void setNumeroControl (String NumeroControl)
	{
		set_Value (COLUMNNAME_NumeroControl, NumeroControl);
	}

	/** Get Control Number.
		@return Contains the number of active contro
	  */
	public String getNumeroControl () 
	{
		return (String)get_Value(COLUMNNAME_NumeroControl);
	}

	/** Set Invoice Number.
		@param NumeroFactura 
		Contains the number of the invoice, if you want to add, since it is optional.
	  */
	public void setNumeroFactura (String NumeroFactura)
	{
		set_Value (COLUMNNAME_NumeroFactura, NumeroFactura);
	}

	/** Get Invoice Number.
		@return Contains the number of the invoice, if you want to add, since it is optional.
	  */
	public String getNumeroFactura () 
	{
		return (String)get_Value(COLUMNNAME_NumeroFactura);
	}

	/** Set Price.
		@param Precio Price	  */
	public void setPrecio (String Precio)
	{
		set_Value (COLUMNNAME_Precio, Precio);
	}

	/** Get Price.
		@return Price	  */
	public String getPrecio () 
	{
		return (String)get_Value(COLUMNNAME_Precio);
	}

	/** Set Provanance.
		@param Procedencia 
		Sets from which the active 
	  */
	public void setProcedencia (String Procedencia)
	{
		set_Value (COLUMNNAME_Procedencia, Procedencia);
	}

	/** Get Provanance.
		@return Sets from which the active 
	  */
	public String getProcedencia () 
	{
		return (String)get_Value(COLUMNNAME_Procedencia);
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

	/** Set Resource.
		@param Recurso 
		Describe the type of resource
	  */
	public void setRecurso (boolean Recurso)
	{
		set_Value (COLUMNNAME_Recurso, Boolean.valueOf(Recurso));
	}

	/** Get Resource.
		@return Describe the type of resource
	  */
	public boolean isRecurso () 
	{
		Object oo = get_Value(COLUMNNAME_Recurso);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Search Key.
		@param Value 
		Search key for the record in the format required - must be unique
	  */
	public void setValue (String Value)
	{
		set_Value (COLUMNNAME_Value, Value);
	}

	/** Get Search Key.
		@return Search key for the record in the format required - must be unique
	  */
	public String getValue () 
	{
		return (String)get_Value(COLUMNNAME_Value);
	}
}