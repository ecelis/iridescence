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
package org.compiere.apps;

import java.util.*;

/**
 *  Base Resource Bundle
 *
 * 	@author 	Erwin Cortes
 * 	@version 	$Id: ALoginRes_es.java,v 1.5 2006/08/23 00:33:36 mrojas Exp $
 */
public final class ALoginRes_es extends ListResourceBundle
{
	/** Translation Content     */
	static final Object[][] contents = new String[][]
	{
	{ "Connection", "Conexi\u00F3n" }, //expert: miguel angel rojas
	{ "Defaults", "Valores iniciales" }, //expert: miguel angel rojas
	{ "Login", "Medsys Login" }, //expert: miguel angel rojas
	{ "File", "Archivo" },
	{ "Exit", "Salir" },
	{ "Help", "Ayuda" },
	{ "About", "Acerca de" },
	{ "Host", "Servidor" },
	{ "Database", "Base de datos" },
	{ "User", "Usuario" }, //expert: miguel angel rojas
	{ "EnterUser", "Introduzca el nombre de usuario" }, //expert: miguel angel rojas
	{ "Password", "Contrase\u00F1a" }, //expert: miguel angel rojas
	{ "EnterPassword", "Introduzca la contrase\u00F1a" }, //expert: miguel angel rojas
	{ "Language", "Idioma" }, //expert: miguel angel rojas
	{ "SelectLanguage", "Seleccione su idioma" }, //expert: miguel angel rojas
	{ "Role", "Perfil" }, //expert: miguel angel rojas
	{ "Client", "Empresa" }, //expert: miguel angel rojas
	{ "Organization", "Organizaci\u00F3n" }, //expert: miguel angel rojas
	{ "Date", "Fecha" },
	{ "Warehouse", "Almac\u00E9n" }, //expert: miguel angel rojas
	{ "Printer", "Impresora" },
	{ "Connected", "Conectado" },
	{ "NotConnected", "No Conectado" },
	{ "DatabaseNotFound", "Base de datos no encontrada" },
	{ "UserPwdError", "El usuario y la contrase\u00F1a no concuerdan" }, //expert: miguel angel rojas
	{ "RoleNotFound", "Perfil no encontrado" }, //expert: miguel angel rojas
	{ "Authorized", "Autorizado" },
	{ "Ok", "Aceptar" },
	{ "Cancel", "Cancelar" },
	{ "VersionConflict", "Conflicto de versi\u00F3n:" }, //expert: miguel angel rojas
	{ "VersionInfo", "Servidor <> Cliente" },
	{ "PleaseUpgrade", "Favor de ejecutar el programa de actualizaci\u00F3n" }, //experALoginRes_es.javat: miguel angel rojas
	{ "AppServerNotFound",  "No se encontr\u00F3 el Servidor de Aplicaciones" }, //expert : miguel rojas
	{ "ServiceStation",  	"Estaci\u00F3n de Servicio" }, //expert : miguel rojas
	{ "ErrorDeactivarUsuario",  "No fue posible desactivar al usuario" },
	{ "ErrorUsuarioDesactivado",  "El usuario ha sido desactivado por seguridad" },
	{ "ErrorEnviar",  "No fue posible enviar el correo de activaci\u00F3n" },
	{ "License",  "Licencia" },
	{ "ErrorLicencia",  " Ocurri\u00F3 un error con la licencia, verificar si est\u00E1 instalada" },
	{ "error.terminoLicencia",  "Los t\u00E9rminos de la licencia no se han cumplido" },
	};

	/**
	 *  Get Contents
	 *  @return context
	 */
	public Object[][] getContents()
	{
		return contents;
	}   //  getContents
}	//	ALoginRes_es
