package org.compiere.model;

import java.sql.ResultSet;
import java.util.List;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.compiere.util.Utilerias;

public class MI_EXME_ListaPrecio extends X_I_EXME_ListaPrecio {

	private static final long	serialVersionUID	= 1L;

	public MI_EXME_ListaPrecio(final Properties ctx, final int I_EXME_ListaPrecio_ID, final String trxName) {
		super(ctx, I_EXME_ListaPrecio_ID, trxName);
	}

	public MI_EXME_ListaPrecio(final Properties ctx, final ResultSet rs, final String trxName) {
		super(ctx, rs, trxName);
	}

	/**
	 * Borra los registros que ya han sido importados satisfactoriamente
	 * 
	 * @param ctx
	 * @param m_AD_Client_ID
	 */
	public static void deleteImported(final Properties ctx, final int m_AD_Client_ID) {
		final List<X_I_EXME_ListaPrecio> list = new Query(ctx, I_I_EXME_ListaPrecio.Table_Name, " I_IsImported = 'Y' AND AD_Client_ID="
				+ (m_AD_Client_ID <= 0 ? Env.getAD_Client_ID(ctx) : m_AD_Client_ID), null).list();
		for (final X_I_EXME_ListaPrecio iPrice : list) {
			iPrice.delete(true);
		}
	}

	/**
	 * Metodo que analiza los campos de nombre y id que corresponden
	 * M_PriceList, M_PriceList_Version y M_Product para calcular sus
	 * respectivos valores. <br/>
	 * 
	 * El calculo se realiza siguiendo las estas prioridades y suponiendo que
	 * alguno de los dos campos (Nombre o ID) no es nulo en caso contrario
	 * lanzara una {@link IllegalArgumentException} indicandolo
	 * <ol>
	 * <li>Si el ID no se encuentra, se cacula el ID apartir del nombre</li>
	 * <li>SI el Nombre es nulo o vacio, se calcula el nombre apartir del ID</li>
	 * </ol>
	 * 
	 * 
	 * @throws IllegalArgumentException
	 *             En caso de que algun calculo no pueda ser realizado por falta
	 *             de argumentos
	 */
	public void prepareToImport() throws IllegalArgumentException {
		// M_PriceList --------------------------
		if (getM_PriceList_ID() <= 0) {
			if (StringUtils.isBlank(getM_PriceList_Name())) {// Msg.translate(ctx,
																// columnName)
				throw new IllegalArgumentException(Utilerias.getAppMsg(
						getCtx(), "import.price.error.NoSelectionNoName", Msg.translate(getCtx(), I_I_EXME_ListaPrecio.COLUMNNAME_M_PriceList_ID),
						Msg.translate(getCtx(), I_I_EXME_ListaPrecio.COLUMNNAME_M_PriceList_Name)));
			} else {
				final int id = MPriceList.get(getCtx(), getM_PriceList_Name(), null);
				if (id > 0) {
					setM_PriceList_ID(id);
				} else {
					throw new IllegalArgumentException(Utilerias.getAppMsg(
							getCtx(), "import.price.error.NoRegistred", getM_PriceList_Name(),
							Msg.translate(getCtx(), I_I_EXME_ListaPrecio.COLUMNNAME_M_PriceList_ID)));
				}
			}
		} else {
			setM_PriceList_Name(getM_PriceList().getName());
		}
		// M_PriceList_Version ---------------------
		if (getM_PriceList_Version_ID() <= 0) {
			if (StringUtils.isBlank(getM_PriceList_Version_Name())) {
				throw new IllegalArgumentException(Utilerias.getAppMsg(
						getCtx(), "import.price.error.NoSelectionNoName",
						Msg.translate(getCtx(), I_I_EXME_ListaPrecio.COLUMNNAME_M_PriceList_Version_ID),
						Msg.translate(getCtx(), I_I_EXME_ListaPrecio.COLUMNNAME_M_PriceList_Version_Name)));
			} else {
				final int id = MPriceListVersion.findByName(getCtx(), getM_PriceList_Version_Name(), null);
				if (id > 0) {
					setM_PriceList_Version_ID(id);
				} else {
					throw new IllegalArgumentException(Utilerias.getAppMsg(
							getCtx(), "import.price.error.NoRegistred", getM_PriceList_Version_Name(),
							Msg.translate(getCtx(), I_I_EXME_ListaPrecio.COLUMNNAME_M_PriceList_Version_ID)));
				}
			}
		} else {
			setM_PriceList_Version_Name(getM_PriceList_Version().getName());
		}

		// M_Product ----------------------------
		if (getM_Product_ID() <= 0) {
			if (StringUtils.isBlank(getM_Product_Value())) {
				throw new IllegalArgumentException(Utilerias.getAppMsg(
						getCtx(), "import.price.error.NoSelectionNoName", Msg.translate(getCtx(), I_I_EXME_ListaPrecio.COLUMNNAME_M_Product_ID),
						Msg.translate(getCtx(), I_I_EXME_ListaPrecio.COLUMNNAME_M_Product_Value)));
			} else {
				final int id = MProduct.getIDByValue(getM_Product_Value());
				if (id > 0) {
					setM_Product_ID(id);
				} else {
					throw new IllegalArgumentException(Utilerias.getAppMsg(
							getCtx(), "import.price.error.NoRegistred", getM_Product_Value(),
							Msg.translate(getCtx(), I_I_EXME_ListaPrecio.COLUMNNAME_M_Product_ID)));
				}
			}
		} else {
			setM_Product_Value(getM_Product().getValue());
		}
		
		// OUM minima desde el producto -----------------------------
		setC_UOM_ID(getM_Product().getC_UOM_ID());
	}

}