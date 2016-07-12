package com.rsa;

import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

/**
 * 程序入口，主类main
 * @author C579
 *
 */
public class Main {

	/*
	 * 整个程序的入口
	 * Console界面输出内容
	 */
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(System.in);
		Console console = new Console();
		System.out.println("输入rsa -help 获取命令使用帮助");
		while (in.hasNextLine()) {
			console.command(in.nextLine());
		}
	}

}
