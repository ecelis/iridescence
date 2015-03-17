/**
 * 
 */
package org.compiere.acct;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.compiere.model.MAcctSchema;
import org.compiere.model.MEXMEPresupuestoEgr;
import org.compiere.model.MEXMEPresupuestoModif;
import org.compiere.model.X_EXME_PresupuestoModif;
import org.compiere.util.Env;

/**
 * Clase para hacer los asientos contables del presupuesto y de sus
 * modificaciones (ampliacion, adición o reducción)
 * 
 * @author twry
 * @version $Id: Doc_PresupuestoEgr.java,v 1.0 2013/05/28 00:53:33 twry $
 */
public class Doc_PresupuestoEgr extends Doc {

	/**
	 * Constructor
	 * 
	 * @param ass
	 * @param rset
	 * @param trxName
	 */
	public Doc_PresupuestoEgr(final MAcctSchema[] ass, final Class clazz,
			final ResultSet rset, final String trxName) {
		super(ass, clazz, rset, DOCTYPE_GLDocument, trxName);
	}

	/** Budget = B */
	public static final String POSTYPEBUDGET = "B";
	/** line */
	private DocLine mLine;
	/** Es presupuesto */
	private boolean isBudget = false;
	/** Es ampliación o reducción */
	private boolean isAmplicacion = false;

	/**
	 * Leer el documento
	 * 
	 * @see org.compiere.acct.Doc#loadDocumentDetails()
	 */
	@Override
	protected String loadDocumentDetails() {

		//
		if (getPO() instanceof MEXMEPresupuestoEgr) {
			// Presupuesto
			final MEXMEPresupuestoEgr presupuesto = (MEXMEPresupuestoEgr) getPO();
			mLine = new DocLine(presupuesto, this);
			mLine.setAmount(presupuesto.getAmount(), presupuesto.getAmount());
			isBudget = true;
			setDateDoc(presupuesto.getDateTrx());
			setDateAcct(presupuesto.getDateAcct());

		} else {
			// Modificacion al Presupuesto
			final MEXMEPresupuestoModif modificacion = (MEXMEPresupuestoModif) getPO();
			mLine = new DocLine(modificacion, this);
			mLine.setAmount(modificacion.getAmount(), modificacion.getAmount());
			setDateDoc(modificacion.getDateTrx());
			setDateAcct(modificacion.getDateAcct());
			isAmplicacion = X_EXME_PresupuestoModif.TIPO_Reduction
					.equals(modificacion.getTipo());

		}

		log.fine(mLine.toString());
		return null;
	}

	/**
	 * Get Balance
	 * 
	 * @return Zero (always balanced)
	 */
	@Override
	public BigDecimal getBalance() {
		return Env.ZERO;
	}

	/**
	 * Create Facts (the accounting logic) Registro del presupuesto de egresos
	 * original
	 * 
	 * <pre>
	 * No. de Cta. Nombre de la cuenta Debe Haber
	 * 8.2.2 Presupuesto de Egresos por Ejercer 140,000
	 * 8.2.1 Presupuesto de Egresos Aprobado            140,000
	 *       Sumas iguales                      140,000 140,000
	 * </pre>
	 * 
	 * Registro de una ampliación al presupuesto de egresos original aprobado
	 * 
	 * <pre>
	 * No. de Cta. Nombre de la cuenta Debe Haber
	 * 8.2.2 Presupuesto de Egresos por Ejercer                40,000
	 * 8.2.3 Modificaciones al Presupuesto de Egresos Aprobado        40,000
	 *       Sumas iguales                                     40,000 40,000
	 * </pre>
	 * 
	 * @param acctSchema
	 *            accounting schema
	 * @return Fact
	 */
	@Override
	public ArrayList<Fact> createFacts(final MAcctSchema acctSchema) {
		// create Fact Header
		final Fact fact = new Fact(this, acctSchema, Fact.POST_Budget);

		if (isBudget) {
			// Project DR
			// final FactLine dr =
			fact.createLine(mLine,
					getAccount(ACCTTYPE_PresEjercer, acctSchema),
					acctSchema.getC_Currency_ID(), mLine.getAmtSourceDr(), null);

			// Inventory CR
			// final FactLine cr =
			fact.createLine(mLine,
					getAccount(ACCTTYPE_PresAprobado, acctSchema),
					acctSchema.getC_Currency_ID(), null, mLine.getAmtSourceCr());
		} else {
			if (isAmplicacion) {
				// Project DR
				// final FactLine dr =
				fact.createLine(mLine,
						getAccount(ACCTTYPE_PresEjercer, acctSchema),
						acctSchema.getC_Currency_ID(), mLine.getAmtSourceDr(),
						null);

				// Inventory CR
				// final FactLine cr =
				fact.createLine(mLine,
						getAccount(ACCTTYPE_PresModificado, acctSchema),
						acctSchema.getC_Currency_ID(), null,
						mLine.getAmtSourceCr());
			} else {
				// Project DR
				// final FactLine dr =
				fact.createLine(mLine,
						getAccount(ACCTTYPE_PresModificado, acctSchema),
						acctSchema.getC_Currency_ID(), mLine.getAmtSourceDr(),
						null);

				// Inventory CR
				// final FactLine cr =
				fact.createLine(mLine,
						getAccount(ACCTTYPE_PresEjercer, acctSchema),
						acctSchema.getC_Currency_ID(), mLine.getAmtSourceCr()
								.negate(), null);
			}
		}
		//
		final ArrayList<Fact> facts = new ArrayList<Fact>();
		facts.add(fact);
		return facts;
	} // createFact
}
