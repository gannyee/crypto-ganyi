package com.rsa;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * ������ʱ����
 * @author C579
 *
 */
public class RandomPrime {
	
	static int[] ab = new int[2];
	/*
	 * �����б��������
	 * ���������贫��
	 * ����:primeList ArrayList<Integer>���ͣ�����200������������
	 */
	public static ArrayList<Integer> primeList(){
		ArrayList<Integer> primeList = new ArrayList<Integer>();
		
		for(int i = 2;i <= 200;i ++){
			int flag = 0;
			for(int j = 2;j <= i - 1;j ++){
				if(i % j == 0){
					flag = 1;
					break;
				}
			}
			if(flag == 0)
				primeList.add(i);
		}
		return primeList;
	}
	
	/*
	 * ���������������
	 * ������primeList ArrayList<Integer>����
	 * ���أ���
	 */
	public static void generateRandomPrime(ArrayList<Integer> primeList){
		Integer primeA = primeList.get((int) ( Math.random() * primeList.size() + 1));
		Integer primeB = primeList.get((int) ( Math.random() * primeList.size() + 1));
		ab = new int[]{primeA,primeB};
	}
	
	/*
	 * �������ɵ���������
	 * ��������
	 * ���أ�ab int[] ����
	 */
	public static int[] generatePrime(){
		return ab;
	}
}
