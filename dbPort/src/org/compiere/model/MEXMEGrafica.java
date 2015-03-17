package org.compiere.model;

import java.awt.Color;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.apache.struts.util.LabelValueBean;
import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.CustomGraphicValues;
import org.compiere.util.DB;
import org.compiere.util.Utilerias;
import org.compiere.util.XYSeries;
import org.compiere.util.XYValues;

public class MEXMEGrafica extends X_EXME_Grafica {

	/**
	 * 
	 */
	private static final long serialVersionUID = 458790048437157260L;

	public MEXMEGrafica(Properties ctx, int EXME_Grafica_ID, String trxName) {
		super(ctx, EXME_Grafica_ID, trxName);
	}

	public MEXMEGrafica(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	private static CLogger  s_log = CLogger.getCLogger (MEXMEGrafica.class);

//	public static List<LabelValueBean> getGraphicsLVB(Properties ctx, String trxName, String sexo) {
//		final List<LabelValueBean> lista = new ArrayList<LabelValueBean>();
//		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		try {
//			sql.append("SELECT  EXME_Grafica.* FROM EXME_Grafica ")
//			.append(" WHERE EXME_Grafica.IsActive = 'Y' ");
//			if (sexo != null) {
//				sql.append(" AND EXME_Grafica.Sexo=?");
//			}
//			sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name));
//			sql.append(" ORDER BY EXME_Grafica.Value");
//
//			pstmt = DB.prepareStatement(sql.toString(), trxName);
//			if (sexo != null) {
//				pstmt.setString(1, sexo);
//			}
//			rs = pstmt.executeQuery();
//
//			while (rs.next()) {
//				lista.add(new LabelValueBean(rs.getString(COLUMNNAME_Value), rs.getString(COLUMNNAME_EXME_Grafica_ID)));
//			}
//		} catch (Exception e) {
//			s_log.log(Level.SEVERE, sql.toString(), e);
//		} finally {
//			DB.close(rs, pstmt);
//		}
//		return lista;
//	}
	
	//Lista que solo manda las graficas que corresponden dependiendo la edad del paciente
	public static List<LabelValueBean> getGraphicsAge(Properties ctx, MEXMEPaciente paciente, String trxName) {
		final List<LabelValueBean> list = new ArrayList<LabelValueBean>();

		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			sql.append("SELECT DISTINCT EXME_Grafica.Value, EXME_Grafica.EXME_Grafica_ID FROM EXME_Grafica ");
			// requerido registros en detalle para las graficas (Lama)
			sql.append(" INNER JOIN EXME_GraficaDet d ON (EXME_Grafica.EXME_Grafica_ID=d.EXME_Grafica_ID AND d.IsActive='Y') ");
			sql.append(" WHERE EXME_Grafica.IsActive = 'Y'");
			if (paciente != null) {
				sql.append(" AND TRIM(EXME_Grafica.Sexo)=?");
				sql.append(" AND ( ? >= COALESCE(EXME_Grafica.edadinicial,0) AND ? <= COALESCE(EXME_Grafica.edadfinal,0)) ");
			}
			sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name));
			sql.append(" ORDER BY EXME_Grafica.Value");

			pstmt = DB.prepareStatement(sql.toString(), trxName);
			if (paciente != null) {
				pstmt.setString(1, paciente.getSexo());
				pstmt.setInt(2, paciente.getAge().getAnios());
				pstmt.setInt(3, paciente.getAge().getAnios());
			}
			rs = pstmt.executeQuery();

			while (rs.next()) {
				list.add(new LabelValueBean(rs.getString(1), rs.getString(2)));
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
		}
		return list;
	}

	public static List<MEXMEGrafica> getGraphics(Properties ctx, String trxName) {
		final List<MEXMEGrafica> lista = new ArrayList<MEXMEGrafica>();
		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			sql.append("SELECT  * FROM EXME_Grafica ");
			sql.append(" WHERE IsActive = 'Y' ");
			sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name));
			sql.append(" ORDER BY Value");

			pstmt = DB.prepareStatement(sql.toString(), trxName);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				lista.add(new MEXMEGrafica(ctx, rs, trxName));
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
		}
		return lista;
	}

	public CustomGraphicValues getValues(Properties ctx, int exmePacienteID, String trxName) {
		CustomGraphicValues values = new CustomGraphicValues();

		MEXMEGraficaDet objX = MEXMEGraficaDet.getGraphicByAxis(ctx, getEXME_Grafica_ID(), true, trxName);
		MEXMEGraficaDet objY = MEXMEGraficaDet.getGraphicByAxis(ctx, getEXME_Grafica_ID(), false, trxName);

		if (objX != null && objY != null) {
			values = getValuesByVitalSign(ctx, exmePacienteID, getEXME_Grafica_ID(), objX, objY, trxName);
		}
		return values;
	}

	public static CustomGraphicValues getValuesByVitalSign(Properties ctx, int exmePacienteID, int exmeGraficaID,
			MEXMEGraficaDet objX, MEXMEGraficaDet objY, String trxName) {
		CustomGraphicValues values = new CustomGraphicValues();
		MEXMESignoVital xValue = new MEXMESignoVital(ctx, objX.getEXME_SignoVital_ID(), trxName);
		MEXMESignoVital yValue = new MEXMESignoVital(ctx, objY.getEXME_SignoVital_ID(), trxName);
		List<XYValues> set = new ArrayList<XYValues>();
		if (xValue.getValue().equals("ABY")) {
			set = MEXMESignoVitalDet.getValuesByAge(ctx, exmePacienteID, 0,  yValue.getEXME_SignoVital_ID(), 
					objX.getLim_Inferior(), objX.getLim_Superior(), objY.getC_UOM_ID(), false, trxName);

		} else if (xValue.getValue().equals("ABM")) {
			set = MEXMESignoVitalDet.getValuesByAge(ctx, exmePacienteID, 0, yValue.getEXME_SignoVital_ID(), 
					//new BigDecimal( Utilerias.getYearsByMonths(objX.getLim_Inferior().intValue())),
					//new BigDecimal( Utilerias.getYearsByMonths(objX.getLim_Superior().intValue())),
					new BigDecimal(objX.getLim_Inferior().doubleValue()*12),
					new BigDecimal(objX.getLim_Superior().doubleValue()*12),
					objY.getC_UOM_ID(), true, trxName);			

		} else {
			List<MEXMESignoVitalDet> lst = MEXMESignoVitalDet.getValues(ctx, exmePacienteID, 0,
					xValue.getEXME_SignoVital_ID(), yValue.getEXME_SignoVital_ID(), objX.getC_UOM_ID(), objY.getC_UOM_ID(), trxName);			
			set = createSeries(lst, xValue, yValue);

		}
		String label = Utilerias.getMessage(ctx, null, "aplicacion.paciente") + ": " + new MEXMEPaciente(ctx, exmePacienteID, null).getNombre_Pac();
		values.addSeries(label, set);

		List<MEXMEGraficaDefaultV> lstDefault = MEXMEGraficaDefaultV.getGraphicDefault(ctx, exmeGraficaID, null);
		if (!lstDefault.isEmpty()) {
			for (MEXMEGraficaDefaultV obj: lstDefault) {
				set = new ArrayList<XYValues>();
				if (xValue.getValue().equals("ABY")) {
					set = MEXMESignoVitalDet.getValuesByAge(ctx, 0, obj.getEXME_GraficaDefaultV_ID(), yValue.getEXME_SignoVital_ID(), 
							objX.getLim_Inferior(), objX.getLim_Superior(), objY.getC_UOM_ID(), false, trxName);
				} else if (xValue.getValue().equals("ABM")) {
					set = MEXMESignoVitalDet.getValuesByAge(ctx, 0, obj.getEXME_GraficaDefaultV_ID(), yValue.getEXME_SignoVital_ID(), 
							//new BigDecimal( Utilerias.getYearsByMonths(objX.getLim_Inferior().intValue())),
							//new BigDecimal( Utilerias.getYearsByMonths(objX.getLim_Superior().intValue())),
							new BigDecimal(objX.getLim_Inferior().doubleValue()*12),
							new BigDecimal(objX.getLim_Superior().doubleValue()*12),
							objY.getC_UOM_ID(), true, trxName);
				} else {				
					List<MEXMESignoVitalDet> lst = MEXMESignoVitalDet.getValues(ctx, 0, obj.getEXME_GraficaDefaultV_ID(),
							xValue.getEXME_SignoVital_ID(), yValue.getEXME_SignoVital_ID(), objX.getC_UOM_ID(), objY.getC_UOM_ID(), trxName);
					set = createSeries(lst, xValue, yValue);
				}
				XYSeries series = new XYSeries(obj.getName());
				for (XYValues value: set) {
					series.add(value.getxValue(), value.getyValue());
				}
				series.setAddAnnotation(true);
				series.setColor(Color.BLACK);
				series.setxAnnotation(objX.getLim_Superior().doubleValue() - objX.getIntervalos().doubleValue()/2);
				values.addSerie(series);
			}
		}
		return values;
	}

	private static List<XYValues> createSeries(List<MEXMESignoVitalDet> lst, MEXMESignoVital xValue, MEXMESignoVital yValue) {
		List<XYValues> set = new ArrayList<XYValues>();
		BigDecimal xGraphValue= null;
		BigDecimal yGraphValue= null;
		if (!lst.isEmpty()) {
			int folio = lst.get(0).getFolio();
			for (int i = 0; i < lst.size(); i++) {
				MEXMESignoVitalDet obj = lst.get(i);
				if (obj.getFolio() == folio) {
					if (obj.getEXME_SignoVital_ID() == xValue.getEXME_SignoVital_ID()) {
						xGraphValue = obj.getValor();
					} else if (obj.getEXME_SignoVital_ID() == yValue.getEXME_SignoVital_ID()) {
						yGraphValue = obj.getValor();
					}	

					if (i == lst.size() -1) {
						if (xGraphValue != null && yGraphValue != null) {
							set.add(new XYValues(xGraphValue, yGraphValue));
						}
					}
				} else {
					folio = obj.getFolio();
					if (xGraphValue != null && yGraphValue != null) {
						set.add(new XYValues(xGraphValue, yGraphValue));
					}
					xGraphValue = null;
					yGraphValue = null;
					if (obj.getEXME_SignoVital_ID() == xValue.getEXME_SignoVital_ID()) {
						xGraphValue = obj.getValor();
					} else if (obj.getEXME_SignoVital_ID() == yValue.getEXME_SignoVital_ID()) {
						yGraphValue = obj.getValor();
					}	
				}
			}
		}
		return set;
	}
}
