package koal.rsa_sign;

import java.io.IOException;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Security;
import java.security.Signature;
import java.security.SignatureException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import koal.key_generate.KeyGenerate;

/**
 * 
 * @author C579
 *
 */
public class Sign {

	/*public static void main(String[] args)
			throws NoSuchAlgorithmException, InvalidKeyException, NoSuchProviderException, SignatureException,
			NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, IOException {
		KeyPair keyPair = KeyGenerate.keyPairGenerate("RSA");
		PrivateKey privateKey = keyPair.getPrivate();
		PublicKey publicKey = keyPair.getPublic();
		String message = "hello world";
		byte[] data = message.getBytes();
		byte[] signData = sign(data, privateKey);
		System.out.println("sign:  " + new BigInteger(signData).toString(16));
		System.out.println(verify(data, publicKey,signData));
	}*/
	/**
	 * PKSC#1签名
	 * @param data
	 *            byte[]类型 待签字的信息
	 * @param privateKey
	 *            PrivateKey类型 用于数字签字的密钥
	 * @return 签字后的数据
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchProviderException
	 * @throws InvalidKeyException
	 * @throws SignatureException
	 */
	public static byte[] sign(byte[] data, PrivateKey privateKey)
			throws NoSuchAlgorithmException, NoSuchProviderException, InvalidKeyException, SignatureException {

		Signature signer = Signature.getInstance("SHA256WithRSA");
		signer.initSign(privateKey);
		signer.update(data);
		return signer.sign();
	}

	/**
	 * PKSC#验签
	 * @param data 待验签的数据
	 * @param publicKey 用于验签的公钥
	 * @param originText 用于对比验签的原文
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
	public static boolean verify(byte[] data, PublicKey publicKey,byte[] originText)
			throws NoSuchAlgorithmException, NoSuchProviderException, NoSuchPaddingException, InvalidKeyException,
			IllegalBlockSizeException, BadPaddingException, IOException, SignatureException {
		Signature signer = Signature.getInstance("SHA256WithRSA");
		signer.initVerify(publicKey);
		signer.update(data);
		return signer.verify(originText);
	}
}
