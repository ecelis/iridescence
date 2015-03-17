/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;

/** Generated Model for I_EXME_Socios_Negocios
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_I_EXME_Socios_Negocios extends PO implements I_I_EXME_Socios_Negocios, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_I_EXME_Socios_Negocios (Properties ctx, int I_EXME_Socios_Negocios_ID, String trxName)
    {
      super (ctx, I_EXME_Socios_Negocios_ID, trxName);
      /** if (I_EXME_Socios_Negocios_ID == 0)
        {
			setI_EXME_Socios_Negocios_ID (0);
        } */
    }

    /** Load Constructor */
    public X_I_EXME_Socios_Negocios (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_I_EXME_Socios_Negocios[")
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
		@param Ciudad 
		description of a city
	  */
	public void setCiudad (String Ciudad)
	{
		set_Value (COLUMNNAME_Ciudad, Ciudad);
	}

	/** Get City.
		@return description of a city
	  */
	public String getCiudad () 
	{
		return (String)get_Value(COLUMNNAME_Ciudad);
	}

	/** Set Customer at Charge.
		@param Cliente_A_Cargo Customer at Charge	  */
	public void setCliente_A_Cargo (String Cliente_A_Cargo)
	{
		set_Value (COLUMNNAME_Cliente_A_Cargo, Cliente_A_Cargo);
	}

	/** Get Customer at Charge.
		@return Customer at Charge	  */
	public String getCliente_A_Cargo () 
	{
		return (String)get_Value(COLUMNNAME_Cliente_A_Cargo);
	}

	/** Set Codigo_Impuestos.
		@param Codigo_Impuestos Codigo_Impuestos	  */
	public void setCodigo_Impuestos (String Codigo_Impuestos)
	{
		set_Value (COLUMNNAME_Codigo_Impuestos, Codigo_Impuestos);
	}

	/** Get Codigo_Impuestos.
		@return Codigo_Impuestos	  */
	public String getCodigo_Impuestos () 
	{
		return (String)get_Value(COLUMNNAME_Codigo_Impuestos);
	}

	/** Set Suburb / District.
		@param Colonia 
		Suburb / District
	  */
	public void setColonia (String Colonia)
	{
		set_Value (COLUMNNAME_Colonia, Colonia);
	}

	/** Get Suburb / District.
		@return Suburb / District
	  */
	public String getColonia () 
	{
		return (String)get_Value(COLUMNNAME_Colonia);
	}

	/** Set Delegacion.
		@param Delegacion Delegacion	  */
	public void setDelegacion (String Delegacion)
	{
		set_Value (COLUMNNAME_Delegacion, Delegacion);
	}

	/** Get Delegacion.
		@return Delegacion	  */
	public String getDelegacion () 
	{
		return (String)get_Value(COLUMNNAME_Delegacion);
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

	/** Set Estado_Activo.
		@param Estado_Activo Estado_Activo	  */
	public void setEstado_Activo (String Estado_Activo)
	{
		set_Value (COLUMNNAME_Estado_Activo, Estado_Activo);
	}

	/** Get Estado_Activo.
		@return Estado_Activo	  */
	public String getEstado_Activo () 
	{
		return (String)get_Value(COLUMNNAME_Estado_Activo);
	}

	/** Set Identifier.
		@param Identificador Identifier	  */
	public void setIdentificador (String Identificador)
	{
		set_Value (COLUMNNAME_Identificador, Identificador);
	}

	/** Get Identifier.
		@return Identifier	  */
	public String getIdentificador () 
	{
		return (String)get_Value(COLUMNNAME_Identificador);
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

	/** Set I_EXME_Socios_Negocios_ID.
		@param I_EXME_Socios_Negocios_ID I_EXME_Socios_Negocios_ID	  */
	public void setI_EXME_Socios_Negocios_ID (int I_EXME_Socios_Negocios_ID)
	{
		if (I_EXME_Socios_Negocios_ID < 1)
			 throw new IllegalArgumentException ("I_EXME_Socios_Negocios_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_I_EXME_Socios_Negocios_ID, Integer.valueOf(I_EXME_Socios_Negocios_ID));
	}

	/** Get I_EXME_Socios_Negocios_ID.
		@return I_EXME_Socios_Negocios_ID	  */
	public int getI_EXME_Socios_Negocios_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_I_EXME_Socios_Negocios_ID);
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

	/** Set Phone.
		@param Phone 
		Identifies a telephone number
	  */
	public void setPhone (String Phone)
	{
		set_Value (COLUMNNAME_Phone, Phone);
	}

	/** Get Phone.
		@return Identifies a telephone number
	  */
	public String getPhone () 
	{
		return (String)get_Value(COLUMNNAME_Phone);
	}

	/** Set ZIP.
		@param Postal 
		Postal code
	  */
	public void setPostal (String Postal)
	{
		set_Value (COLUMNNAME_Postal, Postal);
	}

	/** Get ZIP.
		@return Postal code
	  */
	public String getPostal () 
	{
		return (String)get_Value(COLUMNNAME_Postal);
	}

	/** Set List Price.
		@param PriceList 
		List Price
	  */
	public void setPriceList (BigDecimal PriceList)
	{
		set_Value (COLUMNNAME_PriceList, PriceList);
	}

	/** Get List Price.
		@return List Price
	  */
	public BigDecimal getPriceList () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_PriceList);
		if (bd == null)
			 return Env.ZERO;
		return bd;
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

	/** Set Customer Reference.
		@param Referencia_Cliente Customer Reference	  */
	public void setReferencia_Cliente (String Referencia_Cliente)
	{
		set_Value (COLUMNNAME_Referencia_Cliente, Referencia_Cliente);
	}

	/** Get Customer Reference.
		@return Customer Reference	  */
	public String getReferencia_Cliente () 
	{
		return (String)get_Value(COLUMNNAME_Referencia_Cliente);
	}

	/** Set Reference Address.
		@param Referencia_Direccion Reference Address	  */
	public void setReferencia_Direccion (String Referencia_Direccion)
	{
		set_Value (COLUMNNAME_Referencia_Direccion, Referencia_Direccion);
	}

	/** Get Reference Address.
		@return Reference Address	  */
	public String getReferencia_Direccion () 
	{
		return (String)get_Value(COLUMNNAME_Referencia_Direccion);
	}

	/** Set Taxpayer Identification Number.
		@param Rfc 
		Taxpayer Identification Number
	  */
	public void setRfc (String Rfc)
	{
		set_Value (COLUMNNAME_Rfc, Rfc);
	}

	/** Get Taxpayer Identification Number.
		@return Taxpayer Identification Number
	  */
	public String getRfc () 
	{
		return (String)get_Value(COLUMNNAME_Rfc);
	}

	/** Set Termino_Convenio.
		@param Termino_Convenio Termino_Convenio	  */
	public void setTermino_Convenio (String Termino_Convenio)
	{
		set_Value (COLUMNNAME_Termino_Convenio, Termino_Convenio);
	}

	/** Get Termino_Convenio.
		@return Termino_Convenio	  */
	public String getTermino_Convenio () 
	{
		return (String)get_Value(COLUMNNAME_Termino_Convenio);
	}

	/** Set Client Type.
		@param TipoCliente Client Type	  */
	public void setTipoCliente (String TipoCliente)
	{
		set_Value (COLUMNNAME_TipoCliente, TipoCliente);
	}

	/** Get Client Type.
		@return Client Type	  */
	public String getTipoCliente () 
	{
		return (String)get_Value(COLUMNNAME_TipoCliente);
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