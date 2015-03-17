/*
 * Created on 25-may-2005
 *
 */
package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.logging.Level;

import org.apache.struts.util.LabelValueBean;
import org.compiere.model.bpm.BeanView;
import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;

/**
 * <b>Fecha:</b> 25/Mayo/2005<p>
 * <b>Modificado: </b> $Author: hassan $<p>
 * <b>En :</b> $Date: 2005/06/01 00:07:31 $<p>
 *
 * @author hassan reyes
 * @version $Revision: 1.3 $
 */
public class MEXMEArea extends X_EXME_Area{
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**	Static Logger				*/
	private static CLogger		s_log = CLogger.getCLogger (MEXMEArea.class);
	
	/**
     * @param ctx
     * @param EXME_Area_ID
     * @param trxName
     */
    public MEXMEArea(Properties ctx, int EXME_Area_ID, String trxName) {
        super(ctx, EXME_Area_ID, trxName);
    }

    /**
     * @param ctx
     * @param rs
     * @param trxName
     */
    public MEXMEArea(Properties ctx, ResultSet rs, String trxName) {
        super(ctx, rs, trxName);
    }

    /**
     * 
     * @param ctx
     * @param tipoArea
     * @param trxName
     * @return
     * @deprecated utilizado solo para WMaintenanceDiscount para convertirse en LabelValueBean
     * use {@link #getAreas(Properties, String, String)} directamente en el Combobox
     */
    public static MEXMEArea[] get(final Properties ctx, final String tipoArea, final String trxName){
        final List<MEXMEArea> list = getAreas(ctx, trxName, tipoArea);
		//
        final MEXMEArea[] areas = new MEXMEArea[list.size()];
		list.toArray(areas);
		return areas;
        
    }
    
    /**
     * //Metodo para llevar las areas
     * //jochoa P Reporte de Nutricion
     * @param ctx
     * @param trxName
     * @param tipoArea
     * @return
     */
    public static List<MEXMEArea> getAreas(Properties ctx, String trxName, String tipoArea){
//		final ArrayList<MEXMEArea> list = new ArrayList<MEXMEArea>();
//		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
//		
//		sql.append(" SELECT * FROM EXME_Area WHERE isActive = 'Y' ");
//		if (tipoArea != null) {
//			sql.append(" AND TIPOAREA = ? ");
//		}
//		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name));
//		sql.append(" ORDER BY Name");
//
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		try {
//			pstmt = DB.prepareStatement(sql.toString(), trxName);
//			if (tipoArea != null) {
//				pstmt.setString(1, tipoArea);
//			}
//			rs = pstmt.executeQuery();
//			while (rs.next()) {
//				final MEXMEArea area = new MEXMEArea(ctx, rs, trxName);
//				list.add(area);
//			}
//
//		} catch (Exception e) {
//			s_log.log(Level.SEVERE, "get", e);
//		} finally {
//			DB.close(rs, pstmt);
//		}
		return new Query(ctx, Table_Name, tipoArea == null ? "" : " TIPOAREA=? ", null)//
		.setParameters(tipoArea == null ? (List<Object>)null : tipoArea)//
		.addAccessLevelSQL(true)//
		.setOrderBy(" Name ")//
		.setOnlyActiveRecords(true)//
		.list();

    }
    
    /**
     * Obtiena la Lista de Areas
     * @param ctx
     * @return Label Value Bean
     * @deprecated use {@link KeyNamePair} NO UTILIZADO
     */
	public static List<LabelValueBean> getAreasLabel(Properties ctx, String trx, String tipoArea){
    	List<LabelValueBean> temp = new ArrayList<LabelValueBean>();
    	List<MEXMEArea> lista = getAreas(ctx, trx, tipoArea);
		temp.add(new LabelValueBean("","0"));
    	for(int i = 0; i < lista.size(); i++){
    		MEXMEArea area = (MEXMEArea)lista.get(i);
			temp.add(new LabelValueBean(area.getName(),String.valueOf(area.getEXME_Area_ID())));
    	}
    	return temp;
    }
    
    /**
     * Obtenemos el area por cuenta paciente activa del paciente
     * @param ctx El contexto de la aplicacion
     * @param EXME_Paciente_ID El paciente
     * @param trxName el nombre de la transaccion 
     * @return La cama de un paciente 
     */
	public static MEXMEArea getByCtaPac(Properties ctx, int EXME_Paciente_ID, String trxName) {
    	MEXMEArea clas = null;

    	StringBuilder sql = new StringBuilder("Select * ")
    	.append(" from exme_area area inner join exme_estserv serv inner join exme_ctapac cta on cta.exme_estserv_id = serv.exme_estserv_id ")
    	.append(" on serv.exme_area_id = area.exme_area_id ")
    	.append(" where cta.EncounterStatus = '")
    	//Ren. EncounterStatus Estatus (A= Admission, C= Discharge)
    	.append(MEXMECtaPac.ENCOUNTERSTATUS_Admission)
    	.append("' ")
    	.append(" and exme_paciente_id = ? ")
    	.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", MEXMEArea.Table_ID, "area"));

    	PreparedStatement pstmt = null;
    	ResultSet rs = null;

        try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, EXME_Paciente_ID);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				clas = new MEXMEArea(ctx, rs, trxName);
			}

		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs,pstmt);
		}
        
        return clas;
    }
    
    /** Merge Hrae 15384 MArea*/
    /**
     * Obtenemos las areas en una lista de tipo LabelValueBean
     * @param ctx
     * @param sql
     * @param params
     * @param trxName
     * @return
     * @deprecated use {@link KeyNamePair}
     */
	public static List<LabelValueBean> get(Properties ctx, String sql, List<Object> params, boolean blanco, String trxName) {
		List<LabelValueBean> clas = new ArrayList<LabelValueBean>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = DB.prepareStatement(sql, trxName);
			DB.setParameters(pstmt, params);
			rs = pstmt.executeQuery();
			if (blanco)
				clas.add(new LabelValueBean(" ", "0"));
			while (rs.next()) {
				clas.add(new LabelValueBean(rs.getString(1), String.valueOf(rs.getInt(2))));
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql, e);
		} finally {
			DB.close(rs, pstmt);
		}

		return clas;
	}
    
    /**
     * 
     * @param ctx
     * @param wherestr
     * @param trxName
     * @return
     */
	public static List<MEXMEArea> getByEstServ(Properties ctx, String wherestr, String trxName) {
		List<MEXMEArea> clas = new ArrayList<MEXMEArea>();

		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT DISTINCT area.* ").append(" FROM EXME_Area area ")
			.append(" INNER JOIN EXME_EstServ serv ON area.EXME_Area_ID = serv.EXME_Area_ID ").append(" WHERE area.IsActive = 'Y' ")
			.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", MEXMEArea.Table_Name, "area")).append(wherestr);

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				clas.add(new MEXMEArea(ctx, rs, trxName));
			}

		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
		}

		return clas;
	}
    
	/**
	 * Obtiene areas plantilla
	 * 
	 * @param ctx
	 *            Contexto
	 * @param type
	 *            Tipo de de area
	 * @param trxName
	 *            Transaccion
	 * @return
	 */
	public static List<MEXMEArea> getTemplates(Properties ctx, String type, String trxName) {
		List<MEXMEArea> clas = new ArrayList<MEXMEArea>();

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ");
		sql.append("  a.* ");
		sql.append("FROM ");
		sql.append("  EXME_Area a ");
		sql.append("  INNER JOIN EXME_AreaConfigurador d ");
		sql.append("  ON a.EXME_Area_ID = d.EXME_Area_ID ");
		sql.append("WHERE ");
		sql.append("  a.isActive = 'Y' AND ");
		sql.append("  a.isTemplate = 'Y' AND ");
		sql.append("  d.tipoarea = ? AND ");
		sql.append("  a.AD_Client_ID = 0 AND ");
		sql.append("  a.AD_Org_ID = 0 ");
		sql.append("ORDER BY ");
		sql.append("  a.name ");

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setString(1, type);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				clas.add(new MEXMEArea(ctx, rs, trxName));
			}

		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
		}

		return clas;
	}
	
	
	public static List<MEXMEArea> getRoleAreas(Properties ctx, String whereClause, String trxName, Object... params) {

		final ArrayList<MEXMEArea> list = new ArrayList<MEXMEArea>();
		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);

		sql.append("SELECT DISTINCT EXME_Area.* ");
		sql.append("FROM EXME_Area ");
		sql.append("INNER JOIN EXME_EstServ est ON (EXME_Area.EXME_Area_ID = est.EXME_Area_ID AND est.isActive = 'Y') ");
		sql.append("INNER JOIN EXME_RoleEstServ res ON ( ");
		sql.append("est.EXME_EstServ_ID = res.EXME_EstServ_ID ");
		sql.append("AND res.AD_Role_ID = ? ");
		sql.append("AND res.isActive = 'Y' ");
		sql.append(") ");
		sql.append("WHERE EXME_Area.IsActive = 'Y' ");
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name));
		sql.append("ORDER BY EXME_Area.Name ");

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			int index = 1;
			pstmt.setInt(index++, Env.getAD_Role_ID(ctx));
			for (Object param : params) {
				DB.setParameter(pstmt, index++, param);
			}
			rs = pstmt.executeQuery();
			while (rs.next()) {
				final MEXMEArea area = new MEXMEArea(ctx, rs, trxName);
				list.add(area);
			}

		} catch (Exception e) {
			s_log.log(Level.SEVERE, "get", e);
		} finally {
			DB.close(rs, pstmt);
		}
		return list;
	}
    
	/**
	 * Areas por Tipos de paciente
	 * @param ctx
	 * @param tipoPac
	 * @param trxName
	 * @return
	 */
	public static List<KeyNamePair> getAreasTipoPac(Properties ctx, int tipoPac, String trxName) {
		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append(" SELECT EXME_Area.EXME_Area_ID, EXME_Area.Name ");
		sql.append(" FROM EXME_TipoPacArea TPA ");
		sql.append(" INNER JOIN EXME_Area EXME_Area  ON         TPA.EXME_Area_ID = EXME_Area.EXME_Area_ID");
		sql.append(" INNER JOIN EXME_TipoPaciente tp ON TPA.EXME_TipoPaciente_ID = tp.EXME_TipoPaciente_ID");
		sql.append(" WHERE TPA.IsActive = 'Y'");
		sql.append(" AND EXME_Area.IsActive = 'Y' ");
		sql.append(" AND tp.IsActive = 'Y' ");
		sql.append(" AND TPA.EXME_TipoPaciente_ID = ? ");
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name));
		return Query.getListKN(sql.toString(), trxName, tipoPac);
	}

	/**
	 * Areas por Tipos de paciente
	 * @param ctx
	 * @param tipoPac
	 * @param trxName
	 * @return
	 * @deprecated No se utiliza y no tiene comentario
	 */
	public static List<LabelValueBean> getAreasTipoPacEstacion(Properties ctx, int tipoPac, String trxName){

		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		final List<LabelValueBean> resultados = new ArrayList<LabelValueBean>();

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			sql.append("SELECT area.Name, es.Name, area.EXME_Area_ID, COALESCE(es.EXME_EstServ_ID,0)  ");
			sql.append("FROM EXME_TipoPacArea TPA ");
			sql.append("INNER JOIN EXME_Area area ON (TPA.EXME_Area_ID=area.EXME_Area_ID AND area.IsActive='Y') ");
			sql.append("INNER JOIN EXME_TipoPaciente tp ON (TPA.EXME_TipoPaciente_ID=tp.EXME_TipoPaciente_ID ");
			sql.append("  AND tp.IsActive='Y' AND tp.EXME_TipoPaciente_ID=?) ");
			sql.append("LEFT JOIN EXME_EstServ es ON (area.EXME_Area_ID=es.EXME_Area_ID AND es.IsActive='Y') ");
			sql.append("WHERE TPA.IsActive='Y' ");
			sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name, "area"));

			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setInt(1, tipoPac);
			rs = pstmt.executeQuery();
			
			final HashMap<Integer, List<BeanView>> mapa = new HashMap<Integer, List<BeanView>>();
			while (rs.next()) {
				final List<BeanView> beans;
				final int areaID = rs.getInt(3);
				final BeanView bean = new BeanView(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4));
				if (mapa.containsKey(areaID)) {
					beans = mapa.get(areaID);
				} else {
					beans = new ArrayList<BeanView>();
					mapa.put(areaID, beans);
				}
				beans.add(bean);
			}
			
			for (final Entry<Integer, List<BeanView>> lineas : mapa.entrySet()) {
				final List<BeanView> lista = lineas.getValue();
				if (lista != null) {
					for (final BeanView bean : lista) {
						final StringBuilder label = new StringBuilder();
						label.append(bean.getCadena1());
						if (lista.size() > 1 && bean.getCadena2() != null) {
							label.append(" ");
							label.append(bean.getCadena2());
						}

						final StringBuilder value = new StringBuilder();
						value.append(bean.getCadena3());
						value.append("|");
						value.append(bean.getCadena4());

						resultados.add(new LabelValueBean(label.toString(), value.toString()));
					}
				}
			}
			
		} catch (Exception e) {
			s_log.log(Level.SEVERE, e.getLocalizedMessage(), e);
		} finally {
			DB.close(rs, pstmt);
		}
		return resultados;
	}
	
	/**
	 * 
	 * @param ctx
	 * @param date
	 * @param ctapacID
	 * @return
	 */
	public static boolean hasFrequency3(Properties ctx, Timestamp date, int ctapacID){
		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
		sql.append("select TO_CHAR(f3.hour, 'hh24')  FROM exme_frequency3 f3  ")
		.append(" inner join exme_frequency2 f2 on f2.exme_frequency2_id = f3.exme_frequency2_id ")
		.append(" inner join exme_area area on area.exme_frequency2_id = f2.exme_frequency2_id ")
		.append(" inner join exme_ctapac cta on cta.exme_area_id = area.exme_area_id ")
		.append(" where cta.exme_ctapac_id = ? ")
		.append(" and to_char(f3.hour, 'hh24') = ?");
		
//		try {
//			pstmt = DB.prepareStatement(sql.toString(), null);
//			pstmt.setInt(1, ctapacID);
			final Calendar calFin = Calendar.getInstance();
			calFin.setTime(date);			
//			pstmt.setString(2, Constantes.getDosDigitos().format(calFin.get(Calendar.HOUR_OF_DAY)));
//
//			rs = pstmt.executeQuery();
//			if (rs.next()) {
//				return true;
//			}
//			
//		} catch (Exception e) {
//			s_log.log(Level.SEVERE, "hasFrequency3", e);
//		} finally {
//			DB.close(rs, pstmt);
//		}
		return DB.getSQLValue(null, sql.toString(), ctapacID, Constantes.getDosDigitos().format(calFin.get(Calendar.HOUR_OF_DAY))) >= 0;
	}
	
	/**
	 * Consulta de Area relacionadas a un tipo de paciente
	 * @param ctx
	 * @param tipoAreas
	 * @return
	 */
	public static List<KeyNamePair> getAreas(Properties ctx){
		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append(" SELECT EXME_Area.EXME_Area_ID, EXME_Area.Name ");
		sql.append(" FROM   EXME_Area  ");
		sql.append(" INNER JOIN EXME_TipoPacArea tpa ON EXME_Area.EXME_Area_ID=tpa.EXME_Area_ID ");
		sql.append(" WHERE EXME_Area.IsActive='Y' AND tpa.IsActive='Y' ");
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name));
		sql.append(" GROUP BY EXME_Area.Name, EXME_Area.EXME_Area_ID ");
		sql.append(" ORDER BY EXME_Area.Name ");
		
		return Query.getListKN(sql.toString(), null);
	}
}
