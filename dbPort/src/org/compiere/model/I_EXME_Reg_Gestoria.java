/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_Reg_Gestoria
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_Reg_Gestoria 
{

    /** TableName=EXME_Reg_Gestoria */
    public static final String Table_Name = "EXME_Reg_Gestoria";

    /** AD_Table_ID=1200025 */
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

    /** Column name Estu_Pac */
    public static final String COLUMNNAME_Estu_Pac = "Estu_Pac";

	/** Set Patient Status.
	  * Patient Status
	  */
	public void setEstu_Pac (BigDecimal Estu_Pac);

	/** Get Patient Status.
	  * Patient Status
	  */
	public BigDecimal getEstu_Pac();

    /** Column name Estu_Tram */
    public static final String COLUMNNAME_Estu_Tram = "Estu_Tram";

	/** Set Estu. Tram..
	  * Estu. Tram.
	  */
	public void setEstu_Tram (BigDecimal Estu_Tram);

	/** Get Estu. Tram..
	  * Estu. Tram.
	  */
	public BigDecimal getEstu_Tram();

    /** Column name EXME_Cama_ID */
    public static final String COLUMNNAME_EXME_Cama_ID = "EXME_Cama_ID";

	/** Set Bed.
	  * Bed
	  */
	public void setEXME_Cama_ID (int EXME_Cama_ID);

	/** Get Bed.
	  * Bed
	  */
	public int getEXME_Cama_ID();

    /** Column name EXME_Paciente_ID */
    public static final String COLUMNNAME_EXME_Paciente_ID = "EXME_Paciente_ID";

	/** Set Patient.
	  * Patient
	  */
	public void setEXME_Paciente_ID (int EXME_Paciente_ID);

	/** Get Patient.
	  * Patient
	  */
	public int getEXME_Paciente_ID();

    /** Column name EXME_Reg_Gestoria_ID */
    public static final String COLUMNNAME_EXME_Reg_Gestoria_ID = "EXME_Reg_Gestoria_ID";

	/** Set Daily registry of Agency.
	  * Daily registry of Agency
	  */
	public void setEXME_Reg_Gestoria_ID (int EXME_Reg_Gestoria_ID);

	/** Get Daily registry of Agency.
	  * Daily registry of Agency
	  */
	public int getEXME_Reg_Gestoria_ID();

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

    /** Column name Fibrosis_Quis */
    public static final String COLUMNNAME_Fibrosis_Quis = "Fibrosis_Quis";

	/** Set Quistic Fibrosis.
	  * Quistic Fibrosis
	  */
	public void setFibrosis_Quis (BigDecimal Fibrosis_Quis);

	/** Get Quistic Fibrosis.
	  * Quistic Fibrosis
	  */
	public BigDecimal getFibrosis_Quis();

    /** Column name Gea_Glz */
    public static final String COLUMNNAME_Gea_Glz = "Gea_Glz";

	/** Set Gea. Glz..
	  * Gea. Glz.
	  */
	public void setGea_Glz (BigDecimal Gea_Glz);

	/** Get Gea. Glz..
	  * Gea. Glz.
	  */
	public BigDecimal getGea_Glz();

    /** Column name Gestor */
    public static final String COLUMNNAME_Gestor = "Gestor";

	/** Set Adminstrator.
	  * Administrator
	  */
	public void setGestor (String Gestor);

	/** Get Adminstrator.
	  * Administrator
	  */
	public String getGestor();

    /** Column name HG_Juarez */
    public static final String COLUMNNAME_HG_Juarez = "HG_Juarez";

	/** Set H.G. Juarez.
	  * H.G. Juarez
	  */
	public void setHG_Juarez (BigDecimal HG_Juarez);

	/** Get H.G. Juarez.
	  * H.G. Juarez
	  */
	public BigDecimal getHG_Juarez();

    /** Column name INCan */
    public static final String COLUMNNAME_INCan = "INCan";

	/** Set INCan.
	  * INCan
	  */
	public void setINCan (BigDecimal INCan);

	/** Get INCan.
	  * INCan
	  */
	public BigDecimal getINCan();

    /** Column name INCar */
    public static final String COLUMNNAME_INCar = "INCar";

	/** Set INCar.
	  * INCar
	  */
	public void setINCar (BigDecimal INCar);

	/** Get INCar.
	  * INCar
	  */
	public BigDecimal getINCar();

    /** Column name INCMyN */
    public static final String COLUMNNAME_INCMyN = "INCMyN";

	/** Set INCMyN.
	  * INCMyN
	  */
	public void setINCMyN (BigDecimal INCMyN);

	/** Get INCMyN.
	  * INCMyN
	  */
	public BigDecimal getINCMyN();

    /** Column name INNYN */
    public static final String COLUMNNAME_INNYN = "INNYN";

	/** Set INNYN	  */
	public void setINNYN (BigDecimal INNYN);

	/** Get INNYN	  */
	public BigDecimal getINNYN();

    /** Column name InPediatria */
    public static final String COLUMNNAME_InPediatria = "InPediatria";

	/** Set InPediatria	  */
	public void setInPediatria (BigDecimal InPediatria);

	/** Get InPediatria	  */
	public BigDecimal getInPediatria();

    /** Column name INPsiq */
    public static final String COLUMNNAME_INPsiq = "INPsiq";

	/** Set INPsiq.
	  * INPsiq
	  */
	public void setINPsiq (BigDecimal INPsiq);

	/** Get INPsiq.
	  * INPsiq
	  */
	public BigDecimal getINPsiq();

    /** Column name Inter_Pac */
    public static final String COLUMNNAME_Inter_Pac = "Inter_Pac";

	/** Set Inter. Pac..
	  * Inter. Pac.
	  */
	public void setInter_Pac (BigDecimal Inter_Pac);

	/** Get Inter. Pac..
	  * Inter. Pac.
	  */
	public BigDecimal getInter_Pac();

    /** Column name Inter_Tram */
    public static final String COLUMNNAME_Inter_Tram = "Inter_Tram";

	/** Set Inter. Tram..
	  * Inter. Tram.
	  */
	public void setInter_Tram (BigDecimal Inter_Tram);

	/** Get Inter. Tram..
	  * Inter. Tram.
	  */
	public BigDecimal getInter_Tram();

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

    /** Column name SC */
    public static final String COLUMNNAME_SC = "SC";

	/** Set SC	  */
	public void setSC (String SC);

	/** Get SC	  */
	public String getSC();

    /** Column name Traslado */
    public static final String COLUMNNAME_Traslado = "Traslado";

	/** Set Transfer.
	  * Transfer
	  */
	public void setTraslado (BigDecimal Traslado);

	/** Get Transfer.
	  * Transfer
	  */
	public BigDecimal getTraslado();

    /** Column name UNAM */
    public static final String COLUMNNAME_UNAM = "UNAM";

	/** Set UNAM.
	  * Autonomous University of Mexico. 
	  */
	public void setUNAM (BigDecimal UNAM);

	/** Get UNAM.
	  * Autonomous University of Mexico. 
	  */
	public BigDecimal getUNAM();

    /** Column name Valo_Pac */
    public static final String COLUMNNAME_Valo_Pac = "Valo_Pac";

	/** Set Valo. Pac..
	  * Valo. Pac.
	  */
	public void setValo_Pac (BigDecimal Valo_Pac);

	/** Get Valo. Pac..
	  * Valo. Pac.
	  */
	public BigDecimal getValo_Pac();

    /** Column name Valo_Tram */
    public static final String COLUMNNAME_Valo_Tram = "Valo_Tram";

	/** Set Valo. Tram..
	  * Valo. Tram.
	  */
	public void setValo_Tram (BigDecimal Valo_Tram);

	/** Get Valo. Tram..
	  * Valo. Tram.
	  */
	public BigDecimal getValo_Tram();
}
