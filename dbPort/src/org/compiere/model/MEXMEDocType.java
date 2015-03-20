/*
 * Created on 23-mar-2005
 *
 */
package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.util.LabelValueBean;
import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;
import org.compiere.util.Language;

/**
 * @author hassan reyes
 * 
 */
public class MEXMEDocType extends MDocType {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1243823139086188514L;
	/** Logger */
	private static CLogger log = CLogger.getCLogger(MEXMEDocType.class);

	/**
	 * @param ctx
	 * @param C_DocType_ID
	 * @param trxName
	 */
	public MEXMEDocType(Properties ctx, int C_DocType_ID, String trxName) {
		super(ctx, C_DocType_ID, trxName);
	}

	/**
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MEXMEDocType(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	/**
	 * @param ctx
	 * @param DocBaseType
	 * @param Name
	 * @param trxName
	 */
	public MEXMEDocType(Properties ctx, String DocBaseType, String Name,
			String trxName) {
		super(ctx, DocBaseType, Name, trxName);
	}

//	/**
//	 * Get Client Document Type with DocSubTypeSO
//	 * 
//	 * @param ctx
//	 *            context
//	 * @param DocSubTypeSO
//	 *            document sub type SO
//	 * @return array of doc types
//	 */
//	// checar//OK
//	static public MEXMEDocType[] getOfDocSubTypeSO(Properties ctx,
//			String DocSubTypeSO) {
//		return getOfDocSubTypeSO(ctx, DocSubTypeSO, null, null, 0);
//	}

	/**
	 * Tipo de documento por empresa y organizacion (ordenado en orden
	 * descendente x org)
	 * 
	 * @param ctx
	 * @param DocSubTypeSO
	 * @param whereClause
	 * @param params
	 * @return
	 */
	static public MEXMEDocType[] getOfDocSubTypeSO(Properties ctx,
			String DocSubTypeSO, String whereClause, Object[] params, int limit) {

		final ArrayList<MEXMEDocType> list = new ArrayList<MEXMEDocType>();

		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);

		sql.append(" SELECT * FROM C_DocType WHERE DocSubTypeSO = ? ")
		.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name))
		.append(StringUtils.isNotBlank(whereClause) ? whereClause : StringUtils.EMPTY)
		.append(limit > 0 && DB.isOracle() ? " AND RowNum = " + limit : StringUtils.EMPTY)
		.append(" ORDER BY AD_Org_ID DESC,  Name  ");
		
		if(limit > 0 && DB.isPostgreSQL()) {
			sql = new StringBuilder(DB.getDatabase().addPagingSQL(sql.toString(), 1, limit));
			//sql.append(" LIMIT ").append(limit).append(" OFFSET 0");
		}

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setString(1, DocSubTypeSO);
			
			if(params != null) {
				for (int i = 0; i < params.length; i++) {
					DB.setParameter(pstmt, i + 2, params[i]);
				}
			}
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				list.add(new MEXMEDocType(ctx, rs, null));
			}
		}

		catch (Exception e) {
			log.log(Level.SEVERE, "SQL : " + sql, e);
		} finally {
			DB.close(rs, pstmt);
		}

		MEXMEDocType[] retValue = new MEXMEDocType[list.size()];
		list.toArray(retValue);
		return retValue;
	} // getOfDocBaseType

	/**
	 * Obtenemos el identificador del tipo de documento recibido como parametro
	 * (copiado de Datos)
	 * 
	 * @param ctx
	 *            El contexto de la aplicacion
	 * @param tipo
	 *            El nombre del tipo de documento
	 * @return El identificador del tipo de documento
	 * @exception Exception
	 *                Description of the Exception
	 */
	public static int getOfName(Properties ctx, String tipo, String trxName) {

		int tipoDoc = 0;

		// Tipo de documento por empresa y organizacion (ordenado en orden
		// descendente x org)
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT C_DocType_ID FROM C_DocType WHERE ")// AD_Client_ID
				// IN(0,?) "
				.append(" UPPER(Name) LIKE UPPER(?) ").append(
						" AND IsActive = 'Y' ");

		sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, sql
				.toString(), "C_DocType"));
		sql.append(" ORDER BY AD_Org_ID DESC, Name ");

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setString(1, "%" + tipo + "%");
			rs = pstmt.executeQuery();

			if (rs.next()) {
				tipoDoc = rs.getInt("C_DocType_ID");
			} else {
				tipoDoc = 0;
			}

		} catch (SQLException e) {
			log.log(Level.SEVERE, "getOfName", e);
		} finally {
			DB.close(rs,pstmt);
			pstmt = null;
			rs = null;
		}

		return tipoDoc;
	}

//	/**
//	 * Obtenemos los tipo de documento Movimiento para la empresa y organizacion
//	 * logeada para inventario
//	 * 
//	 * @param ctx
//	 *            contexto
//	 * @param trxName
//	 *            nombre de transaccion
//	 * @return Una lista con los tipos de documento Movimiento
//	 */
//
//	public static List<LabelValueBean> getTipoDocMovInventory(Properties ctx,
//			String trxName) throws Exception {
//
//		return MEXMEDocType
//				.getTipoDocMov(ctx, " AND C_DocType.DocBaseType = '"
//						+ MEXMEDocType.DOCBASETYPE_InternalUseInventory + "' ",
//						trxName);
//	}

	/**
	 * Obtenemos los tipo de documento Movimiento para la empresa y organizacion
	 * logeada
	 * 
	 * @param ctx
	 *            contexto
	 * @param trxName
	 *            nombre de transaccion
	 * @return Una lista con los tipos de documento Movimiento
	 * @deprecated No usar LabelValueBean si no {@link KeyNamePair}
	 */
	public static List<LabelValueBean> getTipoDocMov(Properties ctx,
			String trxName) throws Exception {

		return MEXMEDocType.getTipoDocMov(ctx, null, trxName);
	}

	/**
	 * Obtenemos los tipo de documento Movimiento para la empresa y organizacion
	 * logeada
	 * 
	 * @param ctx
	 *            contexto
	 * @param trxName
	 *            nombre de transaccion
	 * @param cadena
	 *            cadena con el tipo de documento
	 * @return Una lista con los tipos de documento Movimiento
	 * @throws Exception
	 *  @deprecated No usar LabelValueBean si no {@link KeyNamePair}
	 */
	public static List<LabelValueBean> getTipoDocMov(Properties ctx,
			String cadena, String trxName) throws Exception {

		List<LabelValueBean> lstTipoDoc = new ArrayList<LabelValueBean>();
		StringBuilder sql = new StringBuilder();
		boolean base = Language.isBaseLanguage(Env.getContext(ctx,
				"#AD_Language"));

		// Tipo de documento Movimiento por empresa y organizacion
		if (!base) {

			// Si no es el idioma base => cargar traducciones
			sql.append(" SELECT C_DocType.C_DocType_ID, t.Name ").append(
					" FROM C_DocType , C_DocType_Trl t ").append(
					" WHERE C_DocType.C_DocType_ID = t.C_DocType_ID  ").append(
					cadena == null ? " AND C_DocType.DocBaseType = 'MMM' "
							: cadena).append(" AND t.AD_Language = ? ").append(
					" AND C_DocType.IsActive = 'Y' ");

			sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, sql
					.toString(), "C_DocType"));
			sql.append(" ORDER BY C_DocType.AD_Org_ID DESC,  t.Name");
		} else {

			// Si es el idioma base => tomar el nombre de C_DocType
			sql
					.append(" SELECT C_DocType.C_DocType_ID, C_DocType.Name ")
					.append(" FROM C_DocType ")
					.append(" WHERE C_DocType.IsActive = 'Y' ")
					.append(
							cadena == null ? " AND C_DocType.DocBaseType = 'MMM' "
									: cadena);

			sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, sql
					.toString(), "C_DocType"));
			sql.append(" ORDER BY C_DocType.AD_Org_ID DESC, C_DocType.Name ");
		}

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			pstmt = DB.prepareStatement(sql.toString(), trxName);
			if (!base)
				pstmt.setString(1, Env.getContext(ctx, "#AD_Language"));

			rs = pstmt.executeQuery();
			while (rs.next()) {
				LabelValueBean combo = new LabelValueBean(rs.getString("Name"),
						String.valueOf(rs.getLong("C_DocType_ID")));

				lstTipoDoc.add(combo);
			}

		} catch (SQLException e) {
			log.log(Level.SEVERE, "error.tipoDoc", e);
		} finally {
			DB.close(rs, pstmt);
			pstmt = null;
			rs = null;// Expert
		}
		return lstTipoDoc;

	}

	/**
	 * Tipo de documento MMI
	 * 
	 * @param ctx
	 * @return
	 * @throws Exception
	 *  @deprecated No usar LabelValueBean si no {@link KeyNamePair}
	 */
	public static List<LabelValueBean> getDocBaseTypeMMI(Properties ctx)
			throws Exception {
		return MEXMEDocType.getTipoDocMov(ctx, "C_DocType.DocBaseType='MMI'",
				null);
	}

	/**
	 * Get Client Document Type with DocBaseType
	 * 
	 * @param ctx
	 *            context
	 * @param DocBaseType
	 *            base document type
	 * @return array of doc types
	 */
	static public MDocType[] getOfDocBaseType(Properties ctx, String DocBaseType) {
		List<MDocType> list = MEXMEDocType.getOfDocBaseType(ctx, DocBaseType,
				null);

		MDocType[] retValue = new MDocType[list.size()];
		list.toArray(retValue);
		return retValue;
	} // getOfDocBaseType

	/**
	 * Tipo de documento por tipo que este activo, por nivel de acceso ordenado
	 * por organizacion y si es por defecto en orden descendente
	 * 
	 * @param ctx
	 *            contexto
	 * @param DocBaseType
	 *            tipo de documento bade
	 * @param trxName
	 *            nombre de transaccion
	 * @return listado de objetos de tipo de documento
	 */
	static public List<MDocType> getOfDocBaseType(Properties ctx,
			String DocBaseType, String trxName) {
		List<MDocType> list = new ArrayList<MDocType>();
		StringBuilder sql = new StringBuilder();

		sql.append(" SELECT * FROM C_DocType ").append(" WHERE DocBaseType=?")
				.append(" AND IsActive='Y' ");

		sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, sql
				.toString(), "C_DocType"));
		sql.append(" ORDER BY AD_Org_ID DESC, IsDefault DESC ");
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setString(1, DocBaseType);
			rs = pstmt.executeQuery();
			while (rs.next())
				list.add(new MDocType(ctx, rs, trxName));
		} catch (Exception e) {
			log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
		}

		return list;
	} // getOfDocBaseType

	/**
	 * Get Client Document Type Min ID with DocBaseType
	 * 
	 * @param ctx
	 *            context
	 * @param DocBaseType
	 *            base document type
	 * @return array of doc types
	 */
	static public int getOfDocBaseTypeMinID(Properties ctx, String DocBaseType) {
		int cDocTypeID = -1;
		StringBuilder sql = new StringBuilder();

		sql.append(" SELECT MIN(C_DocType_ID) FROM C_DocType ").append(
				" WHERE DocBaseType=?").append(" AND IsActive='Y' ");

		sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, sql
				.toString(), "C_DocType"));
		sql.append(" ORDER BY AD_Org_ID DESC, IsDefault DESC ");
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setString(1, DocBaseType);
			rs = pstmt.executeQuery();
			while (rs.next())
				cDocTypeID = rs.getInt(1);
		} catch (Exception e) {
			log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
			pstmt = null;
			rs = null;// Expert
		}
		return cDocTypeID;
	} // getOfDocBaseType

	/**
	 * Obtenemos el identificador del tipo de documento segun el nombre y
	 * DocBaseType
	 * 
	 * @param empresa
	 *            la empresa con la que se logeo
	 * @param organizacion
	 *            la organizacion con la que se logeo
	 * @deprecated use {@link #getTipoDoc(Properties, String, String, Boolean, String)}
	 */
	public static MDocType getTipoDoc(Properties ctx, int empresa,
			int organizacion, String name, String docBaseType, String trxName)
			throws SQLException {

//		// Tipo de documento Movement por empresa y organizacion
//		String sql =
//			"SELECT * FROM C_DocType WHERE UPPER(Name) LIKE '%" + name + "%' " 
//		+ " AND DocBaseType = '" + docBaseType + "' " + " AND IsActive = 'Y'";
//		sql = MEXMELookupInfo.addAccessLevelSQL(ctx, sql, "C_DocType");
//		sql = sql + " ORDER BY AD_Org_ID DESC ";
//
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		MDocType docType = null;
//		try {
//			pstmt = DB.prepareStatement(sql, null);
//			rs = pstmt.executeQuery();
//
//			if (rs.next()) {
//				docType = new MDocType(ctx, rs, trxName);
//			}
//		} catch (SQLException e) {
//			log.log(Level.SEVERE, "getTipoDoc", e);
//			throw e;
//		} finally {
//			DB.close(rs, pstmt);
//			pstmt = null;
//			rs = null;// Expert
//		}
		return getTipoDoc(ctx, docBaseType, name, null, trxName);
	}
	
	/**
	 * Tipo de documento de nota de credito y debito
	 * @param ctx
	 * @return
	 * @throws Exception
	 */
	public static int notasCreditoDebito(final Properties ctx, String docType) throws Exception{
		// Tipo de documento nota de credito
		final MDocType[] types = MEXMEDocType.getOfDocBaseType(
				ctx, docType);
		int C_DocType_ID = 0;
		if (types.length > 0) { // get first
			C_DocType_ID = types[0].getC_DocType_ID();
		} else {
			throw new Exception("error.caja.tipoDoc.notaCred");
		}
		return C_DocType_ID;
	}
	
	/**
	 * Tipo de documento de nota de credito
	 * PROVEEDOR
	 * @param ctx
	 * @return
	 * @throws Exception
	 */
	public static int notaDebitoProveedor(final Properties ctx) throws Exception{
		// Tipo de documento nota de credito
		final MDocType[] types = MEXMEDocType.getOfDocBaseType(
				ctx, MDocType.DOCBASETYPE_APDebitMemo);
		int C_DocType_ID = 0;
		if (types.length > 0) { // get first
			C_DocType_ID = types[0].getC_DocType_ID();
		} else {
			throw new Exception("error.caja.tipoDoc.notaCred");
		}
		return C_DocType_ID;
	}

	/**
	 * Tipo de documento de nota de credito
	 * CLIENTE
	 * @param ctx
	 * @return
	 * @throws Exception
	 */
//	public static int notaCreditoCliente(final Properties ctx) throws Exception{
//		// Tipo de documento nota de credito
//		final MDocType[] types = MEXMEDocType.getOfDocBaseType(
//				ctx, MDocType.DOCBASETYPE_ARCreditMemo);
//		int C_DocType_ID = 0;
//		if (types.length > 0) { // get first
//			C_DocType_ID = types[0].getC_DocType_ID();
//		} else {
//			throw new Exception("error.caja.tipoDoc.notaCred");
//		}
//		return C_DocType_ID;
//	}
	
	/**
	 * Tipo de documento de nota de credito
	 * CLIENTE
	 * @param ctx
	 * @return
	 * @throws Exception
	 */
	public static int notaDebitoCliente(final Properties ctx) throws Exception{
		// Tipo de documento nota de credito
		final MDocType[] types = MEXMEDocType.getOfDocBaseType(
				ctx, MDocType.DOCBASETYPE_ARDebitMemo);
		int C_DocType_ID = 0;
		if (types.length > 0) { // get first
			C_DocType_ID = types[0].getC_DocType_ID();
		} else {
			throw new Exception("error.caja.tipoDoc.notaCred");
		}
		return C_DocType_ID;
	}
	/**
	 * Obtenemos los tipo de documento Movimiento para la empresa y organizacion
	 * logeada
	 * 
	 * @param ctx
	 *            contexto
	 * @param trxName
	 *            nombre de transaccion
	 * @param cadena
	 *            cadena con el tipo de documento
	 * @return Una lista con los tipos de documento Movimiento
	 * @throws Exception
	 */
	public static List<KeyNamePair> getTipoDoc(Properties ctx,
			String cadena, String trxName) throws Exception {

		List<KeyNamePair> lstTipoDoc = new ArrayList<KeyNamePair>();
		StringBuilder sql = new StringBuilder();
		boolean base = Language.isBaseLanguage(Env.getContext(ctx,
				"#AD_Language"));

		// Tipo de documento Movimiento por empresa y organizacion
		if (!base) {

			// Si no es el idioma base => cargar traducciones
			sql.append(" SELECT C_DocType.C_DocType_ID, t.Name ").append(
					" FROM C_DocType , C_DocType_Trl t ").append(
					" WHERE C_DocType.C_DocType_ID = t.C_DocType_ID  ").append(
					cadena == null ? " AND C_DocType.DocBaseType = 'MMM' "
							: cadena).append(" AND t.AD_Language = ? ").append(
					" AND C_DocType.IsActive = 'Y' ");

			sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, sql
					.toString(), "C_DocType"));
			sql.append(" ORDER BY C_DocType.AD_Org_ID DESC,  t.Name");
		} else {

			// Si es el idioma base => tomar el nombre de C_DocType
			sql
					.append(" SELECT C_DocType.C_DocType_ID, C_DocType.Name ")
					.append(" FROM C_DocType ")
					.append(" WHERE C_DocType.IsActive = 'Y' ")
					.append(
							cadena == null ? " AND C_DocType.DocBaseType = 'MMM' "
									: cadena);

			sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, sql
					.toString(), "C_DocType"));
			sql.append(" ORDER BY C_DocType.AD_Org_ID DESC, C_DocType.Name ");
		}

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			pstmt = DB.prepareStatement(sql.toString(), trxName);
			if (!base)
				pstmt.setString(1, Env.getContext(ctx, "#AD_Language"));

			rs = pstmt.executeQuery();
			while (rs.next()) {
				KeyNamePair combo = new KeyNamePair(rs.getInt("C_DocType_ID"), rs.getString("Name"));
				lstTipoDoc.add(combo);
			}

		} catch (SQLException e) {
			log.log(Level.SEVERE, "error.tipoDoc", e);
		} finally {
			DB.close(rs, pstmt);
			pstmt = null;
			rs = null;// Expert
		}
		return lstTipoDoc;

	}
}
