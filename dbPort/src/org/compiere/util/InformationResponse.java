package org.compiere.util;

import java.util.ArrayList;
import java.util.List;

/**
 * Respuesta de Información sobre ICD9, LOINC, NDC y RXCUI, SNOMED
 * 
 * @author odelarosa
 * 
 */
public class InformationResponse {
	private List<InformationResource> educationalResources = new ArrayList<InformationResource>();
	private String subtitle;
	private String title;
	private String updated;

	public List<InformationResource> getEducationalResources() {
		return educationalResources;
	}

	public String getSubtitle() {
		return subtitle;
	}

	public String getTitle() {
		return title;
	}

	public String getUpdated() {
		return updated;
	}

	public void setEducationalResources(final List<InformationResource> educationalResources) {
		this.educationalResources = educationalResources;
	}

	public void setSubtitle(final String subtitle) {
		this.subtitle = subtitle;
	}

	public void setTitle(final String title) {
		this.title = title;
	}

	public void setUpdated(final String updated) {
		this.updated = updated;
	}

}
