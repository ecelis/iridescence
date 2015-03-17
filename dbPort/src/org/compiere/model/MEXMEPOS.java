package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.util.LabelValueBean;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;

/**
 * Mantenimiento de Place of Service
 * Billing USA
 * 
 * @author pmendoza 11/02/2011
 * 
 *         Modified by
 * @author gvaldez (11/28/2011, 04/24/2012) - redisenio Abstracting y Billing Dashboard (Austin)
 * @author mrojas 06/27/2012 - Modificacones PostgreSQL
 * @author alejandro 06/29/2012 - Correccion alias en SQL
 * @author lama 04/22/2014 - Metodos para correccion de after/before save en MOrg, MEXMECtaPac, MCtaPacDet, MEXMETipoPaciente
 */
public class MEXMEPOS extends X_EXME_POS{

	private static final long serialVersionUID = 367957882099414394L;
	private static CLogger		s_log = CLogger.getCLogger (MEXMEPOS.class);

	public MEXMEPOS(Properties ctx, int EXME_POS_ID, String trxName) {
		super(ctx, EXME_POS_ID, trxName);
	}
	
	public static ArrayList<LabelValueBean> getLstPOS (Properties ctx) throws Exception{
		ArrayList<LabelValueBean> lstCodes = new ArrayList<LabelValueBean>();

		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT Value || ' - ' || Name as Name, EXME_POS_ID ") 
		.append(" FROM EXME_POS ")
		.append(" WHERE IsActive = 'Y' ")
		.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name))
		.append(" ORDER BY Value ");

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				LabelValueBean code = new LabelValueBean(rs.getString(1), String.valueOf(rs.getInt(2)));
				lstCodes.add(code);
			}

		} catch (Exception e) {
			s_log.log(Level.SEVERE, "SQL : " + sql, e);
		} finally {
			DB.close(rs, pstmt);
			pstmt = null;
			rs = null;
		}

		return lstCodes;
	}
	
	/**
	 * Valida si requiere actualizar en cascada el campo EXME_POS_ID
	 * (metodo para evitar actualizaciones en cascada que no correspondan a la
	 * organizacion (del registro) que se esta editando.
	 * 
	 * @param success parametro del {@link PO#afterSave(boolean, boolean)}
	 * @param object Objeto {@link PO} debe contener una columna llamada EXME_Pos_ID
	 * @author lama
	 */
	public static boolean isUpdateAfterSavePOS(boolean success, PO object) {
		// Se pone solo para USA, ya que esta informacion no se relaciona con mexico,
		// adicionalmente se valida que la Organizacion del registro coincida con la del Contexto
		// se valida que sea solo para edicion y que el valor de esa columna haya cambiado
		boolean retValue = false;
		if (object != null && success // si no ocurrio un error
			&& MCountry.isUSA(object.getCtx()) // si es USA
			&& !object.is_new() // no sea nuevo
			&& object.getAD_Org_ID() == Env.getAD_Org_ID(object.getCtx()) // misma organizacion
		) {
			String columnName = "EXME_Pos_ID";
			int index = object.get_ColumnIndex(columnName);
			if (index >= 0) {
				// si el valor de POSId cambio y es mayor a 0
				retValue = object.is_ValueChanged(columnName) //
					&& object.get_ValueAsInt(columnName) > 0;
			}
		}
		return retValue;
	}
	
	
	/**
	 * Asigna el valor de la columna EXME_POS_ID, tomandola de otra tabla
	 * 
	 * @param object Objeto donde se asignara el valor
	 * @param tableNameFrom Tabla origen de donde se traera el campo,
	 *            object debe tener una columna llamada tableNameFrom+"_ID"
	 *            la tabla origen debe tener una columna EXME_POS_ID
	 */
	public static void setPOSId(PO object, String tableNameFrom) {
		if (object != null //
			&& MCountry.isUSA(object.getCtx()) // si es USA
			&& StringUtils.isNotBlank(tableNameFrom) // tenga tabla
		) {
			// columna a actualizar
			String columnName = "EXME_Pos_ID";
			int index = object.get_ColumnIndex(columnName);
			// Si tiene esa columna  y NO tiene valor
			if(index >= 0 && object.get_ValueAsInt(index) <= 0) {
				// columna a obtener el valor
				String columnNameFrom = new StringBuilder(StringUtils.trimToEmpty(tableNameFrom)).append("_ID").toString();
				int originId = object.get_ValueAsInt(columnNameFrom);
				// si se tiene el ID de la tabla origen
				if (originId >=0) {
					StringBuilder sql = new StringBuilder();
					sql.append("SELECT EXME_Pos_ID FROM ").append(tableNameFrom);
					sql.append(" WHERE ").append(columnNameFrom).append("=?");
					
					int posId = DB.getSQLValue(object.get_TrxName(), sql.toString(), object.get_ValueAsInt(columnNameFrom));
					if(posId > 0) {
						object.set_ValueOfColumn(columnName, posId);
					}
				}
			}
		}
	}
	
}
