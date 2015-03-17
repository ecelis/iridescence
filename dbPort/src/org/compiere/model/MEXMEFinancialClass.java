package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.util.LabelValueBean;
import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.SecureEngine;
import org.compiere.util.ValueNamePair;

public class MEXMEFinancialClass extends X_EXME_FinancialClass{
	private static final long serialVersionUID = 1L;
	private static CLogger slog = CLogger.getCLogger (MEXMEFinancialClass.class);
	public MEXMEFinancialClass(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}
	
	public MEXMEFinancialClass(Properties ctx, int EXME_FinancialClass_ID,
			String trxName) {
		super(ctx, EXME_FinancialClass_ID, trxName);
	}
	
	/**
	 * @deprecated utilizar {@link MEXMEFinancialClass#getAll(Properties)}
	 * @param ctx
	 * @return
	 */
	public static List<LabelValueBean> getList(Properties ctx){
		final List<LabelValueBean> array = new ArrayList<LabelValueBean>();
		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		sql.append("select * from exme_financialclass where isactive = 'Y'");
		try{
			pstmt = DB.prepareStatement(sql.toString(), null);
			rs = pstmt.executeQuery();

			while (rs.next()){
				array.add(new LabelValueBean(rs.getString(MEXMEPayerClass.COLUMNNAME_Name), String.valueOf(rs.getString(MEXMEPayerClass.COLUMNNAME_Value))));
			}

		} catch (Exception e) {
			slog.log(Level.SEVERE,sql.toString() +  " - " +e.getMessage());
		}finally {
			DB.close(rs, pstmt);
		}		
		return array;
	}
	
	public static List<ValueNamePair> getAll(Properties ctx){
		
		final List<ValueNamePair> array = new ArrayList<ValueNamePair>();
		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		sql.append("select * from exme_financialclass where isactive = 'Y'");
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ",Table_Name));
		
		
		try{
			pstmt = DB.prepareStatement(sql.toString(), null);
			rs = pstmt.executeQuery();

			while (rs.next()){
				array.add(new ValueNamePair(String.valueOf(rs.getString(MEXMEPayerClass.COLUMNNAME_Value)), rs.getString(MEXMEPayerClass.COLUMNNAME_Name)));
			}

		} catch (Exception e) {
			slog.log(Level.SEVERE,sql.toString() +  " - " +e.getMessage());
		}finally {
			DB.close(rs, pstmt);
		}		
		return array;
	}
	
	public static ArrayList<ObservationLogData> getChargeRecap(StringBuilder filters, List<Object> params){
		ArrayList<ObservationLogData> values = new ArrayList<ObservationLogData>();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		StringBuilder reporte;
		try {
			if(StringUtils.isEmpty(filters.toString()) && params.isEmpty()){
				reporte = new StringBuilder(SQLFinancialRpts.financialClassEnounters(false, filters));
		
			}else {
				reporte = new StringBuilder(SQLFinancialRpts.financialClassEnounters(true, filters));
			}
//			
//			if(StringUtils.isNotEmpty(order)){
//				reporte.append("ORDER BY ").append(order);
//			}
			pstm = DB.prepareStatement(reporte.toString(), null);
			
//			pstm.setInt(1, Env.getAD_Org_ID(Env.getCtx()));
			if (StringUtils.isNotEmpty(filters.toString())
					&& !params.isEmpty()) {
				for (int i = 2; i <= params.size()+1; i++) {
					//Solo hay filtros de fechas
					pstm.setTimestamp(i, (java.sql.Timestamp) params.get(i-2));
				}
			}
			rs = pstm.executeQuery();
			
			values.addAll(createListgetChargeRecap(rs));
			
		}catch(Exception e){
			slog.log(Level.SEVERE, e.getMessage(), e);
			
		}finally {
			DB.close(rs,pstm);
		}
		return values;
	}
	/**
	 * Encounters Payer Group
	 * @return lst of values a mostrar en el reporte Observation Log
	 */
	public static ArrayList<ObservationLogData> createListgetChargeRecap(ResultSet rs){
		ArrayList<ObservationLogData> lst = new ArrayList<ObservationLogData>();
		try{
			while(rs.next()){
				ObservationLogData rValues =  new ObservationLogData();
				rValues.setName(rs.getString("NAME"));
				rValues.setHoras(rs.getInt("HORAS"));
				rValues.setEncountot(rs.getInt("ENCOUNTOT"));
				lst.add(rValues);
			}
		}catch(Exception e){
			slog.log(Level.SEVERE, e.getMessage(), e);
		}
		return lst;
	}
}
