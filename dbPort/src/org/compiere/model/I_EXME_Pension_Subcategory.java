/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_Pension_Subcategory
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_Pension_Subcategory 
{

    /** TableName=EXME_Pension_Subcategory */
    public static final String Table_Name = "EXME_Pension_Subcategory";

    /** AD_Table_ID=1200217 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 3 - Client - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(3);

    /** Load Meta Data */
}
