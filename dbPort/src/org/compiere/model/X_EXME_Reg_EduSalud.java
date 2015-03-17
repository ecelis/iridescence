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

/** Generated Model for EXME_Reg_EduSalud
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_Reg_EduSalud extends PO implements I_EXME_Reg_EduSalud, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_Reg_EduSalud (Properties ctx, int EXME_Reg_EduSalud_ID, String trxName)
    {
      super (ctx, EXME_Reg_EduSalud_ID, trxName);
      /** if (EXME_Reg_EduSalud_ID == 0)
        {
			setEXME_Reg_EduSalud_ID (0);
			setFecha_Ctrl (new Timestamp( System.currentTimeMillis() ));
// @Date@
        } */
    }

    /** Load Constructor */
    public X_EXME_Reg_EduSalud (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_Reg_EduSalud[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Interest Area.
		@param Area_De_Int 
		Interest Area
	  */
	public void setArea_De_Int (String Area_De_Int)
	{
		set_Value (COLUMNNAME_Area_De_Int, Area_De_Int);
	}

	/** Get Interest Area.
		@return Interest Area
	  */
	public String getArea_De_Int () 
	{
		return (String)get_Value(COLUMNNAME_Area_De_Int);
	}

	/** Set Access  Est.  Pas..
		@param Aseso_A_Est_y_Pas 
		Access  Est.  Pas.
	  */
	public void setAseso_A_Est_y_Pas (BigDecimal Aseso_A_Est_y_Pas)
	{
		set_Value (COLUMNNAME_Aseso_A_Est_y_Pas, Aseso_A_Est_y_Pas);
	}

	/** Get Access  Est.  Pas..
		@return Access  Est.  Pas.
	  */
	public BigDecimal getAseso_A_Est_y_Pas () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Aseso_A_Est_y_Pas);
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

	/** Set Coord. Courses. and workshop.
		@param Coord_Cur_y_Tall 
		Coord. Courses. and workshop
	  */
	public void setCoord_Cur_y_Tall (BigDecimal Coord_Cur_y_Tall)
	{
		set_Value (COLUMNNAME_Coord_Cur_y_Tall, Coord_Cur_y_Tall);
	}

	/** Get Coord. Courses. and workshop.
		@return Coord. Courses. and workshop
	  */
	public BigDecimal getCoord_Cur_y_Tall () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Coord_Cur_y_Tall);
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

	/** Set Coord. Intra..
		@param Coord_Intra 
		Coord. Intra.
	  */
	public void setCoord_Intra (BigDecimal Coord_Intra)
	{
		set_Value (COLUMNNAME_Coord_Intra, Coord_Intra);
	}

	/** Get Coord. Intra..
		@return Coord. Intra.
	  */
	public BigDecimal getCoord_Intra () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Coord_Intra);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Coord. talks.
		@param Coord_Platicas 
		Coord. talks
	  */
	public void setCoord_Platicas (BigDecimal Coord_Platicas)
	{
		set_Value (COLUMNNAME_Coord_Platicas, Coord_Platicas);
	}

	/** Get Coord. talks.
		@return Coord. talks
	  */
	public BigDecimal getCoord_Platicas () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Coord_Platicas);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Coord. Proyection  Videos.
		@param Coord_Proy_Videos 
		Coord. Proyection Videos
	  */
	public void setCoord_Proy_Videos (BigDecimal Coord_Proy_Videos)
	{
		set_Value (COLUMNNAME_Coord_Proy_Videos, Coord_Proy_Videos);
	}

	/** Get Coord. Proyection  Videos.
		@return Coord. Proyection Videos
	  */
	public BigDecimal getCoord_Proy_Videos () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Coord_Proy_Videos);
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

	/** Set Make Documentation.
		@param Elab_Gest_De_Doctos 
		Make Documentation
	  */
	public void setElab_Gest_De_Doctos (BigDecimal Elab_Gest_De_Doctos)
	{
		set_Value (COLUMNNAME_Elab_Gest_De_Doctos, Elab_Gest_De_Doctos);
	}

	/** Get Make Documentation.
		@return Make Documentation
	  */
	public BigDecimal getElab_Gest_De_Doctos () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Elab_Gest_De_Doctos);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Ent. Med. Soc.
		@param Ent_Med_Soc 
		Ent. Med. Soc
	  */
	public void setEnt_Med_Soc (BigDecimal Ent_Med_Soc)
	{
		set_Value (COLUMNNAME_Ent_Med_Soc, Ent_Med_Soc);
	}

	/** Get Ent. Med. Soc.
		@return Ent. Med. Soc
	  */
	public BigDecimal getEnt_Med_Soc () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Ent_Med_Soc);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Est. A.
		@param Est_A 
		Est. A
	  */
	public void setEst_A (BigDecimal Est_A)
	{
		set_Value (COLUMNNAME_Est_A, Est_A);
	}

	/** Get Est. A.
		@return Est. A
	  */
	public BigDecimal getEst_A () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Est_A);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Est. I.
		@param Est_I 
		Est. I
	  */
	public void setEst_I (BigDecimal Est_I)
	{
		set_Value (COLUMNNAME_Est_I, Est_I);
	}

	/** Get Est. I.
		@return Est. I
	  */
	public BigDecimal getEst_I () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Est_I);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Est. R.
		@param Est_R 
		Est. R
	  */
	public void setEst_R (BigDecimal Est_R)
	{
		set_Value (COLUMNNAME_Est_R, Est_R);
	}

	/** Get Est. R.
		@return Est. R
	  */
	public BigDecimal getEst_R () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Est_R);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Talk.
		@param EXME_MO_Platicas_ID 
		Talk  by Speciality
	  */
	public void setEXME_MO_Platicas_ID (BigDecimal EXME_MO_Platicas_ID)
	{
		set_Value (COLUMNNAME_EXME_MO_Platicas_ID, EXME_MO_Platicas_ID);
	}

	/** Get Talk.
		@return Talk  by Speciality
	  */
	public BigDecimal getEXME_MO_Platicas_ID () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_EXME_MO_Platicas_ID);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Daily Teaching Registry..
		@param EXME_Reg_EduSalud_ID 
		Daily Teaching Registry.
	  */
	public void setEXME_Reg_EduSalud_ID (int EXME_Reg_EduSalud_ID)
	{
		if (EXME_Reg_EduSalud_ID < 1)
			 throw new IllegalArgumentException ("EXME_Reg_EduSalud_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_Reg_EduSalud_ID, Integer.valueOf(EXME_Reg_EduSalud_ID));
	}

	/** Get Daily Teaching Registry..
		@return Daily Teaching Registry.
	  */
	public int getEXME_Reg_EduSalud_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Reg_EduSalud_ID);
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

	/** Set Time CTRL.
		@param Hora_CTRL Time CTRL	  */
	public void setHora_CTRL (BigDecimal Hora_CTRL)
	{
		set_Value (COLUMNNAME_Hora_CTRL, Hora_CTRL);
	}

	/** Get Time CTRL.
		@return Time CTRL	  */
	public BigDecimal getHora_CTRL () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Hora_CTRL);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Inf. Grupos.
		@param Inf_Grupos 
		Inf. Grupos
	  */
	public void setInf_Grupos (BigDecimal Inf_Grupos)
	{
		set_Value (COLUMNNAME_Inf_Grupos, Inf_Grupos);
	}

	/** Get Inf. Grupos.
		@return Inf. Grupos
	  */
	public BigDecimal getInf_Grupos () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Inf_Grupos);
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

	/** Set Didactic Material.
		@param Mater_Didact 
		Didactic Material
	  */
	public void setMater_Didact (BigDecimal Mater_Didact)
	{
		set_Value (COLUMNNAME_Mater_Didact, Mater_Didact);
	}

	/** Get Didactic Material.
		@return Didactic Material
	  */
	public BigDecimal getMater_Didact () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Mater_Didact);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Minu. Work.
		@param Minu_Trab 
		Minu. Work
	  */
	public void setMinu_Trab (BigDecimal Minu_Trab)
	{
		set_Value (COLUMNNAME_Minu_Trab, Minu_Trab);
	}

	/** Get Minu. Work.
		@return Minu. Work
	  */
	public BigDecimal getMinu_Trab () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Minu_Trab);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Nombre_TS AD_Reference_ID=316 */
	public static final int NOMBRE_TS_AD_Reference_ID=316;
	/** Set Name S.W..
		@param Nombre_TS 
		Name S.W.
	  */
	public void setNombre_TS (String Nombre_TS)
	{
		set_Value (COLUMNNAME_Nombre_TS, Nombre_TS);
	}

	/** Get Name S.W..
		@return Name S.W.
	  */
	public String getNombre_TS () 
	{
		return (String)get_Value(COLUMNNAME_Nombre_TS);
	}

	/** Set Notes I..
		@param Notas_I 
		Notes I.
	  */
	public void setNotas_I (BigDecimal Notas_I)
	{
		set_Value (COLUMNNAME_Notas_I, Notas_I);
	}

	/** Get Notes I..
		@return Notes I.
	  */
	public BigDecimal getNotas_I () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Notas_I);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Notes S..
		@param Notas_S 
		Notes S.
	  */
	public void setNotas_S (BigDecimal Notas_S)
	{
		set_Value (COLUMNNAME_Notas_S, Notas_S);
	}

	/** Get Notes S..
		@return Notes S.
	  */
	public BigDecimal getNotas_S () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Notas_S);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Org. Mat. Didact..
		@param Org_Mat_Didact 
		Org. Mat. Didact.
	  */
	public void setOrg_Mat_Didact (BigDecimal Org_Mat_Didact)
	{
		set_Value (COLUMNNAME_Org_Mat_Didact, Org_Mat_Didact);
	}

	/** Get Org. Mat. Didact..
		@return Org. Mat. Didact.
	  */
	public BigDecimal getOrg_Mat_Didact () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Org_Mat_Didact);
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

	/** Set Run Pac. Tb..
		@param Recorridos_Pac_Tb 
		Run Pac. Tb.
	  */
	public void setRecorridos_Pac_Tb (BigDecimal Recorridos_Pac_Tb)
	{
		set_Value (COLUMNNAME_Recorridos_Pac_Tb, Recorridos_Pac_Tb);
	}

	/** Get Run Pac. Tb..
		@return Run Pac. Tb.
	  */
	public BigDecimal getRecorridos_Pac_Tb () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Recorridos_Pac_Tb);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Ref. Tb..
		@param Ref_Tb 
		Ref. Tb.
	  */
	public void setRef_Tb (BigDecimal Ref_Tb)
	{
		set_Value (COLUMNNAME_Ref_Tb, Ref_Tb);
	}

	/** Get Ref. Tb..
		@return Ref. Tb.
	  */
	public BigDecimal getRef_Tb () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Ref_Tb);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Reference Nivel 1.
		@param Ref_1_Niv 
		Reference Nivel 1
	  */
	public void setRef_1_Niv (BigDecimal Ref_1_Niv)
	{
		set_Value (COLUMNNAME_Ref_1_Niv, Ref_1_Niv);
	}

	/** Get Reference Nivel 1.
		@return Reference Nivel 1
	  */
	public BigDecimal getRef_1_Niv () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Ref_1_Niv);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Reference Nivel 2.
		@param Ref_2_Niv 
		Reference Nivel 2.
	  */
	public void setRef_2_Niv (BigDecimal Ref_2_Niv)
	{
		set_Value (COLUMNNAME_Ref_2_Niv, Ref_2_Niv);
	}

	/** Get Reference Nivel 2.
		@return Reference Nivel 2.
	  */
	public BigDecimal getRef_2_Niv () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Ref_2_Niv);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Reference Nivel 3.
		@param Ref_3_Niv 
		Reference Nivel 3
	  */
	public void setRef_3_Niv (BigDecimal Ref_3_Niv)
	{
		set_Value (COLUMNNAME_Ref_3_Niv, Ref_3_Niv);
	}

	/** Get Reference Nivel 3.
		@return Reference Nivel 3
	  */
	public BigDecimal getRef_3_Niv () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Ref_3_Niv);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Daily Record.
		@param Reg_Diar 
		Daily Record
	  */
	public void setReg_Diar (BigDecimal Reg_Diar)
	{
		set_Value (COLUMNNAME_Reg_Diar, Reg_Diar);
	}

	/** Get Daily Record.
		@return Daily Record
	  */
	public BigDecimal getReg_Diar () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Reg_Diar);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Sal Team Meeting..
		@param Reun_Con_Equip_Sal 
		Sal Team Meeting.
	  */
	public void setReun_Con_Equip_Sal (BigDecimal Reun_Con_Equip_Sal)
	{
		set_Value (COLUMNNAME_Reun_Con_Equip_Sal, Reun_Con_Equip_Sal);
	}

	/** Get Sal Team Meeting..
		@return Sal Team Meeting.
	  */
	public BigDecimal getReun_Con_Equip_Sal () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Reun_Con_Equip_Sal);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Library Review & Educ. Mat..
		@param Rev_Biblio_y_Mat_Educ 
		Library Review & Educ. Mat.
	  */
	public void setRev_Biblio_y_Mat_Educ (BigDecimal Rev_Biblio_y_Mat_Educ)
	{
		set_Value (COLUMNNAME_Rev_Biblio_y_Mat_Educ, Rev_Biblio_y_Mat_Educ);
	}

	/** Get Library Review & Educ. Mat..
		@return Library Review & Educ. Mat.
	  */
	public BigDecimal getRev_Biblio_y_Mat_Educ () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Rev_Biblio_y_Mat_Educ);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Service.
		@param Servicio 
		Service
	  */
	public void setServicio (String Servicio)
	{
		set_Value (COLUMNNAME_Servicio, Servicio);
	}

	/** Get Service.
		@return Service
	  */
	public String getServicio () 
	{
		return (String)get_Value(COLUMNNAME_Servicio);
	}

	/** Set Sup. Group.
		@param Sup_Grup 
		Sup. Group
	  */
	public void setSup_Grup (BigDecimal Sup_Grup)
	{
		set_Value (COLUMNNAME_Sup_Grup, Sup_Grup);
	}

	/** Get Sup. Group.
		@return Sup. Group
	  */
	public BigDecimal getSup_Grup () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Sup_Grup);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Sup. Ind..
		@param Sup_Ind 
		Sup. Ind.
	  */
	public void setSup_Ind (BigDecimal Sup_Ind)
	{
		set_Value (COLUMNNAME_Sup_Ind, Sup_Ind);
	}

	/** Get Sup. Ind..
		@return Sup. Ind.
	  */
	public BigDecimal getSup_Ind () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Sup_Ind);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Vis. Equip. Sal.
		@param Vis_Equip_Sal 
		Vis. Equip. Sal
	  */
	public void setVis_Equip_Sal (BigDecimal Vis_Equip_Sal)
	{
		set_Value (COLUMNNAME_Vis_Equip_Sal, Vis_Equip_Sal);
	}

	/** Get Vis. Equip. Sal.
		@return Vis. Equip. Sal
	  */
	public BigDecimal getVis_Equip_Sal () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Vis_Equip_Sal);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Vis. T.S..
		@param Vis_TS 
		Vis. T.S.
	  */
	public void setVis_TS (BigDecimal Vis_TS)
	{
		set_Value (COLUMNNAME_Vis_TS, Vis_TS);
	}

	/** Get Vis. T.S..
		@return Vis. T.S.
	  */
	public BigDecimal getVis_TS () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Vis_TS);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}
}