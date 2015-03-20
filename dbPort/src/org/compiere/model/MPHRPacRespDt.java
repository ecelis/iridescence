package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.minigrid.IDColumn;
import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.GridItem;
import org.compiere.util.Trx;
import org.compiere.util.cuestionarios.Pregunta_VO;

public class MPHRPacRespDt extends X_PHR_PacRespDt implements GridItem{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final CLogger log = CLogger.getCLogger(MPHRPacRespDt.class);

	public MPHRPacRespDt(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	public MPHRPacRespDt(Properties ctx, int PHR_PacRespDt_ID, String trxName) {
		super(ctx, PHR_PacRespDt_ID, trxName);
	}
	
	private IDColumn idColumn = null; 
	
	private MPHRPacCuest pacCuest = null;
	
	
	public String getName(){		
		pacCuest = new MPHRPacCuest(getCtx(), getPHR_PacCuest_ID(), get_TrxName());		
		if(pacCuest.getPHR_PacCuest_ID() > 0){
			return pacCuest.getName();
		}
		return null;
	}
	/**
	 * Guarda las respuestas del cuestionario.
	 * @param ctx
	 * @param map
	 * @param paciente
	 * @param folio
	 * @return
	 */
    public static boolean insertaPacRespDt(Properties ctx, List<Pregunta_VO> map, 
    		Integer paciente, Integer folio){
		log.log(Level.INFO, "***** Insertando en insertaPacRespDt ***** ");
		
		Timestamp fecha = DB.getTimestampForOrg(ctx);
		Trx m_trx = null;
		String trxName = null;

		try {

			m_trx = Trx.get(Trx.createTrxName("PC"), true);
			trxName = m_trx.getTrxName();

			MPHRPacRespDt cResp = null;

			for (Pregunta_VO vo : map) {

				if(vo.getPacRespId() != null){ //update
					cResp = new MPHRPacRespDt(ctx, vo.getPacRespId().intValue(), trxName);
				}else{ //insert
					cResp = new MPHRPacRespDt(ctx, 0, trxName);
				}

				cResp.setFecha(fecha);
				cResp.setEXME_Paciente_ID(paciente.intValue());

				cResp.setDescription("");

				if (vo.getPacCuestId() != null)
					cResp.setPHR_PacCuest_ID(vo.getPacCuestId().intValue());

				cResp.setEXME_Pregunta_ID(vo.getId());
				cResp.setFolio(folio.intValue());
				
				if (vo.getTipoDato().equalsIgnoreCase(MPregunta.TIPODATO_OptionList) && vo.getRespuestaCombo() != null) {
					cResp.setPregunta_Lista_Value(vo.getRespuestaCombo());
					cResp.setRespuesta(vo.getRespuesta());
				}
				

				if (vo.getSecuencia() != null) {
					cResp.setSecuencia(vo.getSecuencia());
				} else {
					cResp.setSecuencia(new Integer("0"));
				}

				if (vo.getComentario() != null) {
					cResp.setDescription(vo.getComentario());
				}
				
				if ((vo.getTipoDato().equalsIgnoreCase(MPregunta.TIPODATO_Image) || vo.getTipoDato().equalsIgnoreCase(MPregunta.TIPODATO_ImageBinary))&& vo.getRespuesta() != null) {
					cResp.setRespuesta(vo.getRespuesta());
					cResp.setRutaImagen(vo.getRutaImagen());
					cResp.setImagenBinary(vo.getImage());
					
					 if (!cResp.save(trxName)) {
							if (trxName != null) {
								if (m_trx != null) {
									m_trx.rollback();
									m_trx.close();
									m_trx = null;
								}
							}
							return false;
						}
					 

				}else if (!vo.getTipoDato().equalsIgnoreCase(MPregunta.TIPODATO_Image) && !vo.getTipoDato().equalsIgnoreCase(MPregunta.TIPODATO_MultiOptions)){
					
					cResp.setRespuesta(vo.getRespuesta());
					
					 if (!cResp.save(trxName)) {
							if (trxName != null) {
								if (m_trx != null) {
									m_trx.rollback();
									m_trx.close();
									m_trx = null;
								}
							}
							return false;
						}

				}
			}
			
				m_trx.commit();		
		
		} catch (Exception e) {
			if (trxName != null) {
				if (m_trx != null) {
					m_trx.rollback();
					m_trx.close();
					m_trx = null;
				}
			}
			log.log(Level.SEVERE, e.getMessage());
			return false;
		} finally {
			if (m_trx != null) {
				m_trx.close();
				m_trx = null;
			}
			trxName = null;
		}
	

		return true;
	}
    
    /**
     * Regresa una lista de los cuestionarios ejecutados por el paciente.
     * @param ctx
     * @param pacienteId
     * @return
     */
    public static List<MPHRPacRespDt> getHistorial(Properties ctx, int pacienteId){
    	
		List<MPHRPacRespDt> lst = new ArrayList<MPHRPacRespDt>();
		StringBuilder sb = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		ResultSet result = null;
		PreparedStatement pstmt = null;
		
		sb.append(" SELECT PHR_PACRESPDT.PHR_PACCUEST_ID, CUEST.NAME, PHR_PACRESPDT.FOLIO, PHR_PACRESPDT.FECHA ")
		.append(" FROM PHR_PACRESPDT ")
		.append(" INNER JOIN PHR_PacCuest CUEST on (PHR_PACRESPDT.PHR_PACCUEST_ID = CUEST.PHR_PACCUEST_ID) ")
		.append(" WHERE PHR_PACRESPDT.EXME_PACIENTE_ID = ? ");
		
		sb = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, sb.toString(),MPHRPacRespDt.Table_Name));
		sb.append(" GROUP BY PHR_PACRESPDT.PHR_PACCUEST_ID, CUEST.NAME, PHR_PACRESPDT.FOLIO, PHR_PACRESPDT.FECHA")
		.append(" ORDER BY  PHR_PACRESPDT.FOLIO, PHR_PACRESPDT.FECHA, CUEST.NAME ASC ");
		try{
			pstmt = DB.prepareStatement(sb.toString(), null);
			pstmt.setInt(1, pacienteId); 
			result = pstmt.executeQuery();
			
			while(result.next()){
				MPHRPacRespDt resp = new MPHRPacRespDt(ctx, result, null);
				lst.add(resp);
			}
		}catch(SQLException ex){
			log.log(Level.SEVERE, "getHistorial " + ex.getMessage());
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
		return lst;
	}
    
    /**
     * Regresa la respuesta guardada de una pregunta del cuestionario ejecutado.
     * @param ctx
     * @param preguntaId
     * @param pacienteId
     * @param pacCuestId
     * @param folio
     * @return
     */
    public static List<MPHRPacRespDt> getRespuesta(Properties ctx, int preguntaId, int pacienteId, int pacCuestId, int folio){
    	List<MPHRPacRespDt> lst = new ArrayList<MPHRPacRespDt>();
    	StringBuilder sb = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		ResultSet result = null;
		PreparedStatement pstmt = null;
    	
		sb.append(" SELECT PHR_PACRESPDT.PHR_PACRESPDT_ID, PHR_PACRESPDT.RESPUESTA, PHR_PACRESPDT.IMAGENBINARY ")
    	.append(" FROM PHR_PACRESPDT ")
    	.append(" WHERE PHR_PACRESPDT.EXME_PREGUNTA_ID = ? ")
    	.append(" AND PHR_PACRESPDT.EXME_PACIENTE_ID = ? ")
    	.append(" AND PHR_PACRESPDT.PHR_PACCUEST_ID = ? ")
    	.append(" AND PHR_PACRESPDT.FOLIO = ? ");
		sb = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, sb.toString(),MPHRPacRespDt.Table_Name));
		
		try{
			pstmt = DB.prepareStatement(sb.toString(), null);
			pstmt.setInt(1, preguntaId); 
			pstmt.setInt(2, pacienteId);
			pstmt.setInt(3, pacCuestId);
			pstmt.setInt(4, folio);
			result = pstmt.executeQuery();
			
			if(result.next()){		
				MPHRPacRespDt resp = new MPHRPacRespDt(ctx, result, null);
				lst.add(resp);
			}
			
		}catch(SQLException ex){
			log.log(Level.SEVERE, "getRespuesta " + ex.getMessage());
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
		return lst;
    }
    
	@Override
	public String[] getColumns() {
		String[] array = {"idColumn", "folio", "name", "fecha"};
		return array;
	}

	@Override
	public IDColumn getIdColumn() {
		if(idColumn==null){
			idColumn = new IDColumn(getPHR_PacCuest_ID());
		}
		return idColumn;
	}
}
