package com.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * �ļ�����������Ĳ��Դ���
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
	 * ������д�뱾���ļ�
	 * ������ ��
	 * ���أ���
	 */
	public static void write() throws IOException{
		int[] array = new int[100];
		for(int i = 0;i < array.length;i ++){
			array[i] = (int) (Math.random() * 100);
		}
		
		File file = new File("d:\\array.txt");
		FileWriter out = new FileWriter(file);
		
		//������д���ļ���ÿ������֮��TAB���
		for(int i = 0;i < array.length;i ++){
			out.write(array[i] + "\t");
		}
		out.close();
	}
	
	/*
	 * ��ȡд�뱾���ļ�������
	 * ����:��
	 * ���أ���
	 */
	public static void read() throws IOException{
		BufferedReader in = new BufferedReader(new FileReader("d:\\array.txt"));
		String line;
		int[] array = new int[100];
		//���ж�ȡ�ļ�����
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
