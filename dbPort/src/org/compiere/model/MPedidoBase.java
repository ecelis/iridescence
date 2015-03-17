package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.apache.struts.util.LabelValueBean;
import org.compiere.util.CLogger;
import org.compiere.util.DB;

public class MPedidoBase extends X_EXME_PedidoBase {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** Static Logger               */
    private static CLogger      s_log = CLogger.getCLogger (MPedidoBase.class);
    
    /**
     * @param ctx
     * @param EXME_ActPaciente_ID
     * @param trxName
     */
    public MPedidoBase(Properties ctx, int EXME_PedidoBase_ID, String trxName) {
        super(ctx, EXME_PedidoBase_ID, trxName);
    }

    /**
     * @param ctx
     * @param rs
     * @param trxName
     */
    public MPedidoBase(Properties ctx, ResultSet rs, String trxName) {
        super(ctx, rs, trxName);
    }
    
	/**
	*  Obtenemos los almacenes (solicitante y resurtido) para un pedido base
	*
	*  @param movementID el identificador del movimiento
	*  @return Un resultset con los almacenes del movimiento
	*/
//  OK
	public static MPedidoBase getAlmacenesBase(Properties ctx, int baseID, 
			String trxName) throws Exception {
		MPedidoBase pedido = null;
		
		//almacenes solicitante y resurtido por pedido base
		String sql = " SELECT * "
		    + " FROM EXME_PedidoBase "
		    + " WHERE EXME_PedidoBase.EXME_PedidoBase_ID = ? "
		    + " AND EXME_PedidoBase.IsActive = 'Y' ";

		sql = MEXMELookupInfo.addAccessLevelSQL(ctx, sql, "EXME_PedidoBase");
		
		PreparedStatement pstmt = null;
        ResultSet rs = null;

		try {
			pstmt = DB.prepareStatement(sql, trxName);
			pstmt.setInt(1, baseID);
			rs = pstmt.executeQuery();
			if(rs.next()){
				pedido =  new MPedidoBase(ctx, rs, trxName);
			}
			rs.close();
            pstmt.close();
            pstmt = null;
            rs = null;
            
        } catch (SQLException e) {
            s_log.log(Level.SEVERE, "getForCtaPac (" + sql + ")", e);
            throw e;
        } finally {
            try {
                if (rs != null)
                    rs.close();

                if (pstmt != null)
                    pstmt.close();
            } catch (SQLException e) {
                s_log.log(Level.SEVERE, "getForCtaPac.close ", e);
                throw e;
            }

            rs = null;
            pstmt = null;
        }
		return pedido;

	}
	
	/**

	*  Obtenemos el detalle de un pedido base por su identificador

	*

	*  @param baseID el identificador del pedido base

	*  @return Una lista con el detalle del pedido base

	*/

	public static List<MovementLine> getPedidosBaseDet(Properties ctx, int baseID, 
			String trxName) throws Exception {

		List<MovementLine> lista = new ArrayList<MovementLine>();

		//Detalle del pedido base por id
		String sql = "SELECT EXME_PedidoBaseDet.Line, EXME_PedidoBaseDet.M_Product_ID, p.Value, p.Name ProdName, "
		    + "u.Name UomName, EXME_PedidoBaseDet.MovementQty, EXME_PedidoBaseDet.Description "
		    + "FROM EXME_PedidoBaseDet "
		    + "INNER JOIN M_Product p ON EXME_PedidoBaseDet.M_Product_ID = p.M_Product_ID "
		    + "INNER JOIN C_Uom u ON p.C_Uom_ID = u.C_Uom_ID "
		    + "WHERE EXME_PedidoBaseDet.EXME_PedidoBase_ID = ? "
		    + "AND EXME_PedidoBaseDet.IsActive = 'Y' AND p.IsActive = 'Y' "
		    + "AND u.IsActive = 'Y' ";
		    sql = MEXMELookupInfo.addAccessLevelSQL(ctx, sql, "EXME_PedidoBaseDet");
		    sql+=" ORDER BY EXME_PedidoBaseDet.Line";
		  
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			pstmt = DB.prepareStatement(sql, null);
			pstmt.setInt(1, baseID);
			rs = pstmt.executeQuery();

			while (rs.next()){
				MovementLine linea = new MovementLine();
				linea.setLine(rs.getLong("Line"));
				linea.setProductID(rs.getLong("M_Product_ID"));
				linea.setValue(rs.getString("Value"));
				linea.setProdName(rs.getString("ProdName"));
				linea.setProdValue(rs.getString("Value"));//Se agrego ya que en la pantalla de solicitud, este es el valor que se muestra como el codigo del producto, eruiz
				linea.setUomName(rs.getString("UomName"));
				linea.setOriginalQty(rs.getLong("MovementQty"));
				linea.setDescription(rs.getString("Description"));
				lista.add(linea);

			}
			rs.close();
            pstmt.close();
            pstmt = null;
            rs = null;
		} catch (SQLException e) {
			s_log.log(Level.SEVERE, "getHistorial", e);
			throw new Exception(e.getMessage());
		} finally {
            try {
                if (rs != null)
                    rs.close();

                if (pstmt != null)
                    pstmt.close();
            } catch (SQLException e) {
                s_log.log(Level.SEVERE, "getHistorial.close ", e);
                throw e;
            }

            rs = null;
            pstmt = null;
        }

		return lista;

	}
	
	/**
	 * 
	 * @param ctx
	 * @param baseID
	 * @param trxName
	 * @return
	 * @throws Exception
	 */
	public static List<LabelValueBean> getPedidosByMedico(Properties ctx, int medicoID, String trxName){

		List<LabelValueBean> lista = new ArrayList<LabelValueBean>();

		StringBuilder sql = new StringBuilder();
		
		sql.append("select * from exme_pedidobase where exme_medico_id =  ? or exme_medico_id is null ");
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", MPedidoBase.Table_Name));
		    
		  
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setInt(1, medicoID);
			rs = pstmt.executeQuery();

			while (rs.next()){
				lista.add(new LabelValueBean(rs.getString("description"), String.valueOf(rs.getInt("exme_pedidobase_id"))));

			}
			rs.close();
            pstmt.close();
            pstmt = null;
            rs = null;
		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
		}

		return lista;

	}
	
	public static  List<MEXMEPedidoBase> getDescMedico(Properties ctx, int id){
		
		List<MEXMEPedidoBase> lista = new ArrayList<MEXMEPedidoBase>();
		StringBuilder sql = new StringBuilder();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		sql.append("select * ");
		sql.append(" from exme_pedidobase ");
		
		if(id != 0){
			sql.append(" where exme_pedidobase.exme_pedidobase_id = ?");	
		}else{
			sql.append(" where 1 = 1 ");
		}
		
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", MPedidoBase.Table_Name));
		
		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			if(id != 0){
				pstmt.setInt(1, id);
			}
			rs = pstmt.executeQuery();
			
			while (rs.next()){
				MEXMEPedidoBase obj = new MEXMEPedidoBase(ctx, rs, null);
				lista.add(obj);
			}
			rs.close();
            pstmt.close();
            pstmt = null;
            rs = null;
			
		} catch (SQLException e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
		}
		
		return lista;
		
	}
	
	/**
	 * 
	 * @param ctx
	 * @param baseID
	 * @param trxName
	 * @return
	 * @throws Exception
	 */
	public static List<LabelValueBean> getPedidos(Properties ctx, String trxName){

		List<LabelValueBean> lista = new ArrayList<LabelValueBean>();

		StringBuilder sql = new StringBuilder();
		
		sql.append("select * from exme_pedidobase where isactive = 'Y' ");
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", MPedidoBase.Table_Name));
		    
		  
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			pstmt = DB.prepareStatement(sql.toString(), null);
			rs = pstmt.executeQuery();

			while (rs.next()){
				lista.add(new LabelValueBean(rs.getString("name"), String.valueOf(rs.getInt("exme_pedidobase_id"))));

			}
			rs.close();
            pstmt.close();
            pstmt = null;
            rs = null;
		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
		}
		return lista;
	}
	
}
