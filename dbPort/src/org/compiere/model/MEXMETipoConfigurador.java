package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;

/**
 * Tipo de Configurador
 *
 * @author odelarosa
 *
 */
/**
 * @author odelarosa
 *
 */
public class MEXMETipoConfigurador extends X_EXME_TipoConfigurador {

	private static final long serialVersionUID = 7639256095797525560L;
	private static CLogger s_log = CLogger.getCLogger(MEXMETipoConfigurador.class);

	public MEXMETipoConfigurador(Properties ctx, int EXME_TipoConfigurador_ID, String trxName) {
		super(ctx, EXME_TipoConfigurador_ID, trxName);
	}

	public MEXMETipoConfigurador(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	/**
	 * Obtiene los Tipos de Configuradores
	 *
	 * @param ctx
	 *            Contexto
	 * @return Listado de Tipos de Configuradores
	 */
	public static List<MEXMETipoConfigurador> get(Properties ctx) {
		StringBuilder st = new StringBuilder("select * from EXME_TipoConfigurador det where isActive = 'Y' ");
		st = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, st.toString(), "EXME_TipoConfigurador", "det"));
		st.append(" order by det.created asc");
		List<MEXMETipoConfigurador> lst = new ArrayList<MEXMETipoConfigurador>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(st.toString(), null);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				lst.add(new MEXMETipoConfigurador(ctx, rs, null));
			}

		} catch (Exception e) {
			s_log.log(Level.SEVERE, st.toString(), e);
		} finally {
			DB.close(rs, pstmt);
		}
		return lst;
	}

	/**
	 * Configuradores por tipo de Cliente
	 *
	 * @param ctx
	 * @param tipoCliente
	 * @return
	 */
	public static List<MEXMETipoConfigurador> getFromTipo(Properties ctx, String tipoCliente) {
		StringBuilder st = new StringBuilder("select * from exme_tipoconfigurador where tipoarea = ? ");
		st = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, st.toString(), "EXME_TipoConfigurador"));
		st.append(" and isactive = 'Y' order by created asc");
		List<MEXMETipoConfigurador> lst = new ArrayList<MEXMETipoConfigurador>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(st.toString(), null);
			pstmt.setString(1, tipoCliente);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				lst.add(new MEXMETipoConfigurador(ctx, rs, null));
			}

		} catch (Exception e) {
			s_log.log(Level.SEVERE, st.toString(), e);
		} finally {
			DB.close(rs, pstmt);
		}
		return lst;
	}

	/**
	 * Detalles del Tipo de Configurador
	 *
	 * @return
	 */
	public List<MEXMETipoConfiguradorDet> getConfiguradores() {

		StringBuilder st = new StringBuilder("SELECT tcd.* \n")
		.append("FROM EXME_TipoConfiguradorDet tcd \n")
		.append("  INNER JOIN EXME_Configurador_Access conf on (tcd.exme_configurador_id = conf.exme_configurador_id) \n")
		.append("WHERE tcd.EXME_TipoConfigurador_ID = ? ");

		st = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(getCtx(), st.toString(), "EXME_TipoConfiguradorDet", "tcd"));

		st.append(" AND conf.AD_Role_ID = ? AND conf.IsActive = 'Y'");

		List<MEXMETipoConfiguradorDet> lst = new ArrayList<MEXMETipoConfiguradorDet>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(st.toString(), get_TrxName());

			s_log.warning("Parameters : " + getEXME_TipoConfigurador_ID() + " , " + Env.getAD_Role_ID(getCtx()));

			pstmt.setInt(1, getEXME_TipoConfigurador_ID());
			pstmt.setInt(2, Env.getAD_Role_ID(getCtx()));
			rs = pstmt.executeQuery();
			while (rs.next()) {
				lst.add(new MEXMETipoConfiguradorDet(getCtx(), rs, null));
			}

		} catch (Exception e) {
			s_log.log(Level.SEVERE, st.toString(), e);
		} finally {
			DB.close(rs, pstmt);
		}
		return lst;
	}

	/**
	 * @see org.compiere.model.X_EXME_TipoConfigurador#getName()
	 */
	@Override
	public String getName() {
		return get_Translation(COLUMNNAME_Name);
	}

	/**
	 * @see org.compiere.model.X_EXME_TipoConfigurador#getDescription()
	 */
	@Override
	public String getDescription() {
		return get_Translation(COLUMNNAME_Description);
	}

}
