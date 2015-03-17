package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.KeyNamePair;

public class MEXMEGLCategory extends MGLCategory {
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	private static CLogger s_log = CLogger.getCLogger(MEXMEGLCategory.class);

	/**************************************************************************
	 * 	Standard Constructor
	 *	@param ctx context
	 *	@param GL_Category_ID id
	 *	@param trxName transaction
	 */
	public MEXMEGLCategory (Properties ctx, int GL_Category_ID, String trxName)
	{
		super (ctx, GL_Category_ID, trxName);
		if (GL_Category_ID == 0)
		{
		//	setName (null);
			setCategoryType (CATEGORYTYPE_Manual);
			setIsDefault (false);
		}
	}	//	MGLCategory
	
	/**
	 * Obtener el ID de la Categoria
	 * @return
	 */
	public static int getGLCategory(Properties ctx, String trxName){
		
		int retValue = 0;
		StringBuilder sql = new StringBuilder(100);

		sql.append("SELECT GL_CATEGORY_ID FROM GL_CATEGORY WHERE UPPER(name) = UPPER('Manual') ")
			.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", MGLCategory.Table_Name));
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement (sql.toString(), trxName);
			rs = pstmt.executeQuery ();
			if (rs.next ())
			{
				retValue = rs.getInt(1);
			}
			
		}
		catch (Exception e)
		{
			//s_log.log (Level.SEVERE, sql, e);//FIXME ???
		}
		finally
		{
			DB.close(rs,pstmt);
		}
		return retValue;
	}	//	getDefault
	
	/**
	 * 
	 * @param ctx
	 * @param categoryType
	 * @param trxName
	 * @param policyType
	 * @return
	 */
	public static List<KeyNamePair> getCategories(Properties ctx, String categoryType, String trxName, String... policyType) {
		StringBuilder sql = new StringBuilder();
		sql.append(" select GL_Category_ID, name from GL_Category ");
		sql.append(" where IsActive = 'Y' AND categorytype = ? ");
		
		if (policyType != null && policyType.length > 0) {
			sql.append("AND PolicyType in (");
			sql.append(StringUtils.join(policyType, ","));
			sql.append(") ");
		}
		
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, Constantes.SPACE, MGLCategory.Table_Name));
		sql.append(" ORDER BY ISDEFAULT DESC, name DESC");

		return Query.getListKN(sql.toString(), trxName, categoryType);
	}

	/**
	 * 
	 * @param ctx
	 * @param categoryType
	 * @param trxName
	 * @return
	 */
	static public List<KeyNamePair> getOfCategoryType(Properties ctx, String categoryType, String trxName) {
		return getCategories(ctx, categoryType, trxName);
	}


}
