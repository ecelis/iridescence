package org.compiere.util;

import java.awt.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 *  @author     Sam Cardenas
 *  @version    $Id: EnviromentTools.java,v 1.10 2007/01/20 01:35:36 scardenas Exp $
 */
public final  class DBTools {	
	/**Clogger	*/
	private static CLogger			log = CLogger.getCLogger (DBTools.class);
	/*
	 * database process killer
	 */
	@SuppressWarnings("unchecked")
	public static void processKiller() throws Exception{
		log.info("DBTools.class process killer process with no feed options");
		List list = new List();
		
		String sqllvb = null;
		PreparedStatement pstmtlvb = null;
		ResultSet rslbv = null;
		
		sqllvb = 	"select 'alter system kill session '||''''||sid||','||serial#||''''||' ' as killer_query  "+
						" from v$session  "+
						" where STATUS='INACTIVE' AND USERNAME='EXPERT' and SERVICE_NAME not in ('SYS$USERS','SYS$BACKGROUND')  "+
						" and logon_time> (  "+
						"  SELECT min(logon_time)+0.010416 as  logonplus FROM v$session   "+
						"  where STATUS='INACTIVE' AND USERNAME='EXPERT' and SERVICE_NAME not in ('SYS$USERS','SYS$BACKGROUND')  "+
						"                             )  "+
						" and LOGON_TIME<(SELECT sysdate-(0.041664*3.5) as  logonless  FROM dual )  "+
						" order by (sysdate-logon_time) desc ";
		   
		pstmtlvb = null;
		rslbv = null;
		
		try {
			pstmtlvb = DB.prepareStatement(sqllvb, "Executing killer querys for inactive sessions");

			rslbv = pstmtlvb.executeQuery();
			
			while (rslbv.next()) {
				list.add(rslbv.getString("killer_query"));
			}
			
			for (int i=0;i<list.getItemCount();i++){
				sqllvb = list.getItem(i);
				pstmtlvb = DB.prepareStatement(sqllvb, "aplicando killer query , numero "+ i);
				rslbv = pstmtlvb.executeQuery();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			log.info(" fallo el proceso de liberacion de queryes 2. : "+e);
			log.info("SQL execution query : "+ sqllvb);
			throw new Exception(e.getMessage());
		} finally {
			rslbv = null;
			pstmtlvb = null;
			sqllvb = null;;
		}
		
	}
}
