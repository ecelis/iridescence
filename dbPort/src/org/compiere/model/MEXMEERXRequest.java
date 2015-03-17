package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.apache.ecs.storage.Array;
import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.Env;

public class MEXMEERXRequest extends X_EXME_ERXRequest {

	static protected CLogger log = CLogger.getCLogger(MEXMEERXRequest.class);

	/**
	 * 
	 */
	private static final long serialVersionUID = 4206045504525776621L;

	public MEXMEERXRequest(Properties ctx, int EXME_ERXRequest_ID, String trxName) {
		super(ctx, EXME_ERXRequest_ID, trxName);
	}

	public MEXMEERXRequest(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	public static MEXMEERXRequest getFromRX(Properties ctx, int EXME_PrescRxDet_ID, String status, String trxName) {
		MEXMEERXRequest ret = null;
		if(status == null){
			ret = get(ctx, trxName, " EXME_PrescRxDet_ID=? ", null, EXME_PrescRxDet_ID);
		}else{
			ret = get(ctx, trxName, " EXME_PrescRxDet_ID=? and status =?", null, EXME_PrescRxDet_ID, status);
		}
		return ret;
	}

	public static MEXMEERXRequest getFromValue(Properties ctx, String value, String trxName) {
		return get(ctx, trxName, " VALUE=? ", null, value);
	}



	public static MEXMEERXRequest get(Properties ctx, String trxName, String whereClause, StringBuilder joins,
			Object... params) {
		return new Query(ctx, Table_Name, whereClause, trxName)//
		.setParameters(params)//
		.setOnlyActiveRecords(true)//
		.addAccessLevelSQL(true)//
		.setOrderBy(" created desc")
		.first();
	}

	public static List<MEXMEERXRequest> getAll(Properties ctx, String trxName, String whereClause, StringBuilder joins,
			Object... params) {
		return new Query(ctx, Table_Name, whereClause, trxName)//
		.setParameters(params)//
		.setOnlyActiveRecords(true)//
		.addAccessLevelSQL(true)//
		.list();

	}

	public static List<MEXMEERXRequest> getRequestByStatus(Properties ctx, int medicoid, String status, String input, String fechaI, String fechaF, String trxName){
		List<MEXMEERXRequest> retVal = new ArrayList<MEXMEERXRequest>();
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT  ");
		sql.append(" req.* ");
//		sql.append(" req.EXME_ERXREQUEST_ID , ");
//		sql.append(" med.exme_medico_id, ");		
//		sql.append(" med.nombre_med as Doctor, ");
//		sql.append(" 0 as Patient, ");
//		sql.append(" req.created, ");
//		sql.append(" req.value, ");
//		sql.append(" nvl(req.notes, ' ') as notes, ");
//		sql.append(" req.status as status, ");
//		sql.append(" req.AckMsg");
		sql.append(" FROM EXME_ERXREQUEST req ");
		sql.append(" INNER JOIN EXME_MEDICO med on req.exme_medico_id = med.exme_medico_id ");
		sql.append(" WHERE req.isActive='Y'  ");
		sql.append(" AND req.EXME_MEDICO_ID = ? ");		
		if(!status.isEmpty()){
			sql.append(" AND Status = ?");
		}
		
		if(input != null){
			if(!input.isEmpty() && !input.equalsIgnoreCase("*")){
				sql.append(" AND ( upper(Status) like '%"+ input.toUpperCase()+"%' OR " );
				sql.append("upper(med.nombre_med) like '%"+ input.toUpperCase()+"%' OR ");
				sql.append("TO_CHAR(req.created) like '%"+ input.toUpperCase()+"%' OR ");
				sql.append("upper(notes) like '%"+ input.toUpperCase()+"%' )");	
			}			
		}
		
		sql.append(" AND (req.EXME_PRESCRXDET_ID IS NULL OR req.EXME_PRESCRXDET_ID = 0 )");
		
		if(fechaI != null && fechaI.length() > 0 && fechaF != null && fechaF.length() > 0){
			sql.append(" and (req.created > to_date('").append(fechaI).append(" 00:01").append("','dd/mm/yyyy hh24:mi') and req.created < to_date('")
			.append(fechaF).append(" 23:59").append("' ,'dd/mm/yyyy hh24:mi')) ");					
		}
		
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", MEXMEERXRequest.Table_Name, "req"));
		sql.append( " order by req.created desc ");


		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setInt(1, medicoid);
			if(!status.isEmpty()){
				pstmt.setString(2, status);
			}
			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				retVal.add(new MEXMEERXRequest(Env.getCtx(), rs, trxName));
			}
		} catch (Exception e) {
			log.log(Level.SEVERE, null, e);
		} finally {
			DB.close(rs, pstmt);
		}
		
		return retVal;
	}

}
