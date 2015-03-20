package org.compiere.util.vo;

import java.sql.Timestamp;

import org.compiere.minigrid.IDColumn;
import org.compiere.util.GridItem;

/**
 * Vista de actividad para pantalla WQuickSearch
 * @author Omar de la Rosa
 *
 */
public class ActPacienteVO implements GridItem {

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	private Timestamp fecha;
	private String nombrePac;
	private String valor;
	private IDColumn idColumn;
	private String ctaPac;
	
	public String getCtaPac() {
		return ctaPac;
	}

	public void setCtaPac(String ctaPac) {
		this.ctaPac = ctaPac;
	}

	public Timestamp getFecha() {
		return fecha;
	}

	public void setFecha(Timestamp fecha) {
		this.fecha = fecha;
	}

	public String getNombrePac() {
		return nombrePac;
	}

	public void setNombrePac(String nombrePac) {
		this.nombrePac = nombrePac;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public void setIdColumn(IDColumn idColumn) {
		this.idColumn = idColumn;
	}
	
	@Override
	public IDColumn getIdColumn() {
		return idColumn;
	}

	@Override
	public String[] getColumns() {
		return new String[]{"idColumn", "ctaPac", "fecha", "nombrePac", "valor"};
	}
}
