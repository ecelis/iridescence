/* * Created on 6/09/2005 * */package org.compiere.model;import java.sql.ResultSet;import java.util.Properties;/** * @author GUEST_MTY */public class MInterfazProcessorLog extends X_EXME_InterfazProcessorLogimplements CompiereProcessorLog {	/**	 * 	 */	private static final long serialVersionUID = 1L;	/**	 * @param ctx	 * @param EXME_InterfazProcessorLog_ID	 * @param trxName	 */	public MInterfazProcessorLog(Properties ctx, int EXME_InterfazProcessorLog_ID, String trxName) {		super(ctx, EXME_InterfazProcessorLog_ID, trxName);	}	/**	 * @param ctx	 * @param rs	 * @param trxName	 */	public MInterfazProcessorLog(Properties ctx, ResultSet rs, String trxName) {		super(ctx, rs, trxName);	}	/**	 * Parent Constructor	 * 	 * @param parent	 *            parent	 * @param summary	 *            summary	 */	public MInterfazProcessorLog(MInterfazProcessor parent, String summary) {		this(parent.getCtx(), 0, parent.get_TrxName());		setClientOrg(parent);		setEXME_InterfazProcessor_ID(parent.getEXME_InterfazProcessor_ID());		setSummary(summary);	} // MAlertProcessorLog}