package org.compiere.process;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.struts.util.LabelValueBean;
import org.compiere.model.X_EXME_Vacuna;
import org.compiere.model.X_EXME_VacunaDet;
import org.compiere.model.X_I_EXME_Vacuna;
import org.compiere.util.DB;
import org.compiere.util.Trx;


public class ImportVacunas extends SvrProcess {

	/**	Client to be imported to		*/
	private int				m_AD_Client_ID = 0;

	/**	Delete old Imported				*/
	private boolean			m_deleteOldImported = false;

	/** Organization to be imported to	*/
	private int				m_AD_Org_ID = 0;

	@Override
	protected void prepare()
	{

		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++)
		{

			String name = para[i].getParameterName();
			if (name.equals("AD_Client_ID"))
				m_AD_Client_ID = ((BigDecimal)para[i].getParameter()).intValue();

			if (name.equals("DeleteOldImported"))
				m_deleteOldImported = "Y".equals(para[i].getParameter());

			if (name.equals("AD_Org_ID"))
				m_AD_Org_ID = ((BigDecimal)para[i].getParameter()).intValue();
		}

	}	//	prepare

	@Override
	/**
	 *  Perrform process.
	 *  EXME_Vacuna
	 *  @return Message
	 *  @throws Exception
	 */
	protected String doIt() throws java.lang.Exception

	{

		try {

			//ImportVacunas importacion = new ImportVacunas();//getCtx(), m_AD_Client_ID, m_AD_Org_ID,
					//m_deleteOldImported, get_TrxName());
			LabelValueBean result = importacion();
			addLog (0, null, new BigDecimal (result.getLabel()), "@Errors@");
			addLog (0, null, new BigDecimal (result.getValue()), "@EXME_Vacuna_ID@: @Inserted@");

		}
		catch (Exception e) {
			e.printStackTrace();
		}

		return "";

	}	//	doIt


	
	/**
	 *  Perrform process.
	 *  EXME_Vacuna
	 (
	 EXME_Vacuna_id                  NUMBER   (10)                    NOT NULL
	 , ad_client_id                     NUMBER   (10)                    NOT NULL
	 , ad_org_id                        NUMBER   (10)                    NOT NULL
	 , isactive                         CHAR     (1)                     DEFAULT 'Y' NOT NULL
	 , created                          DATE                             DEFAULT SYSDATE NOT NULL
	 , createdby                        NUMBER   (10)                    NOT NULL
	 , updated                          DATE                             DEFAULT SYSDATE NOT NULL
	 , updatedby                        NUMBER   (10)                    NOT NULL
	 , exme_equipo_id                   NUMBER   (10)                    NOT NULL
	 , fecha_ini                        DATE                             NOT NULL
	 , fecha_fin                        DATE                             NOT NULL
	 , exme_area_id                     NUMBER   (10)                    NOT NULL
	 , estatus_equipo                   CHAR     (1)                     NOT NULL
	 
	 )
	 *  @return Message
	 *  @throws Exception
	 */
	protected LabelValueBean importacion() throws java.lang.Exception
	
	{
		
		StringBuilder sql = null;
		int no = 0;
		String clientCheck = " AND AD_Client_ID=" + m_AD_Client_ID + " AND AD_Org_ID=" + m_AD_Org_ID;
		
		//	****	Prepare	****
		//	Delete Old Imported
		if (m_deleteOldImported)
		{
			sql = new StringBuilder ("DELETE I_EXME_Vacuna WHERE I_IsImported='Y'").append(clientCheck);
			no = DB.executeUpdate(sql.toString(), null);
			log.fine("Delete Old Impored =" + no);
		}
		
		//Actualiza valores de los no importados
		//	Set Client, Org, IsActive, Created/Updated
		sql = new StringBuilder ("UPDATE I_EXME_Vacuna ")
		.append(" SET AD_Client_ID = COALESCE (AD_Client_ID, ").append(m_AD_Client_ID).append("), ")
		.append(" AD_Org_ID = COALESCE (AD_Org_ID, ").append(m_AD_Org_ID).append("), ")
		.append(" IsActive = COALESCE (IsActive, 'Y'), ")
		.append(" Created = COALESCE (Created, SysDate), ")
		.append(" CreatedBy = COALESCE (CreatedBy, 0), ")
		.append(" Updated = COALESCE (Updated, SysDate), ")
		.append(" UpdatedBy = COALESCE (UpdatedBy, 0), ")
		.append(" I_ErrorMsg = NULL, ")
		.append(" I_IsImported = 'N' ")
		.append(" WHERE I_IsImported<>'Y' OR I_IsImported IS NULL ");
		no = DB.executeUpdate(sql.toString(), null);
		log.fine("Reset=" + no);
		
		
		//	Existing M_Product_ID ?
		sql = new StringBuilder (" UPDATE I_EXME_Vacuna i ")
		.append(" SET i.M_Product_ID=(SELECT e.M_Product_ID FROM M_Product e ")
		.append(" WHERE trim(i.M_Product_Value)=trim(e.Value) AND e.AD_Client_ID IN (i.AD_Client_ID,0)) ")
		.append(" WHERE i.M_Product_Value IS NOT NULL")
		.append(" AND i.I_IsImported='N'").append(clientCheck);
		no = DB.executeUpdate(sql.toString(), null);
		log.fine("Found M_Product_Value=" + no);
		
		sql = new StringBuilder ("UPDATE I_EXME_Vacuna ")
		.append(" SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg || 'ERR=Invalida llave de busqueda de product'  ")
		.append(" WHERE M_Product_ID IS NULL ")
		.append(" AND I_IsImported='N' ").append(clientCheck);
		no = DB.executeUpdate(sql.toString(), null);
		log.config("Invalid M_Product_ID=" + no);
		
		
		//	Existing EXME_Diagnostico_ID ?
		sql = new StringBuilder (" UPDATE I_EXME_Vacuna i ")
		.append(" SET i.EXME_Diagnostico_ID=(SELECT e.EXME_Diagnostico_ID FROM EXME_Diagnostico e ")
		.append(" WHERE trim(i.EXME_Diagnostico_Value)=trim(e.Value) AND e.AD_Client_ID IN (i.AD_Client_ID,0)) ")
		.append(" WHERE i.EXME_Diagnostico_Value IS NOT NULL")
		.append(" AND i.I_IsImported='N'").append(clientCheck);
		no = DB.executeUpdate(sql.toString(), null);
		log.fine("Found EXME_Diagnostico_Value=" + no);
		
		sql = new StringBuilder ("UPDATE I_EXME_Vacuna ")
		.append(" SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg || 'ERR=Invalida llave de busqueda de Diagnostico2'  ")
		.append(" WHERE EXME_Diagnostico_ID IS NULL ")
		.append(" AND I_IsImported='N' ").append(clientCheck);
		no = DB.executeUpdate(sql.toString(), null);
		log.config("Invalid EXME_Diagnostico_ID=" + no);
		
		
		//	Existing EXME_Diagnostico2_ID ?
		sql = new StringBuilder (" UPDATE I_EXME_Vacuna i ")
		.append(" SET i.EXME_Diagnostico2_ID=(SELECT e.EXME_Diagnostico_ID FROM EXME_Diagnostico e ")
		.append(" WHERE trim(i.EXME_Diagnostico2_Value)=trim(e.Value) AND e.AD_Client_ID IN (i.AD_Client_ID,0)) ")
		.append(" WHERE i.EXME_Diagnostico2_Value IS NOT NULL")
		.append(" AND i.I_IsImported='N'").append(clientCheck);
		no = DB.executeUpdate(sql.toString(), null);
		log.fine("Found EXME_Diagnostico2_Value=" + no);
		
		sql = new StringBuilder ("UPDATE I_EXME_Vacuna ")
		.append(" SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg || 'ERR=Invalida llave de busqueda de Diagnostico2'  ")
		.append(" WHERE EXME_Diagnostico2_ID IS NULL AND EXME_Diagnostico2_Value IS NOT NULL ")
		.append(" AND I_IsImported='N' ").append(clientCheck);
		no = DB.executeUpdate(sql.toString(), null);
		log.config("Invalid EXME_Diagnostico2_ID=" + no);
		
		
		//	Existing EXME_Diagnostico3_ID ?
		sql = new StringBuilder (" UPDATE I_EXME_Vacuna i ")
		.append(" SET i.EXME_Diagnostico3_ID=(SELECT e.EXME_Diagnostico_ID FROM EXME_Diagnostico e ")
		.append(" WHERE trim(i.EXME_Diagnostico3_Value)=trim(e.Value) AND e.AD_Client_ID IN (i.AD_Client_ID,0)) ")
		.append(" WHERE i.EXME_Diagnostico3_Value IS NOT NULL")
		.append(" AND i.I_IsImported='N'").append(clientCheck);
		no = DB.executeUpdate(sql.toString(), null);
		log.fine("Found EXME_Diagnostico3_Value=" + no);
		
		sql = new StringBuilder ("UPDATE I_EXME_Vacuna ")
		.append(" SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg || 'ERR=Invalida llave de busqueda de Diagnostico3'  ")
		.append(" WHERE EXME_Diagnostico3_ID IS NULL AND EXME_Diagnostico3_Value IS NOT NULL ")
		.append(" AND I_IsImported='N' ").append(clientCheck);
		no = DB.executeUpdate(sql.toString(), null);
		log.config("Invalid EXME_Diagnostico3_ID=" + no);
		
		
		//	Existing EXME_Diagnostico4_ID ?
		sql = new StringBuilder (" UPDATE I_EXME_Vacuna i ")
		.append(" SET i.EXME_Diagnostico4_ID=(SELECT e.EXME_Diagnostico_ID FROM EXME_Diagnostico e ")
		.append(" WHERE trim(i.EXME_Diagnostico4_Value)=trim(e.Value) AND e.AD_Client_ID IN (i.AD_Client_ID,0)) ")
		.append(" WHERE i.EXME_Diagnostico4_Value IS NOT NULL")
		.append(" AND i.I_IsImported='N'").append(clientCheck);
		no = DB.executeUpdate(sql.toString(), null);
		log.fine("Found EXME_Diagnostico4_Value=" + no);
		
		sql = new StringBuilder ("UPDATE I_EXME_Vacuna ")
		.append(" SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg || 'ERR=Invalida llave de busqueda de Diagnostico4'  ")
		.append(" WHERE EXME_Diagnostico4_ID IS NULL AND EXME_Diagnostico4_Value IS NOT NULL ")
		.append(" AND I_IsImported='N' ").append(clientCheck);
		no = DB.executeUpdate(sql.toString(), null);
		log.config("Invalid EXME_Diagnostico4_ID=" + no);	
		

		//	Existing EXME_Diagnostico5_ID ?
		sql = new StringBuilder (" UPDATE I_EXME_Vacuna i ")
		.append(" SET i.EXME_Diagnostico5_ID=(SELECT e.EXME_Diagnostico_ID FROM EXME_Diagnostico e ")
		.append(" WHERE trim(i.EXME_Diagnostico5_Value)=trim(e.Value) AND e.AD_Client_ID IN (i.AD_Client_ID,0)) ")
		.append(" WHERE i.EXME_Diagnostico5_Value IS NOT NULL")
		.append(" AND i.I_IsImported='N'").append(clientCheck);
		no = DB.executeUpdate(sql.toString(), null);
		log.fine("Found EXME_Diagnostico5_Value=" + no);
		
		sql = new StringBuilder ("UPDATE I_EXME_Vacuna ")
		.append(" SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg || 'ERR=Invalida llave de busqueda de Diagnostico5'  ")
		.append(" WHERE EXME_Diagnostico5_ID IS NULL AND EXME_Diagnostico5_Value IS NOT NULL ")
		.append(" AND I_IsImported='N' ").append(clientCheck);
		no = DB.executeUpdate(sql.toString(), null);
		log.config("Invalid EXME_Diagnostico5_ID=" + no);	
		
		
		
		//	Existing C_UOM_Hrd_ID ?
		sql = new StringBuilder (" UPDATE I_EXME_Vacuna i ")
		.append(" SET i.C_UOM_Hrd_ID=(SELECT e.C_UOM_ID FROM C_UOM e ")
		.append(" WHERE trim(i.C_UOM_Hrd_Value)=trim(e.Name) AND e.AD_Client_ID IN (i.AD_Client_ID,0)) ")
		.append(" WHERE i.C_UOM_Hrd_Value IS NOT NULL")
		.append(" AND i.I_IsImported='N'").append(clientCheck);
		no = DB.executeUpdate(sql.toString(), null);
		log.fine("Found C_UOM_Hrd_Value=" + no);
		
		sql = new StringBuilder ("UPDATE I_EXME_Vacuna ")
		.append(" SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg || 'ERR=Invalida llave de busqueda de C_UOM_Hrd_Value'  ")
		.append(" WHERE C_UOM_Hrd_ID IS NULL AND C_UOM_Hrd_Value IS NOT NULL ")
		.append(" AND I_IsImported='N' ").append(clientCheck);
		no = DB.executeUpdate(sql.toString(), null);
		log.config("Invalid C_UOM_Hrd_Value=" + no);			
		
		
		
		//	Existing C_UOM_Hrd_ID ?
		sql = new StringBuilder (" UPDATE I_EXME_Vacuna i ")
		.append(" SET i.C_UOM_Det_ID=(SELECT e.C_UOM_ID FROM C_UOM e ")
		.append(" WHERE trim(i.C_UOM_Det_Value)=trim(e.Name) AND e.AD_Client_ID IN (i.AD_Client_ID,0)) ")
		.append(" WHERE i.C_UOM_Det_Value IS NOT NULL")
		.append(" AND i.I_IsImported='N'").append(clientCheck);
		no = DB.executeUpdate(sql.toString(), null);
		log.fine("Found C_UOM_Det_Value=" + no);
		
		sql = new StringBuilder ("UPDATE I_EXME_Vacuna ")
		.append(" SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg || 'ERR=Invalida llave de busqueda de C_UOM_Det_Value'  ")
		.append(" WHERE C_UOM_Det_ID IS NULL AND C_UOM_Det_Value IS NOT NULL ")
		.append(" AND I_IsImported='N' ").append(clientCheck);
		no = DB.executeUpdate(sql.toString(), null);
		log.config("Invalid C_UOM_Det_Value=" + no);	
		
		
		
		//	Existing Rel_Vacuna_ID ?
		sql = new StringBuilder (" UPDATE I_EXME_Vacuna i ")
		.append(" SET i.Rel_Vacuna_ID=(SELECT e.EXME_Vacuna_ID FROM EXME_Vacuna e ")
		.append(" WHERE trim(i.Rel_Vacuna_Value)=trim(e.Value) AND e.AD_Client_ID IN (i.AD_Client_ID,0)) ")
		.append(" WHERE i.Rel_Vacuna_Value IS NOT NULL")
		.append(" AND i.I_IsImported='N'").append(clientCheck);
		no = DB.executeUpdate(sql.toString(), null);
		log.fine("Found Rel_Vacuna_Value=" + no);
		
		sql = new StringBuilder ("UPDATE I_EXME_Vacuna ")
		.append(" SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg || 'ERR=Invalida llave de busqueda de Rel_Vacuna_ID'  ")
		.append(" WHERE Rel_Vacuna_ID IS NULL AND Rel_Vacuna_Value IS NOT NULL ")
		.append(" AND I_IsImported='N' ").append(clientCheck);
		no = DB.executeUpdate(sql.toString(), null);
		log.config("Invalid Rel_Vacuna_ID=" + no);
		

		
		//	Existing EXME_Vacuna_ID ?
		sql = new StringBuilder (" UPDATE I_EXME_Vacuna i ")
		.append(" SET i.EXME_Vacuna_ID=(SELECT e.EXME_Vacuna_ID FROM EXME_Vacuna e ")
		.append(" WHERE trim(i.Value)=trim(e.Value) AND e.AD_Client_ID IN (i.AD_Client_ID,0)) ")
		.append(" WHERE i.Value IS NOT NULL")
		.append(" AND i.I_IsImported='N'").append(clientCheck);
		no = DB.executeUpdate(sql.toString(), null);
		log.fine("Found Value=" + no);
		
		/**sql = new StringBuilder ("UPDATE I_EXME_Vacuna ")
		.append(" SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg || 'ERR=Invalida llave de busqueda de EXME_Vacuna_ID'  ")
		.append(" WHERE EXME_Vacuna_ID IS NULL ")
		.append(" AND I_IsImported='N' ").append(clientCheck);
		no = DB.executeUpdate(sql.toString(), null);
		log.config("Invalid EXME_Vacuna_ID=" + no);**/
		
		DB.commit(true, null);
		
		
		//	V A L I D A C I O N E S -------------------------------------------------------------------
		
		sql = new StringBuilder (" SELECT I_EXME_Vacuna_ID ")
		.append(" FROM I_EXME_Vacuna ")
		.append(" WHERE I_IsImported='N' ")
		.append(" AND ( Value is null  ")
		.append(" OR value                is  null   ")
		.append(" OR m_product_id         is  null   ")
		.append(" OR exme_diagnostico_id  is  null   ")
		.append(" OR via                  is  null   ")
		.append(" OR sexo                 is  null   ")
		.append(" OR incluyecartilla      is  null   ")
		.append(" OR secuencia            is  null   ")
		.append(" OR tipodosis            is  null   ")
		.append(" OR edadminima           is  null   ")
		.append(" OR edadmaxima           is  null ) ")   
		
		.append(clientCheck);
		
		Statement pstmt = null;
		ResultSet rs = null;
		PreparedStatement pstmt_setImported = null;
		try
		{
			pstmt = DB.createStatement();//, trxName);
			rs = pstmt.executeQuery(sql.toString());
			while (rs.next())
			{
				sql = new StringBuilder (" UPDATE I_EXME_Vacuna  ")
				.append(" SET I_IsImported='E', I_ErrorMsg = I_ErrorMsg ||  'FALTAN DATOS REQUERIDOS'  ")
				.append(" WHERE I_EXME_Vacuna_ID=").append(rs.getInt("I_EXME_Vacuna_ID"));
				DB.executeUpdate(sql.toString(), null);
				no++;
			}
		}	
		catch(Exception ex)
		{
			sql = new StringBuilder ("UPDATE I_EXME_Vacuna i ")
			.append(" SET I_IsImported='E', I_ErrorMsg = I_ErrorMsg ||  ':  " + ex.getMessage().toString())
			.append("'  WHERE I_EXME_Vacuna_ID=").append(rs.getInt("I_EXME_Vacuna_ID"));
			DB.executeUpdate(sql.toString(), null);
			no++;
		}
		
		DB.commit(true, null);
		
		//	-------------------------------------------------------------------

		
		int noInsert = 0;
		
		//	Go through Records
		sql = new StringBuilder (" SELECT I_EXME_Vacuna_ID, ")
		.append(" Value , ")
		.append(" M_Product_ID , ")
		.append(" EXME_Diagnostico_ID , ")
		.append(" Via ,")
		.append(" Sexo , ")
		.append(" IncluyeCartilla , ")
		.append(" Rel_Vacuna_ID , ")
		.append(" c_uom_hrd_id , ")
		.append(" Cantidad , ")
		.append(" EXME_Diagnostico2_ID , ")
		.append(" EXME_Diagnostico3_ID , ")
		.append(" EXME_Diagnostico4_ID , ")
		.append(" EXME_Diagnostico5_ID , ")
		.append(" Description , ")
		.append(" Secuencia , ")
		.append(" TipoDosis , ")
		.append(" EdadMinima , ")
		.append(" EdadMaxima , ")
		.append(" EXME_Vacuna_ID , ")
		.append(" c_uom_det_id , ")
		.append(" Intervalo  ")
		.append(" FROM I_EXME_Vacuna ")
		.append(" WHERE I_IsImported='N' ");
		//.append(clientCheck);
		
		Trx m_trx = null;
		
		try
		{
			pstmt = DB.createStatement();
			rs = pstmt.executeQuery(sql.toString());
		//	m_trx = Trx.get(null,true);
			while (rs.next())
			{
				try
				{
					int I_EXME_Vacuna_ID = rs.getInt("I_EXME_Vacuna_ID");
					int EXME_Vacuna_ID = rs.getInt("EXME_Vacuna_ID");
					
					//X_I_EXME_Vacuna iVacuna= new X_I_EXME_Vacuna(getCtx(), I_EXME_Vacuna_ID, null);
					boolean error = false;
					
					if(EXME_Vacuna_ID<=0)
					{
						X_EXME_Vacuna vacuna = new X_EXME_Vacuna(getCtx(), 0, null);
						vacuna.setValue(rs.getString("Value"));
						vacuna.setM_Product_ID(rs.getInt("M_Product_ID"));
						vacuna.setEXME_Diagnostico_ID(rs.getInt("EXME_Diagnostico_ID"));
						vacuna.setVia(rs.getString("Via"));
						vacuna.setSexo(rs.getString("Sexo"));
						vacuna.setIncluyeCartilla(rs.getString("IncluyeCartilla").equalsIgnoreCase("Y")?true:false);
						
						vacuna.setRel_Vacuna_ID(rs.getInt("Rel_Vacuna_ID"));
						vacuna.setC_UOM_ID(rs.getInt("c_uom_hrd_id"));
						vacuna.setCantidad(rs.getBigDecimal("Cantidad"));
						vacuna.setEXME_Diagnostico2_ID(rs.getInt("EXME_Diagnostico2_ID"));
						vacuna.setEXME_Diagnostico3_ID(rs.getInt("EXME_Diagnostico3_ID"));
						vacuna.setEXME_Diagnostico4_ID(rs.getInt("EXME_Diagnostico4_ID"));
						vacuna.setEXME_Diagnostico5_ID(rs.getInt("EXME_Diagnostico5_ID"));
						vacuna.setDescription(rs.getString("Description"));
						
						if (!vacuna.save())
						{
							sql = new StringBuilder (" UPDATE I_EXME_Vacuna  ")
							.append(" SET I_IsImported='E', I_ErrorMsg = I_ErrorMsg ||  'Imposible insertar registro'  ")
							.append(" WHERE I_EXME_Vacuna_ID= ? ");
							
							pstmt_setImported = DB.prepareStatement(sql.toString(), null);
							pstmt_setImported.setInt(1, I_EXME_Vacuna_ID);
							no = pstmt_setImported.executeUpdate();
							//noInsert++;
							error = true;
						}
						EXME_Vacuna_ID = vacuna.getEXME_Vacuna_ID();
					}

					
					
					
					X_EXME_VacunaDet vacunaDet = null;
					if(EXME_Vacuna_ID>0 && !error)
					{
						vacunaDet = new X_EXME_VacunaDet(getCtx(), 0, null);
						vacunaDet.setSecuencia(rs.getInt("Secuencia"));
						
						if(!rs.getString("TipoDosis").equalsIgnoreCase(X_EXME_VacunaDet.TIPODOSIS_Additional)
							&& !rs.getString("TipoDosis").equalsIgnoreCase(X_EXME_VacunaDet.TIPODOSIS_First)
							&& !rs.getString("TipoDosis").equalsIgnoreCase(X_EXME_VacunaDet.TIPODOSIS_Only)
							&& !rs.getString("TipoDosis").equalsIgnoreCase(X_EXME_VacunaDet.TIPODOSIS_Preliminary)
							&& !rs.getString("TipoDosis").equalsIgnoreCase(X_EXME_VacunaDet.TIPODOSIS_Reinforcement)
							&& !rs.getString("TipoDosis").equalsIgnoreCase(X_EXME_VacunaDet.TIPODOSIS_Reinforcement1)
							&& !rs.getString("TipoDosis").equalsIgnoreCase(X_EXME_VacunaDet.TIPODOSIS_Reinforcement2)
							&& !rs.getString("TipoDosis").equalsIgnoreCase(X_EXME_VacunaDet.TIPODOSIS_Second)
							&& !rs.getString("TipoDosis").equalsIgnoreCase(X_EXME_VacunaDet.TIPODOSIS_Third))
						{
							
							sql = new StringBuilder (" UPDATE I_EXME_Vacuna  ")
							.append(" SET I_IsImported='E', I_ErrorMsg = I_ErrorMsg ||  'el tipo no esta validado'  ")
							.append(" WHERE I_EXME_Vacuna_ID= ? ");

							pstmt_setImported = DB.prepareStatement(sql.toString(), null);
							pstmt_setImported.setInt(1, I_EXME_Vacuna_ID);
							no = pstmt_setImported.executeUpdate();
							noInsert++;
							
							
						}
						else
						{

							vacunaDet.setTipoDosis(rs.getString("TipoDosis"));//
							vacunaDet.setEdadMinima(rs.getBigDecimal("EdadMinima"));
							vacunaDet.setEdadMaxima(rs.getBigDecimal("EdadMaxima"));
							vacunaDet.setEXME_Vacuna_ID(EXME_Vacuna_ID);
							vacunaDet.setC_UOM_ID(rs.getInt("c_uom_det_id"));
							vacunaDet.setIntervalo(rs.getBigDecimal("Intervalo"));

							if ( !vacunaDet.save() || EXME_Vacuna_ID<=0 ){
								sql = new StringBuilder (" UPDATE I_EXME_Vacuna  ")
								.append(" SET I_IsImported='E', I_ErrorMsg = I_ErrorMsg ||  'Imposible insertar registro'  ")
								.append(" WHERE I_EXME_Vacuna_ID= ? ");

								pstmt_setImported = DB.prepareStatement(sql.toString(), null);
								pstmt_setImported.setInt(1, I_EXME_Vacuna_ID);
								no = pstmt_setImported.executeUpdate();
								noInsert++;
							}
							else
							{
								X_I_EXME_Vacuna i_vacuna = new X_I_EXME_Vacuna(getCtx(), I_EXME_Vacuna_ID, null);
								i_vacuna.setI_IsImported(true);
								i_vacuna.setProcessed(true);
								
								if (!i_vacuna.save()){
									sql = new StringBuilder (" UPDATE I_EXME_Vacuna  ")
									.append(" SET I_IsImported='E', I_ErrorMsg = I_ErrorMsg ||  'Imposible actualizar registro de importacion pero fue insertado en catalogo'  ")
									.append(" WHERE I_EXME_Vacuna_ID= ? ");
									
									pstmt_setImported = DB.prepareStatement(sql.toString(), null);
									pstmt_setImported.setInt(1, I_EXME_Vacuna_ID);
									no = pstmt_setImported.executeUpdate();
									noInsert++;
								}
								
								no++;
							}
						}
					}

					DB.commit(true, null);
					
					
				}
				catch(Exception ex)
				{
					sql = new StringBuilder ("UPDATE I_EXME_Vacuna i ")
					.append(" SET I_IsImported='E', I_ErrorMsg = I_ErrorMsg ||  ':  " + ex.getMessage().toString())
					.append("'  WHERE I_EXME_Vacuna_ID=").append(rs.getInt("I_EXME_Vacuna_ID"));
					DB.executeUpdate(sql.toString(), null);
					//no++;
					continue;
					
				}
				

			}
			
			if(m_trx!=null){
				m_trx.rollback();
				m_trx.close();
			}
			
			m_trx = null;
			
			DB.commit(true, null);
			
			if(pstmt!=null)
				pstmt.close();
			
			if(pstmt_setImported!=null)
				pstmt_setImported.close();
			
			pstmt = null;
			pstmt_setImported = null;
			
			if(rs!=null)
				rs.close();
			rs = null;
			
		
		}
		catch (SQLException e)
		{
			throw new Exception ("Import Vacunas.doIt", e);
		}
		finally
		{
			//expert
			if(pstmt!=null)
				pstmt.close();
			pstmt = null;
			if(rs!=null)
				rs.close();
			rs = null;
			if(m_trx!=null){
				m_trx.rollback();
				m_trx.close();
			}
			
			m_trx = null;
			
		}
		
		//addLog (0, null, new BigDecimal (no), "@Errors@");
		//addLog (0, null, new BigDecimal (noInsert), "@EXME_Vacuna_ID@: @Inserted@");
		
		LabelValueBean result = new LabelValueBean(String.valueOf(no), String.valueOf(noInsert));
		return result;
		
	}	//	doIt
	

}

