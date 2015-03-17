package com.ecaresoft.process;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;

import org.apache.commons.lang.StringUtils;
import org.compiere.model.MEXMECuestionario;
import org.compiere.model.MEXMECuestionarioDt;
import org.compiere.model.MEXMEEspecialidad;
import org.compiere.model.MEXMEGrupoCuestionario;
import org.compiere.model.MEXMEGrupoCuestionarioDet;
import org.compiere.model.MEXMEOrdenCuestionario;
import org.compiere.model.MEXMEPregunta;
import org.compiere.model.MEXMETipoPregunta;
import org.compiere.model.X_EXME_Pregunta_Lista;
import org.compiere.model.X_I_EXME_GrupoCuestionario;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Msg;

public class ImportFormData extends SvrProcess {

	private String source;
	private Properties ctx;
	
	// question types
	private Map<String, Integer> questionTypes 	= null;
	// questions
	private Map<String, Integer> questions 		= null;
	// list values for questions (must refer to question)
	private Map<String, Integer> questValList 		= null;
	// questionnaire
	private Map<String, Integer> questionnaires 	= null;
	// questionnaire detail (must refer to parent questionnaire)
	private Map<Integer, HashMap<String, Integer>> questDet = null;
	// specialties
	private Map<String, Integer> specialties 		= null;
	// form groups
	private Map<String, Integer> formGroups 		= null;
	// form group detail
	private Map<Integer, HashMap<String, Integer>> formGroupDet = null;
	// form editor generated data
	private Map<String, String>formOrder			= null;
	
	/** The source of the data : Form Groups or Form Editor */
	private final static String SRC_FORM_GROUP 	= "FG";
	private final static String SRC_FORM_EDITOR 	= "FE";
	
	
	@Override
	protected void prepare() {
		ProcessInfoParameter[] para = getParameter();
		
		for (ProcessInfoParameter pInfoParam : para) {
			if(pInfoParam.getParameter() != null 
					&& "Source".equals(pInfoParam.getParameterName())) {
				source = pInfoParam.getParameter().toString();
			}
		}

	}

	@Override
	protected String doIt() throws Exception {
		
		String msg = null;

		ctx = Env.getCtx();
		
		try {
			//first, check if we already have some question types ...
			checkQuestionTypes();
			// questions ...
			checkQuestions();
			// value lists for questions ...
			checkValueLists();
			// questionnaires ...
			checkQuestionnaires();
			checkQuestDet();
			// form (questionnaires) group ...
			checkQuestGroup();
			// specialties for form group
			checkSpecialties();

			// now i'll try to insert new data
			insertData();

			msg = Msg.getMsg(getCtx(), "ImportEnded");
		} catch(Exception e) {
			log.log(Level.SEVERE, "", e);
			addLog(e.getMessage());
		}
		
		
		return msg;
	}

	/**
	 * Update IDs for already existing question types
	 */
	private void checkQuestionTypes() {
		String sql = 
			"UPDATE I_EXME_GrupoCuestionario gc SET EXME_TipoPregunta_ID = "
			+ " (SELECT EXME_TipoPregunta_ID FROM EXME_TipoPregunta WHERE AD_Client_ID = ? "
			+ "  AND Value = gc.QuestTypeValue)";
		
		log.info(
				"Question Types already in db : " +
				DB.executeUpdate(sql, new Object[] {Env.getAD_Client_ID(ctx)}, null)
		);
	}

	/**
	 * Update IDs for already existing questions
	 */
	private void checkQuestions() {
		String sql = 
			"UPDATE I_EXME_GrupoCuestionario gc SET EXME_Pregunta_ID = " 
			+ "(SELECT EXME_Pregunta_ID FROM EXME_Pregunta WHERE AD_Client_ID = ? "
			+ "AND Value = gc.questionValue)";
		
		log.info(
				"Questions already in db : " +
				DB.executeUpdate(sql, new Object[] {Env.getAD_Client_ID(ctx)}, null)
		);
	}
	
	/**
	 * Update IDs for already existing question value lists
	 */
	private void checkValueLists() {
		String sql = 
			"UPDATE I_EXME_GrupoCuestionario gc SET EXME_Pregunta_Lista_ID = " 
			+ "	  (SELECT EXME_Pregunta_Lista_ID FROM EXME_Pregunta_Lista WHERE AD_Client_ID = ? "
			+ "	    AND Value = gc.listValue AND EXME_Pregunta_ID = gc.EXME_Pregunta_ID "
			+ "		AND gc.listValue IS NOT NULL) "
			+ "WHERE gc.EXME_Pregunta_ID IS NOT NULL";
		
		log.info(
				"Values already in db : " +
				DB.executeUpdate(sql, new Object[] {Env.getAD_Client_ID(ctx)}, null)
		);
	}
	
	/**
	 * Update IDs for already existing questionnaires
	 */
	private void checkQuestionnaires() {
		String sql = 
			"UPDATE I_EXME_GrupoCuestionario gc SET EXME_Cuestionario_ID = " 
			+ "  (SELECT EXME_Cuestionario_ID FROM EXME_Cuestionario WHERE AD_Client_ID = ? "
			+ "    AND Value = gc.formValue)";
		
		log.info(
				"Questionnaires already in db : " +
				DB.executeUpdate(sql, new Object[] {Env.getAD_Client_ID(ctx)}, null)
		);
	}
	
	private void checkQuestDet() {
		String sql = 
			"update i_exme_grupocuestionario gc set exme_cuestionariodt_id = ( "
			+ "  select exme_cuestionariodt_id from exme_cuestionariodt "
			+ "  where exme_cuestionario_id = gc.exme_cuestionario_id "
			+ "   and secuencia = gc.formdetseq "
			+ "   and exme_pregunta_id = gc.exme_pregunta_id "
			+ "   and ad_client_id = ? ) "
			+ "where gc.exme_cuestionario_id is not null "
			+ "and gc.exme_pregunta_id is not null "
			+ "and ad_client_id = ?";
		
		int no = 
			DB.executeUpdate(sql, new Object[] {Env.getAD_Client_ID(ctx), Env.getAD_Client_ID(ctx)}, null);
		
		log.info("Questionnaires details already in db : " + no);
	}
	
	/**
	 * Update IDs for already existing form groups
	 */
	private void checkQuestGroup() {
		String sql = 
			"UPDATE I_EXME_GrupoCuestionario gc SET EXME_GrupoCuestionario_ID = " 
			+ "  (SELECT EXME_GrupoCuestionario_ID FROM EXME_GrupoCuestionario WHERE AD_Client_ID = ? "
			+ "    AND Name = gc.formGroupName) "
			+" WHERE gc.formGroupName IS NOT NULL";
		
		log.info(
				"Form groups already in db : " +
				DB.executeUpdate(sql, new Object[] {Env.getAD_Client_ID(ctx)}, null)
		);
	}
	
	/**
	 * Update IDs for already existing specialties
	 */
	private void checkSpecialties() {
		String sql = 
			"UPDATE I_EXME_GrupoCuestionario gc SET EXME_Especialidad_ID = " 
			+ "  (SELECT EXME_Especialidad_ID FROM EXME_Especialidad WHERE AD_Client_ID = ? "
			+ "    AND value = gc.specialtyValue)";
		
		log.info(
				"Form group specialties already in db : " +
				DB.executeUpdate(sql, new Object[] {Env.getAD_Client_ID(ctx)}, null)
		);
		
		sql = 
			"UPDATE I_EXME_GrupoCuestionario gc SET EXME_Especialidad2_ID = " 
			+ "  (SELECT EXME_Especialidad_ID FROM EXME_Especialidad WHERE AD_Client_ID = ? "
			+ "    AND value = gc.questionSpecValue)";
		
		log.info(
				"Question specialties already in db : " +
				DB.executeUpdate(sql, new Object[] {Env.getAD_Client_ID(ctx)}, null)
		);
	}
	
	
	/**
	 * Insert the new questionnaires and form groups data
	 */
	private void insertData() {

		String sql = 
			"SELECT * FROM I_EXME_GrupoCuestionario WHERE AD_Client_ID = ? "
			+"AND I_IsImported = 'N'";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			pstmt = DB.prepareStatement(sql, null);
			pstmt.setInt(1, Env.getAD_Client_ID(ctx));
			
			rs = pstmt.executeQuery();
			
			specialties = new HashMap<String, Integer>();
			questionTypes = new HashMap<String, Integer>();
			questions = new HashMap<String, Integer>();
			questValList = new HashMap<String, Integer>();
			questionnaires = new HashMap<String, Integer>();
			questDet = new HashMap<Integer, HashMap<String,Integer>>();
			
			if(SRC_FORM_GROUP.equals(source)) {
				formGroups = new HashMap<String, Integer>();
				formGroupDet = new HashMap<Integer, HashMap<String,Integer>>();
			} else {
				formOrder = new HashMap<String, String>();
			}
			
			
			while(rs.next()) {
				X_I_EXME_GrupoCuestionario formGroup = 
					new X_I_EXME_GrupoCuestionario(ctx, rs, null);

				if(SRC_FORM_GROUP.equals(source) 
						&& formGroup.getEXME_Especialidad_ID() == 0
						&& formGroup.getSpecialtyValue() != null) {
					// first specialty for form group

					int val = 
						insertSpecialty(
								formGroup.getSpecialtyValue(),
								formGroup.getSpecialtyName(),
								formGroup.getSpecialtyDesc()
						);

					if(val > 0) {
						formGroup.setEXME_Especialidad_ID(val);
					} else {
						formGroup.setI_ErrorMsg("Can not save form group specialty.\n");
					}
				}
				
				// now question specialty
				if(formGroup.getEXME_Especialidad2_ID() == 0
						&& formGroup.getQuestionSpecValue() != null) {
					
					int val = 
						insertSpecialty(
								formGroup.getQuestionSpecValue(),
								formGroup.getQuestionSpecName(),
								formGroup.getQuestionSpecDesc()
						);
					
					if(val > 0) {
						formGroup.setEXME_Especialidad2_ID(val);
					} else {
						formGroup.setI_ErrorMsg("Can not save question specialty.\n");
					}
				}
				
				
				//do we have already the question type?
				if(formGroup.getEXME_TipoPregunta_ID() == 0) {
					insertQuestionType(formGroup);
				}
				
				//or the question itself?
				if(formGroup.getEXME_Pregunta_ID() == 0) {
					insertQuestion(formGroup);
				}
				
				// list value
				if(formGroup.getEXME_Pregunta_Lista_ID() == 0 
						&& StringUtils.isNotEmpty(formGroup.getListValue())) {
					insertListValue(formGroup);
				}
				
				// the questionnaire (form) header
				if(formGroup.getEXME_Cuestionario_ID() == 0) {
					insertQuestHeader(formGroup);
				}
				
				if(SRC_FORM_GROUP.equals(source) 
						&& formGroup.getEXME_GrupoCuestionario_ID() == 0) {
					// now the form group
					insertFormGroup(formGroup);
				}
				
				// insert the questionnaire detail
				insertFormDetail(formGroup);
								
				if(SRC_FORM_GROUP.equals(source)) {
					// and the form group detail
					insertFormGroupDetail(formGroup);
				}
				
				if(SRC_FORM_GROUP.equals(source)) {
					formGroup.setI_IsImported(true);
				}
				
				if(!formGroup.save()) {
					//FIXME : hard coded message
					throw new SQLException("Can not save form group interface data.");
				}
			}
			
			
			// now if we are inserting data from the form editor, first check if
			// it is not already in the target database
			if(SRC_FORM_EDITOR.equals(source)) {
				insertFormOrder();
			}
			
			
			
		} catch (SQLException e) {
			log.log(Level.WARNING, "", e);
		} catch (Exception e) {
			log.log(Level.WARNING, "", e);
		} finally {
			DB.close(rs, pstmt);
		}
		
	}
	
	
	/**
	 * Insert a specialty record, if it is not already in the specialties map.
	 * @param value The specialty search key
	 * @param name The specialty name
	 * @param description The specialty description
	 * @return the specialty ID or 0 if it can't be created in the database
	 */
	private int insertSpecialty(String value, String name, String description) {
		int retVal = 0;
		
		if(specialties.containsKey(value)) {
			retVal = specialties.get(value);
		} else {
			MEXMEEspecialidad specialty = new MEXMEEspecialidad(ctx, 0, null);
			specialty.setValue(value);
			specialty.setName(name);
			specialty.setDescription(description);
			
			
			if(specialty.save()) {
				retVal = specialty.getEXME_Especialidad_ID();
				specialties.put(
						specialty.getValue(), 
						specialty.getEXME_Especialidad_ID()
				);
			}
		}
		
		return retVal;
	}
	
	
	/**
	 * Create the question type if it is not already in the question types map.
	 * @param formGroup The object with the question type data.
	 */
	private void insertQuestionType(X_I_EXME_GrupoCuestionario formGroup) {
		
		if(StringUtils.isNotEmpty(formGroup.getQuestTypeValue())) {
			if(questionTypes.containsKey(formGroup.getQuestTypeValue())) {
				formGroup.setEXME_TipoPregunta_ID(
						questionTypes.get(formGroup.getQuestTypeValue())
				);
			} else {
				MEXMETipoPregunta questType = new MEXMETipoPregunta(ctx, 0, null);
				questType.setValue(formGroup.getQuestTypeValue());
				questType.setName(formGroup.getQuestTypeName());
				questType.setDescription(formGroup.getQuestTypeDesc());
				
				if(questType.save()) {
					formGroup.setEXME_TipoPregunta_ID(
							questType.getEXME_TipoPregunta_ID()
					);
					
					questionTypes.put(
							questType.getValue(), 
							questType.getEXME_TipoPregunta_ID()
					);
				} else {
					formGroup.setI_ErrorMsg(
							formGroup.getI_ErrorMsg()
							+ "Can not save question type.\n"
					);
				}
			}
		}
	}
	
	/**
	 * Creates a new question if it is not already in the questions map.
	 * @param formGroup The object with the question data.
	 */
	private void insertQuestion(X_I_EXME_GrupoCuestionario formGroup) {
		
		if(StringUtils.isNotEmpty(formGroup.getQuestionValue()) 
				&& formGroup.getEXME_TipoPregunta_ID() > 0) {
			if(questions.containsKey(formGroup.getQuestionValue())) {
				formGroup.setEXME_Pregunta_ID(
						questions.get(formGroup.getQuestionValue())
				);
			} else {
				MEXMEPregunta question = new MEXMEPregunta(ctx, 0, null);
				question.setValue(formGroup.getQuestionValue());
				question.setName(formGroup.getQuestionName());
				question.setDescription(formGroup.getQuestionDesc());
				question.setEsCore(formGroup.isQuestionEsCore());
				question.setTipoDato(formGroup.getDataType());
				question.setEXME_Especialidad_ID(formGroup.getEXME_Especialidad2_ID());
				question.setEXME_TipoPregunta_ID(formGroup.getEXME_TipoPregunta_ID());
				
				if(question.save()) {
					formGroup.setEXME_Pregunta_ID(question.getEXME_Pregunta_ID());
					
					questions.put(
							question.getValue(),
							question.getEXME_Pregunta_ID()
					);
				} else {
					formGroup.setI_ErrorMsg(
							formGroup.getI_ErrorMsg()
							+ "Can not save question.\n"
					);
				}
			}
		}
	}
	
	/**
	 * Creates a new question value list if it is not already in the map.
	 * @param formGroup the object with the value list element data.
	 */
	private void insertListValue(X_I_EXME_GrupoCuestionario formGroup) {

		if(StringUtils.isNotEmpty(formGroup.getListValue())
				&& formGroup.getEXME_Pregunta_ID() > 0) {
			// the map key should be question id + list value
			String key = formGroup.getEXME_Pregunta_ID() + "-" + formGroup.getListValue();
			
			if(questValList.containsKey(key)) {
				formGroup.setEXME_Pregunta_Lista_ID(questValList.get(key));
			} else {
				X_EXME_Pregunta_Lista questList = new X_EXME_Pregunta_Lista(ctx, 0, null);
				questList.setValue(formGroup.getListValue());
				questList.setName(formGroup.getListName());
				questList.setDescription(formGroup.getListDesc());
				questList.setEXME_Pregunta_ID(formGroup.getEXME_Pregunta_ID());
				
				if(questList.save()) {
					formGroup.setEXME_Pregunta_Lista_ID(
							questList.getEXME_Pregunta_Lista_ID()
					);
					
					questValList.put(
							formGroup.getEXME_Pregunta_ID() + "-" + questList.getValue(), 
							questList.getEXME_Pregunta_Lista_ID()
					);
				} else {
					formGroup.setI_ErrorMsg(
							formGroup.getI_ErrorMsg()
							+ "Can not save question list element.\n"
					);
				}
			}
		}
	}
	
	/**
	 * Inserts the questionnaire (forms) header, if it is not already in the
	 * header form map.
	 * @param formGroup The object with the questionnaire header data.
	 */
	private void insertQuestHeader(X_I_EXME_GrupoCuestionario formGroup) {

		if(StringUtils.isNotEmpty(formGroup.getFormValue())) {
			// check to see if we already have it in the map
			if(questionnaires.containsKey(formGroup.getFormValue())) {
				formGroup.setEXME_Cuestionario_ID(
						questionnaires.get(formGroup.getFormValue())
				);
			} else {
				MEXMECuestionario questionnaire = new MEXMECuestionario(ctx, 0, null);
				questionnaire.setValue(formGroup.getFormValue());
				questionnaire.setName(formGroup.getFormName());
				questionnaire.setDescription(formGroup.getFormDescription());
				questionnaire.setEXME_Especialidad_ID(formGroup.getEXME_Especialidad_ID());
				questionnaire.setTipoArea(formGroup.getTipoArea());
				questionnaire.setOnlyAnswer(formGroup.isOnlyAnswer());
				questionnaire.setScore(formGroup.getScore());
				
				if(questionnaire.save()) {
					formGroup.setEXME_Cuestionario_ID(
							questionnaire.getEXME_Cuestionario_ID()
					);
					
					questionnaires.put(
							questionnaire.getValue(), 
							questionnaire.getEXME_Cuestionario_ID()
					);
					
					if(!questDet.containsKey(questionnaire.getEXME_Cuestionario_ID())) {
						questDet.put(
								questionnaire.getEXME_Cuestionario_ID(), 
								new HashMap<String, Integer>()
						);
					}
				} else {
					formGroup.setI_ErrorMsg(
							formGroup.getI_ErrorMsg()
							+ "Can not save questionnaire.\n"
					);
				}
			}
		}
	}
	
	/**
	 * Inserts the form group header, if it is not already in the form groups
	 * map.
	 * @param formGroup The object with the form group header data.
	 */
	public void insertFormGroup(X_I_EXME_GrupoCuestionario formGroup) {
		if(formGroups.containsKey(formGroup.getFormGroupName())) {
			formGroup.setEXME_GrupoCuestionario_ID(
					formGroups.get(formGroup.getFormGroupName())
			);
		} else {
			MEXMEGrupoCuestionario formGrp = new MEXMEGrupoCuestionario(ctx, 0, null);
			formGrp.setEXME_Especialidad_ID(formGroup.getEXME_Especialidad_ID());
			formGrp.setName(formGroup.getFormGroupName());
			
			if(formGrp.save()) {
				formGroup.setEXME_GrupoCuestionario_ID(
						formGrp.getEXME_GrupoCuestionario_ID()
				);
				
				formGroups.put(
						formGrp.getName(),
						formGrp.getEXME_GrupoCuestionario_ID()
				);
				
				if(!formGroupDet.containsKey(formGroup.getEXME_GrupoCuestionario_ID())) {
					formGroupDet.put(
							formGroup.getEXME_GrupoCuestionario_ID(), 
							new HashMap<String, Integer>()
					);
				}
			} else {
				formGroup.setI_ErrorMsg(
						formGroup.getI_ErrorMsg()
						+ "Can not save form group.\n"
				);
			}
			
		}
	}

	/**
	 * Inserts a questionnaire (form) detail.
	 * @param formGroup The object with the question to be added to the questionnaire.
	 */
	private void insertFormDetail(X_I_EXME_GrupoCuestionario formGroup) {
		//the questionnaires details
		
		boolean questExists = questDet.containsKey(formGroup.getEXME_Cuestionario_ID());
		
		
		boolean detailExists = false;
		
		if(questExists) {
			detailExists =
				questDet.get(formGroup.getEXME_Cuestionario_ID()).containsKey(
					formGroup.getFormDetSeq() + "-" + formGroup.getEXME_Pregunta_ID() 
				);
		}
		
		try {
			if(formGroup.getEXME_Cuestionario_ID() > 0 
					&& formGroup.getEXME_Pregunta_ID() > 0
					&& formGroup.getEXME_TipoPregunta_ID() > 0 
					&& !detailExists) {
				//check if the current question is already added to the questionnaire
				String key = 
					DB.getSQLValueString(
							null, 
							"SELECT Secuencia||'-'||EXME_Pregunta_ID FROM EXME_CuestionarioDt " 
							+ "WHERE AD_Client_ID = ? AND EXME_Cuestionario_ID = ? "
							+ "AND EXME_Pregunta_ID = ? AND Secuencia = ?", 
							new Object[] {
									Env.getAD_Client_ID(ctx),
									formGroup.getEXME_Cuestionario_ID(),
									formGroup.getEXME_Pregunta_ID(),
									formGroup.getFormDetSeq()
							}
					);
				
				if(key == null) {
					MEXMECuestionarioDt formDet = new MEXMECuestionarioDt(ctx, 0, null);
					formDet.setEXME_Cuestionario_ID(formGroup.getEXME_Cuestionario_ID());
					formDet.setEXME_Pregunta_ID(formGroup.getEXME_Pregunta_ID());
					formDet.setEXME_TipoPregunta_ID(formGroup.getEXME_TipoPregunta_ID());
					formDet.setObligatoria(formGroup.isQuestionMandatory());
					formDet.setSecuencia(formGroup.getFormDetSeq());
					
					if(formDet.save()) {
						
						if(!questExists) {
							questDet.put(
									formDet.getEXME_Cuestionario_ID(), 
									new HashMap<String, Integer>()
							);
						}
						
						questDet.get(formGroup.getEXME_Cuestionario_ID()).put(
								formGroup.getFormDetSeq() + "-" + formGroup.getEXME_Pregunta_ID(), 
								formDet.getEXME_CuestionarioDt_ID()
						);
						
						formGroup.setEXME_CuestionarioDt_ID(
								formDet.getEXME_CuestionarioDt_ID()
						);
					} else {
						formGroup.setI_ErrorMsg(
								formGroup.getI_ErrorMsg()
								+ "Can not save questionnaire detail.\n"
						);
					}
				} else {
					if(!questExists) {
						questDet.put(
								formGroup.getEXME_Cuestionario_ID(), 
								new HashMap<String, Integer>()
						);
					}
					
					questDet.get(formGroup.getEXME_Cuestionario_ID()).put(
							key,
							formGroup.getEXME_Pregunta_ID()
					);
				}
			}
		} catch (IllegalArgumentException e) {
			log.log(Level.WARNING, "", e);
			formGroup.setI_ErrorMsg(formGroup.getI_ErrorMsg() + e.getMessage() + '\n');
		} catch (Exception e) {
			log.log(Level.WARNING, "", e);
			formGroup.setI_ErrorMsg(formGroup.getI_ErrorMsg() + e.getMessage() + '\n');
		}
	}
	
	private void insertFormGroupDetail(X_I_EXME_GrupoCuestionario formGroup) {
		
		boolean formGroupExists = 
			formGroupDet.containsKey(formGroup.getEXME_GrupoCuestionario_ID());
		
		boolean detailExists = false;
		
		if(formGroupExists) {
			detailExists = 
				formGroupDet.get(formGroup.getEXME_GrupoCuestionario_ID()).containsKey(
					formGroup.getFormGroupDetSeq() + "-" + formGroup.getEXME_Cuestionario_ID()
			);
		}
		
		if(formGroup.getEXME_GrupoCuestionario_ID() > 0
				&& formGroup.getEXME_Cuestionario_ID() > 0
				&& !detailExists) {
			
			//check if the current question is already added to the questionnaire
			String key = 
				DB.getSQLValueString(
						null,
						"SELECT SeqNo||'-'||EXME_Cuestionario_ID FROM EXME_GrupoCuestionarioDet " 
						+ "WHERE AD_Client_ID = ? AND EXME_GrupoCuestionario_ID = ? "
						+ "AND EXME_Cuestionario_ID = ? "
						+ "AND SeqNo = ? AND EXME_Cuestionario_ID IS NOT NULL", 
						new Object[] {
								Env.getAD_Client_ID(ctx),
								formGroup.getEXME_GrupoCuestionario_ID(),
								formGroup.getEXME_Cuestionario_ID(),
								formGroup.getFormGroupDetSeq()
						}
				);
			
			if(key == null) {
				MEXMEGrupoCuestionarioDet frmGrpDet = new MEXMEGrupoCuestionarioDet(ctx, 0, null);
				frmGrpDet.setEXME_Cuestionario_ID(formGroup.getEXME_Cuestionario_ID());
				frmGrpDet.setEXME_GrupoCuestionario_ID(formGroup.getEXME_GrupoCuestionario_ID());
				frmGrpDet.setSeqNo(formGroup.getFormGroupDetSeq());
				
				if(frmGrpDet.save()) {
					
					formGroup.setEXME_GrupoCuestionarioDet_ID(
							frmGrpDet.getEXME_GrupoCuestionarioDet_ID()
					);
					
					if(!formGroupExists) {
						formGroupDet.put(
								frmGrpDet.getEXME_GrupoCuestionario_ID(),
								new HashMap<String, Integer>()
						);
					}
					
					formGroupDet.get(formGroup.getEXME_GrupoCuestionario_ID()).put(
							frmGrpDet.getSeqNo() + "-" + frmGrpDet.getEXME_Cuestionario_ID(),
							frmGrpDet.getEXME_GrupoCuestionarioDet_ID()
					);
				} else {
					formGroup.setI_ErrorMsg(
							formGroup.getI_ErrorMsg()
							+ "Can not save form group detail.\n"
					);
				}
			} else {
				
				if(!formGroupExists) {
					formGroupDet.put(
							formGroup.getEXME_GrupoCuestionario_ID(),
							new HashMap<String, Integer>()
					);
				}
				
				formGroupDet.get(formGroup.getEXME_GrupoCuestionario_ID()).put(
						key,
						formGroup.getEXME_GrupoCuestionario_ID()
				);
			}
		}
			
	}
	
	private void insertFormOrder() {
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = 
			"SELECT * FROM I_EXME_GrupoCuestionario WHERE AD_Client_ID = ? "
			+ "AND I_IsImported = 'N' "
			+ "AND EXME_Pregunta_ID IS NOT NULL AND EXME_TipoPregunta_ID IS NOT NULL "
			+ "AND EXME_CuestionarioDt_ID IS NOT NULL AND EXME_CuestionarioDt_ID > 0";


		try {
			pstmt = DB.prepareStatement(sql, null);
			pstmt.setInt(1, Env.getAD_Client_ID(ctx));

			rs = pstmt.executeQuery();

			String key = null;

			while(rs.next()) {
				X_I_EXME_GrupoCuestionario formGroup = 
					new X_I_EXME_GrupoCuestionario(ctx, rs, null);

				key = formGroup.getEXME_Cuestionario_ID() + "-" + formGroup.getEXME_CuestionarioDt_ID(); 

				//do we have already inserted this questionnaire - questionnaire detail?
				if(!formOrder.containsKey(key)) {
					MEXMEOrdenCuestionario frmOrd = 
						new MEXMEOrdenCuestionario(ctx, 0, null);

					frmOrd.setEXME_Cuestionario_ID(formGroup.getEXME_Cuestionario_ID());
					frmOrd.setEXME_CuestionarioDt_ID(formGroup.getEXME_CuestionarioDt_ID());
					frmOrd.setEXME_TipoPregunta_ID(formGroup.getEXME_TipoPregunta_ID());
					frmOrd.setIsSelected(formGroup.isSelected());
					frmOrd.setSeqNo(formGroup.getSeqNo());

					if(frmOrd.save()) {
						formOrder.put(
								frmOrd.getEXME_Cuestionario_ID() + "-" 
								+ frmOrd.getEXME_CuestionarioDt_ID(),
								frmOrd.getEXME_Cuestionario_ID() + "-" 
								+ frmOrd.getEXME_CuestionarioDt_ID()
						);
						formGroup.setI_IsImported(true);
						
					} else {
						formGroup.setI_ErrorMsg(
								formGroup.getI_ErrorMsg()
								+ "Can not save form editor data.\n"
						);
						formGroup.setI_IsImported(false);
					}
				} else {
					formGroup.setI_IsImported(true);
				}

				formGroup.save();
			}
		} catch(SQLException e) {
			log.log(Level.WARNING, "", e);
			addLog(e.getMessage());
		} finally {
			DB.close(rs, pstmt);
		}
	}
}
