package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.vo.ServicesVO;

public class MEXMEClaimCodes extends X_EXME_ClaimCodes {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8995325894052712021L;
	/**	Static Logger				*/
	private static CLogger		s_log = CLogger.getCLogger (MEXMEClaimCodes.class);
	
	private MActPacienteDiag  diag = null;
	private ServicesVO charge = null;

	public MEXMEClaimCodes(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	public MEXMEClaimCodes(Properties ctx, int EXME_ClaimCodes_ID, String trxName) {
		super(ctx, EXME_ClaimCodes_ID, trxName);
	}

	/**
	 * 
	 * @param tableid
	 * @param recordid
	 * @param EXME_Paciente_ID
	 * @param EXME_CtaPac_ID
	 * @param ctx
	 * @param trxName
	 * @return
	 */
	public static MEXMEClaimCodes getCode(int tableid, int recordid, int EXME_Paciente_ID, int EXME_CtaPac_ID,
		Properties ctx, String trxName) {
		MEXMEClaimCodes code = null;
		final StringBuffer sql = new StringBuffer(Constantes.INIT_CAPACITY_ARRAY);
		try {
			sql.append(" EXME_PACIENTE_ID=? ");
			sql.append(" AND AD_TABLE_ID=? AND RECORD_ID=? ");
			if (EXME_CtaPac_ID > 0) {
				sql.append(" AND EXME_CTAPAC_ID=? ");
			}
			final List<Object> params = new ArrayList<Object>();
			params.add(EXME_Paciente_ID);
			params.add(tableid);
			params.add(recordid);
			if (EXME_CtaPac_ID > 0) {
				params.add(EXME_CtaPac_ID);
			}
			code = new Query(ctx, Table_Name, sql.toString(), trxName).setParameters(params).first();
		}
		catch (Exception e) {
			s_log.log(Level.SEVERE, "getCode : " + sql, e);
		}
		return code;
	}
	
	/**
	 * 
	 * @param tableid
	 * @param recordid
	 * @param ctx
	 * @param trxName
	 * @return
	 */
	public static List<MEXMEClaimCodes> getCodeTableRecord(int tableid, int recordid, Properties ctx, String trxName) {
		final StringBuffer sql = new StringBuffer(Constantes.INIT_CAPACITY_ARRAY);
		List<MEXMEClaimCodes> lst = new ArrayList<MEXMEClaimCodes>();
		try {
			sql.append(" AD_TABLE_ID=? AND RECORD_ID=? ");

			final List<Object> params = new ArrayList<Object>();
			params.add(tableid);
			params.add(recordid);

			lst = new Query(ctx, Table_Name, sql.toString(), trxName)
					.setParameters(params)
					.addAccessLevelSQL(true)
					.list();
		} catch (Exception e) {
			s_log.log(Level.SEVERE, "getCodeTableRecord : " + sql, e);
		}
		return lst;
	}
	
	/**
	 * 
	 * @param tableid
	 * @param EXME_Paciente_ID
	 * @param EXME_CtaPac_ID
	 * @param ctx
	 * @param trxName
	 * @return
	 */
	public static MEXMEClaimCodes[] getCodes(int tableid, int EXME_Paciente_ID, int EXME_CtaPac_ID, Properties ctx,
		String trxName) {
		List<MEXMEClaimCodes> list = new ArrayList<MEXMEClaimCodes>();

		final StringBuffer sql = new StringBuffer(Constantes.INIT_CAPACITY_ARRAY);

		final List<Object> params = new ArrayList<Object>();
		try {
			sql.append(" EXME_PACIENTE_ID=? ");
			sql.append(" AND AD_TABLE_ID=? ");
			if (EXME_CtaPac_ID > 0) {
				sql.append("AND EXME_CTAPAC_ID=? ");
			}
			params.add(EXME_Paciente_ID);
			params.add(tableid);
			if (EXME_CtaPac_ID > 0) {
				params.add(EXME_CtaPac_ID);
			}
			list = new Query(ctx, Table_Name, sql.toString(), trxName).setParameters(params).list();
		}
		catch (Exception e) {
			s_log.log(Level.SEVERE, "getCode : " + sql, e);
		}

		MEXMEClaimCodes[] codes = new MEXMEClaimCodes[list.size()];
		list.toArray(codes);
		return codes;
	}
	
	/**
	 * Regresa la lista de valores a partir de la columna tableName.Value,
	 * El campo tiene que ser STRING
	 * @param tableName
	 * @param columnName
	 * @param tableID
	 * @param tableOrigID
	 * @param recordOrigID
	 * @param ctaPacID
	 * @param ctx
	 * @param trxName
	 * @return
	 * @author gvaldez
	 */
	public static String getColumnValue(String tableName, String columnName,  int tableID, 
			int tableOrigID,int recordOrigID, int ctaPacID, Properties ctx, String trxName) {

		final StringBuffer retStr = new StringBuffer(Constantes.INIT_CAPACITY_ARRAY);
		final StringBuffer sql = new StringBuffer(Constantes.INIT_CAPACITY_ARRAY);
	
		sql.append("SELECT X.")
		.append(columnName)
		.append("FROM EXME_ClaimCodes CC")
		.append("INNER JOIN  ")
		.append(tableName)
		.append(" X ")
		.append("On X.")
		.append(tableName)
		.append("_ID = CC.Record_ID ")
		.append("WHERE CC.IsActive = 'Y' ")
		.append("AND CC.Exme_Ctapac_Id=? ")
		.append("AND CC.AD_TableOrig_ID=? ")
		.append("AND CC.RecordOrig_ID=? ")
		.append("AND CC.AD_Table_ID=? ");
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, ctaPacID);
			pstmt.setInt(2, tableOrigID);
			pstmt.setInt(3, recordOrigID);
			pstmt.setInt(4, tableID);
			
			rs = pstmt.executeQuery();
			while (rs.next()) {
				if (retStr.length() > 0) {
					retStr.append(",");
				}
				retStr.append(rs.getString(columnName));
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, "getColumnValue : " + sql, e);
		} finally {
			DB.close(rs, pstmt);
		}
		return retStr.toString();
	}

	
	/**
	 * Regresa TableName.ColumnName partir de un ClaimCode
	 * @param tableName
	 * @param columnName
	 * @param ctx
	 * @param trxName
	 * @return
	 * @author gvaldez
	 */
	public Object getValue(String tableName, String columnName, Properties ctx, String trxName) {

		Object retStr = null;
		final StringBuffer sql = new StringBuffer(Constantes.INIT_CAPACITY_ARRAY);
	
		sql.append("SELECT X.")
		.append(columnName)
		.append(" FROM  ")
		.append(tableName)
		.append(" X ")
		.append("WHERE X.")
		.append(tableName)
		.append("_ID=? ");
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, getRecord_ID());
			
			rs = pstmt.executeQuery();
			if (rs.next()) {
				retStr = rs.getObject(columnName);
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, "getValue : " + sql, e);
		} finally {
			DB.close(rs, pstmt);
		}
		return retStr;
	}
	
	/**
	 * 
	 * @param ctx
	 * @param ctaPacId
	 * @param tableId
	 * @param recordId
	 * @param origTableId
	 * @param trxName
	 * @return
	 */
	public static List<MEXMEClaimCodes> get(Properties ctx, int ctaPacId, int origTableId, int origRecordId, int tableId,
		String trxName) {

		List<MEXMEClaimCodes> lst = new ArrayList<MEXMEClaimCodes>();

		final StringBuffer sql = new StringBuffer(Constantes.INIT_CAPACITY_ARRAY);
		final List<Object> params = new ArrayList<Object>();
		
		try {
			sql.append(" AD_TABLEOrig_ID=? AND RECORDOrig_ID=? ");
			params.add(origTableId);
			params.add(origRecordId);
			
			if (tableId > 0) {
				sql.append(" AND AD_Table_ID=? ");	
				params.add(tableId);
				
			}
			if (ctaPacId > 0) {
				sql.append(" AND EXME_ctapac_ID=? ");
				params.add(ctaPacId);
			}
			
			lst = new Query(ctx, Table_Name, sql.toString(), trxName)//
				.setParameters(params)//
				.setOrderBy(" sequence DESC ")//
				.addAccessLevelSQL(true)//
				.list();
		}
		catch (Exception e) {
			s_log.log(Level.SEVERE, "getCode : " + sql, e);
		}
		return lst;
	}
	
	public static List<MEXMEClaimCodes> getCargos(Properties ctx, int ctaPacId, int tableId, int recordIdOrg, int origTableId, String trxName) {

		
			List<MEXMEClaimCodes> lst = new ArrayList<MEXMEClaimCodes>();

			final StringBuffer sql = new StringBuffer(Constantes.INIT_CAPACITY_ARRAY);
			try {
				sql.append(" AD_TABLE_ID=? AND RecordOrig_ID=? ");
				if (origTableId > 0) {
					sql.append(" AND AD_TableOrig_ID=? ");
				}
				if (ctaPacId > 0) {
					sql.append(" AND EXME_ctapac_ID=? ");
				}
				final List<Object> params = new ArrayList<Object>();
				params.add(tableId);
				params.add(recordIdOrg);
				if (origTableId > 0) {
					params.add(origTableId);
				}
				if (ctaPacId > 0) {
					params.add(ctaPacId);
				}
				lst = new Query(ctx, Table_Name, sql.toString(), trxName)//
					.setParameters(params)//
					.setOrderBy(" sequence DESC ")//
					.addAccessLevelSQL(true)//
					.list();
			}
			catch (Exception e) {
				s_log.log(Level.SEVERE, "getCode : " + sql, e);
			}
			return lst;
		}
	
	/**
	 * 
	 * @param ctx
	 * @param ctaPacId
	 * @param tableId
	 * @param recordId
	 * @param origTableId
	 * @param trxName
	 * @return
	 */
	public static List<MEXMEClaimCodes> get(Properties ctx, int ctaPacId, int tableId, int recordId, int origTableId, int origRecordId,
		String trxName) {

		List<MEXMEClaimCodes> lst = new ArrayList<MEXMEClaimCodes>();

		final StringBuffer sql = new StringBuffer(Constantes.INIT_CAPACITY_ARRAY);
		try {
			sql.append(" AD_TABLE_ID=? AND RECORD_ID=? ");
			if (origTableId > 0) {
				sql.append(" AND AD_TableOrig_ID=? ");
			}
			if (origRecordId > 0) {
				sql.append(" AND RecordOrig_ID=? ");
			}
			if (ctaPacId > 0) {
				sql.append(" AND EXME_ctapac_ID=? ");
			}
			final List<Object> params = new ArrayList<Object>();
			params.add(tableId);
			params.add(recordId);
			if (origTableId > 0) {
				params.add(origTableId);
			}
			if (origRecordId > 0) {
				params.add(origRecordId);
			}
			if (ctaPacId > 0) {
				params.add(ctaPacId);
			}
			lst = new Query(ctx, Table_Name, sql.toString(), trxName)//
				.setParameters(params)//
				.setOrderBy(" sequence DESC ")//
				.addAccessLevelSQL(true)//
				.list();
		}
		catch (Exception e) {
			s_log.log(Level.SEVERE, "getCode : " + sql, e);
		}
		return lst;
	}
	
	/**
	 * 
	 * @param ctx
	 * @param EXME_Paciente_ID
	 * @param EXME_CtaPac_ID
	 * @param AD_Table_ID
	 * @param record_ID
	 * @param AD_TableOrig_ID
	 * @param RecordOrig_ID
	 * @return 
	 */
	public static MEXMEClaimCodes get(Properties ctx, int EXME_Paciente_ID, int EXME_CtaPac_ID, int AD_Table_ID,
		int record_ID, int AD_TableOrig_ID, int RecordOrig_ID) {
		return new Query(ctx,Table_Name,
			" EXME_Paciente_ID=? AND EXME_CtaPac_ID=? AND AD_Table_ID=? AND record_ID=? AND AD_TableOrig_ID=? AND RecordOrig_ID=?",
			null)//
			.setParameters(EXME_Paciente_ID, EXME_CtaPac_ID, AD_Table_ID, record_ID, AD_TableOrig_ID, RecordOrig_ID)//
			.setOnlyActiveRecords(true) //
			.first();
	}
	
	/**
	 * 
	 * @param ctx
	 * @param EXME_Paciente_ID
	 * @param EXME_CtaPac_ID
	 * @param AD_Table_ID
	 * @param record_ID
	 * @param AD_TableOrig_ID
	 * @param RecordOrig_ID
	 * @return
	 */
	public static MEXMEClaimCodes get(Properties ctx, int EXME_CtaPac_ID, int AD_Table_ID,
		String confType, String trxName) {
		return new Query(ctx,Table_Name,
			" EXME_CtaPac_ID=? AND AD_Table_ID=? AND confType=?",
			trxName)//
			.setParameters(EXME_CtaPac_ID, AD_Table_ID, confType)//
			.setOnlyActiveRecords(true) //
			.first();
	}
	
	
	/**
	 * Metodo para determinar 
	 * si existe un registro 
	 * y si i esta apuntando a un registro y tabla dadas 
	 * @param tableName
	 * @param tableID
	 * @param tableOrigID
	 * @param recordOrigID
	 * @param patientID
	 * @param ctaPacID
	 * @param ctx
	 * @param trxName
	 * @return boolean
	 * @author gvaldez
	 */
	public static boolean existe(int ctaPacID, int tableOrigID, int recordOrigID, 
			int tableID, int recordID, Properties ctx, String trxName) {
		boolean retVal = Boolean.FALSE;
		final StringBuffer sql = new StringBuffer(Constantes.INIT_CAPACITY_ARRAY);
	
		sql.append("SELECT count(*) contador ")
		.append("FROM EXME_ClaimCodes CC ")
		.append("WHERE CC.Exme_Ctapac_Id=? AND IsActive = 'Y'  ")
		.append("AND CC.AD_TableOrig_ID=? ")
		.append("AND CC.RecordOrig_ID=? ")
		.append("AND CC.AD_Table_ID=? ")
		.append("AND CC.Record_ID=? ");
		
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, ctaPacID);
			pstmt.setInt(2, tableOrigID);
			pstmt.setInt(3, recordOrigID);
			pstmt.setInt(4, tableID);
			pstmt.setInt(5, recordID);
			
			rs = pstmt.executeQuery();
			while (rs.next()) {
				retVal = rs.getInt("contador")>0;
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, "getCode : " + sql, e);
		} finally {
			DB.close(rs, pstmt);
		}
		return retVal;
	}

	public static boolean createClaimCode(MEXMECtaPac ctaPac, int tableID, 
			int recordID, int tableOrigID, int recordOrigID, String trxName) {
		
		MEXMEClaimCodes reg = new MEXMEClaimCodes(Env.getCtx(), 0, null);
		reg.setAD_Table_ID(tableID);
		reg.setRecord_ID(recordID);
		reg.setAD_TableOrig_ID(tableOrigID);
		reg.setRecordOrig_ID(recordOrigID);
		reg.setEXME_Paciente_ID(ctaPac.getEXME_Paciente_ID());
		reg.setEXME_CtaPac_ID(ctaPac.getEXME_CtaPac_ID());
    	if (!reg.save(trxName)){
    		return false;
    	}
    	return true;
	}

	public MActPacienteDiag getDiag() {
		return diag;
	}

	public void setDiag(MActPacienteDiag diag) {
		this.diag = diag;
	}

	public ServicesVO getCharge() {
		return charge;
	}

	public void setCharge(ServicesVO charge2) {
		this.charge = charge2;
	}
	
	/**
	 * Almacena los modificadores para el claim (USA)
	 * 
	 * @param cargo
	 * Lama: cargos 2014 - Se mueve codigo de CreateCharges
	 */
	public static boolean insertModifiers(final MCtaPacDet cargo) {
		boolean retValue = false;
		if (cargo != null && cargo.getLstModificadores() != null) {
			for (Integer integer : cargo.getLstModificadores()) {
				// toma la transaccion del cargo
				final X_EXME_ClaimCodes reg = new X_EXME_ClaimCodes(cargo.getCtx(), 0, cargo.get_TrxName());
				reg.setAD_Table_ID(MEXMEModifiers.Table_ID);
				reg.setRecord_ID(integer);
				reg.setEXME_Paciente_ID(cargo.getCtaPac().getEXME_Paciente_ID());
				reg.setEXME_CtaPac_ID(cargo.getEXME_CtaPac_ID());
				reg.setAD_TableOrig_ID(MCtaPacDet.Table_ID);
				reg.setRecordOrig_ID(cargo.getEXME_CtaPacDet_ID());
				retValue = reg.save();
				if (!reg.save()) {// no se requiere, ya se paso en contructor
					s_log.log(Level.SEVERE, "Error EnterCharge.createCharges: ",
					// reg.eErrorBD != null ? reg.eErrorBD.getValue() :
						"X_EXME_ClaimCodes.save ");// TODO:
				}
			}
		}
		return retValue;
	}

}
