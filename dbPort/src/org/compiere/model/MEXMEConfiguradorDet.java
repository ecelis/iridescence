package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.DB;

/**
 * Detalles del Configurador (Pasos)
 * 
 * @author odelarosa
 * 
 */
public class MEXMEConfiguradorDet extends X_EXME_ConfiguradorDet {

	private static final long serialVersionUID = -343441181930565107L;
	private static CLogger s_log = CLogger.getCLogger(MEXMEConfiguradorDet.class);

	public MEXMEConfiguradorDet(Properties ctx, int EXME_ConfiguradorDet_ID, String trxName) {
		super(ctx, EXME_ConfiguradorDet_ID, trxName);
	}

	public MEXMEConfiguradorDet(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	/**
	 * Obtiene los pasos del Configurador
	 * 
	 * @param ctx
	 *            Contexto
	 * @param EXME_Configurador_ID
	 *            Identificador del Configurador
	 * @return Listado de pasos del Configurador
	 */
	public static List<MEXMEConfiguradorDet> getSteps(Properties ctx, int EXME_Configurador_ID) {
		StringBuilder st = new StringBuilder("select * from EXME_ConfiguradorDet det ");
		st.append("where det.EXME_Configurador_ID = ? and det.isActive = 'Y' ");
		st = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, st.toString(), "EXME_ConfiguradorDet", "det"));
		st.append(" order by det.seqno asc");
		List<MEXMEConfiguradorDet> lst = new ArrayList<MEXMEConfiguradorDet>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(st.toString(), null);
			pstmt.setInt(1, EXME_Configurador_ID);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				lst.add(new MEXMEConfiguradorDet(ctx, rs, null));
			}
			
		} catch (Exception e) {
			s_log.log(Level.SEVERE, st.toString(), e);
		} finally {
			DB.close(rs, pstmt);
		}
		return lst;
	}

	/**
	 * @see org.compiere.model.X_EXME_ConfiguradorDet#getName()
	 */
	@Override
	public String getName() {
		return get_Translation(COLUMNNAME_Name);
	}

	/**
	 * @see org.compiere.model.X_EXME_ConfiguradorDet#getDescription()
	 */
	@Override
	public String getDescription() {
		return get_Translation(COLUMNNAME_Description);
	}

}
