package com.rsa;

/**
 * ���ַ���תΪascii��
 * @author C579
 *
 */
public class StringToAscii {
	
	/*
	 * ���ַ���תΪascii��
	 * ���� String���� originalString: ��ҪתΪascii���ԭʼ�ַ���
	 * ���� int[]������ originalStringArray��
	 */
	public static int[] stringToAscii(String originalString){
		//originalStringArray ���ڴ���ַ�����Ӧ�ַ���ascii��
		int[] originalStringArray = new int[originalString.length()];
		int len = originalString.length();
		for(int i = 0;i < len;i ++)
			originalStringArray[i] = originalString.charAt(i);
		return originalStringArray;
	}
}
