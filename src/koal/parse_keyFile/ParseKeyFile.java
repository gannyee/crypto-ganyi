package koal.parse_keyFile;

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

import sun.rmi.runtime.Log;

public class ParseKeyFile {
	
	/*public static void main(String[] args) {
		String keyFile = "D://ganyi.pfx";
		String keyPass = "123456";
		getPrivateKey(keyFile, keyPass);
	}
	*/
	
	private static X509Certificate certificate;
	private static PrivateKey privateKey;
	
	public ParseKeyFile(String keyFile,String keyPass) {
		parseKeyFile(keyFile, keyPass);
	}
	public static void  parseKeyFile(String keyFile,String keyPass) {
		
		try {
			KeyStore ks = KeyStore.getInstance("PKCS12");
			FileInputStream fis = new FileInputStream(keyFile);
			//��� keyStore ����Ϊ�գ��������ø�����Ϊnull������keystore������
			char[] nPassword = null;
			
			if((keyPass == null) || keyPass.trim().equals("")){
				nPassword = null;
			}else{
				nPassword = keyPass.toCharArray();
			}
			
			ks.load(fis,nPassword);
			fis.close();
			System.out.println("keystore= " + ks.getType());
			//����֤������������Կ
			Enumeration enumas = ks.aliases();
			String keyAlias = null;
			if(enumas.hasMoreElements()){
				keyAlias = (String)enumas.nextElement();
				System.out.println("alias: [ + " + keyAlias + " ] ");
			}
			//ͨ����������������֪��֤�飬���ڿ��Ի����Կ
			System.out.println("is kay entry= " + ks.isKeyEntry(keyAlias));
			privateKey = (PrivateKey) ks.getKey(keyAlias, nPassword);
			certificate = (X509Certificate) ks.getCertificate(keyAlias);
			PublicKey publicKey = certificate.getPublicKey();
			//ͨ��java������ƻ��֤������
			System.out.println("cert class= " + certificate.getClass().getName());
			System.out.println("cert: " + certificate);
			System.out.println("public key: " + publicKey);
			System.out.println("private key: " + privateKey);
		} catch (KeyStoreException e) {
			// ���ļ���ȡ��Կʧ��
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// �ļ����������쳣
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// �㷨��ȡʧ��
			e.printStackTrace();
		} catch (CertificateException e) {
			//֤��������쳣
			e.printStackTrace();
		} catch (IOException e) {
			//�ļ���ȡ����
			e.printStackTrace();
		} catch (UnrecoverableKeyException e) {
			// ��ȡ��Կ�ǳ���
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
	 * @param certificate the certificate to set
	 */
	public static void setCertificate(X509Certificate certificate) {
		ParseKeyFile.certificate = certificate;
	}

	/**
	 * @return the privateKey
	 */
	public static PrivateKey getPrivateKey() {
		return privateKey;
	}

	/**
	 * @param privateKey the privateKey to set
	 */
	public static void setPrivateKey(PrivateKey privateKey) {
		ParseKeyFile.privateKey = privateKey;
	}
	
	
	
}
