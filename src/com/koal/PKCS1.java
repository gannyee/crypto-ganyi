package com.koal;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

/**
 * 
 * @author C579
 *
 */
public class PKCS1 {

/*	public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeyException, NoSuchProviderException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, SignatureException, IOException {
		String algorithm = "RSA";
		KeyPair keyPair = new KeyGenerate().keyPairGenerate(algorithm);
		PrivateKey privateKey = keyPair.getPrivate();
		PublicKey publicKey = keyPair.getPublic();
		WriteToFile writeToFile = new WriteToFile();
		String form = "HEX";
		String outfile = "G:\\test\\my-private-key." + form.toLowerCase();
		String outfile1 = "G:\\test\\my-public-key." + form.toLowerCase();
		writeToFile.wirteToFile(outfile, privateKey.getEncoded(), form);
		writeToFile.wirteToFile(outfile1, publicKey.getEncoded(), form);
		PKCS1 pkcs1 = new PKCS1();
		String filePath = "D:\\Downloads\\ubuntu-14.04.3-desktop-amd64.iso";
		String outfile2 = "G:\\test\\signed." + form.toLowerCase();
		byte[] signed = pkcs1.sign(filePath, privateKey);
		writeToFile.wirteToFile(outfile2, signed, form);
		boolean isVerified = verify(filePath, publicKey, signed);
		System.out.println("isVerified? " + isVerified);
	}*/
	/**
	 * PKSC#1签名
	 * 
	 * @param data
	 *            byte[]类型 待签字的信息
	 * @param privateKey
	 *            PrivateKey类型 用于数字签字的密钥
	 * @return 签字后的数据
	 */
	public static byte[] sign(String filePath, PrivateKey privateKey){
		
		byte[] signByte = null;
		try {
			//签名算法初始化
			Signature signer = Signature.getInstance("SHA256WithRSA");
			signer.initSign(privateKey);
			//读入文件
			FileInputStream file = new FileInputStream(filePath);
			byte[] dataBytes = new byte[1024];
			int read = 0;
			//文件内容签名
			while ((read = file.read(dataBytes)) != -1) {
				signer.update(dataBytes, 0, read);
			}
			signByte = signer.sign();
		} catch (IOException e) {
			// 文件操作异常
			System.out.println("文件操作异常！");
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			// 密钥异常
			System.out.println("密钥异常！");
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// 签名算法初始化异常
			System.out.println("签名算法初始化异常！");
			e.printStackTrace();
		} catch (SignatureException e) {
			// 签名过程出现异常
			System.out.println("签名过程出现异常！");
			e.printStackTrace();
		}
		
		return signByte;
	}

	/**
	 * PKSC#验签
	 * 
	 * @param data
	 *            待验签的数据
	 * @param publicKey
	 *            用于验签的公钥
	 * @param originText
	 *            用于对比验签的原文
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchProviderException
	 * @throws NoSuchPaddingException
	 * @throws InvalidKeyException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 * @throws IOException
	 * @throws SignatureException
	 */
	public static boolean verify(String filePath, PublicKey publicKey, byte[] originText)
			throws NoSuchAlgorithmException, NoSuchProviderException, NoSuchPaddingException, InvalidKeyException,
			IllegalBlockSizeException, BadPaddingException, IOException, SignatureException {
		byte[] veryfiByte = null;
		//初始化签名算法
		Signature signer = Signature.getInstance("SHA256WithRSA");
		signer.initVerify(publicKey);
		//读取文件
		FileInputStream file = new FileInputStream(filePath);
		
		byte[] dataBytes = new byte[1024];
		int read = 0;
		// 文件生成摘要
		while ((read = file.read(dataBytes)) != -1) {
			signer.update(dataBytes, 0, read);
		}
		return signer.verify(originText);
	}
}
