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
package org.compiere.util;

import java.awt.*;
import java.rmi.*;
import java.sql.*;
import java.util.logging.*;

import org.apache.commons.lang.StringUtils;


/**
 *	Compiere Log Formatter
 *
 *  @author Jorg Janke
 *  @version $Id: CLogFormatter.java,v 1.2 2006/07/30 00:54:36 jjanke Exp $
 */
public final class CLogFormatter extends Formatter
{
	/**
	 * 	Get Formatter
	 *	@return singleton
	 */
	public static CLogFormatter get()
	{
		if (formatter == null) {
			formatter = new CLogFormatter();
		}
		return formatter;
	}	//	get

	/**	Singleton				*/
	private static CLogFormatter	formatter = null;
    /**	New Line				*/
    public static final String	NEWLINE = System.getProperty("line.separator");

	/**************************************************************************
	 * 	CLogFormatter
	 */
	private CLogFormatter()
	{
		super ();
	}	//	CLogFormatter

	/** Short Format			*/
	private transient boolean			shortFormat = false;

	/**
	 * 	Format
	 *	@param record log record
	 *	@return formatted string
	 */
	public String format (final LogRecord record)
	{
		StringBuffer logMessage = new StringBuffer(Constantes.INIT_CAPACITY_ARRAY);

		final long ms = record.getMillis();
		Timestamp ts = null;
		if (ms == 0) {
			ts = new Timestamp(System.currentTimeMillis());
		} else {
			ts = new Timestamp(ms);
		}

		String tsStr = StringUtils.EMPTY;
		try
		{
			tsStr = ts.toString() + "00";
		}
		catch (Exception e)
		{
			System.err.println("CLogFormatter.format: Millis="
				+ ms + " - " + e.toString() + " - " + record.getMessage());
			//      1   5    1    5    2    5
			tsStr = "_________________________";
		}


		tsStr = tsStr.substring(11, 23);

		logMessage.append(tsStr).append("   ");
		/**	Time/Error		*/
		if (record.getLevel() == Level.SEVERE)
		{	//		   12:12:12.123
			logMessage.append("===========> ");
			if (Ini.isClient()) {
				Toolkit.getDefaultToolkit().beep();
			}
		}
		else if (record.getLevel() == Level.WARNING)
		{	//		   12:12:12.123
			logMessage.append("-----------> ");
		}
		else
		{
			int spaces = 11;
			if (record.getLevel() == Level.INFO) {
				spaces = 1;
			} else if (record.getLevel() == Level.CONFIG) {
				spaces = 3;
			} else if (record.getLevel() == Level.FINE) {
				spaces = 5;
			} else if (record.getLevel() == Level.FINER) {
				spaces = 7;
			} else if (record.getLevel() == Level.FINEST) {
				spaces = 9;
			}
			logMessage.append("                          ".substring(0, spaces));
		}

		/**	Class.method	**/
		if (!shortFormat) {
			logMessage.append(getClassMethod(record)).append(": ");
		}

		/**	Message			**/
		logMessage.append(record.getMessage());
		/** Parameters		**/
		final String parameters = getParameters(record);
		if (parameters.length() > 0) {
			logMessage.append(" (").append(parameters).append(')');
		}

		/**	Level			**
		sb.append(" ")
			.append(record.getLevel().getLocalizedName());
		/**	Thread			**/
		if (record.getThreadID() != 10) {
			logMessage.append(" [").append(record.getThreadID()).append(']');
		}

		//
		logMessage.append(NEWLINE);
		if (record.getThrown() != null) {
	        logMessage.append(getExceptionTrace(record)).append(NEWLINE);
		}
		return logMessage.toString();
	}	//	format


    /**
     * Return the header string for a set of formatted records.
     * @param   handler  The target handler.
     * @return  header string
     */
    public String getHead(final Handler handler)
    {
		String className = handler.getClass().getName();
		final int index = className.lastIndexOf('.');

		if (index != -1) {
			className = className.substring(index+1);
		}

		final StringBuffer logMessage = new StringBuffer()
			.append("*** ")
			.append(new Timestamp(System.currentTimeMillis()))
			.append(" eCareSoft Log (").append(className)
			.append(") ***").append(NEWLINE);
    	return logMessage.toString();
    }	//	getHead

    /**
     * Return the tail string for a set of formatted records.
     * @param   handler  The target handler.
     * @return  tail string
     */
    public String getTail(final Handler handler)
    {
		String className = handler.getClass().getName();
		final int index = className.lastIndexOf('.');

		if (index != -1) {
			className = className.substring(index+1);
		}

		final StringBuffer logMessage = new StringBuffer()
			.append(NEWLINE)
			.append("*** ")
			.append(new Timestamp(System.currentTimeMillis()))
			.append(" eCareSoft Log (").append(className)
			.append(") ***").append(NEWLINE);
    	return logMessage.toString();
    }	//	getTail

    /**
     * 	Set Format
     *	@param shortFormat format
     */
    public void setFormat (final boolean shortFormat)
    {
    	this.shortFormat = shortFormat;
    }	//	setFormat

    /**************************************************************************
     * 	Get Class Method from Log Record
     *	@param record record
     *	@return class.method
     */
    public static String getClassMethod (final LogRecord record)
    {
    	final StringBuffer logMessage = new StringBuffer();
    	String className = record.getLoggerName();

    	if (className == null
			|| className.indexOf("default") != -1	//	anonymous logger
			|| className.indexOf("global") != -1) {	//	global logger
    		className = record.getSourceClassName();
    	}

		if (className == null)
		{
			logMessage.append(record.getLoggerName());
		}
		else
		{
			final int index = className.lastIndexOf('.');
			if (index == -1)
			{
				logMessage.append(className);
			}
			else
			{
				logMessage.append(className.substring(index+1));
			}
		}

		if (record.getSourceMethodName() != null) {
		    logMessage.append('.').append(record.getSourceMethodName());
		}

		String retValue = logMessage.toString();

		if ("Trace.printStack".equals(retValue)) {
			retValue = StringUtils.EMPTY;
		}

		return retValue;
    }	//	getClassMethod

    /**
     * 	Get Log Parameters
     *	@param record log record
     *	@return parameters empty string or parameters
     */
    public static String getParameters (final LogRecord record)
    {
    	final StringBuffer paramValues = new StringBuffer();
		final Object[] parameters = record.getParameters();
		if (parameters != null && parameters.length > 0)
		{
			for (int i = 0; i < parameters.length; i++)
			{
				if (i > 0) {
					paramValues.append(", ");
				}
				paramValues.append(parameters[i]);
			}
		}
		return paramValues.toString();
    }	//	getParameters

    /**
     * 	Get Log Exception
     *	@param record log record
     *	@return null if exists or string
     */
    public static String getExceptionTrace (final LogRecord record)
    {
    	final StringBuffer exceptionTrace = new StringBuffer();

    	final Throwable thrown = record.getThrown();

    	if (thrown != null) {
    		try
    		{
    			/**	Create Stack	**/
    			fillExceptionTrace(exceptionTrace, "", thrown);
    		}
    		catch (Exception ex)
    		{
    			ex.printStackTrace();
    		}
    	}

    	return StringUtils.trimToNull(exceptionTrace.toString());
    }	//	getException

    /**
     * 	Fill Exception Trace
     *	@param exceptionTrace string buffer
     *	@param hdr header
     *	@param thrown thrown
     */
    private static void fillExceptionTrace (final StringBuffer exceptionTrace,
    		final String hdr, final Throwable thrown)
    {
//    	boolean firstError = hdr.length() == 0;
		exceptionTrace.append(hdr)
			.append(thrown.toString());
		if (thrown instanceof SQLException)
		{
			SQLException ex = (SQLException)thrown;
			exceptionTrace.append("; State=").append(ex.getSQLState())
				.append("; ErrorCode=").append(ex.getErrorCode());
		}
		exceptionTrace.append(NEWLINE);
		//
		StackTraceElement[] trace = thrown.getStackTrace();
		boolean compiereTrace = false;
		int compiereTraceNo = 0;
        for (int i=0; i < trace.length; i++)
        {
        	compiereTrace = trace[i].getClassName().startsWith("org.compiere.");
        	if (thrown instanceof ServerException	//	RMI
        		|| compiereTrace)
        	{
        		if (compiereTrace) {
                	exceptionTrace.append("\tat ").append(trace[i]).append(NEWLINE);
        		}
        	}
        	else if (i > 20 || (i > 10 && compiereTraceNo > 8))
        	{
        		break;
        	}
        	else
        	{
        		exceptionTrace.append("\tat ").append(trace[i]).append(NEWLINE);
        	}

        	if (compiereTrace)
        	{
        		compiereTraceNo++;
        	}
        }
        //
        Throwable cause = thrown.getCause();
        if (cause != null)
        {
        	fillExceptionTrace(exceptionTrace, "caused by: ", cause);
        }
    }	//	fillExceptionTrace

}	//	CLogFormatter
