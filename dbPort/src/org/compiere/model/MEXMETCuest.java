/*

 * Created on 04-may-2005

 */

package org.compiere.model;

import ij.io.TiffEncoder;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import javax.servlet.http.HttpServletRequest;

import oracle.sql.BLOB;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Trx;
import org.compiere.util.WebEnv;
import org.compiere.util.WebSessionCtx;
import org.compiere.util.cuestionarios.Pregunta_VO;
import org.compiere.util.cuestionarios.RespuestaList_VO;

/**
 * Modelo para tabla de guardado tempora de ejecucion de cuestionarios.
 * 
 * @author
 */

public class MEXMETCuest extends X_EXME_T_Cuest {

	// seria id
	private static final long serialVersionUID = -6322686211159454572L;
	private static CLogger s_log = CLogger.getCLogger(MEXMETCuest.class);
	private static String ImagenFilePath = "";
	private static String filePath = "";

	public static final int TypeAppointment = 1;
	public static final int TypeEncounterForm = 2;
	public static final int TypeProgQuiro = 3;
	public static final String TMP_QUESTIONARY_SUFFIX = "EXME_T_CUEST_";
	/**
	 * @param ctx
	 * @param EXME_T_Cuest_ID
	 * @param trxName
	 */

	public MEXMETCuest(Properties ctx, String trxName) {

		super(ctx, trxName);
	}

	/**
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MEXMETCuest(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	/**
	 * Verificamos si la cuenta tiene al menos 1 linea
	 * 
	 * @return True si tiene al menos 1 linea, false si no.
	 * @throws SQLException
	 */

	public static List<MEXMETCuest> getByCitaMedica(Properties ctx, int citaMedicaId, String trxName, Integer user) throws Exception {

		List<MEXMETCuest> retValue = new ArrayList<MEXMETCuest>();

		StringBuilder sql = new StringBuilder("Select * from EXME_T_Cuest").append("_".concat(user.toString())).append("  where EXME_CitaMedica_ID = ").append(citaMedicaId).append(" and isActive = 'Y' ");

		sql.append("order by secuencia ");
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				retValue.add(new MEXMETCuest(ctx, rs, null));
			}

		} catch (Exception e) {
			s_log.log(Level.SEVERE, "getByCitaMedica", e);

			throw new Exception("error.citas.getCuestionarios");
		} finally {
			DB.close(rs, pstmt);

		}

		return retValue;

	}

	/**
	 * Obtener cuestionario temporal por medio de la cita medica
	 * 
	 * @param ctx
	 *            Contexto
	 * @param cuestId
	 *            Cuestionario
	 * @param recordId
	 *            Cita Medica
	 * @param multioption
	 *            Preguntas o no de multi respuesta
	 * @param typeRecord 1 = cita 2 = encounterform 3 = progquiro
	 *            Si es cita o encounter o quirofanos
	 * @param trxName
	 *            Trx Name
	 * @param user
	 *            Usuario
	 * @return
	 */
	public static List<MEXMETCuest> getBy(Properties ctx, int cuestId, int recordId, boolean multioption, int typeRecord, int osID, String trxName, Integer user) {
		List<MEXMETCuest> retValue = new ArrayList<MEXMETCuest>();

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ");
		sql.append("  * ");
		sql.append("FROM ");
		sql.append("  EXME_T_Cuest_").append(user);
		sql.append(" WHERE ");
		
		 switch(typeRecord) {
		 case 1: 
			 sql.append("  exme_citamedica_id = ? ");
		     break;
		 case 2: 
			 sql.append("  EXME_EncounterForms_ID = ? ");
		     break;
		 case 3: 
			 sql.append("  EXME_PROGQUIRO_ID = ? ");
		     break;
		 }
			
		
		sql.append(" AND ");
		sql.append("  exme_cuestionario_id = ? ");

		if (multioption) {
			sql.append(" AND REF_EXME_PREGUNTA_ID > 0 ");
		} else {
			sql.append(" AND REF_EXME_PREGUNTA_ID = 0 ");
		}
		
		if(osID > 0)
			sql.append(" AND EXME_OrderSet_ID = ").append(osID);
		else if(osID == 0)
			sql.append(" AND ( EXME_OrderSet_ID is null or EXME_OrderSet_ID = ").append(0).append(" ) ") ;
		else if(osID == -2)
			sql.append(" AND exme_orderset_id is not null and exme_orderset_id > 0 ");
		
		
		sql.append("order by secuencia ");
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setInt(1, recordId);
			pstmt.setInt(2, cuestId);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				retValue.add(new MEXMETCuest(ctx, rs, null));
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, null, e);
		} finally {
			DB.close(rs, pstmt);
		}

		return retValue;
	}

	/**
	 * Obtener cuestionario temporal por medio de la cita medica
	 * 
	 * @param ctx
	 *            Contexto
	 * @param Cita Medica ID
	 *            
	 * @param orderSetID
	 *            
	 * @param trxName
	 *            Trx Name
	 * @param user
	 *            Usuario
	 * @return
	 */
	public static boolean isUsingOrderSets(Properties ctx, int citaID, int OrderSetID, String trxName, Integer user) {
		boolean retValue = false;

		StringBuilder sql = new StringBuilder();
				
		sql.append(" (SELECT   tc.exme_orderset_id "); 
		sql.append(" FROM EXME_T_Cuest_").append(user).append(" tc ");
		sql.append(" WHERE   tc.exme_citamedica_id = ? ");  
		sql.append(" AND   tc.exme_orderSet_id = ? ");
		sql.append(" group by tc.exme_orderset_id) ");				  
		sql.append(" union  ");
		sql.append(" (select rc.exme_orderset_id ");
		sql.append(" from exme_respuestacuestionario rc ");
		sql.append(" where rc.ad_table_id = (select ad_table_id from ad_table where tablename = 'EXME_CitaMedica' ) ");
		sql.append(" and rc.record_id = ? ");
		sql.append(" and rc.exme_orderset_id = ? ");
		sql.append(" group by rc.exme_orderset_id ");
		sql.append(" ) ");
		
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setInt(1, citaID);
			pstmt.setInt(2, OrderSetID);
			pstmt.setInt(3, citaID);
			pstmt.setInt(4, OrderSetID);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				retValue = true;
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, null, e);
		} finally {
			DB.close(rs, pstmt);
		}

		return retValue;
	}
	
	/**
	 * Obtener cuestionario temporal por medio de la cita medica
	 * 
	 * @param ctx
	 *            Contexto
	 * @param Cita Medica ID
	 *            
	 * @param orderSetID
	 *            
	 * @param trxName
	 *            Trx Name
	 * @param user
	 *            Usuario
	 * @return
	 */
	public static List<Integer> usedOrderSets(Properties ctx, int citaID, String trxName, Integer user) {
		List<Integer> retValue = new ArrayList<Integer>();

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ");
		sql.append("  exme_orderset_id ");
		sql.append("FROM ");
		sql.append("  EXME_T_Cuest_").append(user);
		sql.append(" WHERE ");
		sql.append("  exme_citamedica_id = ? ");
		sql.append(" AND ");
		sql.append("  exme_orderSet_id = not null ");
		sql.append(" AND ");
		sql.append("  exme_orderSet_id > 0 ");
		sql.append(" group by exme_orderset_id "); 
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setInt(1, citaID);			

			rs = pstmt.executeQuery();

			while (rs.next()) {
				retValue.add(rs.getInt(1));
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, null, e);
		} finally {
			DB.close(rs, pstmt);
		}

		return retValue;
	}
	
	public static List<MEXMETCuest> getByCitaMedicaForms(Properties ctx, int formsID, int cuestID, String trxName, Integer user) throws Exception {

		List<MEXMETCuest> retValue = new ArrayList<MEXMETCuest>();

		StringBuilder sql = new StringBuilder("Select * from EXME_T_Cuest").append("_".concat(user.toString())).append("  where EXME_EncounterForms_ID = ").append(formsID);
		if (cuestID > 0)
			sql.append("  and EXME_Cuestionario_ID = ").append(cuestID);

		sql.append(" and isActive = 'Y' ");

		sql.append("order by secuencia ");
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				retValue.add(new MEXMETCuest(ctx, rs, null));
			}

		} catch (Exception e) {
			s_log.log(Level.SEVERE, "getByCitaMedica", e);

			throw new Exception("error.citas.getCuestionarios");
		} finally {
			DB.close(rs, pstmt);
		}

		return retValue;

	}

	/**
	 * Obtenemos lista de detalles de cuestionario guardados en tabla temporal
	 * 
	 * @param pacienteId
	 * @return Lista de MEXMECuest.
	 */

	public static List<MEXMETCuest> getByPaciente(Properties ctx, int pacienteId, String trxName, Integer user) {

		List<MEXMETCuest> retValue = new ArrayList<MEXMETCuest>();

		StringBuilder sql = new StringBuilder("Select * from EXME_T_Cuest").append("_".concat(user.toString())).append("  where EXME_Paciente_ID = ").append(pacienteId).append(" and isActive = 'Y' ");

		// sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, sql.toString(), "EXME_T_Cuest"));

		sql.append("order by secuencia ");

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			pstmt = DB.prepareStatement(sql.toString(), null);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				retValue.add(new MEXMETCuest(ctx, rs, null));
			}

		} catch (SQLException e) {
			s_log.log(Level.SEVERE, "getByPaciente", e);
			return null;
		} finally {
			DB.close(rs, pstmt);

		}

		return retValue;

	}

	// TODO: Comentar Metodo y Cambios en el mismo.
	@SuppressWarnings("unused")
	public static void delActPacienteTemp1x1(Properties ctx, int citaMedicaId, int pacienteId, String trxName) throws Exception {
		List<MEXMETCuest> tCuestList = null;

		if (citaMedicaId > 0) {
			tCuestList = getByCitaMedica(ctx, citaMedicaId, trxName, new Integer(Env.getAD_User_ID(ctx)));
		} else if (pacienteId > 0) {
			tCuestList = getByPaciente(ctx, pacienteId, trxName, new Integer(Env.getAD_User_ID(ctx)));
		}

		Iterator<MEXMETCuest> i = tCuestList.iterator();

		while (i.hasNext()) {
			MEXMETCuest o = i.next();
			// o.delete(true);
		}
	}

	/**
	 * Eliminar los registros de preguntas de la tabla temporal y tipo de pregunta
	 * 
	 * @return El resultset con los tipos de pregunta
	 */
	public static void delActPacienteTemp(Properties ctx, int citaMedicaId, int pacienteId, String trxName) throws Exception {
		// Proceso de borrado a traves del modelo creando los objetos que corresponden a cada registro el BD.
		if (citaMedicaId > 0) {
			delActPacienteTemp1x1(ctx, citaMedicaId, 0, trxName);
		} else if (pacienteId > 0) {
			delActPacienteTemp1x1(ctx, 0, pacienteId, trxName);
		}
	}

	public static String getRespuesta(Properties ctx, int citaMedicaId, int preguntaId, String trxName, Integer user) throws Exception {

		String retValue = null;
		StringBuilder sql = new StringBuilder(" Select * from EXME_T_Cuest").append("_".concat(user.toString())).append("  where EXME_CitaMedica_ID = ").append(citaMedicaId).append(" and EXME_Pregunta_ID = ").append(preguntaId);

		// sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, sql.toString(), "EXME_T_Cuest"));

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				retValue = rs.getString("Respuesta");
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, "getRespuesta", e);

			throw new Exception("error.citas.noInsertEjec");
		} finally {
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}

		return retValue;
	}

	/**
	 * Obtener las respuestas de una pregunta en la tabla temporal (Lama)
	 * 
	 * @param ctx
	 * @param citaMedicaId
	 * @param preguntaId
	 * @param trxName
	 * @return
	 * @throws Exception
	 */
	public static MEXMETCuest getRespuestaCom(Properties ctx, int citaMedicaId, int preguntaId, String trxName, Integer user) throws Exception {

		MEXMETCuest retValue = null;
		StringBuilder sql = new StringBuilder(" Select * from EXME_T_Cuest").
				append("_".concat(user.toString())).append(" where EXME_CitaMedica_ID = ")
				.append(citaMedicaId).append(" and EXME_Pregunta_ID = ").append(preguntaId);

		// sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, sql.toString(), "EXME_T_Cuest"));

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				retValue = new MEXMETCuest(ctx, rs, trxName);
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, "getRespuestaCom", e);

			throw new Exception("error.citas.noInsertEjec");
		} finally {
			DB.close(rs, pstmt);

		}

		return retValue;
	}

	/**
	 * Busca preguntas de referencia con el id indicado
	 * @param ctx
	 * @param refExmePreguntaId
	 * @param typeRecordID
	 * @param typeRecord
	 * @param trxName
	 * @param user
	 * @return
	 */
	public static List<MEXMETCuest> getRefQuestions(Properties ctx, int refExmePreguntaId, int typeRecordID,int typeRecord, String trxName, Integer user){
		List<MEXMETCuest> retValue = new ArrayList<MEXMETCuest>();

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ");
		sql.append("  * ");
		sql.append("FROM ");
		sql.append("  EXME_T_Cuest_").append(user);
		sql.append(" WHERE ");
		
		 switch(typeRecord) {
		 case 1: 
			 sql.append("  exme_citamedica_id = ? ");
		     break;
		 case 2: 
			 sql.append("  EXME_EncounterForms_ID = ? ");
		     break;
		 case 3: 
			 sql.append("  EXME_PROGQUIRO_ID = ? ");
		     break;
		 }
			
		
		sql.append(" AND REF_EXME_PREGUNTA_ID = ? ");
		
		sql.append("order by secuencia ");
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setInt(1, typeRecordID);
			pstmt.setInt(2, refExmePreguntaId);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				retValue.add(new MEXMETCuest(ctx, rs, null));
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, null, e);
		} finally {
			DB.close(rs, pstmt);
		}

		return retValue;
	}
	
	/**
	 * 
	 * @param ctx
	 * @param map
	 * @param medico
	 * @param paciente
	 * @param folio
	 * @param com
	 * @param cita
	 * @return
	 * @throws Exception
	 */
	public static void insertaActPacienteDetTemp(Properties ctx, ArrayList<Pregunta_VO> map, Integer medico, Integer paciente, Integer folio, String com, Integer cita, int EXME_Enfermeria_ID, boolean esCuestionario, String trxName) throws Exception {

		s_log.log(Level.INFO, "***** Insertando en EXME_T_Cuest ***** ");

		try {

			Iterator<Pregunta_VO> itrValor = map.iterator();
			MEXMETCuest tcuest = null;
			Pregunta_VO vo = null;

			while (itrValor.hasNext()) {
				vo = (Pregunta_VO) itrValor.next();

				// int ID = vo.getExme_t_cuest_ID() == null ? 0 : vo.getExme_t_cuest_ID().intValue();

				tcuest = new MEXMETCuest(ctx, trxName);

				tcuest.setFecha(DB.getTimestampForOrg(ctx));
				tcuest.setEXME_Paciente_ID(paciente.intValue());
				if (medico != null)
					tcuest.setEXME_Medico_ID(medico.intValue());

				tcuest.setEXME_EstServ_ID(Env.getContextAsInt(ctx, "#EXME_EstServ_ID"));
				if (cita != null && cita.intValue() > 0) {
					tcuest.setEXME_CitaMedica_ID(cita.intValue());

				}
				tcuest.setDescription("");

				if (vo.getCuestionarioId() != null && esCuestionario)
					tcuest.setEXME_Cuestionario_ID(vo.getCuestionarioId().intValue());

				tcuest.setEXME_Pregunta_ID(vo.getId());
				tcuest.setFolio(folio.intValue());
				tcuest.setEsNotaMedica(true);

				if (vo.getTipoDato().equalsIgnoreCase(MPregunta.TIPODATO_Image) && (vo.getRespuestaI() != null || vo.getByteArr() != null)) {
					String fileName = vo.saveImagen();
					tcuest.setRespuesta(fileName);
					tcuest.setRutaImagen(fileName);

				} else {

					if (vo.getTipoDato().equalsIgnoreCase(MPregunta.TIPODATO_OptionList) && "0".equalsIgnoreCase(vo.getRespuestaCombo())) {
						tcuest.setRespuesta(null);
					} else {
						tcuest.setRespuesta(vo.getRespuesta());
					}

					if (vo.getTipoDato().equalsIgnoreCase(MPregunta.TIPODATO_TextArea))
						tcuest.setTextBinary(vo.getRespuesta());
				}

				if (vo.getTipoDato().equalsIgnoreCase(MPregunta.TIPODATO_OptionList) && vo.getRespuestaCombo() != null) {
					tcuest.setPregunta_Lista_Value(vo.getRespuestaCombo());
				}
				tcuest.setConfidencial(com != null ? com : "T");

				if (vo.getSecuencia() != null) {
					tcuest.setSecuencia(new BigDecimal(vo.getSecuencia().toString()));
				} else {
					tcuest.setSecuencia(new BigDecimal("0"));
				}

				if (vo.getComentario() != null) {
					tcuest.setDescription(vo.getComentario());
				}

				if (EXME_Enfermeria_ID > 0)
					tcuest.setEXME_Enfermeria_ID(EXME_Enfermeria_ID);

				if (vo.getTipoDato().equalsIgnoreCase(MPregunta.TIPODATO_FixedImage)) {

					tcuest.setRespuesta(vo.getName()); // nombre de la pregunta

					CuestionarioModel cuest = new CuestionarioModel(ctx, null);
					// actualizar ID en nombre de archivo (throws exception)
					int tCuestID = tcuest.getEXME_T_Cuest_ID();
					int pregID = vo.getId() != null ? vo.getId().intValue() : 0;
					int seq = vo.getSecuencia() != null ? vo.getSecuencia().intValue() : 0;
					int cuestID = vo.getCuestionarioId() != null ? vo.getCuestionarioId().intValue() : 0;

					tcuest.setRutaImagen(cuest.updateLine(tCuestID, pregID, cuestID, seq));
				}

				if (!tcuest.save(trxName, new Integer(Env.getAD_User_ID(ctx)))) {
					throw new Exception("error.cuest.guardar");
				} else {
					if (vo.getTipoDato().equalsIgnoreCase(MPregunta.TIPODATO_MultiOptions)) {
						for (RespuestaList_VO voo : vo.getRespuestasMulti()) {
							if (voo.isSeleccionado()) {
								tcuest.setREF_EXME_PREGUNTA_ID(tcuest.getEXME_Pregunta_ID());
								tcuest.setPregunta_Lista_Value(voo.getListaID().toString());

								if (!tcuest.save(trxName, new Integer(Env.getAD_User_ID(ctx)))) {
									throw new Exception("error.cuest.guardar");
								}
							}
						}
					}

					if (vo.getTipoDato().equalsIgnoreCase(MPregunta.TIPODATO_FixedImage) && tcuest.getRutaImagen() != null) {

						CuestionarioModel cuest = new CuestionarioModel(ctx, null);
						// actualizar ID en nombre de archivo (throws exception)
						int tCuestID = tcuest.getEXME_T_Cuest_ID();
						int pregID = vo.getId() != null ? vo.getId().intValue() : 0;
						int seq = vo.getSecuencia() != null ? vo.getSecuencia().intValue() : 0;
						int cuestID = vo.getCuestionarioId() != null ? vo.getCuestionarioId().intValue() : 0;
						// recupera la imagen temporal a insertar
						InputStream input = cuest.getFile(tcuest.getRutaImagen());// (throws exception)
						MEXMETCuest.updateImagen(ctx, MEXMETCuest.Table_Name, "ImagenBinary", tCuestID, paciente.intValue(), medico.intValue(), cuestID, pregID, seq, input, trxName, Env.getAD_User_ID(ctx));

					}
				}
			}

		} catch (Exception e) {
			s_log.log(Level.SEVERE, "insertaActPacienteDetTemp", e);
			throw e;
		}

	}

	public static void insertaActPacienteTempForms(Properties ctx, ArrayList<Pregunta_VO> map, Integer medico, Integer paciente, Integer folio, String com, Integer formaID, int EXME_Enfermeria_ID, boolean esCuestionario, String trxName) throws Exception {

		s_log.log(Level.INFO, "***** Insertando en EXME_T_Cuest ***** ");

		try {

			Iterator<Pregunta_VO> itrValor = map.iterator();
			MEXMETCuest tcuest = null;
			Pregunta_VO vo = null;

			while (itrValor.hasNext()) {
				vo = (Pregunta_VO) itrValor.next();

				// int ID = vo.getExme_t_cuest_ID() == null ? 0 : vo.getExme_t_cuest_ID().intValue();

				tcuest = new MEXMETCuest(ctx, trxName);

				tcuest.setFecha(DB.getTimestampForOrg(ctx));
				tcuest.setEXME_Paciente_ID(paciente.intValue());
				if (medico != null)
					tcuest.setEXME_Medico_ID(medico.intValue());

				tcuest.setEXME_EstServ_ID(Env.getContextAsInt(ctx, "#EXME_EstServ_ID"));
				if (formaID != null && formaID.intValue() > 0) {
					tcuest.setEXME_EncounterForms_ID(formaID.intValue());

				}
				tcuest.setDescription("");

				if (vo.getCuestionarioId() != null && esCuestionario)
					tcuest.setEXME_Cuestionario_ID(vo.getCuestionarioId().intValue());

				tcuest.setEXME_Pregunta_ID(vo.getId());
				tcuest.setFolio(folio);
				tcuest.setEsNotaMedica(true);

				if (vo.getTipoDato().equalsIgnoreCase(MPregunta.TIPODATO_Image) && (vo.getRespuestaI() != null || vo.getByteArr() != null)) {
					String fileName = vo.saveImagen();
					tcuest.setRespuesta(fileName);
					tcuest.setRutaImagen(fileName);

				} else {

					if (vo.getTipoDato().equalsIgnoreCase(MPregunta.TIPODATO_OptionList) && "0".equalsIgnoreCase(vo.getRespuestaCombo())) {
						tcuest.setRespuesta(null);
					} else {
						tcuest.setRespuesta(vo.getRespuesta());
					}

					if (vo.getTipoDato().equalsIgnoreCase(MPregunta.TIPODATO_TextArea))
						tcuest.setTextBinary(vo.getRespuesta());
				}

				if (vo.getTipoDato().equalsIgnoreCase(MPregunta.TIPODATO_OptionList) && vo.getRespuestaCombo() != null) {
					tcuest.setPregunta_Lista_Value(vo.getRespuestaCombo());
				}
				tcuest.setConfidencial(com != null ? com : "T");

				if (vo.getSecuencia() != null) {
					tcuest.setSecuencia(new BigDecimal(vo.getSecuencia().toString()));
				} else {
					tcuest.setSecuencia(new BigDecimal("0"));
				}

				if (vo.getComentario() != null) {
					tcuest.setDescription(vo.getComentario());
				}

				if (EXME_Enfermeria_ID > 0)
					tcuest.setEXME_Enfermeria_ID(EXME_Enfermeria_ID);

				if (vo.getTipoDato().equalsIgnoreCase(MPregunta.TIPODATO_FixedImage)) {

					tcuest.setRespuesta(vo.getName()); // nombre de la pregunta

					CuestionarioModel cuest = new CuestionarioModel(ctx, null);
					// actualizar ID en nombre de archivo (throws exception)
					int tCuestID = tcuest.getEXME_T_Cuest_ID();
					int pregID = vo.getId() != null ? vo.getId().intValue() : 0;
					int seq = vo.getSecuencia() != null ? vo.getSecuencia().intValue() : 0;
					int cuestID = vo.getCuestionarioId() != null ? vo.getCuestionarioId().intValue() : 0;

					tcuest.setRutaImagen(cuest.updateLine(tCuestID, pregID, cuestID, seq));
				}

				if (!tcuest.save(trxName, new Integer(Env.getAD_User_ID(ctx)))) {
					throw new Exception("error.cuest.guardar");
				} else {
					if (vo.getTipoDato().equalsIgnoreCase(MPregunta.TIPODATO_MultiOptions)) {
						for (RespuestaList_VO voo : vo.getRespuestasMulti()) {
							if (voo.isSeleccionado()) {
								tcuest.setREF_EXME_PREGUNTA_ID(tcuest.getEXME_Pregunta_ID());
								tcuest.setPregunta_Lista_Value(voo.getListaID().toString());
								tcuest.setRespuesta(voo.getValorTxt());
								if (!tcuest.save(trxName, new Integer(Env.getAD_User_ID(ctx)))) {
									throw new Exception("error.cuest.guardar");
								}
							}
						}
					}

					if (vo.getTipoDato().equalsIgnoreCase(MPregunta.TIPODATO_FixedImage) && tcuest.getRutaImagen() != null) {

						CuestionarioModel cuest = new CuestionarioModel(ctx, null);
						// actualizar ID en nombre de archivo (throws exception)
						int tCuestID = tcuest.getEXME_T_Cuest_ID();
						int pregID = vo.getId() != null ? vo.getId().intValue() : 0;
						int seq = vo.getSecuencia() != null ? vo.getSecuencia().intValue() : 0;
						int cuestID = vo.getCuestionarioId() != null ? vo.getCuestionarioId().intValue() : 0;
						// recupera la imagen temporal a insertar
						InputStream input = cuest.getFile(tcuest.getRutaImagen());// (throws exception)
						MEXMETCuest.updateImagen(ctx, MEXMETCuest.Table_Name, "ImagenBinary", tCuestID, paciente.intValue(), medico.intValue(), cuestID, pregID, seq, input, trxName, Env.getAD_User_ID(ctx));

					}
				}
			}

		} catch (Exception e) {
			s_log.log(Level.SEVERE, "insertaActPacienteDetTemp", e);
			throw e;
		}

	}

	public static ActionErrors insertaActPacienteDetTempMulti(Properties ctx, ArrayList<Pregunta_VO> map, Integer medico, Integer paciente, Integer folio, String com, Integer cita, Integer Enf) throws SQLException {
		s_log.log(Level.INFO, "***** Insertando en insertaActPacienteDetTempMulti ***** ");
		ActionErrors errores = new ActionErrors();

		Trx m_trx = null;
		String trxName = null;

		try {

			m_trx = Trx.get(Trx.createTrxName("ECD"), true);
			trxName = m_trx.getTrxName();

			Iterator<Pregunta_VO> itrValor = map.iterator();
			MEXMEEnfControlResp cResp = null;
			Pregunta_VO vo = null;
			// ArrayList<Pregunta_VO> valor = null;

			while (itrValor.hasNext()) {
				vo = (Pregunta_VO) itrValor.next();

				int ID = vo.getExme_t_cuest_ID() == null ? 0 : vo.getExme_t_cuest_ID().intValue();

				cResp = new MEXMEEnfControlResp(ctx, ID, trxName);

				cResp.setFecha(DB.getTimestampForOrg(ctx));
				cResp.setEXME_Paciente_ID(paciente.intValue());
				cResp.setEXME_Medico_ID(medico.intValue());
				cResp.setEXME_EnfControlada_ID(Enf.intValue());
				cResp.setEXME_EstServ_ID(Env.getContextAsInt(ctx, "#EXME_EstServ_ID"));
				if (cita != null && cita.intValue() > 0) {
					cResp.setEXME_CitaMedica_ID(cita.intValue());

				}
				cResp.setDescription("");

				if (vo.getCuestionarioId() != null)
					cResp.setEXME_Cuestionario_ID(vo.getCuestionarioId().intValue());

				cResp.setEXME_Pregunta_ID(vo.getId());
				cResp.setFolio(folio.intValue());
				cResp.setEsNotaMedica(true);

				if (vo.getTipoDato().equalsIgnoreCase(MPregunta.TIPODATO_OptionList) && vo.getRespuestaCombo() != null) {
					cResp.setPregunta_Lista_Value(vo.getRespuestaCombo());
					cResp.setRespuesta(vo.getRespuesta());
				}

				if (vo.getSecuencia() != null) {
					cResp.setSecuencia(new BigDecimal(vo.getSecuencia().toString()));
				} else {
					cResp.setSecuencia(new BigDecimal("0"));
				}

				if (vo.getComentario() != null) {
					cResp.setDescription(vo.getComentario());
				}

				if (vo.getTipoDato().equalsIgnoreCase(MPregunta.TIPODATO_Image) && (vo.getRespuestaI() != null || vo.getByteArr() != null)) {
					String fileName = vo.saveImagen();
					cResp.setRespuesta(fileName);
					cResp.setRutaImagen(fileName);

					if (!cResp.save(trxName)) {
						errores.add("SQLException", new ActionError("error.citas.noInsertEjec"));
						Trx.rollback(m_trx);
						break;
					}

				} else if (!vo.getTipoDato().equalsIgnoreCase(MPregunta.TIPODATO_Image) && !vo.getTipoDato().equalsIgnoreCase(MPregunta.TIPODATO_MultiOptions)) {

					cResp.setRespuesta(vo.getRespuesta());

					if (!cResp.save(trxName)) {
						errores.add("SQLException", new ActionError("error.citas.noInsertEjec"));
						Trx.rollback(m_trx);
						break;
					}

				} else if (vo.getTipoDato() != null && vo.getTipoDato().equalsIgnoreCase(MPregunta.TIPODATO_MultiOptions)) {

					if (!cResp.save(trxName)) {
						errores.add("SQLException", new ActionError("error.citas.noInsertEjec"));
						Trx.rollback(m_trx);

					} else {

						MEXMEEnfControlMultiResp pregM = null;

						for (int i = 0; i < vo.getRespuestas().size(); i++) {
							if (vo.getRespuestas().get(i).isSeleccionado()) {
								pregM = new MEXMEEnfControlMultiResp(ctx, 0, trxName);
								pregM.setEXME_EnfControlResp_ID(cResp.getEXME_EnfControlResp_ID());

								if (vo.getCuestionarioId() != null)
									pregM.setEXME_Cuestionario_ID(vo.getCuestionarioId().intValue());

								pregM.setEXME_Pregunta_ID(vo.getId().intValue());
								pregM.setEXME_Pregunta_Lista_ID(vo.getRespuestas().get(i).getListaID().intValue());
								pregM.setRespuesta(vo.getRespuestas().get(i).getDescripcion());
								pregM.setFolio(folio.intValue());
								pregM.setFecha(DB.getTimestampForOrg(ctx));
								pregM.setSecuencia(new BigDecimal(vo.getSecuencia()));

								if (!pregM.save(trxName)) {
									errores.add("SQLException", new ActionError("error.citas.noInsertEjec"));
									Trx.rollback(m_trx);
									break;
								}

							}

						}
					}
				}
			}

			Trx.commit(m_trx);

		} catch (Exception e) {
			Trx.rollback(m_trx);
			s_log.log(Level.SEVERE, "insertaActPacienteDetTempMulti", e);

		} finally {
			Trx.close(m_trx, true);
			trxName = null;
		}

		return errores;
	}

	/**
	 * Insertamos registros en exme_enfcontrolresp y cuest incluyendo el proyecto_id
	 * 
	 * @param ctx
	 * @param map
	 * @param medico
	 * @param folio
	 * @param com
	 * @param cita
	 * @param proyecto
	 * @param cuestionario
	 * @param comentario
	 * @return
	 * @throws SQLException
	 */
	public static ActionErrors insertaProyectoTempMulti(Properties ctx, ArrayList<Pregunta_VO> map, Integer medico, Integer folio, String com, Integer cita, Integer proyecto, Integer cuestionario, String comentario) throws SQLException {
		s_log.log(Level.INFO, "***** Insertando en insertaProyectoTempMulti ***** ");
		ActionErrors errores = new ActionErrors();

		Trx m_trx = null;
		String trxName = null;

		try {

			m_trx = Trx.get(Trx.createTrxName("ECD"), true);
			trxName = m_trx.getTrxName();

			// Antes de iterar las preguntas con su respuesta se debe guardar el encabezado en exme_enfcontrolcuest
			MEXMEEnfControlCuest cCuest = null;
			MEXMEEnfControlCuest oldCuest = MEXMEEnfControlCuest.getCuest(ctx, trxName, proyecto, cuestionario, medico);
			if (oldCuest != null) {
				cCuest = new MEXMEEnfControlCuest(ctx, oldCuest.getEXME_EnfControlCuest_ID(), trxName);
			} else {
				cCuest = new MEXMEEnfControlCuest(ctx, 0, trxName);
			}

			cCuest.setEXME_Cuestionario_ID(cuestionario);
			cCuest.save(trxName);

			Iterator<Pregunta_VO> itrValor = map.iterator();
			MEXMEEnfControlResp cResp = null;
			Pregunta_VO vo = null;
			// ArrayList<Pregunta_VO> valor = null;

			while (itrValor.hasNext()) {
				vo = (Pregunta_VO) itrValor.next();

				int ID = 0;
				// Validar que guarde en el registro anterior
				if (proyecto != null) {
					ID = vo.getExme_enfcontrolresp_ID() == null ? 0 : vo.getExme_enfcontrolresp_ID();
				} else {
					ID = vo.getExme_t_cuest_ID() == null ? 0 : vo.getExme_t_cuest_ID().intValue();
				}

				cResp = new MEXMEEnfControlResp(ctx, ID, trxName);

				cResp.setFecha(DB.getTimestampForOrg(ctx));
				// cResp.setEXME_Paciente_ID(paciente.intValue());
				cResp.setEXME_Medico_ID(medico.intValue());
				// cResp.setEXME_EnfControlada_ID(Enf.intValue());
				cResp.setEXME_EstServ_ID(Env.getContextAsInt(ctx, "#EXME_EstServ_ID"));

				if (cita != null) {
					if (cita.intValue() > 0) {
						cResp.setEXME_CitaMedica_ID(cita.intValue());
					}
				}
				cResp.setDescription("");

				if (vo.getCuestionarioId() != null)
					cResp.setEXME_Cuestionario_ID(vo.getCuestionarioId().intValue());

				cResp.setEXME_Pregunta_ID(vo.getId());
				cResp.setFolio(folio.intValue());
				cResp.setEsNotaMedica(true); // false!!!

				if (vo.getTipoDato().equalsIgnoreCase(MPregunta.TIPODATO_OptionList) && vo.getRespuestaCombo() != null) {
					cResp.setPregunta_Lista_Value(vo.getRespuestaCombo());
					cResp.setRespuesta(vo.getRespuesta());
				} else if (vo.getTipoDato().equalsIgnoreCase(MPregunta.TIPODATO_TextArea))
					cResp.setTextBinary(vo.getRespuesta());

				if (vo.getSecuencia() != null) {
					cResp.setSecuencia(new BigDecimal(vo.getSecuencia().toString()));
				} else {
					cResp.setSecuencia(new BigDecimal("0"));
				}

				if (vo.getComentario() != null) {
					cResp.setDescription(vo.getComentario());
				}

				if (vo.getTipoDato().equalsIgnoreCase(MPregunta.TIPODATO_Image) && (vo.getRespuestaI() != null || vo.getByteArr() != null)) {
					String fileName = vo.saveImagen();
					cResp.setRespuesta(fileName);
					cResp.setRutaImagen(fileName);

					if (!cResp.save(trxName)) {
						errores.add("SQLException", new ActionError("error.citas.noInsertEjec"));
						Trx.rollback(m_trx);
						break;
					} else {
						s_log.fine("Registro guardado en exme_enfcontrolresp: " + cResp.getEXME_EnfControlResp_ID());
					}

				} else if (!vo.getTipoDato().equalsIgnoreCase(MPregunta.TIPODATO_Image) && !vo.getTipoDato().equalsIgnoreCase(MPregunta.TIPODATO_MultiOptions)) {

					if (!vo.getTipoDato().equalsIgnoreCase(MPregunta.TIPODATO_TextArea)) {
						cResp.setRespuesta(vo.getRespuesta());
					}

					if (!cResp.save(trxName)) {
						errores.add("SQLException", new ActionError("error.citas.noInsertEjec"));
						Trx.rollback(m_trx);
						break;
					}

				} else if (vo.getTipoDato() != null && vo.getTipoDato().equalsIgnoreCase(MPregunta.TIPODATO_MultiOptions)) {

					if (!cResp.save(trxName)) {
						errores.add("SQLException", new ActionError("error.citas.noInsertEjec"));
						Trx.rollback(m_trx);

					} else {

						MEXMEEnfControlMultiResp pregM = null;

						for (int i = 0; i < vo.getRespuestas().size(); i++) {
							if (vo.getRespuestas().get(i).isSeleccionado()) {
								pregM = new MEXMEEnfControlMultiResp(ctx, 0, trxName);
								pregM.setEXME_EnfControlResp_ID(cResp.getEXME_EnfControlResp_ID());

								if (vo.getCuestionarioId() != null)
									pregM.setEXME_Cuestionario_ID(vo.getCuestionarioId().intValue());

								pregM.setEXME_Pregunta_ID(vo.getId().intValue());
								pregM.setEXME_Pregunta_Lista_ID(vo.getRespuestas().get(i).getListaID().intValue());
								pregM.setRespuesta(vo.getRespuestas().get(i).getDescripcion());
								pregM.setFolio(folio.intValue());
								pregM.setFecha(DB.getTimestampForOrg(ctx));
								pregM.setSecuencia(new BigDecimal(vo.getSecuencia()));

								if (!pregM.save(trxName)) {
									errores.add("SQLException", new ActionError("error.citas.noInsertEjec"));
									Trx.rollback(m_trx);
									break;
								}

							}

						}
					}
				}
			}

			Trx.commit(m_trx);

		} catch (Exception e) {
			Trx.rollback(m_trx);
			s_log.log(Level.SEVERE, "insertaProyectoTempMulti", e);
		} finally {
			Trx.close(m_trx, true);
			trxName = null;
		}

		return errores;
	}

	public static Integer getIdFromFolio(Integer Folio, Integer cuestionario, Integer secuencia, Integer pregunta) throws Exception {
		Integer ret = null;
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		// int preg = 0;
		// int cues = 0;
		sql.append("	select").append(" 	cu.EXME_T_CUEST_ID").append(" 	from EXME_T_CUEST cu").append(" 	where cu.FOLIO=?");
		if (cuestionario != null && cuestionario.intValue() > 0) {
			sql.append(" 	and cu.EXME_CUESTIONARIO_ID=?");
			// cues = 2;
		} else {
			sql.append(" 	and cu.EXME_CUESTIONARIO_ID IS NULL");

		}
		if (pregunta != null && pregunta.intValue() > 0) {
			sql.append(" 	and cu.EXME_PREGUNTA_ID=?");
			/*
			 * if (cues == 2) { preg = 3; } else { preg = 2; }
			 */
		}
		// secuencia para preg. repetidas .- Lama
		if (secuencia != null && secuencia.intValue() > 0) {
			sql.append(" 	and cu.secuencia=?");

		}

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = DB.prepareStatement(sql.toString(), null);

			int i = 0;
			pstmt.setInt(++i, Folio.intValue());

			if (cuestionario != null && cuestionario.intValue() > 0) {
				pstmt.setInt(++i, cuestionario.intValue());
			}
			if (pregunta != null && pregunta.intValue() > 0) {
				pstmt.setInt(++i, pregunta.intValue());
			}

			if (secuencia != null && secuencia.intValue() > 0) {
				pstmt.setInt(++i, secuencia.intValue());
			}

			rs = pstmt.executeQuery();

			if (rs.next()) {
				ret = new Integer(rs.getInt(1));
			}

		} catch (Exception e) {
			s_log.log(Level.SEVERE, "getIdFromFolio", e);
		} finally {
			DB.close(rs, pstmt);

		}

		return ret;
	}

	public static ArrayList<Integer> getIdFromPacienteCita(Integer paciente, Integer cita) throws Exception {
		ArrayList<Integer> ret = new ArrayList<Integer>();
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			sql.append("	select").append(" 	cu.EXME_T_CUEST_ID").append(" 	from EXME_T_CUEST cu");
			if (paciente != null && paciente.intValue() > 0) {
				sql.append(" 	where cu.exme_paciente_id =?");
				pstmt = DB.prepareStatement(sql.toString(), null);
				pstmt.setInt(1, paciente.intValue());
			} else if (cita != null && cita.intValue() > 0) {
				sql.append(" 	where cu.exme_citamedica_id =?");
				pstmt = DB.prepareStatement(sql.toString(), null);
				pstmt.setInt(1, cita.intValue());
			}

			rs = pstmt.executeQuery();

			while (rs.next()) {
				ret.add(new Integer(rs.getInt(1)));
			}

		} catch (Exception e) {
			s_log.log(Level.SEVERE, "getIdFromPacienteCita", e);
		} finally {
			DB.close(rs, pstmt);

		}

		return ret;
	}

	public static Integer deleteFromPacienteCita(Integer paciente, Integer cita, Integer user, Integer questID) throws SQLException {
		Integer ret = new Integer(0);
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		PreparedStatement pstmt = null;

		try {

			sql.append("	DELETE").append(" 	from EXME_T_CUEST_").append(user.toString());
			if (cita != null && cita.intValue() > 0) {
				sql.append(" 	where exme_citamedica_id = ").append(cita.intValue());
				if (questID != null && questID.intValue() > 0) {
					sql.append(" 	and EXME_CUESTIONARIO_ID = ").append(questID.intValue());
				}
				pstmt = DB.prepareStatement(sql.toString(), null);
			} else if (paciente != null && paciente.intValue() > 0) {
				sql.append(" 	where exme_paciente_id = ").append(paciente.intValue());
				if (questID != null && questID.intValue() > 0) {
					sql.append(" 	and EXME_CUESTIONARIO_ID = ").append(questID.intValue());
				}
				pstmt = DB.prepareStatement(sql.toString(), null);
			}

			ret = new Integer(pstmt.executeUpdate());

		} catch (Exception e) {
			s_log.log(Level.SEVERE, "deleteFromPacienteCita", e);

			ret = new Integer(0);
		} finally {
			DB.close(pstmt);
		}
		return ret;
	}

	public static Integer deleteFromPacienteCita(Integer paciente, Integer cita, Integer user, String trxName) throws SQLException {
		Integer ret = new Integer(0);
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		PreparedStatement pstmt = null;

		try {

			sql.append("	DELETE").append(" 	from EXME_T_CUEST_").append(user.toString());
			if (cita != null && cita.intValue() > 0) {
				sql.append(" 	where exme_citamedica_id =?");
				pstmt = DB.prepareStatement(sql.toString(), trxName);
				pstmt.setInt(1, cita.intValue());
			} else if (paciente != null && paciente.intValue() > 0) {
				sql.append(" 	where exme_paciente_id =?");
				pstmt = DB.prepareStatement(sql.toString(), trxName);
				pstmt.setInt(1, paciente.intValue());
			}

			ret = new Integer(pstmt.executeUpdate());

		} catch (Exception e) {
			s_log.log(Level.SEVERE, "deleteFromPacienteCita", e);

			ret = new Integer(0);
		} finally {
			DB.close(pstmt);
			pstmt = null;
		}
		return ret;
	}

	public static void delete(Properties ctx, int recordId, int cuestionarioId, int typeRecord, int userId, String trxName) {
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE ");
		sql.append("FROM ");
		sql.append("  exme_t_cuest_").append(userId).append(" ");
		sql.append("WHERE ");

		 switch(typeRecord) {
		 case 1: 
			 sql.append("  exme_citamedica_id = ? ");
		     break;
		 case 2: 
			 sql.append("  EXME_EncounterForms_ID = ? ");
		     break;
		 case 3: 
			 sql.append("  EXME_PROGQUIRO_ID = ? ");
		     break;
		 }
		sql.append(" AND ")
		.append("  exme_cuestionario_id = ? ");

		PreparedStatement pstmt = null;

		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, recordId);
			pstmt.setInt(2, cuestionarioId);
			pstmt.executeUpdate();

		} catch (Exception e) {
			s_log.log(Level.SEVERE, null, e);
		} finally {
			DB.close(pstmt);
		}
	}
	
	public static void cleanOS(Properties ctx, int citaID, int userId, int osID, String trxName) {
		StringBuilder sql = new StringBuilder();
		sql.append("Update ");
		//sql.append("FROM ");
		sql.append("  exme_t_cuest_").append(userId).append(" ");
		sql.append(" set respuesta = null, textbinary = null, pregunta_lista_value = null, filecontent = null  ");
		sql.append("WHERE ");
		sql.append("  exme_citamedica_id = ? ");
		
		if(osID > 0){
			sql.append(" AND exme_orderset_id is not null AND exme_orderset_id = ? ");
		}else if(osID == 0){
			sql.append(" AND (exme_orderset_id is null or exme_orderset_id = 0) ");
		}else if(osID < 0){
			sql.append(" AND exme_orderset_id is not null and exme_orderset_id > 0 ");
		}
		PreparedStatement pstmt = null;

		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, citaID);
			
			if(osID > 0)
				pstmt.setInt(2, osID);
			
			pstmt.executeUpdate();

		} catch (Exception e) {
			s_log.log(Level.SEVERE, null, e);
		} finally {
			DB.close(pstmt);
		}
	}

	public static void deleteAllOS(Properties ctx, int citaID, int userId, String trxName) {
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE ");
		sql.append("FROM ");
		sql.append("  exme_t_cuest_").append(userId).append(" ");
		sql.append("WHERE ");
		sql.append("  exme_citamedica_id = ? ");
		sql.append("AND ");
		sql.append("  exme_orderset_id is not null ");
		sql.append("AND ");
		sql.append("  exme_orderset_id > 0 ");

		PreparedStatement pstmt = null;

		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, citaID);			
			
			pstmt.executeUpdate();

		} catch (Exception e) {
			s_log.log(Level.SEVERE, null, e);
		} finally {
			DB.close(pstmt);
		}
	}
	
	public static Integer deleteFromPacienteForms(Integer paciente, Integer formID, Integer questID, Integer user, String trxName) throws SQLException {
		Integer ret = new Integer(0);
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		PreparedStatement pstmt = null;

		try {

			sql.append("	DELETE").append(" 	from EXME_T_CUEST_").append(user.toString());
			if (formID != null && formID.intValue() > 0) {
				sql.append(" 	where EXME_EncounterForms_ID =?");
				if (questID != null && questID.intValue() > 0)
					sql.append(" and exme_cuestionario_id = ").append(questID.intValue());

				pstmt = DB.prepareStatement(sql.toString(), trxName);
				pstmt.setInt(1, formID.intValue());
			} else if (paciente != null && paciente.intValue() > 0) {
				sql.append(" 	where exme_paciente_id =?");
				if (questID != null && questID.intValue() > 0)
					sql.append(" and exme_cuestionario_id = ").append(questID.intValue());
				;

				pstmt = DB.prepareStatement(sql.toString(), trxName);
				pstmt.setInt(1, paciente.intValue());
			}

			ret = new Integer(pstmt.executeUpdate());

		} catch (Exception e) {
			s_log.log(Level.SEVERE, "deleteFromPacienteCita", e);

			ret = new Integer(0);
		} finally {
			DB.close(pstmt);
			pstmt = null;
		}
		return ret;
	}

	public static Integer deleteFromPacienteCitaHC(Integer paciente, Integer cita, Integer user, Integer folio) throws SQLException {
		Integer ret = new Integer(0);
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		PreparedStatement pstmt = null;

		try {

			sql.append("	DELETE").append(" 	from EXME_T_CUEST_").append(user.toString());

			if (paciente != null && paciente.intValue() > 0) {
				sql.append(" 	where exme_paciente_id =?").append(" AND FOLIO = ?");
				pstmt = DB.prepareStatement(sql.toString(), null);
				pstmt.setInt(1, paciente.intValue());
				pstmt.setInt(2, folio.intValue());
			} else if (cita != null && cita.intValue() > 0) {
				sql.append(" 	where exme_citamedica_id =?").append(" AND FOLIO = ?");
				pstmt = DB.prepareStatement(sql.toString(), null);
				pstmt.setInt(1, cita.intValue());
				pstmt.setInt(2, folio.intValue());

			}

			ret = new Integer(pstmt.executeUpdate());

		} catch (Exception e) {
			s_log.log(Level.SEVERE, "MPregunta.getPreguntasCuestionario", e);
			ret = new Integer(0);
		} finally {
			DB.close(pstmt);

		}

		return ret;
	}

	public static void delActPacienteTempCuest(Properties ctx, Integer citaMedicaId, Integer pacienteId, Integer questID, String trxName) throws Exception {
		try {
			// if(MEXMETCuest.createTable(Env.getAD_User_ID(ctx))) Se comento por error en HRAE
			MEXMETCuest.deleteFromPacienteCita(pacienteId, citaMedicaId, new Integer(Env.getAD_User_ID(ctx)), questID);

		} catch (Exception e) {
			s_log.log(Level.SEVERE, "delActPacienteTempCuest .... ", e);
			throw e;
		}
	}

	public static void delTempCuestForms(Properties ctx, Integer formsID, Integer pacienteId, Integer cuestID, String trxName) throws Exception {
		try {
			// if(MEXMETCuest.createTable(Env.getAD_User_ID(ctx))) Se comento por error en HRAE
			MEXMETCuest.deleteFromPacienteForms(pacienteId, formsID, cuestID, new Integer(Env.getAD_User_ID(ctx)), trxName);

		} catch (Exception e) {
			s_log.log(Level.SEVERE, "delActPacienteTempCuest .... ", e);
			throw e;
		}
	}

	public static void delActPacienteTempCuestHC(Properties ctx, Integer citaMedicaId, Integer pacienteId, String trxName, Integer folio) throws Exception {
		try {
			// if(MEXMETCuest.createTable(Env.getAD_User_ID(ctx))) Se comento por error en HRAE
			MEXMETCuest.deleteFromPacienteCitaHC(pacienteId, citaMedicaId, new Integer(Env.getAD_User_ID(ctx)), folio);

		} catch (Exception e) {
			throw e;
		}
	}

	public static void copyActCuestFromTemp(Properties ctx, Integer folio, int EXME_ActPaciente_ID, int liga, int EXME_Especialidad_ID, String trns, String estadoActual) throws Exception {

		s_log.log(Level.INFO, "***** Copiando de EXME_T_Cuest a Act. Paciente Det ***** ");

		try {

			ArrayList<MEXMETCuest> cuestionario = MEXMETCuest.getMEXMETCuestByFolio(ctx, folio, trns, new Integer(Env.getAD_User_ID(ctx)));

			for (int i = 0; i < cuestionario.size(); i++) {
				MEXMETCuest temp = (MEXMETCuest) cuestionario.get(i);
				MEXMEActPacienteDet det = new MEXMEActPacienteDet(ctx, 0, trns);
				det.setDescription(temp.getDescription());
				det.setEXME_Medico_ID(temp.getEXME_Medico_ID());
				det.setEXME_Cuestionario_ID(temp.getEXME_Cuestionario_ID());
				det.setEXME_Pregunta_ID(temp.getEXME_Pregunta_ID());
				det.setRespuesta(temp.getRespuesta());
				det.setTextBinary(temp.getTextBinary());
				det.setRutaImagen(temp.getRutaImagen());

				if (EXME_ActPaciente_ID > 0)
					det.setEXME_Especialidad_ID(EXME_Especialidad_ID);

				if (temp.getConfidencial() == null)
					det.setConfidencial("T");
				else
					det.setConfidencial(temp.getConfidencial());
				det.setPregunta_Lista_Value(temp.getPregunta_Lista_Value());
				det.setSecuencia(temp.getSecuencia().intValue());
				det.setEXME_ActPaciente_ID(EXME_ActPaciente_ID);
				det.setFecha(temp.getFecha());
				det.setFolio(temp.getFolio());
				det.setEXME_EstServ_ID(Env.getContextAsInt(ctx, "#EXME_EstServ_ID"));
				det.setEstadoSalud(estadoActual);

				if (liga == 3 || liga == 4 || liga == 5 || liga == 6)// Lama : merge revision #12212
					det.setEsNotaMedica(true);

				if (temp.getEXME_Enfermeria_ID() > 0)
					det.setEXME_Enfermeria_ID(temp.getEXME_Enfermeria_ID());
				if (!det.save(trns)) {

					throw new Exception("error.cuest.guardar");

				} else {

					// Liz de la Garza- Actualizacion de las imagenes
					if (temp.getImagenBinary() != null && temp.getImagenBinary().length > 0) {
						ByteArrayInputStream byteInput = new ByteArrayInputStream(temp.getImagenBinary());
						InputStream inputStream = (InputStream) byteInput;
						MEXMETCuest.updateImagen(ctx, MEXMEActPacienteDet.Table_Name, "ImagenBinary", det.getEXME_ActPacienteDet_ID(), 0, 0, 0, 0, 0, inputStream, trns, Env.getAD_User_ID(ctx));
					}
				}
			}

		} catch (Exception e) {
		s_log.log(Level.SEVERE, e.getMessage(), e);
			throw e;
		}

	}

	public static ArrayList<MEXMETCuest> getMEXMETCuestByFolio(Properties ctx, Integer folio, String trxName, Integer user) {

		ArrayList<MEXMETCuest> retValue = new ArrayList<MEXMETCuest>();

		StringBuilder sql = new StringBuilder("	Select * from EXME_T_Cuest").append("_".concat(user.toString())).append(" where folio = ").append(folio.intValue()).append(" and isActive = 'Y' ");

		// sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, sql.toString(), "EXME_T_Cuest"));

		sql.append("order by secuencia ");

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			pstmt = DB.prepareStatement(sql.toString(), trxName);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				retValue.add(new MEXMETCuest(ctx, rs, trxName));
			}

		} catch (SQLException e) {
			s_log.log(Level.SEVERE, "getMEXMETCuestByFolio", e);
			return null;
		} finally {
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}

		return retValue;

	}

	public static ArrayList<Integer> getCuestIdByFolio(Properties ctx, Integer folio, String trxName, Integer user) throws SQLException {

		ArrayList<Integer> retValue = new ArrayList<Integer>();

		StringBuilder sql = new StringBuilder("Select distinct exme_cuestionario_id from EXME_T_Cuest").append("_".concat(user.toString())).append("  where exme_citamedica_id = ").append(folio.intValue()).append("  and exme_cuestionario_id is not null").append(" and isActive = 'Y' ");

		// sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, sql.toString(), "EXME_T_Cuest"));

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			pstmt = DB.prepareStatement(sql.toString(), null);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				retValue.add(new Integer(rs.getInt(1)));
			}

		} catch (SQLException e) {
			s_log.log(Level.SEVERE, "getCuestIdByFolio", e);
		} finally {
			DB.close(rs, pstmt);
		}

		return retValue;

	}

	public static ArrayList<Integer> getCuestIdByFolioForms(Properties ctx, Integer folio, Integer questId, String trxName, Integer user) throws SQLException {

		ArrayList<Integer> retValue = new ArrayList<Integer>();

		StringBuilder sql = new StringBuilder("Select distinct exme_cuestionario_id from EXME_T_Cuest").append("_".concat(user.toString())).append("  where EXME_EncounterForms_ID = ").append(folio.intValue()).append("  and exme_cuestionario_id is not null");

		if (questId != null && questId.intValue() > 0)
			sql.append("  and exme_cuestionario_id = ").append(questId.intValue());

		sql.append(" and isActive = 'Y' ");

		// sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, sql.toString(), "EXME_T_Cuest"));

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			pstmt = DB.prepareStatement(sql.toString(), null);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				retValue.add(new Integer(rs.getInt(1)));
			}

		} catch (SQLException e) {
			s_log.log(Level.SEVERE, "getCuestIdByFolio", e);
		} finally {
			DB.close(rs, pstmt);
		}

		return retValue;

	}

	public static ArrayList<Pregunta_VO> getPregIdByFolio(Properties ctx, Integer cita, String trxName, Integer user) throws Exception {

		ArrayList<Pregunta_VO> retValue = new ArrayList<Pregunta_VO>();

		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);

		sql.append("	select t.exme_pregunta_id,  p.exme_tipopregunta_id").append("    from exme_t_cuest").append("_".concat(user.toString())).append(" t").append("    inner join EXME_Pregunta p on (t.exme_pregunta_id = p.exme_pregunta_id)").append("    where t.exme_citamedica_id = ?").append("    and t.exme_cuestionario_id is null").append("    and t.isActive = 'Y'")
				.append("    order by t.created");

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setInt(1, cita.intValue());

			rs = pstmt.executeQuery();
			Pregunta_VO vo = null;
			while (rs.next()) {
				vo = new Pregunta_VO();
				vo.setId(new Integer(rs.getInt(1)));
				vo.setTipoPregunta(new Integer(rs.getInt(2)));

				retValue.add(vo);
			}

		} catch (SQLException e) {
			s_log.log(Level.SEVERE, "getPregIdByFolio", e);
		} finally {
			DB.close(rs, pstmt);
			pstmt = null;
			rs = null;
		}

		return retValue;

	}

	public static Integer getExistFolioCita(Integer cita, Integer user) throws SQLException {

		Integer retorno = null;

		if (MEXMETCuest.createTable(user.intValue())) {

			StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);

			sql.append("	SELECT FOLIO ").append("	FROM EXME_T_CUEST").append("_".concat(user.toString())).append("	WHERE EXME_CITAMEDICA_ID = ? ").append("	AND ISACTIVE = 'Y' ").append("	ORDER BY CREATED ");

			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {
				pstmt = DB.prepareStatement(sql.toString(), null);
				pstmt.setInt(1, cita.intValue());

				rs = pstmt.executeQuery();

				if (rs.next()) {
					retorno = new Integer(rs.getInt(1));
				}

			} catch (SQLException e) {
				s_log.log(Level.SEVERE, "getExistFolioCita", e);
			} finally {
				DB.close(rs, pstmt);
			}
		}
		return retorno;

	}

	public static Integer getExistFolioForms(Integer formsID, Integer cuestId, Integer user) throws SQLException {

		Integer retorno = null;

		if (MEXMETCuest.createTable(user.intValue())) {

			StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);

			sql.append("	SELECT FOLIO ").append("	FROM EXME_T_CUEST").append("_".concat(user.toString())).append("	WHERE EXME_EncounterForms_ID = ? ").append("	and exme_cuestionario_id = ? ").append("	AND ISACTIVE = 'Y' ").append("	ORDER BY CREATED ");

			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {
				pstmt = DB.prepareStatement(sql.toString(), null);
				pstmt.setInt(1, formsID.intValue());
				pstmt.setInt(2, cuestId.intValue());

				rs = pstmt.executeQuery();

				if (rs.next()) {
					retorno = new Integer(rs.getInt(1));
				}

			} catch (SQLException e) {
				s_log.log(Level.SEVERE, "getExistFolioCita", e);
			} finally {
				DB.close(rs, pstmt);

			}
		}
		return retorno;

	}

	public static boolean getContestaronSiNo(Integer folio, Integer user) throws Exception {

		boolean retorno = false;

		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);

		sql.append("	select * ").append("	from exme_t_cuest").append("_".concat(user.toString())).append("	where folio = ?").append("	and respuesta is not null");

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setInt(1, folio.intValue());

			rs = pstmt.executeQuery();

			if (rs.next()) {
				retorno = true;
			}
			

		} catch (SQLException e) {
			s_log.log(Level.SEVERE, "getContestaronSiNo", e);
			throw e;
		} finally {
			DB.close(rs, pstmt);
		}

		return retorno;

	}

	/**
	 * Lizeth de la Garza Creacion de Archivos temporales de imagenes
	 * 
	 * @param Tiff
	 *            Encoder image - Objeto con la imagen y sus propiedades e informacion
	 * @return
	 */

	public static void creaArchivoTemp(HttpServletRequest request, TiffEncoder tiff) {

		if (ImagenFilePath == null || ImagenFilePath.equals("")) {
			String env = System.getProperty("COMPIERE_HOME");
			Properties properties = new Properties();
			properties = WebEnv.getXPT_Properties();
			ImagenFilePath = env + File.separator + properties.getProperty("IMGCuestFilePath");
		}

		filePath = ImagenFilePath + File.separator + Env.getAD_Session_ID(WebSessionCtx.get(request).ctx) + "tmp";

		try {
			File directory = new File(filePath);
			if (!directory.exists()) {
				directory.mkdir();
				directory.deleteOnExit();

				/*
				 * try{ FileUtils.forceDelete(directory); }catch(IOException ex){ s_log.log(Level.SEVERE, "creaArchivoTemp", ex); }
				 */
			}

			String key = // tiff.getRadiografiaID() > 0 ? String.valueOf(tiff.getRadiografiaID()) :
			tiff.getImgName();
			key = StringUtils.substringBeforeLast(key, "_"); // quitamos el nombre de la extension
			// eliminamos si ya existe otra
			CuestionarioModel.deleteDirectory(filePath, key);

			key = StringUtils.substringBeforeLast(StringUtils.substringBeforeLast(tiff.getImgName(), "."), "_") + "_";
			//
			File file = File.createTempFile(key, ".tif", directory);
			file.deleteOnExit();
			FileOutputStream fos = new FileOutputStream(file.getPath());

			tiff.write((OutputStream) fos);
		} catch (IOException e) {
			s_log.log(Level.SEVERE, "While creating temporal file ...", e);
		} catch (Exception e) {
			s_log.log(Level.SEVERE, "While creating temporal file ...", e);
		}
	}

	/**
	 * Liz de la Garza Actualizar campo BLOB de una tabla dado un InputStream
	 * 
	 * @param String
	 *            trxName
	 * @param String
	 *            tableName - Nombre de la tabla
	 * @param String
	 *            columnName - Nombre de la Columna tipo BLOB
	 * @param int recordID -ID del registro en la tabla
	 * @param InputStream
	 *            inputStream -dato a guardar
	 * @return
	 */
	public static void updateImagen(Properties ctx, String tableName, 
			String columnName, int recordID, int EXME_PACIENTE_ID, int EXME_MEDICO_ID, 
			int EXME_CUESTIONARIO_ID, int EXME_PREGUNTA_ID, int SECUENCIA, 
			InputStream inputStream, String trxName, int ad_user_id) 
					throws Exception {

		BLOB blob;
		OutputStream outstream;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);

		try {

			if (recordID > 0) {
				// Lizeth de la Garza- Borramos solo la imagen para el registro

				sql.append(" UPDATE ").append(tableName).append(" SET ").append(columnName).append(" = empty_blob() ").append(" WHERE ").append(tableName).append("_ID =  ? ");

				pstmt = DB.prepareStatement(sql.toString(), trxName);
				pstmt.setInt(1, recordID);
				pstmt.executeUpdate();
				pstmt = null;

				// Lizeth de la Garza- Seleccionamos el campo de la imagen para realizar una
				// actualizacion sobre ella
				sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
				sql.append(" SELECT ").append(columnName).append(" FROM ").append(tableName).append(" WHERE ").append(tableName).append("_ID =  ?  FOR UPDATE ");

				// Connection conn = DB.getConnectionID();
				Trx trx = Trx.get(trxName, false);
				Connection conn = trx.getConnection();
				conn.setAutoCommit(false);// Liz de la Garza-requiere ser autocommit false**
				pstmt = conn.prepareStatement(sql.toString());

				pstmt.setInt(1, recordID);
				// Inserci�n de la imagen en la bd

				rs = pstmt.executeQuery();

				if (rs.next()) {
					// FIXME: cuando es nueva (blob == null) se debe insertar directamente, (error : nullpointer exception)
					// blob = ((OracleResultSet)rs).getBLOB(1);
					blob = (BLOB) rs.getBlob(1);
					// Liz de la Garza-Obtenemos el campo BLOB de la BD y se escribira sobre ella
					blob.open(BLOB.MODE_READWRITE);
					outstream = blob.setBinaryStream(0);
					IOUtils.copy(inputStream, outstream);

					inputStream.close();
					outstream.flush();
					outstream.close();
					blob.close();// Cierre del BLOB (actualizacion del registro)
					// conn.close();

				}
			} else if (recordID == 0) {

				sql.append(" UPDATE ").append(tableName).append("_".concat(new Integer(ad_user_id).toString())).append(" SET ").append(columnName).append(" = empty_blob() ").append("	WHERE EXME_PACIENTE_ID = ? ").append("	AND EXME_MEDICO_ID = ?").append("	AND EXME_CUESTIONARIO_ID = ? ").append("	AND EXME_PREGUNTA_ID = ? ").append("	AND SECUENCIA = ? ");

				pstmt = DB.prepareStatement(sql.toString(), trxName);
				pstmt.setInt(1, EXME_PACIENTE_ID);
				pstmt.setInt(2, EXME_MEDICO_ID);
				pstmt.setInt(3, EXME_CUESTIONARIO_ID);
				pstmt.setInt(4, EXME_PREGUNTA_ID);
				pstmt.setInt(5, SECUENCIA);

				pstmt.executeUpdate();
				pstmt = null;

				// Lizeth de la Garza- Seleccionamos el campo de la imagen para realizar una
				// actualizacion sobre ella
				sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
				sql.append(" SELECT ").append(columnName).append(" FROM ").append(tableName).append("_".concat(new Integer(ad_user_id).toString())).append("	WHERE EXME_PACIENTE_ID = ? ").append("	AND EXME_MEDICO_ID = ?").append("	AND EXME_CUESTIONARIO_ID = ? ").append("	AND EXME_PREGUNTA_ID = ? ").append("	AND SECUENCIA = ? ")

				.append("  FOR UPDATE ");

				// Connection conn = DB.getConnectionID();
				Trx trx = Trx.get(trxName, false);
				Connection conn = trx.getConnection();
				conn.setAutoCommit(false);// Liz de la Garza-requiere ser autocommit false**
				pstmt = conn.prepareStatement(sql.toString());

				pstmt.setInt(1, EXME_PACIENTE_ID);
				pstmt.setInt(2, EXME_MEDICO_ID);
				pstmt.setInt(3, EXME_CUESTIONARIO_ID);
				pstmt.setInt(4, EXME_PREGUNTA_ID);
				pstmt.setInt(5, SECUENCIA);
				// Inserci�n de la imagen en la bd

				rs = pstmt.executeQuery();

				if (rs.next()) {
					// FIXME: cuando es nueva (blob == null) se debe insertar directamente, (error : nullpointer exception)
					blob = (BLOB) rs.getBlob(1);
					blob.open(BLOB.MODE_READWRITE);// Liz de la Garza-Obtenemos el campo BLOB de la BD y se escribira sobre ella
					outstream = blob.setBinaryStream(0);
					IOUtils.copy(inputStream, outstream);

					inputStream.close();
					outstream.flush();
					outstream.close();
					blob.close();// Cierre del BLOB (actualizacion del registro)
					// conn.close();

				}

			}

		} catch (Exception e) {
			s_log.log(Level.SEVERE, "updateBlob", e);

			throw e;
		} finally {
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}
	}

	/**
	 * Valida si una ejecucion de citas tiene al menos una pregunta contestada
	 * 
	 * @param citaID
	 * @return
	 */
	public static boolean isCuestLleno(int citaID) throws Exception {
		boolean retValue = false;
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append("SELECT COUNT(*) FROM EXME_T_Cuest WHERE EXME_CitaMedica_ID = ? AND Respuesta IS NOT NULL");
		retValue = new Boolean(DB.getSQLValue(null, sql.toString(), citaID) > 0);
		return retValue;
	}

	/**
	 * Valida si una ejecucion de citas tiene al menos una pregunta contestada
	 * 
	 * @param folio
	 *            , user
	 * @return
	 */
	public static boolean isCuestLleno2(int citaID, Integer folio, int user) throws Exception {
		boolean retorno = false;
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append(" SELECT EXME_CITAMEDICA_ID FROM EXME_T_CUEST").append("_".concat(new Integer(user).toString())).append("	WHERE EXME_CitaMedica_ID = ? and folio = ?").append("	AND Respuesta IS NOT NULL");

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		// pstmt = conn.prepareStatement(sql.toString());

		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setInt(1, citaID);
			pstmt.setInt(2, folio.intValue());
			rs = pstmt.executeQuery();

			if (rs.next()) {
				retorno = true;
			}
			// retorno = true;
			

		} catch (SQLException e) {
			s_log.log(Level.SEVERE, "isCuestLleno2", e);

			throw e;
		} finally {
			DB.close(rs, pstmt);
		}

		return retorno;

	}

	
	/**
	 * Create a work table for a specific user.
	 * 
	 * @param ad_user_id
	 *            The user identifier to be appended to the table name
	 * @return true if the table is created, false if it is not.
	 * @throws SQLException
	 */
	public static boolean createTable(int ad_user_id) throws SQLException {
		boolean retorno = false;
		String tabla = TMP_QUESTIONARY_SUFFIX;
		String sql = null;

		
		if(DB.isOracle()) {
			sql = "select * from user_tables where table_name = ? ";
		} else {
			sql = "select * from pg_tables where tablename = lower(?)";
		}
		

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = DB.prepareStatement(sql, null);
			pstmt.setString(1, tabla.concat(new Integer(ad_user_id).toString()));

			rs = pstmt.executeQuery();

			if (rs.next()) {
				retorno = true;
			} else {
				
				if(DB.isOracle()) {
					sql = getOracleDDL(tabla, ad_user_id);
				} else {
					sql = getPostgresDDL(tabla, ad_user_id);
				}

				pstmt = DB.prepareStatement(sql.toString(), null);

				pstmt.executeUpdate();

				retorno = true;

			}

		} catch (SQLException e) {
			s_log.log(Level.SEVERE, "While creating working tables .... ", e);
		} finally {
			DB.close(rs, pstmt);
		}

		return retorno;
	}

	public static boolean validateTable(int ad_user_id) throws SQLException {
		boolean retorno = false;
		String tabla = "EXME_T_CUEST_";
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);

		sql.append(" select * from user_tables where table_name = ? ");

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setString(1, tabla.concat(new Integer(ad_user_id).toString()));

			rs = pstmt.executeQuery();

			if (rs.next()) {
				retorno = true;
			}

		} catch (SQLException e) {
			s_log.log(Level.FINEST, "Error en MEXMETCuest.validateTable", e);

		} finally {
			DB.close(rs, pstmt);
		}

		return retorno;

	}
	
	/**
	 * Obtener cuestionario temporal por medio de la cita medica
	 * 
	 * @param ctx
	 *            Contexto
	 * @param cuestId
	 *            Cuestionario
	 * @param recordId
	 *            Cita Medica
	 * @param multioption
	 *            Preguntas o no de multi respuesta
	 * @param typeRecord 1 = cita 2 = encounterform 3 = progquiro
	 *            Si es cita o encounter o quirofanos
	 * @param trxName
	 *            Trx Name
	 * @param user
	 *            Usuario
	 * @return
	 */
	public static List<Integer> getCuest(Properties ctx, int recordId, boolean multioption, int typeRecord, String trxName, Integer user) {
		List<Integer> retValue = new ArrayList<Integer>();

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT DISTINCT EXME_Cuestionario_ID ");
		sql.append("FROM ");
		sql.append("  EXME_T_Cuest_").append(user);
		sql.append(" WHERE ");
		
		 switch(typeRecord) {
		 case 1: 
			 sql.append("  exme_citamedica_id = ? ");
		     break;
		 case 2: 
			 sql.append("  EXME_EncounterForms_ID = ? ");
		     break;
		 case 3: 
			 sql.append("  EXME_PROGQUIRO_ID = ? ");
		     break;
		 }

		if (multioption) {
			sql.append(" AND REF_EXME_PREGUNTA_ID > 0 ");
		} else {
			sql.append(" AND REF_EXME_PREGUNTA_ID = 0 ");
		}
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setInt(1, recordId);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				retValue.add(rs.getInt(MEXMECuestionario.COLUMNNAME_EXME_Cuestionario_ID));
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, null, e);
		} finally {
			DB.close(rs, pstmt);
		}

		return retValue;
	}
	
	
	/**
	 * Returns the DDL for Oracle to create the temporary table.
	 * @param tabla The temporary table prefix
	 * @param AD_User_ID The user numeric identifier
	 * @return A SQL sentence to be used for the table creation.
	 */
	private static String getOracleDDL(String tabla, int AD_User_ID) {
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append("  CREATE TABLE expert.").append(tabla).append(AD_User_ID).append("  ( ")
				.append("     exme_t_cuest_id                  NUMBER   (10)                    NOT NULL ")
				.append("	, ad_client_id                     NUMBER   (10)                    NOT NULL ")
				.append("	, ad_org_id                        NUMBER   (10)                    NOT NULL ")
				.append("	, isactive                         CHAR     (1)                     DEFAULT 'Y' NOT NULL ")
				.append("	, created                          DATE                             DEFAULT SYSDATE NOT NULL ")
				.append("	, createdby                        NUMBER   (10)                    NOT NULL ")
				.append("	, updated                          DATE                             DEFAULT SYSDATE NOT NULL ")
				.append("	, updatedby                        NUMBER   (10)                    NOT NULL ")
				.append("	, description                      NVARCHAR2(2000)                   ")
				.append("	, fecha                            DATE                             NOT NULL ")
				.append("	, exme_cuestionario_id             NUMBER   (10)                     ")
				.append("	, exme_pregunta_id                 NUMBER   (10)                    NOT NULL ")
				.append("	, respuesta                        NVARCHAR2(2000)                   ")
				.append("	, rutaimagen                       VARCHAR2 (120)                    ")
				.append("	, confidencial                     CHAR     (1)                     DEFAULT 'T' NOT NULL ")
				.append("	, secuencia                        NUMBER   (4)                     DEFAULT 10 NOT NULL ")
				.append("	, exme_medico_id                   NUMBER   (10)                     ")
				.append("	, exme_estserv_id                  NUMBER   (10)                     ")
				.append("	, esnotamedica                     CHAR     (1)                     DEFAULT 'N' NOT NULL ")
				.append("	, exme_citamedica_id               NUMBER   (10)                     ")
				.append("	, exme_paciente_id                 NUMBER   (10)                     ")
				.append("	, folio                            NUMBER   (10)                     ")
				.append("	, imagenbinary                     BLOB                              ")
				.append("	, textbinary                       CLOB                              ")
				.append("	, pregunta_lista_value             NVARCHAR2(120)                    ")
				.append("	, exme_enfermeria_id               NUMBER   (10)                     ")
				.append("	, ref_exme_pregunta_id             NUMBER   (10)                     ")
				.append("	, EXME_EncounterForms_ID           NUMBER   (10)                     ")
				.append("	, EXME_PROGQUIRO_ID                NUMBER   (10)                     ")				
				.append("	, DocStatus CHAR(1) default '0'                                      ")
				.append("	, FILECONTENT                      BLOB                              ")
				.append("	, EXME_OrderSet_ID                NUMBER   (10)                     ")
				.append("	) tablespace CUESTIONARIOS ");
		
		return sql.toString();
	}
	
	
	/**
	 * Returns the DDL for PostgreSQL to create the temporary table.
	 * @param tabla The temporary table prefix
	 * @param AD_User_ID The user numeric identifier
	 * @return A SQL sentence to be used for the table creation.
	 */
	private static String getPostgresDDL(String tabla, int AD_User_ID) {
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append("  CREATE TABLE ").append(tabla).append(AD_User_ID).append("  ( ")
				.append("     exme_t_cuest_id                  NUMERIC   	(10)    NOT NULL ")
				.append("	, ad_client_id                     NUMERIC   	(10)    NOT NULL ")
				.append("	, ad_org_id                        NUMERIC   	(10)    NOT NULL ")
				.append("	, isactive                         CHAR     	(1)     DEFAULT 'Y' NOT NULL ")
				.append("	, created                          DATE                 DEFAULT now() NOT NULL ")
				.append("	, createdby                        NUMERIC   	(10)    NOT NULL ")
				.append("	, updated                          DATE         		DEFAULT now() NOT NULL ")
				.append("	, updatedby                        NUMERIC   	(10)	NOT NULL ")
				.append("	, description                      VARCHAR		(2000)")
				.append("	, fecha                            DATE					NOT NULL ")
				.append("	, exme_cuestionario_id             NUMERIC		(10)")
				.append("	, exme_pregunta_id                 NUMERIC   	(10)	NOT NULL ")
				.append("	, respuesta                        VARCHAR		(2000)")
				.append("	, rutaimagen                       VARCHAR 		(120)")
				.append("	, confidencial                     CHAR     	(1)		DEFAULT 'T' NOT NULL ")
				.append("	, secuencia                        NUMERIC		(4)		DEFAULT 10 NOT NULL ")
				.append("	, exme_medico_id                   NUMERIC		(10)")
				.append("	, exme_estserv_id                  NUMERIC		(10)")
				.append("	, esnotamedica                     CHAR			(1)		DEFAULT 'N' NOT NULL ")
				.append("	, exme_citamedica_id               NUMERIC		(10)")
				.append("	, exme_paciente_id                 NUMERIC		(10)")
				.append("	, folio                            NUMERIC		(10)")
				.append("	, imagenbinary                     BYTEA")
				.append("	, textbinary                       TEXT")
				.append("	, pregunta_lista_value             VARCHAR		(120)")
				.append("	, exme_enfermeria_id               NUMERIC		(10)")
				.append("	, ref_exme_pregunta_id             NUMERIC		(10)")
				.append("	, EXME_EncounterForms_ID           NUMERIC		(10)")
				.append("	, EXME_PROGQUIRO_ID                NUMERIC		(10)")
				.append("	, DocStatus CHAR(1) default '0'                                      ")
				.append("	, FILECONTENT                      BYTEA		")
				.append("	, EXME_OrderSet_ID                NUMERIC		(10)")
				.append("	)");
		
		return sql.toString();
	}
}