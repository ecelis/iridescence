/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;
import org.compiere.util.KeyNamePair;

/** Generated Model for EXME_ConfigEsterilizacion
 *  @author Generated Class 
 *  @version Version @VERSION@ - $Id$ */
public class X_EXME_ConfigEsterilizacion extends PO implements I_EXME_ConfigEsterilizacion, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_ConfigEsterilizacion (Properties ctx, int EXME_ConfigEsterilizacion_ID, String trxName)
    {
      super (ctx, EXME_ConfigEsterilizacion_ID, trxName);
      /** if (EXME_ConfigEsterilizacion_ID == 0)
        {
			setC_UOM_ID (0);
			setEXME_ConfigEsterilizacion_ID (0);
			setEXME_TipoProd_ID (0);
			setM_Product_Category_ID (0);
			setName (null);
			setProcedureType (null);
			setProductClass (null);
			setValue (null);
        } */
    }

    /** Load Constructor */
    public X_EXME_ConfigEsterilizacion (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_ConfigEsterilizacion[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public I_C_UOM getC_UOM() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_C_UOM.Table_Name);
        I_C_UOM result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_C_UOM)constructor.newInstance(new Object[] {getCtx(), new Integer(getC_UOM_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set UOM.
		@param C_UOM_ID 
		Unit of Measure
	  */
	public void setC_UOM_ID (int C_UOM_ID)
	{
		if (C_UOM_ID < 1)
			 throw new IllegalArgumentException ("C_UOM_ID is mandatory.");
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

	/** Set EXME_ConfigEsterilizacion_ID.
		@param EXME_ConfigEsterilizacion_ID EXME_ConfigEsterilizacion_ID	  */
	public void setEXME_ConfigEsterilizacion_ID (int EXME_ConfigEsterilizacion_ID)
	{
		if (EXME_ConfigEsterilizacion_ID < 1)
			 throw new IllegalArgumentException ("EXME_ConfigEsterilizacion_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_ConfigEsterilizacion_ID, Integer.valueOf(EXME_ConfigEsterilizacion_ID));
	}

	/** Get EXME_ConfigEsterilizacion_ID.
		@return EXME_ConfigEsterilizacion_ID	  */
	public int getEXME_ConfigEsterilizacion_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_ConfigEsterilizacion_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_TipoProd getEXME_TipoProd() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_TipoProd.Table_Name);
        I_EXME_TipoProd result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_TipoProd)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_TipoProd_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Product Subtype.
		@param EXME_TipoProd_ID 
		Product Subtype for hospitals
	  */
	public void setEXME_TipoProd_ID (int EXME_TipoProd_ID)
	{
		if (EXME_TipoProd_ID < 1)
			 throw new IllegalArgumentException ("EXME_TipoProd_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_TipoProd_ID, Integer.valueOf(EXME_TipoProd_ID));
	}

	/** Get Product Subtype.
		@return Product Subtype for hospitals
	  */
	public int getEXME_TipoProd_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_TipoProd_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_M_Product_Category getM_Product_Category() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_M_Product_Category.Table_Name);
        I_M_Product_Category result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_M_Product_Category)constructor.newInstance(new Object[] {getCtx(), new Integer(getM_Product_Category_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Product Category.
		@param M_Product_Category_ID 
		Category of a Product
	  */
	public void setM_Product_Category_ID (int M_Product_Category_ID)
	{
		if (M_Product_Category_ID < 1)
			 throw new IllegalArgumentException ("M_Product_Category_ID is mandatory.");
		set_Value (COLUMNNAME_M_Product_Category_ID, Integer.valueOf(M_Product_Category_ID));
	}

	/** Get Product Category.
		@return Category of a Product
	  */
	public int getM_Product_Category_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_Product_Category_ID);
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

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), getName());
    }

	/** ProcedureType AD_Reference_ID=1200511 */
	public static final int PROCEDURETYPE_AD_Reference_ID=1200511;
	/** Procedure = PR */
	public static final String PROCEDURETYPE_Procedure = "PR";
	/** Studie = ST */
	public static final String PROCEDURETYPE_Studie = "ST";
	/** Item = IT */
	public static final String PROCEDURETYPE_Item = "IT";
	/** Other = OT */
	public static final String PROCEDURETYPE_Other = "OT";
	/** Set Procedure Type.
		@param ProcedureType 
		Procedure Type
	  */
	public void setProcedureType (String ProcedureType)
	{
		if (ProcedureType == null) throw new IllegalArgumentException ("ProcedureType is mandatory");
		if (ProcedureType.equals("PR") || ProcedureType.equals("ST") || ProcedureType.equals("IT") || ProcedureType.equals("OT")); else throw new IllegalArgumentException ("ProcedureType Invalid value - " + ProcedureType + " - Reference_ID=1200511 - PR - ST - IT - OT");		set_Value (COLUMNNAME_ProcedureType, ProcedureType);
	}

	/** Get Procedure Type.
		@return Procedure Type
	  */
	public String getProcedureType () 
	{
		return (String)get_Value(COLUMNNAME_ProcedureType);
	}

	/** ProductClass AD_Reference_ID=1200509 */
	public static final int PRODUCTCLASS_AD_Reference_ID=1200509;
	/** Laboratory = LA */
	public static final String PRODUCTCLASS_Laboratory = "LA";
	/** X Ray = XR */
	public static final String PRODUCTCLASS_XRay = "XR";
	/** Drug = DG */
	public static final String PRODUCTCLASS_Drug = "DG";
	/** Procedures = PR */
	public static final String PRODUCTCLASS_Procedures = "PR";
	/** Immunization = IM */
	public static final String PRODUCTCLASS_Immunization = "IM";
	/** Cultures = CL */
	public static final String PRODUCTCLASS_Cultures = "CL";
	/** Blood = BL */
	public static final String PRODUCTCLASS_Blood = "BL";
	/** Surgeries = SG */
	public static final String PRODUCTCLASS_Surgeries = "SG";
	/** Anesthesic = AN */
	public static final String PRODUCTCLASS_Anesthesic = "AN";
	/** Medical Supplies = MS */
	public static final String PRODUCTCLASS_MedicalSupplies = "MS";
	/** Treatment = TR */
	public static final String PRODUCTCLASS_Treatment = "TR";
	/** Office Visit = OV */
	public static final String PRODUCTCLASS_OfficeVisit = "OV";
	/** Other Service = OS */
	public static final String PRODUCTCLASS_OtherService = "OS";
	/** Ambulance = AB */
	public static final String PRODUCTCLASS_Ambulance = "AB";
	/** Home Healt = HH */
	public static final String PRODUCTCLASS_HomeHealt = "HH";
	/** Hospice = HP */
	public static final String PRODUCTCLASS_Hospice = "HP";
	/** FQHC/RHC = FR */
	public static final String PRODUCTCLASS_FQHCRHC = "FR";
	/** Physician Services = PS */
	public static final String PRODUCTCLASS_PhysicianServices = "PS";
	/** Rooms = RM */
	public static final String PRODUCTCLASS_Rooms = "RM";
	/** Others = TH */
	public static final String PRODUCTCLASS_Others = "TH";
	/** MR = MR */
	public static final String PRODUCTCLASS_MR = "MR";
	/** CT = CT */
	public static final String PRODUCTCLASS_CT = "CT";
	/** NM = NM */
	public static final String PRODUCTCLASS_NM = "NM";
	/** PT = PT */
	public static final String PRODUCTCLASS_PT = "PT";
	/** INSTRUMENTAL = IN */
	public static final String PRODUCTCLASS_INSTRUMENTAL = "IN";
	/** Set Product Class.
		@param ProductClass Product Class	  */
	public void setProductClass (String ProductClass)
	{
		if (ProductClass == null) throw new IllegalArgumentException ("ProductClass is mandatory");
		if (ProductClass.equals("LA") || ProductClass.equals("XR") || ProductClass.equals("DG") || ProductClass.equals("PR") || ProductClass.equals("IM") || ProductClass.equals("CL") || ProductClass.equals("BL") || ProductClass.equals("SG") || ProductClass.equals("AN") || ProductClass.equals("MS") || ProductClass.equals("TR") || ProductClass.equals("OV") || ProductClass.equals("OS") || ProductClass.equals("AB") || ProductClass.equals("HH") || ProductClass.equals("HP") || ProductClass.equals("FR") || ProductClass.equals("PS") || ProductClass.equals("RM") || ProductClass.equals("TH") || ProductClass.equals("MR") || ProductClass.equals("CT") || ProductClass.equals("NM") || ProductClass.equals("PT") || ProductClass.equals("IN")); else throw new IllegalArgumentException ("ProductClass Invalid value - " + ProductClass + " - Reference_ID=1200509 - LA - XR - DG - PR - IM - CL - BL - SG - AN - MS - TR - OV - OS - AB - HH - HP - FR - PS - RM - TH - MR - CT - NM - PT - IN");		set_Value (COLUMNNAME_ProductClass, ProductClass);
	}

	/** Get Product Class.
		@return Product Class	  */
	public String getProductClass () 
	{
		return (String)get_Value(COLUMNNAME_ProductClass);
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