package org.compiere.process;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.logging.Level;

import org.compiere.model.MEXMELookupInfo;
import org.compiere.model.MEXMEPaciente;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.adempiere.exceptions.DBException;

/**
 * Proceso de importaci&oacute;n de la tabla de Subtipos de Producto.<p>
 * Creado: 10/Mar/2005<p>
 * Modificado: $Date: 2006/02/09 23:31:32 $<p>
 * Por: $Author: gisela $<p>
 * 
 * @author mrojas
 * @version $Revision: 1.3 $
 */
public class ImportTraslados extends SvrProcess {
    
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
    public ImportTraslados() {
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
            sql = new StringBuffer ("DELETE I_EXME_Traslados "
                + "WHERE I_IsImported='Y'").append(clientCheck);
            no = DB.executeUpdate(sql.toString());
            log.info("Delete Old Impored =" + no);
        }

        //  Set Client, Org, IaActive, Created/Updated,
        sql = new StringBuffer ("UPDATE I_EXME_Traslado "
            + "SET AD_Client_ID = COALESCE (AD_Client_ID, ").append(m_AD_Client_ID).append("),"
            + " AD_Org_ID = COALESCE (AD_Org_ID, ").append(m_AD_Org_ID).append("),"
            + " IsActive = COALESCE (IsActive, 'Y'),"
            + " Created = COALESCE (Created, "+ DB.TO_DATE(DB.getTimestampForOrg(Env.getCtx())) + "),"
            + " CreatedBy = COALESCE (CreatedBy, 0),"
            + " Updated = COALESCE (Up"+ DB.TO_DATE(DB.getTimestampForOrg(Env.getCtx())) + ""+ DB.TO_DATE(DB.getTimestampForOrg(Env.getCtx())) + "),"
            + " UpdatedBy = COALESCE (UpdatedBy, 0),"
            + " I_ErrorMsg = NULL,"
            + " I_IsImported = 'N' "
            + "WHERE I_IsImported<>'Y' OR I_IsImported IS NULL");
        no = DB.executeUpdate(sql.toString());
        log.info("Reset=" + no);
        
        //  Set Exme_Paciente_ID
        sql = new StringBuffer ("UPDATE I_EXME_Traslado i "
            + "SET EXME_Paciente_ID = (SELECT EXME_Paciente_ID FROM EXME_Paciente p WHERE "
            + " p.Value = i.EXME_Paciente_value "
            + MEXMELookupInfo.addAccessLevelSQL(getCtx(), " ", MEXMEPaciente.Table_Name, "p")                
            + ") "
            + "WHERE I_IsImported<>'Y'");
        no = DB.executeUpdate(sql.toString());
        log.fine("Set Exme_Paciente_ID=" + no);

        //  Set Exme_Hist_Exp_ID
        sql = new StringBuffer ("UPDATE I_EXME_Traslado i "
            + "SET EXME_HIST_EXP_ID = (SELECT EXME_HIST_EXP_ID FROM EXME_HIST_EXP p WHERE "
            + " p.AD_Client_ID=i.AD_Client_ID  AND p.Expediente = i.Expediente ) "
            + "WHERE I_IsImported<>'Y'").append(clientCheck);
        no = DB.executeUpdate(sql.toString());
        log.fine("Set Exme_Hist_Exp_ID=" + no);
        
         //  Set Exme_Cama_ID
        sql = new StringBuffer ("UPDATE I_EXME_Traslado  i "
            + "SET EXME_Cama_ID = (SELECT EXME_Cama_ID FROM EXME_Cama p WHERE "
            + " p.AD_Client_ID=i.AD_Client_ID  AND p.Value = i.EXME_Cama_Value) "
            + "WHERE I_IsImported<>'Y'").append(clientCheck);
        no = DB.executeUpdate(sql.toString());
        log.fine("Set Exme_Cama_ID=" + no);
        
        //Set EXME_TRASLADO_ID (vgarcia)
        sql = new StringBuffer("Update I_EXME_TRASLADO i set EXME_TRASLADO_ID= " +
        		"(Select EXME_TRASLADO_ID from EXME_TRASLADO tras " +
        		"where tras.EXME_PACIENTE_ID=i.EXME_PACIENTE_ID) " +
        		"where i.I_ISIMPORTED<>'Y' and i.EXME_PACIENTE_ID is not null and i.EXME_PACIENTE_ID>0");
        no = DB.executeUpdate(sql.toString());
        log.fine("Set EXME_TRASLADO_ID=" + no);
        
 
      
        //-------------------------------------------------------------------
        int noInsert = 0;
        int noUpdate = 0;
        //int noInsertPO = 0;
        //int noUpdatePO = 0;

        //  Go through Records
        log.fine("start inserting/updating ...");
        sql = new StringBuffer ("SELECT I_EXME_Traslado_ID, EXME_Traslado_ID "
            + "FROM I_EXME_Traslado WHERE I_IsImported='N'").append(clientCheck);
        Connection conn = DB.createConnection(false, Connection.TRANSACTION_READ_COMMITTED);
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try
        {
            //  Insertar subtipo de producto a partir de la importacion
            PreparedStatement pstmt_insertTraslados = conn.prepareStatement
                ("INSERT INTO EXME_Traslado (EXME_Traslado_ID,"
                + "AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,UpdatedBy,"
                + "TSCama, diagnostico,exme_hist_exp_ID,fecha,lugar_tras,medico_resp,exme_paciente_id,observaciones,user_t_s,updated) "
                + "SELECT ?,"
                + "AD_Client_ID,AD_Org_ID,'Y',"+ DB.TO_DATE(DB.getTimestampForOrg(Env.getCtx())) + ",CreatedBy,UpdatedBy,"
                + "Exme_Cama_Value , diagnostico,exme_hist_exp_ID,fecha,lugar_tras,medico_resp,exme_paciente_id,observaciones,CreatedBy,updated "
                + "FROM I_EXME_Traslado "
                + "WHERE I_EXME_Traslado_ID=?");
            
            // Para actualizar un registro (vgarcia)
            PreparedStatement pstmt_updateTraslados = conn.prepareStatement
                ("Update EXME_Traslado set("
                + "AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,UpdatedBy,"
                + "TSCama, diagnostico,exme_hist_exp_ID,fecha,lugar_tras,medico_resp,exme_paciente_id,observaciones,user_t_s,updated)= "
                + "(SELECT "
                + "AD_Client_ID,AD_Org_ID,'Y',"+ DB.TO_DATE(DB.getTimestampForOrg(Env.getCtx())) + ",CreatedBy,UpdatedBy,"
                + "Exme_Cama_Value , diagnostico,exme_hist_exp_ID,fecha,lugar_tras,medico_resp,exme_paciente_id,observaciones,CreatedBy,updated "
                + "FROM I_EXME_Traslado "
                + "WHERE I_EXME_Traslado_ID=?) where EXME_Traslado_ID=?");

            //  Set Imported = Y  a la tabla temporal
            PreparedStatement pstmt_setImported = conn.prepareStatement
                ("UPDATE I_EXME_Traslado SET I_IsImported='Y', EXME_Traslado_ID=?, "
                + "Updated="+ DB.TO_DATE(DB.getTimestampForOrg(Env.getCtx())) + ", Processed='Y' WHERE I_EXME_Traslado_ID=?");

            //
            pstmt = DB.prepareStatement(sql.toString(), null);
            rs = pstmt.executeQuery();
            int I_EXME_Traslado_ID = 0;
            int EXME_Traslado_ID = 0;
            boolean newRegistro = false;
            
            while (rs.next())
            {
                I_EXME_Traslado_ID = rs.getInt(1);
                EXME_Traslado_ID = rs.getInt(2);
                newRegistro = EXME_Traslado_ID == 0;
                log.fine("I_EXME_Traslado_ID=" + I_EXME_Traslado_ID + ", EXME_Traslado_ID=" + EXME_Traslado_ID);

                    try
                    {
                    	if(newRegistro){
                    		EXME_Traslado_ID = DB.getNextID(m_AD_Client_ID, "EXME_Traslado", null);
                            if (EXME_Traslado_ID <= 0)
                                throw new DBException("No Next ID (" + EXME_Traslado_ID + ")");
                            pstmt_insertTraslados.setInt(1, EXME_Traslado_ID);
                            pstmt_insertTraslados.setInt(2, I_EXME_Traslado_ID);
                            //
                            no = pstmt_insertTraslados.executeUpdate();
                            log.finer("Insert Traslad = " + no);
                            noInsert++;
                    	}
                    	else{
                            pstmt_updateTraslados.setInt(1, I_EXME_Traslado_ID);
                            pstmt_updateTraslados.setInt(2, EXME_Traslado_ID);
                            //
                            no = pstmt_updateTraslados.executeUpdate();
                            log.finer("Update Traslad = " + no);
                            noUpdate++;
                    	}
                        
                    }
                    catch (Exception ex)
                    {
                        log.warning("Insertando Traslad - " + ex.toString());
                        sql = new StringBuffer ("UPDATE I_EXME_Traslado i "
                            + "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||")
                            .append(DB.TO_STRING("Insertando Traslados de Pacientes: " + ex.toString()))
                            .append("WHERE I_EXME_Traslado_ID=").append(I_EXME_Traslado_ID);
                        DB.executeUpdate(sql.toString());
                        continue;
                    }
                    
                    //  Update I_EXME_Diagnostico
                    pstmt_setImported.setInt(1, EXME_Traslado_ID);
                    pstmt_setImported.setInt(2, I_EXME_Traslado_ID);
                    no = pstmt_setImported.executeUpdate();
                    //
                    conn.commit();
                }
            rs.close();
            pstmt.close();

            //
            pstmt_insertTraslados.close();
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
        sql = new StringBuffer ("UPDATE I_EXME_Traslado "
            + "SET I_IsImported='N', Updated="+ DB.TO_DATE(DB.getTimestampForOrg(Env.getCtx())) + " "
            + "WHERE I_IsImported<>'Y'").append(clientCheck);
        no = DB.executeUpdate(sql.toString());
        addLog (0, null, new BigDecimal (no), "@Errors@");
        addLog (0, null, new BigDecimal (noInsert), "@EXME_Traslado_ID@: @Inserted@");
        addLog (0, null, new BigDecimal (noUpdate), "@EXME_Traslado_ID@: @Updated@");
        return "";
    }
}
