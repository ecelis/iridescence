package org.compiere.model.bpm;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Bean4String
 * @author twry
 *
 */
public class BeanView {

	private String cadena1 = null; 
	private String cadena2 = null;
	private String cadena3 = null;
	private String cadena4 = null;
	private String cadena5 = null;

	private String color = null;
	private int integer1 = 0;
	private int integer2 = 0;
	private int integer3 = 0;

	@SuppressWarnings({ "rawtypes" })
	private List lst = new ArrayList();
	private BigDecimal dcimal = null;
	private BigDecimal dcimal2 = null;
	private Timestamp timestamp1 = null;
	
	/**
	 * Constructor
	 * @param cad1     DESCRIPCION
	 * @param int1     TAX id
	 * @param decimal1 MONTO
	 * @param decimal2 IMPUESTO
	 */
	public BeanView(final String cad1, final int int1, final BigDecimal decimal1,
			final BigDecimal decimal2) {
		cadena1  = cad1;
		integer1 = int1;
		dcimal   = decimal1;
		dcimal2  = decimal2;
	}
	
	/**
	 * Constructor
	 * @param cad1
	 * @param cad2
	 * @param cad3
	 * @param cad4
	 */
	public BeanView(final String cad1, final String cad2, final String cad3,
			final String cad4) {
		cadena1 = cad1;
		cadena2 = cad2;
		cadena3 = cad3;
		cadena4 = cad4;
	}

	/**
	 * Constructor
	 * @param one
	 * @param iinteger
	 * @param string
	 */
	public BeanView(final BigDecimal one, final int iinteger,
			final String string) {
		cadena1 = string;
		dcimal = one;
		integer1 = iinteger;
	}

	/**
	 * Constructor
	 * @param cad1
	 * @param cad2
	 * @param cad3
	 * @param cad4
	 * @param iinteger
	 */
	public BeanView(final String cad1, final String cad2, final String cad3,
			final String cad4, final int iinteger) {
		cadena1 = cad1;
		cadena2 = cad2;
		cadena3 = cad3;
		cadena4 = cad4;
		integer1 = iinteger;
	}

	public BeanView() {
	}

	public BeanView(BigDecimal bDTotalLines, BigDecimal bDTaxAmt) {
		dcimal = bDTotalLines;
		dcimal2 =  bDTaxAmt;
	}

	public String getCadena5() {
		return cadena5;
	}

	public void setCadena5(final String cadena5) {
		this.cadena5 = cadena5;
	}

	public BigDecimal getDcimal() {
		return dcimal;
	}

	public void setDcimal(final BigDecimal dcimal) {
		this.dcimal = dcimal;
	}

	public int getInteger1() {
		return integer1;
	}

	public void setInteger1(final int integer1) {
		this.integer1 = integer1;
	}

	public String getCadena1() {
		return cadena1;
	}

	public void setCadena1(final String cadena1) {
		this.cadena1 = cadena1;
	}

	public String getCadena2() {
		return cadena2;
	}

	public void setCadena2(final String cadena2) {
		this.cadena2 = cadena2;
	}

	public String getCadena3() {
		return cadena3;
	}

	public void setCadena3(final String cadena3) {
		this.cadena3 = cadena3;
	}

	public String getCadena4() {
		return cadena4;
	}

	public void setCadena4(final String cadena4) {
		this.cadena4 = cadena4;
	}

	public String getColor() {
		return color;
	}

	public void setColor(final String color) {
		this.color = color;
	}

	@SuppressWarnings({ "rawtypes" })
	public List getLst() {
		return lst;
	}

	@SuppressWarnings({ "rawtypes" })
	public void setLst(final List lst) {
		this.lst = lst;
	}

	public int getInteger2() {
		return integer2;
	}

	public void setInteger2(final int integer2) {
		this.integer2 = integer2;
	}
	public Timestamp getTimestamp1() {
		return timestamp1;
	}

	public void setTimestamp1(Timestamp date) {
		this.timestamp1 = date;
	}
	public BigDecimal getDcimal2() {
		return dcimal2;
	}

	public void setDcimal2(BigDecimal dcimal2) {
		this.dcimal2 = dcimal2;
	}
	
	public int getInteger3() {
		return integer3;
	}

	public void setInteger3(int integer3) {
		this.integer3 = integer3;
	}
}
