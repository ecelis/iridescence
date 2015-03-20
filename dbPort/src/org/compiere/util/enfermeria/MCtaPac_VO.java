package org.compiere.util.enfermeria;

import java.sql.ResultSet;
import java.util.Properties;

import org.compiere.model.MEXMECtaPac;
import org.compiere.process.OnCompare;

/**
 * Vista para la cuenta paciente en la hoja de enfermera
 * Creado: 01/09/2009
 * <p>
 * 
 * @author rsolorzano
 *         Modified by llama (2013)
 */
public class MCtaPac_VO extends MEXMECtaPac implements OnCompare {

	private static final long	serialVersionUID	= 1L;
	public static final int		ENCOUNTER			= 10;
	public static final int		MRN					= 20;
	public static final int		PATIENT_TYPE		= 30;
	public static final int		SERVICE				= 40;
	public static final int		ROOM_BED			= 50;
	public static final int		PATIENT_NAME		= 60;
	public static final int		ATTENDING_PHYS		= 70;
	public static final int		ADMIT_DATE			= 80;
	public static final int		STATUS				= 90;

	private String				habitacion;
	private String				camaStr;
	private String				nombreMed;
	private String				tipoPacienteStr;
	
	private int					diarioID;

	public MCtaPac_VO(final Properties ctx, final ResultSet rs, final String trxName) {
		super(ctx, rs, trxName);
		if (getEXME_TipoPaciente_ID() > 0) {
			setTipoPacienteStr(getEXME_TipoPaciente().getName());
		}
	}

	public String getCamaStr() {
		return camaStr;
	}

	public int getCtaPacID() {
		return getEXME_CtaPac_ID();
	}

	public int getDiarioID() {
		return diarioID;
	}

	public String getHabitacion() {
		return habitacion;
	}

	public String getNombreMed() {
		return nombreMed;
	}

	public int getPatientID() {
		return getEXME_Paciente_ID();
	}

	public String getTipoPacienteStr() {
		return tipoPacienteStr;
	}

	@Override
	public Object onCompare(final int type) {
		Object comparable;
		switch (type) {
		case ENCOUNTER:
			comparable = getDocumentNo();
			break;
		case MRN:
			comparable = getMRN();
			break;
		case PATIENT_TYPE:
			comparable = getTipoPacienteStr();
			break;
		case SERVICE:
			comparable = getService();
			break;
		case ROOM_BED:
			comparable = getCama();
			break;
		case PATIENT_NAME:
			comparable = getPatientName();
			break;
		case ATTENDING_PHYS:
			comparable = getNombreMed();
		case ADMIT_DATE:
			comparable = getDateOrdered();
			break;
		case STATUS:
			comparable = getEncounterStatusStr();
			break;
		default:
			comparable = "";
		}
		return comparable;
	}

	public void setCamaStr(final String camaStr) {
		this.camaStr = camaStr;
	}

	public void setDiarioID(final int diarioID) {
		this.diarioID = diarioID;
	}

	public void setHabitacion(final String habitacion) {
		this.habitacion = habitacion;
	}

	public void setNombreMed(final String nombreMed) {
		this.nombreMed = nombreMed;
	}

	public void setTipoPacienteStr(final String tipoPaciente) {
		this.tipoPacienteStr = tipoPaciente;
	}

}
