package com.test;

import java.security.SecureRandom;
import java.util.ArrayList;

/**
 * 产生随时素数测试
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
	 * 素数列表随机生成
	 * 参数：无需传入
	 * 返回:primeList ArrayList<Integer>类型，返回200以内所有素数
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
	 * 生成两个随机素数
	 * 参数：primeList ArrayList<Integer>类型
	 * 返回：无
	 */
	public static void getRandomPrime(ArrayList<Integer> primeList) {
		int random = (int) (Math.random() * primeList.size() + 1);
		System.out.println("random index: " + primeList.get(random));
	}
}
