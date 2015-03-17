/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;
import org.compiere.util.Env;

/** Generated Model for EXME_RecResiduoDet
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_RecResiduoDet extends PO implements I_EXME_RecResiduoDet, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_RecResiduoDet (Properties ctx, int EXME_RecResiduoDet_ID, String trxName)
    {
      super (ctx, EXME_RecResiduoDet_ID, trxName);
      /** if (EXME_RecResiduoDet_ID == 0)
        {
			setEXME_Contenedor_ID (0);
			setEXME_EstServ_ID (0);
			setEXME_RecResiduoDet_ID (0);
			setEXME_RecResiduo_ID (0);
// @EXME_RecResiduo_ID@
			setEXME_Residuo_ID (0);
			setEXME_SubEstacion_ID (0);
			setPeso (Env.ZERO);
// 1
        } */
    }

    /** Load Constructor */
    public X_EXME_RecResiduoDet (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_RecResiduoDet[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public I_EXME_Contenedor getEXME_Contenedor() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_Contenedor.Table_Name);
        I_EXME_Contenedor result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_Contenedor)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Contenedor_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Container.
		@param EXME_Contenedor_ID 
		Specifies that contains the hazardous biological and infectious waste
	  */
	public void setEXME_Contenedor_ID (int EXME_Contenedor_ID)
	{
		if (EXME_Contenedor_ID < 1)
			 throw new IllegalArgumentException ("EXME_Contenedor_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_Contenedor_ID, Integer.valueOf(EXME_Contenedor_ID));
	}

	/** Get Container.
		@return Specifies that contains the hazardous biological and infectious waste
	  */
	public int getEXME_Contenedor_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Contenedor_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_EstServ getEXME_EstServ() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_EstServ.Table_Name);
        I_EXME_EstServ result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_EstServ)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_EstServ_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Service Station.
		@param EXME_EstServ_ID 
		Service Station
	  */
	public void setEXME_EstServ_ID (int EXME_EstServ_ID)
	{
		if (EXME_EstServ_ID < 1)
			 throw new IllegalArgumentException ("EXME_EstServ_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_EstServ_ID, Integer.valueOf(EXME_EstServ_ID));
	}

	/** Get Service Station.
		@return Service Station
	  */
	public int getEXME_EstServ_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_EstServ_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Waste Collection Detail.
		@param EXME_RecResiduoDet_ID 
		Allows recording details of the collection made
	  */
	public void setEXME_RecResiduoDet_ID (int EXME_RecResiduoDet_ID)
	{
		if (EXME_RecResiduoDet_ID < 1)
			 throw new IllegalArgumentException ("EXME_RecResiduoDet_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_RecResiduoDet_ID, Integer.valueOf(EXME_RecResiduoDet_ID));
	}

	/** Get Waste Collection Detail.
		@return Allows recording details of the collection made
	  */
	public int getEXME_RecResiduoDet_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_RecResiduoDet_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_RecResiduo getEXME_RecResiduo() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_RecResiduo.Table_Name);
        I_EXME_RecResiduo result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_RecResiduo)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_RecResiduo_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Waste Collection.
		@param EXME_RecResiduo_ID 
		Lets keep track of collections that are made on such collection is made
	  */
	public void setEXME_RecResiduo_ID (int EXME_RecResiduo_ID)
	{
		if (EXME_RecResiduo_ID < 1)
			 throw new IllegalArgumentException ("EXME_RecResiduo_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_RecResiduo_ID, Integer.valueOf(EXME_RecResiduo_ID));
	}

	/** Get Waste Collection.
		@return Lets keep track of collections that are made on such collection is made
	  */
	public int getEXME_RecResiduo_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_RecResiduo_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_Residuo getEXME_Residuo() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_Residuo.Table_Name);
        I_EXME_Residuo result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_Residuo)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Residuo_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Residue.
		@param EXME_Residuo_ID 
		Specifies the types of biological / infectious waste
	  */
	public void setEXME_Residuo_ID (int EXME_Residuo_ID)
	{
		if (EXME_Residuo_ID < 1)
			 throw new IllegalArgumentException ("EXME_Residuo_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_Residuo_ID, Integer.valueOf(EXME_Residuo_ID));
	}

	/** Get Residue.
		@return Specifies the types of biological / infectious waste
	  */
	public int getEXME_Residuo_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Residuo_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_SubEstacion getEXME_SubEstacion() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_SubEstacion.Table_Name);
        I_EXME_SubEstacion result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_SubEstacion)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_SubEstacion_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Substation.
		@param EXME_SubEstacion_ID 
		Contains a catalog of substations, which belong to a service station
	  */
	public void setEXME_SubEstacion_ID (int EXME_SubEstacion_ID)
	{
		if (EXME_SubEstacion_ID < 1)
			 throw new IllegalArgumentException ("EXME_SubEstacion_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_SubEstacion_ID, Integer.valueOf(EXME_SubEstacion_ID));
	}

	/** Get Substation.
		@return Contains a catalog of substations, which belong to a service station
	  */
	public int getEXME_SubEstacion_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_SubEstacion_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Weight.
		@param Peso 
		Weight
	  */
	public void setPeso (BigDecimal Peso)
	{
		if (Peso == null)
			throw new IllegalArgumentException ("Peso is mandatory.");
		set_Value (COLUMNNAME_Peso, Peso);
	}

	/** Get Weight.
		@return Weight
	  */
	public BigDecimal getPeso () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Peso);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}
}