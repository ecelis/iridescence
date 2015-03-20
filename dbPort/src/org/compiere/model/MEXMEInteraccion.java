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

public class MEXMEInteraccion extends X_EXME_Interaccion {

	private static final long serialVersionUID = 1L;
	
	/**	Static Logger				*/
	private static CLogger		s_log = CLogger.getCLogger (MEXMEInteraccion.class);
   

	public MEXMEInteraccion (Properties ctx, int EXME_Interaccion_ID, String trxName)
	{
		super (ctx, EXME_Interaccion_ID, trxName);
	}

	public MEXMEInteraccion (Properties ctx, ResultSet rs, String trxName)
	{
		super (ctx, rs, trxName);
	}

	/**
	 * Busca todos los productos que hacen interacci�n con 
	 * el producto enviado como parametro
	 * @param ctx
	 * @param productoID
	 * @param trxName
	 * @return
	 * @throws Exception
	 */
	public static List<BasicoTresProps> descripInteraccion(Properties ctx, 
			 long productoID,  String trxName) throws Exception {

		List<BasicoTresProps> lista = new ArrayList<BasicoTresProps>();

		if (ctx == null) {
			return null;
		}
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append("SELECT i.Value || '-' || i.Name || ':' || NVL(i.Description, ' ') AS Interaccion,  ")
		.append(" i.EXME_Interaccion_ID, i.M_Product_Interac_ID FROM EXME_Interaccion i ")
		.append(" WHERE i.IsActive = 'Y' AND i.M_Product_ID = ? ")
		.append(MEXMELookupInfo.addAccessLevelSQL(ctx," ","EXME_Interaccion","i"));

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setLong(1, productoID);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				BasicoTresProps exp = new BasicoTresProps(rs.getInt("M_Product_Interac_ID"), 
						String.valueOf(rs.getInt("EXME_Interaccion_ID")),rs.getString("Interaccion"),"");
				lista.add(exp);
			}


		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
			throw new Exception(e.getMessage());
		}finally {
			DB.close(rs, pstmt);
				pstmt = null;
				rs = null;
		}

		return lista;
	}

}
