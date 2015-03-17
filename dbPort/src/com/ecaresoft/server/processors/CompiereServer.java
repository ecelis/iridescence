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

import java.sql.Timestamp;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.model.CompiereProcessor;
import org.compiere.model.CompiereProcessorLog;
import org.compiere.model.MAcctProcessor;
import org.compiere.model.MAlertProcessor;
import org.compiere.model.MClient;
import org.compiere.model.MIMPProcessor;
import org.compiere.model.MLdapProcessor;
import org.compiere.model.MOrgInfo;
import org.compiere.model.MProcess;
import org.compiere.model.MProcessPara;
import org.compiere.model.MRequestProcessor;
import org.compiere.model.MScheduler;
import org.compiere.model.MSchedulerPara;
import org.compiere.model.MSystem;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.TimeUtil;
import org.compiere.wf.MWorkflowProcessor;

/**
 *	Compiere Server Base
 *	
 *  @author Jorg Janke
 *  @version $Id: CompiereServer.java,v 1.2 2006/07/30 00:53:33 jjanke Exp $
 */
public abstract class CompiereServer extends Thread
{
	/**
	 * 	Create New Server Thead
	 *	@param model model
	 *	@return server tread or null
	 */
	public static CompiereServer create(CompiereProcessor model) {
		if (model instanceof MRequestProcessor) {
			return new RequestProcessor((MRequestProcessor) model);
		}
		if (model instanceof MWorkflowProcessor) {
			return new WorkflowProcessor((MWorkflowProcessor) model);
		}
		if (model instanceof MAcctProcessor) {
			return new AcctProcessor((MAcctProcessor) model);
		}
		if (model instanceof MAlertProcessor) {
			return new AlertProcessor((MAlertProcessor) model);
		}
		if (model instanceof MScheduler) {
			MScheduler mScheduler = (MScheduler) model;
			// Revisamos la hora de ejecucion
			checkTime(mScheduler);
			return new Scheduler(mScheduler);
		}
		
		throw new IllegalArgumentException("Unknown Processor");
	} // create

	/**
	 * Revisa si el proceso tiene hora programada y calcula cuanto debe dormir para ejecutarse
	 * 
	 * @param mScheduler
	 *            Proceso a revisar
	 */
	private static void checkTime(MScheduler mScheduler) {
		if (mScheduler.getHour() != null) {
			MProcess proc = mScheduler.getProcess();
			MProcessPara[] params = proc.getParameters();
			int adOrg = 0;
			for (MProcessPara param : params) {
				// Si el id es igual al de AD_Org_ID
				if (param.getAD_Element_ID() == 113) {
					MSchedulerPara[] arr = mScheduler.getParameters(true);
					for (int i = 0; i < arr.length; i++) {
						MSchedulerPara p = arr[i];
						if (p.getAD_Process_Para_ID() == param.getAD_Process_Para_ID()) {
							String org = p.getParameterDefault();
							if (org != null && org.trim().length() > 0) {
								try {
									adOrg = Integer.parseInt(org);
								} catch (NumberFormatException e) {
									log.log(Level.SEVERE, null, e);
								}
							}
							break;
						}
					}
					break;
				}
			}

			// Zona horario de la organizacion
			String orgTimeZone = MOrgInfo.get(Env.getCtx(), adOrg).getTimeZone();

			// Sacamos la hora almacenada convertida a la hora del server
			Timestamp prog = DB.convertTimeZone(mScheduler.getHour(), orgTimeZone, Env.getTimezone(Env.getCtx()));

			// Hora actual del server
			Timestamp now = new Timestamp(System.currentTimeMillis());

			// Si la fecha programada esta despues de la hora actual calculamos valor
			if (prog.after(now)) {
				mScheduler.setInitalNap((prog.getTime() - now.getTime()) / 1000);
			}
		}
	}


	/**************************************************************************
	 * 	Server Base Class
	 * 	@param model model
	 *	@param initialNap delay time running in sec
	 */
	protected CompiereServer (CompiereProcessor model, int initialNap)
	{
		super (CompiereServerGroup.get(), null, model.getName(), 0);
		p_model = model;
		m_ctx = new Properties(model.getCtx());
		if (p_system == null)
			p_system = MSystem.get(m_ctx);
		p_client = MClient.get(m_ctx);
		Env.setContext(m_ctx, "#AD_Client_ID", p_client.getAD_Client_ID());
		m_initialNap = initialNap;
	//	log.info(model.getName() + " - " + getThreadGroup());
	}	//	ServerBase

	/**	The Processor Model						*/
	protected					CompiereProcessor 	p_model;
	/** Initial nap is seconds		*/
	private int					m_initialNap = 0;
	
	/**	Miliseconds to sleep - 10 Min default	*/
	private long				m_sleepMS = 600000;
	/** Sleeping					*/
	private volatile boolean	m_sleeping = false;
	/** Server start time					*/
	private long				m_start = 0;
	/** Number of Work executions	*/
	protected int 				p_runCount = 0;
	/** Tine start of work				*/
	protected long				p_startWork = 0;
	/** Number MS of last Run		*/
	private long 				m_runLastMS = 0;
	/** Number of MS total			*/
	private long 				m_runTotalMS = 0;
	/** When to run next			*/
	private long 				m_nextWork = 0;
	
	/**	Logger						*/
	protected static CLogger	log = CLogger.getCLogger(CompiereServer.class);
	/**	Context						*/
	private Properties	m_ctx = null;
	/** System						*/
	protected static MSystem p_system = null;		
	/** Client						*/
	protected MClient	p_client = null;		
	
	/**
	 * 	Get Server Context
	 *	@return context
	 */
	public Properties getCtx()
	{
		return m_ctx;
	}	//	getCtx
	
	/**
	 * @return Returns the sleepMS.
	 */
	public long getSleepMS ()
	{
		return m_sleepMS;
	}	//	getSleepMS
	
	
	/**
	 * 	Sleep for set time
	 *	@return true if not interrupted
	 */
	public boolean sleep()
	{
		if (isInterrupted())
		{
			log.info (getName() + ": interrupted");
			return false;
		}
		log.fine(getName() + ": sleeping " + TimeUtil.formatElapsed(m_sleepMS));
		m_sleeping = true;
		try
		{
			sleep (m_sleepMS);
		}
		catch (InterruptedException e)
		{
			log.info (getName() + ": interrupted");
			m_sleeping = false;
			return false;
		}
		m_sleeping = false;
		return true;
	}	//	sleep

	/**
	 * 	Run Now
	 */
	public void runNow()
	{
		log.info(getName());
		p_startWork = System.currentTimeMillis();
		doWork();
		long now = System.currentTimeMillis();
		//	---------------
		
		p_runCount++;
		m_runLastMS = now - p_startWork;
		m_runTotalMS += m_runLastMS;
		//
		p_model.setDateLastRun(new Timestamp(now));
		p_model.save();
		//
		log.fine(getName() + ": " + getStatistics());
	}	//	runNow
	
	/**************************************************************************
	 * 	Run async
	 */
	public void run ()
	{
		try
		{
			log.fine(getName() + ": pre-nap - " + m_initialNap);
			sleep (m_initialNap * 1000);
		}
		catch (InterruptedException e)
		{
			log.log(Level.SEVERE, getName() + ": pre-nap interrupted", e);
			return;
		}
		
		m_start = System.currentTimeMillis();
		while (true)
		{
			if (m_nextWork == 0)
			{
				Timestamp dateNextRun = getDateNextRun(true);
				if (dateNextRun != null)
					m_nextWork = dateNextRun.getTime();
			}
			long now = System.currentTimeMillis();
			if (m_nextWork > now)
			{
				m_sleepMS = m_nextWork - now;
				if (!sleep ())
					break;
			}
			if (isInterrupted())
			{
				log.info (getName() + ": interrupted");
				break;
			}
			
			//	---------------
			p_startWork = System.currentTimeMillis();
			doWork();
			now = System.currentTimeMillis();
			//	---------------
			
			p_runCount++;
			m_runLastMS = now - p_startWork;
			m_runTotalMS += m_runLastMS;
			//
			m_sleepMS = calculateSleep();
			m_nextWork = now + m_sleepMS;
			//
			p_model.setDateLastRun(new Timestamp(now));
			p_model.setDateNextRun(new Timestamp(m_nextWork));
			p_model.save();
			//
			log.fine(getName() + ": " + getStatistics());
			if (!sleep())
				break;
		}
		m_start = 0;
	}	//	run

	/**
	 * 	Get Run Statistics
	 *	@return Statistic info
	 */
	public String getStatistics()
	{
		return "Run #" + p_runCount 
			+ " - Last=" + TimeUtil.formatElapsed(m_runLastMS)
			+ " - Total=" + TimeUtil.formatElapsed(m_runTotalMS)
			+ " - Next " + TimeUtil.formatElapsed(m_nextWork - System.currentTimeMillis());
	}	//	getStatistics
	
	/**
	 * 	Do the actual Work
	 */
	protected abstract void doWork();

	/**
	 * 	Get Server Info
	 *	@return info
	 */
	public abstract String getServerInfo();

	/**
	 * 	Get Unique ID
	 *	@return Unique ID
	 */
	public String getServerID()
	{
		return p_model.getServerID();
	}	//	getServerID

	/**
	 * 	Get the date Next run
	 * 	@param requery requery database
	 * 	@return date next run
	 */
	public Timestamp getDateNextRun (boolean requery)
	{
		return p_model.getDateNextRun(requery);
	}	//	getDateNextRun
	
	/**
	 * 	Get the date Last run
	 * 	@return date lext run
	 */
	public Timestamp getDateLastRun ()
	{
		return p_model.getDateLastRun();
	}	//	getDateLastRun

	/**
	 * 	Get Description
	 *	@return Description
	 */
	public String getDescription()
	{
		return p_model.getDescription();
	}	//	getDescription
	
	/**
	 * 	Get Model
	 *	@return Model
	 */
	public CompiereProcessor getModel()
	{
		return p_model;
	}	//	getModel
	
	/**
	 * 	Calculate Sleep ms
	 *	@return miliseconds
	 */
	private long calculateSleep ()
	{
		String frequencyType = p_model.getFrequencyType();
		int frequency = p_model.getFrequency();
		if (frequency < 1)
			frequency = 1;
		//
		long typeSec = 600;			//	10 minutes
		if (frequencyType == null)
			typeSec = 300;			//	5 minutes
		else if (MRequestProcessor.FREQUENCYTYPE_Minute.equals(frequencyType))
			typeSec = 60;
		else if (MRequestProcessor.FREQUENCYTYPE_Hour.equals(frequencyType))
			typeSec = 3600;
		else if (MRequestProcessor.FREQUENCYTYPE_Day.equals(frequencyType))
			typeSec = 86400;
		//
		return typeSec * 1000 * frequency;		//	ms
	}	//	calculateSleep

	/**
	 * 	Is Sleeping
	 *	@return sleeping
	 */
	public boolean isSleeping()
	{
		return m_sleeping;
	}	//	isSleeping
	
	/**
	 * 	String Representation
	 *	@return info
	 */
	public String toString ()
	{
		StringBuffer sb = new StringBuffer (getName())
			.append (",Prio=").append(getPriority())
			.append (",").append (getThreadGroup())
			.append (",Alive=").append(isAlive())
			.append (",Sleeping=").append(m_sleeping)
			.append (",Last=").append(getDateLastRun());
		if (m_sleeping)
			sb.append (",Next=").append(getDateNextRun(false));
		return sb.toString ();
	}	//	toString

	/**
	 * 	Get Seconds Alive
	 *	@return seconds alive
	 */
	public int getSecondsAlive()
	{
		if (m_start == 0)
			return 0;
		long now = System.currentTimeMillis();
		long ms = (now-m_start) / 1000;
		return (int)ms;
	}	//	getSecondsAlive
	
	/**
	 * 	Get Start Time
	 *	@return start time
	 */
	public Timestamp getStartTime()
	{
		if (m_start == 0)
			return null;
		return new Timestamp (m_start);
	}	//	getStartTime
	
	/**
	 * 	Get Processor Logs
	 *	@return logs
	 */
	public CompiereProcessorLog[] getLogs()
	{
		return p_model.getLogs();
	}	//	getLogs

}	//	CompiereServer
