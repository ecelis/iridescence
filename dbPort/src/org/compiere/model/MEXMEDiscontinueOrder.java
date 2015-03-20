/**
 * 
 */
package org.compiere.model;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;

import org.adempiere.exceptions.MedsysException;
import org.apache.commons.lang.StringUtils;
import org.compiere.process.Worklist;
import org.compiere.util.Constantes;
import org.compiere.util.Env;

/**
 * <b>Discontinuation Order</b>
 * <p>
 * Contains who and when requested to discontinue an order, who and when authenticated that order.<br>
 * Can be used for any po order model (meds, services, diets, other)<br>
 * The reference record (po) must implement the {@link Worklist} & {@link IDiscontinueOrder} interfaces.<br>
 * Each un-authenticated record will be a pending activity for the physician.
 * </p>
 * Project: Orders (Wiki: /projects/compiere-all/wiki/Orders)<br>
 * <a src="http://control.ecaresoft.com/card/746">Card #746 - Authentication of Discontinuation Orders</a>
 * 
 * @author Lorena Lama
 */
public class MEXMEDiscontinueOrder extends X_EXME_DiscontinueOrder implements Worklist {

	/**
	 * Discontinue order parameters.<br>
	 * <i>Internal class used to avoid static method with excessive parameters</i>
	 * 
	 * @author Lorena Lama
	 */
	public static class DiscontinueParams {
		private int						medicoId;
		private String					orderType;
		private String					readback;
		private String					comments;
		private final Timestamp			date;
		private final IDiscontinueOrder	poModel;
		private int						motivoCancel;
		private MEXMEDiscontinueOrder	discOrder;

		/**
		 * @param poModel PO order model to discontinue (mandatory)
		 * @param date Discontinuation date. Reference {@link X_EXME_DiscontinueOrder#setDiscontinuedDate(Timestamp)}
		 * @throws NullPointerException if date or poModel are <b>null</b>
		 */
		public DiscontinueParams(final IDiscontinueOrder poModel, final Timestamp date) {
			if (date == null || poModel == null || poModel.get_ID() <= 0) {
				throw new IllegalArgumentException();
			}
			this.poModel = poModel;
			this.date = date;
		}

		/** @return Comments of discontinuation. Reference {@link X_EXME_DiscontinueOrder#getComments()} */
		public String getComments() {
			return comments;
		}

		/** @return Discontinuation date. Reference {@link X_EXME_DiscontinueOrder#getDiscontinuedDate()} */
		public Timestamp getDate() {
			return date;
		}

		/** @return Creates the {@link MEXMEDiscontinueOrder} model with the given parameters */
		public MEXMEDiscontinueOrder getFrom() {
			if (discOrder == null) {
				discOrder = new MEXMEDiscontinueOrder(poModel.getCtx(), 0, null);
			}
			discOrder.setAD_Table_ID(poModel.get_Table_ID());
			discOrder.setRecord_ID(poModel.get_ID());
			discOrder.setEXME_Medico_ID(medicoId);
			discOrder.setOrderType(StringUtils.trimToNull(orderType));
			discOrder.setDiscontinuedDate(date);
			discOrder.setComments(comments);
			if (readback != null) {
				discOrder.setReadBack(readback);
			}
			return discOrder;
		}

		/** @return Prescriber. Reference {@link X_EXME_DiscontinueOrder#getEXME_Medico_ID()} */
		public int getMedicoId() {
			return medicoId;
		}

		/** @return Cancellation motive {@link X_EXME_MotivoCancel} used for some orders */
		public int getMotivoCancel() {
			return motivoCancel;
		}

		/** @param comments Coments of discontinuation. Reference: {@link X_EXME_DiscontinueOrder#setComments(String)} */
		public void setComments(final String comments) {
			this.comments = comments;
		}

		/** @param medicoId Prescriber. Reference: {@link X_EXME_DiscontinueOrder#setEXME_Medico_ID(int)} */
		public void setMedicoId(final int medicoId) {
			this.medicoId = medicoId;
		}

		/** @param motivoCancel Cancellation motive {@link X_EXME_MotivoCancel} used for some orders */
		public void setMotivoCancel(final int motivoCancel) {
			this.motivoCancel = motivoCancel;
		}

		/** @param orderType Type or order. Reference: {@link X_EXME_DiscontinueOrder#setOrderType(String)} */
		public void setOrderType(final String orderType) {
			this.orderType = orderType;
		}

		/** @param readback If the order was read back to the prescriber. Reference: {@link X_EXME_DiscontinueOrder#setReadBack(String))} */
		public void setReadback(final String readback) {
			this.readback = readback;
		}

		/**
		 * Creates the discontinue order, and executes the {@link IDiscontinueOrder#discontinue(String, DiscontinueParams)} method of the order
		 * (meds/services/diets/other) to discontinue
		 * 
		 * @param trxName Transaction name
		 */
		public void startProcess(final String trxName) {
			if (getFrom().save(trxName)) {
				discOrder.authenticate(trxName);
				poModel.discontinue(trxName, this);
			}
		}
	}

	/**
	 * Interface used PO order model (meds, services, diets, other).<br>
	 * Requires basic PO -existing- methods
	 * 
	 * @author Lorena Lama
	 */
	public static interface IDiscontinueOrder {

		/**
		 * Discontinues a medication/diet/service/other order.
		 * 
		 * @param trxName Transaction Name
		 * @param params DiscontinueOrder parameters (Prescriber, order type, read back (when applies), comments, etc)
		 */
		void discontinue(String trxName, DiscontinueParams params);

		/** Record Id. References {@link PO#get_ID()}, used to {@link X_EXME_DiscontinueOrder#setRecord_ID(int)} */
		int get_ID();

		/** Record Id. References {@link PO#get_Table_ID()}, used to {@link X_EXME_DiscontinueOrder#setAD_Table_ID(int)} */
		int get_Table_ID();

		/** Context References {@link PO#getCtx()} */
		Properties getCtx();

		/** @return true if the discontinue order has an authentication date */
		boolean isAuthenticatedDisc();
	}

	private static final long	serialVersionUID	= 1L;

	/**
	 * Authenticates the discontinuation of an order
	 * 
	 * @param poFrom discontinued order (mandatory)
	 * @param trxName Transaction name
	 * @throws NullPointerException when poFrom is null
	 */
	public static MEXMEDiscontinueOrder authenticate(final PO poFrom, final String trxName) {
		final MEXMEDiscontinueOrder disc = getFrom(poFrom.getCtx(), poFrom.get_Table_ID(), poFrom.get_ID(), trxName);
		if (disc != null) {
			disc.authenticate(trxName);
		}
		return disc;
	}

	/**
	 * @param poFrom PO order model
	 * @return The discontinue order related to a PO model.
	 * @throws NullPointerException when poFrom is null
	 */
	public static MEXMEDiscontinueOrder getFrom(final PO poFrom) {
		return getFrom(poFrom.getCtx(), poFrom.get_Table_ID(), poFrom.get_ID(), poFrom.get_TrxName());
	}

	/**
	 * @param ctx Context
	 * @param adTableId Table identifier where the record is stored
	 * @param recordId Record identifier
	 * @param trxName Transaction name
	 * @return The discontinue order related to an specific record.
	 */
	public static MEXMEDiscontinueOrder getFrom(final Properties ctx, final int adTableId, final int recordId, final String trxName) {
		return new Query(ctx, Table_Name, " AD_Table_ID=? AND Record_ID=? ", trxName)//
			.setParameters(adTableId, recordId)//
			.setOnlyActiveRecords(true)//
			.addAccessLevelSQL(true)//
			.first();
	}

	/**
	 * @param poFrom PO order model
	 * @return true if the discontinue order has an authentication date or doesn't exist
	 * @throws NullPointerException when poFrom is null
	 */
	public static boolean isAuthenticatedDisc(final PO poFrom) {
		final MEXMEDiscontinueOrder discOrder = getFrom(poFrom);
		return discOrder == null || discOrder.getAuthenticated_Date() != null;
	}

	private PO	poFrom;

	/**
	 * @param ctx Context
	 * @param EXME_DiscontinueOrder_ID Primary Key
	 * @param trxName Transaction name
	 */
	public MEXMEDiscontinueOrder(final Properties ctx, final int EXME_DiscontinueOrder_ID, final String trxName) {
		super(ctx, EXME_DiscontinueOrder_ID, trxName);
	}

	/**
	 * @param ctx Context
	 * @param rs Result set
	 * @param trxName Transaction name
	 */
	public MEXMEDiscontinueOrder(final Properties ctx, final ResultSet rs, final String trxName) {
		super(ctx, rs, trxName);
	}

	/**
	 * Authenticates the discontinuation of an order
	 * 
	 * @param trxName Transaction name
	 */
	public void authenticate(final String trxName) {
		// si no esta autenticado, y el usuario es medico, o la orden esta por escrito
		if (getAuthenticated_Date() == null && (Env.getEXME_Medico_ID(getCtx()) > 0 || ORDERTYPE_WrittenOrder.equals(getOrderType()))) {
			setAuthenticated_Date(Env.getCurrentDate());
			setAuthenticatedBy(MEXMEMedico.getUserId(getCtx(), getEXME_Medico_ID(), true));
			if (!save(trxName)) {
				throw new MedsysException();
			}
		}
	}

	/** @return The patient name of the associated order */
	@Override
	public String getPatientName() {
		String retValue;
		if (getPoFrom() instanceof Worklist) {
			retValue = ((Worklist) getPoFrom()).getPatientName();
		} else {
			retValue = StringUtils.EMPTY;
		}
		return retValue;
	}

	/** @return The PO order model associated */
	public PO getPoFrom() {
		if (poFrom == null && getAD_Table_ID() > 0 && getRecord_ID() > 0) {
			final MTable table = MTable.get(getCtx(), getAD_Table_ID());
			if (table != null) {
				poFrom = new Query(getCtx(), table, table.getTableName() + "_ID=? ", get_TrxName())//
					.setParameters(getRecord_ID())//
					.first();
			}
		}
		return poFrom;
	}

	/** @return Read back label: Yes / No */
	public String getReadBackStr() {
		return MEXMECuidadosPac.getReadBackLabel(this);
	}

	@Override
	public String getRecordType() {
		String retValue;
		if (getPoFrom() instanceof Worklist) {
			retValue = ((Worklist) getPoFrom()).getRecordType();
		} else {
			retValue = getMsgTranslate(COLUMNNAME_EXME_DiscontinueOrder_ID);
		}
		return retValue;
	}

	@Override
	public String getSummaryDetail() {
		final StringBuilder str = new StringBuilder();
		if (getPoFrom() instanceof Worklist) {
			str.append(((Worklist) getPoFrom()).getSummaryDetail());
			if (str.length() > 0) {
				str.append("<br>**************<br>");
			}
		}
		if (getDiscontinuedDate() != null) {
			str.append("<b>").append(getMsgTranslate("Discontinued")).append("</b><br>");
			str.append(Constantes.getSDFDateTime(getCtx()).formatConvert(getDiscontinuedDate()));
			if (getEXME_Medico_ID() > 0) {
				str.append(", ");
				str.append(MEXMEMedico.nombreMedico(getCtx(), getEXME_Medico_ID()));
			}
		}

		// order type
		if (StringUtils.isNotBlank(getOrderType())) {
			str.append("<br>(<b>").append(getOrderType()).append("</b>");
			if (READBACK_Yes.equals(getReadBack())) {
				str.append(" ").append(getMsgTranslate(COLUMNNAME_ReadBack));
			}
			str.append(")");
		}
		if (StringUtils.isNotBlank(getComments())) {
			str.append("<br><i>").append(getComments()).append("</i>");
		}
		return str.toString();
	}

	/** @return true if has an authentication date */
	public boolean isAuthenticated() {
		return getAuthenticated_Date() != null;
	}

	@Override
	public boolean setAction(final String action) {
		if (ACTION_AUTHENTICATE.equals(action) && Env.getEXME_Medico_ID(getCtx()) > 0) {
			authenticate(get_TrxName());
		}
		return true;
	}
	
	/**  @return 	OrderType */
	public String getOrderTypeStr(){
		return MRefList.getListName(getCtx(), X_EXME_DiscontinueOrder.ORDERTYPE_AD_Reference_ID, getOrderType());
	}

	@Override
	public Object onCompare(int type) {
		Object comparable;
		if (type == COLUMNNAME_Authenticated_Date.hashCode()) {
			comparable = getAuthenticated_Date();
		} else if (type == COLUMNNAME_AuthenticatedBy.hashCode()) {
			comparable = getAuthenticatedBy();
		} else if (type == COLUMNNAME_DiscontinuedDate.hashCode()) {
			comparable = getDiscontinuedDate();
		} else if (type == COLUMNNAME_Comments.hashCode()) {
			comparable = getComments();
		} else if (type == COLUMNNAME_EXME_Medico_ID.hashCode()) {
			comparable = getEXME_Medico().getNombre_Med();
		} else if (type == COLUMNNAME_OrderType.hashCode()) {
			comparable = getOrderTypeStr();
		} else if (type == COLUMNNAME_ReadBack.hashCode()) {
			comparable = getReadBackStr();
		} else {
			comparable = super.onCompare(type);
		}
		return comparable;
	}
	
}
