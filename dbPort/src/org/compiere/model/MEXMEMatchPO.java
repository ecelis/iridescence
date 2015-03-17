package org.compiere.model;

import java.io.File;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.process.DocAction;
import org.compiere.process.DocumentEngine;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
/** * Modelo para Conciliacion de Ordenes de Compra * (extiende de MMatchPO e implementa DocAction) * * <b>Fecha:</b> 20/Marzo/2006<p> * <b>Modificado: </b> $Author: mrojas $<p> * <b>En :</b> $Date: 2006/08/11 02:26:22 $<p> * * @author Gisela Lee * @version $Revision: 1.2 $ */public class MEXMEMatchPO extends MMatchPO implements DocAction{	private static final long serialVersionUID = 1L;    /** Process Message             */    private String      m_processMsg = null;        /** Static Logger   */    private static CLogger  s_log   = CLogger.getCLogger (MMatchPO.class);            /**************************************************************************     *  Standard Constructor     *  @param ctx context     *  @param M_MatchPO_ID id     */    public MEXMEMatchPO (Properties ctx, int M_MatchPO_ID, String trxName)    {        super (ctx, M_MatchPO_ID, trxName);        if (M_MatchPO_ID == 0)        {        //  setC_OrderLine_ID (0);        //  setDateTrx (new Timestamp(System.currentTimeMillis()));        //  setM_InOutLine_ID (0);        //  setM_Product_ID (0);            setM_AttributeSetInstance_ID(0);        //  setQty (Env.ZERO);            setPosted (false);            setProcessed (false);            setProcessing (false);        }    }   //  MMatchPO    /**     *  Load Construor     *  @param ctx context     *  @param rs result set     */    public MEXMEMatchPO (Properties ctx, ResultSet rs, String trxName)    {        super(ctx, rs, trxName);    }   //  MMatchPO        /**     *  Get PO Matches     *  @param ctx context     *  @param M_InOut_ID shipment     *  @return array of matches     */    public static MEXMEMatchPO[] getInOut (Properties ctx,         int M_InOut_ID, String trxName)    {        if (M_InOut_ID == 0)            return new MEXMEMatchPO[]{};        //        String sql = "SELECT * FROM M_MatchPO "            + " INNER JOIN M_InOutLine l ON (M_MatchPO.M_InOutLine_ID=l.M_InOutLine_ID) "            + " WHERE l.M_InOut_ID=?"; 
        
        sql = MEXMELookupInfo.addAccessLevelSQL(ctx, sql, "M_MatchPO");
                ArrayList<MEXMEMatchPO> list = new ArrayList<MEXMEMatchPO>();        PreparedStatement pstmt = null;
        ResultSet rs = null;        try        {            pstmt = DB.prepareStatement (sql, trxName);            pstmt.setInt (1, M_InOut_ID);            rs = pstmt.executeQuery ();            while (rs.next ())                list.add (new MEXMEMatchPO (ctx, rs, trxName));        }        catch (Exception e)        {            s_log.log(Level.SEVERE, sql, e);         }        finally        {        	DB.close(rs,pstmt);        }        MEXMEMatchPO[] retValue = new MEXMEMatchPO[list.size()];        list.toArray (retValue);        return retValue;    }   //  getInOut        /**     *  Approve Document     *  @return true if success      */    public boolean  approveIt()    {        log.info(toString());        return true;    }   //  approveIt        /**     *  Reverse Accrual - none     *  @return false     */    public boolean reverseAccrualIt()    {        log.info(toString());        return false;    }   //  reverseAccrualIt        /**     *  Get Process Message     *  @return clear text error message     */    public String getProcessMsg()    {        return m_processMsg;    }   //  getProcessMsg        /**     *  Invalidate Document     *  @return true if success      */    public boolean invalidateIt()    {        log.info(toString());        return true;    }   //  invalidateIt        public void setDocStatus (String DocStatus) {            }        /**     *  Get Document Approval Amount     *  @return amount     */    public BigDecimal getApprovalAmt()    {        return Env.ZERO;    }   //  getApprovalAmt    /**     *  Reject Approval     *  @return true if success      */    public boolean rejectIt()    {        log.info(toString());        return true;    }   //  rejectIt    
   /*************************************************************************     *  Get Summary     *  @return Summary of Document     */    public String getSummary()    {        StringBuffer sb = new StringBuffer();        sb.append(getDocumentNo());        return sb.toString();    }   //  getSummary        /**************************************************************************     *  Process document     *  @param processAction document action     *  @return true if performed     */    public boolean processIt (String processAction)    {        m_processMsg = null;        DocumentEngine engine = new DocumentEngine (this, getDocStatus());        return engine.processIt (processAction, getDocAction());    }   //  process        /**     *  Prepare Document     *  @return new status (In Progress or Invalid)      */    public String prepareIt()    {        log.info(toString());        return DocAction.STATUS_InProgress;    }   //  prepareIt        /**     *  Unlock Document.     *  @return true if success      */    public boolean unlockIt()    {        log.info(toString());        setProcessing(false);        return true;    }   //  unlockIt        public String getDocAction()     {        return DocAction.ACTION_Close;    }    /**     *  Get Document Owner (Responsible)     *  @return AD_User_ID     */    public int getDoc_User_ID()    {        return 0;    }   //  getDoc_User_ID    public String getDocStatus()     {        return DocAction.STATUS_Completed;    }        /**     *  Complete Document     *  @return new status (Complete, In Progress, Invalid, Waiting ..)     */    public String completeIt()    {        log.info(toString());        setProcessed(true);        return DocAction.STATUS_Completed;    }   //  completeIt    /**      *  Re-activate     *  @return false      */    public boolean reActivateIt()    {        log.info(toString());        return false;    }   //  reActivateIt            /**     *  Close Document.     *  @return true if success      */    public boolean closeIt()    {        log.info(toString());        setProcessed(true);        return true;    }   //  closeIt        /**     *  Reverse Correction - same date     *  @return true if success      */    public boolean reverseCorrectIt()    {        log.info(toString());        setProcessed(true);        return true;    }   //  reverseCorrectionIt        /**     *  Void Document.     *  @return true if success      */    public boolean voidIt()    {        log.info(toString());                    setProcessed(true);        return true;    }   //  voidIt        public int getC_Currency_ID()     {        return 0;    }         public File createPDF() {	    return null;    }            public String getDocumentInfo(){	    return null;    };}