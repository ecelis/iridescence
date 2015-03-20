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
public class ImportHojaRec extends SvrProcess {
    
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
    public ImportHojaRec() {
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
            sql = new StringBuffer ("DELETE I_EXME_Hoja_Reclasificacion "
                + "WHERE I_IsImported='Y'").append(clientCheck);
            no = DB.executeUpdate(sql.toString());
            log.info("Delete Old Impored =" + no);
        }

        //  Set Client, Org, IaActive, Created/Updated,     ProductType
        sql = new StringBuffer ("UPDATE I_EXME_Hoja_Reclasificacion "
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
        sql = new StringBuffer ("UPDATE I_EXME_Hoja_Reclasificacion i "
            + "SET EXME_Paciente_ID = (SELECT EXME_Paciente_ID FROM EXME_Paciente p WHERE "
            + " p.Value = i.EXME_Paciente_Value "
            + MEXMELookupInfo.addAccessLevelSQL(getCtx(), " ", MEXMEPaciente.Table_Name, "p")                
            + ") "
            + "WHERE I_IsImported<>'Y'");
        no = DB.executeUpdate(sql.toString());
        log.fine("Set Exme_Paciente_ID=" + no);
        
        //  Set Exme_Hist_Exp_ID
        sql = new StringBuffer ("UPDATE I_EXME_Hoja_Reclasificacion i "
            + "SET EXME_Hist_Exp_ID = (SELECT EXME_Hist_Exp_ID FROM EXME_Hist_Exp p WHERE "
            + " p.AD_Client_ID=i.AD_Client_ID  AND p.Expediente = i.Expediente ) "
            + "WHERE I_IsImported<>'Y'").append(clientCheck);
        no = DB.executeUpdate(sql.toString());
        log.fine("Set Exme_Paciente_ID=" + no);

        
        //Set Exme_Clas_Origen_ID
        sql = new StringBuffer ("UPDATE I_EXME_Hoja_Reclasificacion i " 
                + "SET i.exme_clas_Origen_ID = (SELECT EXME_ClasCliente_ID FROM EXME_ClasCliente cc WHERE " 
                + " cc.AD_Client_ID=i.AD_Client_ID AND cc.AD_Org_ID=i.AD_Org_ID  AND cc.value = i.Exme_Clas_Origen_Value) " 
                + "WHERE I_IsImported<>'Y'").append(clientCheck); 
            no = DB.executeUpdate(sql.toString()); 
            log.fine("Set Exme_Clas_Origen_ID=" + no); 
        
        //  Set Exme_Clas_Destino_ID
            sql = new StringBuffer ("UPDATE I_EXME_Hoja_Reclasificacion i " 
                    + "SET i.exme_clas_destino_ID = (SELECT EXME_ClasCliente_ID FROM EXME_ClasCliente cc WHERE " 
                    + "cc.AD_Client_ID=i.AD_Client_ID AND cc.AD_Org_ID=i.AD_Org_ID  AND cc.value = i.Exme_Clas_Destino_Value) " 
                    + "WHERE I_IsImported<>'Y'").append(clientCheck); 
                no = DB.executeUpdate(sql.toString()); 
                log.fine("Set Exme_Clas_Destino_ID=" + no);
                
                //Set Exme_Hoja_Reclasificacion_ID (vgarcia)
                sql = new StringBuffer ("Update I_EXME_HOJA_RECLASIFICACION i set EXME_HOJA_RECLASIFICACION_ID=" +
                		"(Select EXME_HOJA_RECLASIFICACION_ID from EXME_HOJA_RECLASIFICACION hr " +
                		"where hr.EXME_PACIENTE_ID = i.EXME_PACIENTE_ID ");
                
                if (DB.isOracle()) {
                	sql.append(" AND ROWNUM=1) ");
                } else if (DB.isPostgreSQL()) {
                	sql = new StringBuffer(DB.getDatabase().addPagingSQL(sql.toString(), 1, 1));
                	sql.append(") ");
                }
                
                sql.append(" where i.I_ISIMPORTED<>'Y' And  i.EXME_Paciente_ID Is Not Null And  i.EXME_Paciente_ID>0 ").append(clientCheck); 
                    no = DB.executeUpdate(sql.toString()); 
                    log.fine("Set EXME_HOJA_RECLASIFICACION_ID=" + no); 
        
        //-------------------------------------------------------------------
        int noInsert = 0;
        int noUpdate = 0;
        //int noInsertPO = 0;
        //int noUpdatePO = 0;

        //  Go through Records
        log.fine("start inserting/updating ...");
        sql = new StringBuffer ("SELECT I_EXME_Hoja_Reclasificacion_ID, EXME_Hoja_Reclasificacion_ID "
            + "FROM I_EXME_Hoja_Reclasificacion WHERE I_IsImported='N'").append(clientCheck);
        Connection conn = DB.createConnection(false, Connection.TRANSACTION_READ_COMMITTED);
        try
        {
            //  Insertar subtipo de producto a partir de la importacion
            PreparedStatement pstmt_insertHojaRec = conn.prepareStatement
                ("INSERT INTO EXME_Hoja_Reclasificacion (EXME_Hoja_Reclasificacion_ID,"
                + "AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,AD_User_ID,"
                + "APROBADA,AUTORIZO,CANCELADA,EXME_CLAS_DESTINO_ID,EXME_CLAS_ORIGEN_ID, "
                + "ELABORO,FECHA_ALTA,FECHA_HOSP,  "
                + "FECHA_IMPRESION,MOTIVO_RCLASIF,EXME_PACIENTE_ID,SUPERVISO,TIPO_RCLAS,VOBO) "
                + "SELECT ?,"
                + "AD_Client_ID,AD_Org_ID,'Y',SysDate,CreatedBy,SysDate,UpdatedBy,CreatedBy,"
                + "APROBADA,AUTORIZO,CANCELADA,EXME_CLAS_DESTINO_ID,EXME_CLAS_ORIGEN_ID, "
                + "ELABORO,FECHA_ALTA,FECHA_HOSP,  "
                + "FECHA_IMPRESION,MOTIVO_RCLASIF,EXME_PACIENTE_ID,SUPERVISO,TIPO_RCLAS,VOBO "
                + "FROM I_EXME_Hoja_Reclasificacion "
                + "WHERE I_EXME_Hoja_Reclasificacion_ID=?");
            
            //Sentencia para hecer el Update (vgarcia) 
            PreparedStatement pstmt_updateHojaRec = conn.prepareStatement
            ("Update EXME_Hoja_Reclasificacion set("
            + "AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,AD_User_ID,"
            + "APROBADA,AUTORIZO,CANCELADA,EXME_CLAS_DESTINO_ID,EXME_CLAS_ORIGEN_ID, "
            + "ELABORO,FECHA_ALTA,FECHA_HOSP,  "
            + "FECHA_IMPRESION,MOTIVO_RCLASIF,EXME_PACIENTE_ID,SUPERVISO,TIPO_RCLAS,VOBO)= "
            + "(SELECT "
            + "AD_Client_ID,AD_Org_ID,'Y',SysDate,CreatedBy,SysDate,UpdatedBy,CreatedBy,"
            + "APROBADA,AUTORIZO,CANCELADA,EXME_CLAS_DESTINO_ID,EXME_CLAS_ORIGEN_ID, "
            + "ELABORO,FECHA_ALTA,FECHA_HOSP,  "
            + "FECHA_IMPRESION,MOTIVO_RCLASIF,EXME_PACIENTE_ID,SUPERVISO,TIPO_RCLAS,VOBO "
            + "FROM I_EXME_Hoja_Reclasificacion WHERE I_EXME_Hoja_Reclasificacion_ID=?) " +
            		"Where EXME_Hoja_Reclasificacion_ID=?");

            //  Set Imported = Y  a la tabla temporal
            PreparedStatement pstmt_setImported = conn.prepareStatement
                ("UPDATE I_EXME_Hoja_Reclasificacion SET I_IsImported='Y', EXME_Hoja_Reclasificacion_ID=?, "
                + "Updated=SysDate, Processed='Y' WHERE I_EXME_Hoja_Reclasificacion_ID=?");

            //
            PreparedStatement pstmt = DB.prepareStatement(sql.toString(), null);
            ResultSet rs = pstmt.executeQuery();
            
            int I_EXME_Hoja_Reclasificacion_ID = 0;
            int EXME_Hoja_Reclasificacion_ID = 0;
            boolean newRegistro = false;
            
            while (rs.next())
            {
                I_EXME_Hoja_Reclasificacion_ID = rs.getInt(1);
                EXME_Hoja_Reclasificacion_ID = rs.getInt(2);
                newRegistro = EXME_Hoja_Reclasificacion_ID == 0;
                log.fine("I_EXME_Hoja_Reclasificacion_ID=" + I_EXME_Hoja_Reclasificacion_ID + ", EXME_Hoja_Reclasificacion_ID=" + EXME_Hoja_Reclasificacion_ID);

                    try
                    {
                    	if(newRegistro){
                    		EXME_Hoja_Reclasificacion_ID = DB.getNextID(m_AD_Client_ID, "EXME_Hoja_Reclasificacion", null);
                            if (EXME_Hoja_Reclasificacion_ID <= 0)
                                throw new DBException("No Next ID (" + EXME_Hoja_Reclasificacion_ID + ")");
                            pstmt_insertHojaRec.setInt(1, EXME_Hoja_Reclasificacion_ID);
                            pstmt_insertHojaRec.setInt(2, I_EXME_Hoja_Reclasificacion_ID);
                            //
                            no = pstmt_insertHojaRec.executeUpdate();
                            log.finer("Insert HojaRec = " + no);
                            noInsert++;
                    	}
                    	else{
                    		pstmt_updateHojaRec.setInt(1, I_EXME_Hoja_Reclasificacion_ID);
                            pstmt_updateHojaRec.setInt(2, EXME_Hoja_Reclasificacion_ID);
                            //
                            no = pstmt_updateHojaRec.executeUpdate();
                            log.finer("Update HojaRec = " + no);
                            noUpdate++;
                    	}
                        
                    }
                    catch (Exception ex)
                    {
                        log.warning("Insertando HojaRec - " + ex.toString());
                        sql = new StringBuffer ("UPDATE I_EXME_Hoja_Reclasificacion i "
                            + "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||")
                            .append(DB.TO_STRING("Insertando Hoja de Reclasificacion: " + ex.toString()))
                            .append("WHERE I_EXME_Hoja_Reclasificacion_ID=").append(I_EXME_Hoja_Reclasificacion_ID);
                        DB.executeUpdate(sql.toString());
                        continue;
                    }
                    
                    //  Update I_EXME_Hoja_Reclasificacion
                    pstmt_setImported.setInt(1, EXME_Hoja_Reclasificacion_ID);
                    pstmt_setImported.setInt(2, I_EXME_Hoja_Reclasificacion_ID);
                    no = pstmt_setImported.executeUpdate();
                    //
                    conn.commit();
                }
			if (rs != null)
				rs.close ();
			if (pstmt != null)
				pstmt.close ();
			pstmt = null;
    		rs = null;

			//
    		if (pstmt_insertHojaRec != null)
    			pstmt_insertHojaRec.close();
    		if (pstmt_setImported != null)
    			pstmt_setImported.close();

    		pstmt_insertHojaRec= null;
    		pstmt_setImported= null;
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
        sql = new StringBuffer ("UPDATE I_EXME_Hoja_Reclasificacion "
            + "SET I_IsImported='N', Updated=SysDate "
            + "WHERE I_IsImported<>'Y'").append(clientCheck);
        no = DB.executeUpdate(sql.toString());
        addLog (0, null, new BigDecimal (no), "@Errors@");
        addLog (0, null, new BigDecimal (noInsert), "@EXME_Hoja_Reclasificacion_ID@: @Inserted@");
        addLog (0, null, new BigDecimal (noUpdate), "@EXME_Hoja_Reclasificacion_ID@: @Updated@");
        return "";
    }
}
