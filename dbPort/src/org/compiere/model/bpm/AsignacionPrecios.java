package org.compiere.model.bpm;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.model.MCtaPacDet;
import org.compiere.model.MEXMEActPacienteInd;
import org.compiere.model.MEXMELookupInfo;
import org.compiere.util.CLogger;
import org.compiere.util.DB;

public class AsignacionPrecios {

	/** Logger */
	protected static CLogger log = CLogger.getCLogger(AsignacionPrecios.class);

	/**
	 * Obtenemos los cargos a procesar por nivel de acceso
	 */
	public static HashMap<Integer, List<MCtaPacDet>> getLstDetalle(
			Properties ctx, int pEXMECtaPacExtID) {
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT cpd.*, ")
		   .append("p.Name as ProdName, ")
		   .append("p.Description as Description2, ")
		   .append("p.Value as ProdValue, ")
		   .append("rv.Value as RevCode, ")
		   .append("rv.name as RevDesc ")
		   .append("FROM EXME_CtaPacDet cpd ")
		   .append("INNER JOIN M_Product p ")
		   .append("ON p.M_Product_ID = cpd.M_Product_ID ")
		   .append("LEFT JOIN EXME_RevenueCode rv ")
		   .append("ON rv.EXME_RevenueCode_ID = cpd.EXME_RevenueCode_ID ")
		   .append("WHERE cpd.EXME_CtaPacExt_ID = ? ")
		   .append("AND cpd.CalcularPrecio = 'N' ")
		   .append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ",
								MCtaPacDet.Table_Name, "cpd"))
		   .append(" ORDER BY  p.Name, p.Description ");

		HashMap<Integer, List<MCtaPacDet>> mapa = new HashMap<Integer, List<MCtaPacDet>>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			pstmt = DB.prepareStatement(sql.toString(), null);
			if (pEXMECtaPacExtID > 0) {
				pstmt.setInt(1, pEXMECtaPacExtID);
			}
			rs = pstmt.executeQuery();

			while (rs.next()) {
				BeanView bean = new BeanView();
				bean.setCadena1(rs.getString("ProdName"));
				bean.setInteger1(rs.getInt("M_Product_ID"));
				bean.setCadena2(rs.getString("Description2"));
				bean.setCadena3(rs.getString("ProdValue"));
				bean.setCadena4(rs.getString("RevCode"));

				if (mapa.containsKey(rs.getInt("M_Product_ID"))) {
					
					List<MCtaPacDet> list = mapa.get(rs
							.getInt("M_Product_ID"));
					MCtaPacDet modelo = new MCtaPacDet(ctx,
							rs, null);
					modelo.setBeanID(bean);
					list.add(modelo);
					//Se coloca el contador de cargos en la posicion inicial
					mapa.get(rs.getInt("M_Product_ID")).get(0)
					.getBeanID().setInteger2(
											(mapa.get(rs.getInt("M_Product_ID")).get(0)
													.getBeanID().getInteger2())+1);
				} else {
					List<MCtaPacDet> list = new ArrayList<MCtaPacDet>();
					MCtaPacDet modelo = new MCtaPacDet(ctx,
							rs, null);
					modelo.setBeanID(bean);
					bean.setInteger2(1);
					list.add(0, modelo);
					mapa.put(rs.getInt("M_Product_ID"), list);
				}

			}

		} catch (Exception e) {
			log.log(Level.SEVERE, "MEstServ.get", e);
		} finally {
			DB.close(rs, pstmt);
		}
		return mapa;
	}

	/**
	 * Asigna el precio de lista a aquellos expedientes que no lo tengan
	 * 
	 * @param ctx
	 * @param pEXMECtaPacID
	 * @param pMPriceListVersionID
	 * @return
	 */
	public static HashMap<Boolean, List<MEXMEActPacienteInd>> asignarPrecioDeLista(
			Properties ctx, int pEXMECtaPacID, int pMPriceListVersionID,
			String trxName) {
		StringBuilder sql = new StringBuilder();
		sql
				.append(" SELECT ap.*, ")
				.append("   pp.priceList AS priceList2, ")
				.append("   pp.priceStd AS priceActual2, ")
				.append("   pp.priceLimit AS priceLimit2 ")
				.append(" FROM EXME_CargosAGenerar_V cgv ")
				.append("   INNER JOIN M_ProductPrice pp      ")
				.append("     ON cgv.M_Product_ID = pp.M_Product_ID ")
				.append("       AND pp.m_pricelist_version_ID = ? ")
				.append("       AND pp.PriceList > 0 ")
				.append("   INNER JOIN EXME_ActPacienteInd ap ")
				.append("     ON cgv.EXME_ActPacienteInd_ID = ap.EXME_ActPacienteInd_ID ")
				.append(" WHERE cgv.PriceActual is null ")
				.append("   OR cgv.PriceActual<=0 ")
				.append(
						MEXMELookupInfo.addAccessLevelSQL(ctx, " ",
								"EXME_CargosAGenerar_V", "cgv"));

		if (pEXMECtaPacID > 0) {
			sql.append(" AND cgv.EXME_CtaPac_ID = ? ");
		}

		HashMap<Boolean, List<MEXMEActPacienteInd>> mapa = new HashMap<Boolean, List<MEXMEActPacienteInd>>();
		List<MEXMEActPacienteInd> lstExito = new ArrayList<MEXMEActPacienteInd>();
		List<MEXMEActPacienteInd> lstError = new ArrayList<MEXMEActPacienteInd>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, pMPriceListVersionID);
			if (pEXMECtaPacID > 0) {
				pstmt.setInt(2, pEXMECtaPacID);
			}
			rs = pstmt.executeQuery();

			while (rs.next()) {
				MEXMEActPacienteInd modelo = new MEXMEActPacienteInd(ctx, rs,
						null);
				modelo.setPriceActual(rs.getBigDecimal("PriceActual2"));
				modelo.setPriceList(rs.getBigDecimal("PriceList2"));
				modelo.setPriceLimit(rs.getBigDecimal("PriceLimit2"));
				if (modelo.save(trxName)) {
					lstExito.add(modelo); 
				} else {
					lstError.add(modelo);
					log.log(Level.FINEST,
							"asignarPrecioDeLista: EXME_ActPacienteInd_ID>"
									+ modelo.getEXME_ActPacienteInd_ID());
				}
			}
			
			mapa.put(true, lstExito);
			mapa.put(false, lstError);
		} catch (Exception e) {
			log.log(Level.SEVERE, "MEstServ.get", e);
		} finally {
			DB.close(rs, pstmt);
		}
		return mapa;
	}
	
	
	/**
	 * Obtenemos los cargos a procesar por nivel de acceso
	 */
	public static int getNumDetalle(Properties ctx) {
		StringBuilder sql = new StringBuilder();
		sql
				.append(" SELECT api.*, ")
				.append(" 	p.Name as ProdName, p.Description as Description2 ")
				.append(" FROM EXME_CargosAGenerar_V cgv ")
				.append(" 	INNER JOIN M_Product p ")
				.append("   	ON p.M_Product_ID = cgv.M_Product_ID ")
				.append(" 	INNER JOIN EXME_ActPacienteInd api ")
				.append("   	ON cgv.EXME_ActPacienteInd_ID = api.EXME_ActPacienteInd_ID ")
				.append(" WHERE cgv.PriceActual is  null ")
				.append("   OR cgv.PriceActual<=0 ")
				.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ",
								"EXME_CargosAGenerar_V", "cgv"));

		sql.append(" ORDER BY  p.Name, p.Description ");

		HashMap<Integer, List<MEXMEActPacienteInd>> mapa = new HashMap<Integer, List<MEXMEActPacienteInd>>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			pstmt = DB.prepareStatement(sql.toString(), null);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				BeanView bean = new BeanView();
				bean.setCadena1(rs.getString("ProdName"));
				bean.setInteger1(rs.getInt("M_Product_ID"));
				bean.setCadena2(rs.getString("Description2"));

				if (mapa.containsKey(rs.getInt("M_Product_ID"))) {
					List<MEXMEActPacienteInd> list = mapa.get(rs
							.getInt("M_Product_ID"));
					MEXMEActPacienteInd modelo = new MEXMEActPacienteInd(ctx,
							rs, null);
					modelo.setBeanID(bean);
					list.add(modelo);
				} else {
					List<MEXMEActPacienteInd> list = new ArrayList<MEXMEActPacienteInd>();
					MEXMEActPacienteInd modelo = new MEXMEActPacienteInd(ctx,
							rs, null);
					modelo.setBeanID(bean);
					list.add(modelo);
					mapa.put(rs.getInt("M_Product_ID"), list);
				}

			}

		} catch (Exception e) {
			log.log(Level.SEVERE, "MEstServ.get", e);
		} finally {
			DB.close(rs, pstmt);
		}
		return mapa.entrySet().size();
	}

}
