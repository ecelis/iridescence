/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

/** Generated Model for EXME_ConfigFE
 *  @author Generated Class 
 *  @version Version @VERSION@ - $Id$ */
public class X_EXME_ConfigFE extends PO implements I_EXME_ConfigFE, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_ConfigFE (Properties ctx, int EXME_ConfigFE_ID, String trxName)
    {
      super (ctx, EXME_ConfigFE_ID, trxName);
      /** if (EXME_ConfigFE_ID == 0)
        {
			setEXME_ConfigFE_ID (0);
			setIsInProduction (false);
			setPrintFacturaElect (false);
        } */
    }

    /** Load Constructor */
    public X_EXME_ConfigFE (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_ConfigFE[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public I_AD_Process getAD_Process() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_AD_Process.Table_Name);
        I_AD_Process result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_AD_Process)constructor.newInstance(new Object[] {getCtx(), new Integer(getAD_Process_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Process.
		@param AD_Process_ID 
		Process or Report
	  */
	public void setAD_Process_ID (int AD_Process_ID)
	{
		if (AD_Process_ID < 1) 
			set_Value (COLUMNNAME_AD_Process_ID, null);
		else 
			set_Value (COLUMNNAME_AD_Process_ID, Integer.valueOf(AD_Process_ID));
	}

	/** Get Process.
		@return Process or Report
	  */
	public int getAD_Process_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_Process_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Characters Permitted.
		@param CaracPermitidos Characters Permitted	  */
	public void setCaracPermitidos (String CaracPermitidos)
	{
		set_Value (COLUMNNAME_CaracPermitidos, CaracPermitidos);
	}

	/** Get Characters Permitted.
		@return Characters Permitted	  */
	public String getCaracPermitidos () 
	{
		return (String)get_Value(COLUMNNAME_CaracPermitidos);
	}

	/** Set Configuration of Electronic Invoice.
		@param EXME_ConfigFE_ID Configuration of Electronic Invoice	  */
	public void setEXME_ConfigFE_ID (int EXME_ConfigFE_ID)
	{
		if (EXME_ConfigFE_ID < 1)
			 throw new IllegalArgumentException ("EXME_ConfigFE_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_ConfigFE_ID, Integer.valueOf(EXME_ConfigFE_ID));
	}

	/** Get Configuration of Electronic Invoice.
		@return Configuration of Electronic Invoice	  */
	public int getEXME_ConfigFE_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_ConfigFE_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set In Production.
		@param IsInProduction 
		The system is in production
	  */
	public void setIsInProduction (boolean IsInProduction)
	{
		set_Value (COLUMNNAME_IsInProduction, Boolean.valueOf(IsInProduction));
	}

	/** Get In Production.
		@return The system is in production
	  */
	public boolean isInProduction () 
	{
		Object oo = get_Value(COLUMNNAME_IsInProduction);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Leyend1.
		@param Leyenda1 Leyend1	  */
	public void setLeyenda1 (String Leyenda1)
	{
		set_Value (COLUMNNAME_Leyenda1, Leyenda1);
	}

	/** Get Leyend1.
		@return Leyend1	  */
	public String getLeyenda1 () 
	{
		return (String)get_Value(COLUMNNAME_Leyenda1);
	}

	/** Set Leyend2.
		@param Leyenda2 Leyend2	  */
	public void setLeyenda2 (String Leyenda2)
	{
		set_Value (COLUMNNAME_Leyenda2, Leyenda2);
	}

	/** Get Leyend2.
		@return Leyend2	  */
	public String getLeyenda2 () 
	{
		return (String)get_Value(COLUMNNAME_Leyenda2);
	}

	/** Set Leyend3.
		@param Leyenda3 Leyend3	  */
	public void setLeyenda3 (String Leyenda3)
	{
		set_Value (COLUMNNAME_Leyenda3, Leyenda3);
	}

	/** Get Leyend3.
		@return Leyend3	  */
	public String getLeyenda3 () 
	{
		return (String)get_Value(COLUMNNAME_Leyenda3);
	}

	/** Set Leyend4.
		@param Leyenda4 Leyend4	  */
	public void setLeyenda4 (String Leyenda4)
	{
		set_Value (COLUMNNAME_Leyenda4, Leyenda4);
	}

	/** Get Leyend4.
		@return Leyend4	  */
	public String getLeyenda4 () 
	{
		return (String)get_Value(COLUMNNAME_Leyenda4);
	}

	/** Set Password.
		@param Password 
		Password of any length (case sensitive)
	  */
	public void setPassword (String Password)
	{
		set_ValueE (COLUMNNAME_Password, Password);
	}

	/** Get Password.
		@return Password of any length (case sensitive)
	  */
	public String getPassword () 
	{
		return (String)get_ValueE(COLUMNNAME_Password);
	}

	/** Set Password Private Key.
		@param PasswordPK 
		Password Private Key
	  */
	public void setPasswordPK (String PasswordPK)
	{
		set_ValueE (COLUMNNAME_PasswordPK, PasswordPK);
	}

	/** Get Password Private Key.
		@return Password Private Key
	  */
	public String getPasswordPK () 
	{
		return (String)get_ValueE(COLUMNNAME_PasswordPK);
	}

	/** Set Print Electronic Invoice.
		@param PrintFacturaElect Print Electronic Invoice	  */
	public void setPrintFacturaElect (boolean PrintFacturaElect)
	{
		set_Value (COLUMNNAME_PrintFacturaElect, Boolean.valueOf(PrintFacturaElect));
	}

	/** Get Print Electronic Invoice.
		@return Print Electronic Invoice	  */
	public boolean isPrintFacturaElect () 
	{
		Object oo = get_Value(COLUMNNAME_PrintFacturaElect);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set RFC Foreigner.
		@param RFC_Extranjero RFC Foreigner	  */
	public void setRFC_Extranjero (String RFC_Extranjero)
	{
		set_Value (COLUMNNAME_RFC_Extranjero, RFC_Extranjero);
	}

	/** Get RFC Foreigner.
		@return RFC Foreigner	  */
	public String getRFC_Extranjero () 
	{
		return (String)get_Value(COLUMNNAME_RFC_Extranjero);
	}

	/** Set National RFC.
		@param RFC_Nacional National RFC	  */
	public void setRFC_Nacional (String RFC_Nacional)
	{
		set_Value (COLUMNNAME_RFC_Nacional, RFC_Nacional);
	}

	/** Get National RFC.
		@return National RFC	  */
	public String getRFC_Nacional () 
	{
		return (String)get_Value(COLUMNNAME_RFC_Nacional);
	}

	/** Set Serial Invoice Document.
		@param SerieDocFac Serial Invoice Document	  */
	public void setSerieDocFac (String SerieDocFac)
	{
		set_Value (COLUMNNAME_SerieDocFac, SerieDocFac);
	}

	/** Get Serial Invoice Document.
		@return Serial Invoice Document	  */
	public String getSerieDocFac () 
	{
		return (String)get_Value(COLUMNNAME_SerieDocFac);
	}

	/** Set Serial of Credit Note.
		@param SerieDocNC Serial of Credit Note	  */
	public void setSerieDocNC (String SerieDocNC)
	{
		set_Value (COLUMNNAME_SerieDocNC, SerieDocNC);
	}

	/** Get Serial of Credit Note.
		@return Serial of Credit Note	  */
	public String getSerieDocNC () 
	{
		return (String)get_Value(COLUMNNAME_SerieDocNC);
	}

	/** Set Serial of Debit Note.
		@param SerieDocND Serial of Debit Note	  */
	public void setSerieDocND (String SerieDocND)
	{
		set_Value (COLUMNNAME_SerieDocND, SerieDocND);
	}

	/** Get Serial of Debit Note.
		@return Serial of Debit Note	  */
	public String getSerieDocND () 
	{
		return (String)get_Value(COLUMNNAME_SerieDocND);
	}

	/** Set Stamp Invoice.
		@param StampInvoice Stamp Invoice	  */
	public void setStampInvoice (boolean StampInvoice)
	{
		set_Value (COLUMNNAME_StampInvoice, Boolean.valueOf(StampInvoice));
	}

	/** Get Stamp Invoice.
		@return Stamp Invoice	  */
	public boolean isStampInvoice () 
	{
		Object oo = get_Value(COLUMNNAME_StampInvoice);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Registered EMail.
		@param UserName 
		Email of the responsible for the System
	  */
	public void setUserName (String UserName)
	{
		set_Value (COLUMNNAME_UserName, UserName);
	}

	/** Get Registered EMail.
		@return Email of the responsible for the System
	  */
	public String getUserName () 
	{
		return (String)get_Value(COLUMNNAME_UserName);
	}
}