package com.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * 文件流输入输出的测试代码
 * @author C579
 *
 */
public class FileReadAndWriteTest {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		write();
		read();
	}
	
	/*
	 * 将数组写入本地文件
	 * 参数： 无
	 * 返回：无
	 */
	public static void write() throws IOException{
		int[] array = new int[100];
		for(int i = 0;i < array.length;i ++){
			array[i] = (int) (Math.random() * 100);
		}
		
		File file = new File("d:\\array.txt");
		FileWriter out = new FileWriter(file);
		
		//将数组写入文件，每行数据之间TAB间隔
		for(int i = 0;i < array.length;i ++){
			out.write(array[i] + "\t");
		}
		out.close();
	}
	
	/*
	 * 读取写入本地文件的数据
	 * 参数:无
	 * 返回：无
	 */
	public static void read() throws IOException{
		BufferedReader in = new BufferedReader(new FileReader("d:\\array.txt"));
		String line;
		int[] array = new int[100];
		//按行读取文件数据
		while((line = in.readLine()) != null){
			String[] temp = line.split("\t");
			for(int i = 0;i < temp.length;i ++){
				array[i] = Integer.parseInt(temp[i]);
			}
		}
		in.close();
		
		for(int i = 0;i < array.length;i ++){
			System.out.print(array[i] + " ");
		}
	}
}
