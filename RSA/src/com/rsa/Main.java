package com.rsa;

import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

/**
 * ������ڣ�����main
 * @author C579
 *
 */
public class Main {

	/*
	 * ������������
	 * Console�����������
	 */
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(System.in);
		Console console = new Console();
		System.out.println("����rsa -help ��ȡ����ʹ�ð���");
		while (in.hasNextLine()) {
			console.command(in.nextLine());
		}
	}

}
