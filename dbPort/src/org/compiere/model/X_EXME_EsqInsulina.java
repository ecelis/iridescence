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

/** Generated Model for EXME_EsqInsulina
 *  @author Generated Class 
 *  @version Version @VERSION@ - $Id$ */
public class X_EXME_EsqInsulina extends PO implements I_EXME_EsqInsulina, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_EsqInsulina (Properties ctx, int EXME_EsqInsulina_ID, String trxName)
    {
      super (ctx, EXME_EsqInsulina_ID, trxName);
      /** if (EXME_EsqInsulina_ID == 0)
        {
			setEXME_EsqInsulina_ID (0);
			setLim_Inferior (0);
			setLim_Superior (0);
			setUnidad (0);
        } */
    }

    /** Load Constructor */
    public X_EXME_EsqInsulina (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_EsqInsulina[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public I_EXME_CtaPac getEXME_CtaPac() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_CtaPac.Table_Name);
        I_EXME_CtaPac result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_CtaPac)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_CtaPac_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Encounter.
		@param EXME_CtaPac_ID 
		Encounter
	  */
	public void setEXME_CtaPac_ID (int EXME_CtaPac_ID)
	{
		if (EXME_CtaPac_ID < 1) 
			set_Value (COLUMNNAME_EXME_CtaPac_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_CtaPac_ID, Integer.valueOf(EXME_CtaPac_ID));
	}

	/** Get Encounter.
		@return Encounter
	  */
	public int getEXME_CtaPac_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_CtaPac_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Insulin Scheme.
		@param EXME_EsqInsulina_ID Insulin Scheme	  */
	public void setEXME_EsqInsulina_ID (int EXME_EsqInsulina_ID)
	{
		if (EXME_EsqInsulina_ID < 1)
			 throw new IllegalArgumentException ("EXME_EsqInsulina_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_EsqInsulina_ID, Integer.valueOf(EXME_EsqInsulina_ID));
	}

	/** Get Insulin Scheme.
		@return Insulin Scheme	  */
	public int getEXME_EsqInsulina_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_EsqInsulina_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_GenProduct getEXME_GenProduct() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_GenProduct.Table_Name);
        I_EXME_GenProduct result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_GenProduct)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_GenProduct_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Generic Product.
		@param EXME_GenProduct_ID Generic Product	  */
	public void setEXME_GenProduct_ID (int EXME_GenProduct_ID)
	{
		if (EXME_GenProduct_ID < 1) 
			set_Value (COLUMNNAME_EXME_GenProduct_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_GenProduct_ID, Integer.valueOf(EXME_GenProduct_ID));
	}

	/** Get Generic Product.
		@return Generic Product	  */
	public int getEXME_GenProduct_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_GenProduct_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_Medico getEXME_Medico() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_Medico.Table_Name);
        I_EXME_Medico result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_Medico)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Medico_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Doctor.
		@param EXME_Medico_ID 
		Doctor
	  */
	public void setEXME_Medico_ID (int EXME_Medico_ID)
	{
		if (EXME_Medico_ID < 1) 
			set_Value (COLUMNNAME_EXME_Medico_ID, null);
		else 
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

	/** Set Folio.
		@param Folio Folio	  */
	public void setFolio (int Folio)
	{
		set_Value (COLUMNNAME_Folio, Integer.valueOf(Folio));
	}

	/** Get Folio.
		@return Folio	  */
	public int getFolio () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Folio);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Instructions.
		@param Instructions Instructions	  */
	public void setInstructions (String Instructions)
	{
		set_Value (COLUMNNAME_Instructions, Instructions);
	}

	/** Get Instructions.
		@return Instructions	  */
	public String getInstructions () 
	{
		return (String)get_Value(COLUMNNAME_Instructions);
	}

	/** Set Minimum Level.
		@param Lim_Inferior Minimum Level	  */
	public void setLim_Inferior (int Lim_Inferior)
	{
		set_Value (COLUMNNAME_Lim_Inferior, Integer.valueOf(Lim_Inferior));
	}

	/** Get Minimum Level.
		@return Minimum Level	  */
	public int getLim_Inferior () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Lim_Inferior);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Maximum Level.
		@param Lim_Superior Maximum Level	  */
	public void setLim_Superior (int Lim_Superior)
	{
		set_Value (COLUMNNAME_Lim_Superior, Integer.valueOf(Lim_Superior));
	}

	/** Get Maximum Level.
		@return Maximum Level	  */
	public int getLim_Superior () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Lim_Superior);
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

	/** Tipo AD_Reference_ID=1200185 */
	public static final int TIPO_AD_Reference_ID=1200185;
	/** Fast Action = AR */
	public static final String TIPO_FastAction = "AR";
	/** Short Action = AC */
	public static final String TIPO_ShortAction = "AC";
	/** Intermediate-acting NPH = AI */
	public static final String TIPO_Intermediate_ActingNPH = "AI";
	/** Prolonged action = AP */
	public static final String TIPO_ProlongedAction = "AP";
	/** Set Type.
		@param Tipo Type	  */
	public void setTipo (String Tipo)
	{

		if (Tipo == null || Tipo.equals("AR") || Tipo.equals("AC") || Tipo.equals("AI") || Tipo.equals("AP")); else throw new IllegalArgumentException ("Tipo Invalid value - " + Tipo + " - Reference_ID=1200185 - AR - AC - AI - AP");		set_Value (COLUMNNAME_Tipo, Tipo);
	}

	/** Get Type.
		@return Type	  */
	public String getTipo () 
	{
		return (String)get_Value(COLUMNNAME_Tipo);
	}

	/** Set Unity.
		@param Unidad Unity	  */
	public void setUnidad (int Unidad)
	{
		set_Value (COLUMNNAME_Unidad, Integer.valueOf(Unidad));
	}

	/** Get Unity.
		@return Unity	  */
	public int getUnidad () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Unidad);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}