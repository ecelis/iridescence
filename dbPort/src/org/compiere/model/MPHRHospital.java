package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;

public class MPHRHospital extends X_PHR_Hospital {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static CLogger log = CLogger.getCLogger(MPHRHospital.class);
	public MPHRHospital(Properties ctx, int PHR_Hospital_ID, String trxName) {
		super(ctx, PHR_Hospital_ID, trxName);
	}
	
	public MPHRHospital(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}
	
	
	public static List<PHRHospitalView> getAllHospitals(Properties ctx, int pacienteId){
		List<PHRHospitalView> lst = new ArrayList<PHRHospitalView>();
		StringBuilder sb = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		PreparedStatement pstmt = null;
		ResultSet result = null;
		
		sb.append("SELECT PH.PHR_HOSPITAL_ID, PH.NOMBRE, PH.TELEFONO, PH.FAX, LOC.ADDRESS1, LOC.ADDRESS2, ")
		  .append("LOC.POSTAL, CO.C_COUNTRY_ID, REG.C_REGION_ID, TC.EXME_TOWNCOUNCIL_ID, PH.ESPRINCIPAL ")
		  .append("FROM PHR_HOSPITAL PH ")
		  .append("LEFT JOIN C_LOCATION LOC ON (PH.C_LOCATION_ID = LOC.C_LOCATION_ID) ")
		  .append("LEFT JOIN C_COUNTRY CO ON (LOC.C_COUNTRY_ID = CO.C_COUNTRY_ID) ")
		  .append("LEFT JOIN C_REGION REG ON (LOC.C_REGION_ID = REG.C_REGION_ID) ")
		  .append("LEFT JOIN EXME_TOWNCOUNCIL TC ON (LOC.EXME_TOWNCOUNCIL_ID = TC.EXME_TOWNCOUNCIL_ID) ")
		  .append("WHERE PH.EXME_PACIENTE_ID = ? ");
		
		try{
			pstmt = DB.prepareStatement(sb.toString(), null);
			pstmt.setInt(1, pacienteId); 
			result = pstmt.executeQuery();
			
			while(result.next()){
				PHRHospitalView hosp = new PHRHospitalView();
				hosp.setPHR_Hospital_ID(result.getInt(1));
				hosp.setNombre(result.getString(2));
				hosp.setTelefono(result.getString(3));
				hosp.setFax(result.getString(4));
				hosp.setAddress1(result.getString(5));
				hosp.setAddress2(result.getString(6));
				hosp.setPostal(result.getString(7));
				hosp.setC_Country_ID(result.getInt(8));
				hosp.setC_Region_ID(result.getInt(9));
				hosp.setEXME_TownCouncil_ID(result.getInt(10));
				hosp.setPrincipal(result.getString(11).equalsIgnoreCase("Y")?true:false);
				lst.add(hosp);
			}
		}catch(SQLException e){
			log.log(Level.SEVERE, "getAllHospitals(Properties ctx, int pacienteId) " + e.getMessage());
		}finally{
			try{
				if(pstmt != null)
					pstmt.close();
				
				if(result != null)
					result.close();
				
				pstmt = null;
				result = null;
			}catch(SQLException e){
				log.log(Level.SEVERE, "While closing pstmt and result " + e.getMessage());
			}
		}
		return lst;
	}
	
	/**
	 * El objeto MLocation del contacto
	 * o null si no tiene C_Location_ID
	 * @return
	 */
	public MLocation getC_Location(){
		MLocation location = null;
		if(getC_Location_ID() > 0){
			location = new MLocation(getCtx(),getC_Location_ID(), get_TrxName());
		} 
		return location;
	}
	
	@Override
	protected boolean afterSave(boolean newRecord, boolean success) {
		boolean retValue = true;
		
		//Solo puede existir un registro marcado como principal
		if(isEsPrincipal() && success){
			StringBuilder sql = new StringBuilder();
				sql.append(" UPDATE PHR_HOSPITAL SET ESPRINCIPAL = 'N' ")
						.append(" WHERE PHR_HOSPITAL_ID <> ? AND ESPRINCIPAL = 'Y' ")
						.append(" AND EXME_PACIENTE_ID = ? ");
				
				Object[] params = {getPHR_Hospital_ID(), getEXME_Paciente_ID()};
				DB.executeUpdateEx(sql.toString(), params, get_TrxName());
		}
		return retValue;
	}
	
	@Override
	protected boolean afterDelete(boolean success) {
		//Borrar la direccion 
		if(success){
			if(getC_Location() != null){
				return getC_Location().delete(false, get_TrxName());
			}
		}
		return true;
	}
	
}
