/* The contents of this file are subject to the   Compiere License  Version 1.1
 * ("License"); You may not use this file except in compliance with the License
 * You may obtain a copy of the License at http://www.compiere.org/license.html
 * Software distributed under the License is distributed on an  "AS IS"  basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License for
 * the specific language governing rights and limitations under the License.
 * The Original Code is Compiere ERP & CRM Smart Business Solution. The Initial
 * Developer of the Original Code is Jorg Janke. Portions created by Jorg Janke
 * are Copyright (C) 1999-2005 Jorg Janke.
 * All parts are Copyright (C) 1999-2005 ComPiere, Inc.  All Rights Reserved.
 * Contributor(s): ______________________________________.
 *****************************************************************************/
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
 *  Import Admisiones_INP
 *
 *  @author     Valentin Garcia
 *  @version    $Id: ImportAdmisiones .java,v 1.4 2006/12/08 20:04 vgarcia Exp $
 *  @version $Revision: $
 *
 * Modificado: $Date: $<p>
 * Por: $Author: $<p>
 */
public class ImportINPAdmision extends SvrProcess
{
    /** Client to be imported to        */
    private int             m_AD_Client_ID = 0;
    /** Delete old Imported             */
    private boolean         m_deleteOldImported = false;

    /** Organization to be imported to  */
    private int             m_AD_Org_ID = 0;
    /** Effective                       */
    private Timestamp       m_DateValue = null;

    /**
     *  Prepare - e.g., get Parameters.
     */
    protected void prepare()
    {
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
    }   //  prepare


    /**
     *  Perrform process.
     *  @return Message
     *  @throws Exception
     */
    protected String doIt() throws java.lang.Exception
    {
        StringBuffer sql = null;
        int no = 0;
        String clientCheck = " AND AD_Client_ID=" + m_AD_Client_ID;

        //  ****    Prepare ****

        //  Delete Old Imported
        if (m_deleteOldImported)
        {
            sql = new StringBuffer ("DELETE I_EXME_INP_ADMISIONHOSP "
                + "WHERE I_IsImported='Y'").append(clientCheck);
            no = DB.executeUpdate(sql.toString());
            log.info("Delete Old Impored =" + no);
        }

        //  Set Client, Org, IaActive, Created/Updated,     ProductType
        sql = new StringBuffer ("UPDATE I_EXME_INP_ADMISIONHOSP "
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

         //  Set EXME_HIST_EXP_ID, EXME_PACIENTE_ID, FECHA_APERTURA
        sql = new StringBuffer ("UPDATE I_EXME_INP_ADMISIONHOSP  i SET (i.EXME_HIST_EXP_ID,i.EXME_PACIENTE_ID,i.FECHA_APERTURA)=" +
        		"(SELECT exp.EXME_HIST_EXP_ID,exp.EXME_PACIENTE_ID,exp.FECHA_EXP FROM EXME_HIST_EXP exp " +
        		"WHERE TRIM(exp.EXPEDIENTE)=TRIM(i.EXPEDIENTE_VALUE)) WHERE i.EXME_HIST_EXP_ID IS NULL AND i.I_IsImported<>'Y'");
            no = DB.executeUpdate(sql.toString());
            log.info("EXME_HIST_EXP_ID=" + no);
            
        //  Set NOMBRE, APELLIDO1, APELLIDO2, SEXO, FECHANAC
        sql = new StringBuffer ("UPDATE I_EXME_INP_ADMISIONHOSP  i SET (i.NOMBRE,i.APELLIDO1,i.APELLIDO2,i.SEXO,i.FECHANAC)=" +
        		"(SELECT pac.NAME,pac.APELLIDO1,pac.APELLIDO2,pac.SEXO,pac.FECHANAC FROM EXME_PACIENTE pac " +
        		"WHERE pac.EXME_PACIENTE_ID=i.EXME_PACIENTE_ID " 
                + MEXMELookupInfo.addAccessLevelSQL(getCtx(), " ", MEXMEPaciente.Table_Name, "pac")                
                + ") "
        		+ "WHERE i.EXME_PACIENTE_ID IS NOT NULL " +
        		"AND i.EXME_PACIENTE_ID>0 AND i.I_IsImported<>'Y'");
        no = DB.executeUpdate(sql.toString());
        log.info("NOMBRE=" + no);
        
//      Set EXME_CLASCLIENTE_ID
        sql = new StringBuffer ("UPDATE I_EXME_INP_ADMISIONHOSP  i SET i.EXME_CLASCLIENTE_ID=" +
        		"(SELECT clas.EXME_CLASCLIENTE_ID FROM EXME_CLASCLIENTE clas " +
        		"WHERE clas.VALUE=i.CLASIFICACION_VALUE) WHERE i.EXME_CLASCLIENTE_ID IS NULL AND i.I_IsImported<>'Y'");
        no = DB.executeUpdate(sql.toString());
        log.info("EXME_CLASCLIENTE_ID=" + no);
        
        //Set EXME_INP_ADMISIONHOSP_ID (para los updates)
        sql = new StringBuffer ("UPDATE I_EXME_INP_ADMISIONHOSP  i SET i.EXME_INP_ADMISIONHOSP_ID=" +
        		"(SELECT ah.EXME_INP_ADMISIONHOSP_ID FROM EXME_INP_ADMISIONHOSP ah " +
        		"WHERE ah.EXME_HIST_EXP_ID=i.EXME_HIST_EXP_ID) WHERE i.EXME_HIST_EXP_ID IS NOT NULL " +
        		"AND i.EXME_HIST_EXP_ID>0 AND i.EXME_INP_ADMISIONHOSP_ID IS NULL  AND i.I_IsImported<>'Y'");
            no = DB.executeUpdate(sql.toString());
            log.info("EXME_INP_ADMISIONHOSP_ID=" + no);
     
        //  -------------------------------------------------------------------
        int noInsert = 0;
        int noUpdate = 0;
        //int noInsertPO = 0;
        //int noUpdatePO = 0;
        
        //  Go through Records
        log.fine("start inserting/updating ...");
        sql = new StringBuffer ("SELECT I_EXME_INP_ADMISIONHOSP_ID, EXME_INP_ADMISIONHOSP_ID " +
        		"FROM I_EXME_INP_ADMISIONHOSP WHERE I_IsImported='N'");
        Connection conn = DB.createConnection(false, Connection.TRANSACTION_READ_COMMITTED);       
        try {
        	// Para insertar los Registros Nuevos
            PreparedStatement pstmt_insertAdmisionINP = conn.prepareStatement             
            ("INSERT INTO EXME_INP_ADMISIONHOSP (EXME_INP_ADMISIONHOSP_ID," +
            		"AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy," +
            		"EXME_HIST_EXP_ID,EXME_CLASCLIENTE_ID,SEXO,NOMBRE,APELLIDO1,APELLIDO2,FECHANAC," +
            		"CONDI,FECHA_DEFUNCION,FECHA_GUIAVERDE,MICRORROLLO,FECHA_MICROFILMACION,FECHA_APERTURA) " +
            		"SELECT ?, AD_Client_ID,AD_Org_ID,'Y',Sysdate,CreatedBy,Sysdate,UpdatedBy, " +
            		"EXME_HIST_EXP_ID,EXME_CLASCLIENTE_ID,SEXO,NOMBRE,APELLIDO1,APELLIDO2,FECHANAC," +
            		"CONDI,FECHA_DEFUNCION,FECHA_GUIAVERDE,MICRORROLLO,FECHA_MICROFILMACION,FECHA_APERTURA " +
            		"FROM I_EXME_INP_ADMISIONHOSP " +
            		"WHERE I_EXME_INP_ADMISIONHOSP_ID=?");
            
            // Para actualizar los registros ya existentes (vgarcia)
            PreparedStatement pstmt_updateAdmisionINP = conn.prepareStatement             
            ("UPDATE EXME_INP_ADMISIONHOSP SET(" +
            		"AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy," +
            		"EXME_CLASCLIENTE_ID,SEXO,NOMBRE,APELLIDO1,APELLIDO2,FECHANAC," +
            		"CONDI,FECHA_DEFUNCION,FECHA_GUIAVERDE,MICRORROLLO,FECHA_MICROFILMACION,FECHA_APERTURA)=" +
            		"(SELECT AD_Client_ID,AD_Org_ID,'Y',Sysdate,CreatedBy,Sysdate,UpdatedBy," +
            		"EXME_CLASCLIENTE_ID,SEXO,NOMBRE,APELLIDO1,APELLIDO2,FECHANAC," +
            		"CONDI,FECHA_DEFUNCION,FECHA_GUIAVERDE,MICRORROLLO,FECHA_MICROFILMACION,FECHA_APERTURA " +
            		"WHERE I_EXME_INP_ADMISIONHOSP_ID=?) WHERE EXME_INP_ADMISIONHOSP_ID=?");
                              
            
            //  Set Imported = Y
            PreparedStatement pstmt_setImported = conn.prepareStatement
                ("UPDATE I_EXME_INP_ADMISIONHOSP SET I_IsImported='Y', "
                + "Updated=SysDate, Processed='Y' WHERE I_EXME_INP_ADMISIONHOSP_ID=?");

            //
            PreparedStatement pstmt = DB.prepareStatement(sql.toString(), null);
           // PreparedStatement ps = null;
            //ResultSet rs1 = null;
            ResultSet rs = pstmt.executeQuery();
            int I_EXME_INP_ADMISIONHOSP_ID = 0;   
            int  EXME_INP_ADMISIONHOSP_ID = 0;
            //int id = 0;
            boolean nuevo = false;
            
            while (rs.next())
            {
            	I_EXME_INP_ADMISIONHOSP_ID = rs.getInt(1);   
            	EXME_INP_ADMISIONHOSP_ID = rs.getInt(2);
                //id=0;
                nuevo = EXME_INP_ADMISIONHOSP_ID == 0 ;

                    try
                    {
                    	if(nuevo){
                    		EXME_INP_ADMISIONHOSP_ID = DB.getNextID(m_AD_Client_ID, "EXME_INP_AdmisionHosp", null);
                            if (EXME_INP_ADMISIONHOSP_ID <= 0)
                                throw new DBException("No Next ID (" + EXME_INP_ADMISIONHOSP_ID + ")");
                            pstmt_insertAdmisionINP.setInt(1, EXME_INP_ADMISIONHOSP_ID);
                            pstmt_insertAdmisionINP.setInt(2, I_EXME_INP_ADMISIONHOSP_ID);
                            //
                            no = pstmt_insertAdmisionINP.executeUpdate();
                            log.finer("Insert Admision INP= " + no);
                            noInsert++;
                    	}
                    	else {
                    		 pstmt_updateAdmisionINP.setInt(1, I_EXME_INP_ADMISIONHOSP_ID);
                    		pstmt_updateAdmisionINP.setInt(2, EXME_INP_ADMISIONHOSP_ID);
                            //
                            no = pstmt_updateAdmisionINP.executeUpdate();
                            log.finer("Update Admision INP= " + no);
                            noUpdate++;
                    	}
                         
                    }
                    catch (Exception ex)
                    {
                        log.warning("Insert Municipios  - " + ex.toString());
                        sql = new StringBuffer ("UPDATE I_EXME_INP_ADMISIONHOSP_ID i "
                            + "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||")
                            .append(DB.TO_STRING("Insert admision inp " + ex.toString()))
                            .append("WHERE I_EXME_INP_ADMISIONHOSP_ID=").append(I_EXME_INP_ADMISIONHOSP_ID);
                        DB.executeUpdate(sql.toString());
                        continue;
                    }

                pstmt_setImported.setInt(1, I_EXME_INP_ADMISIONHOSP_ID);
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
    		if (pstmt_insertAdmisionINP != null)
    			pstmt_insertAdmisionINP.close();
    		if (pstmt_setImported != null)
    			pstmt_setImported.close();
    		if (pstmt_updateAdmisionINP != null)
    			pstmt_updateAdmisionINP.close();

    		pstmt_insertAdmisionINP= null;
    		pstmt_setImported= null;
    		pstmt_updateAdmisionINP= null;

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
     
        return "";
        
    }
    }