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

import java.math.*;
import java.sql.*;
import java.util.*;
import java.util.logging.*;

import org.compiere.apps.graph.GraphColumn;
import org.compiere.util.*;

/**
 * 	Performance Measure
 *	
 *  @author Jorg Janke
 *  @version $Id: MMeasure.java,v 1.2 2006/07/30 00:51:05 jjanke Exp $
 */
public class MMeasure extends X_PA_Measure
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6274990637485210675L;

	/**
	 * 	Get MMeasure from Cache
	 *	@param ctx context
	 *	@param PA_Measure_ID id
	 *	@return MMeasure
	 */
	public static MMeasure get (Properties ctx, int PA_Measure_ID)
	{
		Integer key = new Integer (PA_Measure_ID);
		MMeasure retValue = (MMeasure)s_cache.get (key);
		if (retValue != null 
				//Expert : Lama (verificar la sesion, para usar o no el objeto del cache)
				&& (Env.getContextAsInt(ctx, "#AD_Session_ID")==Env.getContextAsInt(retValue.getCtx(), "#AD_Session_ID"))) //lama
			return retValue;
		retValue = new MMeasure (ctx, PA_Measure_ID, null);
		if (retValue.get_ID() != 0)
			s_cache.put (key, retValue);
		return retValue;
	} //	get

	/**	Cache						*/
	private static CCache<Integer, MMeasure> s_cache 
	= new CCache<Integer, MMeasure> ("PA_Measure", 10);

	/**
	 * 	Standard Constructor
	 *	@param ctx context
	 *	@param PA_Measure_ID id
	 *	@param trxName trx
	 */
	public MMeasure (Properties ctx, int PA_Measure_ID, String trxName)
	{
		super (ctx, PA_Measure_ID, trxName);
	}	//	MMeasure

	/**
	 * 	Load Constructor
	 *	@param ctx context
	 *	@param rs result set
	 *	@param trxName trx
	 */
	public MMeasure (Properties ctx, ResultSet rs, String trxName)
	{
		super (ctx, rs, trxName);
	}	//	MMeasure

	public ArrayList<GraphColumn> getGraphColumnList(MGoal goal)
	{
		ArrayList<GraphColumn> list = new ArrayList<GraphColumn>();
		if (MMeasure.MEASURETYPE_Calculated.equals(getMeasureType()))
		{
			MMeasureCalc mc = MMeasureCalc.get(getCtx(), getPA_MeasureCalc_ID());
			String sql = mc.getSqlBarChart(goal.getRestrictions(false),
					goal.getMeasureDisplay(), goal.getDateFrom(),
					MRole.getDefault());	//	logged in role
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try
			{
				pstmt = DB.prepareStatement (sql, null);
				rs = pstmt.executeQuery ();
				ArrayList<Timestamp> dataList = new ArrayList<Timestamp>();
				while (rs.next ())
				{
					BigDecimal data = rs.getBigDecimal(1);
					Timestamp date = rs.getTimestamp(2);
					GraphColumn bgc = new GraphColumn(mc, data);
					bgc.setLabel(date, goal.getMeasureDisplay()); //TODO copy order-loop to other measures
					int pos=0;
					for (int i = 0; i <  dataList.size(); i++)
						if (dataList.get(i).before(date)) pos++;
					dataList.add(date); // list of dates
					list.add(pos, bgc);
				}
			}
			catch (Exception e)
			{
				log.log (Level.SEVERE, sql, e);
			}
			finally
			{
				DB.close(rs, pstmt);
				rs = null; pstmt = null;
			}
		}
		else if (MMeasure.MEASURETYPE_Achievements.equals(getMeasureType()))
		{
			if (MMeasure.MEASUREDATATYPE_StatusQtyAmount.equals(getMeasureDataType()))
			{
				MAchievement[] achievements = MAchievement.get(this);
				for (int i = 0; i < achievements.length; i++)
				{
					MAchievement achievement = achievements[i];
					GraphColumn bgc = new GraphColumn(achievement);
					list.add(bgc);
				}
			}
			else	//	MMeasure.MEASUREDATATYPE_QtyAmountInTime
			{
				String MeasureDisplay = goal.getMeasureDisplay();
				String trunc = null;
				if (DB.isOracle()) {
					trunc = "D";
					if (MGoal.MEASUREDISPLAY_Year.equals(MeasureDisplay)) {
						trunc = "Y";
					} else if (MGoal.MEASUREDISPLAY_Quarter.equals(MeasureDisplay)) {
						trunc = "Q";
					} else if (MGoal.MEASUREDISPLAY_Month.equals(MeasureDisplay)) {
						trunc = "MM";
					} else if (MGoal.MEASUREDISPLAY_Week.equals(MeasureDisplay)) {
						trunc = "W";
					}					
					trunc = "TRUNC(DateDoc,'" + trunc + "')";
				} else if (DB.isPostgreSQL()) {
					trunc = "day";
					if (MGoal.MEASUREDISPLAY_Year.equals(MeasureDisplay)) {
						trunc = "year";
					} else if (MGoal.MEASUREDISPLAY_Quarter.equals(MeasureDisplay)) {
						trunc = "quarter";
					} else if (MGoal.MEASUREDISPLAY_Month.equals(MeasureDisplay)) {
						trunc = "month";
					} else if (MGoal.MEASUREDISPLAY_Week.equals(MeasureDisplay)) {
						trunc = "week";
					}
					trunc = "DATE_TRUNC('" + trunc + "', DateDoc)";
				}				
				
				StringBuffer sql = new StringBuffer ("SELECT SUM(ManualActual), ")
				.append(trunc).append(" FROM PA_Achievement WHERE PA_Measure_ID=? AND IsAchieved='Y' ")
				.append("GROUP BY ").append(trunc)
				.append(" ORDER BY ").append(trunc);
				PreparedStatement pstmt = null;
				ResultSet rs = null;
				try
				{
					pstmt = DB.prepareStatement (sql.toString(), null);
					pstmt.setInt(1, getPA_Measure_ID());
					rs = pstmt.executeQuery ();
					while (rs.next ())
					{
						BigDecimal data = rs.getBigDecimal(1);
						Timestamp date = rs.getTimestamp(2);
						GraphColumn bgc = new GraphColumn(goal, data);
						bgc.setLabel(date, goal.getMeasureDisplay());
						list.add(bgc);
					}
				}
				catch (Exception e)
				{
					log.log (Level.SEVERE, sql.toString(), e);
				}
				finally
				{
					DB.close(rs, pstmt);
					rs = null; pstmt = null;
				}
			}	//	Achievement in time
		}	//	Achievement

		//	Request
		else if (MMeasure.MEASURETYPE_Request.equals(getMeasureType()))
		{
			MRequestType rt = MRequestType.get(Env.getCtx(), getR_RequestType_ID());
			String sql = rt.getSqlBarChart(goal.getRestrictions(false),
					goal.getMeasureDisplay(), getMeasureDataType(),
					goal.getDateFrom(), MRole.getDefault());	//	logged in role
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try
			{
				pstmt = DB.prepareStatement (sql, null);
				rs = pstmt.executeQuery ();
				while (rs.next ())
				{
					BigDecimal data = rs.getBigDecimal(1);
					int R_Status_ID = rs.getInt(3);
					GraphColumn bgc = new GraphColumn(rt, data, R_Status_ID);
					if (R_Status_ID == 0)
					{
						Timestamp date = rs.getTimestamp(2);
						bgc.setLabel(date, goal.getMeasureDisplay());
					}
					else
					{
						MStatus status = MStatus.get(Env.getCtx(), R_Status_ID);
						bgc.setLabel(status.getName());
					}
					list.add(bgc);
				}
			}
			catch (Exception e)
			{
				log.log (Level.SEVERE, sql, e);
			}
			finally
			{
				DB.close(rs, pstmt);
				rs = null; pstmt = null;
			}
		}	//	Request

		//	Project
		else if (MMeasure.MEASURETYPE_Project.equals(getMeasureType()))
		{
			MProjectType pt = MProjectType.get(Env.getCtx(), getC_ProjectType_ID());
			String sql = pt.getSqlBarChart(goal.getRestrictions(false),
					goal.getMeasureDisplay(), getMeasureDataType(),
					goal.getDateFrom(), MRole.getDefault());	//	logged in role
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try
			{
				pstmt = DB.prepareStatement (sql, null);
				rs = pstmt.executeQuery ();
				while (rs.next ())
				{
					BigDecimal data = rs.getBigDecimal(1);
					Timestamp date = rs.getTimestamp(2);
					int id = rs.getInt(3);
					GraphColumn bgc = new GraphColumn(pt, data, id);
					bgc.setLabel(date, goal.getMeasureDisplay());
					list.add(bgc);
				}
			}
			catch (Exception e)
			{
				log.log (Level.SEVERE, sql, e);
			}
			finally
			{
				DB.close(rs, pstmt);
				rs = null; pstmt = null;
			}
		}	//	Project

		return list;
	}

	/**
	 * 	String Representation
	 *	@return info
	 */
	public String toString ()
	{
		StringBuffer sb = new StringBuffer ("MMeasure[");
		sb.append (get_ID()).append ("-").append (getName()).append ("]");
		return sb.toString ();
	}	//	toString

	/**
	 * 	Before Save
	 *	@param newRecord new
	 *	@return true
	 */
	protected boolean beforeSave (boolean newRecord)
	{
		if (MEASURETYPE_Calculated.equals(getMeasureType())
				&& getPA_MeasureCalc_ID() == 0)
		{
			log.saveError("FillMandatory", Msg.getElement(getCtx(), "PA_MeasureCalc_ID"));
			return false;
		}
		else if (MEASURETYPE_Ratio.equals(getMeasureType())
				&& getPA_Ratio_ID() == 0)
		{
			log.saveError("FillMandatory", Msg.getElement(getCtx(), "PA_Ratio_ID"));
			return false;
		}
		else if (MEASURETYPE_UserDefined.equals(getMeasureType())
				&& (getCalculationClass() == null || getCalculationClass().length()==0))
		{
			log.saveError("FillMandatory", Msg.getElement(getCtx(), "CalculationClass"));
			return false;
		}
		else if (MEASURETYPE_Request.equals(getMeasureType())
				&& getR_RequestType_ID() == 0)
		{
			log.saveError("FillMandatory", Msg.getElement(getCtx(), "R_RequestType_ID"));
			return false;
		}
		else if (MEASURETYPE_Project.equals(getMeasureType())
				&& getC_ProjectType_ID() == 0)
		{
			log.saveError("FillMandatory", Msg.getElement(getCtx(), "C_ProjectType_ID"));
			return false;
		}
		return true;
	}	//	beforeSave

	/**
	 * 	After Save
	 *	@param newRecord new
	 *	@param success success
	 *	@return succes
	 */
	protected boolean afterSave (boolean newRecord, boolean success)
	{
		//	Update Goals with Manual Measure
		if (success && MEASURETYPE_Manual.equals(getMeasureType()))
			updateManualGoals();

		return success;
	}	//	afterSave

	/**
	 * 	Update/save Goals
	 * 	@return true if updated
	 */
	public boolean updateGoals()
	{
		String mt = getMeasureType();
		try
		{
			if (MEASURETYPE_Manual.equals(mt))
				return updateManualGoals();
			else if (MEASURETYPE_Achievements.equals(mt))
				return updateAchievementGoals();
			else if (MEASURETYPE_Calculated.equals(mt))
				return updateCalculatedGoals();
			else if (MEASURETYPE_Ratio.equals(mt))
				return updateRatios();
			else if (MEASURETYPE_Request.equals(mt))
				return updateRequests();
			else if (MEASURETYPE_Project.equals(mt))
				return updateProjects();
			//	Projects
		}
		catch (Exception e)
		{
			log.log(Level.SEVERE, "MeasureType=" + mt, e);
		}
		return false;
	}	//	updateGoals

	/**
	 * 	Update/save Manual Goals
	 * 	@return true if updated
	 */
	private boolean updateManualGoals()
	{
		if (!MEASURETYPE_Manual.equals(getMeasureType()))
			return false;
		MGoal[] goals = MGoal.getMeasureGoals (getCtx(), getPA_Measure_ID());
		for (int i = 0; i < goals.length; i++)
		{
			MGoal goal = goals[i];
			goal.setMeasureActual(getManualActual());
			goal.save();
		}
		return true;
	}	//	updateManualGoals

	/**
	 * 	Update/save Goals with Achievement
	 * 	@return true if updated
	 */
	private boolean updateAchievementGoals()
	{
		if (!MEASURETYPE_Achievements.equals(getMeasureType()))
			return false;
		Timestamp today = DB.getTimestampForOrg(getCtx());
		MGoal[] goals = MGoal.getMeasureGoals (getCtx(), getPA_Measure_ID());
		for (int i = 0; i < goals.length; i++)
		{
			MGoal goal = goals[i];
			String MeasureScope = goal.getMeasureScope();
			String trunc = TimeUtil.TRUNC_DAY;
			if (MGoal.MEASUREDISPLAY_Year.equals(MeasureScope)) {
				trunc = TimeUtil.TRUNC_YEAR;
			} else if (MGoal.MEASUREDISPLAY_Quarter.equals(MeasureScope)) {
				trunc = TimeUtil.TRUNC_QUARTER;
			} else if (MGoal.MEASUREDISPLAY_Month.equals(MeasureScope)) {
				trunc = TimeUtil.TRUNC_MONTH;
			} else if (MGoal.MEASUREDISPLAY_Week.equals(MeasureScope)) {
				trunc = TimeUtil.TRUNC_WEEK;
			}
			Timestamp compare = TimeUtil.trunc(today, trunc); 
			//
			MAchievement[] achievements = MAchievement.getOfMeasure(getCtx(), getPA_Measure_ID());
			BigDecimal ManualActual = Env.ZERO;
			for (int j = 0; j < achievements.length; j++)
			{
				MAchievement achievement = achievements[j];
				if (achievement.isAchieved() && achievement.getDateDoc() != null)
				{
					Timestamp ach = TimeUtil.trunc(achievement.getDateDoc(), trunc);
					if (compare.equals(ach))
						ManualActual = ManualActual.add(achievement.getManualActual());
				}
			}
			goal.setMeasureActual(ManualActual);
			goal.save();
		}
		return true;
	}	//	updateAchievementGoals

	/**
	 * 	Update/save Goals with Calculation
	 * 	@return true if updated
	 */
	private boolean updateCalculatedGoals()
	{
		if (!MEASURETYPE_Calculated.equals(getMeasureType()))
			return false;
		MGoal[] goals = MGoal.getMeasureGoals (getCtx(), getPA_Measure_ID());
		for (int i = 0; i < goals.length; i++)
		{
			MGoal goal = goals[i];
			//	Find Role
			MRole role = null;
			if (goal.getAD_Role_ID() != 0)
				role = MRole.get(getCtx(), goal.getAD_Role_ID());
			else if (goal.getAD_User_ID() != 0)
			{
				MUser user = MUser.get(getCtx(), goal.getAD_User_ID());
				MRole[] roles = user.getRoles(goal.getAD_Org_ID());
				if (roles.length > 0)
					role = roles[0];
			}
			if (role == null)
				role = MRole.getDefault(getCtx(), false);	//	could result in wrong data
			//
			MMeasureCalc mc = MMeasureCalc.get(getCtx(), getPA_MeasureCalc_ID());
			if (mc == null || mc.get_ID() == 0 || mc.get_ID() != getPA_MeasureCalc_ID())
			{
				log.log(Level.SEVERE, "Not found PA_MeasureCalc_ID=" + getPA_MeasureCalc_ID());
				return false;
			}
			BigDecimal ManualActual = null;
			String sql = mc.getSqlPI(goal.getRestrictions(false), 
					goal.getMeasureScope(), getMeasureDataType(), null, role);		
			PreparedStatement pstmt = null;
			try		//	SQL statement could be wrong
			{
				pstmt = DB.prepareStatement (sql, null);
				ResultSet rs = pstmt.executeQuery();
				if (rs.next())
					ManualActual = rs.getBigDecimal(1);
				rs.close ();
				pstmt.close ();
				pstmt = null;
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
			//	SQL may return no rows or null
			if (ManualActual == null)
			{
				ManualActual = Env.ZERO;
				log.fine("No Value = " + sql);
			}
			goal.setMeasureActual(ManualActual);
			goal.save();
		}
		return true;
	}	//	updateCalculatedGoals

	/**
	 * 	Update/save Goals with Ratios
	 * 	@return true if updated
	 */
	private boolean updateRatios()
	{
		if (!MEASURETYPE_Ratio.equals(getMeasureType()))
			return false;
		return false;
	}		//	updateRatios

	/**
	 * 	Update/save Goals with Requests
	 * 	@return true if updated
	 */
	private boolean updateRequests()
	{
		if (!MEASURETYPE_Request.equals(getMeasureType())
				|| getR_RequestType_ID() == 0)
			return false;
		MGoal[] goals = MGoal.getMeasureGoals (getCtx(), getPA_Measure_ID());
		for (int i = 0; i < goals.length; i++)
		{
			MGoal goal = goals[i];
			//	Find Role
			MRole role = null;
			if (goal.getAD_Role_ID() != 0)
				role = MRole.get(getCtx(), goal.getAD_Role_ID());
			else if (goal.getAD_User_ID() != 0)
			{
				MUser user = MUser.get(getCtx(), goal.getAD_User_ID());
				MRole[] roles = user.getRoles(goal.getAD_Org_ID());
				if (roles.length > 0)
					role = roles[0];
			}
			if (role == null)
				role = MRole.getDefault(getCtx(), false);	//	could result in wrong data
			//
			BigDecimal ManualActual = null;
			MRequestType rt = MRequestType.get(getCtx(), getR_RequestType_ID());
			String sql = rt.getSqlPI(goal.getRestrictions(false), 
					goal.getMeasureScope(), getMeasureDataType(), null, role);		
			PreparedStatement pstmt = null;
			try		//	SQL statement could be wrong
			{
				pstmt = DB.prepareStatement (sql, null);
				ResultSet rs = pstmt.executeQuery();
				if (rs.next())
					ManualActual = rs.getBigDecimal(1);
				rs.close ();
				pstmt.close ();
				pstmt = null;
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
			//	SQL may return no rows or null
			if (ManualActual == null)
			{
				ManualActual = Env.ZERO;
				log.fine("No Value = " + sql);
			}
			goal.setMeasureActual(ManualActual);
			goal.save();
		}
		return true;
	}		//	updateRequests

	/**
	 * 	Update/save Goals with Projects
	 * 	@return true if updated
	 */
	private boolean updateProjects()
	{
		if (!MEASURETYPE_Project.equals(getMeasureType())
				|| getC_ProjectType_ID() == 0)
			return false;
		MGoal[] goals = MGoal.getMeasureGoals (getCtx(), getPA_Measure_ID());
		for (int i = 0; i < goals.length; i++)
		{
			MGoal goal = goals[i];
			//	Find Role
			MRole role = null;
			if (goal.getAD_Role_ID() != 0)
				role = MRole.get(getCtx(), goal.getAD_Role_ID());
			else if (goal.getAD_User_ID() != 0)
			{
				MUser user = MUser.get(getCtx(), goal.getAD_User_ID());
				MRole[] roles = user.getRoles(goal.getAD_Org_ID());
				if (roles.length > 0)
					role = roles[0];
			}
			if (role == null)
				role = MRole.getDefault(getCtx(), false);	//	could result in wrong data
			//
			BigDecimal ManualActual = null;
			MProjectType pt = MProjectType.get(getCtx(), getC_ProjectType_ID());
			String sql = pt.getSqlPI(goal.getRestrictions(false), 
					goal.getMeasureScope(), getMeasureDataType(), null, role);		
			PreparedStatement pstmt = null;
			try		//	SQL statement could be wrong
			{
				pstmt = DB.prepareStatement (sql, null);
				ResultSet rs = pstmt.executeQuery();
				if (rs.next())
					ManualActual = rs.getBigDecimal(1);
				rs.close ();
				pstmt.close ();
				pstmt = null;
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
			//	SQL may return no rows or null
			if (ManualActual == null)
			{
				ManualActual = Env.ZERO;
				log.fine("No Value = " + sql);
			}
			goal.setMeasureActual(ManualActual);
			goal.save();
		}
		return true;
	}	//	updateProjects

}	//	MMeasure