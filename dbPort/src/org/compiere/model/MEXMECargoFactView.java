/*
 * Derechos Reservados (c) a partir 2005 Expert Sistemas Computacionales, S. A. de C. V.
 * Sistema MedSys
 */
/*

 * Created on 16-feb-2005

 *

 * TODO To change the template for this generated file go to

 * Window - Preferences - Java - Code Style - Code Templates

 */

package org.compiere.model;

import java.math.BigDecimal;

import org.compiere.util.Env;



/**
 * 
 * @author hassan reyes
 *
 *	Reprecenta un cargo en la lista de cargos.
 */

public class MEXMECargoFactView {

    private int id_1 = 0;
    private int id_2 = 0;
    private int id_3 = 0;
	private String cadena_1 = null;
	private BigDecimal monto_1 = Env.ZERO;
	private BigDecimal monto_2 = Env.ZERO;
	
	public BigDecimal getMonto_2() {
		return monto_2;
	}
	public void setMonto_2(BigDecimal monto_2) {
		this.monto_2 = monto_2;
	}
	public int getId_1() {
		return id_1;
	}
	public void setId_1(int id_1) {
		this.id_1 = id_1;
	}
	public int getId_2() {
		return id_2;
	}
	public void setId_2(int id_2) {
		this.id_2 = id_2;
	}
	public int getId_3() {
		return id_3;
	}
	public void setId_3(int id_3) {
		this.id_3 = id_3;
	}
	public String getCadena_1() {
		return cadena_1;
	}
	public void setCadena_1(String cadena_1) {
		this.cadena_1 = cadena_1;
	}
	public BigDecimal getMonto_1() {
		return monto_1;
	}
	public void setMonto_1(BigDecimal monto_1) {
		this.monto_1 = monto_1;
	}
	
}



