package com.koal;

import java.util.Arrays;
import java.util.Base64;

public class DataFormTranslate {

	/*public static void main(String[] args) {
		byte[] data = new byte[]{97,65,66,98};
		String s1 = byteArrayToBase64(data);
		String s2 = byteArrayToHex(data);
		byte[] data1 = hexStringToBytes(s2);
		System.out.println("data: " + Arrays.toString(data));
		System.out.println("dataString: " + new String(data));
		System.out.println("s1: " + s1);
		System.out.println("s2: " + s2);
		System.out.println("data1: " +  Arrays.toString(data1));
	}*/
	/**
	 * 
	 * @param data byte[]类型
	 * @return base64 String 类型
	 */
	public static  String byteArrayToBase64(byte[] data){
		//Base64编码
		String base64 = Base64.getEncoder().encodeToString(data);
		return base64;
	}
	
	/**
	 * byte[] 数组转换为十六进制
	 * 
	 * @param theByte
	 *            byte[] 类型 待转换的bye[]类型数组
	 * @return 转换后的十六进制字符串
	 */
	public static String byteArrayToHex(byte[] data) {
		// 将byte类型转化为十六进制
		StringBuffer hexString = new StringBuffer();

		// 转换为十六进制的实现
		for (int i = 0; i < data.length; i++) {
			String hex = Integer.toHexString(0xff & data[i]);
			if (hex.length() == 1)
				hexString.append('0');
			hexString.append(hex);
		}
		return hexString.toString();
	}
	
	/**
	 * 十六进制字符串转byte[]数组
	 * @param hexString
	 * @return 转码后的byte[]数组
	 */
	public static byte[] hexStringToBytes(String hexString) {  
	    if (hexString == null || hexString.equals("")) {  
	        return null;  
	    }  
	    hexString = hexString.toUpperCase();  
	    int length = hexString.length() / 2;  
	    char[] hexChars = hexString.toCharArray();  
	    byte[] d = new byte[length];  
	    for (int i = 0; i < length; i++) {  
	        int pos = i * 2;  
	        d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));  
	    }  
	    return d;  
	}  
	
	/** 
	 * 将char转byte类型
	 * @param c char 
	 * @return byte 
	 */  
	 private static byte charToByte(char c) {  
	    return (byte) "0123456789ABCDEF".indexOf(c);  
	}  
}
