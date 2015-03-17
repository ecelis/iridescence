package org.compiere.model.form;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.apache.commons.lang.StringUtils;
import org.compiere.model.MEXMECitaMedica;
import org.compiere.model.MEXMEMensajePregunta;
import org.compiere.model.MEXMEPregunta;
import org.compiere.model.MEXMEPreguntaLista;
import org.compiere.model.MEXMERespuestaCuestionario;
import org.compiere.model.MEXMETCuest;
import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.Env;
import org.compiere.util.Utilerias;

/**
 * @author odelarosa
 * 
 */
public class Question implements Cloneable, Comparable<Question> {
	public static class Message {
		private BigDecimal fin;
		private BigDecimal ini;
		private String message;

		public Message() {
			super();
		}

		public Message(String message, BigDecimal ini, BigDecimal fin) {
			this.ini = ini;
			this.fin = fin;
			this.message = message;
		}

		public BigDecimal getFin() {
			return fin;
		}

		public BigDecimal getIni() {
			return ini;
		}

		public String getMessage() {
			return message;
		}

		public void setFin(BigDecimal fin) {
			this.fin = fin;
		}

		public void setIni(BigDecimal ini) {
			this.ini = ini;
		}

		public void setMessage(String message) {
			this.message = message;
		}
	}

	public final static SimpleDateFormat DATE_FORMATER = new SimpleDateFormat("MM/dd/yyyy");
	private final static CLogger LOGGER = CLogger.getCLogger(Question.class);
	private boolean alert = false;
	private int columns;
	private Rule cRule;
	private String description;
	private int ExmeOrderSetID = 0;
	private String fileName;
	private boolean hideLabel = false;
	private int id;
	private List<Message> lstMsg = new ArrayList<Message>();
	private boolean mandatory = false;
	private boolean multiple = true;
	private String name;
	private transient int oldColumns = -1;
	private transient int oldRows = -1;
	private transient int oldXPosition = -1;
	private transient int oldYPosition = -1;
	private List<Option> options = new ArrayList<Option>();
	private int pageSize;
	private transient String printValue;
	private int rows;
	private String rule;
	private String rule2;
	private transient boolean selected = true;
	private transient int seqNo;
	private Rule sum;
	private String type;
	private Object value;
	private int xPosition;
	private int yPosition;
	public Question() {
	}

	public void addMessage(MEXMEMensajePregunta message) {
		Message message2 = new Message(message.getMessage(), message.getInitialValue(), message.getFinalValue());
		lstMsg.add(message2);
	}

	@Override
	public Object clone() {
		Question obj = null;
		try {
			obj = (Question) super.clone();
			obj.setOptions(new ArrayList<Option>());
			obj.getOptions().addAll(getOptions());
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}

		return obj;
	}

	@Override
	public int compareTo(Question another) {
		int value = Double.compare(yPosition, another.getyPosition());
		if (value == 0) {
			return Double.compare(xPosition, another.getxPosition());
		}
		return value;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Question other = (Question) obj;
		if (id != other.id)
			return false;
		return true;
	}

	/**
	 * @return the columns
	 */
	public int getColumns() {
		return columns;
	}

	public Rule getcRule() {
		return cRule;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	public int getExmeOrderSetID() {
		return ExmeOrderSetID;
	}

	public String getFileName() {
		return fileName;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	public List<Message> getLstMsg() {
		return lstMsg;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	public int getOldColumns() {
		return oldColumns;
	}

	public int getOldRows() {
		return oldRows;
	}

	public int getOldXPosition() {
		return oldXPosition;
	}

	public int getOldYPosition() {
		return oldYPosition;
	}

	public List<Option> getOptions() {
		return options;
	}

	public int getPageSize() {
		return pageSize;
	}

	public String getPrintValue() {
		return printValue;
	}

	/**
	 * @return the rows
	 */
	public int getRows() {
		return rows;
	}

	/**
	 * Regla de cuestionario
	 * 
	 * @return
	 */
	public String getRule() {
		return rule;
	}

	/**
	 * Regla de sumatoria
	 * 
	 * @return
	 */
	public String getRule2() {
		return rule2;
	}

	public int getSeqNo() {
		return seqNo;
	}

	public Rule getSum() {
		return sum;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @return the value
	 */
	public Object getValue() {
		return value;
	}

	/**
	 * @return the xPosition
	 */
	public int getxPosition() {
		return xPosition;
	}

	/**
	 * @return the yPosition
	 */
	public int getyPosition() {
		return yPosition;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	public boolean isAlert() {
		return alert;
	}

	public boolean isHideLabel() {
		return hideLabel;
	}

	/**
	 * @return the mandatory
	 */
	public boolean isMandatory() {
		return mandatory;
	}

	public boolean isMultiple() {
		return multiple;
	}

	public boolean isSelected() {
		return selected;
	}

	private void loadValue(Object textoRespuesta) {
		if (MEXMEPregunta.TIPODATO_Text.equals(type) || MEXMEPregunta.TIPODATO_TextArea.equals(type) || MEXMEPregunta.TIPODATO_OptionList.equals(type) || MEXMEPregunta.TIPODATO_Logial.equals(type) || MEXMEPregunta.TIPODATO_MultiOptions.equals(type) || MEXMEPregunta.TIPODATO_Date.equals(type)) {
			setValue(textoRespuesta);
		} else if (MEXMEPregunta.TIPODATO_Decimal.equals(type)) {
			if (textoRespuesta == null) {
				setValue(BigDecimal.ZERO);
			} else {
				setValue(new BigDecimal(textoRespuesta.toString()));
			}
		} else if (MEXMEPregunta.TIPODATO_Integer.equals(type)) {
			if (textoRespuesta == null) {
				setValue(0);
			} else {
				setValue(Integer.parseInt(textoRespuesta.toString()));
			}
		} else if (MEXMEPregunta.TIPODATO_ImageBinary.equals(type)) {
			// No esta soportado por android aun
		} else if (MEXMEPregunta.TIPODATO_FixedImage.equals(type)) {
			// No esta soportado por android aun
		}
	}

	protected void loadValue(Properties ctx, int exmeCuestionnaireID, MEXMECitaMedica exmeCitaMedica) {
		if (MEXMECitaMedica.ESTATUS_Executed.equals(exmeCitaMedica.getEstatus())) {
			if (MEXMEPregunta.TIPODATO_MultiOptions.equals(type)) {
				List<MEXMERespuestaCuestionario> lst = MEXMERespuestaCuestionario.getRespuesta(Env.getCtx(), MEXMECitaMedica.Table_ID, exmeCitaMedica.getEXME_CitaMedica_ID(), exmeCuestionnaireID, true, null, true, id);
				List<String> value = new ArrayList<String>();
				for (MEXMERespuestaCuestionario respuestaCuestionario : lst) {
					value.add(respuestaCuestionario.getTextBinary());
				}
				loadValue(StringUtils.join(value, ","));
			} else {
				// TODO Dafuq es byORG?
				// TODO Tu hiciste el método y no sabes que es?? estamos en problemas, no es bueno dar copy paste
				List<MEXMERespuestaCuestionario> lst = MEXMERespuestaCuestionario.getRespuesta(Env.getCtx(), MEXMECitaMedica.Table_ID, exmeCitaMedica.getEXME_CitaMedica_ID(), exmeCuestionnaireID, false, null, true, id);
				if (!lst.isEmpty()) {
					MEXMERespuestaCuestionario respuestaCuestionario = lst.get(0);
					if (respuestaCuestionario != null) {
						loadValue(respuestaCuestionario.getTextBinary());
					}
				}

			}
		} else {
			try {
				if (MEXMEPregunta.TIPODATO_MultiOptions.equals(type)) {
					List<MEXMETCuest> cuestList = MEXMETCuest.getRefQuestions(Env.getCtx(), id, exmeCitaMedica.getEXME_CitaMedica_ID(), MEXMETCuest.TypeAppointment, null, Env.getAD_User_ID(ctx));
					List<String> value = new ArrayList<String>();
					for (MEXMETCuest cuest : cuestList) {
						if (cuest.getREF_EXME_PREGUNTA_ID() == id) {
							value.add(cuest.getRespuesta());
						}
					}
					loadValue(StringUtils.join(value, ","));
				} else {
					MEXMETCuest cuest = MEXMETCuest.getRespuestaCom(ctx, exmeCitaMedica.getEXME_CitaMedica_ID(), id, null, Env.getAD_User_ID(ctx));
					if (cuest != null) {
						if (MEXMEPregunta.TIPODATO_TextArea.equals(type)) {
							loadValue(cuest.getTextBinary());
						} else {
							loadValue(cuest.getRespuesta());
						}
					}
				}
			} catch (Exception e) {
				LOGGER.log(Level.SEVERE, null, e);
			}
		}
	}

	public boolean move() {
		boolean retValue = false;

		if (oldXPosition != xPosition) {

		}

		return retValue;
	}

	public void setAlert(boolean alert) {
		this.alert = alert;
	}

	/**
	 * @param columns
	 *            the columns to set
	 */
	public void setColumns(int columns) {
		if (oldColumns == -1) {
			oldColumns = columns;
		} else {
			oldColumns = this.columns;
		}
		this.columns = columns;
	}

	public void setcRule(Rule cRule) {
		this.cRule = cRule;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	public void setExmeOrderSetID(int exmeOrderSetID) {
		ExmeOrderSetID = exmeOrderSetID;
	}
	
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public void setHideLabel(boolean hideLabel) {
		this.hideLabel = hideLabel;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	public void setLstMsg(List<Message> lstMsg) {
		this.lstMsg = lstMsg;
	}

	/**
	 * @param mandatory
	 *            the mandatory to set
	 */
	public void setMandatory(boolean mandatory) {
		this.mandatory = mandatory;
	}

	public void setMultiple(boolean multiple) {
		this.multiple = multiple;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	public void setOldColumns(int oldColumns) {
		this.oldColumns = oldColumns;
	}

	public void setOldRows(int oldRows) {
		this.oldRows = oldRows;
	}

	public void setOldXPosition(int oldXPosition) {
		this.oldXPosition = oldXPosition;
	}

	public void setOldYPosition(int oldYPosition) {
		this.oldYPosition = oldYPosition;
	}

	public void setOptions(List<Option> options) {
		this.options = options;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public void setPrintValue(String printValue) {
		this.printValue = printValue;
	}

	/**
	 * @param rows
	 *            the rows to set
	 */
	public void setRows(int rows) {
		if (oldRows == -1) {
			oldRows = rows;
		} else {
			oldRows = this.rows;
		}
		this.rows = rows;
	}

	public void setRule(String rule) {
		this.rule = rule;
	}

	public void setRule2(String rule2) {
		this.rule2 = rule2;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	public void setSeqNo(int seqNo) {
		this.seqNo = seqNo;
	}

	public void setSum(Rule sum) {
		this.sum = sum;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @param value
	 *            the value to set
	 */
	public void setValue(Object value) {
		this.value = value;
		if (value != null) {
			if (MEXMEPregunta.TIPODATO_OptionList.equals(type)) {
				try {
					String str = MEXMEPreguntaLista.getPrintText(value == null ? 0 : Integer.parseInt(String.valueOf(value)));
					setPrintValue(StringUtils.defaultIfEmpty(str, String.valueOf(value)));
				} catch (NumberFormatException e) {
					setPrintValue(String.valueOf(value));
				}
			} else if (MEXMEPregunta.TIPODATO_MultiOptions.equals(type)) {
				setPrintValue((String) value);
			} else {
				if (value instanceof Date) {
					setPrintValue(Constantes.sdfFecha(Env.getCtx()).format((Date) value));
				} else if (MEXMEPregunta.TIPODATO_Logial.equals(getType())) {
					if (value instanceof Boolean) {
						setPrintValue((Boolean) value ? Utilerias.getMsg(Env.getCtx(), "imag.checked") : Utilerias.getMsg(Env.getCtx(), "msj.reglaCuestionario.negativo"));
					} else if (value instanceof String) {
						setPrintValue("Y".equals(value) || "true".equals(value) ? Utilerias.getMsg(Env.getCtx(), "imag.checked") : Utilerias.getMsg(Env.getCtx(), "msj.reglaCuestionario.negativo"));
					}

				} else {
					setPrintValue(String.valueOf(value));
				}
			}
		}
	}

	/**
	 * @param xPosition
	 *            the xPosition to set
	 */
	public void setxPosition(int xPosition) {
		if (oldXPosition == -1) {
			oldXPosition = xPosition;
		} else {
			oldXPosition = this.xPosition;
		}
		this.xPosition = xPosition;
	}

	/**
	 * @param yPosition
	 *            the yPosition to set
	 */
	public void setyPosition(int yPosition) {
		if (oldYPosition == -1) {
			oldYPosition = yPosition;
		} else {
			oldYPosition = this.yPosition;
		}
		this.yPosition = yPosition;
	}
}
