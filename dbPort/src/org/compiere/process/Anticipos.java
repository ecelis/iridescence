package org.compiere.process;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.model.MEXMEAnticipo;
import org.compiere.model.MEXMELookupInfo;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.Trx;

/**
 * 
 * @author Omar de la Rosa
 *
 */
public class Anticipos extends SvrProcess{

	@Override
	protected void prepare() {}

	@Override
	protected String doIt() throws Exception {
		List <MEXMEAnticipo> listado = new ArrayList<MEXMEAnticipo>();
		Trx m_trx = null;
		m_trx = Trx.get(Trx.createTrxName("ANT"), true);
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append("select cp.EXME_CTAPAC_ID, cpe.EXME_CTAPACEXT_ID, sum(p.PAYAMT) from EXME_CTAPACPAG cpp ")
		   .append("inner join C_PAYMENT p on (cpp.C_PAYMENT_ID = p.C_PAYMENT_ID ) ")
		   .append("inner join EXME_CTAPACEXT cpe on (cpp.EXME_CTAPACEXT_ID = cpe.EXME_CTAPACEXT_ID) ")
		   .append("inner join EXME_CTAPAC cp on (p.EXME_CTAPAC_ID = cp.EXME_CTAPAC_ID) ")
		   .append("Where p.PAYAMT >= 0 ")
		   .append(MEXMELookupInfo.addAccessLevelSQL(getCtx(), " ", "EXME_CtaPacPag", "cpp"))
		   .append(" group by cp.EXME_CTAPAC_ID, cpe.EXME_CTAPACEXT_ID "); 
		
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
        try {
        	pstmt = DB.prepareStatement(sql.toString(), m_trx.getTrxName());
        	rs = pstmt.executeQuery();
        	while(rs.next()){
        		MEXMEAnticipo anti = MEXMEAnticipo.getAnticipo(getCtx(),rs.getInt(1), rs.getInt(2), m_trx.getTrxName());
        		if(anti == null)
        			anti = new MEXMEAnticipo(getCtx(), 0, m_trx.getTrxName());
        		anti.setEXME_CtaPac_ID(rs.getInt(1));
        		anti.setEXME_CtaPacExt_ID(rs.getInt(2));
        		anti.setTotal(rs.getBigDecimal(3));
        		listado.add(anti);
        		if(!anti.save(m_trx.getTrxName())) {
        			if(m_trx != null){
                        m_trx.rollback();
                        m_trx.close();
                        m_trx = null;
                    }
        			throw new Exception("Error al guardar anticipos");
                }        		
        	}       	        	        	
        	List <Integer> cuentas = new ArrayList<Integer>();
        	for(int i = 0; i < listado.size(); i++){
        		if(!cuentas.contains(listado.get(i).getEXME_CtaPacExt_ID())){
        			devoluciones(listado.get(i), getCtx(), m_trx);
        			cuentas.add(listado.get(i).getEXME_CtaPacExt_ID());
        		}
        	}
        	
        	if (m_trx != null) {
				m_trx.commit();
				m_trx.close();
				m_trx = null;
			}
        	
            pstmt.close();
            pstmt = null;
            
    	} catch (Exception e){
    		log.log(Level.SEVERE, "doIt - No se pudo realizar la operacion - " + e.getMessage());   
    		m_trx.rollback();
    		return "No se pudo realizar la operacion, ver log de errores";
    	} finally{
    		try{
				if (pstmt != null)
					pstmt.close ();
				if(rs!=null)
	                rs.close();
			}catch (Exception e){}
			pstmt = null;
			rs = null;
    	}
		return "Operacion realizada exitosamente";
	}
	
	public void devoluciones(MEXMEAnticipo anti, Properties ctx, Trx m_trx) throws Exception{
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append("select sum(p.PAYAMT) from EXME_CTAPACPAG cpp ")
		   .append("inner join C_PAYMENT p on (cpp.C_PAYMENT_ID = p.C_PAYMENT_ID) ")
		   .append("inner join EXME_CTAPACEXT cpe on (cpp.EXME_CTAPACEXT_ID = cpe.EXME_CTAPACEXT_ID) ")
		   .append("inner join EXME_CTAPAC cp on (p.EXME_CTAPAC_ID = cp.EXME_CTAPAC_ID) ")
		   .append("Where p.PAYAMT <= 0 and cp.EXME_CtaPac_ID = ? and cpe.EXME_CTAPACEXT_ID = ? ")
		   .append(MEXMELookupInfo.addAccessLevelSQL(getCtx(), " ", "EXME_CtaPacPag", "cpp"));
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			pstmt = DB.prepareStatement(sql.toString(), m_trx.getTrxName());
			pstmt.setInt(1, anti.getEXME_CtaPac_ID());
			pstmt.setInt(2, anti.getEXME_CtaPacExt_ID());
        	rs = pstmt.executeQuery();
        	BigDecimal monto = null;
        	if(rs.next()){
        		monto = rs.getBigDecimal(1);
        	}
        	if(monto!=null)
	        	if(!(monto.compareTo(BigDecimal.ZERO)==0)){
	        		anti.setTotal(anti.getTotal().subtract(monto.multiply(new BigDecimal(-1))));
	        		if(!anti.save(m_trx.getTrxName())){
	        			throw new Exception("Error al guardar anticipos");
	        		}
	        	}			
		} catch (Exception e){
    		log.log(Level.SEVERE, "doIt - No se pudo realizar la operacion - " + e.getMessage());   		
    	} finally{
    		try{
				if (pstmt != null)
					pstmt.close ();
				if(rs!=null)
	                rs.close();
			}catch (Exception e){}
			pstmt = null;
			rs = null;
    	}
	}

}
