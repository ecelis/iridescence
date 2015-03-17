/*

 * Created on 04-may-2005

 */

package org.compiere.model;



import java.util.ArrayList;
import java.util.Properties;



/**

 * Modelo de Plan de Medicamentos

 * <b>Modificado: </b> $Author: taniap $<p>

 * <b>En :</b> $Date: 2006/02/23 23:36:31 $<p>

 *

 * @author Gisela Lee

 * @version $Revision: 1.3 $
 @deprecated use {@link MEXMEActPacienteDet} instead
 */

@Deprecated
public class MActPacienteDet /*extends X_EXME_ActPacienteDet*/ {

    /**
	 * 
	 */
	private static final long serialVersionUID = -6105338268428408488L;


    /**
 	*  Verificamos si la cuenta tiene al menos 1 linea 
 	*
 	*  @return True si tiene al menos 1 linea, false si no.
 	*/

 	public static boolean getForCtaPacId(Properties ctx, int ctaPacId, String trxName) throws Exception {
 		return MEXMEActPacienteDet.getForCtaPacId(ctx, ctaPacId, trxName);
// 		boolean retValue = true;
// 		StringBuffer sql = new StringBuffer("Select * from EXME_ActPacienteDet ")
// 		.append(" left join EXME_ActPaciente on (EXME_ActPaciente.EXME_ActPaciente_ID = EXME_ActPacienteDet.EXME_ActPaciente_ID) ")
//        .append(" where EXME_ActPaciente.isActive = 'Y' and EXME_ActPacienteDet.isActive = 'Y' and EXME_ActPaciente.EXME_CtaPac_ID = ").append(ctaPacId);
//
// 		sql = new StringBuffer(MEXMELookupInfo.addAccessLevelSQL(ctx, sql.toString(), "EXME_ActPacienteDet"));
// 		
// 		PreparedStatement pstmt = null;
// 		ResultSet rs = null;
//
// 		try {
//
// 			pstmt = DB.prepareStatement(sql.toString(), null);
// 			rs = pstmt.executeQuery();
//
// 			if (!rs.next()){
// 				retValue = false;
// 			}
//
// 		} catch (SQLException e) {
// 			throw new Exception(e.getMessage());
// 		} finally {
//             DB.close(rs,pstmt);
//             rs = null;
//             pstmt = null;
//         }
//
// 		return retValue;

 	}
 	
 	/**
	 * Noelia: Metodo para obtener el siguiente
	 * folio de una nota medica para determinado clienteID
	 */
 	public static int nextFolio(String trxName, int clientID)
	{
// 			String sql = "SELECT COALESCE(MAX(folio),0)+1 FROM EXME_ActPacienteDet WHERE AD_Client_ID=?";
//		    return DB.getSQLValue (trxName, sql, clientID);
 		return MEXMEActPacienteDet.nextFolio(trxName, clientID);
	}	
 	
 	/**
 	 * @author Lorena Lama
 	 * @param ctx
 	 * @param folio
 	 * @param trxName
 	 * @return
 	 */
 	public static ArrayList<Integer> getCuestIdByFolio(Properties ctx, Integer actPaciente , Integer folio, String trxName) {
 		return MEXMEActPacienteDet.getCuestIdByFolio(ctx, actPaciente, folio, trxName);
//        ArrayList<Integer> retValue = new ArrayList<Integer>();
//         
//  		StringBuilder sql = new StringBuilder("Select distinct exme_cuestionario_id from EXME_ActPacienteDet ");
//  		boolean entro = false;
//  		if(actPaciente != null && actPaciente.intValue() > 0){
//  			sql.append(" where exme_actpaciente_id = ").append(actPaciente.intValue());
//  			entro =  true;
//  		}
//  		if(folio != null && folio.intValue() > 0){
//  			if(entro)
//              sql.append("  and folio = ").append(folio.intValue());
//  			else{
//  				sql.append("  where folio = ").append(folio.intValue());
//  				entro = true;
//  			}
//  		}
//  		
//  		if(entro)
//  		      sql.append("  and exme_cuestionario_id is not null").append(" and isActive = 'Y' ");
//  		else
//  			sql.append("  where exme_cuestionario_id is not null").append(" and isActive = 'Y' ");
//  		
//  		if(Env.getUserPatientID(ctx) < 0) //Si es un usuario paciente no agrega el accessLevel
//  			sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, sql.toString(), I_EXME_ActPacienteDet.Table_Name));
//                                     
//  		
//
// 		PreparedStatement pstmt = null;
// 		ResultSet rs = null;
//
// 		try {
//
// 			pstmt = DB.prepareStatement(sql.toString(), null);
// 			rs = pstmt.executeQuery();
//
// 			while (rs.next()) {
// 				retValue.add(new Integer(rs.getInt(1)));
// 			}
//
// 		} catch (SQLException e) {
// 			return null;
// 		} finally {
// 			  DB.close(rs,pstmt);
// 			rs = null;
// 			pstmt = null;
// 		}
//
// 		return retValue;

 	}
 	
 	public static ArrayList<Integer> getCuestIdByFolio(Properties ctx, Integer actPaciente , Integer folio, Integer cuestID, String trxName) {
 		return MEXMEActPacienteDet.getCuestIdByFolio(ctx, actPaciente, folio, cuestID, trxName);
//        ArrayList<Integer> retValue = new ArrayList<Integer>();
//         
//  		StringBuilder sql = new StringBuilder("Select distinct exme_cuestionario_id from EXME_ActPacienteDet ");
//  		boolean entro = false;
//  		if(actPaciente != null && actPaciente.intValue() > 0){
//  			sql.append(" where exme_actpaciente_id = ").append(actPaciente.intValue());
//  			entro =  true;
//  		}
//  		if(folio != null && folio.intValue() > 0){
//  			if(entro)
//              sql.append("  and folio = ").append(folio.intValue());
//  			else{
//  				sql.append("  where folio = ").append(folio.intValue());
//  				entro = true;
//  			}
//  		}
//  		
//  		if(cuestID != null && cuestID.intValue() > 0){
//  			if(entro)
//              sql.append("  and exme_cuestionario_id = ").append(cuestID.intValue());
//  			else{
//  				sql.append("  where exme_cuestionario_id = ").append(cuestID.intValue());
//  				entro = true;
//  			}
//  		}
//  		
//  		if(entro)
//  		      sql.append("  and exme_cuestionario_id is not null").append(" and isActive = 'Y' ");
//  		else
//  			sql.append("  where exme_cuestionario_id is not null").append(" and isActive = 'Y' ");
//  		
//  		if(Env.getUserPatientID(ctx) < 0) //Si es un usuario paciente no agrega el accessLevel
//  			sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, sql.toString(), I_EXME_ActPacienteDet.Table_Name));
//                                     
//  		
//
// 		PreparedStatement pstmt = null;
// 		ResultSet rs = null;
//
// 		try {
//
// 			pstmt = DB.prepareStatement(sql.toString(), null);
// 			rs = pstmt.executeQuery();
//
// 			while (rs.next()) {
// 				retValue.add(new Integer(rs.getInt(1)));
// 			}
//
// 		} catch (SQLException e) {
// 			return null;
// 		} finally {
// 			  DB.close(rs,pstmt);
// 			rs = null;
// 			pstmt = null;
// 		}
//
// 		return retValue;

 	}
 	
 	
 	/**
 	 * @author Lorena Lama
 	 * @param actpaciente
 	 * @return
 	 */
 	public static Integer getExistFolioAct(Integer actpaciente) {
 		return MEXMEActPacienteDet.getExistFolioAct(actpaciente);
//		Integer retorno = null;
//    
//    StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
//	 
//     
// sql.append("	select t.folio")
//    .append("    from EXME_ActPacienteDet  t")
//    .append("    where t.exme_actpaciente_id = ?")
//    .append("    and t.isActive = 'Y'")
//    .append("    order by t.created");
//		
//
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//
//		try {
//			pstmt = DB.prepareStatement(sql.toString(), null);
//        pstmt.setInt(1, actpaciente.intValue());
//			
//			rs = pstmt.executeQuery();
//			
//			if (rs.next()) {
//				retorno = new Integer(rs.getInt(1));
//			}
//			
//			
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			  DB.close(rs,pstmt);
//		}
//
//		return retorno;

	}
 	
 	public static Integer getExistFolioActForms(Integer encountFormsID, Integer questID) {
return MEXMEActPacienteDet.getExistFolioActForms(encountFormsID, questID);
//		Integer retorno = null;
//    
//    StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
//	 
//     
// sql.append("	select t.folio")
//    .append("    from EXME_ActPacienteDet  t")
//    .append("    where EXME_ENCOUNTERFORMS_ID = ?")
//    .append("    and exme_cuestionario_id = ?")
//    .append("    and t.isActive = 'Y'")
//    .append("    order by t.created");
//		
//
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//
//		try {
//			pstmt = DB.prepareStatement(sql.toString(), null);
//        pstmt.setInt(1, encountFormsID.intValue());
//        pstmt.setInt(2, questID.intValue());
//			
//			rs = pstmt.executeQuery();
//			
//			if (rs.next()) {
//				retorno = new Integer(rs.getInt(1));
//			}
//			
//		} catch (SQLException e) {
//			e.printStackTrace();
//			
//		}  finally {
//			  DB.close(rs,pstmt);
//		}
//
//		return retorno;

	}
 	
}