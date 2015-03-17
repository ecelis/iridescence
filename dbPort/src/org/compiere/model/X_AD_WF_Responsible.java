/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;
import org.compiere.util.KeyNamePair;

/** Generated Model for AD_WF_Responsible
 *  @author Generated Class 
 *  @version Version @VERSION@ - $Id$ */
public class X_AD_WF_Responsible extends PO implements I_AD_WF_Responsible, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_AD_WF_Responsible (Properties ctx, int AD_WF_Responsible_ID, String trxName)
    {
      super (ctx, AD_WF_Responsible_ID, trxName);
      /** if (AD_WF_Responsible_ID == 0)
        {
			setAD_Role_ID (0);
			setAD_WF_Responsible_ID (0);
			setEntityType (null);
// U
			setName (null);
			setResponsibleType (null);
        } */
    }

    /** Load Constructor */
    public X_AD_WF_Responsible (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 6 - System - Client 
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
      StringBuffer sb = new StringBuffer ("X_AD_WF_Responsible[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public I_AD_Role getAD_Role() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_AD_Role.Table_Name);
        I_AD_Role result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_AD_Role)constructor.newInstance(new Object[] {getCtx(), new Integer(getAD_Role_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Role.
		@param AD_Role_ID 
		Responsibility Role
	  */
	public void setAD_Role_ID (int AD_Role_ID)
	{
		if (AD_Role_ID < 0)
			 throw new IllegalArgumentException ("AD_Role_ID is mandatory.");
		set_Value (COLUMNNAME_AD_Role_ID, Integer.valueOf(AD_Role_ID));
	}

	/** Get Role.
		@return Responsibility Role
	  */
	public int getAD_Role_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_Role_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set User/Contact .
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

	/** Get User/Contact .
		@return User within the system - Internal or Business Partner Contact
	  */
	public int getAD_User_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_User_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Workflow Responsible.
		@param AD_WF_Responsible_ID 
		Responsible for Workflow Execution
	  */
	public void setAD_WF_Responsible_ID (int AD_WF_Responsible_ID)
	{
		if (AD_WF_Responsible_ID < 1)
			 throw new IllegalArgumentException ("AD_WF_Responsible_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_AD_WF_Responsible_ID, Integer.valueOf(AD_WF_Responsible_ID));
	}

	/** Get Workflow Responsible.
		@return Responsible for Workflow Execution
	  */
	public int getAD_WF_Responsible_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_WF_Responsible_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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

	/** EntityType AD_Reference_ID=389 */
	public static final int ENTITYTYPE_AD_Reference_ID=389;
	/** Set Entity Type.
		@param EntityType 
		Dictionary Entity Type; Determines ownership and synchronization
	  */
	public void setEntityType (String EntityType)
	{
		set_Value (COLUMNNAME_EntityType, EntityType);
	}

	/** Get Entity Type.
		@return Dictionary Entity Type; Determines ownership and synchronization
	  */
	public String getEntityType () 
	{
		return (String)get_Value(COLUMNNAME_EntityType);
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

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), getName());
    }

	/** ResponsibleType AD_Reference_ID=304 */
	public static final int RESPONSIBLETYPE_AD_Reference_ID=304;
	/** Organization = O */
	public static final String RESPONSIBLETYPE_Organization = "O";
	/** Human = H */
	public static final String RESPONSIBLETYPE_Human = "H";
	/** Role = R */
	public static final String RESPONSIBLETYPE_Role = "R";
	/** System Resource = S */
	public static final String RESPONSIBLETYPE_SystemResource = "S";
	/** Role Type = T */
	public static final String RESPONSIBLETYPE_RoleType = "T";
	/** Set Responsible Type.
		@param ResponsibleType 
		Type of the Responsibility for a workflow
	  */
	public void setResponsibleType (String ResponsibleType)
	{
		if (ResponsibleType == null) throw new IllegalArgumentException ("ResponsibleType is mandatory");
		if (ResponsibleType.equals("O") || ResponsibleType.equals("H") || ResponsibleType.equals("R") || ResponsibleType.equals("S") || ResponsibleType.equals("T")); else throw new IllegalArgumentException ("ResponsibleType Invalid value - " + ResponsibleType + " - Reference_ID=304 - O - H - R - S - T");		set_Value (COLUMNNAME_ResponsibleType, ResponsibleType);
	}

	/** Get Responsible Type.
		@return Type of the Responsibility for a workflow
	  */
	public String getResponsibleType () 
	{
		return (String)get_Value(COLUMNNAME_ResponsibleType);
	}

	/** UserType AD_Reference_ID=1200551 */
	public static final int USERTYPE_AD_Reference_ID=1200551;
	/** User = US */
	public static final String USERTYPE_User = "US";
	/** Doctor = MD */
	public static final String USERTYPE_Doctor = "MD";
	/** Nurse = NU */
	public static final String USERTYPE_Nurse = "NU";
	/** Coder = CO */
	public static final String USERTYPE_Coder = "CO";
	/** Pharmacist = PH */
	public static final String USERTYPE_Pharmacist = "PH";
	/** Billing = BI */
	public static final String USERTYPE_Billing = "BI";
	/** Patient = PA */
	public static final String USERTYPE_Patient = "PA";
	/** Assistant = AS */
	public static final String USERTYPE_Assistant = "AS";
	/** Admission = AD */
	public static final String USERTYPE_Admission = "AD";
	/** Technical = TE */
	public static final String USERTYPE_Technical = "TE";
	/** Set Profile type.
		@param UserType 
		Profile type
	  */
	public void setUserType (String UserType)
	{

		if (UserType == null || UserType.equals("US") || UserType.equals("MD") || UserType.equals("NU") || UserType.equals("CO") || UserType.equals("PH") || UserType.equals("BI") || UserType.equals("PA") || UserType.equals("AS") || UserType.equals("AD") || UserType.equals("TE")); else throw new IllegalArgumentException ("UserType Invalid value - " + UserType + " - Reference_ID=1200551 - US - MD - NU - CO - PH - BI - PA - AS - AD - TE");		set_Value (COLUMNNAME_UserType, UserType);
	}

	/** Get Profile type.
		@return Profile type
	  */
	public String getUserType () 
	{
		return (String)get_Value(COLUMNNAME_UserType);
	}
}