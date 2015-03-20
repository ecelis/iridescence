package org.compiere.model;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionError;
import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.Trx;
import org.compiere.util.mo.MO_PiezaDental_VO;
import org.compiere.util.mo.MO_ProblemaDental_VO;



	public class MEXMEMO_Expediente extends X_EXME_MO_Expediente {

	    /**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		/** Static logger           */
	    private static CLogger s_log = CLogger.getCLogger(MEXMEMO_Expediente.class);

	    
	    /**
	     * @param ctx
	     * @param EXME_CitaMedica_ID
	     * @param trxName
	     */
	    public MEXMEMO_Expediente(Properties ctx, int EXME_MO_ExpedienteID, String trxName) {
	        super(ctx, EXME_MO_ExpedienteID, trxName);
	    }

	    /**
	     * @param ctx
	     * @param rs
	     * @param trxName
	     */
	    public MEXMEMO_Expediente(Properties ctx, ResultSet rs, String trxName) {
	        super(ctx, rs, trxName);
	    }

	    /**
		 * Obtiene si el usuario es infantil o adulto. 
		 *
		 * @param Integer paciente
		 *  
		 * @return Boolean esAdulto
	     * @throws SQLException 
		 * 
		 * @throws Exception
		 */
  public static Boolean getOdontograma(Integer pac) throws SQLException {
		
  	  	Boolean ret = new Boolean(false);
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		 		 
		 sql.append("	select * ")
            .append(" 	from exme_mo_expediente") 
			.append(" 	where exme_mo_expediente.exme_paciente_id = ?") 
			.append(" 	and exme_mo_expediente.esadulto='Y'");
		 
  		
  		
  	PreparedStatement pstmt = null;
  	ResultSet rs = null;
  	
  	
  		    	
		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setInt(1, pac.intValue());
		
						rs = pstmt.executeQuery();
			
			if(rs.next()) { 
				ret = new Boolean(true);
		    				  
			}
			
		} catch (Exception e) {
			s_log.log(Level.SEVERE, "getCitasMedico", e);
		}finally{				
			DB.close(rs, pstmt);
		}
		return ret;
  }
  
  /**
	 * Obtiene la informacion de cada diente del expediente dental. 
	 *
	 * @param Integer pacienteId 
	 * @param Integer dienteID
	 *  
	 * @return MO_PiezaDental_VO objetdo_informacion_dental
 * @throws SQLException 
	 * 
	 * @throws Exception
	 */
  public static MO_PiezaDental_VO getExpedieteDiente(Integer pac, Integer diente) throws SQLException {
		
	  	
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		
	 sql.append("  select ")
	 	.append(" 	EXME_MO_EXPEDIENTE.EXme_mo_expediente_id, EXME_MO_EXPEDIENTE.EXme_paciente_id, EXME_MO_EXPEDIENTE.EXme_mo_piezadental_id,EXME_MO_EXPEDIENTE.Sangrado,")
	 	.append(" 	EXME_MO_EXPEDIENTE.Ausenciadiente,EXME_MO_EXPEDIENTE.ESadulto,EXME_MO_EXPEDIENTE.Placa,")
	 	.append(" 	EXME_MO_EXPEDIENTE.Created")
	 	.append(" 	FROM")
		.append("  		(select ")
		.append("	 	EXME_MO_EXPEDIENTE.EXme_mo_expediente_id, EXME_MO_EXPEDIENTE.EXme_paciente_id, EXME_MO_EXPEDIENTE.EXme_mo_piezadental_id,EXME_MO_EXPEDIENTE.Sangrado,")
		.append(" 		EXME_MO_EXPEDIENTE.Ausenciadiente,EXME_MO_EXPEDIENTE.ESadulto,EXME_MO_EXPEDIENTE.Placa,")
		.append(" 		EXME_MO_EXPEDIENTE.Created")
		.append(" 		FROM EXME_MO_EXPEDIENTE")
		.append(" 		WHERE EXME_MO_EXPEDIENTE.Exme_paciente_id= ?")
		.append(" 		AND EXME_MO_EXPEDIENTE.Exme_mo_piezadental_id= ?")
		.append(" 		ORDER BY EXME_MO_EXPEDIENTE.Created desc )");
	 
	 if (DB.isOracle()) {
		 sql.append(" 	where rownum = 1");
	 } else if (DB.isPostgreSQL()) {
		 sql.append(" AS consulta").append(StringUtils.EMPTY);
		 sql = new StringBuilder(DB.getDatabase().addPagingSQL(sql.toString(), 1, 1));
	 }
		
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	MO_PiezaDental_VO pieza = null;
		    	
		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setInt(1, pac.intValue());
			pstmt.setInt(2, diente.intValue());
		
						rs = pstmt.executeQuery();
			
			if(rs.next()) { 
				pieza = new MO_PiezaDental_VO();
				pieza.setExpediente(new Integer(rs.getInt(1)));
				pieza.setPacId(new Integer(rs.getInt(2)));
				pieza.setDienteId(new Integer(rs.getInt(3)));
		    	pieza.setSangrado(rs.getString(4));
		    	pieza.setAusente(rs.getString(5));
		    	pieza.setDeAdulto(rs.getString(6));
		    	pieza.setPlaca(rs.getString(7));
		    	pieza.setCreado(rs.getTimestamp(8));
			}
			
		} catch (Exception e) {
			s_log.log(Level.SEVERE, "getExpedieteDiente - sql: " + sql, e);
		}finally{				
			DB.close(rs, pstmt);
		}
		return pieza;
}

  /**
	 * Guarda la informacion del expediente dental . 
	 *
	 * @param Properties contexto 
	 * @param ArrayList Dientes
	 *  
	 * @return Boolean SeguardoCorrectamente
	 * 
	 * @throws Exception
	 */
  public static Boolean guardarExpedienteDental(Properties ctx,ArrayList<MO_PiezaDental_VO> dientes) {
		
	  
		Trx m_trx = null;
        m_trx = Trx.get(Trx.createTrxName("CECI"), true);
        String trxName = m_trx.getTrxName();
        boolean ejecutar = false;
        MEXMEMO_Expediente diente = null;
        MO_PiezaDental_VO aux = null;
        try {
        	
        	for(int i = 0; i < dientes.size(); i++){
        		
	        	aux = (MO_PiezaDental_VO)dientes.get(i);
	        	       	
	        	diente = new MEXMEMO_Expediente(ctx, 0, trxName);
			
	        	diente.setEXME_Paciente_ID(aux.getPacId().intValue());
	        	diente.setEXME_MO_PiezaDental_ID(aux.getDienteId().intValue());
	        	//diente.setSangrado(aux.getIsSangrado().booleanValue());
	        	diente.setAusenciaDiente(aux.getIsAusente().booleanValue());
	        	diente.setEsAdulto(aux.getIsAdulto().booleanValue());
	        	diente.setPlaca(aux.getIsPlaca().booleanValue());
	        	diente.setEsOdontograma(true);
	        	ejecutar = diente.save(trxName);
	        	
	        	
        	
        	}      	
        	
        	
        } catch (Exception e) {
			
			s_log.log(Level.SEVERE, "-- saveNew : ", e);
			
		} finally { 
                if(m_trx!=null){
                    m_trx.close();
                    m_trx=null;
                }
                trxName=null;
		}
				
	
		return new Boolean(ejecutar);
}
 
  public static ActionError guardarExpedienteDentalView(Properties ctx,ArrayList<MO_PiezaDental_VO> dientes, Boolean odonto) {
		
	  
	  Trx m_trx = null;
      m_trx = Trx.get(Trx.createTrxName("CECI"), true);
      String trxName = m_trx.getTrxName();
 	 ActionError error = null;

      MEXMEMO_Expediente exp = null;
      X_EXME_MO_ExpDentalProblems expP = null;
      MO_PiezaDental_VO aux = null;
      try {
      	
      	for(int i = 0; i < dientes.size(); i++){      		
      		  boolean ejecutarC1 = false;
      	      boolean ejecutarC2 = false;
      	      boolean ejecutarC3 = false;
      	      boolean ejecutarC4 = false;
      	      boolean ejecutarC5 = false;
      	      boolean ejecutarC6 = false;
      	      boolean ejecutarC7 = false;
      	      boolean ejecutarC8 = false;
      	      boolean ejecutarC9 = false;
      	    
      	      boolean ejecutarPI = false;    	      
      	      boolean ejecutarTabla = false;
      		
	        	aux = (MO_PiezaDental_VO)dientes.get(i);
	        	       	
	        	exp = new MEXMEMO_Expediente(ctx, 0, trxName);
	        	
	        	
	        	exp.setEXME_Paciente_ID(aux.getPacId().intValue());
	        	exp.setEXME_MO_PiezaDental_ID(aux.getDienteId().intValue());
	        	exp.setEsAdulto(aux.getDeAdulto() != null && aux.getDeAdulto().equalsIgnoreCase("N") ? false : true);
	        	exp.setEsOdontograma(odonto.booleanValue());

	        	if(aux.getCara1() != null && aux.getCara1().intValue() > 0){
	        		ejecutarC1 = true;
	        		
	        	}
	        	if(aux.getCara2() != null && aux.getCara2().intValue() > 0){
	        		ejecutarC2 = true;
	        		
	        	}
	        	if(aux.getCara3() != null && aux.getCara3().intValue() > 0){
	        		ejecutarC3 = true;
	        		
	        	}
	        	if(aux.getCara4() != null && aux.getCara4().intValue() > 0){
	        		ejecutarC4 = true;
	        		
	        	}
	        	if(aux.getCara5() != null && aux.getCara5().intValue() > 0){
	        		ejecutarC5 = true;
	        		
	        	}
	        	if(aux.getCara6() != null && aux.getCara6().intValue() > 0){
	        		ejecutarC6 = true;
	        		
	        	}
	        	if(aux.getCara7() != null && aux.getCara7().intValue() > 0){
	        		ejecutarC7 = true;
	        		
	        	}
	        	if(aux.getCara8() != null && aux.getCara8().intValue() > 0){
	        		ejecutarC8 = true;
	        		
	        	}
	        	if(aux.getCara9() != null && aux.getCara9().intValue() > 0){
	        		ejecutarC9 = true;
	        		
	        	}
	        	if((aux.getProbImgAct() != null && aux.getProbImgAct().intValue() > 0)){
	        		ejecutarPI = true;
	        		
	        	}
	        	
	        	if(aux.getTablaDienteC3Act() != null && aux.getTablaDienteC3Act().intValue() > 0){
	        		ejecutarTabla = true;
	        		
	        	}
	        	
	        	if(ejecutarC1 || ejecutarC2 || ejecutarC3 || ejecutarC4 || ejecutarC5 || ejecutarC6 || ejecutarC7 || ejecutarC8 || ejecutarC9 || ejecutarPI || ejecutarTabla){
	        		
	        		if(!exp.save(trxName)){
	        				        			
						throw new SQLException("error.ts.noguardado");
						
						
	        		}else{
	        			
	                      if(ejecutarC1){
	                    	  	  expP = new X_EXME_MO_ExpDentalProblems(ctx, 0, trxName);
		                    	  expP.setCara(new BigDecimal(1));
		                    	  expP.setEXME_MO_DentalProblems_ID(aux.getCara1().intValue());
		                    	  expP.setEXME_MO_Expediente_ID(exp.getEXME_MO_Expediente_ID());
		                    	  if(!expP.save(trxName)){		      	        			
		      	        			
		      						throw new SQLException("error.ts.noguardado");
		      						
		      	        		}
		                    	  aux.setCara1(new Integer(0));
	                      }
	                      
	                      if(ejecutarC2){
                    	  	  expP = new X_EXME_MO_ExpDentalProblems(ctx, 0, trxName);
	                    	  expP.setCara(new BigDecimal(2));
	                    	  expP.setEXME_MO_DentalProblems_ID(aux.getCara2().intValue());
	                    	  expP.setEXME_MO_Expediente_ID(exp.getEXME_MO_Expediente_ID());
	                    	  if(!expP.save(trxName)){		      	        			
	      	        			
	      						throw new SQLException("error.ts.noguardado");
	      						
	      	        		}
	                    	  aux.setCara2(new Integer(0));
                      }
	                      
	                      if(ejecutarC3){
                    	  	  expP = new X_EXME_MO_ExpDentalProblems(ctx, 0, trxName);
	                    	  expP.setCara(new BigDecimal(3));
	                    	  expP.setEXME_MO_DentalProblems_ID(aux.getCara3().intValue());
	                    	  expP.setEXME_MO_Expediente_ID(exp.getEXME_MO_Expediente_ID());
	                    	  if(!expP.save(trxName)){		      	        			
	      	        			
	      						throw new SQLException("error.ts.noguardado");
	      						
	      	        		}
	                    	  aux.setCara3(new Integer(0));
                      }
	                      
	                      if(ejecutarC4){
                    	  	  expP = new X_EXME_MO_ExpDentalProblems(ctx, 0, trxName);
	                    	  expP.setCara(new BigDecimal(4));
	                    	  expP.setEXME_MO_DentalProblems_ID(aux.getCara4().intValue());
	                    	  expP.setEXME_MO_Expediente_ID(exp.getEXME_MO_Expediente_ID());
	                    	  if(!expP.save(trxName)){		      	        			
	      	        			
	      						throw new SQLException("error.ts.noguardado");
	      						
	      	        		}
	                    	  aux.setCara4(new Integer(0));
                      }
	                      
	                      if(ejecutarC5){
                    	  	  expP = new X_EXME_MO_ExpDentalProblems(ctx, 0, trxName);
	                    	  expP.setCara(new BigDecimal(5));
	                    	  expP.setEXME_MO_DentalProblems_ID(aux.getCara5().intValue());
	                    	  expP.setEXME_MO_Expediente_ID(exp.getEXME_MO_Expediente_ID());
	                    	  if(!expP.save(trxName)){		      	        			
	      	        			
	      						throw new SQLException("error.ts.noguardado");
	      						
	      	        		}
	                    	  aux.setCara5(new Integer(0));
                      }
	                      if(ejecutarC6){
                    	  	  expP = new X_EXME_MO_ExpDentalProblems(ctx, 0, trxName);
	                    	  expP.setCara(new BigDecimal(6));
	                    	  expP.setEXME_MO_DentalProblems_ID(aux.getCara6().intValue());
	                    	  expP.setEXME_MO_Expediente_ID(exp.getEXME_MO_Expediente_ID());
	                    	  if(!expP.save(trxName)){		      	        			
	      	        			
	      						throw new SQLException("error.ts.noguardado");
	      						
	      	        		}
	                    	  aux.setCara6(new Integer(0));
                      }
	                      if(ejecutarC7){
                	  	  expP = new X_EXME_MO_ExpDentalProblems(ctx, 0, trxName);
                    	  expP.setCara(new BigDecimal(7));
                    	  expP.setEXME_MO_DentalProblems_ID(aux.getCara7().intValue());
                    	  expP.setEXME_MO_Expediente_ID(exp.getEXME_MO_Expediente_ID());
                    	  if(!expP.save(trxName)){		      	        			
      	        			
      						throw new SQLException("error.ts.noguardado");
      						
      	        		}
                    	  aux.setCara7(new Integer(0));
                  }   
	                      if(ejecutarC8){
                    	  	  expP = new X_EXME_MO_ExpDentalProblems(ctx, 0, trxName);
	                    	  expP.setCara(new BigDecimal(8));
	                    	  expP.setEXME_MO_DentalProblems_ID(aux.getCara8().intValue());
	                    	  expP.setEXME_MO_Expediente_ID(exp.getEXME_MO_Expediente_ID());
	                    	  if(!expP.save(trxName)){		      	        			
	      	        			
	      						throw new SQLException("error.ts.noguardado");
	      						
	      	        		}
	                    	  aux.setCara8(new Integer(0));
                      }
	                      if(ejecutarC9){
                    	  	  expP = new X_EXME_MO_ExpDentalProblems(ctx, 0, trxName);
	                    	  expP.setCara(new BigDecimal(9));
	                    	  expP.setEXME_MO_DentalProblems_ID(aux.getCara9().intValue());
	                    	  expP.setEXME_MO_Expediente_ID(exp.getEXME_MO_Expediente_ID());
	                    	  if(!expP.save(trxName)){		      	        			
	      	        			
	      						throw new SQLException("error.ts.noguardado");
	      						
	      	        		}
	                    	  aux.setCara9(new Integer(0));
                      }
	                      if(ejecutarPI){
                    	  	  expP = new X_EXME_MO_ExpDentalProblems(ctx, 0, trxName);
	                    	  expP.setEXME_MO_DentalProblems_ID(aux.getProbImgAct().intValue());
	                    	  expP.setEXME_MO_Expediente_ID(exp.getEXME_MO_Expediente_ID());
	                    	  if(!expP.save(trxName)){		      	        			
	      	        			
	      						throw new SQLException("error.ts.noguardado");
	      						
	      	        		}
	                    	  aux.setProbImgAct(new Integer(0));
                      }
	                      
	                      if(ejecutarTabla){
                    	  	  expP = new X_EXME_MO_ExpDentalProblems(ctx, 0, trxName);
	                    	  expP.setEXME_MO_DentalProblems_ID(aux.getTablaDienteC3Act().intValue());
	                    	  expP.setEXME_MO_Expediente_ID(exp.getEXME_MO_Expediente_ID());
	                    	  if(!expP.save(trxName)){		      	        			
	      	        			
	      						throw new SQLException("error.ts.noguardado");
	      						
	      	        		}
	                    	  aux.setProbImgAct(new Integer(0));
                      }
	                      
	                     
	                      m_trx.commit();
	                      
	        		}
	        		
	        	}
	        		
	        		
	        	
	        	
	        	
	        	
      	
      	}      	
      	
      	
      } catch (Exception e) {	
    	  error =  new ActionError("error.ts.noguardado");
    	  	if(m_trx != null){
				m_trx.rollback();			
			}
    	  	
			s_log.log(Level.SEVERE, "-- saveNew : ", e);
								
			
		} finally {               
                  m_trx.close();
                  m_trx=null;
             
		}
				
	
		return error;
}

public static ActionError guardarExpedienteDentalViewV2(Properties ctx,ArrayList<MO_PiezaDental_VO> dientes, Boolean odonto) {
		
	  
	  Trx m_trx = null;
      m_trx = Trx.get(Trx.createTrxName("CECI"), true);
      String trxName = m_trx.getTrxName();
 	 ActionError error = null;

      MEXMEMO_Expediente exp = null;
      X_EXME_MO_ExpDentalProblems expP = null;
      MO_PiezaDental_VO aux = null;
      try {
      	
      	for(int i = 0; i < dientes.size(); i++){      		
      		  boolean ejecutarC1 = false;
      	      boolean ejecutarC2 = false;
      	      boolean ejecutarC3 = false;
      	      boolean ejecutarC4 = false;
      	      boolean ejecutarC5 = false;
      	     
      	      boolean ejecutarPI = false;    	      
      	      boolean ejecutarTabla = false;
      		
	        	aux = (MO_PiezaDental_VO)dientes.get(i);
	        	       	
	        	exp = new MEXMEMO_Expediente(ctx, 0, trxName);
	        	
	        	
	        	exp.setEXME_Paciente_ID(aux.getPacId().intValue());
	        	exp.setEXME_MO_PiezaDental_ID(aux.getDienteId().intValue());
	        	exp.setEsAdulto(aux.getDeAdulto() != null && aux.getDeAdulto().equalsIgnoreCase("N") ? false : true);
	        	exp.setEsOdontograma(odonto.booleanValue());

	        	if(aux.getCara1() != null && aux.getCara1().intValue() != MEXMEMO_DentalProblems.getProblemByID(aux.getPacId(), aux.getDienteId(), new Integer(1),odonto.booleanValue() ? "Y" : "N")){
	        		ejecutarC1 = true;
	        		
	        	}
	        	if(aux.getCara2() != null && aux.getCara2().intValue() != MEXMEMO_DentalProblems.getProblemByID(aux.getPacId(), aux.getDienteId(), new Integer(2),odonto.booleanValue() ? "Y" : "N")){
	        		ejecutarC2 = true;
	        		
	        	}
	        	if(aux.getCara3() != null && aux.getCara3().intValue() != MEXMEMO_DentalProblems.getProblemByID(aux.getPacId(), aux.getDienteId(), new Integer(3),odonto.booleanValue() ? "Y" : "N")){
	        		ejecutarC3 = true;
	        		
	        	}
	        	if(aux.getCara4() != null && aux.getCara4().intValue() != MEXMEMO_DentalProblems.getProblemByID(aux.getPacId(), aux.getDienteId(), new Integer(4),odonto.booleanValue() ? "Y" : "N")){
	        		ejecutarC4 = true;
	        		
	        	}
	        	if(aux.getCara5() != null && aux.getCara5().intValue() != MEXMEMO_DentalProblems.getProblemByID(aux.getPacId(), aux.getDienteId(), new Integer(5),odonto.booleanValue() ? "Y" : "N")){
	        		ejecutarC5 = true;
	        		
	        	}
	        	
	        	if(aux.getProbImgAct() != null && aux.getProbImgAct().intValue() != MEXMEMO_DentalProblems.getProblem_IDByImgV2(aux.getPacId(), aux.getDienteId(),"Y","N","N")){
	        		ejecutarPI = true;
	        		
	        	}
	        	
	        	if(aux.getTablaDienteC3Act() != null && aux.getTablaDienteC3Act().intValue() != MEXMEMO_DentalProblems.getProblemID_ByCaraC3(aux.getPacId(), aux.getDienteId(),"Y")){
	        		ejecutarTabla = true;
	        		
	        	}
	        	
	        	if(ejecutarC1 || ejecutarC2 || ejecutarC3 || ejecutarC4 || ejecutarC5 || ejecutarPI || ejecutarTabla){
	        		
	        		if(!exp.save(trxName)){
	        				        			
						throw new SQLException("error.ts.noguardado");
						
						
	        		}else{
	        			
	                      if(ejecutarC1){
	                    	  	  expP = new X_EXME_MO_ExpDentalProblems(ctx, 0, trxName);
		                    	  expP.setCara(new BigDecimal(1));
		                    	  expP.setEXME_MO_DentalProblems_ID(aux.getCara1().intValue());
		                    	  expP.setEXME_MO_Expediente_ID(exp.getEXME_MO_Expediente_ID());
		                    	  if(!expP.save(trxName)){		      	        			
		      	        			
		      						throw new SQLException("error.ts.noguardado");
		      						
		      	        		}
		                    	  //aux.setCara1(new Integer(0));
	                      }
	                      
	                      if(ejecutarC2){
                    	  	  expP = new X_EXME_MO_ExpDentalProblems(ctx, 0, trxName);
	                    	  expP.setCara(new BigDecimal(2));
	                    	  expP.setEXME_MO_DentalProblems_ID(aux.getCara2().intValue());
	                    	  expP.setEXME_MO_Expediente_ID(exp.getEXME_MO_Expediente_ID());
	                    	  if(!expP.save(trxName)){		      	        			
	      	        			
	      						throw new SQLException("error.ts.noguardado");
	      						
	      	        		}
	                    	 //aux.setCara2(new Integer(0));
                      }
	                      
	                      if(ejecutarC3){
                    	  	  expP = new X_EXME_MO_ExpDentalProblems(ctx, 0, trxName);
	                    	  expP.setCara(new BigDecimal(3));
	                    	  expP.setEXME_MO_DentalProblems_ID(aux.getCara3().intValue());
	                    	  expP.setEXME_MO_Expediente_ID(exp.getEXME_MO_Expediente_ID());
	                    	  if(!expP.save(trxName)){		      	        			
	      	        			
	      						throw new SQLException("error.ts.noguardado");
	      						
	      	        		}
	                    	  //aux.setCara3(new Integer(0));
                      }
	                      
	                      if(ejecutarC4){
                    	  	  expP = new X_EXME_MO_ExpDentalProblems(ctx, 0, trxName);
	                    	  expP.setCara(new BigDecimal(4));
	                    	  expP.setEXME_MO_DentalProblems_ID(aux.getCara4().intValue());
	                    	  expP.setEXME_MO_Expediente_ID(exp.getEXME_MO_Expediente_ID());
	                    	  if(!expP.save(trxName)){		      	        			
	      	        			
	      						throw new SQLException("error.ts.noguardado");
	      						
	      	        		}
	                    	  //aux.setCara4(new Integer(0));
                      }
	                      
	                      if(ejecutarC5){
                    	  	  expP = new X_EXME_MO_ExpDentalProblems(ctx, 0, trxName);
	                    	  expP.setCara(new BigDecimal(5));
	                    	  expP.setEXME_MO_DentalProblems_ID(aux.getCara5().intValue());
	                    	  expP.setEXME_MO_Expediente_ID(exp.getEXME_MO_Expediente_ID());
	                    	  if(!expP.save(trxName)){		      	        			
	      	        			
	      						throw new SQLException("error.ts.noguardado");
	      						
	      	        		}
	                    	  //aux.setCara5(new Integer(0));
                      }
	                     
	                      if(ejecutarPI){
                    	  	  expP = new X_EXME_MO_ExpDentalProblems(ctx, 0, trxName);
	                    	  expP.setEXME_MO_DentalProblems_ID(aux.getProbImgAct().intValue());
	                    	  expP.setEXME_MO_Expediente_ID(exp.getEXME_MO_Expediente_ID());
	                    	  if(!expP.save(trxName)){		      	        			
	      	        			
	      						throw new SQLException("error.ts.noguardado");
	      						
	      	        		}
	                    	  //aux.setProbImgAct(new Integer(0));
                      }
	                      
	                      if(ejecutarTabla){
                    	  	  expP = new X_EXME_MO_ExpDentalProblems(ctx, 0, trxName);
	                    	  expP.setEXME_MO_DentalProblems_ID(aux.getTablaDienteC3Act().intValue());
	                    	  expP.setEXME_MO_Expediente_ID(exp.getEXME_MO_Expediente_ID());
	                    	  if(!expP.save(trxName)){		      	        			
	      	        			
	      						throw new SQLException("error.ts.noguardado");
	      						
	      	        		}
	                    	  //aux.setProbImgAct(new Integer(0));
                      }
	                      
	                     
	                      m_trx.commit();
	                      
	        		}
	        		
	        	}
	        		
	        		
	        	
	        	
	        	
	        	
      	
      	}      	
      	
      	
      } catch (Exception e) {	
    	  error =  new ActionError("error.ts.noguardado");
    	  	if(m_trx != null){
				m_trx.rollback();			
			}
    	  	
			s_log.log(Level.SEVERE, "-- saveNew : ", e);
								
			
		} finally {               
                  m_trx.close();
                  m_trx=null;
             
		}
				
	
		return error;
}
  
  
public static ActionError guardarExpedienteDentalViewTabla(Properties ctx,ArrayList<MO_PiezaDental_VO> dientes, Boolean odonto, String vestibular, String tabla) {
		
	  
	  Trx m_trx = null;
      m_trx = Trx.get(Trx.createTrxName("CECI"), true);
      String trxName = m_trx.getTrxName();
 	 ActionError error = null;

      MEXMEMO_Expediente exp = null;
      X_EXME_MO_ExpDentalProblems expP = null;
      MO_PiezaDental_VO aux = null;
      MO_ProblemaDental_VO prob = null;
      try {
      	
      	for(int i = 0; i < dientes.size(); i++){      		
      		  
	        	aux = (MO_PiezaDental_VO)dientes.get(i);
	        	
	        	for(int j=0; j < aux.getProblemas().size(); j++){
		        		prob = (MO_ProblemaDental_VO)aux.getProblemas().get(j);
			        	if(prob.getIsAdded().booleanValue() != MEXMEMO_Expediente.getExpedietePerioTabla(aux.getPacId(), aux.getDienteId(), prob.getProblemaId().intValue(),vestibular,tabla).booleanValue()){
			        		
			        		exp = new MEXMEMO_Expediente(ctx, 0, trxName);
				        	exp.setEXME_Paciente_ID(aux.getPacId().intValue());
				        	exp.setEXME_MO_PiezaDental_ID(aux.getDienteId().intValue());
				        	exp.setEsAdulto(aux.getDeAdulto() != null && aux.getDeAdulto().equalsIgnoreCase("N") ? false : true);
				        	exp.setEsOdontograma(odonto.booleanValue());

				        		if(!exp.save(trxName)){
				        				        			
									throw new SQLException("error.ts.noguardado");
									
				        		}else{  
				                    	  	  expP = new X_EXME_MO_ExpDentalProblems(ctx, 0, trxName);
					                    	  expP.setEXME_MO_DentalProblems_ID(prob.getProblemaId().intValue());
					                    	  expP.setEXME_MO_Expediente_ID(exp.getEXME_MO_Expediente_ID());
					                    	  expP.setValor(prob.getIsAdded().toString());
					                    	  if(vestibular != null && vestibular.equalsIgnoreCase("Y")){
					                    		  expP.setVestibular(true);
					                    	  }else{
					                    		  expP.setVestibular(false);  
					                    	  }
					                    	  if(tabla != null && tabla.equalsIgnoreCase("Y")){
					                    		  expP.setFromTable(true);
					                    	  }else{
					                    		  expP.setFromTable(false);  
					                    	  }
					                    	  if(!expP.save(trxName)){		      	        			
					      	        			
					      						throw new SQLException("error.ts.noguardado");
					      						
					      	        		}
				                                           
				                    
				                      
				                     
				                      m_trx.commit();
				                      
				        		}
			        		        		
			        	  }	
	        		
	        	}
      	}      	
      	
      	
      } catch (Exception e) {	
    	  error =  new ActionError("error.ts.noguardado");
    	  	if(m_trx != null){
				m_trx.rollback();			
			}
    	  	
			s_log.log(Level.SEVERE, "-- saveNew : ", e);
			
		} finally {               
                  m_trx.close();
                  m_trx=null;
             
		}
				
	
		return error;
}
  
  /**
	 * Guarda la informacion del expediente dental del periodontograma. 
	 *
	 * @param Properties contexto 
	 * @param ArrayList Dientes
	 *  
	 * @return Boolean SeguardoCorrectamente
	 * 
	 * @throws Exception
	 */
  public static Boolean guardarExpedientePerio(Properties ctx,ArrayList<MO_PiezaDental_VO> dientes) {
		
	 
		Trx m_trx = null;
        m_trx = Trx.get(Trx.createTrxName("CECI"), true);
        String trxName = m_trx.getTrxName();
        boolean ejecutar = false;
        MEXMEMO_Expediente diente = null;
        MO_PiezaDental_VO aux = null;
        try {
        	
        	for(int i = 0; i < dientes.size(); i++){
        		
	        	aux = (MO_PiezaDental_VO)dientes.get(i);
	        	       	
	        	diente = new MEXMEMO_Expediente(ctx, 0, trxName);
			
	        	diente.setEXME_Paciente_ID(aux.getPacId().intValue());
	        	diente.setEXME_MO_PiezaDental_ID(aux.getDienteId().intValue());
	        	
	        	diente.setEsAdulto(aux.getIsAdulto().booleanValue());
	        	diente.setPlaca(aux.getIsPlaca().booleanValue());
	        	diente.setEsOdontograma(false);
	        	diente.setEsSupuracion(aux.getIsSupuracion().booleanValue());
	        	diente.setEsCalculo(aux.getIsCalculo().booleanValue());
	        	diente.setSangrado(aux.getIsSangrado().booleanValue());
	        	
	        	ejecutar = diente.save(trxName);
	        	
	        	
        	
        	}      	
        	
        	
        } catch (Exception e) {
			
			s_log.log(Level.SEVERE, "-- saveNew : ", e);
								
			
		} finally { 
                if(m_trx!=null){
                    m_trx.close();
                    m_trx=null;
                }
                trxName=null;
		}
				
	
		return new Boolean(ejecutar);
}
  
  
  
  public static Boolean getExpedietePerioTabla(Integer pac, Integer diente, Integer prob, String vestibular, String tabla) throws SQLException {
		
	  Boolean ret = new Boolean(false);
	  	
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
	 
  sql.append(" 	select expp.valor")
	 .append(" 	from exme_mo_expediente expe")
	 .append(" 	inner join exme_mo_expdentalproblems expp on (expe.exme_mo_expediente_id = expp.exme_mo_expediente_id)")
	 .append(" 	inner join exme_mo_dentalproblems prob on (expp.exme_mo_dentalproblems_id = prob.exme_mo_dentalproblems_id)")
	 .append(" 	where expe.exme_paciente_id = ?")
	 .append(" 	and expe.exme_mo_piezadental_id = ?")
	 .append(" 	AND prob.exme_mo_dentalproblems_id = ?")
	 .append(" 	and expe.esodontograma = 'N'")
	 .append(" 	and expp.vestibular = ?")
	 .append(" 	 and expp.fromtable = ?")
	 .append(" 	order by expp.created desc");
	 
		
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setInt(1, pac.intValue());
			pstmt.setInt(2, diente.intValue());
			pstmt.setInt(3, prob.intValue());
			pstmt.setString(4, vestibular);
			pstmt.setString(5, tabla);
		
						rs = pstmt.executeQuery();
			
			if(rs.next()) { 
				String valor = rs.getString(1);
				if(valor != null){
					if(valor.equals("true")){
						ret = new Boolean("true");
					}else{
						ret = new Boolean("false");
					}
				}else{
				    ret = new Boolean("false");
				}
			}
			
		} catch (Exception e) {
			s_log.log(Level.SEVERE, "getCitasMedico", e);
		}finally{				
			DB.close(rs, pstmt);
		}
		return ret;
}

  
}
