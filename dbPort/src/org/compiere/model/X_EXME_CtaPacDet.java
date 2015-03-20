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

/** Generated Model for EXME_CtaPacDet
 *  @author Generated Class 
 *  @version Version @VERSION@ - $Id$ */
public class X_EXME_CtaPacDet extends PO implements I_EXME_CtaPacDet, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_CtaPacDet (Properties ctx, int EXME_CtaPacDet_ID, String trxName)
    {
      super (ctx, EXME_CtaPacDet_ID, trxName);
      /** if (EXME_CtaPacDet_ID == 0)
        {
			setAD_Session_ID (0);
			setAD_User_ID (0);
			setAR (false);
			setBillingType (null);
// 'S'
			setBorrarConv (false);
// N
			setCalcularPrecio (true);
// Y
			setC_Currency_ID (0);
			setCgoProcesado (false);
			setChargeAmt (Env.ZERO);
			setCosto (Env.ZERO);
			setC_Tax_ID (0);
			setDateOrdered (new Timestamp( System.currentTimeMillis() ));
			setDiscountTaxAmt (Env.ZERO);
			setEXME_Area_ID (0);
			setEXME_CtaPacDet_ID (0);
			setEXME_CtaPacExt_ID (0);
			setEXME_CtaPac_ID (0);
			setFreightAmt (Env.ZERO);
			setIsDescription (false);
			setIsFacturado (false);
			setLine (0);
			setLineNetAmt (Env.ZERO);
			setM_InOut_ID (0);
			setM_Movement_ID (0);
			setPrecioPublico (Env.ZERO);
			setPriceActual (Env.ZERO);
			setPriceActualInv (Env.ZERO);
			setPriceLimit (Env.ZERO);
			setPriceLimitInv (Env.ZERO);
			setPriceList (Env.ZERO);
			setPriceListInv (Env.ZERO);
			setQtyDelivered (Env.ZERO);
			setQtyEntered (Env.ZERO);
			setQtyInvoiced (Env.ZERO);
			setQtyOrdered (Env.ZERO);
			setQtyPaquete (Env.ZERO);
			setQtyPendiente (Env.ZERO);
			setQtyReserved (Env.ZERO);
			setSeDevolvio (false);
			setTaxAmt (Env.ZERO);
			setTipoArea (null);
// "H"
			setTipoLinea (null);
// "CG"
			setUsarFactor (false);
// N
			setVisible (true);
// Y
        } */
    }

    /** Load Constructor */
    public X_EXME_CtaPacDet (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 1 - Org 
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
      StringBuffer sb = new StringBuffer ("X_EXME_CtaPacDet[")
        .append(get_ID()).append("]");
      return sb.toString();
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

	public I_AD_PInstance getAD_PInstance() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_AD_PInstance.Table_Name);
        I_AD_PInstance result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_AD_PInstance)constructor.newInstance(new Object[] {getCtx(), new Integer(getAD_PInstance_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Process Instance.
		@param AD_PInstance_ID 
		Instance of the process
	  */
	public void setAD_PInstance_ID (int AD_PInstance_ID)
	{
		if (AD_PInstance_ID < 1) 
			set_Value (COLUMNNAME_AD_PInstance_ID, null);
		else 
			set_Value (COLUMNNAME_AD_PInstance_ID, Integer.valueOf(AD_PInstance_ID));
	}

	/** Get Process Instance.
		@return Instance of the process
	  */
	public int getAD_PInstance_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_PInstance_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_AD_Session getAD_Session() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_AD_Session.Table_Name);
        I_AD_Session result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_AD_Session)constructor.newInstance(new Object[] {getCtx(), new Integer(getAD_Session_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Session.
		@param AD_Session_ID 
		User Session Online or Web
	  */
	public void setAD_Session_ID (int AD_Session_ID)
	{
		if (AD_Session_ID < 1)
			 throw new IllegalArgumentException ("AD_Session_ID is mandatory.");
		set_Value (COLUMNNAME_AD_Session_ID, Integer.valueOf(AD_Session_ID));
	}

	/** Get Session.
		@return User Session Online or Web
	  */
	public int getAD_Session_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_Session_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_AD_User getAD_User() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_AD_User.Table_Name);
        I_AD_User result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_AD_User)constructor.newInstance(new Object[] {getCtx(), new Integer(getAD_User_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set User/Contact .
		@param AD_User_ID 
		User within the system - Internal or Business Partner Contact
	  */
	public void setAD_User_ID (int AD_User_ID)
	{
		if (AD_User_ID < 1)
			 throw new IllegalArgumentException ("AD_User_ID is mandatory.");
		set_Value (COLUMNNAME_AD_User_ID, Integer.valueOf(AD_User_ID));
	}

	/** Get User/Contact .
		@return User within the system - Internal or Business Partner Contact
	  */
	public int getAD_User_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_User_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Account Receivable.
		@param AR Account Receivable	  */
	public void setAR (boolean AR)
	{
		set_Value (COLUMNNAME_AR, Boolean.valueOf(AR));
	}

	/** Get Account Receivable.
		@return Account Receivable	  */
	public boolean isAR () 
	{
		Object oo = get_Value(COLUMNNAME_AR);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** BillingType AD_Reference_ID=1200647 */
	public static final int BILLINGTYPE_AD_Reference_ID=1200647;
	/** SingleClaim = S */
	public static final String BILLINGTYPE_SingleClaim = "S";
	/** AdditionalClaim = A */
	public static final String BILLINGTYPE_AdditionalClaim = "A";
	/** Set Billing type.
		@param BillingType Billing type	  */
	public void setBillingType (String BillingType)
	{
		if (BillingType == null) throw new IllegalArgumentException ("BillingType is mandatory");
		if (BillingType.equals("S") || BillingType.equals("A")); else throw new IllegalArgumentException ("BillingType Invalid value - " + BillingType + " - Reference_ID=1200647 - S - A");		set_Value (COLUMNNAME_BillingType, BillingType);
	}

	/** Get Billing type.
		@return Billing type	  */
	public String getBillingType () 
	{
		return (String)get_Value(COLUMNNAME_BillingType);
	}

	/** Set Erase agreement.
		@param BorrarConv Erase agreement	  */
	public void setBorrarConv (boolean BorrarConv)
	{
		set_Value (COLUMNNAME_BorrarConv, Boolean.valueOf(BorrarConv));
	}

	/** Get Erase agreement.
		@return Erase agreement	  */
	public boolean isBorrarConv () 
	{
		Object oo = get_Value(COLUMNNAME_BorrarConv);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Calculate Price.
		@param CalcularPrecio Calculate Price	  */
	public void setCalcularPrecio (boolean CalcularPrecio)
	{
		set_Value (COLUMNNAME_CalcularPrecio, Boolean.valueOf(CalcularPrecio));
	}

	/** Get Calculate Price.
		@return Calculate Price	  */
	public boolean isCalcularPrecio () 
	{
		Object oo = get_Value(COLUMNNAME_CalcularPrecio);
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
			 throw new IllegalArgumentException ("C_Currency_ID is mandatory.");
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

	/** Condition AD_Reference_ID=1200640 */
	public static final int CONDITION_AD_Reference_ID=1200640;
	/** Not Used = NU */
	public static final String CONDITION_NotUsed = "NU";
	/** New Services Requested = ST */
	public static final String CONDITION_NewServicesRequested = "ST";
	/** Under Treatment = S2 */
	public static final String CONDITION_UnderTreatment = "S2";
	/** Set Condition Indicator.
		@param Condition Condition Indicator	  */
	public void setCondition (String Condition)
	{

		if (Condition == null || Condition.equals("NU") || Condition.equals("ST") || Condition.equals("S2")); else throw new IllegalArgumentException ("Condition Invalid value - " + Condition + " - Reference_ID=1200640 - NU - ST - S2");		set_Value (COLUMNNAME_Condition, Condition);
	}

	/** Get Condition Indicator.
		@return Condition Indicator	  */
	public String getCondition () 
	{
		return (String)get_Value(COLUMNNAME_Condition);
	}

	/** Set Agreement.
		@param Convenio_ID Agreement	  */
	public void setConvenio_ID (int Convenio_ID)
	{
		if (Convenio_ID < 1) 
			set_Value (COLUMNNAME_Convenio_ID, null);
		else 
			set_Value (COLUMNNAME_Convenio_ID, Integer.valueOf(Convenio_ID));
	}

	/** Get Agreement.
		@return Agreement	  */
	public int getConvenio_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Convenio_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Average Cost.
		@param CostAverage 
		Weighted average costs
	  */
	public void setCostAverage (BigDecimal CostAverage)
	{
		set_Value (COLUMNNAME_CostAverage, CostAverage);
	}

	/** Get Average Cost.
		@return Weighted average costs
	  */
	public BigDecimal getCostAverage () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_CostAverage);
		if (bd == null)
			 return Env.ZERO;
		return bd;
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

	/** Set Standard Cost.
		@param CostStandard 
		Standard Costs
	  */
	public void setCostStandard (BigDecimal CostStandard)
	{
		set_Value (COLUMNNAME_CostStandard, CostStandard);
	}

	/** Get Standard Cost.
		@return Standard Costs
	  */
	public BigDecimal getCostStandard () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_CostStandard);
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
			 throw new IllegalArgumentException ("C_Tax_ID is mandatory.");
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

	/** Set Public Tax.
		@param C_TaxPublico_ID Public Tax	  */
	public void setC_TaxPublico_ID (int C_TaxPublico_ID)
	{
		if (C_TaxPublico_ID < 1) 
			set_Value (COLUMNNAME_C_TaxPublico_ID, null);
		else 
			set_Value (COLUMNNAME_C_TaxPublico_ID, Integer.valueOf(C_TaxPublico_ID));
	}

	/** Get Public Tax.
		@return Public Tax	  */
	public int getC_TaxPublico_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_TaxPublico_ID);
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

	/** Set Detail tax.
		@param DetailTax Detail tax	  */
	public void setDetailTax (String DetailTax)
	{
		set_Value (COLUMNNAME_DetailTax, DetailTax);
	}

	/** Get Detail tax.
		@return Detail tax	  */
	public String getDetailTax () 
	{
		return (String)get_Value(COLUMNNAME_DetailTax);
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

	/** Set Discount Amount.
		@param DiscountAmt 
		Calculated amount of discount
	  */
	public void setDiscountAmt (BigDecimal DiscountAmt)
	{
		set_Value (COLUMNNAME_DiscountAmt, DiscountAmt);
	}

	/** Get Discount Amount.
		@return Calculated amount of discount
	  */
	public BigDecimal getDiscountAmt () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_DiscountAmt);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set DiscountFam.
		@param DiscountFam 
		DiscountFam
	  */
	public void setDiscountFam (BigDecimal DiscountFam)
	{
		set_Value (COLUMNNAME_DiscountFam, DiscountFam);
	}

	/** Get DiscountFam.
		@return DiscountFam
	  */
	public BigDecimal getDiscountFam () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_DiscountFam);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Discount tax amout.
		@param DiscountTaxAmt Discount tax amout	  */
	public void setDiscountTaxAmt (BigDecimal DiscountTaxAmt)
	{
		if (DiscountTaxAmt == null)
			throw new IllegalArgumentException ("DiscountTaxAmt is mandatory.");
		set_Value (COLUMNNAME_DiscountTaxAmt, DiscountTaxAmt);
	}

	/** Get Discount tax amout.
		@return Discount tax amout	  */
	public BigDecimal getDiscountTaxAmt () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_DiscountTaxAmt);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	public I_EXME_ActPacienteInd getEXME_ActPacienteInd() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_ActPacienteInd.Table_Name);
        I_EXME_ActPacienteInd result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_ActPacienteInd)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_ActPacienteInd_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Indication's detail.
		@param EXME_ActPacienteInd_ID 
		Indication's detail
	  */
	public void setEXME_ActPacienteInd_ID (int EXME_ActPacienteInd_ID)
	{
		if (EXME_ActPacienteInd_ID < 1) 
			set_Value (COLUMNNAME_EXME_ActPacienteInd_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_ActPacienteInd_ID, Integer.valueOf(EXME_ActPacienteInd_ID));
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

	public I_EXME_CancelCharges getEXME_CancelCharges() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_CancelCharges.Table_Name);
        I_EXME_CancelCharges result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_CancelCharges)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_CancelCharges_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Cancel Charges .
		@param EXME_CancelCharges_ID Cancel Charges 	  */
	public void setEXME_CancelCharges_ID (int EXME_CancelCharges_ID)
	{
		if (EXME_CancelCharges_ID < 1) 
			set_Value (COLUMNNAME_EXME_CancelCharges_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_CancelCharges_ID, Integer.valueOf(EXME_CancelCharges_ID));
	}

	/** Get Cancel Charges .
		@return Cancel Charges 	  */
	public int getEXME_CancelCharges_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_CancelCharges_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Detail Daily Charge.
		@param EXME_CDiarioDet_ID 
		Detail Daily Charge
	  */
	public void setEXME_CDiarioDet_ID (int EXME_CDiarioDet_ID)
	{
		if (EXME_CDiarioDet_ID < 1) 
			set_Value (COLUMNNAME_EXME_CDiarioDet_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_CDiarioDet_ID, Integer.valueOf(EXME_CDiarioDet_ID));
	}

	/** Get Detail Daily Charge.
		@return Detail Daily Charge
	  */
	public int getEXME_CDiarioDet_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_CDiarioDet_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_ConceptoFac getEXME_ConceptoFac() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_ConceptoFac.Table_Name);
        I_EXME_ConceptoFac result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_ConceptoFac)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_ConceptoFac_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Invoice Concept.
		@param EXME_ConceptoFac_ID Invoice Concept	  */
	public void setEXME_ConceptoFac_ID (int EXME_ConceptoFac_ID)
	{
		if (EXME_ConceptoFac_ID < 1) 
			set_Value (COLUMNNAME_EXME_ConceptoFac_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_ConceptoFac_ID, Integer.valueOf(EXME_ConceptoFac_ID));
	}

	/** Get Invoice Concept.
		@return Invoice Concept	  */
	public int getEXME_ConceptoFac_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_ConceptoFac_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Encounter Detail.
		@param EXME_CtaPacDet_ID 
		Encounter Detail
	  */
	public void setEXME_CtaPacDet_ID (int EXME_CtaPacDet_ID)
	{
		if (EXME_CtaPacDet_ID < 1)
			 throw new IllegalArgumentException ("EXME_CtaPacDet_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_CtaPacDet_ID, Integer.valueOf(EXME_CtaPacDet_ID));
	}

	/** Get Encounter Detail.
		@return Encounter Detail
	  */
	public int getEXME_CtaPacDet_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_CtaPacDet_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Encounter Extension.
		@param EXME_CtaPacExt_ID 
		Encounter Extension
	  */
	public void setEXME_CtaPacExt_ID (int EXME_CtaPacExt_ID)
	{
		if (EXME_CtaPacExt_ID < 1)
			 throw new IllegalArgumentException ("EXME_CtaPacExt_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_CtaPacExt_ID, Integer.valueOf(EXME_CtaPacExt_ID));
	}

	/** Get Encounter Extension.
		@return Encounter Extension
	  */
	public int getEXME_CtaPacExt_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_CtaPacExt_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Encounter.
		@param EXME_CtaPac_ID 
		Encounter
	  */
	public void setEXME_CtaPac_ID (int EXME_CtaPac_ID)
	{
		if (EXME_CtaPac_ID < 1)
			 throw new IllegalArgumentException ("EXME_CtaPac_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_CtaPac_ID, Integer.valueOf(EXME_CtaPac_ID));
	}

	/** Get Encounter.
		@return Encounter
	  */
	public int getEXME_CtaPac_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_CtaPac_ID);
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

	public I_EXME_PaqBase_Version getEXME_PaqBase_Version() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_PaqBase_Version.Table_Name);
        I_EXME_PaqBase_Version result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_PaqBase_Version)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_PaqBase_Version_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Package Version.
		@param EXME_PaqBase_Version_ID 
		Package Version
	  */
	public void setEXME_PaqBase_Version_ID (int EXME_PaqBase_Version_ID)
	{
		if (EXME_PaqBase_Version_ID < 1) 
			set_Value (COLUMNNAME_EXME_PaqBase_Version_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_PaqBase_Version_ID, Integer.valueOf(EXME_PaqBase_Version_ID));
	}

	/** Get Package Version.
		@return Package Version
	  */
	public int getEXME_PaqBase_Version_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_PaqBase_Version_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_PlanMedLine getEXME_PlanMedLine() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_PlanMedLine.Table_Name);
        I_EXME_PlanMedLine result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_PlanMedLine)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_PlanMedLine_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Scheduled Doctor.
		@param EXME_PlanMedLine_ID 
		Scheduled Doctor
	  */
	public void setEXME_PlanMedLine_ID (int EXME_PlanMedLine_ID)
	{
		if (EXME_PlanMedLine_ID < 1) 
			set_Value (COLUMNNAME_EXME_PlanMedLine_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_PlanMedLine_ID, Integer.valueOf(EXME_PlanMedLine_ID));
	}

	/** Get Scheduled Doctor.
		@return Scheduled Doctor
	  */
	public int getEXME_PlanMedLine_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_PlanMedLine_ID);
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

	public I_EXME_ProductFam getEXME_ProductFam() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_ProductFam.Table_Name);
        I_EXME_ProductFam result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_ProductFam)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_ProductFam_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Family Products.
		@param EXME_ProductFam_ID 
		Family Products
	  */
	public void setEXME_ProductFam_ID (int EXME_ProductFam_ID)
	{
		if (EXME_ProductFam_ID < 1) 
			set_Value (COLUMNNAME_EXME_ProductFam_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_ProductFam_ID, Integer.valueOf(EXME_ProductFam_ID));
	}

	/** Get Family Products.
		@return Family Products
	  */
	public int getEXME_ProductFam_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_ProductFam_ID);
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

	public I_EXME_Tratamientos_Sesion getEXME_Tratamientos_Sesion() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_Tratamientos_Sesion.Table_Name);
        I_EXME_Tratamientos_Sesion result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_Tratamientos_Sesion)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Tratamientos_Sesion_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Treatment sessions.
		@param EXME_Tratamientos_Sesion_ID Treatment sessions	  */
	public void setEXME_Tratamientos_Sesion_ID (int EXME_Tratamientos_Sesion_ID)
	{
		if (EXME_Tratamientos_Sesion_ID < 1) 
			set_Value (COLUMNNAME_EXME_Tratamientos_Sesion_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_Tratamientos_Sesion_ID, Integer.valueOf(EXME_Tratamientos_Sesion_ID));
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

	/** Set Invoice Measuring Unit.
		@param Invoice_UOM_ID 
		Invoice Measuring Unit
	  */
	public void setInvoice_UOM_ID (int Invoice_UOM_ID)
	{
		if (Invoice_UOM_ID < 1) 
			set_Value (COLUMNNAME_Invoice_UOM_ID, null);
		else 
			set_Value (COLUMNNAME_Invoice_UOM_ID, Integer.valueOf(Invoice_UOM_ID));
	}

	/** Get Invoice Measuring Unit.
		@return Invoice Measuring Unit
	  */
	public int getInvoice_UOM_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Invoice_UOM_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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

	/** Set Invoiced.
		@param IsFacturado 
		Invoiced
	  */
	public void setIsFacturado (boolean IsFacturado)
	{
		set_Value (COLUMNNAME_IsFacturado, Boolean.valueOf(IsFacturado));
	}

	/** Get Invoiced.
		@return Invoiced
	  */
	public boolean isFacturado () 
	{
		Object oo = get_Value(COLUMNNAME_IsFacturado);
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

	public I_M_InOut getM_InOut() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_M_InOut.Table_Name);
        I_M_InOut result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_M_InOut)constructor.newInstance(new Object[] {getCtx(), new Integer(getM_InOut_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Shipment/Receipt.
		@param M_InOut_ID 
		Material Shipment Document
	  */
	public void setM_InOut_ID (int M_InOut_ID)
	{
		if (M_InOut_ID < 1)
			 throw new IllegalArgumentException ("M_InOut_ID is mandatory.");
		set_Value (COLUMNNAME_M_InOut_ID, Integer.valueOf(M_InOut_ID));
	}

	/** Get Shipment/Receipt.
		@return Material Shipment Document
	  */
	public int getM_InOut_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_InOut_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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

	public I_M_Locator getM_Locator() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_M_Locator.Table_Name);
        I_M_Locator result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_M_Locator)constructor.newInstance(new Object[] {getCtx(), new Integer(getM_Locator_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Locator.
		@param M_Locator_ID 
		Warehouse Locator
	  */
	public void setM_Locator_ID (int M_Locator_ID)
	{
		if (M_Locator_ID < 1) 
			set_Value (COLUMNNAME_M_Locator_ID, null);
		else 
			set_Value (COLUMNNAME_M_Locator_ID, Integer.valueOf(M_Locator_ID));
	}

	/** Get Locator.
		@return Warehouse Locator
	  */
	public int getM_Locator_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_Locator_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_M_Movement getM_Movement() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_M_Movement.Table_Name);
        I_M_Movement result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_M_Movement)constructor.newInstance(new Object[] {getCtx(), new Integer(getM_Movement_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Inventory Move.
		@param M_Movement_ID 
		Movement of Inventory
	  */
	public void setM_Movement_ID (int M_Movement_ID)
	{
		if (M_Movement_ID < 1)
			 throw new IllegalArgumentException ("M_Movement_ID is mandatory.");
		set_Value (COLUMNNAME_M_Movement_ID, Integer.valueOf(M_Movement_ID));
	}

	/** Get Inventory Move.
		@return Movement of Inventory
	  */
	public int getM_Movement_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_Movement_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_M_MovementLine getM_MovementLine() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_M_MovementLine.Table_Name);
        I_M_MovementLine result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_M_MovementLine)constructor.newInstance(new Object[] {getCtx(), new Integer(getM_MovementLine_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Move Line.
		@param M_MovementLine_ID 
		Inventory Move document Line
	  */
	public void setM_MovementLine_ID (int M_MovementLine_ID)
	{
		if (M_MovementLine_ID < 1) 
			set_Value (COLUMNNAME_M_MovementLine_ID, null);
		else 
			set_Value (COLUMNNAME_M_MovementLine_ID, Integer.valueOf(M_MovementLine_ID));
	}

	/** Get Move Line.
		@return Inventory Move document Line
	  */
	public int getM_MovementLine_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_MovementLine_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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

	/** Set Requesting Warehouse.
		@param M_Warehouse_Sol_ID 
		Requesting Warehouse
	  */
	public void setM_Warehouse_Sol_ID (int M_Warehouse_Sol_ID)
	{
		if (M_Warehouse_Sol_ID < 1) 
			set_Value (COLUMNNAME_M_Warehouse_Sol_ID, null);
		else 
			set_Value (COLUMNNAME_M_Warehouse_Sol_ID, Integer.valueOf(M_Warehouse_Sol_ID));
	}

	/** Get Requesting Warehouse.
		@return Requesting Warehouse
	  */
	public int getM_Warehouse_Sol_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_Warehouse_Sol_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Line Number.
		@param NoLine Line Number	  */
	public void setNoLine (int NoLine)
	{
		set_Value (COLUMNNAME_NoLine, Integer.valueOf(NoLine));
	}

	/** Get Line Number.
		@return Line Number	  */
	public int getNoLine () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_NoLine);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Public Price.
		@param PrecioPublico Public Price	  */
	public void setPrecioPublico (BigDecimal PrecioPublico)
	{
		if (PrecioPublico == null)
			throw new IllegalArgumentException ("PrecioPublico is mandatory.");
		set_Value (COLUMNNAME_PrecioPublico, PrecioPublico);
	}

	/** Get Public Price.
		@return Public Price	  */
	public BigDecimal getPrecioPublico () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_PrecioPublico);
		if (bd == null)
			 return Env.ZERO;
		return bd;
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

	/** Set Invoiced Actual Price.
		@param PriceActualInv Invoiced Actual Price	  */
	public void setPriceActualInv (BigDecimal PriceActualInv)
	{
		if (PriceActualInv == null)
			throw new IllegalArgumentException ("PriceActualInv is mandatory.");
		set_Value (COLUMNNAME_PriceActualInv, PriceActualInv);
	}

	/** Get Invoiced Actual Price.
		@return Invoiced Actual Price	  */
	public BigDecimal getPriceActualInv () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_PriceActualInv);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Last PO Price.
		@param PriceLastPO 
		Price of the last purchase order for the product
	  */
	public void setPriceLastPO (BigDecimal PriceLastPO)
	{
		set_Value (COLUMNNAME_PriceLastPO, PriceLastPO);
	}

	/** Get Last PO Price.
		@return Price of the last purchase order for the product
	  */
	public BigDecimal getPriceLastPO () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_PriceLastPO);
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

	/** Set Invoiced Limit Price.
		@param PriceLimitInv Invoiced Limit Price	  */
	public void setPriceLimitInv (BigDecimal PriceLimitInv)
	{
		if (PriceLimitInv == null)
			throw new IllegalArgumentException ("PriceLimitInv is mandatory.");
		set_Value (COLUMNNAME_PriceLimitInv, PriceLimitInv);
	}

	/** Get Invoiced Limit Price.
		@return Invoiced Limit Price	  */
	public BigDecimal getPriceLimitInv () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_PriceLimitInv);
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

	/** Set Invoiced List Price.
		@param PriceListInv Invoiced List Price	  */
	public void setPriceListInv (BigDecimal PriceListInv)
	{
		if (PriceListInv == null)
			throw new IllegalArgumentException ("PriceListInv is mandatory.");
		set_Value (COLUMNNAME_PriceListInv, PriceListInv);
	}

	/** Get Invoiced List Price.
		@return Invoiced List Price	  */
	public BigDecimal getPriceListInv () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_PriceListInv);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Product Category.
		@param ProductCategory Product Category	  */
	public void setProductCategory (String ProductCategory)
	{
		set_Value (COLUMNNAME_ProductCategory, ProductCategory);
	}

	/** Get Product Category.
		@return Product Category	  */
	public String getProductCategory () 
	{
		return (String)get_Value(COLUMNNAME_ProductCategory);
	}

	/** Set Product Description.
		@param ProductDescription 
		Product Description
	  */
	public void setProductDescription (String ProductDescription)
	{
		set_Value (COLUMNNAME_ProductDescription, ProductDescription);
	}

	/** Get Product Description.
		@return Product Description
	  */
	public String getProductDescription () 
	{
		return (String)get_Value(COLUMNNAME_ProductDescription);
	}

	/** Set Product Key.
		@param ProductValue 
		Key of the Product
	  */
	public void setProductValue (String ProductValue)
	{
		set_Value (COLUMNNAME_ProductValue, ProductValue);
	}

	/** Get Product Key.
		@return Key of the Product
	  */
	public String getProductValue () 
	{
		return (String)get_Value(COLUMNNAME_ProductValue);
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

	/** Set Package Quantity.
		@param QtyPaquete 
		Package Quantity
	  */
	public void setQtyPaquete (BigDecimal QtyPaquete)
	{
		if (QtyPaquete == null)
			throw new IllegalArgumentException ("QtyPaquete is mandatory.");
		set_Value (COLUMNNAME_QtyPaquete, QtyPaquete);
	}

	/** Get Package Quantity.
		@return Package Quantity
	  */
	public BigDecimal getQtyPaquete () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_QtyPaquete);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Pending Quantity.
		@param QtyPendiente 
		Pending Quantity
	  */
	public void setQtyPendiente (BigDecimal QtyPendiente)
	{
		if (QtyPendiente == null)
			throw new IllegalArgumentException ("QtyPendiente is mandatory.");
		set_Value (COLUMNNAME_QtyPendiente, QtyPendiente);
	}

	/** Get Pending Quantity.
		@return Pending Quantity
	  */
	public BigDecimal getQtyPendiente () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_QtyPendiente);
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

	/** Set Reference to Encounter detail.
		@param Ref_CtaPacDet_ID 
		Reference to Encounter Detail
	  */
	public void setRef_CtaPacDet_ID (int Ref_CtaPacDet_ID)
	{
		if (Ref_CtaPacDet_ID < 1) 
			set_Value (COLUMNNAME_Ref_CtaPacDet_ID, null);
		else 
			set_Value (COLUMNNAME_Ref_CtaPacDet_ID, Integer.valueOf(Ref_CtaPacDet_ID));
	}

	/** Get Reference to Encounter detail.
		@return Reference to Encounter Detail
	  */
	public int getRef_CtaPacDet_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Ref_CtaPacDet_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Reference Encounter.
		@param Ref_CtaPac_ID 
		Reference Encounter
	  */
	public void setRef_CtaPac_ID (int Ref_CtaPac_ID)
	{
		if (Ref_CtaPac_ID < 1) 
			set_Value (COLUMNNAME_Ref_CtaPac_ID, null);
		else 
			set_Value (COLUMNNAME_Ref_CtaPac_ID, Integer.valueOf(Ref_CtaPac_ID));
	}

	/** Get Reference Encounter.
		@return Reference Encounter
	  */
	public int getRef_CtaPac_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Ref_CtaPac_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Ref_EXME_CtaPacDet_ID.
		@param Ref_EXME_CtaPacDet_ID Ref_EXME_CtaPacDet_ID	  */
	public void setRef_EXME_CtaPacDet_ID (int Ref_EXME_CtaPacDet_ID)
	{
		if (Ref_EXME_CtaPacDet_ID < 1) 
			set_Value (COLUMNNAME_Ref_EXME_CtaPacDet_ID, null);
		else 
			set_Value (COLUMNNAME_Ref_EXME_CtaPacDet_ID, Integer.valueOf(Ref_EXME_CtaPacDet_ID));
	}

	/** Get Ref_EXME_CtaPacDet_ID.
		@return Ref_EXME_CtaPacDet_ID	  */
	public int getRef_EXME_CtaPacDet_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Ref_EXME_CtaPacDet_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Sequence.
		@param Secuencia 
		Sequence
	  */
	public void setSecuencia (int Secuencia)
	{
		set_Value (COLUMNNAME_Secuencia, Integer.valueOf(Secuencia));
	}

	/** Get Sequence.
		@return Sequence
	  */
	public int getSecuencia () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Secuencia);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Was Returned.
		@param SeDevolvio Was Returned	  */
	public void setSeDevolvio (boolean SeDevolvio)
	{
		set_Value (COLUMNNAME_SeDevolvio, Boolean.valueOf(SeDevolvio));
	}

	/** Get Was Returned.
		@return Was Returned	  */
	public boolean isSeDevolvio () 
	{
		Object oo = get_Value(COLUMNNAME_SeDevolvio);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Serie.
		@param Serie Serie	  */
	public void setSerie (int Serie)
	{
		set_Value (COLUMNNAME_Serie, Integer.valueOf(Serie));
	}

	/** Get Serie.
		@return Serie	  */
	public int getSerie () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Serie);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Resource Assignment.
		@param S_ResourceAssignment_ID 
		Resource Assignment
	  */
	public void setS_ResourceAssignment_ID (int S_ResourceAssignment_ID)
	{
		if (S_ResourceAssignment_ID < 1) 
			set_Value (COLUMNNAME_S_ResourceAssignment_ID, null);
		else 
			set_Value (COLUMNNAME_S_ResourceAssignment_ID, Integer.valueOf(S_ResourceAssignment_ID));
	}

	/** Get Resource Assignment.
		@return Resource Assignment
	  */
	public int getS_ResourceAssignment_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_S_ResourceAssignment_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Tax Amount.
		@param TaxAmt 
		Tax Amount for a document
	  */
	public void setTaxAmt (BigDecimal TaxAmt)
	{
		if (TaxAmt == null)
			throw new IllegalArgumentException ("TaxAmt is mandatory.");
		set_Value (COLUMNNAME_TaxAmt, TaxAmt);
	}

	/** Get Tax Amount.
		@return Tax Amount for a document
	  */
	public BigDecimal getTaxAmt () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_TaxAmt);
		if (bd == null)
			 return Env.ZERO;
		return bd;
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

	/** TipoLinea AD_Reference_ID=1200090 */
	public static final int TIPOLINEA_AD_Reference_ID=1200090;
	/** Charge = CG */
	public static final String TIPOLINEA_Charge = "CG";
	/** Base Pack = PB */
	public static final String TIPOLINEA_BasePack = "PB";
	/** Mini pack = MP */
	public static final String TIPOLINEA_MiniPack = "MP";
	/** Coaseguro = CO */
	public static final String TIPOLINEA_Coaseguro = "CO";
	/** Deducible = DE */
	public static final String TIPOLINEA_Deducible = "DE";
	/** Discount = DS */
	public static final String TIPOLINEA_Discount = "DS";
	/** Adjustment = AJ */
	public static final String TIPOLINEA_Adjustment = "AJ";
	/** Agreement = LD */
	public static final String TIPOLINEA_Agreement = "LD";
	/** Exempt = EX */
	public static final String TIPOLINEA_Exempt = "EX";
	/** Copago = CP */
	public static final String TIPOLINEA_Copago = "CP";
	/** Coaseguro Medico = CM */
	public static final String TIPOLINEA_CoaseguroMedico = "CM";
	/** Set Line Type.
		@param TipoLinea Line Type	  */
	public void setTipoLinea (String TipoLinea)
	{
		if (TipoLinea == null) throw new IllegalArgumentException ("TipoLinea is mandatory");
		if (TipoLinea.equals("CG") || TipoLinea.equals("PB") || TipoLinea.equals("MP") || TipoLinea.equals("CO") || TipoLinea.equals("DE") || TipoLinea.equals("DS") || TipoLinea.equals("AJ") || TipoLinea.equals("LD") || TipoLinea.equals("EX") || TipoLinea.equals("CP") || TipoLinea.equals("CM")); else throw new IllegalArgumentException ("TipoLinea Invalid value - " + TipoLinea + " - Reference_ID=1200090 - CG - PB - MP - CO - DE - DS - AJ - LD - EX - CP - CM");		set_Value (COLUMNNAME_TipoLinea, TipoLinea);
	}

	/** Get Line Type.
		@return Line Type	  */
	public String getTipoLinea () 
	{
		return (String)get_Value(COLUMNNAME_TipoLinea);
	}

	/** Set Use Factor.
		@param UsarFactor 
		Use factor
	  */
	public void setUsarFactor (boolean UsarFactor)
	{
		set_Value (COLUMNNAME_UsarFactor, Boolean.valueOf(UsarFactor));
	}

	/** Get Use Factor.
		@return Use factor
	  */
	public boolean isUsarFactor () 
	{
		Object oo = get_Value(COLUMNNAME_UsarFactor);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Visible.
		@param Visible Visible	  */
	public void setVisible (boolean Visible)
	{
		set_Value (COLUMNNAME_Visible, Boolean.valueOf(Visible));
	}

	/** Get Visible.
		@return Visible	  */
	public boolean isVisible () 
	{
		Object oo = get_Value(COLUMNNAME_Visible);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}
}