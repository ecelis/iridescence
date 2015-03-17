package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;
import org.compiere.util.ValueNamePair;

public class MEXMEDeficiencyV extends X_EXME_Deficiency_V{
	
	public MEXMEDeficiencyV(Properties ctx, int EXME_Deficiency_V_ID,
			String trxName) {
		super(ctx, EXME_Deficiency_V_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MEXMEDeficiencyV(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

	public static List<KeyNamePair> getMeds(Properties ctx) {
		List<KeyNamePair> list = new ArrayList<KeyNamePair>();

		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);

		sql.append(" (select exme_medico_id, concat(name, ' ', apellido1) from exme_medico where exme_medico_id in ( ");
		sql.append(" select exme_medico_id from EXME_Deficiency_V where type <> 'FO' and AD_Client_ID IN ( ? ) AND AD_Org_ID IN ( ? ) ");
		sql.append(" group by exme_medico_id)) ");
		sql.append(" Union All ");
		sql.append(" (select ad_user_id, concat(name, '/', description) from ad_user where ad_user_id in ( ");
		sql.append(" select exme_medico_id from EXME_Deficiency_V where type = 'FO' and AD_Client_ID IN ( ? ) AND AD_Org_ID IN ( ? ) ");
		sql.append(" group by exme_medico_id)) ");

		

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setInt(1, Env.getAD_Client_ID(ctx));
			pstmt.setInt(2, Env.getAD_Org_ID(ctx));
			pstmt.setInt(3, Env.getAD_Client_ID(ctx));
			pstmt.setInt(4, Env.getAD_Org_ID(ctx));
			
			rs = pstmt.executeQuery();
			
			KeyNamePair aux = null;
			while (rs.next()) {
				aux = new KeyNamePair(rs.getInt(1), rs.getString(2));
				list.add(aux);
			}


		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			DB.close(rs, pstmt);
			pstmt = null;
			rs = null;
		}
		
		return list;
	}

}
