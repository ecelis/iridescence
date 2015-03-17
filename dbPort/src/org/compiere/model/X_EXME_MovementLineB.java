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

/** Generated Model for EXME_MovementLineB
 *  @author Generated Class 
 *  @version Release 1.1.2 - $Id$ */
public class X_EXME_MovementLineB extends PO implements I_EXME_MovementLineB, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_MovementLineB (Properties ctx, int EXME_MovementLineB_ID, String trxName)
    {
      super (ctx, EXME_MovementLineB_ID, trxName);
      /** if (EXME_MovementLineB_ID == 0)
        {
			setC_UOM_Pla_ID (0);
			setEXME_Diagnostico_ID (0);
			setEXME_MovementLineB_ID (0);
			setHemoglobina (Env.ZERO);
			setIndicaciones (null);
			setPlaquetas (Env.ZERO);
			setPriority (null);
			setTipoSangre (null);
			setTP (Env.ZERO);
			setTTPA (Env.ZERO);
			setValue (null);
        } */
    }

    /** Load Constructor */
    public X_EXME_MovementLineB (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_MovementLineB[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set History of Pregnancy.
		@param AntEmbarazo History of Pregnancy	  */
	public void setAntEmbarazo (boolean AntEmbarazo)
	{
		set_Value (COLUMNNAME_AntEmbarazo, Boolean.valueOf(AntEmbarazo));
	}

	/** Get History of Pregnancy.
		@return History of Pregnancy	  */
	public boolean isAntEmbarazo () 
	{
		Object oo = get_Value(COLUMNNAME_AntEmbarazo);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set AntIsoinmuMater.
		@param AntIsoinmuMater AntIsoinmuMater	  */
	public void setAntIsoinmuMater (boolean AntIsoinmuMater)
	{
		set_Value (COLUMNNAME_AntIsoinmuMater, Boolean.valueOf(AntIsoinmuMater));
	}

	/** Get AntIsoinmuMater.
		@return AntIsoinmuMater	  */
	public boolean isAntIsoinmuMater () 
	{
		Object oo = get_Value(COLUMNNAME_AntIsoinmuMater);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Autotransfusion.
		@param Autotransfusion Autotransfusion	  */
	public void setAutotransfusion (boolean Autotransfusion)
	{
		set_Value (COLUMNNAME_Autotransfusion, Boolean.valueOf(Autotransfusion));
	}

	/** Get Autotransfusion.
		@return Autotransfusion	  */
	public boolean isAutotransfusion () 
	{
		Object oo = get_Value(COLUMNNAME_Autotransfusion);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set C UOM FIB ID.
		@param C_UOM_Fib_ID C UOM FIB ID	  */
	public void setC_UOM_Fib_ID (int C_UOM_Fib_ID)
	{
		if (C_UOM_Fib_ID < 1) 
			set_Value (COLUMNNAME_C_UOM_Fib_ID, null);
		else 
			set_Value (COLUMNNAME_C_UOM_Fib_ID, Integer.valueOf(C_UOM_Fib_ID));
	}

	/** Get C UOM FIB ID.
		@return C UOM FIB ID	  */
	public int getC_UOM_Fib_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_UOM_Fib_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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

	/** Set C_UOM_Neu_ID.
		@param C_UOM_Neu_ID C_UOM_Neu_ID	  */
	public void setC_UOM_Neu_ID (int C_UOM_Neu_ID)
	{
		if (C_UOM_Neu_ID < 1) 
			set_Value (COLUMNNAME_C_UOM_Neu_ID, null);
		else 
			set_Value (COLUMNNAME_C_UOM_Neu_ID, Integer.valueOf(C_UOM_Neu_ID));
	}

	/** Get C_UOM_Neu_ID.
		@return C_UOM_Neu_ID	  */
	public int getC_UOM_Neu_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_UOM_Neu_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set UOM Platelets.
		@param C_UOM_Pla_ID UOM Platelets	  */
	public void setC_UOM_Pla_ID (int C_UOM_Pla_ID)
	{
		if (C_UOM_Pla_ID < 1)
			 throw new IllegalArgumentException ("C_UOM_Pla_ID is mandatory.");
		set_Value (COLUMNNAME_C_UOM_Pla_ID, Integer.valueOf(C_UOM_Pla_ID));
	}

	/** Get UOM Platelets.
		@return UOM Platelets	  */
	public int getC_UOM_Pla_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_UOM_Pla_ID);
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
			 throw new IllegalArgumentException ("EXME_Diagnostico_ID is mandatory.");
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

	/** Set Movement Line Blood.
		@param EXME_MovementLineB_ID Movement Line Blood	  */
	public void setEXME_MovementLineB_ID (int EXME_MovementLineB_ID)
	{
		if (EXME_MovementLineB_ID < 1)
			 throw new IllegalArgumentException ("EXME_MovementLineB_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_MovementLineB_ID, Integer.valueOf(EXME_MovementLineB_ID));
	}

	/** Get Movement Line Blood.
		@return Movement Line Blood	  */
	public int getEXME_MovementLineB_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_MovementLineB_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Date of application.
		@param FechaSol Date of application	  */
	public void setFechaSol (Timestamp FechaSol)
	{
		set_Value (COLUMNNAME_FechaSol, FechaSol);
	}

	/** Get Date of application.
		@return Date of application	  */
	public Timestamp getFechaSol () 
	{
		return (Timestamp)get_Value(COLUMNNAME_FechaSol);
	}

	/** Set Date of last transfusion.
		@param FechaUltimaTrans Date of last transfusion	  */
	public void setFechaUltimaTrans (Timestamp FechaUltimaTrans)
	{
		set_Value (COLUMNNAME_FechaUltimaTrans, FechaUltimaTrans);
	}

	/** Get Date of last transfusion.
		@return Date of last transfusion	  */
	public Timestamp getFechaUltimaTrans () 
	{
		return (Timestamp)get_Value(COLUMNNAME_FechaUltimaTrans);
	}

	/** Set Fibrinogen.
		@param Fibrinogeno Fibrinogen	  */
	public void setFibrinogeno (BigDecimal Fibrinogeno)
	{
		set_Value (COLUMNNAME_Fibrinogeno, Fibrinogeno);
	}

	/** Get Fibrinogen.
		@return Fibrinogen	  */
	public BigDecimal getFibrinogeno () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Fibrinogeno);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Hemoglobin.
		@param Hemoglobina Hemoglobin	  */
	public void setHemoglobina (BigDecimal Hemoglobina)
	{
		if (Hemoglobina == null)
			throw new IllegalArgumentException ("Hemoglobina is mandatory.");
		set_Value (COLUMNNAME_Hemoglobina, Hemoglobina);
	}

	/** Get Hemoglobin.
		@return Hemoglobin	  */
	public BigDecimal getHemoglobina () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Hemoglobina);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Indications.
		@param Indicaciones Indications	  */
	public void setIndicaciones (String Indicaciones)
	{
		if (Indicaciones == null)
			throw new IllegalArgumentException ("Indicaciones is mandatory.");
		set_Value (COLUMNNAME_Indicaciones, Indicaciones);
	}

	/** Get Indications.
		@return Indications	  */
	public String getIndicaciones () 
	{
		return (String)get_Value(COLUMNNAME_Indicaciones);
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

	/** Set Neutrophil.
		@param Neutrofilos Neutrophil	  */
	public void setNeutrofilos (BigDecimal Neutrofilos)
	{
		set_Value (COLUMNNAME_Neutrofilos, Neutrofilos);
	}

	/** Get Neutrophil.
		@return Neutrophil	  */
	public BigDecimal getNeutrofilos () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Neutrofilos);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Platelets.
		@param Plaquetas Platelets	  */
	public void setPlaquetas (BigDecimal Plaquetas)
	{
		if (Plaquetas == null)
			throw new IllegalArgumentException ("Plaquetas is mandatory.");
		set_Value (COLUMNNAME_Plaquetas, Plaquetas);
	}

	/** Get Platelets.
		@return Platelets	  */
	public BigDecimal getPlaquetas () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Plaquetas);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Priority AD_Reference_ID=1200238 */
	public static final int PRIORITY_AD_Reference_ID=1200238;
	/** Normal process = N */
	public static final String PRIORITY_NormalProcess = "N";
	/** Urgent = U */
	public static final String PRIORITY_Urgent = "U";
	/** Very urgent = M */
	public static final String PRIORITY_VeryUrgent = "M";
	/** Set Priority.
		@param Priority 
		Indicates if this request is of a high, medium or low priority.
	  */
	public void setPriority (String Priority)
	{
		if (Priority == null) throw new IllegalArgumentException ("Priority is mandatory");
		if (Priority.equals("N") || Priority.equals("U") || Priority.equals("M")); else throw new IllegalArgumentException ("Priority Invalid value - " + Priority + " - Reference_ID=1200238 - N - U - M");		set_Value (COLUMNNAME_Priority, Priority);
	}

	/** Get Priority.
		@return Indicates if this request is of a high, medium or low priority.
	  */
	public String getPriority () 
	{
		return (String)get_Value(COLUMNNAME_Priority);
	}

	/** Set Previous transfusion reactions .
		@param ReaccionTransPre Previous transfusion reactions 	  */
	public void setReaccionTransPre (boolean ReaccionTransPre)
	{
		set_Value (COLUMNNAME_ReaccionTransPre, Boolean.valueOf(ReaccionTransPre));
	}

	/** Get Previous transfusion reactions .
		@return Previous transfusion reactions 	  */
	public boolean isReaccionTransPre () 
	{
		Object oo = get_Value(COLUMNNAME_ReaccionTransPre);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Type of previous transfusion reactions .
		@param TipoReacTransPre Type of previous transfusion reactions 	  */
	public void setTipoReacTransPre (String TipoReacTransPre)
	{
		set_Value (COLUMNNAME_TipoReacTransPre, TipoReacTransPre);
	}

	/** Get Type of previous transfusion reactions .
		@return Type of previous transfusion reactions 	  */
	public String getTipoReacTransPre () 
	{
		return (String)get_Value(COLUMNNAME_TipoReacTransPre);
	}

	/** TipoSangre AD_Reference_ID=1200172 */
	public static final int TIPOSANGRE_AD_Reference_ID=1200172;
	/** AB+ = AB+ */
	public static final String TIPOSANGRE_ABPlus = "AB+";
	/** AB- = AB- */
	public static final String TIPOSANGRE_AB_ = "AB-";
	/** A+ = A+  */
	public static final String TIPOSANGRE_APlus = "A+ ";
	/** A- = A-  */
	public static final String TIPOSANGRE_A_ = "A- ";
	/** B+ = B+  */
	public static final String TIPOSANGRE_BPlus = "B+ ";
	/** B- = B-  */
	public static final String TIPOSANGRE_B_ = "B- ";
	/** O+ = O+  */
	public static final String TIPOSANGRE_OPlus = "O+ ";
	/** O- = O-  */
	public static final String TIPOSANGRE_O_ = "O- ";
	/** Unknown = UK  */
	public static final String TIPOSANGRE_Unknown = "UK ";
	/** Set Blood Type.
		@param TipoSangre Blood Type	  */
	public void setTipoSangre (String TipoSangre)
	{
		if (TipoSangre == null) throw new IllegalArgumentException ("TipoSangre is mandatory");
		if (TipoSangre.equals("AB+") || TipoSangre.equals("AB-") || TipoSangre.equals("A+ ") || TipoSangre.equals("A- ") || TipoSangre.equals("B+ ") || TipoSangre.equals("B- ") || TipoSangre.equals("O+ ") || TipoSangre.equals("O- ") || TipoSangre.equals("UK ")); else throw new IllegalArgumentException ("TipoSangre Invalid value - " + TipoSangre + " - Reference_ID=1200172 - AB+ - AB- - A+  - A-  - B+  - B-  - O+  - O-  - UK ");		set_Value (COLUMNNAME_TipoSangre, TipoSangre);
	}

	/** Get Blood Type.
		@return Blood Type	  */
	public String getTipoSangre () 
	{
		return (String)get_Value(COLUMNNAME_TipoSangre);
	}

	/** Set TP.
		@param TP TP	  */
	public void setTP (BigDecimal TP)
	{
		if (TP == null)
			throw new IllegalArgumentException ("TP is mandatory.");
		set_Value (COLUMNNAME_TP, TP);
	}

	/** Get TP.
		@return TP	  */
	public BigDecimal getTP () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_TP);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Previous transfusions.
		@param TransPrevias Previous transfusions	  */
	public void setTransPrevias (boolean TransPrevias)
	{
		set_Value (COLUMNNAME_TransPrevias, Boolean.valueOf(TransPrevias));
	}

	/** Get Previous transfusions.
		@return Previous transfusions	  */
	public boolean isTransPrevias () 
	{
		Object oo = get_Value(COLUMNNAME_TransPrevias);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set TTPA.
		@param TTPA TTPA	  */
	public void setTTPA (BigDecimal TTPA)
	{
		if (TTPA == null)
			throw new IllegalArgumentException ("TTPA is mandatory.");
		set_Value (COLUMNNAME_TTPA, TTPA);
	}

	/** Get TTPA.
		@return TTPA	  */
	public BigDecimal getTTPA () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_TTPA);
		if (bd == null)
			 return Env.ZERO;
		return bd;
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