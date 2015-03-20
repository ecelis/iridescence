package org.compiere.util;

// This import is only for the main() example.  It is not required by TEAV.

public class TEAV
{
	private int _key[];	// The 128 bit key.
	private byte _keyBytes[];	// original key as found
	private int _padding;		// amount of padding added in byte --> integer conversion.

	/**
	* Array of hex char mappings.
	* hex[0] = '0', hex[15] = 'F'.
	*/
	protected static final char hex[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };

	/**
	* Accepts key for enciphering/deciphering.
	*
	* @throws ArrayIndexOutOfBoundsException if the key isn't the correct length.
	*
	* @param key 128 bit (16 byte) key.
	*/
	public TEAV(byte key[])
	{
		int klen = key.length;
		_key = new int[4];

		// Incorrect key length throws exception.
		if (klen != 16)
			throw new ArrayIndexOutOfBoundsException(this.getClass().getName() + ": Key is not 16 bytes");

		int j, i;
		for (i = 0, j = 0; j < klen; j += 4, i++)
			_key[i] = (key[j] << 24 ) | (((key[j+1])&0xff) << 16) | (((key[j+2])&0xff) << 8) | ((key[j+3])&0xff);

		_keyBytes = key;	// save for toString.
	}

	public TEAV(int key[])
	{
		_key = key;
	}

	/**
	* Representation of TEA class
	*/
	public String toString()
	{
		String tea = this.getClass().getName();
		tea +=  ": Tiny Encryption Algorithm (TEA)  key: " + getHex(_keyBytes);
		return tea;
	}

	/**
	* Encipher two <code>int</code>s.
	* Replaces the original contents of the parameters with the results.
	* The integers are usually created from 8 bytes.
	* The usual way to collect bytes to the int array is:
	* <PRE>
	* byte ba[] = { .... };
	* int v[] = new int[2];
	* v[0] = (ba[j] << 24 ) | (((ba[j+1])&0xff) << 16) | (((ba[j+2])&0xff) << 8) | ((ba[j+3])&0xff);
	* v[1] = (ba[j+4] << 24 ) | (((ba[j+5])&0xff) << 16) | (((ba[j+6])&0xff) << 8) | ((ba[j+7])&0xff);
	* v = encipher(v);
	* </PRE>
	*
	* @param v two <code>int</code> array as input.
	*
	* @return array of two <code>int</code>s, enciphered.
	*/
	public int [] encipher(int v[])
	{
		int y= v[0];
		int z= v[1];
		int sum=0;
		int delta=0x9E3779B9;

		int n=32;

		while(n-->0)
		{
			y += (z << 4 ^ z >>> 5) + z ^ sum + _key[(int)(sum&3)];
			sum += delta;
			z += (y << 4 ^ y >>> 5) + y ^ sum + _key[(int)(sum >>>11) & 3];
		}

		int w[] = new int[2];
		w[0] = (int)y;
		w[1] = (int)z;

		return w;
	}

	/**
	* Decipher two <code>int</code>s.
	* Replaces the original contents of the parameters with the results.
	* The integers are usually decocted to 8 bytes.
	* The decoction of the <code>int</code>s to bytes can be done
	* this way.
	* <PRE>
	* int x[] = decipher(ins);
	* outb[j]   = (byte)(x[0] >>> 24);
	* outb[j+1] = (byte)(x[0] >>> 16);
	* outb[j+2] = (byte)(x[0] >>> 8);
	* outb[j+3] = (byte)(x[0]);
	* outb[j+4] = (byte)(x[1] >>> 24);
	* outb[j+5] = (byte)(x[1] >>> 16);
	* outb[j+6] = (byte)(x[1] >>> 8);
	* outb[j+7] = (byte)(x[1]);
	* </PRE>
	*
	* @param v <code>int</code> array of 2
	*
	* @return deciphered <code>int</code> array of 2
	*/
	public int [] decipher(int v[])
	{
		int y=v[0];
		int z=v[1];
		int sum=0xC6EF3720;
		int delta=0x9E3779B9;

		int n=32;

	   // sum = delta<<5, in general sum = delta * n

		while (n-->0)
		{
			z -= (y << 4 ^ y >>> 5) + y ^ sum + _key[(sum>>>11) & 3];
			sum -= delta;
			y -= (z << 4 ^ z >>> 5) + z ^ sum + _key[sum &3];
		}

		int w[] = new int[2];
		w[0] = (int)y;
		w[1] = (int)z;

		return w;
	}

	/**
	* Byte wrapper for encoding.
	* Converts bytes to ints.
	* Padding will be added if required.
	*
	* @param b incoming <code>byte</code> array
	*
	* @param byte count
	*
	* @return integer conversion array, possibly with padding.
	*
	* @see #padding
	*/
	public int [] encode(byte b[], int count)
	{
		int j ,i;
		int bLen = count;
		byte bp[] = b;

		_padding = bLen % 8;
		if (_padding != 0)	// Add some padding, if necessary.
		{
			_padding = 8 - (bLen % 8);
			bp = new byte[bLen + _padding];
			System.arraycopy(b, 0, bp, 0, bLen);
			bLen = bp.length;
		}

		int intCount = bLen / 4;
		int r[] = new int[2];
		int out[] = new int[intCount];

		for (i = 0, j = 0; j < bLen; j += 8, i += 2)
		{
			r[0] = (bp[j] << 24 ) | (((bp[j+1])&0xff) << 16) | (((bp[j+2])&0xff) << 8) | ((bp[j+3])&0xff);
			r[1] = (bp[j+4] << 24 ) | (((bp[j+5])&0xff) << 16) | (((bp[j+6])&0xff) << 8) | ((bp[j+7])&0xff);
			r = encipher(r);
			out[i] = r[0];
			out[i+1] = r[1];
		}

		return out;
	}

	/**
	* Report how much padding was done in the last encode.
	*
	* @return bytes of padding added
	*
	* @see #encode
	*/
	public int padding()
	{
		return _padding;
	}

	/**
	* Convert a byte array to ints and then decode.
	* There may be some padding at the end of the byte array from
	* the previous encode operation.
	*
	* @param b bytes to decode
	* @param count number of bytes in the array to decode
	*
	* @return <code>byte</code> array of decoded bytes.
	*/
	public byte [] decode(byte b[], int count)
	{
		int i, j;

		int intCount = count / 4;
		int ini[] = new int[intCount];
		for (i = 0, j = 0; i < intCount; i += 2, j += 8)
		{
			ini[i] = (b[j] << 24 ) | (((b[j+1])&0xff) << 16) | (((b[j+2])&0xff) << 8) | ((b[j+3])&0xff);
			ini[i+1] = (b[j+4] << 24 ) | (((b[j+5])&0xff) << 16) | (((b[j+6])&0xff) << 8) | ((b[j+7])&0xff);
		}
		return decode(ini);
	}

	/**
	* Decode an integer array.
	* There may be some padding at the end of the byte array from
	* the previous encode operation.
	*
	* @param b bytes to decode
	* @param count number of bytes in the array to decode
	*
	* @return <code>byte</code> array of decoded bytes.
	*/
	public byte [] decode(int b[])
	{
		// create the large number and start stripping ints out, two at a time.
		int intCount = b.length;

		byte outb[] = new byte[intCount * 4];
		int tmp[] = new int[2];

		// decipher all the ints.
		int i, j;
		for (j = 0, i = 0; i < intCount; i += 2, j += 8)
		{
			tmp[0] = b[i];
			tmp[1] = b[i+1];
			tmp = decipher(tmp);
			outb[j]   = (byte)(tmp[0] >>> 24);
			outb[j+1] = (byte)(tmp[0] >>> 16);
			outb[j+2] = (byte)(tmp[0] >>> 8);
			outb[j+3] = (byte)(tmp[0]);
			outb[j+4] = (byte)(tmp[1] >>> 24);
			outb[j+5] = (byte)(tmp[1] >>> 16);
			outb[j+6] = (byte)(tmp[1] >>> 8);
			outb[j+7] = (byte)(tmp[1]);
		}

		return outb;
	}

	/**
	* Convert a string into an integer array form suitable for decoding.
	*
	* @param hexStr String of hexadecimal digits.
	* @return integer array.
	* @throws ArrayIndexOutOfBoundsException if the string length is not divisible into integer length pieces.
	*/
	public int [] hexToBin(String hexStr) throws ArrayIndexOutOfBoundsException
	{
		int hexStrLen = hexStr.length();

		// Decode a hex string into a collection of ints.
		if ((hexStrLen % 8) != 0)
			throw new ArrayIndexOutOfBoundsException("Hex string has incorrect length, required to be divisible by eight: " + hexStrLen);

		int outLen = hexStrLen / 8;
		int out[] = new int[outLen];
		byte nibble[] = new byte[2];
		byte b[] = new byte[4];
		int posn = 0;
		for (int i = 0; i < 	outLen; i++)
		{
			for (int j = 0; j < 4; j++)
			{
				for (int k = 0; k < 2; k++)
				{
					switch (hexStr.charAt(posn++))
					{
						case '0': nibble[k] = (byte)0; break;
						case '1': nibble[k] = (byte)1; break;
						case '2': nibble[k] = (byte)2; break;
						case '3': nibble[k] = (byte)3; break;
						case '4': nibble[k] = (byte)4; break;
						case '5': nibble[k] = (byte)5; break;
						case '6': nibble[k] = (byte)6; break;
						case '7': nibble[k] = (byte)7; break;
						case '8': nibble[k] = (byte)8; break;
						case '9': nibble[k] = (byte)9; break;
						case 'A': nibble[k] = (byte)0xA; break;
						case 'B': nibble[k] = (byte)0xB; break;
						case 'C': nibble[k] = (byte)0xC; break;
						case 'D': nibble[k] = (byte)0xD; break;
						case 'E': nibble[k] = (byte)0xE; break;
						case 'F': nibble[k] = (byte)0xF; break;
						case 'a': nibble[k] = (byte)0xA; break;
						case 'b': nibble[k] = (byte)0xB; break;
						case 'c': nibble[k] = (byte)0xC; break;
						case 'd': nibble[k] = (byte)0xD; break;
						case 'e': nibble[k] = (byte)0xE; break;
						case 'f': nibble[k] = (byte)0xF; break;
					}
				}

				b[j] = (byte)(nibble[0] << 4 | nibble[1]);
			}
			out[i] = (b[0] << 24 ) | (((b[1])&0xff) << 16) | (((b[2])&0xff) << 8) | ((b[3])&0xff);
		}

		return out;
	}


	/**
	* Convert an array of ints into a hex string.
	*
	* @param enc Array of integers.
	* @return String hexadecimal representation of the integer array.
	* @throws ArrayIndexOutOfBoundsException if the array doesn't contain pairs of integers.
	*/
	public String binToHex(int enc[]) throws ArrayIndexOutOfBoundsException
	{
		// The number of ints should always be a multiple of two as required by TEA (64 bits).
		if ((enc.length % 2)	== 1)
			throw new ArrayIndexOutOfBoundsException("Odd number of ints found: " + enc.length);

		StringBuffer sb = new StringBuffer();
		byte outb[] = new byte[8];
		int tmp[] = new int[2];
		int counter = enc.length / 2;

		for (int i = 0; i < enc.length; i += 2)
		{
			outb[0]   = (byte)(enc[i] >>> 24);
			outb[1] = (byte)(enc[i] >>> 16);
			outb[2] = (byte)(enc[i] >>> 8);
			outb[3] = (byte)(enc[i]);
			outb[4] = (byte)(enc[i+1] >>> 24);
			outb[5] = (byte)(enc[i+1] >>> 16);
			outb[6] = (byte)(enc[i+1] >>> 8);
			outb[7] = (byte)(enc[i+1]);

			sb.append(getHex(outb));
		}

		return sb.toString();
	}

	// Display some bytes in HEX.
	//
	public String getHex(byte b[])
	{
		StringBuffer r = new StringBuffer();

		for (int i = 0; i < b.length; i++)
		{
			int c = ((b[i]) >>> 4) & 0xf;
			r.append(hex[c]);
			c = ((int)b[i] & 0xf);
			r.append(hex[c]);
		}

		return r.toString();
	}

	/**
	* Pad a string out to the proper length with the given character.
	*
	* @param str Plain text string.
	* @param pc Padding character.
	*/
	public String padPlaintext(String str, char pc)
	{
		StringBuffer sb = new StringBuffer(str);
		int padding = sb.length() % 8;
		for (int i = 0; i < padding; i++)
			sb.append(pc);

		return sb.toString();
	}

	/**
	* Pad a string out to the proper length with spaces.
	*
	* @param str Plain text string.
	*/
	public String padPlaintext(String str)
	{
		return padPlaintext(str, ' ');
	}

}
