package org.compiere.process;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import org.compiere.model.MCtaPacDet;
import org.compiere.util.Trx;

import com.ecaresoft.util.PackageBalance;

/**
 * Reconciliación de paquetes, de uso interno solamente
 * 
 * Revisa todos los cargos de paquetes no facturados activos y ejecuta el match
 * 
 * @author odelarosa
 * 
 */
public class PackageReconciliation extends SvrProcess {

	/**
	 * 
	 */
	public PackageReconciliation() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.compiere.process.SvrProcess#prepare()
	 */
	@Override
	protected void prepare() {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.compiere.process.SvrProcess#doIt()
	 */
	@Override
	protected String doIt() throws Exception {

		List<MCtaPacDet> list = new ArrayList<MCtaPacDet>();//.getPendingPackages(getCtx(), null);

		int count = 0;

		for (MCtaPacDet det : list) {
			Trx trx = null;
			try {
				trx = Trx.get(Trx.createTrxName("paquetes"), true);

				PackageBalance.batchMatch(getCtx(), det.getEXME_CtaPac_ID(), trx.getTrxName());

				Trx.commit(trx);
				count++;
			} catch (Exception ex) {
				log.log(Level.SEVERE, null, ex);
				Trx.rollback(trx);
			} finally {
				Trx.close(trx);
			}
		}

		return count + " de " + list.size();
	}

}
