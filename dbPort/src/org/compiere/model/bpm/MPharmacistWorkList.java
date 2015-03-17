/**
 * 
 */
package org.compiere.model.bpm;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.adempiere.exceptions.DBException;
import org.compiere.model.MEXMEPrescRXDet;
import org.compiere.model.PharmacistModel;
import org.compiere.util.CLogger;
import org.compiere.util.DB;

/**
 * Modelado de la pantalla de 
 * Listado de trabajo del farmaceutico
 * @author twry
 *
 */
public class MPharmacistWorkList {
	/** Static Logger */
	private static CLogger slog = CLogger.getCLogger(PharmacistModel.class);
	private Integer[] params;
	private final Properties ctx;
	private final String select;
	private final String from;
	private final String orderby;
	private final String trxName;
	private final String count;

	/**
	 * Constructor
	 * @param ctx contexto
	 * @param pSelect columnas
	 * @param pFrom tablas y condiciones
	 * @param trxName nombre de transaccion
	 */
	public MPharmacistWorkList(final Properties ctx, final String pSelect,
			final String pFrom, final String pOrderby, final String pCount, final String trxName) {
		this.ctx = ctx;
		this.select = pSelect;
		this.from = pFrom;
		this.orderby = pOrderby;
		this.count = pCount;
		this.trxName = trxName;
	}

	/**
	 * Lectura de Parametros
	 * @param parameters
	 * @return
	 */
	public MPharmacistWorkList setParameters(final Integer[] parameters) {
		params = parameters;
		return this;
	}

	/**
	 * Numero de registros
	 * @return
	 * @throws DBException
	 */
	public int count() throws DBException {
		final StringBuffer sql = new StringBuffer()
				.append(count);
		return DB.getSQLValue(trxName, sql.toString(), params);//[[10001068, 10001068, 10001075, 0]]
	}

	/**
	 * Listado de datos
	 * @return
	 * @throws DBException
	 */
	public List<MEXMEPrescRXDet> list() throws DBException {
		final List<MEXMEPrescRXDet> valueReturn = new ArrayList<MEXMEPrescRXDet>();
		final StringBuffer sql = new StringBuffer(select).append(from).append(orderby);

		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			if(params!=null)
			DB.setParameters(pstmt, params);//[[10001068, 10001068, 10001075, 0]]
			rset = pstmt.executeQuery();
			
			while (rset.next()) {
				final MEXMEPrescRXDet bean = new MEXMEPrescRXDet(ctx, rset, trxName);
				bean.setBackOrder(rset.getString("backorder"));
				bean.setChargeMaster(rset.getInt("Existe"));
				bean.setQtyAvailable(rset.getBigDecimal("QtyAvailable"));
				bean.setDispenseId(rset.getInt("EXME_Dispense_ID"));
				valueReturn.add(bean);
			}
		} catch (SQLException e) {
			slog.log(Level.SEVERE, sql.toString(), e.getLocalizedMessage());
			throw new DBException(e, sql.toString());
		} finally {
			DB.close(rset, pstmt);
		}
		//
		return valueReturn;
	}
	
}
