package org.compiere.process;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.model.MEXMEAccessCatalog;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.Trx;

/**
 *  <p>
 * 
 * Creado: 2008/05/10<p>
 * Modificado: $Author: scardenas $<p>
 * Fecha: $Date: 2008/05/10 05:05:17 $<p>
 *
 * @author samuel cardenas
 * @version $Revision: 1.0 $
 * 
 * @functionality	charge interface
 */

public class ImportAccessCatalog extends SvrProcess

{
	
	private int				m_AD_Client_ID = 0;
	private boolean			m_deleteOldImported = false;
	private int				m_AD_Org_ID = 0;
	private Timestamp		m_DateValue = null;
	private StringBuffer 	sqlErrorLog = new StringBuffer(Constantes.INIT_CAPACITY_ARRAY*2);
	
	/**
	 * METHOD PREPARE : READ PARAMS FROM SWING OR WEB INTERFACE
	 */
	protected void prepare(){
		ProcessInfoParameter[] para = getParameter();
	
		for (int i = 0; i < para.length; i++)	
		{
			String name = para[i].getParameterName();
			
			if (name.equals("AD_Client_ID"))
				m_AD_Client_ID = ((BigDecimal)para[i].getParameter()).intValue();
			else if (name.equals("DeleteOldImported"))
				m_deleteOldImported = "Y".equals(para[i].getParameter());
			else if (name.equals("AD_Org_ID"))
				m_AD_Org_ID = ((BigDecimal)para[i].getParameter()).intValue();
			else{
				log.log(Level.SEVERE, "ImportBPartner.prepare - Unknown Parameter: " + name);
				sqlErrorLog.append("ImportBPartner.prepare - Unknown Parameter: " + name);
			}
		}
		
		if (m_DateValue == null)	
			m_DateValue = DB.getTimestampForOrg(getCtx());
	}	
	
	
	/**
	 * METHOD DOIT : FULL PROCESS ACTION
	 */
	protected String doIt() throws java.lang.Exception
	{
		/**
		 * @1 INIT VARIABLES
		 */
		StringBuffer sql = null;
		Trx m_Trx = Trx.get("ImportaAccessCatalog",true);
		String trxName  = m_Trx.getTrxName();
		//ResultSet rs = null;
		int no = 0;
		int i =0;
		//PreparedStatement pstm = null;
		//String clientCheck = " AND AD_Client_ID=" + m_AD_Client_ID;
		ArrayList<MEXMEAccessCatalog> mac = new ArrayList<MEXMEAccessCatalog>(Constantes.INIT_CAPACITY_ARRAY);
		Properties ctx= new Properties();
		
		/**
		 * @2 DELETE TEMP DATA TABLE
		 */
		if (m_deleteOldImported)
		{
			sql = new StringBuffer ("DELETE I_EXME_ACCESSCATALOG WHERE I_ISIMPORTED='Y'");
			no = DB.executeUpdate(sql.toString(),trxName);
			log.fine(no+ " OLD DATA FROM I_EXME_ACCESSCATALOG DELETED ");	
		}
		
		/**
		 * @3 GET DATA FROM TEMPDATA
		 */
		ResultSet rsTemp= null;
		rsTemp = getTempData(m_Trx);
		
		/**
		 * @4 PROCESSING RESULTSET MAKING MEXMEACCESSCATALOG OBJECT'S
		 * MEXMEAccessCatalog 
		 */
		boolean macDone =false;
		int noRows =getTotalTempRow(m_Trx);
		
		if (noRows>0)
		{
					
			ctx.setProperty("#AD_Client_ID",String.valueOf(m_AD_Client_ID));
			ctx.setProperty("#AD_Org_ID",String.valueOf(m_AD_Org_ID));
					
				try{
					for(i=0;i<noRows;i++){
						mac.add(i,new MEXMEAccessCatalog(ctx,0,trxName));
						}
					}catch(Exception eSQL){
						log.severe("Error processin query result : "+eSQL);
						mac = null;
						macDone=false;
						rsTemp=null;
						noRows=0;
						}
					finally{
						ctx = null;
						if (i>0)
							macDone=true;
						i=0;
						}
					}//END try FOR MEXME OBJECTS
		
		
		/*
		 * @5 GET DATA FROM I_ACCESSCATALOG SO FILL THE REAL DATA
		 */ 
			//boolean isMacFull=false;
			i=0;
			while (macDone && rsTemp.next() && i<noRows){
				//for to explore the full rsTemp data objeto of @3
				//for (i=0;i<mac.size();i++){
					//if to check not null or zero values
					if (rsTemp.getString("Value") != null && 
						rsTemp.getString("Name") != null &&
						rsTemp.getString("Description") != null && 
						rsTemp.getInt("OriginalAccess") >= 0 &&
						rsTemp.getInt("MultiAccess") >= 0 &&
						rsTemp.getInt("UniqueAccess") >= 0 ){
							mac.get(i).setValue(rsTemp.getString("Value"));
							mac.get(i).setName(rsTemp.getString("Name"));
							mac.get(i).setDescription(rsTemp.getString("Description"));
							mac.get(i).setOriginalAccess(rsTemp.getInt("OriginalAccess"));
							mac.get(i).setMultiAccess(rsTemp.getInt("MultiAccess"));
							mac.get(i).setUniqueAccess(rsTemp.getInt("UniqueAccess"));
							
						}
					else{
						//isMacFull=false;
						break;
					}i++;
				//}	
				}
			
		
			/*
			 * @6 PROCESS TO COMMIT ALL TRANSACTIONS 
			 */
			int committed=0;
			for (i=0;i<mac.size();i++){
				if(mac.get(i).save(trxName))
					committed++;
				else
					if (committed>1){
						committed--;
						break;
					}
					
			}
			if (mac.size()==committed){
				m_Trx.commit();
				setProcessed();
				log.fine("process succesfully done, now closing objects");
			
			}else
				try{
					log.fine("process unsuccessfully done, now closing objects");
					m_Trx.rollback();
				}catch(Exception e){}
				finally{
					m_Trx.close();
					committed=0;
					trxName=null;
					/*for (i=0;i<mac.size();i++){
						MEXMEAccessCatalog mex = (MEXMEAccessCatalog)mac.get(i);
						mex=null;
					}*/
					i=0;
					//isMacFull=false;
					
				}
		
		
		
		return "";
		
	}	//	doIt	
	
	private void setProcessed(){
		
		StringBuffer sql = new StringBuffer (Constantes.INIT_CAPACITY_ARRAY);
		sql.append(" update  i_Exme_AccessCatalog set I_ISIMPORTED='Y',PROCESSED='Y'");
		sql.append(" WHERE ISPROCESSED='N' and I_ISIMPORTED ='N' ");
		PreparedStatement pstm =null;
		Trx m_Trx = Trx.get("ProcessedAccessCatalog",true);
//		String trxName = m_Trx.getTrxName();
		int no =0;
		
		try{
			pstm = DB.prepareStatement(sql.toString(),  m_Trx.getTrxName());
			no = pstm.executeUpdate();
			if(no>0){
				Trx.commit(m_Trx);
			}
			}catch(Exception eSQL){
				log.log(Level.SEVERE,"Error processin query result: "+sql,eSQL);
				Trx.rollback(m_Trx);
			}finally{
				Trx.close(m_Trx);
				DB.close(pstm);
				pstm = null;
				sql = null;
				m_Trx=null;
			}
		
	}
	
	
	public ResultSet getTempData(Trx m_Trx){
		/**
		 * @3 GET ALL DATA FROM TEMP TABLE
		 */
		StringBuffer sql = new StringBuffer (Constantes.INIT_CAPACITY_ARRAY);
		sql.append(" SELECT * FROM I_EXME_ACCESSCATALOG ");
		sql.append(" WHERE PROCESSED='N' ");
		PreparedStatement pstm =null;
		ResultSet rsTemp = null;
		
		try{
			pstm = DB.prepareStatement(sql.toString(), m_Trx.getTrxName());
			rsTemp = pstm.executeQuery();
			
		}catch(SQLException eSQL){
			log.severe("Error on : "+eSQL);
			m_Trx.rollback();
			rsTemp = null;
			m_Trx=null;
			m_Trx=null;
		}finally {
			sql = null;
			log.fine("Succesfully nullified query data objects");
		}
		return rsTemp;
	}
	
	/**
	 * Get the number of rows in the temp table
	 * @param m_Trx
	 * @return
	 */
	public int getTotalTempRow(Trx m_Trx){
		/**
		 * @3 GET ALL DATA FROM TEMP TABLE
		 */ 
		StringBuffer sql = new StringBuffer (Constantes.INIT_CAPACITY_ARRAY);
		sql.append(" SELECT count(I_EXME_ACCESSCATALOG_id) as total FROM I_EXME_ACCESSCATALOG ");
		sql.append(" WHERE PROCESSED='N' ");
		PreparedStatement pstm =null;
		ResultSet rsTemp = null;
		int totalTempRow=0;
		
		try{
			pstm = DB.prepareStatement(sql.toString(), m_Trx.getTrxName());
			rsTemp = pstm.executeQuery();
			rsTemp.next();
			totalTempRow= rsTemp.getInt("total");
			
		}catch(SQLException eSQL){
			log.severe("Error on : "+eSQL);
			rsTemp = null;
		}finally {
			sql = null;
			pstm=null;
			rsTemp=null;
			log.fine("Succesfully nullified query data objects");
		}
		return totalTempRow;
	}
	
}


