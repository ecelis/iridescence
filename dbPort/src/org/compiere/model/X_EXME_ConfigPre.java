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

/** Generated Model for EXME_ConfigPre
 *  @author Generated Class 
 *  @version Version @VERSION@ - $Id$ */
public class X_EXME_ConfigPre extends PO implements I_EXME_ConfigPre, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_ConfigPre (Properties ctx, int EXME_ConfigPre_ID, String trxName)
    {
      super (ctx, EXME_ConfigPre_ID, trxName);
      /** if (EXME_ConfigPre_ID == 0)
        {
			setAplicaServSinPrec (true);
// Y
			setBajarPrecioVta (false);
// N
			setBusquedaPrecio (null);
			setCalculoAut (false);
			setC_BankAccount_ID (0);
			setC_BP_Group_ID (0);
			setCoaseguro_ID (0);
			setCreaActividad (false);
			setCreateInventory (false);
			setDeducible_ID (0);
			setEXME_ConfigPre_ID (0);
			setIncluirDesctos (false);
			setMaxDiscount (Env.ZERO);
			setMaxLinFactura (0);
// 45
			setM_PriceList_ID (0);
			setName (null);
			setPrintRecibo (false);
			setRecalculaPre (false);
// N
			setUsarFactor (false);
			setValue (null);
        } */
    }

    /** Load Constructor */
    public X_EXME_ConfigPre (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_ConfigPre[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Apply a clinical study without a price.
		@param AplicaServSinPrec Apply a clinical study without a price	  */
	public void setAplicaServSinPrec (boolean AplicaServSinPrec)
	{
		set_Value (COLUMNNAME_AplicaServSinPrec, Boolean.valueOf(AplicaServSinPrec));
	}

	/** Get Apply a clinical study without a price.
		@return Apply a clinical study without a price	  */
	public boolean isAplicaServSinPrec () 
	{
		Object oo = get_Value(COLUMNNAME_AplicaServSinPrec);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Lower Sale Price.
		@param BajarPrecioVta 
		Lower Sale Price
	  */
	public void setBajarPrecioVta (boolean BajarPrecioVta)
	{
		set_Value (COLUMNNAME_BajarPrecioVta, Boolean.valueOf(BajarPrecioVta));
	}

	/** Get Lower Sale Price.
		@return Lower Sale Price
	  */
	public boolean isBajarPrecioVta () 
	{
		Object oo = get_Value(COLUMNNAME_BajarPrecioVta);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** BusquedaPrecio AD_Reference_ID=1200603 */
	public static final int BUSQUEDAPRECIO_AD_Reference_ID=1200603;
	/** PP = PP */
	public static final String BUSQUEDAPRECIO_PP = "PP";
	/** LP = LP */
	public static final String BUSQUEDAPRECIO_LP = "LP";
	/** Set Search Price.
		@param BusquedaPrecio Search Price	  */
	public void setBusquedaPrecio (String BusquedaPrecio)
	{
		if (BusquedaPrecio == null) throw new IllegalArgumentException ("BusquedaPrecio is mandatory");
		if (BusquedaPrecio.equals("PP") || BusquedaPrecio.equals("LP")); else throw new IllegalArgumentException ("BusquedaPrecio Invalid value - " + BusquedaPrecio + " - Reference_ID=1200603 - PP - LP");		set_Value (COLUMNNAME_BusquedaPrecio, BusquedaPrecio);
	}

	/** Get Search Price.
		@return Search Price	  */
	public String getBusquedaPrecio () 
	{
		return (String)get_Value(COLUMNNAME_BusquedaPrecio);
	}

	/** Set Automatic Calculation.
		@param CalculoAut 
		Automatic Calculation
	  */
	public void setCalculoAut (boolean CalculoAut)
	{
		set_Value (COLUMNNAME_CalculoAut, Boolean.valueOf(CalculoAut));
	}

	/** Get Automatic Calculation.
		@return Automatic Calculation
	  */
	public boolean isCalculoAut () 
	{
		Object oo = get_Value(COLUMNNAME_CalculoAut);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	public I_C_BankAccount getC_BankAccount() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_C_BankAccount.Table_Name);
        I_C_BankAccount result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_C_BankAccount)constructor.newInstance(new Object[] {getCtx(), new Integer(getC_BankAccount_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Bank Account.
		@param C_BankAccount_ID 
		Account at the Bank
	  */
	public void setC_BankAccount_ID (int C_BankAccount_ID)
	{
		if (C_BankAccount_ID < 1)
			 throw new IllegalArgumentException ("C_BankAccount_ID is mandatory.");
		set_Value (COLUMNNAME_C_BankAccount_ID, Integer.valueOf(C_BankAccount_ID));
	}

	/** Get Bank Account.
		@return Account at the Bank
	  */
	public int getC_BankAccount_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_BankAccount_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Company.
		@param C_BPartner_ID 
		Identifier for a Company
	  */
	public void setC_BPartner_ID (int C_BPartner_ID)
	{
		if (C_BPartner_ID < 1) 
			set_Value (COLUMNNAME_C_BPartner_ID, null);
		else 
			set_Value (COLUMNNAME_C_BPartner_ID, Integer.valueOf(C_BPartner_ID));
	}

	/** Get Company.
		@return Identifier for a Company
	  */
	public int getC_BPartner_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_BPartner_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Partner sales.
		@param C_BPartner_Sales_ID Partner sales	  */
	public void setC_BPartner_Sales_ID (int C_BPartner_Sales_ID)
	{
		if (C_BPartner_Sales_ID < 1) 
			set_Value (COLUMNNAME_C_BPartner_Sales_ID, null);
		else 
			set_Value (COLUMNNAME_C_BPartner_Sales_ID, Integer.valueOf(C_BPartner_Sales_ID));
	}

	/** Get Partner sales.
		@return Partner sales	  */
	public int getC_BPartner_Sales_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_BPartner_Sales_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Company Group.
		@param C_BP_Group_ID 
		Company Group
	  */
	public void setC_BP_Group_ID (int C_BP_Group_ID)
	{
		if (C_BP_Group_ID < 1)
			 throw new IllegalArgumentException ("C_BP_Group_ID is mandatory.");
		set_Value (COLUMNNAME_C_BP_Group_ID, Integer.valueOf(C_BP_Group_ID));
	}

	/** Get Company Group.
		@return Company Group
	  */
	public int getC_BP_Group_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_BP_Group_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_C_Charge getC_Charge() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_C_Charge.Table_Name);
        I_C_Charge result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_C_Charge)constructor.newInstance(new Object[] {getCtx(), new Integer(getC_Charge_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
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

	/** Set Coinsurance.
		@param Coaseguro_ID 
		Product/Service corresponding to the coinsurance
	  */
	public void setCoaseguro_ID (int Coaseguro_ID)
	{
		if (Coaseguro_ID < 1)
			 throw new IllegalArgumentException ("Coaseguro_ID is mandatory.");
		set_Value (COLUMNNAME_Coaseguro_ID, Integer.valueOf(Coaseguro_ID));
	}

	/** Get Coinsurance.
		@return Product/Service corresponding to the coinsurance
	  */
	public int getCoaseguro_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Coaseguro_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Coinsurance Professional.
		@param CoaseguroMed_ID Coinsurance Professional	  */
	public void setCoaseguroMed_ID (int CoaseguroMed_ID)
	{
		if (CoaseguroMed_ID < 1) 
			set_Value (COLUMNNAME_CoaseguroMed_ID, null);
		else 
			set_Value (COLUMNNAME_CoaseguroMed_ID, Integer.valueOf(CoaseguroMed_ID));
	}

	/** Get Coinsurance Professional.
		@return Coinsurance Professional	  */
	public int getCoaseguroMed_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_CoaseguroMed_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Social Comm. BP Group.
		@param Com_SC_C_BP_Group_ID 
		Social Comm. BP Group
	  */
	public void setCom_SC_C_BP_Group_ID (int Com_SC_C_BP_Group_ID)
	{
		if (Com_SC_C_BP_Group_ID < 1) 
			set_Value (COLUMNNAME_Com_SC_C_BP_Group_ID, null);
		else 
			set_Value (COLUMNNAME_Com_SC_C_BP_Group_ID, Integer.valueOf(Com_SC_C_BP_Group_ID));
	}

	/** Get Social Comm. BP Group.
		@return Social Comm. BP Group
	  */
	public int getCom_SC_C_BP_Group_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Com_SC_C_BP_Group_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Outpatient Clinic BP Group.
		@param Cons_Ext_C_BP_Group_ID 
		Outpatient Clinic BP Group
	  */
	public void setCons_Ext_C_BP_Group_ID (int Cons_Ext_C_BP_Group_ID)
	{
		if (Cons_Ext_C_BP_Group_ID < 1) 
			set_Value (COLUMNNAME_Cons_Ext_C_BP_Group_ID, null);
		else 
			set_Value (COLUMNNAME_Cons_Ext_C_BP_Group_ID, Integer.valueOf(Cons_Ext_C_BP_Group_ID));
	}

	/** Get Outpatient Clinic BP Group.
		@return Outpatient Clinic BP Group
	  */
	public int getCons_Ext_C_BP_Group_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Cons_Ext_C_BP_Group_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Agreements BP Group.
		@param Convenios_C_BP_Group_ID 
		Agreements BP Group
	  */
	public void setConvenios_C_BP_Group_ID (int Convenios_C_BP_Group_ID)
	{
		if (Convenios_C_BP_Group_ID < 1) 
			set_Value (COLUMNNAME_Convenios_C_BP_Group_ID, null);
		else 
			set_Value (COLUMNNAME_Convenios_C_BP_Group_ID, Integer.valueOf(Convenios_C_BP_Group_ID));
	}

	/** Get Agreements BP Group.
		@return Agreements BP Group
	  */
	public int getConvenios_C_BP_Group_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Convenios_C_BP_Group_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Copayment.
		@param CoPago_ID Copayment	  */
	public void setCoPago_ID (int CoPago_ID)
	{
		if (CoPago_ID < 1) 
			set_Value (COLUMNNAME_CoPago_ID, null);
		else 
			set_Value (COLUMNNAME_CoPago_ID, Integer.valueOf(CoPago_ID));
	}

	/** Get Copayment.
		@return Copayment	  */
	public int getCoPago_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_CoPago_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Create Activity.
		@param CreaActividad Create Activity	  */
	public void setCreaActividad (boolean CreaActividad)
	{
		set_Value (COLUMNNAME_CreaActividad, Boolean.valueOf(CreaActividad));
	}

	/** Get Create Activity.
		@return Create Activity	  */
	public boolean isCreaActividad () 
	{
		Object oo = get_Value(COLUMNNAME_CreaActividad);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Allow negative stock.
		@param CreateInventory 
		Allow negative stock
	  */
	public void setCreateInventory (boolean CreateInventory)
	{
		set_Value (COLUMNNAME_CreateInventory, Boolean.valueOf(CreateInventory));
	}

	/** Get Allow negative stock.
		@return Allow negative stock
	  */
	public boolean isCreateInventory () 
	{
		Object oo = get_Value(COLUMNNAME_CreateInventory);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Deductible.
		@param Deducible_ID 
		Product/Service corresponding to the deductible
	  */
	public void setDeducible_ID (int Deducible_ID)
	{
		if (Deducible_ID < 1)
			 throw new IllegalArgumentException ("Deducible_ID is mandatory.");
		set_Value (COLUMNNAME_Deducible_ID, Integer.valueOf(Deducible_ID));
	}

	/** Get Deductible.
		@return Product/Service corresponding to the deductible
	  */
	public int getDeducible_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Deducible_ID);
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

	/** Set Price Configuration.
		@param EXME_ConfigPre_ID 
		Price Configuration
	  */
	public void setEXME_ConfigPre_ID (int EXME_ConfigPre_ID)
	{
		if (EXME_ConfigPre_ID < 1)
			 throw new IllegalArgumentException ("EXME_ConfigPre_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_ConfigPre_ID, Integer.valueOf(EXME_ConfigPre_ID));
	}

	/** Get Price Configuration.
		@return Price Configuration
	  */
	public int getEXME_ConfigPre_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_ConfigPre_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Professional Modifier.
		@param EXME_ModifiersProf_ID Professional Modifier	  */
	public void setEXME_ModifiersProf_ID (int EXME_ModifiersProf_ID)
	{
		if (EXME_ModifiersProf_ID < 1) 
			set_Value (COLUMNNAME_EXME_ModifiersProf_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_ModifiersProf_ID, Integer.valueOf(EXME_ModifiersProf_ID));
	}

	/** Get Professional Modifier.
		@return Professional Modifier	  */
	public int getEXME_ModifiersProf_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_ModifiersProf_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Inpatient BP Group.
		@param Hospitalizacion_C_BP_Group_ID 
		Inpatient BP Group
	  */
	public void setHospitalizacion_C_BP_Group_ID (int Hospitalizacion_C_BP_Group_ID)
	{
		if (Hospitalizacion_C_BP_Group_ID < 1) 
			set_Value (COLUMNNAME_Hospitalizacion_C_BP_Group_ID, null);
		else 
			set_Value (COLUMNNAME_Hospitalizacion_C_BP_Group_ID, Integer.valueOf(Hospitalizacion_C_BP_Group_ID));
	}

	/** Get Inpatient BP Group.
		@return Inpatient BP Group
	  */
	public int getHospitalizacion_C_BP_Group_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Hospitalizacion_C_BP_Group_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Include Discounts.
		@param IncluirDesctos 
		Include Discounts
	  */
	public void setIncluirDesctos (boolean IncluirDesctos)
	{
		set_Value (COLUMNNAME_IncluirDesctos, Boolean.valueOf(IncluirDesctos));
	}

	/** Get Include Discounts.
		@return Include Discounts
	  */
	public boolean isIncluirDesctos () 
	{
		Object oo = get_Value(COLUMNNAME_IncluirDesctos);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Max. discount %.
		@param MaxDiscount 
		Maximum discount percentage
	  */
	public void setMaxDiscount (BigDecimal MaxDiscount)
	{
		if (MaxDiscount == null)
			throw new IllegalArgumentException ("MaxDiscount is mandatory.");
		set_Value (COLUMNNAME_MaxDiscount, MaxDiscount);
	}

	/** Get Max. discount %.
		@return Maximum discount percentage
	  */
	public BigDecimal getMaxDiscount () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_MaxDiscount);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Max. discount amount.
		@param MaxDiscountAmt 
		Maximum discount amount
	  */
	public void setMaxDiscountAmt (BigDecimal MaxDiscountAmt)
	{
		set_Value (COLUMNNAME_MaxDiscountAmt, MaxDiscountAmt);
	}

	/** Get Max. discount amount.
		@return Maximum discount amount
	  */
	public BigDecimal getMaxDiscountAmt () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_MaxDiscountAmt);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Línes in Invoice.
		@param MaxLinFactura 
		Maximum lines in invoice
	  */
	public void setMaxLinFactura (int MaxLinFactura)
	{
		set_Value (COLUMNNAME_MaxLinFactura, Integer.valueOf(MaxLinFactura));
	}

	/** Get Línes in Invoice.
		@return Maximum lines in invoice
	  */
	public int getMaxLinFactura () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_MaxLinFactura);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Medicaid BP Group.
		@param Medicaid_C_BP_Group_ID Medicaid BP Group	  */
	public void setMedicaid_C_BP_Group_ID (int Medicaid_C_BP_Group_ID)
	{
		if (Medicaid_C_BP_Group_ID < 1) 
			set_Value (COLUMNNAME_Medicaid_C_BP_Group_ID, null);
		else 
			set_Value (COLUMNNAME_Medicaid_C_BP_Group_ID, Integer.valueOf(Medicaid_C_BP_Group_ID));
	}

	/** Get Medicaid BP Group.
		@return Medicaid BP Group	  */
	public int getMedicaid_C_BP_Group_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Medicaid_C_BP_Group_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Price List.
		@param M_PriceList_ID 
		Unique identifier of a Price List
	  */
	public void setM_PriceList_ID (int M_PriceList_ID)
	{
		if (M_PriceList_ID < 1)
			 throw new IllegalArgumentException ("M_PriceList_ID is mandatory.");
		set_Value (COLUMNNAME_M_PriceList_ID, Integer.valueOf(M_PriceList_ID));
	}

	/** Get Price List.
		@return Unique identifier of a Price List
	  */
	public int getM_PriceList_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_PriceList_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Name.
		@param Name 
		Alphanumeric identifier of the entity
	  */
	public void setName (String Name)
	{
		if (Name == null)
			throw new IllegalArgumentException ("Name is mandatory.");
		set_Value (COLUMNNAME_Name, Name);
	}

	/** Get Name.
		@return Alphanumeric identifier of the entity
	  */
	public String getName () 
	{
		return (String)get_Value(COLUMNNAME_Name);
	}

	/** Set Price Variation %.
		@param PriceVariation Price Variation %	  */
	public void setPriceVariation (BigDecimal PriceVariation)
	{
		set_Value (COLUMNNAME_PriceVariation, PriceVariation);
	}

	/** Get Price Variation %.
		@return Price Variation %	  */
	public BigDecimal getPriceVariation () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_PriceVariation);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Print receipt.
		@param PrintRecibo Print receipt	  */
	public void setPrintRecibo (boolean PrintRecibo)
	{
		set_Value (COLUMNNAME_PrintRecibo, Boolean.valueOf(PrintRecibo));
	}

	/** Get Print receipt.
		@return Print receipt	  */
	public boolean isPrintRecibo () 
	{
		Object oo = get_Value(COLUMNNAME_PrintRecibo);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Recalculate Prices.
		@param RecalculaPre Recalculate Prices	  */
	public void setRecalculaPre (boolean RecalculaPre)
	{
		set_Value (COLUMNNAME_RecalculaPre, Boolean.valueOf(RecalculaPre));
	}

	/** Get Recalculate Prices.
		@return Recalculate Prices	  */
	public boolean isRecalculaPre () 
	{
		Object oo = get_Value(COLUMNNAME_RecalculaPre);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Social Work BP Group.
		@param Tab_SC_C_BP_Group_ID 
		Social Work BP Group
	  */
	public void setTab_SC_C_BP_Group_ID (int Tab_SC_C_BP_Group_ID)
	{
		if (Tab_SC_C_BP_Group_ID < 1) 
			set_Value (COLUMNNAME_Tab_SC_C_BP_Group_ID, null);
		else 
			set_Value (COLUMNNAME_Tab_SC_C_BP_Group_ID, Integer.valueOf(Tab_SC_C_BP_Group_ID));
	}

	/** Get Social Work BP Group.
		@return Social Work BP Group
	  */
	public int getTab_SC_C_BP_Group_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Tab_SC_C_BP_Group_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Urgencies BP Group.
		@param Urgencias_C_BP_Group_ID 
		Urgencies BP Group
	  */
	public void setUrgencias_C_BP_Group_ID (int Urgencias_C_BP_Group_ID)
	{
		if (Urgencias_C_BP_Group_ID < 1) 
			set_Value (COLUMNNAME_Urgencias_C_BP_Group_ID, null);
		else 
			set_Value (COLUMNNAME_Urgencias_C_BP_Group_ID, Integer.valueOf(Urgencias_C_BP_Group_ID));
	}

	/** Get Urgencies BP Group.
		@return Urgencies BP Group
	  */
	public int getUrgencias_C_BP_Group_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Urgencias_C_BP_Group_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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