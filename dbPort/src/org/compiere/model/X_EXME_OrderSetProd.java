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

/** Generated Model for EXME_OrderSetProd
 *  @author Generated Class 
 *  @version Version @VERSION@ - $Id$ */
public class X_EXME_OrderSetProd extends PO implements I_EXME_OrderSetProd, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_OrderSetProd (Properties ctx, int EXME_OrderSetProd_ID, String trxName)
    {
      super (ctx, EXME_OrderSetProd_ID, trxName);
      /** if (EXME_OrderSetProd_ID == 0)
        {
			setEXME_OrderSetProd_ID (0);
			setEXME_OrderSet_ID (0);
        } */
    }

    /** Load Constructor */
    public X_EXME_OrderSetProd (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_OrderSetProd[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Number of days.
		@param Days Number of days	  */
	public void setDays (int Days)
	{
		set_Value (COLUMNNAME_Days, Integer.valueOf(Days));
	}

	/** Get Number of days.
		@return Number of days	  */
	public int getDays () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Days);
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

	/** Dose AD_Reference_ID=1200501 */
	public static final int DOSE_AD_Reference_ID=1200501;
	/** Auto = 1 */
	public static final String DOSE_Auto = "1";
	/** 2 Doses = 2 */
	public static final String DOSE_2Doses = "2";
	/** 3 Doses = 3 */
	public static final String DOSE_3Doses = "3";
	/** 4 Doses = 4 */
	public static final String DOSE_4Doses = "4";
	/** 5 Doses = 5 */
	public static final String DOSE_5Doses = "5";
	/** 6 Doses = 6 */
	public static final String DOSE_6Doses = "6";
	/** 7 Doses = 7 */
	public static final String DOSE_7Doses = "7";
	/** 8 Doses = 8 */
	public static final String DOSE_8Doses = "8";
	/** Set Medication Doses.
		@param Dose 
		Medication Doses
	  */
	public void setDose (String Dose)
	{

		if (Dose == null || Dose.equals("1") || Dose.equals("2") || Dose.equals("3") || Dose.equals("4") || Dose.equals("5") || Dose.equals("6") || Dose.equals("7") || Dose.equals("8")); else throw new IllegalArgumentException ("Dose Invalid value - " + Dose + " - Reference_ID=1200501 - 1 - 2 - 3 - 4 - 5 - 6 - 7 - 8");		set_Value (COLUMNNAME_Dose, Dose);
	}

	/** Get Medication Doses.
		@return Medication Doses
	  */
	public String getDose () 
	{
		return (String)get_Value(COLUMNNAME_Dose);
	}

	/** Set Dose Maximum.
		@param DoseMax Dose Maximum	  */
	public void setDoseMax (int DoseMax)
	{
		set_Value (COLUMNNAME_DoseMax, Integer.valueOf(DoseMax));
	}

	/** Get Dose Maximum.
		@return Dose Maximum	  */
	public int getDoseMax () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DoseMax);
		if (ii == null)
			 return 0;
		return ii.intValue();
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

	public I_EXME_Frequency1 getEXME_Frequency1() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_Frequency1.Table_Name);
        I_EXME_Frequency1 result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_Frequency1)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Frequency1_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Frequency 1.
		@param EXME_Frequency1_ID 
		Frequency Header ID
	  */
	public void setEXME_Frequency1_ID (int EXME_Frequency1_ID)
	{
		if (EXME_Frequency1_ID < 1) 
			set_Value (COLUMNNAME_EXME_Frequency1_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_Frequency1_ID, Integer.valueOf(EXME_Frequency1_ID));
	}

	/** Get Frequency 1.
		@return Frequency Header ID
	  */
	public int getEXME_Frequency1_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Frequency1_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_Frequency2 getEXME_Frequency2() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_Frequency2.Table_Name);
        I_EXME_Frequency2 result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_Frequency2)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Frequency2_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Frequency 2.
		@param EXME_Frequency2_ID 
		Frequency First Detail ID
	  */
	public void setEXME_Frequency2_ID (int EXME_Frequency2_ID)
	{
		if (EXME_Frequency2_ID < 1) 
			set_Value (COLUMNNAME_EXME_Frequency2_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_Frequency2_ID, Integer.valueOf(EXME_Frequency2_ID));
	}

	/** Get Frequency 2.
		@return Frequency First Detail ID
	  */
	public int getEXME_Frequency2_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Frequency2_ID);
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

	/** Set Order Set Products.
		@param EXME_OrderSetProd_ID Order Set Products	  */
	public void setEXME_OrderSetProd_ID (int EXME_OrderSetProd_ID)
	{
		if (EXME_OrderSetProd_ID < 1)
			 throw new IllegalArgumentException ("EXME_OrderSetProd_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_OrderSetProd_ID, Integer.valueOf(EXME_OrderSetProd_ID));
	}

	/** Get Order Set Products.
		@return Order Set Products	  */
	public int getEXME_OrderSetProd_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_OrderSetProd_ID);
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
			 throw new IllegalArgumentException ("EXME_OrderSet_ID is mandatory.");
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

	public I_EXME_Route getEXME_Route() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_Route.Table_Name);
        I_EXME_Route result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_Route)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Route_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Route.
		@param EXME_Route_ID 
		Route
	  */
	public void setEXME_Route_ID (int EXME_Route_ID)
	{
		if (EXME_Route_ID < 1) 
			set_Value (COLUMNNAME_EXME_Route_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_Route_ID, Integer.valueOf(EXME_Route_ID));
	}

	/** Get Route.
		@return Route
	  */
	public int getEXME_Route_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Route_ID);
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

	/** Set Year Maximum.
		@param EdadFinal Year Maximum	  */
	public void setEdadFinal (int EdadFinal)
	{
		set_Value (COLUMNNAME_EdadFinal, Integer.valueOf(EdadFinal));
	}

	/** Get Year Maximum.
		@return Year Maximum	  */
	public int getEdadFinal () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EdadFinal);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Year Minimum.
		@param EdadInicial Year Minimum	  */
	public void setEdadInicial (int EdadInicial)
	{
		set_Value (COLUMNNAME_EdadInicial, Integer.valueOf(EdadInicial));
	}

	/** Get Year Minimum.
		@return Year Minimum	  */
	public int getEdadInicial () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EdadInicial);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set ISWEIGHTRANK.
		@param ISWEIGHTRANK ISWEIGHTRANK	  */
	public void setISWEIGHTRANK (boolean ISWEIGHTRANK)
	{
		set_Value (COLUMNNAME_ISWEIGHTRANK, Boolean.valueOf(ISWEIGHTRANK));
	}

	/** Get ISWEIGHTRANK.
		@return ISWEIGHTRANK	  */
	public boolean isWEIGHTRANK () 
	{
		Object oo = get_Value(COLUMNNAME_ISWEIGHTRANK);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set IsEstudio.
		@param IsEstudio IsEstudio	  */
	public void setIsEstudio (boolean IsEstudio)
	{
		set_Value (COLUMNNAME_IsEstudio, Boolean.valueOf(IsEstudio));
	}

	/** Get IsEstudio.
		@return IsEstudio	  */
	public boolean isEstudio () 
	{
		Object oo = get_Value(COLUMNNAME_IsEstudio);
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

	/** Set Month Maximum.
		@param MesFinal Month Maximum	  */
	public void setMesFinal (int MesFinal)
	{
		set_Value (COLUMNNAME_MesFinal, Integer.valueOf(MesFinal));
	}

	/** Get Month Maximum.
		@return Month Maximum	  */
	public int getMesFinal () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_MesFinal);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Month Minimum.
		@param MesInicial Month Minimum	  */
	public void setMesInicial (int MesInicial)
	{
		set_Value (COLUMNNAME_MesInicial, Integer.valueOf(MesInicial));
	}

	/** Get Month Minimum.
		@return Month Minimum	  */
	public int getMesInicial () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_MesInicial);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** ProductType AD_Reference_ID=1200536 */
	public static final int PRODUCTTYPE_AD_Reference_ID=1200536;
	/** Service = SE */
	public static final String PRODUCTTYPE_Service = "SE";
	/** Procedure = PD */
	public static final String PRODUCTTYPE_Procedure = "PD";
	/** Studies = ST */
	public static final String PRODUCTTYPE_Studies = "ST";
	/** Medication = MD */
	public static final String PRODUCTTYPE_Medication = "MD";
	/** Set Product Type.
		@param ProductType 
		Type of product
	  */
	public void setProductType (String ProductType)
	{

		if (ProductType == null || ProductType.equals("SE") || ProductType.equals("PD") || ProductType.equals("ST") || ProductType.equals("MD")); else throw new IllegalArgumentException ("ProductType Invalid value - " + ProductType + " - Reference_ID=1200536 - SE - PD - ST - MD");		set_Value (COLUMNNAME_ProductType, ProductType);
	}

	/** Get Product Type.
		@return Type of product
	  */
	public String getProductType () 
	{
		return (String)get_Value(COLUMNNAME_ProductType);
	}

	/** Set Quantity Plan.
		@param Qty 
		Planned Quantity
	  */
	public void setQty (BigDecimal Qty)
	{
		set_Value (COLUMNNAME_Qty, Qty);
	}

	/** Get Quantity Plan.
		@return Planned Quantity
	  */
	public BigDecimal getQty () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Qty);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Quantity.
		@param Quantity Quantity	  */
	public void setQuantity (BigDecimal Quantity)
	{
		set_Value (COLUMNNAME_Quantity, Quantity);
	}

	/** Get Quantity.
		@return Quantity	  */
	public BigDecimal getQuantity () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Quantity);
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

	/** Set Refill.
		@param Refill Refill	  */
	public void setRefill (int Refill)
	{
		set_Value (COLUMNNAME_Refill, Integer.valueOf(Refill));
	}

	/** Get Refill.
		@return Refill	  */
	public int getRefill () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Refill);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** UnitMeasure AD_Reference_ID=1200553 */
	public static final int UNITMEASURE_AD_Reference_ID=1200553;
	/** P = P */
	public static final String UNITMEASURE_P = "P";
	/** K = K */
	public static final String UNITMEASURE_K = "K";
	/** Set Unit Measure.
		@param UnitMeasure Unit Measure	  */
	public void setUnitMeasure (String UnitMeasure)
	{

		if (UnitMeasure == null || UnitMeasure.equals("P") || UnitMeasure.equals("K")); else throw new IllegalArgumentException ("UnitMeasure Invalid value - " + UnitMeasure + " - Reference_ID=1200553 - P - K");		set_Value (COLUMNNAME_UnitMeasure, UnitMeasure);
	}

	/** Get Unit Measure.
		@return Unit Measure	  */
	public String getUnitMeasure () 
	{
		return (String)get_Value(COLUMNNAME_UnitMeasure);
	}

	/** Set Weight Max.
		@param WeightMax Weight Max	  */
	public void setWeightMax (BigDecimal WeightMax)
	{
		set_Value (COLUMNNAME_WeightMax, WeightMax);
	}

	/** Get Weight Max.
		@return Weight Max	  */
	public BigDecimal getWeightMax () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_WeightMax);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Weight Min.
		@param WeightMin Weight Min	  */
	public void setWeightMin (BigDecimal WeightMin)
	{
		set_Value (COLUMNNAME_WeightMin, WeightMin);
	}

	/** Get Weight Min.
		@return Weight Min	  */
	public BigDecimal getWeightMin () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_WeightMin);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}
}