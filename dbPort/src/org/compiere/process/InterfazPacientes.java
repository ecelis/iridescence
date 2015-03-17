/*
 * Created on 9/09/2005
 *
 */
package org.compiere.process;


/**
 * <b>Prop&oacute;sito : </b> <p>
 * <b>Creado     : </b> 9/09/2005 <p>
 * <b>Por        : </b> alberto reyes<p>
 * <b>Modificado : </b> $Date: 2006/02/20 22:40:01 $ <p>
 * <b>Por        : </b> $Author: mrojas $ <p>
 *
 * @author alberto reyes
 * @version $Revision: 1.1 $
 */
public class InterfazPacientes extends InterfazSvrProcess {

    /**
     * 
     */
    public InterfazPacientes() {
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
        
        //String sql = null;
        
        //String where = null;
        
        //int total_rows = 0;
        
        //Connection cnn_exp = null;
        
      /*  try{
            
            /*
             * Obtenemos los registros que faltan ser tomados y pasados a Oracle.
             */
      /*      Statement stmt =  cnn_int.createStatement();
            sql = "SELECT * FROM \"PUB\".\"paciente-ora\" WHERE \"Actualizado\" = 0";
            ResultSet rs = stmt.executeQuery(sql);
            
            while(rs.next() && total_rows < 60){
                
                //ID del registro generado.

                int record_id = 0;
                
                String historia = rs.getString("numhist");
                
                log.info("Paciente-Ora. historia:" + historia);
                
                where =  " \"numhist\" = '" + historia + "' ";
                                
                /*
                 * Obtenemos el registro completo de la BD de Progress.
                 */                
       /*         Statement stmt_pgs = cnn_pgs.createStatement();
                
                sql = "SELECT * FROM \"PUB\".\"paciente\" WHERE " + where;
                
                
                ResultSet rs_pgs = stmt_pgs.executeQuery(sql);
                
                if(rs_pgs.next()){
                    
                    log.info("\t Record found.");
                    m_textmsg.append("Record found.\n");
                    
                    MEXMEPaciente paciente = new MEXMEPaciente(getCtx(), 0, trx_ora);
                    paciente.setClientOrg(m_client_id, m_org_id);
                    
                    //Historia
                    paciente.setValue(String.valueOf(rs_pgs.getInt("NumHist")));
                    //Primer Nombre
                    paciente.setName(rs_pgs.getString("Nombre_1"));
                    //No. Expediente
                    
                    //Creamos conexion para el expediente.
                    cnn_exp = ProgressCnn.createConnection("jdbc:JdbcProgress:T:192.168.11.230:6002:iner","sysprogress","sysprogress");
                    
                    sql = "SELECT \"expediente\" FROM \"PUB\".\"Hist-Exp\" WHERE \"numhist\" = " + paciente.getValue();
                    Statement stmt_qry = cnn_exp.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
                    ResultSet rs_qry = stmt_qry.executeQuery(sql);
                    if(rs_qry.next())
                        paciente.setDescription(rs_qry.getString("expediente"));
                    else
                        m_textmsg.append("Coud't find the No. Expediente of Paciente with Historia "+ paciente.getValue() +".\n");
                    
                    rs_qry.close();
                    stmt_qry.close();
                    
                    rs_qry = null;
                    stmt_qry = null;
                    
                    //Segundo Nombre
                    paciente.setNombre2(rs_pgs.getString("Nombre_2"));
                    //Apellidos
                    paciente.setApellido1(rs_pgs.getString("Apellido_1"));
                    paciente.setApellido2(rs_pgs.getString("Apellido_2"));
                    paciente.setApellido3(rs_pgs.getString("Apellido_3"));
                    
                    //Sexo
                    paciente.setSexo(rs_pgs.getBoolean("sexo") == true ? "F" : "M");
                    //IMSS
                    paciente.setImss(rs_pgs.getString("imss"));
                    //RFC
                    paciente.setRfc(rs_pgs.getString("referencia"));
                    //CURP
                    paciente.setCurp(rs_pgs.getString("curp"));
                    //Estado Civil
                    String estado_civil = rs_pgs.getString("estado-civil");
                    String edoCivil = "O"; //Por defecto: Otro
                    
                    edoCivil = (estado_civil.equalsIgnoreCase("Soltero") == true ? "S" 
                            : estado_civil.equalsIgnoreCase("Casado") == true ? "C" 
                            : estado_civil.equalsIgnoreCase("Viudo") == true ? "V"
                            : estado_civil.equalsIgnoreCase("Divorciado") == true ? "D"
                            : estado_civil.equalsIgnoreCase("Union Libre") == true ? "U"
                            : "O");
                    
                    paciente.setEdoCivil(edoCivil);
                    
                    //Fecha y Hr de nacimiento
                    paciente.setFechaNac(new Timestamp(rs_pgs.getDate("fecha-nacimiento").getTime()));
                    paciente.setHoraNac(String.valueOf(rs_pgs.getInt("Filler_2")));
                    
                    MLocation location = getLocation(cnn_ora, rs_pgs, "pais-particular", "estado-particular");
                    
                    location.setAddress1(rs_pgs.getString("direccion-habitacion"));
                    location.setAddress2(rs_pgs.getString("direccion-habitacion-2"));
                    location.setAddress3(rs_pgs.getString("Filler_1"));
                    location.setCity(rs_pgs.getString("Filler_6"));
                    location.setPostal(rs_pgs.getString("Procedencia"));
                    
                    //Guradamos la direccion.
                    if(!location.save(trx_ora)){
                        m_errormsg.append("Location not saved with values=" + location.toString() + "./n");
                    }
                    
                    paciente.setC_Location_ID(location.getC_Location_ID());
                    
                    //Tipo de Paciente
                    String tipo_paciente = rs_pgs.getString("tipo-paciente");
                    if(tipo_paciente == null || tipo_paciente.trim().length() == 0)
                        tipo_paciente = "00";
                            
                    sql = "SELECT EXME_TipoPaciente_ID FROM EXME_TipoPaciente WHERE UPPER(value) = UPPER('" + tipo_paciente +  "') AND " + clientOrg;
                    stmt_qry = cnn_ora.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
                    rs_qry = stmt_qry.executeQuery(sql);
                    if(rs_qry.next())
                         paciente.setEXME_TipoPaciente_ID(rs_qry.getInt("EXME_TipoPaciente_ID"));
                    else
                        m_errormsg.append("Coud't find the Tipo Paciente with value "+ tipo_paciente +".\n");
                    
                    rs_qry.close();
                    stmt_qry.close();
                    rs_qry = null;
                    stmt_qry = null;
                    
                    //Religion
                    String religion = rs_pgs.getString("religion");
                    if(religion == null || religion.trim().length() == 0)
                        religion = "NO CONTESTA";
                    
                    sql = "SELECT EXME_Religion_ID FROM EXME_Religion WHERE UPPER(name) = UPPER('" + religion +  "') AND " + clientOrg;
                    stmt_qry = cnn_ora.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
                    rs_qry = stmt_qry.executeQuery(sql);
                    if(rs_qry.next())
                         paciente.setEXME_Religion_ID(rs_qry.getInt("EXME_Religion_ID"));
                    else
                        m_errormsg.append("Coud't find the Religion with value "+ religion +".\n");
                    
                    rs_qry.close();
                    stmt_qry.close();
                    rs_qry = null;
                    stmt_qry = null;
                    
                    //Es Asegurado
                    paciente.setEsAsegurado(rs_pgs.getBoolean("seguros"));
                    
                    //Ocupacion
                    String ocupacion = rs_pgs.getString("ocupacion");
                    if(ocupacion == null || ocupacion.trim().length() == 0)
                        ocupacion = "000";//(NINGUNO)
                    
                    try{
                        Integer.parseInt(ocupacion);
                    }catch (NumberFormatException nfe) {
                        ocupacion = "000";//(NINGUNO)
                    }
                    
                    sql = "SELECT EXME_Ocupacion_ID FROM EXME_Ocupacion WHERE UPPER(value) = UPPER('" + ocupacion +  "') AND " + clientOrg;
                    stmt_qry = cnn_ora.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
                    rs_qry = stmt_qry.executeQuery(sql);
                    if(rs_qry.next())
                         paciente.setEXME_Ocupacion_ID(rs_qry.getInt("EXME_Ocupacion_ID"));
                    else
                        m_errormsg.append("Coud't find the Ocupacion with value "+ ocupacion +".\n");
                    
                    rs_qry.close();
                    stmt_qry.close();
                    rs_qry = null;
                    stmt_qry = null;
                    
                    //Limite de Credito
                    paciente.setLimiteCredito(rs_pgs.getBigDecimal("Filler_3"));
                    
                    //Telefono particular
                    paciente.setTelParticular(rs_pgs.getString("Telefono-habitacion"));
                    
                    //Email
                    paciente.setEMail(rs_pgs.getString("E-mail"));
                    
                    //Nombre del Familiar
                    //paciente.setNombreFamiliar(); //FIXME: Observacion[1] en Progress.
                    
                    //Direccion del Familiar
                    //paciente.setDirFamiliar(); //FIXME: Observacion[2] en Progress.
                    
                    //Tel FAmiliar
                    //paciente.setTelFamiliar(); //FIXME: Observacion[3] en Progress.
                    
                    //Datos del Seguro
                    sql = "SELECT * FROM \"PUB\".\"paciente-seguro\" WHERE \"historia\" = " + rs_pgs.getInt("numhist");
                    stmt_qry = cnn_pgs.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
                    rs_qry = stmt_qry.executeQuery(sql);
                    if(rs_qry.next()){
                        paciente.setPoliza(rs_qry.getString("Poliza"));
                        paciente.setTitular(rs_qry.getString("nombre-asegurado"));
                        paciente.setNoAutorizacion(rs_qry.getString("clave-verificacion"));
                        Date fecha_vencimiento = rs_qry.getDate("fecha-vencimiento");
                    	if(fecha_vencimiento != null){
                    	    paciente.setFechaVigencia(new Timestamp(fecha_vencimiento.getTime()));
                    	}
                        
                        sql = "SELECT C_BPartner_ID FROM C_BPartner WHERE UPPER(value) = UPPER('" + rs_qry.getString("cust-no") +  "') AND " + clientOrg;
                        Statement stmt_qry2 = cnn_ora.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
                        ResultSet rs_qry2 = stmt_qry2.executeQuery(sql);
                        if(rs_qry2.next())
                             paciente.setC_BPartner_Seg_ID(rs_qry2.getInt("C_BPartner_ID"));
                        
                        rs_qry2.close();
                        stmt_qry2.close();
                        rs_qry2 = null;
                        stmt_qry2 = null;
                    }
                    
                    rs_qry.close();
                    stmt_qry.close();
                    rs_qry = null;
                    stmt_qry = null;
                    
                    //Datos del Fiador
                    sql = "SELECT * FROM \"PUB\".\"paciente-fiador\" WHERE \"historia\" = " + rs_pgs.getInt("numhist");
                    stmt_qry = cnn_pgs.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
                    rs_qry = stmt_qry.executeQuery(sql);
                    if(rs_qry.next()){
                        
                        String cust_no = rs_qry.getString("cust-no");
                        if(cust_no != null && cust_no.startsWith("*")){
                            cust_no = cust_no.replace('*','_');
                        }
                        
                        sql = "SELECT C_BPartner_ID FROM C_BPartner WHERE UPPER(value) = UPPER('" + cust_no +  "') AND " + clientOrg;
                        Statement stmt_qry2 = cnn_ora.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
                        ResultSet rs_qry2 = stmt_qry2.executeQuery(sql);
                        if(rs_qry2.next())
                             paciente.setC_BPartner_ID(rs_qry2.getInt("C_BPartner_ID"));
                        
                        paciente.setDirPersResp(rs_qry.getString("fiador-direccion"));
                     
                        rs_qry2.close();
                        stmt_qry2.close();
                        rs_qry2 = null;
                        stmt_qry2 = null;
                    }
                    
                    rs_qry.close();
                    stmt_qry.close();
                    rs_qry = null;
                    stmt_qry = null;
                    
                    //Observaciones
                    paciente.setObservaciones(rs_pgs.getString("observaciones"));
                    
                    MLocation location_nac = getLocation(cnn_ora, rs_pgs, "pais-nacimiento", "estado-nacimiento");
                    location_nac.setAddress1(rs_pgs.getString("lugar-nacimiento"));
                    
                    //Guradamos la direccion de nacimiento.
                    if(!location_nac.save(trx_ora)){
                        m_errormsg.append("Location not saved with values=" + location_nac.toString() + "./n");
                    }
                    
                    paciente.setC_Location_Nac_ID(location_nac.getC_Location_ID());
                    
                    MBPartnerLocation bpLocation = new MBPartnerLocation(new MBPartner(getCtx(), paciente.getC_BPartner_ID(), trx_ora));
                    bpLocation.setC_Location_ID(paciente.getC_Location_ID());
                    bpLocation.setName(paciente.getFullName());
                    bpLocation.setIsBillTo(true);
                    bpLocation.setIsPayFrom(true);
                    bpLocation.setIsRemitTo(true);
                    bpLocation.setIsShipTo(true);
                    
                    if(!bpLocation.save(trx_ora)){
                        m_errormsg.append("BPartner Location not saved with values=" + bpLocation.toString() + "./n");
                    }
                    
                    
                    //Datos del Familiar
                    sql = "SELECT * FROM \"PUB\".\"paciente-familia\" WHERE \"historia\" = " + rs_pgs.getInt("numhist");
                    stmt_qry = cnn_pgs.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
                    rs_qry = stmt_qry.executeQuery(sql);
                    if(rs_qry.next()){                        
                        paciente.setNombre_Fam(rs_qry.getString("nombre-familiar"));
                        paciente.setApellido1_Fam(paciente.getNombre_Fam());
                        paciente.setApellido2_Fam(paciente.getNombre_Fam());
                        paciente.setTelParticular_Fam(rs_qry.getString("tel-particular"));
                        paciente.setPuesto_Fam(rs_qry.getString("Filler_6"));
                        paciente.setRFC_Fam(rs_qry.getString("rfc"));
                        
                        //Parentesco
                        String parentesco = rs_qry.getString("cod-parentesco");
                        if(parentesco == null || parentesco.trim().length() == 0)
                            parentesco = "00"; //NINGUNO
                        
                        sql = "SELECT EXME_Parentesco_ID FROM EXME_Parentesco WHERE UPPER(value) = UPPER('" + parentesco +  "') AND " + clientOrg;
                        Statement stmt_qry2 = cnn_ora.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
                        ResultSet rs_qry2 = stmt_qry2.executeQuery(sql);
                        if(rs_qry2.next())
                             paciente.setEXME_Parentesco_ID(rs_qry2.getInt("EXME_Parentesco_ID"));
                        else
                            m_errormsg.append("Parentesco not found with cod-parentesco=" + parentesco+ "\n");
                     
                        rs_qry2.close();
                        stmt_qry2.close();
                        rs_qry2 = null;
                        stmt_qry2 = null;
                    }else{
                        
                        paciente.setNombre_Fam(paciente.getName());
                        paciente.setApellido1_Fam(paciente.getApellido1());
                        paciente.setApellido2_Fam(paciente.getApellido2());
                        paciente.setTelParticular_Fam(paciente.getTelParticular());
                        
                        sql = "SELECT EXME_Parentesco_ID FROM EXME_Parentesco WHERE UPPER(value) = UPPER('00') AND " + clientOrg; //Ninguno
                        Statement stmt_qry2 = cnn_ora.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
                        ResultSet rs_qry2 = stmt_qry2.executeQuery(sql);
                        if(rs_qry2.next())
                             paciente.setEXME_Parentesco_ID(rs_qry2.getInt("EXME_Parentesco_ID"));
                        else
                            m_errormsg.append("Parentesco not found with cod-parentesco=" + "00" + "\n");
                        
                        rs_qry2.close();
                        stmt_qry2.close();
                        rs_qry2 = null;
                        stmt_qry2 = null;
                    }
                    
                    rs_qry.close();
                    stmt_qry.close();
                    rs_qry = null;
                    stmt_qry = null;
                    
                    //Validamos algunos valores obligatorios y establecemos valores por defecto.
                    if(paciente.getTelParticular_Fam() == null || paciente.getTelParticular_Fam().length() == 0){
                        paciente.setTelParticular_Fam(" ");
                    }
                    if(paciente.getDirPersResp() == null || paciente.getDirPersResp().length() == 0){
                        paciente.setDirPersResp(" ");
                    }
                		
                		 
                    // Guardamos el registro en Oracle.
                    if(!paciente.save(trx_ora)){
                        m_errormsg.append("Paciente not saved with WHERE=" + where + "\n");
                    }
                    
                    record_id = paciente.getEXME_Paciente_ID();
                    
                }else{
                    
                    log.severe("\t Record Not found.");
                }
                
                rs_pgs.close();
                stmt_pgs.close();
                rs_pgs = null;
                stmt_pgs = null;
                
                if(m_errormsg.length() <= 0 && record_id > 0){
	                //Actualizamos el estatus de la tabla e Interfaz para el registro actual.
	                Statement stmt_upd =  cnn_int.createStatement();
	                String sql_upd = "UPDATE \"PUB\".\"paciente-ora\" SET \"Actualizado\" = 1, \"Record_ID\" = "+ record_id +
	                ", FechaAct = '" + sdf2.format(new Date()) + "' WHERE " + where;
	                int updated = stmt_upd.executeUpdate(sql_upd);
	                if(updated > 0){
	                    
	                    total_rows++;
	                    log.log(Level.INFO, updated + " rows updated.");
	                    m_textmsg.append("RowUpdated=" + record_id + ".\n");
	                    
	                }else{
	                    m_errormsg.append("Paciente-Ora was not Updated. \n");
	                }
	                
	                stmt_upd.close();
	                stmt_upd=null;
	                
                }//Existen errores?
                else{
                    break;
                }
                
            }//rs.
            
            rs.close();
            stmt.close();
            rs = null;
            stmt = null;
            
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
            cnn_exp.close();
        }
        */
        return null;
    }

}
