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

/** Generated Model for R_IssueKnown
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_R_IssueKnown extends PO implements I_R_IssueKnown, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_R_IssueKnown (Properties ctx, int R_IssueKnown_ID, String trxName)
    {
      super (ctx, R_IssueKnown_ID, trxName);
      /** if (R_IssueKnown_ID == 0)
        {
			setIssueSummary (null);
			setReleaseNo (null);
			setR_IssueKnown_ID (0);
        } */
    }

    /** Load Constructor */
    public X_R_IssueKnown (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_R_IssueKnown[")
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

	/** Set Issue Status.
		@param IssueStatus 
		Current Status of the Issue
	  */
	public void setIssueStatus (String IssueStatus)
	{
		set_Value (COLUMNNAME_IssueStatus, IssueStatus);
	}

	/** Get Issue Status.
		@return Current Status of the Issue
	  */
	public String getIssueStatus () 
	{
		return (String)get_Value(COLUMNNAME_IssueStatus);
	}

	/** Set Issue Summary.
		@param IssueSummary 
		Issue Summary
	  */
	public void setIssueSummary (String IssueSummary)
	{
		if (IssueSummary == null)
			throw new IllegalArgumentException ("IssueSummary is mandatory.");
		set_Value (COLUMNNAME_IssueSummary, IssueSummary);
	}

	/** Get Issue Summary.
		@return Issue Summary
	  */
	public String getIssueSummary () 
	{
		return (String)get_Value(COLUMNNAME_IssueSummary);
	}

	/** Set Line.
		@param LineNo 
		Line No
	  */
	public void setLineNo (int LineNo)
	{
		set_Value (COLUMNNAME_LineNo, Integer.valueOf(LineNo));
	}

	/** Get Line.
		@return Line No
	  */
	public int getLineNo () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_LineNo);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Logger.
		@param LoggerName 
		Logger Name
	  */
	public void setLoggerName (String LoggerName)
	{
		set_Value (COLUMNNAME_LoggerName, LoggerName);
	}

	/** Get Logger.
		@return Logger Name
	  */
	public String getLoggerName () 
	{
		return (String)get_Value(COLUMNNAME_LoggerName);
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

	/** Set Release No.
		@param ReleaseNo 
		Internal Release Number
	  */
	public void setReleaseNo (String ReleaseNo)
	{
		if (ReleaseNo == null)
			throw new IllegalArgumentException ("ReleaseNo is mandatory.");
		set_Value (COLUMNNAME_ReleaseNo, ReleaseNo);
	}

	/** Get Release No.
		@return Internal Release Number
	  */
	public String getReleaseNo () 
	{
		return (String)get_Value(COLUMNNAME_ReleaseNo);
	}

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), getReleaseNo());
    }

	/** Set Known Issue.
		@param R_IssueKnown_ID 
		Known Issue
	  */
	public void setR_IssueKnown_ID (int R_IssueKnown_ID)
	{
		if (R_IssueKnown_ID < 1)
			 throw new IllegalArgumentException ("R_IssueKnown_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_R_IssueKnown_ID, Integer.valueOf(R_IssueKnown_ID));
	}

	/** Get Known Issue.
		@return Known Issue
	  */
	public int getR_IssueKnown_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_R_IssueKnown_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_R_IssueRecommendation getR_IssueRecommendation() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_R_IssueRecommendation.Table_Name);
        I_R_IssueRecommendation result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_R_IssueRecommendation)constructor.newInstance(new Object[] {getCtx(), new Integer(getR_IssueRecommendation_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Issue Recommendation.
		@param R_IssueRecommendation_ID 
		Recommendations how to fix an Issue
	  */
	public void setR_IssueRecommendation_ID (int R_IssueRecommendation_ID)
	{
		if (R_IssueRecommendation_ID < 1) 
			set_Value (COLUMNNAME_R_IssueRecommendation_ID, null);
		else 
			set_Value (COLUMNNAME_R_IssueRecommendation_ID, Integer.valueOf(R_IssueRecommendation_ID));
	}

	/** Get Issue Recommendation.
		@return Recommendations how to fix an Issue
	  */
	public int getR_IssueRecommendation_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_R_IssueRecommendation_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_R_IssueStatus getR_IssueStatus() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_R_IssueStatus.Table_Name);
        I_R_IssueStatus result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_R_IssueStatus)constructor.newInstance(new Object[] {getCtx(), new Integer(getR_IssueStatus_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Issue Status.
		@param R_IssueStatus_ID 
		Status of an Issue
	  */
	public void setR_IssueStatus_ID (int R_IssueStatus_ID)
	{
		if (R_IssueStatus_ID < 1) 
			set_Value (COLUMNNAME_R_IssueStatus_ID, null);
		else 
			set_Value (COLUMNNAME_R_IssueStatus_ID, Integer.valueOf(R_IssueStatus_ID));
	}

	/** Get Issue Status.
		@return Status of an Issue
	  */
	public int getR_IssueStatus_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_R_IssueStatus_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_R_Request getR_Request() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_R_Request.Table_Name);
        I_R_Request result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_R_Request)constructor.newInstance(new Object[] {getCtx(), new Integer(getR_Request_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Request.
		@param R_Request_ID 
		Request from a Business Partner or Prospect
	  */
	public void setR_Request_ID (int R_Request_ID)
	{
		if (R_Request_ID < 1) 
			set_Value (COLUMNNAME_R_Request_ID, null);
		else 
			set_Value (COLUMNNAME_R_Request_ID, Integer.valueOf(R_Request_ID));
	}

	/** Get Request.
		@return Request from a Business Partner or Prospect
	  */
	public int getR_Request_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_R_Request_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Source Class.
		@param SourceClassName 
		Source Class Name
	  */
	public void setSourceClassName (String SourceClassName)
	{
		set_Value (COLUMNNAME_SourceClassName, SourceClassName);
	}

	/** Get Source Class.
		@return Source Class Name
	  */
	public String getSourceClassName () 
	{
		return (String)get_Value(COLUMNNAME_SourceClassName);
	}

	/** Set Source Method.
		@param SourceMethodName 
		Source Method Name
	  */
	public void setSourceMethodName (String SourceMethodName)
	{
		set_Value (COLUMNNAME_SourceMethodName, SourceMethodName);
	}

	/** Get Source Method.
		@return Source Method Name
	  */
	public String getSourceMethodName () 
	{
		return (String)get_Value(COLUMNNAME_SourceMethodName);
	}
}