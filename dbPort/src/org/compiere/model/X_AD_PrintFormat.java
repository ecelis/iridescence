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

/** Generated Model for AD_PrintFormat
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_AD_PrintFormat extends PO implements I_AD_PrintFormat, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_AD_PrintFormat (Properties ctx, int AD_PrintFormat_ID, String trxName)
    {
      super (ctx, AD_PrintFormat_ID, trxName);
      /** if (AD_PrintFormat_ID == 0)
        {
			setAD_PrintColor_ID (0);
			setAD_PrintFont_ID (0);
			setAD_PrintFormat_ID (0);
// 0
			setAD_PrintPaper_ID (0);
			setAD_Table_ID (0);
			setFooterMargin (0);
			setHeaderMargin (0);
			setIsDefault (false);
			setIsForm (false);
			setIsStandardHeaderFooter (true);
// Y
			setIsTableBased (true);
// Y
			setName (null);
        } */
    }

    /** Load Constructor */
    public X_AD_PrintFormat (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_AD_PrintFormat[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public I_AD_PrintColor getAD_PrintColor() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_AD_PrintColor.Table_Name);
        I_AD_PrintColor result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_AD_PrintColor)constructor.newInstance(new Object[] {getCtx(), new Integer(getAD_PrintColor_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Print Color.
		@param AD_PrintColor_ID 
		Color used for printing and display
	  */
	public void setAD_PrintColor_ID (int AD_PrintColor_ID)
	{
		if (AD_PrintColor_ID < 1)
			 throw new IllegalArgumentException ("AD_PrintColor_ID is mandatory.");
		set_Value (COLUMNNAME_AD_PrintColor_ID, Integer.valueOf(AD_PrintColor_ID));
	}

	/** Get Print Color.
		@return Color used for printing and display
	  */
	public int getAD_PrintColor_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_PrintColor_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_AD_PrintFont getAD_PrintFont() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_AD_PrintFont.Table_Name);
        I_AD_PrintFont result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_AD_PrintFont)constructor.newInstance(new Object[] {getCtx(), new Integer(getAD_PrintFont_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Print Font.
		@param AD_PrintFont_ID 
		Maintain Print Font
	  */
	public void setAD_PrintFont_ID (int AD_PrintFont_ID)
	{
		if (AD_PrintFont_ID < 1)
			 throw new IllegalArgumentException ("AD_PrintFont_ID is mandatory.");
		set_Value (COLUMNNAME_AD_PrintFont_ID, Integer.valueOf(AD_PrintFont_ID));
	}

	/** Get Print Font.
		@return Maintain Print Font
	  */
	public int getAD_PrintFont_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_PrintFont_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Print Format.
		@param AD_PrintFormat_ID 
		Data Print Format
	  */
	public void setAD_PrintFormat_ID (int AD_PrintFormat_ID)
	{
		if (AD_PrintFormat_ID < 1)
			 throw new IllegalArgumentException ("AD_PrintFormat_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_AD_PrintFormat_ID, Integer.valueOf(AD_PrintFormat_ID));
	}

	/** Get Print Format.
		@return Data Print Format
	  */
	public int getAD_PrintFormat_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_PrintFormat_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_AD_PrintPaper getAD_PrintPaper() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_AD_PrintPaper.Table_Name);
        I_AD_PrintPaper result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_AD_PrintPaper)constructor.newInstance(new Object[] {getCtx(), new Integer(getAD_PrintPaper_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Print Paper.
		@param AD_PrintPaper_ID 
		Printer paper definition
	  */
	public void setAD_PrintPaper_ID (int AD_PrintPaper_ID)
	{
		if (AD_PrintPaper_ID < 1)
			 throw new IllegalArgumentException ("AD_PrintPaper_ID is mandatory.");
		set_Value (COLUMNNAME_AD_PrintPaper_ID, Integer.valueOf(AD_PrintPaper_ID));
	}

	/** Get Print Paper.
		@return Printer paper definition
	  */
	public int getAD_PrintPaper_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_PrintPaper_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_AD_PrintTableFormat getAD_PrintTableFormat() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_AD_PrintTableFormat.Table_Name);
        I_AD_PrintTableFormat result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_AD_PrintTableFormat)constructor.newInstance(new Object[] {getCtx(), new Integer(getAD_PrintTableFormat_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Print Table Format.
		@param AD_PrintTableFormat_ID 
		Table Format in Reports
	  */
	public void setAD_PrintTableFormat_ID (int AD_PrintTableFormat_ID)
	{
		if (AD_PrintTableFormat_ID < 1) 
			set_Value (COLUMNNAME_AD_PrintTableFormat_ID, null);
		else 
			set_Value (COLUMNNAME_AD_PrintTableFormat_ID, Integer.valueOf(AD_PrintTableFormat_ID));
	}

	/** Get Print Table Format.
		@return Table Format in Reports
	  */
	public int getAD_PrintTableFormat_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_PrintTableFormat_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_AD_ReportView getAD_ReportView() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_AD_ReportView.Table_Name);
        I_AD_ReportView result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_AD_ReportView)constructor.newInstance(new Object[] {getCtx(), new Integer(getAD_ReportView_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Report View.
		@param AD_ReportView_ID 
		View used to generate this report
	  */
	public void setAD_ReportView_ID (int AD_ReportView_ID)
	{
		if (AD_ReportView_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_AD_ReportView_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_AD_ReportView_ID, Integer.valueOf(AD_ReportView_ID));
	}

	/** Get Report View.
		@return View used to generate this report
	  */
	public int getAD_ReportView_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_ReportView_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_AD_Table getAD_Table() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_AD_Table.Table_Name);
        I_AD_Table result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_AD_Table)constructor.newInstance(new Object[] {getCtx(), new Integer(getAD_Table_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Table.
		@param AD_Table_ID 
		Database Table information
	  */
	public void setAD_Table_ID (int AD_Table_ID)
	{
		if (AD_Table_ID < 1)
			 throw new IllegalArgumentException ("AD_Table_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_AD_Table_ID, Integer.valueOf(AD_Table_ID));
	}

	/** Get Table.
		@return Database Table information
	  */
	public int getAD_Table_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_Table_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Args.
		@param Args Args	  */
	public void setArgs (String Args)
	{
		set_Value (COLUMNNAME_Args, Args);
	}

	/** Get Args.
		@return Args	  */
	public String getArgs () 
	{
		return (String)get_Value(COLUMNNAME_Args);
	}

	/** Set Classname.
		@param Classname 
		Java Classname
	  */
	public void setClassname (String Classname)
	{
		set_Value (COLUMNNAME_Classname, Classname);
	}

	/** Get Classname.
		@return Java Classname
	  */
	public String getClassname () 
	{
		return (String)get_Value(COLUMNNAME_Classname);
	}

	/** Set Create Copy.
		@param CreateCopy Create Copy	  */
	public void setCreateCopy (String CreateCopy)
	{
		set_Value (COLUMNNAME_CreateCopy, CreateCopy);
	}

	/** Get Create Copy.
		@return Create Copy	  */
	public String getCreateCopy () 
	{
		return (String)get_Value(COLUMNNAME_CreateCopy);
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

	/** Set Footer Margin.
		@param FooterMargin 
		Margin of the Footer in 1/72 of an inch
	  */
	public void setFooterMargin (int FooterMargin)
	{
		set_Value (COLUMNNAME_FooterMargin, Integer.valueOf(FooterMargin));
	}

	/** Get Footer Margin.
		@return Margin of the Footer in 1/72 of an inch
	  */
	public int getFooterMargin () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_FooterMargin);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Header Margin.
		@param HeaderMargin 
		Margin of the Header in 1/72 of an inch
	  */
	public void setHeaderMargin (int HeaderMargin)
	{
		set_Value (COLUMNNAME_HeaderMargin, Integer.valueOf(HeaderMargin));
	}

	/** Get Header Margin.
		@return Margin of the Header in 1/72 of an inch
	  */
	public int getHeaderMargin () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HeaderMargin);
		if (ii == null)
			 return 0;
		return ii.intValue();
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

	/** Set Form.
		@param IsForm 
		If Selected, a Form is printed, if not selected a columnar List report
	  */
	public void setIsForm (boolean IsForm)
	{
		set_Value (COLUMNNAME_IsForm, Boolean.valueOf(IsForm));
	}

	/** Get Form.
		@return If Selected, a Form is printed, if not selected a columnar List report
	  */
	public boolean isForm () 
	{
		Object oo = get_Value(COLUMNNAME_IsForm);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Standard Header/Footer.
		@param IsStandardHeaderFooter 
		The standard Header and Footer is used
	  */
	public void setIsStandardHeaderFooter (boolean IsStandardHeaderFooter)
	{
		set_Value (COLUMNNAME_IsStandardHeaderFooter, Boolean.valueOf(IsStandardHeaderFooter));
	}

	/** Get Standard Header/Footer.
		@return The standard Header and Footer is used
	  */
	public boolean isStandardHeaderFooter () 
	{
		Object oo = get_Value(COLUMNNAME_IsStandardHeaderFooter);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Table Based.
		@param IsTableBased 
		Table based List Reporting
	  */
	public void setIsTableBased (boolean IsTableBased)
	{
		set_ValueNoCheck (COLUMNNAME_IsTableBased, Boolean.valueOf(IsTableBased));
	}

	/** Get Table Based.
		@return Table based List Reporting
	  */
	public boolean isTableBased () 
	{
		Object oo = get_Value(COLUMNNAME_IsTableBased);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Jasper Process.
		@param JasperProcess_ID 
		The Jasper Process used by the printengine if any process defined
	  */
	public void setJasperProcess_ID (int JasperProcess_ID)
	{
		if (JasperProcess_ID < 1) 
			set_Value (COLUMNNAME_JasperProcess_ID, null);
		else 
			set_Value (COLUMNNAME_JasperProcess_ID, Integer.valueOf(JasperProcess_ID));
	}

	/** Get Jasper Process.
		@return The Jasper Process used by the printengine if any process defined
	  */
	public int getJasperProcess_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_JasperProcess_ID);
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

	/** Set Printer Name.
		@param PrinterName 
		Name of the Printer
	  */
	public void setPrinterName (String PrinterName)
	{
		set_Value (COLUMNNAME_PrinterName, PrinterName);
	}

	/** Get Printer Name.
		@return Name of the Printer
	  */
	public String getPrinterName () 
	{
		return (String)get_Value(COLUMNNAME_PrinterName);
	}
}