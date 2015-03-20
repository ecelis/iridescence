/**
 * 
 */
package org.compiere.model;

import java.math.BigDecimal;

import org.compiere.util.Env;

/**
 * Bean para mostrar el inventario
 * @author twry
 * 
 */
public class PresupuestoView {

	/**
	 * Finalidad
	 * 
	 */
	public String strFinalidad = null;
	/**
	 * Funcion
	 * 
	 */
	public String strFuncion = null;
	/**
	 * Funcion
	 * 
	 */
	public String strSubfuncion = null;
	/**
	 * Institucional
	 * 
	 */
	public String strActInstituc = null;
	/**
	 * Programa Presupuestal
	 * 
	 */
	public String strProgPresup = null;
	/**
	 * Programa institucional
	 * 
	 */
	public String strProgInst = null;
	/**
	 * Tipo de gasto
	 * 
	 */
	public String strTipoGasto = null;
	/**
	 * Partida presupuestal
	 * 
	 */
	public String strPartidaPres = null;
	/**
	 * Fuenta de financiamiento
	 * 
	 */
	public String strFteFinan = null;
	/**
	 * Entidad federativa
	 * 
	 */
	public String strRegion = null;
	/**
	 * Presupuesto de egreso
	 * 
	 */
	public String strPresupuestoEgr = null;
	/**
	 * linea
	 * 
	 */
	public int linea = 0;
	/**
	 * Unidad responsables
	 */
	private String strUnidadResp = null;
	/**
	 * Value de reasignacion
	 */
	private String strReasignacion = null;
	/**
	 * Value de proyecto
	 */
	private String strProyecto = null;
	/**
	 * Value de descripcion
	 */
	private String strDescripcion = null;
	/**
	 * Linea de presupuesto
	 */
	private MEXMEPresupuestoDet mPresupuestoDet = null;
	/**
	 * Nivel en que se mostrar la consulta 0. COMO SE MUESTRA EL EXCEL 1. UR
	 * cliente/organizacion 2. FI finalida 3. FU Funcion 4. SF Subfuncion 5. RG
	 * Reasignacion 6. AI Actividad institucional 7. PP Programa presupuestal 8.
	 * PI Programa institucional 9. PTDA Partida 10. TG Tipo de gasto 11. FF
	 * Fuente de Financiamiento 12. EF Entidad federativa 13. PPI Proyecto 14.
	 * LINEA Descripcion
	 */
	private int nivelGrupo = 0;
	/** Nivel en la vista que se muestra */
	private int nivelVista = 0;
	/** montos */
	private BigDecimal amount = Env.ZERO;
	private BigDecimal amountene = Env.ZERO;
	private BigDecimal amountfeb = Env.ZERO;
	private BigDecimal amountmar = Env.ZERO;
	private BigDecimal amountabr = Env.ZERO;
	private BigDecimal amountmay = Env.ZERO;
	private BigDecimal amountjun = Env.ZERO;
	private BigDecimal amountjul = Env.ZERO;
	private BigDecimal amountago = Env.ZERO;
	private BigDecimal amountsep = Env.ZERO;
	private BigDecimal amountoct = Env.ZERO;
	private BigDecimal amountnov = Env.ZERO;
	private BigDecimal amountdic = Env.ZERO;
	/** estado del boton */
	private boolean open = true;
	/** vista */
	private String tipo = null;
	/** id del presupuesto en su linea */
	private int presupuestoDetID = 0;
	/** id del tipo de linea */
	private int idTipo = 0;
	/** id de finalidad */
	private int finalidad = 0;
	/** id de funcion */
	private int funcion = 0;
	/** id de subfuncion */
	private int subFuncion = 0;
	/** id de reasignacióm */
	private int reasignacion = 0;
	/** id de act institucional */
	private int actInstitucional = 0;
	/** id de programa presupuestal */
	private int progPresupuestal = 0;
	/** id de programa institucional */
	private int progInstitucional = 0;

	public int getPresupuestoDetID() {
		return presupuestoDetID;
	}

	public void setPresupuestoDetID(final int presupuestoDetID) {
		this.presupuestoDetID = presupuestoDetID;
	}

	public int getIdTipo() {
		return idTipo;
	}

	public void setIdTipo(final int idTipo) {
		this.idTipo = idTipo;
	}

	public boolean isOpen() {
		return open;
	}

	public void setOpen(final boolean open) {
		this.open = open;
	}

	public int getNivelVista() {
		return nivelVista;
	}

	public void setNivelVista(final int nivelVista) {
		this.nivelVista = nivelVista;
	}

	public MEXMEPresupuestoDet getmPresupuestoDet() {
		return mPresupuestoDet;
	}

	public void setmPresupuestoDet(final MEXMEPresupuestoDet mPresupuestoDet) {
		this.mPresupuestoDet = mPresupuestoDet;
	}

	public BigDecimal getAmtSum() {
		return amount;
	}

	public void setAmtSum(final BigDecimal pamount) {
		if (pamount == null) {
			this.amount = Env.ZERO;
		}
		this.amount = pamount;
	}

	public BigDecimal getAmt1() {
		return amountene;
	}

	public void setAmt1(final BigDecimal pamountene) {
		if (pamountene == null) {
			this.amount = Env.ZERO;
		}
		this.amountene = pamountene;
	}

	public BigDecimal getAmt2() {
		return amountfeb;
	}

	public void setAmt2(final BigDecimal pamountfeb) {
		if (pamountfeb == null) {
			this.amountfeb = Env.ZERO;
		}
		this.amountfeb = pamountfeb;
	}

	public BigDecimal getAmt3() {
		return amountmar;
	}

	public void setAmt3(final BigDecimal pamountmar) {
		if (pamountmar == null) {
			this.amountmar = Env.ZERO;
		}
		this.amountmar = pamountmar;
	}

	public BigDecimal getAmt4() {
		return amountabr;
	}

	public void setAmt4(final BigDecimal pamountabr) {
		if (pamountabr == null) {
			this.amountabr = Env.ZERO;
		}
		this.amountabr = pamountabr;
	}

	public BigDecimal getAmt5() {
		return amountmay;
	}

	public void setAmt5(final BigDecimal pamountmay) {
		if (pamountmay == null) {
			this.amountmay = Env.ZERO;
		}
		this.amountmay = pamountmay;
	}

	public BigDecimal getAmt6() {
		return amountjun;
	}

	public void setAmt6(final BigDecimal pamountjun) {
		if (pamountjun == null) {
			this.amountjun = Env.ZERO;
		}
		this.amountjun = pamountjun;
	}

	public BigDecimal getAmt7() {
		return amountjul;
	}

	public void setAmt7(final BigDecimal pamountjul) {
		if (pamountjul == null) {
			this.amountjul = Env.ZERO;
		}
		this.amountjul = pamountjul;
	}

	public BigDecimal getAmt8() {
		return amountago;
	}

	public void setAmt8(final BigDecimal pamountago) {
		if (pamountago == null) {
			this.amountago = Env.ZERO;
		}
		this.amountago = pamountago;
	}

	public BigDecimal getAmt9() {
		return amountsep;
	}

	public void setAmt9(final BigDecimal pamountsep) {
		if (pamountsep == null) {
			this.amountsep = Env.ZERO;
		}
		this.amountsep = pamountsep;
	}

	public BigDecimal getAmt10() {
		return amountoct;
	}

	public void setAmt10(final BigDecimal pamountoct) {
		if (pamountoct == null) {
			this.amountoct = Env.ZERO;
		}
		this.amountoct = pamountoct;
	}

	public BigDecimal getAmt11() {
		return amountnov;
	}

	public void setAmt11(final BigDecimal pamountnov) {
		if (pamountnov == null) {
			this.amountnov = Env.ZERO;
		}
		this.amountnov = pamountnov;
	}

	public BigDecimal getAmt12() {
		return amountdic;
	}

	public void setAmt12(final BigDecimal pamountdic) {
		if (pamountdic == null) {
			this.amountdic = Env.ZERO;
		}
		this.amountdic = pamountdic;
	}

	public int getNivelGrupo() {
		return nivelGrupo;
	}

	public void setNivelGrupo(final int nivel) {
		this.nivelGrupo = nivel;
	}

	public String getStrReasignacion() {
		return strReasignacion;
	}

	public void setStrReasignacion(final String strReasignacion) {
		this.strReasignacion = strReasignacion;
	}

	public String getStrProyecto() {
		return strProyecto;
	}

	public void setStrProyecto(final String strProyecto) {
		this.strProyecto = strProyecto;
	}

	public String getStrDescripcion() {
		return strDescripcion;
	}

	public void setStrDescripcion(final String strDescripcion) {
		this.strDescripcion = strDescripcion;
	}

	public String getStrUnidadResponsable() {
		return strUnidadResp;
	}

	public void setStrUnidadResponsable(final String strUnidadResponsable) {
		this.strUnidadResp = strUnidadResponsable;
	}

	public int getLinea() {
		return linea;
	}

	public void setLinea(final int linea) {
		this.linea = linea;
	}

	public String getStrFinalidad() {
		return strFinalidad;
	}

	public void setStrFinalidad(final String strFinalidad) {
		this.strFinalidad = strFinalidad;
	}

	public String getStrFuncion() {
		return strFuncion;
	}

	public void setStrFuncion(final String strFuncion) {
		this.strFuncion = strFuncion;
	}

	public String getStrSubfuncion() {
		return strSubfuncion;
	}

	public void setStrSubfuncion(final String strSubfuncion) {
		this.strSubfuncion = strSubfuncion;
	}

	public String getStrActInstitucional() {
		return strActInstituc;
	}

	public void setStrActInstitucional(final String strActInstitucional) {
		this.strActInstituc = strActInstitucional;
	}

	public String getStrProgPresupuestal() {
		return strProgPresup;
	}

	public void setStrProgPresupuestal(final String strProgPresupuestal) {
		this.strProgPresup = strProgPresupuestal;
	}

	public String getStrProgInstitucional() {
		return strProgInst;
	}

	public void setStrProgInstitucional(final String strProgInstitucional) {
		this.strProgInst = strProgInstitucional;
	}

	public String getStrTipoGasto() {
		return strTipoGasto;
	}

	public void setStrTipoGasto(final String strTipoGasto) {
		this.strTipoGasto = strTipoGasto;
	}

	public String getStrPartidaPres() {
		return strPartidaPres;
	}

	public void setStrPartidaPres(final String strPartidaPres) {
		this.strPartidaPres = strPartidaPres;
	}

	public String getStrFteFinanciamiento() {
		return strFteFinan;
	}

	public void setStrFteFinanciamiento(final String strFteFinanciamiento) {
		this.strFteFinan = strFteFinanciamiento;
	}

	public String getStrRegion() {
		return strRegion;
	}

	public void setStrRegion(final String strRegion) {
		this.strRegion = strRegion;
	}

	public String getStrPresupuestoEgr() {
		return strPresupuestoEgr;
	}

	public void setStrPresupuestoEgr(final String strPresupuestoEgr) {
		this.strPresupuestoEgr = strPresupuestoEgr;
	}

	public MEXMEPresupuestoDet getMPresupuestoDet() {
		return mPresupuestoDet;
	}

	public void setMPresupuestoDet(final MEXMEPresupuestoDet mPresupuestoDet) {
		this.mPresupuestoDet = mPresupuestoDet;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(final String tipo) {
		this.tipo = tipo;
	}

	public int getFinalidad() {
		return finalidad;
	}

	public int getFuncion() {
		return funcion;
	}

	public int getSubFuncion() {
		return subFuncion;
	}

	public int getReasignacion() {
		return reasignacion;
	}

	public int getActInstitucional() {
		return actInstitucional;
	}

	public int getProgPresupuestal() {
		return progPresupuestal;
	}

	public int getProgInstitucional() {
		return progInstitucional;
	}

	public void setFinalidad(final int int1) {
		finalidad = int1;
	}

	public void setFuncion(final int int1) {
		funcion = int1;
	}

	public void setSubfuncion(final int int1) {
		subFuncion = int1;
	}

	public void setReasignacion(final int int1) {
		reasignacion = int1;
	}

	public void setActInstitucional(final int int1) {
		actInstitucional = int1;
	}

	public void setProgPresupuestal(final int int1) {
		progPresupuestal = int1;
	}

	public void setProgInstitucional(final int int1) {
		progInstitucional = int1;
	}
}
