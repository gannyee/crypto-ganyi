package koal.key_generate;

import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import javax.crypto.KeyGenerator;

import koal.util.ToHEX;

/**
 * @author C579
 *
 */
public class KeyGenerate {

	/**
	 * �ǶԳƼ��ܣ�������Կ��
	 * @param algorithm String���� ָ����Կ�����ɵ��㷨����
	 * @return keyPair Keypair���� ��������Կ��
	 * @throws NoSuchAlgorithmException
	 */
	public static KeyPair keyPairGenerate(String algorithm) throws NoSuchAlgorithmException{
		KeyPair keyPair = KeyPairGenerator.getInstance(algorithm).generateKeyPair();
		return keyPair;
	}
	
	/**
	 * �ԳƼ��ܣ�������Կ
	 * @param algorithm
	 * @return key Key���� ���ص���Կ
	 * @throws NoSuchAlgorithmException
	 */
	public static Key keyGenerate(String algorithm) throws NoSuchAlgorithmException{
		KeyGenerator keyGenerator = KeyGenerator.getInstance(algorithm);
		Key key = keyGenerator.generateKey();
		return key;
	}
}
