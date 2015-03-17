/******************************************************************************
 * Product: Compiere ERP & CRM Smart Business Solution                        *
 * Copyright (C) 1999-2006 ComPiere, Inc. All Rights Reserved.                *
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
 * ComPiere, Inc., 2620 Augustine Dr. #245, Santa Clara, CA 95054, USA        *
 * or via info@compiere.org or http://www.compiere.org/license.html           *
 *****************************************************************************/
package org.compiere.db;

import java.util.*;

/**
 *  Connection Resource Strings
 *
 *  @author     Erwin Cortes
 *  @version    $Id: DBRes_es.java,v 1.2 2006/08/23 01:31:35 mrojas Exp $
 */
public class DBRes_es extends ListResourceBundle
{
	/** Data        */
	static final Object[][] contents = new String[][]{
	{ "CConnectionDialog",  "Conexi\u00f3n Compiere" },
	{ "Name",               "Nombre" },
	{ "AppsHost",           "Servidor de Aplicaci\u00f3n" },
	{ "AppsPort",           "Puerto de Aplicaci\u00f3n" },
	{ "TestApps",           "Prueba de Aplicaci\u00f3n" },
	{ "DBHost",             "Servidor de Base de Datos" },
	{ "DBPort",             "Puerto de Base de Datos" },
	{ "DBName",             "Nombre de Base de datos" },
	{ "DBUidPwd",           "Usuario / Contrase\u00f1a" },
	{ "ViaFirewall",        "v\u00eda Firewall" },
	{ "FWHost",             "Servidor de Firewall" },
	{ "FWPort",             "Puerto del Firewall" },
	{ "TestConnection",     "Prueba de Base de datos" },
	{ "Type",               "Tipo de Base de Datos" },
	{ "BequeathConnection", "Conexi\u00f3n" },
	{ "Overwrite",          "Sobreescribir" },
	{ "RMIoverHTTP", 	"Tunelizar Objetos v\u00eda HTTP" },
	{ "ConnectionError",    "Error en conexi\u00f3n" },
	{ "ServerNotActive",    "Servidor inactivo" }};

	/**
	 * Get Contsnts
	 * @return contents
	 */
	public Object[][] getContents()
	{
		return contents;
	}
}   //  Res
