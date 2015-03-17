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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.DisplayType;
import org.compiere.util.KeyNamePair;
import org.compiere.util.NamePair;

/**
 *	Employment Lookup Model.
 *
 *  @author 	Lorena Lama
 */
public final class MEmploymentLookup extends Lookup
	implements Serializable
{
	private static final long serialVersionUID = 1L;
	/**
	 *	Constructor
	 *  @param ctx context
	 *  @param WindowNo window no (to derive AD_Client/Org for new records)
	 */
	public MEmploymentLookup(Properties ctx, int WindowNo)
	{
		super (DisplayType.TableDir, WindowNo);
		m_ctx = ctx;
	}	//	MEXMEEmployment

	/**	Context					*/
	private Properties 		m_ctx;

	/**
	 *	Get Display for Value (not cached)
	 *  @param value employment_ID
	 *  @return String Value
	 */
	public String getDisplay (Object value)
	{
		if (value == null)
			return null;
		MEXMEEmployment loc = getEmployment (value, null);
		if (loc == null)
			return "<" + value.toString() + ">";
		return loc.toString();
	}	//	getDisplay

	/**
	 *	Get Object of Key Value
	 *  @param value value
	 *  @return Object or null
	 */
	public NamePair get (Object value)
	{
		if (value == null)
			return null;
		MEXMEEmployment loc = getEmployment (value, null);
		if (loc == null)
			return null;
		return new KeyNamePair (loc.getEXME_Employment_ID(), loc.toString());
	}	//	get

	/**
	 *  The Lookup contains the key 
	 *  @param key employment_ID
	 *  @return true if key known
	 */
	public boolean containsKey (Object key)
	{
		return getEmployment(key, null) == null;
	}   //  containsKey

	
	/**************************************************************************
	 * 	Get employment
	 * 	@param key ID as string or integer
	 *	@param trxName transaction
	 * 	@return employment
	 */
	public MEXMEEmployment getEmployment (Object key, String trxName)
	{
		if (key == null)
			return null;
		int EXME_Employment_ID = 0;
		if (key instanceof Integer)
			EXME_Employment_ID = ((Integer)key).intValue();
		else if (key != null)
			EXME_Employment_ID = Integer.parseInt(key.toString());
		//
		return getEmployment(EXME_Employment_ID, trxName);
	}	//	getEmployment
	
	/**
	 * 	Get employment
	 * 	@param EXME_Employment_ID id
	 *	@param trxName transaction
	 * 	@return employment
	 */
	public MEXMEEmployment getEmployment (int EXME_Employment_ID, String trxName)
	{
		return new MEXMEEmployment(m_ctx, EXME_Employment_ID, trxName);
	}	//	getEXME_Employment_ID

	/**
	 *	Get underlying fully qualified Table.Column Name.
	 *	Used for VLookup.actionButton (Zoom)
	 *  @return column name
	 */
	public String getColumnName()
	{
		return "EXME_Employment_ID";
	}   //  getColumnName

	/**
	 *	Return data as sorted Array - not implemented
	 *  @param mandatory mandatory
	 *  @param onlyValidated only validated
	 *  @param onlyActive only active
	 * 	@param temporary force load for temporary display
	 *  @return null
	 */
	public ArrayList<Object> getData (boolean mandatory, boolean onlyValidated, boolean onlyActive, boolean temporary)
	{
		log.log(Level.SEVERE, "not implemented");
		return null;
	}   //  getArray

}	//	MEXMEEmployment
