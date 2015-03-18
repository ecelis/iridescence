/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.util.KeyNamePair;

/** Generated Model for AD_Rule
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_AD_Rule extends PO implements I_AD_Rule, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_AD_Rule (Properties ctx, int AD_Rule_ID, String trxName)
    {
      super (ctx, AD_Rule_ID, trxName);
      /** if (AD_Rule_ID == 0)
        {
			setAD_Rule_ID (0);
			setEntityType (null);
// U
			setEventType (null);
			setName (null);
			setRuleType (null);
			setValue (null);
        } */
    }

    /** Load Constructor */
    public X_AD_Rule (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_AD_Rule[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** AccessLevel AD_Reference_ID=5 */
	public static final int ACCESSLEVEL_AD_Reference_ID=5;
	/** Organization = 1 */
	public static final String ACCESSLEVEL_Organization = "1";
	/** Client+Organization = 3 */
	public static final String ACCESSLEVEL_ClientPlusOrganization = "3";
	/** System only = 4 */
	public static final String ACCESSLEVEL_SystemOnly = "4";
	/** All = 7 */
	public static final String ACCESSLEVEL_All = "7";
	/** System+Client = 6 */
	public static final String ACCESSLEVEL_SystemPlusClient = "6";
	/** Client only = 2 */
	public static final String ACCESSLEVEL_ClientOnly = "2";
	/** Client to System = 9 */
	public static final String ACCESSLEVEL_ClientToSystem = "9";
	/** Set Data Access Level.
		@param AccessLevel 
		Access Level required
	  */
	public void setAccessLevel (String AccessLevel)
	{

		if (AccessLevel == null || AccessLevel.equals("1") || AccessLevel.equals("3") || AccessLevel.equals("4") || AccessLevel.equals("7") || AccessLevel.equals("6") || AccessLevel.equals("2") || AccessLevel.equals("9")); else throw new IllegalArgumentException ("AccessLevel Invalid value - " + AccessLevel + " - Reference_ID=5 - 1 - 3 - 4 - 7 - 6 - 2 - 9");		set_Value (COLUMNNAME_AccessLevel, AccessLevel);
	}

	/** Get Data Access Level.
		@return Access Level required
	  */
	public String getAccessLevel () 
	{
		return (String)get_Value(COLUMNNAME_AccessLevel);
	}

	/** Set Rule.
		@param AD_Rule_ID Rule	  */
	public void setAD_Rule_ID (int AD_Rule_ID)
	{
		if (AD_Rule_ID < 1)
			 throw new IllegalArgumentException ("AD_Rule_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_AD_Rule_ID, Integer.valueOf(AD_Rule_ID));
	}

	/** Get Rule.
		@return Rule	  */
	public int getAD_Rule_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_Rule_ID);
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

	/** EventType AD_Reference_ID=1200259 */
	public static final int EVENTTYPE_AD_Reference_ID=1200259;
	/** Callout = C */
	public static final String EVENTTYPE_Callout = "C";
	/** Process = P */
	public static final String EVENTTYPE_Process = "P";
	/** Model Validator Table Event = T */
	public static final String EVENTTYPE_ModelValidatorTableEvent = "T";
	/** Model Validator Document Event = D */
	public static final String EVENTTYPE_ModelValidatorDocumentEvent = "D";
	/** Model Validator Login Event = L */
	public static final String EVENTTYPE_ModelValidatorLoginEvent = "L";
	/** Set Event Type.
		@param EventType 
		Type of Event
	  */
	public void setEventType (String EventType)
	{
		if (EventType == null) throw new IllegalArgumentException ("EventType is mandatory");
		if (EventType.equals("C") || EventType.equals("P") || EventType.equals("T") || EventType.equals("D") || EventType.equals("L")); else throw new IllegalArgumentException ("EventType Invalid value - " + EventType + " - Reference_ID=1200259 - C - P - T - D - L");		set_Value (COLUMNNAME_EventType, EventType);
	}

	/** Get Event Type.
		@return Type of Event
	  */
	public String getEventType () 
	{
		return (String)get_Value(COLUMNNAME_EventType);
	}

	/** Set Comment/Help.
		@param Help 
		Comment or Hint
	  */
	public void setHelp (String Help)
	{
		set_Value (COLUMNNAME_Help, Help);
	}

	/** Get Comment/Help.
		@return Comment or Hint
	  */
	public String getHelp () 
	{
		return (String)get_Value(COLUMNNAME_Help);
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

	/** RuleType AD_Reference_ID=1200258 */
	public static final int RULETYPE_AD_Reference_ID=1200258;
	/** Aspect Orient Program = A */
	public static final String RULETYPE_AspectOrientProgram = "A";
	/** JSR 223 Scripting APIs = S */
	public static final String RULETYPE_JSR223ScriptingAPIs = "S";
	/** JSR 94 Rule Engine API = R */
	public static final String RULETYPE_JSR94RuleEngineAPI = "R";
	/** SQL = Q */
	public static final String RULETYPE_SQL = "Q";
	/** Set Rule Type.
		@param RuleType Rule Type	  */
	public void setRuleType (String RuleType)
	{
		if (RuleType == null) throw new IllegalArgumentException ("RuleType is mandatory");
		if (RuleType.equals("A") || RuleType.equals("S") || RuleType.equals("R") || RuleType.equals("Q")); else throw new IllegalArgumentException ("RuleType Invalid value - " + RuleType + " - Reference_ID=1200258 - A - S - R - Q");		set_Value (COLUMNNAME_RuleType, RuleType);
	}

	/** Get Rule Type.
		@return Rule Type	  */
	public String getRuleType () 
	{
		return (String)get_Value(COLUMNNAME_RuleType);
	}

	/** Set Script.
		@param Script 
		Dynamic Java Language Script to calculate result
	  */
	public void setScript (String Script)
	{
		set_Value (COLUMNNAME_Script, Script);
	}

	/** Get Script.
		@return Dynamic Java Language Script to calculate result
	  */
	public String getScript () 
	{
		return (String)get_Value(COLUMNNAME_Script);
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