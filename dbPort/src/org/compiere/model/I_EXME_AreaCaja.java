/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_AreaCaja
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_AreaCaja 
{

    /** TableName=EXME_AreaCaja */
    public static final String Table_Name = "EXME_AreaCaja";

    /** AD_Table_ID=1000161 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 7 - System - Client - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(7);

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

    /** Column name DocNoNotaCar */
    public static final String COLUMNNAME_DocNoNotaCar = "DocNoNotaCar";

	/** Set Note of Charge of Document No..
	  * Note of Charge of Document No.
	  */
	public void setDocNoNotaCar (String DocNoNotaCar);

	/** Get Note of Charge of Document No..
	  * Note of Charge of Document No.
	  */
	public String getDocNoNotaCar();

    /** Column name DocNoNotaCre */
    public static final String COLUMNNAME_DocNoNotaCre = "DocNoNotaCre";

	/** Set Note of Credit of Document No..
	  * Note of Credit of Document No.
	  */
	public void setDocNoNotaCre (String DocNoNotaCre);

	/** Get Note of Credit of Document No..
	  * Note of Credit of Document No.
	  */
	public String getDocNoNotaCre();

    /** Column name DocNoRecibo */
    public static final String COLUMNNAME_DocNoRecibo = "DocNoRecibo";

	/** Set Receipt Document No..
	  * Receipt Document No.
	  */
	public void setDocNoRecibo (String DocNoRecibo);

	/** Get Receipt Document No..
	  * Receipt Document No.
	  */
	public String getDocNoRecibo();

    /** Column name DocumentNoExt */
    public static final String COLUMNNAME_DocumentNoExt = "DocumentNoExt";

	/** Set Exterior Document No..
	  * Exterior Document No.
	  */
	public void setDocumentNoExt (String DocumentNoExt);

	/** Get Exterior Document No..
	  * Exterior Document No.
	  */
	public String getDocumentNoExt();

    /** Column name EXME_AreaCaja_ID */
    public static final String COLUMNNAME_EXME_AreaCaja_ID = "EXME_AreaCaja_ID";

	/** Set Cash book Area.
	  * Cash book Area
	  */
	public void setEXME_AreaCaja_ID (int EXME_AreaCaja_ID);

	/** Get Cash book Area.
	  * Cash book Area
	  */
	public int getEXME_AreaCaja_ID();

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

    /** Column name Prefix */
    public static final String COLUMNNAME_Prefix = "Prefix";

	/** Set Prefix.
	  * Prefix before the sequence number
	  */
	public void setPrefix (String Prefix);

	/** Get Prefix.
	  * Prefix before the sequence number
	  */
	public String getPrefix();

    /** Column name PrefixNotaCar */
    public static final String COLUMNNAME_PrefixNotaCar = "PrefixNotaCar";

	/** Set Prefix Charge Note.
	  * Prefix Charge Note
	  */
	public void setPrefixNotaCar (String PrefixNotaCar);

	/** Get Prefix Charge Note.
	  * Prefix Charge Note
	  */
	public String getPrefixNotaCar();

    /** Column name PrefixNotaCre */
    public static final String COLUMNNAME_PrefixNotaCre = "PrefixNotaCre";

	/** Set Prefix Credit Note.
	  * Prefix credit Note
	  */
	public void setPrefixNotaCre (String PrefixNotaCre);

	/** Get Prefix Credit Note.
	  * Prefix credit Note
	  */
	public String getPrefixNotaCre();

    /** Column name PrefixRecibo */
    public static final String COLUMNNAME_PrefixRecibo = "PrefixRecibo";

	/** Set Prefix Receipt.
	  * Prefix receipt
	  */
	public void setPrefixRecibo (String PrefixRecibo);

	/** Get Prefix Receipt.
	  * Prefix receipt
	  */
	public String getPrefixRecibo();

    /** Column name Reinicio_Anual */
    public static final String COLUMNNAME_Reinicio_Anual = "Reinicio_Anual";

	/** Set Annual Restart.
	  * Annual restart
	  */
	public void setReinicio_Anual (boolean Reinicio_Anual);

	/** Get Annual Restart.
	  * Annual restart
	  */
	public boolean isReinicio_Anual();

    /** Column name Reinicio_AnualNotaCar */
    public static final String COLUMNNAME_Reinicio_AnualNotaCar = "Reinicio_AnualNotaCar";

	/** Set Restart Annual Charge Note.
	  * Restart annual Charge note
	  */
	public void setReinicio_AnualNotaCar (boolean Reinicio_AnualNotaCar);

	/** Get Restart Annual Charge Note.
	  * Restart annual Charge note
	  */
	public boolean isReinicio_AnualNotaCar();

    /** Column name Reinicio_AnualNotaCre */
    public static final String COLUMNNAME_Reinicio_AnualNotaCre = "Reinicio_AnualNotaCre";

	/** Set Restart Annual Credit Note.
	  * Restart Annual Credit Note
	  */
	public void setReinicio_AnualNotaCre (boolean Reinicio_AnualNotaCre);

	/** Get Restart Annual Credit Note.
	  * Restart Annual Credit Note
	  */
	public boolean isReinicio_AnualNotaCre();

    /** Column name Reinicio_AnualRecibo */
    public static final String COLUMNNAME_Reinicio_AnualRecibo = "Reinicio_AnualRecibo";

	/** Set Restart Annual Payment Form Receipt.
	  * Restart annual payment form receipt
	  */
	public void setReinicio_AnualRecibo (boolean Reinicio_AnualRecibo);

	/** Get Restart Annual Payment Form Receipt.
	  * Restart annual payment form receipt
	  */
	public boolean isReinicio_AnualRecibo();

    /** Column name StartNo */
    public static final String COLUMNNAME_StartNo = "StartNo";

	/** Set Start No.
	  * Starting number/position
	  */
	public void setStartNo (int StartNo);

	/** Get Start No.
	  * Starting number/position
	  */
	public int getStartNo();

    /** Column name StartNoNotaCar */
    public static final String COLUMNNAME_StartNoNotaCar = "StartNoNotaCar";

	/** Set Initiate Charge Note No..
	  * initiate Charge Note No.
	  */
	public void setStartNoNotaCar (int StartNoNotaCar);

	/** Get Initiate Charge Note No..
	  * initiate Charge Note No.
	  */
	public int getStartNoNotaCar();

    /** Column name StartNoNotaCre */
    public static final String COLUMNNAME_StartNoNotaCre = "StartNoNotaCre";

	/** Set Initiate Credit Note No..
	  * initiate credit note no.
	  */
	public void setStartNoNotaCre (int StartNoNotaCre);

	/** Get Initiate Credit Note No..
	  * initiate credit note no.
	  */
	public int getStartNoNotaCre();

    /** Column name StartNoRecibo */
    public static final String COLUMNNAME_StartNoRecibo = "StartNoRecibo";

	/** Set Initiate Payment Receipt No..
	  * initiate Payment receipt No.
	  */
	public void setStartNoRecibo (int StartNoRecibo);

	/** Get Initiate Payment Receipt No..
	  * initiate Payment receipt No.
	  */
	public int getStartNoRecibo();

    /** Column name Suffix */
    public static final String COLUMNNAME_Suffix = "Suffix";

	/** Set Suffix.
	  * Suffix after the number
	  */
	public void setSuffix (String Suffix);

	/** Get Suffix.
	  * Suffix after the number
	  */
	public String getSuffix();

    /** Column name SuffixNotaCar */
    public static final String COLUMNNAME_SuffixNotaCar = "SuffixNotaCar";

	/** Set Sufix Charge Note.
	  * Sufix Charge Note
	  */
	public void setSuffixNotaCar (String SuffixNotaCar);

	/** Get Sufix Charge Note.
	  * Sufix Charge Note
	  */
	public String getSuffixNotaCar();

    /** Column name SuffixNotaCre */
    public static final String COLUMNNAME_SuffixNotaCre = "SuffixNotaCre";

	/** Set Sufix Credit Note.
	  * Sufix credit Note
	  */
	public void setSuffixNotaCre (String SuffixNotaCre);

	/** Get Sufix Credit Note.
	  * Sufix credit Note
	  */
	public String getSuffixNotaCre();

    /** Column name SuffixRecibo */
    public static final String COLUMNNAME_SuffixRecibo = "SuffixRecibo";

	/** Set Sufix Payment Receipt.
	  * Sufix Payment receipt
	  */
	public void setSuffixRecibo (String SuffixRecibo);

	/** Get Sufix Payment Receipt.
	  * Sufix Payment receipt
	  */
	public String getSuffixRecibo();

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
