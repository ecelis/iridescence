/**
 * 
 */
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

/**
 * @author Expert
 *
 */
@SuppressWarnings("serial")
public class MEXMETipoPacArea extends X_EXME_TipoPacArea {

	/**	Static Logger				*/
	private static CLogger log = CLogger.getCLogger (MEXMETipoPacArea.class);
	
	/**
	 * @param ctx
	 * @param EXME_TipoPacArea_ID
	 * @param trxName
	 */
	public MEXMETipoPacArea(Properties ctx, int EXME_TipoPacArea_ID,
			String trxName) {
		super(ctx, EXME_TipoPacArea_ID, trxName);
	}

	/**
	 * constructor
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MEXMETipoPacArea(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}
	
	/**
	 * executa la consulta 
	 * @param ctx
	 * @param sql
	 * @param params
	 * @return listado de objetos tipo pac
	 */
	public static List<MEXMETipoPaciente> get(Properties ctx, String sql, List<?> params){

		final List<MEXMETipoPaciente> tipoPacienteLista = new ArrayList<MEXMETipoPaciente>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			
			pstmt = DB.prepareStatement(sql.toString(), null);
			DB.setParameters(pstmt, params);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				tipoPacienteLista.add(new MEXMETipoPaciente(ctx, rs, null));
			}
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getLocalizedMessage());
		} finally {
			DB.close(rs, pstmt);
		}
		return tipoPacienteLista;
	}

	/**
	 * Consulta de tipos de paciente por area
	 * @param ctx
	 * @param tipoAreas
	 * @return
	 */
	public static List<MEXMETipoPaciente> get(Properties ctx, String tipoAreas){
		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append(" SELECT EXME_TipoPaciente.*  ");
		sql.append(" FROM   EXME_TipoPaciente  ");
		sql.append(" INNER JOIN EXME_TipoPacArea tpa ON  EXME_TipoPaciente.EXME_TipoPaciente_ID = tpa.EXME_TipoPaciente_ID ");
		sql.append(" WHERE EXME_TipoPaciente.IsActive = 'Y' ");
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", X_EXME_TipoPaciente.Table_Name));
		sql.append(" AND tpa.EXME_Area_ID IN (");
		sql.append(tipoAreas);
		sql.append(") ORDER BY EXME_TipoPaciente.Name ");
		
		return MEXMETipoPaciente.get(ctx, sql.toString(), null);
	}

	/**
	 * Consulta de Area relacionadas a un tipo de paciente
	 * @param ctx
	 * @param tipoAreas
	 * @return
	 * @deprecated use {@link MEXMEArea#getAreas(Properties)}
	 */
	public static List<LabelValueBean> getAreas(Properties ctx){
		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append(" SELECT EXME_Area.Name , EXME_Area.EXME_Area_ID ");
		sql.append(" FROM   EXME_Area  ");
		sql.append(" INNER JOIN EXME_TipoPacArea tpa ON  EXME_Area.EXME_Area_ID = tpa.EXME_Area_ID ");
		sql.append(" WHERE EXME_Area.IsActive = 'Y'  AND tpa.IsActive = 'Y' ");
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", X_EXME_Area.Table_Name));
		sql.append(" GROUP BY EXME_Area.Name , EXME_Area.EXME_Area_ID ");
		sql.append(" ORDER BY EXME_Area.Name ");
		
		return MEXMEArea.get(ctx, sql.toString(), null, false, null);
	}
}
