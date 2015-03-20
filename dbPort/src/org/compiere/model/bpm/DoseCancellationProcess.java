package org.compiere.model.bpm;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.compiere.model.MOrg;
import org.compiere.model.MPlanMedLine;
import org.compiere.model.X_EXME_Mejoras;
import org.compiere.model.X_EXME_PlanMedLine;
import org.compiere.util.Constantes;
import org.compiere.util.DB;

import com.ecaresoft.api.Generic;

/**
 * AND pmd.EXME_CtaPac_ID = 1000077
AND pmd.AD_Org_ID = 1001206
AND cta.EXME_Estserv_id =1000507
 * @author Expert
 *
 */
public class DoseCancellationProcess {
	
	private Properties ctx = null;
	private List<MPlanMedLine> lstAll = null;
	private List<MPlanMedLine> lstTrue = null;
	private List<MPlanMedLine> lstFalse = null;
	
	public DoseCancellationProcess(final Properties pCtx){
		ctx = pCtx;
		modif = new StringBuilder();
		lstTrue = new ArrayList<MPlanMedLine>();
		lstFalse = new ArrayList<MPlanMedLine>();
	}
	
	/**
	 * Uno
	 * @return
	 */
	private String cancelPorEstacion(){
		StringBuilder sql = new StringBuilder(" AND cta.EXME_Estserv_id = ? ");
		return sql.toString();
	}
	
	/**
	 * dos
	 * @return
	 */
	private String cancelPorCuenta(){
		StringBuilder sql = new StringBuilder(" AND pmd.EXME_CtaPac_ID = ? ");
		return sql.toString();
	}
	
	/**
	 * tres
	 * @return
	 */
	private String cancelPorOrganizacion(){
		StringBuilder sql = new StringBuilder(" AND pmd.AD_Org_ID = ? ");
		return sql.toString();
	}
	
	private String consulSql(int opcion, Generic generic){
		
		Timestamp ts = DB.getTimestampFor(generic.getValue());
		String date = Constantes.getSdfFechaHoraBD24().format(ts);
		
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT pml.* ") 
		.append(" FROM EXME_PlanMed pmd ")
		.append(" INNER JOIN EXME_CtaPac      cta ")
		.append("    ON pmd.EXME_CtaPac_ID = cta.EXME_CtaPac_ID ")
		.append(" INNER JOIN EXME_PrescRxDet  prd ")
		.append("    ON pmd.EXME_PrescRxDet_ID = prd.EXME_PrescRxDet_ID ")
		.append(" INNER JOIN EXME_Frequency2  fq2 ")
		.append("    ON prd.EXME_Frequency2_ID = fq2.EXME_Frequency2_ID ")
		.append("       AND fq2.PRN = 'Y' ")
		.append(" INNER JOIN EXME_PlanMedLine pml ")
		.append("    ON pmd.EXME_PlanMed_ID = pml.EXME_PlanMed_ID ")
		.append("       AND pml.Estatus =  'PR'  ")
		.append("  LEFT JOIN EXME_Mejoras mej ")
		.append("    ON pmd.AD_Org_ID = mej.AD_Org_ID ")
		.append(" WHERE pmd.isactive = 'Y'  ")
		.append(" AND pmd.AD_Org_ID = ").append(generic.getId());

		if(opcion==1) {
			sql.append(cancelPorEstacion()); 
		} else {
			if(opcion==2) {
				sql.append(cancelPorCuenta()); 
			} else {
				if(opcion==3) {
					sql.append(cancelPorOrganizacion()); 
				}		
			}
		}

		sql.append(" AND progdate + ( mej.").append(X_EXME_Mejoras.COLUMNNAME_Tolerancia).append("/24) <= to_date('").append(date).append("', 'yyyy-MM-dd HH24:mi') ");
		
		return sql.toString();
	}
	
	private StringBuilder modif;
	
	public void cencel(int opcion, List<Integer> params, String trxName){
		lstAll = new ArrayList<MPlanMedLine>();
		
		List<Generic> generics = MOrg.getParentOrgTimeZones();
		
		for (Generic generic : generics) {
			List<MPlanMedLine> lst = MPlanMedLine.getPlanMedLine(ctx, consulSql(opcion, generic), params, trxName);
			for (int i = 0; i < lst.size(); i++) {
				lst.get(i).setEstatus(X_EXME_PlanMedLine.ESTATUS_AutoCancel);
				if (!lst.get(i).save()) {
					modif.append(",").append(lst.get(i).getEXME_PlanMedLine_ID());
					lstFalse.add(lst.get(i));
				} else {
					lstTrue.add(lst.get(i));
				}
			}

			lstAll.addAll(lst);
		}
	}

	public List<MPlanMedLine> getLstAll() {
		return this.lstAll;
	}

	public void setLstAll(List<MPlanMedLine> lst) {
		this.lstAll = lst;
	}
	
	public StringBuilder getModif() {
		return this.modif;
	}

	public void setModif(StringBuilder modif) {
		this.modif = modif;
	}
	
	public List<MPlanMedLine> getLstTrue() {
		return this.lstTrue;
	}

	public void setLstTrue(List<MPlanMedLine> lstTrue) {
		this.lstTrue = lstTrue;
	}

	public List<MPlanMedLine> getLstFalse() {
		return this.lstFalse;
	}

	public void setLstFalse(List<MPlanMedLine> lstFalse) {
		this.lstFalse = lstFalse;
	}
}
