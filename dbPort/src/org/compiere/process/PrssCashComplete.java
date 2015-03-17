package org.compiere.process;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.model.MCash;
import org.compiere.model.MEXMELookupInfo;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Trx;

/**
 * 
 * Proceso de correccion de folios de caja no completados
 * 
 */

public class PrssCashComplete extends SvrProcess {

	private MCash[] cash = null;

	/**
	 * Llena el arreglo de cajas erroneas
	 */
	protected void prepare() {
		cash = getCash();
	} // prepare


	/**
	 * ejecuta el proceso
	 * Actualiza y Completa las cajas, para el posteo
	 */
	protected String doIt() throws Exception {
		Trx m_trx = null;
		String message = null;
		
		try {
			if (cash != null && cash.length > 0) {

				// creamos la transaccion
				m_trx = Trx.get(Trx.createTrxName("CSH"), true);
				String trxName = m_trx.getTrxName();

				// barremos cada una de las lineas
				for (int i = 0; i < cash.length; i++) {
					// actualizamos los estatus
					MCash m_cash = cash[i];
					m_cash.set_TrxName(trxName);
					m_cash.setDocAction(MCash.DOCACTION_Complete);
					m_cash.setDocStatus(MCash.DOCSTATUS_Drafted);
					if (!m_cash.save(trxName)) {
						throw new Exception(": Error al actualizar el Folio " + m_cash.getSecuencia());
					} 
					// ejecutamos el complete
					m_cash.setDocStatus(m_cash.completeIt());
					m_cash.setDocAction(MCash.DOCACTION_Close);
					m_cash.setProcessed(true);

					if (!m_cash.getDocStatus().equals(MCash.DOCSTATUS_Completed) || !m_cash.save(trxName)) {
						throw new Exception(": Error al completar el Folio " + m_cash.getSecuencia());
					}// m_trx.commit();
				}

				if (m_trx != null) {
					m_trx.commit();
					m_trx.close();
					m_trx = null;
				}
				message = "Proceso exitoso. " + cash.length + " folios de caja corregidos.";

			} else {
				message = "No hay folios de caja erroneos";
			}

		} catch (Exception e) {
			e.printStackTrace();
			if (m_trx != null) {
				m_trx.rollback();
				m_trx.close();
				m_trx = null;
			}
			log.log(Level.SEVERE, "doIt - el Proceso no se pudo generar - " + e.getMessage());
			message = "El Proceso no se pudo completar. " + e.getMessage();
		} finally {
			if (m_trx != null) {
				m_trx.rollback();
				m_trx.close();
				m_trx = null;
			}
		}
		return message;
	} // doIt
	
	
	/**
	 * Regresa los cash con errores
	 * @return
	 */
	private MCash[] getCash() {
		
		Properties ctx = Env.getCtx();
		
		int client = Env.getContextAsInt(ctx,"AD_Client_ID");
		int org = Env.getContextAsInt(ctx,"AD_Org_ID");
		if(client <= 0 || org < 0){
		    return null;
		}
		
		MCash[] retValue = null;
		
		StringBuilder sql = new StringBuilder(100); 
		sql.append(" select distinct c_cash.* ")
			.append(" from c_cash ")
			.append(" inner join c_cashline cl on ( c_cash.c_cash_id = cl.c_cash_id and cl.isactive = 'Y') ")
			.append(" where c_cash.docstatus = 'VO' and c_cash.docaction = '--' and c_cash.isactive = 'Y' ");
		
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx," ", "C_Cash"));
		
		PreparedStatement pstmt = null;
	    ResultSet rs = null;
		MCash m_cash = null;
		ArrayList<MCash> list = new ArrayList<MCash>();
		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			
			rs = pstmt.executeQuery();
			while (rs.next()){
				m_cash = new MCash(ctx, rs, null);
				list.add(m_cash);
			}
			rs.close();
			pstmt.close();
			rs = null;
			pstmt = null;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
	        try {
	            if (rs != null)
	                rs.close();

	            if (pstmt != null)
	                pstmt.close();
	        } catch (SQLException e) {
	        }

	        rs = null;
	        pstmt = null;
	    }
		
		retValue = new MCash[list.size ()];
		list.toArray (retValue);
		return retValue;
	}
}