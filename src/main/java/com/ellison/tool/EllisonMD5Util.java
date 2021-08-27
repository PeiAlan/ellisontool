package com.ellison.tool;


import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *  MD5 操作工具类
 * @author Ellison Pei
 * @date 2021-08-26 14:23
 * @version 1.0
 */
public class EllisonMD5Util {
	private static final char[] DIGITS = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E',
			'F' };

	/**
	 * The jce MD5 message digest generator.
	 */
	private static MessageDigest md5;

	public static final String encodeString(String string) throws RuntimeException {
		return byteToHex(digestString(string, null));
	}

	/**
	 * Retrieves a hexidecimal character sequence representing the MD5 digest of
	 * the specified character sequence, using the specified encoding to first
	 * convert the character sequence into a byte sequence. If the specified
	 * encoding is null, then ISO-8859-1 is assumed
	 * 
	 * @param string
	 *            the string to encode.
	 * @param encoding
	 *            the encoding used to convert the string into the byte sequence
	 *            to submit for MD5 digest
	 * @return a hexidecimal character sequence representing the MD5 digest of
	 *         the specified string
	 * @throws
	 *             if an MD5 digest algorithm is not available through the
	 *             java.security.MessageDigest spi or the requested encoding is
	 *             not available
	 */
	public static final String encodeString(String string, String encoding) throws RuntimeException {
		return byteToHex(digestString(string, encoding));
	}

	/**
	 * Retrieves a byte sequence representing the MD5 digest of the specified
	 * character sequence, using the specified encoding to first convert the
	 * character sequence into a byte sequence. If the specified encoding is
	 * null, then ISO-8859-1 is assumed.
	 * 
	 * @param string
	 *            the string to digest.
	 * @param encoding
	 *            the character encoding.
	 * @return the digest as an array of 16 bytes.
	 * @throws
	 *             if an MD5 digest algorithm is not available through the
	 *             java.security.MessageDigest spi or the requested encoding is
	 *             not available
	 */
	public static byte[] digestString(String string, String encoding) throws RuntimeException {

		byte[] data;

		if (encoding == null) {
			encoding = "ISO-8859-1";
		}

		try {
			data = string.getBytes(encoding);
		} catch (UnsupportedEncodingException x) {
			throw new RuntimeException(x.toString());
		}

		return digestBytes(data);
	}

	/**
	 * Retrieves a byte sequence representing the MD5 digest of the specified
	 * byte sequence.
	 * 
	 * @param data
	 *            the data to digest.
	 * @return the MD5 digest as an array of 16 bytes.
	 * @throws
	 *             if an MD5 digest algorithm is not available through the
	 *             java.security.MessageDigest spi
	 */
	public static final byte[] digestBytes(byte[] data) throws RuntimeException {

		synchronized (EllisonMD5Util.class) {
			if (md5 == null) {
				try {
					md5 = MessageDigest.getInstance("MD5");
				} catch (NoSuchAlgorithmException e) {
					throw new RuntimeException(e.toString());
				}
			}

			return md5.digest(data);
		}
	}

	private static final char HEXCHAR[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e',
			'f' };

	public static String byteToHex(byte b[]) {

		int len = b.length;
		char[] s = new char[len * 2];

		for (int i = 0, j = 0; i < len; i++) {
			int c = ((int) b[i]) & 0xff;

			s[j++] = HEXCHAR[c >> 4 & 0xf];
			s[j++] = HEXCHAR[c & 0xf];
		}

		return new String(s);
	}

	public static String getFileMd5(String filename) throws Exception {
		return getFileMd5(filename, null);
	}

	public static String getFileMd5(String filename, String encoding) throws Exception {
		encoding = encoding == null ? "ISO-8859-1" : encoding;
		File f = new File(filename);

		if (!f.exists()) {
			return "";
		}
		InputStream is = new FileInputStream(f);
		byte[] buffer = new byte[1024];
		MessageDigest digest = MessageDigest.getInstance("MD5");

		int count;
		while ((count = is.read(buffer)) > 0) {
			digest.update(buffer, 0, count);
		}
		is.close();
		byte[] md5sum = digest.digest();
		String output = new String(encodeHex(md5sum)).toLowerCase();
		return output;
	}

	public static String getFileMd5(File file) throws Exception {
		return getFileMd5(file, null);
	}

	public static String getFileMd5(File file, String encoding) throws Exception {
		encoding = encoding == null ? "ISO-8859-1" : encoding;
		InputStream is = new FileInputStream(file);
		byte[] buffer = new byte[1024];
		MessageDigest digest = MessageDigest.getInstance("MD5");

		int count;
		while ((count = is.read(buffer)) > 0) {
			digest.update(buffer, 0, count);
		}
		is.close();
		byte[] md5sum = digest.digest();
		String output = new String(encodeHex(md5sum)).toLowerCase();

		return output;
	}

	public static String getStringMD5(String s) {
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
		try {
			byte[] strTemp = s.getBytes("UTF-8");

			// 使用MD5创建MessageDigest对象
			MessageDigest mdTemp = MessageDigest.getInstance("MD5");
			mdTemp.update(strTemp);
			byte[] md = mdTemp.digest();
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte b = md[i];
				// System.out.println((int)b);
				// 将没个数(int)b进行双字节加密
				str[k++] = hexDigits[b >> 4 & 0xf];
				str[k++] = hexDigits[b & 0xf];
			}
			return new String(str);
		} catch (Exception e) {
			return null;
		}
	}

	public static char[] encodeHex(byte[] data) {
		int l = data.length;
		char[] out = new char[l << 1];
		// two characters form the hex value.
		int j = 0;
		for (int i = 0; i < l; i++) {
			out[j++] = DIGITS[(0xF0 & data[i]) >>> 4];
			out[j++] = DIGITS[0x0F & data[i]];
		}
		return out;
	}

	public static String toMD5(String text, String charset) {
		MessageDigest msgDigest = null;
		try {
			msgDigest = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			throw new IllegalStateException("System doesn't support MD5 algorithm.");
		}
		try {
			msgDigest.update(text.getBytes(charset));
		} catch (UnsupportedEncodingException e) {
			throw new IllegalStateException("System doesn't support your EncodingException.");
		}
		byte[] bytes = msgDigest.digest();
		String md5Str = new String(encodeHex(bytes));
		return md5Str;
	}

	public static String toMD5(String text) {
		return toMD5(text, "utf-8");
	}

	public static String getFileMd5(String filename, String attachStr, String encoding) throws Exception {
		encoding = (encoding == null) ? "ISO-8859-1" : encoding;
		File f = new File(filename);
		if (!f.exists()) {
			return "";
		}
		InputStream is = new FileInputStream(f);
		byte[] buffer = new byte[1024];
		MessageDigest digest = MessageDigest.getInstance("MD5");

		int count;
		while ((count = is.read(buffer)) > 0) {
			digest.update(buffer, 0, count);
		}
		is.close();
		digest.update(attachStr.getBytes(), 0, attachStr.getBytes().length);
		byte[] md5sum = digest.digest();
		String output = new String(encodeHex(md5sum)).toLowerCase();

		return output;
	}

	public static String getKeyFileMd5(String filename, String attachStr, String encoding) throws Exception {
		encoding = (encoding == null) ? "ISO-8859-1" : encoding;
		File f = new File(filename);
		if (!f.exists()) {
			return "";
		}
		InputStream is = new FileInputStream(f);
		byte[] buffer = new byte[1024];
		MessageDigest digest = MessageDigest.getInstance("MD5");
		digest.update(attachStr.getBytes(), 0, attachStr.getBytes().length);
		int count;
		while ((count = is.read(buffer)) > 0) {
			digest.update(buffer, 0, count);
		}
		is.close();
		byte[] md5sum = digest.digest();
		String output = new String(encodeHex(md5sum)).toLowerCase();
		return output;
	}

	public static void main(String[] args) {
		String aString = "YG<?xml version=\"1.0\" encoding=\"UTF-8\"?><Package><Header><Asyn>0</Asyn><PageReturnUrl></PageReturnUrl><ProductCode>YG160316</ProductCode><RequestType>01</RequestType><ReturnUrl></ReturnUrl><SendTime>2016-02-18 21:52:52</SendTime><UserId>0999960000019772</UserId><WBSerial>1602180K002027d9vRyAYEByLuWv6Ftk</WBSerial></Header><Request><Benefit><IsLegal>1</IsLegal></Benefit><Holder><HolderAddress>广东省，深圳市，选择区刚刚割发代首山山水水</HolderAddress><HolderBirthday>1990-11-09</HolderBirthday><HolderCardNo>500101199011091018</HolderCardNo><HolderCardType>0</HolderCardType><HolderEmail>55643287@qq.com</HolderEmail><HolderMobile>18665326497</HolderMobile><HolderName>davidjqli</HolderName><HolderPhone></HolderPhone><HolderSex>0</HolderSex><OccupationCode></OccupationCode></Holder><InsuredInfo><IsHolder>1</IsHolder></InsuredInfo><Item><Amount></Amount><Premium>100000</Premium><ProductCode>YG160316</ProductCode><ProductName>阳光稳添益</ProductName><SkuCode>160316</SkuCode><SpecialCode></SpecialCode></Item><Order><ApplyNum>1</ApplyNum><InsBeginDate></InsBeginDate><InsEndDate></InsEndDate><InsPeriod></InsPeriod><OrderId>1602180K002027d9vRyAYEByLuWv6Ftk</OrderId><TotalPremium>100000</TotalPremium></Order><OtherInfo><IsPostInvoice>0</IsPostInvoice><PolicyType>1</PolicyType><PostAddress></PostAddress><PostZip></PostZip></OtherInfo></Request></Package>";
		StringBuffer bf = new StringBuffer();
//		bf.append("jfjr123")
//		  .append("2016-08-05 14:38:46")
//		  .append("57a434767105b1470379126")
//		  .append("http://eas.f3322.org:8007/payto/notify_callback/yangguangrs_pay")
//		  .append("http://eas.f3322.org:8007/payto/return_callback/yangguangrs_pay")
//		  .append("JFJR1470213202298")
//		  .append("1008320726853918")
//		  .append("钦一")
//		  .append("6227001291082482730")
//		  .append("0101041")
//		  .append("13008843884")
//		  .append("0.01")
//		  .append("672724");
//		bf.append("YGOLTSR-0001").append("95510RTIIQIBXAXAXl");
//		System.out.println(MD5Util.getStringMD5(bf.toString()));
//		bf = new StringBuffer();
//		bf.append("YGOLTSR-0002").append("95510RTIIQIBXAXAXl");
//		System.out.println(MD5Util.getStringMD5(bf.toString()));
//		bf = new StringBuffer();
//		bf.append("YGOLTSR-0003").append("95510RTIIQIBXAXAXl");
//		System.out.println(MD5Util.getStringMD5(bf.toString()));
//		bf = new StringBuffer();
//		bf.append("YGOLTSR-0004").append("95510RTIIQIBXAXAXl");
//		System.out.println(MD5Util.getStringMD5(bf.toString()));
//		bf = new StringBuffer();
//		bf.append("YGOLTSR-0005").append("95510RTIIQIBXAXAXl");
//		System.out.println(MD5Util.getStringMD5(bf.toString()));
//		bf = new StringBuffer();
//		bf.append("YGOLTSR-0006").append("95510RTIIQIBXAXAXl");
//		System.out.println(MD5Util.getStringMD5(bf.toString()));
//		bf = new StringBuffer();
//		bf.append("YGOLTSR-0007").append("95510RTIIQIBXAXAXl");
//		System.out.println(MD5Util.getStringMD5(bf.toString()));
//		bf = new StringBuffer();
//		bf.append("YGOLTSR-0008").append("95510RTIIQIBXAXAXl");
//		System.out.println(MD5Util.getStringMD5(bf.toString()));
//		bf = new StringBuffer();
//		bf.append("YGOLTSR-0009").append("95510RTIIQIBXAXAXl");
//		System.out.println(MD5Util.getStringMD5(bf.toString()));
//		bf = new StringBuffer();
//		bf.append("YGOLTSR-0010").append("95510RTIIQIBXAXAXl");
//		System.out.println(MD5Util.getStringMD5(bf.toString()));
//		bf = new StringBuffer();
//		bf.append("YGOLTSR-0011").append("95510RTIIQIBXAXAXl");
//		System.out.println(MD5Util.getStringMD5(bf.toString()));
//		bf = new StringBuffer();
//		bf.append("YGOLTSR-0012").append("95510RTIIQIBXAXAXl");
//		System.out.println(MD5Util.getStringMD5(bf.toString()));
		//System.out.println(MD5Util.getStringMD5("5860042450"+"13511111111"+"95510RTIIQIBXAXAXl"));
		
		
	}

}

