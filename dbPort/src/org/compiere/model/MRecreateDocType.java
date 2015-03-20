/*
 * Derechos Reservados (c) 
 * a partir 2005 
 * Expert Sistemas Computacionales, S. A. de C. V.
 * Sistema MedSys
 */

package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;

/**
 * clase MRecreaci&oacute;n de clones de DocTypep
 * <p>
 * 
 * <b>Fecha:</b> 08/Mayo/2008
 * <p>
 * <b>Modificado: </b> $Author: Samuel C&aacute;rdenas $
 * <p>
 * <b>En :</b> $Date: 2006/11/01 20:25:17 $
 * <p>
 * 
 * @author Samuel C&aacute;rdenas
 * @version $Revision: 1.0 $
 */

public class MRecreateDocType

{
	/**	Static Logger				*/
	private static CLogger s_log = CLogger.getCLogger(MRecreateDocType.class);

	public static int getTotalDocTypeRows(Properties ctx, int AD_OrgID, String trxName)
			throws SQLException {

		StringBuilder sql = new StringBuilder("  SELECT count(*) totalRows FROM c_doctype  ");
						sql.append("WHERE   DocBaseType not in (' ','  ','   ') and ad_org_id = ");
						sql.append( AD_OrgID  );

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int regreso = 0;
		try {

			pstmt = null;
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			rs = pstmt.executeQuery(sql.toString());
			while (rs.next()) {
				regreso = rs.getInt(1);
			}

		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e.getMessage());
		} finally {
			DB.close(rs, pstmt);
		}

		return regreso;
	}

	@SuppressWarnings("rawtypes")
	public static boolean updateOrgID(Properties ctx, ArrayList lstOfSavedIDElements, int AD_OrgID,
			String trxName) throws SQLException {

		StringBuilder sqlUpdate = new StringBuilder(100);
		sqlUpdate.append(" UPDATE C_DOCTYPE SET AD_ORG_ID=  ");
		sqlUpdate.append(AD_OrgID);
		sqlUpdate.append(" WHERE C_DOCTYPE_ID in (");

		if(lstOfSavedIDElements.size()>0)
		for (int a = 0; a < lstOfSavedIDElements.size(); a++)
			if(a==lstOfSavedIDElements.size()-1)
				sqlUpdate.append(lstOfSavedIDElements.get(a) + ")");
			else
				sqlUpdate.append(lstOfSavedIDElements.get(a) + ",");

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int regreso = 0;
		try {

			pstmt = null;
			pstmt = DB.prepareStatement(sqlUpdate.toString(), trxName);
			regreso = pstmt.executeUpdate(sqlUpdate.toString());

			if (regreso == lstOfSavedIDElements.size())
				return true;
			else
				return false;

		} catch (Exception e) {
			
			s_log.log(Level.SEVERE, sqlUpdate.toString(), e.getMessage());
			
		} finally {
			DB.close(rs, pstmt);
		}

		return false;
	}

	public static boolean isSon(int orgPadreID,String nexo1) {

		StringBuilder sql = new StringBuilder(100);
		sql.append(" SELECT count(*) FROM  C_DOCTYPE WHERE  name like '%"+nexo1+"%' ");
		sql.append(" AND AD_ORG_ID = "+orgPadreID);


		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int repeated=0;
		
		try {

			pstmt = null;
			pstmt = DB.prepareStatement(sql.toString(), null);
			rs = pstmt.executeQuery(sql.toString());
			
			if (rs.next()) {
				repeated =rs.getInt(1);
				if (repeated>0)
					return true;
			}
			else 
				return false;
			

		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e.getMessage());
			
		} finally {
			DB.close(rs, pstmt);
		}		
		
		return false;
		
	}
	
	public static boolean isFather (int org) {

		StringBuilder sql = new StringBuilder(100);
		sql.append(" SELECT count(*) FROM ad_org WHERE ad_org_id="+org+" and ad_org_id =some( ") ;
		sql.append(" SELECT AD_ORG_ID FROM AD_ORGINFO where PARENT_ORG_ID is null)");


		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int repeated=0;
		
		try {

			pstmt = null;
			pstmt = DB.prepareStatement(sql.toString(), null);
			rs = pstmt.executeQuery(sql.toString());
			
			if (rs.next()) {
				repeated =rs.getInt(1);
				if (repeated>0)
					return true;
			}
			else 
				return false;
			

		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e.getMessage());
		} finally {
			DB.close(rs, pstmt);
		}		
		
		return false;
		
	}

	public static boolean isGenerated(int orgHijoID,String orgPadre,String nexo1) {

		StringBuilder sql = new StringBuilder(100);
		sql.append(" SELECT count(*) FROM  C_DOCTYPE WHERE  name like '%"+nexo1+"%' ");
		sql.append(" AND AD_ORG_ID = "+orgHijoID);
		sql.append(" and name like '%"+orgPadre+"%' ");


		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int repeated=0;
		
		try {

			pstmt = null;
			pstmt = DB.prepareStatement(sql.toString(), null);
			rs = pstmt.executeQuery(sql.toString());
			
			if (rs.next()) {
				repeated =rs.getInt(1);
				if (repeated>0)
					return true;
			}
			else 
				return false;
			

		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e.getMessage());
		} finally {
			DB.close(rs, pstmt);
		}		
		
		return false;
		
	}
	
	/**
	 * method created in the case that exist a Sequence Number 
	 * recently created by the previouly saved records.
	 */
	public static int getIfExistNewSeqNo(String name, int OrgPadreID,int OrgHijoID,String trxName,String nexo1, String nexo2){
		
		StringBuilder vToSearch = new StringBuilder(name+nexo1+OrgPadreID+nexo2+OrgHijoID);
		int searchedValue =0;
		
		StringBuilder sql = new StringBuilder(100);
		sql.append(" SELECT AD_SEQUENCE_id FROM AD_SEQUENCE WHERE NAME = ?");
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setString(1, vToSearch.toString());
			
			rs = pstmt.executeQuery();

			if (rs.next()){
				searchedValue = rs.getInt(1);
			} 
			else 
				return 0;
			
		} catch (SQLException e) {
			e.printStackTrace();
            try{
                if(rs != null)
                    rs.close();
                if(pstmt != null)
                    pstmt.close();
            }catch (Exception f){}
            pstmt = null;
            rs=null;
        }finally {
            try {
                if (pstmt != null)
                    pstmt.close ();
                if(rs!=null)
                    rs.close();
            } catch (Exception e) {}
            pstmt=null;
            rs=null;
            sql=null;
            
		}

		
		
		return searchedValue;
		
	}
	
	public static List<MDocType> getAllDocType(Properties ctx, int orgPadreID, String trxName) {

		StringBuilder sql = new StringBuilder(100);
		sql.append(" SELECT C_DocType_ID FROM C_DocType WHERE AD_Client_ID IN(0,?) "
		      + " AND  AD_Org_ID IN (?)  AND IsActive = 'Y'  "
		      + " and DocBaseType not in (' ','  ','   ') and ad_org_id "
		      + " =some (SELECT ad_org_id FROM ad_orginfo  WHERE ad_org_id= ? "
		      + " and PARENT_ORG_ID is null  ) order by C_DocType_ID ");
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<MDocType> docType = new ArrayList<MDocType> ();
		int cont=0;
		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setInt(1, Env.getAD_Client_ID(ctx));
			pstmt.setInt(2, orgPadreID);
			pstmt.setInt(3, orgPadreID);
			
			rs = pstmt.executeQuery();

			while (rs.next()){
				docType.add(cont,new MDocType(ctx, rs.getInt(1), trxName));
				cont++;
			} 
			
		} catch (SQLException e) {
			e.printStackTrace();
            try{
                if(rs != null)
                    rs.close();
                if(pstmt != null)
                    pstmt.close();
            }catch (Exception f){}
            pstmt = null;
            rs=null;
        }finally {
            try {
                if (pstmt != null)
                    pstmt.close ();
                if(rs!=null)
                    rs.close();
            } catch (Exception e) {}
            pstmt=null;
            rs=null;
            cont=0;
		}

		return docType;
	}
	
	/**
	 * This method return a map whit an existent collection of the row created
	 * for an especific organizaction, this means that if the process has been run more
	 * than one time, then it will not create more documentes of the created yet, but
	 * when finds any register that is new then the process proceed to create thi new one.
	 * 
	 */
	public static HashMap<String, String> getExistentDocument(Properties ctx, int orgPadreID, int orgHijo,String nexo1,String nexo2) {

		StringBuilder condition = new StringBuilder(nexo1+orgPadreID+nexo2+orgHijo);
		
		StringBuilder sql = new StringBuilder(100);
		sql.append(" SELECT NAME FROM C_DOCTYPE  WHERE NAME LIKE '%" +
				condition+"%' ");
		
		
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		HashMap<String, String> docType = new HashMap<String, String>();
		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			
			rs = pstmt.executeQuery();

			while (rs.next()){
				docType.put(rs.getString(1),rs.getString(1));
			} 
			
		} catch (SQLException e) {
			e.printStackTrace();
            try{
                if(rs != null)
                    rs.close();
                if(pstmt != null)
                    pstmt.close();
            }catch (Exception f){}
            pstmt = null;
            rs=null;
        }finally {
            try {
                if (pstmt != null)
                    pstmt.close ();
                if(rs!=null)
                    rs.close();
            } catch (Exception e) {}
            pstmt=null;
            rs=null;
		}

		return docType;
	}

}
