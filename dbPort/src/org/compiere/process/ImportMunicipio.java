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

import org.compiere.util.DB;
import org.compiere.util.Env;
import org.adempiere.exceptions.DBException;

/**
 *  Import Products from I_exme_esqdesqline
 *
 *  @author     Valentin Garcia
 *  @version    $Id: ImportConvenio.java,v 1.4 2006/11/02 02:40:56 vgarcia Exp $
 *  @version $Revision: $
 *
 * Modificado: $Date: $<p>
 * Por: $Author: $<p>
 */
public class ImportMunicipio extends SvrProcess
{
    /** Client to be imported to        */
    private int             m_AD_Client_ID = 0;
    /** Delete old Imported             */
    private boolean         m_deleteOldImported = false;

    /** Organization to be imported to  */
    private int             m_AD_Org_ID = 0;
    /** Effective                       */
    private Timestamp       m_DateValue = null;
    
    private int 	procesados=0;
    
    private int aProcesar=0;
    

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
    protected String doIt() throws java.lang.Exception {
        StringBuffer sql = null;
        int no = 0;
        String clientCheck = " AND AD_Client_ID=" + m_AD_Client_ID;

        //  ****    Prepare ****

        //  Delete Old Imported
        if (m_deleteOldImported) {
            sql = new StringBuffer ("DELETE I_EXME_TownCouncil "
                + "WHERE I_IsImported='Y'").append(clientCheck);
            no = DB.executeUpdate(sql.toString());
            log.info("Delete Old Impored =" + no);
        }

        //  Set Client, Org, IaActive, Created/Updated,     ProductType
        sql = new StringBuffer ("UPDATE I_EXME_TownCouncil "
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

         //  Set EXME_Region_ID
        sql = new StringBuffer ("UPDATE I_EXME_TownCouncil " +
        		"SET C_REGION_ID=(SELECT r.C_REGION_ID FROM C_REGION r " +
        		"WHERE TRIM(r.VALUE)=TRIM(I_EXME_TownCouncil.C_REGION_VALUE)) " +//JGARAY.- Cambio de campo Name a campo Value
        		"WHERE I_EXME_TownCouncil.C_REGION_ID IS NULL AND I_EXME_TownCouncil.I_IsImported<>'Y'");
            no = DB.executeUpdate(sql.toString());
            log.info("EXME_Region_ID=" + no);
            
        //  Set EXME_TownCouncil_ID
        sql = new StringBuffer ("UPDATE I_EXME_TownCouncil " +
        		"SET EXME_TownCouncil_ID=(SELECT m.EXME_TownCouncil_ID FROM EXME_TownCouncil m " +
        		"WHERE TRIM(m.Value)=TRIM(I_EXME_TownCouncil.Value) and m.C_REGION_ID=I_EXME_TownCouncil.C_REGION_ID ");
        
        if (DB.isOracle()) {
        	sql.append(" AND ROWNUM=1) ");
        } else if (DB.isPostgreSQL()) {
        	sql = new StringBuffer(DB.getDatabase().addPagingSQL(sql.toString(), 1, 1));
        	sql.append(") ");
        }
        
        sql.append(" WHERE I_EXME_TownCouncil.EXME_TownCouncil_ID IS NULL AND I_EXME_TownCouncil.I_IsImported<>'Y'");
        		
        no = DB.executeUpdate(sql.toString());
        log.info("EXME_TownCouncil_ID=" + no);
                           
     	  StringBuffer sqlRet = new StringBuffer ("UPDATE I_EXME_TownCouncil "
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
        no = DB.executeUpdate(sqlRet.toString());


        //  -------------------------------------------------------------------
        int noInsert = 0;
        int noUpdate = 0;
        //int noInsertPO = 0;
        //int noUpdatePO = 0;
        
        //  Go through Records
        log.fine("start inserting/updating ...");
        sql = new StringBuffer ("SELECT I_EXME_TownCouncil_ID,EXME_TownCouncil_ID " +
        		"FROM I_EXME_TownCouncil WHERE I_IsImported='N'");
        Connection conn = DB.createConnection(false, Connection.TRANSACTION_READ_COMMITTED);   
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
        	
        	
           /* PreparedStatement oldinsertMuni = conn.prepareStatement             
            ("INSERT INTO EXME_TownCouncil (EXME_TownCouncil_ID," +
            		"AD_Client_ID,AD_Org_ID,IsActive,Created, "+ 
            		" CreatedBy,Updated,UpdatedBy," +
            		"NAME,VALUE,DESCRIPTION,C_REGION_ID) " +
            		"SELECT ?, AD_Client_ID,AD_Org_ID,'Y'," +
            		"Sysdate,CreatedBy,Sysdate,UpdatedBy," +
            		"NAME,VALUE,DESCRIPTION,C_REGION_ID " +
            		"FROM I_EXME_TownCouncil " +
            		"WHERE I_EXME_TownCouncil_ID=?");            
            */
            /*                 
             pstmt_updateMunicipio.setInt(1, I_EXME_TownCouncil_ID);
    		pstmt_updateMunicipio.setInt(2, EXME_TownCouncil_ID);
            //
            no = pstmt_updateMunicipio.executeUpdate();
            log.finer("Update Municipios= " + no);
            noUpdate++;
                            
             */
        	//Para Oracle
        	String slSysdate="Sysdate";
        	
        	//Para PostgreSQL.
        	if (DB.isPostgreSQL()) {
        		slSysdate="current_date";
        	}
                            
        	// Para insertar los Registros Nuevos
            PreparedStatement pstmt_insertMunicipio = conn.prepareStatement             
            ("INSERT INTO EXME_TownCouncil (EXME_TownCouncil_ID," +
            		"AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy," +
            		"NAME,VALUE,DESCRIPTION,C_REGION_ID) " +
            		"SELECT ?, AD_Client_ID,AD_Org_ID,'Y'," + slSysdate + ",CreatedBy," + slSysdate + ",UpdatedBy," +
            		"NAME,VALUE,DESCRIPTION,C_REGION_ID FROM I_EXME_TownCouncil " +
            		"WHERE I_EXME_TownCouncil_ID=?");
            
            // Para actualizar los registros ya existentes (vgarcia)
            PreparedStatement pstmt_updateMunicipio = conn.prepareStatement             
            ("UPDATE EXME_TownCouncil SET(" +
            		"AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy," +
            		"NAME,VALUE,DESCRIPTION,C_REGION_ID)=" +
            		"(SELECT AD_Client_ID,AD_Org_ID,'Y'," + slSysdate + ",CreatedBy," + slSysdate + ",UpdatedBy," +
            		"NAME,VALUE,DESCRIPTION,C_REGION_ID FROM I_EXME_TownCouncil " +
            		"WHERE I_EXME_TownCouncil_ID=?) WHERE EXME_TownCouncil_ID=?");
                              
            
            //  Set Imported = Y
            PreparedStatement pstmt_setImported = conn.prepareStatement
                ("UPDATE I_EXME_TownCouncil SET I_IsImported='Y', "
                + "Updated=" + slSysdate + ", Processed='Y' WHERE I_EXME_TownCouncil_ID=?");

            //
            PreparedStatement pstmt = DB.prepareStatement(sql.toString(), null);
            //PreparedStatement ps = null;
            //ResultSet rs1 = null;
            rs = pstmt.executeQuery();
            int I_EXME_TownCouncil_ID = 0;   
            int  EXME_TownCouncil_ID = 0;
            //int id = 0;
            boolean nuevo = false;
            //oldinsertMuni=null;
            sqlRet = null;
            while (rs.next()) {
            	I_EXME_TownCouncil_ID = rs.getInt(1);   
            	EXME_TownCouncil_ID = rs.getInt(2);
                //id=0;
                nuevo = EXME_TownCouncil_ID == 0 ;

                    try {
                    	if (nuevo) {
                    		EXME_TownCouncil_ID = 0;
                    		EXME_TownCouncil_ID = DB.getNextID(m_AD_Client_ID, "EXME_TownCouncil", null);
                            if (EXME_TownCouncil_ID <= 0)
                                throw new DBException("No Next ID (" + EXME_TownCouncil_ID + ")");
                            pstmt_insertMunicipio.setInt(1, EXME_TownCouncil_ID);
                            pstmt_insertMunicipio.setInt(2, I_EXME_TownCouncil_ID);
                            //
                            no = pstmt_insertMunicipio.executeUpdate();
                            log.finer("Insert Municipios= " + no);
                            noInsert++;
                    	} else {
                    		pstmt_updateMunicipio.setInt(1, I_EXME_TownCouncil_ID);
                    		pstmt_updateMunicipio.setInt(2, EXME_TownCouncil_ID);
                            //
                            no = pstmt_updateMunicipio.executeUpdate();
                            log.finer("Update Municipios= " + no);
                            noUpdate++;
                            
                            /*
                            pstmt_updateMunicipio.setInt(1, I_EXME_TownCouncil_ID);
                    		pstmt_updateMunicipio.setInt(2, EXME_TownCouncil_ID);
                            //
                            no = pstmt_updateMunicipio.executeUpdate();
                            log.finer("Update Municipios= " + no);
                            noUpdate++;
                             */
                    	}
                         
                    } catch (Exception ex) {
                        log.warning("Insert Municipios  - " + ex.toString());
                        sql = new StringBuffer ("UPDATE I_EXME_TownCouncil i "
                            + "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||")
                            .append(DB.TO_STRING("Insert municipios " + ex.toString()))
                            .append("WHERE I_EXME_TownCouncil_ID=").append(I_EXME_TownCouncil_ID);
                        DB.executeUpdate(sql.toString());
                        continue;
                    }

                pstmt_setImported.setInt(1, I_EXME_TownCouncil_ID);
                no = pstmt_setImported.executeUpdate();
                //
                conn.commit();
            }
            rs.close();
            pstmt.close();

            //
            pstmt_insertMunicipio.close();
            pstmt_updateMunicipio.close();
            pstmt_setImported.close();
            //
            conn.close();
            conn = null;
        } catch (SQLException e) {
            try {
                if (conn != null)
                    conn.close();
                conn = null;
                if (rs != null)
                    rs.close();
                if (ps != null)
                    ps.close();
            } catch (SQLException ex) {
            	log.log(Level.SEVERE, "ImportarMunicipio.doIt while closing objects ", e);
            }
            log.log(Level.SEVERE, "doIt", e);

            throw new Exception ("doIt", e);
        } finally {
            if (conn != null)
                conn.close();

            if(rs != null)
                rs.close();
            if(ps != null)
                ps.close();

        }
     
        return "";
        
    }
}