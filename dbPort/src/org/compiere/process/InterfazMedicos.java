/*
 * Created on 7/09/2005
 *
 */
package org.compiere.process;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.StringTokenizer;
import java.util.logging.Level;

import org.compiere.model.MLocation;
import org.compiere.model.MEXMEMedico;
import org.compiere.model.MUser;
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
public class InterfazMedicos extends InterfazSvrProcess {

    /**
     * 
     */
    public InterfazMedicos() {
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
        
        String where_m = null;
        
        int total_rows = 0;
               
        
        
               
        try{
            
            /*
             * Obtenemos los registros que faltan ser tomados y pasados a Oracle.
             */
                        
            Statement stmt =  cnn_int.createStatement();
            stmt.setFetchSize(1);
            sql = "SELECT * FROM \"PUB\".\"medicos-ora\" WHERE \"Actualizado\" = 0";
            ResultSet rs = stmt.executeQuery(sql);
            
            while(rs.next() && total_rows <= 25 ){
                
                //ID del registro generado
                //obtine el registro de interfaz.
                int record_id = 0;
                
                String str_medicos = rs.getString("medicos");
                                                             
                log.info("medicos-ora. medicos:" + str_medicos);
                
                where = " \"codigo\" = '" + str_medicos + "' ";
                
                /*
                 * Obtenemos el registro completo de la BD de Progress.
                 * de medicos
                 */
                Statement stmt_pgs = cnn_pgs.createStatement();
                stmt_pgs.setFetchSize(1);                
                sql = "SELECT * FROM \"PUB\".\"medicos\" WHERE " + where;                
                ResultSet rs_pgs = stmt_pgs.executeQuery(sql);
                
                if(rs_pgs.next()){
                    
                    log.info("\t Record found.");
                    m_textmsg.append("Record found.\n");
                    
                    
                    /* Busca en la base de datos de oracle */                    
                    MEXMEMedico medicos = new MEXMEMedico(getCtx(), 0, trx_ora);
                    medicos.setClientOrg(m_client_id, m_org_id);
                    
                    /*Empieza Llenado de Datos a la Base de Datos Oracle y Trae los de Progres*/
                    //nombre medico               
                    medicos.setValue(rs_pgs.getString("codigo"));
                    medicos.setName(rs_pgs.getString("nombre"));
                    medicos.setApellido1(rs_pgs.getString("apellido_1"));
                    medicos.setApellido2(rs_pgs.getString("apellido_2"));
                    medicos.setSexo(rs_pgs.getBoolean("sexo") == true ? "F" : "M");                    
                    medicos.setEstaSuspendido(rs_pgs.getBoolean("suspendido"));
                    medicos.setFechaNac(rs_pgs.getTimestamp("FechaNac"));                                      
                    medicos.setEMail(rs_pgs.getString("e-mail"));
                    medicos.setEsInterno(rs_pgs.getBoolean("Interno"));
                    medicos.setIsActive(true);
                    
                    
                    // Buscamos Tipo de Medico.                                                         
                    sql = "SELECT EXME_tipomedico_ID FROM EXME_tipomedico WHERE value = '" + rs_pgs.getString("tipo-medico") + "' AND " + clientOrg;
                    Statement stmt_qry = cnn_ora.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
                    ResultSet rs_qry = stmt_qry.executeQuery(sql);
                    if(rs_qry.next())
                        medicos.setEXME_TipoMedico_ID(rs_qry.getInt(1));
                    
                    rs_qry.close();
                    stmt_qry.close();
                    rs_qry = null;
                    stmt_qry = null;
                    
                    medicos.setDescription(rs_pgs.getString("referencia"));
                    medicos.setTelParticular(rs_pgs.getString("telefonoh"));
                    //medicos.setRadio("celular");
                    medicos.setFechaIngreso(rs_pgs.getTimestamp("Fechaingreso"));
                    medicos.setCedProfesional(rs_pgs.getString("cedula"));
                    medicos.setCodSanidad(rs_pgs.getString("sanidad"));
                    medicos.setFechaBaja(rs_pgs.getTimestamp("fechabaja"));
                    medicos.setFechaTitulo(rs_pgs.getTimestamp("fechatitulo"));
                    medicos.setFechaCertifConsejo(rs_pgs.getTimestamp("cert_fecha"));
                    medicos.setFechaVencimCertif(rs_pgs.getTimestamp("cert_vencimiento"));
                    medicos.setEstaRecertificado(rs_pgs.getBoolean("cert_recertificado"));
                    medicos.setTieneIncentivo(rs_pgs.getBoolean("incentivos"));
                    medicos.setTelConsultorio(rs_pgs.getString("cm_telefonos"));
                    medicos.setEstaCertifConsejo(rs_pgs.getBoolean("cert_consejo"));
                    medicos.setCelular(rs_pgs.getString("celular"));
                    
                    //Direccion Particular
                    MLocation location = getLocation(cnn_ora, rs_pgs, "country-code", "st");
                    
                    location.setAddress1(rs_pgs.getString("direccionh"));
                    location.setCity(rs_pgs.getString("city"));
                    location.setPostal(rs_pgs.getString("zip-code"));
                    
                    if(!location.save(trx_ora)){
                        m_errormsg.append("Location not saved with values=" + location.toString() + "./n");
                    }
                    
                    medicos.setC_Location_ID(location.getC_Location_ID());
                    //Telefono particular
                    medicos.setTelParticular(rs_pgs.getString("telefonoh"));
                    
                    //Buscamos el Motivo de Baja.                                                         
                    sql = "SELECT EXME_motivobaja_ID FROM EXME_motivobaja WHERE value = '" + rs_pgs.getString("motivobaja") + "' AND " + clientOrg;
                    stmt_qry = cnn_ora.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
                    rs_qry = stmt_qry.executeQuery(sql);
                    if(rs_qry.next())
                        medicos.setEXME_MotivoBaja_ID(rs_qry.getInt(1));
                    
                    rs_qry.close();
                    stmt_qry.close();
                    rs_qry = null;
                    stmt_qry = null;
                    
                    //Buscamos la Universidad.                                                         
                    sql = "SELECT EXME_Universidad_ID FROM EXME_Universidad WHERE value = '" + rs_pgs.getString("Universidad") + "' AND " + clientOrg;
                    stmt_qry = cnn_ora.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
                    rs_qry = stmt_qry.executeQuery(sql);
                    if(rs_qry.next())
                        medicos.setEXME_Universidad_ID(rs_qry.getInt(1));
                    
                    rs_qry.close();
                    stmt_qry.close();
                    rs_qry = null;
                    stmt_qry = null;
                    
                    //Fecha de Certificacion por el consejo
                    medicos.setFechaCertifConsejo(rs_pgs.getTimestamp("cert_fecha"));
                    medicos.setFechaVencimCertif(rs_pgs.getTimestamp("cert_vencimiento"));
                    medicos.setEstaCertifConsejo(rs_pgs.getBoolean("cert_consejo"));
                    medicos.setEstaRecertificado(rs_pgs.getBoolean("cert_recertificado"));
                    
                    //Mensaje Medico
                    medicos.setMensajeMedico(rs_pgs.getString("mensaje-medico"));
                    
                    //Direccion Consultorio
                    MLocation location_cons = getLocation(cnn_ora, rs_pgs, "country-code", "CM_Estado");
                    location_cons.setAddress1(rs_pgs.getString("direccionc"));
                    location_cons.setCity(rs_pgs.getString("city"));
                    location_cons.setPostal(rs_pgs.getString("CM_CodigoPostal"));
                    
                    if(!location_cons.save(trx_ora)){
                        m_errormsg.append("Location not saved with values=" + location.toString() + "./n");
                    }
                    
                    medicos.setC_Location_Cons_ID(location_cons.getC_Location_ID());
                    
                    medicos.setTelConsultorio(rs_pgs.getString("telefonoc"));
                    
                    //Buscamos el Centro Medico.                                                         
                    sql = "SELECT EXME_CentroMedico_ID FROM EXME_CentroMedico WHERE value = '" + rs_pgs.getString("cm_centro_medico") + "' AND " + clientOrg;
                    stmt_qry = cnn_ora.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
                    rs_qry = stmt_qry.executeQuery(sql);
                    if(rs_qry.next())
                        medicos.setEXME_CentroMedico_ID(rs_qry.getInt(1));        
                    
                    rs_qry.close();
                    stmt_qry.close();
                    rs_qry = null;
                    stmt_qry = null;
                    
                    medicos.setNoConsultorio(rs_pgs.getString("cm_consultorio"));
                    
                        
                    //FIXME: Turnos
                    String turnos_ini = rs_pgs.getString("turnos-hora-inicio");
                    String turnos_fin = rs_pgs.getString("turnos-hora-final");
                    
                    String[] hrs = new String[6]; //Las horas extraidas de progress.
                    String[] turno_value = new String[6]; //Las horas para buscar en el campo value de EXME_Turno.
                    
                    StringTokenizer st = new StringTokenizer(turnos_ini,";");
                    
                    int p = 0;
                    while(st.hasMoreElements() && p < 3){
                        hrs[p] = (String)st.nextElement();
                        p++;
                    }
                    
                    st = new StringTokenizer(turnos_fin,";");
                    
                    while(st.hasMoreElements() && p < 6){
                        hrs[p] = (String)st.nextElement();
                        p++;
                    }
                    
                    //Baremos las 0-2 iniciales y 3-5 finales posiciones.
                    for(p = 0; p < hrs.length; p++){                        
                        hrs[p] = String.valueOf(Integer.parseInt(hrs[p]) / 3600);
                        
                        String hr = null;
                        String min = null;
                        
                        if(!hrs[p].equalsIgnoreCase("0"))
                           {
	                        int idx = hrs[p].indexOf('.');
	                        if(idx > 0){
	    	                    hr = hrs[p].substring(0,idx);
	    	                    min = hrs[p].substring(idx+1, hrs[p].length());
	                        }else{
	                            hr = hrs[p].substring(0,hrs[p].length());
	    	                    min = "00";
	                        }
	                        
	                        if(!min.equalsIgnoreCase("00")){
	                            min = String.valueOf((Integer.parseInt(min)/100) * 60);
	                        }
	                        
	                        // Si la longitud de la hr no es de dos posiciones, se agrega un 0 al inicio.
	                        if(hr.length() == 1)
	                            hr = "0" + hr;
	                        
	                        turno_value[p] = hr + min;
                        }//no es igual a cero
                        else{                            
                            turno_value[p] = "X";                            
                        }
                        
                    }// p
                    
                    //Buscamos el turno del medico.                                                         
                    sql = "SELECT EXME_Turnos_ID FROM EXME_Turnos WHERE value = '" 
                        + turno_value[0] + "," + turno_value[1] + "," + turno_value[2] +
                        "-" 
                        + turno_value[3] + "," + turno_value[4] + "," + turno_value[5] +
                        "' AND " + clientOrg;
                    
                    stmt_qry = cnn_ora.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
                    rs_qry = stmt_qry.executeQuery(sql);
                    if(rs_qry.next())                                                
                        medicos.setEXME_Turnos_ID(rs_qry.getInt(1));   
                    
                    rs_qry.close();
                    stmt_qry.close();
                    rs_qry = null;
                    stmt_qry = null;
                    
                    medicos.setIntervaloConsulta(rs_pgs.getInt("intervalo"));
                    
                    //Buscamos el producto relacionado por concepto de consulta medica     
                    sql = "SELECT M_Product_ID FROM M_Product WHERE value = '" + rs_pgs.getString("item-no") + "' AND " + clientOrg;
                    stmt_qry = cnn_ora.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
                    rs_qry = stmt_qry.executeQuery(sql);
                    if(rs_qry.next())
                        medicos.setM_Product_ID(rs_qry.getInt(1));   
                    
                    rs_qry.close();
                    stmt_qry.close();
                    rs_qry = null;
                    stmt_qry = null;
                    
                    medicos.setModificaEnFactura(false);
            
                    //Creamos el usuario asociado al Medico.
                    MUser user = new MUser(medicos.getCtx(), 0, trx_ora);
                    user.setName(medicos.getValue());
                    user.setDescription(medicos.getFullName());
                    user.setPassword("xxxx");//Por defecto siempre son 4 x
                    user.setEMail(medicos.getEMail());
            
                    if(!user.save(trx_ora)){
                        m_errormsg.append("User not saved with values=" + user.toString() + "/n");
                    }
            
                    medicos.setAD_User_ID(user.getAD_User_ID());
            
	                // Guardamos el registro en Oracle.
	                 if(!medicos.save(trx_ora)){
	                       m_errormsg.append("Record not saved with WHERE=" + where + "/n");
	                    }
                    
                    record_id = medicos.getEXME_Medico_ID();
                    
                }else{                    
                    log.severe("\t Record Not found.");
                } //medicos
                
                rs_pgs.close();
                rs_pgs = null;
                
                if(m_errormsg.length() <= 0 && record_id > 0){
	                //Actualizamos el estatus de la tabla e Interfaz para el registro actual.
	                Statement stmt_upd =  cnn_int.createStatement();	                
	                where_m = " \"medicos\" = '" + str_medicos + "' ";
	                String sql_upd = "UPDATE \"PUB\".\"medicos-ora\" SET \"Actualizado\" = 1, \"Record_ID\" = "+ record_id +
	                ", FechaAct = '" + sdf2.format(DB.getDateForOrg(getCtx())) + "' WHERE " + where_m;
	                int updated = stmt_upd.executeUpdate(sql_upd); 
	                if(updated > 0){	                    
	                    total_rows++;
	                   log.log(Level.INFO, updated + " rows updated."); 
	                   m_textmsg.append("RowUpdated=" + record_id + ".\n"); 	                    
	                  }else{
	                    m_errormsg.append("Medicos was not Updated. /n");
	                  }
	                
	                stmt_upd.close();
                	stmt_upd = null; 
	                
                }//Existen errores?
                else{
                    break;
                }
                
            }//rs.
            
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
