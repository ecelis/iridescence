/*

 * Created on 6/09/2005

 *

 */

package org.compiere.model;



import java.sql.PreparedStatement;

import java.sql.ResultSet;

import java.sql.Timestamp;

import java.util.ArrayList;

import java.util.Properties;

import java.util.logging.Level;



import org.compiere.util.CLogger;

import org.compiere.util.DB;

import org.compiere.util.Trx;



/**

 * @author GUEST_MTY

 *

 */

public class MInterfazProcessor extends X_EXME_InterfazProcessor implements

        CompiereProcessor {



    /**

     * @param ctx

     * @param EXME_InterfazProcessor_ID

     * @param trxName

     */

    public MInterfazProcessor(Properties ctx, int EXME_InterfazProcessor_ID,

            String trxName) {

        super(ctx, EXME_InterfazProcessor_ID, trxName);

    }



    /**

     * @param ctx

     * @param rs

     * @param trxName

     */

    public MInterfazProcessor(Properties ctx, ResultSet rs, String trxName) {

        super(ctx, rs, trxName);

    }

    

    /**

	 * 	Get Active

	 *	@param ctx context

	 *	@return active processors

	 */

	public static MInterfazProcessor[] getActive (Properties ctx)

	{

		ArrayList list = new ArrayList ();

		String sql = "SELECT * FROM EXME_InterfazProcessor WHERE IsActive='Y'";

		PreparedStatement pstmt = null;
		sql = MEXMELookupInfo.addAccessLevelSQL(ctx,sql,"EXME_InterfazProcessor");

		try

		{

			pstmt = DB.prepareStatement (sql);

			ResultSet rs = pstmt.executeQuery ();

			while (rs.next ())

				list.add (new MInterfazProcessor (ctx, rs, null));

			rs.close ();

			pstmt.close ();

			pstmt = null;
			
			rs=null;

		}

		catch (Exception e)

		{

			s_log.log(Level.SEVERE, "getActive", e);

		}

		try

		{

			if (pstmt != null)

				pstmt.close ();

			pstmt = null;

		}

		catch (Exception e)

		{

			pstmt = null;

		}

		MInterfazProcessor[] retValue = new MInterfazProcessor[list.size ()];

		list.toArray (retValue);

		return retValue;

	}	//	getActive

	

	/**	Static Logger	*/

	private static CLogger	s_log	= CLogger.getCLogger (MInterfazProcessor.class);



    /* (non-Javadoc)

     * @see org.compiere.model.CompiereProcessor#getServerID()

     */

    public String getServerID() {

        return "InterfazProcessor" + get_ID();

    }



    /* (non-Javadoc)

     * @see org.compiere.model.CompiereProcessor#getDateNextRun(boolean)

     */

    public Timestamp getDateNextRun(boolean requery) {

        if (requery)

			load(get_TrxName());

		return getDateNextRun();

    }



    /* (non-Javadoc)

     * @see org.compiere.model.CompiereProcessor#getLogs()

     */

    public CompiereProcessorLog[] getLogs() {

        ArrayList list = new ArrayList ();

		String sql = "SELECT * "

			+ "FROM EXME_InterfazProcessorLog "

			+ "WHERE EXME_InterfazProcessor_ID=? " 

			+ "ORDER BY Created DESC";

		PreparedStatement pstmt = null;

		sql = MEXMELookupInfo.addAccessLevelSQL(getCtx(), sql, "EXME_InterfazProcessorLog");
		
		try

		{

			pstmt = DB.prepareStatement (sql);

			pstmt.setInt (1, get_ID());

			ResultSet rs = pstmt.executeQuery ();

			while (rs.next ())

				list.add (new MInterfazProcessorLog (getCtx(), rs, null));

			rs.close ();

			pstmt.close ();

			pstmt = null;
			
			rs=null;

		}

		catch (Exception e)

		{

			log.log(Level.SEVERE, "getLogs", e);

		}

		try

		{

			if (pstmt != null)

				pstmt.close ();

			pstmt = null;

		}

		catch (Exception e)

		{

			pstmt = null;

		}

		MInterfazProcessorLog[] retValue = new MInterfazProcessorLog[list.size ()];

		list.toArray (retValue);

		return retValue;

    }

    

    /**

     * Interfaces del Proceso de Interfaz

     */

    private MInterfaz[] m_interfaces = null;

    

    /**

	 * 	Get interfaces

	 *	@param reload reload data

	 *	@return array of alerts

	 */

	public MInterfaz[] getInterfaces (boolean reload)

	{
		Trx m_trx = null;
		
    	String slTransName = null;
		
		if (m_interfaces != null && m_interfaces.length > 0 && !reload)

			return m_interfaces;

		String sql = "SELECT * FROM EXME_Interfaz "

			+ "WHERE EXME_InterfazProcessor_ID=? AND IsActive = 'Y'";

		ArrayList list = new ArrayList ();

		PreparedStatement pstmt = null;
		
		ResultSet rs = null;
		
		sql = MEXMELookupInfo.addAccessLevelSQL(getCtx(), sql, "EXME_Interfaz");

		try

		{
			m_trx = Trx.get(Trx.createTrxName(), true);
			slTransName = m_trx.getTrxName();
			
			pstmt = DB.prepareStatement (sql);

			pstmt.setInt (1, get_ID());

			rs = pstmt.executeQuery ();

			while (rs.next ())

				list.add (new MInterfaz (getCtx(), rs, slTransName));

			rs.close ();

			pstmt.close ();

			pstmt = null;
			
			rs = null;

		}

		catch (Exception e)

		{

			log.log(Level.SEVERE, "getInterfaces", e);

		}
		
		finally
		
		{
			try{
				if (m_trx!=null)
					m_trx.close();
				
				if (rs != null)
					rs.close ();
				
				if (pstmt != null)
					pstmt.close ();
			
			} 
			
			catch(Exception e1)
			{
				
				e1.printStackTrace();
				
			}
			
			finally
			{
				
				m_trx	= null;
				
				rs		= null;
				
				pstmt	= null;
				
			}
			
		}

		m_interfaces = new MInterfaz[list.size ()];

		list.toArray (m_interfaces);

		return m_interfaces;

	}	//	getInterfaces



	/**

	 * 	Delete old Request Log

	 *	@return number of records

	 */

	public int deleteLog()

	{

		if (getKeepLogDays() < 1)

			return 0;

		String sql = "DELETE EXME_InterfazProcessorLog "

			+ "WHERE EXME_InterfazProcessor_ID=" + get_ID() 

			+ " AND (Created+" + getKeepLogDays() + ") < SysDate";

		int no = DB.executeUpdate(sql, get_TrxName());

		return 0;

	}	//	deleteLog



    public String getFrequencyType() {

        // TODO Auto-generated method stub

        return null;

    }

    

    

}

