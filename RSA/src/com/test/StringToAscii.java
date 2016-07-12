package com.test;

import java.math.BigInteger;
import java.util.Arrays;

import com.sun.org.apache.xpath.internal.operations.Mod;

/**
 * 将字符串转为ascii码测试代码
 * @author C579
 *
 */
public class StringToAscii {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		stringToAscii("helloworld");
	}
	
	/*
	 * 将字符串转为ascii码
	 * 参数 String类型 originalString: 待要转为ascii码的原始字符串
	 * 返回 无
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
