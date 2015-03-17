package org.compiere.model;

import java.util.List;
import java.util.Properties;

import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;

/** @deprecated use {@link MAttributeSetInstance} instead */
public class MEXMEAttributeSetInstance extends MAttributeSetInstance {

	private static final long	serialVersionUID	= 1L;
	
	@SuppressWarnings("unused")
	private static CLogger		slog				= CLogger.getCLogger(MEXMEAttributeSetInstance.class);

//	public MEXMEAttributeSetInstance(Properties ctx, int M_AttributeSetInstance_ID, int M_AttributeSet_ID,
//		String trxName) {
//		super(ctx, M_AttributeSetInstance_ID, M_AttributeSet_ID, trxName);
//	}

	public MEXMEAttributeSetInstance(Properties ctx, int M_AttributeSetInstance_ID, String trxName) {
		super(ctx, M_AttributeSetInstance_ID, trxName);
	}

//	public MEXMEAttributeSetInstance(Properties ctx, ResultSet rs, String trxName) {
//		super(ctx, rs, trxName);
//	}

	/** @deprecated solo se usa en clase EntradasAlmAction */
	public static boolean existLot(Properties ctx, String name, String trxName) {
		return new Query(ctx, Table_Name, " Lot=?", trxName)//
			.setParameters(name)//
			.addAccessLevelSQL(true)//
			.firstId() > 0;
	}

	/** @deprecated solo se usa en clase EntradasAlmAction */
	public static MEXMEAttributeSetInstance getFromLotName(Properties ctx, String loteName, String trxName) {
		return new Query(ctx, Table_Name, " Lot=?", trxName)//
			.setParameters(loteName)//
			.addAccessLevelSQL(true)//
			.first();
	}

	/** @deprecated Validar si se usa, no hay referencias */
	public static List<MAttributeSetInstance> getFromProduct(Properties ctx, int M_Product_ID, String trxName) {

		final StringBuilder joins = new StringBuilder();
		joins.append(" INNER JOIN m_attributeset aset ");
		joins.append(" ON ( ");
		joins.append("  m_attributesetinstance.m_attributeset_id = aset.m_attributeset_id ");
		joins.append("  AND aset.isactive                        = 'Y' ");
		joins.append("  AND aset.islot                           = 'Y' ");
		joins.append(" )");
		joins.append(" INNER JOIN exme_productoorg po ");
		joins.append(" ON ( ");
		joins.append("  aset.m_attributeset_id = po.m_attributeset_ID ");
		joins.append("  AND po.islot           = 'Y' ");
		joins.append("  AND po.isactive        = 'Y' ");
		joins.append("  AND po.m_product_id    = ? ");// #1
		joins.append(" )");

		final StringBuilder where = new StringBuilder();
		where.append(" m_attributesetinstance.ad_org_id = ? ");// #2
		where.append(" AND ( ");
		where.append("   m_attributesetinstance.datefrom IS NULL ");
		if (DB.isOracle()) {
			where.append("   OR TRUNC(m_attributesetinstance.datefrom,'DD') <= TRUNC("+ DB.TO_DATE(DB.getTimestampForOrg(Env.getCtx())) + ",'DD')");
		} else if (DB.isPostgreSQL()) {
			where.append("   OR DATE_TRUNC('day', m_attributesetinstance.datefrom) <= DATE_TRUNC('day',"+ DB.TO_DATE(DB.getTimestampForOrg(Env.getCtx())) + ")");
		}
		where.append(" )");
		where.append(" AND ( ");
		where.append("   m_attributesetinstance.dateto IS NULL ");
		if (DB.isOracle()) {
			where.append("   OR TRUNC(m_attributesetinstance.dateto ,'DD') >= TRUNC("+ DB.TO_DATE(DB.getTimestampForOrg(Env.getCtx())) + ",'DD')");
		} else if (DB.isPostgreSQL()) {
			where.append("   OR DATE_TRUNC('day', m_attributesetinstance.dateto) >= DATE_TRUNC('day',"+ DB.TO_DATE(DB.getTimestampForOrg(Env.getCtx())) + ")");
		}
		where.append(" )");

		return new Query(ctx, Table_Name, where.toString(), trxName)//
			.setJoins(joins)//
			.setParameters(M_Product_ID, Env.getAD_Org_ID(ctx))//
			.setOnlyActiveRecords(true)//
			.addAccessLevelSQL(true)//
			.list();
	}

}
