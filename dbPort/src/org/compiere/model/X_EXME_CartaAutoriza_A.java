/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;
import java.util.logging.Level;

/** Generated Model for EXME_CartaAutoriza_A
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_CartaAutoriza_A extends PO implements I_EXME_CartaAutoriza_A, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_CartaAutoriza_A (Properties ctx, int EXME_CartaAutoriza_A_ID, String trxName)
    {
      super (ctx, EXME_CartaAutoriza_A_ID, trxName);
      /** if (EXME_CartaAutoriza_A_ID == 0)
        {
			setDateOrdered (new Timestamp( System.currentTimeMillis() ));
			setEXME_CartaAutoriza_A_ID (0);
			setEXME_CartaAutoriza_ID (0);
			setEXME_Paciente_ID (0);
			setTipoCarta (null);
			setVersion (0);
        } */
    }

    /** Load Constructor */
    public X_EXME_CartaAutoriza_A (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 1 - Org 
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
      StringBuffer sb = new StringBuffer ("X_EXME_CartaAutoriza_A[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set User/Contact.
		@param AD_User_ID 
		User within the system - Internal or Business Partner Contact
	  */
	public void setAD_User_ID (int AD_User_ID)
	{
		if (AD_User_ID < 1) 
			set_Value (COLUMNNAME_AD_User_ID, null);
		else 
			set_Value (COLUMNNAME_AD_User_ID, Integer.valueOf(AD_User_ID));
	}

	/** Get User/Contact.
		@return User within the system - Internal or Business Partner Contact
	  */
	public int getAD_User_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_User_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Date Ordered.
		@param DateOrdered 
		Date of Order
	  */
	public void setDateOrdered (Timestamp DateOrdered)
	{
		if (DateOrdered == null)
			throw new IllegalArgumentException ("DateOrdered is mandatory.");
		set_Value (COLUMNNAME_DateOrdered, DateOrdered);
	}

	/** Get Date Ordered.
		@return Date of Order
	  */
	public Timestamp getDateOrdered () 
	{
		return (Timestamp)get_Value(COLUMNNAME_DateOrdered);
	}

	/** Set Date printed.
		@param DatePrinted 
		Date the document was printed.
	  */
	public void setDatePrinted (Timestamp DatePrinted)
	{
		set_Value (COLUMNNAME_DatePrinted, DatePrinted);
	}

	/** Get Date printed.
		@return Date the document was printed.
	  */
	public Timestamp getDatePrinted () 
	{
		return (Timestamp)get_Value(COLUMNNAME_DatePrinted);
	}

	/** Set Donors.
		@param Donadores 
		Donors
	  */
	public void setDonadores (int Donadores)
	{
		set_Value (COLUMNNAME_Donadores, Integer.valueOf(Donadores));
	}

	/** Get Donors.
		@return Donors
	  */
	public int getDonadores () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Donadores);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Audit Authorization Letter.
		@param EXME_CartaAutoriza_A_ID 
		Audit Authorization Letter
	  */
	public void setEXME_CartaAutoriza_A_ID (int EXME_CartaAutoriza_A_ID)
	{
		if (EXME_CartaAutoriza_A_ID < 1)
			 throw new IllegalArgumentException ("EXME_CartaAutoriza_A_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_CartaAutoriza_A_ID, Integer.valueOf(EXME_CartaAutoriza_A_ID));
	}

	/** Get Audit Authorization Letter.
		@return Audit Authorization Letter
	  */
	public int getEXME_CartaAutoriza_A_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_CartaAutoriza_A_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_CartaAutoriza getEXME_CartaAutoriza() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_CartaAutoriza.Table_Name);
        I_EXME_CartaAutoriza result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_CartaAutoriza)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_CartaAutoriza_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Authorization Letter.
		@param EXME_CartaAutoriza_ID 
		Authorization Letter
	  */
	public void setEXME_CartaAutoriza_ID (int EXME_CartaAutoriza_ID)
	{
		if (EXME_CartaAutoriza_ID < 1)
			 throw new IllegalArgumentException ("EXME_CartaAutoriza_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_CartaAutoriza_ID, Integer.valueOf(EXME_CartaAutoriza_ID));
	}

	/** Get Authorization Letter.
		@return Authorization Letter
	  */
	public int getEXME_CartaAutoriza_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_CartaAutoriza_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Patient.
		@param EXME_Paciente_ID 
		Patient
	  */
	public void setEXME_Paciente_ID (int EXME_Paciente_ID)
	{
		if (EXME_Paciente_ID < 1)
			 throw new IllegalArgumentException ("EXME_Paciente_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_Paciente_ID, Integer.valueOf(EXME_Paciente_ID));
	}

	/** Get Patient.
		@return Patient
	  */
	public int getEXME_Paciente_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Paciente_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Witness 1.
		@param Testigo1 
		Witness 1
	  */
	public void setTestigo1 (String Testigo1)
	{
		set_Value (COLUMNNAME_Testigo1, Testigo1);
	}

	/** Get Witness 1.
		@return Witness 1
	  */
	public String getTestigo1 () 
	{
		return (String)get_Value(COLUMNNAME_Testigo1);
	}

	/** Set Witness 2.
		@param Testigo2 
		Witness 2
	  */
	public void setTestigo2 (String Testigo2)
	{
		set_Value (COLUMNNAME_Testigo2, Testigo2);
	}

	/** Get Witness 2.
		@return Witness 2
	  */
	public String getTestigo2 () 
	{
		return (String)get_Value(COLUMNNAME_Testigo2);
	}

	/** TipoCarta AD_Reference_ID=1200024 */
	public static final int TIPOCARTA_AD_Reference_ID=1200024;
	/** Child or Handicapped Person = MD */
	public static final String TIPOCARTA_ChildOrHandicappedPerson = "MD";
	/** Responsible and Authorization = RA */
	public static final String TIPOCARTA_ResponsibleAndAuthorization = "RA";
	/** Set Letter Type.
		@param TipoCarta 
		Letter Type
	  */
	public void setTipoCarta (String TipoCarta)
	{
		if (TipoCarta == null) throw new IllegalArgumentException ("TipoCarta is mandatory");
		if (TipoCarta.equals("MD") || TipoCarta.equals("RA")); else throw new IllegalArgumentException ("TipoCarta Invalid value - " + TipoCarta + " - Reference_ID=1200024 - MD - RA");		set_Value (COLUMNNAME_TipoCarta, TipoCarta);
	}

	/** Get Letter Type.
		@return Letter Type
	  */
	public String getTipoCarta () 
	{
		return (String)get_Value(COLUMNNAME_TipoCarta);
	}

	/** Set Version.
		@param Version 
		Version of the table definition
	  */
	public void setVersion (int Version)
	{
		set_Value (COLUMNNAME_Version, Integer.valueOf(Version));
	}

	/** Get Version.
		@return Version of the table definition
	  */
	public int getVersion () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Version);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}