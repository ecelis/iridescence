package org.compiere.process;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;

import org.compiere.model.X_A_Asset_Delivery;
import org.compiere.model.X_EXME_Asset_DeliveryDet;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.OptionItem;
import org.compiere.util.Trx;

/**
 * Clase de Movimiento de Activos. Resliza los movimientos de activos
 * entre las Estaciones de Servicio.
 * 
 * <p>
 * Fecha: $Date: 2009/30/08 19:10:58 $ <p>
 *
 * @author Expert Victoria
 * @version $Revision: 1.0 $
 */
public class AssetMovement extends SvrProcess {

	private static CLogger s_log = CLogger.getCLogger (AssetMovement.class);
	
	/**
	 * Se inactivan los activos correspondientes a la estacion de servicio de referencia
	 * de la que provienen.
	 *
	 * @param N/A
	 *
	 * @return Una cadena con un mensaje si fue satisfactorio el proceso o no.
	 */
	protected String doIt() throws Exception {
		
		
		String trxName = get_TrxName();
		Trx m_trx= Trx.get(trxName, true);
		X_A_Asset_Delivery entrega=new X_A_Asset_Delivery(getCtx(),getRecord_ID(),trxName);
		entrega.setIsTransfered("Y");
		
		Vector<OptionItem> tr=getTransfer(entrega.getEXME_EstServRef_ID());
		if(tr != null && tr.size() > 0){
			for(int i = 0;i < tr.size();i++){
				X_EXME_Asset_DeliveryDet detEnt = new X_EXME_Asset_DeliveryDet(getCtx(),0,trxName);
				detEnt.setA_Asset_ID(Integer.parseInt(tr.get(i).getId()));
				detEnt.setA_Asset_Delivery_ID(getRecord_ID());
			
				if(!detEnt.save(get_TrxName())){
					if (m_trx != null) {
						m_trx.rollback();
						m_trx.close();
						m_trx = null;
					}
					return "@Error: No se pudo registrar la entrega en la estación correspondiente@";
				}
				detEnt=null;
			
				X_EXME_Asset_DeliveryDet detDel = new X_EXME_Asset_DeliveryDet(getCtx(),Integer.parseInt(tr.get(i).getLabel()),trxName);
				detDel.setIsActive(false);
			
				if(!detDel.save(get_TrxName())){
					if (m_trx != null) {
						m_trx.rollback();
						m_trx.close();
						m_trx = null;
					}
					return "@Error: No se pudo desactivar los detalles movidos@";
				}
				detDel=null;
			}
		}else{
			if (m_trx != null) {
				m_trx.rollback();
				m_trx.close();
				m_trx = null;
			}
			return "@Error: No existen activos seleccionados para su movimiento@";
		}
		
		
		
		if(!entrega.save(get_TrxName())){
			if (m_trx != null) {
				m_trx.rollback();
				m_trx.close();
			 	m_trx = null;
			}
			return "@Error@";
		}
		return "@OK@";
	}

	/**
	 * Metodo implementtado por default.
	 *
	 * @param N/A
	 *
	 * @return N/A
	 */
	protected void prepare() {
		
	}
	
	/**
	 * Metodo que obtiene el identificador de las entrega y de los
	 * activo de cada entrega para la estacion de servicio que se 
	 * le van a retirar los activos.
	 *
	 * @param EstSer Identificador de la estacion de servicio
	 * de la cual provienen los activos
	 *
	 * @return list Vector de tipo OptionItem que contiene los
	 *  identificadores de la entrega y del activo de la estacion de servicio.
	 */
	private Vector<OptionItem> getTransfer(int EstServ){
		Vector<OptionItem> list = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT D.A_Asset_ID, D.EXME_Asset_DeliveryDet_ID FROM EXME_Asset_DeliveryDet D" +
				" INNER JOIN A_Asset_Delivery E ON E.A_Asset_Delivery_ID=D.A_Asset_Delivery_ID" +
				" WHERE D.IsTransfered = 'Y' AND D.IsActive = 'Y' AND E.EXME_EstServ_ID = ?";
		try{
			pstmt = DB.prepareStatement(sql,null);
			pstmt.setInt(1, EstServ);
			rs = pstmt.executeQuery();
			list = new Vector<OptionItem>();
			//list.add(new OptionItem("0"," "));
			while (rs.next())
				list.add(new OptionItem(rs.getString(1),rs.getString(2)));
		}catch(SQLException e){
			s_log.log(Level.SEVERE, "getTransfer", e);
		}finally{
			try{
				if (pstmt != null)
					pstmt.close();
				if (rs != null)
					rs.close();
			}catch (SQLException e){
				s_log.log(Level.SEVERE, "getTransfer", e);
			}
		}
		return list;
	}
}
