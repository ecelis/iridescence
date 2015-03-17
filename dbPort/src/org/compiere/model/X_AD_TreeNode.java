/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

/** Generated Model for AD_TreeNode
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_AD_TreeNode extends PO implements I_AD_TreeNode, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_AD_TreeNode (Properties ctx, int AD_TreeNode_ID, String trxName)
    {
      super (ctx, AD_TreeNode_ID, trxName);
      /** if (AD_TreeNode_ID == 0)
        {
			setAD_Tree_ID (0);
			setNode_ID (0);
			setSeqNo (0);
        } */
    }

    /** Load Constructor */
    public X_AD_TreeNode (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 7 - System - Client - Org 
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
      StringBuffer sb = new StringBuffer ("X_AD_TreeNode[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public I_AD_Tree getAD_Tree() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_AD_Tree.Table_Name);
        I_AD_Tree result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_AD_Tree)constructor.newInstance(new Object[] {getCtx(), new Integer(getAD_Tree_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Tree.
		@param AD_Tree_ID 
		Identifies a Tree
	  */
	public void setAD_Tree_ID (int AD_Tree_ID)
	{
		if (AD_Tree_ID < 1)
			 throw new IllegalArgumentException ("AD_Tree_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_AD_Tree_ID, Integer.valueOf(AD_Tree_ID));
	}

	/** Get Tree.
		@return Identifies a Tree
	  */
	public int getAD_Tree_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_Tree_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Node ID.
		@param Node_ID Node ID	  */
	public void setNode_ID (int Node_ID)
	{
		if (Node_ID < 0)
			 throw new IllegalArgumentException ("Node_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_Node_ID, Integer.valueOf(Node_ID));
	}

	/** Get Node ID.
		@return Node ID	  */
	public int getNode_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Node_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Parent.
		@param Parent_ID 
		Parent of Entity
	  */
	public void setParent_ID (int Parent_ID)
	{
		if (Parent_ID < 1) 
			set_Value (COLUMNNAME_Parent_ID, null);
		else 
			set_Value (COLUMNNAME_Parent_ID, Integer.valueOf(Parent_ID));
	}

	/** Get Parent.
		@return Parent of Entity
	  */
	public int getParent_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Parent_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Sequence Number.
		@param SeqNo 
		Method of ordering records; lowest number comes first
	  */
	public void setSeqNo (int SeqNo)
	{
		set_Value (COLUMNNAME_SeqNo, Integer.valueOf(SeqNo));
	}

	/** Get Sequence Number.
		@return Method of ordering records; lowest number comes first
	  */
	public int getSeqNo () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_SeqNo);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}