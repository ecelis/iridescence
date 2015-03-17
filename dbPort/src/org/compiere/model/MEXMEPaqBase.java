/**
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
import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;

/**
 * @author Expert
 * 
 */
public class MEXMEPaqBase extends X_EXME_PaqBase {

	/** serialVersionUID */
	private static final long serialVersionUID = -8185927555962770515L;
	/** log */
	private static final CLogger logger = CLogger
			.getCLogger(MEXMEPaqBase.class);

	/**
	 * @param ctx
	 * @param EXME_PaqBase_ID
	 * @param trxName
	 */
	public MEXMEPaqBase(final Properties ctx, final int EXME_PaqBase_ID,
			final String trxName) {
		super(ctx, EXME_PaqBase_ID, trxName);
	}

	/**
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MEXMEPaqBase(final Properties ctx, final ResultSet rs,
			final String trxName) {
		super(ctx, rs, trxName);
	}

	/**
	 * Todos los paquetes por nivel de acceso activos
	 * @param ctx
	 * @param trxName
	 * @return
	 */
	public static List<MEXMEPaqBase> get(Properties ctx, String where, String trxName) {
		
		if (ctx == null  ) {
			return null;
		}
		
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT * FROM EXME_PaqBase ").append(
				" WHERE EXME_PaqBase.IsActive = 'Y' ").append(
				MEXMELookupInfo.addAccessLevelSQL(ctx, " ",
						X_EXME_PaqBase.Table_Name)).append(where).append(
				" ORDER BY EXME_PaqBase.EXME_PaqBase_ID DESC ");

		return get(ctx, sql.toString(), null, trxName);
	}

	/**
	 * Metdoos genericopara ejecutar una consulta y devuelva una lista
	 * de objetos MEXMEPaqBase
	 * @param ctx contexto Obligatorio
	 * @param sql consulta
	 * @param params parametros
	 * @param trxName nombre de la transaccion
	 * @return List<MEXMEPaqBase>
	 */
	public static List<MEXMEPaqBase> get(Properties ctx, String sql,
			List<?> params, String trxName) {

		List<MEXMEPaqBase> resultados = new ArrayList<MEXMEPaqBase>();

		if (ctx == null  || sql == null ) {
			return null;
		}
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = DB.prepareStatement(sql, trxName);
			DB.setParameters(pstmt, params);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				resultados.add(new MEXMEPaqBase(ctx, rs, trxName));
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} finally {
			DB.close(rs, pstmt);
		}

		return resultados;
	}
	/**
	 * Enlistar todos los paquetes base de la organización activos excepto el
	 * paquete del parametro
	 * 
	 * @param ctx
	 *            : Contexto
	 * @param paqId
	 *            : Paquete Id
	 * @return Listado de paquetes
	 */
	public static List<MEXMEPaqBase> getPackBase(final Properties ctx,
			final int paqId) {
		final List<MEXMEPaqBase> list = new ArrayList<MEXMEPaqBase>();

		final StringBuilder sql = new StringBuilder();
		sql.append("SELECT ");
		sql.append("  * ");
		sql.append("FROM ");
		sql.append("  EXME_PaqBase ");
		sql.append("WHERE ");
		sql.append("  EXME_PaqBase_ID <> ? AND ");
		sql.append("  isactive = 'Y' ");
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name));

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setInt(1, paqId);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				list.add(new MEXMEPaqBase(ctx, rs, null));
			}
		} catch (Exception e) {
			logger.log(Level.SEVERE, null, e);
		} finally {
			DB.close(rs, pstmt);
		}

		
		return list;
	}
	
	/**
	 * Enlistar los paquetes de la organización
	 * 
	 * @param ctx
	 *            : contexto
	 * @param where
	 *            : condiciones adicionales
	 * @param trxName
	 *            : Nombre de transacción
	 * @return
	 */
	public static List<MEXMEPaqBase> getListPaqBase(final Properties ctx,
			final String where, final String trxName) {

		if (ctx == null) {
			return null;
		}

		final StringBuilder sql = new StringBuilder();
		sql.append(" SELECT * FROM EXME_PaqBase ")
				.append(" WHERE EXME_PaqBase.IsActive = 'Y' ")
				.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ",
						X_EXME_PaqBase.Table_Name)).append(where)
				.append(" ORDER BY EXME_PaqBase.EXME_PaqBase_ID DESC ");

		final List<MEXMEPaqBase> resultados = new ArrayList<MEXMEPaqBase>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				resultados.add(new MEXMEPaqBase(ctx, rs, trxName));
			}
		} catch (SQLException sqle) {
			logger.log(Level.SEVERE, null, sqle);
		} finally {
			DB.close(rs, pstmt);
		}

		return resultados;
	}
	
	
	/** Enlistar los minipaquetes */
	public static List<MEXMEPaqBase> getLstMinipack(
			final Properties ctx, final String valuePack,  final String namePack, 
			final String description, final boolean isActive,  
			final String trxName) {
		
		final List<Object> params = new ArrayList<Object>();
		final StringBuilder where = new StringBuilder();
		params.add(isActive?"Y":"N");
		
		// Producto
		if (!StringUtils.isEmpty(valuePack)) {
			params.add(valuePack);
			where.append(" AND UPPER(pb.Value)  like UPPER('%?%') ");
		}
		
		// Nombre del minipaquete o version
		if (!StringUtils.isEmpty(namePack)) {
			params.add(namePack);
			where.append(" AND UPPER(pb.Name)  like UPPER('%?%') ");
		}
		
		final List<MEXMEPaqBase> resultados = new ArrayList<MEXMEPaqBase>();
		final StringBuilder sql = new StringBuilder()
		.append(" SELECT pb.* ")
		.append(" FROM EXME_PaqBase pb   ")
		.append(" WHERE pb.IsActive = ?  ")
		.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ",X_EXME_PaqBase.Table_Name,"pb"))
		.append(" AND   pb.IsMiniPack = 'Y' ")
		.append(where==null?"":where)
		.append(" ORDER BY pb.Created DESC ");

		
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			DB.setParameters(pstmt, params);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				resultados.add(new MEXMEPaqBase(ctx, rs, trxName));
			}
		} catch (Exception e) {
			logger.log(Level.SEVERE, null, e);
		} finally {
			DB.close(rs, pstmt);
		}

		return resultados;
	}

	
	 /**
     * Obtenemos todos las versiones del paquete para la empresa logeada (expert)
     * @param ctx
     * @param cadena
     * @param value
     * @param trxName
     * @return
     */
    public static List<MEXMEPaqBaseVersion> getPaqBaseVersion(Properties ctx, String cadena, int value, String trxName){
        
        if(ctx == null)
            return null;
        
        ArrayList<MEXMEPaqBaseVersion> list = new ArrayList<MEXMEPaqBaseVersion>();
        StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY); 
        sql.append(" SELECT * ") 
        .append(" FROM EXME_PaqBase_Version ")
        .append(" WHERE EXME_PaqBase_Version.IsActive = 'Y' ")
        .append(" AND EXME_PaqBase_Version.EXME_PaqBase_ID = ? ");

        if (cadena != null) {
            sql.append(cadena);
        }
            
        sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", "EXME_PaqBase_Version"));
        sql.append(" ORDER BY EXME_PaqBase_Version.EXME_PaqBase_Version_ID DESC ");
                    
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = DB.prepareStatement(sql.toString(), trxName);
            pstmt.setInt(1, value);
            rs = pstmt.executeQuery();
            while (rs.next()) {
            	MEXMEPaqBaseVersion paquete = new MEXMEPaqBaseVersion(ctx, rs, trxName);
                list.add(paquete);
            }
        }
        catch (Exception e) {
        	logger.log(Level.SEVERE, "While closing Objects - getPaqBaseVersion - sql: " + sql, e);
        }
        finally {
            DB.close(rs, pstmt);
        }

        //
        return list;
        
    }
	/**
	 * Buscar el nombre de la version en otros registros para que no se repita
	 * @param ctx: conexto
	 * @param valueName: Nombre de la version
	 * @param paqBaseVersionID: Id de la version 
	 * @param trxName: Nombre de transacción
	 * @return true: Si existe el registro
	 */
	public static boolean validarNoRepetirElNombre(final Properties ctx, final String valueName, final int paqBaseVersionID, final String trxName) {
		boolean existe = false;

		final StringBuilder sql = new StringBuilder()
		.append(" SELECT * FROM EXME_PaqBase pb ")
		.append(" INNER JOIN EXME_PaqBase_Version pbv ON pbv.EXME_PaqBase_ID = pb.EXME_PaqBase_ID ")
		.append(" WHERE pb.IsActive = 'Y' ")
		.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", X_EXME_PaqBase.Table_Name, "pb"))
		.append(" AND pb.IsMiniPack = 'Y' ")
		.append(" AND pbv.EXME_PaqBase_Version_ID <> ? ")
		.append(" AND ( UPPER(pb.Value) = UPPER(?)     ")
		.append(" OR    UPPER(pb.Name)  = UPPER(?)     ")
		.append(" OR    UPPER(pbv.Name) = UPPER(?) )   ");
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, paqBaseVersionID);
			pstmt.setString(2, valueName);
			pstmt.setString(3, valueName);
			pstmt.setString(4, valueName);
			
			rs = pstmt.executeQuery();
			existe = rs.next();
		} catch (SQLException sqle) {
			logger.log(Level.SEVERE, null, sqle);
		} finally {
			DB.close(rs, pstmt);
		}

		return existe;
	}
	
}
