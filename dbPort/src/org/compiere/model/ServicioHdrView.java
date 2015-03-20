package org.compiere.model;



/**

 * Modelo para en encabezado de Servicios

 * <p>

 * Modificado por: $Author: taniap $

 * Fecha: $Date: 2006/09/27 14:42:16 $

 *

 * @fecha 29 de agosto de 2006

 * @author Gisela Lee Gonzalez

 * @version $Revision: 1.2 $

 */

public class ServicioHdrView {

    String documentNo = null;

    String historia   = null;

    String paciente      = null;

    String asegurado     = null;

    String medico        = null;

    String especialidad  = null;

    String consultorio   = null;

    String fechaOrd      = null;

    String fechaAplica   = null;

    String horaAplica    = null;

    String minutosAplica = null;

    String prioridad    = null;

    String almacenSol = null;
    
    String ctaPac  = null;
    
    /*
     * Miguel Loera: se guarda expediente,
     * descripcion y diagnostico de la
     * solicitud de servicio
     */
	String expediente = null;
	
	String diagnostico = null;
	
	String description = null;
	
	

    public String getAsegurado() {

        return asegurado;

    }

    public void setAsegurado(String asegurado) {

        this.asegurado = asegurado;

    }

    public String getConsultorio() {

        return consultorio;

    }

    public void setConsultorio(String consultorio) {

        this.consultorio = consultorio;

    }

    public String getDocumentNo() {

        return documentNo;

    }

    public void setDocumentNo(String documentNo) {

        this.documentNo = documentNo;

    }

    public String getEspecialidad() {

        return especialidad;

    }

    public void setEspecialidad(String especialidad) {

        this.especialidad = especialidad;

    }

    public String getFechaAplica() {

        return fechaAplica;

    }

    public void setFechaAplica(String fechaAplica) {

        this.fechaAplica = fechaAplica;

    }

    public String getFechaOrd() {

        return fechaOrd;

    }

    public void setFechaOrd(String fechaOrd) {

        this.fechaOrd = fechaOrd;

    }

    public String getHistoria() {

        return historia;

    }

    public void setHistoria(String historia) {

        this.historia = historia;

    }

    public String getHoraAplica() {

        return horaAplica;

    }

    public void setHoraAplica(String horaAplica) {

        this.horaAplica = horaAplica;

    }

    public String getMedico() {

        return medico;

    }

    public void setMedico(String medico) {

        this.medico = medico;

    }

    public String getMinutosAplica() {

        return minutosAplica;

    }

    public void setMinutosAplica(String minutosAplica) {

        this.minutosAplica = minutosAplica;

    }

    public String getPaciente() {

        return paciente;

    }

    public void setPaciente(String paciente) {

        this.paciente = paciente;

    }

    public String getPrioridad() {

        return prioridad;

    }

    public void setPrioridad(String prioridad) {

        this.prioridad = prioridad;

    }

	public String getAlmacenSol() {
		return almacenSol;
	}

	public void setAlmacenSol(String almacenSol) {
		this.almacenSol = almacenSol;
	}

	public String getCtaPac() {
		return ctaPac;
	}

	public void setCtaPac(String ctaPac) {
		this.ctaPac = ctaPac;
	}
	
	public String getExpediente() {
		return expediente;
	}

	public void setExpediente(String expediente) {
		this.expediente = expediente;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDiagnostico() {
		return diagnostico;
	}

	public void setDiagnostico(String diagnostico) {
		this.diagnostico = diagnostico;
	}
}

