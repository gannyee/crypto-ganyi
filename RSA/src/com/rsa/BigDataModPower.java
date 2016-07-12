package com.rsa;

/**
 * 解决大数幂模运算
 * @author C579
 *
 */
public class BigDataModPower {
	
	/*
	 * 解决大数幂模运算，通过位移拆分幂运算 核心公式：'a^b % m = (...((a % m) * a) % m) ......* a) % m = (a % m)^b'
	 * 参数：a:底数 b:指数 m：除数
	 * 返回结果：求模余数
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
