/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_Reg_EduSalud
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_Reg_EduSalud 
{

    /** TableName=EXME_Reg_EduSalud */
    public static final String Table_Name = "EXME_Reg_EduSalud";

    /** AD_Table_ID=1200024 */
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

    /** Column name Area_De_Int */
    public static final String COLUMNNAME_Area_De_Int = "Area_De_Int";

	/** Set Interest Area.
	  * Interest Area
	  */
	public void setArea_De_Int (String Area_De_Int);

	/** Get Interest Area.
	  * Interest Area
	  */
	public String getArea_De_Int();

    /** Column name Aseso_A_Est_y_Pas */
    public static final String COLUMNNAME_Aseso_A_Est_y_Pas = "Aseso_A_Est_y_Pas";

	/** Set Access  Est.  Pas..
	  * Access  Est.  Pas.
	  */
	public void setAseso_A_Est_y_Pas (BigDecimal Aseso_A_Est_y_Pas);

	/** Get Access  Est.  Pas..
	  * Access  Est.  Pas.
	  */
	public BigDecimal getAseso_A_Est_y_Pas();

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

    /** Column name Coord_Cur_y_Tall */
    public static final String COLUMNNAME_Coord_Cur_y_Tall = "Coord_Cur_y_Tall";

	/** Set Coord. Courses. and workshop.
	  * Coord. Courses. and workshop
	  */
	public void setCoord_Cur_y_Tall (BigDecimal Coord_Cur_y_Tall);

	/** Get Coord. Courses. and workshop.
	  * Coord. Courses. and workshop
	  */
	public BigDecimal getCoord_Cur_y_Tall();

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

    /** Column name Coord_Intra */
    public static final String COLUMNNAME_Coord_Intra = "Coord_Intra";

	/** Set Coord. Intra..
	  * Coord. Intra.
	  */
	public void setCoord_Intra (BigDecimal Coord_Intra);

	/** Get Coord. Intra..
	  * Coord. Intra.
	  */
	public BigDecimal getCoord_Intra();

    /** Column name Coord_Platicas */
    public static final String COLUMNNAME_Coord_Platicas = "Coord_Platicas";

	/** Set Coord. talks.
	  * Coord. talks
	  */
	public void setCoord_Platicas (BigDecimal Coord_Platicas);

	/** Get Coord. talks.
	  * Coord. talks
	  */
	public BigDecimal getCoord_Platicas();

    /** Column name Coord_Proy_Videos */
    public static final String COLUMNNAME_Coord_Proy_Videos = "Coord_Proy_Videos";

	/** Set Coord. Proyection  Videos.
	  * Coord. Proyection Videos
	  */
	public void setCoord_Proy_Videos (BigDecimal Coord_Proy_Videos);

	/** Get Coord. Proyection  Videos.
	  * Coord. Proyection Videos
	  */
	public BigDecimal getCoord_Proy_Videos();

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

    /** Column name Elab_Gest_De_Doctos */
    public static final String COLUMNNAME_Elab_Gest_De_Doctos = "Elab_Gest_De_Doctos";

	/** Set Make Documentation.
	  * Make Documentation
	  */
	public void setElab_Gest_De_Doctos (BigDecimal Elab_Gest_De_Doctos);

	/** Get Make Documentation.
	  * Make Documentation
	  */
	public BigDecimal getElab_Gest_De_Doctos();

    /** Column name Ent_Med_Soc */
    public static final String COLUMNNAME_Ent_Med_Soc = "Ent_Med_Soc";

	/** Set Ent. Med. Soc.
	  * Ent. Med. Soc
	  */
	public void setEnt_Med_Soc (BigDecimal Ent_Med_Soc);

	/** Get Ent. Med. Soc.
	  * Ent. Med. Soc
	  */
	public BigDecimal getEnt_Med_Soc();

    /** Column name Est_A */
    public static final String COLUMNNAME_Est_A = "Est_A";

	/** Set Est. A.
	  * Est. A
	  */
	public void setEst_A (BigDecimal Est_A);

	/** Get Est. A.
	  * Est. A
	  */
	public BigDecimal getEst_A();

    /** Column name Est_I */
    public static final String COLUMNNAME_Est_I = "Est_I";

	/** Set Est. I.
	  * Est. I
	  */
	public void setEst_I (BigDecimal Est_I);

	/** Get Est. I.
	  * Est. I
	  */
	public BigDecimal getEst_I();

    /** Column name Est_R */
    public static final String COLUMNNAME_Est_R = "Est_R";

	/** Set Est. R.
	  * Est. R
	  */
	public void setEst_R (BigDecimal Est_R);

	/** Get Est. R.
	  * Est. R
	  */
	public BigDecimal getEst_R();

    /** Column name EXME_MO_Platicas_ID */
    public static final String COLUMNNAME_EXME_MO_Platicas_ID = "EXME_MO_Platicas_ID";

	/** Set Talk.
	  * Talk  by Speciality
	  */
	public void setEXME_MO_Platicas_ID (BigDecimal EXME_MO_Platicas_ID);

	/** Get Talk.
	  * Talk  by Speciality
	  */
	public BigDecimal getEXME_MO_Platicas_ID();

    /** Column name EXME_Reg_EduSalud_ID */
    public static final String COLUMNNAME_EXME_Reg_EduSalud_ID = "EXME_Reg_EduSalud_ID";

	/** Set Daily Teaching Registry..
	  * Daily Teaching Registry.
	  */
	public void setEXME_Reg_EduSalud_ID (int EXME_Reg_EduSalud_ID);

	/** Get Daily Teaching Registry..
	  * Daily Teaching Registry.
	  */
	public int getEXME_Reg_EduSalud_ID();

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

    /** Column name Hora_CTRL */
    public static final String COLUMNNAME_Hora_CTRL = "Hora_CTRL";

	/** Set Time CTRL	  */
	public void setHora_CTRL (BigDecimal Hora_CTRL);

	/** Get Time CTRL	  */
	public BigDecimal getHora_CTRL();

    /** Column name Inf_Grupos */
    public static final String COLUMNNAME_Inf_Grupos = "Inf_Grupos";

	/** Set Inf. Grupos.
	  * Inf. Grupos
	  */
	public void setInf_Grupos (BigDecimal Inf_Grupos);

	/** Get Inf. Grupos.
	  * Inf. Grupos
	  */
	public BigDecimal getInf_Grupos();

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

    /** Column name Mater_Didact */
    public static final String COLUMNNAME_Mater_Didact = "Mater_Didact";

	/** Set Didactic Material.
	  * Didactic Material
	  */
	public void setMater_Didact (BigDecimal Mater_Didact);

	/** Get Didactic Material.
	  * Didactic Material
	  */
	public BigDecimal getMater_Didact();

    /** Column name Minu_Trab */
    public static final String COLUMNNAME_Minu_Trab = "Minu_Trab";

	/** Set Minu. Work.
	  * Minu. Work
	  */
	public void setMinu_Trab (BigDecimal Minu_Trab);

	/** Get Minu. Work.
	  * Minu. Work
	  */
	public BigDecimal getMinu_Trab();

    /** Column name Nombre_TS */
    public static final String COLUMNNAME_Nombre_TS = "Nombre_TS";

	/** Set Name S.W..
	  * Name S.W.
	  */
	public void setNombre_TS (String Nombre_TS);

	/** Get Name S.W..
	  * Name S.W.
	  */
	public String getNombre_TS();

    /** Column name Notas_I */
    public static final String COLUMNNAME_Notas_I = "Notas_I";

	/** Set Notes I..
	  * Notes I.
	  */
	public void setNotas_I (BigDecimal Notas_I);

	/** Get Notes I..
	  * Notes I.
	  */
	public BigDecimal getNotas_I();

    /** Column name Notas_S */
    public static final String COLUMNNAME_Notas_S = "Notas_S";

	/** Set Notes S..
	  * Notes S.
	  */
	public void setNotas_S (BigDecimal Notas_S);

	/** Get Notes S..
	  * Notes S.
	  */
	public BigDecimal getNotas_S();

    /** Column name Org_Mat_Didact */
    public static final String COLUMNNAME_Org_Mat_Didact = "Org_Mat_Didact";

	/** Set Org. Mat. Didact..
	  * Org. Mat. Didact.
	  */
	public void setOrg_Mat_Didact (BigDecimal Org_Mat_Didact);

	/** Get Org. Mat. Didact..
	  * Org. Mat. Didact.
	  */
	public BigDecimal getOrg_Mat_Didact();

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

    /** Column name Recorridos_Pac_Tb */
    public static final String COLUMNNAME_Recorridos_Pac_Tb = "Recorridos_Pac_Tb";

	/** Set Run Pac. Tb..
	  * Run Pac. Tb.
	  */
	public void setRecorridos_Pac_Tb (BigDecimal Recorridos_Pac_Tb);

	/** Get Run Pac. Tb..
	  * Run Pac. Tb.
	  */
	public BigDecimal getRecorridos_Pac_Tb();

    /** Column name Ref_Tb */
    public static final String COLUMNNAME_Ref_Tb = "Ref_Tb";

	/** Set Ref. Tb..
	  * Ref. Tb.
	  */
	public void setRef_Tb (BigDecimal Ref_Tb);

	/** Get Ref. Tb..
	  * Ref. Tb.
	  */
	public BigDecimal getRef_Tb();

    /** Column name Ref_1_Niv */
    public static final String COLUMNNAME_Ref_1_Niv = "Ref_1_Niv";

	/** Set Reference Nivel 1.
	  * Reference Nivel 1
	  */
	public void setRef_1_Niv (BigDecimal Ref_1_Niv);

	/** Get Reference Nivel 1.
	  * Reference Nivel 1
	  */
	public BigDecimal getRef_1_Niv();

    /** Column name Ref_2_Niv */
    public static final String COLUMNNAME_Ref_2_Niv = "Ref_2_Niv";

	/** Set Reference Nivel 2.
	  * Reference Nivel 2.
	  */
	public void setRef_2_Niv (BigDecimal Ref_2_Niv);

	/** Get Reference Nivel 2.
	  * Reference Nivel 2.
	  */
	public BigDecimal getRef_2_Niv();

    /** Column name Ref_3_Niv */
    public static final String COLUMNNAME_Ref_3_Niv = "Ref_3_Niv";

	/** Set Reference Nivel 3.
	  * Reference Nivel 3
	  */
	public void setRef_3_Niv (BigDecimal Ref_3_Niv);

	/** Get Reference Nivel 3.
	  * Reference Nivel 3
	  */
	public BigDecimal getRef_3_Niv();

    /** Column name Reg_Diar */
    public static final String COLUMNNAME_Reg_Diar = "Reg_Diar";

	/** Set Daily Record.
	  * Daily Record
	  */
	public void setReg_Diar (BigDecimal Reg_Diar);

	/** Get Daily Record.
	  * Daily Record
	  */
	public BigDecimal getReg_Diar();

    /** Column name Reun_Con_Equip_Sal */
    public static final String COLUMNNAME_Reun_Con_Equip_Sal = "Reun_Con_Equip_Sal";

	/** Set Sal Team Meeting..
	  * Sal Team Meeting.
	  */
	public void setReun_Con_Equip_Sal (BigDecimal Reun_Con_Equip_Sal);

	/** Get Sal Team Meeting..
	  * Sal Team Meeting.
	  */
	public BigDecimal getReun_Con_Equip_Sal();

    /** Column name Rev_Biblio_y_Mat_Educ */
    public static final String COLUMNNAME_Rev_Biblio_y_Mat_Educ = "Rev_Biblio_y_Mat_Educ";

	/** Set Library Review & Educ. Mat..
	  * Library Review & Educ. Mat.
	  */
	public void setRev_Biblio_y_Mat_Educ (BigDecimal Rev_Biblio_y_Mat_Educ);

	/** Get Library Review & Educ. Mat..
	  * Library Review & Educ. Mat.
	  */
	public BigDecimal getRev_Biblio_y_Mat_Educ();

    /** Column name Servicio */
    public static final String COLUMNNAME_Servicio = "Servicio";

	/** Set Service.
	  * Service
	  */
	public void setServicio (String Servicio);

	/** Get Service.
	  * Service
	  */
	public String getServicio();

    /** Column name Sup_Grup */
    public static final String COLUMNNAME_Sup_Grup = "Sup_Grup";

	/** Set Sup. Group.
	  * Sup. Group
	  */
	public void setSup_Grup (BigDecimal Sup_Grup);

	/** Get Sup. Group.
	  * Sup. Group
	  */
	public BigDecimal getSup_Grup();

    /** Column name Sup_Ind */
    public static final String COLUMNNAME_Sup_Ind = "Sup_Ind";

	/** Set Sup. Ind..
	  * Sup. Ind.
	  */
	public void setSup_Ind (BigDecimal Sup_Ind);

	/** Get Sup. Ind..
	  * Sup. Ind.
	  */
	public BigDecimal getSup_Ind();

    /** Column name Vis_Equip_Sal */
    public static final String COLUMNNAME_Vis_Equip_Sal = "Vis_Equip_Sal";

	/** Set Vis. Equip. Sal.
	  * Vis. Equip. Sal
	  */
	public void setVis_Equip_Sal (BigDecimal Vis_Equip_Sal);

	/** Get Vis. Equip. Sal.
	  * Vis. Equip. Sal
	  */
	public BigDecimal getVis_Equip_Sal();

    /** Column name Vis_TS */
    public static final String COLUMNNAME_Vis_TS = "Vis_TS";

	/** Set Vis. T.S..
	  * Vis. T.S.
	  */
	public void setVis_TS (BigDecimal Vis_TS);

	/** Get Vis. T.S..
	  * Vis. T.S.
	  */
	public BigDecimal getVis_TS();
}
