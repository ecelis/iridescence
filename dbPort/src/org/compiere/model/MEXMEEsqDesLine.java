package org.compiere.model;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.logging.Level;

import org.apache.commons.lang.StringUtils;
import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.Env;

/**
 * Convenios
 * 
 * @author twry
 */
public class MEXMEEsqDesLine extends X_EXME_EsqDesLine {

	/** serialVersionUID */
	private static final long serialVersionUID = -8758429377912122080L;
	/** Static Logger */
	private static CLogger slog = CLogger.getCLogger(MEXMEEsqDesLine.class);
	/** area del convenio */
	private transient MEXMEArea mArea = null;
	/** unidad de medida del producto del convenio */
	private transient MUOM mUOM = null;
	
	/** constructor */
	public MEXMEEsqDesLine(final Properties ctx,final  int esqDesLineID,
			final String trxName) {
		super(ctx, esqDesLineID, trxName);
	}

	/** constructor */
	public MEXMEEsqDesLine(final Properties ctx, final ResultSet rSet, final String trxName) {
		super(ctx, rSet, trxName);
	}

	/**
	 * Obj Area (Servicio)
	 * @return MEXMEArea
	 */
	public MEXMEArea getArea() {
		if (mArea == null || mArea.getEXME_Area_ID() == 0) {
			mArea = new MEXMEArea(getCtx(), getEXME_Area_ID(), get_TrxName());
		}
		return mArea;
	}

	/**
	 * Obj Unidad de medida
	 * @return MUOM
	 */
	public MUOM getUom() {
		if (mUOM == null || mUOM.getC_UOM_ID() == 0) {
			mUOM = new MUOM(getCtx(), getC_UOM_ID(), get_TrxName());
		}
		return mUOM;
	}

	
	/**
	 * Devuelve el esquema de descuento segun criterios.
	 * 
	 * @param ctx
	 * @param trxName
	 * @param cBPartnerId
	 * @param cBPGroupId
	 * @param adOrgId
	 * @param adClientId
	 * @param exmeCtaPacExtId
	 * @param tipoArea
	 * @param mProductId
	 * @param mProdCategoryId
	 * @param date
	 * @return el objecto <code> MEXMEEsqDesLine </code> con la información
	 *         encontrada.
	 */
	
	public static MEXMEEsqDesLine cargasCtaPacDet(final Properties ctx,
			final String trxName,  final int cBPartnerId, final int cBPGroupId,
			final int adOrgId,     final int adClientId,  final int exmeCtaPacExtId,
			final String tipoArea, final int mProductId,  final int mProdCategoryId,
			final Timestamp date) {
		
		MEXMEEsqDesLine list = null;
		final StringBuilder sql = new StringBuilder(100);
		PreparedStatement pstmt = null;
		ResultSet rSet = null;

		try {
			sql.append(
					" SELECT exme_esqdesline_id, m_product_id, m_product_category_id, c_bpartner_id, c_bp_group_id, ")
					.append("           List_AddAmt, List_Discount, EXME_Area_ID, TipoArea, ValidFrom, Validto, AD_Client_ID, AD_Org_ID ")
					.append("    FROM ( ")
					.append("       	select edl.exme_esqdesline_id, edl.AD_Client_ID, edl.AD_Org_ID, ")
					.append("       	edl.m_product_id, edl.m_product_category_id, ")
					.append(" 			edl.c_bpartner_id, edl.c_bp_group_id,  ")
					.append(" 			edl.List_AddAmt,edl.List_Discount, ")
					.append(" 			edl.EXME_Area_ID,edl.TipoArea, ")
					.append(" 			edl.validFrom, edl.validto ")
					.append(" 			from exme_esqdesline edl ")
					.append(" 			where edl.isactive = 'Y' ")
					.append(" 			and ( ")
					.append(" 					edl.m_product_id in (select m_product_id from exme_ctapacext where isActive = 'Y' and exme_ctapacext_id = ? ) ")
					// 1
					.append(" 					or  edl.m_product_category_id in (  select distinct pc.m_product_category_id   from exme_ctapacdet cpd   inner join m_product p on cpd.m_product_id = p.m_product_id  inner join m_product_category pc on p.m_product_category_id = pc.m_product_category_id  where  cpd.isActive = 'Y' and  cpd.exme_ctapacext_id = ? ) ")
					// 2
					.append(" 			) and (  ")
					.append(" 					edl.c_bpartner_id = ? ")
					// 3
					.append(" 					or  edl.c_bp_group_id = ? ")
					// 4
					.append(" 			) ")
					.append(" 			order by edl.m_product_id, edl.c_bpartner_id, edl.m_product_category_id, edl.c_bp_group_id ")
					.append(" 	)  ");

			// Agregar el Alias en la Subconsulta si es Postgresql.
			// Si es oracle no se necesita. Jesus Cantu.
			if (DB.isPostgreSQL()) {
				sql.append("as consulta");
			}

			sql.append(" WHERE AD_Client_ID = ? AND AD_Org_ID = ? ")
					// 5,6
					.append(" AND (m_product_id = ? OR m_product_category_id = ?) ")// 7,8
					.append(" AND TipoArea = ? ")// 9
					.append(" AND ValidFrom <= TO_DATE(? ,'dd/mm/yyyy HH24:MI' )")// 10
					.append(" AND ValidTo  >= TO_DATE(? ,'dd/mm/yyyy HH24:MI' )")// 11
					.append(" ORDER BY m_product_id, c_bpartner_id, m_product_category_id, c_bp_group_id, validFrom, EXME_Area_ID ");

			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, exmeCtaPacExtId);
			pstmt.setInt(2, exmeCtaPacExtId);
			pstmt.setInt(3, cBPartnerId);
			pstmt.setInt(4, cBPGroupId);
			pstmt.setInt(5, adClientId);
			pstmt.setInt(6, adOrgId);
			pstmt.setInt(7, mProductId);
			pstmt.setInt(8, mProdCategoryId);
			pstmt.setString(9, tipoArea);
			pstmt.setString(10, Constantes.getSdfFechaHora().format(date));
			pstmt.setString(11, Constantes.getSdfFechaHora().format(date));

			rSet = pstmt.executeQuery();
			if (rSet.next()) {
				list = new MEXMEEsqDesLine(ctx,
						rSet.getInt("exme_esqdesline_id"), trxName);
			}
		} catch (Exception e) {
			slog.log(Level.SEVERE, "sql: " + sql, e);
		} finally {
			DB.close(rSet, pstmt);
		}

		return list;

	}

	/**
	 * Descuento
	 * @param ctx
	 * @param trxName
	 * @param cBPartnerId
	 * @param cBPGroupId
	 * @param adOrgId
	 * @param adClientId
	 * @param tipoArea
	 * @param mProductId
	 * @param mProdCategoryId
	 * @param date
	 * @return
	 */
	public static MEXMEEsqDesLine esquemaDescuento(final Properties ctx,
			final String trxName,  final int cBPartnerId, final int cBPGroupId,
			final int adOrgId,     final int adClientId,  
			final String tipoArea, final int mProductId,  final int mProdCategoryId,
			final Timestamp date) {
		
		MEXMEEsqDesLine list = null;
		final StringBuilder sql = new StringBuilder(100);
		PreparedStatement pstmt = null;
		ResultSet rSet = null;

		try {
			sql.append(" SELECT edl.*          ")
			.append(" FROM EXME_EsqDesLine edl ")
			.append(" LEFT JOIN M_Product           p ON p.M_Product_ID = edl.M_Product_ID AND p.M_Product_ID = ?        ")
			.append(" LEFT JOIN M_Product_Category pc ON pc.M_Product_Category_ID = edl.M_Product_Category_ID AND pc.M_Product_Category_ID = ? ")
			.append(" LEFT JOIN C_BPartner         bp ON bp.C_BPartner_ID = edl.C_BPartner_ID AND bp.C_BPartner_ID = ?   ")
			.append(" LEFT JOIN C_BP_Group        bpg ON bpg.C_BP_Group_ID = edl.C_BP_Group_ID AND bpg.C_BP_Group_ID = ? ")

			.append(" WHERE edl.IsActive = 'Y' ")
			.append(" AND edl.AD_Client_ID = ? ") 
			.append(" AND edl.AD_Org_ID    = ? ")
			.append(" AND (edl.m_product_id  = ? OR edl.m_product_category_id = ? ) ")  
			.append(" AND (edl.c_bpartner_id = ? OR edl.c_bp_group_id = ? )         ")
			.append(" AND edl.TipoArea     = ? ");
			
			if (DB.isOracle()) {
				sql.append(" AND TRUNC(edl.ValidFrom,'DD') <= TRUNC(").append(DB.TO_DATE(date)).append(",'DD')");
				sql.append(" AND TRUNC(edl.ValidTo,'DD')   >= TRUNC(").append(DB.TO_DATE(date)).append(",'DD')");
			} else if (DB.isPostgreSQL()) {
				sql.append(" AND DATE_TRUNC('day', edl.ValidFrom) <= DATE_TRUNC('day',").append(DB.TO_DATE(date)).append(")");
				sql.append(" AND DATE_TRUNC('day', edl.ValidTo)   >= DATE_TRUNC('day',").append(DB.TO_DATE(date)).append(")");
			}
			
			sql.append(" ORDER BY edl.m_product_id DESC, edl.c_bpartner_id DESC, edl.m_product_category_id DESC, edl.c_bp_group_id ");

			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, mProductId);
			pstmt.setInt(2, mProdCategoryId);
			pstmt.setInt(3, cBPartnerId);
			pstmt.setInt(4, cBPGroupId);
			
			pstmt.setInt(5, adClientId);
			pstmt.setInt(6, adOrgId);
			
			pstmt.setInt(7, mProductId);
			pstmt.setInt(8, mProdCategoryId);
			pstmt.setInt(9, cBPartnerId);
			pstmt.setInt(10, cBPGroupId);
			
			pstmt.setString(11, tipoArea);
//			pstmt.setTimestamp(12, DB.getTimestampForOrg(Env.getCtx()));
//			pstmt.setTimestamp(13, DB.getTimestampForOrg(Env.getCtx()));

			rSet = pstmt.executeQuery();
			if (rSet.next()) {
				list = new MEXMEEsqDesLine(ctx,rSet, trxName);
			}
			
		} catch (Exception e) {
			slog.log(Level.SEVERE, "sql: " + sql, e);
		} finally {
			DB.close(rSet, pstmt);
		}

		return list;

	}
	
	/**
	 * Obtiene el acuerdo adecuado segun el orden de importacia 1.- Global por
	 * cliente 2.- Global por grupo de cliente 3.- Producto y cliente 4.-
	 * Producto y grupo de clientes 5.- Categoria de producto y cliente 6.-
	 * Categoria de producto y grupo de cliente
	 * 
	 * @param ctx
	 *            Contexto
	 * @param cbPartnerId
	 *            Cliente
	 * @param cBPGroupId
	 *            Grupo de Cliente
	 * @param tipoArea
	 *            Tipo de Area
	 * @param productId
	 *            Producto
	 * @param productCategoryId
	 *            Categoria de Producto
	 * @param date
	 *            Fecha
	 * @return Acuerdo o nulo en caso de no encontrar
	 */
	public static MEXMEEsqDesLine getAgreement(Properties ctx, int cbPartnerId, int cBPGroupId, String tipoArea, int productId, int productCategoryId, Date date) {
		// Revisamos global por cliente
		MEXMEEsqDesLine desLine = getAgreementN1(ctx, cbPartnerId, tipoArea, date);

		if (desLine == null) {
			// Revisamos global por grupo de cliente
			desLine = getAgreementN2(ctx, cBPGroupId, tipoArea, date);

			if (desLine == null) {
				// Revisamos por producto y cliente
				desLine = getAgreementN3(ctx, productId, cbPartnerId, tipoArea, date);

				if (desLine == null) {
					// Revisamos por producto y grupo de clientes
					desLine = getAgreementN4(ctx, productId, cBPGroupId, tipoArea, date);

					if (desLine == null) {
						// Revisamos por categoria y cliente
						desLine = getAgreementN5(ctx, productCategoryId, cbPartnerId, tipoArea, date);

						if (desLine == null) {
							// Revisamos por categoria y grupo de cliente
							desLine = getAgreementN6(ctx, productCategoryId, cBPGroupId, tipoArea, date);
						}
					}
				}
			}
		}

		return desLine;
	}
	
	public static MEXMEEsqDesLine getAgreementN1(final Properties ctx, int cBPartnerId, String tipoArea, Date date) {
		MEXMEEsqDesLine list = null;
		PreparedStatement pstmt = null;
		ResultSet rSet = null;
		
		StringBuilder sql = new StringBuilder();

		try {
			
			sql.append("SELECT ");
			sql.append("  edl.* ");
			sql.append("FROM ");
			sql.append("  EXME_EsqDesLine edl ");
			sql.append("WHERE ");
			sql.append("  edl.IsActive = 'Y' AND ");
			sql.append("  edl.AD_Client_ID = ? AND ");
			sql.append("  edl.AD_Org_ID = ? AND ");
			sql.append("  edl.m_product_id is null AND ");
			sql.append("  edl.m_product_category_id is null AND ");
			sql.append("  edl.c_bpartner_id = ? AND ");
			sql.append("  (edl.TipoArea = ? OR edl.TipoArea is null)  AND");
			sql.append("  edl.ValidFrom <= ? AND ");
			sql.append("  edl.ValidTo >= ? ");
			sql.append(" ORDER BY edl.ValidFrom ");

			pstmt = DB.prepareStatement(sql.toString(), null);

			pstmt.setInt(1, Env.getAD_Client_ID(ctx));
			pstmt.setInt(2, Env.getAD_Org_ID(ctx));

			pstmt.setInt(3, cBPartnerId);

			pstmt.setString(4, StringUtils.isBlank(tipoArea) ? "X" : tipoArea);
			pstmt.setTimestamp(5, new Timestamp(date.getTime()));
			pstmt.setTimestamp(6, new Timestamp(date.getTime()));

			rSet = pstmt.executeQuery();
			if (rSet.next()) {
				list = new MEXMEEsqDesLine(ctx, rSet, null);
			}

		} catch (Exception e) {
			slog.log(Level.SEVERE, "sql: " + sql, e);
		} finally {
			DB.close(rSet, pstmt);
		}

		return list;
	}
	
	public static MEXMEEsqDesLine getAgreementN2(final Properties ctx, int cBPGroupId, String tipoArea, Date date) {
		MEXMEEsqDesLine list = null;
		PreparedStatement pstmt = null;
		ResultSet rSet = null;
		
		StringBuilder sql = new StringBuilder();

		try {
			
			sql.append("SELECT ");
			sql.append("  edl.* ");
			sql.append("FROM ");
			sql.append("  EXME_EsqDesLine edl ");
			sql.append("WHERE ");
			sql.append("  edl.IsActive = 'Y' AND ");
			sql.append("  edl.AD_Client_ID = ? AND ");
			sql.append("  edl.AD_Org_ID = ? AND ");
			sql.append("  edl.m_product_id is null AND ");
			sql.append("  edl.m_product_category_id is null AND ");
			sql.append("  edl.c_bp_group_id = ? AND ");
			sql.append("  (edl.TipoArea = ? OR edl.TipoArea is null)  AND");
			sql.append("  edl.ValidFrom <= ? AND ");
			sql.append("  edl.ValidTo >= ? ");
			sql.append(" ORDER BY edl.ValidFrom ");

			pstmt = DB.prepareStatement(sql.toString(), null);

			pstmt.setInt(1, Env.getAD_Client_ID(ctx));
			pstmt.setInt(2, Env.getAD_Org_ID(ctx));

			pstmt.setInt(3, cBPGroupId);

			pstmt.setString(4, StringUtils.isBlank(tipoArea) ? "X" : tipoArea);
			pstmt.setTimestamp(5, new Timestamp(date.getTime()));
			pstmt.setTimestamp(6, new Timestamp(date.getTime()));

			rSet = pstmt.executeQuery();
			if (rSet.next()) {
				list = new MEXMEEsqDesLine(ctx, rSet, null);
			}

		} catch (Exception e) {
			slog.log(Level.SEVERE, "sql: " + sql, e);
		} finally {
			DB.close(rSet, pstmt);
		}

		return list;
	}

	public static MEXMEEsqDesLine getAgreementN3(final Properties ctx, int mProductId, int cBPartnerId, String tipoArea, Date date) {
		MEXMEEsqDesLine list = null;
		PreparedStatement pstmt = null;
		ResultSet rSet = null;
		
		StringBuilder sql = new StringBuilder();

		try {
			
			sql.append("SELECT ");
			sql.append("  edl.* ");
			sql.append("FROM ");
			sql.append("  EXME_EsqDesLine edl ");
			sql.append("WHERE ");
			sql.append("  edl.IsActive = 'Y' AND ");
			sql.append("  edl.AD_Client_ID = ? AND ");
			sql.append("  edl.AD_Org_ID = ? AND ");
			sql.append("  edl.m_product_id = ? AND ");
			sql.append("  edl.c_bpartner_id = ? AND ");
			sql.append("  (edl.TipoArea = ? OR edl.TipoArea is null)  AND");
			sql.append("  edl.ValidFrom <= ? AND ");
			sql.append("  edl.ValidTo >= ? ");
			sql.append(" ORDER BY edl.ValidFrom ");

			pstmt = DB.prepareStatement(sql.toString(), null);

			pstmt.setInt(1, Env.getAD_Client_ID(ctx));
			pstmt.setInt(2, Env.getAD_Org_ID(ctx));

			pstmt.setInt(3, mProductId);
			pstmt.setInt(4, cBPartnerId);

			pstmt.setString(5, StringUtils.isBlank(tipoArea) ? "X" : tipoArea);
			pstmt.setTimestamp(6, new Timestamp(date.getTime()));
			pstmt.setTimestamp(7, new Timestamp(date.getTime()));

			rSet = pstmt.executeQuery();
			if (rSet.next()) {
				list = new MEXMEEsqDesLine(ctx, rSet, null);
			}

		} catch (Exception e) {
			slog.log(Level.SEVERE, "sql: " + sql, e);
		} finally {
			DB.close(rSet, pstmt);
		}

		return list;
	}
	
	public static MEXMEEsqDesLine getAgreementN4(final Properties ctx, int mProductId, int cBPGroupId, String tipoArea, Date date) {
		MEXMEEsqDesLine list = null;
		PreparedStatement pstmt = null;
		ResultSet rSet = null;
		
		StringBuilder sql = new StringBuilder();

		try {
			
			sql.append("SELECT ");
			sql.append("  edl.* ");
			sql.append("FROM ");
			sql.append("  EXME_EsqDesLine edl ");
			sql.append("WHERE ");
			sql.append("  edl.IsActive = 'Y' AND ");
			sql.append("  edl.AD_Client_ID = ? AND ");
			sql.append("  edl.AD_Org_ID = ? AND ");
			sql.append("  edl.m_product_id = ? AND ");
			sql.append("  edl.c_bp_group_id = ? AND ");
			sql.append("  (edl.TipoArea = ? OR edl.TipoArea is null)  AND");
			sql.append("  edl.ValidFrom <= ? AND ");
			sql.append("  edl.ValidTo >= ? ");
			sql.append(" ORDER BY edl.ValidFrom ");

			pstmt = DB.prepareStatement(sql.toString(), null);

			pstmt.setInt(1, Env.getAD_Client_ID(ctx));
			pstmt.setInt(2, Env.getAD_Org_ID(ctx));

			pstmt.setInt(3, mProductId);
			pstmt.setInt(4, cBPGroupId);

			pstmt.setString(5, StringUtils.isBlank(tipoArea) ? "X" : tipoArea);
			pstmt.setTimestamp(6, new Timestamp(date.getTime()));
			pstmt.setTimestamp(7, new Timestamp(date.getTime()));

			rSet = pstmt.executeQuery();
			if (rSet.next()) {
				list = new MEXMEEsqDesLine(ctx, rSet, null);
			}

		} catch (Exception e) {
			slog.log(Level.SEVERE, "sql: " + sql, e);
		} finally {
			DB.close(rSet, pstmt);
		}

		return list;
	}
	
	public static MEXMEEsqDesLine getAgreementN5(final Properties ctx, int mProductCatId, int cBPartnerId, String tipoArea, Date date) {
		MEXMEEsqDesLine list = null;
		PreparedStatement pstmt = null;
		ResultSet rSet = null;
		
		StringBuilder sql = new StringBuilder();

		try {
			
			sql.append("SELECT ");
			sql.append("  edl.* ");
			sql.append("FROM ");
			sql.append("  EXME_EsqDesLine edl ");
			sql.append("WHERE ");
			sql.append("  edl.IsActive = 'Y' AND ");
			sql.append("  edl.AD_Client_ID = ? AND ");
			sql.append("  edl.AD_Org_ID = ? AND ");
			sql.append("  edl.m_product_category_id = ? AND ");
			sql.append("  edl.c_bpartner_id = ? AND ");
			sql.append("  (edl.TipoArea = ? OR edl.TipoArea is null)  AND");
			sql.append("  edl.ValidFrom <= ? AND ");
			sql.append("  edl.ValidTo >= ? ");
			sql.append(" ORDER BY edl.ValidFrom ");

			pstmt = DB.prepareStatement(sql.toString(), null);

			pstmt.setInt(1, Env.getAD_Client_ID(ctx));
			pstmt.setInt(2, Env.getAD_Org_ID(ctx));

			pstmt.setInt(3, mProductCatId);
			pstmt.setInt(4, cBPartnerId);

			pstmt.setString(5, StringUtils.isBlank(tipoArea) ? "X" : tipoArea);
			pstmt.setTimestamp(6, new Timestamp(date.getTime()));
			pstmt.setTimestamp(7, new Timestamp(date.getTime()));

			rSet = pstmt.executeQuery();
			if (rSet.next()) {
				list = new MEXMEEsqDesLine(ctx, rSet, null);
			}

		} catch (Exception e) {
			slog.log(Level.SEVERE, "sql: " + sql, e);
		} finally {
			DB.close(rSet, pstmt);
		}

		return list;
	}
	
	public static MEXMEEsqDesLine getAgreementN6(final Properties ctx, int mProductCatId, int cBPGroupId, String tipoArea, Date date) {
		MEXMEEsqDesLine list = null;
		PreparedStatement pstmt = null;
		ResultSet rSet = null;
		
		StringBuilder sql = new StringBuilder();

		try {
			
			sql.append("SELECT ");
			sql.append("  edl.* ");
			sql.append("FROM ");
			sql.append("  EXME_EsqDesLine edl ");
			sql.append("WHERE ");
			sql.append("  edl.IsActive = 'Y' AND ");
			sql.append("  edl.AD_Client_ID = ? AND ");
			sql.append("  edl.AD_Org_ID = ? AND ");
			sql.append("  edl.m_product_category_id = ? AND ");
			sql.append("  edl.c_bp_group_id = ? AND ");
			sql.append("  (edl.TipoArea = ? OR edl.TipoArea is null)  AND");
			sql.append("  edl.ValidFrom <= ? AND ");
			sql.append("  edl.ValidTo >= ? ");
			sql.append(" ORDER BY edl.ValidFrom ");

			pstmt = DB.prepareStatement(sql.toString(), null);

			pstmt.setInt(1, Env.getAD_Client_ID(ctx));
			pstmt.setInt(2, Env.getAD_Org_ID(ctx));

			pstmt.setInt(3, mProductCatId);
			pstmt.setInt(4, cBPGroupId);

			pstmt.setString(5, StringUtils.isBlank(tipoArea) ? "X" : tipoArea);
			pstmt.setTimestamp(6, new Timestamp(date.getTime()));
			pstmt.setTimestamp(7, new Timestamp(date.getTime()));

			rSet = pstmt.executeQuery();
			if (rSet.next()) {
				list = new MEXMEEsqDesLine(ctx, rSet, null);
			}

		} catch (Exception e) {
			slog.log(Level.SEVERE, "sql: " + sql, e);
		} finally {
			DB.close(rSet, pstmt);
		}

		return list;
	}
	
	/**
	 * 
	 * @param ctx
	 * @param whereClause
	 * @param orderBy
	 * @param trxName
	 * @param all
	 * @return
	 */
	private static MEXMEEsqDesLine[] get(final Properties ctx, final String sql,
			final List<?> parameters, final String trxName) {

		if(sql==null || ctx ==null){
			return null;
		}
		
		final ArrayList<MEXMEEsqDesLine> list = new ArrayList<MEXMEEsqDesLine>();
		PreparedStatement pstmt = null;
		ResultSet rSet = null;

		try {

			pstmt = DB.prepareStatement(sql, trxName);
			DB.setParameters(pstmt, parameters);
			rSet = pstmt.executeQuery();

			while (rSet.next()) {
				list.add(new MEXMEEsqDesLine(ctx, rSet,
						trxName));
			}

		} catch (Exception e) {
			slog.log(Level.SEVERE, "sql : " + sql, e);
		} finally {
			DB.close(rSet, pstmt);
		}

		final MEXMEEsqDesLine[] esqDescLineas = new MEXMEEsqDesLine[list.size()];
		list.toArray(esqDescLineas);
		return esqDescLineas;
	}

	/**
	 * Realiza la busqueda segun el critero seleccionado
	 * 
	 * @param criterio
	 * @param valor
	 * @param ctx
	 * @param whereclause
	 * @param orderBlock
	 * @return
	 * @throws Exception
	 */
	public static MEXMEEsqDesLine[] busqueda(final Properties ctx, final String criterio,
			final String valor) {

		MEXMEEsqDesLine[] esqDescs = null;
		final StringBuilder sql = new StringBuilder(
				Constantes.INIT_CAPACITY_ARRAY);
		final StringBuilder sqlWhere = new StringBuilder(
				Constantes.INIT_CAPACITY_ARRAY);
		final List<Object> parameters = new ArrayList<Object>();
		final String operador = criterio.isEmpty() ? " OR " : " AND ";
		
		try {

			sql.append(" SELECT EXME_EsqDesLine.* FROM EXME_EsqDesLine ");

			if (!valor.isEmpty() && !"%".equals(valor)) {

				if (criterio.startsWith("C_BPartner.") || criterio.isEmpty()) {
					sql.append(" LEFT JOIN C_BPartner ON C_BPartner.C_BPartner_ID = EXME_EsqDesLine.C_BPartner_ID AND C_BPartner.isActive = 'Y' ");
					if(criterio.isEmpty()&& (!valor.isEmpty() && !"%".equals(valor))){
						sqlWhere.append( " ( UPPER(C_BPartner.value) LIKE UPPER(?) OR UPPER(C_BPartner.Name) LIKE UPPER(?)  )");
						parameters.add(valor);
						parameters.add(valor);
					}else {
					sqlWhere.append(operador
							+ " ( UPPER(C_BPartner.value) LIKE UPPER(?) OR UPPER(C_BPartner.Name) LIKE UPPER(?)  )");
					parameters.add(valor);
					parameters.add(valor);
					}
				}

				if (criterio.startsWith("C_BP_Group.") || criterio.isEmpty()) {
					sql.append(" LEFT JOIN C_BP_Group ON C_BP_Group.C_BP_Group_ID = EXME_EsqDesLine.C_BP_Group_ID AND C_BP_Group.isActive = 'Y' ");
					sqlWhere.append(operador
							+ " ( UPPER(C_BP_Group.value) LIKE UPPER(?) OR UPPER(C_BP_Group.Name) LIKE UPPER(?)  )");
					parameters.add(valor);
					parameters.add(valor);
				}

				if (criterio.startsWith("M_Product.") || criterio.isEmpty()) {
					sql.append(" LEFT JOIN M_Product ON M_Product.M_Product_ID = EXME_EsqDesLine.M_Product_ID AND M_Product.isActive = 'Y' ");
					sqlWhere.append(operador
							+ " ( UPPER(M_Product.value) LIKE UPPER(?) OR UPPER(M_Product.Name) LIKE UPPER(?)  )");
					parameters.add(valor);
					parameters.add(valor);
				}

				if (criterio.startsWith("M_Product_Category.")
						|| criterio.isEmpty()) {
					sql.append(" LEFT JOIN M_Product_Category ON (M_Product_Category.M_Product_Category_ID = EXME_EsqDesLine.M_Product_Category_ID ) AND M_Product_Category.isActive = 'Y' ");
					sqlWhere.append(operador
							+ " ( UPPER(M_Product_Category.value) LIKE UPPER(?) OR UPPER(M_Product_Category.Name) LIKE UPPER(?)  )");
					parameters.add(valor);
					parameters.add(valor);
				}

				if (criterio.startsWith("EXME_Area.") || criterio.isEmpty()) {
					sql.append(" LEFT JOIN EXME_Area ON (EXME_Area.EXME_Area_ID = EXME_EsqDesLine.EXME_Area_ID) AND EXME_Area.isActive = 'Y' ");
					sqlWhere.append(operador
							+ " ( UPPER(EXME_Area.value) LIKE UPPER(?) OR UPPER(EXME_Area.Name) LIKE UPPER(?)  )");
					parameters.add(valor);
					parameters.add(valor);
				}

				if (criterio.startsWith("EXME_EsqDesLine.") || criterio.isEmpty()) {
					final String AD_Language = Env.getAD_Language(ctx);
					sql.append(" LEFT JOIN AD_Ref_List ON EXME_EsqDesLine.TIPOAREA=AD_Ref_List.Value ");
					sql.append(" AND AD_Ref_List.AD_Reference_ID = ")
					.append(X_EXME_EsqDesLine.TIPOAREA_AD_Reference_ID)
					.append(" ");
					sql.append(" LEFT JOIN AD_Ref_List_Trl t ON AD_Ref_List.AD_Ref_List_ID=t.AD_Ref_List_ID ");
					sql.append(" AND t.AD_Language = ")
					.append("'" + AD_Language + "'");
					sqlWhere.append(operador).append(
							" UPPER(t.Name) LIKE UPPER(?) ");
					parameters.add(valor);
				}
			}
			

					sql.append(" WHERE EXME_EsqDesLine.isActive = 'Y' ");
					sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ","EXME_EsqDesLine"));
					if(criterio.isEmpty()&& (!valor.isEmpty() && !"%".equals(valor))){
						sql.append(" AND ( ");
					}
					sql.append(sqlWhere == null ? "":sqlWhere);
					if(criterio.isEmpty()&& (!valor.isEmpty() && !"%".equals(valor))){
						sql.append(" ) " );
					}
					
					sql.append(" ORDER BY  EXME_EsqDesLine_ID DESC  ");

			esqDescs = MEXMEEsqDesLine.get(ctx, sql.toString(), parameters,
					null);

		} catch (Exception e) {
			slog.log(Level.SEVERE, "sql : " + sql, e);
		}

		return esqDescs;
	}
	
	/**
	 * B&uacutesqueda de convenios
	 * 
	 * @param ctx
	 *            Contexto de la App
	 * @param cbPartnerId
	 *            Socio de Negocios
	 * @param cbPartnerGpId
	 *            Grupo de Socios
	 * @param productId
	 *            Producto
	 * @param productCategoryId
	 *            Categor&iacutea de productos
	 * @param tipoArea
	 *            Tipo de Area
	 * @return Resultado de la b&uacutesqueda
	 */
	public static List<MEXMEEsqDesLine> getList(Properties ctx, int cbPartnerId, int cbPartnerGpId, int productId, int productCategoryId, String tipoArea) {
		List<MEXMEEsqDesLine> list = new ArrayList<MEXMEEsqDesLine>();

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ");
		sql.append("  esq.* ");
		sql.append("FROM ");
		sql.append("  EXME_EsqDesLine esq ");
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " WHERE ", Table_Name, "esq"));

		List<Object> params = new ArrayList<Object>();

		if (cbPartnerId > 0) {
			sql.append("  AND esq.c_bpartner_id = ? ");
			params.add(cbPartnerId);
		}

		if (cbPartnerGpId > 0) {
			sql.append("  AND esq.c_bp_group_id = ? ");
			params.add(cbPartnerGpId);
		}

		if (productId > 0) {
			sql.append("  AND esq.m_product_id = ? ");
			params.add(productId);
		}

		if (productCategoryId > 0) {
			sql.append("  AND esq.m_product_category_id = ? ");
			params.add(productCategoryId);
		}

		if (StringUtils.isNotBlank(tipoArea)) {
			sql.append("  AND esq.tipoArea = ? ");
			params.add(tipoArea);
		}
		
		sql.append(" ORDER BY esq.validFrom desc, esq.validTo desc ");

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			DB.setParameters(pstmt, params);

			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				list.add(new MEXMEEsqDesLine(ctx, rs, null));
			}
		} catch (Exception ex) {
			slog.log(Level.SEVERE, null, ex);
		} finally {
			DB.close(rs, pstmt);
		}

		return list;
	}

	/**
	 * Define si el convenio tiene algun cargo relacionado
	 * 
	 * @param ctx
	 * @param esqDesLineID
	 * @param trxName
	 * @return
	 */
	public static boolean estaEnUso(final Properties ctx, final int esqDesLineID,
			final String trxName) {

		MCtaPacDet esqDescLinea = null;
		PreparedStatement pstmt = null;
		ResultSet rSet = null;
		
		final StringBuilder sql = new StringBuilder()
		.append(" SELECT * FROM EXME_CtaPacDet WHERE IsActive = 'Y' ")
		.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", "EXME_CtaPacDet"))
		.append(" AND EXME_EsqDesLine_ID = ? ");

		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, esqDesLineID);
			rSet = pstmt.executeQuery();
			
			if (rSet.next()) {
				esqDescLinea = new MCtaPacDet(ctx, rSet, trxName);
			}

		} catch (Exception e) {
			slog.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rSet, pstmt);
		}
		return esqDescLinea != null;
	}
	
	private String partnerName = null;
	private String partnerGroupName = null;
	private String productName = null;
	private String productCategoryName = null;
	private String areaType = null;

	public String getPartnerName() {
		if (StringUtils.isEmpty(partnerName)) {
			if (getC_BPartner_ID() > 0) {
				partnerName = new MBPartner(getCtx(), getC_BPartner_ID(), null).getName();
			} else {
				partnerName = StringUtils.EMPTY;
			}
		}
		return partnerName;
	}
	
	public String getPartnerGroupName() {
		if (StringUtils.isEmpty(partnerGroupName)) {
			if (getC_BP_Group_ID() > 0) {
				partnerGroupName = new MBPGroup(getCtx(), getC_BP_Group_ID(), null).getName();
			} else {
				partnerGroupName = StringUtils.EMPTY;
			}
		}
		return partnerGroupName;
	}
	
	public String getProductName() {
		if (StringUtils.isEmpty(productName)) {
			if (getM_Product_ID() > 0) {
				productName = new MProduct(getCtx(), getM_Product_ID(), null).getName();
			} else {
				productName = StringUtils.EMPTY;
			}
		}
		return productName;
	}
	
	public String getProductCategoryName() {
		if (StringUtils.isEmpty(productCategoryName)) {
			if (getM_Product_Category_ID() > 0) {
				productCategoryName = new MProductCategory(getCtx(), getM_Product_Category_ID(), null).getName();
			} else {
				productCategoryName = StringUtils.EMPTY;
			}
		}
		return productCategoryName;
	}

	public String getAreaType() {
		if (StringUtils.isEmpty(areaType)) {
			if (StringUtils.isNotEmpty(getTipoArea())) {
				areaType = MRefList.getListName(getCtx(), MEXMEEsqDesLine.TIPOAREA_AD_Reference_ID, getTipoArea());
			} else {
				areaType = StringUtils.EMPTY;
			}
		}
		return areaType;
	}
	
	int test = -1;

	public int getTest() {
		if (test == -1) {
			Random rand = new Random();
			test = rand.nextInt(101);
		}
		return test;
	}
	
	private BigDecimal total = Env.ZERO;
	private BigDecimal discount = Env.ZERO;

	public void calculateDiscount(final BigDecimal price){
		// Prioridad porcentaje
		if (getList_Discount() != null
				&& getList_Discount().compareTo(Env.ZERO) > 0) {
			total = price.subtract(price.multiply(getList_Discount()
					.divide(Env.ONEHUNDRED))).setScale(2, BigDecimal.ROUND_HALF_UP);
			discount = price.multiply(getList_Discount().divide(
					Env.ONEHUNDRED)).setScale(2, BigDecimal.ROUND_HALF_UP);
		} else {
			total = getList_AddAmt().setScale(2, BigDecimal.ROUND_HALF_UP);
			discount = Env.ZERO;
		}
	}
	
	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public BigDecimal getDiscount() {
		return discount;
	}

	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}
}
