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

/** Generated Model for EXME_OrderSet
 *  @author Generated Class 
 *  @version Version @VERSION@ - $Id$ */
public class X_EXME_OrderSet extends PO implements I_EXME_OrderSet, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_OrderSet (Properties ctx, int EXME_OrderSet_ID, String trxName)
    {
      super (ctx, EXME_OrderSet_ID, trxName);
      /** if (EXME_OrderSet_ID == 0)
        {
			setEXME_OrderCategory_ID (0);
			setName (null);
			setOrderType (null);
			setValue (null);
        } */
    }

    /** Load Constructor */
    public X_EXME_OrderSet (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_OrderSet[")
        .append(get_ID()).append("]");
      return sb.toString();
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

	public I_EXME_GrupoCuestionario getEXME_GrupoCuestionario() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_GrupoCuestionario.Table_Name);
        I_EXME_GrupoCuestionario result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_GrupoCuestionario)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_GrupoCuestionario_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Form Group.
		@param EXME_GrupoCuestionario_ID 
		Form Group
	  */
	public void setEXME_GrupoCuestionario_ID (int EXME_GrupoCuestionario_ID)
	{
		if (EXME_GrupoCuestionario_ID < 1) 
			set_Value (COLUMNNAME_EXME_GrupoCuestionario_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_GrupoCuestionario_ID, Integer.valueOf(EXME_GrupoCuestionario_ID));
	}

	/** Get Form Group.
		@return Form Group
	  */
	public int getEXME_GrupoCuestionario_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_GrupoCuestionario_ID);
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

	public I_EXME_OrderCategory getEXME_OrderCategory() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_OrderCategory.Table_Name);
        I_EXME_OrderCategory result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_OrderCategory)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_OrderCategory_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Orders Set Category.
		@param EXME_OrderCategory_ID Orders Set Category	  */
	public void setEXME_OrderCategory_ID (int EXME_OrderCategory_ID)
	{
		if (EXME_OrderCategory_ID < 1)
			 throw new IllegalArgumentException ("EXME_OrderCategory_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_OrderCategory_ID, Integer.valueOf(EXME_OrderCategory_ID));
	}

	/** Get Orders Set Category.
		@return Orders Set Category	  */
	public int getEXME_OrderCategory_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_OrderCategory_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Order Set.
		@param EXME_OrderSet_ID Order Set	  */
	public void setEXME_OrderSet_ID (int EXME_OrderSet_ID)
	{
		if (EXME_OrderSet_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_EXME_OrderSet_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_EXME_OrderSet_ID, Integer.valueOf(EXME_OrderSet_ID));
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

	/** Set IsNormalCondition.
		@param IsCondicionNormal IsNormalCondition	  */
	public void setIsCondicionNormal (boolean IsCondicionNormal)
	{
		set_Value (COLUMNNAME_IsCondicionNormal, Boolean.valueOf(IsCondicionNormal));
	}

	/** Get IsNormalCondition.
		@return IsNormalCondition	  */
	public boolean isCondicionNormal () 
	{
		Object oo = get_Value(COLUMNNAME_IsCondicionNormal);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Multi System.
		@param isMultiSys 
		Indicates whether or not is multi system
	  */
	public void setisMultiSys (boolean isMultiSys)
	{
		set_Value (COLUMNNAME_isMultiSys, Boolean.valueOf(isMultiSys));
	}

	/** Get Multi System.
		@return Indicates whether or not is multi system
	  */
	public boolean isMultiSys () 
	{
		Object oo = get_Value(COLUMNNAME_isMultiSys);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
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

	/** OrderType AD_Reference_ID=1200515 */
	public static final int ORDERTYPE_AD_Reference_ID=1200515;
	/** Medications = MD */
	public static final String ORDERTYPE_Medications = "MD";
	/** Laboratory = LB */
	public static final String ORDERTYPE_Laboratory = "LB";
	/** Imaging = IM */
	public static final String ORDERTYPE_Imaging = "IM";
	/** Prep Meds = PM */
	public static final String ORDERTYPE_PrepMeds = "PM";
	/** ADT = AD */
	public static final String ORDERTYPE_ADT = "AD";
	/** Respiratory = RS */
	public static final String ORDERTYPE_Respiratory = "RS";
	/** Orthopedics = OP */
	public static final String ORDERTYPE_Orthopedics = "OP";
	/** Proc/Treatments = PT */
	public static final String ORDERTYPE_ProcTreatments = "PT";
	/** Supplies = SP */
	public static final String ORDERTYPE_Supplies = "SP";
	/** Nutrition = NT */
	public static final String ORDERTYPE_Nutrition = "NT";
	/** Consults/Referrals = CR */
	public static final String ORDERTYPE_ConsultsReferrals = "CR";
	/** Nursing = NR */
	public static final String ORDERTYPE_Nursing = "NR";
	/** Others = OT */
	public static final String ORDERTYPE_Others = "OT";
	/** Diagnosis = DG */
	public static final String ORDERTYPE_Diagnosis = "DG";
	/** Today Services = TS */
	public static final String ORDERTYPE_TodayServices = "TS";
	/** Medical Appointment = MA */
	public static final String ORDERTYPE_MedicalAppointment = "MA";
	/** Set OrderType.
		@param OrderType OrderType	  */
	public void setOrderType (String OrderType)
	{
		if (OrderType == null) throw new IllegalArgumentException ("OrderType is mandatory");
		if (OrderType.equals("MD") || OrderType.equals("LB") || OrderType.equals("IM") || OrderType.equals("PM") || OrderType.equals("AD") || OrderType.equals("RS") || OrderType.equals("OP") || OrderType.equals("PT") || OrderType.equals("SP") || OrderType.equals("NT") || OrderType.equals("CR") || OrderType.equals("NR") || OrderType.equals("OT") || OrderType.equals("DG") || OrderType.equals("TS") || OrderType.equals("MA")); else throw new IllegalArgumentException ("OrderType Invalid value - " + OrderType + " - Reference_ID=1200515 - MD - LB - IM - PM - AD - RS - OP - PT - SP - NT - CR - NR - OT - DG - TS - MA");		set_Value (COLUMNNAME_OrderType, OrderType);
	}

	/** Get OrderType.
		@return OrderType	  */
	public String getOrderType () 
	{
		return (String)get_Value(COLUMNNAME_OrderType);
	}

	/** SystemType AD_Reference_ID=1200613 */
	public static final int SYSTEMTYPE_AD_Reference_ID=1200613;
	/** Breast = BR */
	public static final String SYSTEMTYPE_Breast = "BR";
	/** Cardiovascular = CA */
	public static final String SYSTEMTYPE_Cardiovascular = "CA";
	/** Ears, Nose, Mouth, and Throat = EN */
	public static final String SYSTEMTYPE_EarsNoseMouthAndThroat = "EN";
	/** Eyes = EY */
	public static final String SYSTEMTYPE_Eyes = "EY";
	/** Genitourinary = GE */
	public static final String SYSTEMTYPE_Genitourinary = "GE";
	/** Hematologic/Lymphatic/Immunologic = HL */
	public static final String SYSTEMTYPE_HematologicLymphaticImmunologic = "HL";
	/** Musculoskeletal = MU */
	public static final String SYSTEMTYPE_Musculoskeletal = "MU";
	/** Neurological = NE */
	public static final String SYSTEMTYPE_Neurological = "NE";
	/** Psychiatric = PS */
	public static final String SYSTEMTYPE_Psychiatric = "PS";
	/** Respiratory = RE */
	public static final String SYSTEMTYPE_Respiratory = "RE";
	/** Skin = SK */
	public static final String SYSTEMTYPE_Skin = "SK";
	/** Set System Type.
		@param SystemType System Type	  */
	public void setSystemType (String SystemType)
	{

		if (SystemType == null || SystemType.equals("BR") || SystemType.equals("CA") || SystemType.equals("EN") || SystemType.equals("EY") || SystemType.equals("GE") || SystemType.equals("HL") || SystemType.equals("MU") || SystemType.equals("NE") || SystemType.equals("PS") || SystemType.equals("RE") || SystemType.equals("SK")); else throw new IllegalArgumentException ("SystemType Invalid value - " + SystemType + " - Reference_ID=1200613 - BR - CA - EN - EY - GE - HL - MU - NE - PS - RE - SK");		set_Value (COLUMNNAME_SystemType, SystemType);
	}

	/** Get System Type.
		@return System Type	  */
	public String getSystemType () 
	{
		return (String)get_Value(COLUMNNAME_SystemType);
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

		if (TipoArea == null || TipoArea.equals("H") || TipoArea.equals("U") || TipoArea.equals("A") || TipoArea.equals("C") || TipoArea.equals("P") || TipoArea.equals("R") || TipoArea.equals("O") || TipoArea.equals("E") || TipoArea.equals("D") || TipoArea.equals("T") || TipoArea.equals("S")); else throw new IllegalArgumentException ("TipoArea Invalid value - " + TipoArea + " - Reference_ID=1000039 - H - U - A - C - P - R - O - E - D - T - S");		set_Value (COLUMNNAME_TipoArea, TipoArea);
	}

	/** Get Area Type.
		@return Admission Area Type
	  */
	public String getTipoArea () 
	{
		return (String)get_Value(COLUMNNAME_TipoArea);
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