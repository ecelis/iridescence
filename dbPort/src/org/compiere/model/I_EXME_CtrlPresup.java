/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_CtrlPresup
 *  @author Generated Class 
 *  @version Version @VERSION@
 */
public interface I_EXME_CtrlPresup 
{

    /** TableName=EXME_CtrlPresup */
    public static final String Table_Name = "EXME_CtrlPresup";

    /** AD_Table_ID=1201365 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 3 - Client - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(3);

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

    /** Column name Amount */
    public static final String COLUMNNAME_Amount = "Amount";

	/** Set Amount.
	  * Amount in a defined currency
	  */
	public void setAmount (BigDecimal Amount);

	/** Get Amount.
	  * Amount in a defined currency
	  */
	public BigDecimal getAmount();

    /** Column name AmountAbr */
    public static final String COLUMNNAME_AmountAbr = "AmountAbr";

	/** Set Amount April.
	  * Amount April
	  */
	public void setAmountAbr (BigDecimal AmountAbr);

	/** Get Amount April.
	  * Amount April
	  */
	public BigDecimal getAmountAbr();

    /** Column name AmountAgo */
    public static final String COLUMNNAME_AmountAgo = "AmountAgo";

	/** Set Amount August.
	  * Amount August
	  */
	public void setAmountAgo (BigDecimal AmountAgo);

	/** Get Amount August.
	  * Amount August
	  */
	public BigDecimal getAmountAgo();

    /** Column name AmountDic */
    public static final String COLUMNNAME_AmountDic = "AmountDic";

	/** Set Amount December.
	  * Amount December
	  */
	public void setAmountDic (BigDecimal AmountDic);

	/** Get Amount December.
	  * Amount December
	  */
	public BigDecimal getAmountDic();

    /** Column name AmountEne */
    public static final String COLUMNNAME_AmountEne = "AmountEne";

	/** Set Amount January.
	  * Amount January
	  */
	public void setAmountEne (BigDecimal AmountEne);

	/** Get Amount January.
	  * Amount January
	  */
	public BigDecimal getAmountEne();

    /** Column name AmountFeb */
    public static final String COLUMNNAME_AmountFeb = "AmountFeb";

	/** Set Amount February.
	  * Amount February
	  */
	public void setAmountFeb (BigDecimal AmountFeb);

	/** Get Amount February.
	  * Amount February
	  */
	public BigDecimal getAmountFeb();

    /** Column name AmountJul */
    public static final String COLUMNNAME_AmountJul = "AmountJul";

	/** Set Amount July.
	  * Amount July
	  */
	public void setAmountJul (BigDecimal AmountJul);

	/** Get Amount July.
	  * Amount July
	  */
	public BigDecimal getAmountJul();

    /** Column name AmountJun */
    public static final String COLUMNNAME_AmountJun = "AmountJun";

	/** Set Amount June.
	  * Amount June
	  */
	public void setAmountJun (BigDecimal AmountJun);

	/** Get Amount June.
	  * Amount June
	  */
	public BigDecimal getAmountJun();

    /** Column name AmountMar */
    public static final String COLUMNNAME_AmountMar = "AmountMar";

	/** Set Amount March.
	  * Amount March
	  */
	public void setAmountMar (BigDecimal AmountMar);

	/** Get Amount March.
	  * Amount March
	  */
	public BigDecimal getAmountMar();

    /** Column name AmountMay */
    public static final String COLUMNNAME_AmountMay = "AmountMay";

	/** Set Amount May.
	  * Amount May
	  */
	public void setAmountMay (BigDecimal AmountMay);

	/** Get Amount May.
	  * Amount May
	  */
	public BigDecimal getAmountMay();

    /** Column name AmountNov */
    public static final String COLUMNNAME_AmountNov = "AmountNov";

	/** Set Amount November.
	  * Amount November
	  */
	public void setAmountNov (BigDecimal AmountNov);

	/** Get Amount November.
	  * Amount November
	  */
	public BigDecimal getAmountNov();

    /** Column name AmountOct */
    public static final String COLUMNNAME_AmountOct = "AmountOct";

	/** Set Amount October.
	  * Amount October
	  */
	public void setAmountOct (BigDecimal AmountOct);

	/** Get Amount October.
	  * Amount October
	  */
	public BigDecimal getAmountOct();

    /** Column name AmountSep */
    public static final String COLUMNNAME_AmountSep = "AmountSep";

	/** Set Amount September.
	  * Amount September
	  */
	public void setAmountSep (BigDecimal AmountSep);

	/** Get Amount September.
	  * Amount September
	  */
	public BigDecimal getAmountSep();

    /** Column name Co1 */
    public static final String COLUMNNAME_Co1 = "Co1";

	/** Set Co. January.
	  * GL
	  */
	public void setCo1 (BigDecimal Co1);

	/** Get Co. January.
	  * GL
	  */
	public BigDecimal getCo1();

    /** Column name Co10 */
    public static final String COLUMNNAME_Co10 = "Co10";

	/** Set Co. October.
	  * GL
	  */
	public void setCo10 (BigDecimal Co10);

	/** Get Co. October.
	  * GL
	  */
	public BigDecimal getCo10();

    /** Column name Co11 */
    public static final String COLUMNNAME_Co11 = "Co11";

	/** Set Co. November.
	  * GL
	  */
	public void setCo11 (BigDecimal Co11);

	/** Get Co. November.
	  * GL
	  */
	public BigDecimal getCo11();

    /** Column name Co12 */
    public static final String COLUMNNAME_Co12 = "Co12";

	/** Set Co. December.
	  * GL
	  */
	public void setCo12 (BigDecimal Co12);

	/** Get Co. December.
	  * GL
	  */
	public BigDecimal getCo12();

    /** Column name Co2 */
    public static final String COLUMNNAME_Co2 = "Co2";

	/** Set Co. February.
	  * GL
	  */
	public void setCo2 (BigDecimal Co2);

	/** Get Co. February.
	  * GL
	  */
	public BigDecimal getCo2();

    /** Column name Co3 */
    public static final String COLUMNNAME_Co3 = "Co3";

	/** Set Co. March.
	  * GL
	  */
	public void setCo3 (BigDecimal Co3);

	/** Get Co. March.
	  * GL
	  */
	public BigDecimal getCo3();

    /** Column name Co4 */
    public static final String COLUMNNAME_Co4 = "Co4";

	/** Set Co. April.
	  * GL
	  */
	public void setCo4 (BigDecimal Co4);

	/** Get Co. April.
	  * GL
	  */
	public BigDecimal getCo4();

    /** Column name Co5 */
    public static final String COLUMNNAME_Co5 = "Co5";

	/** Set Co. May.
	  * GL
	  */
	public void setCo5 (BigDecimal Co5);

	/** Get Co. May.
	  * GL
	  */
	public BigDecimal getCo5();

    /** Column name Co6 */
    public static final String COLUMNNAME_Co6 = "Co6";

	/** Set Co. June.
	  * GL
	  */
	public void setCo6 (BigDecimal Co6);

	/** Get Co. June.
	  * GL
	  */
	public BigDecimal getCo6();

    /** Column name Co7 */
    public static final String COLUMNNAME_Co7 = "Co7";

	/** Set Co. July.
	  * GL
	  */
	public void setCo7 (BigDecimal Co7);

	/** Get Co. July.
	  * GL
	  */
	public BigDecimal getCo7();

    /** Column name Co8 */
    public static final String COLUMNNAME_Co8 = "Co8";

	/** Set Co. August.
	  * GL
	  */
	public void setCo8 (BigDecimal Co8);

	/** Get Co. August.
	  * GL
	  */
	public BigDecimal getCo8();

    /** Column name Co9 */
    public static final String COLUMNNAME_Co9 = "Co9";

	/** Set Co. September.
	  * GL
	  */
	public void setCo9 (BigDecimal Co9);

	/** Get Co. September.
	  * GL
	  */
	public BigDecimal getCo9();

    /** Column name CoSum */
    public static final String COLUMNNAME_CoSum = "CoSum";

	/** Set Co. Sum.
	  * GL
	  */
	public void setCoSum (BigDecimal CoSum);

	/** Get Co. Sum.
	  * GL
	  */
	public BigDecimal getCoSum();

    /** Column name C_Project_ID */
    public static final String COLUMNNAME_C_Project_ID = "C_Project_ID";

	/** Set Project.
	  * Financial Project
	  */
	public void setC_Project_ID (int C_Project_ID);

	/** Get Project.
	  * Financial Project
	  */
	public int getC_Project_ID();

	public I_C_Project getC_Project() throws RuntimeException;

    /** Column name C_Region_ID */
    public static final String COLUMNNAME_C_Region_ID = "C_Region_ID";

	/** Set Region.
	  * Identifies a geographical Region
	  */
	public void setC_Region_ID (int C_Region_ID);

	/** Get Region.
	  * Identifies a geographical Region
	  */
	public int getC_Region_ID();

	public I_C_Region getC_Region() throws RuntimeException;

    /** Column name De1 */
    public static final String COLUMNNAME_De1 = "De1";

	/** Set De. January.
	  * GL
	  */
	public void setDe1 (BigDecimal De1);

	/** Get De. January.
	  * GL
	  */
	public BigDecimal getDe1();

    /** Column name De10 */
    public static final String COLUMNNAME_De10 = "De10";

	/** Set De. October.
	  * GL
	  */
	public void setDe10 (BigDecimal De10);

	/** Get De. October.
	  * GL
	  */
	public BigDecimal getDe10();

    /** Column name De11 */
    public static final String COLUMNNAME_De11 = "De11";

	/** Set De. November.
	  * GL
	  */
	public void setDe11 (BigDecimal De11);

	/** Get De. November.
	  * GL
	  */
	public BigDecimal getDe11();

    /** Column name De12 */
    public static final String COLUMNNAME_De12 = "De12";

	/** Set De. December.
	  * GL
	  */
	public void setDe12 (BigDecimal De12);

	/** Get De. December.
	  * GL
	  */
	public BigDecimal getDe12();

    /** Column name De2 */
    public static final String COLUMNNAME_De2 = "De2";

	/** Set De. February.
	  * GL
	  */
	public void setDe2 (BigDecimal De2);

	/** Get De. February.
	  * GL
	  */
	public BigDecimal getDe2();

    /** Column name De3 */
    public static final String COLUMNNAME_De3 = "De3";

	/** Set De. March.
	  * GL
	  */
	public void setDe3 (BigDecimal De3);

	/** Get De. March.
	  * GL
	  */
	public BigDecimal getDe3();

    /** Column name De4 */
    public static final String COLUMNNAME_De4 = "De4";

	/** Set De. April.
	  * GL
	  */
	public void setDe4 (BigDecimal De4);

	/** Get De. April.
	  * GL
	  */
	public BigDecimal getDe4();

    /** Column name De5 */
    public static final String COLUMNNAME_De5 = "De5";

	/** Set De. May.
	  * GL
	  */
	public void setDe5 (BigDecimal De5);

	/** Get De. May.
	  * GL
	  */
	public BigDecimal getDe5();

    /** Column name De6 */
    public static final String COLUMNNAME_De6 = "De6";

	/** Set De. June.
	  * GL
	  */
	public void setDe6 (BigDecimal De6);

	/** Get De. June.
	  * GL
	  */
	public BigDecimal getDe6();

    /** Column name De7 */
    public static final String COLUMNNAME_De7 = "De7";

	/** Set De. July.
	  * GL
	  */
	public void setDe7 (BigDecimal De7);

	/** Get De. July.
	  * GL
	  */
	public BigDecimal getDe7();

    /** Column name De8 */
    public static final String COLUMNNAME_De8 = "De8";

	/** Set De. August.
	  * GL
	  */
	public void setDe8 (BigDecimal De8);

	/** Get De. August.
	  * GL
	  */
	public BigDecimal getDe8();

    /** Column name De9 */
    public static final String COLUMNNAME_De9 = "De9";

	/** Set De. September.
	  * GL
	  */
	public void setDe9 (BigDecimal De9);

	/** Get De. September.
	  * GL
	  */
	public BigDecimal getDe9();

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

    /** Column name DeSum */
    public static final String COLUMNNAME_DeSum = "DeSum";

	/** Set De. Sum.
	  * GL
	  */
	public void setDeSum (BigDecimal DeSum);

	/** Get De. Sum.
	  * GL
	  */
	public BigDecimal getDeSum();

    /** Column name Di1 */
    public static final String COLUMNNAME_Di1 = "Di1";

	/** Set Di. January.
	  * GL
	  */
	public void setDi1 (BigDecimal Di1);

	/** Get Di. January.
	  * GL
	  */
	public BigDecimal getDi1();

    /** Column name Di10 */
    public static final String COLUMNNAME_Di10 = "Di10";

	/** Set Di. October.
	  * GL
	  */
	public void setDi10 (BigDecimal Di10);

	/** Get Di. October.
	  * GL
	  */
	public BigDecimal getDi10();

    /** Column name Di11 */
    public static final String COLUMNNAME_Di11 = "Di11";

	/** Set Di. November.
	  * GL
	  */
	public void setDi11 (BigDecimal Di11);

	/** Get Di. November.
	  * GL
	  */
	public BigDecimal getDi11();

    /** Column name Di12 */
    public static final String COLUMNNAME_Di12 = "Di12";

	/** Set Di. December.
	  * GL
	  */
	public void setDi12 (BigDecimal Di12);

	/** Get Di. December.
	  * GL
	  */
	public BigDecimal getDi12();

    /** Column name Di2 */
    public static final String COLUMNNAME_Di2 = "Di2";

	/** Set Di. February.
	  * GL
	  */
	public void setDi2 (BigDecimal Di2);

	/** Get Di. February.
	  * GL
	  */
	public BigDecimal getDi2();

    /** Column name Di3 */
    public static final String COLUMNNAME_Di3 = "Di3";

	/** Set Di. March.
	  * GL
	  */
	public void setDi3 (BigDecimal Di3);

	/** Get Di. March.
	  * GL
	  */
	public BigDecimal getDi3();

    /** Column name Di4 */
    public static final String COLUMNNAME_Di4 = "Di4";

	/** Set Di. April.
	  * GL
	  */
	public void setDi4 (BigDecimal Di4);

	/** Get Di. April.
	  * GL
	  */
	public BigDecimal getDi4();

    /** Column name Di5 */
    public static final String COLUMNNAME_Di5 = "Di5";

	/** Set Di. May.
	  * GL
	  */
	public void setDi5 (BigDecimal Di5);

	/** Get Di. May.
	  * GL
	  */
	public BigDecimal getDi5();

    /** Column name Di6 */
    public static final String COLUMNNAME_Di6 = "Di6";

	/** Set Di. June.
	  * GL
	  */
	public void setDi6 (BigDecimal Di6);

	/** Get Di. June.
	  * GL
	  */
	public BigDecimal getDi6();

    /** Column name Di7 */
    public static final String COLUMNNAME_Di7 = "Di7";

	/** Set Di. July.
	  * GL
	  */
	public void setDi7 (BigDecimal Di7);

	/** Get Di. July.
	  * GL
	  */
	public BigDecimal getDi7();

    /** Column name Di8 */
    public static final String COLUMNNAME_Di8 = "Di8";

	/** Set Di. August.
	  * GL
	  */
	public void setDi8 (BigDecimal Di8);

	/** Get Di. August.
	  * GL
	  */
	public BigDecimal getDi8();

    /** Column name Di9 */
    public static final String COLUMNNAME_Di9 = "Di9";

	/** Set Di. September.
	  * GL
	  */
	public void setDi9 (BigDecimal Di9);

	/** Get Di. September.
	  * GL
	  */
	public BigDecimal getDi9();

    /** Column name Disum */
    public static final String COLUMNNAME_Disum = "Disum";

	/** Set Di. Sum.
	  * GL
	  */
	public void setDisum (BigDecimal Disum);

	/** Get Di. Sum.
	  * GL
	  */
	public BigDecimal getDisum();

    /** Column name Ej1 */
    public static final String COLUMNNAME_Ej1 = "Ej1";

	/** Set Ej. January.
	  * GL
	  */
	public void setEj1 (BigDecimal Ej1);

	/** Get Ej. January.
	  * GL
	  */
	public BigDecimal getEj1();

    /** Column name Ej10 */
    public static final String COLUMNNAME_Ej10 = "Ej10";

	/** Set Ej. October.
	  * GL
	  */
	public void setEj10 (BigDecimal Ej10);

	/** Get Ej. October.
	  * GL
	  */
	public BigDecimal getEj10();

    /** Column name Ej11 */
    public static final String COLUMNNAME_Ej11 = "Ej11";

	/** Set Ej. November.
	  * GL
	  */
	public void setEj11 (BigDecimal Ej11);

	/** Get Ej. November.
	  * GL
	  */
	public BigDecimal getEj11();

    /** Column name Ej12 */
    public static final String COLUMNNAME_Ej12 = "Ej12";

	/** Set Ej. December.
	  * GL
	  */
	public void setEj12 (BigDecimal Ej12);

	/** Get Ej. December.
	  * GL
	  */
	public BigDecimal getEj12();

    /** Column name Ej2 */
    public static final String COLUMNNAME_Ej2 = "Ej2";

	/** Set Ej. February.
	  * GL
	  */
	public void setEj2 (BigDecimal Ej2);

	/** Get Ej. February.
	  * GL
	  */
	public BigDecimal getEj2();

    /** Column name Ej3 */
    public static final String COLUMNNAME_Ej3 = "Ej3";

	/** Set Ej. March.
	  * GL
	  */
	public void setEj3 (BigDecimal Ej3);

	/** Get Ej. March.
	  * GL
	  */
	public BigDecimal getEj3();

    /** Column name Ej4 */
    public static final String COLUMNNAME_Ej4 = "Ej4";

	/** Set Ej. April.
	  * GL
	  */
	public void setEj4 (BigDecimal Ej4);

	/** Get Ej. April.
	  * GL
	  */
	public BigDecimal getEj4();

    /** Column name Ej5 */
    public static final String COLUMNNAME_Ej5 = "Ej5";

	/** Set Ej. May.
	  * GL
	  */
	public void setEj5 (BigDecimal Ej5);

	/** Get Ej. May.
	  * GL
	  */
	public BigDecimal getEj5();

    /** Column name Ej6 */
    public static final String COLUMNNAME_Ej6 = "Ej6";

	/** Set Ej. June.
	  * GL
	  */
	public void setEj6 (BigDecimal Ej6);

	/** Get Ej. June.
	  * GL
	  */
	public BigDecimal getEj6();

    /** Column name Ej7 */
    public static final String COLUMNNAME_Ej7 = "Ej7";

	/** Set Ej. July.
	  * GL
	  */
	public void setEj7 (BigDecimal Ej7);

	/** Get Ej. July.
	  * GL
	  */
	public BigDecimal getEj7();

    /** Column name Ej8 */
    public static final String COLUMNNAME_Ej8 = "Ej8";

	/** Set Ej. August.
	  * GL
	  */
	public void setEj8 (BigDecimal Ej8);

	/** Get Ej. August.
	  * GL
	  */
	public BigDecimal getEj8();

    /** Column name Ej9 */
    public static final String COLUMNNAME_Ej9 = "Ej9";

	/** Set Ej. September.
	  * GL
	  */
	public void setEj9 (BigDecimal Ej9);

	/** Get Ej. September.
	  * GL
	  */
	public BigDecimal getEj9();

    /** Column name EjSum */
    public static final String COLUMNNAME_EjSum = "EjSum";

	/** Set Ej. Sum.
	  * GL
	  */
	public void setEjSum (BigDecimal EjSum);

	/** Get Ej. Sum.
	  * GL
	  */
	public BigDecimal getEjSum();

    /** Column name EXME_ActInstitucional_ID */
    public static final String COLUMNNAME_EXME_ActInstitucional_ID = "EXME_ActInstitucional_ID";

	/** Set Institutional Activity	  */
	public void setEXME_ActInstitucional_ID (int EXME_ActInstitucional_ID);

	/** Get Institutional Activity	  */
	public int getEXME_ActInstitucional_ID();

	public I_EXME_ActInstitucional getEXME_ActInstitucional() throws RuntimeException;

    /** Column name EXME_ClasFuncional_Fun_ID */
    public static final String COLUMNNAME_EXME_ClasFuncional_Fun_ID = "EXME_ClasFuncional_Fun_ID";

	/** Set Function	  */
	public void setEXME_ClasFuncional_Fun_ID (int EXME_ClasFuncional_Fun_ID);

	/** Get Function	  */
	public int getEXME_ClasFuncional_Fun_ID();

	public I_EXME_ClasFuncional_Fun getEXME_ClasFuncional_Fun() throws RuntimeException;

    /** Column name EXME_ClasFuncional_ID */
    public static final String COLUMNNAME_EXME_ClasFuncional_ID = "EXME_ClasFuncional_ID";

	/** Set Functional classification	  */
	public void setEXME_ClasFuncional_ID (int EXME_ClasFuncional_ID);

	/** Get Functional classification	  */
	public int getEXME_ClasFuncional_ID();

	public I_EXME_ClasFuncional getEXME_ClasFuncional() throws RuntimeException;

    /** Column name EXME_ClasFuncional_Sfu_ID */
    public static final String COLUMNNAME_EXME_ClasFuncional_Sfu_ID = "EXME_ClasFuncional_Sfu_ID";

	/** Set Subfunction	  */
	public void setEXME_ClasFuncional_Sfu_ID (int EXME_ClasFuncional_Sfu_ID);

	/** Get Subfunction	  */
	public int getEXME_ClasFuncional_Sfu_ID();

	public I_EXME_ClasFuncional_Sfu getEXME_ClasFuncional_Sfu() throws RuntimeException;

    /** Column name EXME_CtrlPresup_ID */
    public static final String COLUMNNAME_EXME_CtrlPresup_ID = "EXME_CtrlPresup_ID";

	/** Set Budgetary control	  */
	public void setEXME_CtrlPresup_ID (int EXME_CtrlPresup_ID);

	/** Get Budgetary control	  */
	public int getEXME_CtrlPresup_ID();

    /** Column name EXME_FteFinanciamiento_ID */
    public static final String COLUMNNAME_EXME_FteFinanciamiento_ID = "EXME_FteFinanciamiento_ID";

	/** Set Source of Funding.
	  * Source of Funding
	  */
	public void setEXME_FteFinanciamiento_ID (int EXME_FteFinanciamiento_ID);

	/** Get Source of Funding.
	  * Source of Funding
	  */
	public int getEXME_FteFinanciamiento_ID();

	public I_EXME_FteFinanciamiento getEXME_FteFinanciamiento() throws RuntimeException;

    /** Column name EXME_PartidaPres_ID */
    public static final String COLUMNNAME_EXME_PartidaPres_ID = "EXME_PartidaPres_ID";

	/** Set Budget Item.
	  * Budget Item
	  */
	public void setEXME_PartidaPres_ID (int EXME_PartidaPres_ID);

	/** Get Budget Item.
	  * Budget Item
	  */
	public int getEXME_PartidaPres_ID();

	public I_EXME_PartidaPres getEXME_PartidaPres() throws RuntimeException;

    /** Column name EXME_PresupuestoDet_ID */
    public static final String COLUMNNAME_EXME_PresupuestoDet_ID = "EXME_PresupuestoDet_ID";

	/** Set Egress budget (Detail)	  */
	public void setEXME_PresupuestoDet_ID (int EXME_PresupuestoDet_ID);

	/** Get Egress budget (Detail)	  */
	public int getEXME_PresupuestoDet_ID();

	public I_EXME_PresupuestoDet getEXME_PresupuestoDet() throws RuntimeException;

    /** Column name EXME_PresupuestoEgr_ID */
    public static final String COLUMNNAME_EXME_PresupuestoEgr_ID = "EXME_PresupuestoEgr_ID";

	/** Set Egress budget	  */
	public void setEXME_PresupuestoEgr_ID (int EXME_PresupuestoEgr_ID);

	/** Get Egress budget	  */
	public int getEXME_PresupuestoEgr_ID();

	public I_EXME_PresupuestoEgr getEXME_PresupuestoEgr() throws RuntimeException;

    /** Column name EXME_ProgInstitucional_ID */
    public static final String COLUMNNAME_EXME_ProgInstitucional_ID = "EXME_ProgInstitucional_ID";

	/** Set Institutional program	  */
	public void setEXME_ProgInstitucional_ID (int EXME_ProgInstitucional_ID);

	/** Get Institutional program	  */
	public int getEXME_ProgInstitucional_ID();

	public I_EXME_ProgInstitucional getEXME_ProgInstitucional() throws RuntimeException;

    /** Column name EXME_ProgPresupuestal_ID */
    public static final String COLUMNNAME_EXME_ProgPresupuestal_ID = "EXME_ProgPresupuestal_ID";

	/** Set Budget programmes.
	  * Budget programmes
	  */
	public void setEXME_ProgPresupuestal_ID (int EXME_ProgPresupuestal_ID);

	/** Get Budget programmes.
	  * Budget programmes
	  */
	public int getEXME_ProgPresupuestal_ID();

	public I_EXME_ProgPresupuestal getEXME_ProgPresupuestal() throws RuntimeException;

    /** Column name EXME_Reasignacion_ID */
    public static final String COLUMNNAME_EXME_Reasignacion_ID = "EXME_Reasignacion_ID";

	/** Set Reallocation.
	  * Reallocation
	  */
	public void setEXME_Reasignacion_ID (int EXME_Reasignacion_ID);

	/** Get Reallocation.
	  * Reallocation
	  */
	public int getEXME_Reasignacion_ID();

	public I_EXME_Reasignacion getEXME_Reasignacion() throws RuntimeException;

    /** Column name EXME_TipoGasto_ID */
    public static final String COLUMNNAME_EXME_TipoGasto_ID = "EXME_TipoGasto_ID";

	/** Set Type of expenditure.
	  * Type of expenditure
	  */
	public void setEXME_TipoGasto_ID (int EXME_TipoGasto_ID);

	/** Get Type of expenditure.
	  * Type of expenditure
	  */
	public int getEXME_TipoGasto_ID();

	public I_EXME_TipoGasto getEXME_TipoGasto() throws RuntimeException;

    /** Column name Line */
    public static final String COLUMNNAME_Line = "Line";

	/** Set Line No.
	  * Unique line for this document
	  */
	public void setLine (int Line);

	/** Get Line No.
	  * Unique line for this document
	  */
	public int getLine();

    /** Column name Ma1 */
    public static final String COLUMNNAME_Ma1 = "Ma1";

	/** Set MA. January.
	  * GL
	  */
	public void setMa1 (BigDecimal Ma1);

	/** Get MA. January.
	  * GL
	  */
	public BigDecimal getMa1();

    /** Column name Ma10 */
    public static final String COLUMNNAME_Ma10 = "Ma10";

	/** Set MA. October.
	  * GL
	  */
	public void setMa10 (BigDecimal Ma10);

	/** Get MA. October.
	  * GL
	  */
	public BigDecimal getMa10();

    /** Column name Ma11 */
    public static final String COLUMNNAME_Ma11 = "Ma11";

	/** Set MA. November.
	  * GL
	  */
	public void setMa11 (BigDecimal Ma11);

	/** Get MA. November.
	  * GL
	  */
	public BigDecimal getMa11();

    /** Column name Ma12 */
    public static final String COLUMNNAME_Ma12 = "Ma12";

	/** Set MA. December.
	  * GL
	  */
	public void setMa12 (BigDecimal Ma12);

	/** Get MA. December.
	  * GL
	  */
	public BigDecimal getMa12();

    /** Column name Ma2 */
    public static final String COLUMNNAME_Ma2 = "Ma2";

	/** Set MA. February.
	  * GL
	  */
	public void setMa2 (BigDecimal Ma2);

	/** Get MA. February.
	  * GL
	  */
	public BigDecimal getMa2();

    /** Column name Ma3 */
    public static final String COLUMNNAME_Ma3 = "Ma3";

	/** Set MA. March.
	  * GL
	  */
	public void setMa3 (BigDecimal Ma3);

	/** Get MA. March.
	  * GL
	  */
	public BigDecimal getMa3();

    /** Column name Ma4 */
    public static final String COLUMNNAME_Ma4 = "Ma4";

	/** Set MA. April.
	  * GL
	  */
	public void setMa4 (BigDecimal Ma4);

	/** Get MA. April.
	  * GL
	  */
	public BigDecimal getMa4();

    /** Column name Ma5 */
    public static final String COLUMNNAME_Ma5 = "Ma5";

	/** Set MA. May.
	  * GL
	  */
	public void setMa5 (BigDecimal Ma5);

	/** Get MA. May.
	  * GL
	  */
	public BigDecimal getMa5();

    /** Column name Ma6 */
    public static final String COLUMNNAME_Ma6 = "Ma6";

	/** Set MA. June.
	  * GL
	  */
	public void setMa6 (BigDecimal Ma6);

	/** Get MA. June.
	  * GL
	  */
	public BigDecimal getMa6();

    /** Column name Ma7 */
    public static final String COLUMNNAME_Ma7 = "Ma7";

	/** Set MA. July.
	  * GL
	  */
	public void setMa7 (BigDecimal Ma7);

	/** Get MA. July.
	  * GL
	  */
	public BigDecimal getMa7();

    /** Column name Ma8 */
    public static final String COLUMNNAME_Ma8 = "Ma8";

	/** Set MA. August.
	  * GL
	  */
	public void setMa8 (BigDecimal Ma8);

	/** Get MA. August.
	  * GL
	  */
	public BigDecimal getMa8();

    /** Column name Ma9 */
    public static final String COLUMNNAME_Ma9 = "Ma9";

	/** Set MA. September.
	  * GL
	  */
	public void setMa9 (BigDecimal Ma9);

	/** Get MA. September.
	  * GL
	  */
	public BigDecimal getMa9();

    /** Column name MaSum */
    public static final String COLUMNNAME_MaSum = "MaSum";

	/** Set MA Sum.
	  * GL
	  */
	public void setMaSum (BigDecimal MaSum);

	/** Get MA Sum.
	  * GL
	  */
	public BigDecimal getMaSum();

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

    /** Column name Pa1 */
    public static final String COLUMNNAME_Pa1 = "Pa1";

	/** Set Pa. January.
	  * GL
	  */
	public void setPa1 (BigDecimal Pa1);

	/** Get Pa. January.
	  * GL
	  */
	public BigDecimal getPa1();

    /** Column name Pa10 */
    public static final String COLUMNNAME_Pa10 = "Pa10";

	/** Set Pa. October.
	  * GL
	  */
	public void setPa10 (BigDecimal Pa10);

	/** Get Pa. October.
	  * GL
	  */
	public BigDecimal getPa10();

    /** Column name Pa11 */
    public static final String COLUMNNAME_Pa11 = "Pa11";

	/** Set Pa. November.
	  * GL
	  */
	public void setPa11 (BigDecimal Pa11);

	/** Get Pa. November.
	  * GL
	  */
	public BigDecimal getPa11();

    /** Column name Pa12 */
    public static final String COLUMNNAME_Pa12 = "Pa12";

	/** Set Pa. December.
	  * GL
	  */
	public void setPa12 (BigDecimal Pa12);

	/** Get Pa. December.
	  * GL
	  */
	public BigDecimal getPa12();

    /** Column name Pa2 */
    public static final String COLUMNNAME_Pa2 = "Pa2";

	/** Set Pa. February.
	  * GL
	  */
	public void setPa2 (BigDecimal Pa2);

	/** Get Pa. February.
	  * GL
	  */
	public BigDecimal getPa2();

    /** Column name Pa3 */
    public static final String COLUMNNAME_Pa3 = "Pa3";

	/** Set Pa. March.
	  * GL
	  */
	public void setPa3 (BigDecimal Pa3);

	/** Get Pa. March.
	  * GL
	  */
	public BigDecimal getPa3();

    /** Column name Pa4 */
    public static final String COLUMNNAME_Pa4 = "Pa4";

	/** Set Pa. April.
	  * GL
	  */
	public void setPa4 (BigDecimal Pa4);

	/** Get Pa. April.
	  * GL
	  */
	public BigDecimal getPa4();

    /** Column name Pa5 */
    public static final String COLUMNNAME_Pa5 = "Pa5";

	/** Set Pa. May.
	  * GL
	  */
	public void setPa5 (BigDecimal Pa5);

	/** Get Pa. May.
	  * GL
	  */
	public BigDecimal getPa5();

    /** Column name Pa6 */
    public static final String COLUMNNAME_Pa6 = "Pa6";

	/** Set Pa. June.
	  * GL
	  */
	public void setPa6 (BigDecimal Pa6);

	/** Get Pa. June.
	  * GL
	  */
	public BigDecimal getPa6();

    /** Column name Pa7 */
    public static final String COLUMNNAME_Pa7 = "Pa7";

	/** Set Pa. July.
	  * GL
	  */
	public void setPa7 (BigDecimal Pa7);

	/** Get Pa. July.
	  * GL
	  */
	public BigDecimal getPa7();

    /** Column name Pa8 */
    public static final String COLUMNNAME_Pa8 = "Pa8";

	/** Set Pa. August.
	  * GL
	  */
	public void setPa8 (BigDecimal Pa8);

	/** Get Pa. August.
	  * GL
	  */
	public BigDecimal getPa8();

    /** Column name Pa9 */
    public static final String COLUMNNAME_Pa9 = "Pa9";

	/** Set Pa. September.
	  * GL
	  */
	public void setPa9 (BigDecimal Pa9);

	/** Get Pa. September.
	  * GL
	  */
	public BigDecimal getPa9();

    /** Column name PaSum */
    public static final String COLUMNNAME_PaSum = "PaSum";

	/** Set Pa. Sum.
	  * GL
	  */
	public void setPaSum (BigDecimal PaSum);

	/** Get Pa. Sum.
	  * GL
	  */
	public BigDecimal getPaSum();

    /** Column name Rt1 */
    public static final String COLUMNNAME_Rt1 = "Rt1";

	/** Set RT. January.
	  * GL
	  */
	public void setRt1 (BigDecimal Rt1);

	/** Get RT. January.
	  * GL
	  */
	public BigDecimal getRt1();

    /** Column name Rt10 */
    public static final String COLUMNNAME_Rt10 = "Rt10";

	/** Set RT. October.
	  * GL
	  */
	public void setRt10 (BigDecimal Rt10);

	/** Get RT. October.
	  * GL
	  */
	public BigDecimal getRt10();

    /** Column name Rt11 */
    public static final String COLUMNNAME_Rt11 = "Rt11";

	/** Set RT November.
	  * GL
	  */
	public void setRt11 (BigDecimal Rt11);

	/** Get RT November.
	  * GL
	  */
	public BigDecimal getRt11();

    /** Column name Rt12 */
    public static final String COLUMNNAME_Rt12 = "Rt12";

	/** Set RT. December.
	  * GL
	  */
	public void setRt12 (BigDecimal Rt12);

	/** Get RT. December.
	  * GL
	  */
	public BigDecimal getRt12();

    /** Column name Rt2 */
    public static final String COLUMNNAME_Rt2 = "Rt2";

	/** Set RT. February.
	  * GL
	  */
	public void setRt2 (BigDecimal Rt2);

	/** Get RT. February.
	  * GL
	  */
	public BigDecimal getRt2();

    /** Column name Rt3 */
    public static final String COLUMNNAME_Rt3 = "Rt3";

	/** Set RT. March.
	  * GL
	  */
	public void setRt3 (BigDecimal Rt3);

	/** Get RT. March.
	  * GL
	  */
	public BigDecimal getRt3();

    /** Column name Rt4 */
    public static final String COLUMNNAME_Rt4 = "Rt4";

	/** Set RT. April.
	  * GL
	  */
	public void setRt4 (BigDecimal Rt4);

	/** Get RT. April.
	  * GL
	  */
	public BigDecimal getRt4();

    /** Column name Rt5 */
    public static final String COLUMNNAME_Rt5 = "Rt5";

	/** Set RT. May.
	  * GL
	  */
	public void setRt5 (BigDecimal Rt5);

	/** Get RT. May.
	  * GL
	  */
	public BigDecimal getRt5();

    /** Column name Rt6 */
    public static final String COLUMNNAME_Rt6 = "Rt6";

	/** Set RT. June.
	  * GL
	  */
	public void setRt6 (BigDecimal Rt6);

	/** Get RT. June.
	  * GL
	  */
	public BigDecimal getRt6();

    /** Column name Rt7 */
    public static final String COLUMNNAME_Rt7 = "Rt7";

	/** Set RT. July.
	  * GL
	  */
	public void setRt7 (BigDecimal Rt7);

	/** Get RT. July.
	  * GL
	  */
	public BigDecimal getRt7();

    /** Column name Rt8 */
    public static final String COLUMNNAME_Rt8 = "Rt8";

	/** Set RT. August.
	  * GL
	  */
	public void setRt8 (BigDecimal Rt8);

	/** Get RT. August.
	  * GL
	  */
	public BigDecimal getRt8();

    /** Column name Rt9 */
    public static final String COLUMNNAME_Rt9 = "Rt9";

	/** Set RT. September.
	  * GL
	  */
	public void setRt9 (BigDecimal Rt9);

	/** Get RT. September.
	  * GL
	  */
	public BigDecimal getRt9();

    /** Column name RtSum */
    public static final String COLUMNNAME_RtSum = "RtSum";

	/** Set RT. Sum.
	  * GL
	  */
	public void setRtSum (BigDecimal RtSum);

	/** Get RT. Sum.
	  * GL
	  */
	public BigDecimal getRtSum();

    /** Column name Tipo */
    public static final String COLUMNNAME_Tipo = "Tipo";

	/** Set Type.
	  * GL
	  */
	public void setTipo (String Tipo);

	/** Get Type.
	  * GL
	  */
	public String getTipo();

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
