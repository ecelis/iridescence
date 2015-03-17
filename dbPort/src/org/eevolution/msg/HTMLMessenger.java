/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * This program is free software; you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * Copyright (C) 2003-2007 e-Evolution,SC. All Rights Reserved.               *
 * Contributor(s): Victor Perez www.e-evolution.com                           *
 *****************************************************************************/

package org.eevolution.msg;

import java.math.BigDecimal;
import java.text.MessageFormat;

import org.compiere.model.MProduct;
import org.compiere.util.Env;
import org.compiere.util.Msg;

/**
 * @author Gunther Hoppe, tranSIT GmbH Ilmenau/Germany
 * @version 1.0, October 14th 2005
 */
public class HTMLMessenger {

	final protected String PRODUCT_TOOLTIP = 	
		"<html><H1 align=\"CENTER\">"+Msg.translate(Env.getCtx(), "M_Product_ID")+"</H1>"+
		"<table cellpadding=\"5\" cellspacing=\"5\">"+
		"<tr><td><b>"+Msg.translate(Env.getCtx(), "Description")+":</b></td><td>{0}</td></tr>"+
		"</table></html>";

	final protected String LENGTHTRANSFORM_INFO_PATTERN = 	
		"<html>" +
		"<table cellpadding=\"5\" cellspacing=\"5\">"+
		"<tr><td><b>{0}</b></td></tr>"+
		"<tr><td>{1}</td></tr>"+
		"<tr><td>{2}</td></tr>"+
		"</table></html>";	
	
	final protected String PP_ORDER_INFO_PATTERN = 	
			"<html><H1 align=\"CENTER\">"+Msg.translate(Env.getCtx(), "PP_Order_ID")+"</H1>"+
			"<table cellpadding=\"5\" cellspacing=\"5\">"+
			"<tr><td><b>"+Msg.translate(Env.getCtx(), "DocumentNo")+":</b></td><td>{0}</td></tr>"+
			"<tr><td><b>"+Msg.translate(Env.getCtx(), "DateStartSchedule")+":</b></td><td>{1}</td></tr>"+
			"<tr><td><b>"+Msg.translate(Env.getCtx(), "DateFinishSchedule")+":</b></td><td>{2}</td></tr>"+
			"<tr><td><b>"+Msg.translate(Env.getCtx(), "C_Project_ID")+":</b></td><td>{3}</td></tr>"+
			"<tr><td><b>"+Msg.translate(Env.getCtx(), "M_Product_ID")+":</b></td><td>{4}</td></tr>"+
			"<tr><td><b>"+Msg.translate(Env.getCtx(), "QtyOrdered")+":</b></td><td>{5}</td></tr>"+
			"<tr><td><b>"+Msg.translate(Env.getCtx(), "QtyDelivered")+":</b></td><td>{6}</td></tr>"+
			"</table></html>";
	
	final protected String PP_ORDER_HEADER_INFO_PATTERN = 	
		"<html><H1 align=\"LEFT\">{0}</H1>"+
		"<table cellpadding=\"5\" cellspacing=\"5\">"+
		"<tr>"+
		"<td><b>"+Msg.translate(Env.getCtx(), "DocumentNo")+"</b></td>"+
		"<td><b>"+Msg.translate(Env.getCtx(), "DateStartSchedule")+"</b></td>"+
		"<td><b>"+Msg.translate(Env.getCtx(), "DateFinishSchedule")+"</b></td>"+
		"<td><b>"+Msg.translate(Env.getCtx(), "C_Project_ID")+"</b></td>"+
		"<td><b>"+Msg.translate(Env.getCtx(), "M_Product_ID")+"</b></td>"+
		"<td><b>"+Msg.translate(Env.getCtx(), "QtyOrdered")+"</b></td>"+
		"<td><b>"+Msg.translate(Env.getCtx(), "QtyDelivered")+"</b></td>"+
		"<tr>"+
		"</table></html>";
	
	final protected String PP_ORDER_LINE_INFO_PATTERN = 	
		"<html>"+
		"<table cellpadding=\"5\" cellspacing=\"5\">"+
		"<tr>"+
		"<td>{0}</td>"+
		"<td>{1}</td>"+
		"<td>{2}</td>"+
		"<td>{3}</td>"+
		"<td>{4}</td>"+
		"<td>{5}</td>"+
		"<td>{6}</td>"+
		"</tr>"+
		"</table></html>";
	
	final protected String BOM_INFO_PATTERN = 	
		"<html><H1 align=\"CENTER\">"+Msg.translate(Env.getCtx(), "PP_Product_BOM_ID")+"</H1>"+
		"<table cellpadding=\"5\" cellspacing=\"5\">"+
		"<tr><td><b>"+Msg.translate(Env.getCtx(), "DocumentNo")+":</b></td><td>{0}</td></tr>"+
		"<tr><td><b>"+Msg.translate(Env.getCtx(), "PP_Product_BOM_ID")+":</b></td><td>{1}</td></tr>"+
		"<tr><td><b>"+Msg.translate(Env.getCtx(), "ValidFrom")+":</b></td><td>{2} - {3}</td></tr>"+
		"<tr><td><b>"+Msg.translate(Env.getCtx(), "Value")+":</b></td><td>{4}</td></tr>"+
		"<tr><td><b>"+Msg.translate(Env.getCtx(), "M_Product_ID")+":</b></td><td>{5}</td></tr>"+
		"<tr><td></td><td>{6}</td></tr>"+
		"</table>"+
		"<p>{7}</p>"+
		"</html>";
	
	final protected String BOM_HEADER_INFO_PATTERN =
		"<table align=\"CENTER\" cellpadding=\"5\" cellspacing=\"5\"><tr>"
		+"<td><b>"+Msg.translate(Env.getCtx(), "Line")+"</b></td>"
		+"<td><b>"+Msg.translate(Env.getCtx(), "Qty")+"</b></td>"
		+"<td><b>"+Msg.translate(Env.getCtx(), "M_Product_ID")+"</b></td>"
		+"<td><b>"+Msg.translate(Env.getCtx(), "M_AttributeSetInstance_ID")+"</b></td>"
		+"</tr>";
	
	final protected String BOM_LINE_INFO_PATTERN =
		"<tr>"
		+"<td align=RIGHT>{0}</td>"
		+"<td align=RIGHT>{1}</td>"
		+"<td>{2}</td>"
		+"<td>{3}</td>"
		+"</tr>";

	final protected String BOMLINE_INFO_PATTERN = 	
		"<html><H1 align=\"CENTER\">"+Msg.translate(Env.getCtx(), "Line")+":&nbsp;{0}</H1>"+
		"<table cellpadding=\"5\" cellspacing=\"5\">"+
		"<tr><td><b>"+Msg.translate(Env.getCtx(), "ComponentType")+":</b></td><td>{1}</td></tr>"+
		"<tr><td><b>"+Msg.translate(Env.getCtx(), "ValidFrom")+":</b></td><td>{2} - {3}</td></tr>"+
		"<tr><td><b>"+Msg.translate(Env.getCtx(), "Qty")+":</b></td><td>{4}</td></tr>"+
		"<tr><td><b>"+Msg.translate(Env.getCtx(), "M_Product_ID")+":</b></td><td>{5}</td></tr>"+
		"<tr><td></td><td>{6}</td></tr>" +
		"</table>"+
		"<p>{7}</p>"+
		"</html>";

	final protected String STORAGE_HEADER_INFO_PATTERN =
		"<table align=\"CENTER\" cellpadding=\"5\" cellspacing=\"5\"><tr>"
		+"<td><b>"+Msg.translate(Env.getCtx(), "M_Locator_ID")+"</b></td>"
		+"<td><b>"+Msg.translate(Env.getCtx(), "M_Warehouse_ID")+"</b></td>"
		+"<td><b>"+Msg.translate(Env.getCtx(), "QtyOnHand")+"</b></td>"
		+"<td><b>"+Msg.translate(Env.getCtx(), "QtyReserved")+"</b></td>"
		+"<td><b>"+Msg.translate(Env.getCtx(), "QtyOrdered")+"</b></td>"
		+"<td><b>"+Msg.translate(Env.getCtx(), "QtyAvailable")+"</b></td>"
		+"</tr>";

	final protected String STORAGE_LINE_INFO_PATTERN =
		"<tr>"
		+"<td>{0}</td>"
		+"<td>{1}</td>"
		+"<td align=RIGHT>{2}</td>"
		+"<td align=RIGHT>{3}</td>"
		+"<td align=RIGHT>{4}</td>"
		+"<td align=RIGHT>{5}</td>"
		+"</tr>";

	final protected String STORAGE_SUM_LINE_INFO_PATTERN =
		"<tr>"
		+"<td></td>"
		+"<td></td>"
		+"<td align=RIGHT><hr size=\"1\" noshade=\"NOSHADE\">{0}</td>"
		+"<td align=RIGHT><hr size=\"1\" noshade=\"NOSHADE\">{1}</td>"
		+"<td align=RIGHT><hr size=\"1\" noshade=\"NOSHADE\">{2}</td>"
		+"<td align=RIGHT><hr size=\"1\" noshade=\"NOSHADE\">{3}</td>"
		+"</tr>";

	final protected String STORAGE_NOINVENTORY_INFO_PATTERN =
		"<tr>"
		+"<td align=\"CENTER\" colspan=\"6\">"+Msg.translate(Env.getCtx(), Msg.getMsg(Env.getCtx(), "NoQtyAvailable"))+"</td>"
		+"</tr>";
	
	final protected String STORAGE_FOOTER_INFO_PATTERN = "</table>";

	final protected String ATTRIBUTE_INFO_PATTERN = "{0}&nbsp;=&nbsp;<i>{1}</i>";
	
	public String getProductInfo(MProduct p) {

    	
    	Object[] obj = new Object[] {
    			p.getDescription() == null ? "" : p.getDescription()
    	};
    	
		return MessageFormat.format(PRODUCT_TOOLTIP, obj);	    	
	}

	public String getLengthTransformInfo(MProduct p, BigDecimal srcLength, BigDecimal tgtLength, BigDecimal pieces) {

    	BigDecimal scrapLength = srcLength.subtract(tgtLength.multiply(pieces));
    	
    	Object[] obj = new Object[] {
    			p.getName()+" ("+p.getValue()+")",
    			"1 x "+srcLength.setScale(2, BigDecimal.ROUND_HALF_DOWN)+" &#8594; "+pieces+" x "+tgtLength.setScale(2, BigDecimal.ROUND_HALF_DOWN),
    			Msg.translate(Env.getCtx(), "Scrap")+": 1 x "+scrapLength.setScale(2, BigDecimal.ROUND_HALF_DOWN),
    	};
    	
		return MessageFormat.format(LENGTHTRANSFORM_INFO_PATTERN, obj);	    	
	}
	
}
