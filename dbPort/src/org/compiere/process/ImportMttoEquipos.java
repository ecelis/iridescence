package org.compiere.process;

import java.math.BigDecimal;

import org.apache.struts.util.LabelValueBean;


public class ImportMttoEquipos extends SvrProcess {

	/**	Client to be imported to		*/
	private int				m_AD_Client_ID = 0;

	/**	Delete old Imported				*/
	private boolean			m_deleteOldImported = false;

	/** Organization to be imported to	*/
	private int				m_AD_Org_ID = 0;

	@Override
	protected void prepare()
	{

		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++)
		{

			String name = para[i].getParameterName();
			if (name.equals("AD_Client_ID"))
				m_AD_Client_ID = ((BigDecimal)para[i].getParameter()).intValue();

			if (name.equals("DeleteOldImported"))
				m_deleteOldImported = "Y".equals(para[i].getParameter());

			if (name.equals("AD_Org_ID"))
				m_AD_Org_ID = ((BigDecimal)para[i].getParameter()).intValue();
		}

	}	//	prepare

	@Override
	/**
	 *  Perrform process.
	 *  EXME_Equipoh
	 *  @return Message
	 *  @throws Exception
	 */
	protected String doIt() throws java.lang.Exception

	{
		
		try {
			
			MttoEquipos importacion = new MttoEquipos(getCtx(), m_AD_Client_ID, m_AD_Org_ID,
					m_deleteOldImported, get_TrxName());
			LabelValueBean result = importacion.doIt();
			addLog (0, null, new BigDecimal (result.getLabel()), "@Errors@");
			addLog (0, null, new BigDecimal (result.getValue()), "@EXME_Equipoh_ID@: @Inserted@");
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return "";

	}	//	doIt



}
