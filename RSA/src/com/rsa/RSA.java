package com.rsa;

import java.io.IOException;
import java.util.Arrays;
import java.util.Base64;

/**
 * RSA算法的具体实现
 * @author C579
 *
 */
public class RSA {

	// 定义p,q,n,e,on
	private static int p;
	private static int q;
	private static int n;
	private static int e;
	private static int on;
	private static int d;

	// 构造函数，初始化p,q,n,on
	public RSA() {
		// 产生随机的质数p,q
		RandomPrime rp = new RandomPrime();
		rp.generateRandomPrime(rp.primeList());
		p = RandomPrime.generatePrime()[0];
		q = RandomPrime.generatePrime()[1];
		// 计算n
		n = p * q;
		// 计算p,q的欧拉数
		on = (p - 1) * (q - 1);
		// 随机选取0 < e < on
		e = 1;
		// 保证随机选取的e和on是互质
		while (on % e == 0) {
			e++;
		}
		// 计算私钥
		d = 1;
		while (((d * e) % on) != 1) {
			d++;
		}
	}

	/*
	 * 内部私有加密方法，外部函数无法调用，明文加密 参数： message String类型 欲加密的明文；e int类型 密钥；n int类型
	 * 用于加密 返回值：无返回值
	 */
	private static void encrypt(String message, int e, int n) throws IOException {
		// 计算大数的幂模运算
		BigDataModPower bdm = new BigDataModPower();

		// 将字符串转为ascii码
		int[] array = new StringToAscii().stringToAscii(message);

		// encryptArray用于存放加密后每个字符的密文
		int[] encrytpArray = new int[array.length];

		// 对ascii码数据进行明文加密
		for (int i = 0; i < array.length; i++) {
			encrytpArray[i] = bdm.bigDataModPower(array[i], e, n);
		}

		// 对加密的密文进行base64编码转码
		String base64 = Base64.getEncoder().encodeToString(Arrays.toString(encrytpArray).getBytes());
		System.out.println("加密之后： " + base64);
	}

	/*
	 * 外部可调用加密方法 参数： message String类型 欲加密的明文 返回值：无返回值
	 */
	public static void encrypt(String message) throws IOException {
		encrypt(message, getE(), getN());
	}

	/*
	 * 密文解密，将base编码的密文传值过来，对密文进行解密 参数： encrypt String类型 密文；d int类型 密钥；n int类型
	 * 用于解密 返回值：decryptMessage String类型 解密后的明文
	 */
	public static String decrypt(String encrypt, int d, int n) {
		// 对base64编码的密文进行解密
		byte[] asByte = Base64.getDecoder().decode(encrypt);
		// 将byte[] 类型的密文转为String[] 类型，便于之后的String类型转为int类型
		String[] newS = new String(asByte).substring(1, (new String(asByte).length() - 1)).split(", ");

		// String类型转int类型
		int[] array = new int[newS.length];
		for (int i = 0; i < newS.length; i++) {
			array[i] = Integer.parseInt(newS[i]);
		}

		// 解密，大数幂模运算
		BigDataModPower bdm = new BigDataModPower();
		int len = array.length;
		int[] decryptArray = new int[array.length];
		for (int i = 0; i < len; i++) {
			decryptArray[i] = bdm.bigDataModPower(array[i], d, n);
		}

		// 解密后的明文
		String decryptMessage = "";
		for (int i = 0; i < decryptArray.length; i++) {
			decryptMessage = decryptMessage + (char) (decryptArray[i]);
		}

		return decryptMessage;
	}

	// getN
	public static int getN() {
		return n;
	}

	// getE
	public static int getE() {
		return e;
	}

	// getD
	public static int getD() {
		return d;
	}

	// getOn
	public static int getOn() {
		return on;
	}

	// 随机生成质素p
	public static int getP() {
		return p;
	}

	// 随机生成质素q
	public static int getQ() {
		return q;
	}
}
