package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.apache.commons.lang.StringUtils;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Utilerias;

/**
 * @author odelarosa
 * @deprecated
 */
public class Questionnaire {
	/**
	 * 
	 * @author odelarosa
	 * 
	 */
	public static class Question {
		private int id;
		private String items;
		private String name;
		private String anwser;
		private String type;
		private String clazz;
		private boolean space = false;
		public String getAnwser() {
			return anwser;
		}
		public String getClazz() {
			return clazz;
		}

		public int getId() {
			return id;
		}

		public String getItems() {
			return items;
		}

		public String getName() {
			return name;
		}

		public String getType() {
			return type;
		}

		public boolean isSpace() {
			return space;
		}

		public void setAnwser(String anwser) {
			this.anwser = anwser;
		}

		public void setClazz(String clazz) {
			this.clazz = clazz;
		}

		public void setId(int id) {
			this.id = id;
		}

		public void setItems(String items) {
			this.items = items;
		}

		public void setName(String name) {
			this.name = name;
		}

		public void setSpace(boolean space) {
			this.space = space;
		}

		public void setType(String type) {
			this.type = type;
		}
	}

	public static class QuestionSection {
		private Question quest1 = null;
		private Question quest2 = null;
		private Question quest3 = null;

		public void addQuestion(Question quest) {
			if (quest1 == null) {
				quest1 = quest;
			} else if (quest2 == null) {
				quest2 = quest;
			} else if (quest3 == null) {
				quest3 = quest;
			}
		}

		public Question getQuest1() {
			return quest1;
		}

		public Question getQuest2() {
			return quest2;
		}

		public Question getQuest3() {
			return quest3;
		}
	}

	private static CLogger s_log = CLogger.getCLogger(Questionnaire.class);

	/**
	 * 
	 * @param ctx
	 * @param actPacienteId
	 * @param trxName
	 * @return
	 */
	public static List<Questionnaire> get(Properties ctx, int actPacienteId, int cuestId, String trxName) {
		List<Questionnaire> questionnaires = new ArrayList<Questionnaire>();
		List<MEXMECuestionario> mQuestionnaires = getQuestionnaires(ctx, actPacienteId, cuestId, trxName);
		for (MEXMECuestionario mQuestionnaire : mQuestionnaires) {
			Questionnaire questionnaire = new Questionnaire();
			questionnaire.setName(mQuestionnaire.getName());
			List<Question> questions = new ArrayList<Question>();
			List<MEXMEActPacienteDet> dets = getDetails(ctx, actPacienteId, mQuestionnaire.getEXME_Cuestionario_ID(), trxName);
			for (MEXMEActPacienteDet det : dets) {
				Question question = new Question();
				MEXMEPregunta pregunta = new MEXMEPregunta(ctx, det.getEXME_Pregunta_ID(), trxName);
				question.setName(pregunta.getName());
				question.setType(new MTipoPregunta(ctx, pregunta.getEXME_TipoPregunta_ID(), trxName).getName());
				String tipoDato = pregunta.getTipoDato();
				question.setClazz(tipoDato);
				if (MEXMEPregunta.TIPODATO_Text.equals(tipoDato) || MEXMEPregunta.TIPODATO_Integer.equals(tipoDato) || MEXMEPregunta.TIPODATO_Decimal.equals(tipoDato) || MEXMEPregunta.TIPODATO_Date.equals(tipoDato) || MEXMEPregunta.TIPODATO_OptionList.equals(tipoDato)) {
					question.setAnwser(StringUtils.defaultIfEmpty(det.getRespuesta(), "No Data"));
				} else if (MEXMEPregunta.TIPODATO_TextArea.equals(tipoDato)) {
					question.setAnwser(StringUtils.defaultIfEmpty(det.getTextBinary(), "No Data"));
				} else if (MEXMEPregunta.TIPODATO_Logial.equals(tipoDato)) {
					if (StringUtils.isBlank(det.getRespuesta())) {
						question.setAnwser(Utilerias.getMessage(ctx, null, "msg.no"));
					} else {
						question.setAnwser("Y".equals(det.getRespuesta()) ? Utilerias.getMessage(ctx, null, "msg.si") : Utilerias.getMessage(ctx, null, "msg.no"));
					}
				} else if (MEXMEPregunta.TIPODATO_MultiOptions.equals(tipoDato)) {
					List<String> multiple = getMultipleOptions(ctx, actPacienteId, mQuestionnaire.getEXME_Cuestionario_ID(), pregunta.getEXME_Pregunta_ID(), trxName);
					question.setAnwser(StringUtils.defaultIfEmpty(StringUtils.join(multiple, ", "), "No Data"));
				} else {
					continue;
				}
				questions.add(question);
			}
			questionnaire.setQuestions(questions);
			questionnaires.add(questionnaire);
		}
		return questionnaires;
	}

	/**
	 * 
	 * @param ctx
	 * @param actPacienteId
	 * @param cuestionarioId
	 * @param trxName
	 * @return
	 */
	private static List<MEXMEActPacienteDet> getDetails(Properties ctx, int actPacienteId, Integer cuestionarioId, String trxName) {
		List<MEXMEActPacienteDet> ids = new ArrayList<MEXMEActPacienteDet>();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ");
		sql.append("  * ");
		sql.append("FROM ");
		sql.append("  exme_actpacientedet ");
		sql.append("WHERE ");
		sql.append("  exme_cuestionario_id = ? AND ");
		sql.append("  exme_actpaciente_id = ? AND ");
		sql.append("  ref_exme_pregunta_id IS NULL ");

		sql.append(" order by created");

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, cuestionarioId);
			pstmt.setInt(2, actPacienteId);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				ids.add(new MEXMEActPacienteDet(ctx, rs, trxName));
			}

		} catch (Exception e) {
			s_log.log(Level.SEVERE, null, e);
		} finally {
			DB.close(rs, pstmt);
		}
		return ids;
	}

	/**
	 * 
	 * @param ctx
	 * @param actPacienteId
	 * @param cuestionarioId
	 * @param preguntaId
	 * @param trxName
	 * @return
	 */
	private static List<String> getMultipleOptions(Properties ctx, int actPacienteId, int cuestionarioId, int preguntaId, String trxName) {
		List<String> multiple = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ");
		sql.append("  * ");
		sql.append("FROM ");
		sql.append("  exme_actpacientedet ");
		sql.append("WHERE ");
		sql.append("  exme_cuestionario_id = ? AND ");
		sql.append("  exme_actpaciente_id = ? AND ");
		sql.append("  ref_exme_pregunta_id = ? ");

		sql.append(" order by created");

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, cuestionarioId);
			pstmt.setInt(2, actPacienteId);
			pstmt.setInt(3, preguntaId);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				multiple.add(new MPregunta_Lista(ctx, Integer.parseInt(new MEXMEActPacienteDet(ctx, rs, trxName).getPregunta_Lista_Value()), trxName).getName());
			}

		} catch (Exception e) {
			s_log.log(Level.SEVERE, null, e);
		} finally {
			DB.close(rs, pstmt);
		}
		return multiple;
	}

	/**
	 * 
	 * @param ctx
	 * @param actPacienteId
	 * @param trxName
	 * @return
	 */
	private static List<MEXMECuestionario> getQuestionnaires(Properties ctx, int actPacienteId, int cuestId, String trxName) {
		List<MEXMECuestionario> ids = new ArrayList<MEXMECuestionario>();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT DISTINCT ");
		sql.append("  (exme_cuestionario_id) ");
		sql.append("FROM ");
		sql.append("  exme_actpacientedet ");
		sql.append("WHERE ");
		sql.append("  exme_actpaciente_id = ? ");

		if (cuestId > 0) {
			sql.append(" AND EXME_Cuestionario_ID = ? ");
		}

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, actPacienteId);
			if (cuestId > 0) {
				pstmt.setInt(2, cuestId);
			}
			rs = pstmt.executeQuery();

			while (rs.next()) {
				ids.add(new MEXMECuestionario(ctx, rs.getInt(1), trxName));
			}

		} catch (Exception e) {
			s_log.log(Level.SEVERE, null, e);
		} finally {
			DB.close(rs, pstmt);
		}
		return ids;
	}

	private String description;
	private int id;
	private String name;
	private List<Question> questions = new ArrayList<Question>();
	private HashMap<String, List<Question>> categories = new HashMap<String, List<Question>>();
	private HashMap<String, String> categoriesName = new HashMap<String, String>();
	private List<QuestionSection> questionSections = new ArrayList<QuestionSection>();

	public HashMap<String, List<Question>> getCategories() {
		return categories;
	}

	public HashMap<String, String> getCategoriesName() {
		return categoriesName;
	}

	public String getDescription() {
		return description;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public List<Question> getQuestions() {
		return questions;
	}

	public List<QuestionSection> getQuestionSections() {
		return questionSections;
	}

	public void setCategories(HashMap<String, List<Question>> categories) {
		this.categories = categories;
	}

	public void setCategoriesName(HashMap<String, String> categoriesName) {
		this.categoriesName = categoriesName;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}

	public void setQuestionSections(List<QuestionSection> questionSections) {
		this.questionSections = questionSections;
	}
}
