package com.test;

import java.math.BigInteger;
import java.util.Arrays;

import com.sun.org.apache.xpath.internal.operations.Mod;

/**
 * ���ַ���תΪascii����Դ���
 * @author C579
 *
 */
public class StringToAscii {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		stringToAscii("helloworld");
	}
	
	/*
	 * ���ַ���תΪascii��
	 * ���� String���� originalString: ��ҪתΪascii���ԭʼ�ַ���
	 * ���� ��
	 */
	public static void stringToAscii(String message){
		
		
		byte[] array = message.getBytes();
		int[] array1 = new int[array.length];
		String s = "";
		for(int i = 0;i < array.length;i ++){
			
			array1[i] = (int) Math.pow(array[i],2) % 76;
			s = s + (char)array1[i];
		}
		System.out.println("array1: " + Arrays.toString(array1));
		System.out.println("array: " + Arrays.toString(array));
		System.out.println("s: " + s + "ascii: " + Arrays.toString(s.getBytes()));
	}
}
