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

/** Generated Model for EXME_Morgue
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_Morgue extends PO implements I_EXME_Morgue, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_Morgue (Properties ctx, int EXME_Morgue_ID, String trxName)
    {
      super (ctx, EXME_Morgue_ID, trxName);
      /** if (EXME_Morgue_ID == 0)
        {
			setEXME_CtaPac_ID (0);
			setEXME_Diagnostico_ID (0);
			setEXME_Funeraria_ID (0);
			setEXME_MedicoForense_ID (0);
			setEXME_Medico_ID (0);
			setEXME_Morgue_ID (0);
			setEXME_Paciente_ID (0);
			setEXME_Res_Ministerio_ID (0);
			setFecha_Muerte (new Timestamp( System.currentTimeMillis() ));
			setIsReclamado (false);
			setNum_Acta_Defuncion (null);
			setValue (null);
        } */
    }

    /** Load Constructor */
    public X_EXME_Morgue (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_Morgue[")
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

	/** Set Comment.
		@param Comentario Comment	  */
	public void setComentario (String Comentario)
	{
		set_Value (COLUMNNAME_Comentario, Comentario);
	}

	/** Get Comment.
		@return Comment	  */
	public String getComentario () 
	{
		return (String)get_Value(COLUMNNAME_Comentario);
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

	/** Set Patient Account.
		@param EXME_CtaPac_ID 
		Patient Account
	  */
	public void setEXME_CtaPac_ID (int EXME_CtaPac_ID)
	{
		if (EXME_CtaPac_ID < 1)
			 throw new IllegalArgumentException ("EXME_CtaPac_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_CtaPac_ID, Integer.valueOf(EXME_CtaPac_ID));
	}

	/** Get Patient Account.
		@return Patient Account
	  */
	public int getEXME_CtaPac_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_CtaPac_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Diagnosis.
		@param EXME_Diagnostico_ID 
		Diagnosis
	  */
	public void setEXME_Diagnostico_ID (int EXME_Diagnostico_ID)
	{
		if (EXME_Diagnostico_ID < 1)
			 throw new IllegalArgumentException ("EXME_Diagnostico_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_Diagnostico_ID, Integer.valueOf(EXME_Diagnostico_ID));
	}

	/** Get Diagnosis.
		@return Diagnosis
	  */
	public int getEXME_Diagnostico_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Diagnostico_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Second Diagnostic.
		@param EXME_Diagnostico2_ID 
		Second Diagnostic
	  */
	public void setEXME_Diagnostico2_ID (int EXME_Diagnostico2_ID)
	{
		if (EXME_Diagnostico2_ID < 1) 
			set_Value (COLUMNNAME_EXME_Diagnostico2_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_Diagnostico2_ID, Integer.valueOf(EXME_Diagnostico2_ID));
	}

	/** Get Second Diagnostic.
		@return Second Diagnostic
	  */
	public int getEXME_Diagnostico2_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Diagnostico2_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Funeral.
		@param EXME_Funeraria_ID 
		Funeral
	  */
	public void setEXME_Funeraria_ID (int EXME_Funeraria_ID)
	{
		if (EXME_Funeraria_ID < 1)
			 throw new IllegalArgumentException ("EXME_Funeraria_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_Funeraria_ID, Integer.valueOf(EXME_Funeraria_ID));
	}

	/** Get Funeral.
		@return Funeral
	  */
	public int getEXME_Funeraria_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Funeraria_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Forensic Doctor.
		@param EXME_MedicoForense_ID 
		Forensic Doctor
	  */
	public void setEXME_MedicoForense_ID (int EXME_MedicoForense_ID)
	{
		if (EXME_MedicoForense_ID < 1)
			 throw new IllegalArgumentException ("EXME_MedicoForense_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_MedicoForense_ID, Integer.valueOf(EXME_MedicoForense_ID));
	}

	/** Get Forensic Doctor.
		@return Forensic Doctor
	  */
	public int getEXME_MedicoForense_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_MedicoForense_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Doctor.
		@param EXME_Medico_ID 
		Doctor
	  */
	public void setEXME_Medico_ID (int EXME_Medico_ID)
	{
		if (EXME_Medico_ID < 1)
			 throw new IllegalArgumentException ("EXME_Medico_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_Medico_ID, Integer.valueOf(EXME_Medico_ID));
	}

	/** Get Doctor.
		@return Doctor
	  */
	public int getEXME_Medico_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Medico_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_Ministerio getEXME_Ministerio() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_Ministerio.Table_Name);
        I_EXME_Ministerio result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_Ministerio)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Ministerio_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Ministry.
		@param EXME_Ministerio_ID 
		Ministry
	  */
	public void setEXME_Ministerio_ID (int EXME_Ministerio_ID)
	{
		if (EXME_Ministerio_ID < 1) 
			set_Value (COLUMNNAME_EXME_Ministerio_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_Ministerio_ID, Integer.valueOf(EXME_Ministerio_ID));
	}

	/** Get Ministry.
		@return Ministry
	  */
	public int getEXME_Ministerio_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Ministerio_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Morgue.
		@param EXME_Morgue_ID 
		Morgue
	  */
	public void setEXME_Morgue_ID (int EXME_Morgue_ID)
	{
		if (EXME_Morgue_ID < 1)
			 throw new IllegalArgumentException ("EXME_Morgue_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_Morgue_ID, Integer.valueOf(EXME_Morgue_ID));
	}

	/** Get Morgue.
		@return Morgue
	  */
	public int getEXME_Morgue_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Morgue_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Death Cause.
		@param EXME_MotivoMuerte_ID 
		Death Cause
	  */
	public void setEXME_MotivoMuerte_ID (int EXME_MotivoMuerte_ID)
	{
		if (EXME_MotivoMuerte_ID < 1) 
			set_Value (COLUMNNAME_EXME_MotivoMuerte_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_MotivoMuerte_ID, Integer.valueOf(EXME_MotivoMuerte_ID));
	}

	/** Get Death Cause.
		@return Death Cause
	  */
	public int getEXME_MotivoMuerte_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_MotivoMuerte_ID);
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

	/** Set Kinship.
		@param EXME_Parentesco_ID 
		Kinship
	  */
	public void setEXME_Parentesco_ID (int EXME_Parentesco_ID)
	{
		if (EXME_Parentesco_ID < 1) 
			set_Value (COLUMNNAME_EXME_Parentesco_ID, null);
		else 
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

	/** Set Responsible Ministry.
		@param EXME_Res_Ministerio_ID 
		Responsables de Ministerios
	  */
	public void setEXME_Res_Ministerio_ID (int EXME_Res_Ministerio_ID)
	{
		if (EXME_Res_Ministerio_ID < 1)
			 throw new IllegalArgumentException ("EXME_Res_Ministerio_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_Res_Ministerio_ID, Integer.valueOf(EXME_Res_Ministerio_ID));
	}

	/** Get Responsible Ministry.
		@return Responsables de Ministerios
	  */
	public int getEXME_Res_Ministerio_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Res_Ministerio_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Date of Death.
		@param Fecha_Muerte 
		Date of Death
	  */
	public void setFecha_Muerte (Timestamp Fecha_Muerte)
	{
		if (Fecha_Muerte == null)
			throw new IllegalArgumentException ("Fecha_Muerte is mandatory.");
		set_Value (COLUMNNAME_Fecha_Muerte, Fecha_Muerte);
	}

	/** Get Date of Death.
		@return Date of Death
	  */
	public Timestamp getFecha_Muerte () 
	{
		return (Timestamp)get_Value(COLUMNNAME_Fecha_Muerte);
	}

	/** Set Delivered Date.
		@param Fecha_Salida 
		Delivered Date
	  */
	public void setFecha_Salida (Timestamp Fecha_Salida)
	{
		set_Value (COLUMNNAME_Fecha_Salida, Fecha_Salida);
	}

	/** Get Delivered Date.
		@return Delivered Date
	  */
	public Timestamp getFecha_Salida () 
	{
		return (Timestamp)get_Value(COLUMNNAME_Fecha_Salida);
	}

	/** Set Printed.
		@param IsPrinted 
		Indicates if this document / line is printed
	  */
	public void setIsPrinted (String IsPrinted)
	{
		set_Value (COLUMNNAME_IsPrinted, IsPrinted);
	}

	/** Get Printed.
		@return Indicates if this document / line is printed
	  */
	public String getIsPrinted () 
	{
		return (String)get_Value(COLUMNNAME_IsPrinted);
	}

	/** Set Is claimed.
		@param IsReclamado 
		Is claimed
	  */
	public void setIsReclamado (boolean IsReclamado)
	{
		set_Value (COLUMNNAME_IsReclamado, Boolean.valueOf(IsReclamado));
	}

	/** Get Is claimed.
		@return Is claimed
	  */
	public boolean isReclamado () 
	{
		Object oo = get_Value(COLUMNNAME_IsReclamado);
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

	/** Set Relative Name.
		@param Nombre_Pariente 
		Relative Name
	  */
	public void setNombre_Pariente (String Nombre_Pariente)
	{
		set_Value (COLUMNNAME_Nombre_Pariente, Nombre_Pariente);
	}

	/** Get Relative Name.
		@return Relative Name
	  */
	public String getNombre_Pariente () 
	{
		return (String)get_Value(COLUMNNAME_Nombre_Pariente);
	}

	/** Set Death Act Number.
		@param Num_Acta_Defuncion 
		Death Act Number
	  */
	public void setNum_Acta_Defuncion (String Num_Acta_Defuncion)
	{
		if (Num_Acta_Defuncion == null)
			throw new IllegalArgumentException ("Num_Acta_Defuncion is mandatory.");
		set_Value (COLUMNNAME_Num_Acta_Defuncion, Num_Acta_Defuncion);
	}

	/** Get Death Act Number.
		@return Death Act Number
	  */
	public String getNum_Acta_Defuncion () 
	{
		return (String)get_Value(COLUMNNAME_Num_Acta_Defuncion);
	}

	/** Set Notes.
		@param Observaciones 
		Notes
	  */
	public void setObservaciones (String Observaciones)
	{
		set_Value (COLUMNNAME_Observaciones, Observaciones);
	}

	/** Get Notes.
		@return Notes
	  */
	public String getObservaciones () 
	{
		return (String)get_Value(COLUMNNAME_Observaciones);
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