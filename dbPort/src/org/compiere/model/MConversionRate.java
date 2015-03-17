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

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.apache.commons.lang.StringUtils;
import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.compiere.util.TimeUtil;
import org.compiere.util.Utilerias;


/**
 *	Currency Conversion Rate Model
 *
 *  @author Jorg Janke
 *  @version $Id: MConversionRate.java,v 1.2 2006/07/30 00:58:18 jjanke Exp $
 */
public class MConversionRate extends X_C_Conversion_Rate
{
	/** serialVersionUID */
	private static final long serialVersionUID = -8148710780459350026L;
	/**	Logger						*/
	private static CLogger		s_log = CLogger.getCLogger (MConversionRate.class);


	/**
	 *	Convert an amount to base Currency
	 *	@param ctx context
	 *  @param CurFrom_ID  The C_Currency_ID FROM
	 *  @param ConvDate conversion date - if null - use current date
	 *  @param C_ConversionType_ID conversion rate type - if 0 - use Default
	 *  @param Amt amount to be converted
	 * 	@param AD_Client_ID client
	 * 	@param AD_Org_ID organization
	 *  @return converted amount
	 */
	public static BigDecimal convertBase (Properties ctx,
		BigDecimal Amt, int CurFrom_ID,
		Timestamp ConvDate, int C_ConversionType_ID,
		int AD_Client_ID, int AD_Org_ID)
	{
		return convert (ctx, Amt, CurFrom_ID, MClient.get(ctx).getC_Currency_ID(),
			ConvDate, C_ConversionType_ID, AD_Client_ID, AD_Org_ID);
	}	//	convertBase


	/**
	 *  Convert an amount with today's default rate
	 *	@param ctx context
	 *  @param CurFrom_ID  The C_Currency_ID FROM
	 *  @param CurTo_ID    The C_Currency_ID TO
	 *  @param Amt amount to be converted
	 * 	@param AD_Client_ID client
	 * 	@param AD_Org_ID organization
	 *  @return converted amount
	 */
	public static BigDecimal convert (Properties ctx,
		BigDecimal Amt, int CurFrom_ID, int CurTo_ID,
		int AD_Client_ID, int AD_Org_ID)
	{
		return convert (ctx, Amt, CurFrom_ID, CurTo_ID, null, 0, AD_Client_ID, AD_Org_ID);
	}   //  convert

	/**
	 *	Convert an amount
	 *	@param ctx context	 
	 *  @param Amt amount to be converted
	 *  @param CurFrom_ID  The C_Currency_ID FROM
	 *  @param CurTo_ID    The C_Currency_ID TO
	 *  @param ConvDate conversion date - if null - use current date
	 *  @param C_ConversionType_ID conversion rate type - if 0 - use Default
	 * 	@param AD_Client_ID client
	 * 	@param AD_Org_ID organization
	 *  @return converted amount or null if no rate
	 */
	public static BigDecimal convert (Properties ctx,
		BigDecimal Amt, int CurFrom_ID, int CurTo_ID,
		Timestamp ConvDate, int C_ConversionType_ID,
		int AD_Client_ID, int AD_Org_ID)
	{//203.98
		if (Amt == null)
			throw new IllegalArgumentException("Required parameter missing - Amt");
		if (CurFrom_ID == CurTo_ID || Amt.equals(Env.ZERO))
			return Amt;
		//	Get Rate                        100 -> 130
		BigDecimal retValue = getRate (CurFrom_ID, CurTo_ID,
			ConvDate, C_ConversionType_ID,
			AD_Client_ID, AD_Org_ID);

		if (retValue == null) {
			MConversionRate rate = MConversionRate.getConversionRate(CurTo_ID, CurFrom_ID);
			//	Get Amount in Currency Precision
			if(rate==null){
				retValue = Env.ZERO;
			} else {
				retValue = rate.getDivideRate().multiply(Amt);
			}
		}else{
			//	Get Amount in Currency Precision
			retValue = retValue.multiply(Amt);	
		}
		
		int stdPrecision = MCurrency.getStdPrecision(ctx, CurTo_ID);
		if (retValue.scale() > stdPrecision)
			retValue = retValue.setScale(stdPrecision, BigDecimal.ROUND_HALF_UP);

		return retValue;
	}	//	convert

	/**
	 *	Get Currency Conversion Rate
	 *  @param  CurFrom_ID  The C_Currency_ID FROM
	 *  @param  CurTo_ID    The C_Currency_ID TO
	 *  @param  ConvDate    The Conversion date - if null - use current date
	 *  @param  ConversionType_ID Conversion rate type - if 0 - use Default
	 * 	@param	AD_Client_ID client
	 * 	@param	AD_Org_ID	organization
	 *  @return currency Rate or null
	 */
	public static BigDecimal getRate (int CurFrom_ID, int CurTo_ID,
		Timestamp ConvDate, int ConversionType_ID, int AD_Client_ID, int AD_Org_ID) {
		if (CurFrom_ID == CurTo_ID) {
			return Env.ONE;
		}
		// Conversion Type
		int C_ConversionType_ID = ConversionType_ID;
		if (C_ConversionType_ID == 0) {
			C_ConversionType_ID = MConversionType.getDefault(AD_Client_ID);
		}
		// Conversion Date
		if (ConvDate == null) {
			ConvDate = Env.getCurrentDate();
		}

		// Get Rate
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT MultiplyRate ");
		sql.append("FROM C_Conversion_Rate ");
		sql.append("WHERE C_Currency_ID=?");// #1
		sql.append(" AND C_Currency_ID_To=?");// #2
		sql.append(" AND	C_ConversionType_ID=?");// #3
		// #4 TRUNC (?) ORA-00932: inconsistent datatypes: expected NUMBER got
		// TIMESTAMP
		sql.append(" AND	? BETWEEN ValidFrom AND ValidTo");
		sql.append(" AND AD_Client_ID IN (0,?)");// #5
		sql.append(" AND AD_Org_ID IN (0,?) ");// #6
		sql.append("ORDER BY AD_Client_ID DESC, AD_Org_ID DESC, ValidFrom DESC");

		BigDecimal retValue = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setInt(1, CurFrom_ID);
			pstmt.setInt(2, CurTo_ID);
			pstmt.setInt(3, C_ConversionType_ID);
			pstmt.setTimestamp(4, ConvDate);
			pstmt.setInt(5, AD_Client_ID);
			pstmt.setInt(6, AD_Org_ID);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				retValue = rs.getBigDecimal(1);
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, "getRate", e);
		} finally {
			DB.close(rs, pstmt);
		}

		if (retValue == null) {
			StringBuilder str = new StringBuilder();
			str.append("getRate - not found - CurFrom=");
			str.append(CurFrom_ID);
			str.append(", CurTo=");
			str.append(CurTo_ID);
			str.append(", ");
			str.append(ConvDate);
			str.append(", Type=");
			str.append(ConversionType_ID);
			str.append((ConversionType_ID == C_ConversionType_ID ? "" : "->" + C_ConversionType_ID));
			str.append(", Client=");
			str.append(AD_Client_ID);
			str.append(", Org=");
			str.append(AD_Org_ID);
		}
		return retValue;
	}	//	getRate


	/**************************************************************************
	 * 	Standard Constructor
	 *	@param ctx context
	 *	@param C_Conversion_Rate_ID id
	 *	@param trxName transaction
	 */
	public MConversionRate (Properties ctx, int C_Conversion_Rate_ID, String trxName)
	{
		super(ctx, C_Conversion_Rate_ID, trxName);
		if (C_Conversion_Rate_ID == 0)
		{
		//	setC_Conversion_Rate_ID (0);
		//	setC_Currency_ID (0);
		//	setC_Currency_ID_To (null);
			super.setDivideRate (Env.ZERO);
			super.setMultiplyRate (Env.ZERO);
			setValidFrom (Env.getCurrentDate());
		}
	}	//	MConversionRate

	/**
	 * 	Load Constructor
	 *	@param ctx context
	 *	@param rs result set
	 *	@param trxName transaction
	 */
	public MConversionRate (Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
	}	//	MConversionRate

	/**
	 * 	New Constructor
	 *	@param po parent
	 *	@param C_ConversionType_ID conversion type
	 *	@param C_Currency_ID currency
	 *	@param C_Currency_ID_To currency to
	 *	@param MultiplyRate multiply rate
	 *	@param ValidFrom valid from
	 */
	public MConversionRate (PO po,
		int C_ConversionType_ID,
		int C_Currency_ID, int C_Currency_ID_To,
		BigDecimal MultiplyRate, Timestamp ValidFrom)
	{
		this (po.getCtx(), 0, po.get_TrxName());
		setClientOrg(po);
		setC_ConversionType_ID (C_ConversionType_ID);
		setC_Currency_ID (C_Currency_ID);
		setC_Currency_ID_To (C_Currency_ID_To);
		//
		setMultiplyRate (MultiplyRate);
		setValidFrom(ValidFrom);
	}	//	MConversionRate

	/**
	 * 	Set Multiply Rate
	 * 	Sets also Divide Rate
	 *	@param MultiplyRate multiply rate
	 */
	public void setMultiplyRate (BigDecimal MultiplyRate)
	{
		if (MultiplyRate == null
			|| MultiplyRate.compareTo(Env.ZERO) == 0
			|| MultiplyRate.compareTo(Env.ONE) == 0)
		{
			super.setDivideRate(Env.ONE);
			super.setMultiplyRate(Env.ONE);
		}
		else
		{
			super.setMultiplyRate(MultiplyRate);
			double dd = 1 / MultiplyRate.doubleValue();
			super.setDivideRate(new BigDecimal(dd));
		}
	}	//	setMultiplyRate

	/**
	 *	Set Divide Rate.
	 *	Sets also Multiply Rate
	 *	@param	DivideRate divide rate
	 */
	public void setDivideRate (BigDecimal DivideRate)
	{
		if (DivideRate == null
			|| DivideRate.compareTo(Env.ZERO) == 0
			|| DivideRate.compareTo(Env.ONE) == 0)
		{
			super.setDivideRate(Env.ONE);
			super.setMultiplyRate(Env.ONE);
		}
		else
		{
			super.setDivideRate(DivideRate);
			double dd = 1 / DivideRate.doubleValue();
			super.setMultiplyRate(new BigDecimal(dd));
		}
	}	//	setDivideRate

	/**
	 * 	String Representation
	 *	@return info
	 */
	public String toString()
	{
		StringBuffer sb = new StringBuffer("MConversionRate[");
		sb.append(get_ID())
			.append(",Currency=").append(getC_Currency_ID())
			.append(",To=").append(getC_Currency_ID_To())
			.append(", Multiply=").append(getMultiplyRate())
			.append(",Divide=").append(getDivideRate())
			.append(", ValidFrom=").append(getValidFrom());
		sb.append("]");
		return sb.toString();
	}	//	toString


	/**
	 * 	Before Save.
	 * 	- Same Currency
	 * 	- Date Range Check
	 * 	- Set To date to 2056
	 *	@param newRecord new
	 *	@return true if OK to save
	 */
	protected boolean beforeSave (boolean newRecord)
	{
		// Validar fluctuación tipo Spot
		if(getC_ConversionType_ID() == 114 && !allowCreation(newRecord)){
			return false;
		}
		
		//	From - To is the same
		if (getC_Currency_ID() == getC_Currency_ID_To())
		{
			log.saveError("Error", Msg.parseTranslation(getCtx(), "@C_Currency_ID@ = @C_Currency_ID@"));
			return false;
		}
		//	Nothing to convert
		if (getMultiplyRate().compareTo(Env.ZERO) <= 0)
		{
			log.saveError("Error", Msg.parseTranslation(getCtx(), "@MultiplyRate@ <= 0"));
			return false;
		}

		// dates must take into account the hour:minute:second, as problems could happen
		setValidFrom(TimeUtil.dateToTimestamp(TimeUtil.truncateDay(getValidFrom())));

		//	Date Range Check
		Timestamp from = getValidFrom();
		if (getValidTo() == null)
			setValidTo (TimeUtil.getDay(2056, 1, 29));	//	 no exchange rates after my 100th birthday
		Timestamp to = getValidTo();

		if (to.before(from))
		{
			SimpleDateFormat df = DisplayType.getDateFormat(DisplayType.Date);
			log.saveError("Error", df.format(to) + " < " + df.format(from));
			return false;
		}

		setValidTo(TimeUtil.dateToTimestamp(TimeUtil.truncateEndDay(getValidTo())));

		return true;
	}	//	beforeSave

	/**
	 * No permitir que se repitan los registros
	 * @return
	 */
	private boolean allowCreation(final boolean isNew){
		//Search TypeCoversion
		final StringBuilder where = new StringBuilder(" isActive = 'Y' ")
		.append(" AND C_ConversionType_ID = 114 ")
		.append(" AND ( ? between validfrom and validto ")
		.append(" OR    ? between validfrom and validto ")
		.append(" OR  validfrom between ? and ? ")
		.append(" OR  validto   between ? and ? ")
		.append(") ");
		
		// params
		final ArrayList<Object> params = new ArrayList<Object>();
		params.add(getValidFrom());
		params.add(getValidTo());
		params.add(getValidFrom());
		params.add(getValidTo());
		params.add(getValidFrom());
		params.add(getValidTo());
		
		if(!isNew){
			where.append(" AND C_Conversion_Rate_ID <> ? ");
			params.add(getC_Conversion_Rate_ID());
		}
		
		params.add(getC_Currency_ID());// Se cambia en la segunda consulta
		params.add(getC_Currency_ID_To());// Se cambia en la segunda consulta
		
		StringBuilder sql = new StringBuilder(where)
		.append("AND C_Currency_ID = ? ").append("AND C_Currency_ID_To = ? ");
		MConversionRate dateCompare = MConversionRate.getConversionRate(getCtx(), sql.toString(), params, null);
		if(dateCompare != null){
			log.saveError("Error",  Utilerias.getAppMsg(getCtx(), "msg.fluctuationForce"));
			return false;
		} 
		
		sql = new StringBuilder(where)
		.append("AND C_Currency_ID_To = ? ").append("AND C_Currency_ID = ? ");
		dateCompare = MConversionRate.getConversionRate(getCtx(), sql.toString(), params, null);
		if(dateCompare != null){
			log.saveError("Error",  Utilerias.getAppMsg(getCtx(), "msg.fluctuationForce"));
			//existeFluctuacionVigente Ya existe una fluctuación vigente
			return false;
		} 
		
		return true;
	}

	
	static public List<MConversionRate> getFromCurrencyID(Properties ctx, List<Object> params, String whereAnd, String trxName) {
		List<MConversionRate> list = new ArrayList<MConversionRate>();

		StringBuilder sql = new StringBuilder();
		sql.append("select *  from  C_CONVERSION_RATE where c_currency_id_to = ? ");
		if (StringUtils.isNotEmpty(whereAnd)) {
			sql.append(whereAnd);
		}
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, Constantes.SPACE, Table_Name));
		sql.append(" ORDER BY CREATED DESC ");

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			DB.setParameters(pstmt, params);
			rs = pstmt.executeQuery();

			while (rs.next())
				list.add(new MConversionRate(ctx, rs, trxName));

			rs.close();
			pstmt.close();
			pstmt = null;
		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
			pstmt = null;
			rs = null;
		}
		return list;
	}
	
	/**
	 * Listado de conversiones
	 * 
	 * @param currencyID
	 *            Moneda de
	 * @param currencyToID
	 *            Moneda para
	 * @return
	 */
	public static List<MConversionRate> getConversionRates(int currencyId, int currencyToId) {
		Query query = new Query(Env.getCtx(), Table_Name, " C_Currency_ID=? AND C_Currency_ID_To=? ", null);
		query.setParameters(currencyId, currencyToId);
		query.addAccessLevelSQL(true);
		query.setOrderBy("C_ConversionType_ID, validto, created desc");

		return query.list();
	}
	
	private MConversionType conversionType;

	public MConversionType getConversionType() {
		if (conversionType == null) {
			conversionType = new MConversionType(getCtx(), getC_ConversionType_ID(), null);
		}

		return conversionType;
	}
	
	public static MConversionRate getConversionRate(int currencyID, int currencyToID) {
		String where = " C_Currency_ID=? AND C_Currency_ID_To=? ";
		return new Query(Env.getCtx(), Table_Name, where, null)
		.setParameters(currencyID, currencyToID)
		.addAccessLevelSQL(true)
		.setOrderBy("validto").first();
	}
	
	public static MConversionRate getConversionRate(final Properties ctx, String where, List<Object> params, String trxName) {
		return new Query(ctx, Table_Name, where, trxName)
		.setParameters(params)
		.addAccessLevelSQL(true)
		.setOnlyActiveRecords(true)
		.setOrderBy(" ORDER BY validto DESC ")
		.first();
	}
	
//	/**
//	 * Conversion de monto
//	 * @param ctx
//	 * @param currencyIdFrom
//	 * @param currencyIdTo
//	 * @param amt
//	 * @param date
//	 * @return
//	 * @deprecated
//	 */
//	public static BigDecimal getExchangeAmt(final Properties ctx, final int currencyIdFrom 
//			, final int currencyIdTo , final BigDecimal amt, final Timestamp date) {
//		
//		BigDecimal amtConv = Env.ZERO;
//		if ((currencyIdFrom == currencyIdTo) || amt.compareTo(Env.ZERO)==0) {
//			return amt;
//
//		} else {
//
//			amtConv = MConversionRate.convert(ctx, amt
//					, currencyIdFrom, currencyIdTo, date
//					, Env.getContextAsInt(ctx, "#C_ConversionType_ID")
//					, Env.getAD_Client_ID(ctx), Env.getAD_Org_ID(ctx));
//
//			if(amtConv == null || amtConv.compareTo(Env.ZERO)==0){
//
//				final String where = " ((C_Currency_ID = ? AND C_Currency_ID_To = ? ) OR (C_Currency_ID = ? AND C_Currency_ID_TO = ? )) AND ValidFrom <= ? AND ValidTO >= ? ";
//
//				final List<Object> params = new ArrayList<Object>();
//				params.add(currencyIdFrom);
//				params.add(currencyIdTo);
//				params.add(currencyIdTo);
//				params.add(currencyIdFrom);
//				params.add(date == null? new java.sql.Timestamp(TimeUtil.getToday().getTimeInMillis()):date);
//				params.add(date == null? new java.sql.Timestamp(TimeUtil.getToday().getTimeInMillis()):date);
//
//				final MConversionRate convTo_From = MConversionRate.getConversionRate(ctx, where, params, null);
//
//				if (convTo_From == null) {
//					amtConv = BigDecimal.ONE;
//				} else {
//					amtConv = amt.multiply(convTo_From.getMultiplyRate());
//				}
//			}
//			return amtConv;
//		}
//	}

	/** Buscar el tipo de cambio y convertir la moneda */
	public static BigDecimal getExchangeAmt(final Properties ctx, final int selectedCurrencyID, final BigDecimal amt) {
		return amt.multiply(MConversionRate.getRate(ctx, -1, selectedCurrencyID, new java.sql.Timestamp(TimeUtil.getToday().getTimeInMillis()).getTime()));
	}
	
	/** Buscar el tipo de cambio */
	public static BigDecimal getRate(final Properties ctx, int schemaCurrencyID, int selectedCurrencyID, long payDate){
		
		if(schemaCurrencyID<=0){
			final MClientInfo clientInfo = MClientInfo.get(ctx, Env.getAD_Client_ID(ctx), null);
			final MAcctSchema schema = MAcctSchema.get(ctx, clientInfo.getC_AcctSchema1_ID());
			schemaCurrencyID = schema.getC_Currency_ID();
		}
		
		BigDecimal ratebox;
		
		if (schemaCurrencyID == selectedCurrencyID) {
			ratebox = BigDecimal.ONE;
			
		} else {
			final String where = " (C_Currency_ID = ? AND C_Currency_ID_To = ? ) AND ValidFrom <= ? AND ValidTO >= ? ";
	
			List<Object> params = new ArrayList<Object>();
			params.add(schemaCurrencyID);
			params.add(selectedCurrencyID);
			params.add(new java.sql.Timestamp(payDate /*TimeUtil.getToday().getTimeInMillis()*/));
			params.add(new java.sql.Timestamp(payDate /*TimeUtil.getToday().getTimeInMillis()*/));
			MConversionRate convTo_From = MConversionRate.getConversionRate(ctx, where, params, null);
	
			if (convTo_From == null) {
				params = new ArrayList<Object>();
				params.add(selectedCurrencyID);
				params.add(schemaCurrencyID);
				params.add(new java.sql.Timestamp(payDate /*TimeUtil.getToday().getTimeInMillis()*/));
				params.add(new java.sql.Timestamp(payDate /*TimeUtil.getToday().getTimeInMillis()*/));
				convTo_From = MConversionRate.getConversionRate(ctx, where, params, null);
				if(convTo_From == null){
					ratebox = BigDecimal.ONE;
				}else{
					ratebox = convTo_From.getMultiplyRate();
				}
			} else {
				ratebox = convTo_From.getDivideRate();
			}
		}
		return ratebox;
	}

}	//	MConversionRate
