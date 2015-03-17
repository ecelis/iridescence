package org.compiere.model;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.apache.commons.lang.StringUtils;
import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.Env;

public class MEXMEEsqInsulinaDet extends X_EXME_EsqInsulinaDet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4865888311274334814L;
	private static CLogger	s_log = CLogger.getCLogger (MEXMEEsqInsulinaDet.class);

	public MEXMEEsqInsulinaDet(Properties ctx, int EXME_EsqInsulinaDet_ID, String trxName) {
		super(ctx, EXME_EsqInsulinaDet_ID, trxName);
	}
	
	
	public MEXMEEsqInsulinaDet(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	/**
	 * 
	 * @param ctx
	 * @param ctaPacID
	 * @param diarioEnfID
	 * @param trxName
	 * @return
	 */
	public static List<MEXMEEsqInsulinaDet> get(Properties ctx, int ctaPacID, int diarioEnfID, String trxName) {
		List<MEXMEEsqInsulinaDet> lista = new ArrayList<MEXMEEsqInsulinaDet>();
		final List<Object> params = new ArrayList<Object>();
		final StringBuilder sql = new StringBuilder();
		try {
			if (ctaPacID > 0) {
				sql.append(" EXME_EsqInsulinaDet.EXME_CtaPac_ID=? ");
				params.add(ctaPacID);
			}
			if (diarioEnfID > 0) {
				params.add(diarioEnfID);
				if (sql.length() > 0) {
					sql.append(Constantes.SQL_AND);
					sql.append(" (");
				}
				sql.append(" EXME_EsqInsulinaDet.EXME_DiarioEnf_ID=? ");
				if (Env.getEXME_Enfermeria_ID(ctx) > 0) {
					params.add(Env.getEXME_Enfermeria_ID(ctx));
					sql.append(" OR EXME_Enfermeria_ID=? ");
				}
				sql.append(") ");
			}

			lista = new Query(ctx, Table_Name, sql.toString(), trxName)//
					.setParameters(params)//
					.addAccessLevelSQL(true)//
					.setOnlyActiveRecords(true)//
					.setOrderBy("EXME_EsqInsulinaDet.FechaAplica Desc")//
					.list();
		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString() + " " + params.toString(), e);
		}

		return lista;
	}
	
	
	public String getTipoStr() {
		return MRefList.getListName(getCtx(), TIPO_AD_Reference_ID, StringUtils.trim(getTipo()));
	}
	
	public String getProductName() {
		return getM_Product().getName();
	}
	
	
	/**
	 * Regresa la historia de los registros de insulina
	 * @param ctx                Propiedades
	 * @param trxName            Nombre de la transaccion
	 * @deprecated
	 *
    public static List<MEsqInsulinaDet_VO> getHistoria(Properties ctx, int ctapacID, int diarioEnfID, String trxName)
    {
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		List<MEsqInsulinaDet_VO> lista = new ArrayList<MEsqInsulinaDet_VO>();
		
		try{
			
			 sql.append("SELECT DET.EXME_ESQINSULINADET_ID, DET.FECHAAPLICA, DET.TIPO, DET.CANTIDAD,"); 
			 sql.append(" (ENF.NAME || ' ' || ENF.APELLIDO1 || ' ' || ENF.APELLIDO2) AS ENFERMERA, ");
			 sql.append(" PROD.NAME AS PRODUCTNAME, DET.DESCRIPTION, DET.ISACTIVE AS ESTATUS");
			 sql.append(" FROM EXME_ESQINSULINADET DET");
			 sql.append(" INNER JOIN EXME_ENFERMERIA ENF ON DET.EXME_ENFERMERIA_ID = ENF.EXME_ENFERMERIA_ID");
			 sql.append(" INNER JOIN M_PRODUCT PROD ON DET.M_PRODUCT_ID = PROD.M_PRODUCT_ID");
			 sql.append(" WHERE DET.EXME_CTAPAC_ID = ? ");
			 sql.append(" AND DET.EXME_DIARIOENF_ID = ? ");
			 sql.append(" ORDER BY DET.FECHAAPLICA");
			
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, ctapacID);
			pstmt.setInt(2, diarioEnfID);
			rs = pstmt.executeQuery();
			
			while (rs.next()){
				MEsqInsulinaDet_VO obj = new MEsqInsulinaDet_VO();
				obj.setInsulinaID(rs.getInt("EXME_ESQINSULINADET_ID"));
				obj.setFechaAplicacion(rs.getTimestamp("FECHAAPLICA"));
				obj.setTipo(rs.getString("TIPO"));
				obj.setCantidad(rs.getBigDecimal("CANTIDAD"));
				obj.setEnfermera(rs.getString("ENFERMERA"));
				obj.setDescripcion(rs.getString("DESCRIPTION"));
				obj.setProducto(rs.getString("PRODUCTNAME"));
				obj.setCancelado(rs.getString("ESTATUS"));
				lista.add(obj);
				
			}
			
		}
		catch(Exception e){
    		s_log.log(Level.SEVERE, sql.toString(), e);
		}
		finally{
			DB.close(rs,pstmt);
    		sql = null;
    		rs = null;
    		pstmt = null;
		}

		return lista;
    }*/
    
}
