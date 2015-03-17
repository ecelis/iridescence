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

/** Generated Model for AD_OrgInfo
 *  @author Generated Class 
 *  @version Version @VERSION@ - $Id$ */
public class X_AD_OrgInfo extends PO implements I_AD_OrgInfo, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_AD_OrgInfo (Properties ctx, int AD_OrgInfo_ID, String trxName)
    {
      super (ctx, AD_OrgInfo_ID, trxName);
      /** if (AD_OrgInfo_ID == 0)
        {
			setDUNS (null);
			setReceiptFooterMsg (null);
// 1
			setTaxID (null);
			setTipoCliente (null);
        } */
    }

    /** Load Constructor */
    public X_AD_OrgInfo (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_AD_OrgInfo[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public I_AD_OrgType getAD_OrgType() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_AD_OrgType.Table_Name);
        I_AD_OrgType result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_AD_OrgType)constructor.newInstance(new Object[] {getCtx(), new Integer(getAD_OrgType_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Organization Type.
		@param AD_OrgType_ID 
		Organization Type allows you to categorize your organizations
	  */
	public void setAD_OrgType_ID (int AD_OrgType_ID)
	{
		if (AD_OrgType_ID < 1) 
			set_Value (COLUMNNAME_AD_OrgType_ID, null);
		else 
			set_Value (COLUMNNAME_AD_OrgType_ID, Integer.valueOf(AD_OrgType_ID));
	}

	/** Get Organization Type.
		@return Organization Type allows you to categorize your organizations
	  */
	public int getAD_OrgType_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_OrgType_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Primary Org.
		@param AD_PrimaryOrg_ID Primary Org	  */
	public void setAD_PrimaryOrg_ID (int AD_PrimaryOrg_ID)
	{
		if (AD_PrimaryOrg_ID < 1) 
			set_Value (COLUMNNAME_AD_PrimaryOrg_ID, null);
		else 
			set_Value (COLUMNNAME_AD_PrimaryOrg_ID, Integer.valueOf(AD_PrimaryOrg_ID));
	}

	/** Get Primary Org.
		@return Primary Org	  */
	public int getAD_PrimaryOrg_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_PrimaryOrg_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_C_Calendar getC_Calendar() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_C_Calendar.Table_Name);
        I_C_Calendar result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_C_Calendar)constructor.newInstance(new Object[] {getCtx(), new Integer(getC_Calendar_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Calendar.
		@param C_Calendar_ID 
		Accounting Calendar Name
	  */
	public void setC_Calendar_ID (int C_Calendar_ID)
	{
		if (C_Calendar_ID < 1) 
			set_Value (COLUMNNAME_C_Calendar_ID, null);
		else 
			set_Value (COLUMNNAME_C_Calendar_ID, Integer.valueOf(C_Calendar_ID));
	}

	/** Get Calendar.
		@return Accounting Calendar Name
	  */
	public int getC_Calendar_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Calendar_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Physical Address.
		@param C_LocationPhys_ID Physical Address	  */
	public void setC_LocationPhys_ID (int C_LocationPhys_ID)
	{
		if (C_LocationPhys_ID < 1) 
			set_Value (COLUMNNAME_C_LocationPhys_ID, null);
		else 
			set_Value (COLUMNNAME_C_LocationPhys_ID, Integer.valueOf(C_LocationPhys_ID));
	}

	/** Get Physical Address.
		@return Physical Address	  */
	public int getC_LocationPhys_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_LocationPhys_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Address .
		@param C_Location_ID 
		Location or Address
	  */
	public void setC_Location_ID (int C_Location_ID)
	{
		if (C_Location_ID < 1) 
			set_Value (COLUMNNAME_C_Location_ID, null);
		else 
			set_Value (COLUMNNAME_C_Location_ID, Integer.valueOf(C_Location_ID));
	}

	/** Get Address .
		@return Location or Address
	  */
	public int getC_Location_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Location_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Claim Type.
		@param ClaimType Claim Type	  */
	public void setClaimType (boolean ClaimType)
	{
		set_Value (COLUMNNAME_ClaimType, Boolean.valueOf(ClaimType));
	}

	/** Get Claim Type.
		@return Claim Type	  */
	public boolean isClaimType () 
	{
		Object oo = get_Value(COLUMNNAME_ClaimType);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set D-U-N-S.
		@param DUNS 
		Dun & Bradstreet Number
	  */
	public void setDUNS (String DUNS)
	{
		if (DUNS == null)
			throw new IllegalArgumentException ("DUNS is mandatory.");
		set_Value (COLUMNNAME_DUNS, DUNS);
	}

	/** Get D-U-N-S.
		@return Dun & Bradstreet Number
	  */
	public String getDUNS () 
	{
		return (String)get_Value(COLUMNNAME_DUNS);
	}

	/** Set Drop Ship Warehouse.
		@param DropShip_Warehouse_ID 
		The (logical) warehouse to use for recording drop ship receipts and shipments.
	  */
	public void setDropShip_Warehouse_ID (int DropShip_Warehouse_ID)
	{
		if (DropShip_Warehouse_ID < 1) 
			set_Value (COLUMNNAME_DropShip_Warehouse_ID, null);
		else 
			set_Value (COLUMNNAME_DropShip_Warehouse_ID, Integer.valueOf(DropShip_Warehouse_ID));
	}

	/** Get Drop Ship Warehouse.
		@return The (logical) warehouse to use for recording drop ship receipts and shipments.
	  */
	public int getDropShip_Warehouse_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DropShip_Warehouse_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_EULA getEXME_EULA() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_EULA.Table_Name);
        I_EXME_EULA result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_EULA)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_EULA_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set End User Agreement License.
		@param EXME_EULA_ID 
		The End User Agreement License
	  */
	public void setEXME_EULA_ID (int EXME_EULA_ID)
	{
		if (EXME_EULA_ID < 1) 
			set_Value (COLUMNNAME_EXME_EULA_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_EULA_ID, Integer.valueOf(EXME_EULA_ID));
	}

	/** Get End User Agreement License.
		@return The End User Agreement License
	  */
	public int getEXME_EULA_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_EULA_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set External Service Facility.
		@param EXME_InstitucionExt_ID External Service Facility	  */
	public void setEXME_InstitucionExt_ID (int EXME_InstitucionExt_ID)
	{
		if (EXME_InstitucionExt_ID < 1) 
			set_Value (COLUMNNAME_EXME_InstitucionExt_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_InstitucionExt_ID, Integer.valueOf(EXME_InstitucionExt_ID));
	}

	/** Get External Service Facility.
		@return External Service Facility	  */
	public int getEXME_InstitucionExt_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_InstitucionExt_ID);
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

	public I_EXME_Tipologia getEXME_Tipologia() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_Tipologia.Table_Name);
        I_EXME_Tipologia result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_Tipologia)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Tipologia_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Typology.
		@param EXME_Tipologia_ID Typology	  */
	public void setEXME_Tipologia_ID (int EXME_Tipologia_ID)
	{
		if (EXME_Tipologia_ID < 1) 
			set_Value (COLUMNNAME_EXME_Tipologia_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_Tipologia_ID, Integer.valueOf(EXME_Tipologia_ID));
	}

	/** Get Typology.
		@return Typology	  */
	public int getEXME_Tipologia_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Tipologia_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Type Of Bill.
		@param EXME_TypeOfBill_ID 
		Type Of Bill
	  */
	public void setEXME_TypeOfBill_ID (int EXME_TypeOfBill_ID)
	{
		if (EXME_TypeOfBill_ID < 1) 
			set_Value (COLUMNNAME_EXME_TypeOfBill_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_TypeOfBill_ID, Integer.valueOf(EXME_TypeOfBill_ID));
	}

	/** Get Type Of Bill.
		@return Type Of Bill
	  */
	public int getEXME_TypeOfBill_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_TypeOfBill_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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

	/** Set NPI.
		@param NPI NPI	  */
	public void setNPI (String NPI)
	{
		set_Value (COLUMNNAME_NPI, NPI);
	}

	/** Get NPI.
		@return NPI	  */
	public String getNPI () 
	{
		return (String)get_Value(COLUMNNAME_NPI);
	}

	public I_PA_Goal getPA_Goal() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_PA_Goal.Table_Name);
        I_PA_Goal result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_PA_Goal)constructor.newInstance(new Object[] {getCtx(), new Integer(getPA_Goal_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Goal.
		@param PA_Goal_ID 
		Performance Goal
	  */
	public void setPA_Goal_ID (int PA_Goal_ID)
	{
		if (PA_Goal_ID < 1) 
			set_Value (COLUMNNAME_PA_Goal_ID, null);
		else 
			set_Value (COLUMNNAME_PA_Goal_ID, Integer.valueOf(PA_Goal_ID));
	}

	/** Get Goal.
		@return Performance Goal
	  */
	public int getPA_Goal_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_PA_Goal_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Parent Organization.
		@param Parent_Org_ID 
		Parent (superior) Organization 
	  */
	public void setParent_Org_ID (int Parent_Org_ID)
	{
		if (Parent_Org_ID < 1) 
			set_Value (COLUMNNAME_Parent_Org_ID, null);
		else 
			set_Value (COLUMNNAME_Parent_Org_ID, Integer.valueOf(Parent_Org_ID));
	}

	/** Get Parent Organization.
		@return Parent (superior) Organization 
	  */
	public int getParent_Org_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Parent_Org_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Main Phone.
		@param Phone 
		Identifies a telephone number
	  */
	public void setPhone (String Phone)
	{
		set_Value (COLUMNNAME_Phone, Phone);
	}

	/** Get Main Phone.
		@return Identifies a telephone number
	  */
	public String getPhone () 
	{
		return (String)get_Value(COLUMNNAME_Phone);
	}

	/** Set Receipt Footer Msg.
		@param ReceiptFooterMsg 
		This message will be displayed at the bottom of a receipt when doing a sales or purchase
	  */
	public void setReceiptFooterMsg (String ReceiptFooterMsg)
	{
		if (ReceiptFooterMsg == null)
			throw new IllegalArgumentException ("ReceiptFooterMsg is mandatory.");
		set_Value (COLUMNNAME_ReceiptFooterMsg, ReceiptFooterMsg);
	}

	/** Get Receipt Footer Msg.
		@return This message will be displayed at the bottom of a receipt when doing a sales or purchase
	  */
	public String getReceiptFooterMsg () 
	{
		return (String)get_Value(COLUMNNAME_ReceiptFooterMsg);
	}

	/** Set SSN.
		@param SSN SSN	  */
	public void setSSN (BigDecimal SSN)
	{
		set_Value (COLUMNNAME_SSN, SSN);
	}

	/** Get SSN.
		@return SSN	  */
	public BigDecimal getSSN () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_SSN);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Supervisor.
		@param Supervisor_ID 
		Supervisor for this user/organization - used for escalation and approval
	  */
	public void setSupervisor_ID (int Supervisor_ID)
	{
		if (Supervisor_ID < 1) 
			set_Value (COLUMNNAME_Supervisor_ID, null);
		else 
			set_Value (COLUMNNAME_Supervisor_ID, Integer.valueOf(Supervisor_ID));
	}

	/** Get Supervisor.
		@return Supervisor for this user/organization - used for escalation and approval
	  */
	public int getSupervisor_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Supervisor_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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

	/** Set Tax ID.
		@param TaxID 
		Tax Identification
	  */
	public void setTaxID (String TaxID)
	{
		if (TaxID == null)
			throw new IllegalArgumentException ("TaxID is mandatory.");
		set_Value (COLUMNNAME_TaxID, TaxID);
	}

	/** Get Tax ID.
		@return Tax Identification
	  */
	public String getTaxID () 
	{
		return (String)get_Value(COLUMNNAME_TaxID);
	}

	/** Set Tax Regime.
		@param TaxRegime 
		Tax Regime
	  */
	public void setTaxRegime (String TaxRegime)
	{
		set_Value (COLUMNNAME_TaxRegime, TaxRegime);
	}

	/** Get Tax Regime.
		@return Tax Regime
	  */
	public String getTaxRegime () 
	{
		return (String)get_Value(COLUMNNAME_TaxRegime);
	}

	/** Set Time Zone.
		@param TimeZone Time Zone	  */
	public void setTimeZone (String TimeZone)
	{
		set_Value (COLUMNNAME_TimeZone, TimeZone);
	}

	/** Get Time Zone.
		@return Time Zone	  */
	public String getTimeZone () 
	{
		return (String)get_Value(COLUMNNAME_TimeZone);
	}

	/** TipoCliente AD_Reference_ID=1200421 */
	public static final int TIPOCLIENTE_AD_Reference_ID=1200421;
	/** PHYSICIAN OFFICE  = P */
	public static final String TIPOCLIENTE_PHYSICIANOFFICE = "P";
	/** OUTPATIENT CARE CENTER  = O */
	public static final String TIPOCLIENTE_OUTPATIENTCARECENTER = "O";
	/** AMBULATORY SURGERY CENTER  = A */
	public static final String TIPOCLIENTE_AMBULATORYSURGERYCENTER = "A";
	/** HOSPITAL CENTER = C */
	public static final String TIPOCLIENTE_HOSPITALCENTER = "C";
	/** Set Client Type.
		@param TipoCliente Client Type	  */
	public void setTipoCliente (String TipoCliente)
	{
		if (TipoCliente == null) throw new IllegalArgumentException ("TipoCliente is mandatory");
		if (TipoCliente.equals("P") || TipoCliente.equals("O") || TipoCliente.equals("A") || TipoCliente.equals("C")); else throw new IllegalArgumentException ("TipoCliente Invalid value - " + TipoCliente + " - Reference_ID=1200421 - P - O - A - C");		set_Value (COLUMNNAME_TipoCliente, TipoCliente);
	}

	/** Get Client Type.
		@return Client Type	  */
	public String getTipoCliente () 
	{
		return (String)get_Value(COLUMNNAME_TipoCliente);
	}

	/** Tipo_Institucion AD_Reference_ID=1200124 */
	public static final int TIPO_INSTITUCION_AD_Reference_ID=1200124;
	/** Cruz Roja Mexicana = CRO */
	public static final String TIPO_INSTITUCION_CruzRojaMexicana = "CRO";
	/** Hospital Universitario = HUN */
	public static final String TIPO_INSTITUCION_HospitalUniversitario = "HUN";
	/** ISSTE = IST */
	public static final String TIPO_INSTITUCION_ISSTE = "IST";
	/** IMSS = IMS */
	public static final String TIPO_INSTITUCION_IMSS = "IMS";
	/** Instituto Mexicano del Seguro Social.Regimen Oportunidades = IMO */
	public static final String TIPO_INSTITUCION_InstitutoMexicanoDelSeguroSocialRegimenOportunidades = "IMO";
	/** PEMEX = PMX */
	public static final String TIPO_INSTITUCION_PEMEX = "PMX";
	/** Secretaria de la Defensa Nacional = SDN */
	public static final String TIPO_INSTITUCION_SecretariaDeLaDefensaNacional = "SDN";
	/** Secretaria de Marina = SMA */
	public static final String TIPO_INSTITUCION_SecretariaDeMarina = "SMA";
	/** Secretaria de Salud = SSA */
	public static final String TIPO_INSTITUCION_SecretariaDeSalud = "SSA";
	/** Servicios Medicos Estatales = SME */
	public static final String TIPO_INSTITUCION_ServiciosMedicosEstatales = "SME";
	/** Servicios Medicos Municipales = SMM */
	public static final String TIPO_INSTITUCION_ServiciosMedicosMunicipales = "SMM";
	/** Servicios Privados = SMP */
	public static final String TIPO_INSTITUCION_ServiciosPrivados = "SMP";
	/** DIF = DIF */
	public static final String TIPO_INSTITUCION_DIF = "DIF";
	/** Set Institution Type.
		@param Tipo_Institucion 
		Institution Type
	  */
	public void setTipo_Institucion (String Tipo_Institucion)
	{

		if (Tipo_Institucion == null || Tipo_Institucion.equals("CRO") || Tipo_Institucion.equals("HUN") || Tipo_Institucion.equals("IST") || Tipo_Institucion.equals("IMS") || Tipo_Institucion.equals("IMO") || Tipo_Institucion.equals("PMX") || Tipo_Institucion.equals("SDN") || Tipo_Institucion.equals("SMA") || Tipo_Institucion.equals("SSA") || Tipo_Institucion.equals("SME") || Tipo_Institucion.equals("SMM") || Tipo_Institucion.equals("SMP") || Tipo_Institucion.equals("DIF")); else throw new IllegalArgumentException ("Tipo_Institucion Invalid value - " + Tipo_Institucion + " - Reference_ID=1200124 - CRO - HUN - IST - IMS - IMO - PMX - SDN - SMA - SSA - SME - SMM - SMP - DIF");		set_Value (COLUMNNAME_Tipo_Institucion, Tipo_Institucion);
	}

	/** Get Institution Type.
		@return Institution Type
	  */
	public String getTipo_Institucion () 
	{
		return (String)get_Value(COLUMNNAME_Tipo_Institucion);
	}

	/** Tipo_Unidad AD_Reference_ID=1200125 */
	public static final int TIPO_UNIDAD_AD_Reference_ID=1200125;
	/** Establecimiento de Apoyo = EA */
	public static final String TIPO_UNIDAD_EstablecimientoDeApoyo = "EA";
	/** Establecimiento de Asistencia Social = AS */
	public static final String TIPO_UNIDAD_EstablecimientoDeAsistenciaSocial = "AS";
	/** No Disponible = ND */
	public static final String TIPO_UNIDAD_NoDisponible = "ND";
	/** Unidad de Consulta Externa = CE */
	public static final String TIPO_UNIDAD_UnidadDeConsultaExterna = "CE";
	/** Unidad de Hospitalizacion = HO */
	public static final String TIPO_UNIDAD_UnidadDeHospitalizacion = "HO";
	/** Ambulance = AB */
	public static final String TIPO_UNIDAD_Ambulance = "AB";
	/** Home Health = HH */
	public static final String TIPO_UNIDAD_HomeHealth = "HH";
	/** Set Unity Type.
		@param Tipo_Unidad 
		Unity Type
	  */
	public void setTipo_Unidad (String Tipo_Unidad)
	{

		if (Tipo_Unidad == null || Tipo_Unidad.equals("EA") || Tipo_Unidad.equals("AS") || Tipo_Unidad.equals("ND") || Tipo_Unidad.equals("CE") || Tipo_Unidad.equals("HO") || Tipo_Unidad.equals("AB") || Tipo_Unidad.equals("HH")); else throw new IllegalArgumentException ("Tipo_Unidad Invalid value - " + Tipo_Unidad + " - Reference_ID=1200125 - EA - AS - ND - CE - HO - AB - HH");		set_Value (COLUMNNAME_Tipo_Unidad, Tipo_Unidad);
	}

	/** Get Unity Type.
		@return Unity Type
	  */
	public String getTipo_Unidad () 
	{
		return (String)get_Value(COLUMNNAME_Tipo_Unidad);
	}

	/** Set Tipology.
		@param Tipologia 
		Tipology
	  */
	public void setTipologia (String Tipologia)
	{
		set_Value (COLUMNNAME_Tipologia, Tipologia);
	}

	/** Get Tipology.
		@return Tipology
	  */
	public String getTipologia () 
	{
		return (String)get_Value(COLUMNNAME_Tipologia);
	}

	/** Set Bank for transfers.
		@param TransferBank_ID 
		Bank account depending on currency will be used from this bank for doing transfers
	  */
	public void setTransferBank_ID (int TransferBank_ID)
	{
		if (TransferBank_ID < 1) 
			set_Value (COLUMNNAME_TransferBank_ID, null);
		else 
			set_Value (COLUMNNAME_TransferBank_ID, Integer.valueOf(TransferBank_ID));
	}

	/** Get Bank for transfers.
		@return Bank account depending on currency will be used from this bank for doing transfers
	  */
	public int getTransferBank_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_TransferBank_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set CashBook for transfers.
		@param TransferCashBook_ID CashBook for transfers	  */
	public void setTransferCashBook_ID (int TransferCashBook_ID)
	{
		if (TransferCashBook_ID < 1) 
			set_Value (COLUMNNAME_TransferCashBook_ID, null);
		else 
			set_Value (COLUMNNAME_TransferCashBook_ID, Integer.valueOf(TransferCashBook_ID));
	}

	/** Get CashBook for transfers.
		@return CashBook for transfers	  */
	public int getTransferCashBook_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_TransferCashBook_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}