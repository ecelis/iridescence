/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_Reg_Superv
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_Reg_Superv 
{

    /** TableName=EXME_Reg_Superv */
    public static final String Table_Name = "EXME_Reg_Superv";

    /** AD_Table_ID=1200026 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 3 - Client - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(3);

    /** Load Meta Data */

    /** Column name Acuer_Con_Jefe_TS */
    public static final String COLUMNNAME_Acuer_Con_Jefe_TS = "Acuer_Con_Jefe_TS";

	/** Set Agreement with Social Work Chief.
	  * Agreement with Social Work Chief
	  */
	public void setAcuer_Con_Jefe_TS (BigDecimal Acuer_Con_Jefe_TS);

	/** Get Agreement with Social Work Chief.
	  * Agreement with Social Work Chief
	  */
	public BigDecimal getAcuer_Con_Jefe_TS();

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

    /** Column name Ases_Invest */
    public static final String COLUMNNAME_Ases_Invest = "Ases_Invest";

	/** Set Invest Advisor.
	  * Invest Advisor
	  */
	public void setAses_Invest (BigDecimal Ases_Invest);

	/** Get Invest Advisor.
	  * Invest Advisor
	  */
	public BigDecimal getAses_Invest();

    /** Column name Aseso_Est_Pas */
    public static final String COLUMNNAME_Aseso_Est_Pas = "Aseso_Est_Pas";

	/** Set Access. Est. Pas..
	  * Access. Est. Pas.
	  */
	public void setAseso_Est_Pas (BigDecimal Aseso_Est_Pas);

	/** Get Access. Est. Pas..
	  * Access. Est. Pas.
	  */
	public BigDecimal getAseso_Est_Pas();

    /** Column name Asis_Even_Acad */
    public static final String COLUMNNAME_Asis_Even_Acad = "Asis_Even_Acad";

	/** Set Asis. Even. Acad..
	  * Asis. Even. Acad.
	  */
	public void setAsis_Even_Acad (BigDecimal Asis_Even_Acad);

	/** Get Asis. Even. Acad..
	  * Asis. Even. Acad.
	  */
	public BigDecimal getAsis_Even_Acad();

    /** Column name Asis_Ses_Med */
    public static final String COLUMNNAME_Asis_Ses_Med = "Asis_Ses_Med";

	/** Set Asis. Ses. Med..
	  * Asis. Ses. Med.
	  */
	public void setAsis_Ses_Med (BigDecimal Asis_Ses_Med);

	/** Get Asis. Ses. Med..
	  * Asis. Ses. Med.
	  */
	public BigDecimal getAsis_Ses_Med();

    /** Column name Cons_Exp */
    public static final String COLUMNNAME_Cons_Exp = "Cons_Exp";

	/** Set Cons. Exp..
	  * Cons. Exp.
	  */
	public void setCons_Exp (BigDecimal Cons_Exp);

	/** Get Cons. Exp..
	  * Cons. Exp.
	  */
	public BigDecimal getCons_Exp();

    /** Column name Coord_Extra */
    public static final String COLUMNNAME_Coord_Extra = "Coord_Extra";

	/** Set Extra Coordination.
	  * Extra Coordination
	  */
	public void setCoord_Extra (BigDecimal Coord_Extra);

	/** Get Extra Coordination.
	  * Extra Coordination
	  */
	public BigDecimal getCoord_Extra();

    /** Column name Coor_Intra */
    public static final String COLUMNNAME_Coor_Intra = "Coor_Intra";

	/** Set Coor. Intra..
	  * Coor. Intra.
	  */
	public void setCoor_Intra (BigDecimal Coor_Intra);

	/** Get Coor. Intra..
	  * Coor. Intra.
	  */
	public BigDecimal getCoor_Intra();

    /** Column name Dimension_Cual */
    public static final String COLUMNNAME_Dimension_Cual = "Dimension_Cual";

	/** Set Dimension_Cual.
	  * Dimension_Cual
	  */
	public void setDimension_Cual (String Dimension_Cual);

	/** Get Dimension_Cual.
	  * Dimension_Cual
	  */
	public String getDimension_Cual();

    /** Column name Ent_Msd_Soc */
    public static final String COLUMNNAME_Ent_Msd_Soc = "Ent_Msd_Soc";

	/** Set Ent. Msd. Soc..
	  * Ent. Msd. Soc.
	  */
	public void setEnt_Msd_Soc (BigDecimal Ent_Msd_Soc);

	/** Get Ent. Msd. Soc..
	  * Ent. Msd. Soc.
	  */
	public BigDecimal getEnt_Msd_Soc();

    /** Column name Entre_Med_Paramed */
    public static final String COLUMNNAME_Entre_Med_Paramed = "Entre_Med_Paramed";

	/** Set Entre. Med. Paramed..
	  * Entre. Med. Paramed.
	  */
	public void setEntre_Med_Paramed (BigDecimal Entre_Med_Paramed);

	/** Get Entre. Med. Paramed..
	  * Entre. Med. Paramed.
	  */
	public BigDecimal getEntre_Med_Paramed();

    /** Column name Evalu_Grup */
    public static final String COLUMNNAME_Evalu_Grup = "Evalu_Grup";

	/** Set Evalu. Grup..
	  * Evalu. Grup.
	  */
	public void setEvalu_Grup (BigDecimal Evalu_Grup);

	/** Get Evalu. Grup..
	  * Evalu. Grup.
	  */
	public BigDecimal getEvalu_Grup();

    /** Column name Evalu_Ind */
    public static final String COLUMNNAME_Evalu_Ind = "Evalu_Ind";

	/** Set Evalu. Ind..
	  * Evalu. Ind.
	  */
	public void setEvalu_Ind (BigDecimal Evalu_Ind);

	/** Get Evalu. Ind..
	  * Evalu. Ind.
	  */
	public BigDecimal getEvalu_Ind();

    /** Column name EXME_Reg_Superv_ID */
    public static final String COLUMNNAME_EXME_Reg_Superv_ID = "EXME_Reg_Superv_ID";

	/** Set Daily Registry of the Supervisor.
	  * Daily Registry of the Supervisor
	  */
	public void setEXME_Reg_Superv_ID (int EXME_Reg_Superv_ID);

	/** Get Daily Registry of the Supervisor.
	  * Daily Registry of the Supervisor
	  */
	public int getEXME_Reg_Superv_ID();

    /** Column name Fecha */
    public static final String COLUMNNAME_Fecha = "Fecha";

	/** Set Date.
	  * Date
	  */
	public void setFecha (Timestamp Fecha);

	/** Get Date.
	  * Date
	  */
	public Timestamp getFecha();

    /** Column name Fecha_Ctrl */
    public static final String COLUMNNAME_Fecha_Ctrl = "Fecha_Ctrl";

	/** Set Control Date.
	  * Control Date
	  */
	public void setFecha_Ctrl (Timestamp Fecha_Ctrl);

	/** Get Control Date.
	  * Control Date
	  */
	public Timestamp getFecha_Ctrl();

    /** Column name Introd_Puest_Nuevo_Ing */
    public static final String COLUMNNAME_Introd_Puest_Nuevo_Ing = "Introd_Puest_Nuevo_Ing";

	/** Set Enter New Position.
	  * Enter New Position
	  */
	public void setIntrod_Puest_Nuevo_Ing (BigDecimal Introd_Puest_Nuevo_Ing);

	/** Get Enter New Position.
	  * Enter New Position
	  */
	public BigDecimal getIntrod_Puest_Nuevo_Ing();

    /** Column name Invest */
    public static final String COLUMNNAME_Invest = "Invest";

	/** Set Invest.
	  * Invest.
	  */
	public void setInvest (BigDecimal Invest);

	/** Get Invest.
	  * Invest.
	  */
	public BigDecimal getInvest();

    /** Column name IsPrinted */
    public static final String COLUMNNAME_IsPrinted = "IsPrinted";

	/** Set Printed.
	  * Indicates if this document / line is printed
	  */
	public void setIsPrinted (String IsPrinted);

	/** Get Printed.
	  * Indicates if this document / line is printed
	  */
	public String getIsPrinted();

    /** Column name Juntas */
    public static final String COLUMNNAME_Juntas = "Juntas";

	/** Set Meetings.
	  * Meetings
	  */
	public void setJuntas (BigDecimal Juntas);

	/** Get Meetings.
	  * Meetings
	  */
	public BigDecimal getJuntas();

    /** Column name Min_Trab */
    public static final String COLUMNNAME_Min_Trab = "Min_Trab";

	/** Set Min. Work.
	  * Min. Work
	  */
	public void setMin_Trab (BigDecimal Min_Trab);

	/** Get Min. Work.
	  * Min. Work
	  */
	public BigDecimal getMin_Trab();

    /** Column name Not_Inf */
    public static final String COLUMNNAME_Not_Inf = "Not_Inf";

	/** Set Not. Inf..
	  * Not. Inf.
	  */
	public void setNot_Inf (BigDecimal Not_Inf);

	/** Get Not. Inf..
	  * Not. Inf.
	  */
	public BigDecimal getNot_Inf();

    /** Column name Orientaciones */
    public static final String COLUMNNAME_Orientaciones = "Orientaciones";

	/** Set Guidelines.
	  * Guidelines
	  */
	public void setOrientaciones (BigDecimal Orientaciones);

	/** Get Guidelines.
	  * Guidelines
	  */
	public BigDecimal getOrientaciones();

    /** Column name Otros */
    public static final String COLUMNNAME_Otros = "Otros";

	/** Set Others	  */
	public void setOtros (BigDecimal Otros);

	/** Get Others	  */
	public BigDecimal getOtros();

    /** Column name Part_Even_Acad */
    public static final String COLUMNNAME_Part_Even_Acad = "Part_Even_Acad";

	/** Set Part. Even. Acad..
	  * Part. Even. Acad.
	  */
	public void setPart_Even_Acad (BigDecimal Part_Even_Acad);

	/** Get Part. Even. Acad..
	  * Part. Even. Acad.
	  */
	public BigDecimal getPart_Even_Acad();

    /** Column name Personas */
    public static final String COLUMNNAME_Personas = "Personas";

	/** Set Persons.
	  * Persons
	  */
	public void setPersonas (BigDecimal Personas);

	/** Get Persons.
	  * Persons
	  */
	public BigDecimal getPersonas();

    /** Column name Present_Casos_Jefe_TS */
    public static final String COLUMNNAME_Present_Casos_Jefe_TS = "Present_Casos_Jefe_TS";

	/** Set Present. Cases Chief S.W..
	  * Present. Cases Chief S.W.
	  */
	public void setPresent_Casos_Jefe_TS (BigDecimal Present_Casos_Jefe_TS);

	/** Get Present. Cases Chief S.W..
	  * Present. Cases Chief S.W.
	  */
	public BigDecimal getPresent_Casos_Jefe_TS();

    /** Column name Recor_SC */
    public static final String COLUMNNAME_Recor_SC = "Recor_SC";

	/** Set Recor. S.C..
	  * Recor. S.C.
	  */
	public void setRecor_SC (BigDecimal Recor_SC);

	/** Get Recor. S.C..
	  * Recor. S.C.
	  */
	public BigDecimal getRecor_SC();

    /** Column name Reg_Inf_Mens_Conc */
    public static final String COLUMNNAME_Reg_Inf_Mens_Conc = "Reg_Inf_Mens_Conc";

	/** Set Reg. Inf. Mens. Conc..
	  * Reg. Inf. Mens. Conc.
	  */
	public void setReg_Inf_Mens_Conc (BigDecimal Reg_Inf_Mens_Conc);

	/** Get Reg. Inf. Mens. Conc..
	  * Reg. Inf. Mens. Conc.
	  */
	public BigDecimal getReg_Inf_Mens_Conc();

    /** Column name Reg_Inf_Mens_Ind */
    public static final String COLUMNNAME_Reg_Inf_Mens_Ind = "Reg_Inf_Mens_Ind";

	/** Set Reg. Inf. Mens. Ind..
	  * Reg. Inf. Mens. Ind.
	  */
	public void setReg_Inf_Mens_Ind (BigDecimal Reg_Inf_Mens_Ind);

	/** Get Reg. Inf. Mens. Ind..
	  * Reg. Inf. Mens. Ind.
	  */
	public BigDecimal getReg_Inf_Mens_Ind();

    /** Column name Report_Recor_SC */
    public static final String COLUMNNAME_Report_Recor_SC = "Report_Recor_SC";

	/** Set Report. Recor. S.C..
	  * Report. Recor. S.C.
	  */
	public void setReport_Recor_SC (BigDecimal Report_Recor_SC);

	/** Get Report. Recor. S.C..
	  * Report. Recor. S.C.
	  */
	public BigDecimal getReport_Recor_SC();

    /** Column name Reun_Comp */
    public static final String COLUMNNAME_Reun_Comp = "Reun_Comp";

	/** Set Comp. Meeting.
	  * Comp. Meeting
	  */
	public void setReun_Comp (BigDecimal Reun_Comp);

	/** Get Comp. Meeting.
	  * Comp. Meeting
	  */
	public BigDecimal getReun_Comp();

    /** Column name Reunion_Jef_Serv */
    public static final String COLUMNNAME_Reunion_Jef_Serv = "Reunion_Jef_Serv";

	/** Set Service Mgr Meeting.
	  * Service Mgr Meeting
	  */
	public void setReunion_Jef_Serv (BigDecimal Reunion_Jef_Serv);

	/** Get Service Mgr Meeting.
	  * Service Mgr Meeting
	  */
	public BigDecimal getReunion_Jef_Serv();

    /** Column name Rev_Casos_TS */
    public static final String COLUMNNAME_Rev_Casos_TS = "Rev_Casos_TS";

	/** Set Revalidation Case S.W..
	  * Revalidation Case S.W.
	  */
	public void setRev_Casos_TS (BigDecimal Rev_Casos_TS);

	/** Get Revalidation Case S.W..
	  * Revalidation Case S.W.
	  */
	public BigDecimal getRev_Casos_TS();

    /** Column name Supervisora */
    public static final String COLUMNNAME_Supervisora = "Supervisora";

	/** Set Supervisor.
	  * Supervisor
	  */
	public void setSupervisora (String Supervisora);

	/** Get Supervisor.
	  * Supervisor
	  */
	public String getSupervisora();

    /** Column name Sup_Grup_con_Jefe_TS */
    public static final String COLUMNNAME_Sup_Grup_con_Jefe_TS = "Sup_Grup_con_Jefe_TS";

	/** Set Sup. Group. with Chief T.S..
	  * Sup. Group. with Chief T.S.
	  */
	public void setSup_Grup_con_Jefe_TS (BigDecimal Sup_Grup_con_Jefe_TS);

	/** Get Sup. Group. with Chief T.S..
	  * Sup. Group. with Chief T.S.
	  */
	public BigDecimal getSup_Grup_con_Jefe_TS();

    /** Column name Sup_Grup_TS_Gest */
    public static final String COLUMNNAME_Sup_Grup_TS_Gest = "Sup_Grup_TS_Gest";

	/** Set Sup. Group. T.S. Mgr..
	  * Sup. Group. T.S. Mgr.
	  */
	public void setSup_Grup_TS_Gest (BigDecimal Sup_Grup_TS_Gest);

	/** Get Sup. Group. T.S. Mgr..
	  * Sup. Group. T.S. Mgr.
	  */
	public BigDecimal getSup_Grup_TS_Gest();

    /** Column name Sup_Ind_con_Jefe_TS */
    public static final String COLUMNNAME_Sup_Ind_con_Jefe_TS = "Sup_Ind_con_Jefe_TS";

	/** Set Sup. Ind. with Chief T.S..
	  * Sup. Ind. with Chief T.S.
	  */
	public void setSup_Ind_con_Jefe_TS (BigDecimal Sup_Ind_con_Jefe_TS);

	/** Get Sup. Ind. with Chief T.S..
	  * Sup. Ind. with Chief T.S.
	  */
	public BigDecimal getSup_Ind_con_Jefe_TS();

    /** Column name Sup_Ind_TS_Gest */
    public static final String COLUMNNAME_Sup_Ind_TS_Gest = "Sup_Ind_TS_Gest";

	/** Set Sup. Ind. T.S. Mgr..
	  * Sup. Ind. T.S. Mgr.
	  */
	public void setSup_Ind_TS_Gest (BigDecimal Sup_Ind_TS_Gest);

	/** Get Sup. Ind. T.S. Mgr..
	  * Sup. Ind. T.S. Mgr.
	  */
	public BigDecimal getSup_Ind_TS_Gest();
}
