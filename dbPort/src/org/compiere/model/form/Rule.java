package org.compiere.model.form;

import java.util.ArrayList;
import java.util.List;

import org.compiere.model.MEXMEReglaCuestionario;
import org.compiere.model.MEXMEReglaCuestionarioDt;

/**
 * @author odelarosa
 * 
 */
public class Rule {

	public static class RuleDet {

		private String answer;
		private String operator;
		private int questionId;
		private int seqNo;
		private int value;

		public RuleDet() {
			super();
		}

		public String getAnswer() {
			return answer;
		}

		public String getOperator() {
			return operator;
		}

		public int getQuestionId() {
			return questionId;
		}

		public int getSeqNo() {
			return seqNo;
		}

		public int getValue() {
			return value;
		}

		public void setAnswer(String answer) {
			this.answer = answer;
		}

		public void setOperator(String operator) {
			this.operator = operator;
		}

		public void setQuestionId(int questionId) {
			this.questionId = questionId;
		}

		public void setSeqNo(int seqNo) {
			this.seqNo = seqNo;
		}

		public void setValue(int value) {
			this.value = value;
		}

	}

	public static Rule getRule(MEXMEReglaCuestionario rule) {
		Rule r = new Rule();
		r.setQuestionId(rule.get_ID());
		r.setOperator(rule.getOperator());

		List<MEXMEReglaCuestionarioDt> dets = rule.getCopyDet(null);

		for (MEXMEReglaCuestionarioDt det : dets) {
			RuleDet ruleDet = new RuleDet();

			ruleDet.setSeqNo(det.getSeqNo());
			ruleDet.setQuestionId(det.getEXME_Pregunta_ID());
			ruleDet.setOperator(det.getOperator());
			ruleDet.setAnswer(det.getRespuesta());
			ruleDet.setValue(det.getEXME_Pregunta_Lista_ID());

			r.getLst().add(ruleDet);
		}
		return r;
	}

	private List<RuleDet> lst = new ArrayList<RuleDet>();
	private String operator;
	private int questionId;

	public Rule() {
		super();
	}

	public List<RuleDet> getLst() {
		return lst;
	}

	public String getOperator() {
		return operator;
	}

	public int getQuestionId() {
		return questionId;
	}

	public void setLst(List<RuleDet> lst) {
		this.lst = lst;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}

}
