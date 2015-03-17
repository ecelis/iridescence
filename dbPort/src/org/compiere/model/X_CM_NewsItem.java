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

/** Generated Model for CM_NewsItem
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_CM_NewsItem extends PO implements I_CM_NewsItem, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_CM_NewsItem (Properties ctx, int CM_NewsItem_ID, String trxName)
    {
      super (ctx, CM_NewsItem_ID, trxName);
      /** if (CM_NewsItem_ID == 0)
        {
			setCM_NewsChannel_ID (0);
			setCM_NewsItem_ID (0);
        } */
    }

    /** Load Constructor */
    public X_CM_NewsItem (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_CM_NewsItem[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Author.
		@param Author 
		Author/Creator of the Entity
	  */
	public void setAuthor (String Author)
	{
		set_Value (COLUMNNAME_Author, Author);
	}

	/** Get Author.
		@return Author/Creator of the Entity
	  */
	public String getAuthor () 
	{
		return (String)get_Value(COLUMNNAME_Author);
	}

	public I_CM_NewsChannel getCM_NewsChannel() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_CM_NewsChannel.Table_Name);
        I_CM_NewsChannel result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_CM_NewsChannel)constructor.newInstance(new Object[] {getCtx(), new Integer(getCM_NewsChannel_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set News Channel.
		@param CM_NewsChannel_ID 
		News channel for rss feed
	  */
	public void setCM_NewsChannel_ID (int CM_NewsChannel_ID)
	{
		if (CM_NewsChannel_ID < 1)
			 throw new IllegalArgumentException ("CM_NewsChannel_ID is mandatory.");
		set_Value (COLUMNNAME_CM_NewsChannel_ID, Integer.valueOf(CM_NewsChannel_ID));
	}

	/** Get News Channel.
		@return News channel for rss feed
	  */
	public int getCM_NewsChannel_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_CM_NewsChannel_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set News Item / Article.
		@param CM_NewsItem_ID 
		News item or article defines base content
	  */
	public void setCM_NewsItem_ID (int CM_NewsItem_ID)
	{
		if (CM_NewsItem_ID < 1)
			 throw new IllegalArgumentException ("CM_NewsItem_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_CM_NewsItem_ID, Integer.valueOf(CM_NewsItem_ID));
	}

	/** Get News Item / Article.
		@return News item or article defines base content
	  */
	public int getCM_NewsItem_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_CM_NewsItem_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Content HTML.
		@param ContentHTML 
		Contains the content itself
	  */
	public void setContentHTML (String ContentHTML)
	{
		set_Value (COLUMNNAME_ContentHTML, ContentHTML);
	}

	/** Get Content HTML.
		@return Contains the content itself
	  */
	public String getContentHTML () 
	{
		return (String)get_Value(COLUMNNAME_ContentHTML);
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

	/** Set Link URL.
		@param LinkURL 
		Contains URL to a target
	  */
	public void setLinkURL (String LinkURL)
	{
		set_Value (COLUMNNAME_LinkURL, LinkURL);
	}

	/** Get Link URL.
		@return Contains URL to a target
	  */
	public String getLinkURL () 
	{
		return (String)get_Value(COLUMNNAME_LinkURL);
	}

	/** Set Publication Date.
		@param PubDate 
		Date on which this article will / should get published
	  */
	public void setPubDate (Timestamp PubDate)
	{
		set_Value (COLUMNNAME_PubDate, PubDate);
	}

	/** Get Publication Date.
		@return Date on which this article will / should get published
	  */
	public Timestamp getPubDate () 
	{
		return (Timestamp)get_Value(COLUMNNAME_PubDate);
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