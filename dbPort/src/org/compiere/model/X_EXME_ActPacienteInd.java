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

/** Generated Model for EXME_ActPacienteInd
 *  @author Generated Class 
 *  @version Version @VERSION@ - $Id$ */
public class X_EXME_ActPacienteInd extends PO implements I_EXME_ActPacienteInd, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_ActPacienteInd (Properties ctx, int EXME_ActPacienteInd_ID, String trxName)
    {
      super (ctx, EXME_ActPacienteInd_ID, trxName);
      /** if (EXME_ActPacienteInd_ID == 0)
        {
			setCgoProcesado (false);
			setChargeAmt (Env.ZERO);
			setCosto (Env.ZERO);
			setDateOrdered (new Timestamp( System.currentTimeMillis() ));
			setDocAction (null);
			setDocStatus (null);
			setEXME_ActPacienteIndH_ID (0);
			setEXME_ActPacienteInd_ID (0);
			setEXME_Area_ID (0);
			setFreightAmt (Env.ZERO);
			setIsDescription (false);
			setIsInstruction (false);
			setIsOBXReviewed (false);
			setLine (0);
			setLineNetAmt (Env.ZERO);
			setPriceActual (Env.ZERO);
			setPriceLimit (Env.ZERO);
			setPriceList (Env.ZERO);
			setQtyDelivered (Env.ZERO);
			setQtyEntered (Env.ZERO);
			setQtyInvoiced (Env.ZERO);
			setQtyOrdered (Env.ZERO);
			setQtyReserved (Env.ZERO);
			setTipoArea (null);
        } */
    }

    /** Load Constructor */
    public X_EXME_ActPacienteInd (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_ActPacienteInd[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Target Organization.
		@param AD_Org_Dest_ID 
		The organization to refer to
	  */
	public void setAD_Org_Dest_ID (int AD_Org_Dest_ID)
	{
		if (AD_Org_Dest_ID < 1) 
			set_Value (COLUMNNAME_AD_Org_Dest_ID, null);
		else 
			set_Value (COLUMNNAME_AD_Org_Dest_ID, Integer.valueOf(AD_Org_Dest_ID));
	}

	/** Get Target Organization.
		@return The organization to refer to
	  */
	public int getAD_Org_Dest_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_Org_Dest_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Trx Organization.
		@param AD_OrgTrx_ID 
		Performing or initiating organization
	  */
	public void setAD_OrgTrx_ID (int AD_OrgTrx_ID)
	{
		if (AD_OrgTrx_ID < 1) 
			set_Value (COLUMNNAME_AD_OrgTrx_ID, null);
		else 
			set_Value (COLUMNNAME_AD_OrgTrx_ID, Integer.valueOf(AD_OrgTrx_ID));
	}

	/** Get Trx Organization.
		@return Performing or initiating organization
	  */
	public int getAD_OrgTrx_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_OrgTrx_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Notes.
		@param Anotaciones 
		Notes related to the annexed image of the indication
	  */
	public void setAnotaciones (String Anotaciones)
	{
		set_Value (COLUMNNAME_Anotaciones, Anotaciones);
	}

	/** Get Notes.
		@return Notes related to the annexed image of the indication
	  */
	public String getAnotaciones () 
	{
		return (String)get_Value(COLUMNNAME_Anotaciones);
	}

	/** Set Billable.
		@param Billable Billable	  */
	public void setBillable (boolean Billable)
	{
		set_Value (COLUMNNAME_Billable, Boolean.valueOf(Billable));
	}

	/** Get Billable.
		@return Billable	  */
	public boolean isBillable () 
	{
		Object oo = get_Value(COLUMNNAME_Billable);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Canceled By.
		@param CanceledBy 
		Canceled By
	  */
	public void setCanceledBy (int CanceledBy)
	{
		set_Value (COLUMNNAME_CanceledBy, Integer.valueOf(CanceledBy));
	}

	/** Get Canceled By.
		@return Canceled By
	  */
	public int getCanceledBy () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_CanceledBy);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Amount to take.
		@param CantidadToma Amount to take	  */
	public void setCantidadToma (BigDecimal CantidadToma)
	{
		set_Value (COLUMNNAME_CantidadToma, CantidadToma);
	}

	/** Get Amount to take.
		@return Amount to take	  */
	public BigDecimal getCantidadToma () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_CantidadToma);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set CargosGenerados.
		@param CargosGenerados CargosGenerados	  */
	public void setCargosGenerados (boolean CargosGenerados)
	{
		set_Value (COLUMNNAME_CargosGenerados, Boolean.valueOf(CargosGenerados));
	}

	/** Get CargosGenerados.
		@return CargosGenerados	  */
	public boolean isCargosGenerados () 
	{
		Object oo = get_Value(COLUMNNAME_CargosGenerados);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Charge.
		@param C_Charge_ID 
		Additional document charges
	  */
	public void setC_Charge_ID (int C_Charge_ID)
	{
		if (C_Charge_ID < 1) 
			set_Value (COLUMNNAME_C_Charge_ID, null);
		else 
			set_Value (COLUMNNAME_C_Charge_ID, Integer.valueOf(C_Charge_ID));
	}

	/** Get Charge.
		@return Additional document charges
	  */
	public int getC_Charge_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Charge_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Currency.
		@param C_Currency_ID 
		The Currency for this record
	  */
	public void setC_Currency_ID (int C_Currency_ID)
	{
		if (C_Currency_ID < 1) 
			set_Value (COLUMNNAME_C_Currency_ID, null);
		else 
			set_Value (COLUMNNAME_C_Currency_ID, Integer.valueOf(C_Currency_ID));
	}

	/** Get Currency.
		@return The Currency for this record
	  */
	public int getC_Currency_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Currency_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Charge processed.
		@param CgoProcesado Charge processed	  */
	public void setCgoProcesado (boolean CgoProcesado)
	{
		set_Value (COLUMNNAME_CgoProcesado, Boolean.valueOf(CgoProcesado));
	}

	/** Get Charge processed.
		@return Charge processed	  */
	public boolean isCgoProcesado () 
	{
		Object oo = get_Value(COLUMNNAME_CgoProcesado);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Charge amount.
		@param ChargeAmt 
		Charge Amount
	  */
	public void setChargeAmt (BigDecimal ChargeAmt)
	{
		if (ChargeAmt == null)
			throw new IllegalArgumentException ("ChargeAmt is mandatory.");
		set_Value (COLUMNNAME_ChargeAmt, ChargeAmt);
	}

	/** Get Charge amount.
		@return Charge Amount
	  */
	public BigDecimal getChargeAmt () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_ChargeAmt);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Comments.
		@param Comments 
		Comments or additional information
	  */
	public void setComments (String Comments)
	{
		set_Value (COLUMNNAME_Comments, Comments);
	}

	/** Get Comments.
		@return Comments or additional information
	  */
	public String getComments () 
	{
		return (String)get_Value(COLUMNNAME_Comments);
	}

	/** ComponentType AD_Reference_ID=1200579 */
	public static final int COMPONENTTYPE_AD_Reference_ID=1200579;
	/** Technical = T */
	public static final String COMPONENTTYPE_Technical = "T";
	/** Professional = P */
	public static final String COMPONENTTYPE_Professional = "P";
	/** Set Component Type.
		@param ComponentType 
		Component Type for a Bill of Material or Formula
	  */
	public void setComponentType (String ComponentType)
	{

		if (ComponentType == null || ComponentType.equals("T") || ComponentType.equals("P")); else throw new IllegalArgumentException ("ComponentType Invalid value - " + ComponentType + " - Reference_ID=1200579 - T - P");		set_Value (COLUMNNAME_ComponentType, ComponentType);
	}

	/** Get Component Type.
		@return Component Type for a Bill of Material or Formula
	  */
	public String getComponentType () 
	{
		return (String)get_Value(COLUMNNAME_ComponentType);
	}

	/** Set Consulting Provider.
		@param ConsultingProvider 
		Consulting Provider / Facility
	  */
	public void setConsultingProvider (String ConsultingProvider)
	{
		set_Value (COLUMNNAME_ConsultingProvider, ConsultingProvider);
	}

	/** Get Consulting Provider.
		@return Consulting Provider / Facility
	  */
	public String getConsultingProvider () 
	{
		return (String)get_Value(COLUMNNAME_ConsultingProvider);
	}

	/** Set Cost.
		@param Costo 
		Cost
	  */
	public void setCosto (BigDecimal Costo)
	{
		if (Costo == null)
			throw new IllegalArgumentException ("Costo is mandatory.");
		set_Value (COLUMNNAME_Costo, Costo);
	}

	/** Get Cost.
		@return Cost
	  */
	public BigDecimal getCosto () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Costo);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Tax.
		@param C_Tax_ID 
		Tax identifier
	  */
	public void setC_Tax_ID (int C_Tax_ID)
	{
		if (C_Tax_ID < 1) 
			set_Value (COLUMNNAME_C_Tax_ID, null);
		else 
			set_Value (COLUMNNAME_C_Tax_ID, Integer.valueOf(C_Tax_ID));
	}

	/** Get Tax.
		@return Tax identifier
	  */
	public int getC_Tax_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Tax_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set UOM.
		@param C_UOM_ID 
		Unit of Measure
	  */
	public void setC_UOM_ID (int C_UOM_ID)
	{
		if (C_UOM_ID < 1) 
			set_Value (COLUMNNAME_C_UOM_ID, null);
		else 
			set_Value (COLUMNNAME_C_UOM_ID, Integer.valueOf(C_UOM_ID));
	}

	/** Get UOM.
		@return Unit of Measure
	  */
	public int getC_UOM_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_UOM_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Pack UOM.
		@param C_UOMVolume_ID 
		Unit of measure of volume or packing
	  */
	public void setC_UOMVolume_ID (int C_UOMVolume_ID)
	{
		if (C_UOMVolume_ID < 1) 
			set_Value (COLUMNNAME_C_UOMVolume_ID, null);
		else 
			set_Value (COLUMNNAME_C_UOMVolume_ID, Integer.valueOf(C_UOMVolume_ID));
	}

	/** Get Pack UOM.
		@return Unit of measure of volume or packing
	  */
	public int getC_UOMVolume_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_UOMVolume_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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

	/** Set Collection date.
		@param DateCollected 
		Specimen collection date
	  */
	public void setDateCollected (Timestamp DateCollected)
	{
		set_Value (COLUMNNAME_DateCollected, DateCollected);
	}

	/** Get Collection date.
		@return Specimen collection date
	  */
	public Timestamp getDateCollected () 
	{
		return (Timestamp)get_Value(COLUMNNAME_DateCollected);
	}

	/** Set Date Delivered.
		@param DateDelivered 
		Date when the product was delivered
	  */
	public void setDateDelivered (Timestamp DateDelivered)
	{
		set_Value (COLUMNNAME_DateDelivered, DateDelivered);
	}

	/** Get Date Delivered.
		@return Date when the product was delivered
	  */
	public Timestamp getDateDelivered () 
	{
		return (Timestamp)get_Value(COLUMNNAME_DateDelivered);
	}

	/** Set Date Invoiced.
		@param DateInvoiced 
		Date printed on Invoice
	  */
	public void setDateInvoiced (Timestamp DateInvoiced)
	{
		set_Value (COLUMNNAME_DateInvoiced, DateInvoiced);
	}

	/** Get Date Invoiced.
		@return Date printed on Invoice
	  */
	public Timestamp getDateInvoiced () 
	{
		return (Timestamp)get_Value(COLUMNNAME_DateInvoiced);
	}

	/** Set Date Ordered.
		@param DateOrdered 
		Date of Order
	  */
	public void setDateOrdered (Timestamp DateOrdered)
	{
		if (DateOrdered == null)
			throw new IllegalArgumentException ("DateOrdered is mandatory.");
		set_Value (COLUMNNAME_DateOrdered, DateOrdered);
	}

	/** Get Date Ordered.
		@return Date of Order
	  */
	public Timestamp getDateOrdered () 
	{
		return (Timestamp)get_Value(COLUMNNAME_DateOrdered);
	}

	/** Set Date Promised.
		@param DatePromised 
		Date Order was promised
	  */
	public void setDatePromised (Timestamp DatePromised)
	{
		set_Value (COLUMNNAME_DatePromised, DatePromised);
	}

	/** Get Date Promised.
		@return Date Order was promised
	  */
	public Timestamp getDatePromised () 
	{
		return (Timestamp)get_Value(COLUMNNAME_DatePromised);
	}

	/** Set Date received.
		@param DateReceived 
		Date a product was received
	  */
	public void setDateReceived (Timestamp DateReceived)
	{
		set_Value (COLUMNNAME_DateReceived, DateReceived);
	}

	/** Get Date received.
		@return Date a product was received
	  */
	public Timestamp getDateReceived () 
	{
		return (Timestamp)get_Value(COLUMNNAME_DateReceived);
	}

	/** Set Delivered By.
		@param DeliveredBy Delivered By	  */
	public void setDeliveredBy (int DeliveredBy)
	{
		set_Value (COLUMNNAME_DeliveredBy, Integer.valueOf(DeliveredBy));
	}

	/** Get Delivered By.
		@return Delivered By	  */
	public int getDeliveredBy () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DeliveredBy);
		if (ii == null)
			 return 0;
		return ii.intValue();
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

	/** Set Discount %.
		@param Discount 
		Discount in percent
	  */
	public void setDiscount (BigDecimal Discount)
	{
		set_Value (COLUMNNAME_Discount, Discount);
	}

	/** Get Discount %.
		@return Discount in percent
	  */
	public BigDecimal getDiscount () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Discount);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** DocAction AD_Reference_ID=135 */
	public static final int DOCACTION_AD_Reference_ID=135;
	/** Complete = CO */
	public static final String DOCACTION_Complete = "CO";
	/** Approve = AP */
	public static final String DOCACTION_Approve = "AP";
	/** Reject = RJ */
	public static final String DOCACTION_Reject = "RJ";
	/** Post = PO */
	public static final String DOCACTION_Post = "PO";
	/** Void = VO */
	public static final String DOCACTION_Void = "VO";
	/** Close = CL */
	public static final String DOCACTION_Close = "CL";
	/** Reverse - Correct = RC */
	public static final String DOCACTION_Reverse_Correct = "RC";
	/** Reverse - Accrual = RA */
	public static final String DOCACTION_Reverse_Accrual = "RA";
	/** Invalidate = IN */
	public static final String DOCACTION_Invalidate = "IN";
	/** Re-activate = RE */
	public static final String DOCACTION_Re_Activate = "RE";
	/** <None> = -- */
	public static final String DOCACTION_None = "--";
	/** Prepare = PR */
	public static final String DOCACTION_Prepare = "PR";
	/** Unlock = XL */
	public static final String DOCACTION_Unlock = "XL";
	/** Wait Complete = WC */
	public static final String DOCACTION_WaitComplete = "WC";
	/** Set Document Action.
		@param DocAction 
		The targeted status of the document
	  */
	public void setDocAction (String DocAction)
	{
		if (DocAction == null) throw new IllegalArgumentException ("DocAction is mandatory");
		if (DocAction.equals("CO") || DocAction.equals("AP") || DocAction.equals("RJ") || DocAction.equals("PO") || DocAction.equals("VO") || DocAction.equals("CL") || DocAction.equals("RC") || DocAction.equals("RA") || DocAction.equals("IN") || DocAction.equals("RE") || DocAction.equals("--") || DocAction.equals("PR") || DocAction.equals("XL") || DocAction.equals("WC")); else throw new IllegalArgumentException ("DocAction Invalid value - " + DocAction + " - Reference_ID=135 - CO - AP - RJ - PO - VO - CL - RC - RA - IN - RE - -- - PR - XL - WC");		set_Value (COLUMNNAME_DocAction, DocAction);
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

	/** Set Dose.
		@param Dose_txt Dose	  */
	public void setDose_txt (String Dose_txt)
	{
		set_Value (COLUMNNAME_Dose_txt, Dose_txt);
	}

	/** Get Dose.
		@return Dose	  */
	public String getDose_txt () 
	{
		return (String)get_Value(COLUMNNAME_Dose_txt);
	}

	/** Estatus AD_Reference_ID=1200114 */
	public static final int ESTATUS_AD_Reference_ID=1200114;
	/** Requested Service = S */
	public static final String ESTATUS_RequestedService = "S";
	/** Schedule Service = P */
	public static final String ESTATUS_ScheduleService = "P";
	/** Completed Service = C */
	public static final String ESTATUS_CompletedService = "C";
	/** Cancelled Service = A */
	public static final String ESTATUS_CancelledService = "A";
	/** Pending Service = W */
	public static final String ESTATUS_PendingService = "W";
	/** Set Status.
		@param Estatus 
		Status
	  */
	public void setEstatus (String Estatus)
	{

		if (Estatus == null || Estatus.equals("S") || Estatus.equals("P") || Estatus.equals("C") || Estatus.equals("A") || Estatus.equals("W")); else throw new IllegalArgumentException ("Estatus Invalid value - " + Estatus + " - Reference_ID=1200114 - S - P - C - A - W");		set_ValueNoCheck (COLUMNNAME_Estatus, Estatus);
	}

	/** Get Status.
		@return Status
	  */
	public String getEstatus () 
	{
		return (String)get_Value(COLUMNNAME_Estatus);
	}

	public I_EXME_ActPacienteIndH getEXME_ActPacienteIndH() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_ActPacienteIndH.Table_Name);
        I_EXME_ActPacienteIndH result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_ActPacienteIndH)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_ActPacienteIndH_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Patient's Indication.
		@param EXME_ActPacienteIndH_ID 
		Patient's Indication
	  */
	public void setEXME_ActPacienteIndH_ID (int EXME_ActPacienteIndH_ID)
	{
		if (EXME_ActPacienteIndH_ID < 1)
			 throw new IllegalArgumentException ("EXME_ActPacienteIndH_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_ActPacienteIndH_ID, Integer.valueOf(EXME_ActPacienteIndH_ID));
	}

	/** Get Patient's Indication.
		@return Patient's Indication
	  */
	public int getEXME_ActPacienteIndH_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_ActPacienteIndH_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Indication's detail.
		@param EXME_ActPacienteInd_ID 
		Indication's detail
	  */
	public void setEXME_ActPacienteInd_ID (int EXME_ActPacienteInd_ID)
	{
		if (EXME_ActPacienteInd_ID < 1)
			 throw new IllegalArgumentException ("EXME_ActPacienteInd_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_ActPacienteInd_ID, Integer.valueOf(EXME_ActPacienteInd_ID));
	}

	/** Get Indication's detail.
		@return Indication's detail
	  */
	public int getEXME_ActPacienteInd_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_ActPacienteInd_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Service.
		@param EXME_Area_ID 
		Service
	  */
	public void setEXME_Area_ID (int EXME_Area_ID)
	{
		if (EXME_Area_ID < 1)
			 throw new IllegalArgumentException ("EXME_Area_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_Area_ID, Integer.valueOf(EXME_Area_ID));
	}

	/** Get Service.
		@return Service
	  */
	public int getEXME_Area_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Area_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Second Diagnostic.
		@param EXME_Diagnostico2_ID 
		Second Diagnostic
	  */
	public void setEXME_Diagnostico2_ID (int EXME_Diagnostico2_ID)
	{
		if (EXME_Diagnostico2_ID < 1) 
			set_Value (COLUMNNAME_EXME_Diagnostico2_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_Diagnostico2_ID, Integer.valueOf(EXME_Diagnostico2_ID));
	}

	/** Get Second Diagnostic.
		@return Second Diagnostic
	  */
	public int getEXME_Diagnostico2_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Diagnostico2_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Third Diagnostic.
		@param EXME_Diagnostico3_ID 
		Third Diagnostic
	  */
	public void setEXME_Diagnostico3_ID (int EXME_Diagnostico3_ID)
	{
		if (EXME_Diagnostico3_ID < 1) 
			set_Value (COLUMNNAME_EXME_Diagnostico3_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_Diagnostico3_ID, Integer.valueOf(EXME_Diagnostico3_ID));
	}

	/** Get Third Diagnostic.
		@return Third Diagnostic
	  */
	public int getEXME_Diagnostico3_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Diagnostico3_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_Diagnostico getEXME_Diagnostico() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_Diagnostico.Table_Name);
        I_EXME_Diagnostico result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_Diagnostico)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Diagnostico_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Diagnosis.
		@param EXME_Diagnostico_ID 
		Diagnosis
	  */
	public void setEXME_Diagnostico_ID (int EXME_Diagnostico_ID)
	{
		if (EXME_Diagnostico_ID < 1) 
			set_Value (COLUMNNAME_EXME_Diagnostico_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_Diagnostico_ID, Integer.valueOf(EXME_Diagnostico_ID));
	}

	/** Get Diagnosis.
		@return Diagnosis
	  */
	public int getEXME_Diagnostico_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Diagnostico_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_Dosis getEXME_Dosis() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_Dosis.Table_Name);
        I_EXME_Dosis result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_Dosis)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Dosis_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Dose.
		@param EXME_Dosis_ID Dose	  */
	public void setEXME_Dosis_ID (int EXME_Dosis_ID)
	{
		if (EXME_Dosis_ID < 1) 
			set_Value (COLUMNNAME_EXME_Dosis_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_Dosis_ID, Integer.valueOf(EXME_Dosis_ID));
	}

	/** Get Dose.
		@return Dose	  */
	public int getEXME_Dosis_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Dosis_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_Equipo getEXME_Equipo() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_Equipo.Table_Name);
        I_EXME_Equipo result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_Equipo)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Equipo_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Equipment.
		@param EXME_Equipo_ID 
		Equipment
	  */
	public void setEXME_Equipo_ID (int EXME_Equipo_ID)
	{
		if (EXME_Equipo_ID < 1) 
			set_Value (COLUMNNAME_EXME_Equipo_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_Equipo_ID, Integer.valueOf(EXME_Equipo_ID));
	}

	/** Get Equipment.
		@return Equipment
	  */
	public int getEXME_Equipo_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Equipo_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_EspecimenCondicion getEXME_EspecimenCondicion() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_EspecimenCondicion.Table_Name);
        I_EXME_EspecimenCondicion result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_EspecimenCondicion)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_EspecimenCondicion_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Condition/Disposition of Specimen.
		@param EXME_EspecimenCondicion_ID 
		Condition/Disposition of Specimen
	  */
	public void setEXME_EspecimenCondicion_ID (int EXME_EspecimenCondicion_ID)
	{
		if (EXME_EspecimenCondicion_ID < 1) 
			set_Value (COLUMNNAME_EXME_EspecimenCondicion_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_EspecimenCondicion_ID, Integer.valueOf(EXME_EspecimenCondicion_ID));
	}

	/** Get Condition/Disposition of Specimen.
		@return Condition/Disposition of Specimen
	  */
	public int getEXME_EspecimenCondicion_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_EspecimenCondicion_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_Especimen getEXME_Especimen() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_Especimen.Table_Name);
        I_EXME_Especimen result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_Especimen)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Especimen_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Test Specimen.
		@param EXME_Especimen_ID 
		Test Specimen
	  */
	public void setEXME_Especimen_ID (int EXME_Especimen_ID)
	{
		if (EXME_Especimen_ID < 1) 
			set_Value (COLUMNNAME_EXME_Especimen_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_Especimen_ID, Integer.valueOf(EXME_Especimen_ID));
	}

	/** Get Test Specimen.
		@return Test Specimen
	  */
	public int getEXME_Especimen_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Especimen_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_EsqDesLine getEXME_EsqDesLine() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_EsqDesLine.Table_Name);
        I_EXME_EsqDesLine result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_EsqDesLine)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_EsqDesLine_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Price List Discount.
		@param EXME_EsqDesLine_ID 
		Lines of discount schema
	  */
	public void setEXME_EsqDesLine_ID (int EXME_EsqDesLine_ID)
	{
		if (EXME_EsqDesLine_ID < 1) 
			set_Value (COLUMNNAME_EXME_EsqDesLine_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_EsqDesLine_ID, Integer.valueOf(EXME_EsqDesLine_ID));
	}

	/** Get Price List Discount.
		@return Lines of discount schema
	  */
	public int getEXME_EsqDesLine_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_EsqDesLine_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_Farmacia getEXME_Farmacia() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_Farmacia.Table_Name);
        I_EXME_Farmacia result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_Farmacia)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Farmacia_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Pharmacy.
		@param EXME_Farmacia_ID Pharmacy	  */
	public void setEXME_Farmacia_ID (int EXME_Farmacia_ID)
	{
		if (EXME_Farmacia_ID < 1) 
			set_Value (COLUMNNAME_EXME_Farmacia_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_Farmacia_ID, Integer.valueOf(EXME_Farmacia_ID));
	}

	/** Get Pharmacy.
		@return Pharmacy	  */
	public int getEXME_Farmacia_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Farmacia_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_GenProduct getEXME_GenProduct() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_GenProduct.Table_Name);
        I_EXME_GenProduct result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_GenProduct)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_GenProduct_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Generic Product.
		@param EXME_GenProduct_ID Generic Product	  */
	public void setEXME_GenProduct_ID (int EXME_GenProduct_ID)
	{
		if (EXME_GenProduct_ID < 1) 
			set_Value (COLUMNNAME_EXME_GenProduct_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_GenProduct_ID, Integer.valueOf(EXME_GenProduct_ID));
	}

	/** Get Generic Product.
		@return Generic Product	  */
	public int getEXME_GenProduct_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_GenProduct_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_Institucion getEXME_Institucion() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_Institucion.Table_Name);
        I_EXME_Institucion result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_Institucion)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Institucion_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Service Facility.
		@param EXME_Institucion_ID 
		Service Facility
	  */
	public void setEXME_Institucion_ID (int EXME_Institucion_ID)
	{
		if (EXME_Institucion_ID < 1) 
			set_Value (COLUMNNAME_EXME_Institucion_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_Institucion_ID, Integer.valueOf(EXME_Institucion_ID));
	}

	/** Get Service Facility.
		@return Service Facility
	  */
	public int getEXME_Institucion_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Institucion_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_Instructions getEXME_Instructions() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_Instructions.Table_Name);
        I_EXME_Instructions result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_Instructions)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Instructions_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Instructions.
		@param EXME_Instructions_ID 
		General instructions
	  */
	public void setEXME_Instructions_ID (int EXME_Instructions_ID)
	{
		if (EXME_Instructions_ID < 1) 
			set_Value (COLUMNNAME_EXME_Instructions_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_Instructions_ID, Integer.valueOf(EXME_Instructions_ID));
	}

	/** Get Instructions.
		@return General instructions
	  */
	public int getEXME_Instructions_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Instructions_ID);
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
			set_Value (COLUMNNAME_EXME_Medico_ID, null);
		else 
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

	/** Set Performing Physician.
		@param EXME_MedicoPer_ID Performing Physician	  */
	public void setEXME_MedicoPer_ID (int EXME_MedicoPer_ID)
	{
		if (EXME_MedicoPer_ID < 1) 
			set_Value (COLUMNNAME_EXME_MedicoPer_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_MedicoPer_ID, Integer.valueOf(EXME_MedicoPer_ID));
	}

	/** Get Performing Physician.
		@return Performing Physician	  */
	public int getEXME_MedicoPer_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_MedicoPer_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_Modifiers getEXME_Modifiers() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_Modifiers.Table_Name);
        I_EXME_Modifiers result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_Modifiers)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Modifiers_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set EXME_Modifiers_ID.
		@param EXME_Modifiers_ID EXME_Modifiers_ID	  */
	public void setEXME_Modifiers_ID (int EXME_Modifiers_ID)
	{
		if (EXME_Modifiers_ID < 1) 
			set_Value (COLUMNNAME_EXME_Modifiers_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_Modifiers_ID, Integer.valueOf(EXME_Modifiers_ID));
	}

	/** Get EXME_Modifiers_ID.
		@return EXME_Modifiers_ID	  */
	public int getEXME_Modifiers_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Modifiers_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_MotivoCancel getEXME_MotivoCancel() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_MotivoCancel.Table_Name);
        I_EXME_MotivoCancel result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_MotivoCancel)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_MotivoCancel_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Cancel Reason.
		@param EXME_MotivoCancel_ID 
		Cancel Reason
	  */
	public void setEXME_MotivoCancel_ID (int EXME_MotivoCancel_ID)
	{
		if (EXME_MotivoCancel_ID < 1) 
			set_Value (COLUMNNAME_EXME_MotivoCancel_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_MotivoCancel_ID, Integer.valueOf(EXME_MotivoCancel_ID));
	}

	/** Get Cancel Reason.
		@return Cancel Reason
	  */
	public int getEXME_MotivoCancel_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_MotivoCancel_ID);
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

	public I_EXME_PerformingLab getEXME_PerformingLab() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_PerformingLab.Table_Name);
        I_EXME_PerformingLab result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_PerformingLab)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_PerformingLab_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Performing Lab.
		@param EXME_PerformingLab_ID Performing Lab	  */
	public void setEXME_PerformingLab_ID (int EXME_PerformingLab_ID)
	{
		if (EXME_PerformingLab_ID < 1) 
			set_Value (COLUMNNAME_EXME_PerformingLab_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_PerformingLab_ID, Integer.valueOf(EXME_PerformingLab_ID));
	}

	/** Get Performing Lab.
		@return Performing Lab	  */
	public int getEXME_PerformingLab_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_PerformingLab_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_POS getEXME_POS() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_POS.Table_Name);
        I_EXME_POS result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_POS)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_POS_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Place of Service.
		@param EXME_POS_ID 
		Place of Service
	  */
	public void setEXME_POS_ID (int EXME_POS_ID)
	{
		if (EXME_POS_ID < 1) 
			set_Value (COLUMNNAME_EXME_POS_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_POS_ID, Integer.valueOf(EXME_POS_ID));
	}

	/** Get Place of Service.
		@return Place of Service
	  */
	public int getEXME_POS_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_POS_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_ReferInpatient getEXME_ReferInpatient() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_ReferInpatient.Table_Name);
        I_EXME_ReferInpatient result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_ReferInpatient)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_ReferInpatient_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Inpatient Reference.
		@param EXME_ReferInpatient_ID Inpatient Reference	  */
	public void setEXME_ReferInpatient_ID (int EXME_ReferInpatient_ID)
	{
		if (EXME_ReferInpatient_ID < 1) 
			set_Value (COLUMNNAME_EXME_ReferInpatient_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_ReferInpatient_ID, Integer.valueOf(EXME_ReferInpatient_ID));
	}

	/** Get Inpatient Reference.
		@return Inpatient Reference	  */
	public int getEXME_ReferInpatient_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_ReferInpatient_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_RevenueCode getEXME_RevenueCode() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_RevenueCode.Table_Name);
        I_EXME_RevenueCode result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_RevenueCode)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_RevenueCode_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Revenue Code.
		@param EXME_RevenueCode_ID Revenue Code	  */
	public void setEXME_RevenueCode_ID (int EXME_RevenueCode_ID)
	{
		if (EXME_RevenueCode_ID < 1) 
			set_Value (COLUMNNAME_EXME_RevenueCode_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_RevenueCode_ID, Integer.valueOf(EXME_RevenueCode_ID));
	}

	/** Get Revenue Code.
		@return Revenue Code	  */
	public int getEXME_RevenueCode_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_RevenueCode_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_ViasAdministracion getEXME_ViasAdministracion() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_ViasAdministracion.Table_Name);
        I_EXME_ViasAdministracion result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_ViasAdministracion)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_ViasAdministracion_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Route of Administration.
		@param EXME_ViasAdministracion_ID Route of Administration	  */
	public void setEXME_ViasAdministracion_ID (int EXME_ViasAdministracion_ID)
	{
		if (EXME_ViasAdministracion_ID < 1) 
			set_Value (COLUMNNAME_EXME_ViasAdministracion_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_ViasAdministracion_ID, Integer.valueOf(EXME_ViasAdministracion_ID));
	}

	/** Get Route of Administration.
		@return Route of Administration	  */
	public int getEXME_ViasAdministracion_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_ViasAdministracion_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Folio.
		@param Folio Folio	  */
	public void setFolio (int Folio)
	{
		set_Value (COLUMNNAME_Folio, Integer.valueOf(Folio));
	}

	/** Get Folio.
		@return Folio	  */
	public int getFolio () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Folio);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Freight Amount.
		@param FreightAmt 
		Freight Amount 
	  */
	public void setFreightAmt (BigDecimal FreightAmt)
	{
		if (FreightAmt == null)
			throw new IllegalArgumentException ("FreightAmt is mandatory.");
		set_Value (COLUMNNAME_FreightAmt, FreightAmt);
	}

	/** Get Freight Amount.
		@return Freight Amount 
	  */
	public BigDecimal getFreightAmt () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_FreightAmt);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Image.
		@param Imagen 
		Name of stored image
	  */
	public void setImagen (String Imagen)
	{
		set_Value (COLUMNNAME_Imagen, Imagen);
	}

	/** Get Image.
		@return Name of stored image
	  */
	public String getImagen () 
	{
		return (String)get_Value(COLUMNNAME_Imagen);
	}

	/** Set Dispense as Written.
		@param IsDAW Dispense as Written	  */
	public void setIsDAW (boolean IsDAW)
	{
		set_Value (COLUMNNAME_IsDAW, Boolean.valueOf(IsDAW));
	}

	/** Get Dispense as Written.
		@return Dispense as Written	  */
	public boolean isDAW () 
	{
		Object oo = get_Value(COLUMNNAME_IsDAW);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Print DEA Number.
		@param IsDEA Print DEA Number	  */
	public void setIsDEA (boolean IsDEA)
	{
		set_Value (COLUMNNAME_IsDEA, Boolean.valueOf(IsDEA));
	}

	/** Get Print DEA Number.
		@return Print DEA Number	  */
	public boolean isDEA () 
	{
		Object oo = get_Value(COLUMNNAME_IsDEA);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Description Only.
		@param IsDescription 
		if true, the line is just description and no transaction
	  */
	public void setIsDescription (boolean IsDescription)
	{
		set_Value (COLUMNNAME_IsDescription, Boolean.valueOf(IsDescription));
	}

	/** Get Description Only.
		@return if true, the line is just description and no transaction
	  */
	public boolean isDescription () 
	{
		Object oo = get_Value(COLUMNNAME_IsDescription);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set External.
		@param IsExternal 
		External
	  */
	public void setIsExternal (boolean IsExternal)
	{
		set_Value (COLUMNNAME_IsExternal, Boolean.valueOf(IsExternal));
	}

	/** Get External.
		@return External
	  */
	public boolean isExternal () 
	{
		Object oo = get_Value(COLUMNNAME_IsExternal);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Is Instruction.
		@param IsInstruction 
		Indicates if is an instruction
	  */
	public void setIsInstruction (boolean IsInstruction)
	{
		set_Value (COLUMNNAME_IsInstruction, Boolean.valueOf(IsInstruction));
	}

	/** Get Is Instruction.
		@return Indicates if is an instruction
	  */
	public boolean isInstruction () 
	{
		Object oo = get_Value(COLUMNNAME_IsInstruction);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Is OBX Reviewed.
		@param IsOBXReviewed 
		Is OBX Reviewed
	  */
	public void setIsOBXReviewed (boolean IsOBXReviewed)
	{
		set_Value (COLUMNNAME_IsOBXReviewed, Boolean.valueOf(IsOBXReviewed));
	}

	/** Get Is OBX Reviewed.
		@return Is OBX Reviewed
	  */
	public boolean isOBXReviewed () 
	{
		Object oo = get_Value(COLUMNNAME_IsOBXReviewed);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set When necessary.
		@param IsPRN When necessary	  */
	public void setIsPRN (boolean IsPRN)
	{
		set_Value (COLUMNNAME_IsPRN, Boolean.valueOf(IsPRN));
	}

	/** Get When necessary.
		@return When necessary	  */
	public boolean isPRN () 
	{
		Object oo = get_Value(COLUMNNAME_IsPRN);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Today Service.
		@param IsTodayService 
		Today Service
	  */
	public void setIsTodayService (boolean IsTodayService)
	{
		set_Value (COLUMNNAME_IsTodayService, Boolean.valueOf(IsTodayService));
	}

	/** Get Today Service.
		@return Today Service
	  */
	public boolean isTodayService () 
	{
		Object oo = get_Value(COLUMNNAME_IsTodayService);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Line No.
		@param Line 
		Unique line for this document
	  */
	public void setLine (int Line)
	{
		set_Value (COLUMNNAME_Line, Integer.valueOf(Line));
	}

	/** Get Line No.
		@return Unique line for this document
	  */
	public int getLine () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Line);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Line Amount.
		@param LineNetAmt 
		Line Extended Amount (Quantity * Actual Price) without Freight and Charges
	  */
	public void setLineNetAmt (BigDecimal LineNetAmt)
	{
		if (LineNetAmt == null)
			throw new IllegalArgumentException ("LineNetAmt is mandatory.");
		set_Value (COLUMNNAME_LineNetAmt, LineNetAmt);
	}

	/** Get Line Amount.
		@return Line Extended Amount (Quantity * Actual Price) without Freight and Charges
	  */
	public BigDecimal getLineNetAmt () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_LineNetAmt);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Lote.
		@param Lote Lote	  */
	public void setLote (String Lote)
	{
		set_Value (COLUMNNAME_Lote, Lote);
	}

	/** Get Lote.
		@return Lote	  */
	public String getLote () 
	{
		return (String)get_Value(COLUMNNAME_Lote);
	}

	/** Set Attribute Set Instance.
		@param M_AttributeSetInstance_ID 
		Product Attribute Set Instance
	  */
	public void setM_AttributeSetInstance_ID (int M_AttributeSetInstance_ID)
	{
		if (M_AttributeSetInstance_ID < 1) 
			set_Value (COLUMNNAME_M_AttributeSetInstance_ID, null);
		else 
			set_Value (COLUMNNAME_M_AttributeSetInstance_ID, Integer.valueOf(M_AttributeSetInstance_ID));
	}

	/** Get Attribute Set Instance.
		@return Product Attribute Set Instance
	  */
	public int getM_AttributeSetInstance_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_AttributeSetInstance_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_M_InOutLine getM_InOutLine() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_M_InOutLine.Table_Name);
        I_M_InOutLine result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_M_InOutLine)constructor.newInstance(new Object[] {getCtx(), new Integer(getM_InOutLine_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Shipment/Receipt Line.
		@param M_InOutLine_ID 
		Line on Shipment or Receipt document
	  */
	public void setM_InOutLine_ID (int M_InOutLine_ID)
	{
		if (M_InOutLine_ID < 1) 
			set_Value (COLUMNNAME_M_InOutLine_ID, null);
		else 
			set_Value (COLUMNNAME_M_InOutLine_ID, Integer.valueOf(M_InOutLine_ID));
	}

	/** Get Shipment/Receipt Line.
		@return Line on Shipment or Receipt document
	  */
	public int getM_InOutLine_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_InOutLine_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Comment of the reason for suspension.
		@param MotivoCancelacion 
		Comment of the reason for suspension
	  */
	public void setMotivoCancelacion (String MotivoCancelacion)
	{
		set_Value (COLUMNNAME_MotivoCancelacion, MotivoCancelacion);
	}

	/** Get Comment of the reason for suspension.
		@return Comment of the reason for suspension
	  */
	public String getMotivoCancelacion () 
	{
		return (String)get_Value(COLUMNNAME_MotivoCancelacion);
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

	public I_M_Shipper getM_Shipper() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_M_Shipper.Table_Name);
        I_M_Shipper result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_M_Shipper)constructor.newInstance(new Object[] {getCtx(), new Integer(getM_Shipper_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Shipper.
		@param M_Shipper_ID 
		Method or manner of product delivery
	  */
	public void setM_Shipper_ID (int M_Shipper_ID)
	{
		if (M_Shipper_ID < 1) 
			set_Value (COLUMNNAME_M_Shipper_ID, null);
		else 
			set_Value (COLUMNNAME_M_Shipper_ID, Integer.valueOf(M_Shipper_ID));
	}

	/** Get Shipper.
		@return Method or manner of product delivery
	  */
	public int getM_Shipper_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_Shipper_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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

	/** Set Number of days.
		@param NumDias Number of days	  */
	public void setNumDias (BigDecimal NumDias)
	{
		set_Value (COLUMNNAME_NumDias, NumDias);
	}

	/** Get Number of days.
		@return Number of days	  */
	public BigDecimal getNumDias () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_NumDias);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Other Instructions.
		@param OtherInstructions 
		Other Instructions
	  */
	public void setOtherInstructions (String OtherInstructions)
	{
		set_Value (COLUMNNAME_OtherInstructions, OtherInstructions);
	}

	/** Get Other Instructions.
		@return Other Instructions
	  */
	public String getOtherInstructions () 
	{
		return (String)get_Value(COLUMNNAME_OtherInstructions);
	}

	/** Set Unit Price.
		@param PriceActual 
		Actual Price 
	  */
	public void setPriceActual (BigDecimal PriceActual)
	{
		if (PriceActual == null)
			throw new IllegalArgumentException ("PriceActual is mandatory.");
		set_Value (COLUMNNAME_PriceActual, PriceActual);
	}

	/** Get Unit Price.
		@return Actual Price 
	  */
	public BigDecimal getPriceActual () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_PriceActual);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Limit Price.
		@param PriceLimit 
		Lowest price for a product
	  */
	public void setPriceLimit (BigDecimal PriceLimit)
	{
		if (PriceLimit == null)
			throw new IllegalArgumentException ("PriceLimit is mandatory.");
		set_Value (COLUMNNAME_PriceLimit, PriceLimit);
	}

	/** Get Limit Price.
		@return Lowest price for a product
	  */
	public BigDecimal getPriceLimit () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_PriceLimit);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set List Price.
		@param PriceList 
		List Price
	  */
	public void setPriceList (BigDecimal PriceList)
	{
		if (PriceList == null)
			throw new IllegalArgumentException ("PriceList is mandatory.");
		set_Value (COLUMNNAME_PriceList, PriceList);
	}

	/** Get List Price.
		@return List Price
	  */
	public BigDecimal getPriceList () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_PriceList);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** PriorityRule AD_Reference_ID=1200573 */
	public static final int PRIORITYRULE_AD_Reference_ID=1200573;
	/** STAT = 1 */
	public static final String PRIORITYRULE_STAT = "1";
	/** CALL BACK/FAX = 11 */
	public static final String PRIORITYRULE_CALLBACKFAX = "11";
	/** NURSE COLLECT = 13 */
	public static final String PRIORITYRULE_NURSECOLLECT = "13";
	/** ASAP = 3 */
	public static final String PRIORITYRULE_ASAP = "3";
	/** TIMED = 5 */
	public static final String PRIORITYRULE_TIMED = "5";
	/** ROUTINE = 7 */
	public static final String PRIORITYRULE_ROUTINE = "7";
	/** EARLY AM = 9 */
	public static final String PRIORITYRULE_EARLYAM = "9";
	/** 0 = 0 */
	public static final String PRIORITYRULE_0 = "0";
	/** Set Priority.
		@param PriorityRule 
		Priority of a document
	  */
	public void setPriorityRule (String PriorityRule)
	{

		if (PriorityRule == null || PriorityRule.equals("1") || PriorityRule.equals("11") || PriorityRule.equals("13") || PriorityRule.equals("3") || PriorityRule.equals("5") || PriorityRule.equals("7") || PriorityRule.equals("9") || PriorityRule.equals("0")); else throw new IllegalArgumentException ("PriorityRule Invalid value - " + PriorityRule + " - Reference_ID=1200573 - 1 - 11 - 13 - 3 - 5 - 7 - 9 - 0");		set_Value (COLUMNNAME_PriorityRule, PriorityRule);
	}

	/** Get Priority.
		@return Priority of a document
	  */
	public String getPriorityRule () 
	{
		return (String)get_Value(COLUMNNAME_PriorityRule);
	}

	/** Set Process Now.
		@param Processing Process Now	  */
	public void setProcessing (boolean Processing)
	{
		throw new IllegalArgumentException ("Processing is virtual column");	}

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

	/** Set Product Substitute.
		@param Prod_Substitute_ID 
		Product Substitute
	  */
	public void setProd_Substitute_ID (int Prod_Substitute_ID)
	{
		if (Prod_Substitute_ID < 1) 
			set_Value (COLUMNNAME_Prod_Substitute_ID, null);
		else 
			set_Value (COLUMNNAME_Prod_Substitute_ID, Integer.valueOf(Prod_Substitute_ID));
	}

	/** Get Product Substitute.
		@return Product Substitute
	  */
	public int getProd_Substitute_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Prod_Substitute_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Supplier.
		@param Proveedor Supplier	  */
	public void setProveedor (String Proveedor)
	{
		set_Value (COLUMNNAME_Proveedor, Proveedor);
	}

	/** Get Supplier.
		@return Supplier	  */
	public String getProveedor () 
	{
		return (String)get_Value(COLUMNNAME_Proveedor);
	}

	/** Set Delivered Quantity.
		@param QtyDelivered 
		Delivered Quantity
	  */
	public void setQtyDelivered (BigDecimal QtyDelivered)
	{
		if (QtyDelivered == null)
			throw new IllegalArgumentException ("QtyDelivered is mandatory.");
		set_Value (COLUMNNAME_QtyDelivered, QtyDelivered);
	}

	/** Get Delivered Quantity.
		@return Delivered Quantity
	  */
	public BigDecimal getQtyDelivered () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_QtyDelivered);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Quantity.
		@param QtyEntered 
		The Quantity Entered is based on the selected UoM
	  */
	public void setQtyEntered (BigDecimal QtyEntered)
	{
		if (QtyEntered == null)
			throw new IllegalArgumentException ("QtyEntered is mandatory.");
		set_Value (COLUMNNAME_QtyEntered, QtyEntered);
	}

	/** Get Quantity.
		@return The Quantity Entered is based on the selected UoM
	  */
	public BigDecimal getQtyEntered () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_QtyEntered);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Quantity Invoiced.
		@param QtyInvoiced 
		Invoiced Quantity
	  */
	public void setQtyInvoiced (BigDecimal QtyInvoiced)
	{
		if (QtyInvoiced == null)
			throw new IllegalArgumentException ("QtyInvoiced is mandatory.");
		set_Value (COLUMNNAME_QtyInvoiced, QtyInvoiced);
	}

	/** Get Quantity Invoiced.
		@return Invoiced Quantity
	  */
	public BigDecimal getQtyInvoiced () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_QtyInvoiced);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Ordered Quantity.
		@param QtyOrdered 
		Ordered Quantity
	  */
	public void setQtyOrdered (BigDecimal QtyOrdered)
	{
		if (QtyOrdered == null)
			throw new IllegalArgumentException ("QtyOrdered is mandatory.");
		set_Value (COLUMNNAME_QtyOrdered, QtyOrdered);
	}

	/** Get Ordered Quantity.
		@return Ordered Quantity
	  */
	public BigDecimal getQtyOrdered () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_QtyOrdered);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Ordered Qty Pack.
		@param QtyOrdered_Vol 
		Ordered Quantity Packs ordered
	  */
	public void setQtyOrdered_Vol (BigDecimal QtyOrdered_Vol)
	{
		set_Value (COLUMNNAME_QtyOrdered_Vol, QtyOrdered_Vol);
	}

	/** Get Ordered Qty Pack.
		@return Ordered Quantity Packs ordered
	  */
	public BigDecimal getQtyOrdered_Vol () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_QtyOrdered_Vol);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Reserved Quantity.
		@param QtyReserved 
		Reserved Quantity
	  */
	public void setQtyReserved (BigDecimal QtyReserved)
	{
		if (QtyReserved == null)
			throw new IllegalArgumentException ("QtyReserved is mandatory.");
		set_Value (COLUMNNAME_QtyReserved, QtyReserved);
	}

	/** Get Reserved Quantity.
		@return Reserved Quantity
	  */
	public BigDecimal getQtyReserved () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_QtyReserved);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Quantity.
		@param Quantity_txt Quantity	  */
	public void setQuantity_txt (String Quantity_txt)
	{
		set_Value (COLUMNNAME_Quantity_txt, Quantity_txt);
	}

	/** Get Quantity.
		@return Quantity	  */
	public String getQuantity_txt () 
	{
		return (String)get_Value(COLUMNNAME_Quantity_txt);
	}

	/** Set Reference to Patient Activity  Indications.
		@param Ref_ActPacienteInd_ID 
		Reference to Patient Activity Indications
	  */
	public void setRef_ActPacienteInd_ID (int Ref_ActPacienteInd_ID)
	{
		if (Ref_ActPacienteInd_ID < 1) 
			set_Value (COLUMNNAME_Ref_ActPacienteInd_ID, null);
		else 
			set_Value (COLUMNNAME_Ref_ActPacienteInd_ID, Integer.valueOf(Ref_ActPacienteInd_ID));
	}

	/** Get Reference to Patient Activity  Indications.
		@return Reference to Patient Activity Indications
	  */
	public int getRef_ActPacienteInd_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Ref_ActPacienteInd_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** RequestType AD_Reference_ID=1200526 */
	public static final int REQUESTTYPE_AD_Reference_ID=1200526;
	/** Cancel = 3 */
	public static final String REQUESTTYPE_Cancel = "3";
	/** Refill = 1 */
	public static final String REQUESTTYPE_Refill = "1";
	/** Change = 2 */
	public static final String REQUESTTYPE_Change = "2";
	/** None = 0 */
	public static final String REQUESTTYPE_None = "0";
	/** Error = 4 */
	public static final String REQUESTTYPE_Error = "4";
	/** Verify = 5 */
	public static final String REQUESTTYPE_Verify = "5";
	/** FreeStandingError = 6 */
	public static final String REQUESTTYPE_FreeStandingError = "6";
	/** Set Request Type.
		@param RequestType Request Type	  */
	public void setRequestType (String RequestType)
	{

		if (RequestType == null || RequestType.equals("3") || RequestType.equals("1") || RequestType.equals("2") || RequestType.equals("0") || RequestType.equals("4") || RequestType.equals("5") || RequestType.equals("6")); else throw new IllegalArgumentException ("RequestType Invalid value - " + RequestType + " - Reference_ID=1200526 - 3 - 1 - 2 - 0 - 4 - 5 - 6");		set_Value (COLUMNNAME_RequestType, RequestType);
	}

	/** Get Request Type.
		@return Request Type	  */
	public String getRequestType () 
	{
		return (String)get_Value(COLUMNNAME_RequestType);
	}

	/** ResultStatus AD_Reference_ID=1200472 */
	public static final int RESULTSTATUS_AD_Reference_ID=1200472;
	/** Order Received = O */
	public static final String RESULTSTATUS_OrderReceived = "O";
	/** Not Results aviable; procedure incomplete = I */
	public static final String RESULTSTATUS_NotResultsAviableProcedureIncomplete = "I";
	/** Procedure Scheduled = S */
	public static final String RESULTSTATUS_ProcedureScheduled = "S";
	/** Some but no all results aviable = A */
	public static final String RESULTSTATUS_SomeButNoAllResultsAviable = "A";
	/** Preliminary = P */
	public static final String RESULTSTATUS_Preliminary = "P";
	/** Correction to results = C */
	public static final String RESULTSTATUS_CorrectionToResults = "C";
	/** Results stored, not yet verified = R */
	public static final String RESULTSTATUS_ResultsStoredNotYetVerified = "R";
	/** Final Results = F */
	public static final String RESULTSTATUS_FinalResults = "F";
	/** No results aviable, Order canceled = X */
	public static final String RESULTSTATUS_NoResultsAviableOrderCanceled = "X";
	/** Set Result Status.
		@param ResultStatus Result Status	  */
	public void setResultStatus (String ResultStatus)
	{

		if (ResultStatus == null || ResultStatus.equals("O") || ResultStatus.equals("I") || ResultStatus.equals("S") || ResultStatus.equals("A") || ResultStatus.equals("P") || ResultStatus.equals("C") || ResultStatus.equals("R") || ResultStatus.equals("F") || ResultStatus.equals("X")); else throw new IllegalArgumentException ("ResultStatus Invalid value - " + ResultStatus + " - Reference_ID=1200472 - O - I - S - A - P - C - R - F - X");		set_Value (COLUMNNAME_ResultStatus, ResultStatus);
	}

	/** Get Result Status.
		@return Result Status	  */
	public String getResultStatus () 
	{
		return (String)get_Value(COLUMNNAME_ResultStatus);
	}

	/** Set Refills.
		@param Resurtidos Refills	  */
	public void setResurtidos (int Resurtidos)
	{
		set_Value (COLUMNNAME_Resurtidos, Integer.valueOf(Resurtidos));
	}

	/** Get Refills.
		@return Refills	  */
	public int getResurtidos () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Resurtidos);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set RIS Application Number.
		@param RisID RIS Application Number	  */
	public void setRisID (int RisID)
	{
		set_Value (COLUMNNAME_RisID, Integer.valueOf(RisID));
	}

	/** Get RIS Application Number.
		@return RIS Application Number	  */
	public int getRisID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_RisID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Sequence.
		@param Sequence Sequence	  */
	public void setSequence (int Sequence)
	{
		set_Value (COLUMNNAME_Sequence, Integer.valueOf(Sequence));
	}

	/** Get Sequence.
		@return Sequence	  */
	public int getSequence () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Sequence);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Deliver.
		@param Surtir 
		If the product or service will deliver internally
	  */
	public void setSurtir (boolean Surtir)
	{
		set_Value (COLUMNNAME_Surtir, Boolean.valueOf(Surtir));
	}

	/** Get Deliver.
		@return If the product or service will deliver internally
	  */
	public boolean isSurtir () 
	{
		Object oo = get_Value(COLUMNNAME_Surtir);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** TipoArea AD_Reference_ID=1000039 */
	public static final int TIPOAREA_AD_Reference_ID=1000039;
	/** Hospitalization = H */
	public static final String TIPOAREA_Hospitalization = "H";
	/** Emergency = U */
	public static final String TIPOAREA_Emergency = "U";
	/** Ambulatory = A */
	public static final String TIPOAREA_Ambulatory = "A";
	/** Medical Consultation = C */
	public static final String TIPOAREA_MedicalConsultation = "C";
	/** Procedures = P */
	public static final String TIPOAREA_Procedures = "P";
	/** Medical Records = R */
	public static final String TIPOAREA_MedicalRecords = "R";
	/** Other = O */
	public static final String TIPOAREA_Other = "O";
	/** External = E */
	public static final String TIPOAREA_External = "E";
	/** Admission = D */
	public static final String TIPOAREA_Admission = "D";
	/** Social Work = T */
	public static final String TIPOAREA_SocialWork = "T";
	/** Social Comunication = S */
	public static final String TIPOAREA_SocialComunication = "S";
	/** Set Area Type.
		@param TipoArea 
		Admission Area Type
	  */
	public void setTipoArea (String TipoArea)
	{
		if (TipoArea == null) throw new IllegalArgumentException ("TipoArea is mandatory");
		if (TipoArea.equals("H") || TipoArea.equals("U") || TipoArea.equals("A") || TipoArea.equals("C") || TipoArea.equals("P") || TipoArea.equals("R") || TipoArea.equals("O") || TipoArea.equals("E") || TipoArea.equals("D") || TipoArea.equals("T") || TipoArea.equals("S")); else throw new IllegalArgumentException ("TipoArea Invalid value - " + TipoArea + " - Reference_ID=1000039 - H - U - A - C - P - R - O - E - D - T - S");		set_Value (COLUMNNAME_TipoArea, TipoArea);
	}

	/** Get Area Type.
		@return Admission Area Type
	  */
	public String getTipoArea () 
	{
		return (String)get_Value(COLUMNNAME_TipoArea);
	}

	/** TipoSurtido AD_Reference_ID=1200369 */
	public static final int TIPOSURTIDO_AD_Reference_ID=1200369;
	/** No product selection indicated = 0 */
	public static final String TIPOSURTIDO_NoProductSelectionIndicated = "0";
	/** Substitution Not Allowed by Prescriber = 1 */
	public static final String TIPOSURTIDO_SubstitutionNotAllowedByPrescriber = "1";
	/** Substitution Allowed?Patient Requested Product Dispensed = 2 */
	public static final String TIPOSURTIDO_SubstitutionAllowedPatientRequestedProductDispensed = "2";
	/** Substitution Allowed?Pharmacist Selected Product Dispensed = 3 */
	public static final String TIPOSURTIDO_SubstitutionAllowedPharmacistSelectedProductDispensed = "3";
	/** Substitution Allowed?Generic Drug Not in Stock = 4 */
	public static final String TIPOSURTIDO_SubstitutionAllowedGenericDrugNotInStock = "4";
	/** Substitution Allowed?Brand Drug Dispensed as a Generic = 5 */
	public static final String TIPOSURTIDO_SubstitutionAllowedBrandDrugDispensedAsAGeneric = "5";
	/** Override = 6 */
	public static final String TIPOSURTIDO_Override = "6";
	/** Substitution Not Allowed?Brand Drug Mandated by Law = 7 */
	public static final String TIPOSURTIDO_SubstitutionNotAllowedBrandDrugMandatedByLaw = "7";
	/** Substitution Allowed?Generic Drug Not Available in Marketpla = 8 */
	public static final String TIPOSURTIDO_SubstitutionAllowedGenericDrugNotAvailableInMarketpla = "8";
	/** Other = 9 */
	public static final String TIPOSURTIDO_Other = "9";
	/** Set Dispense as written.
		@param TipoSurtido 
		Dispense as written  (DAW)
	  */
	public void setTipoSurtido (String TipoSurtido)
	{

		if (TipoSurtido == null || TipoSurtido.equals("0") || TipoSurtido.equals("1") || TipoSurtido.equals("2") || TipoSurtido.equals("3") || TipoSurtido.equals("4") || TipoSurtido.equals("5") || TipoSurtido.equals("6") || TipoSurtido.equals("7") || TipoSurtido.equals("8") || TipoSurtido.equals("9")); else throw new IllegalArgumentException ("TipoSurtido Invalid value - " + TipoSurtido + " - Reference_ID=1200369 - 0 - 1 - 2 - 3 - 4 - 5 - 6 - 7 - 8 - 9");		set_Value (COLUMNNAME_TipoSurtido, TipoSurtido);
	}

	/** Get Dispense as written.
		@return Dispense as written  (DAW)
	  */
	public String getTipoSurtido () 
	{
		return (String)get_Value(COLUMNNAME_TipoSurtido);
	}

	/** Set Taken at home.
		@param TomadoCasa Taken at home	  */
	public void setTomadoCasa (boolean TomadoCasa)
	{
		set_Value (COLUMNNAME_TomadoCasa, Boolean.valueOf(TomadoCasa));
	}

	/** Get Taken at home.
		@return Taken at home	  */
	public boolean isTomadoCasa () 
	{
		Object oo = get_Value(COLUMNNAME_TomadoCasa);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Day Timing.
		@param VecesDia Day Timing	  */
	public void setVecesDia (BigDecimal VecesDia)
	{
		set_Value (COLUMNNAME_VecesDia, VecesDia);
	}

	/** Get Day Timing.
		@return Day Timing	  */
	public BigDecimal getVecesDia () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_VecesDia);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Warehouse address.
		@param WarehouseLocation 
		Warehouse address
	  */
	public void setWarehouseLocation (String WarehouseLocation)
	{
		set_Value (COLUMNNAME_WarehouseLocation, WarehouseLocation);
	}

	/** Get Warehouse address.
		@return Warehouse address
	  */
	public String getWarehouseLocation () 
	{
		return (String)get_Value(COLUMNNAME_WarehouseLocation);
	}

	/** Set Warehouse Name.
		@param WarehouseName 
		Warehouse Name
	  */
	public void setWarehouseName (String WarehouseName)
	{
		set_Value (COLUMNNAME_WarehouseName, WarehouseName);
	}

	/** Get Warehouse Name.
		@return Warehouse Name
	  */
	public String getWarehouseName () 
	{
		return (String)get_Value(COLUMNNAME_WarehouseName);
	}
}