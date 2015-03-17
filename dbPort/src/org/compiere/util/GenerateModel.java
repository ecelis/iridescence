package org.compiere.util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;

import org.compiere.Compiere;
import org.compiere.util.CLogMgt;
import org.compiere.util.CLogger;
import org.compiere.util.DB;

/**
 *  Generate Model Classes extending PO.
 *  Base class for CMP interface - will be extended to create byte code directly
 */
public class GenerateModel
{

	/**
	 * the connection string, to avoid Compiere.properties
	 */
	private static final String connString =
		"CConnection[name=apl01{10.90.1.80-10.90.1.79-expert},AppsHost=localhost," +
		"AppsPort=0,Profile=L,type=%s,DBhost=%s,DBport=%s,DBname=%s," +
		"BQ=false,FW=false,FWhost=,FWport=1630,UID=%s,PWD=%s]";

	/**	Logger			*/
	private static CLogger	log	= CLogger.getCLogger (GenerateModel.class);

	/**
	 * 	String representation
	 * 	@return string representation
	 */
	public String toString()
	{
		StringBuffer sb = new StringBuffer ("GenerateModel[").append("]");
		return sb.toString();
	}


	/**************************************************************************
	 * 	Generate PO Model Class.
	 * 	<pre>
	 * 	Example: java GenerateModel.class mydirectory myPackage 'U','A'
	 * 	would generate entity type User and Application classes into mydirectory.
	 * 	Without parameters, the default is used:
	 * 	C:\model\ ecaresoft.model 'U','A'
	 * 	</pre>
	 * 	@param args directory package entityType
	 * 	- directory where to save the generated file
	 * 	- package of the classes to be generated
	 * 	- entityType to be generated
	 */
	public static void main (String[] args)
	{

		if(args.length > 10) {

			Ini.setProperty(Ini.P_CONNECTION, String.format(connString, args[5], args[6], args[7], args[8], args[9], args[10]));

			Compiere.startupEnvironment((args.length > 4 && args[4].equals("Client")));
			CLogMgt.setLevel(Level.FINE);
			log.info("Generate Model   $Revision: 1.42 $");
			log.info("----------------------------------");
			//	first parameter
			String directory = "C:\\model\\";
			if (args.length > 0)
				directory = args[0];
			if (directory == null || directory.length() == 0)
			{
				System.err.println("No Directory");
				System.exit(1);
			}
			log.info("Directory: " + directory);

			//	second parameter
			String packageName = "compiere.model";
			if (args.length > 1)
				packageName = args[1];
			if (packageName == null || packageName.length() == 0)
			{
				System.err.println("No package");
				System.exit(1);
			}
			log.info("Package:   " + packageName);

			//	third parameter
			String entityType = "'U','A'";	//	User, Application
			if (args.length > 2)
				entityType = args[2];
			if (entityType == null || entityType.length() == 0)
			{
				System.err.println("No EntityType");
				System.exit(1);
			}
			StringBuffer sql = new StringBuffer("EntityType IN (")
				.append(entityType).append(")");
			log.info(sql.toString());
			log.info("----------------------------------");

			String tableLike = null;
			tableLike = "'%'";	//	All tables
			// tableLike = "'AD_OrgInfo', 'AD_Role', 'C_CashLine', 'C_Currency', 'C_Invoice', 'C_Order', 'C_Payment', 'M_InventoryLine', 'M_PriceList', 'M_Product', 'U_POSTerminal'";	//	Only specific tables
			if (args.length > 3)
				tableLike = args[3];
			log.info("Table Like: " + tableLike);

			//	complete sql
			sql.insert(0, "SELECT AD_Table_ID "
				+ "FROM AD_Table "
				+ "WHERE (TableName IN ('RV_WarehousePrice','RV_BPartner')"	//	special views
				+ " OR IsView='N')"
				+ " AND TableName NOT IN ('EXME_T_Cuest') "//Expert - Lama 25/03/2010 : excludes special table
				+ " AND IsActive = 'Y' AND TableName NOT LIKE '%_Trl' AND ");
			//sql.append(" AND TableName LIKE ").append(tableLike);
			sql.append(" AND TableName IN (").append(tableLike).append(")"); // only specific tables

			sql.append(" ORDER BY TableName");

			//
			int count = 0;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try
			{
				pstmt = DB.prepareStatement(sql.toString(), null);
				rs = pstmt.executeQuery();
				while (rs.next())
				{
					new ModelInterfaceGenerator(rs.getInt(1), directory, packageName);
					new ModelClassGenerator(rs.getInt(1), directory, packageName);
					count++;
				}
	 		}
			catch (Exception e)
			{
				log.log(Level.SEVERE, sql.toString(), e);
			}
			finally
			{
				DB.close(rs, pstmt);
				rs = null; pstmt = null;
			}
			log.info("Generated = " + count);
		} else {
			System.out.println("Usage : GenerateModel <target_directory> <package> <entity_type> <table> <mode> <db_ip_address> <db_name> <db_user> <db_password>\n");
			System.out.println("\tWhere:");
			System.out.println("\t\ttarget_directory : directory to where the .java files will be generated.");
			System.out.println("\t\tpackage : the package for the model classes (normally org.compiere.model).");
			System.out.println("\t\tentity_type : the entity types for the models : EXME, D.");
			System.out.println("\t\ttable : the table or wildcard to use for the model generation (AD_Client, C_BPartner%).");
			System.out.println("\t\tmode : Must be \"Client\".");
			System.out.println("\t\tdatabase Type : Oracle or PostgreSQL");
			System.out.println("\t\tdb_ip_address : the IP address or server name for the database server.");
			System.out.println("\t\tdb_name : the database name.");
			System.out.println("\t\tdb_user : the database user.");
			System.out.println("\t\tdb_password : the password for the database user.");
		}
	}

}
