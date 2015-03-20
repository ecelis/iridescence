/**
 * 
 */
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
import org.compiere.util.Constantes;
import org.compiere.util.DB;

/**
 * Numeros de serie para productos
 * segun la configuraci�n M_Product.isNumSerie = 'Y'
 * 
 * @author LLama
 *
 */
public class MEXMENumSerie extends X_EXME_NumSerie {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * @param ctx
     * @param EXME_NumSerie_ID
     * @param trxName
     */
    public MEXMENumSerie(Properties ctx, int EXME_NumSerie_ID, String trxName) {
        super(ctx, EXME_NumSerie_ID, trxName);
        
    }

    /**
     * @param ctx
     * @param rs
     * @param trxName
     */
    public MEXMENumSerie(Properties ctx, ResultSet rs, String trxName) {
        super(ctx, rs, trxName);
        
    }
    
    /** Static logger           */
    private static CLogger s_log = CLogger.getCLogger(MEXMENumSerie.class);
    
    /** Closed = CL */
    public static final String DOCSTATUS_DevolProd = DOCSTATUS_Closed; 
    /** Completed = CO */
    public static final String DOCSTATUS_Aplicado = DOCSTATUS_Completed;
    /** Drafted = DR */
    public static final String DOCSTATUS_Surtido = DOCSTATUS_Drafted;
    /** Voided = VO */
    public static final String DOCSTATUS_DevolCargo = DOCSTATUS_Voided;

    /**
     * Valida un determinado numero de serie para un producto.
     * 
     * @param ctx
     * @param numSerie
     * @param M_Product_ID
     * @param trxName
     * @return true/false segpun si el Numero es o no v�lido
     * @throws SQLException
     */
    public static boolean numSerieValido(Properties ctx, String numSerie, int M_Product_ID,
            String trxName) throws SQLException {

    	StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            sql.append("SELECT numserie FROM EXME_NumSerie ")
                //agregamos el cliente y org
                .append(MEXMELookupInfo.addAccessLevelSQL(ctx," WHERE ", "EXME_NumSerie"))
                .append(" AND isActive = 'Y' ")
                .append(" AND numSerie = ? AND M_Product_ID = ? ");

            pstmt = DB.prepareStatement(sql.toString(), trxName);
            pstmt.setString(1, numSerie);
            pstmt.setInt(2, M_Product_ID);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                return false;
            }

        } catch (SQLException e) {
            s_log.log(Level.SEVERE, "numSerieValido (" + sql + ")", e);
            throw e;
        } finally {
        	DB.close(rs,pstmt);
			pstmt = null;
			rs =null;
        }

        return true;
    }
    
   
    /**
     * Lista de numeros de serie disponibles para devolver el cargo
     * deben de tener estatus de aplicado
     * 
     * @param ctx
     * @param ctaPacID
     * @param productoID
     * @param trxName
     * @return Un valor List<LabelValueBean> de los numero de serie
     * @throws Exception
     */
    public static List<LabelValueBean> getNumerosDeSerie(Properties ctx, int ctaPacID, int productoID,
            String trxName) throws Exception {
        
    	StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<LabelValueBean> numSerie = new ArrayList<LabelValueBean>(); 
        
        try {
            sql.append("SELECT numSerie, EXME_NumSerie_ID FROM EXME_NumSerie ")
                .append(MEXMELookupInfo.addAccessLevelSQL(ctx, " WHERE ", "EXME_NumSerie"))
                .append(" AND isActive = 'Y' ")
                .append(" AND docstatus = '").append(DOCSTATUS_Aplicado).append("' ")
                .append(" AND EXME_CtaPac_ID = ? AND M_Product_ID = ? ")
                .append(" ORDER BY numSerie ");
            
            pstmt = DB.prepareStatement(sql.toString(), null);
            pstmt.setInt(1, ctaPacID);
            pstmt.setInt(2, productoID);
            rs = pstmt.executeQuery();
            
            while(rs.next()) {
                LabelValueBean opc = new LabelValueBean(rs.getString(1),String.valueOf(rs.getInt(2)));
                numSerie.add(opc);
            }
            
        } catch (SQLException e) {
            s_log.log(Level.SEVERE, "getNumerosDeSerie (" + sql + ")", e);
            throw new Exception(e.getMessage());
        } finally {
            sql= null;
            DB.close(rs,pstmt);
			pstmt = null;
			rs =null;
        }
        
        return numSerie;
    }
    
    /**
     * Lista de numeros de serie disponibles para devolver el producto
     * deben tener estatus de cargodevuelto
     * 
     * @param ctx
     * @param productoID
     * @param locatorToID
     * @param locatorID
     * @param trxName
     * @return List<LabelValueBean> de los numero de serie
     * @throws Exception
     */
    public static List<LabelValueBean> getNumSerieDevolver(Properties ctx, int productoID, int almacenToID,
            int almacenID, String trxName) throws Exception {
        
    	StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<LabelValueBean> numSerie = new ArrayList<LabelValueBean>(); 
        
        try {
            sql.append("SELECT EXME_NumSerie.numSerie, EXME_NumSerie.EXME_NumSerie_ID ")
                .append(" FROM EXME_NumSerie ")
                .append(" INNER JOIN M_MovementLine ON (EXME_NumSerie.M_MovementLine_ID = ")
                .append(" M_MovementLine.M_MovementLine_ID AND M_MovementLine.isActive = 'Y') ")
                .append(" INNER JOIN M_Locator M_LocatorTo ON (M_MovementLine.M_LocatorTo_ID = ")
                .append(" M_LocatorTo.M_Locator_ID AND M_LocatorTo.isActive = 'Y') ")
                .append(" INNER JOIN M_Locator M_Locator ON (M_MovementLine.M_Locator_ID = ")
                .append(" M_Locator.M_Locator_ID AND M_Locator.isActive = 'Y') ")
                .append(" WHERE EXME_NumSerie.docstatus = '").append(DOCSTATUS_DevolCargo).append("' ")
                .append(" AND EXME_NumSerie.isActive = 'Y' ")
                .append(" AND EXME_NumSerie.M_Product_ID = ? ")
                .append(" AND M_LocatorTo.M_Warehouse_ID = ? ") //solicita
                .append(" AND M_Locator.M_Warehouse_ID = ? ") //resurte
                .append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", "EXME_NumSerie"))//cliente y org
                .append(" ORDER BY numSerie");
            
            pstmt = DB.prepareStatement(sql.toString(), null);
            
            pstmt.setInt(1, productoID);
            pstmt.setInt(2, almacenToID);
            pstmt.setInt(3, almacenID);
            rs = pstmt.executeQuery();
            
            while(rs.next()) {
                LabelValueBean opc = new LabelValueBean(rs.getString(1),String.valueOf(rs.getInt(2)));
                numSerie.add(opc);
            }
            
        } catch (SQLException e) {
            s_log.log(Level.SEVERE, "getNumSerieDevolver (" + sql + ")", e);
            throw new Exception(e.getMessage());
        } finally {
            sql= null;
            DB.close(rs,pstmt);
			pstmt = null;
			rs =null;
        }
        
        return numSerie;
    }
    
    /**
     * Devuelve los cargos relacionados a una cuenta paciente.
     *
     * @param ctaPacID El id de la cuenta paciente
     *
     * @return Un valor List con el detalle de los cargos
     * @throws Exception en caso de ocurrir un error al
     * ejecutar la consulta.
     *
     */
    public static MEXMENumSerie getFromNumSerie(Properties ctx, String numSerie, int productoID,
            String trxName) throws Exception {
        
    	StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        MEXMENumSerie retValue = null; 

        try {
            sql.append(" SELECT EXME_NumSerie.*  ")
                .append(" FROM EXME_NumSerie  ")
                .append(MEXMELookupInfo.addAccessLevelSQL(ctx, " WHERE ", "EXME_NumSerie"))
                .append(" AND EXME_NumSerie.M_Product_ID = ? ")
                .append(" AND EXME_NumSerie.NumSerie = ? ")
                .append(" AND EXME_NumSerie.isActive = 'Y' ");
            
            pstmt = DB.prepareStatement(sql.toString(), null);
            pstmt.setInt(1, productoID);
            pstmt.setString(2, numSerie);
            rs = pstmt.executeQuery();
            
            if(rs.next()) {
                retValue = new MEXMENumSerie(ctx, rs, trxName);
            }
            
        } catch (SQLException e) {
            s_log.log(Level.SEVERE, "getFromNumSerie (" + sql + ")", e);
            throw new Exception(e.getMessage());
        } finally {
            sql= null;
            DB.close(rs,pstmt);
			pstmt = null;
			rs =null;
        }
        
        return retValue;
    }
}
