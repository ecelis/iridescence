/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;
import org.compiere.util.Env;

/** Generated Model for EXME_T_SaldoCtaPac
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_T_SaldoCtaPac extends PO implements I_EXME_T_SaldoCtaPac, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_T_SaldoCtaPac (Properties ctx, int EXME_T_SaldoCtaPac_ID, String trxName)
    {
      super (ctx, EXME_T_SaldoCtaPac_ID, trxName);
      /** if (EXME_T_SaldoCtaPac_ID == 0)
        {
			setAD_Session_ID (0);
			setAnticipos (Env.ZERO);
			setCargos (Env.ZERO);
			setC_BPartner_ID (0);
			setEXME_CtaPac_ID (0);
			setEXME_Paciente_ID (0);
			setEXME_T_SaldoCtaPac_ID (0);
			setSaldo (Env.ZERO);
        } */
    }

    /** Load Constructor */
    public X_EXME_T_SaldoCtaPac (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_T_SaldoCtaPac[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public I_AD_Session getAD_Session() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_AD_Session.Table_Name);
        I_AD_Session result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_AD_Session)constructor.newInstance(new Object[] {getCtx(), new Integer(getAD_Session_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Session.
		@param AD_Session_ID 
		User Session Online or Web
	  */
	public void setAD_Session_ID (int AD_Session_ID)
	{
		if (AD_Session_ID < 1)
			 throw new IllegalArgumentException ("AD_Session_ID is mandatory.");
		set_Value (COLUMNNAME_AD_Session_ID, Integer.valueOf(AD_Session_ID));
	}

	/** Get Session.
		@return User Session Online or Web
	  */
	public int getAD_Session_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_Session_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Advance Payments.
		@param Anticipos 
		Payments made in advance
	  */
	public void setAnticipos (BigDecimal Anticipos)
	{
		if (Anticipos == null)
			throw new IllegalArgumentException ("Anticipos is mandatory.");
		set_Value (COLUMNNAME_Anticipos, Anticipos);
	}

	/** Get Advance Payments.
		@return Payments made in advance
	  */
	public BigDecimal getAnticipos () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Anticipos);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Charges.
		@param Cargos 
		Charges of the patient account
	  */
	public void setCargos (BigDecimal Cargos)
	{
		if (Cargos == null)
			throw new IllegalArgumentException ("Cargos is mandatory.");
		set_Value (COLUMNNAME_Cargos, Cargos);
	}

	/** Get Charges.
		@return Charges of the patient account
	  */
	public BigDecimal getCargos () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Cargos);
		if (bd == null)
			 return Env.ZERO;
		return bd;
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
			 throw new IllegalArgumentException ("C_BPartner_ID is mandatory.");
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

	public I_EXME_Cama getEXME_Cama() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_Cama.Table_Name);
        I_EXME_Cama result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_Cama)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Cama_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Bed.
		@param EXME_Cama_ID 
		Bed
	  */
	public void setEXME_Cama_ID (int EXME_Cama_ID)
	{
		if (EXME_Cama_ID < 1) 
			set_Value (COLUMNNAME_EXME_Cama_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_Cama_ID, Integer.valueOf(EXME_Cama_ID));
	}

	/** Get Bed.
		@return Bed
	  */
	public int getEXME_Cama_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Cama_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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

	/** Set Patient Account.
		@param EXME_CtaPac_ID 
		Patient Account
	  */
	public void setEXME_CtaPac_ID (int EXME_CtaPac_ID)
	{
		if (EXME_CtaPac_ID < 1)
			 throw new IllegalArgumentException ("EXME_CtaPac_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_CtaPac_ID, Integer.valueOf(EXME_CtaPac_ID));
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

	public I_EXME_Habitacion getEXME_Habitacion() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_Habitacion.Table_Name);
        I_EXME_Habitacion result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_Habitacion)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Habitacion_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Room.
		@param EXME_Habitacion_ID 
		Room
	  */
	public void setEXME_Habitacion_ID (int EXME_Habitacion_ID)
	{
		if (EXME_Habitacion_ID < 1) 
			set_Value (COLUMNNAME_EXME_Habitacion_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_Habitacion_ID, Integer.valueOf(EXME_Habitacion_ID));
	}

	/** Get Room.
		@return Room
	  */
	public int getEXME_Habitacion_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Habitacion_ID);
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

	/** Set Patient Account Balance.
		@param EXME_T_SaldoCtaPac_ID 
		The Balance of the Patient Account
	  */
	public void setEXME_T_SaldoCtaPac_ID (int EXME_T_SaldoCtaPac_ID)
	{
		if (EXME_T_SaldoCtaPac_ID < 1)
			 throw new IllegalArgumentException ("EXME_T_SaldoCtaPac_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_T_SaldoCtaPac_ID, Integer.valueOf(EXME_T_SaldoCtaPac_ID));
	}

	/** Get Patient Account Balance.
		@return The Balance of the Patient Account
	  */
	public int getEXME_T_SaldoCtaPac_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_T_SaldoCtaPac_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Payments.
		@param Pagos 
		Payments
	  */
	public void setPagos (BigDecimal Pagos)
	{
		set_Value (COLUMNNAME_Pagos, Pagos);
	}

	/** Get Payments.
		@return Payments
	  */
	public BigDecimal getPagos () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Pagos);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Balance.
		@param Saldo 
		Balance of Patient Account
	  */
	public void setSaldo (BigDecimal Saldo)
	{
		if (Saldo == null)
			throw new IllegalArgumentException ("Saldo is mandatory.");
		set_Value (COLUMNNAME_Saldo, Saldo);
	}

	/** Get Balance.
		@return Balance of Patient Account
	  */
	public BigDecimal getSaldo () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Saldo);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Area Type.
		@param TipoArea 
		Admission Area Type
	  */
	public void setTipoArea (String TipoArea)
	{
		set_Value (COLUMNNAME_TipoArea, TipoArea);
	}

	/** Get Area Type.
		@return Admission Area Type
	  */
	public String getTipoArea () 
	{
		return (String)get_Value(COLUMNNAME_TipoArea);
	}
}