/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

/** Generated Model for EXME_TaxCategorySeq
 *  @author Generated Class 
 *  @version Version @VERSION@ - $Id$ */
public class X_EXME_TaxCategorySeq extends PO implements I_EXME_TaxCategorySeq, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_TaxCategorySeq (Properties ctx, int EXME_TaxCategorySeq_ID, String trxName)
    {
      super (ctx, EXME_TaxCategorySeq_ID, trxName);
      /** if (EXME_TaxCategorySeq_ID == 0)
        {
			setC_TaxCategory_ID (0);
			setC_Tax_ID (0);
			setEXME_TaxCategorySeq_ID (0);
			setSequence (0);
			setSOPOType (null);
// B
        } */
    }

    /** Load Constructor */
    public X_EXME_TaxCategorySeq (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 2 - Client 
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
      StringBuffer sb = new StringBuffer ("X_EXME_TaxCategorySeq[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Base_Calculation.
		@param Base_Calculation Base_Calculation	  */
	public void setBase_Calculation (int Base_Calculation)
	{
		set_Value (COLUMNNAME_Base_Calculation, Integer.valueOf(Base_Calculation));
	}

	/** Get Base_Calculation.
		@return Base_Calculation	  */
	public int getBase_Calculation () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Base_Calculation);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Buy_Sell AD_Reference_ID=1200683 */
	public static final int BUY_SELL_AD_Reference_ID=1200683;
	/** A=All = A */
	public static final String BUY_SELL_AEqAll = "A";
	/** B=Buy = B */
	public static final String BUY_SELL_BEqBuy = "B";
	/** S=Sell = S */
	public static final String BUY_SELL_SEqSell = "S";
	/** Set Buy_Sell.
		@param Buy_Sell Buy_Sell	  */
	public void setBuy_Sell (String Buy_Sell)
	{

		if (Buy_Sell == null || Buy_Sell.equals("A") || Buy_Sell.equals("B") || Buy_Sell.equals("S")); else throw new IllegalArgumentException ("Buy_Sell Invalid value - " + Buy_Sell + " - Reference_ID=1200683 - A - B - S");		set_Value (COLUMNNAME_Buy_Sell, Buy_Sell);
	}

	/** Get Buy_Sell.
		@return Buy_Sell	  */
	public String getBuy_Sell () 
	{
		return (String)get_Value(COLUMNNAME_Buy_Sell);
	}

	public I_C_TaxCategory getC_TaxCategory() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_C_TaxCategory.Table_Name);
        I_C_TaxCategory result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_C_TaxCategory)constructor.newInstance(new Object[] {getCtx(), new Integer(getC_TaxCategory_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Tax Category.
		@param C_TaxCategory_ID 
		Tax Category
	  */
	public void setC_TaxCategory_ID (int C_TaxCategory_ID)
	{
		if (C_TaxCategory_ID < 1)
			 throw new IllegalArgumentException ("C_TaxCategory_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_C_TaxCategory_ID, Integer.valueOf(C_TaxCategory_ID));
	}

	/** Get Tax Category.
		@return Tax Category
	  */
	public int getC_TaxCategory_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_TaxCategory_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_C_Tax getC_Tax() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_C_Tax.Table_Name);
        I_C_Tax result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_C_Tax)constructor.newInstance(new Object[] {getCtx(), new Integer(getC_Tax_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Tax.
		@param C_Tax_ID 
		Tax identifier
	  */
	public void setC_Tax_ID (int C_Tax_ID)
	{
		if (C_Tax_ID < 1)
			 throw new IllegalArgumentException ("C_Tax_ID is mandatory.");
		set_Value (COLUMNNAME_C_Tax_ID, Integer.valueOf(C_Tax_ID));
	}

	/** Get Tax.
		@return Tax identifier
	  */
	public int getC_Tax_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Tax_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Tax sequence.
		@param EXME_TaxCategorySeq_ID Tax sequence	  */
	public void setEXME_TaxCategorySeq_ID (int EXME_TaxCategorySeq_ID)
	{
		if (EXME_TaxCategorySeq_ID < 1)
			 throw new IllegalArgumentException ("EXME_TaxCategorySeq_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_TaxCategorySeq_ID, Integer.valueOf(EXME_TaxCategorySeq_ID));
	}

	/** Get Tax sequence.
		@return Tax sequence	  */
	public int getEXME_TaxCategorySeq_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_TaxCategorySeq_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Base of calculation.
		@param Ref_TaxCategorySeq_ID Base of calculation	  */
	public void setRef_TaxCategorySeq_ID (int Ref_TaxCategorySeq_ID)
	{
		if (Ref_TaxCategorySeq_ID < 1) 
			set_Value (COLUMNNAME_Ref_TaxCategorySeq_ID, null);
		else 
			set_Value (COLUMNNAME_Ref_TaxCategorySeq_ID, Integer.valueOf(Ref_TaxCategorySeq_ID));
	}

	/** Get Base of calculation.
		@return Base of calculation	  */
	public int getRef_TaxCategorySeq_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Ref_TaxCategorySeq_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Sequence.
		@param Sequence Sequence	  */
	public void setSequence (int Sequence)
	{
		set_Value (COLUMNNAME_Sequence, Integer.valueOf(Sequence));
	}

	/** Get Sequence.
		@return Sequence	  */
	public int getSequence () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Sequence);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** SOPOType AD_Reference_ID=287 */
	public static final int SOPOTYPE_AD_Reference_ID=287;
	/** All = B */
	public static final String SOPOTYPE_All = "B";
	/** Sales = S */
	public static final String SOPOTYPE_Sales = "S";
	/** Purchase = P */
	public static final String SOPOTYPE_Purchase = "P";
	/** Set SO/PO Type.
		@param SOPOType 
		Sales Tax applies to sales situations, Purchase Tax to purchase situations
	  */
	public void setSOPOType (String SOPOType)
	{
		if (SOPOType == null) throw new IllegalArgumentException ("SOPOType is mandatory");
		if (SOPOType.equals("B") || SOPOType.equals("S") || SOPOType.equals("P")); else throw new IllegalArgumentException ("SOPOType Invalid value - " + SOPOType + " - Reference_ID=287 - B - S - P");		set_Value (COLUMNNAME_SOPOType, SOPOType);
	}

	/** Get SO/PO Type.
		@return Sales Tax applies to sales situations, Purchase Tax to purchase situations
	  */
	public String getSOPOType () 
	{
		return (String)get_Value(COLUMNNAME_SOPOType);
	}
}