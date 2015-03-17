/**
 * 
 */
package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.DB;

/**
 * Control del presupuesto de egresos El presupuesto (MA) Modificado Autorizado
 * es calculado por OR + AD + AM - RE y sirve como base para calcular el (DI)
 * Disponible Trámite y este a su vez es calculado por MA - RT - CO - DE - EJ -
 * PA las iniciales son las siguientes: (RT) Reducciones Trámite (CO)
 * Comprometido (DE) Devengado (EJ) Ejercido (PA) Pagado
 * 
 * @author twry
 * 
 */
public class MEXMECtrlPresup extends X_EXME_CtrlPresup {

	/** serialVersionUID */
	private static final long serialVersionUID = -8263311289024953990L;
	/** Static Logger */
	private static CLogger slog = CLogger.getCLogger(MEXMECtrlPresup.class);
	/** Sirve para almacenar temporalmente los montos de la reasignación */
	private PresupuestoView auxMonto = new PresupuestoView();
	/** Linea del presupuesto al que se hace referencia */
	private MEXMEPresupuestoDet mPresupuestoDet = null;

	/**
	 * Constructor
	 * 
	 * @param ctx
	 *            Contexto
	 * @param exmeCtrlPresupID
	 *            Id control presupuestal
	 * @param trxName
	 *            Nombre de transaccion
	 */
	public MEXMECtrlPresup(final Properties ctx, final int exmeCtrlPresupID,
			final String trxName) {
		super(ctx, exmeCtrlPresupID, trxName);
	}

	/**
	 * Constructor
	 * 
	 * @param ctx
	 *            Contexto
	 * @param rset
	 *            Result set
	 * @param trxName
	 *            Nombre de transaccion
	 */
	public MEXMECtrlPresup(final Properties ctx, final ResultSet rset,
			final String trxName) {
		super(ctx, rset, trxName);
	}

	public PresupuestoView getAuxMonto() {
		return auxMonto;
	}

	public void setAuxMonto(final PresupuestoView auxiliar) {
		this.auxMonto = auxiliar;
	}

	public MEXMEPresupuestoDet getmPresupuestoDet() {
		return mPresupuestoDet;
	}

	public void setmPresupuestoDet(final MEXMEPresupuestoDet mPresupuestoDet) {
		this.mPresupuestoDet = mPresupuestoDet;
	}

	/**
	 * Listar el presupuesto de egresos en la tabla de control de presupuesto en
	 * las partidas que esten relacionadas con alguna reasignación
	 * 
	 * @param ctx
	 *            Contexto
	 * @param pPresupEgrID
	 *            Presupuesto
	 * @param pPresupModifID
	 *            Reasignacion
	 * @param trxName
	 *            Nombre de transacción
	 * @return Listado de partidas del control presupuestal afectadas
	 */
	public static List<MEXMECtrlPresup> getModAutirizado(final Properties ctx,
			final int pPresupEgrID, final int pPresupModifID,
			final String trxName) {

		final StringBuilder sql = new StringBuilder();
		final List<MEXMECtrlPresup> list = new ArrayList<MEXMECtrlPresup>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		sql.append(" SELECT cp.* , re.EXME_PresupuestoDet_ID as Ids, ")
				.append("    re.amount    as a1,  ")
				.append("    re.amountene as a2,  ")
				.append("    re.amountfeb as a3,  ")
				.append("    re.amountmar as a4,  ")
				.append("    re.amountabr as a5,  ")
				.append("    re.amountmay as a6,  ")
				.append("    re.amountjun as a7,  ")
				.append("    re.amountjul as a8,  ")
				.append("    re.amountago as a9,  ")
				.append("    re.amountsep as a10, ")
				.append("    re.amountoct as a11, ")
				.append("    re.amountnov as a12, ")
				.append("    re.amountdic as a13  ")
				.append(" FROM  EXME_CtrlPresup          cp    ")
				.append(" INNER JOIN EXME_PresupuestoDet re ON  cp.EXME_PartidaPres_ID = re.EXME_PartidaPres_ID ")
				.append(" WHERE  cp.IsActive='Y'               ")
				.append("    AND cp.EXME_PresupuestoEgr_ID = ? ")
				// #1
				.append("    AND re.IsActive = 'Y'             ")
				.append("    AND re.EXME_PresupuestoEgr_ID = ? ") // #2
				.append("    AND re.EXME_PresupuestoModif_ID = ?   ");// #3

		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, pPresupEgrID);
			pstmt.setInt(2, pPresupEgrID);
			pstmt.setInt(3, pPresupModifID);
			rset = pstmt.executeQuery();

			while (rset.next()) {
				final MEXMECtrlPresup bean = new MEXMECtrlPresup(ctx, rset,
						trxName);
				bean.getAuxMonto().setAmtSum(rset.getBigDecimal("a1"));
				bean.getAuxMonto().setAmt1(rset.getBigDecimal("a2"));
				bean.getAuxMonto().setAmt2(rset.getBigDecimal("a3"));
				bean.getAuxMonto().setAmt3(rset.getBigDecimal("a4"));
				bean.getAuxMonto().setAmt4(rset.getBigDecimal("a5"));
				bean.getAuxMonto().setAmt5(rset.getBigDecimal("a6"));
				bean.getAuxMonto().setAmt6(rset.getBigDecimal("a7"));
				bean.getAuxMonto().setAmt7(rset.getBigDecimal("a8"));
				bean.getAuxMonto().setAmt8(rset.getBigDecimal("a9"));
				bean.getAuxMonto().setAmt9(rset.getBigDecimal("a10"));
				bean.getAuxMonto().setAmt10(rset.getBigDecimal("a11"));
				bean.getAuxMonto().setAmt11(rset.getBigDecimal("a12"));
				bean.getAuxMonto().setAmt12(rset.getBigDecimal("a13"));
				bean.getAuxMonto().setPresupuestoDetID(rset.getInt("Ids"));
				list.add(bean);
			}

		} catch (Exception e) {
			slog.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rset, pstmt);
		}
		return list;
	}

	/**
	 * Actualizacion de las columnas de modificado autorizado
	 * 
	 * @param sumar
	 *            true aumentar el monto
	 */
	public void setMA(final boolean sumar) {
		if (sumar) {
			setMaSum(getMaSum().add(auxMonto.getAmtSum()));
			setMa1(getMa1().add(auxMonto.getAmt1()));
			setMa2(getMa2().add(auxMonto.getAmt2()));
			setMa3(getMa3().add(auxMonto.getAmt3()));
			setMa4(getMa4().add(auxMonto.getAmt4()));
			setMa5(getMa5().add(auxMonto.getAmt5()));
			setMa6(getMa6().add(auxMonto.getAmt6()));
			setMa7(getMa7().add(auxMonto.getAmt7()));
			setMa8(getMa8().add(auxMonto.getAmt8()));
			setMa9(getMa9().add(auxMonto.getAmt9()));
			setMa10(getMa10().add(auxMonto.getAmt10()));
			setMa11(getMa11().add(auxMonto.getAmt11()));
			setMa12(getMa12().add(auxMonto.getAmt12()));
		} else {
			setMaSum(getMaSum().subtract(auxMonto.getAmtSum()));
			setMa1(getMa1().subtract(auxMonto.getAmt1()));
			setMa2(getMa2().subtract(auxMonto.getAmt2()));
			setMa3(getMa3().subtract(auxMonto.getAmt3()));
			setMa4(getMa4().subtract(auxMonto.getAmt4()));
			setMa5(getMa5().subtract(auxMonto.getAmt5()));
			setMa6(getMa6().subtract(auxMonto.getAmt6()));
			setMa7(getMa7().subtract(auxMonto.getAmt7()));
			setMa8(getMa8().subtract(auxMonto.getAmt8()));
			setMa9(getMa9().subtract(auxMonto.getAmt9()));
			setMa10(getMa10().subtract(auxMonto.getAmt10()));
			setMa11(getMa11().subtract(auxMonto.getAmt11()));
			setMa12(getMa12().subtract(auxMonto.getAmt12()));
		}
	}

	/**
	 * Actualizacion de las columnas de modificado autorizado
	 * 
	 * @param sumar
	 *            true aumentar el monto
	 */
	public void setCO(final boolean sumar) {
		if (sumar) {
			setCoSum(getCoSum().add(auxMonto.getAmtSum()));
			setCo1(getCo1().add(auxMonto.getAmt1()));
			setCo2(getCo2().add(auxMonto.getAmt2()));
			setCo3(getCo3().add(auxMonto.getAmt3()));
			setCo4(getCo4().add(auxMonto.getAmt4()));
			setCo5(getCo5().add(auxMonto.getAmt5()));
			setCo6(getCo6().add(auxMonto.getAmt6()));
			setCo7(getCo7().add(auxMonto.getAmt7()));
			setCo8(getCo8().add(auxMonto.getAmt8()));
			setCo9(getCo9().add(auxMonto.getAmt9()));
			setCo10(getCo10().add(auxMonto.getAmt10()));
			setCo11(getCo11().add(auxMonto.getAmt11()));
			setCo12(getCo12().add(auxMonto.getAmt12()));
		} else {
			setCoSum(getCoSum().subtract(auxMonto.getAmtSum()));
			setCo1(getCo1().subtract(auxMonto.getAmt1()));
			setCo2(getCo2().subtract(auxMonto.getAmt2()));
			setCo3(getCo3().subtract(auxMonto.getAmt3()));
			setCo4(getCo4().subtract(auxMonto.getAmt4()));
			setCo5(getCo5().subtract(auxMonto.getAmt5()));
			setCo6(getCo6().subtract(auxMonto.getAmt6()));
			setCo7(getCo7().subtract(auxMonto.getAmt7()));
			setCo8(getCo8().subtract(auxMonto.getAmt8()));
			setCo9(getCo9().subtract(auxMonto.getAmt9()));
			setCo10(getCo10().subtract(auxMonto.getAmt10()));
			setCo11(getCo11().subtract(auxMonto.getAmt11()));
			setCo12(getCo12().subtract(auxMonto.getAmt12()));
		}
	}

	/**
	 * Actualizacion de las columnas de modificado autorizado
	 * 
	 * @param sumar
	 *            true aumentar el monto
	 */
	public void setDE(final boolean sumar) {
		if (sumar) {
			setDeSum(getDeSum().subtract(auxMonto.getAmtSum()));
			setDe1(getDe1().add(auxMonto.getAmt1()));
			setDe2(getDe2().add(auxMonto.getAmt2()));
			setDe3(getDe3().add(auxMonto.getAmt3()));
			setDe4(getDe4().add(auxMonto.getAmt4()));
			setDe5(getDe5().add(auxMonto.getAmt5()));
			setDe6(getDe6().add(auxMonto.getAmt6()));
			setDe7(getDe7().add(auxMonto.getAmt7()));
			setDe8(getDe8().add(auxMonto.getAmt8()));
			setDe9(getDe9().add(auxMonto.getAmt9()));
			setDe10(getDe10().add(auxMonto.getAmt10()));
			setDe11(getDe11().add(auxMonto.getAmt11()));
			setDe12(getDe12().add(auxMonto.getAmt12()));
		} else {
			setDeSum(getDeSum().subtract(auxMonto.getAmtSum()));
			setDe1(getDe1().subtract(auxMonto.getAmt1()));
			setDe2(getDe2().subtract(auxMonto.getAmt2()));
			setDe3(getDe3().subtract(auxMonto.getAmt3()));
			setDe4(getDe4().subtract(auxMonto.getAmt4()));
			setDe5(getDe5().subtract(auxMonto.getAmt5()));
			setDe6(getDe6().subtract(auxMonto.getAmt6()));
			setDe7(getDe7().subtract(auxMonto.getAmt7()));
			setDe8(getDe8().subtract(auxMonto.getAmt8()));
			setDe9(getDe9().subtract(auxMonto.getAmt9()));
			setDe10(getDe10().subtract(auxMonto.getAmt10()));
			setDe11(getDe11().subtract(auxMonto.getAmt11()));
			setDe12(getDe12().subtract(auxMonto.getAmt12()));
		}
	}

	/**
	 * Actualizacion de las columnas de disponible tramite
	 * 
	 * @param sumar
	 *            true aumentar el monto
	 */
	public void setDI(final boolean sumar) {
		if (sumar) {
			setDisum(getDisum().add(auxMonto.getAmtSum()));
			setDi1(getDi1().add(auxMonto.getAmt1()));
			setDi2(getDi2().add(auxMonto.getAmt2()));
			setDi3(getDi3().add(auxMonto.getAmt3()));
			setDi4(getDi4().add(auxMonto.getAmt4()));
			setDi5(getDi5().add(auxMonto.getAmt5()));
			setDi6(getDi6().add(auxMonto.getAmt6()));
			setDi7(getDi7().add(auxMonto.getAmt7()));
			setDi8(getDi8().add(auxMonto.getAmt8()));
			setDi9(getDi9().add(auxMonto.getAmt9()));
			setDi10(getDi10().add(auxMonto.getAmt10()));
			setDi11(getDi11().add(auxMonto.getAmt11()));
			setDi12(getDi12().add(auxMonto.getAmt12()));
		} else {
			setDisum(getDisum().subtract(auxMonto.getAmtSum()));
			setDi1(getDi1().subtract(auxMonto.getAmt1()));
			setDi2(getDi2().subtract(auxMonto.getAmt2()));
			setDi3(getDi3().subtract(auxMonto.getAmt3()));
			setDi4(getDi4().subtract(auxMonto.getAmt4()));
			setDi5(getDi5().subtract(auxMonto.getAmt5()));
			setDi6(getDi6().subtract(auxMonto.getAmt6()));
			setDi7(getDi7().subtract(auxMonto.getAmt7()));
			setDi8(getDi8().subtract(auxMonto.getAmt8()));
			setDi9(getDi9().subtract(auxMonto.getAmt9()));
			setDi10(getDi10().subtract(auxMonto.getAmt10()));
			setDi11(getDi11().subtract(auxMonto.getAmt11()));
			setDi12(getDi12().subtract(auxMonto.getAmt12()));
		}
	}

	/**
	 * Actualizacion de las columnas de ejercido
	 * 
	 * @param sumar
	 *            true aumentar el monto
	 */
	public void setEJ(final boolean sumar) {
		if (sumar) {
			setEjSum(getEjSum().add(auxMonto.getAmtSum()));
			setEj1(getEj1().add(auxMonto.getAmt1()));
			setEj2(getEj2().add(auxMonto.getAmt2()));
			setEj3(getEj3().add(auxMonto.getAmt3()));
			setEj4(getEj4().add(auxMonto.getAmt4()));
			setEj5(getEj5().add(auxMonto.getAmt5()));
			setEj6(getEj6().add(auxMonto.getAmt6()));
			setEj7(getEj7().add(auxMonto.getAmt7()));
			setEj8(getEj8().add(auxMonto.getAmt8()));
			setEj9(getEj9().add(auxMonto.getAmt9()));
			setEj10(getEj10().add(auxMonto.getAmt10()));
			setEj11(getEj11().add(auxMonto.getAmt11()));
			setEj12(getEj12().add(auxMonto.getAmt12()));
		} else {
			setEjSum(getEjSum().subtract(auxMonto.getAmtSum()));
			setEj1(getEj1().subtract(auxMonto.getAmt1()));
			setEj2(getEj2().subtract(auxMonto.getAmt2()));
			setEj3(getEj3().subtract(auxMonto.getAmt3()));
			setEj4(getEj4().subtract(auxMonto.getAmt4()));
			setEj5(getEj5().subtract(auxMonto.getAmt5()));
			setEj6(getEj6().subtract(auxMonto.getAmt6()));
			setEj7(getEj7().subtract(auxMonto.getAmt7()));
			setEj8(getEj8().subtract(auxMonto.getAmt8()));
			setEj9(getEj9().subtract(auxMonto.getAmt9()));
			setEj10(getEj10().subtract(auxMonto.getAmt10()));
			setEj11(getEj11().subtract(auxMonto.getAmt11()));
			setEj12(getEj12().subtract(auxMonto.getAmt12()));
		}
	}

	/**
	 * Actualizacion de las columnas de modificado autorizado
	 * 
	 * @param sumar
	 *            true aumentar el monto
	 */
	public void setPA(final boolean sumar) {
		if (sumar) {
			setPaSum(getPaSum().add(auxMonto.getAmtSum()));
			setPa1(getPa1().add(auxMonto.getAmt1()));
			setPa2(getPa2().add(auxMonto.getAmt2()));
			setPa3(getPa3().add(auxMonto.getAmt3()));
			setPa4(getPa4().add(auxMonto.getAmt4()));
			setPa5(getPa5().add(auxMonto.getAmt5()));
			setPa6(getPa6().add(auxMonto.getAmt6()));
			setPa7(getPa7().add(auxMonto.getAmt7()));
			setPa8(getPa8().add(auxMonto.getAmt8()));
			setPa9(getPa9().add(auxMonto.getAmt9()));
			setPa10(getPa10().add(auxMonto.getAmt10()));
			setPa11(getPa11().add(auxMonto.getAmt11()));
			setPa12(getPa12().add(auxMonto.getAmt12()));
		} else {
			setPaSum(getPaSum().subtract(auxMonto.getAmtSum()));
			setPa1(getPa1().subtract(auxMonto.getAmt1()));
			setPa2(getPa2().subtract(auxMonto.getAmt2()));
			setPa3(getPa3().subtract(auxMonto.getAmt3()));
			setPa4(getPa4().subtract(auxMonto.getAmt4()));
			setPa5(getPa5().subtract(auxMonto.getAmt5()));
			setPa6(getPa6().subtract(auxMonto.getAmt6()));
			setPa7(getPa7().subtract(auxMonto.getAmt7()));
			setPa8(getPa8().subtract(auxMonto.getAmt8()));
			setPa9(getPa9().subtract(auxMonto.getAmt9()));
			setPa10(getPa10().subtract(auxMonto.getAmt10()));
			setPa11(getPa11().subtract(auxMonto.getAmt11()));
			setPa12(getPa12().subtract(auxMonto.getAmt12()));
		}
	}

	/**
	 * Actualizacion de las columnas de modificado autorizado
	 * 
	 * @param sumar
	 *            true aumentar el monto
	 */
	public void setRT(final boolean sumar) {
		if (sumar) {
			setRtSum(getRtSum().add(auxMonto.getAmtSum()));
			setRt1(getRt1().add(auxMonto.getAmt1()));
			setRt2(getRt2().add(auxMonto.getAmt2()));
			setRt3(getRt3().add(auxMonto.getAmt3()));
			setRt4(getRt4().add(auxMonto.getAmt4()));
			setRt5(getRt5().add(auxMonto.getAmt5()));
			setRt6(getRt6().add(auxMonto.getAmt6()));
			setRt7(getRt7().add(auxMonto.getAmt7()));
			setRt8(getRt8().add(auxMonto.getAmt8()));
			setRt9(getRt9().add(auxMonto.getAmt9()));
			setRt10(getRt10().add(auxMonto.getAmt10()));
			setRt11(getRt11().add(auxMonto.getAmt11()));
			setRt12(getRt12().add(auxMonto.getAmt12()));
		} else {
			setRtSum(getRtSum().subtract(auxMonto.getAmtSum()));
			setRt1(getRt1().subtract(auxMonto.getAmt1()));
			setRt2(getRt2().subtract(auxMonto.getAmt2()));
			setRt3(getRt3().subtract(auxMonto.getAmt3()));
			setRt4(getRt4().subtract(auxMonto.getAmt4()));
			setRt5(getRt5().subtract(auxMonto.getAmt5()));
			setRt6(getRt6().subtract(auxMonto.getAmt6()));
			setRt7(getRt7().subtract(auxMonto.getAmt7()));
			setRt8(getRt8().subtract(auxMonto.getAmt8()));
			setRt9(getRt9().subtract(auxMonto.getAmt9()));
			setRt10(getRt10().subtract(auxMonto.getAmt10()));
			setRt11(getRt11().subtract(auxMonto.getAmt11()));
			setRt12(getRt12().subtract(auxMonto.getAmt12()));
		}
	}

	/**
	 * Copia los montos del modificado autorizado a partir del presupuesto
	 * original
	 * 
	 * @param bean
	 */
	public void copyAmountMa(final MEXMEPresupuestoDet bean) {
		setMaSum(bean.getAmount());
		setMa1(bean.getAmountEne());
		setMa2(bean.getAmountFeb());
		setMa3(bean.getAmountMar());
		setMa4(bean.getAmountAbr());
		setMa5(bean.getAmountMay());
		setMa6(bean.getAmountJun());
		setMa7(bean.getAmountJul());
		setMa8(bean.getAmountAgo());
		setMa9(bean.getAmountSep());
		setMa10(bean.getAmountOct());
		setMa11(bean.getAmountNov());
		setMa12(bean.getAmountDic());
	}

	/**
	 * Copiar a disponible tramite
	 * 
	 * @param bean
	 */
	public void copyAmountDi(final MEXMEPresupuestoDet bean) {
		setDisum(bean.getAmount());
		setDi1(bean.getAmountEne());
		setDi2(bean.getAmountFeb());
		setDi3(bean.getAmountMar());
		setDi4(bean.getAmountAbr());
		setDi5(bean.getAmountMay());
		setDi6(bean.getAmountJun());
		setDi7(bean.getAmountJul());
		setDi8(bean.getAmountAgo());
		setDi9(bean.getAmountSep());
		setDi10(bean.getAmountOct());
		setDi11(bean.getAmountNov());
		setDi12(bean.getAmountDic());
	}

	/**
	 * DI Disponible Trámite Calculo MA - RT - CO - DE - EJ - PA
	 * 
	 * @param bean
	 */
	public void calculoDi() {
		setDisum(getMaSum().subtract(
				getRtSum().add(getCoSum()).add(getDeSum()).add(getEjSum())
						.add(getPaSum())));
		setDi1(getMa1().subtract(
				getRt1().add(getCo1()).add(getDe1()).add(getEj1())
						.add(getPa1())));
		setDi2(getMa2().subtract(
				getRt2().add(getCo2()).add(getDe2()).add(getEj2())
						.add(getPa2())));
		setDi3(getMa3().subtract(
				getRt3().add(getCo3()).add(getDe3()).add(getEj3())
						.add(getPa3())));
		setDi4(getMa4().subtract(
				getRt4().add(getCo4()).add(getDe4()).add(getEj4())
						.add(getPa4())));
		setDi5(getMa5().subtract(
				getRt5().add(getCo5()).add(getDe5()).add(getEj5())
						.add(getPa5())));
		setDi6(getMa6().subtract(
				getRt6().add(getCo6()).add(getDe6()).add(getEj6())
						.add(getPa6())));
		setDi7(getMa7().subtract(
				getRt7().add(getCo7()).add(getDe7()).add(getEj7())
						.add(getPa7())));
		setDi8(getMa8().subtract(
				getRt8().add(getCo8()).add(getDe8()).add(getEj8())
						.add(getPa8())));
		setDi9(getMa9().subtract(
				getRt9().add(getCo9()).add(getDe9()).add(getEj9())
						.add(getPa9())));
		setDi10(getMa10().subtract(
				getRt10().add(getCo10()).add(getDe10()).add(getEj10())
						.add(getPa10())));
		setDi11(getMa11().subtract(
				getRt11().add(getCo11()).add(getDe11()).add(getEj11())
						.add(getPa11())));
		setDi12(getMa12().subtract(
				getRt12().add(getCo12()).add(getDe12()).add(getEj12())
						.add(getPa12())));
	}
}
