package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Msg;


/**
 * Modelo para parametros de medidas de calidad clinica.
 * @author rosy velazquez
 */
public class MEXMEParamMetricas extends X_EXME_Param_Metricas{

	private static final long serialVersionUID = -3434410060710898718L;
	/** Static Logger               */
    private static CLogger      log = CLogger.getCLogger (MEXMEParamMetricas.class);

	public MEXMEParamMetricas(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);		
	}

	public MEXMEParamMetricas(Properties ctx, int EXME_Param_Metricas_ID,
			String trxName) {
		super(ctx, EXME_Param_Metricas_ID, trxName);		
	}

	/**
     *  Before Save
     *  @param newRecord new
     *  @return true
     */
    protected boolean beforeSave(boolean newRecord) {
    	
    	if(newRecord){
    		MEXMEParamMetricas param = getParamMetricas(getCtx(), get_TrxName());
    		if(param != null){
    			log.saveError("Error", Msg.getMsg(getCtx(), "ErrorParamMetricas"));
    			return false;
    		}
    	}
    	
    	//Validar rangos de fechas
    	if(getAge_BreastFrom() > getAge_BreastTo()){
    		log.saveError("ErrorRangoEdad", Msg.getElement(getCtx(), "Age_BreastFrom"));
			return false;
    	}
    	
    	if(getAge_ColorectalFrom() > getAge_ColorectalTo()){
    		log.saveError("ErrorRangoEdad", Msg.getElement(getCtx(), "Age_ColorectalFrom"));
			return false;
    	}
    	
    	if(getAge_Weight() > getAge_WeightTo()){
    		log.saveError("ErrorRangoEdad", Msg.getElement(getCtx(), "Age_Weight"));
			return false;	
    	}
    	    	
    	
    	//Validar Fechas Finales
    	//Segunda pestaña
    	/*
    	if(this.getFecha_Fin_Urgencia().before(this.getFecha_Ini_Urgencia())){
    		log.saveError("error.dateIniFin", Msg.getElement(getCtx(), "Fecha_Fin_Urgencia"));
			return false;	
    	}
    	
    	if(this.getFecha_Fin_Urgencia_ED().before(this.getFecha_Ini_Urgencia_ED())){
    		log.saveError("error.dateIniFin", Msg.getElement(getCtx(), "Fecha_Fin_Urgencia_ED"));
			return false;	
    	}
    	
    	if(this.getFecha_Fin_Urgencia_S2().before(this.getFecha_Ini_Urgencia_S2())){
    		log.saveError("error.dateIniFin", Msg.getElement(getCtx(), "Fecha_Fin_Urgencia_S2"));
			return false;	
    	}
    	
    	if(this.getFecha_Fin_Urgencia_S3().before(this.getFecha_Ini_Urgencia_S3())){
    		log.saveError("error.dateIniFin", Msg.getElement(getCtx(), "Fecha_Fin_Urgencia_S3"));
			return false;	
    	}
    	*/
    	
    	return true;
    }
	
    /**
     * Obtenemos el registro de parametros para las metricas
     * @param ctx El contexto de la aplicacion    
     * @param trxName el nombre de la transaccion 
     * @return parametros de metricas 
     */
    public static MEXMEParamMetricas getParamMetricas(Properties ctx, String trxName) {
		
    	MEXMEParamMetricas param = null;
		String sql = "SELECT * FROM EXME_Param_Metricas WHERE IsActive = 'Y' ";
        
        sql = MEXMELookupInfo.addAccessLevelSQL(ctx, sql, "EXME_Param_Metricas");
        
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
			pstmt = DB.prepareStatement(sql, trxName);						
			rs = pstmt.executeQuery();

			if (rs.next()) {
				param = new MEXMEParamMetricas(ctx, rs, trxName);
			}

		} catch (Exception e) {
			log.log(Level.SEVERE, sql, e.getMessage());
		} finally {
			DB.close(rs, pstmt);
		}        
        return param;
    }
}
