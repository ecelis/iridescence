package org.compiere.process;



import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.logging.Level;


import org.compiere.model.MCountry;
import org.compiere.model.MLocation;
import org.compiere.model.MOrg;
import org.compiere.model.MOrgInfo;
import org.compiere.model.MRegion;
import org.compiere.model.X_I_AD_Org;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Trx;



/**

 * Proceso para importar Organizaciones
 *
 * <b>Modificado: </b> $Author: otorres $<p>
 * <b>En :</b> $Date: 2006/10/11 17:12:08 $<p>
 *
 * @author Omar Torres Atonal
 * @version $Revision: 1.7 $
 */

public class ImportOrganizacion extends SvrProcess {


	/**	Client to be imported to		*/
	private int			 	m_AD_Client_ID = 0;

	/** Organization to be imported to  */
	//private int             m_AD_Org_ID = 0;

	/**	Delete old Imported				*/
	private boolean	m_deleteOldImported = false;


	/**

	 * Obtener los valores de los par&aacute;metros

	 */

	protected void prepare() {

		ProcessInfoParameter[] para = getParameter();

		for (int i = 0; i < para.length; i++) {
			String name = para[i].getParameterName();

			if (name.equals("AD_Client_ID"))
				m_AD_Client_ID = ((BigDecimal)para[i].getParameter()).intValue();
			/*else if (name.equals("AD_Org_ID"))
				m_AD_Org_ID = ((BigDecimal)para[i].getParameter()).intValue();*/
			else if (name.equals("DeleteOldImported"))
				m_deleteOldImported = "Y".equals(para[i].getParameter());
			else
				log.log(Level.SEVERE, "Unknown Parameter: " + name);

		}



	}



	/**
	 * Corre el proceso.
	 * @return Un mensaje de estado
	 * @throws Exception
	 */
	protected String doIt() throws Exception {

		StringBuffer sql = null;
		int no = 0;

		//aumentar la parte del cliente en el sql
		String clientCheck = " AND AD_Client_ID=" + m_AD_Client_ID;

		//	****	Prepare	****

		//	Delete Old Imported
		if (m_deleteOldImported) {

			sql = 
				new StringBuffer (
						"DELETE I_AD_Org "
						+ "WHERE I_IsImported='Y'"
				).append(clientCheck);

			no = DB.executeUpdate(sql.toString(),this.get_TrxName());

			log.info("Delete Old Impored =" + no);
		}



		//	Set Client, Org, IaActive, Created/Updated,
		/*sql = 
			new StringBuffer (
					"UPDATE I_AD_Org "
					+ "SET AD_Client_ID = COALESCE (AD_Client_ID, ")
			.append(m_AD_Client_ID)
			.append(")," + 
					" AD_Org_ID = COALESCE (AD_Org_ID, 0),"
					+ " IsActive = COALESCE (IsActive, 'Y'),"
					+ " Created = COALESCE (Created, SysDate),"
					+ " CreatedBy = COALESCE (CreatedBy, 0),"
					+ " Updated = COALESCE (Updated, SysDate),"
					+ " UpdatedBy = COALESCE (UpdatedBy, 0),"
					+ " I_ErrorMsg = NULL,"
					+ " I_IsImported = 'N' "
					+ "WHERE I_IsImported<>'Y' OR I_IsImported IS NULL"
			);

		no = DB.executeUpdate(sql.toString(),this.get_TrxName());

		log.info("Reset=" + no);

		//establecemos la organizacion
		sql = 
			new StringBuffer(
					"UPDATE I_AD_Org io " +
					"SET AD_Org_ID = (SELECT AD_Org_ID " +
					" FROM AD_Org o WHERE trim(o.Value) = trim( io.Value) "+
					" AND o.AD_Client_ID IN  (io.AD_Client_ID,0)) " +
					"WHERE io.Value IS NOT NULL " +
					"AND io.I_IsImported = 'N' ")
			.append(clientCheck);
		
		no = DB.executeUpdate(sql.toString(),this.get_TrxName());
		log.info("Set AD_Org_Padre=" + no);

		//establecemos la organizacion padre
		sql = 
			new StringBuffer(
					"UPDATE I_AD_Org io " +
					"SET AD_Org_Padre_ID = (SELECT AD_Org_ID " +
					" FROM AD_Org o WHERE trim(o.Value) =trim( io.AD_Org_Padre_Value) "+
					" AND o.AD_Client_ID IN  (io.AD_Client_ID,0)) " +
					"WHERE io.AD_Org_Padre_Value IS NOT NULL " +
					"AND io.I_IsImported = 'N' ")
			.append(clientCheck);

		no = DB.executeUpdate(sql.toString(),this.get_TrxName());
		log.info("Set AD_Org_Padre=" + no);

		//Municipio
		sql = new StringBuffer("UPDATE I_AD_Org io " +
				"SET EXME_TownCouncil_ID = (SELECT m.EXME_TownCouncil_ID " +
				" FROM EXME_TownCouncil m WHERE trim(m.Value) =trim( io.EXME_TownCouncil_Value) "+
				" AND m.AD_Client_ID IN  (io.AD_Client_ID,0)) " +
				"WHERE io.EXME_TownCouncil_Value IS NOT NULL " +
		"AND io.I_IsImported = 'N' ").append(clientCheck);

		no = DB.executeUpdate(sql.toString(),this.get_TrxName());
		log.info("Set EXME_TownCouncil_ID=" + no);


		DB.commit(true,this.get_TrxName());*/


		//
		int noInsert = 0;
		int noUpdate = 0;   
		int noErrores=0;

		int noInsertInfo = 0;
		int noUpdateInfo = 0;   
		int noErroresInfo=0;


		// Todos los registros

		log.fine("start inserting/updating ...");

		sql = 
			new StringBuffer (
					"SELECT * "
					+ "FROM I_AD_Org WHERE I_IsImported='N'"
			).append(clientCheck)
			.append(" AND Value IS NOT NULL ")
			.append("ORDER BY I_AD_Org_ID ");

		Statement stmt = null;
		ResultSet rs = null;

		try {

			stmt = DB.createStatement();

			rs = stmt.executeQuery(sql.toString());

			//int iORgID;	

			while(rs.next()) 
			{

				try
				{
					//iORgID = rs.getInt("I_AD_Org_ID");

					X_I_AD_Org io = new X_I_AD_Org(getCtx(), rs,get_TrxName()+"I");

					boolean nuevoRegistro;


					MOrg o= new MOrg(this.getCtx(),io.getAD_Org_ID(),this.get_TrxName()+"M");
					nuevoRegistro=o.getAD_Org_ID()==0?true:false;

					o.setAD_Org_ID(io.getAD_Org_ID());
					o.setValue(io.getValue());
					o.setName(io.getName());
					o.setDescription(io.getDescription());
					o.setIsSummary(io.isSummary());


					if(o.save(this.get_TrxName()+"M"))
					{
						DB.commit(true,this.get_TrxName()+"M");

						if(nuevoRegistro)
							noInsert++;
						else
							noUpdate++;

						MOrgInfo info= MOrgInfo.get(getCtx(),o.getAD_Org_ID());

						if(info==null)
							info=  new MOrgInfo(o);

						if(io.getDUNS() != null)
							info.setDUNS(io.getDUNS());
						else
							info.setDUNS("?");

						if(io.getRFC_TAXID() != null)
							info.setTaxID(io.getRFC_TAXID());
						else
							info.setTaxID("?");

						if(io.getAD_Org_Padre_Value() != null && io.getAD_Org_Padre_Value().length() > 0)
							info.setParent_Org_ID(io.getAD_Org_Padre_ID());
						
						
						info.setC_Location_ID(insertActualizaLocation(io.getAD_Org_ID(),this.getCtx(),this.get_TrxName(), log));

						io.setProcessed(true);
						io.setProcessing(true);
						io.setI_IsImported(true);

						if(info.save(this.get_TrxName()+"F"))    			    
						{
							if(io.save(this.get_TrxName()+"I"))
							{
								if(nuevoRegistro)
									noInsertInfo++;
								else
									noUpdateInfo++;

							}
							else
							{
								errorActualiza(sql,io.getAD_Org_ID()," No fue posible insertar el registro X_I_AD_Org,",this.get_TrxName()+"I");  //Guarda un mensaje de error en el registro actual
								noErroresInfo++;          
								continue;
							}
						}
						else
						{
							errorActualiza(sql,io.getAD_Org_ID()," No fue posible insertar el registro AD_OrgInfo,",this.get_TrxName()+"F");  //Guarda un mensaje de error en el registro actual
							noErroresInfo++;          
							continue;
						} 	                 

					}
					else
					{
						errorActualiza(sql,io.getAD_Org_ID()," No fue posible insertar el registro AD_Org,",this.get_TrxName()+"M");  //Guarda un mensaje de error en el registro actual                		      
						noErrores++;
						continue;
					}

					//completamos la transaccion
					DB.commit(true,  this.get_TrxName()+"I");
					DB.commit(true,  this.get_TrxName()+"F");
				}
				catch (Exception e)
				{
					DB.rollback(false, this.get_TrxName());
					e.printStackTrace();
					log.warning("Importando Organizacion:" + e);
				}
			}

		} catch (Exception e) 
		{

			DB.rollback(false, this.get_TrxName());
			e.printStackTrace();
			log.warning("Importando Organizacion:" + e);

		}finally
		{
			if(rs != null)
				rs.close();

			if(stmt != null)
				stmt.close();

			rs=null;
			stmt=null;
		}

		addLog (0, null, new BigDecimal (noErrores), "@Errors@");
		addLog (0, null, new BigDecimal (noInsert), "@AD_Org@: @Inserted@");
		addLog (0, null, new BigDecimal (noUpdate), "@AD_Org@: @Updated@");       

		addLog (0, null, new BigDecimal (noErroresInfo), "@ErrorsInfo@");
		addLog (0, null, new BigDecimal (noInsertInfo), "@AD_OrgInfo@: @Inserted@");
		addLog (0, null, new BigDecimal (noUpdateInfo), "@AD_OrgInfo@: @Updated@");       

		Trx.get(this.get_TrxName(), false).close();



		return "";

	}





	/**
	 * Metodo para crear los locations del Medico (vgarcia)
	 * @param i_Exme_Medico_ID
	 * @param ctx
	 * @param trxName
	 * @param log
	 * @return
	 */
	public int  insertActualizaLocation( long I_AD_Org_ID,Properties ctx, String trxName, CLogger log){

		int IDLocation=0;
		PreparedStatement pstmt1 = null;
		ResultSet rsl = null;
		StringBuffer sql = null;
		trxName+="L";

		try
		{

			sql = new StringBuffer("select c_location_id, ct.c_country_id, r.c_region_id, " +        		 
					"o.address1, o.address2, o.colonia, o.city, o.postal, EXME_TownCouncil_ID  "+
					"from i_ad_ORg o " +
					"inner join c_country  ct on (trim(ct.countrycode)=trim(o.countrycode)) " +
					"inner join c_region r on(trim(r.name)=trim(o.C_Region_Value )) " +
			"where o.I_AD_Org_ID = ? and o.i_isimported<>'Y' ") ;


			pstmt1 = DB.prepareStatement(sql.toString(), trxName);
			pstmt1.setLong(1, I_AD_Org_ID);

			rsl = pstmt1.executeQuery();
			MLocation location = null;

			if (rsl.next()){

				location = MLocation.get(ctx, rsl.getInt(1), trxName); 

				location.setCountry(MCountry.get(ctx, rsl.getInt(2)));
				location.setRegion(MRegion.get(ctx,rsl.getInt(3)));
				location.setAddress1(rsl.getString(4));
				location.setAddress2(rsl.getString(5));
				location.setAddress3(rsl.getString(6));
				location.setCity(rsl.getString(7));
				location.setPostal(rsl.getString(8));
				location.setEXME_TownCouncil_ID(rsl.getInt(9));


				if(!location.save(trxName))
					errorActualiza(sql, Long.valueOf(I_AD_Org_ID).intValue()," No fue posible insertar C_Location_ID,",trxName);  //Guarda un mensaje de error en el registro actual
					else
						IDLocation=location.getC_Location_ID();

				DB.commit(true,trxName);

			}



		} catch(SQLException e) {

			errorActualiza(
					sql, 
					Long.valueOf(I_AD_Org_ID).intValue(),
					" No fue posible insertar C_Location_ID,",
					trxName
			);  //Guarda un mensaje de error en el registro actual


		} finally {
			try {
				if(pstmt1 != null)
					pstmt1.close();

				if(rsl != null)
					rsl.close();

				pstmt1=null;
				rsl=null;

			} catch (SQLException ex) {
				log.log(Level.SEVERE, "While inserting / updating location ", ex.getMessage());
			}
		}

		return IDLocation;

	}




	private  void errorActualiza(StringBuffer sql, int ID,String error, String trxName)
	{
		try
		{
			DB.rollback(false, trxName);     			        

			log.warning(error);
			sql = new StringBuffer ("UPDATE I_AD_Org  " 
					+ " SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg || '" + error 
					+ "'  WHERE I_AD_Org_ID=" + String.valueOf(ID));

			DB.executeUpdate(sql.toString(), trxName);
		}
		catch(Exception ex)
		{

		}
	}

}