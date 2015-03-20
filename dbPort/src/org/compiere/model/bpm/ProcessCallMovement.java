/**
 * 
 */
package org.compiere.model.bpm;

import java.util.Properties;

import org.compiere.util.Trx;

/**
 * @author twry
 *  @deprecated
 */
public interface ProcessCallMovement {
	public boolean startProcess (Properties ctx, Trx trx);
}
