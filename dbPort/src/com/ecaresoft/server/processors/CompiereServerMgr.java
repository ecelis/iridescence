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
package com.ecaresoft.server.processors;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.Compiere;
import org.compiere.model.MAcctProcessor;
import org.compiere.model.MAlertProcessor;
import org.compiere.model.MIMPProcessor;
import org.compiere.model.MLdapProcessor;
import org.compiere.model.MRequestProcessor;
import org.compiere.model.MScheduler;
import org.compiere.model.MSession;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.wf.MWorkflowProcessor;


/**
 *	Compiere Server Manager
 *
 *  @author Jorg Janke
 *  @version $Id: CompiereServerMgr.java,v 1.3 2006/07/30 00:53:33 jjanke Exp $
 */
public class CompiereServerMgr
{
	/**
	 * 	Get Compiere Server Manager
	 *	@return mgr
	 */
	public static CompiereServerMgr get()
	{
		if (m_serverMgr == null)
		{
			//	for faster subsequent calls
			m_serverMgr = new CompiereServerMgr();
			m_serverMgr.startServers();
			m_serverMgr.log.info(m_serverMgr.toString());
		}
		return m_serverMgr;
	}	//	get

	/**	Singleton					*/
	private static	CompiereServerMgr	m_serverMgr = null;
	/**	Logger			*/
	protected CLogger	log = CLogger.getCLogger(getClass());

	/**************************************************************************
	 * 	Compiere Server Manager
	 */
	private CompiereServerMgr ()
	{
		super();
		startEnvironment();
	//	m_serverMgr.startServers();
	}	//	CompiereServerMgr

	/**	The Servers				*/
	private ArrayList<CompiereServer>	m_servers = new ArrayList<CompiereServer>();
	/** Context					*/
	private Properties		m_ctx = Env.getCtx();
	/** Start					*/
	private Timestamp		m_start = new Timestamp(System.currentTimeMillis());

	/**
	 * 	Start Environment
	 *	@return true if started
	 */
	private boolean startEnvironment()
	{
		Compiere.startup(false);
		log.info("");

		//	Set Session
		InetAddress lh;
		String hostName = null;
		try {
			lh = InetAddress.getLocalHost();
			hostName = lh.getHostName();
		} catch (UnknownHostException e) {
			hostName = "Server";
		}

		/*String sql =  "Update AD_Session " +
					  "Set Processed = 'Y' " +
					  "WHERE CreatedBy = 0 " +
					  "And WebSession = 'Server' " +
					  "And Remote_Host = ? ";*/

		//TODO : como se manejara el control de usuarios?
		// Clear Sessions
		String sql = "UPDATE AD_Session SET Processed = 'Y' WHERE Processed = 'N'";
		DB.executeUpdate(sql, true, null);


		MSession session = MSession.get(getCtx(), true);
		session.setWebStoreSession(false);
		session.setWebSession("Server");
		session.save();
		//
		return true;
	}	//	startEnvironment

	/**
	 * 	Start Environment
	 *	@return true if started
	 */
	private boolean startServers()
	{
		log.info("");
		int noServers = 0;
		//	Accounting
		MAcctProcessor[] acctModels = MAcctProcessor.getActive(m_ctx);
		for (int i = 0; i < acctModels.length; i++)
		{
			MAcctProcessor pModel = acctModels[i];
			CompiereServer server = CompiereServer.create(pModel);
			server.start();
			server.setPriority(Thread.NORM_PRIORITY-2);
			m_servers.add(server);
		}
		//	Request
		MRequestProcessor[] requestModels = MRequestProcessor.getActive(m_ctx);
		for (int i = 0; i < requestModels.length; i++)
		{
			MRequestProcessor pModel = requestModels[i];
			CompiereServer server = CompiereServer.create(pModel);
			server.start();
			server.setPriority(Thread.NORM_PRIORITY-2);
			m_servers.add(server);
		}
		//	Workflow
		MWorkflowProcessor[] workflowModels = MWorkflowProcessor.getActive(m_ctx);
		for (int i = 0; i < workflowModels.length; i++)
		{
			MWorkflowProcessor pModel = workflowModels[i];
			CompiereServer server = CompiereServer.create(pModel);
			server.start();
			server.setPriority(Thread.NORM_PRIORITY-2);
			m_servers.add(server);
		}
		//	Alert
		MAlertProcessor[] alertModels = MAlertProcessor.getActive(m_ctx);
		for (int i = 0; i < alertModels.length; i++)
		{
			MAlertProcessor pModel = alertModels[i];
			CompiereServer server = CompiereServer.create(pModel);
			server.start();
			server.setPriority(Thread.NORM_PRIORITY-2);
			m_servers.add(server);
		}
		//	Scheduler
		MScheduler[] schedulerModels = MScheduler.getActive(m_ctx);
		for (int i = 0; i < schedulerModels.length; i++)
		{
			MScheduler pModel = schedulerModels[i];
			CompiereServer server = CompiereServer.create(pModel);
			server.start();
			server.setPriority(Thread.NORM_PRIORITY-2);
			m_servers.add(server);
		}
		//	LDAP
		MLdapProcessor[] ldapModels = MLdapProcessor.getActive(m_ctx);
		for (int i = 0; i < ldapModels.length; i++)
		{
			MLdapProcessor lp = ldapModels[i];
			CompiereServer server = CompiereServer.create(lp);
			server.start();
			server.setPriority(Thread.NORM_PRIORITY-1);
			m_servers.add(server);
		}
		//	ImportProcessor - @Trifon
		MIMPProcessor[] importModels = MIMPProcessor.getActive(m_ctx);
		for (int i = 0; i < importModels.length; i++)
		{
			MIMPProcessor lp = importModels[i];
			CompiereServer server = CompiereServer.create(lp);
			server.start();
			server.setPriority(Thread.NORM_PRIORITY-1);
			m_servers.add(server);
		}

		log.fine("#" + noServers);
		return startAll();
	}	//	startEnvironment

	/**
	 * 	Get Server Context
	 *	@return ctx
	 */
	public Properties getCtx()
	{
		return m_ctx;
	}	//	getCtx

	/**
	 * 	Start all servers
	 *	@return true if started
	 */
	public boolean startAll()
	{
		log.info ("");
		CompiereServer[] servers = getInActive();
		for (int i = 0; i < servers.length; i++)
		{
			CompiereServer server = servers[i];
			try
			{
				if (server.isAlive())
					continue;
				//	Wait until dead
				if (server.isInterrupted())
				{
					int maxWait = 10;	//	10 iterations = 1 sec
					while (server.isAlive())
					{
						if (maxWait-- == 0)
						{
							log.severe ("Wait timeout for interruped " + server);
							break;
						}
						try
						{
							Thread.sleep(100);		//	1/10 sec
						}
						catch (InterruptedException e)
						{
							log.log(Level.SEVERE, "While sleeping", e);
						}
					}
				}
				//	Do start
				if (!server.isAlive())
				{
					//	replace
					server = CompiereServer.create (server.getModel());
					if (server == null)
						m_servers.remove(i);
					else
						m_servers.set(i, server);
					server.start();
					server.setPriority(Thread.NORM_PRIORITY-2);
				}
			}
			catch (Exception e)
			{
				log.log(Level.SEVERE, "Server: " + server, e);
			}
		}	//	for all servers

		//	Final Check
		int noRunning = 0;
		int noStopped = 0;
		for (int i = 0; i < servers.length; i++)
		{
			CompiereServer server = servers[i];
			try
			{
				if (server.isAlive())
				{
					log.info("Alive: " + server);
					noRunning++;
				}
				else
				{
					log.warning("Dead: " + server);
					noStopped++;
				}
			}
			catch (Exception e)
			{
				log.log(Level.SEVERE, "(checking) - " + server, e);
				noStopped++;
			}
		}
		log.fine("Running=" + noRunning + ", Stopped=" + noStopped);
		CompiereServerGroup.get().dump();
		return noStopped == 0;
	}	//	startAll

	/**
	 * 	Start Server if not started yet
	 * 	@param serverID server ID
	 *	@return true if started
	 */
	public boolean start (String serverID)
	{
		CompiereServer server = getServer(serverID);
		if (server == null)
			return false;
		if (server.isAlive())
			return true;

		try
		{
			//	replace
			int index = m_servers.indexOf(server);
			server = CompiereServer.create (server.getModel());
			if (server == null)
				m_servers.remove(index);
			else
				m_servers.set(index, server);
			server.start();
			server.setPriority(Thread.NORM_PRIORITY-2);
			Thread.yield();
		}
		catch (Exception e)
		{
			log.log(Level.SEVERE, "Server=" + serverID, e);
			return false;
		}
		log.info(server.toString());
		CompiereServerGroup.get().dump();
		if (server == null)
			return false;
		return server.isAlive();
	}	//	startIt

	/**
	 * 	Stop all Servers
	 *	@return true if stopped
	 */
	public boolean stopAll()
	{
		log.info ("");
		CompiereServer[] servers = getActive();
		//	Interrupt
		for (int i = 0; i < servers.length; i++)
		{
			CompiereServer server = servers[i];
			try
			{
				if (server.isAlive() && !server.isInterrupted())
				{
					server.setPriority(Thread.MAX_PRIORITY-1);
					server.interrupt();
				}
			}
			catch (Exception e)
			{
				log.log(Level.SEVERE, "(interrupting) - " + server, e);
			}
		}	//	for all servers
		Thread.yield();

		//	Wait for death
		for (int i = 0; i < servers.length; i++)
		{
			CompiereServer server = servers[i];
			try
			{
				int maxWait = 10;	//	10 iterations = 1 sec
				while (server.isAlive())
				{
					if (maxWait-- == 0)
					{
						log.severe ("Wait timeout for interruped " + server);
						break;
					}
					Thread.sleep(100);		//	1/10
				}
			}
			catch (Exception e)
			{
				log.log(Level.SEVERE, "(waiting) - " + server, e);
			}
		}	//	for all servers

		//	Final Check
		int noRunning = 0;
		int noStopped = 0;
		for (int i = 0; i < servers.length; i++)
		{
			CompiereServer server = servers[i];
			try
			{
				if (server.isAlive())
				{
					log.warning ("Alive: " + server);
					noRunning++;
				}
				else
				{
					log.info ("Stopped: " + server);
					noStopped++;
				}
			}
			catch (Exception e)
			{
				log.log(Level.SEVERE, "(checking) - " + server, e);
				noRunning++;
			}
		}
		log.fine("Running=" + noRunning + ", Stopped=" + noStopped);
		CompiereServerGroup.get().dump();
		return noRunning == 0;
	}	//	stopAll

	/**
	 * 	Stop Server if not stopped
	 * 	@param serverID server ID
	 *	@return true if interrupted
	 */
	public boolean stop (String serverID)
	{
		CompiereServer server = getServer(serverID);
		if (server == null)
			return false;
		if (!server.isAlive())
			return true;

		try
		{
			server.interrupt();
			Thread.sleep(10);	//	1/100 sec
		}
		catch (Exception e)
		{
			log.log(Level.SEVERE, "stop", e);
			return false;
		}
		log.info(server.toString());
		CompiereServerGroup.get().dump();
		return !server.isAlive();
	}	//	stop


	/**
	 * 	Destroy
	 */
	public void destroy ()
	{
		log.info ("");
		stopAll();
		m_servers.clear();
	}	//	destroy

	/**
	 * 	Get Active Servers
	 *	@return array of active servers
	 */
	protected CompiereServer[] getActive()
	{
		ArrayList<CompiereServer> list = new ArrayList<CompiereServer>();
		for (int i = 0; i < m_servers.size(); i++)
		{
			CompiereServer server = (CompiereServer)m_servers.get(i);
			if (server != null && server.isAlive() && !server.isInterrupted())
				list.add (server);
		}
		CompiereServer[] retValue = new CompiereServer[list.size ()];
		list.toArray (retValue);
		return retValue;
	}	//	getActive

	/**
	 * 	Get InActive Servers
	 *	@return array of inactive servers
	 */
	protected CompiereServer[] getInActive()
	{
		ArrayList<CompiereServer> list = new ArrayList<CompiereServer>();
		for (int i = 0; i < m_servers.size(); i++)
		{
			CompiereServer server = (CompiereServer)m_servers.get(i);
			if (server != null && (!server.isAlive() || !server.isInterrupted()))
				list.add (server);
		}
		CompiereServer[] retValue = new CompiereServer[list.size()];
		list.toArray (retValue);
		return retValue;
	}	//	getInActive

	/**
	 * 	Get all Servers
	 *	@return array of servers
	 */
	public CompiereServer[] getAll()
	{
		CompiereServer[] retValue = new CompiereServer[m_servers.size()];
		m_servers.toArray (retValue);
		return retValue;
	}	//	getAll

	/**
	 * 	Get Server with ID
	 *	@param serverID server id
	 *	@return server or null
	 */
	public CompiereServer getServer (String serverID)
	{
		if (serverID == null)
			return null;
		for (int i = 0; i < m_servers.size(); i++)
		{
			CompiereServer server = (CompiereServer)m_servers.get(i);
			if (serverID.equals(server.getServerID()))
				return server;
		}
		return null;
	}	//	getServer

	/**
	 * 	String Representation
	 *	@return info
	 */
	public String toString ()
	{
		StringBuffer sb = new StringBuffer ("CompiereServerMgr[");
		sb.append("Servers=").append(m_servers.size())
			.append(",ContextSize=").append(m_ctx.size())
			.append(",Started=").append(m_start)
			.append ("]");
		return sb.toString ();
	}	//	toString

	/**
	 * 	Get Description
	 *	@return description
	 */
	public String getDescription()
	{
		return "$Revision: 1.4 $";
	}	//	getDescription

	/**
	 * 	Get Number Servers
	 *	@return no of servers
	 */
	public String getServerCount()
	{
		int noRunning = 0;
		int noStopped = 0;
		for (int i = 0; i < m_servers.size(); i++)
		{
			CompiereServer server = (CompiereServer)m_servers.get(i);
			if (server.isAlive())
				noRunning++;
			else
				noStopped++;
		}
		String info = String.valueOf(m_servers.size())
			+ " - Running=" + noRunning
			+ " - Stopped=" + noStopped;
		return info;
	}	//	getServerCount

	/**
	 * 	Get start date
	 *	@return start date
	 */
	public Timestamp getStartTime()
	{
		return m_start;
	}	//	getStartTime

	public void add(CompiereServer sever) {
		m_servers.add(sever);
	}

}	//	CompiereServerMgr
