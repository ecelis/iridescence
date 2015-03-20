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
import org.adempiere.exceptions.DBException;

/**
 * Proceso de importaci&oacute;n de la tabla de Subtipos de Producto.<p>
 * Creado: 10/Mar/2005<p>
 * Modificado: $Date: 2006/10/11 17:12:08 $<p>
 * Por: $Author: vgarcia $<p>
 * 
 * @author mrojas
 * @version $Revision: 1.5 $
 */
public class ImportCartaAut extends SvrProcess {
    
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
    public ImportCartaAut() {
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
            sql = new StringBuffer ("DELETE I_EXME_CartaAutoriza "
                + "WHERE I_IsImported='Y'").append(clientCheck);
            no = DB.executeUpdate(sql.toString());
            log.info("Delete Old Impored =" + no);
        }

        //  Set Client, Org, IaActive, Created/Updated,     ProductType
        sql = new StringBuffer ("UPDATE I_EXME_CartaAutoriza "
            + "SET AD_Client_ID = COALESCE (AD_Client_ID, ").append(m_AD_Client_ID).append("),"
            + "AD_Org_ID = COALESCE (AD_Org_ID,").append(m_AD_Org_ID).append("),"
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
        sql = new StringBuffer ("UPDATE I_EXME_CartaAutoriza i "
            + "SET EXME_Paciente_ID = (SELECT EXME_Paciente_ID FROM EXME_Paciente p WHERE "
            + " p.value = i.EXME_Paciente_Value " 
            + MEXMELookupInfo.addAccessLevelSQL(getCtx(), " ", MEXMEPaciente.Table_Name, "p")            
            + ") "

            + "WHERE I_IsImported<>'Y'");//.append(clientCheck);
        no = DB.executeUpdate(sql.toString());
        log.fine("Set Exme_Paciente_ID=" + no);
        
        //Set EXME_CartaAutoriza_ID
        // Por medio de esto asignamos el ID si es que ya existe el registro y solo se hara una actualizacion (vgarcia)
        sql = new StringBuffer ("UPDATE I_EXME_CartaAutoriza i "
                + "SET EXME_CartaAutoriza_ID = (SELECT EXME_CartaAutoriza_ID FROM EXME_CartaAutoriza a WHERE "
                + "a.EXME_Paciente_ID = i.EXME_Paciente_ID) "
                + "WHERE I_IsImported<>'Y' And  i.EXME_Paciente_ID Is Not Null And  i.EXME_Paciente_ID>0 ").append(clientCheck);
            no = DB.executeUpdate(sql.toString());
            log.fine("Set EXME_CartaAutoriza_ID=" + no);
            
        //-------------------------------------------------------------------
        int noInsert = 0;
        int noUpdate = 0;
        //int noInsertPO = 0;
        //int noUpdatePO = 0;

        //  Go through Records
        log.fine("start inserting/updating ...");
        sql = new StringBuffer ("SELECT I_EXME_CartaAutoriza_ID, EXME_CartaAutoriza_ID "
            + "FROM I_EXME_CartaAutoriza WHERE I_IsImported='N'").append(clientCheck);
        Connection conn = DB.createConnection(false, Connection.TRANSACTION_READ_COMMITTED);
        try
        {
            //  Insertar subtipo de producto a partir de la importacion
            PreparedStatement pstmt_insertCartaAut = conn.prepareStatement
                ("INSERT INTO EXME_CartaAutoriza (EXME_CartaAutoriza_ID,"
                + "AD_Client_ID,AD_Org_ID,IsActive,CreatedBy,Updated,UpdatedBy,"
                + "DONADORES,CREATED,DATEPRINTED,EXME_PACIENTE_ID,TIPOCARTA) "
                + "SELECT ?,"
                + "AD_Client_ID,AD_Org_ID,'Y',CreatedBy,SysDate,UpdatedBy,"
                + "DONADORES,CREATED,DATEPRINTED,EXME_PACIENTE_ID,TIPOCARTA "
                + "FROM I_EXME_CartaAutoriza "
                + "WHERE I_EXME_CartaAutoriza_ID=?");
            
            //  Actualiza subtipo de producto a partir de la importacion
            PreparedStatement pstmt_updateCartaAut = conn.prepareStatement
                ("UPDATE EXME_CartaAutoriza  SET("
                + "AD_Client_ID,AD_Org_ID,IsActive,CreatedBy,Updated,UpdatedBy,"
                + "DONADORES,CREATED,DATEPRINTED,EXME_PACIENTE_ID,TIPOCARTA)= "
                + "(SELECT "
                + "AD_Client_ID,AD_Org_ID,'Y',CreatedBy,SysDate,UpdatedBy,"
                + "DONADORES,CREATED,DATEPRINTED,EXME_PACIENTE_ID,TIPOCARTA "
                + "FROM I_EXME_CartaAutoriza "
                + "WHERE I_EXME_CartaAutoriza_ID=?) WHERE EXME_CartaAutoriza_ID=?");

            //  Set Imported = Y  a la tabla temporal
            PreparedStatement pstmt_setImported = conn.prepareStatement
                ("UPDATE I_EXME_CartaAutoriza SET I_IsImported='Y', EXME_CartaAutoriza_ID=?, "
                + "Updated=SysDate, Processed='Y' WHERE I_EXME_CartaAutoriza_ID=?");

            //
            PreparedStatement pstmt = DB.prepareStatement(sql.toString(), null);
            ResultSet rs = pstmt.executeQuery();
            int I_EXME_CartaAutoriza_ID = 0;
            int EXME_CartaAutoriza_ID = 0;
            boolean newCartaAut = false;
            
            while (rs.next())
            {
                I_EXME_CartaAutoriza_ID = rs.getInt(1);
                EXME_CartaAutoriza_ID = rs.getInt(2);
                newCartaAut = EXME_CartaAutoriza_ID == 0;
                log.fine("I_EXME_CartaAutoriza_ID=" + I_EXME_CartaAutoriza_ID + ", EXME_CartaAutoriza_ID=" + EXME_CartaAutoriza_ID);

                    try
                    {
                    	// Inserccion
                    	if((newCartaAut)){
                    		EXME_CartaAutoriza_ID = DB.getNextID(m_AD_Client_ID, "EXME_CartaAutoriza", null);
                            if (EXME_CartaAutoriza_ID <= 0)
                                throw new DBException("No Next ID (" + EXME_CartaAutoriza_ID + ")");
                            pstmt_insertCartaAut.setInt(1, EXME_CartaAutoriza_ID);
                            pstmt_insertCartaAut.setInt(2, I_EXME_CartaAutoriza_ID);
                            //
                            no = pstmt_insertCartaAut.executeUpdate();
                            log.finer("Insert CartaAut = " + no);
                            noInsert++;
                    	} else if(!newCartaAut){ // Actualizacion
                            pstmt_updateCartaAut.setInt(1, I_EXME_CartaAutoriza_ID);
                            pstmt_updateCartaAut.setInt(2, EXME_CartaAutoriza_ID);
                            
                            no = pstmt_updateCartaAut.executeUpdate();
                            log.finer("Insert CartaAut = " + no);
                            noUpdate++;
                    	}
                        
                    }
                    catch (Exception ex)
                    {
                        log.warning("Insertando CartaAut - " + ex.toString());
                        sql = new StringBuffer ("UPDATE I_EXME_CartaAutoriza i "
                            + "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||")
                            .append(DB.TO_STRING("Insertando subtipo producto: " + ex.toString()))
                            .append("WHERE I_EXME_CartaAutoriza_ID=").append(I_EXME_CartaAutoriza_ID);
                        DB.executeUpdate(sql.toString());
                        continue;
                    }
                    
                    //  Update I_EXME_Diagnostico
                    pstmt_setImported.setInt(1, EXME_CartaAutoriza_ID);
                    pstmt_setImported.setInt(2, I_EXME_CartaAutoriza_ID);
                    no = pstmt_setImported.executeUpdate();
                    //
                    conn.commit();
                }
            rs.close();
            pstmt.close();
	    rs=null;
	    pstmt=null;

            //
            pstmt_insertCartaAut.close();
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
        sql = new StringBuffer ("UPDATE I_EXME_CartaAutoriza "
            + "SET I_IsImported='N', Updated=SysDate "
            + "WHERE I_IsImported<>'Y'").append(clientCheck);
        no = DB.executeUpdate(sql.toString());
        addLog (0, null, new BigDecimal (no), "@Errors@");
        addLog (0, null, new BigDecimal (noInsert), "@M_CartaAut_ID@: @Inserted@");
        addLog (0, null, new BigDecimal (noUpdate), "@M_CartaAut_ID@: @Updated@");
        return "";
        
    }

}
