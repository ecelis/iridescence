/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

/** Generated Model for PHR_Hospital
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_PHR_Hospital extends PO implements I_PHR_Hospital, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_PHR_Hospital (Properties ctx, int PHR_Hospital_ID, String trxName)
    {
      super (ctx, PHR_Hospital_ID, trxName);
      /** if (PHR_Hospital_ID == 0)
        {
			setEXME_Paciente_ID (0);
			setNombre (null);
			setPHR_Hospital_ID (0);
			setTelefono (null);
        } */
    }

    /** Load Constructor */
    public X_PHR_Hospital (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 9 - System 
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
      StringBuffer sb = new StringBuffer ("X_PHR_Hospital[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Address.
		@param C_Location_ID 
		Location or Address
	  */
	public void setC_Location_ID (int C_Location_ID)
	{
		if (C_Location_ID < 1) 
			set_Value (COLUMNNAME_C_Location_ID, null);
		else 
			set_Value (COLUMNNAME_C_Location_ID, Integer.valueOf(C_Location_ID));
	}

	/** Get Address.
		@return Location or Address
	  */
	public int getC_Location_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Location_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Primary physician.
		@param EsPrincipal 
		Primary physician
	  */
	public void setEsPrincipal (boolean EsPrincipal)
	{
		set_Value (COLUMNNAME_EsPrincipal, Boolean.valueOf(EsPrincipal));
	}

	/** Get Primary physician.
		@return Primary physician
	  */
	public boolean isEsPrincipal () 
	{
		Object oo = get_Value(COLUMNNAME_EsPrincipal);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	public I_EXME_Paciente getEXME_Paciente() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_Paciente.Table_Name);
        I_EXME_Paciente result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_Paciente)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Paciente_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
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

	/** Set Fax.
		@param Fax 
		Facsimile number
	  */
	public void setFax (String Fax)
	{
		set_Value (COLUMNNAME_Fax, Fax);
	}

	/** Get Fax.
		@return Facsimile number
	  */
	public String getFax () 
	{
		return (String)get_Value(COLUMNNAME_Fax);
	}

	/** Set Name.
		@param Nombre 
		Name of friend
	  */
	public void setNombre (String Nombre)
	{
		if (Nombre == null)
			throw new IllegalArgumentException ("Nombre is mandatory.");
		set_Value (COLUMNNAME_Nombre, Nombre);
	}

	/** Get Name.
		@return Name of friend
	  */
	public String getNombre () 
	{
		return (String)get_Value(COLUMNNAME_Nombre);
	}

	/** Set Patient Hospital.
		@param PHR_Hospital_ID Patient Hospital	  */
	public void setPHR_Hospital_ID (int PHR_Hospital_ID)
	{
		if (PHR_Hospital_ID < 1)
			 throw new IllegalArgumentException ("PHR_Hospital_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_PHR_Hospital_ID, Integer.valueOf(PHR_Hospital_ID));
	}

	/** Get Patient Hospital.
		@return Patient Hospital	  */
	public int getPHR_Hospital_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_PHR_Hospital_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Telephone.
		@param Telefono 
		friend telephone
	  */
	public void setTelefono (String Telefono)
	{
		if (Telefono == null)
			throw new IllegalArgumentException ("Telefono is mandatory.");
		set_Value (COLUMNNAME_Telefono, Telefono);
	}

	/** Get Telephone.
		@return friend telephone
	  */
	public String getTelefono () 
	{
		return (String)get_Value(COLUMNNAME_Telefono);
	}
}