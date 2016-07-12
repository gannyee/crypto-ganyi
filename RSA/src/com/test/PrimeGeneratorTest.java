package com.test;

import java.security.SecureRandom;
import java.util.ArrayList;

/**
 * ������ʱ��������
 * 
 * @author C579
 *
 */
public class PrimeGeneratorTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		getRandomPrime(primeGenerator());
		getRandomPrime(primeGenerator());

	}

	/*
	 * �����б��������
	 * ���������贫��
	 * ����:primeList ArrayList<Integer>���ͣ�����200������������
	 */
	public static ArrayList<Integer> primeGenerator() {
		ArrayList<Integer> primeList = new ArrayList<Integer>();

		for (int i = 2; i <= 200; i++) {
			int flag = 0;
			for (int j = 2; j <= i - 1; j++) {
				if (i % j == 0) {
					flag = 1;
					break;
				}
			}
			if (flag == 0)
				primeList.add(i);
		}
		System.out.println(primeList);
		return primeList;
	}

	/*
	 * ���������������
	 * ������primeList ArrayList<Integer>����
	 * ���أ���
	 */
	public static void getRandomPrime(ArrayList<Integer> primeList) {
		int random = (int) (Math.random() * primeList.size() + 1);
		System.out.println("random index: " + primeList.get(random));
	}
}
