/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_SActiva
 *  @author Generated Class 
 *  @version Release 1.1.2
 */
public interface I_EXME_SActiva 
{

    /** TableName=EXME_SActiva */
    public static final String Table_Name = "EXME_SActiva";

    /** AD_Table_ID=1000063 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 6 - System - Client 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(6);

    /** Load Meta Data */

    /** Column name AD_Org_ID */
    public static final String COLUMNNAME_AD_Org_ID = "AD_Org_ID";

	/** Set Organization.
	  * Organizational entity within client
	  */
	public void setAD_Org_ID (int AD_Org_ID);

	/** Get Organization.
	  * Organizational entity within client
	  */
	public int getAD_Org_ID();

    /** Column name BaseIngredientId */
    public static final String COLUMNNAME_BaseIngredientId = "BaseIngredientId";

	/** Set Base Ingredient	  */
	public void setBaseIngredientId (String BaseIngredientId);

	/** Get Base Ingredient	  */
	public String getBaseIngredientId();

    /** Column name ConceptId */
    public static final String COLUMNNAME_ConceptId = "ConceptId";

	/** Set Concept.
	  * Concept
	  */
	public void setConceptId (int ConceptId);

	/** Get Concept.
	  * Concept
	  */
	public int getConceptId();

    /** Column name ConceptType */
    public static final String COLUMNNAME_ConceptType = "ConceptType";

	/** Set Concept Type	  */
	public void setConceptType (int ConceptType);

	/** Get Concept Type	  */
	public int getConceptType();

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

    /** Column name EXME_SActiva_ID */
    public static final String COLUMNNAME_EXME_SActiva_ID = "EXME_SActiva_ID";

	/** Set Active Substance.
	  * Active Substance
	  */
	public void setEXME_SActiva_ID (int EXME_SActiva_ID);

	/** Get Active Substance.
	  * Active Substance
	  */
	public int getEXME_SActiva_ID();

    /** Column name Formula */
    public static final String COLUMNNAME_Formula = "Formula";

	/** Set Formule.
	  * Substance Formule
	  */
	public void setFormula (String Formula);

	/** Get Formule.
	  * Substance Formule
	  */
	public String getFormula();

    /** Column name HicSeqNo */
    public static final String COLUMNNAME_HicSeqNo = "HicSeqNo";

	/** Set Sequence number	  */
	public void setHicSeqNo (String HicSeqNo);

	/** Get Sequence number	  */
	public String getHicSeqNo();

    /** Column name IsGroup */
    public static final String COLUMNNAME_IsGroup = "IsGroup";

	/** Set Is Group.
	  * Marks this segment as a group
	  */
	public void setIsGroup (boolean IsGroup);

	/** Get Is Group.
	  * Marks this segment as a group
	  */
	public boolean isGroup();

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

    /** Column name Value */
    public static final String COLUMNNAME_Value = "Value";

	/** Set Search Key.
	  * Search key for the record in the format required - must be unique
	  */
	public void setValue (String Value);

	/** Get Search Key.
	  * Search key for the record in the format required - must be unique
	  */
	public String getValue();
}
