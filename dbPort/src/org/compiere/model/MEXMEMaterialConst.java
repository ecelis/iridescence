package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.DB;

public class MEXMEMaterialConst extends X_EXME_Material_Const{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**	Static Logger				*/
	private static CLogger		s_log = CLogger.getCLogger (MEXMEMaterialConst.class);

	/**
	 * Constructor de MEXMEMaterialConst
	 * @param ctx                Propiedades
	 * @param EXME_Material_Const_ID  ID de Procedencia
	 * @param trxName            Nombre de la transaccion
	 *
	 */

	public MEXMEMaterialConst(Properties ctx, int EXME_Material_Const_ID, String trxName) {
		super(ctx, EXME_Material_Const_ID, trxName);
	}
	
	/**
	 * Constructor de MEXMEMaterialConst
	 * @param ctx                Propiedades
	 * @param rs                 Resultset con que se crea el objeto
	 * @param trxName            Nombre de la transaccion
	 *
	 */
 
	public MEXMEMaterialConst(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}
	
	public static MEXMEMaterialConst[] getMaterialesConst(Properties ctx, String trxName)
	    {
			String sql = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			ArrayList<MEXMEMaterialConst> resultados = new ArrayList<MEXMEMaterialConst>();
			try{
				sql = "SELECT * FROM EXME_Material_Const WHERE IsActive = 'Y'";
				
				sql = MEXMELookupInfo.addAccessLevelSQL(ctx, sql, "EXME_Material_Const");
				
				pstmt = DB.prepareStatement(sql, trxName);
				
				rs = pstmt.executeQuery();
				while (rs.next()){
					MEXMEMaterialConst materialConst = new MEXMEMaterialConst(ctx, rs, trxName);
					resultados.add(materialConst);
				}
			}
			catch(Exception e){
	    		s_log.log(Level.SEVERE, sql.toString(), e);
			}
			finally{
				DB.close(rs,pstmt);
			}
			MEXMEMaterialConst[] reenviar = new MEXMEMaterialConst[resultados.size()];
			resultados.toArray(reenviar);
			return reenviar;
	    }
	    
}
