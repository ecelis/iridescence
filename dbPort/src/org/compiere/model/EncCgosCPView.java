package org.compiere.model;

/**
 * Modelo del encabezado de cargos a cuenta paciente.
 *
 * <b>Creado:</b> 15/Jul/2004
 * <b>Modificado por:</b> $Author: gisela $
 * <b>Fecha:</b> $Date: 2006/08/30 00:52:59 $
 *
 * @author miguel angel rojas
 * @version $Revision: 1.1 $
 */
public class EncCgosCPView {

    /**
     * Propiedad CtaPacId.
     */
    private long ctaPacID = 0;

    /**
     * Devuelve el valor de la propiedad ctaPacId .
     * 
     * @return  El valor actual de CtaPacId
     */
    public long getCtaPacID() {
        return this.ctaPacID;
    }

    /**
     * Establece el nuevo valor para la propiedad ctaPacId .
     * 
     * @param ctaPacId El nuevo valor para ctaPacId .
     */
    public void setCtaPacID(long ctaPacID) {
        this.ctaPacID = ctaPacID;
    }


    /**
     * Propiedad PacienteID.
     */
    private long pacienteID = 0;

    /**
     * Devuelve el valor de la propiedad pacienteID .
     * 
     * @return  El valor actual de PacienteID
     */
    public long getPacienteID() {
        return this.pacienteID;
    }

    /**
     * Establece el nuevo valor para la propiedad pacienteID .
     * 
     * @param pacienteID El nuevo valor para pacienteID .
     */
    public void setPacienteID(long pacienteID) {
        this.pacienteID = pacienteID;
    }


    /**
     * Propiedad NomPaciente.
     */
    private String nomPaciente = null;

    /**
     * Devuelve el valor de la propiedad nomPaciente .
     * 
     * @return  El valor actual de NomPaciente
     */
    public String getNomPaciente() {
        return this.nomPaciente;
    }

    /**
     * Establece el nuevo valor para la propiedad nomPaciente .
     * 
     * @param nomPaciente El nuevo valor para nomPaciente .
     */
    public void setNomPaciente(String nomPaciente) {
        this.nomPaciente = nomPaciente;
    }


    /**
     * Propiedad MotivoCita.
     */
    private String motivoCita = null;

    /**
     * Devuelve el valor de la propiedad motivoCita .
     * 
     * @return  El valor actual de MotivoCita
     */
    public String getMotivoCita() {
        return this.motivoCita;
    }

    /**
     * Establece el nuevo valor para la propiedad motivoCita .
     * 
     * @param motivoCita El nuevo valor para motivoCita .
     */
    public void setMotivoCita(String motivoCita) {
        this.motivoCita = motivoCita;
    }


    /**
     * Propiedad FamiliaID.
     */
//  private long familiaID = 0;

    /**
     * Devuelve el valor de la propiedad familiaID .
     * 
     * @return  El valor actual de FamiliaID
     */
/*  public long getFamiliaID() {
        return this.familiaID;
    }
*/
    /**
     * Establece el nuevo valor para la propiedad familiaID .
     * 
     * @param familiaID El nuevo valor para familiaID .
     */
/*  public void setFamiliaID(long familiaID) {
        this.familiaID = familiaID;
    }

*/
    /**
     * Propiedad Familia.
     */
    private String familia = null;

    /**
     * Devuelve el valor de la propiedad familia .
     * 
     * @return  El valor actual de Familia
     */
    public String getFamilia() {
        return this.familia;
    }

    /**
     * Establece el nuevo valor para la propiedad familia .
     * 
     * @param familia El nuevo valor para familia .
     */
    public void setFamilia(String familia) {
        this.familia = familia;
    }


    /**
     * Propiedad Especialidad.
     */
    private String especialidad = null;

    /**
     * Devuelve el valor de la propiedad especialidad .
     * 
     * @return  El valor actual de Especialidad
     */
    public String getEspecialidad() {
        return this.especialidad;
    }

    /**
     * Establece el nuevo valor para la propiedad especialidad .
     * 
     * @param especialidad El nuevo valor para especialidad .
     */
    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }


    /**
     * Propiedad CamaID.
     */
    private long camaID = 0;

    /**
     * Devuelve el valor de la propiedad camaID .
     * 
     * @return  El valor actual de CamaID
     */
    public long getCamaID() {
        return this.camaID;
    }

    /**
     * Establece el nuevo valor para la propiedad camaID .
     * 
     * @param camaID El nuevo valor para camaID .
     */
    public void setCamaID(long camaID) {
        this.camaID = camaID;
    }

    /**
     * Propiedad Cama.
     */
    private String cama = null;

    /**
     * Devuelve el valor de la propiedad cama .
     * 
     * @return  El valor actual de Cama
     */
    public String getCama() {
        return this.cama;
    }

    /**
     * Establece el nuevo valor para la propiedad cama .
     * 
     * @param cama El nuevo valor para cama .
     */
    public void setCama(String cama) {
        this.cama = cama;
    }


    /**
     * Propiedad MedicoID.
     */
    private long medicoID = 0;

    /**
     * Devuelve el valor de la propiedad medicoID .
     * 
     * @return  El valor actual de MedicoID
     */
    public long getMedicoID() {
        return this.medicoID;
    }

    /**
     * Establece el nuevo valor para la propiedad medicoID .
     * 
     * @param medicoID El nuevo valor para medicoID .
     */
    public void setMedicoID(long medicoID) {
        this.medicoID = medicoID;
    }


    /**
     * Propiedad NomMedico.
     */
    private String nomMedico = null;

    /**
     * Devuelve el valor de la propiedad nomMedico .
     * 
     * @return  El valor actual de NomMedico
     */
    public String getNomMedico() {
        return this.nomMedico;
    }

    /**
     * Establece el nuevo valor para la propiedad nomMedico .
     * 
     * @param nomMedico El nuevo valor para nomMedico .
     */
    public void setNomMedico(String nomMedico) {
        this.nomMedico = nomMedico;
    }


    /**
     * Propiedad HabitaID.
     */
    private long habitaID = 0;

    /**
     * Devuelve el valor de la propiedad habitaID .
     * 
     * @return  El valor actual de HabitaID
     */
    public long getHabitaID() {
        return this.habitaID;
    }

    /**
     * Establece el nuevo valor para la propiedad habitaID .
     * 
     * @param habitaID El nuevo valor para habitaID .
     */
    public void setHabitaID(long habitaID) {
        this.habitaID = habitaID;
    }


    /**
     * Propiedad Habitacion.
     */
    private String habitacion = null;

    /**
     * Devuelve el valor de la propiedad habitacion .
     * 
     * @return  El valor actual de Habitacion
     */
    public String getHabitacion() {
        return this.habitacion;
    }

    /**
     * Establece el nuevo valor para la propiedad habitacion .
     * 
     * @param habitacion El nuevo valor para habitacion .
     */
    public void setHabitacion(String habitacion) {
        this.habitacion = habitacion;
    }


    /**
     * Propiedad CtaRefID.
     */
    private long ctaRefID = 0;

    /**
     * Devuelve el valor de la propiedad ctaRefID .
     * 
     * @return  El valor actual de CtaRefID
     */
    public long getCtaRefID() {
        return this.ctaRefID;
    }

    /**
     * Establece el nuevo valor para la propiedad ctaRefID .
     * 
     * @param ctaRefID El nuevo valor para ctaRefID .
     */
    public void setCtaRefID(long ctaRefID) {
        this.ctaRefID = ctaRefID;
    }


    public String toString() {
        StringBuffer sb = new StringBuffer();

        sb.append("[cama: ").
        append(this.cama).
        append(", camaId: ").
        append(this.camaID).
        append(", ctaPacID: ").
        append(this.ctaPacID).
        append(", ctaRefID: ").
        append(this.ctaRefID).
        append(", especialidad: ").
        append(this.especialidad).
        append(", familia: ").
        append(this.familia).
        
        append(", habitacion: ").
        append(this.habitacion).
        append(", habitaID: ").
        append(this.habitaID).
        append(", ").
        append(this.habitaID).
        append(", medicoID: ").
        append(this.medicoID).
        append(", motivoCita: ").
        append(this.motivoCita).
        append(", nomMedico: ").
        append(this.nomMedico).
        append(", nomPaciente: ").
        append(this.nomPaciente).
        append(", pacienteID").
        append(this.pacienteID).
        append("]");

        return sb.toString();
        /*append(", familiaID: ").
        append(this.familiaID).*/
    }

    /**
     * PAquete relacionado a la cta pac
     */
    private String paquete = null;
    /**
     * @return Returns the paquete.
     */
    public String getPaquete() {
        return paquete;
    }
    /**
     * @param paquete The paquete to set.
     */
    public void setPaquete(String paquete) {
        this.paquete = paquete;
    }
    
    //Este es para que aparesca el paquete en el encabezado de cargos a ctapac
}