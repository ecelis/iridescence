package org.compiere.process;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;

import org.compiere.model.X_EXME_EstServResp;
import org.compiere.util.CLogger;
import org.compiere.util.DB;

/**
 * Clase de Asignacion de Responsables. Establece al responsable e
 * inserta la fecha de termino de responsabilidad al responsable anterior
 * para cada Estacion de servicio
 * <p>
 * Fecha: $Date: 2009/30/08 18:54:58 $ <p>
 *
 * @author Expert Victoria
 * @version $Revision: 1.0 $
 */
public class Responsible extends SvrProcess {

	private static CLogger s_log = CLogger.getCLogger (Responsible.class);
	
	/**
	 * Establece la fecha de termino al responsable que sale e inactiva el registro,
	 * y pone como procesado el registro nuevo de responsabilidad.
	 *
	 * @param N/A
	 *
	 * @return Una cadena con un mensaje si fue satisfactorio el proceso o no.
	 */
	protected String doIt() throws Exception {
		// TODO Auto-generated method stub
		X_EXME_EstServResp resp=new X_EXME_EstServResp(getCtx(),getRecord_ID(),get_TrxName());
		Vector<String> respt=getResponsible(resp.getEXME_EstServ_ID());
		for(int i=0;i<respt.size();i++){
			if(getRecord_ID()!=Integer.parseInt(respt.get(i))){
				X_EXME_EstServResp termino=new X_EXME_EstServResp(getCtx(), Integer.parseInt(respt.get(i)), get_TrxName());
				termino.setFechaFin(resp.getFechaIni());
				termino.setIsActive(false);
				if(!termino.save(get_TrxName())){
					return "@Error@";
				}
			}
		}
		resp.setIsTransfered("Y");
		if(!resp.save(get_TrxName())){
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
		// TODO Auto-generated method stub
	}
	
	/**
	 * Metodo que obtiene el responsable actual que va a ser
	 * remplasado.
	 *
	 * @param estser Identificador de la estacion de servicio
	 * a la que se le va a asignar el nuevo responsable
	 *
	 * @return list Vector que contiene los identificadores
	 * de los responsables de la estacion de servicio.
	 */
	private Vector<String> getResponsible(int estserv){
		Vector<String> list = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT EXME_EstServResp_ID FROM EXME_EstServResp" +
				" WHERE fechafin is null" +
				" AND exme_estserv_id= ?" +
				" AND isactive='Y'";
		try{
			pstmt = DB.prepareStatement(sql,null);
			pstmt.setInt(1, estserv);
			rs = pstmt.executeQuery();
			list = new Vector<String>();
			//list.add(new OptionItem("0"," "));
			while (rs.next())
				list.add(rs.getString(1));
		}catch(SQLException e){
			s_log.log(Level.SEVERE, "getResponsible", e);
		}finally{
			try{
				if (pstmt != null)
					pstmt.close();
				if (rs != null)
					rs.close();
			}catch (SQLException e){
				s_log.log(Level.SEVERE, "getResponsible", e);
			}
		}
		return list;
	}
}
