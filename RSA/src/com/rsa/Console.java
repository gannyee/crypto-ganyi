package com.rsa;

import java.io.IOException;
import java.util.Scanner;

/**
 * console对话界面
 * @author C579
 *
 */
public class Console {
	
	/*
	 * 控制台初始对话
	 */
	public static void dialogue(){
			System.out.println("加密明文： rsa encrypt 明文内容 示例 rsa encrypt 'hello rsa' ");
			System.out.println("返回当前加密时系统生成的大素数p,q: rsa -pq");
			System.out.println("解密密文：rsa decrypt 密钥  密文文件所在路径 示例：rsa decrypt (123,2345) d://miwen.txt ");
	}
	
	/*
	 * 控制台输出内容
	 * 指示用户的输入规范
	 */
	public static void command(String commandLine) throws IOException{
		RSA rsa = new RSA();
		if(commandLine.equals("rsa -help")){
			dialogue();
		}else if(commandLine.contains("rsa encrypt")){
			rsa.encrypt(commandLine.substring("rsa encrypt".length()));
			System.out.println("公钥： (" + rsa.getE() + " , " + rsa.getN() + " )");
			System.out.println("密钥： (" + rsa.getD() + " , " + rsa.getN() + " )");
			
		}else if(commandLine.equals("rsa -pq")){
			System.out.println("系统生成的两个用于加密的素数：( " + rsa.getP() + " , " + rsa.getQ() + " )");
		}else if(commandLine.contains("rsa decrypt")){
			String s = "";
			for(int i = "rsa decrypt ".length() + 1 ;commandLine.charAt(i) !=  ')';i ++){
				s = s + commandLine.charAt(i);
			}
			String fileName = commandLine;
			String encrypt1 = fileName.substring("rsa decrypt ".length() + s.length() + 3);
			String[] s1 = s.split(",");
			int d = Integer.parseInt(s1[0]);
			int n = Integer.parseInt(s1[1]);
			System.out.println("解密后内容： " + rsa.decrypt(encrypt1, d, n));
		}
	}
}
