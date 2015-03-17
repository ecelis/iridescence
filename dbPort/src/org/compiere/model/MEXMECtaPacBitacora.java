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

import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;

/**
 * @author Alejandro
 * 
 */
public class MEXMECtaPacBitacora extends X_EXME_CtaPacBitacora {

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 5201881151407858025L;
	/** Static Logger */
	private static CLogger		s_log				= CLogger.getCLogger(MEXMECtaPacBitacora.class);

	public MEXMECtaPacBitacora(Properties ctx, int EXME_CtaPacBitacora_ID, String trxName) {
		super(ctx, EXME_CtaPacBitacora_ID, trxName);
	}

	public MEXMECtaPacBitacora(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	/**
	 * Obtiene todos los registros de la bitacora a partir de una CtaPaciente
	 * 
	 * @param ctx
	 * @param EXME_CtaPac_ID
	 * @param trxName
	 * @return
	 */
	public static MEXMECtaPacBitacora[] get(Properties ctx, int EXME_CtaPac_ID, String trxName) {

		MEXMECtaPacBitacora[] retValue = null;
		List<MEXMECtaPacBitacora> list = new ArrayList<MEXMECtaPacBitacora>();

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);

		try {

			sql.append("SELECT * FROM EXME_CtaPacBitacora WHERE isActive = 'Y' AND EXME_CtaPac_ID = ? ");
			sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", "EXME_CtaPacBitacora"));

			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, EXME_CtaPac_ID);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				list.add(new MEXMECtaPacBitacora(ctx, rs, trxName));
			}

		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
			pstmt = null;
			rs = null;
			sql = null;
		}

		retValue = new MEXMECtaPacBitacora[list.size()];
		list.toArray(retValue);

		return retValue;
	}

	public static MEXMECtaPacBitacora create(Properties ctx, int EXME_CtaPac_ID, String trxName) {
		final MEXMECtaPacBitacora ctapacbit = new MEXMECtaPacBitacora(ctx, 0, trxName);
		ctapacbit.setEXME_CtaPac_ID(EXME_CtaPac_ID);
		return ctapacbit;
	}

	public static boolean open(Properties ctx, int EXME_CtaPac_ID, String trxName) {
		final MEXMECtaPacBitacora ctapacbit = create(ctx, EXME_CtaPac_ID, trxName);
		ctapacbit.setOpcion(OPCION_Open);
		return ctapacbit.save();
	}

	public static boolean close(Properties ctx, int EXME_CtaPac_ID, String trxName) {
		final MEXMECtaPacBitacora ctapacbit = create(ctx, EXME_CtaPac_ID, trxName);
		ctapacbit.setOpcion(OPCION_Close);
		return ctapacbit.save();
	}

	public static boolean reactivation(Properties ctx, int EXME_CtaPac_ID, String trxName) {
		final MEXMECtaPacBitacora ctapacbit = create(ctx, EXME_CtaPac_ID, trxName);
		ctapacbit.setOpcion(OPCION_Reactivation);
		return ctapacbit.save();
	}

}
