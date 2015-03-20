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

/** Generated Model for I_EXME_Clasificacion
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_I_EXME_Clasificacion extends PO implements I_I_EXME_Clasificacion, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_I_EXME_Clasificacion (Properties ctx, int I_EXME_Clasificacion_ID, String trxName)
    {
      super (ctx, I_EXME_Clasificacion_ID, trxName);
      /** if (I_EXME_Clasificacion_ID == 0)
        {
        } */
    }

    /** Load Constructor */
    public X_I_EXME_Clasificacion (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_I_EXME_Clasificacion[")
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

	/** Set Classification's update key..
		@param CambiaClas_ID 
		Classification's update key.
	  */
	public void setCambiaClas_ID (int CambiaClas_ID)
	{
		if (CambiaClas_ID < 1) 
			set_Value (COLUMNNAME_CambiaClas_ID, null);
		else 
			set_Value (COLUMNNAME_CambiaClas_ID, Integer.valueOf(CambiaClas_ID));
	}

	/** Get Classification's update key..
		@return Classification's update key.
	  */
	public int getCambiaClas_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_CambiaClas_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Update Classification_Name.
		@param CambiaClas_Name 
		Update Classification_Name
	  */
	public void setCambiaClas_Name (String CambiaClas_Name)
	{
		set_Value (COLUMNNAME_CambiaClas_Name, CambiaClas_Name);
	}

	/** Get Update Classification_Name.
		@return Update Classification_Name
	  */
	public String getCambiaClas_Name () 
	{
		return (String)get_Value(COLUMNNAME_CambiaClas_Name);
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

	/** Set Valid Date.
		@param DateVaidTo 
		Valid Date
	  */
	public void setDateVaidTo (Timestamp DateVaidTo)
	{
		set_Value (COLUMNNAME_DateVaidTo, DateVaidTo);
	}

	/** Get Valid Date.
		@return Valid Date
	  */
	public Timestamp getDateVaidTo () 
	{
		return (Timestamp)get_Value(COLUMNNAME_DateVaidTo);
	}

	/** Set Feeding Expense.
		@param EgrAlimentacion 
		Feeding Expense
	  */
	public void setEgrAlimentacion (BigDecimal EgrAlimentacion)
	{
		set_Value (COLUMNNAME_EgrAlimentacion, EgrAlimentacion);
	}

	/** Get Feeding Expense.
		@return Feeding Expense
	  */
	public BigDecimal getEgrAlimentacion () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_EgrAlimentacion);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Other expense.
		@param EgrOtros 
		Other expense
	  */
	public void setEgrOtros (BigDecimal EgrOtros)
	{
		set_Value (COLUMNNAME_EgrOtros, EgrOtros);
	}

	/** Get Other expense.
		@return Other expense
	  */
	public BigDecimal getEgrOtros () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_EgrOtros);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Service Expense.
		@param EgrServicios 
		Service Expense
	  */
	public void setEgrServicios (BigDecimal EgrServicios)
	{
		set_Value (COLUMNNAME_EgrServicios, EgrServicios);
	}

	/** Get Service Expense.
		@return Service Expense
	  */
	public BigDecimal getEgrServicios () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_EgrServicios);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set House Expense.
		@param EgrVivienda 
		House Expense
	  */
	public void setEgrVivienda (BigDecimal EgrVivienda)
	{
		set_Value (COLUMNNAME_EgrVivienda, EgrVivienda);
	}

	/** Get House Expense.
		@return House Expense
	  */
	public BigDecimal getEgrVivienda () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_EgrVivienda);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Agreement.
		@param EsDeConvenio Agreement	  */
	public void setEsDeConvenio (boolean EsDeConvenio)
	{
		set_Value (COLUMNNAME_EsDeConvenio, Boolean.valueOf(EsDeConvenio));
	}

	/** Get Agreement.
		@return Agreement	  */
	public boolean isEsDeConvenio () 
	{
		Object oo = get_Value(COLUMNNAME_EsDeConvenio);
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

	public I_EXME_ClasCliente getEXME_ClasCliente() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_ClasCliente.Table_Name);
        I_EXME_ClasCliente result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_ClasCliente)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_ClasCliente_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Classification and Customer.
		@param EXME_ClasCliente_ID Classification and Customer	  */
	public void setEXME_ClasCliente_ID (int EXME_ClasCliente_ID)
	{
		if (EXME_ClasCliente_ID < 1) 
			set_Value (COLUMNNAME_EXME_ClasCliente_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_ClasCliente_ID, Integer.valueOf(EXME_ClasCliente_ID));
	}

	/** Get Classification and Customer.
		@return Classification and Customer	  */
	public int getEXME_ClasCliente_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_ClasCliente_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Value of Classification and Customer.
		@param EXME_ClasCliente_Value 
		Value of Classification and Customer
	  */
	public void setEXME_ClasCliente_Value (String EXME_ClasCliente_Value)
	{
		set_Value (COLUMNNAME_EXME_ClasCliente_Value, EXME_ClasCliente_Value);
	}

	/** Get Value of Classification and Customer.
		@return Value of Classification and Customer
	  */
	public String getEXME_ClasCliente_Value () 
	{
		return (String)get_Value(COLUMNNAME_EXME_ClasCliente_Value);
	}

	public I_EXME_Clasificacion getEXME_Clasificacion() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_Clasificacion.Table_Name);
        I_EXME_Clasificacion result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_Clasificacion)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Clasificacion_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Classification.
		@param EXME_Clasificacion_ID 
		Classification
	  */
	public void setEXME_Clasificacion_ID (int EXME_Clasificacion_ID)
	{
		if (EXME_Clasificacion_ID < 1) 
			set_Value (COLUMNNAME_EXME_Clasificacion_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_Clasificacion_ID, Integer.valueOf(EXME_Clasificacion_ID));
	}

	/** Get Classification.
		@return Classification
	  */
	public int getEXME_Clasificacion_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Clasificacion_ID);
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

	/** Set Medical File Value.
		@param EXME_Hist_Exp_Value 
		Medical File Value
	  */
	public void setEXME_Hist_Exp_Value (int EXME_Hist_Exp_Value)
	{
		set_Value (COLUMNNAME_EXME_Hist_Exp_Value, Integer.valueOf(EXME_Hist_Exp_Value));
	}

	/** Get Medical File Value.
		@return Medical File Value
	  */
	public int getEXME_Hist_Exp_Value () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Hist_Exp_Value);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Construction Material.
		@param EXME_MatConst_ID 
		Construction Material
	  */
	public void setEXME_MatConst_ID (int EXME_MatConst_ID)
	{
		if (EXME_MatConst_ID < 1) 
			set_Value (COLUMNNAME_EXME_MatConst_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_MatConst_ID, Integer.valueOf(EXME_MatConst_ID));
	}

	/** Get Construction Material.
		@return Construction Material
	  */
	public int getEXME_MatConst_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_MatConst_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Construction Material's Value.
		@param EXME_Material_Const_Value 
		Construction Material's Value
	  */
	public void setEXME_Material_Const_Value (String EXME_Material_Const_Value)
	{
		set_Value (COLUMNNAME_EXME_Material_Const_Value, EXME_Material_Const_Value);
	}

	/** Get Construction Material's Value.
		@return Construction Material's Value
	  */
	public String getEXME_Material_Const_Value () 
	{
		return (String)get_Value(COLUMNNAME_EXME_Material_Const_Value);
	}

	public I_EXME_NumEnferm getEXME_NumEnferm() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_NumEnferm.Table_Name);
        I_EXME_NumEnferm result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_NumEnferm)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_NumEnferm_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Number of Sick People.
		@param EXME_NumEnferm_ID 
		Number of Sick People that  will be inside the patient's house
	  */
	public void setEXME_NumEnferm_ID (int EXME_NumEnferm_ID)
	{
		if (EXME_NumEnferm_ID < 1) 
			set_Value (COLUMNNAME_EXME_NumEnferm_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_NumEnferm_ID, Integer.valueOf(EXME_NumEnferm_ID));
	}

	/** Get Number of Sick People.
		@return Number of Sick People that  will be inside the patient's house
	  */
	public int getEXME_NumEnferm_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_NumEnferm_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set EXME_NumEnferm_Value.
		@param EXME_NumEnferm_Value 
		Sickness Number
	  */
	public void setEXME_NumEnferm_Value (String EXME_NumEnferm_Value)
	{
		set_Value (COLUMNNAME_EXME_NumEnferm_Value, EXME_NumEnferm_Value);
	}

	/** Get EXME_NumEnferm_Value.
		@return Sickness Number
	  */
	public String getEXME_NumEnferm_Value () 
	{
		return (String)get_Value(COLUMNNAME_EXME_NumEnferm_Value);
	}

	public I_EXME_NumHabts getEXME_NumHabts() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_NumHabts.Table_Name);
        I_EXME_NumHabts result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_NumHabts)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_NumHabts_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Number of Rooms.
		@param EXME_NumHabts_ID 
		Number of Rooms
	  */
	public void setEXME_NumHabts_ID (int EXME_NumHabts_ID)
	{
		if (EXME_NumHabts_ID < 1) 
			set_Value (COLUMNNAME_EXME_NumHabts_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_NumHabts_ID, Integer.valueOf(EXME_NumHabts_ID));
	}

	/** Get Number of Rooms.
		@return Number of Rooms
	  */
	public int getEXME_NumHabts_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_NumHabts_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Number of Rooms_Value.
		@param EXME_NumHabts_Value 
		Number of Rooms_Value
	  */
	public void setEXME_NumHabts_Value (String EXME_NumHabts_Value)
	{
		set_Value (COLUMNNAME_EXME_NumHabts_Value, EXME_NumHabts_Value);
	}

	/** Get Number of Rooms_Value.
		@return Number of Rooms_Value
	  */
	public String getEXME_NumHabts_Value () 
	{
		return (String)get_Value(COLUMNNAME_EXME_NumHabts_Value);
	}

	public I_EXME_NumPers getEXME_NumPers() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_NumPers.Table_Name);
        I_EXME_NumPers result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_NumPers)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_NumPers_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Number of Persons.
		@param EXME_NumPers_ID 
		Number of Persons
	  */
	public void setEXME_NumPers_ID (int EXME_NumPers_ID)
	{
		if (EXME_NumPers_ID < 1) 
			set_Value (COLUMNNAME_EXME_NumPers_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_NumPers_ID, Integer.valueOf(EXME_NumPers_ID));
	}

	/** Get Number of Persons.
		@return Number of Persons
	  */
	public int getEXME_NumPers_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_NumPers_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Number of Persons_Value.
		@param EXME_NumPers_Value 
		Number of Persons_Value
	  */
	public void setEXME_NumPers_Value (String EXME_NumPers_Value)
	{
		set_Value (COLUMNNAME_EXME_NumPers_Value, EXME_NumPers_Value);
	}

	/** Get Number of Persons_Value.
		@return Number of Persons_Value
	  */
	public String getEXME_NumPers_Value () 
	{
		return (String)get_Value(COLUMNNAME_EXME_NumPers_Value);
	}

	public I_EXME_Ocupacion_Clas getEXME_Ocupacion_Clas() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_Ocupacion_Clas.Table_Name);
        I_EXME_Ocupacion_Clas result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_Ocupacion_Clas)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Ocupacion_Clas_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Classified Ocupation.
		@param EXME_Ocupacion_Clas_ID 
		Classified Ocupation
	  */
	public void setEXME_Ocupacion_Clas_ID (int EXME_Ocupacion_Clas_ID)
	{
		if (EXME_Ocupacion_Clas_ID < 1) 
			set_Value (COLUMNNAME_EXME_Ocupacion_Clas_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_Ocupacion_Clas_ID, Integer.valueOf(EXME_Ocupacion_Clas_ID));
	}

	/** Get Classified Ocupation.
		@return Classified Ocupation
	  */
	public int getEXME_Ocupacion_Clas_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Ocupacion_Clas_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Classified Ocupation_Value.
		@param EXME_Ocupacion_Clas_Value Classified Ocupation_Value	  */
	public void setEXME_Ocupacion_Clas_Value (int EXME_Ocupacion_Clas_Value)
	{
		set_Value (COLUMNNAME_EXME_Ocupacion_Clas_Value, Integer.valueOf(EXME_Ocupacion_Clas_Value));
	}

	/** Get Classified Ocupation_Value.
		@return Classified Ocupation_Value	  */
	public int getEXME_Ocupacion_Clas_Value () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Ocupacion_Clas_Value);
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

	public I_EXME_Procedencia getEXME_Procedencia() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_Procedencia.Table_Name);
        I_EXME_Procedencia result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_Procedencia)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Procedencia_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Provenance.
		@param EXME_Procedencia_ID 
		Provenance
	  */
	public void setEXME_Procedencia_ID (int EXME_Procedencia_ID)
	{
		if (EXME_Procedencia_ID < 1) 
			set_Value (COLUMNNAME_EXME_Procedencia_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_Procedencia_ID, Integer.valueOf(EXME_Procedencia_ID));
	}

	/** Get Provenance.
		@return Provenance
	  */
	public int getEXME_Procedencia_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Procedencia_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Provenance_Value.
		@param EXME_Procedencia_Value Provenance_Value	  */
	public void setEXME_Procedencia_Value (String EXME_Procedencia_Value)
	{
		set_Value (COLUMNNAME_EXME_Procedencia_Value, EXME_Procedencia_Value);
	}

	/** Get Provenance_Value.
		@return Provenance_Value	  */
	public String getEXME_Procedencia_Value () 
	{
		return (String)get_Value(COLUMNNAME_EXME_Procedencia_Value);
	}

	public I_EXME_ServPublico getEXME_ServPublico() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_ServPublico.Table_Name);
        I_EXME_ServPublico result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_ServPublico)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_ServPublico_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Public Services.
		@param EXME_ServPublico_ID 
		Public Services
	  */
	public void setEXME_ServPublico_ID (int EXME_ServPublico_ID)
	{
		if (EXME_ServPublico_ID < 1) 
			set_Value (COLUMNNAME_EXME_ServPublico_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_ServPublico_ID, Integer.valueOf(EXME_ServPublico_ID));
	}

	/** Get Public Services.
		@return Public Services
	  */
	public int getEXME_ServPublico_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_ServPublico_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Public Services_Value.
		@param EXME_ServPublico_Value Public Services_Value	  */
	public void setEXME_ServPublico_Value (String EXME_ServPublico_Value)
	{
		set_Value (COLUMNNAME_EXME_ServPublico_Value, EXME_ServPublico_Value);
	}

	/** Get Public Services_Value.
		@return Public Services_Value	  */
	public String getEXME_ServPublico_Value () 
	{
		return (String)get_Value(COLUMNNAME_EXME_ServPublico_Value);
	}

	public I_EXME_Tenencia getEXME_Tenencia() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_Tenencia.Table_Name);
        I_EXME_Tenencia result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_Tenencia)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Tenencia_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Tenancy.
		@param EXME_Tenencia_ID 
		Housing tenure
	  */
	public void setEXME_Tenencia_ID (int EXME_Tenencia_ID)
	{
		if (EXME_Tenencia_ID < 1) 
			set_Value (COLUMNNAME_EXME_Tenencia_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_Tenencia_ID, Integer.valueOf(EXME_Tenencia_ID));
	}

	/** Get Tenancy.
		@return Housing tenure
	  */
	public int getEXME_Tenencia_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Tenencia_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Tenure_Value.
		@param EXME_Tenencia_Value Tenure_Value	  */
	public void setEXME_Tenencia_Value (String EXME_Tenencia_Value)
	{
		set_Value (COLUMNNAME_EXME_Tenencia_Value, EXME_Tenencia_Value);
	}

	/** Get Tenure_Value.
		@return Tenure_Value	  */
	public String getEXME_Tenencia_Value () 
	{
		return (String)get_Value(COLUMNNAME_EXME_Tenencia_Value);
	}

	public I_EXME_TipoVivienda getEXME_TipoVivienda() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_TipoVivienda.Table_Name);
        I_EXME_TipoVivienda result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_TipoVivienda)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_TipoVivienda_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Housing type.
		@param EXME_TipoVivienda_ID 
		Housing type
	  */
	public void setEXME_TipoVivienda_ID (int EXME_TipoVivienda_ID)
	{
		if (EXME_TipoVivienda_ID < 1) 
			set_Value (COLUMNNAME_EXME_TipoVivienda_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_TipoVivienda_ID, Integer.valueOf(EXME_TipoVivienda_ID));
	}

	/** Get Housing type.
		@return Housing type
	  */
	public int getEXME_TipoVivienda_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_TipoVivienda_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Housing type_Value.
		@param EXME_TipoVivienda_Value Housing type_Value	  */
	public void setEXME_TipoVivienda_Value (String EXME_TipoVivienda_Value)
	{
		set_Value (COLUMNNAME_EXME_TipoVivienda_Value, EXME_TipoVivienda_Value);
	}

	/** Get Housing type_Value.
		@return Housing type_Value	  */
	public String getEXME_TipoVivienda_Value () 
	{
		return (String)get_Value(COLUMNNAME_EXME_TipoVivienda_Value);
	}

	/** Set User Shift.
		@param EXME_User_Cambia_ID User Shift	  */
	public void setEXME_User_Cambia_ID (int EXME_User_Cambia_ID)
	{
		if (EXME_User_Cambia_ID < 1) 
			set_Value (COLUMNNAME_EXME_User_Cambia_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_User_Cambia_ID, Integer.valueOf(EXME_User_Cambia_ID));
	}

	/** Get User Shift.
		@return User Shift	  */
	public int getEXME_User_Cambia_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_User_Cambia_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set User Shift_Value.
		@param EXME_User_Cambia_Value User Shift_Value	  */
	public void setEXME_User_Cambia_Value (String EXME_User_Cambia_Value)
	{
		set_Value (COLUMNNAME_EXME_User_Cambia_Value, EXME_User_Cambia_Value);
	}

	/** Get User Shift_Value.
		@return User Shift_Value	  */
	public String getEXME_User_Cambia_Value () 
	{
		return (String)get_Value(COLUMNNAME_EXME_User_Cambia_Value);
	}

	/** Set Validity.
		@param EXME_Vigencia_ID 
		Duration in Years for the Study of a Patient
	  */
	public void setEXME_Vigencia_ID (BigDecimal EXME_Vigencia_ID)
	{
		set_Value (COLUMNNAME_EXME_Vigencia_ID, EXME_Vigencia_ID);
	}

	/** Get Validity.
		@return Duration in Years for the Study of a Patient
	  */
	public BigDecimal getEXME_Vigencia_ID () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_EXME_Vigencia_ID);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Validity Value.
		@param EXME_Vigencia_Value 
		Validity Value
	  */
	public void setEXME_Vigencia_Value (String EXME_Vigencia_Value)
	{
		set_Value (COLUMNNAME_EXME_Vigencia_Value, EXME_Vigencia_Value);
	}

	/** Get Validity Value.
		@return Validity Value
	  */
	public String getEXME_Vigencia_Value () 
	{
		return (String)get_Value(COLUMNNAME_EXME_Vigencia_Value);
	}

	public I_EXME_Zona getEXME_Zona() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_Zona.Table_Name);
        I_EXME_Zona result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_Zona)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Zona_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Zone.
		@param EXME_Zona_ID 
		Zone
	  */
	public void setEXME_Zona_ID (int EXME_Zona_ID)
	{
		if (EXME_Zona_ID < 1) 
			set_Value (COLUMNNAME_EXME_Zona_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_Zona_ID, Integer.valueOf(EXME_Zona_ID));
	}

	/** Get Zone.
		@return Zone
	  */
	public int getEXME_Zona_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Zona_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Zone_Value.
		@param EXME_Zona_Value Zone_Value	  */
	public void setEXME_Zona_Value (String EXME_Zona_Value)
	{
		set_Value (COLUMNNAME_EXME_Zona_Value, EXME_Zona_Value);
	}

	/** Get Zone_Value.
		@return Zone_Value	  */
	public String getEXME_Zona_Value () 
	{
		return (String)get_Value(COLUMNNAME_EXME_Zona_Value);
	}

	/** Set Date of Last Change.
		@param Fecha_Ult_Act 
		Date of Last Change
	  */
	public void setFecha_Ult_Act (Timestamp Fecha_Ult_Act)
	{
		set_Value (COLUMNNAME_Fecha_Ult_Act, Fecha_Ult_Act);
	}

	/** Get Date of Last Change.
		@return Date of Last Change
	  */
	public Timestamp getFecha_Ult_Act () 
	{
		return (Timestamp)get_Value(COLUMNNAME_Fecha_Ult_Act);
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

	/** Set I_EXME_Clasificacion_ID.
		@param I_EXME_Clasificacion_ID I_EXME_Clasificacion_ID	  */
	public void setI_EXME_Clasificacion_ID (int I_EXME_Clasificacion_ID)
	{
		if (I_EXME_Clasificacion_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_I_EXME_Clasificacion_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_I_EXME_Clasificacion_ID, Integer.valueOf(I_EXME_Clasificacion_ID));
	}

	/** Get I_EXME_Clasificacion_ID.
		@return I_EXME_Clasificacion_ID	  */
	public int getI_EXME_Clasificacion_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_I_EXME_Clasificacion_ID);
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

	/** Set Imprime_Clas_Abierta.
		@param Imprime_Clas_Abierta Imprime_Clas_Abierta	  */
	public void setImprime_Clas_Abierta (boolean Imprime_Clas_Abierta)
	{
		set_Value (COLUMNNAME_Imprime_Clas_Abierta, Boolean.valueOf(Imprime_Clas_Abierta));
	}

	/** Get Imprime_Clas_Abierta.
		@return Imprime_Clas_Abierta	  */
	public boolean isImprime_Clas_Abierta () 
	{
		Object oo = get_Value(COLUMNNAME_Imprime_Clas_Abierta);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Children income.
		@param IngHijos 
		Children income
	  */
	public void setIngHijos (BigDecimal IngHijos)
	{
		set_Value (COLUMNNAME_IngHijos, IngHijos);
	}

	/** Get Children income.
		@return Children income
	  */
	public BigDecimal getIngHijos () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_IngHijos);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Income of Family Head.
		@param IngJefeFam 
		Income of Family Head
	  */
	public void setIngJefeFam (BigDecimal IngJefeFam)
	{
		set_Value (COLUMNNAME_IngJefeFam, IngJefeFam);
	}

	/** Get Income of Family Head.
		@return Income of Family Head
	  */
	public BigDecimal getIngJefeFam () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_IngJefeFam);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Other income.
		@param IngOtros 
		Other income
	  */
	public void setIngOtros (BigDecimal IngOtros)
	{
		set_Value (COLUMNNAME_IngOtros, IngOtros);
	}

	/** Get Other income.
		@return Other income
	  */
	public BigDecimal getIngOtros () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_IngOtros);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Locked.
		@param IsLocked 
		Locked
	  */
	public void setIsLocked (boolean IsLocked)
	{
		set_Value (COLUMNNAME_IsLocked, Boolean.valueOf(IsLocked));
	}

	/** Get Locked.
		@return Locked
	  */
	public boolean isLocked () 
	{
		Object oo = get_Value(COLUMNNAME_IsLocked);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Printed.
		@param IsPrinted 
		Indicates if this document / line is printed
	  */
	public void setIsPrinted (boolean IsPrinted)
	{
		set_Value (COLUMNNAME_IsPrinted, Boolean.valueOf(IsPrinted));
	}

	/** Get Printed.
		@return Indicates if this document / line is printed
	  */
	public boolean isPrinted () 
	{
		Object oo = get_Value(COLUMNNAME_IsPrinted);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
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

	/** Set Number of Members.
		@param NumMiembros 
		Number of Members
	  */
	public void setNumMiembros (BigDecimal NumMiembros)
	{
		set_Value (COLUMNNAME_NumMiembros, NumMiembros);
	}

	/** Get Number of Members.
		@return Number of Members
	  */
	public BigDecimal getNumMiembros () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_NumMiembros);
		if (bd == null)
			 return Env.ZERO;
		return bd;
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

	/** Set Percentage of Feeding.
		@param PorcAlimentacion 
		Percentage of Feeding
	  */
	public void setPorcAlimentacion (BigDecimal PorcAlimentacion)
	{
		set_Value (COLUMNNAME_PorcAlimentacion, PorcAlimentacion);
	}

	/** Get Percentage of Feeding.
		@return Percentage of Feeding
	  */
	public BigDecimal getPorcAlimentacion () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_PorcAlimentacion);
		if (bd == null)
			 return Env.ZERO;
		return bd;
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

	/** Set Discharge Points.
		@param PtsEgre 
		Discharge Points
	  */
	public void setPtsEgre (BigDecimal PtsEgre)
	{
		set_Value (COLUMNNAME_PtsEgre, PtsEgre);
	}

	/** Get Discharge Points.
		@return Discharge Points
	  */
	public BigDecimal getPtsEgre () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_PtsEgre);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Income Points.
		@param PtsIngresos 
		Income Points
	  */
	public void setPtsIngresos (BigDecimal PtsIngresos)
	{
		set_Value (COLUMNNAME_PtsIngresos, PtsIngresos);
	}

	/** Get Income Points.
		@return Income Points
	  */
	public BigDecimal getPtsIngresos () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_PtsIngresos);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Construction Equipment Points.
		@param PtsMatConst 
		Construction Equipment Points
	  */
	public void setPtsMatConst (BigDecimal PtsMatConst)
	{
		set_Value (COLUMNNAME_PtsMatConst, PtsMatConst);
	}

	/** Get Construction Equipment Points.
		@return Construction Equipment Points
	  */
	public BigDecimal getPtsMatConst () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_PtsMatConst);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Number of Patients Points.
		@param PtsNumEnferm 
		Number of Patients Points
	  */
	public void setPtsNumEnferm (BigDecimal PtsNumEnferm)
	{
		set_Value (COLUMNNAME_PtsNumEnferm, PtsNumEnferm);
	}

	/** Get Number of Patients Points.
		@return Number of Patients Points
	  */
	public BigDecimal getPtsNumEnferm () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_PtsNumEnferm);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Number of Rooms Points.
		@param PtsNumHabts 
		Number of Rooms Points
	  */
	public void setPtsNumHabts (BigDecimal PtsNumHabts)
	{
		set_Value (COLUMNNAME_PtsNumHabts, PtsNumHabts);
	}

	/** Get Number of Rooms Points.
		@return Number of Rooms Points
	  */
	public BigDecimal getPtsNumHabts () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_PtsNumHabts);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Number of People Points.
		@param PtsNumPers 
		Number of People Points
	  */
	public void setPtsNumPers (BigDecimal PtsNumPers)
	{
		set_Value (COLUMNNAME_PtsNumPers, PtsNumPers);
	}

	/** Get Number of People Points.
		@return Number of People Points
	  */
	public BigDecimal getPtsNumPers () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_PtsNumPers);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Occupation Points.
		@param PtsOcupacion 
		Occupation Points
	  */
	public void setPtsOcupacion (BigDecimal PtsOcupacion)
	{
		set_Value (COLUMNNAME_PtsOcupacion, PtsOcupacion);
	}

	/** Get Occupation Points.
		@return Occupation Points
	  */
	public BigDecimal getPtsOcupacion () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_PtsOcupacion);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Origin Points.
		@param PtsProcedencia 
		Origin Points
	  */
	public void setPtsProcedencia (BigDecimal PtsProcedencia)
	{
		set_Value (COLUMNNAME_PtsProcedencia, PtsProcedencia);
	}

	/** Get Origin Points.
		@return Origin Points
	  */
	public BigDecimal getPtsProcedencia () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_PtsProcedencia);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Public Service Points.
		@param PtsServPublico 
		Public Service Points
	  */
	public void setPtsServPublico (BigDecimal PtsServPublico)
	{
		set_Value (COLUMNNAME_PtsServPublico, PtsServPublico);
	}

	/** Get Public Service Points.
		@return Public Service Points
	  */
	public BigDecimal getPtsServPublico () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_PtsServPublico);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Possession Points.
		@param PtsTenencia 
		Possession Points
	  */
	public void setPtsTenencia (BigDecimal PtsTenencia)
	{
		set_Value (COLUMNNAME_PtsTenencia, PtsTenencia);
	}

	/** Get Possession Points.
		@return Possession Points
	  */
	public BigDecimal getPtsTenencia () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_PtsTenencia);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Type of House Points.
		@param PtsTipoVivienda 
		Type of House Points
	  */
	public void setPtsTipoVivienda (BigDecimal PtsTipoVivienda)
	{
		set_Value (COLUMNNAME_PtsTipoVivienda, PtsTipoVivienda);
	}

	/** Get Type of House Points.
		@return Type of House Points
	  */
	public BigDecimal getPtsTipoVivienda () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_PtsTipoVivienda);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Zone Points.
		@param PtsZona 
		Zone Points
	  */
	public void setPtsZona (BigDecimal PtsZona)
	{
		set_Value (COLUMNNAME_PtsZona, PtsZona);
	}

	/** Get Zone Points.
		@return Zone Points
	  */
	public BigDecimal getPtsZona () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_PtsZona);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Total Expense.
		@param TotalEgr 
		Total Expense
	  */
	public void setTotalEgr (BigDecimal TotalEgr)
	{
		set_Value (COLUMNNAME_TotalEgr, TotalEgr);
	}

	/** Get Total Expense.
		@return Total Expense
	  */
	public BigDecimal getTotalEgr () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_TotalEgr);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Total Income.
		@param TotalIng 
		Total Income
	  */
	public void setTotalIng (BigDecimal TotalIng)
	{
		set_Value (COLUMNNAME_TotalIng, TotalIng);
	}

	/** Get Total Income.
		@return Total Income
	  */
	public BigDecimal getTotalIng () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_TotalIng);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Total Points.
		@param TotalPts 
		Total Points
	  */
	public void setTotalPts (BigDecimal TotalPts)
	{
		set_Value (COLUMNNAME_TotalPts, TotalPts);
	}

	/** Get Total Points.
		@return Total Points
	  */
	public BigDecimal getTotalPts () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_TotalPts);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}
}