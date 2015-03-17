/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;
import java.util.logging.Level;

/** Generated Model for EXME_EncounterForms
 *  @author Generated Class 
 *  @version Version @VERSION@ - $Id$ */
public class X_EXME_EncounterForms extends PO implements I_EXME_EncounterForms, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_EncounterForms (Properties ctx, int EXME_EncounterForms_ID, String trxName)
    {
      super (ctx, EXME_EncounterForms_ID, trxName);
      /** if (EXME_EncounterForms_ID == 0)
        {
			setDocAction (null);
			setDocStatus (null);
			setDocumentNo (null);
			setEXME_CtaPac_ID (0);
			setEXME_EncounterForms_ID (0);
			setIsComplete (false);
// N
			setType (null);
        } */
    }

    /** Load Constructor */
    public X_EXME_EncounterForms (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_EncounterForms[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Authenticated.
		@param Authenticated Authenticated	  */
	public void setAuthenticated (boolean Authenticated)
	{
		set_Value (COLUMNNAME_Authenticated, Boolean.valueOf(Authenticated));
	}

	/** Get Authenticated.
		@return Authenticated	  */
	public boolean isAuthenticated () 
	{
		Object oo = get_Value(COLUMNNAME_Authenticated);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Authentication Date.
		@param Authenticated_Date Authentication Date	  */
	public void setAuthenticated_Date (Timestamp Authenticated_Date)
	{
		set_Value (COLUMNNAME_Authenticated_Date, Authenticated_Date);
	}

	/** Get Authentication Date.
		@return Authentication Date	  */
	public Timestamp getAuthenticated_Date () 
	{
		return (Timestamp)get_Value(COLUMNNAME_Authenticated_Date);
	}

	/** DocAction AD_Reference_ID=135 */
	public static final int DOCACTION_AD_Reference_ID=135;
	/** Complete = CO */
	public static final String DOCACTION_Complete = "CO";
	/** Approve = AP */
	public static final String DOCACTION_Approve = "AP";
	/** Reject = RJ */
	public static final String DOCACTION_Reject = "RJ";
	/** Post = PO */
	public static final String DOCACTION_Post = "PO";
	/** Void = VO */
	public static final String DOCACTION_Void = "VO";
	/** Close = CL */
	public static final String DOCACTION_Close = "CL";
	/** Reverse - Correct = RC */
	public static final String DOCACTION_Reverse_Correct = "RC";
	/** Reverse - Accrual = RA */
	public static final String DOCACTION_Reverse_Accrual = "RA";
	/** Invalidate = IN */
	public static final String DOCACTION_Invalidate = "IN";
	/** Re-activate = RE */
	public static final String DOCACTION_Re_Activate = "RE";
	/** <None> = -- */
	public static final String DOCACTION_None = "--";
	/** Prepare = PR */
	public static final String DOCACTION_Prepare = "PR";
	/** Unlock = XL */
	public static final String DOCACTION_Unlock = "XL";
	/** Wait Complete = WC */
	public static final String DOCACTION_WaitComplete = "WC";
	/** Set Document Action.
		@param DocAction 
		The targeted status of the document
	  */
	public void setDocAction (String DocAction)
	{
		if (DocAction == null) throw new IllegalArgumentException ("DocAction is mandatory");
		if (DocAction.equals("CO") || DocAction.equals("AP") || DocAction.equals("RJ") || DocAction.equals("PO") || DocAction.equals("VO") || DocAction.equals("CL") || DocAction.equals("RC") || DocAction.equals("RA") || DocAction.equals("IN") || DocAction.equals("RE") || DocAction.equals("--") || DocAction.equals("PR") || DocAction.equals("XL") || DocAction.equals("WC")); else throw new IllegalArgumentException ("DocAction Invalid value - " + DocAction + " - Reference_ID=135 - CO - AP - RJ - PO - VO - CL - RC - RA - IN - RE - -- - PR - XL - WC");		set_Value (COLUMNNAME_DocAction, DocAction);
	}

	/** Get Document Action.
		@return The targeted status of the document
	  */
	public String getDocAction () 
	{
		return (String)get_Value(COLUMNNAME_DocAction);
	}

	/** DocStatus AD_Reference_ID=131 */
	public static final int DOCSTATUS_AD_Reference_ID=131;
	/** Drafted = DR */
	public static final String DOCSTATUS_Drafted = "DR";
	/** Completed = CO */
	public static final String DOCSTATUS_Completed = "CO";
	/** Approved = AP */
	public static final String DOCSTATUS_Approved = "AP";
	/** Not Approved = NA */
	public static final String DOCSTATUS_NotApproved = "NA";
	/** Voided = VO */
	public static final String DOCSTATUS_Voided = "VO";
	/** Invalid = IN */
	public static final String DOCSTATUS_Invalid = "IN";
	/** Reversed = RE */
	public static final String DOCSTATUS_Reversed = "RE";
	/** Closed = CL */
	public static final String DOCSTATUS_Closed = "CL";
	/** Unknown = ?? */
	public static final String DOCSTATUS_Unknown = "??";
	/** In Progress = IP */
	public static final String DOCSTATUS_InProgress = "IP";
	/** Waiting Payment = WP */
	public static final String DOCSTATUS_WaitingPayment = "WP";
	/** Waiting Confirmation = WC */
	public static final String DOCSTATUS_WaitingConfirmation = "WC";
	/** Set Document Status.
		@param DocStatus 
		The current status of the document
	  */
	public void setDocStatus (String DocStatus)
	{
		if (DocStatus == null) throw new IllegalArgumentException ("DocStatus is mandatory");
		if (DocStatus.equals("DR") || DocStatus.equals("CO") || DocStatus.equals("AP") || DocStatus.equals("NA") || DocStatus.equals("VO") || DocStatus.equals("IN") || DocStatus.equals("RE") || DocStatus.equals("CL") || DocStatus.equals("??") || DocStatus.equals("IP") || DocStatus.equals("WP") || DocStatus.equals("WC")); else throw new IllegalArgumentException ("DocStatus Invalid value - " + DocStatus + " - Reference_ID=131 - DR - CO - AP - NA - VO - IN - RE - CL - ?? - IP - WP - WC");		set_Value (COLUMNNAME_DocStatus, DocStatus);
	}

	/** Get Document Status.
		@return The current status of the document
	  */
	public String getDocStatus () 
	{
		return (String)get_Value(COLUMNNAME_DocStatus);
	}

	/** Set Document No.
		@param DocumentNo 
		Document sequence number of the document
	  */
	public void setDocumentNo (String DocumentNo)
	{
		if (DocumentNo == null)
			throw new IllegalArgumentException ("DocumentNo is mandatory.");
		set_Value (COLUMNNAME_DocumentNo, DocumentNo);
	}

	/** Get Document No.
		@return Document sequence number of the document
	  */
	public String getDocumentNo () 
	{
		return (String)get_Value(COLUMNNAME_DocumentNo);
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
			 throw new IllegalArgumentException ("EXME_CtaPac_ID is mandatory.");
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

	/** Set Encounter Forms.
		@param EXME_EncounterForms_ID 
		Encounter Forms
	  */
	public void setEXME_EncounterForms_ID (int EXME_EncounterForms_ID)
	{
		if (EXME_EncounterForms_ID < 1)
			 throw new IllegalArgumentException ("EXME_EncounterForms_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_EncounterForms_ID, Integer.valueOf(EXME_EncounterForms_ID));
	}

	/** Get Encounter Forms.
		@return Encounter Forms
	  */
	public int getEXME_EncounterForms_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_EncounterForms_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_FormSectionHeader getEXME_FormSectionHeader() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_FormSectionHeader.Table_Name);
        I_EXME_FormSectionHeader result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_FormSectionHeader)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_FormSectionHeader_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Form Section's Header.
		@param EXME_FormSectionHeader_ID Form Section's Header	  */
	public void setEXME_FormSectionHeader_ID (int EXME_FormSectionHeader_ID)
	{
		if (EXME_FormSectionHeader_ID < 1) 
			set_Value (COLUMNNAME_EXME_FormSectionHeader_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_FormSectionHeader_ID, Integer.valueOf(EXME_FormSectionHeader_ID));
	}

	/** Get Form Section's Header.
		@return Form Section's Header	  */
	public int getEXME_FormSectionHeader_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_FormSectionHeader_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Complete.
		@param IsComplete 
		It is complete
	  */
	public void setIsComplete (boolean IsComplete)
	{
		set_ValueNoCheck (COLUMNNAME_IsComplete, Boolean.valueOf(IsComplete));
	}

	/** Get Complete.
		@return It is complete
	  */
	public boolean isComplete () 
	{
		Object oo = get_Value(COLUMNNAME_IsComplete);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Noted By.
		@param NotedBy Noted By	  */
	public void setNotedBy (int NotedBy)
	{
		set_Value (COLUMNNAME_NotedBy, Integer.valueOf(NotedBy));
	}

	/** Get Noted By.
		@return Noted By	  */
	public int getNotedBy () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_NotedBy);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Noted Date.
		@param NotedDate Noted Date	  */
	public void setNotedDate (Timestamp NotedDate)
	{
		set_Value (COLUMNNAME_NotedDate, NotedDate);
	}

	/** Get Noted Date.
		@return Noted Date	  */
	public Timestamp getNotedDate () 
	{
		return (Timestamp)get_Value(COLUMNNAME_NotedDate);
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

	/** Set Responsible User.
		@param respAD_User_id Responsible User	  */
	public void setrespAD_User_id (int respAD_User_id)
	{
		set_Value (COLUMNNAME_respAD_User_id, Integer.valueOf(respAD_User_id));
	}

	/** Get Responsible User.
		@return Responsible User	  */
	public int getrespAD_User_id () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_respAD_User_id);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Type AD_Reference_ID=1200547 */
	public static final int TYPE_AD_Reference_ID=1200547;
	/** Admitting = A */
	public static final String TYPE_Admitting = "A";
	/** Discharge = D */
	public static final String TYPE_Discharge = "D";
	/** PreDischarge = P */
	public static final String TYPE_PreDischarge = "P";
	/** Other = O */
	public static final String TYPE_Other = "O";
	/** Set Type.
		@param Type 
		Type of Validation
	  */
	public void setType (String Type)
	{
		if (Type == null) throw new IllegalArgumentException ("Type is mandatory");
		if (Type.equals("A") || Type.equals("D") || Type.equals("P") || Type.equals("O")); else throw new IllegalArgumentException ("Type Invalid value - " + Type + " - Reference_ID=1200547 - A - D - P - O");		set_Value (COLUMNNAME_Type, Type);
	}

	/** Get Type.
		@return Type of Validation
	  */
	public String getType () 
	{
		return (String)get_Value(COLUMNNAME_Type);
	}
}