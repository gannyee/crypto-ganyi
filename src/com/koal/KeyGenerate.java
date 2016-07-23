package com.koal;

import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import javax.crypto.KeyGenerator;

/**
 * @author C579
 *
 */
public class KeyGenerate {

	/**
	 * 非对称加密，产生密钥对
	 * 
	 * @param algorithm
	 *            String类型 指定密钥对生成的算法对象
	 * @return keyPair Keypair类型 产生的密钥对
	 * @throws NoSuchAlgorithmException
	 */
	public static KeyPair keyPairGenerate(String algorithm) throws NoSuchAlgorithmException {
		KeyPair keyPair = KeyPairGenerator.getInstance(algorithm).generateKeyPair();
		return keyPair;
	}

	/**
	 * 对称加密，产生密钥
	 * 
	 * @param algorithm
	 * @return key Key类型 返回的密钥
	 * @throws NoSuchAlgorithmException
	 */
	public static Key keyGenerate(String algorithm) throws NoSuchAlgorithmException {
		KeyGenerator keyGenerator = KeyGenerator.getInstance(algorithm);
		Key key = keyGenerator.generateKey();
		return key;
	}
}