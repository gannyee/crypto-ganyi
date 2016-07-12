package com.test;

/**
 * ������ģ����Ĳ��Դ���
 * @author C579
 *
 */
public class ModPower {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(runFermatPower(65, 17, 3233));
	}
	
	/*
	 * ���������ģ����
	 * ������a:���� b:ָ�� m������
	 * ���أ�result int���� ��ģ����
	 */
	public static int runFermatPower(int a,int b,int m){
		int result = 1;
		while(b > 0){
			if((b & 1) == 1)
				result = (result * a) % m;
			a = (a * a) % m;
			b >>= 1;
		}
		
		return result;
	}
}
