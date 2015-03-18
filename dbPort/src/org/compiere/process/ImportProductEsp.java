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



/**

 * Proceso de importaci&oacute;n de la tabla Producto/Especialidad.<p>

 * Creado: 28/Mar/2005<p>

 * Modificado: $Date: 2005/03/29 00:58:15 $<p>

 * Por: $Author: gisela $<p>

 * 

 * @author Gisela Lee

 * @version $Revision: 1.1 $

 */

public class ImportProductEsp extends SvrProcess {

    

    /**	Client to be imported to		*/

	private int				m_AD_Client_ID = 0;

	/**	Delete old Imported				*/

	private boolean			m_deleteOldImported = false;



	/** Organization to be imported to	*/

	private int				m_AD_Org_ID = 0;

	/** Effective						*/

	private Timestamp		m_DateValue = null;

    

    /**

     * Constructor por defecto.

     */

    public ImportProductEsp() {

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



		//	****	Prepare	****



		//	Delete Old Imported

		if (m_deleteOldImported)

		{

			sql = new StringBuffer ("DELETE I_EXME_ProductEsp "

				+ "WHERE I_IsImported='Y'").append(clientCheck);

			no = DB.executeUpdate(sql.toString());

			log.info("Delete Old Impored =" + no);

		}



		//	Set Client, Org, IaActive, Created/Updated,

		sql = new StringBuffer ("UPDATE I_EXME_ProductEsp "

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

		

		//	Set Product

		sql = new StringBuffer ("UPDATE I_EXME_ProductEsp i "

			+ "SET M_Product_ID=(SELECT M_Product_ID FROM M_Product p"

			+ " WHERE i.M_Product_Value=p.Value AND i.AD_Client_ID=p.AD_Client_ID) "

			+ "WHERE M_Product_ID IS NULL"

			+ " AND I_IsImported<>'Y'").append(clientCheck);

		no = DB.executeUpdate(sql.toString());

		log.fine("Set Product=" + no);

		//

		sql = new StringBuffer ("UPDATE I_EXME_ProductEsp "

			+ "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||'ERR=Invalid Product, ' "

			+ "WHERE M_Product_ID IS NULL"

			+ " AND I_IsImported<>'Y'").append(clientCheck);

		no = DB.executeUpdate(sql.toString());

		log.config("Invalid Product=" + no);

		

		//	Set Especialidad

		sql = new StringBuffer ("UPDATE I_EXME_ProductEsp i "

			+ "SET EXME_Especialidad_ID=(SELECT EXME_Especialidad_ID FROM EXME_Especialidad e"

			+ " WHERE i.Especialidad_Value=e.Value AND i.AD_Client_ID=e.AD_Client_ID) "

			+ "WHERE EXME_Especialidad_ID IS NULL"

			+ " AND I_IsImported<>'Y'").append(clientCheck);

		no = DB.executeUpdate(sql.toString());

		log.fine("Set Especialidad=" + no);

		//

		

		sql = new StringBuffer ("UPDATE I_EXME_ProductEsp "

			+ "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||'ERR=Invalid Especialidad, ' "

			+ "WHERE EXME_Especialidad_ID IS NULL"

			+ " AND I_IsImported<>'Y'").append(clientCheck);

		no = DB.executeUpdate(sql.toString());

		log.config("Invalid Especialidad=" + no);

		

		

//		-------------------------------------------------------------------

		int noInsert = 0;

		int noUpdate = 0;

		//int noInsertPO = 0;

		//int noUpdatePO = 0;



		//	Go through Records

		log.fine("start inserting/updating ...");

		sql = new StringBuffer ("SELECT I_EXME_ProductEsp_ID "

			+ "FROM I_EXME_ProductEsp WHERE I_IsImported='N'").append(clientCheck);

		Connection conn = DB.createConnection(false, Connection.TRANSACTION_READ_COMMITTED);

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
		try

		{

			//	Insertar producto especialidad a partir de la importacion

			PreparedStatement pstmt_insertProductEsp = conn.prepareStatement

				("INSERT INTO EXME_ProductEsp ("

				+ "AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,"

				+ "EXME_Especialidad_ID, M_Product_ID) "

				+ "SELECT "

				+ "AD_Client_ID,AD_Org_ID,'Y',SysDate,CreatedBy,SysDate,UpdatedBy,"

				+ "EXME_Especialidad_ID, M_Product_ID "

				+ "FROM I_EXME_ProductEsp "

				+ "WHERE I_EXME_ProductEsp_ID=?");





			/*	Update producto especialidad from Import

			PreparedStatement pstmt_updateProductEsp = conn.prepareStatement

				("UPDATE EXME_ProductEsp "

				+ "SET (Updated,UpdatedBy,EXME_Especialidad_ID, M_Product_ID)= "

				+ "(SELECT SysDate,UpdatedBy,EXME_Especialidad_ID, M_Product_ID"

				+ " FROM I_EXME_ProductEsp WHERE I_EXME_ProductEsp_ID=?) "

				+ "WHERE EXME_Especialidad_ID=? AND M_Product_ID=?");*/



			

			//	Set Imported = Y

			PreparedStatement pstmt_setImported = conn.prepareStatement

				("UPDATE I_EXME_ProductEsp SET I_IsImported='Y', "

				+ "Updated=SysDate, Processed='Y' WHERE I_EXME_ProductEsp_ID=?");



			//

			pstmt = DB.prepareStatement(sql.toString());

			rs = pstmt.executeQuery();

			while (rs.next())

			{

				int I_EXME_ProductEsp_ID = rs.getInt(1);

				//int EXME_ProductEsp_ID = rs.getInt(2);

				//boolean newProductEsp = EXME_ProductEsp_ID == 0;

				log.fine("I_EXME_ProductEsp_ID=" + I_EXME_ProductEsp_ID);



				//	producto especialidad

				/*if (newProductEsp)	//	Insert new producto especialidad

				{*/

					try

					{

						/*EXME_ProductEsp_ID = DB.getNextID(m_AD_Client_ID, "EXME_ProductEsp", null);

						if (EXME_ProductEsp_ID <= 0)

							throw new DBException("No Next ID (" + EXME_ProductEsp_ID + ")");

						pstmt_insertProductEsp.setInt(1, EXME_ProductEsp_ID);*/

						pstmt_insertProductEsp.setInt(1, I_EXME_ProductEsp_ID);

						//

						no = pstmt_insertProductEsp.executeUpdate();

						log.finer("Insert producto especialidad = " + no);

						noInsert++;

					}

					catch (Exception ex)

					{

						log.warning("Insert producto especialidad  - " + ex.toString());

						sql = new StringBuffer ("UPDATE I_EXME_ProductEsp i "

							+ "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||")

							.append(DB.TO_STRING("Insert producto especialidad: " + ex.toString()))

							.append("WHERE I_EXME_ProductEsp_ID=").append(I_EXME_ProductEsp_ID);

						DB.executeUpdate(sql.toString());

						continue;

					}

				/*}

				else	//	Update producto especialidad

				{

					pstmt_updateProductEsp.setInt(1, I_EXME_ProductEsp_ID);

					//pstmt_updateProductEsp.setInt(2, EXME_ProductEsp_ID);

					try

					{

						no = pstmt_updateProductEsp.executeUpdate();

						log.finer("Update producto especialidad = " + no);

						noUpdate++;

					}

					catch (SQLException ex)

					{

						log.warning("Update producto especialidad - " + ex.toString());

						sql = new StringBuffer ("UPDATE I_EXME_ProductEsp i "

							+ "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||").append(DB.TO_STRING("Update producto especialidad: " + ex.toString()))

							.append("WHERE I_EXME_ProductEsp_ID=").append(I_EXME_ProductEsp_ID);

						DB.executeUpdate(sql.toString());

						continue;

					}

					

				}*/

				

				//	Update I_EXME_ProductEsp

				//pstmt_setImported.setInt(1, EXME_ProductEsp_ID);

				pstmt_setImported.setInt(1, I_EXME_ProductEsp_ID);

				no = pstmt_setImported.executeUpdate();

				//

				conn.commit();

			}

			rs.close();

			pstmt.close();



			//

			pstmt_insertProductEsp.close();

			//pstmt_updateProductEsp.close();

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

			catch (SQLException ex){}
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



		//	Set Error to indicator to not imported

		sql = new StringBuffer ("UPDATE I_EXME_ProductEsp "

			+ "SET I_IsImported='N', Updated=SysDate "

			+ "WHERE I_IsImported<>'Y'").append(clientCheck);

		no = DB.executeUpdate(sql.toString());

		addLog (0, null, new BigDecimal (no), "@Errors@");

		addLog (0, null, new BigDecimal (noInsert), "@EXME_ProductEsp_ID@: @Inserted@");

		addLog (0, null, new BigDecimal (noUpdate), "@EXME_ProductEsp_ID@: @Updated@");

		

		return "";

        

    }



}