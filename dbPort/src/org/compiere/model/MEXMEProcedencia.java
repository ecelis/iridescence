package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.KeyNamePair;

/**
 * 
 * @author lama
 *
 */
public class MEXMEProcedencia extends X_EXME_Procedencia{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	/**	Static Logger				*/
	private static CLogger		s_log = CLogger.getCLogger (MEXMEProcedencia.class);

	/**
	 * Constructor de MEXMEProcedencia
	 * @param ctx                Propiedades
	 * @param EXME_Procedencia_ID  ID de Procedencia
	 * @param trxName            Nombre de la transaccion
	 *
	 */

	public MEXMEProcedencia(Properties ctx, int EXME_Procedencia_ID, String trxName) {
		super(ctx, EXME_Procedencia_ID, trxName);
	}

	/**
	 * Constructor de MEXMEProcedencia
	 * @param ctx                Propiedades
	 * @param rs                 Resultset con que se crea el objeto
	 * @param trxName            Nombre de la transaccion
	 *
	 */
	public MEXMEProcedencia(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}
	
	/**
	 * 
	 * @param ctx
	 * @param trxName
	 * @return
	 */
	public static List<KeyNamePair> get(Properties ctx, String trxName){
		final List<KeyNamePair> retValue = new ArrayList<KeyNamePair>();
		final  StringBuffer sql = new StringBuffer(Constantes.INIT_CAPACITY_ARRAY);
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try{
			sql.append("SELECT EXME_Procedencia_ID, Name ");
			sql.append(" FROM EXME_Procedencia ");
			sql.append(" WHERE IsActive='Y' ");
			sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name));
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			rs = pstmt.executeQuery();

			while (rs.next()){
				retValue.add(new KeyNamePair(rs.getInt(1), rs.getString(2)));
			}
		}
		catch(Exception e){
			s_log.log(Level.SEVERE, sql.toString(), e);
		}
		finally{
			DB.close(rs, pstmt);
		}

		return retValue;

	}

}
