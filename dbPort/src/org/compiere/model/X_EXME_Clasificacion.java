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

/** Generated Model for EXME_Clasificacion
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_Clasificacion extends PO implements I_EXME_Clasificacion, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_Clasificacion (Properties ctx, int EXME_Clasificacion_ID, String trxName)
    {
      super (ctx, EXME_Clasificacion_ID, trxName);
      /** if (EXME_Clasificacion_ID == 0)
        {
			setDateOrdered (new Timestamp( System.currentTimeMillis() ));
// @#Date@
			setDateVaidTo (new Timestamp( System.currentTimeMillis() ));
// @#Date@
			setEgrAgua (Env.ZERO);
			setEgrAlimentacion (Env.ZERO);
			setEgrLuzCombustible (Env.ZERO);
			setEgrOtros (Env.ZERO);
			setEgrServicios (Env.ZERO);
			setEgrTransporte (Env.ZERO);
			setEgrVivienda (Env.ZERO);
			setEXME_Clasificacion_ID (0);
			setEXME_MatConst_ID (0);
			setEXME_NumEnferm_ID (0);
			setEXME_NumHabts_ID (0);
			setEXME_NumPers_ID (0);
			setEXME_Ocupacion_Clas_ID (0);
			setEXME_Paciente_ID (0);
			setEXME_Procedencia_ID (0);
			setEXME_ServPublico_ID (0);
			setEXME_Tenencia_ID (0);
			setEXME_TipoVivienda_ID (0);
			setEXME_Vigencia_ID (0);
			setEXME_Zona_ID (0);
			setIngHijos (Env.ZERO);
			setIngJefeFam (Env.ZERO);
// 0
			setIngOtros (Env.ZERO);
			setIsLocked (null);
			setNumMiembros (0);
// 1
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
        } */
    }

    /** Load Constructor */
    public X_EXME_Clasificacion (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_Clasificacion[")
        .append(get_ID()).append("]");
      return sb.toString();
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

	/** Set Occupation's Antiquity.
		@param Antiguedad_Ocupacion Occupation's Antiquity	  */
	public void setAntiguedad_Ocupacion (String Antiguedad_Ocupacion)
	{
		set_Value (COLUMNNAME_Antiguedad_Ocupacion, Antiguedad_Ocupacion);
	}

	/** Get Occupation's Antiquity.
		@return Occupation's Antiquity	  */
	public String getAntiguedad_Ocupacion () 
	{
		return (String)get_Value(COLUMNNAME_Antiguedad_Ocupacion);
	}

	/** Set Update Classification.
		@param CambiaClas 
		Update Classification
	  */
	public void setCambiaClas (String CambiaClas)
	{
		set_Value (COLUMNNAME_CambiaClas, CambiaClas);
	}

	/** Get Update Classification.
		@return Update Classification
	  */
	public String getCambiaClas () 
	{
		return (String)get_Value(COLUMNNAME_CambiaClas);
	}

	/** Set Feeding Notes.
		@param Comentario_Alimentacion Feeding Notes	  */
	public void setComentario_Alimentacion (String Comentario_Alimentacion)
	{
		set_Value (COLUMNNAME_Comentario_Alimentacion, Comentario_Alimentacion);
	}

	/** Get Feeding Notes.
		@return Feeding Notes	  */
	public String getComentario_Alimentacion () 
	{
		return (String)get_Value(COLUMNNAME_Comentario_Alimentacion);
	}

	/** Set household income Notes.
		@param Comentario_IngrFamiliar household income Notes	  */
	public void setComentario_IngrFamiliar (String Comentario_IngrFamiliar)
	{
		set_Value (COLUMNNAME_Comentario_IngrFamiliar, Comentario_IngrFamiliar);
	}

	/** Get household income Notes.
		@return household income Notes	  */
	public String getComentario_IngrFamiliar () 
	{
		return (String)get_Value(COLUMNNAME_Comentario_IngrFamiliar);
	}

	/** Set boulding material Notes.
		@param Comentario_MatConstruccion boulding material Notes	  */
	public void setComentario_MatConstruccion (String Comentario_MatConstruccion)
	{
		set_Value (COLUMNNAME_Comentario_MatConstruccion, Comentario_MatConstruccion);
	}

	/** Get boulding material Notes.
		@return boulding material Notes	  */
	public String getComentario_MatConstruccion () 
	{
		return (String)get_Value(COLUMNNAME_Comentario_MatConstruccion);
	}

	/** Set Rooms Number's Notes.
		@param Comentario_NumHabit 
		Notes for the total number of rooms in the house
	  */
	public void setComentario_NumHabit (String Comentario_NumHabit)
	{
		set_Value (COLUMNNAME_Comentario_NumHabit, Comentario_NumHabit);
	}

	/** Get Rooms Number's Notes.
		@return Notes for the total number of rooms in the house
	  */
	public String getComentario_NumHabit () 
	{
		return (String)get_Value(COLUMNNAME_Comentario_NumHabit);
	}

	/** Set Notes of number of people.
		@param Comentario_NumPers Notes of number of people	  */
	public void setComentario_NumPers (String Comentario_NumPers)
	{
		set_Value (COLUMNNAME_Comentario_NumPers, Comentario_NumPers);
	}

	/** Get Notes of number of people.
		@return Notes of number of people	  */
	public String getComentario_NumPers () 
	{
		return (String)get_Value(COLUMNNAME_Comentario_NumPers);
	}

	/** Set Notes of occupancy.
		@param Comentario_Ocupacion Notes of occupancy	  */
	public void setComentario_Ocupacion (String Comentario_Ocupacion)
	{
		set_Value (COLUMNNAME_Comentario_Ocupacion, Comentario_Ocupacion);
	}

	/** Get Notes of occupancy.
		@return Notes of occupancy	  */
	public String getComentario_Ocupacion () 
	{
		return (String)get_Value(COLUMNNAME_Comentario_Ocupacion);
	}

	/** Set Notes of provenance.
		@param Comentario_Procedencia Notes of provenance	  */
	public void setComentario_Procedencia (String Comentario_Procedencia)
	{
		set_Value (COLUMNNAME_Comentario_Procedencia, Comentario_Procedencia);
	}

	/** Get Notes of provenance.
		@return Notes of provenance	  */
	public String getComentario_Procedencia () 
	{
		return (String)get_Value(COLUMNNAME_Comentario_Procedencia);
	}

	/** Set Notes of family healt.
		@param Comentario_SaludFam Notes of family healt	  */
	public void setComentario_SaludFam (String Comentario_SaludFam)
	{
		set_Value (COLUMNNAME_Comentario_SaludFam, Comentario_SaludFam);
	}

	/** Get Notes of family healt.
		@return Notes of family healt	  */
	public String getComentario_SaludFam () 
	{
		return (String)get_Value(COLUMNNAME_Comentario_SaludFam);
	}

	/** Set Notes of intrahousehold services.
		@param Comentario_ServIntra Notes of intrahousehold services	  */
	public void setComentario_ServIntra (String Comentario_ServIntra)
	{
		set_Value (COLUMNNAME_Comentario_ServIntra, Comentario_ServIntra);
	}

	/** Get Notes of intrahousehold services.
		@return Notes of intrahousehold services	  */
	public String getComentario_ServIntra () 
	{
		return (String)get_Value(COLUMNNAME_Comentario_ServIntra);
	}

	/** Set Notes of tenancy.
		@param Comentario_Tenencia Notes of tenancy	  */
	public void setComentario_Tenencia (String Comentario_Tenencia)
	{
		set_Value (COLUMNNAME_Comentario_Tenencia, Comentario_Tenencia);
	}

	/** Get Notes of tenancy.
		@return Notes of tenancy	  */
	public String getComentario_Tenencia () 
	{
		return (String)get_Value(COLUMNNAME_Comentario_Tenencia);
	}

	/** Set Notes of housing.
		@param Comentario_Vivienda Notes of housing	  */
	public void setComentario_Vivienda (String Comentario_Vivienda)
	{
		set_Value (COLUMNNAME_Comentario_Vivienda, Comentario_Vivienda);
	}

	/** Get Notes of housing.
		@return Notes of housing	  */
	public String getComentario_Vivienda () 
	{
		return (String)get_Value(COLUMNNAME_Comentario_Vivienda);
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

	/** Set Water Expense.
		@param EgrAgua Water Expense	  */
	public void setEgrAgua (BigDecimal EgrAgua)
	{
		if (EgrAgua == null)
			throw new IllegalArgumentException ("EgrAgua is mandatory.");
		set_Value (COLUMNNAME_EgrAgua, EgrAgua);
	}

	/** Get Water Expense.
		@return Water Expense	  */
	public BigDecimal getEgrAgua () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_EgrAgua);
		if (bd == null)
			 return Env.ZERO;
		return bd;
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

	/** Set Light and Fuel Expenses.
		@param EgrLuzCombustible Light and Fuel Expenses	  */
	public void setEgrLuzCombustible (BigDecimal EgrLuzCombustible)
	{
		if (EgrLuzCombustible == null)
			throw new IllegalArgumentException ("EgrLuzCombustible is mandatory.");
		set_Value (COLUMNNAME_EgrLuzCombustible, EgrLuzCombustible);
	}

	/** Get Light and Fuel Expenses.
		@return Light and Fuel Expenses	  */
	public BigDecimal getEgrLuzCombustible () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_EgrLuzCombustible);
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

	/** Set Transpotation.
		@param EgrTransporte 
		Transpotation
	  */
	public void setEgrTransporte (BigDecimal EgrTransporte)
	{
		if (EgrTransporte == null)
			throw new IllegalArgumentException ("EgrTransporte is mandatory.");
		set_Value (COLUMNNAME_EgrTransporte, EgrTransporte);
	}

	/** Get Transpotation.
		@return Transpotation
	  */
	public BigDecimal getEgrTransporte () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_EgrTransporte);
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

	/** Set White Space.
		@param EspacioBlanco White Space	  */
	public void setEspacioBlanco (String EspacioBlanco)
	{
		throw new IllegalArgumentException ("EspacioBlanco is virtual column");	}

	/** Get White Space.
		@return White Space	  */
	public String getEspacioBlanco () 
	{
		return (String)get_Value(COLUMNNAME_EspacioBlanco);
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

	/** Set Classification.
		@param EXME_Clasificacion_ID 
		Classification
	  */
	public void setEXME_Clasificacion_ID (int EXME_Clasificacion_ID)
	{
		if (EXME_Clasificacion_ID < 1)
			 throw new IllegalArgumentException ("EXME_Clasificacion_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_Clasificacion_ID, Integer.valueOf(EXME_Clasificacion_ID));
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

	/** Set Status.
		@param EXME_Estatus_Clas_ID 
		Status
	  */
	public void setEXME_Estatus_Clas_ID (int EXME_Estatus_Clas_ID)
	{
		if (EXME_Estatus_Clas_ID < 1) 
			set_Value (COLUMNNAME_EXME_Estatus_Clas_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_Estatus_Clas_ID, Integer.valueOf(EXME_Estatus_Clas_ID));
	}

	/** Get Status.
		@return Status
	  */
	public int getEXME_Estatus_Clas_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Estatus_Clas_ID);
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

	/** Set Construction Material.
		@param EXME_MatConst_ID 
		Construction Material
	  */
	public void setEXME_MatConst_ID (int EXME_MatConst_ID)
	{
		if (EXME_MatConst_ID < 1)
			 throw new IllegalArgumentException ("EXME_MatConst_ID is mandatory.");
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

	/** Set Number of Sick People.
		@param EXME_NumEnferm_ID 
		Number of Sick People that  will be inside the patient's house
	  */
	public void setEXME_NumEnferm_ID (int EXME_NumEnferm_ID)
	{
		if (EXME_NumEnferm_ID < 1)
			 throw new IllegalArgumentException ("EXME_NumEnferm_ID is mandatory.");
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

	/** Set Number of Rooms.
		@param EXME_NumHabts_ID 
		Number of Rooms
	  */
	public void setEXME_NumHabts_ID (int EXME_NumHabts_ID)
	{
		if (EXME_NumHabts_ID < 1)
			 throw new IllegalArgumentException ("EXME_NumHabts_ID is mandatory.");
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

	/** Set Number of Persons.
		@param EXME_NumPers_ID 
		Number of Persons
	  */
	public void setEXME_NumPers_ID (int EXME_NumPers_ID)
	{
		if (EXME_NumPers_ID < 1)
			 throw new IllegalArgumentException ("EXME_NumPers_ID is mandatory.");
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
			 throw new IllegalArgumentException ("EXME_Ocupacion_Clas_ID is mandatory.");
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
		set_ValueNoCheck (COLUMNNAME_EXME_Paciente_ID, Integer.valueOf(EXME_Paciente_ID));
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

	/** Set Minimum Monthly Salaries.
		@param EXME_Salarios_Min_Mes_ID 
		Minimum Monthly Salaries
	  */
	public void setEXME_Salarios_Min_Mes_ID (int EXME_Salarios_Min_Mes_ID)
	{
		if (EXME_Salarios_Min_Mes_ID < 1) 
			set_Value (COLUMNNAME_EXME_Salarios_Min_Mes_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_Salarios_Min_Mes_ID, Integer.valueOf(EXME_Salarios_Min_Mes_ID));
	}

	/** Get Minimum Monthly Salaries.
		@return Minimum Monthly Salaries
	  */
	public int getEXME_Salarios_Min_Mes_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Salarios_Min_Mes_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_Servicio_Medico getEXME_Servicio_Medico() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_Servicio_Medico.Table_Name);
        I_EXME_Servicio_Medico result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_Servicio_Medico)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Servicio_Medico_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Medical Service.
		@param EXME_Servicio_Medico_ID 
		Medical Service
	  */
	public void setEXME_Servicio_Medico_ID (int EXME_Servicio_Medico_ID)
	{
		if (EXME_Servicio_Medico_ID < 1) 
			set_Value (COLUMNNAME_EXME_Servicio_Medico_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_Servicio_Medico_ID, Integer.valueOf(EXME_Servicio_Medico_ID));
	}

	/** Get Medical Service.
		@return Medical Service
	  */
	public int getEXME_Servicio_Medico_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Servicio_Medico_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Public Services.
		@param EXME_ServPublico_ID 
		Public Services
	  */
	public void setEXME_ServPublico_ID (int EXME_ServPublico_ID)
	{
		if (EXME_ServPublico_ID < 1)
			 throw new IllegalArgumentException ("EXME_ServPublico_ID is mandatory.");
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

	/** Set Tenancy.
		@param EXME_Tenencia_ID 
		Housing tenure
	  */
	public void setEXME_Tenencia_ID (int EXME_Tenencia_ID)
	{
		if (EXME_Tenencia_ID < 1)
			 throw new IllegalArgumentException ("EXME_Tenencia_ID is mandatory.");
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

	/** Set Housing type.
		@param EXME_TipoVivienda_ID 
		Housing type
	  */
	public void setEXME_TipoVivienda_ID (int EXME_TipoVivienda_ID)
	{
		if (EXME_TipoVivienda_ID < 1)
			 throw new IllegalArgumentException ("EXME_TipoVivienda_ID is mandatory.");
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

	/** Set Validity.
		@param EXME_Vigencia_ID 
		Duration in Years for the Study of a Patient
	  */
	public void setEXME_Vigencia_ID (int EXME_Vigencia_ID)
	{
		if (EXME_Vigencia_ID < 1)
			 throw new IllegalArgumentException ("EXME_Vigencia_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_Vigencia_ID, Integer.valueOf(EXME_Vigencia_ID));
	}

	/** Get Validity.
		@return Duration in Years for the Study of a Patient
	  */
	public int getEXME_Vigencia_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Vigencia_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Zone.
		@param EXME_Zona_ID 
		Zone
	  */
	public void setEXME_Zona_ID (int EXME_Zona_ID)
	{
		if (EXME_Zona_ID < 1)
			 throw new IllegalArgumentException ("EXME_Zona_ID is mandatory.");
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

	/** Set Imprime_Clas_Abierta.
		@param Imprime_Clas_Abierta Imprime_Clas_Abierta	  */
	public void setImprime_Clas_Abierta (String Imprime_Clas_Abierta)
	{
		set_Value (COLUMNNAME_Imprime_Clas_Abierta, Imprime_Clas_Abierta);
	}

	/** Get Imprime_Clas_Abierta.
		@return Imprime_Clas_Abierta	  */
	public String getImprime_Clas_Abierta () 
	{
		return (String)get_Value(COLUMNNAME_Imprime_Clas_Abierta);
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

	/** IsLocked AD_Reference_ID=1200058 */
	public static final int ISLOCKED_AD_Reference_ID=1200058;
	/** Yes = Y */
	public static final String ISLOCKED_Yes = "Y";
	/** No = N */
	public static final String ISLOCKED_No = "N";
	/** Set Locked.
		@param IsLocked 
		Locked
	  */
	public void setIsLocked (String IsLocked)
	{
		if (IsLocked == null) throw new IllegalArgumentException ("IsLocked is mandatory");
		if (IsLocked.equals("Y") || IsLocked.equals("N")); else throw new IllegalArgumentException ("IsLocked Invalid value - " + IsLocked + " - Reference_ID=1200058 - Y - N");		set_Value (COLUMNNAME_IsLocked, IsLocked);
	}

	/** Get Locked.
		@return Locked
	  */
	public String getIsLocked () 
	{
		return (String)get_Value(COLUMNNAME_IsLocked);
	}

	/** Set Printed.
		@param IsPrinted 
		Indicates if this document / line is printed
	  */
	public void setIsPrinted (String IsPrinted)
	{
		set_Value (COLUMNNAME_IsPrinted, IsPrinted);
	}

	/** Get Printed.
		@return Indicates if this document / line is printed
	  */
	public String getIsPrinted () 
	{
		return (String)get_Value(COLUMNNAME_IsPrinted);
	}

	/** Set Patient Name.
		@param Nombre_Pac 
		Patient Name
	  */
	public void setNombre_Pac (String Nombre_Pac)
	{
		throw new IllegalArgumentException ("Nombre_Pac is virtual column");	}

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

	/** Set Job Type.
		@param Tipo_Empleo 
		Job Type
	  */
	public void setTipo_Empleo (String Tipo_Empleo)
	{
		set_Value (COLUMNNAME_Tipo_Empleo, Tipo_Empleo);
	}

	/** Get Job Type.
		@return Job Type
	  */
	public String getTipo_Empleo () 
	{
		return (String)get_Value(COLUMNNAME_Tipo_Empleo);
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
}