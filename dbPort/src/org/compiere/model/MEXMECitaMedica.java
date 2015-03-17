package org.compiere.model;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.adempiere.exceptions.MedsysException;
import org.apache.commons.lang.StringUtils;
import org.compiere.minigrid.IDColumn;
import org.compiere.util.CLogger;
import org.compiere.util.CalcularEdadAMD;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.GridItem;
import org.compiere.util.SecureEngine;
import org.compiere.util.TimeUtil;
import org.compiere.util.Utilerias;
import org.compiere.util.ValueNamePair;
import org.joda.time.DateTime;
import org.joda.time.Interval;

/**
 * Modelo de Cita Medica
 * <b>Modificado: </b> $Author: otorres $<p>
 * <b>En :</b> $Date: 2006/10/25 23:20:41 $<p>
 *
 * @author Hassan Reyes
 * @version $Revision: 1.9 $
 */
public class MEXMECitaMedica extends X_EXME_CitaMedica implements GridItem {

    /** serialVersionUID */
	private static final long serialVersionUID = -6929453570057066493L;

    /** Static logger           */
    private static CLogger s_log = CLogger.getCLogger(MEXMECitaMedica.class);
   
    /** Especialidad */
    private MEXMEEspecialidad especialidad = null;
    
    /**
     * Especialidad de la cita medica como objeto
     * @return Especialidad objeto tipo Especialidad
     */
    public MEXMEEspecialidad getEspecialidad() {
    	if (especialidad == null){
    		especialidad = new MEXMEEspecialidad(getCtx(), getEXME_Especialidad_ID(), null);
    	}
		return especialidad;
	}

    /**
     *  Alimenta al objeto con la Especialidad de la cita
     *  medica en forma de objeto
     * @param especialidad objeto tipo Especialidad
     */
	public void setEspecialidad(MEXMEEspecialidad especialidad) {
		this.especialidad = especialidad;
	}

	/**
	 * Constructor
     * @param ctx
     * @param EXME_CitaMedica_ID
     * @param trxName
     */
    public MEXMECitaMedica(Properties ctx, int EXME_CitaMedica_ID, String trxName) {
        super(ctx, EXME_CitaMedica_ID, trxName);
    }

    /**
     * Constructor
     * @param ctx
     * @param rs
     * @param trxName
     */
    public MEXMECitaMedica(Properties ctx, ResultSet rs, String trxName) {
        super(ctx, rs, trxName);
    }
    
    /**
     * MEdico que ejecutolacita puede ser el sustituto 
     * o el medico tratante para quien fue agendado
     * @return
     */
    private MEXMEMedico medicoEjecutor = null;
    
    /**
     * MEdico que ejecutolacita puede ser el sustituto 
     * o el medico tratante para quien fue agendado
     * @return
     */
    public MEXMEMedico getMedicoEjecutor(){
    	
    	if(this.medicoEjecutor==null){

    		//El medico sustituto lo atendio
    		if(getSubstitute_ID()>0) {
    			this.medicoEjecutor = new MEXMEMedico(getCtx(), getSubstitute_ID(), get_TrxName()) ;
    		} else {
    			this.medicoEjecutor = new MEXMEMedico(getCtx(), getEXME_Medico_ID(), get_TrxName()) ;
    		}
    	}
    	
    	return this.medicoEjecutor; 	
    }
    
    private String financialClassName = null;
    private BigDecimal pagos = null;
    private BigDecimal cargos = null;
    
    public String getFinancialClassName() {
    	return financialClassName;
    }
    
    public BigDecimal getPagos() {
    	return pagos;
    }
    
    public BigDecimal getCargos() {
    	return cargos;
    }
    
    public void setFinancialClassName(String financialClassName) {
    	this.financialClassName = financialClassName;
    }
    
    public void setPagos(BigDecimal pagos) {
    	this.pagos = pagos;
    }
    
    public void setCargos(BigDecimal cargos) {
    	this.cargos = cargos;
    }
    /**
     * Producto de simboliza a la cita medica para su cobro
     * @param productoConfigurado Producto que existen en la configuracion de Expediente Clinico
     * @return
     */
//    private MProduct producto = null; 
    
    /**
     * Producto de simboliza a la cita medica para su cobro
     * @param productoConfigurado Producto que existen en la configuracion de Expediente Clinico
     * @return
     *
    public MProduct getProducto (int productoConfigurado) {

		if (this.producto == null) {

			final MEXMEMedico medico = getMedicoEjecutor();
			// lhernandez. Obtener el id del turno del médico
			int productoID = MEXMEMedicoOrg.getProductoMedico(getCtx(), medico.getEXME_Medico_ID());

			if (productoID <= 0) {
				// s_log.finer("Getting the product from the physician ...");
				// productoID = medico.getM_Product_ID();
				// } else{
				s_log.finer("Getting the product from the clinical file ...");
				productoID = productoConfigurado;
			}

			this.producto = new MProduct(getCtx(), productoID, get_TrxName());

		}

    	return this.producto;
    }*/
    
    /**
     * Cliente por defecto de la quien se responsabiliza de los gastos
     * generados por la cita medica
     * @return
     */
//    private MBPartner cliente = null;
   
    /**
     * Cliente por defecto de la quien se responsabiliza de los gastos
     * generados por la cita medica
     * @return
     *
    public MBPartner getCliente () {

    	if(this.cliente==null){

    		if(getPaciente().isEsAsegurado() && getPaciente().getC_BPartner_Seg_ID()>0)
    			this.cliente = new MBPartner(getCtx(), getPaciente().getC_BPartner_Seg_ID(), 
    					get_TrxName());
    		else
    			this.cliente = new MBPartner(getCtx(), getPaciente().getC_BPartner_ID(), 
    					get_TrxName());
    	}
    	return this.cliente;
    }*/

    /**
     * Determinar el estatus de la cita
     * @return
     *
    public int getEstatusActual () {

    	int estatusCita = 1;//CITA_CONFIRMADA Nada facturado (Default)
    	if (getC_Invoice_ID()>0)
    		/*GValdez
    		 *Ticket 2145 si la cita esta facturada, pero la factura fue devuelta
    		 *retorna no retornar estatus Facturada
    		*
    		if (!MInvoice.isCancelInvoice(getC_Invoice_ID()))
    			estatusCita = 2;//CITA_FACTURADA Cita facturada
    	if (getEstatus().equalsIgnoreCase(MEXMECitaMedica.ESTATUS_Executed))
    		estatusCita = 4;//CITA_EJECUTADA Cita ejecutada

    	return estatusCita;
    }*/
  
   
    /**
     * Copia una cita medica con diferente id
     * 
     * @author Julio Gutierrez
     * @param ctx
     * @param citaoriginal
     * @param id 
     * @return
     */
    public static MEXMECitaMedica copyFrom(Properties ctx, MEXMECitaMedica citaoriginal, int id){
	
    	final MEXMECitaMedica newObject = new MEXMECitaMedica(citaoriginal.getCtx(), 0, citaoriginal.get_TrxName());
		copyValues(citaoriginal, newObject);
		return newObject;
	}
    

    /**
     * WARNING: Creado para Interfaces Medsys.
     * @author hassan reyes
     */
    public void setClientOrg(int ad_client_id, int ad_org_id){
        setAD_Client_ID(ad_client_id);
        setAD_Org_ID(ad_org_id);
    }

    /**
     *  Obtenemos los datos de las citas que tiene determinado medico
     *  en una fecha por asistente
     * @param medico El medico a obtener sus citas
     * @param fecha La fecha de las citas
     * @param asistente La asistente que consulta las citas
     * @return El resultset con la informacion de las citas
     *
    @Deprecated
    public static ResultSet getCitasMedico(long medico, java.util.Date fecha, String clausWhere, long asistente, int especialidad) {
        //buscamos el medico y paciente en citas
    	
    	final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
    	
   	 	sql.append("SELECT EXME_CitaMedica.EXME_CitaMedica_ID, EXME_Paciente.Name as nom_pac, EXME_Paciente.Nombre2,")
   	 		.append("EXME_Paciente.Apellido1, EXME_Paciente.Apellido2,  EXME_Paciente.Sexo, EXME_CitaMedica.Estatus,")
   	 		.append("EXME_Paciente.EXME_Paciente_ID, EXME_Paciente.Value, EXME_CitaMedica.Edad, EXME_Medico.NoConsultorio,")
   	 		.append("EXME_Paciente.TelParticular, EXME_Paciente.FechaNac, TO_CHAR(EXME_CitaMedica.FechaHrCita, 'HH24') Hora,")
   	 		.append("TO_CHAR(EXME_CitaMedica.FechaHrCita, 'MI') Minutos, EXME_CitaMedica.Confirmada,EXME_CitaMedica.AD_Org_ID,")
   	 		.append("AD_User.Name as nom_usuario, EXME_CITAMEDICA.MOTIVOTS,")
   	 		.append("EXME_CitaMedica.CitaDe, EXME_Paciente.Expediente, ")//Expert: Javier Ochoa .- se agregaron nuevas columnas por configuraci�n
    		.append("NVL(EXME_Refer.DocumentNo,'') as referencia ")//Lama .- se agreg� el Numero de referencia de paciente externo
   	 		.append("FROM EXME_CitaMedica ")
   	 		.append("INNER JOIN EXME_Paciente ON (EXME_CitaMedica.EXME_Paciente_ID = EXME_Paciente.EXME_Paciente_ID AND EXME_Paciente.IsActive = 'Y') ")
   	 		.append("INNER JOIN EXME_Medico ON (EXME_CitaMedica.EXME_Medico_ID = EXME_Medico.EXME_Medico_ID AND EXME_Medico.IsActive = 'Y') ")
   	 		.append("INNER JOIN EXME_MedicoAsist ON (EXME_MedicoAsist.EXME_Medico_ID = EXME_Medico.EXME_Medico_ID AND EXME_MedicoAsist.IsActive = 'Y') ")
   	 		.append("INNER JOIN AD_User ON (AD_USER.AD_USER_ID = EXME_CITAMEDICA.CREATEDBY) ")
   	 		.append("LEFT JOIN EXME_Refer ON (EXME_Paciente.EXME_Refer_ID = EXME_Refer.EXME_Refer_ID AND EXME_Paciente.isRefer = 'Y') ")
   	 		
   	 		.append("WHERE EXME_CitaMedica.IsActive = 'Y' ")
   	 		.append("AND TO_CHAR(EXME_CitaMedica.FechaHrCita, 'DD/MM/YYYY') = ? ")
   	 		.append("AND EXME_Medico.EXME_Medico_ID = ? AND EXME_MedicoAsist.EXME_Asistente_ID = ? ")
   	 		.append("AND EXME_CitaMedica.Estatus <> '").append(ESTATUS_Cancelled).append("' ")
   	 		.append(especialidad>0 ? "AND EXME_CitaMedica.EXME_Especialidad_ID = " + especialidad + " " :"")
   	 		.append(clausWhere != null ? clausWhere : "")

   	 		.append("ORDER BY hora, minutos");

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = DB.prepareStatement(sql.toString(), null);
            pstmt.setString(1, Constantes.getSdfFecha().format(fecha));
            pstmt.setLong(2, medico);
            pstmt.setLong(3, asistente);
           
            rs = pstmt.executeQuery();
		} catch (Exception e) {
			s_log.log(Level.SEVERE, "getCitasMedico", e);
	
		}
        return rs;
    }*/

    /**
     *  Obtenemos los datos de las citas que tiene determinado medico
     *  en una fecha
     * @param medico El medico a obtener sus citas
     * @param fecha La fecha de las citas 
     * @return El resultset con la informacion de las citas
     */
    public static List<MEXMECitaMedicaView> getCitasMedico(Properties ctx, String clausWhere, long medico, java.util.Date fecha, int especialidad, boolean odontologia) {

    	final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
    	final List<MEXMECitaMedicaView> retValue = new ArrayList<MEXMECitaMedicaView>();
    	
    	 /*buscamos el medico y paciente en citas
    	sql.append("SELECT EXME_CitaMedica.EXME_CitaMedica_ID, EXME_Paciente.Name as nom_pac, EXME_Paciente.Nombre2, ")
    		.append("EXME_Paciente.Apellido1, EXME_Paciente.Apellido2, EXME_CitaMedica.Estatus, ")
    		.append("EXME_Paciente.EXME_Paciente_ID, EXME_Paciente.Value, EXME_Paciente.Sexo, EXME_CitaMedica.Edad, ")
    		.append("EXME_Medico.NoConsultorio, EXME_Paciente.TelParticular, EXME_Paciente.FechaNac, ")
    		.append("TO_CHAR(EXME_CitaMedica.FechaHrCita, 'HH24') Hora, ")
    		.append("TO_CHAR(EXME_CitaMedica.FechaHrCita, 'MI') Minutos, EXME_CitaMedica.Confirmada, ")
    		.append("EXME_CitaMedica.AD_Org_ID,EXME_CitaMedica.MotivoTS,AD_User.Name as nom_usuario, ")
    		.append("EXME_CitaMedica.CitaDe, EXME_Paciente.Expediente ")//Expert: Javier Ochoa .- se agregaron nuevas columnas por configuraci�n*/
    	//cambio por modelo de compiere
    	//Ren. EncounterStatus Estatus (A= Admission, C= Discharge)
    	sql.append("SELECT EXME_CitaMedica.*, EXME_CtaPac.EncounterStatus as CtaPacStatus, ")
    	//info paciente
    		.append("EXME_Paciente.EXME_Paciente_ID as pacID, EXME_Paciente.Value as PacValue, ")
    		.append("EXME_Paciente.nombre_pac as nom_pac, ")
    		.append(" EXME_Paciente.Sexo, EXME_Paciente.TelParticular, EXME_Paciente.FechaNac,");
			
    		if (DB.isOracle()) {
				sql.append("nvl(exp.Expediente,0) as expediente, ");
			} else if (DB.isPostgreSQL()) {
				sql.append("COALESCE(exp.Expediente, '0') as expediente, ");
			}
    		
    		sql.append("EXME_Medico.NoConsultorio, AD_User.Name as nom_usuario, ad_org.name as org_name, ")
    		.append("TO_CHAR(EXME_CitaMedica.FechaHrCita, 'HH24') Hora, ")
    		.append("TO_CHAR(EXME_CitaMedica.FechaHrCita, 'MI') Minutos ")
    		
    		.append("FROM EXME_CitaMedica ")
    		.append("LEFT JOIN EXME_CtaPac on EXME_CitaMedica.EXME_CtaPac_ID = EXME_CtaPac.EXME_CtaPac_ID ") 
    		.append("INNER JOIN EXME_Paciente ON (EXME_CitaMedica.EXME_Paciente_ID = EXME_Paciente.EXME_Paciente_ID ")
    		.append("AND EXME_Paciente.IsActive = 'Y' AND EXME_Paciente.IsRefer = 'N') ") //Lama (20080714) .- validar que no sea un paciente ingreso externo
    		.append("INNER JOIN EXME_Medico ON (EXME_CitaMedica.EXME_Medico_ID = EXME_Medico.EXME_Medico_ID AND EXME_Medico.IsActive = 'Y') ")
    		.append("INNER JOIN AD_User ON (AD_User.AD_User_ID = EXME_CitaMedica.CreatedBy) ")
    		.append("INNER JOIN AD_Org ON (AD_Org.AD_Org_ID = EXME_CitaMedica.AD_Org_ID) ")
    		.append("INNER JOIN C_BPartner ON (EXME_Paciente.C_BPartner_ID = C_BPartner.C_BPartner_ID) ") // Noelia: obtenemos el socio de negocios asignado al paciente
    		//freyes un solo mrn activo por org
    		.append("LEFT JOIN EXME_Hist_Exp exp ON (EXME_Paciente.EXME_Paciente_ID = exp.EXME_Paciente_ID and exp.isactive = 'Y' ")
    		.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", MEXMEHistExp.Table_Name, "exp"))
    		.append(") ")
    		.append("WHERE EXME_CitaMedica.IsActive = 'Y'")
    		.append("AND ");
    	
    	if (DB.isOracle()) {
    		sql.append("trunc(EXME_CitaMedica.FechaHrCita,'DD') ");
    	} else if (DB.isPostgreSQL()) {
    		sql.append("date_trunc('day', EXME_CitaMedica.FechaHrCita) ");
    	}
    	
    	sql.append("= TO_DATE(?,'DD/MM/YYYY') ")
    		.append("AND EXME_Medico.EXME_Medico_ID = ? ") //AND EXME_CitaMedica.processing = 'N' ")
    		.append("AND EXME_CitaMedica.Estatus <> '").append(ESTATUS_Cancelled).append("' ");
       
        if (especialidad > 0) {
            sql.append(" AND EXME_CitaMedica.EXME_Especialidad_ID = ").append(especialidad);
        }
        
        if (clausWhere != null) {
        	sql.append(clausWhere);
        }
        
        if (!odontologia) {
        	sql.append(" AND EXME_CitaMedica.EXME_Especialidad_ID <> ").append(MEXMECitaMedica_MO.getEspecialidadforOdonto(ctx,null));
        }
        
        sql.append(" ORDER BY hora, minutos");

        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setString(1, Constantes.getSdfFecha().format(fecha));
			pstmt.setLong(2, medico);
			rs = pstmt.executeQuery();
			
			//barremos las citas encontradas
			while (rs.next()) {
				final MEXMECitaMedicaView citasView = new MEXMECitaMedicaView(ctx,rs, null);

				if (rs.getLong("AD_Org_ID")==Env.getAD_Org_ID(ctx)) {
					citasView.setEditable("Y");
				} else {
					citasView.setEditable("N");
				}
				citasView.setInvoice(rs.getInt("C_INVOICE_ID"));
				citasView.setHistoria(rs.getString("PacValue"));
				citasView.setHora(rs.getString("Hora"));
				citasView.setMinutos(rs.getString("Minutos"));
				citasView.setSexo(rs.getString("Sexo"));
				citasView.setNombre(SecureEngine.decrypt(rs.getString("nom_pac")));
				citasView.setFechaNac(rs.getTimestamp("fechaNac"));
				citasView.setStringEdadAMD(CalcularEdadAMD.getAge(ctx,rs.getTimestamp("fechaNac")).getEdadAMD());						
				citasView.setTelefono(rs.getString("TelParticular"));
				citasView.setConsultorio(rs.getString("NoConsultorio"));
				citasView.setExpediente(rs.getString("Expediente"));
				citasView.setOrganizacion(rs.getString("org_name"));
				citasView.setCitaDeName(MRefList.getListName(ctx, MEXMECitaMedica.CITADE_AD_Reference_ID, citasView.getCitaDe()));
				citasView.setCtaPac(rs.getString("CtaPacStatus"));
				retValue.add(citasView);
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, "MEXMECitaMedica.getCitasMedico: sql: " + sql, e);
		} finally {
			DB.close(rs, pstmt);		
		}
		
		return retValue;
	}

  
    /**
	 * Obtenemos los datos de una determinada cita
	 * 
	 * @param cita
	 *            La cita a seleccionar
	 * @return El resultset con la informacion de la cita
	 */
    public static MEXMECitaMedicaView getCita(Properties ctx, int EXME_CitaMedica_ID, String trxName) {

    	MEXMECitaMedicaView cita = null;
    	
        //buscamos la cita
    	final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
        	
        sql.append("SELECT EXME_CitaMedica.*, ")
    		.append("EXME_Paciente.EXME_Paciente_ID as pacID, EXME_Paciente.Value as PacValue, ")
    		.append("EXME_Paciente.Apellido1, EXME_Paciente.Apellido2, EXME_Paciente.Apellido3, EXME_Paciente.Name pac_name, EXME_Paciente.Nombre2, ")
    		.append("EXME_Medico.Apellido1 || ' ' || EXME_Medico.Apellido2 || ' ' || EXME_Medico.Name NombreMedico, ")
    		.append("EXME_Paciente.TelParticular, EXME_Paciente.FechaNac, ")
    		.append("EXME_Medico.Value ValueMedico, ");
        
        //RQM: 2823: Agenda Medica - No es posible editar una cita.
        //Se adecua el codigo a postgresql. Jesus Cantu
        if (DB.isOracle()) {
        	sql.append("nvl(exp.Expediente,0) as expediente, ");
        } else if (DB.isPostgreSQL()) {
        	sql.append("COALESCE(exp.Expediente, '0') as expediente, ");
        }
        
    	sql.append("TO_CHAR(EXME_CitaMedica.FechaHrCita, 'HH24') Hora, ")
    		.append("TO_CHAR(EXME_CitaMedica.FechaHrCita, 'MI') Minutos, ")
    		.append("EXME_CitaMedica.FechaHrCita, ")
    		.append("TO_CHAR(EXME_CitaMedica.HoraLlegada, 'HH24') HoraConfirm, ")
    		.append("TO_CHAR(EXME_CitaMedica.HoraLlegada, 'MI') MinutosConfirm ")
    		
    		.append("FROM EXME_CitaMedica ")
    		.append("INNER JOIN EXME_Paciente ON (EXME_CitaMedica.EXME_Paciente_ID = EXME_Paciente.EXME_Paciente_ID) ")
    		.append("INNER JOIN EXME_Medico ON (EXME_CitaMedica.EXME_Medico_ID = EXME_Medico.EXME_Medico_ID AND EXME_Medico.IsActive = 'Y') ")
    		//freyes un solo mrn activo por org
    		.append("LEFT JOIN EXME_Hist_Exp exp ON (EXME_Paciente.EXME_Paciente_ID = exp.EXME_Paciente_ID and exp.isactive ='Y' ")
    		.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", MEXMEHistExp.Table_Name, "exp"))
    		.append(") ")
    		.append("WHERE EXME_CitaMedica.EXME_CitaMedica_ID = ? ");
        
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = DB.prepareStatement(sql.toString(), null);
            pstmt.setInt(1, EXME_CitaMedica_ID);
            rs = pstmt.executeQuery();
            
            if (rs.next()) {
            	cita = new MEXMECitaMedicaView(ctx,rs, null);           	

				cita.setMedicoValue(rs.getString("ValueMedico"));
				cita.setMedicoName(rs.getString("NombreMedico"));
				cita.setHistoria(rs.getString("PacValue"));
				cita.setPacName(rs.getString("pac_name"));
				cita.setPacName2(rs.getString("Nombre2"));
				cita.setPacApellido1(rs.getString("Apellido1"));
				cita.setPacApellido2(rs.getString("Apellido2"));
				cita.setPacApellido3(rs.getString("Apellido3"));
				cita.setTelefono(rs.getString("TelParticular"));
				cita.setFechaNac(rs.getTimestamp("FechaNac"));
				Date date = DB.convert(ctx, rs.getTimestamp("FechaHrCita"));
				DateTime dateTime = new DateTime(date);
				cita.setHora(StringUtils.leftPad(dateTime.getHourOfDay() + "", 2, "0"));
				cita.setMinutos(StringUtils.leftPad(dateTime.getMinuteOfHour() + "", 2, "0"));
				cita.setExpediente(rs.getString("Expediente"));
				cita.setHoraConfirm(rs.getString("HoraConfirm"));
				cita.setMinConfirm(rs.getString("MinutosConfirm"));
			}
        } catch (Exception e) {
			s_log.log(Level.SEVERE, "sql: getCita = " + sql + " param = " + EXME_CitaMedica_ID , e);
		} finally {
			DB.close(rs, pstmt);		
		}
		return cita;

    }
    
    
    /**
     *  Obtenemos las horas de consulta del medico
     *  @return Una lista con las horas de consulta
     *  @deprecated usado solamente por CitasAction / CitasDetalleAction / SelecMedicoAction
     *
    public static List<String> getHorasConsulta (Properties ctx, long medico, int especialidadID, String fecha) {
        List<String> lstHorasConsulta = null;

        try {
            //dia de la semana
            Calendar cal = Calendar.getInstance();
            Date strFecha = Constantes.getSdfFecha().parse(fecha);  
            cal.setTime(strFecha);

            int dia = cal.get(Calendar.DAY_OF_WEEK);
            s_log.log(Level.INFO,"dia de la semana " + dia);

            HashMap<String, String>  rs = MEXMEMedico.getHorario(ctx, medico);
            if (rs.size() > 0) {
                //int intervalo = rs.getInt("IntervaloConsulta");
            	int intervalo = MEXMEMedico.getIntervaloConsulta(ctx, (int)medico, especialidadID, null);
            	
                //horario de fin de semana
                String hora1a = rs.get("HoraEnt1Fs");
                String hora2a = rs.get("HoraSal1Fs");

                //primer horario entre semana
                String hora1b = rs.get("HoraEnt1Es");
                String hora2b = rs.get("HoraSal1Es");

                //segundo horario entre semana
                String hora1c = rs.get("HoraEnt2Es");
                String hora2c = rs.get("HoraSal2Es");

                s_log.log(Level.SEVERE,"1 " + hora1a+" 2 "+ hora2a+" 3 "+hora1b +" 4 "+hora2b +" 5 "+ hora1c+" 6 "+hora2c);
              
                if(intervalo==0) {
                    intervalo = 30;
                }

                lstHorasConsulta = new ArrayList<String>();

                //si es finde semana y tiene horario de finde semana
                if ((dia==Calendar.SUNDAY || dia==Calendar.SATURDAY) && hora1a !=null && hora2a != null){
                    lstHorasConsulta = getHorasConsul (hora1a, hora2a, intervalo);
                }else{
                        //tiene el 1er horario
                        if (hora1b !=null && hora2b != null)
                            lstHorasConsulta = getHorasConsul (hora1b, hora2b, intervalo);

                        //tiene el 2do horario
                        if (hora1c != null && hora2c != null){
                            //si tiene tambien el 1er horario y el segundo
                                if( lstHorasConsulta.size()!=0){
                                    List<String> lista = getHorasConsul (hora1c, hora2c, intervalo);
                                    for(int i=0; i<lista.size(); i++){
                                        lstHorasConsulta.add(lista.get(i).toString());}
                                }else{
                                    //si solo tiene el 2do horario
                                    lstHorasConsulta = getHorasConsul (hora1c, hora2c, intervalo);
                                }
                        }
                }//fin else sin hoarario de fin
            }
            
        } catch (Exception e)
        {
            s_log.log(Level.SEVERE, "getHorasConsulta", e);
        } 

        return lstHorasConsulta;

    }*/

    /**
     * Horas de consulta
     * @param strHoraIni
     * @param strHoraFin
     * @param intervalo
     * @return
     * @deprecated
     *
    public static List<String> getHorasConsul (String strHoraIni, String strHoraFin, int intervalo) {

        //lista con las horas de consulta
    	final List<String> lstHorasConsulta = new ArrayList<String>();
        //java.text.DecimalFormat dosEnteros = new java.text.DecimalFormat("00");

        try {
            int dosPuntos = strHoraIni.indexOf(':');
            
            int horas = 0;
            int minutos = 0;
            if(dosPuntos<0){
            	horas = Integer.parseInt(strHoraIni.trim());
            } else {
            	horas = Integer.parseInt(strHoraIni.substring(0, dosPuntos));
            	minutos = Integer.parseInt(strHoraIni.substring(dosPuntos+1, strHoraIni.length()));
            }
            
            //minutos iniciales y finales (para efectos de calculos)
            int minutosIni = horas * 60 + minutos;

            dosPuntos = strHoraFin.indexOf(':');
            horas = 0;
            minutos = 0;
            if(dosPuntos<0){
            	horas = Integer.parseInt(strHoraFin.trim());
            } else {
            	horas = Integer.parseInt(strHoraFin.substring(0, dosPuntos));
            	minutos = Integer.parseInt(strHoraFin.substring(dosPuntos+1, strHoraFin.length()));
            }

            //minutos iniciales y finales (para efectos de calculos)
            int minutosFin = horas * 60 + minutos;

            for(int i=minutosIni; i<=minutosFin; i=i+intervalo) {
                horas = (int) i/60;
                minutos = i - horas * 60;
                lstHorasConsulta.add(Constantes.getDosDigitos().format(horas));
                lstHorasConsulta.add(Constantes.getDosDigitos().format(minutos));
            }

        } catch (Exception e) {
            s_log.log(Level.SEVERE, "getHorasConsul", e);
        } 

        return lstHorasConsulta;
    }*/

    /**
     *  Obtenemos la citas programadas o el historial para un paciente en particular
     * @param paciente el paciente a obtener sus citas
      * @param prog Una cadena que nos dice si obtendremos las citas programadas o el historial
      * @param ctx El contexto de la aplicacion
      * @param all Ver todas las citas
     * @return Una lista de objetos CitaProgView con la informacion de las citas
     * encontradas
     *
    public static List<CitaProgView> getCitasProg(long paciente, String prog, Properties ctx, boolean all){

//    	final String decendente = "DESC";
        java.sql.Date sqlDate = DB.getDateForOrg(ctx);//new java.sql.Date(Calendar.getInstance().getTime().getTime());
        
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ");
        sql.append("  EXME_CitaMedica.EXME_CitaMedica_ID, ");
        sql.append("  EXME_CitaMedica.FechaHrCita, ");
        sql.append("  EXME_Medico.Name || ' ' || EXME_Medico.Apellido1 ");
        sql.append("  AS m_name, ");
        sql.append("  EXME_CitaMedica.EXME_Especialidad_ID ");
        sql.append("FROM ");
        sql.append("  EXME_CitaMedica ");
        sql.append("  INNER JOIN EXME_Medico ");
        sql.append("  ON (EXME_CitaMedica.EXME_Medico_ID = EXME_Medico.EXME_Medico_ID) ");
        
		if (!all) {
			sql.append("  INNER JOIN EXME_MedicoAsist ");
			sql.append("  ON (EXME_Medico.EXME_Medico_ID = EXME_MedicoAsist.EXME_Medico_ID) ");
		}
        
        sql.append("WHERE ");
        sql.append("  EXME_CitaMedica.EXME_Paciente_ID = ? AND ");
        sql.append("  EXME_CitaMedica.Estatus <> '").append(ESTATUS_Cancelled).append("' ");
        
        if(prog != null){
            if (prog.equalsIgnoreCase("SI")){
                sql.append("AND EXME_CitaMedica.fechahrcita >= TO_DATE('"+ sqlDate.toString() +"','yyyy-MM-dd') ");
            }else{
                sql.append("AND EXME_CitaMedica.fechahrcita < TO_DATE('"+ sqlDate.toString() +"','yyyy-MM-dd') ");
            }
        }
        
        sql.append("  AND EXME_CitaMedica.IsActive = 'Y' ");
        sql.append("  AND EXME_Medico.IsActive = 'Y'");
        
		if (!all) {
			if (Env.getEXME_Asistente_ID(ctx) <= 0) {
				s_log.log(Level.INFO, ">>>>> No existe  Atributo #EXME_Asistente_ID en el Contexto <<<<<");
				return null;
			}
			
			sql.append("  AND EXME_MedicoAsist.IsActive = 'Y' AND ");
			sql.append("  EXME_MedicoAsist.EXME_Asistente_ID =  ").append(Env.getEXME_Asistente_ID(ctx));
		}
        
        sql.append(" ORDER BY ");
        sql.append("  EXME_CitaMedica.FechaHrCita ");

        if(prog != null)
            if(prog.equalsIgnoreCase("NO"))
                sql.append("DESC");

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        ArrayList<CitaProgView> list = new ArrayList<CitaProgView>();

        try {
            //Resultset para los datos del medico
            pstmt = DB.prepareStatement(sql.toString(), null);
            pstmt.setLong(1, paciente);
            rs = pstmt.executeQuery();

            while(rs.next()){
                CitaProgView citaProgView = new CitaProgView();
                citaProgView.setCitaMedica(rs.getLong(1));
                citaProgView.setFechaHr((java.util.Date)rs.getTimestamp(2,Calendar.getInstance()));
                citaProgView.setMedico(rs.getString(3));
                citaProgView.setEspecialidadID(rs.getInt(4));
                citaProgView.setCtx(ctx);

                list.add(citaProgView);
            }

		} catch (Exception e) {
			s_log.log(Level.SEVERE, "getCitasProg", e);
		} finally {
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}

        return list;
    }*/

    /**
     * @deprecated - usada en TableroCitasAction
     * Devuelve una cita medica especifica para nostrarla en el tablero de citas
     *
     * @param xxMedicoId El medico del que se estan consultando las citas
     * @param fecha La fecha de las citas
     * @param ctx El contexto de la aplicacion
     *
     * @return Un valor CitaTableroView con los datos de la cita
     * @throws Exception en caso de ocurrir un error al hacer la consulta
     *
    public static CitaTableroView getCita(long xxMedicoId, java.util.Date fecha, Properties ctx) throws Exception{

    	final CitaTableroView cita = new CitaTableroView();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try{
            String sql = "SELECT p.Apellido1||' '||p.Apellido2||' '||p.Name AS Nombre, " +
                  "c.Description AS Observaciones, c.EXME_CitaMedica_ID " +
                  "FROM EXME_Paciente p, EXME_CitaMedica c " +
                  "WHERE c.EXME_Medico_ID = ? " + 
                  "AND c.FechaHrCita = TO_DATE(?, 'dd/MM/yyyy HH24:mi') " +
                  "AND c.AD_Client_ID = ? AND c.AD_Org_ID = ?";

             pstmt = DB.prepareStatement(sql, null);
             pstmt.setLong(1, xxMedicoId);
             pstmt.setString(2, Constantes.getSdfFechaHora().format(fecha));
             pstmt.setLong(3, Env.getAD_Client_ID(ctx));
             pstmt.setLong(4, Env.getAD_Org_ID(ctx));

             rs = pstmt.executeQuery();
             if(rs.next()) {
                 cita.setXxCitaMedicaId(rs.getLong("EXME_CitaMedica_ID"));
                 cita.setNomPaciente(rs.getString("Nombre"));
                 cita.setObservaciones(rs.getString("Observaciones"));
                 cita.setOcupado(1);
             } else {
                 cita.setXxCitaMedicaId(0);
             }

		} catch (Exception e) {
			s_log.log(Level.SEVERE, "getCitasProg", e);

		} finally {
        	DB.close(rs, pstmt);
            rs = null;
            pstmt = null;
        }
        return cita;
    }*/

    /**
     * Paciente
     * @return objeto de l paciente
     */
    public MEXMEPaciente getPaciente(){
    	if(m_paciente == null && getEXME_Paciente_ID() != 0)
    		m_paciente = new MEXMEPaciente(getCtx(), getEXME_Paciente_ID(), get_TrxName());
    	return m_paciente;
    }
    
    /**
     * Objeto del medico
     */
    private MEXMEMedico m_medico = null;
    
    /**
     * Objeto del paciente
     */
    private MEXMEPaciente m_paciente = null;
    
    /**
     * Objeto de medico de la cita medica 
     * @return
     */
    public MEXMEMedico getMedico(){
    	if(m_medico == null && getEXME_Medico_ID() != 0)
    		m_medico = new MEXMEMedico(getCtx(), getEXME_Medico_ID(), get_TrxName());
    	return m_medico;
    }
    
    /**
     * Id de la especialidad
     * para que no cause confusion en el jsp
     * @return
     */
    public int getEspecialidadID(){
    	return getEXME_Especialidad_ID();
    }
    
    /**
     * id de la cita medica
     * para que no cause confusion en el jsp
     * @return
     */
    public int getId(){
    	return getEXME_CitaMedica_ID();
    }
    
	/**
	 * Metodo que obtiene las citas a facturar
	 * @param ctx
	 * @param fecha
	 * @param trxName
	 * @return
	 */
	public static List<MEXMECitaMedica> getCitasFacturacion(final Properties ctx, final String fecha, final String trxName) {

		if (ctx == null) {
			return null;
		}

		final ArrayList<MEXMECitaMedica> list = new ArrayList<MEXMECitaMedica>();
		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY)

		/* Obtenemos las citas pendientes a facturar confirmada o no confirmadas */
		.append(" SELECT DISTINCT(EXME_CitaMedica.EXME_CitaMedica_ID), EXME_CitaMedica.* ")
		.append(" FROM EXME_CitaMedica ")
		.append(" INNER JOIN EXME_Paciente pac on( EXME_CitaMedica.EXME_Paciente_id = pac.EXME_Paciente_id AND pac.isactive='Y') ")
		.append(" INNER JOIN EXME_Ctapac cp on( EXME_CitaMedica.EXME_CtaPac_id = cp.EXME_CtaPac_id AND cp.isactive='Y') ")
		.append(" INNER JOIN EXME_CtapacDet cpd on( cp.EXME_CtaPac_id = cpd.EXME_CtaPac_id) ")
		.append(" INNER JOIN C_BPartner bp on( bp.C_BPartner_id=pac.C_BPartner_id AND bp.isactive='Y' ) ")
		.append(" WHERE to_date(to_char(EXME_CitaMedica.fechahrcita,'DD/MM/YYYY'),'DD/MM/YYYY') >= to_Date(?,'DD/MM/YYYY') ")
		.append(" AND EXME_CitaMedica.Estatus = '").append(MEXMECitaMedica.ESTATUS_Executed).append("' ")
		.append(" AND EXME_CitaMedica.C_Invoice_ID IS NULL ")
		.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", MEXMECitaMedica.Table_Name))
		.append(" ORDER BY EXME_CitaMedica.EXME_Medico_ID, EXME_CitaMedica.fechahrcita  ");

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setString(1, fecha);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				final MEXMECitaMedica citaMed = new MEXMECitaMedica(ctx, rs, trxName);
				list.add(citaMed);
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, "getCitasFacturacion", e);
		} finally {
			DB.close(rs, pstmt);
		}
		return list;
	}

    /**
     * Calculo de Precio de la cita medica
     * @param ctx
     * @param medicoID
     * @param pacienteID
     * @param tipoArea
     * @param areaID
     * @param especialidadID
     * @param listaAUsar
     * @param bPartnerID
     * @param warehouseID
     * @param configPre
     * @param configEC
     * @param paciente
     * @param medico
     * @param trxName
     * @return
     * @deprecated
     */
//	public static MPrecios getPrecioCitaMedica(Properties ctx, 
//    		int medicoID, int pacienteID, 
//    		String tipoArea, int areaID, int especialidadID, String listaAUsar, 
//    		int bPartnerID, int warehouseID, 
//    		MConfigPre configPre, MConfigEC configEC,   
//    		MEXMEPaciente paciente, MEXMEMedico medico, String trxName){
//    	
//    	final MPrecios precio = new MPrecios(); 
//    	BigDecimal precioLista = Env.ZERO;
//    	
//    	//Obtenemos el prod del servicio de consulta
//    	if(medico == null)
//    		medico = new MEXMEMedico(ctx, medicoID, trxName);
//    	
//    	if(configEC == null)
//    		configEC = MConfigEC.get(ctx, trxName); 
//    	
//    	if(configPre == null)
//    		configPre = MConfigPre.get(ctx, trxName);
//    	
//    	MProduct producto = null;
//    	/**lhernandez. Obtener el producto relacionado al médico**/
//    	int productoId = 0;
//    	productoId = MEXMEMedicoOrg.getProductoMedico(ctx, medico.getEXME_Medico_ID());
//    	/**lhernandez**/
//    	if(productoId>0) {
//    		s_log.finer("Getting the product from the physician ...");
//    		producto = new MProduct(ctx, productoId, trxName);
//    	} else {
//    		s_log.finer("Getting the product from the clinical file ...");
//   			producto = new MProduct(ctx, configEC.getM_Product_ID(), trxName);
//    	}
//
//    	
//    	//Medico Especialidad
//    	MEXMEMedicoEsp medicoEsp = MEXMEMedicoEsp.get(ctx, medico.getEXME_Medico_ID(), especialidadID, trxName);
//    	if(medicoEsp != null) {
//    		s_log.finer("We got the physician / speciality ...");
//    		precioLista = medicoEsp.getPriceList()==null?Env.ZERO:medicoEsp.getPriceList();
//    	}
//    	    	
//    	//Especialidad
//    	if(precioLista.compareTo(Env.ZERO)<=0 && especialidadID > 0){
//	    	MEXMEEspecialidad especialidad = new MEXMEEspecialidad(ctx, especialidadID, trxName);
//	    	precioLista = especialidad.getPriceList()==null?Env.ZERO:especialidad.getPriceList();
//    	}
//    	if(precioLista != null && precioLista.compareTo(Env.ZERO)>0){
//    		
//    		s_log.finer("precioLista != null && precioLista > 0 ...");
//    		
//    		precio.setCosto(precioLista);
//    		precio.setC_BPartner_ID(bPartnerID);
//    		precio.setM_PriceList_ID(0);
//    		precio.setTaxIncluded(false);
//    		precio.setUsoFactor(false);
//    		precio.setM_Product_ID(producto.getM_Product_ID());
//    	}
//    	//Configuracion global
//    	if(precioLista.compareTo(Env.ZERO)<=0){
//    		
//    		s_log.finer("precioLista <= 0 ...");
//    /*		
//	        PreciosVenta.m_configPre =  configPre;
//	        PreciosVenta.m_Paciente = paciente;
//	   */     
//	        if(paciente == null) {
//	        	s_log.finer("Paciente is null ...");
//	        	//PreciosVenta.m_Paciente = new MEXMEPaciente(ctx, pacienteID, trxName);
//	        }
//	        
//	  //      PreciosVenta.m_ConfigEC =  configEC; 
//	
//	        MPrecios pv = 
//	        	PreciosVenta.precioProdVta(
//	        			ctx, 
//	        			tipoArea,
//	        			producto.getM_Product_ID(), 
//	        			Env.ONE, 
//	        			producto.getC_UOM_ID(), 
//	        			listaAUsar, 
//	        			0, 
//	        			bPartnerID,  
//	        			warehouseID, 
//	        			warehouseID, 
//	        			areaID,
//	        			DB.getTimestampForOrg(ctx), 
//	        			false, 
//	        			trxName
//	        	);
//	        
//	        
//	        precioLista = pv.getPriceList();
//	        
//	        s_log.finer("precioLista from MPrecios.getPrigeList() = " + precioLista + " ...");
//    	}
//    	
//    	//Calculos de convenios
//    	precio.setPriceList(precioLista);
//		MPrecios price = MEsquemaDescuento.descuentos(ctx,
//				precio, areaID,
//				bPartnerID, tipoArea,
//				producto.getM_Product_ID(), 
//				producto.getC_UOM_ID(), 
//				DB.getTimestampForOrg(ctx), 
//				trxName);
//
//		if(price!= null && (price.getPriceStd()== null || price.getPriceStd().compareTo(Env.ZERO)<=0)){
//			price.setPriceStd(precioLista);
//		}
//
//		price.setPriceLimit(price.getPriceStd());
//		price.setM_Product_ID(producto.getM_Product_ID());
//    	return price;
//    }
//    
    /**
     * Calculo de Precio de la cita medica
     * @param ctx
     * @param medicoID
     * @param pacienteID
     * @param tipoArea
     * @param areaID
     * @param especialidadID
     * @param listaAUsar
     * @param bPartnerID
     * @param warehouseID
     * @param configPre
     * @param configEC
     * @param paciente
     * @param medico
     * @param trxName
     * @return
     *
	public MPrecios getPrecioCitaMedica(String tipoArea, 
    		int areaID, int warehouseID, 
    		MConfigPre configPre, MConfigEC configEC,   
    		String trxName){
    	
    	if(tipoArea==null || tipoArea.length()<=0)
    		return null;
    	if(areaID<=0)
    		return null;
    	if(warehouseID<=0)
    		return null;
    	if(configPre==null)
    		return null;
    	if(configEC==null)
    		return null;
    	
    	
    	MPrecios precio = new MPrecios(); 

    	final MEXMEPaciente paciente =  getPaciente();
    	
    	final MEXMEMedico medico = getMedicoEjecutor();
    	if( medico.getEXME_Medico_ID()<=0)
    		return null;

    	final MProduct producto  = getProducto(configEC.getM_Product_ID());
    	if( producto.getM_Product_ID()<=0)
    		return null;
    	
    	/*
    	 * OBTENEMOS EL PRECIO CONFIGURADO DE LA CITA
    	 */

    	//Producto - M�dico
   /* 	PreciosVenta.m_configPre = configPre;
        PreciosVenta.m_Paciente  = paciente;
        PreciosVenta.m_ConfigEC  = configEC; 
       *
        //Los parametros estan estrechamente ligados al servicio de la consulta
        //por que la unidad de medida es del sevicio y no elegida por el usuario
        //y el socio de negocios se obtiene de la relacion (paciente-aseguradora)
        //pero ya lo hace la clase que se esta llamando
        precio = 
        	PreciosVenta.precioProdVta(
        			getCtx(),
        			producto.getM_Product_ID(), 
        			Env.ONE, 
        			producto.getC_UOM_ID(), 
        			PreciosVenta.PVCE, 
        			warehouseID, 
        			warehouseID, 
        			new Timestamp(System.currentTimeMillis()), 
        			trxName
        	);
    	
     // Expert. Precios en cero
        //Medico Especialidad
    	if(Env.ZERO.compareTo(precio.getPriceList())>=0){
        	MEXMEMedicoEsp medicoEsp = MEXMEMedicoEsp.get(getCtx(), medico.getEXME_Medico_ID(), getEspecialidadID(), trxName);
        	if(medicoEsp != null && medicoEsp.getPriceList()!=null) 
        		precio.setPriceList(medicoEsp.getPriceList());
    	}
    	
    	//Especialidad
    	if(Env.ZERO.compareTo(precio.getPriceList())>=0){
	    	MEXMEEspecialidad especialidad = new MEXMEEspecialidad(getCtx(), getEspecialidadID(), trxName);
	    	if(especialidad != null && especialidad.getPriceList()!=null) 
	    	precio.setPriceList(especialidad.getPriceList());
    	}
    	
    	//Obtenemos el convenio
    	if(precio.getPriceList() != null && precio.getPriceList().compareTo(Env.ZERO)>0){    	

    		//Calculos de convenios
    		precio = MEsquemaDescuento.descuentos(getCtx(),
    				precio, areaID,
    				tipoArea,
    				producto.getC_UOM_ID(), 
    				new Timestamp(System.currentTimeMillis()), 
    				trxName);

    		if(precio!= null 
    				&& (precio.getPriceStd()== null || precio.getPriceStd().compareTo(Env.ZERO)<=0)){
    			precio.setPriceStd(precio.getPriceList());
    			precio.setPriceLimit(precio.getPriceStd());
    		}

    		
    	}
    	
    	//Impuestos
    	precio.setC_TaxCategory_ID(producto.getC_TaxCategory_ID());
        
    	return precio;
    }*/
    
    /**
     * Calculo de Precio de la cita medica solo considerando el socio de negocios
     * y no la configuracion de medico y medico especialidad
     * @param ctx
     * @param medicoID
     * @param pacienteID
     * @param tipoArea
     * @param areaID
     * @param especialidadID
     * @param listaAUsar
     * @param bPartnerID
     * @param warehouseID
     * @param configPre
     * @param configEC
     * @param paciente
     * @param medico
     * @param trxName
     * @return
     *
	public MPrecios getPrecioCitaMedicaRecalculado(MEXMELineasFactView cargo,
    		String tipoArea, String liga, int EXME_Area_ID,   Timestamp fecha,
    		MConfigPre configPre , MConfigEC configEC, String trxName) {


    	if(tipoArea==null || tipoArea.length()<=0)
    		return null;
    	if(EXME_Area_ID<=0)
    		return null;
    	if(configPre==null)
    		return null;
    	if(configEC==null)
    		return null;
    	if(liga==null)
    		return null;
    	if(cargo==null)
    		return null;
    	if(fecha==null)
    		return null;

    	MPrecios precio = new MPrecios(); 
    	final MEXMEPaciente paciente = new MEXMEPaciente(getCtx(), getEXME_Paciente_ID(),trxName);;

    	/*
    	 * OBTENEMOS EL PRECIO 
    	 */
/*
    	PreciosVenta.m_configPre = configPre;
    	PreciosVenta.m_Paciente  = paciente;
    	PreciosVenta.m_ConfigEC  = configEC; 
*
    	precio = 
    		PreciosVenta.precioProdVta( 
    				getCtx(),     
    				tipoArea,
    				cargo.getM_Product_ID(),   
    				cargo.getCantidad(), 
    				cargo.getC_UOM_ID(),       
    				liga,       
    				0, 
    				0, 
    				cargo.getM_Warehouse_ID(),       
    				cargo.getM_Warehouse_ID(), 
    				EXME_Area_ID,   
    				fecha,
    				true, trxName);


    	return precio;
    }*/
    /**
     * Metodo que regresa un mapa de listas con los horarios disponibles 
     * Key ("Horaini") = Regresa una Lista de String con los horarios en donde inicia el bloque de disponibilidad
     * Key ("Horafin") = Regresa una Lista de String con los horarios en donde finaliza el bloque de disponibilidad
     * 
     * @author Julio Gutierrez
     * @param medico
     * @param fecha
     * @param asistente 
     * @return regresa un mapa con las listas de los horarios disponibles
     * @deprecated
     */
    public static HashMap<String, List<String>> getCitasMedicoMap(
    		Properties ctx, int medico, Date fecha) {
    	
    	final List<String> horaini = new ArrayList<String>();
    	final List<String> horafin = new ArrayList<String>();
    	ResultSet rs = null;
    	
    	try {
    		// Tareas del medico: citas, prg. quirofanos y actividades
    		rs = AgendaMedicaModel.getDetail(ctx, medico, 
    				Constantes.getSdfFecha().format(fecha), 
    				Constantes.getSdfFecha().format(fecha));
    		
    		// guardo los valores en las listas
    		while (rs.next()) { 

    			final Timestamp StartDate = rs.getTimestamp("fechaini");//Fecha inicio real o programada
    			final Timestamp EndDate = rs.getTimestamp("fechafin");//Fecha fin real o calculada

    			String horaMinIni = Constantes.getSdfHora24().format(StartDate);
    			String horaMinFin = Constantes.getSdfHora24().format(EndDate);
    			horaini.add(horaMinIni);
    			horafin.add(horaMinFin);
    		}
    		
    	} catch (Exception e) {
    		s_log.log(Level.SEVERE, e.getMessage(), e);
    	} finally {
    		DB.close(rs);
    	}
    	
    	// Meto las listas a un mapa
    	HashMap<String, List<String>> fechasocupadas = new HashMap<String, List<String>>();
    	fechasocupadas.put("Horaini", horaini);
    	fechasocupadas.put("Horafin", horafin);
    	return fechasocupadas;
    }

    /**
    * Obtenemos los datos de las citas que tiene determinado medico
    * en una fecha por asistente
    * @param medico El medico a obtener sus citas
    * @param fecha La fecha de las citas
    * @param asistente La asistente que consulta las citas
    * @return El resultset con la informacion de las citas
    *
	@Deprecated
    public static ResultSet getCitasMedico(long medico, java.util.Date fecha, long asistente) {
		// buscamos el medico y paciente en citas
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
    	
    	 sql.append("SELECT DISTINCT EXME_CitaMedica.EXME_CitaMedica_ID, TO_CHAR(EXME_CitaMedica.FechaHrCita, 'HH24') Hora,")
    		.append("TO_CHAR(EXME_CitaMedica.FechaHrCita, 'MI') Minutos, EXME_MedicoEsp.Intervaloconsulta,")
    		.append("EXME_CitaMedica.EXME_Especialidad_ID ")
    		.append(", Exme_citamedica.duracion ")
    		.append("FROM EXME_CitaMedica ")
    		.append("INNER JOIN EXME_Medico ON (EXME_CitaMedica.EXME_Medico_ID = EXME_Medico.EXME_Medico_ID AND EXME_Medico.IsActive = 'Y') ")
    		.append("INNER JOIN EXME_MedicoAsist ON (EXME_MedicoAsist.EXME_Medico_ID = EXME_Medico.EXME_Medico_ID AND EXME_MedicoAsist.IsActive = 'Y') ")
    		.append("INNER JOIN AD_User ON (AD_USER.AD_USER_ID = EXME_CITAMEDICA.CREATEDBY) ")
    		.append("INNER JOIN EXME_MedicoEsp ON (EXME_MedicoEsp.EXME_Especialidad_ID = EXME_CitaMedica.EXME_Especialidad_ID ")
    		.append("AND EXME_MedicoEsp.EXME_Medico_ID = EXME_Medico.EXME_Medico_ID) ")
    		.append("WHERE EXME_CitaMedica.IsActive = 'Y' ")
    		.append("AND EXME_CitaMedica.Estatus <> '").append(ESTATUS_Cancelled).append("' ")
    		.append("AND TO_CHAR(EXME_CitaMedica.FechaHrCita, 'DD/MM/YYYY') = ? ")
    		.append("AND EXME_Medico.EXME_Medico_ID = ? AND EXME_MedicoAsist.EXME_Asistente_ID = ? ")
    		.append("ORDER BY hora, minutos");

    	PreparedStatement pstmt = null;
    	ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setString(1, Constantes.getSdfFecha().format(fecha));
			pstmt.setLong(2, medico);
			pstmt.setLong(3, asistente);

			rs = pstmt.executeQuery();
		} catch (Exception e) {
			s_log.log(Level.SEVERE, "getCitasMedico", e);
		}
		return rs;
    }*/
    
    /**
     * @deprecated - No referencias en clases
     * @return
     *
    public String getEdadFormatoLargo(){
    	return MEXMEEdadPaciente_V.getEdad(getCtx(), getEXME_Paciente_ID());
    }*/
    
    

    /**
     *  Obtenemos los datos de las citas que tiene determinado medico
     *  en una fecha por asistente
     * @param medico El medico a obtener sus citas
     * @param fecha La fecha de las citas
     * @param asistente La asistente que consulta las citas
     * @return El resultset con la informacion de las citas
     *
    @Deprecated
    public static ResultSet getCitasMedicoTS(long medico, java.util.Date fecha, long asistente) {
        //buscamos el medico y paciente en citas
        String sql = "SELECT EXME_CitaMedica.EXME_CitaMedica_ID, EXME_Paciente.Name as nom_pac, EXME_Paciente.Nombre2, "
              + "EXME_Paciente.Apellido1, EXME_Paciente.Apellido2,  EXME_Paciente.Sexo, EXME_CitaMedica.Estatus, "
              + "EXME_Paciente.EXME_Paciente_ID, EXME_Paciente.Value, EXME_CitaMedica.Edad, EXME_Medico.NoConsultorio, "
              + "EXME_Paciente.TelParticular, EXME_Paciente.FechaNac, TO_CHAR(EXME_CitaMedica.FechaHrCita, 'HH24') Hora, "
              + "TO_CHAR(EXME_CitaMedica.FechaHrCita, 'MI') Minutos, EXME_CitaMedica.Confirmada, "
              + "EXME_CitaMedica.AD_Org_ID, "
              + "AD_User.Name as nom_usuario, EXME_CITAMEDICA.MOTIVOTS "
              + "FROM EXME_CitaMedica, EXME_Paciente, EXME_Medico, EXME_MedicoAsist, AD_User "
              + "WHERE EXME_CitaMedica.EXME_Paciente_ID = EXME_Paciente.EXME_Paciente_ID "
              + "AND EXME_CitaMedica.EXME_Medico_ID = EXME_Medico.EXME_Medico_ID "
              + "AND EXME_MedicoAsist.EXME_Medico_ID = EXME_Medico.EXME_Medico_ID "
              + "AND TO_CHAR(EXME_CitaMedica.FechaHrCita, 'DD/MM/YYYY') = ? "
              + "AND EXME_Medico.EXME_Medico_ID = ? AND EXME_MedicoAsist.EXME_Asistente_ID = ? "
              + "AND EXME_CitaMedica.IsActive = 'Y' AND EXME_Paciente.IsActive = 'Y' "
              + "AND EXME_Medico.IsActive = 'Y' AND EXME_MedicoAsist.IsActive = 'Y' "
              + "AND EXME_CitaMedica.Estatus <> '" + ESTATUS_Cancelled + "' "
              + "AND AD_USER.AD_USER_ID = EXME_CITAMEDICA.CREATEDBY "
              + "ORDER BY hora, minutos";

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = DB.prepareStatement(sql, null);
            pstmt.setString(1, Constantes.getSdfFecha().format(fecha));
            pstmt.setLong(2, medico);
            pstmt.setLong(3, asistente);
            rs = pstmt.executeQuery();
		} catch (Exception e) {
			s_log.log(Level.SEVERE, "getCitasMedico", e);
		}
		return rs;
    }*/

    /**
     *  Obtenemos los datos de las citas que tiene determinado medico
     *  en una fecha
     * @param medico El medico a obtener sus citas
     * @param fecha La fecha de las citas 
     * @return El resultset con la informacion de las citas
     *
    @Deprecated
    public static ResultSet getCitasMedicoTS(long medico, java.util.Date fecha) {

        //buscamos el medico y paciente en citas
        String sql = "SELECT EXME_CitaMedica.EXME_CitaMedica_ID, EXME_Paciente.Name as nom_pac, EXME_Paciente.Nombre2, "
              + "EXME_Paciente.Apellido1, EXME_Paciente.Apellido2, EXME_CitaMedica.Estatus, "
              + "EXME_Paciente.EXME_Paciente_ID, EXME_Paciente.Value, EXME_Paciente.Sexo, EXME_CitaMedica.Edad, "
              + "EXME_Medico.NoConsultorio, EXME_Paciente.TelParticular, EXME_Paciente.FechaNac,  "
              + "TO_CHAR(EXME_CitaMedica.FechaHrCita, 'HH24') Hora, "
              + "TO_CHAR(EXME_CitaMedica.FechaHrCita, 'MI') Minutos, EXME_CitaMedica.Confirmada, "
              + "EXME_CitaMedica.AD_Org_ID,EXME_CitaMedica.MotivoTS, "
              + "AD_User.Name as nom_usuario "
              + "FROM EXME_CitaMedica, EXME_Paciente, EXME_Medico, AD_User "
              + "WHERE EXME_CitaMedica.EXME_Paciente_ID = EXME_Paciente.EXME_Paciente_ID "
              + "AND EXME_CitaMedica.EXME_Medico_ID = EXME_Medico.EXME_Medico_ID "
              + "AND TO_CHAR(EXME_CitaMedica.FechaHrCita, 'DD/MM/YYYY') = ? "
              + "AND EXME_Medico.EXME_Medico_ID = ? "
              + "AND EXME_CitaMedica.IsActive = 'Y' AND EXME_Paciente.IsActive = 'Y' "
              + "AND EXME_Medico.IsActive = 'Y' "
              + "AND EXME_CitaMedica.Estatus <> '" + ESTATUS_Cancelled + "' "
              + "AND AD_USER.AD_USER_ID = EXME_CITAMEDICA.CREATEDBY "
              + "ORDER BY hora, minutos";

        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            pstmt = DB.prepareStatement(sql, null);
            pstmt.setString(1, Constantes.getSdfFecha().format(fecha));
            pstmt.setLong(2, medico);
            rs = pstmt.executeQuery();
        } catch (Exception e) {
			s_log.log(Level.SEVERE, "getCitasMedico", e);
		}
		return rs;
    }*/


    /**
     *  Obtenemos las horas de consulta del medico
     *  @return Una lista con las horas de consulta
     *
    @Deprecated
    public static List<String> getHorasConsultaTS(Properties ctx, long medico, String fecha) {
        List<String> lstHorasConsulta = null;

        try {
            //dia de la semana
            Calendar cal = Calendar.getInstance();
            Date strFecha = Constantes.getSdfFecha().parse(fecha);  
            cal.setTime(strFecha);

            int dia = cal.get(Calendar.DAY_OF_WEEK);
            s_log.log(Level.INFO,"dia de la semana " + dia);

            HashMap<String, String>  rs = MEXMEMedico.getHorario(ctx, medico);
            if (rs.size() > 0) {
                int intervalo = Integer.parseInt(rs.get("IntervaloConsulta"));

                //horario de fin de semana
                String hora1a = rs.get("HoraEnt1Fs");
                String hora2a = rs.get("HoraSal1Fs");

                //primer horario entre semana
                String hora1b = rs.get("HoraEnt1Es");
                String hora2b = rs.get("HoraSal1Es");

                //segundo horario entre semana
                String hora1c = rs.get("HoraEnt2Es");
                String hora2c = rs.get("HoraSal2Es");

                s_log.log(Level.SEVERE,"1 " + hora1a+" 2 "+ hora2a+" 3 "+hora1b +" 4 "+hora2b +" 5 "+ hora1c+" 6 "+hora2c);
              
                if(intervalo==0)
                    intervalo = 30;

                lstHorasConsulta = new ArrayList<String>();

                //si es finde semana y tiene horario de finde semana
                if ((dia==Calendar.SUNDAY || dia==Calendar.SATURDAY) && hora1a !=null && hora2a != null){
                    lstHorasConsulta = getHorasConsul (hora1a, hora2a, intervalo);
                }else{
                        //tiene el 1er horario
                        if (hora1b !=null && hora2b != null)
                            lstHorasConsulta = getHorasConsul (hora1b, hora2b, intervalo);

                        //tiene el 2do horario
                        if (hora1c != null && hora2c != null){
                            //si tiene tambien el 1er horario y el segundo
                                if( lstHorasConsulta.size()!=0){
                                    List<String> lista = getHorasConsul (hora1c, hora2c, intervalo);
                                    for(int i=0; i<lista.size(); i++){
                                        lstHorasConsulta.add(lista.get(i).toString());}
                                }else{
                                    //si solo tiene el 2do horario
                                    lstHorasConsulta = getHorasConsul (hora1c, hora2c, intervalo);
                                }
                        }
                }//fin else sin hoarario de fin
            }
           
        } catch (Exception e) {
			s_log.log(Level.SEVERE, "getHorasConsulta", e);
		}

        return lstHorasConsulta;

    }*/
    
	private static StringBuilder isOverlapSQL(Properties ctx, int patientId, int physicianId) {
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT count(*) FROM EXME_CitaMedica ");
		sql.append(" WHERE isActive='Y' ");
		sql.append(" AND EXME_CitaMedica_ID<>? ");
		if (physicianId > 0) {
			sql.append(" AND EXME_Medico_ID=? ");
		}
		if (patientId > 0) {
			sql.append(" AND EXME_Paciente_ID=? ");
		}
		sql.append(" AND Estatus NOT IN (?) ");
		sql.append(" AND ? BETWEEN COALESCE(FechaHrIni,FechaHrCita) ");
		sql.append("       AND COALESCE(FechaHrFin,FechaHrCita + ((duracion-1) * interval '1 minute')) ");
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", MEXMECitaMedica.Table_Name));
		return sql;
	}
    
    /**
     * 
     * @param ctx
     * @param patientId
     * @param physicianId
     * @param fecha
     * @return
     */
	public static String isOverlap(Properties ctx, int citaId, int patientId, int physicianId, Timestamp fecha) {
		int count;
		if (patientId > 0) {
			// paciente + medico
			if (physicianId > 0) {
				count = DB.getSQLValue(null, isOverlapSQL(ctx, patientId, physicianId).toString(), //
					citaId, physicianId, patientId, ESTATUS_Cancelled, fecha);
				if (count > 0) {
					return Utilerias.getMsg(ctx, "error.citasdetalle.yaTieneCita");
				}
			}
			// paciente
			count = DB.getSQLValue(null, isOverlapSQL(ctx, patientId, 0).toString(), //
				citaId, patientId, ESTATUS_Cancelled, fecha);
			if (count > 0) {
				return Utilerias.getMsg(ctx, "error.citasdetalle.tieneOtraCita");
			}
		}
		// evento repetido
		count = DB.getSQLValue(null, isOverlapSQL(ctx, 0, physicianId).toString(),//
			citaId, physicianId, ESTATUS_Cancelled, fecha);
		if (count > 0) {
			return Utilerias.getMsg(ctx, "error.citasDetalle.otraCita");
		}
		return "";
	}
    
    /**
     * 
     * @param ctx
     * @param physicianId
     * @param fecha
     * @return
     */
    public static String isBlocked(Properties ctx, int physicianId, Timestamp fecha) {
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT * FROM exme_blocktime ");
		sql.append(" WHERE isActive='Y' ");
		sql.append(" AND AD_Table_ID=? AND record_ID=? ");
		sql.append(" AND ? BETWEEN FechaHrIni ");
		sql.append("       AND FechaHrFin ");
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", MEXMEBlockTime.Table_Name));
		int count = DB.getSQLValue(null, sql.toString(), MEXMEMedico.Table_ID, physicianId, fecha);
		if (count > 0) {
			return Utilerias.getAppMsg(ctx, "error.citas.fechaDisponible", Utilerias.getMsg(ctx, "msj.blockedTime"));//FIXME
		}
		return "";
	}
    
    /**
     * 
     * @param medico
     * @param fecha
     * @param trxName
     * @param whereClause (LRHU)
     * @param onlyDate (LRHU)
     * @return
     * @deprecated
     */
    public static boolean IsOcupado( long medico, String fecha, String whereClause, boolean ocupado, boolean onlyDate, boolean otraCita, String trxName) {
    	
    	boolean retValue = false;

        //buscamos el medico y paciente en citas
        StringBuffer sql = new StringBuffer(" SELECT * FROM EXME_CitaMedica WHERE ");
        
        //Inicio:LRHU
        if (onlyDate){
        	sql.append(" to_char(NVL(FechaHrIni,FechaHrCita), 'DD/MM/YYYY') = '" + fecha + "' ");
        }
        else {
            sql.append(" NVL(FechaHrIni,FechaHrCita) = to_date ('" + fecha + "','DD/MM/YYYY HH24:MI:SS')  ");
        }
        
        if (whereClause != null && !ocupado)
        	sql.append(whereClause);
        
        if(!otraCita){
        	sql.append("  AND EXME_Medico_ID = " + medico); //RMontemayor.- Junio 28, 2007
        }
        
       	sql.append(" AND Estatus <> " + ESTATUS_Cancelled);
        
        //Fin:LRHU
        
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            pstmt = DB.prepareStatement(sql.toString(), trxName);
            rs = pstmt.executeQuery();
            
            if (rs.next()){
            	retValue = true;
         
            }else {
            	retValue = false;
            	
            }
            
        } catch(Exception e) {
            s_log.log(Level.SEVERE, "isOcupado", e);
        }  finally {
        	DB.close(rs);
		}
        return retValue;
    }
    
    /**LRHU. CM 0005 
     * Obtiene las citas de un medico en un dia especifico para verificar si se puede reagendar una cita medica
     * @param medicoID
     * @param citaID
     * @param fechaSel -> fecha en la que se va a reagendar la cita dd/mm/yyyy
     * @param fechaIniAppt -> fecha inicial de la cita dd/mm/yyyy hh:mm
     * @param fechaFinAppt -> fecha final de la cita dd/mm/yyyy hh:mm
     * @param trxName
     * @return
     * @deprecated - Usada en AgendaMedicaAction
     *
	public static List<MEXMECitaMedica> getLstCitasAgendadas(Properties ctx, long medicoID, int citaID, String fechaSel, 
	/*Date fechaIniAppt, Date fechaFinAppt,* String trxName) {
    	
    	//boolean ocupado = false;
    	List<MEXMECitaMedica> lista = new ArrayList<MEXMECitaMedica>();
    	
        //buscamos el medico y paciente en citas
        StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
        
        sql.append(" SELECT EXME_CitaMedica.*,  ")
        //fecha inicial
        	.append("NVL(EXME_CitaMedica.fechahrini, EXME_CitaMedica.fechahrcita) as fechaInicial, ")
        //fecha final
        	.append("CASE WHEN fechahrfin IS NULL ")
			.append("THEN (NVL(EXME_CitaMedica.fechahrini, EXME_CitaMedica.fechahrcita)) + (EXME_CitaMedica.duracion/1440) ")
			.append("ELSE fechahrfin END  as fechaFinal ")

			.append(" FROM EXME_CitaMedica ")
	        .append(" WHERE to_char(NVL(EXME_CitaMedica.fechahrini, EXME_CitaMedica.fechahrcita), 'DD/MM/YYYY') = '" + fechaSel + "' ")
	        .append("  AND EXME_Medico_ID = " + medicoID)
	        .append(" AND EXME_CitaMedica_ID <> " + citaID)
	        .append(" AND Estatus <> " + ESTATUS_Cancelled);
        
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            pstmt = DB.prepareStatement(sql.toString(), trxName);
            rs = pstmt.executeQuery();
            
            while(rs.next()){
            	MEXMECitaMedica cita = new MEXMECitaMedica(ctx, rs, trxName);
            	cita.setFechaFinal(rs.getTimestamp("fechaFinal"));
				cita.setFechaInicial(rs.getTimestamp("fechaInicial"));
				lista.add(cita);            	
            }             
		} catch (Exception e) {
			s_log.log(Level.SEVERE, "getCitasMedico", e);
		} finally {
			DB.close(rs);
		}
		return lista;
	}*/

    /**
     * Cambia el estatus de la cita, indicando que se encuentra en ejecuci�n
     * @param id
     * @throws Exception
     */
    public static void block(Properties ctx, int id) throws Exception{
    	//validamos uqe no este bloqueda ya
    	/*StringBuilder  sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
    	sql.append(" SELECT EXME_CitaMedica_ID FROM EXME_CitaMedica WHERE processing = 'Y' AND EXME_CitaMedica_ID = ? ");
    	int no = DB.executeUpdate(sql.toString(),id,null);
    	if(no>=1)
    		throw new Exception("error.cita.bloqueda");
    	sql = new StringBuilder ("UPDATE EXME_CitaMedica SET processing = 'Y' WHERE EXME_CitaMedica_ID = ? ");
    	DB.executeUpdate(sql.toString(),id,null);*/
    	
    	//Liz de la Garza - Cambio del proceso para utilizarlo dentro del modelo de persistencia (para log de cambios)
    	MEXMECitaMedica cita = new MEXMECitaMedica(ctx, id, null);
    	if(cita.getEXME_CitaMedica_ID() > 0){
	    	if (cita.isProcessing() == true) {
	    		throw new Exception(Utilerias.getMsg(ctx, "error.cita.bloqueda"));
	    	}
	    	cita.setProcessing(true);
	    	if(cita.getHoraLlegada() == null){
	    		cita.setHoraLlegada(DB.getTSForOrg(ctx));
	    	}
	    	if (!cita.save())
	    		s_log.log(Level.SEVERE, "diarioEnf.error.noSave");
    	}
        //log.info("Reset=" + no);
    }
    
    /**
     * Cambia el estatus de la cita, indicando que no se encuentra en ejecuci�n
     * @param id
     */
    public static void release(Properties ctx, int id){
    	/*StringBuilder  sql = new StringBuilder (" UPDATE EXME_CitaMedica SET processing = 'N' WHERE EXME_CitaMedica_ID = ? ");
    	DB.executeUpdate(sql.toString(),id,null);*/
    	
    	//Liz de la Garza - Cambio del proceso para utilizarlo dentro del modelo de persistencia (para log de cambios)
    	MEXMECitaMedica cita = new MEXMECitaMedica(ctx, id, null);
    	if (cita.getEXME_CitaMedica_ID() > 0) {//el objeto nunca es nulo, se valida que el id sea mayor a cero .- LAMA
    		cita.setProcessing(false);    		
        	if (!cita.save())
        		s_log.log(Level.SEVERE, "release()");
    	}    	
        //log.info("Reset=" + no);
    }
    
    /**
     * Cambia el estatus de la cita, indicando que no se encuentra en ejecuci�n
     * @param id
     */
    public static void releaseDoctor(Properties ctx, int id){    	
    	MEXMECitaMedica cita = new MEXMECitaMedica(ctx, id, null);
    	if (cita.getEXME_CitaMedica_ID() > 0) {
    		if(cita.getSubstitute_ID() == 0 && cita.getEXME_Medico_ID() != cita.getEXME_MEDICO_ORIG()){
    			cita.setEXME_Medico_ID(cita.getEXME_MEDICO_ORIG());
    			if (!cita.save())
            		s_log.log(Level.SEVERE, "releaseDoctor()");
    		}    		        	
    	}    	
        //log.info("Reset=" + no);
    }  
    
    
    @Override
    protected boolean beforeSave(boolean newRecord) {
    	// Validacion estatus  - Lama
		if (getEstatus() == null) {
			setEstatus(MEXMECitaMedica.ESTATUS_ToBeConfirmed);//obligatorio
		} else {
			// si estatus es pendiente confirmar, confirmada debe ser falso
			if (isConfirmada() && getEstatus().equalsIgnoreCase(ESTATUS_ToBeConfirmed))
				setConfirmada(false);
		}
		
		if(getEXME_Level_Of_Service_ID() > 0){
			setPatient_Type(getEXME_Level_Of_Service().getPatient_Type());
		}
		Integer finishfor = getTerminadoPor();
		if(finishfor == 0){
			finishfor = null;
		}
		if(!is_new() && finishfor == null  && is_ValueChanged(COLUMNNAME_Estatus) && ESTATUS_Executed.equals(getEstatus())){
			
			setTerminadoPor(Env.getAD_User_ID(getCtx()));
		}
 		return validaCM();
    }
	/**
	 * Valida si una cita medica se empalma con un blockTime
	 * 
	 * @return true si la cita medica es valida
	 */
	private boolean validaCM() {
		Date fechaFin = new Timestamp(getFechaHrCita().getTime() + (getDuracion() * 60000));
		Interval interval = new Interval(new DateTime(getFechaHrCita()), new DateTime(fechaFin));

		List<MEXMEBlockTime> lst = MEXMEBlockTime.getBlocksTime(getCtx(), MEXMEMedico.Table_ID, getEXME_Medico_ID(), getFechaHrCita(), fechaFin);
		boolean valid = true;
		if (lst != null && !lst.isEmpty()) {
			for (MEXMEBlockTime bT : lst) {
				Interval interval2 = new Interval(new DateTime(bT.getFechaHrIni()), new DateTime(bT.getFechaHrFin()));

				if (interval.overlaps(interval2)) {
					StringBuilder sb = new StringBuilder();
					sb.append(Utilerias.getAppMsg(getCtx(), "error.citasDetalle.duracion", getMedico().getNombre_Med()));
					sb.append("\n");
					sb.append(Utilerias.getMsg(getCtx(), "progMed.ProgDate"));
					sb.append(Constantes.SPACE);
					sb.append(Constantes.getSdfFechaMH(getCtx()).format(bT.getFechaHrIni()));
					s_log.saveError("Error", sb.toString());
					valid = false;
				}
			}
		}
		return valid;
	}
    
   /**
    * @Override
    * @param newRecord
    * @param success
    */
    protected boolean afterSave(boolean newRecord, boolean success) {
    	if (!success){
			return success;
		}
    	// se guarda en la tabla de trabajo, para notificar el cambio al medico/paciente - Lama
    	if(getFechaHrCita().after(DB.getTSForOrg(Env.getCtx()))){
    		if (newRecord){
    			//actualizamos/insertamos en la tabla temporal los datos anteriores
    			MEXMETCitaMedica cita = new MEXMETCitaMedica(getCtx(),0,null);
				cita.setAD_Session_ID(Env.getAD_Session_ID(getCtx()));
				cita.setEXME_CitaMedica_ID(getEXME_CitaMedica_ID());
				cita.setEXME_Medico_ID(getEXME_Medico_ID());
				cita.setFechaHrCita(getFechaHrCita());
				cita.setEstatus(getEstatus());
				cita.setTipoOperacion("N");
				cita.setIsInfoSent(false);
				cita.save();
    		} else {
    			//Si la cita se modifica: medico, estatus o fecha inicio (mayor a 30minutos)
    			char tipo = 'X';
    			// S: Estatus: Confirmacion, Cancelacion
    			tipo = !((String) get_ValueOld("Estatus")).equals(getEstatus())
    					&& !getEstatus().equals("6") ? 'S' : tipo;
    			// D: Fecha difiere por mas de 30 minutos
    			tipo = Math.abs(getFechaHrCita().getTime() - ((Timestamp) get_ValueOld("FechaHrCita"))
    					.getTime()) > (30 * 60 * 1000) ? 'D' : tipo;
    			// M: Cambio de m�dico responsable
    			tipo = (Integer) get_ValueOld("EXME_Medico_ID") != getEXME_Medico_ID() ? 'M' : tipo;
    			
    			if (tipo != 'X' || is_ValueChanged("IsInfoSent")) {
    				//recuperamos el registro si existe
    				StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
    				sql.append("SELECT EXME_T_CITAMEDICA_ID FROM EXME_T_CITAMEDICA WHERE EXME_CITAMEDICA_ID = ? ");
    				s_log.log(Level.FINE, sql.toString());
    				int count = DB.getSQLValue(null, sql.toString(), getEXME_CitaMedica_ID());

    				// actualizamos/insertamos en la tabla temporal los datos anteriores
    				MEXMETCitaMedica cita = new MEXMETCitaMedica(getCtx(), count < 0 ? 0 : count,null);
    				if (is_ValueChanged("IsInfoSent") && isInfoSent()) {
    					if(cita.getEXME_T_CitaMedica_ID() > 0)
    						cita.delete(false);
    				} else {
    					cita.setAD_Session_ID(Env.getAD_Session_ID(getCtx()));
    					cita.setEXME_CitaMedica_ID(getEXME_CitaMedica_ID());
    					cita.setEXME_Medico_ID((Integer) get_ValueOld("EXME_Medico_ID"));
    					cita.setFechaHrCita((Timestamp) get_ValueOld("FechaHrCita"));
    					cita.setEstatus((String) get_ValueOld("Estatus"));
    					cita.setTipoOperacion(String.valueOf(tipo));
    					cita.setIsInfoSent(false);
    					cita.save();
    				}
    			}
    		}
    	}
    	return true;
    }

    
    /**
     * @Override
     * @param newRecord
     * @param success
     */
     protected boolean afterDelete(boolean newRecord) {
      // al eliminar el registro actualizamos en la tabla temporal el cambio de estatus
      //recuperamos el registro si existe
    	 
    	 StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
    	 sql.append("SELECT EXME_T_CITAMEDICA_ID FROM EXME_T_CITAMEDICA WHERE EXME_CITAMEDICA_ID = ? ");
    	 s_log.log(Level.FINE, sql.toString());
    	 int count = DB.getSQLValue(null, sql.toString(), getEXME_CitaMedica_ID());
    	 if(count > 0 ){
    		 MEXMETCitaMedica cita = new MEXMETCitaMedica(getCtx(), count < 0 ? 0 : count,null);
    		 	
    		 	if(this.getEstatus().equalsIgnoreCase(MEXMECitaMedica.ESTATUS_Cancelled)){
	    		 	if(cita.getEXME_T_CitaMedica_ID() > 0){
	    		 		cita.setAD_Session_ID(Env.getAD_Session_ID(getCtx()));
	    		 		cita.setEstatus(MEXMECitaMedica.ESTATUS_Cancelled);
	    		 		cita.setTipoOperacion("S");
	    		 		cita.setIsInfoSent(false);
	    		 		cita.save();
	    		 	}
    		 	}else{
    		 		
    		 		cita.delete(true);
    		 	}
    	 }
      
      return true;
     }
    
    
    /**
     * Limpia la tabla de trabajo de cambios en las citas, para una determinada sesion
     * @param sessionID
     *
    public static void cleanTmp(Properties ctx, int sessionID, String trxName) {
    	StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);

    	//Liz de la Garza - Cambio del proceso para utilizarlo dentro del modelo de persistencia (para log de cambios)
    	sql.append("SELECT EXME_T_CITAMEDICA_ID FROM EXME_T_CITAMEDICA WHERE AD_SESSION_ID = ? ");

    	PreparedStatement pstmt = null;
    	ResultSet rs = null;
    	try {    		
    		pstmt = DB.prepareStatement(sql.toString(), trxName);
    		pstmt.setInt(1, sessionID);
    		rs = pstmt.executeQuery();

    		while (rs.next()) {
    			MEXMETCitaMedica obj = new MEXMETCitaMedica(ctx, rs, null);
    			if (!obj.delete(true, trxName))
    				s_log.log(Level.SEVERE, "error.insulinas.registro.eliminar");
    			obj = null;    			
    		}

    	} catch (Exception e) {
    		s_log.log(Level.SEVERE, sql.toString(), e);
    		
    	} finally {
    		DB.close(rs);
    	}

    	/*StringBuilder sql= new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append("DELETE FROM EXME_T_CITAMEDICA WHERE AD_SESSION_ID = ? ");
		DB.executeUpdate(sql.toString(),sessionID,null);*
	}
    */
   
    /**
     * Obtiene los cambios realizados en las citas durante una sesion
     * @param ctx
     * @param i
     * @param trxName
     * @return
     *
    public static List<AgendaNotificacionView> getTmp(Properties ctx, String whereClause, String trxName){
		List<AgendaNotificacionView> retValue = new ArrayList<AgendaNotificacionView>();
		
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		try {
			sql.append("SELECT * FROM EXME_T_CITAMEDICA WHERE AD_SESSION_ID = ? ");
			if(whereClause!=null)
				sql.append(whereClause);
			
			psmt = DB.prepareStatement(sql.toString(), trxName);
			psmt.setInt(1, Env.getAD_Session_ID(ctx));
			rs = psmt.executeQuery();
			
			while (rs.next()) {
				AgendaNotificacionView notifica = new AgendaNotificacionView();
				MEXMECitaMedica cita = new MEXMECitaMedica(ctx, rs.getInt("EXME_CitaMedica_ID"), trxName);
				notifica.setEXME_CitaMedica_ID(cita.getEXME_CitaMedica_ID());
				notifica.setCitaMedica(cita);
				notifica.setCitaMedicaTmp(new MEXMETCitaMedica(ctx, rs, trxName));
				notifica.setEXME_T_CitaMedica_ID(rs.getInt("EXME_T_CitaMedica_ID"));// IDTemp de la cita
				notifica.setTipoOperacion(rs.getString("TipoOperacion"));
				notifica.setCitaName(cita.getName());//Nombre de la cita
				// Descripcion del cambio
				if(rs.getString("TipoOperacion").equals("S"))//estatus
					notifica.setDescription(cita.getEstatus());
				else if(rs.getString("TipoOperacion").equals("D"))//fecha
					notifica.setDescription(Constantes.getSdfFechaHoraAmPm().format(cita.getFechaHrCita()));
				else if(rs.getString("TipoOperacion").equals("M"))//medico
					notifica.setDescription(cita.getMedico().getNombre_Med());
				retValue.add(notifica);				
			}
			
		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs);
		}
		return retValue;
	}*/
    
    /**
     * Para intercalar, define cual es la fecha inicial/final real de la cita
     */
    private Timestamp fechaFinal = null;
    
    /**
     * Para intercalar, define cual es la fecha inicial/final real de la cita
     */
    private Timestamp fechaInicial = null;

    /**
     * Para intercalar, define cual es la fecha inicial/final real de la cita
     * @return
     */
	public Timestamp getFechaFinal() {
		return fechaFinal;
	}

	/**
	 * Para intercalar, define cual es la fecha inicial/final real de la cita
	 * @param fechaFinal
	 */
	public void setFechaFinal(Timestamp fechaFinal) {
		this.fechaFinal = fechaFinal;
	}

	/**
	 * Para intercalar, define cual es la fecha inicial/final real de la cita
	 * @return
	 */
	public Timestamp getFechaInicial() {
		return fechaInicial;
	}

	/**
	 * Para intercalar, define cual es la fecha inicial/final real de la cita
	 * @param fechaInicial
	 */
	public void setFechaInicial(Timestamp fechaInicial) {
		this.fechaInicial = fechaInicial;
	}
	
	
	/**
	 * Obtiene la citas iniciales de promocion de un medico
	 * @author Lama
	 * @param ctx
	 * @param medicoID		Medico general de promocion
	 * @param emptyRow  	true: regresa el listado con una linea vacia
	 * @param all			true: obtiene todas las citas de promocion del medico
	 * @param trxName
	 * @return
	 *
	public static List<LabelValueBean> getCitasPromo(Properties ctx, int medicoID, boolean emptyRow, boolean all, String trxName) {
		List<LabelValueBean> retValue = new ArrayList<LabelValueBean>();
		
		StringBuilder sql = new StringBuilder();
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		try {
			if(emptyRow)
				retValue.add(new LabelValueBean(" ", "0"));
			
			sql.append("SELECT EXME_CitaMedica_ID, n_promo ")
				.append("FROM EXME_CitaMedica ")
				.append("WHERE n_promo is not null ")
				.append(medicoID > 0 ? "AND exme_medico_ID = ?" : "")
				.append(!all ? " AND ref_citamedica_id is null " : "")
				.append(MEXMELookupInfo.addAccessLevelSQL(ctx," ",Table_Name));
			
			//order
			sql.append(" ORDER BY n_promo DESC ");
			
			psmt = DB.prepareStatement(sql.toString(), trxName);
			if(medicoID > 0)
				psmt.setInt(1, medicoID);
			rs = psmt.executeQuery();
			
			while (rs.next()) {
				retValue.add(new LabelValueBean( rs.getString("n_promo"), rs.getString("EXME_CitaMedica_ID")));
			}
			
		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs);
		}
		
		return retValue;
	}*/
	
	
	/**
	 * Obtiene todas la citas de promocion relacionadas a una cita medica de origen,
	 *  y/o partir de un numero de promocion
	 * @author Lama
	 * @param ctx
	 * @param citaID			Cita de promocion inicial
	 * @param nPromo		Numero de promocion
	 * @param trxName
	 * @return
	 *
	public static List<MEXMECitaMedicaView> getCitasPromo(Properties ctx, int citaID, String nPromo, String trxName) {
		List<MEXMECitaMedicaView> retValue = new ArrayList<MEXMECitaMedicaView>();
		
		StringBuilder sql = new StringBuilder();
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		try {
			sql.append("SELECT EXME_CitaMedica.*, ")
	    		.append("pac.EXME_Paciente_ID as pacID, pac.Value as PacValue, ")
	    		.append("pac.Name||' ' ||pac.Nombre2||pac.Apellido1||' '||pac.Apellido2||' '||pac.Apellido3 as pac_name, ")
	    		.append("EXME_Medico.Apellido1||' '||EXME_Medico.Apellido2||' '||EXME_Medico.Name NombreMedico, ")
	    		.append("EXME_Medico.Value ValueMedico ")
	    		.append(", esp.name as especialidadName ")
	    		
	    		.append("FROM EXME_CitaMedica ")
	    		.append("INNER JOIN EXME_Paciente pac ON (EXME_CitaMedica.EXME_Paciente_ID = pac.EXME_Paciente_ID) ")
	    		.append("INNER JOIN EXME_Medico ON (EXME_CitaMedica.EXME_Medico_ID = EXME_Medico.EXME_Medico_ID ")
	    		.append(" AND EXME_Medico.IsActive = 'Y') ")
	    		.append("INNER JOIN EXME_Especialidad esp ON (EXME_CitaMedica.EXME_Especialidad_ID = esp.EXME_Especialidad_ID )")
				.append("WHERE  EXME_CitaMedica.n_promo is not null ") //
				.append("AND ( EXME_CitaMedica.ref_citamedica_ID = ? OR EXME_CitaMedica.EXME_CitaMedica_ID = ? )")
				//anexar las citas subsecuentes
				.append(nPromo != null ? " AND n_promo = ? " : "")
				.append(MEXMELookupInfo.addAccessLevelSQL(ctx," ",Table_Name))
				.append(" ORDER BY nvl(EXME_CitaMedica.FechaHrIni,EXME_CitaMedica.FechaHrCita)  ");
			
			psmt = DB.prepareStatement(sql.toString(), trxName);

			psmt.setInt(1, citaID);
			psmt.setInt(2, citaID);
			if(nPromo!=null)
				psmt.setString(3, nPromo);
			rs = psmt.executeQuery();
			
			while (rs.next()) {
				MEXMECitaMedicaView cita = new MEXMECitaMedicaView(ctx, rs, trxName);
				cita.setMedicoValue(rs.getString("ValueMedico"));
				cita.setMedicoName(rs.getString("NombreMedico"));
				cita.setHistoria(rs.getString("PacValue"));
				cita.setPacName(rs.getString("pac_name"));
				cita.setEspecialidadName(rs.getString("especialidadName"));
				
				//llenamos los diagnosticos de la ejecucion
				if(cita.getEstatus().equals(ESTATUS_Executed)) {
					cita.setLstDiagnostico(MActPacienteDiag.get(ctx, cita.getEXME_CitaMedica_ID(), trxName));
				} else {
					cita.setLstDiagnostico(new ArrayList<LabelValueBean>());
				}
				
				retValue.add(cita);
			}
			
		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs);
		}
		
		return retValue;
	}*/
	/**@deprecated usada solo para SolAgendaAction*
	public static List<ArchivoView> getCitasMed(Properties ctx, String medicoIni, String medicoFin,
			String fechaIni, String fechaFin, String estServIni, String estServFin, String pacienteIni, String pacienteFin ) throws Exception {
		
		//lista con las motivos de cita
		List<ArchivoView> lstdocs = new ArrayList<ArchivoView>();
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		//buscamos el motivo de cita
		sql.append("SELECT DISTINCT c.fechahrcita ,p.value , p.nombre_pac , ")
				.append("m.nombre_med, a.ubicacion, p.EXME_Paciente_ID, c.exme_especialidad_id, m.ad_user_id  ")
    			.append("FROM exme_citamedica c  ")
    			.append("inner join exme_medico m on(m.exme_medico_id=c.exme_medico_id) ")
    			.append("inner join exme_paciente p on(p.exme_paciente_id=c.exme_paciente_id) ")
    			.append("left join exme_archcli a on (a.exme_paciente_id=p.exme_paciente_id) ")
    			.append("left join exme_estServ estServ on (estServ.exme_estServ_id=c.exme_estServ_id) ")
    			.append("WHERE m.value between ? and ? " )
    			.append("AND p.value between ? and ? " )
    			.append("AND estServ.value between ? and ? " )
    			.append("AND to_date(to_char(c.fechahrcita, 'dd/mm/yyyy') , 'dd/mm/yyyy') between TO_DATE(?, 'dd/MM/yyyy') and TO_DATE(?, 'dd/MM/yyyy')" )
    			.append("AND c.estatus in (").append(DB.TO_STRING(MEXMECitaMedica.ESTATUS_Confirmed)).append(",").append(DB.TO_STRING(MEXMECitaMedica.ESTATUS_ToBeConfirmed)).append(")");

		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setString(1, medicoIni);
			pstmt.setString(2, medicoFin);
			pstmt.setString(3, pacienteIni);
			pstmt.setString(4, pacienteFin);
			pstmt.setString(5, estServIni);
			pstmt.setString(6, estServFin);
			pstmt.setString(7, fechaIni);
			pstmt.setString(8, fechaFin);
			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				ArchivoView obj = new ArchivoView();
				obj.setHoracita(rs.getString(1));
				obj.setHistoria(rs.getString(2));
				obj.setNombrePac(rs.getString(3));
				obj.setNombreMed(rs.getString(4));
				obj.setUbicacion(rs.getString(5));
				obj.setPacienteid(rs.getInt(6));
				obj.setEspecialidad_ID(rs.getInt(7));
				obj.setUser_ID(rs.getInt(8));
				lstdocs.add(obj);
			}
               
		} catch (Exception e) {
    		s_log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs);
			pstmt = null;
			rs = null;
		}

		return lstdocs;
	}*/
	
	/**
	 * Obtener ultima cita del paciente
	 * @param ctx
	 * @param pacienteID
	 * @param especialidadID
	 * @param enEjecucion - unicamente si la cita esta en ejecucion actualmente
	 * @param trxName
	 * @author Lizeth de la Garza.
	 * @return
	 * @deprecated usada solo para ExpedienteAction
	 *
	public static MEXMECitaMedica getUltimaCita(Properties ctx, int pacienteID, int especialidadID, boolean enEjecucion, String trxName) {
		return getUltimaCita(ctx, pacienteID, especialidadID, enEjecucion, true, null, null, trxName);
	}*/
	
	/**
	 * Obtener ultima cita del paciente
	 * @param ctx
	 * @param pacienteID
	 * @param especialidadID
	 * @param enEjecucion
	 * @param estatus - validar que el paciente no tenga una cita
	 * @param trxName
	 * @author Alejandro Garza
	 * @return
	 */	
	public static MEXMECitaMedica getUltimaCita(Properties ctx, int pacienteID, int especialidadID, boolean enEjecucion, boolean estatus, String trxName) {
		return getUltimaCita(ctx, pacienteID, especialidadID, enEjecucion, estatus, true, trxName);
	}
	
	public static MEXMECitaMedica getUltimaCita(Properties ctx, int pacienteID, int especialidadID, boolean enEjecucion, boolean estatus, boolean accessLevel,
			String trxName) {
		StringBuilder where = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		if (estatus) {
			where.append(" AND cita.estatus in ( ").append(DB.TO_STRING(ESTATUS_Confirmed)).append(", ");
			where.append(DB.TO_STRING(ESTATUS_ToBeConfirmed)).append(", ");
			where.append(DB.TO_STRING(ESTATUS_InProcess));
			where.append(") AND ");
			
			if (DB.isOracle()) {
				where.append("trunc(cita.fechahrcita,'HH24') ");
			} else if (DB.isPostgreSQL()) {
				where.append("date_trunc('hour', cita.fechahrcita) ");
			}
			
			where.append(">= ");
			
			if (DB.isOracle()) {
				where.append("trunc(sysdate,'HH24')");
			} else if (DB.isPostgreSQL()) {
				where.append("date_trunc('hour', current_date)");
			}
			
			
		}
		return getUltimaCita(ctx, pacienteID, especialidadID, enEjecucion, accessLevel, estatus ? where.toString() : null, null, trxName);
	}
    /** Obtiene la proxima cita a partir de una fecha*/
	public static MEXMECitaMedica getNextAppt(Properties ctx, int patientID, Timestamp dateFrom) {
		return getUltimaCita(ctx, patientID, 0, false, true, " AND cita.fechahrcita >= ? ", new Object[] { dateFrom == null ? Env.getCurrentDate()
			: dateFrom }, null);
	}
	
	/**
	 * Obtener ultima cita del paciente
	 * @param ctx
	 * @param pacienteID - paciente
	 * @param especialidadID - especialidad de la cita
	 * @param enEjecucion - unicamente si la cita esta en ejecucion actualmente
	 * @param accessLevel - si se agrega o no nivel de acceso
	 * @param whereClause - condiciones adicionales (table_alias: cita)
	 * @param params - parametros adicionales
	 * @param trxName
	 * @return
	 */
	public static MEXMECitaMedica getUltimaCita(Properties ctx, int pacienteID, int especialidadID, boolean enEjecucion, boolean accessLevel,
			String whereClause, Object[] params, String trxName) {

		MEXMECitaMedica citaMedica = null;
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append(" SELECT cita.*  ")//	
				.append(" FROM EXME_CitaMedica cita WHERE cita.EXME_Paciente_ID = ? ");
		sql.append(especialidadID > 0 ? " AND cita.EXME_Especialidad_ID = ? " : "");
		sql.append(enEjecucion ? " AND ( cita.Processing = 'Y' OR cita.fechaHrIni IS NOT NULL ) AND cita.FechaHrFin IS NULL " : "");
		sql.append(accessLevel ? MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name, "cita") : "");
		sql.append(whereClause != null && whereClause.length() > 0 ? whereClause : "");
		
		sql.append(" ORDER BY NVL(cita.fechaHrIni,cita.fechaHrCita)  ");

		PreparedStatement pstmt = null;
		ResultSet rs = null; //

		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			int i = 0;
			pstmt.setInt(++i, pacienteID);
			if(especialidadID > 0)
				pstmt.setInt(++i, especialidadID);
			
			if (params != null && params.length > 0) {
				for (int j = 0; j < params.length; j++) {
					DB.setParameter(pstmt, ++i, params[j]);
				}
			}
			
			rs = pstmt.executeQuery();

			if (rs.next()) {
				citaMedica = new MEXMECitaMedica(ctx, rs, trxName);
			}

		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			sql = null;
			DB.close(rs,pstmt);
		}
		return citaMedica;
	}
	
	/**
	 * expediente prerequisites / treatments 
	 */
	private List<MEXMEPreReqCita> prereq = null;

	/**
	 * MEXMEPreReqCita
	 * @return listado de MEXMEPreReqCita
	 */
	public List<MEXMEPreReqCita> getPrereq() {
		if(prereq == null)
			prereq = MEXMEPreReqCita.get(getCtx(), getEXME_CitaMedica_ID(), null, get_TrxName());
		return prereq;
	}
	
	/**
	 * Listado de tratamientos
	 */
	private List<MEXMETratamientos> treatments = null;
	
	/**
	 * Listado de tratamientos
	 * @return Listado de tratamientos
	 */
	public List<MEXMETratamientos> getTreatments() {
		if (treatments != null)
			return treatments;
		
		treatments = new ArrayList<MEXMETratamientos>();
		
		// Odont: No se asigna Id de trat en las citas con trat. no programados .- Lama
		if (getEXME_Tratamiento_ID() > 0) {
			/*** A - tratamiento directamente relacionado a la cita ***/
			treatments.add(new MEXMETratamientos(getCtx(), getEXME_Tratamiento_ID(), String.valueOf(getCitaNo()), null));
		}

		/*** B - tratamientos iniciados desde la cita (ref_cita = citaID & CitaNo 2) ***/
		ArrayList<MEXMETratamientos> lstChildTrats = MEXMETratamientos.getChildRecords(getCtx(), getEXME_Tratamiento_ID(), getEXME_CitaMedica_ID(), 2,
				getCitaNo(), null, null);
		for (int i = 0; i < lstChildTrats.size(); i++) {
			treatments.add(lstChildTrats.get(i));
		}

		/*** C - Cita Odontologica: tratamiento en piezas dentales ***/
		if (getEXME_Especialidad_ID() == MEXMECitaMedica_MO.getEspecialidadforOdonto(getCtx(), null)) {
			List<MEXMETratamientosPaciente> tratsPaciente = MEXMETratamientosPaciente.obtenerTratamientosPac(getCtx(), null, getEXME_Paciente_ID(),
					getEXME_CitaMedica_ID(), new ArrayList<String>(), String.valueOf(Calendar.getInstance().get(Calendar.MONTH) + 1), String.valueOf(Calendar
							.getInstance().get(Calendar.YEAR)), getCitaNo(), "Y");

			for (int i = 0; i < tratsPaciente.size(); i++) {
				MEXMETratamientos trat = tratsPaciente.get(i).getTratamiento(String.valueOf(getCitaNo()));
				treatments.add(trat);

			}
		}
		
		if(treatments.isEmpty())
			treatments = null;
		
		return treatments;
	}
	
	/**
	 * Obtener citas pendientes (para recordatorios)
	 * @author Lizeth de la Garza	
	 * @param ctx
	 * @param pacienteID
	 * @param trxName
	 * @return
	 */
	public static List<ValueNamePair> getCitasPacPending(Properties ctx, int pacienteID, String trxName) {

		List<ValueNamePair> list = new ArrayList<ValueNamePair>();
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append(" SELECT cita.EXME_CitaMedica_ID, cita.FechaHrCita , esp.name as esp  ");
		sql.append(" FROM EXME_CitaMedica cita  ");
		sql.append(" left join EXME_Especialidad esp ON (cita.EXME_Especialidad_Id=esp.EXME_Especialidad_ID)  ");
		sql.append(" WHERE cita.EXME_Paciente_ID = ? ");
		sql.append(" AND cita.Estatus IN (?,?) ");

		if (DB.isOracle()) {
			sql.append("AND trunc(cita.fechaHrCita,'DD') >= ?");
		} else if (DB.isPostgreSQL()) {
			sql.append("AND date_trunc('day', cita.fechaHrCita) >= ?");
		}
		sql.append(" ORDER BY cita.fechaHrCita DESC ");

		PreparedStatement pstmt = null;
		ResultSet rs = null; //

		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, pacienteID);
			pstmt.setString(2, ESTATUS_Confirmed);
			pstmt.setString(3, ESTATUS_ToBeConfirmed);
			pstmt.setTimestamp(4, TimeUtil.getDay(Env.getCurrentDate()));
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				
				StringBuilder name = new StringBuilder();
				name.append(Utilerias.getMsg(ctx, "citas.citaDe"));
				name.append(rs.getString("Esp")).append("-");
				name.append(Constantes.getSDFDateTime(ctx).formatConvert(rs.getTimestamp("FechaHrCita")));

				StringBuilder value = new StringBuilder();
				value.append(MEXMEProgRecordatorio.TIPORECORDATORIO_EXME_CitaMedica);
				value.append("-");
				value.append(rs.getInt("EXME_CitaMedica_ID"));

				list.add(new ValueNamePair(value.toString(), name.toString()));
			}

		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs);
		}
		return list;
	}
	
	/**
	 * 
	 */
	private MEXMEMotivoCita motivocita = null;

	/**
	 * 
	 * @return
	 */
	public MEXMEMotivoCita getMotivocita() {
		if (motivocita == null) {
			motivocita = new MEXMEMotivoCita(getCtx(), getEXME_MotivoCita_ID(), null);
		}
		return motivocita;
	}

	/**
	 * 
	 * @param motivocita
	 */
	public void setMotivocita(MEXMEMotivoCita motivocita) {
		this.motivocita = motivocita;
	}
	
	 /**
     * Calculo de Precio de la cita medica
     * @param ctx
     * @param medicoID
     * @param pacienteID
     * @param tipoArea
     * @param areaID
     * @param especialidadID
     * @param listaAUsar
     * @param bPartnerID
     * @param warehouseID
     * @param configPre
     * @param configEC
     * @param paciente
     * @param medico
     * @param trxName
     * @return
     *
	public MPrecios getPrecioCitaMedicaByIntervention(String tipoArea, 
    		int areaID, int warehouseID, 
    		MConfigPre configPre, MConfigEC configEC, MProduct producto,  
    		String trxName){
    	
    	if(tipoArea==null || tipoArea.length()<=0)
    		return null;
    	if(areaID<=0)
    		return null;
    	if(warehouseID<=0)
    		return null;
    	if(configPre==null)
    		return null;
    	if(configEC==null)
    		return null;
    	
    	
    	MPrecios precio = new MPrecios(); 

    	MEXMEPaciente paciente =  getPaciente();
    	
    	/*
    	 * OBTENEMOS EL PRECIO CONFIGURADO DE LA CITA
    	 */

    	//Producto - M�dico
    /*	PreciosVenta.m_configPre = configPre;
        PreciosVenta.m_Paciente  = paciente;
        PreciosVenta.m_ConfigEC  = configEC; 
      *
        //Los parametros estan estrechamente ligados al servicio de la consulta
        //por que la unidad de medida es del sevicio y no elegida por el usuario
        //y el socio de negocios se obtiene de la relacion (paciente-aseguradora)
        //pero ya lo hace la clase que se esta llamando
        precio = 
        	PreciosVenta.precioProdVta(
        			getCtx(),
        			producto.getM_Product_ID(), 
        			Env.ONE, 
        			producto.getC_UOM_ID(), 
        			PreciosVenta.PVCE, 
        			warehouseID, 
        			warehouseID, 
        			new Timestamp(System.currentTimeMillis()), 
        			trxName
        	);
     // Expert. Precios en cero
    	//Obtenemos el convenio
    	if(precio.getPriceList() != null && precio.getPriceList().compareTo(Env.ZERO)>0){    	

    		//Calculos de convenios
    		precio = MEsquemaDescuento.descuentos(getCtx(),
    				precio, areaID,
    				tipoArea,
    				producto.getC_UOM_ID(), 
    				new Timestamp(System.currentTimeMillis()), 
    				trxName);

    		if(precio!= null 
    				&& (precio.getPriceStd()== null || precio.getPriceStd().compareTo(Env.ZERO)<=0)){
    			precio.setPriceStd(precio.getPriceList());
    			precio.setPriceLimit(precio.getPriceStd());
    		}
    	}
    	
    	//Impuestos
    	precio.setC_TaxCategory_ID(producto.getC_TaxCategory_ID());
        
    	return precio;
    }
*/
	/**
	 * cita medica por sesion
	 * 
	 * @param ctx
	 *            conexto
	 * @param EXME_Tratamientos_Sesion_ID
	 *            id de la relacion de las tablas tratamiento-paciente-detalle
	 *            del tratamiento
	 * @param trxName
	 *            nombre de transaccion
	 * @return Listado de objetos MEXMECitaMedica
	 */
	public static List<MEXMECitaMedica> getTratamientosDetalle(Properties ctx,
			int EXME_Tratamientos_Detalle_ID, int EXME_TratamientosPaciente_ID, 
			String trxName) {

		List<Object> params = new ArrayList<Object>();
		params.add(EXME_Tratamientos_Detalle_ID);
		params.add(EXME_TratamientosPaciente_ID);
		
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append(" SELECT cm.* ")  
		.append(" FROM EXME_CitaMedica cm  ")
		.append(" 	INNER JOIN EXME_Tratamientos_Sesion ts ")  
		.append(" 		ON cm.EXME_Tratamientos_Sesion_ID = ts.EXME_Tratamientos_Sesion_ID ")  
		.append(" WHERE cm.IsActive = 'Y' ")
		.append(" 	AND ts.EXME_Tratamientos_Detalle_ID =  ? ")
		.append(" 	AND ts.EXME_TratamientosPaciente_ID =  ? ");

		return MEXMECitaMedica.get(ctx, null, sql.toString(), params, trxName);
	}

	/**
	 * Citas del tratamiento
	 * @param ctx
	 * @param EXME_Tratamientos_Sesion_ID
	 * @param trxName
	 * @return
	 */
	public static List<MEXMECitaMedica> getTratamientosDetalle(
			Properties ctx, int EXME_Tratamientos_Sesion_ID, String trxName) {

		List<Object> params = new ArrayList<Object>();
		params.add(EXME_Tratamientos_Sesion_ID);
		
		String sql = " 	AND EXME_Tratamientos_Sesion_ID =  ? ";

		return MEXMECitaMedica.get(ctx, null, sql.toString(), params, trxName);
	}
	
	/**
	 * Cita Medica
	 * 
	 * @param ctx
	 * @param consulta
	 * @param params
	 * @param trxName
	 * @return
	 */
	public static List<MEXMECitaMedica> get(Properties ctx,
			String consulta, String where, List<Object> params, String trxName) {

		List<MEXMECitaMedica> list = new ArrayList<MEXMECitaMedica>();
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		
		if(consulta==null || !consulta.contains("SELECT"))
			sql.append(" SELECT * ");
		if(consulta==null || !consulta.contains("FROM"))
			sql.append(" FROM  EXME_CitaMedica  ");
		if(where!=null && where.contains("WHERE"))
			sql.append(where);
		else if(consulta==null || !consulta.contains("WHERE")) {
			sql.append(" WHERE IsActive = 'Y' "+ (where!=null?where:""));

			sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ",
					X_EXME_CitaMedica.Table_Name));
		}
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			DB.setParameters(pstmt, params);
			rs = pstmt.executeQuery();

			while (rs.next())
				list.add(new MEXMECitaMedica(ctx, rs, trxName));

		} catch (Exception e) {
			s_log.log(Level.SEVERE, "getEjecucion", e);
		} finally {
			DB.close(rs, pstmt);
		}
		return list;
	}

	@Override
	public IDColumn getIdColumn() {
		if(idColumn==null){
			idColumn = new IDColumn(getEXME_CitaMedica_ID());
		}
		return idColumn;
	}
	
	@Override
	public String[] getColumns() {
		return new String[]{"idColumn", "value", "name", "fechaHrCita", "observaciones"};
	}
	
	private IDColumn idColumn = null; 
	
	/**
	 * Crea la cuenta paciente desde consulta externa
	 * @param ctx Contexto
	 * @param EXME_MotivoCita_ID Motivo de la cita
	 * @param trxName Nombre de la transaccion
	 * @return Un objeto MEXMECtaPac
	 * @throws SQLException En caso de que no se logre guardar el objeto
	 *
	public int createCtaPac(Properties ctx, int EXME_MotivoCita_ID, String trxName) throws SQLException {		
		
		MEXMECtaPac ctaPac = createCtaPac2(ctx, EXME_MotivoCita_ID, trxName);
		if (!ctaPac.save(trxName)) {
			throw new SQLException("error.citas.noInsertEjec");
		}
		return ctaPac.getEXME_CtaPac_ID();
	}*/
	
	/**
	 * Crea la cuenta paciente desde consulta externa
	 * @param ctx Contexto
	 * @param EXME_MotivoCita_ID Motivo de la cita
	 * @param trxName Nombre de la transaccion
	 * @return Un objeto MEXMECtaPac
	 */
	public MEXMECtaPac createCtaPac2(Properties ctx, int EXME_MotivoCita_ID, String trxName)  {		
		
		final MEXMECtaPac ctaPac = new MEXMECtaPac(ctx, 0, trxName);
		ctaPac.setEXME_Paciente_ID(getEXME_Paciente_ID());
		ctaPac.setEXME_Medico_ID(getEXME_Medico_ID());
		ctaPac.setEXME_Especialidad_ID(getEXME_Especialidad_ID());
		ctaPac.setM_PriceList_ID(MEXMEConfigPre.getPriceList(ctx, null).getM_PriceList_ID());
		ctaPac.setTipoArea(MEXMECtaPac.TIPOAREA_Ambulatory);
		if (getHoraLlegada() != null) {
			ctaPac.setDateOrdered(getHoraLlegada());
		}
		// Motivo de cita, se puede tomar de la propia cita
		if (getEXME_MotivoCita_ID() != 0 ) {
			ctaPac.setEXME_MotivoCita_ID(getEXME_MotivoCita_ID());
		} else {
			ctaPac.setEXME_MotivoCita_ID(EXME_MotivoCita_ID);
		}
		// Card #1626 - Lama
		if (getEXME_TipoPaciente_ID() > 0) {
			ctaPac.setEXME_TipoPaciente_ID(getEXME_TipoPaciente_ID());
		} else {
			// Tipo de paciente para tipo de area ambulatorio
			MEXMETipoPaciente patientType = MEXMETipoPaciente.getPatientTypeByAreaType(ctx, MEXMECtaPac.TIPOAREA_Ambulatory);
			if (patientType != null) {
				ctaPac.setEXME_TipoPaciente_ID(patientType.getEXME_TipoPaciente_ID());
			}	
		}
		// Requerimos el area
		MEXMEEstServ estServ = new MEXMEEstServ(ctx, Env.getEXME_EstServ_ID(ctx), null);
		if(estServ!=null){
			ctaPac.setEXME_Area_ID(estServ.getEXME_Area_ID());
		}
		return ctaPac;
	}
	
	public static MEXMECtaPac createCtaPac2(Properties ctx, int getEXME_Paciente_ID, int EXME_MotivoCita_ID, int getEXME_Medico_ID, 
			int getEXME_Especialidad_ID, String trxName) throws Exception  {		
		
		final MEXMECtaPac ctaPac = new MEXMECtaPac(ctx, 0, trxName);
		ctaPac.setEXME_Paciente_ID(getEXME_Paciente_ID);
		ctaPac.setEXME_Medico_ID(getEXME_Medico_ID);
		ctaPac.setEXME_Especialidad_ID(getEXME_Especialidad_ID);
		ctaPac.setM_PriceList_ID(MEXMEConfigPre.getPriceList(ctx, null).getM_PriceList_ID());
		ctaPac.setTipoArea(MEXMECtaPac.TIPOAREA_Ambulatory);
		ctaPac.setEXME_MotivoCita_ID(EXME_MotivoCita_ID);
		//Encuentro ejecutado internamente, ver card 416
		ctaPac.setBillingType(MEXMECtaPac.BILLINGTYPE_Internal);
		ctaPac.setEXME_Institucion_ID(MOrgInfo.get(ctx, Env.getAD_Org_ID(ctx)).getEXME_Institucion_ID());
		
		
		// Tipo de paciente para tipo de area ambulatorio
		MEXMETipoPaciente patientType = MEXMETipoPaciente.getPatientTypeByAreaType(ctx, MEXMECtaPac.TIPOAREA_Ambulatory);
		if (patientType != null) {
			ctaPac.setEXME_TipoPaciente_ID(patientType.getEXME_TipoPaciente_ID());
		}		
		
		// Requerimos el area
		MEXMEEstServ estServ = new MEXMEEstServ(ctx, Env.getEXME_EstServ_ID(ctx), null);
		if(estServ!=null){
			ctaPac.setEXME_Area_ID(estServ.getEXME_Area_ID());
		}
		
		if (!ctaPac.save()) {
			throw new Exception("error.citas.noInsertEjec");
		}
		
		return ctaPac;
	}
	
	/**
	 * Cancelar la cuenta paciente
	 * @param ctx
	 * @param trxName
	 * @throws SQLException
	 */
	public void cancelCtaPac(String trxName) throws SQLException {
		if (getEXME_CtaPac_ID() > 0) {
			final MEXMECtaPac ctaPac = new MEXMECtaPac(getCtx(), getEXME_CtaPac_ID(), trxName);
			ctaPac.setEncounterStatus(MEXMECtaPac.ENCOUNTERSTATUS_Discharge);//Ren. EncounterStatus Estatus (A= Admission, C= Discharge)
			ctaPac.setFechaCierre(new Timestamp(System.currentTimeMillis()));
			if (!ctaPac.save()) {
				throw new SQLException("error.citas.noInsertEjec");
			}
		}
	}
	
	private MEXMEReferPhysician referPhysician;
	private MEXMEReferInpatient referInpatient;

	/**
	 * 
	 * @return
	 * @deprecated Usar {@link MEXMEReferPhysician#getReferPhysicianLst(Properties, int, String)}
	 */
	public MEXMEReferPhysician getReferPhysician() {
		if(referPhysician == null && getEXME_CitaMedica_ID() > 0){
			referPhysician = MEXMEReferPhysician.getReferPhysician(getCtx(), getEXME_CitaMedica_ID(), get_TrxName());
		}
		return referPhysician;
	}

	public void setReferPhysician(MEXMEReferPhysician referPhysician) {
		this.referPhysician = referPhysician;
	}

	public MEXMEReferInpatient getReferInpatient() {
		return referInpatient;
	}

	public void setReferInpatient(MEXMEReferInpatient referInpatient) {
		if(referInpatient == null && getEXME_CitaMedica_ID() > 0){
			referInpatient = MEXMEReferInpatient.getReferInpatient(getCtx(), getEXME_CitaMedica_ID(), get_TrxName());
		}
		this.referInpatient = referInpatient;
	}

	private MEXMECtaPac mCtaPac = null;

	public MEXMECtaPac getMCtaPac() {
		if (mCtaPac == null && getEXME_CtaPac_ID() > 0) {
			mCtaPac = new MEXMECtaPac(getCtx(), getEXME_CtaPac_ID(), get_TrxName());
		}
		return mCtaPac;
	}
	/**
	 * 
	 * @param ctx
	 * @param medico EXME_Medico_ID
	 * @param paciente EXME_Paciente_ID - No requerido
	 * @param dayPlus - Dia de busqueda - Ejemplo antier(-2), ayer(-1) , hoy (0), manana(1), pasado manana(2) y asi sucesivamente
	 * @param trxName - transaccion
	 * @return Lista de Citas medicas
	 * @author Pmendoza
	 */
	public static List<MEXMECitaMedica> getCitasAtDay(Properties ctx, int medico, int paciente, int dayPlus, String trxName) {
		Calendar dateIni = Calendar.getInstance();
		dateIni.setTime(DB.getTSForOrg(ctx));
		dateIni.add(Calendar.DAY_OF_YEAR, dayPlus);
		Calendar dateFin = Calendar.getInstance();
		dateFin.setTime(DB.getDateForOrg(ctx));
		dateFin.add(Calendar.DAY_OF_YEAR, dayPlus + 1); // +1 para indicar que el periodo de tiempo sera de un dia de diferencia
		
		// Hora y minutos a 0 para indicar que es el inicio del dia y poder recorrer todos los horarios
		dateIni.set(Calendar.HOUR_OF_DAY, 0);
		dateIni.set(Calendar.MINUTE, 0);		
		dateFin.set(Calendar.HOUR_OF_DAY, 0);
		dateFin.set(Calendar.MINUTE, 0);;
//		String dateBEGINS = Constantes.getSdfFechaHoraBD24().format(dateIni.getTime());
//		String dateENDS = Constantes.getSdfFechaHoraBD24().format(dateFin.getTime());
		int medicoSustituto = 0;
		return getCitasCalendar(ctx, medico, paciente, medicoSustituto, dateIni.getTime(), dateFin.getTime(), trxName);
	}
	
	/**
	 * Recibe int y lo transforma a arreglo
	 * @param ctx
	 * @param physicianId
	 * @param patientId
	 * @param substituteId
	 * @param dateBEGINS
	 * @param dateENDS
	 * @param trxName
	 * @return
	 */
	public static List<MEXMECitaMedica> getCitasCalendar(Properties ctx, 
		int physicianId,//
		int patientId, //
		int substituteId, //
		Date initDate, //
		Date endDate, //
		String trxName) {
		return getCitasCalendar(ctx, new int[]{physicianId}, patientId, new int[]{substituteId}, initDate, endDate, trxName);
	}
	
	/**
	 * Obtiene la lista de citas de varios medicos y sustitutos, para un paciente determinado y/o un rango de fechas
	 * 
	 * @param ctx Contexto de la aplicacion
	 * @param physicianArray arreglo de exme_medico_id
	 * @param patientId id del paciente (opcional)
	 * @param substituteArray arreglo de substitute_id
	 * @param initDate fecha inicial (opcional)
	 * @param endDate fecha final (opcional)
	 * @param trxName Transaccion
	 * @return Listado de citas<br>
	 *         <b>NOTA:</b>
	 *         <ul>
	 *         <li>Vacia si ambos arreglos (physicianArray/substituteArray) son NULL</li>
	 *         <li>NULL si ocurre una excepcion</li>
	 *         </ul>
	 */
	public static List<MEXMECitaMedica> getCitasCalendar(Properties ctx, //
		int[] physicianArray, //
		int patientId, //
		int[] substituteArray, //
		Date initDate, //
		Date endDate, //
		String trxName) {
		
		final List<MEXMECitaMedica> list = new ArrayList<MEXMECitaMedica>();
		int subsLength = substituteArray == null ? 0 : substituteArray.length;
		int physLength = physicianArray == null ? 0 : physicianArray.length;
		if(subsLength == 0 && physLength == 0){
			return list;
		}
		
		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		final List<Object> params = new ArrayList<>();
		
		if(subsLength > 0 && physLength > 0){
 			sql.append(" SELECT * FROM (( ");
		}
		// Citas del medico
		if (physLength > 0) {
			sql.append(getAppointmentsSQL(ctx, params, physicianArray, patientId, initDate, endDate));
		}
		//
		if(subsLength > 0  && physLength > 0){
			sql.append(") UNION (");
		}
		// citas por sustituto
		if(subsLength > 0){
			sql.append(getAppointmentsSQL(ctx, params, substituteArray, patientId, initDate, endDate));
			sql.append(" AND ((citas.substitute_id IS NULL) OR (citas.substitute_id = 0)) ");
		}
		//
		if(subsLength > 0 && physLength > 0){
			sql.append(") ) citas ");			
		}
		//
		sql.append(" ORDER BY citas.FechaHrCita ");

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			DB.setParameters(pstmt, params);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				list.add(new MEXMECitaMedica(ctx, rs, null));
			}
		} catch (SQLException e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
			return null;
		} finally {
			DB.close(rs, pstmt);
		}
		
		return list;
	}
	
	/**
	 * Arma el sql para obtener las citas de un arreglo de medicos
	 * @param ctx
	 * @param sql
	 * @param params
	 * @param arrayDoctors
	 * @param patientId
	 * @param initDate
	 * @param endDate
	 */
	private static StringBuilder getAppointmentsSQL(Properties ctx,
		List<Object> params, int[] arrayDoctors, 
		int patientId, 
		Date initDate, 
		Date endDate ){
		
		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append("SELECT citas.* ");
		sql.append("FROM EXME_CitaMedica citas ");
		sql.append("WHERE citas.isactive = 'Y' ");
		// rango de fechas
		if (initDate != null && endDate != null) {
			sql.append(" AND citas.FechaHrCita BETWEEN ? AND ? ");
			params.add(initDate);
			params.add(endDate);
		}
		// arreglo de medicos
		sql.append(" AND citas.EXME_Medico_ID IN (");
		for (int i = 0; i < arrayDoctors.length; i++) {
			if (i > 0) {
				sql.append(",");
			}
			sql.append(" ? ");// array[i]
			params.add(arrayDoctors[i]);
		}
		sql.append(")");
		//estatus validos
		sql.append(" AND citas.estatus NOT  IN (?,?) "); // '5','8'
		params.add(ESTATUS_Cancelled);
		params.add(ESTATUS_Closed);
		// paciente
		if (patientId > 0) {
			sql.append(" and citas.exme_paciente_id = ? ");
			params.add(patientId);
		}
		// nivel de acceso
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name, "citas"));
		return sql;
	}
	
	/**
	 * Devolvemos una Cita Medica en base a la factura relacionada.
	 * @author Alejandro Garza
	 * @param ctx
	 * @param C_Invoice_ID
	 * @param trxName
	 * @return cita
	 * @throws SQLException
	 */
	public static MEXMECitaMedica getForInvoice(Properties ctx, int C_Invoice_ID,
			String trxName) throws SQLException {
//		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		MEXMECitaMedica cita = null;
//		try {
//			sql.append(" SELECT EXME_CITAMEDICA.* FROM EXME_CITAMEDICA WHERE C_INVOICE_ID = ")
//			.append(C_Invoice_ID).append(" AND EXME_CITAMEDICA.IsActive = 'Y' ");
//			sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, sql.toString(), MEXMECitaMedica.Table_Name));
//			pstmt = DB.prepareStatement(sql.toString(), trxName);
//			rs = pstmt.executeQuery();
//			if (rs.next()) {
//				cita = new MEXMECitaMedica(ctx, rs, trxName);
//			}
//		} catch (SQLException e) {
//			s_log.log(Level.SEVERE, sql.toString(), e);
//		} finally {
//			DB.close(rs, pstmt);
//		}
		return new Query(ctx, Table_Name, " C_INVOICE_ID =? " , trxName)//
		.setParameters(C_Invoice_ID)//
		.setOnlyActiveRecords(true)//
		.addAccessLevelSQL(true)//
		.first();
	}
	
	/**
	 * Devolvemos una Cita Medica en base a la factura relacionada.
	 * @author Alejandro Garza
	 * @param ctx
	 * @param C_Invoice_ID
	 * @param trxName
	 * @return cita
	 * @throws SQLException
	 */
	public static List<MEXMECitaMedica> getForPatient(Properties ctx, int exmePacienteID, String trxName) throws SQLException {
		
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<MEXMECitaMedica> cita = new ArrayList<MEXMECitaMedica>();
		
		try {

			sql.append(" SELECT EXME_CITAMEDICA.* FROM EXME_CITAMEDICA WHERE EXME_PACIENTE_ID = ? ")
			.append(" AND EXME_CITAMEDICA.IsActive = 'Y' AND ESTATUS = ? ");
			
			if (DB.isOracle()) {
				sql.append(" AND rownum = 1 ");
			}
			
			sql.append(" order by fechahrcita desc ");
			
			if (DB.isPostgreSQL()) {
				sql = new StringBuilder(DB.getDatabase().addPagingSQL(sql.toString(), 1, 1));
			}
			
//			sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, sql.toString(), MEXMECitaMedica.Table_Name));
			
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, exmePacienteID);
			pstmt.setString(2, MEXMECitaMedica.ESTATUS_Executed);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				cita.add(new MEXMECitaMedica(ctx, rs, trxName));
			}
			
		} catch (SQLException e) {
			s_log.log(Level.SEVERE, "MEXMECitaMedica.getForPatient - sql: " + sql, e);
		} finally {
			DB.close(rs, pstmt);
		}
		
		return cita;
	}
	
	/**
	 * Obtiene la ultima cita del paciente
	 * 
	 * @param ctx
	 *            App Ctx
	 * @param pacId
	 *            Paciente
	 * @param trxName
	 * @return Ultima cita o nulo si no ha tenido
	 */
	public static MEXMECitaMedica getLast(Properties ctx, int pacId, String trxName) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ");
		sql.append("  * ");
		sql.append("FROM ");
		sql.append("  exme_citamedica ");
		sql.append("WHERE ");
		sql.append("  exme_paciente_id = ? ");
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", MEXMECitaMedica.Table_Name));
		sql.append("ORDER BY ");
		sql.append("  FECHAHRCITA desc ");
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MEXMECitaMedica cita = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, pacId);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				cita = new MEXMECitaMedica(ctx, rs, trxName);
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, null, e);
		} finally {
			DB.close(rs, pstmt);
		}
		return cita;
	}
	
	
	/**
	 * Confirma una cita medica en la base de datos
	 *
	 * @param       CitaMedica a almacenar
	 * @throws      SQLException si ocurre un error
	 */
	public void confirmar(String trxName) throws SQLException {
		s_log.log(Level.INFO,"******* Confirmando Cita: " + get_ID());
        setConfirmada(true);
        setEstatus(MEXMECitaMedica.ESTATUS_Confirmed);
        setFechaConfirm(new Timestamp(System.currentTimeMillis()));
		if(!save(trxName)) {
            throw new SQLException("error.citas.noConfirmarCita");
        }
	}
	
	/**
	 * Actualiza el estatus de la cita medica a 'Realizado'
	 *
	 * @param  Cita medica a actualizar en el estatus
	 * @throws SQLException si ocurre un error
	 */
	public void cancel(int motivoCancel, String coments, String trxName) throws SQLException {
        setEstatus(MEXMECitaMedica.ESTATUS_Cancelled);
        setFechaCancel(new Timestamp(System.currentTimeMillis()));
        setEXME_MotivoCancel_ID(motivoCancel);
        setMotivoCancel(coments);
        cancelCtaPac(trxName);
        if(!save(trxName)) {
            throw new SQLException("error.citas.noUpdateCita");
        }
	}
	
	/**
	 * @throws SQLException si ocurre un error
	 */
	public void cancelCitaRefer(String trxName) throws SQLException {
		if (getEXME_Paciente_ID() > 0) {
			final List<MEXMERefer> referlst = MEXMERefer.getPacientes(getCtx(),//
				"AND EXME_Refer.EXME_Paciente_ID=" + getEXME_Paciente_ID(),//
				false, // contrarefer
				trxName);
			if (!referlst.isEmpty()) {
				final MEXMERefer refer = referlst.get(0);
				if (refer != null && refer.getEXME_Refer_ID() > 0) {
					// regresamos al estatus inicial
					refer.setDocAction(MEXMERefer.DOCACTION_WaitComplete); // Verificar
					refer.setDocStatus(MEXMERefer.DOCSTATUS_Drafted); // Verificar
					refer.setEstatus(MEXMERefer.ESTATUS_PendingConfirmation); // Verificar
					refer.setFechaRec(null);

					if (!refer.save()) {
						throw new SQLException("error.citas.noUpdateCita");
					}
					getPaciente().setIsRefer(true);
					if (!getPaciente().save(trxName)) {
						throw new SQLException("error.citas.noUpdateCita");
					}
				}
			}
		}
	}
	
	/**
	 * 
	 * @param ctx
	 * @param pacID
	 * @param medID
	 * @param trxName
	 * @return
	 */
	public static List<MEXMECitaMedica> getCitas(Properties ctx, int pacID, int medID, String trxName) {
		StringBuilder sql = new StringBuilder();
		final List<Object> params = new ArrayList<>();

		sql.append("  ESTATUS = ? ");
		params.add(MEXMECitaMedica.ESTATUS_Executed);
		if (pacID > 0) {
			sql.append(" AND exme_paciente_id = ?  ");
			params.add(pacID);
		}
		if (medID > 0) {
			sql.append(" AND exme_medico_id = ?  ");
			params.add(medID);
		}
		
		return new Query(ctx, MEXMECitaMedica.Table_Name, sql.toString(), trxName)//
		.setParameters(params)//
		.setOnlyActiveRecords(true)//
		.addAccessLevelSQL(true)//
		.setOrderBy(" Created desc ")//
		.setLimit(10)//
		.list();
	}
	
	
	public MEXMEPrescRX getPrescRXVisit(String trxName) {
		MEXMEPrescRX prescRX =
			MEXMEPrescRX.getPrescbyCita(getCtx(), getEXME_CitaMedica_ID(), trxName);
		
		if (prescRX.getEXME_PrescRX_ID() == 0) {
			prescRX.setEXME_CitaMedica_ID(getEXME_CitaMedica_ID());
			prescRX.setEXME_CtaPac_ID(getEXME_CtaPac_ID());
			prescRX.setTipo(MEXMEPrescRX.TIPO_OV);
			prescRX.setEXME_Medico_ID(getEXME_Medico_ID());
			if (!prescRX.save(trxName)) {
				throw new MedsysException("error.notasMed.servicios.detalle");
			}
		}else{
			if(prescRX.getEXME_Medico_ID() == 0){
				prescRX.setEXME_Medico_ID(getEXME_Medico_ID());
				if (!prescRX.save(trxName)) {
					throw new MedsysException("error.notasMed.servicios.detalle");
				}	
			}
		}
		return prescRX;
	}

	/**
	 * Indica si la cita fue ejecutada en dentro de las ultimas 72 horas
	 * @return
	 */
	public boolean isLast72hrs() {
		boolean isLast72hrs = true;
		Calendar today = Calendar.getInstance();
		MConfigEC config = MEXMEConfigEC.get(getCtx());
		if(config == null || config.getQty_Hr_Abierta()<=0) {
			today.add(Calendar.DAY_OF_YEAR, -3);
		} else {
			today.add(Calendar.HOUR_OF_DAY, -config.getQty_Hr_Abierta());
		}
		if (getFechaHrFin() != null) {
			isLast72hrs = today.getTime().before(getFechaHrFin());
		}
		return isLast72hrs;
	}

	public String getMsjSMS() {
		StringBuilder msj = new StringBuilder();
		msj.append(Utilerias.getMsg(getCtx(), "msg.histRecord.citaDe"));
		msj.append(StringUtils.replaceChars(getEspecialidad().getName(), " ", "+"));
		msj.append("+");
		msj.append(Utilerias.getMsg(getCtx(), "msg.histRecord.FechaProg"));
		msj.append(":");
		msj.append(StringUtils.replaceChars(Constantes.getSdfFechaHora().format(getFechaHrCita()), " ", "+"));
		return msj.toString();
	}
	
	/**
	 * Listado de citas pendientes de notificar</br> Solo si la cita no esta
	 * notificada (notified = 'N')</br> y su estatus no es confirmado (estatus
	 * <> '7')
	 * 
	 * @param ctx
	 *            Contexto de la app
	 * @param toDate
	 *            Fecha limite
	 * @param trxName
	 *            Nombre de Trx
	 * @return Listado de las citas
	 */
	public static List<AppointmentReminder> getAppointments(Properties ctx, Timestamp toDate, String trxName) {
		List<AppointmentReminder> lst = new ArrayList<AppointmentReminder>();

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ");
		sql.append("  c.FechaHrCita, ");// 1
		sql.append("  c.exme_citamedica_id, ");// 2
		sql.append("  p.exme_paciente_id, ");// 3
		sql.append("  p.nombre_pac, ");// 4
		sql.append("  p.email, ");// 5
		sql.append("  p.TelParticular, ");// 6
		sql.append("  m.nombre_med, ");// 7
		sql.append("  cr.isemail ");// 8
		sql.append("FROM ");
		sql.append("  exme_citamedica c ");
		sql.append("  INNER JOIN exme_paciente p ");
		sql.append("  ON c.exme_paciente_id = p.exme_paciente_id ");
		sql.append("  INNER JOIN exme_medico m ");
		sql.append("  ON c.exme_medico_id = m.exme_medico_id left ");
		sql.append("  JOIN exme_configrecordatorio cr ");
		sql.append("  ON (p.exme_paciente_id = cr.exme_paciente_id ");
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, Constantes.SPACE, MEXMEConfigRecordatorio.Table_Name, "cr"));
		sql.append("  ) ");
		sql.append("WHERE ");
		sql.append("  c.fechahrcita BETWEEN ");
		sql.append("  ? AND ");
		sql.append("  ? AND ");
		sql.append("  c.notified = 'N' AND ");
		sql.append("  c.estatus <> ? ");
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, Constantes.SPACE, Table_Name, "c"));
		sql.append(" ORDER BY c.created asc");

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setTimestamp(1, Env.getCurrentDate());
			pstmt.setTimestamp(2, TimeUtil.getFinalRangeT(ctx, toDate));
			pstmt.setString(3, ESTATUS_Confirmed);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				AppointmentReminder reminder = new AppointmentReminder();
				reminder.setAppointmentDate(rs.getTimestamp(1));
				reminder.setAppointmentId(rs.getInt(2));
				reminder.setPacId(rs.getInt(3));
				reminder.setPacName(SecureEngine.decrypt(rs.getString(4)));
				reminder.setEmail(rs.getString(5));
				reminder.setPhone(rs.getString(6));
				reminder.setNombreMed(rs.getString(7));
				String byEmail = rs.getString(8);

				if ("Y".equals(byEmail)) {
					reminder.setByEmail(true);
				} else {
					reminder.setByEmail(false);
				}

				lst.add(reminder);
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, null, e);
		} finally {
			DB.close(rs, pstmt);
		}

		return lst;
	}

	public String getMsjEmail() {
		StringBuilder msj = new StringBuilder();
		msj.append(Utilerias.getMsg(getCtx(), "citas.citaDe"));
		msj.append(getEspecialidad().getName());
		msj.append(" ");
		msj.append(Utilerias.getMsg(getCtx(), "msj.fechaProgramada"));
		msj.append(":");
		msj.append(Constantes.getSdfFechaHora().format(getFechaHrCita()));
		return msj.toString();
	}
	
	/**
	 * Obtiene las citas que tiene cuentas pacientes pagadas en su totalidad y que tiene solicitudes de medicamentos pendientes de surtir
	 * @return listado de citas
	 */
	public static List<MEXMECitaMedica> getCitaPagadas() {
		List<MEXMECitaMedica> citas = new ArrayList<MEXMECitaMedica>();
		StringBuilder sql = new StringBuilder();
		
		// Seleccionar citas medicas donde la cuenta paciente
		sql.append(" SELECT CM.* FROM EXME_CITAMEDICA CM WHERE CM.EXME_CTAPAC_ID IN ( ")
		
		   .append(" SELECT SBAL.CTAPAC_ID FROM ( ") //Inicio de tabla SBAL
		   .append(" SELECT SUM ( ") //Inicio del calculo de columna Balance (Diferencia de cargos y pagos)
		   .append(" (SELECT COALESCE(SUM(CPD.LineNetAmt),0) ") //Sumatoria de los cargos de la cuenta paciente
		   .append(" FROM EXME_CtaPacDet CPD WHERE CPD.EXME_CtaPac_ID = CP.EXME_CTAPAC_ID  and CPD.IsActive = 'Y' ) ") //filtros de la sumatoria
		   .append(" - (SELECT COALESCE(SUM(A.total),0) ") //Sumatoria de los pagos a la cuenta paciente
		   .append(" FROM EXME_Anticipo A WHERE A.EXME_CtaPac_ID = CP.EXME_CTAPAC_ID  and A.IsActive = 'Y') ") //filtros de la sumatoria de los pagos a la cuenta paciente
		   .append(" ) Balance, CP.EXME_CTAPAC_ID AS CTAPAC_ID ")
		   .append(" FROM EXME_CtaPac CP ")
		   .append(" INNER JOIN EXME_ACTPACIENTE AC ON AC.EXME_CTAPAC_ID = CP.EXME_CTAPAC_ID ") 
		   .append(" INNER JOIN EXME_ACTPACIENTEINDH INDH ")
		   .append(" ON (INDH.EXME_ACTPACIENTE_ID = AC.EXME_ACTPACIENTE_ID AND INDH.ISSERVICE = 'N' AND INDH.ISDELIVERED = 'N') ")// AND INDH.ISDELIVERED = 'N'//Solo las citas que contienen solicitudes que no han sido entregadas y que son de medicamentos
		   .append(" INNER JOIN EXME_ACTPACIENTEIND IND ON (INDH.EXME_ACTPACIENTEINDH_ID = IND.EXME_ACTPACIENTEINDH_ID AND IND.ESTATUS = 'S') ")//Solo las solicitudes que tiene medicamentos
		   .append(" group by CP.EXME_CTAPAC_ID) SBAL ") //Fin de la tabla SBAL
		   .append(" WHERE SBAL.BALANCE = 0) AND CM.ESTATUS = '6' "); //Solo se muestran las cuentas que estan pagadas por completo
		sql.append(MEXMELookupInfo.addAccessLevelSQL(Env.getCtx(), " ", MEXMECitaMedica.Table_ID, "CM"));
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				citas.add(new MEXMECitaMedica(Env.getCtx(), rs, null));
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, "MEXMECitaMedica.getCitaPagadas - sql: " + sql, e);
		} finally {
			DB.close(rs, pstmt);
		}
		return citas;
	}
	
	
	
	
	
	/**
	 * Obtiene las citas que tiene cuentas pacientes pagadas en su totalidad y que tiene solicitudes de medicamentos pendientes de surtir
	 * @return listado de citas
	 */
	public static List<MEXMECitaMedica> getCitasPagadas(final int mWarehouseID) {
		List<MEXMECitaMedica> citas = new ArrayList<MEXMECitaMedica>();
		StringBuilder sql = new StringBuilder();
		
		// Seleccionar citas medicas donde la cuenta paciente
		sql.append(" select cm.exme_citamedica_id from exme_citamedica cm ")
		   
		   .append(" inner join exme_actpaciente       ap on    cm.exme_citamedica_id = ap.exme_citamedica_id    ")
		   .append(" inner join exme_actpacienteindh apih on   ap.exme_actpaciente_id = apih.exme_actpaciente_id ")
		   .append("                                      and      apih.IsService = 'N'                          ")
		   .append("                                      and    apih.IsDelivered = 'N'                          ")
		   .append("                                      and apih.DocStatus <> ").append(DB.TO_STRING(X_EXME_ActPacienteIndH.DOCSTATUS_Completed))
		   
		   .append(" inner join exme_actpacienteInd   ind on  apih.exme_actpacienteindh_id = ind.exme_actpacienteindh_Id ")
		   .append("                                      and        ind.Estatus IN ('S','P') ")//Solo las solicitudes que tiene medicamentos
		   
		   .append(" INNER JOIN EXME_CtaPacDet        cpd ON cpd.exme_actpacienteind_id = ind.exme_actpacienteind_id  ")
		   .append(" INNER JOIN C_InvoiceLine          il ON      cpd.EXME_CtaPacDet_id = il.EXME_CtaPacDet_id               ")
		   .append(" INNER JOIN C_Invoice               i ON            il.C_Invoice_id = i.C_Invoice_id and i.ispaid = 'Y' ")
		
//		   .append("                                      and ind.M_Warehouse_ID = ?   ")  
//		   DEBERIA FILTRAR POR ALMACEN PERO NO SE ESTA GUARDANDO EL ALMACEN CORRECTO 
		   .append(" where cm.isActive = 'Y'  ") //Solo se muestran las cuentas que estan pagadas por completo
		   .append(MEXMELookupInfo.addAccessLevelSQL(Env.getCtx(), " ", MEXMECitaMedica.Table_ID, "CM"))
		   .append(" group by cm.exme_citamedica_id ");
		   
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
//			pstmt.setInt(1, mWarehouseID);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				citas.add(new MEXMECitaMedica(Env.getCtx(), rs.getInt("exme_citamedica_id"), null));
			}
			
		} catch (Exception e) {
			s_log.log(Level.SEVERE, "MEXMECitaMedica.getCitaPagadas - sql: " + sql, e);
		} finally {
			DB.close(rs, pstmt);
		}
		return citas;
	}
	
	/**
	 * Regresa los medicamentos prescritos en una cita.
	 * @param ctx Contexto
	 * @param exmeCitaMedicaID Id de la Cita
	 * @param trxName Nombre de la transaccion
	 * @return
	 */
	public List<MEXMEActPacienteInd> getSavedMeds() {
		final StringBuilder sqlW = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		final StringBuilder join = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		
		join.append(" INNER JOIN EXME_CtaPacDet        cpd ON cpd.exme_actpacienteind_id = EXME_ActPacienteInd.exme_actpacienteind_id  ");
		join.append(" INNER JOIN C_InvoiceLine          il ON      cpd.EXME_CtaPacDet_id = il.EXME_CtaPacDet_id               ");
		join.append(" INNER JOIN C_Invoice               i ON            il.C_Invoice_id = i.C_Invoice_id and i.ispaid = 'Y' ");
	
		join.append(" INNER JOIN EXME_ActPacienteIndH indh ON (indh.exme_actpacienteindh_id = EXME_ActPacienteInd.exme_actpacienteindh_id) ");
		join.append(" INNER JOIN EXME_ActPaciente      act ON (act.exme_actpaciente_id = indh.exme_actpaciente_id)");
		join.append(" LEFT JOIN M_Product             prod ON (EXME_ActPacienteInd.m_product_id = prod.m_product_id) ");//#1
		
		// where clause
		sqlW.append(" (prod.productclass = ? or (exme_actpacienteind.m_product_id is null and exme_actpacienteind.exme_genproduct_id is not null)) ");
		sqlW.append(" AND act.EXME_CitaMedica_ID=? ");//#2
		sqlW.append(" AND indh.isService='N' ");
		sqlW.append(" AND COALESCE(EXME_ActPacienteInd.exme_genproduct_id,EXME_ActPacienteInd.m_product_id) IS NOT NULL ");
		sqlW.append(" AND EXME_ActPacienteInd.surtir = 'Y' AND EXME_ActPacienteInd.estatus IN (");// 'S','P','C'
		sqlW.append(DB.TO_STRING(X_EXME_ActPacienteInd.ESTATUS_ScheduleService)).append(",");
		sqlW.append(DB.TO_STRING(X_EXME_ActPacienteInd.ESTATUS_RequestedService)).append(")");
		//sqlW.append(DB.TO_STRING(X_EXME_ActPacienteInd.ESTATUS_CompletedService)).append(")");

		List<MEXMEActPacienteInd> list = new Query(getCtx(), MEXMEActPacienteInd.Table_Name, sqlW.toString(), get_TrxName())//
			.setJoins(join)//joins
			.setParameters(MProduct.PRODUCTCLASS_Drug, getEXME_CitaMedica_ID())//parametros
			.setOnlyActiveRecords(true)// solo activos (actpacienteind.isactive='Y'
			.addAccessLevelSQL(true)// nivel de acceso
			.list();
		return list;
	}
	
	MEXMEGrupoCuestionario grupoCuestionario = null;
	public MEXMEGrupoCuestionario getEXME_GrupoCuestionario(){
        try	{
	        if(grupoCuestionario == null || grupoCuestionario.getEXME_GrupoCuestionario_ID() == 0)
	        	grupoCuestionario = new MEXMEGrupoCuestionario(getCtx(), new Integer(getEXME_GrupoCuestionario_ID()), null);
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=", e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=");
           throw new RuntimeException( e );
        }
        return grupoCuestionario;
    }
	
	private MEXMEConfigOV configOV = null;

	public MEXMEConfigOV getConfigOV() {
		if(configOV == null && this.getEXME_Medico_ID() > 0){
			configOV = MEXMEConfigOV.find(this.getCtx(), this.getEXME_Medico_ID(), null);
		}
		return configOV;
	}

	public void setConfigOV(MEXMEConfigOV configOV) {
		this.configOV = configOV;
	}
	
	/**
	 * Regresa una lista de citas con gargos segun los filtros seleccionados
	 * @param dateBEGINS Rango de fecha inicial en la que se angandaron las citas
	 * @param dateENDS Rango de fecha final en la que se angandaron las citas
	 * @param motivoCita Filtro por motivo de la cita
	 * @param primFC Filtro por value de la Primary Financial Class  
	 * @param trxName
	 * @return
	 */
	public static List<MEXMECitaMedica> getApptForBilling(Properties ctx, Date dateBEGINS, Date dateENDS, int motivoCita, String primFC, String trxName){
		StringBuilder sql = new StringBuilder();
		List<MEXMECitaMedica> list = new ArrayList<MEXMECitaMedica>();
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		try {
			sql.append(" SELECT CM.EXME_CITAMEDICA_ID,  FC.NAME,  FCP.NAME, ")
				.append(" COALESCE(SUM(PAY.PAYAMT), 0) AS PAGOS, COALESCE(SUM(CPD.LineNetAmt), 0) AS CARGOS ")
				.append(" FROM EXME_CITAMEDICA CM ")
				.append(" INNER JOIN EXME_CTAPAC CP ON (CM.EXME_CTAPAC_ID = CP.EXME_CTAPAC_ID) ")
				.append(" INNER JOIN EXME_PACIENTE PAC ON (CM.EXME_PACIENTE_ID = PAC.EXME_PACIENTE_ID) ")
				.append(" LEFT JOIN C_BPARTNER BPP ON (BPP.C_BPARTNER_ID = PAC.C_BPARTNER_ID) ")
				.append(" LEFT JOIN EXME_FINANCIALCLASS FCP ON (FCP.EXME_FINANCIALCLASS_ID = BPP.EXME_FINANCIALCLASS_ID) ")
				.append(" LEFT JOIN EXME_PACIENTEASEG PAS ON (PAS.EXME_CTAPAC_ID = CP.EXME_CTAPAC_ID AND PAS.PRIORITY = 1) ")
				.append(" LEFT JOIN C_BPARTNER BP ON (BP.C_BPARTNER_ID = PAS.C_BPARTNER_ID) ")
				.append(" LEFT JOIN EXME_FINANCIALCLASS FC ON (FC.EXME_FINANCIALCLASS_ID = BP.EXME_FINANCIALCLASS_ID) ")
				.append(" LEFT JOIN C_PAYMENT PAY ON (PAY.EXME_CTAPAC_ID = CP.EXME_CTAPAC_ID) ")
				.append(" LEFT JOIN C_CHARGE CH ON (PAY.C_CHARGE_ID = CH.C_CHARGE_ID AND CH.TYPE IN ('Y', 'E','N','P')) ")
				.append(" LEFT JOIN EXME_CTAPACDET CPD ON (CPD.EXME_CTAPAC_ID = CP.EXME_CTAPAC_ID AND CPD.ISACTIVE = 'Y') ")
				.append(" WHERE CM.ISACTIVE = 'Y' AND CM.ESTATUS = '6' ")
				.append(" AND CM.FECHAHRCITA BETWEEN ")
				.append(" to_timestamp( ? ,'yyyy-mm-dd hh24:mi') ")
				.append(" AND to_timestamp( ? , 'yyyy-mm-dd hh24:mi') ")
				.append(" AND CM.AD_ORG_ID = ? ");
			if (StringUtils.isNotEmpty(primFC)) {
				sql.append(" AND (FC.VALUE = ? OR (BP.C_BPARTNER_ID IS NULL AND FCP.VALUE = ?)) ");
			}
			if (motivoCita > 0) {
				sql.append(" AND CM.EXME_MOTIVOCITA_ID = ?");
			}
			sql.append(" GROUP BY CM.EXME_CITAMEDICA_ID,  FC.NAME,  FCP.NAME ");
			
			pstmt = DB.prepareStatement(sql.toString(), null);
			int param = 1;
			pstmt.setString(param++, Constantes.getSdfFechaHoraBD24().format(dateBEGINS));
			pstmt.setString(param++, Constantes.getSdfFechaHoraBD24().format(dateENDS));
			pstmt.setInt(param++, Env.getAD_Org_ID(ctx));
			if (StringUtils.isNotEmpty(primFC)) {
				pstmt.setString(param++, primFC);
				pstmt.setString(param++, primFC);
			}
			if (motivoCita > 0) {
				pstmt.setInt(param++, motivoCita);
			}
			
			rs = pstmt.executeQuery();
			while (rs.next()) {
				MEXMECitaMedica citaMed = new MEXMECitaMedica(ctx, rs.getInt(1), null);
				citaMed.setFinancialClassName(StringUtils.isEmpty(rs.getString(2)) ? rs.getString(3) : rs.getString(2));
				citaMed.setPagos(rs.getBigDecimal(4));
				citaMed.setCargos(rs.getBigDecimal(5));
				list.add(citaMed);
			}
		} catch (SQLException e) {
			s_log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=", e);
		} finally {
			DB.close(rs, pstmt);
		}
		return list;
	}
	
	public static class AppointmentReminder {
		private Timestamp appointmentDate;
		private int pacId;
		private String pacName;
		private String email;
		private String phone;
		private boolean byEmail;
		private int appointmentId;
		private String nombreMed;
		private Boolean notified = Boolean.FALSE;

		public int getAppointmentId() {
			return appointmentId;
		}

		public void setAppointmentId(int appointmentId) {
			this.appointmentId = appointmentId;
		}

		public String getNombreMed() {
			return nombreMed;
		}

		public void setNombreMed(String nombreMed) {
			this.nombreMed = nombreMed;
		}

		public Timestamp getAppointmentDate() {
			return appointmentDate;
		}

		public void setAppointmentDate(Timestamp appointmentDate) {
			this.appointmentDate = appointmentDate;
		}

		public int getPacId() {
			return pacId;
		}

		public void setPacId(int pacId) {
			this.pacId = pacId;
		}

		public String getPacName() {
			return pacName;
		}

		public void setPacName(String pacName) {
			this.pacName = pacName;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getPhone() {
			return phone;
		}

		public void setPhone(String phone) {
			this.phone = phone;
		}

		public boolean isByEmail() {
			return byEmail;
		}

		public void setByEmail(boolean byEmail) {
			this.byEmail = byEmail;
		}
		
		public Boolean getNotified() {
			return notified;
		}

		/**
		 * Actualiza la cita por base de datos
		 * 
		 * @param isNotified
		 *            Si esta o no notificado
		 * @return Si pudo guardar
		 */
		public void setNotified(Boolean notified) {

			Object[] arr = new Object[] { notified.booleanValue() ? "Y" : "N", getAppointmentId() };

			int r = DB.executeUpdate("Update exme_citamedica set notified = ? where exme_citamedica_id = ? ", arr, null);

			this.notified = r > 0 ? Boolean.TRUE : Boolean.FALSE;
		}
	}

	/**
	 * @return TRUE si la consulta ya tiene un cargo relacionado
	 */
	public boolean isCharged() {
		final StringBuilder join = new StringBuilder();
		join.append(" INNER JOIN EXME_CtaPacDet cp ON (EXME_ActPacienteInd.EXME_ActPacienteInd_ID=cp.EXME_ActPacienteInd_ID) ");
		return new Query(getCtx(), MEXMEActPacienteInd.Table_Name, " EXME_ActPacienteInd.EXME_ActPacienteIndH_ID=? ", get_TrxName())//
			.setParameters(getEXME_ActPacienteIndH_ID())//
			.setOnlyActiveRecords(true)//
			.setJoins(join)//
			.addAccessLevelSQL(true).firstId() > 0;
	}

	/**
	 *  @return La linea de actividad paciente del cargo de la consulta
	 */
	public MEXMEActPacienteInd getActPacienteIndCharge() {
		return new Query(getCtx(), MEXMEActPacienteInd.Table_Name, 
			" EXME_ActPacienteInd.EXME_ActPacienteIndH_ID=? ", get_TrxName())//
			.setParameters(getEXME_ActPacienteIndH_ID())//
			.setOnlyActiveRecords(true)//
			.addAccessLevelSQL(true).first();
	}
	
	/**
	 * Busca dentro de los cuestionarios relacionados a citas
	 * 
	 * @param ctx
	 *            Contexto
	 * @param pacId
	 *            Paciente
	 * @param searchString
	 *            Texto a buscar
	 * @param trxName
	 *            Trx
	 * @return Listado de eventos
	 */
	public static List<MEXMECuestionario.QEvent> searchIntoQuestionnaires(Properties ctx, int pacId, String searchString, String trxName) {
		List<MEXMECuestionario.QEvent> lst = new ArrayList<MEXMECuestionario.QEvent>();

		searchString = StringUtils.join(StringUtils.split(searchString, Constantes.SPACE), " | ");

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ");
		sql.append("  u.description ");
		sql.append("  AS user, ");
		sql.append("  rc.created, ");
		sql.append("  cm.EXME_CitaMedica_ID, ");
		sql.append("  cu.EXME_Cuestionario_id, ");
		sql.append("  ts_headline(rc.textbinary, ");
		sql.append("  to_tsquery(?)) ");
		sql.append("  AS hl, ");
		sql.append("  cu.name, ");
		sql.append("  u.ad_user_id ");
		sql.append("FROM ");
		sql.append("  exme_respuestacuestionario rc ");
		sql.append("  INNER JOIN exme_citamedica cm ");
		sql.append("  ON (rc.record_id = cm.exme_citamedica_id) ");
		sql.append("  INNER JOIN EXME_cuestionario cu ");
		sql.append("  ON rc.exme_cuestionario_id = cu.exme_cuestionario_id ");
		sql.append("  INNER JOIN AD_User u ");
		sql.append("  ON rc.createdby = u.ad_user_id ");
		sql.append("WHERE ");
		sql.append("  rc.ad_table_id = ? AND cm.exme_paciente_id = ? AND ");
		sql.append("  to_tsvector(rc.textbinary) @@ to_tsquery(?) ");
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, Constantes.SPACE, Table_Name, "cm"));

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setString(1, searchString);
			pstmt.setInt(2, Table_ID);
			pstmt.setInt(3, pacId);
			pstmt.setString(4, searchString);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				MEXMECuestionario.QEvent event = new MEXMECuestionario.QEvent();
				event.setDate(rs.getTimestamp("created"));
				event.setHeadLine(rs.getString("hl"));
				event.setId(rs.getInt("EXME_Cuestionario_id"));
				event.setName(rs.getString("name"));
				event.setRecordId(rs.getInt("EXME_CitaMedica_ID"));
				event.setTableId(Table_ID);
				event.setUserName(rs.getString("user"));
				event.setUserId(rs.getInt("ad_user_id"));

				lst.add(event);
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, null, e);
		} finally {
			DB.close(rs, pstmt);
		}
		return lst;
	}
	
	/**
	 * Busca los cuestionarios relacionados a citas
	 * 
	 * @param ctx
	 *            Contexto
	 * @param pacId
	 *            Paciente
	 * @param name
	 *            Nombre del cuestionario
	 * @param trxName
	 *            Trx
	 * @return Cuestionarios
	 */
	public static List<MEXMECuestionario.QEvent> getQuestionnaires(Properties ctx, int pacId, String name, String trxName){
		List<MEXMECuestionario.QEvent> list = new ArrayList<MEXMECuestionario.QEvent>();
		
		StringBuilder sql = new StringBuilder();
		sql.append("select * from (");
		sql.append("SELECT DISTINCT ON ");
		sql.append("  (cu.exme_cuestionario_id, cm.exme_citamedica_id) cu.exme_cuestionario_id, ");
		sql.append("  cm.exme_citamedica_id, ");
		sql.append("  cu.exme_cuestionario_id, ");
		sql.append("  cm.exme_citamedica_id, ");
		sql.append("  cu.name ");
		sql.append("  AS cuest, ");
		sql.append("  rc.created, ");
		sql.append("  u.ad_user_id, ");
		sql.append("  u.name ");
		sql.append("  AS userName ");
		sql.append("FROM ");
		sql.append("  exme_citamedica cm ");
		sql.append("  INNER JOIN exme_respuestacuestionario rc ");
		sql.append("  ON (cm.exme_citamedica_id = rc.record_id AND ");
		sql.append("  rc.ad_table_id = ?) ");
		sql.append("  INNER JOIN exme_cuestionario cu ");
		sql.append("  ON rc.exme_cuestionario_id = cu.exme_cuestionario_id ");
		sql.append("  INNER JOIN ad_user u ");
		sql.append("  ON rc.createdby = u.ad_user_id ");
		sql.append("WHERE ");
		sql.append("  cm.exme_paciente_id = ? AND ");
		sql.append("  (upper(cu.name) LIKE ? OR ");
		sql.append("  upper(cu.value) LIKE ?) ");
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, Constantes.SPACE, Table_Name, "cm"));
		sql.append(") test ");
		sql.append(" ORDER BY ");
		sql.append("  created desc ");
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, Table_ID);
			pstmt.setInt(2, pacId);
			pstmt.setString(3, "%" + StringUtils.defaultIfEmpty(name, StringUtils.EMPTY).toUpperCase() + "%");
			pstmt.setString(4, "%" + StringUtils.defaultIfEmpty(name, StringUtils.EMPTY).toUpperCase() + "%");
			
			rs = pstmt.executeQuery();
			while (rs.next()) {
				MEXMECuestionario.QEvent event = new MEXMECuestionario.QEvent();
				event.setTableId(Table_ID);
				event.setDate(rs.getTimestamp("created"));
				event.setId(rs.getInt("exme_cuestionario_id"));
				event.setName(rs.getString("cuest"));
				event.setRecordId(rs.getInt("exme_citamedica_id"));
				event.setUserId(rs.getInt("ad_user_id"));
				event.setUserName(rs.getString("userName"));
				list.add(event);
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, null, e);
		} finally {
			DB.close(rs, pstmt);
		}
		
		return list;
	}
 }