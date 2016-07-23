package com.koal;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
/**
 * 
 * @author C579
 *
 */
public class MD {

	/*public static void main(String[] args) {
		String algorithm = "MD5";
		MD md = new MD(algorithm);
		//String filePath = "D:\\Downloads\\ubuntu-14.04.3-desktop-amd64.iso";
		String filePath = "D:\\Downloads\\commons-cli-1.3.1-bin.tar.gz";
		String form = "raw";
		String output = "G:\\test\\output-md." + form;
		byte[] data = md.md(filePath);
		WriteToFile wtf = new WriteToFile();
		wtf.wirteToFile(output, data, form);
	}*/
	//定义摘要算法的类型
	private static String algorithm;
	
	public MD(String algorithm) {
		this.algorithm = algorithm;
	}
	/**
	 * 生成信息摘要操作
	 * 
	 * @param algorithm
	 *            String类型 信息摘要算法类型
	 * @param filePath
	 *            String类型 文件的路径
	 * @return mdByte byte[] 类型 生成摘要原文
	 */
	public static byte[] md(String filePath) {
		byte[] mdByte = null;
		try {
			// 初始化md5算法
			MessageDigest md = MessageDigest.getInstance(algorithm);
			// 读取要输入的文件
			FileInputStream file = new FileInputStream(filePath);

			
			byte[] dataBytes = new byte[1024];
			int read = 0;
			// 文件生成摘要
			while ((read = file.read(dataBytes)) != -1) {
				md.update(dataBytes, 0, read);
			}

			// 读取加密后的密文
			mdByte = md.digest();

		} catch (NoSuchAlgorithmException e) {
			// 函数库算法不存在报错
			e.printStackTrace();
			System.out.println("信息摘要算法" + algorithm + "不存在！");
		} catch (FileNotFoundException e) {
			// 读取空文件错误
			e.printStackTrace();
			System.out.println("读取的文件不存在");
		} catch (IOException e) {
			// 文件读取错误
			e.printStackTrace();
			System.out.println("文件读取失败");
		}
		return mdByte;
	}
}