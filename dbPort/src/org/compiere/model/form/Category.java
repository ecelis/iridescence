package org.compiere.model.form;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.compiere.model.MEXMECitaMedica;

/**
 * @author odelarosa
 * 
 */
public class Category implements Comparable<Category> {
	private int columns;
	private int id;
	private String name;
	private int order;
	private List<Question> questions = new ArrayList<Question>();
	private int rows;

	/**
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public boolean canResize(int x, int y) {
		boolean retValue = true;

		for (Question question : questions) {
			if (question.getxPosition() + question.getColumns() - 1 > x) {
				retValue = false;
			} else if (question.getyPosition() + question.getRows() - 1 > y) {
				retValue = false;
			}
		}

		return retValue;
	}

	@Override
	public int compareTo(Category o) {
		if (order < o.getOrder()) {
			return -1;
		} else if (order == o.getOrder()) {
			return 0;
		} else {
			return 1;
		}

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
		Category other = (Category) obj;
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

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public int getOrder() {
		return order;
	}

	/**
	 * @return the questions
	 */
	public List<Question> getQuestions() {
		return questions;
	}

	/**
	 * @return the rows
	 */
	public int getRows() {
		return rows;
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

	/**
	 * Si contiene o no preguntas seleccionadas
	 * 
	 * @return
	 */
	public boolean isSelected() {
		boolean retValue = false;
		for (Question question : getQuestions()) {
			if (question.isSelected()) {
				retValue = true;
				break;
			}
		}
		return retValue;
	}

	protected void loadValues(Properties ctx, int exmeCuestionarioID, MEXMECitaMedica exmeCitaMedica) {
		for (Question question : getQuestions()) {
			question.loadValue(ctx, exmeCuestionarioID, exmeCitaMedica);
		}
	}

	/**
	 * @param columns
	 *            the columns to set
	 */
	public void setColumns(int columns) {
		this.columns = columns;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	/**
	 * @param questions
	 *            the questions to set
	 */
	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}

	/**
	 * @param rows
	 *            the rows to set
	 */
	public void setRows(int rows) {
		this.rows = rows;
	}
}
