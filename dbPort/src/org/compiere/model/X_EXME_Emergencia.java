/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;

/** Generated Model for EXME_Emergencia
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_Emergencia extends PO implements I_EXME_Emergencia, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_Emergencia (Properties ctx, int EXME_Emergencia_ID, String trxName)
    {
      super (ctx, EXME_Emergencia_ID, trxName);
      /** if (EXME_Emergencia_ID == 0)
        {
			setDescription (null);
			setEXME_Emergencia_ID (0);
        } */
    }

    /** Load Constructor */
    public X_EXME_Emergencia (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_Emergencia[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Description.
		@param Description 
		Optional short description of the record
	  */
	public void setDescription (String Description)
	{
		if (Description == null)
			throw new IllegalArgumentException ("Description is mandatory.");
		set_Value (COLUMNNAME_Description, Description);
	}

	/** Get Description.
		@return Optional short description of the record
	  */
	public String getDescription () 
	{
		return (String)get_Value(COLUMNNAME_Description);
	}

	/** Set Emergency.
		@param EXME_Emergencia_ID Emergency	  */
	public void setEXME_Emergencia_ID (int EXME_Emergencia_ID)
	{
		if (EXME_Emergencia_ID < 1)
			 throw new IllegalArgumentException ("EXME_Emergencia_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_Emergencia_ID, Integer.valueOf(EXME_Emergencia_ID));
	}

	/** Get Emergency.
		@return Emergency	  */
	public int getEXME_Emergencia_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Emergencia_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Ending Date.
		@param Fecha_Fin 
		Date of ending of intervention
	  */
	public void setFecha_Fin (Timestamp Fecha_Fin)
	{
		set_Value (COLUMNNAME_Fecha_Fin, Fecha_Fin);
	}

	/** Get Ending Date.
		@return Date of ending of intervention
	  */
	public Timestamp getFecha_Fin () 
	{
		return (Timestamp)get_Value(COLUMNNAME_Fecha_Fin);
	}

	/** Set Initial Date.
		@param Fecha_Ini 
		Initial Date
	  */
	public void setFecha_Ini (Timestamp Fecha_Ini)
	{
		set_Value (COLUMNNAME_Fecha_Ini, Fecha_Ini);
	}

	/** Get Initial Date.
		@return Initial Date
	  */
	public Timestamp getFecha_Ini () 
	{
		return (Timestamp)get_Value(COLUMNNAME_Fecha_Ini);
	}

	/** Set Started By.
		@param IniciadoPor Started By	  */
	public void setIniciadoPor (int IniciadoPor)
	{
		set_Value (COLUMNNAME_IniciadoPor, Integer.valueOf(IniciadoPor));
	}

	/** Get Started By.
		@return Started By	  */
	public int getIniciadoPor () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_IniciadoPor);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Finished By.
		@param TerminadoPor Finished By	  */
	public void setTerminadoPor (int TerminadoPor)
	{
		set_Value (COLUMNNAME_TerminadoPor, Integer.valueOf(TerminadoPor));
	}

	/** Get Finished By.
		@return Finished By	  */
	public int getTerminadoPor () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_TerminadoPor);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}