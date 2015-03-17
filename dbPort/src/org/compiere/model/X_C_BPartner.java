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
import org.compiere.util.KeyNamePair;

/** Generated Model for C_BPartner
 *  @author Generated Class 
 *  @version Version @VERSION@ - $Id$ */
public class X_C_BPartner extends PO implements I_C_BPartner, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_C_BPartner (Properties ctx, int C_BPartner_ID, String trxName)
    {
      super (ctx, C_BPartner_ID, trxName);
      /** if (C_BPartner_ID == 0)
        {
			setC_BPartner_ID (0);
			setC_BP_Group_ID (0);
			setFacturaEmail (false);
			setFacturarAseg (true);
// Y
			setImprimeFactura (false);
			setIsCreditor (false);
// N
			setIsCustomer (true);
// Y
			setIsEmployee (false);
// N
			setisExento (false);
// N
			setIsOneTime (false);
			setIsOrderFacLineCategory (false);
			setIsPatient (false);
// N
			setIsPension (false);
			setIsPOTaxExempt (false);
// N
			setIsProspect (false);
			setIsSalesRep (false);
			setIsSummary (false);
// N
			setIsVendor (false);
// N
			setModificaEnFactura (false);
// N
			setName (null);
			setSendEMail (false);
			setSO_CreditLimit (Env.ZERO);
			setSO_CreditUsed (Env.ZERO);
			setSupportEclaim (false);
			setSupportElegibilityVerification (false);
			setTipoProveedor (null);
// 04
			setValue (null);
        } */
    }

    /** Load Constructor */
    public X_C_BPartner (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 6 - System - Client 
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
      StringBuffer sb = new StringBuffer ("X_C_BPartner[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Acquisition Cost.
		@param AcqusitionCost 
		The cost of gaining the prospect as a customer
	  */
	public void setAcqusitionCost (BigDecimal AcqusitionCost)
	{
		set_Value (COLUMNNAME_AcqusitionCost, AcqusitionCost);
	}

	/** Get Acquisition Cost.
		@return The cost of gaining the prospect as a customer
	  */
	public BigDecimal getAcqusitionCost () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_AcqusitionCost);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Actual Life Time Value.
		@param ActualLifeTimeValue 
		Actual Life Time Revenue
	  */
	public void setActualLifeTimeValue (BigDecimal ActualLifeTimeValue)
	{
		set_Value (COLUMNNAME_ActualLifeTimeValue, ActualLifeTimeValue);
	}

	/** Get Actual Life Time Value.
		@return Actual Life Time Revenue
	  */
	public BigDecimal getActualLifeTimeValue () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_ActualLifeTimeValue);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** AD_Language AD_Reference_ID=327 */
	public static final int AD_LANGUAGE_AD_Reference_ID=327;
	/** Set Language.
		@param AD_Language 
		Language for this entity
	  */
	public void setAD_Language (String AD_Language)
	{
		set_Value (COLUMNNAME_AD_Language, AD_Language);
	}

	/** Get Language.
		@return Language for this entity
	  */
	public String getAD_Language () 
	{
		return (String)get_Value(COLUMNNAME_AD_Language);
	}

	/** Set Linked Organization.
		@param AD_OrgBP_ID 
		The Business Partner is another Organization for explicit Inter-Org transactions
	  */
	public void setAD_OrgBP_ID (String AD_OrgBP_ID)
	{
		set_Value (COLUMNNAME_AD_OrgBP_ID, AD_OrgBP_ID);
	}

	/** Get Linked Organization.
		@return The Business Partner is another Organization for explicit Inter-Org transactions
	  */
	public String getAD_OrgBP_ID () 
	{
		return (String)get_Value(COLUMNNAME_AD_OrgBP_ID);
	}

	/** Set Company Parent.
		@param BPartner_Parent_ID 
		Company Parent
	  */
	public void setBPartner_Parent_ID (int BPartner_Parent_ID)
	{
		if (BPartner_Parent_ID < 1) 
			set_Value (COLUMNNAME_BPartner_Parent_ID, null);
		else 
			set_Value (COLUMNNAME_BPartner_Parent_ID, Integer.valueOf(BPartner_Parent_ID));
	}

	/** Get Company Parent.
		@return Company Parent
	  */
	public int getBPartner_Parent_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_BPartner_Parent_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** BP_Class AD_Reference_ID=1200556 */
	public static final int BP_CLASS_AD_Reference_ID=1200556;
	/** I = I */
	public static final String BP_CLASS_I = "I";
	/** P = P */
	public static final String BP_CLASS_P = "P";
	/** C = C */
	public static final String BP_CLASS_C = "C";
	/** O = O */
	public static final String BP_CLASS_O = "O";
	/** D = D */
	public static final String BP_CLASS_D = "D";
	/** Set B. Partner Class.
		@param BP_Class B. Partner Class	  */
	public void setBP_Class (String BP_Class)
	{

		if (BP_Class == null || BP_Class.equals("I") || BP_Class.equals("P") || BP_Class.equals("C") || BP_Class.equals("O") || BP_Class.equals("D")); else throw new IllegalArgumentException ("BP_Class Invalid value - " + BP_Class + " - Reference_ID=1200556 - I - P - C - O - D");		set_Value (COLUMNNAME_BP_Class, BP_Class);
	}

	/** Get B. Partner Class.
		@return B. Partner Class	  */
	public String getBP_Class () 
	{
		return (String)get_Value(COLUMNNAME_BP_Class);
	}

	/** Set Company.
		@param C_BPartner_ID 
		Identifier for a Company
	  */
	public void setC_BPartner_ID (int C_BPartner_ID)
	{
		if (C_BPartner_ID < 1)
			 throw new IllegalArgumentException ("C_BPartner_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_C_BPartner_ID, Integer.valueOf(C_BPartner_ID));
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

	public I_C_BP_Group getC_BP_Group() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_C_BP_Group.Table_Name);
        I_C_BP_Group result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_C_BP_Group)constructor.newInstance(new Object[] {getCtx(), new Integer(getC_BP_Group_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
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

	public I_C_Dunning getC_Dunning() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_C_Dunning.Table_Name);
        I_C_Dunning result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_C_Dunning)constructor.newInstance(new Object[] {getCtx(), new Integer(getC_Dunning_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Dunning.
		@param C_Dunning_ID 
		Dunning Rules for overdue invoices
	  */
	public void setC_Dunning_ID (int C_Dunning_ID)
	{
		if (C_Dunning_ID < 1) 
			set_Value (COLUMNNAME_C_Dunning_ID, null);
		else 
			set_Value (COLUMNNAME_C_Dunning_ID, Integer.valueOf(C_Dunning_ID));
	}

	/** Get Dunning.
		@return Dunning Rules for overdue invoices
	  */
	public int getC_Dunning_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Dunning_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_C_Greeting getC_Greeting() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_C_Greeting.Table_Name);
        I_C_Greeting result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_C_Greeting)constructor.newInstance(new Object[] {getCtx(), new Integer(getC_Greeting_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Greeting.
		@param C_Greeting_ID 
		Greeting to print on correspondence
	  */
	public void setC_Greeting_ID (int C_Greeting_ID)
	{
		if (C_Greeting_ID < 1) 
			set_Value (COLUMNNAME_C_Greeting_ID, null);
		else 
			set_Value (COLUMNNAME_C_Greeting_ID, Integer.valueOf(C_Greeting_ID));
	}

	/** Get Greeting.
		@return Greeting to print on correspondence
	  */
	public int getC_Greeting_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Greeting_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_C_InvoiceSchedule getC_InvoiceSchedule() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_C_InvoiceSchedule.Table_Name);
        I_C_InvoiceSchedule result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_C_InvoiceSchedule)constructor.newInstance(new Object[] {getCtx(), new Integer(getC_InvoiceSchedule_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Invoice Schedule.
		@param C_InvoiceSchedule_ID 
		Schedule for generating Invoices
	  */
	public void setC_InvoiceSchedule_ID (int C_InvoiceSchedule_ID)
	{
		if (C_InvoiceSchedule_ID < 1) 
			set_Value (COLUMNNAME_C_InvoiceSchedule_ID, null);
		else 
			set_Value (COLUMNNAME_C_InvoiceSchedule_ID, Integer.valueOf(C_InvoiceSchedule_ID));
	}

	/** Get Invoice Schedule.
		@return Schedule for generating Invoices
	  */
	public int getC_InvoiceSchedule_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_InvoiceSchedule_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Claim Reception.
		@param Claim_ID_Recp 
		Claim Reception
	  */
	public void setClaim_ID_Recp (String Claim_ID_Recp)
	{
		set_Value (COLUMNNAME_Claim_ID_Recp, Claim_ID_Recp);
	}

	/** Get Claim Reception.
		@return Claim Reception
	  */
	public String getClaim_ID_Recp () 
	{
		return (String)get_Value(COLUMNNAME_Claim_ID_Recp);
	}

	/** Set Claim Send.
		@param Claim_ID_Send Claim Send	  */
	public void setClaim_ID_Send (String Claim_ID_Send)
	{
		set_Value (COLUMNNAME_Claim_ID_Send, Claim_ID_Send);
	}

	/** Get Claim Send.
		@return Claim Send	  */
	public String getClaim_ID_Send () 
	{
		return (String)get_Value(COLUMNNAME_Claim_ID_Send);
	}

	/** Set Copies.
		@param Copias Copies	  */
	public void setCopias (BigDecimal Copias)
	{
		set_Value (COLUMNNAME_Copias, Copias);
	}

	/** Get Copies.
		@return Copies	  */
	public BigDecimal getCopias () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Copias);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	public I_C_PaymentTerm getC_PaymentTerm() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_C_PaymentTerm.Table_Name);
        I_C_PaymentTerm result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_C_PaymentTerm)constructor.newInstance(new Object[] {getCtx(), new Integer(getC_PaymentTerm_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Payment Term.
		@param C_PaymentTerm_ID 
		The terms of Payment (timing, discount)
	  */
	public void setC_PaymentTerm_ID (int C_PaymentTerm_ID)
	{
		if (C_PaymentTerm_ID < 1) 
			set_Value (COLUMNNAME_C_PaymentTerm_ID, null);
		else 
			set_Value (COLUMNNAME_C_PaymentTerm_ID, Integer.valueOf(C_PaymentTerm_ID));
	}

	/** Get Payment Term.
		@return The terms of Payment (timing, discount)
	  */
	public int getC_PaymentTerm_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_PaymentTerm_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_C_TaxGroup getC_TaxGroup() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_C_TaxGroup.Table_Name);
        I_C_TaxGroup result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_C_TaxGroup)constructor.newInstance(new Object[] {getCtx(), new Integer(getC_TaxGroup_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Tax Group.
		@param C_TaxGroup_ID Tax Group	  */
	public void setC_TaxGroup_ID (int C_TaxGroup_ID)
	{
		if (C_TaxGroup_ID < 1) 
			set_Value (COLUMNNAME_C_TaxGroup_ID, null);
		else 
			set_Value (COLUMNNAME_C_TaxGroup_ID, Integer.valueOf(C_TaxGroup_ID));
	}

	/** Get Tax Group.
		@return Tax Group	  */
	public int getC_TaxGroup_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_TaxGroup_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** DeliveryRule AD_Reference_ID=1200082 */
	public static final int DELIVERYRULE_AD_Reference_ID=1200082;
	/** Ambos = A */
	public static final String DELIVERYRULE_Ambos = "A";
	/** Interno = I */
	public static final String DELIVERYRULE_Interno = "I";
	/** Externo = E */
	public static final String DELIVERYRULE_Externo = "E";
	/** Set Delivery Rule.
		@param DeliveryRule 
		Defines the timing of Delivery
	  */
	public void setDeliveryRule (String DeliveryRule)
	{

		if (DeliveryRule == null || DeliveryRule.equals("A") || DeliveryRule.equals("I") || DeliveryRule.equals("E")); else throw new IllegalArgumentException ("DeliveryRule Invalid value - " + DeliveryRule + " - Reference_ID=1200082 - A - I - E");		set_Value (COLUMNNAME_DeliveryRule, DeliveryRule);
	}

	/** Get Delivery Rule.
		@return Defines the timing of Delivery
	  */
	public String getDeliveryRule () 
	{
		return (String)get_Value(COLUMNNAME_DeliveryRule);
	}

	/** DeliveryViaRule AD_Reference_ID=152 */
	public static final int DELIVERYVIARULE_AD_Reference_ID=152;
	/** Pickup = P */
	public static final String DELIVERYVIARULE_Pickup = "P";
	/** Delivery = D */
	public static final String DELIVERYVIARULE_Delivery = "D";
	/** Shipper = S */
	public static final String DELIVERYVIARULE_Shipper = "S";
	/** Set Delivery Via.
		@param DeliveryViaRule 
		How the order will be delivered
	  */
	public void setDeliveryViaRule (String DeliveryViaRule)
	{

		if (DeliveryViaRule == null || DeliveryViaRule.equals("P") || DeliveryViaRule.equals("D") || DeliveryViaRule.equals("S")); else throw new IllegalArgumentException ("DeliveryViaRule Invalid value - " + DeliveryViaRule + " - Reference_ID=152 - P - D - S");		set_Value (COLUMNNAME_DeliveryViaRule, DeliveryViaRule);
	}

	/** Get Delivery Via.
		@return How the order will be delivered
	  */
	public String getDeliveryViaRule () 
	{
		return (String)get_Value(COLUMNNAME_DeliveryViaRule);
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

	/** Set Document Copies.
		@param DocumentCopies 
		Number of copies to be printed
	  */
	public void setDocumentCopies (int DocumentCopies)
	{
		set_Value (COLUMNNAME_DocumentCopies, Integer.valueOf(DocumentCopies));
	}

	/** Get Document Copies.
		@return Number of copies to be printed
	  */
	public int getDocumentCopies () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DocumentCopies);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Dunning Grace.
		@param DunningGrace Dunning Grace	  */
	public void setDunningGrace (Timestamp DunningGrace)
	{
		set_Value (COLUMNNAME_DunningGrace, DunningGrace);
	}

	/** Get Dunning Grace.
		@return Dunning Grace	  */
	public Timestamp getDunningGrace () 
	{
		return (Timestamp)get_Value(COLUMNNAME_DunningGrace);
	}

	/** Set D-U-N-S.
		@param DUNS 
		Dun & Bradstreet Number
	  */
	public void setDUNS (String DUNS)
	{
		set_Value (COLUMNNAME_DUNS, DUNS);
	}

	/** Get D-U-N-S.
		@return Dun & Bradstreet Number
	  */
	public String getDUNS () 
	{
		return (String)get_Value(COLUMNNAME_DUNS);
	}

	/** Set Eligibility Reception.
		@param Elig_ID_Recp Eligibility Reception	  */
	public void setElig_ID_Recp (String Elig_ID_Recp)
	{
		set_Value (COLUMNNAME_Elig_ID_Recp, Elig_ID_Recp);
	}

	/** Get Eligibility Reception.
		@return Eligibility Reception	  */
	public String getElig_ID_Recp () 
	{
		return (String)get_Value(COLUMNNAME_Elig_ID_Recp);
	}

	/** Set Payer ID.
		@param Elig_ID_Send Payer ID	  */
	public void setElig_ID_Send (String Elig_ID_Send)
	{
		set_Value (COLUMNNAME_Elig_ID_Send, Elig_ID_Send);
	}

	/** Get Payer ID.
		@return Payer ID	  */
	public String getElig_ID_Send () 
	{
		return (String)get_Value(COLUMNNAME_Elig_ID_Send);
	}

	/** Set EMail Address.
		@param EMail 
		Electronic Mail Address
	  */
	public void setEMail (String EMail)
	{
		set_Value (COLUMNNAME_EMail, EMail);
	}

	/** Get EMail Address.
		@return Electronic Mail Address
	  */
	public String getEMail () 
	{
		return (String)get_Value(COLUMNNAME_EMail);
	}

	/** Set Send Email.
		@param EnviaEMail 
		Send Email
	  */
	public void setEnviaEMail (String EnviaEMail)
	{
		set_Value (COLUMNNAME_EnviaEMail, EnviaEMail);
	}

	/** Get Send Email.
		@return Send Email
	  */
	public String getEnviaEMail () 
	{
		return (String)get_Value(COLUMNNAME_EnviaEMail);
	}

	public I_EXME_FinancialClass getEXME_FinancialClass() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_FinancialClass.Table_Name);
        I_EXME_FinancialClass result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_FinancialClass)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_FinancialClass_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Financial Class.
		@param EXME_FinancialClass_ID Financial Class	  */
	public void setEXME_FinancialClass_ID (int EXME_FinancialClass_ID)
	{
		if (EXME_FinancialClass_ID < 1) 
			set_Value (COLUMNNAME_EXME_FinancialClass_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_FinancialClass_ID, Integer.valueOf(EXME_FinancialClass_ID));
	}

	/** Get Financial Class.
		@return Financial Class	  */
	public int getEXME_FinancialClass_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_FinancialClass_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_PayerClass getEXME_PayerClass() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_PayerClass.Table_Name);
        I_EXME_PayerClass result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_PayerClass)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_PayerClass_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Payer Class.
		@param EXME_PayerClass_ID Payer Class	  */
	public void setEXME_PayerClass_ID (int EXME_PayerClass_ID)
	{
		if (EXME_PayerClass_ID < 1) 
			set_Value (COLUMNNAME_EXME_PayerClass_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_PayerClass_ID, Integer.valueOf(EXME_PayerClass_ID));
	}

	/** Get Payer Class.
		@return Payer Class	  */
	public int getEXME_PayerClass_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_PayerClass_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Email Bill.
		@param FacturaEmail Email Bill	  */
	public void setFacturaEmail (boolean FacturaEmail)
	{
		set_Value (COLUMNNAME_FacturaEmail, Boolean.valueOf(FacturaEmail));
	}

	/** Get Email Bill.
		@return Email Bill	  */
	public boolean isFacturaEmail () 
	{
		Object oo = get_Value(COLUMNNAME_FacturaEmail);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Invoice to Insurance Company.
		@param FacturarAseg 
		Invoice to Insurance Company
	  */
	public void setFacturarAseg (boolean FacturarAseg)
	{
		set_Value (COLUMNNAME_FacturarAseg, Boolean.valueOf(FacturarAseg));
	}

	/** Get Invoice to Insurance Company.
		@return Invoice to Insurance Company
	  */
	public boolean isFacturarAseg () 
	{
		Object oo = get_Value(COLUMNNAME_FacturarAseg);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set First Sale.
		@param FirstSale 
		Date of First Sale
	  */
	public void setFirstSale (Timestamp FirstSale)
	{
		set_Value (COLUMNNAME_FirstSale, FirstSale);
	}

	/** Get First Sale.
		@return Date of First Sale
	  */
	public Timestamp getFirstSale () 
	{
		return (Timestamp)get_Value(COLUMNNAME_FirstSale);
	}

	/** Set Flat Discount %.
		@param FlatDiscount 
		Flat discount percentage 
	  */
	public void setFlatDiscount (BigDecimal FlatDiscount)
	{
		set_Value (COLUMNNAME_FlatDiscount, FlatDiscount);
	}

	/** Get Flat Discount %.
		@return Flat discount percentage 
	  */
	public BigDecimal getFlatDiscount () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_FlatDiscount);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** FreightCostRule AD_Reference_ID=153 */
	public static final int FREIGHTCOSTRULE_AD_Reference_ID=153;
	/** Freight included = I */
	public static final String FREIGHTCOSTRULE_FreightIncluded = "I";
	/** Fix price = F */
	public static final String FREIGHTCOSTRULE_FixPrice = "F";
	/** Calculated = C */
	public static final String FREIGHTCOSTRULE_Calculated = "C";
	/** Line = L */
	public static final String FREIGHTCOSTRULE_Line = "L";
	/** Set Freight Cost Rule.
		@param FreightCostRule 
		Method for charging Freight
	  */
	public void setFreightCostRule (String FreightCostRule)
	{

		if (FreightCostRule == null || FreightCostRule.equals("I") || FreightCostRule.equals("F") || FreightCostRule.equals("C") || FreightCostRule.equals("L")); else throw new IllegalArgumentException ("FreightCostRule Invalid value - " + FreightCostRule + " - Reference_ID=153 - I - F - C - L");		set_Value (COLUMNNAME_FreightCostRule, FreightCostRule);
	}

	/** Get Freight Cost Rule.
		@return Method for charging Freight
	  */
	public String getFreightCostRule () 
	{
		return (String)get_Value(COLUMNNAME_FreightCostRule);
	}

	/** Identificador AD_Reference_ID=1200082 */
	public static final int IDENTIFICADOR_AD_Reference_ID=1200082;
	/** Ambos = A */
	public static final String IDENTIFICADOR_Ambos = "A";
	/** Interno = I */
	public static final String IDENTIFICADOR_Interno = "I";
	/** Externo = E */
	public static final String IDENTIFICADOR_Externo = "E";
	/** Set Identifier.
		@param Identificador Identifier	  */
	public void setIdentificador (String Identificador)
	{

		if (Identificador == null || Identificador.equals("A") || Identificador.equals("I") || Identificador.equals("E")); else throw new IllegalArgumentException ("Identificador Invalid value - " + Identificador + " - Reference_ID=1200082 - A - I - E");		set_Value (COLUMNNAME_Identificador, Identificador);
	}

	/** Get Identifier.
		@return Identifier	  */
	public String getIdentificador () 
	{
		return (String)get_Value(COLUMNNAME_Identificador);
	}

	/** Set ImpresionDe.
		@param ImpresionDe ImpresionDe	  */
	public void setImpresionDe (String ImpresionDe)
	{
		set_Value (COLUMNNAME_ImpresionDe, ImpresionDe);
	}

	/** Get ImpresionDe.
		@return ImpresionDe	  */
	public String getImpresionDe () 
	{
		return (String)get_Value(COLUMNNAME_ImpresionDe);
	}

	/** Set Impressions.
		@param Impresiones Impressions	  */
	public void setImpresiones (String Impresiones)
	{
		set_Value (COLUMNNAME_Impresiones, Impresiones);
	}

	/** Get Impressions.
		@return Impressions	  */
	public String getImpresiones () 
	{
		return (String)get_Value(COLUMNNAME_Impresiones);
	}

	/** Set Print Invoice.
		@param ImprimeFactura Print Invoice	  */
	public void setImprimeFactura (boolean ImprimeFactura)
	{
		set_Value (COLUMNNAME_ImprimeFactura, Boolean.valueOf(ImprimeFactura));
	}

	/** Get Print Invoice.
		@return Print Invoice	  */
	public boolean isImprimeFactura () 
	{
		Object oo = get_Value(COLUMNNAME_ImprimeFactura);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Invoice Print Format.
		@param Invoice_PrintFormat_ID 
		Print Format for printing Invoices
	  */
	public void setInvoice_PrintFormat_ID (int Invoice_PrintFormat_ID)
	{
		if (Invoice_PrintFormat_ID < 1) 
			set_Value (COLUMNNAME_Invoice_PrintFormat_ID, null);
		else 
			set_Value (COLUMNNAME_Invoice_PrintFormat_ID, Integer.valueOf(Invoice_PrintFormat_ID));
	}

	/** Get Invoice Print Format.
		@return Print Format for printing Invoices
	  */
	public int getInvoice_PrintFormat_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Invoice_PrintFormat_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** InvoiceRule AD_Reference_ID=150 */
	public static final int INVOICERULE_AD_Reference_ID=150;
	/** After Order delivered = O */
	public static final String INVOICERULE_AfterOrderDelivered = "O";
	/** After Delivery = D */
	public static final String INVOICERULE_AfterDelivery = "D";
	/** Customer Schedule after Delivery = S */
	public static final String INVOICERULE_CustomerScheduleAfterDelivery = "S";
	/** Immediate = I */
	public static final String INVOICERULE_Immediate = "I";
	/** Set Invoice Rule.
		@param InvoiceRule 
		Frequency and method of invoicing 
	  */
	public void setInvoiceRule (String InvoiceRule)
	{

		if (InvoiceRule == null || InvoiceRule.equals("O") || InvoiceRule.equals("D") || InvoiceRule.equals("S") || InvoiceRule.equals("I")); else throw new IllegalArgumentException ("InvoiceRule Invalid value - " + InvoiceRule + " - Reference_ID=150 - O - D - S - I");		set_Value (COLUMNNAME_InvoiceRule, InvoiceRule);
	}

	/** Get Invoice Rule.
		@return Frequency and method of invoicing 
	  */
	public String getInvoiceRule () 
	{
		return (String)get_Value(COLUMNNAME_InvoiceRule);
	}

	/** Set Main business partner.
		@param IsBanorte Main business partner	  */
	public void setIsBanorte (boolean IsBanorte)
	{
		set_Value (COLUMNNAME_IsBanorte, Boolean.valueOf(IsBanorte));
	}

	/** Get Main business partner.
		@return Main business partner	  */
	public boolean isBanorte () 
	{
		Object oo = get_Value(COLUMNNAME_IsBanorte);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Diverse Creditor.
		@param IsCreditor 
		Diverse Creditor
	  */
	public void setIsCreditor (boolean IsCreditor)
	{
		set_Value (COLUMNNAME_IsCreditor, Boolean.valueOf(IsCreditor));
	}

	/** Get Diverse Creditor.
		@return Diverse Creditor
	  */
	public boolean isCreditor () 
	{
		Object oo = get_Value(COLUMNNAME_IsCreditor);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Customer.
		@param IsCustomer 
		Indicates if this Business Partner is a Customer
	  */
	public void setIsCustomer (boolean IsCustomer)
	{
		set_Value (COLUMNNAME_IsCustomer, Boolean.valueOf(IsCustomer));
	}

	/** Get Customer.
		@return Indicates if this Business Partner is a Customer
	  */
	public boolean isCustomer () 
	{
		Object oo = get_Value(COLUMNNAME_IsCustomer);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Default.
		@param IsDefault 
		Default value
	  */
	public void setIsDefault (boolean IsDefault)
	{
		set_Value (COLUMNNAME_IsDefault, Boolean.valueOf(IsDefault));
	}

	/** Get Default.
		@return Default value
	  */
	public boolean isDefault () 
	{
		Object oo = get_Value(COLUMNNAME_IsDefault);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Discount Printed.
		@param IsDiscountPrinted 
		Print Discount on Invoice and Order
	  */
	public void setIsDiscountPrinted (boolean IsDiscountPrinted)
	{
		set_Value (COLUMNNAME_IsDiscountPrinted, Boolean.valueOf(IsDiscountPrinted));
	}

	/** Get Discount Printed.
		@return Print Discount on Invoice and Order
	  */
	public boolean isDiscountPrinted () 
	{
		Object oo = get_Value(COLUMNNAME_IsDiscountPrinted);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Employee.
		@param IsEmployee 
		Indicates if  this Business Partner is an employee
	  */
	public void setIsEmployee (boolean IsEmployee)
	{
		set_Value (COLUMNNAME_IsEmployee, Boolean.valueOf(IsEmployee));
	}

	/** Get Employee.
		@return Indicates if  this Business Partner is an employee
	  */
	public boolean isEmployee () 
	{
		Object oo = get_Value(COLUMNNAME_IsEmployee);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Billing after appointment.
		@param isExento Billing after appointment	  */
	public void setisExento (boolean isExento)
	{
		set_Value (COLUMNNAME_isExento, Boolean.valueOf(isExento));
	}

	/** Get Billing after appointment.
		@return Billing after appointment	  */
	public boolean isExento () 
	{
		Object oo = get_Value(COLUMNNAME_isExento);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Billing multiple.
		@param IsFactEspec Billing multiple	  */
	public void setIsFactEspec (boolean IsFactEspec)
	{
		set_Value (COLUMNNAME_IsFactEspec, Boolean.valueOf(IsFactEspec));
	}

	/** Get Billing multiple.
		@return Billing multiple	  */
	public boolean isFactEspec () 
	{
		Object oo = get_Value(COLUMNNAME_IsFactEspec);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Preferably generic name.
		@param IsGI Preferably generic name	  */
	public void setIsGI (boolean IsGI)
	{
		set_Value (COLUMNNAME_IsGI, Boolean.valueOf(IsGI));
	}

	/** Get Preferably generic name.
		@return Preferably generic name	  */
	public boolean isGI () 
	{
		Object oo = get_Value(COLUMNNAME_IsGI);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Allows mini packages.
		@param IsMiniPack 
		Allows mini packages
	  */
	public void setIsMiniPack (boolean IsMiniPack)
	{
		set_Value (COLUMNNAME_IsMiniPack, Boolean.valueOf(IsMiniPack));
	}

	/** Get Allows mini packages.
		@return Allows mini packages
	  */
	public boolean isMiniPack () 
	{
		Object oo = get_Value(COLUMNNAME_IsMiniPack);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set National.
		@param IsNational 
		National
	  */
	public void setIsNational (boolean IsNational)
	{
		set_Value (COLUMNNAME_IsNational, Boolean.valueOf(IsNational));
	}

	/** Get National.
		@return National
	  */
	public boolean isNational () 
	{
		Object oo = get_Value(COLUMNNAME_IsNational);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Allows debit memo.
		@param IsNotaCargo 
		Allows debit memo
	  */
	public void setIsNotaCargo (boolean IsNotaCargo)
	{
		set_Value (COLUMNNAME_IsNotaCargo, Boolean.valueOf(IsNotaCargo));
	}

	/** Get Allows debit memo.
		@return Allows debit memo
	  */
	public boolean isNotaCargo () 
	{
		Object oo = get_Value(COLUMNNAME_IsNotaCargo);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set One time transaction.
		@param IsOneTime One time transaction	  */
	public void setIsOneTime (boolean IsOneTime)
	{
		set_Value (COLUMNNAME_IsOneTime, Boolean.valueOf(IsOneTime));
	}

	/** Get One time transaction.
		@return One time transaction	  */
	public boolean isOneTime () 
	{
		Object oo = get_Value(COLUMNNAME_IsOneTime);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Sort by Category invoice line.
		@param IsOrderFacLineCategory Sort by Category invoice line	  */
	public void setIsOrderFacLineCategory (boolean IsOrderFacLineCategory)
	{
		set_Value (COLUMNNAME_IsOrderFacLineCategory, Boolean.valueOf(IsOrderFacLineCategory));
	}

	/** Get Sort by Category invoice line.
		@return Sort by Category invoice line	  */
	public boolean isOrderFacLineCategory () 
	{
		Object oo = get_Value(COLUMNNAME_IsOrderFacLineCategory);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Patient.
		@param IsPatient 
		Is patient
	  */
	public void setIsPatient (boolean IsPatient)
	{
		set_Value (COLUMNNAME_IsPatient, Boolean.valueOf(IsPatient));
	}

	/** Get Patient.
		@return Is patient
	  */
	public boolean isPatient () 
	{
		Object oo = get_Value(COLUMNNAME_IsPatient);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Pension.
		@param IsPension Pension	  */
	public void setIsPension (boolean IsPension)
	{
		set_Value (COLUMNNAME_IsPension, Boolean.valueOf(IsPension));
	}

	/** Get Pension.
		@return Pension	  */
	public boolean isPension () 
	{
		Object oo = get_Value(COLUMNNAME_IsPension);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set PO Tax exempt.
		@param IsPOTaxExempt 
		Business partner is exempt from tax on purchases
	  */
	public void setIsPOTaxExempt (boolean IsPOTaxExempt)
	{
		set_Value (COLUMNNAME_IsPOTaxExempt, Boolean.valueOf(IsPOTaxExempt));
	}

	/** Get PO Tax exempt.
		@return Business partner is exempt from tax on purchases
	  */
	public boolean isPOTaxExempt () 
	{
		Object oo = get_Value(COLUMNNAME_IsPOTaxExempt);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Prospect.
		@param IsProspect 
		Indicates this is a Prospect
	  */
	public void setIsProspect (boolean IsProspect)
	{
		set_Value (COLUMNNAME_IsProspect, Boolean.valueOf(IsProspect));
	}

	/** Get Prospect.
		@return Indicates this is a Prospect
	  */
	public boolean isProspect () 
	{
		Object oo = get_Value(COLUMNNAME_IsProspect);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Sales Representative.
		@param IsSalesRep 
		Indicates if  the business partner is a sales representative or company agent
	  */
	public void setIsSalesRep (boolean IsSalesRep)
	{
		set_Value (COLUMNNAME_IsSalesRep, Boolean.valueOf(IsSalesRep));
	}

	/** Get Sales Representative.
		@return Indicates if  the business partner is a sales representative or company agent
	  */
	public boolean isSalesRep () 
	{
		Object oo = get_Value(COLUMNNAME_IsSalesRep);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Summary Level.
		@param IsSummary 
		This is a summary entity
	  */
	public void setIsSummary (boolean IsSummary)
	{
		set_Value (COLUMNNAME_IsSummary, Boolean.valueOf(IsSummary));
	}

	/** Get Summary Level.
		@return This is a summary entity
	  */
	public boolean isSummary () 
	{
		Object oo = get_Value(COLUMNNAME_IsSummary);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Tax exempt.
		@param IsTaxExempt 
		Business partner is exempt from tax
	  */
	public void setIsTaxExempt (boolean IsTaxExempt)
	{
		set_Value (COLUMNNAME_IsTaxExempt, Boolean.valueOf(IsTaxExempt));
	}

	/** Get Tax exempt.
		@return Business partner is exempt from tax
	  */
	public boolean isTaxExempt () 
	{
		Object oo = get_Value(COLUMNNAME_IsTaxExempt);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Vendor.
		@param IsVendor 
		Indicates if this Business Partner is a Vendor
	  */
	public void setIsVendor (boolean IsVendor)
	{
		set_Value (COLUMNNAME_IsVendor, Boolean.valueOf(IsVendor));
	}

	/** Get Vendor.
		@return Indicates if this Business Partner is a Vendor
	  */
	public boolean isVendor () 
	{
		Object oo = get_Value(COLUMNNAME_IsVendor);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set LlaveDeBusqueda.
		@param LlaveDeBusqueda LlaveDeBusqueda	  */
	public void setLlaveDeBusqueda (String LlaveDeBusqueda)
	{
		set_Value (COLUMNNAME_LlaveDeBusqueda, LlaveDeBusqueda);
	}

	/** Get LlaveDeBusqueda.
		@return LlaveDeBusqueda	  */
	public String getLlaveDeBusqueda () 
	{
		return (String)get_Value(COLUMNNAME_LlaveDeBusqueda);
	}

	/** Set Logo.
		@param Logo_ID Logo	  */
	public void setLogo_ID (int Logo_ID)
	{
		if (Logo_ID < 1) 
			set_Value (COLUMNNAME_Logo_ID, null);
		else 
			set_Value (COLUMNNAME_Logo_ID, Integer.valueOf(Logo_ID));
	}

	/** Get Logo.
		@return Logo	  */
	public int getLogo_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Logo_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Discount Schema.
		@param M_DiscountSchema_ID 
		Schema to calculate the trade discount percentage
	  */
	public void setM_DiscountSchema_ID (int M_DiscountSchema_ID)
	{
		if (M_DiscountSchema_ID < 1) 
			set_Value (COLUMNNAME_M_DiscountSchema_ID, null);
		else 
			set_Value (COLUMNNAME_M_DiscountSchema_ID, Integer.valueOf(M_DiscountSchema_ID));
	}

	/** Get Discount Schema.
		@return Schema to calculate the trade discount percentage
	  */
	public int getM_DiscountSchema_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_DiscountSchema_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Modify In Invoice.
		@param ModificaEnFactura Modify In Invoice	  */
	public void setModificaEnFactura (boolean ModificaEnFactura)
	{
		set_Value (COLUMNNAME_ModificaEnFactura, Boolean.valueOf(ModificaEnFactura));
	}

	/** Get Modify In Invoice.
		@return Modify In Invoice	  */
	public boolean isModificaEnFactura () 
	{
		Object oo = get_Value(COLUMNNAME_ModificaEnFactura);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	public I_M_PriceList getM_PriceList() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_M_PriceList.Table_Name);
        I_M_PriceList result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_M_PriceList)constructor.newInstance(new Object[] {getCtx(), new Integer(getM_PriceList_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Price List.
		@param M_PriceList_ID 
		Unique identifier of a Price List
	  */
	public void setM_PriceList_ID (int M_PriceList_ID)
	{
		if (M_PriceList_ID < 1) 
			set_Value (COLUMNNAME_M_PriceList_ID, null);
		else 
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

	/** Set NAICS/SIC.
		@param NAICS 
		Standard Industry Code or its successor NAIC - http://www.osha.gov/oshstats/sicser.html
	  */
	public void setNAICS (String NAICS)
	{
		set_Value (COLUMNNAME_NAICS, NAICS);
	}

	/** Get NAICS/SIC.
		@return Standard Industry Code or its successor NAIC - http://www.osha.gov/oshstats/sicser.html
	  */
	public String getNAICS () 
	{
		return (String)get_Value(COLUMNNAME_NAICS);
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

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), getName());
    }

	/** Set Name 2.
		@param Name2 
		Additional Name
	  */
	public void setName2 (String Name2)
	{
		set_Value (COLUMNNAME_Name2, Name2);
	}

	/** Get Name 2.
		@return Additional Name
	  */
	public String getName2 () 
	{
		return (String)get_Value(COLUMNNAME_Name2);
	}

	/** Set Employees.
		@param NumberEmployees 
		Number of employees
	  */
	public void setNumberEmployees (int NumberEmployees)
	{
		set_Value (COLUMNNAME_NumberEmployees, Integer.valueOf(NumberEmployees));
	}

	/** Get Employees.
		@return Number of employees
	  */
	public int getNumberEmployees () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_NumberEmployees);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** PaymentRule AD_Reference_ID=195 */
	public static final int PAYMENTRULE_AD_Reference_ID=195;
	/** Cash = B */
	public static final String PAYMENTRULE_Cash = "B";
	/** Credit Card = K */
	public static final String PAYMENTRULE_CreditCard = "K";
	/** Direct Deposit = T */
	public static final String PAYMENTRULE_DirectDeposit = "T";
	/** Check = S */
	public static final String PAYMENTRULE_Check = "S";
	/** On Credit = P */
	public static final String PAYMENTRULE_OnCredit = "P";
	/** Direct Debit = D */
	public static final String PAYMENTRULE_DirectDebit = "D";
	/** Other = O */
	public static final String PAYMENTRULE_Other = "O";
	/** Prepayment = A */
	public static final String PAYMENTRULE_Prepayment = "A";
	/** Mixed = M */
	public static final String PAYMENTRULE_Mixed = "M";
	/** Set Payment Rule.
		@param PaymentRule 
		How you pay the invoice
	  */
	public void setPaymentRule (String PaymentRule)
	{

		if (PaymentRule == null || PaymentRule.equals("B") || PaymentRule.equals("K") || PaymentRule.equals("T") || PaymentRule.equals("S") || PaymentRule.equals("P") || PaymentRule.equals("D") || PaymentRule.equals("O") || PaymentRule.equals("A") || PaymentRule.equals("M")); else throw new IllegalArgumentException ("PaymentRule Invalid value - " + PaymentRule + " - Reference_ID=195 - B - K - T - S - P - D - O - A - M");		set_Value (COLUMNNAME_PaymentRule, PaymentRule);
	}

	/** Get Payment Rule.
		@return How you pay the invoice
	  */
	public String getPaymentRule () 
	{
		return (String)get_Value(COLUMNNAME_PaymentRule);
	}

	/** PaymentRulePO AD_Reference_ID=195 */
	public static final int PAYMENTRULEPO_AD_Reference_ID=195;
	/** Cash = B */
	public static final String PAYMENTRULEPO_Cash = "B";
	/** Credit Card = K */
	public static final String PAYMENTRULEPO_CreditCard = "K";
	/** Direct Deposit = T */
	public static final String PAYMENTRULEPO_DirectDeposit = "T";
	/** Check = S */
	public static final String PAYMENTRULEPO_Check = "S";
	/** On Credit = P */
	public static final String PAYMENTRULEPO_OnCredit = "P";
	/** Direct Debit = D */
	public static final String PAYMENTRULEPO_DirectDebit = "D";
	/** Other = O */
	public static final String PAYMENTRULEPO_Other = "O";
	/** Prepayment = A */
	public static final String PAYMENTRULEPO_Prepayment = "A";
	/** Mixed = M */
	public static final String PAYMENTRULEPO_Mixed = "M";
	/** Set Payment Rule.
		@param PaymentRulePO 
		Purchase payment option
	  */
	public void setPaymentRulePO (String PaymentRulePO)
	{

		if (PaymentRulePO == null || PaymentRulePO.equals("B") || PaymentRulePO.equals("K") || PaymentRulePO.equals("T") || PaymentRulePO.equals("S") || PaymentRulePO.equals("P") || PaymentRulePO.equals("D") || PaymentRulePO.equals("O") || PaymentRulePO.equals("A") || PaymentRulePO.equals("M")); else throw new IllegalArgumentException ("PaymentRulePO Invalid value - " + PaymentRulePO + " - Reference_ID=195 - B - K - T - S - P - D - O - A - M");		set_Value (COLUMNNAME_PaymentRulePO, PaymentRulePO);
	}

	/** Get Payment Rule.
		@return Purchase payment option
	  */
	public String getPaymentRulePO () 
	{
		return (String)get_Value(COLUMNNAME_PaymentRulePO);
	}

	/** Set PO Discount Schema.
		@param PO_DiscountSchema_ID 
		Schema to calculate the purchase trade discount percentage
	  */
	public void setPO_DiscountSchema_ID (int PO_DiscountSchema_ID)
	{
		if (PO_DiscountSchema_ID < 1) 
			set_Value (COLUMNNAME_PO_DiscountSchema_ID, null);
		else 
			set_Value (COLUMNNAME_PO_DiscountSchema_ID, Integer.valueOf(PO_DiscountSchema_ID));
	}

	/** Get PO Discount Schema.
		@return Schema to calculate the purchase trade discount percentage
	  */
	public int getPO_DiscountSchema_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_PO_DiscountSchema_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set PO Payment Term.
		@param PO_PaymentTerm_ID 
		Payment rules for a purchase order
	  */
	public void setPO_PaymentTerm_ID (int PO_PaymentTerm_ID)
	{
		if (PO_PaymentTerm_ID < 1) 
			set_Value (COLUMNNAME_PO_PaymentTerm_ID, null);
		else 
			set_Value (COLUMNNAME_PO_PaymentTerm_ID, Integer.valueOf(PO_PaymentTerm_ID));
	}

	/** Get PO Payment Term.
		@return Payment rules for a purchase order
	  */
	public int getPO_PaymentTerm_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_PO_PaymentTerm_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Purchase Pricelist.
		@param PO_PriceList_ID 
		Price List used by this Business Partner
	  */
	public void setPO_PriceList_ID (int PO_PriceList_ID)
	{
		if (PO_PriceList_ID < 1) 
			set_Value (COLUMNNAME_PO_PriceList_ID, null);
		else 
			set_Value (COLUMNNAME_PO_PriceList_ID, Integer.valueOf(PO_PriceList_ID));
	}

	/** Get Purchase Pricelist.
		@return Price List used by this Business Partner
	  */
	public int getPO_PriceList_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_PO_PriceList_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Order Reference.
		@param POReference 
		Transaction Reference Number (Sales Order, Purchase Order) of your Business Partner
	  */
	public void setPOReference (String POReference)
	{
		set_Value (COLUMNNAME_POReference, POReference);
	}

	/** Get Order Reference.
		@return Transaction Reference Number (Sales Order, Purchase Order) of your Business Partner
	  */
	public String getPOReference () 
	{
		return (String)get_Value(COLUMNNAME_POReference);
	}

	/** Set Potential Life Time Value.
		@param PotentialLifeTimeValue 
		Total Revenue expected
	  */
	public void setPotentialLifeTimeValue (BigDecimal PotentialLifeTimeValue)
	{
		set_Value (COLUMNNAME_PotentialLifeTimeValue, PotentialLifeTimeValue);
	}

	/** Get Potential Life Time Value.
		@return Total Revenue expected
	  */
	public BigDecimal getPotentialLifeTimeValue () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_PotentialLifeTimeValue);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Queuing Time.
		@param QueuingTime Queuing Time	  */
	public void setQueuingTime (int QueuingTime)
	{
		set_Value (COLUMNNAME_QueuingTime, Integer.valueOf(QueuingTime));
	}

	/** Get Queuing Time.
		@return Queuing Time	  */
	public int getQueuingTime () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_QueuingTime);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Rating.
		@param Rating 
		Classification or Importance
	  */
	public void setRating (String Rating)
	{
		set_Value (COLUMNNAME_Rating, Rating);
	}

	/** Get Rating.
		@return Classification or Importance
	  */
	public String getRating () 
	{
		return (String)get_Value(COLUMNNAME_Rating);
	}

	/** Set Reference No.
		@param ReferenceNo 
		Your customer or vendor number at the Business Partner's site
	  */
	public void setReferenceNo (String ReferenceNo)
	{
		set_Value (COLUMNNAME_ReferenceNo, ReferenceNo);
	}

	/** Get Reference No.
		@return Your customer or vendor number at the Business Partner's site
	  */
	public String getReferenceNo () 
	{
		return (String)get_Value(COLUMNNAME_ReferenceNo);
	}

	/** Set Customer Reference.
		@param Referencia_Cliente Customer Reference	  */
	public void setReferencia_Cliente (String Referencia_Cliente)
	{
		set_Value (COLUMNNAME_Referencia_Cliente, Referencia_Cliente);
	}

	/** Get Customer Reference.
		@return Customer Reference	  */
	public String getReferencia_Cliente () 
	{
		return (String)get_Value(COLUMNNAME_Referencia_Cliente);
	}

	/** Set Reference Address.
		@param Referencia_Direccion Reference Address	  */
	public void setReferencia_Direccion (String Referencia_Direccion)
	{
		set_Value (COLUMNNAME_Referencia_Direccion, Referencia_Direccion);
	}

	/** Get Reference Address.
		@return Reference Address	  */
	public String getReferencia_Direccion () 
	{
		return (String)get_Value(COLUMNNAME_Referencia_Direccion);
	}

	/** Set Eligibility require.
		@param RequerirEleg Eligibility require	  */
	public void setRequerirEleg (boolean RequerirEleg)
	{
		set_Value (COLUMNNAME_RequerirEleg, Boolean.valueOf(RequerirEleg));
	}

	/** Get Eligibility require.
		@return Eligibility require	  */
	public boolean isRequerirEleg () 
	{
		Object oo = get_Value(COLUMNNAME_RequerirEleg);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set reward_amount.
		@param reward_amount reward_amount	  */
	public void setreward_amount (BigDecimal reward_amount)
	{
		set_Value (COLUMNNAME_reward_amount, reward_amount);
	}

	/** Get reward_amount.
		@return reward_amount	  */
	public BigDecimal getreward_amount () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_reward_amount);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set reward_card.
		@param reward_card reward_card	  */
	public void setreward_card (String reward_card)
	{
		set_Value (COLUMNNAME_reward_card, reward_card);
	}

	/** Get reward_card.
		@return reward_card	  */
	public String getreward_card () 
	{
		return (String)get_Value(COLUMNNAME_reward_card);
	}

	/** Set Taxpayer Identification Number.
		@param Rfc 
		Taxpayer Identification Number
	  */
	public void setRfc (String Rfc)
	{
		set_Value (COLUMNNAME_Rfc, Rfc);
	}

	/** Get Taxpayer Identification Number.
		@return Taxpayer Identification Number
	  */
	public String getRfc () 
	{
		return (String)get_Value(COLUMNNAME_Rfc);
	}

	/** Set Sales Representative.
		@param SalesRep_ID 
		Sales Representative or Company Agent
	  */
	public void setSalesRep_ID (int SalesRep_ID)
	{
		if (SalesRep_ID < 1) 
			set_Value (COLUMNNAME_SalesRep_ID, null);
		else 
			set_Value (COLUMNNAME_SalesRep_ID, Integer.valueOf(SalesRep_ID));
	}

	/** Get Sales Representative.
		@return Sales Representative or Company Agent
	  */
	public int getSalesRep_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_SalesRep_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Sales Volume in 1.000.
		@param SalesVolume 
		Total Volume of Sales in Thousands of Currency
	  */
	public void setSalesVolume (int SalesVolume)
	{
		set_Value (COLUMNNAME_SalesVolume, Integer.valueOf(SalesVolume));
	}

	/** Get Sales Volume in 1.000.
		@return Total Volume of Sales in Thousands of Currency
	  */
	public int getSalesVolume () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_SalesVolume);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Send EMail.
		@param SendEMail 
		Enable sending Document EMail
	  */
	public void setSendEMail (boolean SendEMail)
	{
		set_Value (COLUMNNAME_SendEMail, Boolean.valueOf(SendEMail));
	}

	/** Get Send EMail.
		@return Enable sending Document EMail
	  */
	public boolean isSendEMail () 
	{
		Object oo = get_Value(COLUMNNAME_SendEMail);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Share.
		@param ShareOfCustomer 
		Share of Customer's business as a percentage
	  */
	public void setShareOfCustomer (int ShareOfCustomer)
	{
		set_Value (COLUMNNAME_ShareOfCustomer, Integer.valueOf(ShareOfCustomer));
	}

	/** Get Share.
		@return Share of Customer's business as a percentage
	  */
	public int getShareOfCustomer () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_ShareOfCustomer);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Min Shelf Life %.
		@param ShelfLifeMinPct 
		Minimum Shelf Life in percent based on Product Instance Guarantee Date
	  */
	public void setShelfLifeMinPct (int ShelfLifeMinPct)
	{
		set_Value (COLUMNNAME_ShelfLifeMinPct, Integer.valueOf(ShelfLifeMinPct));
	}

	/** Get Min Shelf Life %.
		@return Minimum Shelf Life in percent based on Product Instance Guarantee Date
	  */
	public int getShelfLifeMinPct () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_ShelfLifeMinPct);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Credit Limit.
		@param SO_CreditLimit 
		Total outstanding invoice amounts allowed
	  */
	public void setSO_CreditLimit (BigDecimal SO_CreditLimit)
	{
		if (SO_CreditLimit == null)
			throw new IllegalArgumentException ("SO_CreditLimit is mandatory.");
		set_Value (COLUMNNAME_SO_CreditLimit, SO_CreditLimit);
	}

	/** Get Credit Limit.
		@return Total outstanding invoice amounts allowed
	  */
	public BigDecimal getSO_CreditLimit () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_SO_CreditLimit);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** SOCreditStatus AD_Reference_ID=289 */
	public static final int SOCREDITSTATUS_AD_Reference_ID=289;
	/** Credit Stop = S */
	public static final String SOCREDITSTATUS_CreditStop = "S";
	/** Credit Hold = H */
	public static final String SOCREDITSTATUS_CreditHold = "H";
	/** Credit Watch = W */
	public static final String SOCREDITSTATUS_CreditWatch = "W";
	/** No Credit Check = X */
	public static final String SOCREDITSTATUS_NoCreditCheck = "X";
	/** Credit OK = O */
	public static final String SOCREDITSTATUS_CreditOK = "O";
	/** Set Credit Status.
		@param SOCreditStatus 
		Business Partner Credit Status
	  */
	public void setSOCreditStatus (String SOCreditStatus)
	{

		if (SOCreditStatus == null || SOCreditStatus.equals("S") || SOCreditStatus.equals("H") || SOCreditStatus.equals("W") || SOCreditStatus.equals("X") || SOCreditStatus.equals("O")); else throw new IllegalArgumentException ("SOCreditStatus Invalid value - " + SOCreditStatus + " - Reference_ID=289 - S - H - W - X - O");		set_Value (COLUMNNAME_SOCreditStatus, SOCreditStatus);
	}

	/** Get Credit Status.
		@return Business Partner Credit Status
	  */
	public String getSOCreditStatus () 
	{
		return (String)get_Value(COLUMNNAME_SOCreditStatus);
	}

	/** Set Credit Used.
		@param SO_CreditUsed 
		Current open balance
	  */
	public void setSO_CreditUsed (BigDecimal SO_CreditUsed)
	{
		if (SO_CreditUsed == null)
			throw new IllegalArgumentException ("SO_CreditUsed is mandatory.");
		set_ValueNoCheck (COLUMNNAME_SO_CreditUsed, SO_CreditUsed);
	}

	/** Get Credit Used.
		@return Current open balance
	  */
	public BigDecimal getSO_CreditUsed () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_SO_CreditUsed);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Order Description.
		@param SO_Description 
		Description to be used on orders
	  */
	public void setSO_Description (String SO_Description)
	{
		set_Value (COLUMNNAME_SO_Description, SO_Description);
	}

	/** Get Order Description.
		@return Description to be used on orders
	  */
	public String getSO_Description () 
	{
		return (String)get_Value(COLUMNNAME_SO_Description);
	}

	/** SupportBilling AD_Reference_ID=1200578 */
	public static final int SUPPORTBILLING_AD_Reference_ID=1200578;
	/** Professional = PR */
	public static final String SUPPORTBILLING_Professional = "PR";
	/** Institucional = IN */
	public static final String SUPPORTBILLING_Institucional = "IN";
	/** Both = BO */
	public static final String SUPPORTBILLING_Both = "BO";
	/** Set Support Billing.
		@param SupportBilling Support Billing	  */
	public void setSupportBilling (String SupportBilling)
	{

		if (SupportBilling == null || SupportBilling.equals("PR") || SupportBilling.equals("IN") || SupportBilling.equals("BO")); else throw new IllegalArgumentException ("SupportBilling Invalid value - " + SupportBilling + " - Reference_ID=1200578 - PR - IN - BO");		set_Value (COLUMNNAME_SupportBilling, SupportBilling);
	}

	/** Get Support Billing.
		@return Support Billing	  */
	public String getSupportBilling () 
	{
		return (String)get_Value(COLUMNNAME_SupportBilling);
	}

	/** Set Support eClaim.
		@param SupportEclaim Support eClaim	  */
	public void setSupportEclaim (boolean SupportEclaim)
	{
		set_Value (COLUMNNAME_SupportEclaim, Boolean.valueOf(SupportEclaim));
	}

	/** Get Support eClaim.
		@return Support eClaim	  */
	public boolean isSupportEclaim () 
	{
		Object oo = get_Value(COLUMNNAME_SupportEclaim);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Support Elegibility Verification.
		@param SupportElegibilityVerification Support Elegibility Verification	  */
	public void setSupportElegibilityVerification (boolean SupportElegibilityVerification)
	{
		set_Value (COLUMNNAME_SupportElegibilityVerification, Boolean.valueOf(SupportElegibilityVerification));
	}

	/** Get Support Elegibility Verification.
		@return Support Elegibility Verification	  */
	public boolean isSupportElegibilityVerification () 
	{
		Object oo = get_Value(COLUMNNAME_SupportElegibilityVerification);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Tax ID.
		@param TaxID 
		Tax Identification
	  */
	public void setTaxID (String TaxID)
	{
		set_Value (COLUMNNAME_TaxID, TaxID);
	}

	/** Get Tax ID.
		@return Tax Identification
	  */
	public String getTaxID () 
	{
		return (String)get_Value(COLUMNNAME_TaxID);
	}

	/** TipoProveedor AD_Reference_ID=1200104 */
	public static final int TIPOPROVEEDOR_AD_Reference_ID=1200104;
	/** National = 04 */
	public static final String TIPOPROVEEDOR_National = "04";
	/** Extrajero = 05 */
	public static final String TIPOPROVEEDOR_Extrajero = "05";
	/** Accionistas Socios o Integrantes = 07 */
	public static final String TIPOPROVEEDOR_AccionistasSociosOIntegrantes = "07";
	/** Retenciones(Excepto Proveedores) = 11 */
	public static final String TIPOPROVEEDOR_RetencionesExceptoProveedores = "11";
	/** Proveedor Global = 15 */
	public static final String TIPOPROVEEDOR_ProveedorGlobal = "15";
	/** Set Provider Type.
		@param TipoProveedor Provider Type	  */
	public void setTipoProveedor (String TipoProveedor)
	{
		if (TipoProveedor == null) throw new IllegalArgumentException ("TipoProveedor is mandatory");
		if (TipoProveedor.equals("04") || TipoProveedor.equals("05") || TipoProveedor.equals("07") || TipoProveedor.equals("11") || TipoProveedor.equals("15")); else throw new IllegalArgumentException ("TipoProveedor Invalid value - " + TipoProveedor + " - Reference_ID=1200104 - 04 - 05 - 07 - 11 - 15");		set_Value (COLUMNNAME_TipoProveedor, TipoProveedor);
	}

	/** Get Provider Type.
		@return Provider Type	  */
	public String getTipoProveedor () 
	{
		return (String)get_Value(COLUMNNAME_TipoProveedor);
	}

	/** Set Open Balance.
		@param TotalOpenBalance 
		Total Open Balance Amount in primary Accounting Currency
	  */
	public void setTotalOpenBalance (BigDecimal TotalOpenBalance)
	{
		set_Value (COLUMNNAME_TotalOpenBalance, TotalOpenBalance);
	}

	/** Get Open Balance.
		@return Total Open Balance Amount in primary Accounting Currency
	  */
	public BigDecimal getTotalOpenBalance () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_TotalOpenBalance);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set URL.
		@param URL 
		Full URL address - e.g. http://www.compiere.org
	  */
	public void setURL (String URL)
	{
		set_Value (COLUMNNAME_URL, URL);
	}

	/** Get URL.
		@return Full URL address - e.g. http://www.compiere.org
	  */
	public String getURL () 
	{
		return (String)get_Value(COLUMNNAME_URL);
	}

	/** Set Use Clearing House.
		@param UseClearingHouse Use Clearing House	  */
	public void setUseClearingHouse (boolean UseClearingHouse)
	{
		set_Value (COLUMNNAME_UseClearingHouse, Boolean.valueOf(UseClearingHouse));
	}

	/** Get Use Clearing House.
		@return Use Clearing House	  */
	public boolean isUseClearingHouse () 
	{
		Object oo = get_Value(COLUMNNAME_UseClearingHouse);
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