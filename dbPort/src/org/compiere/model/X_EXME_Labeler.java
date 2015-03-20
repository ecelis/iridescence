/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;

/** Generated Model for EXME_Labeler
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_Labeler extends PO implements I_EXME_Labeler, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_Labeler (Properties ctx, int EXME_Labeler_ID, String trxName)
    {
      super (ctx, EXME_Labeler_ID, trxName);
      /** if (EXME_Labeler_ID == 0)
        {
			setEXME_Labeler_ID (0);
			setIsVacuna (false);
        } */
    }

    /** Load Constructor */
    public X_EXME_Labeler (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 4 - System 
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
      StringBuffer sb = new StringBuffer ("X_EXME_Labeler[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Labeler.
		@param EXME_Labeler_ID 
		Labeler
	  */
	public void setEXME_Labeler_ID (int EXME_Labeler_ID)
	{
		if (EXME_Labeler_ID < 1)
			 throw new IllegalArgumentException ("EXME_Labeler_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_Labeler_ID, Integer.valueOf(EXME_Labeler_ID));
	}

	/** Get Labeler.
		@return Labeler
	  */
	public int getEXME_Labeler_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Labeler_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Is Vaccine.
		@param IsVacuna Is Vaccine	  */
	public void setIsVacuna (boolean IsVacuna)
	{
		set_Value (COLUMNNAME_IsVacuna, Boolean.valueOf(IsVacuna));
	}

	/** Get Is Vaccine.
		@return Is Vaccine	  */
	public boolean isVacuna () 
	{
		Object oo = get_Value(COLUMNNAME_IsVacuna);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Labeler.
		@param LabelerID Labeler	  */
	public void setLabelerID (String LabelerID)
	{
		set_Value (COLUMNNAME_LabelerID, LabelerID);
	}

	/** Get Labeler.
		@return Labeler	  */
	public String getLabelerID () 
	{
		return (String)get_Value(COLUMNNAME_LabelerID);
	}

	/** Set Name.
		@param Mfgname 
		Name
	  */
	public void setMfgname (String Mfgname)
	{
		set_Value (COLUMNNAME_Mfgname, Mfgname);
	}

	/** Get Name.
		@return Name
	  */
	public String getMfgname () 
	{
		return (String)get_Value(COLUMNNAME_Mfgname);
	}

	/** Set Code MVX.
		@param MVXCode Code MVX	  */
	public void setMVXCode (String MVXCode)
	{
		set_Value (COLUMNNAME_MVXCode, MVXCode);
	}

	/** Get Code MVX.
		@return Code MVX	  */
	public String getMVXCode () 
	{
		return (String)get_Value(COLUMNNAME_MVXCode);
	}

	/** Set Code.
		@param Ndcmfgcode 
		Code
	  */
	public void setNdcmfgcode (String Ndcmfgcode)
	{
		set_Value (COLUMNNAME_Ndcmfgcode, Ndcmfgcode);
	}

	/** Get Code.
		@return Code
	  */
	public String getNdcmfgcode () 
	{
		return (String)get_Value(COLUMNNAME_Ndcmfgcode);
	}
}