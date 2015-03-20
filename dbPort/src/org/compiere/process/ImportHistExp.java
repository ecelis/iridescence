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
 * Modificado: $Date: 2006/10/25 16:50:20 $<p>
 * Por: $Author: vgarcia $<p>
 *
 * @author mrojas
 * @version $Revision: 1.2 $
 */
public class ImportHistExp extends SvrProcess {

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
    public ImportHistExp() {
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
            sql = new StringBuffer ("DELETE I_EXME_Hist_Exp "
                + "WHERE I_IsImported='Y'").append(clientCheck);
            no = DB.executeUpdate(sql.toString(), null);
            log.info("Delete Old Impored =" + no);
        }

        //  Set Client, Org, IaActive, Created/Updated,     ProductType
        sql = new StringBuffer ("UPDATE I_EXME_Hist_Exp "
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
        sql = new StringBuffer ("UPDATE I_EXME_Hist_Exp i "
            + "SET EXME_Paciente_ID = (SELECT EXME_Paciente_ID FROM EXME_Paciente p WHERE "
            + " p.Value = i.numhist "
            + MEXMELookupInfo.addAccessLevelSQL(getCtx(), " ", MEXMEPaciente.Table_Name, "p")                
            + ") "
            + "WHERE I_IsImported<>'Y'");
        no = DB.executeUpdate(sql.toString(), null);
        log.fine("Set Exme_Paciente_ID=" + no);
        
        //  Set EXME_Hist_Exp_ID
        sql = new StringBuffer ("UPDATE I_EXME_Hist_Exp i "
            + "SET EXME_Hist_Exp_ID = (SELECT EXME_Hist_Exp_ID FROM EXME_Hist_Exp p WHERE "
            + "p.exme_paciente_id = i.exme_paciente_id) "
            + "WHERE I_IsImported<>'Y'").append(clientCheck);
        no = DB.executeUpdate(sql.toString(), null);
        log.fine("Set EXME_Hist_Exp_ID=" + no);

//      -------------------------------------------------------------------
        int noInsert = 0;
        int noUpdate = 0;
        //int noInsertPO = 0;
        //int noUpdatePO = 0;

        //  Go through Records
        log.fine("start inserting/updating ...");
        sql = new StringBuffer ("SELECT I_EXME_Hist_Exp_ID, EXME_Hist_Exp_ID "
            + "FROM I_EXME_Hist_Exp WHERE I_IsImported='N'").append(clientCheck);
        Connection conn = DB.createConnection(false, Connection.TRANSACTION_READ_COMMITTED);
        
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try
        {
            //  Insertar subtipo de producto a partir de la importacion
            PreparedStatement pstmt_insertHistExp = conn.prepareStatement
                ("INSERT INTO EXME_Hist_Exp (EXME_Hist_Exp_ID,"
                + "AD_Client_ID,AD_Org_ID,IsActive,Fecha_Exp,CreatedBy,Updated,UpdatedBy,"
                + "EXME_Paciente_ID,Value,Cancelado)  "
                + "SELECT ?,"
                + "AD_Client_ID,AD_Org_ID,'Y',Fecha_Exp,CreatedBy,SysDate,UpdatedBy,"
                + "EXME_Paciente_ID,Expediente,Cancelado  "
                + "FROM I_EXME_Hist_Exp "
                + "WHERE I_EXME_Hist_Exp_ID=?");
            
//          Insertar subtipo de producto a partir de la importacion
            PreparedStatement pstmt_updateHistExp = conn.prepareStatement
                ("UPDATE EXME_Hist_Exp SET(AD_Client_ID,AD_Org_ID,IsActive,Fecha_Exp,CreatedBy,Updated," +
                		"UpdatedBy,EXME_Paciente_ID,Value,Cancelado) = " +
                		"(SELECT AD_Client_ID,AD_Org_ID,'Y',Fecha_Exp,CreatedBy,SysDate,UpdatedBy,EXME_Paciente_ID," +
                		"Expediente,Cancelado FROM I_EXME_Hist_Exp WHERE I_EXME_Hist_Exp_ID=?) WHERE EXME_Hist_Exp_ID = ?");

            //  Set Imported = Y  a la tabla temporal
            PreparedStatement pstmt_setImported = conn.prepareStatement
                ("UPDATE I_EXME_Hist_Exp SET I_IsImported='Y', EXME_Hist_Exp_ID=?, "
                + "Updated=SysDate, Processed='Y' WHERE I_EXME_Hist_Exp_ID=?");
            
            //  Acualiza el expediente de paciente
            PreparedStatement pstmt_setExpediente = conn.prepareStatement
                ("UPDATE EXME_Paciente SET Expediente = ( SELECT Expediente FROM EXME_Hist_Exp WHERE EXME_Hist_Exp_ID = ? ) " +
                		" WHERE EXME_Paciente_ID = ( SELECT EXME_Paciente_ID FROM EXME_Hist_Exp WHERE EXME_Hist_Exp_ID = ? ) " );
            
            
            //
            pstmt = DB.prepareStatement(sql.toString(), null);
            rs = pstmt.executeQuery();
            int I_EXME_Hist_Exp_ID = 0;
            int EXME_Hist_Exp_ID = 0;
            boolean newHistExp = false;
            
            while (rs.next())
            {
                I_EXME_Hist_Exp_ID = rs.getInt(1);
                EXME_Hist_Exp_ID = rs.getInt(2);
                newHistExp = EXME_Hist_Exp_ID == 0;
                
                log.fine("I_EXME_Hist_Exp_ID=" + I_EXME_Hist_Exp_ID + ", EXME_Hist_Exp_ID=" + EXME_Hist_Exp_ID);

                    try
                    {
                    	if(newHistExp){
                    		EXME_Hist_Exp_ID = DB.getNextID(m_AD_Client_ID, "EXME_Hist_Exp", null);
                            if (EXME_Hist_Exp_ID <= 0)
                                throw new DBException("No Next ID (" + EXME_Hist_Exp_ID + ")");
                            pstmt_insertHistExp.setInt(1, EXME_Hist_Exp_ID);
                            pstmt_insertHistExp.setInt(2, I_EXME_Hist_Exp_ID);
                            //
                            no = pstmt_insertHistExp.executeUpdate();
                            log.finer("Insert Expediente = " + no);
                            noInsert++;
                    	}else {
                    		pstmt_updateHistExp.setInt(1, I_EXME_Hist_Exp_ID);
                            pstmt_updateHistExp.setInt(2, EXME_Hist_Exp_ID);
                            //
                            no = pstmt_updateHistExp.executeUpdate();
                            log.finer("Update Expediente = " + no);
                            noUpdate++;
                    	}
                    	
                    	pstmt_setExpediente.setInt(1, EXME_Hist_Exp_ID);
                    	pstmt_setExpediente.setInt(2, EXME_Hist_Exp_ID);
                        no = pstmt_setExpediente.executeUpdate();
                        log.finer("Update Update Paciente = " + no);
                        System.out.println("Actualizar reg de paciente : " + EXME_Hist_Exp_ID);
                    	
                    }
                    catch (Exception ex)
                    {
                        log.warning("Insertando Expediente - " + ex.toString());
                        sql = new StringBuffer ("UPDATE I_EXME_Hist_Exp i "
                            + "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||")
                            .append(DB.TO_STRING("Insertando Expediente: " + ex.toString()))
                            .append("WHERE I_Exme_Hist_Exp_ID=").append(I_EXME_Hist_Exp_ID);
                        DB.executeUpdate(sql.toString(), null);
                        continue;
                    }

                    //  Update I_EXME_Diagnostico
                    pstmt_setImported.setInt(1, EXME_Hist_Exp_ID);
                    pstmt_setImported.setInt(2, I_EXME_Hist_Exp_ID);
                    no = pstmt_setImported.executeUpdate();
                    //
                    conn.commit();
                }
            rs.close();
            pstmt.close();
            rs = null;
            pstmt = null;

            //
            pstmt_insertHistExp.close();
            pstmt_insertHistExp = null;
            pstmt_setImported.close();
            pstmt_setImported = null;
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
            try {
                if (rs != null)
                    rs.close();

                if (pstmt != null)
                    pstmt.close();
            } catch (SQLException e) {
            }

            rs = null;
            pstmt = null;
            
            if (conn != null)
                conn.close();
            conn = null;
        }

        //  Set Error to indicator to not imported
        sql = new StringBuffer ("UPDATE I_EXME_Hist_Exp "
            + "SET I_IsImported='N', Updated=SysDate "
            + "WHERE I_IsImported<>'Y'").append(clientCheck);
        no = DB.executeUpdate(sql.toString(), null);
        addLog (0, null, new BigDecimal (no), "@Errors@");
        addLog (0, null, new BigDecimal (noInsert), "@M_Exme_Hist_Exp_ID@: @Inserted@");
        addLog (0, null, new BigDecimal (noUpdate), "@M_Exme_Hist_Exp_ID@: @Updated@");
        return "";
    }
}
