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
 * Modificado: $Date: 2006/02/09 23:31:32 $<p>
 * Por: $Author: gisela $<p>
 * 
 * @author mrojas
 * @version $Revision: 1.3 $
 */
public class ImportCasosMedicos extends SvrProcess {
    
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
    public ImportCasosMedicos() {
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
            sql = new StringBuffer ("DELETE I_EXME_CasosMedicos "
                + "WHERE I_IsImported='Y'").append(clientCheck);
            no = DB.executeUpdate(sql.toString());
            log.info("Delete Old Impored =" + no);
        }

        //  Set Client, Org, IaActive, Created/Updated,     ProductType
        sql = new StringBuffer ("UPDATE I_EXME_CasosMedicos "
            + "SET AD_Client_ID = COALESCE (AD_Client_ID, ").append(m_AD_Client_ID).append("),"
            + " AD_Org_ID = COALESCE (AD_Org_ID,").append(m_AD_Org_ID).append("),"
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
        sql = new StringBuffer ("UPDATE I_EXME_CasosMedicos  i "
            + "SET EXME_Paciente_ID = (SELECT EXME_Paciente_ID FROM EXME_Paciente p WHERE p.IsDefault='Y'"
            + " AND p.Value = i.EXME_Paciente_Value "
            + MEXMELookupInfo.addAccessLevelSQL(getCtx(), " ", MEXMEPaciente.Table_Name, "p")            
            + ") "
            + "WHERE I_IsImported<>'Y'");
        no = DB.executeUpdate(sql.toString());
        log.fine("Set Exme_Paciente_ID=" + no);
        
         //  Set Exme_Hist_Exp_ID
        sql = new StringBuffer ("UPDATE I_EXME_CasosMedicos i "
            + "SET EXME_Hist_Exp_ID = (SELECT EXME_Hist_Exp_ID FROM EXME_Hist_Exp p WHERE "
            + " p.AD_Client_ID=i.AD_Client_ID  AND p.Expediente = i.Expediente ) "
            + "WHERE I_IsImported<>'Y'").append(clientCheck);
        no = DB.executeUpdate(sql.toString());
        log.fine("Set Exme_Paciente_ID=" + no);

        //  Set EXME_Habitacion_ID
        
        
        //  Set Exme_Cama_ID
        sql = new StringBuffer ("UPDATE I_EXME_CasosMedicos i "
            + "SET EXME_Cama_ID = (SELECT EXME_Cama_ID FROM EXME_Cama p WHERE "
            + " p.AD_Client_ID=i.AD_Client_ID  AND p.Value = i.EXME_Cama_Value) "
            + "WHERE I_IsImported<>'Y'").append(clientCheck);
        no = DB.executeUpdate(sql.toString());
        log.fine("Set Exme_Cama_ID=" + no);

        
        //  Set Exme_Medico_ID
       /* sql = new StringBuffer ("UPDATE I_EXME_CasosMedicos i "
            + "SET EXME_Medico_ID = (SELECT EXME_Medico_ID FROM EXME_Medico p WHERE "
            + " p.AD_Client_ID=i.AD_Client_ID  AND p.Value = i.EXME_Medico_Value) "
            + "WHERE I_IsImported<>'Y'").append(clientCheck);*/
        
        sql = new StringBuffer ("UPDATE I_EXME_CasosMedicos i "
                + "SET EXME_Medico_ID = (SELECT EXME_Medico_ID FROM EXME_Medico p WHERE "
                + "p.Value = i.EXME_Medico_Value "
                + MEXMELookupInfo.addAccessLevelSQL(getCtx(), " ", MEXMEMedico.Table_Name, "p")                
                + ") "
                + "WHERE I_IsImported<>'Y'");
        
        no = DB.executeUpdate(sql.toString());
        log.fine("Set Exme_Medico_ID=" + no);
        
        //Set EXME_CASOSMEDICOS_ID  (vgarcia)
        sql= new StringBuffer("Update I_EXME_CASOSMEDICOS i set EXME_CASOSMEDICOS_ID=" +
        		"(Select EXME_CASOSMEDICOS_ID from EXME_CASOSMEDICOS cm " +
        		"where cm.EXME_PACIENTE_ID=i.EXME_PACIENTE_ID and cm.EXME_MEDICO_ID = i.EXME_MEDICO_ID) " +
        		"where i.I_ISIMPORTED<>'Y' and i.EXME_PACIENTE_ID is not null and i.EXME_PACIENTE_ID>0 " +
        		"and i.EXME_MEDICO_ID IS NOT NULL and i.EXME_MEDICO_ID>0");
        no = DB.executeUpdate(sql.toString());
        log.fine("Set EXME_CASOSMEDICOS_ID=" + no);
       
        
        //-------------------------------------------------------------------
        int noInsert = 0;
        int noUpdate = 0;
        //int noInsertPO = 0;
        //int noUpdatePO = 0;

        //  Go through Records
        log.fine("start inserting/updating ...");
        sql = new StringBuffer ("SELECT I_EXME_CasosMedicos_ID, EXME_CasosMedicos_ID "
            + "FROM I_EXME_CasosMedicos WHERE I_IsImported='N'").append(clientCheck);
        Connection conn = DB.createConnection(false, Connection.TRANSACTION_READ_COMMITTED);
        try
        {
            //  Insertar subtipo de producto a partir de la importacion
            PreparedStatement pstmt_insertCasosMedicos = conn.prepareStatement
                ("INSERT INTO EXME_CasosMedicos (EXME_CasosMedicos_ID,"
                + "AD_Client_ID,AD_Org_ID,IsActive,CreatedBy,Updated,UpdatedBy,"
                + "AVERPREV,CONSECUTIVO,DATEORDERED,DIAGNOSTICO,EXME_PACIENTE_ID,Exme_Hist_Exp_ID,NOMBRE_PAC,JURIDICO,MEDICO,MINSTPUB,MOTIVOALTA, "
                + "NOAVERPREV,OBSERVACION,TSCAMA,TSCLASIFICACION,TSCLINICO,TSSEXO)"
                + "SELECT ?,"
                + "AD_Client_ID,AD_Org_ID,'Y',CreatedBy,SysDate,UpdatedBy,"
                + "AVERPREV,CONSECUTIVO,DATEORDERED,DIAGNOSTICO,EXME_PACIENTE_ID,Exme_Hist_Exp_ID,NOMBRE_PAC,JURIDICO,MEDICO,MINSTPUB,MOTIVOALTA, "
                + "NOAVERPREV,OBSERVACION,TSCAMA,TSCLASIFICACION,TSCLINICO,TSSEXO"
                + "FROM I_EXME_CasosMedicos "
                + "WHERE I_EXME_CasosMedicos_ID=?");
            
            //  Insertar subtipo de producto a partir de la importacion (vgarcia)
            PreparedStatement pstmt_updateCasosMedicos = conn.prepareStatement
                ("Update EXME_CasosMedicos set("
                + "AD_Client_ID,AD_Org_ID,IsActive,CreatedBy,Updated,UpdatedBy,"
                + "AVERPREV,CONSECUTIVO,DATEORDERED,DIAGNOSTICO,EXME_PACIENTE_ID,Exme_Hist_Exp_ID,NOMBRE_PAC,JURIDICO,MEDICO,MINSTPUB,MOTIVOALTA, "
                + "NOAVERPREV,OBSERVACION,TSCAMA,TSCLASIFICACION,TSCLINICO,TSSEXO)="
                + "(SELECT "
                + "AD_Client_ID,AD_Org_ID,'Y',CreatedBy,SysDate,UpdatedBy,"
                + "AVERPREV,CONSECUTIVO,DATEORDERED,DIAGNOSTICO,EXME_PACIENTE_ID,Exme_Hist_Exp_ID,NOMBRE_PAC,JURIDICO,MEDICO,MINSTPUB,MOTIVOALTA, "
                + "NOAVERPREV,OBSERVACION,TSCAMA,TSCLASIFICACION,TSCLINICO,TSSEXO"
                + "FROM I_EXME_CasosMedicos "
                + "WHERE I_EXME_CasosMedicos_ID=?) " +
                		"where EXME_CasosMedicos_ID=?");

            //  Set Imported = Y  a la tabla temporal
            PreparedStatement pstmt_setImported = conn.prepareStatement
                ("UPDATE I_EXME_CasosMedicos SET I_IsImported='Y', EXME_CasosMedicos_ID=?, "
                + "Updated=SysDate, Processed='Y' WHERE I_EXME_CasosMedicos_ID=?");

            //
            PreparedStatement pstmt = DB.prepareStatement(sql.toString(), null);
            ResultSet rs = pstmt.executeQuery();
            int I_EXME_CasosMedicos_ID = rs.getInt(1);
            int EXME_CasosMedicos_ID = rs.getInt(2);
            boolean newRegistro = EXME_CasosMedicos_ID == 0;
            
            while (rs.next())
            {
                I_EXME_CasosMedicos_ID = 0;
                EXME_CasosMedicos_ID = 0;
                newRegistro =false;
                log.fine("I_EXME_CasosMedicos_ID=" + I_EXME_CasosMedicos_ID + ", EXME_CasosMedicos_ID=" + EXME_CasosMedicos_ID);

                    try
                    {
                    	if(newRegistro){
                    		EXME_CasosMedicos_ID = DB.getNextID(m_AD_Client_ID, "EXME_CasosMedicos", null);
                            if (EXME_CasosMedicos_ID <= 0)
                                throw new DBException("No Next ID (" + EXME_CasosMedicos_ID + ")");
                            pstmt_insertCasosMedicos.setInt(1, EXME_CasosMedicos_ID);
                            pstmt_insertCasosMedicos.setInt(2, I_EXME_CasosMedicos_ID);
                            //
                            no = pstmt_insertCasosMedicos.executeUpdate();
                            log.finer("Insert CasosMedicos = " + no);
                            noInsert++;
                    	}
                    	else{
                    		pstmt_updateCasosMedicos.setInt(1, I_EXME_CasosMedicos_ID);
                            pstmt_updateCasosMedicos.setInt(2, EXME_CasosMedicos_ID);
                            
                            //
                            no = pstmt_updateCasosMedicos.executeUpdate();
                            log.finer("Update CasosMedicos = " + no);
                            noUpdate++;
                    	}
                        
                    }
                    catch (Exception ex)
                    {
                        log.warning("Insertando CasosMedicos - " + ex.toString());
                        sql = new StringBuffer ("UPDATE I_EXME_CasosMedicos i "
                            + "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||")
                            .append(DB.TO_STRING("Insertando Casos Medicos: " + ex.toString()))
                            .append("WHERE I_EXME_CasosMedicos_ID=").append(I_EXME_CasosMedicos_ID);
                        DB.executeUpdate(sql.toString());
                        continue;
                    }
                    
                    //  Update I_EXME_CasosMedicos
                    pstmt_setImported.setInt(1, EXME_CasosMedicos_ID);
                    pstmt_setImported.setInt(2, I_EXME_CasosMedicos_ID);
                    no = pstmt_setImported.executeUpdate();
                    //
                    conn.commit();
                }
            rs.close();
            pstmt.close();
	        rs=null;
	        pstmt=null;

            //
            pstmt_insertCasosMedicos.close();
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
        sql = new StringBuffer ("UPDATE I_EXME_CasosMedicos "
            + "SET I_IsImported='N', Updated=SysDate "
            + "WHERE I_IsImported<>'Y'").append(clientCheck);
        no = DB.executeUpdate(sql.toString());
        addLog (0, null, new BigDecimal (no), "@Errors@");
        addLog (0, null, new BigDecimal (noInsert), "@EXME_CasosMedicos_ID@: @Inserted@");
        addLog (0, null, new BigDecimal (noUpdate), "@EXME_CasosMedicos_ID@: @Updated@");
        return "";
        
    }

}
