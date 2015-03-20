/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;
import java.util.logging.Level;
import org.compiere.util.Env;

/** Generated Model for EXME_Cornea
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_Cornea extends PO implements I_EXME_Cornea, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_Cornea (Properties ctx, int EXME_Cornea_ID, String trxName)
    {
      super (ctx, EXME_Cornea_ID, trxName);
      /** if (EXME_Cornea_ID == 0)
        {
			setDateReceived (new Timestamp( System.currentTimeMillis() ));
			setEXME_BancoProcedencia_ID (0);
			setEXME_Cornea_ID (0);
			setNumCornea (null);
			setTipoCornea (null);
			setValue (null);
        } */
    }

    /** Load Constructor */
    public X_EXME_Cornea (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_Cornea[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Cell Counting.
		@param ConteoCelular Cell Counting	  */
	public void setConteoCelular (BigDecimal ConteoCelular)
	{
		set_Value (COLUMNNAME_ConteoCelular, ConteoCelular);
	}

	/** Get Cell Counting.
		@return Cell Counting	  */
	public BigDecimal getConteoCelular () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_ConteoCelular);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Date received.
		@param DateReceived 
		Date a product was received
	  */
	public void setDateReceived (Timestamp DateReceived)
	{
		if (DateReceived == null)
			throw new IllegalArgumentException ("DateReceived is mandatory.");
		set_Value (COLUMNNAME_DateReceived, DateReceived);
	}

	/** Get Date received.
		@return Date a product was received
	  */
	public Timestamp getDateReceived () 
	{
		return (Timestamp)get_Value(COLUMNNAME_DateReceived);
	}

	/** Set Preservation Days.
		@param DiasPreservacion Preservation Days	  */
	public void setDiasPreservacion (BigDecimal DiasPreservacion)
	{
		set_Value (COLUMNNAME_DiasPreservacion, DiasPreservacion);
	}

	/** Get Preservation Days.
		@return Preservation Days	  */
	public BigDecimal getDiasPreservacion () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_DiasPreservacion);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	public I_EXME_BancoProcedencia getEXME_BancoProcedencia() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_BancoProcedencia.Table_Name);
        I_EXME_BancoProcedencia result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_BancoProcedencia)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_BancoProcedencia_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Bank of Origin.
		@param EXME_BancoProcedencia_ID Bank of Origin	  */
	public void setEXME_BancoProcedencia_ID (int EXME_BancoProcedencia_ID)
	{
		if (EXME_BancoProcedencia_ID < 1)
			 throw new IllegalArgumentException ("EXME_BancoProcedencia_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_BancoProcedencia_ID, Integer.valueOf(EXME_BancoProcedencia_ID));
	}

	/** Get Bank of Origin.
		@return Bank of Origin	  */
	public int getEXME_BancoProcedencia_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_BancoProcedencia_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Corneal.
		@param EXME_Cornea_ID 
		Corneal
	  */
	public void setEXME_Cornea_ID (int EXME_Cornea_ID)
	{
		if (EXME_Cornea_ID < 1)
			 throw new IllegalArgumentException ("EXME_Cornea_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_Cornea_ID, Integer.valueOf(EXME_Cornea_ID));
	}

	/** Get Corneal.
		@return Corneal
	  */
	public int getEXME_Cornea_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Cornea_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_Destino getEXME_Destino() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_Destino.Table_Name);
        I_EXME_Destino result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_Destino)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Destino_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Destination.
		@param EXME_Destino_ID Destination	  */
	public void setEXME_Destino_ID (int EXME_Destino_ID)
	{
		if (EXME_Destino_ID < 1) 
			set_Value (COLUMNNAME_EXME_Destino_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_Destino_ID, Integer.valueOf(EXME_Destino_ID));
	}

	/** Get Destination.
		@return Destination	  */
	public int getEXME_Destino_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Destino_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Invoice.
		@param Invoice Invoice	  */
	public void setInvoice (String Invoice)
	{
		set_Value (COLUMNNAME_Invoice, Invoice);
	}

	/** Get Invoice.
		@return Invoice	  */
	public String getInvoice () 
	{
		return (String)get_Value(COLUMNNAME_Invoice);
	}

	/** Set Dollar Amount.
		@param MontoUSD Dollar Amount	  */
	public void setMontoUSD (BigDecimal MontoUSD)
	{
		set_Value (COLUMNNAME_MontoUSD, MontoUSD);
	}

	/** Get Dollar Amount.
		@return Dollar Amount	  */
	public BigDecimal getMontoUSD () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_MontoUSD);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Cornea Number.
		@param NumCornea Cornea Number	  */
	public void setNumCornea (String NumCornea)
	{
		if (NumCornea == null)
			throw new IllegalArgumentException ("NumCornea is mandatory.");
		set_Value (COLUMNNAME_NumCornea, NumCornea);
	}

	/** Get Cornea Number.
		@return Cornea Number	  */
	public String getNumCornea () 
	{
		return (String)get_Value(COLUMNNAME_NumCornea);
	}

	/** TipoCornea AD_Reference_ID=1200096 */
	public static final int TIPOCORNEA_AD_Reference_ID=1200096;
	/** Foreigner = F */
	public static final String TIPOCORNEA_Foreigner = "F";
	/** National = N */
	public static final String TIPOCORNEA_National = "N";
	/** Set Cornea Type.
		@param TipoCornea Cornea Type	  */
	public void setTipoCornea (String TipoCornea)
	{
		if (TipoCornea == null) throw new IllegalArgumentException ("TipoCornea is mandatory");
		if (TipoCornea.equals("F") || TipoCornea.equals("N")); else throw new IllegalArgumentException ("TipoCornea Invalid value - " + TipoCornea + " - Reference_ID=1200096 - F - N");		set_Value (COLUMNNAME_TipoCornea, TipoCornea);
	}

	/** Get Cornea Type.
		@return Cornea Type	  */
	public String getTipoCornea () 
	{
		return (String)get_Value(COLUMNNAME_TipoCornea);
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