package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.apache.struts.util.LabelValueBean;
import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.WebEnv;

public class MEXMEMotivoReagenda extends X_EXME_MotivoReagenda {

	/** SerialVersionUID */
	private static final long serialVersionUID = 1L;
	
	/** Static Logger */
	private static CLogger s_log = CLogger.getCLogger(MEXMEVacuna.class);

	
	public MEXMEMotivoReagenda(Properties ctx, int EXME_MotivoReagenda_ID,
			String trxName) {
		super(ctx, EXME_MotivoReagenda_ID, trxName);
	}

	public MEXMEMotivoReagenda(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	/**
	 *  Devuelve un objeto tipo lista con los Motivo de Reagenda
	 *
	 *@param  ctx         Contexto
	 *@param  tabla       Tabla para buscar
	 *@param  cadena       value del registro (LIKE)
	 *@return             un objeto LabenValueBean con el ID y el Value
	 *@throws  Exception  en caso de ocurrir un error en la consulta
	 */
	public static List<LabelValueBean> getLVBMotivoReagenda(Properties ctx)
			throws Exception {

		//Lista de  tipos de anestesia
		List<LabelValueBean> motivoReagenda = new ArrayList<LabelValueBean>();
		
		if(ctx==null)
			return motivoReagenda;
		
		//Consulta
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		sql.append(" SELECT v.EXME_MotivoReagenda_ID , v.Value || ' - ' || v.Name as Nombre ")
		.append(" FROM EXME_MotivoReagenda v ")
		.append(" WHERE v.IsActive = 'Y' ")
		.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", "EXME_MotivoReagenda", "v"));

		sql.append(" ORDER BY v.Value, v.Name, v.EXME_MotivoReagenda_ID ");

		if (WebEnv.DEBUG) {
			s_log.log(Level.FINE, "SQL : " + sql.toString());
		}
		
		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				LabelValueBean lvb = new LabelValueBean(rs.getString("Nombre")
						, rs.getString("EXME_MotivoReagenda_ID"));
				motivoReagenda.add(lvb);
			}

		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e.getMessage());
			throw new Exception(e.getMessage());
		} finally {
			DB.close(rs, pstmt);
			pstmt = null;
			rs = null;
		}
		
		return motivoReagenda;
	}
}
