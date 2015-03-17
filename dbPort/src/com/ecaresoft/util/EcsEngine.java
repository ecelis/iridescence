package com.ecaresoft.util;

import java.math.BigDecimal;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.util.logging.Level;

import org.apache.commons.lang.StringUtils;
import org.compiere.util.CLogger;
import org.compiere.util.SecureInterface;

import com.ecaresoft.apps.secure.CryptoUtils;

public class EcsEngine implements SecureInterface {

	private static CLogger log = CLogger.getCLogger(EcsEngine.class);
	private MessageDigest m_md = null;

	@Override
	public String decrypt(String value) {
		String retVal = null;
		try {
			retVal = CryptoUtils.decrypt(EcsEngine.getDbCryptoSeed(), value);
		} catch (Exception e) {
			log.log(Level.FINEST, StringUtils.EMPTY, e);
			retVal = value;
		}

		return retVal;
	}

	@Override
	public String encrypt(String value) {
		String retVal = null;

		try {
			retVal = CryptoUtils.encrypt(EcsEngine.getDbCryptoSeed(), value);
		} catch (Exception e) {
			log.log(Level.FINEST, StringUtils.EMPTY, e);
			retVal = value;
		}

		return retVal;
	}

	@Override
	public Integer encrypt(Integer value) {
		return value;
	}

	@Override
	public Integer decrypt(Integer value) {
		return value;
	}

	@Override
	public BigDecimal encrypt(BigDecimal value) {
		return value;
	}

	@Override
	public BigDecimal decrypt(BigDecimal value) {
		return value;
	}

	@Override
	public Timestamp encrypt(Timestamp value) {
		return value;
	}

	@Override
	public Timestamp decrypt(Timestamp value) {
		return value;
	}

	@Override
	public String encryptFile(byte[] value) {
		String retVal = null;

		try {
			retVal = CryptoUtils.encrypt(EcsEngine.getDbCryptoSeed(), value);
		} catch (Exception e) {
			log.log(Level.FINEST, StringUtils.EMPTY, e);
		}

		return retVal;
	}

	@Override
	public byte[] decryptFile(String value) {
		byte[] retVal = null;

		try {
			retVal = CryptoUtils.decryptToByte(EcsEngine.getDbCryptoSeed(), value);
		} catch (Exception e) {
			log.log(Level.FINEST, StringUtils.EMPTY, e);
		}

		return retVal;
	}

	@Override
	public String getDigest(String value) {
		if (m_md == null) {
			try {
				m_md = MessageDigest.getInstance("MD5");
				// m_md = MessageDigest.getInstance("SHA-1");
			} catch (NoSuchAlgorithmException nsae) {
				nsae.printStackTrace();
			}
		}
		// Reset MessageDigest object
		m_md.reset();
		// Convert String to array of bytes
		byte[] input = value.getBytes();
		// feed this array of bytes to the MessageDigest object
		m_md.update(input);
		// Get the resulting bytes after the encryption process
		byte[] output = m_md.digest();
		m_md.reset();
		//
		return convertToHexString(output);
	}

	@Override
	public boolean isDigest(String value) {
		if (value == null || value.length() != 32)
			return false;
		// needs to be a hex string, so try to convert it
		return (convertHexString(value) != null);
	}

	/**
	 * Convert Hex String to Byte Array
	 *
	 * @param hexString
	 *            hex string
	 * @return byte array
	 */
	public static byte[] convertHexString(String hexString) {

		byte[] retVal = null;

		if (StringUtils.isNotEmpty(hexString)) {
			int size = hexString.length() / 2;
			retVal = new byte[size];
			String inString = hexString.toLowerCase();

			try {

				for (int i = 0; i < size; i++) {

					int index = i * 2;
					int ii = Integer.parseInt(inString.substring(index, index + 2), 16);

					retVal[i] = (byte) ii;
				}

			} catch (Exception e) {
				log.finest(hexString + " - " + e.getMessage());
				retVal = null;
			}
		}

		return retVal;
	}

	/**
	 * Convert Byte Array to Hex String
	 *
	 * @param bytes
	 *            bytes
	 * @return HexString
	 */
	public static String convertToHexString(byte[] bytes) {
		// see also Util.toHex
		int size = bytes.length;

		StringBuffer buffer = new StringBuffer(size * 2);

		for (int i = 0; i < size; i++) {
			// convert byte to an int
			int x = bytes[i];
			// account for int being a signed type and byte being unsigned
			if (x < 0) {
				x += 256;
			}

			String tmp = Integer.toHexString(x);

			// pad out "1" to "01" etc.
			if (tmp.length() == 1) {
				buffer.append("0");
			}

			buffer.append(tmp);
		}
		return buffer.toString();
	}


	private static final String dbCryptoSeed = "ddad617210cdd5fced765b60cf309da9";
	private static final String cryptoSeed = "f74de1d77975e92a4061fe31d8fe0656";

	public static String getCryptoSeed() {
		return cryptoSeed;
	}

	public static String getDbCryptoSeed() {
		return dbCryptoSeed;
	}

}
