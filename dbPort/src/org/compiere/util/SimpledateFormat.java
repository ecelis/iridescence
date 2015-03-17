/**
 * 
 */
package org.compiere.util;

import java.sql.Timestamp;
import java.text.DateFormatSymbols;
import java.util.Date;
import java.util.Locale;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;

/**
 * @author lama
 * 
 */
@SuppressWarnings("unused")
public class SimpledateFormat extends java.text.SimpleDateFormat {

	private static final long	serialVersionUID	= 1L;
	private Properties			ctx;

	public SimpledateFormat(Properties ctx, String pattern) {
		super(pattern);
		this.ctx = ctx;
	}

	public SimpledateFormat(Properties ctx, String pattern, Locale locale) {
		super(pattern, locale);
		this.ctx = ctx;
	}

	private SimpledateFormat(String pattern) {
		super(pattern);
	}

	private SimpledateFormat(String pattern, DateFormatSymbols arg1) {
		super(pattern, arg1);
	}

	private SimpledateFormat(String pattern, Locale locale) {
		super(pattern, locale);
	}

	public Properties getCtx() {
		return ctx;
	}

	public String format(Timestamp timestamp) {
		if (timestamp == null) {
			return StringUtils.EMPTY;
		}
		return super.format(DB.convert(ctx, timestamp));
	}

	public String formatConvert(Timestamp timestamp) {
		if (timestamp == null) {
			return StringUtils.EMPTY;
		}
		return super.format(DB.convert(ctx, timestamp));
	}

	public String formatConvert(Date date) {
		if (date == null) {
			return StringUtils.EMPTY;
		}
		return super.format(DB.convert(ctx, date));
	}
}
