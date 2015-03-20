package org.compiere.process;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.logging.Level;

import org.compiere.model.MEXMELookupInfo;
import org.compiere.model.MEXMEMedico;
import org.compiere.model.MEXMEPaciente;
import org.compiere.util.DB;
import org.adempiere.exceptions.DBException;

/**
 * Proceso de importaci&oacute;n de la tabla de Subtipos de Producto.<p>
 * Creado: 10/Mar/2005<p>
 * Modificado: $Date: 2006/10/11 17:12:08 $<p>
 * Por: $Author: vgarcia $<p>
 *
 * @author mrojas
 * @version $Revision: 1.2 $
 */
public class ImportCitas extends SvrProcess {

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
    public ImportCitas() {
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
            m_DateValue = DB.getTimestampForOrg(getCtx());
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
            sql = new StringBuffer ("DELETE I_EXME_CitaMedica "
                + "WHERE I_IsImported='Y'").append(clientCheck);
            no = DB.executeUpdate(sql.toString());
            log.info("Delete Old Impored =" + no);
        }

        //  Set Client, Org, IaActive, Created/Updated,     ProductType
        sql = new StringBuffer ("UPDATE I_EXME_CitaMedica "
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
        no = DB.executeUpdate(sql.toString());
        log.info("Reset=" + no);
              
        //  Set Exme_Paciente_ID
        sql = new StringBuffer ("UPDATE I_EXME_CitaMedica i "
            + "SET EXME_Paciente_ID = (SELECT EXME_Paciente_ID FROM EXME_Paciente p WHERE  p.value = i.numhist "
            + MEXMELookupInfo.addAccessLevelSQL(getCtx(), " ", MEXMEPaciente.Table_Name, "p")            
            + ") "
            + "WHERE I_IsImported<>'Y'");
        no = DB.executeUpdate(sql.toString());
        log.fine("Set Exme_Paciente_ID=" + no);

        //  Set Exme_Medico_ID
        /*sql = new StringBuffer ("UPDATE I_EXME_CitaMedica i "
            + "SET EXME_Medico_ID = (SELECT m.EXME_Medico_ID FROM EXME_Medico m WHERE m.AD_Client_ID=i.AD_Client_ID "
            + " AND m.AD_Org_ID in(i.AD_Org_ID,0)  AND m.Value = i.EXME_Medico_Value) "
            + "WHERE I_IsImported<>'Y'").append(clientCheck);*/
        
        sql = new StringBuffer ("UPDATE I_EXME_CitaMedica i "
                + "SET EXME_Medico_ID = (SELECT m.EXME_Medico_ID FROM EXME_Medico m WHERE "
                + "  m.Value = i.EXME_Medico_Value "
                + MEXMELookupInfo.addAccessLevelSQL(getCtx(), " ", MEXMEMedico.Table_Name, "m")                
                + ") "
                + "WHERE I_IsImported<>'Y'");
        
        no = DB.executeUpdate(sql.toString());
        log.fine("Set Exme_Medico_ID=" + no);

        //  Set EXME_CitaMedica_ID
        sql = new StringBuffer ("UPDATE I_EXME_CitaMedica i " +
        		"SET EXME_CITAMEDICA_ID = (SELECT EXME_CITAMEDICA_ID FROM EXME_CITAMEDICA c WHERE c.AD_Client_ID=i.AD_Client_ID " +
        		"AND c.AD_Org_ID in(i.AD_Org_ID,0)  AND c.EXME_PACIENTE_ID = i.EXME_PACIENTE_ID AND c.FECHAHRCITA=i.FECHAHRCITA AND i.EXME_MEDICO_ID=c.EXME_MEDICO_ID) " +
        		"WHERE I_IsImported<>'Y'").append(clientCheck);
        no = DB.executeUpdate(sql.toString());
        log.fine("Set EXME_CITAMEDICA_ID=" + no);

        
        //  Set Exme_Especialidad_ID
        sql = new StringBuffer ("UPDATE I_EXME_CitaMedica i "
            + "SET EXME_Especialidad_ID = (SELECT EXME_Especialidad_ID FROM EXME_Especialidad p WHERE p.AD_Client_ID=i.AD_Client_ID "
            + " AND p.AD_Org_ID in(i.AD_Org_ID,0)  AND p.Value = i.EXME_Especialidad_Value) "
            + "WHERE I_IsImported<>'Y'").append(clientCheck);
        no = DB.executeUpdate(sql.toString());
        log.fine("Set Exme_Medico_ID=" + no);

        //  Set Exme_MotivoCita_ID
        sql = new StringBuffer ("UPDATE I_EXME_CitaMedica i "
            + "SET EXME_MotivoCita_ID = (SELECT EXME_MotivoCita_ID FROM EXME_MotivoCita p WHERE p.AD_Client_ID=i.AD_Client_ID "
            + " AND p.AD_Org_ID in(i.AD_Org_ID,0)  AND p.Value = i.EXME_Motivo_Value) "
            + "WHERE I_IsImported<>'Y'").append(clientCheck);
        no = DB.executeUpdate(sql.toString());
        log.fine("Set Exme_Medico_ID=" + no);

//      -------------------------------------------------------------------
        int noInsert = 0;
        int noUpdate = 0;
        //int noInsertPO = 0;
        //int noUpdatePO = 0;

        //  Go through Records
        log.fine("start inserting/updating ...");
        sql = new StringBuffer ("SELECT I_EXME_CitaMedica_ID, EXME_CitaMedica_ID "
            + "FROM I_EXME_CitaMedica WHERE I_IsImported='N'").append(clientCheck);
        Connection conn = DB.createConnection(false, Connection.TRANSACTION_READ_COMMITTED);
        try
        {
            //  Insertar subtipo de producto a partir de la importacion
            PreparedStatement pstmt_insertCitaMedica = conn.prepareStatement
                ("INSERT INTO EXME_CitaMedica (EXME_CitaMedica_ID,"
                + "AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,"
                + "NAME,exme_paciente_id,exme_medico_id,fechahrcita,observaciones,duracion,edad,exme_especialidad_id,exme_motivocita_id,confirmada,estatus,citade)  "
                + "SELECT ?,"
                + "AD_Client_ID,AD_Org_ID,'Y',SysDate,CreatedBy,SysDate,UpdatedBy,"
                + "TO_CHAR(I_EXME_CitaMedica_ID),exme_paciente_id,exme_medico_id,fechahrcita,observaciones,duracion,edad,exme_especialidad_id,exme_motivocita_id,confirmada,estatus,EXME_TipoCita_Value "
                + "FROM I_EXME_CitaMedica "
                + "WHERE I_EXME_CitaMedica_ID=?");
            
            //  Actualizar subtipo de producto a partir de la importacion
            PreparedStatement pstmt_updateCitaMedica = conn.prepareStatement
                ("UPDATE EXME_CitaMedica SET("
                + "AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,"
                + "NAME,exme_paciente_id,exme_medico_id,fechahrcita,observaciones,duracion,edad,exme_especialidad_id,exme_motivocita_id,confirmada,estatus,citade )= "
                + "(SELECT "
                + "AD_Client_ID,AD_Org_ID,'Y',SysDate,CreatedBy,SysDate,UpdatedBy,"
                + "NAME,exme_paciente_id,exme_medico_id,fechahrcita,observaciones,duracion,edad,exme_especialidad_id,exme_motivocita_id,confirmada,estatus,EXME_TipoCita_Value  "
                + "FROM I_EXME_CitaMedica "
                + "WHERE I_EXME_CitaMedica_ID=?) WHERE EXME_CitaMedica_ID = ?");


            //  Set Imported = Y  a la tabla temporal
            PreparedStatement pstmt_setImported = conn.prepareStatement
                ("UPDATE I_EXME_CitaMedica SET I_IsImported='Y', EXME_CitaMedica_ID=?, "
                + "Updated=SysDate, Processed='Y' WHERE I_EXME_CitaMedica_ID=?");

            //
            PreparedStatement pstmt = DB.prepareStatement(sql.toString(), null);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next())
            {
                int I_EXME_CitaMedica_ID = rs.getInt(1);
                int EXME_CitaMedica_ID = rs.getInt(2);
                boolean newCitaMedica = EXME_CitaMedica_ID == 0;
                log.fine("I_EXME_CitaMedica_ID=" + I_EXME_CitaMedica_ID + ", EXME_CitaMedica_ID=" + EXME_CitaMedica_ID);

                    try
                    {
                    	if(newCitaMedica){
                    		EXME_CitaMedica_ID = DB.getNextID(m_AD_Client_ID, "EXME_CitaMedica", null);
                            if (EXME_CitaMedica_ID <= 0)
                                throw new DBException("No Next ID (" + EXME_CitaMedica_ID + ")");
                            pstmt_insertCitaMedica.setInt(1, EXME_CitaMedica_ID);
                            pstmt_insertCitaMedica.setInt(2, I_EXME_CitaMedica_ID);
                            //
                            no = pstmt_insertCitaMedica.executeUpdate();
                            log.finer("Insert Citas = " + no);
                            noInsert++;
                    	}
                    	else{
                    		pstmt_updateCitaMedica.setInt(1, I_EXME_CitaMedica_ID);
                    		pstmt_updateCitaMedica.setInt(2, EXME_CitaMedica_ID);
                            //
                            no = pstmt_updateCitaMedica.executeUpdate();
                            log.finer("Update Citas = " + no);
                            noUpdate++;
                    	}
		
                        
                    }
                    catch (Exception ex)
                    {
                        log.warning("Insertando Citas - " + ex.toString());
                        sql = new StringBuffer ("UPDATE I_EXME_CitaMedica i "
                            + "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||")
                            .append(DB.TO_STRING("Insertando Cita Medica: " + ex.toString()))
                            .append("WHERE I_Exme_CitaMedica_ID=").append(I_EXME_CitaMedica_ID);
                        DB.executeUpdate(sql.toString());
                        continue;
                    }

                    //  Update I_EXME_Diagnostico
                    pstmt_setImported.setInt(1, EXME_CitaMedica_ID);
                    pstmt_setImported.setInt(2, I_EXME_CitaMedica_ID);
                    no = pstmt_setImported.executeUpdate();
                    //
                    conn.commit();
                }
            rs.close();
            pstmt.close();
	    rs=null;
	    pstmt=null;

            //
            pstmt_insertCitaMedica.close();
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
            }
            catch (SQLException ex)
            {
            }
            log.log(Level.SEVERE, "doIt", e);
            throw new Exception ("doIt", e);
        }
        finally
        {
            if (conn != null)
                conn.close();
            conn = null;
        }

        //  Set Error to indicator to not imported
        sql = new StringBuffer ("UPDATE I_Exme_CitaMedica "
            + "SET I_IsImported='N', Updated=SysDate "
            + "WHERE I_IsImported<>'Y'").append(clientCheck);
        no = DB.executeUpdate(sql.toString());
        addLog (0, null, new BigDecimal (no), "@Errors@");
        addLog (0, null, new BigDecimal (noInsert), "@M_Citas_ID@: @Inserted@");
        addLog (0, null, new BigDecimal (noUpdate), "@M_Citas_ID@: @Updated@");
        return "";

    }

}
