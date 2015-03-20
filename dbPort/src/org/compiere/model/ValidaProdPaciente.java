package org.compiere.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.util.LabelValueBean;
import org.compiere.util.Utilerias;

/**
 * Datos los parametros busca las validaciones que deben hacerse al hacer alguna
 * actividad con el paciente relacionado a los productos
 * 
 * Valida Alergias Interacciones con los medicamentos antes prescritos Si el
 * producto esta en el almacen Valida si el producto ya a sido agregado
 * 
 * @author Expert
 * 
 */
public class ValidaProdPaciente {
/*
	private Properties ctx = null;
	private List<? extends ServicioView> lstIndicaciones = null;
	private List<MovementLine> lstMovimientos = null;
	private List<? extends MPlanMed> lstPlanMedLine = null;
	private List<BasicoTresProps> lstAlergias = null;
	private List<LabelValueBean> lstAlmacenes = null;
	private ActionErrors errores = new ActionErrors();
	private int pacienteID = 0;
	private int productID = 0;
	private int ctaPacID = 0;
	private String productName = null;
	private int especialidadID = 0;
	private int medicoID = 0;
	private int almacenID = 0;
	private String almacenName = "";

	public ValidaProdPaciente(Properties ctx,
			List<? extends ServicioView> lstIndicaciones,
			List<BasicoTresProps> lstAlergias, int especialidadID,
			int pacienteID, int productID, String productName, int medicoID,
			int almacenID, List<MovementLine> lstMovimientos) {
		this.ctx = ctx;
		this.lstIndicaciones = lstIndicaciones;
		this.lstAlergias = lstAlergias;
		this.especialidadID = especialidadID;
		this.pacienteID = pacienteID;
		this.productID = productID;
		this.productName = productName;
		this.medicoID = medicoID;
		this.almacenID = almacenID;
		this.lstMovimientos = lstMovimientos;

		X_M_Warehouse alma = new X_M_Warehouse(ctx, this.almacenID, null);
		this.almacenName = alma.getName();
	}

	public ValidaProdPaciente(Properties ctx, List<MPlanMed> lstIndicaciones,
			List<BasicoTresProps> lstAlergias, int especialidadID,
			int pacienteID, int productID, String productName, int medicoID,
			int almacenID) {
		this.ctx = ctx;
		this.lstPlanMedLine = lstIndicaciones;
		this.lstAlergias = lstAlergias;
		this.especialidadID = especialidadID;
		this.pacienteID = pacienteID;
		this.productID = productID;
		this.productName = productName;
		this.medicoID = medicoID;
		this.almacenID = almacenID;

		X_M_Warehouse alma = new X_M_Warehouse(ctx, this.almacenID, null);
		this.almacenName = alma.getName();
	}

	public ValidaProdPaciente(Properties ctx, int ctaPac, int paciente,
			int productID, List<? extends MPlanMed> lstPlanMedLine) {
		this.ctx = ctx;
		this.ctaPacID = ctaPac;
		this.pacienteID = paciente;
		this.productID = productID;
		this.lstPlanMedLine = lstPlanMedLine;
	}

	public List<LabelValueBean> getLstAlmacenes() {
		return lstAlmacenes;
	}

	public void setLstAlmacenes(List<LabelValueBean> lstAlmacenes) {
		this.lstAlmacenes = lstAlmacenes;
	}

	/**
	 * Retorna el mensaje de error si un paciente es al�rgico a alg�n
	 * medicamento
	 * 
	 * @return
	 * @throws Exception
	 *
	public String validaAlergiasHistorial() throws Exception {

		if (this.pacienteID > 0
				&& MEXMEPacienteAler.esAlergico(this.ctx, this.productID,
						this.pacienteID)) {
			this.errores
					.add("error", new ActionError("error.citas.esAlergico"));
			return "error.citas.esAlergico";
		}
		return null;
	}

	/**
	 * Si el producto ya esta programado para la misma cuenta
	 * 
	 * @return
	 * @throws Exception
	 *
	public String validaPlanProdEstatus() throws Exception {
		if (MPlanMed.programado(this.ctx, this.ctaPacID, this.productID, null)) {
			this.errores.add("error", new ActionError(
					"error.progMed.programado"));
			return "error.progMed.programado";
		}
		return null;
	}

	/**
	 * Retorna el mensaje de error si un paciente es al�rgico a alg�n
	 * medicamento
	 * 
	 * @return
	 * @throws Exception
	 *
	public void validaAlergias() throws Exception {

		// necesitamos recuperar la lista de sustancias activas del producto
		List<Long> lstSusAct = MProductSActiva.getSusActivas(this.ctx,
				this.productID);

		if (lstSusAct.size() > 0 && this.lstAlergias != null) {
			for (int i = 0; i < this.lstAlergias.size(); i++) {
				for (int j = 0; j < lstSusAct.size(); j++) {
					BasicoTresProps alergia = (BasicoTresProps) lstAlergias
							.get(i);
					if (alergia.getId() == ((Long) lstSusAct.get(j))
							.longValue()) {
						this.errores.add("error", new ActionError(
								"error.citas.esAlergico"));
					}
				}
			}
		}

	}

	/**
	 * Valida que el producto no se haya cargado con anterioridad
	 * 
	 * @return
	 *
	public void validaRepetidosCE() {
		if (this.lstIndicaciones != null) {
			// validar que el producto no se encuentre en la lista
			for (int i = 0; i < this.lstIndicaciones.size(); i++) {
				ServicioView indica = (ServicioView) lstIndicaciones.get(i);
				if (indica.getProdID() == this.productID) {
					this.errores.add("error", new ActionError(
							"error.citas.existeIndi", this.productName));
				}
			}
		}

	}

	/**
	 * Valida que el producto no se haya cargado con anterioridad
	 * 
	 * @return
	 *
	public String validaRepetidosPM() {
		String er = null;
		if (this.lstPlanMedLine != null) {
			// validar que el producto no se encuentre en la lista
			for (int i = 0; i < this.lstPlanMedLine.size(); i++) {
				MPlanMed indica = (MPlanMed) lstPlanMedLine.get(i);
				if (indica.getM_Product_ID() == this.productID) {
					this.errores.add("error", new ActionError(
							"error.citas.existeIndi", this.productName));
					er = "msj.productoDuplicado";
				}
			}
		}
		return er;

	}

	/**
	 * Valida que el producto no se haya cargado con anterioridad
	 * 
	 * @return
	 *
	public void validaRepetidos() {
		if (this.lstMovimientos != null) {
			// validar que el producto no se encuentre en la lista
			for (int i = 0; i < this.lstMovimientos.size(); i++) {
				MovementLine indica = (MovementLine) lstMovimientos.get(i);
				if (indica.getProductID() == this.productID) {
					this.errores.add("error", new ActionError(
							"error.citas.existeIndi", this.productName));
				}
			}
		}

	}

	/**
	 * Valida que el producto este dentro de la especialidad del medico
	 * 
	 * @return
	 * @throws Exception
	 *
	public void validaEspecialidad() throws Exception {
		// validar que el producto no se encuentre en la lista
		List<Integer> especialidades = MEXMEProductEsp.getProdEsp(this.ctx,
				this.productID, null);

		boolean mismaEspecialidad = false;
		int numEspecialidades = 0;

		if (especialidades != null && especialidades.size() > 0) {
			for (int i = 0; i < especialidades.size(); i++) {
				if (especialidades.get(i) == especialidadID) {
					// Es de la especialidad
					mismaEspecialidad = true;
				} else {
					// Tiene mas de una especialidad
					numEspecialidades++;
				}
			}

		}
		// else
		// No pertenece a ninguna especialidad, por lo que lo puede recetar

		if (numEspecialidades > 0 && !mismaEspecialidad)
			this.errores.add("error", new ActionError("error.prodEspecialidad",
					this.productName));

	}

	/**
	 * Valida que un producto exista en almacenes a los que se puede surtir
	 * 
	 * @return
	 * @throws Exception
	 *
	public void validaAlmacenes(String tipoAlmacen, boolean validaAlmacenSurte)
			throws Exception {

		// Validamos almacen que solicita
		if (!MEXMEReplenish.validarProductoAlm(this.ctx, this.productID,
				this.almacenID))
			this.errores.add("error", new ActionError("error.existeProdAlm",
					this.productName, this.almacenName));

		if (validaAlmacenSurte) {
			// Validamos que el producto este en almacenes a los que puede
			// solicitar
			List<LabelValueBean> almacenes = MEXMEReplenish
					.getProductoAlmacenes(this.ctx, this.productID,
							this.almacenID, tipoAlmacen, null);

			if (almacenes != null && almacenes.size() > 0) {
				// Obtenemos los almacenes a los que se puede solicitar
				if (almacenes.size() > 1)
					this.lstAlmacenes = almacenes;

				this.almacen = Integer.parseInt((almacenes.get(0)).getValue());
			} else {
				this.errores.add("error", new ActionError(
						"error.citas.existeIndiAlm", this.productName));
			}
		}
	}

	private int almacen = 0;

	public int getAlmacen() {
		return almacen;
	}

	public void setAlmacen(int almacen) {
		this.almacen = almacen;
	}

	/**
	 * validar que el producto no tenga problemas de nivel de control deacuerdo
	 * al nivel de control permitido para el medico
	 * 
	 * @return
	 * @throws Exception
	 *
	public void validaMedControlado() throws Exception {
		// validar que el producto no tenga problemas de nivel de control de
		// acuerdo al nivel de control
		// permitido para el m�dico
		MEXMEMedico medico = new MEXMEMedico(this.ctx, this.medicoID, null);
		MProduct producto = new MProduct(this.ctx, this.productID, null);
		if (medico != null && producto != null) {
			// 6 >= 1; 2>=1; 3>=2; 2<= 6
			if (medico.nivelControl() == 6 && producto.nivelControl() <= 3) {
				// Error no se puede recetar
				this.errores.add("error", new ActionError(
						"error.niveldeControl", this.productName));
			}
			if (medico.nivelControl() == 2 && producto.nivelControl() == 1) {
				// Error no se puede recetar
				this.errores.add("error", new ActionError(
						"error.niveldeControl", this.productName));
			}
			if (medico.nivelControl() == 3 && producto.nivelControl() <= 2) {
				// Error no se puede recetar
				this.errores.add("error", new ActionError(
						"error.niveldeControl", this.productName));
			}
		} else {
			this.errores.add("error", new ActionError("error.niveldeControl",
					this.productName));
		}

	}

	public void validaInteraccion() throws Exception {
		if (this.lstIndicaciones != null) {
			List<BasicoTresProps> list = MEXMEInteraccion.descripInteraccion(
					this.ctx, this.productID, null);

			for (int i = 0; i < this.lstIndicaciones.size(); i++) {
				for (int j = 0; j < list.size(); j++) {
					if (lstIndicaciones.get(i).getProdID() == list.get(j)
							.getId())
						this.errores.add("error", new ActionError(list.get(j)
								.getNombre()));
				}
			}
		}// fin for
	}

	public String validaInteraccionMov() throws Exception {
		List<BasicoTresProps> list = MEXMEInteraccion.descripInteraccion(
				this.ctx, this.productID, null);
		String dm = null;
		if (this.lstMovimientos != null) {
			for (int i = 0; i < this.lstMovimientos.size(); i++) {

				for (int j = 0; j < list.size(); j++) {
					if (lstMovimientos.get(i).getProductID() == list.get(j)
							.getId()) {
						this.errores.add("error", new ActionError(list.get(j)
								.getNombre()));
						dm = dm + " " + list.get(j).getNombre();
					}
				}
			}
		}
		return dm;

	}

	/**
	 * Valida las interacciones para los medicamentos en la planeacion de
	 * medicamentos
	 * 
	 * @throws Exception
	 *
	public void validaInteraccionPlan() throws Exception {
		List<BasicoTresProps> list = MEXMEInteraccion.descripInteraccion(
				this.ctx, this.productID, null);
		if (this.lstPlanMedLine != null) {
			for (int i = 0; i < this.lstPlanMedLine.size(); i++) {

				for (int j = 0; j < list.size(); j++) {
					if (lstPlanMedLine.get(i).getProductID() == list.get(j)
							.getId())
						this.errores.add("error", new ActionError(list.get(j)
								.getNombre()));
				}
			}
		}
	}

	/**
	 * Hace las validaciones respectivas para agregar un producto que requiere
	 * el m�dico hacia un paciente desde la receta individual
	 * 
	 * @param ctx
	 * @param lstIndicaciones
	 * @param lstAlergias
	 * @param especialidadID
	 * @param pacienteID
	 * @param productID
	 * @param productName
	 * @return
	 * @throws Exception
	 *
	public ActionErrors validaRecetaIndividual() throws Exception {
		validaAlergiasHistorial();
		validaInteraccion();
		validaAlmacenes(X_M_Warehouse.TIPOALMACEN_IndividualPrescription, true);
		validaEspecialidad();
		validaRepetidosCE();
		validaMedControlado();

		return this.errores;
	}

	public ActionErrors validaMedicationList() throws Exception {
		validaAlergiasHistorial();
		validaInteraccion();
		// validaAlmacenes(X_M_Warehouse.TIPOALMACEN_IndividualPrescription,
		// false);
		validaEspecialidad();
		validaRepetidosCE();
		validaMedControlado();

		return this.errores;
	}

	public ActionErrors validaConsultaExterna(boolean validaEspecialidad,
			String tipoAlmacen) throws Exception {
		validaAlergias();
		validaInteraccion();
		validaRepetidosCE();
		validaMedControlado();
		validaAlmacenes(tipoAlmacen, true);
		if (validaEspecialidad)
			validaEspecialidad();

		return this.errores;
	}

	public ActionErrors validaRecetaColectiva() throws Exception {
		validaAlmacenes(X_M_Warehouse.TIPOALMACEN_CollectivePrescription, true);

		return this.errores;
	}

	public ActionErrors validaSolicitudProd(boolean validRepetidos)
			throws Exception {
		validaAlmacenes(null, true);
		validaAlergiasHistorial();
		validaInteraccionMov();
		if (validRepetidos)
			validaRepetidos();

		return this.errores;
	}

	/*
	 * pertenesca al almacen alergico ya este programado que ya ete en la lista
	 * 
	 * public ActionErrors validaProgramacionProd(boolean validRepetidos) throws
	 * Exception { validaAlmacenes(null, false); validaAlergiasHistorial();
	 * validaInteraccionMov(); //FIXME : Ver si es correcto. Este es para
	 * movimientos if(validRepetidos) validaRepetidosPM(); //FIXME : Este es
	 * para una programacion return this.errores; }
	 *
	public List<String> validaProgProdCta(boolean validRepetidos)
			throws Exception {
		List<String> lista = new ArrayList<String>();
		String er = validaAlergiasHistorial();
		if (er != null)
			lista.add(er);

		er = validaPlanProdEstatus();
		if (er != null)
			lista.add(er);

		er = validaInteraccionMov();
		if (er != null)
			lista.add(er);

		if (validRepetidos) {
			er = validaRepetidosPM();
			if (er != null)
				lista.add(er);
		}
		// return this.errores;
		return lista;
	}

	/**
	 * Devuelve el error en un bean
	 * 
	 * @return
	 *
	public List<ModelError> getErrores() {
		List<ModelError> lstError = new ArrayList<ModelError>();
		lstError.add(new ModelError(ModelError.TIPOERROR_Error, Utilerias
				.getErrors(this.errores).toString()));
		return lstError;
	}

	/**
	 * Devuelve el error en un bean
	 * 
	 * @return
	 *
	public List<ModelError> getErrores(ActionErrors thiserrores) {
		List<ModelError> lstError = new ArrayList<ModelError>();
		if (thiserrores.size() > 0)
			lstError.add(new ModelError(ModelError.TIPOERROR_Error, Utilerias
					.getErrors(thiserrores).toString()));
		return lstError;
	}*/
}
