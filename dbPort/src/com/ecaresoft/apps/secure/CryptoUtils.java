package com.ecaresoft.apps.secure;

import java.util.Random;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import com.ecaresoft.apps.secure.Crypt;

import com.ecaresoft.util.EcsEngine;

/**
 *
 * @author Omar de la Rosa
 */
public class CryptoUtils {

    /**
     * Encripta un String en base a una llave
     * @param keyFrase Llave secreta
     * @param text Texto a encriptar
     * @return Texto encriptado
     */
    public static String encrypt(String keyFrase, String text) {
        String encriptado = null;
        byte[] raw = CryptoUtils.hexToBytes(keyFrase);
        encriptado = CryptoUtils.encrypt(CryptoUtils.aesKey(raw), Crypt.AES, text);
        return encriptado;
    }

    public static String encrypt(String keyFrase, byte[] text) {
        String encriptado = null;
        byte[] raw = CryptoUtils.hexToBytes(keyFrase);
        encriptado = CryptoUtils.encrypt(CryptoUtils.aesKey(raw), Crypt.AES, text);
        return encriptado;
    }

	public static String encrypt(String text) {
		String encriptado = text;
		String keyFrase = EcsEngine.getCryptoSeed();
		if (keyFrase != null) {
			byte[] raw = CryptoUtils.hexToBytes(keyFrase);
			encriptado = CryptoUtils.encrypt(CryptoUtils.aesKey(raw), Crypt.AES, text);
		}
		return encriptado;
	}

	public static String decrypt(String text) {
		String encriptado = text;
		String keyFrase = EcsEngine.getCryptoSeed();
		if (keyFrase != null) {
			byte[] raw = CryptoUtils.hexToBytes(keyFrase);
			encriptado = CryptoUtils.decrypt(CryptoUtils.aesKey(raw), Crypt.AES, text);
		}
		return encriptado;
	}


    public static String decrypt(String semilla, String text){
    	byte[] raw = CryptoUtils.hexToBytes(semilla);
        text = (CryptoUtils.decrypt(CryptoUtils.aesKey(raw), Crypt.AES, text));
        return text;
    }

    public static byte[] decryptToByte(String semilla, String arr){
    	byte[] raw = CryptoUtils.hexToBytes(semilla);
        byte[] text = (CryptoUtils.decryptToByte(CryptoUtils.aesKey(raw), Crypt.AES, arr));
        return text;
    }

    public static String decrypt(SecretKey secretKey, String mode, String text) {
        String desencriptado = null;

        Crypt encrypter = new Crypt(secretKey, mode);
        desencriptado = encrypter.decrypt(text);

        return desencriptado;
    }

    public static byte[] decryptToByte(SecretKey secretKey, String mode, String arr) {
        byte[] desencriptado = null;

        Crypt encrypter = new Crypt(secretKey, mode);
        desencriptado = encrypter.decryptToByte(arr);

        return desencriptado;
    }

    /**
     * Obtiene la llave secreta a partir del arreglo de bytes
     * @param raw Arreglo de Bytes
     * @return Llave secreta
     */
    public static SecretKey aesKey(byte[] raw) {
        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
        return skeySpec;
    }

    /**
     * Obtiene el arreglo de bytes de un String Hexadecimal
     * @param hex String hexadecimal
     * @return Arreglo de Bytes
     */
    public static byte[] hexToBytes(String hex) {
        return hexToBytes(hex.toCharArray());
    }

    /**
     * Obtiene el arreglo de bytes de una cadena de caracteres Hexadecimal
     * @param hex Cadena hexadecimal
     * @return Arreglo de Bytes
     */
    public static byte[] hexToBytes(char[] hex) {
        int length = hex.length / 2;
        byte[] raw = new byte[length];
        for (int i = 0; i < length; i++) {
            int high = Character.digit(hex[i * 2], 16);
            int low = Character.digit(hex[i * 2 + 1], 16);
            int value = (high << 4) | low;
            if (value > 127) {
                value -= 256;
            }
            raw[i] = (byte) value;
        }
        return raw;
    }

    /**
     * Encripta un String segun el modo de encriptaci#n
     * @param secretKey Llave secreta
     * @param mode Modo de encriptaci#n
     * @param text Texto a encriptar
     * @return Texto encriptado
     */
    public static String encrypt(SecretKey secretKey, String mode, String text) {
        String encriptado = null;
        Crypt encrypter = new Crypt(secretKey, mode);
        encriptado = encrypter.encrypt(text);
        return encriptado;
    }

    public static String encrypt(SecretKey secretKey, String mode, byte[] arr) {
        String encriptado = null;
        Crypt encrypter = new Crypt(secretKey, mode);
        encriptado = encrypter.encrypt(arr);
        return encriptado;
    }

    /**
     * Genera una llave aleatoriamente
     * @return Llave generada
     */
    public static String generateKeyFrame() {
        String retValue = null;
        Random r = new Random();
        retValue = Long.toString(Math.abs(r.nextLong()), 36);
        return retValue;
    }



    public static void main(String [] args){
        byte[] raw = CryptoUtils.hexToBytes("f74de1d77975e92a4061fe31d8fe0656");
        String text = (CryptoUtils.decrypt(CryptoUtils.aesKey(raw), Crypt.AES, "QCTLHvoKvC7l6+z6NO5O4UNBJ0B3lxRbJOBxvhSkR0k="));
//        String text = (CryptoUtils.decrypt(CryptoUtils.aesKey(raw), Crypt.AES, "bififGjw9vN3sovdLeRIW2xROEovulGQEfe54ZYdgL0="));
        System.out.println(text);

        String encriptado = "developer";
//        String keyFrase = "f74de1d77975e92a4061fe31d8fe0656";
        String keyFrase = "ddad617210cdd5fced765b60cf309da9";
        if (keyFrase != null) {
            raw = CryptoUtils.hexToBytes(keyFrase);
            encriptado = CryptoUtils.encrypt(CryptoUtils.aesKey(raw), Crypt.AES, "NOMBRECOMPLETO");
        }

        System.out.println("developer = " + encriptado);
        System.out.println("original = " + CryptoUtils.decrypt(CryptoUtils.aesKey(raw), Crypt.AES, "BZ1lzfTCXDo6tzqw2pLrBA=="));
    }
}