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
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Properties;
import java.util.logging.Level;

import org.apache.commons.lang.StringUtils;
import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.compiere.util.TimeUtil;

/**
 *	Price List Version Model
 *	
 *  @author Jorg Janke
 *  @version $Id: MPriceListVersion.java,v 1.3 2006/07/30 00:51:03 jjanke Exp $
 */
public class MPriceListVersion extends X_M_PriceList_Version
{
	/** serialVersionUID. */
	private static final long serialVersionUID = 4760770517131019211L;

	/**
	 * 	Standard Cinstructor
	 *	@param ctx context
	 *	@param M_PriceList_Version_ID id
	 *	@param trxName transaction
	 */
	public MPriceListVersion(Properties ctx, int M_PriceList_Version_ID, String trxName)
	{
		super(ctx, M_PriceList_Version_ID, trxName);
		if (M_PriceList_Version_ID == 0)
		{
		//	setName (null);	// @#Date@
		//	setM_PriceList_ID (0);
		//	setValidFrom (TimeUtil.getDay(null));	// @#Date@
		//	setM_DiscountSchema_ID (0);
		}
	}	//	MPriceListVersion

	/**
	 * 	Load Constructor
	 *	@param ctx context
	 *	@param rs result set
	 *	@param trxName transaction
	 */
	public MPriceListVersion(Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
	}	//	MPriceListVersion

	/**
	 * 	Parent Constructor
	 *	@param pl parent
	 */
	public MPriceListVersion (MPriceList pl)
	{
		this (pl.getCtx(), 0, pl.get_TrxName());
		setClientOrg(pl);
		setM_PriceList_ID(pl.getM_PriceList_ID());
	}	//	MPriceListVersion
	
	/**
	 * 	Set Name to Valid From Date.
	 * 	If valid from not set, use today
	 */
	public void setName() {
		if (getValidFrom() == null) {
			Timestamp time = new Timestamp(System.currentTimeMillis());
//				DB.getTimestampForOrg(getCtx());
			setValidFrom(TimeUtil.getDayTime(time, time));
		}
		if (getName() == null) {
			String name = Constantes.getSdfFechaHoraAmpliaAmPm().format(getValidFrom());
			// DisplayType.getDateFormat(DisplayType.Date).format(getValidFrom());
			setName(name);
		}
	}	//	setName
	
	/**
	 * 	Before Save
	 *	@param newRecord new
	 *	@return true
	 */
	protected boolean beforeSave (boolean newRecord)
	{
		setName();
		
		boolean result = true;
		try {
			result = getValidName();
		} catch (Exception e) {
			log.saveError("lista.precios.version.name", "Invalid name: " + getName());
			result = false;
		}
		return result;
	}	//	beforeSave
	
	/**
	 * 	get valid Name to list version.
	 * 	If valid, insert, else, return a error
	 * @throws Exception 
	 */
	public boolean getValidName() throws Exception{
		int [] mplvID=MPriceListVersion.getAllIDs(get_TableName(), " Name = '"+getName().trim()+"' AND AD_Client_ID = " 
				+ Env.getAD_Client_ID(Env.getCtx()), get_TrxName());
		boolean eName=true;
		if(mplvID.length>0 ){
			eName=false;
			for (int i = 0; i < mplvID.length; i++) {	
				int idTmp = mplvID[i];
				if(idTmp == getM_PriceList_Version_ID()){
					eName = true;
					break; //esta editando la misma version, no esta guardando una nueva
				}
			}
			if(!eName){
				throw new Exception(Msg.getMsg(Env.getCtx(), "lista.precios.version.name"));
			}
		}
		return eName;
	}	//	
	
	/**
	 * Id de la lista de precios
	 * @param ctx
	 * @param priceListId
	 * @param trxName
	 * @return
	 */
	public static int getIdbyPriceList(Properties ctx, int priceListId, String trxName) {
		CLogger s_log = CLogger.getCLogger(MPriceListVersion.class);

		int retValue = 0;

		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);

		sql.append("select * from m_pricelist_version where m_pricelist_id = ? ");

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, priceListId);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				retValue = rs.getInt("M_PriceList_Version_ID");
			}

		} catch (SQLException ex) {
			s_log.log(Level.SEVERE, "Error: " + ex.getMessage() + " " + sql, ex);
		} finally {
			DB.close(rs, pstmt);
		}
		return retValue;
	}
	
	/**
	 * Version de la lista de precios
	 */
	private MPriceList listaPre = null;

	public MPriceList getListaPre() {
		if(listaPre==null)
			listaPre = new MPriceList(getCtx(), getM_PriceList_ID(), null);
		return this.listaPre;
	}

	public void setListaPre(MPriceList pListaPre) {
		this.listaPre = pListaPre;
	}
	/**
	 * Importacion de lista de precios
	 * @param ctx
	 * @param name
	 * @param trxName
	 * @return
	 */
	public static int findByName(Properties ctx, String name, String trxName) {
		StringBuilder st = new StringBuilder();
		st.append("select M_PriceList_Version_ID from M_PriceList_Version where name = ? ");
		st = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, st.toString(), MPriceListVersion.Table_Name));
		int id = DB.getSQLValue(trxName, st.toString(), name);
		return id;
	}
}	//	MPriceListVersion
