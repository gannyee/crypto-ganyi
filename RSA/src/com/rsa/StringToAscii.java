package com.rsa;

/**
 * 将字符串转为ascii码
 * @author C579
 *
 */
public class StringToAscii {
	
	/*
	 * 将字符串转为ascii码
	 * 参数 String类型 originalString: 待要转为ascii码的原始字符串
	 * 返回 int[]组类型 originalStringArray：
	 */
	public static int[] stringToAscii(String originalString){
		//originalStringArray 用于存放字符串对应字符的ascii码
		int[] originalStringArray = new int[originalString.length()];
		int len = originalString.length();
		for(int i = 0;i < len;i ++)
			originalStringArray[i] = originalString.charAt(i);
		return originalStringArray;
	}
}
