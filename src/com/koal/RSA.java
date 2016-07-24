package com.koal;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Arrays;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class RSA {

	public static void main(String[] args)
			throws NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException {
		KeyPair key = new KeyGenerate().keyPairGenerate("RSA");
		PrivateKey privateKey = key.getPrivate();
		PublicKey publicKey = key.getPublic();
		new WriteToFile().wirteToFile("G:\\test\\rsa-prikey.hex", privateKey.getEncoded(), "hex");
		new WriteToFile().wirteToFile("G:\\test\\rc-pubkey.hex", publicKey.getEncoded(), "hex");
		File inputFile = new File("D:\\Downloads\\DOC112814-011 (1).pdf");
		File outputFile = new File("G:\\test\\en-rsa.txt");
		File outputFile1 = new File("G:\\test\\de-rsa.pdf");
		encrypt(inputFile, outputFile, publicKey);
		decrypt(outputFile, outputFile1, privateKey);
	}

	public static void encrypt(File inputFile, File outputFile, PublicKey publicKey)
			throws IllegalBlockSizeException, BadPaddingException {
		try {
			// 新建文件
			InputStream inputStream = null;
			OutputStream outputStream = null;
			inputStream = new FileInputStream(inputFile);
			outputStream = new FileOutputStream(outputFile);
			// 加密算法初始化
			Cipher cipher = Cipher.getInstance("RSA");
			cipher.init(Cipher.ENCRYPT_MODE, publicKey);

			byte[] cache = new byte[117];
			byte[] encrByte = null;
			int read = 0;
			while ((read = inputStream.read(cache)) != -1) {
				encrByte = cipher.update(cache, 0, read);
				encrByte = cipher.doFinal();
				outputStream.write(encrByte);
				outputStream.flush();
			}
			inputStream.close();
			outputStream.close();
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
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void decrypt(File inputFile, File outputFile, PrivateKey privateKey)
			throws IllegalBlockSizeException, BadPaddingException {
		try {
			// 新建文件
			InputStream inputStream = null;
			OutputStream outputStream = null;
			inputStream = new FileInputStream(inputFile);
			outputStream = new FileOutputStream(outputFile);
			// 加密算法初始化
			Cipher cipher = Cipher.getInstance("RSA");
			cipher.init(Cipher.DECRYPT_MODE, privateKey);
			byte[] cache = new byte[128];
			byte[] decryType = null;
			int read = 0;
			while ((read = inputStream.read(cache)) != -1) {
				decryType = cipher.update(cache, 0, read);
				decryType = cipher.doFinal();
				outputStream.write(decryType);
				outputStream.flush();
			}
			inputStream.close();
			outputStream.close();
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
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
