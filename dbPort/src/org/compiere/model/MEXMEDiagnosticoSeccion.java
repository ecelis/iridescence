package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
/**
 * Modelo para la tabla EXME_DiagnosticoSeccion
 * @author raul
 *
 */
public class MEXMEDiagnosticoSeccion extends X_EXME_DiagnosticoSeccion {


	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 5052284356433004522L;
	/*Log*/
	private static CLogger log = CLogger.getCLogger (MEXMEDiagnosticoSeccion.class);
	
	public MEXMEDiagnosticoSeccion(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}
	
	public MEXMEDiagnosticoSeccion(Properties ctx,
			int EXME_DiagnosticoSeccion_ID, String trxName) {
		super(ctx, EXME_DiagnosticoSeccion_ID, trxName);
	}
	
	/**
	 * Objeto MEXMESectionBody
	 * @return
	 */
	public MEXMESectionBody getSection(){
		return new MEXMESectionBody(getCtx(),getEXME_SectionBody_ID(),get_TrxName());
	}
	
	/**
	 * Obtiene el objeto MEXMEDiagnosticoSeccion
	 * con el diagnostico proporcionado
	 * @param ctx
	 * @param EXME_Diagnostico_ID
	 * @param trxName
	 * @return
	 */
	public static MEXMEDiagnosticoSeccion getFromDiag(Properties ctx, int EXME_Diagnostico_ID, String trxName){
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MEXMEDiagnosticoSeccion section = null;
		try{
			sql.append(" SELECT REL.EXME_DIAGNOSTICOSECCION_ID FROM EXME_DIAGNOSTICOSECCION REL " )
			.append(" WHERE REL.EXME_DIAGNOSTICO_ID = ? ")
			.append(" AND REL.ISACTIVE = 'Y' ");
			
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, EXME_Diagnostico_ID);
			rs = pstmt.executeQuery();

			if(rs.next()){
				section = new MEXMEDiagnosticoSeccion(ctx, rs.getInt(1), trxName);
			}
		}catch(Exception e){
			log.log(Level.SEVERE, sql.toString(), e);
		}finally{
			DB.close(rs,pstmt);
			pstmt = null;
			rs =null;
		}
		return section;
	}
}
