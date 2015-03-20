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

public class MEXMEProcAnestesico extends X_EXME_ProcAnestesico {
	
	/** SerialVersionUID */
	private static final long serialVersionUID = 1L;
	
	/** Static Logger */
	private static CLogger s_log = CLogger.getCLogger(MEXMEVacuna.class);


	public MEXMEProcAnestesico(Properties ctx, int EXME_ProcAnestesico_ID,
			String trxName) {
		super(ctx, EXME_ProcAnestesico_ID, trxName);
	}

	public MEXMEProcAnestesico(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	
	/**
	 *  Devuelve un objeto tipo lista con los tipos de anestesia
	 *
	 *@param  ctx         Contexto
	 *@param  tabla       Tabla para buscar
	 *@param  cadena       value del registro (LIKE)
	 *@return             un objeto LabenValueBean con el ID y el Value
	 *@throws  Exception  en caso de ocurrir un error en la consulta
	 */
	public static List<LabelValueBean> getLVBProcAnestesico(Properties ctx)
			throws Exception {

		//Lista de  tipos de anestesia
		List<LabelValueBean> procAnestesico = new ArrayList<LabelValueBean>();
		
		if(ctx==null)
			return procAnestesico;
		
		//Consulta
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		sql.append(" SELECT v.EXME_ProcAnestesico_ID , v.Value || ' - ' || v.Name as Nombre ")
		.append(" FROM EXME_ProcAnestesico v ")
		.append(" WHERE v.IsActive = 'Y' ")
		.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", "EXME_ProcAnestesico", "v"));

		sql.append(" ORDER BY v.Value, v.Name, v.EXME_ProcAnestesico_ID ");

		if (WebEnv.DEBUG) {
			s_log.log(Level.FINE, "SQL : " + sql.toString());
		}
		
		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				LabelValueBean lvb = new LabelValueBean(rs.getString("Nombre")
						, rs.getString("EXME_ProcAnestesico_ID"));
				procAnestesico.add(lvb);
			}

		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e.getMessage());
			throw new Exception(e.getMessage());
		} finally {
			DB.close(rs, pstmt);
			pstmt = null;
			rs = null;
		}
		
		return procAnestesico;
	}
}
