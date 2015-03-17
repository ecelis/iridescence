/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;
import org.compiere.util.Env;

/** Generated Model for EXME_Reg_Superv
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_Reg_Superv extends PO implements I_EXME_Reg_Superv, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_Reg_Superv (Properties ctx, int EXME_Reg_Superv_ID, String trxName)
    {
      super (ctx, EXME_Reg_Superv_ID, trxName);
      /** if (EXME_Reg_Superv_ID == 0)
        {
			setEXME_Reg_Superv_ID (0);
			setFecha (new Timestamp( System.currentTimeMillis() ));
			setFecha_Ctrl (new Timestamp( System.currentTimeMillis() ));
        } */
    }

    /** Load Constructor */
    public X_EXME_Reg_Superv (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_Reg_Superv[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Agreement with Social Work Chief.
		@param Acuer_Con_Jefe_TS 
		Agreement with Social Work Chief
	  */
	public void setAcuer_Con_Jefe_TS (BigDecimal Acuer_Con_Jefe_TS)
	{
		set_Value (COLUMNNAME_Acuer_Con_Jefe_TS, Acuer_Con_Jefe_TS);
	}

	/** Get Agreement with Social Work Chief.
		@return Agreement with Social Work Chief
	  */
	public BigDecimal getAcuer_Con_Jefe_TS () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Acuer_Con_Jefe_TS);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Invest Advisor.
		@param Ases_Invest 
		Invest Advisor
	  */
	public void setAses_Invest (BigDecimal Ases_Invest)
	{
		set_Value (COLUMNNAME_Ases_Invest, Ases_Invest);
	}

	/** Get Invest Advisor.
		@return Invest Advisor
	  */
	public BigDecimal getAses_Invest () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Ases_Invest);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Access. Est. Pas..
		@param Aseso_Est_Pas 
		Access. Est. Pas.
	  */
	public void setAseso_Est_Pas (BigDecimal Aseso_Est_Pas)
	{
		set_Value (COLUMNNAME_Aseso_Est_Pas, Aseso_Est_Pas);
	}

	/** Get Access. Est. Pas..
		@return Access. Est. Pas.
	  */
	public BigDecimal getAseso_Est_Pas () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Aseso_Est_Pas);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Asis. Even. Acad..
		@param Asis_Even_Acad 
		Asis. Even. Acad.
	  */
	public void setAsis_Even_Acad (BigDecimal Asis_Even_Acad)
	{
		set_Value (COLUMNNAME_Asis_Even_Acad, Asis_Even_Acad);
	}

	/** Get Asis. Even. Acad..
		@return Asis. Even. Acad.
	  */
	public BigDecimal getAsis_Even_Acad () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Asis_Even_Acad);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Asis. Ses. Med..
		@param Asis_Ses_Med 
		Asis. Ses. Med.
	  */
	public void setAsis_Ses_Med (BigDecimal Asis_Ses_Med)
	{
		set_Value (COLUMNNAME_Asis_Ses_Med, Asis_Ses_Med);
	}

	/** Get Asis. Ses. Med..
		@return Asis. Ses. Med.
	  */
	public BigDecimal getAsis_Ses_Med () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Asis_Ses_Med);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Cons. Exp..
		@param Cons_Exp 
		Cons. Exp.
	  */
	public void setCons_Exp (BigDecimal Cons_Exp)
	{
		set_Value (COLUMNNAME_Cons_Exp, Cons_Exp);
	}

	/** Get Cons. Exp..
		@return Cons. Exp.
	  */
	public BigDecimal getCons_Exp () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Cons_Exp);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Extra Coordination.
		@param Coord_Extra 
		Extra Coordination
	  */
	public void setCoord_Extra (BigDecimal Coord_Extra)
	{
		set_Value (COLUMNNAME_Coord_Extra, Coord_Extra);
	}

	/** Get Extra Coordination.
		@return Extra Coordination
	  */
	public BigDecimal getCoord_Extra () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Coord_Extra);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Coor. Intra..
		@param Coor_Intra 
		Coor. Intra.
	  */
	public void setCoor_Intra (BigDecimal Coor_Intra)
	{
		set_Value (COLUMNNAME_Coor_Intra, Coor_Intra);
	}

	/** Get Coor. Intra..
		@return Coor. Intra.
	  */
	public BigDecimal getCoor_Intra () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Coor_Intra);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Dimension_Cual.
		@param Dimension_Cual 
		Dimension_Cual
	  */
	public void setDimension_Cual (String Dimension_Cual)
	{
		set_Value (COLUMNNAME_Dimension_Cual, Dimension_Cual);
	}

	/** Get Dimension_Cual.
		@return Dimension_Cual
	  */
	public String getDimension_Cual () 
	{
		return (String)get_Value(COLUMNNAME_Dimension_Cual);
	}

	/** Set Ent. Msd. Soc..
		@param Ent_Msd_Soc 
		Ent. Msd. Soc.
	  */
	public void setEnt_Msd_Soc (BigDecimal Ent_Msd_Soc)
	{
		set_Value (COLUMNNAME_Ent_Msd_Soc, Ent_Msd_Soc);
	}

	/** Get Ent. Msd. Soc..
		@return Ent. Msd. Soc.
	  */
	public BigDecimal getEnt_Msd_Soc () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Ent_Msd_Soc);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Entre. Med. Paramed..
		@param Entre_Med_Paramed 
		Entre. Med. Paramed.
	  */
	public void setEntre_Med_Paramed (BigDecimal Entre_Med_Paramed)
	{
		set_Value (COLUMNNAME_Entre_Med_Paramed, Entre_Med_Paramed);
	}

	/** Get Entre. Med. Paramed..
		@return Entre. Med. Paramed.
	  */
	public BigDecimal getEntre_Med_Paramed () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Entre_Med_Paramed);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Evalu. Grup..
		@param Evalu_Grup 
		Evalu. Grup.
	  */
	public void setEvalu_Grup (BigDecimal Evalu_Grup)
	{
		set_Value (COLUMNNAME_Evalu_Grup, Evalu_Grup);
	}

	/** Get Evalu. Grup..
		@return Evalu. Grup.
	  */
	public BigDecimal getEvalu_Grup () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Evalu_Grup);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Evalu. Ind..
		@param Evalu_Ind 
		Evalu. Ind.
	  */
	public void setEvalu_Ind (BigDecimal Evalu_Ind)
	{
		set_Value (COLUMNNAME_Evalu_Ind, Evalu_Ind);
	}

	/** Get Evalu. Ind..
		@return Evalu. Ind.
	  */
	public BigDecimal getEvalu_Ind () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Evalu_Ind);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Daily Registry of the Supervisor.
		@param EXME_Reg_Superv_ID 
		Daily Registry of the Supervisor
	  */
	public void setEXME_Reg_Superv_ID (int EXME_Reg_Superv_ID)
	{
		if (EXME_Reg_Superv_ID < 1)
			 throw new IllegalArgumentException ("EXME_Reg_Superv_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_Reg_Superv_ID, Integer.valueOf(EXME_Reg_Superv_ID));
	}

	/** Get Daily Registry of the Supervisor.
		@return Daily Registry of the Supervisor
	  */
	public int getEXME_Reg_Superv_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Reg_Superv_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Date.
		@param Fecha 
		Date
	  */
	public void setFecha (Timestamp Fecha)
	{
		if (Fecha == null)
			throw new IllegalArgumentException ("Fecha is mandatory.");
		set_Value (COLUMNNAME_Fecha, Fecha);
	}

	/** Get Date.
		@return Date
	  */
	public Timestamp getFecha () 
	{
		return (Timestamp)get_Value(COLUMNNAME_Fecha);
	}

	/** Set Control Date.
		@param Fecha_Ctrl 
		Control Date
	  */
	public void setFecha_Ctrl (Timestamp Fecha_Ctrl)
	{
		if (Fecha_Ctrl == null)
			throw new IllegalArgumentException ("Fecha_Ctrl is mandatory.");
		set_Value (COLUMNNAME_Fecha_Ctrl, Fecha_Ctrl);
	}

	/** Get Control Date.
		@return Control Date
	  */
	public Timestamp getFecha_Ctrl () 
	{
		return (Timestamp)get_Value(COLUMNNAME_Fecha_Ctrl);
	}

	/** Set Enter New Position.
		@param Introd_Puest_Nuevo_Ing 
		Enter New Position
	  */
	public void setIntrod_Puest_Nuevo_Ing (BigDecimal Introd_Puest_Nuevo_Ing)
	{
		set_Value (COLUMNNAME_Introd_Puest_Nuevo_Ing, Introd_Puest_Nuevo_Ing);
	}

	/** Get Enter New Position.
		@return Enter New Position
	  */
	public BigDecimal getIntrod_Puest_Nuevo_Ing () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Introd_Puest_Nuevo_Ing);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Invest.
		@param Invest 
		Invest.
	  */
	public void setInvest (BigDecimal Invest)
	{
		set_Value (COLUMNNAME_Invest, Invest);
	}

	/** Get Invest.
		@return Invest.
	  */
	public BigDecimal getInvest () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Invest);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Printed.
		@param IsPrinted 
		Indicates if this document / line is printed
	  */
	public void setIsPrinted (String IsPrinted)
	{
		set_Value (COLUMNNAME_IsPrinted, IsPrinted);
	}

	/** Get Printed.
		@return Indicates if this document / line is printed
	  */
	public String getIsPrinted () 
	{
		return (String)get_Value(COLUMNNAME_IsPrinted);
	}

	/** Set Meetings.
		@param Juntas 
		Meetings
	  */
	public void setJuntas (BigDecimal Juntas)
	{
		set_Value (COLUMNNAME_Juntas, Juntas);
	}

	/** Get Meetings.
		@return Meetings
	  */
	public BigDecimal getJuntas () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Juntas);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Min. Work.
		@param Min_Trab 
		Min. Work
	  */
	public void setMin_Trab (BigDecimal Min_Trab)
	{
		set_Value (COLUMNNAME_Min_Trab, Min_Trab);
	}

	/** Get Min. Work.
		@return Min. Work
	  */
	public BigDecimal getMin_Trab () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Min_Trab);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Not. Inf..
		@param Not_Inf 
		Not. Inf.
	  */
	public void setNot_Inf (BigDecimal Not_Inf)
	{
		set_Value (COLUMNNAME_Not_Inf, Not_Inf);
	}

	/** Get Not. Inf..
		@return Not. Inf.
	  */
	public BigDecimal getNot_Inf () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Not_Inf);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Guidelines.
		@param Orientaciones 
		Guidelines
	  */
	public void setOrientaciones (BigDecimal Orientaciones)
	{
		set_Value (COLUMNNAME_Orientaciones, Orientaciones);
	}

	/** Get Guidelines.
		@return Guidelines
	  */
	public BigDecimal getOrientaciones () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Orientaciones);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Others.
		@param Otros Others	  */
	public void setOtros (BigDecimal Otros)
	{
		set_Value (COLUMNNAME_Otros, Otros);
	}

	/** Get Others.
		@return Others	  */
	public BigDecimal getOtros () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Otros);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Part. Even. Acad..
		@param Part_Even_Acad 
		Part. Even. Acad.
	  */
	public void setPart_Even_Acad (BigDecimal Part_Even_Acad)
	{
		set_Value (COLUMNNAME_Part_Even_Acad, Part_Even_Acad);
	}

	/** Get Part. Even. Acad..
		@return Part. Even. Acad.
	  */
	public BigDecimal getPart_Even_Acad () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Part_Even_Acad);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Persons.
		@param Personas 
		Persons
	  */
	public void setPersonas (BigDecimal Personas)
	{
		set_Value (COLUMNNAME_Personas, Personas);
	}

	/** Get Persons.
		@return Persons
	  */
	public BigDecimal getPersonas () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Personas);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Present. Cases Chief S.W..
		@param Present_Casos_Jefe_TS 
		Present. Cases Chief S.W.
	  */
	public void setPresent_Casos_Jefe_TS (BigDecimal Present_Casos_Jefe_TS)
	{
		set_Value (COLUMNNAME_Present_Casos_Jefe_TS, Present_Casos_Jefe_TS);
	}

	/** Get Present. Cases Chief S.W..
		@return Present. Cases Chief S.W.
	  */
	public BigDecimal getPresent_Casos_Jefe_TS () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Present_Casos_Jefe_TS);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Recor. S.C..
		@param Recor_SC 
		Recor. S.C.
	  */
	public void setRecor_SC (BigDecimal Recor_SC)
	{
		set_Value (COLUMNNAME_Recor_SC, Recor_SC);
	}

	/** Get Recor. S.C..
		@return Recor. S.C.
	  */
	public BigDecimal getRecor_SC () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Recor_SC);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Reg. Inf. Mens. Conc..
		@param Reg_Inf_Mens_Conc 
		Reg. Inf. Mens. Conc.
	  */
	public void setReg_Inf_Mens_Conc (BigDecimal Reg_Inf_Mens_Conc)
	{
		set_Value (COLUMNNAME_Reg_Inf_Mens_Conc, Reg_Inf_Mens_Conc);
	}

	/** Get Reg. Inf. Mens. Conc..
		@return Reg. Inf. Mens. Conc.
	  */
	public BigDecimal getReg_Inf_Mens_Conc () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Reg_Inf_Mens_Conc);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Reg. Inf. Mens. Ind..
		@param Reg_Inf_Mens_Ind 
		Reg. Inf. Mens. Ind.
	  */
	public void setReg_Inf_Mens_Ind (BigDecimal Reg_Inf_Mens_Ind)
	{
		set_Value (COLUMNNAME_Reg_Inf_Mens_Ind, Reg_Inf_Mens_Ind);
	}

	/** Get Reg. Inf. Mens. Ind..
		@return Reg. Inf. Mens. Ind.
	  */
	public BigDecimal getReg_Inf_Mens_Ind () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Reg_Inf_Mens_Ind);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Report. Recor. S.C..
		@param Report_Recor_SC 
		Report. Recor. S.C.
	  */
	public void setReport_Recor_SC (BigDecimal Report_Recor_SC)
	{
		set_Value (COLUMNNAME_Report_Recor_SC, Report_Recor_SC);
	}

	/** Get Report. Recor. S.C..
		@return Report. Recor. S.C.
	  */
	public BigDecimal getReport_Recor_SC () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Report_Recor_SC);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Comp. Meeting.
		@param Reun_Comp 
		Comp. Meeting
	  */
	public void setReun_Comp (BigDecimal Reun_Comp)
	{
		set_Value (COLUMNNAME_Reun_Comp, Reun_Comp);
	}

	/** Get Comp. Meeting.
		@return Comp. Meeting
	  */
	public BigDecimal getReun_Comp () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Reun_Comp);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Service Mgr Meeting.
		@param Reunion_Jef_Serv 
		Service Mgr Meeting
	  */
	public void setReunion_Jef_Serv (BigDecimal Reunion_Jef_Serv)
	{
		set_Value (COLUMNNAME_Reunion_Jef_Serv, Reunion_Jef_Serv);
	}

	/** Get Service Mgr Meeting.
		@return Service Mgr Meeting
	  */
	public BigDecimal getReunion_Jef_Serv () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Reunion_Jef_Serv);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Revalidation Case S.W..
		@param Rev_Casos_TS 
		Revalidation Case S.W.
	  */
	public void setRev_Casos_TS (BigDecimal Rev_Casos_TS)
	{
		set_Value (COLUMNNAME_Rev_Casos_TS, Rev_Casos_TS);
	}

	/** Get Revalidation Case S.W..
		@return Revalidation Case S.W.
	  */
	public BigDecimal getRev_Casos_TS () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Rev_Casos_TS);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Supervisor.
		@param Supervisora 
		Supervisor
	  */
	public void setSupervisora (String Supervisora)
	{
		set_Value (COLUMNNAME_Supervisora, Supervisora);
	}

	/** Get Supervisor.
		@return Supervisor
	  */
	public String getSupervisora () 
	{
		return (String)get_Value(COLUMNNAME_Supervisora);
	}

	/** Set Sup. Group. with Chief T.S..
		@param Sup_Grup_con_Jefe_TS 
		Sup. Group. with Chief T.S.
	  */
	public void setSup_Grup_con_Jefe_TS (BigDecimal Sup_Grup_con_Jefe_TS)
	{
		set_Value (COLUMNNAME_Sup_Grup_con_Jefe_TS, Sup_Grup_con_Jefe_TS);
	}

	/** Get Sup. Group. with Chief T.S..
		@return Sup. Group. with Chief T.S.
	  */
	public BigDecimal getSup_Grup_con_Jefe_TS () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Sup_Grup_con_Jefe_TS);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Sup. Group. T.S. Mgr..
		@param Sup_Grup_TS_Gest 
		Sup. Group. T.S. Mgr.
	  */
	public void setSup_Grup_TS_Gest (BigDecimal Sup_Grup_TS_Gest)
	{
		set_Value (COLUMNNAME_Sup_Grup_TS_Gest, Sup_Grup_TS_Gest);
	}

	/** Get Sup. Group. T.S. Mgr..
		@return Sup. Group. T.S. Mgr.
	  */
	public BigDecimal getSup_Grup_TS_Gest () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Sup_Grup_TS_Gest);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Sup. Ind. with Chief T.S..
		@param Sup_Ind_con_Jefe_TS 
		Sup. Ind. with Chief T.S.
	  */
	public void setSup_Ind_con_Jefe_TS (BigDecimal Sup_Ind_con_Jefe_TS)
	{
		set_Value (COLUMNNAME_Sup_Ind_con_Jefe_TS, Sup_Ind_con_Jefe_TS);
	}

	/** Get Sup. Ind. with Chief T.S..
		@return Sup. Ind. with Chief T.S.
	  */
	public BigDecimal getSup_Ind_con_Jefe_TS () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Sup_Ind_con_Jefe_TS);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Sup. Ind. T.S. Mgr..
		@param Sup_Ind_TS_Gest 
		Sup. Ind. T.S. Mgr.
	  */
	public void setSup_Ind_TS_Gest (BigDecimal Sup_Ind_TS_Gest)
	{
		set_Value (COLUMNNAME_Sup_Ind_TS_Gest, Sup_Ind_TS_Gest);
	}

	/** Get Sup. Ind. T.S. Mgr..
		@return Sup. Ind. T.S. Mgr.
	  */
	public BigDecimal getSup_Ind_TS_Gest () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Sup_Ind_TS_Gest);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}
}