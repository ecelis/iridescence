package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.util.LabelValueBean;
import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;

public class MEXMEPlanMedico extends X_EXME_PlanMedico {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** Standard Constructor
	@param ctx context
	@param EXME_PlanMedico_ID id
	@param trxName transaction
	*/
	public MEXMEPlanMedico (Properties ctx, int EXME_PlanMedico_ID, String trxName)
	{
	super (ctx, EXME_PlanMedico_ID, trxName);
	/** if (EXME_PlanMedico_ID == 0)
	{
	setEXME_PlanMedico_ID (0);
	setValue (null);
	}
	 */
	}
	/** Load Constructor 
	@param ctx context
	@param rs result set 
	@param trxName transaction
	*/
	public MEXMEPlanMedico (Properties ctx, ResultSet rs, String trxName)
	{
	super (ctx, rs, trxName);
	}
	

	private static CLogger s_log = CLogger.getCLogger(MEXMEPlanMedico.class);
	
	/**
	 * 
	 * @param ctx
	 * @param ctaPacID
	 * @param trxName
	 * @return
	 */
	public static List<LabelValueBean> getIds(Properties ctx, String trxName)
	throws Exception {
		
		List<LabelValueBean>  retValue = new ArrayList<LabelValueBean>();
		
		LabelValueBean procPac = null;
		
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		try {
			sql.append("SELECT ").append(Table_Name).append("_ID, Name FROM ")
			.append(Table_Name)
			.append(" WHERE IsActive = 'Y' ")
			.append(MEXMELookupInfo.addAccessLevelSQL(ctx," ",Table_Name));
			
			psmt = DB.prepareStatement(sql.toString(), trxName);
			rs = psmt.executeQuery();
			
			while (rs.next()) {
				procPac = new LabelValueBean(rs.getString(2), String.valueOf(rs.getInt(1)));
				retValue.add(procPac);
			}
			
		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
			throw new Exception(e.getMessage());
		} finally {
			DB.close(rs, psmt);
		}
		return retValue;
	}

	public static String getCadena(List<MEXMEPlanMedico> lista){
		return StringUtils.join(lista, ",");
		}
	@Override
	public String toString() {
		return getName();
	}//No modificar GADC
	
}
