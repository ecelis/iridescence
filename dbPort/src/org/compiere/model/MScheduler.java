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

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.apache.commons.lang.StringUtils;
import org.compiere.util.CLogger;
import org.compiere.util.DB;


/**
 *	Scheduler Model
 *	
 *  @author Jorg Janke
 *  @version $Id: MScheduler.java,v 1.2 2006/09/05 23:18:54 taniap Exp $
 */
public class MScheduler extends X_AD_Scheduler
	implements CompiereProcessor
{

	private static final long serialVersionUID = 251471409265504537L;
	private long initalNap = 240;
	
	public long getInitalNap() {
		return initalNap;
	}

	public void setInitalNap(long initalNap) {
		this.initalNap = initalNap;
	}

	/**
	 * 	Get Active
	 *	@param ctx context
	 * @return active processors
	 */
	public static MScheduler[] getActive(Properties ctx) {
		List<MScheduler> list = getActive(ctx, null);
		MScheduler[] retValue = new MScheduler[list.size()];
		list.toArray(retValue);
		return retValue;
	} // getActive
	
	public static List<MScheduler> getActive(Properties ctx, List<Integer> exclude) {
		List<MScheduler> list = new ArrayList<MScheduler>();
		StringBuilder sql = new StringBuilder("SELECT * FROM AD_Scheduler WHERE IsActive='Y' ");
		if (exclude != null && !exclude.isEmpty()) {
			sql.append(" and AD_Scheduler_ID not in (").append(StringUtils.join(exclude, ",")).append(") ");
		}
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				list.add(new MScheduler(ctx, rs, null));
			}

		} catch (Exception e) {
			s_log.log(Level.SEVERE, null, e);
		} finally {
			DB.close(pstmt);
			DB.close(rs);
		}
		return list;
	}

	/**	Static Logger	*/
	private static CLogger	s_log	= CLogger.getCLogger (MScheduler.class);

	
	/**
	 * 	Standard Constructor
	 *	@param ctx context
	 *	@param AD_Scheduler_ID id
	 *	@param trxName transaction
	 */
	public MScheduler (Properties ctx, int AD_Scheduler_ID, String trxName)
	{
		super (ctx, AD_Scheduler_ID, trxName);
	}	//	MScheduler

	/**
	 * 	Load Constructor
	 *	@param ctx context
	 *	@param rs result set
	 *	@param trxName transaction
	 */
	public MScheduler (Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
	}	//	MScheduler

	/**	Process Parameter			*/
	private MSchedulerPara[] m_parameter = null;
	/** Process Recipients			*/
	private MSchedulerRecipient[]	m_recipients = null;
	
	/**
	 * 	Get Server ID
	 *	@return id
	 */
	public String getServerID ()
	{
		return "Scheduler" + get_ID();
	}	//	getServerID

	/**
	 * 	Get Date Next Run
	 *	@param requery requery
	 *	@return date next run
	 */
	public Timestamp getDateNextRun (boolean requery)
	{
		if (requery)
			load(get_TrxName());
		return getDateNextRun();
	}	//	getDateNextRun

	/**
	 * 	Get Logs
	 *	@return logs
	 */
	public CompiereProcessorLog[] getLogs ()
	{
		ArrayList<MSchedulerLog> list = new ArrayList<MSchedulerLog>();
		String sql = "SELECT * "
			+ "FROM AD_SchedulerLog "
			+ "WHERE AD_Scheduler_ID=? " 
			+ "ORDER BY Created DESC";
		PreparedStatement pstmt = null;
		try
		{
			pstmt = DB.prepareStatement (sql, get_TrxName());
			pstmt.setInt (1, getAD_Scheduler_ID());
			ResultSet rs = pstmt.executeQuery ();
			while (rs.next ())
				list.add (new MSchedulerLog (getCtx(), rs, get_TrxName()));
			rs.close ();
			pstmt.close ();
			pstmt = null;
			rs = null;
		}
		catch (Exception e)
		{
			log.log(Level.SEVERE, sql, e);
		}
		try
		{
			if (pstmt != null)
				pstmt.close ();
			pstmt = null;
		}
		catch (Exception e)
		{
			pstmt = null;
		}
		MSchedulerLog[] retValue = new MSchedulerLog[list.size ()];
		list.toArray (retValue);
		return retValue;
	}	//	getLogs

	/**
	 * 	Delete old Request Log
	 *	@return number of records
	 */
	public int deleteLog()
	{
		if (getKeepLogDays() < 1)
			return 0;
		String sql = "DELETE AD_SchedulerLog "
			+ "WHERE AD_Scheduler_ID=" + getAD_Scheduler_ID() 
			+ " AND (Created+" + getKeepLogDays() + ") < SysDate";
		int no = DB.executeUpdateEx(sql, get_TrxName());
		return no;
	}	//	deleteLog

	/**
	 * 	Get Process
	 *	@return process
	 */
	public MProcess getProcess()
	{
		return MProcess.get(getCtx(), getAD_Process_ID());
	}	//	getProcess
	
	/**
	 * 	Get Parameters
	 *	@param reload reload
	 *	@return parameter
	 */
	public MSchedulerPara[] getParameters (boolean reload)
	{
		if (!reload && m_parameter != null)
			return m_parameter;
		ArrayList<MSchedulerPara> list = new ArrayList<MSchedulerPara>();
		String sql = "SELECT * FROM AD_Scheduler_Para WHERE AD_Scheduler_ID=? AND IsActive='Y'";
		PreparedStatement pstmt = null;
		try
		{
			pstmt = DB.prepareStatement (sql, null);
			pstmt.setInt (1, getAD_Scheduler_ID());
			ResultSet rs = pstmt.executeQuery ();
			while (rs.next ())
				list.add (new MSchedulerPara (getCtx(), rs, null));
			rs.close ();
			pstmt.close ();
			pstmt = null;
			rs = null;
		}
		catch (Exception e)
		{
			log.log (Level.SEVERE, sql, e);
		}
		try
		{
			if (pstmt != null)
				pstmt.close ();
			pstmt = null;
		}
		catch (Exception e)
		{
			pstmt = null;
		}
		m_parameter = new MSchedulerPara[list.size()];
		list.toArray(m_parameter);
		return m_parameter;
	}	//	getParameter
	
	/**
	 * 	Get Recipients
	 *	@param reload reload
	 *	@return Recipients
	 */
	public MSchedulerRecipient[] getRecipients (boolean reload)
	{
		if (!reload && m_recipients != null)
			return m_recipients;
		ArrayList<MSchedulerRecipient> list = new ArrayList<MSchedulerRecipient>();
		String sql = "SELECT * FROM AD_SchedulerRecipient WHERE AD_Scheduler_ID=? AND IsActive='Y'";
		PreparedStatement pstmt = null;
		try
		{
			pstmt = DB.prepareStatement (sql, null);
			pstmt.setInt (1, getAD_Scheduler_ID());
			ResultSet rs = pstmt.executeQuery ();
			while (rs.next ())
				list.add (new MSchedulerRecipient (getCtx(), rs, null));
			rs.close ();
			pstmt.close ();
			pstmt = null;
			rs=null;
		}
		catch (Exception e)
		{
			log.log (Level.SEVERE, sql, e);
		}
		try
		{
			if (pstmt != null)
				pstmt.close ();
			pstmt = null;
		}
		catch (Exception e)
		{
			pstmt = null;
		}
		m_recipients = new MSchedulerRecipient[list.size()];
		list.toArray(m_recipients);
		return m_recipients;
	}	//	getRecipients
	
	/**
	 * 	Get Recipient AD_User_IDs
	 *	@return array of user IDs
	 */
	public Integer[] getRecipientAD_User_IDs()
	{
		ArrayList<Integer> list = new ArrayList<Integer>();
		MSchedulerRecipient[] recipients = getRecipients(false);
		for (int i = 0; i < recipients.length; i++)
		{
			MSchedulerRecipient recipient = recipients[i];
			if (!recipient.isActive())
				continue;
			if (recipient.getAD_User_ID() != 0)
			{
				Integer ii = new Integer(recipient.getAD_User_ID());
				if (!list.contains(ii))
					list.add(ii);
			}
			if (recipient.getAD_Role_ID() != 0)
			{
				MUserRoles[] urs = MUserRoles.getOfRole(getCtx(), recipient.getAD_Role_ID());
				for (int j = 0; j < urs.length; j++)
				{
					MUserRoles ur = urs[j];
					if (!ur.isActive())
						continue;
					Integer ii = new Integer(ur.getAD_User_ID());
					if (!list.contains(ii))
						list.add(ii);
				}
			}
		}
		//	Add Updater
		if (list.size() == 0)
		{
			Integer ii = new Integer(getUpdatedBy());
			list.add(ii);
		}
		//
		Integer[] recipientIDs = new Integer[list.size()];
		list.toArray(recipientIDs);
		return recipientIDs;
	}	//	getRecipientAD_User_IDs
	
	/**
	 * 	String Representation
	 *	@return info
	 */
	public String toString ()
	{
		StringBuffer sb = new StringBuffer ("MScheduler[");
		sb.append (get_ID ()).append ("-").append (getName()).append ("]");
		return sb.toString ();
	}	//	toString
	
}	//	MScheduler
