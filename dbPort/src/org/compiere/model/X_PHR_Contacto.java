/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

/** Generated Model for PHR_Contacto
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_PHR_Contacto extends PO implements I_PHR_Contacto, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_PHR_Contacto (Properties ctx, int PHR_Contacto_ID, String trxName)
    {
      super (ctx, PHR_Contacto_ID, trxName);
      /** if (PHR_Contacto_ID == 0)
        {
			setApellido1 (null);
			setEsPrincipal (false);
			setEXME_Paciente_ID (0);
			setEXME_Parentesco_ID (0);
			setNombre (null);
			setPHR_Contacto_ID (0);
        } */
    }

    /** Load Constructor */
    public X_PHR_Contacto (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 4 - System 
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
      StringBuffer sb = new StringBuffer ("X_PHR_Contacto[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Last Name.
		@param Apellido1 
		Last Name
	  */
	public void setApellido1 (String Apellido1)
	{
		if (Apellido1 == null)
			throw new IllegalArgumentException ("Apellido1 is mandatory.");
		set_Value (COLUMNNAME_Apellido1, Apellido1);
	}

	/** Get Last Name.
		@return Last Name
	  */
	public String getApellido1 () 
	{
		return (String)get_Value(COLUMNNAME_Apellido1);
	}

	/** Set Mother's Maiden Name.
		@param Apellido2 
		Mother's Maiden Name
	  */
	public void setApellido2 (String Apellido2)
	{
		set_Value (COLUMNNAME_Apellido2, Apellido2);
	}

	/** Get Mother's Maiden Name.
		@return Mother's Maiden Name
	  */
	public String getApellido2 () 
	{
		return (String)get_Value(COLUMNNAME_Apellido2);
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

	/** Set EMail Address.
		@param EMail 
		Electronic Mail Address
	  */
	public void setEMail (String EMail)
	{
		set_Value (COLUMNNAME_EMail, EMail);
	}

	/** Get EMail Address.
		@return Electronic Mail Address
	  */
	public String getEMail () 
	{
		return (String)get_Value(COLUMNNAME_EMail);
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

	public I_EXME_Parentesco getEXME_Parentesco() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_Parentesco.Table_Name);
        I_EXME_Parentesco result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_Parentesco)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Parentesco_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Kinship.
		@param EXME_Parentesco_ID 
		Kinship
	  */
	public void setEXME_Parentesco_ID (int EXME_Parentesco_ID)
	{
		if (EXME_Parentesco_ID < 1)
			 throw new IllegalArgumentException ("EXME_Parentesco_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_Parentesco_ID, Integer.valueOf(EXME_Parentesco_ID));
	}

	/** Get Kinship.
		@return Kinship
	  */
	public int getEXME_Parentesco_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Parentesco_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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

	/** Set Middle Name.
		@param Nombre2 
		Middle name
	  */
	public void setNombre2 (String Nombre2)
	{
		set_Value (COLUMNNAME_Nombre2, Nombre2);
	}

	/** Get Middle Name.
		@return Middle name
	  */
	public String getNombre2 () 
	{
		return (String)get_Value(COLUMNNAME_Nombre2);
	}

	/** Set Emergency Contacts.
		@param PHR_Contacto_ID Emergency Contacts	  */
	public void setPHR_Contacto_ID (int PHR_Contacto_ID)
	{
		if (PHR_Contacto_ID < 1)
			 throw new IllegalArgumentException ("PHR_Contacto_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_PHR_Contacto_ID, Integer.valueOf(PHR_Contacto_ID));
	}

	/** Get Emergency Contacts.
		@return Emergency Contacts	  */
	public int getPHR_Contacto_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_PHR_Contacto_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Home Phone.
		@param TelefonoCasa Home Phone	  */
	public void setTelefonoCasa (String TelefonoCasa)
	{
		set_Value (COLUMNNAME_TelefonoCasa, TelefonoCasa);
	}

	/** Get Home Phone.
		@return Home Phone	  */
	public String getTelefonoCasa () 
	{
		return (String)get_Value(COLUMNNAME_TelefonoCasa);
	}

	/** Set Mobile Phone.
		@param TelefonoMovil Mobile Phone	  */
	public void setTelefonoMovil (String TelefonoMovil)
	{
		set_Value (COLUMNNAME_TelefonoMovil, TelefonoMovil);
	}

	/** Get Mobile Phone.
		@return Mobile Phone	  */
	public String getTelefonoMovil () 
	{
		return (String)get_Value(COLUMNNAME_TelefonoMovil);
	}

	/** Set Work Phone.
		@param TelefonoTrabajo Work Phone	  */
	public void setTelefonoTrabajo (String TelefonoTrabajo)
	{
		set_Value (COLUMNNAME_TelefonoTrabajo, TelefonoTrabajo);
	}

	/** Get Work Phone.
		@return Work Phone	  */
	public String getTelefonoTrabajo () 
	{
		return (String)get_Value(COLUMNNAME_TelefonoTrabajo);
	}
}