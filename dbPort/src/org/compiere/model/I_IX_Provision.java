/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for IX_Provision
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_IX_Provision 
{

    /** TableName=IX_Provision */
    public static final String Table_Name = "IX_Provision";

    /** AD_Table_ID=1200261 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 3 - Client - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(3);

    /** Load Meta Data */
}
