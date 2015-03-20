/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;

/** Generated Model for EXME_Expediente_Historia_V
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_Expediente_Historia_V extends PO implements I_EXME_Expediente_Historia_V, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_Expediente_Historia_V (Properties ctx, int EXME_Expediente_Historia_V_ID, String trxName)
    {
      super (ctx, EXME_Expediente_Historia_V_ID, trxName);
      /** if (EXME_Expediente_Historia_V_ID == 0)
        {
			setHistoria (null);
        } */
    }

    /** Load Constructor */
    public X_EXME_Expediente_Historia_V (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_Expediente_Historia_V[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Patient Account.
		@param CtaPac 
		Patient Account
	  */
	public void setCtaPac (String CtaPac)
	{
		set_ValueNoCheck (COLUMNNAME_CtaPac, CtaPac);
	}

	/** Get Patient Account.
		@return Patient Account
	  */
	public String getCtaPac () 
	{
		return (String)get_Value(COLUMNNAME_CtaPac);
	}

	/** Set File - History.
		@param EXME_Expediente_Historia_V_ID 
		File - History
	  */
	public void setEXME_Expediente_Historia_V_ID (int EXME_Expediente_Historia_V_ID)
	{
		if (EXME_Expediente_Historia_V_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_EXME_Expediente_Historia_V_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_EXME_Expediente_Historia_V_ID, Integer.valueOf(EXME_Expediente_Historia_V_ID));
	}

	/** Get File - History.
		@return File - History
	  */
	public int getEXME_Expediente_Historia_V_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Expediente_Historia_V_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Medical Record.
		@param Expediente 
		Medical Record
	  */
	public void setExpediente (int Expediente)
	{
		set_ValueNoCheck (COLUMNNAME_Expediente, Integer.valueOf(Expediente));
	}

	/** Get Medical Record.
		@return Medical Record
	  */
	public int getExpediente () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Expediente);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Unique Patient Identification.
		@param Historia 
		Unique Patient Identification
	  */
	public void setHistoria (String Historia)
	{
		if (Historia == null)
			throw new IllegalArgumentException ("Historia is mandatory.");
		set_ValueNoCheck (COLUMNNAME_Historia, Historia);
	}

	/** Get Unique Patient Identification.
		@return Unique Patient Identification
	  */
	public String getHistoria () 
	{
		return (String)get_Value(COLUMNNAME_Historia);
	}

	/** Set Is On Loan.
		@param IsPrestado 
		Is On Loan
	  */
	public void setIsPrestado (boolean IsPrestado)
	{
		set_Value (COLUMNNAME_IsPrestado, Boolean.valueOf(IsPrestado));
	}

	/** Get Is On Loan.
		@return Is On Loan
	  */
	public boolean isPrestado () 
	{
		Object oo = get_Value(COLUMNNAME_IsPrestado);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Patient Name.
		@param Nombre_Pac 
		Patient Name
	  */
	public void setNombre_Pac (String Nombre_Pac)
	{
		set_ValueNoCheck (COLUMNNAME_Nombre_Pac, Nombre_Pac);
	}

	/** Get Patient Name.
		@return Patient Name
	  */
	public String getNombre_Pac () 
	{
		return (String)get_Value(COLUMNNAME_Nombre_Pac);
	}
}