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

/** Generated Model for PA_ReportLine
 *  @author Generated Class 
 *  @version Version @VERSION@ - $Id$ */
public class X_PA_ReportLine extends PO implements I_PA_ReportLine, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_PA_ReportLine (Properties ctx, int PA_ReportLine_ID, String trxName)
    {
      super (ctx, PA_ReportLine_ID, trxName);
      /** if (PA_ReportLine_ID == 0)
        {
			setIsPrinted (true);
// Y
			setLineType (null);
			setName (null);
			setPA_ReportLine_ID (0);
			setPA_ReportLineSet_ID (0);
			setSeqNo (0);
// @SQL=SELECT NVL(MAX(SeqNo),0)+10 AS DefaultValue FROM PA_ReportLine WHERE PA_ReportLineSet_ID=@PA_ReportLineSet_ID@
        } */
    }

    /** Load Constructor */
    public X_PA_ReportLine (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_PA_ReportLine[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** AmountType AD_Reference_ID=235 */
	public static final int AMOUNTTYPE_AD_Reference_ID=235;
	/** Total Debit Only = DT */
	public static final String AMOUNTTYPE_TotalDebitOnly = "DT";
	/** Total Credit Only = CT */
	public static final String AMOUNTTYPE_TotalCreditOnly = "CT";
	/** Total Balance = BT */
	public static final String AMOUNTTYPE_TotalBalance = "BT";
	/** Period Balance = BP */
	public static final String AMOUNTTYPE_PeriodBalance = "BP";
	/** Period Credit Only = CP */
	public static final String AMOUNTTYPE_PeriodCreditOnly = "CP";
	/** Period Debit Only = DP */
	public static final String AMOUNTTYPE_PeriodDebitOnly = "DP";
	/** Period Quantity = QP */
	public static final String AMOUNTTYPE_PeriodQuantity = "QP";
	/** Total Quantity = QT */
	public static final String AMOUNTTYPE_TotalQuantity = "QT";
	/** Year Balance = BY */
	public static final String AMOUNTTYPE_YearBalance = "BY";
	/** Year Credit Only = CY */
	public static final String AMOUNTTYPE_YearCreditOnly = "CY";
	/** Year Debit Only = DY */
	public static final String AMOUNTTYPE_YearDebitOnly = "DY";
	/** Year Quantity = QY */
	public static final String AMOUNTTYPE_YearQuantity = "QY";
	/** Natural Balance = BN */
	public static final String AMOUNTTYPE_NaturalBalance = "BN";
	/** Set Amount Type.
		@param AmountType 
		Type of amount to report
	  */
	public void setAmountType (String AmountType)
	{

		if (AmountType == null || AmountType.equals("DT") || AmountType.equals("CT") || AmountType.equals("BT") || AmountType.equals("BP") || AmountType.equals("CP") || AmountType.equals("DP") || AmountType.equals("QP") || AmountType.equals("QT") || AmountType.equals("BY") || AmountType.equals("CY") || AmountType.equals("DY") || AmountType.equals("QY") || AmountType.equals("BN")); else throw new IllegalArgumentException ("AmountType Invalid value - " + AmountType + " - Reference_ID=235 - DT - CT - BT - BP - CP - DP - QP - QT - BY - CY - DY - QY - BN");		set_Value (COLUMNNAME_AmountType, AmountType);
	}

	/** Get Amount Type.
		@return Type of amount to report
	  */
	public String getAmountType () 
	{
		return (String)get_Value(COLUMNNAME_AmountType);
	}

	/** CalculationType AD_Reference_ID=236 */
	public static final int CALCULATIONTYPE_AD_Reference_ID=236;
	/** Add (Op1+Op2) = A */
	public static final String CALCULATIONTYPE_AddOp1PlusOp2 = "A";
	/** Subtract (Op1-Op2) = S */
	public static final String CALCULATIONTYPE_SubtractOp1_Op2 = "S";
	/** Percentage (Op1 of Op2) = P */
	public static final String CALCULATIONTYPE_PercentageOp1OfOp2 = "P";
	/** Add Range (Op1 to Op2) = R */
	public static final String CALCULATIONTYPE_AddRangeOp1ToOp2 = "R";
	/** Net Income = N */
	public static final String CALCULATIONTYPE_NetIncome = "N";
	/** Set Calculation.
		@param CalculationType Calculation	  */
	public void setCalculationType (String CalculationType)
	{

		if (CalculationType == null || CalculationType.equals("A") || CalculationType.equals("S") || CalculationType.equals("P") || CalculationType.equals("R") || CalculationType.equals("N")); else throw new IllegalArgumentException ("CalculationType Invalid value - " + CalculationType + " - Reference_ID=236 - A - S - P - R - N");		set_Value (COLUMNNAME_CalculationType, CalculationType);
	}

	/** Get Calculation.
		@return Calculation	  */
	public String getCalculationType () 
	{
		return (String)get_Value(COLUMNNAME_CalculationType);
	}

	public I_C_ElementValue getC_ElementValue() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_C_ElementValue.Table_Name);
        I_C_ElementValue result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_C_ElementValue)constructor.newInstance(new Object[] {getCtx(), new Integer(getC_ElementValue_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Account Element.
		@param C_ElementValue_ID 
		Account Element
	  */
	public void setC_ElementValue_ID (int C_ElementValue_ID)
	{
		if (C_ElementValue_ID < 1) 
			set_Value (COLUMNNAME_C_ElementValue_ID, null);
		else 
			set_Value (COLUMNNAME_C_ElementValue_ID, Integer.valueOf(C_ElementValue_ID));
	}

	/** Get Account Element.
		@return Account Element
	  */
	public int getC_ElementValue_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_ElementValue_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Create Report Source.
		@param Create_Rep_Src 
		Create the report source for the report line
	  */
	public void setCreate_Rep_Src (String Create_Rep_Src)
	{
		set_Value (COLUMNNAME_Create_Rep_Src, Create_Rep_Src);
	}

	/** Get Create Report Source.
		@return Create the report source for the report line
	  */
	public String getCreate_Rep_Src () 
	{
		return (String)get_Value(COLUMNNAME_Create_Rep_Src);
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

	public I_GL_Budget getGL_Budget() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_GL_Budget.Table_Name);
        I_GL_Budget result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_GL_Budget)constructor.newInstance(new Object[] {getCtx(), new Integer(getGL_Budget_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Budget.
		@param GL_Budget_ID 
		General Ledger Budget
	  */
	public void setGL_Budget_ID (int GL_Budget_ID)
	{
		if (GL_Budget_ID < 1) 
			set_Value (COLUMNNAME_GL_Budget_ID, null);
		else 
			set_Value (COLUMNNAME_GL_Budget_ID, Integer.valueOf(GL_Budget_ID));
	}

	/** Get Budget.
		@return General Ledger Budget
	  */
	public int getGL_Budget_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_GL_Budget_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Printed.
		@param IsPrinted 
		Indicates if this document / line is printed
	  */
	public void setIsPrinted (boolean IsPrinted)
	{
		set_Value (COLUMNNAME_IsPrinted, Boolean.valueOf(IsPrinted));
	}

	/** Get Printed.
		@return Indicates if this document / line is printed
	  */
	public boolean isPrinted () 
	{
		Object oo = get_Value(COLUMNNAME_IsPrinted);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** LineType AD_Reference_ID=241 */
	public static final int LINETYPE_AD_Reference_ID=241;
	/** Segment Value = S */
	public static final String LINETYPE_SegmentValue = "S";
	/** Calculation = C */
	public static final String LINETYPE_Calculation = "C";
	/** Set Line Type.
		@param LineType Line Type	  */
	public void setLineType (String LineType)
	{
		if (LineType == null) throw new IllegalArgumentException ("LineType is mandatory");
		if (LineType.equals("S") || LineType.equals("C")); else throw new IllegalArgumentException ("LineType Invalid value - " + LineType + " - Reference_ID=241 - S - C");		set_Value (COLUMNNAME_LineType, LineType);
	}

	/** Get Line Type.
		@return Line Type	  */
	public String getLineType () 
	{
		return (String)get_Value(COLUMNNAME_LineType);
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

	/** Set Operand 1.
		@param Oper_1_ID 
		First operand for calculation
	  */
	public void setOper_1_ID (int Oper_1_ID)
	{
		if (Oper_1_ID < 1) 
			set_Value (COLUMNNAME_Oper_1_ID, null);
		else 
			set_Value (COLUMNNAME_Oper_1_ID, Integer.valueOf(Oper_1_ID));
	}

	/** Get Operand 1.
		@return First operand for calculation
	  */
	public int getOper_1_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Oper_1_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Operand 2.
		@param Oper_2_ID 
		Second operand for calculation
	  */
	public void setOper_2_ID (int Oper_2_ID)
	{
		if (Oper_2_ID < 1) 
			set_Value (COLUMNNAME_Oper_2_ID, null);
		else 
			set_Value (COLUMNNAME_Oper_2_ID, Integer.valueOf(Oper_2_ID));
	}

	/** Get Operand 2.
		@return Second operand for calculation
	  */
	public int getOper_2_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Oper_2_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** PAAmountType AD_Reference_ID=53328 */
	public static final int PAAMOUNTTYPE_AD_Reference_ID=53328;
	/** Balance (expected sign) = B */
	public static final String PAAMOUNTTYPE_BalanceExpectedSign = "B";
	/** Credit Only = C */
	public static final String PAAMOUNTTYPE_CreditOnly = "C";
	/** Debit Only = D */
	public static final String PAAMOUNTTYPE_DebitOnly = "D";
	/** Quantity (expected sign) = Q */
	public static final String PAAMOUNTTYPE_QuantityExpectedSign = "Q";
	/** Balance (accounted sign) = S */
	public static final String PAAMOUNTTYPE_BalanceAccountedSign = "S";
	/** Quantity (accounted sign) = R */
	public static final String PAAMOUNTTYPE_QuantityAccountedSign = "R";
	/** Set Amount Type.
		@param PAAmountType 
		PA Amount Type for reporting
	  */
	public void setPAAmountType (String PAAmountType)
	{

		if (PAAmountType == null || PAAmountType.equals("B") || PAAmountType.equals("C") || PAAmountType.equals("D") || PAAmountType.equals("Q") || PAAmountType.equals("S") || PAAmountType.equals("R")); else throw new IllegalArgumentException ("PAAmountType Invalid value - " + PAAmountType + " - Reference_ID=53328 - B - C - D - Q - S - R");		set_Value (COLUMNNAME_PAAmountType, PAAmountType);
	}

	/** Get Amount Type.
		@return PA Amount Type for reporting
	  */
	public String getPAAmountType () 
	{
		return (String)get_Value(COLUMNNAME_PAAmountType);
	}

	/** PAPeriodType AD_Reference_ID=53327 */
	public static final int PAPERIODTYPE_AD_Reference_ID=53327;
	/** Total = T */
	public static final String PAPERIODTYPE_Total = "T";
	/** Year = Y */
	public static final String PAPERIODTYPE_Year = "Y";
	/** Period = P */
	public static final String PAPERIODTYPE_Period = "P";
	/** Natural = N */
	public static final String PAPERIODTYPE_Natural = "N";
	/** Set Period Type.
		@param PAPeriodType 
		PA Period Type
	  */
	public void setPAPeriodType (String PAPeriodType)
	{

		if (PAPeriodType == null || PAPeriodType.equals("T") || PAPeriodType.equals("Y") || PAPeriodType.equals("P") || PAPeriodType.equals("N")); else throw new IllegalArgumentException ("PAPeriodType Invalid value - " + PAPeriodType + " - Reference_ID=53327 - T - Y - P - N");		set_Value (COLUMNNAME_PAPeriodType, PAPeriodType);
	}

	/** Get Period Type.
		@return PA Period Type
	  */
	public String getPAPeriodType () 
	{
		return (String)get_Value(COLUMNNAME_PAPeriodType);
	}

	/** Set Report Line.
		@param PA_ReportLine_ID Report Line	  */
	public void setPA_ReportLine_ID (int PA_ReportLine_ID)
	{
		if (PA_ReportLine_ID < 1)
			 throw new IllegalArgumentException ("PA_ReportLine_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_PA_ReportLine_ID, Integer.valueOf(PA_ReportLine_ID));
	}

	/** Get Report Line.
		@return Report Line	  */
	public int getPA_ReportLine_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_PA_ReportLine_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_PA_ReportLineSet getPA_ReportLineSet() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_PA_ReportLineSet.Table_Name);
        I_PA_ReportLineSet result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_PA_ReportLineSet)constructor.newInstance(new Object[] {getCtx(), new Integer(getPA_ReportLineSet_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Report Line Set.
		@param PA_ReportLineSet_ID Report Line Set	  */
	public void setPA_ReportLineSet_ID (int PA_ReportLineSet_ID)
	{
		if (PA_ReportLineSet_ID < 1)
			 throw new IllegalArgumentException ("PA_ReportLineSet_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_PA_ReportLineSet_ID, Integer.valueOf(PA_ReportLineSet_ID));
	}

	/** Get Report Line Set.
		@return Report Line Set	  */
	public int getPA_ReportLineSet_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_PA_ReportLineSet_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** PostingType AD_Reference_ID=125 */
	public static final int POSTINGTYPE_AD_Reference_ID=125;
	/** Actual = A */
	public static final String POSTINGTYPE_Actual = "A";
	/** Budget = B */
	public static final String POSTINGTYPE_Budget = "B";
	/** Commitment = E */
	public static final String POSTINGTYPE_Commitment = "E";
	/** Statistical = S */
	public static final String POSTINGTYPE_Statistical = "S";
	/** Reservation = R */
	public static final String POSTINGTYPE_Reservation = "R";
	/** Set Posting Type.
		@param PostingType 
		The type of posted amount for the transaction
	  */
	public void setPostingType (String PostingType)
	{

		if (PostingType == null || PostingType.equals("A") || PostingType.equals("B") || PostingType.equals("E") || PostingType.equals("S") || PostingType.equals("R")); else throw new IllegalArgumentException ("PostingType Invalid value - " + PostingType + " - Reference_ID=125 - A - B - E - S - R");		set_Value (COLUMNNAME_PostingType, PostingType);
	}

	/** Get Posting Type.
		@return The type of posted amount for the transaction
	  */
	public String getPostingType () 
	{
		return (String)get_Value(COLUMNNAME_PostingType);
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