package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;

import org.compiere.util.CLogger;

public class MEXMECentroMedico extends X_EXME_CentroMedico {
	private static final long	serialVersionUID	= 1L;
	 /**	Static Logger				*/
	@SuppressWarnings("unused")
	private static CLogger		s_log = CLogger.getCLogger (MEXMECentroMedico.class);

    /**
     * @param ctx
     * @param EXME_Medico_ID
     * @param trxName
     */
    public MEXMECentroMedico(Properties ctx, int EXME_CentroMedico_ID, String trxName) {
        super(ctx, EXME_CentroMedico_ID, trxName);
    }

    /**
     * Centro M�dico
     * @param ctx
     * @param rs
     * @param trxName
     */
    public MEXMECentroMedico(Properties ctx, ResultSet rs, String trxName) {
        super(ctx, rs, trxName);
    }
    
    private MLocation m_location = null;
   
    public String getDireccion(){
		String direccion = null;
		
		m_location =  new MLocation(getCtx(), getC_Location_ID(), get_TrxName());
		
		
		direccion = (m_location.getAddress1()==null?"":(m_location.getAddress1()+", ")) +
					(m_location.getAddress2()==null?"":(m_location.getAddress2()+", ")) +
					(m_location.getAddress3()==null?"":(m_location.getAddress3()+", ")) +
					(m_location.getAddress4()==null?"":(m_location.getAddress4()+", ")) +
					(m_location.getPostal()==null?"":(m_location.getPostal()+", ")) +
					(m_location.getCity()==null?"":(m_location.getCity()+", "));
					
		if(m_location.getTownCouncil()!=null)	
			direccion += m_location.getTownCouncil().getName()==null?"":(m_location.getTownCouncil().getName()+", ");
		direccion += (m_location.getRegionName()==null?"":(m_location.getRegionName()+", ")) +
		   			 (m_location.getCountryName()==null?"":(m_location.getCountryName()));
		
		return direccion;
	}
    
    public String getColonia(){
		String direccion = null;
		
		if(m_location==null || m_location.getC_Location_ID() != getC_Location_ID())
			m_location =  new MLocation(getCtx(), getC_Location_ID(), get_TrxName());
		
		direccion = m_location.getAddress2()==null?"":m_location.getAddress2();
		
		return direccion;
	}
}
