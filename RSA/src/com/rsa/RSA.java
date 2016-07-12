package com.rsa;

import java.io.IOException;
import java.util.Arrays;
import java.util.Base64;

/**
 * RSA�㷨�ľ���ʵ��
 * @author C579
 *
 */
public class RSA {

	// ����p,q,n,e,on
	private static int p;
	private static int q;
	private static int n;
	private static int e;
	private static int on;
	private static int d;

	// ���캯������ʼ��p,q,n,on
	public RSA() {
		// �������������p,q
		RandomPrime rp = new RandomPrime();
		rp.generateRandomPrime(rp.primeList());
		p = RandomPrime.generatePrime()[0];
		q = RandomPrime.generatePrime()[1];
		// ����n
		n = p * q;
		// ����p,q��ŷ����
		on = (p - 1) * (q - 1);
		// ���ѡȡ0 < e < on
		e = 1;
		// ��֤���ѡȡ��e��on�ǻ���
		while (on % e == 0) {
			e++;
		}
		// ����˽Կ
		d = 1;
		while (((d * e) % on) != 1) {
			d++;
		}
	}

	/*
	 * �ڲ�˽�м��ܷ������ⲿ�����޷����ã����ļ��� ������ message String���� �����ܵ����ģ�e int���� ��Կ��n int����
	 * ���ڼ��� ����ֵ���޷���ֵ
	 */
	private static void encrypt(String message, int e, int n) throws IOException {
		// �����������ģ����
		BigDataModPower bdm = new BigDataModPower();

		// ���ַ���תΪascii��
		int[] array = new StringToAscii().stringToAscii(message);

		// encryptArray���ڴ�ż��ܺ�ÿ���ַ�������
		int[] encrytpArray = new int[array.length];

		// ��ascii�����ݽ������ļ���
		for (int i = 0; i < array.length; i++) {
			encrytpArray[i] = bdm.bigDataModPower(array[i], e, n);
		}

		// �Լ��ܵ����Ľ���base64����ת��
		String base64 = Base64.getEncoder().encodeToString(Arrays.toString(encrytpArray).getBytes());
		System.out.println("����֮�� " + base64);
	}

	/*
	 * �ⲿ�ɵ��ü��ܷ��� ������ message String���� �����ܵ����� ����ֵ���޷���ֵ
	 */
	public static void encrypt(String message) throws IOException {
		encrypt(message, getE(), getN());
	}

	/*
	 * ���Ľ��ܣ���base��������Ĵ�ֵ�����������Ľ��н��� ������ encrypt String���� ���ģ�d int���� ��Կ��n int����
	 * ���ڽ��� ����ֵ��decryptMessage String���� ���ܺ������
	 */
	public static String decrypt(String encrypt, int d, int n) {
		// ��base64��������Ľ��н���
		byte[] asByte = Base64.getDecoder().decode(encrypt);
		// ��byte[] ���͵�����תΪString[] ���ͣ�����֮���String����תΪint����
		String[] newS = new String(asByte).substring(1, (new String(asByte).length() - 1)).split(", ");

		// String����תint����
		int[] array = new int[newS.length];
		for (int i = 0; i < newS.length; i++) {
			array[i] = Integer.parseInt(newS[i]);
		}

		// ���ܣ�������ģ����
		BigDataModPower bdm = new BigDataModPower();
		int len = array.length;
		int[] decryptArray = new int[array.length];
		for (int i = 0; i < len; i++) {
			decryptArray[i] = bdm.bigDataModPower(array[i], d, n);
		}

		// ���ܺ������
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

	// �����������p
	public static int getP() {
		return p;
	}

	// �����������q
	public static int getQ() {
		return q;
	}
}
