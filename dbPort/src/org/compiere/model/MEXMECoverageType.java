package org.compiere.model;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.struts.util.LabelValueBean;

public class MEXMECoverageType extends X_EXME_CoverageType{
	private static final long serialVersionUID = 1L;
	public MEXMECoverageType(Properties ctx, int EXME_CoverageType_ID, String trxName) {
		super(ctx, EXME_CoverageType_ID, trxName);
	}
	public MEXMECoverageType(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}
	
	public static List<LabelValueBean> getBeanList(Properties ctx){
		List<LabelValueBean> beans = new ArrayList<LabelValueBean>();	
		List<MEXMECoverageType> list = new Query(ctx, Table_Name, "", null)
		.setOnlyActiveRecords(true)
		.list();		
		for(MEXMECoverageType coverageType : list){
			beans.add(new LabelValueBean(coverageType.getName(), coverageType.getValue()));
		}
		return beans;
	}
}

