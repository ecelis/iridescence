package org.compiere.model;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.DB;

public class MEXMEFolDig extends X_EXME_FolDig{

	/**
	 * Clase Modelo EXME_FolDig
	 */
	
	private static final long serialVersionUID = 1429843303788213316L;
	private static CLogger s_log = CLogger.getCLogger(MEXMEFolDig.class);

	public MEXMEFolDig(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	public MEXMEFolDig(Properties ctx, int EXME_FolDig_ID, String trxName) {
		super(ctx, EXME_FolDig_ID, trxName);
	}
	
	
	/**
	 * Metodo para obtener la linea del cliente
	 * si esque tiene asignados folios
	 * @param clientID
	 * @param ctx
	 * @param trxName
	 **/
	public static MEXMEFolDig getRegister(int clientID, Properties ctx, String trxName){
		MEXMEFolDig folio = null;
		
		PreparedStatement pstmt = null;
		ResultSet rs =null;		
		StringBuilder sql = new StringBuilder();
		
		sql.append(" SELECT * FROM EXME_FolDig ");
		//.append(" WHERE IsActive = 'Y' ");
		if (clientID > 0) {
			sql.append(" WHERE AssignClient_ID = ? ");
		}
		
		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			DB.setParameter(pstmt, 1, clientID);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				folio = new MEXMEFolDig(ctx, rs, trxName); 
			}
			
		}catch(Exception e){
			s_log.log(Level.SEVERE, "getRegister", e);
		} finally {
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}		
		
		return folio;
	}
	
	/**
	 * Metodo para obtener la linea del cliente
	 * si eque tiene asignados folios
	 * @param clientID
	 * @param ctx
	 * @param trxName
	 **/
	public static List<MEXMEFolDig> getAll(Properties ctx, String trxName){
		List<MEXMEFolDig> folioLst = new ArrayList<MEXMEFolDig>();
		
		PreparedStatement pstmt = null;
		ResultSet rs =null;		
		StringBuilder sql = new StringBuilder();
		
		sql.append(" SELECT distinct (fol.exme_folDig_id), fol.* FROM EXME_FolDig fol")
		.append(" LEFT JOIN EXME_FolDigDet det on fol.EXME_FolDig_ID = det.EXME_FolDig_ID ");
		//.append(" WHERE fol.IsActive = 'Y' ");
		
		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				MEXMEFolDig folio = new MEXMEFolDig(ctx, rs, trxName);
				//folio.set
				folioLst.add(folio);
			}
			
		}catch(Exception e){
			s_log.log(Level.SEVERE, "MEXMEFolDig.getAll", e);
		} finally {
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}		
		
		return folioLst;
	}
	
	public static List<FolDigBean> getInfo(Properties ctx, int clientID, Date dateIni, Date dateFin, boolean cobro, String trxName){
		List<FolDigBean> folioLst = new ArrayList<FolDigBean>();

		PreparedStatement pstmt = null;
		ResultSet rs =null;		
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<Object>();

		sql.append(" SELECT fol.exme_foldig_id AS folID, COUNT(exme_foldigDet_id) as totFolios, fol.isactive as Active," )
		.append(" fol.assignclient_id as clientID,fol.name as client , fol.assignclient_id AS clientID ")
		.append(" FROM exme_foldig fol ")		
		.append(" INNER JOIN EXME_FolDigDet det on fol.EXME_FolDig_ID = det.EXME_FolDig_ID ")
		.append(" INNER JOIN c_invoice i on det.c_invoice_id = i.c_invoice_id ")
		.append(" WHERE fol.IsActive = 'Y' AND det.fechacorte is null AND det.pagado <> 'Y' ");
		if (dateIni != null && dateFin != null) {
			sql.append("AND  i.dateinvoiced between ? AND ?");
			params.add(dateIni);
			params.add(dateFin);
		}
		
		if (cobro) {
			sql.append(" AND fol.isconsumo = 'Y' ");
		}

		if (clientID > 0) {
			sql.append(" AND  fol.assignclient_id = ?");
			params.add(clientID);
		}
		sql.append(" GROUP BY fol.EXME_FolDig_ID ");

		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			DB.setParameters(pstmt, params);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				FolDigBean bean = new FolDigBean(); 
				bean.setClient(rs.getString("client"));
				bean.setIsactive(rs.getString("Active").equals('Y'));
				bean.setTotTimbres(rs.getInt("totFolios"));
				bean.setFolDig_ID(rs.getInt("folID"));
				bean.setLstFolDet(getDetail(ctx, rs.getInt("clientID"), dateIni, dateFin, trxName));

				folioLst.add(bean);
			}

		}catch(Exception e){
			s_log.log(Level.SEVERE, "MEXMEFolDig.getInfo", e);
		} finally {
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}		

		return folioLst;
	}
	
	public static List<MEXMEFolDigDet> getDetail(Properties ctx, int clientID, Date dateIni, Date dateFin, String trxName){
		List<MEXMEFolDigDet> folioLst = new ArrayList<MEXMEFolDigDet>();

		PreparedStatement pstmt = null;
		ResultSet rs =null;		
		StringBuilder sql = new StringBuilder();

		sql.append(" SELECT det.* " )
		.append(" FROM exme_foldig fol ")		
		.append(" INNER JOIN EXME_FolDigDet det on fol.EXME_FolDig_ID = det.EXME_FolDig_ID ")
		.append(" INNER JOIN c_invoice i on det.c_invoice_id = i.c_invoice_id ")
		.append(" WHERE fol.IsActive = 'Y' AND  i.dateinvoiced between ? AND ?");

		if (clientID > 0) {
			sql.append(" AND  fol.assignclient_id = ?");
		}

		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setTimestamp(1, new Timestamp(dateIni.getTime()));
			pstmt.setTimestamp(2, new Timestamp(dateFin.getTime()));

			if (clientID > 0) {
				pstmt.setInt(3, clientID);
			}

			rs = pstmt.executeQuery();

			while (rs.next()) {
				MEXMEFolDigDet bean = new MEXMEFolDigDet(ctx, rs, trxName);
				folioLst.add(bean);
			}

		}catch(Exception e){
			s_log.log(Level.SEVERE, "MEXMEFolDig.getDetail", e);
		} finally {
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}		

		return folioLst;
	}

}
