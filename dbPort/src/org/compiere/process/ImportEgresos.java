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
public class ImportEgresos extends SvrProcess {
    
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
    public ImportEgresos() {
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
            sql = new StringBuffer ("DELETE I_EXME_Egresos "
                + "WHERE I_IsImported='Y'").append(clientCheck);
            no = DB.executeUpdate(sql.toString());
            log.info("Delete Old Impored =" + no);
        }

        //  Set Client, Org, IaActive, Created/Updated,
        sql = new StringBuffer ("UPDATE I_EXME_Egresos "
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
        sql = new StringBuffer ("UPDATE I_EXME_Egresos i "
            + "SET EXME_Paciente_ID = (SELECT EXME_Paciente_ID FROM EXME_Paciente p WHERE "
            + " p.Value = i.EXME_Paciente_Value "
            + MEXMELookupInfo.addAccessLevelSQL(getCtx(), " ", MEXMEPaciente.Table_Name, "p")                
            + ") "
            + "WHERE I_IsImported<>'Y'");
        no = DB.executeUpdate(sql.toString());
        log.fine("Set Exme_Paciente_ID=" + no);
        
        //  Set Exme_Hist_Exp_ID
        sql = new StringBuffer ("UPDATE I_EXME_Egresos i "
            + "SET EXME_Hist_Exp_ID = (SELECT EXME_Hist_Exp_ID FROM EXME_Hist_Exp p WHERE "
            + " p.AD_Client_ID=i.AD_Client_ID  AND p.Expediente = i.Expediente ) "
            + "WHERE I_IsImported<>'Y'").append(clientCheck);
        no = DB.executeUpdate(sql.toString());
        log.fine("Set EXME_Hist_Exp_ID=" + no);        

        //  Set Exme_Cama_ID
        sql = new StringBuffer ("UPDATE I_EXME_Egresos i "
            + "SET EXME_Cama_ID = (SELECT EXME_Cama_ID FROM EXME_Cama p WHERE "
            + " p.AD_Client_ID=i.AD_Client_ID  AND p.Value = i.Exme_Cama_Value) "
            + "WHERE I_IsImported<>'Y'").append(clientCheck);
        no = DB.executeUpdate(sql.toString());
        log.fine("Set Exme_Cama_ID=" + no);
        
        //  Set Exme_Diagnostico_ID
        //Se agrego el MAX ala Subconsulta ya que como se a#adio el header a exme diagnosticos los value se repiten 
        // y con esto al traer mas de un ID , solo tomara el del valor mayor suponiendo que este es del header mas reciente. 
        sql = new StringBuffer ("UPDATE I_EXME_Egresos i "
            + "SET EXME_Diagnostico_ID = (SELECT MAX(EXME_Diagnostico_ID) FROM EXME_Diagnostico p WHERE "
            + " p.AD_Client_ID=i.AD_Client_ID  AND p.Value = i.Exme_Diagnostico_Value) "
            + "WHERE I_IsImported<>'Y'").append(clientCheck);
        no = DB.executeUpdate(sql.toString());
        log.fine("Set Exme_Diagnostico_ID=" + no);
        
        
        //  Set Exme_EstServ_ID
        sql = new StringBuffer ("UPDATE I_EXME_Egresos i "
            + "SET EXME_EstServ_ID = (SELECT EXME_EstServ_ID FROM EXME_EstServ  p WHERE "
            + " p.AD_Client_ID=i.AD_Client_ID  AND p.Value = i.Exme_EstServ_Value) "
            + "WHERE I_IsImported<>'Y'").append(clientCheck);
        no = DB.executeUpdate(sql.toString());
        log.fine("Set Exme_EstServ_ID=" + no);
        
        
        //  Set Exme_MotivoEgreso_ID
        sql = new StringBuffer ("UPDATE I_EXME_Egresos i "
            + "SET EXME_MotivoEgreso_ID = (SELECT EXME_MotivoEgreso_ID FROM EXME_MotivoEgreso  p WHERE "
            + " p.AD_Client_ID=i.AD_Client_ID  AND p.Name = i.Exme_MotivoEgreso_Name) "
            + "WHERE I_IsImported<>'Y'").append(clientCheck);
        no = DB.executeUpdate(sql.toString());
        log.fine("Set Exme_MotivoEgreso_ID=" + no);
        
        
        

        
        //-------------------------------------------------------------------
        int noInsert = 0;
        int noUpdate = 0;
      //  int noInsertPO = 0;
        //int noUpdatePO = 0;

        //  Go through Records
        log.fine("start inserting/updating ...");
        sql = new StringBuffer ("SELECT I_EXME_Egresos_ID, EXME_Egresos_ID "
            + "FROM I_EXME_Egresos WHERE I_IsImported='N'").append(clientCheck);
        Connection conn = DB.createConnection(false, Connection.TRANSACTION_READ_COMMITTED);
        try
        {
            //  Insertar subtipo de producto a partir de la importacion
            PreparedStatement pstmt_insertEgresos = conn.prepareStatement
                ("INSERT INTO EXME_Egresos (EXME_Egresos_ID,"
                + "AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,"
                + "Consecutivo,CtasCor,Diagnostico1,Diagnostico2,Diagnostico3,Diagnostico4, "
                + "EXME_CAMA_ID,EXME_CTAPAC_ID,EXME_DIAGNOSTICO_ID,EXME_ESTSERV_ID,EXME_MOTIVOEGRESO_ID,Exme_Hist_Exp_ID,NOMBRE_PAC, "
                + "Fecha,Motivo,ServClin,TS,VisBueno ) "
                + "SELECT ?,"
                + "AD_Client_ID,AD_Org_ID,'Y',SysDate,CreatedBy,SysDate,UpdatedBy,"
                + "Consecutivo, CtasCor,Diagnostico1,Diagnostico2,Diagnostico3,Diagnostico4, "
                + "EXME_CAMA_ID,EXME_CTAPAC_ID,EXME_DIAGNOSTICO_ID,EXME_ESTSERV_ID,EXME_MOTIVOEGRESO_ID,Exme_Hist_Exp_ID,NOMBRE_PAC, "
                + "Fecha,Motivo,ServClin,TS,VisBueno "
                + "FROM I_EXME_Egresos "
                + "WHERE I_EXME_Egresos_ID=?");

            //  Set Imported = Y  a la tabla temporal
            PreparedStatement pstmt_setImported = conn.prepareStatement
                ("UPDATE I_EXME_Egresos SET I_IsImported='Y', EXME_Egresos_ID=?, "
                + "Updated=SysDate, Processed='Y' WHERE I_EXME_Egresos_ID=?");

            //
            PreparedStatement pstmt = DB.prepareStatement(sql.toString(), null);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next())
            {
                int I_EXME_Egresos_ID = rs.getInt(1);
                int EXME_Egresos_ID = rs.getInt(2);
               // boolean newRegistro = EXME_Egresos_ID == 0;
                log.fine("I_EXME_Egresos_ID=" + I_EXME_Egresos_ID + ", EXME_Egresos_ID=" + EXME_Egresos_ID);

                    try
                    {
                        EXME_Egresos_ID = DB.getNextID(m_AD_Client_ID, "EXME_Egresos", null);
                        if (EXME_Egresos_ID <= 0)
                            throw new DBException("No Next ID (" + EXME_Egresos_ID + ")");
                        pstmt_insertEgresos.setInt(1, EXME_Egresos_ID);
                        pstmt_insertEgresos.setInt(2, I_EXME_Egresos_ID);
                        //
                        no = pstmt_insertEgresos.executeUpdate();
                        log.finer("Insert Egresos = " + no);
                        noInsert++;
                    }
                    catch (Exception ex)
                    {
                        log.warning("Insertando Egresos - " + ex.toString());
                        sql = new StringBuffer ("UPDATE I_EXME_Egresos i "
                            + "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||")
                            .append(DB.TO_STRING("Insertando Defunciones TS: " + ex.toString()))
                            .append("WHERE I_EXME_Egresos_ID=").append(I_EXME_Egresos_ID);
                        DB.executeUpdate(sql.toString());
                        continue;
                    }
                    
                    //  Update I_EXME_Egresos
                    pstmt_setImported.setInt(1, EXME_Egresos_ID);
                    pstmt_setImported.setInt(2, I_EXME_Egresos_ID);
                    no = pstmt_setImported.executeUpdate();
                    //
                    conn.commit();
                }
            rs.close();
            pstmt.close();
	    rs=null;
	    pstmt=null;

            //
            pstmt_insertEgresos.close();
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
        sql = new StringBuffer ("UPDATE I_EXME_Egresos "
            + "SET I_IsImported='N', Updated=SysDate "
            + "WHERE I_IsImported<>'Y'").append(clientCheck);
        no = DB.executeUpdate(sql.toString());
        addLog (0, null, new BigDecimal (no), "@Errors@");
        addLog (0, null, new BigDecimal (noInsert), "@EXME_Egresos_ID@: @Inserted@");
        addLog (0, null, new BigDecimal (noUpdate), "@EXME_Egresos_ID@: @Updated@");
        return "";
    }
}
