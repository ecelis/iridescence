/**
 * 
 */
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.apache.commons.lang.StringUtils;
import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Utilerias;

/**
 * Versiones de paquetes
 * @author Expert
 * 
 */
public class MEXMEPaqBaseVersion extends X_EXME_PaqBase_Version {

	/** serialVersionUID */
	private static final long serialVersionUID = -6040215257802302634L;
	/** Log */
	private static CLogger slog = CLogger.getCLogger(MEXMEPaqBaseVersion.class);
	/** PO Producto */
	private MProduct product = null;
	/** PO Paquete base */
	private MEXMEPaqBase paqBase = null;
	/** PO Paquete base */
	private MEXMEPaqBase paquete = null;
	
	/**
	 * @param ctx
	 * @param EXME_PaqBase_Version_ID
	 * @param trxName
	 */
	public MEXMEPaqBaseVersion(final Properties ctx, final int EXME_PaqBase_Version_ID,
			final String trxName) {
		super(ctx, EXME_PaqBase_Version_ID, trxName);

		if (EXME_PaqBase_Version_ID <= 0) {
			setBaseAmt(Env.ZERO);
			setTotalAmt(Env.ZERO);
			setTaxAmt(Env.ZERO);
			setDiscount(Env.ZERO);
		}
	}

	/**
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MEXMEPaqBaseVersion(final Properties ctx, final ResultSet rs, final String trxName) {
		super(ctx, rs, trxName);
	}

	// public String getFechaValidoDesdeStr() {
	// return Constantes.sdfFecha(getCtx()).format(getValidFrom());
	// }

	/**
	 * Producto
	 * 
	 * @return
	 */
	public MEXMEProduct getProducto() {

		if (getM_Product_ID() > 0)
			return new MEXMEProduct(getCtx(), getM_Product_ID(), null);
		else
			return null;
	}

    /**
     * Producto
     * @return
     */
    public MProduct getProduct() {
    	if(product == null && getM_Product_ID() > 0){
    		product = new MProduct(getCtx(), getM_Product_ID(), null);
    	}
    	return product;
    }
	/**
	 * Todos los paquetes por nivel de acceso activos
	 * 
	 * @param ctx
	 * @param trxName
	 * @return
	 */
	public static List<MEXMEPaqBaseVersion> get(final Properties ctx, final int paqBaseID,
			final String trxName) {

		if (ctx == null) {
			return null;
		}

		StringBuilder sql = new StringBuilder();
		List<Integer> params = new ArrayList<Integer>();

		sql.append(" SELECT * FROM EXME_PaqBase_Version ")
				.append(" WHERE EXME_PaqBase_Version.IsActive = 'Y' ")
				.append(" AND EXME_PaqBase_Version.EXME_PaqBase_ID = ? ")
				.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ",
						X_EXME_PaqBase_Version.Table_Name))
				.append(" ORDER BY EXME_PaqBase_Version.ValidFrom DESC ");

		params.add(paqBaseID);
		return get(ctx, sql.toString(), params, null);
	}

	/**
	 * Metdoos genericopara ejecutar una consulta y devuelva una lista de
	 * objetos MEXMEPaqBase
	 * 
	 * @param ctx
	 *            contexto Obligatorio
	 * @param sql
	 *            consulta
	 * @param params
	 *            parametros
	 * @param trxName
	 *            nombre de la transaccion
	 * @return List<MEXMEPaqBaseVersion>
	 */
	public static List<MEXMEPaqBaseVersion> get(final Properties ctx,
			final String sql, final List<?> params, final String trxName) {

		final List<MEXMEPaqBaseVersion> resultados = new ArrayList<MEXMEPaqBaseVersion>();

		if (ctx == null || sql == null) {
			return null;
		}

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = DB.prepareStatement(sql, trxName);
			DB.setParameters(pstmt, params);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				resultados.add(new MEXMEPaqBaseVersion(ctx, rs, trxName));
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} finally {
			DB.close(rs, pstmt);
		}

		return resultados;
	}

	/**
	 * Enlistar las versiones bajo ciertos parametros para el cliente y organización de logueo 
	 * @param ctx: Contexto
	 * @param date: Rango de fecha valido desde
	 * @param date2: Rango de fecha valido desde
	 * @param isActive: true, solo registros activos
	 * @param namePack: Nombre del paquete
	 * @param descriptionPack: Descripción del paquete
	 * @param productId: Producto del detalle o del encabezado
	 * @param trxName: Nombre de transacción
	 * @return Listado de versiones
	 */
	public static List<MEXMEPaqBaseVersion> getLstVersionMinipack(
			final Properties ctx,   final Timestamp date, final Timestamp date2,
			final boolean isActive, final String namePack,
			final String descriptionPack, final int productId,
			final String trxName) {

		final List<Object> params = new ArrayList<Object>();
		final StringBuilder where = new StringBuilder();

		// Producto
		if (productId > 0) {
			params.add(productId);
			params.add(productId);
			where.append(" AND ( pbv.M_Product_ID = ? OR pbd.M_Product_ID = ? ) ");
		}

		// Nombre del minipaquete o version
		if (!StringUtils.isEmpty(namePack)) {
			where.append(" AND ( pbv.Name like '%").append(namePack).append("%' OR pb.Name  like '%").append(namePack).append("%' ) ");
		}

		// Descripción
		if (!StringUtils.isEmpty(descriptionPack)) {
			where.append(" AND ( pbv.description like '%").append(descriptionPack).append("%' OR pb.description like '%").append(descriptionPack).append("%' ) ");
		}

		final List<MEXMEPaqBaseVersion> resultados = new ArrayList<MEXMEPaqBaseVersion>();
		final StringBuilder sql = new StringBuilder()
				.append(" SELECT pbv.* ")
				.append(" FROM EXME_PaqBase_Version pbv ")
				.append(" INNER JOIN EXME_PaqBase     pb ON  pbv.EXME_PaqBase_ID = pb.EXME_PaqBase_ID  ")
				.append("                                AND pb.IsActive = ? ")
				// #1
				.append("                                AND pb.IsMiniPack = 'Y' ")
				.append(" LEFT  JOIN EXME_PaqBaseDet pbd ON  pbv.EXME_PaqBase_Version_ID = pbd.EXME_PaqBase_Version_ID ")
				.append("                                AND pbd.IsActive = ? ")
				// #2
				.append(" WHERE pbv.IsActive = ? ")
				// #3
				.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ",
						X_EXME_PaqBase_Version.Table_Name, "pbv"))
				.append(" AND TRUNC(pbv.ValidFrom) BETWEEN ? AND ? ")
				// #4,5
				.append(where == null ? "" : where)
				.append(" GROUP BY pbv.EXME_PaqBase_Version_ID ")
				.append(" ORDER BY pbv.ValidFrom DESC ");

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setString(1, DB.TO_STRING(isActive));
			pstmt.setString(2, DB.TO_STRING(isActive));
			pstmt.setString(3, DB.TO_STRING(isActive));
			pstmt.setTimestamp(4, date);
			pstmt.setTimestamp(5, date2);
			for (int i = 0; i < params.size(); i++) {
				DB.setParameter(pstmt, 6 + i, params.get(i));
			}

			rs = pstmt.executeQuery();

			while (rs.next()) {
				resultados.add(new MEXMEPaqBaseVersion(ctx, rs, trxName));
			}
		} catch (Exception e) {
			slog.log(Level.SEVERE, null, e);
		} finally {
			DB.close(rs, pstmt);
		}

		return resultados;
	}
	

	/**
	 * Obtenemos la Version especifica del paquete que fue reservada a la cuenta
	 * paciente.
	 * 
	 * @return
	 */
	public MEXMEPaqBase getPaquete() {
		if (paquete == null && getEXME_PaqBase_ID() > 0)
			paquete = new MEXMEPaqBase(getCtx(), getEXME_PaqBase_ID(),
					get_TrxName());
		return paquete;
	}

	public void getPaquete(final MEXMEPaqBase pack) {
		paquete = pack;
	}

	public void setPaqBase(MEXMEPaqBase pack) {
		paquete = pack;
	}

	/**
	 * Listado de detalle de la version del paquete
	 * 
	 * @return
	 */
	public List<MEXMEPaqBaseDet> getLstDetalle() {
		return MEXMEPaqBaseDet.getDetalleDeVersion(getCtx(),
				getEXME_PaqBase_Version_ID(), true, null);
	}

	// /**
	// *
	// */
	// public static List<MDiagnostico> getDiagnosticos(Properties ctx, int
	// productID, String trxName) {
	// List<MDiagnostico> lst = new ArrayList<MDiagnostico>();
	// StringBuilder sql = new StringBuilder();
	//
	// sql.append("SELECT diag.* ")
	// .append(" FROM exme_diagnostico diag ")
	// .append(" INNER JOIN EXME_PaqBaseAtributo pba on pba.record_id = diag.exme_diagnostico_id ")
	// .append(" INNER JOIN EXME_PaqBaseDet line ")
	// .append(" ON line.exme_paqbasedet_id = pba.exme_paqbasedet_id ")
	// .append(" INNER JOIN EXME_PaqBase_Version version ON line.exme_paqbase_version_id = version.exme_paqbase_version_id ")
	// .append(" WHERE version.M_Product_ID       = ? ")
	// .append(" AND version.IsActive             = 'Y' ");
	// sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name,
	// "version"));
	// PreparedStatement pstmt = null;
	// ResultSet rs = null;
	//
	// try {
	// pstmt = DB.prepareStatement(sql.toString(), trxName);
	// pstmt.setInt(1, productID);
	// rs = pstmt.executeQuery();
	//
	// while (rs.next()) {
	// lst.add(new MDiagnostico(ctx, rs, null));
	// }
	// } catch (SQLException sqle) {
	// s_log.log(Level.SEVERE, sql.toString(), sqle);
	// } finally {
	// DB.close(rs, pstmt);
	// }
	// return lst;
	// }

	/**
	 * Buscar si existe el producto de la version en otro registro
	 * @param ctx: Contexto
	 * @param productID: Producto
	 * @param trxName: Nombre de transacción
	 * @return true: existe
	 */
	private boolean isAlreadyAdded(Properties ctx, int productID,
			int versionID, String trxName) {
		boolean isAlreadyAdded = false;
		final StringBuilder sql = new StringBuilder();
		sql.append(" SELECT * FROM EXME_PaqBase_Version ")
				.append(" WHERE EXME_PaqBase_Version.IsActive = 'Y' ")
				.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name))
				.append(" AND EXME_PaqBase_Version.EXME_PaqBase_Version_ID <> ? ")
				.append(" AND EXME_PaqBase_Version.M_Product_ID = ? ");

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, productID);
			pstmt.setInt(2, versionID);
			rs = pstmt.executeQuery();
			isAlreadyAdded = rs.next();
		} catch (SQLException sqle) {
			slog.log(Level.SEVERE, sql.toString(), sqle);
		} finally {
			DB.close(rs, pstmt);
		}
		return isAlreadyAdded;
	}

	@Override
	protected boolean beforeSave(boolean newRecord) {
		boolean success = true;
		
		if(!getPaqBase().isMiniPack()){
			if (isAlreadyAdded(getCtx(), getM_Product_ID(),
					getEXME_PaqBase_Version_ID(), get_TrxName())) {
				final MProduct prod = new MProduct(Env.getCtx(), getM_Product_ID(), null);
				log.saveError(null, Utilerias.getAppMsg(Env.getCtx(),
						"error.citas.existeMed", prod.getName()));
				success = false;
			}
		}
		return success;
	}

	/**
	 * El mismo producto para dos versiones distintas
	 * @param ctx
	 * @param versionId
	 * @param selectedId
	 * @param trxName
	 * @return
	 */
	public static boolean existeProduct(final Properties ctx, final int versionId,
			final int selectedId, final String trxName) {
		final String sql = " EXME_PaqBase_Version_ID <> ? AND M_Product_ID = ? ";
		final List<MEXMEPaqBaseVersion> lista = new Query(ctx,
				MEXMEPaqBaseVersion.Table_Name, sql, trxName)//
				.setOrderBy("Created") //
				.setParameters(versionId, selectedId) //
				.setOnlyActiveRecords(true) //
				.addAccessLevelSQL(true).list();

		return lista != null && !lista.isEmpty();
	}

	/**
	 * 
	 * @param ctx
	 * @param paqId
	 * @param versionId
	 * @return
	 */
	public static List<MEXMEPaqBaseVersion> getPackVersion(final Properties ctx,
			final int paqId, final int versionId) {
		final List<MEXMEPaqBaseVersion> list = new ArrayList<MEXMEPaqBaseVersion>();

		final StringBuilder sql = new StringBuilder();
		sql.append("SELECT ");
		sql.append("  * ");
		sql.append("FROM ");
		sql.append("  EXME_PaqBase_Version ");
		sql.append("WHERE ");
		sql.append("  EXME_PaqBase_Version_ID <> ? AND ");
		sql.append("  EXME_PaqBase_id = ? AND ");
		sql.append("  isactive = 'Y' ");

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setInt(1, versionId);
			pstmt.setInt(2, paqId);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				list.add(new MEXMEPaqBaseVersion(ctx, rs, null));
			}
		} catch (Exception e) {
			slog.log(Level.SEVERE, null, e);
		} finally {
			DB.close(rs, pstmt);
		}

		return list;
	}

	/** buscar las referencias del minipaquete */
	public boolean hasReferences() {
		// TODO: Card 1189 return
		// getEXME_PaqBase_Version_ID()>0?MPlanMed.haveMinipacks(getCtx(),
		// getEXME_PaqBase_Version_ID(), get_TrxName()):false;
		return false;// TODO Card 1189 quitar cuando se desarrolle card
	}
	
	 /**
     * Calculamos el total de la version con impuestos a partir del detalle
     */
    public void setTotalAmt() {
        
    	StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
        
        sql.append("SELECT SUM(LineTotalAmt) FROM EXME_PaqBaseDet ")
        	.append("WHERE EXME_PaqBase_Version_ID = ? ")
        	.append("AND IsActive = 'Y' ");
        
        sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(getCtx(), sql.toString(), "EXME_PaqBaseDet"));
        ResultSet rs = null;
        
		PreparedStatement pstmt = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), get_TrxName());
			pstmt.setInt(1, getEXME_PaqBase_Version_ID());
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				setTotalAmt(rs.getBigDecimal(1).setScale(2,BigDecimal.ROUND_HALF_UP));
			} else {
				setTotalAmt(Env.ZERO);
			}


		} catch (Exception e)	{
			log.log(Level.SEVERE, "setTotalAmt", e);
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close ();
				}
				
				if (rs != null) {
					rs.close();
				}
			} catch (Exception e) {
				log.log(Level.SEVERE, "Closing objects", e);
			}
		}
    }
    
    /**
     * Calculamos el total de la version con impuestos a partir del detalle
     */
    public void setTaxAmt() {
    	
    	StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
        
        sql.append("SELECT SUM(TaxAmt) FROM EXME_PaqBaseDet ")
        	.append("WHERE EXME_PaqBase_Version_ID = ? ")
        	.append("AND IsActive = 'Y' ");
        
        sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(getCtx(), sql.toString(), "EXME_PaqBaseDet"));
        
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = DB.prepareStatement(sql.toString(), get_TrxName());
			pstmt.setInt(1, getEXME_PaqBase_Version_ID());
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
			    setTaxAmt(rs.getBigDecimal(1).setScale(2,BigDecimal.ROUND_HALF_UP));
			} else {
				setTaxAmt(Env.ZERO);
			}
		} catch (Exception e) {
			log.log(Level.SEVERE, "setTaxAmt", e);
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close ();
				}
				
				if (rs != null) {
					rs.close();
				}
			} catch (Exception e) {
				log.log(Level.SEVERE, "setTaxAmt - while closing objects", e);
			}
		}
    }
    
    /**
     * Calculamos la base de la version a partir del detalle
     */
    public void setBaseAmt() {
        
    	StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
    	
        sql.append("SELECT SUM(Cantidad * PriceList) FROM EXME_PaqBaseDet ")
        	.append("WHERE EXME_PaqBase_Version_ID = ? ")
        	.append("AND IsActive = 'Y' ");
        
        sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(getCtx(), sql.toString(), "EXME_PaqBaseDet"));
        
        ResultSet rs = null;
        
		PreparedStatement pstmt = null;
		try	{
			pstmt = DB.prepareStatement(sql.toString(), get_TrxName());
			pstmt.setInt(1, getEXME_PaqBase_Version_ID());
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				setBaseAmt((rs.getBigDecimal(1).setScale(2,BigDecimal.ROUND_HALF_UP)));
			} else {
				setBaseAmt(Env.ZERO);
			}
		} catch (Exception e) {
			log.log(Level.SEVERE, "setBaseAmt", e);
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				
				if (rs != null) {
					rs.close();
				}
			} catch (Exception e) {
				log.log(Level.SEVERE, "setBaseAmt - Closing objects", e);
			}
		}
    }

//    /**
//     * getPacksLVB
//     * @param ctx
//     * @param C_BPartner_ID
//     * @param fecha
//     * @return
//     */
//	public static List<LabelValueBean> getPacksLVB(Properties ctx, int C_BPartner_ID, Timestamp fecha) {
//		List<LabelValueBean> lstLVB = new ArrayList<LabelValueBean>();
//		List<MEXMEPaqBaseVersion> lst = MEXMEPaqBaseVersion.getPacks(ctx, C_BPartner_ID, fecha, null);
//		for (int i = 0; i < lst.size(); i++) {
//			lstLVB.add(new LabelValueBean(lst.get(i).getPaquete().getName() + " - " + lst.get(i).getName(), String.valueOf(lst.get(i)
//				.getEXME_PaqBase_Version_ID())));
//		}
//		return lstLVB;
//	}
    
    /**
     * 
     * @param ctx
     * @param cadena
     * @param fechaDesde
     * @param fechaHasta
     * @param trxName
     * @return
     */
    public static List<MEXMEPaqBaseVersion> getPackages(Properties ctx, int C_BPartner_ID, 
            Timestamp fecha, String trxName){
        
    	 List<MEXMEPaqBaseVersion> retValue = new ArrayList<MEXMEPaqBaseVersion>();
		
    	 StringBuilder sql = new StringBuilder();
    	 sql.append("SELECT ");
    	 sql.append("  v.* ");
    	 sql.append("FROM ");
    	 sql.append("  EXME_PaqBase_Version v ");
    	 sql.append("  INNER JOIN exme_productoorg po ");
    	 sql.append("  ON (v.ad_org_id = po.ad_org_id AND ");
    	 sql.append("  v.m_product_id = po.m_product_id) ");
    	 sql.append("WHERE ");
    	 sql.append("  v.IsActive = 'Y' AND ");
    	 sql.append("  po.unused = 'N' ");

		if (DB.isOracle()) {
			sql.append(" AND trunc( v.ValidFrom,'dd') <=  trunc(? ,'dd') ");
		} else if (DB.isPostgreSQL()) {
        	// RQM: 2878. No se ven los paquetes.
        	//Cuando se usa un date_trunc con un timestamp no funciona el pstmt.setTimestamp, se debe dar formato.
        	//Como abajo se muestra con TO_DATE.
        	sql.append(" AND DATE_TRUNC('day', v.ValidFrom) <=  DATE_TRUNC('day', TO_DATE(?,'dd/MM/yyyy')) ");
        }
		
	    sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, Constantes.SPACE, X_EXME_PaqBase_Version.Table_Name, "v"));
	                
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			
			if (DB.isOracle()) {
				pstmt.setTimestamp(1, fecha);
			} else if (DB.isPostgreSQL()) {
				//Setear el String con un SimpleDateFormat ya que no funciona con el date_trunc el timestamp.
				pstmt.setString(1, Constantes.getSdfFecha().format(fecha));
			}
			
			rs = pstmt.executeQuery();
			while (rs.next()) {
				MEXMEPaqBaseVersion pack = new MEXMEPaqBaseVersion(ctx, rs, trxName);
				pack.setPaqBase(new MEXMEPaqBase(ctx, pack.getEXME_PaqBase_ID(), trxName));
				retValue.add(pack);
			}
		} catch (Exception e) {
			slog.log(Level.SEVERE, "MPaqBase_Version.get : sql = " + sql
					+ " param : " + fecha, e);
		} finally {
			DB.close(rs, pstmt);
		}

		return retValue;

	}
	/**
	 * Obtenemos la Version especifica del paquete que fue reservada a la cuenta
	 * paciente.
	 * 
	 * @return
	 */
	public MEXMEPaqBase getPaqBase() {
		if (paqBase == null && getEXME_PaqBase_ID() > 0)
			paqBase = new MEXMEPaqBase(getCtx(), getEXME_PaqBase_ID(),
					get_TrxName());
		return paqBase;
	}

}
