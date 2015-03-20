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

/** Generated Model for EXME_ArchCliMov
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_ArchCliMov extends PO implements I_EXME_ArchCliMov, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_ArchCliMov (Properties ctx, int EXME_ArchCliMov_ID, String trxName)
    {
      super (ctx, EXME_ArchCliMov_ID, trxName);
      /** if (EXME_ArchCliMov_ID == 0)
        {
			setAD_User_ID (0);
			setDocumentNo (null);
			setEnArchivo (false);
			setEXME_ArchCli_ID (0);
			setEXME_ArchCliMov_ID (0);
			setEXME_TipoExped_ID (0);
			setExtemporaneo (false);
			setFechaSolicitud (new Timestamp( System.currentTimeMillis() ));
			setName (null);
			setUbicacion (null);
        } */
    }

    /** Load Constructor */
    public X_EXME_ArchCliMov (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_ArchCliMov[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public I_AD_User getAD_User() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_AD_User.Table_Name);
        I_AD_User result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_AD_User)constructor.newInstance(new Object[] {getCtx(), new Integer(getAD_User_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set User/Contact.
		@param AD_User_ID 
		User within the system - Internal or Business Partner Contact
	  */
	public void setAD_User_ID (int AD_User_ID)
	{
		if (AD_User_ID < 1)
			 throw new IllegalArgumentException ("AD_User_ID is mandatory.");
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

	/** Set Document No.
		@param DocumentNo 
		Document sequence number of the document
	  */
	public void setDocumentNo (String DocumentNo)
	{
		if (DocumentNo == null)
			throw new IllegalArgumentException ("DocumentNo is mandatory.");
		set_Value (COLUMNNAME_DocumentNo, DocumentNo);
	}

	/** Get Document No.
		@return Document sequence number of the document
	  */
	public String getDocumentNo () 
	{
		return (String)get_Value(COLUMNNAME_DocumentNo);
	}

	/** Set InArchive.
		@param EnArchivo InArchive	  */
	public void setEnArchivo (boolean EnArchivo)
	{
		set_Value (COLUMNNAME_EnArchivo, Boolean.valueOf(EnArchivo));
	}

	/** Get InArchive.
		@return InArchive	  */
	public boolean isEnArchivo () 
	{
		Object oo = get_Value(COLUMNNAME_EnArchivo);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Status.
		@param Estatus 
		Status
	  */
	public void setEstatus (String Estatus)
	{
		set_Value (COLUMNNAME_Estatus, Estatus);
	}

	/** Get Status.
		@return Status
	  */
	public String getEstatus () 
	{
		return (String)get_Value(COLUMNNAME_Estatus);
	}

	public I_EXME_ArchCli getEXME_ArchCli() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_ArchCli.Table_Name);
        I_EXME_ArchCli result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_ArchCli)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_ArchCli_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Clinical Archive.
		@param EXME_ArchCli_ID 
		Clinical Archive
	  */
	public void setEXME_ArchCli_ID (int EXME_ArchCli_ID)
	{
		if (EXME_ArchCli_ID < 1)
			 throw new IllegalArgumentException ("EXME_ArchCli_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_ArchCli_ID, Integer.valueOf(EXME_ArchCli_ID));
	}

	/** Get Clinical Archive.
		@return Clinical Archive
	  */
	public int getEXME_ArchCli_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_ArchCli_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Movement of Expedient.
		@param EXME_ArchCliMov_ID Movement of Expedient	  */
	public void setEXME_ArchCliMov_ID (int EXME_ArchCliMov_ID)
	{
		if (EXME_ArchCliMov_ID < 1)
			 throw new IllegalArgumentException ("EXME_ArchCliMov_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_ArchCliMov_ID, Integer.valueOf(EXME_ArchCliMov_ID));
	}

	/** Get Movement of Expedient.
		@return Movement of Expedient	  */
	public int getEXME_ArchCliMov_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_ArchCliMov_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_Especialidad getEXME_Especialidad() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_Especialidad.Table_Name);
        I_EXME_Especialidad result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_Especialidad)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Especialidad_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Specialty.
		@param EXME_Especialidad_ID 
		Specialty
	  */
	public void setEXME_Especialidad_ID (int EXME_Especialidad_ID)
	{
		if (EXME_Especialidad_ID < 1) 
			set_Value (COLUMNNAME_EXME_Especialidad_ID, null);
		else 
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
			set_Value (COLUMNNAME_EXME_Paciente_ID, null);
		else 
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

	public I_EXME_TipoExped getEXME_TipoExped() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_TipoExped.Table_Name);
        I_EXME_TipoExped result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_TipoExped)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_TipoExped_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Type of Medical Record.
		@param EXME_TipoExped_ID 
		Type of Medical Record
	  */
	public void setEXME_TipoExped_ID (int EXME_TipoExped_ID)
	{
		if (EXME_TipoExped_ID < 1)
			 throw new IllegalArgumentException ("EXME_TipoExped_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_TipoExped_ID, Integer.valueOf(EXME_TipoExped_ID));
	}

	/** Get Type of Medical Record.
		@return Type of Medical Record
	  */
	public int getEXME_TipoExped_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_TipoExped_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Medical Record.
		@param Expediente 
		Medical Record
	  */
	public void setExpediente (String Expediente)
	{
		set_Value (COLUMNNAME_Expediente, Expediente);
	}

	/** Get Medical Record.
		@return Medical Record
	  */
	public String getExpediente () 
	{
		return (String)get_Value(COLUMNNAME_Expediente);
	}

	/** Set Extemporaneo.
		@param Extemporaneo 
		Extemporaneous
	  */
	public void setExtemporaneo (boolean Extemporaneo)
	{
		set_Value (COLUMNNAME_Extemporaneo, Boolean.valueOf(Extemporaneo));
	}

	/** Get Extemporaneo.
		@return Extemporaneous
	  */
	public boolean isExtemporaneo () 
	{
		Object oo = get_Value(COLUMNNAME_Extemporaneo);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Date Request.
		@param FechaSolicitud Date Request	  */
	public void setFechaSolicitud (Timestamp FechaSolicitud)
	{
		if (FechaSolicitud == null)
			throw new IllegalArgumentException ("FechaSolicitud is mandatory.");
		set_Value (COLUMNNAME_FechaSolicitud, FechaSolicitud);
	}

	/** Get Date Request.
		@return Date Request	  */
	public Timestamp getFechaSolicitud () 
	{
		return (Timestamp)get_Value(COLUMNNAME_FechaSolicitud);
	}

	public I_M_Warehouse getM_Warehouse() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_M_Warehouse.Table_Name);
        I_M_Warehouse result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_M_Warehouse)constructor.newInstance(new Object[] {getCtx(), new Integer(getM_Warehouse_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Warehouse.
		@param M_Warehouse_ID 
		Storage Warehouse and Service Point
	  */
	public void setM_Warehouse_ID (int M_Warehouse_ID)
	{
		if (M_Warehouse_ID < 1) 
			set_Value (COLUMNNAME_M_Warehouse_ID, null);
		else 
			set_Value (COLUMNNAME_M_Warehouse_ID, Integer.valueOf(M_Warehouse_ID));
	}

	/** Get Warehouse.
		@return Storage Warehouse and Service Point
	  */
	public int getM_Warehouse_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_Warehouse_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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

	/** Set Patient Name.
		@param Nombre_Pac 
		Patient Name
	  */
	public void setNombre_Pac (String Nombre_Pac)
	{
		set_Value (COLUMNNAME_Nombre_Pac, Nombre_Pac);
	}

	/** Get Patient Name.
		@return Patient Name
	  */
	public String getNombre_Pac () 
	{
		return (String)get_Value(COLUMNNAME_Nombre_Pac);
	}

	/** Set Location of Element.
		@param Ubicacion 
		Location of Element
	  */
	public void setUbicacion (String Ubicacion)
	{
		if (Ubicacion == null)
			throw new IllegalArgumentException ("Ubicacion is mandatory.");
		set_Value (COLUMNNAME_Ubicacion, Ubicacion);
	}

	/** Get Location of Element.
		@return Location of Element
	  */
	public String getUbicacion () 
	{
		return (String)get_Value(COLUMNNAME_Ubicacion);
	}
}