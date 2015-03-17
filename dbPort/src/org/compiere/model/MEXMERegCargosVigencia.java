package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.DB;

/**
 * Creado: Octubre/2009<p>
 * @author Noelia
 */
public class MEXMERegCargosVigencia extends X_EXME_RegCargosVigencia {

	/**
	 * Serial
	 */
	private static final long serialVersionUID = 1L;
	
	private static CLogger s_log = CLogger.getCLogger(MEXMERegCargosVigencia.class);

	public MEXMERegCargosVigencia (Properties ctx, int EXME_RegCargosVigencia_ID, String trxName){
		super (ctx, EXME_RegCargosVigencia_ID, trxName);
	}

	public MEXMERegCargosVigencia (Properties ctx, ResultSet rs, String trxName){
		super(ctx, rs, trxName);
	}

	/** 
	 * Obtenemos el registro de cargos de vigencia de acuerdo al folio (DocumentNo)
	 * @param Properties ctx
	 * @param String documentNo
	 * @param String trxName
	 * @author Noelia
	 */
	 public static MEXMERegCargosVigencia getFromValue(Properties ctx,  String documentNo,  String trxName) {

			StringBuilder sql = new StringBuilder("SELECT * FROM EXME_RegCargosVigencia v WHERE v.documentNo = ?");
			sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, sql.toString(), "EXME_RegCargosVigencia", "v"));
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			MEXMERegCargosVigencia rcv = null;

			try {
				pstmt = DB.prepareStatement(sql.toString(), trxName);
				pstmt.setString(1, documentNo);
				rs = pstmt.executeQuery();

				if (rs.next()) {
					rcv = new MEXMERegCargosVigencia(ctx, rs, trxName);
				}

			} catch (Exception e) {
				s_log.log(Level.SEVERE, sql.toString(), e);
			} finally {
				DB.close(rs, pstmt);
			}
		return rcv;
	}	 
	 
 	/** 
	 * Obtenemos el numero de factura para el cual se realizo el folio para un paciente Externo
	 * @param Properties ctx
	 * @param Int EXME_RegCargosVigencia_ID
	 * @param String trxName
	 * @author Noelia
	 */
	 public static int getInvoice(Properties ctx,  int EXME_RegCargosVigencia_ID,  String trxName) {

		 	int C_Invoice_ID = 0;
		 	
			StringBuilder sql = new StringBuilder("SELECT C_Invoice_ID FROM EXME_RegCargosVigencia v ")								
								.append("WHERE v.EXME_RegCargosVigencia_ID = ? ")
								.append("AND v.EXME_CtaPac_ID is null AND v.EXME_Paciente_ID is not null ");
			sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, sql.toString(), "EXME_RegCargosVigencia", "v"));
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			try {
				pstmt = DB.prepareStatement(sql.toString(), trxName);
				pstmt.setInt(1, EXME_RegCargosVigencia_ID);
				rs = pstmt.executeQuery();

				if (rs.next()) {
					C_Invoice_ID = rs.getInt("C_Invoice_ID");
				}

			} catch (Exception e) {
				s_log.log(Level.SEVERE, sql.toString(), e);
			} finally {
				DB.close(rs, pstmt);
			}
		return C_Invoice_ID;
	}
	 
	 
	/** 
	 * Metodo que indica si la cuenta paciente ya tiene un folio generado
	 * @param Properties ctx
	 * @param Int EXME_CtaPac_ID
	 * @param String trxName
	 * @author Noelia
	 */
	 public static boolean isFolioAlready(Properties ctx,  int EXME_CtaPac_ID,  String trxName) {
		 	
		 	boolean exist = false;
		 	
			StringBuilder sql = new StringBuilder("SELECT * FROM EXME_RegCargosVigencia v ")
								.append("WHERE v.EXME_CtaPac_ID = ? ");								
			sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, sql.toString(), "EXME_RegCargosVigencia", "v"));
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			try {
				pstmt = DB.prepareStatement(sql.toString(), trxName);
				pstmt.setInt(1, EXME_CtaPac_ID);
				rs = pstmt.executeQuery();

				if (rs.next()) {
					exist = true;
				}

			} catch (Exception e) {
				s_log.log(Level.SEVERE, sql.toString(), e);
			} finally {
				DB.close(rs, pstmt);
			}
		return exist;
	}
	 
 /** 
	 * Metodo que indica si el numero de factura para el paciente seleccionado ya tiene un folio generado
	 * @param Properties ctx
	 * @param Int EXME_CtaPac_ID
	 * @param String trxName
	 * @author Noelia
	 */
	 public static boolean isInvoiceAlready(Properties ctx,  int EXME_Paciente_ID, int C_Invoice_ID,  String trxName) {
		 	
		 	boolean exist = false;
		 	
		 	StringBuilder sql = new StringBuilder("SELECT * FROM EXME_RegCargosVigencia v ")		 						
		 						.append("WHERE v.EXME_Paciente_ID = ? ")
		 						.append("AND v.C_Invoice_ID = ? ");
		 	sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, sql.toString(), "EXME_RegCargosVigencia", "v"));					
	
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			try {
				pstmt = DB.prepareStatement(sql.toString(), trxName);
				pstmt.setInt(1, EXME_Paciente_ID);
				pstmt.setInt(2, C_Invoice_ID);
				rs = pstmt.executeQuery();

				if (rs.next()) {
					exist = true;
				}

			} catch (Exception e) {
				s_log.log(Level.SEVERE, sql.toString(), e);
			} finally {
				DB.close(rs, pstmt);
			}
		return exist;
	}
}
