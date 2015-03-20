/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;
import org.compiere.util.KeyNamePair;

/** Generated Model for EXME_MO_Radiografias
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_MO_Radiografias extends PO implements I_EXME_MO_Radiografias, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_MO_Radiografias (Properties ctx, int EXME_MO_Radiografias_ID, String trxName)
    {
      super (ctx, EXME_MO_Radiografias_ID, trxName);
      /** if (EXME_MO_Radiografias_ID == 0)
        {
			setEXME_MO_Radiografias_ID (0);
			setEXME_Paciente_ID (0);
			setImagen (null);
			setName (null);
        } */
    }

    /** Load Constructor */
    public X_EXME_MO_Radiografias (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_MO_Radiografias[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public I_EXME_CitaMedica getEXME_CitaMedica() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_CitaMedica.Table_Name);
        I_EXME_CitaMedica result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_CitaMedica)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_CitaMedica_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Medical Appointment.
		@param EXME_CitaMedica_ID 
		Medical appointment
	  */
	public void setEXME_CitaMedica_ID (int EXME_CitaMedica_ID)
	{
		if (EXME_CitaMedica_ID < 1) 
			set_Value (COLUMNNAME_EXME_CitaMedica_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_CitaMedica_ID, Integer.valueOf(EXME_CitaMedica_ID));
	}

	/** Get Medical Appointment.
		@return Medical appointment
	  */
	public int getEXME_CitaMedica_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_CitaMedica_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set X-Ray.
		@param EXME_MO_Radiografias_ID X-Ray	  */
	public void setEXME_MO_Radiografias_ID (int EXME_MO_Radiografias_ID)
	{
		if (EXME_MO_Radiografias_ID < 1)
			 throw new IllegalArgumentException ("EXME_MO_Radiografias_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_MO_Radiografias_ID, Integer.valueOf(EXME_MO_Radiografias_ID));
	}

	/** Get X-Ray.
		@return X-Ray	  */
	public int getEXME_MO_Radiografias_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_MO_Radiografias_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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

	/** Set Image.
		@param Imagen 
		Name of stored image
	  */
	public void setImagen (byte[] Imagen)
	{
		if (Imagen == null)
			throw new IllegalArgumentException ("Imagen is mandatory.");
		set_Value (COLUMNNAME_Imagen, Imagen);
	}

	/** Get Image.
		@return Name of stored image
	  */
	public byte[] getImagen () 
	{
		return (byte[])get_Value(COLUMNNAME_Imagen);
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

	/** Set Note.
		@param Nota 
		Note
	  */
	public void setNota (String Nota)
	{
		set_Value (COLUMNNAME_Nota, Nota);
	}

	/** Get Note.
		@return Note
	  */
	public String getNota () 
	{
		return (String)get_Value(COLUMNNAME_Nota);
	}
}