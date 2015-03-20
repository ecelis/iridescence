 /*
 * Created on 13/09/2005
 *
 */
package org.compiere.process;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;

import org.compiere.model.MEXMEActPacienteIndH;
import org.compiere.model.MEXMEActPacienteInd;

/**
 * <b>Prop&oacute;sito : </b> <p>
 * <b>Creado     : </b> 13/09/2005 <p>
 * <b>Por        : </b> alberto reyes<p>
 * <b>Modificado : </b> $Date: 2006/02/20 22:40:01 $ <p>
 * <b>Por        : </b> $Author: mrojas $ <p>
 *
 * @author alberto reyes
 * @version $Revision: 1.1 $
 */
public class InterfazIndH extends InterfazSvrProcess {

    /**
     * 
     */
    public InterfazIndH() {
        super();
    }
    
    /* (non-Javadoc)
     * @see org.compiere.process.SvrProcess#prepare()
     */
    protected void prepare() {
        
        super.prepare();

    }
    
    /* (non-Javadoc)
     * @see org.compiere.process.SvrProcess#doIt()
     */
    protected String doIt() throws Exception {
        
        log.info("doIt()");
        
        String sql = null;
        
        StringBuffer insert_values = new StringBuffer();
        
        int total_rows = 0;
        
        try{
            
            /*
             * Obtenemos los registros que faltan ser tomados y pasados a Progress.
             */
            Statement stmt =  cnn_ora.createStatement();
            sql = "SELECT EXME_ActPacienteIndH_ID FROM EXME_Interfaz_IndH WHERE Actualizado = 'N'";
            ResultSet rs = stmt.executeQuery(sql);
            
            while(rs.next()){
                
                //ID del registro generado
                //obtine el registro de interfaz.
                int record_id = 0;
                
                record_id = rs.getInt("EXME_ActPacienteIndH_ID");
                
                // Recuperamos el registro de Oracle para pasarlo a Progress.
                MEXMEActPacienteIndH actPacienteIndH = new MEXMEActPacienteIndH(getCtx(), record_id, trx_ora);
                
                
                insert_values.append("'MSM',");//in-entity
                
                String warehouse_Sol = null;
                
                sql = "SELECT w.value FROM AD_Org o " +
                	"INNER JOIN EXME_EstServ e ON (o.AD_org_id = e.ad_orgtrx_id) " +
                	"INNER JOIN EXME_EstServAlm ea ON (e.exme_estserv_id = ea.exme_estservalm_id) " + 
                	"INNER JOIN M_Warehouse w ON (w.m_warehouse_id = ea.m_warehouse_id) " +
                	"WHERE o.ad_org_id = " + actPacienteIndH.getAD_OrgTrx_ID();
                Statement stmt_qry = cnn_ora.createStatement();
                ResultSet rs_qry = stmt_qry.executeQuery(sql);
                if(rs_qry.next())
                    warehouse_Sol = rs_qry.getString(1);
                
                rs_qry.close();
                stmt_qry.close();
                rs_qry = null;
                stmt_qry = null;
                
                if(warehouse_Sol != null){
                    insert_values.append("'" + warehouse_Sol + "',");//whs-solicitud
                    insert_values.append("'" + warehouse_Sol + "',");//whs-resurtido
                    insert_values.append("'" + warehouse_Sol + "',");//whs-ctrl
                }else{
                    insert_values.append("NULL,");//whs-solicitud
                    insert_values.append("NULL,");//whs-resurtido
                    insert_values.append("NULL,");//whs-ctrl
                }
                
                insert_values.append(actPacienteIndH.getDocumentNo() + ",");//no-inter-po
                
                insert_values.append("NULL,");//no-transf
                
                insert_values.append("'" + sdf2.format(actPacienteIndH.getDateOrdered())  + "',");//trans-date
                insert_values.append("'" + sdf2.format(actPacienteIndH.getDateOrdered())  + "',");//request-date
                insert_values.append("'" + sdf2.format(actPacienteIndH.getDatePromised())  + "',");//promise-date
                
                insert_values.append("NULL,");//control-qty
                insert_values.append("NULL,");//last-rcpt-no
                insert_values.append("NULL,");//last-rc-date
                
                insert_values.append("'11',");//chang-cancel
                
                sql = "SELECT u.name FROM AD_User u " +
	            	"WHERE u.ad_user_id = " + actPacienteIndH.getCreatedBy();
                stmt_qry = cnn_ora.createStatement();
                rs_qry = stmt_qry.executeQuery(sql);
                if(rs_qry.next())
                    insert_values.append("'" + rs_qry.getString(1) + "',");//user-id
                else
                    insert_values.append("NULL,");//user-id
                
                rs_qry.close();
                stmt_qry.close();
                rs_qry = null;
                stmt_qry = null;
                
                insert_values.append("NULL,");//terminal-no
                insert_values.append("NULL,");//cancel-date
                insert_values.append("NULL,");//seq-no
                
                sql = "SELECT u.name FROM AD_User u " +
	            	"WHERE u.ad_user_id = " + actPacienteIndH.getUpdatedBy();
	            stmt_qry = cnn_ora.createStatement();
	            rs_qry = stmt_qry.executeQuery(sql);
	            if(rs_qry.next())
	                insert_values.append("'" + rs_qry.getString(1) + "',");//despachador
	            else
	                insert_values.append("NULL,");//despachador
	            
	            rs_qry.close();
                stmt_qry.close();
                rs_qry = null;
                stmt_qry = null;
	            
	            insert_values.append("NULL,");//no-whs-sol
	            
	            sql = "SELECT w.value FROM M_Warehouse w " +
		        	"WHERE w.m_warehouse_id = " + actPacienteIndH.getM_Warehouse_ID();
		        stmt_qry = cnn_ora.createStatement();
		        rs_qry = stmt_qry.executeQuery(sql);
		        if(rs_qry.next())
		            insert_values.append("" + rs_qry.getString(1) + ",");//no-whs-res
		        else
		            insert_values.append("NULL,");//no-whs-res
		        
		        rs_qry.close();
                stmt_qry.close();
                rs_qry = null;
                stmt_qry = null;
		        
		        insert_values.append("NULL,");//inter-cerrada
		        
		        String priority = actPacienteIndH.getPriorityRule();
		        
		        if(!priority.equalsIgnoreCase("3"))
		            insert_values.append("0,");//prioridad
		        else
		            insert_values.append("1,");//prioridad
		        
		        sql = "SELECT o.value FROM AD_Org o " +
		        	"WHERE o.ad_org_id = " + actPacienteIndH.getAD_OrgTrx_ID();
		        stmt_qry = cnn_ora.createStatement();
		        rs_qry = stmt_qry.executeQuery(sql);
		        if(rs_qry.next())
		            insert_values.append("" + rs_qry.getString(1) + ",");//ar-entity
		        else
		            insert_values.append("NULL,");//ar-entity
		        
		        rs_qry.close();
                stmt_qry.close();
                rs_qry = null;
                stmt_qry = null;
		        
		        insert_values.append("NULL,");//order-no
		        
		        insert_values.append("NULL,");//applied
		        
		        insert_values.append("NULL,");//send-time
		        
		        insert_values.append("'" + sdf2.format(actPacienteIndH.getCreated())  + "',");//send-date
		        
		        insert_values.append("NULL,");//backward-time
		        
		        insert_values.append("NULL,");//backward-date
		        
		        insert_values.append("NULL,");//receipt-time
		        
		        insert_values.append("'" + sdf2.format(actPacienteIndH.getDateDelivered())  + "',");//receipt-date
		        
		        insert_values.append("NULL,");//filler1
		        insert_values.append("NULL,");//filler2
		        insert_values.append("NULL,");//filler3
		        insert_values.append("NULL,");//filler4
		        insert_values.append("NULL,");//filler5
		        insert_values.append("NULL,");//filler6
		        insert_values.append("NULL,");//filler7
		        insert_values.append("NULL,");//filler8
		        insert_values.append("NULL,");//filler9
		        insert_values.append("NULL,");//filler10
		        insert_values.append("NULL,");//filler11
		        insert_values.append("NULL,");//filler12
		        insert_values.append("NULL,");//filler13
		        insert_values.append("NULL,");//filler14
		        insert_values.append("NULL,");//filler15
		        insert_values.append("NULL,");//filler16
		        insert_values.append("NULL,");//filler17
		        
		        insert_values.append("NULL,");//user-autorizo
		        
		        sql = "SELECT m.value FROM AD_User u " + 
		        	"INNER JOIN EXME_Medico m ON (u.ad_user_id = m.ad_user_id) " + 
		        	"WHERE u.ad_user_id = " + actPacienteIndH.getCreatedBy(); 
		        stmt_qry = cnn_ora.createStatement();
		        rs_qry = stmt_qry.executeQuery(sql);
		        if(rs_qry.next())
		            insert_values.append("" + rs_qry.getString(1) + ")");//medico
		        else
		            insert_values.append("NULL)");//medico
		        
		        rs_qry.close();
                stmt_qry.close();
                rs_qry = null;
                stmt_qry = null;
                
                //Sentencia de Insercion en la tabla Inter-PO de Progress
                sql = "INERT INTO \"PUB\".\"Inter-PO\" (in-entity, whs-solicitud, whs-resurtido, whs-ctrl," +
                		"no-inter-po, no-transf, trans-date, request-date, promise-date, control-qty, last-rcpt-no, " +
                		"last-rc-date, chang-cancel, user-id, terminal-no, cancel-date, seq-no, despachador, no-whs-sol, no-whs-res, " +
                		"inter-cerrada, prioridad, ar-entity, order-no, applied, send-time, send-date, backward-time, backward-date," +
                		"receipt-time, receipt-date, filler1, filler2, filler3, filler4, filler5, filler6, filler7, filler8, filler9, filler10, filler11, " +
                		"filler12, filler13, filler14, filler15, filler16, filler17, user-autorizo, medico) VALUES (";
                
                sql += insert_values.toString();
                
                Statement stmt_ins = cnn_pgs.createStatement();
                int inserted = stmt_ins.executeUpdate(sql);
                if(inserted < 1){
                    m_errormsg.append("Solicitud (ActPacienteIndH --> Inter-Po) was not Inserted. /n");
                }else{
                    
                    //Detalle del Pedido.
                    insert_values = new StringBuffer();
                    
                    MEXMEActPacienteInd[] lines = actPacienteIndH.getLineas();
                    
                    for(int i = 0; i < lines.length; i++){
                        
                        MEXMEActPacienteInd line = lines[i];
                        
                        insert_values.append("'MSM',");//in-entity
                        
                        String warehouse_Sol_line = null;
                        
                        sql = "SELECT w.value FROM AD_Org o " +
                        	"INNER JOIN EXME_EstServ e ON (o.AD_org_id = e.ad_orgtrx_id) " +
                        	"INNER JOIN EXME_EstServAlm ea ON (e.exme_estserv_id = ea.exme_estservalm_id) " + 
                        	"INNER JOIN M_Warehouse w ON (w.m_warehouse_id = ea.m_warehouse_id) " +
                        	"WHERE o.ad_org_id = " + line.getAD_OrgTrx_ID();
                        stmt_qry = cnn_ora.createStatement();
                        rs_qry = stmt_qry.executeQuery(sql);
                        if(rs_qry.next())
                            warehouse_Sol_line = rs_qry.getString(1);
                        
                        rs_qry.close();
                        stmt_qry.close();
                        rs_qry = null;
                        stmt_qry = null;
                        
                        if(warehouse_Sol_line != null){
                            insert_values.append("'" + warehouse_Sol + "',");//whs-ctrl
                        }else{
                            insert_values.append("NULL,");//whs-ctrl
                        }
                        
                        insert_values.append(actPacienteIndH.getDocumentNo() + ",");//no-inter-po
                        
                        insert_values.append(line.getLine() + ",");//line-no
                        
                        insert_values.append("'" + sdf2.format(actPacienteIndH.getDateOrdered())  + "',");//trans-date
                        insert_values.append("'" + sdf2.format(actPacienteIndH.getDateOrdered())  + "',");//request-date
                        insert_values.append("'" + sdf2.format(actPacienteIndH.getDatePromised())  + "',");//promise-date
                        
                        insert_values.append("NULL,");//last-rcpt-no
                        insert_values.append("NULL,");//last-rc-date
                        insert_values.append("NULL,");//last-rc-qty
                        insert_values.append("NULL,");//no-transf
                        
                        insert_values.append("'11',");//chang-cancel
                        
                        sql = "SELECT u.name FROM AD_User u " +
	    	            	"WHERE u.ad_user_id = " + actPacienteIndH.getUpdatedBy();
	    	            stmt_qry = cnn_ora.createStatement();
	    	            rs_qry = stmt_qry.executeQuery(sql);
	    	            if(rs_qry.next())
	    	                insert_values.append("'" + rs_qry.getString(1) + "',");//user-id
	    	            else
	    	                insert_values.append("NULL,");//user-id
	    	            
	    	            rs_qry.close();
                        stmt_qry.close();
                        rs_qry = null;
                        stmt_qry = null;
                        
	    	            insert_values.append("NULL,");//terminal-no
	    	            insert_values.append("NULL,");//cancel-date
	    	            insert_values.append("NULL,");//seq-no
	    	            
	    	            sql = "SELECT p.value FROM M_Product p " +
	    	            	"WHERE p.m_product_id = " + line.getM_Product_ID();
	    	            stmt_qry = cnn_ora.createStatement();
	    	            rs_qry = stmt_qry.executeQuery(sql);
	    	            if(rs_qry.next())
	    	                insert_values.append("'" + rs_qry.getString(1) + "',");//item-no
	    	            else
	    	                insert_values.append("NULL,");//item-no
	    	            
	    	            rs_qry.close();
                        stmt_qry.close();
                        rs_qry = null;
                        stmt_qry = null;
	    	            
	    	            insert_values.append(line.getQtyOrdered() + ",");//qty-orig-ord
	    	            insert_values.append(line.getQtyDelivered() + ",");//qty-received
	    	            insert_values.append("NULL,");//qty-to-rcve
	    	            insert_values.append(line.getQtyOrdered() + ",");//qty-order
	    	            
	    	            insert_values.append("NULL,");//user-id-resur
	    	            insert_values.append("NULL,");//terminal-id-res
	    	            
	    	            sql = "SELECT u.uomsymbol FROM C_UOM u " +
	    	            	"WHERE u.c_uom_id = " + line.getC_UOM_ID();
	    	            stmt_qry = cnn_ora.createStatement();
	    	            rs_qry = stmt_qry.executeQuery(sql);
	    	            if(rs_qry.next())
	    	                insert_values.append("'" + rs_qry.getString(1) + "',");//uom-code
	    	            else
	    	                insert_values.append("NULL,");//uom-code
	    	            
	    	            rs_qry.close();
                        stmt_qry.close();
                        rs_qry = null;
                        stmt_qry = null;
	    	            
	    	            insert_values.append("NULL,");//h-code
	    	            
	    	            insert_values.append(line.getQtyDelivered() + ",");//qty-shipped
	    	            insert_values.append(line.getQtyDelivered() + ",");//qty-tot-shipped
	    	            insert_values.append(line.getQtyDelivered() + ",");//qty-to-ship
	    	            
	    	            insert_values.append("NULL,");//prioridad
	    	            insert_values.append("NULL,");//qty-back
	    	            
	    	            insert_values.append(line.getCosto() + ",");//cost
	    	            
	    	            insert_values.append("NULL,");//filler1
	    		        insert_values.append("'" + line.getAnotaciones() + "',");//filler2
	    		        insert_values.append("NULL,");//filler3
	    		        insert_values.append("NULL,");//filler4
	    		        insert_values.append("NULL,");//filler5
	    		        insert_values.append("NULL,");//filler6
	    		        
	    		        sql = "SELECT p.value FROM EXME_actpaciente a " +
							"JOIN EXME_Paciente p ON (p.exme_paciente_id = a.exme_paciente_id) " +
							"WHERE a.exme_actpaciente_id = " + actPacienteIndH.getEXME_ActPaciente_ID(); 
	    	            stmt_qry = cnn_ora.createStatement();
	    	            rs_qry = stmt_qry.executeQuery(sql);
	    	            if(rs_qry.next())
	    	                insert_values.append("'" + rs_qry.getString(1) + "',");//filler7
	    	            else
	    	                insert_values.append("NULL,");//filler7
	    	            
	    	            rs_qry.close();
                        stmt_qry.close();
                        rs_qry = null;
                        stmt_qry = null;
	    		        
	    		        insert_values.append("NULL,");//filler8
	    		        insert_values.append("NULL,");//filler9
	    		        insert_values.append("NULL,");//filler10
	    		        insert_values.append("NULL,");//filler11
	    		        
	    		        insert_values.append("NULL,");//int-filler1
	    		        insert_values.append("NULL,");//int-filler2
	    		        insert_values.append("NULL,");//int-filler3
	    		        insert_values.append("NULL)");//int-filler4
                        
                        sql = "INERT INTO \"PUB\".\"Inter-PO\" (in-entity, whs-ctrl, no-inter-po, line-no, " +
	                		"trans-date, request-date, promise-date, last-rcpt-no, last-rc-date, last-rc-qty, " +
	                		"no-transf, chang-cancel, user-id, terminal-no, cancel-date, seq-no, item-no, " +
	                		"qty-orig-ord, qty-received, qty-to-rcve, qty-order, user-id-resur, terminal-id-res, " +
	                		"uom-code, h-code, qty-shipped, qty-tot-shipped, qty-to-ship, prioridad, qty-back, cost, " +
	                		"filler1, filler2, filler3, filler4, filler5, filler6, filler7, filler8, filler9, filler10, filler11, " +
	                		"int-filler1, int-filler2, int-filler3, int-filler4) VALUES (";
                        
                        sql += insert_values.toString();
                        
                        Statement stmt_ins_line = cnn_pgs.createStatement();
                        int inserted_lines = stmt_ins_line.executeUpdate(sql);
                        if(inserted_lines < 1){
                            m_errormsg.append("Linea de Solicitud " + actPacienteIndH.getDocumentNo() + "(ActPacienteIndH --> Inter-Po) was not Inserted. /n");
                        }
                        
                        stmt_ins_line.close();
                        stmt_ins_line = null;
                        
                    }//lines
                    
                }//header was inserted.
                
                stmt_ins.close();
                stmt_ins = null;
                
                
                if(m_errormsg.length() <= 0 && record_id > 0){
	                //Actualizamos el estatus de la tabla e Interfaz para el registro actual.
	                Statement stmt_upd = cnn_ora.createStatement();
	                sql = "UPDATE EXME_Interfaz_IndH SET Actualizado = 'Y' WHERE EXME_ActPacienteIndH_ID " + record_id
	                	+ " AND " + clientOrg;
	                int updated = stmt_upd.executeUpdate(sql);
	                if(updated > 0){	                    
	                    total_rows++;
	                    log.log(Level.INFO, updated + " rows updated.");
	                    m_textmsg.append("RowUpdated=" + record_id + ".\n");
	                    
	                }else{
	                    m_errormsg.append("Solicitud (ActPacienteIndH - Inter-Po) was not Updated. /n");
	                }
                    stmt_upd.close();
                    stmt_upd = null;
                }else{
                    break;
                }
                
                
            }//rs.
            
            rs.close();
            rs = null;
            
        }catch (SQLException sqle) {
            
            m_errormsg.append("Error: " + sqle.getMessage());
            sqle.printStackTrace();
            
        }finally{
            
            if(m_errormsg.length() > 0)
                rollback();
            else
                commit();
            
            super.doLog(total_rows);
            super.doIt();
            close();
        }
        
        return null;
    }

}
