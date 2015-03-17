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
import org.compiere.util.KeyNamePair;

/** Generated Model for EXME_Pharmacist
 *  @author Generated Class 
 *  @version Release 1.1.2 - $Id$ */
public class X_EXME_Pharmacist extends PO implements I_EXME_Pharmacist, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_Pharmacist (Properties ctx, int EXME_Pharmacist_ID, String trxName)
    {
      super (ctx, EXME_Pharmacist_ID, trxName);
      /** if (EXME_Pharmacist_ID == 0)
        {
			setAD_User_ID (0);
			setApellido1 (null);
			setC_Location_ID (0);
			setEMail (null);
			setEXME_Pharmacist_ID (0);
			setEXME_Turnos_ID (0);
			setFechaNac (new Timestamp( System.currentTimeMillis() ));
			setName (null);
			setSexo (null);
			setStartDate (new Timestamp( System.currentTimeMillis() ));
			setTitle (null);
			setType (null);
			setValue (null);
        } */
    }

    /** Load Constructor */
    public X_EXME_Pharmacist (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_Pharmacist[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set User/Contact .
		@param AD_User_ID 
		User within the system - Internal or Business Partner Contact
	  */
	public void setAD_User_ID (int AD_User_ID)
	{
		if (AD_User_ID < 1)
			 throw new IllegalArgumentException ("AD_User_ID is mandatory.");
		set_Value (COLUMNNAME_AD_User_ID, Integer.valueOf(AD_User_ID));
	}

	/** Get User/Contact .
		@return User within the system - Internal or Business Partner Contact
	  */
	public int getAD_User_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_User_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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

	/** Set Cellular Phone.
		@param Celular 
		Cellular Phone
	  */
	public void setCelular (String Celular)
	{
		set_Value (COLUMNNAME_Celular, Celular);
	}

	/** Get Cellular Phone.
		@return Cellular Phone
	  */
	public String getCelular () 
	{
		return (String)get_Value(COLUMNNAME_Celular);
	}

	/** Set Address .
		@param C_Location_ID 
		Location or Address
	  */
	public void setC_Location_ID (int C_Location_ID)
	{
		if (C_Location_ID < 1)
			 throw new IllegalArgumentException ("C_Location_ID is mandatory.");
		set_Value (COLUMNNAME_C_Location_ID, Integer.valueOf(C_Location_ID));
	}

	/** Get Address .
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
		if (EMail == null)
			throw new IllegalArgumentException ("EMail is mandatory.");
		set_Value (COLUMNNAME_EMail, EMail);
	}

	/** Get EMail Address.
		@return Electronic Mail Address
	  */
	public String getEMail () 
	{
		return (String)get_Value(COLUMNNAME_EMail);
	}

	/** Set Work email address.
		@param EMailWork 
		Work email address
	  */
	public void setEMailWork (String EMailWork)
	{
		set_Value (COLUMNNAME_EMailWork, EMailWork);
	}

	/** Get Work email address.
		@return Work email address
	  */
	public String getEMailWork () 
	{
		return (String)get_Value(COLUMNNAME_EMailWork);
	}

	public I_EXME_Farmacia getEXME_Farmacia() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_Farmacia.Table_Name);
        I_EXME_Farmacia result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_Farmacia)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Farmacia_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Pharmacy.
		@param EXME_Farmacia_ID Pharmacy	  */
	public void setEXME_Farmacia_ID (int EXME_Farmacia_ID)
	{
		if (EXME_Farmacia_ID < 1) 
			set_Value (COLUMNNAME_EXME_Farmacia_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_Farmacia_ID, Integer.valueOf(EXME_Farmacia_ID));
	}

	/** Get Pharmacy.
		@return Pharmacy	  */
	public int getEXME_Farmacia_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Farmacia_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Pharmacist.
		@param EXME_Pharmacist_ID Pharmacist	  */
	public void setEXME_Pharmacist_ID (int EXME_Pharmacist_ID)
	{
		if (EXME_Pharmacist_ID < 1)
			 throw new IllegalArgumentException ("EXME_Pharmacist_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_Pharmacist_ID, Integer.valueOf(EXME_Pharmacist_ID));
	}

	/** Get Pharmacist.
		@return Pharmacist	  */
	public int getEXME_Pharmacist_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Pharmacist_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_Turnos getEXME_Turnos() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_Turnos.Table_Name);
        I_EXME_Turnos result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_Turnos)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Turnos_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Shift.
		@param EXME_Turnos_ID 
		Shift
	  */
	public void setEXME_Turnos_ID (int EXME_Turnos_ID)
	{
		if (EXME_Turnos_ID < 1)
			 throw new IllegalArgumentException ("EXME_Turnos_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_Turnos_ID, Integer.valueOf(EXME_Turnos_ID));
	}

	/** Get Shift.
		@return Shift
	  */
	public int getEXME_Turnos_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Turnos_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Birth Date.
		@param FechaNac 
		Birth Date
	  */
	public void setFechaNac (Timestamp FechaNac)
	{
		if (FechaNac == null)
			throw new IllegalArgumentException ("FechaNac is mandatory.");
		set_Value (COLUMNNAME_FechaNac, FechaNac);
	}

	/** Get Birth Date.
		@return Birth Date
	  */
	public Timestamp getFechaNac () 
	{
		return (Timestamp)get_Value(COLUMNNAME_FechaNac);
	}

	/** Set Termination Date.
		@param FechaVencimiento Termination Date	  */
	public void setFechaVencimiento (Timestamp FechaVencimiento)
	{
		set_Value (COLUMNNAME_FechaVencimiento, FechaVencimiento);
	}

	/** Get Termination Date.
		@return Termination Date	  */
	public Timestamp getFechaVencimiento () 
	{
		return (Timestamp)get_Value(COLUMNNAME_FechaVencimiento);
	}

	/** Set Social Security Number.
		@param Imss 
		Social Security Number
	  */
	public void setImss (String Imss)
	{
		set_Value (COLUMNNAME_Imss, Imss);
	}

	/** Get Social Security Number.
		@return Social Security Number
	  */
	public String getImss () 
	{
		return (String)get_Value(COLUMNNAME_Imss);
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

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), getName());
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

	/** Set Pager.
		@param Pager Pager	  */
	public void setPager (String Pager)
	{
		set_Value (COLUMNNAME_Pager, Pager);
	}

	/** Get Pager.
		@return Pager	  */
	public String getPager () 
	{
		return (String)get_Value(COLUMNNAME_Pager);
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

	/** Sexo AD_Reference_ID=1000018 */
	public static final int SEXO_AD_Reference_ID=1000018;
	/** Female = F */
	public static final String SEXO_Female = "F";
	/** Male = M */
	public static final String SEXO_Male = "M";
	/** Unassigned = U */
	public static final String SEXO_Unassigned = "U";
	/** Ambiguous = A */
	public static final String SEXO_Ambiguous = "A";
	/** Not Applicable = N */
	public static final String SEXO_NotApplicable = "N";
	/** Other = O */
	public static final String SEXO_Other = "O";
	/** Set Sex.
		@param Sexo 
		Sex
	  */
	public void setSexo (String Sexo)
	{
		if (Sexo == null) throw new IllegalArgumentException ("Sexo is mandatory");
		if (Sexo.equals("F") || Sexo.equals("M") || Sexo.equals("U") || Sexo.equals("A") || Sexo.equals("N") || Sexo.equals("O")); else throw new IllegalArgumentException ("Sexo Invalid value - " + Sexo + " - Reference_ID=1000018 - F - M - U - A - N - O");		set_Value (COLUMNNAME_Sexo, Sexo);
	}

	/** Get Sex.
		@return Sex
	  */
	public String getSexo () 
	{
		return (String)get_Value(COLUMNNAME_Sexo);
	}

	/** Set Start Date.
		@param StartDate 
		First effective day (inclusive)
	  */
	public void setStartDate (Timestamp StartDate)
	{
		if (StartDate == null)
			throw new IllegalArgumentException ("StartDate is mandatory.");
		set_Value (COLUMNNAME_StartDate, StartDate);
	}

	/** Get Start Date.
		@return First effective day (inclusive)
	  */
	public Timestamp getStartDate () 
	{
		return (Timestamp)get_Value(COLUMNNAME_StartDate);
	}

	/** Set Suffix.
		@param Suffix 
		Suffix after the number
	  */
	public void setSuffix (String Suffix)
	{
		set_Value (COLUMNNAME_Suffix, Suffix);
	}

	/** Get Suffix.
		@return Suffix after the number
	  */
	public String getSuffix () 
	{
		return (String)get_Value(COLUMNNAME_Suffix);
	}

	/** Set Home Phone.
		@param TelParticular 
		Home Phone
	  */
	public void setTelParticular (String TelParticular)
	{
		set_Value (COLUMNNAME_TelParticular, TelParticular);
	}

	/** Get Home Phone.
		@return Home Phone
	  */
	public String getTelParticular () 
	{
		return (String)get_Value(COLUMNNAME_TelParticular);
	}

	/** Set Work Phone.
		@param TelTrabajo 
		Work Phone
	  */
	public void setTelTrabajo (String TelTrabajo)
	{
		set_Value (COLUMNNAME_TelTrabajo, TelTrabajo);
	}

	/** Get Work Phone.
		@return Work Phone
	  */
	public String getTelTrabajo () 
	{
		return (String)get_Value(COLUMNNAME_TelTrabajo);
	}

	/** Title AD_Reference_ID=1200508 */
	public static final int TITLE_AD_Reference_ID=1200508;
	/** Mr = Mr */
	public static final String TITLE_Mr = "Mr";
	/** Mrs = Mrs */
	public static final String TITLE_Mrs = "Mrs";
	/** Miss = Miss */
	public static final String TITLE_Miss = "Miss";
	/** Ms = Ms */
	public static final String TITLE_Ms = "Ms";
	/** Set Title.
		@param Title 
		Name this entity is referred to as
	  */
	public void setTitle (String Title)
	{
		if (Title == null) throw new IllegalArgumentException ("Title is mandatory");
		if (Title.equals("Mr") || Title.equals("Mrs") || Title.equals("Miss") || Title.equals("Ms")); else throw new IllegalArgumentException ("Title Invalid value - " + Title + " - Reference_ID=1200508 - Mr - Mrs - Miss - Ms");		set_Value (COLUMNNAME_Title, Title);
	}

	/** Get Title.
		@return Name this entity is referred to as
	  */
	public String getTitle () 
	{
		return (String)get_Value(COLUMNNAME_Title);
	}

	/** Type AD_Reference_ID=1200520 */
	public static final int TYPE_AD_Reference_ID=1200520;
	/** Technician = T */
	public static final String TYPE_Technician = "T";
	/** Certificate = C */
	public static final String TYPE_Certificate = "C";
	/** Set Type.
		@param Type 
		Type of Validation (SQL, Java Script, Java Language)
	  */
	public void setType (String Type)
	{
		if (Type == null) throw new IllegalArgumentException ("Type is mandatory");
		if (Type.equals("T") || Type.equals("C")); else throw new IllegalArgumentException ("Type Invalid value - " + Type + " - Reference_ID=1200520 - T - C");		set_Value (COLUMNNAME_Type, Type);
	}

	/** Get Type.
		@return Type of Validation (SQL, Java Script, Java Language)
	  */
	public String getType () 
	{
		return (String)get_Value(COLUMNNAME_Type);
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
}