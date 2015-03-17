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

import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.adempiere.exceptions.DBException;

/**
 *  Import Products from I_EXME_EsqDesqLine
 *
 *  @author     Ivan Vargas
 *  @version    $Id: ImportConvenio.java,v 1.4 2006/11/02 02:40:56 vgarcia Exp $
 *  @version $Revision: 1.4 $
 *
 * Modificado: $Date: 2006/11/02 02:40:56 $<p>
 * Por: $Author: vgarcia $<p>
 */
public class ImportConvenio extends SvrProcess {
    /** Client to be imported to        */
    private int             m_AD_Client_ID =0;
    /** Delete old Imported             */
    private boolean         m_deleteOldImported = false;

    /** Organization to be imported to  */
    private int             m_AD_Org_ID = 0;
    /** Effective                       */
    private Timestamp       m_DateValue = null;

    /**
     *  Prepare - e.g., get Parameters.
     */
    protected void prepare() {
        ProcessInfoParameter[] para = getParameter();
        for (int i = 0; i < para.length; i++) {
            String name = para[i].getParameterName();
            
            if (name.equals("AD_Client_ID")) {
                m_AD_Client_ID = ((BigDecimal)para[i].getParameter()).intValue();
            } else if (name.equals("AD_Org_ID")) {
				m_AD_Org_ID = ((BigDecimal)para[i].getParameter()).intValue();
            } else if (name.equals("DeleteOldImported")) {
                m_deleteOldImported = "Y".equals(para[i].getParameter());
            } else {
                log.log(Level.SEVERE, "Unknown Parameter: " + name);
            }
        }
        
        if (m_DateValue == null) {
            m_DateValue = DB.getTSForOrg(getCtx());
        }
    }   //  prepare
    


    /**
     *  Perrform process.            
     *  @return Message
     *  @throws Exception
     */
    protected String doIt() throws Exception {
        StringBuffer sql = null;
        int no = 0;
        String clientCheck = " AND AD_Client_ID=" + m_AD_Client_ID;
        
        //  ****    Prepare ****
        
        //  Delete Old Imported
        if (m_deleteOldImported) {
            sql = new StringBuffer ("DELETE I_EXME_Esqdesline ");
            sql.append("WHERE I_IsImported='Y'").append(clientCheck);
            no = DB.executeUpdate(sql.toString(), null);
            log.info("Delete Old Impored =" + no);
        }

        //  Set Client, Org, IaActive, Created/Updated,     ProductType
        sql = new StringBuffer ("UPDATE I_EXME_Esqdesline ");
        sql.append("SET AD_Client_ID = COALESCE (AD_Client_ID, ").append(m_AD_Client_ID).append("),");
        sql.append(" AD_Org_ID = COALESCE (AD_Org_ID, ").append(m_AD_Org_ID).append("),");
        sql.append(" IsActive = COALESCE (IsActive, 'Y'),");
        sql.append(" Created = COALESCE (Created, " + DB.getSysdate() +"),");
        sql.append(" CreatedBy = COALESCE (CreatedBy, 0),");
        sql.append(" Updated = COALESCE (Updated, " + DB.getSysdate() + "),");
        sql.append(" UpdatedBy = COALESCE (UpdatedBy, 0),");           
        sql.append(" I_ErrorMsg = NULL,");
        sql.append(" I_IsImported = 'N' ");
        sql.append("WHERE I_IsImported<>'Y' OR I_IsImported IS NULL");
        no = DB.executeUpdate(sql.toString(), null);
        log.info("Reset=" + no);

         //  Producto Categoria Opcional
        sql = new StringBuffer ("UPDATE I_EXME_Esqdesline  esq ");
        sql.append(" SET M_Product_Category_ID=(SELECT M_Product_Category_ID FROM M_Product_Category pc");
        sql.append(" WHERE esq.M_Product_Category_value=pc.Value AND (esq.AD_Client_ID = pc.AD_Client_ID or pc.AD_Client_ID=0)) ");
        sql.append(" WHERE M_Product_Category_ID IS NULL");
        sql.append(" AND I_IsImported<>'Y'").append(clientCheck);
        no = DB.executeUpdate(sql.toString(), null);
        log.info("Product_Category=" + no);
            
        //  Set Optional BPartner
        sql = new StringBuffer ("UPDATE I_EXME_Esqdesline  esq ");
        sql.append(" SET C_BPartner_ID=(SELECT C_BPartner_ID FROM C_BPartner p");
        sql.append("  WHERE esq.C_BPartner_Value=p.Value AND esq.AD_Client_ID=p.AD_Client_ID) ");
        sql.append(" WHERE C_BPartner_ID IS NULL");
        sql.append(" AND I_IsImported<>'Y'").append(clientCheck);
        no = DB.executeUpdate(sql.toString(), null);
        log.info("BPartner=" + no);
                    
            
        //  M_product_id Opcional
        sql = new StringBuffer ("UPDATE I_EXME_Esqdesline  esq ");
        sql.append("SET M_Product_ID=(SELECT M_Product_ID FROM M_Product p ");
        sql.append(" WHERE esq.M_Product_Value=p.Value AND esq.AD_Client_ID=p.AD_Client_ID) ");
        sql.append("WHERE M_Product_ID IS NULL");
        sql.append(" AND I_IsImported<>'Y'").append(clientCheck);
        no = DB.executeUpdate(sql.toString(), null);
        log.info("Product =" + no);
                
                
        // tipo Area opcional
        sql = new StringBuffer ("UPDATE I_EXME_Esqdesline  esq ");
        sql.append("SET TipoArea=(SELECT TipoArea FROM EXME_Area  a ");
        sql.append(" WHERE esq.EXME_Area_Value=a.Value AND esq.AD_Client_ID=a.AD_Client_ID) ");
        sql.append("WHERE TipoArea IS NULL");
        sql.append(" AND I_IsImported<>'Y'").append(clientCheck);
        no = DB.executeUpdate(sql.toString(), null);
        log.info("Area =" + no);
            
            
//                  Area opcional
        sql = new StringBuffer ("UPDATE I_EXME_Esqdesline  esq ");
        sql.append("SET EXME_Area_ID=(SELECT EXME_Area_ID FROM EXME_Area  a ");
        sql.append(" WHERE esq.EXME_Area_Value=a.Value AND esq.AD_Client_ID=a.AD_Client_ID) ");
        sql.append("WHERE EXME_Area_ID IS NULL");
        sql.append(" AND I_IsImported<>'Y'").append(clientCheck);
        no = DB.executeUpdate(sql.toString(), null);
        log.info("Area =" + no);
        
        
        //  C_bpgroup opcional
        sql = new StringBuffer ("UPDATE I_EXME_Esqdesline  esq ");
        sql.append("SET C_BP_Group_ID=(SELECT C_BP_Group_ID FROM C_BP_Group  bpg ");
        sql.append(" WHERE esq.C_BP_Group_Value=bpg.Value AND esq.AD_Client_ID=bpg.AD_Client_ID) ");
        sql.append("WHERE C_BP_Group_ID IS NULL");
        sql.append(" AND I_IsImported<>'Y'").append(clientCheck);
        no = DB.executeUpdate(sql.toString(), null);
        log.info(" C_BP_Group =" + no);
            
                        
        // Unidad de Medida opcional
        sql = new StringBuffer ("UPDATE I_EXME_Esqdesline  esq ");
        sql.append("SET C_Uom_ID=(SELECT C_Uom_ID FROM C_Uom  bpg ");
        sql.append(" WHERE esq.x12de355=bpg.x12de355 AND esq.AD_Client_ID=bpg.AD_Client_ID) ");
        sql.append("WHERE C_Uom_ID IS NULL");
        sql.append(" AND I_IsImported<>'Y'").append(clientCheck);
        no = DB.executeUpdate(sql.toString(), null);
        log.info(" C_Uom =" + no);
            
                 
        // Termina la busqueda de registros existentes
            
     
        //  -------------------------------------------------------------------
        int noInsert = 0;
        int noUpdate = 0;
   
        int noErrores=0;
        //  Go through Records
        log.fine("start inserting/updating ...");
        sql = new StringBuffer ("SELECT I_EXME_Esqdesline_ID, EXME_Esqdesline_ID,C_BPartner_ID ");
        sql.append("FROM I_EXME_Esqdesline WHERE I_IsImported='N'").
            append(clientCheck).append(" order by I_EXME_ESQDESLINE_ID");
        Connection conn = DB.createConnection(false, Connection.TRANSACTION_READ_COMMITTED);    
        
        ResultSet rs = null;
        ResultSet rs1 = null;
        PreparedStatement ps = null;
        PreparedStatement pstmt = null;
        PreparedStatement pstmt_setImported = null;
        PreparedStatement pstmt_updateEsqdesline2 = null;
        PreparedStatement pstmt_insertEsqdesline = null;
        
        try {
            pstmt_insertEsqdesline = conn.prepareStatement             
            ("INSERT INTO EXME_EsqDesLine (EXME_EsqDesLine_ID,"
               + "AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,"
               + "ValidFrom,TipoArea,M_Product_Category_ID,List_Discount,List_AddAmt,EXME_Area_ID,C_BPartner_id,validto,M_Product_ID,C_Uom_ID )"
               + " SELECT ?,"
               + "AD_Client_ID,AD_Org_ID,'Y'," + DB.getSysdate() + ",CreatedBy, " + DB.getSysdate() + ",UpdatedBy,"
               + "ValidFrom,TipoArea,M_Product_Category_ID,List_Discount,List_AddAmt,EXME_Area_ID,C_BPartner_id,validto,M_Product_ID,C_Uom_ID "
               + "FROM I_EXME_Esqdesline WHERE I_EXME_Esqdesline_ID=?");
            
            // Para los updates (vgarcia)
            final StringBuilder  sbUpdateEsqdesline = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
            sbUpdateEsqdesline.append("UPDATE EXME_EsqDesLine SET ");
            sbUpdateEsqdesline.append("AD_Client_ID = I_EXME_Esqdesline.AD_Client_ID, ");
            sbUpdateEsqdesline.append("AD_Org_ID = I_EXME_Esqdesline.AD_Org_ID, ");
            sbUpdateEsqdesline.append("IsActive = 'Y', ");
            sbUpdateEsqdesline.append("Created = ").append(DB.getSysdate()).append(", ");
            sbUpdateEsqdesline.append("CreatedBy = I_EXME_Esqdesline.CreatedBy, ");
            sbUpdateEsqdesline.append("Updated = ").append(DB.getSysdate()).append(", ");
            sbUpdateEsqdesline.append("UpdatedBy = I_EXME_Esqdesline.UpdatedBy, ");
            sbUpdateEsqdesline.append("ValidFrom = I_EXME_Esq            desline.ValidFrom, ");
            sbUpdateEsqdesline.append("TipoArea = I_EXME_Esqdesline.TipoArea, ");
            sbUpdateEsqdesline.append("M_Product_Category_ID = I_EXME_Esqdesline.M_Product_Category_ID, ");
            sbUpdateEsqdesline.append("List_Discount = I_EXME_Esqdesline.List_Discount, ");
            sbUpdateEsqdesline.append("List_AddAmt = I_EXME_Esqdesline.List_AddAmt, ");
            sbUpdateEsqdesline.append("EXME_Area_ID = I_EXME_Esqdesline.EXME_Area_ID, ");
            sbUpdateEsqdesline.append("C_BPartner_id = I_EXME_Esqdesline.C_BPartner_id, ");
            sbUpdateEsqdesline.append("validto = I_EXME_Esqdesline.validto, ");
            sbUpdateEsqdesline.append("M_Product_ID = I_EXME_Esqdesline.M_Product_ID, ");
            sbUpdateEsqdesline.append("C_Uom_ID = I_EXME_Esqdesline.C_Uom_ID ");
            sbUpdateEsqdesline.append("FROM I_EXME_Esqdesline ");
            sbUpdateEsqdesline.append("WHERE I_EXME_Esqdesline.I_EXME_Esqdesline_ID = ? ");
            sbUpdateEsqdesline.append("AND EXME_EsqDesLine.EXME_ESQDESLINE_ID = ? ");
            
            pstmt_updateEsqdesline2 = conn.prepareStatement(sbUpdateEsqdesline.toString());
            
                              
            
            //  Set Imported = Y and put the EXME_Esqdesline_ID
            pstmt_setImported = conn.prepareStatement
                ("UPDATE I_EXME_Esqdesline SET I_IsImported='Y', "
                + "Updated=" + DB.getSysdate() + ", Processed='Y', EXME_Esqdesline_ID = ? WHERE I_EXME_Esqdesline_ID = ?");

            //
            pstmt = DB.prepareStatement(sql.toString(), null);
            
            int I_EXME_Esqdesline_ID = 0;   
            int  EXME_Esqdesline_ID = 0;
            int id = 0;
            
            boolean nuevo = false;
            
            rs = pstmt.executeQuery();
            
            while (rs.next()) {
                I_EXME_Esqdesline_ID = rs.getInt(1);   
                EXME_Esqdesline_ID = rs.getInt(2);
                id = 0;
                
//              Este segmento de codigo se coloco aqui para evitar registros repetidos (vgarcia)
                sql = new StringBuffer("select esq.exme_esqdesline_id as id from exme_esqdesline esq, i_exme_esqdesline i ");
                		sql.append("where (esq.c_bpartner_id = i.c_bpartner_id or (esq.c_bpartner_id is null and i.c_bpartner_id is null)) ");
                		sql.append("and (esq.m_product_category_id = i.m_product_category_id or (esq.m_product_category_id is null and ");
                		sql.append("i.m_product_category_id is null)) ");
                		sql.append("and (esq.m_product_id = i.m_product_id or (esq.m_product_id is null and i.m_product_id is null)) ");
                		sql.append("and (esq.c_uom_id = i.c_uom_id or (esq.c_uom_id is null and i.c_uom_id is null)) ");
                		sql.append("and (esq.c_bp_group_id = i.c_bp_group_id or (esq.c_bp_group_id is null and i.c_bp_group_id is null)) ");
                		sql.append("and (esq.exme_area_id = i.exme_area_id or (esq.exme_area_id is null and i.exme_area_id is null)) ");
                		sql.append("and (esq.tipoarea = i.tipoarea or (esq.tipoarea is null and i.tipoarea is null)) ");
                		sql.append("and esq.isactive = 'Y' and i.i_exme_esqdesline_id = ?");
                
                ps = DB.prepareStatement(sql.toString(), null);
                ps.setInt(1,I_EXME_Esqdesline_ID);
                rs1 = ps.executeQuery();
                
                while (rs1.next()) {
                	id = rs1.getInt(1);
                }
                
                if (id < 1) {
                	nuevo = true;
                }
                
                // Segmento para evitar Repetidos
                
                if (I_EXME_Esqdesline_ID != 0) { 
                
                    try {
                    	if (nuevo) {
                    		EXME_Esqdesline_ID = DB.getNextID(m_AD_Client_ID, "EXME_EsqDesLine", null);
                            if (EXME_Esqdesline_ID <= 0) {
                                throw new DBException("No Next ID (" + EXME_Esqdesline_ID + ")");
                            }
                            
                            pstmt_insertEsqdesline.setInt(1, EXME_Esqdesline_ID);
                            pstmt_insertEsqdesline.setInt(2, I_EXME_Esqdesline_ID);

                            no = pstmt_insertEsqdesline.executeUpdate();
                            log.finer("Insert Convenios= " + no);
                            noInsert++;
                            
                    	} else {
                    		pstmt_updateEsqdesline2.setInt(1, I_EXME_Esqdesline_ID);
                    		pstmt_updateEsqdesline2.setInt(2, id);
                    		no = pstmt_updateEsqdesline2.executeUpdate();
                            log.finer("Update Convenios= " + no);
                            noUpdate++;
                    	}
                         
                    } catch (Exception ex) {
                    
                    	log.log(Level.SEVERE, "ImportConvenio: Error Inserting Convenios  - ", ex);
                        sql = new StringBuffer ("UPDATE I_EXME_Esqdesline i ");
                        sql.append("SET I_IsImported='E', I_ErrorMsg=COALESCE (I_ErrorMsg, '') ||")
                            .append(DB.TO_STRING("Insert convenios " + ex.toString()))
                            .append("WHERE I_EXME_Esqdesline_ID=").append(I_EXME_Esqdesline_ID);
                        DB.executeUpdate(sql.toString(), null);
                        noErrores++;
                        continue;
                    }
                } else { 
	                if (EXME_Esqdesline_ID == 0) {
	                    sql = new StringBuffer ("UPDATE I_EXME_Esqdesline i ");
	                    sql.append("SET I_IsImported='E', I_ErrorMsg=COALESCE (I_ErrorMsg, '') || " + "No existe esquema de descuento para el Cliente_ID=" + m_AD_Client_ID)
	                       .append(" WHERE EXME_ESQDESLINE_ID is null ");
	                    DB.executeUpdate(sql.toString(), null);

	            		addLog (0, null, new BigDecimal (1), "Error: No existe esquema de descuento para el cliente: "+  clientCheck);
	            	
	                }
                }
                
                pstmt_setImported.setInt(1, EXME_Esqdesline_ID);
                pstmt_setImported.setInt(2, I_EXME_Esqdesline_ID);
                no = pstmt_setImported.executeUpdate();
               
                conn.commit();
            }

        } catch (SQLException e) {
            log.log(Level.SEVERE, "ImportConvenio.doIt", e);
            throw new Exception ("doIt", e);
        } finally {
        	
            if (rs != null) {
                rs.close();
            }
            
            if (rs1 != null) {
                rs1.close();
            }
            
            if (ps != null) {
                ps.close();
            }
            
            if (pstmt != null) {
            	pstmt.close();
            }
            
            if (pstmt_setImported != null) {
            	pstmt_setImported.close();
            }
            
            if (pstmt_updateEsqdesline2 != null) {
            	pstmt_updateEsqdesline2.close();
            }
            
            if (pstmt_insertEsqdesline != null) {
            	pstmt_insertEsqdesline.close();
            }
                    
            if (conn != null) {
                conn.close();
            }
        }
        
    	addLog (0, null, new BigDecimal (noErrores), "@Errors@");
		addLog (0, null, new BigDecimal (noInsert), "@EXME_EsqDesLine@: @Inserted@");
		addLog (0, null, new BigDecimal (noUpdate), "@EXME_EsqDesLine@: @Updated@");
        
        return "";
    }
}