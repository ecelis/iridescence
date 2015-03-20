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

/** Generated Model for I_EXME_CartaAutoriza
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_I_EXME_CartaAutoriza extends PO implements I_I_EXME_CartaAutoriza, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_I_EXME_CartaAutoriza (Properties ctx, int I_EXME_CartaAutoriza_ID, String trxName)
    {
      super (ctx, I_EXME_CartaAutoriza_ID, trxName);
      /** if (I_EXME_CartaAutoriza_ID == 0)
        {
			setI_EXME_CartaAutoriza_ID (0);
        } */
    }

    /** Load Constructor */
    public X_I_EXME_CartaAutoriza (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 1 - Org 
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
      StringBuffer sb = new StringBuffer ("X_I_EXME_CartaAutoriza[")
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
			set_Value (COLUMNNAME_AD_User_ID, null);
		else 
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

	/** Set User Name.
		@param AD_User_Name 
		User Name
	  */
	public void setAD_User_Name (String AD_User_Name)
	{
		set_Value (COLUMNNAME_AD_User_Name, AD_User_Name);
	}

	/** Get User Name.
		@return User Name
	  */
	public String getAD_User_Name () 
	{
		return (String)get_Value(COLUMNNAME_AD_User_Name);
	}

	/** Set Approved.
		@param Aprobada 
		Approved
	  */
	public void setAprobada (boolean Aprobada)
	{
		set_Value (COLUMNNAME_Aprobada, Boolean.valueOf(Aprobada));
	}

	/** Get Approved.
		@return Approved
	  */
	public boolean isAprobada () 
	{
		Object oo = get_Value(COLUMNNAME_Aprobada);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Date Ordered.
		@param DateOrdered 
		Date of Order
	  */
	public void setDateOrdered (Timestamp DateOrdered)
	{
		set_Value (COLUMNNAME_DateOrdered, DateOrdered);
	}

	/** Get Date Ordered.
		@return Date of Order
	  */
	public Timestamp getDateOrdered () 
	{
		return (Timestamp)get_Value(COLUMNNAME_DateOrdered);
	}

	/** Set Date printed.
		@param DatePrinted 
		Date the document was printed.
	  */
	public void setDatePrinted (Timestamp DatePrinted)
	{
		set_Value (COLUMNNAME_DatePrinted, DatePrinted);
	}

	/** Get Date printed.
		@return Date the document was printed.
	  */
	public Timestamp getDatePrinted () 
	{
		return (Timestamp)get_Value(COLUMNNAME_DatePrinted);
	}

	/** Set Donors.
		@param Donadores 
		Donors
	  */
	public void setDonadores (BigDecimal Donadores)
	{
		set_Value (COLUMNNAME_Donadores, Donadores);
	}

	/** Get Donors.
		@return Donors
	  */
	public BigDecimal getDonadores () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Donadores);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	public I_EXME_CartaAutoriza getEXME_CartaAutoriza() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_CartaAutoriza.Table_Name);
        I_EXME_CartaAutoriza result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_CartaAutoriza)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_CartaAutoriza_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Authorization Letter.
		@param EXME_CartaAutoriza_ID 
		Authorization Letter
	  */
	public void setEXME_CartaAutoriza_ID (int EXME_CartaAutoriza_ID)
	{
		if (EXME_CartaAutoriza_ID < 1) 
			set_Value (COLUMNNAME_EXME_CartaAutoriza_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_CartaAutoriza_ID, Integer.valueOf(EXME_CartaAutoriza_ID));
	}

	/** Get Authorization Letter.
		@return Authorization Letter
	  */
	public int getEXME_CartaAutoriza_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_CartaAutoriza_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_Hist_Exp getEXME_Hist_Exp() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_Hist_Exp.Table_Name);
        I_EXME_Hist_Exp result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_Hist_Exp)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Hist_Exp_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Medical File.
		@param EXME_Hist_Exp_ID 
		Medical File
	  */
	public void setEXME_Hist_Exp_ID (int EXME_Hist_Exp_ID)
	{
		if (EXME_Hist_Exp_ID < 1) 
			set_Value (COLUMNNAME_EXME_Hist_Exp_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_Hist_Exp_ID, Integer.valueOf(EXME_Hist_Exp_ID));
	}

	/** Get Medical File.
		@return Medical File
	  */
	public int getEXME_Hist_Exp_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Hist_Exp_ID);
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

	/** Set Patient History Number.
		@param EXME_Paciente_Value 
		Patient History Number
	  */
	public void setEXME_Paciente_Value (String EXME_Paciente_Value)
	{
		set_Value (COLUMNNAME_EXME_Paciente_Value, EXME_Paciente_Value);
	}

	/** Get Patient History Number.
		@return Patient History Number
	  */
	public String getEXME_Paciente_Value () 
	{
		return (String)get_Value(COLUMNNAME_EXME_Paciente_Value);
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

	/** Set Import Error Message.
		@param I_ErrorMsg 
		Messages generated from import process
	  */
	public void setI_ErrorMsg (String I_ErrorMsg)
	{
		set_Value (COLUMNNAME_I_ErrorMsg, I_ErrorMsg);
	}

	/** Get Import Error Message.
		@return Messages generated from import process
	  */
	public String getI_ErrorMsg () 
	{
		return (String)get_Value(COLUMNNAME_I_ErrorMsg);
	}

	/** Set Authorization Letter.
		@param I_EXME_CartaAutoriza_ID Authorization Letter	  */
	public void setI_EXME_CartaAutoriza_ID (int I_EXME_CartaAutoriza_ID)
	{
		if (I_EXME_CartaAutoriza_ID < 1)
			 throw new IllegalArgumentException ("I_EXME_CartaAutoriza_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_I_EXME_CartaAutoriza_ID, Integer.valueOf(I_EXME_CartaAutoriza_ID));
	}

	/** Get Authorization Letter.
		@return Authorization Letter	  */
	public int getI_EXME_CartaAutoriza_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_I_EXME_CartaAutoriza_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Imported.
		@param I_IsImported 
		Has this import been processed
	  */
	public void setI_IsImported (boolean I_IsImported)
	{
		set_Value (COLUMNNAME_I_IsImported, Boolean.valueOf(I_IsImported));
	}

	/** Get Imported.
		@return Has this import been processed
	  */
	public boolean isI_IsImported () 
	{
		Object oo = get_Value(COLUMNNAME_I_IsImported);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Processed.
		@param Processed 
		The document has been processed
	  */
	public void setProcessed (boolean Processed)
	{
		set_Value (COLUMNNAME_Processed, Boolean.valueOf(Processed));
	}

	/** Get Processed.
		@return The document has been processed
	  */
	public boolean isProcessed () 
	{
		Object oo = get_Value(COLUMNNAME_Processed);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Process Now.
		@param Processing Process Now	  */
	public void setProcessing (boolean Processing)
	{
		set_Value (COLUMNNAME_Processing, Boolean.valueOf(Processing));
	}

	/** Get Process Now.
		@return Process Now	  */
	public boolean isProcessing () 
	{
		Object oo = get_Value(COLUMNNAME_Processing);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Witness 1.
		@param Testigo1 
		Witness 1
	  */
	public void setTestigo1 (String Testigo1)
	{
		set_Value (COLUMNNAME_Testigo1, Testigo1);
	}

	/** Get Witness 1.
		@return Witness 1
	  */
	public String getTestigo1 () 
	{
		return (String)get_Value(COLUMNNAME_Testigo1);
	}

	/** Set Witness 2.
		@param Testigo2 
		Witness 2
	  */
	public void setTestigo2 (String Testigo2)
	{
		set_Value (COLUMNNAME_Testigo2, Testigo2);
	}

	/** Get Witness 2.
		@return Witness 2
	  */
	public String getTestigo2 () 
	{
		return (String)get_Value(COLUMNNAME_Testigo2);
	}

	/** Set Letter Type.
		@param TipoCarta 
		Letter Type
	  */
	public void setTipoCarta (String TipoCarta)
	{
		set_Value (COLUMNNAME_TipoCarta, TipoCarta);
	}

	/** Get Letter Type.
		@return Letter Type
	  */
	public String getTipoCarta () 
	{
		return (String)get_Value(COLUMNNAME_TipoCarta);
	}
}