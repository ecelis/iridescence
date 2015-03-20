/******************************************************************************
 * Product: Compiere ERP & CRM Smart Business Solution                        *
 * Copyright (C) 1999-2006 ComPiere, Inc. All Rights Reserved.                *
 * This program is free software; you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * ComPiere, Inc., 2620 Augustine Dr. #245, Santa Clara, CA 95054, USA        *
 * or via info@compiere.org or http://www.compiere.org/license.html           *
 *****************************************************************************/
package org.compiere.model;

import java.sql.*;
import java.text.*;
import java.util.*;
import java.util.logging.*;

import org.compiere.process.*;
import org.compiere.util.*;


/**
 *	Year Model
 *	
 *  @author Jorg Janke
 *  @version $Id: MYear.java,v 1.4 2006/07/30 00:51:04 jjanke Exp $
 */
public class MYear extends X_C_Year
{

	/**	Logger							*/
	private static CLogger		s_log = CLogger.getCLogger (MYear.class);
	
	/**
	 * 	Standard Constructor
	 *	@param ctx context
	 *	@param C_Year_ID id
	 *	@param trxName transaction
	 */
	public MYear (Properties ctx, int C_Year_ID, String trxName)
	{
		super (ctx, C_Year_ID, trxName);
		if (C_Year_ID == 0)
		{
		//	setC_Calendar_ID (0);
		//	setYear (null);
			setProcessing (false);	// N
		}		
	}	//	MYear

	/**
	 * 	Load Constructor
	 *	@param ctx context
	 *	@param rs result set
	 *	@param trxName transaction
	 */
	public MYear (Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
	}	//	MYear
	
	/**
	 * 	Parent Constructor
	 *	@param calendar parent
	 */
	public MYear (MCalendar calendar)
	{
		this (calendar.getCtx(), 0, calendar.get_TrxName());
		setClientOrg(calendar);
		setC_Calendar_ID(calendar.getC_Calendar_ID());
		setYear();
	}	//	MYear
	
	
	/**
	 * 	Set current Year
	 */
	private void setYear ()
	{
		GregorianCalendar cal = new GregorianCalendar(Language.getLoginLanguage().getLocale());
		String Year = String.valueOf(cal.get(Calendar.YEAR));
		super.setYear (Year);
	}	//	setYear
	
	/**
	 * 	Get Year As Int
	 *	@return year as int or 0
	 */
	public int getYearAsInt()
	{
		String year = getYear();
		try
		{
			return Integer.parseInt(year);
		}
		catch (Exception e)
		{
			StringTokenizer st = new StringTokenizer(year, "/-, \t\n\r\f");
			if (st.hasMoreTokens())
			{
				String year2 = st.nextToken();
				try
				{
					return Integer.parseInt(year2);
				}
				catch (Exception e2)
				{
					log.log(Level.WARNING, year + "->" + year2 + " - " + e2.toString());
				}
			}
			else
				log.log(Level.WARNING, year + " - " + e.toString());
		}
		return 0;
	}	//	getYearAsInt
	
	/**
	 * 	Get last two characters of year
	 *	@return 01
	 */
	public String getYY()
	{
		int yy = getYearAsInt();
		String year = String.valueOf(yy);
		if (year.length() == 4)
			return year.substring(2, 4);
		return getYear();
	}	//	getYY
	
	/**
	 * 	String Representation
	 *	@return info
	 */
	public String toString ()
	{
		StringBuffer sb = new StringBuffer ("MYear[");
		sb.append(get_ID()).append("-")
			.append(getYear())
			.append ("]");
		return sb.toString ();
	}	//	toString
	
	
	/**
	 * 	Before Save
	 *	@param newRecord new
	 *	@return true if can be saved
	 */
	protected boolean beforeSave (boolean newRecord)
	{
		int yy = getYearAsInt();
		if (yy == 0)
		{
			log.saveError("FillMandatory", Msg.getElement(getCtx(), "Year") 
				+ " -> " + yy + " (2006 - 2006/07 - 2006-07 - ...)");
			return false;
		}
		return true;
	}	//	beforeSave
	
	/**
	 * 	Create 12 Standard (Jan-Dec) Periods.
	 * 	Creates also Period Control from DocType.
	 * 	@see DocumentTypeVerify#createPeriodControls(Properties, int, SvrProcess, String)
	 * 	@param locale locale 
	 *	@return true if created
	 */
	public boolean createStdPeriods(Locale locale) {
		if (locale == null)	{
			MClient client = MClient.get(getCtx());
			locale = client.getLocale();
		}
		
		if (locale == null && Language.getLoginLanguage() != null) {
			locale = Language.getLoginLanguage().getLocale();
		}
		
		if (locale == null) {
			locale = Env.getLanguage(getCtx()).getLocale();
		}
		
		//
		String[] months = null;
		try {
			DateFormatSymbols symbols = new DateFormatSymbols(locale);
			months = symbols.getShortMonths();
		} catch (Exception e) {
			months = new String[]{"Jan", "Feb", "Nar",
				"Apr", "May", "Jun",
				"Jul", "Aug", "Sep",
				"Oct", "Nov", "Dec"};
		}
		//
		int year = getYearAsInt();
		GregorianCalendar cal = new GregorianCalendar(TimeZone.getTimeZone(Env.getTimezone(Env.getCtx())));
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		//
		for (int month = 0; month < 12; month++) {
			cal.set(Calendar.YEAR, year);
			cal.set(Calendar.MONTH, month);
			cal.set(Calendar.DAY_OF_MONTH, 1);
			Timestamp start = DB.convert(Env.getCtx(), new Timestamp(cal.getTimeInMillis()));
			String name = months[month] + "-" + getYY();
			//
			cal.add(Calendar.MONTH, 1);
			cal.add(Calendar.DAY_OF_YEAR, -1);
			Timestamp end = DB.convert(Env.getCtx(), new Timestamp(cal.getTimeInMillis()));
			//
			MPeriod period = new MPeriod (this, month+1, name, start, end);
			if (!period.save(get_TrxName()))	//	Creates Period Control
				return false;
		}
		
		return true;
	}	//	createStdPeriods
	
	
	/**
	 * 
	 * @param ctx
	 * @return
	 */
	public static MYear getActualYear(Properties ctx){
		
		MYear year = null;
		
		Calendar now = Calendar.getInstance();
		int yearInt = now.get(Calendar.YEAR);
		
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT * FROM C_YEAR WHERE AD_CLIENT_ID= ?  AND YEAR = ? AND ISACTIVE = 'Y' ");
       
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
		try{
			pstmt = DB.prepareStatement(sql.toString(), null);		
			pstmt.setInt(1, Env.getAD_Client_ID(ctx));
			pstmt.setString(2, String.valueOf(yearInt));
			
			rs = pstmt.executeQuery();
			
			if (rs.next()){
				year = new MYear(ctx, rs, null);
			}
			
		}catch (Exception e){
			s_log.log(Level.SEVERE, sql.toString(), e);
		} finally{
			DB.close(rs,pstmt);
		}
		return year;
	}
	
	public static List<KeyNamePair> getActiveYears(Properties ctx, String trxName) {

		StringBuilder str = new StringBuilder();
		str.append("select c_year_id, year from c_year where isactive = 'Y' ");
		str.append("and ad_client_id = ? ");

		return DB.getKeyNamePairsList(str.toString(), false, new Object[] { Env.getAD_Client_ID(ctx) });
	}
	
}	//	MYear
