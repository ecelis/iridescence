/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

/** Generated Model for EXME_PacBeneficiarios
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_PacBeneficiarios extends PO implements I_EXME_PacBeneficiarios, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_PacBeneficiarios (Properties ctx, int EXME_PacBeneficiarios_ID, String trxName)
    {
      super (ctx, EXME_PacBeneficiarios_ID, trxName);
      /** if (EXME_PacBeneficiarios_ID == 0)
        {
			setEXME_Beneficario_ID (0);
			setEXME_PacBeneficiarios_ID (0);
			setEXME_Paciente_ID (0);
        } */
    }

    /** Load Constructor */
    public X_EXME_PacBeneficiarios (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 9 - System 
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
      StringBuffer sb = new StringBuffer ("X_EXME_PacBeneficiarios[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Beneficiary.
		@param EXME_Beneficario_ID Beneficiary	  */
	public void setEXME_Beneficario_ID (int EXME_Beneficario_ID)
	{
		if (EXME_Beneficario_ID < 1)
			 throw new IllegalArgumentException ("EXME_Beneficario_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_Beneficario_ID, Integer.valueOf(EXME_Beneficario_ID));
	}

	/** Get Beneficiary.
		@return Beneficiary	  */
	public int getEXME_Beneficario_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Beneficario_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Patient's Beneficiaries.
		@param EXME_PacBeneficiarios_ID Patient's Beneficiaries	  */
	public void setEXME_PacBeneficiarios_ID (int EXME_PacBeneficiarios_ID)
	{
		if (EXME_PacBeneficiarios_ID < 1)
			 throw new IllegalArgumentException ("EXME_PacBeneficiarios_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_PacBeneficiarios_ID, Integer.valueOf(EXME_PacBeneficiarios_ID));
	}

	/** Get Patient's Beneficiaries.
		@return Patient's Beneficiaries	  */
	public int getEXME_PacBeneficiarios_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_PacBeneficiarios_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_Paciente getEXME_Paciente() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_Paciente.Table_Name);
        I_EXME_Paciente result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_Paciente)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Paciente_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Patient.
		@param EXME_Paciente_ID 
		Patient
	  */
	public void setEXME_Paciente_ID (int EXME_Paciente_ID)
	{
		if (EXME_Paciente_ID < 1)
			 throw new IllegalArgumentException ("EXME_Paciente_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_Paciente_ID, Integer.valueOf(EXME_Paciente_ID));
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
}