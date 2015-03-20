package org.compiere.util;

/**
 * Recurso de información
 * 
 * @author odelarosa
 * 
 */
public class InformationResource {
	private String summary;
	private String title;
	private String updated;
	private String url;

	public String getSummary() {
		return summary;
	}

	public String getTitle() {
		return title;
	}

	public String getUpdated() {
		return updated;
	}

	public String getUrl() {
		return url;
	}

	public void setSummary(final String summary) {
		this.summary = summary;
	}

	public void setTitle(final String title) {
		this.title = title;
	}

	public void setUpdated(final String updated) {
		this.updated = updated;
	}

	public void setUrl(final String url) {
		this.url = url;
	}
}
