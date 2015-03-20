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
import org.compiere.util.Env;

/**
 *  @author     Victor Herrera
 * 
 *  
 */
public class ImportColonia extends SvrProcess
{
    /** Client to be imported to        */
    private int             c_AD_Client_ID = 0;
    /** Delete old Imported             */
    private boolean         c_deleteOldImported = false;

    /** Effective                       */
    private Timestamp       c_DateValue = null;
    
    private int             c_C_Country_ID = 0;

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
                c_AD_Client_ID = ((BigDecimal)para[i].getParameter()).intValue();
            else if (name.equals("DeleteOldImported"))
                c_deleteOldImported = "Y".equals(para[i].getParameter());
            else if (name.equals("C_Country_ID"))
                c_C_Country_ID = ((BigDecimal)para[i].getParameter()).intValue();
            else
                log.log(Level.SEVERE, "Unknown Parameter: " + name);
        }
        if (c_DateValue == null)
            c_DateValue = new Timestamp (System.currentTimeMillis());
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
        
    	String slSysdate = "Sysdate";
    	String slC_REGION_ID = "i.C_REGION_ID";
    	String slEXME_TownCouncil_ID = "i.EXME_TownCouncil_ID";
    	String slEXME_Colonia_ID = "i.EXME_Colonia_ID";
        
    	if (DB.isPostgreSQL()) {
        	slSysdate = "current_date";
        	slC_REGION_ID = "C_REGION_ID";
        	slEXME_TownCouncil_ID = "EXME_TownCouncil_ID";
        	slEXME_Colonia_ID = "EXME_Colonia_ID";
        }
        
        //si el pa#s es 0, insertamos es Id del logueo
        if(c_C_Country_ID <= 0){
        	c_C_Country_ID = Env.getContextAsInt(getCtx(),"#C_Country_ID");
        }
        
        
        //
        String clientCheck = " AND AD_Client_ID=" + ( c_AD_Client_ID <= 0 ? Env.getAD_Client_ID(getCtx()) : c_AD_Client_ID );

        //  ****    Prepare ****

        //  Delete Old Imported, si el usuario decide eliminar los registros que estan importados ('Y')
        if (c_deleteOldImported)
        {
            sql = new StringBuffer ("DELETE I_EXME_Colonia "
                + "WHERE I_IsImported='Y'").append(clientCheck);
            no = DB.executeUpdate(sql.toString());
            log.info("Delete Old Impored =" + no);
        }

        //  Set Client, Org, IaActive, Created/Updated,     ProductType
        //Actualiza los registros que no han sido importados o tienen null en la importacion para no volver a procesarlos otra vez
        sql = new StringBuffer ("UPDATE I_EXME_Colonia "
            + " SET AD_Client_ID = COALESCE (AD_Client_ID, ").append(c_AD_Client_ID).append("),"
            + " AD_Org_ID = COALESCE (AD_Org_ID, 0),"
            + " IsActive = COALESCE (IsActive, 'Y'),"
            + " Created = COALESCE (Created, " + slSysdate + "),"
            + " CreatedBy = COALESCE (CreatedBy, 0),"
            + " Updated = COALESCE (Updated, " + slSysdate + "),"
            + " UpdatedBy = COALESCE (UpdatedBy, 0),"           
            + " I_ErrorMsg = NULL,"
            + " I_IsImported = 'N', "
            + " C_COUNTRY_ID = ").append(c_C_Country_ID).append(" "
            + " WHERE I_IsImported<>'Y' OR I_IsImported IS NULL");
        no = DB.executeUpdate(sql.toString());
        log.info("Reset=" + no);
        
        //pais
//        sql = new StringBuffer ("UPDATE I_EXME_Colonia  i " +
//       		" SET i.C_COUNTRY_ID=(SELECT r.C_COUNTRY_ID FROM C_COUNTRY r " +
//        	" WHERE TRIM(r.COUNTRYCODE)=TRIM(i.COUNTRY_VALUE)) " +
//        	" WHERE i.C_COUNTRY_ID=0 AND i.I_IsImported<>'Y'");
//        	no = DB.executeUpdate(sql.toString());
//        	log.info("EXME_COUNTRY_ID=" + no);

         //  Set EXME_Region_ID (ESTADO)
        //Actualizar la tabla de I_EXME_Colonia el campo de Region_ID donde el valor de Region_ID de la
        //tabla I_EXME_Colonia es igual al valor de Region_ID de C_REGION, siempre y cuando i.C_REGION_ID este
        //vac#a para insertar el valor y colocarla como ya importada, para no volver a repetir el proceso de leerla otra vez.
        sql = new StringBuffer ("UPDATE I_EXME_Colonia  i " +
        		" SET " + slC_REGION_ID + "=(SELECT r.C_REGION_ID FROM C_REGION r " +
        		" WHERE UPPER(TRIM(COALESCE(r.DESCRIPTION,r.NAME)))=UPPER(TRIM(i.REGIONNAME)) AND i.C_COUNTRY_ID=r.C_COUNTRY_ID) " +
        		" WHERE i.C_REGION_ID IS NULL AND i.I_IsImported<>'Y'");
    			//" WHERE i.C_REGION_ID IS NULL AND i.I_IsImported<>'Y' ");//niguno es null
            no = DB.executeUpdate(sql.toString());
            log.info("EXME_Region_ID=" + no);
            
        //  Set EXME_TownCouncil_ID
        //se repite el proceso anterior pero solo para el municipio
        sql = new StringBuffer ("UPDATE I_EXME_Colonia  i ");
        sql.append(" SET " + slEXME_TownCouncil_ID + "=(SELECT m.EXME_TownCouncil_ID FROM EXME_TownCouncil m ");
        sql.append(" WHERE UPPER(TRIM(m.Name))=UPPER(TRIM(i.TOWNCOUNCILNAME)) AND i.C_REGION_ID=m.C_REGION_ID "); 

        if (DB.isOracle()) {
        	sql.append(" AND ROWNUM=1) ");
        } else if (DB.isPostgreSQL()) {
        	sql = new StringBuffer(DB.getDatabase().addPagingSQL(sql.toString(), 1, 1));
        	sql.append(") ");
        }
        
        sql.append(" WHERE i.EXME_TownCouncil_ID IS NULL AND i.I_IsImported<>'Y'");
        		//" WHERE i.EXME_TownCouncil_ID IS NULL AND i.I_IsImported<>'Y'");niguno es null
        no = DB.executeUpdate(sql.toString());
        log.info("EXME_TownCouncil_ID=" + no);
                           
     
        //
        sql=new StringBuffer(" UPDATE I_EXME_Colonia i " +
        				" SET " + slEXME_Colonia_ID + "=( SELECT c.EXME_Colonia_ID FROM EXME_Colonia c WHERE UPPER(TRIM(c.Codigo_Postal))= UPPER(TRIM(i.Codigo_Postal)) " +
        				" AND UPPER(TRIM(c.Colonia)) = UPPER(TRIM(i.Colonia)))WHERE i.I_IsImported<>'Y' ");
        no = DB.executeUpdate(sql.toString());
        log.info("EXME_Colonia_ID=" + no);

        //  -------------------------------------------------------------------
        int noInsert = 0;
        int noUpdate = 0;
        //  Go through Records
        log.fine("start inserting/updating ...");
        //se utiliza I_EXME_Colonia_ID-->para obtener el id de la tabla donde se obtendran los valores
        //para hacer el update y el insert en la tabla de EXME_Colonia si los ID's de ambos coinciden y no 
        //han sido importados.Recordar que los que fueron importados, ya no se vuelven a procesar.
        sql = new StringBuffer ("SELECT I_EXME_Colonia_ID,EXME_Colonia_ID " +
        		" FROM I_EXME_Colonia WHERE I_IsImported='N'");
        Connection conn = DB.createConnection(false, Connection.TRANSACTION_READ_COMMITTED);   
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
        	
        	StringBuilder sbInsertColonia = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
        	sbInsertColonia.append("INSERT INTO EXME_Colonia (");
        	sbInsertColonia.append("EXME_Colonia_ID, AD_Client_ID, AD_Org_ID, IsActive, Created, ");
        	sbInsertColonia.append("CreatedBy, Updated, UpdatedBy, colonia, codigo_postal, c_country_id, ");
        	sbInsertColonia.append("c_region_id, exme_towncouncil_id, ciudad) ");
        	sbInsertColonia.append(" SELECT ?, AD_Client_ID, AD_Org_ID, 'Y', ");
        	
        	if (DB.isPostgreSQL()) {
        		sbInsertColonia.append("current_date, ");
        	} else if (DB.isOracle()) {
        		sbInsertColonia.append("Sysdate, ");
        	}
        	
        	sbInsertColonia.append(" CreatedBy,  ");
        	
        	if (DB.isPostgreSQL()) {
        		sbInsertColonia.append("current_date, ");
        	} else if (DB.isOracle()) {
        		sbInsertColonia.append("Sysdate, ");
        	}
        	
        	sbInsertColonia.append(" UpdatedBy, colonia, codigo_postal, c_country_id, ");
        	sbInsertColonia.append("c_region_id, exme_towncouncil_id, ciudad ");
        	sbInsertColonia.append("FROM I_EXME_Colonia WHERE I_EXME_Colonia_ID = ? ");
        	
        	PreparedStatement pstmt_insertColonia = conn.prepareStatement(sbInsertColonia.toString());
        	
        	
        	// Para insertar los Registros Nuevos
            /*PreparedStatement pstmt_insertColonia = conn.prepareStatement             
            ("INSERT INTO EXME_Colonia (" +
            		" EXME_Colonia_ID,AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,"+
            		" colonia,codigo_postal,c_country_id,c_region_id,exme_towncouncil_id,ciudad)"+
            		" SELECT ?, AD_Client_ID,AD_Org_ID,'Y',Sysdate,CreatedBy,Sysdate,UpdatedBy,"+
            		" colonia,codigo_postal,c_country_id,c_region_id,exme_towncouncil_id,ciudad"+
            		" FROM I_EXME_Colonia WHERE I_EXME_Colonia_ID=?");*/


            // Para actualizar los registros ya existentes en la tabla Real de EXME_Colonia
            PreparedStatement pstmt_updateColonia = conn.prepareStatement             
            ("UPDATE EXME_Colonia SET(" +
            		" AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy," +
            		" colonia,codigo_postal,c_country_id,c_region_id,exme_towncouncil_id,ciudad)=" +
            		" (SELECT AD_Client_ID,AD_Org_ID,'Y'," + slSysdate + " ,CreatedBy, " + slSysdate + ", UpdatedBy," +
            		" colonia,codigo_postal,c_country_id,c_region_id,exme_towncouncil_id,ciudad FROM I_EXME_Colonia " +
            		" WHERE I_EXME_Colonia_ID=?) WHERE EXME_Colonia_ID=?");
                              
            
            //  Set Imported = Y
            //una vez que ya fueron agregados cada registro de la tabla de I_EXME_Colonia a EXME_Colonia, se 
            //colocan como importados, para que al actualizar de nuevo, no se lean los registros otra vez.
            //Nota: esto es en la tabla I_EXME_Colonia, ya que de esta se obtienen los registros a los que se 
            //agregaran ala tabla EXME_Colonia
            PreparedStatement pstmt_setImported = conn.prepareStatement
                ("UPDATE I_EXME_Colonia SET I_IsImported='Y', Updated=" + slSysdate + ", Processed='Y' WHERE I_EXME_Colonia_ID=?");

            //
            PreparedStatement pstmt = DB.prepareStatement(sql.toString(), null);
            //PreparedStatement ps = null;
            //ResultSet rs1 = null;
            rs = pstmt.executeQuery();
            //variables para obtener los parametros del query
            int I_EXME_Colonia_ID = 0;   
            int  EXME_Colonia_ID = 0;
            //int id = 0;
            boolean nuevo = false;
            
            while (rs.next())
            {
            	I_EXME_Colonia_ID = rs.getInt(1);//obtiene el parametro ID de I_EXME_Colonia que proviene del query  ?   
            	EXME_Colonia_ID = rs.getInt(2);//obtiene el id de la tabla EXME_Colonia del segundo parametro , es decir del segundo ?
                //id=0;
                nuevo = EXME_Colonia_ID == 0;//SI ESTA VACIO..nuevo es true 

                    try
                    {
                    	if(nuevo){
                    		//se crea el un nuevo registro con el siguiente ID,en la tabla EXME_Colonia
                    		EXME_Colonia_ID = DB.getNextID(c_AD_Client_ID, "EXME_Colonia", null);
                            if (EXME_Colonia_ID <= 0)
                                throw new DBException("No Next ID (" + EXME_Colonia_ID + ")");//se lanza una excepcion en caso de que no exista un id correcto
                            pstmt_insertColonia.setInt(1, EXME_Colonia_ID);//el primer parametro es el id de la tabla real
                            pstmt_insertColonia.setInt(2, I_EXME_Colonia_ID);//el 2 es el segundo parametro de la tabla de importaciones
                            //
                            no = pstmt_insertColonia.executeUpdate();
                            log.finer("Insert Municipios= " + no);
                            noInsert++;
                    	}
                    	//en dado caso que exista ya un registro con el mismo EXME_Colonia_ID, este se actualiza
                    	else {
                    		 pstmt_updateColonia.setInt(1, I_EXME_Colonia_ID);
                    		pstmt_updateColonia.setInt(2, EXME_Colonia_ID);
                            //
                            no = pstmt_updateColonia.executeUpdate();
                            log.finer("Update Municipios= " + no);
                            noUpdate++;
                    	}
                         
                    }
                    //si se produce un error en los registros al actualizar o al crear, se 
                    //llena el campo de error y el de Imprtaci#n
                    catch (Exception ex)
                    {
                        log.warning("Insert Colonias  - " + ex.toString());
                        sql = new StringBuffer ("UPDATE I_EXME_Colonia "
                            + " SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||")
                            .append(DB.TO_STRING(" Insert Colonias " + ex.toString()))
                            .append(" WHERE I_EXME_Colonia_ID=").append(I_EXME_Colonia_ID);
                        DB.executeUpdate(sql.toString());
                        continue;
                    }

                pstmt_setImported.setInt(1, I_EXME_Colonia_ID);//una vez que ya se proceso cada registro, se coloca como importado Y, para no ser le#do de nuevo en la tabla I_EXME_Colonia
                no = pstmt_setImported.executeUpdate();
                //
                conn.commit();
            }
            rs.close();
            pstmt.close();

            //
            pstmt_insertColonia.close();
            pstmt_updateColonia.close();
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
                if(ps != null)
                    ps.close();
            }
            catch (SQLException ex)
            {
            }
            log.log(Level.SEVERE, "doIt", e);
            rs=null;
            ps=null;
            throw new Exception ("doIt", e);
        }
        finally
        {
            if (conn != null)
                conn.close();
            conn = null;
            if(rs != null)
                rs.close();
            if(ps != null)
                ps.close();
            rs=null;
            ps=null;
        }

        addLog (0, null, new BigDecimal (no), "@Errors@");
		addLog (0, null, new BigDecimal (noInsert), "@EXME_Colonia_ID@: @Inserted@");
		addLog (0, null, new BigDecimal (noUpdate), "@EXME_Colonia_ID@: @Updated@");
     
        return "";
        
    }
    }