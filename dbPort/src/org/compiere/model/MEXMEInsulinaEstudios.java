package org.compiere.model;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.Constantes;

public class MEXMEInsulinaEstudios extends X_EXME_InsulinaEstudios{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1351155112869688312L;
	private static CLogger s_log = CLogger.getCLogger(MEXMEInsulinaEstudios.class);
	
	public MEXMEInsulinaEstudios(Properties ctx, int EXME_InsulinaEstudios_ID,
			String trxName) {
		super(ctx, EXME_InsulinaEstudios_ID, trxName);
	}
	
	
	public MEXMEInsulinaEstudios(Properties ctx, ResultSet rs, String trxName) {
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
	public static List<MEXMEInsulinaEstudios> get(Properties ctx, int ctaPacID, int diarioEnfID, String trxName) {
		List<MEXMEInsulinaEstudios> lista = new ArrayList<MEXMEInsulinaEstudios>();
		final List<Object> params = new ArrayList<Object>();
		final StringBuilder sql = new StringBuilder();
		try {
			if (ctaPacID > 0) {
				sql.append(" EXME_InsulinaEstudios.EXME_CtaPac_ID=? ");
				params.add(ctaPacID);
			}
			if (diarioEnfID > 0) {
				params.add(diarioEnfID);
				if (sql.length() > 0) {
					sql.append(Constantes.SQL_AND);
				}
				sql.append(" EXME_InsulinaEstudios.EXME_DiarioEnf_ID=? ");
			}

			lista = new Query(ctx, Table_Name, sql.toString(), trxName)//
				.setParameters(params)//
				.addAccessLevelSQL(true)//
				.setOnlyActiveRecords(true)//
				.setOrderBy("EXME_InsulinaEstudios.FechaAplica Desc")//
				.list();
		}
		catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString() + " " + params.toString(), e);
		}

		return lista;
	}
	
	public String getEstudiosStr(){
		return getEXME_EstudiosDiabeticos().getName();
	}
	
	/**
	 * Regresa la historia de los resultados de los estudios
	 * @param ctx                Propiedades
	 * @param trxName            Nombre de la transaccion
	 * @deprecated
	 *
    public static List<MEsqInsulinaEstudios_VO> getHistoria(Properties ctx, int ctapacID, int diarioEnfID, String trxName)
    {
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		List<MEsqInsulinaEstudios_VO> lista = new ArrayList<MEsqInsulinaEstudios_VO>();
		
		try{
			
			 sql.append("SELECT INS.EXME_INSULINAESTUDIOS_ID, INS.FECHAAPLICA, INS.VALUE AS RESULTADO,"); 
			 sql.append(" (ENF.NAME || ' ' || ENF.APELLIDO1 || ' ' || ENF.APELLIDO2) AS ENFERMERA, ");
			 sql.append(" INS.DESCRIPTION, EST.NAME AS ESTUDIO, INS.ISACTIVE AS ESTATUS ");
			 sql.append(" FROM EXME_INSULINAESTUDIOS INS");
			 sql.append(" INNER JOIN EXME_ENFERMERIA ENF ON INS.EXME_ENFERMERIA_ID = ENF.EXME_ENFERMERIA_ID ");
			 sql.append(" INNER JOIN EXME_ESTUDIOSDIABETICOS EST ON INS.EXME_ESTUDIOSDIABETICOS_ID = EST.EXME_ESTUDIOSDIABETICOS_ID ");
			 sql.append(" WHERE INS.EXME_CTAPAC_ID = ?  ");
			 sql.append(" AND INS.EXME_DIARIOENF_ID = ? ");
			 sql.append(" ORDER BY INS.FECHAAPLICA, EST.NAME");
			
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, ctapacID);
			pstmt.setInt(2, diarioEnfID);
			rs = pstmt.executeQuery();
			
			while (rs.next()){
				MEsqInsulinaEstudios_VO obj = new MEsqInsulinaEstudios_VO();
				obj.setEstudioID(rs.getInt("EXME_INSULINAESTUDIOS_ID"));
				obj.setFechaAplicacion(rs.getTimestamp("FECHAAPLICA"));
				obj.setResultadoEstudio(rs.getString("RESULTADO"));
				obj.setEnfermera(rs.getString("ENFERMERA"));
				obj.setObservacionEstudio(rs.getString("DESCRIPTION"));
				obj.setEstudio(rs.getString("ESTUDIO"));
				obj.setCancelado(rs.getString("ESTATUS"));
				lista.add(obj);
				
			}
			
		}
		catch(Exception e){
    		s_log.log(Level.SEVERE, sql.toString(), e.getMessage());
		}
		finally{
			DB.close(rs, pstmt);
    		sql = null;
    		rs = null;
    		pstmt = null;
		}

		return lista;
    }*/
}
