/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

/** Generated Model for EXME_MO_Expediente
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_MO_Expediente extends PO implements I_EXME_MO_Expediente, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_MO_Expediente (Properties ctx, int EXME_MO_Expediente_ID, String trxName)
    {
      super (ctx, EXME_MO_Expediente_ID, trxName);
      /** if (EXME_MO_Expediente_ID == 0)
        {
			setEXME_MO_Expediente_ID (0);
			setEXME_MO_PiezaDental_ID (0);
			setEXME_Paciente_ID (0);
        } */
    }

    /** Load Constructor */
    public X_EXME_MO_Expediente (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_MO_Expediente[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set absence tooth.
		@param AusenciaDiente absence tooth	  */
	public void setAusenciaDiente (boolean AusenciaDiente)
	{
		set_Value (COLUMNNAME_AusenciaDiente, Boolean.valueOf(AusenciaDiente));
	}

	/** Get absence tooth.
		@return absence tooth	  */
	public boolean isAusenciaDiente () 
	{
		Object oo = get_Value(COLUMNNAME_AusenciaDiente);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set is adult.
		@param EsAdulto is adult	  */
	public void setEsAdulto (boolean EsAdulto)
	{
		set_Value (COLUMNNAME_EsAdulto, Boolean.valueOf(EsAdulto));
	}

	/** Get is adult.
		@return is adult	  */
	public boolean isEsAdulto () 
	{
		Object oo = get_Value(COLUMNNAME_EsAdulto);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Is Calculus.
		@param EsCalculo Is Calculus	  */
	public void setEsCalculo (boolean EsCalculo)
	{
		set_Value (COLUMNNAME_EsCalculo, Boolean.valueOf(EsCalculo));
	}

	/** Get Is Calculus.
		@return Is Calculus	  */
	public boolean isEsCalculo () 
	{
		Object oo = get_Value(COLUMNNAME_EsCalculo);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Is Odontogram.
		@param EsOdontograma 
		Is Odontogram
	  */
	public void setEsOdontograma (boolean EsOdontograma)
	{
		set_Value (COLUMNNAME_EsOdontograma, Boolean.valueOf(EsOdontograma));
	}

	/** Get Is Odontogram.
		@return Is Odontogram
	  */
	public boolean isEsOdontograma () 
	{
		Object oo = get_Value(COLUMNNAME_EsOdontograma);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set IsSuppuration.
		@param EsSupuracion IsSuppuration	  */
	public void setEsSupuracion (boolean EsSupuracion)
	{
		set_Value (COLUMNNAME_EsSupuracion, Boolean.valueOf(EsSupuracion));
	}

	/** Get IsSuppuration.
		@return IsSuppuration	  */
	public boolean isEsSupuracion () 
	{
		Object oo = get_Value(COLUMNNAME_EsSupuracion);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set MO Record.
		@param EXME_MO_Expediente_ID MO Record	  */
	public void setEXME_MO_Expediente_ID (int EXME_MO_Expediente_ID)
	{
		if (EXME_MO_Expediente_ID < 1)
			 throw new IllegalArgumentException ("EXME_MO_Expediente_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_MO_Expediente_ID, Integer.valueOf(EXME_MO_Expediente_ID));
	}

	/** Get MO Record.
		@return MO Record	  */
	public int getEXME_MO_Expediente_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_MO_Expediente_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_MO_PiezaDental getEXME_MO_PiezaDental() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_MO_PiezaDental.Table_Name);
        I_EXME_MO_PiezaDental result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_MO_PiezaDental)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_MO_PiezaDental_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Dental Piece.
		@param EXME_MO_PiezaDental_ID Dental Piece	  */
	public void setEXME_MO_PiezaDental_ID (int EXME_MO_PiezaDental_ID)
	{
		if (EXME_MO_PiezaDental_ID < 1)
			 throw new IllegalArgumentException ("EXME_MO_PiezaDental_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_MO_PiezaDental_ID, Integer.valueOf(EXME_MO_PiezaDental_ID));
	}

	/** Get Dental Piece.
		@return Dental Piece	  */
	public int getEXME_MO_PiezaDental_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_MO_PiezaDental_ID);
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

	/** Set plate.
		@param Placa plate	  */
	public void setPlaca (boolean Placa)
	{
		set_Value (COLUMNNAME_Placa, Boolean.valueOf(Placa));
	}

	/** Get plate.
		@return plate	  */
	public boolean isPlaca () 
	{
		Object oo = get_Value(COLUMNNAME_Placa);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Bleeding.
		@param Sangrado Bleeding	  */
	public void setSangrado (boolean Sangrado)
	{
		set_Value (COLUMNNAME_Sangrado, Boolean.valueOf(Sangrado));
	}

	/** Get Bleeding.
		@return Bleeding	  */
	public boolean isSangrado () 
	{
		Object oo = get_Value(COLUMNNAME_Sangrado);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}
}