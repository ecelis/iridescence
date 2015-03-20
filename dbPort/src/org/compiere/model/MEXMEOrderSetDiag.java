package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;

public class MEXMEOrderSetDiag extends X_EXME_OrderSetDiag{

	private boolean isNew = false;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** log de la clase*/
	private static CLogger log = CLogger.getCLogger(MEXMEOrderSetDiag.class);

	public MEXMEOrderSetDiag(Properties ctx, int EXME_OrderSetDiag_ID,
			String trxName) {
		super(ctx, EXME_OrderSetDiag_ID, trxName);
	}


	public MEXMEOrderSetDiag(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}
	

	/**
	 * Obtiene los diagnosticos del order set, separados por coma
	 * @param ctx
	 * @param orderSetID
	 * @return String
	 */
	public static String getDiagnosisStr(final Properties ctx, final int orderSetID){

		StringBuilder strResult = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);

		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		PreparedStatement pstmt = null;
		ResultSet result = null;
		try {
			sql.append("SELECT EXME_Diagnostico.Name ")
				.append(" FROM EXME_OrderSetDiag ")
				.append("INNER JOIN EXME_Diagnostico ON EXME_OrderSetDiag.EXME_Diagnostico_ID = EXME_Diagnostico.EXME_Diagnostico_ID ")
				.append("WHERE EXME_ORDERSETDIAG.EXME_OrderSet_ID = ? AND EXME_ORDERSETDIAG.ISACTIVE = 'Y' " );
			
//			sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, sql.toString(), Table_Name));

			sql.append(" ORDER BY EXME_Diagnostico.Name ");
			
			pstmt = DB.prepareStatement(sql.toString(), null);
				pstmt.setInt(1, orderSetID);
			
			result = pstmt.executeQuery();
			while(result.next()){
				if(strResult.length()>0){
					strResult.append(", ");
				}
				strResult.append(result.getString("Name"));
			}
		} catch (SQLException e) {
			log.log(Level.SEVERE, e.getLocalizedMessage());
		} finally {
			DB.close(result,pstmt);
		}

		return strResult.toString();
	}
	

	/**
	 * Obtiene los diagnosticos del order set
	 * @param ctx
	 * @param orderSetID
	 * @return List<MEXMEOrderSetDiag>
	 */
	public static List<MEXMEOrderSetDiag> getDiagnosis(final Properties ctx, final int orderSetID){

		List<MEXMEOrderSetDiag> arrayDiagnosis = new ArrayList<MEXMEOrderSetDiag>();

		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		PreparedStatement pstmt = null;
		ResultSet result = null;
		try {
			sql.append("SELECT EXME_OrderSetDiag.* ")
				.append(" FROM EXME_OrderSetDiag ")
				.append(" INNER JOIN EXME_Diagnostico on (EXME_OrderSetDiag.EXME_Diagnostico_ID = EXME_Diagnostico.EXME_Diagnostico_ID) ")
				.append("WHERE EXME_OrderSetDiag.EXME_OrderSet_ID = ?" )
				.append(" AND EXME_OrderSetDiag.isActive = 'Y' ");
			
//			sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, sql.toString(), Table_Name));

			sql.append(" ORDER BY EXME_Diagnostico.value ");
			
			pstmt = DB.prepareStatement(sql.toString(), null);
				pstmt.setInt(1, orderSetID);
			
			result = pstmt.executeQuery();
			while(result.next()){
				arrayDiagnosis.add(new MEXMEOrderSetDiag(ctx, result, null));
			}
		} catch (SQLException e) {
			log.log(Level.SEVERE, e.getLocalizedMessage());
		} finally {
			DB.close(result,pstmt);
		}

		return arrayDiagnosis;
	}
	
	private MDiagnostico diagnostic = null;

	public MDiagnostico getDiagnostic(Properties ctx) {
		if (diagnostic == null) {
			diagnostic = new MDiagnostico(ctx, getEXME_Diagnostico_ID(), null);
		}
		return diagnostic;
	}

	/**
	 * 
	 * @param trxName nombre de transaccion
	 * @return
	 * @throws Exception
	 */
	public static List<MDiagnostico> getDiagnosticDefault(final Properties ctx, final int OrderSetDefault) throws Exception {

		List<MDiagnostico> list = new ArrayList<MDiagnostico>();
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		
		sql.append(" SELECT EXME_Diagnostico_ID ")
		.append(" FROM EXME_ORDERSETDIAG ")
		.append(" WHERE EXME_ORDERSETDIAG.EXME_OrderSet_ID = ? ") 
		.append(" AND EXME_ORDERSETDIAG.isActive = 'Y' ");


		ResultSet rs = null;
		PreparedStatement pstmt = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setInt(1, OrderSetDefault);

			rs = pstmt.executeQuery();
			while (rs.next()) { 
				MDiagnostico diagnostic = new MDiagnostico(ctx, rs.getInt(MDiagnostico.COLUMNNAME_EXME_Diagnostico_ID), null);				
				list.add(diagnostic);
			}			
		} catch (Exception e) {
			log.log(Level.SEVERE, sql.toString(), e.getMessage());
			throw new Exception("Error al eliminar registros");
		} finally {
			DB.close(rs, pstmt);
		}
		return list;
	}


	public void setNew(boolean isNew) {
		this.isNew = isNew;
	}


	public boolean isNew() {
		return isNew;
	}

}
