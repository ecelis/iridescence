/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;
import java.util.logging.Level;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;

/** Generated Model for PHR_Aseguradora
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_PHR_Aseguradora extends PO implements I_PHR_Aseguradora, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_PHR_Aseguradora (Properties ctx, int PHR_Aseguradora_ID, String trxName)
    {
      super (ctx, PHR_Aseguradora_ID, trxName);
      /** if (PHR_Aseguradora_ID == 0)
        {
			setEXME_InsuranceType_ID (0);
			setEXME_Paciente_ID (0);
			setFechaFin (new Timestamp( System.currentTimeMillis() ));
			setFechaIni (new Timestamp( System.currentTimeMillis() ));
			setNumeroPoliza (null);
			setPHR_Aseguradora_ID (0);
        } */
    }

    /** Load Constructor */
    public X_PHR_Aseguradora (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_PHR_Aseguradora[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public I_C_BPartner getC_BPartner() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_C_BPartner.Table_Name);
        I_C_BPartner result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_C_BPartner)constructor.newInstance(new Object[] {getCtx(), new Integer(getC_BPartner_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Business Partner.
		@param C_BPartner_ID 
		Identifier for a Business Partner
	  */
	public void setC_BPartner_ID (int C_BPartner_ID)
	{
		if (C_BPartner_ID < 1) 
			set_Value (COLUMNNAME_C_BPartner_ID, null);
		else 
			set_Value (COLUMNNAME_C_BPartner_ID, Integer.valueOf(C_BPartner_ID));
	}

	/** Get Business Partner.
		@return Identifier for a Business Partner
	  */
	public int getC_BPartner_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_BPartner_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Deductible.
		@param Deducible 
		Deductible
	  */
	public void setDeducible (BigDecimal Deducible)
	{
		set_Value (COLUMNNAME_Deducible, Deducible);
	}

	/** Get Deductible.
		@return Deductible
	  */
	public BigDecimal getDeducible () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Deducible);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Mount-Percentage.
		@param DeduciblePorcentaje Mount-Percentage	  */
	public void setDeduciblePorcentaje (BigDecimal DeduciblePorcentaje)
	{
		set_Value (COLUMNNAME_DeduciblePorcentaje, DeduciblePorcentaje);
	}

	/** Get Mount-Percentage.
		@return Mount-Percentage	  */
	public BigDecimal getDeduciblePorcentaje () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_DeduciblePorcentaje);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	public I_EXME_InsuranceType getEXME_InsuranceType() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_InsuranceType.Table_Name);
        I_EXME_InsuranceType result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_InsuranceType)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_InsuranceType_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Insurance Type.
		@param EXME_InsuranceType_ID Insurance Type	  */
	public void setEXME_InsuranceType_ID (int EXME_InsuranceType_ID)
	{
		if (EXME_InsuranceType_ID < 1)
			 throw new IllegalArgumentException ("EXME_InsuranceType_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_InsuranceType_ID, Integer.valueOf(EXME_InsuranceType_ID));
	}

	/** Get Insurance Type.
		@return Insurance Type	  */
	public int getEXME_InsuranceType_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_InsuranceType_ID);
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

	/** Set Ending Date.
		@param FechaFin 
		Date of ending of intervention
	  */
	public void setFechaFin (Timestamp FechaFin)
	{
		if (FechaFin == null)
			throw new IllegalArgumentException ("FechaFin is mandatory.");
		set_Value (COLUMNNAME_FechaFin, FechaFin);
	}

	/** Get Ending Date.
		@return Date of ending of intervention
	  */
	public Timestamp getFechaFin () 
	{
		return (Timestamp)get_Value(COLUMNNAME_FechaFin);
	}

	/** Set Initial Date.
		@param FechaIni 
		Initial Date
	  */
	public void setFechaIni (Timestamp FechaIni)
	{
		if (FechaIni == null)
			throw new IllegalArgumentException ("FechaIni is mandatory.");
		set_Value (COLUMNNAME_FechaIni, FechaIni);
	}

	/** Get Initial Date.
		@return Initial Date
	  */
	public Timestamp getFechaIni () 
	{
		return (Timestamp)get_Value(COLUMNNAME_FechaIni);
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

	/** Set Group number.
		@param NumeroGrupo Group number	  */
	public void setNumeroGrupo (BigDecimal NumeroGrupo)
	{
		set_Value (COLUMNNAME_NumeroGrupo, NumeroGrupo);
	}

	/** Get Group number.
		@return Group number	  */
	public BigDecimal getNumeroGrupo () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_NumeroGrupo);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Police number.
		@param NumeroPoliza Police number	  */
	public void setNumeroPoliza (String NumeroPoliza)
	{
		if (NumeroPoliza == null)
			throw new IllegalArgumentException ("NumeroPoliza is mandatory.");
		set_Value (COLUMNNAME_NumeroPoliza, NumeroPoliza);
	}

	/** Get Police number.
		@return Police number	  */
	public String getNumeroPoliza () 
	{
		return (String)get_Value(COLUMNNAME_NumeroPoliza);
	}

	/** Set Insurance.
		@param PHR_Aseguradora_ID Insurance	  */
	public void setPHR_Aseguradora_ID (int PHR_Aseguradora_ID)
	{
		if (PHR_Aseguradora_ID < 1)
			 throw new IllegalArgumentException ("PHR_Aseguradora_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_PHR_Aseguradora_ID, Integer.valueOf(PHR_Aseguradora_ID));
	}

	/** Get Insurance.
		@return Insurance	  */
	public int getPHR_Aseguradora_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_PHR_Aseguradora_ID);
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