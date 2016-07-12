package com.rsa;

import java.io.IOException;
import java.util.Scanner;

/**
 * console�Ի�����
 * @author C579
 *
 */
public class Console {
	
	/*
	 * ����̨��ʼ�Ի�
	 */
	public static void dialogue(){
			System.out.println("�������ģ� rsa encrypt �������� ʾ�� rsa encrypt 'hello rsa' ");
			System.out.println("���ص�ǰ����ʱϵͳ���ɵĴ�����p,q: rsa -pq");
			System.out.println("�������ģ�rsa decrypt ��Կ  �����ļ�����·�� ʾ����rsa decrypt (123,2345) d://miwen.txt ");
	}
	
	/*
	 * ����̨�������
	 * ָʾ�û�������淶
	 */
	public static void command(String commandLine) throws IOException{
		RSA rsa = new RSA();
		if(commandLine.equals("rsa -help")){
			dialogue();
		}else if(commandLine.contains("rsa encrypt")){
			rsa.encrypt(commandLine.substring("rsa encrypt".length()));
			System.out.println("��Կ�� (" + rsa.getE() + " , " + rsa.getN() + " )");
			System.out.println("��Կ�� (" + rsa.getD() + " , " + rsa.getN() + " )");
			
		}else if(commandLine.equals("rsa -pq")){
			System.out.println("ϵͳ���ɵ��������ڼ��ܵ�������( " + rsa.getP() + " , " + rsa.getQ() + " )");
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
			System.out.println("���ܺ����ݣ� " + rsa.decrypt(encrypt1, d, n));
		}
	}
}
