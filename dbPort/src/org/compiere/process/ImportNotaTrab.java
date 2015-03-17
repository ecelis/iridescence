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
 * Proceso de importaci&oacute;n para la tabla EXME_Notas_Trabajador.<p>
 * Creado: 10/Mar/2005<p>
 * Modificado: $Date: 2006/08/11 02:35:28 $<p>
 * Por: $Author: mrojas $<p>
 * 
 * @author 
 * @version $Revision: 1.2 $
 */
public class ImportNotaTrab extends SvrProcess {
    
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
    public ImportNotaTrab() {
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
            sql = new StringBuffer ("DELETE I_EXME_Notas_Trabajador "
                + "WHERE I_IsImported='Y'").append(clientCheck);
            no = DB.executeUpdate(sql.toString());
            log.info("Delete Old Impored =" + no);
        }

        //  Set Client, Org, IaActive, Created/Updated
        sql = new StringBuffer ("UPDATE I_EXME_Notas_Trabajador "
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
        sql = new StringBuffer ("UPDATE I_EXME_Notas_Trabajador i "
            + "SET EXME_Paciente_ID = (SELECT EXME_Paciente_ID FROM EXME_Paciente p WHERE "
            + " p.value = i.numhist "
            + MEXMELookupInfo.addAccessLevelSQL(getCtx(), " ", MEXMEPaciente.Table_Name, "p")                
            + ") "
            + "WHERE I_IsImported<>'Y'");
        no = DB.executeUpdate(sql.toString());
        log.fine("Set Exme_Paciente_ID=" + no);
        
        //  Set Exme_Notas_Trabajador_ID (vgarcia)
        sql = new StringBuffer ("Update I_EXME_NOTAS_TRABAJADOR i set EXME_NOTAS_TRABAJADOR_ID=" +
        		"(Select EXME_NOTAS_TRABAJADOR_ID from EXME_NOTAS_TRABAJADOR nt " +
        		"where nt.EXME_PACIENTE_ID=i.EXME_PACIENTE_ID) " +
        		"where i.I_ISIMPORTED<>'Y' and i.EXME_PACIENTE_ID is not null and i.EXME_PACIENTE_ID>0");
        no = DB.executeUpdate(sql.toString());
        log.fine("Set Exme_Notas_Trabajador_ID=" + no);
        
        //-------------------------------------------------------------------
        int noInsert = 0;
        int noUpdate = 0;
        //int noInsertPO = 0;
        //int noUpdatePO = 0;

        //  Go through Records
        log.fine("start inserting/updating ...");
        sql = new StringBuffer ("SELECT I_EXME_Notas_Trabajador_ID, EXME_Notas_Trabajador_ID "
            + "FROM I_EXME_Notas_Trabajador WHERE I_IsImported='N'");
        Connection conn = DB.createConnection(false, Connection.TRANSACTION_READ_COMMITTED);
        
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try
        {
            //  Insertar Nota de Trabajo Social a partir de la importacion
            PreparedStatement pstmt_insertNotaTrab = conn.prepareStatement
                ("INSERT INTO EXME_Notas_Trabajador (EXME_Notas_Trabajador_ID,"
                + "AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,"
                + "EXME_Paciente_ID,Fecha_Nota,Nota,Nota2,Nota3)  "
                + "SELECT ?,"
                + "AD_Client_ID,AD_Org_ID,'Y',SysDate,CreatedBy,SysDate,UpdatedBy,"
                + "EXME_Paciente_ID,Fecha_Nota,Nota,Nota2,Nota3  "
                + "FROM I_EXME_Notas_Trabajador "
                + "WHERE I_EXME_Notas_Trabajador_ID=?");
            
            //Insertar Nota de Trabajo Social a partir de la importacion (vgarcia)
            PreparedStatement pstmt_updateNotaTrab = conn.prepareStatement
                ("Update EXME_Notas_Trabajador set("
                + "AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,"
                + "EXME_Paciente_ID,Fecha_Nota,Nota,Nota2,Nota3)=  "
                + "(SELECT "
                + "AD_Client_ID,AD_Org_ID,'Y',SysDate,CreatedBy,SysDate,UpdatedBy,"
                + "EXME_Paciente_ID,Fecha_Nota,Nota,Nota2,Nota3  "
                + "FROM I_EXME_Notas_Trabajador "
                + "WHERE I_EXME_Notas_Trabajador_ID=?) where EXME_Notas_Trabajador_ID=?");

            //  Set Imported = Y  a la tabla temporal
            PreparedStatement pstmt_setImported = conn.prepareStatement
                ("UPDATE I_EXME_Notas_Trabajador SET I_IsImported='Y', EXME_Notas_Trabajador_ID=?, "
                + "Updated=SysDate, Processed='Y' WHERE I_EXME_Notas_Trabajador_ID=?");

            //
            pstmt = DB.prepareStatement(sql.toString(), null);
            rs = pstmt.executeQuery();
            
            int I_EXME_Notas_Trabajador_ID = 0;
            int EXME_Notas_Trabajador_ID = 0;
            boolean newNotaTrab = false;
            
            while (rs.next())
            {
                I_EXME_Notas_Trabajador_ID = rs.getInt(1);
                EXME_Notas_Trabajador_ID = rs.getInt(2);
                newNotaTrab = EXME_Notas_Trabajador_ID == 0;
                log.fine("I_EXME_Notas_Trabajador_ID=" + I_EXME_Notas_Trabajador_ID + ", EXME_Notas_Trabajador_ID=" + EXME_Notas_Trabajador_ID);

                    try
                    {
                    	if(newNotaTrab){
                    		EXME_Notas_Trabajador_ID = DB.getNextID(m_AD_Client_ID, "EXME_Notas_Trabajador", null);
                            if (EXME_Notas_Trabajador_ID <= 0)
                                throw new DBException("No Next ID (" + EXME_Notas_Trabajador_ID + ")");
                            pstmt_insertNotaTrab.setInt(1, EXME_Notas_Trabajador_ID);
                            pstmt_insertNotaTrab.setInt(2, I_EXME_Notas_Trabajador_ID);
                            //
                            no = pstmt_insertNotaTrab.executeUpdate();
                            log.finer("Insert NotaTrab = " + no);
                            noInsert++;
                    	}
                    	else{
                            pstmt_updateNotaTrab.setInt(1, I_EXME_Notas_Trabajador_ID);
                            pstmt_updateNotaTrab.setInt(2, EXME_Notas_Trabajador_ID);
                            //
                            no = pstmt_updateNotaTrab.executeUpdate();
                            log.finer("Update NotaTrab = " + no);
                            noUpdate++;
                    	}
                        
                    }
                    catch (Exception ex)
                    {
                        log.warning("Insertando NotaTrab - " + ex.toString());
                        sql = new StringBuffer ("UPDATE I_EXME_Notas_Trabajador i "
                            + "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||")
                            .append(DB.TO_STRING("Insertando Nota de Trabajo Social: " + ex.toString()))
                            .append("WHERE I_EXME_Notas_Trabajador_ID=").append(I_EXME_Notas_Trabajador_ID);
                        DB.executeUpdate(sql.toString());
                        continue;
                    }
                    
                    //  Update I_EXME_Diagnostico
                    pstmt_setImported.setInt(1, EXME_Notas_Trabajador_ID);
                    pstmt_setImported.setInt(2, I_EXME_Notas_Trabajador_ID);
                    no = pstmt_setImported.executeUpdate();
                    //
                    conn.commit();
                }
            rs.close();
            pstmt.close();

            //
            pstmt_insertNotaTrab.close();
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
            log.log(Level.SEVERE, "doIt", e);
            rs=null;
            pstmt=null;
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
        sql = new StringBuffer ("UPDATE I_EXME_Notas_Trabajador_ID "
            + "SET I_IsImported='N', Updated=SysDate "
            + "WHERE I_IsImported<>'Y'");
        no = DB.executeUpdate(sql.toString());
        addLog (0, null, new BigDecimal (no), "@Errors@");
        addLog (0, null, new BigDecimal (noInsert), "@I_EXME_Notas_Trabajador_ID@: @Inserted@");
        addLog (0, null, new BigDecimal (noUpdate), "@I_EXME_Notas_Trabajador_ID@: @Updated@");
        return "";
        
    }

}
