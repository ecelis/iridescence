package org.compiere.model;

import java.awt.Color;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.minigrid.IDColumn;
import org.compiere.model.bpm.PrimerEventoSesion;
import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.GridItem;

/**
 * Modelo de Tratamientos
 * 
 * @author Julio Gutierrez
 * @Date: 2009/05/28 12:06
 * @version Revision: 1.0
 */
public class MEXMETratamientosDetalle extends X_EXME_Tratamientos_Detalle
		implements GridItem {

	/** */
	private static final long serialVersionUID = 1L;
	/** Static logger */
	private static CLogger log = CLogger
			.getCLogger(MEXMETratamientosDetalle.class);

	public final static Color DOCSTATUSCOLOR_VOIDED = new Color(16764108);
	public final static Color DOCSTATUSCOLOR_CLOSED = new Color(13421772);
	public final static Color DOCSTATUSCOLOR_COMPLETED = new Color(16777164);
	public final static Color DOCSTATUSCOLOR_DRAFTED = DOCSTATUSCOLOR_COMPLETED;

	/**
	 * Constructor MEXMETratamientosDetalle
	 * 
	 * @param ctx
	 * @param EXME_Tratamientos_ID
	 * @param trxName
	 */
	public MEXMETratamientosDetalle(Properties ctx,
			int EXME_TratamientosDetalle_ID, String trxName) {
		super(ctx, EXME_TratamientosDetalle_ID, trxName);
		cual();
	}

	/**
	 * Constructor MEXMETratamientosDetalle
	 * 
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MEXMETratamientosDetalle(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	/**
	 * Metodo que rergesa una lista de los detalles del tratamiento
	 * 
	 * @author Lorena Lama
	 * @param ctx
	 * @param tratamiento
	 * @return
	 */
	public static List<MEXMETratamientosDetalle> getTratamientosDetalle(
			Properties ctx, int tratamiento) {
		return getTratamientosDetalle(ctx, tratamiento, null);
	}

	/**
	 * 
	 * @param ctx
	 * @param tratamiento
	 * @param whereClause
	 * @return
	 */
	public static List<MEXMETratamientosDetalle> getTratamientosDetalle(
			Properties ctx, int tratamiento, String whereClause) {

		List<Object> params = new ArrayList<Object>();

		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append(" SELECT * FROM EXME_Tratamientos_Detalle ").append(
				" WHERE EXME_Tratamientos_ID = ? ").append(
				MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name))
				.append(whereClause != null ? whereClause : "").append(
						" ORDER BY Name ");

		params.add(tratamiento);

		return getTratamientosDetalle(ctx, sql.toString(), params, null);
	}

	/**
	 * Obtiene el detalle de los tratamientos seleccionados
	 * 
	 * @param ctx
	 * @param tratamiento
	 * @param whereClause
	 * @return
	 */
	public static List<MEXMETratamientosDetalle> getTratamientosDetalle(
			Properties ctx, List<Integer> lstTratamiento) {
		List<Object> params = new ArrayList<Object>();

		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append("SELECT * FROM EXME_Tratamientos_Detalle ").append(
				"WHERE IsActive = 'Y' ").append(
				MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name));

		if (lstTratamiento != null) {
			StringBuilder tratamiento = new StringBuilder(
					Constantes.INIT_CAPACITY_ARRAY);
			for (int i = 0; i < lstTratamiento.size(); i++) {
				tratamiento.append(lstTratamiento.get(i));
				if (i + 1 < lstTratamiento.size())
					tratamiento.append(",");
			}
			sql.append("AND EXME_Tratamientos_ID IN ( ").append(tratamiento)
					.append(" ) ");

		}
		sql.append(" ORDER BY EXME_Tratamientos_ID, SessionNo ASC ");

		return getTratamientosDetalle(ctx, sql.toString(), params, null);
	}

	/**
	 * Metodo que regresa una lista de los detalles del tratamiento
	 * 
	 * @author Julio Gutierrez
	 * @author Lorena Lama: Modificado por Se agrego param. clausula where
	 * @param ctx
	 * @param tratamiento
	 * @param whereClause
	 * @return
	 */
	public static List<MEXMETratamientosDetalle> getTratamientosDetalle(
			Properties ctx, String sql, List<?> params, String trxName) {

		List<MEXMETratamientosDetalle> retorno = new ArrayList<MEXMETratamientosDetalle>();

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			DB.setParameters(pstmt, params);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				MEXMETratamientosDetalle mtd = new MEXMETratamientosDetalle(
						ctx, rs, null);
				retorno.add(mtd);
			}

		} catch (Exception e) {
			log.log(Level.SEVERE, "getCitasMedico", e);
			e.printStackTrace();
		} finally {
			DB.close(rs, pstmt);
		}
		return retorno;
	}

	/**
	 * Referencia
	 */
	private MReference reference = null;

	public MReference getReference() {
		return reference;
	}

	public void setReference(MReference reference) {
		this.reference = reference;
	}

	/**
	 * Nombre del tipo de sesion
	 * 
	 * @return
	 */
	public String getTipoSession() {

		if (reference == null
				|| reference.getAD_Reference_ID() != TYPE_AD_Reference_ID)

			reference = new MReference(getCtx(), TYPE_AD_Reference_ID,
					get_TrxName());

		return reference.getValueList(getType());
	}

	/**
	 * Primer evento de la session
	 */
	private PrimerEventoSesion primerEvento = null;

	/**
	 * Busqueda del primer evento de la session puede ser una cita, un servicio
	 * o una prescripcion
	 * 
	 * @return
	 */
	public PrimerEventoSesion getPrimerEvento(
			MEXMETratamientosPaciente tratPac, int EXME_Tratamientos_Sesion_ID) {

		if (primerEvento == null) {
			primerEvento = new PrimerEventoSesion(tratPac,
					EXME_Tratamientos_Sesion_ID);
			primerEvento.primerEvento();
		}
		return primerEvento;
	}

	public PrimerEventoSesion getPrimerEvento() {
		return primerEvento;
	}

	private MEXMETratamientos _tratamientos = null;

	public MEXMETratamientos getTratamientos() {
		if (_tratamientos == null) {
			_tratamientos = new MEXMETratamientos(getCtx(),
					getEXME_Tratamientos_ID(), get_TableName());
		}
		return _tratamientos;
	}

	private String[] valor = null;

	@Override
	public String[] getColumns() {
		return valor;
	}

	@Override
	public IDColumn getIdColumn() {
		return new IDColumn(getEXME_Tratamientos_Detalle_ID());
	}

	public void cual() {

		valor = new String[] { "idColumn", "description", "caden" };
		cual(0);
	}

	public void cual(int id) {
		if (id > 0) {
			lst = MEXMECitaMedica.getTratamientosDetalle(getCtx(),
					getEXME_Tratamientos_Detalle_ID(), id, null);
			for (int i = 0; i < lst.size(); i++) {
				caden = caden + " Cita para la fecha "
						+ lst.get(i).getCreated();
			}
		}
	}

	private List<MEXMECitaMedica> lst = null;

	public List<MEXMECitaMedica> getLst() {
		return lst;
	}

	public void setLst(List<MEXMECitaMedica> lst) {
		this.lst = lst;
	}

	private String caden = null;

	public String getCaden() {
		return caden;
	}

	public void setCaden(String caden) {
		this.caden = caden;
	}

}
