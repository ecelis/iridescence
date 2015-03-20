/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

/** Generated Model for EXME_ProcedPacDet
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_ProcedPacDet extends PO implements I_EXME_ProcedPacDet, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_ProcedPacDet (Properties ctx, int EXME_ProcedPacDet_ID, String trxName)
    {
      super (ctx, EXME_ProcedPacDet_ID, trxName);
      /** if (EXME_ProcedPacDet_ID == 0)
        {
			setEXME_ProcedPacDet_ID (0);
			setValue (null);
        } */
    }

    /** Load Constructor */
    public X_EXME_ProcedPacDet (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_ProcedPacDet[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Description.
		@param Description 
		Optional short description of the record
	  */
	public void setDescription (String Description)
	{
		set_Value (COLUMNNAME_Description, Description);
	}

	/** Get Description.
		@return Optional short description of the record
	  */
	public String getDescription () 
	{
		return (String)get_Value(COLUMNNAME_Description);
	}

	/** Set Alternative procedure as the patient.
		@param EXME_PlanMedicoAlt_ID Alternative procedure as the patient	  */
	public void setEXME_PlanMedicoAlt_ID (int EXME_PlanMedicoAlt_ID)
	{
		if (EXME_PlanMedicoAlt_ID < 1) 
			set_Value (COLUMNNAME_EXME_PlanMedicoAlt_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_PlanMedicoAlt_ID, Integer.valueOf(EXME_PlanMedicoAlt_ID));
	}

	/** Get Alternative procedure as the patient.
		@return Alternative procedure as the patient	  */
	public int getEXME_PlanMedicoAlt_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_PlanMedicoAlt_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_PlanMedico getEXME_PlanMedico() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_PlanMedico.Table_Name);
        I_EXME_PlanMedico result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_PlanMedico)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_PlanMedico_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Health Plan.
		@param EXME_PlanMedico_ID Health Plan	  */
	public void setEXME_PlanMedico_ID (int EXME_PlanMedico_ID)
	{
		if (EXME_PlanMedico_ID < 1) 
			set_Value (COLUMNNAME_EXME_PlanMedico_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_PlanMedico_ID, Integer.valueOf(EXME_PlanMedico_ID));
	}

	/** Get Health Plan.
		@return Health Plan	  */
	public int getEXME_PlanMedico_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_PlanMedico_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Details of the procedure as the patient.
		@param EXME_ProcedPacDet_ID Details of the procedure as the patient	  */
	public void setEXME_ProcedPacDet_ID (int EXME_ProcedPacDet_ID)
	{
		if (EXME_ProcedPacDet_ID < 1)
			 throw new IllegalArgumentException ("EXME_ProcedPacDet_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_ProcedPacDet_ID, Integer.valueOf(EXME_ProcedPacDet_ID));
	}

	/** Get Details of the procedure as the patient.
		@return Details of the procedure as the patient	  */
	public int getEXME_ProcedPacDet_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_ProcedPacDet_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_ProcedPac getEXME_ProcedPac() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_ProcedPac.Table_Name);
        I_EXME_ProcedPac result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_ProcedPac)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_ProcedPac_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Procedures applied to the patient.
		@param EXME_ProcedPac_ID Procedures applied to the patient	  */
	public void setEXME_ProcedPac_ID (int EXME_ProcedPac_ID)
	{
		if (EXME_ProcedPac_ID < 1) 
			set_Value (COLUMNNAME_EXME_ProcedPac_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_ProcedPac_ID, Integer.valueOf(EXME_ProcedPac_ID));
	}

	/** Get Procedures applied to the patient.
		@return Procedures applied to the patient	  */
	public int getEXME_ProcedPac_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_ProcedPac_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_ResultadoMedico getEXME_ResultadoMedico() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_ResultadoMedico.Table_Name);
        I_EXME_ResultadoMedico result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_ResultadoMedico)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_ResultadoMedico_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Medical Staff.
		@param EXME_ResultadoMedico_ID Medical Staff	  */
	public void setEXME_ResultadoMedico_ID (int EXME_ResultadoMedico_ID)
	{
		if (EXME_ResultadoMedico_ID < 1) 
			set_Value (COLUMNNAME_EXME_ResultadoMedico_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_ResultadoMedico_ID, Integer.valueOf(EXME_ResultadoMedico_ID));
	}

	/** Get Medical Staff.
		@return Medical Staff	  */
	public int getEXME_ResultadoMedico_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_ResultadoMedico_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_RiesgoMedico getEXME_RiesgoMedico() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_RiesgoMedico.Table_Name);
        I_EXME_RiesgoMedico result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_RiesgoMedico)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_RiesgoMedico_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Medical risk.
		@param EXME_RiesgoMedico_ID Medical risk	  */
	public void setEXME_RiesgoMedico_ID (int EXME_RiesgoMedico_ID)
	{
		if (EXME_RiesgoMedico_ID < 1) 
			set_Value (COLUMNNAME_EXME_RiesgoMedico_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_RiesgoMedico_ID, Integer.valueOf(EXME_RiesgoMedico_ID));
	}

	/** Get Medical risk.
		@return Medical risk	  */
	public int getEXME_RiesgoMedico_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_RiesgoMedico_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** TipoRegistro AD_Reference_ID=1200221 */
	public static final int TIPOREGISTRO_AD_Reference_ID=1200221;
	/** Risk = R */
	public static final String TIPOREGISTRO_Risk = "R";
	/** Plan alternative = A */
	public static final String TIPOREGISTRO_PlanAlternative = "A";
	/** Plan = P */
	public static final String TIPOREGISTRO_Plan = "P";
	/** Guest = G */
	public static final String TIPOREGISTRO_Guest = "G";
	/** Set Record Type.
		@param TipoRegistro Record Type	  */
	public void setTipoRegistro (String TipoRegistro)
	{

		if (TipoRegistro == null || TipoRegistro.equals("R") || TipoRegistro.equals("A") || TipoRegistro.equals("P") || TipoRegistro.equals("G")); else throw new IllegalArgumentException ("TipoRegistro Invalid value - " + TipoRegistro + " - Reference_ID=1200221 - R - A - P - G");		set_Value (COLUMNNAME_TipoRegistro, TipoRegistro);
	}

	/** Get Record Type.
		@return Record Type	  */
	public String getTipoRegistro () 
	{
		return (String)get_Value(COLUMNNAME_TipoRegistro);
	}

	/** Set Search Key.
		@param Value 
		Search key for the record in the format required - must be unique
	  */
	public void setValue (String Value)
	{
		if (Value == null)
			throw new IllegalArgumentException ("Value is mandatory.");
		set_Value (COLUMNNAME_Value, Value);
	}

	/** Get Search Key.
		@return Search key for the record in the format required - must be unique
	  */
	public String getValue () 
	{
		return (String)get_Value(COLUMNNAME_Value);
	}
}