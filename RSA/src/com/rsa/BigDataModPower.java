package com.rsa;

/**
 * ���������ģ����
 * @author C579
 *
 */
public class BigDataModPower {
	
	/*
	 * ���������ģ���㣬ͨ��λ�Ʋ�������� ���Ĺ�ʽ��'a^b % m = (...((a % m) * a) % m) ......* a) % m = (a % m)^b'
	 * ������a:���� b:ָ�� m������
	 * ���ؽ������ģ����
	 */
	public static int bigDataModPower(int a,int b,int m){
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
