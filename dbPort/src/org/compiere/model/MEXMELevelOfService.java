/**
 * 
 */
package org.compiere.model;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.compiere.util.ValueNamePair;

/**
 * @author pedro
 *
 */
public class MEXMELevelOfService extends X_EXME_Level_Of_Service {

	private static final long	serialVersionUID	= 1L;

	/**
	 * @param ctx
	 * @param EXME_Level_of_Service_ID
	 * @param trxName
	 */
	public MEXMELevelOfService(Properties ctx, int EXME_Level_of_Service_ID, String trxName) {
		super(ctx, EXME_Level_of_Service_ID, trxName);
	}

	/**
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MEXMELevelOfService(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}
	
	public static List<MEXMELevelOfService>  getAllForPatientType(Properties ctx, String patientType){	
		return  new Query(ctx,Table_Name,String.format("isActive = 'Y' AND Patient_Type = '%s'", patientType),null).setOrderBy("name").list();
	}
	
	private static void forceRename(Properties ctx){
		List<MEXMELevelOfService> lvls = new Query(ctx,Table_Name,null,null).list();
		for(MEXMELevelOfService lvl : lvls){
			lvl.setName(lvl.getEXME_Intervencion().getValue() + "-" + lvl.getEXME_Intervencion().getName());
		}
	}
	
	@Override
	protected boolean beforeSave(boolean newRecord) {
		setName(getEXME_Intervencion().getValue() + "-" + getEXME_Intervencion().getName());
		return true;
	}

}
