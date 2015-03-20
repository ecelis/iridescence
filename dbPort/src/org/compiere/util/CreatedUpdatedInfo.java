package org.compiere.util;

import java.util.Properties;

/**
 * 
 * @author lherrera
 * 
 */
public class CreatedUpdatedInfo {

	private int createdBy = 0;
	private int updatedBy = 0;
	private String dateCreated = null;
	private String dateUpdated = null;
	private String medCreated = null;
	private String medUpdated = null;
	private String asiCreated = null;
	private String asiUpdated = null;
	private String enfCreated = null;
	private String enfUpdated = null;
	
	public String getCreatedUpdatedInfo(Properties ctx) {
		StringBuilder sb = new StringBuilder();

		if (getCreatedBy() != 0) {
			if (getMedCreated() != null) {
				sb.append(Utilerias.getAppMsg(ctx, "msj.createdBy") + ": " + getMedCreated());
				sb.append("\n");
			} else if (getAsiCreated() != null) {
				sb.append(Utilerias.getAppMsg(ctx, "msj.createdBy") + ": " + getAsiCreated());
				sb.append("\n");
			} else if (getEnfCreated() != null) {
				sb.append(Utilerias.getAppMsg(ctx, "msj.createdBy") + ": " + getEnfCreated());
				sb.append("\n");
			}
			sb.append(Utilerias.getAppMsg(ctx, "msj.dateCreated") + ": " + getDateCreated());
			sb.append("\n");
			sb.append("\n");
		}
		
		if (getUpdatedBy() != 0) {
			if (getMedUpdated() != null) {
				sb.append(Utilerias.getAppMsg(ctx, "msj.updatedBy") + ": " + getMedUpdated());
				sb.append("\n");
			} else if (getAsiUpdated() != null) {
				sb.append(Utilerias.getAppMsg(ctx, "msj.updatedBy") + ": " + getAsiUpdated());
				sb.append("\n");
			} else if (getEnfUpdated() != null) {
				sb.append(Utilerias.getAppMsg(ctx, "msj.updatedBy") + ": " + getEnfUpdated());
				sb.append("\n");
			}
			sb.append(Utilerias.getAppMsg(ctx, "msj.dateUpdated") + ": " + getDateUpdated());
		}
		
		if (getCreatedBy() == 0 && getUpdatedBy() == 0) {
			sb.append(Utilerias.getAppMsg(ctx, "msj.notInfo"));	
		}

		return sb.toString();
	}
	
	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}

	public int getCreatedBy() {
		return createdBy;
	}

	public void setUpdatedBy(int updatedBy) {
		this.updatedBy = updatedBy;
	}

	public int getUpdatedBy() {
		return updatedBy;
	}

	public void setDateCreated(String dateCreated) {
		this.dateCreated = dateCreated;
	}

	public String getDateCreated() {
		return dateCreated;
	}

	public void setDateUpdated(String dateUpdated) {
		this.dateUpdated = dateUpdated;
	}

	public String getDateUpdated() {
		return dateUpdated;
	}

	public void setMedCreated(String medCreated) {
		this.medCreated = medCreated;
	}

	public String getMedCreated() {
		return medCreated;
	}

	public void setMedUpdated(String medUpdated) {
		this.medUpdated = medUpdated;
	}

	public String getMedUpdated() {
		return medUpdated;
	}

	public void setAsiCreated(String asiCreated) {
		this.asiCreated = asiCreated;
	}

	public String getAsiCreated() {
		return asiCreated;
	}

	public void setAsiUpdated(String asiUpdated) {
		this.asiUpdated = asiUpdated;
	}

	public String getAsiUpdated() {
		return asiUpdated;
	}

	public void setEnfCreated(String enfCreated) {
		this.enfCreated = enfCreated;
	}

	public String getEnfCreated() {
		return enfCreated;
	}

	public void setEnfUpdated(String enfUpdated) {
		this.enfUpdated = enfUpdated;
	}

	public String getEnfUpdated() {
		return enfUpdated;
	}

}