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

 * Modificado: $Date: 2006/05/18 21:48:37 $<p>

 * Por: $Author: gisela $<p>

 * 

 * @author mrojas

 * @version $Revision: 1.1 $

 */

public class ImportAltasVolunt extends SvrProcess {

    

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

    public ImportAltasVolunt() {

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

            sql = new StringBuffer ("DELETE I_EXME_AltasVolunt "

                + "WHERE I_IsImported='Y'").append(clientCheck);

            no = DB.executeUpdate(sql.toString());

            log.info("Delete Old Impored =" + no);

        }



        //  Set Client, Org, IaActive, Created/Updated,

        sql = new StringBuffer ("UPDATE I_EXME_AltasVolunt "

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

        sql = new StringBuffer ("UPDATE I_EXME_AltasVolunt i "

            + "SET EXME_Paciente_ID = (SELECT p.EXME_Paciente_ID FROM EXME_Paciente p WHERE "

            + "p.Value = i.EXME_Paciente_Value " 
            
            + MEXMELookupInfo.addAccessLevelSQL(getCtx(), " ", MEXMEPaciente.Table_Name, "p")
            
            + ") "

            + "WHERE I_IsImported<>'Y'");

        no = DB.executeUpdate(sql.toString());

        log.fine("Set Exme_Paciente_ID=" + no);



        //  Set Exme_Cama_ID

        sql = new StringBuffer ("UPDATE I_EXME_AltasVolunt i "

            + "SET EXME_Cama_ID = (SELECT EXME_Cama_ID FROM EXME_Cama p WHERE "

            + " p.AD_Client_ID=i.AD_Client_ID  AND p.Value = i.codigocama) "

            + "WHERE I_IsImported<>'Y'").append(clientCheck);

        no = DB.executeUpdate(sql.toString());

        log.fine("Set Exme_Cama_ID=" + no);



        

        //  Set Exme_Medico_ID

       /* sql = new StringBuffer ("UPDATE I_EXME_AltasVolunt i "

            + "SET EXME_Medico_ID = (SELECT EXME_Medico_ID FROM EXME_Medico p WHERE "

            + " p.AD_Client_ID=i.AD_Client_ID  AND p.Value = i.codigomed) "

            + "WHERE I_IsImported<>'Y'").append(clientCheck);*/
        sql = new StringBuffer ("UPDATE I_EXME_AltasVolunt i "

                + "SET EXME_Medico_ID = (SELECT EXME_Medico_ID FROM EXME_Medico p WHERE "
                + " p.Value = i.codigomed "      
                + MEXMELookupInfo.addAccessLevelSQL(getCtx(), " ", MEXMEMedico.Table_Name, "p")                
                + ") "
                + "WHERE I_IsImported<>'Y'");

        no = DB.executeUpdate(sql.toString());

        log.fine("Set Exme_Medico_ID=" + no);
        
       //Set EXME_ALTASVOLUNT_ID (vgarcia)
        sql = new StringBuffer("Update I_EXME_ALTASVOLUNT i set EXME_ALTASVOLUNT_ID=" +
        		"(Select EXME_ALTASVOLUNT_ID from EXME_ALTASVOLUNT av " +
        		"where av.EXME_PACIENTE_ID=i.EXME_PACIENTE_ID) " +
        		"where i.I_ISIMPORTED<>'Y' and i.EXME_PACIENTE_ID is not null and i.EXME_PACIENTE_ID>0");
        no = DB.executeUpdate(sql.toString());
        log.fine("Set EXME_ALTASVOLUNT_ID=" + no);

        

        //-------------------------------------------------------------------

        int noInsert = 0;

        int noUpdate = 0;

        //int noInsertPO = 0;

        //int noUpdatePO = 0;



        //  Go through Records

        log.fine("start inserting/updating ...");

        sql = new StringBuffer ("SELECT I_EXME_AltasVolunt_ID, EXME_AltasVolunt_ID "

            + "FROM I_EXME_AltasVolunt WHERE I_IsImported='N'").append(clientCheck);

        Connection conn = DB.createConnection(false, Connection.TRANSACTION_READ_COMMITTED);

        try

        {

            //  Insertar subtipo de producto a partir de la importacion

            PreparedStatement pstmt_insertAltasVolunt = conn.prepareStatement

                ("INSERT INTO EXME_AltasVolunt (EXME_AltasVolunt_ID,"

                + "AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,"

                + "CamaAlta,DateOrdered,MedicoResp,Motivo,Exme_Paciente_ID,Exme_Cama_ID,VALUE) "

                + "SELECT ?,"

                + "AD_Client_ID,AD_Org_ID,'Y',SysDate,CreatedBy,SysDate,UpdatedBy,"

                + "CamaAlta,DateOrdered,MedicoResp,Motivo,Exme_Paciente_ID,Exme_Cama_ID,VALUE "

                + "FROM I_EXME_AltasVolunt "

                + "WHERE I_EXME_AltasVolunt_ID=?");
            
            //Para actualizar un registro (vgarcia)
            PreparedStatement pstmt_updateAltasVolunt = conn.prepareStatement
                ("Update EXME_AltasVolunt set("
                + "AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,"
                + "CamaAlta,DateOrdered,MedicoResp,Motivo,Exme_Paciente_ID,Exme_Cama_ID,VALUE)= "
                + "(SELECT "
                + "AD_Client_ID,AD_Org_ID,'Y',SysDate,CreatedBy,SysDate,UpdatedBy,"
                + "CamaAlta,DateOrdered,MedicoResp,Motivo,Exme_Paciente_ID,Exme_Cama_ID,VALUE "
                + "FROM I_EXME_AltasVolunt "
                + "WHERE I_EXME_AltasVolunt_ID=?) where EXME_AltasVolunt_ID=?");



            //  Set Imported = Y  a la tabla temporal

            PreparedStatement pstmt_setImported = conn.prepareStatement

                ("UPDATE I_EXME_AltasVolunt SET I_IsImported='Y', EXME_AltasVolunt_ID=?, "

                + "Updated=SysDate, Processed='Y' WHERE I_EXME_AltasVolunt_ID=?");



            //

            PreparedStatement pstmt = DB.prepareStatement(sql.toString(), null);
            ResultSet rs = pstmt.executeQuery();
            int I_EXME_AltasVolunt_ID = 0;
            int EXME_AltasVolunt_ID = 0;
            boolean newAltasVol = false;
            
            while (rs.next())

            {
                I_EXME_AltasVolunt_ID = rs.getInt(1);
                EXME_AltasVolunt_ID = rs.getInt(2);
                newAltasVol = EXME_AltasVolunt_ID == 0;

                log.fine("I_EXME_AltasVolunt_ID=" + I_EXME_AltasVolunt_ID + ", EXME_AltasVolunt_ID=" + EXME_AltasVolunt_ID);



                    try
                    {

                      if(newAltasVol){
                    	  EXME_AltasVolunt_ID = DB.getNextID(m_AD_Client_ID, "EXME_AltasVolunt", null);
                          if (EXME_AltasVolunt_ID <= 0)
                              throw new DBException("No Next ID (" + EXME_AltasVolunt_ID + ")");

                          pstmt_insertAltasVolunt.setInt(1, EXME_AltasVolunt_ID);
                          pstmt_insertAltasVolunt.setInt(2, I_EXME_AltasVolunt_ID);

                          //
                          no = pstmt_insertAltasVolunt.executeUpdate();
                          log.finer("Insert AltasVol = " + no);
                          noInsert++;
                      }
                      else{
                    	  pstmt_updateAltasVolunt.setInt(1, I_EXME_AltasVolunt_ID);
                          pstmt_updateAltasVolunt.setInt(2, EXME_AltasVolunt_ID);
                          //
                          no = pstmt_updateAltasVolunt.executeUpdate();
                          log.finer("Update AltasVol = " + no);

                          noUpdate++;
                      }
                      rs.close();

                      pstmt.close();
          	    
          	       rs= null;
          	    
          	       pstmt=null;

                    }

                    catch (Exception ex)

                    {

                        log.warning("Insertando AltasVol - " + ex.toString());

                        sql = new StringBuffer ("UPDATE I_EXME_AltasVolunt i "

                            + "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||")

                            .append(DB.TO_STRING("Insertando Altas Voluntaria de Pacientes: " + ex.toString()))

                            .append("WHERE I_EXME_AltasVolunt_ID=").append(I_EXME_AltasVolunt_ID);

                        DB.executeUpdate(sql.toString());

                        continue;

                    }

                    

                    //  Update I_EXME_Diagnostico

                    pstmt_setImported.setInt(1, EXME_AltasVolunt_ID);

                    pstmt_setImported.setInt(2, I_EXME_AltasVolunt_ID);

                    no = pstmt_setImported.executeUpdate();

                    //

                    conn.commit();

                }

            rs.close();

            pstmt.close();
	    
	    rs= null;
	    
	    pstmt=null;



            //

            pstmt_insertAltasVolunt.close();

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

        sql = new StringBuffer ("UPDATE I_EXME_AltasVolunt "

            + "SET I_IsImported='N', Updated=SysDate "

            + "WHERE I_IsImported<>'Y'").append(clientCheck);

        no = DB.executeUpdate(sql.toString());

        addLog (0, null, new BigDecimal (no), "@Errors@");

        addLog (0, null, new BigDecimal (noInsert), "@M_AltasVol_ID@: @Inserted@");

        addLog (0, null, new BigDecimal (noUpdate), "@M_AltasVol_ID@: @Updated@");

        return "";

    }

}

