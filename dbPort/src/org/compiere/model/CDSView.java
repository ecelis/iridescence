package org.compiere.model;

/*
 * Derechos Reservados (c) a partir 2005 Expert Sistemas Computacionales, S. A. de C. V.
 * Sistema MedSys
 */

import java.io.Serializable;
import java.util.List;

import org.compiere.minigrid.IDColumn;
import org.compiere.util.GridItem;

/**
 * Modelo para la pantalla que muestra CDS
 * <p>
 * $Author: GValdez $
 * <p>
 * Fecha: $Date: 2010/04/26 16:05:41 $
 * <p>
 * 
 * @author Gerardo Valdez
 * @version $Revision: 1.0 $
 */
public class CDSView implements Serializable, GridItem {

	private static final long serialVersionUID = -6541237564993766962L;
	private MEXMECDS cds = null;
	private List<MEXMECDSRules> cdsRules = null;
	private StringBuilder labels = null;
	private String response = null;
	private IDColumn idColumn = null;

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	public void reset() {

		cds = null;
		cdsRules = null;
		labels = null;
		response = null;
	}

	public MEXMECDS getCds() {
		return cds;
	}

	public void setCds(MEXMECDS cds) {
		this.cds = cds;
	}

	public List<MEXMECDSRules> getCdsRules() {
		return cdsRules;
	}

	public void setCdsRules(List<MEXMECDSRules> cdsRules) {
		this.cdsRules = cdsRules;
	}

	public StringBuilder getLabels() {
		return labels;
	}

	public void setLabels(StringBuilder labels) {
		this.labels = labels;
	}

	@Override
	public String[] getColumns() {
		return new String[] { "cds.description", "cds.alertMessage" };
	}

	@Override
	public IDColumn getIdColumn() {
		return idColumn;
	}

}
