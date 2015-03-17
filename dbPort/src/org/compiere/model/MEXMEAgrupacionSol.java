package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.compiere.util.Constantes;
import org.compiere.util.DB;

public class MEXMEAgrupacionSol extends X_EXME_AgrupacionSol {
	
	private static final long	serialVersionUID	= 1L;


	public MEXMEAgrupacionSol(Properties ctx, int EXME_AgrupacionSol_ID, String trxName) {
		super(ctx, EXME_AgrupacionSol_ID, trxName);

	}

	public MEXMEAgrupacionSol(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}
	
	public static List<MTipoProd> getLstProductTypeByWarehouse( Properties ctx, int LocatorID, String trxName) throws Exception{
		
		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		final List<MTipoProd> lstTipoProducto= new ArrayList<MTipoProd>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			if (ctx == null) {
				return null;
			}

			sql.append("select * from exme_agrupacionsol WHERE isActive='Y' ");
			sql.append(" AND exme_agrupacionsol.m_warehouse_id=?");
			sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name));

			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, LocatorID);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				MTipoProd tipoProducto =new MTipoProd(ctx,rs.getInt("exme_tipoprod_id"),trxName);
				lstTipoProducto.add(tipoProducto);
			
			}
		} catch (Exception e) { 
			throw e;
		} finally {
			DB.close(rs, pstmt);
		    rs = null;
			pstmt = null; 
		}
		return lstTipoProducto; 
	}
}
