/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

/** Generated Model for EXME_Area
 *  @author Generated Class 
 *  @version Release 1.1.2 - $Id$ */
public class X_EXME_Area extends PO implements I_EXME_Area, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_Area (Properties ctx, int EXME_Area_ID, String trxName)
    {
      super (ctx, EXME_Area_ID, trxName);
      /** if (EXME_Area_ID == 0)
        {
			setConEquipos (false);
// N
			setEXME_Area_ID (0);
			setIsAutoGenerated (false);
			setIsTemplate (false);
			setName (null);
			setTipoArea (null);
			setValue (null);
        } */
    }

    /** Load Constructor */
    public X_EXME_Area (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_Area[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Medical equipment do you have?.
		@param ConEquipos 
		Medical equipment do you have?
	  */
	public void setConEquipos (boolean ConEquipos)
	{
		set_Value (COLUMNNAME_ConEquipos, Boolean.valueOf(ConEquipos));
	}

	/** Get Medical equipment do you have?.
		@return Medical equipment do you have?
	  */
	public boolean isConEquipos () 
	{
		Object oo = get_Value(COLUMNNAME_ConEquipos);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Description.
		@param Description 
		Optional short description of the record
	  */
	public void setDescription (String Description)
	{
		set_Value (COLUMNNAME_Description, Description);
	}

	/** Get Description.
		@return Optional short description of the record
	  */
	public String getDescription () 
	{
		return (String)get_Value(COLUMNNAME_Description);
	}

	/** Set Service.
		@param EXME_Area_ID 
		Service
	  */
	public void setEXME_Area_ID (int EXME_Area_ID)
	{
		if (EXME_Area_ID < 1)
			 throw new IllegalArgumentException ("EXME_Area_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_Area_ID, Integer.valueOf(EXME_Area_ID));
	}

	/** Get Service.
		@return Service
	  */
	public int getEXME_Area_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Area_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_FacTypeCode getEXME_FacTypeCode() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_FacTypeCode.Table_Name);
        I_EXME_FacTypeCode result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_FacTypeCode)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_FacTypeCode_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Fac. Type Code.
		@param EXME_FacTypeCode_ID 
		Fac. Type Code
	  */
	public void setEXME_FacTypeCode_ID (int EXME_FacTypeCode_ID)
	{
		if (EXME_FacTypeCode_ID < 1) 
			set_Value (COLUMNNAME_EXME_FacTypeCode_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_FacTypeCode_ID, Integer.valueOf(EXME_FacTypeCode_ID));
	}

	/** Get Fac. Type Code.
		@return Fac. Type Code
	  */
	public int getEXME_FacTypeCode_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_FacTypeCode_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_Frequency1 getEXME_Frequency1() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_Frequency1.Table_Name);
        I_EXME_Frequency1 result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_Frequency1)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Frequency1_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Frequency 1.
		@param EXME_Frequency1_ID 
		Frequency Header ID
	  */
	public void setEXME_Frequency1_ID (int EXME_Frequency1_ID)
	{
		if (EXME_Frequency1_ID < 1) 
			set_Value (COLUMNNAME_EXME_Frequency1_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_Frequency1_ID, Integer.valueOf(EXME_Frequency1_ID));
	}

	/** Get Frequency 1.
		@return Frequency Header ID
	  */
	public int getEXME_Frequency1_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Frequency1_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_Frequency2 getEXME_Frequency2() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_Frequency2.Table_Name);
        I_EXME_Frequency2 result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_Frequency2)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Frequency2_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Frequency 2.
		@param EXME_Frequency2_ID 
		Frequency First Detail ID
	  */
	public void setEXME_Frequency2_ID (int EXME_Frequency2_ID)
	{
		if (EXME_Frequency2_ID < 1) 
			set_Value (COLUMNNAME_EXME_Frequency2_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_Frequency2_ID, Integer.valueOf(EXME_Frequency2_ID));
	}

	/** Get Frequency 2.
		@return Frequency First Detail ID
	  */
	public int getEXME_Frequency2_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Frequency2_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Auto Generated .
		@param IsAutoGenerated Auto Generated 	  */
	public void setIsAutoGenerated (boolean IsAutoGenerated)
	{
		set_Value (COLUMNNAME_IsAutoGenerated, Boolean.valueOf(IsAutoGenerated));
	}

	/** Get Auto Generated .
		@return Auto Generated 	  */
	public boolean isAutoGenerated () 
	{
		Object oo = get_Value(COLUMNNAME_IsAutoGenerated);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Is Template.
		@param IsTemplate Is Template	  */
	public void setIsTemplate (boolean IsTemplate)
	{
		set_Value (COLUMNNAME_IsTemplate, Boolean.valueOf(IsTemplate));
	}

	/** Get Is Template.
		@return Is Template	  */
	public boolean isTemplate () 
	{
		Object oo = get_Value(COLUMNNAME_IsTemplate);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Name.
		@param Name 
		Alphanumeric identifier of the entity
	  */
	public void setName (String Name)
	{
		if (Name == null)
			throw new IllegalArgumentException ("Name is mandatory.");
		set_Value (COLUMNNAME_Name, Name);
	}

	/** Get Name.
		@return Alphanumeric identifier of the entity
	  */
	public String getName () 
	{
		return (String)get_Value(COLUMNNAME_Name);
	}

	/** TipoArea AD_Reference_ID=1000039 */
	public static final int TIPOAREA_AD_Reference_ID=1000039;
	/** Hospitalization = H */
	public static final String TIPOAREA_Hospitalization = "H";
	/** Emergency = U */
	public static final String TIPOAREA_Emergency = "U";
	/** Ambulatory = A */
	public static final String TIPOAREA_Ambulatory = "A";
	/** Medical Consultation = C */
	public static final String TIPOAREA_MedicalConsultation = "C";
	/** Procedures = P */
	public static final String TIPOAREA_Procedures = "P";
	/** Medical Records = R */
	public static final String TIPOAREA_MedicalRecords = "R";
	/** Other = O */
	public static final String TIPOAREA_Other = "O";
	/** External = E */
	public static final String TIPOAREA_External = "E";
	/** Admission = D */
	public static final String TIPOAREA_Admission = "D";
	/** Social Work = T */
	public static final String TIPOAREA_SocialWork = "T";
	/** Social Comunication = S */
	public static final String TIPOAREA_SocialComunication = "S";
	/** Set Area Type.
		@param TipoArea 
		Admission Area Type
	  */
	public void setTipoArea (String TipoArea)
	{
		if (TipoArea == null) throw new IllegalArgumentException ("TipoArea is mandatory");
		if (TipoArea.equals("H") || TipoArea.equals("U") || TipoArea.equals("A") || TipoArea.equals("C") || TipoArea.equals("P") || TipoArea.equals("R") || TipoArea.equals("O") || TipoArea.equals("E") || TipoArea.equals("D") || TipoArea.equals("T") || TipoArea.equals("S")); else throw new IllegalArgumentException ("TipoArea Invalid value - " + TipoArea + " - Reference_ID=1000039 - H - U - A - C - P - R - O - E - D - T - S");		set_Value (COLUMNNAME_TipoArea, TipoArea);
	}

	/** Get Area Type.
		@return Admission Area Type
	  */
	public String getTipoArea () 
	{
		return (String)get_Value(COLUMNNAME_TipoArea);
	}

	/** Set Search Key.
		@param Value 
		Search key for the record in the format required - must be unique
	  */
	public void setValue (String Value)
	{
		if (Value == null)
			throw new IllegalArgumentException ("Value is mandatory.");
		set_Value (COLUMNNAME_Value, Value);
	}

	/** Get Search Key.
		@return Search key for the record in the format required - must be unique
	  */
	public String getValue () 
	{
		return (String)get_Value(COLUMNNAME_Value);
	}
}