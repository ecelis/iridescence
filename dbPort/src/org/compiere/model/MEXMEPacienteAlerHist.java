package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.minigrid.IDColumn;
import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.GridItem;

public class MEXMEPacienteAlerHist extends X_EXME_PacienteAler_Hist implements GridItem{
	private static final long serialVersionUID = 1L;
	private static CLogger s_log = CLogger.getCLogger (MEXMEPacienteAlerHist.class);
	private IDColumn idColumn = null; 
	private String severidadDesc = null;
	private String estatusDesc = null;
	private String userName = null;

	public MEXMEPacienteAlerHist(Properties ctx, int EXME_PacienteAler_Hist_ID,
			String trxName) {
		super(ctx, EXME_PacienteAler_Hist_ID, trxName);
	}
	
	public MEXMEPacienteAlerHist(Properties ctx, ResultSet rs,
			String trxName) {
		super(ctx, rs, trxName);
	}
	
	public String[] getColumns() {
		
		return new String[]{"updated", "userName", "severidadDesc", "estatusDesc", "reaccion"};
	}

	public IDColumn getIdColumn() {
		if(idColumn==null){
			idColumn = new IDColumn(getEXME_PacienteAler_ID());
		}
		return idColumn;
	}
	
	
	public String getSeveridadDesc() {
		return severidadDesc;
	}

	public void setSeveridadDesc(String severidadDesc) {
		this.severidadDesc = severidadDesc;
	}

	public String getEstatusDesc() {
		return estatusDesc;
	}

	public void setEstatusDesc(String estatusDesc) {
		this.estatusDesc = estatusDesc;
	}

	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 *  Regresa el historico de alergias
	 *
	 *@param  ctx    		 Contexto
	 *@param  tipoAlergia    Tipo de alergia
	 *@param  ID       		 ID de la alergia o de la sustancia activa
	 *@param  paciente       Description of the Parameter
	 *@param  notEmptyList   Si es true, cuando no hay historial regresa el registro orginal.
	 *@return                Un valor boolean indicando si tiene o no alergia a
	 *      alguna sustancia del producto.
	 *@exception  Exception  Description of the Exception
	 */
	public static List<MEXMEPacienteAlerHist> getHistoria(Properties ctx, int ID, boolean notEmptyList) {
		List<MEXMEPacienteAlerHist> retValue = new ArrayList<MEXMEPacienteAlerHist>();
        if(ID <= 0){ //Lama
        	return retValue;
        }
        
        StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
        PreparedStatement pstmt = null;
    	ResultSet rs = null;
    	
		sql.append(" SELECT PA.* ")
		.append(" FROM EXME_PacienteAler_Hist PA ")
		.append(" WHERE EXME_PacienteAler_ID = ? ")
		.append(" ORDER BY PA.CREATED DESC ");
		
		
		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setInt(1, ID);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				MEXMEPacienteAlerHist obj = new MEXMEPacienteAlerHist(ctx, rs, null);
				if(obj.getSeveridad()!=null){
					obj.setSeveridadDesc(MRefList.getListName(ctx,MEXMEPacienteAler.SEVERIDAD_AD_Reference_ID, obj.getSeveridad().trim()));
				}
				if(obj.getEstatus()!=null){
					obj.setEstatusDesc(MRefList.getListName(ctx,MEXMEPacienteAler.ESTATUS_AD_Reference_ID, obj.getEstatus().trim()));
				}
				MEXMEUser user = new MEXMEUser(ctx, obj.getUpdatedBy(), null);
				obj.setUserName(user.getName());
				retValue.add(obj);
			} 
			
			// Lama
			if (retValue.isEmpty() && notEmptyList) {
				MEXMEPacienteAler from = new MEXMEPacienteAler(ctx, ID, null);
				MEXMEPacienteAlerHist to = new MEXMEPacienteAlerHist(from.getCtx(), 0, null);
				copyValues(from, to);
				to.setUpdatedBy(from.getUpdatedBy());
				
				if(to.getSeveridad()!=null){
					to.setSeveridadDesc(MRefList.getListName(ctx,MEXMEPacienteAler.SEVERIDAD_AD_Reference_ID, to.getSeveridad().trim()));
				}
				if(to.getEstatus()!=null){
					to.setEstatusDesc(MRefList.getListName(ctx,MEXMEPacienteAler.ESTATUS_AD_Reference_ID, to.getEstatus().trim()));
				}
				MEXMEUser user = new MEXMEUser(ctx, to.getUpdatedBy(), null);
				to.setUserName(user.getName());
				retValue.add(to);
			}
               
		} catch (Exception e) {
    		s_log.log(Level.SEVERE, sql.toString(), e.getMessage());
    	}finally {
    		DB.close(rs, pstmt);
    	}        
        return retValue;
	}
}
