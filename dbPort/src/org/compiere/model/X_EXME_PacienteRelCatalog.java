/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

/** Generated Model for EXME_PacienteRelCatalog
 *  @author Generated Class 
 *  @version Version @VERSION@ - $Id$ */
public class X_EXME_PacienteRelCatalog extends PO implements I_EXME_PacienteRelCatalog, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_PacienteRelCatalog (Properties ctx, int EXME_PacienteRelCatalog_ID, String trxName)
    {
      super (ctx, EXME_PacienteRelCatalog_ID, trxName);
      /** if (EXME_PacienteRelCatalog_ID == 0)
        {
			setEXME_Paciente_ID (0);
			setEXME_PacienteRelCatalog_ID (0);
			setEXME_PacienteRel_ID (0);
			setEXME_Parentesco_ID (0);
			setIsDefault (false);
        } */
    }

    /** Load Constructor */
    public X_EXME_PacienteRelCatalog (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 9 - System 
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
      StringBuffer sb = new StringBuffer ("X_EXME_PacienteRelCatalog[")
        .append(get_ID()).append("]");
      return sb.toString();
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

	/** Set Encounter.
		@param EXME_CtaPac_ID 
		Encounter
	  */
	public void setEXME_CtaPac_ID (int EXME_CtaPac_ID)
	{
		if (EXME_CtaPac_ID < 1) 
			set_Value (COLUMNNAME_EXME_CtaPac_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_CtaPac_ID, Integer.valueOf(EXME_CtaPac_ID));
	}

	/** Get Encounter.
		@return Encounter
	  */
	public int getEXME_CtaPac_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_CtaPac_ID);
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

	/** Set Patient Relation Catalog.
		@param EXME_PacienteRelCatalog_ID Patient Relation Catalog	  */
	public void setEXME_PacienteRelCatalog_ID (int EXME_PacienteRelCatalog_ID)
	{
		if (EXME_PacienteRelCatalog_ID < 1)
			 throw new IllegalArgumentException ("EXME_PacienteRelCatalog_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_PacienteRelCatalog_ID, Integer.valueOf(EXME_PacienteRelCatalog_ID));
	}

	/** Get Patient Relation Catalog.
		@return Patient Relation Catalog	  */
	public int getEXME_PacienteRelCatalog_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_PacienteRelCatalog_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_PacienteRel getEXME_PacienteRel() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_PacienteRel.Table_Name);
        I_EXME_PacienteRel result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_PacienteRel)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_PacienteRel_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Patient Relations.
		@param EXME_PacienteRel_ID 
		Patient Relations
	  */
	public void setEXME_PacienteRel_ID (int EXME_PacienteRel_ID)
	{
		if (EXME_PacienteRel_ID < 1)
			 throw new IllegalArgumentException ("EXME_PacienteRel_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_PacienteRel_ID, Integer.valueOf(EXME_PacienteRel_ID));
	}

	/** Get Patient Relations.
		@return Patient Relations
	  */
	public int getEXME_PacienteRel_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_PacienteRel_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_Parentesco getEXME_Parentesco() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_Parentesco.Table_Name);
        I_EXME_Parentesco result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_Parentesco)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Parentesco_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Kinship.
		@param EXME_Parentesco_ID 
		Kinship
	  */
	public void setEXME_Parentesco_ID (int EXME_Parentesco_ID)
	{
		if (EXME_Parentesco_ID < 1)
			 throw new IllegalArgumentException ("EXME_Parentesco_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_Parentesco_ID, Integer.valueOf(EXME_Parentesco_ID));
	}

	/** Get Kinship.
		@return Kinship
	  */
	public int getEXME_Parentesco_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Parentesco_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Default.
		@param IsDefault 
		Default value
	  */
	public void setIsDefault (boolean IsDefault)
	{
		set_Value (COLUMNNAME_IsDefault, Boolean.valueOf(IsDefault));
	}

	/** Get Default.
		@return Default value
	  */
	public boolean isDefault () 
	{
		Object oo = get_Value(COLUMNNAME_IsDefault);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Patient.
		@param IsPatient 
		Is patient
	  */
	public void setIsPatient (boolean IsPatient)
	{
		set_Value (COLUMNNAME_IsPatient, Boolean.valueOf(IsPatient));
	}

	/** Get Patient.
		@return Is patient
	  */
	public boolean isPatient () 
	{
		Object oo = get_Value(COLUMNNAME_IsPatient);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Type AD_Reference_ID=1200476 */
	public static final int TYPE_AD_Reference_ID=1200476;
	/** Responsible = R */
	public static final String TYPE_Responsible = "R";
	/** Emergency = E */
	public static final String TYPE_Emergency = "E";
	/** Policy Holder = I */
	public static final String TYPE_PolicyHolder = "I";
	/** Responsible & Policy Holder = P */
	public static final String TYPE_ResponsiblePolicyHolder = "P";
	/** Set Type.
		@param Type 
		Type of Validation
	  */
	public void setType (String Type)
	{

		if (Type == null || Type.equals("R") || Type.equals("E") || Type.equals("I") || Type.equals("P")); else throw new IllegalArgumentException ("Type Invalid value - " + Type + " - Reference_ID=1200476 - R - E - I - P");		set_Value (COLUMNNAME_Type, Type);
	}

	/** Get Type.
		@return Type of Validation
	  */
	public String getType () 
	{
		return (String)get_Value(COLUMNNAME_Type);
	}
}