package org.compiere.process;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.logging.Level;

import org.adempiere.exceptions.DBException;
import org.compiere.model.MEXMELookupInfo;
import org.compiere.model.MEXMEPaciente;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;

/**
 * Proceso de importaci&oacute;n de la tabla de pacientes_ts de medsys3.<p>
 * Creado: 2/Ago/2006<p>
 * 
 * @author Valent&iacute;n
 * @version $Revision: 1.1 $
 */
public class ImportPacienteTS extends SvrProcess {

	private static CLogger		s_log = CLogger.getCLogger (ImportPacienteTS.class);
    /** Client to be imported to        */
    private int             m_AD_Client_ID = 0;
    /** Delete old Imported             */
    private boolean         m_deleteOldImported = false;

    /** Organization to be imported to  */
    private int             m_AD_Org_ID = 0;
    /** Effective                       */
    private Timestamp       m_DateValue = null;

    /**
     * Constructor por defecto.
     */
    public ImportPacienteTS() {
        super();
    }
    /**
     * Preparar: obtener par&aacute;metros
     */
    protected void prepare() {
        ProcessInfoParameter[] para = getParameter();
        for (int i = 0; i < para.length; i++)
        {
            String name = para[i].getParameterName();
            if (name.equals("AD_Client_ID"))
                m_AD_Client_ID = ((BigDecimal)para[i].getParameter()).intValue();
            else if (name.equals("AD_Org_ID"))
				m_AD_Org_ID = ((BigDecimal)para[i].getParameter()).intValue();
            else if (name.equals("DeleteOldImported"))
                m_deleteOldImported = "Y".equals(para[i].getParameter());
            else
                log.log(Level.SEVERE, "Unknown Parameter: " + name);
        }
        if (m_DateValue == null)
            m_DateValue = DB.getTimestampForOrg(Env.getCtx());
    }

    /**
     * Corre el proceso.
     * @return Un mensaje de estado
     * @throws Exception
     */
    protected String doIt() throws Exception {

        StringBuffer sql = null;
        int no = 0;
        String clientCheck = " AND AD_Client_ID=" + m_AD_Client_ID;

        //  ****    Prepare ****
        //  Delete Old Imported
        if (m_deleteOldImported)
        {
            sql = new StringBuffer ("DELETE I_EXME_Paciente_TS "
                + "WHERE I_IsImported='Y'").append(clientCheck);
            no = DB.executeUpdate(sql.toString(), null);
            log.info("Delete Old Impored =" + no);
        }

        //  Set Client, Org, IaActive, Created/Updated,
        sql = new StringBuffer ("UPDATE I_EXME_Paciente_TS "
            + "SET AD_Client_ID = COALESCE (AD_Client_ID, ").append(m_AD_Client_ID).append("),"
            + " AD_Org_ID = COALESCE (AD_Org_ID, ").append(m_AD_Org_ID).append("),"
            + " IsActive = COALESCE (IsActive, 'Y'),"
            + " Created = COALESCE (Created, SysDate),"
            + " CreatedBy = COALESCE (CreatedBy, 0),"
            + " Updated = COALESCE (Updated, SysDate),"
            + " UpdatedBy = COALESCE (UpdatedBy, 0),"
            + " I_ErrorMsg = NULL,"
            + " I_IsImported = 'N' "
            + "WHERE I_IsImported<>'Y' OR I_IsImported IS NULL");
        no = DB.executeUpdate(sql.toString(), null);
        log.info("Reset=" + no);

        //  Set Exme_Paciente_ID
        sql = new StringBuffer ("UPDATE I_EXME_Paciente_TS "
            + "SET EXME_Paciente_ID = (SELECT EXME_Paciente_ID FROM EXME_Paciente WHERE "
            + " EXME_Paciente.value = I_EXME_Paciente_TS.numhist "
            + MEXMELookupInfo.addAccessLevelSQL(getCtx(), " ", MEXMEPaciente.Table_Name)                
            + ") "
            + "WHERE I_EXME_Paciente_TS.I_IsImported<>'Y'");
        no = DB.executeUpdate(sql.toString(), null);
        log.fine("Set Exme_Paciente_ID=" + no);


	//  Set Exme_Nacionalidad_ID
			sql = new StringBuffer ("UPDATE I_EXME_Paciente_TS "
				+ "SET EXME_Nacionalidad_ID = (SELECT EXME_Nacionalidad_ID FROM EXME_Nacionalidad WHERE EXME_Nacionalidad.AD_Client_ID=I_EXME_Paciente_TS.AD_Client_ID "
				+ " AND EXME_Nacionalidad.Value = I_EXME_Paciente_TS.EXME_Nacionalidad_Value) "
				+ "WHERE I_EXME_Paciente_TS.I_IsImported<>'Y'");
			no = DB.executeUpdate(sql.toString(), null);
			log.fine("Set EXME_Nacionalidad_ID=" + no);

	//  Set EXME_Escolaridad_ID
			sql = new StringBuffer ("UPDATE I_EXME_Paciente_TS "
				+ "SET EXME_Escolaridad_ID = (SELECT EXME_Escolaridad_ID FROM EXME_Escolaridad WHERE EXME_Escolaridad.AD_Client_ID=I_EXME_Paciente_TS.AD_Client_ID "
				+ " AND EXME_Escolaridad.Value = I_EXME_Paciente_TS.EXME_Escolaridad_Value) "
				+ "WHERE I_EXME_Paciente_TS.I_IsImported<>'Y'");
			no = DB.executeUpdate(sql.toString(), null);
			log.fine("Set EXME_Escolaridad_ID=" + no);


	//  Set EXME_Institucion_ID
			sql = new StringBuffer ("UPDATE I_EXME_Paciente_TS "
				+ "SET EXME_Institucion_ID = (SELECT EXME_Institucion_ID FROM EXME_Institucion WHERE EXME_Institucion.AD_Client_ID=I_EXME_Paciente_TS.AD_Client_ID "
				+ " AND EXME_Institucion.Value = I_EXME_Paciente_TS.EXME_Institucion_Value) "
				+ "WHERE I_EXME_Paciente_TS.I_IsImported<>'Y'");
			no = DB.executeUpdate(sql.toString(), null);
			log.fine("Set EXME_Institucion_ID=" + no);

	//  Set EXME_Referencia_ID
			sql = new StringBuffer ("UPDATE I_EXME_Paciente_TS "
				+ "SET EXME_Referencia_ID = (SELECT EXME_Referencia_ID FROM EXME_Referencia WHERE EXME_Referencia.AD_Client_ID=I_EXME_Paciente_TS.AD_Client_ID "
				+ " AND EXME_Referencia.Value = I_EXME_Paciente_TS.EXME_Referencia_Value) "
				+ "WHERE I_EXME_Paciente_TS.I_IsImported<>'Y'");
			no = DB.executeUpdate(sql.toString(), null);
			log.fine("Set EXME_Referencia_ID=" + no);

	//  Set EXME_Referencia_Int_ID
			sql = new StringBuffer ("UPDATE I_EXME_Paciente_TS "
				+ "SET EXME_Referencia_Int_ID = (SELECT EXME_Referencia_Int_ID FROM EXME_Referencia_Int WHERE EXME_Referencia_Int.AD_Client_ID=I_EXME_Paciente_TS.AD_Client_ID "
				+ " AND EXME_Referencia_Int.Value = I_EXME_Paciente_TS.EXME_Referencia_Int_Value) "
				+ "WHERE I_EXME_Paciente_TS.I_IsImported<>'Y'");
			no = DB.executeUpdate(sql.toString(), null);
			log.fine("Set EXME_Referencia_Int_ID=" + no);

	//  Set EXME_Especialidad_ID
			sql = new StringBuffer ("UPDATE I_EXME_Paciente_TS "
				+ "SET EXME_Especialidad_TS_ID = (SELECT EXME_Especialidad_TS_ID FROM EXME_Especialidad_TS WHERE EXME_Especialidad_TS.AD_Client_ID=I_EXME_Paciente_TS.AD_Client_ID "
				+ " AND EXME_Especialidad_TS.Value = I_EXME_Paciente_TS.EXME_Especialidad_Value) "
				+ "WHERE I_EXME_Paciente_TS.I_IsImported<>'Y'");
			no = DB.executeUpdate(sql.toString(), null);
			log.fine("Set EXME_Especialidad_TS_ID=" + no);

	//  Set EXME_DELEGACION_T_RESP_ID
			sql = new StringBuffer ("UPDATE I_EXME_Paciente_TS "
				+ "SET EXME_DELEGACION_T_RESP_ID = (SELECT EXME_Delegacion_ID FROM EXME_Delegacion WHERE EXME_Delegacion.AD_Client_ID=I_EXME_Paciente_TS.AD_Client_ID "
				+ " AND EXME_Delegacion.Value = I_EXME_Paciente_TS.EXME_Delegacion_T_Resp_Value) "
				+ "WHERE I_EXME_Paciente_TS.I_IsImported<>'Y'");
			no = DB.executeUpdate(sql.toString(), null);
			log.fine("Set EXME_DELEGACION_T_RESP_ID=" + no);

	//  Set EXME_DELEGACION_P_PROV_ID
			sql = new StringBuffer ("UPDATE I_EXME_Paciente_TS "
				+ "SET EXME_DELEGACION_P_PROV_ID = (SELECT EXME_Delegacion_ID FROM EXME_Delegacion WHERE EXME_Delegacion.AD_Client_ID=I_EXME_Paciente_TS.AD_Client_ID "
				+ " AND EXME_Delegacion.Value = I_EXME_Paciente_TS.EXME_Delegacion_P_Prov_Value) "
				+ "WHERE I_EXME_Paciente_TS.I_IsImported<>'Y'");
			no = DB.executeUpdate(sql.toString(), null);
			log.fine("Set EXME_DELEGACION_P_PROV_ID=" + no);

	//  Set EXME_DELEGACION_P_TRAB_ID
			sql = new StringBuffer ("UPDATE I_EXME_Paciente_TS "
				+ "SET EXME_DELEGACION_P_TRAB_ID = (SELECT EXME_Delegacion_ID FROM EXME_Delegacion WHERE EXME_Delegacion.AD_Client_ID=I_EXME_Paciente_TS.AD_Client_ID "
				+ " AND EXME_Delegacion.Value = I_EXME_Paciente_TS.EXME_Delegacion_P_Trab_Value) "
				+ "WHERE I_EXME_Paciente_TS.I_IsImported<>'Y'");
			no = DB.executeUpdate(sql.toString(), null);
			log.fine("Set EXME_DELEGACION_P_TRAB_ID=" + no);

	//  Set EXME_DELEGACION_D_RESP_ID
			sql = new StringBuffer ("UPDATE I_EXME_Paciente_TS "
				+ "SET EXME_DELEGACION_D_RESP_ID = (SELECT EXME_Delegacion_ID FROM EXME_Delegacion WHERE EXME_Delegacion.AD_Client_ID=I_EXME_Paciente_TS.AD_Client_ID "
				+ " AND EXME_Delegacion.Value = I_EXME_Paciente_TS.EXME_Delegacion_D_Resp_Value) "
				+ "WHERE I_EXME_Paciente_TS.I_IsImported<>'Y'");
			no = DB.executeUpdate(sql.toString(), null);
			log.fine("Set EXME_DELEGACION_D_RESP_ID=" + no);

	//  Set EXME_DELEGACION_PERM_ID
			sql = new StringBuffer ("UPDATE I_EXME_Paciente_TS "
				+ "SET EXME_DELEGACION_PERM_ID = (SELECT EXME_Delegacion_ID FROM EXME_Delegacion WHERE EXME_Delegacion.AD_Client_ID=I_EXME_Paciente_TS.AD_Client_ID "
				+ " AND EXME_Delegacion.Value = I_EXME_Paciente_TS.EXME_Delegacion_Perm_Value) "
				+ "WHERE I_EXME_Paciente_TS.I_IsImported<>'Y'");
			no = DB.executeUpdate(sql.toString(), null);
			log.fine("Set EXME_DELEGACION_PERM_ID=" + no);

	//  Set EXME_OCUPACION_RESP_ID
			sql = new StringBuffer ("UPDATE I_EXME_Paciente_TS "
				+ "SET EXME_OCUPACION_RESP_ID = (SELECT EXME_Ocupacion_ID FROM EXME_Ocupacion WHERE EXME_Ocupacion.AD_Client_ID=I_EXME_Paciente_TS.AD_Client_ID "
				+ " AND EXME_Ocupacion.Value = I_EXME_Paciente_TS.EXME_Ocupacion_Resp_Value) "
				+ "WHERE I_EXME_Paciente_TS.I_IsImported<>'Y'");
			no = DB.executeUpdate(sql.toString(), null);
			log.fine("Set EXME_OCUPACION_RESP_ID=" + no);

	//  Set EXME_PARENTESCO_RESP_ID
			sql = new StringBuffer ("UPDATE I_EXME_Paciente_TS "
				+ "SET EXME_PARENTESCO_RESP_ID = (SELECT EXME_Parentesco_ID FROM EXME_Parentesco WHERE EXME_Parentesco.AD_Client_ID=I_EXME_Paciente_TS.AD_Client_ID "
				+ " AND EXME_Parentesco.Value = I_EXME_Paciente_TS.EXME_Parentesco_Resp_Value) "
				+ "WHERE I_EXME_Paciente_TS.I_IsImported<>'Y'");
			no = DB.executeUpdate(sql.toString(), null);
			log.fine("Set EXME_PARENTESCO_RESP_ID=" + no);


//      -------------------------------------------------------------------
        int noInsert = 0;
        int noUpdate = 0;
       // int noInsertPO = 0;
        //int noUpdatePO = 0;

        //  Obtenemos todos los id's de los registros de la tabla I_EXME_Paciente_TS
        log.fine("start inserting/updating ...");
        sql = new StringBuffer ("SELECT I_EXME_Paciente_TS_ID, EXME_PACIENTE_ID "
            + "FROM I_EXME_Paciente_TS WHERE I_IsImported='N'");
        
        
        Connection conn = DB.createConnection(false, Connection.TRANSACTION_READ_COMMITTED);
        
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try
        {

        	String insertPacienteTs = "INSERT INTO EXME_PACIENTE_TS " +
        			"(EXME_Paciente_TS_ID,AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy," +
        			"EXME_PACIENTE_ID,EXME_NACIONALIDAD_ID ,EXME_ESCOLARIDAD_ID ," +
        			"EXME_INSTITUCION_ID,LISTAINSTITUCION,EXME_OTRA_INST ,EXME_REFERENCIA_ID ," +
        			"EXME_REFERENCIA_INT_ID ,EXME_ESPECIALIDAD_TS_ID ,DIAGNOSTICO_ING ,DIAGNOSTICO_EGR ," +
        			"PADRE_VIVE ,MADRE_VIVE ,CONYUGE_VIVE ,C_LOCATION_T_RESP_ID,TEL_TRAB_RESP ," +
        			"EXME_DELEGACION_T_RESP_ID,C_LOCATION_P_PROV_ID ,TEL_PAC_PROV ,EXME_DELEGACION_P_PROV_ID ," +
        			"C_LOCATION_P_TRAB_ID ,TEL_PAC_TRAB ,EXME_DELEGACION_P_TRAB_ID ,C_LOCATION_D_RESP_ID ," +
        			"TEL_DOM_RESP ,EXME_DELEGACION_D_RESP_ID ,NOM_PADRE ,NOM_MADRE," +
        			"NOM_CONYUGE ,PADRE_DESCO ,MADRE_DESCO ,CONYUGE_DESCO ,C_LOCATION_PERM_ID ," +
        			"TEL_PERM ,EXME_DELEGACION_PERM_ID,NOMBRE_RESP ,EXME_OCUPACION_RESP_ID, EXME_PARENTESCO_RESP_ID) " +
        			"SELECT ?," + //para el EXME_Paciente_TS_ID
        			"AD_Client_ID,AD_Org_ID,'Y',SYSDATE,CreatedBy,SYSDATE,UpdatedBy," +
        			"EXME_PACIENTE_ID,EXME_NACIONALIDAD_ID ,EXME_ESCOLARIDAD_ID ,EXME_INSTITUCION_ID," +
        			"LISTAINSTITUCION,EXME_OTRA_INST ,EXME_REFERENCIA_ID ,EXME_REFERENCIA_INT_ID ," +
        			"EXME_ESPECIALIDAD_TS_ID ,DIAGNOSTICO_ING ,DIAGNOSTICO_EGR ,PADRE_VIVE ,MADRE_VIVE ," +
        			"CONYUGE_VIVE ," +
        			"?, " + //C_LOCATION_T_RESP_ID
        			"TEL_TRAB_RESP ,EXME_DELEGACION_T_RESP_ID," +
        			"?," + //C_LOCATION_P_PROV_ID
        			"TEL_PAC_PROV ,EXME_DELEGACION_P_PROV_ID ," +
        			"?," + //C_LOCATION_P_TRAB_ID
        			"TEL_PAC_TRAB ,EXME_DELEGACION_P_TRAB_ID ," +
        			"?," + //C_LOCATION_D_RESP_ID 
        			"TEL_DOM_RESP ,EXME_DELEGACION_D_RESP_ID ,NOM_PADRE ,NOM_MADRE,NOM_CONYUGE ,PADRE_DESCO ," +
        			"MADRE_DESCO ,CONYUGE_DESCO ," +
        			"?," + //C_LOCATION_PERM_ID 
        			"TEL_PERM ,EXME_DELEGACION_PERM_ID," +
        			"NOMBRE_RESP ,EXME_OCUPACION_RESP_ID, EXME_PARENTESCO_RESP_ID FROM I_EXME_PACIENTE_TS " +
        			"WHERE I_EXME_Paciente_TS_ID=?";
        	
        	String updatePacienteTs = "UPDATE EXME_PACIENTE_TS " +
			"set(AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy," +
			"EXME_PACIENTE_ID,EXME_NACIONALIDAD_ID ,EXME_ESCOLARIDAD_ID ," +
			"EXME_INSTITUCION_ID,LISTAINSTITUCION,EXME_OTRA_INST ,EXME_REFERENCIA_ID ," +
			"EXME_REFERENCIA_INT_ID ,EXME_ESPECIALIDAD_TS_ID ,DIAGNOSTICO_ING ,DIAGNOSTICO_EGR ," +
			"PADRE_VIVE ,MADRE_VIVE ,CONYUGE_VIVE ,TEL_TRAB_RESP ," +
			"EXME_DELEGACION_T_RESP_ID ,TEL_PAC_PROV ,EXME_DELEGACION_P_PROV_ID ," +
			" TEL_PAC_TRAB ,EXME_DELEGACION_P_TRAB_ID ," +
			"TEL_DOM_RESP ,EXME_DELEGACION_D_RESP_ID ,NOM_PADRE ,NOM_MADRE," +
			"NOM_CONYUGE ,PADRE_DESCO ,MADRE_DESCO ,CONYUGE_DESCO ," +
			"TEL_PERM ,EXME_DELEGACION_PERM_ID,NOMBRE_RESP ,EXME_OCUPACION_RESP_ID, EXME_PARENTESCO_RESP_ID)= " +
			"(SELECT " + //para el EXME_Paciente_TS_ID
			"AD_Client_ID,AD_Org_ID,'Y',SYSDATE,CreatedBy,SYSDATE,UpdatedBy," +
			"EXME_PACIENTE_ID,EXME_NACIONALIDAD_ID ,EXME_ESCOLARIDAD_ID ,EXME_INSTITUCION_ID," +
			"LISTAINSTITUCION,EXME_OTRA_INST ,EXME_REFERENCIA_ID ,EXME_REFERENCIA_INT_ID ," +
			"EXME_ESPECIALIDAD_TS_ID ,DIAGNOSTICO_ING ,DIAGNOSTICO_EGR ,PADRE_VIVE ,MADRE_VIVE ," +
			"CONYUGE_VIVE ," +
			"TEL_TRAB_RESP ,EXME_DELEGACION_T_RESP_ID," +
			"TEL_PAC_PROV ,EXME_DELEGACION_P_PROV_ID ," +
			"TEL_PAC_TRAB ,EXME_DELEGACION_P_TRAB_ID ," +
			"TEL_DOM_RESP ,EXME_DELEGACION_D_RESP_ID ,NOM_PADRE ,NOM_MADRE,NOM_CONYUGE ,PADRE_DESCO ," +
			"MADRE_DESCO ,CONYUGE_DESCO ," +
			"TEL_PERM ,EXME_DELEGACION_PERM_ID," +
			"NOMBRE_RESP ,EXME_OCUPACION_RESP_ID, EXME_PARENTESCO_RESP_ID FROM I_EXME_PACIENTE_TS " +
			"WHERE I_EXME_Paciente_TS_ID=?) WHERE EXME_PACIENTE_ID=?";
        	
        	// Perteneciente al paciente que se insertara
            PreparedStatement pstmt_PacienteTS = conn.prepareStatement(insertPacienteTs);
            PreparedStatement pstmtUpdate_PacienteTS = conn.prepareStatement(updatePacienteTs);

            //  Set Imported = Y  a la tabla temporal
            PreparedStatement pstmt_setImported = conn.prepareStatement
                ("UPDATE I_EXME_Paciente_TS SET I_IsImported='Y', EXME_Paciente_TS_ID=?, "
                + "Updated=SysDate, Processed='Y' WHERE I_EXME_Paciente_TS_ID=?");

            //Ejecutamos el query que obtiene todos los ID de los registros de I_EXME_Paciente_TS
            pstmt = DB.prepareStatement(sql.toString(), null);
            rs = pstmt.executeQuery();
            
            int I_EXME_Paciente_TS_ID = 0;
            int EXME_Paciente_TS_ID = 0;
            long ids_regiones[];
            boolean newPacienteTS=false;
            
            while (rs.next())
            {
                I_EXME_Paciente_TS_ID = rs.getInt(1);
                EXME_Paciente_TS_ID = rs.getInt(2);
                newPacienteTS = EXME_Paciente_TS_ID == 0;
                log.fine("EXME_Paciente_TS_ID=" + I_EXME_Paciente_TS_ID + ", EXME_Paciente_TS_ID=" + EXME_Paciente_TS_ID);

                    try
                    {
                    	if(newPacienteTS){
                    		EXME_Paciente_TS_ID = DB.getNextID(m_AD_Client_ID, "EXME_Paciente_TS", null);
                            if (EXME_Paciente_TS_ID <= 0)
                                throw new DBException("No Next ID (" + EXME_Paciente_TS_ID + ")");
                            
                            /* creamos los registros de las regiones y obtenemos la 
                             * lista de ids correspondientes*/
                            ids_regiones = creaLocationes(I_EXME_Paciente_TS_ID);
                            
                            pstmt_PacienteTS.setLong(1,EXME_Paciente_TS_ID);
                            //se asignan al query los ids de las locaciones
                            pstmt_PacienteTS.setLong(2, ids_regiones[4]);//C_LOCATION_T_RESP_ID
                            pstmt_PacienteTS.setLong(3, ids_regiones[1]);//C_LOCATION_P_PROV_ID
                            pstmt_PacienteTS.setLong(4, ids_regiones[2]);//C_LOCATION_P_TRAB_ID
                            pstmt_PacienteTS.setLong(5, ids_regiones[0]);//C_LOCATION_D_RESP_ID 
                            pstmt_PacienteTS.setLong(6, ids_regiones[3]);//C_LOCATION_PERM_ID 
                            //asigamos el id del paciente
                            pstmt_PacienteTS.setLong(7, I_EXME_Paciente_TS_ID);
                            
                            no = pstmt_PacienteTS.executeUpdate();
                            log.finer("Insert Paciente TS = " + no);
                            noInsert++;
                    	}
                    	else{
                            
                            /*actualizamos los locations*/
                    		actualizaLocations( EXME_Paciente_TS_ID, I_EXME_Paciente_TS_ID);
                            
                    		pstmtUpdate_PacienteTS.setLong(1, I_EXME_Paciente_TS_ID);
                    		pstmtUpdate_PacienteTS.setLong(2,EXME_Paciente_TS_ID);
                            
                            no = pstmtUpdate_PacienteTS.executeUpdate();
                            log.finer("Insert Paciente TS = " + no);
                            noUpdate++;
                    	}
                    }
                    catch (Exception ex)
                    {
                        log.warning("Registro de Pacientes_TS, No Importado " + ex.toString());
                        sql = new StringBuffer ("UPDATE I_EXME_Paciente_TS i "
                            + "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||")
                            .append(DB.TO_STRING("Insertando Paciente_TS: " + ex.toString()))
                            .append("WHERE I_Exme_Paciente_TS_ID=").append(I_EXME_Paciente_TS_ID);
                        DB.executeUpdate(sql.toString(), null);
                        continue;
                    }
                    //  Update I_EXME_Paciente_TS
                    pstmt_setImported.setInt(1, EXME_Paciente_TS_ID);
                    pstmt_setImported.setInt(2, I_EXME_Paciente_TS_ID);
                    no = pstmt_setImported.executeUpdate();
                    //
                    conn.commit();
                }
            rs.close();
            pstmt.close();

            //
            pstmt_PacienteTS.close();
            pstmt_setImported.close();
            //
            conn.close();
            conn = null;
        }
        catch (SQLException e)
        {
            try
            {
                if (conn != null)
                    conn.close();
                conn = null;
                if(rs != null)
                    rs.close();
                if(pstmt != null)
                    pstmt.close();
            }
            catch (SQLException ex)
            {
            }
            rs=null;
            pstmt=null;
            log.log(Level.SEVERE, "doIt", e);
            throw new Exception ("doIt", e);
        }
        finally
        {
            if (conn != null)
                conn.close();
            conn = null;
            if(rs != null)
                rs.close();
            if(pstmt != null)
                pstmt.close();
            rs=null;
            pstmt=null;
        }

        //  Set Error to indicator to not imported
        sql = new StringBuffer ("UPDATE I_Exme_Paciente_TS "
            + "SET I_IsImported='N', Updated=SysDate "
            + "WHERE I_IsImported <>'Y'");
        no = DB.executeUpdate(sql.toString(), null);
        addLog (0, null, new BigDecimal (no), "@Errors@");
        addLog (0, null, new BigDecimal (noInsert), "@I_EXME_Paciente_TS@: @Inserted@");
        addLog (0, null, new BigDecimal (noUpdate), "@EXME_Paciente_TS_ID@: @Updated@");
        return "";

    }
    
    /**
     * Obtiene el id de la nueva locacion para el paciente
     * @return
     */
    public long getIDRegion(String region){
    	
    	//String clientCheck = " AND AD_Client_ID=" + m_AD_Client_ID;
    	long region_ID = 0;
    	
//      Set ESTADO
    	StringBuffer sql = new StringBuffer ("SELECT C_Region_ID FROM C_Region " +
    			"WHERE C_Region.Name = ? ");
        PreparedStatement pstmt = DB.prepareStatement(sql.toString(), null);
        
        
        ResultSet rs=null;
		try {
//			Asignamos los valores de la region
	        pstmt.setString(1,region);
			
			rs = pstmt.executeQuery();
			while (rs.next())
	        	region_ID = rs.getInt(1);
		} catch (SQLException e) {
			s_log.log(Level.SEVERE, e.getMessage(), e);
		}
        finally
        {
            try{
            if(rs != null)
                rs.close();
            if(pstmt != null)
                pstmt.close();
            }catch (Exception e) {}
            rs=null;
            pstmt=null;
        }

		log.fine("Set Edo_Trab_Resp_ID=" + region_ID);
		
		return region_ID;
    }
    
    /**
     * Inserta los nuevos registros en la tabla C_Location para
     * un paciente de TS y regresa los Ids
     * @return
     */
    public long[] creaLocationes(long id_paciente_ts){
    	
    	String tipos[] ={"DOM_RESP",
    			"PAC_PROV",
    			"PAC_TRAB",
    			"PERM",
    			"TRAB_RESP"
    	}; 
    	
    	long IDs[] = new long[5];
    	//Para Obtener el consecutivo del registro
    	
    	//Obtenemos los values de las regiones del paciente
    	String regionesValues[] = getRegionesValues(id_paciente_ts);
    	long region_Id = 0;
    	String insertLocation="";
    	
    	for(int i=0; i<tipos.length ; i++){
    		//Obtenemos el Id de la region a partir del value
    		long location_Id = DB.getNextID(m_AD_Client_ID, "C_Location", null);
    		region_Id = getIDRegion(regionesValues[i]);
    		
    		insertLocation =  "INSERT INTO C_LOCATION " +
			"(AD_CLIENT_ID,AD_ORG_ID,ISACTIVE,CREATED,CREATEDBY,UPDATED,UPDATEDBY," +
			"C_LOCATION_ID,ADDRESS1,ADDRESS3,ADDRESS4,C_REGION_ID, POSTAL,C_COUNTRY_ID) " +
			
			"SELECT I_EXME_PACIENTE_TS.AD_CLIENT_ID,I_EXME_PACIENTE_TS.AD_ORG_ID, I_EXME_PACIENTE_TS.ISACTIVE, I_EXME_PACIENTE_TS.CREATED, " +
			"I_EXME_PACIENTE_TS.CREATEDBY, I_EXME_PACIENTE_TS.UPDATED, I_EXME_PACIENTE_TS.UPDATEDBY,?," +
			"I_EXME_PACIENTE_TS.CALLE_" + tipos[i] + "_VALUE," +
			"I_EXME_PACIENTE_TS.COL_" + tipos[i] + "_VALUE," +
			"I_EXME_PACIENTE_TS.MPIO_" + tipos[i] + "_VALUE,?," +
			"I_EXME_PACIENTE_TS.CP_" + 
			(tipos[i].equals("PERM")?"PROCEDENCIA":tipos[i])+ 
			"_VALUE, 247 " +
			"FROM I_EXME_PACIENTE_TS WHERE I_EXME_PACIENTE_TS.I_EXME_PACIENTE_TS_ID =?";
    		
    		PreparedStatement pstmt = DB.prepareStatement(insertLocation, null);
	
    		//Asignamos los valores
    		try {
    			pstmt.setLong(1,location_Id);
    			pstmt.setLong(2,region_Id);
    			pstmt.setLong(3,id_paciente_ts);
    			
    			/* Insertamos el registro y guardamos el Id del nuevo registro 
    			 * de C_Location, en el arreglo de Ids*/
    			pstmt.executeUpdate();
    			IDs[i] = location_Id;
                
                pstmt.close();
                pstmt = null;
    			
    		} catch (SQLException e) {
    			s_log.log(Level.SEVERE, e.getMessage(), e);
    		}
            try{
                if(pstmt!=null)
                    pstmt.close();
            }catch (Exception e) {}
            pstmt=null;
    	}  	
    	return IDs;
    }
    
    /**
     * Obtiene los values de la regiones del paciente
     * @return
     */
    public String[] getRegionesValues(long id_paciente_ts){
    	
    	//String clientCheck = " AND AD_Client_ID=" + m_AD_Client_ID;
    	String values[] = {"","","","",""};
    	
//      Set ESTADO
    	String sql = "SELECT I_EXME_PACIENTE_TS.EDO_DOM_RESP_VALUE, I_EXME_PACIENTE_TS.EDO_PAC_PROV_VALUE, " +
    			"I_EXME_PACIENTE_TS.EDO_PAC_TRAB_VALUE," +
		" I_EXME_PACIENTE_TS.EDO_PERM_VALUE, I_EXME_PACIENTE_TS.EDO_TRAB_RESP_VALUE" +
		" FROM I_EXME_PACIENTE_TS WHERE I_EXME_PACIENTE_TS.I_EXME_PACIENTE_TS_ID = ? AND " +
		" I_EXME_PACIENTE_TS.I_IsImported <>'Y'";
        PreparedStatement pstmt = DB.prepareStatement(sql, null);
        
        ResultSet rs=null;
		try {
//			Asignamos los valores de la region
	        pstmt.setLong(1,id_paciente_ts);
			
			rs = pstmt.executeQuery();
			while (rs.next()){
	        	values[0] = rs.getString(1);
				values[1] = rs.getString(2);
				values[2] = rs.getString(3);
				values[3] = rs.getString(4);
				values[4] = rs.getString(5);}
			
		} catch (SQLException e) {
			s_log.log(Level.SEVERE, e.getMessage(), e);
		}finally
        {
            try{
            if(rs != null)
                rs.close();
            if(pstmt != null)
                pstmt.close();
            }catch (Exception e) {}
            rs=null;
            pstmt=null;
		}

		log.fine("Set Edo_Trab_Resp_ID=" + values);
		
		return values;
    }
    
    /*
     * actualizaLocations : Metodo que actualiza la informacion de los Locations relacionados
     * con un paciente de trabajo social
     */
    public void actualizaLocations(int id_PacienteTs, int i_id_PacienteTs){
    	
    	// para obterner los ids de los locations
    	int ids[] = new int[5];
    	String sqlLocations = "select C_LOCATION_D_RESP_ID,C_LOCATION_P_PROV_ID,C_LOCATION_P_TRAB_ID," +
    			"C_LOCATION_PERM_ID,C_LOCATION_T_RESP_ID " +
    			"from EXME_PACIENTE_TS where EXME_PACIENTE_ID=?";
    	
    	PreparedStatement psLocations = DB.prepareStatement(sqlLocations,null);
    	ResultSet rsLocations= null;
		try {
			psLocations.setInt(1, id_PacienteTs);
			rsLocations = psLocations.executeQuery();
			
			//se llena el arreglo con los IDs Obtenidos
	    	while(rsLocations.next()){
	    		ids[0] = rsLocations.getInt(1);
	    		ids[1] = rsLocations.getInt(2);
	    		ids[2] = rsLocations.getInt(3);
	    		ids[3] = rsLocations.getInt(4);
	    		ids[4] = rsLocations.getInt(5);
	    	}
	    	
		} catch (SQLException e1) {
			s_log.log(Level.SEVERE, e1.getMessage(), e1);
		}finally
        {
            try{
            if(rsLocations != null)
                rsLocations.close();
            if(rsLocations != null)
                rsLocations.close();
            }catch (Exception e) {}
            rsLocations=null;
            rsLocations=null;
		}
    	
    	String tipos[] ={"DOM_RESP",
    			"PAC_PROV",
    			"PAC_TRAB",
    			"PERM",
    			"TRAB_RESP"
    	}; 
    	
    	//Obtenemos los values de las regiones del paciente
    	String regionesValues[] = getRegionesValues(i_id_PacienteTs);
    	long region_Id = 0;
    	String updateLocation="";
    	String newLocation = null;
    	PreparedStatement psnew = null;
    	
    	for(int i=0; i<tipos.length ; i++){
    		region_Id = getIDRegion(regionesValues[i]);
    		long location_Id =0;
    		if(ids[i]>0){
    			updateLocation =  "UPDATE C_LOCATION SET" +
    			"(AD_CLIENT_ID,AD_ORG_ID,ISACTIVE,CREATED,CREATEDBY,UPDATED,UPDATEDBY," +
    			"ADDRESS1,ADDRESS3,ADDRESS4,C_REGION_ID, POSTAL,C_COUNTRY_ID)= " +
    			
    			"(SELECT I_EXME_PACIENTE_TS.AD_CLIENT_ID,I_EXME_PACIENTE_TS.AD_ORG_ID, I_EXME_PACIENTE_TS.ISACTIVE, I_EXME_PACIENTE_TS.CREATED, " +
    			"I_EXME_PACIENTE_TS.CREATEDBY, I_EXME_PACIENTE_TS.UPDATED, I_EXME_PACIENTE_TS.UPDATEDBY," +
    			"I_EXME_PACIENTE_TS.CALLE_" + tipos[i] + "_VALUE," +
    			"I_EXME_PACIENTE_TS.COL_" + tipos[i] + "_VALUE," +
    			"I_EXME_PACIENTE_TS.MPIO_" + tipos[i] + "_VALUE,?," +
    			"I_EXME_PACIENTE_TS.CP_" + 
    			(tipos[i].equals("PERM")?"PROCEDENCIA":tipos[i])+ 
    			"_VALUE, 247 " +
    			"FROM I_EXME_PACIENTE_TS WHERE I_EXME_PACIENTE_TS.I_EXME_PACIENTE_TS_ID =?) " +
    			"WHERE C_LOCATION_ID=?";
    		}
    		else{
    			location_Id = DB.getNextID(m_AD_Client_ID, "C_Location", null);
    			updateLocation =  "INSERT INTO C_LOCATION " +
    			"(AD_CLIENT_ID,AD_ORG_ID,ISACTIVE,CREATED,CREATEDBY,UPDATED,UPDATEDBY," +
    			"C_LOCATION_ID,ADDRESS1,ADDRESS3,ADDRESS4,C_REGION_ID, POSTAL,C_COUNTRY_ID) " +
    			
    			"SELECT I_EXME_PACIENTE_TS.AD_CLIENT_ID,I_EXME_PACIENTE_TS.AD_ORG_ID, I_EXME_PACIENTE_TS.ISACTIVE, I_EXME_PACIENTE_TS.CREATED, " +
    			"I_EXME_PACIENTE_TS.CREATEDBY, I_EXME_PACIENTE_TS.UPDATED, I_EXME_PACIENTE_TS.UPDATEDBY,?," +
    			"I_EXME_PACIENTE_TS.CALLE_" + tipos[i] + "_VALUE," +
    			"I_EXME_PACIENTE_TS.COL_" + tipos[i] + "_VALUE," +
    			"I_EXME_PACIENTE_TS.MPIO_" + tipos[i] + "_VALUE,?," +
    			"I_EXME_PACIENTE_TS.CP_" + 
    			(tipos[i].equals("PERM")?"PROCEDENCIA":tipos[i])+ 
    			"_VALUE, 247 " +
    			"FROM I_EXME_PACIENTE_TS WHERE I_EXME_PACIENTE_TS.I_EXME_PACIENTE_TS_ID =?";
    		}
    		
    		PreparedStatement pstmt = DB.prepareStatement(updateLocation, null);
	
    		//Asignamos los valores
    		try {
    			if(ids[i]>0){
    				pstmt.setLong(1,region_Id);
        			pstmt.setLong(2,i_id_PacienteTs);
        			pstmt.setLong(3,ids[i]);
        			
        			/* Ejecutamos la actualizacion del registro*/
        			pstmt.executeUpdate();
        			
    			}
    			else{
    				pstmt.setLong(1,location_Id);
        			pstmt.setLong(2,region_Id);
        			pstmt.setLong(3,i_id_PacienteTs);
            			
        			if(tipos[i].equals("DOM_RESP"))
        				newLocation="UPDATE EXME_PACIENTE_TS SET C_LOCATION_D_RESP_ID = ? WHERE EXME_PACIENTE_ID=?";
        			else if(tipos[i].equals("PAC_PROV"))
        				newLocation="UPDATE EXME_PACIENTE_TS SET C_LOCATION_P_PROV_ID = ? WHERE EXME_PACIENTE_ID=?";
        			else if(tipos[i].equals("PAC_TRAB"))
        				newLocation="UPDATE EXME_PACIENTE_TS SET C_LOCATION_P_TRAB_ID = ? WHERE EXME_PACIENTE_ID=?";
        			else if(tipos[i].equals("PERM"))
        				newLocation="UPDATE EXME_PACIENTE_TS SET C_LOCATION_PERM_ID = ? WHERE EXME_PACIENTE_ID=?";
        			else if(tipos[i].equals("TRAB_RESP"))
        				newLocation="UPDATE EXME_PACIENTE_TS SET C_LOCATION_T_RESP_ID = ? WHERE EXME_PACIENTE_ID=?";
        			
        			psnew = DB.prepareStatement(newLocation,null);
        			psnew.setLong(1,location_Id);
        			psnew.setInt(2,id_PacienteTs);
        			
        			/* Ejecutamos la actualizacion del registro*/
        			if(pstmt.executeUpdate()>0)
        				psnew.executeUpdate();
    			}
    			
    		} catch (SQLException e) {
    			s_log.log(Level.SEVERE, e.getMessage(), e);
    		}
            try
            {
                if(pstmt != null)
                    pstmt.close();
                pstmt=null;
                if(psnew != null)
                    psnew.close();
                psnew=null;
            }catch (Exception e) {
                pstmt=null;
                psnew=null;
            }
    	}  	
    	
    }

}