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
 * Modificado: $Date: 2006/10/11 17:12:08 $<p>
 * Por: $Author: vgarcia $<p>
 * 
 * @author mrojas
 * @version $Revision: 1.2 $
 */
public class ImportDefuncion extends SvrProcess {
    
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
    public ImportDefuncion() {
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
            sql = new StringBuffer ("DELETE I_EXME_Defuncion "
                + "WHERE I_IsImported='Y'").append(clientCheck);
            no = DB.executeUpdate(sql.toString());
            log.info("Delete Old Impored =" + no);
        }

        //  Set Client, Org, IaActive, Created/Updated,
        sql = new StringBuffer ("UPDATE I_EXME_Defuncion "
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
        sql = new StringBuffer ("UPDATE I_EXME_Defuncion i "
            + "SET EXME_Paciente_ID = (SELECT EXME_Paciente_ID FROM EXME_Paciente p WHERE "
            + " p.Value = i.EXME_Paciente_Value "
            + MEXMELookupInfo.addAccessLevelSQL(getCtx(), " ", MEXMEPaciente.Table_Name, "p")                
            + ") "
            + "WHERE I_IsImported<>'Y'");
        no = DB.executeUpdate(sql.toString());
        log.fine("Set Exme_Paciente_ID=" + no);
        
        //  Set Expediente
        sql = new StringBuffer ("UPDATE I_EXME_CartaAutoriza i "
            + "SET Expediente = (SELECT Expediente FROM EXME_hist_exp p WHERE  "
            + " p.AD_Client_ID=i.AD_Client_ID  AND p.EXME_Paciente_id = i.EXME_Paciente_id ) "
            + "WHERE I_IsImported<>'Y'").append(clientCheck);
        no = DB.executeUpdate(sql.toString());
        log.fine("Set Expediente=" + no);
        
        
        //  Set Exme_Hist_Exp_ID
        sql = new StringBuffer ("UPDATE I_EXME_CartaAutoriza i "
            + "SET Exme_Hist_Exp_ID = (SELECT Exme_Hist_Exp_ID FROM EXME_hist_exp p WHERE  "
            + "   p.AD_Client_ID=i.AD_Client_ID  AND p.Expediente  = i.Expediente  ) "
            + "WHERE I_IsImported<>'Y'").append(clientCheck);
        no = DB.executeUpdate(sql.toString());
        log.fine("Set Exme_Hist_Exp_ID=" + no);
        
        // Set Exme_Defuncion_ID
        sql = new StringBuffer("Update I_EXME_DEFUNCION i set EXME_DEFUNCION_ID=" +
        		"(Select EXME_DEFUNCION_ID from EXME_DEFUNCION def " +
        		"where def.EXME_PACIENTE_ID=i.EXME_PACIENTE_ID) " +
        		"where i.I_ISIMPORTED<>'Y' and i.EXME_PACIENTE_ID is not null and i.EXME_PACIENTE_ID>0");
        no = DB.executeUpdate(sql.toString());
        log.fine("Set Exme_Defuncion_ID=" + no);
    

        
        //-------------------------------------------------------------------
        int noInsert = 0;
        int noUpdate = 0;
        //int noInsertPO = 0;
        //int noUpdatePO = 0;

        //  Go through Records
        log.fine("start inserting/updating ...");
        sql = new StringBuffer ("SELECT I_EXME_Defuncion_ID, EXME_Defuncion_ID "
            + "FROM I_EXME_Defuncion WHERE I_IsImported='N'").append(clientCheck);
        Connection conn = DB.createConnection(false, Connection.TRANSACTION_READ_COMMITTED);
        try
        {
            //  Insertar subtipo de producto a partir de la importacion
            PreparedStatement pstmt_insertDefuncion = conn.prepareStatement
                ("INSERT INTO EXME_Defuncion (EXME_Defuncion_ID,"
                + "AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,"
                + "AVISOFAMILIAR,AVISOTRABSOC,FECHAHR,EXME_PACIENTE_ID,Exme_Hist_Exp_ID,NOMBRE_PAC,OBSERVACION, DIRECCION,  TSCAMA,TSCLINICO) "
                + "SELECT ?,"
                + "AD_Client_ID,AD_Org_ID,'Y',SysDate,CreatedBy,SysDate,UpdatedBy,"
                + "AVISOFAMILIAR,AVISOTRABSOC,FECHAHR,EXME_PACIENTE_ID,Exme_Hist_Exp_ID,NOMBRE_PAC,OBSERVACION, DIRECCION, TSCAMA,TSCLINICO "
                + "FROM I_EXME_Defuncion "
                + "WHERE I_EXME_Defuncion_ID=?");
            
            //Actualizar el registro (vgarcia)
            PreparedStatement pstmt_updateDefuncion = conn.prepareStatement
                ("Update EXME_Defuncion set("
                + "AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,"
                + "AVISOFAMILIAR,AVISOTRABSOC,FECHAHR,EXME_PACIENTE_ID,Exme_Hist_Exp_ID,NOMBRE_PAC,OBSERVACION, " +
                		"DIRECCION,  TSCAMA,TSCLINICO)= "
                + "(SELECT "
                + "AD_Client_ID,AD_Org_ID,'Y',SysDate,CreatedBy,SysDate,UpdatedBy,"
                + "AVISOFAMILIAR,AVISOTRABSOC,FECHAHR,EXME_PACIENTE_ID,Exme_Hist_Exp_ID,NOMBRE_PAC,OBSERVACION, " +
                		"DIRECCION, TSCAMA,TSCLINICO "
                + "FROM I_EXME_Defuncion "
                + "WHERE I_EXME_Defuncion_ID=?) where EXME_Defuncion_ID=?");

            //  Set Imported = Y  a la tabla temporal
            PreparedStatement pstmt_setImported = conn.prepareStatement
                ("UPDATE I_EXME_Defuncion SET I_IsImported='Y', EXME_Defuncion_ID=?, "
                + "Updated=SysDate, Processed='Y' WHERE I_EXME_Defuncion_ID=?");

            //
            PreparedStatement pstmt = DB.prepareStatement(sql.toString(), null);
            ResultSet rs = pstmt.executeQuery();
            int I_EXME_Defuncion_ID = 0;
            int EXME_Defuncion_ID = 0;
            boolean newRegistro = false;
            
            while (rs.next())
            {
                I_EXME_Defuncion_ID = rs.getInt(1);
                EXME_Defuncion_ID = rs.getInt(2);
                newRegistro = EXME_Defuncion_ID == 0;
                log.fine("I_EXME_Defuncion_ID=" + I_EXME_Defuncion_ID + ", EXME_Defuncion_ID=" + EXME_Defuncion_ID);

                    try
                    {
                    	if(newRegistro){
                    		EXME_Defuncion_ID = DB.getNextID(m_AD_Client_ID, "EXME_Defuncion", null);
                            if (EXME_Defuncion_ID <= 0)
                                throw new DBException("No Next ID (" + EXME_Defuncion_ID + ")");
                            pstmt_insertDefuncion.setInt(1, EXME_Defuncion_ID);
                            pstmt_insertDefuncion.setInt(2, I_EXME_Defuncion_ID);
                            //
                            no = pstmt_insertDefuncion.executeUpdate();
                            log.finer("Insert Defunc = " + no);
                            noInsert++;
                    	}
                    	else{
                            pstmt_updateDefuncion.setInt(1, I_EXME_Defuncion_ID);
                            pstmt_updateDefuncion.setInt(2, EXME_Defuncion_ID);
                            //
                            no = pstmt_updateDefuncion.executeUpdate();
                            log.finer("Update Defunc = " + no);
                            noUpdate++;
                    	}
			   rs.close();
			   pstmt.close();
			   rs=null;
			   pstmt=null;
                        
                    }
                    catch (Exception ex)
                    {
                        log.warning("Insertando Defunc - " + ex.toString());
                        sql = new StringBuffer ("UPDATE I_EXME_Defuncion i "
                            + "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||")
                            .append(DB.TO_STRING("Insertando Defunciones TS: " + ex.toString()))
                            .append("WHERE I_EXME_Defuncion_ID=").append(I_EXME_Defuncion_ID);
                        DB.executeUpdate(sql.toString());
                        continue;
                    }
                    
                    //  Update I_EXME_Defuncion
                    pstmt_setImported.setInt(1, EXME_Defuncion_ID);
                    pstmt_setImported.setInt(2, I_EXME_Defuncion_ID);
                    no = pstmt_setImported.executeUpdate();
                    //
                    conn.commit();
                }
            rs.close();
            pstmt.close();
	    rs=null;
	    pstmt=null;

            //
            pstmt_insertDefuncion.close();
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
        sql = new StringBuffer ("UPDATE I_EXME_Defuncion "
            + "SET I_IsImported='N', Updated=SysDate "
            + "WHERE I_IsImported<>'Y'").append(clientCheck);
        no = DB.executeUpdate(sql.toString());
        addLog (0, null, new BigDecimal (no), "@Errors@");
        addLog (0, null, new BigDecimal (noInsert), "@EXME_Defuncion_ID@: @Inserted@");
        addLog (0, null, new BigDecimal (noUpdate), "@EXME_Defuncion_ID@: @Updated@");
        return "";
    }
}
