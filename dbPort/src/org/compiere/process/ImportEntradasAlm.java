package org.compiere.process;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.model.EntradasAlm;
import org.compiere.model.MBPartner;
import org.compiere.model.MConfigEC;
import org.compiere.model.MEXMECalculoImpuestos;
import org.compiere.model.MEXMEConfigInt;
import org.compiere.model.MEXMEDocType;
import org.compiere.model.MEXMEEstServ;
import org.compiere.model.MEXMELookupInfo;
import org.compiere.model.MEXMELot;
import org.compiere.model.MEXMETax;
import org.compiere.model.MTax;
import org.compiere.model.OrderLine;
import org.compiere.model.X_EXME_ConfigInt;
import org.compiere.util.DB;
import org.compiere.util.Env;

public class ImportEntradasAlm extends SvrProcess {
	
	
	private int		p_AD_Client_ID = -1;
	//private int		p_AD_User_ID = -1;
	private int		p_AD_Org_ID = -1;
	
	protected String doIt() throws Exception {
		
		log.info("Ejecutando importacion de entradas almacen desde tabla interfaz");
		
		Env.setContext(getCtx(), "#AD_Client_ID", p_AD_Client_ID);
		
		Env.setContext(getCtx(), "#AD_Org_ID", p_AD_Org_ID);
		
		int cDocTypeID = -1;
		
        //asignamos el id del almacen (el primero de la lista)
        cDocTypeID= MEXMEDocType.getOfDocBaseTypeMinID(Env.getCtx(),MEXMEDocType.DOCBASETYPE_PurchaseOrder);
		String fechaMovimiento = null;
		int	 cBPartnerID = getcBPartner();
		
		MConfigEC config =  MConfigEC.get(Env.getCtx(), null);
		if (config!= null){
			cBPartnerID = config.getC_BPartner_ID();
		}else{
			return "No existe Socio de Negocios en Configuracion de Expediente Cl#nico";
		}
			
		int  mWareHouse = -1;
		int  priceListID = -1;
		MBPartner partner = new MBPartner(getCtx(),cBPartnerID,null);
		int adOrgTrxID = 0;
		
        //validar que el socio exista en la tabla
        if(partner==null) {

        	
            return "Error CBPartner default no existe";
        }
        
        //validamos que el socio de negocios tenga una lista de precios de compra asociada
        if(partner.getPO_PriceList_ID() > 0){
        	//Datos de la lista de precios
        	priceListID = partner.getPO_PriceList_ID();
        } else {
        	
        	priceListID = getPriceList(getCtx());
        	if (priceListID <= 0){
        		
        		return "Error PriceList default no existe";
        		
        	}
        }
		
        partner = null;
        
        
        PreparedStatement pstmt = null;
		ResultSet rsEntrada	 	= null;
		
		StringBuilder sqlEntrada = null;
		sqlEntrada = new StringBuilder("SELECT DISTINCT SE.NO_ENTRADA ");
		sqlEntrada.append("FROM SM_ENTRADA SE " );
		sqlEntrada.append("WHERE SE.TRANSFERIDO = 'N' ");
		
		ArrayList<String> listaEntradas = new ArrayList<String>();
		
		try{
			pstmt = DB.prepareStatement(sqlEntrada.toString(), null);
			rsEntrada = pstmt.executeQuery();
			while (rsEntrada.next()){
				listaEntradas.add(rsEntrada.getString(1));
			}
		}catch(Exception e){
			e.printStackTrace();
			return e.getMessage();
		}finally{
			try {
				if (pstmt != null)
					pstmt.close();
				if (rsEntrada != null)
					rsEntrada.close();
			} catch (Exception ex) {
				log.log(Level.SEVERE, sqlEntrada.toString(), ex.getMessage());
			}
			sqlEntrada = null;
			pstmt = null;
			rsEntrada = null;
		}
		
		for (String documentNo : listaEntradas){
			List<OrderLine> lineasEntrada = new ArrayList<OrderLine>();
			try{
				
				sqlEntrada = new StringBuilder("SELECT MP.M_PRODUCT_ID, MP.NAME, MP.VALUE, CU.C_UOM_ID, CU.NAME, ");
				sqlEntrada.append("SE.CANTIDAD, SE.PRICEACTUAL, SE.FECHA_MOV,MW.M_WAREHOUSE_ID, AU.AD_USER_ID, ");
				sqlEntrada.append("SE.LOT, TO_CHAR(SE.GUARANTEEDATE,'dd/MM/yyyy'), SE.SM_ENTRADA_ANIO, SE.SM_ENTRADA_ID ");
				sqlEntrada.append(" "); 
				sqlEntrada.append("FROM SM_ENTRADA SE " );
				sqlEntrada.append("INNER JOIN M_PRODUCT MP " );
				
				MEXMEConfigInt configInt = MEXMEConfigInt.get(getCtx(), null,null);
				if (configInt.getInterfase_Inventarios().equalsIgnoreCase(X_EXME_ConfigInt.INTERFASE_INVENTARIOS_Sicome)){
					sqlEntrada.append("		ON MP.AD_CLIENT_ID = ? AND MP.M_PRODUCT_ID = SE.PRODUCTO_VALUE ");
				}else if(configInt.getInterfase_Inventarios().equalsIgnoreCase(X_EXME_ConfigInt.INTERFASE_INVENTARIOS_SIA)){
					sqlEntrada.append("		ON MP.AD_CLIENT_ID = ? AND MP.SKU = SE.PRODUCTO_VALUE ");
				}
				
				sqlEntrada.append("INNER JOIN M_WAREHOUSE MW ");
				if (configInt.getInterfase_Inventarios().equalsIgnoreCase(X_EXME_ConfigInt.INTERFASE_INVENTARIOS_Sicome)){
					sqlEntrada.append("		ON MW.AD_CLIENT_ID = ? AND MW.VALUE = SE.WHS_VALUE ");
				}else if(configInt.getInterfase_Inventarios().equalsIgnoreCase(X_EXME_ConfigInt.INTERFASE_INVENTARIOS_SIA)){
					sqlEntrada.append("		ON MW.AD_CLIENT_ID = ? AND MW.DESCRIPTION = SE.WHS_VALUE ");
				}
				configInt = null;
				
				
				sqlEntrada.append("INNER JOIN C_UOM CU ");
				sqlEntrada.append("		ON CU.AD_CLIENT_ID = ? AND CU.NAME = SE.UOM_VALUE ");
				sqlEntrada.append("LEFT JOIN AD_USER AU ON AU.NAME = UPPER(SE.USUARIO_NAME) ");
				sqlEntrada.append("WHERE SE.NO_ENTRADA  = ? ");
				
				pstmt = DB.prepareStatement(sqlEntrada.toString(), null );
				pstmt.setInt(1, p_AD_Client_ID);
				pstmt.setInt(2, p_AD_Client_ID);
				pstmt.setInt(3, p_AD_Client_ID);
				pstmt.setString(4, documentNo);
				rsEntrada = pstmt.executeQuery();
				
				while (rsEntrada.next()){
					OrderLine linea = new OrderLine();
					//Asignamos el impuesto a la forma
					MTax tax = MEXMETax.getImpuestoProducto(getCtx(), 
							rsEntrada.getInt(1),null);
					
					linea.setProductID(rsEntrada.getInt(1));
					linea.setProdName(rsEntrada.getString(2));
					linea.setValue(rsEntrada.getString(3));
					linea.setUOM(rsEntrada.getInt(4));
					linea.setUomName(rsEntrada.getString(5));
					linea.setOriginalQty(rsEntrada.getInt(6));
					linea.setCostoUnit(rsEntrada.getDouble(7));
					if(tax!=null){
						linea.setTaxID(tax.getC_Tax_ID());
						linea.setTaxName(tax.getName());
					}
					StringBuilder loteInfo = new StringBuilder();
					loteInfo.append(rsEntrada.getString(11)).append(MEXMELot.DELIMITADOR);
					loteInfo.append("");//descripcion
					loteInfo.append(MEXMELot.DELIMITADOR);
					loteInfo.append(rsEntrada.getString(12));
					
					linea.setLoteInfo(loteInfo.toString());
					lineasEntrada.add(linea);
					fechaMovimiento = rsEntrada.getString(8);
					mWareHouse = rsEntrada.getInt(9);
					adOrgTrxID = MEXMEEstServ.getEstServAlmOrgTrx(getCtx(), mWareHouse);
					
				}
			}catch(Exception e){
				e.printStackTrace();
				return e.getMessage();
			}

			EntradasAlm entradasAlm = new EntradasAlm(getCtx(), documentNo, "INTERFAZ", 
					cDocTypeID, fechaMovimiento, cBPartnerID, 
					priceListID, mWareHouse,
					adOrgTrxID, 
					lineasEntrada, false);

			ArrayList<String[]> saveOrders = entradasAlm.saveOrders();

			if(saveOrders.size()>0) {

				actualizaEntradas(documentNo, false, cDocTypeID);

			}else{
				ArrayList<String[]> complete = entradasAlm.complete();

				if (complete.size()>0)
					actualizaEntradas(documentNo, false, cDocTypeID);
				else
					actualizaEntradas(documentNo, true, cDocTypeID);	
			}
			if (pstmt != null)
				pstmt.close();
			if (rsEntrada != null)
				rsEntrada.close();
			sqlEntrada = null;
			pstmt = null;
			rsEntrada = null;
		}
		
		return "Exitoso";
	}

	private boolean actualizaEntradas(String documentNo, boolean b, int cDocTypeID) {
		
		try {
			PreparedStatement psUpdate = null;
			StringBuilder sqlUpdate = null; 
			sqlUpdate = new StringBuilder("UPDATE SM_ENTRADA SE ");
			sqlUpdate.append("SET TRANSFERIDO = ? " );
			sqlUpdate.append(", UPDATED = SYSDATE " );
			if (b){
				sqlUpdate.append(",(SE.M_INOUT_ID, SE.M_INOUTLINE_ID) = ")
	            .append("(SELECT MI.M_INOUT_ID, MIL.M_INOUTLINE_ID FROM M_INOUT MI ")
	            .append("INNER JOIN M_INOUTLINE MIL ON MIL.M_INOUT_ID = MI.M_INOUT_ID ")
	            .append("INNER JOIN M_PRODUCT MP ON MP.M_PRODUCT_ID = MIL.M_PRODUCT_ID ")
	            .append("WHERE MI.AD_CLIENT_ID = ? AND MI.C_DOCTYPE_ID = ? ");
	            MEXMEConfigInt configInt = MEXMEConfigInt.get(getCtx(), null,null);
				if (configInt.getInterfase_Inventarios().equalsIgnoreCase(X_EXME_ConfigInt.INTERFASE_INVENTARIOS_Sicome)){
					sqlUpdate.append("AND MI.DOCUMENTNO = SE.NO_ENTRADA AND MP.M_PRODUCT_ID = SE.PRODUCTO_VALUE)" );
				}else if(configInt.getInterfase_Inventarios().equalsIgnoreCase(X_EXME_ConfigInt.INTERFASE_INVENTARIOS_SIA)){
					sqlUpdate.append("AND MI.DOCUMENTNO = SE.NO_ENTRADA AND MP.SKU = SE.PRODUCTO_VALUE)" );
				}
				configInt = null;
	            
			}
			sqlUpdate.append("WHERE SE.NO_ENTRADA = ? ");
			psUpdate = DB.prepareStatement(sqlUpdate.toString(), null);
			if (b){
				psUpdate.setString(1, "Y");
				psUpdate.setInt(2, p_AD_Client_ID);
				psUpdate.setInt(3,cDocTypeID);
				psUpdate.setString(4,documentNo);
			}else{
				psUpdate.setString(1, "E");
				psUpdate.setString(2,documentNo);
			}
			
			psUpdate.executeUpdate();
		} catch (SQLException e) {
			return false;
		}
		return true;
	}

	private int getPriceList(Properties ctx) {
		
		int retValue = -1;
		String sql = "SELECT M_PriceList.M_PriceList_ID, M_PriceList.Name FROM M_PriceList "
				+ "WHERE M_PriceList.IsActive = 'Y' AND M_PriceList.issopricelist  = 'Y' ";

		sql = MEXMELookupInfo.addAccessLevelSQL(ctx, sql, "M_PriceList");

		sql += " ORDER BY M_PriceList.Name";

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = DB.prepareStatement(sql, null);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				retValue = rs.getInt("M_PriceList_ID");
				break;
			}
		} catch (Exception e) {
			log.log(Level.SEVERE, sql.toString(), e.getMessage());
			
			try {
				if (pstmt != null)
					pstmt.close();
				if (rs != null)
					rs.close();
			} catch (Exception ex) {
				log.log(Level.SEVERE, sql.toString(), ex.getMessage());
			}
			sql = null;
			pstmt = null;
			rs = null;
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (rs != null) {
					rs.close();
				}
				pstmt = null;
				rs = null;
			} catch (Exception e) {
				log.log(Level.SEVERE, "Closing rs and pstmt", e.getMessage());
				pstmt = null;
				rs = null;
			}
		}
		
		return retValue;

	}

	private int getcBPartner() {
		
		int retValue = -1;
		
		String sql = "SELECT bp.C_BPartner_ID FROM C_BPartner bp "
			+ "WHERE bp.IsSalesRep = 'Y' AND bp.AD_Client_ID = ? AND "
			+ "UPPER(Value) LIKE '%ADMIN%'";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement (sql, null);
			pstmt.setInt (1, p_AD_Client_ID);
			rs = pstmt.executeQuery ();
			if (rs.next ())
				retValue = rs.getInt("C_BPartner_ID");
		} 
		catch (Exception e)
		{
			log.log(Level.SEVERE, sql, e);
		}finally {
			try {
				if (pstmt != null)
					pstmt.close ();
				if(rs!=null)
					rs.close();
			} catch (Exception e) {}
			pstmt=null;
			rs=null;
		}
		return retValue;
	}

	@Override
	protected void prepare() {
		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++)
		{
			String name = para[i].getParameterName();
			if (para[i].getParameter() == null);
			if (name.equals("AD_Client_ID"))
				p_AD_Client_ID = (new BigDecimal(para[i].getParameter().toString())).intValue();
			else if (name.equals("AD_Org_ID"))
				p_AD_Org_ID = (new BigDecimal(para[i].getParameter().toString())).intValue();
			else
				log.log(Level.SEVERE, "prepare - Unknown Parameter: " + name);
		}
		
	}

}
