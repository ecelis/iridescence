/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.util.Env;

/** Generated Model for EXME_Pregunta
 *  @author Generated Class 
 *  @version Version @VERSION@ - $Id$ */
public class X_EXME_Pregunta extends PO implements I_EXME_Pregunta, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_Pregunta (Properties ctx, int EXME_Pregunta_ID, String trxName)
    {
      super (ctx, EXME_Pregunta_ID, trxName);
      /** if (EXME_Pregunta_ID == 0)
        {
			setEsCore (false);
			setEXME_Especialidad_ID (0);
			setEXME_Pregunta_ID (0);
			setEXME_TipoPregunta_ID (0);
			setMultiple (false);
			setName (null);
			setObligatoria (false);
			setTipoDato (null);
			setValue (null);
        } */
    }

    /** Load Constructor */
    public X_EXME_Pregunta (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_Pregunta[")
        .append(get_ID()).append("]");
      return sb.toString();
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

	/** Set Public Domain.
		@param EsCore 
		Public Domain
	  */
	public void setEsCore (boolean EsCore)
	{
		set_Value (COLUMNNAME_EsCore, Boolean.valueOf(EsCore));
	}

	/** Get Public Domain.
		@return Public Domain
	  */
	public boolean isEsCore () 
	{
		Object oo = get_Value(COLUMNNAME_EsCore);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Specialty.
		@param EXME_Especialidad_ID 
		Specialty
	  */
	public void setEXME_Especialidad_ID (int EXME_Especialidad_ID)
	{
		if (EXME_Especialidad_ID < 1)
			 throw new IllegalArgumentException ("EXME_Especialidad_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_Especialidad_ID, Integer.valueOf(EXME_Especialidad_ID));
	}

	/** Get Specialty.
		@return Specialty
	  */
	public int getEXME_Especialidad_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Especialidad_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Order Set.
		@param EXME_OrderSet_ID Order Set	  */
	public void setEXME_OrderSet_ID (int EXME_OrderSet_ID)
	{
		if (EXME_OrderSet_ID < 1) 
			set_Value (COLUMNNAME_EXME_OrderSet_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_OrderSet_ID, Integer.valueOf(EXME_OrderSet_ID));
	}

	/** Get Order Set.
		@return Order Set	  */
	public int getEXME_OrderSet_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_OrderSet_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Question.
		@param EXME_Pregunta_ID 
		Question
	  */
	public void setEXME_Pregunta_ID (int EXME_Pregunta_ID)
	{
		if (EXME_Pregunta_ID < 1)
			 throw new IllegalArgumentException ("EXME_Pregunta_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_Pregunta_ID, Integer.valueOf(EXME_Pregunta_ID));
	}

	/** Get Question.
		@return Question
	  */
	public int getEXME_Pregunta_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Pregunta_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Type of Question.
		@param EXME_TipoPregunta_ID 
		Type of Question
	  */
	public void setEXME_TipoPregunta_ID (int EXME_TipoPregunta_ID)
	{
		if (EXME_TipoPregunta_ID < 1)
			 throw new IllegalArgumentException ("EXME_TipoPregunta_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_TipoPregunta_ID, Integer.valueOf(EXME_TipoPregunta_ID));
	}

	/** Get Type of Question.
		@return Type of Question
	  */
	public int getEXME_TipoPregunta_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_TipoPregunta_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set File Content.
		@param FileContent File Content	  */
	public void setFileContent (byte[] FileContent)
	{
		set_Value (COLUMNNAME_FileContent, FileContent);
	}

	/** Get File Content.
		@return File Content	  */
	public byte[] getFileContent () 
	{
		return (byte[])get_Value(COLUMNNAME_FileContent);
	}

	/** Set Hide Label.
		@param HideLabel Hide Label	  */
	public void setHideLabel (boolean HideLabel)
	{
		set_Value (COLUMNNAME_HideLabel, Boolean.valueOf(HideLabel));
	}

	/** Get Hide Label.
		@return Hide Label	  */
	public boolean isHideLabel () 
	{
		Object oo = get_Value(COLUMNNAME_HideLabel);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Message.
		@param Mensaje Message	  */
	public void setMensaje (String Mensaje)
	{
		set_Value (COLUMNNAME_Mensaje, Mensaje);
	}

	/** Get Message.
		@return Message	  */
	public String getMensaje () 
	{
		return (String)get_Value(COLUMNNAME_Mensaje);
	}

	/** Set Message First Value.
		@param Mensaje_Valor1 Message First Value	  */
	public void setMensaje_Valor1 (BigDecimal Mensaje_Valor1)
	{
		set_Value (COLUMNNAME_Mensaje_Valor1, Mensaje_Valor1);
	}

	/** Get Message First Value.
		@return Message First Value	  */
	public BigDecimal getMensaje_Valor1 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Mensaje_Valor1);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Message Second Value.
		@param Mensaje_Valor2 Message Second Value	  */
	public void setMensaje_Valor2 (BigDecimal Mensaje_Valor2)
	{
		set_Value (COLUMNNAME_Mensaje_Valor2, Mensaje_Valor2);
	}

	/** Get Message Second Value.
		@return Message Second Value	  */
	public BigDecimal getMensaje_Valor2 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Mensaje_Valor2);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Multiple.
		@param Multiple Multiple	  */
	public void setMultiple (boolean Multiple)
	{
		set_Value (COLUMNNAME_Multiple, Boolean.valueOf(Multiple));
	}

	/** Get Multiple.
		@return Multiple	  */
	public boolean isMultiple () 
	{
		Object oo = get_Value(COLUMNNAME_Multiple);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
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

	/** Set Columns.
		@param NColumns Columns	  */
	public void setNColumns (int NColumns)
	{
		set_Value (COLUMNNAME_NColumns, Integer.valueOf(NColumns));
	}

	/** Get Columns.
		@return Columns	  */
	public int getNColumns () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_NColumns);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Rows.
		@param NRows Rows	  */
	public void setNRows (int NRows)
	{
		set_Value (COLUMNNAME_NRows, Integer.valueOf(NRows));
	}

	/** Get Rows.
		@return Rows	  */
	public int getNRows () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_NRows);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Mandatory.
		@param Obligatoria 
		Mandatory
	  */
	public void setObligatoria (boolean Obligatoria)
	{
		set_Value (COLUMNNAME_Obligatoria, Boolean.valueOf(Obligatoria));
	}

	/** Get Mandatory.
		@return Mandatory
	  */
	public boolean isObligatoria () 
	{
		Object oo = get_Value(COLUMNNAME_Obligatoria);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Page Size.
		@param PageSize Page Size	  */
	public void setPageSize (int PageSize)
	{
		set_Value (COLUMNNAME_PageSize, Integer.valueOf(PageSize));
	}

	/** Get Page Size.
		@return Page Size	  */
	public int getPageSize () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_PageSize);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Print Text.
		@param PrintName 
		The label text to be printed on a document or correspondence.
	  */
	public void setPrintName (String PrintName)
	{
		set_Value (COLUMNNAME_PrintName, PrintName);
	}

	/** Get Print Text.
		@return The label text to be printed on a document or correspondence.
	  */
	public String getPrintName () 
	{
		return (String)get_Value(COLUMNNAME_PrintName);
	}

	/** Set Image Route.
		@param RutaImagen 
		Image Route
	  */
	public void setRutaImagen (String RutaImagen)
	{
		set_Value (COLUMNNAME_RutaImagen, RutaImagen);
	}

	/** Get Image Route.
		@return Image Route
	  */
	public String getRutaImagen () 
	{
		return (String)get_Value(COLUMNNAME_RutaImagen);
	}

	/** TipoDato AD_Reference_ID=1000030 */
	public static final int TIPODATO_AD_Reference_ID=1000030;
	/** Decimal = D */
	public static final String TIPODATO_Decimal = "D";
	/** Integer = E */
	public static final String TIPODATO_Integer = "E";
	/** Text = T */
	public static final String TIPODATO_Text = "T";
	/** Date = F */
	public static final String TIPODATO_Date = "F";
	/** Image = I */
	public static final String TIPODATO_Image = "I";
	/** Logial = L */
	public static final String TIPODATO_Logial = "L";
	/** Option List = C */
	public static final String TIPODATO_OptionList = "C";
	/** Text Area = A */
	public static final String TIPODATO_TextArea = "A";
	/** Image Binary = B */
	public static final String TIPODATO_ImageBinary = "B";
	/** Multi Options = O */
	public static final String TIPODATO_MultiOptions = "O";
	/** Fixed Image = X */
	public static final String TIPODATO_FixedImage = "X";
	/** Label = LA */
	public static final String TIPODATO_Label = "LA";
	/** Set Data Type.
		@param TipoDato 
		Data Type
	  */
	public void setTipoDato (String TipoDato)
	{
		if (TipoDato == null) throw new IllegalArgumentException ("TipoDato is mandatory");
		if (TipoDato.equals("D") || TipoDato.equals("E") || TipoDato.equals("T") || TipoDato.equals("F") || TipoDato.equals("I") || TipoDato.equals("L") || TipoDato.equals("C") || TipoDato.equals("A") || TipoDato.equals("B") || TipoDato.equals("O") || TipoDato.equals("X") || TipoDato.equals("LA")); else throw new IllegalArgumentException ("TipoDato Invalid value - " + TipoDato + " - Reference_ID=1000030 - D - E - T - F - I - L - C - A - B - O - X - LA");		set_Value (COLUMNNAME_TipoDato, TipoDato);
	}

	/** Get Data Type.
		@return Data Type
	  */
	public String getTipoDato () 
	{
		return (String)get_Value(COLUMNNAME_TipoDato);
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

	/** Set Aisle (X).
		@param X 
		X dimension, e.g., Aisle
	  */
	public void setX (int X)
	{
		set_Value (COLUMNNAME_X, Integer.valueOf(X));
	}

	/** Get Aisle (X).
		@return X dimension, e.g., Aisle
	  */
	public int getX () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_X);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Bin (Y).
		@param Y 
		Y dimension, e.g., Bin
	  */
	public void setY (int Y)
	{
		set_Value (COLUMNNAME_Y, Integer.valueOf(Y));
	}

	/** Get Bin (Y).
		@return Y dimension, e.g., Bin
	  */
	public int getY () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Y);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}