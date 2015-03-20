package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.Env;

/**
 * Elegibilidad
 *
 */
public class MEXMEBeneficiosH extends X_EXME_BeneficiosH {
	
	/** serialVersionUID */
	private static final long serialVersionUID = -1623960634158215114L;
	/**	Static Logger				*/
	private static CLogger		s_log = CLogger.getCLogger (MEXMEBeneficiosH.class);

	/**
	 * Costructor
	 * @param ctx Constexto
	 * @param EXME_BeneficiosH_ID ID del registro de la tabla EXME_BeneficiosH_ID
	 * @param trxName Nombre de la transaccion
	 */
	public MEXMEBeneficiosH(Properties ctx, int EXME_BeneficiosH_ID, String trxName) {
		super(ctx, EXME_BeneficiosH_ID, trxName);
	}
	
	public MEXMEBeneficiosH(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}
	
	/**
	 *  verificar si ya le hicieron elegibilidad
	 *  funcion estatica getActive de la clase se llama MEXMEBeneficiosH
	 *	revisa una vista EXME_VISTAELEGIBILIDAD para que siempre mostrar el
	 *	estatus de la ultima elegibilidad de un paciente en particular
	 *	y comparara la fecha de la solicitud contra la configuracion de
	 *	en que momento "caduca" una elegibilidad
	 *	el query es sencillo, ya que solo busca sobre la vista para saber
	 *	si esta ISEXPIRED = 'N' para un paciente en particular
	 *	si esta en Y entonces ya expiro la elegibilidad y ya no hay
	 *	elegibilidad "activa" para el paciente
	 *	esta vista se usa para el proceso normal de solicitar y consultar
	 *	elegibilidad
	 * @param EXME_Paciente_ID la llamada la funcion te pide el id de paciente 
	 * @return y te devuelve 0 si no elegibilidad activa o el ID de la tabla de EXME_BENEFICIOSH
	 *         (es la tabla encabezado de las solicitudes de elegibilidad) que
	 *		   representa la elegibilidad activa
	 */
	public static int getActive(int EXME_Paciente_ID) {
		int res = 0;
		
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append(" SELECT EXME_BENEFICIOSH_ID ")
		.append(" FROM EXME_VISTAELEGIBILIDAD ")
		.append(" WHERE ISACTIVE = 'Y' ")
		.append(" AND ISEXPIRED = 'N' ")
		.append(" AND EXME_PACIENTE_ID = ? ");
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setInt(1, EXME_Paciente_ID);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				res = rs.getInt("EXME_BENEFICIOSH_ID");
			}
			
		} catch (Exception ex) {
			s_log.log(Level.SEVERE, "getActive : " + sql, ex);
		} finally {
			DB.close(rs, pstmt);
		}
		return res;
	}
	
	public static MEXMEBeneficiosH[] getActives(Properties ctx, int EXME_Paciente_ID) {
		ArrayList<MEXMEBeneficiosH> list = new ArrayList<MEXMEBeneficiosH>();
		
		StringBuffer sql = new StringBuffer(Constantes.INIT_CAPACITY_ARRAY);
		
		sql.append("SELECT EXME_BENEFICIOSH_ID FROM EXME_VISTAELEGIBILIDAD WHERE IsActive = 'Y' AND ISEXPIRED = 'N' AND EXME_PACIENTE_ID = ? ");
		//sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name));
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setInt(1, EXME_Paciente_ID);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				MEXMEBeneficiosH reg = new MEXMEBeneficiosH(ctx, rs.getInt("EXME_BENEFICIOSH_ID"), null);
				list.add(reg);
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, "gets: " + sql, e);
		} finally {
			DB.close(rs, pstmt);
		}
		MEXMEBeneficiosH[] regs = new MEXMEBeneficiosH[list.size()];
		list.toArray(regs);
		
		return regs;
	}
	
	public static int getActivoVigente(int EXME_Paciente_ID) {
		int res = 0;
		
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append(" SELECT EXME_BENEFICIOSH_ID ")
		.append(" FROM EXME_BENEFICIOSH ")
		.append(" WHERE ISACTIVE = 'Y' ")
		.append(" AND EndDate > "+ DB.TO_DATE(DB.getTimestampForOrg(Env.getCtx())) + " ")
		.append(" AND EXME_PACIENTE_ID = ? order by EndDate desc ");
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setInt(1, EXME_Paciente_ID);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				res = rs.getInt("EXME_BENEFICIOSH_ID");
			}
			
		} catch (Exception ex) {
			s_log.log(Level.SEVERE, "getActive : " + sql, ex);
		} finally {
			DB.close(rs, pstmt);
		}
		return res;
	}
	
	public static boolean linkToCtaPac(Properties ctx,int EXME_BeneifciosH_ID, int EXME_CtaPac_ID, int EXME_PacienteAseg_ID){
		if(EXME_BeneifciosH_ID > 0 && EXME_CtaPac_ID > 0){
			MEXMEClaimCodes codes = new MEXMEClaimCodes(ctx, 0, null);
			MEXMECtaPac ctaPac = new MEXMECtaPac(ctx, EXME_CtaPac_ID, null);
			codes.setEXME_CtaPac_ID(EXME_CtaPac_ID);
			codes.setAD_Table_ID(MEXMEBeneficiosH.Table_ID);
			codes.setRecord_ID(EXME_BeneifciosH_ID);
			codes.setAD_TableOrig_ID(MEXMEPacienteAseg.Table_ID);
			codes.setRecordOrig_ID(EXME_PacienteAseg_ID);
			codes.setEXME_Paciente_ID(ctaPac.getEXME_Paciente_ID());
			return codes.save();
		}else{
			return false;
		}
	}
	
}
