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

/** Generated Model for PHR_PacienteAler
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_PHR_PacienteAler extends PO implements I_PHR_PacienteAler, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_PHR_PacienteAler (Properties ctx, int PHR_PacienteAler_ID, String trxName)
    {
      super (ctx, PHR_PacienteAler_ID, trxName);
      /** if (PHR_PacienteAler_ID == 0)
        {
			setEXME_Paciente_ID (0);
			setPHR_PacienteAler_ID (0);
			setTipoAlergia (null);
        } */
    }

    /** Load Constructor */
    public X_PHR_PacienteAler (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_PHR_PacienteAler[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public I_EXME_Alergia getEXME_Alergia() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_Alergia.Table_Name);
        I_EXME_Alergia result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_Alergia)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Alergia_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Alergy.
		@param EXME_Alergia_ID 
		Alergy
	  */
	public void setEXME_Alergia_ID (int EXME_Alergia_ID)
	{
		if (EXME_Alergia_ID < 1) 
			set_Value (COLUMNNAME_EXME_Alergia_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_Alergia_ID, Integer.valueOf(EXME_Alergia_ID));
	}

	/** Get Alergy.
		@return Alergy
	  */
	public int getEXME_Alergia_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Alergia_ID);
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

	public I_EXME_SActiva getEXME_SActiva() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_SActiva.Table_Name);
        I_EXME_SActiva result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_SActiva)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_SActiva_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Active Substance.
		@param EXME_SActiva_ID 
		Active Substance
	  */
	public void setEXME_SActiva_ID (int EXME_SActiva_ID)
	{
		if (EXME_SActiva_ID < 1) 
			set_Value (COLUMNNAME_EXME_SActiva_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_SActiva_ID, Integer.valueOf(EXME_SActiva_ID));
	}

	/** Get Active Substance.
		@return Active Substance
	  */
	public int getEXME_SActiva_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_SActiva_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Date of Diagnosis.
		@param FechaDiagnostico 
		Date of Diagnosis
	  */
	public void setFechaDiagnostico (Timestamp FechaDiagnostico)
	{
		set_Value (COLUMNNAME_FechaDiagnostico, FechaDiagnostico);
	}

	/** Get Date of Diagnosis.
		@return Date of Diagnosis
	  */
	public Timestamp getFechaDiagnostico () 
	{
		return (Timestamp)get_Value(COLUMNNAME_FechaDiagnostico);
	}

	public I_M_Product getM_Product() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_M_Product.Table_Name);
        I_M_Product result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_M_Product)constructor.newInstance(new Object[] {getCtx(), new Integer(getM_Product_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Product.
		@param M_Product_ID 
		Product, Service, Item
	  */
	public void setM_Product_ID (int M_Product_ID)
	{
		if (M_Product_ID < 1) 
			set_Value (COLUMNNAME_M_Product_ID, null);
		else 
			set_Value (COLUMNNAME_M_Product_ID, Integer.valueOf(M_Product_ID));
	}

	/** Get Product.
		@return Product, Service, Item
	  */
	public int getM_Product_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_Product_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Allergies.
		@param PHR_PacienteAler_ID Allergies	  */
	public void setPHR_PacienteAler_ID (int PHR_PacienteAler_ID)
	{
		if (PHR_PacienteAler_ID < 1)
			 throw new IllegalArgumentException ("PHR_PacienteAler_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_PHR_PacienteAler_ID, Integer.valueOf(PHR_PacienteAler_ID));
	}

	/** Get Allergies.
		@return Allergies	  */
	public int getPHR_PacienteAler_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_PHR_PacienteAler_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Reaction.
		@param Reaccion Reaction	  */
	public void setReaccion (String Reaccion)
	{
		set_Value (COLUMNNAME_Reaccion, Reaccion);
	}

	/** Get Reaction.
		@return Reaction	  */
	public String getReaccion () 
	{
		return (String)get_Value(COLUMNNAME_Reaccion);
	}

	/** TipoAlergia AD_Reference_ID=394 */
	public static final int TIPOALERGIA_AD_Reference_ID=394;
	/** Animal Allergy = AA */
	public static final String TIPOALERGIA_AnimalAllergy = "AA";
	/** Drug Allergy = DA */
	public static final String TIPOALERGIA_DrugAllergy = "DA";
	/** Environmental Allergy = EA */
	public static final String TIPOALERGIA_EnvironmentalAllergy = "EA";
	/** Food Allergy = FA */
	public static final String TIPOALERGIA_FoodAllergy = "FA";
	/** Pollen Allergy = LA */
	public static final String TIPOALERGIA_PollenAllergy = "LA";
	/** Miscellaneous Allergy = MA */
	public static final String TIPOALERGIA_MiscellaneousAllergy = "MA";
	/** Miscellaneous Contraindications = MC */
	public static final String TIPOALERGIA_MiscellaneousContraindications = "MC";
	/** Plant Allergy = PA */
	public static final String TIPOALERGIA_PlantAllergy = "PA";
	/** Set Alergy Type.
		@param TipoAlergia 
		Alergy Type
	  */
	public void setTipoAlergia (String TipoAlergia)
	{
		if (TipoAlergia == null) throw new IllegalArgumentException ("TipoAlergia is mandatory");
		if (TipoAlergia.equals("AA") || TipoAlergia.equals("DA") || TipoAlergia.equals("EA") || TipoAlergia.equals("FA") || TipoAlergia.equals("LA") || TipoAlergia.equals("MA") || TipoAlergia.equals("MC") || TipoAlergia.equals("PA")); else throw new IllegalArgumentException ("TipoAlergia Invalid value - " + TipoAlergia + " - Reference_ID=394 - AA - DA - EA - FA - LA - MA - MC - PA");		set_Value (COLUMNNAME_TipoAlergia, TipoAlergia);
	}

	/** Get Alergy Type.
		@return Alergy Type
	  */
	public String getTipoAlergia () 
	{
		return (String)get_Value(COLUMNNAME_TipoAlergia);
	}
}