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

/** Generated Model for EXME_Acervo
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_Acervo extends PO implements I_EXME_Acervo, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_Acervo (Properties ctx, int EXME_Acervo_ID, String trxName)
    {
      super (ctx, EXME_Acervo_ID, trxName);
      /** if (EXME_Acervo_ID == 0)
        {
			setEXME_Acervo_ID (0);
			setEXME_ArchCli_ID (0);
			setEXME_ArchCliMov_ID (0);
			setEXME_TipoExped_ID (0);
			setHist_Pac (null);
			setM_Warehouse_ID (0);
			setName_Pac (null);
        } */
    }

    /** Load Constructor */
    public X_EXME_Acervo (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_Acervo[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Acquis.
		@param EXME_Acervo_ID Acquis	  */
	public void setEXME_Acervo_ID (int EXME_Acervo_ID)
	{
		if (EXME_Acervo_ID < 1)
			 throw new IllegalArgumentException ("EXME_Acervo_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_Acervo_ID, Integer.valueOf(EXME_Acervo_ID));
	}

	/** Get Acquis.
		@return Acquis	  */
	public int getEXME_Acervo_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Acervo_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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

	public I_EXME_ArchCliMov getEXME_ArchCliMov() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_ArchCliMov.Table_Name);
        I_EXME_ArchCliMov result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_ArchCliMov)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_ArchCliMov_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Movement of Expedient.
		@param EXME_ArchCliMov_ID Movement of Expedient	  */
	public void setEXME_ArchCliMov_ID (int EXME_ArchCliMov_ID)
	{
		if (EXME_ArchCliMov_ID < 1)
			 throw new IllegalArgumentException ("EXME_ArchCliMov_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_ArchCliMov_ID, Integer.valueOf(EXME_ArchCliMov_ID));
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

	/** Set Date Acquis Income .
		@param Fecha_IngAcervo Date Acquis Income 	  */
	public void setFecha_IngAcervo (Timestamp Fecha_IngAcervo)
	{
		set_Value (COLUMNNAME_Fecha_IngAcervo, Fecha_IngAcervo);
	}

	/** Get Date Acquis Income .
		@return Date Acquis Income 	  */
	public Timestamp getFecha_IngAcervo () 
	{
		return (Timestamp)get_Value(COLUMNNAME_Fecha_IngAcervo);
	}

	/** Set Date of entry of File .
		@param Fecha_IngArch Date of entry of File 	  */
	public void setFecha_IngArch (Timestamp Fecha_IngArch)
	{
		set_Value (COLUMNNAME_Fecha_IngArch, Fecha_IngArch);
	}

	/** Get Date of entry of File .
		@return Date of entry of File 	  */
	public Timestamp getFecha_IngArch () 
	{
		return (Timestamp)get_Value(COLUMNNAME_Fecha_IngArch);
	}

	/** Set Acquis Release Date.
		@param Fecha_SalAcervo Acquis Release Date	  */
	public void setFecha_SalAcervo (Timestamp Fecha_SalAcervo)
	{
		set_Value (COLUMNNAME_Fecha_SalAcervo, Fecha_SalAcervo);
	}

	/** Get Acquis Release Date.
		@return Acquis Release Date	  */
	public Timestamp getFecha_SalAcervo () 
	{
		return (Timestamp)get_Value(COLUMNNAME_Fecha_SalAcervo);
	}

	/** Set File Exit Date.
		@param Fecha_SalArch File Exit Date	  */
	public void setFecha_SalArch (Timestamp Fecha_SalArch)
	{
		set_Value (COLUMNNAME_Fecha_SalArch, Fecha_SalArch);
	}

	/** Get File Exit Date.
		@return File Exit Date	  */
	public Timestamp getFecha_SalArch () 
	{
		return (Timestamp)get_Value(COLUMNNAME_Fecha_SalArch);
	}

	/** Set Pacient History.
		@param Hist_Pac Pacient History	  */
	public void setHist_Pac (String Hist_Pac)
	{
		if (Hist_Pac == null)
			throw new IllegalArgumentException ("Hist_Pac is mandatory.");
		set_Value (COLUMNNAME_Hist_Pac, Hist_Pac);
	}

	/** Get Pacient History.
		@return Pacient History	  */
	public String getHist_Pac () 
	{
		return (String)get_Value(COLUMNNAME_Hist_Pac);
	}

	/** Set Ing_Acervo.
		@param Ing_Acervo Ing_Acervo	  */
	public void setIng_Acervo (boolean Ing_Acervo)
	{
		set_Value (COLUMNNAME_Ing_Acervo, Boolean.valueOf(Ing_Acervo));
	}

	/** Get Ing_Acervo.
		@return Ing_Acervo	  */
	public boolean isIng_Acervo () 
	{
		Object oo = get_Value(COLUMNNAME_Ing_Acervo);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Ing_Archivo.
		@param Ing_Archivo Ing_Archivo	  */
	public void setIng_Archivo (boolean Ing_Archivo)
	{
		set_Value (COLUMNNAME_Ing_Archivo, Boolean.valueOf(Ing_Archivo));
	}

	/** Get Ing_Archivo.
		@return Ing_Archivo	  */
	public boolean isIng_Archivo () 
	{
		Object oo = get_Value(COLUMNNAME_Ing_Archivo);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
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
			 throw new IllegalArgumentException ("M_Warehouse_ID is mandatory.");
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

	/** Set Patient Name.
		@param Name_Pac Patient Name	  */
	public void setName_Pac (String Name_Pac)
	{
		if (Name_Pac == null)
			throw new IllegalArgumentException ("Name_Pac is mandatory.");
		set_Value (COLUMNNAME_Name_Pac, Name_Pac);
	}

	/** Get Patient Name.
		@return Patient Name	  */
	public String getName_Pac () 
	{
		return (String)get_Value(COLUMNNAME_Name_Pac);
	}

	/** Set Sal_Acervo.
		@param Sal_Acervo Sal_Acervo	  */
	public void setSal_Acervo (boolean Sal_Acervo)
	{
		set_Value (COLUMNNAME_Sal_Acervo, Boolean.valueOf(Sal_Acervo));
	}

	/** Get Sal_Acervo.
		@return Sal_Acervo	  */
	public boolean isSal_Acervo () 
	{
		Object oo = get_Value(COLUMNNAME_Sal_Acervo);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Sal_Archivo.
		@param Sal_Archivo Sal_Archivo	  */
	public void setSal_Archivo (boolean Sal_Archivo)
	{
		set_Value (COLUMNNAME_Sal_Archivo, Boolean.valueOf(Sal_Archivo));
	}

	/** Get Sal_Archivo.
		@return Sal_Archivo	  */
	public boolean isSal_Archivo () 
	{
		Object oo = get_Value(COLUMNNAME_Sal_Archivo);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set User_IngAcervo.
		@param User_IngAcervo User_IngAcervo	  */
	public void setUser_IngAcervo (String User_IngAcervo)
	{
		set_Value (COLUMNNAME_User_IngAcervo, User_IngAcervo);
	}

	/** Get User_IngAcervo.
		@return User_IngAcervo	  */
	public String getUser_IngAcervo () 
	{
		return (String)get_Value(COLUMNNAME_User_IngAcervo);
	}

	/** Set User IngArch.
		@param User_IngArch User IngArch	  */
	public void setUser_IngArch (String User_IngArch)
	{
		set_Value (COLUMNNAME_User_IngArch, User_IngArch);
	}

	/** Get User IngArch.
		@return User IngArch	  */
	public String getUser_IngArch () 
	{
		return (String)get_Value(COLUMNNAME_User_IngArch);
	}

	/** Set User_SalAcervo.
		@param User_SalAcervo User_SalAcervo	  */
	public void setUser_SalAcervo (String User_SalAcervo)
	{
		set_Value (COLUMNNAME_User_SalAcervo, User_SalAcervo);
	}

	/** Get User_SalAcervo.
		@return User_SalAcervo	  */
	public String getUser_SalAcervo () 
	{
		return (String)get_Value(COLUMNNAME_User_SalAcervo);
	}

	/** Set User_SalArchivo.
		@param User_SalArchivo User_SalArchivo	  */
	public void setUser_SalArchivo (String User_SalArchivo)
	{
		set_Value (COLUMNNAME_User_SalArchivo, User_SalArchivo);
	}

	/** Get User_SalArchivo.
		@return User_SalArchivo	  */
	public String getUser_SalArchivo () 
	{
		return (String)get_Value(COLUMNNAME_User_SalArchivo);
	}
}