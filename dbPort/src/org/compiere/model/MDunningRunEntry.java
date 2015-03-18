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
import java.util.*;
import org.compiere.util.*;

/**
 *	Dunning Run Entry Model
 *	
 *  @author Jorg Janke
 *  @version $Id: MDunningRunEntry.java,v 1.2 2006/07/30 00:51:05 jjanke Exp $
 */
public class MDunningRunEntry extends X_C_DunningRunEntry
{
	/**
	 * 	Standard Constructor
	 *	@param ctx context
	 *	@param C_DunningRunEntry_ID id
	 *	@param trxName transaction
	 */
	public MDunningRunEntry (Properties ctx, int C_DunningRunEntry_ID, String trxName)
	{
		super (ctx, C_DunningRunEntry_ID, trxName);
		if (C_DunningRunEntry_ID == 0)
		{
		//	setC_BPartner_ID (0);
		//	setC_BPartner_Location_ID (0);
		//	setAD_User_ID (0);
			
		//	setSalesRep_ID (0);
		//	setC_Currency_ID (0);
			setAmt (Env.ZERO);
			setQty (Env.ZERO);
			setProcessed (false);
		}
	}	//	MDunningRunEntry

	/**
	 * 	Load Constructor
	 *	@param ctx context
	 *	@param rs result set
	 *	@param trxName transaction
	 */
	public MDunningRunEntry (Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
	}	//	MDunningRunEntry
	
	/**
	 * 	Parent Constructor
	 *	@param parent parent 
	 */
	public MDunningRunEntry (MDunningRun parent)
	{
		this (parent.getCtx(), 0, parent.get_TrxName());
		setClientOrg(parent);
		setC_DunningRun_ID(parent.getC_DunningRun_ID());
		m_parent = parent;
	}	//	MDunningRunEntry

	/** Parent				*/
	private MDunningRun		m_parent = null;
	
	/**
	 * 	Set BPartner
	 *	@param bp partner
	 *	@param isSOTrx SO
	 */
	public void setBPartner (MBPartner bp, boolean isSOTrx)
	{
		setC_BPartner_ID(bp.getC_BPartner_ID());
		MBPartnerLocation[] locations = bp.getLocations(false);
		//	Location
		if (locations.length == 1)
			setC_BPartner_Location_ID (locations[0].getC_BPartner_Location_ID());
		else
		{
			for (int i = 0; i < locations.length; i++)
			{
				MBPartnerLocation location = locations[i];
				if ((location.isPayFrom() && isSOTrx)
					|| (location.isRemitTo() && !isSOTrx))
				{
					setC_BPartner_Location_ID (location.getC_BPartner_Location_ID());
					break;
				}
			}
		}
		if (getC_BPartner_Location_ID() == 0)
		{
			String msg = "@C_BPartner_ID@ " + bp.getName();
			if (isSOTrx)
				msg += " @No@ @IsPayFrom@";
			else
				msg += " @No@ @IsRemitTo@";
			throw new IllegalArgumentException (msg);
		}
		//	User with location
		MUser[] users = MUser.getOfBPartner(getCtx(), bp.getC_BPartner_ID());
		if (users.length == 1)
			setAD_User_ID (users[0].getAD_User_ID());
		else
		{
			for (int i = 0; i < users.length; i++)
			{
				MUser user = users[i];
				if (user.getC_BPartner_Location_ID() == getC_BPartner_Location_ID())
				{
					setAD_User_ID (users[i].getAD_User_ID());
					break;
				}
			}
		}
		//
		int SalesRep_ID = bp.getSalesRep_ID();
		if (SalesRep_ID != 0)
			setSalesRep_ID (SalesRep_ID);
	}	//	setBPartner
	
}	//	MDunningRunEntry