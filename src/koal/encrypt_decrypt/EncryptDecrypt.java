package koal.encrypt_decrypt;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

import com.sun.org.apache.bcel.internal.generic.NEW;

import koal.key_generate.KeyGenerate;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * 
 * @author C579
 *
 */
public class EncryptDecrypt {
	
	/**
	 * @param algorithm String���� �㷨���
	 * @param plainText  String���� �����ܵ�����
	 * @param key Key���� ���ڼ��ܵ���Կ
	 * @return encryptValue String ���� �����ļ��ܺ������
	 * @throws InvalidKeyException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchPaddingException
	 */
	public static byte[] encrypt(String algorithm,String plainText,Key key) throws InvalidKeyException, IllegalBlockSizeException,
			BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException {
		/*//������Կ����
		Key key = generateKey();*/
		//��ü��ܲ����㷨
		Cipher cipher = Cipher.getInstance(algorithm);
		//�㷨����ģʽ
		cipher.init(Cipher.ENCRYPT_MODE, key);
		//���м��ܲ���
		byte[] encVal = cipher.doFinal(plainText.getBytes());
		/*//��ü��ܵ�����
		String encryptValue = new BASE64Encoder().encode(encVal);*/
		//��������
		return encVal;
	}
	
	/**
	 * 
	 * @param algorithm String���� �㷨���
	 * @param encryptText  String���� �����ܵ�����
	 * @param key Key���� ���ڼ��ܵ���Կ
	 * @return ���ܺ������
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchPaddingException
	 * @throws InvalidKeyException
	 * @throws IOException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 */
	public static byte[] decrypt(String algorithm,String encryptText,Key key) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IOException, IllegalBlockSizeException, BadPaddingException{
	/*	//������Կ����
		Key key = generateKey();*/
		//��ý��ܲ����㷨
		Cipher cipher = Cipher.getInstance(algorithm);
		//�㷨����ģʽ
		cipher.init(Cipher.DECRYPT_MODE, key);
		//�������Ƚ���base64����
		byte[] decodeValue = new BASE64Decoder().decodeBuffer(encryptText);
		//�Խ���Ŀɼ��ַ������н��ܲ���
		byte[] decValue = cipher.doFinal(decodeValue);
	/*	//��ý��ܺ������
		String decryptValue = new String(decValue);*/
		//���ؽ��ܺ������
		return decValue;
	}

}
