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

/** Generated Model for EXME_Clasificacion_A
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_Clasificacion_A extends PO implements I_EXME_Clasificacion_A, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_Clasificacion_A (Properties ctx, int EXME_Clasificacion_A_ID, String trxName)
    {
      super (ctx, EXME_Clasificacion_A_ID, trxName);
      /** if (EXME_Clasificacion_A_ID == 0)
        {
			setDateOrdered (new Timestamp( System.currentTimeMillis() ));
			setDateVaidTo (new Timestamp( System.currentTimeMillis() ));
			setEgrAlimentacion (Env.ZERO);
			setEgrOtros (Env.ZERO);
			setEgrServicios (Env.ZERO);
			setEgrVivienda (Env.ZERO);
			setEstatus (null);
			setEXME_Clasificacion_A_ID (0);
			setEXME_Paciente_ID (0);
			setEXME_Procedencia_ID (0);
			setIngHijos (Env.ZERO);
			setIngJefeFam (Env.ZERO);
			setIngOtros (Env.ZERO);
			setIsLocked (false);
			setNumMiembros (0);
			setPorcAlimentacion (Env.ZERO);
			setPtsEgre (Env.ZERO);
			setPtsIngresos (Env.ZERO);
			setPtsMatConst (Env.ZERO);
			setPtsNumEnferm (Env.ZERO);
			setPtsNumHabts (Env.ZERO);
			setPtsNumPers (Env.ZERO);
			setPtsOcupacion (Env.ZERO);
			setPtsProcedencia (Env.ZERO);
			setPtsServPublico (Env.ZERO);
			setPtsTenencia (Env.ZERO);
			setPtsTipoVivienda (Env.ZERO);
			setPtsZona (Env.ZERO);
			setTotalEgr (Env.ZERO);
			setTotalIng (Env.ZERO);
			setTotalPts (Env.ZERO);
			setVersion (0);
        } */
    }

    /** Load Constructor */
    public X_EXME_Clasificacion_A (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_Clasificacion_A[")
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

	/** Set Date Ordered.
		@param DateOrdered 
		Date of Order
	  */
	public void setDateOrdered (Timestamp DateOrdered)
	{
		if (DateOrdered == null)
			throw new IllegalArgumentException ("DateOrdered is mandatory.");
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
		if (DateVaidTo == null)
			throw new IllegalArgumentException ("DateVaidTo is mandatory.");
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
		if (EgrAlimentacion == null)
			throw new IllegalArgumentException ("EgrAlimentacion is mandatory.");
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
		if (EgrOtros == null)
			throw new IllegalArgumentException ("EgrOtros is mandatory.");
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
		if (EgrServicios == null)
			throw new IllegalArgumentException ("EgrServicios is mandatory.");
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
		if (EgrVivienda == null)
			throw new IllegalArgumentException ("EgrVivienda is mandatory.");
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

	/** Set Status.
		@param Estatus 
		Status
	  */
	public void setEstatus (String Estatus)
	{
		if (Estatus == null)
			throw new IllegalArgumentException ("Estatus is mandatory.");
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

	/** Set Audit Classification.
		@param EXME_Clasificacion_A_ID 
		Audit Classification
	  */
	public void setEXME_Clasificacion_A_ID (int EXME_Clasificacion_A_ID)
	{
		if (EXME_Clasificacion_A_ID < 1)
			 throw new IllegalArgumentException ("EXME_Clasificacion_A_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_Clasificacion_A_ID, Integer.valueOf(EXME_Clasificacion_A_ID));
	}

	/** Get Audit Classification.
		@return Audit Classification
	  */
	public int getEXME_Clasificacion_A_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Clasificacion_A_ID);
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
			 throw new IllegalArgumentException ("EXME_Procedencia_ID is mandatory.");
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

	/** Set Children income.
		@param IngHijos 
		Children income
	  */
	public void setIngHijos (BigDecimal IngHijos)
	{
		if (IngHijos == null)
			throw new IllegalArgumentException ("IngHijos is mandatory.");
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
		if (IngJefeFam == null)
			throw new IllegalArgumentException ("IngJefeFam is mandatory.");
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
		if (IngOtros == null)
			throw new IllegalArgumentException ("IngOtros is mandatory.");
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

	/** Set Number of Members.
		@param NumMiembros 
		Number of Members
	  */
	public void setNumMiembros (int NumMiembros)
	{
		set_Value (COLUMNNAME_NumMiembros, Integer.valueOf(NumMiembros));
	}

	/** Get Number of Members.
		@return Number of Members
	  */
	public int getNumMiembros () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_NumMiembros);
		if (ii == null)
			 return 0;
		return ii.intValue();
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
		if (PorcAlimentacion == null)
			throw new IllegalArgumentException ("PorcAlimentacion is mandatory.");
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

	/** Set Discharge Points.
		@param PtsEgre 
		Discharge Points
	  */
	public void setPtsEgre (BigDecimal PtsEgre)
	{
		if (PtsEgre == null)
			throw new IllegalArgumentException ("PtsEgre is mandatory.");
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
		if (PtsIngresos == null)
			throw new IllegalArgumentException ("PtsIngresos is mandatory.");
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
		if (PtsMatConst == null)
			throw new IllegalArgumentException ("PtsMatConst is mandatory.");
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
		if (PtsNumEnferm == null)
			throw new IllegalArgumentException ("PtsNumEnferm is mandatory.");
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
		if (PtsNumHabts == null)
			throw new IllegalArgumentException ("PtsNumHabts is mandatory.");
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
		if (PtsNumPers == null)
			throw new IllegalArgumentException ("PtsNumPers is mandatory.");
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
		if (PtsOcupacion == null)
			throw new IllegalArgumentException ("PtsOcupacion is mandatory.");
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
		if (PtsProcedencia == null)
			throw new IllegalArgumentException ("PtsProcedencia is mandatory.");
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
		if (PtsServPublico == null)
			throw new IllegalArgumentException ("PtsServPublico is mandatory.");
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
		if (PtsTenencia == null)
			throw new IllegalArgumentException ("PtsTenencia is mandatory.");
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
		if (PtsTipoVivienda == null)
			throw new IllegalArgumentException ("PtsTipoVivienda is mandatory.");
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
		if (PtsZona == null)
			throw new IllegalArgumentException ("PtsZona is mandatory.");
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
		if (TotalEgr == null)
			throw new IllegalArgumentException ("TotalEgr is mandatory.");
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
		if (TotalIng == null)
			throw new IllegalArgumentException ("TotalIng is mandatory.");
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
		if (TotalPts == null)
			throw new IllegalArgumentException ("TotalPts is mandatory.");
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

	/** Set Version.
		@param Version 
		Version of the table definition
	  */
	public void setVersion (int Version)
	{
		set_Value (COLUMNNAME_Version, Integer.valueOf(Version));
	}

	/** Get Version.
		@return Version of the table definition
	  */
	public int getVersion () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Version);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}