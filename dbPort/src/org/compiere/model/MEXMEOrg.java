package org.compiere.model;


@Deprecated 
public class MEXMEOrg/*extends MOrg*/{
    
    /**
	 * 
	 */
//	private static final long	serialVersionUID	= 1L;
	/** Logger  */
//    private static CLogger          s_log = CLogger.getCLogger (MEXMEOrg.class);
    
    /**
     * @param ctx
     * @param M_Org_ID
     * @param trxName
     */
//    public MEXMEOrg(Properties ctx, int M_Org_ID, String trxName) {
//        super(ctx, M_Org_ID, trxName);
//    }

    /**
     * @param ctx
     * @param rs
     * @param trxName
     */
//    public MEXMEOrg(Properties ctx, ResultSet rs, String trxName) {
//        super(ctx, rs, trxName);
//    }
    

    /**
     *  Get Organizations Of Client
     *  @param po persistent object
     *  @return array of orgs
     *
    public static MEXMEOrg[] getList (Properties ctx)
    {
        ArrayList<MEXMEOrg> list = new ArrayList<MEXMEOrg>();
        String sql = "SELECT * FROM AD_Org WHERE AD_Client_ID=? AND IsSummary='N' AND AD_Org_ID <> 0 ORDER BY Name";
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try
        {
            pstmt = DB.prepareStatement (sql, null);
            pstmt.setInt (1, Env.getAD_Client_ID(ctx));
            rs = pstmt.executeQuery ();
            while (rs.next ())
                list.add (new MEXMEOrg (ctx, rs, null));
            
        }
        catch (Exception e)
        {
            s_log.log (Level.SEVERE, sql, e);
        }
        finally
        {
        	DB.close(rs, pstmt);
            pstmt = null;
        }
        
        MEXMEOrg[] retValue = new MEXMEOrg[list.size ()];
        list.toArray (retValue);
        return retValue;
    }   //  getOfClient*/
    
    /**
     *  Get Organizations Of Client
     *  @param po persistent object
     *  @return array of orgs
     *
    public static MEXMEOrg[] getOrgTrx(Properties ctx)
    {
        ArrayList<MEXMEOrg> list = new ArrayList<MEXMEOrg>();
        String sql = "SELECT * FROM AD_Org WHERE AD_Client_ID=? AND IsSummary='N' AND AD_Org_ID <> 0 ORDER BY Name";
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try
        {
            pstmt = DB.prepareStatement (sql, null);
            pstmt.setInt (1, Env.getAD_Client_ID(ctx));
            rs = pstmt.executeQuery ();
            while (rs.next ())
                list.add (new MEXMEOrg (ctx, rs, null));
        }
        catch (Exception e)
        {
            s_log.log (Level.SEVERE, sql, e);
        }
        finally
        {
        	DB.close(rs, pstmt);
            pstmt = null;
        }
        
        MEXMEOrg[] retValue = new MEXMEOrg[list.size ()];
        list.toArray (retValue);
        return retValue;
    }   //  getOfClient
    */
}
