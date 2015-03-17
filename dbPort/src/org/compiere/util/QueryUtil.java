package org.compiere.util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;

import org.apache.commons.lang.StringUtils;

public class QueryUtil {

	public static StringBuilder queryBuilt = null;

	private static CLogger logger = CLogger.getCLogger(QueryUtil.class);
	
	private final static String SELECT_FV  	= "                                   (SELECT @FV \n";
	private final static String WHERE_FV   	= "                                   WHERE  @FV >= \n";
	private final static String SELECT_FV2 	= "                                          (SELECT @FV \n";
	private final static String FROM_TN 	 	= "                                                 FROM     @TN \n";
	private final static String SELECT_FV3 	= "                                                 (SELECT  @FV \n";
	private final static String FROM	 	 	= "                                          FROM \n";
	private final static String SPACE	 	 	= "                                   ) \n";
	private final static String FROM2	 	 	= "                            FROM \n";
	private final static String SPACE2	 	= "                     ) \n";
	private final static String SPACE3	 	= "                                          ) \n";
	private final static String ROWNUM	 	= "                                          WHERE  rownum=1 \n";
	private final static String ORDER_BY	 	= "                                                 ORDER BY @FV \n";
	private final static String SPACE4	 	= "                                                 ) \n";
	private final static String NL	 		= " \n";

	/**
	 * 
	 * @param fieldValue
	 * @param tableName
	 * @param initValue
	 * @param endValue
	 * @param tablePrefix
	 * @return
	 * @deprecated se deprecia ya que no se manda llamar ninguna vez. Jesus Cantu
	 * 			   el 13 de Julio del 2012. No se corrige lo de rownum para postgresql. 
	 */
	public static String getQueryForValueSearch(String fieldValue, String tableName, 
			String initValue, String endValue, String tablePrefix) {
		
		String retVal = null;
		ResultSet rs = null;
		String tempQry = null;
		ArrayList<String> qry = new ArrayList<String>();
		ArrayList<PreparedStatement> lstPrepSt = new ArrayList<PreparedStatement>();
		
		if (queryBuilt == null) {

			/*
			 * i>= to N<=
			 */
			StringBuilder qA = new StringBuilder();
			qA.append("SELECT \n");
			qA.append("       CASE \n");
			qA.append("                     --ini: i>=  fin: n<= \n");
			qA.append("              WHEN   \n");
			qA.append("                     (SELECT count(@FV) \n");
			qA.append("                     FROM   @TN \n");
			qA.append("                     WHERE  @FV >= \n");
			qA.append("                            (SELECT @FV \n");
			qA.append(FROM2);
			qA.append("                                   (SELECT  @FV \n");
			qA.append("                                   FROM     @TN \n");
			qA.append("                                   ORDER BY @FV \n");
			qA.append(SPACE);
			qA.append("                            WHERE  rownum=1 \n");
			qA.append("                            ) and  @FV>= @VAL1 \n");
			qA.append("                     ) >0\n");
			qA.append("                 AND   \n");
			qA.append("                     (SELECT count(@FV) \n");
			qA.append("                     FROM   @TN \n");
			qA.append("                     WHERE  @FV >= \n");
			qA.append("                            (SELECT @FV \n");
			qA.append(FROM2);
			qA.append("                                   (SELECT  @FV \n");
			qA.append("                                   FROM     @TN \n");
			qA.append("                                   ORDER BY @FV \n");
			qA.append(SPACE);
			qA.append("                            WHERE  rownum=1 \n");
			qA.append("                            ) and @FV <=@VAL2 \n");
			qA.append("                     ) >0 \n");
			qA.append("              THEN 1 \n");
			qA.append("              ELSE 0 \n");
			qA.append("       END \n");
			qA.append("FROM   dual");

			StringBuilder retA = new StringBuilder();
			retA.append (" '"+ initValue+ "' AND ");
			retA.append (tablePrefix + "." + fieldValue + " <= '" + endValue + "'");

			/*
			 * i2>= to N2<=
			 */
			StringBuilder qB = new StringBuilder();
			qB.append("SELECT \n");
			qB.append("       CASE \n");
			qB.append("              WHEN \n");
			qB.append("                     (SELECT COUNT (*) \n");
			qB.append(FROM2);
			qB.append(SELECT_FV);
			qB.append("                                   FROM   @TN minus \n");
			qB.append("                                   SELECT @FV \n");
			qB.append("                                   FROM   @TN \n");
			qB.append(WHERE_FV);
			qB.append(SELECT_FV2);
			qB.append(FROM);
			qB.append(SELECT_FV3);
			qB.append(FROM_TN);
			qB.append(ORDER_BY);
			qB.append(SPACE4);
			qB.append(ROWNUM);
			qB.append(SPACE3);
			qB.append(SPACE);
			qB.append("                            WHERE  @FV >= @VAL1 \n");
			qB.append(SPACE2);
			qB.append("                     >0 \n");
			qB.append("                 AND \n");
			qB.append("                     (SELECT COUNT(*) \n");
			qB.append(FROM2);
			qB.append(SELECT_FV);
			qB.append("                                   FROM   @TN minus \n");
			qB.append("                                   SELECT @FV \n");
			qB.append("                                   FROM   @TN \n");
			qB.append(WHERE_FV);
			qB.append(SELECT_FV2);
			qB.append(FROM);
			qB.append(SELECT_FV3);
			qB.append(FROM_TN);
			qB.append(ORDER_BY);
			qB.append(SPACE4);
			qB.append(ROWNUM);
			qB.append(SPACE3);
			qB.append(SPACE);
			qB.append("                            WHERE  @FV<=@VAL2 \n");
			qB.append(SPACE2);
			qB.append("                     >0 \n");
			qB.append("              THEN 1 \n");
			qB.append("              ELSE 0 \n");
			qB.append("       END \n");
			qB.append("FROM   dual");

			StringBuilder retB = new StringBuilder();
			retB.append("  '" + initValue+ "' AND ");
			retB.append(tablePrefix + "." + fieldValue + " <= '" + endValue + "' ");

			/*
			 * i>= to N2<=
			 */
			StringBuilder qC = new StringBuilder();
			qC.append("SELECT \n");
			qC.append("       CASE \n");
			qC.append("              WHEN \n");
			qC.append("                     (SELECT COUNT(*) \n");
			qC.append(FROM2);
			qC.append(SELECT_FV);
			qC.append("                                   FROM   m_product \n");
			qC.append(WHERE_FV);
			qC.append(SELECT_FV2);
			qC.append(FROM);
			qC.append(SELECT_FV3);
			qC.append(FROM_TN);
			qC.append(ORDER_BY);
			qC.append(SPACE4);
			qC.append(ROWNUM);
			qC.append(SPACE3);
			qC.append(SPACE);
			qC.append("                            WHERE  @FV >= @VAL1 \n");
			qC.append(SPACE2);
			qC.append("                     >0 \n");
			qC.append("                 AND \n");
			qC.append("                     (SELECT COUNT(*) \n");
			qC.append(FROM2);
			qC.append(SELECT_FV);
			qC.append("                                   FROM   @TN minus \n");
			qC.append("                                   SELECT @FV \n");
			qC.append("                                   FROM   @TN \n");
			qC.append(WHERE_FV);
			qC.append(SELECT_FV2);
			qC.append(FROM);
			qC.append(SELECT_FV3);
			qC.append(FROM_TN);
			qC.append(ORDER_BY);
			qC.append(SPACE4);
			qC.append(ROWNUM);
			qC.append(SPACE3);
			qC.append(SPACE);
			qC.append("                            WHERE  @FV <= @VAL2 \n");
			qC.append(SPACE2);
			qC.append("                     > 0 \n");
			qC.append("              THEN 1 \n");
			qC.append("              ELSE 0 \n");
			qC.append("       END \n");
			qC.append("FROM   dual");

			StringBuilder retC = new StringBuilder();
			retC.append("  '" + initValue
					+ "' AND " + tablePrefix + "." + fieldValue + " <= \n");
			retC.append(" (SELECT " + fieldValue + NL);
			retC.append("FROM \n");
			retC.append("       (SELECT  " + fieldValue + NL);
			retC.append("       FROM     " + tableName + NL);
			retC.append("       ORDER BY " + fieldValue + " DESC \n");
			retC.append("       ) \n");
			retC.append("WHERE  rownum =1 \n");
			retC.append(") OR " + tablePrefix + "." + fieldValue + " >= \n");
			retC.append("SELECT " + fieldValue + NL);
			retC.append("FROM \n");
			retC.append("       (SELECT " + fieldValue + NL);
			retC.append("       FROM   " + tableName + " minus \n");
			retC.append("       SELECT " + fieldValue + NL);
			retC.append("       FROM   " + tableName + NL);
			retC.append("       WHERE  " + fieldValue + " >= \n");
			retC.append("              (SELECT " + fieldValue + NL);
			retC.append("              FROM \n");
			retC.append("                     (SELECT  " + fieldValue + NL);
			retC.append("                     FROM     " + tableName + NL);
			retC.append("                     ORDER BY " + fieldValue + NL);
			retC.append(SPACE2);
			retC.append("              WHERE  rownum=1 \n");
			retC.append("              ) \n");
			retC.append("       ) \n");
			retC.append("WHERE  rownum   =1 \n");
			retC.append("   AND " + tablePrefix + "." + fieldValue + " <= $P{"
					+ endValue + "} ");

			qry.add(0, qA.toString());
			qry.add(1, qB.toString());
			qry.add(2, qC.toString());

			PreparedStatement psmt;
			/*
			 * Fill PStatemnet for execution
			 */
			for (int i = 0; i < qry.size(); i++) {
				tempQry = 
					qry.get(i)
						.replace("@TN", tableName)
						.replace("@FV", fieldValue)
						.replace("@VAL1","'"+initValue+"'")
						.replace("@VAL2","'"+endValue+"'");
				
				psmt = DB.prepareStatement(tempQry, null);
				
				try {/*
					psmt.setString(1, initValue);
					psmt.setString(2, endValue);*/
					psmt.close();
				} catch (Exception e) {
					logger.log(Level.WARNING, "Error al ejecutar", e);
				}
				
				lstPrepSt.add(i, DB.prepareStatement(tempQry, null));
			}

			/*
			 * Execution of PStatement
			 */
			int cont = 0;

			for (int i = 0; i < qry.size(); i++) {
				
				cont++;
				
				try {
					
					if (cont>qry.size()) {
						throw new Exception("Error no coincide ningun criterio de consulta");
					}
					
					rs = lstPrepSt.get(i).executeQuery();
					
					if (rs.next() &&  rs.getInt(1) == 1) {
						if (cont == 1) {
							retVal = retA.toString();
							break;
						} else if (cont == 2) {
							retVal = retB.toString();
							break;
						} else {
							retVal = retC.toString();
							break;
						}
					}

				} catch (Exception e) {
					logger.log(Level.WARNING, "Error al ejecutar", e);
				} finally {
					try {
						if(rs != null) {
							rs.close();
						}
					} catch (SQLException e) {
						logger.log(Level.WARNING, "", e);
					}
				}
			}//end for

			if(StringUtils.isEmpty(retVal) && queryBuilt != null) {
				retVal = queryBuilt.toString();
			}
		}
		
		try { 
			for (PreparedStatement pstmt : lstPrepSt) {
				try {
					if(!pstmt.isClosed()) {
						pstmt.close();
					}
				} catch (SQLException e) {
					logger.log(Level.WARNING, " ", e);
				}
			}
		} catch (Exception e) {
			logger.log(Level.SEVERE, "", e);
		}
		
		return retVal;
	}

}
