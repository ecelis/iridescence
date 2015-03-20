/*
 * Created on 7/09/2005
 *
 */
package org.compiere.process;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;

import org.compiere.model.MEXMECitaMedica;
import org.compiere.model.MEXMELookupInfo;
import org.compiere.model.MEXMEMedico;
import org.compiere.model.MEXMEPaciente;
import org.compiere.util.DB;
import org.compiere.util.Env;

/**
 * <b>Prop&oacute;sito : </b> <p>
 * <b>Creado     : </b> 7/09/2005 <p>
 * <b>Por        : </b> alberto reyes<p>
 * <b>Modificado : </b> $Date: 2006/02/20 22:40:01 $ <p>
 * <b>Por        : </b> $Author: mrojas $ <p>
 *
 * @author alberto reyes
 * @version $Revision: 1.1 $
 */
public class InterfazCitas extends InterfazSvrProcess {

    /**
     * 
     */
    public InterfazCitas() {
        super();
    }
    
    
    
    /* (non-Javadoc)
     * @see org.compiere.process.SvrProcess#prepare()
     */
    protected void prepare() {
        
        super.prepare();

    }

    /* (non-Javadoc)
     * @see org.compiere.process.SvrProcess#doIt()
     */
    protected String doIt() throws Exception {
        
        log.info("doIt()");
        
        String sql = null;
        
        String where = null;
        
        int total_rows = 0;
        int total_updated_rows = 0;
        
        try{
            
            /*
             * Obtenemos los registros que faltan ser tomados y pasados a Oracle.
             */
            Statement stmt =  cnn_int.createStatement();
            stmt.setFetchSize(1);
            sql = "SELECT * FROM \"PUB\".\"citas-ora\" WHERE \"Actualizado\" = 0";
            ResultSet rs = stmt.executeQuery(sql);
            
            while(rs.next()){
                
                //ID del registro generado.
                int record_id = 0;
                
                Timestamp fecha_cita = rs.getTimestamp("fecha-cita");
                boolean hora_cm	= rs.getBoolean("hora-cm");
                String hora_cn = rs.getString("hora-cn");
                String codigo_especialidad = rs.getString("codigo-especialidad");
                String medico = rs.getString("medico");
                int cedula = rs.getInt("cedula");
                
                log.info("Citas-Ora. fecha-cita:" + fecha_cita + ", hora-cm:" + hora_cm +
                        ", hora-cn:" + hora_cn + ", codigo-especialidad:" + codigo_especialidad + 
                        ", medico:" + medico + ", cedula:" + cedula);
                
                where = " \"fecha-cita\" = TO_DATE('"+ sdf.format(fecha_cita) +"','DD/MM/YYYY') AND " +
        		"\"hora-cm\" = "+ (hora_cm == true ? 1 : 0) +" AND \"hora-cn\" = '"+ hora_cn +"' AND \"codigo-especialidad\" = '"+ codigo_especialidad +
        		"' AND \"medico\" = '"+ medico +"' AND \"cedula\" = " + cedula;
                
                /*
                 * Obtenemos el registro completo de la BD de Progress.
                 */
                Statement stmt_pgs = cnn_pgs.createStatement();
                stmt_pgs.setFetchSize(1);
                sql = "SELECT \"nombre-paciente\", \"numhist\", \"medico\", \"hora-cn\", \"fecha-cita\", " +
                		" \"observaciones\", \"Duracion\", \"edad\", \"codigo-especialidad\", \"codigo-motivo\", \"tipo-cita\" " +
                		" FROM \"PUB\".\"Citas-Medicas\" WHERE " + where;
                
                ResultSet rs_pgs = stmt_pgs.executeQuery(sql);
                
                if(rs_pgs.next()){
                    
                    log.info("\t Record found.");
                    m_textmsg.append("Record found.\n");
                    
                    MEXMECitaMedica citaMedica = new MEXMECitaMedica(getCtx(), 0, trx_ora);
                    citaMedica.setClientOrg(m_client_id, m_org_id);
                    
                    sql = "SELECT CurrentNext FROM AD_Sequence s, AD_Table t WHERE t.tablename = s.name AND t.AD_Table_ID = " + MEXMECitaMedica.Table_ID;
                    Statement stmt_qry = cnn_ora.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
                    ResultSet rs_qry = stmt_qry.executeQuery(sql);
                    if(rs_qry.next())
                        citaMedica.setName("Cita " + rs_qry.getInt(1));
                    else
                        m_errormsg.append("Coud't determine the CurrentNext of Cita.\n");
                    
                    rs_qry.close();
                    stmt_qry.close();
                    rs_qry = null;
                    stmt_qry = null;
                    
                    citaMedica.setDescription(rs_pgs.getString("nombre-paciente"));
                    
                    // Buscamos paciente.
                    sql = "SELECT EXME_Paciente_ID FROM EXME_Paciente WHERE value = '" + rs_pgs.getInt("numhist") + " "
                   + MEXMELookupInfo.addAccessLevelSQL(getCtx(), " ", MEXMEPaciente.Table_Name)                
                   + ") ";
                    stmt_qry = cnn_ora.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
                    rs_qry = stmt_qry.executeQuery(sql);
                    if(rs_qry.next())
                        citaMedica.setEXME_Paciente_ID(rs_qry.getInt(1));
                    
                    rs_qry.close();
                    stmt_qry.close();
                    rs_qry = null;
                    stmt_qry = null;
                    
                    //Buscamos medico.
                    sql = "SELECT EXME_Medico_ID FROM EXME_Medico WHERE value = '" + rs_pgs.getString("medico") + " " 
                    + MEXMELookupInfo.addAccessLevelSQL(getCtx(), " ", MEXMEMedico.Table_Name)                
                    + ") ";
                    stmt_qry = cnn_ora.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
                    rs_qry = stmt_qry.executeQuery(sql);
                    if(rs_qry.next())
                        citaMedica.setEXME_Medico_ID(rs_qry.getInt(1));
                    else
                        ;//TODO: Log de Error.
                    
                    rs_qry.close();
                    stmt_qry.close();
                    rs_qry = null;
                    stmt_qry = null;
                    
                    // Fecha y Hr de la Cita.
                    Calendar cal = Calendar.getInstance();
                    String hora = rs_pgs.getString("hora-cn");
                    Timestamp fecha = rs_pgs.getTimestamp("fecha-cita");
                    String am_pm = hora.substring(hora.length() - 2, hora.length());
                    String min =  hora.substring(hora.length() - 5, hora.length() - 3);
                    String hr = hora.substring(0, 2);
                    hr = hr.replace(':',' ');
                    hr = hr.trim();
                    
                    cal.setTime(fecha);
                    cal.set(Calendar.AM_PM, (am_pm.equalsIgnoreCase("am") ? Calendar.AM : Calendar.PM) );
                    cal.set(Calendar.HOUR, Integer.parseInt(hr));
                    cal.set(Calendar.MINUTE, Integer.parseInt(min));
                    
                    citaMedica.setFechaHrCita(new Timestamp(cal.getTimeInMillis()));
                    
                    //Observaciones
                    citaMedica.setObservaciones(rs_pgs.getString("observaciones"));
                    //Duracion
                    citaMedica.setDuracion(rs_pgs.getInt("Duracion"));
                    //Edad
                    citaMedica.setEdad(rs_pgs.getInt("edad"));
                    
                    //Buscamos la Especialidad
                    sql = "SELECT EXME_Especialidad_ID FROM EXME_Especialidad WHERE value = '" + rs_pgs.getString("codigo-especialidad") + "' AND " + clientOrg;
                    stmt_qry = cnn_ora.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
                    rs_qry = stmt_qry.executeQuery(sql);
                    if(rs_qry.next())
                        citaMedica.setEXME_Especialidad_ID(rs_qry.getInt(1));
                    
                    rs_qry.close();
                    stmt_qry.close();
                    rs_qry = null;
                    stmt_qry = null;
                    
                    //Buscamos el Motivo de la Cita
                    sql = "SELECT EXME_MotivoCita_ID FROM EXME_MotivoCita WHERE UPPER(value) = UPPER('" + rs_pgs.getString("codigo-motivo") + "') AND " + clientOrg;
                    stmt_qry = cnn_ora.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
                    rs_qry = stmt_qry.executeQuery(sql);
                    if(rs_qry.next())
                        citaMedica.setEXME_MotivoCita_ID(rs_qry.getInt(1));
                    
                    rs_qry.close();
                    stmt_qry.close();
                    rs_qry = null;
                    stmt_qry = null;
                    
                    //La marcamos como confirmada.
                    citaMedica.setConfirmada(true);
                    
                    //Por Confirmar --> 0
                    citaMedica.setEstatus("0");
                    
                    //Buscamos el Tipo de Cita
                    sql = "SELECT EXME_TipoCita_ID FROM EXME_TipoCita WHERE value = '" + (rs_pgs.getBoolean("tipo-cita") ? "NORMAL" : "URGENTE") + "' AND " + clientOrg;
                    stmt_qry = cnn_ora.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
                    rs_qry = stmt_qry.executeQuery(sql);
                    if(rs_qry.next())
                        citaMedica.setEXME_TipoCita_ID(rs_qry.getInt(1));
                    
                    rs_qry.close();
                    stmt_qry.close();
                    rs_qry = null;
                    stmt_qry = null;
                    
                    //Buscamos el primer Asistente relacionado al Medico
                    sql = "SELECT EXME_Asistente_ID FROM EXME_MedicoAsist WHERE EXME_Medico_ID = " + citaMedica.getEXME_Medico_ID();
                    stmt_qry = cnn_ora.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
                    rs_qry = stmt_qry.executeQuery(sql);
                    if(rs_qry.next())
                        citaMedica.setEXME_Asistente_ID(rs_qry.getInt(1));
                    
                    rs_qry.close();
                    stmt_qry.close();
                    rs_qry = null;
                    stmt_qry = null;
                    
                    // Guardamos el registro en Oracle.
                    if(!citaMedica.save(trx_ora)){
                        m_errormsg.append("Cita Medica not saved with WHERE=" + where + "/n");
                    }
                    
                    record_id = citaMedica.getEXME_CitaMedica_ID();
                    
                }else{
                    
                    log.severe("\t Record Not found.");
                }
                
                rs_pgs.close();
                rs_pgs = null;
                
                if(m_errormsg.length() <= 0 && record_id > 0){
	                //Actualizamos el estatus de la tabla e Interfaz para el registro actual.
	                Statement stmt_upd =  cnn_int.createStatement();
	                String sql_upd = "UPDATE \"PUB\".\"citas-ora\" SET \"Actualizado\" = 1, \"Record_ID\" = "+ record_id +
	                ", FechaAct = '" + sdf2.format(DB.getDateForOrg(getCtx())) + "' WHERE " + where;
	                int updated = stmt_upd.executeUpdate(sql_upd);
	                if(updated > 0){
	                    
	                    total_updated_rows++;
	                    log.log(Level.INFO, updated + " rows updated.");
	                    m_textmsg.append("RowUpdated=" + record_id + ".\n");
	                    
	                }else{
	                    m_errormsg.append("Citas-Ora was not Updated. /n");
	                }
	                
	                stmt_upd.close();
	            	stmt_upd = null; 
                }//Existen errores?
                else{
                    break;
                }
                
                total_rows++;
                
            }//rs.
            
            rs.close();
            rs = null;
            
        }catch (SQLException sqle) {
            
            m_errormsg.append("Error: " + sqle.getMessage());
            sqle.printStackTrace();
            
        }finally{
            
            if(m_errormsg.length() > 0)
                rollback();
            else
                commit();	
            
            super.doLog(total_rows);
            super.doIt();
            close();
        }
        
        return null;
    }

}
