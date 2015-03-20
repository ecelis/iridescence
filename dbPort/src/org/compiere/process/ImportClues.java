package org.compiere.process;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.logging.Level;

import org.compiere.model.MCountry;
import org.compiere.model.MLocation;
import org.compiere.model.MOrg;
import org.compiere.model.MOrgInfo;
import org.compiere.model.MRegion;
import org.compiere.model.X_I_EXME_ImportClues;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Trx;

/**

 * Proceso de importaci&oacute;n del archivo de Centros de Salud CLUES como organizaciones.<p>

 * Creado: 25/Feb/2009<p>

 * Modificado: $Date: $<p>

 * Por: $Author:  $<p>

 * 

 * @author Lizeth de la Garza

 * @version $Revision: 1.1 $

 */

public class ImportClues extends SvrProcess{
	private int			 	m_AD_Client_ID = 0;
	private int             m_AD_Org_ID = 0;
	private boolean         m_deleteOldImported = false;
	private Timestamp       m_DateValue = null;
	
	/**
	 * Se obtienen los valores de los par#metros
	 */
    
	protected void prepare() {
		
		ProcessInfoParameter[] para = getParameter();

		for (int i = 0; i < para.length; i++){
			String name = para[i].getParameterName();

			if(name.equals("AD_Client_ID")){
				m_AD_Client_ID = ((BigDecimal)para[i].getParameter()).intValue();
			}else if (name.equals("AD_Org_ID")){
				m_AD_Org_ID = ((BigDecimal)para[i].getParameter()).intValue();
			}else if (name.equals("DeleteOldImported")){
				m_deleteOldImported = "Y".equals(para[i].getParameter());
			}else{
				log.log(Level.SEVERE, "Unknown Parameter: " + name);
            }			
			if (m_DateValue == null){
	            m_DateValue = DB.getTimestampForOrg(getCtx());
			}
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
		 
			if (m_deleteOldImported){
				sql = new StringBuffer ("DELETE I_EXME_IMPORTCLUES ")
						.append("WHERE I_IsImported='Y'");

				no = DB.executeUpdate(sql.toString(), this.get_TrxName());

				log.info("Delete Old Imported =" + no);
			}

			//Prepara la tabla I_EXME_ImportClues
			sql = new StringBuffer ("UPDATE I_EXME_ImportClues ")
				.append( "SET AD_Client_ID = COALESCE (AD_Client_ID,").append(m_AD_Client_ID)
				.append("), AD_Org_ID = COALESCE (AD_Org_ID, 0),")
				.append(" IsActive = COALESCE (IsActive, 'Y'),")
				.append(" Created = COALESCE (Created, SysDate),")
				.append(" CreatedBy = COALESCE (CreatedBy, 0),")
				.append(" Updated = COALESCE (Updated, SysDate),")
				.append(" UpdatedBy = COALESCE (UpdatedBy, 0),")
				.append(" I_ErrorMsg = NULL,")
				.append(" I_IsImported = 'N' ")
				.append("WHERE I_IsImported<>'Y' OR I_IsImported IS NULL");

			no = DB.executeUpdate(sql.toString(), this.get_TrxName());
			log.info("Reset=" + no);
					
			//establecemos la organizacion
			sql = new StringBuffer("UPDATE I_EXME_ImportClues io ")
			        .append("SET Org_ID = (SELECT AD_Org_ID ")
			        .append(" FROM AD_Org o WHERE trim(o.Value) =trim( io.OrgValue))")
			      //.append(" AND o.AD_Client_ID IN  (io.AD_Client_ID,0)) ")
			        .append("WHERE io.OrgValue IS NOT NULL ")
			        .append("AND io.I_IsImported = 'N' ");
			no = DB.executeUpdate(sql.toString(), this.get_TrxName());
			log.info("Set AD_Org=" + no);
					
			//establecemos la Regi#n
			sql = new StringBuffer("UPDATE I_EXME_ImportClues io ")
			        .append("SET C_Region_ID = (SELECT m.C_Region_ID ")
			        .append(" FROM C_Region m WHERE trim(m.Value) =trim( io.Region))")
			      //.append(" AND m.AD_Client_ID IN  (io.AD_Client_ID,0)) " 
			        .append("WHERE io.Region IS NOT NULL ")
			        .append("AND io.I_IsImported = 'N' ");

			no = DB.executeUpdate(sql.toString(), this.get_TrxName());
			log.info("Set C_Region_ID=" + no);
	
			//establecemos el Municipio
			sql = new StringBuffer("UPDATE I_EXME_ImportClues io ")
					.append("SET EXME_TownCouncil_ID = (SELECT m.EXME_TownCouncil_ID ")
					.append(" FROM EXME_TownCouncil m WHERE trim(m.Value) =trim( io.EXME_TownCouncil_Value)")
					.append(" AND TRIM(m.C_REGION_ID)=TRIM(io.C_REGION_ID)) ")
			      //.append(" AND m.AD_Client_ID IN  (io.AD_Client_ID,0)) ")
					.append("WHERE io.EXME_TownCouncil_Value IS NOT NULL ")
					.append("AND io.I_IsImported = 'N' ");
			no = DB.executeUpdate(sql.toString(), this.get_TrxName());
			log.info("Set EXME_TownCouncil_ID=" + no);
			
			//establecemos la Localidad -- Comentarizada debido a que no hay datos en la tabla EXME_Localidad
			/*sql = new StringBuffer("UPDATE I_EXME_ImportClues io ")
			        .append("SET EXME_Localidad_ID = (SELECT m.EXME_Localidad_ID ")
			        .append(" FROM EXME_Localidad m WHERE trim(m.Value) =trim( io.EXME_Localidad_Value) ")
			        .append(" AND m.AD_Client_ID IN  (io.AD_Client_ID,0)) ")
			        .append("WHERE io.EXME_Localidad_Value IS NOT NULL ")
			        .append("AND io.I_IsImported = 'N' ");
			
			no = DB.executeUpdate(sql.toString(), this.get_TrxName());
			log.info("Set EXME_Localidad_ID=" + no);*/
			
			//establecemos la Tipologia
			sql = new StringBuffer("UPDATE I_EXME_ImportClues io ")
					.append("SET EXME_Tipologia_ID  = (SELECT m.Exme_Tipologia_ID ")
			        .append(" FROM EXME_Tipologia m WHERE trim(m.Value) =trim( io.EXME_Tipologia_Value)) ")
			      //.append(" AND m.AD_Client_ID IN  (io.AD_Client_ID,0)) ")
			        .append("WHERE io.EXME_Tipologia_Value IS NOT NULL ")
			        .append("AND io.I_IsImported = 'N' ");
	
			no = DB.executeUpdate(sql.toString(), this.get_TrxName());
			log.info("Set Tipologia=" + no);

			DB.commit(true, this.get_TrxName());
			
			//---------------------------------------------------------------------
			int noInsert = 0;
	        int noUpdate = 0;   
	        int noErrores=0;
	        int noErroresInfo=0;
			int noInsertInfo = 0;
	        int noUpdateInfo = 0;   
	        
			log.fine("start inserting/updating ...");
			
			//Selecciona los registros de la temporal que no esten importados
			sql = new StringBuffer ("SELECT I_EXME_ImportClues_ID ")
							.append("FROM I_EXME_ImportClues WHERE I_IsImported='N' ") 
							.append("AND OrgValue IS NOT NULL ");

	        Statement stmt = null;
	        ResultSet rs = null;
	        Statement stmtu = null;
	        ResultSet rsu = null;
	        StringBuffer sql_update = null;
	        
	        try {
				int iCluesID = 0;
				boolean issummary = false;				
				int parent = 0; 
				String update = null;

			    stmt = DB.createStatement();
				rs = stmt.executeQuery(sql.toString());

				while(rs.next()){
					try	{
						iCluesID = rs.getInt(1);
						boolean nuevoRegistro= true;
						X_I_EXME_ImportClues io = new X_I_EXME_ImportClues(getCtx(), iCluesID, null);

	                    //Se crea el objeto de para AD_Org            	
						MOrg o= null;
						//Variable que determina el value del centro de salud (nombre del clues)
						update = io.getOrgValue();
						
						//Trae registro si la organizacion ya fue anteriormente importada
						sql_update = new StringBuffer ("SELECT AD_ORG_ID FROM AD_ORG WHERE Value = '").append(update).append("'");
						stmtu = DB.createStatement();
						rsu = stmtu.executeQuery(sql_update.toString());
												
						//Compara si la organizacion ya ha sido importada, y especificar si es un nuevo registro o una actualizaci#n
						// de la informacion del Clues.
						if(rsu.next()){
							nuevoRegistro=false;
							o = new MOrg(this.getCtx(), rsu.getInt(1), this.get_TrxName());
						}else{
							o = new MOrg(this.getCtx(), io.getOrg_ID(), this.get_TrxName());     	
						}
						//Se guardan los datos de la organizacion en AD_Org
	                    o.setValue(io.getOrgValue());
	                    o.setName(io.getOrgName());
	                    o.setDescription(io.getOrgName());
	                    o.setIsSummary(issummary);	                 
	                   
	                    //Si se guardaron los datos en AD_Org continua para guardar en AD_OrgInfo
	                    if(o.save(this.get_TrxName())){
	                    	io.setOrg_ID(o.getAD_Org_ID());
	                    	
	                    	MOrgInfo info = null;
	                    	
	                    	//Compara si es nuevo registro o es registro ya existente
	                    	if(nuevoRegistro){	                    		
	                    		info = MOrgInfo.get(getCtx(), o.getAD_Org_ID(), this.get_TrxName());	                        	
	                    	}else{	                    		
	                    		info = MOrgInfo.get(getCtx(), o.getAD_Org_ID());	                    		
	                    	} 
	                    	
	                    	//Se guardan los datos en AD_OrgInfo
	                    	info.setParent_Org_ID(parent);
	                    	info.setTipo_Institucion(io.getTipoInstitucion());
	                    	info.setTipo_Unidad(io.getTipoUnidad());
	                    	info.setTipologia(io.getEXME_Tipologia_Value());
	                    	info.setEXME_Tipologia_ID(io.getEXME_Tipologia_ID());
	                    	
	                    	//compara si es un nuevo registro y realizar un insert, o es un registro ya existente
							//y realizar el update
	                    	if(nuevoRegistro){
	                    		info.setC_Location_ID(insertLocation(iCluesID, log));
	                    	}else{
	                    		info.setC_Location_ID(updateLocation(iCluesID, info.getC_Location_ID(), log));
	                       	}
	                    	
	                    	//Si se guardan los datos en AD_OrgInfo procede a definir el registro como Importado
	                    	if(info.save(this.get_TrxName())){ 
	                    		
	                    		//Se guarda informacion que detalla que el registro ha sido importado
	                    	    io.setProcessed(true);
	                        	io.setProcessing(true);
	                        	io.setI_IsImported(true);
	                        	
	                        	//Si se actualizan los datos en la tabla temporal, se incrementa el contador
	                    		if(io.save(this.get_TrxName())){	                    		
	                    			if(nuevoRegistro){
	                    				noInsert++;
	                    				noInsertInfo++;
	                    			}else{
	                    				noUpdate++;
	                    				noUpdateInfo++;
	                    			}
	                    		}else{	                    		
	                    			errorActualiza(sql, iCluesID, "No fue posible insertar el registro X_I_EXME_ImportClues,");  //Guarda un mensaje de error en el registro actual
	                    			noErroresInfo++;          
	                    			continue;
	                    		}
	                    	}else{
	                    		errorActualiza(sql,iCluesID, "No fue posible insertar el registro AD_OrgInfo,");  //Guarda un mensaje de error en el registro actual
	                    		noErroresInfo++;          
	                    		continue;
	                    	} 
	                    }else{	                    
	                    	errorActualiza(sql,iCluesID, "No fue posible importar el registro AD_Org,");  //Guarda un mensaje de error en el registro actual                		      
	                    	noErrores++;
	                    	continue;
	                    }
	                    DB.commit(true, this.get_TrxName()); 
					}catch (Exception e){
						DB.rollback(false, this.get_TrxName());
						e.printStackTrace();
						log.warning("Importando Organizacion:" + e);
						errorActualiza(sql, iCluesID, "No fue posible insertar el registro");  //Guarda un mensaje de error en el registro actual
					}	
				}				
			}catch (Exception e){
				
				DB.rollback(false, this.get_TrxName());
				Trx m_trx = Trx.get(this.get_TrxName(), false);
				if(m_trx != null){
					m_trx.close();
					m_trx = null;
				}
				e.printStackTrace();
				log.warning("Importando Organizacion:" + e);
				
			}finally{
				if(rs != null)
					rs.close();
				if(stmt != null)
					stmt.close();
				
				rs=null;
				stmt=null;
				
				if(rsu != null)
					rsu.close();
				if(stmtu != null)
					stmtu.close();
				
				rsu=null;
				stmtu=null;
				
				Trx m_trx = Trx.get(this.get_TrxName(), false);
				if(m_trx != null){
					m_trx.close();
					m_trx = null;
				}
			}	
			//errores que aparecen en una ventana indicando el numero de errores o de inserciones/actualizaciones
			addLog (0, null, new BigDecimal (noErrores), "@Errors@");
			addLog (0, null, new BigDecimal (noErroresInfo), "@Errors@");
			addLog (0, null, new BigDecimal (noInsert), "@AD_Org@: @Inserted@");
			addLog (0, null, new BigDecimal (noUpdate), "@AD_Org@: @Updated@");
			addLog (0, null, new BigDecimal (noInsertInfo), "@AD_OrgInfo@: @Inserted@");
			addLog (0, null, new BigDecimal (noUpdateInfo), "@AD_OrgInfo@: @Updated@");       
			
			Trx m_trx = Trx.get(this.get_TrxName(), false);
			if(m_trx != null){
				m_trx.close();
				m_trx = null;
			}
			return "";
	  }
	
	 	/**
	 	 * Metodo que inserta una nueva Ubicaci#n a partir de un nuevo Centro de Salud (Lizeth de la Garza)
	 	 * @param iCluesID
	 	 * @param ctx
	 	 * @param trxName
	 	 * @param log
	 	 * @return
	 	 */
	    public int insertLocation(int iCluesID, CLogger log){
	    	
	    	PreparedStatement pstmt1 = null;
	    	Statement pstmt2 = null;
	    	ResultSet rsl = null;
	    	StringBuffer sql = null;
	    	int no=0;
	    	ResultSet rst = null;
	    	int C_Country_ID = 0;
	    	int C_Region_ID = 0;
	    
	    	try{	    		
	    		MLocation location = null;
	    		StringBuffer pais = null;
	    		//Selecciona el ID para el pa#s M#xico
	    		pais = new StringBuffer("select p.c_country_id from c_country p where p.countrycode=trim('MX')");
	    		pstmt2 =DB.createStatement();
	    		rst = pstmt2.executeQuery(pais.toString());
	    		try{
	    			while (rst.next()){	  
	    				C_Country_ID =  rst.getInt(1);
	    			}
	    		}catch(SQLException e){
		    		errorActualiza(pais, iCluesID, "No se encontro C_Country_ID");
	    		}
	    		
	    		//Selecciona los datos del registro actual
	    		sql = new StringBuffer("select a.i_exme_importclues_id, a.c_region_id, a.address1, ")
	    		      //.append("a.exme_localidad_value, ")
	    				.append("a.postal,a.exme_towncouncil_id, a.regionname  ")
	    				.append("from i_exme_importclues a ")
	    				.append("where a.i_exme_importclues_id = ? and a.i_isimported<>'Y' ");
	    				    		
	    		pstmt1 = DB.prepareStatement(sql.toString(), null);
	    		pstmt1.setInt(1, iCluesID);
	    		rsl = pstmt1.executeQuery();
	    		
	    		while (rsl.next()){	    			
	    			C_Region_ID = rsl.getInt(2);
	    			location = new MLocation(MCountry.get(this.getCtx(), C_Country_ID), MRegion.get(this.getCtx(), C_Region_ID));
	    			
	    			//Se guardan los datos para la informacion de ubicaci#n del centro de salud
	    			location.setAddress1(rsl.getString(3));
	    			//location.setAddress2(rsl.getString());
	    			//location.setEXME_Localidad_ID(rsl.getInt());
	    			location.setPostal(rsl.getString(4));
	    			location.setEXME_TownCouncil_ID(rsl.getInt(5));
	    			location.setRegionName(rsl.getString(6));
	 				    			
	    			location.save(this.get_TrxName());
	    			
	    			//Se actualiza la informacion en la temporal
	    			sql = new StringBuffer ("UPDATE I_EXME_ImportClues clu ") 
	    					.append("SET C_Location_ID=").append(location.getC_Location_ID())
	    					.append(" WHERE C_Location_ID IS NULL") 
	    					.append(" AND I_IsImported<>'Y' and i_exme_importclues_id=").append(iCluesID);	    			
	    				    			
	    			no = DB.executeUpdate(sql.toString());
	    			log.fine("Set C_Location_ID =" + no);
	    		}
	    		pstmt1.close();
	    		rsl.close();
	    		pstmt2.close();
	    		rst.close();
	    		
	    		//Regresa el C_Location_ID para ser guardado en AD_OrgInfo
	    		if(location!=null){
	    			return location.getC_Location_ID();
	    		}else{
	    			return 0;
	    		}
	    	}catch(SQLException e){
	    			    		
	    		errorActualiza(sql, iCluesID, "C_Location_ID Inv#lido=" + no);	    		   		
	    		
	    		try{
	    			if(rsl != null)
	    				rsl.close();
	    			if(pstmt1 != null)
	    				pstmt1.close();
	    			if(rst != null)
	    				rst.close();
	    			if(pstmt2 != null)
	    				pstmt2.close();
	    		}catch (SQLException ex){}
	    		log.log(Level.SEVERE, "doIt", e);
	    		rsl=null;
	    		pstmt1=null;
	    		rst=null;
	    		pstmt2=null;
	    		
	    		return 0;
	    	}
	    	finally{
	    		try {
	    			pstmt1.close();
	    			rsl.close();
	    			pstmt2.close();
		    		rst.close();
	    		}catch (SQLException e) {
	    			// TODO Auto-generated catch block
	    			e.printStackTrace();
	    		}
	    		rsl=null;
	    		pstmt1=null;
	    		rst=null;
	    		pstmt2=null;
	    	}
	    }
	    /**
	     * Metodo que actualiza la Direcci#n(Ubicaci#n) para el Centro de Salud (Lizeth de la Garza)
	     * @param iCluesID
	     * @param c_Location_ID
	     * @param ctx
	     * @param trxName
	     * @param log
	     * @return 0 si no se actualizo, o el C_Location_ID si se realizo la actualizaci#n.
	     */
	    
	    public int updateLocation(int iCluesID, int c_Location_ID, CLogger log){
	    	
	    	PreparedStatement pstmt1 = null;
	    	Statement pstmt2 = null;
	    	ResultSet rsl = null;
	    	StringBuffer sql = null;
	    	int no=0;
	    	ResultSet rst = null;
	    	StringBuffer pais = null;
	    	int C_Country_ID = 0;
	    	int C_Region_ID = 0;
	    		    	
	    	try{
	    		MLocation location = null;
	    		//Selecciona el ID para el pa#s M#xico
	    		pais = new StringBuffer("select p.c_country_id from c_country p where p.countrycode=trim('MX')");
	    		pstmt2 =DB.createStatement();
	    		rst = pstmt2.executeQuery(pais.toString());
	    		try{
	    			while (rst.next()){	  
	    				C_Country_ID =  rst.getInt(1);
	    			}
	    		}catch(SQLException e){
		    		errorActualiza(pais, iCluesID, "No se encontro C_Country_ID");
	    		}
	    		//Selecciona la informacion del registro actual
	    		sql = new StringBuffer("select a.i_exme_importclues_id, a.c_region_id, a.address1, ")
	    			  //.append("a.exme_localidad_value,")
	    				.append(" a.postal,a.exme_towncouncil_id, a.regionname  ")
	    				.append("from i_exme_importclues a ")
	    				.append("where a.i_exme_importclues_id = ? and a.i_isimported<>'Y' ");
	    					    		
	    		pstmt1 = DB.prepareStatement(sql.toString(), null);
	    		pstmt1.setInt(1, iCluesID);
	    		rsl = pstmt1.executeQuery();
	    		while (rsl.next()){
	    			
	    			C_Region_ID = rsl.getInt(2);
	    			location = MLocation.get(this.getCtx(), c_Location_ID, this.get_TrxName()); 
	    			
	    			//Se guarda la informacion de la ubicaci#n del centro de salud
	    			location.setCountry(MCountry.get(this.getCtx(), C_Country_ID));
	    			location.setRegion(MRegion.get(this.getCtx(), C_Region_ID));
	    			location.setAddress1(rsl.getString(3));
	    			//location.setAddress2(rsl.getString());
	    			//location.setEXME_Localidad_ID(rsl.getInt());
	    			location.setPostal(rsl.getString(4));
	    			location.setEXME_TownCouncil_ID(rsl.getInt(5));
	    			location.setRegionName(rsl.getString(6));
	    			
	    			location.save(this.get_TrxName());
	    			
	    			//Actualiza la tabla temporal
	    			sql = new StringBuffer ("UPDATE I_EXME_ImportClues clu ")
	    					.append("SET C_Location_ID=").append(location.getC_Location_ID())
	    					.append(" WHERE  I_IsImported<>'Y' and i_exme_importclues_id=").append(iCluesID);
	    			
	    			no = DB.executeUpdate(sql.toString());
	    			log.fine("Set C_Location_ID =" + no);
	    			
	    		}
	    		//Si no hay datos a actualizar
	    		if(location==null){
	    			sql = new StringBuffer ("UPDATE I_EXME_ImportClues clu ") 
	    					.append("SET C_Location_ID=").append(c_Location_ID)
	    					.append(" WHERE  I_IsImported<>'Y' and i_exme_importclues_id=").append(iCluesID);
	    			
	    			no = DB.executeUpdate(sql.toString());
	    			log.fine("Set C_Location_ID =" + no);
	    		}
	    		pstmt1.close();
	    		rsl.close();
	    		pstmt2.close();
	    		rst.close();
	    		
	    		//Envia el C_Location_ID para guardarse en AD_OrgInfo
	    		if(location!=null){
	    			return location.getC_Location_ID();
	    		}else{
	    			return 0;
	    		}
	    	}catch(SQLException e){
	    		
	    		errorActualiza(sql, iCluesID, "C_Location_ID Inv#lido=" + no);
	    			    		
	    		try{
	    			if(rsl != null)
	    				rsl.close();
	    			if(pstmt1 != null)
	    				pstmt1.close();
	    			if(rst != null)
	    				rst.close();
	    			if(pstmt2 != null)
	    				pstmt2.close();
	    		}catch (SQLException ex){
	    			//TODO Auto-generated catch block
	    			e.printStackTrace();
	    		}
	    		log.log(Level.SEVERE, "doIt", e);
	    		rsl=null;
	    		pstmt1=null;
	    		return 0;
	    		
	    	}
	    	finally{
	    		try {
	    			pstmt1.close();
	    			rsl.close();
	    			pstmt2.close();
		    		rst.close();
	    		}catch (SQLException e) {
	    			// TODO Auto-generated catch block
	    			e.printStackTrace();
	    		}
	    		rsl=null;
	    		pstmt1=null;
	    		rst=null;
	    		pstmt2=null;
	    	}
	    }
	    /**
	     * Metodo que establece los errores dentro del registro de la I
	     * @param sql
	     * @param ID
	     * @param error
	     * @param trxName
	     */
	    private void errorActualiza(StringBuffer sql, int ID, String error){
	    	try{
	    		DB.rollback(true, this.get_TrxName());     			        
	    		
	    		log.warning(error);
	    		//Actualiza la tabla temporal marcando error en importacion y guardando los mensajes de error
	    		sql = new StringBuffer ("UPDATE I_EXME_ImportClues  ")	    				
	    				.append(" SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg || '" + error)
	    				.append("'  WHERE I_EXME_ImportClues_id=" + String.valueOf(ID));
	    		
	    		DB.executeUpdate(sql.toString());
	    	}catch(Exception ex)	{ 
	    		ex.printStackTrace();
	    	}
	    }
}

