/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 2008 SC ARHIPAC SERVICE SRL. All Rights Reserved.            *
 * This program is free software; you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 *****************************************************************************/
package org.adempiere.exceptions;

import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.compiere.util.CLogger;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.compiere.util.Utilerias;

/**
 * Any exception that occurs inside the Adempiere core
 * @author Teo Sarca, SC ARHIPAC SERVICE SRL
 */
public class MedsysException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	/**
	 * Default Constructor (saved logger error will be used as message)
	 */
	public MedsysException() {
		this(getMessageFromLogger());
	}
	

	/**
	 * @param message
	 */
	public MedsysException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public MedsysException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public MedsysException(String message, Throwable cause) {
		super(message, cause);
	}

	@Override
	public String getLocalizedMessage() {
		String msg = super.getLocalizedMessage();
//		msg = Msg.parseTranslation(getCtx(), msg);
		return msg;// ya se traduce al crearse
	}
	
	protected Properties getCtx() {
		return Env.getCtx();
	}


	/**
	 * @return error message from logger
	 * @see org.compiere.util.CLogger#retrieveError()
	 */
	public static String getMessageFromLogger() {
		return MedsysException.getMessageFromLogger(null);
	}
	
	/**
	 * @return error message from logger
	 * @see org.compiere.util.CLogger#retrieveError()
	 */
	public static String getMessageFromLogger(final String message) {
		org.compiere.util.ValueNamePair err = CLogger.retrieveError();
		StringBuilder msg = new StringBuilder();
		Properties ctx = Env.getCtx();
		if (err == null) {
			msg.append(Utilerias.getAppMsg(ctx, "msj.error.desconocido", "-"));
			msg.append(message==null?StringUtils.EMPTY:message);
		} else {
			if(StringUtils.isNotBlank(err.getValue())){
				msg.append(Msg.parseTranslation(ctx, err.getValue()));
			}
			if (StringUtils.isNotBlank(err.getName())) {
				if (msg.length() > 0) {
					msg.append(" - ");
				}
				msg.append(Msg.parseTranslation(ctx, err.getName()));
			}
			
			if (StringUtils.isEmpty(msg.toString())) {
				msg.append(message==null?StringUtils.EMPTY:message);
			}
		}
		return msg.toString();
	}
}
