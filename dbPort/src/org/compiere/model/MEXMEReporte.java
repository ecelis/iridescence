package org.compiere.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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
 * 
 * @author Omar de la Rosa
 * 
 */
public class MEXMEReporte extends X_EXME_Reporte {

	private static final long serialVersionUID = 1L;
	private static CLogger s_log = CLogger.getCLogger(MEXMEReporte.class);

	public MEXMEReporte(Properties ctx, int EXME_Reporte_ID, String trxName) {
		super(ctx, EXME_Reporte_ID, trxName);
	}

	public MEXMEReporte(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	public static List<MEXMEReporte> getAll(Properties ctx, String whereClause,
			String orderBy, boolean verificaRol, String trxName) {
		List<MEXMEReporte> list = new ArrayList<MEXMEReporte>();

		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append("SELECT * FROM EXME_Reporte r ");
		if (verificaRol)
			sql
					.append("INNER JOIN EXME_Reporte_Access acc on r.EXME_Reporte_ID = acc.EXME_Reporte_ID ");

		if (whereClause != null) {
			if (!whereClause.toLowerCase().contains("where")) {
				sql.append("WHERE ");
			}
			sql.append(whereClause);
		} else {
			sql.append("WHERE ");
		}
		sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, sql
				.toString(), "EXME_Reporte", "r"));

		int rolId = Integer.parseInt(ctx.getProperty("#AD_Role_ID"));

		if (verificaRol)
			sql.append(" AND acc.AD_ROLE_ID = ? ");

		if (orderBy != null && orderBy.length() > 0)
			sql.append(" ORDER BY ").append(orderBy);
		else
			sql.append(" ORDER BY Name ");

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			if (verificaRol)
				pstmt.setInt(1, rolId);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				MEXMEReporte pac = new MEXMEReporte(ctx, rs, trxName);
				list.add(pac);
			}
			
		} catch (Exception e) {
			s_log.log(Level.SEVERE, "get", e);
		} finally {
			DB.close(rs, pstmt);
			pstmt = null;
			rs = null;
		}

		return list;

	}
/**
 * 
 * @param ctx
 * @param campo
 * @param tableName
 * @param where
 * @param trxName
 * @return
 * @throws Exception
 * @deprecated No hay referencia alguna, se deprecia metodo, no se corrige rownum.
 * @author jcantu depreciado el 12 de Julio del 2012.
 *
	public static List<BusquedaView> getBusquedaView(Properties ctx,
			String campo, String tableName, String where, String trxName)
			throws Exception {
		List<BusquedaView> list = new ArrayList<BusquedaView>();
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append("SELECT * FROM ").append(tableName).append(" WHERE ");

		if (where != null)
			sql.append(where).append(" ");

		sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, sql
				.toString(), tableName));

		if (sql.toString().trim().toLowerCase().endsWith("where"))
			sql.append(" ROWNUM <= 100");
		else
			sql.append(" AND ROWNUM <= 100");

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			rs = pstmt.executeQuery();
			int indice = 0;
			while (rs.next()) {
				String name = null;
				String value = null;
				String description = null;
				String documentNo = null;
				try {
					if (tableName.equalsIgnoreCase("EXME_Paciente"))
						name = rs.getString("NOMBRE_PAC");
					else
						name = rs.getString("name");
				} catch (Exception e) {
					s_log.log(Level.INFO, "getBusquedaView", e);
				}
				try {
					value = rs.getString("value");
				} catch (Exception e) {
					s_log.log(Level.INFO, "getBusquedaView", e);
				}
				try {
					description = rs.getString("description");
				} catch (Exception e) {
					s_log.log(Level.INFO, "getBusquedaView", e);
				}
				try {
					documentNo = rs.getString("documentNo");
				} catch (Exception e) {
					s_log.log(Level.INFO, "getBusquedaView", e);
				}
				BusquedaView busqueda = new BusquedaView(name, description,
						value, documentNo, rs.getString("created"), rs.getString(campo));
				busqueda.setSecuencia(indice);
				indice++;
				list.add(busqueda);
			}
			
		} catch (Exception e) {
			s_log.log(Level.INFO, "getBusquedaView", e);
			throw new Exception("error.crearBusqueda");
		} finally {
			DB.close(rs, pstmt);
			pstmt = null;
			rs = null;
		}

		return list;

	}*/

	public static int insertFile(Properties ctx, File xml, String description,
			String trxName) throws Exception {

		MEXMEReporte reporte = new MEXMEReporte(ctx, 0, trxName);
		reporte.setName(xml.getName());
		reporte.setDescription(description);
		if (!reporte.save(trxName)) {
			throw new Exception("error.guardarReporte");
		}
		MAttachment att = new MAttachment(ctx, 0, trxName);
		MTable table = MTable.get(ctx, "EXME_Reporte");
		att.setAD_Table_ID(table.getAD_Table_ID());
		att.setRecord_ID(reporte.getEXME_Reporte_ID());
		att.setTitle(xml.getName());
		att.setTextMsg(description);
		att.addEntry(xml);
		if (!att.save(trxName)) {
			throw new Exception("error.guardarReporte");
		}

		return reporte.getEXME_Reporte_ID();
	}

	public static byte[] getBytesFromFile(File file) throws IOException {
		InputStream is = new FileInputStream(file);

		long length = file.length();

		byte[] bytes = new byte[(int) length];

		int offset = 0;
		int numRead = 0;
		while (offset < bytes.length
				&& (numRead = is.read(bytes, offset, bytes.length - offset)) >= 0) {
			offset += numRead;
		}

		if (offset < bytes.length) {
			throw new IOException("Could not completely read file "
					+ file.getName());
		}

		is.close();
		return bytes;
	}

	public static File toFile(InputStream inputStream, String name)
			throws IOException {
		// File f = File.createTempFile("rep", ".xml");
		File f = new File(name);
		OutputStream out = new FileOutputStream(f);
		byte buf[] = new byte[1024];
		int len;
		while ((len = inputStream.read(buf)) > 0)
			out.write(buf, 0, len);
		out.close();
		inputStream.close();
		return f;
	}

	public int deleteChild(Properties ctx, String trxName) {
		StringBuilder st = new StringBuilder(
				"DELETE FROM EXME_Reporte_Access WHERE EXME_Reporte_ID = ?");
		PreparedStatement pstmt = null;
		int cantidad = 0;
		try {
			pstmt = DB.prepareStatement(st.toString(), trxName);
			pstmt.setInt(1, getEXME_Reporte_ID());
			cantidad = pstmt.executeUpdate();
			
		} catch (Exception e) {
			s_log.log(Level.SEVERE, st.toString(), e);
		}
		  finally {
			  DB.close(pstmt);
			pstmt = null;
		}
		return cantidad;
	}
}
