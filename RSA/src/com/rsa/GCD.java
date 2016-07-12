package com.rsa;

/**
 * 计算两数的最大公约数,亦或判断两数是否互质
 * 参数：a,b待求最大公约数的初始值
 * 返回a,b两个数的最大公约数
 */
public class GCD {
	
	public static void main(String[] args) {
		System.out.println(gcd(26,14));
	}
	/*
	 * 递归算法计算两数的最大公约数
	 * 参数：a,b int类型 待求最大公约数
	 * 返回：int类型 a,b最大公约数
	 */
	public static int gcd(int a,int b){
		if(b == 0)
			return a;
		return gcd(b,a % b);
	}
}
