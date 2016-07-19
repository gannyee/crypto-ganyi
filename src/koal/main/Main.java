package koal.main;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import koal.encrypt_decrypt.EncryptDecrypt;
import koal.key_generate.KeyGenerate;
import koal.message_digest.MD;
import koal.util.ToBase64;
import koal.util.ToHEX;

public class Main {
	public static void main(String[] args) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException, IOException {
	
		System.out.println("\t\t摘要算法");
		MD md = new MD();
		String algorith_md5 = "MD5";
		String filePath = "d:\\1.txt";
		byte[] md5 = md.md(algorith_md5, filePath);
		System.out.println("md5_base64: " + new ToBase64().toBase64(md5));
		System.out.println("md5_hex: " + new ToHEX().toHex(md5));
		System.out.println("--------------------------------------------------");
		String algorith_sha256 = "SHA-256";
		byte[] md_sha256 = md.md(algorith_sha256, filePath);
		System.out.println("md_sha256: " + new ToBase64().toBase64(md_sha256));
		System.out.println("md_sha256_hex: " + new ToHEX().toHex(md_sha256));
		System.out.println("--------------------------------------------------");
		String algorith_sha1 = "SHA-1";
		byte[] md_sha1 = md.md(algorith_sha1, filePath);
		System.out.println("md_sha1: " + new ToBase64().toBase64(md_sha1));
		System.out.println("md_sha1_hex: " + new ToHEX().toHex(md_sha1));
		System.out.println("--------------------------------------------------");
		
		
		System.out.println("\t\t加密算法");
		EncryptDecrypt encryptDecrypt = new EncryptDecrypt();
		String algorithm_aes = "AES";
		String text = "helloword";
		Key key = new KeyGenerate().keyGenerate(algorithm_aes);
		String aes_en = new ToBase64().toBase64(encryptDecrypt.encrypt(algorithm_aes, text, key));
		String aes_de =new String(encryptDecrypt.decrypt(algorithm_aes, aes_en, key));
		System.out.println("plantext: " + text);
		System.out.println("aes_es: " + aes_en);
		System.out.println("aes_de: " + aes_de);
		
		System.out.println("--------------------------------------------------");
		String algorithm_rc = "RC4";
		Key key_rc4 = new KeyGenerate().keyGenerate(algorithm_rc);
		String rc_en = new ToBase64().toBase64(encryptDecrypt.encrypt(algorithm_rc, text, key_rc4));
		String rc_de = new String(encryptDecrypt.decrypt(algorithm_rc, rc_en, key_rc4));
		System.out.println("plantext: " + text);
		System.out.println("aes_es: " + rc_en);
		System.out.println("aes_de: " + rc_de);
		
		System.out.println("--------------------------------------------------");
		String algorithm_rsa = "RSA";
		KeyPair key_rsa= new KeyGenerate().keyPairGenerate(algorithm_rsa);
		
		Key key_pub = key_rsa.getPublic();
		Key key_pri = key_rsa.getPrivate();
		String rsa_en = new ToBase64().toBase64(encryptDecrypt.encrypt(algorithm_rsa, text, key_pub));
		String rsa_de = new String(encryptDecrypt.decrypt(algorithm_rsa, rsa_en, key_pri));
		System.out.println("plantext: " + text);
		System.out.println("aes_es: " + rsa_en);
		System.out.println("aes_de: " + rsa_de);
		
	}
}
