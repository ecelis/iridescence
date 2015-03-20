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
package org.compiere.util;

import java.math.BigDecimal;
import java.util.logging.Level;

import org.apache.commons.lang.StringUtils;

/**
 *	Spanish Amount in Words
 *	
 *  @author Jorg Janke - http://www.rgagnon.com/javadetails/java-0426.html
 *  @version $Id: AmtInWords_ES.java,v 1.3 2006/07/30 00:54:36 jjanke Exp $
 */
public class AmtInWords_ES implements AmtInWords
{
	/**
	 * 	AmtInWords_ES
	 */
	public AmtInWords_ES ()
	{
		super ();
	} //	AmtInWords_ES

	// private static final String[] majorNames = {
	// "",
	// " MIL",
	// " MILLONES",
	// " BILLONES",
	// " TRILLONES",
	// " CUATRILLONES",
	// " QUINTRILLONES"
	// };
	//
	// private static final String[] tensNames = {
	// "",
	// " DIEZ",
	// " VEINTE",
	// " TREINTA",
	// " CUARENTA",
	// " CINCUENTA",
	// " SESENTA",
	// " SETENTA",
	// " OCHENTA",
	// " NOVENTA"
	// };
	//
	// private static final String[] numNames = {
	// "",
	// " UNO",
	// " DOS",
	// " TRES",
	// " CUATRO",
	// " CINCO",
	// " SEIS",
	// " SIETE",
	// " OCHO",
	// " NUEVE",
	// " DIEZ",
	// " ONCE",
	// " DOCE",
	// " TRECE",
	// " CATORCE",
	// " QUINCE",
	// " DIECISEIS",
	// " DIECISIETE",
	// " DIECIOCHO",
	// " DIECINUEVE"
	// };
	//
	// /**
	// * Convert Less Than One Thousand
	// * @param number
	// * @return amt
	// */
	// private String convertLessThanOneThousand (int number)
	// {
	// String soFar;
	// // Esta dentro de los 1os. diecinueve?? ISCAP
	// if (number % 100 < 20)
	// {
	// soFar = numNames[number % 100];
	// number /= 100;
	// }
	// else
	// {
	// soFar = numNames[number % 10];
	// number /= 10;
	// String s = Integer.toString (number);
	// if (s.endsWith ("2") && soFar != "")
	// soFar = " VEINTI" + soFar.trim ();
	// else if (soFar == "")
	// soFar = tensNames[number % 10] + soFar;
	// else
	// soFar = tensNames[number % 10] + " Y" + soFar;
	// number /= 10;
	// }
	// if (number == 0)
	// return soFar;
	// if (number > 1)
	// soFar = "S" + soFar;
	// if (number == 1 && soFar != "")
	// number = 0;
	// return numNames[number] + " CIENTO" + soFar;
	// } // convertLessThanOneThousand
	//
	// /**
	// * Convert
	// * @param number
	// * @return amt
	// */
	// private String convert (int number){
	// /* special case */
	// if (number == 0)
	// return "CERO";
	// String prefix = "";
	// if (number < 0){
	// number = -number;
	// prefix = "MENOS";
	// }
	// String soFar = "";
	// int place = 0;
	// do{
	// int n = number % 1000;
	// if (n != 0){
	// String s = convertLessThanOneThousand (n);
	// //Rqm 4860 Nombre de la cantidad incorrecta, se agregan Cantidades faltantes
	// if (s.startsWith ("DOS CIENTOS", 1)){
	// s = s.replaceFirst ("DOS CIENTOS", "DOSCIENTOS");
	// }
	// if (s.startsWith ("TRES CIENTOS", 1)){
	// s = s.replaceFirst ("TRES CIENTOS", "TRESCIENTOS");
	// }
	// if (s.startsWith ("CUATRO CIENTOS", 1)){
	// s = s.replaceFirst ("CUATRO CIENTOS", "CUATROCIENTOS");
	// }
	// if (s.startsWith ("CINCO CIENTOS", 1)){
	// s = s.replaceFirst ("CINCO CIENTOS", "QUINIENTOS");
	// }
	// if (s.startsWith ("SEIS CIENTOS", 1)){
	// s = s.replaceFirst ("SEIS CIENTOS", " SEISCIENTOS");
	// }
	// if (s.startsWith ("SIETE CIENTOS", 1)){
	// s = s.replaceFirst ("SIETE CIENTOS", "SETECIENTOS");
	// }
	// if (s.startsWith ("OCHO CIENTOS", 1)){
	// s = s.replaceFirst ("OCHO CIENTOS", "OCHOCIENTOS");
	// }
	// if (s.startsWith ("NUEVE CIENTOS", 1)){
	// s = s.replaceFirst ("NUEVE CIENTOS", "NOVECIENTOS");
	// }
	// if (s.trim().equalsIgnoreCase("UNO CIENTO")){
	// s = s.trim().replaceFirst("UNO CIENTO", "CIEN");
	// }
	// if (s == " UNO"){
	// soFar = majorNames[place] + soFar;
	// }
	// else
	// soFar = s + majorNames[place] + soFar;
	// }
	// place++;
	// number /= 1000;
	// }
	// while (number > 0);
	// return (prefix + soFar).trim ();
	// } // convert

	protected static CLogger	slog	= CLogger.getCLogger(AmtInWords_ES.class);

	/**************************************************************************
	 * Get Amount in Words
	 * 
	 * @param amount numeric amount (352.80)
	 * @return amount in words (trescientos cincuenta y dos 80/100)
	 * @deprecated use {@link #getAmtInWords(BigDecimal)} instead (oct 2013)
	 */
	public String getAmtInWords(String amount) throws Exception {
		final StringBuffer sb = new StringBuffer();
		try {
			amount = StringUtils.replaceChars(StringUtils.deleteWhitespace(StringUtils.trimToEmpty(amount)), ",", "");
			if (StringUtils.isEmpty(amount)) {
				return amount;
			}
			sb.append(getAmtInWords(new BigDecimal(amount)));
		} catch (Exception e) {
			slog.log(Level.SEVERE, e.getMessage(), e);
		}
		return sb.toString();
	} // getAmtInWords

	/**************************************************************************
	 * Get Amount in Words
	 * 
	 * @param amount numeric amount (352.80)
	 * @return amount in words (trescientos cincuenta y dos 80/100)
	 */
	public String getAmtInWords(BigDecimal amount) throws Exception {
		final StringBuffer sb = new StringBuffer();
		
		try {

			if (amount != null) {
				// long pesos = Long.parseLong(array[0]);
				sb.append(n2t.convertirLetras(amount.longValue()).toUpperCase());

				if (amount.scale() > 0) {
					String d = amount.abs().remainder(BigDecimal.ONE).unscaledValue().toString(); 
					sb.append(" ").append(d.length() == 1 ? "0" : StringUtils.EMPTY);
					sb.append(d).append("/100"); 
					// .append ("/100 PESOS");
				}
			}
		} catch (Exception e) {
			slog.log(Level.SEVERE, e.getMessage(), e);
		}
		return sb.toString();
	} // getAmtInWords

} // AmtInWords_ES
