package com.koal;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.Key;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.UnrecoverableKeyException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Enumeration;

public class CertificateParse {

	/*
	 * public static void main(String[] args) { String keyFile =
	 * "D://ganyi.pfx"; String keyPass = "123456"; getPrivateKey(keyFile,
	 * keyPass); }
	 */

	private static X509Certificate certificate;
	private static PrivateKey privateKey;

	public CertificateParse(String keyFile, String keyPass) {
		parseKeyFile(keyFile, keyPass);
	}

	public static void parseKeyFile(String keyFile, String keyPass) {

		try {
			KeyStore ks = KeyStore.getInstance("PKCS12");
			FileInputStream fis = new FileInputStream(keyFile);
			// 如何 keyStore 密码为空，必须设置改密码为null，否则keystore将报错
			char[] nPassword = null;

			if ((keyPass == null) || keyPass.trim().equals("")) {
				nPassword = null;
			} else {
				nPassword = keyPass.toCharArray();
			}

			ks.load(fis, nPassword);
			fis.close();
			System.out.println("keystore= " + ks.getType());
			// 遍历证书别名，获得密钥
			Enumeration enumas = ks.aliases();
			String keyAlias = null;
			if (enumas.hasMoreElements()) {
				keyAlias = (String) enumas.nextElement();
				System.out.println("alias: [ + " + keyAlias + " ] ");
			}
			// 通过上述遍历，可以知道证书，现在可以获得密钥
			System.out.println("is kay entry= " + ks.isKeyEntry(keyAlias));
			privateKey = (PrivateKey) ks.getKey(keyAlias, nPassword);
			certificate = (X509Certificate) ks.getCertificate(keyAlias);
			PublicKey publicKey = certificate.getPublicKey();
			// 通过java反射机制获得证书类型
			System.out.println("cert class= " + certificate.getClass().getName());
			System.out.println("cert: " + certificate);
			System.out.println("public key: " + publicKey);
			System.out.println("private key: " + privateKey);
		} catch (KeyStoreException e) {
			// 从文件读取密钥失败
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// 文件并不存在异常
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// 算法获取失败
			e.printStackTrace();
		} catch (CertificateException e) {
			// 证书吸相关异常
			e.printStackTrace();
		} catch (IOException e) {
			// 文件读取出错
			e.printStackTrace();
		} catch (UnrecoverableKeyException e) {
			// 获取密钥是出错
			e.printStackTrace();
		}
	}

	/**
	 * @return the certificate
	 */
	public static X509Certificate getCertificate() {
		return certificate;
	}

	/**
	 * @param certificate
	 *            the certificate to set
	 */
	public static void setCertificate(X509Certificate certificate) {
		CertificateParse.certificate = certificate;
	}

	/**
	 * @return the privateKey
	 */
	public static PrivateKey getPrivateKey() {
		return privateKey;
	}

	/**
	 * @param privateKey
	 *            the privateKey to set
	 */
	public static void setPrivateKey(PrivateKey privateKey) {
		CertificateParse.privateKey = privateKey;
	}

}
