package org.compiere.model.bean;

import java.sql.Timestamp;

/**
 * Proyecto Pro Mujer
 * http://control.ecaresoft.com/epic/82/
 * Transferencia de información entre NIMBO (eCareSoft) y FIN+
 * 
 * @author abautista
 *         Modificado por Lama, Feb 2015
 */
public interface IDHPatientInfo {

	public String getCodUsuario();

	public String getNombres();

	public String getPaterno();

	public String getMaterno();

	public String getSexo();

	public String getDI();

	public String getCodDocIden();

	public Timestamp getFechaNacimiento();

	public String getEstadoCivil();

	public String getDomicilio();

	public String getTelefono();

	public String getCodCF();

	public String getTipoCredito();

	public String getBancaComunal();

	public String getNombreAsesor();

	public String getCiclo();

	public Timestamp getFechaDesem();

	public Timestamp getFechaUltPago();

	public String getApellidoConyugue();

	public String getNombreConyugue();

	public String getDIConyugue();

	public String getCodDocIdenConyugue();
	
	public String getSOCreditStatus();
}
