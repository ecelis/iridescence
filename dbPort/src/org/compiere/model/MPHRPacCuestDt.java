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
import org.compiere.util.cuestionarios.Pregunta_VO;
import org.compiere.util.cuestionarios.RespuestaList_VO;

public class MPHRPacCuestDt extends X_PHR_PacCuestDt {
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * CLogger
	 */
	private static CLogger log = CLogger.getCLogger(MPHRPacCuestDt.class);
	
	
	public MPHRPacCuestDt(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	public MPHRPacCuestDt(Properties ctx, int PHR_PacCuestDt_ID,
			String trxName) {
		super(ctx, PHR_PacCuestDt_ID, trxName);
	}
	
	public static MPHRPacCuestDt[] getDetail(Properties ctx, int exmePacCuestId){
		List<MPHRPacCuestDt> lst = new ArrayList<MPHRPacCuestDt>();
		StringBuilder sb = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		ResultSet result = null;
		PreparedStatement pstmt = null;
		
		sb.append("SELECT * FROM PHR_PacCuestDt WHERE PHR_PacCuest_ID = ? ");
		sb = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, sb.toString(),MPHRPacCuestDt.Table_Name));
		sb.append(" ORDER BY Secuencia ASC ");
		try{
			pstmt = DB.prepareStatement(sb.toString(), null);
			pstmt.setInt(1, exmePacCuestId); 
			result = pstmt.executeQuery();
			
			while(result.next()){
				MPHRPacCuestDt dt = new MPHRPacCuestDt(ctx, result, null);
				lst.add(dt);
			}
		}catch(SQLException ex){
			log.log(Level.SEVERE, "MPHRPacCuestDt at method getDetail " + ex.getMessage());
		} finally{
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
		MPHRPacCuestDt[] det = new MPHRPacCuestDt[lst.size()];
		lst.toArray(det);
    	return det;
	}
	/**
	 * Devuelve la lista de las preguntaas del cuestionario.
	 * @param ctx
	 * @param exmePacCuestId
	 * @return
	 */
	public static List<Pregunta_VO> getPreguntasCuestionario(Properties ctx, int exmePacCuestId, boolean conRespuesta, int pacienteId, int folio){
		List<Pregunta_VO> lst = new ArrayList<Pregunta_VO>();
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		PreparedStatement pstmt = null;
        ResultSet result = null;

		sql.append("SELECT ")
		.append("dt.EXME_Pregunta_ID, ")//1
		.append("p.Name, ")//2
		.append("p.TipoDato, ")//3
		.append("p.EXME_TipoPregunta_ID, ")//4
		.append("tp.Name, ")//5
		.append("dt.PHR_PacCuest_ID, ")//6
		.append("dt.Secuencia, ")//7
		.append("rl.value, ")//8
		.append("rl.name, ")//9
		.append("rl.EXME_Pregunta_Lista_ID, ")//10
		.append("dt.PHR_PacCuestDt_id ")//11
		.append("from PHR_PacCuestDt dt ")
		.append("inner join EXME_Pregunta p on (dt.EXME_Pregunta_ID = p.EXME_Pregunta_ID) ")
		.append("inner join EXME_TipoPregunta tp on (p.EXME_TipoPregunta_ID=tp.EXME_TipoPregunta_ID) ")
		.append("left join  EXME_Pregunta_lista rl on (p.EXME_Pregunta_ID = rl.EXME_Pregunta_ID) ")
		.append("where dt.PHR_PacCuest_ID = ? ")
		.append("ORDER BY dt.Secuencia, tp.Name,rl.EXME_Pregunta_Lista_ID ");
		
		try{
			 pstmt = DB.prepareStatement(sql.toString(), null);
	         pstmt.setInt(1, exmePacCuestId);
	         result = pstmt.executeQuery();
	         int exmePacCuestDtId = 0; 
	         Pregunta_VO preg = null;
	         RespuestaList_VO resps = null;
	         
	         while(result.next()){

	        	 if(preg==null || exmePacCuestDtId != result.getInt(11)){
	        		 preg = new Pregunta_VO();            	 

	        		 preg.setId(new Integer(result.getInt(1)));
	        		 preg.setPregId(new Integer(result.getInt(1)));
	        		 preg.setName(result.getString(2));
	        		 preg.setTipoDato(result.getString(3));
	        		 preg.setTipoPregunta(new Integer(result.getInt(4)));
	        		 preg.setTipoPreguntaTxt(result.getString(5));
	        		 preg.setPacCuestId(result.getInt(6));
	        		 preg.setSecuencia(new Integer(result.getInt(7)));

	        		 if((preg.getTipoDato().equalsIgnoreCase(MPregunta.TIPODATO_OptionList) || 
	        				 preg.getTipoDato().equalsIgnoreCase(MPregunta.TIPODATO_MultiOptions)) && 
	        				 result.getString(8) != null){
	        			 preg.setRespuestas(new ArrayList<RespuestaList_VO>());
	        			 resps = new RespuestaList_VO();            	 
	        			 resps.setPreguntaId(new Integer(result.getInt(1)));
	        			 resps.setValorTxt(result.getString(8));
	        			 resps.setDescripcion(result.getString(9));
	        			 resps.setListaID(new Integer(result.getInt(11)));

	        			 preg.getRespuestas().add(resps);
	        		 }
	        		 lst.add(preg);
	        	 } else{
	        		 if(preg.getId().intValue() == result.getInt(1)){	        			 
		        		 if((preg.getTipoDato().equalsIgnoreCase(MPregunta.TIPODATO_OptionList) || 
		        				 preg.getTipoDato().equalsIgnoreCase(MPregunta.TIPODATO_MultiOptions)) && 
		        				 result.getString(8) != null){
		        			 if(preg.getRespuestas() != null  && preg.getRespuestas().size()<1){
		        				 preg.setRespuestas(new ArrayList<RespuestaList_VO>());
		        			 }
		        			 resps = new RespuestaList_VO();            	 
		        			 resps.setPreguntaId(new Integer(result.getInt(1)));
		        			 resps.setValorTxt(result.getString(8));
		        			 resps.setDescripcion(result.getString(9));
		        			 resps.setListaID(new Integer(result.getInt(11)));

		        			 preg.getRespuestas().add(resps);
		        		 }
		        	 }
	        	 }
	        	 
	        	 if(conRespuesta){
	        		 List<MPHRPacRespDt> list = new ArrayList<MPHRPacRespDt>();
	        		 list.addAll(MPHRPacRespDt.getRespuesta(ctx, preg.getPregId().intValue(), pacienteId, exmePacCuestId, folio));
	        		 if(list.size()>0){
	        			 MPHRPacRespDt r = list.get(0);
	        			 preg.setRespuesta(r.getRespuesta());
	        			 preg.setImage(r.getImagenBinary());
	        			 preg.setPacRespId(r.getPHR_PacRespDt_ID());
	        		 }
	        	 }
	        	 
	        	 exmePacCuestDtId = result.getInt(11);
	         }
	         
		}catch(SQLException e){
			log.log(Level.SEVERE, "getPreguntasCuestionario: " +e.getMessage());
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
}
