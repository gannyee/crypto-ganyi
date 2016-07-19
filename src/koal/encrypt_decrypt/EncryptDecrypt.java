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
	 * @param algorithm String类型 算法类别
	 * @param plainText  String类型 待加密的明文
	 * @param key Key类型 用于加密的密钥
	 * @return encryptValue String 类型 对明文加密后的密文
	 * @throws InvalidKeyException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchPaddingException
	 */
	public static byte[] encrypt(String algorithm,String plainText,Key key) throws InvalidKeyException, IllegalBlockSizeException,
			BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException {
		/*//产生密钥操作
		Key key = generateKey();*/
		//获得加密操作算法
		Cipher cipher = Cipher.getInstance(algorithm);
		//算法操作模式
		cipher.init(Cipher.ENCRYPT_MODE, key);
		//进行加密操作
		byte[] encVal = cipher.doFinal(plainText.getBytes());
		/*//获得加密的密文
		String encryptValue = new BASE64Encoder().encode(encVal);*/
		//返回密文
		return encVal;
	}
	
	/**
	 * 
	 * @param algorithm String类型 算法类别
	 * @param encryptText  String类型 待解密的密文
	 * @param key Key类型 用于加密的密钥
	 * @return 解密后的明文
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchPaddingException
	 * @throws InvalidKeyException
	 * @throws IOException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 */
	public static byte[] decrypt(String algorithm,String encryptText,Key key) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IOException, IllegalBlockSizeException, BadPaddingException{
	/*	//产生密钥操作
		Key key = generateKey();*/
		//获得解密操作算法
		Cipher cipher = Cipher.getInstance(algorithm);
		//算法操作模式
		cipher.init(Cipher.DECRYPT_MODE, key);
		//对密文先进行base64解码
		byte[] decodeValue = new BASE64Decoder().decodeBuffer(encryptText);
		//对解码的可见字符串进行解密操作
		byte[] decValue = cipher.doFinal(decodeValue);
	/*	//获得解密后的密文
		String decryptValue = new String(decValue);*/
		//返回解密后的明文
		return decValue;
	}

}
