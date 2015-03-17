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

public class ImportPreReClas extends SvrProcess {

    public ImportPreReClas() {
        super();
    }

    /** Client to be imported to        */
    private int             m_AD_Client_ID = 0;
    /** Delete old Imported             */
    private boolean         m_deleteOldImported = false;

    /** Organization to be imported to  */
//    private int             m_AD_Org_ID = 0; //FIXME: Nunca es usada la organizacion.
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
            else if (name.equals("DeleteOldImported"))
                m_deleteOldImported = "Y".equals(para[i].getParameter());
            else
                log.log(Level.SEVERE, "ImportExmeClasificacion.prepare - Unknown Parameter: " + name);
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
            sql = new StringBuffer ("DELETE I_EXME_Pre_ReClas "
                + "WHERE I_IsImported='Y'").append(clientCheck);
            no = DB.executeUpdate(sql.toString(), null);
            log.fine("Delete Old Impored =" + no);
        }

        //  Set Client, Org, IsActive, Created/Updated
        sql = new StringBuffer ("UPDATE I_EXME_Pre_ReClas "
            + "SET AD_Client_ID = COALESCE (AD_Client_ID, ").append(m_AD_Client_ID).append("),"
            + " AD_Org_ID = COALESCE (AD_Org_ID, 0),"
            + " IsActive = COALESCE (IsActive, 'Y'),"
            + " Created = COALESCE (Created, SysDate),"
            + " CreatedBy = COALESCE (CreatedBy, 0),"
            + " Updated = COALESCE (Updated, SysDate),"
            + " UpdatedBy = COALESCE (UpdatedBy, 0),"
            + " I_ErrorMsg = NULL,"
            + " I_IsImported = 'N' "
            + "WHERE I_IsImported<>'Y' OR I_IsImported IS NULL");
        no = DB.executeUpdate(sql.toString(), null);
        log.fine("Reset=" + no);

  /*****************************************************************************/      
        //  Set EXME_ClasCliente_ID
        sql = new StringBuffer (" UPDATE I_EXME_Pre_ReClas i " + 
                " SET i.EXME_ClasCliente_ID = (SELECT EXME_ClasCliente_ID " +
                " FROM EXME_ClasCliente c " + 
                " WHERE c.AD_Client_ID IN (0, i.AD_Client_ID) " + 
                " AND c.Value = i.EXME_ClasCliente_Value " );
        
        if (DB.isOracle()) {
        	sql.append(" AND ROWNUM=1) ");
        } else if (DB.isPostgreSQL()) {
        	sql = new StringBuffer(DB.getDatabase().addPagingSQL(sql.toString(), 1, 1));
        	sql.append(") ");
        }
        
        sql.append(" WHERE i.EXME_ClasCliente_Value IS NOT NULL " +
                " AND i.EXME_ClasCliente_ID IS NULL " +
                " AND i.I_IsImported<>'Y'" );
        no = DB.executeUpdate(sql.toString(), null);
        log.fine("Set EXME_ClasCliente_ID=" + no);
        //
        sql = new StringBuffer(" UPDATE I_EXME_Pre_ReClas "
                + " SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||'ERR=ClasCliente no existe,' "
                + " WHERE EXME_ClasCliente_ID IS NULL"
                + " AND I_IsImported<>'Y'");
        no = DB.executeUpdate(sql.toString(), null);
        if (no != 0)
            log.warning("ClasCliente no existe=" + no);
        
        
        //  Set EXME_MatConst_ID
        sql = new StringBuffer (" UPDATE I_EXME_Pre_ReClas i " + 
                " SET  i.EXME_MatConst_ID = (SELECT EXME_Material_Const_ID " +
                " FROM EXME_Material_Const c " + 
                " WHERE c.AD_Client_ID IN (0, i.AD_Client_ID) " + 
                " AND c.value = i.EXME_Material_Const_Value ");
        
        if (DB.isOracle()) {
        	sql.append(" AND ROWNUM=1) ");
        } else if (DB.isPostgreSQL()) {
        	sql = new StringBuffer(DB.getDatabase().addPagingSQL(sql.toString(), 1, 1));
        	sql.append(") ");
        }
        
        sql.append(" WHERE i.EXME_Material_Const_Value is not null " +
                " AND i.EXME_MatConst_ID is null " +
                " AND i.I_IsImported<>'Y'" );
        no = DB.executeUpdate(sql.toString(), null);
        log.fine("Set EXME_MatConst_ID=" + no);
        //
        sql = new StringBuffer(" UPDATE I_EXME_Pre_ReClas "
                + " SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||'ERR=Material_Const no existe,' "
                + " WHERE EXME_MatConst_id IS NULL"
                + " AND I_IsImported<>'Y'");
        no = DB.executeUpdate(sql.toString(), null);
        if (no != 0)
            log.warning("Material_Const no existe=" + no);  
        
        
        //Set EXME_NumEnferm_ID
        sql = new StringBuffer (" UPDATE I_EXME_Pre_ReClas i " + 
                " SET  i.EXME_NumEnferm_ID = (SELECT EXME_NumEnferm_ID " +
                " FROM EXME_NumEnferm c " + 
                " WHERE c.AD_Client_ID IN (0, i.AD_Client_ID) " + 
                " AND c.value = i.EXME_NumEnferm_Value ");
        
        if (DB.isOracle()) {
        	sql.append(" AND ROWNUM=1) ");
        } else if (DB.isPostgreSQL()) {
        	sql = new StringBuffer(DB.getDatabase().addPagingSQL(sql.toString(), 1, 1));
        	sql.append(") ");
        }
        
        sql.append("WHERE i.EXME_NumEnferm_Value is not null " +
                " AND i.EXME_NumEnferm_ID is null " +
                " AND i.I_IsImported<>'Y'" );
        no = DB.executeUpdate(sql.toString(), null);
        log.fine("Set EXME_NumEnferm_ID=" + no);
        //
        sql = new StringBuffer(" UPDATE I_EXME_Pre_ReClas "
                + " SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||'ERR=NumEnferm no existe,' "
                + " WHERE EXME_NumEnferm_ID IS NULL"
                + " AND I_IsImported<>'Y'");
        no = DB.executeUpdate(sql.toString(), null);
        if (no != 0)
            log.warning("NumEnferm no existe=" + no);  
        
        
        //Set EXME_NumHabts_ID
        sql = new StringBuffer (" UPDATE I_EXME_Pre_ReClas i " + 
                " SET  i.EXME_NumHabts_ID = (SELECT EXME_NumHabts_ID " +
                " FROM EXME_NumHabts c " + 
                " WHERE c.AD_Client_ID IN (0, i.AD_Client_ID) " + 
                " AND c.value = i.EXME_NumHabts_Value ");
        
        if (DB.isOracle()) {
        	sql.append(" AND ROWNUM=1) ");
        } else if (DB.isPostgreSQL()) {
        	sql = new StringBuffer(DB.getDatabase().addPagingSQL(sql.toString(), 1, 1));
        	sql.append(") ");
        }
        
        sql.append(" WHERE i.EXME_NumHabts_Value is not null " +
                " AND i.EXME_NumHabts_ID is null " +
                " AND i.I_IsImported<>'Y'" );
        no = DB.executeUpdate(sql.toString(), null);
        log.fine("Set EXME_NumHabts_ID=" + no);
        //
        sql = new StringBuffer(" UPDATE I_EXME_Pre_ReClas "
                + " SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||'ERR=NumHabts no existe,' "
                + " WHERE EXME_NumHabts_ID IS NULL"
                + " AND I_IsImported<>'Y'");
        no = DB.executeUpdate(sql.toString(), null);
        if (no != 0)
            log.warning("NumHabts no existe=" + no);  
        
        
        //Set EXME_NumPers_ID
        sql = new StringBuffer (" UPDATE I_EXME_Pre_ReClas i " + 
                " SET  i.EXME_NumPers_ID = (SELECT EXME_NumPers_ID " +
                " FROM EXME_NumPers c " + 
                " WHERE c.AD_Client_ID IN (0, i.AD_Client_ID) " + 
                " AND c.value = i.EXME_NumPers_Value ");
        
        if (DB.isOracle()) {
        	sql.append(" AND ROWNUM=1) ");
        } else if (DB.isPostgreSQL()) {
        	sql = new StringBuffer(DB.getDatabase().addPagingSQL(sql.toString(), 1, 1));
        	sql.append(") ");
        }
        
        sql.append(" WHERE i.EXME_NumPers_Value is not null " +
                " AND i.EXME_NumPers_ID is null " +
                " AND i.I_IsImported<>'Y'" );
        no = DB.executeUpdate(sql.toString(), null);
        log.fine("Set EXME_NumPers_ID=" + no);
        //
        sql = new StringBuffer(" UPDATE I_EXME_Pre_ReClas "
                + " SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||'ERR=NumPers no existe,' "
                + " WHERE EXME_NumPers_ID IS NULL"
                + " AND I_IsImported<>'Y'");
        no = DB.executeUpdate(sql.toString(), null);
        if (no != 0)
            log.warning("NumPers no existe=" + no);  
        
        
        //Set EXME_Ocupacion_Clas_ID
        sql = new StringBuffer (" UPDATE I_EXME_Pre_ReClas i " + 
                " SET  i.EXME_Ocupacion_Clas_ID = (SELECT EXME_Ocupacion_Clas_ID " +
                " FROM EXME_Ocupacion_Clas c " + 
                " WHERE c.AD_Client_ID IN (0, i.AD_Client_ID) " + 
                " AND c.value = i.EXME_Ocupacion_Clas_Value ");
        
        if (DB.isOracle()) {
        	sql.append(" AND ROWNUM=1) ");
        } else if (DB.isPostgreSQL()) {
        	sql = new StringBuffer(DB.getDatabase().addPagingSQL(sql.toString(), 1, 1));
        	sql.append(") ");
        }
        
        sql.append("WHERE i.EXME_Ocupacion_Clas_Value is not null " +
                " AND i.EXME_Ocupacion_Clas_ID is null " +
                " AND i.I_IsImported<>'Y'" );
        no = DB.executeUpdate(sql.toString(), null);
        log.fine("Set EXME_Ocupacion_Clas_ID=" + no);
        //
        sql = new StringBuffer(" UPDATE I_EXME_Pre_ReClas "
                + " SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||'ERR=Ocupacion_Clas no existe,' "
                + " WHERE EXME_Ocupacion_Clas_ID IS NULL"
                + " AND I_IsImported<>'Y'");
        no = DB.executeUpdate(sql.toString(), null);
        if (no != 0)
            log.warning("Ocupacion_Clas no existe=" + no);  
        
        
        //Set EXME_Procedencia_ID
        sql = new StringBuffer (" UPDATE I_EXME_Pre_ReClas i " + 
                " SET  i.EXME_Procedencia_ID = (SELECT EXME_Procedencia_ID " +
                " FROM EXME_Procedencia c " + 
                " WHERE c.AD_Client_ID IN (0, i.AD_Client_ID) " +
                " AND c.value = i.EXME_Procedencia_Value ");
        
        if (DB.isOracle()) {
        	sql.append(" AND ROWNUM=1) ");
        } else if (DB.isPostgreSQL()) {
        	sql = new StringBuffer(DB.getDatabase().addPagingSQL(sql.toString(), 1, 1));
        	sql.append(") ");
        }
        
        sql.append("WHERE i.EXME_Procedencia_Value is not null " +
                " AND i.EXME_Procedencia_ID is null " +
                " AND i.I_IsImported<>'Y'" );
        no = DB.executeUpdate(sql.toString(), null);
        log.fine("Set EXME_Procedencia_ID=" + no);
        //
        sql = new StringBuffer(" UPDATE I_EXME_Pre_ReClas "
                + " SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||'ERR=Procedencia no existe,' "
                + " WHERE EXME_Procedencia_ID IS NULL"
                + " AND I_IsImported<>'Y'");
        no = DB.executeUpdate(sql.toString(), null);
        if (no != 0)
            log.warning("Procedencia no existe=" + no);  
        
        
        //Set EXME_ServPublico_ID
        sql = new StringBuffer (" UPDATE I_EXME_Pre_ReClas i " + 
                " SET  i.EXME_ServPublico_ID = (SELECT EXME_ServPublico_ID " +
                " FROM EXME_ServPublico c " + 
                " WHERE c.AD_Client_ID IN (0, i.AD_Client_ID) " + 
                " AND c.value = i.EXME_ServPublico_Value ");
        
        if (DB.isOracle()) {
        	sql.append(" AND ROWNUM=1) ");
        } else if (DB.isPostgreSQL()) {
        	sql = new StringBuffer(DB.getDatabase().addPagingSQL(sql.toString(), 1, 1));
        	sql.append(") ");
        }
        
        sql.append("WHERE i.EXME_ServPublico_Value is not null " +
                " AND i.EXME_ServPublico_ID is null " +
                " AND i.I_IsImported<>'Y'" );
        no = DB.executeUpdate(sql.toString(), null);
        log.fine("Set EXME_ServPublico_ID=" + no);
        //
        sql = new StringBuffer(" UPDATE I_EXME_Pre_ReClas "
                + " SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||'ERR=ServPublico no existe,' "
                + " WHERE EXME_ServPublico_ID IS NULL"
                + " AND I_IsImported<>'Y'");
        no = DB.executeUpdate(sql.toString(), null);
        if (no != 0)
            log.warning("ServPublico no existe=" + no);  
        
        
        //Set EXME_Tenencia_ID
        sql = new StringBuffer (" UPDATE I_EXME_Pre_ReClas i " + 
                " SET  i.EXME_Tenencia_ID = (SELECT EXME_Tenencia_ID " +
                " FROM EXME_Tenencia c " + 
                " WHERE c.AD_Client_ID IN (0, i.AD_Client_ID) " + 
                " AND c.value = i.EXME_Tenencia_Value ");
        
        if (DB.isOracle()) {
        	sql.append(" AND ROWNUM=1) ");
        } else if (DB.isPostgreSQL()) {
        	sql = new StringBuffer(DB.getDatabase().addPagingSQL(sql.toString(), 1, 1));
        	sql.append(") ");
        }
        
        sql.append("WHERE i.EXME_Tenencia_Value is not null " +
                " AND i.EXME_Tenencia_ID is null " +
                " AND i.I_IsImported<>'Y'" );
        no = DB.executeUpdate(sql.toString(), null);
        log.fine("Set EXME_Tenencia_ID=" + no);
        //
        sql = new StringBuffer(" UPDATE I_EXME_Pre_ReClas "
                + " SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||'ERR=Tenencia no existe,' "
                + " WHERE EXME_Tenencia_ID IS NULL"
                + " AND I_IsImported<>'Y'");
        no = DB.executeUpdate(sql.toString(), null);
        if (no != 0)
            log.warning("Tenencia no existe=" + no); 

        
        //Set EXME_TipoVivienda_ID
        sql = new StringBuffer (" UPDATE I_EXME_Pre_ReClas i " + 
                " SET  i.EXME_TipoVivienda_ID = (SELECT EXME_TipoVivienda_ID " +
                " FROM EXME_TipoVivienda c " + 
                " WHERE c.AD_Client_ID IN (0, i.AD_Client_ID) " + 
                " AND c.value = i.EXME_TipoVivienda_Value ");
        
        if (DB.isOracle()) {
        	sql.append(" AND ROWNUM=1) ");
        } else if (DB.isPostgreSQL()) {
        	sql = new StringBuffer(DB.getDatabase().addPagingSQL(sql.toString(), 1, 1));
        	sql.append(") ");
        }
        
        sql.append("WHERE i.EXME_TipoVivienda_Value is not null " +
                " AND i.EXME_TipoVivienda_ID is null " +
                " AND i.I_IsImported<>'Y'" );
        no = DB.executeUpdate(sql.toString(), null);
        log.fine("Set EXME_TipoVivienda_ID=" + no);
        //
        sql = new StringBuffer(" UPDATE I_EXME_Pre_ReClas "
                + " SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||'ERR=TipoVivienda no existe,' "
                + " WHERE EXME_TipoVivienda_ID IS NULL"
                + " AND I_IsImported<>'Y'");
        no = DB.executeUpdate(sql.toString(), null);
        if (no != 0)
            log.warning("TipoVivienda no existe=" + no); 

        
        //Set EXME_Zona_ID
        sql = new StringBuffer (" UPDATE I_EXME_Pre_ReClas i " + 
                " SET  i.EXME_Zona_ID = (SELECT EXME_Zona_ID " +
                " FROM EXME_Zona c " + 
                " WHERE c.AD_Client_ID IN (0, i.AD_Client_ID) " + 
                " AND c.value = i.EXME_Zona_Value ");
        
        if (DB.isOracle()) {
        	sql.append(" AND ROWNUM=1) ");
        } else if (DB.isPostgreSQL()) {
        	sql = new StringBuffer(DB.getDatabase().addPagingSQL(sql.toString(), 1, 1));
        	sql.append(") ");
        }
        
        sql.append("WHERE i.EXME_Zona_Value is not null " +
                " AND i.EXME_Zona_ID is null " +
                " AND i.I_IsImported<>'Y'" );
        no = DB.executeUpdate(sql.toString(), null);
        log.fine("Set EXME_Zona_ID=" + no);
        //
        sql = new StringBuffer(" UPDATE I_EXME_Pre_ReClas "
                + " SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||'ERR=Zona no existe,' "
                + " WHERE EXME_Zona_ID IS NULL"
                + " AND I_IsImported<>'Y'");
        no = DB.executeUpdate(sql.toString(), null);
        if (no != 0)
            log.warning("Zona no existe=" + no); 
        
        
        //Set EXME_Paciente_ID
        sql = new StringBuffer (" UPDATE I_EXME_Pre_ReClas i " + 
                " SET  i.EXME_Paciente_ID = (SELECT EXME_Paciente_ID " +
                " FROM EXME_Paciente c " + 
                " WHERE  " + 
                " c.value = i.EXME_Paciente_Value "
                + MEXMELookupInfo.addAccessLevelSQL(getCtx(), " ", MEXMEPaciente.Table_Name, "c"));
        
        if (DB.isOracle()) {
        	sql.append(" AND ROWNUM=1) ");
        } else if (DB.isPostgreSQL()) {
        	sql = new StringBuffer(DB.getDatabase().addPagingSQL(sql.toString(), 1, 1));
        	sql.append(") ");
        }
        
        sql.append(" WHERE i.EXME_Paciente_Value is not null " +
                " AND i.EXME_Paciente_ID is null " +
                " AND i.I_IsImported<>'Y'" );
        no = DB.executeUpdate(sql.toString(), null);
        log.fine("Set EXME_Paciente_ID=" + no);
        //
        sql = new StringBuffer(" UPDATE I_EXME_Pre_ReClas "
                + " SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||'ERR=Paciente no existe,' "
                + " WHERE EXME_Paciente_ID IS NULL"
                + " AND I_IsImported<>'Y'");
        no = DB.executeUpdate(sql.toString(), null);
        if (no != 0)
            log.warning("Paciente no existe=" + no); 
        
        //Set EXME_PRE_RECLAS_ID (vgarcia)
        sql = new StringBuffer("Update I_EXME_Pre_ReClas i set EXME_Pre_ReClas_ID=" +
        		"(Select EXME_Pre_ReClas_ID from EXME_Pre_ReClas rc " +
        		"where rc.EXME_PACIENTE_ID=i.EXME_PACIENTE_ID) " +
        		"where i.I_ISIMPORTED<>'Y' and i.EXME_PACIENTE_ID is not null and i.EXME_PACIENTE_ID>0");
        no = DB.executeUpdate(sql.toString(), null);
        log.fine("Set EXME_Pre_ReClas_ID=" + no);
        
/******************************************************************************/        
        


//      -------------------------------------------------------------------

        int noInsert = 0;
        int noUpdate = 0;
        //int noInsertPO = 0;
        //int noUpdatePO = 0;

        //  Go through Records

        log.fine("start inserting/updating ...");
        
        sql = new StringBuffer ("SELECT I_EXME_Pre_ReClas_ID, EXME_Pre_ReClas_ID "
            + "FROM I_EXME_Pre_ReClas WHERE I_IsImported='N'");
        
        Connection conn = DB.createConnection(false, Connection.TRANSACTION_READ_COMMITTED);

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try
        {
            //  Insertar Clasificacion a partir de la importacion
            PreparedStatement pstmt_insertPreReclas = conn.prepareStatement
                ("INSERT INTO EXME_Pre_ReClas (EXME_Pre_ReClas_ID"
                + ",  AD_Client_ID, AD_Org_ID, IsActive, Created, CreatedBy, Updated, UpdatedBy"
                + ", EXME_Paciente_ID, DateOrdered, EXME_Procedencia_ID, PtsProcedencia"
                + ", EXME_Ocupacion_Clas_ID, Ptsocupacion, EXME_zona_ID, Ptszona, EXME_tipovivienda_ID"
                + ", PtsTipoVivienda, EXME_tenencia_ID, Ptstenencia, EXME_servpublico_ID, Ptsservpublico"
                + ", EXME_matconst_ID, Ptsmatconst, EXME_numhabts_ID, Ptsnumhabts, EXME_numpers_ID"
                + ", Ptsnumpers, EXME_numenferm_ID, NumMiembros, Observaciones"
                + ", totalpts, EXME_clascliente_ID, ingjefefam, inghijos, ingotros, totaling"
                + ", Egralimentacion, Egrvivienda, Egrservicios, Egrotros, totalegr, Porcalimentacion"
                + ", Ptsingresos, Ptsegre, datevaidto, islocked, ad_user_ID ) "
                
                + "SELECT ?"
                + ", AD_Client_ID, AD_Org_ID, 'Y', SysDate, CreatedBy, SysDate, UpdatedBy"
                + ", EXME_Paciente_ID, DateOrdered, EXME_Procedencia_ID, PtsProcedencia"
                + ", EXME_Ocupacion_Clas_ID, Ptsocupacion, EXME_zona_ID, Ptszona, EXME_tipovivienda_ID"
                + ", PtsTipoVivienda, EXME_tenencia_ID, Ptstenencia, EXME_servpublico_ID, Ptsservpublico"
                + ", EXME_matconst_ID, Ptsmatconst, EXME_numhabts_ID, Ptsnumhabts, EXME_numpers_ID"
                + ", Ptsnumpers, EXME_numenferm_ID, nummiembros, observaciones"
                + ", totalpts, EXME_clascliente_ID, ingjefefam, inghijos, ingotros, totaling"
                + ", Egralimentacion, Egrvivienda, Egrservicios, Egrotros, totalegr, Porcalimentacion"
                + ", Ptsingresos, Ptsegre, DateOrdered, IsLocked, AD_User_ID "
                + "  FROM I_EXME_Pre_ReClas "
                + "  WHERE I_EXME_Pre_ReClas_ID=? ");

            //  Update Clasificacion from Import
            PreparedStatement pstmt_updatePreReclas = conn.prepareStatement
                ("UPDATE EXME_Pre_ReClas "
                + " SET (Updated,UpdatedBy" 
                + ", EXME_Paciente_ID, DateOrdered, EXME_Procedencia_ID, PtsProcedencia"
                + ", EXME_Ocupacion_Clas_ID, Ptsocupacion, EXME_zona_ID, Ptszona, EXME_tipovivienda_ID"
                + ", PtsTipoVivienda, EXME_tenencia_ID, Ptstenencia, EXME_servpublico_ID, Ptsservpublico"
                + ", EXME_matconst_ID, Ptsmatconst, EXME_numhabts_ID, Ptsnumhabts, EXME_numpers_ID"
                + ", Ptsnumpers, EXME_numenferm_ID, nummiembros, observaciones"
                + ", totalpts, EXME_clascliente_ID, ingjefefam, inghijos, ingotros, totaling"
                + ", Egralimentacion, Egrvivienda, Egrservicios, Egrotros, totalegr, Porcalimentacion"
                + ", Ptsingresos, Ptsegre, datevaidto, IsLocked, AD_User_ID)= "
                + "(SELECT SysDate,UpdatedBy"
                + ", EXME_Paciente_ID, DateOrdered, EXME_Procedencia_ID, PtsProcedencia"
                + ", EXME_Ocupacion_Clas_ID, Ptsocupacion, EXME_zona_ID, Ptszona, EXME_tipovivienda_ID"
                + ", PtsTipoVivienda, EXME_tenencia_ID, Ptstenencia, EXME_servpublico_ID, Ptsservpublico"
                + ", EXME_matconst_ID, Ptsmatconst, EXME_numhabts_ID, Ptsnumhabts, EXME_numpers_ID"
                + ", Ptsnumpers, EXME_numenferm_ID, nummiembros, observaciones"
                + ", totalpts, EXME_clascliente_ID, ingjefefam, inghijos, ingotros, totaling"
                + ", Egralimentacion, Egrvivienda, Egrservicios, Egrotros, totalegr, Porcalimentacion"
                + ", Ptsingresos, Ptsegre, DateOrdered, IsLocked, AD_User_ID"
                + " FROM I_EXME_Pre_ReClas WHERE I_EXME_Pre_ReClas_ID=?) "
                + "WHERE EXME_Pre_ReClas_ID=?");
            

            //  Set Imported = Y
            PreparedStatement pstmt_setImported = conn.prepareStatement
                ("UPDATE I_EXME_Pre_ReClas SET I_IsImported='Y', EXME_Pre_ReClas_ID=?, "
                + "Updated=SysDate, Processed='Y' WHERE I_EXME_Pre_ReClas_ID=?");

            
            //Primero el select
            pstmt = DB.prepareStatement(sql.toString(), null);
            rs = pstmt.executeQuery();
            int I_EXME_Pre_ReClas_ID = 0;
            int EXME_Pre_ReClas_ID = 0;
            boolean newRegistro = false;
            
            while (rs.next())
            {

                I_EXME_Pre_ReClas_ID = rs.getInt(1);
                EXME_Pre_ReClas_ID = rs.getInt(2);
                newRegistro = EXME_Pre_ReClas_ID == 0;
                log.fine("I_EXME_Pre_ReClas_ID=" + I_EXME_Pre_ReClas_ID + ", EXME_EXME_Pre_ReClas_ID=" + EXME_Pre_ReClas_ID);

                //  nuevo registro
                if (newRegistro)
                {
                    try
                    {

                        EXME_Pre_ReClas_ID = DB.getNextID(m_AD_Client_ID, "EXME_Pre_ReClas", null);
                        if (EXME_Pre_ReClas_ID <= 0)
                            throw new DBException("No Next ID (" + EXME_Pre_ReClas_ID + ")");
                        pstmt_insertPreReclas.setInt(1, EXME_Pre_ReClas_ID);
                        pstmt_insertPreReclas.setInt(2, I_EXME_Pre_ReClas_ID);
                        //
                        no = pstmt_insertPreReclas.executeUpdate();
                        log.finer("Insert Pre Reclasificacion = " + no);
                        noInsert++;

                    }catch (Exception ex){

                        log.warning("Insert Pre Reclasificacion  - " + ex.toString());
                        sql = new StringBuffer ("UPDATE I_EXME_Pre_ReClas i "
                            + "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||")
                            .append(DB.TO_STRING("Insert Pre Reclasificacion: " + ex.toString()))
                            .append("WHERE I_EXME_Pre_ReClas_ID=").append(I_EXME_Pre_ReClas_ID);
                        DB.executeUpdate(sql.toString(), null);
                        continue;

                    }

                }
                else    //  Update Clasificacion
                {
                    pstmt_updatePreReclas.setInt(1, I_EXME_Pre_ReClas_ID);
                    pstmt_updatePreReclas.setInt(2, EXME_Pre_ReClas_ID);

                    try
                    {
                        no = pstmt_updatePreReclas.executeUpdate();
                        log.finer("Update Pre Reclasificacion = " + no);
                        noUpdate++;
                    }
                    catch (SQLException ex)
                    {
                        log.warning("Update PreReclas- " + ex.toString());
                        sql = new StringBuffer ("UPDATE I_EXME_Pre_ReClas i "
                            + "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||").append(DB.TO_STRING("Update Clasificacion: " + ex.toString()))
                            .append("WHERE I_EXME_Pre_ReClas_ID=").append(I_EXME_Pre_ReClas_ID);
                        DB.executeUpdate(sql.toString(), null);
                        continue;
                    }

                }

                //  Update I_EXME_Pre_ReClas
                pstmt_setImported.setInt(1, EXME_Pre_ReClas_ID);
                pstmt_setImported.setInt(2, I_EXME_Pre_ReClas_ID);
                no = pstmt_setImported.executeUpdate();
                //
                conn.commit();

            }

            rs.close();
            pstmt.close();
            //
            pstmt_insertPreReclas.close();
            pstmt_updatePreReclas.close();
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
            rs=null;
            pstmt=null;
            log.log(Level.SEVERE, "doIt", e);
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
        sql = new StringBuffer ("UPDATE I_EXME_Pre_ReClas "
            + "SET I_IsImported='N', Updated=SysDate "
            + "WHERE I_IsImported<>'Y'");
        no = DB.executeUpdate(sql.toString(), null);
        addLog (0, null, new BigDecimal (no), "@Errors@");
        addLog (0, null, new BigDecimal (noInsert), "@EXME_Pre_ReClas_ID@: @Inserted@");
        addLog (0, null, new BigDecimal (noUpdate), "@EXME_Pre_ReClas_ID@: @Updated@");
        return "";

    }
}   // 
