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

/** Generated Model for EXME_Reg_Gestoria
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_Reg_Gestoria extends PO implements I_EXME_Reg_Gestoria, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_Reg_Gestoria (Properties ctx, int EXME_Reg_Gestoria_ID, String trxName)
    {
      super (ctx, EXME_Reg_Gestoria_ID, trxName);
      /** if (EXME_Reg_Gestoria_ID == 0)
        {
			setEXME_Reg_Gestoria_ID (0);
			setFecha (new Timestamp( System.currentTimeMillis() ));
			setFecha_Ctrl (new Timestamp( System.currentTimeMillis() ));
        } */
    }

    /** Load Constructor */
    public X_EXME_Reg_Gestoria (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_Reg_Gestoria[")
        .append(get_ID()).append("]");
      return sb.toString();
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

	/** Set Patient Status.
		@param Estu_Pac 
		Patient Status
	  */
	public void setEstu_Pac (BigDecimal Estu_Pac)
	{
		set_Value (COLUMNNAME_Estu_Pac, Estu_Pac);
	}

	/** Get Patient Status.
		@return Patient Status
	  */
	public BigDecimal getEstu_Pac () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Estu_Pac);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Estu. Tram..
		@param Estu_Tram 
		Estu. Tram.
	  */
	public void setEstu_Tram (BigDecimal Estu_Tram)
	{
		set_Value (COLUMNNAME_Estu_Tram, Estu_Tram);
	}

	/** Get Estu. Tram..
		@return Estu. Tram.
	  */
	public BigDecimal getEstu_Tram () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Estu_Tram);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Bed.
		@param EXME_Cama_ID 
		Bed
	  */
	public void setEXME_Cama_ID (int EXME_Cama_ID)
	{
		if (EXME_Cama_ID < 1) 
			set_Value (COLUMNNAME_EXME_Cama_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_Cama_ID, Integer.valueOf(EXME_Cama_ID));
	}

	/** Get Bed.
		@return Bed
	  */
	public int getEXME_Cama_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Cama_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Patient.
		@param EXME_Paciente_ID 
		Patient
	  */
	public void setEXME_Paciente_ID (int EXME_Paciente_ID)
	{
		if (EXME_Paciente_ID < 1) 
			set_Value (COLUMNNAME_EXME_Paciente_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_Paciente_ID, Integer.valueOf(EXME_Paciente_ID));
	}

	/** Get Patient.
		@return Patient
	  */
	public int getEXME_Paciente_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Paciente_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Daily registry of Agency.
		@param EXME_Reg_Gestoria_ID 
		Daily registry of Agency
	  */
	public void setEXME_Reg_Gestoria_ID (int EXME_Reg_Gestoria_ID)
	{
		if (EXME_Reg_Gestoria_ID < 1)
			 throw new IllegalArgumentException ("EXME_Reg_Gestoria_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_Reg_Gestoria_ID, Integer.valueOf(EXME_Reg_Gestoria_ID));
	}

	/** Get Daily registry of Agency.
		@return Daily registry of Agency
	  */
	public int getEXME_Reg_Gestoria_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Reg_Gestoria_ID);
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

	/** Set Quistic Fibrosis.
		@param Fibrosis_Quis 
		Quistic Fibrosis
	  */
	public void setFibrosis_Quis (BigDecimal Fibrosis_Quis)
	{
		set_Value (COLUMNNAME_Fibrosis_Quis, Fibrosis_Quis);
	}

	/** Get Quistic Fibrosis.
		@return Quistic Fibrosis
	  */
	public BigDecimal getFibrosis_Quis () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Fibrosis_Quis);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Gea. Glz..
		@param Gea_Glz 
		Gea. Glz.
	  */
	public void setGea_Glz (BigDecimal Gea_Glz)
	{
		set_Value (COLUMNNAME_Gea_Glz, Gea_Glz);
	}

	/** Get Gea. Glz..
		@return Gea. Glz.
	  */
	public BigDecimal getGea_Glz () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Gea_Glz);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Adminstrator.
		@param Gestor 
		Administrator
	  */
	public void setGestor (String Gestor)
	{
		set_Value (COLUMNNAME_Gestor, Gestor);
	}

	/** Get Adminstrator.
		@return Administrator
	  */
	public String getGestor () 
	{
		return (String)get_Value(COLUMNNAME_Gestor);
	}

	/** Set H.G. Juarez.
		@param HG_Juarez 
		H.G. Juarez
	  */
	public void setHG_Juarez (BigDecimal HG_Juarez)
	{
		set_Value (COLUMNNAME_HG_Juarez, HG_Juarez);
	}

	/** Get H.G. Juarez.
		@return H.G. Juarez
	  */
	public BigDecimal getHG_Juarez () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_HG_Juarez);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set INCan.
		@param INCan 
		INCan
	  */
	public void setINCan (BigDecimal INCan)
	{
		set_Value (COLUMNNAME_INCan, INCan);
	}

	/** Get INCan.
		@return INCan
	  */
	public BigDecimal getINCan () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_INCan);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set INCar.
		@param INCar 
		INCar
	  */
	public void setINCar (BigDecimal INCar)
	{
		set_Value (COLUMNNAME_INCar, INCar);
	}

	/** Get INCar.
		@return INCar
	  */
	public BigDecimal getINCar () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_INCar);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set INCMyN.
		@param INCMyN 
		INCMyN
	  */
	public void setINCMyN (BigDecimal INCMyN)
	{
		set_Value (COLUMNNAME_INCMyN, INCMyN);
	}

	/** Get INCMyN.
		@return INCMyN
	  */
	public BigDecimal getINCMyN () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_INCMyN);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set INNYN.
		@param INNYN INNYN	  */
	public void setINNYN (BigDecimal INNYN)
	{
		set_Value (COLUMNNAME_INNYN, INNYN);
	}

	/** Get INNYN.
		@return INNYN	  */
	public BigDecimal getINNYN () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_INNYN);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set InPediatria.
		@param InPediatria InPediatria	  */
	public void setInPediatria (BigDecimal InPediatria)
	{
		set_Value (COLUMNNAME_InPediatria, InPediatria);
	}

	/** Get InPediatria.
		@return InPediatria	  */
	public BigDecimal getInPediatria () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_InPediatria);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set INPsiq.
		@param INPsiq 
		INPsiq
	  */
	public void setINPsiq (BigDecimal INPsiq)
	{
		set_Value (COLUMNNAME_INPsiq, INPsiq);
	}

	/** Get INPsiq.
		@return INPsiq
	  */
	public BigDecimal getINPsiq () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_INPsiq);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Inter. Pac..
		@param Inter_Pac 
		Inter. Pac.
	  */
	public void setInter_Pac (BigDecimal Inter_Pac)
	{
		set_Value (COLUMNNAME_Inter_Pac, Inter_Pac);
	}

	/** Get Inter. Pac..
		@return Inter. Pac.
	  */
	public BigDecimal getInter_Pac () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Inter_Pac);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Inter. Tram..
		@param Inter_Tram 
		Inter. Tram.
	  */
	public void setInter_Tram (BigDecimal Inter_Tram)
	{
		set_Value (COLUMNNAME_Inter_Tram, Inter_Tram);
	}

	/** Get Inter. Tram..
		@return Inter. Tram.
	  */
	public BigDecimal getInter_Tram () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Inter_Tram);
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

	/** Set SC.
		@param SC SC	  */
	public void setSC (String SC)
	{
		set_Value (COLUMNNAME_SC, SC);
	}

	/** Get SC.
		@return SC	  */
	public String getSC () 
	{
		return (String)get_Value(COLUMNNAME_SC);
	}

	/** Set Transfer.
		@param Traslado 
		Transfer
	  */
	public void setTraslado (BigDecimal Traslado)
	{
		set_Value (COLUMNNAME_Traslado, Traslado);
	}

	/** Get Transfer.
		@return Transfer
	  */
	public BigDecimal getTraslado () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Traslado);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set UNAM.
		@param UNAM 
		Autonomous University of Mexico. 
	  */
	public void setUNAM (BigDecimal UNAM)
	{
		set_Value (COLUMNNAME_UNAM, UNAM);
	}

	/** Get UNAM.
		@return Autonomous University of Mexico. 
	  */
	public BigDecimal getUNAM () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_UNAM);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Valo. Pac..
		@param Valo_Pac 
		Valo. Pac.
	  */
	public void setValo_Pac (BigDecimal Valo_Pac)
	{
		set_Value (COLUMNNAME_Valo_Pac, Valo_Pac);
	}

	/** Get Valo. Pac..
		@return Valo. Pac.
	  */
	public BigDecimal getValo_Pac () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Valo_Pac);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Valo. Tram..
		@param Valo_Tram 
		Valo. Tram.
	  */
	public void setValo_Tram (BigDecimal Valo_Tram)
	{
		set_Value (COLUMNNAME_Valo_Tram, Valo_Tram);
	}

	/** Get Valo. Tram..
		@return Valo. Tram.
	  */
	public BigDecimal getValo_Tram () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Valo_Tram);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}
}