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
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.IvParameterSpec;

import org.bouncycastle.util.encoders.Base64;

/**
 * 
 * @author C579
 *
 */
public class AES {
	
   public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, NoSuchPaddingException, IOException {
	/*String algorithm = "RSA";
	KeyPair keyPair = new KeyGenerate().keyPairGenerate(algorithm);
	PrivateKey privateKey = keyPair.getPrivate();
	PublicKey publicKey = keyPair.getPublic();
	String plainText = "hello the world jsdfjdsjcndjsnjdsncjdnsjcnsdjcn";
	byte[] encypted = encrypt(algorithm, plainText, privateKey);
	WriteToFile writeToFile = new WriteToFile();
	String form = "b64";
	String filenPath = "G:\\test\\rsa-enc." + form.toLowerCase();
	String prikey = "G:\\test\\rsa-prikey." + form.toLowerCase();
	writeToFile.wirteToFile(filenPath, encypted, form);
	writeToFile.wirteToFile(prikey, privateKey.getEncoded(), form);
	byte[] decypted =  decrypt(algorithm, encypted, publicKey);
	System.out.println("dec: " + new String(decypted));
	String filenPath1 = "G:\\test\\rsa-dec." + form.toLowerCase();
	String pubkey = "G:\\test\\rsa-pubkey." + form.toLowerCase();
	writeToFile.wirteToFile(filenPath1, decypted, form);
	writeToFile.wirteToFile(pubkey, publicKey.getEncoded(), form);*/
	Key key = new KeyGenerate().keyGenerate("AES");
	new WriteToFile().wirteToFile("G:\\test\\aes-key.b64", key.getEncoded(), "b64");
	File inputFile = new File("D:\\Downloads\\Screenshot from 2016-01-27 06_58_46.png");
	File outputFile = new File("G:\\test\\en-aes1.txt");
	File outputFile1 = new File("G:\\test\\de-aes.png");
	String iv = "9238513401340227";
	encrypt(inputFile, outputFile, key, iv);
	decrypt(outputFile, outputFile1, key, iv);
   }
	
	public static void encrypt(File inputFile,File outputFile, Key key,String iv){
		try {
			//新建文件
			InputStream inputStream = null;
			OutputStream outputStream = null;
			inputStream = new FileInputStream(inputFile);
			outputStream = new FileOutputStream(outputFile);
			//初始化向量
			IvParameterSpec ivp = new IvParameterSpec(iv.getBytes());
			//加密算法初始化
			Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
			cipher.init(Cipher.ENCRYPT_MODE, key, ivp);
			
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
		} catch (InvalidAlgorithmParameterException e) {
			// 算法参数异常
			System.out.println("算法参数异常");
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	public static void decrypt(File inputFile,File outputFile, Key key,String iv){
		try {
			//新建文件
			InputStream inputStream = null;
			OutputStream outputStream = null;
			inputStream = new FileInputStream(inputFile);
			outputStream = new FileOutputStream(outputFile);
			//初始化向量
			IvParameterSpec ivp = new IvParameterSpec(iv.getBytes());
			//加密算法初始化
			Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
			cipher.init(Cipher.DECRYPT_MODE, key, ivp);
			
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
		} catch (InvalidAlgorithmParameterException e) {
			// 算法参数异常
			System.out.println("算法参数异常");
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