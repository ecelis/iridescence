package org.compiere.model.form;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.compiere.model.MEXMECitaMedica;
import org.compiere.model.MEXMECuestionario;
import org.compiere.model.MEXMEMensajePregunta;
import org.compiere.model.MEXMEPregunta;
import org.compiere.model.MEXMEPreguntaLista;
import org.compiere.model.MEXMEReglaCuestionario;
import org.compiere.model.MEXMEReglaCuestionarioDt;
import org.compiere.model.MEXMETipoPregunta;
import org.compiere.model.form.Rule.RuleDet;
import org.compiere.util.Trx;

import com.ecaresoft.api.utils.Taggable;
import com.ecaresoft.apps.secure.Base64;
import com.google.gson.Gson;

/**
 * @author odelarosa
 * 
 */
public class Questionnaire extends Taggable {
	private boolean active = true;
	private List<Category> categories = new ArrayList<Category>();
	private String customReport;
	private String description;
	private int id;
	private String name;
	private String type;
	private String value;
	private int orgId;

	public Questionnaire() {
		super();
	}

	/**
	 * Copia el cuestionario actual para crear uno nuevo
	 * 
	 * @param ctx
	 *            Contexto de aplicación
	 * @return El id del nuevo cuestionario
	 */
	public int copy(Properties ctx) {
		HashMap<Integer, Integer> questionMap = new HashMap<Integer, Integer>();
		HashMap<Integer, Integer> questionListMap = new HashMap<Integer, Integer>();
		int id = -1;
		Trx trx = null;
		boolean isOk = true;
		try {
			trx = Trx.get(Trx.createTrxName("FE"), true);

			MEXMECuestionario cuestionario = new MEXMECuestionario(ctx, 0, null);
			cuestionario.setName(getName());
			cuestionario.setDescription(getDescription());
			cuestionario.setType(getType());
			cuestionario.setTipoArea(MEXMECuestionario.TIPOAREA_Other);
			cuestionario.setIsActive(isActive());

			if (cuestionario.save(trx.getTrxName())) {
				setId(cuestionario.get_ID());
			} else {
				isOk = false;
			}

			if (isOk) {
				outter: for (Category category : getCategories()) {
					MEXMETipoPregunta tipoPregunta = new MEXMETipoPregunta(ctx, 0, null);
					tipoPregunta.setName(category.getName());
					tipoPregunta.setNRows(category.getRows());
					tipoPregunta.setNColumns(category.getColumns());
					tipoPregunta.setEXME_Cuestionario_ID(cuestionario.getEXME_Cuestionario_ID());
					tipoPregunta.setSeqNo(category.getOrder());

					if (!tipoPregunta.save(trx.getTrxName())) {
						isOk = false;
						break;
					}

					// primera iteración de preguntas para crear nuevas preguntas
					for (Question question : category.getQuestions()) {
						MEXMEPregunta pregunta = new MEXMEPregunta(ctx, 0, null);
						pregunta.setName(question.getName());
						pregunta.setDescription(question.getDescription());
						pregunta.setEXME_TipoPregunta_ID(tipoPregunta.getEXME_TipoPregunta_ID());
						pregunta.setX(question.getxPosition());
						pregunta.setY(question.getyPosition());
						pregunta.setNColumns(question.getColumns());
						pregunta.setNRows(question.getRows());
						pregunta.setTipoDato(question.getType());
						pregunta.setObligatoria(question.isMandatory());
						pregunta.setPageSize(question.getPageSize());
						pregunta.setMultiple(question.isMultiple());
						pregunta.setHideLabel(question.isHideLabel());

						if (MEXMEPregunta.TIPODATO_FixedImage.equals(question.getType())) {
							pregunta.setFileContent(Base64.decode((String) question.getValue()));
							pregunta.setRutaImagen(question.getFileName());
						}

						if (!pregunta.save(trx.getTrxName())) {
							isOk = false;
							break outter;
						}

						questionMap.put(question.getId(), pregunta.get_ID());

						if (MEXMEPregunta.TIPODATO_MultiOptions.equals(question.getType()) || MEXMEPregunta.TIPODATO_OptionList.equals(question.getType())) {
							for (Option option : question.getOptions()) {
								MEXMEPreguntaLista preguntaLista = new MEXMEPreguntaLista(ctx, 0, null);
								preguntaLista.setName(option.getName());
								preguntaLista.setEXME_Pregunta_ID(pregunta.getEXME_Pregunta_ID());
								preguntaLista.setListValue(option.getListValue());
								preguntaLista.setSeqNo(option.getSeqNo());

								if (!preguntaLista.save(trx.getTrxName())) {
									isOk = false;
									break outter;
								}

								questionListMap.put(option.getId(), preguntaLista.get_ID());
							}
						}

						// por cada pregunta se revisa si tienen mensajes
						for (Question.Message message : question.getLstMsg()) {
							MEXMEMensajePregunta mensaje = new MEXMEMensajePregunta(ctx, 0, null);

							mensaje.setInitialValue(message.getIni());
							mensaje.setFinalValue(message.getFin());
							mensaje.setMessage(message.getMessage());
							mensaje.setEXME_Pregunta_ID(pregunta.get_ID());

							if (!mensaje.save(trx.getTrxName())) {
								isOk = false;
								break outter;
							}
						}
					}

					// Una vez creadas las preguntas Se itera nuevamente para crear las reglas
					for (Question question : category.getQuestions()) {
						// Sumatorias
						if (question.getSum() != null) {
							createRule(ctx, questionMap, questionListMap, question, question.getSum(), trx.getTrxName());
						}

						// Reglas generales
						if (question.getcRule() != null) {
							createRule(ctx, questionMap, questionListMap, question, question.getcRule(), trx.getTrxName());
						}
					}
				}
			}

			if (isOk) {
				Trx.commit(trx);
				id = cuestionario.get_ID();
			} else {
				Trx.rollback(trx);
			}
		} catch (Exception e) {
			isOk = false;
			Trx.rollback(trx);
		} finally {
			Trx.close(trx);
		}

		return id;
	}

	/**
	 * Crear regla apartir de otra
	 * 
	 * @param ctx
	 *            Contexto
	 * @param questionMap
	 *            Mapa de ids de preguntas
	 * @param questionListMap
	 *            Mapa de ids de opciones de preguntas
	 * @param question
	 *            Pregunta que tendrá la regla
	 * @param rule
	 *            Regla
	 * @param trxName
	 *            Trx
	 * @return True si fue creada, false lo contrario
	 */
	private boolean createRule(Properties ctx, HashMap<Integer, Integer> questionMap, HashMap<Integer, Integer> questionListMap, Question question, Rule rule, String trxName) {
		boolean isOk = true;
		MEXMEReglaCuestionario r = new MEXMEReglaCuestionario(ctx, 0, null);
		r.setEXME_Pregunta_ID(questionMap.get(question.getId()));
		r.setOperator(rule.getOperator());

		if (r.save(trxName)) {
			for (RuleDet det : rule.getLst()) {
				MEXMEReglaCuestionarioDt dt = new MEXMEReglaCuestionarioDt(ctx, 0, null);

				dt.setEXME_Pregunta_ID(questionMap.get(det.getQuestionId()));
				dt.setOperator(det.getOperator());
				dt.setSeqNo(det.getSeqNo());
				dt.setEXME_ReglaCuestionario_ID(r.get_ID());
				dt.setEXME_Pregunta_Lista_ID(det.getValue());

				String type = new MEXMEPregunta(ctx, dt.getEXME_Pregunta_ID(), trxName).getTipoDato();

				if (MEXMEPregunta.TIPODATO_MultiOptions.equals(type)) {
					String[] oldIds = StringUtils.split(det.getAnswer(), ",");

					if (oldIds != null) {
						List<Integer> ids = new ArrayList<Integer>();

						for (String id : oldIds) {
							ids.add(questionListMap.get(Integer.valueOf(id)));
						}

						dt.setRespuesta(StringUtils.join(ids.toArray(new Integer[] {}), ","));
					}
				} else if (MEXMEPregunta.TIPODATO_OptionList.equals(type)) {
					dt.setEXME_Pregunta_Lista_ID(questionListMap.get(det.getValue()));
				} else {
					dt.setRespuesta(det.getAnswer());
				}

				if (!dt.save(trxName)) {
					isOk = false;
					break;
				}
			}
		} else {
			isOk = false;
		}

		return isOk;
	}

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Questionnaire other = (Questionnaire) obj;
		if (id != other.id)
			return false;
		return true;
	}

	public List<Category> getCategories() {
		return categories;
	}

	public String getCustomReport() {
		return customReport;
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

	/**
	 * @param excludeEmptyAnswer true: excluye las respuestas vacias 
	 * @return
	 */
	public List<QuestionnaireBean> getQuestionnaireBeans(boolean excludeEmptyAnswer) {
		List<Category> cat = getCategories();

		HashMap<Integer, Category> orde = new HashMap<Integer, Category>();

		for (Category category : cat) {
			orde.put(category.getId(), category);
		}

		List<QuestionnaireBean> beans = new ArrayList<QuestionnaireBean>();

		for (Category category : getCategories()) {
			int i = 0;
			QuestionnaireBean bean = null;
			for (Question question : category.getQuestions()) {
				String answer = question.getPrintValue();
				Object obj = question.getValue();
//				Card 1465 - Omite las preguntas vacias, si estas en nimbo
				if(excludeEmptyAnswer && StringUtils.isBlank(answer) && obj == null){
					continue;
				}
				
				byte[] image = obj instanceof byte[] ?  (byte[])obj : null;//Ticket #09165 - Lama
				if (bean != null) {
					if (!bean.getCategory().equalsIgnoreCase(category.getName())) {
						beans.add(bean);
						bean = new QuestionnaireBean();
						bean.setQuestionAnswer1(question.getName(), image == null ? answer : image);
						bean.setCategory(category.getName());
						bean.setCategoryOrder(category.getOrder());
						i = 1;
						continue;
					}
				}

				switch (i) {
				case 0:
					bean = new QuestionnaireBean();
					bean.setQuestionAnswer1(question.getName(), image == null ? answer : image);
					bean.setCategory(category.getName());
					bean.setCategoryOrder(category.getOrder());
					break;

				case 1:
					bean.setQ2(question.getName());
					bean.setQuestionAnswer2(question.getName(), image == null ? answer : image);
					bean.setCategory(category.getName());
					bean.setCategoryOrder(category.getOrder());
					break;

				case 2:
					bean.setQ3(question.getName());
					bean.setQuestionAnswer3(question.getName(), image == null ? answer : image);
					bean.setCategory(category.getName());
					bean.setCategoryOrder(category.getOrder());
					i = -1;
					beans.add(bean);
					bean = null;
					break;

				default:
					break;
				}

				i++;
			}
			if (i != 0) {
				beans.add(bean);
			}
		}

		Collections.sort(beans, new Comparator<QuestionnaireBean>() {

			@Override
			public int compare(QuestionnaireBean o1, QuestionnaireBean o2) {
				if (o1.getCategoryOrder() < o2.getCategoryOrder()) {
					return -1;
				} else if (o1.getCategoryOrder() == o2.getCategoryOrder()) {
					return 0;
				} else {
					return 1;
				}
			}
		});
		return beans;
	}

	public String getType() {
		return type;
	}

	public String getValue() {
		return value;
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	public boolean isActive() {
		return active;
	}

	public void loadValues(Properties ctx, MEXMECitaMedica exmeCitaMedica) {
		if (exmeCitaMedica == null || exmeCitaMedica.getEXME_CitaMedica_ID() == 0) {
			throw new NullPointerException("The appointment is null");
		}
		for (Category category : getCategories()) {
			category.loadValues(ctx, getId(), exmeCitaMedica);
		}
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

	public void setCustomReport(String customReport) {
		this.customReport = customReport;
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

	public void setType(String type) {
		this.type = type;
	}

	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * Serializar objeto a Json para exportar
	 * 
	 * @return Representación Json
	 */
	public String toJson() {
		return new Gson().toJson(this);
	}

	@Override
	public String toString() {
		StringBuilder aux = new StringBuilder();
		aux.append(StringUtils.upperCase(getName()));

		boolean empty = true;
		int categoryID = 0;
		for (Category category : getCategories()) {

			for (Question question : category.getQuestions()) {
				if (StringUtils.isNotBlank(question.getPrintValue())) {
					if (categoryID != category.getId()) {
						aux.append("\n").append("\n").append("    ").append(StringUtils.upperCase(category.getName())).append("\n").append("    ");
						categoryID = category.getId();
					}
					empty = false;
					if (MEXMEPregunta.TIPODATO_OptionList.equalsIgnoreCase(question.getType()) && ("YES".equalsIgnoreCase(question.getPrintValue()) || "NO".equalsIgnoreCase(question.getPrintValue()))) {
						if ("YES".equalsIgnoreCase(question.getPrintValue())) {
							aux.append(question.getName()).append("  ");
						} else {
							aux.append("No ").append(question.getName()).append("  ");
						}
					} else {
						aux.append(question.getName()).append(":").append(question.getPrintValue()).append("  ");
					}
				}
			}
		}

		return empty ? null : aux.append("\n").toString();
	}

	public int getOrgId() {
		return orgId;
	}

	public void setOrgId(int orgId) {
		this.orgId = orgId;
	}
}
