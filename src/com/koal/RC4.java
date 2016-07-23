package com.koal;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;

public class RC4 {
	
	public static void main(String[] args) throws NoSuchAlgorithmException {
		Key key = new KeyGenerate().keyGenerate("RC4");
		new WriteToFile().wirteToFile("G:\\test\\rc-key.b64", key.getEncoded(), "b64");
		File inputFile = new File("D:\\Downloads\\Screenshot from 2016-01-27 06_58_46.png");
		File outputFile = new File("G:\\test\\en-rc4.txt");
		File outputFile1 = new File("G:\\test\\de-rc4.png");
		encrypt(inputFile, outputFile, key);
		decrypt(outputFile, outputFile1, key);
	}
	
	public static void encrypt(File inputFile,File outputFile, Key key){
		try {
			//新建文件
			InputStream inputStream = null;
			OutputStream outputStream = null;
			inputStream = new FileInputStream(inputFile);
			outputStream = new FileOutputStream(outputFile);
			//加密算法初始化
			Cipher cipher = Cipher.getInstance("RC4");
			cipher.init(Cipher.ENCRYPT_MODE, key);
			
			//以加密流写入文件
			CipherInputStream cipherInputStream = new CipherInputStream(inputStream, cipher);
			
			byte[] cache = new byte[1024];
			int read = 0;
			while((read = cipherInputStream.read(cache)) != -1){
				outputStream.write(cache, 0, read);
				outputStream.flush();
			}
			cipherInputStream.close();
		} catch (NoSuchAlgorithmException e) {
			// 加密算法调用错误异常
			System.out.println("加密算法调用错误异常");
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			// 加密算法填充异常
			System.out.println("加密算法填充异常");
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			// 密钥异常
			System.out.println("用于加密的文件的密钥异常");
			e.printStackTrace();
		}catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	public static void decrypt(File inputFile,File outputFile, Key key){
		try {
			//新建文件
			InputStream inputStream = null;
			OutputStream outputStream = null;
			inputStream = new FileInputStream(inputFile);
			outputStream = new FileOutputStream(outputFile);
			//加密算法初始化
			Cipher cipher = Cipher.getInstance("RC4");
			cipher.init(Cipher.DECRYPT_MODE, key);
			
			//以加密流写入文件
			CipherOutputStream cipheroutputStream = new CipherOutputStream(outputStream, cipher);
			
			byte[] cache = new byte[1024];
			int read = 0;
			while((read = inputStream.read(cache)) != -1){
				cipheroutputStream.write(cache, 0, read);
				cipheroutputStream.flush();
			}
			cipheroutputStream.close();
		} catch (NoSuchAlgorithmException e) {
			// 加密算法调用错误异常
			System.out.println("加密算法调用错误异常");
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			// 加密算法填充异常
			System.out.println("加密算法填充异常");
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			// 密钥异常
			System.out.println("用于加密的文件的密钥异常");
			e.printStackTrace();
		}catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
}
