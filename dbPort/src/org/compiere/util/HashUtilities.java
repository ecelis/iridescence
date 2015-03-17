package org.compiere.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.cert.X509Certificate;
import java.util.Formatter;
import java.util.logging.Level;

import javax.activation.MimetypesFileTypeMap;

import mx.bigdata.sat.cfdi.CFDv32;
//import mx.bigdata.sat.security.KeyLoader;

import mx.bigdata.sat.security.KeyLoaderEnumeration;
import mx.bigdata.sat.security.factory.KeyLoaderFactory;

import org.apache.commons.io.FileUtils;
import org.compiere.model.MAttachmentEntry;
import org.compiere.model.MEXMEConfigFE;
import org.compiere.model.MInvoice;

import com.ecaresoft.apps.secure.Base64;

/**
 * 
 * @author odelarosa
 */
public class HashUtilities {

	private static CLogger log = CLogger.getCLogger(HashUtilities.class);
	public static final String SHA_1 = "SHA-1";
	public static final String SHA_512 = "SHA-512";
	public static final int HEX = 1;
	public static final int B64 = 0;
	

	/**
	 * Lee el hash de un archivo de texto o binario
	 * 
	 * @param file
	 *            Archivo Hash
	 * @param type
	 *            Tipo de codificacion HEX o B64
	 * @return byte[] del hash
	 */
	public static byte[] readHash(File file, int type) {
		byte[] arr = null;
		try {
			String contentType = new MimetypesFileTypeMap().getContentType(file);
			if (contentType.equalsIgnoreCase("text/plain")) {
				String content = FileUtils.readFileToString(file);
				if (type == HEX) {
					arr = hexStringToByteArray(content);
				} else {
					arr = Base64.decode(content);
				}
			} else if (contentType.equalsIgnoreCase("application/octet-stream")) {
				arr = FileUtils.readFileToByteArray(file);
			}
		} catch (IOException ex) {
			log.log(Level.SEVERE, ex.getLocalizedMessage(), ex);
		}
		return arr;
	}

	/**
	 * Crea un hash a partir de un arreglo de bytes
	 * 
	 * @param arg
	 *            Arreglo de bytes
	 * @param type
	 *            Tipo de algoritmo
	 * @return byte[] hash
	 */
	public static byte[] createHash(byte[] arg, String type) {
		byte[] arr = null;
		try {
			MessageDigest md = MessageDigest.getInstance(type);
			md.update(arg);
			arr = md.digest();
		} catch (NoSuchAlgorithmException ex) {
			log.log(Level.SEVERE, ex.getLocalizedMessage(), ex);
		}
		return arr;
	}

	/**
	 * Crea un hash a partir de un archivo
	 * 
	 * @param file
	 *            Archivo
	 * @param type
	 *            Tipo de algoritmo
	 * @return byte[] hash
	 */
	public static byte[] createHash(File file, String type) {
		byte[] arr = null;
		try {
			arr = createHash(FileUtils.readFileToByteArray(file), type);
		} catch (IOException ex) {
			log.log(Level.SEVERE, ex.getLocalizedMessage(), ex);
		}
		return arr;
	}

	/**
	 * Crea un hash a partir de una cadena
	 * 
	 * @param text
	 *            Cadena de texto
	 * @param type
	 *            Tipo de algoritmo
	 * @return byte[] hash
	 */
	public static byte[] createHash(String text, String type) {
		return createHash(text.getBytes(), type);
	}

	/**
	 * Crea un hash a partir de un objeto
	 * 
	 * @param obj
	 *            Objeto
	 * @param type
	 *            Tipo de algoritmo
	 * @return byte[] hash
	 */
	public static byte[] createHash(Object obj, String type) {
		return createHash(getbytes(obj), type);
	}

	/**
	 * Codifica arreglo de bytes a Base64
	 * 
	 * @param arg
	 *            Arreglo de bytes
	 * @return String codificado
	 */
	public static String encode64Hash(byte[] arg) {
		return Base64.encodeBytes(arg);
	}

	/**
	 * Codifica arreglo de bytes a Hex
	 * 
	 * @param arg
	 *            Arreglo a codificar
	 * @return String codificado
	 */
	public static String encodeHexHash(byte[] hash) {
		Formatter formatter = new Formatter();
		for (byte b : hash) {
			formatter.format("%02x", b);
		}
		return formatter.toString();
	}

	/**
	 * Convierte String hex a un arreglo de bytes
	 * 
	 * @param s
	 *            String hex
	 * @return Arreglo de bytes
	 */
	public static byte[] hexStringToByteArray(String s) {
		int len = s.length();
		byte[] data = new byte[len / 2];
		for (int i = 0; i < len; i += 2) {
			data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4) + Character.digit(s.charAt(i + 1), 16));
		}
		return data;
	}

	/**
	 * Obtiene los bytes de un objeto
	 * 
	 * @param obj
	 *            Objeto
	 * @return Arreglo de bytes
	 */
	private static byte[] getbytes(Object obj) {
		ObjectOutputStream oos = null;
		byte[] data = null;
		try {
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			oos = new ObjectOutputStream(bos);
			oos.writeObject(obj);
			oos.flush();
			oos.close();
			bos.close();
			data = bos.toByteArray();
		} catch (IOException ex) {
			log.log(Level.SEVERE, ex.getLocalizedMessage(), ex);
		} finally {
			try {
				if (oos != null) {
					oos.close();
				}
			} catch (IOException ex) {
				log.log(Level.SEVERE, ex.getLocalizedMessage(), ex);
			}
		}
		return data;
	}

	/**
	 * Crea un archivo zip con el archivo enviado y su hash
	 * 
	 * @param source
	 *            Archivo fuente
	 * @param zipFile
	 *            Archivo Zip
	 * @param algorithm
	 *            Algoritmo a usar
	 * @param encoding
	 *            Tipo de codificacion
	 */
	public static void createHashZip(File source, File zipFile, String algorithm, int encoding) {
		try {
			File hash = File.createTempFile("hash", ".sha");

			byte[] arr = createHash(source, algorithm);
			String encoded = null;
			if (encoding == B64) {
				encoded = encode64Hash(arr);
			} else if (encoding == HEX) {
				encoded = encodeHexHash(arr);
			}

			FileUtils.writeStringToFile(hash, encoded);

			ZipUtil.createZip(zipFile, source, hash);

			hash.delete();

		} catch (IOException ex) {
			log.log(Level.SEVERE, ex.getLocalizedMessage(), ex);
		}
	}	
	
	/**
	 * Sella el xml, ademas de asignarle el certificado
	 * @param file
	 * @return xml ya certificado
	 */
	public static File Sellar(File file, MEXMEConfigFE fe, MInvoice invoice){
		File retFile = null;
		// Directorio temporal
		String path = MAttachmentEntry.getTmpDirectory();
		try {
			File certificado = new File(path.concat(File.separator).concat(String.valueOf(Env.getAD_Client_ID(Env.getCtx()))).concat("certificado.cer"));
			File llave = new File(path.concat(File.separator).concat(String.valueOf(Env.getAD_Client_ID(Env.getCtx()))).concat("key.key"));
			if (fe != null) {
				// Obtener el archivo de certificado
				byte[] cer = fe.getAttachmentData(".cer");
				if (cer == null){
					return null;
				}
				// Obtener el archivo de la llave
				byte[] arllave = fe.getAttachmentData(".key");
				if (arllave == null) {
					return null;
				}
				// Escribir lo guardado en la base de datos en un archivo
				FileUtils.writeByteArrayToFile(certificado, cer);
				FileUtils.writeByteArrayToFile(llave, arllave);
				retFile = File.createTempFile(invoice.getDocumentNo(), ".xml", new File(MAttachmentEntry.getTmpDirectory()));
				// Crea el CFD a partir de un archivo
				CFDv32 cfd = new CFDv32(new FileInputStream(file));
				// Carga la llave privada
				//PrivateKey key = KeyLoader.loadPKCS8PrivateKey(new FileInputStream(llave), fe.getPasswordPK());
				// Carga el certificado
				//java.security.cert.X509Certificate cert = KeyLoader.loadX509Certificate(new FileInputStream(certificado));

			    PrivateKey key = KeyLoaderFactory.createInstance( KeyLoaderEnumeration.PRIVATE_KEY_LOADER,
			              new FileInputStream(llave), fe.getPasswordPK()).getKey();

			    X509Certificate cert = KeyLoaderFactory.createInstance(KeyLoaderEnumeration.PUBLIC_KEY_LOADER,
			              new FileInputStream(certificado)).getKey();				
				
				// Sellar CFD y obtener un Comprobante sellado
				mx.bigdata.sat.cfdi.v32.schema.Comprobante sellado = cfd.sellarComprobante(key, cert);
				// Serializa el CFD ya firmado
				cfd.guardar(new FileOutputStream(retFile));
				// Obtener cadena original
				String cadena = cfd.getCadenaOriginal();
				// Obtener sello
				String sello = sellado.getSello();
				// Enviar datos a el log
				log.log(Level.WARNING, "@cadena l-285"+cadena+"@");
				log.log(Level.WARNING, "@sellar l-286"+sello+"@");
			} else {
				return null;
			}
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getLocalizedMessage(), e);
			return retFile;
		}
	    return retFile;
	}
}
