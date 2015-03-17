/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for T_Report
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_T_Report 
{

    /** TableName=T_Report */
    public static final String Table_Name = "T_Report";

    /** AD_Table_ID=544 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 4 - System 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(4);

    /** Load Meta Data */

    /** Column name AD_PInstance_ID */
    public static final String COLUMNNAME_AD_PInstance_ID = "AD_PInstance_ID";

	/** Set Process Instance.
	  * Instance of the process
	  */
	public void setAD_PInstance_ID (int AD_PInstance_ID);

	/** Get Process Instance.
	  * Instance of the process
	  */
	public int getAD_PInstance_ID();

	public I_AD_PInstance getAD_PInstance() throws RuntimeException;

    /** Column name Col_0 */
    public static final String COLUMNNAME_Col_0 = "Col_0";

	/** Set Col 0	  */
	public void setCol_0 (BigDecimal Col_0);

	/** Get Col 0	  */
	public BigDecimal getCol_0();

    /** Column name Col_1 */
    public static final String COLUMNNAME_Col_1 = "Col_1";

	/** Set Col 1	  */
	public void setCol_1 (BigDecimal Col_1);

	/** Get Col 1	  */
	public BigDecimal getCol_1();

    /** Column name Col_10 */
    public static final String COLUMNNAME_Col_10 = "Col_10";

	/** Set Col 10	  */
	public void setCol_10 (BigDecimal Col_10);

	/** Get Col 10	  */
	public BigDecimal getCol_10();

    /** Column name Col_11 */
    public static final String COLUMNNAME_Col_11 = "Col_11";

	/** Set Col 11	  */
	public void setCol_11 (BigDecimal Col_11);

	/** Get Col 11	  */
	public BigDecimal getCol_11();

    /** Column name Col_12 */
    public static final String COLUMNNAME_Col_12 = "Col_12";

	/** Set Col 12	  */
	public void setCol_12 (BigDecimal Col_12);

	/** Get Col 12	  */
	public BigDecimal getCol_12();

    /** Column name Col_13 */
    public static final String COLUMNNAME_Col_13 = "Col_13";

	/** Set Col 13	  */
	public void setCol_13 (BigDecimal Col_13);

	/** Get Col 13	  */
	public BigDecimal getCol_13();

    /** Column name Col_14 */
    public static final String COLUMNNAME_Col_14 = "Col_14";

	/** Set Col 14	  */
	public void setCol_14 (BigDecimal Col_14);

	/** Get Col 14	  */
	public BigDecimal getCol_14();

    /** Column name Col_15 */
    public static final String COLUMNNAME_Col_15 = "Col_15";

	/** Set Col 15	  */
	public void setCol_15 (BigDecimal Col_15);

	/** Get Col 15	  */
	public BigDecimal getCol_15();

    /** Column name Col_16 */
    public static final String COLUMNNAME_Col_16 = "Col_16";

	/** Set Col 16	  */
	public void setCol_16 (BigDecimal Col_16);

	/** Get Col 16	  */
	public BigDecimal getCol_16();

    /** Column name Col_17 */
    public static final String COLUMNNAME_Col_17 = "Col_17";

	/** Set Col 17	  */
	public void setCol_17 (BigDecimal Col_17);

	/** Get Col 17	  */
	public BigDecimal getCol_17();

    /** Column name Col_18 */
    public static final String COLUMNNAME_Col_18 = "Col_18";

	/** Set Col 18	  */
	public void setCol_18 (BigDecimal Col_18);

	/** Get Col 18	  */
	public BigDecimal getCol_18();

    /** Column name Col_19 */
    public static final String COLUMNNAME_Col_19 = "Col_19";

	/** Set Col 19	  */
	public void setCol_19 (BigDecimal Col_19);

	/** Get Col 19	  */
	public BigDecimal getCol_19();

    /** Column name Col_2 */
    public static final String COLUMNNAME_Col_2 = "Col_2";

	/** Set Col 2	  */
	public void setCol_2 (BigDecimal Col_2);

	/** Get Col 2	  */
	public BigDecimal getCol_2();

    /** Column name Col_20 */
    public static final String COLUMNNAME_Col_20 = "Col_20";

	/** Set Col 20	  */
	public void setCol_20 (BigDecimal Col_20);

	/** Get Col 20	  */
	public BigDecimal getCol_20();

    /** Column name Col_3 */
    public static final String COLUMNNAME_Col_3 = "Col_3";

	/** Set Col 3	  */
	public void setCol_3 (BigDecimal Col_3);

	/** Get Col 3	  */
	public BigDecimal getCol_3();

    /** Column name Col_4 */
    public static final String COLUMNNAME_Col_4 = "Col_4";

	/** Set Col 4	  */
	public void setCol_4 (BigDecimal Col_4);

	/** Get Col 4	  */
	public BigDecimal getCol_4();

    /** Column name Col_5 */
    public static final String COLUMNNAME_Col_5 = "Col_5";

	/** Set Col 5	  */
	public void setCol_5 (BigDecimal Col_5);

	/** Get Col 5	  */
	public BigDecimal getCol_5();

    /** Column name Col_6 */
    public static final String COLUMNNAME_Col_6 = "Col_6";

	/** Set Col 6	  */
	public void setCol_6 (BigDecimal Col_6);

	/** Get Col 6	  */
	public BigDecimal getCol_6();

    /** Column name Col_7 */
    public static final String COLUMNNAME_Col_7 = "Col_7";

	/** Set Col 7	  */
	public void setCol_7 (BigDecimal Col_7);

	/** Get Col 7	  */
	public BigDecimal getCol_7();

    /** Column name Col_8 */
    public static final String COLUMNNAME_Col_8 = "Col_8";

	/** Set Col 8	  */
	public void setCol_8 (BigDecimal Col_8);

	/** Get Col 8	  */
	public BigDecimal getCol_8();

    /** Column name Col_9 */
    public static final String COLUMNNAME_Col_9 = "Col_9";

	/** Set Col 9	  */
	public void setCol_9 (BigDecimal Col_9);

	/** Get Col 9	  */
	public BigDecimal getCol_9();

    /** Column name Description */
    public static final String COLUMNNAME_Description = "Description";

	/** Set Description.
	  * Optional short description of the record
	  */
	public void setDescription (String Description);

	/** Get Description.
	  * Optional short description of the record
	  */
	public String getDescription();

    /** Column name Fact_Acct_ID */
    public static final String COLUMNNAME_Fact_Acct_ID = "Fact_Acct_ID";

	/** Set Accounting Fact	  */
	public void setFact_Acct_ID (int Fact_Acct_ID);

	/** Get Accounting Fact	  */
	public int getFact_Acct_ID();

    /** Column name LevelNo */
    public static final String COLUMNNAME_LevelNo = "LevelNo";

	/** Set Level no	  */
	public void setLevelNo (int LevelNo);

	/** Get Level no	  */
	public int getLevelNo();

    /** Column name Name */
    public static final String COLUMNNAME_Name = "Name";

	/** Set Name.
	  * Alphanumeric identifier of the entity
	  */
	public void setName (String Name);

	/** Get Name.
	  * Alphanumeric identifier of the entity
	  */
	public String getName();

    /** Column name PA_ReportLine_ID */
    public static final String COLUMNNAME_PA_ReportLine_ID = "PA_ReportLine_ID";

	/** Set Report Line	  */
	public void setPA_ReportLine_ID (int PA_ReportLine_ID);

	/** Get Report Line	  */
	public int getPA_ReportLine_ID();

	public I_PA_ReportLine getPA_ReportLine() throws RuntimeException;

    /** Column name Record_ID */
    public static final String COLUMNNAME_Record_ID = "Record_ID";

	/** Set Record ID.
	  * Direct internal record ID
	  */
	public void setRecord_ID (int Record_ID);

	/** Get Record ID.
	  * Direct internal record ID
	  */
	public int getRecord_ID();

    /** Column name SeqNo */
    public static final String COLUMNNAME_SeqNo = "SeqNo";

	/** Set Sequence Number.
	  * Method of ordering records;
 lowest number comes first
	  */
	public void setSeqNo (int SeqNo);

	/** Get Sequence Number.
	  * Method of ordering records;
 lowest number comes first
	  */
	public int getSeqNo();
}
