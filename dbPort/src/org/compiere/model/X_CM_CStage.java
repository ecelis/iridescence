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

/** Generated Model for CM_CStage
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_CM_CStage extends PO implements I_CM_CStage, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_CM_CStage (Properties ctx, int CM_CStage_ID, String trxName)
    {
      super (ctx, CM_CStage_ID, trxName);
      /** if (CM_CStage_ID == 0)
        {
			setCM_CStage_ID (0);
			setCM_WebProject_ID (0);
			setContainerType (null);
// D
			setIsIndexed (true);
// Y
			setIsModified (false);
			setIsSecure (false);
			setIsSummary (false);
			setMeta_Description (null);
			setMeta_Keywords (null);
			setName (null);
			setNotice (null);
			setPriority (0);
			setRelativeURL (null);
        } */
    }

    /** Load Constructor */
    public X_CM_CStage (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_CM_CStage[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Web Container Stage.
		@param CM_CStage_ID 
		Web Container Stage contains the staging content like images, text etc.
	  */
	public void setCM_CStage_ID (int CM_CStage_ID)
	{
		if (CM_CStage_ID < 1)
			 throw new IllegalArgumentException ("CM_CStage_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_CM_CStage_ID, Integer.valueOf(CM_CStage_ID));
	}

	/** Get Web Container Stage.
		@return Web Container Stage contains the staging content like images, text etc.
	  */
	public int getCM_CStage_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_CM_CStage_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Container Link.
		@param CM_CStageLink_ID 
		Stage Link to another Container in the Web Project
	  */
	public void setCM_CStageLink_ID (int CM_CStageLink_ID)
	{
		if (CM_CStageLink_ID < 1) 
			set_Value (COLUMNNAME_CM_CStageLink_ID, null);
		else 
			set_Value (COLUMNNAME_CM_CStageLink_ID, Integer.valueOf(CM_CStageLink_ID));
	}

	/** Get Container Link.
		@return Stage Link to another Container in the Web Project
	  */
	public int getCM_CStageLink_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_CM_CStageLink_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_CM_Template getCM_Template() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_CM_Template.Table_Name);
        I_CM_Template result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_CM_Template)constructor.newInstance(new Object[] {getCtx(), new Integer(getCM_Template_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Template.
		@param CM_Template_ID 
		Template defines how content is displayed
	  */
	public void setCM_Template_ID (int CM_Template_ID)
	{
		if (CM_Template_ID < 1) 
			set_Value (COLUMNNAME_CM_Template_ID, null);
		else 
			set_Value (COLUMNNAME_CM_Template_ID, Integer.valueOf(CM_Template_ID));
	}

	/** Get Template.
		@return Template defines how content is displayed
	  */
	public int getCM_Template_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_CM_Template_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_CM_WebProject getCM_WebProject() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_CM_WebProject.Table_Name);
        I_CM_WebProject result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_CM_WebProject)constructor.newInstance(new Object[] {getCtx(), new Integer(getCM_WebProject_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Web Project.
		@param CM_WebProject_ID 
		A web project is the main data container for Containers, URLs, Ads, Media etc.
	  */
	public void setCM_WebProject_ID (int CM_WebProject_ID)
	{
		if (CM_WebProject_ID < 1)
			 throw new IllegalArgumentException ("CM_WebProject_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_CM_WebProject_ID, Integer.valueOf(CM_WebProject_ID));
	}

	/** Get Web Project.
		@return A web project is the main data container for Containers, URLs, Ads, Media etc.
	  */
	public int getCM_WebProject_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_CM_WebProject_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set External Link (URL).
		@param ContainerLinkURL 
		External Link (IRL) for the Container
	  */
	public void setContainerLinkURL (String ContainerLinkURL)
	{
		set_Value (COLUMNNAME_ContainerLinkURL, ContainerLinkURL);
	}

	/** Get External Link (URL).
		@return External Link (IRL) for the Container
	  */
	public String getContainerLinkURL () 
	{
		return (String)get_Value(COLUMNNAME_ContainerLinkURL);
	}

	/** ContainerType AD_Reference_ID=385 */
	public static final int CONTAINERTYPE_AD_Reference_ID=385;
	/** Document = D */
	public static final String CONTAINERTYPE_Document = "D";
	/** Internal Link = L */
	public static final String CONTAINERTYPE_InternalLink = "L";
	/** External URL = U */
	public static final String CONTAINERTYPE_ExternalURL = "U";
	/** Set Web Container Type.
		@param ContainerType 
		Web Container Type
	  */
	public void setContainerType (String ContainerType)
	{
		if (ContainerType == null) throw new IllegalArgumentException ("ContainerType is mandatory");
		if (ContainerType.equals("D") || ContainerType.equals("L") || ContainerType.equals("U")); else throw new IllegalArgumentException ("ContainerType Invalid value - " + ContainerType + " - Reference_ID=385 - D - L - U");		set_Value (COLUMNNAME_ContainerType, ContainerType);
	}

	/** Get Web Container Type.
		@return Web Container Type
	  */
	public String getContainerType () 
	{
		return (String)get_Value(COLUMNNAME_ContainerType);
	}

	/** Set ContainerXML.
		@param ContainerXML 
		Autogenerated Containerdefinition as XML Code
	  */
	public void setContainerXML (String ContainerXML)
	{
		set_ValueNoCheck (COLUMNNAME_ContainerXML, ContainerXML);
	}

	/** Get ContainerXML.
		@return Autogenerated Containerdefinition as XML Code
	  */
	public String getContainerXML () 
	{
		return (String)get_Value(COLUMNNAME_ContainerXML);
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

	/** Set Indexed.
		@param IsIndexed 
		Index the document for the internal search engine
	  */
	public void setIsIndexed (boolean IsIndexed)
	{
		set_Value (COLUMNNAME_IsIndexed, Boolean.valueOf(IsIndexed));
	}

	/** Get Indexed.
		@return Index the document for the internal search engine
	  */
	public boolean isIndexed () 
	{
		Object oo = get_Value(COLUMNNAME_IsIndexed);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Modified.
		@param IsModified 
		The record is modified
	  */
	public void setIsModified (boolean IsModified)
	{
		set_ValueNoCheck (COLUMNNAME_IsModified, Boolean.valueOf(IsModified));
	}

	/** Get Modified.
		@return The record is modified
	  */
	public boolean isModified () 
	{
		Object oo = get_Value(COLUMNNAME_IsModified);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Secure content.
		@param IsSecure 
		Defines whether content needs to get encrypted
	  */
	public void setIsSecure (boolean IsSecure)
	{
		set_Value (COLUMNNAME_IsSecure, Boolean.valueOf(IsSecure));
	}

	/** Get Secure content.
		@return Defines whether content needs to get encrypted
	  */
	public boolean isSecure () 
	{
		Object oo = get_Value(COLUMNNAME_IsSecure);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Summary Level.
		@param IsSummary 
		This is a summary entity
	  */
	public void setIsSummary (boolean IsSummary)
	{
		set_ValueNoCheck (COLUMNNAME_IsSummary, Boolean.valueOf(IsSummary));
	}

	/** Get Summary Level.
		@return This is a summary entity
	  */
	public boolean isSummary () 
	{
		Object oo = get_Value(COLUMNNAME_IsSummary);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Valid.
		@param IsValid 
		Element is valid
	  */
	public void setIsValid (boolean IsValid)
	{
		set_Value (COLUMNNAME_IsValid, Boolean.valueOf(IsValid));
	}

	/** Get Valid.
		@return Element is valid
	  */
	public boolean isValid () 
	{
		Object oo = get_Value(COLUMNNAME_IsValid);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Meta Author.
		@param Meta_Author 
		Author of the content
	  */
	public void setMeta_Author (String Meta_Author)
	{
		set_Value (COLUMNNAME_Meta_Author, Meta_Author);
	}

	/** Get Meta Author.
		@return Author of the content
	  */
	public String getMeta_Author () 
	{
		return (String)get_Value(COLUMNNAME_Meta_Author);
	}

	/** Set Meta Content Type.
		@param Meta_Content 
		Defines the type of content i.e. "text/html; charset=UTF-8"
	  */
	public void setMeta_Content (String Meta_Content)
	{
		set_Value (COLUMNNAME_Meta_Content, Meta_Content);
	}

	/** Get Meta Content Type.
		@return Defines the type of content i.e. "text/html; charset=UTF-8"
	  */
	public String getMeta_Content () 
	{
		return (String)get_Value(COLUMNNAME_Meta_Content);
	}

	/** Set Meta Copyright.
		@param Meta_Copyright 
		Contains Copyright information for the content
	  */
	public void setMeta_Copyright (String Meta_Copyright)
	{
		set_Value (COLUMNNAME_Meta_Copyright, Meta_Copyright);
	}

	/** Get Meta Copyright.
		@return Contains Copyright information for the content
	  */
	public String getMeta_Copyright () 
	{
		return (String)get_Value(COLUMNNAME_Meta_Copyright);
	}

	/** Set Meta Description.
		@param Meta_Description 
		Meta info describing the contents of the page
	  */
	public void setMeta_Description (String Meta_Description)
	{
		if (Meta_Description == null)
			throw new IllegalArgumentException ("Meta_Description is mandatory.");
		set_Value (COLUMNNAME_Meta_Description, Meta_Description);
	}

	/** Get Meta Description.
		@return Meta info describing the contents of the page
	  */
	public String getMeta_Description () 
	{
		return (String)get_Value(COLUMNNAME_Meta_Description);
	}

	/** Set Meta Keywords.
		@param Meta_Keywords 
		Contains the keywords for the content
	  */
	public void setMeta_Keywords (String Meta_Keywords)
	{
		if (Meta_Keywords == null)
			throw new IllegalArgumentException ("Meta_Keywords is mandatory.");
		set_Value (COLUMNNAME_Meta_Keywords, Meta_Keywords);
	}

	/** Get Meta Keywords.
		@return Contains the keywords for the content
	  */
	public String getMeta_Keywords () 
	{
		return (String)get_Value(COLUMNNAME_Meta_Keywords);
	}

	/** Set Meta Language.
		@param Meta_Language 
		Language HTML Meta Tag
	  */
	public void setMeta_Language (String Meta_Language)
	{
		set_Value (COLUMNNAME_Meta_Language, Meta_Language);
	}

	/** Get Meta Language.
		@return Language HTML Meta Tag
	  */
	public String getMeta_Language () 
	{
		return (String)get_Value(COLUMNNAME_Meta_Language);
	}

	/** Set Meta Publisher.
		@param Meta_Publisher 
		Meta Publisher defines the publisher of the content
	  */
	public void setMeta_Publisher (String Meta_Publisher)
	{
		set_Value (COLUMNNAME_Meta_Publisher, Meta_Publisher);
	}

	/** Get Meta Publisher.
		@return Meta Publisher defines the publisher of the content
	  */
	public String getMeta_Publisher () 
	{
		return (String)get_Value(COLUMNNAME_Meta_Publisher);
	}

	/** Set Meta RobotsTag.
		@param Meta_RobotsTag 
		RobotsTag defines how search robots should handle this content
	  */
	public void setMeta_RobotsTag (String Meta_RobotsTag)
	{
		set_Value (COLUMNNAME_Meta_RobotsTag, Meta_RobotsTag);
	}

	/** Get Meta RobotsTag.
		@return RobotsTag defines how search robots should handle this content
	  */
	public String getMeta_RobotsTag () 
	{
		return (String)get_Value(COLUMNNAME_Meta_RobotsTag);
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

	/** Set Notice.
		@param Notice 
		Contains last write notice
	  */
	public void setNotice (String Notice)
	{
		if (Notice == null)
			throw new IllegalArgumentException ("Notice is mandatory.");
		set_Value (COLUMNNAME_Notice, Notice);
	}

	/** Get Notice.
		@return Contains last write notice
	  */
	public String getNotice () 
	{
		return (String)get_Value(COLUMNNAME_Notice);
	}

	/** Set Priority.
		@param Priority 
		Indicates if this request is of a high, medium or low priority.
	  */
	public void setPriority (int Priority)
	{
		set_Value (COLUMNNAME_Priority, Integer.valueOf(Priority));
	}

	/** Get Priority.
		@return Indicates if this request is of a high, medium or low priority.
	  */
	public int getPriority () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Priority);
		if (ii == null)
			 return 0;
		return ii.intValue();
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

	/** Set Relative URL.
		@param RelativeURL 
		Contains the relative URL for the container
	  */
	public void setRelativeURL (String RelativeURL)
	{
		if (RelativeURL == null)
			throw new IllegalArgumentException ("RelativeURL is mandatory.");
		set_Value (COLUMNNAME_RelativeURL, RelativeURL);
	}

	/** Get Relative URL.
		@return Contains the relative URL for the container
	  */
	public String getRelativeURL () 
	{
		return (String)get_Value(COLUMNNAME_RelativeURL);
	}

	/** Set Structure XML.
		@param StructureXML 
		Autogenerated Containerdefinition as XML Code
	  */
	public void setStructureXML (String StructureXML)
	{
		set_Value (COLUMNNAME_StructureXML, StructureXML);
	}

	/** Get Structure XML.
		@return Autogenerated Containerdefinition as XML Code
	  */
	public String getStructureXML () 
	{
		return (String)get_Value(COLUMNNAME_StructureXML);
	}

	/** Set Title.
		@param Title 
		Name this entity is referred to as
	  */
	public void setTitle (String Title)
	{
		set_Value (COLUMNNAME_Title, Title);
	}

	/** Get Title.
		@return Name this entity is referred to as
	  */
	public String getTitle () 
	{
		return (String)get_Value(COLUMNNAME_Title);
	}
}