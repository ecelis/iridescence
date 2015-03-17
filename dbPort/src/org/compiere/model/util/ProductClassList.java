/**
 * 
 */
package org.compiere.model.util;

import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.compiere.model.MRefList;
import org.compiere.model.X_M_Product;
import org.compiere.util.ValueNamePair;

/**
 * Grupos de product class segun el tipo de producto
 * 
 * @author Lorena Lama
 */
public enum ProductClassList {

		MEDICATION(
			X_M_Product.PRODUCTCLASS_Drug,
			X_M_Product.PRODUCTCLASS_Immunization),
		PROCEDURE(
			X_M_Product.PRODUCTCLASS_Procedures),
		SERVICE(
			X_M_Product.PRODUCTCLASS_HomeHealt,
			X_M_Product.PRODUCTCLASS_Ambulance,
			X_M_Product.PRODUCTCLASS_Blood,
			X_M_Product.PRODUCTCLASS_Hospice,
			X_M_Product.PRODUCTCLASS_Laboratory,
			X_M_Product.PRODUCTCLASS_XRay,
			X_M_Product.PRODUCTCLASS_Procedures,
			X_M_Product.PRODUCTCLASS_FQHCRHC,
			X_M_Product.PRODUCTCLASS_OtherService),
		SERVICE_NIMBO(
			X_M_Product.PRODUCTCLASS_HomeHealt,
			X_M_Product.PRODUCTCLASS_Ambulance,
			X_M_Product.PRODUCTCLASS_Blood,
			X_M_Product.PRODUCTCLASS_Laboratory,
			X_M_Product.PRODUCTCLASS_XRay,
			X_M_Product.PRODUCTCLASS_Procedures,
			X_M_Product.PRODUCTCLASS_Cultures,
			X_M_Product.PRODUCTCLASS_Surgeries,
			X_M_Product.PRODUCTCLASS_Anesthesic,
			X_M_Product.PRODUCTCLASS_PhysicianServices,
			X_M_Product.PRODUCTCLASS_Others,
			X_M_Product.PRODUCTCLASS_OtherService),
		STUDY(
			X_M_Product.PRODUCTCLASS_XRay,
			X_M_Product.PRODUCTCLASS_Laboratory);

	private List<String>	list;
	private String[]		array;
	private String			whereClause;

	private ProductClassList(final String... prodClass) {
		this.array = prodClass;
	}

	/**
	 * @param ctx
	 * @return lista de prodcut class traducida
	 */
	public List<ValueNamePair> getList(final Properties ctx) {
		return MRefList.getList(ctx, X_M_Product.PRODUCTCLASS_AD_Reference_ID, false, getWhereClause());
	}

	/**
	 * @return lista de product class
	 */
	public List<String> getProdClassList() {
		if (list == null) {
			this.list = Arrays.asList(array);
		}
		return list;
	}

	public String getWhereClause() {
		if (whereClause == null) {
			final StringBuilder where = new StringBuilder();
			where.append(" AND l.Value IN ('").append(StringUtils.join(array, "','")).append("') ");
			this.whereClause = where.toString();
		}
		return whereClause;
	}
}
