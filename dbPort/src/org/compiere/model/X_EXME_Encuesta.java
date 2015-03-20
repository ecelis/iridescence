/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;

/** Generated Model for EXME_Encuesta
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_Encuesta extends PO implements I_EXME_Encuesta, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_Encuesta (Properties ctx, int EXME_Encuesta_ID, String trxName)
    {
      super (ctx, EXME_Encuesta_ID, trxName);
      /** if (EXME_Encuesta_ID == 0)
        {
			setCombust_Cocina (null);
// G
			setEXME_Encuesta_ID (0);
			setEXME_Paciente_ID (0);
// @EXME_Paciente_ID@
        } */
    }

    /** Load Constructor */
    public X_EXME_Encuesta (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_Encuesta[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set How many years have you been smoking?.
		@param Anios_Fuma 
		How many years have you been smoking?
	  */
	public void setAnios_Fuma (int Anios_Fuma)
	{
		set_Value (COLUMNNAME_Anios_Fuma, Integer.valueOf(Anios_Fuma));
	}

	/** Get How many years have you been smoking?.
		@return How many years have you been smoking?
	  */
	public int getAnios_Fuma () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Anios_Fuma);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set How many years did you smoke?.
		@param Anios_Fumo 
		How many years did you smoke?
	  */
	public void setAnios_Fumo (int Anios_Fumo)
	{
		set_Value (COLUMNNAME_Anios_Fumo, Integer.valueOf(Anios_Fumo));
	}

	/** Get How many years did you smoke?.
		@return How many years did you smoke?
	  */
	public int getAnios_Fumo () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Anios_Fumo);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Small boxes.
		@param Caj_Fuma 
		Small boxes
	  */
	public void setCaj_Fuma (boolean Caj_Fuma)
	{
		set_Value (COLUMNNAME_Caj_Fuma, Boolean.valueOf(Caj_Fuma));
	}

	/** Get Small boxes.
		@return Small boxes
	  */
	public boolean isCaj_Fuma () 
	{
		Object oo = get_Value(COLUMNNAME_Caj_Fuma);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Small boxes.
		@param Caj_Fumaba 
		Small boxes
	  */
	public void setCaj_Fumaba (boolean Caj_Fumaba)
	{
		set_Value (COLUMNNAME_Caj_Fumaba, Boolean.valueOf(Caj_Fumaba));
	}

	/** Get Small boxes.
		@return Small boxes
	  */
	public boolean isCaj_Fumaba () 
	{
		Object oo = get_Value(COLUMNNAME_Caj_Fumaba);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Combust_Cocina AD_Reference_ID=1200023 */
	public static final int COMBUST_COCINA_AD_Reference_ID=1200023;
	/** Gas = G */
	public static final String COMBUST_COCINA_Gas = "G";
	/** Firewood = L */
	public static final String COMBUST_COCINA_Firewood = "L";
	/** Coal = C */
	public static final String COMBUST_COCINA_Coal = "C";
	/** Oil = P */
	public static final String COMBUST_COCINA_Oil = "P";
	/** Set What combustible do you use to cook?.
		@param Combust_Cocina 
		What combustible do you use to cook?
	  */
	public void setCombust_Cocina (String Combust_Cocina)
	{
		if (Combust_Cocina == null) throw new IllegalArgumentException ("Combust_Cocina is mandatory");
		if (Combust_Cocina.equals("G") || Combust_Cocina.equals("L") || Combust_Cocina.equals("C") || Combust_Cocina.equals("P")); else throw new IllegalArgumentException ("Combust_Cocina Invalid value - " + Combust_Cocina + " - Reference_ID=1200023 - G - L - C - P");		set_Value (COLUMNNAME_Combust_Cocina, Combust_Cocina);
	}

	/** Get What combustible do you use to cook?.
		@return What combustible do you use to cook?
	  */
	public String getCombust_Cocina () 
	{
		return (String)get_Value(COLUMNNAME_Combust_Cocina);
	}

	/** Set Poll of Exhibition Factors.
		@param EXME_Encuesta_ID 
		Poll of Exhibition Factors
	  */
	public void setEXME_Encuesta_ID (int EXME_Encuesta_ID)
	{
		if (EXME_Encuesta_ID < 1)
			 throw new IllegalArgumentException ("EXME_Encuesta_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_Encuesta_ID, Integer.valueOf(EXME_Encuesta_ID));
	}

	/** Get Poll of Exhibition Factors.
		@return Poll of Exhibition Factors
	  */
	public int getEXME_Encuesta_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Encuesta_ID);
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

	/** Set During your work do you are exposed to smoke?.
		@param Expone_Humo 
		During your work do you are exposed to smoke?
	  */
	public void setExpone_Humo (boolean Expone_Humo)
	{
		set_Value (COLUMNNAME_Expone_Humo, Boolean.valueOf(Expone_Humo));
	}

	/** Get During your work do you are exposed to smoke?.
		@return During your work do you are exposed to smoke?
	  */
	public boolean isExpone_Humo () 
	{
		Object oo = get_Value(COLUMNNAME_Expone_Humo);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set During your work do you are exposed to the dust?.
		@param Expone_Polvo 
		During your work do you are exposed to the dust?
	  */
	public void setExpone_Polvo (boolean Expone_Polvo)
	{
		set_Value (COLUMNNAME_Expone_Polvo, Boolean.valueOf(Expone_Polvo));
	}

	/** Get During your work do you are exposed to the dust?.
		@return During your work do you are exposed to the dust?
	  */
	public boolean isExpone_Polvo () 
	{
		Object oo = get_Value(COLUMNNAME_Expone_Polvo);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set In some previous work, did you were exposed to smoke?.
		@param Expuso_Humo 
		In some previous work, did you were exposed to smoke?
	  */
	public void setExpuso_Humo (boolean Expuso_Humo)
	{
		set_Value (COLUMNNAME_Expuso_Humo, Boolean.valueOf(Expuso_Humo));
	}

	/** Get In some previous work, did you were exposed to smoke?.
		@return In some previous work, did you were exposed to smoke?
	  */
	public boolean isExpuso_Humo () 
	{
		Object oo = get_Value(COLUMNNAME_Expuso_Humo);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set In some previous work, did you were exposed to the dust?.
		@param Expuso_Polvo 
		In some previous work, did you were exposed to the dust?
	  */
	public void setExpuso_Polvo (boolean Expuso_Polvo)
	{
		set_Value (COLUMNNAME_Expuso_Polvo, Boolean.valueOf(Expuso_Polvo));
	}

	/** Get In some previous work, did you were exposed to the dust?.
		@return In some previous work, did you were exposed to the dust?
	  */
	public boolean isExpuso_Polvo () 
	{
		Object oo = get_Value(COLUMNNAME_Expuso_Polvo);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Do you smoke?.
		@param Fuma 
		Do you smoke?
	  */
	public void setFuma (boolean Fuma)
	{
		set_Value (COLUMNNAME_Fuma, Boolean.valueOf(Fuma));
	}

	/** Get Do you smoke?.
		@return Do you smoke?
	  */
	public boolean isFuma () 
	{
		Object oo = get_Value(COLUMNNAME_Fuma);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Is there any main avenue to less than 2 blocks?.
		@param Hay_Avenida 
		Is there any main avenue to less than 2 blocks?
	  */
	public void setHay_Avenida (boolean Hay_Avenida)
	{
		set_Value (COLUMNNAME_Hay_Avenida, Boolean.valueOf(Hay_Avenida));
	}

	/** Get Is there any main avenue to less than 2 blocks?.
		@return Is there any main avenue to less than 2 blocks?
	  */
	public boolean isHay_Avenida () 
	{
		Object oo = get_Value(COLUMNNAME_Hay_Avenida);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Is there any garbage collector near your house?.
		@param Hay_Basura 
		Is there any garbage collector near your house?
	  */
	public void setHay_Basura (boolean Hay_Basura)
	{
		set_Value (COLUMNNAME_Hay_Basura, Boolean.valueOf(Hay_Basura));
	}

	/** Get Is there any garbage collector near your house?.
		@return Is there any garbage collector near your house?
	  */
	public boolean isHay_Basura () 
	{
		Object oo = get_Value(COLUMNNAME_Hay_Basura);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Is there any stable in your district?.
		@param Hay_Establo 
		Is there any stable in your district?
	  */
	public void setHay_Establo (boolean Hay_Establo)
	{
		set_Value (COLUMNNAME_Hay_Establo, Boolean.valueOf(Hay_Establo));
	}

	/** Get Is there any stable in your district?.
		@return Is there any stable in your district?
	  */
	public boolean isHay_Establo () 
	{
		Object oo = get_Value(COLUMNNAME_Hay_Establo);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Is there any factory to less than 10 blocks?.
		@param Hay_Fabrica 
		Is there any factory to less than 10 blocks?
	  */
	public void setHay_Fabrica (boolean Hay_Fabrica)
	{
		set_Value (COLUMNNAME_Hay_Fabrica, Boolean.valueOf(Hay_Fabrica));
	}

	/** Get Is there any factory to less than 10 blocks?.
		@return Is there any factory to less than 10 blocks?
	  */
	public boolean isHay_Fabrica () 
	{
		Object oo = get_Value(COLUMNNAME_Hay_Fabrica);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Is there any gas station to less than 10 blocks?.
		@param Hay_Gasolinera 
		Is there any gas station to less than 10 blocks?
	  */
	public void setHay_Gasolinera (boolean Hay_Gasolinera)
	{
		set_Value (COLUMNNAME_Hay_Gasolinera, Boolean.valueOf(Hay_Gasolinera));
	}

	/** Get Is there any gas station to less than 10 blocks?.
		@return Is there any gas station to less than 10 blocks?
	  */
	public boolean isHay_Gasolinera () 
	{
		Object oo = get_Value(COLUMNNAME_Hay_Gasolinera);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Is there any aviaria farm in your house?.
		@param Hay_Granja 
		Is there any aviaria farm in your house?
	  */
	public void setHay_Granja (boolean Hay_Granja)
	{
		set_Value (COLUMNNAME_Hay_Granja, Boolean.valueOf(Hay_Granja));
	}

	/** Get Is there any aviaria farm in your house?.
		@return Is there any aviaria farm in your house?
	  */
	public boolean isHay_Granja () 
	{
		Object oo = get_Value(COLUMNNAME_Hay_Granja);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Are there smaller children of 6 years in your house?.
		@param Hay_Ninos 
		Are there smaller children of 6 years in your house?
	  */
	public void setHay_Ninos (boolean Hay_Ninos)
	{
		set_Value (COLUMNNAME_Hay_Ninos, Boolean.valueOf(Hay_Ninos));
	}

	/** Get Are there smaller children of 6 years in your house?.
		@return Are there smaller children of 6 years in your house?
	  */
	public boolean isHay_Ninos () 
	{
		Object oo = get_Value(COLUMNNAME_Hay_Ninos);
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

	/** Set During your work, do you handle chemical substances?.
		@param Maneja_Sustancia 
		During your work, do you handle chemical substances?
	  */
	public void setManeja_Sustancia (boolean Maneja_Sustancia)
	{
		set_Value (COLUMNNAME_Maneja_Sustancia, Boolean.valueOf(Maneja_Sustancia));
	}

	/** Get During your work, do you handle chemical substances?.
		@return During your work, do you handle chemical substances?
	  */
	public boolean isManeja_Sustancia () 
	{
		Object oo = get_Value(COLUMNNAME_Maneja_Sustancia);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set In some previous work, did you handle chemical substances?.
		@param Manejo_Sustancia 
		In some previous work, did you handle chemical substances?
	  */
	public void setManejo_Sustancia (boolean Manejo_Sustancia)
	{
		set_Value (COLUMNNAME_Manejo_Sustancia, Boolean.valueOf(Manejo_Sustancia));
	}

	/** Get In some previous work, did you handle chemical substances?.
		@return In some previous work, did you handle chemical substances?
	  */
	public boolean isManejo_Sustancia () 
	{
		Object oo = get_Value(COLUMNNAME_Manejo_Sustancia);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set In all its life it has smoked more than 100 cigarettes?.
		@param Mas100Cig 
		In all its life it has smoked more than 100 cigarettes?
	  */
	public void setMas100Cig (boolean Mas100Cig)
	{
		set_Value (COLUMNNAME_Mas100Cig, Boolean.valueOf(Mas100Cig));
	}

	/** Get In all its life it has smoked more than 100 cigarettes?.
		@return In all its life it has smoked more than 100 cigarettes?
	  */
	public boolean isMas100Cig () 
	{
		Object oo = get_Value(COLUMNNAME_Mas100Cig);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Months.
		@param Meses_Fuma 
		Months
	  */
	public void setMeses_Fuma (int Meses_Fuma)
	{
		set_Value (COLUMNNAME_Meses_Fuma, Integer.valueOf(Meses_Fuma));
	}

	/** Get Months.
		@return Months
	  */
	public int getMeses_Fuma () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Meses_Fuma);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Months.
		@param Meses_Fumo 
		Months
	  */
	public void setMeses_Fumo (int Meses_Fumo)
	{
		set_Value (COLUMNNAME_Meses_Fumo, Integer.valueOf(Meses_Fumo));
	}

	/** Get Months.
		@return Months
	  */
	public int getMeses_Fumo () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Meses_Fumo);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set How many people sleep with the patient?.
		@param No_Pers_Pac 
		How many people sleep in the same room with the patient?
	  */
	public void setNo_Pers_Pac (int No_Pers_Pac)
	{
		set_Value (COLUMNNAME_No_Pers_Pac, Integer.valueOf(No_Pers_Pac));
	}

	/** Get How many people sleep with the patient?.
		@return How many people sleep in the same room with the patient?
	  */
	public int getNo_Pers_Pac () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_No_Pers_Pac);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Another person smokes in your house?.
		@param Otra_Fuma 
		Another person smokes in your house?
	  */
	public void setOtra_Fuma (boolean Otra_Fuma)
	{
		set_Value (COLUMNNAME_Otra_Fuma, Boolean.valueOf(Otra_Fuma));
	}

	/** Get Another person smokes in your house?.
		@return Another person smokes in your house?
	  */
	public boolean isOtra_Fuma () 
	{
		Object oo = get_Value(COLUMNNAME_Otra_Fuma);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Another person smoked in your house?.
		@param Otra_Fumaba 
		Another person smoked in your house?
	  */
	public void setOtra_Fumaba (boolean Otra_Fumaba)
	{
		set_Value (COLUMNNAME_Otra_Fumaba, Boolean.valueOf(Otra_Fumaba));
	}

	/** Get Another person smoked in your house?.
		@return Another person smoked in your house?
	  */
	public boolean isOtra_Fumaba () 
	{
		Object oo = get_Value(COLUMNNAME_Otra_Fumaba);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Other.
		@param Otro_Combust 
		Another combustible
	  */
	public void setOtro_Combust (String Otro_Combust)
	{
		set_Value (COLUMNNAME_Otro_Combust, Otro_Combust);
	}

	/** Get Other.
		@return Another combustible
	  */
	public String getOtro_Combust () 
	{
		return (String)get_Value(COLUMNNAME_Otro_Combust);
	}

	/** Set How many cigarettes do you smoke?.
		@param Qty_Fum 
		How many cigarettes do you smoke?
	  */
	public void setQty_Fum (int Qty_Fum)
	{
		set_Value (COLUMNNAME_Qty_Fum, Integer.valueOf(Qty_Fum));
	}

	/** Get How many cigarettes do you smoke?.
		@return How many cigarettes do you smoke?
	  */
	public int getQty_Fum () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Qty_Fum);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set How many cigarettes did you smoke at day?.
		@param Qty_Fumaba 
		How many cigarettes did you smoke at day?
	  */
	public void setQty_Fumaba (int Qty_Fumaba)
	{
		set_Value (COLUMNNAME_Qty_Fumaba, Integer.valueOf(Qty_Fumaba));
	}

	/** Get How many cigarettes did you smoke at day?.
		@return How many cigarettes did you smoke at day?
	  */
	public int getQty_Fumaba () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Qty_Fumaba);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set How many hours are the windows open?.
		@param Qty_Hr_Abierta 
		How many hours at day are the windows open?
	  */
	public void setQty_Hr_Abierta (int Qty_Hr_Abierta)
	{
		set_Value (COLUMNNAME_Qty_Hr_Abierta, Integer.valueOf(Qty_Hr_Abierta));
	}

	/** Get How many hours are the windows open?.
		@return How many hours at day are the windows open?
	  */
	public int getQty_Hr_Abierta () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Qty_Hr_Abierta);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set How many windows does the bedroom have?.
		@param Qty_Ventanas 
		How many windows does the bedroom have?
	  */
	public void setQty_Ventanas (int Qty_Ventanas)
	{
		set_Value (COLUMNNAME_Qty_Ventanas, Integer.valueOf(Qty_Ventanas));
	}

	/** Get How many windows does the bedroom have?.
		@return How many windows does the bedroom have?
	  */
	public int getQty_Ventanas () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Qty_Ventanas);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Have you ever had carpet in your bedroom?.
		@param Tenido_Alfombra 
		Have you ever had carpet in your bedroom?
	  */
	public void setTenido_Alfombra (boolean Tenido_Alfombra)
	{
		set_Value (COLUMNNAME_Tenido_Alfombra, Boolean.valueOf(Tenido_Alfombra));
	}

	/** Get Have you ever had carpet in your bedroom?.
		@return Have you ever had carpet in your bedroom?
	  */
	public boolean isTenido_Alfombra () 
	{
		Object oo = get_Value(COLUMNNAME_Tenido_Alfombra);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Have you ever had another kind of animal?.
		@param Tenido_Animal 
		Have you ever had another kind of animal in your house?
	  */
	public void setTenido_Animal (boolean Tenido_Animal)
	{
		set_Value (COLUMNNAME_Tenido_Animal, Boolean.valueOf(Tenido_Animal));
	}

	/** Get Have you ever had another kind of animal?.
		@return Have you ever had another kind of animal in your house?
	  */
	public boolean isTenido_Animal () 
	{
		Object oo = get_Value(COLUMNNAME_Tenido_Animal);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Have you ever had birds in your house?.
		@param Tenido_Ave 
		Have you ever had birds in your house?
	  */
	public void setTenido_Ave (boolean Tenido_Ave)
	{
		set_Value (COLUMNNAME_Tenido_Ave, Boolean.valueOf(Tenido_Ave));
	}

	/** Get Have you ever had birds in your house?.
		@return Have you ever had birds in your house?
	  */
	public boolean isTenido_Ave () 
	{
		Object oo = get_Value(COLUMNNAME_Tenido_Ave);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set A year ago, did you see cockroaches in your house?.
		@param Tenido_Cucar 
		A year ago, did you see cockroaches in your house?
	  */
	public void setTenido_Cucar (boolean Tenido_Cucar)
	{
		set_Value (COLUMNNAME_Tenido_Cucar, Boolean.valueOf(Tenido_Cucar));
	}

	/** Get A year ago, did you see cockroaches in your house?.
		@return A year ago, did you see cockroaches in your house?
	  */
	public boolean isTenido_Cucar () 
	{
		Object oo = get_Value(COLUMNNAME_Tenido_Cucar);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Have you ever had cats within your house?.
		@param Tenido_Gato 
		Have you ever had cats within your house?
	  */
	public void setTenido_Gato (boolean Tenido_Gato)
	{
		set_Value (COLUMNNAME_Tenido_Gato, Boolean.valueOf(Tenido_Gato));
	}

	/** Get Have you ever had cats within your house?.
		@return Have you ever had cats within your house?
	  */
	public boolean isTenido_Gato () 
	{
		Object oo = get_Value(COLUMNNAME_Tenido_Gato);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set In the walls of your house, have their ever had humidity?.
		@param Tenido_Humedad 
		In the walls of your house, have their ever had humidity?
	  */
	public void setTenido_Humedad (boolean Tenido_Humedad)
	{
		set_Value (COLUMNNAME_Tenido_Humedad, Boolean.valueOf(Tenido_Humedad));
	}

	/** Get In the walls of your house, have their ever had humidity?.
		@return In the walls of your house, have their ever had humidity?
	  */
	public boolean isTenido_Humedad () 
	{
		Object oo = get_Value(COLUMNNAME_Tenido_Humedad);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Have you ever had dogs within your house?.
		@param Tenido_Perro 
		Have you ever had dogs within your house?
	  */
	public void setTenido_Perro (boolean Tenido_Perro)
	{
		set_Value (COLUMNNAME_Tenido_Perro, Boolean.valueOf(Tenido_Perro));
	}

	/** Get Have you ever had dogs within your house?.
		@return Have you ever had dogs within your house?
	  */
	public boolean isTenido_Perro () 
	{
		Object oo = get_Value(COLUMNNAME_Tenido_Perro);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Do you have carpet in your bedroom?.
		@param Tiene_Alfombra 
		Do you have carpet in your bedroom?
	  */
	public void setTiene_Alfombra (boolean Tiene_Alfombra)
	{
		set_Value (COLUMNNAME_Tiene_Alfombra, Boolean.valueOf(Tiene_Alfombra));
	}

	/** Get Do you have carpet in your bedroom?.
		@return Do you have carpet in your bedroom?
	  */
	public boolean isTiene_Alfombra () 
	{
		Object oo = get_Value(COLUMNNAME_Tiene_Alfombra);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Is there another kind of animal in your house?.
		@param Tiene_Animal 
		Is there another kind of animal in your house?
	  */
	public void setTiene_Animal (boolean Tiene_Animal)
	{
		set_Value (COLUMNNAME_Tiene_Animal, Boolean.valueOf(Tiene_Animal));
	}

	/** Get Is there another kind of animal in your house?.
		@return Is there another kind of animal in your house?
	  */
	public boolean isTiene_Animal () 
	{
		Object oo = get_Value(COLUMNNAME_Tiene_Animal);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Do you have birds in your house?.
		@param Tiene_Ave 
		Do you have birds in your house?
	  */
	public void setTiene_Ave (boolean Tiene_Ave)
	{
		set_Value (COLUMNNAME_Tiene_Ave, Boolean.valueOf(Tiene_Ave));
	}

	/** Get Do you have birds in your house?.
		@return Do you have birds in your house?
	  */
	public boolean isTiene_Ave () 
	{
		Object oo = get_Value(COLUMNNAME_Tiene_Ave);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Do you have a computer?.
		@param Tiene_Computadora 
		Do you have a computer?
	  */
	public void setTiene_Computadora (boolean Tiene_Computadora)
	{
		set_Value (COLUMNNAME_Tiene_Computadora, Boolean.valueOf(Tiene_Computadora));
	}

	/** Get Do you have a computer?.
		@return Do you have a computer?
	  */
	public boolean isTiene_Computadora () 
	{
		Object oo = get_Value(COLUMNNAME_Tiene_Computadora);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set A month ago, did you see cockroaches in your house?.
		@param Tiene_Cucar 
		A month ago, did you see cockroaches in your house?
	  */
	public void setTiene_Cucar (boolean Tiene_Cucar)
	{
		set_Value (COLUMNNAME_Tiene_Cucar, Boolean.valueOf(Tiene_Cucar));
	}

	/** Get A month ago, did you see cockroaches in your house?.
		@return A month ago, did you see cockroaches in your house?
	  */
	public boolean isTiene_Cucar () 
	{
		Object oo = get_Value(COLUMNNAME_Tiene_Cucar);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Do you have cats within your house?.
		@param Tiene_Gato 
		Do you have cats within your house?
	  */
	public void setTiene_Gato (boolean Tiene_Gato)
	{
		set_Value (COLUMNNAME_Tiene_Gato, Boolean.valueOf(Tiene_Gato));
	}

	/** Get Do you have cats within your house?.
		@return Do you have cats within your house?
	  */
	public boolean isTiene_Gato () 
	{
		Object oo = get_Value(COLUMNNAME_Tiene_Gato);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Do you have electrical furnace or bread toaster?.
		@param Tiene_Horno 
		Do you have electrical furnace or bread toaster?
	  */
	public void setTiene_Horno (boolean Tiene_Horno)
	{
		set_Value (COLUMNNAME_Tiene_Horno, Boolean.valueOf(Tiene_Horno));
	}

	/** Get Do you have electrical furnace or bread toaster?.
		@return Do you have electrical furnace or bread toaster?
	  */
	public boolean isTiene_Horno () 
	{
		Object oo = get_Value(COLUMNNAME_Tiene_Horno);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set In the walls of your house, do they have humidity?.
		@param Tiene_Humedad 
		In the walls of your house, do they have humidity?
	  */
	public void setTiene_Humedad (boolean Tiene_Humedad)
	{
		set_Value (COLUMNNAME_Tiene_Humedad, Boolean.valueOf(Tiene_Humedad));
	}

	/** Get In the walls of your house, do they have humidity?.
		@return In the walls of your house, do they have humidity?
	  */
	public boolean isTiene_Humedad () 
	{
		Object oo = get_Value(COLUMNNAME_Tiene_Humedad);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Do you have microwave?.
		@param Tiene_Microondas 
		Do you have microwave?
	  */
	public void setTiene_Microondas (boolean Tiene_Microondas)
	{
		set_Value (COLUMNNAME_Tiene_Microondas, Boolean.valueOf(Tiene_Microondas));
	}

	/** Get Do you have microwave?.
		@return Do you have microwave?
	  */
	public boolean isTiene_Microondas () 
	{
		Object oo = get_Value(COLUMNNAME_Tiene_Microondas);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Do you have dogs within your house?.
		@param Tiene_Perro 
		Do you have dogs within your house?
	  */
	public void setTiene_Perro (boolean Tiene_Perro)
	{
		set_Value (COLUMNNAME_Tiene_Perro, Boolean.valueOf(Tiene_Perro));
	}

	/** Get Do you have dogs within your house?.
		@return Do you have dogs within your house?
	  */
	public boolean isTiene_Perro () 
	{
		Object oo = get_Value(COLUMNNAME_Tiene_Perro);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Do you have land floor?.
		@param Tiene_Piso 
		Do you have land floor?
	  */
	public void setTiene_Piso (boolean Tiene_Piso)
	{
		set_Value (COLUMNNAME_Tiene_Piso, Boolean.valueOf(Tiene_Piso));
	}

	/** Get Do you have land floor?.
		@return Do you have land floor?
	  */
	public boolean isTiene_Piso () 
	{
		Object oo = get_Value(COLUMNNAME_Tiene_Piso);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Do you have refrigerator?.
		@param Tiene_Refri 
		Do you have refrigerator?
	  */
	public void setTiene_Refri (boolean Tiene_Refri)
	{
		set_Value (COLUMNNAME_Tiene_Refri, Boolean.valueOf(Tiene_Refri));
	}

	/** Get Do you have refrigerator?.
		@return Do you have refrigerator?
	  */
	public boolean isTiene_Refri () 
	{
		Object oo = get_Value(COLUMNNAME_Tiene_Refri);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Do you have phone?.
		@param Tiene_Telefono 
		Do you have phone?
	  */
	public void setTiene_Telefono (boolean Tiene_Telefono)
	{
		set_Value (COLUMNNAME_Tiene_Telefono, Boolean.valueOf(Tiene_Telefono));
	}

	/** Get Do you have phone?.
		@return Do you have phone?
	  */
	public boolean isTiene_Telefono () 
	{
		Object oo = get_Value(COLUMNNAME_Tiene_Telefono);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Do you have television?.
		@param Tiene_TV 
		Do you have television?
	  */
	public void setTiene_TV (boolean Tiene_TV)
	{
		set_Value (COLUMNNAME_Tiene_TV, Boolean.valueOf(Tiene_TV));
	}

	/** Get Do you have television?.
		@return Do you have television?
	  */
	public boolean isTiene_TV () 
	{
		Object oo = get_Value(COLUMNNAME_Tiene_TV);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Do you have windows in your bedroom?.
		@param Tiene_Ventanas 
		Do you have windows in your bedroom?
	  */
	public void setTiene_Ventanas (boolean Tiene_Ventanas)
	{
		set_Value (COLUMNNAME_Tiene_Ventanas, Boolean.valueOf(Tiene_Ventanas));
	}

	/** Get Do you have windows in your bedroom?.
		@return Do you have windows in your bedroom?
	  */
	public boolean isTiene_Ventanas () 
	{
		Object oo = get_Value(COLUMNNAME_Tiene_Ventanas);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Do you drink alcohol regularly?.
		@param Toma_Bebidas 
		Do you drink alcohol regularly?
	  */
	public void setToma_Bebidas (boolean Toma_Bebidas)
	{
		set_Value (COLUMNNAME_Toma_Bebidas, Boolean.valueOf(Toma_Bebidas));
	}

	/** Get Do you drink alcohol regularly?.
		@return Do you drink alcohol regularly?
	  */
	public boolean isToma_Bebidas () 
	{
		Object oo = get_Value(COLUMNNAME_Toma_Bebidas);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Do you consume Canderel, Nutrasweet or diet drink?.
		@param Toma_Dieta 
		Do you consume Canderel, Nutrasweet or diet drink?
	  */
	public void setToma_Dieta (boolean Toma_Dieta)
	{
		set_Value (COLUMNNAME_Toma_Dieta, Boolean.valueOf(Toma_Dieta));
	}

	/** Get Do you consume Canderel, Nutrasweet or diet drink?.
		@return Do you consume Canderel, Nutrasweet or diet drink?
	  */
	public boolean isToma_Dieta () 
	{
		Object oo = get_Value(COLUMNNAME_Toma_Dieta);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Do you took medicines to sleep?.
		@param Toma_Med_Dormir 
		Do you took medicines to sleep?
	  */
	public void setToma_Med_Dormir (boolean Toma_Med_Dormir)
	{
		set_Value (COLUMNNAME_Toma_Med_Dormir, Boolean.valueOf(Toma_Med_Dormir));
	}

	/** Get Do you took medicines to sleep?.
		@return Do you took medicines to sleep?
	  */
	public boolean isToma_Med_Dormir () 
	{
		Object oo = get_Value(COLUMNNAME_Toma_Med_Dormir);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Do you take vitamins regularly?.
		@param Toma_Vitaminas 
		Do you take vitamins regularly?
	  */
	public void setToma_Vitaminas (boolean Toma_Vitaminas)
	{
		set_Value (COLUMNNAME_Toma_Vitaminas, Boolean.valueOf(Toma_Vitaminas));
	}

	/** Get Do you take vitamins regularly?.
		@return Do you take vitamins regularly?
	  */
	public boolean isToma_Vitaminas () 
	{
		Object oo = get_Value(COLUMNNAME_Toma_Vitaminas);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Do you ever have drunk alcohol regularly?.
		@param Tomo_Bebidas 
		Do you ever have drunk alcohol regularly?
	  */
	public void setTomo_Bebidas (boolean Tomo_Bebidas)
	{
		set_Value (COLUMNNAME_Tomo_Bebidas, Boolean.valueOf(Tomo_Bebidas));
	}

	/** Get Do you ever have drunk alcohol regularly?.
		@return Do you ever have drunk alcohol regularly?
	  */
	public boolean isTomo_Bebidas () 
	{
		Object oo = get_Value(COLUMNNAME_Tomo_Bebidas);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Did you consume Canderel, Nutrasweet or diet drink?.
		@param Tomo_Dieta 
		Did you consume Canderel, Nutrasweet or diet drink?
	  */
	public void setTomo_Dieta (boolean Tomo_Dieta)
	{
		set_Value (COLUMNNAME_Tomo_Dieta, Boolean.valueOf(Tomo_Dieta));
	}

	/** Get Did you consume Canderel, Nutrasweet or diet drink?.
		@return Did you consume Canderel, Nutrasweet or diet drink?
	  */
	public boolean isTomo_Dieta () 
	{
		Object oo = get_Value(COLUMNNAME_Tomo_Dieta);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Did you took medicines to sleep?.
		@param Tomo_Med_Dormir 
		Did you took medicines to sleep?
	  */
	public void setTomo_Med_Dormir (boolean Tomo_Med_Dormir)
	{
		set_Value (COLUMNNAME_Tomo_Med_Dormir, Boolean.valueOf(Tomo_Med_Dormir));
	}

	/** Get Did you took medicines to sleep?.
		@return Did you took medicines to sleep?
	  */
	public boolean isTomo_Med_Dormir () 
	{
		Object oo = get_Value(COLUMNNAME_Tomo_Med_Dormir);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Do you used to taking vitamins regularly?.
		@param Tomo_Vitaminas 
		Do you used to taking vitamins regularly?
	  */
	public void setTomo_Vitaminas (boolean Tomo_Vitaminas)
	{
		set_Value (COLUMNNAME_Tomo_Vitaminas, Boolean.valueOf(Tomo_Vitaminas));
	}

	/** Get Do you used to taking vitamins regularly?.
		@return Do you used to taking vitamins regularly?
	  */
	public boolean isTomo_Vitaminas () 
	{
		Object oo = get_Value(COLUMNNAME_Tomo_Vitaminas);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Have you ever used insecticide in your house?.
		@param Usado_Insectic 
		Have you ever used insecticide in your house?
	  */
	public void setUsado_Insectic (boolean Usado_Insectic)
	{
		set_Value (COLUMNNAME_Usado_Insectic, Boolean.valueOf(Usado_Insectic));
	}

	/** Get Have you ever used insecticide in your house?.
		@return Have you ever used insecticide in your house?
	  */
	public boolean isUsado_Insectic () 
	{
		Object oo = get_Value(COLUMNNAME_Usado_Insectic);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Do you use insecticide in your house?.
		@param Usa_Insectic 
		Do you use insecticide in your house?
	  */
	public void setUsa_Insectic (boolean Usa_Insectic)
	{
		set_Value (COLUMNNAME_Usa_Insectic, Boolean.valueOf(Usa_Insectic));
	}

	/** Get Do you use insecticide in your house?.
		@return Do you use insecticide in your house?
	  */
	public boolean isUsa_Insectic () 
	{
		Object oo = get_Value(COLUMNNAME_Usa_Insectic);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Have you ever used coal to cook?.
		@param Uso_Carbon 
		Have you ever used coal to cook?
	  */
	public void setUso_Carbon (boolean Uso_Carbon)
	{
		set_Value (COLUMNNAME_Uso_Carbon, Boolean.valueOf(Uso_Carbon));
	}

	/** Get Have you ever used coal to cook?.
		@return Have you ever used coal to cook?
	  */
	public boolean isUso_Carbon () 
	{
		Object oo = get_Value(COLUMNNAME_Uso_Carbon);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Have you ever used firewood to cook?.
		@param Uso_Lena 
		Have you ever used firewood to cook?
	  */
	public void setUso_Lena (boolean Uso_Lena)
	{
		set_Value (COLUMNNAME_Uso_Lena, Boolean.valueOf(Uso_Lena));
	}

	/** Get Have you ever used firewood to cook?.
		@return Have you ever used firewood to cook?
	  */
	public boolean isUso_Lena () 
	{
		Object oo = get_Value(COLUMNNAME_Uso_Lena);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}
}