/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

/** Generated Model for EXME_Config_OV
 *  @author Generated Class 
 *  @version Version @VERSION@ - $Id$ */
public class X_EXME_Config_OV extends PO implements I_EXME_Config_OV, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_Config_OV (Properties ctx, int EXME_Config_OV_ID, String trxName)
    {
      super (ctx, EXME_Config_OV_ID, trxName);
      /** if (EXME_Config_OV_ID == 0)
        {
			setAssessment_ID (0);
			setEXME_Config_OV_ID (0);
			setEXME_Medico_ID (0);
			setHPI_ID (0);
			setMFSH_ID (0);
			setMULTI_ID (0);
			setROS_ID (0);
        } */
    }

    /** Load Constructor */
    public X_EXME_Config_OV (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 7 - System - Client - Org 
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
      StringBuffer sb = new StringBuffer ("X_EXME_Config_OV[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Assessment.
		@param Assessment_ID 
		Cuestionario para la pestaña de Valoración
	  */
	public void setAssessment_ID (int Assessment_ID)
	{
		if (Assessment_ID < 1)
			 throw new IllegalArgumentException ("Assessment_ID is mandatory.");
		set_Value (COLUMNNAME_Assessment_ID, Integer.valueOf(Assessment_ID));
	}

	/** Get Assessment.
		@return Cuestionario para la pestaña de Valoración
	  */
	public int getAssessment_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Assessment_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set BREAST_ID.
		@param BREAST_ID BREAST_ID	  */
	public void setBREAST_ID (int BREAST_ID)
	{
		if (BREAST_ID < 1) 
			set_Value (COLUMNNAME_BREAST_ID, null);
		else 
			set_Value (COLUMNNAME_BREAST_ID, Integer.valueOf(BREAST_ID));
	}

	/** Get BREAST_ID.
		@return BREAST_ID	  */
	public int getBREAST_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_BREAST_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set CARDIO_ID.
		@param CARDIO_ID CARDIO_ID	  */
	public void setCARDIO_ID (int CARDIO_ID)
	{
		if (CARDIO_ID < 1) 
			set_Value (COLUMNNAME_CARDIO_ID, null);
		else 
			set_Value (COLUMNNAME_CARDIO_ID, Integer.valueOf(CARDIO_ID));
	}

	/** Get CARDIO_ID.
		@return CARDIO_ID	  */
	public int getCARDIO_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_CARDIO_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set ENMT_ID.
		@param ENMT_ID ENMT_ID	  */
	public void setENMT_ID (int ENMT_ID)
	{
		if (ENMT_ID < 1) 
			set_Value (COLUMNNAME_ENMT_ID, null);
		else 
			set_Value (COLUMNNAME_ENMT_ID, Integer.valueOf(ENMT_ID));
	}

	/** Get ENMT_ID.
		@return ENMT_ID	  */
	public int getENMT_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_ENMT_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set EXME_Config_OV_ID.
		@param EXME_Config_OV_ID EXME_Config_OV_ID	  */
	public void setEXME_Config_OV_ID (int EXME_Config_OV_ID)
	{
		if (EXME_Config_OV_ID < 1)
			 throw new IllegalArgumentException ("EXME_Config_OV_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_Config_OV_ID, Integer.valueOf(EXME_Config_OV_ID));
	}

	/** Get EXME_Config_OV_ID.
		@return EXME_Config_OV_ID	  */
	public int getEXME_Config_OV_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Config_OV_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Form Group.
		@param EXME_GrupoCuestionario_ID 
		Form Group
	  */
	public void setEXME_GrupoCuestionario_ID (int EXME_GrupoCuestionario_ID)
	{
		if (EXME_GrupoCuestionario_ID < 1) 
			set_Value (COLUMNNAME_EXME_GrupoCuestionario_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_GrupoCuestionario_ID, Integer.valueOf(EXME_GrupoCuestionario_ID));
	}

	/** Get Form Group.
		@return Form Group
	  */
	public int getEXME_GrupoCuestionario_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_GrupoCuestionario_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_Medico getEXME_Medico() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_Medico.Table_Name);
        I_EXME_Medico result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_Medico)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Medico_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Doctor.
		@param EXME_Medico_ID 
		Doctor
	  */
	public void setEXME_Medico_ID (int EXME_Medico_ID)
	{
		if (EXME_Medico_ID < 1)
			 throw new IllegalArgumentException ("EXME_Medico_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_Medico_ID, Integer.valueOf(EXME_Medico_ID));
	}

	/** Get Doctor.
		@return Doctor
	  */
	public int getEXME_Medico_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Medico_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_OrderSet getEXME_OrderSet() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_OrderSet.Table_Name);
        I_EXME_OrderSet result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_OrderSet)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_OrderSet_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Order Set.
		@param EXME_OrderSet_ID Order Set	  */
	public void setEXME_OrderSet_ID (int EXME_OrderSet_ID)
	{
		if (EXME_OrderSet_ID < 1) 
			set_Value (COLUMNNAME_EXME_OrderSet_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_OrderSet_ID, Integer.valueOf(EXME_OrderSet_ID));
	}

	/** Get Order Set.
		@return Order Set	  */
	public int getEXME_OrderSet_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_OrderSet_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set EYES_ID.
		@param EYES_ID EYES_ID	  */
	public void setEYES_ID (int EYES_ID)
	{
		if (EYES_ID < 1) 
			set_Value (COLUMNNAME_EYES_ID, null);
		else 
			set_Value (COLUMNNAME_EYES_ID, Integer.valueOf(EYES_ID));
	}

	/** Get EYES_ID.
		@return EYES_ID	  */
	public int getEYES_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EYES_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Fee Charges.
		@param GenCharges 
		Fee Charges
	  */
	public void setGenCharges (boolean GenCharges)
	{
		set_Value (COLUMNNAME_GenCharges, Boolean.valueOf(GenCharges));
	}

	/** Get Fee Charges.
		@return Fee Charges
	  */
	public boolean isGenCharges () 
	{
		Object oo = get_Value(COLUMNNAME_GenCharges);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set GENIT_ID.
		@param GENIT_ID GENIT_ID	  */
	public void setGENIT_ID (int GENIT_ID)
	{
		if (GENIT_ID < 1) 
			set_Value (COLUMNNAME_GENIT_ID, null);
		else 
			set_Value (COLUMNNAME_GENIT_ID, Integer.valueOf(GENIT_ID));
	}

	/** Get GENIT_ID.
		@return GENIT_ID	  */
	public int getGENIT_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_GENIT_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set HEMATO_ID.
		@param HEMATO_ID HEMATO_ID	  */
	public void setHEMATO_ID (int HEMATO_ID)
	{
		if (HEMATO_ID < 1) 
			set_Value (COLUMNNAME_HEMATO_ID, null);
		else 
			set_Value (COLUMNNAME_HEMATO_ID, Integer.valueOf(HEMATO_ID));
	}

	/** Get HEMATO_ID.
		@return HEMATO_ID	  */
	public int getHEMATO_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HEMATO_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set HPI_ID.
		@param HPI_ID HPI_ID	  */
	public void setHPI_ID (int HPI_ID)
	{
		if (HPI_ID < 1)
			 throw new IllegalArgumentException ("HPI_ID is mandatory.");
		set_Value (COLUMNNAME_HPI_ID, Integer.valueOf(HPI_ID));
	}

	/** Get HPI_ID.
		@return HPI_ID	  */
	public int getHPI_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HPI_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set MFSH_ID.
		@param MFSH_ID MFSH_ID	  */
	public void setMFSH_ID (int MFSH_ID)
	{
		if (MFSH_ID < 1)
			 throw new IllegalArgumentException ("MFSH_ID is mandatory.");
		set_Value (COLUMNNAME_MFSH_ID, Integer.valueOf(MFSH_ID));
	}

	/** Get MFSH_ID.
		@return MFSH_ID	  */
	public int getMFSH_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_MFSH_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_M_Product getM_Product() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_M_Product.Table_Name);
        I_M_Product result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_M_Product)constructor.newInstance(new Object[] {getCtx(), new Integer(getM_Product_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Product.
		@param M_Product_ID 
		Product, Service, Item
	  */
	public void setM_Product_ID (int M_Product_ID)
	{
		if (M_Product_ID < 1) 
			set_Value (COLUMNNAME_M_Product_ID, null);
		else 
			set_Value (COLUMNNAME_M_Product_ID, Integer.valueOf(M_Product_ID));
	}

	/** Get Product.
		@return Product, Service, Item
	  */
	public int getM_Product_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_Product_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set MULTI_ID.
		@param MULTI_ID MULTI_ID	  */
	public void setMULTI_ID (int MULTI_ID)
	{
		if (MULTI_ID < 1)
			 throw new IllegalArgumentException ("MULTI_ID is mandatory.");
		set_Value (COLUMNNAME_MULTI_ID, Integer.valueOf(MULTI_ID));
	}

	/** Get MULTI_ID.
		@return MULTI_ID	  */
	public int getMULTI_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_MULTI_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set MUSCUL_ID.
		@param MUSCUL_ID MUSCUL_ID	  */
	public void setMUSCUL_ID (int MUSCUL_ID)
	{
		if (MUSCUL_ID < 1) 
			set_Value (COLUMNNAME_MUSCUL_ID, null);
		else 
			set_Value (COLUMNNAME_MUSCUL_ID, Integer.valueOf(MUSCUL_ID));
	}

	/** Get MUSCUL_ID.
		@return MUSCUL_ID	  */
	public int getMUSCUL_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_MUSCUL_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_M_Warehouse getM_Warehouse() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_M_Warehouse.Table_Name);
        I_M_Warehouse result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_M_Warehouse)constructor.newInstance(new Object[] {getCtx(), new Integer(getM_Warehouse_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Warehouse.
		@param M_Warehouse_ID 
		Storage Warehouse and Service Point
	  */
	public void setM_Warehouse_ID (int M_Warehouse_ID)
	{
		if (M_Warehouse_ID < 1) 
			set_Value (COLUMNNAME_M_Warehouse_ID, null);
		else 
			set_Value (COLUMNNAME_M_Warehouse_ID, Integer.valueOf(M_Warehouse_ID));
	}

	/** Get Warehouse.
		@return Storage Warehouse and Service Point
	  */
	public int getM_Warehouse_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_Warehouse_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set NEUROL_ID.
		@param NEUROL_ID NEUROL_ID	  */
	public void setNEUROL_ID (int NEUROL_ID)
	{
		if (NEUROL_ID < 1) 
			set_Value (COLUMNNAME_NEUROL_ID, null);
		else 
			set_Value (COLUMNNAME_NEUROL_ID, Integer.valueOf(NEUROL_ID));
	}

	/** Get NEUROL_ID.
		@return NEUROL_ID	  */
	public int getNEUROL_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_NEUROL_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set PSYCHI_ID.
		@param PSYCHI_ID PSYCHI_ID	  */
	public void setPSYCHI_ID (int PSYCHI_ID)
	{
		if (PSYCHI_ID < 1) 
			set_Value (COLUMNNAME_PSYCHI_ID, null);
		else 
			set_Value (COLUMNNAME_PSYCHI_ID, Integer.valueOf(PSYCHI_ID));
	}

	/** Get PSYCHI_ID.
		@return PSYCHI_ID	  */
	public int getPSYCHI_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_PSYCHI_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set RESPIR_ID.
		@param RESPIR_ID RESPIR_ID	  */
	public void setRESPIR_ID (int RESPIR_ID)
	{
		if (RESPIR_ID < 1) 
			set_Value (COLUMNNAME_RESPIR_ID, null);
		else 
			set_Value (COLUMNNAME_RESPIR_ID, Integer.valueOf(RESPIR_ID));
	}

	/** Get RESPIR_ID.
		@return RESPIR_ID	  */
	public int getRESPIR_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_RESPIR_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set ROS Form.
		@param ROS_ID ROS Form	  */
	public void setROS_ID (int ROS_ID)
	{
		if (ROS_ID < 1)
			 throw new IllegalArgumentException ("ROS_ID is mandatory.");
		set_Value (COLUMNNAME_ROS_ID, Integer.valueOf(ROS_ID));
	}

	/** Get ROS Form.
		@return ROS Form	  */
	public int getROS_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_ROS_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set SKIN_ID.
		@param SKIN_ID SKIN_ID	  */
	public void setSKIN_ID (int SKIN_ID)
	{
		if (SKIN_ID < 1) 
			set_Value (COLUMNNAME_SKIN_ID, null);
		else 
			set_Value (COLUMNNAME_SKIN_ID, Integer.valueOf(SKIN_ID));
	}

	/** Get SKIN_ID.
		@return SKIN_ID	  */
	public int getSKIN_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_SKIN_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}