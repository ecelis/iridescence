/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;
import org.compiere.util.Env;

/** Generated Model for M_PromotionDistribution
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_M_PromotionDistribution extends PO implements I_M_PromotionDistribution, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_M_PromotionDistribution (Properties ctx, int M_PromotionDistribution_ID, String trxName)
    {
      super (ctx, M_PromotionDistribution_ID, trxName);
      /** if (M_PromotionDistribution_ID == 0)
        {
			setDistributionType (null);
			setM_PromotionDistribution_ID (0);
			setM_Promotion_ID (0);
			setM_PromotionLine_ID (0);
			setOperation (null);
			setQty (Env.ZERO);
// 0
			setSeqNo (0);
// @SQL=SELECT COALESCE(MAX(SeqNo),0)+10 AS DefaultValue FROM M_PromotionDistribution WHERE M_Promotion_ID=@M_Promotion_ID@
        } */
    }

    /** Load Constructor */
    public X_M_PromotionDistribution (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_M_PromotionDistribution[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** DistributionSorting AD_Reference_ID=1200341 */
	public static final int DISTRIBUTIONSORTING_AD_Reference_ID=1200341;
	/** Ascending = A */
	public static final String DISTRIBUTIONSORTING_Ascending = "A";
	/** Descending = D */
	public static final String DISTRIBUTIONSORTING_Descending = "D";
	/** Set Distribution Sorting.
		@param DistributionSorting 
		Quantity distribution sorting by unit price
	  */
	public void setDistributionSorting (String DistributionSorting)
	{

		if (DistributionSorting == null || DistributionSorting.equals("A") || DistributionSorting.equals("D")); else throw new IllegalArgumentException ("DistributionSorting Invalid value - " + DistributionSorting + " - Reference_ID=1200341 - A - D");		set_Value (COLUMNNAME_DistributionSorting, DistributionSorting);
	}

	/** Get Distribution Sorting.
		@return Quantity distribution sorting by unit price
	  */
	public String getDistributionSorting () 
	{
		return (String)get_Value(COLUMNNAME_DistributionSorting);
	}

	/** DistributionType AD_Reference_ID=1200340 */
	public static final int DISTRIBUTIONTYPE_AD_Reference_ID=1200340;
	/** Min = I */
	public static final String DISTRIBUTIONTYPE_Min = "I";
	/** Max = X */
	public static final String DISTRIBUTIONTYPE_Max = "X";
	/** Minus = N */
	public static final String DISTRIBUTIONTYPE_Minus = "N";
	/** Set Distribution Type.
		@param DistributionType 
		Type of quantity distribution calculation using comparison qty and order qty as operand
	  */
	public void setDistributionType (String DistributionType)
	{
		if (DistributionType == null) throw new IllegalArgumentException ("DistributionType is mandatory");
		if (DistributionType.equals("I") || DistributionType.equals("X") || DistributionType.equals("N")); else throw new IllegalArgumentException ("DistributionType Invalid value - " + DistributionType + " - Reference_ID=1200340 - I - X - N");		set_Value (COLUMNNAME_DistributionType, DistributionType);
	}

	/** Get Distribution Type.
		@return Type of quantity distribution calculation using comparison qty and order qty as operand
	  */
	public String getDistributionType () 
	{
		return (String)get_Value(COLUMNNAME_DistributionType);
	}

	/** Set Promotion Distribution.
		@param M_PromotionDistribution_ID Promotion Distribution	  */
	public void setM_PromotionDistribution_ID (int M_PromotionDistribution_ID)
	{
		if (M_PromotionDistribution_ID < 1)
			 throw new IllegalArgumentException ("M_PromotionDistribution_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_M_PromotionDistribution_ID, Integer.valueOf(M_PromotionDistribution_ID));
	}

	/** Get Promotion Distribution.
		@return Promotion Distribution	  */
	public int getM_PromotionDistribution_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_PromotionDistribution_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_M_Promotion getM_Promotion() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_M_Promotion.Table_Name);
        I_M_Promotion result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_M_Promotion)constructor.newInstance(new Object[] {getCtx(), new Integer(getM_Promotion_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Promotion.
		@param M_Promotion_ID Promotion	  */
	public void setM_Promotion_ID (int M_Promotion_ID)
	{
		if (M_Promotion_ID < 1)
			 throw new IllegalArgumentException ("M_Promotion_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_M_Promotion_ID, Integer.valueOf(M_Promotion_ID));
	}

	/** Get Promotion.
		@return Promotion	  */
	public int getM_Promotion_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_Promotion_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_M_PromotionLine getM_PromotionLine() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_M_PromotionLine.Table_Name);
        I_M_PromotionLine result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_M_PromotionLine)constructor.newInstance(new Object[] {getCtx(), new Integer(getM_PromotionLine_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Promotion Line.
		@param M_PromotionLine_ID Promotion Line	  */
	public void setM_PromotionLine_ID (int M_PromotionLine_ID)
	{
		if (M_PromotionLine_ID < 1)
			 throw new IllegalArgumentException ("M_PromotionLine_ID is mandatory.");
		set_Value (COLUMNNAME_M_PromotionLine_ID, Integer.valueOf(M_PromotionLine_ID));
	}

	/** Get Promotion Line.
		@return Promotion Line	  */
	public int getM_PromotionLine_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_PromotionLine_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Operation AD_Reference_ID=1200339 */
	public static final int OPERATION_AD_Reference_ID=1200339;
	/** >= = >= */
	public static final String OPERATION_GtEq = ">=";
	/** <= = <= */
	public static final String OPERATION_LeEq = "<=";
	/** Set Operation.
		@param Operation 
		Compare Operation
	  */
	public void setOperation (String Operation)
	{
		if (Operation == null) throw new IllegalArgumentException ("Operation is mandatory");
		if (Operation.equals(">=") || Operation.equals("<=")); else throw new IllegalArgumentException ("Operation Invalid value - " + Operation + " - Reference_ID=1200339 - >= - <=");		set_Value (COLUMNNAME_Operation, Operation);
	}

	/** Get Operation.
		@return Compare Operation
	  */
	public String getOperation () 
	{
		return (String)get_Value(COLUMNNAME_Operation);
	}

	/** Set Quantity.
		@param Qty 
		Quantity
	  */
	public void setQty (BigDecimal Qty)
	{
		if (Qty == null)
			throw new IllegalArgumentException ("Qty is mandatory.");
		set_Value (COLUMNNAME_Qty, Qty);
	}

	/** Get Quantity.
		@return Quantity
	  */
	public BigDecimal getQty () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Qty);
		if (bd == null)
			 return Env.ZERO;
		return bd;
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