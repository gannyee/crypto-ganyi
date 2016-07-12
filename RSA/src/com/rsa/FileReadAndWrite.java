package com.rsa;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileReadAndWrite {
	
	//将数组写入本地文件
	public static void write(int[] array) throws IOException{
		
		File file = new File("d:\\encrypt.txt");
		FileWriter out = new FileWriter(file);
		//获取文件的路径
		System.out.println("文件成功写入：文件所在路径为：  " + file.getAbsolutePath());
		//将数组写入文件，每行数据之间TAB间隔
		for(int i = 0;i < array.length;i ++){
			out.write(array[i] + "\t");
		}
		out.close();
	}
	
	//读取本地文件
	public static int[] read(String fileName) throws IOException{
		BufferedReader in = new BufferedReader(new FileReader("d:\\encrypt.txt"));
		String line;
		int[] array = new int[100];
		while((line = in.readLine()) != null){
			String[] temp = line.split("\t");
			for(int i = 0;i < temp.length;i ++){
				array[i] = Integer.parseInt(temp[i]);
			}
		}
		in.close();
		return array;
	}
}
