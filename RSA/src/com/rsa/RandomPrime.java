package com.rsa;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 产生随时素数
 * @author C579
 *
 */
public class RandomPrime {
	
	static int[] ab = new int[2];
	/*
	 * 素数列表随机生成
	 * 参数：无需传入
	 * 返回:primeList ArrayList<Integer>类型，返回200以内所有素数
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
	 * 生成两个随机素数
	 * 参数：primeList ArrayList<Integer>类型
	 * 返回：无
	 */
	public static void generateRandomPrime(ArrayList<Integer> primeList){
		Integer primeA = primeList.get((int) ( Math.random() * primeList.size() + 1));
		Integer primeB = primeList.get((int) ( Math.random() * primeList.size() + 1));
		ab = new int[]{primeA,primeB};
	}
	
	/*
	 * 返回生成的两个素数
	 * 参数：无
	 * 返回：ab int[] 类型
	 */
	public static int[] generatePrime(){
		return ab;
	}
}
