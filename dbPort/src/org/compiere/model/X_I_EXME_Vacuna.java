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

/** Generated Model for I_EXME_Vacuna
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_I_EXME_Vacuna extends PO implements I_I_EXME_Vacuna, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_I_EXME_Vacuna (Properties ctx, int I_EXME_Vacuna_ID, String trxName)
    {
      super (ctx, I_EXME_Vacuna_ID, trxName);
      /** if (I_EXME_Vacuna_ID == 0)
        {
			setEXME_VacunaDet_ID (0);
			setEXME_Vacuna_ID (0);
			setI_EXME_Vacuna_ID (0);
			setProcessing (false);
        } */
    }

    /** Load Constructor */
    public X_I_EXME_Vacuna (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_I_EXME_Vacuna[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Quantity.
		@param Cantidad 
		Quantity
	  */
	public void setCantidad (BigDecimal Cantidad)
	{
		set_Value (COLUMNNAME_Cantidad, Cantidad);
	}

	/** Get Quantity.
		@return Quantity
	  */
	public BigDecimal getCantidad () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Cantidad);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set UOM Det.
		@param C_UOM_Det_ID UOM Det	  */
	public void setC_UOM_Det_ID (int C_UOM_Det_ID)
	{
		if (C_UOM_Det_ID < 1) 
			set_Value (COLUMNNAME_C_UOM_Det_ID, null);
		else 
			set_Value (COLUMNNAME_C_UOM_Det_ID, Integer.valueOf(C_UOM_Det_ID));
	}

	/** Get UOM Det.
		@return UOM Det	  */
	public int getC_UOM_Det_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_UOM_Det_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Value UOM.
		@param C_UOM_Det_Value Value UOM	  */
	public void setC_UOM_Det_Value (String C_UOM_Det_Value)
	{
		set_Value (COLUMNNAME_C_UOM_Det_Value, C_UOM_Det_Value);
	}

	/** Get Value UOM.
		@return Value UOM	  */
	public String getC_UOM_Det_Value () 
	{
		return (String)get_Value(COLUMNNAME_C_UOM_Det_Value);
	}

	/** Set UOM Vaccine.
		@param C_UOM_Hrd_ID UOM Vaccine	  */
	public void setC_UOM_Hrd_ID (int C_UOM_Hrd_ID)
	{
		if (C_UOM_Hrd_ID < 1) 
			set_Value (COLUMNNAME_C_UOM_Hrd_ID, null);
		else 
			set_Value (COLUMNNAME_C_UOM_Hrd_ID, Integer.valueOf(C_UOM_Hrd_ID));
	}

	/** Get UOM Vaccine.
		@return UOM Vaccine	  */
	public int getC_UOM_Hrd_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_UOM_Hrd_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Value UOM Vaccine.
		@param C_UOM_Hrd_Value Value UOM Vaccine	  */
	public void setC_UOM_Hrd_Value (String C_UOM_Hrd_Value)
	{
		set_Value (COLUMNNAME_C_UOM_Hrd_Value, C_UOM_Hrd_Value);
	}

	/** Get Value UOM Vaccine.
		@return Value UOM Vaccine	  */
	public String getC_UOM_Hrd_Value () 
	{
		return (String)get_Value(COLUMNNAME_C_UOM_Hrd_Value);
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

	/** Set Maximum age.
		@param EdadMaxima Maximum age	  */
	public void setEdadMaxima (BigDecimal EdadMaxima)
	{
		set_Value (COLUMNNAME_EdadMaxima, EdadMaxima);
	}

	/** Get Maximum age.
		@return Maximum age	  */
	public BigDecimal getEdadMaxima () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_EdadMaxima);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Minimum age.
		@param EdadMinima Minimum age	  */
	public void setEdadMinima (BigDecimal EdadMinima)
	{
		set_Value (COLUMNNAME_EdadMinima, EdadMinima);
	}

	/** Get Minimum age.
		@return Minimum age	  */
	public BigDecimal getEdadMinima () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_EdadMinima);
		if (bd == null)
			 return Env.ZERO;
		return bd;
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

	/** Set Diagnostic.
		@param EXME_Diagnostico_Value 
		Diagnostic
	  */
	public void setEXME_Diagnostico_Value (String EXME_Diagnostico_Value)
	{
		set_Value (COLUMNNAME_EXME_Diagnostico_Value, EXME_Diagnostico_Value);
	}

	/** Get Diagnostic.
		@return Diagnostic
	  */
	public String getEXME_Diagnostico_Value () 
	{
		return (String)get_Value(COLUMNNAME_EXME_Diagnostico_Value);
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

	/** Set Value Diagnosis 2.
		@param EXME_Diagnostico2_Value Value Diagnosis 2	  */
	public void setEXME_Diagnostico2_Value (String EXME_Diagnostico2_Value)
	{
		set_Value (COLUMNNAME_EXME_Diagnostico2_Value, EXME_Diagnostico2_Value);
	}

	/** Get Value Diagnosis 2.
		@return Value Diagnosis 2	  */
	public String getEXME_Diagnostico2_Value () 
	{
		return (String)get_Value(COLUMNNAME_EXME_Diagnostico2_Value);
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

	/** Set Value Diagnosis 3.
		@param EXME_Diagnostico3_Value Value Diagnosis 3	  */
	public void setEXME_Diagnostico3_Value (String EXME_Diagnostico3_Value)
	{
		set_Value (COLUMNNAME_EXME_Diagnostico3_Value, EXME_Diagnostico3_Value);
	}

	/** Get Value Diagnosis 3.
		@return Value Diagnosis 3	  */
	public String getEXME_Diagnostico3_Value () 
	{
		return (String)get_Value(COLUMNNAME_EXME_Diagnostico3_Value);
	}

	/** Set Fourth Diagnosis.
		@param EXME_Diagnostico4_ID Fourth Diagnosis	  */
	public void setEXME_Diagnostico4_ID (int EXME_Diagnostico4_ID)
	{
		if (EXME_Diagnostico4_ID < 1) 
			set_Value (COLUMNNAME_EXME_Diagnostico4_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_Diagnostico4_ID, Integer.valueOf(EXME_Diagnostico4_ID));
	}

	/** Get Fourth Diagnosis.
		@return Fourth Diagnosis	  */
	public int getEXME_Diagnostico4_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Diagnostico4_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Value Diagnosis 4.
		@param EXME_Diagnostico4_Value Value Diagnosis 4	  */
	public void setEXME_Diagnostico4_Value (String EXME_Diagnostico4_Value)
	{
		set_Value (COLUMNNAME_EXME_Diagnostico4_Value, EXME_Diagnostico4_Value);
	}

	/** Get Value Diagnosis 4.
		@return Value Diagnosis 4	  */
	public String getEXME_Diagnostico4_Value () 
	{
		return (String)get_Value(COLUMNNAME_EXME_Diagnostico4_Value);
	}

	/** Set Fifth Diagnosis.
		@param EXME_Diagnostico5_ID Fifth Diagnosis	  */
	public void setEXME_Diagnostico5_ID (int EXME_Diagnostico5_ID)
	{
		if (EXME_Diagnostico5_ID < 1) 
			set_Value (COLUMNNAME_EXME_Diagnostico5_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_Diagnostico5_ID, Integer.valueOf(EXME_Diagnostico5_ID));
	}

	/** Get Fifth Diagnosis.
		@return Fifth Diagnosis	  */
	public int getEXME_Diagnostico5_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Diagnostico5_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Value Diagnosis 5.
		@param EXME_Diagnostico5_Value Value Diagnosis 5	  */
	public void setEXME_Diagnostico5_Value (String EXME_Diagnostico5_Value)
	{
		set_Value (COLUMNNAME_EXME_Diagnostico5_Value, EXME_Diagnostico5_Value);
	}

	/** Get Value Diagnosis 5.
		@return Value Diagnosis 5	  */
	public String getEXME_Diagnostico5_Value () 
	{
		return (String)get_Value(COLUMNNAME_EXME_Diagnostico5_Value);
	}

	public I_EXME_VacunaDet getEXME_VacunaDet() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_VacunaDet.Table_Name);
        I_EXME_VacunaDet result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_VacunaDet)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_VacunaDet_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Detail immunization.
		@param EXME_VacunaDet_ID 
		Detail immunization
	  */
	public void setEXME_VacunaDet_ID (int EXME_VacunaDet_ID)
	{
		if (EXME_VacunaDet_ID < 1)
			 throw new IllegalArgumentException ("EXME_VacunaDet_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_VacunaDet_ID, Integer.valueOf(EXME_VacunaDet_ID));
	}

	/** Get Detail immunization.
		@return Detail immunization
	  */
	public int getEXME_VacunaDet_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_VacunaDet_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_Vacuna getEXME_Vacuna() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_Vacuna.Table_Name);
        I_EXME_Vacuna result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_Vacuna)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Vacuna_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Vaccine.
		@param EXME_Vacuna_ID 
		Vaccine
	  */
	public void setEXME_Vacuna_ID (int EXME_Vacuna_ID)
	{
		if (EXME_Vacuna_ID < 1)
			 throw new IllegalArgumentException ("EXME_Vacuna_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_Vacuna_ID, Integer.valueOf(EXME_Vacuna_ID));
	}

	/** Get Vaccine.
		@return Vaccine
	  */
	public int getEXME_Vacuna_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Vacuna_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Import Error Message.
		@param I_ErrorMsg 
		Messages generated from import process
	  */
	public void setI_ErrorMsg (String I_ErrorMsg)
	{
		set_Value (COLUMNNAME_I_ErrorMsg, I_ErrorMsg);
	}

	/** Get Import Error Message.
		@return Messages generated from import process
	  */
	public String getI_ErrorMsg () 
	{
		return (String)get_Value(COLUMNNAME_I_ErrorMsg);
	}

	/** Set Import Vaccine.
		@param I_EXME_Vacuna_ID Import Vaccine	  */
	public void setI_EXME_Vacuna_ID (int I_EXME_Vacuna_ID)
	{
		if (I_EXME_Vacuna_ID < 1)
			 throw new IllegalArgumentException ("I_EXME_Vacuna_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_I_EXME_Vacuna_ID, Integer.valueOf(I_EXME_Vacuna_ID));
	}

	/** Get Import Vaccine.
		@return Import Vaccine	  */
	public int getI_EXME_Vacuna_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_I_EXME_Vacuna_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Imported.
		@param I_IsImported 
		Has this import been processed
	  */
	public void setI_IsImported (boolean I_IsImported)
	{
		set_Value (COLUMNNAME_I_IsImported, Boolean.valueOf(I_IsImported));
	}

	/** Get Imported.
		@return Has this import been processed
	  */
	public boolean isI_IsImported () 
	{
		Object oo = get_Value(COLUMNNAME_I_IsImported);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Included in Immunization Cards.
		@param IncluyeCartilla Included in Immunization Cards	  */
	public void setIncluyeCartilla (boolean IncluyeCartilla)
	{
		set_Value (COLUMNNAME_IncluyeCartilla, Boolean.valueOf(IncluyeCartilla));
	}

	/** Get Included in Immunization Cards.
		@return Included in Immunization Cards	  */
	public boolean isIncluyeCartilla () 
	{
		Object oo = get_Value(COLUMNNAME_IncluyeCartilla);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Interval.
		@param Intervalo 
		Interval
	  */
	public void setIntervalo (BigDecimal Intervalo)
	{
		set_Value (COLUMNNAME_Intervalo, Intervalo);
	}

	/** Get Interval.
		@return Interval
	  */
	public BigDecimal getIntervalo () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Intervalo);
		if (bd == null)
			 return Env.ZERO;
		return bd;
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

	/** Set Product Code.
		@param M_Product_Value 
		product search Code
	  */
	public void setM_Product_Value (String M_Product_Value)
	{
		set_Value (COLUMNNAME_M_Product_Value, M_Product_Value);
	}

	/** Get Product Code.
		@return product search Code
	  */
	public String getM_Product_Value () 
	{
		return (String)get_Value(COLUMNNAME_M_Product_Value);
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

	/** Set Related Vaccine.
		@param Rel_Vacuna_ID Related Vaccine	  */
	public void setRel_Vacuna_ID (int Rel_Vacuna_ID)
	{
		if (Rel_Vacuna_ID < 1) 
			set_Value (COLUMNNAME_Rel_Vacuna_ID, null);
		else 
			set_Value (COLUMNNAME_Rel_Vacuna_ID, Integer.valueOf(Rel_Vacuna_ID));
	}

	/** Get Related Vaccine.
		@return Related Vaccine	  */
	public int getRel_Vacuna_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Rel_Vacuna_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Code of vaccine related .
		@param Rel_Vacuna_Value Code of vaccine related 	  */
	public void setRel_Vacuna_Value (String Rel_Vacuna_Value)
	{
		set_Value (COLUMNNAME_Rel_Vacuna_Value, Rel_Vacuna_Value);
	}

	/** Get Code of vaccine related .
		@return Code of vaccine related 	  */
	public String getRel_Vacuna_Value () 
	{
		return (String)get_Value(COLUMNNAME_Rel_Vacuna_Value);
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

	/** Sexo AD_Reference_ID=1000018 */
	public static final int SEXO_AD_Reference_ID=1000018;
	/** Female = F */
	public static final String SEXO_Female = "F";
	/** Male = M */
	public static final String SEXO_Male = "M";
	/** Unassigned = U */
	public static final String SEXO_Unassigned = "U";
	/** Ambiguous = A */
	public static final String SEXO_Ambiguous = "A";
	/** Not Applicable = N */
	public static final String SEXO_NotApplicable = "N";
	/** Other = O */
	public static final String SEXO_Other = "O";
	/** Set Sex.
		@param Sexo 
		Sex
	  */
	public void setSexo (String Sexo)
	{

		if (Sexo == null || Sexo.equals("F") || Sexo.equals("M") || Sexo.equals("U") || Sexo.equals("A") || Sexo.equals("N") || Sexo.equals("O")); else throw new IllegalArgumentException ("Sexo Invalid value - " + Sexo + " - Reference_ID=1000018 - F - M - U - A - N - O");		set_Value (COLUMNNAME_Sexo, Sexo);
	}

	/** Get Sex.
		@return Sex
	  */
	public String getSexo () 
	{
		return (String)get_Value(COLUMNNAME_Sexo);
	}

	/** TipoDosis AD_Reference_ID=1200129 */
	public static final int TIPODOSIS_AD_Reference_ID=1200129;
	/** Only = UNI */
	public static final String TIPODOSIS_Only = "UNI";
	/** Preliminary = PRE */
	public static final String TIPODOSIS_Preliminary = "PRE";
	/** First = PRI */
	public static final String TIPODOSIS_First = "PRI";
	/** Second = SEG */
	public static final String TIPODOSIS_Second = "SEG";
	/** Third = TER */
	public static final String TIPODOSIS_Third = "TER";
	/** Additional = ADI */
	public static final String TIPODOSIS_Additional = "ADI";
	/** Reinforcement 1 = RE1 */
	public static final String TIPODOSIS_Reinforcement1 = "RE1";
	/** Reinforcement 2 = RE2 */
	public static final String TIPODOSIS_Reinforcement2 = "RE2";
	/** Reinforcement = RE */
	public static final String TIPODOSIS_Reinforcement = "RE";
	/** Fourth = FOU */
	public static final String TIPODOSIS_Fourth = "FOU";
	/** Fifth = FIF */
	public static final String TIPODOSIS_Fifth = "FIF";
	/** Set Dose rate.
		@param TipoDosis Dose rate	  */
	public void setTipoDosis (String TipoDosis)
	{

		if (TipoDosis == null || TipoDosis.equals("UNI") || TipoDosis.equals("PRE") || TipoDosis.equals("PRI") || TipoDosis.equals("SEG") || TipoDosis.equals("TER") || TipoDosis.equals("ADI") || TipoDosis.equals("RE1") || TipoDosis.equals("RE2") || TipoDosis.equals("RE") || TipoDosis.equals("FOU") || TipoDosis.equals("FIF")); else throw new IllegalArgumentException ("TipoDosis Invalid value - " + TipoDosis + " - Reference_ID=1200129 - UNI - PRE - PRI - SEG - TER - ADI - RE1 - RE2 - RE - FOU - FIF");		set_Value (COLUMNNAME_TipoDosis, TipoDosis);
	}

	/** Get Dose rate.
		@return Dose rate	  */
	public String getTipoDosis () 
	{
		return (String)get_Value(COLUMNNAME_TipoDosis);
	}

	/** Set Search Key.
		@param Value 
		Search key for the record in the format required - must be unique
	  */
	public void setValue (String Value)
	{
		set_Value (COLUMNNAME_Value, Value);
	}

	/** Get Search Key.
		@return Search key for the record in the format required - must be unique
	  */
	public String getValue () 
	{
		return (String)get_Value(COLUMNNAME_Value);
	}

	/** Set Route of Administration.
		@param Via 
		Route of Administration
	  */
	public void setVia (String Via)
	{
		set_Value (COLUMNNAME_Via, Via);
	}

	/** Get Route of Administration.
		@return Route of Administration
	  */
	public String getVia () 
	{
		return (String)get_Value(COLUMNNAME_Via);
	}
}