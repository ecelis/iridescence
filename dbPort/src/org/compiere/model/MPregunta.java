/*
 * Created on 09-may-2005
 */
package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.util.LabelValueBean;
import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.cuestionarios.Pregunta_VO;
import org.compiere.util.cuestionarios.Regla_VO;
import org.compiere.util.cuestionarios.RespuestaList_VO;


/**
 * Modelo de Pregunta
 * <b>Modificado: </b> $Author: gisela $<p>
 * <b>En :</b> $Date: 2006/08/19 19:36:26 $<p>
 *
 * @author Gisela Lee
 * @version $Revision: 1.4 $
 */
public class MPregunta extends X_EXME_Pregunta {

    /**	Static Logger				*/
	private static CLogger		logger = CLogger.getCLogger (MPregunta.class);

	private final static String SELECTED = "selected=\"selected\"";
	
	
    /**
     * @param ctx
     * @param EXME_Pregunta_ID
     * @param trxName
     */
    public MPregunta(Properties ctx, int EXME_Pregunta_ID, String trxName) {
        super(ctx, EXME_Pregunta_ID, trxName);
    }

    /**
     * @param ctx
     * @param rs
     * @param trxName
     */
    public MPregunta(Properties ctx, ResultSet rs, String trxName) {
        super(ctx, rs, trxName);
    }


    public MPregunta(Properties ctx, X_I_EXME_Pregunta pregunta, String trxName) {
		super(ctx, 0, trxName);
		
		setDescription(pregunta.getPregunta_Description());
		setEXME_Especialidad_ID(pregunta.getEXME_Especialidad_ID());
		setEXME_TipoPregunta_ID(pregunta.getEXME_TipoPregunta_ID());
		setName(pregunta.getPregunta_Name());
		setTipoDato(pregunta.getPregunta_TipoDato());
		setValue(pregunta.getPregunta_Value());
	}


	/**
     * Obtenemos la lista de valores para una pregunta tipo combo
     * @param ctx El contexto de la aplicacion
     * @param EXME_Pregunta_ID La habitacion para obtener las camas
     * @param trxName El nombre de la transaccion
     * @return
     */
	public static MPregunta_Lista[] getListaDeValores(Properties ctx, int EXME_Pregunta_ID,
			String trxName) {
		
		List<MPregunta_Lista> list = null;

		StringBuilder sql = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MPregunta_Lista pregLista = null;
		MPregunta_Lista[] pregListaRes = null;

		if(ctx != null) {

			sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
			
			try {

				sql.append(" SELECT EXME_Pregunta_Lista_ID FROM EXME_Pregunta_Lista ")
				.append(" WHERE IsActive = 'Y' AND EXME_Pregunta_ID = ? ")
				.append(MEXMELookupInfo.addAccessLevelSQL(ctx, "  ", MPregunta_Lista.Table_Name))
				.append(" ORDER BY Name");

				pstmt = DB.prepareStatement(sql.toString(), trxName);
				pstmt.setInt(1, EXME_Pregunta_ID);
				rs = pstmt.executeQuery();

				list = new ArrayList<MPregunta_Lista>();
				
				while (rs.next()) {
					pregLista = new MPregunta_Lista(ctx, rs.getInt(1), trxName);
					list.add(pregLista);
				}

			} catch (Exception e) {
				logger.log(Level.SEVERE, "getListaDeValores - sql = " + sql, e);
			} finally {
				try {
					if (pstmt != null) {
						pstmt.close();
					}

					if (rs != null) {
						rs.close();
					}
				} catch (SQLException exClose) {
					logger.log(Level.SEVERE, "getListaDeValores - while closing rs and pstmt objects = ", exClose);
				}
			}

			//
			pregListaRes = new MPregunta_Lista[list.size()];
			list.toArray(pregListaRes);
		}
		
		return pregListaRes;
    }

    public MTipoPregunta getTipoPregunta() {

    	MTipoPregunta retVal = null;
    	
        if(getEXME_TipoPregunta_ID() > 0) {
            retVal = 
            	new MTipoPregunta(getCtx(), getEXME_TipoPregunta_ID(), get_TrxName());
        }

        return retVal;
    }

    /**
     *  Obtenemos una lista con las preguntas por tipo de pregunta
     *  @return La lista con las preguntas
     */
    public static List<LabelValueBean> getPreguntasXTipo (Properties ctx, long tipoPregunta) throws Exception{
        //lista con las preguntas
        List<LabelValueBean> lstPreguntas = new ArrayList<LabelValueBean>();
        
        StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
        //buscamos las preguntas
        /*String sql = " SELECT EXME_Pregunta.EXME_Pregunta_ID, EXME_Pregunta.Name "
              + " FROM EXME_Pregunta, EXME_TipoPregunta "
              + " WHERE EXME_Pregunta.EXME_TipoPregunta_ID = EXME_TipoPregunta.EXME_TipoPregunta_ID "
              + " AND EXME_TipoPregunta.EXME_TipoPregunta_ID = ? "
              + " AND EXME_Pregunta.IsActive = 'Y' "
              + " AND EXME_TipoPregunta.IsActive = 'Y' ";*/
        
        sql.append(" SELECT EXME_Pregunta.EXME_Pregunta_ID, EXME_Pregunta.Name ");
        sql.append(" FROM EXME_Pregunta, EXME_TipoPregunta ");
        sql.append(" WHERE EXME_Pregunta.EXME_TipoPregunta_ID = EXME_TipoPregunta.EXME_TipoPregunta_ID ");
        sql.append(" AND EXME_TipoPregunta.EXME_TipoPregunta_ID = ? ");
        sql.append(" AND EXME_Pregunta.IsActive = 'Y' ");
        sql.append(" AND EXME_TipoPregunta.IsActive = 'Y' ");

        sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, sql.toString(), "EXME_Pregunta"));
        
        sql.append(" ORDER BY EXME_Pregunta.Name");

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        LabelValueBean preg = null;
        try {
            pstmt = DB.prepareStatement(sql.toString(), null);
            pstmt.setLong(1, tipoPregunta);
            rs = pstmt.executeQuery();

            while(rs.next()) {
                preg = 
                    new LabelValueBean(
                            rs.getString("Name"),
                            String.valueOf(rs.getLong("EXME_Pregunta_ID"))
                    );
                lstPreguntas.add(preg);
            }
		 } catch (Exception e) {
			 logger.log(Level.SEVERE, "getPreguntasXTipo - sql = " + sql, e);
		 } finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				
				if (rs != null) {
					rs.close();
				}
			 } catch (SQLException exClose) {
				 logger.log(Level.SEVERE, "getPreguntasXTipo - while closing rs and pstmt objects = ", exClose);
			 }
		 }

        return lstPreguntas;
    }    
    
    public static ArrayList<Pregunta_VO> getPreguntasPorTipo (Properties ctx, 
    		Integer tipoPregunta) throws Exception {
        //lista con las preguntas
        ArrayList<Pregunta_VO> lstPreguntas = new ArrayList<Pregunta_VO>();
        StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
        
        //buscamos las preguntas
        /*String sql = " SELECT EXME_Pregunta.EXME_Pregunta_ID, EXME_Pregunta.Name, EXME_Pregunta.TipoDato, EXME_TipoPregunta.Name "
              + " FROM EXME_Pregunta, EXME_TipoPregunta "
              + " WHERE EXME_Pregunta.EXME_TipoPregunta_ID = EXME_TipoPregunta.EXME_TipoPregunta_ID "
              + " AND EXME_TipoPregunta.EXME_TipoPregunta_ID = ? "
              + " AND EXME_Pregunta.IsActive = 'Y' "
              + " AND EXME_TipoPregunta.IsActive = 'Y' ";*/
        
        sql.append(" SELECT EXME_Pregunta.EXME_Pregunta_ID, EXME_Pregunta.Name, ");
        sql.append(" EXME_Pregunta.TipoDato, EXME_TipoPregunta.Name ");
        sql.append(" FROM EXME_Pregunta, EXME_TipoPregunta ");
        sql.append(" WHERE EXME_Pregunta.EXME_TipoPregunta_ID = EXME_TipoPregunta.EXME_TipoPregunta_ID ");
        sql.append(" AND EXME_TipoPregunta.EXME_TipoPregunta_ID = ? ");
        sql.append(" AND EXME_Pregunta.IsActive = 'Y' ");
        sql.append(" AND EXME_TipoPregunta.IsActive = 'Y' ");

        sql = new StringBuilder (MEXMELookupInfo.addAccessLevelSQL(ctx, sql.toString(), "EXME_Pregunta"));
        
        sql.append(" ORDER BY EXME_Pregunta.Name");

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = DB.prepareStatement(sql.toString(), null);
            pstmt.setInt(1, tipoPregunta.intValue());
            rs = pstmt.executeQuery();
            Pregunta_VO preg = null;
            
            while (rs.next()) {
            	 preg = new Pregunta_VO();  
            	 preg.setTipoPregunta(tipoPregunta);
            	 preg.setId(rs.getInt(1));
            	 preg.setName(rs.getString(2));
            	 preg.setTipoDato(rs.getString(3));
            	 preg.setTipoPreguntaTxt(rs.getString(4));
            	 
            	 if (preg.getTipoDato().equalsIgnoreCase(MPregunta.TIPODATO_OptionList)) {
            		 preg.setRespuestas(MPregunta_Lista.getListaRespuestas(preg.getId()));
            	 }
            	 
                lstPreguntas.add(preg);
            }
		} catch (Exception e) {
	        logger.log(Level.SEVERE, "getPreguntasPorTipo - sql = " + sql, e);
		} finally {
		    try {
				if (pstmt != null) {
					pstmt.close();
				}
					
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException exClose) {
			    logger.log(Level.SEVERE, "getPreguntasPorTipo - while closing rs and pstmt objects = ", exClose);
			}
		}
        return lstPreguntas;
    }    

    
    // secuencia para preg. repetidas .- Lama
    public static List<Pregunta_VO> getPreguntasCuestionario (Integer paciente, 
    		Integer cuestionario, List<Pregunta_VO> lstPreguntas, String getResp, 
    		Integer cita, Integer ctaPac, boolean histclinica) 
    		throws Exception {

    	StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);

    	sql.append(" 	select ")
    	.append(" 	cu.EXME_Pregunta_ID, ")//1
    	.append(" 	p.Name, ")//2
    	.append(" 	p.TipoDato, ")//3 
    	.append(" 	p.exme_tipopregunta_id, ")//4
    	.append(" 	tp.name, ")//5
    	.append(" 	cu.EXME_Cuestionario_ID, ")//6
    	.append(" 	cu.Obligatoria, ")//7
    	.append(" 	cu.Secuencia,   ")//8
    	.append(" 	rl.value,  ")//9
    	.append(" 	rl.name,  ")//10
    	.append(" 	rl.exme_pregunta_lista_id, ")//11
    	.append(" 	r.exme_cuestionario_id, ")//12
    	.append(" 	r.preg_condicionante, ")//13
    	.append(" 	r.preg_condicionante, ")//14
    	.append(" 	rr.exme_pregunta_id, ")//15
    	.append(" 	r.exme_tipopregunta_id, ")//16
    	.append(" 	r.respuesta, ")//17
    	.append(" 	r.exme_pregunta_lista_id, ")//18
    	.append(" 	lista.name, ")//19
    	.append(" 	cu.EXME_CuestionarioDt_id, ")//20
    	.append(" 	rr.respuesta, ")//21
    	.append(" 	rr.exme_pregunta_lista_id")//22
    	.append(" 	from EXME_CuestionarioDt cu ")
    	.append(" 	inner join exme_pregunta p on (cu.exme_pregunta_id = p.exme_pregunta_id) ")
    	.append(" 	inner join exme_tipopregunta tp on (p.exme_tipopregunta_id=tp.exme_tipopregunta_id) ")
    	.append(" 	left join  exme_pregunta_lista rl on (p.exme_pregunta_id = rl.exme_pregunta_id and (p.TipoDato = 'C' or p.TipoDato = 'O')) 	 ")
    	
    	//.append(" 	left join  exme_reglacuestionario r on (cu.exme_cuestionario_id = r.exme_cuestionario_id and cu.exme_pregunta_id = r.exme_pregunta_id) ")
    	.append(" 	left JOIN ( select ri.exme_cuestionario_id, ri.preg_condicionante, ri.exme_tipopregunta_id, 	 ")
        .append(" 	rdti.exme_pregunta_lista_id, rdti.exme_pregunta_id , rdti.respuesta 	 ")
        .append(" 	from exme_reglacuestionario ri 	 ")
        .append(" 	inner join exme_reglacuestionariodt rdti on rdti.exme_reglacuestionario_id = ri.exme_reglacuestionario_id 	 ")
        .append(" 	) r ON (cu.exme_cuestionario_id = r.exme_cuestionario_id AND cu.exme_pregunta_id = r.exme_pregunta_id) 	 ")

    	//.append(" 	left join  exme_reglacuestionario rr on (r.exme_cuestionario_id = rr.exme_cuestionario_id and r.exme_pregunta_id = rr.preg_condicionante)  ")
        .append(" 	left JOIN ( select ri.exme_cuestionario_id, ri.preg_condicionante, ri.exme_tipopregunta_id, 	 ")
        .append(" 	rdti.exme_pregunta_lista_id, rdti.exme_pregunta_id, rdti.respuesta  	 ")
        .append(" 	from exme_reglacuestionario ri 	 ")
        .append(" 	INNER join exme_reglacuestionariodt rdti on rdti.exme_reglacuestionario_id = ri.exme_reglacuestionario_id 	 ")
        .append(" 	) rr   	 ")
        .append(" 	ON (rr.exme_cuestionario_id = cu.exme_cuestionario_id 	 ") 
        .append(" 	AND rr.preg_condicionante = cu.exme_pregunta_id  	 ")
        .append(" 	and ( 	 ")
        .append(" 	(rr.exme_pregunta_lista_id = rl.exme_pregunta_lista_id AND (p.TipoDato = 'C' OR p.TipoDato = 'O')) 	 ")
        .append(" 	or (rr.exme_pregunta_lista_id is null AND (p.TipoDato <> 'C' OR p.TipoDato <> 'O')) 	 ")
        .append(" 	) 	 ")
        .append(" 	) 	 ")
        
    	
    	//.append(" 	left join  exme_reglacuestionariodt rd on (rd.exme_reglacuestionario_id=r.exme_reglacuestionario_id) ")
    	//.append(" 	left join  exme_reglacuestionariodt rrd on (rrd.exme_reglacuestionario_id=rr.exme_reglacuestionario_id) ")  	
    	.append(" 	left join  exme_pregunta_lista lista on (lista.exme_pregunta_lista_id=r.exme_pregunta_lista_id) ")
    	.append(" 	where cu.exme_cuestionario_id=?")
    	.append(" 	ORDER BY cu.Secuencia, tp.name,rl.exme_pregunta_lista_id ");


    	PreparedStatement pstmt = null;
    	ResultSet rs = null;

    	try {
    		pstmt = DB.prepareStatement(sql.toString(), null);
    		pstmt.setInt(1, cuestionario.intValue());
    		rs = pstmt.executeQuery();
    		Pregunta_VO preg = null;
    		RespuestaList_VO resps = null;
    		Regla_VO reg = null;
    		boolean banderaAux = false;
    		int EXME_CuestionarioDt_id = 0;
    		int multiOpsID = 0;
    		while (rs.next()) {
    			banderaAux = true;
    			if (preg == null || EXME_CuestionarioDt_id != rs.getInt(20)) {
    				if (preg != null) {
    					if (getResp != null && getResp.equalsIgnoreCase("Y") 
    							&& paciente != null && paciente.intValue() > 0) {
    						if (histclinica) {	
    							if (preg.getTipoDato().equalsIgnoreCase(MPregunta.TIPODATO_Image)) {
    								preg.setRutaImagen(MPregunta.getRutaImagen(paciente, preg.getId(), preg.getCuestionarioId(), cita, ctaPac, preg.getSecuencia()));
    							} else if (!preg.getTipoDato().equalsIgnoreCase(MPregunta.TIPODATO_Image)) {
    								preg.setRespuesta(MPregunta.getRespuesta(paciente, preg.getId(), preg.getCuestionarioId(), cita, ctaPac, preg.getSecuencia(),true));
    								preg.setComentario(MPregunta.getComentario(paciente, preg.getId(), preg.getCuestionarioId(), cita, ctaPac, preg.getSecuencia()));
    							}
    							
    							if (preg.getTipoDato().equalsIgnoreCase(MPregunta.TIPODATO_OptionList)) {
    								for (int j = 0; j < preg.getRespuestas().size(); j++) {
    									if (preg.getRespuestas().get(j).getDescripcion().equalsIgnoreCase(preg.getRespuesta())) {
    										preg.getRespuestas().get(j).setSelected(SELECTED);
    										j = preg.getRespuestas().size();
    									}
    								}
    							}
    						} else {
    							if (preg.getTipoDato().equalsIgnoreCase(MPregunta.TIPODATO_Image)) {
    								preg.setRutaImagen(MPregunta.getRutaImagen(paciente, preg.getId(), preg.getCuestionarioId(), cita, ctaPac, preg.getSecuencia()));
    							} else if (!preg.getTipoDato().equalsIgnoreCase(MPregunta.TIPODATO_Image)) {
    								preg.setRespuesta(MPregunta.getRespuesta(paciente, preg.getId(), preg.getCuestionarioId(), cita, ctaPac, preg.getSecuencia(),false));
    								preg.setComentario(MPregunta.getComentario(paciente, preg.getId(), preg.getCuestionarioId(), cita, ctaPac, preg.getSecuencia()));
    							}
    							
    							if (preg.getTipoDato().equalsIgnoreCase(MPregunta.TIPODATO_OptionList)) {
    								for (int j = 0; j < preg.getRespuestas().size(); j++) {
    									if (preg.getRespuestas().get(j).getDescripcion().equalsIgnoreCase(preg.getRespuesta())) {
    										preg.getRespuestas().get(j).setSelected(SELECTED);
    										j = preg.getRespuestas().size();
    									}
    								}
    							}
    						}
    					}
    					
    					preg.setIndice(lstPreguntas.size());             		    	 
    					lstPreguntas.add(preg);
    				} // end if preg != null
    				multiOpsID = 0;
    				preg = new Pregunta_VO();            	 
    				preg.setId(rs.getInt(1));
    				preg.setPregId(rs.getInt(1));
    				preg.setName(rs.getString(2));
    				preg.setTipoDato(rs.getString(3));
    				preg.setTipoPregunta(rs.getInt(4));
    				preg.setTipoPreguntaTxt(rs.getString(5));
    				preg.setCuestionarioId(cuestionario);
    				preg.setObligatoriaTxt(rs.getString(7));
    				preg.setSecuencia(rs.getInt(8));

    				if ((preg.getTipoDato().equalsIgnoreCase(MPregunta.TIPODATO_OptionList) 
    						|| preg.getTipoDato().equalsIgnoreCase(MPregunta.TIPODATO_MultiOptions)) 
    						&& rs.getString(9) != null) {
    					preg.setRespuestas(new ArrayList<RespuestaList_VO>());
    					resps = new RespuestaList_VO();            	 
    					resps.setPreguntaId(rs.getInt(1));
    					resps.setValorTxt(rs.getString(9));
    					resps.setDescripcion(rs.getString(10));
    					resps.setListaID(rs.getInt(11));
    					multiOpsID = rs.getInt(11); 
    					preg.getRespuestas().add(resps);
    				}

    				if (rs.getInt(12) > 0 || rs.getInt(15) > 0) {
    					preg.setLstReglas(new ArrayList<Regla_VO>());
    					reg = new Regla_VO();            	 

    					reg.setCuestionarioId(rs.getInt(12));
    					reg.setPreguntaId(rs.getInt(13));
    					reg.setPregCondicionante(rs.getInt(14));
    					reg.setPregModifica(rs.getInt(15));
    					reg.setTipoPregunta(rs.getInt(16));
    					reg.setBanderaResp(rs.getString(17));
    					reg.setListaId(rs.getInt(18));
    					reg.setRespLista(rs.getString(19));
    					reg.setBanderaResp2(rs.getString(21));
    					reg.setBanderaRespLstId(rs.getInt(22));
    					preg.getLstReglas().add(reg);
    				}
    			} else { // else del if (preg == null || EXME_CuestionarioDt_id != rs.getInt(20)) {
    				if (preg.getPregId().intValue() == rs.getInt(1)) {
    					if((preg.getTipoDato().equalsIgnoreCase(MPregunta.TIPODATO_OptionList) 
    							|| preg.getTipoDato().equalsIgnoreCase(MPregunta.TIPODATO_MultiOptions)) 
    							&& rs.getString(9) != null) {

    						if (preg.getRespuestas() == null || preg.getRespuestas().size() < 1) {
    							preg.setRespuestas(new ArrayList<RespuestaList_VO>());	 
    						}
    						
    						if(multiOpsID != rs.getInt(11)){
	    						resps = new RespuestaList_VO();            	 
	    						resps.setPreguntaId(rs.getInt(1));
	    						resps.setValorTxt(rs.getString(9));
	    						resps.setDescripcion(rs.getString(10));
	    						resps.setListaID(rs.getInt(11));
	    						multiOpsID = rs.getInt(11);
	    						preg.getRespuestas().add(resps);
    						}
    					}

    					if (rs.getInt(12) > 0 || rs.getInt(15) > 0) {
    						if(preg.getLstReglas() == null || preg.getLstReglas().size() < 1){
    							preg.setLstReglas(new ArrayList<Regla_VO>());	 
    						}

    						reg = new Regla_VO();            	 

    						reg.setCuestionarioId(rs.getInt(12));
    						reg.setPreguntaId(rs.getInt(13));
    						reg.setPregCondicionante(rs.getInt(14));
    						reg.setPregModifica(rs.getInt(15));
    						reg.setTipoPregunta(rs.getInt(16));
    						reg.setBanderaResp(rs.getString(17));
    						reg.setListaId(rs.getInt(18));
    						reg.setRespLista(rs.getString(19));
    						reg.setBanderaResp2(rs.getString(21));
    						reg.setBanderaRespLstId(rs.getInt(22));
    						preg.getLstReglas().add(reg);
    					}
    				}
    			}
    			EXME_CuestionarioDt_id = rs.getInt(20);
    		}

    		if (banderaAux) {
    			if (preg != null) {
    				if (getResp != null 
    						&& getResp.equalsIgnoreCase("Y") 
    						&& paciente != null 
    						&& paciente.intValue() > 0 ) {
    					if (histclinica) {
    						if (!preg.getTipoDato().equalsIgnoreCase(MPregunta.TIPODATO_Image)) {
    							preg.setRespuesta(MPregunta.getRespuesta(paciente, preg.getId(), preg.getCuestionarioId(), cita, ctaPac, preg.getSecuencia(),true));
    							preg.setComentario(MPregunta.getComentario(paciente, preg.getId(), preg.getCuestionarioId(), cita, ctaPac, preg.getSecuencia()));
    						}
    						if (preg.getTipoDato().equalsIgnoreCase(MPregunta.TIPODATO_Image)) {
    							preg.setRutaImagen(MPregunta.getRutaImagen(paciente, preg.getId(), preg.getCuestionarioId(), cita, ctaPac, preg.getSecuencia()));
    						}
    						if (preg.getTipoDato().equalsIgnoreCase(MPregunta.TIPODATO_OptionList)) {
    							for (int j = 0; j < preg.getRespuestas().size(); j++) {
    								if (preg.getRespuestas().get(j).getDescripcion().equalsIgnoreCase(preg.getRespuesta())){
    									preg.getRespuestas().get(j).setSelected(SELECTED);
    									j = preg.getRespuestas().size();
    								}
    							}
    						}
    					} else {
    						if (!preg.getTipoDato().equalsIgnoreCase(MPregunta.TIPODATO_Image)) {
    							preg.setRespuesta(MPregunta.getRespuesta(paciente, preg.getId(), preg.getCuestionarioId(), cita, ctaPac, preg.getSecuencia(),false));
    							preg.setComentario(MPregunta.getComentario(paciente, preg.getId(), preg.getCuestionarioId(), cita, ctaPac, preg.getSecuencia()));
    						}
    						if (preg.getTipoDato().equalsIgnoreCase(MPregunta.TIPODATO_Image)) {
    							preg.setRutaImagen(MPregunta.getRutaImagen(paciente, preg.getId(), preg.getCuestionarioId(), cita, ctaPac, preg.getSecuencia()));
    						}
    						if (preg.getTipoDato().equalsIgnoreCase(MPregunta.TIPODATO_OptionList)) {
    							for (int j = 0; j < preg.getRespuestas().size(); j++) {
    								if(preg.getRespuestas().get(j).getDescripcion().equalsIgnoreCase(preg.getRespuesta())) {
    									preg.getRespuestas().get(j).setSelected(SELECTED);
    									j = preg.getRespuestas().size();
    								}
    							}
    						}
    					}
    				}		 
    				preg.setIndice(lstPreguntas.size());             		    	 
    				lstPreguntas.add(preg);
    			}
    		}
		} catch (Exception e) {
	        logger.log(Level.SEVERE, "getPreguntasCuestionario - sql = " + sql, e);
		} finally {
		    try {
				if (pstmt != null) {
					pstmt.close();
				}
					
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException exClose) {
			    logger.log(Level.SEVERE, "getPreguntasCuestionario - while closing rs and pstmt objects = ", exClose);
			}
		}

    	return lstPreguntas;
    }    
        
    
    public static List<Pregunta_VO> getPreguntasCuestionarioMulti (Properties ctx, Integer paciente, 
    		Integer cuestionario, List<Pregunta_VO> lstPreguntas, String getResp, Integer cita, 
    		Integer ctaPac, Integer proyecto, Integer medico) throws Exception {
        
    	StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
	 
        sql.append("	select")
           .append(" 	cu.EXME_Pregunta_ID,")
           .append(" 	p.Name,")
           .append(" 	p.TipoDato,") 
           .append(" 	p.exme_tipopregunta_id,")
           .append(" 	tp.name,")
           .append(" 	cu.EXME_Cuestionario_ID,") 
           .append(" 	cu.Obligatoria,")
           .append(" 	cu.Secuencia")
           .append(" 	from EXME_CuestionarioDt cu") 
           .append(" 	inner join exme_pregunta p on (cu.exme_pregunta_id = p.exme_pregunta_id)")
           .append(" 	inner join exme_tipopregunta tp on (p.exme_tipopregunta_id=tp.exme_tipopregunta_id)")
           .append(" 	where cu.exme_cuestionario_id=?")
           .append("    ORDER BY cu.Secuencia, tp.name ");
             
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            pstmt = DB.prepareStatement(sql.toString(), null);
            pstmt.setInt(1, cuestionario.intValue());
            rs = pstmt.executeQuery();
            Pregunta_VO preg = null;
            
            while(rs.next()) {
            	 preg = new Pregunta_VO();            	 
            	 preg.setId(rs.getInt(1));
            	 preg.setPregId(rs.getInt(1));
            	 preg.setName(rs.getString(2));
            	 preg.setTipoDato(rs.getString(3));
            	 preg.setTipoPregunta(rs.getInt(4));
            	 preg.setTipoPreguntaTxt(rs.getString(5));
            	 preg.setCuestionarioId(cuestionario);
            	 preg.setObligatoriaTxt(rs.getString(7));
            	 preg.setSecuencia(rs.getInt(8));
            	 preg.setLstReglas(MEXMEReglaCuestionario.getPreguntasCuestionario(preg.getId(), preg.getCuestionarioId()));
            	 
            	 if (preg.getTipoDato().equalsIgnoreCase(MPregunta.TIPODATO_OptionList) || preg.getTipoDato().equalsIgnoreCase(MPregunta.TIPODATO_MultiOptions)) {
            		 preg.setRespuestas(MPregunta_Lista.getListaRespuestas(preg.getId()));
            	 }
            	 
            	 
            	 if (getResp != null && getResp.equalsIgnoreCase("Y") ) {
            		 
            		 if (!preg.getTipoDato().equalsIgnoreCase(MPregunta.TIPODATO_Image) && 
            				 !preg.getTipoDato().equalsIgnoreCase(MPregunta.TIPODATO_MultiOptions)) {            			 
            			 if (proyecto > 0) {
            				 preg.setRespuesta(MPregunta.getRespuestaEnfControl(proyecto, preg.getId(), preg.getCuestionarioId(), preg.getSecuencia(), medico));
            				 preg.setComentario(MPregunta.getComentarioEnfControl(proyecto, preg.getId(), preg.getCuestionarioId(), preg.getSecuencia(), medico));
            			 } else {
	            			 preg.setRespuesta(MPregunta.getRespuestaEnfControl(paciente, preg.getId(), preg.getCuestionarioId(), cita, ctaPac, preg.getSecuencia()));
	            			 preg.setComentario(MPregunta.getComentarioEnfControl(paciente, preg.getId(), preg.getCuestionarioId(), cita, ctaPac, preg.getSecuencia()));
            			 }
            		 } else if(preg.getTipoDato().equalsIgnoreCase(MPregunta.TIPODATO_Image)) {
            			 if (proyecto > 0) {     
            				 preg.setRutaImagen(MPregunta.getRutaImagenEnfControl(proyecto, preg.getId(), preg.getCuestionarioId(), preg.getSecuencia(), medico));
	                		 preg.setComentario(MPregunta.getComentarioEnfControl(proyecto, preg.getId(), preg.getCuestionarioId(), preg.getSecuencia(), medico));
            			 } else {
	                		 preg.setRutaImagen(MPregunta.getRutaImagenEnfControl(paciente, preg.getId(), preg.getCuestionarioId(), cita, ctaPac, preg.getSecuencia()));
	                		 preg.setComentario(MPregunta.getComentarioEnfControl(paciente, preg.getId(), preg.getCuestionarioId(), cita, ctaPac, preg.getSecuencia()));
            			 }
                	 } else if (preg.getTipoDato().equalsIgnoreCase(MPregunta.TIPODATO_MultiOptions)) {
                		 if (proyecto > 0) {
                			 preg.setComentario(MPregunta.getComentarioEnfControl(proyecto, preg.getId(), preg.getCuestionarioId(), preg.getSecuencia(), medico));
                			
                			 if (MPregunta.existRespuestasMulti(proyecto, preg.getId(), preg.getCuestionarioId(), preg.getSecuencia())) {
		            			 for (int j = 0; j < preg.getRespuestas().size(); j++) {
		            				 preg.getRespuestas().get(j).setSeleccionado(MPregunta.getRespuestasMulti(proyecto, preg.getId(), preg.getCuestionarioId(), preg.getSecuencia(), preg.getRespuestas().get(j).getListaID()));
		            			 }
                			 }
                		 } else {
                			 preg.setComentario(MPregunta.getComentarioEnfControl(paciente, preg.getId(), preg.getCuestionarioId(), cita, ctaPac, preg.getSecuencia()));
                			 
                			 if (MPregunta.existRespuestasMulti(paciente, preg.getId(), preg.getCuestionarioId(), cita, ctaPac, preg.getSecuencia())) {
		            			 for (int j = 0; j < preg.getRespuestas().size(); j++) {
		            				 preg.getRespuestas().get(j).setSeleccionado(MPregunta.getRespuestasMulti(paciente, preg.getId(), preg.getCuestionarioId(), cita, ctaPac, preg.getSecuencia(), preg.getRespuestas().get(j).getListaID()));
		            			 }
                			 }
                		 }                		                 		             			 
            		 }
            		 
            		 if (preg.getTipoDato().equalsIgnoreCase(MPregunta.TIPODATO_OptionList)) {
            			 for (int j = 0; j < preg.getRespuestas().size(); j++) {
            				 if (preg.getRespuestas().get(j).getDescripcion().equalsIgnoreCase(preg.getRespuesta())){
            					 preg.getRespuestas().get(j).setSelected(SELECTED);
            					 j = preg.getRespuestas().size();
            				 }
            			 }
            		 }
            	 }
            	 preg.setIndice(lstPreguntas.size());
                 lstPreguntas.add(preg);
            }           
		} catch (Exception e) {
	        logger.log(Level.SEVERE, "getPreguntasCuestionarioMulti - sql = " + sql, e);
		} finally {
		    try {
				if (pstmt != null) {
					pstmt.close();
				}
					
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException exClose) {
			    logger.log(Level.SEVERE, "getPreguntasCuestionarioMulti - while closing rs and pstmt objects = ", exClose);
			}
		}

        return lstPreguntas;
    }    
    
    public static ArrayList<Pregunta_VO> getPreguntasCuestionarioFolio (Integer paciente, Integer cuestionario, ArrayList<Pregunta_VO> lstPreguntas, Integer folio, Properties ctx) throws Exception{
        //lista con las preguntas
        //ArrayList<Pregunta_VO> lstPreguntas = new ArrayList<Pregunta_VO>();

        StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
	 
         sql.append("	select")
            .append(" 	cu.EXME_Pregunta_ID, ")//1
            .append(" 	p.Name, ")//2
            .append(" 	p.TipoDato, ")//3 
            .append(" 	p.exme_tipopregunta_id, ")//4
	    	.append(" 	tp.name, ")//5
	    	.append(" 	cu.EXME_Cuestionario_ID, ")//6
	    	.append(" 	cu.Obligatoria, ")//7
	    	.append(" 	cu.Secuencia   ")//8
	    	//.append(" 	rl.value,  ")//9
	    	//.append(" 	rl.name,  ")//10
	    	//.append(" 	rl.exme_pregunta_lista_id, ")//11
/*	    	.append(" 	r.exme_cuestionario_id, ")//12
	    	.append(" 	r.preg_condicionante, ")//13
	    	.append(" 	r.preg_condicionante, ")//14
	    	.append(" 	rr.exme_pregunta_id, ")//15
	    	.append(" 	r.exme_tipopregunta_id, ")//16
	    	.append(" 	rd.respuesta, ")//17
	    	.append(" 	rd.exme_pregunta_lista_id, ")//18
	    	.append(" 	lista.name, ")//19
	    	.append(" 	cu.EXME_CuestionarioDt_id, ")//20
	    	.append(" 	rrd.respuesta, ")//21
	    	.append(" 	rrd.exme_pregunta_lista_id")//22
*/           
           .append(" 	from EXME_CuestionarioDt cu") 
           .append(" 	inner join exme_pregunta p on (cu.exme_pregunta_id = p.exme_pregunta_id)")
           .append(" 	inner join exme_tipopregunta tp on (p.exme_tipopregunta_id=tp.exme_tipopregunta_id)")
           
     //      .append(" 	left join  exme_pregunta_lista rl on (p.exme_pregunta_id = rl.exme_pregunta_id) ")
/*           .append(" 	left join  exme_reglacuestionario r on (cu.exme_cuestionario_id = r.exme_cuestionario_id and cu.exme_pregunta_id = r.exme_pregunta_id) ")
           .append(" 	left join  exme_reglacuestionario rr on (cu.exme_cuestionario_id = rr.exme_cuestionario_id and cu.exme_pregunta_id = rr.preg_condicionante)  ") 	
           .append(" 	left join  exme_reglacuestionariodt rd on (rd.exme_reglacuestionario_id=r.exme_reglacuestionario_id) ")
           .append(" 	left join  exme_reglacuestionariodt rrd on (rrd.exme_reglacuestionario_id=rr.exme_reglacuestionario_id) ")  	
           .append(" 	left join  exme_pregunta_lista lista on (lista.exme_pregunta_lista_id=rd.exme_pregunta_lista_id) ")
 */          
           .append(" 	where cu.exme_cuestionario_id=?")
           .append("    ORDER BY cu.Secuencia, tp.name ");
                     
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            pstmt = DB.prepareStatement(sql.toString(), null);
            pstmt.setInt(1, cuestionario.intValue());
            rs = pstmt.executeQuery();
            Pregunta_VO preg = null;
            
            while(rs.next()) {
            	 preg = new Pregunta_VO();            	 
            	 preg.setId(rs.getInt(1));
            	 preg.setName(rs.getString(2));
            	 preg.setTipoDato(rs.getString(3));
            	 preg.setTipoPregunta(rs.getInt(4));
            	 preg.setTipoPreguntaTxt(rs.getString(5));
            	 preg.setCuestionarioId(cuestionario);
            	 preg.setObligatoriaTxt(rs.getString(7));
            	 preg.setSecuencia(rs.getInt(8));
            	 preg.setLstReglas(MEXMEReglaCuestionario.getPreguntasCuestionarioForms(preg.getId(), preg.getCuestionarioId()));
            	 if (preg.getTipoDato().equalsIgnoreCase(MPregunta.TIPODATO_OptionList) || preg.getTipoDato().equalsIgnoreCase(MPregunta.TIPODATO_MultiOptions)) {
            		 preg.setRespuestas(MPregunta_Lista.getListaRespuestas(preg.getId()));
            	 }
            	 
				if (!preg.getTipoDato().equalsIgnoreCase(MPregunta.TIPODATO_MultiOptions)) {
					String[] respuesta = MPregunta.getRespuestaComentarioFolio(folio, preg.getId(), preg.getCuestionarioId(), preg.getSecuencia(), Env.getAD_User_ID(ctx));
					if (respuesta != null) {
						if (preg.getTipoDato().equalsIgnoreCase(MPregunta.TIPODATO_Image)) {
							// preg.setRutaImagen(MPregunta.getRutaImagenFolio(folio, preg.getId(), preg.getCuestionarioId()));
							preg.setRutaImagen(respuesta[3]);// rutaimagen

						} else if (!preg.getTipoDato().equalsIgnoreCase(MPregunta.TIPODATO_Image)) {
							if (preg.getTipoDato().equalsIgnoreCase(MPregunta.TIPODATO_Logial)) {
								preg.setRespuestaL(respuesta[0].equals("Y") ? true : false);
							}
							
							preg.setRespuesta(respuesta[0]); //respuesta
							preg.setComentario(respuesta[1]);// description
							if (preg.getTipoDato().equalsIgnoreCase(MPregunta.TIPODATO_OptionList)) {
								preg.setRespuestaCombo(respuesta[2]); //pregunta_lista_value
							}
						}
					}
				}else{
					ArrayList<Integer> respuestasMulti = MPregunta.getRespuestaComentarioFolioMulti(folio, preg.getId(), preg.getCuestionarioId(), preg.getSecuencia(), Env.getAD_User_ID(ctx));
					for(Integer obj : respuestasMulti){
						for(int i=0; i<preg.getRespuestas().size(); i++ ){
							if(((RespuestaList_VO)preg.getRespuestas().get(i)).getListaID().intValue() == obj.intValue()){
								((RespuestaList_VO)preg.getRespuestas().get(i)).setSeleccionado(true);
								break;
							}
						}
					}
				}
				
			/*	Regla_VO reg = null;
				 
				if (rs.getInt(9) > 0 || rs.getInt(12) > 0) {
					preg.setLstReglas(new ArrayList<Regla_VO>());
					reg = new Regla_VO();            	 

					reg.setCuestionarioId(rs.getInt(9));
					reg.setPreguntaId(rs.getInt(10));
					reg.setPregCondicionante(rs.getInt(11));
					reg.setPregModifica(rs.getInt(12));
					reg.setTipoPregunta(rs.getInt(13));
					reg.setBanderaResp(rs.getString(14));
					reg.setListaId(rs.getInt(15));
					reg.setRespLista(rs.getString(16));
					reg.setBanderaResp2(rs.getString(18));
					reg.setBanderaRespLstId(rs.getInt(19));
					preg.getLstReglas().add(reg);
				}*/
				
				
            	preg.setIndice(lstPreguntas.size());
                lstPreguntas.add(preg);
            }
		} catch (Exception e) {
	        logger.log(Level.SEVERE, "getPreguntasCuestionarioFolio - sql = " + sql, e);
		} finally {
		    try {
				if (pstmt != null) {
					pstmt.close();
				}
					
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException exClose) {
			    logger.log(Level.SEVERE, "getPreguntasCuestionarioFolio - while closing rs and pstmt objects = ", exClose);
			}
		}

        return lstPreguntas;
    }
    
    public static ArrayList<Pregunta_VO> getPreguntasCuestionarioFolioReglas (Integer paciente, Integer cuestionario, ArrayList<Pregunta_VO> lstPreguntas, Integer folio, Properties ctx) throws Exception{
        //lista con las preguntas
        //ArrayList<Pregunta_VO> lstPreguntas = new ArrayList<Pregunta_VO>();

        StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
	 
         sql.append("	select")
            .append(" 	cu.EXME_Pregunta_ID, ")//1
            .append(" 	p.Name, ")//2
            .append(" 	p.TipoDato, ")//3 
            .append(" 	p.exme_tipopregunta_id, ")//4
	    	.append(" 	tp.name, ")//5
	    	.append(" 	cu.EXME_Cuestionario_ID, ")//6
	    	.append(" 	cu.Obligatoria, ")//7
	    	.append(" 	cu.Secuencia   ")//8
	    	//.append(" 	rl.value,  ")//9
	    	//.append(" 	rl.name,  ")//10
	    	//.append(" 	rl.exme_pregunta_lista_id, ")//11
/*	    	.append(" 	r.exme_cuestionario_id, ")//12
	    	.append(" 	r.preg_condicionante, ")//13
	    	.append(" 	r.preg_condicionante, ")//14
	    	.append(" 	rr.exme_pregunta_id, ")//15
	    	.append(" 	r.exme_tipopregunta_id, ")//16
	    	.append(" 	rd.respuesta, ")//17
	    	.append(" 	rd.exme_pregunta_lista_id, ")//18
	    	.append(" 	lista.name, ")//19
	    	.append(" 	cu.EXME_CuestionarioDt_id, ")//20
	    	.append(" 	rrd.respuesta, ")//21
	    	.append(" 	rrd.exme_pregunta_lista_id")//22
*/           
           .append(" 	from EXME_CuestionarioDt cu") 
           .append(" 	inner join exme_pregunta p on (cu.exme_pregunta_id = p.exme_pregunta_id)")
           .append(" 	inner join exme_tipopregunta tp on (p.exme_tipopregunta_id=tp.exme_tipopregunta_id)")
           
     //      .append(" 	left join  exme_pregunta_lista rl on (p.exme_pregunta_id = rl.exme_pregunta_id) ")
/*           .append(" 	left join  exme_reglacuestionario r on (cu.exme_cuestionario_id = r.exme_cuestionario_id and cu.exme_pregunta_id = r.exme_pregunta_id) ")
           .append(" 	left join  exme_reglacuestionario rr on (cu.exme_cuestionario_id = rr.exme_cuestionario_id and cu.exme_pregunta_id = rr.preg_condicionante)  ") 	
           .append(" 	left join  exme_reglacuestionariodt rd on (rd.exme_reglacuestionario_id=r.exme_reglacuestionario_id) ")
           .append(" 	left join  exme_reglacuestionariodt rrd on (rrd.exme_reglacuestionario_id=rr.exme_reglacuestionario_id) ")  	
           .append(" 	left join  exme_pregunta_lista lista on (lista.exme_pregunta_lista_id=rd.exme_pregunta_lista_id) ")
 */          
           .append(" 	where cu.exme_cuestionario_id=?")
           .append("    ORDER BY cu.Secuencia, tp.name ");
                     
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            pstmt = DB.prepareStatement(sql.toString(), null);
            pstmt.setInt(1, cuestionario.intValue());
            rs = pstmt.executeQuery();
            Pregunta_VO preg = null;
            
            while(rs.next()) {
            	 preg = new Pregunta_VO();            	 
            	 preg.setId(rs.getInt(1));
            	 preg.setName(rs.getString(2));
            	 preg.setTipoDato(rs.getString(3));
            	 preg.setTipoPregunta(rs.getInt(4));
            	 preg.setTipoPreguntaTxt(rs.getString(5));
            	 preg.setCuestionarioId(cuestionario);
            	 preg.setObligatoriaTxt(rs.getString(7));
            	 preg.setSecuencia(rs.getInt(8));
            	 preg.setLstReglas(MEXMEReglaCuestionario.getPreguntasCuestionarioForms(preg.getId(), preg.getCuestionarioId()));
            	 if (preg.getTipoDato().equalsIgnoreCase(MPregunta.TIPODATO_OptionList) || preg.getTipoDato().equalsIgnoreCase(MPregunta.TIPODATO_MultiOptions)) {
            		 preg.setRespuestas(MPregunta_Lista.getListaRespuestas(preg.getId()));
            	 }
            	 
				if (!preg.getTipoDato().equalsIgnoreCase(MPregunta.TIPODATO_MultiOptions)) {
					String[] respuesta = MPregunta.getRespuestaComentarioFolio(folio, preg.getId(), preg.getCuestionarioId(), preg.getSecuencia(), Env.getAD_User_ID(ctx));
					if (respuesta != null) {
						if (preg.getTipoDato().equalsIgnoreCase(MPregunta.TIPODATO_Image)) {
							// preg.setRutaImagen(MPregunta.getRutaImagenFolio(folio, preg.getId(), preg.getCuestionarioId()));
							preg.setRutaImagen(respuesta[3]);// rutaimagen

						} else if (!preg.getTipoDato().equalsIgnoreCase(MPregunta.TIPODATO_Image)) {
							if (preg.getTipoDato().equalsIgnoreCase(MPregunta.TIPODATO_Logial)) {
								preg.setRespuestaL(respuesta[0].equals("Y") ? true : false);
							}
							
							preg.setRespuesta(respuesta[0]); //respuesta
							preg.setComentario(respuesta[1]);// description
							if (preg.getTipoDato().equalsIgnoreCase(MPregunta.TIPODATO_OptionList)) {
								preg.setRespuestaCombo(respuesta[2]); //pregunta_lista_value
							}
						}
					}
				}else{
					ArrayList<Integer> respuestasMulti = MPregunta.getRespuestaComentarioFolioMulti(folio, preg.getId(), preg.getCuestionarioId(), preg.getSecuencia(), Env.getAD_User_ID(ctx));
					for(Integer obj : respuestasMulti){
						for(int i=0; i<preg.getRespuestas().size(); i++ ){
							if(((RespuestaList_VO)preg.getRespuestas().get(i)).getListaID().intValue() == obj.intValue()){
								((RespuestaList_VO)preg.getRespuestas().get(i)).setSeleccionado(true);
								break;
							}
						}
					}
				}
				
            	preg.setIndice(lstPreguntas.size());
                lstPreguntas.add(preg);
            }
		} catch (Exception e) {
	        logger.log(Level.SEVERE, "getPreguntasCuestionarioFolio - sql = " + sql, e);
		} finally {
		    try {
				if (pstmt != null) {
					pstmt.close();
				}
					
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException exClose) {
			    logger.log(Level.SEVERE, "getPreguntasCuestionarioFolio - while closing rs and pstmt objects = ", exClose);
			}
		}

        return lstPreguntas;
    }    
    
   
    
    public static String getRespuesta (Integer paciente, Integer pregunta, Integer cuestionario, 
    		Integer cita, Integer cta_PAC, Integer secuencia, boolean histclinica) throws Exception {
       
    	String ret = "";
        StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
	   
	    sql.append("	select dt.respuesta, dt.TEXTBINARY")
	      .append("    from exme_actpaciente ac")
	      .append("    inner join exme_actpacientedet dt on(ac.exme_actpaciente_id = dt.exme_actpaciente_id)")
	      .append("    where ac.exme_paciente_id = ?")
	      .append("    and dt.exme_cuestionario_id = ?")
	      .append("    and dt.exme_pregunta_id = ?");
	    
   	    if (!histclinica) {
   	    	if (cita != null && cita.intValue() > 0) {
	    	  sql.append("    and ac.exme_citamedica_id is not null");
	        } else {
	    	  sql.append("    and ac.exme_citamedica_id is null");
	        }
   	    	
	        if (cta_PAC != null && cta_PAC.intValue() > 0) {
	    	    sql.append("    and ac.exme_ctapac_id is not null");
	        } else {
	    	    sql.append("    and ac.exme_ctapac_id is null");
	        }
   	    }	      
      
        sql.append(" and dt.secuencia = ? ");// secuencia para preg. repetidas .- Lama
        sql.append("    order by dt.created desc");
             
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            pstmt = DB.prepareStatement(sql.toString(), null);
            pstmt.setInt(1, paciente.intValue());
            pstmt.setInt(2, cuestionario.intValue());
            pstmt.setInt(3, pregunta.intValue());
            pstmt.setInt(4, secuencia.intValue());
            
            rs = pstmt.executeQuery();
            
            if (rs.next()) {
            	if (rs.getString(2) == null) {
            		ret = rs.getString(1);
            	} else {
            		ret = rs.getString(2);
            	}
            }
		} catch (Exception e) {
	        logger.log(Level.SEVERE, "getRespuesta - sql = " + sql, e);
		} finally {
		    try {
				if (pstmt != null) {
					pstmt.close();
				}
					
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException exClose) {
			    logger.log(Level.SEVERE, "getRespuesta - while closing rs and pstmt objects = ", exClose);
			}
		}

        return ret;
    }
    
    
    public static String getRespuestaEnfControl (Integer paciente, Integer pregunta, 
    		Integer cuestionario, Integer cita, Integer cta_PAC, Integer secuencia) throws Exception {
        
    	String ret = "";

        StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
	   
  
		sql.append("	select ac.respuesta, ac.TEXTBINARY")    
		    .append("	from exme_enfcontrolresp ac")    
		    .append("	where ac.exme_paciente_id = ?") 
		    .append("	and ac.exme_cuestionario_id = ?")    
		    .append("	and ac.exme_pregunta_id = ?")
		    .append("	and ac.secuencia = ?")
		    .append("	order by ac.created desc");
     
 		PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            pstmt = DB.prepareStatement(sql.toString(), null);
            pstmt.setInt(1, paciente.intValue());
            pstmt.setInt(2, cuestionario.intValue());
            pstmt.setInt(3, pregunta.intValue());
            pstmt.setInt(4, secuencia.intValue());
            
            rs = pstmt.executeQuery();
            
            if (rs.next()) {
            	if (rs.getString(2) == null) {
            		ret = rs.getString(1);
            	} else {
            		ret = rs.getString(2);
            	}
            }
		} catch (Exception e) {
	        logger.log(Level.SEVERE, "getRespuestaEnfControl - sql = " + sql, e);
		} finally {
		    try {
				if (pstmt != null) {
					pstmt.close();
				}
					
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException exClose) {
			    logger.log(Level.SEVERE, "getRespuestaEnfControl - while closing rs and pstmt objects = ", exClose);
			}
		}

        return ret;
    }

    /**
     * Respuestas desde EnfContro con Proyecto
     * */
    public static String getRespuestaEnfControl (Integer proyecto, Integer pregunta, Integer cuestionario, Integer secuencia, Integer medico) throws Exception{
        
    	String ret = "";
        StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
	   
  
		sql.append("	select ac.respuesta, ac.TEXTBINARY")    
		   .append("	from exme_enfcontrolresp ac")    
		   .append("	where ac.exme_proyecto_id = ?") 
		   .append("	and ac.exme_cuestionario_id = ?")    
		   .append("	and ac.exme_pregunta_id = ?")    
		   .append("	and ac.secuencia = ?")
		   .append("	and ac.exme_medico_id = ?")
		   .append("	order by ac.created desc");
     
 		PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            pstmt = DB.prepareStatement(sql.toString(), null);
            pstmt.setInt(1, proyecto.intValue());
            pstmt.setInt(2, cuestionario.intValue());
            pstmt.setInt(3, pregunta.intValue());
            pstmt.setInt(4, secuencia.intValue());
            pstmt.setInt(5, medico.intValue());
            
            rs = pstmt.executeQuery();
            
            if (rs.next()) {
            	if (rs.getString(2) == null) {
            		ret = rs.getString(1);
            	} else {
            		ret = rs.getString(2);
            	}
            }
		} catch (Exception e) {
	        logger.log(Level.SEVERE, "getRespuestaEnfControl - sql = " + sql, e);
		} finally {
		    try {
				if (pstmt != null) {
					pstmt.close();
				}
					
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException exClose) {
			    logger.log(Level.SEVERE, "getRespuestaEnfControl - while closing rs and pstmt objects = ", exClose);
			}
		}

        return ret;
    }


    public static String getComentarioEnfControl (Integer paciente, Integer pregunta, 
		 Integer cuestionario, Integer cita, Integer cta_PAC, Integer secuencia) throws Exception {
        
    	String ret = "";

        StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
	   
  
        sql.append("	select ac.description")    
		    .append("	from exme_enfcontrolresp ac")    
		    .append("	where ac.exme_paciente_id = ?") 
		    .append("	and ac.exme_cuestionario_id = ?")    
		    .append("	and ac.exme_pregunta_id = ?")
		    .append("	and ac.secuencia = ?")
		    .append("	order by ac.created desc");
     
 		PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            pstmt = DB.prepareStatement(sql.toString(), null);
            pstmt.setInt(1, paciente.intValue());
            pstmt.setInt(2, cuestionario.intValue());
            pstmt.setInt(3, pregunta.intValue());
            pstmt.setInt(4, secuencia.intValue());
            
            rs = pstmt.executeQuery();
            
            if (rs.next()) {
            	ret = rs.getString(1);
            }
		} catch (Exception e) {
	        logger.log(Level.SEVERE, "getComentarioEnfControl - sql = " + sql, e);
		} finally {
		    try {
				if (pstmt != null) {
					pstmt.close();
				}
					
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException exClose) {
			    logger.log(Level.SEVERE, "getComentarioEnfControl - while closing rs and pstmt objects = ", exClose);
			}
		}

        return ret;
    }
 
	/**
	 * Comentarios desde enf Control con Proyecto
	 **/
   	public static String getComentarioEnfControl (Integer proyecto, Integer pregunta, Integer cuestionario, 
			 Integer secuencia, Integer medico) throws Exception {
     
	 	String ret = "";
	    StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);

		sql.append("	select ac.description")    
		  .append("	from exme_enfcontrolresp ac")    
		  .append("	where ac.exme_proyecto_id = ?") 
		  .append("	and ac.exme_cuestionario_id = ?")    
		  .append("	and ac.exme_pregunta_id = ?")
		  .append("	and ac.secuencia = ?")
		  .append("	and ac.exme_medico_id = ?")
		  .append("	order by ac.created desc");
	  
		PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
           pstmt = DB.prepareStatement(sql.toString(), null);
           pstmt.setInt(1, proyecto.intValue());
           pstmt.setInt(2, cuestionario.intValue());
           pstmt.setInt(3, pregunta.intValue());
           pstmt.setInt(4, secuencia.intValue());
           pstmt.setInt(5, medico.intValue());
           
           rs = pstmt.executeQuery();
           
           if (rs.next()) {
         	   ret = rs.getString(1);
           }
		} catch (Exception e) {
	        logger.log(Level.SEVERE, "getComentarioEnfControl - sql = " + sql, e);
		} finally {
		    try {
				if (pstmt != null) {
					pstmt.close();
				}
					
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException exClose) {
			    logger.log(Level.SEVERE, "getComentarioEnfControl - while closing rs and pstmt objects = ", exClose);
			}
		}
        return ret;
    }
 
    public static String getRutaImagenEnfControl (Integer paciente, Integer pregunta, 
    		Integer cuestionario, Integer cita, Integer cta_PAC, Integer secuencia) throws Exception{
     
    	String ret = "";

    	StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
	   
		sql.append("	select ac.rutaimagen")    
		 .append("	from exme_enfcontrolresp ac")    
		 .append("	where ac.exme_paciente_id = ?") 
		 .append("	and ac.exme_cuestionario_id = ?")    
		 .append("	and ac.exme_pregunta_id = ?")
		 .append("	and ac.secuencia = ?")
		 .append("	order by ac.created desc");
  
		PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            pstmt = DB.prepareStatement(sql.toString(), null);
            pstmt.setInt(1, paciente.intValue());
            pstmt.setInt(2, cuestionario.intValue());
            pstmt.setInt(3, pregunta.intValue());
            pstmt.setInt(4, secuencia.intValue());
         
            rs = pstmt.executeQuery();
         
            if (rs.next()) {
         	   ret = rs.getString(1);
            }
		} catch (Exception e) {
	        logger.log(Level.SEVERE, "getRutaImagenEnfControl - sql = " + sql, e);
		} finally {
		    try {
				if (pstmt != null) {
					pstmt.close();
				}
					
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException exClose) {
			    logger.log(Level.SEVERE, "getRutaImagenEnfControl - while closing rs and pstmt objects = ", exClose);
			}
		}
        return ret;
    }
 
	 /**Obtiene la ruta de imagen desde Enf Control con Proyecto
	  * 
	  * @param paciente
	  * @param pregunta
	  * @param cuestionario
	  * @param cita
	  * @param cta_PAC
	  * @param secuencia
	  * @return
	  * @throws Exception
	  */
    
    public static String getRutaImagenEnfControl (Integer proyecto, Integer pregunta, Integer cuestionario, 
	    Integer secuencia, Integer medico) throws Exception {
     
	 	String ret = "";
	    StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);

	 	sql.append("	select ac.rutaimagen")    
		 .append("	from exme_enfcontrolresp ac")    
		 .append("	where ac.exme_proyecto_id = ?") 
		 .append("	and ac.exme_cuestionario_id = ?")    
		 .append("	and ac.exme_pregunta_id = ?")	 
		 .append("	and ac.secuencia = ?")
		 .append("	and ac.exme_medico_id = ?")
		 .append("	order by ac.created desc");
	  
		PreparedStatement pstmt = null;
	    ResultSet rs = null;

	    try {
	        pstmt = DB.prepareStatement(sql.toString(), null);
	        pstmt.setInt(1, proyecto.intValue());
	        pstmt.setInt(2, cuestionario.intValue());
	        pstmt.setInt(3, pregunta.intValue());
	        pstmt.setInt(4, secuencia.intValue());
	        pstmt.setInt(5, medico.intValue());
	         
	        rs = pstmt.executeQuery();
	         
	        if (rs.next()) {
	         	ret = rs.getString(1);
	        }
		} catch (Exception e) {
	        logger.log(Level.SEVERE, "getRutaImagenEnfControl - sql = " + sql, e);
		} finally {
		    try {
				if (pstmt != null) {
					pstmt.close();
				}
					
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException exClose) {
			    logger.log(Level.SEVERE, "getRutaImagenEnfControl - while closing rs and pstmt objects = ", exClose);
			}
		}
	    return ret;
    }
 
    public static boolean getRespuestasMulti (Integer paciente, Integer pregunta, Integer cuestionario, 
    		Integer cita, Integer cta_PAC, Integer secuencia, Integer ListaID) throws Exception {
        
    	boolean ret = false;

        StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
   
		sql.append("	select m.*")
		   .append("    from exme_enfcontrolresp c")
		   .append("    inner join exme_enfcontrolmultiresp m on(c.exme_enfcontrolresp_id = m.exme_enfcontrolresp_id)")
		   .append("    where c.exme_paciente_id = ? ")
		   .append("    and c.exme_cuestionario_id = ?")
		   .append("    and c.exme_pregunta_id = ?")
		   .append("    and m.exme_pregunta_lista_id = ?");
             
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            pstmt = DB.prepareStatement(sql.toString(), null);
            pstmt.setInt(1, paciente.intValue());
            pstmt.setInt(2, cuestionario.intValue());
            pstmt.setInt(3, pregunta.intValue());
            pstmt.setInt(4, ListaID.intValue());
            
            rs = pstmt.executeQuery();
            
            if(rs.next()) {
            	
            	ret = true;
            	 
            }
		} catch (Exception e) {
		    logger.log(Level.SEVERE, "getRespuestasMulti - sql = " + sql, e);
		} finally {
		    try {
				if (pstmt != null) {
					pstmt.close();
				}
				
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException exClose) {
			    logger.log(Level.SEVERE, "getRespuestasMulti - while closing rs and pstmt objects = ", exClose);
			}
		}

        return ret;
    }
    
    /**
     * Obtiene multiples respuestas con Proyecto
     * @param proyecto
     * @param pregunta
     * @param cuestionario
     * @param secuencia
     * @param ListaID
     * @return
     * @throws Exception
     */
    public static boolean getRespuestasMulti (Integer proyecto, Integer pregunta, Integer cuestionario, Integer secuencia, Integer ListaID) throws Exception{
        
    	boolean ret = false;

        StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
   
		sql.append("	select m.*")
		   .append("    from exme_enfcontrolresp c")
		   .append("    inner join exme_enfcontrolmultiresp m on(c.exme_enfcontrolresp_id = m.exme_enfcontrolresp_id)")
		   .append("    where c.exme_proyecto_id = ? ")
		   .append("    and c.exme_cuestionario_id = ?")
		   .append("    and c.exme_pregunta_id = ?")
		   .append("    and m.exme_pregunta_lista_id = ?");
             
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            pstmt = DB.prepareStatement(sql.toString(), null);
            pstmt.setInt(1, proyecto.intValue());
            pstmt.setInt(2, cuestionario.intValue());
            pstmt.setInt(3, pregunta.intValue());
            pstmt.setInt(4, ListaID.intValue());
            
            rs = pstmt.executeQuery();
            
            if (rs.next()) {
            	ret = true;
            }
		 } catch (Exception e) {
			 logger.log(Level.SEVERE, "getRespuestasMulti - sql = " + sql, e);
		 } finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				
				if (rs != null) {
					rs.close();
				}
			 } catch (SQLException exClose) {
				 logger.log(Level.SEVERE, "getRespuestasMulti - while closing rs and pstmt objects = ", exClose);
			 }
		 }

        return ret;
    }
    
    public static boolean existRespuestasMulti (Integer paciente, Integer pregunta, Integer cuestionario, Integer cita, Integer cta_PAC, Integer secuencia) throws Exception{
        
    	boolean ret = false;

        StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
   
		sql.append("	select c.*")
		   .append("    from exme_enfcontrolresp c")
		   .append("    inner join exme_enfcontrolmultiresp m on(c.exme_enfcontrolresp_id = m.exme_enfcontrolresp_id)")
		   .append("    where c.exme_paciente_id = ? ")
		   .append("    and c.exme_cuestionario_id = ?")
		   .append("    and c.exme_pregunta_id = ?");
             
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            pstmt = DB.prepareStatement(sql.toString(), null);
            pstmt.setInt(1, paciente.intValue());
            pstmt.setInt(2, cuestionario.intValue());
            pstmt.setInt(3, pregunta.intValue());
            
            rs = pstmt.executeQuery();
            
            if (rs.next()) {
            	ret = true;
            }
		 } catch (Exception e) {
			 logger.log(Level.SEVERE, "existRespuestasMulti - sql = " + sql, e);
		 } finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				
				if (rs != null) {
					rs.close();
				}
			 } catch (SQLException exClose) {
				 logger.log(Level.SEVERE, "existRespuestasMulti - while closing rs and pstmt objects = ", exClose);
			 }
		 }

        return ret;
    }

	/**
	 * Existen respuestas multi de proyecto
	 * 
	 * @param proyecto
	 * @param pregunta
	 * @param cuestionario
	 * @param secuencia
	 * @return
	 * @throws Exception
	 */
	public static boolean existRespuestasMulti (Integer proyecto, Integer pregunta, Integer cuestionario, Integer secuencia) throws Exception{
	    
		boolean ret = false;
	
	    StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
	
		sql.append("	select c.*")
		.append("    from exme_enfcontrolresp c")
		.append("    inner join exme_enfcontrolmultiresp m on(c.exme_enfcontrolresp_id = m.exme_enfcontrolresp_id)")
		.append("    where c.exme_proyecto_id = ? ")
		.append("    and c.exme_cuestionario_id = ?")
		.append("    and c.exme_pregunta_id = ?");
	
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	
	    try {
	        pstmt = DB.prepareStatement(sql.toString(), null);
	        pstmt.setInt(1, proyecto.intValue());
	        pstmt.setInt(2, cuestionario.intValue());
	        pstmt.setInt(3, pregunta.intValue());
	                
	        rs = pstmt.executeQuery();
	        
	        if (rs.next()) {        	
	        	ret = true;        	 
	        }
		 } catch (Exception e) {
			 logger.log(Level.SEVERE, "existRespuestasMulti - sql = " + sql, e);
		 } finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				
				if (rs != null) {
					rs.close();
				}
			 } catch (SQLException exClose) {
				 logger.log(Level.SEVERE, "existRespuestasMulti - while closing rs and pstmt objects = ", exClose);
			 }
		 }
	
	    return ret;
	}

    public static String getComentario (Integer paciente, Integer pregunta, 
    		Integer cuestionario, Integer cita, Integer cta_PAC, Integer secuencia) throws Exception{
        
    	String ret = "";

        StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
	   
        sql.append("	select dt.description")
           .append("    from exme_actpaciente ac")
           .append("    inner join exme_actpacientedet dt on(ac.exme_actpaciente_id = dt.exme_actpaciente_id)")
           .append("    where ac.exme_paciente_id = ?")
           .append("    and dt.exme_cuestionario_id = ?")
           .append("    and dt.exme_pregunta_id = ?");
        
        if (cita != null && cita.intValue() > 0) {
    	    sql.append("    and ac.exme_citamedica_id is not null");
    	} else {
    	    sql.append("    and ac.exme_citamedica_id is null");
        }
        
        if (cta_PAC != null && cta_PAC.intValue() > 0) {
    	    sql.append("    and ac.exme_ctapac_id is not null");
        } else {
    	    sql.append("    and ac.exme_ctapac_id is null");
        }
      
        sql.append(" and dt.secuencia = ? ");// secuencia para preg. repetidas .- Lama
      
        sql.append("    order by dt.created desc");
             
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            pstmt = DB.prepareStatement(sql.toString(), null);
            pstmt.setInt(1, paciente.intValue());
            pstmt.setInt(2, cuestionario.intValue());
            pstmt.setInt(3, pregunta.intValue());
            pstmt.setInt(4, secuencia.intValue());
            
            rs = pstmt.executeQuery();
            
            if(rs.next()) {
            	
            	ret = rs.getString(1);
            	 
            }
		 } catch (Exception e) {
			 logger.log(Level.SEVERE, "getComentario - sql = " + sql, e);
		 } finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				
				if (rs != null) {
					rs.close();
				}
			 } catch (SQLException exClose) {
				 logger.log(Level.SEVERE, "getComentario - while closing rs and pstmt objects = ", exClose);
			 }
		 }

        return ret;
    }
    
    public static boolean existCuestionario (Integer paciente, Integer cuestionario, 
    		Integer cita, Integer cta_PAC) throws Exception {
        
    	boolean ret = false;
        StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
	   
	    sql.append("	select dt.respuesta")
	       .append("    from exme_actpaciente ac")
	       .append("    inner join exme_actpacientedet dt on(ac.exme_actpaciente_id = dt.exme_actpaciente_id)")
	       .append("    where ac.exme_paciente_id = ?")
	       .append("    and dt.exme_cuestionario_id = ?");
      
        if (cita != null && cita.intValue() > 0) {
    	    sql.append("    and ac.exme_citamedica_id is not null");
        } else {
    	    sql.append("    and ac.exme_citamedica_id is null");
        }
        
        if (cta_PAC != null && cta_PAC.intValue() > 0) {
    	    sql.append("    and ac.exme_ctapac_id is not null");
        } else {
    	    sql.append("    and ac.exme_ctapac_id is null");
        }
        
        sql.append("    order by dt.created desc");
             
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            pstmt = DB.prepareStatement(sql.toString(), null);
            pstmt.setInt(1, paciente.intValue());
            pstmt.setInt(2, cuestionario.intValue());
            
            rs = pstmt.executeQuery();
            
            if (rs.next()) {
            	ret = true;
            }
		 } catch (Exception e) {
			 logger.log(Level.SEVERE, "existCuestionario - sql = " + sql, e);
		 } finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				
				if (rs != null) {
					rs.close();
				}
			 } catch (SQLException exClose) {
				 logger.log(Level.SEVERE, "existCuestionario - while closing rs and pstmt objects = ", exClose);
			 }
		 }

        return ret;
    }
    
    
    public static boolean existCuestionarioMulti (Integer paciente, Integer cuestionario, 
    		Integer cita, Integer cta_PAC) throws Exception{
    	boolean ret = false;

        StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
	   
        sql.append("	select ac.respuesta")    
        .append("	from exme_enfcontrolresp ac")    
        .append("	where ac.exme_paciente_id = ?") 
        .append("	and ac.exme_cuestionario_id = ?")    
        .append("	order by ac.created desc");
      
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            pstmt = DB.prepareStatement(sql.toString(), null);
            pstmt.setInt(1, paciente.intValue());
            pstmt.setInt(2, cuestionario.intValue());
            
            rs = pstmt.executeQuery();
            
            if (rs.next()) {
            	ret = true;
            }
		 } catch (Exception e) {
			 logger.log(Level.SEVERE, "existCuestionarioMulti - sql = " + sql, e);
		 } finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				
				if (rs != null) {
					rs.close();
				}
			 } catch (SQLException exClose) {
				 logger.log(Level.SEVERE, "existCuestionarioMulti - while closing rs and pstmt objects = ", exClose);
			 }
		 }

        return ret;
    }
 
	 /*
	  * 
	  * Busqueda de Cuestionarios por Proyecto y medico
	  * */
	 public static boolean existCuestionarioProyecto (Integer proyecto, Integer cuestionario, Integer medico) throws Exception{
	     
	 	boolean ret = false;
	
	     StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		   
	     sql.append("	select ac.respuesta")    
	     .append("	from exme_enfcontrolresp ac")    
	     .append("	where ac.exme_proyecto_id = ?") 
	     .append("	and ac.exme_cuestionario_id = ?")
	     .append("	and ac.exme_medico_id = ?")  
	     .append("	order by ac.created desc");
	   
	         
	     PreparedStatement pstmt = null;
	     ResultSet rs = null;
	
	     try {
	         pstmt = DB.prepareStatement(sql.toString(), null);
	         pstmt.setInt(1, proyecto.intValue());
	         pstmt.setInt(2, cuestionario.intValue());
	         pstmt.setInt(3, medico.intValue());
	         
	         rs = pstmt.executeQuery();
	         
	         if(rs.next()) {
	         	ret = true;
	         }
		 } catch (Exception e) {
			 logger.log(Level.SEVERE, "existCuestionarioProyecto - sql = " + sql, e);
		 } finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				
				if (rs != null) {
					rs.close();
				}
			 } catch (SQLException exClose) {
				 logger.log(Level.SEVERE, "existCuestionarioProyecto - while closing rs and pstmt objects = ", exClose);
			 }
		 }
	
	     return ret;
    }


	public static String[] getRespuestaComentarioFolio(Integer folio, Integer pregunta, Integer cuestionario, Integer secuencia, Integer user)
			throws Exception {

		String[] ret = new String[4];

		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);

		sql.append("	   select t.respuesta, t.description, t.Pregunta_Lista_Value, t.rutaImagen, t.TEXTBINARY ")
			.append("    from exme_t_cuest").append("_".concat(user.toString())).append(" t")
			.append("    where t.folio = ?");
		
		if (cuestionario != null && cuestionario.intValue() > 0) {
			sql.append("    and t.exme_cuestionario_id = ?");
	    } else {
			sql.append("    and t.exme_cuestionario_id is null ");
	    }
		
		sql.append("    and t.exme_pregunta_id = ?")
			.append("    and t.secuencia = ?")
			.append("    and t.isActive = 'Y'");
		           
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
		
			int i = 1;
			pstmt.setInt(i, folio.intValue());
			if (cuestionario != null && cuestionario.intValue() > 0) {
				pstmt.setInt(++i, cuestionario.intValue());
			}

			pstmt.setInt(++i, pregunta.intValue());
			pstmt.setInt(++i, secuencia.intValue());

			rs = pstmt.executeQuery();

			if (rs.next()) {
				
				if(rs.getString(5) == null) {
					ret[0] = rs.getString(1);// respuesta Texto Normal
				} else {
					ret[0] = rs.getString(5);// respuesta TextoBinario
				}
				
				ret[1] = rs.getString(2); // comentario
				ret[2] = rs.getString(3);// para el combo, id de respuesta
				ret[3] = rs.getString(4);// ruta de la imagen
			}
			
           
		 } catch (Exception e) {
			 logger.log(Level.SEVERE, "getRespuestaComentarioFolio - sql = " + sql, e);
		 } finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				
				if (rs != null) {
					rs.close();
				}
			 } catch (SQLException exClose) {
				 logger.log(Level.SEVERE, "getRespuestaComentarioFolio - while closing rs and pstmt objects = ", exClose);
			 }
		 }

		return ret;
	}
	

	public static String[] getRespuestaComentarioFolioForms(Integer folio, Integer pregunta, Integer cuestionario, Integer actPaciente)
			throws Exception {

		String[] ret = new String[4];

		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		
	 sql.append("	   SELECT TD.respuesta, ")
		.append("	   TD.description, ")
		.append("	   TD.Pregunta_Lista_Value, ")
		.append("	   TD.rutaImagen, ")
		.append("	   TD.TEXTBINARY ")
		.append("	   FROM exme_ACTPACIENTE t ")
		.append("	   INNER JOIN EXME_ACTPACIENTEDET TD ON t.EXME_ACTPACIENTE_ID = TD.EXME_ACTPACIENTE_ID ")
		.append("	   WHERE t.EXME_ACTPACIENTE_ID              = ? ")
		.append("	   AND TD.folio = ? ")
		.append("	   AND TD.exme_pregunta_id     = ? ")
		.append("	   and TD.exme_cuestionario_id = ? ")
		.append("	   AND TD.isActive             = 'Y' ");
		           
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setInt(1, actPaciente.intValue());
			pstmt.setInt(2, folio.intValue());
			pstmt.setInt(3, pregunta.intValue());
			pstmt.setInt(4, cuestionario.intValue());

			rs = pstmt.executeQuery();

			if (rs.next()) {
				
				if(rs.getString(5) == null) {
					ret[0] = rs.getString(1);// respuesta Texto Normal
				} else {
					ret[0] = rs.getString(5);// respuesta TextoBinario
				}
				
				ret[1] = rs.getString(2); // comentario
				ret[2] = rs.getString(3);// para el combo, id de respuesta
				ret[3] = rs.getString(4);// ruta de la imagen
			}
			
           
		 } catch (Exception e) {
			 logger.log(Level.SEVERE, "getRespuestaComentarioFolio - sql = " + sql, e);
		 } finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				
				if (rs != null) {
					rs.close();
				}
			 } catch (SQLException exClose) {
				 logger.log(Level.SEVERE, "getRespuestaComentarioFolio - while closing rs and pstmt objects = ", exClose);
			 }
		 }

		return ret;
	}
	
	public static ArrayList<Integer> getRespuestaComentarioFolioMulti(Integer folio, Integer pregunta, Integer cuestionario, Integer secuencia, Integer user)
			throws Exception {
		
		ArrayList<Integer> ret = new ArrayList<Integer>();
		
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		
		sql.append("	   select t.Pregunta_Lista_Value, t.REF_EXME_PREGUNTA_ID ")
			.append("    from exme_t_cuest").append("_".concat(user.toString())).append(" t")
			.append("    where t.folio = ?");
		
		if (cuestionario != null && cuestionario.intValue() > 0) {
			sql.append("    and t.exme_cuestionario_id = ?");
		} else {
			sql.append("    and t.exme_cuestionario_id is null ");
		}
		
		sql.append("    and t.exme_pregunta_id = ?")
		   .append("    and t.ref_exme_pregunta_id = ?")
			.append("    and t.secuencia = ?")
			.append("    and t.isActive = 'Y'");
		           
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
		
			int i = 1;
			pstmt.setInt(i, folio.intValue());
			if (cuestionario != null && cuestionario.intValue() > 0) {
				pstmt.setInt(++i, cuestionario.intValue());
			}
		
			pstmt.setInt(++i, pregunta.intValue());
			pstmt.setInt(++i, pregunta.intValue());
			pstmt.setInt(++i, secuencia.intValue());
		
			rs = pstmt.executeQuery();
		
			while (rs.next()) {
				ret.add(rs.getInt(1));				
			}
			
		   
		 } catch (Exception e) {
			 logger.log(Level.SEVERE, "getRespuestaComentarioFolio - sql = " + sql, e);
		 } finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				
				if (rs != null) {
					rs.close();
				}
			 } catch (SQLException exClose) {
				 logger.log(Level.SEVERE, "getRespuestaComentarioFolio - while closing rs and pstmt objects = ", exClose);
			 }
		 }
		
		return ret;
		}

	
		public static ArrayList<Integer> getRespuestasFolioECE_Multi(Integer paciente, Integer cuestionario, Integer folio, Integer ActPac, Integer pregID)
			throws Exception {
		
		ArrayList<Integer> ret = new ArrayList<Integer>();
		
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		
		 sql.append("	   select ")
			.append("	   A.Pregunta_Lista_Value,")//1
			.append("	   A.REF_EXME_PREGUNTA_ID")//2
			.append("	   FROM EXME_ACTPACIENTEDET A")
			.append("	   inner join exme_pregunta p on (A.exme_pregunta_id = p.exme_pregunta_id) ")
			.append("	   inner join exme_tipopregunta tp on (p.exme_tipopregunta_id=tp.exme_tipopregunta_id) ")
			.append("	   WHERE A.Exme_actpaciente_id =? ")
			.append("	   AND A.Folio = ? ")
			.append("	   and A.exme_cuestionario_id=? ")
			.append("	   and p.exme_pregunta_id = ? ")
			.append("	   and A.ref_exme_pregunta_id = ? ")
			.append("	   ORDER BY A.Secuencia, tp.name  ");
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;

        try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setInt(1, ActPac.intValue());
			pstmt.setInt(2, folio == null ? 0 : folio.intValue());
			pstmt.setInt(3, cuestionario.intValue());
			pstmt.setInt(4, pregID);
			pstmt.setInt(5, pregID);
			
			rs = pstmt.executeQuery();
		
			while (rs.next()) {
				ret.add(rs.getInt(1));				
			}
			
		   
		 } catch (Exception e) {
			 logger.log(Level.SEVERE, "getRespuestaComentarioFolio - sql = " + sql, e);
		 } finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				
				if (rs != null) {
					rs.close();
				}
			 } catch (SQLException exClose) {
				 logger.log(Level.SEVERE, "getRespuestaComentarioFolio - while closing rs and pstmt objects = ", exClose);
			 }
		 }
		
		return ret;
		}

	
	
	public static String getRutaImagen (Integer paciente, Integer pregunta, 
			Integer cuestionario, Integer cita, Integer cta_PAC, Integer secuencia) 
	        throws Exception{
	  	String ret = "";

        StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
     
	    sql.append("	select dt.rutaimagen")
	      .append("    from exme_actpaciente ac")
	      .append("    inner join exme_actpacientedet dt on(ac.exme_actpaciente_id = dt.exme_actpaciente_id)")
	      .append("    where ac.exme_paciente_id = ?")
	      .append("    and dt.exme_cuestionario_id = ?")
	      .append("    and dt.exme_pregunta_id = ?");
	    if (cita != null && cita.intValue() > 0) {
	  	    sql.append("    and ac.exme_citamedica_id is not null");
	    } else {
	  	    sql.append("    and ac.exme_citamedica_id is null");
	    }
	    
	    if (cta_PAC != null && cta_PAC.intValue() > 0) {
	  	    sql.append("    and ac.exme_ctapac_id is not null");
	    } else {
	  	  sql.append("    and ac.exme_ctapac_id is null");
	    }
	    
	    sql.append(" and dt.secuencia = ? ");// secuencia para preg. repetidas .- Lama
	           
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;

		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setInt(1, paciente.intValue());
			pstmt.setInt(2, cuestionario.intValue());
			pstmt.setInt(3, pregunta.intValue());
			pstmt.setInt(4, secuencia.intValue());

			rs = pstmt.executeQuery();

			if (rs.next()) {
				ret = rs.getString(1);
			}
		 } catch (Exception e) {
			 logger.log(Level.SEVERE, "getRutaImagen - sql = " + sql, e);
		 } finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				
				if (rs != null) {
					rs.close();
				}
			 } catch (SQLException exClose) {
				 logger.log(Level.SEVERE, "getRutaImagen - while closing rs and pstmt objects = ", exClose);
			 }
		 }
		 return ret;
	}    

 /******************* consultar cuestionarios *****************/
 
 
 /**
  * @author Lama
  * @param paciente
  * @param cuestionario
  * @param lstPreguntas
  * @param folio
  * @return
  * @throws Exception
  */
  public static ArrayList<Pregunta_VO> getPreguntasCuestionarioFolioAct (Integer paciente, Integer cuestionario, ArrayList<Pregunta_VO> lstPreguntas, Integer folio) throws Exception{
      //lista con las preguntas
      //ArrayList<Pregunta_VO> lstPreguntas = new ArrayList<Pregunta_VO>();

      StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
	 
      sql.append("	select")
         .append(" 	cu.EXME_Pregunta_ID,")
         .append(" 	p.Name,")
         .append(" 	p.TipoDato,") 
         .append(" 	p.exme_tipopregunta_id,")
         .append(" 	tp.name,")
         .append(" 	cu.EXME_Cuestionario_ID,") 
         .append(" 	cu.Obligatoria,")
         .append(" 	cu.Secuencia")
         .append(" 	from EXME_CuestionarioDt cu") 
         .append(" 	inner join exme_pregunta p on (cu.exme_pregunta_id = p.exme_pregunta_id)")
         .append(" 	inner join exme_tipopregunta tp on (p.exme_tipopregunta_id=tp.exme_tipopregunta_id)")
         .append(" 	where cu.exme_cuestionario_id=?")
         .append("    ORDER BY cu.Secuencia, tp.name ");

		PreparedStatement pstmt = null;
		ResultSet rs = null;

      try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setInt(1, cuestionario.intValue());
			rs = pstmt.executeQuery();
			Pregunta_VO preg = null;
			while (rs.next()) {

				preg = new Pregunta_VO();

				preg.setId(rs.getInt(1));
				preg.setName(rs.getString(2));
				preg.setTipoDato(rs.getString(3));
				preg.setTipoPregunta(rs.getInt(4));
				preg.setTipoPreguntaTxt(rs.getString(5));
				preg.setCuestionarioId(cuestionario);
				preg.setObligatoriaTxt(rs.getString(7));
				preg.setSecuencia(rs.getInt(8));
				preg.setLstReglas(MEXMEReglaCuestionario.getPreguntasCuestionario(preg.getId(), preg.getCuestionarioId()));
				if (preg.getTipoDato().equalsIgnoreCase(MPregunta.TIPODATO_OptionList)) {
					preg.setRespuestas(MPregunta_Lista.getListaRespuestas(preg.getId()));
				}
				//if ("Y" != null && "Y".equalsIgnoreCase("Y")) {
					String[] respuesta = MPregunta.getRespuestaFolioAct(folio, preg.getId(), preg.getCuestionarioId(),preg.getSecuencia());
					if (respuesta != null) {
						if (preg.getTipoDato().equalsIgnoreCase(MPregunta.TIPODATO_Image)) {
							preg.setRutaImagen(respuesta[2]);
						} else if (!preg.getTipoDato().equalsIgnoreCase(MPregunta.TIPODATO_Image)) {
							preg.setRespuesta(respuesta[0]);
							preg.setComentario(respuesta[1]);
						}
					}
				//}
				preg.setIndice(lstPreguntas.size());
				lstPreguntas.add(preg);
			}

		 } catch (Exception e) {
			 logger.log(Level.SEVERE, "getPreguntasCuestionarioFolioAct - sql = " + sql, e);
		 } finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				
				if (rs != null) {
					rs.close();
				}
			 } catch (SQLException exClose) {
				 logger.log(Level.SEVERE, "getPreguntasCuestionarioFolioAct - while closing rs and pstmt objects = ", exClose);
			 }
		 }

		return lstPreguntas;
	}
  
  public static ArrayList<Pregunta_VO> getPreguntasCuestionarioFolioActECE (Integer paciente, Integer cuestionario, ArrayList<Pregunta_VO> lstPreguntas, Integer folio, Integer ActPac) throws Exception{
      //lista con las preguntas
      //ArrayList<Pregunta_VO> lstPreguntas = new ArrayList<Pregunta_VO>();

      StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
	  
       sql.append(" 	select")
	      .append(" 	A.EXME_Pregunta_ID,")//1
	      .append(" 	p.Name,")//2
	      .append(" 	p.TipoDato,")//3
	      .append(" 	p.exme_tipopregunta_id,")//4
	      .append(" 	tp.name,")//5
	      .append(" 	A.EXME_Cuestionario_ID,")//6
	      .append(" 	A.EXME_Cuestionario_ID,")//7
	      .append(" 	A.Secuencia,")//8
	      // Se modifica c�digo para tomar en cuenta la respuesta de texto amplio. Jesus Cantu.
	      // Cuando existe una de texto amplio se muestra esta ya que guarda 4000, de lo contrario la respuesta normal.
	      .append(" (case when A.textbinary is not null ")//9
	   	  .append(" then to_char(A.textbinary) ")//9
	   	  .append(" else to_char(A.respuesta) end ) as Respuesta, ")//9
	      //.append(" 	A.Respuesta,")//9
	      .append(" 	A.Rutaimagen,")//10
	      .append(" 	A.Description")//11
	      .append(" 	FROM EXME_ACTPACIENTEDET A")
	      .append(" 	inner join exme_pregunta p on (A.exme_pregunta_id = p.exme_pregunta_id)")
	      .append(" 	inner join exme_tipopregunta tp on (p.exme_tipopregunta_id=tp.exme_tipopregunta_id)")
	      .append(" 	WHERE A.Exme_actpaciente_id =?")
	      .append(" 	AND A.Folio = ?")
	      .append(" 	and A.exme_cuestionario_id=?")
	      .append(" 	and A.ref_exme_pregunta_id is null")
	      .append(" 	ORDER BY A.Secuencia, tp.name ");

		PreparedStatement pstmt = null;
		ResultSet rs = null;

        try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setInt(1, ActPac.intValue());
			pstmt.setInt(2, folio == null ? 0 : folio.intValue());
			pstmt.setInt(3, cuestionario.intValue());
			rs = pstmt.executeQuery();
			Pregunta_VO preg = null;
			while (rs.next()) {
				preg = new Pregunta_VO();
				preg.setId(rs.getInt(1));
				preg.setName(rs.getString(2));
				preg.setTipoDato(rs.getString(3));
				preg.setTipoPregunta(rs.getInt(4));
				preg.setTipoPreguntaTxt(rs.getString(5));
				preg.setCuestionarioId(cuestionario);
				preg.setObligatoriaTxt("N");
				preg.setSecuencia(rs.getInt(8));
				preg.setLstReglas(MEXMEReglaCuestionario.getPreguntasCuestionarioForms(preg.getId(), preg.getCuestionarioId()));
				if (preg.getTipoDato().equalsIgnoreCase(MPregunta.TIPODATO_OptionList) || preg.getTipoDato().equalsIgnoreCase(MPregunta.TIPODATO_MultiOptions)) {
					preg.setRespuestas(MPregunta_Lista.getListaRespuestas(preg.getId()));
				}
						
				if (!preg.getTipoDato().equalsIgnoreCase(MPregunta.TIPODATO_Image) && 
						!preg.getTipoDato().equalsIgnoreCase(MPregunta.TIPODATO_FixedImage) ) {
        			 preg.setRespuesta(rs.getString(9));
        			 preg.setComentario(rs.getString(11));
        		 } else if (preg.getTipoDato().equalsIgnoreCase(MPregunta.TIPODATO_Image)) {
            		 preg.setRutaImagen(rs.getString(10));
            		 preg.setComentario(rs.getString(11));	                		 
        		 } else if (preg.getTipoDato().equalsIgnoreCase(MPregunta.TIPODATO_FixedImage)) {
        			 //Liz delaGarza-Cambiar extension del nombre del archivo a PNG(Visualizaci�n Expediente)
        			 if (rs.getString(10) != null && rs.getString(10).endsWith("tif")) {
        			 	String ruta = StringUtils.substringBeforeLast(rs.getString(10), ".");
        			 	preg.setRutaImagen(ruta + ".png");
        			 } else {
        				preg.setRutaImagen(rs.getString(10));
        			 }
            		 preg.setComentario(rs.getString(11));	            			 
            	 } 
				
				if (preg.getTipoDato().equalsIgnoreCase(MPregunta.TIPODATO_OptionList)) {
        			 for (int j = 0; j < preg.getRespuestas().size(); j++) {
        				 if (preg.getRespuestas().get(j).getDescripcion().equalsIgnoreCase(preg.getRespuesta())) {
        					 preg.getRespuestas().get(j).setSelected(SELECTED);
        					 j = preg.getRespuestas().size();
        				 }
        			 }
        		 } else if (preg.getTipoDato().equalsIgnoreCase(MPregunta.TIPODATO_MultiOptions)) {
					ArrayList<Integer> respuestasMulti = MPregunta.getRespuestasFolioECE_Multi(paciente, cuestionario, folio, ActPac, preg.getId());
					for(Integer obj : respuestasMulti){
						for(int i=0; i<preg.getRespuestas().size(); i++ ){
							if(((RespuestaList_VO)preg.getRespuestas().get(i)).getListaID().intValue() == obj.intValue()){
								((RespuestaList_VO)preg.getRespuestas().get(i)).setSeleccionado(true);
								break;
							}
						}
					}
				}
				
				
				preg.setIndice(lstPreguntas.size());
				lstPreguntas.add(preg);
			}
		} catch (Exception e) {
			logger.log(Level.SEVERE, "MPregunta.getPreguntasCuestionarioFolioActECE", e);
		} finally {
			if (pstmt != null) {
				pstmt.close();
			}
			
			if (rs != null) {
				rs.close();
			}
		}

		return lstPreguntas;
	}
  
  public static ArrayList<Pregunta_VO> getCuestionarioActECEForms (Integer paciente, Integer cuestionario, ArrayList<Pregunta_VO> lstPreguntas, Integer folio, Integer ActPac) throws Exception{
      //lista con las preguntas
      //ArrayList<Pregunta_VO> lstPreguntas = new ArrayList<Pregunta_VO>();

      StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
	  
      sql.append("	select")
      .append(" 	cu.EXME_Pregunta_ID, ")//1
      .append(" 	p.Name, ")//2
      .append(" 	p.TipoDato, ")//3 
      .append(" 	p.exme_tipopregunta_id, ")//4
      .append(" 	tp.name, ")//5
      .append(" 	cu.EXME_Cuestionario_ID, ")//6
      .append(" 	cu.Obligatoria, ")//7
      .append(" 	cu.Secuencia   ")//8

      .append(" 	from EXME_CuestionarioDt cu") 
      .append(" 	inner join exme_pregunta p on (cu.exme_pregunta_id = p.exme_pregunta_id)")
      .append(" 	inner join exme_tipopregunta tp on (p.exme_tipopregunta_id=tp.exme_tipopregunta_id)")

      .append(" 	where cu.exme_cuestionario_id=?")
      .append("    ORDER BY cu.Secuencia, tp.name ");

      PreparedStatement pstmt = null;
      ResultSet rs = null;

        try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setInt(1, cuestionario.intValue());
			
			rs = pstmt.executeQuery();
			Pregunta_VO preg = null;
			while (rs.next()) {
				
				preg = new Pregunta_VO();
				preg.setId(rs.getInt(1));
				preg.setName(rs.getString(2));
				preg.setTipoDato(rs.getString(3));
				preg.setTipoPregunta(rs.getInt(4));
				preg.setTipoPreguntaTxt(rs.getString(5));
				preg.setCuestionarioId(cuestionario);
				preg.setObligatoriaTxt("N");
				preg.setSecuencia(rs.getInt(8));
				preg.setLstReglas(MEXMEReglaCuestionario.getPreguntasCuestionarioForms(preg.getId(), preg.getCuestionarioId()));
				if (preg.getTipoDato().equalsIgnoreCase(MPregunta.TIPODATO_OptionList) || preg.getTipoDato().equalsIgnoreCase(MPregunta.TIPODATO_MultiOptions)) {
					preg.setRespuestas(MPregunta_Lista.getListaRespuestas(preg.getId()));
				}
				
				
				if (!preg.getTipoDato().equalsIgnoreCase(MPregunta.TIPODATO_MultiOptions)) {
					String[] respuesta = MPregunta.getRespuestaComentarioFolioForms(folio, preg.getId(), preg.getCuestionarioId(), ActPac);
					if (respuesta != null) {
						if (preg.getTipoDato().equalsIgnoreCase(MPregunta.TIPODATO_Image)) {
							// preg.setRutaImagen(MPregunta.getRutaImagenFolio(folio, preg.getId(), preg.getCuestionarioId()));
							preg.setRutaImagen(respuesta[3]);// rutaimagen

						} else if (!preg.getTipoDato().equalsIgnoreCase(MPregunta.TIPODATO_Image)) {
							if (preg.getTipoDato().equalsIgnoreCase(MPregunta.TIPODATO_Logial)) {
								preg.setRespuestaL(respuesta[0].equals("Y") ? true : false);
							}
							
							preg.setRespuesta(respuesta[0]); //respuesta
							preg.setComentario(respuesta[1]);// description
							if (preg.getTipoDato().equalsIgnoreCase(MPregunta.TIPODATO_OptionList)) {
								preg.setRespuestaCombo(respuesta[2]); //pregunta_lista_value
							}
						}
					}
				}else if (preg.getTipoDato().equalsIgnoreCase(MPregunta.TIPODATO_MultiOptions)) {
					ArrayList<Integer> respuestasMulti = MPregunta.getRespuestasFolioECE_Multi(paciente, cuestionario, folio, ActPac, preg.getId());
					for(Integer obj : respuestasMulti){
						for(int i=0; i<preg.getRespuestas().size(); i++ ){
							if(((RespuestaList_VO)preg.getRespuestas().get(i)).getListaID().intValue() == obj.intValue()){
								((RespuestaList_VO)preg.getRespuestas().get(i)).setSeleccionado(true);
								break;
							}
						}
					}
				}
				
				
				preg.setIndice(lstPreguntas.size());
				lstPreguntas.add(preg);
			
			}
		} catch (Exception e) {
			logger.log(Level.SEVERE, "MPregunta.getPreguntasCuestionarioFolioActECE", e);
		} finally {
			if (pstmt != null) {
				pstmt.close();
			}
			
			if (rs != null) {
				rs.close();
			}
		}

		return lstPreguntas;
	}

  
	/**
	 * @author Lorena Lama
	 * @param folio
	 * @param pregunta
	 * @param cuestionario
	 * @param secuencia
	 * @return
	 * @throws Exception
	 */
	public static String[] getRespuestaFolioAct(Integer folio, Integer pregunta, 
			Integer cuestionario, Integer secuencia)
			throws Exception {

		String[] ret = new String[3];

		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);

		sql.append("	   select t.respuesta, t.DESCRIPTION, t.rutaImagen, t.TEXTBINARY ")
			.append("    from EXME_ActPacienteDet t")
			.append("    where t.folio = ?");
		if (cuestionario != null && cuestionario > 0) {
			sql.append("    and t.exme_cuestionario_id = ?");
		} else {
			sql.append("    and t.exme_cuestionario_id is null ");
		}
		
		sql.append("    and t.exme_pregunta_id = ?")
			.append("    and t.secuencia = ?") // secuencia para preg. repetidas .- Lama
			.append("    and t.isActive = 'Y'");

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			int i = 1;
			pstmt.setInt(i, folio.intValue());
			if (cuestionario != null && cuestionario > 0) {
				pstmt.setInt(++i, cuestionario.intValue());
			}
			
			pstmt.setInt(++i, pregunta.intValue());
			pstmt.setInt(++i, secuencia.intValue());

			rs = pstmt.executeQuery();

			if (rs.next()) {
				if(rs.getString(4) == null) {
					ret[0] = rs.getString(1);
				} else {
					ret[0] = rs.getString(4);
				}
				
				ret[1] = rs.getString(2);
				ret[2] = rs.getString(3);
			}
		 } catch (Exception e) {
			 logger.log(Level.SEVERE, "getRespuestaFolioAct - sql = " + sql, e);
		 } finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				
				if (rs != null) {
					rs.close();
				}
			 } catch (SQLException exClose) {
				 logger.log(Level.SEVERE, "getRespuestaFolioAct - while closing rs and pstmt objects = ", exClose);
			 }
		 }

		return ret;
	}
	
	
	/**
	 * Metodo para recuperar las respuestas del cuestionario
	 * configurado como Historia Clinica
	 * @param pacienteID
	 * @param cuestionarioID
	 * @author Gustavo Derreza
	 * **/
	public static boolean existCuestionarioHC (Integer paciente, Integer cuestionario) throws Exception {

		boolean ret = false;

		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);

		sql.append(" SELECT dt.respuesta")
		.append(" FROM EXME_ActPaciente ac")
		.append(" INNER JOIN EXME_ActPacienteDet dt on(ac.EXME_ActPaciente_ID = dt.EXME_ActPaciente_ID)")
		.append(" WHERE ac.EXME_Paciente_ID = ?")
		.append(" AND dt.EXME_Cuestionario_ID = ?")
		.append(" ORDER BY dt.created desc");

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setInt(1, paciente.intValue());
			pstmt.setInt(2, cuestionario.intValue());
			
			rs = pstmt.executeQuery();

			if (rs.next()) {
				ret = true;
			}
		 } catch (Exception e) {
			 logger.log(Level.SEVERE, "existCuestionarioHC - sql = " + sql, e);
		 } finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				
				if (rs != null) {
					rs.close();
				}
			 } catch (SQLException exClose) {
				 logger.log(Level.SEVERE, "existCuestionarioHC - while closing rs and pstmt objects = ", exClose);
			 }
		 }

		return ret;
	}
	
	
	/**Obtiene las observaciones del cuestionario anterior guardado
	  * 	
	  * @param proyecto
	  * @param cuestionario
	  * @param medico
	  * @return observaciones
	  * @throws Exception
	  */
	    
	 public static String getObservacionCuest(Integer proyecto, Integer cuestionario, Integer medico) throws Exception {
	     
		 String ret = "";
		 StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
			   
		 sql.append("	select ac.observaciones")    
			 .append("	from exme_enfcontrolcuest ac")    
			 .append("	where ac.exme_proyecto_id = ?") 
			 .append("	and ac.exme_cuestionario_id = ?")    			 
			 .append("	and ac.exme_medico_id = ?");
		  
		 PreparedStatement pstmt = null;
		 ResultSet rs = null;
	
	     try {
	         pstmt = DB.prepareStatement(sql.toString(), null);
	         pstmt.setInt(1, proyecto.intValue());
	         pstmt.setInt(2, cuestionario.intValue());
	         pstmt.setInt(3, medico.intValue());
	         
	         rs = pstmt.executeQuery();
	         
	         if (rs.next()) {		         	
	         	ret = rs.getString(1);		         	 
	         }		        
		 } catch (Exception e) {
			 logger.log(Level.SEVERE, "getObservacionCuest - sql = " + sql, e);
		 } finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				
				if (rs != null) {
					rs.close();
				}
			 } catch (SQLException exClose) {
				 logger.log(Level.SEVERE, "getObservacionCuest - while closing rs and pstmt objects = ", exClose);
			 }
		 }
		 return ret;
	 }
}    