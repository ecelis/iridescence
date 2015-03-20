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
 * Proceso de importaci&oacute;n de la tabla de Envios de Pago.<p>
 * Creado: 13/Nov/2006<p>
 * Modificado: $Date: 2006/10/11 10:36:08 $<p>
 * Por: $Author: vgarcia $<p>
 * 
 * @author vgarcia
 * @version $Revision: 1.0 $
 */
public class ImportEnviosPago extends SvrProcess {
    
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
    public ImportEnviosPago() {
        super();
    }
    /**
     * Preparar: obtener par&aacute;metros
     */
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
            sql = new StringBuffer ("DELETE I_EXME_CartaAutoriza "
                + "WHERE I_IsImported='Y'").append(clientCheck);
            no = DB.executeUpdate(sql.toString());
            log.info("Delete Old Impored =" + no);
        }

        //  Set Client, Org, IaActive, Created/Updated,     ProductType
        sql = new StringBuffer ("UPDATE I_EXME_CartaAutoriza "
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
        sql = new StringBuffer ("UPDATE I_EXME_ENVIOSPAGO i " +
        		"SET EXME_Paciente_ID = (SELECT EXME_Paciente_ID FROM EXME_Paciente p " +
        		"WHERE p.value = i.EXME_Paciente_Value " 
                + MEXMELookupInfo.addAccessLevelSQL(getCtx(), " ", MEXMEPaciente.Table_Name, "p")                
                + ") "
        		+ "WHERE I_IsImported<>'Y'");
        no = DB.executeUpdate(sql.toString());
        log.fine("Set Exme_Paciente_ID=" + no);
        
        //Set EXME_HIST_EXP_ID
        sql = new StringBuffer ("UPDATE I_EXME_ENVIOSPAGO i " +
        		"SET EXME_HIST_EXP_ID = (SELECT EXME_HIST_EXP_ID FROM EXME_HIST_EXP ex " +
        		"WHERE ex.EXPEDIENTE = i.EXPEDIENTE) " +
        		"WHERE I_IsImported<>'Y' ").append(clientCheck);
            no = DB.executeUpdate(sql.toString());
            log.fine("Set EXME_HIST_EXP_ID=" + no);
            
            //Set EXME_ENVIOSPAGO_ID
            sql = new StringBuffer("Update I_EXME_ENVIOSPAGO i set EXME_ENVIOSPAGO_ID=" +
            		"(Select EXME_ENVIOSPAGO_ID from EXME_ENVIOSPAGO ep " +
            		"where ep.EXME_PACIENTE_ID=i.EXME_PACIENTE_ID) " +
            		"where i.I_ISIMPORTED<>'Y' and i.EXME_PACIENTE_ID is not null and i.EXME_PACIENTE_ID>0 ").append(clientCheck);
            no = DB.executeUpdate(sql.toString());
            log.fine("Set EXME_ENVIOSPAGO_ID=" + no);
            
        //-------------------------------------------------------------------
        int noInsert = 0;
        int noUpdate = 0;
        //int noInsertPO = 0;
        //int noUpdatePO = 0;

        //  Go through Records
        log.fine("start inserting/updating ...");
        sql = new StringBuffer ("Select I_EXME_ENVIOSPAGO_ID, EXME_ENVIOSPAGO_ID from I_EXME_ENVIOSPAGO " +
        		"WHERE I_IsImported='N'");//.append(clientCheck);
        Connection conn = DB.createConnection(false, Connection.TRANSACTION_READ_COMMITTED);
        try
        {
            //  Insertar un registro
            PreparedStatement pstmt_insertEnviosPago = conn.prepareStatement
                ("INSERT INTO EXME_ENVIOSPAGO (EXME_ENVIOSPAGO_ID,AD_CLIENT_ID, AD_ORG_ID, ISACTIVE, " +
                		"CREATED, CREATEDBY, UPDATED, UPDATEDBY, EXME_PACIENTE_ID, EXME_HIST_EXP_ID, FECHA, " +
                		"NOMBRE_PAC, TSCLASIFICACION, CONCPAGO, PAGA, DEBE, TS, PAGADO) " +
                		"SELECT ? ,AD_CLIENT_ID, AD_ORG_ID, ISACTIVE, CREATED, CREATEDBY,UPDATED, UPDATEDBY, " +
                		"EXME_PACIENTE_ID, EXME_HIST_EXP_ID, FECHA, NOMBRE_PAC, CLASIFICACION, CONCPAGO, PAGA, " +
                		"DEBE, TS, PAGADO FROM I_EXME_ENVIOSPAGO WHERE I_EXME_ENVIOSPAGO_ID=?");
            
            //  Actualiza un registro
            PreparedStatement pstmt_updateEnviosPago = conn.prepareStatement
                ("UPDATE EXME_ENVIOSPAGO SET(AD_CLIENT_ID, AD_ORG_ID, ISACTIVE, CREATED, CREATEDBY, UPDATED, " +
                		"UPDATEDBY, EXME_PACIENTE_ID, EXME_HIST_EXP_ID, FECHA, NOMBRE_PAC, TSCLASIFICACION, CONCPAGO, " +
                		"PAGA, DEBE, TS, PAGADO)= " +
                		"(SELECT AD_CLIENT_ID, AD_ORG_ID, ISACTIVE, CREATED, CREATEDBY,UPDATED, UPDATEDBY, " +
                		"EXME_PACIENTE_ID, EXME_HIST_EXP_ID, FECHA, NOMBRE_PAC, CLASIFICACION, CONCPAGO, PAGA, " +
                		"DEBE, TS, PAGADO FROM I_EXME_ENVIOSPAGO WHERE I_EXME_ENVIOSPAGO_ID=?) " +
                		"WHERE EXME_ENVIOSPAGO_ID=?");

            //  Set Imported = Y  a la tabla temporal
            PreparedStatement pstmt_setImported = conn.prepareStatement
                ("UPDATE I_EXME_ENVIOSPAGO SET I_IsImported='Y', EXME_ENVIOSPAGO_ID=?, "
                + "Updated=SysDate, Processed='Y' WHERE I_EXME_ENVIOSPAGO_ID=?");

            //
            PreparedStatement pstmt = DB.prepareStatement(sql.toString(), null);
            ResultSet rs = pstmt.executeQuery();
            int I_EXME_ENVIOSPAGO_ID = 0;
            int EXME_ENVIOSPAGO_ID = 0;
            boolean newRegistro = false;
            
            while (rs.next())
            {
                I_EXME_ENVIOSPAGO_ID = rs.getInt(1);
                EXME_ENVIOSPAGO_ID = rs.getInt(2);
                newRegistro = EXME_ENVIOSPAGO_ID == 0;
                log.fine("I_EXME_CartaAutoriza_ID=" + I_EXME_ENVIOSPAGO_ID + ", EXME_CartaAutoriza_ID=" + EXME_ENVIOSPAGO_ID);

                    try
                    {
                    	// Inserccion
                    	if((newRegistro)){
                    		EXME_ENVIOSPAGO_ID = DB.getNextID(m_AD_Client_ID, "EXME_EnviosPago", null);
                            if (EXME_ENVIOSPAGO_ID <= 0)
                                throw new DBException("No Next ID (" + EXME_ENVIOSPAGO_ID + ")");
                            pstmt_insertEnviosPago.setInt(1, EXME_ENVIOSPAGO_ID);
                            pstmt_insertEnviosPago.setInt(2, I_EXME_ENVIOSPAGO_ID);
                            //
                            no = pstmt_insertEnviosPago.executeUpdate();
                            log.finer("Insert CartaAut = " + no);
                            noInsert++;
                    	} else { // Actualizacion
                            pstmt_updateEnviosPago.setInt(1, I_EXME_ENVIOSPAGO_ID);
                            pstmt_updateEnviosPago.setInt(2, EXME_ENVIOSPAGO_ID);
                            
                            no = pstmt_updateEnviosPago.executeUpdate();
                            log.finer("Insert ENVIOSPAGO = " + no);
                            noUpdate++;
                    	}
                        
                    }
                    catch (Exception ex)
                    {
                        log.warning("Insertando ENVIOSPAGO - " + ex.toString());
                        sql = new StringBuffer ("UPDATE I_EXME_ENVIOSPAGO i "
                            + "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||")
                            .append(DB.TO_STRING("Insertando envio de pago: " + ex.toString()))
                            .append("WHERE I_EXME_ENVIOSPAGO_ID=").append(I_EXME_ENVIOSPAGO_ID);
                        DB.executeUpdate(sql.toString());
                        continue;
                    }
                    
                    //  Update I_EXME_ENVIOSPAGO
                    pstmt_setImported.setInt(1, EXME_ENVIOSPAGO_ID);
                    pstmt_setImported.setInt(2, I_EXME_ENVIOSPAGO_ID);
                    no = pstmt_setImported.executeUpdate();
                    //
                    conn.commit();
                }
            rs.close();
            pstmt.close();
	    rs=null;
	    pstmt=null;

            //
            pstmt_insertEnviosPago.close();
            pstmt_updateEnviosPago.close();
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
        sql = new StringBuffer ("UPDATE I_EXME_ENVIOSPAGO "
            + "SET I_IsImported='N', Updated=SysDate "
            + "WHERE I_IsImported<>'Y'").append(clientCheck);
        no = DB.executeUpdate(sql.toString());
        addLog (0, null, new BigDecimal (no), "@Errors@");
        addLog (0, null, new BigDecimal (noInsert), "@EXME_ENVIOSPAGO_ID@: @Inserted@");
        addLog (0, null, new BigDecimal (noUpdate), "@EXME_ENVIOSPAGO_ID@: @Updated@");
        return "";
        
    }

}
