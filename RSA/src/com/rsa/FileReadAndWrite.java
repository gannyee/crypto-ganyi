package com.rsa;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileReadAndWrite {
	
	//������д�뱾���ļ�
	public static void write(int[] array) throws IOException{
		
		File file = new File("d:\\encrypt.txt");
		FileWriter out = new FileWriter(file);
		//��ȡ�ļ���·��
		System.out.println("�ļ��ɹ�д�룺�ļ�����·��Ϊ��  " + file.getAbsolutePath());
		//������д���ļ���ÿ������֮��TAB���
		for(int i = 0;i < array.length;i ++){
			out.write(array[i] + "\t");
		}
		out.close();
	}
	
	//��ȡ�����ļ�
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
