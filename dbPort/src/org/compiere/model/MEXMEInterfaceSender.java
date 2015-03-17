package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;

public class MEXMEInterfaceSender extends X_EXME_InterfaceSender{

	private static final long serialVersionUID = 1L;
	private static CLogger s_log = CLogger.getCLogger (MEXMEInterfaceSender.class);

	public MEXMEInterfaceSender(Properties ctx, int EXME_InterfaceSender_ID, String trxName) {
		super(ctx, EXME_InterfaceSender_ID, trxName);
	}

	public MEXMEInterfaceSender(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	/**
	 * Obtiene informacion de la interfaz remitente segun su value
	 * @param ctx Contexto de la aplicacion
	 * @param trxName Nombre de transaccion
	 * @param value Valor del remitente
	 * @return Remitente
	 */
	public static MEXMEInterfaceSender getData(Properties ctx, String trxName, String value){
		MEXMEInterfaceSender sender = null;
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);

		sql.append("select * from EXME_InterfaceSender where VALUE = ?");
		//sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", "EXME_InterfaceSender"));

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setString(1, value);

			rs = pstmt.executeQuery();
			if(rs.next()){
				sender = new MEXMEInterfaceSender(ctx,rs,trxName);
			}

		} catch (Exception e){
			s_log.log(Level.SEVERE, "getSender ", e);
		} finally{
			DB.close(rs,pstmt);
			pstmt = null;
			rs =null;
		}
		return sender;
	}


	//-------------------------------------------Armando------------------------------------------

	/**
	 * Obtiene informacion de la interfaz remitente segun su estacion de servicio
	 * @param ctx Contexto de la aplicacion
	 * @param trxName Nombre de transaccion
	 * @param value Valor del remitente
	 * @return Remitente
	 */
	public static MEXMEInterfaceSender getSenderEstServicio(Properties ctx, String trxName, int estacionID){
		MEXMEInterfaceSender sender = null;
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);

		sql.append("select * from EXME_InterfaceSender where exme_estserv_id =?");
		//sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", "EXME_InterfaceSender"));
		//select i.* from  exme_interfacesender i
		//where exme_estserv_id = ? ;
		PreparedStatement pstmt = null;
		ResultSet rs = null; 
		try {  
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, estacionID); 

			rs = pstmt.executeQuery();
			if(rs.next()){
				sender = new MEXMEInterfaceSender(ctx,rs,trxName);
			}

		} catch (Exception e){
			s_log.log(Level.SEVERE, "getSender ", e);
		} finally{
			DB.close(rs,pstmt);
			pstmt = null;
			rs =null;
		}
		return sender;
	}
}
