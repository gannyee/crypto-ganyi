package com.test;

/**
 * 大数幂模运算的测试代码
 * @author C579
 *
 */
public class ModPower {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(runFermatPower(65, 17, 3233));
	}
	
	/*
	 * 解决大数幂模运算
	 * 参数：a:底数 b:指数 m：除数
	 * 返回：result int类型 求模余数
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
