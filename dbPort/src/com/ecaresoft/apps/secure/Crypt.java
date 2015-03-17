package com.ecaresoft.apps.secure;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

/**
 *
 * @author Omar de la Rosa
 */
public class Crypt {

    public static final String DES = "DES";
    public static final String AES = "AES";
    Cipher ecipher;
    Cipher dcipher;

    /**
     * Encrypts based on a key
     * @param key
     * @param mode
     */
    public Crypt(SecretKey key, String mode) {
        try {
            ecipher = Cipher.getInstance(mode);
            dcipher = Cipher.getInstance(mode);
            ecipher.init(Cipher.ENCRYPT_MODE, key);
            dcipher.init(Cipher.DECRYPT_MODE, key);
        } catch (InvalidKeyException ex) {
            Logger.getLogger(Crypt.class.getName()).log(Level.FINEST, null, ex);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Crypt.class.getName()).log(Level.FINEST, null, ex);
        } catch (NoSuchPaddingException ex) {
            Logger.getLogger(Crypt.class.getName()).log(Level.FINEST, null, ex);
        }
    }

    /**
     * M#todo para encriptar un String
     * @param str String a encriptar
     * @return String encriptado
     */
    public String encrypt(String str) {
        try {
            byte[] utf8 = str.getBytes("UTF8");
            byte[] enc = ecipher.doFinal(utf8);
            return Base64.encodeBytes(enc);
        } catch (IllegalBlockSizeException ex) {
            Logger.getLogger(Crypt.class.getName()).log(Level.FINEST, null, ex);
        } catch (BadPaddingException ex) {
            Logger.getLogger(Crypt.class.getName()).log(Level.FINEST, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(Crypt.class.getName()).log(Level.FINEST, null, ex);
        }
        return str;
    }

	public String encrypt(byte[] arr) {
		try {
			byte[] enc = ecipher.doFinal(arr);
			return Base64.encodeBytes(enc);
		} catch (IllegalBlockSizeException ex) {
			Logger.getLogger(Crypt.class.getName()).log(Level.FINEST, null, ex);
		} catch (BadPaddingException ex) {
			Logger.getLogger(Crypt.class.getName()).log(Level.FINEST, null, ex);
		}
		return null;
	}

    /**
     * M#todo para desencriptar un String
     * @param str String a desencriptar
     * @return String desencriptado
     */
    public String decrypt(String str) {
    	String retVal = null;
        try {
            retVal = new String(decryptToByte(str), "UTF8");
        } catch (IOException ex) {
            Logger.getLogger(Crypt.class.getName()).log(Level.FINEST, null, ex);
            retVal = str;
        }
        return retVal;
    }

    public byte[] decryptToByte(String str) {
        try {
            byte[] dec = Base64.decode(str);
            return dcipher.doFinal(dec);
            //return new String(utf8, "UTF8");
        } catch (IllegalBlockSizeException ex) {
            Logger.getLogger(Crypt.class.getName()).log(Level.FINEST, null, ex);
        } catch (BadPaddingException ex) {
            Logger.getLogger(Crypt.class.getName()).log(Level.FINEST, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Crypt.class.getName()).log(Level.FINEST, null, ex);
        }
        return null;
    }

    public String decrypt(byte[] arr) {
        try {
            byte[] utf8 = dcipher.doFinal(arr);
            return new String(utf8, "UTF8");
        } catch (IllegalBlockSizeException ex) {
            Logger.getLogger(Crypt.class.getName()).log(Level.FINEST, null, ex);
        } catch (BadPaddingException ex) {
            Logger.getLogger(Crypt.class.getName()).log(Level.FINEST, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Crypt.class.getName()).log(Level.FINEST, null, ex);
        }
        return null;
    }
}
