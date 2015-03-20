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

/** Generated Model for EXME_Tratamientos_Sesion
 *  @author Generated Class 
 *  @version Version @VERSION@ - $Id$ */
public class X_EXME_Tratamientos_Sesion extends PO implements I_EXME_Tratamientos_Sesion, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_Tratamientos_Sesion (Properties ctx, int EXME_Tratamientos_Sesion_ID, String trxName)
    {
      super (ctx, EXME_Tratamientos_Sesion_ID, trxName);
      /** if (EXME_Tratamientos_Sesion_ID == 0)
        {
			setDatePromised (new Timestamp( System.currentTimeMillis() ));
			setDateStart (new Timestamp( System.currentTimeMillis() ));
// sysdate
			setDocAction (null);
			setDocStatus (null);
			setDocumentNo (null);
			setEXME_Tratamientos_Detalle_ID (0);
			setEXME_TratamientosPaciente_ID (0);
			setEXME_Tratamientos_Sesion_ID (0);
			setProcessed (false);
        } */
    }

    /** Load Constructor */
    public X_EXME_Tratamientos_Sesion (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_Tratamientos_Sesion[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Date Canceled.
		@param DateCanceled Date Canceled	  */
	public void setDateCanceled (Timestamp DateCanceled)
	{
		set_Value (COLUMNNAME_DateCanceled, DateCanceled);
	}

	/** Get Date Canceled.
		@return Date Canceled	  */
	public Timestamp getDateCanceled () 
	{
		return (Timestamp)get_Value(COLUMNNAME_DateCanceled);
	}

	/** Set Date Promised.
		@param DatePromised 
		Date Order was promised
	  */
	public void setDatePromised (Timestamp DatePromised)
	{
		if (DatePromised == null)
			throw new IllegalArgumentException ("DatePromised is mandatory.");
		set_Value (COLUMNNAME_DatePromised, DatePromised);
	}

	/** Get Date Promised.
		@return Date Order was promised
	  */
	public Timestamp getDatePromised () 
	{
		return (Timestamp)get_Value(COLUMNNAME_DatePromised);
	}

	/** Set Date Start.
		@param DateStart Date Start	  */
	public void setDateStart (Timestamp DateStart)
	{
		if (DateStart == null)
			throw new IllegalArgumentException ("DateStart is mandatory.");
		set_Value (COLUMNNAME_DateStart, DateStart);
	}

	/** Get Date Start.
		@return Date Start	  */
	public Timestamp getDateStart () 
	{
		return (Timestamp)get_Value(COLUMNNAME_DateStart);
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

	/** DocAction AD_Reference_ID=131 */
	public static final int DOCACTION_AD_Reference_ID=131;
	/** Drafted = DR */
	public static final String DOCACTION_Drafted = "DR";
	/** Completed = CO */
	public static final String DOCACTION_Completed = "CO";
	/** Approved = AP */
	public static final String DOCACTION_Approved = "AP";
	/** Not Approved = NA */
	public static final String DOCACTION_NotApproved = "NA";
	/** Voided = VO */
	public static final String DOCACTION_Voided = "VO";
	/** Invalid = IN */
	public static final String DOCACTION_Invalid = "IN";
	/** Reversed = RE */
	public static final String DOCACTION_Reversed = "RE";
	/** Closed = CL */
	public static final String DOCACTION_Closed = "CL";
	/** Unknown = ?? */
	public static final String DOCACTION_Unknown = "??";
	/** In Progress = IP */
	public static final String DOCACTION_InProgress = "IP";
	/** Waiting Payment = WP */
	public static final String DOCACTION_WaitingPayment = "WP";
	/** Waiting Confirmation = WC */
	public static final String DOCACTION_WaitingConfirmation = "WC";
	/** Set Document Action.
		@param DocAction 
		The targeted status of the document
	  */
	public void setDocAction (String DocAction)
	{
		if (DocAction == null) throw new IllegalArgumentException ("DocAction is mandatory");
		if (DocAction.equals("DR") || DocAction.equals("CO") || DocAction.equals("AP") || DocAction.equals("NA") || DocAction.equals("VO") || DocAction.equals("IN") || DocAction.equals("RE") || DocAction.equals("CL") || DocAction.equals("??") || DocAction.equals("IP") || DocAction.equals("WP") || DocAction.equals("WC")); else throw new IllegalArgumentException ("DocAction Invalid value - " + DocAction + " - Reference_ID=131 - DR - CO - AP - NA - VO - IN - RE - CL - ?? - IP - WP - WC");		set_Value (COLUMNNAME_DocAction, DocAction);
	}

	/** Get Document Action.
		@return The targeted status of the document
	  */
	public String getDocAction () 
	{
		return (String)get_Value(COLUMNNAME_DocAction);
	}

	/** DocStatus AD_Reference_ID=131 */
	public static final int DOCSTATUS_AD_Reference_ID=131;
	/** Drafted = DR */
	public static final String DOCSTATUS_Drafted = "DR";
	/** Completed = CO */
	public static final String DOCSTATUS_Completed = "CO";
	/** Approved = AP */
	public static final String DOCSTATUS_Approved = "AP";
	/** Not Approved = NA */
	public static final String DOCSTATUS_NotApproved = "NA";
	/** Voided = VO */
	public static final String DOCSTATUS_Voided = "VO";
	/** Invalid = IN */
	public static final String DOCSTATUS_Invalid = "IN";
	/** Reversed = RE */
	public static final String DOCSTATUS_Reversed = "RE";
	/** Closed = CL */
	public static final String DOCSTATUS_Closed = "CL";
	/** Unknown = ?? */
	public static final String DOCSTATUS_Unknown = "??";
	/** In Progress = IP */
	public static final String DOCSTATUS_InProgress = "IP";
	/** Waiting Payment = WP */
	public static final String DOCSTATUS_WaitingPayment = "WP";
	/** Waiting Confirmation = WC */
	public static final String DOCSTATUS_WaitingConfirmation = "WC";
	/** Set Document Status.
		@param DocStatus 
		The current status of the document
	  */
	public void setDocStatus (String DocStatus)
	{
		if (DocStatus == null) throw new IllegalArgumentException ("DocStatus is mandatory");
		if (DocStatus.equals("DR") || DocStatus.equals("CO") || DocStatus.equals("AP") || DocStatus.equals("NA") || DocStatus.equals("VO") || DocStatus.equals("IN") || DocStatus.equals("RE") || DocStatus.equals("CL") || DocStatus.equals("??") || DocStatus.equals("IP") || DocStatus.equals("WP") || DocStatus.equals("WC")); else throw new IllegalArgumentException ("DocStatus Invalid value - " + DocStatus + " - Reference_ID=131 - DR - CO - AP - NA - VO - IN - RE - CL - ?? - IP - WP - WC");		set_Value (COLUMNNAME_DocStatus, DocStatus);
	}

	/** Get Document Status.
		@return The current status of the document
	  */
	public String getDocStatus () 
	{
		return (String)get_Value(COLUMNNAME_DocStatus);
	}

	/** Set Document No.
		@param DocumentNo 
		Document sequence number of the document
	  */
	public void setDocumentNo (String DocumentNo)
	{
		if (DocumentNo == null)
			throw new IllegalArgumentException ("DocumentNo is mandatory.");
		set_Value (COLUMNNAME_DocumentNo, DocumentNo);
	}

	/** Get Document No.
		@return Document sequence number of the document
	  */
	public String getDocumentNo () 
	{
		return (String)get_Value(COLUMNNAME_DocumentNo);
	}

	public I_EXME_Tratamientos_Detalle getEXME_Tratamientos_Detalle() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_Tratamientos_Detalle.Table_Name);
        I_EXME_Tratamientos_Detalle result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_Tratamientos_Detalle)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Tratamientos_Detalle_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Treatments Detail.
		@param EXME_Tratamientos_Detalle_ID Treatments Detail	  */
	public void setEXME_Tratamientos_Detalle_ID (int EXME_Tratamientos_Detalle_ID)
	{
		if (EXME_Tratamientos_Detalle_ID < 1)
			 throw new IllegalArgumentException ("EXME_Tratamientos_Detalle_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_Tratamientos_Detalle_ID, Integer.valueOf(EXME_Tratamientos_Detalle_ID));
	}

	/** Get Treatments Detail.
		@return Treatments Detail	  */
	public int getEXME_Tratamientos_Detalle_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Tratamientos_Detalle_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_TratamientosPaciente getEXME_TratamientosPaciente() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_TratamientosPaciente.Table_Name);
        I_EXME_TratamientosPaciente result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_TratamientosPaciente)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_TratamientosPaciente_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Patient Treatments.
		@param EXME_TratamientosPaciente_ID Patient Treatments	  */
	public void setEXME_TratamientosPaciente_ID (int EXME_TratamientosPaciente_ID)
	{
		if (EXME_TratamientosPaciente_ID < 1)
			 throw new IllegalArgumentException ("EXME_TratamientosPaciente_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_TratamientosPaciente_ID, Integer.valueOf(EXME_TratamientosPaciente_ID));
	}

	/** Get Patient Treatments.
		@return Patient Treatments	  */
	public int getEXME_TratamientosPaciente_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_TratamientosPaciente_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Treatment sessions.
		@param EXME_Tratamientos_Sesion_ID Treatment sessions	  */
	public void setEXME_Tratamientos_Sesion_ID (int EXME_Tratamientos_Sesion_ID)
	{
		if (EXME_Tratamientos_Sesion_ID < 1)
			 throw new IllegalArgumentException ("EXME_Tratamientos_Sesion_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_Tratamientos_Sesion_ID, Integer.valueOf(EXME_Tratamientos_Sesion_ID));
	}

	/** Get Treatment sessions.
		@return Treatment sessions	  */
	public int getEXME_Tratamientos_Sesion_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Tratamientos_Sesion_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Processed.
		@param Processed 
		The document has been processed
	  */
	public void setProcessed (boolean Processed)
	{
		set_Value (COLUMNNAME_Processed, Boolean.valueOf(Processed));
	}

	/** Get Processed.
		@return The document has been processed
	  */
	public boolean isProcessed () 
	{
		Object oo = get_Value(COLUMNNAME_Processed);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Process Now.
		@param Processing Process Now	  */
	public void setProcessing (boolean Processing)
	{
		set_Value (COLUMNNAME_Processing, Boolean.valueOf(Processing));
	}

	/** Get Process Now.
		@return Process Now	  */
	public boolean isProcessing () 
	{
		Object oo = get_Value(COLUMNNAME_Processing);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Sequence.
		@param Secuencia 
		Sequence
	  */
	public void setSecuencia (BigDecimal Secuencia)
	{
		set_Value (COLUMNNAME_Secuencia, Secuencia);
	}

	/** Get Sequence.
		@return Sequence
	  */
	public BigDecimal getSecuencia () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Secuencia);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}
}